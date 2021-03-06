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
 * @aspect Java7Constant
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/frontend/Constant.jadd:33
 */
 class Constant extends java.lang.Object {
  
    static class ConstantInt extends Constant {
      private int value;
      public ConstantInt(int i) { this.value = i; }
      int intValue() { return value; }
      long longValue() { return value; }
      float floatValue() { return value; }
      double doubleValue() { return value; }
      String stringValue() { return new Integer(value).toString(); }
    }

  
    static class ConstantLong extends Constant {
      private long value;
      public ConstantLong(long l) { this.value = l; }
      int intValue() { return (int)value; }
      long longValue() { return value; }
      float floatValue() { return value; }
      double doubleValue() { return value; }
      String stringValue() { return new Long(value).toString(); }
    }

  
    static class ConstantFloat extends Constant {
      private float value;
      public ConstantFloat(float f) { this.value = f; }
      int intValue() { return (int)value; }
      long longValue() { return (long)value; }
      float floatValue() { return value; }
      double doubleValue() { return value; }
      String stringValue() { return new Float(value).toString(); }
    }

  
    static class ConstantDouble extends Constant {
      private double value;
      public ConstantDouble(double d) { this.value = d; }
      int intValue() { return (int)value; }
      long longValue() { return (long)value; }
      float floatValue() { return (float)value; }
      double doubleValue() { return value; }
      String stringValue() { return new Double(value).toString(); }
    }

  
    static class ConstantChar extends Constant {
      private char value;
      public ConstantChar(char c) { this.value = c; }
      int intValue() { return value; }
      long longValue() { return value; }
      float floatValue() { return value; }
      double doubleValue() { return value; }
      String stringValue() { return new Character(value).toString(); }
    }

  
    static class ConstantBoolean extends Constant {
      private boolean value;
      public ConstantBoolean(boolean b) { this.value = b; }
      boolean booleanValue() { return value; }
      String stringValue() { return new Boolean(value).toString(); }
    }

  
    static class ConstantString extends Constant {
      private String value;
      public ConstantString(String s) { this.value = s; }
      String stringValue() { return value; }
    }

  

    int intValue() { throw new UnsupportedOperationException(); }

  
    long longValue() { throw new UnsupportedOperationException(); }

  
    float floatValue() { throw new UnsupportedOperationException(); }

  
    double doubleValue() { throw new UnsupportedOperationException(); }

  
    boolean booleanValue() { throw new UnsupportedOperationException(getClass().getName()); }

  
    String stringValue() { throw new UnsupportedOperationException(); }

  

    protected Constant() { }

  

    public boolean error = false;

  

    static Constant create(int i) { return new ConstantInt(i); }

  
    static Constant create(long l) { return new ConstantLong(l); }

  
    static Constant create(float f) { return new ConstantFloat(f); }

  
    static Constant create(double d) { return new ConstantDouble(d); }

  
    static Constant create(boolean b) { return new ConstantBoolean(b); }

  
    static Constant create(char c) { return new ConstantChar(c); }

  
    static Constant create(String s) { return new ConstantString(s); }

  public void createBCode(ASTNode<ASTNode> node, CodeGeneration gen) {
    if (this instanceof ConstantInt) {
      IntegerLiteral.push(node, gen, intValue());
    } else if (this instanceof ConstantLong) {
      LongLiteral.push(node, gen, longValue());
    } else if (this instanceof ConstantFloat) {
      FloatingPointLiteral.push(node, gen, floatValue());
    } else if (this instanceof ConstantDouble) {
      DoubleLiteral.push(node, gen, doubleValue());
    } else if (this instanceof ConstantChar) {
      IntegerLiteral.push(node, gen, intValue());
    } else if (this instanceof ConstantBoolean) {
      BooleanLiteral.push(node, gen, booleanValue());
    } else if (this instanceof ConstantString) {
      StringLiteral.push(node, gen, stringValue());
    }
  }


}
