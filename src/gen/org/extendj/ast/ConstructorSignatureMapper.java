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
 * @aspect InnerClasses
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/InnerClasses.jrag:500
 */
 class ConstructorSignatureMapper extends java.lang.Object {
  
    public final ConstructorDecl decl;

  

    public ConstructorSignatureMapper(ConstructorDecl decl) {
      this.decl = decl;
    }

  

    @Override
    public int hashCode() {
      return decl.signature().hashCode();
    }

  

    @Override
    public boolean equals(Object o) {
      if (o instanceof ConstructorSignatureMapper) {
        ConstructorSignatureMapper other = (ConstructorSignatureMapper) o;
        return decl.signature().equals(other.decl.signature());
      }
      return false;
    }


}
