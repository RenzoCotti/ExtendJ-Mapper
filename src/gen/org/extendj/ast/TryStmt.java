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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/grammar/Java.ast:223
 * @production TryStmt : {@link Stmt} ::= <span class="component">{@link Block}</span> <span class="component">{@link CatchClause}*</span> <span class="component">[Finally:{@link Block}]</span> <span class="component">ExceptionHandler:{@link Block}</span>;

 */
public class TryStmt extends Stmt implements Cloneable, FinallyHost {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrint.jadd:593
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("try ");
    out.print(getBlock());
    out.print(" ");
    out.join(getCatchClauseList(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.print(" ");
      }
    });
    if (hasFinally()) {
      out.print(" finally ");
      out.print(getFinally());
    }
  }
  /**
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:116
   */
  public void collectBranches(Collection<Stmt> c) {
    c.addAll(escapedBranches());
  }
  /**
   * @aspect DefiniteUnassignment
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1235
   */
  public Block getFinallyBlock() {
    return getFinally();
  }
  /**
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:301
   */
  protected boolean reachedException(TypeDecl type) {
    boolean found = false;
    // Found is true if the exception type is caught by a catch clause.
    for (int i = 0; i < getNumCatchClause() && !found; i++) {
      if (getCatchClause(i).handles(type)) {
        found = true;
      }
    }
    // If an exception is thrown in the block and the exception is not caught and
    // either there is no finally block or the finally block can complete normally.
    if (!found && (!hasNonEmptyFinally() || getFinally().canCompleteNormally()) ) {
      if (getBlock().reachedException(type)) {
        return true;
      }
    }
    // Even if the exception is caught by the catch clauses they may
    // throw new exceptions.
    for (int i = 0; i < getNumCatchClause(); i++) {
      if (getCatchClause(i).reachedException(type)) {
        return true;
      }
    }
    return hasNonEmptyFinally() && getFinally().reachedException(type);
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1954
   */
  public void createBCode(CodeGeneration gen) {
    int block_top_lbl = gen.constantPool().newLabel();
    int block_end_lbl = gen.constantPool().newLabel();
    int fallthrough_lbl = fallthrough_label();
    int handler_lbl = gen.constantPool().newLabel();
    boolean needsHandler = false;

    super.createBCode(gen);
    gen.addLabel(block_top_lbl);
    getBlock().createBCode(gen);
    gen.addLabel(block_end_lbl);

    if (getBlock().canCompleteNormally()) {
      gen.emitGoto(this, fallthrough_lbl);
    }

    for (int i = 0; i < getNumCatchClause(); i++) {
      CatchClause cc = getCatchClause(i);
      cc.createBCode(gen);
      cc.exceptionTableEntries(gen, block_top_lbl, block_end_lbl);
      if (cc.getBlock().canCompleteNormally()) {
        gen.emitGoto(this, fallthrough_lbl);
      }
      if (hasNonEmptyFinally()) {
        int cc_end_lbl = gen.constantPool().newLabel();
        gen.addLabel(cc_end_lbl);
        needsHandler = true;
        gen.addException(cc.label(), cc_end_lbl, handler_lbl,
            CodeGeneration.ExceptionEntry.CATCH_ALL);
      }
    }

    if (hasNonEmptyFinally()) {

      emitExceptionHandler(gen, block_top_lbl, block_end_lbl, handler_lbl, needsHandler);

      // Fallthrough finally.
      gen.addLabel(fallthrough_lbl);
      getFinally().createBCode(gen);
    }

    gen.addLabel(label_end());
  }
  /**
   * Generate catch-all (finally).
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:2014
   */
  public void emitExceptionHandler(CodeGeneration gen, int start_lbl, int end_lbl,
      int handler_lbl, boolean needsHandler) {
    int top_pc = gen.addressOf(start_lbl);
    int end_pc = gen.addressOf(end_lbl);
    if (needsHandler || top_pc != end_pc) {
      // Catch-all exception handler (finally).
      gen.addLabel(handler_lbl);
      gen.changeStackDepth(1);
      int num = getBlock().localNum();
      gen.emitStoreReference(this, num);
      getExceptionHandler().createBCode(gen);
      gen.emitLoadReference(this, num);
      gen.emit(this, Bytecode.ATHROW);
      gen.addException(start_lbl, end_lbl, handler_lbl, CodeGeneration.ExceptionEntry.CATCH_ALL);
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public TryStmt() {
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
    children = new ASTNode[4];
    setChild(new List(), 1);
    setChild(new Opt(), 2);
  }
  /**
   * @declaredat ASTNode:15
   */
  public TryStmt(Block p0, List<CatchClause> p1, Opt<Block> p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:21
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:27
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:31
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    branches_reset();
    escapedBranches_reset();
    assignedAfter_Variable_reset();
    unassignedAfterFinally_Variable_reset();
    assignedAfterFinally_Variable_reset();
    unassignedBefore_Variable_reset();
    unassignedAfter_Variable_reset();
    hasNonEmptyFinally_reset();
    catchableException_TypeDecl_reset();
    getExceptionHandler_reset();
    canCompleteNormally_reset();
    fallthrough_label_reset();
    label_end_reset();
    handlesException_TypeDecl_reset();
    typeError_reset();
    typeRuntimeException_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:55
   */
  public TryStmt clone() throws CloneNotSupportedException {
    TryStmt node = (TryStmt) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:60
   */
  public TryStmt copy() {
    try {
      TryStmt node = (TryStmt) clone();
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
   * @declaredat ASTNode:79
   */
  @Deprecated
  public TryStmt fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:89
   */
  public TryStmt treeCopyNoTransform() {
    TryStmt tree = (TryStmt) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 3:
          tree.children[i] = null;
          continue;
        }
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
   * @declaredat ASTNode:114
   */
  public TryStmt treeCopy() {
    TryStmt tree = (TryStmt) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        switch (i) {
        case 3:
          tree.children[i] = null;
          continue;
        }
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
   * @declaredat ASTNode:133
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the Block child.
   * @param node The new node to replace the Block child.
   * @apilevel high-level
   */
  public void setBlock(Block node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Block child.
   * @return The current node used as the Block child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Block")
  public Block getBlock() {
    return (Block) getChild(0);
  }
  /**
   * Retrieves the Block child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Block child.
   * @apilevel low-level
   */
  public Block getBlockNoTransform() {
    return (Block) getChildNoTransform(0);
  }
  /**
   * Replaces the CatchClause list.
   * @param list The new list node to be used as the CatchClause list.
   * @apilevel high-level
   */
  public void setCatchClauseList(List<CatchClause> list) {
    setChild(list, 1);
  }
  /**
   * Retrieves the number of children in the CatchClause list.
   * @return Number of children in the CatchClause list.
   * @apilevel high-level
   */
  public int getNumCatchClause() {
    return getCatchClauseList().getNumChild();
  }
  /**
   * Retrieves the number of children in the CatchClause list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the CatchClause list.
   * @apilevel low-level
   */
  public int getNumCatchClauseNoTransform() {
    return getCatchClauseListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the CatchClause list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the CatchClause list.
   * @apilevel high-level
   */
  public CatchClause getCatchClause(int i) {
    return (CatchClause) getCatchClauseList().getChild(i);
  }
  /**
   * Check whether the CatchClause list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasCatchClause() {
    return getCatchClauseList().getNumChild() != 0;
  }
  /**
   * Append an element to the CatchClause list.
   * @param node The element to append to the CatchClause list.
   * @apilevel high-level
   */
  public void addCatchClause(CatchClause node) {
    List<CatchClause> list = (parent == null) ? getCatchClauseListNoTransform() : getCatchClauseList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addCatchClauseNoTransform(CatchClause node) {
    List<CatchClause> list = getCatchClauseListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the CatchClause list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setCatchClause(CatchClause node, int i) {
    List<CatchClause> list = getCatchClauseList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the CatchClause list.
   * @return The node representing the CatchClause list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="CatchClause")
  public List<CatchClause> getCatchClauseList() {
    List<CatchClause> list = (List<CatchClause>) getChild(1);
    return list;
  }
  /**
   * Retrieves the CatchClause list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the CatchClause list.
   * @apilevel low-level
   */
  public List<CatchClause> getCatchClauseListNoTransform() {
    return (List<CatchClause>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the CatchClause list without
   * triggering rewrites.
   */
  public CatchClause getCatchClauseNoTransform(int i) {
    return (CatchClause) getCatchClauseListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the CatchClause list.
   * @return The node representing the CatchClause list.
   * @apilevel high-level
   */
  public List<CatchClause> getCatchClauses() {
    return getCatchClauseList();
  }
  /**
   * Retrieves the CatchClause list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the CatchClause list.
   * @apilevel low-level
   */
  public List<CatchClause> getCatchClausesNoTransform() {
    return getCatchClauseListNoTransform();
  }
  /**
   * Replaces the optional node for the Finally child. This is the <code>Opt</code>
   * node containing the child Finally, not the actual child!
   * @param opt The new node to be used as the optional node for the Finally child.
   * @apilevel low-level
   */
  public void setFinallyOpt(Opt<Block> opt) {
    setChild(opt, 2);
  }
  /**
   * Replaces the (optional) Finally child.
   * @param node The new node to be used as the Finally child.
   * @apilevel high-level
   */
  public void setFinally(Block node) {
    getFinallyOpt().setChild(node, 0);
  }
  /**
   * Check whether the optional Finally child exists.
   * @return {@code true} if the optional Finally child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasFinally() {
    return getFinallyOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) Finally child.
   * @return The Finally child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Block getFinally() {
    return (Block) getFinallyOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the Finally child. This is the <code>Opt</code> node containing the child Finally, not the actual child!
   * @return The optional node for child the Finally child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="Finally")
  public Opt<Block> getFinallyOpt() {
    return (Opt<Block>) getChild(2);
  }
  /**
   * Retrieves the optional node for child Finally. This is the <code>Opt</code> node containing the child Finally, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child Finally.
   * @apilevel low-level
   */
  public Opt<Block> getFinallyOptNoTransform() {
    return (Opt<Block>) getChildNoTransform(2);
  }
  /**
   * Retrieves the ExceptionHandler child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the ExceptionHandler child.
   * @apilevel low-level
   */
  public Block getExceptionHandlerNoTransform() {
    return (Block) getChildNoTransform(3);
  }
  /**
   * Retrieves the child position of the optional child ExceptionHandler.
   * @return The the child position of the optional child ExceptionHandler.
   * @apilevel low-level
   */
  protected int getExceptionHandlerChildPosition() {
    return 3;
  }
  /** @apilevel internal */
  private void branches_reset() {
    branches_computed = null;
    branches_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle branches_computed = null;

  /** @apilevel internal */
  protected Collection<Stmt> branches_value;

  /** All branches that reach this node. 
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:156
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:156")
  public Collection<Stmt> branches() {
    ASTNode$State state = state();
    if (branches_computed == ASTNode$State.NON_CYCLE || branches_computed == state().cycle()) {
      return branches_value;
    }
    branches_value = branches_compute();
    if (state().inCircle()) {
      branches_computed = state().cycle();
    
    } else {
      branches_computed = ASTNode$State.NON_CYCLE;
    
    }
    return branches_value;
  }
  /** @apilevel internal */
  private Collection<Stmt> branches_compute() {
      Collection<Stmt> set = new HashSet<Stmt>();
      getBlock().collectBranches(set);
      for (int i = 0; i < getNumCatchClause(); i++) {
        getCatchClause(i).collectBranches(set);
      }
      return set;
    }
  /** @apilevel internal */
  private void escapedBranches_reset() {
    escapedBranches_computed = null;
    escapedBranches_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle escapedBranches_computed = null;

  /** @apilevel internal */
  protected Collection<Stmt> escapedBranches_value;

  /** All branches that escape this node. 
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:166
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:166")
  public Collection<Stmt> escapedBranches() {
    ASTNode$State state = state();
    if (escapedBranches_computed == ASTNode$State.NON_CYCLE || escapedBranches_computed == state().cycle()) {
      return escapedBranches_value;
    }
    escapedBranches_value = escapedBranches_compute();
    if (state().inCircle()) {
      escapedBranches_computed = state().cycle();
    
    } else {
      escapedBranches_computed = ASTNode$State.NON_CYCLE;
    
    }
    return escapedBranches_value;
  }
  /** @apilevel internal */
  private Collection<Stmt> escapedBranches_compute() {
      Collection<Stmt> set = new HashSet<Stmt>();
      if (hasNonEmptyFinally()) {
        // Branches from finally.
        getFinally().collectBranches(set);
      }
      if (!hasFinally() || getFinally().canCompleteNormally()) {
        set.addAll(branches());
      }
      return set;
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
      // 16.2.15 4th bullet
      if (!hasNonEmptyFinally()) {
        if (!getBlock().assignedAfter(v)) {
          return false;
        }
        for (int i = 0; i < getNumCatchClause(); i++) {
          if (!getCatchClause(i).getBlock().assignedAfter(v)) {
            return false;
          }
        }
        return true;
      } else {
        // 16.2.15 5th bullet
        if (getFinally().assignedAfter(v)) {
          return true;
        }
        if (!getBlock().assignedAfter(v)) {
          return false;
        }
        for (int i = 0; i < getNumCatchClause(); i++) {
          if (!getCatchClause(i).getBlock().assignedAfter(v)) {
            return false;
          }
        }
        return true;
      }
    }
  /** @apilevel internal */
  private void unassignedAfterFinally_Variable_reset() {
    unassignedAfterFinally_Variable_values = null;
  }
  protected java.util.Map unassignedAfterFinally_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1243")
  public boolean unassignedAfterFinally(Variable v) {
    Object _parameters = v;
    if (unassignedAfterFinally_Variable_values == null) unassignedAfterFinally_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (unassignedAfterFinally_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfterFinally_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      unassignedAfterFinally_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfterFinally_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfterFinally_Variable_value = getFinally().unassignedAfter(v);
        if (new_unassignedAfterFinally_Variable_value != ((Boolean)_value.value)) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfterFinally_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfterFinally_Variable_values.put(_parameters, new_unassignedAfterFinally_Variable_value);

      state.leaveCircle();
      return new_unassignedAfterFinally_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfterFinally_Variable_value = getFinally().unassignedAfter(v);
      if (new_unassignedAfterFinally_Variable_value != ((Boolean)_value.value)) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfterFinally_Variable_value;
      }
      return new_unassignedAfterFinally_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private void assignedAfterFinally_Variable_reset() {
    assignedAfterFinally_Variable_values = null;
  }
  protected java.util.Map assignedAfterFinally_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1248")
  public boolean assignedAfterFinally(Variable v) {
    Object _parameters = v;
    if (assignedAfterFinally_Variable_values == null) assignedAfterFinally_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (assignedAfterFinally_Variable_values.containsKey(_parameters)) {
      Object _cache = assignedAfterFinally_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      assignedAfterFinally_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_assignedAfterFinally_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_assignedAfterFinally_Variable_value = getFinally().assignedAfter(v);
        if (new_assignedAfterFinally_Variable_value != ((Boolean)_value.value)) {
          state.setChangeInCycle();
          _value.value = new_assignedAfterFinally_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedAfterFinally_Variable_values.put(_parameters, new_assignedAfterFinally_Variable_value);

      state.leaveCircle();
      return new_assignedAfterFinally_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedAfterFinally_Variable_value = getFinally().assignedAfter(v);
      if (new_assignedAfterFinally_Variable_value != ((Boolean)_value.value)) {
        state.setChangeInCycle();
        _value.value = new_assignedAfterFinally_Variable_value;
      }
      return new_assignedAfterFinally_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private void unassignedBefore_Variable_reset() {
    unassignedBefore_Variable_values = null;
  }
  protected java.util.Map unassignedBefore_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1576")
  public boolean unassignedBefore(Variable v) {
    Object _parameters = v;
    if (unassignedBefore_Variable_values == null) unassignedBefore_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (unassignedBefore_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedBefore_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      unassignedBefore_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedBefore_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedBefore_Variable_value = super.unassignedBefore(v);
        if (new_unassignedBefore_Variable_value != ((Boolean)_value.value)) {
          state.setChangeInCycle();
          _value.value = new_unassignedBefore_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedBefore_Variable_values.put(_parameters, new_unassignedBefore_Variable_value);

      state.leaveCircle();
      return new_unassignedBefore_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedBefore_Variable_value = super.unassignedBefore(v);
      if (new_unassignedBefore_Variable_value != ((Boolean)_value.value)) {
        state.setChangeInCycle();
        _value.value = new_unassignedBefore_Variable_value;
      }
      return new_unassignedBefore_Variable_value;
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
      // 16.2.14 4th bullet
      if (!hasNonEmptyFinally()) {
        if (!getBlock().unassignedAfter(v)) {
          return false;
        }
        for (int i = 0; i < getNumCatchClause(); i++) {
          if (!getCatchClause(i).getBlock().unassignedAfter(v)) {
            return false;
          }
        }
        return true;
      } else {
        return getFinally().unassignedAfter(v);
      }
    }
  /** @apilevel internal */
  private void hasNonEmptyFinally_reset() {
    hasNonEmptyFinally_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle hasNonEmptyFinally_computed = null;

  /** @apilevel internal */
  protected boolean hasNonEmptyFinally_value;

  /**
   * @return <code>true</code> if this TyStmt has a non-empty finally block
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:43")
  public boolean hasNonEmptyFinally() {
    ASTNode$State state = state();
    if (hasNonEmptyFinally_computed == ASTNode$State.NON_CYCLE || hasNonEmptyFinally_computed == state().cycle()) {
      return hasNonEmptyFinally_value;
    }
    hasNonEmptyFinally_value = hasFinally() && getFinally().getNumStmt() > 0;
    if (state().inCircle()) {
      hasNonEmptyFinally_computed = state().cycle();
    
    } else {
      hasNonEmptyFinally_computed = ASTNode$State.NON_CYCLE;
    
    }
    return hasNonEmptyFinally_value;
  }
  /** @apilevel internal */
  private void catchableException_TypeDecl_reset() {
    catchableException_TypeDecl_computed = new java.util.HashMap(4);
    catchableException_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map catchableException_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map catchableException_TypeDecl_computed;
  /**
   * The block of the try statement can throw an exception of
   * a type assignable to the given type.
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:289
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:289")
  public boolean catchableException(TypeDecl type) {
    Object _parameters = type;
    if (catchableException_TypeDecl_computed == null) catchableException_TypeDecl_computed = new java.util.HashMap(4);
    if (catchableException_TypeDecl_values == null) catchableException_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (catchableException_TypeDecl_values.containsKey(_parameters) && catchableException_TypeDecl_computed != null
        && catchableException_TypeDecl_computed.containsKey(_parameters)
        && (catchableException_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || catchableException_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) catchableException_TypeDecl_values.get(_parameters);
    }
    boolean catchableException_TypeDecl_value = getBlock().reachedException(type);
    if (state().inCircle()) {
      catchableException_TypeDecl_values.put(_parameters, catchableException_TypeDecl_value);
      catchableException_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      catchableException_TypeDecl_values.put(_parameters, catchableException_TypeDecl_value);
      catchableException_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return catchableException_TypeDecl_value;
  }
  /** @apilevel internal */
  private void getExceptionHandler_reset() {
    getExceptionHandler_computed = false;
    
    getExceptionHandler_value = null;
  }
  /** @apilevel internal */
  protected boolean getExceptionHandler_computed = false;

  /** @apilevel internal */
  protected Block getExceptionHandler_value;

  /** Copy of the finally block for catch-all exception handling. 
   * @attribute syn nta
   * @aspect NTAFinally
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NTAFinally.jrag:59
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="NTAFinally", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NTAFinally.jrag:59")
  public Block getExceptionHandler() {
    ASTNode$State state = state();
    if (getExceptionHandler_computed) {
      return (Block) getChild(getExceptionHandlerChildPosition());
    }
    state().enterLazyAttribute();
    getExceptionHandler_value = getExceptionHandler_compute();
    setChild(getExceptionHandler_value, getExceptionHandlerChildPosition());
    getExceptionHandler_computed = true;
    state().leaveLazyAttribute();
    Block node = (Block) this.getChild(getExceptionHandlerChildPosition());
    return node;
  }
  /** @apilevel internal */
  private Block getExceptionHandler_compute() {
      if (hasNonEmptyFinally()) {
        NTAFinallyBlock ntaBlock = new NTAFinallyBlock(this);
        ntaBlock.addStmt((Block) getFinally().treeCopyNoTransform());
        return ntaBlock;
      } else {
        return new NTAFinallyBlock();
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
    canCompleteNormally_value = canCompleteNormally_compute();
    if (state().inCircle()) {
      canCompleteNormally_computed = state().cycle();
    
    } else {
      canCompleteNormally_computed = ASTNode$State.NON_CYCLE;
    
    }
    return canCompleteNormally_value;
  }
  /** @apilevel internal */
  private boolean canCompleteNormally_compute() {
       boolean anyCatchClauseCompleteNormally = false;
       for (int i = 0; i < getNumCatchClause() && !anyCatchClauseCompleteNormally; i++) {
         anyCatchClauseCompleteNormally = getCatchClause(i).getBlock().canCompleteNormally();
       }
       return (getBlock().canCompleteNormally() || anyCatchClauseCompleteNormally)
         && (!hasNonEmptyFinally() || getFinally().canCompleteNormally());
    }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:78")
  public boolean modifiedInScope(Variable var) {
    {
        if (getBlock().modifiedInScope(var)) {
          return true;
        }
        for (CatchClause cc : getCatchClauseList()) {
          if (cc.modifiedInScope(var)) {
            return true;
          }
        }
        return hasNonEmptyFinally() && getFinally().modifiedInScope(var);
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
    int break_label_value = label_end();
    return break_label_value;
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1883
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1883")
  public int continue_label() {
    int continue_label_value = label_end();
    return continue_label_value;
  }
  /** @apilevel internal */
  private void fallthrough_label_reset() {
    fallthrough_label_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle fallthrough_label_computed = null;

  /** @apilevel internal */
  protected int fallthrough_label_value;

  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1951
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1951")
  public int fallthrough_label() {
    ASTNode$State state = state();
    if (fallthrough_label_computed == ASTNode$State.NON_CYCLE || fallthrough_label_computed == state().cycle()) {
      return fallthrough_label_value;
    }
    fallthrough_label_value = hasNonEmptyFinally() ? hostType().constantPool().newLabel() : label_end();
    if (state().inCircle()) {
      fallthrough_label_computed = state().cycle();
    
    } else {
      fallthrough_label_computed = ASTNode$State.NON_CYCLE;
    
    }
    return fallthrough_label_value;
  }
  /**
   * Outer finally host with a finally block that can complete normally.
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:2001
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:2001")
  public FinallyHost outerFinallyHost() {
    {
        // Find outer finally block.
        FinallyHost outer = this;
        Iterator<FinallyHost> iter = finallyIterator();
        while (iter.hasNext()) {
          outer = iter.next();
        }
        return outer;
      }
  }
  /** @apilevel internal */
  private void label_end_reset() {
    label_end_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle label_end_computed = null;

  /** @apilevel internal */
  protected int label_end_value;

  /**
   * @attribute syn
   * @aspect NTAFinally
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/NTAFinally.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NTAFinally", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/NTAFinally.jrag:32")
  public int label_end() {
    ASTNode$State state = state();
    if (label_end_computed == ASTNode$State.NON_CYCLE || label_end_computed == state().cycle()) {
      return label_end_value;
    }
    label_end_value = hostType().constantPool().newLabel();
    if (state().inCircle()) {
      label_end_computed = state().cycle();
    
    } else {
      label_end_computed = ASTNode$State.NON_CYCLE;
    
    }
    return label_end_value;
  }
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:93
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:93")
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
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:197
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:197")
  public TypeDecl typeError() {
    ASTNode$State state = state();
    if (typeError_computed == ASTNode$State.NON_CYCLE || typeError_computed == state().cycle()) {
      return typeError_value;
    }
    typeError_value = getParent().Define_typeError(this, null);
    if (state().inCircle()) {
      typeError_computed = state().cycle();
    
    } else {
      typeError_computed = ASTNode$State.NON_CYCLE;
    
    }
    return typeError_value;
  }
  /** @apilevel internal */
  private void typeError_reset() {
    typeError_computed = null;
    typeError_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle typeError_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeError_value;

  /**
   * @attribute inh
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:198
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:198")
  public TypeDecl typeRuntimeException() {
    ASTNode$State state = state();
    if (typeRuntimeException_computed == ASTNode$State.NON_CYCLE || typeRuntimeException_computed == state().cycle()) {
      return typeRuntimeException_value;
    }
    typeRuntimeException_value = getParent().Define_typeRuntimeException(this, null);
    if (state().inCircle()) {
      typeRuntimeException_computed = state().cycle();
    
    } else {
      typeRuntimeException_computed = ASTNode$State.NON_CYCLE;
    
    }
    return typeRuntimeException_value;
  }
  /** @apilevel internal */
  private void typeRuntimeException_reset() {
    typeRuntimeException_computed = null;
    typeRuntimeException_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle typeRuntimeException_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeRuntimeException_value;

  /**
   * @attribute inh
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:572
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="CodeGeneration", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:572")
  public TypeDecl typeThrowable() {
    TypeDecl typeThrowable_value = getParent().Define_typeThrowable(this, null);
    return typeThrowable_value;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:273
   * @apilevel internal
   */
  public FinallyHost Define_enclosingFinally(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    if (_callerNode == getFinallyOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:280
      return enclosingFinally(branch);
    }
    else {
      int childIndex = this.getIndexOfChild(_callerNode);
      return hasNonEmptyFinally() ? this : enclosingFinally(branch);
    }
  }
  protected boolean canDefine_enclosingFinally(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (_callerNode == getFinallyOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:858
      return assignedBefore(v);
    }
    else if (_callerNode == getCatchClauseListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:855
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return getBlock().assignedBefore(v);
    }
    else if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:852
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
    if (_callerNode == getFinallyOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1590
      {
          if (!getBlock().unassignedEverywhere(v)) {
            return false;
      	}
          for (int i = 0; i < getNumCatchClause(); i++) {
            if (!getCatchClause(i).getBlock().checkDUeverywhere(v)) {
              return false;
      	  }
      	}
          return true;
        }
    }
    else if (_callerNode == getCatchClauseListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1579
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      {
          if (!getBlock().unassignedAfter(v)) {
            return false;
          }
          if (!getBlock().unassignedEverywhere(v)) {
            return false;
          }
          return true;
        }
    }
    else if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1574
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/TryWithResources.jrag:115
   * @apilevel internal
   */
  public boolean Define_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:266
      {
          for (int i = 0; i < getNumCatchClause(); i++) {
            if (getCatchClause(i).handles(exceptionType)) {
              return true;
            }
          }
          if (hasNonEmptyFinally() && !getFinally().canCompleteNormally()) {
            return true;
          }
          return handlesException(exceptionType);
        }
    }
    else if (_callerNode == getCatchClauseListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:259
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      {
          if (hasNonEmptyFinally() && !getFinally().canCompleteNormally()) {
            return true;
          }
          return handlesException(exceptionType);
        }
    }
    else {
      return getParent().Define_handlesException(this, _callerNode, exceptionType);
    }
  }
  protected boolean canDefine_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getFinallyOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:176
      return reachable();
    }
    else if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:175
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:182
   * @apilevel internal
   */
  public boolean Define_reachableCatchClause(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    if (_callerNode == getCatchClauseListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:183
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      {
          for (int i = 0; i < childIndex; i++) {
            if (getCatchClause(i).handles(exceptionType)) {
              return false;
            }
          }
          if (catchableException(exceptionType)) {
            return true;
          }
          if (exceptionType.mayCatch(typeError()) || exceptionType.mayCatch(typeRuntimeException())) {
            return true;
          }
          return false;
        }
    }
    else {
      return getParent().Define_reachableCatchClause(this, _callerNode, exceptionType);
    }
  }
  protected boolean canDefine_reachableCatchClause(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:280
   * @apilevel internal
   */
  public boolean Define_reportUnreachable(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getFinallyOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:216
      return reachable();
    }
    else if (_callerNode == getCatchClauseListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:215
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return reachable();
    }
    else if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:214
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:217
   * @apilevel internal
   */
  public Collection<TypeDecl> Define_caughtExceptions(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getCatchClauseListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:219
      int index = _callerNode.getIndexOfChild(_childNode);
      {
          Collection<TypeDecl> exceptions = new HashSet<TypeDecl>();
          getBlock().collectExceptions(exceptions, this);
          Collection<TypeDecl> caught = new LinkedList<TypeDecl>();
          for (TypeDecl exception : exceptions) {
            // This catch clause handles the exception.
            if (!getCatchClause(index).handles(exception)) {
              continue;
            }
            // No previous catch clause handles the exception.
            boolean already = false;
            for (int i = 0; i < index; ++i) {
              if (getCatchClause(i).handles(exception)) {
                already = true;
                break;
              }
            }
            if (!already) {
              caught.add(exception);
            }
          }
          return caught;
        }
    }
    else {
      return getParent().Define_caughtExceptions(this, _callerNode);
    }
  }
  protected boolean canDefine_caughtExceptions(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:64
   * @apilevel internal
   */
  public int Define_localNum(ASTNode _callerNode, ASTNode _childNode) {
    if (getExceptionHandlerNoTransform() != null && _callerNode == getExceptionHandler()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:191
      return localNum() + 1;
    }
    else if (_callerNode == getFinallyOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:188
      return localNum();
    }
    else {
      int childIndex = this.getIndexOfChild(_callerNode);
      return localNum();
    }
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
}
