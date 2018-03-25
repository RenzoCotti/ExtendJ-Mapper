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
 * @declaredat /Users/BMW/Dropbox/Bachelor Project/compiler/extendj/java4/backend/Attributes.jrag:141
 */
public class InnerClassesAttribute extends Attribute {
  
    public InnerClassesAttribute(TypeDecl typeDecl) {
      super(typeDecl.constantPool(), "InnerClasses");
      ConstantPool c = typeDecl.constantPool();
      Collection<TypeDecl> list = typeDecl.innerClassesAttributeEntries();
      u2(list.size());
      for (TypeDecl type : list) {
        u2(c.addClass(type.constantPoolName())); // inner_class_info_index
        // outer_class_info_index
        u2(type.isMemberType() ? c.addClass(type.enclosingType().constantPoolName()) : 0);
        u2(type.isAnonymous() ? 0 : c.addUtf8(type.name())); // inner_name_index
        // inner_class_access_flags
        u2(type.isInterfaceDecl() ? (type.flags() | Modifiers.ACC_INTERFACE) : type.flags());
      }
    }


}
