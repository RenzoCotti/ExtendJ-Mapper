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
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java7/backend/StringsInSwitch.jrag:66
 */
 class CaseGroup extends java.lang.Object {
  
    int lbl;

  
    int hashCode;

  
    java.util.List<CaseLbl> lbls = new LinkedList<CaseLbl>();

  

    public CaseGroup(SwitchStmt ss, int hash) {
      lbl = ss.hostType().constantPool().newLabel();
      hashCode = hash;
    }

  

    public void addCase(CaseLbl lbl) {
      lbls.add(lbl);
    }


}
