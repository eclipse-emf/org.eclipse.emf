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
 * $Id: JMerger.java,v 1.1 2006/01/18 20:42:15 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.emf.codegen.merge.java.facade.JInitializer;
import org.eclipse.emf.codegen.merge.java.facade.JMember;
import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JPackage;
import org.eclipse.emf.codegen.merge.java.facade.JType;
import org.eclipse.emf.codegen.merge.java.facade.jdom.JDOMFacadeHelper;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;


/**
 * This implements the method {@link #run}, 
 * which is called just like main during headless workbench invocation.
 */
public class JMerger
{
  public static final String DEFAULT_FACADE_HELPER_CLASS = JDOMFacadeHelper.class.getName(); 
  
  protected JCompilationUnit sourceCompilationUnit;
  protected JCompilationUnit targetCompilationUnit;
  protected JPatternDictionary sourcePatternDictionary;
  protected JPatternDictionary targetPatternDictionary;
  protected Map sourceToTargetMap = new HashMap();
  protected Map targetToSourceMap = new HashMap();
  protected Map orderedSourceChildrenMap = new HashMap();
  protected boolean isBlocked;
  protected boolean fixInterfaceBrace;
  protected JControlModel controlModel;

  /**
   * This creates an empty instances, an when used as a runnable.
   */
  public JMerger()
  {
  }

  public JMerger(JControlModel controlModel)
  {
    this();
    this.controlModel = controlModel;
  }

  public void merge()
  {
    pullTargetCompilationUnit();
    if (!isBlocked)
    {
      pushSourceCompilationUnit();
      sweepTargetCompilationUnit();
      sortTargetCompilationUnit();
    }
  }

  public void remerge()
  {
    sourceToTargetMap.clear();
    targetToSourceMap.clear();
    orderedSourceChildrenMap.clear();
    isBlocked = false;
    merge();
  }

  public boolean isFixInterfaceBrace()
  {
    return fixInterfaceBrace;
  }

  public void setFixInterfaceBrace(boolean fixInterfaceBrace)
  {
    this.fixInterfaceBrace = fixInterfaceBrace;
  }

  public String getSourceCompilationUnitContents()
  {
    return sourceCompilationUnit.getContents();
  }
  
  public JCompilationUnit getSourceCompilationUnit()
  {
    return sourceCompilationUnit;
  }
  
  public JControlModel getControlModel()
  {
    return controlModel;
  }

  public void setSourceCompilationUnit(JCompilationUnit sourceCompilationUnit)
  {
    this.sourceCompilationUnit =  sourceCompilationUnit;
    sourcePatternDictionary = new JPatternDictionary(sourceCompilationUnit, getControlModel());
    // System.out.println("-- source --");
    // sourcePatternDictionary.dumpMarkup();
  }
  
  protected static final Pattern INTERFACE_BRACE_PATTERN = 
    Pattern.compile
      ("(?:\\n\\r|\\r\\n|\\n|\\r)(\\s*)(?:public|private|protected|static|\\s)*(?:interface|class)\\s*[^\\{\\n\\r]*(\\{)(\\n\\r|\\r\\n|\\n|\\r)", 
       Pattern.MULTILINE); // }}

  public String getTargetCompilationUnitContents()
  {
    String result = targetCompilationUnit.getContents();
    if (fixInterfaceBrace)
    {
      Matcher matcher = INTERFACE_BRACE_PATTERN.matcher(result);
      int offset = 0;
      while (matcher.find())
      {
        if (getControlModel().standardBraceStyle)
        {
          if (result.charAt(matcher.start(2) - 1) != ' ')
          {
            result = 
              result.substring(0, offset + matcher.start(2)) + 
                " {" + result.substring(offset + matcher.end(2), result.length()); // }
            offset += 1;
          }
        }
        else
        {
          result = 
            result.substring(0, offset + matcher.start(2)) + matcher.group(3) + 
              matcher.group(1) + "{" + result.substring(offset + matcher.end(2), result.length()); // }
          offset += matcher.group(1).length() + matcher.group(3).length();
        }
      }
    }
    return result;
  }

  public JCompilationUnit getTargetCompilationUnit()
  {
    return targetCompilationUnit;
  }

  public void setTargetCompilationUnit(JCompilationUnit targetCompilationUnit)
  {
    this.targetCompilationUnit = targetCompilationUnit;
    targetPatternDictionary = new JPatternDictionary(targetCompilationUnit, getControlModel());
    // System.out.println("-- target --");
    // targetPatternDictionary.dumpMarkup();
  }

  public JPatternDictionary getSourcePatternDictionary()
  {
    return sourcePatternDictionary;
  }

  public void setSourcePatternDictionary(JPatternDictionary sourcePatternDictionary)
  {
    this.sourcePatternDictionary = sourcePatternDictionary;
  }

  public JPatternDictionary getTargetPatternDictionary()
  {
    return targetPatternDictionary;
  }

  public void setTargetPatternDictionary(JPatternDictionary targetPatternDictionary)
  {
    this.targetPatternDictionary = targetPatternDictionary;
  }

  public Map getSourceToTargetMap()
  {
    return sourceToTargetMap;
  }

  public void setSourceToTargetMap(Map sourceToTargetMap)
  {
    this.sourceToTargetMap = sourceToTargetMap;
  }

  /**
   * Create a JDOM from a URI.
   */
  public JCompilationUnit createCompilationUnitForURI(String uri)
  {
    try
    {
      URL url = null;
      try
      {
        url = new URL(uri);
      }
      catch (MalformedURLException exception)
      {
        url = new URL("file:" + uri);
      }
      if (url != null)
      {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
        byte [] input = new byte [bufferedInputStream.available()];
        bufferedInputStream.read(input);
        bufferedInputStream.close();
        return getControlModel().getFacadeHelper().createCompilationUnit(url.toString(), new String(input));
      }
    }
    catch (IOException exception)
    {
      // exception.printStackTrace();
    }

    return null;
  }

  public JCompilationUnit createCompilationUnitForInputStream(InputStream inputStream)
  {
    try
    {
      BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
      byte [] input = new byte [bufferedInputStream.available()];
      bufferedInputStream.read(input);
      bufferedInputStream.close();
      return getControlModel().getFacadeHelper().createCompilationUnit("NAME", new String(input));
    }
    catch (IOException exception)
    {
      // exception.printStackTrace();
    }
    return null;
  }

  /**
   * Create a JDOM from contents.
   */
  public JCompilationUnit createCompilationUnitForContents(String contents)
  {
    return getControlModel().getFacadeHelper().createCompilationUnit("NAME", contents);
  }

/////////////////////////////////  PULL  /////////////////////////////////////

  protected void pullTargetCompilationUnit()
  {
    if (targetCompilationUnit == null)
    {
      setTargetCompilationUnit((JCompilationUnit)insertClone(sourceCompilationUnit));
    }
    else
    {
      map(sourceCompilationUnit, targetCompilationUnit);
      applyPullRules(sourceCompilationUnit, targetCompilationUnit);

/*
    // PULL Header 
    //
    String sourceHeader = sourceCompilationUnit.getHeader();
    if (sourceHeader != null)
    {
      targetCompilationUnit.setHeader(sourceHeader);
    }

*/
      for (Iterator i = targetCompilationUnit.getChildren().iterator(); i.hasNext();)
      {
        JNode child = (JNode)i.next();
        if (child instanceof JPackage)
        {
          pullTargetPackage((JPackage)child);
        }
        else if (child instanceof JImport)
        {
          pullTargetImport((JImport)child);
        }
        else if (child instanceof JType)
        {
          JType type = (JType)child;
          isBlocked = 
            getControlModel().getBlockPattern() != null && 
              type.getComment() != null &&
              getControlModel().getBlockPattern().matcher(type.getComment()).find();
          if (!isBlocked)
          {
            pullTargetType(type);
          }
        }
      }
    }
  }

  protected void pullTargetPackage(JPackage targetPackage)
  {
    JPackage sourcePackage = sourcePatternDictionary.getJPackage();
    map(sourcePackage, targetPackage);
    applyPullRules(sourcePackage, targetPackage);

/*
    // PULL Name
    //
    targetPackage.setName(sourcePackage.getName());
*/
  }

  protected void pullTargetImport(JImport targetImport)
  {
    JImport sourceImport = (JImport)sourcePatternDictionary.getImportMap().get(targetImport.getQualifiedName());
    map(sourceImport, targetImport);
    if (sourceImport != null)
    {
      applyPullRules(sourceImport, targetImport);
    }
  }

  protected void pullTargetType(JType targetType)
  {
    JType sourceType = (JType)sourcePatternDictionary.getTypeMap().get(targetType.getQualifiedName());

    map(sourceType, targetType);
    if (sourceType != null)
    {
      applyPullRules(sourceType, targetType);

/*
      // PULL: Comment
      //
      String sourceComment = sourceType.getComment();
      if (sourceComment != null)
      {
        targetType.setComment(sourceComment);
      }
*/
/*
      // PULL: Flags
      //
      int sourceFlags = sourceType.getFlags();
      targetType.setFlags(sourceFlags);

      // PULL: Superclass
      //
      targetType.setSuperclass(sourceType.getSuperclass());

      // PULL: SuperInterfaces
      //
      ArrayList additionalSuperInterfaces = new ArrayList();
      String [] sourceSuperInterfaces = sourceType.getSuperInterfaces();
      if (sourceSuperInterfaces != null)
      {
        additionalSuperInterfaces.addAll(Arrays.asList(sourceSuperInterfaces));
        String [] targetTypeSuperInterfaces = targetType.getSuperInterfaces();
        if (targetTypeSuperInterfaces != null)
        {
          additionalSuperInterfaces.removeAll(Arrays.asList(targetType.getSuperInterfaces()));
        }
      }
      for (Iterator i = additionalSuperInterfaces.iterator(); i.hasNext(); )
      {
        String superInterface = (String)i.next();
        targetType.addSuperInterface(superInterface);
      }
*/
    }
    
    for (Iterator i = targetType.getChildren().iterator(); i.hasNext();)
    {
      JNode child = (JNode)i.next();
      if (child instanceof JInitializer)
      {
        pullTargetInitializer((JInitializer)child);
      }
      else if (child instanceof JField)
      {
        pullTargetField((JField)child);
      }
      else if (child instanceof JMethod)
      {
        pullTargetMethod((JMethod)child);
      }
      else if (child instanceof JType)
      {
        pullTargetType((JType)child);
      }
    }
  }

  protected void pullTargetInitializer(JInitializer targetInitializer)
  {
    JInitializer sourceInitializer = 
      (JInitializer)sourcePatternDictionary.getInitializerMap().get(targetInitializer.getQualifiedName());
    map(sourceInitializer, targetInitializer);
    if (sourceInitializer != null)
    {
      applyPullRules(sourceInitializer, targetInitializer);

/*
      // PULL: Comment
      //
      String sourceComment = sourceInitializer.getComment();
      if (sourceComment != null)
      {
        targetInitializer.setComment(sourceComment);
      }
*/

/*
      // PULL: Body
      //
      String sourceBody = sourceInitializer.getBody();
      if (sourceBody != null)
      {
        targetInitializer.setBody(sourceBody);
      }
*/
    }
  }

  protected void pullTargetField(JField targetField)
  {
    JField sourceField = 
      (JField)sourcePatternDictionary.getFieldMap().get(targetField.getQualifiedName());

    map(sourceField, targetField);
    if (sourceField != null)
    {
      applyPullRules(sourceField, targetField);

/*
      // PULL: Comment
      //
      String sourceComment = sourceField.getComment();
      if (sourceComment != null)
      {
        targetField.setComment(sourceComment);
      }
*/

/*
      // PULL: Flags
      //
      int sourceFlags = sourceField.getFlags();
      targetField.setFlags(sourceFlags);

      // PULL: Type
      //
      String sourceFieldType = sourceField.getType();
      if (sourceFieldType != null)
      {
        targetField.setType(sourceFieldType);
      }

      // PULL: Initializer
      //
      String sourceFieldInitializer = sourceField.getInitializer();
      if (sourceFieldInitializer != null)
      {
        targetField.setInitializer(sourceFieldInitializer);
      }
*/
    }
  }

  protected void pullTargetMethod(JMethod targetMethod)
  {
    String qualifiedTargetMethodName = targetMethod.getQualifiedName();
    JMethod sourceMethod = 
      (JMethod)sourcePatternDictionary.getMethodMap().get(qualifiedTargetMethodName);

    if (sourceMethod == null && 
          getControlModel().getRedirect() != null && 
          targetMethod.getName() != null &&
          targetMethod.getName().endsWith(getControlModel().getRedirect()))
    {
      int index = qualifiedTargetMethodName.indexOf("("); //)
      qualifiedTargetMethodName =
        qualifiedTargetMethodName.substring(0, index - getControlModel().getRedirect().length()) + 
          qualifiedTargetMethodName.substring(index);
      sourceMethod = 
        (JMethod)sourcePatternDictionary.getMethodMap().get(qualifiedTargetMethodName);
    }

    map(sourceMethod, targetMethod);
    if (sourceMethod != null)
    {
      applyPullRules(sourceMethod, targetMethod);

/*
      // PULL: Comment
      //
      String sourceComment = sourceMethod.getComment();
      if (sourceComment != null)
      {
        targetMethod.setComment(sourceComment);
      }
*/

/*
      // PULL: Flags
      //
      int sourceFlags = sourceMethod.getFlags();
      targetMethod.setFlags(sourceFlags);

      // PULL: Body
      //
      String sourceMethodBody = sourceMethod.getBody();
      if (sourceMethodBody != null)
      {
        targetMethod.setBody(sourceMethodBody);
      }

      // PULL: ReturnType
      //
      String sourceMethodReturnType = sourceMethod.getReturnType();
      if (sourceMethodReturnType != null)
      {
        targetMethod.setReturnType(sourceMethodReturnType);
      }
*/

/*
      // PULL: Exceptions
      //
      ArrayList additionalExceptions = new ArrayList();
      String [] sourceMethodExceptions = sourceMethod.getExceptions();
      if (sourceMethodExceptions != null)
      {
        additionalExceptions.addAll(Arrays.asList(sourceMethodExceptions));
        String [] targetMethodExceptions = targetMethod.getExceptions();
        if (targetMethodExceptions != null)
        {
          additionalExceptions.removeAll(Arrays.asList(targetMethodExceptions));
        }
      }
      for (Iterator i = additionalExceptions.iterator(); i.hasNext(); )
      {
        String exception = (String)i.next();
        targetMethod.addException(exception);
      }
*/
    }
  }
  
  protected static Object [] noArguments = new Object [0];
  protected void applyPullRules(JNode sourceNode, JNode targetNode)
  {
    try
    {
      for (Iterator pullRules = getControlModel().getPullRules().iterator(); pullRules.hasNext(); )
      {
        JControlModel.PullRule pullRule = (JControlModel.PullRule)pullRules.next();
        if (sourcePatternDictionary.isMarkedUp(pullRule.getSourceMarkup(), sourceNode) && 
              targetPatternDictionary.isMarkedUp(pullRule.getTargetMarkup(), targetNode) && 
              pullRule.getSourceGetFeature().getFeatureClass().isInstance(sourceNode) &&
              pullRule.getTargetPutFeature().getFeatureClass().isInstance(targetNode))
        {
          Method sourceGetMethod = pullRule.getSourceGetFeature().getFeatureMethod();
          Object value = sourceGetMethod.invoke(sourceNode, noArguments);
          Method targetPutMethod = pullRule.getTargetPutFeature().getFeatureMethod();
          if (!sourceGetMethod.getReturnType().isArray() || 
                targetPutMethod.getParameterTypes()[0].isAssignableFrom(sourceGetMethod.getReturnType()))
          {
            if (value instanceof String)
            {
              String stringValue = (String)value;
              stringValue = getControlModel().getFacadeHelper().applyFormatRules(stringValue);
              Pattern sourceTransfer = pullRule.getSourceTransfer();
              if (sourceTransfer != null)
              {
                String oldStringValue = (String)sourceGetMethod.invoke(targetNode, noArguments);
                if (oldStringValue != null)
                {
                  Matcher sourceMatcher = sourceTransfer.matcher(stringValue);
                  Matcher targetMatcher = sourceTransfer.matcher(oldStringValue);
                  if (sourceMatcher.groupCount() >= 1 && targetMatcher.groupCount() >= 1)
                  {
                    StringBuffer result = new StringBuffer();
                    int index = 0;
                    while (sourceMatcher.find() && targetMatcher.find())
                    {
                      result.append(stringValue.substring(index, sourceMatcher.start(1)));
                      result.append(targetMatcher.group(1));
                      index =  sourceMatcher.end(1);
                    }
                    // There must be at least one match.
                    //
                    if (result.length() == 0)
                    {
                      stringValue = null;
                    }
                    else
                    {
                      result.append(stringValue.substring(index));
                      stringValue = result.toString();
                    }
                  }
                  else
                  {
                    stringValue = null;
                  }
                }
              }
              value = stringValue;
            }
            if (value != null || 
                  targetPutMethod.getName().equals("setInitializer") ||
                  targetPutMethod.getName().equals("setSuperclass") ||
                  targetPutMethod.getName().equals("setExceptions"))
            {
              // Ignore if there is not substantial change.
              //
              Object oldValue = sourceGetMethod.invoke(targetNode, noArguments);
              if (value == null ? oldValue == null : value.equals(oldValue))
              {
                continue;
              }
              else if (targetPutMethod.getName().equals("setSuperclass"))
              {
                if (oldValue != null && value != null && ((String)oldValue).trim().equals(((String)value).trim()))
                {
                  continue;
                }
              }

              // The block pattern needs to prevent merging of the return type, to allow changing from the modeled data
              // type (Bugzilla 102209). 
              //
              if (sourceGetMethod.getName().equals("getReturnType") &&
                  getControlModel().getBlockPattern() != null && 
                  ((JMethod)targetNode).getComment() != null &&
                  getControlModel().getBlockPattern().matcher(((JMethod)targetNode).getComment()).find())
              {
                continue;
              }

              targetPutMethod.invoke(targetNode, new Object [] { value });
              if (targetPutMethod.getName().equals("setBody") && sourceNode instanceof JMethod)
              {
                JMethod sourceMethod = (JMethod)sourceNode;
                JMethod targetMethod = (JMethod)targetNode;
                String [] sourceParameterNames = sourceMethod.getParameterNames();
                String [] targetParameterTypes = targetMethod.getParameterTypes();
                targetMethod.setParameters(targetParameterTypes, sourceParameterNames);
              }
            }
          }
          else
          {
            ArrayList additionalStrings = new ArrayList();
            String [] sourceStrings = (String [])value;
            if (sourceStrings != null)
            {
              additionalStrings.addAll(Arrays.asList(sourceStrings));
            }

            if (targetPutMethod.getName().equals("addSuperInterface"))
            {
              Pattern sourceTransfer = pullRule.getSourceTransfer();
              if (sourceTransfer != null)
              {
                String comment = ((JMember)targetNode).getComment();
                Matcher matcher = sourceTransfer.matcher(comment);
                while (matcher.find() && matcher.groupCount() >= 1)
                {
                  String clientStrings =
                    comment.substring(matcher.start(matcher.groupCount()), matcher.end(matcher.groupCount()));
  
                  for (StringTokenizer stringTokenizer = new StringTokenizer(clientStrings, ", \t\n\r\f"); 
                       stringTokenizer.hasMoreTokens(); )
                  {
                    String token = stringTokenizer.nextToken();
                    if (!additionalStrings.contains(token))
                    {
                      additionalStrings.add(token);
                    }
                  }
                }
              }

              JType type = (JType)targetNode;
              String [] superInterfaces = (String [])additionalStrings.toArray(new String [additionalStrings.size()]);
              if (type.getSuperInterfaces() == null ?
                   superInterfaces.length != 0 :
                   !Arrays.equals(type.getSuperInterfaces(), superInterfaces))
              {
                type.setSuperInterfaces((String [])additionalStrings.toArray(new String [additionalStrings.size()]));
              }
            }
            else
            {
              String [] oldStringValues = (String [])sourceGetMethod.invoke(targetNode, noArguments);
              List old = oldStringValues == null ? Collections.EMPTY_LIST : Arrays.asList(oldStringValues);
              for (Iterator i = additionalStrings.iterator(); i.hasNext(); )
              {
                String string = (String)i.next();
                if (!old.contains(string))
                {
                  targetPutMethod.invoke(targetNode, new Object [] { string });
                }
              }
            }
          }
        }
      }

    }
    catch (InvocationTargetException exception)
    {
      // exception.printStackTrace();
    }
    catch (IllegalAccessException exception)
    {
      // exception.printStackTrace();
    }
  }


/////////////////////////////////  PUSH  /////////////////////////////////////

  protected void pushSourceCompilationUnit()
  {
    for (Iterator i = sourceCompilationUnit.getChildren().iterator(); i.hasNext();)
    {
      JNode child = (JNode)i.next();
      if (child instanceof JPackage)
      {
        pushSourcePackage((JPackage)child);
      }
      else if (child instanceof JImport)
      {
        pushSourceImport((JImport)child);
      }
      else if (child instanceof JType)
      {
        pushSourceType((JType)child);
      }
    }
  }

  protected void pushSourcePackage(JPackage sourcePackage)
  {
    if (!sourceToTargetMap.containsKey(sourcePackage))
    {
      insertClone(sourcePackage);
    }
  }

  protected void pushSourceImport(JImport sourceImport)
  {
    if (!sourceToTargetMap.containsKey(sourceImport) && !targetPatternDictionary.isNoImport(sourceImport))
    {
      insertClone(sourceImport);
    }
  }

  protected void pushSourceType(JType sourceType)
  {
    if (!sourceToTargetMap.containsKey(sourceType))
    {
      insertClone(sourceType);
    }
    else
    {
      for (Iterator i = sourceType.getChildren().iterator(); i.hasNext();)
      {
        JNode child = (JNode)i.next();
        if (child instanceof JInitializer)
        {
          pushSourceInitializer((JInitializer)child);
        }
        else if (child instanceof JField)
        {
          pushSourceField((JField)child);
        }
        else if (child instanceof JMethod)
        {
          pushSourceMethod((JMethod)child);
        }
        else if (child instanceof JType)
        {
          pushSourceType((JType)child);
        }
      }
    }
  }

  protected void pushSourceInitializer(JInitializer sourceInitializer)
  {
    if (!sourceToTargetMap.containsKey(sourceInitializer))
    {
      insertClone(sourceInitializer);
    }
  }

  protected void pushSourceField(JField sourceField)
  {
    applySortRules(sourceField);
    if (!sourceToTargetMap.containsKey(sourceField))
    {
      insertClone(sourceField);
    }
  }

  protected void pushSourceMethod(JMethod sourceMethod)
  {
    if (!sourceToTargetMap.containsKey(sourceMethod))
    {
      insertClone(sourceMethod);
    }
  }

  public void applySortRules(JNode sourceNode)
  {
    for (Iterator sortRules = getControlModel().getSortRules().iterator(); sortRules.hasNext(); )
    {
      JControlModel.SortRule sortRule = (JControlModel.SortRule)sortRules.next();
      if (sourcePatternDictionary.isMarkedUp(sortRule.getMarkup(), sourceNode)  &&
            sortRule.getSelector().isInstance(sourceNode))
      {
        JNode parent = sourceNode.getParent();
        List children = (List)orderedSourceChildrenMap.get(parent);
        if (children == null)
        {
          children = new ArrayList();
          orderedSourceChildrenMap.put(parent, children);
        }
        children.add(sourceNode);
        break;
      }
    }
  }


/////////////////////////////////  SWEEP  /////////////////////////////////////

  protected void sweepTargetCompilationUnit()
  {
    for (Iterator entries = targetToSourceMap.entrySet().iterator(); entries.hasNext(); )
    {
      Map.Entry entry = (Map.Entry)entries.next();
      if (entry.getValue() == null)
      {
        applySweepRules((JNode)entry.getKey());
      }
    }
  }

  protected void applySweepRules(JNode targetNode)
  {
    for (Iterator sweepRules = getControlModel().getSweepRules().iterator(); sweepRules.hasNext(); )
    {
      JControlModel.SweepRule sweepRule = (JControlModel.SweepRule)sweepRules.next();
      if (sweepRule.getSelector() == JImport.class && targetNode instanceof JImport)
      {
        if (sweepRule.getMarkup().matcher(((JNode)targetNode).getName()).find())
        {
          getControlModel().getFacadeHelper().remove(targetNode);
          break;
        }
      }
      else if (targetPatternDictionary.isMarkedUp(sweepRule.getMarkup(), targetNode)  &&
            sweepRule.getSelector().isInstance(targetNode))
      {
        getControlModel().getFacadeHelper().remove(targetNode);
        break;
      }
    }
  }

/////////////////////////////////  SORT  /////////////////////////////////////

  protected void sortTargetCompilationUnit()
  {
    for (Iterator values = orderedSourceChildrenMap.values().iterator(); values.hasNext(); )
    {
      List children = (List)values.next();
      if (children.size() > 2)
      {
        Iterator i = children.iterator();
        JNode sourceNode = (JNode)i.next();
        JNode previousTargetNode = (JNode)sourceToTargetMap.get(sourceNode);
        do
        {
          sourceNode = (JNode)i.next();
          JNode nextTargetNode = (JNode)sourceToTargetMap.get(sourceNode);

          boolean reorder = true;
          for (JNode domNode = getControlModel().getFacadeHelper().getPrevious(nextTargetNode); domNode != null; domNode = getControlModel().getFacadeHelper().getPrevious(domNode))
          {
            if (domNode == previousTargetNode)
            {
              reorder = false;
              break;
            }
          }

          if (reorder)
          {
            getControlModel().getFacadeHelper().remove(nextTargetNode);
            if (getControlModel().getFacadeHelper().getNext(previousTargetNode) == null)
            {
              getControlModel().getFacadeHelper().addChild(previousTargetNode.getParent(), nextTargetNode);
            }
            else
            {
              getControlModel().getFacadeHelper().insertSibling(previousTargetNode, nextTargetNode, false);
            }
          }

          previousTargetNode = nextTargetNode;
        }
        while (i.hasNext());
      }
    }
  }

/////////////////////////////////  CLONE AND MAP  /////////////////////////////////////

  protected JNode insertClone(JNode sourceNode)
  {
    Object context = targetCompilationUnit != null ? getControlModel().getFacadeHelper().getContext(targetCompilationUnit) : null; 
    JNode targetNode = getControlModel().getFacadeHelper().cloneNode(context, sourceNode);
    if (targetNode != null)
    {
      map(sourceNode, targetNode);
      mapChildren(sourceNode, targetNode);
    }
    else
    {
      // System.err.println("Warning: Cannot clone '" + sourceNode.getContents() + "'");
    }
        
    for (JNode previousNode = getControlModel().getFacadeHelper().getPrevious(sourceNode); previousNode != null; previousNode = getControlModel().getFacadeHelper().getPrevious(previousNode))
    {
      JNode targetSibling = (JNode)sourceToTargetMap.get(previousNode);
      if (targetSibling != null)
      {
        JNode targetNextSibling = getControlModel().getFacadeHelper().getNext(targetSibling);
        if (targetNextSibling == null)
        {
          getControlModel().getFacadeHelper().addChild(targetSibling.getParent(), targetNode);
        }
        else
        {
          getControlModel().getFacadeHelper().insertSibling(targetNextSibling, targetNode, true);
        }

        return targetNode;
      }
    }
    
    JNode sourceParent = sourceNode.getParent();
    if (sourceParent != null)
    {
      JNode targetParent = (JNode)sourceToTargetMap.get(sourceParent);
      JNode targetSibling = getControlModel().getFacadeHelper().getFirstChild(targetParent);
      if (targetSibling == null)
      {
        getControlModel().getFacadeHelper().addChild(targetParent.getParent(), targetNode);
      }
      else
      {
        getControlModel().getFacadeHelper().insertSibling(targetSibling, targetNode, true);
      }
    }
    return targetNode;
  }

  protected void mapChildren(JNode sourceNode, JNode targetNode)
  {
    map(sourceNode, targetNode);
    for (JNode sourceChild = getControlModel().getFacadeHelper().getFirstChild(sourceNode), targetChild = getControlModel().getFacadeHelper().getFirstChild(targetNode); 
         sourceChild != null;
         sourceChild = getControlModel().getFacadeHelper().getNext(sourceChild), targetChild = getControlModel().getFacadeHelper().getNext(targetChild))
    {
      mapChildren(sourceChild, targetChild);
    }
  }

  protected void map(JNode sourceNode, JNode targetNode)
  {
    if (sourceNode != null)
    {
      sourceToTargetMap.put(sourceNode, targetNode);
    }
    targetToSourceMap.put(targetNode, sourceNode);
  }

  /**
   * This is called with the command line arguments of a headless workbench invocation.
   */
  public Object run(Object object) 
  {
    try
    {
      // Three arguments are expected: the .xml getControlModel() URI, the source java URI, and the target java URI.
      //
      String contents = execute(new BasicMonitor(), (String[])object);

      System.out.println("**********************************************");
      System.out.println(contents);

      return new Integer(0);
    }
    catch (Exception exception)
    {
      // exception.printStackTrace();
      return new Integer(1);
    }
  }
  
  /**
   * Utilitiy for headless operations.
   * 
   * @param mergeXML
   * @param sourceURI
   * @param targetURI
   * @return the merged content
   */
  public String execute(Monitor monitor, String[] arguments)
  {
    String mergeXML = arguments[0];
    String sourceURI = arguments[1];
    String targetURI = arguments[2];
    String facadeHelperClass = arguments.length > 3 ? arguments[3] : DEFAULT_FACADE_HELPER_CLASS;
    
    // Create the options model.
    //
    controlModel =  new JControlModel();
    controlModel.initialize(CodeGenUtil.instantiateFacadeHelper(facadeHelperClass), mergeXML);

    // Create the source and target JDOMs.
    //
    sourceCompilationUnit = createCompilationUnitForURI(sourceURI);
    targetCompilationUnit = createCompilationUnitForURI(targetURI);

    // Create a pattern dictionary for each.
    //
    sourcePatternDictionary = new JPatternDictionary(sourceCompilationUnit, getControlModel());
    targetPatternDictionary = new JPatternDictionary(targetCompilationUnit, getControlModel());
    
    merge();
    
    return targetCompilationUnit.getContents();
  }
}
