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
 * Used in code generation to represent the implicit monitor exit
 * call at the end of a synchronized block.
 * @ast node
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/grammar/Java.ast:221
 * @production MonitorExit : {@link Block};

 */
public class MonitorExit extends Block implements Cloneable {
  /**
   * @aspect MonitorExit
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/MonitorExit.jrag:34
   */
  protected SynchronizedStmt monitor = null;
  /**
   * @aspect MonitorExit
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/MonitorExit.jrag:36
   */
  public MonitorExit(SynchronizedStmt sync) {
    monitor = sync;
  }
  /**
   * Generate bytecode for the monitor exit call.
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:2596
   */
  public void createBCode(CodeGeneration gen) {
    gen.monitorRangeEnd(this, monitor.monitorId, hostType().constantPool().newLabel());
  }
  /**
   * Generate exception handler for monitor closing.
   * @param gen
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:2604
   */
  public void emitMonitorExitHandler(CodeGeneration gen) {
    int handler_lbl = handler_label();
    int end_lbl = handler_end_label();

    gen.changeStackDepth(1);
    int num = localNum() + 1;

    // Handler start.
    gen.addLabel(handler_lbl);

    gen.emitStoreReference(this, num);

    gen.emitLoadReference(this, monitor.localNum());
    gen.emit(this, Bytecode.MONITOREXIT);

    // Handler end.
    gen.addLabel(end_lbl);

    gen.emitLoadReference(this, num);
    gen.emit(this, Bytecode.ATHROW);

    // Add exception handler for the monitor closing.
    // See http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4414101.
    gen.addException(handler_lbl, end_lbl, handler_lbl, CodeGeneration.ExceptionEntry.CATCH_ALL);
  }
  /**
   * @declaredat ASTNode:1
   */
  public MonitorExit() {
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
    setChild(new List(), 0);
  }
  /**
   * @declaredat ASTNode:14
   */
  public MonitorExit(List<Stmt> p0) {
    setChild(p0, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:18
   */
  protected int numChildren() {
    return 1;
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
    handler_label_reset();
    handler_end_label_reset();
    end_label_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:35
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:39
   */
  public MonitorExit clone() throws CloneNotSupportedException {
    MonitorExit node = (MonitorExit) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:44
   */
  public MonitorExit copy() {
    try {
      MonitorExit node = (MonitorExit) clone();
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
   * @declaredat ASTNode:63
   */
  @Deprecated
  public MonitorExit fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:73
   */
  public MonitorExit treeCopyNoTransform() {
    MonitorExit tree = (MonitorExit) copy();
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
   * @declaredat ASTNode:93
   */
  public MonitorExit treeCopy() {
    MonitorExit tree = (MonitorExit) copy();
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
   * @declaredat ASTNode:107
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the Stmt list.
   * @param list The new list node to be used as the Stmt list.
   * @apilevel high-level
   */
  public void setStmtList(List<Stmt> list) {
    setChild(list, 0);
  }
  /**
   * Retrieves the number of children in the Stmt list.
   * @return Number of children in the Stmt list.
   * @apilevel high-level
   */
  public int getNumStmt() {
    return getStmtList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Stmt list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Stmt list.
   * @apilevel low-level
   */
  public int getNumStmtNoTransform() {
    return getStmtListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Stmt list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Stmt list.
   * @apilevel high-level
   */
  public Stmt getStmt(int i) {
    return (Stmt) getStmtList().getChild(i);
  }
  /**
   * Check whether the Stmt list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasStmt() {
    return getStmtList().getNumChild() != 0;
  }
  /**
   * Append an element to the Stmt list.
   * @param node The element to append to the Stmt list.
   * @apilevel high-level
   */
  public void addStmt(Stmt node) {
    List<Stmt> list = (parent == null) ? getStmtListNoTransform() : getStmtList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addStmtNoTransform(Stmt node) {
    List<Stmt> list = getStmtListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the Stmt list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setStmt(Stmt node, int i) {
    List<Stmt> list = getStmtList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the Stmt list.
   * @return The node representing the Stmt list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Stmt")
  public List<Stmt> getStmtList() {
    List<Stmt> list = (List<Stmt>) getChild(0);
    return list;
  }
  /**
   * Retrieves the Stmt list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Stmt list.
   * @apilevel low-level
   */
  public List<Stmt> getStmtListNoTransform() {
    return (List<Stmt>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the Stmt list without
   * triggering rewrites.
   */
  public Stmt getStmtNoTransform(int i) {
    return (Stmt) getStmtListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Stmt list.
   * @return The node representing the Stmt list.
   * @apilevel high-level
   */
  public List<Stmt> getStmts() {
    return getStmtList();
  }
  /**
   * Retrieves the Stmt list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Stmt list.
   * @apilevel low-level
   */
  public List<Stmt> getStmtsNoTransform() {
    return getStmtListNoTransform();
  }
  /** @apilevel internal */
  private void handler_label_reset() {
    handler_label_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle handler_label_computed = null;

  /** @apilevel internal */
  protected int handler_label_value;

  /**
   * @attribute syn
   * @aspect MonitorExit
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/MonitorExit.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MonitorExit", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/MonitorExit.jrag:32")
  public int handler_label() {
    ASTNode$State state = state();
    if (handler_label_computed == ASTNode$State.NON_CYCLE || handler_label_computed == state().cycle()) {
      return handler_label_value;
    }
    handler_label_value = hostType().constantPool().newLabel();
    if (state().inCircle()) {
      handler_label_computed = state().cycle();
    
    } else {
      handler_label_computed = ASTNode$State.NON_CYCLE;
    
    }
    return handler_label_value;
  }
  /** @apilevel internal */
  private void handler_end_label_reset() {
    handler_end_label_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle handler_end_label_computed = null;

  /** @apilevel internal */
  protected int handler_end_label_value;

  /**
   * @attribute syn
   * @aspect MonitorExit
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/MonitorExit.jrag:34
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MonitorExit", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/MonitorExit.jrag:34")
  public int handler_end_label() {
    ASTNode$State state = state();
    if (handler_end_label_computed == ASTNode$State.NON_CYCLE || handler_end_label_computed == state().cycle()) {
      return handler_end_label_value;
    }
    handler_end_label_value = hostType().constantPool().newLabel();
    if (state().inCircle()) {
      handler_end_label_computed = state().cycle();
    
    } else {
      handler_end_label_computed = ASTNode$State.NON_CYCLE;
    
    }
    return handler_end_label_value;
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
   * @aspect MonitorExit
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/MonitorExit.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MonitorExit", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/MonitorExit.jrag:36")
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
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }
}
