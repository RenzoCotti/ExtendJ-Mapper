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
 * @aspect PositionTable
 * @declaredat /Users/BMW/Documents/Git/ExtendJ-Mapper/src/jastadd/PositionTable.jrag:2
 */
public class PositionTableAttribute extends Attribute {
  
		public PositionTableAttribute(CodeGeneration gen) {
			super(gen.constantPool(), "PositionTable");
			//u2(gen.positionTable.size());
			for (CodeGeneration.PositionEntry e : gen.positionTable) {
				u2(e.start_pc);
				u2(e.start_line);
				u2(e.start_column);
				u2(e.end_line);
				u2(e.end_column);
			}
		}


}
