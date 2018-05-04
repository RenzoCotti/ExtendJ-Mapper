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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/grammar/Java.ast:41
 * @production ReferenceType : {@link TypeDecl};

 */
public abstract class ReferenceType extends TypeDecl implements Cloneable {
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:295
   */
  public void emitReturn(ASTNode<ASTNode> node, CodeGeneration gen) {
    gen.emit(node, Bytecode.ARETURN);
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:397
   */
  public void emitLoadLocal(ASTNode<ASTNode> node, CodeGeneration gen, int pos) {
    gen.emitLoadReference(node, pos);
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:564
   */
  public void emitStoreLocal(ASTNode<ASTNode> node, CodeGeneration gen, int pos) {
    gen.emitStoreReference(node, pos);
  }
  /**
   * @aspect CodeGenerationConversions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:715
   */
  void refined_CodeGenerationConversions_ReferenceType_emitCastTo(ASTNode<ASTNode> node, CodeGeneration gen, TypeDecl type) {
    if (!instanceOf(type) && !type.isNull()) {
      gen.emitCheckCast(node, type);
    }
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1409
   */
  public void branchEQ(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emitCompare(node, Bytecode.IF_ACMPEQ, label);
  }
  /**
   * @aspect CodeGenerationBranch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:1441
   */
  public void branchNE(ASTNode<ASTNode> node, CodeGeneration gen, int label) {
    gen.emitCompare(node, Bytecode.IF_ACMPNE, label);
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AutoBoxingCodegen.jrag:41
   */
  void byteToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeByte().boxed().byteToThis(node, gen);
    } else {
      unboxed().byteToThis(node, gen);
      emitBoxingOperation(node, gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AutoBoxingCodegen.jrag:53
   */
  void charToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeChar().boxed().charToThis(node, gen);
    } else {
      unboxed().charToThis(node, gen);
      emitBoxingOperation(node, gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AutoBoxingCodegen.jrag:65
   */
  void shortToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeShort().boxed().shortToThis(node, gen);
    } else {
      unboxed().shortToThis(node, gen);
      emitBoxingOperation(node, gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AutoBoxingCodegen.jrag:77
   */
  void intToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeInt().boxed().intToThis(node, gen);
    } else {
      unboxed().intToThis(node, gen);
      emitBoxingOperation(node, gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AutoBoxingCodegen.jrag:89
   */
  void longToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeLong().boxed().longToThis(node, gen);
    } else {
      unboxed().longToThis(node, gen);
      emitBoxingOperation(node, gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AutoBoxingCodegen.jrag:101
   */
  void floatToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeFloat().boxed().floatToThis(node, gen);
    } else {
      unboxed().floatToThis(node, gen);
      emitBoxingOperation(node, gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AutoBoxingCodegen.jrag:113
   */
  void doubleToThis(ASTNode<ASTNode> node, CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeDouble().boxed().doubleToThis(node, gen);
    } else {
      unboxed().doubleToThis(node, gen);
      emitBoxingOperation(node, gen);
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public ReferenceType() {
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
    setChild(new List(), 1);
  }
  /**
   * @declaredat ASTNode:14
   */
  public ReferenceType(Modifiers p0, String p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:19
   */
  public ReferenceType(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:25
   */
  protected int numChildren() {
    return 2;
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
    narrowingConversionTo_TypeDecl_reset();
    unboxed_reset();
    fieldDeclarations_reset();
    jvmName_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public ReferenceType clone() throws CloneNotSupportedException {
    ReferenceType node = (ReferenceType) super.clone();
    return node;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:58
   */
  @Deprecated
  public abstract ReferenceType fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:66
   */
  public abstract ReferenceType treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:74
   */
  public abstract ReferenceType treeCopy();
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
   * Replaces the BodyDecl list.
   * @param list The new list node to be used as the BodyDecl list.
   * @apilevel high-level
   */
  public void setBodyDeclList(List<BodyDecl> list) {
    setChild(list, 1);
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
    List<BodyDecl> list = (List<BodyDecl>) getChild(1);
    return list;
  }
  /**
   * Retrieves the BodyDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the BodyDecl list.
   * @apilevel low-level
   */
  public List<BodyDecl> getBodyDeclListNoTransform() {
    return (List<BodyDecl>) getChildNoTransform(1);
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
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AutoBoxingCodegen.jrag:135
   */
   
  public void emitCastTo(ASTNode<ASTNode> node, CodeGeneration gen, TypeDecl type) {
    if (type instanceof PrimitiveType) {
      if (unboxed().isUnknown()) {
        emitCastTo(node, gen, type.boxed());
        type.boxed().emitUnboxingOperation(node, gen);
      } else {
        emitUnboxingOperation(node, gen);
        unboxed().emitCastTo(node, gen, type);
      }
    } else {
      refined_CodeGenerationConversions_ReferenceType_emitCastTo(node, gen, type);
    }
  }
  /**
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:335
   */
  private Collection<FieldDeclarator> refined_GenerateClassfile_ReferenceType_fieldDeclarations()
{
    Collection<FieldDeclarator> fields = new ArrayList<FieldDeclarator>();
    for (BodyDecl decl : getBodyDeclList()) {
      if (decl.isField()) {
        fields.addAll(decl.fieldDeclarations());
      }
    }
    if (hasAssertStatement()) {
      fields.add(assertionsDisabled());
    }
    return fields;
  }
  /**
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:431
   */
  private Collection<BodyDecl> refined_GenerateClassfile_ReferenceType_methodsAndConstructors()
{
    Collection<BodyDecl> methods = new ArrayList<BodyDecl>();
    for (BodyDecl decl : getBodyDeclList()) {
      if (decl.isMethodOrConstructor()) {
        methods.add(decl);
      }
    }
    methods.addAll(accessors());
    return methods;
  }
  /**
   * @attribute syn
   * @aspect TypeConversion
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeConversion", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:38")
  public boolean wideningConversionTo(TypeDecl type) {
    boolean wideningConversionTo_TypeDecl_value = instanceOf(type);
    return wideningConversionTo_TypeDecl_value;
  }
  /** @apilevel internal */
  private void narrowingConversionTo_TypeDecl_reset() {
    narrowingConversionTo_TypeDecl_computed = new java.util.HashMap(4);
    narrowingConversionTo_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map narrowingConversionTo_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map narrowingConversionTo_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect TypeConversion
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeConversion", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:39")
  public boolean narrowingConversionTo(TypeDecl type) {
    Object _parameters = type;
    if (narrowingConversionTo_TypeDecl_computed == null) narrowingConversionTo_TypeDecl_computed = new java.util.HashMap(4);
    if (narrowingConversionTo_TypeDecl_values == null) narrowingConversionTo_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (narrowingConversionTo_TypeDecl_values.containsKey(_parameters) && narrowingConversionTo_TypeDecl_computed != null
        && narrowingConversionTo_TypeDecl_computed.containsKey(_parameters)
        && (narrowingConversionTo_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || narrowingConversionTo_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) narrowingConversionTo_TypeDecl_values.get(_parameters);
    }
    boolean narrowingConversionTo_TypeDecl_value = narrowingConversionTo_compute(type);
    if (state().inCircle()) {
      narrowingConversionTo_TypeDecl_values.put(_parameters, narrowingConversionTo_TypeDecl_value);
      narrowingConversionTo_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      narrowingConversionTo_TypeDecl_values.put(_parameters, narrowingConversionTo_TypeDecl_value);
      narrowingConversionTo_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return narrowingConversionTo_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean narrowingConversionTo_compute(TypeDecl type) {
      if (type.instanceOf(this)) {
        return true;
      }
      if (isClassDecl() && !getModifiers().isFinal() && type.isInterfaceDecl()) {
        return true;
      }
      if (isInterfaceDecl() && type.isClassDecl() && !type.getModifiers().isFinal()) {
        return true;
      }
      if (isInterfaceDecl() && type.instanceOf(this)) {
        return true;
      }
      if (fullName().equals("java.lang.Object") && type.isInterfaceDecl()) {
        return true;
      }
      // Dragons
      // TODO: Check if both are interfaces with compatible methods
      if (isArrayDecl() && type.isArrayDecl() && elementType().instanceOf(type.elementType())) {
        return true;
      }
      return false;
    }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:178
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:178")
  public boolean isReferenceType() {
    boolean isReferenceType_value = true;
    return isReferenceType_value;
  }
  /**
   * @attribute syn
   * @aspect TypeWideningAndIdentity
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:530
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeWideningAndIdentity", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:530")
  public boolean isSupertypeOfNullType(NullType type) {
    boolean isSupertypeOfNullType_NullType_value = true;
    return isSupertypeOfNullType_NullType_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:199
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:199")
  public boolean isValidAnnotationMethodReturnType() {
    {
        if (isString()) {
          return true;
        }
        if (fullName().equals("java.lang.Class")) {
          return true;
        }
        // Include generic versions of Class.
        if (erasure().fullName().equals("java.lang.Class")) {
          return true;
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:73
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:73")
  public boolean unboxingConversionTo(TypeDecl typeDecl) {
    boolean unboxingConversionTo_TypeDecl_value = unboxed() == typeDecl;
    return unboxingConversionTo_TypeDecl_value;
  }
  /** @apilevel internal */
  private void unboxed_reset() {
    unboxed_computed = null;
    unboxed_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle unboxed_computed = null;

  /** @apilevel internal */
  protected TypeDecl unboxed_value;

  /** Mapping between Reference type and corresponding unboxed Primitive type. 
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:77
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:77")
  public TypeDecl unboxed() {
    ASTNode$State state = state();
    if (unboxed_computed == ASTNode$State.NON_CYCLE || unboxed_computed == state().cycle()) {
      return unboxed_value;
    }
    unboxed_value = unboxed_compute();
    if (state().inCircle()) {
      unboxed_computed = state().cycle();
    
    } else {
      unboxed_computed = ASTNode$State.NON_CYCLE;
    
    }
    return unboxed_value;
  }
  /** @apilevel internal */
  private TypeDecl unboxed_compute() {
      if (packageName().equals("java.lang") && isTopLevelType()) {
        String n = name();
        if (n.equals("Boolean")) {
          return typeBoolean();
        }
        if (n.equals("Byte")) {
          return typeByte();
        }
        if (n.equals("Character")) {
          return typeChar();
        }
        if (n.equals("Short")) {
          return typeShort();
        }
        if (n.equals("Integer")) {
          return typeInt();
        }
        if (n.equals("Long")) {
          return typeLong();
        }
        if (n.equals("Float")) {
          return typeFloat();
        }
        if (n.equals("Double")) {
          return typeDouble();
        }
      }
      return unknownType();
    }
  /**
   * @attribute syn
   * @aspect NumericPromotion
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:157
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NumericPromotion", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:157")
  public TypeDecl unaryNumericPromotion() {
    TypeDecl unaryNumericPromotion_value = isNumericType() && !isUnknown() ? unboxed().unaryNumericPromotion() : this;
    return unaryNumericPromotion_value;
  }
  /**
   * @attribute syn
   * @aspect NumericPromotion
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:166
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NumericPromotion", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:166")
  public TypeDecl binaryNumericPromotion(TypeDecl type) {
    TypeDecl binaryNumericPromotion_TypeDecl_value = unboxed().binaryNumericPromotion(type);
    return binaryNumericPromotion_TypeDecl_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:187
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:187")
  public boolean isNumericType() {
    boolean isNumericType_value = !unboxed().isUnknown() && unboxed().isNumericType();
    return isNumericType_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:191
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:191")
  public boolean isIntegralType() {
    boolean isIntegralType_value = !unboxed().isUnknown() && unboxed().isIntegralType();
    return isIntegralType_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:237
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:237")
  public boolean isPrimitive() {
    boolean isPrimitive_value = !unboxed().isUnknown() && unboxed().isPrimitive();
    return isPrimitive_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:195
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:195")
  public boolean isBoolean() {
    boolean isBoolean_value = fullName().equals("java.lang.Boolean") && unboxed().isBoolean();
    return isBoolean_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsSubtype.jrag:577
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsSubtype.jrag:577")
  public boolean supertypeNullType(NullType type) {
    boolean supertypeNullType_NullType_value = true;
    return supertypeNullType_NullType_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/GenericsSubtype.jrag:454
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/GenericsSubtype.jrag:454")
  public boolean strictSupertypeNullType(NullType type) {
    boolean strictSupertypeNullType_NullType_value = true;
    return strictSupertypeNullType_NullType_value;
  }
  /**
   * @attribute syn
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:303
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CodeGeneration", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:303")
  public byte arrayLoad() {
    byte arrayLoad_value = Bytecode.AALOAD;
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
    byte arrayStore_value = Bytecode.AASTORE;
    return arrayStore_value;
  }
  /** @apilevel internal */
  private void fieldDeclarations_reset() {
    fieldDeclarations_computed = null;
    fieldDeclarations_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle fieldDeclarations_computed = null;

  /** @apilevel internal */
  protected Collection<FieldDeclarator> fieldDeclarations_value;

  /** @return a collection of the fields declared in this type. 
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:333
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenerateClassfile", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:333")
  public Collection<FieldDeclarator> fieldDeclarations() {
    ASTNode$State state = state();
    if (fieldDeclarations_computed == ASTNode$State.NON_CYCLE || fieldDeclarations_computed == state().cycle()) {
      return fieldDeclarations_value;
    }
    fieldDeclarations_value = fieldDeclarations_compute();
    if (state().inCircle()) {
      fieldDeclarations_computed = state().cycle();
    
    } else {
      fieldDeclarations_computed = ASTNode$State.NON_CYCLE;
    
    }
    return fieldDeclarations_value;
  }
  /** @apilevel internal */
  private Collection<FieldDeclarator> fieldDeclarations_compute() {
      Collection<FieldDeclarator> fields = new ArrayList<FieldDeclarator>(refined_GenerateClassfile_ReferenceType_fieldDeclarations());
      // Add enum index fields:
      for (SwitchStmt enumSwitch : enumSwitchStatements()) {
        fields.add(enumArrayDecl(enumSwitch));
      }
      return fields;
    }
  /** @return a collection of the methods and constructors declared in this type. 
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:431
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenerateClassfile", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:431")
  public Collection<BodyDecl> methodsAndConstructors() {
    {
        Collection<BodyDecl> original = refined_GenerateClassfile_ReferenceType_methodsAndConstructors();
        Collection<BodyDecl> methods = new ArrayList<BodyDecl>();
        for (BodyDecl decl : original) {
          if (decl instanceof ConstructorDecl) {
            ConstructorDecl constructor = (ConstructorDecl) decl;
            methods.add(constructor.transformed());
          } else {
            methods.add(decl);
          }
        }
        // Add enum index methods:
        // TODO(joqvist): generate an anonymous class with initializer instead.
        for (SwitchStmt enumSwitch : enumSwitchStatements()) {
          methods.add(createEnumMethod(enumSwitch));
        }
        // Add bridge methods:
        methods.addAll(bridgeMethods());
        return methods;
      }
  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:122
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="InnerClasses", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:122")
  public TypeDecl stringPromotion() {
    TypeDecl stringPromotion_value = typeObject();
    return stringPromotion_value;
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
    jvmName_value = jvmName_compute();
    if (state().inCircle()) {
      jvmName_computed = state().cycle();
    
    } else {
      jvmName_computed = ASTNode$State.NON_CYCLE;
    
    }
    return jvmName_value;
  }
  /** @apilevel internal */
  private String jvmName_compute() {
      if (!isNestedType()) {
        return fullName();
      } else if (isAnonymous() || isLocalClass()) {
        return enclosingType().jvmName() + "$" + uniqueIndex() + name();
      } else {
        return enclosingType().jvmName() + "$" + name();
      }
    }
  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:198
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LocalNum", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:198")
  public int variableSize() {
    int variableSize_value = 1;
    return variableSize_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:110
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:110")
  public TypeDecl typeBoolean() {
    TypeDecl typeBoolean_value = getParent().Define_typeBoolean(this, null);
    return typeBoolean_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:112
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:112")
  public TypeDecl typeByte() {
    TypeDecl typeByte_value = getParent().Define_typeByte(this, null);
    return typeByte_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:114
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:114")
  public TypeDecl typeChar() {
    TypeDecl typeChar_value = getParent().Define_typeChar(this, null);
    return typeChar_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:116
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:116")
  public TypeDecl typeShort() {
    TypeDecl typeShort_value = getParent().Define_typeShort(this, null);
    return typeShort_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:118
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:118")
  public TypeDecl typeInt() {
    TypeDecl typeInt_value = getParent().Define_typeInt(this, null);
    return typeInt_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:120
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:120")
  public TypeDecl typeLong() {
    TypeDecl typeLong_value = getParent().Define_typeLong(this, null);
    return typeLong_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:122
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:122")
  public TypeDecl typeFloat() {
    TypeDecl typeFloat_value = getParent().Define_typeFloat(this, null);
    return typeFloat_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:124
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:124")
  public TypeDecl typeDouble() {
    TypeDecl typeDouble_value = getParent().Define_typeDouble(this, null);
    return typeDouble_value;
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
