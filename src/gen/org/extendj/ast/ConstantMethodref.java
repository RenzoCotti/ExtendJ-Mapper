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
 * @ast class
 * @aspect ConstantPool
 * @declaredat /Users/BMW/Downloads/extendj/java4/backend/ConstantPool.jrag:320
 */
public class ConstantMethodref extends CPInfo {
  
    private int classname;

  
    private int nameandtype;

  

    public ConstantMethodref(int classname, int nameandtype) {
      this.classname = classname;
      this.nameandtype = nameandtype;
    }

  

    @Override
    public void emit(DataOutputStream out) throws IOException {
      out.writeByte(ConstantPool.CONSTANT_Methodref);
      out.writeChar(classname);
      out.writeChar(nameandtype);
    }

  

    @Override
    public String toString() {
      return pos + " ConstantMethodref: tag " + ConstantPool.CONSTANT_Methodref
          + ", class_index: " + classname + ", name_and_type_index: " + nameandtype;
    }


}
