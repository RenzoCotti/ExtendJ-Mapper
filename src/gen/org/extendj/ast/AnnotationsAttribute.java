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
 * @aspect AnnotationsCodegen
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java5/backend/AnnotationsCodegen.jrag:80
 */
 class AnnotationsAttribute extends Attribute {
  
    public AnnotationsAttribute(ConstantPool cp, Collection<Annotation> annotations, String name) {
      super(cp, name);
      u2(annotations.size());
      for (Annotation annotation : annotations) {
        annotation.appendAsAttributeTo(this);
      }
    }


}
