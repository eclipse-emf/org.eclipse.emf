/**
 * <copyright>
 *
 * Copyright (c) 2010 Kenn Hussey and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Kenn Hussey - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: StringUtil.java,v 1.1 2010/12/05 01:42:05 khussey Exp $
 */
package org.eclipse.emf.oda.ecore.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;


/**
 * A utility for working with strings and text.
 */
public class StringUtil
{
  protected static List<String> parseName(String sourceName, char sourceSeparator)
  {
    List<String> result = new ArrayList<String>();
    StringBuffer currentWord = new StringBuffer();

    int length = sourceName.length();
    boolean lastIsLower = false;

    for (int index = 0; index < length; index++)
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

  protected static String format(String name, char separator)
  {
    StringBuffer result = new StringBuffer();

    for (Iterator<String> i = parseName(name, '_').iterator(); i.hasNext();)
    {
      String component = i.next();
      result.append(component);
      if (i.hasNext() && component.length() > 1)
      {
        result.append(separator);
      }
    }
    return result.toString();
  }

  protected static String capName(String name)
  {
    return name.length() == 0 ? name : name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  protected static EStructuralFeature getLabelFeature(org.eclipse.emf.ecore.EClass eClass)
  {
    EAttribute result = null;
    for (EAttribute eAttribute : eClass.getEAllAttributes())
    {
      if (!eAttribute.isMany() && eAttribute.getEType().getInstanceClass() != FeatureMap.Entry.class)
      {
        if ("name".equalsIgnoreCase(eAttribute.getName())) //$NON-NLS-1$
        {
          result = eAttribute;
          break;
        }
        else if (result == null)
        {
          result = eAttribute;
        }
        else if (eAttribute.getEAttributeType().getInstanceClass() == String.class
          && result.getEAttributeType().getInstanceClass() != String.class)
        {
          result = eAttribute;
        }
      }
    }
    return result;
  }

  public static String getText(Object object)
  {
    if (object instanceof Collection< ? >)
    {
      StringBuilder sb = new StringBuilder("["); //$NON-NLS-1$

      for (Iterator< ? > i = ((Collection< ? >)object).iterator(); i.hasNext();)
      {
        sb.append(getText(i.next()));

        if (i.hasNext())
        {
          sb.append(", "); //$NON-NLS-1$
        }
      }
      sb.append(']');

      return sb.toString();
    }
    else if (object instanceof EObject)
    {
      EObject eObject = (EObject)object;
      EClass eClass = eObject.eClass();
      String label = format(capName(eClass.getName()), ' ');

      EStructuralFeature feature = getLabelFeature(eClass);
      if (feature != null)
      {
        Object value = eObject.eGet(feature);
        if (value != null)
        {
          return label + " " + value.toString();
        }
      }
      return label;
    }
    else
    {
      return String.valueOf(object);
    }
  }

  /**
   * Returns formatted text for the specified feature
   * @param feature the feature
   * @return formatted text
   */
  public static String getFeatureText(EStructuralFeature feature)
  {
    return format(capName(feature.getName()), ' ');
  }

  /**
   * Returns formatted text for the specified type
   * @param classifier the type
   * @return formatted text
   */
  public static String getTypeText(EClassifier classifier)
  {
    return classifier.getEPackage().getName() + '.' + classifier.getName();
  }

  /**
   * Indicates whether the specified text is null or has a length of zero.
   * @param text the text
   * @return whether the text is null or empty
   */
  public static boolean isEmpty(String text)
  {
    return text == null || text.trim().length() < 1;
  }
}
