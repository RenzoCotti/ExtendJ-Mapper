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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/ConstantPool.jrag:467
 */
public class ConstantLong extends CPInfo {
  
    private long val;

  

    public ConstantLong(long val) {
      this.val = val;
    }

  

    @Override
    public void emit(DataOutputStream out) throws IOException {
      out.writeByte(ConstantPool.CONSTANT_Long);
      out.writeLong(val);
    }

  

    @Override
    public int size() {
      return 2;
    }

  

    @Override
    public String toString() {
      return pos + " ConstantLong: tag " + ConstantPool.CONSTANT_Long + ", bytes: " + val;
    }


}
