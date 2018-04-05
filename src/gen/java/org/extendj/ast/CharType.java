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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/grammar/Java.ast:148
 * @astdecl CharType : IntegralType;
 * @production CharType : {@link IntegralType};

 */
public class CharType extends IntegralType implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/PrettyPrint.jadd:136
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("char");
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:261
   */
  public void emitArrayLoad(ASTNode node, CodeGeneration gen)      {
		gen.addPositionEntryAtCurrentPC(node);
		gen.CALOAD();
	}
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:364
   */
  public void emitArrayStore(CodeGeneration gen)       { gen.CASTORE(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:509
   */
  void emitCastTo(CodeGeneration gen, TypeDecl type)      { type.charToThis(gen); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:519
   */
  void intToThis(CodeGeneration gen)   { gen.I2C(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:526
   */
  void floatToThis(CodeGeneration gen)   { gen.F2I(); gen.I2C(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:535
   */
  void doubleToThis(CodeGeneration gen)   { gen.D2I(); gen.I2C(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:544
   */
  void longToThis(CodeGeneration gen)   { gen.L2I(); gen.I2C(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:551
   */
  void byteToThis(CodeGeneration gen)     { gen.I2C(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:567
   */
  void shortToThis(CodeGeneration gen)     { gen.I2C(); }
  /**
   * @declaredat ASTNode:1
   */
  public CharType() {
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
  public CharType(Modifiers p0, String p1, Opt<Access> p2, List<BodyDecl> p3) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
  }
  /**
   * @declaredat ASTNode:26
   */
  public CharType(Modifiers p0, beaver.Symbol p1, Opt<Access> p2, List<BodyDecl> p3) {
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
    narrowingConversionTo_TypeDecl_reset();
    unaryNumericPromotion_reset();
    boxed_reset();
    typeDescriptor_reset();
    jvmName_reset();
    verificationType_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:53
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:57
   */
  public CharType clone() throws CloneNotSupportedException {
    CharType node = (CharType) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:62
   */
  public CharType copy() {
    try {
      CharType node = (CharType) clone();
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
   * @declaredat ASTNode:81
   */
  @Deprecated
  public CharType fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:91
   */
  public CharType treeCopyNoTransform() {
    CharType tree = (CharType) copy();
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
   * @declaredat ASTNode:111
   */
  public CharType treeCopy() {
    CharType tree = (CharType) copy();
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
   * @declaredat ASTNode:125
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node) && (tokenString_ID == ((CharType) node).tokenString_ID);    
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ConstantExpression.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ConstantExpression.jrag:95")
  public Constant cast(Constant c) {
    Constant cast_Constant_value = Constant.create((char) c.intValue());
    return cast_Constant_value;
  }
  /** @apilevel internal */
  private void narrowingConversionTo_TypeDecl_reset() {
    narrowingConversionTo_TypeDecl_computed = null;
    narrowingConversionTo_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map narrowingConversionTo_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map narrowingConversionTo_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect TypeConversion
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeConversion", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:39")
  public boolean narrowingConversionTo(TypeDecl type) {
    Object _parameters = type;
    if (narrowingConversionTo_TypeDecl_computed == null) narrowingConversionTo_TypeDecl_computed = new java.util.HashMap(4);
    if (narrowingConversionTo_TypeDecl_values == null) narrowingConversionTo_TypeDecl_values = new java.util.HashMap(4);
    ASTState state = state();
    if (narrowingConversionTo_TypeDecl_values.containsKey(_parameters)
        && narrowingConversionTo_TypeDecl_computed.containsKey(_parameters)
        && (narrowingConversionTo_TypeDecl_computed.get(_parameters) == ASTState.NON_CYCLE || narrowingConversionTo_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) narrowingConversionTo_TypeDecl_values.get(_parameters);
    }
    boolean narrowingConversionTo_TypeDecl_value = type.isByte() || type.isShort();
    if (state().inCircle()) {
      narrowingConversionTo_TypeDecl_values.put(_parameters, narrowingConversionTo_TypeDecl_value);
      narrowingConversionTo_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      narrowingConversionTo_TypeDecl_values.put(_parameters, narrowingConversionTo_TypeDecl_value);
      narrowingConversionTo_TypeDecl_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return narrowingConversionTo_TypeDecl_value;
  }
  /** @apilevel internal */
  private void unaryNumericPromotion_reset() {
    unaryNumericPromotion_computed = null;
    unaryNumericPromotion_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle unaryNumericPromotion_computed = null;

  /** @apilevel internal */
  protected TypeDecl unaryNumericPromotion_value;

  /**
   * @attribute syn
   * @aspect NumericPromotion
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:159
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NumericPromotion", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:159")
  public TypeDecl unaryNumericPromotion() {
    ASTState state = state();
    if (unaryNumericPromotion_computed == ASTState.NON_CYCLE || unaryNumericPromotion_computed == state().cycle()) {
      return unaryNumericPromotion_value;
    }
    unaryNumericPromotion_value = typeInt();
    if (state().inCircle()) {
      unaryNumericPromotion_computed = state().cycle();
    
    } else {
      unaryNumericPromotion_computed = ASTState.NON_CYCLE;
    
    }
    return unaryNumericPromotion_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:200
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:200")
  public boolean isChar() {
    boolean isChar_value = true;
    return isChar_value;
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:53
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:53")
  public TypeDecl boxed() {
    ASTState state = state();
    if (boxed_computed == ASTState.NON_CYCLE || boxed_computed == state().cycle()) {
      return boxed_value;
    }
    boxed_value = lookupType("java.lang", "Character");
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/ConstantPoolNames.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantPoolNames", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/ConstantPoolNames.jrag:78")
  public String typeDescriptor() {
    ASTState state = state();
    if (typeDescriptor_computed == ASTState.NON_CYCLE || typeDescriptor_computed == state().cycle()) {
      return typeDescriptor_value;
    }
    typeDescriptor_value = "C";
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CreateBCode.jrag:1016
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CreateBCode.jrag:1016")
  public int arrayPrimitiveTypeDescriptor() {
    int arrayPrimitiveTypeDescriptor_value = 5;
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/Java2Rewrites.jrag:37
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java2Rewrites", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/Java2Rewrites.jrag:37")
  public String jvmName() {
    ASTState state = state();
    if (jvmName_computed == ASTState.NON_CYCLE || jvmName_computed == state().cycle()) {
      return jvmName_value;
    }
    jvmName_value = "C";
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/Java2Rewrites.jrag:75
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java2Rewrites", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/Java2Rewrites.jrag:75")
  public String primitiveClassName() {
    String primitiveClassName_value = "Character";
    return primitiveClassName_value;
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/VerificationTypes.jrag:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VerificationTypes", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/VerificationTypes.jrag:42")
  public VerificationType verificationType() {
    ASTState state = state();
    if (verificationType_computed == ASTState.NON_CYCLE || verificationType_computed == state().cycle()) {
      return verificationType_value;
    }
    verificationType_value = VerificationTypes.INT;
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
