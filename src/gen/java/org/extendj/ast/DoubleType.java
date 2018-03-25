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
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/grammar/Java.ast:151
 * @astdecl DoubleType : FloatingPointType;
 * @production DoubleType : {@link FloatingPointType};

 */
public class DoubleType extends FloatingPointType implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/PrettyPrint.jadd:350
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("double");
  }
  /**
   * @aspect Attributes
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/Attributes.jrag:137
   */
  public int addConstant(ConstantPool p, Constant c) {
    return p.addConstant(c.doubleValue());
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:114
   */
  public void emitPushConstant(CodeGeneration gen, int value) { DoubleLiteral.push(gen, value); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:184
   */
  public void emitReturn(CodeGeneration gen)    { gen.DRETURN(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:193
   */
  public void emitArrayLoad(CodeGeneration gen)    { gen.DALOAD(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:203
   */
  public void emitLoadLocal(CodeGeneration gen, int pos)     { gen.DLOAD(pos); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:263
   */
  public void emitArrayStore(CodeGeneration gen)     { gen.DASTORE(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:285
   */
  public void emitStoreLocal(CodeGeneration gen, int pos)    { gen.DSTORE(pos); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:386
   */
  void emitCastTo(CodeGeneration gen, TypeDecl type)    { type.doubleToThis(gen); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:398
   */
  void intToThis(CodeGeneration gen) { gen.I2D(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:410
   */
  void floatToThis(CodeGeneration gen) { gen.F2D(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:419
   */
  void doubleToThis(CodeGeneration gen) { }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:428
   */
  void longToThis(CodeGeneration gen) { gen.L2D(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:435
   */
  void byteToThis(CodeGeneration gen)   { gen.I2D();}
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:443
   */
  void charToThis(CodeGeneration gen)   { gen.I2D();}
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:451
   */
  void shortToThis(CodeGeneration gen)   { gen.I2D(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:484
   */
  void neg(CodeGeneration gen)   { gen.DNEG(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:496
   */
  void add(CodeGeneration gen)   { gen.DADD(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:502
   */
  void sub(CodeGeneration gen)   { gen.DSUB(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:508
   */
  void mul(CodeGeneration gen)   { gen.DMUL(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:514
   */
  void div(CodeGeneration gen)   { gen.DDIV(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:520
   */
  void rem(CodeGeneration gen)   { gen.DREM(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:553
   */
  public void branchLT(CodeGeneration gen, int label)   { gen.DCMPG(); gen.IFLT(label); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:559
   */
  public void branchLTInv(CodeGeneration gen, int label) { gen.DCMPL(); gen.IFLT(label); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:565
   */
  public void branchLE(CodeGeneration gen, int label)   { gen.DCMPG(); gen.IFLE(label); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:571
   */
  public void branchLEInv(CodeGeneration gen, int label) { gen.DCMPL(); gen.IFLE(label); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:577
   */
  public void branchGE(CodeGeneration gen, int label)   { gen.DCMPL(); gen.IFGE(label); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:583
   */
  public void branchGEInv(CodeGeneration gen, int label) { gen.DCMPG(); gen.IFGE(label); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:589
   */
  public void branchGT(CodeGeneration gen, int label)   { gen.DCMPL(); gen.IFGT(label); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:595
   */
  public void branchGTInv(CodeGeneration gen, int label) { gen.DCMPG(); gen.IFGT(label); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:601
   */
  public void branchEQ(CodeGeneration gen, int label)    { gen.DCMPL(); gen.IFEQ(label); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CodeGeneration.jrag:612
   */
  public void branchNE(CodeGeneration gen, int label)    { gen.DCMPL(); gen.IFNE(label); }
  /**
   * @aspect AnnotationsCodegen
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/backend/AnnotationsCodegen.jrag:261
   */
  public int addAnnotConstant(ConstantPool p, Constant c) {
    return addConstant(p, c);
  }
  /**
   * @declaredat ASTNode:1
   */
  public DoubleType() {
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
    setChild(new Opt(), 1);
    setChild(new List(), 2);
  }
  /**
   * @declaredat ASTNode:15
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "SuperClass", "BodyDecl"},
    type = {"Modifiers", "String", "Opt<Access>", "List<BodyDecl>"},
    kind = {"Child", "Token", "Opt", "List"}
  )
  public DoubleType(Modifiers p0, String p1, Opt<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @declaredat ASTNode:26
   */
  public DoubleType(Modifiers p0, beaver.Symbol p1, Opt<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:33
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:39
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    boxed_reset();
    typeDescriptor_reset();
    jvmName_reset();
    verificationType_reset();
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
  public DoubleType clone() throws CloneNotSupportedException {
    DoubleType node = (DoubleType) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:60
   */
  public DoubleType copy() {
    try {
      DoubleType node = (DoubleType) clone();
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
  public DoubleType fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:89
   */
  public DoubleType treeCopyNoTransform() {
    DoubleType tree = (DoubleType) copy();
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
   * @declaredat ASTNode:109
   */
  public DoubleType treeCopy() {
    DoubleType tree = (DoubleType) copy();
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
   * @declaredat ASTNode:123
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node) && (tokenString_ID == ((DoubleType) node).tokenString_ID);    
  }
  /**
   * Replaces the Modifiers child.
   * @param node The new node to replace the Modifiers child.
   * @apilevel high-level
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Modifiers child.
   * @return The current node used as the Modifiers child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Modifiers")
  public Modifiers getModifiers() {
    return (Modifiers) getChild(0);
  }
  /**
   * Retrieves the Modifiers child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Modifiers child.
   * @apilevel low-level
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers) getChildNoTransform(0);
  }
  /**
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public void setID(beaver.Symbol symbol) {
    if (symbol.value != null && !(symbol.value instanceof String))
    throw new UnsupportedOperationException("setID is only valid for String lexemes");
    tokenString_ID = (String)symbol.value;
    IDstart = symbol.getStart();
    IDend = symbol.getEnd();
  }
  /**
   * Retrieves the value for the lexeme ID.
   * @return The value for the lexeme ID.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="ID")
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Replaces the optional node for the SuperClass child. This is the <code>Opt</code>
   * node containing the child SuperClass, not the actual child!
   * @param opt The new node to be used as the optional node for the SuperClass child.
   * @apilevel low-level
   */
  public void setSuperClassOpt(Opt<Access> opt) {
    setChild(opt, 1);
  }
  /**
   * Replaces the (optional) SuperClass child.
   * @param node The new node to be used as the SuperClass child.
   * @apilevel high-level
   */
  public void setSuperClass(Access node) {
    getSuperClassOpt().setChild(node, 0);
  }
  /**
   * Check whether the optional SuperClass child exists.
   * @return {@code true} if the optional SuperClass child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasSuperClass() {
    return getSuperClassOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) SuperClass child.
   * @return The SuperClass child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Access getSuperClass() {
    return (Access) getSuperClassOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the SuperClass child. This is the <code>Opt</code> node containing the child SuperClass, not the actual child!
   * @return The optional node for child the SuperClass child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="SuperClass")
  public Opt<Access> getSuperClassOpt() {
    return (Opt<Access>) getChild(1);
  }
  /**
   * Retrieves the optional node for child SuperClass. This is the <code>Opt</code> node containing the child SuperClass, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child SuperClass.
   * @apilevel low-level
   */
  public Opt<Access> getSuperClassOptNoTransform() {
    return (Opt<Access>) getChildNoTransform(1);
  }
  /**
   * Replaces the BodyDecl list.
   * @param list The new list node to be used as the BodyDecl list.
   * @apilevel high-level
   */
  public void setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 2);
  }
  /**
   * Retrieves the number of children in the BodyDecl list.
   * @return Number of children in the BodyDecl list.
   * @apilevel high-level
   */
  public int getNumBodyDecl() {
    return getBodyDeclList().getNumChild();
  }
  /**
   * Retrieves the number of children in the BodyDecl list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the BodyDecl list.
   * @apilevel low-level
   */
  public int getNumBodyDeclNoTransform() {
    return getBodyDeclListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the BodyDecl list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the BodyDecl list.
   * @apilevel high-level
   */
  public BodyDecl getBodyDecl(int i) {
    return (BodyDecl) getBodyDeclList().getChild(i);
  }
  /**
   * Check whether the BodyDecl list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasBodyDecl() {
    return getBodyDeclList().getNumChild() != 0;
  }
  /**
   * Append an element to the BodyDecl list.
   * @param node The element to append to the BodyDecl list.
   * @apilevel high-level
   */
  public void addBodyDecl(BodyDecl node) {
    List<BodyDecl> list = (parent == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addBodyDeclNoTransform(BodyDecl node) {
    List<BodyDecl> list = getBodyDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the BodyDecl list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setBodyDecl(BodyDecl node, int i) {
    List<BodyDecl> list = getBodyDeclList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the BodyDecl list.
   * @return The node representing the BodyDecl list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="BodyDecl")
  public List<BodyDecl> getBodyDeclList() {
    List<BodyDecl> list = (List<BodyDecl>) getChild(2);
    return list;
  }
  /**
   * Retrieves the BodyDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the BodyDecl list.
   * @apilevel low-level
   */
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>) getChildNoTransform(2);
  }
  /**
   * @return the element at index {@code i} in the BodyDecl list without
   * triggering rewrites.
   */
  public BodyDecl getBodyDeclNoTransform(int i) {
    return (BodyDecl) getBodyDeclListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the BodyDecl list.
   * @return The node representing the BodyDecl list.
   * @apilevel high-level
   */
  public List<BodyDecl> getBodyDecls() {
    return getBodyDeclList();
  }
  /**
   * Retrieves the BodyDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the BodyDecl list.
   * @apilevel low-level
   */
  public List<BodyDecl> getBodyDeclsNoTransform() {
    return getBodyDeclListNoTransform();
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:95")
  public Constant cast(Constant c) {
    Constant cast_Constant_value = Constant.create(c.doubleValue());
    return cast_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:118
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:118")
  public Constant plus(Constant c) {
    Constant plus_Constant_value = c;
    return plus_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:131
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:131")
  public Constant minus(Constant c) {
    Constant minus_Constant_value = Constant.create(-c.doubleValue());
    return minus_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:153
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:153")
  public Constant mul(Constant c1, Constant c2) {
    Constant mul_Constant_Constant_value = Constant.create(c1.doubleValue() * c2.doubleValue());
    return mul_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:167
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:167")
  public Constant div(Constant c1, Constant c2) {
    Constant div_Constant_Constant_value = Constant.create(c1.doubleValue() / c2.doubleValue());
    return div_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:181
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:181")
  public Constant mod(Constant c1, Constant c2) {
    Constant mod_Constant_Constant_value = Constant.create(c1.doubleValue() % c2.doubleValue());
    return mod_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:195
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:195")
  public Constant add(Constant c1, Constant c2) {
    Constant add_Constant_Constant_value = Constant.create(c1.doubleValue() + c2.doubleValue());
    return add_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:212
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:212")
  public Constant sub(Constant c1, Constant c2) {
    Constant sub_Constant_Constant_value = Constant.create(c1.doubleValue() - c2.doubleValue());
    return sub_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:299
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:299")
  public Constant questionColon(Constant cond, Constant c1, Constant c2) {
    Constant questionColon_Constant_Constant_Constant_value = Constant.create(cond.booleanValue() ? c1.doubleValue() : c2.doubleValue());
    return questionColon_Constant_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:499
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:499")
  public boolean eqIsTrue(Expr left, Expr right) {
    boolean eqIsTrue_Expr_Expr_value = left.constant().doubleValue() == right.constant().doubleValue();
    return eqIsTrue_Expr_Expr_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:522
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:522")
  public boolean ltIsTrue(Expr left, Expr right) {
    boolean ltIsTrue_Expr_Expr_value = left.constant().doubleValue() < right.constant().doubleValue();
    return ltIsTrue_Expr_Expr_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:536
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/ConstantExpression.jrag:536")
  public boolean leIsTrue(Expr left, Expr right) {
    boolean leIsTrue_Expr_Expr_value = left.constant().doubleValue() <= right.constant().doubleValue();
    return leIsTrue_Expr_Expr_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeAnalysis.jrag:212
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/TypeAnalysis.jrag:212")
  public boolean isDouble() {
    boolean isDouble_value = true;
    return isDouble_value;
  }
  /** @apilevel internal */
  private void boxed_reset() {
    boxed_computed = null;
    boxed_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle boxed_computed = null;

  /** @apilevel internal */
  protected TypeDecl boxed_value;

  /** Mapping between Primitive type and corresponding boxed Reference type. 
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/frontend/AutoBoxing.jrag:53
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/frontend/AutoBoxing.jrag:53")
  public TypeDecl boxed() {
    ASTState state = state();
    if (boxed_computed == ASTState.NON_CYCLE || boxed_computed == state().cycle()) {
      return boxed_value;
    }
    boxed_value = lookupType("java.lang", "Double");
    if (state().inCircle()) {
      boxed_computed = state().cycle();
    
    } else {
      boxed_computed = ASTState.NON_CYCLE;
    
    }
    return boxed_value;
  }
  /** @apilevel internal */
  private void typeDescriptor_reset() {
    typeDescriptor_computed = null;
    typeDescriptor_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeDescriptor_computed = null;

  /** @apilevel internal */
  protected String typeDescriptor_value;

  /**
   * @attribute syn
   * @aspect ConstantPoolNames
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/ConstantPoolNames.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantPoolNames", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/ConstantPoolNames.jrag:78")
  public String typeDescriptor() {
    ASTState state = state();
    if (typeDescriptor_computed == ASTState.NON_CYCLE || typeDescriptor_computed == state().cycle()) {
      return typeDescriptor_value;
    }
    typeDescriptor_value = "D";
    if (state().inCircle()) {
      typeDescriptor_computed = state().cycle();
    
    } else {
      typeDescriptor_computed = ASTState.NON_CYCLE;
    
    }
    return typeDescriptor_value;
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1004
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/CreateBCode.jrag:1004")
  public int arrayPrimitiveTypeDescriptor() {
    int arrayPrimitiveTypeDescriptor_value = 7;
    return arrayPrimitiveTypeDescriptor_value;
  }
  /** @apilevel internal */
  private void jvmName_reset() {
    jvmName_computed = null;
    jvmName_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle jvmName_computed = null;

  /** @apilevel internal */
  protected String jvmName_value;

  /**
   * @attribute syn
   * @aspect Java2Rewrites
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/Java2Rewrites.jrag:37
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java2Rewrites", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/Java2Rewrites.jrag:37")
  public String jvmName() {
    ASTState state = state();
    if (jvmName_computed == ASTState.NON_CYCLE || jvmName_computed == state().cycle()) {
      return jvmName_value;
    }
    jvmName_value = "D";
    if (state().inCircle()) {
      jvmName_computed = state().cycle();
    
    } else {
      jvmName_computed = ASTState.NON_CYCLE;
    
    }
    return jvmName_value;
  }
  /**
   * @attribute syn
   * @aspect Java2Rewrites
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/Java2Rewrites.jrag:75
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java2Rewrites", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/Java2Rewrites.jrag:75")
  public String primitiveClassName() {
    String primitiveClassName_value = "Double";
    return primitiveClassName_value;
  }
  /**
   * The number of local variable slots that a variable of this type occupies.
   * @attribute syn
   * @aspect LocalNum
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/LocalNum.jrag:201
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LocalNum", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/LocalNum.jrag:201")
  public int variableSize() {
    int variableSize_value = 2;
    return variableSize_value;
  }
  /** @apilevel internal */
  private void verificationType_reset() {
    verificationType_computed = null;
    verificationType_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle verificationType_computed = null;

  /** @apilevel internal */
  protected VerificationType verificationType_value;

  /**
   * @attribute syn
   * @aspect VerificationTypes
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/VerificationTypes.jrag:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VerificationTypes", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/VerificationTypes.jrag:42")
  public VerificationType verificationType() {
    ASTState state = state();
    if (verificationType_computed == ASTState.NON_CYCLE || verificationType_computed == state().cycle()) {
      return verificationType_value;
    }
    verificationType_value = VerificationTypes.DOUBLE;
    if (state().inCircle()) {
      verificationType_computed = state().cycle();
    
    } else {
      verificationType_computed = ASTState.NON_CYCLE;
    
    }
    return verificationType_value;
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
