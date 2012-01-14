/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.ecore;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;


public class NameMangler
{
  public NameMangler()
  {
    super();
  }

  // This behaves like CodeGenUtil.uncapPrefixedName(), which isn't available here,
  // except without the forceDifferent option.
  // The two methods should be kept in synch. 
  //
  public String uncapName(String name)
  {
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
      if (i > 1 && i < name.length() && !Character.isDigit(name.charAt(i)))
      {
        --i;
      }
      return name.substring(0, i).toLowerCase() + name.substring(i);
    }
  }

  protected static final List<String> DOMAINS = Arrays.asList(new String [] { "COM", "com", "ORG", "org" });

  public String qualifiedPackageName(String namespace)
  {
    URI uri = URI.createURI(namespace);
    List<String> parsedName;
    if (uri.isHierarchical())
    {
      String host = uri.host();
      if (host != null && host.startsWith("www."))
      {
        host = host.substring(4);
      }
      parsedName = parseName(host, '.');
      Collections.reverse(parsedName);
      if (!parsedName.isEmpty())
      {
        parsedName.set(0, parsedName.get(0).toLowerCase());
      }

      parsedName.addAll(parseName(uri.trimFileExtension().path(), '/'));
    }
    else
    {
      String opaquePart = uri.opaquePart();
      int index = opaquePart.indexOf(":");
      if (index != -1 && "urn".equalsIgnoreCase(uri.scheme()))
      {
        parsedName = parseName(opaquePart.substring(0, index), '-');
        if (parsedName.size() > 0 && DOMAINS.contains(parsedName.get(parsedName.size() - 1))) 
        {
          Collections.reverse(parsedName);
          parsedName.set(0, parsedName.get(0).toLowerCase());
        }
        parsedName.addAll(parseName(opaquePart.substring(index + 1), '/'));
      }
      else
      {
        parsedName = parseName(opaquePart, '/');
      }
    }

    StringBuffer qualifiedPackageName = new StringBuffer();
    for (String packageName : parsedName)
    {
      if (packageName.length() > 0)
      {
        if (qualifiedPackageName.length() > 0)
        {
          qualifiedPackageName.append('.');
        }
        qualifiedPackageName.append(validName(packageName, false));
      }
    }
    return qualifiedPackageName.toString();
  }

  protected String validName(String name, boolean isUpperCase)
  {
    return validName(name, isUpperCase, "_"); 
  }
  
  protected String validName(String name, boolean isUpperCase, String prefix)
  {
    return validName(name, isUpperCase ? UPPER_CASE : LOWER_CASE, prefix);
  }

  protected static final int UNCHANGED_CASE = 0;
  protected static final int UPPER_CASE = 1;
  protected static final int LOWER_CASE = 2;

  protected String validName(String name, int casing, String prefix)
  {
    List<String> parsedName = parseName(name, '_');
    StringBuffer result = new StringBuffer();
    for (String nameComponent : parsedName)
    {
      if (nameComponent.length() > 0)
      {
        if (result.length() > 0 || casing == UPPER_CASE)
        {
          result.append(Character.toUpperCase(nameComponent.charAt(0)));
          result.append(nameComponent.substring(1));
        }
        else
        {
          result.append(nameComponent);
        }
      }
    }

    return
      result.length() == 0 ?
        prefix :
        Character.isJavaIdentifierStart(result.charAt(0)) ?
          casing == LOWER_CASE ?
            uncapName(result.toString()) :
            result.toString() :
          prefix + result;
  }

  // This behaves like CodeGenUtil.parseName(), which isn't available here,
  // except it also removes invalid indentifier characters.
  // The two methods should be kept in synch. 
  //
  protected List<String> parseName(String sourceName, char separator)
  {
    List<String> result = new ArrayList<String>();
    if (sourceName != null)
    {
      StringBuffer currentWord = new StringBuffer();
      boolean lastIsLower = false;
      for (int index = 0, length = sourceName.length(); index < length; ++index)
      {
        char curChar = sourceName.charAt(index);
        if (!Character.isJavaIdentifierPart(curChar))
        {
          curChar = separator;
        }
        if (Character.isUpperCase(curChar) || (!lastIsLower && Character.isDigit(curChar)) || curChar == separator)
        {
          if (lastIsLower && currentWord.length() > 1 || curChar == separator && currentWord.length() > 0)
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

        if (curChar != separator)
        {
          currentWord.append(curChar);
        }
      }

      result.add(currentWord.toString());
    }
    return result;
  }

}
