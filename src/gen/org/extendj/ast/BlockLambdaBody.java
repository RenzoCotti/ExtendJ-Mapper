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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/grammar/Lambda.ast:12
 * @production BlockLambdaBody : {@link LambdaBody} ::= <span class="component">{@link Block}</span>;

 */
public class BlockLambdaBody extends LambdaBody implements Cloneable {
  /**
   * @aspect Java8PrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PrettyPrint.jadd:39
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getBlock());
  }
  /**
   * @aspect LambdaToClass
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaAnonymousDecl.jrag:79
   */
  protected Block toBlock() {
    return getBlock().treeCopyNoTransform();
  }
  /**
   * @aspect ReturnCompatible
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:57
   */
  public boolean noReturnsHasResult() {
    ArrayList<ReturnStmt> returnList = lambdaReturns();
    for (int i = 0; i < returnList.size(); i++) {
      if (returnList.get(i).hasResult()) {
        return false;
      }
    }
    return true;
  }
  /**
   * @aspect ReturnCompatible
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:67
   */
  public boolean allReturnsHasResult() {
    ArrayList<ReturnStmt> returnList = lambdaReturns();
    for (int i = 0; i < returnList.size(); i++) {
      if (!returnList.get(i).hasResult()) {
        return false;
      }
    }
    return true;
  }
  /**
   * @declaredat ASTNode:1
   */
  public BlockLambdaBody() {
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
    children = new ASTNode[1];
  }
  /**
   * @declaredat ASTNode:13
   */
  public BlockLambdaBody(Block p0) {
    setChild(p0, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:17
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:23
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:27
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    congruentTo_FunctionDescriptor_reset();
    isBlockBody_reset();
    isExprBody_reset();
    voidCompatible_reset();
    valueCompatible_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:36
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    BlockLambdaBody_lambdaReturns_computed = null;
    BlockLambdaBody_lambdaReturns_value = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:42
   */
  public BlockLambdaBody clone() throws CloneNotSupportedException {
    BlockLambdaBody node = (BlockLambdaBody) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public BlockLambdaBody copy() {
    try {
      BlockLambdaBody node = (BlockLambdaBody) clone();
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
   * @declaredat ASTNode:66
   */
  @Deprecated
  public BlockLambdaBody fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:76
   */
  public BlockLambdaBody treeCopyNoTransform() {
    BlockLambdaBody tree = (BlockLambdaBody) copy();
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
   * @declaredat ASTNode:96
   */
  public BlockLambdaBody treeCopy() {
    BlockLambdaBody tree = (BlockLambdaBody) copy();
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
   * @declaredat ASTNode:110
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the Block child.
   * @param node The new node to replace the Block child.
   * @apilevel high-level
   */
  public void setBlock(Block node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Block child.
   * @return The current node used as the Block child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Block")
  public Block getBlock() {
    return (Block) getChild(0);
  }
  /**
   * Retrieves the Block child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Block child.
   * @apilevel low-level
   */
  public Block getBlockNoTransform() {
    return (Block) getChildNoTransform(0);
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
   * @aspect LambdaExpr
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaExpr.jrag:74
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaExpr", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaExpr.jrag:74")
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
    boolean congruentTo_FunctionDescriptor_value = congruentTo_compute(f);
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
  private boolean congruentTo_compute(FunctionDescriptor f) {
      if (f.method.type().isVoid()) {
        return voidCompatible();
      } else {
        if (!valueCompatible()) {
          return false;
        }
        for (ReturnStmt returnStmt : lambdaReturns()) {
          if (!returnStmt.getResult().assignConversionTo(f.method.type())) {
            return false;
          }
        }
        return true;
      }
    }
  /** @apilevel internal */
  private void isBlockBody_reset() {
    isBlockBody_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isBlockBody_computed = null;

  /** @apilevel internal */
  protected boolean isBlockBody_value;

  /**
   * @attribute syn
   * @aspect LambdaBody
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:29
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaBody", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:29")
  public boolean isBlockBody() {
    ASTNode$State state = state();
    if (isBlockBody_computed == ASTNode$State.NON_CYCLE || isBlockBody_computed == state().cycle()) {
      return isBlockBody_value;
    }
    isBlockBody_value = true;
    if (state().inCircle()) {
      isBlockBody_computed = state().cycle();
    
    } else {
      isBlockBody_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isBlockBody_value;
  }
  /** @apilevel internal */
  private void isExprBody_reset() {
    isExprBody_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isExprBody_computed = null;

  /** @apilevel internal */
  protected boolean isExprBody_value;

  /**
   * @attribute syn
   * @aspect LambdaBody
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:30
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaBody", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:30")
  public boolean isExprBody() {
    ASTNode$State state = state();
    if (isExprBody_computed == ASTNode$State.NON_CYCLE || isExprBody_computed == state().cycle()) {
      return isExprBody_value;
    }
    isExprBody_value = false;
    if (state().inCircle()) {
      isExprBody_computed = state().cycle();
    
    } else {
      isExprBody_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isExprBody_value;
  }
  /** @apilevel internal */
  private void voidCompatible_reset() {
    voidCompatible_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle voidCompatible_computed = null;

  /** @apilevel internal */
  protected boolean voidCompatible_value;

  /**
   * @attribute syn
   * @aspect ReturnCompatible
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:40
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ReturnCompatible", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:40")
  public boolean voidCompatible() {
    ASTNode$State state = state();
    if (voidCompatible_computed == ASTNode$State.NON_CYCLE || voidCompatible_computed == state().cycle()) {
      return voidCompatible_value;
    }
    voidCompatible_value = noReturnsHasResult();
    if (state().inCircle()) {
      voidCompatible_computed = state().cycle();
    
    } else {
      voidCompatible_computed = ASTNode$State.NON_CYCLE;
    
    }
    return voidCompatible_value;
  }
  /** @apilevel internal */
  private void valueCompatible_reset() {
    valueCompatible_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle valueCompatible_computed = null;

  /** @apilevel internal */
  protected boolean valueCompatible_value;

  /**
   * @attribute syn
   * @aspect ReturnCompatible
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:41
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ReturnCompatible", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:41")
  public boolean valueCompatible() {
    ASTNode$State state = state();
    if (valueCompatible_computed == ASTNode$State.NON_CYCLE || valueCompatible_computed == state().cycle()) {
      return valueCompatible_value;
    }
    valueCompatible_value = allReturnsHasResult() && !getBlock().canCompleteNormally();
    if (state().inCircle()) {
      valueCompatible_computed = state().cycle();
    
    } else {
      valueCompatible_computed = ASTNode$State.NON_CYCLE;
    
    }
    return valueCompatible_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/EffectivelyFinal.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/EffectivelyFinal.jrag:43")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getBlock().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/UnreachableStatements.jrag:29
      return true;
    }
    else {
      return getParent().Define_reachable(this, _callerNode);
    }
  }
  protected boolean canDefine_reachable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:534
   * @apilevel internal
   */
  public TypeDecl Define_returnType(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:39
      {
          TypeDecl decl = enclosingLambda().targetType();
          if (decl == null) {
            return unknownType();
          } else if (!(decl instanceof InterfaceDecl)) {
            return unknownType();
          } else {
            InterfaceDecl iDecl = (InterfaceDecl) decl;
            if (!iDecl.isFunctional()) {
              return unknownType();
            } else {
              return iDecl.functionDescriptor().method.type();
            }
          }
        }
    }
    else {
      return getParent().Define_returnType(this, _callerNode);
    }
  }
  protected boolean canDefine_returnType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:195
   * @apilevel internal
   */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:213
      return false;
    }
    else {
      return getParent().Define_assignmentContext(this, _callerNode);
    }
  }
  protected boolean canDefine_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:196
   * @apilevel internal
   */
  public boolean Define_invocationContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:214
      return false;
    }
    else {
      return getParent().Define_invocationContext(this, _callerNode);
    }
  }
  protected boolean canDefine_invocationContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:197
   * @apilevel internal
   */
  public boolean Define_castContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:215
      return false;
    }
    else {
      return getParent().Define_castContext(this, _callerNode);
    }
  }
  protected boolean canDefine_castContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:198
   * @apilevel internal
   */
  public boolean Define_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:216
      return false;
    }
    else {
      return getParent().Define_stringContext(this, _callerNode);
    }
  }
  protected boolean canDefine_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:199
   * @apilevel internal
   */
  public boolean Define_numericContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getBlockNoTransform() != null && _callerNode == getBlock()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:217
      return false;
    }
    else {
      return getParent().Define_numericContext(this, _callerNode);
    }
  }
  protected boolean canDefine_numericContext(ASTNode _callerNode, ASTNode _childNode) {
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
  /**
   * @attribute coll
   * @aspect ReturnCompatible
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:47
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="ReturnCompatible", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:47")
  public ArrayList<ReturnStmt> lambdaReturns() {
    ASTNode$State state = state();
    if (BlockLambdaBody_lambdaReturns_computed == ASTNode$State.NON_CYCLE || BlockLambdaBody_lambdaReturns_computed == state().cycle()) {
      return BlockLambdaBody_lambdaReturns_value;
    }
    BlockLambdaBody_lambdaReturns_value = lambdaReturns_compute();
    if (state().inCircle()) {
      BlockLambdaBody_lambdaReturns_computed = state().cycle();
    
    } else {
      BlockLambdaBody_lambdaReturns_computed = ASTNode$State.NON_CYCLE;
    
    }
    return BlockLambdaBody_lambdaReturns_value;
  }
  /** @apilevel internal */
  private ArrayList<ReturnStmt> lambdaReturns_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof Program)) {
      node = node.getParent();
    }
    Program root = (Program) node;
    root.survey_BlockLambdaBody_lambdaReturns();
    ArrayList<ReturnStmt> _computedValue = new ArrayList<ReturnStmt>();
    if (root.contributorMap_BlockLambdaBody_lambdaReturns.containsKey(this)) {
      for (ASTNode contributor : root.contributorMap_BlockLambdaBody_lambdaReturns.get(this)) {
        contributor.contributeTo_BlockLambdaBody_lambdaReturns(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle BlockLambdaBody_lambdaReturns_computed = null;

  /** @apilevel internal */
  protected ArrayList<ReturnStmt> BlockLambdaBody_lambdaReturns_value;

  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:195
    if (!voidCompatible() && !valueCompatible()) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    if (!voidCompatible() && !valueCompatible()) {
      collection.add(error("Block lambda bodies must be either void or value compatible"));
    }
  }
}
