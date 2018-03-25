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
 * @aspect ConstantPool
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/ConstantPool.jrag:430
 */
public class ConstantInteger extends CPInfo {
  
    private int val;

  

    public ConstantInteger(int val) {
      this.val = val;
    }

  

    @Override
    public void emit(DataOutputStream out) throws IOException {
      out.writeByte(ConstantPool.CONSTANT_Integer);
      out.writeInt(val);
    }

  

    @Override
    public String toString() {
      return pos + " ConstantInteger: tag " + ConstantPool.CONSTANT_Integer + ", bytes: " + val;
    }


}
