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
 * @ast class
 * @aspect StackMapTable
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java6/backend/StackMap.jrag:36
 */
public class StackMapTableAttribute extends Attribute {
  
    public StackMapTableAttribute(CodeGeneration codeGen) {
      super(codeGen.constantPool(), "StackMapTable");

      java.util.List<StackFrame> frames = codeGen.stackFrames();
      u2(frames.size());
      for (StackFrame frame : frames) {
        frame.emit(this, codeGen.constantPool());
      }
    }


}
