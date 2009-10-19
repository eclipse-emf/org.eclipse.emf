/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: Literals.java,v 1.12 2009/10/19 11:20:42 emerks Exp $
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
  // Suppress default constructor for non-instantiability.
  private Literals()
  {
    super();
  }

  /**
   * Convenience dispatch method.  If the <code>object</code> is an instance
   * of <code>Boolean</code>, <code>Byte</code>, <code>Short</code>,
   * <code>Integer</code>, <code>Long</code>, <code>Float</code>,
   * <code>Double</code>, <code>Character</code>, <code>String</code>, 
   * <code>BigDecimal</code>, <code>BigInteger</code>, <code>Date</code>,
   * <code>Class</code>, or <code>byte[]</code>, the appropriate conversion
   * method is called, with the unboxed primitive, or the typed object as an
   * argument. Class names are never imported; the qualified name is used.
   */
  public static String toLiteral(Object o)
  {
    return toLiteral(o, null);
  }

  /**
   * Convenience dispatch method.  If the <code>object</code> is an instance
   * of <code>Boolean</code>, <code>Byte</code>, <code>Short</code>,
   * <code>Integer</code>, <code>Long</code>, <code>Float</code>,
   * <code>Double</code>, <code>Character</code>, <code>String</code>, 
   * <code>BigDecimal</code>, <code>BigInteger</code>, <code>Date</code>,
   * <code>Class</code>, or <code>byte[]</code>, the appropriate conversion
   * method is called, with the unboxed primitive, or the typed object as an
   * argument.
   * <p>The specified {@link org.eclipse.emf.codegen.ecore.genmodel.GenModel},
   * if non-null, is used when necessary to import class names.
   */
  public static String toLiteral(Object o, GenModel genModel)
  {
    return toLiteral(o, false, genModel);
  }

  /**
   * Convenience dispatch method.  If the <code>object</code> is an instance
   * of <code>Boolean</code>, <code>Byte</code>, <code>Short</code>,
   * <code>Integer</code>, <code>Long</code>, <code>Float</code>,
   * <code>Double</code>, <code>Character</code>, <code>String</code>, 
   * <code>BigDecimal</code>, <code>BigInteger</code>, <code>Date</code>,
   * <code>Class</code>, or <code>byte[]</code>, the appropriate conversion
   * method is called, with the unwrapped primitive, or the typed object as an
   * argument.
   * <p>The specified {@link org.eclipse.emf.codegen.ecore.genmodel.GenModel},
   * if non-null, is used when necessary to import class names.
   * <p>If the object is a primitive type, <boolean>boxPrimitive</boolean>
   * indicates whether to include boxing code in the literal expression.
   * Otherwise, this argument is ignored. 
   */
  public static String toLiteral(Object o, boolean boxPrimitive, GenModel genModel)
  {
    if (o instanceof Boolean)
    {
      return toBooleanLiteral((Boolean)o, boxPrimitive, genModel);
    }
    if (o instanceof Byte)
    {
      return toByteLiteral((Byte)o, boxPrimitive, genModel);
    }
    if (o instanceof Short)
    {
      String result = toShortLiteral((Short)o, genModel);
      return boxPrimitive ? box(result, "Short", "short", genModel) : result;
    }
    if (o instanceof Integer)
    {
      String result =  toIntLiteral((Integer)o, genModel);
      return boxPrimitive ? box(result, "Integer", null, genModel) : result;
    }
    if (o instanceof Long)
    {
      String result = toLongLiteral((Long)o, genModel);
      return boxPrimitive ? box(result, "Long", null, genModel) : result;
    }
    if (o instanceof Float)
    {
      String result = toFloatLiteral((Float)o, genModel);
      return boxPrimitive ? box(result, "Float", null, genModel) : result;
    }
    if (o instanceof Double)
    {
      String result = toDoubleLiteral((Double)o, genModel);
      return boxPrimitive ? box(result, "Double", null, genModel) : result;
    }
    if (o instanceof Character)
    {
      String result = toCharLiteral((Character)o, genModel);
      return boxPrimitive ? box(result, "Character", null, genModel) : result;
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
    if (o instanceof Class<?>)
    {
      return toClassLiteral((Class<?>)o, genModel);
    }
    if (o instanceof byte[])
    {
      return toByteArrayLiteral((byte[])o, genModel);
    }
    return null;
  }

  /**
   * Returns the literal expression for the given <code>boolean</code> value,
   * with optional boxing.
   */
  private static String toBooleanLiteral(boolean b, boolean box, GenModel genModel)
  {
    if (box)
    {
      return importName("java.lang.Boolean", genModel) + (b ? ".TRUE" : ".FALSE");
    }
    return b ? "true" : "false";
  }

  /**
   * Returns the literal expression for the given <code>boolean</code> value.
   */
  public static String toBooleanLiteral(boolean b, GenModel genModel)
  {
    return toBooleanLiteral(b, false, genModel);
  }

  /**
   * Returns the hexadecimal literal expression for the given <code>byte</code>
   * value, with optional boxing.
   */
  private static String toByteLiteral(byte b, boolean box, GenModel genModel)
  {
    String result = toByteLiteral(b, genModel);
    if (box)
    {
      String wrapperClass = importName("java.lang.Byte", genModel);
      StringBuilder boxed = new StringBuilder(16 + wrapperClass.length());
      boxed.append("new ");
      boxed.append(wrapperClass);
      boxed.append('(');
      if (b >= 0)
      {
        boxed.append("(byte)");
      }
      boxed.append(result);
      boxed.append(')');
      result = boxed.toString();
    }
    return result;
  }

  /**
   * Returns the hexadecimal literal expression for the given <code>byte</code>
   * value.
   */
  public static String toByteLiteral(byte b, GenModel genModel)
  {
    String hex = Integer.toHexString(b);
    switch (hex.length())
    {
      case 1: return "0x0" + hex;
      case 2: return "0x" + hex;
      case 8: return "(byte)0x" + hex.substring(hex.length() - 2);
    }
    throw new IllegalArgumentException("A byte cannot convert to a " + hex.length() +  " digit hex string (Java platform error in Integer.toHexString())");
  }

  /**
   * Returns the boxed form of the literal: new wrapperClass((castType)literal)
   * The wrapperClass is imported, and, if the castType is null, no cast is
   * included.
   */
  private static String box(String literal, String wrapperClass, String castType, GenModel genModel)
  {
    wrapperClass = importName(wrapperClass, genModel);
    StringBuilder boxed = new StringBuilder(6 + literal.length() + wrapperClass.length() + (castType != null ? castType.length() + 2 : 0));
    boxed.append("new ");
    boxed.append(wrapperClass);
    boxed.append('(');
    if (castType != null)
    {
      boxed.append('(');
      boxed.append(castType);
      boxed.append(')');
    }
    boxed.append(literal);
    boxed.append(')');
    return boxed.toString();
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
    return genModel != null && genModel.getImportManager() != null ? genModel.getImportedName(name) : name;
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
    StringBuilder result = new StringBuilder(8);
    result.append('\'');
    result.append(escapeChar(c, false));
    result.append('\'');
    return result.toString();
  }

  /**
   * Returns a literal expression for the given <code>char</code> value.
   * This literal will be in its escaped form if it is backspace,
   * horizontal tab, newline, form feed, carriage return, double quote,
   * single quote, or backslash.  Otherwise, it will simply be the character
   * literal. This result may not fall within the common printable range.
   * @since 2.5
   */
  public static String toUnsafeCharLiteral(char c, GenModel genModel)
  {
    StringBuilder result = new StringBuilder(8);
    result.append('\'');
    result.append(escapeChar(c, true));
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
    return toStringLiteral(s, genModel, false);
  }

  /**
   * Returns a literal expression for the given <code>String</code>.  Each
   * of its characters will appear in the same form as if it was the
   * argument to {@link #toUnsafeCharLiteral}.
   * @since 2.5
   */
  public static String toUnsafeStringLiteral(String s, GenModel genModel)
  {
    return toStringLiteral(s, genModel, true);
  }

  private static String toStringLiteral(String s, GenModel genModel, boolean unsafe)
  {
    if (s == null) return "null";
    int len = s.length();
    StringBuilder result = new StringBuilder(len + 16);
    result.append('\"');
    for (int i = 0; i < len; i++)
    {
      result.append(escapeChar(s.charAt(i), unsafe));
    }
    result.append('\"');
    return result.toString();
  }

  private static String escapeChar(char c, boolean unsafe)
  {
    if (c == '\b') return "\\b";
    if (c == '\t') return "\\t";
    if (c == '\n') return "\\n";
    if (c == '\f') return "\\f";
    if (c == '\r') return "\\r";
    if (c == '\"') return "\\\"";
    if (c == '\'') return "\\\'";
    if (c == '\\') return "\\\\";
    if (unsafe || (c >= 32 && c < 127)) return String.valueOf(c);

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
    
    StringBuilder result = new StringBuilder(name.length() + 2 * arrayDepth + 8);
    result.append(name);
    for (int i = 0; i < arrayDepth; i++)
    {
      result.append('[');
      result.append(']');
    }
    result.append(".class");
    return result.toString();
  }

  /**
   * Returns a literal expression for the given <code>byte[]</code> value.
   */
  public static String toByteArrayLiteral(byte[] bytes, GenModel genModel)
  {
    if (bytes == null) return "null";
    if (bytes.length == 0) return "{}";

    StringBuilder result = new StringBuilder(2 + bytes.length * 7);
    result.append("{ ");
    for (int i = 0, last = bytes.length - 1; i <= last; i++)
    {
      result.append(toByteLiteral(bytes[i], genModel));
      if (i < last)
      {
        result.append(", ");
      }
    }
    result.append(" }");
    return result.toString();
  }
}
