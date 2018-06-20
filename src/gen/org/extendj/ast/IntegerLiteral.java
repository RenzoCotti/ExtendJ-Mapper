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
 * Default Java integer literal. Should only be used for numbers
 * that can be stored in 32 bits binary.
 * @ast node
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/grammar/Literals.ast:48
 * @production IntegerLiteral : {@link NumericLiteral};

 */
public class IntegerLiteral extends NumericLiteral implements Cloneable {
  /**
   * @aspect NodeConstructors
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/NodeConstructors.jrag:62
   */
  public IntegerLiteral(int i) {
    this(Integer.toString(i));
  }
  /** Push a constant value. 
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:100
   */
  public static void push(ASTNode<ASTNode> node, CodeGeneration gen, int value) {
    switch(value) {
      case -1:
        gen.emit(node, Bytecode.ICONST_M1);
        break;
      case 0:
        gen.emit(node, Bytecode.ICONST_0);
        break;
      case 1:
        gen.emit(node, Bytecode.ICONST_1);
        break;
      case 2:
        gen.emit(node, Bytecode.ICONST_2);
        break;
      case 3:
        gen.emit(node, Bytecode.ICONST_3);
        break;
      case 4:
        gen.emit(node, Bytecode.ICONST_4);
        break;
      case 5:
        gen.emit(node, Bytecode.ICONST_5);
        break;
      default:
        if (value >= -128 && value <= 127) {
          gen.emit(node, Bytecode.BIPUSH).add(value);
        } else if (value >= -32768 && value <= 32767) {
          gen.emit(node, Bytecode.SIPUSH).add2(value);
        } else {
          int index = gen.constantPool().addConstant(value);
          if (index < 256) {
            gen.emit(node, Bytecode.LDC).add(index);
          } else {
            gen.emit(node, Bytecode.LDC_W).add2(index);
          }
        }
    }
  }
  /**
   * @aspect CodeGeneration
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/CodeGeneration.jrag:214
   */
  public void emitPushConstant(CodeGeneration gen) {
    type().emitPushConstant(this, gen, constant().intValue());
  }
  /**
   * @declaredat ASTNode:1
   */
  public IntegerLiteral() {
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
  public IntegerLiteral(String p0) {
    setLITERAL(p0);
  }
  /**
   * @declaredat ASTNode:15
   */
  public IntegerLiteral(beaver.Symbol p0) {
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
    return true;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:29
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    type_reset();
    constant_reset();
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
  public IntegerLiteral clone() throws CloneNotSupportedException {
    IntegerLiteral node = (IntegerLiteral) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:44
   */
  public IntegerLiteral copy() {
    try {
      IntegerLiteral node = (IntegerLiteral) clone();
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
  public IntegerLiteral fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:73
   */
  public IntegerLiteral treeCopyNoTransform() {
    IntegerLiteral tree = (IntegerLiteral) copy();
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
  public IntegerLiteral treeCopy() {
    IntegerLiteral tree = (IntegerLiteral) copy();
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
    return super.is$Equal(node) && (tokenString_LITERAL == ((IntegerLiteral) node).tokenString_LITERAL);    
  }
  /**
   * Replaces the lexeme LITERAL.
   * @param value The new value for the lexeme LITERAL.
   * @apilevel high-level
   */
  public void setLITERAL(String value) {
    tokenString_LITERAL = value;
  }
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
   * @attribute syn
   * @aspect PositiveLiterals
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PositiveLiterals.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PositiveLiterals", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/PositiveLiterals.jrag:36")
  public boolean isPositive() {
    boolean isPositive_value = !getLITERAL().startsWith("-");
    return isPositive_value;
  }
  /**
   * @attribute syn
   * @aspect TypeCheck
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:750
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeCheck", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:750")
  public Problem sizeError() {
    Problem sizeError_value = errorf("The integer literal \"%s\" is too large for type int.", getLITERAL());
    return sizeError_value;
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
    type_value = typeInt();
    if (state().inCircle()) {
      type_computed = state().cycle();
    
    } else {
      type_computed = ASTNode$State.NON_CYCLE;
    
    }
    return type_value;
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
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="ConstantExpression", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/ConstantExpression.jrag:38")
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
      long l = 0;
      try {
        l = parseLong();
      } catch (NumberFormatException e) {
        Constant c = Constant.create(0L);
        c.error = true;
        return c;
      }
      int clamped = (int) l;
      Constant c = Constant.create(clamped);
      if (l != clamped) {
        if (l != (0xFFFFFFFFL & clamped) || getKind() == DECIMAL) {
          c.error = true;
        }
      }
      return c;
    }
  /**
   * Utility attribute for literal rewriting.
   * Any of the NumericLiteral subclasses have already
   * been rewritten and/or parsed, and should not be
   * rewritten again.
   * 
   * @return true if this literal is a "raw", not-yet-parsed NumericLiteral
   * @attribute syn
   * @aspect Java7Literals
   * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/Literals.jrag:364
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Java7Literals", declaredAt="/Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/Literals.jrag:364")
  public boolean needsRewrite() {
    boolean needsRewrite_value = false;
    return needsRewrite_value;
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
    // @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/frontend/TypeCheck.jrag:754
    if (constant().error) {
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
    if (constant().error) {
      collection.add(sizeError());
    }
  }
}
