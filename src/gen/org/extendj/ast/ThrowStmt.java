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
import org.jastadd.util.*;
import java.util.zip.*;
import java.io.*;
import org.jastadd.util.PrettyPrintable;
import org.jastadd.util.PrettyPrinter;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
/**
 * @ast node
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/grammar/Java.ast:213
 * @production ThrowStmt : {@link Stmt} ::= <span class="component">{@link Expr}</span>;

 */
public class ThrowStmt extends Stmt implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrint.jadd:588
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("throw ");
    out.print(getExpr());
    out.print(";");
  }
  /**
   * @aspect AnonymousClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/AnonymousClasses.jrag:129
   */
  protected void collectExceptions(Collection<TypeDecl> exceptions, ASTNode target) {
    super.collectExceptions(exceptions, target);
    TypeDecl exceptionType = getExpr().type();
    if (exceptionType == typeNull()) {
      exceptionType = typeNullPointerException();
    }
    exceptions.add(exceptionType);
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1946
   */
  public void createBCode(CodeGeneration gen) {
    super.createBCode(gen);
    getExpr().createBCode(gen);
    gen.emitThrow(this);
  }
  /**
   * @declaredat ASTNode:1
   */
  public ThrowStmt() {
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
    children = new ASTNode[1];
  }
  /**
   * @declaredat ASTNode:13
   */
  public ThrowStmt(Expr p0) {
    setChild(p0, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:17
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:23
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:27
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    canCompleteNormally_reset();
    typeNullPointerException_reset();
    handlesException_TypeDecl_reset();
    typeThrowable_reset();
    typeNull_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:42
   */
  public ThrowStmt clone() throws CloneNotSupportedException {
    ThrowStmt node = (ThrowStmt) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public ThrowStmt copy() {
    try {
      ThrowStmt node = (ThrowStmt) clone();
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
   * @declaredat ASTNode:66
   */
  @Deprecated
  public ThrowStmt fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:76
   */
  public ThrowStmt treeCopyNoTransform() {
    ThrowStmt tree = (ThrowStmt) copy();
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
   * @declaredat ASTNode:96
   */
  public ThrowStmt treeCopy() {
    ThrowStmt tree = (ThrowStmt) copy();
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
   * @declaredat ASTNode:110
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
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:260
   */
   
  protected boolean reachedException(TypeDecl catchType) {
    Collection<TypeDecl> exceptionTypes = getExpr().throwTypes();
    boolean reached = false;
    for (TypeDecl exceptionType : exceptionTypes) {
      if (exceptionType == typeNull()) {
        exceptionType = typeNullPointerException();
      }
      if (catchType.mayCatch(exceptionType)) {
        reached = true;
        break;
      }
      if (super.reachedException(catchType)) {
        reached = true;
        break;
      }
    }
    return reached;
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
        new_assignedAfter_Variable_value = true;
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
      boolean new_assignedAfter_Variable_value = true;
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
        new_unassignedAfter_Variable_value = true;
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
      boolean new_unassignedAfter_Variable_value = true;
      if (new_unassignedAfter_Variable_value != ((Boolean)_value.value)) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfter_Variable_value;
      }
      return new_unassignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:181
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:181")
  public Collection<Problem> exceptionHandlingProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        Collection<TypeDecl> exceptionTypes = getExpr().throwTypes();
        for (TypeDecl exceptionType : exceptionTypes) {
          if (exceptionType == typeNull()) {
            exceptionType = typeNullPointerException();
          }
          // 8.4.4
          if (exceptionType.isCheckedException() && !handlesException(exceptionType)) {
            problems.add(errorf("%s throws uncaught exception %s",
                this.prettyPrint(), exceptionType.fullName()));
          }
        }
        return problems;
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
    canCompleteNormally_value = false;
    if (state().inCircle()) {
      canCompleteNormally_computed = state().cycle();
    
    } else {
      canCompleteNormally_computed = ASTNode$State.NON_CYCLE;
    
    }
    return canCompleteNormally_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:78")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = false;
    return modifiedInScope_Variable_value;
  }
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:56
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:56")
  public TypeDecl typeNullPointerException() {
    ASTNode$State state = state();
    if (typeNullPointerException_computed == ASTNode$State.NON_CYCLE || typeNullPointerException_computed == state().cycle()) {
      return typeNullPointerException_value;
    }
    typeNullPointerException_value = getParent().Define_typeNullPointerException(this, null);
    if (state().inCircle()) {
      typeNullPointerException_computed = state().cycle();
    
    } else {
      typeNullPointerException_computed = ASTNode$State.NON_CYCLE;
    
    }
    return typeNullPointerException_value;
  }
  /** @apilevel internal */
  private void typeNullPointerException_reset() {
    typeNullPointerException_computed = null;
    typeNullPointerException_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle typeNullPointerException_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeNullPointerException_value;

  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:90
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:90")
  public boolean handlesException(TypeDecl exceptionType) {
    Object _parameters = exceptionType;
    if (handlesException_TypeDecl_computed == null) handlesException_TypeDecl_computed = new java.util.HashMap(4);
    if (handlesException_TypeDecl_values == null) handlesException_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (handlesException_TypeDecl_values.containsKey(_parameters) && handlesException_TypeDecl_computed != null
        && handlesException_TypeDecl_computed.containsKey(_parameters)
        && (handlesException_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || handlesException_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) handlesException_TypeDecl_values.get(_parameters);
    }
    boolean handlesException_TypeDecl_value = getParent().Define_handlesException(this, null, exceptionType);
    if (state().inCircle()) {
      handlesException_TypeDecl_values.put(_parameters, handlesException_TypeDecl_value);
      handlesException_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      handlesException_TypeDecl_values.put(_parameters, handlesException_TypeDecl_value);
      handlesException_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return handlesException_TypeDecl_value;
  }
  /** @apilevel internal */
  private void handlesException_TypeDecl_reset() {
    handlesException_TypeDecl_computed = new java.util.HashMap(4);
    handlesException_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map handlesException_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map handlesException_TypeDecl_computed;
  /**
   * @attribute inh
   * @aspect SpecialClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:92
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:92")
  public TypeDecl typeThrowable() {
    ASTNode$State state = state();
    if (typeThrowable_computed == ASTNode$State.NON_CYCLE || typeThrowable_computed == state().cycle()) {
      return typeThrowable_value;
    }
    typeThrowable_value = getParent().Define_typeThrowable(this, null);
    if (state().inCircle()) {
      typeThrowable_computed = state().cycle();
    
    } else {
      typeThrowable_computed = ASTNode$State.NON_CYCLE;
    
    }
    return typeThrowable_value;
  }
  /** @apilevel internal */
  private void typeThrowable_reset() {
    typeThrowable_computed = null;
    typeThrowable_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle typeThrowable_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeThrowable_value;

  /**
   * @attribute inh
   * @aspect SpecialClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="SpecialClasses", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:95")
  public TypeDecl typeNull() {
    ASTNode$State state = state();
    if (typeNull_computed == ASTNode$State.NON_CYCLE || typeNull_computed == state().cycle()) {
      return typeNull_value;
    }
    typeNull_value = getParent().Define_typeNull(this, null);
    if (state().inCircle()) {
      typeNull_computed = state().cycle();
    
    } else {
      typeNull_computed = ASTNode$State.NON_CYCLE;
    
    }
    return typeNull_value;
  }
  /** @apilevel internal */
  private void typeNull_reset() {
    typeNull_computed = null;
    typeNull_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle typeNull_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeNull_value;

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:843
      return assignedBefore(v);
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
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1565
      return unassignedBefore(v);
    }
    else {
      return getParent().Define_unassignedBefore(this, _callerNode, v);
    }
  }
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
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
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:179
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:496
    if (!getExpr().type().instanceOf(typeThrowable())) {
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
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : exceptionHandlingProblems()) {
      collection.add(value);
    }
    if (!getExpr().type().instanceOf(typeThrowable())) {
      collection.add(error("*** The thrown expression must extend Throwable"));
    }
  }
}
