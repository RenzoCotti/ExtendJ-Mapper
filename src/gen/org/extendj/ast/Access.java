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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/grammar/Java.ast:13
 * @production Access : {@link Expr};

 */
public abstract class Access extends Expr implements Cloneable {
  /**
   * Used by the parser to build a method access from a parsed, potentially qualified, name.
   * @aspect QualifiedNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:442
   */
  public Access buildMethodAccess(List<Expr> arguments) {
    throw new Error("Can not build method access from access of type "
        + getClass().getSimpleName());
  }
  /**
   * @aspect QualifiedNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:465
   */
  public Access addArrayDims(List list) {
    Access a = this;
    for (int i = 0; i < list.getNumChildNoTransform(); i++) {
      Dims dims = (Dims) list.getChildNoTransform(i);
      Opt<Expr> opt = dims.getExprOpt();
      if (opt.getNumChildNoTransform() == 1) {
        a = new ArrayTypeWithSizeAccess(a, (Expr) opt.getChildNoTransform(0));
      } else {
        a = new ArrayTypeAccess(a);
      }
      a.setStart(dims.start());
      a.setEnd(dims.end());
    }
    return a;
  }
  /**
   * Checks that two type accesses are the same, while taking type variable
   * substitution into account.
   * @aspect FunctionalInterface
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/FunctionalInterface.jrag:182
   */
  public boolean sameType(Access a) {
    if (this instanceof ArrayTypeAccess && a instanceof ArrayTypeAccess) {
      ArrayTypeAccess at1 = (ArrayTypeAccess) this;
      ArrayTypeAccess at2 = (ArrayTypeAccess) a;
      return at1.sameType(at2);
    } else if (this instanceof AbstractWildcard && a instanceof AbstractWildcard) {
      AbstractWildcard w1 = (AbstractWildcard) this;
      AbstractWildcard w2 = (AbstractWildcard) a;
      return w1.sameType(w2);
    } else if (this instanceof TypeAccess && a instanceof TypeAccess) {
      TypeAccess t1 = (TypeAccess) this;
      TypeAccess t2 = (TypeAccess) a;
      return t1.sameType(t2);
    } else if (this instanceof ParTypeAccess && a instanceof ParTypeAccess) {
      ParTypeAccess pta1 = (ParTypeAccess) this;
      ParTypeAccess pta2 = (ParTypeAccess) a;
      return pta1.sameType(pta2);
    } else {
      return false;
    }
  }
  /**
   * Generate bytecode to load a field, local variable, or parameter.
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:629
   */
  protected void refined_CreateBCode_Access_emitLoadVariable(CodeGeneration gen, Variable v) {
    if (v instanceof VariableDeclarator) {
      VariableDeclarator var = (VariableDeclarator) v;
      if (var.hostType() == hostType()) {
        var.type().emitLoadLocal(this, gen, var.localNum());
      } else {
        emitLoadLocalInNestedClass(gen, v);
      }
    } else if (v.isField()) {
      createLoadQualifier(gen, v);
      if (v.isConstant() && (v.type().isPrimitive() || v.type().isString())) {
        if (!v.isStatic()) {
          fieldQualifierType().emitPop(this, gen);
        }
        v.constant().createBCode(this, gen);
      } else if (requiresAccessor()) {
        fieldQualifierType().fieldAccessor(v).emitInvokeMethod(this, gen, fieldQualifierType());
      } else {
        emitLoadField(this, gen, v, fieldQualifierType());
      }
    } else if (v instanceof ParameterDeclaration) {
      ParameterDeclaration decl = (ParameterDeclaration) v;
      if (decl.hostType() == hostType()) {
        decl.type().emitLoadLocal(this, gen, decl.localNum());
      } else {
        emitLoadLocalInNestedClass(gen, v);
      }
    }
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:671
   */
  protected void emitLoadLocalInNestedClass(CodeGeneration gen, Variable v) {
    if (inExplicitConstructorInvocation() && enclosingBodyDecl() instanceof ConstructorDecl) {
      ConstructorDecl c = (ConstructorDecl) enclosingBodyDecl();
      v.type().emitLoadLocal(this, gen, c.localIndexOfEnclosingVariable(v));
    } else {
      String classname = hostType().constantPoolName();
      String      desc = v.type().typeDescriptor();
      String      name = "val$" + v.name();
      int index = gen.constantPool().addFieldref(classname, name, desc);
      gen.emit(this, Bytecode.ALOAD_0);
      gen.emit(this, Bytecode.GETFIELD, v.type().variableSize() - 1).add2(index);
    }
  }
  /**
   * Generate bytecode to push on the stack the qualifier to access the variable v.
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:688
   */
  protected void createLoadQualifier(CodeGeneration gen, Variable v) {
    if (v.isField()) {
      if (hasPrevExpr()) {
        // Load explicit qualifier.
        prevExpr().createBCode(gen);
        // Pop qualifier stack element for class variables.
        // This qualifier must be computed to ensure side effects are evaluated.
        if (!prevExpr().isTypeAccess() && v.isClassVariable()) {
          prevExpr().type().emitPop(this, gen);
        }
      } else if (v.isInstanceVariable()) {
        emitThis(gen, fieldQualifierType());
      }
    }
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:782
   */
  public void emitThis(CodeGeneration gen, TypeDecl targetDecl) {
    if (targetDecl == hostType()) {
      gen.emit(this, Bytecode.ALOAD_0);
    } else {
      TypeDecl enclosing = hostType();
      if (inExplicitConstructorInvocation()) {
        gen.emit(this, Bytecode.ALOAD_1);
        enclosing = enclosing.enclosing();
      } else {
        gen.emit(this, Bytecode.ALOAD_0);
      }
      while (enclosing != targetDecl) {
        String classname = enclosing.constantPoolName();
        enclosing = enclosing.enclosingType();
        String desc = enclosing.typeDescriptor();
        int index = gen.constantPool().addFieldref(classname, "this$0", desc);
        gen.emit(this, Bytecode.GETFIELD, 0).add2(index);
      }
    }
  }
  /** Generate a checked cast if needed. 
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:134
   */
  protected void emitCheckCast(CodeGeneration gen, Variable v) {
  }
  /**
   * @declaredat ASTNode:1
   */
  public Access() {
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
    type_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:28
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:32
   */
  public Access clone() throws CloneNotSupportedException {
    Access node = (Access) super.clone();
    return node;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:43
   */
  @Deprecated
  public abstract Access fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:51
   */
  public abstract Access treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:59
   */
  public abstract Access treeCopy();
  /**
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:110
   */
   
  protected void refined_GenericsCodegen_Access_emitLoadVariable(CodeGeneration gen, Variable v) {
    if (v.isField()) {
      if (v instanceof FieldDeclarator) {
        v = ((FieldDeclarator) v).erasedField();
      }
      createLoadQualifier(gen, v);
      if (v.isConstant() && (v.type().isPrimitive() || v.type().isString())) {
        if (!v.isStatic()) {
          fieldQualifierType().emitPop(this, gen);
        }
        v.constant().createBCode(this, gen);
      } else if (requiresAccessor()) {
        fieldQualifierType().fieldAccessor(v).emitInvokeMethod(this, gen, fieldQualifierType());
      } else {
        emitLoadField(this, gen, v, fieldQualifierType());
      }
      emitCheckCast(gen, v);
    } else {
      refined_CreateBCode_Access_emitLoadVariable(gen, v);
    }
  }
  /**
   * @aspect MultiCatch
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:84
   */
   
  protected void emitLoadVariable(CodeGeneration gen, Variable v) {
    if (v instanceof CatchParameterDeclaration) {
      CatchParameterDeclaration decl = (CatchParameterDeclaration) v;
      if (decl.hostType() == hostType()) {
        decl.type().emitLoadLocal(this, gen, decl.localNum());
      } else {
        emitLoadLocalInNestedClass(gen, v);
      }
    } else {
      refined_GenericsCodegen_Access_emitLoadVariable(gen, v);
    }
  }
  /**
   * @attribute syn
   * @aspect LookupMethod
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:40
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:40")
  public Expr unqualifiedScope() {
    Expr unqualifiedScope_value = isQualified() ? nestedScope() : this;
    return unqualifiedScope_value;
  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:156
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="QualifiedNames", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:156")
  public boolean isQualified() {
    boolean isQualified_value = hasPrevExpr();
    return isQualified_value;
  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:159
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="QualifiedNames", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:159")
  public Expr qualifier() {
    Expr qualifier_value = prevExpr();
    return qualifier_value;
  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:166
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="QualifiedNames", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:166")
  public Access lastAccess() {
    Access lastAccess_value = this;
    return lastAccess_value;
  }
  /**
   * @attribute syn
   * @aspect QualifiedNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:175
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="QualifiedNames", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:175")
  public boolean hasPrevExpr() {
    boolean hasPrevExpr_value = isRightChildOfDot();
    return hasPrevExpr_value;
  }
  /**
   * Defines the expected kind of name for the left hand side in a qualified
   * expression.
   * @attribute syn
   * @aspect SyntacticClassification
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/SyntacticClassification.jrag:60
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SyntacticClassification", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/SyntacticClassification.jrag:60")
  public NameType predNameType() {
    {
        switch (nameType()) {
          case PACKAGE_NAME:
            return NameType.PACKAGE_NAME;
          case TYPE_NAME:
          case PACKAGE_OR_TYPE_NAME:
            return NameType.PACKAGE_OR_TYPE_NAME;
          case AMBIGUOUS_NAME:
          case EXPRESSION_NAME:
            return NameType.AMBIGUOUS_NAME;
          case NOT_CLASSIFIED:
          default:
            return NameType.NOT_CLASSIFIED;
        }
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
    type_value = unknownType();
    if (state().inCircle()) {
      type_computed = state().cycle();
    
    } else {
      type_computed = ASTNode$State.NON_CYCLE;
    
    }
    return type_value;
  }
  /**
   * Creates a copy of this access where parameterized types have been erased.
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1462
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1462")
  public Access erasedCopy() {
    Access erasedCopy_value = treeCopyNoTransform();
    return erasedCopy_value;
  }
  /**
   * WARNING: this attribute is not the same as TypeDecl.isWildcard,
   * which returns true for any wildcard type (even bounded wildcard types).
   * @return {@code true} if this is an unbounded wildcard access
   * @attribute syn
   * @aspect ReifiableTypes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/ReifiableTypes.jrag:106
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ReifiableTypes", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/ReifiableTypes.jrag:106")
  public boolean isWildcard() {
    boolean isWildcard_value = false;
    return isWildcard_value;
  }
  /**
   * @attribute syn
   * @aspect Diamond
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/Diamond.jrag:87
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Diamond", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/Diamond.jrag:87")
  public boolean isDiamond() {
    boolean isDiamond_value = false;
    return isDiamond_value;
  }
  /**
   * Builds a copy of this Access node where all occurrences
   * of type variables in the original type parameter list have been replaced
   * by the substitution type parameters.
   * 
   * @return the substituted Access node
   * @attribute syn
   * @aspect Diamond
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/Diamond.jrag:369
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Diamond", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/Diamond.jrag:369")
  public Access substituted(Collection<TypeVariable> original, List<TypeVariable> substitution) {
    Access substituted_Collection_TypeVariable__List_TypeVariable__value = (Access) treeCopyNoTransform();
    return substituted_Collection_TypeVariable__List_TypeVariable__value;
  }
  /**
   * @attribute syn
   * @aspect LambdaParametersInference
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:583
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaParametersInference", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:583")
  public boolean mentionsTypeVariable(TypeVariable var) {
    boolean mentionsTypeVariable_TypeVariable_value = false;
    return mentionsTypeVariable_TypeVariable_value;
  }
  /**
   * @attribute syn
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:51
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CodeGeneration", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:51")
  public int sourceLineNumber() {
    int sourceLineNumber_value = findFirstSourceLineNumber();
    return sourceLineNumber_value;
  }
  /**
   * @return {@code true} if this access is a method call of a non-static method.
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:419
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenerateClassfile", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:419")
  public boolean isInstanceMethodAccess() {
    boolean isInstanceMethodAccess_value = false;
    return isInstanceMethodAccess_value;
  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:57
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="InnerClasses", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:57")
  public TypeDecl fieldQualifierType() {
    {
        throw new Error("Can not evaluate fieldQualifierType() on node of type "
            + getClass().getSimpleName());
      }
  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:135
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="InnerClasses", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:135")
  public TypeDecl superConstructorQualifier(TypeDecl targetEnclosingType) {
    {
        TypeDecl enclosing = hostType();
        while (!enclosing.instanceOf(targetEnclosingType)) {
          enclosing = enclosing.enclosingType();
        }
        return enclosing;
      }
  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:399
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="InnerClasses", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:399")
  public boolean requiresAccessor() {
    {
        throw new Error("Can not evaluate requiresAccessor() on node of type "
            + getClass().getSimpleName());
      }
  }
  /**
   * @attribute inh
   * @aspect LookupMethod
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:42
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:42")
  public Expr nestedScope() {
    Expr nestedScope_value = getParent().Define_nestedScope(this, null);
    return nestedScope_value;
  }
  /**
   * @attribute inh
   * @aspect TypeScopePropagation
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:295
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:295")
  public TypeDecl unknownType() {
    TypeDecl unknownType_value = getParent().Define_unknownType(this, null);
    return unknownType_value;
  }
  /**
   * @attribute inh
   * @aspect VariableScopePropagation
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupVariable.jrag:355
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="VariableScopePropagation", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupVariable.jrag:355")
  public Variable unknownField() {
    Variable unknownField_value = getParent().Define_unknownField(this, null);
    return unknownField_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:403
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:403")
  public boolean withinSuppressWarnings(String annot) {
    boolean withinSuppressWarnings_String_value = getParent().Define_withinSuppressWarnings(this, null, annot);
    return withinSuppressWarnings_String_value;
  }
  /**
   * @attribute inh
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:534
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:534")
  public boolean withinDeprecatedAnnotation() {
    boolean withinDeprecatedAnnotation_value = getParent().Define_withinDeprecatedAnnotation(this, null);
    return withinDeprecatedAnnotation_value;
  }
  /**
   * @attribute inh
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:669
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:669")
  public boolean inExplicitConstructorInvocation() {
    boolean inExplicitConstructorInvocation_value = getParent().Define_inExplicitConstructorInvocation(this, null);
    return inExplicitConstructorInvocation_value;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:78
   * @apilevel internal
   */
  public boolean Define_isLeftChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  protected boolean canDefine_isLeftChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:93
   * @apilevel internal
   */
  public boolean Define_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  protected boolean canDefine_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:110
   * @apilevel internal
   */
  public Expr Define_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return prevExprError();
  }
  protected boolean canDefine_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:134
   * @apilevel internal
   */
  public Access Define_nextAccess(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return nextAccessError();
  }
  protected boolean canDefine_nextAccess(ASTNode _callerNode, ASTNode _childNode) {
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
