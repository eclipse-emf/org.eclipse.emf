/**
 * <copyright>
 *
 * Copyright (c) 2002-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * CodeGenUtil.java,v 1.27 2007/05/15 22:33:38 emerks Exp
 */
package org.eclipse.emf.codegen.util;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.osgi.util.ManifestElement;

import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;

/**
 * This class contains convenient static methods for EMF code generation.
 * Methods in the {@link CodeGenUtil.EclipseHelper EclipseHelper} inner class may only be used running under Eclipse.
 * The same applies to those that are deprecated and replaced by methods in the inner class, too.
 * All other methods can be used in a stand-alone scenario, too.
 * <p>
 * This class, like much of the code in this plug-in, is currently undergoing change and should not be considered API.
 */
public class CodeGenUtil
{
  private static Set<String> javaReservedWords;

  /**
   * Returns the set of all Java's keywords and textual literals, as of Java 5.0.
   */
  public static Set<String> getJavaReservedWords()
  {
    if (javaReservedWords == null)
    {
      Set<String> result = new HashSet<String>(100);
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
      result.add("enum");
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

  private static Set<String> javaDefaultTypes;

  /**
   * Returns the short names of the primitives and types in java.lang (i.e. those
   * that don't need qualification).
   */
  public static Set<String> getJavaDefaultTypes()
  {
    if (javaDefaultTypes == null)
    {
      Set<String> result = new HashSet<String>(100);
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
      result.add("Deprecated");
      result.add("Enum");
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
      result.add("Override");
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
      result.add("StringBuilder");
      result.add("StringIndexOutOfBoundsException");
      result.add("SuppressWarnings");
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
    StringBuilder result = new StringBuilder(len);
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
    else if (EMFPlugin.IS_ECLIPSE_RUNNING && EclipseHelper.isValidJavaIdentifier(name))
    {
      return name;
    }

    StringBuilder result = new StringBuilder();
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
  
  /**
   * @since 2.4
   */
  public static String capName(String name, Locale locale)
  {
    if (name.length() == 0)
      return name;
    else
      return name.substring(0,1).toUpperCase(locale) + name.substring(1);
  }

  public static String capName(String name)
  {
    return capName(name, Locale.getDefault());
  }

  /*
   * @since 2.4
   */
  public static String uncapName(String name, Locale locale)
  {
    if (name.length() == 0)
      return name;
    else
      return name.substring(0,1).toLowerCase(locale) + name.substring(1);
  }
  public static String uncapName(String name)
  {
    return uncapName(name, Locale.getDefault());
  }

  /**
   * @since 2.4
   */
  public static String uncapPrefixedName(String name, boolean forceDifferent, Locale locale)
  {
    // lower all except the last upper case character if there are
    // more than one upper case characters in the beginning.
    // e.g. XSDElementContent -> xsdElementContent
    // However if the whole string is upper case, the whole string
    // is turned into lower case.
    // e.g. CPU -> cpu
    if (name.length() == 0)
    {
      return name;
    }
    else 
    {
      String lowerName = name.toLowerCase(locale);
      int i;
      for (i = 0; i < name.length(); i++) 
      {
        if (name.charAt(i) == lowerName.charAt(i)) 
        {
          break;
        }
      }
      if (i > 1 && i < name.length() && !Character.isDigit(name.charAt(i))) 
      {
        --i;
      }
      String prefix = name.substring(0, i);
      String lowerCasePrefix = prefix.toLowerCase(locale);
      if (forceDifferent && lowerCasePrefix.equals(prefix))
      {
        lowerCasePrefix = "_" + lowerCasePrefix;
      }
      return lowerCasePrefix + name.substring(i);
    }
  }

  public static String uncapPrefixedName(String name, boolean forceDifferent)
  {
    return uncapPrefixedName(name, forceDifferent, Locale.getDefault());
  }

  public static String safeName(String name)
  {
    if (CodeGenUtil.isJavaReservedWord(name)) return name + "_";
    return name;
  }

  /**
   * @since 2.6
   */
  public static String upperName(String name, Locale locale)
  {
    return format(name, '_', null, false, true).toUpperCase(locale);
  }

  /**
   * @since 2.6
   */
  public static String upperName(String name)
  {
    return upperName(name, Locale.getDefault());
  }

  /**
   * @deprecated In 2.2. Please use {@link #format(String, char, String, boolean, boolean)} instead.
   */
  @Deprecated
  public static String format(String name, char separator, String prefix, boolean includePrefix)
  {
    return format(name, separator, prefix, includePrefix, false);
  }

  /**
   * Formats a name by parsing it into words separated by underscores and/or mixed-casing and then
   * recombining them using the specified separator. A prefix can also be given to be recognized as
   * a separate word or to be trimmed. Leading underscores can be ignored or can cause a leading
   * separator to be prepended.
   * @since 2.2
   */
  public static String format(String name, char separator, String prefix, boolean includePrefix, boolean includeLeadingSeparator)
  {
    String leadingSeparators = includeLeadingSeparator ? getLeadingSeparators(name, '_') : null;
    if (leadingSeparators != null)
    {
      name = name.substring(leadingSeparators.length());
    }

    List<String> parsedName = new ArrayList<String>();
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

    StringBuilder result = new StringBuilder();
    
    for (Iterator<String> nameIter = parsedName.iterator(); nameIter.hasNext(); )
    {
      String nameComponent = nameIter.next();
      result.append(nameComponent);

      if (nameIter.hasNext() && nameComponent.length() > 1)
      {
        result.append(separator);
      }
    }

    if (result.length() == 0 && prefix != null)
    {
      result.append(prefix);
    }
    return leadingSeparators != null ? "_" + result.toString() : result.toString();
  }

  private static String getLeadingSeparators(String name, char separator)
  {
    int i = 0;
    for (int len = name.length(); i < len && name.charAt(i) == separator; i++)
    {
      // the for loop's condition finds the separator 
    }
    return i != 0 ? name.substring(0, i) : null;
  }

  /**
   * This method breaks sourceName into words delimited by separator and/or mixed-case naming.
   */
  public static List<String> parseName(String sourceName, char separator)
  {
    List<String> result = new ArrayList<String>();
    if (sourceName != null)
    {
      StringBuilder currentWord = new StringBuilder();
      boolean lastIsLower = false;
      for (int index = 0, length = sourceName.length(); index < length; ++index)
      {
        char curChar = sourceName.charAt(index);
        if (Character.isUpperCase(curChar) || (!lastIsLower && Character.isDigit(curChar)) || curChar == separator)
        {
          if (lastIsLower && currentWord.length() > 1 || curChar == separator && currentWord.length() > 0)
          {
            result.add(currentWord.toString());
            currentWord = new StringBuilder();
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
              currentWord = new StringBuilder();
              currentWord.append(lastChar);
            }
          }
          lastIsLower = true;
        }

        if (curChar != separator)
        {
          currentWord.append(curChar);
        }
      }
  
      result.add(currentWord.toString());
    }
    return result;
  }  

  /**
   * @deprecated in 2.2. Please use {@link CodeGenUtil.EclipseUtil#isInJavaOutput} instead. 
   */
  @Deprecated
  public static boolean isInJavaOutput(IResource resource)
  {
    return EclipseUtil.isInJavaOutput(resource);
  }  

  /**
   * This is a progress monitor that prints the progress information to a stream.
   * @deprecated As of EMF 2.2, moved to {@link CodeGenUtil.EclipseUtil.StreamProgressMonitor EcoreUtil}.
   */
  @Deprecated
  public static class StreamProgressMonitor extends EclipseUtil.StreamProgressMonitor
  {
    public StreamProgressMonitor(PrintStream printStream)
    {
      super(printStream);
    }
  }

  /**
   * @deprecated in 2.2. Please use {@link CodeGenUtil.EclipseUtil#findOrCreateContainer(IPath, boolean, IPath, IProgressMonitor)} instead. 
   */
  @Deprecated
  public static IContainer findOrCreateContainer
    (IPath path, boolean forceRefresh, IPath localLocation, IProgressMonitor progressMonitor) throws CoreException
  {
    return EclipseUtil.findOrCreateContainer(path, forceRefresh, localLocation, progressMonitor);
  }

  /**
   * @deprecated in 2.2. Please use {@link CodeGenUtil.EclipseUtil#findOrCreateContainer(IPath, boolean, IProjectDescription, IProgressMonitor)} instead. 
   */
  @Deprecated
  public static IContainer findOrCreateContainer
    (IPath path, boolean forceRefresh, IProjectDescription projectDescription, IProgressMonitor progressMonitor) throws CoreException
  {
    return EclipseUtil.findOrCreateContainer(path, forceRefresh, projectDescription, progressMonitor);
  }

  /**
   * @deprecated in 2.2. Please use {@link CodeGenUtil.EclipseUtil#getClasspathPaths} instead. 
   */
  @Deprecated
  public static List<String> getClasspathPaths(String pluginID) throws JETException
  {
    return EclipseUtil.getClasspathPaths(pluginID);
  }

  /**
   * @deprecated in 2.2. Please use {@link CodeGenUtil.EclipseUtil#addClasspathEntries(Collection, String, String)} instead. 
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  public static void addClasspathEntries(Collection<?> classpathEntries, String variableName, String pluginID) throws JETException
  {
    EclipseUtil.addClasspathEntries((Collection<IClasspathEntry>)classpathEntries, variableName, pluginID);
  }

  /**
   * @deprecated in 2.2. Please use {@link CodeGenUtil.EclipseUtil#addClasspathEntries(Collection, String)} instead. 
   */
  @SuppressWarnings("unchecked")
  @Deprecated
  public static void addClasspathEntries(Collection<?> classpathEntries, String pluginID) throws Exception
  {
    EclipseUtil.addClasspathEntries((Collection<IClasspathEntry>)classpathEntries, pluginID);
  }

  /**
   * Returns the package name for a qualified class name, i.e., a substring
   * from the first char until the last &quot;.&quot;.  If the argument is 
   * <tt>null</tt> or a non-qualified name, this method returns <tt>null</tt>. 
   * @param qualifiedClassName
   * @return String
   */
  public static String getPackageName(String qualifiedClassName)
  {
    if (qualifiedClassName != null)
    {
      int index = qualifiedClassName.lastIndexOf('.');
      if (index >= 0)
      {
        return qualifiedClassName.substring(0, index);
      }
    }
    return null;
  }

  /**
   * Returns the simple class name for a qualified class name, i.e., a substring
   * from starting after the last &quot;.&quot;.  If the argument is 
   * a non-qualified name, this method returns the argument. 
   * @param qualifiedClassName
   * @return String
   */
  public static String getSimpleClassName(String qualifiedClassName)
  {
    if (qualifiedClassName != null)
    {
      int index = qualifiedClassName.lastIndexOf('.');
      if (index >= 0)
      {
        return qualifiedClassName.substring(index + 1);
      }
    }
    return qualifiedClassName;
  }

  public static Monitor createMonitor(Monitor monitor, int ticks)
  {
    return
      EMFPlugin.IS_ECLIPSE_RUNNING ?
        EclipseHelper.createMonitor(monitor, ticks) :
        monitor;
  }

  protected static final String MATCH_LINE_SEPARATOR = "(\n\r?|\r\n?)";
  protected static final Pattern BRACE_LINE_PATTERN = Pattern.compile("(\\s*" + MATCH_LINE_SEPARATOR + "\\s*\\{\\s*?)" + MATCH_LINE_SEPARATOR); // }

  public static String convertFormat(final String tabReplacement, boolean convertToStandardBraceStyle, String value)
  {
    if (tabReplacement != null && !"\t".equals(tabReplacement))
    {
      char[] text = value.toCharArray();
      StringBuilder result = new StringBuilder(text.length + text.length / 10);
      boolean blankLine = true;
      int tabCount = 0;
      int previous = 0;
      for (int i = 0, length = text.length; i < length; ++i)
      {
        char c = text[i];
        if (c == '\t')
        {
          if (blankLine)
          {
            ++tabCount;
          }          
        }
        else
        {
          if (tabCount > 0)
          {
            result.append(text, previous, i - tabCount - previous);
            for (int j = 0; j < tabCount; ++j)
            {
              result.append(tabReplacement);
            }
            previous = i;
            tabCount = 0;
          }
          blankLine = c == '\n' || c == '\r';
        }
      }
      result.append(text, previous, text.length - previous);
      value = result.toString();
    }

    if (convertToStandardBraceStyle)
    {
      FindAndReplace findAndReplaceLineWithJustABrace = 
        new FindAndReplace(BRACE_LINE_PATTERN)
        {
          @Override
          public boolean handleMatch(int offset, Matcher matcher)
          {
            if (matcher.groupCount() >= 1)
            {
              int begin = offset + matcher.start(1);

              // Don't do replacement if we just did one, or if previous line
              // ended with a semicolon.
              //
              if (current != 0 && (begin <= current || string.charAt(begin - 1) == ';'))
              {
                return true;
              }

              // Don't do replacement if previous line ended with a comment.
              //
              for (int i = begin - 1; i >= current; --i)
              {
                char character = string.charAt(i);
                if (character == '\n' || character == '\r' || i == current)
                {
                  boolean slash = false;
                  while (++i < begin)
                  {
                    character = string.charAt(i);
                    if (character == '/')
                    {
                      if (slash)
                      {
                        return true;
                      }
                      slash = true;
                    }
                    else
                    {
                      slash = false;
                    }
                  }

                  break;
                }
              }

              int end = offset + matcher.end(1);
              replace(begin, end, " {"); // }
            }
            return true;
          }
        };
      value = findAndReplaceLineWithJustABrace.apply(value);
    }

    return value;
  }

  private static abstract class FindAndReplace
  {
    protected Pattern pattern;
    protected String string;
    protected StringBuilder stringBuilder;
    protected int current;

    public FindAndReplace(Pattern pattern)
    {
      this.pattern = pattern;
    }

    public String apply(String string)
    {
      current = 0;
      this.string = string;
      this.stringBuilder = new StringBuilder();

      for (int start = 0, end = string.length(); start < end; )
      {
        Matcher matcher = pattern.matcher(string.subSequence(start, end));
        if (matcher.find())
        {
          if (!handleMatch(start, matcher))
          {
            break;
          }
          start += matcher.end();
        }
        else
        {
          break;
        }
      }

      stringBuilder.append(string.substring(current));
      return stringBuilder.toString();
    }

    public void replace(int begin, int end, String replacement)
    {
      stringBuilder.append(string.substring(current, begin));
      stringBuilder.append(replacement);
      current = end;
    }

    public abstract boolean handleMatch(int offset, Matcher matcher);
  }

  /**
   * Performs escape encoding on the given string so that it can be represented using 1-byte characters.
   * Any characters higher than 0xFF are replaced with an escape of the form \\uXXXX, where XXXX is the
   * four-digit hex representation of the Unicode code point.
   */
  public static String unicodeEscapeEncode(String unicode)
  {
    StringBuilder result = new StringBuilder(unicode.length());
    for (int i = 0, size = unicode.length(); i < size; ++i)
    {
      char character = unicode.charAt(i);
      if (character > '\u00ff')
      {
        result.append("\\u");
        String hex = Integer.toString(character, 16);
        for (int j = hex.length(); j < 4; ++j)
        {
          result.append("0");
        }
        result.append(hex);
      }
      else
      {
        result.append(character);
      }
    }

    return result.toString();
  }

  public static FacadeHelper instantiateFacadeHelper(String facadeHelperClass)
  {
    try
    {
      Class<?> cls = Class.forName(facadeHelperClass);
      Object object = cls.newInstance();
      if (object instanceof FacadeHelper)
      {
        return (FacadeHelper)object;
      }
    }
    catch (Exception e)
    {
      // Ignore
    }
      
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return EclipseHelper.instantiateRegisteredFacadeHelper(facadeHelperClass);
    }
    return null;
  }

  /**
   * @since 2.5
   */
  public static String validPluginID(String base)
  {
    StringBuffer sb = new StringBuffer(base);
    for (int i = sb.length() - 1; i >= 0; i--)
    {
      char c = sb.charAt(i);
      if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || c == '_' || c == '-' || c == '.')
      {
        //do nothing
      }
      else if (c == ' ')
      {
        sb.setCharAt(i, '_');
      }
      else
      {
        sb.deleteCharAt(i);
      }
    }
    return sb.length() == 0 ? "_": sb.toString();
  }

  public static class EclipseUtil
  {
    public static class StreamProgressMonitor extends NullProgressMonitor
    {
      protected PrintStream printStream;

      public StreamProgressMonitor(PrintStream printStream)
      {
        this.printStream = printStream;
      }

      @Override
      public void beginTask(String name, int totalWork)
      {
        if (name != null && name.length() != 0)
        {
          printStream.println(">>> " + name);
        }
        super.beginTask(name, totalWork);
      }

      @Override
      public void setTaskName(String name)
      {
        if (name != null && name.length() != 0)
        {
          printStream.println("<>> " + name);
        }
        super.setTaskName(name);
      }

      @Override
      public void subTask(String name)
      {
        if (name != null && name.length() != 0)
        {
          printStream.println(">>  " + name);
        }
        super.subTask(name);
      }
    }

    public static boolean isInJavaOutput(IResource resource)
    {
      IProject project = resource.getProject();
      IJavaProject javaProject = JavaCore.create(project);
      try
      {
        if (javaProject.exists() && project != project.getWorkspace().getRoot().findMember(javaProject.getOutputLocation())
          && javaProject.getOutputLocation().isPrefixOf(resource.getFullPath()))
        {
          return true;
        }
      }
      catch (JavaModelException exception)
      {
        CodeGenPlugin.INSTANCE.log(exception);
      }
  
      return false;
    }  

    public static List<String> getClasspathPaths(String pluginID) throws JETException
    {
      List<String> result = new ArrayList<String>();
      try
      {
        Bundle bundle = Platform.getBundle(pluginID);
        String requires = (String)bundle.getHeaders().get(Constants.BUNDLE_CLASSPATH);
        if (requires == null)
        {
          requires = ".";
        }
        ManifestElement[] elements = ManifestElement.parseHeader(Constants.BUNDLE_CLASSPATH, requires);
        if (elements != null)
        {
          for (int i = 0; i < elements.length; ++i)
          {
            ManifestElement element = elements[i];
            String value = element.getValue();
            if (".".equals(value))
            {
              value = "/";
            }
            try
            {
              URL url = bundle.getEntry(value);
              if (url != null)
              {
                URL resolvedURL = FileLocator.resolve(url);
                String resolvedURLString = resolvedURL.toString();
                if (resolvedURLString.endsWith("!/"))
                {
                  resolvedURLString = resolvedURL.getFile();
                  resolvedURLString = resolvedURLString.substring(0,resolvedURLString.length() - "!/".length());
                }
                if (resolvedURLString.startsWith("file:"))
                {
                  result.add(resolvedURLString.substring("file:".length()));
                }
                else
                {
                  result.add(FileLocator.toFileURL(url).getFile());
                }
              }
            }
            catch (IOException exception)
            {
              throw new JETException(exception);
            }
          }
        }
      }
      catch (BundleException exception)
      {
        throw new JETException(exception);
      }
      return result;
    }

    /**
     * An {@link IClasspathAttribute#getName() class path attribute name} 
     * that records the originating plugin ID 
     * for each classpath entry created by 
     * {@link #addClasspathEntries(Collection, String)}
     * and {@link #addClasspathEntries(Collection, String, String)}.
     * @since 2.3
     */
    public static final String PLUGIN_ID_CLASSPATH_ATTRIBUTE_NAME = "plugin_id";

    public static void addClasspathEntries(Collection<IClasspathEntry> classpathEntries, String variableName, String pluginID) throws JETException
    {
      for (ListIterator<String> i = getClasspathPaths(pluginID).listIterator(); i.hasNext(); )
      {
        IPath path = new Path(i.next());
        if (variableName == null)
        {
          classpathEntries.add
            (JavaCore.newLibraryEntry
              (path, null, null, null, new IClasspathAttribute [] { JavaCore.newClasspathAttribute(PLUGIN_ID_CLASSPATH_ATTRIBUTE_NAME, pluginID) } , true));
        }
        else
        {
          String mangledName = variableName + (i.previousIndex() == 0 ? "" : "_" + i.previousIndex());
          try
          {
            JavaCore.setClasspathVariable(mangledName, path, null);
          }
          catch (JavaModelException exception)
          {
            throw new JETException(exception);
          } 
          classpathEntries.add
            (JavaCore.newVariableEntry
               (new Path(mangledName), null, null, null, new IClasspathAttribute [] { JavaCore.newClasspathAttribute(PLUGIN_ID_CLASSPATH_ATTRIBUTE_NAME, pluginID) }, true));
        }
      }
    }

    public static void addClasspathEntries(Collection<IClasspathEntry> classpathEntries, String pluginID) throws JETException
    {
      addClasspathEntries(classpathEntries, null, pluginID);
    }

    public static Monitor createMonitor(IProgressMonitor monitor, int ticks)
    {
      return new BasicMonitor.EclipseSubProgress(monitor, ticks);
    }

    public static IContainer findOrCreateContainer
      (IPath path, boolean forceRefresh, IPath localLocation, IProgressMonitor progressMonitor) throws CoreException
    {
      String projectName = path.segment(0);
      IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
      projectDescription.setLocation(localLocation);
      return findOrCreateContainer(path, forceRefresh, projectDescription, progressMonitor);
    }

    public static IContainer findOrCreateContainer
      (IPath path, boolean forceRefresh, IProjectDescription projectDescription, IProgressMonitor progressMonitor) throws CoreException
    {
      try
      {
        String projectName = path.segment(0);
        progressMonitor.beginTask("", path.segmentCount() + 3);
        progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_ExaminingProject_message", new Object [] { projectName }));
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProject project = workspace.getRoot().getProject(path.segment(0));
  
        if (forceRefresh)
        {
          project.refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(progressMonitor, 1));
        }
        else
        {
          progressMonitor.worked(1);
        }
  
        if (!project.exists())
        {
          project.create(projectDescription, new SubProgressMonitor(progressMonitor, 1));
          project.open(new SubProgressMonitor(progressMonitor, 1));
        }
        else
        {
          project.open(new SubProgressMonitor(progressMonitor, 2));
        }
  
        IContainer container = project;
        for (int i = 1, length = path.segmentCount(); i < length; ++ i)
        {
          IFolder folder = container.getFolder(new Path(path.segment(i)));
          if (!folder.exists())
          {
            folder.create(false, true, new SubProgressMonitor(progressMonitor, 1));
          }
          else
          {
            progressMonitor.worked(1);
          }
  
          container = folder;
        }
  
        return container;
      }
      finally
      {
        progressMonitor.done();
      }
    }
    
    public static String getJavaComplianceLevel(IProject project)
    {
      IJavaProject javaProject = JavaCore.create(project);
      return javaProject.getOption(JavaCore.COMPILER_COMPLIANCE, true);
    }    
  }

  private static class EclipseHelper
  {
    public static Monitor createMonitor(Monitor monitor, int ticks)
    {
      if (monitor instanceof IProgressMonitor)
      {
        return new BasicMonitor.EclipseSubProgress((IProgressMonitor)monitor, ticks);
      }
      else
      {
        return new BasicMonitor.EclipseSubProgress(BasicMonitor.toIProgressMonitor(monitor), ticks);
      }
    }

    public static boolean isValidJavaIdentifier(String name)
    {      
      return JavaConventions.validateIdentifier(name, JavaCore.VERSION_1_5, JavaCore.VERSION_1_5).isOK();
    }

    public static FacadeHelper instantiateRegisteredFacadeHelper(String facadeHelperClass)
    {
      org.eclipse.core.runtime.IExtensionPoint extensionPoint = org.eclipse.core.runtime.Platform.getExtensionRegistry().getExtensionPoint(CodeGenPlugin.ID, "facadeHelpers");
      org.eclipse.core.runtime.IConfigurationElement[] configurationElements = extensionPoint.getConfigurationElements();
      for (int i = 0; i < configurationElements.length; i++)
      {
        if ("facadeHelper".equals(configurationElements[i].getName()) && facadeHelperClass.equals(configurationElements[i].getAttribute("class")))
        {
          try
          {
            Object object = configurationElements[i].createExecutableExtension("class");
            if (object instanceof FacadeHelper)
            {
              return (FacadeHelper)object;
            }
          }
          catch (CoreException e)
          {
            // Ignore
          }
        }
      }
      return null;
    }    
  }
}
