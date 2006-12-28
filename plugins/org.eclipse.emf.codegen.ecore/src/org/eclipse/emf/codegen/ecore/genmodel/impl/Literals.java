/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: Literals.java,v 1.5 2006/12/28 06:40:38 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;


/**
 * Utility class for converting primitive values, strings, and classes to
 * literals that could appear in code.
 */
public class Literals
{
  // Supress default constructor for non-instantiability.
  private Literals()
  {
    super();
  }

  /**
   * Convenience dispatch method.  If the argument is an instance of
   * <code>Boolean</code>, <code>Byte</code>, <code>Short</code>,
   * <code>Integer</code>, <code>Long</code>, <code>Float</code>,
   * <code>Double</code>, <code>Character</code>, <code>String</code>, 
   * <code>BigDecimal</code>, <code>BigInteger</code>, <code>Date</code>,
   * or <code>Class</code>, the appropriate conversion method is called,
   * with the unwrapped primitive, or the typed object as an argument.
   * Class names are never imported; the qualified name is used.
   */
  public static String toLiteral(Object o)
  {
    return toLiteral(o, null);
  }

  /**
   * Convenience dispatch method.  If the argument is an instance of
   * <code>Boolean</code>, <code>Byte</code>, <code>Short</code>,
   * <code>Integer</code>, <code>Long</code>, <code>Float</code>,
   * <code>Double</code>, <code>Character</code>, <code>String</code>, 
   * <code>BigDecimal</code>, <code>BigInteger</code>, <code>Date</code>,
   * or <code>Class</code>, the appropriate conversion method is called,
   * with the unwrapped primitive, or the typed object as an argument.
   * The specified {@link org.eclipse.emf.codegen.ecore.genmodel.GenModel},
   * if non-null, is used when necessary to import class names. 
   */
  public static String toLiteral(Object o, GenModel genModel)
  {
    if (o instanceof Boolean)
    {
      return toBooleanLiteral(((Boolean)o).booleanValue(), genModel);
    }
    if (o instanceof Byte)
    {
      return toByteLiteral(((Byte)o).byteValue(), genModel);
    }
    if (o instanceof Short)
    {
      return toShortLiteral(((Short)o).shortValue(), genModel);
    }
    if (o instanceof Integer)
    {
      return toIntLiteral(((Integer)o).intValue(), genModel);
    }
    if (o instanceof Long)
    {
      return toLongLiteral(((Long)o).longValue(), genModel);
    }
    if (o instanceof Float)
    {
      return toFloatLiteral(((Float)o).floatValue(), genModel);
    }
    if (o instanceof Double)
    {
      return toDoubleLiteral(((Double)o).doubleValue(), genModel);
    }
    if (o instanceof Character)
    {
      return toCharLiteral(((Character)o).charValue(), genModel);
    }
    if (o instanceof String)
    {
      return toStringLiteral((String)o, genModel);
    }
    if (o instanceof BigDecimal)
    {
      return toBigDecimalLiteral((BigDecimal)o, genModel);
    }
    if (o instanceof BigInteger)
    {
      return toBigIntegerLiteral((BigInteger)o, genModel);
    }
    if (o instanceof Date)
    {
      return toDateLiteral((Date)o, genModel);
    }
    if (o instanceof Class)
    {
      return toClassLiteral((Class<?>)o, genModel);
    }
    return null;
  }

  /**
   * Returns the literal expression for the given <code>boolean</code> value.
   */
  public static String toBooleanLiteral(boolean b, GenModel genModel)
  {
    return b ? "true" : "false";
  }

  /**
   * Returns the decimal literal expression for the given <code>byte</code>
   * value.
   */
  public static String toByteLiteral(byte b, GenModel genModel)
  {
    return Byte.toString(b);
  }

  /**
   * Returns the decimal literal expression for the given <code>short</code>
   * value.
   */
  public static String toShortLiteral(short s, GenModel genModel)
  {
    return Short.toString(s);
  }

  /**
   * Returns the decimal literal expression for the given <code>int</code>
   * value.
   */
  public static String toIntLiteral(int i, GenModel genModel)
  {
    return Integer.toString(i);
  }

  /**
   * Returns the decimal literal expression for the given <code>long</code>
   * value.
   */
  public static String toLongLiteral(long l, GenModel genModel)
  {
    return Long.toString(l) + "L";
  }

  /**
   * Returns a literal expression for the given <code>float</code> value.
   * This literal may be in simple form or exponential notation, or it may
   * be one of the special values <code>java.lang.Float.NaN</code>,
   * <code>java.lang.Float.POSITIVE_INFINITY</code>, or
   * <code>java.lang.Float.NEGATIVE_INFINITY</code>.
   */
  public static String toFloatLiteral(float f, GenModel genModel)
  {
    if (Float.isNaN(f)) return importName("java.lang.Float", genModel) + ".NaN";
    if (Float.isInfinite(f)) return f > 0 ?
      importName("java.lang.Float", genModel) + ".POSITIVE_INFINITY" :
      importName("java.lang.Float", genModel) + ".NEGATIVE_INFINITY";
    return Float.toString(f) + "F";
  }

  /**
   * Returns a literal expression for the given <code>double</code> value.
   * This literal may be in simple form or exponential notation, or it may
   * be one of the special values <code>java.lang.Double.NaN</code>,
   * <code>java.lang.Double.POSITIVE_INFINITY</code>, or
   * <code>java.lang.Double.NEGATIVE_INFINITY</code>.
   */
  public static String toDoubleLiteral(double d, GenModel genModel)
  {
    if (Double.isNaN(d)) return importName("java.lang.Double", genModel) + ".NaN";
    if (Double.isInfinite(d)) return d > 0 ?
      importName("java.lang.Double", genModel) + ".POSITIVE_INFINITY" :
      importName("java.lang.Double", genModel) + ".NEGATIVE_INFINITY";
    return Double.toString(d);
  }

  private static String importName(String name, GenModel genModel)
  {
    return genModel != null ? genModel.getImportedName(name) : name;
  }

  /**
   * Returns a literal expression for the given <code>char</code> value.
   * This literal will be in its escaped form if it is backspace,
   * horizontal tab, newline, form feed, carriage return, double quote,
   * single quote, or backslash.  If it is within the common printable
   * range of space (32) to <code>~</code> (126), it will simply be the
   * character literal.  Otherwise, it will be in the escaped Unicode
   * encoding form.
   */
  public static String toCharLiteral(char c, GenModel genModel)
  {
    StringBuffer result = new StringBuffer(8);
    result.append('\'');
    result.append(escapeChar(c));
    result.append('\'');
    return result.toString();
  }

  /**
   * Returns a literal expression for the given <code>String</code>.  Each
   * of its characters will appear in the same form as if it was the
   * argument to {@link #toCharLiteral}.
   */
  public static String toStringLiteral(String s, GenModel genModel)
  {
    if (s == null) return "null";
    int len = s.length();
    StringBuffer result = new StringBuffer(len + 16);
    result.append('\"');
    for (int i = 0; i < len; i++)
    {
      result.append(escapeChar(s.charAt(i)));
    }
    result.append('\"');
    return result.toString();
  }

  private static String escapeChar(char c)
  {
    if (c == '\b') return "\\b";
    if (c == '\t') return "\\t";
    if (c == '\n') return "\\n";
    if (c == '\f') return "\\f";
    if (c == '\r') return "\\r";
    if (c == '\"') return "\\\"";
    if (c == '\'') return "\\\'";
    if (c == '\\') return "\\\\";
    if (c >= 32 && c < 127) return String.valueOf(c);

    // escaped unicode form
    String num = Integer.toHexString(c);
    switch(num.length()) {
      case 1: return "\\u000" + num;
      case 2: return "\\u00" + num;
      case 3: return "\\u0" + num;
    }
    return "\\u" + num;
  }

  /**
   * Returns a literal expression for the given <code>BigDecimal</code> value.  
   */
  public static String toBigDecimalLiteral(BigDecimal bigDecimal, GenModel genModel)
  {
    if (bigDecimal == null) return "null";
    return "new " + importName("java.math.BigDecimal", genModel) + "(\"" + bigDecimal.toString() + "\")";
  }

  /**
   * Returns a literal expression for the given <code>BigInteger</code> value.  
   */
  public static String toBigIntegerLiteral(BigInteger bigInteger, GenModel genModel)
  {
    if (bigInteger == null) return "null";
    return "new " + importName("java.math.BigInteger", genModel) + "(\"" + bigInteger.toString() + "\")";
  }

  /**
   * Returns a literal expression for the given <code>Date</code> value.
   */
  public static String toDateLiteral(Date date, GenModel genModel)
  {
    String timeLiteral = toLongLiteral(date.getTime(), genModel);
    return "new " + importName("java.util.Date", genModel) + "(" + timeLiteral + ")";
  }

  /**
   * Returns a literal expression for the given <code>Class</code> value.
   */
  public static String toClassLiteral(Class<?> c, GenModel genModel)
  {
    if (c == null) return "null";
    String name = c.getName(); 

    // See java.lang.Class.getName() javadoc for explanation of array encoding.
    int arrayDepth = 0;
    while (name.charAt(arrayDepth) == '[')
    {
      ++arrayDepth;
    }

    if (arrayDepth > 0)
    {
      if      (name.charAt(arrayDepth) == 'B') name = "byte";
      else if (name.charAt(arrayDepth) == 'C') name = "char";
      else if (name.charAt(arrayDepth) == 'D') name = "double";
      else if (name.charAt(arrayDepth) == 'F') name = "float";
      else if (name.charAt(arrayDepth) == 'I') name = "int";
      else if (name.charAt(arrayDepth) == 'J') name = "long";
      else if (name.charAt(arrayDepth) == 'S') name = "short";
      else if (name.charAt(arrayDepth) == 'Z') name = "boolean";
      else if (name.charAt(arrayDepth) == 'L') name = importName(name.substring(arrayDepth + 1, name.length() - 1), genModel);
      else throw new IllegalArgumentException("Invalid class name: " + name);
    }
    else if (!c.isPrimitive())
    {
      name = importName(name, genModel);
    }
    
    StringBuffer result = new StringBuffer(name.length() + 2 * arrayDepth + 8);
    result.append(name);
    for (int i = 0; i < arrayDepth; i++)
    {
      result.append('[');
      result.append(']');
    }
    result.append(".class");
    return result.toString();
  }
}
