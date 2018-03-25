/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.0 */
package org.extendj.ast;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Set;
import beaver.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.LinkedHashSet;
import org.jastadd.util.*;
import java.util.zip.*;
import java.io.*;
import org.jastadd.util.PrettyPrintable;
import org.jastadd.util.PrettyPrinter;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
/**
 * @ast node
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/grammar/Java.ast:296
 * @astdecl SwitchStmt : BranchTargetStmt ::= Expr Block;
 * @production SwitchStmt : {@link BranchTargetStmt} ::= <span class="component">{@link Expr}</span> <span class="component">{@link Block}</span>;

 */
public class SwitchStmt extends BranchTargetStmt implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/PrettyPrint.jadd:573
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("switch (");
    out.print(getExpr());
    out.print(") ");
    out.print(getBlock());
  }
  /**
   * @return label to be used as default target in switch bytecode instruction
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1603
   */
  private int defaultLabel() {
    DefaultCase defaultCase = defaultCase();
    if (defaultCase != null) {
      return defaultCase.label();
    } else {
      return end_label();
    }
  }
  /**
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:137
   */
  private void genFirstSwitch(
      CodeGeneration gen,
      Collection<StringLblGroup> groups) {

    int defaultLbl = defaultLabel();
    int index_b = localNumB();

    // Store the value of the switch expr so that it is only evaluated once!
    getExpr().createBCode(gen);
    getExpr().emitCastTo(gen, typeString());

    // Push the hash code for the switch instruction.
    if (getExpr().isConstant()) {
      // Statically computed hash code.
      typeString().emitStoreLocal(gen, index_b);
      int hashCode = getExpr().constant().stringValue().hashCode();
      gen.ICONST(hashCode);
    } else {
      // Dynamically computed hash code.
      gen.DUP(typeString());
      typeString().emitStoreLocal(gen, index_b);
      hashCodeMethod().emitInvokeMethod(gen,
          lookupType("java.lang", "Object"));
    }

    // Emit switch instruction.
    gen.SWITCH(groups, defaultLbl);

    // Code generation for switch body
    for (StringLblGroup group : groups) {
      gen.addLabel(group.label);

      // Possible hash miss. Check for equality.
      Iterator<StringLabel> iter = group.subLabels.iterator();
      while (iter.hasNext()) {
        StringLabel lbl = iter.next();
        int thenLbl;
        if (iter.hasNext()) {
          thenLbl = gen.constantPool().newLabel();
        } else{
          // Last conditional branches to default label.
          thenLbl = defaultLbl;
        }

        typeString().emitLoadLocal(gen, index_b);
        gen.LDC(lbl.string);
        equalsMethod().emitInvokeMethod(gen,
            lookupType("java.lang", "Object"));
        gen.IFEQ(thenLbl);
        gen.GOTO(lbl.label);

        if (iter.hasNext()) {
          gen.addLabel(thenLbl);
        }
      }
    }
  }
  /**
   * Generate invocation of method
   * {@code java.lang.Object.hashCode()}.
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:199
   */
  private MethodDecl hashCodeMethod() {
    TypeDecl objectType = lookupType("java.lang", "Object");
    for (MethodDecl method :
        (Collection<MethodDecl>) objectType.memberMethods("hashCode")) {
      if (method.getNumParameter() == 0) {
        return method;
      }
    }
    throw new Error("Could not find java.lang.Object.hashCode()");
  }
  /**
   * Generate invocation of method
   * {@code java.lang.Object.equals(java.lang.Object)}.
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:214
   */
  private MethodDecl equalsMethod() {
    TypeDecl objectType = lookupType("java.lang", "Object");
    for (MethodDecl method :
        (Collection<MethodDecl>) objectType.memberMethods("equals")) {
      if (method.getNumParameter() == 1 &&
          method.getParameter(0).getTypeAccess().type() == objectType) {
        return method;
      }
    }
    throw new Error("Could not find java.lang.Object.equals()");
  }
  /**
   * @declaredat ASTNode:1
   */
  public SwitchStmt() {
    super();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:10
   */
  public void init$Children() {
    children = new ASTNode[2];
  }
  /**
   * @declaredat ASTNode:13
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Expr", "Block"},
    type = {"Expr", "Block"},
    kind = {"Child", "Child"}
  )
  public SwitchStmt(Expr p0, Block p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:23
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:29
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:33
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    unassignedAfterLastStmt_Variable_reset();
    caseMap_reset();
    canCompleteNormally_reset();
    defaultCase_reset();
    end_label_reset();
    enumIndexExpr_reset();
    typeInt_reset();
    typeLong_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  public SwitchStmt clone() throws CloneNotSupportedException {
    SwitchStmt node = (SwitchStmt) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:56
   */
  public SwitchStmt copy() {
    try {
      SwitchStmt node = (SwitchStmt) clone();
      node.parent = null;
      if (children != null) {
        node.children = (ASTNode[]) children.clone();
      }
      return node;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " + getClass().getName());
    }
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:75
   */
  @Deprecated
  public SwitchStmt fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:85
   */
  public SwitchStmt treeCopyNoTransform() {
    SwitchStmt tree = (SwitchStmt) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) children[i];
        if (child != null) {
          child = child.treeCopyNoTransform();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:105
   */
  public SwitchStmt treeCopy() {
    SwitchStmt tree = (SwitchStmt) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) getChild(i);
        if (child != null) {
          child = child.treeCopy();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:119
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the Expr child.
   * @param node The new node to replace the Expr child.
   * @apilevel high-level
   */
  public void setExpr(Expr node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Expr child.
   * @return The current node used as the Expr child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Expr")
  public Expr getExpr() {
    return (Expr) getChild(0);
  }
  /**
   * Retrieves the Expr child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Expr child.
   * @apilevel low-level
   */
  public Expr getExprNoTransform() {
    return (Expr) getChildNoTransform(0);
  }
  /**
   * Replaces the Block child.
   * @param node The new node to replace the Block child.
   * @apilevel high-level
   */
  public void setBlock(Block node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the Block child.
   * @return The current node used as the Block child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Block")
  public Block getBlock() {
    return (Block) getChild(1);
  }
  /**
   * Retrieves the Block child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Block child.
   * @apilevel low-level
   */
  public Block getBlockNoTransform() {
    return (Block) getChildNoTransform(1);
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/backend/AutoBoxingCodegen.jrag:207
   */
    public void refined_AutoBoxingCodegen_SwitchStmt_createBCode(CodeGeneration gen) {
    super.createBCode(gen);
    int cond_label = gen.constantPool().newLabel();
    int switch_label = gen.constantPool().newLabel();

    ArrayList<CaseLabel> cases = new ArrayList<CaseLabel>();
    if (getExpr().type().isEnumDecl()) {
      enumIndexExpr().createBCode(gen);
      Map<EnumConstant, Integer> enumIndices = hostType().enumIndices(getExpr().type());
      for (ConstCase cc : constCases()) {
        cases.add(new EnumLabel(enumIndices.get((EnumConstant) cc.getValue().varDecl()), cc));
      }
    } else {
      getExpr().createBCode(gen);
      if (getExpr().type().isReferenceType()) {
        getExpr().type().emitUnboxingOperation(gen);
      }
      for (ConstCase cc : constCases()) {
        cases.add(new CaseLabel(cc));
      }
    }

    gen.SWITCH(cases, defaultLabel());

    getBlock().createBCode(gen);

    gen.addLabel(end_label());
  }
  /**
   * Two implicit switch statements are generated for String typed switch statements.
   * The first switch will switch on the hash code of the switch expression.
   * The first switch statement computes a value for a variable that selects
   * a case in the second switch statement.
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:104
   */
    public void createBCode(CodeGeneration gen) {
    if (getExpr().type().isString()) {
      // Add line number for start of statement.
      super.createBCode(gen);

      // Enumerate case labels with same hash value.
      Map<Integer, StringLblGroup> groups = new HashMap<Integer, StringLblGroup>();

      int index = 1;
      for (Stmt stmt : getBlock().getStmts()) {
        if (stmt instanceof ConstCase) {
          ConstCase cc = (ConstCase) stmt;
          StringLabel caseLbl = new StringLabel(index++, cc);
          int key = caseLbl.hashCode;
          StringLblGroup group = groups.get(key);
          if (group == null) {
            group = new StringLblGroup(key, gen.constantPool().newLabel());
            groups.put(key, group);
          }
          group.addCase(caseLbl);
        }
      }

      genFirstSwitch(gen, groups.values());

      getBlock().createBCode(gen);

      gen.addLabel(end_label());
    } else {
      refined_AutoBoxingCodegen_SwitchStmt_createBCode(gen);
    }
  }
  /**
   * @return <code>true</code> if this statement is a potential
   * branch target of the given branch statement.
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:215
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:215")
  public boolean potentialTargetOf(Stmt branch) {
    boolean potentialTargetOf_Stmt_value = branch.canBranchTo(this);
    return potentialTargetOf_Stmt_value;
  }
  /** @apilevel internal */
  private void assignedAfter_Variable_reset() {
    assignedAfter_Variable_values = null;
  }
  protected java.util.Map assignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:264")
  public boolean assignedAfter(Variable v) {
    Object _parameters = v;
    if (assignedAfter_Variable_values == null) assignedAfter_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (assignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = assignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      assignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_assignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_assignedAfter_Variable_value = assignedAfter_compute(v);
        if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_assignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedAfter_Variable_values.put(_parameters, new_assignedAfter_Variable_value);

      state.leaveCircle();
      return new_assignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedAfter_Variable_value = assignedAfter_compute(v);
      if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_assignedAfter_Variable_value;
      }
      return new_assignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private boolean assignedAfter_compute(Variable v) {
      if (!(!noDefaultLabel() || getExpr().assignedAfter(v))) {
        return false;
      }
      if (!(!switchLabelEndsBlock() || getExpr().assignedAfter(v))) {
        return false;
      }
      if (!assignedAfterLastStmt(v)) {
        return false;
      }
      for (BreakStmt stmt : targetBreaks()) {
        if (!stmt.assignedAfterReachedFinallyBlocks(v)) {
          return false;
        }
      }
      return true;
    }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:708
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:708")
  public boolean assignedAfterLastStmt(Variable v) {
    boolean assignedAfterLastStmt_Variable_value = getBlock().assignedAfter(v);
    return assignedAfterLastStmt_Variable_value;
  }
  /** @apilevel internal */
  private void unassignedAfter_Variable_reset() {
    unassignedAfter_Variable_values = null;
  }
  protected java.util.Map unassignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:895")
  public boolean unassignedAfter(Variable v) {
    Object _parameters = v;
    if (unassignedAfter_Variable_values == null) unassignedAfter_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (unassignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      unassignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfter_Variable_value = unassignedAfter_compute(v);
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = unassignedAfter_compute(v);
      if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfter_Variable_value;
      }
      return new_unassignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private boolean unassignedAfter_compute(Variable v) {
      if (!(!noDefaultLabel() || getExpr().unassignedAfter(v))) {
        return false;
      }
      if (!(!switchLabelEndsBlock() || getExpr().unassignedAfter(v))) {
        return false;
      }
      if (!unassignedAfterLastStmt(v)) {
        return false;
      }
      for (BreakStmt stmt : targetBreaks()) {
        if (!stmt.unassignedAfterReachedFinallyBlocks(v)) {
          return false;
        }
      }
      return true;
    }
  /** @apilevel internal */
  private void unassignedAfterLastStmt_Variable_reset() {
    unassignedAfterLastStmt_Variable_values = null;
  }
  protected java.util.Map unassignedAfterLastStmt_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:1378")
  public boolean unassignedAfterLastStmt(Variable v) {
    Object _parameters = v;
    if (unassignedAfterLastStmt_Variable_values == null) unassignedAfterLastStmt_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (unassignedAfterLastStmt_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfterLastStmt_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      unassignedAfterLastStmt_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfterLastStmt_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfterLastStmt_Variable_value = getBlock().unassignedAfter(v);
        if (((Boolean)_value.value) != new_unassignedAfterLastStmt_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfterLastStmt_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfterLastStmt_Variable_values.put(_parameters, new_unassignedAfterLastStmt_Variable_value);

      state.leaveCircle();
      return new_unassignedAfterLastStmt_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfterLastStmt_Variable_value = getBlock().unassignedAfter(v);
      if (((Boolean)_value.value) != new_unassignedAfterLastStmt_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfterLastStmt_Variable_value;
      }
      return new_unassignedAfterLastStmt_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect DefiniteUnassignment
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:1381
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:1381")
  public boolean switchLabelEndsBlock() {
    boolean switchLabelEndsBlock_value = getBlock().getNumStmt() > 0
          && getBlock().getStmt(getBlock().getNumStmt()-1) instanceof ConstCase;
    return switchLabelEndsBlock_value;
  }
  /** @apilevel internal */
  private void caseMap_reset() {
    caseMap_computed = null;
    caseMap_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle caseMap_computed = null;

  /** @apilevel internal */
  protected Map<Object, Case> caseMap_value;

  /**
   * Maps constant values to case labels.
   * 
   * <p>This is used to accelerate duplicate statement label checking.
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NameCheck.jrag:637
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NameCheck.jrag:637")
  public Map<Object, Case> caseMap() {
    ASTState state = state();
    if (caseMap_computed == ASTState.NON_CYCLE || caseMap_computed == state().cycle()) {
      return caseMap_value;
    }
    caseMap_value = caseMap_compute();
    if (state().inCircle()) {
      caseMap_computed = state().cycle();
    
    } else {
      caseMap_computed = ASTState.NON_CYCLE;
    
    }
    return caseMap_value;
  }
  /** @apilevel internal */
  private Map<Object, Case> caseMap_compute() {
      Map<Object, Case> map = new HashMap<Object, Case>();
      for (Stmt stmt : getBlock().getStmtList()) {
        if (stmt instanceof Case) {
          if (stmt instanceof ConstCase) {
            ConstCase cc = (ConstCase) stmt;
            if (cc.getValue().isConstant()) {
              if (cc.getValue().type().assignableToInt()) {
                Constant v = cc.getValue().constant();
                if (!map.containsKey(v.intValue())) {
                  map.put(v.intValue(), cc);
                }
              }
            }
          }
        }
      }
      return map;
    }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeCheck.jrag:459
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeCheck.jrag:459")
  public Collection<Problem> typeProblems() {
    {
        TypeDecl type = getExpr().type();
        if ((!type.isIntegralType() || type.isLong()) && !type.isEnumDecl() && !type.isString()) {
          return Collections.singletonList(
              error("Switch expression must be of type char, byte, short, int, enum, or string"));
        }
        return Collections.emptyList();
      }
  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:96
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:96")
  public boolean lastStmtCanCompleteNormally() {
    boolean lastStmtCanCompleteNormally_value = getBlock().canCompleteNormally();
    return lastStmtCanCompleteNormally_value;
  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:98
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:98")
  public boolean noStmts() {
    {
        for (int i = 0; i < getBlock().getNumStmt(); i++) {
          if (!(getBlock().getStmt(i) instanceof Case)) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:107
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:107")
  public boolean noStmtsAfterLastLabel() {
    boolean noStmtsAfterLastLabel_value = getBlock().getNumStmt() > 0
          && getBlock().getStmt(getBlock().getNumStmt()-1) instanceof Case;
    return noStmtsAfterLastLabel_value;
  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:111
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:111")
  public boolean noDefaultLabel() {
    {
        for (int i = 0; i < getBlock().getNumStmt(); i++) {
          if (getBlock().getStmt(i) instanceof DefaultCase) {
            return false;
          }
        }
        return true;
      }
  }
  /** @apilevel internal */
  private void canCompleteNormally_reset() {
    canCompleteNormally_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle canCompleteNormally_computed = null;

  /** @apilevel internal */
  protected boolean canCompleteNormally_value;

  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:50")
  public boolean canCompleteNormally() {
    ASTState state = state();
    if (canCompleteNormally_computed == ASTState.NON_CYCLE || canCompleteNormally_computed == state().cycle()) {
      return canCompleteNormally_value;
    }
    canCompleteNormally_value = lastStmtCanCompleteNormally() || noStmts()
          || noStmtsAfterLastLabel()
          || noDefaultLabel() || reachableBreak();
    if (state().inCircle()) {
      canCompleteNormally_computed = state().cycle();
    
    } else {
      canCompleteNormally_computed = ASTState.NON_CYCLE;
    
    }
    return canCompleteNormally_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/frontend/PreciseRethrow.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/frontend/PreciseRethrow.jrag:78")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getBlock().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /** @apilevel internal */
  private void defaultCase_reset() {
    defaultCase_computed = null;
    defaultCase_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle defaultCase_computed = null;

  /** @apilevel internal */
  protected DefaultCase defaultCase_value;

  /**
   * @return a reference to the default case lable, or {@code null}
   * if there is no default case.
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1559
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1559")
  public DefaultCase defaultCase() {
    ASTState state = state();
    if (defaultCase_computed == ASTState.NON_CYCLE || defaultCase_computed == state().cycle()) {
      return defaultCase_value;
    }
    defaultCase_value = defaultCase_compute();
    if (state().inCircle()) {
      defaultCase_computed = state().cycle();
    
    } else {
      defaultCase_computed = ASTState.NON_CYCLE;
    
    }
    return defaultCase_value;
  }
  /** @apilevel internal */
  private DefaultCase defaultCase_compute() {
      for (int i= 0; i < getBlock().getNumStmt(); i++) {
        if (getBlock().getStmt(i) instanceof DefaultCase) {
          return (DefaultCase) getBlock().getStmt(i);
        }
      }
      return null;
    }
  /** @apilevel internal */
  private void end_label_reset() {
    end_label_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle end_label_computed = null;

  /** @apilevel internal */
  protected int end_label_value;

  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1568
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1568")
  public int end_label() {
    ASTState state = state();
    if (end_label_computed == ASTState.NON_CYCLE || end_label_computed == state().cycle()) {
      return end_label_value;
    }
    end_label_value = hostType().constantPool().newLabel();
    if (state().inCircle()) {
      end_label_computed = state().cycle();
    
    } else {
      end_label_computed = ASTState.NON_CYCLE;
    
    }
    return end_label_value;
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1590
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1590")
  public int numCase() {
    {
        int result = 0;
        for (int i = 0; i < getBlock().getNumStmt(); i++) {
          if (getBlock().getStmt(i) instanceof Case) {
            result++;
          }
        }
        return result;
      }
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1706
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1706")
  public int break_label() {
    int break_label_value = end_label();
    return break_label_value;
  }
  /** @apilevel internal */
  private void enumIndexExpr_reset() {
    enumIndexExpr_computed = false;
    
    enumIndexExpr_value = null;
  }
  /** @apilevel internal */
  protected boolean enumIndexExpr_computed = false;

  /** @apilevel internal */
  protected Expr enumIndexExpr_value;

  /**
   * This is the expression used during code generation to access the enum index field
   * and compute the jump target.
   * @attribute syn
   * @aspect EnumsCodegen
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/backend/EnumsCodegen.jrag:141
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="EnumsCodegen", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/backend/EnumsCodegen.jrag:141")
  public Expr enumIndexExpr() {
    ASTState state = state();
    if (enumIndexExpr_computed) {
      return enumIndexExpr_value;
    }
    state().enterLazyAttribute();
    enumIndexExpr_value = hostType().createEnumMethod(getExpr().type())
              .createBoundAccess(new List())
              .qualifiesAccess(
                  new ArrayAccess(((Expr) getExpr().treeCopyNoTransform())
                      .qualifiesAccess(new MethodAccess("ordinal", new List()))));
    enumIndexExpr_value.setParent(this);
    enumIndexExpr_computed = true;
    state().leaveLazyAttribute();
    return enumIndexExpr_value;
  }
  /** @return a collection of the constant cases in this switch statement. 
   * @attribute syn
   * @aspect EnumsCodegen
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/backend/EnumsCodegen.jrag:149
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="EnumsCodegen", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/backend/EnumsCodegen.jrag:149")
  public Collection<ConstCase> constCases() {
    {
        Collection<ConstCase> cases = new LinkedList<ConstCase>();
        for (Stmt stmt : getBlock().getStmtList()) {
          if (stmt instanceof ConstCase) {
            cases.add((ConstCase) stmt);
          }
        }
        return cases;
      }
  }
  /**
   * @attribute syn
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StringsInSwitch", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:42")
  public boolean isSwitchWithString() {
    boolean isSwitchWithString_value = getExpr().type().isString();
    return isSwitchWithString_value;
  }
  /**
   * Local index for the first switch variable.
   * @attribute syn
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StringsInSwitch", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:56")
  public int localNumA() {
    int localNumA_value = localNum();
    return localNumA_value;
  }
  /**
   * Local index for the second switch variable.
   * @attribute syn
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:61
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StringsInSwitch", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:61")
  public int localNumB() {
    int localNumB_value = localNum() + typeInt().variableSize();
    return localNumB_value;
  }
  /**
   * Utility method to compute offsets between labels.
   * @attribute syn
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StringsInSwitch", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:95")
  public int labelOffset(CodeGeneration gen, int lbl1, int lbl2) {
    int labelOffset_CodeGeneration_int_int_value = gen.addressOf(lbl1) - gen.addressOf(lbl2);
    return labelOffset_CodeGeneration_int_int_value;
  }
  /**
   * @attribute inh
   * @aspect SpecialClasses
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupType.jrag:90
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupType.jrag:90")
  public TypeDecl typeInt() {
    ASTState state = state();
    if (typeInt_computed == ASTState.NON_CYCLE || typeInt_computed == state().cycle()) {
      return typeInt_value;
    }
    typeInt_value = getParent().Define_typeInt(this, null);
    if (state().inCircle()) {
      typeInt_computed = state().cycle();
    
    } else {
      typeInt_computed = ASTState.NON_CYCLE;
    
    }
    return typeInt_value;
  }
  /** @apilevel internal */
  private void typeInt_reset() {
    typeInt_computed = null;
    typeInt_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeInt_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeInt_value;

  /**
   * @attribute inh
   * @aspect SpecialClasses
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupType.jrag:92
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupType.jrag:92")
  public TypeDecl typeLong() {
    ASTState state = state();
    if (typeLong_computed == ASTState.NON_CYCLE || typeLong_computed == state().cycle()) {
      return typeLong_value;
    }
    typeLong_value = getParent().Define_typeLong(this, null);
    if (state().inCircle()) {
      typeLong_computed = state().cycle();
    
    } else {
      typeLong_computed = ASTState.NON_CYCLE;
    
    }
    return typeLong_value;
  }
  /** @apilevel internal */
  private void typeLong_reset() {
    typeLong_computed = null;
    typeLong_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeLong_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeLong_value;

  /**
   * @attribute inh
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:45
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="StringsInSwitch", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/StringsInSwitch.jrag:45")
  public TypeDecl typeString() {
    TypeDecl typeString_value = getParent().Define_typeString(this, null);
    return typeString_value;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:230
   * @apilevel internal
   */
  public Stmt Define_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return branch.canBranchTo(this) ? this : branchTarget(branch);
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:230
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute branchTarget
   */
  protected boolean canDefine_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:729
      return getExpr().assignedAfter(v);
    }
    else if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:711
      {
          if (((ASTNode) v).isDescendantTo(this)) {
            return false;
          }
          boolean result = assignedBefore(v);
          return result;
        }
    }
    else {
      return getParent().Define_assignedBefore(this, _callerNode, v);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignedBefore
   */
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:1388
      return getExpr().unassignedAfter(v);
    }
    else if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:1386
      return unassignedBefore(v);
    }
    else {
      return getParent().Define_unassignedBefore(this, _callerNode, v);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unassignedBefore
   */
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NameCheck.jrag:531
   * @apilevel internal
   */
  public boolean Define_insideSwitch(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NameCheck.jrag:534
      return true;
    }
    else {
      return getParent().Define_insideSwitch(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NameCheck.jrag:531
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute insideSwitch
   */
  protected boolean canDefine_insideSwitch(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NameCheck.jrag:592
   * @apilevel internal
   */
  public Case Define_previousCase(ASTNode _callerNode, ASTNode _childNode, Case c) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NameCheck.jrag:594
      {
          if (c instanceof ConstCase) {
            ConstCase cc = (ConstCase) c;
            if (cc.getValue().isConstant()) {
              if (cc.getValue().type().assignableToInt()) {
                return caseMap().get(cc.getValue().constant().intValue());
              }
            } else {
              // The label does not have a constant value, so we won't be able to
              // find a duplicate.
              return c;
            }
          } else if (c instanceof DefaultCase) {
            // Default case label.
            for (Stmt stmt : getBlock().getStmtList()) {
              if (stmt instanceof DefaultCase) {
                return (Case) stmt;
              }
            }
          }
          // Fall back on comparing against all case labels.
          for (Stmt stmt : getBlock().getStmtList()) {
            if (stmt instanceof Case && ((Case) stmt).constValue(c)) {
              return (Case) stmt;
            }
            if (stmt == c) {
              return c;
            }
          }
          // This should not happen, since the Case label that evaluates the
          // attribute must be directly nested in this switch statement.
          throw new Error("Case label not found in switch statement list.");
        }
    }
    else {
      return getParent().Define_previousCase(this, _callerNode, c);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NameCheck.jrag:592
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute previousCase
   */
  protected boolean canDefine_previousCase(ASTNode _callerNode, ASTNode _childNode, Case c) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeCheck.jrag:482
   * @apilevel internal
   */
  public TypeDecl Define_switchType(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeCheck.jrag:483
      return getExpr().type();
    }
    else {
      return getParent().Define_switchType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeCheck.jrag:482
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute switchType
   */
  protected boolean canDefine_switchType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:125
      return reachable();
    }
    else {
      return getParent().Define_reachable(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute reachable
   */
  protected boolean canDefine_reachable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/frontend/PreciseRethrow.jrag:280
   * @apilevel internal
   */
  public boolean Define_reportUnreachable(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:218
      return reachable();
    }
    else {
      return getParent().Define_reportUnreachable(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/frontend/PreciseRethrow.jrag:280
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute reportUnreachable
   */
  protected boolean canDefine_reportUnreachable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:53
   * @apilevel internal
   */
  public int Define_localNum(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return localNum() + typeInt().variableSize() + typeString().variableSize();
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:53
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute localNum
   */
  protected boolean canDefine_localNum(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }
  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeCheck.jrag:457
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_TypeDecl_enumSwitchStatements(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/backend/EnumsCodegen.jrag:132
    if (getExpr().type().isEnumDecl()) {
      {
        TypeDecl target = (TypeDecl) (hostType());
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_TypeDecl_enumSwitchStatements(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_TypeDecl_enumSwitchStatements(LinkedList<SwitchStmt> collection) {
    super.contributeTo_TypeDecl_enumSwitchStatements(collection);
    if (getExpr().type().isEnumDecl()) {
      collection.add(this);
    }
  }
}
