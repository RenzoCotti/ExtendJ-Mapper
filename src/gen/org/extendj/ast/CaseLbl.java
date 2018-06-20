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
 * @aspect StringsInSwitch
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:84
 */
 class CaseLbl extends java.lang.Object {
  
    int lbl;

  
    int serial;

  
    String value;

  
    java.util.List<Stmt> stmts = new ArrayList<Stmt>();

  

    CaseLbl(int lbl) {
      this.lbl = lbl;
    }

  

    CaseLbl(ConstCase cc, CodeGeneration gen) {
      lbl = cc.label();
      value = cc.getValue().constant().stringValue();
    }

  

    void addStmt(Stmt stmt) {
      stmts.add(stmt);
    }

  

    /**
     * Code generation for case label.
     */
    void createBCode(CodeGeneration gen) {
      for (Stmt stmt : stmts) {
        stmt.createBCode(gen);
      }
    }


}
