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
 * $Id: JPatternDictionary.java,v 1.9 2010/02/04 20:56:37 emerks Exp $
 */
package org.eclipse.emf.codegen.jmerge;


import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;
import org.eclipse.jdt.core.jdom.IDOMField;
import org.eclipse.jdt.core.jdom.IDOMImport;
import org.eclipse.jdt.core.jdom.IDOMInitializer;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jdt.core.jdom.IDOMNode;
import org.eclipse.jdt.core.jdom.IDOMPackage;
import org.eclipse.jdt.core.jdom.IDOMType;


/**
 * A dictionary of signatures and JDOM nodes.
 * @deprecated in 2.2.0. Use {@link org.eclipse.emf.codegen.merge.java.JPatternDictionary} instead.
 */
@Deprecated
@SuppressWarnings({"unchecked", "rawtypes"})
public class JPatternDictionary 
{
  protected IDOMCompilationUnit compilationUnit;
  protected IDOMPackage jPackage;
  protected JControlModel options;
  protected Map importMap = new HashMap();
  protected Map typeMap = new HashMap();
  protected Map initializerMap = new HashMap();
  protected Map fieldMap = new HashMap();
  protected Map methodMap = new HashMap();
  protected Map markupMap = new HashMap();
  protected Collection noImportSet = new HashSet();

  /**
   * This creates an instance.
   */
  public JPatternDictionary(IDOMCompilationUnit compilationUnit, JControlModel options)
  {
    this.options = options;
    analyzeCompilationUnit(compilationUnit);
  }

  protected void analyzeCompilationUnit(IDOMCompilationUnit compilationUnit)
  {
    this.compilationUnit = compilationUnit;

    if (options.getNoImportPattern() != null)
    {
      Matcher matcher = options.getNoImportPattern().matcher(compilationUnit.getContents());
      while (matcher.find())
      {
        noImportSet.add(matcher.group(1));
      }
    }

    match(compilationUnit);
    for (IDOMNode child = compilationUnit.getFirstChild(); child != null; child = child.getNextNode())
    {
      switch (child.getNodeType())
      {
        case IDOMNode.PACKAGE: 
        {
          analyzePackage((IDOMPackage)child);
          break;
        }
        case IDOMNode.IMPORT:
        {
          analyzeImport((IDOMImport)child);
          break;
        }
        case IDOMNode.TYPE: 
        {
          analyzeType((IDOMType)child);
          break;
        }
      }
    }
  }

  protected void analyzePackage(IDOMPackage jPackage)
  {
    this.jPackage = jPackage;
    match(jPackage);
  }

  protected void analyzeImport(IDOMImport jImport)
  {
    importMap.put(getQualifiedName(jImport), jImport);
    match(jImport);
  }

  protected void analyzeType(IDOMType type)
  {
    typeMap.put(getQualifiedName(type), type);
    match(type);
    for (IDOMNode child = type.getFirstChild(); child != null; child = child.getNextNode())
    {
      switch (child.getNodeType())
      {
        case IDOMNode.INITIALIZER:
        {
          analyzeInitializer((IDOMInitializer)child);
          break;
        }
        case IDOMNode.FIELD:
        {
          analyzeField((IDOMField)child);
          break;
        }
        case IDOMNode.METHOD: 
        {
          analyzeMethod((IDOMMethod)child);
          break;
        }
        case IDOMNode.TYPE: 
        {
          analyzeType((IDOMType)child);
          break;
        }
      }
    }
  }

  protected void analyzeInitializer(IDOMInitializer initializer)
  {
    initializerMap.put(getQualifiedName(initializer), initializer);
    match(initializer);
  }

  protected void analyzeField(IDOMField field)
  {
    fieldMap.put(getQualifiedName(field), field);
    match(field);
  }

  protected void analyzeMethod(IDOMMethod method)
  {
    methodMap.put(getQualifiedName(method), method);
    match(method);
  }

  public String getQualifiedName(IDOMNode jdomNode)
  {
    switch (jdomNode.getNodeType())
    {
      case IDOMNode.COMPILATION_UNIT:
      {
        return jdomNode.getName();
      }
      case IDOMNode.PACKAGE: 
      {
        return jdomNode.getName();
      }
      case IDOMNode.IMPORT:
      {
        return jdomNode.getName();
      }
      case IDOMNode.TYPE: 
      {
        return jPackage != null ? jPackage.getName() + "." + jdomNode.getName() : jdomNode.getName();
      }
      case IDOMNode.FIELD:
      {
        return getQualifiedName(jdomNode.getParent()) + "." + jdomNode.getName();
      }
      case IDOMNode.INITIALIZER:
      {
        String name = getQualifiedName(jdomNode.getParent());
        int index = 0;
        for (jdomNode = jdomNode.getNextNode(); jdomNode != null; jdomNode = jdomNode.getNextNode())
        {
          if (jdomNode.getNodeType() == IDOMNode.INITIALIZER)
          {
            ++index;
          }
        }
        return name + "." + index;
      }
      case IDOMNode.METHOD: 
      {
        IDOMMethod jdomMethod = (IDOMMethod)jdomNode;
        StringBuffer result = new StringBuffer(getQualifiedName(jdomNode.getParent()));
        result.append(".");
        if (jdomMethod.isConstructor())
        {
          result.append(jdomMethod.getParent().getName()); 
        }
        else
        {
          result.append(jdomMethod.getName());
        }
        result.append("("); //)
        String [] parameters = jdomMethod.getParameterTypes();
        if (parameters != null)
        {
          for (int i = 0; i < parameters.length; ++i)
          {
            if (i != 0)
            {
              result.append(", ");
            }
            result.append(parameters[i]);
          }
        }
        // (
        result.append(")"); 
        return result.toString();
      }
      default:
      {
        return "";
      }
    }
  }

  public void dump()
  {
    System.out.println("---- imports ---------------------------------------------");
    dumpStringToIDOMNodeMap(importMap);
    System.out.println("---- types -----------------------------------------------");
    dumpStringToIDOMNodeMap(typeMap);
    System.out.println("---- initializers ----------------------------------------");
    dumpStringToIDOMNodeMap(initializerMap);
    System.out.println("---- fields ----------------------------------------------");
    dumpStringToIDOMNodeMap(fieldMap);
    System.out.println("---- methods ---------------------------------------------");
    dumpStringToIDOMNodeMap(methodMap);

    dumpMarkup();
  }

  public void dumpMarkup()
  {
    System.out.println("====  markup  ============================================");
    for (Iterator entries = markupMap.entrySet().iterator(); entries.hasNext(); )
    {
      Map.Entry entry = (Map.Entry)entries.next();
      System.out.println("==== " + entry.getKey() + " ============================================");
      for (Iterator values = ((Collection)entry.getValue()).iterator(); values.hasNext(); )
      {
        IDOMNode node = (IDOMNode)values.next();
        System.out.println(getQualifiedName(node));
        //dumpNodeContents(node);
      }
    }
  }

  public void dumpNodeContents(IDOMNode node)
  {
    System.out.println("____ " + getQualifiedName(node) + " ____________________________________________");
    System.out.print(node.getContents());
    System.out.println("_____________________________________________________________________");
  }

  public void dumpStringToIDOMNodeMap(Map map)
  {
    for (Iterator entries = map.entrySet().iterator(); entries.hasNext(); )
    {
      Map.Entry entry = (Map.Entry)entries.next();
      String key = (String)entry.getKey();
      IDOMNode node = (IDOMNode)entry.getValue();
      System.out.println(key + "->" + getQualifiedName(node));
      // dumpNodeContents(node);
    }
  }

  protected static Pattern comment = Pattern.compile("/\\*.*?\\*/", Pattern.MULTILINE | Pattern.DOTALL);
  protected static Object [] noArguments = new Object [0];
  protected void match(IDOMNode node)
  {
    for (Iterator dictionaryPatterns = options.getDictionaryPatterns().iterator(); dictionaryPatterns.hasNext(); )
    {
      JControlModel.DictionaryPattern dictionaryPattern = (JControlModel.DictionaryPattern)dictionaryPatterns.next();
      if (dictionaryPattern.getSelectorFeature().getFeatureClass() != null && 
		  dictionaryPattern.getSelectorFeature().getFeatureClass().isInstance(node))
      {
        try
        {
          String selection = (String)dictionaryPattern.getSelectorFeature().getFeatureMethod().invoke(node, noArguments);
          if (dictionaryPattern.getSelectorFeature().getFeatureMethod().getName().equals("getComment"))
          {
            String contents = node.getContents();
            for (int start = 0, end = contents.length(), count = 0; start < end; )
            {
              Matcher matcher = comment.matcher(contents.subSequence(start, end));
              if (matcher.find())
              {
                // Ignore it if there are multiple comments.
                //
                if (++count > 1)
                {
                  int braceIndex = contents.indexOf("{", start); // }
                  if (braceIndex > start + matcher.start(0))
                  {
                    selection = null;
                  }

                  break;
                }
                start += matcher.end(0) + 1;
              }
              else
              {
                break;
              }
            }
          }

          if (selection != null)
          {
            Matcher matcher = dictionaryPattern.getPattern().matcher(selection);
            if (matcher.find())
            {
              for (int i = 1; i <= matcher.groupCount(); ++i)
              {
                String markup = matcher.group(i);
                Collection collection = (Collection)markupMap.get(markup);
                if (collection == null)
                {
                  collection = new HashSet();
                  markupMap.put(markup, collection);
                }
                collection.add(node);
              }
            }
          }
        }
        catch (IllegalAccessException exception)
        {
          // exception.printStackTrace();
        }
        catch (InvocationTargetException exception)
        {
          // exception.printStackTrace();
        }
      }
    }
  }

  public IDOMCompilationUnit getCompilationUnit()
  {
    return compilationUnit;
  }

  public IDOMPackage getPackage()
  {
    return jPackage;
  }

  public JControlModel options()
  {
    return options;
  }

  public Map getImportMap()
  {
    return importMap;
  }

  public Map getTypeMap()
  {
    return typeMap;
  }

  public Map getInitializerMap()
  {
    return initializerMap;
  }

  public Map getFieldMap()
  {
    return fieldMap;
  }

  public Map getMethodMap()
  {
    return methodMap;
  }

  public Map getMarkupMap()
  {
    return markupMap;
  }

  public boolean isMarkedUp(Pattern markupPattern, IDOMNode node)
  {
    if (markupPattern == null)
    {
      return true;
    }
    else
    {
      for (Iterator markupEntries = markupMap.entrySet().iterator(); markupEntries.hasNext(); )
      {
        Map.Entry markupEntry = (Map.Entry)markupEntries.next();
        String key = (String)markupEntry.getKey();
        if (key != null && markupPattern.matcher(key).find())
        {
          if (((Collection)markupEntry.getValue()).contains(node))
          {
            return true;
          }
        }
      }
      return false;
    }
  }

  public boolean isNoImport(IDOMImport domImport)
  {
    return noImportSet.contains(getQualifiedName(domImport));
  }
}
