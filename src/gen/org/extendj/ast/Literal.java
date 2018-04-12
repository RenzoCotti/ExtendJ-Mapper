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
 * The abstract base class for all literals.
 * @ast node
 * @declaredat /Users/BMW/Downloads/extendj/java7/grammar/Literals.ast:4
 * @production Literal : {@link PrimaryExpr} ::= <span class="component">&lt;LITERAL:String&gt;</span>;

 */
public abstract class Literal extends PrimaryExpr implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/PrettyPrint.jadd:442
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getLITERAL());
  }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/BytecodeCONSTANT.jrag:96
   */
  public static Literal buildBooleanLiteral(boolean value) {
    return new BooleanLiteral(value ? "true" : "false");
  }
  /**
   * @aspect BytecodeCONSTANT
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/BytecodeCONSTANT.jrag:100
   */
  public static Literal buildStringLiteral(String value) {
    return new StringLiteral(value);
  }
  /**
   * Escapes a string literal.
   * @param s string to escape
   * @return escaped string literal
   * @aspect PrettyPrintUtil
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/PrettyPrintUtil.jrag:295
   */
  protected static String escape(String s) {
    StringBuffer result = new StringBuffer();
    for (int i=0; i < s.length(); i++) {
      switch(s.charAt(i)) {
        case '\b':
          result.append("\\b");
          break;
        case '\t':
          result.append("\\t");
          break;
        case '\n':
          result.append("\\n");
          break;
        case '\f':
          result.append("\\f");
          break;
        case '\r':
          result.append("\\r");
          break;
        case '\"':
          result.append("\\\"");
          break;
        case '\'':
          result.append("\\\'");
          break;
        case '\\':
          result.append("\\\\");
          break;
        default:
          int value = (int) s.charAt(i);
          if (value < 0x20 || (value > 0x7e)) {
            result.append(asEscape(value));
          } else {
            result.append(s.charAt(i));
          }
      }
    }
    return result.toString();
  }
  /**
   * @aspect PrettyPrintUtil
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/PrettyPrintUtil.jrag:335
   */
  protected static String asEscape(int value) {
    StringBuffer s = new StringBuffer("\\u");
    String hex = Integer.toHexString(value);
    for (int i = 0; i < 4-hex.length(); i++) {
      s.append("0");
    }
    s.append(hex);
    return s.toString();
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Downloads/extendj/java4/backend/CodeGeneration.jrag:232
   */
  public void emitPushConstant(CodeGeneration gen) {
    System.err.println("ERROR: Tried to generate bytecode for: " + getClass().getName());
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Downloads/extendj/java4/backend/CreateBCode.jrag:323
   */
  public <E extends TraceElement<S>, S> NodeValueList generateExplanation(TraceIterator<E, S> trace,
		  TraceGenerator<E, S> generator, String contextMsg) {
	  trace.checkAndFwd().value(0);
	  String msg = Messages.LITERAL_LOAD.replace("?",  this.getLITERAL());
	  System.out.println("Literal value is " +this.getLITERAL());
	  ASTNodeValue nodeValue = new ASTNodeValue(this, this.getLITERAL(), msg);
	  NodeValueList list = new NodeValueList();
	  list.add(nodeValue);

	  return list;
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Downloads/extendj/java4/backend/CreateBCode.jrag:336
   */
  public void createBCode(CodeGeneration gen) {
    emitPushConstant(gen);
  }
  /**
   * @declaredat ASTNode:1
   */
  public Literal() {
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
  /**
   * @declaredat ASTNode:12
   */
  public Literal(String p0) {
    setLITERAL(p0);
  }
  /**
   * @declaredat ASTNode:15
   */
  public Literal(beaver.Symbol p0) {
    setLITERAL(p0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:19
   */
  protected int numChildren() {
    return 0;
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
  }
  /** @apilevel internal 
   * @declaredat ASTNode:34
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public Literal clone() throws CloneNotSupportedException {
    Literal node = (Literal) super.clone();
    return node;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:49
   */
  @Deprecated
  public abstract Literal fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:57
   */
  public abstract Literal treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:65
   */
  public abstract Literal treeCopy();
  /**
   * Replaces the lexeme LITERAL.
   * @param value The new value for the lexeme LITERAL.
   * @apilevel high-level
   */
  public void setLITERAL(String value) {
    tokenString_LITERAL = value;
  }
  /** @apilevel internal 
   */
  protected String tokenString_LITERAL;
  /**
   */
  public int LITERALstart;
  /**
   */
  public int LITERALend;
  /**
   * JastAdd-internal setter for lexeme LITERAL using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme LITERAL
   * @apilevel internal
   */
  public void setLITERAL(beaver.Symbol symbol) {
    if (symbol.value != null && !(symbol.value instanceof String))
    throw new UnsupportedOperationException("setLITERAL is only valid for String lexemes");
    tokenString_LITERAL = (String)symbol.value;
    LITERALstart = symbol.getStart();
    LITERALend = symbol.getEnd();
  }
  /**
   * Retrieves the value for the lexeme LITERAL.
   * @return The value for the lexeme LITERAL.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="LITERAL")
  public String getLITERAL() {
    return tokenString_LITERAL != null ? tokenString_LITERAL : "";
  }
  /**
   * @return a fresh double literal representing the given value
   * @aspect Java7Literals
   * @declaredat /Users/BMW/Downloads/extendj/java7/frontend/Literals.jrag:76
   */
    public static Literal buildDoubleLiteral(double value) {
    String digits = Double.toString(value);
    NumericLiteral lit = new DoubleLiteral(digits);
    lit.setDigits(digits);
    lit.setKind(NumericLiteral.DECIMAL);
    return lit;
  }
  /**
   * @return a fresh float literal representing the given value
   * @aspect Java7Literals
   * @declaredat /Users/BMW/Downloads/extendj/java7/frontend/Literals.jrag:88
   */
    public static Literal buildFloatLiteral(float value) {
    String digits = Float.toString(value);
    NumericLiteral lit = new FloatingPointLiteral(digits);
    lit.setDigits(digits);
    lit.setKind(NumericLiteral.DECIMAL);
    return lit;
  }
  /**
   * @return a fresh integer literal representing the given value
   * @aspect Java7Literals
   * @declaredat /Users/BMW/Downloads/extendj/java7/frontend/Literals.jrag:100
   */
    public static Literal buildIntegerLiteral(int value) {
    String digits = Integer.toHexString(value);
    NumericLiteral lit = new IntegerLiteral("0x" + digits);
    lit.setDigits(digits.toLowerCase());
    lit.setKind(NumericLiteral.HEXADECIMAL);
    return lit;
  }
  /**
   * @return a fresh long literal representing the given value
   * @aspect Java7Literals
   * @declaredat /Users/BMW/Downloads/extendj/java7/frontend/Literals.jrag:112
   */
    public static Literal buildLongLiteral(long value) {
    String digits = Long.toHexString(value);
    NumericLiteral lit = new LongLiteral("0x" + digits);
    lit.setDigits(digits.toLowerCase());
    lit.setKind(NumericLiteral.HEXADECIMAL);
    return lit;
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
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/ConstantExpression.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Downloads/extendj/java4/frontend/ConstantExpression.jrag:38")
  public Constant constant() {
    ASTNode$State state = state();
    if (constant_computed == ASTNode$State.NON_CYCLE || constant_computed == state().cycle()) {
      return constant_value;
    }
    constant_value = constant_compute();
    if (state().inCircle()) {
      constant_computed = state().cycle();
    
    } else {
      constant_computed = ASTNode$State.NON_CYCLE;
    
    }
    return constant_value;
  }
  /** @apilevel internal */
  private Constant constant_compute() {
      throw new UnsupportedOperationException("ConstantExpression operation constant"
          + " not supported for type " + getClass().getName());
    }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/ConstantExpression.jrag:383
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Downloads/extendj/java4/frontend/ConstantExpression.jrag:383")
  public boolean isConstant() {
    boolean isConstant_value = true;
    return isConstant_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/ConstantExpression.jrag:435
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Downloads/extendj/java4/frontend/ConstantExpression.jrag:435")
  public boolean isTrue() {
    boolean isTrue_value = false;
    return isTrue_value;
  }
  /**
   * @attribute syn
   * @aspect ConstantExpression
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/ConstantExpression.jrag:438
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Downloads/extendj/java4/frontend/ConstantExpression.jrag:438")
  public boolean isFalse() {
    boolean isFalse_value = false;
    return isFalse_value;
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
