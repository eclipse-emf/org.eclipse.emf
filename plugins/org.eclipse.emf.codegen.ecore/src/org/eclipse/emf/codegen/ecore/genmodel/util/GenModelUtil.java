/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenModelUtil.java,v 1.1 2005/02/15 20:26:33 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class contains convenient static methods for EMF code generation.
 * <p>
 * This class, like much of the code in this plug-in, is currently undergoing change and should not be considered API.
 */
public class GenModelUtil
{
  // Suppress default constructor for noninstantiability.
  //
  private GenModelUtil()
  {
  }

  private static Set javaReservedWords;

  /**
   * Returns the set of all Java's keywords and textual literals, as of Java 1.4.
   */
  public static Set getJavaReservedWords()
  {
    if (javaReservedWords == null)
    {
      Set result = new HashSet(100);
      result.add("abstract");
      result.add("assert");
      result.add("boolean");
      result.add("break");
      result.add("byte");
      result.add("case");
      result.add("catch");
      result.add("char");
      result.add("class");
      result.add("const");
      result.add("continue");
      result.add("default");
      result.add("do");
      result.add("double");
      result.add("else");
      result.add("extends");
      result.add("false");
      result.add("final");
      result.add("finally");
      result.add("float");
      result.add("for");
      result.add("goto");
      result.add("if");
      result.add("implements");
      result.add("import");
      result.add("instanceof");
      result.add("int");
      result.add("interface");
      result.add("long");
      result.add("native");
      result.add("new");
      result.add("null");
      result.add("package");
      result.add("private");
      result.add("protected");
      result.add("public");
      result.add("return");
      result.add("short");
      result.add("static");
      result.add("strictfp");
      result.add("super");
      result.add("switch");
      result.add("synchronized");
      result.add("this");
      result.add("throw");
      result.add("throws");
      result.add("transient");
      result.add("true");
      result.add("try");
      result.add("void");
      result.add("volatile");
      result.add("while");
      javaReservedWords = Collections.unmodifiableSet(result);
    }
    return javaReservedWords;
  }

  private static Set javaDefaultTypes;

  /**
   * Returns the short names of the primitives and types in java.lang (i.e. those
   * that don't need qualification).
   */
  public static Set getJavaDefaultTypes()
  {
    if (javaDefaultTypes == null)
    {
      Set result = new HashSet();
      result = new HashSet(100);
      result.add("AbstractMethodError");
      result.add("ArithmeticException");
      result.add("ArrayIndexOutOfBoundsException");
      result.add("ArrayStoreException");
      result.add("Boolean");
      result.add("Byte");
      result.add("Character");
      result.add("Class");
      result.add("ClassCastException");
      result.add("ClassCircularityError");
      result.add("ClassFormatError");
      result.add("ClassLoader");
      result.add("ClassNotFoundException");
      result.add("CloneNotSupportedException");
      result.add("Cloneable");
      result.add("Comparable");
      result.add("Compiler");
      result.add("Double");
      result.add("Error");
      result.add("Exception");
      result.add("ExceptionInInitializerError");
      result.add("Float");
      result.add("FloatingDecimal");
      result.add("IllegalAccessError");
      result.add("IllegalAccessException");
      result.add("IllegalArgumentException");
      result.add("IllegalMonitorStateException");
      result.add("IllegalStateException");
      result.add("IllegalThreadStateException");
      result.add("IncompatibleClassChangeError");
      result.add("IndexOutOfBoundsException");
      result.add("InheritableThreadLocal");
      result.add("InstantiationError");
      result.add("InstantiationException");
      result.add("Integer");
      result.add("InternalError");
      result.add("InterruptedException");
      result.add("LinkageError");
      result.add("Long");
      result.add("Math");
      result.add("NegativeArraySizeException");
      result.add("NoClassDefFoundError");
      result.add("NoSuchFieldError");
      result.add("NoSuchFieldException");
      result.add("NoSuchMethodError");
      result.add("NoSuchMethodException");
      result.add("NullPointerException");
      result.add("Number");
      result.add("NumberFormatException");
      result.add("Object");
      result.add("OutOfMemoryError");
      result.add("Package");
      result.add("Process");
      result.add("Runnable");
      result.add("Runtime");
      result.add("RuntimeException");
      result.add("RuntimePermission");
      result.add("SecurityException");
      result.add("SecurityManager");
      result.add("Short");
      result.add("StackOverflowError");
      result.add("String");
      result.add("StringBuffer");
      result.add("StringIndexOutOfBoundsException");
      result.add("System");
      result.add("Thread");
      result.add("ThreadDeath");
      result.add("ThreadGroup");
      result.add("ThreadLocal");
      result.add("Throwable");
      result.add("UnknownError");
      result.add("UnsatisfiedLinkError");
      result.add("UnsupportedClassVersionError");
      result.add("UnsupportedOperationException");
      result.add("VerifyError");
      result.add("VirtualMachineError");
      result.add("Void");
      result.add("boolean");
      result.add("byte");
      result.add("char");
      result.add("double");
      result.add("float");
      result.add("int");
      result.add("long");
      result.add("short");
      javaDefaultTypes = Collections.unmodifiableSet(result);
    }
    return javaDefaultTypes;
  }

  /**
   * Tests whether a given string is a Java reserved word.
   */
  public static boolean isJavaReservedWord(String s)
  {
    return getJavaReservedWords().contains(s);
  }

  /**
   * Tests whether the given string is the name of a primitive or java.lang type.
   */
  public static boolean isJavaDefaultType(String s)
  {
    return getJavaDefaultTypes().contains(s);
  }

  /**
   * Tests whether the given string is the name of a java.lang type.
   */
  public static boolean isJavaLangType(String s)
  {
    return getJavaDefaultTypes().contains(s) && Character.isUpperCase(s.charAt(0));
  }

  /**
   * Tests whether the given string is the name of a primitive type.
   */
  public static boolean isJavaPrimitiveType(String s)
  {
    return getJavaDefaultTypes().contains(s) && Character.isLowerCase(s.charAt(0));
  }
}
