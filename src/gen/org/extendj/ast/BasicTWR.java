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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/grammar/BasicTWR.ast:1
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/TryWithResources.jrag:156
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

    gen.changeStackDepth(3);
    int resourceIndex = resource.localNum();
    int primaryIndex = resourceIndex+resourceType.variableSize();
    int suppressedIndex = primaryIndex+throwableType.variableSize();

    // Store the resource in local.
    gen.addLabel(resourceBeginLbl);
    resource.createBCode(gen);
    gen.addLabel(resourceEndLbl);
    gen.emit(this, Bytecode.NOP);

    gen.addLabel(blockBeginLbl);
    getBlock().createBCode(gen);
    gen.addLabel(blockEndLbl);
    gen.emitGoto(this, outerFinallyLbl);

    // If there was an exception when initializing the resource
    // we need to directly rethrow the exception.
    gen.addLabel(resourceExceptionLbl);
    gen.emitThrow(this);
    gen.addException(
      resourceBeginLbl,
      resourceEndLbl,
      resourceExceptionLbl,
      CodeGeneration.ExceptionEntry.CATCH_ALL);

    if (gen.addressOf(blockBeginLbl) != gen.addressOf(blockEndLbl)) {

      // Catch primary exception:
      // operand stack: .., #primary
      gen.addLabel(catchPrimaryLbl);
      throwableType.emitStoreLocal(this, gen, primaryIndex);

      // Try-close resource:
      gen.addLabel(tryCloseBeginLbl);
      {
        // if resource != null
        resourceType.emitLoadLocal(this, gen, resourceIndex);
        gen.emitCompare(this, Bytecode.IFNULL, innerFinallyLbl);
        resourceType.emitLoadLocal(this, gen, resourceIndex);
        closeMethod().emitInvokeMethod(this, gen, autoCloseableType);
      }
      gen.addLabel(tryCloseEndLbl);
      gen.emitGoto(this, innerFinallyLbl);

      // Catch suppressed exception:
      // operand stack: .., #primary, #suppressed
      gen.addLabel(catchSuppressedLbl);
      throwableType.emitStoreLocal(this, gen, suppressedIndex);
      throwableType.emitLoadLocal(this, gen, primaryIndex);
      throwableType.emitLoadLocal(this, gen, suppressedIndex);
      addSuppressedMethod().emitInvokeMethod(this, gen, throwableType);

      // Inner finally:
      // operand stack: .., #primary
      gen.addLabel(innerFinallyLbl);
      throwableType.emitLoadLocal(this, gen, primaryIndex);
      gen.emitThrow(this);

      // If there was an exception during the block of the try
      // statement, then we should try to close the resource.
      gen.addException(
        blockBeginLbl,
        blockEndLbl,
        catchPrimaryLbl,
        CodeGeneration.ExceptionEntry.CATCH_ALL);

      // If an exception occurrs during the automatic closing
      // of a resource after an exception in the try block...
      gen.addException(
        tryCloseBeginLbl,
        tryCloseEndLbl,
        catchSuppressedLbl,
        CodeGeneration.ExceptionEntry.CATCH_ALL);
    }

    // Outer finally.
    gen.addLabel(outerFinallyLbl);
    {
      // if resource != null
      resourceType.emitLoadLocal(this, gen, resourceIndex);
      gen.emitCompare(this, Bytecode.IFNULL, tryEndLbl);
      resourceType.emitLoadLocal(this, gen, resourceIndex);
      closeMethod().emitInvokeMethod(this, gen, autoCloseableType);
    }

    gen.addLabel(tryEndLbl);
    gen.emit(this, Bytecode.NOP);
  }
  /**
   * Lookup the java.lang.Throwable.close() method.
   * @aspect TryWithResources
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/TryWithResources.jrag:270
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/TryWithResources.jrag:287
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
  public BasicTWR(ResourceDeclaration p0, Block p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:18
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:24
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:28
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    localLookup_String_reset();
    localVariableDeclaration_String_reset();
    lookupVariable_String_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:35
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:39
   */
  public BasicTWR clone() throws CloneNotSupportedException {
    BasicTWR node = (BasicTWR) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:44
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
   * @declaredat ASTNode:63
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
   * @declaredat ASTNode:73
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
   * @declaredat ASTNode:93
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
   * @declaredat ASTNode:107
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
    localLookup_String_computed = new java.util.HashMap(4);
    localLookup_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map localLookup_String_values;
  /** @apilevel internal */
  protected java.util.Map localLookup_String_computed;
  /**
   * @attribute syn
   * @aspect MultiCatch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:100
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MultiCatch", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:100")
  public SimpleSet<Variable> localLookup(String name) {
    Object _parameters = name;
    if (localLookup_String_computed == null) localLookup_String_computed = new java.util.HashMap(4);
    if (localLookup_String_values == null) localLookup_String_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (localLookup_String_values.containsKey(_parameters) && localLookup_String_computed != null
        && localLookup_String_computed.containsKey(_parameters)
        && (localLookup_String_computed.get(_parameters) == ASTNode$State.NON_CYCLE || localLookup_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<Variable>) localLookup_String_values.get(_parameters);
    }
    SimpleSet<Variable> localLookup_String_value = localLookup_compute(name);
    if (state().inCircle()) {
      localLookup_String_values.put(_parameters, localLookup_String_value);
      localLookup_String_computed.put(_parameters, state().cycle());
    
    } else {
      localLookup_String_values.put(_parameters, localLookup_String_value);
      localLookup_String_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
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
    localVariableDeclaration_String_computed = new java.util.HashMap(4);
    localVariableDeclaration_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map localVariableDeclaration_String_values;
  /** @apilevel internal */
  protected java.util.Map localVariableDeclaration_String_computed;
  /**
   * @attribute syn
   * @aspect MultiCatch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:108
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MultiCatch", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:108")
  public VariableDeclarator localVariableDeclaration(String name) {
    Object _parameters = name;
    if (localVariableDeclaration_String_computed == null) localVariableDeclaration_String_computed = new java.util.HashMap(4);
    if (localVariableDeclaration_String_values == null) localVariableDeclaration_String_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (localVariableDeclaration_String_values.containsKey(_parameters) && localVariableDeclaration_String_computed != null
        && localVariableDeclaration_String_computed.containsKey(_parameters)
        && (localVariableDeclaration_String_computed.get(_parameters) == ASTNode$State.NON_CYCLE || localVariableDeclaration_String_computed.get(_parameters) == state().cycle())) {
      return (VariableDeclarator) localVariableDeclaration_String_values.get(_parameters);
    }
    VariableDeclarator localVariableDeclaration_String_value = getResource().declaresVariable(name) ? getResource() : null;
    if (state().inCircle()) {
      localVariableDeclaration_String_values.put(_parameters, localVariableDeclaration_String_value);
      localVariableDeclaration_String_computed.put(_parameters, state().cycle());
    
    } else {
      localVariableDeclaration_String_values.put(_parameters, localVariableDeclaration_String_value);
      localVariableDeclaration_String_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return localVariableDeclaration_String_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:78")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getBlock().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /**
   * @attribute inh
   * @aspect MultiCatch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:113
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="MultiCatch", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:113")
  public SimpleSet<Variable> lookupVariable(String name) {
    Object _parameters = name;
    if (lookupVariable_String_computed == null) lookupVariable_String_computed = new java.util.HashMap(4);
    if (lookupVariable_String_values == null) lookupVariable_String_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (lookupVariable_String_values.containsKey(_parameters) && lookupVariable_String_computed != null
        && lookupVariable_String_computed.containsKey(_parameters)
        && (lookupVariable_String_computed.get(_parameters) == ASTNode$State.NON_CYCLE || lookupVariable_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<Variable>) lookupVariable_String_values.get(_parameters);
    }
    SimpleSet<Variable> lookupVariable_String_value = getParent().Define_lookupVariable(this, null, name);
    if (state().inCircle()) {
      lookupVariable_String_values.put(_parameters, lookupVariable_String_value);
      lookupVariable_String_computed.put(_parameters, state().cycle());
    
    } else {
      lookupVariable_String_values.put(_parameters, lookupVariable_String_value);
      lookupVariable_String_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return lookupVariable_String_value;
  }
  /** @apilevel internal */
  private void lookupVariable_String_reset() {
    lookupVariable_String_computed = new java.util.HashMap(4);
    lookupVariable_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map lookupVariable_String_values;
  /** @apilevel internal */
  protected java.util.Map lookupVariable_String_computed;
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:113
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:98
      return localLookup(name);
    }
    else {
      return getParent().Define_lookupVariable(this, _callerNode, name);
    }
  }
  protected boolean canDefine_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:64
   * @apilevel internal
   */
  public int Define_localNum(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/TryWithResources.jrag:316
      return getResource().localNum()
            + getResource().type().variableSize()
            + 2 * lookupType("java.lang", "Throwable").variableSize();
    }
    else if (getResourceNoTransform() != null && _callerNode == getResource()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/TryWithResources.jrag:305
      return localNum();
    }
    else {
      return getParent().Define_localNum(this, _callerNode);
    }
  }
  protected boolean canDefine_localNum(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/VariableDeclaration.jrag:133
   * @apilevel internal
   */
  public Modifiers Define_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    if (getResourceNoTransform() != null && _callerNode == getResource()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/TryWithResources.jrag:321
      return getResource().getModifiers();
    }
    else {
      return getParent().Define_declarationModifiers(this, _callerNode);
    }
  }
  protected boolean canDefine_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/VariableDeclaration.jrag:144
   * @apilevel internal
   */
  public Access Define_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    if (getResourceNoTransform() != null && _callerNode == getResource()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/TryWithResources.jrag:323
      return getResource().getResourceType();
    }
    else {
      return getParent().Define_declarationType(this, _callerNode);
    }
  }
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
