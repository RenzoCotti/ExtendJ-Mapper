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
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/ConstantPool.jrag:367
 */
public class ConstantNameAndType extends CPInfo {
  
    private int name;

  
    private int type;

  

    public ConstantNameAndType(int name, int type) {
      this.name = name;
      this.type = type;
    }

  

    @Override
    public void emit(DataOutputStream out) throws IOException {
      out.writeByte(ConstantPool.CONSTANT_NameAndType);
      out.writeChar(name);
      out.writeChar(type);
    }

  

    @Override
    public String toString() {
      return pos + " NameAndType: tag " + ConstantPool.CONSTANT_NameAndType + ", name_index: "
          + name + ", descriptor_index: " + type;
    }


}
