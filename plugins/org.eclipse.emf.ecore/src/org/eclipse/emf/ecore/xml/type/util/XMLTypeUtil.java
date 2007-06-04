/**
 * <copyright>
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
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
 * $Id: XMLTypeUtil.java,v 1.11 2007/06/04 18:47:00 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.type.util;


import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.xml.type.internal.DataValue;
import org.eclipse.emf.ecore.xml.type.internal.RegEx;


/**
 * This class contains convenient static methods for working with XML-related information.
 */
public final class XMLTypeUtil
{
  public static final int EQUALS = 0;
  public static final int LESS_THAN = -1;
  public static final int GREATER_THAN = 1;
  public static final int INDETERMINATE = 2;

  public static int compareCalendar(Object calendar1, Object calendar2)
  {
    switch (((XMLGregorianCalendar)calendar1).compare((XMLGregorianCalendar)calendar2))
    {
      case DatatypeConstants.EQUAL:
      {
        return EQUALS;
      }
      case DatatypeConstants.LESSER:
      {
        return LESS_THAN;
      }
      case DatatypeConstants.GREATER:
      {
        return GREATER_THAN;
      }
      default:
      {
        return INDETERMINATE;
      }
    }
  }

  public static int compareDuration(Object duration1, Object duration2)
  {
    switch (((Duration)duration1).compare((Duration)duration2))
    {
      case DatatypeConstants.EQUAL:
      {
        return EQUALS;
      }
      case DatatypeConstants.LESSER:
      {
        return LESS_THAN;
      }
      case DatatypeConstants.GREATER:
      {
        return GREATER_THAN;
      }
      default:
      {
        return INDETERMINATE;
      }
    }
  }

  public static boolean isSpace(char value)
  {
    return DataValue.XMLChar.isSpace(value);
  }

  // TODO
  // This is faster than many charAt() calls.
  //
  private static class CharArrayThreadLocal extends ThreadLocal<char[]>
  {
    private Thread cachedThread;
    private char [] cachedResult;

    public final char [] get(int capacity)
    {
      Thread currentThread = Thread.currentThread();
      char [] result = cachedResult;
      if (cachedThread != currentThread)
      {
        cachedThread = currentThread;
        result = get();
      }
      if (result.length < capacity)
      {
        result = new char [capacity];
        set(result);
      }
      return cachedResult = result;
    }

    @Override
    protected char [] initialValue()
    {
      return new char [20];
    }
  }

  private static final CharArrayThreadLocal VALUE = new CharArrayThreadLocal();

  public static String normalize(String value, boolean collapse) 
  {
    if (value == null)
    {
      return null;
    }

    int length = value.length();
    if (length == 0)
    {
      return "";
    }

    char [] valueArray = VALUE.get(length);
    value.getChars(0, length, valueArray, 0);
    StringBuffer buffer = null;
    boolean skipSpace = collapse;
    for (int i = 0, offset = 0; i < length; i++) 
    {
      char c = valueArray[i];
      if (isSpace(c)) 
      {
        if (skipSpace)
        {
          if (buffer == null)
          {
            buffer = new StringBuffer(value);
          }
          buffer.deleteCharAt(i - offset++);
        }
        else 
        {
          skipSpace = collapse;
          if (c != ' ')
          {
            if (buffer == null)
            {
              buffer = new StringBuffer(value);
            }
            buffer.setCharAt(i - offset, ' ');
          }
        }
      }
      else 
      {
        skipSpace = false;
      }
    }

    if (skipSpace) 
    {
      if (buffer == null)
      {
        return value.substring(0, length - 1);
      }
      else 
      {
        length = buffer.length();
        if (length > 0)
        {
          return buffer.substring(0, length - 1);
        }
        else
        {
          return "";
        }
      }
    }
    else
    {
      if (buffer == null)
      {
        return value;
      }
      else
      {
        return buffer.toString();
      }
    }
  }

  public static EValidator.PatternMatcher createPatternMatcher(String pattern)
  {
    return new PatternMatcherImpl(pattern);
  }
  
  
  /**
   * Creates a new QName object with the specified values
   * @param namespaceUri namespace uri value or null
   * @param localPart localPart (not null)
   * @param prefix prefix value or null
   * @return The newly created QName object
   */
  public static Object createQName(String namespaceUri, String localPart, String prefix)
  {
    return new org.eclipse.emf.ecore.xml.type.internal.QName(namespaceUri, localPart, prefix);
  }
  
  /**
   * Sets the QName object values to the specified onces
   * @param namespaceUri namespace uri value or null
   * @param localPart localPart (not null)
   * @param prefix prefix value or null
   */
  @Deprecated
  public static void setQNameValues(Object qname, String namespaceUri, String localPart, String prefix)
  {
    if (!(qname instanceof org.eclipse.emf.ecore.xml.type.internal.QName))
    {
      throw new UnsupportedOperationException("QNames are immutable, so this can't be supported");
    }
    if (namespaceUri == null)
    {
      namespaceUri = "";
    }
    org.eclipse.emf.ecore.xml.type.internal.QName qn = (org.eclipse.emf.ecore.xml.type.internal.QName)qname;
    if (!qn.getLocalPart().equals(localPart) || qn.getNamespaceURI().equals(namespaceUri))
    {
      throw new UnsupportedOperationException("QNames are immutable, so this can't be supported");
    }
    qn.setPrefix(prefix);
  }
  
  /**
   * Returns the namespaceURI of a QName.
   */
  public static String getQNameNamespaceURI(Object qname)
  {
    return ((QName)qname).getNamespaceURI();
  }
  /**
   * Returns the localPart of a QName.
   */
  public static String getQNameLocalPart(Object qname)
  {
    return ((QName)qname).getLocalPart();
  }
  
  /**
   * Returns the prefix of a QName.
   */
  public static String getQNamePrefix(Object qname)
  {
    return ((QName)qname).getPrefix();
  }

  private static class PatternMatcherImpl implements EValidator.PatternMatcher
  {
    protected RegEx.RegularExpression regularExpression;

    public PatternMatcherImpl(String pattern)
    {
      regularExpression =  new RegEx.RegularExpression(pattern, "X");
    }

    public boolean matches(String value)
    {
      return regularExpression.matches(value);
    }

    @Override
    public String toString()
    {
      return regularExpression.getPattern();
    }
  }

  /**
   * Returns whether the code point is the valid start of an XML Name.
   */
  public static boolean isNameStart(int codePoint)
  {
    return DataValue.XMLChar.isNameStart(codePoint);
  }

  /**
   * Returns whether the code point is a valid part of an XML Name.
   */
  public static boolean isNamePart(int codePoint)
  {
    return DataValue.XMLChar.isName(codePoint);
  }

  /**
   * Returns whether the code point is the valid start of an XML NCName.
   */
  public static boolean isNCNameStart(int codePoint)
  {
    return DataValue.XMLChar.isNCNameStart(codePoint);
  }

  /**
   * Returns whether the code point is a valid part of an XML NCName.
   */
  public static boolean isNCNamePart(int codePoint)
  {
    return DataValue.XMLChar.isNCName(codePoint);
  }

/*
  public static void main(String args[])
  {
    System.err.println("###YES");
    String x1 = normalize("  x  ", true);
    String x2 = normalize("  x  ", false);
    String x3 = normalize("  x  x  ", true);
    String x4 = normalize("  x  x  ", false);
    String x5 = normalize("  x  x ", true);
    String x6 = normalize("  x  x ", false);
    String x7 = normalize("  x  x", true);
    String x8 = normalize("  x  x", false);
    String x9 = normalize("  x \n x", true);
    String x10 = normalize("  x \n x", false);
    System.err.println(XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInt(), " 1 "));
    System.err.println(XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInt(), "  1 \n "));
    System.err.println("###YES");
  }
*/
}
