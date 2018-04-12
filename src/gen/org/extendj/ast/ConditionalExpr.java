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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/grammar/Java.ast:186
 * @production ConditionalExpr : {@link Expr} ::= <span class="component">Condition:{@link Expr}</span> <span class="component">TrueExpr:{@link Expr}</span> <span class="component">FalseExpr:{@link Expr}</span>;

 */
public class ConditionalExpr extends Expr implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrint.jadd:246
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getCondition());
    out.print(" ? ");
    out.print(getTrueExpr());
    out.print(" : ");
    out.print(getFalseExpr());
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1243
   */
  public void createBCode(CodeGeneration gen) {
    int end_label = -1;
    int false_label = -1;
    if (!getCondition().isConstant()) {
      false_label = gen.constantPool().newLabel();
      getCondition().branchFalse(gen, false_label);
    }
    if (getCondition().canBeTrue()) {
      getTrueExpr().createBCode(gen);
      getTrueExpr().type().emitCastTo(this, gen, type());
      if (getCondition().canBeFalse()) {
        end_label = gen.constantPool().newLabel();
        gen.emitGoto(this, end_label);
        gen.changeStackDepth(-type().variableSize());
      }
    }
    if (false_label != -1) {
      gen.addLabel(false_label);
    }
    if (getCondition().canBeFalse()) {
      getFalseExpr().createBCode(gen);
      getFalseExpr().type().emitCastTo(this, gen, type());
    }
    if (end_label != -1) {
      gen.addLabel(end_label);
    }
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1334
   */
  public void branchTrue(CodeGeneration gen, int target) {
    // Branch when true.
    int else_label = -1;
    int end_label = -1;
    if (!getCondition().isConstant()) {
      else_label = gen.constantPool().newLabel();
      getCondition().branchFalse(gen, else_label);
    }
    if (getCondition().canBeTrue()) {
      getTrueExpr().branchTrue(gen, target);
      if (getCondition().canBeFalse() && getTrueExpr().canBeFalse()) {
        end_label = gen.constantPool().newLabel();
        gen.emitGoto(this, end_label);
        //gen.GOTO(end_label);
      }
    }
    if (else_label != -1) {
      gen.addLabel(else_label);
    }
    if (getCondition().canBeFalse()) {
      getFalseExpr().branchTrue(gen, target);
    }
    if (end_label != -1) {
      gen.addLabel(end_label);
    }
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1447
   */
  public void branchFalse(CodeGeneration gen, int target) {
    // Branch when false.
    int else_label = -1;
    int end_label = -1;
    if (!getCondition().isConstant()) {
      else_label = gen.constantPool().newLabel();
      getCondition().branchFalse(gen, else_label);
    }
    if (getCondition().canBeTrue()) {
      getTrueExpr().branchFalse(gen, target);
      if (getCondition().canBeFalse() && getTrueExpr().canBeTrue()) {
        end_label = gen.constantPool().newLabel();
        gen.emitGoto(this, end_label);
      }
    }
    if (else_label != -1) {
      gen.addLabel(else_label);
    }
    if (getCondition().canBeFalse()) {
      getFalseExpr().branchFalse(gen, target);
    }
    if (end_label != -1) {
      gen.addLabel(end_label);
    }
  }
  /**
   * @aspect AutoBoxingCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AutoBoxingCodegen.jrag:497
   */
  public void emitBooleanCondition(CodeGeneration gen) {
    super.emitBooleanCondition(gen);
    if (type().isReferenceType()) {
      type().emitBoxingOperation(this, gen);
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public ConditionalExpr() {
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
  }
  /**
   * @declaredat ASTNode:13
   */
  public ConditionalExpr(Expr p0, Expr p1, Expr p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:19
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:25
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:29
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    constant_reset();
    isConstant_reset();
    booleanOperator_reset();
    unassignedAfterTrue_Variable_reset();
    unassignedAfterFalse_Variable_reset();
    unassignedAfter_Variable_reset();
    type_reset();
    compatibleStrictContext_TypeDecl_reset();
    compatibleLooseContext_TypeDecl_reset();
    pertinentToApplicability_Expr_BodyDecl_int_reset();
    moreSpecificThan_TypeDecl_TypeDecl_reset();
    potentiallyCompatible_TypeDecl_BodyDecl_reset();
    isBooleanExpression_reset();
    isBooleanConditional_reset();
    isNumericExpression_reset();
    isNumericConditional_reset();
    isReferenceConditional_reset();
    isPolyExpression_reset();
    assignConversionTo_TypeDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:52
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:56
   */
  public ConditionalExpr clone() throws CloneNotSupportedException {
    ConditionalExpr node = (ConditionalExpr) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:61
   */
  public ConditionalExpr copy() {
    try {
      ConditionalExpr node = (ConditionalExpr) clone();
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
   * @declaredat ASTNode:80
   */
  @Deprecated
  public ConditionalExpr fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:90
   */
  public ConditionalExpr treeCopyNoTransform() {
    ConditionalExpr tree = (ConditionalExpr) copy();
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
   * @declaredat ASTNode:110
   */
  public ConditionalExpr treeCopy() {
    ConditionalExpr tree = (ConditionalExpr) copy();
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
   * @declaredat ASTNode:124
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the Condition child.
   * @param node The new node to replace the Condition child.
   * @apilevel high-level
   */
  public void setCondition(Expr node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Condition child.
   * @return The current node used as the Condition child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Condition")
  public Expr getCondition() {
    return (Expr) getChild(0);
  }
  /**
   * Retrieves the Condition child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Condition child.
   * @apilevel low-level
   */
  public Expr getConditionNoTransform() {
    return (Expr) getChildNoTransform(0);
  }
  /**
   * Replaces the TrueExpr child.
   * @param node The new node to replace the TrueExpr child.
   * @apilevel high-level
   */
  public void setTrueExpr(Expr node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the TrueExpr child.
   * @return The current node used as the TrueExpr child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="TrueExpr")
  public Expr getTrueExpr() {
    return (Expr) getChild(1);
  }
  /**
   * Retrieves the TrueExpr child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the TrueExpr child.
   * @apilevel low-level
   */
  public Expr getTrueExprNoTransform() {
    return (Expr) getChildNoTransform(1);
  }
  /**
   * Replaces the FalseExpr child.
   * @param node The new node to replace the FalseExpr child.
   * @apilevel high-level
   */
  public void setFalseExpr(Expr node) {
    setChild(node, 2);
  }
  /**
   * Retrieves the FalseExpr child.
   * @return The current node used as the FalseExpr child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="FalseExpr")
  public Expr getFalseExpr() {
    return (Expr) getChild(2);
  }
  /**
   * Retrieves the FalseExpr child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the FalseExpr child.
   * @apilevel low-level
   */
  public Expr getFalseExprNoTransform() {
    return (Expr) getChildNoTransform(2);
  }
  /**
   * @aspect AutoBoxing
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/AutoBoxing.jrag:237
   */
  private TypeDecl refined_AutoBoxing_ConditionalExpr_type()
{
    TypeDecl second = getTrueExpr().type();
    TypeDecl third = getFalseExpr().type();

    if (second == third) {
      return second;
    } else if (second.isReferenceType() && third.isNull()) {
      return second;
    } else if (second.isNull() && third.isReferenceType()) {
      return third;
    }

    if (second.isPrimitiveType()) {
      if (!second.isNull() && third.isNull()) {
        return second;
      }
    } else if (!second.unboxed().isUnknown()) {
      second = second.unboxed();
    }

    if (third.isPrimitiveType()) {
      if (!third.isNull() && second.isNull()) {
        return third;
      }
    } else if (!third.unboxed().isUnknown()) {
      third = third.unboxed();
    }

    return conditionalExprType(second, third);
  }
  /** @apilevel internal */
  private void constant_reset() {
    constant_computed = null;
    constant_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle constant_computed = null;

  /** @apilevel internal */
  protected Constant constant_value;

  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:85
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:85")
  public Constant constant() {
    ASTNode$State state = state();
    if (constant_computed == ASTNode$State.NON_CYCLE || constant_computed == state().cycle()) {
      return constant_value;
    }
    constant_value = type().questionColon(getCondition().constant(),
              getTrueExpr().constant(),getFalseExpr().constant());
    if (state().inCircle()) {
      constant_computed = state().cycle();
    
    } else {
      constant_computed = ASTNode$State.NON_CYCLE;
    
    }
    return constant_value;
  }
  /** @apilevel internal */
  private void isConstant_reset() {
    isConstant_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isConstant_computed = null;

  /** @apilevel internal */
  protected boolean isConstant_value;

  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:406
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:406")
  public boolean isConstant() {
    ASTNode$State state = state();
    if (isConstant_computed == ASTNode$State.NON_CYCLE || isConstant_computed == state().cycle()) {
      return isConstant_value;
    }
    isConstant_value = getCondition().isConstant() && getTrueExpr().isConstant() && getFalseExpr().isConstant();
    if (state().inCircle()) {
      isConstant_computed = state().cycle();
    
    } else {
      isConstant_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isConstant_value;
  }
  /** @apilevel internal */
  private void booleanOperator_reset() {
    booleanOperator_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle booleanOperator_computed = null;

  /** @apilevel internal */
  protected boolean booleanOperator_value;

  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:253
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:253")
  public boolean booleanOperator() {
    ASTNode$State state = state();
    if (booleanOperator_computed == ASTNode$State.NON_CYCLE || booleanOperator_computed == state().cycle()) {
      return booleanOperator_value;
    }
    booleanOperator_value = getTrueExpr().type().isBoolean() && getFalseExpr().type().isBoolean();
    if (state().inCircle()) {
      booleanOperator_computed = state().cycle();
    
    } else {
      booleanOperator_computed = ASTNode$State.NON_CYCLE;
    
    }
    return booleanOperator_value;
  }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:378
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:378")
  public boolean assignedAfterTrue(Variable v) {
    boolean assignedAfterTrue_Variable_value = isFalse() || (getTrueExpr().assignedAfterTrue(v) && getFalseExpr().assignedAfterTrue(v));
    return assignedAfterTrue_Variable_value;
  }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:380
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:380")
  public boolean assignedAfterFalse(Variable v) {
    boolean assignedAfterFalse_Variable_value = isTrue() || (getTrueExpr().assignedAfterFalse(v) && getFalseExpr().assignedAfterFalse(v));
    return assignedAfterFalse_Variable_value;
  }
  /**
   * @attribute syn
   * @aspect DefiniteAssignment
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:268
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:268")
  public boolean assignedAfter(Variable v) {
    boolean assignedAfter_Variable_value = booleanOperator()
          ? assignedAfterTrue(v) && assignedAfterFalse(v)
          : getTrueExpr().assignedAfter(v) && getFalseExpr().assignedAfter(v);
    return assignedAfter_Variable_value;
  }
  /** @apilevel internal */
  private void unassignedAfterTrue_Variable_reset() {
    unassignedAfterTrue_Variable_values = null;
  }
  protected java.util.Map unassignedAfterTrue_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:909")
  public boolean unassignedAfterTrue(Variable v) {
    Object _parameters = v;
    if (unassignedAfterTrue_Variable_values == null) unassignedAfterTrue_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (unassignedAfterTrue_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfterTrue_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      unassignedAfterTrue_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfterTrue_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfterTrue_Variable_value = getTrueExpr().unassignedAfterTrue(v) && getFalseExpr().unassignedAfterTrue(v);
        if (new_unassignedAfterTrue_Variable_value != ((Boolean)_value.value)) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfterTrue_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfterTrue_Variable_values.put(_parameters, new_unassignedAfterTrue_Variable_value);

      state.leaveCircle();
      return new_unassignedAfterTrue_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfterTrue_Variable_value = getTrueExpr().unassignedAfterTrue(v) && getFalseExpr().unassignedAfterTrue(v);
      if (new_unassignedAfterTrue_Variable_value != ((Boolean)_value.value)) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfterTrue_Variable_value;
      }
      return new_unassignedAfterTrue_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private void unassignedAfterFalse_Variable_reset() {
    unassignedAfterFalse_Variable_values = null;
  }
  protected java.util.Map unassignedAfterFalse_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:911")
  public boolean unassignedAfterFalse(Variable v) {
    Object _parameters = v;
    if (unassignedAfterFalse_Variable_values == null) unassignedAfterFalse_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (unassignedAfterFalse_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfterFalse_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      unassignedAfterFalse_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfterFalse_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfterFalse_Variable_value = getTrueExpr().unassignedAfterFalse(v) && getFalseExpr().unassignedAfterFalse(v);
        if (new_unassignedAfterFalse_Variable_value != ((Boolean)_value.value)) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfterFalse_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfterFalse_Variable_values.put(_parameters, new_unassignedAfterFalse_Variable_value);

      state.leaveCircle();
      return new_unassignedAfterFalse_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfterFalse_Variable_value = getTrueExpr().unassignedAfterFalse(v) && getFalseExpr().unassignedAfterFalse(v);
      if (new_unassignedAfterFalse_Variable_value != ((Boolean)_value.value)) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfterFalse_Variable_value;
      }
      return new_unassignedAfterFalse_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private void unassignedAfter_Variable_reset() {
    unassignedAfter_Variable_values = null;
  }
  protected java.util.Map unassignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:903")
  public boolean unassignedAfter(Variable v) {
    Object _parameters = v;
    if (unassignedAfter_Variable_values == null) unassignedAfter_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (unassignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      unassignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfter_Variable_value = booleanOperator()
              ? unassignedAfterTrue(v) && unassignedAfterFalse(v)
              : getTrueExpr().unassignedAfter(v) && getFalseExpr().unassignedAfter(v);
        if (new_unassignedAfter_Variable_value != ((Boolean)_value.value)) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = booleanOperator()
            ? unassignedAfterTrue(v) && unassignedAfterFalse(v)
            : getTrueExpr().unassignedAfter(v) && getFalseExpr().unassignedAfter(v);
      if (new_unassignedAfter_Variable_value != ((Boolean)_value.value)) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfter_Variable_value;
      }
      return new_unassignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private void type_reset() {
    type_computed = null;
    type_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle type_computed = null;

  /** @apilevel internal */
  protected TypeDecl type_value;

  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:296
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:296")
  public TypeDecl type() {
    ASTNode$State state = state();
    if (type_computed == ASTNode$State.NON_CYCLE || type_computed == state().cycle()) {
      return type_value;
    }
    type_value = type_compute();
    if (state().inCircle()) {
      type_computed = state().cycle();
    
    } else {
      type_computed = ASTNode$State.NON_CYCLE;
    
    }
    return type_value;
  }
  /** @apilevel internal */
  private TypeDecl type_compute() {
      TypeDecl type = refined_AutoBoxing_ConditionalExpr_type();
      if (type.isUnknown()) {
        TypeDecl trueType = getTrueExpr().type();
        TypeDecl falseType = getFalseExpr().type();
        if (!trueType.isReferenceType() && !trueType.boxed().isUnknown()) {
          trueType = trueType.boxed();
        }
        if (!falseType.isReferenceType() && !falseType.boxed().isUnknown()) {
          falseType = falseType.boxed();
        }
        ArrayList<TypeDecl> list = new ArrayList<TypeDecl>();
        list.add(trueType);
        list.add(falseType);
        return type.lookupLUBType(list).lub();
      }
      return type;
    }
  /**
   * Returns the type of a conditional expression, given the types of the
   * second and third operands.
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:396
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:396")
  public TypeDecl conditionalExprType(TypeDecl second, TypeDecl third) {
    {
        if (second == third) {
          return second;
        }
    
        if (second.isNumericType() && third.isNumericType()) {
          if (second.isByte() && third.isShort()) {
            return third;
          }
          if (second.isShort() && third.isByte()) {
            return second;
          }
          if ((second.isByte() || second.isShort() || second.isChar())
              && third.isInt() && getFalseExpr().isConstant()
              && getFalseExpr().representableIn(second)) {
            return second;
          }
          if ((third.isByte() || third.isShort() || third.isChar())
              && second.isInt() && getTrueExpr().isConstant()
              && getTrueExpr().representableIn(third)) {
            return third;
          }
          return second.binaryNumericPromotion(third);
        } else if (second.isBoolean() && third.isBoolean()) {
          return second;
        } else if (second.isReferenceType() && third.isNull()) {
          return second;
        } else if (second.isNull() && third.isReferenceType()) {
          return third;
        } else if (second.isReferenceType() && third.isReferenceType()) {
          if (second.assignConversionTo(third, null)) {
            return third;
          }
          if (third.assignConversionTo(second, null)) {
            return second;
          }
          return unknownType();
        } else {
          return unknownType();
        }
      }
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:145
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:145")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getCondition().modifiedInScope(var)
          || getTrueExpr().modifiedInScope(var)
          || getFalseExpr().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /** @apilevel internal */
  private void compatibleStrictContext_TypeDecl_reset() {
    compatibleStrictContext_TypeDecl_computed = new java.util.HashMap(4);
    compatibleStrictContext_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map compatibleStrictContext_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map compatibleStrictContext_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodSignature.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodSignature.jrag:32")
  public boolean compatibleStrictContext(TypeDecl type) {
    Object _parameters = type;
    if (compatibleStrictContext_TypeDecl_computed == null) compatibleStrictContext_TypeDecl_computed = new java.util.HashMap(4);
    if (compatibleStrictContext_TypeDecl_values == null) compatibleStrictContext_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (compatibleStrictContext_TypeDecl_values.containsKey(_parameters) && compatibleStrictContext_TypeDecl_computed != null
        && compatibleStrictContext_TypeDecl_computed.containsKey(_parameters)
        && (compatibleStrictContext_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || compatibleStrictContext_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) compatibleStrictContext_TypeDecl_values.get(_parameters);
    }
    boolean compatibleStrictContext_TypeDecl_value = compatibleStrictContext_compute(type);
    if (state().inCircle()) {
      compatibleStrictContext_TypeDecl_values.put(_parameters, compatibleStrictContext_TypeDecl_value);
      compatibleStrictContext_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      compatibleStrictContext_TypeDecl_values.put(_parameters, compatibleStrictContext_TypeDecl_value);
      compatibleStrictContext_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return compatibleStrictContext_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean compatibleStrictContext_compute(TypeDecl type) {
      if (isPolyExpression()) {
        return getTrueExpr().compatibleStrictContext(type)
            && getFalseExpr().compatibleStrictContext(type);
      } else {
        return super.compatibleStrictContext(type);
      }
    }
  /** @apilevel internal */
  private void compatibleLooseContext_TypeDecl_reset() {
    compatibleLooseContext_TypeDecl_computed = new java.util.HashMap(4);
    compatibleLooseContext_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map compatibleLooseContext_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map compatibleLooseContext_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodSignature.jrag:76
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodSignature.jrag:76")
  public boolean compatibleLooseContext(TypeDecl type) {
    Object _parameters = type;
    if (compatibleLooseContext_TypeDecl_computed == null) compatibleLooseContext_TypeDecl_computed = new java.util.HashMap(4);
    if (compatibleLooseContext_TypeDecl_values == null) compatibleLooseContext_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (compatibleLooseContext_TypeDecl_values.containsKey(_parameters) && compatibleLooseContext_TypeDecl_computed != null
        && compatibleLooseContext_TypeDecl_computed.containsKey(_parameters)
        && (compatibleLooseContext_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || compatibleLooseContext_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) compatibleLooseContext_TypeDecl_values.get(_parameters);
    }
    boolean compatibleLooseContext_TypeDecl_value = compatibleLooseContext_compute(type);
    if (state().inCircle()) {
      compatibleLooseContext_TypeDecl_values.put(_parameters, compatibleLooseContext_TypeDecl_value);
      compatibleLooseContext_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      compatibleLooseContext_TypeDecl_values.put(_parameters, compatibleLooseContext_TypeDecl_value);
      compatibleLooseContext_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return compatibleLooseContext_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean compatibleLooseContext_compute(TypeDecl type) {
      if (isPolyExpression()) {
        return getTrueExpr().compatibleLooseContext(type)
            && getFalseExpr().compatibleLooseContext(type);
      } else {
        return super.compatibleLooseContext(type);
      }
    }
  /** @apilevel internal */
  private void pertinentToApplicability_Expr_BodyDecl_int_reset() {
    pertinentToApplicability_Expr_BodyDecl_int_computed = new java.util.HashMap(4);
    pertinentToApplicability_Expr_BodyDecl_int_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map pertinentToApplicability_Expr_BodyDecl_int_values;
  /** @apilevel internal */
  protected java.util.Map pertinentToApplicability_Expr_BodyDecl_int_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodSignature.jrag:104
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodSignature.jrag:104")
  public boolean pertinentToApplicability(Expr access, BodyDecl decl, int argIndex) {
    java.util.List _parameters = new java.util.ArrayList(3);
    _parameters.add(access);
    _parameters.add(decl);
    _parameters.add(argIndex);
    if (pertinentToApplicability_Expr_BodyDecl_int_computed == null) pertinentToApplicability_Expr_BodyDecl_int_computed = new java.util.HashMap(4);
    if (pertinentToApplicability_Expr_BodyDecl_int_values == null) pertinentToApplicability_Expr_BodyDecl_int_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (pertinentToApplicability_Expr_BodyDecl_int_values.containsKey(_parameters) && pertinentToApplicability_Expr_BodyDecl_int_computed != null
        && pertinentToApplicability_Expr_BodyDecl_int_computed.containsKey(_parameters)
        && (pertinentToApplicability_Expr_BodyDecl_int_computed.get(_parameters) == ASTNode$State.NON_CYCLE || pertinentToApplicability_Expr_BodyDecl_int_computed.get(_parameters) == state().cycle())) {
      return (Boolean) pertinentToApplicability_Expr_BodyDecl_int_values.get(_parameters);
    }
    boolean pertinentToApplicability_Expr_BodyDecl_int_value = getFalseExpr().pertinentToApplicability(access, decl, argIndex)
          && getTrueExpr().pertinentToApplicability(access, decl, argIndex);
    if (state().inCircle()) {
      pertinentToApplicability_Expr_BodyDecl_int_values.put(_parameters, pertinentToApplicability_Expr_BodyDecl_int_value);
      pertinentToApplicability_Expr_BodyDecl_int_computed.put(_parameters, state().cycle());
    
    } else {
      pertinentToApplicability_Expr_BodyDecl_int_values.put(_parameters, pertinentToApplicability_Expr_BodyDecl_int_value);
      pertinentToApplicability_Expr_BodyDecl_int_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return pertinentToApplicability_Expr_BodyDecl_int_value;
  }
  /** @apilevel internal */
  private void moreSpecificThan_TypeDecl_TypeDecl_reset() {
    moreSpecificThan_TypeDecl_TypeDecl_computed = new java.util.HashMap(4);
    moreSpecificThan_TypeDecl_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map moreSpecificThan_TypeDecl_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map moreSpecificThan_TypeDecl_TypeDecl_computed;
  /**
   * Computes which type is more specific for a specific argument, as defined in 15.12.2.5
   * @param type1
   * @param type2
   * @return {@code true} if type1 is more specific than type2, {@code false} otherwise
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodSignature.jrag:230
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodSignature.jrag:230")
  public boolean moreSpecificThan(TypeDecl type1, TypeDecl type2) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(type1);
    _parameters.add(type2);
    if (moreSpecificThan_TypeDecl_TypeDecl_computed == null) moreSpecificThan_TypeDecl_TypeDecl_computed = new java.util.HashMap(4);
    if (moreSpecificThan_TypeDecl_TypeDecl_values == null) moreSpecificThan_TypeDecl_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (moreSpecificThan_TypeDecl_TypeDecl_values.containsKey(_parameters) && moreSpecificThan_TypeDecl_TypeDecl_computed != null
        && moreSpecificThan_TypeDecl_TypeDecl_computed.containsKey(_parameters)
        && (moreSpecificThan_TypeDecl_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || moreSpecificThan_TypeDecl_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) moreSpecificThan_TypeDecl_TypeDecl_values.get(_parameters);
    }
    boolean moreSpecificThan_TypeDecl_TypeDecl_value = moreSpecificThan_compute(type1, type2);
    if (state().inCircle()) {
      moreSpecificThan_TypeDecl_TypeDecl_values.put(_parameters, moreSpecificThan_TypeDecl_TypeDecl_value);
      moreSpecificThan_TypeDecl_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      moreSpecificThan_TypeDecl_TypeDecl_values.put(_parameters, moreSpecificThan_TypeDecl_TypeDecl_value);
      moreSpecificThan_TypeDecl_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return moreSpecificThan_TypeDecl_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean moreSpecificThan_compute(TypeDecl type1, TypeDecl type2) {
      if (super.moreSpecificThan(type1, type2)) {
        return true;
      }
      return getTrueExpr().moreSpecificThan(type1, type2)
          && getFalseExpr().moreSpecificThan(type1, type2);
    }
  /** @apilevel internal */
  private void potentiallyCompatible_TypeDecl_BodyDecl_reset() {
    potentiallyCompatible_TypeDecl_BodyDecl_computed = new java.util.HashMap(4);
    potentiallyCompatible_TypeDecl_BodyDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map potentiallyCompatible_TypeDecl_BodyDecl_values;
  /** @apilevel internal */
  protected java.util.Map potentiallyCompatible_TypeDecl_BodyDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodSignature18
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodSignature.jrag:465
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature18", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodSignature.jrag:465")
  public boolean potentiallyCompatible(TypeDecl type, BodyDecl candidateDecl) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(type);
    _parameters.add(candidateDecl);
    if (potentiallyCompatible_TypeDecl_BodyDecl_computed == null) potentiallyCompatible_TypeDecl_BodyDecl_computed = new java.util.HashMap(4);
    if (potentiallyCompatible_TypeDecl_BodyDecl_values == null) potentiallyCompatible_TypeDecl_BodyDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (potentiallyCompatible_TypeDecl_BodyDecl_values.containsKey(_parameters) && potentiallyCompatible_TypeDecl_BodyDecl_computed != null
        && potentiallyCompatible_TypeDecl_BodyDecl_computed.containsKey(_parameters)
        && (potentiallyCompatible_TypeDecl_BodyDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || potentiallyCompatible_TypeDecl_BodyDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) potentiallyCompatible_TypeDecl_BodyDecl_values.get(_parameters);
    }
    boolean potentiallyCompatible_TypeDecl_BodyDecl_value = potentiallyCompatible_compute(type, candidateDecl);
    if (state().inCircle()) {
      potentiallyCompatible_TypeDecl_BodyDecl_values.put(_parameters, potentiallyCompatible_TypeDecl_BodyDecl_value);
      potentiallyCompatible_TypeDecl_BodyDecl_computed.put(_parameters, state().cycle());
    
    } else {
      potentiallyCompatible_TypeDecl_BodyDecl_values.put(_parameters, potentiallyCompatible_TypeDecl_BodyDecl_value);
      potentiallyCompatible_TypeDecl_BodyDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return potentiallyCompatible_TypeDecl_BodyDecl_value;
  }
  /** @apilevel internal */
  private boolean potentiallyCompatible_compute(TypeDecl type, BodyDecl candidateDecl) {
      if (!isPolyExpression()) {
        return true;
      }
      return getTrueExpr().potentiallyCompatible(type, candidateDecl)
          && getFalseExpr().potentiallyCompatible(type, candidateDecl);
    }
  /** @apilevel internal */
  private void isBooleanExpression_reset() {
    isBooleanExpression_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isBooleanExpression_computed = null;

  /** @apilevel internal */
  protected boolean isBooleanExpression_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:29
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:29")
  public boolean isBooleanExpression() {
    ASTNode$State state = state();
    if (isBooleanExpression_computed == ASTNode$State.NON_CYCLE || isBooleanExpression_computed == state().cycle()) {
      return isBooleanExpression_value;
    }
    isBooleanExpression_value = isBooleanConditional();
    if (state().inCircle()) {
      isBooleanExpression_computed = state().cycle();
    
    } else {
      isBooleanExpression_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isBooleanExpression_value;
  }
  /** @apilevel internal */
  private void isBooleanConditional_reset() {
    isBooleanConditional_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isBooleanConditional_computed = null;

  /** @apilevel internal */
  protected boolean isBooleanConditional_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:55
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:55")
  public boolean isBooleanConditional() {
    ASTNode$State state = state();
    if (isBooleanConditional_computed == ASTNode$State.NON_CYCLE || isBooleanConditional_computed == state().cycle()) {
      return isBooleanConditional_value;
    }
    isBooleanConditional_value = getTrueExpr().isBooleanExpression() && getFalseExpr().isBooleanExpression()
          || getTrueExpr().isBooleanExpression() && getFalseExpr().isNullLiteral()
          || getFalseExpr().isBooleanExpression() && getTrueExpr().isNullLiteral();
    if (state().inCircle()) {
      isBooleanConditional_computed = state().cycle();
    
    } else {
      isBooleanConditional_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isBooleanConditional_value;
  }
  /** @apilevel internal */
  private void isNumericExpression_reset() {
    isNumericExpression_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isNumericExpression_computed = null;

  /** @apilevel internal */
  protected boolean isNumericExpression_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:60
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:60")
  public boolean isNumericExpression() {
    ASTNode$State state = state();
    if (isNumericExpression_computed == ASTNode$State.NON_CYCLE || isNumericExpression_computed == state().cycle()) {
      return isNumericExpression_value;
    }
    isNumericExpression_value = isNumericConditional();
    if (state().inCircle()) {
      isNumericExpression_computed = state().cycle();
    
    } else {
      isNumericExpression_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isNumericExpression_value;
  }
  /** @apilevel internal */
  private void isNumericConditional_reset() {
    isNumericConditional_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isNumericConditional_computed = null;

  /** @apilevel internal */
  protected boolean isNumericConditional_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:78")
  public boolean isNumericConditional() {
    ASTNode$State state = state();
    if (isNumericConditional_computed == ASTNode$State.NON_CYCLE || isNumericConditional_computed == state().cycle()) {
      return isNumericConditional_value;
    }
    isNumericConditional_value = getTrueExpr().isNumericExpression() && getFalseExpr().isNumericExpression()
          || getTrueExpr().isNumericExpression() && getFalseExpr().isNullLiteral()
          || getFalseExpr().isNumericExpression() && getTrueExpr().isNullLiteral();
    if (state().inCircle()) {
      isNumericConditional_computed = state().cycle();
    
    } else {
      isNumericConditional_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isNumericConditional_value;
  }
  /** @apilevel internal */
  private void isReferenceConditional_reset() {
    isReferenceConditional_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isReferenceConditional_computed = null;

  /** @apilevel internal */
  protected boolean isReferenceConditional_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:83
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:83")
  public boolean isReferenceConditional() {
    ASTNode$State state = state();
    if (isReferenceConditional_computed == ASTNode$State.NON_CYCLE || isReferenceConditional_computed == state().cycle()) {
      return isReferenceConditional_value;
    }
    isReferenceConditional_value = !isBooleanConditional() && !isNumericConditional();
    if (state().inCircle()) {
      isReferenceConditional_computed = state().cycle();
    
    } else {
      isReferenceConditional_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isReferenceConditional_value;
  }
  /** @apilevel internal */
  private void isPolyExpression_reset() {
    isPolyExpression_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle isPolyExpression_computed = null;

  /** @apilevel internal */
  protected boolean isPolyExpression_value;

  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:86
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:86")
  public boolean isPolyExpression() {
    ASTNode$State state = state();
    if (isPolyExpression_computed == ASTNode$State.NON_CYCLE || isPolyExpression_computed == state().cycle()) {
      return isPolyExpression_value;
    }
    isPolyExpression_value = isReferenceConditional() && (assignmentContext() || invocationContext());
    if (state().inCircle()) {
      isPolyExpression_computed = state().cycle();
    
    } else {
      isPolyExpression_computed = ASTNode$State.NON_CYCLE;
    
    }
    return isPolyExpression_value;
  }
  /** @apilevel internal */
  private void assignConversionTo_TypeDecl_reset() {
    assignConversionTo_TypeDecl_computed = new java.util.HashMap(4);
    assignConversionTo_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map assignConversionTo_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map assignConversionTo_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect PolyExpressions
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:149
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PolyExpressions", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/PolyExpressions.jrag:149")
  public boolean assignConversionTo(TypeDecl type) {
    Object _parameters = type;
    if (assignConversionTo_TypeDecl_computed == null) assignConversionTo_TypeDecl_computed = new java.util.HashMap(4);
    if (assignConversionTo_TypeDecl_values == null) assignConversionTo_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (assignConversionTo_TypeDecl_values.containsKey(_parameters) && assignConversionTo_TypeDecl_computed != null
        && assignConversionTo_TypeDecl_computed.containsKey(_parameters)
        && (assignConversionTo_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || assignConversionTo_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) assignConversionTo_TypeDecl_values.get(_parameters);
    }
    boolean assignConversionTo_TypeDecl_value = assignConversionTo_compute(type);
    if (state().inCircle()) {
      assignConversionTo_TypeDecl_values.put(_parameters, assignConversionTo_TypeDecl_value);
      assignConversionTo_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      assignConversionTo_TypeDecl_values.put(_parameters, assignConversionTo_TypeDecl_value);
      assignConversionTo_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return assignConversionTo_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean assignConversionTo_compute(TypeDecl type) {
      if (!isPolyExpression()) {
        return type().assignConversionTo(type, this);
      } else {
        return getTrueExpr().assignConversionTo(type) && getFalseExpr().assignConversionTo(type);
      }
    }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1189
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1189")
  public boolean canBeTrue() {
    boolean canBeTrue_value = type().isBoolean()
          && (getCondition().canBeTrue() && getTrueExpr().canBeTrue()
              || getCondition().canBeFalse() && getFalseExpr().canBeTrue());
    return canBeTrue_value;
  }
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1201
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:1201")
  public boolean canBeFalse() {
    boolean canBeFalse_value = type().isBoolean()
          && (getCondition().canBeTrue() && getTrueExpr().canBeFalse()
              || getCondition().canBeFalse() && getFalseExpr().canBeFalse());
    return canBeFalse_value;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getFalseExprNoTransform() != null && _callerNode == getFalseExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:466
      return getCondition().assignedAfterFalse(v);
    }
    else if (getTrueExprNoTransform() != null && _callerNode == getTrueExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:464
      return getCondition().assignedAfterTrue(v);
    }
    else if (getConditionNoTransform() != null && _callerNode == getCondition()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:462
      return assignedBefore(v);
    }
    else {
      return getParent().Define_assignedBefore(this, _callerNode, v);
    }
  }
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:891
   * @apilevel internal
   */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getFalseExprNoTransform() != null && _callerNode == getFalseExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1062
      return getCondition().unassignedAfterFalse(v);
    }
    else if (getTrueExprNoTransform() != null && _callerNode == getTrueExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1059
      return getCondition().unassignedAfterTrue(v);
    }
    else if (getConditionNoTransform() != null && _callerNode == getCondition()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1057
      return unassignedBefore(v);
    }
    else {
      return getParent().Define_unassignedBefore(this, _callerNode, v);
    }
  }
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:30
   * @apilevel internal
   */
  public TypeDecl Define_targetType(ASTNode _callerNode, ASTNode _childNode) {
    if (getFalseExprNoTransform() != null && _callerNode == getFalseExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:35
      return targetType();
    }
    else if (getTrueExprNoTransform() != null && _callerNode == getTrueExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:34
      return targetType();
    }
    else {
      return getParent().Define_targetType(this, _callerNode);
    }
  }
  protected boolean canDefine_targetType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:195
   * @apilevel internal
   */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getConditionNoTransform() != null && _callerNode == getCondition()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:257
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
    if (getConditionNoTransform() != null && _callerNode == getCondition()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:258
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
    if (getFalseExprNoTransform() != null && _callerNode == getFalseExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:269
      return false;
    }
    else if (getTrueExprNoTransform() != null && _callerNode == getTrueExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:264
      return false;
    }
    else if (getConditionNoTransform() != null && _callerNode == getCondition()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:259
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:199
   * @apilevel internal
   */
  public boolean Define_numericContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getFalseExprNoTransform() != null && _callerNode == getFalseExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:271
      return false;
    }
    else if (getTrueExprNoTransform() != null && _callerNode == getTrueExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:266
      return false;
    }
    else if (getConditionNoTransform() != null && _callerNode == getCondition()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:260
      return false;
    }
    else {
      return getParent().Define_numericContext(this, _callerNode);
    }
  }
  protected boolean canDefine_numericContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:198
   * @apilevel internal
   */
  public boolean Define_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getFalseExprNoTransform() != null && _callerNode == getFalseExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:270
      return false;
    }
    else if (getTrueExprNoTransform() != null && _callerNode == getTrueExpr()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:265
      return false;
    }
    else if (getConditionNoTransform() != null && _callerNode == getCondition()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:261
      return false;
    }
    else {
      return getParent().Define_stringContext(this, _callerNode);
    }
  }
  protected boolean canDefine_stringContext(ASTNode _callerNode, ASTNode _childNode) {
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
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:738
    if (!getCondition().type().isBoolean()) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:743
    if (type().isUnknown() && !getTrueExpr().type().isUnknown()
              && !getFalseExpr().type().isUnknown()) {
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
    if (!getCondition().type().isBoolean()) {
      collection.add(error("The first operand of a conditional expression must be a boolean"));
    }
    if (type().isUnknown() && !getTrueExpr().type().isUnknown()
              && !getFalseExpr().type().isUnknown()) {
      collection.add(error("The types of the second and third operand in "
                + "this conditional expression do not match"));
    }
  }
}
