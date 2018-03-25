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
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/grammar/Java.ast:301
 * @astdecl IfStmt : Stmt ::= Condition:Expr Then:Stmt [Else:Stmt];
 * @production IfStmt : {@link Stmt} ::= <span class="component">Condition:{@link Expr}</span> <span class="component">Then:{@link Stmt}</span> <span class="component">[Else:{@link Stmt}]</span>;

 */
public class IfStmt extends Stmt implements Cloneable {
  /**
   * @aspect NodeConstructors
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NodeConstructors.jrag:80
   */
  public IfStmt(Expr cond, Stmt thenBranch) {
    this(cond, thenBranch, new Opt());
  }
  /**
   * @aspect NodeConstructors
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/NodeConstructors.jrag:84
   */
  public IfStmt(Expr cond, Stmt thenBranch, Stmt elseBranch) {
    this(cond, thenBranch, new Opt(elseBranch));
  }
  /**
   * @aspect PrettyPrintUtil
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/PrettyPrintUtil.jrag:235
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("if (");
    out.print(getCondition());
    out.print(") ");
    if (getThen() instanceof Block) {
      out.print(getThen());
    } else {
      out.print("{");
      out.println();
      out.indent(1);
      out.print(getThen());
      out.indent(0);
      out.println();
      out.print("}");
    }
    if (hasElse()) {
      out.print(" else ");
      if (getElse() instanceof Block) {
        out.print(getElse());
      } else {
        out.print("{");
        out.println();
        out.indent(1);
        out.print(getElse());
        out.println();
        out.print("}");
      }
    }
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1624
   */
  public void createBCode(CodeGeneration gen) {
    super.createBCode(gen);
    int elseBranch = else_branch_label();
    int thenBranch = then_branch_label();
    int endBranch = end_label();
    if (!getCondition().isConstant()) {
      getCondition().branchFalse(gen, elseBranch);
    }
    gen.addLabel(thenBranch);
    if (getCondition().canBeTrue()) {
      getThen().createBCode(gen);
      if (getThen().canCompleteNormally() && hasElse() && getCondition().canBeFalse()) {
        gen.GOTO(endBranch);
      }
    }
    gen.addLabel(elseBranch);
    if (hasElse() && getCondition().canBeFalse()) {
      getElse().createBCode(gen);
    }
    gen.addLabel(endBranch);
  }
  /**
   * @declaredat ASTNode:1
   */
  public IfStmt() {
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
    children = new ASTNode[3];
    setChild(new Opt(), 2);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Condition", "Then", "Else"},
    type = {"Expr", "Stmt", "Opt<Stmt>"},
    kind = {"Child", "Child", "Opt"}
  )
  public IfStmt(Expr p0, Stmt p1, Opt<Stmt> p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:25
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:31
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:35
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    canCompleteNormally_reset();
    else_branch_label_reset();
    then_branch_label_reset();
    end_label_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:45
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:49
   */
  public IfStmt clone() throws CloneNotSupportedException {
    IfStmt node = (IfStmt) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:54
   */
  public IfStmt copy() {
    try {
      IfStmt node = (IfStmt) clone();
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
   * @declaredat ASTNode:73
   */
  @Deprecated
  public IfStmt fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:83
   */
  public IfStmt treeCopyNoTransform() {
    IfStmt tree = (IfStmt) copy();
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
   * @declaredat ASTNode:103
   */
  public IfStmt treeCopy() {
    IfStmt tree = (IfStmt) copy();
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
   * @declaredat ASTNode:117
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the Condition child.
   * @param node The new node to replace the Condition child.
   * @apilevel high-level
   */
  public void setCondition(Expr node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Condition child.
   * @return The current node used as the Condition child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Condition")
  public Expr getCondition() {
    return (Expr) getChild(0);
  }
  /**
   * Retrieves the Condition child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Condition child.
   * @apilevel low-level
   */
  public Expr getConditionNoTransform() {
    return (Expr) getChildNoTransform(0);
  }
  /**
   * Replaces the Then child.
   * @param node The new node to replace the Then child.
   * @apilevel high-level
   */
  public void setThen(Stmt node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the Then child.
   * @return The current node used as the Then child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Then")
  public Stmt getThen() {
    return (Stmt) getChild(1);
  }
  /**
   * Retrieves the Then child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Then child.
   * @apilevel low-level
   */
  public Stmt getThenNoTransform() {
    return (Stmt) getChildNoTransform(1);
  }
  /**
   * Replaces the optional node for the Else child. This is the <code>Opt</code>
   * node containing the child Else, not the actual child!
   * @param opt The new node to be used as the optional node for the Else child.
   * @apilevel low-level
   */
  public void setElseOpt(Opt<Stmt> opt) {
    setChild(opt, 2);
  }
  /**
   * Replaces the (optional) Else child.
   * @param node The new node to be used as the Else child.
   * @apilevel high-level
   */
  public void setElse(Stmt node) {
    getElseOpt().setChild(node, 0);
  }
  /**
   * Check whether the optional Else child exists.
   * @return {@code true} if the optional Else child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasElse() {
    return getElseOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) Else child.
   * @return The Else child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Stmt getElse() {
    return (Stmt) getElseOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the Else child. This is the <code>Opt</code> node containing the child Else, not the actual child!
   * @return The optional node for child the Else child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="Else")
  public Opt<Stmt> getElseOpt() {
    return (Opt<Stmt>) getChild(2);
  }
  /**
   * Retrieves the optional node for child Else. This is the <code>Opt</code> node containing the child Else, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child Else.
   * @apilevel low-level
   */
  public Opt<Stmt> getElseOptNoTransform() {
    return (Opt<Stmt>) getChildNoTransform(2);
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
        new_assignedAfter_Variable_value = hasElse()
              ? getThen().assignedAfter(v) && getElse().assignedAfter(v)
              : getThen().assignedAfter(v) && getCondition().assignedAfterFalse(v);
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
      boolean new_assignedAfter_Variable_value = hasElse()
            ? getThen().assignedAfter(v) && getElse().assignedAfter(v)
            : getThen().assignedAfter(v) && getCondition().assignedAfterFalse(v);
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
        new_unassignedAfter_Variable_value = hasElse()
              ? getThen().unassignedAfter(v) && getElse().unassignedAfter(v)
              : getThen().unassignedAfter(v) && getCondition().unassignedAfterFalse(v);
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
      boolean new_unassignedAfter_Variable_value = hasElse()
            ? getThen().unassignedAfter(v) && getElse().unassignedAfter(v)
            : getThen().unassignedAfter(v) && getCondition().unassignedAfterFalse(v);
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
    canCompleteNormally_value = (reachable() && !hasElse())
        || (getThen().canCompleteNormally() || (hasElse() && getElse().canCompleteNormally()));
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
    {
        if (getThen().modifiedInScope(var)) {
          return true;
        }
        return hasElse() && getElse().modifiedInScope(var);
      }
  }
  /** @apilevel internal */
  private void else_branch_label_reset() {
    else_branch_label_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle else_branch_label_computed = null;

  /** @apilevel internal */
  protected int else_branch_label_value;

  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1618
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1618")
  public int else_branch_label() {
    ASTState state = state();
    if (else_branch_label_computed == ASTState.NON_CYCLE || else_branch_label_computed == state().cycle()) {
      return else_branch_label_value;
    }
    else_branch_label_value = hostType().constantPool().newLabel();
    if (state().inCircle()) {
      else_branch_label_computed = state().cycle();
    
    } else {
      else_branch_label_computed = ASTState.NON_CYCLE;
    
    }
    return else_branch_label_value;
  }
  /** @apilevel internal */
  private void then_branch_label_reset() {
    then_branch_label_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle then_branch_label_computed = null;

  /** @apilevel internal */
  protected int then_branch_label_value;

  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1620
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1620")
  public int then_branch_label() {
    ASTState state = state();
    if (then_branch_label_computed == ASTState.NON_CYCLE || then_branch_label_computed == state().cycle()) {
      return then_branch_label_value;
    }
    then_branch_label_value = hostType().constantPool().newLabel();
    if (state().inCircle()) {
      then_branch_label_computed = state().cycle();
    
    } else {
      then_branch_label_computed = ASTState.NON_CYCLE;
    
    }
    return then_branch_label_value;
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
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1622
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1622")
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
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (_callerNode == getElseOptNoTransform()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:687
      return getCondition().assignedAfterFalse(v);
    }
    else if (getThenNoTransform() != null && _callerNode == getThen()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:685
      return getCondition().assignedAfterTrue(v);
    }
    else if (getConditionNoTransform() != null && _callerNode == getCondition()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:683
      return assignedBefore(v);
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
    if (_callerNode == getElseOptNoTransform()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:1357
      return getCondition().unassignedAfterFalse(v);
    }
    else if (getThenNoTransform() != null && _callerNode == getThen()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:1355
      return getCondition().unassignedAfterTrue(v);
    }
    else if (getConditionNoTransform() != null && _callerNode == getCondition()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/DefiniteAssignment.jrag:1353
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
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getElseOptNoTransform()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:204
      return reachable();
    }
    else if (getThenNoTransform() != null && _callerNode == getThen()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:203
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
    if (_callerNode == getElseOptNoTransform()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:210
      return reachable();
    }
    else if (getThenNoTransform() != null && _callerNode == getThen()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/UnreachableStatements.jrag:209
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
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java8/frontend/EffectivelyFinal.jrag:30
   * @apilevel internal
   */
  public boolean Define_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    if (_callerNode == getElseOptNoTransform()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java8/frontend/EffectivelyFinal.jrag:52
      return false;
    }
    else if (getThenNoTransform() != null && _callerNode == getThen()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java8/frontend/EffectivelyFinal.jrag:51
      return false;
    }
    else {
      return getParent().Define_inhModifiedInScope(this, _callerNode, var);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java8/frontend/EffectivelyFinal.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inhModifiedInScope
   */
  protected boolean canDefine_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
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
    // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeCheck.jrag:433
    if (!getCondition().type().isBoolean()) {
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
    if (!getCondition().type().isBoolean()) {
      collection.add(errorf("the type of \"%s\" is %s which is not boolean",
                getCondition().prettyPrint(), getCondition().type().name()));
    }
  }
}
