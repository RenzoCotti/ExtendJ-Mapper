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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/grammar/Generics.ast:55
 * @production InterfaceDeclSubstituted : {@link InterfaceDecl} ::= <span class="component">&lt;Original:TypeDecl&gt;</span>;

 */
public class InterfaceDeclSubstituted extends InterfaceDecl implements Cloneable, MemberSubstitutor {
  /**
   * @declaredat ASTNode:1
   */
  public InterfaceDeclSubstituted() {
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
    setChild(new List(), 1);
    setChild(new List(), 2);
  }
  /**
   * @declaredat ASTNode:15
   */
  public InterfaceDeclSubstituted(Modifiers p0, String p1, List<Access> p2, List<BodyDecl> p3, TypeDecl p4) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setOriginal(p4);
  }
  /**
   * @declaredat ASTNode:22
   */
  public InterfaceDeclSubstituted(Modifiers p0, beaver.Symbol p1, List<Access> p2, List<BodyDecl> p3, TypeDecl p4) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
    setChild(p3, 2);
    setOriginal(p4);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:30
   */
  protected int numChildren() {
    return 3;
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
    sourceTypeDecl_reset();
    instanceOf_TypeDecl_reset();
    subtype_TypeDecl_reset();
    strictSubtype_TypeDecl_reset();
    typeDescriptor_reset();
    constantPoolName_reset();
    uniqueIndex_reset();
    localMethodsSignatureMap_reset();
    localFields_String_reset();
    localTypeDecls_String_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:54
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:58
   */
  public InterfaceDeclSubstituted clone() throws CloneNotSupportedException {
    InterfaceDeclSubstituted node = (InterfaceDeclSubstituted) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:63
   */
  public InterfaceDeclSubstituted copy() {
    try {
      InterfaceDeclSubstituted node = (InterfaceDeclSubstituted) clone();
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
   * @declaredat ASTNode:82
   */
  @Deprecated
  public InterfaceDeclSubstituted fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:92
   */
  public InterfaceDeclSubstituted treeCopyNoTransform() {
    InterfaceDeclSubstituted tree = (InterfaceDeclSubstituted) copy();
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
   * @declaredat ASTNode:112
   */
  public InterfaceDeclSubstituted treeCopy() {
    InterfaceDeclSubstituted tree = (InterfaceDeclSubstituted) copy();
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
   * @declaredat ASTNode:126
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node) && (tokenString_ID == ((InterfaceDeclSubstituted) node).tokenString_ID) && (tokenTypeDecl_Original == ((InterfaceDeclSubstituted) node).tokenTypeDecl_Original);    
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
   * Replaces the SuperInterface list.
   * @param list The new list node to be used as the SuperInterface list.
   * @apilevel high-level
   */
  public void setSuperInterfaceList(List<Access> list) {
    setChild(list, 1);
  }
  /**
   * Retrieves the number of children in the SuperInterface list.
   * @return Number of children in the SuperInterface list.
   * @apilevel high-level
   */
  public int getNumSuperInterface() {
    return getSuperInterfaceList().getNumChild();
  }
  /**
   * Retrieves the number of children in the SuperInterface list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the SuperInterface list.
   * @apilevel low-level
   */
  public int getNumSuperInterfaceNoTransform() {
    return getSuperInterfaceListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the SuperInterface list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the SuperInterface list.
   * @apilevel high-level
   */
  public Access getSuperInterface(int i) {
    return (Access) getSuperInterfaceList().getChild(i);
  }
  /**
   * Check whether the SuperInterface list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasSuperInterface() {
    return getSuperInterfaceList().getNumChild() != 0;
  }
  /**
   * Append an element to the SuperInterface list.
   * @param node The element to append to the SuperInterface list.
   * @apilevel high-level
   */
  public void addSuperInterface(Access node) {
    List<Access> list = (parent == null) ? getSuperInterfaceListNoTransform() : getSuperInterfaceList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addSuperInterfaceNoTransform(Access node) {
    List<Access> list = getSuperInterfaceListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the SuperInterface list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setSuperInterface(Access node, int i) {
    List<Access> list = getSuperInterfaceList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the SuperInterface list.
   * @return The node representing the SuperInterface list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="SuperInterface")
  public List<Access> getSuperInterfaceList() {
    List<Access> list = (List<Access>) getChild(1);
    return list;
  }
  /**
   * Retrieves the SuperInterface list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the SuperInterface list.
   * @apilevel low-level
   */
  public List<Access> getSuperInterfaceListNoTransform() {
    return (List<Access>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the SuperInterface list without
   * triggering rewrites.
   */
  public Access getSuperInterfaceNoTransform(int i) {
    return (Access) getSuperInterfaceListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the SuperInterface list.
   * @return The node representing the SuperInterface list.
   * @apilevel high-level
   */
  public List<Access> getSuperInterfaces() {
    return getSuperInterfaceList();
  }
  /**
   * Retrieves the SuperInterface list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the SuperInterface list.
   * @apilevel low-level
   */
  public List<Access> getSuperInterfacesNoTransform() {
    return getSuperInterfaceListNoTransform();
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
   * Replaces the lexeme Original.
   * @param value The new value for the lexeme Original.
   * @apilevel high-level
   */
  public void setOriginal(TypeDecl value) {
    tokenTypeDecl_Original = value;
  }
  /** @apilevel internal 
   */
  protected TypeDecl tokenTypeDecl_Original;
  /**
   * Retrieves the value for the lexeme Original.
   * @return The value for the lexeme Original.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="Original")
  public TypeDecl getOriginal() {
    return tokenTypeDecl_Original;
  }
  /**
   * @attribute syn
   * @aspect NestedTypes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:643
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:643")
  public TypeDecl hostType() {
    TypeDecl hostType_value = getOriginal();
    return hostType_value;
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1530
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1530")
  public TypeDecl original() {
    TypeDecl original_value = getOriginal().original();
    return original_value;
  }
  /** @apilevel internal */
  private void sourceTypeDecl_reset() {
    sourceTypeDecl_computed = null;
    sourceTypeDecl_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle sourceTypeDecl_computed = null;

  /** @apilevel internal */
  protected TypeDecl sourceTypeDecl_value;

  /**
   * @attribute syn
   * @aspect SourceDeclarations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1722
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SourceDeclarations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1722")
  public TypeDecl sourceTypeDecl() {
    ASTNode$State state = state();
    if (sourceTypeDecl_computed == ASTNode$State.NON_CYCLE || sourceTypeDecl_computed == state().cycle()) {
      return sourceTypeDecl_value;
    }
    sourceTypeDecl_value = original().sourceTypeDecl();
    if (state().inCircle()) {
      sourceTypeDecl_computed = state().cycle();
    
    } else {
      sourceTypeDecl_computed = ASTNode$State.NON_CYCLE;
    
    }
    return sourceTypeDecl_value;
  }
  /** @apilevel internal */
  private void instanceOf_TypeDecl_reset() {
    instanceOf_TypeDecl_computed = new java.util.HashMap(4);
    instanceOf_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map instanceOf_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map instanceOf_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect TypeWideningAndIdentity
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:443
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeWideningAndIdentity", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:443")
  public boolean instanceOf(TypeDecl type) {
    Object _parameters = type;
    if (instanceOf_TypeDecl_computed == null) instanceOf_TypeDecl_computed = new java.util.HashMap(4);
    if (instanceOf_TypeDecl_values == null) instanceOf_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (instanceOf_TypeDecl_values.containsKey(_parameters) && instanceOf_TypeDecl_computed != null
        && instanceOf_TypeDecl_computed.containsKey(_parameters)
        && (instanceOf_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || instanceOf_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) instanceOf_TypeDecl_values.get(_parameters);
    }
    boolean instanceOf_TypeDecl_value = subtype(type);
    if (state().inCircle()) {
      instanceOf_TypeDecl_values.put(_parameters, instanceOf_TypeDecl_value);
      instanceOf_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      instanceOf_TypeDecl_values.put(_parameters, instanceOf_TypeDecl_value);
      instanceOf_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return instanceOf_TypeDecl_value;
  }
  /** @apilevel internal */
  private void subtype_TypeDecl_reset() {
    subtype_TypeDecl_values = null;
  }
  protected java.util.Map subtype_TypeDecl_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsSubtype.jrag:490")
  public boolean subtype(TypeDecl type) {
    Object _parameters = type;
    if (subtype_TypeDecl_values == null) subtype_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (subtype_TypeDecl_values.containsKey(_parameters)) {
      Object _cache = subtype_TypeDecl_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      subtype_TypeDecl_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_subtype_TypeDecl_value;
      do {
        _value.cycle = state.nextCycle();
        new_subtype_TypeDecl_value = type.supertypeInterfaceDeclSubstituted(this);
        if (new_subtype_TypeDecl_value != ((Boolean)_value.value)) {
          state.setChangeInCycle();
          _value.value = new_subtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      subtype_TypeDecl_values.put(_parameters, new_subtype_TypeDecl_value);

      state.leaveCircle();
      return new_subtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_subtype_TypeDecl_value = type.supertypeInterfaceDeclSubstituted(this);
      if (new_subtype_TypeDecl_value != ((Boolean)_value.value)) {
        state.setChangeInCycle();
        _value.value = new_subtype_TypeDecl_value;
      }
      return new_subtype_TypeDecl_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsSubtype.jrag:605
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsSubtype.jrag:605")
  public boolean supertypeInterfaceDeclSubstituted(InterfaceDeclSubstituted type) {
    boolean supertypeInterfaceDeclSubstituted_InterfaceDeclSubstituted_value = original() == type.original() && type.enclosingType().subtype(enclosingType())
          || super.supertypeInterfaceDeclSubstituted(type);
    return supertypeInterfaceDeclSubstituted_InterfaceDeclSubstituted_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsSubtype
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsSubtype.jrag:522
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsSubtype.jrag:522")
  public boolean supertypeInterfaceDecl(InterfaceDecl type) {
    boolean supertypeInterfaceDecl_InterfaceDecl_value = super.supertypeInterfaceDecl(type) || original().supertypeInterfaceDecl(type);
    return supertypeInterfaceDecl_InterfaceDecl_value;
  }
  /** @apilevel internal */
  private void strictSubtype_TypeDecl_reset() {
    strictSubtype_TypeDecl_values = null;
  }
  protected java.util.Map strictSubtype_TypeDecl_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/GenericsSubtype.jrag:363")
  public boolean strictSubtype(TypeDecl type) {
    Object _parameters = type;
    if (strictSubtype_TypeDecl_values == null) strictSubtype_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (strictSubtype_TypeDecl_values.containsKey(_parameters)) {
      Object _cache = strictSubtype_TypeDecl_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      strictSubtype_TypeDecl_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_strictSubtype_TypeDecl_value;
      do {
        _value.cycle = state.nextCycle();
        new_strictSubtype_TypeDecl_value = type.strictSupertypeInterfaceDeclSubstituted(this);
        if (new_strictSubtype_TypeDecl_value != ((Boolean)_value.value)) {
          state.setChangeInCycle();
          _value.value = new_strictSubtype_TypeDecl_value;
        }
      } while (state.testAndClearChangeInCycle());
      strictSubtype_TypeDecl_values.put(_parameters, new_strictSubtype_TypeDecl_value);

      state.leaveCircle();
      return new_strictSubtype_TypeDecl_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_strictSubtype_TypeDecl_value = type.strictSupertypeInterfaceDeclSubstituted(this);
      if (new_strictSubtype_TypeDecl_value != ((Boolean)_value.value)) {
        state.setChangeInCycle();
        _value.value = new_strictSubtype_TypeDecl_value;
      }
      return new_strictSubtype_TypeDecl_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/GenericsSubtype.jrag:474
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/GenericsSubtype.jrag:474")
  public boolean strictSupertypeInterfaceDeclSubstituted(InterfaceDeclSubstituted type) {
    boolean strictSupertypeInterfaceDeclSubstituted_InterfaceDeclSubstituted_value = original() == type.original() && type.enclosingType().strictSubtype(enclosingType())
          || super.strictSupertypeInterfaceDeclSubstituted(type);
    return strictSupertypeInterfaceDeclSubstituted_InterfaceDeclSubstituted_value;
  }
  /**
   * @attribute syn
   * @aspect StrictSubtype
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/GenericsSubtype.jrag:398
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="StrictSubtype", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/GenericsSubtype.jrag:398")
  public boolean strictSupertypeInterfaceDecl(InterfaceDecl type) {
    boolean strictSupertypeInterfaceDecl_InterfaceDecl_value = super.strictSupertypeInterfaceDecl(type) || original().strictSupertypeInterfaceDecl(type);
    return strictSupertypeInterfaceDecl_InterfaceDecl_value;
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
    typeDescriptor_value = original().typeDescriptor();
    if (state().inCircle()) {
      typeDescriptor_computed = state().cycle();
    
    } else {
      typeDescriptor_computed = ASTNode$State.NON_CYCLE;
    
    }
    return typeDescriptor_value;
  }
  /** @apilevel internal */
  private void constantPoolName_reset() {
    constantPoolName_computed = null;
    constantPoolName_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle constantPoolName_computed = null;

  /** @apilevel internal */
  protected String constantPoolName_value;

  /**
   * For a top-level type the constant pool name of the type is the same as the
   * canonical name but with dots replaced by solidus.
   * 
   * <p>For nested types the constant pool name is based on the enclosing top-level
   * types constant pool name followed by a dollar sign and a unique index and/or
   * the type name.
   * 
   * @return constant pool name of this type
   * @attribute syn
   * @aspect ConstantPoolNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/ConstantPoolNames.jrag:44
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantPoolNames", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/ConstantPoolNames.jrag:44")
  public String constantPoolName() {
    ASTNode$State state = state();
    if (constantPoolName_computed == ASTNode$State.NON_CYCLE || constantPoolName_computed == state().cycle()) {
      return constantPoolName_value;
    }
    constantPoolName_value = original().constantPoolName();
    if (state().inCircle()) {
      constantPoolName_computed = state().cycle();
    
    } else {
      constantPoolName_computed = ASTNode$State.NON_CYCLE;
    
    }
    return constantPoolName_value;
  }
  /** @apilevel internal */
  private void uniqueIndex_reset() {
    uniqueIndex_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle uniqueIndex_computed = null;

  /** @apilevel internal */
  protected int uniqueIndex_value;

  /**
   * @attribute syn
   * @aspect Java2Rewrites
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Java2Rewrites.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java2Rewrites", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Java2Rewrites.jrag:35")
  public int uniqueIndex() {
    ASTNode$State state = state();
    if (uniqueIndex_computed == ASTNode$State.NON_CYCLE || uniqueIndex_computed == state().cycle()) {
      return uniqueIndex_value;
    }
    uniqueIndex_value = original().uniqueIndex();
    if (state().inCircle()) {
      uniqueIndex_computed = state().cycle();
    
    } else {
      uniqueIndex_computed = ASTNode$State.NON_CYCLE;
    
    }
    return uniqueIndex_value;
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1211
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1211")
  public String arrayTypeDescriptor() {
    String arrayTypeDescriptor_value = original().arrayTypeDescriptor();
    return arrayTypeDescriptor_value;
  }
  /** @apilevel internal */
  private void localMethodsSignatureMap_reset() {
    localMethodsSignatureMap_computed = null;
    localMethodsSignatureMap_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle localMethodsSignatureMap_computed = null;

  /** @apilevel internal */
  protected Map<String, SimpleSet<MethodDecl>> localMethodsSignatureMap_value;

  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1211
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1211")
  public Map<String, SimpleSet<MethodDecl>> localMethodsSignatureMap() {
    ASTNode$State state = state();
    if (localMethodsSignatureMap_computed == ASTNode$State.NON_CYCLE || localMethodsSignatureMap_computed == state().cycle()) {
      return localMethodsSignatureMap_value;
    }
    localMethodsSignatureMap_value = localMethodsSignatureMap_compute();
    if (state().inCircle()) {
      localMethodsSignatureMap_computed = state().cycle();
    
    } else {
      localMethodsSignatureMap_computed = ASTNode$State.NON_CYCLE;
    
    }
    return localMethodsSignatureMap_value;
  }
  /** @apilevel internal */
  private Map<String, SimpleSet<MethodDecl>> localMethodsSignatureMap_compute() {
      Map<String, SimpleSet<MethodDecl>> map = new HashMap<String, SimpleSet<MethodDecl>>();
      for (Iterator<MethodDecl> iter = original().localMethodsIterator(); iter.hasNext(); ) {
        MethodDecl decl = iter.next();
        if (!decl.isSubstitutable()) {
          putSimpleSetElement(map, decl.signature(), decl);
        }
      }
      for (BodyDecl decl : getBodyDeclList()) {
        if (decl instanceof MethodDecl) {
          MethodDecl method = (MethodDecl) decl;
          putSimpleSetElement(map, method.signature(), method);
        }
      }
      return map;
    }
  /** @apilevel internal */
  private void localFields_String_reset() {
    localFields_String_computed = new java.util.HashMap(4);
    localFields_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map localFields_String_values;
  /** @apilevel internal */
  protected java.util.Map localFields_String_computed;
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1228
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1228")
  public SimpleSet<Variable> localFields(String name) {
    Object _parameters = name;
    if (localFields_String_computed == null) localFields_String_computed = new java.util.HashMap(4);
    if (localFields_String_values == null) localFields_String_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (localFields_String_values.containsKey(_parameters) && localFields_String_computed != null
        && localFields_String_computed.containsKey(_parameters)
        && (localFields_String_computed.get(_parameters) == ASTNode$State.NON_CYCLE || localFields_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<Variable>) localFields_String_values.get(_parameters);
    }
    SimpleSet<Variable> localFields_String_value = localFields_compute(name);
    if (state().inCircle()) {
      localFields_String_values.put(_parameters, localFields_String_value);
      localFields_String_computed.put(_parameters, state().cycle());
    
    } else {
      localFields_String_values.put(_parameters, localFields_String_value);
      localFields_String_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return localFields_String_value;
  }
  /** @apilevel internal */
  private SimpleSet<Variable> localFields_compute(String name) {
      SimpleSet<Variable> set = emptySet();
      for (Variable field : original().localFields(name)) {
        if (field.name().equals(name)
            && field.fieldDecl() != null && !field.fieldDecl().isSubstitutable()) {
          set = set.add(field);
        }
      }
      for (BodyDecl decl : getBodyDeclList()) {
        if (decl instanceof FieldDecl) {
          FieldDecl field = (FieldDecl) decl;
          for (FieldDeclarator f : field.getDeclaratorList()) {
            if (f.name().equals(name)) {
              set = set.add(f);
            }
          }
        }
      }
      return set;
    }
  /** @apilevel internal */
  private void localTypeDecls_String_reset() {
    localTypeDecls_String_values = null;
  }
  protected java.util.Map localTypeDecls_String_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1259")
  public SimpleSet<TypeDecl> localTypeDecls(String name) {
    Object _parameters = name;
    if (localTypeDecls_String_values == null) localTypeDecls_String_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (localTypeDecls_String_values.containsKey(_parameters)) {
      Object _cache = localTypeDecls_String_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (SimpleSet<TypeDecl>) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      localTypeDecls_String_values.put(_parameters, _value);
      _value.value = emptySet();
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      SimpleSet<TypeDecl> new_localTypeDecls_String_value;
      do {
        _value.cycle = state.nextCycle();
        new_localTypeDecls_String_value = localTypeDecls_compute(name);
        if ((new_localTypeDecls_String_value == null && ((SimpleSet<TypeDecl>)_value.value) != null) || (new_localTypeDecls_String_value != null && !new_localTypeDecls_String_value.equals(((SimpleSet<TypeDecl>)_value.value)))) {
          state.setChangeInCycle();
          _value.value = new_localTypeDecls_String_value;
        }
      } while (state.testAndClearChangeInCycle());
      localTypeDecls_String_values.put(_parameters, new_localTypeDecls_String_value);

      state.leaveCircle();
      return new_localTypeDecls_String_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      SimpleSet<TypeDecl> new_localTypeDecls_String_value = localTypeDecls_compute(name);
      if ((new_localTypeDecls_String_value == null && ((SimpleSet<TypeDecl>)_value.value) != null) || (new_localTypeDecls_String_value != null && !new_localTypeDecls_String_value.equals(((SimpleSet<TypeDecl>)_value.value)))) {
        state.setChangeInCycle();
        _value.value = new_localTypeDecls_String_value;
      }
      return new_localTypeDecls_String_value;
    } else {
      return (SimpleSet<TypeDecl>) _value.value;
    }
  }
  /** @apilevel internal */
  private SimpleSet<TypeDecl> localTypeDecls_compute(String name) {
      SimpleSet<TypeDecl> set = emptySet();
      for (TypeDecl type : original().localTypeDecls(name)) {
        if (type.isStatic()) {
          set = set.add(type);
        }
      }
      for (BodyDecl decl : getBodyDeclList()) {
        if (decl instanceof MemberClassDecl) {
          ClassDecl typeDecl = ((MemberClassDecl) decl).getClassDecl();
          if (typeDecl.name().equals(name)) {
            set = set.add(typeDecl);
          }
        }
      }
      return set;
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
