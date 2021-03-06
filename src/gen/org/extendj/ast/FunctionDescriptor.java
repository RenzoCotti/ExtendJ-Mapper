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
 * @aspect FunctionDescriptor
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java8/frontend/FunctionDescriptor.jrag:36
 */
 class FunctionDescriptor extends java.lang.Object {
  
    public final java.util.List<TypeDecl> throwsList;

  
    public final MethodDecl method;

  
    public final InterfaceDecl fromInterface;

  

    public FunctionDescriptor(InterfaceDecl fromInterface, MethodDecl method,
        java.util.List<TypeDecl> throwsList) {
      this.fromInterface = fromInterface;
      // TODO: need to handle errors in the function descriptor better!
      // Right now, the method is set to null if the function type is undefined,
      // however this leads to null pointer exceptions all over.
      this.method = method.nonWildcardParameterization();
      this.throwsList = throwsList;
    }

  

    public boolean isGeneric() {
      if (method == null) {
        return false;
      } else {
        return method.isGeneric();
      }
    }

  

    public InterfaceDecl fromInterface() {
      return this.fromInterface;
    }

  

    public String toString() {
      StringBuilder str = new StringBuilder();
      if (method != null) {
        if (method.isGeneric()) {
          GenericMethodDecl genericMethod = method.genericDecl();
          str.append("<" + genericMethod.getTypeParameter(0).prettyPrint());
          for (int i = 1; i < genericMethod.getNumTypeParameter(); i++) {
            str.append(", " + genericMethod.getTypeParameter(i).prettyPrint());
          }
          str.append("> ");
        }
        str.append("(");
        if (method.getNumParameter() > 0) {
          str.append(method.getParameter(0).type().typeName());
          for (int i = 1; i < method.getNumParameter(); i++) {
            str.append(", " + method.getParameter(i).type().typeName());
          }
        }
        str.append(")->");
        str.append(method.type().typeName());

        str.append(" throws ");
        if (throwsList.size() > 0) {
          str.append(throwsList.get(0).typeName());
          for (int i = 1; i < throwsList.size(); i++) {
            str.append(", " + throwsList.get(i).typeName());
          }
        }
      }

      return str.toString();
    }


}
