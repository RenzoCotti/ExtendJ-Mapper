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
 * @declaredat /Users/BMW/Downloads/extendj/java4/backend/ConstantPool.jrag:389
 */
public class ConstantUtf8 extends CPInfo {
  
    private String name;

  

    public ConstantUtf8(String name) {
      this.name = name;
    }

  

    @Override
    public void emit(DataOutputStream out) throws IOException {
      out.writeByte(ConstantPool.CONSTANT_Utf8);
      out.writeUTF(name);
    }

  

    @Override
    public String toString() {
      return pos + " ConstantUtf8: tag " + ConstantPool.CONSTANT_Utf8 + ", length: "
          + name.length() + ", bytes: " + name;
    }


}
