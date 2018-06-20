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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Attributes.jrag:169
 */
 class LocalVariableTableAttribute extends Attribute {
  
    public LocalVariableTableAttribute(CodeGeneration gen) {
      super(gen.constantPool(), "LocalVariableTable");
      u2(gen.localVariableTable.size());
      for (CodeGeneration.LocalVariableEntry e : gen.localVariableTable) {
        u2(e.start_pc);
        u2(e.length);
        u2(e.name_index);
        u2(e.descriptor_index);
        u2(e.index);
      }
    }


}
