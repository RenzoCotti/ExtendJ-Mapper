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
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/grammar/Java.ast:271
 * @astdecl EqualityExpr : RelationalExpr;
 * @production EqualityExpr : {@link RelationalExpr};

 */
public abstract class EqualityExpr extends RelationalExpr implements Cloneable {
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/backend/AutoBoxingCodegen.jrag:376
   */
  public void branchTrue(CodeGeneration gen, int target) {
    // Branch when true.
    if (isConstant()) {
      if (isTrue()) {
        gen.GOTO(target);
        return;
      }
    } else {
      TypeDecl type = getLeftOperand().type();
      if (type.isNumericType()
          && !(type.isReferenceType() && getRightOperand().type().isReferenceType())) {
        type = binaryNumericPromotedType();
        getLeftOperand().createBCode(gen);
        getLeftOperand().emitCastTo(gen, type); // Binary numeric promotion.
        getRightOperand().createBCode(gen);
        getRightOperand().emitCastTo(gen, type); // Binary numeric promotion.
      } else if (type.isBoolean() && type != getRightOperand().type()) {
        type = binaryNumericPromotedType();
        getLeftOperand().createBCode(gen);
        getLeftOperand().emitCastTo(gen, type); // Binary numeric promotion.
        getRightOperand().createBCode(gen);
        getRightOperand().emitCastTo(gen, type); // Binary numeric promotion.
      } else {
        getLeftOperand().createBCode(gen);
        getRightOperand().createBCode(gen);
      }
      compareBranch(gen, target, type);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/backend/AutoBoxingCodegen.jrag:406
   */
  public void branchFalse(CodeGeneration gen, int target) {
    // Branch when false.
    if (isConstant()) {
      if (isFalse()) {
        gen.GOTO(target);
        return;
      }
    } else {
      TypeDecl type = getLeftOperand().type();
      if (type.isNumericType() && !(type.isReferenceType() && getRightOperand().type().isReferenceType())) {
        type = binaryNumericPromotedType();
        getLeftOperand().createBCode(gen);
        getLeftOperand().emitCastTo(gen, type); // Binary numeric promotion.
        getRightOperand().createBCode(gen);
        getRightOperand().emitCastTo(gen, type); // Binary numeric promotion.
      } else if (type.isBoolean() && type != getRightOperand().type()) {
        type = binaryNumericPromotedType();
        getLeftOperand().createBCode(gen);
        getLeftOperand().emitCastTo(gen, type); // Binary numeric promotion.
        getRightOperand().createBCode(gen);
        getRightOperand().emitCastTo(gen, type); // Binary numeric promotion.
      } else {
        getLeftOperand().createBCode(gen);
        getRightOperand().createBCode(gen);
      }
      compareNotBranch(gen, target, type);
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public EqualityExpr() {
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
    name = {"LeftOperand", "RightOperand"},
    type = {"Expr", "Expr"},
    kind = {"Child", "Child"}
  )
  public EqualityExpr(Expr p0, Expr p1) {
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
  }
  /** @apilevel internal 
   * @declaredat ASTNode:37
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:41
   */
  public EqualityExpr clone() throws CloneNotSupportedException {
    EqualityExpr node = (EqualityExpr) super.clone();
    return node;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:52
   */
  @Deprecated
  public abstract EqualityExpr fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:60
   */
  public abstract EqualityExpr treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:68
   */
  public abstract EqualityExpr treeCopy();
  /**
   * Replaces the LeftOperand child.
   * @param node The new node to replace the LeftOperand child.
   * @apilevel high-level
   */
  public void setLeftOperand(Expr node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the LeftOperand child.
   * @return The current node used as the LeftOperand child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="LeftOperand")
  public Expr getLeftOperand() {
    return (Expr) getChild(0);
  }
  /**
   * Retrieves the LeftOperand child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the LeftOperand child.
   * @apilevel low-level
   */
  public Expr getLeftOperandNoTransform() {
    return (Expr) getChildNoTransform(0);
  }
  /**
   * Replaces the RightOperand child.
   * @param node The new node to replace the RightOperand child.
   * @apilevel high-level
   */
  public void setRightOperand(Expr node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the RightOperand child.
   * @return The current node used as the RightOperand child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="RightOperand")
  public Expr getRightOperand() {
    return (Expr) getChild(1);
  }
  /**
   * Retrieves the RightOperand child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the RightOperand child.
   * @apilevel low-level
   */
  public Expr getRightOperandNoTransform() {
    return (Expr) getChildNoTransform(1);
  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeCheck.jrag:280
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeCheck.jrag:280")
  public Collection<Problem> typeProblems() {
    {
        TypeDecl left = getLeftOperand().type();
        TypeDecl right = getRightOperand().type();
        if (left.isNumericType() && right.isNumericType()) {
          return Collections.emptyList();
        } else if (left.isBoolean() && right.isBoolean()) {
          return Collections.emptyList();
        } else if ((left.isReferenceType() || left.isNull())
            && (right.isReferenceType() || right.isNull())) {
          if (left.castingConversionTo(right) || right.castingConversionTo(left)) {
            return Collections.emptyList();
          }
        }
        return Collections.singletonList(errorf("%s can not be compared to %s",
            left.typeName(), right.typeName()));
      }
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
