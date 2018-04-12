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
 * @aspect EnclosingMethodAttribute
 * @declaredat /Users/BMW/Downloads/extendj/java5/backend/EnclosingMethodAttribute.jrag:41
 */
 class EnclosingMethod extends Attribute {
  
    public EnclosingMethod(ConstantPool cp, TypeDecl typeDecl) {
      super(cp, "EnclosingMethod");
      u2(cp.addClass(typeDecl.enclosingType().constantPoolName()));
      BodyDecl b = typeDecl.enclosingBodyDecl();
      if (b instanceof MethodDecl) {
        MethodDecl m = (MethodDecl) b;
        u2(cp.addNameAndType(m.name(), m.descName()));
      } else if (b instanceof ConstructorDecl) {
        ConstructorDecl m = (ConstructorDecl) b;
        u2(cp.addNameAndType(m.name(), m.descName()));
      } else {
        u2(0);
      }
    }


}
