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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/grammar/ConstructorReference.ast:1
 * @production ConstructorReference : {@link Expr} ::= <span class="component">TypeAccess:{@link Access}</span>;

 */
public abstract class ConstructorReference extends Expr implements Cloneable {
  /**
   * @aspect Java8CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/CreateBCode.jrag:41
   */
  public void createBCode(CodeGeneration gen) {
    toClass().createBCode(gen);
  }
  /**
   * @declaredat ASTNode:1
   */
  public ConstructorReference() {
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
  public ConstructorReference(Access p0) {
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
    compatibleStrictContext_TypeDecl_reset();
    compatibleLooseContext_TypeDecl_reset();
    pertinentToApplicability_Expr_BodyDecl_int_reset();
    moreSpecificThan_TypeDecl_TypeDecl_reset();
    potentiallyCompatible_TypeDecl_BodyDecl_reset();
    isPolyExpression_reset();
    assignConversionTo_TypeDecl_reset();
    targetInterface_reset();
    type_reset();
    toClass_reset();
    toParameterList_reset();
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
  public ConstructorReference clone() throws CloneNotSupportedException {
    ConstructorReference node = (ConstructorReference) super.clone();
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
  public abstract ConstructorReference fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:65
   */
  public abstract ConstructorReference treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:73
   */
  public abstract ConstructorReference treeCopy();
  /**
   * Replaces the TypeAccess child.
   * @param node The new node to replace the TypeAccess child.
   * @apilevel high-level
   */
  public void setTypeAccess(Access node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the TypeAccess child.
   * @return The current node used as the TypeAccess child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="TypeAccess")
  public Access getTypeAccess() {
    return (Access) getChild(0);
  }
  /**
   * Retrieves the TypeAccess child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the TypeAccess child.
   * @apilevel low-level
   */
  public Access getTypeAccessNoTransform() {
    return (Access) getChildNoTransform(0);
  }
  /**
   * @attribute syn
   * @aspect ConstructorReference
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/ConstructorReference.jrag:68
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorReference", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/ConstructorReference.jrag:68")
  public abstract boolean congruentTo(FunctionDescriptor f);
  /**
   * @attribute syn
   * @aspect ConstructorReference
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/ConstructorReference.jrag:127
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorReference", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/ConstructorReference.jrag:127")
  public abstract boolean isExact();
  /**
   * @attribute syn
   * @aspect ConstructorReferenceToClass
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/ConstructorReferenceToClass.jrag:93
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorReferenceToClass", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/ConstructorReferenceToClass.jrag:93")
  public abstract Block toBlock();
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
      if (!type.isFunctionalInterface()) {
        return false;
      }
      InterfaceDecl iDecl = (InterfaceDecl) type;
      return congruentTo(iDecl.functionDescriptor());
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
    boolean compatibleLooseContext_TypeDecl_value = compatibleStrictContext(type);
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
    boolean pertinentToApplicability_Expr_BodyDecl_int_value = pertinentToApplicability_compute(access, decl, argIndex);
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
  private boolean pertinentToApplicability_compute(Expr access, BodyDecl decl, int argIndex) {
      if (!isExact()) {
        return false;
      }
      if (decl instanceof MethodDecl
          && decl.isGeneric()
          && !(access instanceof ParMethodAccess)
          && ((MethodDecl) decl).genericDecl().getParameter(argIndex).type().isTypeVariable()) {
        GenericMethodDecl genericDecl = ((MethodDecl) decl).genericDecl();
        TypeVariable typeVar = (TypeVariable) genericDecl.getParameter(argIndex).type();
        for (int i = 0; i < genericDecl.getNumTypeParameter(); i++) {
          if (typeVar == genericDecl.getTypeParameter(i)) {
            return false;
          }
        }
      } else if (decl instanceof ConstructorDecl
          && decl.isGeneric()
          && !(access instanceof ParConstructorAccess)
          && !(access instanceof ParSuperConstructorAccess)
          && !(access instanceof ParClassInstanceExpr)
          && ((ConstructorDecl) decl).genericDecl().getParameter(argIndex).type().isTypeVariable()) {
        GenericConstructorDecl genericDecl = ((ConstructorDecl) decl).genericDecl();
        TypeVariable typeVar = (TypeVariable) genericDecl.getParameter(argIndex).type();
        for (int i = 0; i < genericDecl.getNumTypeParameter(); i++) {
          if (typeVar == genericDecl.getTypeParameter(i)) {
            return false;
          }
        }
      }
      return true;
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
      if (!type1.isFunctionalInterface() || !type2.isFunctionalInterface()) {
        return false;
      }
      if (type2.instanceOf(type1)) {
        return false;
      }
      InterfaceDecl iDecl1 = (InterfaceDecl) type1;
      InterfaceDecl iDecl2 = (InterfaceDecl) type2;
  
      if (!isExact()) {
        return false;
      }
  
      FunctionDescriptor f1 = iDecl1.functionDescriptor();
      FunctionDescriptor f2 = iDecl2.functionDescriptor();
  
      if (f1.method.arity() != f2.method.arity()) {
        return false;
      }
  
      for (int i = 0; i < f1.method.getNumParameter(); i++) {
        if (f1.method.getParameter(i).type() != f2.method.getParameter(i).type()) {
          return false;
        }
      }
  
      // First bullet
      if (f2.method.type().isVoid()) {
        return true;
      }
  
      // Second bullet
      if (f1.method.type().instanceOf(f2.method.type())) {
        return true;
      }
  
      // Third bullet
      if (f1.method.type().isPrimitiveType() && f2.method.type().isReferenceType()) {
        // A constructor can never have primitive return type
        return false;
      }
  
      // Fourth bullet
      if (f1.method.type().isReferenceType() && f2.method.type().isPrimitiveType()) {
        // A constructor always have reference return type
        return true;
      }
  
      return false;
  
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
      if (type.isTypeVariable()) {
        if (candidateDecl.isGeneric()) {
          boolean foundTypeVariable = false;
          List<TypeVariable> typeParams = candidateDecl.typeParameters();
          for (int i = 0; i < typeParams.getNumChild(); i++) {
            if (type == typeParams.getChild(i)) {
              foundTypeVariable = true;
              break;
            }
          }
          return foundTypeVariable;
        } else {
          return false;
        }
      }
  
      if (!type.isFunctionalInterface()) {
        return false;
      }
      return true;
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
    isPolyExpression_value = true;
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
      if (!type.isFunctionalInterface()) {
        return false;
      }
      FunctionDescriptor f = ((InterfaceDecl) type).functionDescriptor();
      return congruentTo(f);
    }
  /** @apilevel internal */
  private void targetInterface_reset() {
    targetInterface_computed = null;
    targetInterface_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle targetInterface_computed = null;

  /** @apilevel internal */
  protected InterfaceDecl targetInterface_value;

  /**
   * @attribute syn
   * @aspect TargetType
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:161
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TargetType", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:161")
  public InterfaceDecl targetInterface() {
    ASTNode$State state = state();
    if (targetInterface_computed == ASTNode$State.NON_CYCLE || targetInterface_computed == state().cycle()) {
      return targetInterface_value;
    }
    targetInterface_value = targetInterface_compute();
    if (state().inCircle()) {
      targetInterface_computed = state().cycle();
    
    } else {
      targetInterface_computed = ASTNode$State.NON_CYCLE;
    
    }
    return targetInterface_value;
  }
  /** @apilevel internal */
  private InterfaceDecl targetInterface_compute() {
      if (targetType().isNull()) {
        return null;
      } else if (!(targetType() instanceof InterfaceDecl)) {
        return null;
      } else {
        return (InterfaceDecl) targetType();
      }
    }
/** @apilevel internal */
protected ASTNode$State.Cycle type_cycle = null;
  /** @apilevel internal */
  private void type_reset() {
    type_computed = false;
    type_initialized = false;
    type_value = null;
    type_cycle = null;
  }
  /** @apilevel internal */
  protected boolean type_computed = false;

  /** @apilevel internal */
  protected TypeDecl type_value;
  /** @apilevel internal */
  protected boolean type_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:97")
  public TypeDecl type() {
    if (type_computed) {
      return type_value;
    }
    ASTNode$State state = state();
    if (!type_initialized) {
      type_initialized = true;
      type_value = unknownType();
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        type_cycle = state.nextCycle();
        TypeDecl new_type_value = type_compute();
        if ((new_type_value == null && type_value != null) || (new_type_value != null && !new_type_value.equals(type_value))) {
          state.setChangeInCycle();
        }
        type_value = new_type_value;
      } while (state.testAndClearChangeInCycle());
      type_computed = true;

      state.leaveCircle();
    } else if (type_cycle != state.cycle()) {
      type_cycle = state.cycle();
      TypeDecl new_type_value = type_compute();
      if ((new_type_value == null && type_value != null) || (new_type_value != null && !new_type_value.equals(type_value))) {
        state.setChangeInCycle();
      }
      type_value = new_type_value;
    } else {
    }
    return type_value;
  }
  /** @apilevel internal */
  private TypeDecl type_compute() {
      // 15.13.1
      if (!assignmentContext() && !castContext() && !invocationContext()) {
        return unknownType();
      }
      if (targetInterface() == null) {
        return unknownType();
      }
  
      InterfaceDecl iDecl = targetInterface();
      if (!iDecl.isFunctional()) {
        return unknownType();
      }
  
      return iDecl;
    }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:371
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:371")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        if (!assignmentContext() && !castContext() && !invocationContext()) {
          problems.add(error("Constructor references must target a functional interface"));
          return problems;
        }
    
        // This means there was an error in the overload resolution, will be reported elsewhere
        if (invocationContext() && targetType() == unknownType()) {
          return problems;
        }
    
        if (!targetType().isFunctionalInterface()) {
          problems.add(error("Constructor references must target a functional interface"));
          return problems;
        }
        InterfaceDecl iDecl = targetInterface();
    
        if (!iDecl.isFunctional()) {
          problems.add(errorf("Interface %s is not functional and can therefore not be targeted by a constructor reference",
              iDecl.typeName()));
          return problems;
        }
    
        FunctionDescriptor f = iDecl.functionDescriptor();
    
        if (this instanceof ClassReference) {
          ClassReference ref = (ClassReference) this;
          ConstructorDecl decl = ref.targetConstructor(f);
          if (unknownConstructor() == decl) {
            problems.add(errorf("No constructor for the type %s that is compatible with the method %s in the interface %s was found",
                getTypeAccess().type().typeName(), f.method.fullSignature(), iDecl.typeName()));
          }
          if (!f.method.type().isVoid()) {
            // 15.13.1
            TypeDecl returnType = ref.syntheticInstanceExpr(f).type();
            if (!returnType.assignConversionTo(f.method.type(), null)) {
              problems.add(errorf("Return type of method %s in interface %s is not compatible with"
                  + " referenced constructor which has return type: %s",
                  f.method.fullSignature(), iDecl.typeName(), returnType.typeName()));
            }
          }
          for (int i = 0; i < decl.getNumException(); i++) {
            TypeDecl exception = decl.getException(i).type();
            if (exception.isUncheckedException()) {
              continue;
            }
    
            boolean legalException = false;
            for (TypeDecl descriptorThrows : iDecl.functionDescriptor().throwsList) {
              if (exception.strictSubtype(descriptorThrows)) {
                legalException = true;
                break;
              }
            }
            if (!legalException) {
              // 15.13.1
              problems.add(errorf("Referenced constructor %s throws unhandled exception type %s",
                  decl.name(), exception.typeName()));
            }
          }
          problems.addAll(ref.syntheticInstanceExpr(f).typeProblems());
        } else {
          ArrayReference ref = (ArrayReference) this;
          if (f.method.getNumParameter() != 1) {
            problems.add(errorf("Array reference not compatible with method %s in interface %s,"
                + " should have a single parameter of type int",
                f.method.fullSignature(), iDecl.typeName()));
            return problems;
          }
          if (!f.method.getParameter(0).type().assignConversionTo(iDecl.typeInt(), null)) {
            problems.add(errorf("Array reference not compatible with method %s in interface %s,"
                + " should have a single parameter of type int",
                f.method.fullSignature(), iDecl.typeName()));
            return problems;
          }
          if (!f.method.type().isVoid()) {
            if (!getTypeAccess().type().assignConversionTo(f.method.type(), null)) {
              problems.add(errorf("Return type %s of method %s in interface %s is not compatible with"
                  + " the array reference type %s",
                  f.method.type().typeName(), f.method.fullSignature(), iDecl.typeName(),
                  getTypeAccess().type().typeName()));
            }
          }
        }
        return problems;
      }
  }
  /** @apilevel internal */
  private void toClass_reset() {
    toClass_computed = false;
    
    toClass_value = null;
  }
  /** @apilevel internal */
  protected boolean toClass_computed = false;

  /** @apilevel internal */
  protected ClassInstanceExpr toClass_value;

  /**
   * @attribute syn
   * @aspect ConstructorReferenceToClass
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/ConstructorReferenceToClass.jrag:55
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="ConstructorReferenceToClass", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/ConstructorReferenceToClass.jrag:55")
  public ClassInstanceExpr toClass() {
    ASTNode$State state = state();
    if (toClass_computed) {
      return toClass_value;
    }
    state().enterLazyAttribute();
    toClass_value = toClass_compute();
    toClass_value.setParent(this);
    toClass_computed = true;
    state().leaveLazyAttribute();
    return toClass_value;
  }
  /** @apilevel internal */
  private ClassInstanceExpr toClass_compute() {
      List<Access> implementsList = new List<Access>();
      InterfaceDecl iDecl = targetInterface();
  
      // First compute the interface implemented by the anonymous class
      Access implementsInterface = iDecl.createQualifiedAccess();
      implementsList.add(implementsInterface);
  
      // Next compute the BodyDecls for the anonymous class
      List<BodyDecl> bodyDecls = new List<BodyDecl>();
  
      // Which means we must build the method overriding the abstract methods
  
      Modifiers methodModifiers = new Modifiers(new List<Modifier>().add(new Modifier("public")));
      Access returnType = new SyntheticTypeAccess(iDecl.functionDescriptor().method.type());
      List<ParameterDeclaration> methodParams = toParameterList();
      List<Access> methodThrows = new List<Access>();
      for (TypeDecl throwsType : iDecl.functionDescriptor().throwsList) {
        methodThrows.add(new SyntheticTypeAccess(throwsType));
      }
      Opt<Block> methodBlock = new Opt<Block>(toBlock());
      MethodDecl method = new MethodDecl(methodModifiers, returnType,
          iDecl.functionDescriptor().method.name(),
          methodParams, methodThrows, methodBlock);
  
      bodyDecls.add(method);
  
      // Now the anonymous class can be built. We use the type
      // LambdaAnonymousDecl instead of a normal AnonymousDecl in order for this
      // and super keywords to get the type of the outer scope.
      LambdaAnonymousDecl anonymousDecl = new LambdaAnonymousDecl(new Modifiers(),
          "ConstructorReference", implementsList, bodyDecls);
  
      return new ClassInstanceExpr(
          (Access) implementsInterface.treeCopyNoTransform(),
          new List<Expr>(), new Opt<TypeDecl>(anonymousDecl));
    }
  /** @apilevel internal */
  private void toParameterList_reset() {
    toParameterList_computed = null;
    toParameterList_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle toParameterList_computed = null;

  /** @apilevel internal */
  protected List<ParameterDeclaration> toParameterList_value;

  /**
   * @attribute syn
   * @aspect ConstructorReferenceToClass
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/ConstructorReferenceToClass.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstructorReferenceToClass", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/ConstructorReferenceToClass.jrag:95")
  public List<ParameterDeclaration> toParameterList() {
    ASTNode$State state = state();
    if (toParameterList_computed == ASTNode$State.NON_CYCLE || toParameterList_computed == state().cycle()) {
      return toParameterList_value;
    }
    toParameterList_value = toParameterList_compute();
    if (state().inCircle()) {
      toParameterList_computed = state().cycle();
    
    } else {
      toParameterList_computed = ASTNode$State.NON_CYCLE;
    
    }
    return toParameterList_value;
  }
  /** @apilevel internal */
  private List<ParameterDeclaration> toParameterList_compute() {
      List<ParameterDeclaration> list = new List<ParameterDeclaration>();
      for (int i = 0; i < targetInterface().functionDescriptor().method.getNumParameter(); i++) {
        TypeDecl paramType = targetInterface().functionDescriptor().method.getParameter(i).type();
        String paramName = targetInterface().functionDescriptor().method.getParameter(i).name();
        list.add(new ParameterDeclaration(new SyntheticTypeAccess(paramType), paramName));
      }
      return list;
    }
  /**
   * @attribute inh
   * @aspect ConstructorReference
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/ConstructorReference.jrag:29
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ConstructorReference", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/ConstructorReference.jrag:29")
  public ConstructorDecl unknownConstructor() {
    ConstructorDecl unknownConstructor_value = getParent().Define_unknownConstructor(this, null);
    return unknownConstructor_value;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/ConstructorReference.jrag:66
      return NameType.TYPE_NAME;
    }
    else {
      return getParent().Define_nameType(this, _callerNode);
    }
  }
  protected boolean canDefine_nameType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:195
   * @apilevel internal
   */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:364
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
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:365
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
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:366
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
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:367
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
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:368
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
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:368
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
  protected void collect_contributors_TypeDecl_nestedTypes(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/ConstructorReferenceToClass.jrag:29
    {
      TypeDecl target = (TypeDecl) (hostType());
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_TypeDecl_nestedTypes(_root, _map);
  }
  protected void collect_contributors_TypeDecl_accessors(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {


  
toClass().collect_contributors_TypeDecl_accessors(_root, _map);
    super.collect_contributors_TypeDecl_accessors(_root, _map);
  }
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
  }
  protected void contributeTo_TypeDecl_nestedTypes(LinkedList<TypeDecl> collection) {
    super.contributeTo_TypeDecl_nestedTypes(collection);
    collection.add(toClass().getTypeDecl());
  }
}
