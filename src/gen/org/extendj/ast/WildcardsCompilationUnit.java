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
import java.util.zip.*;
import java.io.*;
import org.jastadd.util.*;
import org.jastadd.util.PrettyPrintable;
import org.jastadd.util.PrettyPrinter;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
/**
 * @ast node
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/grammar/Generics.ast:62
 * @production WildcardsCompilationUnit : {@link CompilationUnit};

 */
public class WildcardsCompilationUnit extends CompilationUnit implements Cloneable {
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1626
   */
  public static LUBType createLUBType(Collection<TypeDecl> bounds) {
    List<Access> boundList = new List<Access>();
    StringBuilder name = new StringBuilder();
    for (TypeDecl typeDecl : bounds) {
      boundList.add(typeDecl.createBoundAccess());
      name.append("& " + typeDecl.typeName());
    }
    LUBType decl = new LUBType(
      new Modifiers(new List().add(new Modifier("public"))),
      name.toString(),
      new List(),
      boundList
    );
    return decl;
  }
  /**
   * @declaredat ASTNode:1
   */
  public WildcardsCompilationUnit() {
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
    setChild(new List(), 0);
    setChild(new List(), 1);
  }
  /**
   * @declaredat ASTNode:15
   */
  public WildcardsCompilationUnit(String p0, List<ImportDecl> p1, List<TypeDecl> p2) {
    setPackageDecl(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:20
   */
  public WildcardsCompilationUnit(beaver.Symbol p0, List<ImportDecl> p1, List<TypeDecl> p2) {
    setPackageDecl(p0);
    setChild(p1, 0);
    setChild(p2, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:26
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:32
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:36
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    typeWildcard_reset();
    lookupWildcardExtends_TypeDecl_reset();
    lookupWildcardSuper_TypeDecl_reset();
    lookupLUBType_Collection_TypeDecl__reset();
    lookupGLBType_Collection_TypeDecl__reset();
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
  public WildcardsCompilationUnit clone() throws CloneNotSupportedException {
    WildcardsCompilationUnit node = (WildcardsCompilationUnit) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:54
   */
  public WildcardsCompilationUnit copy() {
    try {
      WildcardsCompilationUnit node = (WildcardsCompilationUnit) clone();
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
  public WildcardsCompilationUnit fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:83
   */
  public WildcardsCompilationUnit treeCopyNoTransform() {
    WildcardsCompilationUnit tree = (WildcardsCompilationUnit) copy();
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
  public WildcardsCompilationUnit treeCopy() {
    WildcardsCompilationUnit tree = (WildcardsCompilationUnit) copy();
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
    return super.is$Equal(node) && (tokenString_PackageDecl == ((WildcardsCompilationUnit) node).tokenString_PackageDecl);    
  }
  /**
   * Replaces the lexeme PackageDecl.
   * @param value The new value for the lexeme PackageDecl.
   * @apilevel high-level
   */
  public void setPackageDecl(String value) {
    tokenString_PackageDecl = value;
  }
  /**
   * JastAdd-internal setter for lexeme PackageDecl using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme PackageDecl
   * @apilevel internal
   */
  public void setPackageDecl(beaver.Symbol symbol) {
    if (symbol.value != null && !(symbol.value instanceof String))
    throw new UnsupportedOperationException("setPackageDecl is only valid for String lexemes");
    tokenString_PackageDecl = (String)symbol.value;
    PackageDeclstart = symbol.getStart();
    PackageDeclend = symbol.getEnd();
  }
  /**
   * Retrieves the value for the lexeme PackageDecl.
   * @return The value for the lexeme PackageDecl.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="PackageDecl")
  public String getPackageDecl() {
    return tokenString_PackageDecl != null ? tokenString_PackageDecl : "";
  }
  /**
   * Replaces the ImportDecl list.
   * @param list The new list node to be used as the ImportDecl list.
   * @apilevel high-level
   */
  public void setImportDeclList(List<ImportDecl> list) {
    setChild(list, 0);
  }
  /**
   * Retrieves the number of children in the ImportDecl list.
   * @return Number of children in the ImportDecl list.
   * @apilevel high-level
   */
  public int getNumImportDecl() {
    return getImportDeclList().getNumChild();
  }
  /**
   * Retrieves the number of children in the ImportDecl list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the ImportDecl list.
   * @apilevel low-level
   */
  public int getNumImportDeclNoTransform() {
    return getImportDeclListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the ImportDecl list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the ImportDecl list.
   * @apilevel high-level
   */
  public ImportDecl getImportDecl(int i) {
    return (ImportDecl) getImportDeclList().getChild(i);
  }
  /**
   * Check whether the ImportDecl list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasImportDecl() {
    return getImportDeclList().getNumChild() != 0;
  }
  /**
   * Append an element to the ImportDecl list.
   * @param node The element to append to the ImportDecl list.
   * @apilevel high-level
   */
  public void addImportDecl(ImportDecl node) {
    List<ImportDecl> list = (parent == null) ? getImportDeclListNoTransform() : getImportDeclList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addImportDeclNoTransform(ImportDecl node) {
    List<ImportDecl> list = getImportDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the ImportDecl list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setImportDecl(ImportDecl node, int i) {
    List<ImportDecl> list = getImportDeclList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the ImportDecl list.
   * @return The node representing the ImportDecl list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="ImportDecl")
  public List<ImportDecl> getImportDeclList() {
    List<ImportDecl> list = (List<ImportDecl>) getChild(0);
    return list;
  }
  /**
   * Retrieves the ImportDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the ImportDecl list.
   * @apilevel low-level
   */
  public List<ImportDecl> getImportDeclListNoTransform() {
    return (List<ImportDecl>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the ImportDecl list without
   * triggering rewrites.
   */
  public ImportDecl getImportDeclNoTransform(int i) {
    return (ImportDecl) getImportDeclListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the ImportDecl list.
   * @return The node representing the ImportDecl list.
   * @apilevel high-level
   */
  public List<ImportDecl> getImportDecls() {
    return getImportDeclList();
  }
  /**
   * Retrieves the ImportDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the ImportDecl list.
   * @apilevel low-level
   */
  public List<ImportDecl> getImportDeclsNoTransform() {
    return getImportDeclListNoTransform();
  }
  /**
   * Replaces the TypeDecl list.
   * @param list The new list node to be used as the TypeDecl list.
   * @apilevel high-level
   */
  public void setTypeDeclList(List<TypeDecl> list) {
    setChild(list, 1);
  }
  /**
   * Retrieves the number of children in the TypeDecl list.
   * @return Number of children in the TypeDecl list.
   * @apilevel high-level
   */
  public int getNumTypeDecl() {
    return getTypeDeclList().getNumChild();
  }
  /**
   * Retrieves the number of children in the TypeDecl list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the TypeDecl list.
   * @apilevel low-level
   */
  public int getNumTypeDeclNoTransform() {
    return getTypeDeclListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the TypeDecl list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the TypeDecl list.
   * @apilevel high-level
   */
  public TypeDecl getTypeDecl(int i) {
    return (TypeDecl) getTypeDeclList().getChild(i);
  }
  /**
   * Check whether the TypeDecl list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasTypeDecl() {
    return getTypeDeclList().getNumChild() != 0;
  }
  /**
   * Append an element to the TypeDecl list.
   * @param node The element to append to the TypeDecl list.
   * @apilevel high-level
   */
  public void addTypeDecl(TypeDecl node) {
    List<TypeDecl> list = (parent == null) ? getTypeDeclListNoTransform() : getTypeDeclList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addTypeDeclNoTransform(TypeDecl node) {
    List<TypeDecl> list = getTypeDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the TypeDecl list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setTypeDecl(TypeDecl node, int i) {
    List<TypeDecl> list = getTypeDeclList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the TypeDecl list.
   * @return The node representing the TypeDecl list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="TypeDecl")
  public List<TypeDecl> getTypeDeclList() {
    List<TypeDecl> list = (List<TypeDecl>) getChild(1);
    return list;
  }
  /**
   * Retrieves the TypeDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeDecl list.
   * @apilevel low-level
   */
  public List<TypeDecl> getTypeDeclListNoTransform() {
    return (List<TypeDecl>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the TypeDecl list without
   * triggering rewrites.
   */
  public TypeDecl getTypeDeclNoTransform(int i) {
    return (TypeDecl) getTypeDeclListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the TypeDecl list.
   * @return The node representing the TypeDecl list.
   * @apilevel high-level
   */
  public List<TypeDecl> getTypeDecls() {
    return getTypeDeclList();
  }
  /**
   * Retrieves the TypeDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeDecl list.
   * @apilevel low-level
   */
  public List<TypeDecl> getTypeDeclsNoTransform() {
    return getTypeDeclListNoTransform();
  }
  /** @apilevel internal */
  private void typeWildcard_reset() {
    typeWildcard_computed = false;
    
    typeWildcard_value = null;
  }
  /** @apilevel internal */
  protected boolean typeWildcard_computed = false;

  /** @apilevel internal */
  protected TypeDecl typeWildcard_value;

  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1585
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1585")
  public TypeDecl typeWildcard() {
    ASTNode$State state = state();
    if (typeWildcard_computed) {
      return typeWildcard_value;
    }
    state().enterLazyAttribute();
    typeWildcard_value = new WildcardType(
              new Modifiers(new List().add(new Modifier("public"))),
              "?",
              new List());
    typeWildcard_value.setParent(this);
    typeWildcard_computed = true;
    state().leaveLazyAttribute();
    return typeWildcard_value;
  }
  /** @apilevel internal */
  private void lookupWildcardExtends_TypeDecl_reset() {
    lookupWildcardExtends_TypeDecl_values = null;
    lookupWildcardExtends_TypeDecl_list = null;
  }
  /** @apilevel internal */
  protected List lookupWildcardExtends_TypeDecl_list;
  /** @apilevel internal */
  protected java.util.Map lookupWildcardExtends_TypeDecl_values;

  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1595
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1595")
  public TypeDecl lookupWildcardExtends(TypeDecl bound) {
    Object _parameters = bound;
    if (lookupWildcardExtends_TypeDecl_values == null) lookupWildcardExtends_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (lookupWildcardExtends_TypeDecl_values.containsKey(_parameters)) {
      return (TypeDecl) lookupWildcardExtends_TypeDecl_values.get(_parameters);
    }
    state().enterLazyAttribute();
    TypeDecl lookupWildcardExtends_TypeDecl_value = new WildcardExtendsType(
              new Modifiers(new List().add(new Modifier("public"))),
              "? extends " + bound.typeName(),
              new List(),
              bound.createBoundAccess());
    if (lookupWildcardExtends_TypeDecl_list == null) {
      lookupWildcardExtends_TypeDecl_list = new List();
      lookupWildcardExtends_TypeDecl_list.setParent(this);
    }
    lookupWildcardExtends_TypeDecl_list.add(lookupWildcardExtends_TypeDecl_value);
    if (lookupWildcardExtends_TypeDecl_value != null) {
      lookupWildcardExtends_TypeDecl_value = (TypeDecl) lookupWildcardExtends_TypeDecl_list.getChild(lookupWildcardExtends_TypeDecl_list.numChildren - 1);
    }
    lookupWildcardExtends_TypeDecl_values.put(_parameters, lookupWildcardExtends_TypeDecl_value);
    state().leaveLazyAttribute();
    return lookupWildcardExtends_TypeDecl_value;
  }
  /** @apilevel internal */
  private void lookupWildcardSuper_TypeDecl_reset() {
    lookupWildcardSuper_TypeDecl_values = null;
    lookupWildcardSuper_TypeDecl_list = null;
  }
  /** @apilevel internal */
  protected List lookupWildcardSuper_TypeDecl_list;
  /** @apilevel internal */
  protected java.util.Map lookupWildcardSuper_TypeDecl_values;

  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1609
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1609")
  public TypeDecl lookupWildcardSuper(TypeDecl bound) {
    Object _parameters = bound;
    if (lookupWildcardSuper_TypeDecl_values == null) lookupWildcardSuper_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (lookupWildcardSuper_TypeDecl_values.containsKey(_parameters)) {
      return (TypeDecl) lookupWildcardSuper_TypeDecl_values.get(_parameters);
    }
    state().enterLazyAttribute();
    TypeDecl lookupWildcardSuper_TypeDecl_value = new WildcardSuperType(
              new Modifiers(new List().add(new Modifier("public"))),
              "? super " + bound.typeName(),
              new List(),
              bound.createBoundAccess());
    if (lookupWildcardSuper_TypeDecl_list == null) {
      lookupWildcardSuper_TypeDecl_list = new List();
      lookupWildcardSuper_TypeDecl_list.setParent(this);
    }
    lookupWildcardSuper_TypeDecl_list.add(lookupWildcardSuper_TypeDecl_value);
    if (lookupWildcardSuper_TypeDecl_value != null) {
      lookupWildcardSuper_TypeDecl_value = (TypeDecl) lookupWildcardSuper_TypeDecl_list.getChild(lookupWildcardSuper_TypeDecl_list.numChildren - 1);
    }
    lookupWildcardSuper_TypeDecl_values.put(_parameters, lookupWildcardSuper_TypeDecl_value);
    state().leaveLazyAttribute();
    return lookupWildcardSuper_TypeDecl_value;
  }
  /** @apilevel internal */
  private void lookupLUBType_Collection_TypeDecl__reset() {
    lookupLUBType_Collection_TypeDecl__values = null;
    lookupLUBType_Collection_TypeDecl__list = null;
  }
  /** @apilevel internal */
  protected List lookupLUBType_Collection_TypeDecl__list;
  /** @apilevel internal */
  protected java.util.Map lookupLUBType_Collection_TypeDecl__values;

  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1623
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1623")
  public LUBType lookupLUBType(Collection<TypeDecl> bounds) {
    Object _parameters = bounds;
    if (lookupLUBType_Collection_TypeDecl__values == null) lookupLUBType_Collection_TypeDecl__values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (lookupLUBType_Collection_TypeDecl__values.containsKey(_parameters)) {
      return (LUBType) lookupLUBType_Collection_TypeDecl__values.get(_parameters);
    }
    state().enterLazyAttribute();
    LUBType lookupLUBType_Collection_TypeDecl__value = createLUBType(bounds);
    if (lookupLUBType_Collection_TypeDecl__list == null) {
      lookupLUBType_Collection_TypeDecl__list = new List();
      lookupLUBType_Collection_TypeDecl__list.setParent(this);
    }
    lookupLUBType_Collection_TypeDecl__list.add(lookupLUBType_Collection_TypeDecl__value);
    if (lookupLUBType_Collection_TypeDecl__value != null) {
      lookupLUBType_Collection_TypeDecl__value = (LUBType) lookupLUBType_Collection_TypeDecl__list.getChild(lookupLUBType_Collection_TypeDecl__list.numChildren - 1);
    }
    lookupLUBType_Collection_TypeDecl__values.put(_parameters, lookupLUBType_Collection_TypeDecl__value);
    state().leaveLazyAttribute();
    return lookupLUBType_Collection_TypeDecl__value;
  }
  /** @apilevel internal */
  private void lookupGLBType_Collection_TypeDecl__reset() {
    lookupGLBType_Collection_TypeDecl__values = null;
    lookupGLBType_Collection_TypeDecl__list = null;
  }
  /** @apilevel internal */
  protected List lookupGLBType_Collection_TypeDecl__list;
  /** @apilevel internal */
  protected java.util.Map lookupGLBType_Collection_TypeDecl__values;

  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1667
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1667")
  public GLBType lookupGLBType(Collection<TypeDecl> bounds) {
    Object _parameters = bounds;
    if (lookupGLBType_Collection_TypeDecl__values == null) lookupGLBType_Collection_TypeDecl__values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (lookupGLBType_Collection_TypeDecl__values.containsKey(_parameters)) {
      return (GLBType) lookupGLBType_Collection_TypeDecl__values.get(_parameters);
    }
    state().enterLazyAttribute();
    GLBType lookupGLBType_Collection_TypeDecl__value = lookupGLBType_compute(bounds);
    if (lookupGLBType_Collection_TypeDecl__list == null) {
      lookupGLBType_Collection_TypeDecl__list = new List();
      lookupGLBType_Collection_TypeDecl__list.setParent(this);
    }
    lookupGLBType_Collection_TypeDecl__list.add(lookupGLBType_Collection_TypeDecl__value);
    if (lookupGLBType_Collection_TypeDecl__value != null) {
      lookupGLBType_Collection_TypeDecl__value = (GLBType) lookupGLBType_Collection_TypeDecl__list.getChild(lookupGLBType_Collection_TypeDecl__list.numChildren - 1);
    }
    lookupGLBType_Collection_TypeDecl__values.put(_parameters, lookupGLBType_Collection_TypeDecl__value);
    state().leaveLazyAttribute();
    return lookupGLBType_Collection_TypeDecl__value;
  }
  /** @apilevel internal */
  private GLBType lookupGLBType_compute(Collection<TypeDecl> bounds) {
      List<Access> boundList = new List<Access>();
      StringBuilder name = new StringBuilder();
      for (TypeDecl typeDecl : bounds) {
        boundList.add(typeDecl.createBoundAccess());
        name.append("& " + typeDecl.typeName());
      }
      GLBType decl = new GLBType(
        new Modifiers(new List().add(new Modifier("public"))),
        name.toString(),
        new List(),
        boundList
      );
      return decl;
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
