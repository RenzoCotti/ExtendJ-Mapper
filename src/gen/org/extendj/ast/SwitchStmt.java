/* This file was generated with JastAdd2 (http://jastadd.org) version 2.2.2 */
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
import java.util.zip.*;
import java.io.*;
import org.jastadd.util.*;
import org.jastadd.util.PrettyPrintable;
import org.jastadd.util.PrettyPrinter;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
/**
 * @ast node
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/grammar/Java.ast:200
 * @production SwitchStmt : {@link BranchTargetStmt} ::= <span class="component">{@link Expr}</span> <span class="component">{@link Block}</span>;

 */
public class SwitchStmt extends BranchTargetStmt implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrint.jadd:573
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("switch (");
    out.print(getExpr());
    out.print(") ");
    out.print(getBlock());
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1715
   */
  private int emitPad(CodeGeneration gen) {
    int pad = (4 - (gen.pos() % 4)) % 4;
    for (int i = 0; i < pad; i++) {
      gen.emit(this, Bytecode.NOP);
    }
    if (gen.pos() % 4 != 0) {
      throw new Error("Switch not at 4-byte boundary:" + gen.pos());
    }
    return pad;
  }
  /**
   * Calculate offset to the default label.
   * @return bytecode offset to default label (or zero if there is no
   * default label)
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1731
   */
  private int defaultOffset(CodeGeneration gen, int switch_label) {
    DefaultCase defaultCase = defaultCase();
    if (defaultCase != null) {
      int offset = gen.addressOf(defaultCase.label()) - gen.addressOf(switch_label);
      return offset;
    }
    return 0;
  }
  /**
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:166
   */
  private void genFirstSwitch(
      CodeGeneration gen,
      TreeMap<Integer, CaseGroup> groups,
      int index_a) {
    int switch_label = gen.constantPool().newLabel();
    int end_label1 = gen.constantPool().newLabel();
    int index_b = localNumB();

    // Initialize switch variable for second switch.
    IntegerLiteral.push(this, gen, 0);
    typeInt().emitStoreLocal(this, gen, index_a);

    // Store the value of the switch expr so that it is only evaluated once!
    getExpr().createBCode(gen);

    // Push the hash code for the switch instruction.
    if (getExpr().isConstant()) {
      typeString().emitStoreLocal(this, gen, index_b);

      int hashCode = getExpr().constant().stringValue().hashCode();
      IntegerLiteral.push(this, gen, hashCode);
    } else {
      typeString().emitDup(this, gen);
      typeString().emitStoreLocal(this, gen, index_b);
      hashCodeMethod().emitInvokeMethod(this, gen,
          lookupType("java.lang", "Object"));
    }

    // Emit switch instruction.
    int low = groups.isEmpty() ? 0 : groups.firstKey();
    int high = groups.isEmpty() ? 0 : groups.lastKey();

    long tableSwitchSize = 4L * (3L + (high - low + 1L));
    int lookupSwitchSize = 4 * (2 + 2 * groups.size());
    int pad;
    int switchSize;
    int switchPos;
    boolean tableSwitch = tableSwitchSize < lookupSwitchSize;

    gen.addLabel(switch_label);

    // Select the switch type which produces the smallest switch instruction.
    if (tableSwitch) {
      // Use TABLESWITCH.
      gen.emit(this, Bytecode.TABLESWITCH);
      switchSize = (int) tableSwitchSize;
    } else {
      // Use LOOKUPSWITCH.
      gen.emit(this, Bytecode.LOOKUPSWITCH);
      switchSize = lookupSwitchSize;
    }

    pad = emitPad(gen);
    switchPos = gen.pos();

    // Leave room for the address table.
    gen.skip(switchSize);

    // Code generation for switch body.
    for (CaseGroup group : groups.values()) {
      gen.addLabel(group.lbl);

      // Possible hash miss. Check for equality.
      Iterator<CaseLbl> iter = group.lbls.iterator();
      while (iter.hasNext()) {
        CaseLbl lbl = iter.next();
        int thenLbl;
        if (iter.hasNext()) {
          thenLbl = gen.constantPool().newLabel();
        } else {
          // The last conditional branches to end label.
          thenLbl = end_label1;
        }

        typeString().emitLoadLocal(this, gen, index_b);
        StringLiteral.push(this, gen, lbl.value);
        equalsMethod().emitInvokeMethod(this, gen,
            lookupType("java.lang", "Object"));
        gen.emitCompare(this, Bytecode.IFEQ, thenLbl);
        IntegerLiteral.push(this, gen, lbl.serial);
        typeInt().emitStoreLocal(this, gen, index_a);
        gen.emitGoto(this, end_label1);

        if (iter.hasNext()) {
          gen.addLabel(thenLbl);
        }
      }
    }

    // Write jump address table.
    int endpos = gen.pos();
    gen.setPos(switchPos);
    if (tableSwitch) {
      int defaultOffset = 1 + pad + switchSize;
      gen.add4(defaultOffset);
      gen.add4(low);
      gen.add4(high);
      for (int i = low; i <= high; i++) {
        if (groups.containsKey(i)) {
          CaseGroup group = groups.get(i);
          int offset = labelOffset(gen, group.lbl, switch_label);
          gen.add4(offset);
        } else {
          gen.add4(defaultOffset);
        }
      }
    } else {
      int defaultOffset = 1 + pad + switchSize;
      gen.add4(defaultOffset);
      gen.add4(groups.size());
      for (CaseGroup group : groups.values()) {
        gen.add4(group.hashCode);
        int offset = labelOffset(gen, group.lbl, switch_label);
        gen.add4(offset);
      }
    }
    gen.setPos(endpos);

    gen.addLabel(end_label1);
  }
  /**
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:287
   */
  private void genSecondSwitch(
      CodeGeneration gen,
      java.util.List<CaseLbl> labels,
      int index_a,
      CaseLbl defaultLbl) {
    int switch_label = gen.constantPool().newLabel();
    int default_label = gen.constantPool().newLabel();

    // Push the switch variable.
    typeInt().emitLoadLocal(this, gen, index_a);

    // Emit switch instruction.
    gen.addLabel(switch_label);
    gen.emit(this, Bytecode.TABLESWITCH);
    int high = labels.size();
    int low = 0;
    int pad;
    int switchSize = 4 * (3 + (high - low + 1));
    int switchPos;

    pad = emitPad(gen);
    switchPos = gen.pos();
    gen.skip(switchSize);

    // Code generation for case labels.
    for (CaseLbl lbl : labels) {
      gen.addLabel(lbl.lbl);
      lbl.createBCode(gen);
    }

    gen.addLabel(default_label);
    if (defaultLbl != null) {
      defaultLbl.createBCode(gen);
    }

    int endpos = gen.pos();
    gen.setPos(switchPos);
    int defaultOffset = 1 + pad + switchSize;
    gen.add4(defaultOffset);
    gen.add4(low);
    gen.add4(high);
    int offset = labelOffset(gen, default_label, switch_label);
    gen.add4(offset);
    for (CaseLbl lbl : labels) {
      offset = labelOffset(gen, lbl.lbl, switch_label);
      gen.add4(offset);
    }
    gen.setPos(endpos);

    gen.addLabel(end_label());
  }
  /**
   * Generate invocation of method
   * {@code java.lang.Object.hashCode()}.
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:343
   */
  private MethodDecl hashCodeMethod() {
    TypeDecl objectType = lookupType("java.lang", "Object");
    if (objectType.isUnknown()) {
      throw new Error("Could not find java.lang.Object");
    }
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:361
   */
  private MethodDecl equalsMethod() {
    TypeDecl objectType = lookupType("java.lang", "Object");
    if (objectType.isUnknown()) {
      throw new Error("Could not find java.lang.Object");
    }
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
  public SwitchStmt(Expr p0, Block p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:18
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:24
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:28
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    canCompleteNormally_reset();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    unassignedAfterLastStmt_Variable_reset();
    defaultCase_reset();
    end_label_reset();
    enumIndexExpr_reset();
    enumIndices_reset();
    typeInt_reset();
    typeLong_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:42
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:46
   */
  public SwitchStmt clone() throws CloneNotSupportedException {
    SwitchStmt node = (SwitchStmt) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
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
   * @declaredat ASTNode:70
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
   * @declaredat ASTNode:80
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
   * @declaredat ASTNode:100
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
   * @declaredat ASTNode:114
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AutoBoxingCodegen.jrag:193
   */
    public void refined_AutoBoxingCodegen_SwitchStmt_createBCode(CodeGeneration gen) {
    super.createBCode(gen);
    int cond_label = gen.constantPool().newLabel();
    int switch_label = gen.constantPool().newLabel();

    TreeMap<Integer, ConstCase> caseMap = new TreeMap<Integer, ConstCase>();

    gen.emitGoto(this, cond_label);
    getBlock().createBCode(gen);
    if (canCompleteNormally()) {
      gen.emitGoto(this, end_label());
    }
    gen.addLabel(cond_label);
    if (getExpr().type().isEnumDecl()) {
      enumIndexExpr().createBCode(gen);
      for (ConstCase cc : constCases()) {
        caseMap.put(enumIndices().get((EnumConstant) cc.getValue().varDecl()), cc);
      }
    } else {
      getExpr().createBCode(gen);
      if (getExpr().type().isReferenceType()) {
        getExpr().type().emitUnboxingOperation(this, gen);
      }
      for (ConstCase cc : constCases()) {
        caseMap.put(cc.getValue().constant().intValue(), cc);
      }
    }

    long low = caseMap.isEmpty() ? 0 : caseMap.firstKey();
    long high = caseMap.isEmpty() ? 0 : caseMap.lastKey();

    long tableSwitchSize = 8L + (high - low + 1L) * 4L;
    long lookupSwitchSize = 4L + caseMap.size() * 8L;

    gen.addLabel(switch_label);
    if (tableSwitchSize < lookupSwitchSize) {
      gen.emit(this, Bytecode.TABLESWITCH);
      int pad = emitPad(gen);
      int defaultOffset = defaultOffset(gen, switch_label);
      if (defaultOffset == 0) {
        defaultOffset = 1 + pad + 4 + 4 + 4 + 4 * (int)(high - low + 1);
      }
      gen.add4(defaultOffset);
      gen.add4((int) low);
      gen.add4((int) high);
      for (long i = low; i <= high; i++) {
        ConstCase cc = caseMap.get((int) i);
        if (cc != null) {
          int offset = gen.addressOf(cc.label())
            - gen.addressOf(switch_label);
          gen.add4(offset);
        } else {
          gen.add4(defaultOffset);
        }
      }
    } else {
      gen.emit(this, Bytecode.LOOKUPSWITCH);
      int pad = emitPad(gen);
      int defaultOffset = defaultOffset(gen, switch_label);
      if (defaultOffset == 0) {
        defaultOffset = 1 + pad + 4 + 4 + 8 * numCase();
      }
      gen.add4(defaultOffset);
      gen.add4(caseMap.size());
      for (Map.Entry<Integer, ConstCase> entry : caseMap.entrySet()) {
        gen.add4(entry.getKey());
        int offset = gen.addressOf(entry.getValue().label()) - gen.addressOf(switch_label);
        gen.add4(offset);
      }
    }
    gen.addLabel(end_label());
  }
  /**
   * Two implicit switch statements are generated for String typed switch statements.
   * The first switch will switch on the hash code of the switch expression.
   * The first switch statement computes a value for a variable that selects
   * a case in the second switch statement.
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:125
   */
    public void createBCode(CodeGeneration gen) {
    if (getExpr().type().isString()) {
      // Add line number for start of statement.
      super.createBCode(gen);

      // Enumerate case labels with same hash value.
      TreeMap<Integer, CaseGroup> groups = new TreeMap<Integer, CaseGroup>();
      java.util.List<CaseLbl> labels = new LinkedList<CaseLbl>();

      CaseLbl defaultLbl = null;
      CaseLbl caseLbl = null;
      int serial = 1;
      for (Stmt stmt : getBlock().getStmts()) {
        if (stmt instanceof ConstCase) {
          ConstCase cc = (ConstCase) stmt;
          caseLbl = new CaseLbl(cc, gen);
          caseLbl.serial = serial++;
          labels.add(caseLbl);
          int key = caseLbl.value.hashCode();
          if (groups.containsKey(key)) {
            groups.get(key).addCase(caseLbl);
          } else {
            CaseGroup group = new CaseGroup(this, key);
            group.addCase(caseLbl);
            groups.put(key, group);
          }
        } else if (stmt instanceof DefaultCase) {
          defaultLbl = new CaseLbl(hostType().constantPool().newLabel());
          caseLbl = defaultLbl;
        } else if (caseLbl != null) {
          caseLbl.addStmt(stmt);
        }
      }
      int index_a = localNumA();
      genFirstSwitch(gen, groups, index_a);
      genSecondSwitch(gen, labels, index_a, defaultLbl);
    } else {
      refined_AutoBoxingCodegen_SwitchStmt_createBCode(gen);
    }
  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:96
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:96")
  public boolean lastStmtCanCompleteNormally() {
    boolean lastStmtCanCompleteNormally_value = getBlock().canCompleteNormally();
    return lastStmtCanCompleteNormally_value;
  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:98
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:98")
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:107
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:107")
  public boolean noStmtsAfterLastLabel() {
    boolean noStmtsAfterLastLabel_value = getBlock().getNumStmt() > 0
          && getBlock().getStmt(getBlock().getNumStmt()-1) instanceof Case;
    return noStmtsAfterLastLabel_value;
  }
  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:111
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:111")
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
  protected ASTNode$State.Cycle canCompleteNormally_computed = null;

  /** @apilevel internal */
  protected boolean canCompleteNormally_value;

  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:50")
  public boolean canCompleteNormally() {
    ASTNode$State state = state();
    if (canCompleteNormally_computed == ASTNode$State.NON_CYCLE || canCompleteNormally_computed == state().cycle()) {
      return canCompleteNormally_value;
    }
    canCompleteNormally_value = lastStmtCanCompleteNormally() || noStmts()
          || noStmtsAfterLastLabel()
          || noDefaultLabel() || reachableBreak();
    if (state().inCircle()) {
      canCompleteNormally_computed = state().cycle();
    
    } else {
      canCompleteNormally_computed = ASTNode$State.NON_CYCLE;
    
    }
    return canCompleteNormally_value;
  }
  /** @apilevel internal */
  private void assignedAfter_Variable_reset() {
    assignedAfter_Variable_values = null;
  }
  protected java.util.Map assignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:264")
  public boolean assignedAfter(Variable v) {
    Object _parameters = v;
    if (assignedAfter_Variable_values == null) assignedAfter_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (assignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = assignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      assignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_assignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_assignedAfter_Variable_value = assignedAfter_compute(v);
        if (new_assignedAfter_Variable_value != ((Boolean)_value.value)) {
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
      if (new_assignedAfter_Variable_value != ((Boolean)_value.value)) {
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:712
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:712")
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
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:899")
  public boolean unassignedAfter(Variable v) {
    Object _parameters = v;
    if (unassignedAfter_Variable_values == null) unassignedAfter_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (unassignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      unassignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfter_Variable_value = unassignedAfter_compute(v);
        if (new_unassignedAfter_Variable_value != ((Boolean)_value.value)) {
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
      if (new_unassignedAfter_Variable_value != ((Boolean)_value.value)) {
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
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1381")
  public boolean unassignedAfterLastStmt(Variable v) {
    Object _parameters = v;
    if (unassignedAfterLastStmt_Variable_values == null) unassignedAfterLastStmt_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (unassignedAfterLastStmt_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfterLastStmt_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      unassignedAfterLastStmt_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfterLastStmt_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfterLastStmt_Variable_value = getBlock().unassignedAfter(v);
        if (new_unassignedAfterLastStmt_Variable_value != ((Boolean)_value.value)) {
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
      if (new_unassignedAfterLastStmt_Variable_value != ((Boolean)_value.value)) {
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1384
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1384")
  public boolean switchLabelEndsBlock() {
    boolean switchLabelEndsBlock_value = getBlock().getNumStmt() > 0
          && getBlock().getStmt(getBlock().getNumStmt()-1) instanceof ConstCase;
    return switchLabelEndsBlock_value;
  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:459
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:459")
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
   * @return <code>true</code> if this statement is a potential
   * branch target of the given branch statement.
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:215
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:215")
  public boolean potentialTargetOf(Stmt branch) {
    boolean potentialTargetOf_Stmt_value = branch.canBranchTo(this);
    return potentialTargetOf_Stmt_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:78")
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
  protected ASTNode$State.Cycle defaultCase_computed = null;

  /** @apilevel internal */
  protected DefaultCase defaultCase_value;

  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1609
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1609")
  public DefaultCase defaultCase() {
    ASTNode$State state = state();
    if (defaultCase_computed == ASTNode$State.NON_CYCLE || defaultCase_computed == state().cycle()) {
      return defaultCase_value;
    }
    defaultCase_value = defaultCase_compute();
    if (state().inCircle()) {
      defaultCase_computed = state().cycle();
    
    } else {
      defaultCase_computed = ASTNode$State.NON_CYCLE;
    
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
  protected ASTNode$State.Cycle end_label_computed = null;

  /** @apilevel internal */
  protected int end_label_value;

  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1618
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1618")
  public int end_label() {
    ASTNode$State state = state();
    if (end_label_computed == ASTNode$State.NON_CYCLE || end_label_computed == state().cycle()) {
      return end_label_value;
    }
    end_label_value = hostType().constantPool().newLabel();
    if (state().inCircle()) {
      end_label_computed = state().cycle();
    
    } else {
      end_label_computed = ASTNode$State.NON_CYCLE;
    
    }
    return end_label_value;
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1705
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1705")
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1858
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1858")
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/EnumsCodegen.jrag:126
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="EnumsCodegen", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/EnumsCodegen.jrag:126")
  public Expr enumIndexExpr() {
    ASTNode$State state = state();
    if (enumIndexExpr_computed) {
      return enumIndexExpr_value;
    }
    state().enterLazyAttribute();
    enumIndexExpr_value = hostType().createEnumMethod(this).createBoundAccess(new List())
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/EnumsCodegen.jrag:133
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="EnumsCodegen", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/EnumsCodegen.jrag:133")
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
  /** @apilevel internal */
  private void enumIndices_reset() {
    enumIndices_computed = null;
    enumIndices_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle enumIndices_computed = null;

  /** @apilevel internal */
  protected Map<EnumConstant, Integer> enumIndices_value;

  /**
   * Should only be evaluated on switch statements that have enum constants as
   * the switch cases.
   * @attribute syn
   * @aspect EnumsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/EnumsCodegen.jrag:147
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="EnumsCodegen", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/EnumsCodegen.jrag:147")
  public Map<EnumConstant, Integer> enumIndices() {
    ASTNode$State state = state();
    if (enumIndices_computed == ASTNode$State.NON_CYCLE || enumIndices_computed == state().cycle()) {
      return enumIndices_value;
    }
    enumIndices_value = enumIndices_compute();
    if (state().inCircle()) {
      enumIndices_computed = state().cycle();
    
    } else {
      enumIndices_computed = ASTNode$State.NON_CYCLE;
    
    }
    return enumIndices_value;
  }
  /** @apilevel internal */
  private Map<EnumConstant, Integer> enumIndices_compute() {
      Map<EnumConstant, Integer> indexMap = new HashMap<EnumConstant, Integer>();
      int next = 1;
      for (ConstCase cc : constCases()) {
        indexMap.put((EnumConstant) cc.getValue().varDecl(), next);
        next += 1;
      }
      return indexMap;
    }
  /**
   * @attribute syn
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StringsInSwitch", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:42")
  public boolean isSwitchWithString() {
    boolean isSwitchWithString_value = getExpr().type().isString();
    return isSwitchWithString_value;
  }
  /**
   * Local index for the first switch variable.
   * @attribute syn
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StringsInSwitch", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:56")
  public int localNumA() {
    int localNumA_value = localNum();
    return localNumA_value;
  }
  /**
   * Local index for the second switch variable.
   * @attribute syn
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:61
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StringsInSwitch", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:61")
  public int localNumB() {
    int localNumB_value = localNum() + typeInt().variableSize();
    return localNumB_value;
  }
  /**
   * Utility method to compute offsets between labels.
   * @attribute syn
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:116
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StringsInSwitch", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:116")
  public int labelOffset(CodeGeneration gen, int lbl1, int lbl2) {
    int labelOffset_CodeGeneration_int_int_value = gen.addressOf(lbl1) - gen.addressOf(lbl2);
    return labelOffset_CodeGeneration_int_int_value;
  }
  /**
   * @attribute inh
   * @aspect SpecialClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:86
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:86")
  public TypeDecl typeInt() {
    ASTNode$State state = state();
    if (typeInt_computed == ASTNode$State.NON_CYCLE || typeInt_computed == state().cycle()) {
      return typeInt_value;
    }
    typeInt_value = getParent().Define_typeInt(this, null);
    if (state().inCircle()) {
      typeInt_computed = state().cycle();
    
    } else {
      typeInt_computed = ASTNode$State.NON_CYCLE;
    
    }
    return typeInt_value;
  }
  /** @apilevel internal */
  private void typeInt_reset() {
    typeInt_computed = null;
    typeInt_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle typeInt_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeInt_value;

  /**
   * @attribute inh
   * @aspect SpecialClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:88
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:88")
  public TypeDecl typeLong() {
    ASTNode$State state = state();
    if (typeLong_computed == ASTNode$State.NON_CYCLE || typeLong_computed == state().cycle()) {
      return typeLong_value;
    }
    typeLong_value = getParent().Define_typeLong(this, null);
    if (state().inCircle()) {
      typeLong_computed = state().cycle();
    
    } else {
      typeLong_computed = ASTNode$State.NON_CYCLE;
    
    }
    return typeLong_value;
  }
  /** @apilevel internal */
  private void typeLong_reset() {
    typeLong_computed = null;
    typeLong_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle typeLong_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeLong_value;

  /**
   * @attribute inh
   * @aspect StringsInSwitch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:45
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="StringsInSwitch", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:45")
  public TypeDecl typeString() {
    TypeDecl typeString_value = getParent().Define_typeString(this, null);
    return typeString_value;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:125
      return reachable();
    }
    else {
      return getParent().Define_reachable(this, _callerNode);
    }
  }
  protected boolean canDefine_reachable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:280
   * @apilevel internal
   */
  public boolean Define_reportUnreachable(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:218
      return reachable();
    }
    else {
      return getParent().Define_reportUnreachable(this, _callerNode);
    }
  }
  protected boolean canDefine_reportUnreachable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:733
      return getExpr().assignedAfter(v);
    }
    else if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:715
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
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:891
   * @apilevel internal
   */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1391
      return getExpr().unassignedAfter(v);
    }
    else if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1389
      return unassignedBefore(v);
    }
    else {
      return getParent().Define_unassignedBefore(this, _callerNode, v);
    }
  }
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:482
   * @apilevel internal
   */
  public TypeDecl Define_switchType(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:483
      return getExpr().type();
    }
    else {
      return getParent().Define_switchType(this, _callerNode);
    }
  }
  protected boolean canDefine_switchType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:512
   * @apilevel internal
   */
  public boolean Define_insideSwitch(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:515
      return true;
    }
    else {
      return getParent().Define_insideSwitch(this, _callerNode);
    }
  }
  protected boolean canDefine_insideSwitch(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:569
   * @apilevel internal
   */
  public Case Define_bind(ASTNode _callerNode, ASTNode _childNode, Case c) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:571
      {
          Block b = getBlock();
          for (int i = 0; i < b.getNumStmt(); i++) {
            if (b.getStmt(i) instanceof Case && ((Case) b.getStmt(i)).constValue(c)) {
              return (Case) b.getStmt(i);
            }
          }
          return null;
        }
    }
    else {
      return getParent().Define_bind(this, _callerNode, c);
    }
  }
  protected boolean canDefine_bind(ASTNode _callerNode, ASTNode _childNode, Case c) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:230
   * @apilevel internal
   */
  public Stmt Define_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return branch.canBranchTo(this) ? this : branchTarget(branch);
  }
  protected boolean canDefine_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:64
   * @apilevel internal
   */
  public int Define_localNum(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return localNum() + typeInt().variableSize() + typeString().variableSize();
  }
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
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:457
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
  protected void collect_contributors_TypeDecl_enumSwitchStatements(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/EnumsCodegen.jrag:117
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
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
  }
  protected void contributeTo_TypeDecl_enumSwitchStatements(LinkedList<SwitchStmt> collection) {
    super.contributeTo_TypeDecl_enumSwitchStatements(collection);
    if (getExpr().type().isEnumDecl()) {
      collection.add(this);
    }
  }
}
