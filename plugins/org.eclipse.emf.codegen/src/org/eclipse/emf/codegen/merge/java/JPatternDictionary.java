/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: JPatternDictionary.java,v 1.1 2006/01/18 20:42:15 marcelop Exp $
 */

package org.eclipse.emf.codegen.merge.java;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.codegen.merge.java.facade.FacadeVisitor;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.emf.codegen.merge.java.facade.JInitializer;
import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JPackage;
import org.eclipse.emf.codegen.merge.java.facade.JType;

/**
 * A dictionary of signatures and {@link JNode}}.
 */
public class JPatternDictionary extends FacadeVisitor
{
  protected static final Pattern COMMENT = Pattern.compile("/\\*.*?\\*/", Pattern.MULTILINE | Pattern.DOTALL);
  protected static final Object [] NO_ARGUMENTS = new Object[0];
  
  protected JControlModel controlModel;
  protected JPackage jPackage;
  
  protected Map importMap;
  protected Map typeMap;
  protected Map initializerMap;
  protected Map fieldMap;
  protected Map methodMap;
  protected Map markupMap;  
  protected Set noImportSet;
  
  public JPatternDictionary(JCompilationUnit compilationUnit, JControlModel controlModel)
  {
    super();
    this.controlModel = controlModel;
    start(compilationUnit);
  }
  
  public JPackage getJPackage()
  {
    return jPackage;
  }
  
  public Map getImportMap()
  {
    if (importMap == null)
    {
      importMap = new HashMap();
    }
    return importMap;
  }

  public Map getTypeMap()
  {
    if (typeMap == null)
    {
      typeMap = new HashMap();
    }
    return typeMap;
  }

  public Map getInitializerMap()
  {
    if (initializerMap == null)
    {
      initializerMap = new HashMap();
    }
    return initializerMap;
  }

  public Map getFieldMap()
  {
    if (fieldMap == null)
    {
      fieldMap = new HashMap();
    }
    return fieldMap;
  }

  public Map getMethodMap()
  {
    if (methodMap == null)
    {
      methodMap = new HashMap();
    }
    return methodMap;
  }

  public Map getMarkupMap()
  {
    if (markupMap == null)
    {
      markupMap = new HashMap();
    }
    return markupMap;
  }

  public boolean isMarkedUp(Pattern markupPattern, JNode node)
  {
    if (markupPattern == null)
    {
      return true;
    }
    else
    {
      for (Iterator markupEntries = getMarkupMap().entrySet().iterator(); markupEntries.hasNext(); )
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
  
  protected Set getNoImporterSet()
  {
    if (noImportSet == null)
    {
      noImportSet = new HashSet();
    }
    return noImportSet;
  }

  public boolean isNoImport(JImport jImport)
  {
    return noImportSet != null && getNoImporterSet().contains(jImport.getQualifiedName());
  }
  
    
  protected boolean visit(JCompilationUnit compilationUnit)
  {
    if (controlModel.getNoImportPattern() != null)
    {
      Matcher matcher = controlModel.getNoImportPattern().matcher(compilationUnit.getContents());
      while (matcher.find())
      {
        getNoImporterSet().add(matcher.group(1));
      }
    }
    return true;
  }
  
  protected boolean visit(JPackage jPackage)
  {
    this.jPackage = jPackage;
    return super.visit(jPackage);
  }
  
  protected boolean visit(JType type)
  {
    getTypeMap().put(type.getQualifiedName(), type);
    return true;
  }  
  
  protected boolean visit(JImport jImport)
  {
    getImportMap().put(jImport.getQualifiedName(), jImport);
    return super.visit(jImport);
  }
    
  protected boolean visit(JInitializer initializer)
  {
    getInitializerMap().put(initializer.getQualifiedName(), initializer);
    return super.visit(initializer);
  }
  
  protected boolean visit(JField field)
  {
    getFieldMap().put(field.getQualifiedName(), field);
    return super.visit(field);
  }
  
  protected boolean visit(JMethod method)
  {
    getMethodMap().put(method.getQualifiedName(), method);
    return super.visit(method);
  }
    
  protected void afterVisit(JNode node)
  {
    for (Iterator dictionaryPatterns = controlModel.getDictionaryPatterns().iterator(); dictionaryPatterns.hasNext(); )
    {
      JControlModel.DictionaryPattern dictionaryPattern = (JControlModel.DictionaryPattern)dictionaryPatterns.next();
      if (dictionaryPattern.getSelectorFeature().getFeatureClass().isInstance(node))
      {
        try
        {
          String selection = (String)dictionaryPattern.getSelectorFeature().getFeatureMethod().invoke(node, NO_ARGUMENTS);
          if (dictionaryPattern.getSelectorFeature().getFeatureMethod().getName().equals("getComment"))
          {
            String contents = node.getContents();
            for (int start = 0, end = contents.length(), count = 0; start < end; )
            {
              Matcher matcher = COMMENT.matcher(contents.subSequence(start, end));
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
                Collection collection = (Collection)getMarkupMap().get(markup);
                if (collection == null)
                {
                  collection = new HashSet();
                  getMarkupMap().put(markup, collection);
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
}
