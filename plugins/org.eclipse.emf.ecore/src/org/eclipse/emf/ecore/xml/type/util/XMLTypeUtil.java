/**
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
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
    private static final int MAX_CACHE_CAPACITY;
    static
    {
      // Set a reasonably small default limit.
      //
      int result = 10000;
      try
      {
        String property = System.getProperty("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil.CharArrayThreadLocal.MAX_CACHE_CAPACITY");
        if (property != null)
        {
          result = Integer.valueOf(property);
        }
      }
      catch (Throwable throwable)
      {
        // Ignore all exceptions, including security exceptions.
      }
      MAX_CACHE_CAPACITY = result;
    }

    private long cachedThread = -1;
    private char [] cachedResult;

    public final char [] get(int capacity)
    {
      if (capacity > MAX_CACHE_CAPACITY)
      {
        return new char [capacity];
      }
      long currentThread = Thread.currentThread().getId();
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
   * @param prefix prefix value or null (if null, an empty string will actually be used in the resulting QName)
   * @return The newly created QName object
   */
  public static Object createQName(String namespaceUri, String localPart, String prefix)
  {
    return new org.eclipse.emf.ecore.xml.type.internal.QName(namespaceUri, localPart, prefix);
  }
  
  /**
   * Sets the QName object values to the specified once
   * @param namespaceUri namespace uri value or null
   * @param localPart localPart (not null)
   * @param prefix prefix value or null
   */
  @Deprecated
  public static void setQNameValues(Object qName, String namespaceUri, String localPart, String prefix)
  {
    if (!(qName instanceof org.eclipse.emf.ecore.xml.type.internal.QName))
    {
      throw new UnsupportedOperationException("QNames are immutable, so this can't be supported");
    }
    if (namespaceUri == null)
    {
      namespaceUri = "";
    }
    org.eclipse.emf.ecore.xml.type.internal.QName qn = (org.eclipse.emf.ecore.xml.type.internal.QName)qName;
    if (!qn.getLocalPart().equals(localPart) || qn.getNamespaceURI().equals(namespaceUri))
    {
      throw new UnsupportedOperationException("QNames are immutable, so this can't be supported");
    }
    qn.setPrefix(prefix);
  }

  /**
   * Updates the QName's prefix, if possible, and returns either the updated result, 
   * or a newly created QName with the new prefix, if the QName could not be directly updated.
   * @param qName the QName to be updated.
   * @param prefix the new prefix.
   * @return a QName with the same namespace URI and local part as the argument, but with the new prefix.
   */
  public static QName setPrefix(QName qName, String prefix)
  {
    if (qName instanceof org.eclipse.emf.ecore.xml.type.internal.QName)
    {
      org.eclipse.emf.ecore.xml.type.internal.QName result = (org.eclipse.emf.ecore.xml.type.internal.QName)qName;
      result.setPrefix(prefix);
      return result;
    }
    else
    {
      return new org.eclipse.emf.ecore.xml.type.internal.QName(qName.getNamespaceURI(), qName.getLocalPart(), prefix);
    }
  }

  /**
   * Returns the namespaceURI of a QName.
   */
  public static String getQNameNamespaceURI(Object qName)
  {
    return ((QName)qName).getNamespaceURI();
  }
  /**
   * Returns the localPart of a QName.
   */
  public static String getQNameLocalPart(Object qName)
  {
    return ((QName)qName).getLocalPart();
  }
  
  /**
   * Returns the prefix of a QName.
   */
  public static String getQNamePrefix(Object qName)
  {
    return ((QName)qName).getPrefix();
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
}
