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
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/grammar/Java.ast:284
 * @astdecl Stmt : ASTNode;
 * @production Stmt : {@link ASTNode};

 */
public abstract class Stmt extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @return An Opt node containing the finally and monitor exit blocks
   * from the list of enclosing try-statements and synchronized blocks.
   * @aspect NTAFinally
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NTAFinally.jrag:73
   */
  public Opt<Block> branchFinallyOpt() {
    FinallyHost enclosing = enclosingFinally(this);
    if (enclosing != null) {
      return new Opt<Block>(ntaFinallyBlock(enclosing, this, enclosing.getFinallyBlock()));
    } else {
      return new Opt<Block>();
    }
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1514
   */
  public void createBCode(CodeGeneration gen) {
    gen.addLineNumberEntryAtCurrentPC(this);
		gen.addPositionEntryAtCurrentPC(this);
  }
  /**
   * @declaredat ASTNode:1
   */
  public Stmt() {
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
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:13
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:19
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:23
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    finallyIterator_reset();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    canCompleteNormally_reset();
    localSize_reset();
    enclosingFinally_Stmt_reset();
    localNum_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:34
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public Stmt clone() throws CloneNotSupportedException {
    Stmt node = (Stmt) super.clone();
    return node;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:49
   */
  @Deprecated
  public abstract Stmt fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:57
   */
  public abstract Stmt treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:65
   */
  public abstract Stmt treeCopy();
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/frontend/PreciseRethrow.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/frontend/PreciseRethrow.jrag:78")
  public abstract boolean modifiedInScope(Variable var);
  /** @apilevel internal */
  private void finallyIterator_reset() {
    finallyIterator_computed = null;
    finallyIterator_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle finallyIterator_computed = null;

  /** @apilevel internal */
  protected Iterator<FinallyHost> finallyIterator_value;

  /**
   * Finds enclosing finally and monitor exit blocks.
   * @return an iterator for finally (and monitor exit) blocks that are
   * reached before the final target of this statement is reached
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:80
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:80")
  public Iterator<FinallyHost> finallyIterator() {
    ASTState state = state();
    if (finallyIterator_computed == ASTState.NON_CYCLE || finallyIterator_computed == state().cycle()) {
      return finallyIterator_value;
    }
    finallyIterator_value = finallyIterator_compute();
    if (state().inCircle()) {
      finallyIterator_computed = state().cycle();
    
    } else {
      finallyIterator_computed = ASTState.NON_CYCLE;
    
    }
    return finallyIterator_value;
  }
  /** @apilevel internal */
  private Iterator<FinallyHost> finallyIterator_compute() {
      return new LazyFinallyIterator(this);
    }
  /**
   * @return <code>true</code> if this statement can branch to
   * the target statement.
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:182
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:182")
  public boolean canBranchTo(BranchTargetStmt target) {
    boolean canBranchTo_BranchTargetStmt_value = false;
    return canBranchTo_BranchTargetStmt_value;
  }
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:184
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:184")
  public boolean canBranchTo(LabeledStmt target) {
    boolean canBranchTo_LabeledStmt_value = false;
    return canBranchTo_LabeledStmt_value;
  }
  /**
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:186
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:186")
  public boolean canBranchTo(SwitchStmt target) {
    boolean canBranchTo_SwitchStmt_value = false;
    return canBranchTo_SwitchStmt_value;
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
        new_assignedAfter_Variable_value = assignedBefore(v);
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
      boolean new_assignedAfter_Variable_value = assignedBefore(v);
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
      throw new Error("unassignedAfter in " + getClass().getName());
    }
  /**
   * @attribute syn
   * @aspect VariableScope
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupVariable.jrag:219
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupVariable.jrag:219")
  public VariableDeclarator variableDeclaration(String name) {
    VariableDeclarator variableDeclaration_String_value = null;
    return variableDeclaration_String_value;
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NameCheck.jrag:567
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NameCheck.jrag:567")
  public boolean continueLabel() {
    boolean continueLabel_value = false;
    return continueLabel_value;
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
    canCompleteNormally_value = true;
    if (state().inCircle()) {
      canCompleteNormally_computed = state().cycle();
    
    } else {
      canCompleteNormally_computed = ASTState.NON_CYCLE;
    
    }
    return canCompleteNormally_value;
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1706
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1706")
  public int break_label() {
    {
        throw new UnsupportedOperationException("Can not break at this statement of type "
            + getClass().getName());
      }
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1731
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1731")
  public int continue_label() {
    {
        throw new UnsupportedOperationException("Can not continue at this statement");
      }
  }
  /** @apilevel internal */
  private void localSize_reset() {
    localSize_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle localSize_computed = null;

  /** @apilevel internal */
  protected int localSize_value;

  /**
   * Computes size required for local variables of this statement.
   * NB: only relevant for variable declaration statements.
   * @return local size for declared variables
   * @attribute syn
   * @aspect LocalNum
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/LocalNum.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LocalNum", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/LocalNum.jrag:38")
  public int localSize() {
    ASTState state = state();
    if (localSize_computed == ASTState.NON_CYCLE || localSize_computed == state().cycle()) {
      return localSize_value;
    }
    localSize_value = 0;
    if (state().inCircle()) {
      localSize_computed = state().cycle();
    
    } else {
      localSize_computed = ASTState.NON_CYCLE;
    
    }
    return localSize_value;
  }
  /**
   * @return the target statement for a break or continue
   * @attribute inh
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:230
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:230")
  public Stmt branchTarget(Stmt branch) {
    Stmt branchTarget_Stmt_value = getParent().Define_branchTarget(this, null, branch);
    return branchTarget_Stmt_value;
  }
  /**
   * Finds the finally block of an enclosing try-statement, or monitor exit
   * block of an enclosing synchronized block.
   * 
   * @param branch the source branch
   * @return a finally block, or <code>null</code> if there is no enclosing
   * try-statement or synchronized block.
   * @attribute inh
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:273
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/BranchTarget.jrag:273")
  public FinallyHost enclosingFinally(Stmt branch) {
    Object _parameters = branch;
    if (enclosingFinally_Stmt_computed == null) enclosingFinally_Stmt_computed = new java.util.HashMap(4);
    if (enclosingFinally_Stmt_values == null) enclosingFinally_Stmt_values = new java.util.HashMap(4);
    ASTState state = state();
    if (enclosingFinally_Stmt_values.containsKey(_parameters)
        && enclosingFinally_Stmt_computed.containsKey(_parameters)
        && (enclosingFinally_Stmt_computed.get(_parameters) == ASTState.NON_CYCLE || enclosingFinally_Stmt_computed.get(_parameters) == state().cycle())) {
      return (FinallyHost) enclosingFinally_Stmt_values.get(_parameters);
    }
    FinallyHost enclosingFinally_Stmt_value = getParent().Define_enclosingFinally(this, null, branch);
    if (state().inCircle()) {
      enclosingFinally_Stmt_values.put(_parameters, enclosingFinally_Stmt_value);
      enclosingFinally_Stmt_computed.put(_parameters, state().cycle());
    
    } else {
      enclosingFinally_Stmt_values.put(_parameters, enclosingFinally_Stmt_value);
      enclosingFinally_Stmt_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return enclosingFinally_Stmt_value;
  }
  /** @apilevel internal */
  private void enclosingFinally_Stmt_reset() {
    enclosingFinally_Stmt_computed = null;
    enclosingFinally_Stmt_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map enclosingFinally_Stmt_values;
  /** @apilevel internal */
  protected java.util.Map enclosingFinally_Stmt_computed;
  /**
   * @attribute inh
   * @aspect DefiniteAssignment
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:256
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:256")
  public boolean assignedBefore(Variable v) {
    boolean assignedBefore_Variable_value = getParent().Define_assignedBefore(this, null, v);
    return assignedBefore_Variable_value;
  }
  /**
   * @attribute inh
   * @aspect DefiniteUnassignment
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:887
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:887")
  public boolean unassignedBefore(Variable v) {
    boolean unassignedBefore_Variable_value = getParent().Define_unassignedBefore(this, null, v);
    return unassignedBefore_Variable_value;
  }
  /**
   * Find all visible methods with the given name in the local scope.
   * @attribute inh
   * @aspect LookupMethod
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupMethod.jrag:116
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupMethod.jrag:116")
  public Collection<MethodDecl> lookupMethod(String name) {
    Collection<MethodDecl> lookupMethod_String_value = getParent().Define_lookupMethod(this, null, name);
    return lookupMethod_String_value;
  }
  /**
   * @attribute inh
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupType.jrag:128
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupFullyQualifiedTypes", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupType.jrag:128")
  public TypeDecl lookupType(String packageName, String typeName) {
    TypeDecl lookupType_String_String_value = getParent().Define_lookupType(this, null, packageName, typeName);
    return lookupType_String_String_value;
  }
  /**
   * @attribute inh
   * @aspect TypeScopePropagation
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupType.jrag:399
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupType.jrag:399")
  public SimpleSet<TypeDecl> lookupType(String name) {
    SimpleSet<TypeDecl> lookupType_String_value = getParent().Define_lookupType(this, null, name);
    return lookupType_String_value;
  }
  /**
   * @attribute inh
   * @aspect VariableScope
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupVariable.jrag:40
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupVariable.jrag:40")
  public SimpleSet<Variable> lookupVariable(String name) {
    SimpleSet<Variable> lookupVariable_String_value = getParent().Define_lookupVariable(this, null, name);
    return lookupVariable_String_value;
  }
  /**
   * @attribute inh
   * @aspect NestedTypes
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeAnalysis.jrag:568
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeAnalysis.jrag:568")
  public BodyDecl enclosingBodyDecl() {
    BodyDecl enclosingBodyDecl_value = getParent().Define_enclosingBodyDecl(this, null);
    return enclosingBodyDecl_value;
  }
  /**
   * @attribute inh
   * @aspect NestedTypes
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeAnalysis.jrag:654
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeAnalysis.jrag:654")
  public TypeDecl hostType() {
    TypeDecl hostType_value = getParent().Define_hostType(this, null);
    return hostType_value;
  }
  /**
   * @attribute inh
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:48
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:48")
  public boolean reachable() {
    boolean reachable_value = getParent().Define_reachable(this, null);
    return reachable_value;
  }
  /**
   * @attribute inh
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:207
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:207")
  public boolean reportUnreachable() {
    boolean reportUnreachable_value = getParent().Define_reportUnreachable(this, null);
    return reportUnreachable_value;
  }
  /**
   * Checks if the branch statement leaves the monitor.
   * @return <code>true</code> if the branch leaves the monitor
   * @attribute inh
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1916
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1916")
  public boolean leavesMonitor(Stmt branch, SynchronizedStmt monitor) {
    boolean leavesMonitor_Stmt_SynchronizedStmt_value = getParent().Define_leavesMonitor(this, null, branch, monitor);
    return leavesMonitor_Stmt_SynchronizedStmt_value;
  }
  /**
   * @return The next available local variable index.
   * @attribute inh
   * @aspect LocalNum
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/LocalNum.jrag:63
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LocalNum", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/LocalNum.jrag:63")
  public int localNum() {
    ASTState state = state();
    if (localNum_computed == ASTState.NON_CYCLE || localNum_computed == state().cycle()) {
      return localNum_value;
    }
    localNum_value = getParent().Define_localNum(this, null);
    if (state().inCircle()) {
      localNum_computed = state().cycle();
    
    } else {
      localNum_computed = ASTState.NON_CYCLE;
    
    }
    return localNum_value;
  }
  /** @apilevel internal */
  private void localNum_reset() {
    localNum_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle localNum_computed = null;

  /** @apilevel internal */
  protected int localNum_value;

  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ResolveAmbiguousNames.jrag:86
   * @apilevel internal
   */
  public boolean Define_isLeftChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ResolveAmbiguousNames.jrag:86
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isLeftChildOfDot
   */
  protected boolean canDefine_isLeftChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ResolveAmbiguousNames.jrag:101
   * @apilevel internal
   */
  public boolean Define_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ResolveAmbiguousNames.jrag:101
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isRightChildOfDot
   */
  protected boolean canDefine_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ResolveAmbiguousNames.jrag:118
   * @apilevel internal
   */
  public Expr Define_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return prevExprError();
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ResolveAmbiguousNames.jrag:118
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute prevExpr
   */
  protected boolean canDefine_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ResolveAmbiguousNames.jrag:142
   * @apilevel internal
   */
  public Access Define_nextAccess(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return nextAccessError();
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ResolveAmbiguousNames.jrag:142
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute nextAccess
   */
  protected boolean canDefine_nextAccess(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/frontend/Annotations.jrag:95
   * @apilevel internal
   */
  public boolean Define_inComplexAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/frontend/Annotations.jrag:95
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inComplexAnnotation
   */
  protected boolean canDefine_inComplexAnnotation(ASTNode _callerNode, ASTNode _childNode) {
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
    // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:33
    if (!reachable() && reportUnreachable()) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    if (!reachable() && reportUnreachable()) {
      collection.add(error("statement is unreachable"));
    }
  }
}
