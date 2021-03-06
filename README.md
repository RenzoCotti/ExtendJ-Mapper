
Bytecode to source code mapper
===============================

This is an extension for ExtendJ which allows for bytecode to source code mapping with a finer granularity,
meaning it would be possible to find the precise portion of Java code that generated a certain bytecode instruction.

How to use ExtendJ
=============
Inside the main folder, run: `./gradlew --rerun-tasks` to generate extendj.jar, the compiler.

Then, run `java -jar extendj.jar file_to_compile.java` to compile a certain file using ExtendJ.

How to use the testing suite
========================

First, compile a file using ExtendJ
Then, run `javap -verbose -c compiled_file.class > decompiled_file.txt` to decompile the .class file.

At this point, go inside `bytecode-checker/src/` and run `javac Main.java` to compile the files of the testing suite.
Then, run `java Main [print|missing|percent|source] path_to_decompiled_file.txt path_to_original_file.java`.

A few notes:
- feeding the original .java file is necessary only for the `source` option.
- `print` just prints information derived from the class file in a structured way
- `missing` prints the bytecode instructions that do not have relevant information inside the PositionTable
- `percent` prints the percentage of coverage (bytecode instruction mapped/total bytecode instructions)
- `source` allows for interactive stepping from bytecode instruction to bytecode instruction, while printing the corresponding source code fragment.








(what follows is the original ExtendJ readme)

ExtendJ
========

The JastAdd extensible Java compiler.

License & Copyright
-------------------

* Copyright (c) 2005-2008, Torbj&ouml;rn Ekman
* Copyright (c) 2005-2016, ExtendJ Committers

All rights reserved.

ExtendJ is covered by the Modified BSD License. The full license text is
distributed with this software. See the `LICENSE` file.

Tools Used
----------

ExtendJ uses these libraries:

* JastAdd2 R20130212, Copyright (c) 2005-2013, The JastAdd Team. JastAdd2 is
  covered by the Modified BSD License. See the file `licenses/JastAdd2-BSD` for
the full license text.
* Beaver 0.9.11, Copyright (c) 2003-2011 Alexander Demenchuk. Beaver is covered
  by the Modified BSD License. See the file `licenses/Beaver-BSD` for the full
license text.
* JFlex 1.4.3, Copyright (c) 1998-2009 Gerwin Klein. JFlex is covered by the
  GNU General Public License. See the file `licenses/JFlex-GPL` for the full
license text.
* JastAddParser, Copyright (c) 2005, The JastAdd Team. JastAddParser is covered
  by the Modified BSD License. See the file `licenses/JastAddParser-BSD` for
the full license text.
* RagDoll R20120208, Copyright (c) 2011-2012 Jesper &Ouml;qvist. RagDoll is
  covered by the GNU General Public License Version 2, with the Classpath
Exception. See the file `licenses/RagDoll-GPL` for the full license text.

The only library used by ExtendJ at runtime is the Beaver runtime component
`beaver-rt.jar`.

Building
--------

ExtendJ is built using Apache Ant. Each module has it's own Ant script, and
there is a toplevel Ant script that contains targets to build ExtendJ with
support for various versions of Java.  The default target will build ExtendJ
for the highest supported Java version.

If you have Ant installed you can get a list of available build targets by
entering the following in a terminal:

    $ ant -p

Running
-------

Usage:

    java JavaCompiler <options> <source files>
      -verbose                  Output messages about what the compiler is doing
      -classpath <path>         Specify where to find user class files
      -sourcepath <path>        Specify where to find input source files
      -bootclasspath <path>     Override location of bootstrap class files
      -extdirs <dirs>           Override location of installed extensions
      -d <directory>            Specify where to place generated class files
      -nowarn                   Disable warning messages
      -help                     Print a synopsis of standard options
      -version                  Print version information

Extensions
----------

ExtendJ is intended to be an extensible compiler, however right now we are
changing things in ExtendJ rapidly and breaking backward-compatibility.

The ExtendJ API up to version 7.1 was mostly much unchanged for several years.
Since version 7.1 though many things have changed in ExtendJ in order to
remove side effects, fix errors, and make the code more understandable. The
next release should be much more stable, but right now ExtendJ is changing
very much. Most of these non-compatible changes have happened since we moved
the main development code to bitbucket. What you see in the bitbucket
repository should be considered unstable.

[See the extension migration guide][1] for more information about migrating
an extension from an older version of ExtendJ to the latest development
version.

Development
-----------

Some useful scripts for ExtendJ development can be found at [the JJScripts
repository][2].

###Coding Style

*Note: The source code of ExtendJ does not fully follow this style guide yet.
We are in a conversion process to get the code consistent, but it takes a lot of
work.*

For the most part, JastAdd code should follow [Google's Java Style Guide][3].
JastAdd code should use a 100 character maximum line width, two-space
indentation, as in the linked style guide. Line breaking rules are also
generally the same.

Some things to note about JastAdd code:

* Don't let synthesized and inherited attributes share the same name. That is a
  sure way to cause unexpected behaviour.
* The `refine` constructs can cause lines to be very long, so it's a good idea
  to consistently always insert a line break after aspect name part of a refinement.
* If you have a long equation, prefer to add a line break after the first equals
  sign.

###Debugging

If ExtendJ should generate faulty bytecode there are a number of different
tools that can be used to diagnose the problem.

* `javap` comes with the JDK
* `asm` can be downloaded from http://asm.ow2.org/

`javap` can be used to disassemble compiled bytecode:

    $ javap -verbose -c Test.class

ASM by OW2 Consortium can be used for advanced instrumentation and analysis
of bytecode. There is also a useful plugin for eclipse called
"Bytecode Outline" from OW2.

[1]: https://bitbucket.org/extendj/extendj/src/HEAD/ExtensionMigrationGuide.md?at=master
[2]: https://bitbucket.org/joqvist/jjscripts
[3]: http://google.github.io/styleguide/javaguide.html
