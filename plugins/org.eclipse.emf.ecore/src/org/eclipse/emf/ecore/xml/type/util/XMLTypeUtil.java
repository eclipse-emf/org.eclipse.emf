/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: XMLTypeUtil.java,v 1.2 2004/06/08 13:21:25 marcelop Exp $
 */
package org.eclipse.emf.ecore.xml.type.util;


import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.xml.type.internal.DataValue;
import org.eclipse.emf.ecore.xml.type.internal.RegEx;


/**
 * This class contains convenient static methods for working with XML-related information.
 */
public final class XMLTypeUtil
{
  public static boolean isSpace(char value)
  {
    return DataValue.XMLChar.isSpace(value);
  }


  public static String normalize(String value, boolean collapse) 
  {
    if (value == null)
    {
      return null;
    }

    StringBuffer buffer = null;
    boolean skipSpace = collapse;
    for (int i = 0, size = value.length(), offset = 0; i < size; i++) 
    {
      char c = value.charAt(i);
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
        int length = value.length();
        if (length > 0)
        {
          return value.substring(0, length - 1);
        }
        else
        {
          return value;
        }
      }
      else 
      {
        int length = buffer.length();
        if (length > 0)
        {
          return buffer.substring(0, length - 1);
        }
        else
        {
          return buffer.toString();
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
