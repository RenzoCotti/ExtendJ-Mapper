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
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/grammar/Enums.ast:8
 * @astdecl BoundEnumConstant : VarAccess ::= <EnumConstant:EnumConstant>;
 * @production BoundEnumConstant : {@link VarAccess} ::= <span class="component">&lt;EnumConstant:EnumConstant&gt;</span>;

 */
public class BoundEnumConstant extends VarAccess implements Cloneable {
  /**
   * @aspect Enums
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/frontend/Enums.jrag:427
   */
  public boolean isExactVarAccess() {
    return false;
  }
  /**
   * @aspect Enums
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java5/frontend/Enums.jrag:431
   */
  public BoundEnumConstant(EnumConstant c) {
    this(c.name(), c);
  }
  /**
   * @declaredat ASTNode:1
   */
  public BoundEnumConstant() {
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
  }
  /**
   * @declaredat ASTNode:12
   */
  @ASTNodeAnnotation.Constructor(
    name = {"ID", "EnumConstant"},
    type = {"String", "EnumConstant"},
    kind = {"Token", "Token"}
  )
  public BoundEnumConstant(String p0, EnumConstant p1) {
    setID(p0);
    setEnumConstant(p1);
  }
  /**
   * @declaredat ASTNode:21
   */
  public BoundEnumConstant(beaver.Symbol p0, EnumConstant p1) {
    setID(p0);
    setEnumConstant(p1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:26
   */
  protected int numChildren() {
    return 0;
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
    decl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:41
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:45
   */
  public BoundEnumConstant clone() throws CloneNotSupportedException {
    BoundEnumConstant node = (BoundEnumConstant) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:50
   */
  public BoundEnumConstant copy() {
    try {
      BoundEnumConstant node = (BoundEnumConstant) clone();
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
   * @declaredat ASTNode:69
   */
  @Deprecated
  public BoundEnumConstant fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:79
   */
  public BoundEnumConstant treeCopyNoTransform() {
    BoundEnumConstant tree = (BoundEnumConstant) copy();
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
   * @declaredat ASTNode:99
   */
  public BoundEnumConstant treeCopy() {
    BoundEnumConstant tree = (BoundEnumConstant) copy();
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
   * @declaredat ASTNode:113
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node) && (tokenString_ID == ((BoundEnumConstant) node).tokenString_ID) && (tokenEnumConstant_EnumConstant == ((BoundEnumConstant) node).tokenEnumConstant_EnumConstant);    
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
   * Replaces the lexeme EnumConstant.
   * @param value The new value for the lexeme EnumConstant.
   * @apilevel high-level
   */
  public void setEnumConstant(EnumConstant value) {
    tokenEnumConstant_EnumConstant = value;
  }
  /** @apilevel internal 
   */
  protected EnumConstant tokenEnumConstant_EnumConstant;
  /**
   * Retrieves the value for the lexeme EnumConstant.
   * @return The value for the lexeme EnumConstant.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="EnumConstant")
  public EnumConstant getEnumConstant() {
    return tokenEnumConstant_EnumConstant;
  }
  /** @apilevel internal */
  private void decl_reset() {
    decl_computed = null;
    decl_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle decl_computed = null;

  /** @apilevel internal */
  protected Variable decl_value;

  /**
   * @attribute syn
   * @aspect VariableScopePropagation
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupVariable.jrag:374
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableScopePropagation", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/LookupVariable.jrag:374")
  public Variable decl() {
    ASTState state = state();
    if (decl_computed == ASTState.NON_CYCLE || decl_computed == state().cycle()) {
      return decl_value;
    }
    decl_value = getEnumConstant();
    if (state().inCircle()) {
      decl_computed = state().cycle();
    
    } else {
      decl_computed = ASTState.NON_CYCLE;
    
    }
    return decl_value;
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
