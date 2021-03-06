/* Copyright (c) 2005-2008, Torbjorn Ekman
 *                    2013, Jesper Öqvist <jesper.oqvist@cs.lth.se>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.extendj.ast;

import java.util.List;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beaver.Symbol;
import javafx.geometry.Pos;

/**
 * Helper class to generate bytecode instructions.
 *
 * Stores the generated bytecodes in an internal byte array.
 * The generated bytecode is accessed using toArray().
 */
@SuppressWarnings({"javadoc","unchecked","rawtypes"})
class CodeGeneration {
  /**
   * An exception of this type is thrown during code generation if the code
   * generator tried to generate a jump that did not fit in a short jump offset
   * (2 bytes).  Code generation should then be retried with wide gotos (4 byte
   * offsets).
   */
  public static class JumpOffsetError extends Error {
    public JumpOffsetError() {
      super("Trying to generate a bytecode jump that does not fit in a 2 byte offset.");
    }
  }

  static class LocalVariableEntry {
    int start_pc;
    int length;
    int name_index;
    int descriptor_index;
    int index;
  }

  class ExceptionEntry {
    public static final int CATCH_ALL = 0;

    int start_pc;
    int end_pc;
    int handler_lbl;
    int catch_type;

    public ExceptionEntry(int start_pc, int end_pc, int handler_lbl, int catch_type) {
      this.start_pc = start_pc;
      this.end_pc = end_pc;
      this.handler_lbl = handler_lbl;
      this.catch_type = catch_type;
    }

    public int handlerPC() {
      return addressOf(handler_lbl);
    }
  }

  static class LineNumberEntry {
    int start_pc;
    int line_number;
  }

  static class ExceptionRange {
    int start;
    int end;

    ExceptionRange(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  static class Monitor {
    java.util.List<ExceptionRange> ranges =
      new ArrayList<ExceptionRange>();
    final SynchronizedStmt mon;
    int start_lbl = -1;

    Monitor(SynchronizedStmt mon) {
      this.mon = mon;
    }

    void rangeStart(int label) {
      start_lbl = label;
    }

    void rangeEnd(int label) {
      if (start_lbl != -1) {
        ranges.add(new ExceptionRange(start_lbl, label));
        start_lbl = -1;
      }
    }

    void monitorEnter(ASTNode<ASTNode> node, CodeGeneration gen) {
      gen.emitDup(node);
      gen.emitStoreReference(node, mon.localNum());
      gen.emit(node, Bytecode.MONITORENTER);
    }

    void monitorExit(CodeGeneration gen) {
      MonitorExit monExit = mon.getMonitorExit();
      monExit.emitMonitorExitHandler(gen);
      if (start_lbl != -1 && gen.addressOf(start_lbl) !=
          gen.addressOf(monExit.handler_label())) {
        rangeEnd(monExit.handler_end_label());
      }
      for (ExceptionRange range: ranges) {
        gen.addException(range.start, range.end,
            monExit.handler_label(),
            CodeGeneration.ExceptionEntry.CATCH_ALL);
      }
    }
  }

  private ByteArray bytes = new ByteArray();

  private ConstantPool constantPool;

  private boolean wideGoto = false;

  private int variableScopeLabel = 1;

  private Map<Integer, Integer> variableScopeLabelAddress = new HashMap<Integer, Integer>();

  private Map<Integer, Collection<LocalVariableEntry>> variableScopeLabelUses =
      new HashMap<Integer, Collection<LocalVariableEntry>>();

  public Collection<LocalVariableEntry> localVariableTable = new ArrayList<LocalVariableEntry>();

  public Collection<LineNumberEntry> lineNumberTable = new ArrayList<LineNumberEntry>();

	public Collection<PositionEntry> positionTable = new ArrayList<PositionEntry>();

  public Collection<ExceptionEntry> exceptions = new ArrayList<ExceptionEntry>();

  int maxLocals = 0;

  private Map<Integer, Integer> address = new HashMap<Integer, Integer>();

  private Map<Integer, Collection<Integer>> uses = new HashMap<Integer, Collection<Integer>>();

  private java.util.List<Monitor> monitors = new ArrayList<Monitor>();


  // TODO: Refactor the code for adding line index information into the class file
  private final String methodName;

  private final String methodDesc;

  private final String hostName;

  private final String path;

  static class Location{
	  byte b;
	  int line;
	  int start;
	  int end;
	  int bcIndex;

	public Location(byte b, int bcIndex, int line, int start, int end) {
		this.b = b;
		this.line = line;
		this.start = start;
		this.end = end;
		this.bcIndex = bcIndex;
	}
  }

  /**
   * Initializes code generation with narrow jumps. This will cause addLabel
   * and emitGoto to throw a JumpOffsetError if the required jump (or
   * back-patched jump) does not fit inside a 2-byte offset.
   */
  public CodeGeneration(ConstantPool constantPool,
		  					String methodName,
		  					String methodDesc,
		  					String hostName,
		  					String path) {
    this.constantPool = constantPool;
    this.methodName = methodName;
    this.methodDesc = methodDesc;
    this.hostName = hostName;
    this.path = path;
  }

  /**
   * If the wideGoto parameter is {@code true} then code generation is
   * initialized to use wide gotos where possible. This can prevent
   * JumpOffsetError from being thrown during code generation, but will
   * generate slightly larger bytecode.
   */
  public CodeGeneration(ConstantPool constantPool, boolean wideGoto,
							String methodName,
							String methodDesc,
							String hostName,
							String path) {
    this.constantPool = constantPool;
    this.wideGoto = wideGoto;
    this.methodName = methodName;
    this.methodDesc = methodDesc;
    this.hostName = hostName;
    this.path = path;
  }

  public void clearCodeGeneration() {
    bytes = null;
    constantPool = null;
    variableScopeLabelAddress = null;
    variableScopeLabelUses = null;
    localVariableTable = null;
    lineNumberTable = null;
		positionTable = null;
    exceptions = null;
    address = null;
    uses = null;
  }

  public ConstantPool constantPool() {
    return constantPool;
  }

  public int variableScopeLabel() {
    return variableScopeLabel++;
  }

  public void addVariableScopeLabel(int label) {
    Integer label_object = label;
    variableScopeLabelAddress.put(label_object, pos());
    // Update all reference to this label.
    if (variableScopeLabelUses.containsKey(label_object)) {
      Collection<LocalVariableEntry> array = variableScopeLabelUses.get(label_object);
      for (LocalVariableEntry e : array) {
        e.length = pos() - e.start_pc;
      }
    }
  }

  public void addLocalVariableEntryAtCurrentPC(String name, String typeDescriptor,
      int localNum, int variableScopeEndLabel) {
    LocalVariableEntry e = new LocalVariableEntry();
    e.start_pc = pos();
    e.length = 0;
    e.name_index = constantPool().addUtf8(name);
    e.descriptor_index = constantPool().addUtf8(typeDescriptor);
    e.index = localNum;
    localVariableTable.add(e);
    Integer label_object = variableScopeEndLabel;
    if (!variableScopeLabelUses.containsKey(label_object)) {
      variableScopeLabelUses.put(label_object, new ArrayList<LocalVariableEntry>());
    }
    variableScopeLabelUses.get(label_object).add(e);
  }

  public void addLineNumberEntryAtCurrentPC(ASTNode node) {
    LineNumberEntry e = new LineNumberEntry();
    e.start_pc = pos();
    e.line_number = node.sourceLineNumber();
    if (e.line_number != -1 && e.line_number != 65535) {
      lineNumberTable.add(e);
    }
  }

  /**
   * Adds an exception handler for the given exception type.
   * If catch_type is zero then this handler catches any exception.
   * @param start_lbl
   * @param end_lbl
   * @param handler_lbl
   * @param catch_type
   */
  public void addException(int start_lbl, int end_lbl, int handler_lbl, int catch_type) {
    int start_pc = addressOf(start_lbl);
    int end_pc = addressOf(end_lbl);
    if (start_pc != end_pc) {
      exceptions.add(new ExceptionEntry(start_pc, end_pc, handler_lbl, catch_type));
    }
  }

  public int maxLocals() {
    return maxLocals+1;
  }

  /**
   * Add a label at the current PC. Back-patches the label address to previous
   * jumps that use this label. May throw a JumpOffsetError if the offset to
   * this label does not fit in a any previous jump.
   */
  public void addLabel(int label) {
    Integer label_object = label;
    address.put(label_object, pos());
    // Update all reference to this label.
    if (uses.containsKey(label_object)) {
      for (int use : uses.get(label_object)) {
        if (bytes.get(use) == Bytecode.GOTO_W) {
          setAddress32(use + 1, pos() - use);
        } else {
          setAddress(use + 1, pos() -  use);
        }
      }
    }
  }

  /**
   * Returns the address of the given label.
   */
  public int addressOf(int label) {
    Integer label_object = label;
    if (!address.containsKey(label_object)) {
      throw new Error("Can not compute address of unplaced label (id: " + label + ")");
    }
    return address.get(label_object);
  }

  /**
   * Calculate the jump offset to reach a label.
   * @return the offset from the current PC to the label, or 0 if the label has
   * not been placed yet.
   */
  private int jump(int label) {
    Integer label_object = label;
    if (!uses.containsKey(label_object)) {
      uses.put(label_object, new ArrayList());
    }
    Collection<Integer> jumps = uses.get(label_object);
    jumps.add(pos()); // Add this PC to list of jumps to be back-patched for the given label.
    Integer val = address.get(label_object);
    if (val != null) {
      return val - pos();
    }
    return 0;
  }

  private void setAddress(int position, int address) {
    if (address > Short.MAX_VALUE || address < Short.MIN_VALUE) {
      throw new JumpOffsetError();
    }
    bytes.set(position + 0, (byte) ((address & 0xff00)>>8));
    bytes.set(position + 1, (byte) (address & 0xff));
  }

  private void setAddress32(int position, int address) {
    bytes.set(position + 0, (byte) (address >> 24 & 0xff));
    bytes.set(position + 1, (byte) (address >> 16 & 0xff));
    bytes.set(position + 2, (byte) (address >> 8 & 0xff));
    bytes.set(position + 3, (byte) (address & 0xff));
  }

  public void emitStoreReference(ASTNode<? extends ASTNode> node, int pos) {
    maxLocals = Math.max(maxLocals, pos + 1);
    if (pos == 0) {
      emit(node, Bytecode.ASTORE_0);
    } else if (pos == 1) {
      emit(node, Bytecode.ASTORE_1);
    } else if (pos == 2) {
      emit(node, Bytecode.ASTORE_2);
    } else if (pos == 3) {
      emit(node, Bytecode.ASTORE_3);
    } else if (pos < 256) {
      emit(node, Bytecode.ASTORE).add(pos);
    } else {
      emit(node, Bytecode.WIDE).emit(node, Bytecode.ASTORE).add2(pos);
    }
  }

  public void emitLoadReference(ASTNode<? extends ASTNode> node, int pos) {
    maxLocals = Math.max(maxLocals, pos + 1);
    if (pos == 0) {
      emit(node, Bytecode.ALOAD_0);
    } else if (pos == 1) {
      emit(node, Bytecode.ALOAD_1);
    } else if (pos == 2) {
      emit(node, Bytecode.ALOAD_2);
    } else if (pos == 3) {
      emit(node, Bytecode.ALOAD_3);
    } else if (pos < 256) {
      emit(node, Bytecode.ALOAD).add(pos);
    } else {
      emit(node, Bytecode.WIDE).emit(node, Bytecode.ALOAD).add2(pos);
    }
  }

  public void emitReturn(ASTNode<? extends ASTNode> node) {
		// addPositionEntryAtCurrentPC(node);
	  emit(node, Bytecode.RETURN);
  }

  public void emitThrow(ASTNode<? extends ASTNode> node) {
		// addPositionEntryAtCurrentPC(node);
    emit(node, Bytecode.ATHROW);
  }

  public void emitInstanceof(ASTNode<? extends ASTNode> node, TypeDecl type) {
    int p = constantPool().addClass(type.isArrayDecl()
        ? type.typeDescriptor()
        : type.constantPoolName());

		// addPositionEntryAtCurrentPC(node);
    emit(node, Bytecode.INSTANCEOF).add2(p);
  }

  public void emitCheckCast(ASTNode<? extends ASTNode> node, TypeDecl type) {
    int p = constantPool().addClass(type.isArrayDecl()
        ? type.typeDescriptor()
        : type.constantPoolName());

		// addPositionEntryAtCurrentPC(node);
    emit(node, Bytecode.CHECKCAST).add2(p);
  }

  public void emitDup(ASTNode<? extends ASTNode> node) {
		// addPositionEntryAtCurrentPC(node);
    emit(node, Bytecode.DUP);
  }

  public void emitDup2(ASTNode<? extends ASTNode> node) {
		// addPositionEntryAtCurrentPC(node);
    emit(node, Bytecode.DUP2);
  }

  public void emitPop(ASTNode<? extends ASTNode> node) {
		// addPositionEntryAtCurrentPC(node);
    emit(node, Bytecode.POP);
  }

  public void emitSwap(ASTNode<? extends ASTNode> node) {
		// addPositionEntryAtCurrentPC(node);
    emit(node, Bytecode.SWAP);
  }

  public void emitBranchNonNull(ASTNode<? extends ASTNode> node, int label) {
    int p = jump(label);
		// addPositionEntryAtCurrentPC(node);
    emit(node, Bytecode.IFNONNULL).add2(p);
  }

  public void emitGoto(ASTNode<? extends ASTNode> node, int label) {
    int p = jump(label);
    if (wideGoto) {
			addPositionEntryAtCurrentPC(node);
      bytes.emitGoto(Bytecode.GOTO_W).add4(p);
    } else {
      if (p > Short.MAX_VALUE || p < Short.MIN_VALUE) {
        throw new JumpOffsetError();
      }
			addPositionEntryAtCurrentPC(node);
      bytes.emitGoto(Bytecode.GOTO).add2(p);
    }
  }

  public void emitCompare(ASTNode<? extends ASTNode> node, byte bytecode, int label) {
    int p = jump(label);
		// addPositionEntryAtCurrentPC(node);
    emit(node, bytecode).add2(p);
  }

  @Override
  public String toString() {
    return bytes.toString();
  }

  public int size() {
    return bytes.size();
  }

  public int pos() {
    return bytes.pos();
  }

  public void setPos(int pos) {
    bytes.setPos(pos);
  }

  public void skip(int num) {
    bytes.skip(num);
  }

  public byte[] toArray() {
    return bytes.toArray();
  }

  CodeGeneration add(int i) {
    return add((byte) i);
  }

  CodeGeneration add(byte b) {
    bytes.add(b);
    return this;
  }

  CodeGeneration add2(int index) {
    bytes.add2(index);
    return this;
  }

  CodeGeneration add4(int index) {
    bytes.add4(index);
    return this;
  }

  CodeGeneration emit(ASTNode<? extends ASTNode> node, byte b) {
//	System.out.println("[CG]:\tByte:\t"+b + ",\tLine:\t"+ node.lineNumber() +",\tStart:\t"+node.getColumn(node.start())+",\tEnd:\t"+ node.getColumn(node.end()));
//	for (StackTraceElement s: Thread.currentThread().getStackTrace()){
//		System.out.println(s);
//	}

		addPositionEntryAtCurrentPC(node);
    bytes.emit(b);
    return this;
  }

  CodeGeneration emit(ASTNode<? extends ASTNode> node, byte b, int stackChange) {
//	  System.out.println("[CG]:\tByte:\t"+b + ",\tLine:\t"+ node.lineNumber() +",\tStart:\t"+node.getColumn(node.start())+",\tEnd:\t"+ node.getColumn(node.end()));
//	  for (StackTraceElement s: Thread.currentThread().getStackTrace()){
//		  System.out.println(s);
//	  }

		addPositionEntryAtCurrentPC(node);
    bytes.emit(b, stackChange);
    return this;
  }

  public int maxStackDepth() {
    return bytes.maxStackDepth();
  }

  public int stackDepth() {
    return bytes.stackDepth();
  }

  public void changeStackDepth(int i) {
    bytes.changeStackDepth(i);
  }

  /**
   * Pus a new monitor to the monitor stack.
   * @param mon the monitor local number
   * @return the monitor id
   */
  public int monitorEnter(SynchronizedStmt mon) {
    Monitor monitor = new Monitor(mon);
    monitor.monitorEnter(mon, this);
    monitors.add(monitor);
    return monitors.size() - 1;
  }

  /**
   * Exit the current top monitor.
   */
  public void monitorExit() {
    if (monitors.isEmpty()) {
      throw new Error("Monitor stack is empty!");
    }
    Monitor monitor = monitors.remove(monitors.size()-1);
    monitor.monitorExit(this);
  }

  /**
   * Start a monitor exception range.
   * @param monitorId the monitor id
   */
  public void monitorRangeStart(int monitorId, int label) {
    monitors.get(monitorId).rangeStart(label);
  }

  public void monitorRangesStart(Stmt branch, int label) {
    for (Monitor monitor : monitors) {
      if (branch.leavesMonitor(branch, monitor.mon)) {
        monitor.rangeStart(label);
      }
    }
  }

  /**
   * End a monitor exception range.
   * @param monitorId the monitor id
   */
  public void monitorRangeEnd(ASTNode<ASTNode> node, int monitorId, int label) {
    Monitor monitor = monitors.get(monitorId);
    emitLoadReference(node, monitor.mon.localNum());
    emit(node, Bytecode.MONITOREXIT);
    addLabel(label);
    monitor.rangeEnd(label);
  }

	static class PositionEntry {
		int pc;
		int start_line;
		int start_column;
		int end_line;
		int end_column;
		int id;
  }

	public void addPositionEntryAtCurrentPC(ASTNode node){
		int sl = node.startLine();
		int sc = node.startColumn();
		int el = node.endLine();
		int ec = node.endColumn();
		int id = Counter.getId(node);
		//case the node isn't an empty list
		if (sl != 0 && el != 0) {
			PositionEntry pe = new PositionEntry();
			pe.pc = pos();
			pe.start_line = sl;
			pe.start_column = sc;
			pe.end_line = el;
			pe.end_column = ec;
			pe.id = id;
			positionTable.add(pe);
		}
	}
}
