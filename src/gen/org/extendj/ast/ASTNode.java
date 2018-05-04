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
 * @production ASTNode;

 */
public class ASTNode<T extends ASTNode> extends beaver.Symbol implements Cloneable, PrettyPrintable {
  /**
   * @aspect DumpTree
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DumpTree.jadd:38
   */
  private String DUMP_TREE_INDENT = "  ";
  /**
   * @aspect DumpTree
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DumpTree.jadd:40
   */
  public String dumpTree() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    dumpTree(new PrintStream(bytes));
    return bytes.toString();
  }
  /**
   * @aspect DumpTree
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DumpTree.jadd:46
   */
  public void dumpTree(PrintStream out) {
    dumpTree(out, "");
    out.flush();
  }
  /**
   * @aspect DumpTree
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DumpTree.jadd:51
   */
  public void dumpTree(PrintStream out, String indent) {
    out.print(indent + getClass().getSimpleName());
    out.println(getTokens());
    String childIndent = indent + DUMP_TREE_INDENT;
    for (int i = 0; i < getNumChild(); ++i) {
      ASTNode child = getChild(i);
      if (child == null)  {
        out.println(childIndent + "null");
      } else {
        child.dumpTree(out, childIndent);
      }
    }
  }
  /**
   * @aspect DumpTree
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DumpTree.jadd:65
   */
  public String getTokens() {
    StringBuilder sb = new StringBuilder();
    Method[] methods = getClass().getMethods();
    for (Method method : getClass().getMethods()) {
      ASTNodeAnnotation.Token token = method.getAnnotation(ASTNodeAnnotation.Token.class);
      if (token != null) {
        try {
          sb.append(" " + token.name() + "=\"" + method.invoke(this) + "\"");
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
      }
    }
    return sb.toString();
  }
  /**
   * @aspect DumpTree
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DumpTree.jadd:81
   */
  public String dumpTreeNoRewrite() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    dumpTreeNoRewrite(new PrintStream(bytes));
    return bytes.toString();
  }
  /**
   * @aspect DumpTree
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DumpTree.jadd:87
   */
  public void dumpTreeNoRewrite(PrintStream out) {
    dumpTreeNoRewrite(out, "");
    out.flush();
  }
  /**
   * @aspect DumpTree
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DumpTree.jadd:92
   */
  public void dumpTreeNoRewrite(PrintStream out, String indent) {
    out.print(indent + getClass().getSimpleName());
    out.println(getTokens());
    String childIndent = indent + DUMP_TREE_INDENT;
    for (int i = 0; i < getNumChildNoTransform(); ++i) {
      ASTNode child = getChildNoTransform(i);
      if (child == null)  {
        out.println(childIndent + "null");
      } else {
        child.dumpTreeNoRewrite(out, childIndent);
      }
    }
  }
  /**
   * @aspect StructuredPrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/StructuredPrettyPrint.jadd:32
   */
  public String structuredPrettyPrint() throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    // First, transform the tree by wrapping all expressions in ParExpr.
    wrapExprs();
    prettyPrint(new PrintStream(out, false, "UTF8"));
    return out.toString().trim();
  }
  /**
   * Hacky way of inserting parens around all expressions.
   * @aspect StructuredPrettyPrint
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/StructuredPrettyPrint.jadd:43
   */
  private void wrapExprs() {
    for (int i = 0; i < getNumChildNoTransform(); ++i) {
      ASTNode child = getChildNoTransform(i);
      if (child instanceof Expr
          && !(child instanceof ParExpr)
          && !(child instanceof Access)
          && !(child instanceof Literal)) {
        child.setParent(null);
        ParExpr parExpr = new ParExpr((Expr) child);
        setChild(parExpr, i);
      }
      child.wrapExprs();
    }
  }
  /**
   * @aspect AnonymousClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/AnonymousClasses.jrag:123
   */
  protected void collectExceptions(Collection<TypeDecl> exceptions, ASTNode target) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collectExceptions(exceptions, target);
    }
  }
  /**
   * @aspect BranchTarget
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:94
   */
  public void collectBranches(Collection<Stmt> c) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collectBranches(c);
    }
  }
  /**
   * @aspect DataStructures
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DataStructures.jrag:312
   */
  public static <T> SimpleSet<T> emptySet() {
    return (SimpleSet<T>) SimpleSet.EMPTY_SET;
  }
  /**
   * @aspect DefiniteAssignment
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:574
   */
  protected boolean checkDUeverywhere(Variable v) {
    for (int i = 0; i < getNumChild(); i++) {
      if (!getChild(i).checkDUeverywhere(v)) {
        return false;
      }
    }
    return true;
  }
  /**
   * @aspect DefiniteAssignment
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:723
   */
  protected boolean isDescendantTo(ASTNode node) {
    if (this == node) {
      return true;
    }
    if (getParent() == null) {
      return false;
    }
    return getParent().isDescendantTo(node);
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:34
   */
  protected String sourceFile() {
    ASTNode node = this;
    while (node != null && !(node instanceof CompilationUnit)) {
      node = node.getParent();
    }
    if (node == null) {
      return "Unknown file";
    }
    CompilationUnit u = (CompilationUnit) node;
    return u.relativeName();
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:59
   */
  public ASTNode setLocation(ASTNode node) {
    setStart(node.getStart());
    setEnd(node.getEnd());
    return this;
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:65
   */
  public ASTNode setStart(int i) {
    start = i;
    return this;
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:70
   */
  public int start() {
    return start;
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:74
   */
  public ASTNode setEnd(int i) {
    end = i;
    return this;
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:79
   */
  public int end() {
    return end;
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:83
   */
  public String location() {
    return "" + lineNumber();
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:87
   */
  public String errorPrefix() {
    return sourceFile() + ":" + location() + ":\n" + "  *** Semantic Error: ";
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:91
   */
  public String warningPrefix() {
    return sourceFile() + ":" + location() + ":\n" + "  *** WARNING: ";
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:240
   */
  public Problem errorf(String messagefmt, Object... args) {
    return error(String.format(messagefmt, args));
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:244
   */
  public Problem error(String message) {
    ASTNode node = this;
    while (node != null && !(node instanceof CompilationUnit)) {
      node = node.getParent();
    }
    CompilationUnit cu = (CompilationUnit) node;
    if (getNumChild() == 0 && getStart() != 0 && getEnd() != 0) {
      int line = getLine(getStart());
      int column = getColumn(getStart());
      int endLine = getLine(getEnd());
      int endColumn = getColumn(getEnd());
      return new Problem(sourceFile(), message, line, column, endLine, endColumn,
          Problem.Severity.ERROR, Problem.Kind.SEMANTIC);
    } else {
      return new Problem(sourceFile(), message, lineNumber(), Problem.Severity.ERROR,
          Problem.Kind.SEMANTIC);
    }
  }
  /**
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:263
   */
  public Problem warning(String message) {
    ASTNode node = this;
    while (node != null && !(node instanceof CompilationUnit)) {
      node = node.getParent();
    }
    CompilationUnit cu = (CompilationUnit) node;
    return new Problem(sourceFile(), message, lineNumber(),
        Problem.Severity.WARNING, Problem.Kind.SEMANTIC);
  }
  /**
   * @aspect ExceptionHandling
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:292
   */
  protected boolean reachedException(TypeDecl type) {
    for (int i = 0; i < getNumChild(); i++) {
      if (getChild(i).reachedException(type)) {
        return true;
      }
    }
    return false;
  }
  /**
   * Returns a filtered collection of methods, keeping only the static methods
   * from the input collection.
   * @aspect LookupMethod
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:107
   */
  public static Collection<MethodDecl> keepStaticMethods(
      Collection<MethodDecl> methods) {
    Collection<MethodDecl> result = new LinkedList<MethodDecl>();
    for (MethodDecl method : methods) {
      if (method.isStatic()) {
        result.add(method);
      }
    }
    return result;
  }
  /**
   * Utility method to add a single item in a SimpleSet based signature map.
   * @aspect MemberMethods
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:566
   */
  protected static <E> void putSimpleSetElement(Map<String, SimpleSet<E>> map,
      String key, E value) {
    SimpleSet<E> result = map.get(key);
    if (result == null) {
      result = emptySet();
    }
    map.put(key, result.add(value));
  }
  /**
   * @aspect VariableScope
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupVariable.jrag:299
   */
  public SimpleSet<Variable> removeInstanceVariables(SimpleSet<Variable> vars) {
    SimpleSet<Variable> newSet = emptySet();
    for (Variable v : vars) {
      if (!v.isInstanceVariable()) {
        newSet = newSet.add(v);
      }
    }
    return newSet;
  }
  /**
   * @aspect NameCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:33
   */
  public TypeDecl extractSingleType(SimpleSet<TypeDecl> types) {
    return types.isSingleton() ? types.singletonValue() : null;
  }
  /** @return a copy of the block as an NTAFinallyBlock. 
   * @aspect NTAFinally
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NTAFinally.jrag:33
   */
  protected static NTAFinallyBlock ntaFinallyBlock(FinallyHost origin,
      Stmt branch, Block block) {
    NTAFinallyBlock ntaBlock = new NTAFinallyBlock(origin);
    ntaBlock.addStmt((Block) block.treeCopyNoTransform());
    if (block.canCompleteNormally()) {
      FinallyHost enclosing = block.enclosingFinally(branch);
      if (enclosing != null) {
        ntaBlock.addStmt(ntaFinallyBlock(enclosing, branch, enclosing.getFinallyBlock()));
      }
    }
    return ntaBlock;
  }
  /**
   * Pretty-print this ASTNode.
   * @return pretty-printed representation of this AST node
   * @aspect PrettyPrintUtil
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrintUtil.jrag:41
   */
  public String prettyPrint() {
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    prettyPrint(new PrettyPrinter("  ", new PrintStream(buf)));
    return buf.toString();
  }
  /**
   * Pretty print this AST node to the target PrintStream.
   * @param out target for pretty printing
   * @aspect PrettyPrintUtil
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrintUtil.jrag:51
   */
  public void prettyPrint(PrintStream out) {
    prettyPrint(new PrettyPrinter("  ", out));
  }
  /** @return the name of the class implementing this AST node. 
   * @aspect PrettyPrintUtil
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrintUtil.jrag:56
   */
  public String toString() {
    return getClass().getName();
  }
  /**
   * @aspect PrettyPrintUtil
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrettyPrintUtil.jrag:60
   */
  public void prettyPrint(PrettyPrinter out) {
  }
  /**
   * @aspect PrimitiveTypes
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PrimitiveTypes.jrag:35
   */
  protected static final String PRIMITIVE_PACKAGE_NAME = "@primitive";
  /**
   * Helper method to throw an error when prevExpr is evaluated somewhere where
   * the attribute can not be evaluated.
   * @aspect QualifiedNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:130
   */
  protected Expr prevExprError() {
    throw new Error("prevExpr can not be evaluated outside of the right side of a Dot access.");
  }
  /**
   * Helper method to throw an error when nextAccess is evaluated somewhere
   * where the attribute can not be evaluated.
   * @aspect QualifiedNames
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ResolveAmbiguousNames.jrag:152
   */
  protected Access nextAccessError() {
    throw new Error("nextAccess can not be evaluated outside of the left side of a Dot access.");
  }
  /**
   * @aspect VariableDeclarationTransformation
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/VariableDeclaration.jrag:121
   */
  public void clearLocations() {
    setStart(0);
    setEnd(0);
    for (int i = 0; i < getNumChildNoTransform(); i++) {
      getChildNoTransform(i).clearLocations();
    }
  }
  /**
   * Create a copy of the access list where each access has been erased.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1490
   */
  protected List<Access> erasedAccessList(List<Access> list) {
    List<Access> result = new List<Access>();
    for (Access access : list) {
      result.add(access.erasedCopy());
    }
    return result;
  }
  /**
   * Create a copy of the parameter list where each parameter has been erased.
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1501
   */
  protected List<ParameterDeclaration> erasedParameterList(List<ParameterDeclaration> list) {
    List<ParameterDeclaration> result = new List<ParameterDeclaration>();
    for (ParameterDeclaration param : list) {
      result.add(param.erasedCopy());
    }
    return result;
  }
  /**
   * @aspect UncheckedConversion
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/UncheckedConversion.jrag:61
   */
  Collection<Problem> uncheckedConversionWarnings(TypeDecl source, TypeDecl dest) {
    if (source.isUncheckedConversionTo(dest)) {
      return Collections.singletonList(
          warning("unchecked conversion from raw type " + source.typeName()
              + " to generic type " + dest.typeName()));
    } else {
      return Collections.emptyList();
    }
  }
  /**
   * @aspect Converter
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/ASTToJSON.jrag:35
   */
  public String toJSON(int depth){
		String finalString = "{\"id\": "+Counter.getId(this)+", \"type\": \"";
		finalString+=getClass()+"\", \"position\": {\"start_line\": ";
		finalString+=startLine()+", \"start_column\": "+startColumn()+ ", \"end_line\": ";
		finalString+=endLine()+", \"end_column\": "+endColumn()+"}, \"children\": [ ";


		if(children != null) {
			for(int i = 0; i < numChildren; i++){
				ASTNode currentChild = children[i];

				String temp = currentChild.toJSON(++depth);

				if(i == numChildren-1){
					finalString += temp;
				} else {
					finalString += temp + ", ";
				}
			}
		}

		finalString+="] }";
		return finalString;
	}
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:34
   */
  protected int bcStartIndex = -1;
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:36
   */
  protected int bcEndIndex = -1;
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:38
   */
  public int getBcStartIndex(){
  	return this.bcStartIndex;
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:42
   */
  public int getBcEndIndex(){
  	return this.bcEndIndex;
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:47
   */
  public void setSourceLineNumber(int i) {
    setStart(ASTNode.makePosition(i, 1));
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:71
   */
  protected int findFirstSourceLineNumber() {
    if (getStart() != 0) {
      return getLine(getStart());
    }
    for (int i = 0; i < getNumChild(); i++) {
      int num = getChild(i).findFirstSourceLineNumber();
      if (num != -1) {
        return num;
      }
    }
    return -1;
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:246
   */
  public void error() {
    Throwable t = new Throwable();
    StackTraceElement[] ste = new Throwable().getStackTrace();
    String s = ste[1].toString();
    throw new Error(s+" Cannot create bytecode for:"+getClass().getName());
  }
  /** Generate bytecode to load this field. 
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:406
   */
  protected static void emitLoadField(ASTNode<ASTNode> node, CodeGeneration gen, Variable field,
      TypeDecl hostType) {
    if (field.hostType().isArrayDecl() && field.name().equals("length")) {
      gen.emit(node, Bytecode.ARRAYLENGTH);
      return;
    }
    String classname = hostType.constantPoolName();
    String desc = field.type().typeDescriptor();
    String name = field.name();
    int index = gen.constantPool().addFieldref(classname, name, desc);
    if (field.isStatic()) {
      gen.emit(node, Bytecode.GETSTATIC, field.type().variableSize()).add2(index);
    } else {
      gen.emit(node, Bytecode.GETFIELD, field.type().variableSize() - 1).add2(index);
    }
  }
  /** Generate bytecode to store a field in the given hostType. 
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:480
   */
  protected void emitStoreField(ASTNode<ASTNode> node, CodeGeneration gen, Variable var, TypeDecl hostType) {
    String classname = hostType.constantPoolName();
    String desc = var.type().typeDescriptor();
    String name = var.name();
    int index = gen.constantPool().addFieldref(classname, name, desc);
    if (var.isStatic()) {
      gen.emit(node, Bytecode.PUTSTATIC, -var.type().variableSize()).add2(index);
    } else {
      gen.emit(node, Bytecode.PUTFIELD, -var.type().variableSize() - 1).add2(index);
    }
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CreateBCode.jrag:290
   */
  public void createBCode(CodeGeneration gen) {
    this.bcStartIndex = gen.pos();

    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).createBCode(gen);
    }

    this.bcEndIndex = gen.pos();
  }
  /**
   * @aspect InnerClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:204
   */
  public void collectEnclosingVariables(Collection<Variable> vars, TypeDecl typeDecl) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collectEnclosingVariables(vars, typeDecl);
    }
  }
  /**
   * @aspect InnerClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:386
   */
  protected static Access createAccess(Variable var) {
    if (var instanceof FieldDeclarator) {
      return ((FieldDeclarator) var).createAccess();
    } else {
      throw new Error("Trying to create accessor for non-field.");
    }
  }
  /**
   * Imperative transformation of the AST.
   * This should be removed.
   * 
   * <p>Usage: this.replaceWith(replacement)
   * 
   * @param replacement node to replace this node with
   * @return the new node
   * @deprecated
   * @aspect Transformations
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Transformations.jrag:118
   */
  @Deprecated
  protected void replaceWith(ASTNode replacement) {
	  int replacePos = getParent().getIndexOfChild(this);
	  getParent().setChild(replacement, replacePos);
  }
  /**
   * Finds one method with the given name, and no parameters, in the specified
   * type.
   * @param type type to search for the method declaration
   * @param name name of the method
   * @return method declaration
   * @aspect EnhancedForToBytecode
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/EnhancedForCodegen.jrag:113
   */
  protected static MethodDecl getMethod(TypeDecl type, String name) {
    Collection<MethodDecl> methods = (Collection<MethodDecl>) type.memberMethods(name);
    for (MethodDecl method : methods) {
      if (method.getNumParameter() == 0) {
        return method;
      }
    }
    throw new Error("Could not find " + type.typeName() +
        "." + name + "()");
  }
  /**
   * @declaredat ASTNode:1
   */
  public ASTNode() {
    super();
    init$Children();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:11
   */
  public void init$Children() {
  }
  /**
   * Cached child index. Child indices are assumed to never change (AST should
   * not change after construction).
   * @apilevel internal
   * @declaredat ASTNode:18
   */
  private int childIndex = -1;
  /** @apilevel low-level 
   * @declaredat ASTNode:21
   */
  public int getIndexOfChild(ASTNode node) {
    if (node == null) {
      return -1;
    }
    if (node.childIndex >= 0) {
      return node.childIndex;
    }
    for (int i = 0; children != null && i < children.length; i++) {
      if (getChild(i) == node) {
        node.childIndex = i;
        return i;
      }
    }
    return -1;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public static final boolean generatedWithCacheCycle = false;
  /** @apilevel internal 
   * @declaredat ASTNode:41
   */
  public static final boolean generatedWithComponentCheck = false;
  /** @apilevel low-level 
   * @declaredat ASTNode:44
   */
  protected ASTNode parent;
  /** @apilevel low-level 
   * @declaredat ASTNode:47
   */
  protected ASTNode[] children;
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  private static ASTNode$State state = new ASTNode$State();
  /** @apilevel internal 
   * @declaredat ASTNode:54
   */
  public final ASTNode$State state() {
    return state;
  }
  /**
   * @return an iterator that can be used to iterate over the children of this node.
   * The iterator does not allow removing children.
   * @declaredat ASTNode:63
   */
  public java.util.Iterator<T> astChildIterator() {
    return new java.util.Iterator<T>() {
      private int index = 0;

      @Override
      public boolean hasNext() {
        return index < getNumChild();
      }

      @Override
      public T next() {
        return hasNext() ? (T) getChild(index++) : null;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
  /** @return an object that can be used to iterate over the children of this node 
   * @declaredat ASTNode:85
   */
  public Iterable<T> astChildren() {
    return new Iterable<T>() {
      @Override
      public java.util.Iterator<T> iterator() {
        return astChildIterator();
      }
    };
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:95
   */
  public T getChild(int i) {
    ASTNode node = this.getChildNoTransform(i);
    if (node != null && node.mayHaveRewrite()) {
      ASTNode rewritten = node.rewrittenNode();
      if (rewritten != node) {
        rewritten.setParent(this);
        node = rewritten;
      }
    }
    return (T) node;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:107
   */
  public void addChild(T node) {
    setChild(node, getNumChildNoTransform());
  }
  /**
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @apilevel low-level
   * @declaredat ASTNode:114
   */
  public final T getChildNoTransform(int i) {
    if (children == null) {
      return null;
    }
    T child = (T)children[i];
    return child;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:122
   */
  protected int numChildren;
  /** @apilevel low-level 
   * @declaredat ASTNode:125
   */
  protected int numChildren() {
    return numChildren;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:130
   */
  public int getNumChild() {
    return numChildren();
  }
  /**
   * Behaves like getNumChild, but does not invoke AST transformations (rewrites).
   * @apilevel low-level
   * @declaredat ASTNode:138
   */
  public final int getNumChildNoTransform() {
    return numChildren();
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:142
   */
  public void setChild(ASTNode node, int i) {
    if (children == null) {
      children = new ASTNode[(i + 1 > 4 || !(this instanceof List)) ? i + 1 : 4];
    } else if (i >= children.length) {
      ASTNode c[] = new ASTNode[i << 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = node;
    if (i >= numChildren) {
      numChildren = i+1;
    }
    if (node != null) {
      node.setParent(this);
      node.childIndex = i;
    }
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:160
   */
  public void insertChild(ASTNode node, int i) {
    if (children == null) {
      children = new ASTNode[(i + 1 > 4 || !(this instanceof List)) ? i + 1 : 4];
      children[i] = node;
    } else {
      ASTNode c[] = new ASTNode[children.length + 1];
      System.arraycopy(children, 0, c, 0, i);
      c[i] = node;
      if (i < children.length) {
        System.arraycopy(children, i, c, i+1, children.length-i);
        for(int j = i+1; j < c.length; ++j) {
          if (c[j] != null) {
            c[j].childIndex = j;
          }
        }
      }
      children = c;
    }
    numChildren++;
    if (node != null) {
      node.setParent(this);
      node.childIndex = i;
    }
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:185
   */
  public void removeChild(int i) {
    if (children != null) {
      ASTNode child = (ASTNode) children[i];
      if (child != null) {
        child.parent = null;
        child.childIndex = -1;
      }
      // Adding a check of this instance to make sure its a List, a move of children doesn't make
      // any sense for a node unless its a list. Also, there is a problem if a child of a non-List node is removed
      // and siblings are moved one step to the right, with null at the end.
      if (this instanceof List || this instanceof Opt) {
        System.arraycopy(children, i+1, children, i, children.length-i-1);
        children[children.length-1] = null;
        numChildren--;
        // fix child indices
        for(int j = i; j < numChildren; ++j) {
          if (children[j] != null) {
            child = (ASTNode) children[j];
            child.childIndex = j;
          }
        }
      } else {
        children[i] = null;
      }
    }
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:212
   */
  public ASTNode getParent() {
    return (ASTNode) parent;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:216
   */
  public void setParent(ASTNode node) {
    parent = node;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:274
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:278
   */
  public void flushTreeCache() {
    flushCache();
    if (children != null) {
      for (int i = 0; i < children.length; i++) {
        if (children[i] != null) {
          ((ASTNode) children[i]).flushTreeCache();
        }
      }
    }
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:289
   */
  public void flushCache() {
    flushAttrAndCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:293
   */
  public void flushAttrAndCollectionCache() {
    flushAttrCache();
    flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:298
   */
  public void flushAttrCache() {
  }
  /** @apilevel internal 
   * @declaredat ASTNode:301
   */
  public void flushCollectionCache() {
  }
  /** @apilevel internal 
   * @declaredat ASTNode:304
   */
  public ASTNode<T> clone() throws CloneNotSupportedException {
    ASTNode node = (ASTNode) super.clone();
    node.flushAttrAndCollectionCache();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:310
   */
  public ASTNode<T> copy() {
    try {
      ASTNode node = (ASTNode) clone();
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
   * @declaredat ASTNode:329
   */
  @Deprecated
  public ASTNode<T> fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:339
   */
  public ASTNode<T> treeCopyNoTransform() {
    ASTNode tree = (ASTNode) copy();
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
   * @declaredat ASTNode:359
   */
  public ASTNode<T> treeCopy() {
    ASTNode tree = (ASTNode) copy();
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
  /**
   * Performs a full traversal of the tree using getChild to trigger rewrites
   * @apilevel low-level
   * @declaredat ASTNode:376
   */
  public void doFullTraversal() {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).doFullTraversal();
    }
  }
  /** @apilevel internal 
   * @declaredat ASTNode:382
   */
  protected boolean is$Equal(ASTNode n1, ASTNode n2) {
    if (n1 == null && n2 == null) return true;
    if (n1 == null || n2 == null) return false;
    return n1.is$Equal(n2);
  }
  /** @apilevel internal 
   * @declaredat ASTNode:388
   */
  protected boolean is$Equal(ASTNode node) {
    if (getClass() != node.getClass()) {
      return false;
    }
    if (numChildren != node.numChildren) {
      return false;
    }
    for (int i = 0; i < numChildren; i++) {
      if (children[i] == null && node.children[i] != null) {
        return false;
      }
      if (!((ASTNode)children[i]).is$Equal(((ASTNode)node.children[i]))) {
        return false;
      }
    }
    return true;
  }
  /**
   * @aspect <NoAspect>
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:278
   */
    protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_CompilationUnit_problems(_root, _map);
    }
  }
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/GenerateClassfile.jrag:354
   */
    protected void collect_contributors_TypeDecl_accessors(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_TypeDecl_accessors(_root, _map);
    }
  }
  protected void contributeTo_TypeDecl_accessors(HashSet<BodyDecl> collection) {
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:155
   */
    protected void collect_contributors_TypeDecl_nestedTypes(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_TypeDecl_nestedTypes(_root, _map);
    }
  }
  protected void contributeTo_TypeDecl_nestedTypes(LinkedList<TypeDecl> collection) {
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/EnumsCodegen.jrag:115
   */
    protected void collect_contributors_TypeDecl_enumSwitchStatements(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_TypeDecl_enumSwitchStatements(_root, _map);
    }
  }
  protected void contributeTo_TypeDecl_enumSwitchStatements(LinkedList<SwitchStmt> collection) {
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/GenericsCodegen.jrag:261
   */
    protected void collect_contributors_TypeDecl_bridgeMethods(TypeDecl _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_TypeDecl_bridgeMethods(_root, _map);
    }
  }
  protected void contributeTo_TypeDecl_bridgeMethods(HashSet<MethodDecl> collection) {
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaBody.jrag:47
   */
    protected void collect_contributors_BlockLambdaBody_lambdaReturns(Program _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_BlockLambdaBody_lambdaReturns(_root, _map);
    }
  }
  protected void contributeTo_BlockLambdaBody_lambdaReturns(ArrayList<ReturnStmt> collection) {
  }

  /**
   * @attribute syn
   * @aspect ErrorCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:46
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ErrorCheck", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ErrorCheck.jrag:46")
  public int lineNumber() {
    {
        ASTNode n = this;
        while (n.getParent() != null && n.getStart() == 0) {
          n = n.getParent();
        }
        return getLine(n.getStart());
      }
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1170
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1170")
  public boolean usesTypeVariable() {
    {
        for (int i = 0; i < getNumChild(); i++) {
          if (getChild(i).usesTypeVariable()) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:51
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CodeGeneration", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:51")
  public int sourceLineNumber() {
    int sourceLineNumber_value = getStart() != 0 ? getLine(getStart()) : -1;
    return sourceLineNumber_value;
  }
  /**
   * @attribute syn
   * @aspect InnerClasses
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:128
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="InnerClasses", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:128")
  public boolean isStringAdd() {
    boolean isStringAdd_value = false;
    return isStringAdd_value;
  }
  /**
   * @attribute syn
   * @aspect Java2Rewrites
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Java2Rewrites.jrag:97
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java2Rewrites", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Java2Rewrites.jrag:97")
  public boolean hasAssertStatementRecursive() {
    {
        for (ASTNode child : astChildren()) {
          if (child.hasAssertStatementRecursive()) {
            return true;
          }
        }
        return false;
      }
  }
  /**
   * @attribute syn
   * @aspect Position
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Position.jrag:2
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Position", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Position.jrag:2")
  public int startLine() {
    {
    		return getLine(getStart());
    	}
  }
  /**
   * @attribute syn
   * @aspect Position
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Position.jrag:6
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Position", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Position.jrag:6")
  public int startColumn() {
    {
    		return getColumn(getStart());
    	}
  }
  /**
   * @attribute syn
   * @aspect Position
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Position.jrag:10
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Position", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Position.jrag:10")
  public int endLine() {
    {
    		return getLine(getEnd());
    	}
  }
  /**
   * @attribute syn
   * @aspect Position
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Position.jrag:14
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Position", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Position.jrag:14")
  public int endColumn() {
    {
    		return getColumn(getEnd());
    	}
  }
  /**
   * @attribute syn
   * @aspect Position
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Position.jrag:18
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Position", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Position.jrag:18")
  public String getPos() {
    {
    		// returns " - [ L1C3 - L2C4 ]" string, for example
    
    		return startLine()+","+startColumn()+"-"+
    			+endLine()+","+endColumn();
    
    	}
  }
  /**
   * @attribute inh
   * @aspect AddOptionsToProgram
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Options.jadd:40
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="AddOptionsToProgram", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Options.jadd:40")
  public Program program() {
    Program program_value = getParent().Define_program(this, null);
    return program_value;
  }
  /** @return the enclosing compilation unit. 
   * @attribute inh
   * @aspect ClassPath
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ClassPath.jrag:105
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="ClassPath", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ClassPath.jrag:105")
  public CompilationUnit compilationUnit() {
    CompilationUnit compilationUnit_value = getParent().Define_compilationUnit(this, null);
    return compilationUnit_value;
  }
  /** @return {@code true} if the field declaration is before this node. 
   * @attribute inh
   * @aspect DeclareBeforeUse
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DeclareBeforeUse.jrag:58
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="DeclareBeforeUse", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DeclareBeforeUse.jrag:58")
  public boolean declaredBefore(Variable decl) {
    boolean declaredBefore_Variable_value = getParent().Define_declaredBefore(this, null, decl);
    return declaredBefore_Variable_value;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return this;
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }
  /** @apilevel internal */
  public Program Define_program(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_program(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_program(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Options.jadd:42
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute program
   */
  protected boolean canDefine_program(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_superType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_superType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_superType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LookupType.jrag:92
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute superType
   */
  protected boolean canDefine_superType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public ConstructorDecl Define_constructorDecl(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_constructorDecl(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_constructorDecl(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/MethodSignature.jrag:118
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute constructorDecl
   */
  protected boolean canDefine_constructorDecl(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_componentType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_componentType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_componentType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Arrays.jrag:54
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute componentType
   */
  protected boolean canDefine_componentType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public LabeledStmt Define_lookupLabel(ASTNode _callerNode, ASTNode _childNode, String name) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupLabel(self, _callerNode, name)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupLabel(self, _callerNode, name);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/BranchTarget.jrag:260
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupLabel
   */
  protected boolean canDefine_lookupLabel(ASTNode _callerNode, ASTNode _childNode, String name) {
    return false;
  }
  /** @apilevel internal */
  public CompilationUnit Define_compilationUnit(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_compilationUnit(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_compilationUnit(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ClassPath.jrag:107
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute compilationUnit
   */
  protected boolean canDefine_compilationUnit(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public int Define_blockIndex(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_blockIndex(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_blockIndex(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DeclareBeforeUse.jrag:40
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute blockIndex
   */
  protected boolean canDefine_blockIndex(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_declaredBefore(ASTNode _callerNode, ASTNode _childNode, Variable decl) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_declaredBefore(self, _callerNode, decl)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_declaredBefore(self, _callerNode, decl);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DeclareBeforeUse.jrag:62
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declaredBefore
   */
  protected boolean canDefine_declaredBefore(ASTNode _callerNode, ASTNode _childNode, Variable decl) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isDest(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isDest(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isDest(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:62
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isDest
   */
  protected boolean canDefine_isDest(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isSource(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isSource(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isSource(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:61
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isSource
   */
  protected boolean canDefine_isSource(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isIncOrDec(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isIncOrDec(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isIncOrDec(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:69
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isIncOrDec
   */
  protected boolean canDefine_isIncOrDec(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_assignedBefore(self, _callerNode, v)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_assignedBefore(self, _callerNode, v);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/TryWithResources.jrag:227
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignedBefore
   */
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_unassignedBefore(self, _callerNode, v)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_unassignedBefore(self, _callerNode, v);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/EnhancedFor.jrag:212
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unassignedBefore
   */
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeException(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeException(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeException(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:48
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeException
   */
  protected boolean canDefine_typeException(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeRuntimeException(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeRuntimeException(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeRuntimeException(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:51
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeRuntimeException
   */
  protected boolean canDefine_typeRuntimeException(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeError(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeError(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeError(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:54
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeError
   */
  protected boolean canDefine_typeError(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeNullPointerException(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeNullPointerException(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeNullPointerException(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:57
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeNullPointerException
   */
  protected boolean canDefine_typeNullPointerException(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeThrowable(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeThrowable(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeThrowable(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ExceptionHandling.jrag:61
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeThrowable
   */
  protected boolean canDefine_typeThrowable(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_handlesException(self, _callerNode, exceptionType)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_handlesException(self, _callerNode, exceptionType);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/LambdaExpr.jrag:134
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute handlesException
   */
  protected boolean canDefine_handlesException(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    return false;
  }
  /** @apilevel internal */
  public Collection<ConstructorDecl> Define_lookupConstructor(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupConstructor(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupConstructor(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupConstructor.jrag:39
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupConstructor
   */
  protected boolean canDefine_lookupConstructor(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public Collection<ConstructorDecl> Define_lookupSuperConstructor(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupSuperConstructor(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupSuperConstructor(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupConstructor.jrag:45
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupSuperConstructor
   */
  protected boolean canDefine_lookupSuperConstructor(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public Expr Define_nestedScope(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_nestedScope(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_nestedScope(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:44
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute nestedScope
   */
  protected boolean canDefine_nestedScope(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public Collection<MethodDecl> Define_lookupMethod(ASTNode _callerNode, ASTNode _childNode, String name) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupMethod(self, _callerNode, name)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupMethod(self, _callerNode, name);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupMethod.jrag:93
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupMethod
   */
  protected boolean canDefine_lookupMethod(ASTNode _callerNode, ASTNode _childNode, String name) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeObject(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeObject(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeObject(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:41
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeObject
   */
  protected boolean canDefine_typeObject(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeCloneable(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeCloneable(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeCloneable(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:42
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeCloneable
   */
  protected boolean canDefine_typeCloneable(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeSerializable(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeSerializable(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeSerializable(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:43
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeSerializable
   */
  protected boolean canDefine_typeSerializable(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeBoolean(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeBoolean(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeBoolean(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:55
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeBoolean
   */
  protected boolean canDefine_typeBoolean(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeByte(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeByte(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeByte(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:56
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeByte
   */
  protected boolean canDefine_typeByte(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeShort(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeShort(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeShort(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:57
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeShort
   */
  protected boolean canDefine_typeShort(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeChar(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeChar(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeChar(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:58
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeChar
   */
  protected boolean canDefine_typeChar(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeInt(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeInt(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeInt(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:59
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeInt
   */
  protected boolean canDefine_typeInt(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeLong(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeLong(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeLong(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:60
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeLong
   */
  protected boolean canDefine_typeLong(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeFloat(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeFloat(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeFloat(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:61
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeFloat
   */
  protected boolean canDefine_typeFloat(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeDouble(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeDouble(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeDouble(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:62
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeDouble
   */
  protected boolean canDefine_typeDouble(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeString(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeString(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeString(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:63
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeString
   */
  protected boolean canDefine_typeString(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeVoid(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeVoid(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeVoid(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:66
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVoid
   */
  protected boolean canDefine_typeVoid(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeNull(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeNull(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeNull(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:69
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeNull
   */
  protected boolean canDefine_typeNull(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_unknownType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_unknownType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_unknownType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:34
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownType
   */
  protected boolean canDefine_unknownType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_hasPackage(ASTNode _callerNode, ASTNode _childNode, String packageName) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_hasPackage(self, _callerNode, packageName)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_hasPackage(self, _callerNode, packageName);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:124
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute hasPackage
   */
  protected boolean canDefine_hasPackage(ASTNode _callerNode, ASTNode _childNode, String packageName) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String packageName, String typeName) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupType(self, _callerNode, packageName, typeName)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupType(self, _callerNode, packageName, typeName);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/LookupType.jrag:138
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupType
   */
  protected boolean canDefine_lookupType(ASTNode _callerNode, ASTNode _childNode, String packageName, String typeName) {
    return false;
  }
  /** @apilevel internal */
  public SimpleSet<TypeDecl> Define_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupType(self, _callerNode, name)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupType(self, _callerNode, name);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:335
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupType
   */
  protected boolean canDefine_lookupType(ASTNode _callerNode, ASTNode _childNode, String name) {
    return false;
  }
  /** @apilevel internal */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupVariable(self, _callerNode, name)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupVariable(self, _callerNode, name);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/MultiCatch.jrag:98
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupVariable
   */
  protected boolean canDefine_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBePublic(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBePublic(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBePublic(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:327
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBePublic
   */
  protected boolean canDefine_mayBePublic(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBeProtected(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBeProtected(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBeProtected(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:328
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeProtected
   */
  protected boolean canDefine_mayBeProtected(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBePrivate(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBePrivate(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBePrivate(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:329
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBePrivate
   */
  protected boolean canDefine_mayBePrivate(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBeStatic(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBeStatic(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBeStatic(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Enums.jrag:67
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeStatic
   */
  protected boolean canDefine_mayBeStatic(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBeFinal(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBeFinal(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/TryWithResources.jrag:314
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeFinal
   */
  protected boolean canDefine_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBeAbstract(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBeAbstract(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBeAbstract(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Enums.jrag:60
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeAbstract
   */
  protected boolean canDefine_mayBeAbstract(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBeVolatile(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBeVolatile(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBeVolatile(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:324
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeVolatile
   */
  protected boolean canDefine_mayBeVolatile(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBeTransient(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBeTransient(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBeTransient(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:320
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeTransient
   */
  protected boolean canDefine_mayBeTransient(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBeStrictfp(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBeStrictfp(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBeStrictfp(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:335
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeStrictfp
   */
  protected boolean canDefine_mayBeStrictfp(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBeSynchronized(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBeSynchronized(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBeSynchronized(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:333
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeSynchronized
   */
  protected boolean canDefine_mayBeSynchronized(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayBeNative(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayBeNative(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayBeNative(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/Modifiers.jrag:334
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeNative
   */
  protected boolean canDefine_mayBeNative(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public ASTNode Define_enclosingBlock(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_enclosingBlock(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_enclosingBlock(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:357
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingBlock
   */
  protected boolean canDefine_enclosingBlock(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public VariableScope Define_outerScope(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_outerScope(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_outerScope(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/NameCheck.jrag:36
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute outerScope
   */
  protected boolean canDefine_outerScope(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_insideLoop(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_insideLoop(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_insideLoop(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/EnhancedFor.jrag:214
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute insideLoop
   */
  protected boolean canDefine_insideLoop(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_insideSwitch(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_insideSwitch(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_insideSwitch(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:515
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute insideSwitch
   */
  protected boolean canDefine_insideSwitch(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public Case Define_bind(ASTNode _callerNode, ASTNode _childNode, Case c) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_bind(self, _callerNode, c)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_bind(self, _callerNode, c);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:571
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute bind
   */
  protected boolean canDefine_bind(ASTNode _callerNode, ASTNode _childNode, Case c) {
    return false;
  }
  /** @apilevel internal */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_nameType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_nameType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/MethodReference.jrag:196
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute nameType
   */
  protected boolean canDefine_nameType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isAnonymous(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isAnonymous(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isAnonymous(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:234
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isAnonymous
   */
  protected boolean canDefine_isAnonymous(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public Variable Define_unknownField(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_unknownField(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_unknownField(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:249
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownField
   */
  protected boolean canDefine_unknownField(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public MethodDecl Define_unknownMethod(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_unknownMethod(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_unknownMethod(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:255
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownMethod
   */
  protected boolean canDefine_unknownMethod(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public ConstructorDecl Define_unknownConstructor(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_unknownConstructor(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_unknownConstructor(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:262
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownConstructor
   */
  protected boolean canDefine_unknownConstructor(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_declType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_declType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_declType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:717
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declType
   */
  protected boolean canDefine_declType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public BodyDecl Define_enclosingBodyDecl(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_enclosingBodyDecl(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_enclosingBodyDecl(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:577
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingBodyDecl
   */
  protected boolean canDefine_enclosingBodyDecl(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isMemberType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isMemberType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isMemberType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:590
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isMemberType
   */
  protected boolean canDefine_isMemberType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_hostType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_hostType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_hostType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:651
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute hostType
   */
  protected boolean canDefine_hostType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_switchType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_switchType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_switchType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:483
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute switchType
   */
  protected boolean canDefine_switchType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_returnType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_returnType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_returnType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:39
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute returnType
   */
  protected boolean canDefine_returnType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_enclosingInstance(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_enclosingInstance(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_enclosingInstance(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:688
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingInstance
   */
  protected boolean canDefine_enclosingInstance(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public String Define_methodHost(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_methodHost(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_methodHost(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:773
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute methodHost
   */
  protected boolean canDefine_methodHost(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_inExplicitConstructorInvocation(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_inExplicitConstructorInvocation(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_inExplicitConstructorInvocation(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeHierarchyCheck.jrag:194
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inExplicitConstructorInvocation
   */
  protected boolean canDefine_inExplicitConstructorInvocation(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_enclosingExplicitConstructorHostType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_enclosingExplicitConstructorHostType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_enclosingExplicitConstructorHostType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeHierarchyCheck.jrag:204
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingExplicitConstructorHostType
   */
  protected boolean canDefine_enclosingExplicitConstructorHostType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_inStaticContext(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_inStaticContext(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Enums.jrag:210
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inStaticContext
   */
  protected boolean canDefine_inStaticContext(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_reportUnreachable(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_reportUnreachable(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_reportUnreachable(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:281
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute reportUnreachable
   */
  protected boolean canDefine_reportUnreachable(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isMethodParameter(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isMethodParameter(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isMethodParameter(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/MultiCatch.jrag:54
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isMethodParameter
   */
  protected boolean canDefine_isMethodParameter(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isConstructorParameter(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isConstructorParameter(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isConstructorParameter(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/MultiCatch.jrag:55
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isConstructorParameter
   */
  protected boolean canDefine_isConstructorParameter(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isExceptionHandlerParameter(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isExceptionHandlerParameter(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isExceptionHandlerParameter(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/MultiCatch.jrag:56
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isExceptionHandlerParameter
   */
  protected boolean canDefine_isExceptionHandlerParameter(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_mayUseAnnotationTarget(self, _callerNode, name)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_mayUseAnnotationTarget(self, _callerNode, name);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:147
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayUseAnnotationTarget
   */
  protected boolean canDefine_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    return false;
  }
  /** @apilevel internal */
  public ElementValue Define_lookupElementTypeValue(ASTNode _callerNode, ASTNode _childNode, String name) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupElementTypeValue(self, _callerNode, name)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupElementTypeValue(self, _callerNode, name);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:280
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupElementTypeValue
   */
  protected boolean canDefine_lookupElementTypeValue(ASTNode _callerNode, ASTNode _childNode, String name) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_withinSuppressWarnings(ASTNode _callerNode, ASTNode _childNode, String annot) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_withinSuppressWarnings(self, _callerNode, annot)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_withinSuppressWarnings(self, _callerNode, annot);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:409
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute withinSuppressWarnings
   */
  protected boolean canDefine_withinSuppressWarnings(ASTNode _callerNode, ASTNode _childNode, String annot) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_withinDeprecatedAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_withinDeprecatedAnnotation(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_withinDeprecatedAnnotation(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:538
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute withinDeprecatedAnnotation
   */
  protected boolean canDefine_withinDeprecatedAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public Annotation Define_lookupAnnotation(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupAnnotation(self, _callerNode, typeDecl)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupAnnotation(self, _callerNode, typeDecl);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:610
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupAnnotation
   */
  protected boolean canDefine_lookupAnnotation(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_enclosingAnnotationDecl(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_enclosingAnnotationDecl(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_enclosingAnnotationDecl(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:650
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingAnnotationDecl
   */
  protected boolean canDefine_enclosingAnnotationDecl(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_assignConvertedType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_assignConvertedType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_assignConvertedType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/ToClassInherited.jrag:40
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignConvertedType
   */
  protected boolean canDefine_assignConvertedType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_inExtendsOrImplements(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_inExtendsOrImplements(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_inExtendsOrImplements(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:340
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inExtendsOrImplements
   */
  protected boolean canDefine_inExtendsOrImplements(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public FieldDecl Define_fieldDecl(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_fieldDecl(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_fieldDecl(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1255
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute fieldDecl
   */
  protected boolean canDefine_fieldDecl(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_typeWildcard(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeWildcard(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeWildcard(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1593
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeWildcard
   */
  protected boolean canDefine_typeWildcard(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_lookupWildcardExtends(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupWildcardExtends(self, _callerNode, typeDecl)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupWildcardExtends(self, _callerNode, typeDecl);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1602
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupWildcardExtends
   */
  protected boolean canDefine_lookupWildcardExtends(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_lookupWildcardSuper(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupWildcardSuper(self, _callerNode, typeDecl)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupWildcardSuper(self, _callerNode, typeDecl);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1616
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupWildcardSuper
   */
  protected boolean canDefine_lookupWildcardSuper(ASTNode _callerNode, ASTNode _childNode, TypeDecl typeDecl) {
    return false;
  }
  /** @apilevel internal */
  public LUBType Define_lookupLUBType(ASTNode _callerNode, ASTNode _childNode, Collection<TypeDecl> bounds) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupLUBType(self, _callerNode, bounds)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupLUBType(self, _callerNode, bounds);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1644
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupLUBType
   */
  protected boolean canDefine_lookupLUBType(ASTNode _callerNode, ASTNode _childNode, Collection<TypeDecl> bounds) {
    return false;
  }
  /** @apilevel internal */
  public GLBType Define_lookupGLBType(ASTNode _callerNode, ASTNode _childNode, Collection<TypeDecl> bounds) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupGLBType(self, _callerNode, bounds)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupGLBType(self, _callerNode, bounds);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1685
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupGLBType
   */
  protected boolean canDefine_lookupGLBType(ASTNode _callerNode, ASTNode _childNode, Collection<TypeDecl> bounds) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_genericDecl(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_genericDecl(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_genericDecl(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericsParTypeDecl.jrag:90
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute genericDecl
   */
  protected boolean canDefine_genericDecl(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_variableArityValid(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_variableArityValid(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_variableArityValid(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/VariableArityParameters.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute variableArityValid
   */
  protected boolean canDefine_variableArityValid(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public ClassInstanceExpr Define_getClassInstanceExpr(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_getClassInstanceExpr(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_getClassInstanceExpr(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/Diamond.jrag:96
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute getClassInstanceExpr
   */
  protected boolean canDefine_getClassInstanceExpr(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isAnonymousDecl(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isAnonymousDecl(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isAnonymousDecl(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/Diamond.jrag:414
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isAnonymousDecl
   */
  protected boolean canDefine_isAnonymousDecl(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isExplicitGenericConstructorAccess(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isExplicitGenericConstructorAccess(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isExplicitGenericConstructorAccess(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/Diamond.jrag:430
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isExplicitGenericConstructorAccess
   */
  protected boolean canDefine_isExplicitGenericConstructorAccess(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isCatchParam(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isCatchParam(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isCatchParam(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:206
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isCatchParam
   */
  protected boolean canDefine_isCatchParam(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public CatchClause Define_catchClause(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_catchClause(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_catchClause(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:215
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute catchClause
   */
  protected boolean canDefine_catchClause(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_resourcePreviouslyDeclared(ASTNode _callerNode, ASTNode _childNode, String name) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_resourcePreviouslyDeclared(self, _callerNode, name)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_resourcePreviouslyDeclared(self, _callerNode, name);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/TryWithResources.jrag:200
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute resourcePreviouslyDeclared
   */
  protected boolean canDefine_resourcePreviouslyDeclared(ASTNode _callerNode, ASTNode _childNode, String name) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_targetType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_targetType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_targetType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:125
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute targetType
   */
  protected boolean canDefine_targetType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public int Define_variableScopeEndLabel(ASTNode _callerNode, ASTNode _childNode, CodeGeneration gen) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_variableScopeEndLabel(self, _callerNode, gen)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_variableScopeEndLabel(self, _callerNode, gen);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:92
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute variableScopeEndLabel
   */
  protected boolean canDefine_variableScopeEndLabel(ASTNode _callerNode, ASTNode _childNode, CodeGeneration gen) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_expectedType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_expectedType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:108
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute expectedType
   */
  protected boolean canDefine_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public ClassInstanceExpr Define_classInstanceExpression(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_classInstanceExpression(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_classInstanceExpression(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:446
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute classInstanceExpression
   */
  protected boolean canDefine_classInstanceExpression(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public int Define_localNum(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_localNum(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_localNum(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/TryWithResources.jrag:316
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute localNum
   */
  protected boolean canDefine_localNum(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public int Define_resultSaveLocalNum(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_resultSaveLocalNum(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_resultSaveLocalNum(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/LocalNum.jrag:102
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute resultSaveLocalNum
   */
  protected boolean canDefine_resultSaveLocalNum(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public SimpleSet<TypeDecl> Define_allImportedTypes(ASTNode _callerNode, ASTNode _childNode, String name) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_allImportedTypes(self, _callerNode, name)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_allImportedTypes(self, _callerNode, name);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NameCheck.jrag:52
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute allImportedTypes
   */
  protected boolean canDefine_allImportedTypes(ASTNode _callerNode, ASTNode _childNode, String name) {
    return false;
  }
  /** @apilevel internal */
  public String Define_packageName(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_packageName(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_packageName(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/QualifiedNames.jrag:110
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute packageName
   */
  protected boolean canDefine_packageName(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_enclosingType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_enclosingType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_enclosingType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:704
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingType
   */
  protected boolean canDefine_enclosingType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isNestedType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isNestedType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isNestedType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:703
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isNestedType
   */
  protected boolean canDefine_isNestedType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isLocalClass(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isLocalClass(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isLocalClass(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeAnalysis.jrag:612
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isLocalClass
   */
  protected boolean canDefine_isLocalClass(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public String Define_hostPackage(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_hostPackage(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_hostPackage(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:770
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute hostPackage
   */
  protected boolean canDefine_hostPackage(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_inComplexAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_inComplexAnnotation(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_inComplexAnnotation(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:97
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inComplexAnnotation
   */
  protected boolean canDefine_inComplexAnnotation(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isOriginalEnumConstructor(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isOriginalEnumConstructor(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isOriginalEnumConstructor(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Enums.jrag:134
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isOriginalEnumConstructor
   */
  protected boolean canDefine_isOriginalEnumConstructor(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_inEnumInitializer(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_inEnumInitializer(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_inEnumInitializer(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Enums.jrag:568
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inEnumInitializer
   */
  protected boolean canDefine_inEnumInitializer(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public String Define_typeVariableContext(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeVariableContext(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeVariableContext(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:791
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVariableContext
   */
  protected boolean canDefine_typeVariableContext(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public LambdaExpr Define_enclosingLambda(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_enclosingLambda(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_enclosingLambda(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/ToClassInherited.jrag:34
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingLambda
   */
  protected boolean canDefine_enclosingLambda(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_assignmentContext(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_assignmentContext(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:358
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignmentContext
   */
  protected boolean canDefine_assignmentContext(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_invocationContext(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_invocationContext(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_invocationContext(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:359
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute invocationContext
   */
  protected boolean canDefine_invocationContext(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_castContext(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_castContext(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_castContext(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:360
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute castContext
   */
  protected boolean canDefine_castContext(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_stringContext(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_stringContext(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:361
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute stringContext
   */
  protected boolean canDefine_stringContext(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_numericContext(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_numericContext(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_numericContext(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TargetType.jrag:362
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute numericContext
   */
  protected boolean canDefine_numericContext(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public int Define_typeVarPosition(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeVarPosition(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeVarPosition(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeVariablePositions.jrag:46
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVarPosition
   */
  protected boolean canDefine_typeVarPosition(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_typeVarInMethod(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_typeVarInMethod(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_typeVarInMethod(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeVariablePositions.jrag:47
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute typeVarInMethod
   */
  protected boolean canDefine_typeVarInMethod(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public int Define_genericMethodLevel(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_genericMethodLevel(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_genericMethodLevel(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeVariablePositions.jrag:50
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute genericMethodLevel
   */
  protected boolean canDefine_genericMethodLevel(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isLeftChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isLeftChildOfDot(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isLeftChildOfDot(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:33
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isLeftChildOfDot
   */
  protected boolean canDefine_isLeftChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_isRightChildOfDot(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_isRightChildOfDot(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/GenericMethods.jrag:33
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isRightChildOfDot
   */
  protected boolean canDefine_isRightChildOfDot(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public Expr Define_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_prevExpr(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_prevExpr(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:37
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute prevExpr
   */
  protected boolean canDefine_prevExpr(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public Access Define_nextAccess(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_nextAccess(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_nextAccess(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Annotations.jrag:39
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute nextAccess
   */
  protected boolean canDefine_nextAccess(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v, BodyDecl b) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_assignedBefore(self, _callerNode, v, b)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_assignedBefore(self, _callerNode, v, b);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:292
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignedBefore
   */
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v, BodyDecl b) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v, BodyDecl b) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_unassignedBefore(self, _callerNode, v, b)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_unassignedBefore(self, _callerNode, v, b);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/DefiniteAssignment.jrag:935
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unassignedBefore
   */
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v, BodyDecl b) {
    return false;
  }
  /** @apilevel internal */
  public Stmt Define_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_branchTarget(self, _callerNode, branch)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_branchTarget(self, _callerNode, branch);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/ExtraInheritedEqs.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute branchTarget
   */
  protected boolean canDefine_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    return false;
  }
  /** @apilevel internal */
  public FinallyHost Define_enclosingFinally(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_enclosingFinally(self, _callerNode, branch)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_enclosingFinally(self, _callerNode, branch);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/ExtraInheritedEqs.jrag:29
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingFinally
   */
  protected boolean canDefine_enclosingFinally(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    return false;
  }
  /** @apilevel internal */
  public SimpleSet<TypeDecl> Define_otherLocalClassDecls(ASTNode _callerNode, ASTNode _childNode, String name) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_otherLocalClassDecls(self, _callerNode, name)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_otherLocalClassDecls(self, _callerNode, name);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/ExtraInheritedEqs.jrag:33
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute otherLocalClassDecls
   */
  protected boolean canDefine_otherLocalClassDecls(ASTNode _callerNode, ASTNode _childNode, String name) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_leavesMonitor(ASTNode _callerNode, ASTNode _childNode, Stmt branch, SynchronizedStmt monitor) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_leavesMonitor(self, _callerNode, branch, monitor)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_leavesMonitor(self, _callerNode, branch, monitor);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/backend/Tmp.jrag:29
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute leavesMonitor
   */
  protected boolean canDefine_leavesMonitor(ASTNode _callerNode, ASTNode _childNode, Stmt branch, SynchronizedStmt monitor) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_reachable(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_reachable(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/UnreachableStatements.jrag:29
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute reachable
   */
  protected boolean canDefine_reachable(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_inhModifiedInScope(self, _callerNode, var)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_inhModifiedInScope(self, _callerNode, var);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/EffectivelyFinal.jrag:35
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inhModifiedInScope
   */
  protected boolean canDefine_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    return false;
  }
  /** @apilevel internal */
  public Modifiers Define_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_declarationModifiers(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_declarationModifiers(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/TryWithResources.jrag:321
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declarationModifiers
   */
  protected boolean canDefine_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public Access Define_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_declarationType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_declarationType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/TryWithResources.jrag:323
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declarationType
   */
  protected boolean canDefine_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public FieldDeclarator Define_erasedField(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_erasedField(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_erasedField(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/frontend/Generics.jrag:1513
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute erasedField
   */
  protected boolean canDefine_erasedField(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public boolean Define_reachableCatchClause(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_reachableCatchClause(self, _callerNode, exceptionType)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_reachableCatchClause(self, _callerNode, exceptionType);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/TryWithResources.jrag:144
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute reachableCatchClause
   */
  protected boolean canDefine_reachableCatchClause(ASTNode _callerNode, ASTNode _childNode, TypeDecl exceptionType) {
    return false;
  }
  /** @apilevel internal */
  public Collection<TypeDecl> Define_caughtExceptions(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_caughtExceptions(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_caughtExceptions(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/PreciseRethrow.jrag:219
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute caughtExceptions
   */
  protected boolean canDefine_caughtExceptions(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_enclosingLambdaType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_enclosingLambdaType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_enclosingLambdaType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/VariableDeclaration.jrag:79
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingLambdaType
   */
  protected boolean canDefine_enclosingLambdaType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public TypeDecl Define_inferredType(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_inferredType(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_inferredType(self, _callerNode);
  }

  /**
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/TypeCheck.jrag:478
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inferredType
   */
  protected boolean canDefine_inferredType(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
public ASTNode rewrittenNode() { throw new Error("rewrittenNode is undefined for ASTNode"); }
}
