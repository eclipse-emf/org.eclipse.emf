/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: XMLTypeUtil.java,v 1.8 2006/02/10 20:51:22 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.type.util;


import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.xml.type.internal.DataValue;
import org.eclipse.emf.ecore.xml.type.internal.QName;
import org.eclipse.emf.ecore.xml.type.internal.RegEx;
import org.eclipse.emf.ecore.xml.type.internal.XMLCalendar;
import org.eclipse.emf.ecore.xml.type.internal.XMLDuration;


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
    return XMLCalendar.compare((XMLCalendar)calendar1, (XMLCalendar)calendar2);
  }

  public static int compareDuration(Object duration1, Object duration2)
  {
    return XMLDuration.compare((XMLDuration)duration1, (XMLDuration)duration2);
  }

  public static boolean isSpace(char value)
  {
    return DataValue.XMLChar.isSpace(value);
  }

  // TODO
  // This is faster than many charAt() calls.
  //
  private static class CharArrayThreadLocal extends ThreadLocal
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
        result = (char [])get();
      }
      if (result.length < capacity)
      {
        result = new char [capacity];
        set(result);
      }
      return cachedResult = result;
    }

    protected Object initialValue()
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
    return new QName(namespaceUri, localPart, prefix);
  }
  
  /**
   * Sets the QName object values to the specified onces
   * @param namespaceUri namespace uri value or null
   * @param localPart localPart (not null)
   * @param prefix prefix value or null
   */
  public static void setQNameValues(Object qname, String namespaceUri, String localPart, String prefix)
  {
      QName qn = (QName)qname;
      qn.setLocalPart(localPart);
      qn.setNamespaceURI(namespaceUri);
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

    public String toString()
    {
      return regularExpression.getPattern();
    }
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
