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
 * @aspect Attributes
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/Attributes.jrag:36
 */
 class Attribute extends java.lang.Object {
  
    int attribute_name_index;

  
    ByteArrayOutputStream buf = new ByteArrayOutputStream();

  
    DataOutputStream output = new DataOutputStream(buf);

  

    public Attribute(ConstantPool cp, String name) {
      attribute_name_index = cp.addUtf8(name);
    }

  

    public void emit(DataOutputStream out) throws IOException {
      out.writeChar(attribute_name_index);
      out.writeInt(buf.size());
      buf.writeTo(out);
      output.close();
      buf.close();
    }

  

    public int size() {
      return buf.size();
    }

  

    public void u1(int v) {
      try {
        output.writeByte(v);
      } catch(IOException e) {
      }
    }

  

    public void u2(int v) {
      try {
        output.writeChar(v);
      } catch(IOException e) {
      }
    }

  

    public void u4(int v) {
      try {
        output.writeInt(v);
      } catch(IOException e) {
      }
    }

  

    public void append(byte[] data) {
      try {
        output.write(data, 0, data.length);
      } catch(IOException e) {
      }
    }

  

    public void append(Attribute attribute) {
      try {
        attribute.emit(output);
      } catch(IOException e) {
      }
    }


}
