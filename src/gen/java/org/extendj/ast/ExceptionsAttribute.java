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
 * @aspect Attributes
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/Attributes.jrag:245
 */
public class ExceptionsAttribute extends Attribute {
  
    public ExceptionsAttribute(CodeGeneration gen, ExceptionHolder m) {
      super(gen.constantPool(), "Exceptions");
      u2(m.getNumException());
      for (int i = 0; i < m.getNumException(); i++) {
        u2(gen.constantPool().addClass(m.getException(i).type().constantPoolName()));
      }
    }


}
