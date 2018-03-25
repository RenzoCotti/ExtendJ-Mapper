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
 * An abstract reference type.
 * 
 * All reference types are represented by type declarations.
 * Concrete subclasses include class and interface declarations.
 * @ast node
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/grammar/Java.ast:124
 * @astdecl ReferenceType : TypeDecl;
 * @production ReferenceType : {@link TypeDecl};

 */
public abstract class ReferenceType extends TypeDecl implements Cloneable {
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:185
   */
  public void emitReturn(CodeGeneration gen) { gen.ARETURN(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:189
   */
  public void emitArrayLoad(CodeGeneration gen) { gen.AALOAD(this); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:204
   */
  public void emitLoadLocal(CodeGeneration gen, int pos)  { gen.ALOAD(pos, this); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:259
   */
  public void emitArrayStore(CodeGeneration gen)  { gen.AASTORE(); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:286
   */
  public void emitStoreLocal(CodeGeneration gen, int pos) { gen.ASTORE(pos, this); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:606
   */
  public void branchEQ(CodeGeneration gen, int label) { gen.IF_ACMPEQ(label); }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CodeGeneration.jrag:617
   */
  public void branchNE(CodeGeneration gen, int label) { gen.IF_ACMPNE(label); }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/backend/AutoBoxingCodegen.jrag:45
   */
  void byteToThis(CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeByte().boxed().byteToThis(gen);
    } else {
      unboxed().byteToThis(gen);
      emitBoxingOperation(gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/backend/AutoBoxingCodegen.jrag:57
   */
  void charToThis(CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeChar().boxed().charToThis(gen);
    } else {
      unboxed().charToThis(gen);
      emitBoxingOperation(gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/backend/AutoBoxingCodegen.jrag:69
   */
  void shortToThis(CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeShort().boxed().shortToThis(gen);
    } else {
      unboxed().shortToThis(gen);
      emitBoxingOperation(gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/backend/AutoBoxingCodegen.jrag:81
   */
  void intToThis(CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeInt().boxed().intToThis(gen);
    } else {
      unboxed().intToThis(gen); // TODO(joqvist): is this redundant?
      emitBoxingOperation(gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/backend/AutoBoxingCodegen.jrag:93
   */
  void longToThis(CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeLong().boxed().longToThis(gen);
    } else {
      unboxed().longToThis(gen); // TODO(joqvist): is this redundant?
      emitBoxingOperation(gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/backend/AutoBoxingCodegen.jrag:105
   */
  void floatToThis(CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeFloat().boxed().floatToThis(gen);
    } else {
      unboxed().floatToThis(gen); // TODO(joqvist): is this redundant?
      emitBoxingOperation(gen);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/backend/AutoBoxingCodegen.jrag:117
   */
  void doubleToThis(CodeGeneration gen) {
    if (isUnknown()) {
      throw new Error("Trying to cast to Unknown");
    }
    if (!isNumericType()) {
      typeDouble().boxed().doubleToThis(gen);
    } else {
      unboxed().doubleToThis(gen); // TODO(joqvist): is this redundant?
      emitBoxingOperation(gen);
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
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "ID", "BodyDecl"},
    type = {"Modifiers", "String", "List<BodyDecl>"},
    kind = {"Child", "Token", "List"}
  )
  public ReferenceType(Modifiers p0, String p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:24
   */
  public ReferenceType(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:30
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:36
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:40
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    narrowingConversionTo_TypeDecl_reset();
    unboxed_reset();
    fieldDeclarations_reset();
    jvmName_reset();
    verificationType_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:49
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:53
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
   * @declaredat ASTNode:64
   */
  @Deprecated
  public abstract ReferenceType fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:72
   */
  public abstract ReferenceType treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:80
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/backend/AutoBoxingCodegen.jrag:139
   */
   
  public void emitCastTo(CodeGeneration gen, TypeDecl type) {
    if (type instanceof PrimitiveType) {
      if (unboxed().isUnknown()) {
        emitCastTo(gen, type.boxed());
        type.boxed().emitUnboxingOperation(gen);
      } else {
        emitUnboxingOperation(gen);
        unboxed().emitCastTo(gen, type);
      }
    } else {
      type = type.erasure(); // Erase the target type!
      if (!instanceOf(type) && !type.isNull()) {
        gen.CHECKCAST(type);
      }
    }
  }
  /**
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:328
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:424
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeConversion", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:38")
  public boolean wideningConversionTo(TypeDecl type) {
    boolean wideningConversionTo_TypeDecl_value = instanceOf(type);
    return wideningConversionTo_TypeDecl_value;
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
    boolean narrowingConversionTo_TypeDecl_value = narrowingConversionTo_compute(type);
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:177
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:177")
  public boolean isReferenceType() {
    boolean isReferenceType_value = true;
    return isReferenceType_value;
  }
  /**
   * @attribute syn
   * @aspect TypeWideningAndIdentity
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:526
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeWideningAndIdentity", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:526")
  public boolean isSupertypeOfNullType(NullType type) {
    boolean isSupertypeOfNullType_NullType_value = true;
    return isSupertypeOfNullType_NullType_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:199
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:199")
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:73
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:73")
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
  protected ASTState.Cycle unboxed_computed = null;

  /** @apilevel internal */
  protected TypeDecl unboxed_value;

  /** Mapping between Reference type and corresponding unboxed Primitive type. 
   * @attribute syn
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:77
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:77")
  public TypeDecl unboxed() {
    ASTState state = state();
    if (unboxed_computed == ASTState.NON_CYCLE || unboxed_computed == state().cycle()) {
      return unboxed_value;
    }
    unboxed_value = unboxed_compute();
    if (state().inCircle()) {
      unboxed_computed = state().cycle();
    
    } else {
      unboxed_computed = ASTState.NON_CYCLE;
    
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:156
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NumericPromotion", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:156")
  public TypeDecl unaryNumericPromotion() {
    TypeDecl unaryNumericPromotion_value = isNumericType() && !isUnknown() ? unboxed().unaryNumericPromotion() : this;
    return unaryNumericPromotion_value;
  }
  /**
   * @attribute syn
   * @aspect NumericPromotion
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:165
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NumericPromotion", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:165")
  public TypeDecl binaryNumericPromotion(TypeDecl type) {
    TypeDecl binaryNumericPromotion_TypeDecl_value = unboxed().binaryNumericPromotion(type);
    return binaryNumericPromotion_TypeDecl_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:186
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:186")
  public boolean isNumericType() {
    boolean isNumericType_value = !unboxed().isUnknown() && unboxed().isNumericType();
    return isNumericType_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:190
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:190")
  public boolean isIntegralType() {
    boolean isIntegralType_value = !unboxed().isUnknown() && unboxed().isIntegralType();
    return isIntegralType_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:236
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:236")
  public boolean isPrimitive() {
    boolean isPrimitive_value = !unboxed().isUnknown() && unboxed().isPrimitive();
    return isPrimitive_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:194
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:194")
  public boolean isBoolean() {
    boolean isBoolean_value = fullName().equals("java.lang.Boolean") && unboxed().isBoolean();
    return isBoolean_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/GenericsSubtype.jrag:576
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/GenericsSubtype.jrag:576")
  public boolean supertypeNullType(NullType type) {
    boolean supertypeNullType_NullType_value = true;
    return supertypeNullType_NullType_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java8/frontend/GenericsSubtype.jrag:451
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java8/frontend/GenericsSubtype.jrag:451")
  public boolean strictSupertypeNullType(NullType type) {
    boolean strictSupertypeNullType_NullType_value = true;
    return strictSupertypeNullType_NullType_value;
  }
  /** @apilevel internal */
  private void fieldDeclarations_reset() {
    fieldDeclarations_computed = null;
    fieldDeclarations_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle fieldDeclarations_computed = null;

  /** @apilevel internal */
  protected Collection<FieldDeclarator> fieldDeclarations_value;

  /** @return a collection of the fields declared in this type. 
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:326
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenerateClassfile", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:326")
  public Collection<FieldDeclarator> fieldDeclarations() {
    ASTState state = state();
    if (fieldDeclarations_computed == ASTState.NON_CYCLE || fieldDeclarations_computed == state().cycle()) {
      return fieldDeclarations_value;
    }
    fieldDeclarations_value = fieldDeclarations_compute();
    if (state().inCircle()) {
      fieldDeclarations_computed = state().cycle();
    
    } else {
      fieldDeclarations_computed = ASTState.NON_CYCLE;
    
    }
    return fieldDeclarations_value;
  }
  /** @apilevel internal */
  private Collection<FieldDeclarator> fieldDeclarations_compute() {
      Collection<FieldDeclarator> fields = new ArrayList<FieldDeclarator>(refined_GenerateClassfile_ReferenceType_fieldDeclarations());
      // Add enum index fields:
      Set<String> enumTypes = new HashSet<String>();
      for (SwitchStmt enumSwitch : enumSwitchStatements()) {
        TypeDecl enumType = enumSwitch.getExpr().type();
        if (enumTypes.add(enumType.fullName())) {
          // This is a new enum type.
          fields.add(enumArrayDecl(enumType));
        }
      }
      return fields;
    }
  /** @return a collection of the methods and constructors declared in this type. 
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:424
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenerateClassfile", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:424")
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
        Set<String> enumTypes = new HashSet<String>();
        for (SwitchStmt enumSwitch : enumSwitchStatements()) {
          TypeDecl enumType = enumSwitch.getExpr().type();
          if (enumTypes.add(enumType.fullName())) {
            // This is a new enum type.
            methods.add(createEnumMethod(enumType));
          }
        }
        // Add bridge methods:
        methods.addAll(bridgeMethods());
        return methods;
      }
  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/InnerClasses.jrag:122
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="InnerClasses", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/InnerClasses.jrag:122")
  public TypeDecl stringPromotion() {
    TypeDecl stringPromotion_value = isType("java.lang", "String") ? this : typeObject();
    return stringPromotion_value;
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
    jvmName_value = jvmName_compute();
    if (state().inCircle()) {
      jvmName_computed = state().cycle();
    
    } else {
      jvmName_computed = ASTState.NON_CYCLE;
    
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
    verificationType_value = verificationType_compute();
    if (state().inCircle()) {
      verificationType_computed = state().cycle();
    
    } else {
      verificationType_computed = ASTState.NON_CYCLE;
    
    }
    return verificationType_value;
  }
  /** @apilevel internal */
  private VerificationType verificationType_compute() {
      String cpname = constantPoolName();
      if (cpname.equals("java/lang/Object")) {
        return VerificationTypes.OBJECT;
      } else if (cpname.equals("java/lang/String")) {
        return VerificationTypes.STRING;
      } else if (cpname.equals("java/lang/Throwable")) {
        return VerificationTypes.THROWABLE;
      } else if (cpname.equals("java/lang/Class")) {
        return VerificationTypes.CLASS;
      } else {
        return new VerificationTypes.JavaType(this, supertype());
      }
    }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:110
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:110")
  public TypeDecl typeBoolean() {
    TypeDecl typeBoolean_value = getParent().Define_typeBoolean(this, null);
    return typeBoolean_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:112
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:112")
  public TypeDecl typeByte() {
    TypeDecl typeByte_value = getParent().Define_typeByte(this, null);
    return typeByte_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:114
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:114")
  public TypeDecl typeChar() {
    TypeDecl typeChar_value = getParent().Define_typeChar(this, null);
    return typeChar_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:116
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:116")
  public TypeDecl typeShort() {
    TypeDecl typeShort_value = getParent().Define_typeShort(this, null);
    return typeShort_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:118
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:118")
  public TypeDecl typeInt() {
    TypeDecl typeInt_value = getParent().Define_typeInt(this, null);
    return typeInt_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:120
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:120")
  public TypeDecl typeLong() {
    TypeDecl typeLong_value = getParent().Define_typeLong(this, null);
    return typeLong_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:122
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:122")
  public TypeDecl typeFloat() {
    TypeDecl typeFloat_value = getParent().Define_typeFloat(this, null);
    return typeFloat_value;
  }
  /**
   * @attribute inh
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:124
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AutoBoxing", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/AutoBoxing.jrag:124")
  public TypeDecl typeDouble() {
    TypeDecl typeDouble_value = getParent().Define_typeDouble(this, null);
    return typeDouble_value;
  }
  /**
   * @attribute inh
   * @aspect VerificationTypes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/VerificationTypes.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="VerificationTypes", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/VerificationTypes.jrag:35")
  public Program program() {
    Program program_value = getParent().Define_program(this, null);
    return program_value;
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
