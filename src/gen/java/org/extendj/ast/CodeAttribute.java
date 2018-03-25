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
 * @aspect Attributes
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/extendj/java4/backend/Attributes.jrag:194
 */
public class CodeAttribute extends Attribute {
  
    public CodeAttribute(CodeGeneration codeGen, MethodDecl m) {
      super(codeGen.constantPool(), "Code");
      u2(codeGen.maxStackDepth());
      u2(codeGen.maxLocals());
      u4(codeGen.pos()); // code_length
      append(codeGen);
      u2(codeGen.exceptions.size());
      for (CodeGeneration.ExceptionEntry e : codeGen.exceptions) {
        u2(e.start_pc);
        u2(e.end_pc);
        u2(e.handlerPC());
        u2(e.catch_type);
      }

      Collection<Attribute> attributes = ASTNode.codeAttributes(codeGen, m);

      u2(attributes.size());
      for (Attribute attr: attributes) {
        append(attr);
      }
    }


}
