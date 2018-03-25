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
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/grammar/BasicTWR.ast:1
 * @astdecl BasicTWR : Stmt ::= Resource:ResourceDeclaration Block;
 * @production BasicTWR : {@link Stmt} ::= <span class="component">Resource:{@link ResourceDeclaration}</span> <span class="component">{@link Block}</span>;

 */
public class BasicTWR extends Stmt implements Cloneable, VariableScope {
  /**
   * The general structure of the basic try-with-resources:
   * 
   * <pre><code>
   * RESOURCE
   * BLOCK
   * 
   * Primary Exception Handler
   * Automatic Closing of Resource
   * Suppressed Exception Handler
   * re-throw primary exception
   * Automatic Closing of Resource
   * </pre></code>
   * 
   * Pseudocode for basic try-with-resources:
   * 
   * <pre><code>
   * 0  .resourceBegin
   * 1  emit RESOURCE
   * 0  store resource
   * 0  .resourceEnd
   * 
   * 0  .blockBegin
   * 0  emit BLOCK
   * 0  .blockEnd
   * 0  goto outerFinally
   * 
   * 1  .resourceException
   * 1  throw
   * 
   * #if BLOCK is not empty:
   * 
   * 1  .catchPrimary
   * 0  store primary
   * 
   * 0  .tryCloseBegin
   * 1  load resource
   * 0  ifnull innerFinally
   * 1  load resource
   * 0  invoke java.lang.AutoCloseable.close()
   * 0  .tryCloseEnd
   * 
   * 0  goto innerFinally
   * 
   * 1  .catchSuppressed
   * 0  store suppressed
   * 1  load primary
   * 2  load suppressed
   * 0  invoke java.lang.Throwable.addSuppressed(Throwable)
   * 
   * 0  .innerFinally
   * 1  load primary
   * 1  throw
   * 
   * #endif BLOCK is not empty
   * 
   * 0  .outerFinally
   * 1  load resource
   * 0  ifnull tryEnd
   * 1  load resource
   * 0  invoke java.lang.AutoCloseable.close()
   * 
   * 0  .tryEnd
   * 
   * Exception Table:
   * resourceBegin .. resourceEnd : resourceException
   * blockBegin .. blockEnd : catchPrimary
   * tryCloseBegin .. tryCloseEnd : catchSuppressed
   * </pre></code>
   * 
   * @aspect TryWithResources
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/TryWithResources.jrag:156
   */
  public void createBCode(CodeGeneration gen) {
    ResourceDeclaration resource = getResource();

    int resourceBeginLbl = gen.constantPool().newLabel();
    int resourceEndLbl = gen.constantPool().newLabel();
    int blockBeginLbl = gen.constantPool().newLabel();
    int blockEndLbl = gen.constantPool().newLabel();
    int tryCloseBeginLbl = gen.constantPool().newLabel();
    int tryCloseEndLbl = gen.constantPool().newLabel();

    int resourceExceptionLbl = gen.constantPool().newLabel();
    int catchPrimaryLbl = gen.constantPool().newLabel();
    int catchSuppressedLbl = gen.constantPool().newLabel();
    int innerFinallyLbl = gen.constantPool().newLabel();
    int outerFinallyLbl = gen.constantPool().newLabel();
    int tryEndLbl = gen.constantPool().newLabel();

    TypeDecl throwableType = lookupType("java.lang", "Throwable");
    TypeDecl resourceType = resource.type();
    TypeDecl autoCloseableType = lookupType("java.lang", "AutoCloseable");

    int resourceIndex = resource.localNum();
    int primaryIndex = resourceIndex+resourceType.variableSize();
    int suppressedIndex = primaryIndex+throwableType.variableSize();

    // Store the resource in local.
    gen.addLabel(resourceBeginLbl);
    resource.createBCode(gen);
    gen.addLabel(resourceEndLbl);
    gen.NOP();

    gen.addLabel(blockBeginLbl);
    getBlock().createBCode(gen);
    gen.addLabel(blockEndLbl);
    gen.GOTO(outerFinallyLbl);

    // If there was an exception when initializing the resource
    // we need to directly rethrow the exception.
    gen.addLabel(resourceExceptionLbl);
    gen.ATHROW();
    gen.addCatchAll(resourceBeginLbl, resourceEndLbl, resourceExceptionLbl);

    if (gen.addressOf(blockBeginLbl) != gen.addressOf(blockEndLbl)) {

      // Catch primary exception:
      // operand stack: .., #primary
      gen.addLabel(catchPrimaryLbl);
      throwableType.emitStoreLocal(gen, primaryIndex);

      // Try-close resource:
      gen.addLabel(tryCloseBeginLbl);
      {
        // if resource != null
        resourceType.emitLoadLocal(gen, resourceIndex);
        gen.IFNULL(innerFinallyLbl);
        resourceType.emitLoadLocal(gen, resourceIndex);
        closeMethod().emitInvokeMethod(gen, autoCloseableType);
      }
      gen.addLabel(tryCloseEndLbl);
      gen.GOTO(innerFinallyLbl);

      // Catch suppressed exception.
      // operand stack: .., #primary, #suppressed
      gen.addLabel(catchSuppressedLbl);
      throwableType.emitStoreLocal(gen, suppressedIndex);
      throwableType.emitLoadLocal(gen, primaryIndex);
      throwableType.emitLoadLocal(gen, suppressedIndex);
      addSuppressedMethod().emitInvokeMethod(gen, throwableType);

      // Inner finally:
      // operand stack: .., #primary
      gen.addLabel(innerFinallyLbl);
      throwableType.emitLoadLocal(gen, primaryIndex);
      gen.ATHROW();

      // If there was an exception during the block of the try
      // statement, then we should try to close the resource.
      gen.addCatchAll(blockBeginLbl, blockEndLbl, catchPrimaryLbl);

      // If an exception occurrs during the automatic closing
      // of a resource after an exception in the try block...
      gen.addCatchAll(tryCloseBeginLbl, tryCloseEndLbl, catchSuppressedLbl);
    }

    // Outer finally.
    gen.addLabel(outerFinallyLbl);
    {
      // if resource != null
      resourceType.emitLoadLocal(gen, resourceIndex);
      gen.IFNULL(tryEndLbl);
      resourceType.emitLoadLocal(gen, resourceIndex);
      closeMethod().emitInvokeMethod(gen, autoCloseableType);
    }

    gen.addLabel(tryEndLbl);
    gen.NOP();
  }
  /**
   * Lookup the java.lang.Throwable.close() method.
   * @aspect TryWithResources
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/TryWithResources.jrag:257
   */
  private MethodDecl closeMethod() {
    TypeDecl autoCloseableType = lookupType("java.lang", "AutoCloseable");
    if (autoCloseableType.isUnknown()) {
      throw new Error("Could not find java.lang.AutoCloseable");
    }
    for (MethodDecl method : (Collection<MethodDecl>)
        autoCloseableType.memberMethods("close")) {
      if (method.getNumParameter() == 0) {
        return method;
      }
    }
    throw new Error("Could not find java.lang.AutoCloseable.close()");
  }
  /**
   * Lookup the java.lang.Throwable.addSuppressed(Throwable) method.
   * @aspect TryWithResources
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/TryWithResources.jrag:274
   */
  private MethodDecl addSuppressedMethod() {
    TypeDecl throwableType = lookupType("java.lang", "Throwable");
    if (throwableType.isUnknown()) {
      throw new Error("Could not find java.lang.Throwable");
    }
    for (MethodDecl method : (Collection<MethodDecl>)
        throwableType.memberMethods("addSuppressed")) {
      if (method.getNumParameter() == 1 &&
          method.getParameter(0).getTypeAccess().type() == throwableType) {
        return method;
      }
    }
    throw new Error("Could not find java.lang.Throwable.addSuppressed()");
  }
  /**
   * @declaredat ASTNode:1
   */
  public BasicTWR() {
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
  }
  /**
   * @declaredat ASTNode:13
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Resource", "Block"},
    type = {"ResourceDeclaration", "Block"},
    kind = {"Child", "Child"}
  )
  public BasicTWR(ResourceDeclaration p0, Block p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:23
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:29
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:33
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    localLookup_String_reset();
    localVariableDeclaration_String_reset();
    lookupVariable_String_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:40
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:44
   */
  public BasicTWR clone() throws CloneNotSupportedException {
    BasicTWR node = (BasicTWR) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:49
   */
  public BasicTWR copy() {
    try {
      BasicTWR node = (BasicTWR) clone();
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
   * @declaredat ASTNode:68
   */
  @Deprecated
  public BasicTWR fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:78
   */
  public BasicTWR treeCopyNoTransform() {
    BasicTWR tree = (BasicTWR) copy();
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
   * @declaredat ASTNode:98
   */
  public BasicTWR treeCopy() {
    BasicTWR tree = (BasicTWR) copy();
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
   * @declaredat ASTNode:112
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the Resource child.
   * @param node The new node to replace the Resource child.
   * @apilevel high-level
   */
  public void setResource(ResourceDeclaration node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Resource child.
   * @return The current node used as the Resource child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Resource")
  public ResourceDeclaration getResource() {
    return (ResourceDeclaration) getChild(0);
  }
  /**
   * Retrieves the Resource child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Resource child.
   * @apilevel low-level
   */
  public ResourceDeclaration getResourceNoTransform() {
    return (ResourceDeclaration) getChildNoTransform(0);
  }
  /**
   * Replaces the Block child.
   * @param node The new node to replace the Block child.
   * @apilevel high-level
   */
  public void setBlock(Block node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the Block child.
   * @return The current node used as the Block child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Block")
  public Block getBlock() {
    return (Block) getChild(1);
  }
  /**
   * Retrieves the Block child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Block child.
   * @apilevel low-level
   */
  public Block getBlockNoTransform() {
    return (Block) getChildNoTransform(1);
  }
  /** @apilevel internal */
  private void localLookup_String_reset() {
    localLookup_String_computed = null;
    localLookup_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map localLookup_String_values;
  /** @apilevel internal */
  protected java.util.Map localLookup_String_computed;
  /**
   * @attribute syn
   * @aspect MultiCatch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:83
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MultiCatch", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:83")
  public SimpleSet<Variable> localLookup(String name) {
    Object _parameters = name;
    if (localLookup_String_computed == null) localLookup_String_computed = new java.util.HashMap(4);
    if (localLookup_String_values == null) localLookup_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (localLookup_String_values.containsKey(_parameters)
        && localLookup_String_computed.containsKey(_parameters)
        && (localLookup_String_computed.get(_parameters) == ASTState.NON_CYCLE || localLookup_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<Variable>) localLookup_String_values.get(_parameters);
    }
    SimpleSet<Variable> localLookup_String_value = localLookup_compute(name);
    if (state().inCircle()) {
      localLookup_String_values.put(_parameters, localLookup_String_value);
      localLookup_String_computed.put(_parameters, state().cycle());
    
    } else {
      localLookup_String_values.put(_parameters, localLookup_String_value);
      localLookup_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return localLookup_String_value;
  }
  /** @apilevel internal */
  private SimpleSet<Variable> localLookup_compute(String name) {
      VariableDeclarator v = localVariableDeclaration(name);
      if (v != null) {
        return v;
      }
      return lookupVariable(name);
    }
  /** @apilevel internal */
  private void localVariableDeclaration_String_reset() {
    localVariableDeclaration_String_computed = null;
    localVariableDeclaration_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map localVariableDeclaration_String_values;
  /** @apilevel internal */
  protected java.util.Map localVariableDeclaration_String_computed;
  /**
   * @attribute syn
   * @aspect MultiCatch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:91
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MultiCatch", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:91")
  public VariableDeclarator localVariableDeclaration(String name) {
    Object _parameters = name;
    if (localVariableDeclaration_String_computed == null) localVariableDeclaration_String_computed = new java.util.HashMap(4);
    if (localVariableDeclaration_String_values == null) localVariableDeclaration_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (localVariableDeclaration_String_values.containsKey(_parameters)
        && localVariableDeclaration_String_computed.containsKey(_parameters)
        && (localVariableDeclaration_String_computed.get(_parameters) == ASTState.NON_CYCLE || localVariableDeclaration_String_computed.get(_parameters) == state().cycle())) {
      return (VariableDeclarator) localVariableDeclaration_String_values.get(_parameters);
    }
    VariableDeclarator localVariableDeclaration_String_value = getResource().declaresVariable(name) ? getResource() : null;
    if (state().inCircle()) {
      localVariableDeclaration_String_values.put(_parameters, localVariableDeclaration_String_value);
      localVariableDeclaration_String_computed.put(_parameters, state().cycle());
    
    } else {
      localVariableDeclaration_String_values.put(_parameters, localVariableDeclaration_String_value);
      localVariableDeclaration_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return localVariableDeclaration_String_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/frontend/PreciseRethrow.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/frontend/PreciseRethrow.jrag:78")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getBlock().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /**
   * @attribute inh
   * @aspect MultiCatch
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:96
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="MultiCatch", declaredAt="/Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:96")
  public SimpleSet<Variable> lookupVariable(String name) {
    Object _parameters = name;
    if (lookupVariable_String_computed == null) lookupVariable_String_computed = new java.util.HashMap(4);
    if (lookupVariable_String_values == null) lookupVariable_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lookupVariable_String_values.containsKey(_parameters)
        && lookupVariable_String_computed.containsKey(_parameters)
        && (lookupVariable_String_computed.get(_parameters) == ASTState.NON_CYCLE || lookupVariable_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<Variable>) lookupVariable_String_values.get(_parameters);
    }
    SimpleSet<Variable> lookupVariable_String_value = getParent().Define_lookupVariable(this, null, name);
    if (state().inCircle()) {
      lookupVariable_String_values.put(_parameters, lookupVariable_String_value);
      lookupVariable_String_computed.put(_parameters, state().cycle());
    
    } else {
      lookupVariable_String_values.put(_parameters, lookupVariable_String_value);
      lookupVariable_String_computed.put(_parameters, ASTState.NON_CYCLE);
    
    }
    return lookupVariable_String_value;
  }
  /** @apilevel internal */
  private void lookupVariable_String_reset() {
    lookupVariable_String_computed = null;
    lookupVariable_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map lookupVariable_String_values;
  /** @apilevel internal */
  protected java.util.Map lookupVariable_String_computed;
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:96
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:81
      return localLookup(name);
    }
    else {
      return getParent().Define_lookupVariable(this, _callerNode, name);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:96
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupVariable
   */
  protected boolean canDefine_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:53
   * @apilevel internal
   */
  public int Define_localNum(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/TryWithResources.jrag:300
      return getResource().localNum() + 3;
    }
    else if (getResourceNoTransform() != null && _callerNode == getResource()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/TryWithResources.jrag:292
      return localNum();
    }
    else {
      return getParent().Define_localNum(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/MultiCatch.jrag:53
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute localNum
   */
  protected boolean canDefine_localNum(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/VariableDeclaration.jrag:133
   * @apilevel internal
   */
  public Modifiers Define_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    if (getResourceNoTransform() != null && _callerNode == getResource()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/TryWithResources.jrag:302
      return getResource().getModifiers();
    }
    else {
      return getParent().Define_declarationModifiers(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/VariableDeclaration.jrag:133
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declarationModifiers
   */
  protected boolean canDefine_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/VariableDeclaration.jrag:144
   * @apilevel internal
   */
  public Access Define_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    if (getResourceNoTransform() != null && _callerNode == getResource()) {
      // @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java7/backend/TryWithResources.jrag:304
      return getResource().getResourceType();
    }
    else {
      return getParent().Define_declarationType(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/frontend/VariableDeclaration.jrag:144
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declarationType
   */
  protected boolean canDefine_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
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
