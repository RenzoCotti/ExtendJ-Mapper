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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/grammar/MethodReference.ast:5
 * @production AmbiguousMethodReference : {@link MethodReference} ::= <span class="component">AmbiguousName:{@link Access}</span>;

 */
public class AmbiguousMethodReference extends MethodReference implements Cloneable {
  /**
   * @declaredat ASTNode:1
   */
  public AmbiguousMethodReference() {
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
  }
  /**
   * @declaredat ASTNode:14
   */
  public AmbiguousMethodReference(List<Access> p0, String p1, Access p2) {
    setChild(p0, 0);
    setID(p1);
    setChild(p2, 1);
  }
  /**
   * @declaredat ASTNode:19
   */
  public AmbiguousMethodReference(List<Access> p0, beaver.Symbol p1, Access p2) {
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
    return true;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:35
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    rewrittenNode_reset();
    congruentTo_FunctionDescriptor_reset();
    potentiallyApplicableMethods_FunctionDescriptor_reset();
    exactCompileTimeDeclaration_reset();
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
  public AmbiguousMethodReference clone() throws CloneNotSupportedException {
    AmbiguousMethodReference node = (AmbiguousMethodReference) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:52
   */
  public AmbiguousMethodReference copy() {
    try {
      AmbiguousMethodReference node = (AmbiguousMethodReference) clone();
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
   * @declaredat ASTNode:71
   */
  @Deprecated
  public AmbiguousMethodReference fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:81
   */
  public AmbiguousMethodReference treeCopyNoTransform() {
    AmbiguousMethodReference tree = (AmbiguousMethodReference) copy();
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
   * @declaredat ASTNode:101
   */
  public AmbiguousMethodReference treeCopy() {
    AmbiguousMethodReference tree = (AmbiguousMethodReference) copy();
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
   * @declaredat ASTNode:115
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node) && (tokenString_ID == ((AmbiguousMethodReference) node).tokenString_ID);    
  }
  /**
   * Replaces the TypeArgument list.
   * @param list The new list node to be used as the TypeArgument list.
   * @apilevel high-level
   */
  public void setTypeArgumentList(List<Access> list) {
    setChild(list, 0);
  }
  /**
   * Retrieves the number of children in the TypeArgument list.
   * @return Number of children in the TypeArgument list.
   * @apilevel high-level
   */
  public int getNumTypeArgument() {
    return getTypeArgumentList().getNumChild();
  }
  /**
   * Retrieves the number of children in the TypeArgument list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the TypeArgument list.
   * @apilevel low-level
   */
  public int getNumTypeArgumentNoTransform() {
    return getTypeArgumentListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the TypeArgument list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the TypeArgument list.
   * @apilevel high-level
   */
  public Access getTypeArgument(int i) {
    return (Access) getTypeArgumentList().getChild(i);
  }
  /**
   * Check whether the TypeArgument list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasTypeArgument() {
    return getTypeArgumentList().getNumChild() != 0;
  }
  /**
   * Append an element to the TypeArgument list.
   * @param node The element to append to the TypeArgument list.
   * @apilevel high-level
   */
  public void addTypeArgument(Access node) {
    List<Access> list = (parent == null) ? getTypeArgumentListNoTransform() : getTypeArgumentList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addTypeArgumentNoTransform(Access node) {
    List<Access> list = getTypeArgumentListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the TypeArgument list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setTypeArgument(Access node, int i) {
    List<Access> list = getTypeArgumentList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the TypeArgument list.
   * @return The node representing the TypeArgument list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="TypeArgument")
  public List<Access> getTypeArgumentList() {
    List<Access> list = (List<Access>) getChild(0);
    return list;
  }
  /**
   * Retrieves the TypeArgument list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   */
  public List<Access> getTypeArgumentListNoTransform() {
    return (List<Access>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the TypeArgument list without
   * triggering rewrites.
   */
  public Access getTypeArgumentNoTransform(int i) {
    return (Access) getTypeArgumentListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the TypeArgument list.
   * @return The node representing the TypeArgument list.
   * @apilevel high-level
   */
  public List<Access> getTypeArguments() {
    return getTypeArgumentList();
  }
  /**
   * Retrieves the TypeArgument list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   */
  public List<Access> getTypeArgumentsNoTransform() {
    return getTypeArgumentListNoTransform();
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
   * Replaces the AmbiguousName child.
   * @param node The new node to replace the AmbiguousName child.
   * @apilevel high-level
   */
  public void setAmbiguousName(Access node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the AmbiguousName child.
   * @return The current node used as the AmbiguousName child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="AmbiguousName")
  public Access getAmbiguousName() {
    return (Access) getChild(1);
  }
  /**
   * Retrieves the AmbiguousName child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the AmbiguousName child.
   * @apilevel low-level
   */
  public Access getAmbiguousNameNoTransform() {
    return (Access) getChildNoTransform(1);
  }
  /** @apilevel internal */
  private void congruentTo_FunctionDescriptor_reset() {
    congruentTo_FunctionDescriptor_computed = new java.util.HashMap(4);
    congruentTo_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map congruentTo_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map congruentTo_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:218
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:218")
  public boolean congruentTo(FunctionDescriptor f) {
    Object _parameters = f;
    if (congruentTo_FunctionDescriptor_computed == null) congruentTo_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (congruentTo_FunctionDescriptor_values == null) congruentTo_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (congruentTo_FunctionDescriptor_values.containsKey(_parameters) && congruentTo_FunctionDescriptor_computed != null
        && congruentTo_FunctionDescriptor_computed.containsKey(_parameters)
        && (congruentTo_FunctionDescriptor_computed.get(_parameters) == ASTNode$State.NON_CYCLE || congruentTo_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (Boolean) congruentTo_FunctionDescriptor_values.get(_parameters);
    }
    boolean congruentTo_FunctionDescriptor_value = false;
    if (state().inCircle()) {
      congruentTo_FunctionDescriptor_values.put(_parameters, congruentTo_FunctionDescriptor_value);
      congruentTo_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      congruentTo_FunctionDescriptor_values.put(_parameters, congruentTo_FunctionDescriptor_value);
      congruentTo_FunctionDescriptor_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return congruentTo_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private void potentiallyApplicableMethods_FunctionDescriptor_reset() {
    potentiallyApplicableMethods_FunctionDescriptor_computed = new java.util.HashMap(4);
    potentiallyApplicableMethods_FunctionDescriptor_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map potentiallyApplicableMethods_FunctionDescriptor_values;
  /** @apilevel internal */
  protected java.util.Map potentiallyApplicableMethods_FunctionDescriptor_computed;
  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:259
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:259")
  public ArrayList<MethodDecl> potentiallyApplicableMethods(FunctionDescriptor f) {
    Object _parameters = f;
    if (potentiallyApplicableMethods_FunctionDescriptor_computed == null) potentiallyApplicableMethods_FunctionDescriptor_computed = new java.util.HashMap(4);
    if (potentiallyApplicableMethods_FunctionDescriptor_values == null) potentiallyApplicableMethods_FunctionDescriptor_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (potentiallyApplicableMethods_FunctionDescriptor_values.containsKey(_parameters) && potentiallyApplicableMethods_FunctionDescriptor_computed != null
        && potentiallyApplicableMethods_FunctionDescriptor_computed.containsKey(_parameters)
        && (potentiallyApplicableMethods_FunctionDescriptor_computed.get(_parameters) == ASTNode$State.NON_CYCLE || potentiallyApplicableMethods_FunctionDescriptor_computed.get(_parameters) == state().cycle())) {
      return (ArrayList<MethodDecl>) potentiallyApplicableMethods_FunctionDescriptor_values.get(_parameters);
    }
    ArrayList<MethodDecl> potentiallyApplicableMethods_FunctionDescriptor_value = new ArrayList<MethodDecl>();
    if (state().inCircle()) {
      potentiallyApplicableMethods_FunctionDescriptor_values.put(_parameters, potentiallyApplicableMethods_FunctionDescriptor_value);
      potentiallyApplicableMethods_FunctionDescriptor_computed.put(_parameters, state().cycle());
    
    } else {
      potentiallyApplicableMethods_FunctionDescriptor_values.put(_parameters, potentiallyApplicableMethods_FunctionDescriptor_value);
      potentiallyApplicableMethods_FunctionDescriptor_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return potentiallyApplicableMethods_FunctionDescriptor_value;
  }
  /** @apilevel internal */
  private void exactCompileTimeDeclaration_reset() {
    exactCompileTimeDeclaration_computed = null;
    exactCompileTimeDeclaration_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle exactCompileTimeDeclaration_computed = null;

  /** @apilevel internal */
  protected MethodDecl exactCompileTimeDeclaration_value;

  /**
   * @attribute syn
   * @aspect MethodReference
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:314
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodReference", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:314")
  public MethodDecl exactCompileTimeDeclaration() {
    ASTNode$State state = state();
    if (exactCompileTimeDeclaration_computed == ASTNode$State.NON_CYCLE || exactCompileTimeDeclaration_computed == state().cycle()) {
      return exactCompileTimeDeclaration_value;
    }
    exactCompileTimeDeclaration_value = unknownMethod();
    if (state().inCircle()) {
      exactCompileTimeDeclaration_computed = state().cycle();
    
    } else {
      exactCompileTimeDeclaration_computed = ASTNode$State.NON_CYCLE;
    
    }
    return exactCompileTimeDeclaration_value;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (getAmbiguousNameNoTransform() != null && _callerNode == getAmbiguousName()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:196
      return NameType.AMBIGUOUS_NAME;
    }
    else {
      return super.Define_nameType(_callerNode, _childNode);
    }
  }
  protected boolean canDefine_nameType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    // Declared at /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:199
    if (!getAmbiguousName().isTypeAccess()) {
      return rewriteRule0();
    }
    // Declared at /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:209
    if (getAmbiguousName().isTypeAccess()) {
      return rewriteRule1();
    }
    return super.rewriteTo();
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:199
   * @apilevel internal
   */
  private ExprMethodReference rewriteRule0() {
{
        return new ExprMethodReference(
            (List<Access>) getTypeArgumentList().treeCopy(),
            getID(),
            (Access) getAmbiguousName().treeCopy());
      }  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:209
   * @apilevel internal
   */
  private TypeMethodReference rewriteRule1() {
{
        return new TypeMethodReference(
            (List<Access>) getTypeArgumentList(),
            getID(),
            (Access) getAmbiguousName());
      }  }
  /** @apilevel internal */
  public boolean canRewrite() {
    // Declared at /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:199
    if (!getAmbiguousName().isTypeAccess()) {
      return true;
    }
    // Declared at /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:209
    if (getAmbiguousName().isTypeAccess()) {
      return true;
    }
    return false;
  }
  /** @apilevel internal */
  private void rewrittenNode_reset() {
    rewrittenNode_computed = false;
    rewrittenNode_initialized = false;
    rewrittenNode_value = null;
    rewrittenNode_cycle = null;
  }
/** @apilevel internal */
protected ASTNode$State.Cycle rewrittenNode_cycle = null;
  /** @apilevel internal */
  protected boolean rewrittenNode_computed = false;

  /** @apilevel internal */
  protected ASTNode rewrittenNode_value;
  /** @apilevel internal */
  protected boolean rewrittenNode_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="", declaredAt=":0")
  public ASTNode rewrittenNode() {
    if (rewrittenNode_computed) {
      return rewrittenNode_value;
    }
    ASTNode$State state = state();
    if (!rewrittenNode_initialized) {
      rewrittenNode_initialized = true;
      rewrittenNode_value = this;
      if (rewrittenNode_value != null) {
        rewrittenNode_value.setParent(getParent());
      }
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        rewrittenNode_cycle = state.nextCycle();
        ASTNode new_rewrittenNode_value = rewrittenNode_value.rewriteTo();
        if (new_rewrittenNode_value != rewrittenNode_value || new_rewrittenNode_value.canRewrite()) {
          state.setChangeInCycle();
        }
        rewrittenNode_value = new_rewrittenNode_value;
        if (rewrittenNode_value != null) {
          rewrittenNode_value.setParent(getParent());
        }
      } while (state.testAndClearChangeInCycle());
      rewrittenNode_computed = true;

      state.leaveCircle();
    } else if (rewrittenNode_cycle != state.cycle()) {
      rewrittenNode_cycle = state.cycle();
      ASTNode new_rewrittenNode_value = rewrittenNode_value.rewriteTo();
      if (new_rewrittenNode_value != rewrittenNode_value || new_rewrittenNode_value.canRewrite()) {
        state.setChangeInCycle();
      }
      rewrittenNode_value = new_rewrittenNode_value;
      if (rewrittenNode_value != null) {
        rewrittenNode_value.setParent(getParent());
      }
    } else {
    }
    return rewrittenNode_value;
  }
}
