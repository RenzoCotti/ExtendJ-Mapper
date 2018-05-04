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
 * @aspect Converter
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/java4/backend/ASTToJSON.jrag:3
 */
public class Counter extends java.lang.Object {
  
		private static HashMap<ASTNode,Integer> IDS = new HashMap<ASTNode, Integer>();

  
		private static int CURRENT_ID = 0;

  
		public static int getId(ASTNode n) {
			Integer id = IDS.get(n);
			if (id==null) {
				id = CURRENT_ID++;
				IDS.put(n, id);
			}
			return id;
		}


}
