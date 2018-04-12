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
 * @declaredat /Users/BMW/Downloads/extendj/java4/grammar/Java.ast:78
 * @production VarDeclStmt : {@link Stmt} ::= <span class="component">{@link Modifiers}</span> <span class="component">TypeAccess:{@link Access}</span> <span class="component">Declarator:{@link VariableDeclarator}*</span>;

 */
public class VarDeclStmt extends Stmt implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/PrettyPrint.jadd:629
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print(getModifiers());
    out.print(getTypeAccess());
    out.print(" ");
    out.join(getDeclaratorList(), new PrettyPrinter.Joiner() {
      @Override
      public void printSeparator(PrettyPrinter out) {
        out.print(", ");
      }
    });
    out.print(";");
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Downloads/extendj/java4/backend/CreateBCode.jrag:368
   */
  public <E extends TraceElement<S>, S> NodeValueList generateExplanation(
		  TraceIterator<E, S> trace,
		  TraceGenerator<E, S> generator,
		  String contextMsg) {
	  PrimitiveTypeAccess type = ((PrimitiveTypeAccess)getChild(1));

	  String varNames = getDeclaratorList().getNumChild() > 1 ? "the variables " : "the variable ";

	  for (Iterator<VariableDeclarator> it = getDeclaratorList().iterator(); it.hasNext(); ) {
		  varNames += "'"+it.next().name()+"'";
	      if (it.hasNext()){
	    	  varNames += ", ";
	      }
	  }


	  contextMsg +=  Messages.replace(Messages.VAR_DECL_STMT_ENTRY, new String[]{varNames, type.getID()});
	  generator.generate(trace, contextMsg, new NodeValueList(), this, false);

	  for (VariableDeclarator decl : getDeclaratorList()) {
	      decl.generateExplanation(trace, generator, "");
	  }

	  return new NodeValueList();
  }
  /**
   * @aspect CreateBCode
   * @declaredat /Users/BMW/Downloads/extendj/java4/backend/CreateBCode.jrag:394
   */
  public void createBCode(CodeGeneration gen) {
    this.bcStartIndex = gen.pos();

    gen.addLineNumberEntryAtCurrentPC(this); // Generate line number entry.
    for (VariableDeclarator decl : getDeclaratorList()) {
      decl.createBCode(gen);
    }

    this.bcEndIndex = gen.pos();
  }
  /**
   * @declaredat ASTNode:1
   */
  public VarDeclStmt() {
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
    setChild(new List(), 2);
  }
  /**
   * @declaredat ASTNode:14
   */
  public VarDeclStmt(Modifiers p0, Access p1, List<VariableDeclarator> p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:20
   */
  protected int numChildren() {
    return 3;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:26
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:30
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    canCompleteNormally_reset();
    localSize_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:42
   */
  public VarDeclStmt clone() throws CloneNotSupportedException {
    VarDeclStmt node = (VarDeclStmt) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:47
   */
  public VarDeclStmt copy() {
    try {
      VarDeclStmt node = (VarDeclStmt) clone();
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
  public VarDeclStmt fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:76
   */
  public VarDeclStmt treeCopyNoTransform() {
    VarDeclStmt tree = (VarDeclStmt) copy();
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
  public VarDeclStmt treeCopy() {
    VarDeclStmt tree = (VarDeclStmt) copy();
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
   * Replaces the Declarator list.
   * @param list The new list node to be used as the Declarator list.
   * @apilevel high-level
   */
  public void setDeclaratorList(List<VariableDeclarator> list) {
    setChild(list, 2);
  }
  /**
   * Retrieves the number of children in the Declarator list.
   * @return Number of children in the Declarator list.
   * @apilevel high-level
   */
  public int getNumDeclarator() {
    return getDeclaratorList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Declarator list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Declarator list.
   * @apilevel low-level
   */
  public int getNumDeclaratorNoTransform() {
    return getDeclaratorListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Declarator list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Declarator list.
   * @apilevel high-level
   */
  public VariableDeclarator getDeclarator(int i) {
    return (VariableDeclarator) getDeclaratorList().getChild(i);
  }
  /**
   * Check whether the Declarator list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasDeclarator() {
    return getDeclaratorList().getNumChild() != 0;
  }
  /**
   * Append an element to the Declarator list.
   * @param node The element to append to the Declarator list.
   * @apilevel high-level
   */
  public void addDeclarator(VariableDeclarator node) {
    List<VariableDeclarator> list = (parent == null) ? getDeclaratorListNoTransform() : getDeclaratorList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addDeclaratorNoTransform(VariableDeclarator node) {
    List<VariableDeclarator> list = getDeclaratorListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the Declarator list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setDeclarator(VariableDeclarator node, int i) {
    List<VariableDeclarator> list = getDeclaratorList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the Declarator list.
   * @return The node representing the Declarator list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Declarator")
  public List<VariableDeclarator> getDeclaratorList() {
    List<VariableDeclarator> list = (List<VariableDeclarator>) getChild(2);
    return list;
  }
  /**
   * Retrieves the Declarator list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Declarator list.
   * @apilevel low-level
   */
  public List<VariableDeclarator> getDeclaratorListNoTransform() {
    return (List<VariableDeclarator>) getChildNoTransform(2);
  }
  /**
   * @return the element at index {@code i} in the Declarator list without
   * triggering rewrites.
   */
  public VariableDeclarator getDeclaratorNoTransform(int i) {
    return (VariableDeclarator) getDeclaratorListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Declarator list.
   * @return The node representing the Declarator list.
   * @apilevel high-level
   */
  public List<VariableDeclarator> getDeclarators() {
    return getDeclaratorList();
  }
  /**
   * Retrieves the Declarator list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Declarator list.
   * @apilevel low-level
   */
  public List<VariableDeclarator> getDeclaratorsNoTransform() {
    return getDeclaratorListNoTransform();
  }
  /** @apilevel internal */
  private void assignedAfter_Variable_reset() {
    assignedAfter_Variable_values = null;
  }
  protected java.util.Map assignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="/Users/BMW/Downloads/extendj/java4/frontend/DefiniteAssignment.jrag:264")
  public boolean assignedAfter(Variable v) {
    Object _parameters = v;
    if (assignedAfter_Variable_values == null) assignedAfter_Variable_values = new java.util.HashMap(4);
    ASTNode$State.CircularValue _value;
    if (assignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = assignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTNode$State.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTNode$State.CircularValue) _cache;
      }
    } else {
      _value = new ASTNode$State.CircularValue();
      assignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTNode$State state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_assignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_assignedAfter_Variable_value = getDeclarator(getNumDeclarator() - 1).assignedAfter(v);
        if (new_assignedAfter_Variable_value != ((Boolean)_value.value)) {
          state.setChangeInCycle();
          _value.value = new_assignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedAfter_Variable_values.put(_parameters, new_assignedAfter_Variable_value);

      state.leaveCircle();
      return new_assignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedAfter_Variable_value = getDeclarator(getNumDeclarator() - 1).assignedAfter(v);
      if (new_assignedAfter_Variable_value != ((Boolean)_value.value)) {
        state.setChangeInCycle();
        _value.value = new_assignedAfter_Variable_value;
      }
      return new_assignedAfter_Variable_value;
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
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="/Users/BMW/Downloads/extendj/java4/frontend/DefiniteAssignment.jrag:899")
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
        new_unassignedAfter_Variable_value = getDeclarator(getNumDeclarator() - 1).unassignedAfter(v);
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
      boolean new_unassignedAfter_Variable_value = getDeclarator(getNumDeclarator() - 1).unassignedAfter(v);
      if (new_unassignedAfter_Variable_value != ((Boolean)_value.value)) {
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
   * @aspect VariableScope
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/LookupVariable.jrag:219
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="/Users/BMW/Downloads/extendj/java4/frontend/LookupVariable.jrag:219")
  public VariableDeclarator variableDeclaration(String name) {
    {
        for (VariableDeclarator decl : getDeclaratorList()) {
          if (decl.declaresVariable(name)) {
            return decl;
          }
        }
        return null;
      }
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/TypeAnalysis.jrag:275
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/Users/BMW/Downloads/extendj/java4/frontend/TypeAnalysis.jrag:275")
  public TypeDecl type() {
    TypeDecl type_value = getTypeAccess().type();
    return type_value;
  }
  /** @apilevel internal */
  private void canCompleteNormally_reset() {
    canCompleteNormally_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle canCompleteNormally_computed = null;

  /** @apilevel internal */
  protected boolean canCompleteNormally_value;

  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/UnreachableStatements.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="/Users/BMW/Downloads/extendj/java4/frontend/UnreachableStatements.jrag:50")
  public boolean canCompleteNormally() {
    ASTNode$State state = state();
    if (canCompleteNormally_computed == ASTNode$State.NON_CYCLE || canCompleteNormally_computed == state().cycle()) {
      return canCompleteNormally_value;
    }
    canCompleteNormally_value = reachable();
    if (state().inCircle()) {
      canCompleteNormally_computed = state().cycle();
    
    } else {
      canCompleteNormally_computed = ASTNode$State.NON_CYCLE;
    
    }
    return canCompleteNormally_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat /Users/BMW/Downloads/extendj/java7/frontend/PreciseRethrow.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="/Users/BMW/Downloads/extendj/java7/frontend/PreciseRethrow.jrag:78")
  public boolean modifiedInScope(Variable var) {
    {
        for (VariableDeclarator decl : getDeclaratorList()) {
          if (decl.modifiedInScope(var)) {
            return true;
          }
        }
        return false;
      }
  }
  /** @apilevel internal */
  private void localSize_reset() {
    localSize_computed = null;
  }
  /** @apilevel internal */
  protected ASTNode$State.Cycle localSize_computed = null;

  /** @apilevel internal */
  protected int localSize_value;

  /**
   * Computes size required for local variables of this statement.
   * NB: only relevant for variable declaration statements.
   * @return local size for declared variables
   * @attribute syn
   * @aspect LocalNum
   * @declaredat /Users/BMW/Downloads/extendj/java4/backend/LocalNum.jrag:38
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LocalNum", declaredAt="/Users/BMW/Downloads/extendj/java4/backend/LocalNum.jrag:38")
  public int localSize() {
    ASTNode$State state = state();
    if (localSize_computed == ASTNode$State.NON_CYCLE || localSize_computed == state().cycle()) {
      return localSize_value;
    }
    localSize_value = localSize_compute();
    if (state().inCircle()) {
      localSize_computed = state().cycle();
    
    } else {
      localSize_computed = ASTNode$State.NON_CYCLE;
    
    }
    return localSize_value;
  }
  /** @apilevel internal */
  private int localSize_compute() {
      int size = 0;
      for (VariableDeclarator decl : getDeclaratorList()) {
        size += decl.localSize();
      }
      return size;
    }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/DefiniteAssignment.jrag:66
   * @apilevel internal
   */
  public boolean Define_isIncOrDec(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getDeclaratorListNoTransform()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/frontend/DefiniteAssignment.jrag:70
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else {
      return getParent().Define_isIncOrDec(this, _callerNode);
    }
  }
  protected boolean canDefine_isIncOrDec(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (_callerNode == getDeclaratorListNoTransform()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/frontend/DefiniteAssignment.jrag:564
      int index = _callerNode.getIndexOfChild(_childNode);
      return index == 0 ? assignedBefore(v) : getDeclarator(index - 1).assignedAfter(v);
    }
    else {
      return getParent().Define_assignedBefore(this, _callerNode, v);
    }
  }
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/DefiniteAssignment.jrag:891
   * @apilevel internal
   */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (_callerNode == getDeclaratorListNoTransform()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/frontend/DefiniteAssignment.jrag:1170
      int index = _callerNode.getIndexOfChild(_childNode);
      return index == 0 ? unassignedBefore(v) : getDeclarator(index - 1).unassignedAfter(v);
    }
    else {
      return getParent().Define_unassignedBefore(this, _callerNode, v);
    }
  }
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java7/backend/MultiCatch.jrag:113
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getDeclaratorListNoTransform()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/frontend/LookupVariable.jrag:114
      int index = _callerNode.getIndexOfChild(_childNode);
      {
          for (int i = index - 1; i >= 0; i -= 1) {
            if (getDeclarator(i).declaresVariable(name)) {
              return getDeclarator(i);
            }
          }
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
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/Modifiers.jrag:437
   * @apilevel internal
   */
  public boolean Define_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/frontend/Modifiers.jrag:323
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
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/Modifiers.jrag:439
   * @apilevel internal
   */
  public boolean Define_mayBeVolatile(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/frontend/Modifiers.jrag:324
      return true;
    }
    else {
      return getParent().Define_mayBeVolatile(this, _callerNode);
    }
  }
  protected boolean canDefine_mayBeVolatile(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/frontend/SyntacticClassification.jrag:107
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
   * @declaredat /Users/BMW/Downloads/extendj/java5/frontend/Annotations.jrag:713
   * @apilevel internal
   */
  public TypeDecl Define_declType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getDeclaratorListNoTransform()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/frontend/TypeAnalysis.jrag:280
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return null;
    }
    else {
      return getParent().Define_declType(this, _callerNode);
    }
  }
  protected boolean canDefine_declType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/VariableDeclaration.jrag:133
   * @apilevel internal
   */
  public Modifiers Define_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getDeclaratorListNoTransform()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/frontend/VariableDeclaration.jrag:135
      int index = _callerNode.getIndexOfChild(_childNode);
      return getModifiers();
    }
    else {
      return getParent().Define_declarationModifiers(this, _callerNode);
    }
  }
  protected boolean canDefine_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java4/frontend/VariableDeclaration.jrag:144
   * @apilevel internal
   */
  public Access Define_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getDeclaratorListNoTransform()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/frontend/VariableDeclaration.jrag:146
      int index = _callerNode.getIndexOfChild(_childNode);
      return getTypeAccess();
    }
    else {
      return getParent().Define_declarationType(this, _callerNode);
    }
  }
  protected boolean canDefine_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java5/frontend/Annotations.jrag:131
   * @apilevel internal
   */
  public boolean Define_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat /Users/BMW/Downloads/extendj/java5/frontend/Annotations.jrag:162
      return name.equals("LOCAL_VARIABLE");
    }
    else {
      return getParent().Define_mayUseAnnotationTarget(this, _callerNode, name);
    }
  }
  protected boolean canDefine_mayUseAnnotationTarget(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java5/frontend/Generics.jrag:1249
   * @apilevel internal
   */
  public FieldDecl Define_fieldDecl(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getDeclaratorListNoTransform()) {
      // @declaredat /Users/BMW/Downloads/extendj/java5/frontend/Generics.jrag:1253
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return null;
    }
    else {
      return getParent().Define_fieldDecl(this, _callerNode);
    }
  }
  protected boolean canDefine_fieldDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java5/frontend/Generics.jrag:1509
   * @apilevel internal
   */
  public FieldDeclarator Define_erasedField(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getDeclaratorListNoTransform()) {
      // @declaredat /Users/BMW/Downloads/extendj/java5/frontend/Generics.jrag:1516
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      {
          throw new Error("FieldDeclarator child of VarDeclStmt");
        }
    }
    else {
      return getParent().Define_erasedField(this, _callerNode);
    }
  }
  protected boolean canDefine_erasedField(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/BMW/Downloads/extendj/java7/backend/MultiCatch.jrag:64
   * @apilevel internal
   */
  public int Define_localNum(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getDeclaratorListNoTransform()) {
      // @declaredat /Users/BMW/Downloads/extendj/java4/backend/LocalNum.jrag:52
      int index = _callerNode.getIndexOfChild(_childNode);
      {
          if (index == 0) {
            return localNum();
          } else {
            return getDeclarator(index - 1).localNum() + getDeclarator(index - 1).localSize();
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
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }
}
