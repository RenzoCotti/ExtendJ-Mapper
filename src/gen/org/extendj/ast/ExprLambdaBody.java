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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/grammar/Lambda.ast:13
 * @production ExprLambdaBody : {@link LambdaBody} ::= <span class="component">{@link Expr}</span>;

 */
public class ExprLambdaBody extends LambdaBody implements Cloneable {
  /**
   * @aspect Java8PrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PrettyPrint.jadd:67
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getExpr());
  }
  /**
   * @aspect LambdaToClass
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaAnonymousDecl.jrag:83
   */
  protected Block toBlock() {
    List<Stmt> stmtList = new List<Stmt>();
    // If expected return type is void, insert ExprStmt in list.
    Expr expr = getExpr().treeCopyNoTransform();
    if (enclosingLambda().targetInterface().functionDescriptor().method.type().isVoid()) {
      stmtList.add(new ExprStmt(expr));
    } else {
      // Otherwise, insert return statement.
      stmtList.add(new ReturnStmt(expr));
    }
    return new Block(stmtList);
  }
  /**
   * @declaredat ASTNode:1
   */
  public ExprLambdaBody() {
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
  public ExprLambdaBody(Expr p0) {
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
    isBlockBody_reset();
    isExprBody_reset();
    congruentTo_FunctionDescriptor_reset();
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
  public ExprLambdaBody clone() throws CloneNotSupportedException {
    ExprLambdaBody node = (ExprLambdaBody) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public ExprLambdaBody copy() {
    try {
      ExprLambdaBody node = (ExprLambdaBody) clone();
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
   * @declaredat ASTNode:62
   */
  @Deprecated
  public ExprLambdaBody fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:72
   */
  public ExprLambdaBody treeCopyNoTransform() {
    ExprLambdaBody tree = (ExprLambdaBody) copy();
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
   * @declaredat ASTNode:92
   */
  public ExprLambdaBody treeCopy() {
    ExprLambdaBody tree = (ExprLambdaBody) copy();
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
   * @declaredat ASTNode:106
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
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/EffectivelyFinal.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/EffectivelyFinal.jrag:43")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getExpr().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /** @apilevel internal */
  private void isBlockBody_reset() {
    isBlockBody_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isBlockBody_computed = null;

  /** @apilevel internal */
  protected boolean isBlockBody_value;

  /**
   * @attribute syn
   * @aspect LambdaBody
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:29
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaBody", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:29")
  public boolean isBlockBody() {
    ASTNode$State state = state();
    if (isBlockBody_computed == ASTNode$State.NON_CYCLE || isBlockBody_computed == state().cycle()) {
      return isBlockBody_value;
    }
    isBlockBody_value = false;
    if (state().inCircle()) {
      isBlockBody_computed = state().cycle();
    
    } else {
      isBlockBody_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isBlockBody_value;
  }
  /** @apilevel internal */
  private void isExprBody_reset() {
    isExprBody_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isExprBody_computed = null;

  /** @apilevel internal */
  protected boolean isExprBody_value;

  /**
   * @attribute syn
   * @aspect LambdaBody
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:30
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaBody", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:30")
  public boolean isExprBody() {
    ASTNode$State state = state();
    if (isExprBody_computed == ASTNode$State.NON_CYCLE || isExprBody_computed == state().cycle()) {
      return isExprBody_value;
    }
    isExprBody_value = true;
    if (state().inCircle()) {
      isExprBody_computed = state().cycle();
    
    } else {
      isExprBody_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isExprBody_value;
  }
  /** @apilevel internal */
  private void congruentTo_FunctionDescriptor_reset() {
    congruentTo_FunctionDescriptor_computed = new java.util.HashMap(4);
    congruentTo_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map congruentTo_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map congruentTo_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect LambdaExpr
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaExpr.jrag:74
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaExpr", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaExpr.jrag:74")
  public boolean congruentTo(FunctionDescriptor f) {
    Object _parameters = f;
    if (congruentTo_FunctionDescriptor_computed == null) congruentTo_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (congruentTo_FunctionDescriptor_values == null) congruentTo_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (congruentTo_FunctionDescriptor_values.containsKey(_parameters) && congruentTo_FunctionDescriptor_computed != null
        && congruentTo_FunctionDescriptor_computed.containsKey(_parameters)
        && (congruentTo_FunctionDescriptor_computed.get(_parameters) == ASTNode$State.NON_CYCLE || congruentTo_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (Boolean) congruentTo_FunctionDescriptor_values.get(_parameters);
    }
    boolean congruentTo_FunctionDescriptor_value = congruentTo_compute(f);
    if (state().inCircle()) {
      congruentTo_FunctionDescriptor_values.put(_parameters, congruentTo_FunctionDescriptor_value);
      congruentTo_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      congruentTo_FunctionDescriptor_values.put(_parameters, congruentTo_FunctionDescriptor_value);
      congruentTo_FunctionDescriptor_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return congruentTo_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private boolean congruentTo_compute(FunctionDescriptor f) {
      if (f.method.type().isVoid()) {
        return getExpr().stmtCompatible();
      } else {
        return getExpr().assignConversionTo(f.method.type());
      }
    }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:30
   * @apilevel internal
   */
  public TypeDecl Define_targetType(ASTNode _callerNode, ASTNode _childNode) {
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:125
      {
          TypeDecl decl = enclosingLambda().targetType();
          if (decl.isNull()) {
            return unknownType();
          } else if (!(decl instanceof InterfaceDecl)) {
            return unknownType();
          } else {
            InterfaceDecl iDecl = (InterfaceDecl) decl;
            if (!iDecl.isFunctional()) {
              return unknownType();
            } else {
              return iDecl.functionDescriptor().method.type();
            }
          }
        }
    }
    else {
      return getParent().Define_targetType(this, _callerNode);
    }
  }
  protected boolean canDefine_targetType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:195
   * @apilevel internal
   */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:207
      return true;
    }
    else {
      return getParent().Define_assignmentContext(this, _callerNode);
    }
  }
  protected boolean canDefine_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:196
   * @apilevel internal
   */
  public boolean Define_invocationContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:208
      return false;
    }
    else {
      return getParent().Define_invocationContext(this, _callerNode);
    }
  }
  protected boolean canDefine_invocationContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:197
   * @apilevel internal
   */
  public boolean Define_castContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:209
      return false;
    }
    else {
      return getParent().Define_castContext(this, _callerNode);
    }
  }
  protected boolean canDefine_castContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:198
   * @apilevel internal
   */
  public boolean Define_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:210
      return false;
    }
    else {
      return getParent().Define_stringContext(this, _callerNode);
    }
  }
  protected boolean canDefine_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:199
   * @apilevel internal
   */
  public boolean Define_numericContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:211
      return false;
    }
    else {
      return getParent().Define_numericContext(this, _callerNode);
    }
  }
  protected boolean canDefine_numericContext(ASTNode _callerNode, ASTNode _childNode) {
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
