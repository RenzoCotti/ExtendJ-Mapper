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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/grammar/Java.ast:160
 * @astdecl BodyDecl : ASTNode;
 * @production BodyDecl : {@link ASTNode};

 */
public abstract class BodyDecl extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect DocumentationComments
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DocumentationComments.jadd:36
   */
  public String docComment = "";
  /**
   * Copies the signature of this body decl. The type and name and parameters
   * are copied where applicable, but the body or initializer is not copied.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Generics.jrag:1416
   */
  public BodyDecl signatureCopy() {
    throw new Error("Can not susbtitute type parameters in body decl of type "
        + getClass().getSimpleName());
  }
  /**
   * Creates an erased copy of the signature of this body declaration.
   * 
   * <p>Occurrences of type variables are replaced by their type bound.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Generics.jrag:1518
   */
  public BodyDecl erasedCopy() {
    throw new Error("Can not erase types in body decl of type " + getClass().getSimpleName());
  }
  /**
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:286
   */
  public void generateMethod(DataOutputStream out, ConstantPool cp) throws IOException {
  }
  /**
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:310
   */
  public void touchMethod(ConstantPool cp) {
  }
  /**
   * @declaredat ASTNode:1
   */
  public BodyDecl() {
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
  /** @apilevel low-level 
   * @declaredat ASTNode:13
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:19
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:23
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    assignedAfter_Variable_reset();
    assignedBefore_Variable_reset();
    unassignedAfter_Variable_reset();
    attributes_reset();
    typeThrowable_reset();
    lookupVariable_String_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:33
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:37
   */
  public BodyDecl clone() throws CloneNotSupportedException {
    BodyDecl node = (BodyDecl) super.clone();
    return node;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:48
   */
  @Deprecated
  public abstract BodyDecl fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:56
   */
  public abstract BodyDecl treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:64
   */
  public abstract BodyDecl treeCopy();
  /**
   * @attribute syn
   * @aspect DocumentationComments
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DocumentationComments.jadd:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DocumentationComments", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DocumentationComments.jadd:42")
  public String docComment() {
    String docComment_value = docComment;
    return docComment_value;
  }
  /**
   * @attribute syn
   * @aspect DocumentationComments
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DocumentationComments.jadd:46
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DocumentationComments", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DocumentationComments.jadd:46")
  public boolean hasDocComment() {
    boolean hasDocComment_value = !docComment.isEmpty();
    return hasDocComment_value;
  }
  /** @return {@code true} if the field declaration is inside this node. 
   * @attribute syn
   * @aspect DeclareBeforeUse
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DeclareBeforeUse.jrag:46
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DeclareBeforeUse", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DeclareBeforeUse.jrag:46")
  public boolean declaredIn(Variable decl) {
    boolean declaredIn_Variable_value = declaredBefore(decl);
    return declaredIn_Variable_value;
  }
  /** @apilevel internal */
  private void assignedAfter_Variable_reset() {
    assignedAfter_Variable_values = null;
  }
  protected java.util.Map assignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:272")
  public boolean assignedAfter(Variable v) {
    Object _parameters = v;
    if (assignedAfter_Variable_values == null) assignedAfter_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (assignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = assignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      assignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_assignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_assignedAfter_Variable_value = true;
        if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_assignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedAfter_Variable_values.put(_parameters, new_assignedAfter_Variable_value);

      state.leaveCircle();
      return new_assignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedAfter_Variable_value = true;
      if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_assignedAfter_Variable_value;
      }
      return new_assignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private void assignedBefore_Variable_reset() {
    assignedBefore_Variable_values = null;
  }
  protected java.util.Map assignedBefore_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:278")
  public boolean assignedBefore(Variable v) {
    Object _parameters = v;
    if (assignedBefore_Variable_values == null) assignedBefore_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (assignedBefore_Variable_values.containsKey(_parameters)) {
      Object _cache = assignedBefore_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      assignedBefore_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_assignedBefore_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_assignedBefore_Variable_value = assignedBefore(v, this);
        if (((Boolean)_value.value) != new_assignedBefore_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_assignedBefore_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedBefore_Variable_values.put(_parameters, new_assignedBefore_Variable_value);

      state.leaveCircle();
      return new_assignedBefore_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedBefore_Variable_value = assignedBefore(v, this);
      if (((Boolean)_value.value) != new_assignedBefore_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_assignedBefore_Variable_value;
      }
      return new_assignedBefore_Variable_value;
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
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:911")
  public boolean unassignedAfter(Variable v) {
    Object _parameters = v;
    if (unassignedAfter_Variable_values == null) unassignedAfter_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (unassignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      unassignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfter_Variable_value = true;
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = true;
      if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfter_Variable_value;
      }
      return new_unassignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /**
   * @attribute syn
   * @aspect DefiniteUnassignment
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:917
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:917")
  public boolean unassignedBefore(Variable v) {
    boolean unassignedBefore_Variable_value = unassignedBefore(v, this);
    return unassignedBefore_Variable_value;
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupType.jrag:657
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupType.jrag:657")
  public boolean declaresType(String name) {
    boolean declaresType_String_value = false;
    return declaresType_String_value;
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupType.jrag:659
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupType.jrag:659")
  public TypeDecl type(String name) {
    TypeDecl type_String_value = null;
    return type_String_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/Modifiers.jrag:247
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/Modifiers.jrag:247")
  public boolean isSynthetic() {
    boolean isSynthetic_value = false;
    return isSynthetic_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:291
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:291")
  public boolean isVoid() {
    boolean isVoid_value = false;
    return isVoid_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:425
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:425")
  public boolean hasAnnotationSuppressWarnings(String annot) {
    boolean hasAnnotationSuppressWarnings_String_value = false;
    return hasAnnotationSuppressWarnings_String_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:486
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:486")
  public boolean isDeprecated() {
    boolean isDeprecated_value = false;
    return isDeprecated_value;
  }
  /**
   * @attribute syn
   * @aspect Enums
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Enums.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Enums", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Enums.jrag:50")
  public boolean isEnumConstant() {
    boolean isEnumConstant_value = false;
    return isEnumConstant_value;
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Generics.jrag:1714
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Generics.jrag:1714")
  public boolean isSubstitutable() {
    boolean isSubstitutable_value = false;
    return isSubstitutable_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/GenericsParTypeDecl.jrag:98
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/GenericsParTypeDecl.jrag:98")
  public boolean visibleTypeParameters() {
    boolean visibleTypeParameters_value = true;
    return visibleTypeParameters_value;
  }
  /**
   * @return {@code true} if this is a generic method or constructor, or a
   * substitued generic method or constructor.
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/MethodSignature.jrag:413
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/MethodSignature.jrag:413")
  public boolean isGeneric() {
    boolean isGeneric_value = false;
    return isGeneric_value;
  }
  /**
   * Note: isGeneric must be called first to check if this declaration is generic.
   * Otherwise this attribute will throw an error!
   * @return type parameters for this declaration.
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/MethodSignature.jrag:448
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/MethodSignature.jrag:448")
  public List<TypeVariable> typeParameters() {
    {
        throw new Error("can not evaulate type parameters for non-generic declaration");
      }
  }
  /**
   * @return true if the modifier list includes the SafeVarargs annotation
   * @attribute syn
   * @aspect SafeVarargs
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/SafeVarargs.jrag:41
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SafeVarargs", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/SafeVarargs.jrag:41")
  public boolean hasAnnotationSafeVarargs() {
    boolean hasAnnotationSafeVarargs_value = false;
    return hasAnnotationSafeVarargs_value;
  }
  /**
   * @attribute syn
   * @aspect SafeVarargs
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/SafeVarargs.jrag:93
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SafeVarargs", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/SafeVarargs.jrag:93")
  public Collection<Problem> safeVarargsProblems() {
    {
        if (hasAnnotationSafeVarargs()) {
          return Collections.singleton(error(
                "the @SafeVarargs annotation is only allowed on constructor or method declarations."));
        } else {
          return Collections.emptySet();
        }
      }
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java8/frontend/EffectivelyFinal.jrag:40
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java8/frontend/EffectivelyFinal.jrag:40")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = false;
    return modifiedInScope_Variable_value;
  }
  /**
   * @return {@code true} if this method is a substituted method declaration with
   * the source declaration being identical to the given method.
   * @attribute syn
   * @aspect LambdaParametersInference
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java8/frontend/TypeCheck.jrag:561
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaParametersInference", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java8/frontend/TypeCheck.jrag:561")
  public boolean isSubstitutionOf(MethodDecl method) {
    boolean isSubstitutionOf_MethodDecl_value = false;
    return isSubstitutionOf_MethodDecl_value;
  }
  /** @apilevel internal */
  private void attributes_reset() {
    attributes_computed = null;
    attributes_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle attributes_computed = null;

  /** @apilevel internal */
  protected Collection<Attribute> attributes_value;

  /**
   * @attribute syn
   * @aspect Attributes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/Attributes.jrag:277
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Attributes", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/Attributes.jrag:277")
  public Collection<Attribute> attributes() {
    ASTState state = state();
    if (attributes_computed == ASTState.NON_CYCLE || attributes_computed == state().cycle()) {
      return attributes_value;
    }
    attributes_value = Collections.emptyList();
    if (state().inCircle()) {
      attributes_computed = state().cycle();
    
    } else {
      attributes_computed = ASTState.NON_CYCLE;
    
    }
    return attributes_value;
  }
  /**
   * @return all fields declared in this body decl
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:370
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenerateClassfile", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:370")
  public Collection<FieldDeclarator> fieldDeclarations() {
    Collection<FieldDeclarator> fieldDeclarations_value = Collections.emptyList();
    return fieldDeclarations_value;
  }
  /**
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:444
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenerateClassfile", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:444")
  public boolean isField() {
    boolean isField_value = false;
    return isField_value;
  }
  /**
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:448
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenerateClassfile", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/GenerateClassfile.jrag:448")
  public boolean isMethodOrConstructor() {
    boolean isMethodOrConstructor_value = false;
    return isMethodOrConstructor_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/backend/GenericsCodegen.jrag:436
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsCodegen", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/backend/GenericsCodegen.jrag:436")
  public boolean needsSignatureAttribute() {
    boolean needsSignatureAttribute_value = false;
    return needsSignatureAttribute_value;
  }
  /**
   * @attribute inh
   * @aspect DefiniteAssignment
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:280
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:280")
  public boolean assignedBefore(Variable v, BodyDecl b) {
    boolean assignedBefore_Variable_BodyDecl_value = getParent().Define_assignedBefore(this, null, v, b);
    return assignedBefore_Variable_BodyDecl_value;
  }
  /**
   * @attribute inh
   * @aspect DefiniteUnassignment
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:923
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:923")
  public boolean unassignedBefore(Variable v, BodyDecl b) {
    boolean unassignedBefore_Variable_BodyDecl_value = getParent().Define_unassignedBefore(this, null, v, b);
    return unassignedBefore_Variable_BodyDecl_value;
  }
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ExceptionHandling.jrag:60
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ExceptionHandling.jrag:60")
  public TypeDecl typeThrowable() {
    ASTState state = state();
    if (typeThrowable_computed == ASTState.NON_CYCLE || typeThrowable_computed == state().cycle()) {
      return typeThrowable_value;
    }
    typeThrowable_value = getParent().Define_typeThrowable(this, null);
    if (state().inCircle()) {
      typeThrowable_computed = state().cycle();
    
    } else {
      typeThrowable_computed = ASTState.NON_CYCLE;
    
    }
    return typeThrowable_value;
  }
  /** @apilevel internal */
  private void typeThrowable_reset() {
    typeThrowable_computed = null;
    typeThrowable_value = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle typeThrowable_computed = null;

  /** @apilevel internal */
  protected TypeDecl typeThrowable_value;

  /**
   * Find all visible methods with the given name in the local scope.
   * @attribute inh
   * @aspect LookupMethod
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupMethod.jrag:121
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupMethod.jrag:121")
  public Collection<MethodDecl> lookupMethod(String name) {
    Collection<MethodDecl> lookupMethod_String_value = getParent().Define_lookupMethod(this, null, name);
    return lookupMethod_String_value;
  }
  /**
   * @attribute inh
   * @aspect LookupFullyQualifiedTypes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupType.jrag:129
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupFullyQualifiedTypes", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupType.jrag:129")
  public TypeDecl lookupType(String packageName, String typeName) {
    TypeDecl lookupType_String_String_value = getParent().Define_lookupType(this, null, packageName, typeName);
    return lookupType_String_String_value;
  }
  /**
   * @attribute inh
   * @aspect TypeScopePropagation
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupType.jrag:398
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupType.jrag:398")
  public SimpleSet<TypeDecl> lookupType(String name) {
    SimpleSet<TypeDecl> lookupType_String_value = getParent().Define_lookupType(this, null, name);
    return lookupType_String_value;
  }
  /**
   * @attribute inh
   * @aspect VariableScope
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupVariable.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/LookupVariable.jrag:39")
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
   * @attribute inh
   * @aspect NestedTypes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:635
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:635")
  public String hostPackage() {
    String hostPackage_value = getParent().Define_hostPackage(this, null);
    return hostPackage_value;
  }
  /**
   * @attribute inh
   * @aspect NestedTypes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:652
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NestedTypes", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/TypeAnalysis.jrag:652")
  public TypeDecl hostType() {
    TypeDecl hostType_value = getParent().Define_hostType(this, null);
    return hostType_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:402
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:402")
  public boolean withinSuppressWarnings(String annot) {
    boolean withinSuppressWarnings_String_value = getParent().Define_withinSuppressWarnings(this, null, annot);
    return withinSuppressWarnings_String_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:533
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:533")
  public boolean withinDeprecatedAnnotation() {
    boolean withinDeprecatedAnnotation_value = getParent().Define_withinDeprecatedAnnotation(this, null);
    return withinDeprecatedAnnotation_value;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/BranchTarget.jrag:230
   * @apilevel internal
   */
  public Stmt Define_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
        throw new Error("Found no branch targets for " + branch.getClass().getName());
      }
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/BranchTarget.jrag:230
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute branchTarget
   */
  protected boolean canDefine_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/BranchTarget.jrag:273
   * @apilevel internal
   */
  public FinallyHost Define_enclosingFinally(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/BranchTarget.jrag:273
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingFinally
   */
  protected boolean canDefine_enclosingFinally(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return assignedBefore(v, this);
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignedBefore
   */
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unassignedBefore(v, this);
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/DefiniteAssignment.jrag:887
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unassignedBefore
   */
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/NameCheck.jrag:680
   * @apilevel internal
   */
  public SimpleSet<TypeDecl> Define_otherLocalClassDecls(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return emptySet();
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/NameCheck.jrag:680
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute otherLocalClassDecls
   */
  protected boolean canDefine_otherLocalClassDecls(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ResolveAmbiguousNames.jrag:86
   * @apilevel internal
   */
  public boolean Define_isLeftChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ResolveAmbiguousNames.jrag:86
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isLeftChildOfDot
   */
  protected boolean canDefine_isLeftChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ResolveAmbiguousNames.jrag:101
   * @apilevel internal
   */
  public boolean Define_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ResolveAmbiguousNames.jrag:101
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isRightChildOfDot
   */
  protected boolean canDefine_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ResolveAmbiguousNames.jrag:118
   * @apilevel internal
   */
  public Expr Define_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return prevExprError();
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ResolveAmbiguousNames.jrag:118
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute prevExpr
   */
  protected boolean canDefine_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ResolveAmbiguousNames.jrag:142
   * @apilevel internal
   */
  public Access Define_nextAccess(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return nextAccessError();
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ResolveAmbiguousNames.jrag:142
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute nextAccess
   */
  protected boolean canDefine_nextAccess(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ResolveAmbiguousNames.jrag:539
   * @apilevel internal
   */
  public boolean Define_canResolve(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/frontend/ResolveAmbiguousNames.jrag:539
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute canResolve
   */
  protected boolean canDefine_canResolve(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java8/frontend/NameCheck.jrag:30
   * @apilevel internal
   */
  public BodyDecl Define_enclosingBodyDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return this;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java8/frontend/NameCheck.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingBodyDecl
   */
  protected boolean canDefine_enclosingBodyDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:95
   * @apilevel internal
   */
  public boolean Define_inComplexAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:95
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inComplexAnnotation
   */
  protected boolean canDefine_inComplexAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/SuppressWarnings.jrag:37
   * @apilevel internal
   */
  public boolean Define_withinSuppressWarnings(ASTNode _callerNode, ASTNode _childNode, String annot) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return hasAnnotationSuppressWarnings(annot)
          || hasAnnotationSuppressWarnings(annot)
          || withinSuppressWarnings(annot);
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/SuppressWarnings.jrag:37
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute withinSuppressWarnings
   */
  protected boolean canDefine_withinSuppressWarnings(ASTNode _callerNode, ASTNode _childNode, String annot) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:533
   * @apilevel internal
   */
  public boolean Define_withinDeprecatedAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return isDeprecated() || isDeprecated() || withinDeprecatedAnnotation();
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Annotations.jrag:533
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute withinDeprecatedAnnotation
   */
  protected boolean canDefine_withinDeprecatedAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Enums.jrag:130
   * @apilevel internal
   */
  public boolean Define_isOriginalEnumConstructor(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java5/frontend/Enums.jrag:130
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isOriginalEnumConstructor
   */
  protected boolean canDefine_isOriginalEnumConstructor(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/Diamond.jrag:106
   * @apilevel internal
   */
  public ClassInstanceExpr Define_getClassInstanceExpr(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/Diamond.jrag:106
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute getClassInstanceExpr
   */
  protected boolean canDefine_getClassInstanceExpr(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/TryWithResources.jrag:178
   * @apilevel internal
   */
  public boolean Define_resourcePreviouslyDeclared(ASTNode _callerNode, ASTNode _childNode, String name) {
    int i = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/TryWithResources.jrag:178
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute resourcePreviouslyDeclared
   */
  protected boolean canDefine_resourcePreviouslyDeclared(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CreateBCode.jrag:1928
   * @apilevel internal
   */
  public boolean Define_leavesMonitor(ASTNode _callerNode, ASTNode _childNode, Stmt branch, SynchronizedStmt monitor) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
        throw new Error("Enclosing monitor not found!");
      }
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/CreateBCode.jrag:1928
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute leavesMonitor
   */
  protected boolean canDefine_leavesMonitor(ASTNode _callerNode, ASTNode _childNode, Stmt branch, SynchronizedStmt monitor) {
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
  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java7/frontend/SafeVarargs.jrag:91
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
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : safeVarargsProblems()) {
      collection.add(value);
    }
  }
}
