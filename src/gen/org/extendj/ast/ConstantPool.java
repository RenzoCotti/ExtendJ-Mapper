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
 * @aspect ConstantPool
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/ConstantPool.jrag:38
 */
public class ConstantPool extends java.lang.Object {
  
    public TypeDecl typeDecl;

  
    public ConstantPool(TypeDecl typeDecl) {
      this.typeDecl = typeDecl;
    }

  

    public static final byte CONSTANT_Class = 7;

  
    public static final byte CONSTANT_Fieldref = 9;

  
    public static final byte CONSTANT_Methodref = 10;

  
    public static final byte CONSTANT_InterfaceMethodref = 11;

  
    public static final byte CONSTANT_String = 8;

  
    public static final byte CONSTANT_Integer = 3;

  
    public static final byte CONSTANT_Float = 4;

  
    public static final byte CONSTANT_Long = 5;

  
    public static final byte CONSTANT_Double = 6;

  
    public static final byte CONSTANT_NameAndType = 12;

  
    public static final byte CONSTANT_Utf8 = 1;

  

    private int posCounter = 1;

  

    private ArrayList<CPInfo> list = new ArrayList<CPInfo>();

  

    private int labelCounter = 1;

  
    private Map<String, CPInfo> classConstants = new HashMap<String, CPInfo>();

  
    private Map<String, CPInfo> fieldrefConstants = new HashMap<String, CPInfo>();

  
    private Map<String, CPInfo> methodrefConstants = new HashMap<String, CPInfo>();

  
    private Map<String, CPInfo> interfaceMethodrefConstants = new HashMap<String, CPInfo>();

  
    private Map<String, CPInfo> nameAndTypeConstants = new HashMap<String, CPInfo>();

  
    private Map<String, CPInfo> utf8Constants = new HashMap<String, CPInfo>();

  
    private Map<String, CPInfo> stringConstants = new HashMap<String, CPInfo>();

  
    private Map<Integer, CPInfo> intConstants = new HashMap<Integer, CPInfo>();

  
    private Map<Long, CPInfo> longConstants = new HashMap<Long, CPInfo>();

  
    private Map<Float, CPInfo> floatConstants = new HashMap<Float, CPInfo>();

  
    private Map<Double, CPInfo> doubleConstants = new HashMap<Double, CPInfo>();

  

    private void addCPInfo(CPInfo info) {
      info.pos = posCounter;
      posCounter += info.size();
      if (posCounter > 0xFFFF) {
        throw new Error("Too many constants in class!");
      }
      list.add(info);
    }

  

    // For debugging purposes.
    public int numElements() {
      return list.size();
    }

  

    @Override
    public String toString() {
      StringBuffer s = new StringBuffer();
      for (CPInfo info : list) {
        s.append(info.toString());
        s.append("\n");
      }
      return s.toString();
    }

  

    public void emit(DataOutputStream out) throws IOException {
      out.writeChar(posCounter);
      for (CPInfo info : list) {
        info.emit(out);
      }
    }

  

    public int newLabel() {
      return labelCounter++;
    }

  

    public int addClass(String name) {
      if (!classConstants.containsKey(name)) {
        CPInfo info = new ConstantClass(addUtf8(name.replace('.', '/')));
        addCPInfo(info);
        classConstants.put(name, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = classConstants.get(name);
      return info.pos;
    }

  

    public int addFieldref(String classname, String name, String type) {
      String key = classname + name + type;
      if (!fieldrefConstants.containsKey(key)) {
        CPInfo info = new ConstantFieldref(addClass(classname), addNameAndType(name, type));
        addCPInfo(info);
        fieldrefConstants.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = fieldrefConstants.get(key);
      return info.pos;
    }

  

    public int addMethodref(String classname, String name, String desc) {
      String key = classname + name + desc;
      if (!methodrefConstants.containsKey(key)) {
        CPInfo info = new ConstantMethodref(addClass(classname), addNameAndType(name, desc));
        addCPInfo(info);
        methodrefConstants.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = methodrefConstants.get(key);
      return info.pos;
    }

  

    public int addInterfaceMethodref(String classname, String name, String desc) {
      String key = classname + name + desc;
      if (!interfaceMethodrefConstants.containsKey(key)) {
        CPInfo info = new ConstantInterfaceMethodref(addClass(classname),
            addNameAndType(name, desc));
        addCPInfo(info);
        interfaceMethodrefConstants.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = interfaceMethodrefConstants.get(key);
      return info.pos;
    }

  

    public int addNameAndType(String name, String type) {
      String key = name + type;
      if (!nameAndTypeConstants.containsKey(key)) {
        CPInfo info = new ConstantNameAndType(addUtf8(name), addUtf8(type));
        addCPInfo(info);
        nameAndTypeConstants.put(key, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = nameAndTypeConstants.get(key);
      return info.pos;
    }

  

    public int addUtf8(String name) {
      if (!utf8Constants.containsKey(name)) {
        CPInfo info = new ConstantUtf8(name);
        addCPInfo(info);
        utf8Constants.put(name, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = utf8Constants.get(name);
      return info.pos;
    }

  

    /**
     * Add value to constant pool.
     * @return index of value in constant pool
     */
    public int addConstant(String val) {
      if (!stringConstants.containsKey(val)) {
        CPInfo info = new ConstantString(addUtf8(val));
        addCPInfo(info);
        stringConstants.put(val, info);
        String s = info.toString();
        return info.pos;
      }
      CPInfo info = stringConstants.get(val);
      return info.pos;
    }

  

    /**
     * Add value to constant pool.
     * @return index of value in constant pool
     */
    public int addConstant(int val) {
      Integer key = Integer.valueOf(val);
      if (!intConstants.containsKey(key)) {
        CPInfo info = new ConstantInteger(val);
        addCPInfo(info);
        intConstants.put(key, info);
        return info.pos;
      }
      CPInfo info = intConstants.get(key);
      return info.pos;
    }

  

    /**
     * Add value to constant pool.
     * @return index of value in constant pool
     */
    public int addConstant(float val) {
      Float key = Float.valueOf(val);
      if (!floatConstants.containsKey(key)) {
        CPInfo info = new ConstantFloat(val);
        addCPInfo(info);
        floatConstants.put(key, info);
        return info.pos;
      }
      CPInfo info = floatConstants.get(key);
      return info.pos;
    }

  

    /**
     * Add value to constant pool.
     * @return index of value in constant pool
     */
    public int addConstant(long val) {
      Long key = Long.valueOf(val);
      if (!longConstants.containsKey(key)) {
        CPInfo info = new ConstantLong(val);
        addCPInfo(info);
        longConstants.put(key, info);
        return info.pos;
      }
      CPInfo info = longConstants.get(key);
      return info.pos;
    }

  

    /**
     * Add value to constant pool.
     * @return index of value in constant pool
     */
    public int addConstant(double val) {
      Double key = Double.valueOf(val);
      if (!doubleConstants.containsKey(key)) {
        CPInfo info = new ConstantDouble(val);
        addCPInfo(info);
        doubleConstants.put(key, info);
        return info.pos;
      }
      CPInfo info = doubleConstants.get(key);
      return info.pos;
    }


}
