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
 * $Id: GenModelUtil.java,v 1.2 2005/05/10 21:22:59 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.JavaConventions;

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
  
  // Interprets escaped characters within the string according to Java
  // literal rules, with two exceptions: an unescaped " does not terminate
  // the string, and a \ not followed by b, t, n, f, r, ", ', u, or an octal
  // digit is taken literally, not as an error
  public static String parseString(String s)
  {
    if (s == null) return null;

    int len = s.length();
    StringBuffer result = new StringBuffer(len);
    for (int i = 0; i < len; i++)
    {
      char c = s.charAt(i);
      if (c == '\\' && len > i + 1)
      {
        if ("btnfr\"\'\\".indexOf(s.charAt(i + 1)) != -1)
        {
          c = parseChar(s.substring(i, i + 2));
          i++;
        }
        else if (s.charAt(i + 1) == 'u' && len > i + 5)
        {
          c = parseChar(s.substring(i, i + 6));
          i += 5;
        }
        else
        {
          int j;  // will point at the character after 0 to 3 octal digits
          for (j = i + 1; j < len && j - i < 4; j++)
          {
            char digit = s.charAt(j);
            if  (digit < '0' || digit > '7') break;
          }
          c = parseChar(s.substring(i, j));
          i = j - 1;
        }
      }
      result.append(c);
    }
    return result.toString();
  }
  
  // Interprets escaped characters according to Java literal rules, with one
  // exception: a single \ is taken literally, not as an error.
  public static char parseChar(String c)
  {
    if (c == null) throw new IllegalArgumentException("null");

    if ("\\b".equals(c)) return '\b';
    if ("\\t".equals(c)) return '\t';
    if ("\\n".equals(c)) return '\n';
    if ("\\f".equals(c)) return '\f';
    if ("\\r".equals(c)) return '\r';
    if ("\\\"".equals(c)) return '\"';
    if ("\\\'".equals(c)) return '\'';
    if ("\\\\".equals(c)) return '\\';

    if (c.startsWith("\\u") && c.length() == 6)
    {
      int i = Integer.parseInt(c.substring(2), 16);
      if (i >= Character.MIN_VALUE && i <= Character.MAX_VALUE)
      {
        return (char)i;
      }
    }
    else if (c.length() >= 2 && c.length() <= 4 && c.charAt(0) == '\\')
    {
      int i = Integer.parseInt(c.substring(1), 8);
      if (i >= Character.MIN_VALUE && i <= Character.MAX_VALUE)
      {
        return (char)i;
      }
    }

    if (c.length() != 1) throw new IllegalArgumentException(c);
    return c.charAt(0);
  }
  
  public static String validJavaIdentifier(String name)
  {
    if (name == null || name.length() == 0)
    {
      return name;
    }
    else if (JavaConventions.validateIdentifier(name).isOK())
    {
      return name;
    }

    StringBuffer result = new StringBuffer();
    if (Character.isJavaIdentifierStart(name.charAt(0)))
    {
      result.append(name.charAt(0));
    }
    else
    {
      result.append('_');
      if (Character.isJavaIdentifierPart(name.charAt(0)))
      {
        result.append(name.charAt(0));
      }
    }
    for (int i = 1; i < name.length(); ++ i)
    {
      if (Character.isJavaIdentifierPart(name.charAt(i)))
      {
        result.append(name.charAt(i));
      }
    }

    return result.length() == 0 ? "_" : result.toString();
  }
  
  public static String capName(String name)
  {
    if (name.length() == 0)
      return name;
    else
      return name.substring(0,1).toUpperCase() + name.substring(1);
  }

  public static String uncapName(String name)
  {
    if (name.length() == 0)
      return name;
    else
      return name.substring(0,1).toLowerCase() + name.substring(1);
  }

  public static String uncapPrefixedName(String name, boolean forceDifferent)
  {
    // lower all except the last upper case character if there are
    // more than one upper case characters in the beginning.
    // e.g. XSDElementContent -> xsdElementContent
    // However if the whole string is uppercase, the whole string
    // is turned into lower case.
    // e.g. CPU -> cpu
    if (name.length() == 0)
    {
      return name;
    }
    else 
    {
      String lowerName = name.toLowerCase();
      int i;
      for (i = 0; i < name.length(); i++) 
      {
        if (name.charAt(i) == lowerName.charAt(i)) 
        {
          break;
        }
      }
      if (i > 1 && i < name.length()) 
      {
        --i;
      }
      String prefix = name.substring(0, i);
      String lowerCasePrefix = prefix.toLowerCase();
      if (forceDifferent && lowerCasePrefix.equals(prefix))
      {
        lowerCasePrefix = "_" + lowerCasePrefix;
      }
      return lowerCasePrefix + name.substring(i);
    }
  }

  public static String safeName(String name)
  {
    if (GenModelUtil.isJavaReservedWord(name)) return name + "_";
    return name;
  }
  
  public static String format(String name, char separator, String prefix, boolean includePrefix)
  {
    List parsedName = new ArrayList();

    if (prefix != null && 
          name.startsWith(prefix) && 
          name.length() > prefix.length() && Character.isUpperCase(name.charAt(prefix.length())))
    {
      name = name.substring(prefix.length());
      if (includePrefix)
      {
        parsedName = parseName(prefix, '_');
      }
    }

    if (name.length() != 0) parsedName.addAll(parseName(name, '_'));

    StringBuffer result = new StringBuffer();

    for (Iterator nameIter = parsedName.iterator(); nameIter.hasNext(); )
    {
      String nameComponent = (String)nameIter.next();
      result.append(nameComponent);

      if (nameIter.hasNext() && nameComponent.length() > 1)
      {
        result.append(separator);
      }
    }

    return result.length() == 0 && prefix != null ? prefix : result.toString();
  }

  /**
   * This method breaks sourceName into words delimited by sourceSeparator and/or mixed-case naming.
   */
  public static List parseName(String sourceName, char sourceSeparator)
  {
    List result = new ArrayList();
    StringBuffer currentWord = new StringBuffer();

    int length = sourceName.length();
    boolean lastIsLower = false;

    for (int index=0; index<length; index++)
    {
      char curChar = sourceName.charAt(index);
      if (Character.isUpperCase(curChar) || (!lastIsLower && Character.isDigit(curChar)) || curChar == sourceSeparator)
      {
        if (lastIsLower || curChar == sourceSeparator)
        {
          result.add(currentWord.toString());
          currentWord = new StringBuffer();
        }
        lastIsLower = false;
      }
      else
      {
        if (!lastIsLower)
        {
          int currentWordLength = currentWord.length();
          if (currentWordLength > 1)
          {
            char lastChar = currentWord.charAt(--currentWordLength);
            currentWord.setLength(currentWordLength);
            result.add(currentWord.toString());
            currentWord = new StringBuffer();
            currentWord.append(lastChar);
          }
        }
        lastIsLower = true;
      }
      if (curChar != sourceSeparator)
      {
        currentWord.append(curChar);
      }
    }

    result.add(currentWord.toString());
    return result;
  }  
}
