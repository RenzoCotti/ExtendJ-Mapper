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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/grammar/Java.ast:94
 * @production MethodDecl : {@link MemberDecl} ::= <span class="component">{@link Modifiers}</span> <span class="component">TypeAccess:{@link Access}</span> <span class="component">&lt;ID:String&gt;</span> <span class="component">Parameter:{@link ParameterDeclaration}*</span> <span class="component">Exception:{@link Access}*</span> <span class="component">[{@link Block}]</span>;

 */
public class MethodDecl extends MemberDecl implements Cloneable, SimpleSet<MethodDecl>, ExceptionHolder {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrint.jadd:471
   */
  public void prettyPrint(PrettyPrinter out) {
    if (!isSynthetic()) {
      if (hasDocComment()) {
        out.print(docComment());
      }
      if (!out.isNewLine()) {
        out.println();
      }
      out.print(getModifiers());
      out.print(getTypeAccess());
      out.print(" ");
      out.print(getID());
      out.print("(");
      out.join(getParameterList(), new PrettyPrinter.Joiner() {
        @Override
        public void printSeparator(PrettyPrinter out) {
          out.print(", ");
        }
      });
      out.print(")");
      if (hasExceptions()) {
        out.print(" throws ");
        out.join(getExceptionList(), new PrettyPrinter.Joiner() {
          @Override
          public void printSeparator(PrettyPrinter out) {
            out.print(", ");
          }
        });
      }
      if (hasBlock()) {
        out.print(" ");
        out.print(getBlock());
      } else {
        out.print(";");
      }
    }
  }
  /**
   * @aspect BoundNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BoundNames.jrag:95
   */
  public Access createBoundAccess(List<Expr> args) {
    return createBoundAccess(args, hostType());
  }
  /**
   * @aspect BoundNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BoundNames.jrag:99
   */
  public Access createBoundAccess(List<Expr> args, TypeDecl hostType) {
    if (isStatic()) {
      return hostType().createQualifiedAccess().qualifiesAccess(
          new BoundMethodAccess(name(), args, this, hostType));
    } else {
      return new BoundMethodAccess(name(), args, this, hostType);
    }
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DataStructures.jrag:455
   */
  @Override
  public int size() {
    return 1;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DataStructures.jrag:460
   */
  @Override
  public boolean isEmpty() {
    return false;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DataStructures.jrag:465
   */
  @Override
  public SimpleSet<MethodDecl> add(MethodDecl o) {
    return new SimpleSetImpl<MethodDecl>(this, o);
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DataStructures.jrag:470
   */
  @Override
  public boolean contains(Object o) {
    return this == o;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DataStructures.jrag:475
   */
  @Override
  public boolean isSingleton() {
    return true;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DataStructures.jrag:480
   */
  @Override
  public boolean isSingleton(MethodDecl o) {
    return contains(o);
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DataStructures.jrag:485
   */
  @Override
  public MethodDecl singletonValue() {
    return this;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DataStructures.jrag:490
   */
  @Override
  public boolean equals(Object o) {
    return this == o;
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DataStructures.jrag:495
   */
  @Override
  public Iterator<MethodDecl> iterator() {
    return new SingleItemIterator(this);
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1286
   */
  public BodyDecl signatureCopy() {
    return new MethodDeclSubstituted(
        getModifiers().treeCopyNoTransform(),
        getTypeAccessNoTransform().treeCopyNoTransform(),
        getID(),
        getParameterList().treeCopyNoTransform(),
        getExceptionList().treeCopyNoTransform(),
        new Opt<Block>(),
        this);
  }
  /**
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1387
   */
  public BodyDecl erasedCopy() {
    return new MethodDeclSubstituted(
        getModifiers().treeCopyNoTransform(),
        getTypeAccess().erasedCopy(),
        getID(),
        erasedParameterList(getParameterList()),
        erasedAccessList(getExceptionList()),
        new Opt<Block>(),
        this);
  }
  /**
   * Checks that the argument exception is a subtype to all exceptions
   * in the methods throws-clause. This takes the position of the type
   * parameters into account.
   * @aspect FunctionDescriptor
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/FunctionDescriptor.jrag:216
   */
  public boolean subtypeThrowsClause(Access exception) {
    boolean foundCompatible = false;
    for (Access throwsException : getExceptionList()) {
      if (exception.type().strictSubtype(throwsException.type())) {
        foundCompatible = true;
        break;
      }
    }
    return foundCompatible;
  }
  /**
   * Checks that the argument exception is a subtype to all exceptions
   * in the methods throws-clause. Performs erasure on all types before
   * comparing them.
   * @aspect FunctionDescriptor
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/FunctionDescriptor.jrag:232
   */
  public boolean subtypeThrowsClauseErased(Access exception) {
    boolean foundCompatible = false;
    for (Access throwsException : getExceptionList()) {
      if (exception.type().erasure().strictSubtype(throwsException.type().erasure())) {
        foundCompatible = true;
        break;
      }
    }
    return foundCompatible;
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:634
   */
  public void emitInvokeMethod(ASTNode<ASTNode> node, CodeGeneration gen, TypeDecl hostType) {
    if (hostType.isInterfaceDecl()) {
      String classname = hostType.constantPoolName();
      String desc = descName();
      String name = name();
      int index = gen.constantPool().addInterfaceMethodref(classname, name, desc);
      int numArg = 1;
      for (int i = 0; i < getNumParameter(); i++) {
        numArg += getParameter(i).type().variableSize();
      }
      int stackChange = type().variableSize() - numArg;
      gen.emit(node, Bytecode.INVOKEINTERFACE, stackChange)
            .add2(index).add(numArg).add(0);
    } else {
      String classname = hostType.constantPoolName();
      String desc = descName();
      String name = name();
      int index = gen.constantPool().addMethodref(classname, name, desc);
      int stackChange = type().variableSize();
      for (int i = 0; i < getNumParameter(); i++) {
          stackChange -= getParameter(i).type().variableSize();
      }
      if (isStatic()) {
        gen.emit(node, Bytecode.INVOKESTATIC, stackChange).add2(index);
      } else {
        stackChange -= 1;
        gen.emit(node, Bytecode.INVOKEVIRTUAL, stackChange).add2(index);
      }
    }
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:665
   */
  public void emitInvokeSpecialMethod(ASTNode<ASTNode> node, CodeGeneration gen, TypeDecl hostType) {
    String classname = hostType.constantPoolName();
    String desc = descName();
    String name = name();
    int index = gen.constantPool().addMethodref(classname, name, desc);
    int stackChange = type().variableSize() - 1;
    for (int i = 0; i < getNumParameter(); i++) {
      stackChange -= getParameter(i).type().variableSize();
    }
    gen.emit(node, Bytecode.INVOKESPECIAL, stackChange).add2(index);
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:145
   */
  private void generateBytecodes(CodeGeneration gen) {
    int label = gen.variableScopeLabel();
    int paramLength = 0;
    if (!isStatic()) {
      paramLength += 1;
      gen.addLocalVariableEntryAtCurrentPC("this", hostType().typeDescriptor(), 0, label);
    }
    for (ParameterDeclaration p : getParameterList()) {
      paramLength += p.type().variableSize();
      if (paramLength > 255) {
        throw new Error("parameter list too large");
      }
      gen.addLocalVariableEntryAtCurrentPC(
        p.name(), p.type().typeDescriptor(), p.localNum(), label
      );
    }
    createBCode(gen);
    if (type() instanceof VoidType) {
      // TODO(joqvist): also check for canCompleteNormally.
      gen.emitReturn(this);
    }
    gen.addVariableScopeLabel(label);
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:210
   */
  public void createBCode(CodeGeneration gen) {
    try {
      if (hasBlock()) {
        gen.maxLocals = Math.max(gen.maxLocals, getBlock().localNum());
        this.bcStartIndex = gen.pos();
        getBlock().createBCode(gen);
        this.bcEndIndex = gen.pos();
      }
    } catch (Error e) {
      System.err.println(hostType().typeName() + ": " + this);
      throw e;
    }
  }
  /**
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:301
   */
  public void generateMethod(DataOutputStream out, ConstantPool cp) throws IOException {
  // System.out.println("[MethodDecl]\tname:\t"+name()+", desc:\t"+descName()+", host:\t"+hostType().typeName()+",\t"+hostType().packageName());
    out.writeChar(flags());
    out.writeChar(cp.addUtf8(name()));
    out.writeChar(cp.addUtf8(descName()));
    out.writeChar(attributes().size());
    for (Attribute attribute : attributes()) {
      attribute.emit(out);
    }
  }
  /**
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:326
   */
  public void touchMethod(ConstantPool cp) {
    cp.addUtf8(name());
    cp.addUtf8(descName());
    attributes();
  }
  /**
   * @aspect InnerClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:268
   */
  protected Stmt createAccessorStmt() {
    List<Access> argumentList = new List<Access>();
    for (ParameterDeclaration param : getParameterList()) {
      argumentList.add(new VarAccess(param.name()));
    }
    Access access = new BoundMethodAccess(name(), argumentList, this);
    if (!isStatic()) {
      access = new ThisAccess("this").qualifiesAccess(access);
    }
    return isVoid() ? (Stmt) new ExprStmt(access) : new ReturnStmt(new Opt(access));
  }
  /** Create a static super accessor binding. 
   * @aspect InnerClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:291
   */
  public Access createBoundSuperAccessor(List<Expr> args) {
    if (isStatic()) {
      return hostType().createQualifiedAccess().qualifiesAccess(
          new BoundMethodAccess(name(), args, this)
          .setSuperAccessor());
    } else {
      return new BoundMethodAccess(name(), args, this)
          .setSuperAccessor();
    }
  }
  /**
   * @aspect AnnotationsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AnnotationsCodegen.jrag:115
   */
  public void addRuntimeVisibleParameterAnnotationsAttribute(
      Collection<Attribute> attributes) {
    boolean foundVisibleAnnotations = false;
    Collection<Collection<Annotation>> annotations =
        new ArrayList<Collection<Annotation>>(getNumParameter());
    for (int i = 0; i < getNumParameter(); i++) {
      Collection<Annotation> a = getParameter(i).getModifiers().runtimeVisibleAnnotations();
      if (!a.isEmpty()) {
        foundVisibleAnnotations = true;
      }
      annotations.add(a);
    }
    if (foundVisibleAnnotations) {
      attributes.add(new ParameterAnnotationsAttribute(hostType().constantPool(), annotations,
          "RuntimeVisibleParameterAnnotations"));
    }
  }
  /**
   * @aspect AnnotationsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AnnotationsCodegen.jrag:144
   */
  public void addRuntimeInvisibleParameterAnnotationsAttribute(
      Collection<Attribute> attributes) {
    boolean foundInvisibleAnnotations = false;
    Collection<Collection<Annotation>> annotations =
        new ArrayList<Collection<Annotation>>(getNumParameter());
    for (int i = 0; i < getNumParameter(); i++) {
      Collection<Annotation> a = getParameter(i).getModifiers().runtimeInvisibleAnnotations();
      if (!a.isEmpty()) {
        foundInvisibleAnnotations = true;
      }
      annotations.add(a);
    }
    if (foundInvisibleAnnotations) {
      attributes.add(new ParameterAnnotationsAttribute(hostType().constantPool(), annotations,
          "RuntimeInvisibleParameterAnnotations"));
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public MethodDecl() {
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
    children = new ASTNode[5];
    setChild(new List(), 2);
    setChild(new List(), 3);
    setChild(new Opt(), 4);
  }
  /**
   * @declaredat ASTNode:16
   */
  public MethodDecl(Modifiers p0, Access p1, String p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
  }
  /**
   * @declaredat ASTNode:24
   */
  public MethodDecl(Modifiers p0, Access p1, beaver.Symbol p2, List<ParameterDeclaration> p3, List<Access> p4, Opt<Block> p5) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:33
   */
  protected int numChildren() {
    return 5;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:39
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    accessibleFrom_TypeDecl_reset();
    throwsException_TypeDecl_reset();
    signature_reset();
    lessSpecificThan_MethodDecl_reset();
    overrideCandidate_MethodDecl_reset();
    overrides_MethodDecl_reset();
    hides_MethodDecl_reset();
    parameterDeclaration_String_reset();
    type_reset();
    usesTypeVariable_reset();
    sourceMethodDecl_reset();
    subsignatureTo_MethodDecl_reset();
    returnTypeSubstitutableFor_MethodDecl_reset();
    attributes_reset();
    descName_reset();
    bytecodes_ConstantPool_reset();
    flags_reset();
    offsetBeforeParameters_reset();
    offsetAfterParameters_reset();
    resultOffset_reset();
    handlesException_TypeDecl_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:68
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:72
   */
  public MethodDecl clone() throws CloneNotSupportedException {
    MethodDecl node = (MethodDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:77
   */
  public MethodDecl copy() {
    try {
      MethodDecl node = (MethodDecl) clone();
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
   * @declaredat ASTNode:96
   */
  @Deprecated
  public MethodDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:106
   */
  public MethodDecl treeCopyNoTransform() {
    MethodDecl tree = (MethodDecl) copy();
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
   * @declaredat ASTNode:126
   */
  public MethodDecl treeCopy() {
    MethodDecl tree = (MethodDecl) copy();
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
   * @declaredat ASTNode:140
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node) && (tokenString_ID == ((MethodDecl) node).tokenString_ID);    
  }
  /**
   * Replaces the Modifiers child.
   * @param node The new node to replace the Modifiers child.
   * @apilevel high-level
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Modifiers child.
   * @return The current node used as the Modifiers child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Modifiers")
  public Modifiers getModifiers() {
    return (Modifiers) getChild(0);
  }
  /**
   * Retrieves the Modifiers child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Modifiers child.
   * @apilevel low-level
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers) getChildNoTransform(0);
  }
  /**
   * Replaces the TypeAccess child.
   * @param node The new node to replace the TypeAccess child.
   * @apilevel high-level
   */
  public void setTypeAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the TypeAccess child.
   * @return The current node used as the TypeAccess child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="TypeAccess")
  public Access getTypeAccess() {
    return (Access) getChild(1);
  }
  /**
   * Retrieves the TypeAccess child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the TypeAccess child.
   * @apilevel low-level
   */
  public Access getTypeAccessNoTransform() {
    return (Access) getChildNoTransform(1);
  }
  /**
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /** @apilevel internal 
   */
  protected String tokenString_ID;
  /**
   */
  public int IDstart;
  /**
   */
  public int IDend;
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
   * Replaces the Parameter list.
   * @param list The new list node to be used as the Parameter list.
   * @apilevel high-level
   */
  public void setParameterList(List<ParameterDeclaration> list) {
    setChild(list, 2);
  }
  /**
   * Retrieves the number of children in the Parameter list.
   * @return Number of children in the Parameter list.
   * @apilevel high-level
   */
  public int getNumParameter() {
    return getParameterList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Parameter list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Parameter list.
   * @apilevel low-level
   */
  public int getNumParameterNoTransform() {
    return getParameterListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Parameter list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Parameter list.
   * @apilevel high-level
   */
  public ParameterDeclaration getParameter(int i) {
    return (ParameterDeclaration) getParameterList().getChild(i);
  }
  /**
   * Check whether the Parameter list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasParameter() {
    return getParameterList().getNumChild() != 0;
  }
  /**
   * Append an element to the Parameter list.
   * @param node The element to append to the Parameter list.
   * @apilevel high-level
   */
  public void addParameter(ParameterDeclaration node) {
    List<ParameterDeclaration> list = (parent == null) ? getParameterListNoTransform() : getParameterList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addParameterNoTransform(ParameterDeclaration node) {
    List<ParameterDeclaration> list = getParameterListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the Parameter list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setParameter(ParameterDeclaration node, int i) {
    List<ParameterDeclaration> list = getParameterList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the Parameter list.
   * @return The node representing the Parameter list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Parameter")
  public List<ParameterDeclaration> getParameterList() {
    List<ParameterDeclaration> list = (List<ParameterDeclaration>) getChild(2);
    return list;
  }
  /**
   * Retrieves the Parameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Parameter list.
   * @apilevel low-level
   */
  public List<ParameterDeclaration> getParameterListNoTransform() {
    return (List<ParameterDeclaration>) getChildNoTransform(2);
  }
  /**
   * @return the element at index {@code i} in the Parameter list without
   * triggering rewrites.
   */
  public ParameterDeclaration getParameterNoTransform(int i) {
    return (ParameterDeclaration) getParameterListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Parameter list.
   * @return The node representing the Parameter list.
   * @apilevel high-level
   */
  public List<ParameterDeclaration> getParameters() {
    return getParameterList();
  }
  /**
   * Retrieves the Parameter list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Parameter list.
   * @apilevel low-level
   */
  public List<ParameterDeclaration> getParametersNoTransform() {
    return getParameterListNoTransform();
  }
  /**
   * Replaces the Exception list.
   * @param list The new list node to be used as the Exception list.
   * @apilevel high-level
   */
  public void setExceptionList(List<Access> list) {
    setChild(list, 3);
  }
  /**
   * Retrieves the number of children in the Exception list.
   * @return Number of children in the Exception list.
   * @apilevel high-level
   */
  public int getNumException() {
    return getExceptionList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Exception list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Exception list.
   * @apilevel low-level
   */
  public int getNumExceptionNoTransform() {
    return getExceptionListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Exception list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Exception list.
   * @apilevel high-level
   */
  public Access getException(int i) {
    return (Access) getExceptionList().getChild(i);
  }
  /**
   * Check whether the Exception list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasException() {
    return getExceptionList().getNumChild() != 0;
  }
  /**
   * Append an element to the Exception list.
   * @param node The element to append to the Exception list.
   * @apilevel high-level
   */
  public void addException(Access node) {
    List<Access> list = (parent == null) ? getExceptionListNoTransform() : getExceptionList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addExceptionNoTransform(Access node) {
    List<Access> list = getExceptionListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the Exception list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setException(Access node, int i) {
    List<Access> list = getExceptionList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the Exception list.
   * @return The node representing the Exception list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Exception")
  public List<Access> getExceptionList() {
    List<Access> list = (List<Access>) getChild(3);
    return list;
  }
  /**
   * Retrieves the Exception list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Exception list.
   * @apilevel low-level
   */
  public List<Access> getExceptionListNoTransform() {
    return (List<Access>) getChildNoTransform(3);
  }
  /**
   * @return the element at index {@code i} in the Exception list without
   * triggering rewrites.
   */
  public Access getExceptionNoTransform(int i) {
    return (Access) getExceptionListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Exception list.
   * @return The node representing the Exception list.
   * @apilevel high-level
   */
  public List<Access> getExceptions() {
    return getExceptionList();
  }
  /**
   * Retrieves the Exception list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Exception list.
   * @apilevel low-level
   */
  public List<Access> getExceptionsNoTransform() {
    return getExceptionListNoTransform();
  }
  /**
   * Replaces the optional node for the Block child. This is the <code>Opt</code>
   * node containing the child Block, not the actual child!
   * @param opt The new node to be used as the optional node for the Block child.
   * @apilevel low-level
   */
  public void setBlockOpt(Opt<Block> opt) {
    setChild(opt, 4);
  }
  /**
   * Replaces the (optional) Block child.
   * @param node The new node to be used as the Block child.
   * @apilevel high-level
   */
  public void setBlock(Block node) {
    getBlockOpt().setChild(node, 0);
  }
  /**
   * Check whether the optional Block child exists.
   * @return {@code true} if the optional Block child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasBlock() {
    return getBlockOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) Block child.
   * @return The Block child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Block getBlock() {
    return (Block) getBlockOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the Block child. This is the <code>Opt</code> node containing the child Block, not the actual child!
   * @return The optional node for child the Block child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="Block")
  public Opt<Block> getBlockOpt() {
    return (Opt<Block>) getChild(4);
  }
  /**
   * Retrieves the optional node for child Block. This is the <code>Opt</code> node containing the child Block, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child Block.
   * @apilevel low-level
   */
  public Opt<Block> getBlockOptNoTransform() {
    return (Opt<Block>) getChildNoTransform(4);
  }
  /**
   * @aspect MethodDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:232
   */
  private boolean refined_MethodDecl_MethodDecl_sameSignature_MethodDecl(MethodDecl other)
{ return signature().equals(other.signature()); }
  /**
   * @aspect Attributes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Attributes.jrag:269
   */
  private Collection<Attribute> refined_Attributes_MethodDecl_attributes()
{
    Collection<Attribute> attributes = new ArrayList<Attribute>();
    CodeGeneration bytecodes = bytecodes(hostType().constantPool());
    attributes.add(new ExceptionsAttribute(bytecodes, this));
    if (isAbstract() || isNative()) {
      return attributes;
    } else {
      attributes.add(new CodeAttribute(bytecodes, this));
      if (getModifiers().isSynthetic()) {
        attributes.add(new SyntheticAttribute(hostType().constantPool()));
      }
      return attributes;
    }
  }
  /**
   * @aspect Flags
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Flags.jrag:60
   */
  private int refined_Flags_MethodDecl_flags()
{
    int res = 0;
    if (isPublic()) {
      res |= Modifiers.ACC_PUBLIC;
    }
    if (isPrivate()) {
      res |= Modifiers.ACC_PRIVATE;
    }
    if (isProtected()) {
      res |= Modifiers.ACC_PROTECTED;
    }
    if (isStatic()) {
      res |= Modifiers.ACC_STATIC;
    }
    if (isFinal()) {
      res |= Modifiers.ACC_FINAL;
    }
    if (isSynchronized()) {
      res |= Modifiers.ACC_SYNCHRONIZED;
    }
    if (isNative()) {
      res |= Modifiers.ACC_NATIVE;
    }
    if (isAbstract()) {
      res |= Modifiers.ACC_ABSTRACT;
    }
    if (isStrictfp() || (hostType().isStrictfp() && !hostType().isInterfaceDecl())) {
      res |= Modifiers.ACC_STRICT;
    }
    if (isSynthetic()) {
      res |= Modifiers.ACC_SYNTHETIC;
    }
    return res;
  }
  /**
   * @aspect AnnotationsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AnnotationsCodegen.jrag:46
   */
  private Collection<Attribute> refined_AnnotationsCodegen_MethodDecl_attributes()
{
    Collection<Attribute> attributes = refined_Attributes_MethodDecl_attributes();
    getModifiers().addRuntimeVisibleAnnotationsAttribute(attributes);
    getModifiers().addRuntimeInvisibleAnnotationsAttribute(attributes);
    addRuntimeVisibleParameterAnnotationsAttribute(attributes);
    addRuntimeInvisibleParameterAnnotationsAttribute(attributes);
    return attributes;
  }
  /** @apilevel internal */
  private void accessibleFrom_TypeDecl_reset() {
    accessibleFrom_TypeDecl_computed = new java.util.HashMap(4);
    accessibleFrom_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map accessibleFrom_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map accessibleFrom_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/AccessControl.jrag:104
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessControl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/AccessControl.jrag:104")
  public boolean accessibleFrom(TypeDecl type) {
    Object _parameters = type;
    if (accessibleFrom_TypeDecl_computed == null) accessibleFrom_TypeDecl_computed = new java.util.HashMap(4);
    if (accessibleFrom_TypeDecl_values == null) accessibleFrom_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (accessibleFrom_TypeDecl_values.containsKey(_parameters) && accessibleFrom_TypeDecl_computed != null
        && accessibleFrom_TypeDecl_computed.containsKey(_parameters)
        && (accessibleFrom_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || accessibleFrom_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) accessibleFrom_TypeDecl_values.get(_parameters);
    }
    boolean accessibleFrom_TypeDecl_value = accessibleFrom_compute(type);
    if (state().inCircle()) {
      accessibleFrom_TypeDecl_values.put(_parameters, accessibleFrom_TypeDecl_value);
      accessibleFrom_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      accessibleFrom_TypeDecl_values.put(_parameters, accessibleFrom_TypeDecl_value);
      accessibleFrom_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return accessibleFrom_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean accessibleFrom_compute(TypeDecl type) {
      if (isPublic()) {
        return true;
      } else if (isProtected()) {
        if (hostPackage().equals(type.hostPackage())) {
          return true;
        }
        if (type.withinBodyThatSubclasses(hostType()) != null) {
          return true;
        }
        return false;
      } else if (isPrivate()) {
        return hostType().topLevelType() == type.topLevelType();
      } else {
        return hostPackage().equals(type.hostPackage());
      }
    }
  /**
   * @attribute syn
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:46
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ErrorCheck", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:46")
  public int lineNumber() {
    int lineNumber_value = getLine(IDstart);
    return lineNumber_value;
  }
  /** @apilevel internal */
  private void throwsException_TypeDecl_reset() {
    throwsException_TypeDecl_computed = new java.util.HashMap(4);
    throwsException_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map throwsException_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map throwsException_TypeDecl_computed;
  /**
   * @attribute syn
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:204
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:204")
  public boolean throwsException(TypeDecl exceptionType) {
    Object _parameters = exceptionType;
    if (throwsException_TypeDecl_computed == null) throwsException_TypeDecl_computed = new java.util.HashMap(4);
    if (throwsException_TypeDecl_values == null) throwsException_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (throwsException_TypeDecl_values.containsKey(_parameters) && throwsException_TypeDecl_computed != null
        && throwsException_TypeDecl_computed.containsKey(_parameters)
        && (throwsException_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || throwsException_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) throwsException_TypeDecl_values.get(_parameters);
    }
    boolean throwsException_TypeDecl_value = throwsException_compute(exceptionType);
    if (state().inCircle()) {
      throwsException_TypeDecl_values.put(_parameters, throwsException_TypeDecl_value);
      throwsException_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      throwsException_TypeDecl_values.put(_parameters, throwsException_TypeDecl_value);
      throwsException_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return throwsException_TypeDecl_value;
  }
  /** @apilevel internal */
  private boolean throwsException_compute(TypeDecl exceptionType) {
      for (Access exception : getExceptionList()) {
        if (exceptionType.instanceOf(exception.type())) {
          return true;
        }
      }
      return false;
    }
  /**
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:193
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:193")
  public String name() {
    String name_value = getID();
    return name_value;
  }
  /** @apilevel internal */
  private void signature_reset() {
    signature_computed = null;
    signature_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle signature_computed = null;

  /** @apilevel internal */
  protected String signature_value;

  /**
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:196
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:196")
  public String signature() {
    ASTNode$State state = state();
    if (signature_computed == ASTNode$State.NON_CYCLE || signature_computed == state().cycle()) {
      return signature_value;
    }
    signature_value = signature_compute();
    if (state().inCircle()) {
      signature_computed = state().cycle();
    
    } else {
      signature_computed = ASTNode$State.NON_CYCLE;
    
    }
    return signature_value;
  }
  /** @apilevel internal */
  private String signature_compute() {
      StringBuilder sb = new StringBuilder();
      sb.append(name() + "(");
      for (int i = 0; i < getNumParameter(); i++) {
        if (i != 0) {
          sb.append(", ");
        }
        sb.append(getParameter(i).type().erasure().typeName());
      }
      sb.append(")");
      return sb.toString();
    }
  /**
   * @return Method signature, including generic parameters.
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:212
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:212")
  public String fullSignature() {
    {
        StringBuilder sb = new StringBuilder();
        sb.append(name() + "(");
        for (int i = 0; i < getNumParameter(); i++) {
          if (i != 0) {
            sb.append(", ");
          }
          if (getParameter(i).type() instanceof PrimitiveType) {
            sb.append(getParameter(i).type().typeName());
          } else {
            sb.append(getParameter(i).type().fullName());
          }
        }
        sb.append(")");
        return sb.toString();
      }
  }
  /**
   * 8.4.2 Method Signature
   * @param other
   * @return {@code true} if the signature of this method is same as the
   * the signature of the argument method
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:232
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:232")
  public boolean sameSignature(MethodDecl other) {
    {
        if (!refined_MethodDecl_MethodDecl_sameSignature_MethodDecl(other)) {
          return false;
        }
        for (int i = 0; i < getNumParameter(); ++i) {
          TypeDecl p1 = getParameter(i).type();
          TypeDecl p2 = other.getParameter(i).type();
          // JLSv7 $8.4.8.1 exception: if one parameter type is raw, then don't check type bounds
          if (p1 != p2 && !p1.isRawType() && !p2.isRawType()) {
            return false;
          }
        }
        return true;
      }
  }
  /**
   * @param m argument method to compare to
   * @return {@code true} if this the argument method is less specific than this
   * and this is not less specific than the argument
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:240
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:240")
  public boolean moreSpecificThan(MethodDecl m) {
    boolean moreSpecificThan_MethodDecl_value = m.lessSpecificThan(this) && !this.lessSpecificThan(m);
    return moreSpecificThan_MethodDecl_value;
  }
  /** @apilevel internal */
  private void lessSpecificThan_MethodDecl_reset() {
    lessSpecificThan_MethodDecl_computed = new java.util.HashMap(4);
    lessSpecificThan_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map lessSpecificThan_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map lessSpecificThan_MethodDecl_computed;
  /**
   * Caution: a less specific than b does not mean b is not less specific than a!
   * @param m argument method to compare to
   * @return {@code true} if any parameter of this method decl is not a
   * subtype (non-proper subtype) of the corresponding parameter of the
   * argument method
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:250
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:250")
  public boolean lessSpecificThan(MethodDecl m) {
    Object _parameters = m;
    if (lessSpecificThan_MethodDecl_computed == null) lessSpecificThan_MethodDecl_computed = new java.util.HashMap(4);
    if (lessSpecificThan_MethodDecl_values == null) lessSpecificThan_MethodDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (lessSpecificThan_MethodDecl_values.containsKey(_parameters) && lessSpecificThan_MethodDecl_computed != null
        && lessSpecificThan_MethodDecl_computed.containsKey(_parameters)
        && (lessSpecificThan_MethodDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || lessSpecificThan_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) lessSpecificThan_MethodDecl_values.get(_parameters);
    }
    boolean lessSpecificThan_MethodDecl_value = lessSpecificThan_compute(m);
    if (state().inCircle()) {
      lessSpecificThan_MethodDecl_values.put(_parameters, lessSpecificThan_MethodDecl_value);
      lessSpecificThan_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      lessSpecificThan_MethodDecl_values.put(_parameters, lessSpecificThan_MethodDecl_value);
      lessSpecificThan_MethodDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return lessSpecificThan_MethodDecl_value;
  }
  /** @apilevel internal */
  private boolean lessSpecificThan_compute(MethodDecl m) {
      int numA = getNumParameter();
      int numB = m.getNumParameter();
      int num = Math.max(numA, numB);
      for (int i = 0; i < num; i++) {
        TypeDecl t1 = getParameter(Math.min(i, numA - 1)).type();
        TypeDecl t2 = m.getParameter(Math.min(i, numB - 1)).type();
        if (!t1.instanceOf(t2) && !t1.withinBounds(t2)) {
          return true;
        }
      }
      return false;
    }
  /** @apilevel internal */
  private void overrideCandidate_MethodDecl_reset() {
    overrideCandidate_MethodDecl_computed = new java.util.HashMap(4);
    overrideCandidate_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map overrideCandidate_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map overrideCandidate_MethodDecl_computed;
  /**
   * Only check if this method would be able to override other method,
   * not if this method is declared in a subtype of the hostType of
   * other method. NB: does not check for equal signature!
   * @param m other method
   * @return {@code true} of the method could potentially override
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:319
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:319")
  public boolean overrideCandidate(MethodDecl m) {
    Object _parameters = m;
    if (overrideCandidate_MethodDecl_computed == null) overrideCandidate_MethodDecl_computed = new java.util.HashMap(4);
    if (overrideCandidate_MethodDecl_values == null) overrideCandidate_MethodDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (overrideCandidate_MethodDecl_values.containsKey(_parameters) && overrideCandidate_MethodDecl_computed != null
        && overrideCandidate_MethodDecl_computed.containsKey(_parameters)
        && (overrideCandidate_MethodDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || overrideCandidate_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) overrideCandidate_MethodDecl_values.get(_parameters);
    }
    boolean overrideCandidate_MethodDecl_value = !isStatic() && !m.isPrivate() && m.accessibleFrom(hostType());
    if (state().inCircle()) {
      overrideCandidate_MethodDecl_values.put(_parameters, overrideCandidate_MethodDecl_value);
      overrideCandidate_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      overrideCandidate_MethodDecl_values.put(_parameters, overrideCandidate_MethodDecl_value);
      overrideCandidate_MethodDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return overrideCandidate_MethodDecl_value;
  }
  /** @apilevel internal */
  private void overrides_MethodDecl_reset() {
    overrides_MethodDecl_computed = new java.util.HashMap(4);
    overrides_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map overrides_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map overrides_MethodDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:322
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:322")
  public boolean overrides(MethodDecl m) {
    Object _parameters = m;
    if (overrides_MethodDecl_computed == null) overrides_MethodDecl_computed = new java.util.HashMap(4);
    if (overrides_MethodDecl_values == null) overrides_MethodDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (overrides_MethodDecl_values.containsKey(_parameters) && overrides_MethodDecl_computed != null
        && overrides_MethodDecl_computed.containsKey(_parameters)
        && (overrides_MethodDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || overrides_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) overrides_MethodDecl_values.get(_parameters);
    }
    boolean overrides_MethodDecl_value = !isStatic() && !m.isPrivate() && m.accessibleFrom(hostType())
          && hostType().instanceOf(m.hostType()) && m.signature().equals(signature());
    if (state().inCircle()) {
      overrides_MethodDecl_values.put(_parameters, overrides_MethodDecl_value);
      overrides_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      overrides_MethodDecl_values.put(_parameters, overrides_MethodDecl_value);
      overrides_MethodDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return overrides_MethodDecl_value;
  }
  /** @apilevel internal */
  private void hides_MethodDecl_reset() {
    hides_MethodDecl_computed = new java.util.HashMap(4);
    hides_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map hides_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map hides_MethodDecl_computed;
  /**
   * @attribute syn
   * @aspect MethodDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:326
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:326")
  public boolean hides(MethodDecl m) {
    Object _parameters = m;
    if (hides_MethodDecl_computed == null) hides_MethodDecl_computed = new java.util.HashMap(4);
    if (hides_MethodDecl_values == null) hides_MethodDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (hides_MethodDecl_values.containsKey(_parameters) && hides_MethodDecl_computed != null
        && hides_MethodDecl_computed.containsKey(_parameters)
        && (hides_MethodDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || hides_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) hides_MethodDecl_values.get(_parameters);
    }
    boolean hides_MethodDecl_value = isStatic() && !m.isPrivate() && m.accessibleFrom(hostType())
          && hostType().instanceOf(m.hostType()) && m.signature().equals(signature());
    if (state().inCircle()) {
      hides_MethodDecl_values.put(_parameters, hides_MethodDecl_value);
      hides_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      hides_MethodDecl_values.put(_parameters, hides_MethodDecl_value);
      hides_MethodDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return hides_MethodDecl_value;
  }
  /** @apilevel internal */
  private void parameterDeclaration_String_reset() {
    parameterDeclaration_String_computed = new java.util.HashMap(4);
    parameterDeclaration_String_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map parameterDeclaration_String_values;
  /** @apilevel internal */
  protected java.util.Map parameterDeclaration_String_computed;
  /** @return the first variable declaration with the given name. 
   * @attribute syn
   * @aspect VariableScope
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupVariable.jrag:174
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupVariable.jrag:174")
  public SimpleSet<Variable> parameterDeclaration(String name) {
    Object _parameters = name;
    if (parameterDeclaration_String_computed == null) parameterDeclaration_String_computed = new java.util.HashMap(4);
    if (parameterDeclaration_String_values == null) parameterDeclaration_String_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (parameterDeclaration_String_values.containsKey(_parameters) && parameterDeclaration_String_computed != null
        && parameterDeclaration_String_computed.containsKey(_parameters)
        && (parameterDeclaration_String_computed.get(_parameters) == ASTNode$State.NON_CYCLE || parameterDeclaration_String_computed.get(_parameters) == state().cycle())) {
      return (SimpleSet<Variable>) parameterDeclaration_String_values.get(_parameters);
    }
    SimpleSet<Variable> parameterDeclaration_String_value = parameterDeclaration_compute(name);
    if (state().inCircle()) {
      parameterDeclaration_String_values.put(_parameters, parameterDeclaration_String_value);
      parameterDeclaration_String_computed.put(_parameters, state().cycle());
    
    } else {
      parameterDeclaration_String_values.put(_parameters, parameterDeclaration_String_value);
      parameterDeclaration_String_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return parameterDeclaration_String_value;
  }
  /** @apilevel internal */
  private SimpleSet<Variable> parameterDeclaration_compute(String name) {
      for (int i = 0; i < getNumParameter(); i++) {
        if (getParameter(i).name().equals(name)) {
          return (ParameterDeclaration) getParameter(i);
        }
      }
      return emptySet();
    }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:152
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:152")
  public Collection<Problem> modifierProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        if (hostType().isClassDecl()) {
          // 8.4.3.1
          if (!hostType().isEnumDecl() && isAbstract() && !hostType().isAbstract()) {
            problems.add(error("class must be abstract to include abstract methods"));
          }
          // 8.4.3.1
          if (isAbstract() && isPrivate()) {
            problems.add(error("method may not be abstract and private"));
          }
          // 8.4.3.1
          // 8.4.3.2
          if (isAbstract() && isStatic()) {
            problems.add(error("method may not be abstract and static"));
          }
          if (isAbstract() && isSynchronized()) {
            problems.add(error("method may not be abstract and synchronized"));
          }
          // 8.4.3.4
          if (isAbstract() && isNative()) {
            problems.add(error("method may not be abstract and native"));
          }
          if (isAbstract() && isStrictfp()) {
            problems.add(error("method may not be abstract and strictfp"));
          }
          if (isNative() && isStrictfp()) {
            problems.add(error("method may not be native and strictfp"));
          }
          if (isDefault()) {
            problems.add(error("non-interface methods may not use the default modifier"));
          }
        }
        if (hostType().isInterfaceDecl()) {
          // 9.4
          if (isAbstract()) {
            if (isStatic()) {
              problems.add(errorf("interface method %s in %s can not be both abstract and static",
                  signature(), hostType().typeName()));
            }
            if (isDefault()) {
              problems.add(errorf("interface method %s in %s can not be both abstract and default",
                  signature(), hostType().typeName()));
            }
            if (isStrictfp()) {
              problems.add(errorf("interface method %s in %s can not be both abstract and strictfp",
                  signature(), hostType().typeName()));
            }
          }
          if (isStatic() && isDefault()) {
            problems.add(errorf("interface method %s in %s can not be both static and default",
                signature(), hostType().typeName()));
          }
          if (isNative()) {
            problems.add(errorf("interface method %s in %s may not be native",
                signature(), hostType().typeName()));
          }
          if (isSynchronized()) {
            problems.add(errorf("interface method %s in %s may not be synchronized",
                signature(), hostType().typeName()));
          }
          if (isProtected()) {
            problems.add(errorf("interface method %s in %s may not be protected",
                signature(), hostType().typeName()));
          }
          if (isPrivate()) {
            problems.add(errorf("interface method %s in %s may not be private",
                signature(), hostType().typeName()));
          } else if (isFinal()) {
            problems.add(errorf("interface method %s in %s may not be final",
                signature(), hostType().typeName()));
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:249
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:249")
  public boolean isSynthetic() {
    boolean isSynthetic_value = getModifiers().isSynthetic();
    return isSynthetic_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:259
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:259")
  public boolean isPublic() {
    boolean isPublic_value = getModifiers().isPublic() || hostType().isInterfaceDecl();
    return isPublic_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:260
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:260")
  public boolean isPrivate() {
    boolean isPrivate_value = getModifiers().isPrivate();
    return isPrivate_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:261
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:261")
  public boolean isProtected() {
    boolean isProtected_value = getModifiers().isProtected();
    return isProtected_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:262
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:262")
  public boolean isAbstract() {
    {
        return getModifiers().isAbstract() || (hostType().isInterfaceDecl() && !isStatic() && !isDefault());
      }
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:263
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:263")
  public boolean isStatic() {
    boolean isStatic_value = getModifiers().isStatic();
    return isStatic_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:266
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:266")
  public boolean isFinal() {
    boolean isFinal_value = getModifiers().isFinal() || hostType().isFinal() || isPrivate();
    return isFinal_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:268
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:268")
  public boolean isSynchronized() {
    boolean isSynchronized_value = getModifiers().isSynchronized();
    return isSynchronized_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:269
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:269")
  public boolean isNative() {
    boolean isNative_value = getModifiers().isNative();
    return isNative_value;
  }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:270
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:270")
  public boolean isStrictfp() {
    boolean isStrictfp_value = getModifiers().isStrictfp();
    return isStrictfp_value;
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:147
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:147")
  public Collection<Problem> nameProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        // 8.4
        // 8.4.2
        if (hostType().methodsSignature(signature()).size() > 1) {
          problems.add(errorf("method with signature %s is multiply declared in type %s", signature(),
              hostType().typeName()));
        }
        // 8.4.3.4
        if (isNative() && hasBlock()) {
          problems.add(error("native methods must have an empty semicolon body"));
        }
        // 8.4.5
        if (isAbstract() && hasBlock()) {
          problems.add(error("abstract methods must have an empty semicolon body"));
        }
        // 8.4.5
        if (!hasBlock() && !(isNative() || isAbstract())) {
          problems.add(error("only abstract and native methods may have an empty semicolon body"));
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrintUtil.jrag:219
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrintUtil.jrag:219")
  public boolean hasModifiers() {
    boolean hasModifiers_value = getModifiers().getNumModifier() > 0;
    return hasModifiers_value;
  }
  /**
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrintUtil.jrag:231
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrintUtil.jrag:231")
  public boolean hasExceptions() {
    boolean hasExceptions_value = getNumException() > 0;
    return hasExceptions_value;
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:290
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:290")
  public TypeDecl type() {
    ASTNode$State state = state();
    if (type_computed == ASTNode$State.NON_CYCLE || type_computed == state().cycle()) {
      return type_value;
    }
    type_value = getTypeAccess().type();
    if (state().inCircle()) {
      type_computed = state().cycle();
    
    } else {
      type_computed = ASTNode$State.NON_CYCLE;
    
    }
    return type_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:292
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:292")
  public boolean isVoid() {
    boolean isVoid_value = type().isVoid();
    return isVoid_value;
  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:514
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:514")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        // Thrown vs super class method see MethodDecl.nameCheck.
        // 8.4.4
        TypeDecl exceptionType = typeThrowable();
        for (int i = 0; i < getNumException(); i++) {
          TypeDecl typeDecl = getException(i).type();
          if (!typeDecl.instanceOf(exceptionType)) {
            problems.add(errorf("%s throws non throwable type %s", signature(), typeDecl.fullName()));
          }
        }
        // Check returns.
        if (!isVoid() && hasBlock() && getBlock().canCompleteNormally()) {
          problems.add(error("the body of a non void method may not complete normally"));
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect TypeHierarchyCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeHierarchyCheck.jrag:387
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeHierarchyCheck.jrag:387")
  public boolean mayOverride(MethodDecl m) {
    {
        // 9.4.3
        if (isDefault() && m.hostType().isType("java.lang", "Object") && !m.isPrivate()) {
          return false;
        } else {
          MethodDecl self = this;
          if (self.isGeneric()) {
            self = genericDecl().rawMethodDecl();
          }
          if (m.isGeneric()) {
            m = m.genericDecl().rawMethodDecl();
          }
          return self.returnTypeSubstitutableFor(m);
        }
      }
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:231
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:231")
  public boolean annotationMethodOverride() {
    boolean annotationMethodOverride_value = !hostType().ancestorMethods(signature()).isEmpty();
    return annotationMethodOverride_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:428
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:428")
  public boolean hasAnnotationSuppressWarnings(String annot) {
    boolean hasAnnotationSuppressWarnings_String_value = getModifiers().hasAnnotationSuppressWarnings(annot);
    return hasAnnotationSuppressWarnings_String_value;
  }
  /**
   * @attribute syn
   * @aspect Annotations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:485
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Annotations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:485")
  public boolean isDeprecated() {
    boolean isDeprecated_value = getModifiers().hasDeprecatedAnnotation();
    return isDeprecated_value;
  }
  /** @apilevel internal */
  private void usesTypeVariable_reset() {
    usesTypeVariable_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle usesTypeVariable_computed = null;

  /** @apilevel internal */
  protected boolean usesTypeVariable_value;

  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1179
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1179")
  public boolean usesTypeVariable() {
    ASTNode$State state = state();
    if (usesTypeVariable_computed == ASTNode$State.NON_CYCLE || usesTypeVariable_computed == state().cycle()) {
      return usesTypeVariable_value;
    }
    usesTypeVariable_value = getModifiers().usesTypeVariable() || getTypeAccess().usesTypeVariable()
          || getParameterList().usesTypeVariable() || getExceptionList().usesTypeVariable();
    if (state().inCircle()) {
      usesTypeVariable_computed = state().cycle();
    
    } else {
      usesTypeVariable_computed = ASTNode$State.NON_CYCLE;
    
    }
    return usesTypeVariable_value;
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1524
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1524")
  public MethodDecl erasedMethod() {
    MethodDecl erasedMethod_value = this;
    return erasedMethod_value;
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1563
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1563")
  public boolean isSubstitutable() {
    boolean isSubstitutable_value = !isStatic();
    return isSubstitutable_value;
  }
  /** @apilevel internal */
  private void sourceMethodDecl_reset() {
    sourceMethodDecl_computed = null;
    sourceMethodDecl_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle sourceMethodDecl_computed = null;

  /** @apilevel internal */
  protected MethodDecl sourceMethodDecl_value;

  /**
   * @attribute syn
   * @aspect SourceDeclarations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1729
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SourceDeclarations", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1729")
  public MethodDecl sourceMethodDecl() {
    ASTNode$State state = state();
    if (sourceMethodDecl_computed == ASTNode$State.NON_CYCLE || sourceMethodDecl_computed == state().cycle()) {
      return sourceMethodDecl_value;
    }
    sourceMethodDecl_value = this;
    if (state().inCircle()) {
      sourceMethodDecl_computed = state().cycle();
    
    } else {
      sourceMethodDecl_computed = ASTNode$State.NON_CYCLE;
    
    }
    return sourceMethodDecl_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsParTypeDecl.jrag:98
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsParTypeDecl.jrag:98")
  public boolean visibleTypeParameters() {
    boolean visibleTypeParameters_value = !isStatic();
    return visibleTypeParameters_value;
  }
  /**
   * Note: isGeneric must be called first to check if this declaration is generic.
   * Otherwise this attribute will throw an error!
   * @return the original generic declaration of this method.
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/MethodSignature.jrag:336
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/MethodSignature.jrag:336")
  public GenericMethodDecl genericDecl() {
    {
        throw new Error("can not evaulate generic declaration of non-generic method");
      }
  }
  /**
   * @attribute syn
   * @aspect MethodSignature15
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/MethodSignature.jrag:425
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="MethodSignature15", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/MethodSignature.jrag:425")
  public int arity() {
    int arity_value = getNumParameter();
    return arity_value;
  }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/VariableArityParameters.jrag:53
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableArityParameters", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/VariableArityParameters.jrag:53")
  public boolean isVariableArity() {
    boolean isVariableArity_value = getNumParameter() > 0 && getParameter(getNumParameter() - 1).isVariableArity();
    return isVariableArity_value;
  }
  /**
   * @attribute syn
   * @aspect VariableArityParameters
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/VariableArityParameters.jrag:63
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableArityParameters", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/VariableArityParameters.jrag:63")
  public ParameterDeclaration lastParameter() {
    ParameterDeclaration lastParameter_value = getParameter(getNumParameter() - 1);
    return lastParameter_value;
  }
  /**
   * @return true if the modifier list includes the SafeVarargs annotation
   * @attribute syn
   * @aspect SafeVarargs
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/SafeVarargs.jrag:41
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SafeVarargs", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/SafeVarargs.jrag:41")
  public boolean hasAnnotationSafeVarargs() {
    boolean hasAnnotationSafeVarargs_value = getModifiers().hasAnnotationSafeVarargs();
    return hasAnnotationSafeVarargs_value;
  }
  /**
   * It is an error if the SafeVarargs annotation is used on something
   * that is not a variable arity method or constructor.
   * @attribute syn
   * @aspect SafeVarargs
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/SafeVarargs.jrag:72
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SafeVarargs", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/SafeVarargs.jrag:72")
  public boolean hasIllegalAnnotationSafeVarargs() {
    boolean hasIllegalAnnotationSafeVarargs_value = hasAnnotationSafeVarargs() && (!isVariableArity() || (!isFinal() && !isStatic()));
    return hasIllegalAnnotationSafeVarargs_value;
  }
  /**
   * @attribute syn
   * @aspect SuppressWarnings
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/SuppressWarnings.jrag:45
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SuppressWarnings", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/SuppressWarnings.jrag:45")
  public boolean suppressWarnings(String type) {
    boolean suppressWarnings_String_value = hasAnnotationSuppressWarnings(type) || withinSuppressWarnings(type);
    return suppressWarnings_String_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/EffectivelyFinal.jrag:40
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/EffectivelyFinal.jrag:40")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = hasBlock() && getBlock().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /** @apilevel internal */
  private void subsignatureTo_MethodDecl_reset() {
    subsignatureTo_MethodDecl_computed = new java.util.HashMap(4);
    subsignatureTo_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map subsignatureTo_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map subsignatureTo_MethodDecl_computed;
  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/FunctionalInterface.jrag:62
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/FunctionalInterface.jrag:62")
  public boolean subsignatureTo(MethodDecl m) {
    Object _parameters = m;
    if (subsignatureTo_MethodDecl_computed == null) subsignatureTo_MethodDecl_computed = new java.util.HashMap(4);
    if (subsignatureTo_MethodDecl_values == null) subsignatureTo_MethodDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (subsignatureTo_MethodDecl_values.containsKey(_parameters) && subsignatureTo_MethodDecl_computed != null
        && subsignatureTo_MethodDecl_computed.containsKey(_parameters)
        && (subsignatureTo_MethodDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || subsignatureTo_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) subsignatureTo_MethodDecl_values.get(_parameters);
    }
    boolean subsignatureTo_MethodDecl_value = subsignatureTo_compute(m);
    if (state().inCircle()) {
      subsignatureTo_MethodDecl_values.put(_parameters, subsignatureTo_MethodDecl_value);
      subsignatureTo_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      subsignatureTo_MethodDecl_values.put(_parameters, subsignatureTo_MethodDecl_value);
      subsignatureTo_MethodDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return subsignatureTo_MethodDecl_value;
  }
  /** @apilevel internal */
  private boolean subsignatureTo_compute(MethodDecl m) {
      if (fullSignature().equals(m.fullSignature())) {
        return true;
      } else if (fullSignature().equals(m.signature())) {
        return true;
      } else {
        return false;
      }
    }
  /** @apilevel internal */
  private void returnTypeSubstitutableFor_MethodDecl_reset() {
    returnTypeSubstitutableFor_MethodDecl_computed = new java.util.HashMap(4);
    returnTypeSubstitutableFor_MethodDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map returnTypeSubstitutableFor_MethodDecl_values;
  /** @apilevel internal */
  protected java.util.Map returnTypeSubstitutableFor_MethodDecl_computed;
  /**
   * @attribute syn
   * @aspect FunctionalInterface
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/FunctionalInterface.jrag:86
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="FunctionalInterface", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/FunctionalInterface.jrag:86")
  public boolean returnTypeSubstitutableFor(MethodDecl m) {
    Object _parameters = m;
    if (returnTypeSubstitutableFor_MethodDecl_computed == null) returnTypeSubstitutableFor_MethodDecl_computed = new java.util.HashMap(4);
    if (returnTypeSubstitutableFor_MethodDecl_values == null) returnTypeSubstitutableFor_MethodDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (returnTypeSubstitutableFor_MethodDecl_values.containsKey(_parameters) && returnTypeSubstitutableFor_MethodDecl_computed != null
        && returnTypeSubstitutableFor_MethodDecl_computed.containsKey(_parameters)
        && (returnTypeSubstitutableFor_MethodDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || returnTypeSubstitutableFor_MethodDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) returnTypeSubstitutableFor_MethodDecl_values.get(_parameters);
    }
    boolean returnTypeSubstitutableFor_MethodDecl_value = returnTypeSubstitutableFor_compute(m);
    if (state().inCircle()) {
      returnTypeSubstitutableFor_MethodDecl_values.put(_parameters, returnTypeSubstitutableFor_MethodDecl_value);
      returnTypeSubstitutableFor_MethodDecl_computed.put(_parameters, state().cycle());
    
    } else {
      returnTypeSubstitutableFor_MethodDecl_values.put(_parameters, returnTypeSubstitutableFor_MethodDecl_value);
      returnTypeSubstitutableFor_MethodDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return returnTypeSubstitutableFor_MethodDecl_value;
  }
  /** @apilevel internal */
  private boolean returnTypeSubstitutableFor_compute(MethodDecl m) {
      TypeDecl t1 = type();
      TypeDecl t2 = m.type();
      if (t1 instanceof VoidType && t2 instanceof VoidType) {
        return true;
      } else if (t1 instanceof PrimitiveType && t2 instanceof PrimitiveType) {
        PrimitiveType p1 = (PrimitiveType) t1;
        PrimitiveType p2 = (PrimitiveType) t2;
        return p1 == p2;
      } else if (t1.isReferenceType() && t2.isReferenceType()) {
        if (t1.strictSubtype(t2)) {
          return true;
        } else {
          return !sameSignature(m) && (t1 == t2.erasure() || t1.erasure() == t2);
        }
      } else {
        return false;
      }
    }
  /**
   * @attribute syn
   * @aspect Modifiers
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/Modifiers.jrag:31
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Modifiers", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/Modifiers.jrag:31")
  public boolean isDefault() {
    boolean isDefault_value = getModifiers().isDefault();
    return isDefault_value;
  }
  /**
   * If the method is parameterized, this returns the non-wildcard parameterization
   * according to the rules specified in JLS 8 &sect;9.9.
   * @attribute syn
   * @aspect LambdaParametersInference
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:497
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaParametersInference", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:497")
  public MethodDecl nonWildcardParameterization() {
    MethodDecl nonWildcardParameterization_value = this;
    return nonWildcardParameterization_value;
  }
  /** @apilevel internal */
  private void attributes_reset() {
    attributes_computed = null;
    attributes_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle attributes_computed = null;

  /** @apilevel internal */
  protected Collection<Attribute> attributes_value;

  /**
   * @attribute syn
   * @aspect Attributes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Attributes.jrag:259
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Attributes", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Attributes.jrag:259")
  public Collection<Attribute> attributes() {
    ASTNode$State state = state();
    if (attributes_computed == ASTNode$State.NON_CYCLE || attributes_computed == state().cycle()) {
      return attributes_value;
    }
    attributes_value = attributes_compute();
    if (state().inCircle()) {
      attributes_computed = state().cycle();
    
    } else {
      attributes_computed = ASTNode$State.NON_CYCLE;
    
    }
    return attributes_value;
  }
  /** @apilevel internal */
  private Collection<Attribute> attributes_compute() {
      Collection<Attribute> attributes = refined_AnnotationsCodegen_MethodDecl_attributes();
      if (needsSignatureAttribute()) {
        attributes.add(new SignatureAttribute(hostType().constantPool(), methodTypeSignature()));
      }
      return attributes;
    }
  /** @apilevel internal */
  private void descName_reset() {
    descName_computed = null;
    descName_value = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle descName_computed = null;

  /** @apilevel internal */
  protected String descName_value;

  /**
   * @attribute syn
   * @aspect ConstantPoolNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/ConstantPoolNames.jrag:117
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantPoolNames", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/ConstantPoolNames.jrag:117")
  public String descName() {
    ASTNode$State state = state();
    if (descName_computed == ASTNode$State.NON_CYCLE || descName_computed == state().cycle()) {
      return descName_value;
    }
    descName_value = descName_compute();
    if (state().inCircle()) {
      descName_computed = state().cycle();
    
    } else {
      descName_computed = ASTNode$State.NON_CYCLE;
    
    }
    return descName_value;
  }
  /** @apilevel internal */
  private String descName_compute() {
      StringBuilder b = new StringBuilder();
      b.append("(");
      for (int i=0; i<getNumParameter(); i++) {
        b.append(getParameter(i).type().typeDescriptor());
      }
      b.append(")");
      if (type().elementType().isUnknown()) {
        System.err.println(getTypeAccess().dumpTree());
        throw new Error("Error generating descName for " + signature()
            + ", did not expect unknown return type");
      }
      b.append(type().typeDescriptor());
      return b.toString();
    }
  /** @apilevel internal */
  private void bytecodes_ConstantPool_reset() {
    bytecodes_ConstantPool_computed = new java.util.HashMap(4);
    bytecodes_ConstantPool_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map bytecodes_ConstantPool_values;
  /** @apilevel internal */
  protected java.util.Map bytecodes_ConstantPool_computed;
  /**
   * @attribute syn
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:132
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CreateBCode", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:132")
  public CodeGeneration bytecodes(ConstantPool constantPool) {
    Object _parameters = constantPool;
    if (bytecodes_ConstantPool_computed == null) bytecodes_ConstantPool_computed = new java.util.HashMap(4);
    if (bytecodes_ConstantPool_values == null) bytecodes_ConstantPool_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (bytecodes_ConstantPool_values.containsKey(_parameters) && bytecodes_ConstantPool_computed != null
        && bytecodes_ConstantPool_computed.containsKey(_parameters)
        && (bytecodes_ConstantPool_computed.get(_parameters) == ASTNode$State.NON_CYCLE || bytecodes_ConstantPool_computed.get(_parameters) == state().cycle())) {
      return (CodeGeneration) bytecodes_ConstantPool_values.get(_parameters);
    }
    CodeGeneration bytecodes_ConstantPool_value = bytecodes_compute(constantPool);
    if (state().inCircle()) {
      bytecodes_ConstantPool_values.put(_parameters, bytecodes_ConstantPool_value);
      bytecodes_ConstantPool_computed.put(_parameters, state().cycle());
    
    } else {
      bytecodes_ConstantPool_values.put(_parameters, bytecodes_ConstantPool_value);
      bytecodes_ConstantPool_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return bytecodes_ConstantPool_value;
  }
  /** @apilevel internal */
  private CodeGeneration bytecodes_compute(ConstantPool constantPool) {
      BodyRootNode.add(this);
      CodeGeneration gen = new CodeGeneration(constantPool, name(), descName(), hostType().typeName(), destination_compute());
      try {
        generateBytecodes(gen);
      } catch (CodeGeneration.JumpOffsetError e) {
        // Retry with wide gotos.
        gen = new CodeGeneration(constantPool, true, name(), descName(), hostType().typeName(), destination_compute());
        generateBytecodes(gen);
      }
      return gen;
    }
  /** @apilevel internal */
  private void flags_reset() {
    flags_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle flags_computed = null;

  /** @apilevel internal */
  protected int flags_value;

  /**
   * @attribute syn
   * @aspect Flags
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Flags.jrag:60
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Flags", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Flags.jrag:60")
  public int flags() {
    ASTNode$State state = state();
    if (flags_computed == ASTNode$State.NON_CYCLE || flags_computed == state().cycle()) {
      return flags_value;
    }
    flags_value = flags_compute();
    if (state().inCircle()) {
      flags_computed = state().cycle();
    
    } else {
      flags_computed = ASTNode$State.NON_CYCLE;
    
    }
    return flags_value;
  }
  /** @apilevel internal */
  private int flags_compute() {
      int res = refined_Flags_MethodDecl_flags();
      if (isVariableArity()) {
        res |= Modifiers.ACC_VARARGS;
      }
      return res;
    }
  /**
   * @attribute syn
   * @aspect GenerateClassfile
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:461
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenerateClassfile", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:461")
  public boolean isMethodOrConstructor() {
    boolean isMethodOrConstructor_value = true;
    return isMethodOrConstructor_value;
  }
  /** @apilevel internal */
  private void offsetBeforeParameters_reset() {
    offsetBeforeParameters_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle offsetBeforeParameters_computed = null;

  /** @apilevel internal */
  protected int offsetBeforeParameters_value;

  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:69
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LocalNum", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:69")
  public int offsetBeforeParameters() {
    ASTNode$State state = state();
    if (offsetBeforeParameters_computed == ASTNode$State.NON_CYCLE || offsetBeforeParameters_computed == state().cycle()) {
      return offsetBeforeParameters_value;
    }
    offsetBeforeParameters_value = isStatic() ? 0 : 1;
    if (state().inCircle()) {
      offsetBeforeParameters_computed = state().cycle();
    
    } else {
      offsetBeforeParameters_computed = ASTNode$State.NON_CYCLE;
    
    }
    return offsetBeforeParameters_value;
  }
  /** @apilevel internal */
  private void offsetAfterParameters_reset() {
    offsetAfterParameters_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle offsetAfterParameters_computed = null;

  /** @apilevel internal */
  protected int offsetAfterParameters_value;

  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:71
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LocalNum", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:71")
  public int offsetAfterParameters() {
    ASTNode$State state = state();
    if (offsetAfterParameters_computed == ASTNode$State.NON_CYCLE || offsetAfterParameters_computed == state().cycle()) {
      return offsetAfterParameters_value;
    }
    offsetAfterParameters_value = offsetAfterParameters_compute();
    if (state().inCircle()) {
      offsetAfterParameters_computed = state().cycle();
    
    } else {
      offsetAfterParameters_computed = ASTNode$State.NON_CYCLE;
    
    }
    return offsetAfterParameters_value;
  }
  /** @apilevel internal */
  private int offsetAfterParameters_compute() {
      if (getNumParameter() == 0) {
        return offsetBeforeParameters();
      } else {
        ParameterDeclaration last = getParameter(getNumParameter()-1);
        return last.localNum() + last.type().variableSize();
      }
    }
  /** @apilevel internal */
  private void resultOffset_reset() {
    resultOffset_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle resultOffset_computed = null;

  /** @apilevel internal */
  protected int resultOffset_value;

  /**
   * @attribute syn
   * @aspect LocalNum
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:108
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LocalNum", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:108")
  public int resultOffset() {
    ASTNode$State state = state();
    if (resultOffset_computed == ASTNode$State.NON_CYCLE || resultOffset_computed == state().cycle()) {
      return resultOffset_value;
    }
    resultOffset_value = type().isVoid() ? 0 : type().variableSize();
    if (state().inCircle()) {
      resultOffset_computed = state().cycle();
    
    } else {
      resultOffset_computed = ASTNode$State.NON_CYCLE;
    
    }
    return resultOffset_value;
  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:423
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsCodegen", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:423")
  public boolean needsSignatureAttribute() {
    {
        if (type().needsSignatureAttribute()) {
          return true;
        }
        for (int i = 0; i < getNumParameter(); i++) {
          if (getParameter(i).type().needsSignatureAttribute()) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect GenericsCodegen
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:554
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="GenericsCodegen", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:554")
  public String methodTypeSignature() {
    {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        for (int i = 0; i < getNumParameter(); i++) {
          buf.append(getParameter(i).type().classTypeSignature());
        }
        buf.append(")");
        buf.append(type().classTypeSignature());
        for (int i = 0; i < getNumException(); i++) {
          buf.append("^" + getException(i).type().classTypeSignature());
        }
        return buf.toString();
      }
  }
  /**
   * @attribute inh
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:95
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ExceptionHandling", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:95")
  public boolean handlesException(TypeDecl exceptionType) {
    Object _parameters = exceptionType;
    if (handlesException_TypeDecl_computed == null) handlesException_TypeDecl_computed = new java.util.HashMap(4);
    if (handlesException_TypeDecl_values == null) handlesException_TypeDecl_values = new java.util.HashMap(4);
    ASTNode$State state = state();
    if (handlesException_TypeDecl_values.containsKey(_parameters) && handlesException_TypeDecl_computed != null
        && handlesException_TypeDecl_computed.containsKey(_parameters)
        && (handlesException_TypeDecl_computed.get(_parameters) == ASTNode$State.NON_CYCLE || handlesException_TypeDecl_computed.get(_parameters) == state().cycle())) {
      return (Boolean) handlesException_TypeDecl_values.get(_parameters);
    }
    boolean handlesException_TypeDecl_value = getParent().Define_handlesException(this, null, exceptionType);
    if (state().inCircle()) {
      handlesException_TypeDecl_values.put(_parameters, handlesException_TypeDecl_value);
      handlesException_TypeDecl_computed.put(_parameters, state().cycle());
    
    } else {
      handlesException_TypeDecl_values.put(_parameters, handlesException_TypeDecl_value);
      handlesException_TypeDecl_computed.put(_parameters, ASTNode$State.NON_CYCLE);
    
    }
    return handlesException_TypeDecl_value;
  }
  /** @apilevel internal */
  private void handlesException_TypeDecl_reset() {
    handlesException_TypeDecl_computed = new java.util.HashMap(4);
    handlesException_TypeDecl_values = null;
  }
  /** @apilevel internal */
  protected java.util.Map handlesException_TypeDecl_values;
  /** @apilevel internal */
  protected java.util.Map handlesException_TypeDecl_computed;
  /**
   * @attribute inh
   * @aspect LookupMethod
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="LookupMethod", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:36")
  public MethodDecl unknownMethod() {
    MethodDecl unknownMethod_value = getParent().Define_unknownMethod(this, null);
    return unknownMethod_value;
  }
  /**
   * @attribute inh
   * @aspect SuppressWarnings
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/SuppressWarnings.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="SuppressWarnings", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/SuppressWarnings.jrag:38")
  public boolean withinSuppressWarnings(String annot) {
    boolean withinSuppressWarnings_String_value = getParent().Define_withinSuppressWarnings(this, null, annot);
    return withinSuppressWarnings_String_value;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:550
      return v.isFinal() && v.isField() || assignedBefore(v);
    }
    else {
      return super.Define_assignedBefore(_callerNode, _childNode, v);
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
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:1162
      return !(v.isFinal() && v.isField());
    }
    else {
      return super.Define_unassignedBefore(_callerNode, _childNode, v);
    }
  }
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/TryWithResources.jrag:115
   * @apilevel internal
   */
  public boolean Define_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:201
      return throwsException(exceptionType);
    }
    else {
      return getParent().Define_handlesException(this, _callerNode, exceptionType);
    }
  }
  protected boolean canDefine_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:113
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupVariable.jrag:76
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return parameterDeclaration(name);
    }
    else if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupVariable.jrag:64
      {
          SimpleSet<Variable> result = parameterDeclaration(name);
          // A declaration of a method parameter name shadows any other variable declarations.
          if (!result.isEmpty()) {
            return result;
          }
          // Delegate to other declarations in scope.
          return lookupVariable(name);
        }
    }
    else {
      return getParent().Define_lookupVariable(this, _callerNode, name);
    }
  }
  protected boolean canDefine_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:433
   * @apilevel internal
   */
  public boolean Define_mayBePublic(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:327
      return true;
    }
    else {
      return getParent().Define_mayBePublic(this, _callerNode);
    }
  }
  protected boolean canDefine_mayBePublic(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:435
   * @apilevel internal
   */
  public boolean Define_mayBeProtected(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:328
      return true;
    }
    else {
      return getParent().Define_mayBeProtected(this, _callerNode);
    }
  }
  protected boolean canDefine_mayBeProtected(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:434
   * @apilevel internal
   */
  public boolean Define_mayBePrivate(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:329
      return true;
    }
    else {
      return getParent().Define_mayBePrivate(this, _callerNode);
    }
  }
  protected boolean canDefine_mayBePrivate(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:438
   * @apilevel internal
   */
  public boolean Define_mayBeAbstract(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:330
      return true;
    }
    else {
      return getParent().Define_mayBeAbstract(this, _callerNode);
    }
  }
  protected boolean canDefine_mayBeAbstract(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:436
   * @apilevel internal
   */
  public boolean Define_mayBeStatic(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:331
      return true;
    }
    else {
      return getParent().Define_mayBeStatic(this, _callerNode);
    }
  }
  protected boolean canDefine_mayBeStatic(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:437
   * @apilevel internal
   */
  public boolean Define_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:332
      return true;
    }
    else {
      return getParent().Define_mayBeFinal(this, _callerNode);
    }
  }
  protected boolean canDefine_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:442
   * @apilevel internal
   */
  public boolean Define_mayBeSynchronized(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:333
      return true;
    }
    else {
      return getParent().Define_mayBeSynchronized(this, _callerNode);
    }
  }
  protected boolean canDefine_mayBeSynchronized(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:443
   * @apilevel internal
   */
  public boolean Define_mayBeNative(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:334
      return true;
    }
    else {
      return getParent().Define_mayBeNative(this, _callerNode);
    }
  }
  protected boolean canDefine_mayBeNative(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:441
   * @apilevel internal
   */
  public boolean Define_mayBeStrictfp(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:335
      return true;
    }
    else {
      return getParent().Define_mayBeStrictfp(this, _callerNode);
    }
  }
  protected boolean canDefine_mayBeStrictfp(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:356
   * @apilevel internal
   */
  public ASTNode Define_enclosingBlock(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:357
      return this;
    }
    else {
      return getParent().Define_enclosingBlock(this, _callerNode);
    }
  }
  protected boolean canDefine_enclosingBlock(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getExceptionListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/SyntacticClassification.jrag:104
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.TYPE_NAME;
    }
    else if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/SyntacticClassification.jrag:103
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return NameType.TYPE_NAME;
    }
    else if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/SyntacticClassification.jrag:102
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:534
   * @apilevel internal
   */
  public TypeDecl Define_returnType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:536
      return type();
    }
    else {
      return getParent().Define_returnType(this, _callerNode);
    }
  }
  protected boolean canDefine_returnType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeHierarchyCheck.jrag:207
   * @apilevel internal
   */
  public boolean Define_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeHierarchyCheck.jrag:215
      return isStatic();
    }
    else {
      return getParent().Define_inStaticContext(this, _callerNode);
    }
  }
  protected boolean canDefine_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:49
   * @apilevel internal
   */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/UnreachableStatements.jrag:62
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/MultiCatch.jrag:44
   * @apilevel internal
   */
  public boolean Define_isMethodParameter(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/VariableDeclaration.jrag:92
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return true;
    }
    else {
      return getParent().Define_isMethodParameter(this, _callerNode);
    }
  }
  protected boolean canDefine_isMethodParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/MultiCatch.jrag:45
   * @apilevel internal
   */
  public boolean Define_isConstructorParameter(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/VariableDeclaration.jrag:93
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_isConstructorParameter(this, _callerNode);
    }
  }
  protected boolean canDefine_isConstructorParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/MultiCatch.jrag:46
   * @apilevel internal
   */
  public boolean Define_isExceptionHandlerParameter(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/VariableDeclaration.jrag:94
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_isExceptionHandlerParameter(this, _callerNode);
    }
  }
  protected boolean canDefine_isExceptionHandlerParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:131
   * @apilevel internal
   */
  public boolean Define_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:154
      return name.equals("METHOD");
    }
    else {
      return getParent().Define_mayUseAnnotationTarget(this, _callerNode, name);
    }
  }
  protected boolean canDefine_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Enums.jrag:566
   * @apilevel internal
   */
  public boolean Define_inEnumInitializer(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  protected boolean canDefine_inEnumInitializer(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:789
   * @apilevel internal
   */
  public String Define_typeVariableContext(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return hostType().typeName() + "." + signature();
  }
  protected boolean canDefine_typeVariableContext(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/VariableArityParameters.jrag:46
   * @apilevel internal
   */
  public boolean Define_variableArityValid(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/VariableArityParameters.jrag:42
      int i = _callerNode.getIndexOfChild(_childNode);
      return i == getNumParameter() - 1;
    }
    else {
      return getParent().Define_variableArityValid(this, _callerNode);
    }
  }
  protected boolean canDefine_variableArityValid(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/EffectivelyFinal.jrag:30
   * @apilevel internal
   */
  public boolean Define_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:73
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return hasBlock() && getBlock().modifiedInScope(var);
    }
    else {
      return getParent().Define_inhModifiedInScope(this, _callerNode, var);
    }
  }
  protected boolean canDefine_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:202
   * @apilevel internal
   */
  public boolean Define_isCatchParam(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:205
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_isCatchParam(this, _callerNode);
    }
  }
  protected boolean canDefine_isCatchParam(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:64
   * @apilevel internal
   */
  public int Define_localNum(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:110
      return offsetAfterParameters() + resultOffset();
    }
    else if (_callerNode == getParameterListNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:80
      int index = _callerNode.getIndexOfChild(_childNode);
      {
          if (index == 0) {
            return offsetBeforeParameters();
          } else {
            ParameterDeclaration last = getParameter(index-1);
            return last.localNum() + last.type().variableSize();
          }
        }
    }
    else {
      return getParent().Define_localNum(this, _callerNode);
    }
  }
  protected boolean canDefine_localNum(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:100
   * @apilevel internal
   */
  public int Define_resultSaveLocalNum(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getBlockOptNoTransform()) {
      // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:102
      return offsetAfterParameters();
    }
    else {
      return getParent().Define_resultSaveLocalNum(this, _callerNode);
    }
  }
  protected boolean canDefine_resultSaveLocalNum(ASTNode _callerNode, ASTNode _childNode) {
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
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:150
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:145
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:512
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/SafeVarargs.jrag:118
    if (!suppressWarnings("unchecked")
              && !hasAnnotationSafeVarargs()
              && isVariableArity()
              && !getParameter(getNumParameter()-1).type().isReifiable()) {
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
    for (Problem value : modifierProblems()) {
      collection.add(value);
    }
    for (Problem value : nameProblems()) {
      collection.add(value);
    }
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
    if (!suppressWarnings("unchecked")
              && !hasAnnotationSafeVarargs()
              && isVariableArity()
              && !getParameter(getNumParameter()-1).type().isReifiable()) {
      collection.add(warning("possible heap pollution for variable arity parameter"));
    }
  }
}
