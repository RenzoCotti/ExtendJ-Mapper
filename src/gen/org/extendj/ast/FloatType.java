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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/grammar/Java.ast:59
 * @production FloatType : {@link FloatingPointType};

 */
public class FloatType extends FloatingPointType implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrint.jadd:383
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("float");
  }
  /**
   * @aspect Attributes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Attributes.jrag:133
   */
  public int addConstant(ConstantPool p, Constant c) {
    return p.addConstant(c.floatValue());
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:206
   */
  public void emitPushConstant(ASTNode<ASTNode> node, CodeGeneration gen, int value) {
    FloatingPointLiteral.push(node, gen, value);
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:287
   */
  public void emitReturn(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.FRETURN);
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:363
   */
  public void emitLoadLocal(ASTNode<ASTNode> node, CodeGeneration gen, int pos) {
    gen.maxLocals = Math.max(gen.maxLocals, pos+1);
    if (pos == 0) {
      gen.emit(node, Bytecode.FLOAD_0);
    } else if (pos == 1) {
      gen.emit(node, Bytecode.FLOAD_1);
    } else if (pos == 2) {
      gen.emit(node, Bytecode.FLOAD_2);
    } else if (pos == 3) {
      gen.emit(node, Bytecode.FLOAD_3);
    } else if (pos < 256) {
      gen.emit(node, Bytecode.FLOAD).add(pos);
    } else {
      gen.emit(node, Bytecode.WIDE).emit(node, Bytecode.FLOAD).add2(pos);
    }
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:530
   */
  public void emitStoreLocal(ASTNode<ASTNode> node, CodeGeneration gen, int pos) {
    gen.maxLocals = Math.max(gen.maxLocals, pos+1);
    if (pos == 0) {
      gen.emit(node, Bytecode.FSTORE_0);
    } else if (pos == 1) {
      gen.emit(node, Bytecode.FSTORE_1);
    } else if (pos == 2) {
      gen.emit(node, Bytecode.FSTORE_2);
    } else if (pos == 3) {
      gen.emit(node, Bytecode.FSTORE_3);
    } else if (pos < 256) {
      gen.emit(node, Bytecode.FSTORE).add(pos);
    } else {
      gen.emit(node, Bytecode.WIDE).emit(node, Bytecode.FSTORE).add2(pos);
    }
  }
  /**
   * @aspect CodeGenerationConversions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:725
   */
  void emitCastTo(ASTNode<ASTNode> node, CodeGeneration gen, TypeDecl type) {
    type.floatToThis(node, gen);
  }
  /**
   * @aspect CodeGenerationConversions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:766
   */
  void intToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.I2F);
  }
  /**
   * @aspect CodeGenerationConversions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:806
   */
  void floatToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
  }
  /**
   * @aspect CodeGenerationConversions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:837
   */
  void doubleToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.D2F);
  }
  /**
   * @aspect CodeGenerationConversions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:868
   */
  void longToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.L2F);
  }
  /**
   * @aspect CodeGenerationConversions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:890
   */
  void byteToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.I2F);
  }
  /**
   * @aspect CodeGenerationConversions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:917
   */
  void charToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.I2F);
  }
  /**
   * @aspect CodeGenerationConversions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:944
   */
  void shortToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.I2F);
  }
  /**
   * @aspect CodeGenerationBinaryOperations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1033
   */
  void neg(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.FNEG);
  }
  /**
   * @aspect CodeGenerationBinaryOperations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1069
   */
  void add(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.FADD);
  }
  /**
   * @aspect CodeGenerationBinaryOperations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1089
   */
  void sub(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.FSUB);
  }
  /**
   * @aspect CodeGenerationBinaryOperations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1109
   */
  void mul(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.FMUL);
  }
  /**
   * @aspect CodeGenerationBinaryOperations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1129
   */
  void div(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.FDIV);
  }
  /**
   * @aspect CodeGenerationBinaryOperations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1149
   */
  void rem(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.FREM);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1256
   */
  public void branchLT(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emit(node, Bytecode.FCMPG).emitCompare(node, Bytecode.IFLT, label);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1278
   */
  public void branchLTInv(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emit(node, Bytecode.FCMPL).emitCompare(node, Bytecode.IFLT, label);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1291
   */
  public void branchLE(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emit(node, Bytecode.FCMPG).emitCompare(node, Bytecode.IFLE, label);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1314
   */
  public void branchLEInv(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emit(node, Bytecode.FCMPL).emitCompare(node, Bytecode.IFLE, label);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1326
   */
  public void branchGE(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emit(node, Bytecode.FCMPL).emitCompare(node, Bytecode.IFGE, label);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1347
   */
  public void branchGEInv(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emit(node, Bytecode.FCMPG).emitCompare(node, Bytecode.IFGE, label);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1360
   */
  public void branchGT(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emit(node, Bytecode.FCMPL).emitCompare(node, Bytecode.IFGT, label);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1381
   */
  public void branchGTInv(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emit(node, Bytecode.FCMPG).emitCompare(node, Bytecode.IFGT, label);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1393
   */
  public void branchEQ(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emit(node, Bytecode.FCMPL).emitCompare(node, Bytecode.IFEQ, label);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1425
   */
  public void branchNE(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emit(node, Bytecode.FCMPL).emitCompare(node, Bytecode.IFNE, label);
  }
  /**
   * @aspect AnnotationsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AnnotationsCodegen.jrag:237
   */
  public int addAnnotConstant(ConstantPool p, Constant c) {
    return addConstant(p, c);
  }
  /**
   * @declaredat ASTNode:1
   */
  public FloatType() {
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
  public FloatType(Modifiers p0, String p1, Opt<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @declaredat ASTNode:21
   */
  public FloatType(Modifiers p0, beaver.Symbol p1, Opt<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:28
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:34
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    boxed_reset();
    typeDescriptor_reset();
    jvmName_reset();
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
  public FloatType clone() throws CloneNotSupportedException {
    FloatType node = (FloatType) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:54
   */
  public FloatType copy() {
    try {
      FloatType node = (FloatType) clone();
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
  public FloatType fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:83
   */
  public FloatType treeCopyNoTransform() {
    FloatType tree = (FloatType) copy();
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
  public FloatType treeCopy() {
    FloatType tree = (FloatType) copy();
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
    return super.is$Equal(node) && (tokenString_ID == ((FloatType) node).tokenString_ID);    
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:95")
  public Constant cast(Constant c) {
    Constant cast_Constant_value = Constant.create(c.floatValue());
    return cast_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:118
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:118")
  public Constant plus(Constant c) {
    Constant plus_Constant_value = c;
    return plus_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:131
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:131")
  public Constant minus(Constant c) {
    Constant minus_Constant_value = Constant.create(-c.floatValue());
    return minus_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:153
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:153")
  public Constant mul(Constant c1, Constant c2) {
    Constant mul_Constant_Constant_value = Constant.create(c1.floatValue() * c2.floatValue());
    return mul_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:167
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:167")
  public Constant div(Constant c1, Constant c2) {
    Constant div_Constant_Constant_value = Constant.create(c1.floatValue() / c2.floatValue());
    return div_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:181
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:181")
  public Constant mod(Constant c1, Constant c2) {
    Constant mod_Constant_Constant_value = Constant.create(c1.floatValue() % c2.floatValue());
    return mod_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:195
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:195")
  public Constant add(Constant c1, Constant c2) {
    Constant add_Constant_Constant_value = Constant.create(c1.floatValue() + c2.floatValue());
    return add_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:212
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:212")
  public Constant sub(Constant c1, Constant c2) {
    Constant sub_Constant_Constant_value = Constant.create(c1.floatValue() - c2.floatValue());
    return sub_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:299
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:299")
  public Constant questionColon(Constant cond, Constant c1, Constant c2) {
    Constant questionColon_Constant_Constant_Constant_value = Constant.create(cond.booleanValue() ? c1.floatValue() : c2.floatValue());
    return questionColon_Constant_Constant_Constant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:499
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:499")
  public boolean eqIsTrue(Expr left, Expr right) {
    boolean eqIsTrue_Expr_Expr_value = left.constant().floatValue() == right.constant().floatValue();
    return eqIsTrue_Expr_Expr_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:522
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:522")
  public boolean ltIsTrue(Expr left, Expr right) {
    boolean ltIsTrue_Expr_Expr_value = left.constant().floatValue() < right.constant().floatValue();
    return ltIsTrue_Expr_Expr_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:536
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:536")
  public boolean leIsTrue(Expr left, Expr right) {
    boolean leIsTrue_Expr_Expr_value = left.constant().floatValue() <= right.constant().floatValue();
    return leIsTrue_Expr_Expr_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:209
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:209")
  public boolean isFloat() {
    boolean isFloat_value = true;
    return isFloat_value;
  }
  /** @apilevel internal */
  private void boxed_reset() {
    boxed_computed = null;
    boxed_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle boxed_computed = null;

  /** @apilevel internal */
  protected TypeDecl boxed_value;

  /** Mapping between Primitive type and corresponding boxed Reference type. 
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:53
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:53")
  public TypeDecl boxed() {
    ASTNode$State state = state();
    if (boxed_computed == ASTNode$State.NON_CYCLE || boxed_computed == state().cycle()) {
      return boxed_value;
    }
    boxed_value = lookupType("java.lang", "Float");
    if (state().inCircle()) {
      boxed_computed = state().cycle();
    
    } else {
      boxed_computed = ASTNode$State.NON_CYCLE;
    
    }
    return boxed_value;
  }
  /**
   * @attribute syn
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:303
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CodeGeneration", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:303")
  public byte arrayLoad() {
    byte arrayLoad_value = Bytecode.FALOAD;
    return arrayLoad_value;
  }
  /**
   * @attribute syn
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:457
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CodeGeneration", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:457")
  public byte arrayStore() {
    byte arrayStore_value = Bytecode.FASTORE;
    return arrayStore_value;
  }
  /** @apilevel internal */
  private void typeDescriptor_reset() {
    typeDescriptor_computed = null;
    typeDescriptor_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle typeDescriptor_computed = null;

  /** @apilevel internal */
  protected String typeDescriptor_value;

  /**
   * @attribute syn
   * @aspect ConstantPoolNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/ConstantPoolNames.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantPoolNames", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/ConstantPoolNames.jrag:78")
  public String typeDescriptor() {
    ASTNode$State state = state();
    if (typeDescriptor_computed == ASTNode$State.NON_CYCLE || typeDescriptor_computed == state().cycle()) {
      return typeDescriptor_value;
    }
    typeDescriptor_value = "F";
    if (state().inCircle()) {
      typeDescriptor_computed = state().cycle();
    
    } else {
      typeDescriptor_computed = ASTNode$State.NON_CYCLE;
    
    }
    return typeDescriptor_value;
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1042
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1042")
  public int arrayPrimitiveTypeDescriptor() {
    int arrayPrimitiveTypeDescriptor_value = 6;
    return arrayPrimitiveTypeDescriptor_value;
  }
  /** @apilevel internal */
  private void jvmName_reset() {
    jvmName_computed = null;
    jvmName_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle jvmName_computed = null;

  /** @apilevel internal */
  protected String jvmName_value;

  /**
   * @attribute syn
   * @aspect Java2Rewrites
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Java2Rewrites.jrag:37
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java2Rewrites", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Java2Rewrites.jrag:37")
  public String jvmName() {
    ASTNode$State state = state();
    if (jvmName_computed == ASTNode$State.NON_CYCLE || jvmName_computed == state().cycle()) {
      return jvmName_value;
    }
    jvmName_value = "F";
    if (state().inCircle()) {
      jvmName_computed = state().cycle();
    
    } else {
      jvmName_computed = ASTNode$State.NON_CYCLE;
    
    }
    return jvmName_value;
  }
  /**
   * @attribute syn
   * @aspect Java2Rewrites
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Java2Rewrites.jrag:72
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java2Rewrites", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Java2Rewrites.jrag:72")
  public String primitiveClassName() {
    String primitiveClassName_value = "Float";
    return primitiveClassName_value;
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
