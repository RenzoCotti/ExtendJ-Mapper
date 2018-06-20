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
 * @ast class
 * @aspect Attributes
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Attributes.jrag:183
 */
 class LineNumberTableAttribute extends Attribute {
  
    public LineNumberTableAttribute(CodeGeneration gen) {
      super(gen.constantPool(), "LineNumberTable");
      u2(gen.lineNumberTable.size());
      for (CodeGeneration.LineNumberEntry e : gen.lineNumberTable) {
        u2(e.start_pc);
        u2(e.line_number);
      }
    }


}
