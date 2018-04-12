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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/grammar/Generics.ast:38
 * @production AbstractWildcardType : {@link TypeDecl};

 */
public abstract class AbstractWildcardType extends TypeDecl implements Cloneable {
  /**
   * Qualified access for wildcard types.  Since they have no ordinary package
   * and type name we just return the bound access.
   * @aspect Generics
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:38
   */
  public Access createQualifiedAccess() {
    return createBoundAccess();
  }
  /**
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:638
   */
  void emitCastTo(CodeGeneration gen, TypeDecl type) {
    erasure().emitCastTo(this, gen, type);
  }
  /**
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:642
   */
  public void branchLT(CodeGeneration gen, int label) {
    erasure().branchLT(this, gen, label);
  }
  /**
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:646
   */
  public void branchLE(CodeGeneration gen, int label) {
    erasure().branchLE(this, gen, label);
  }
  /**
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:650
   */
  public void branchGE(CodeGeneration gen, int label) {
    erasure().branchGE(this, gen, label);
  }
  /**
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:654
   */
  public void branchGT(CodeGeneration gen, int label) {
    erasure().branchGT(this, gen, label);
  }
  /**
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:658
   */
  public void branchEQ(CodeGeneration gen, int label) {
    erasure().branchEQ(this, gen, label);
  }
  /**
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:662
   */
  public void branchNE(CodeGeneration gen, int label) {
    erasure().branchNE(this, gen, label);
  }
  /**
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:666
   */
  public void emitLoadLocal(CodeGeneration gen, int pos) {
    erasure().emitLoadLocal(this, gen, pos);
  }
  /**
   * @declaredat ASTNode:1
   */
  public AbstractWildcardType() {
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
  public AbstractWildcardType(Modifiers p0, String p1, List<BodyDecl> p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:19
   */
  public AbstractWildcardType(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2) {
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
    castingConversionTo_TypeDecl_reset();
    typeDescriptor_reset();
    needsSignatureAttribute_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:42
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:46
   */
  public AbstractWildcardType clone() throws CloneNotSupportedException {
    AbstractWildcardType node = (AbstractWildcardType) super.clone();
    return node;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:57
   */
  @Deprecated
  public abstract AbstractWildcardType fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:65
   */
  public abstract AbstractWildcardType treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:73
   */
  public abstract AbstractWildcardType treeCopy();
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
  /** @apilevel internal */
  private void castingConversionTo_TypeDecl_reset() {
    castingConversionTo_TypeDecl_computed = new java.util.HashMap(4);
    castingConversionTo_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map castingConversionTo_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map castingConversionTo_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect TypeConversion
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:100
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeConversion", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:100")
  public boolean castingConversionTo(TypeDecl type) {
    Object _parameters = type;
    if (castingConversionTo_TypeDecl_computed == null) castingConversionTo_TypeDecl_computed = new java.util.HashMap(4);
    if (castingConversionTo_TypeDecl_values == null) castingConversionTo_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (castingConversionTo_TypeDecl_values.containsKey(_parameters) && castingConversionTo_TypeDecl_computed != null
        && castingConversionTo_TypeDecl_computed.containsKey(_parameters)
        && (castingConversionTo_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || castingConversionTo_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) castingConversionTo_TypeDecl_values.get(_parameters);
    }
    boolean castingConversionTo_TypeDecl_value = erasure().castingConversionTo(type);
    if (state().inCircle()) {
      castingConversionTo_TypeDecl_values.put(_parameters, castingConversionTo_TypeDecl_value);
      castingConversionTo_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      castingConversionTo_TypeDecl_values.put(_parameters, castingConversionTo_TypeDecl_value);
      castingConversionTo_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return castingConversionTo_TypeDecl_value;
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
  /** @return {@code true} if this type is a wildcard type, possibly with type bounds. 
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsSubtype.jrag:120
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsSubtype.jrag:120")
  public boolean isWildcard() {
    boolean isWildcard_value = true;
    return isWildcard_value;
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
   * A type is reifiable if it either refers to a non-parameterized type,
   * is a raw type, is a parameterized type with only unbound wildcard
   * parameters or is an array type with a reifiable type parameter.
   * 
   * @see "JLS SE7 &sect;4.7"
   * @attribute syn
   * @aspect ReifiableTypes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/ReifiableTypes.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ReifiableTypes", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/ReifiableTypes.jrag:39")
  public boolean isReifiable() {
    boolean isReifiable_value = false;
    return isReifiable_value;
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
    typeDescriptor_value = erasure().typeDescriptor();
    if (state().inCircle()) {
      typeDescriptor_computed = state().cycle();
    
    } else {
      typeDescriptor_computed = ASTNode$State.NON_CYCLE;
    
    }
    return typeDescriptor_value;
  }
  /** @apilevel internal */
  private void needsSignatureAttribute_reset() {
    needsSignatureAttribute_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle needsSignatureAttribute_computed = null;

  /** @apilevel internal */
  protected boolean needsSignatureAttribute_value;

  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:433
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsCodegen", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:433")
  public boolean needsSignatureAttribute() {
    ASTNode$State state = state();
    if (needsSignatureAttribute_computed == ASTNode$State.NON_CYCLE || needsSignatureAttribute_computed == state().cycle()) {
      return needsSignatureAttribute_value;
    }
    needsSignatureAttribute_value = true;
    if (state().inCircle()) {
      needsSignatureAttribute_computed = state().cycle();
    
    } else {
      needsSignatureAttribute_computed = ASTNode$State.NON_CYCLE;
    
    }
    return needsSignatureAttribute_value;
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
