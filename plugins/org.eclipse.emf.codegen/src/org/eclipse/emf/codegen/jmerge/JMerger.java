/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: JMerger.java,v 1.28 2007/04/27 16:01:49 marcelop Exp $
 */
package org.eclipse.emf.codegen.jmerge;


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

import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.jdom.DOMFactory;
import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;
import org.eclipse.jdt.core.jdom.IDOMField;
import org.eclipse.jdt.core.jdom.IDOMImport;
import org.eclipse.jdt.core.jdom.IDOMInitializer;
import org.eclipse.jdt.core.jdom.IDOMMember;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jdt.core.jdom.IDOMNode;
import org.eclipse.jdt.core.jdom.IDOMPackage;
import org.eclipse.jdt.core.jdom.IDOMType;

import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.codegen.util.CodeGenUtil;


/**
 * This implements the method {@link #run}, 
 * which is called just like main during headless workbench invocation.
 * @deprecated in 2.2.0. Use {@link org.eclipse.emf.codegen.merge.java.JMerger} instead.
 */
@Deprecated
@SuppressWarnings("unchecked")
public class JMerger
{
  protected DOMFactory jdomFactory = new DOMFactory();
  protected JControlModel jControlModel;
  protected IDOMCompilationUnit sourceCompilationUnit;
  protected IDOMCompilationUnit targetCompilationUnit;
  protected JPatternDictionary sourcePatternDictionary;
  protected JPatternDictionary targetPatternDictionary;
  protected Map sourceToTargetMap = new HashMap();
  protected Map targetToSourceMap = new HashMap();
  protected Map orderedSourceChildrenMap = new HashMap();
  protected boolean isBlocked;
  protected boolean fixInterfaceBrace;

  /**
   * This creates an empty instances, an when used as a runnable.
   */
  public JMerger()
  {
    if ("1.4".compareTo(JavaCore.getOption(JavaCore.COMPILER_SOURCE)) < 0)
    {
      CodeGenPlugin.INSTANCE.log(CodeGenPlugin.INSTANCE.getString("_UI_JDOMInvalidSourceCompatibility_message"));
    }    
  }

  public JMerger(JControlModel jControlModel, IDOMCompilationUnit sourceCompilationUnit, IDOMCompilationUnit targetCompilationUnit) 
  {
    this();
    this.jControlModel = jControlModel;
    setSourceCompilationUnit(sourceCompilationUnit);
    setTargetCompilationUnit(targetCompilationUnit);
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

  public JControlModel getControlModel()
  {
    return jControlModel;
  }

  public void setControlModel(JControlModel jControlModel)
  {
    this.jControlModel = jControlModel;
  }
  
  public String getSourceCompilationUnitContents()
  {
    return sourceCompilationUnit.getContents();
  }
  
  public IDOMCompilationUnit getSourceCompilationUnit()
  {
    return sourceCompilationUnit;
  }

  public void setSourceCompilationUnit(IDOMCompilationUnit sourceCompilationUnit)
  {
    this.sourceCompilationUnit =  sourceCompilationUnit;
    sourcePatternDictionary = new JPatternDictionary(sourceCompilationUnit, jControlModel);
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
        if (getControlModel().convertToStandardBraceStyle())
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

  public IDOMCompilationUnit getTargetCompilationUnit()
  {
    return targetCompilationUnit;
  }

  public void setTargetCompilationUnit(IDOMCompilationUnit targetCompilationUnit)
  {
    this.targetCompilationUnit = targetCompilationUnit;
    targetPatternDictionary = new JPatternDictionary(targetCompilationUnit, jControlModel);
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
  public IDOMCompilationUnit createCompilationUnitForURI(String uri)
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

      BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
      byte [] input = new byte [bufferedInputStream.available()];
      bufferedInputStream.read(input);
      bufferedInputStream.close();
      return jdomFactory.createCompilationUnit(new String(input), url.toString());
    }
    catch (IOException exception)
    {
      // exception.printStackTrace();
    }

    return null;
  }

  public IDOMCompilationUnit createCompilationUnitForInputStream(InputStream inputStream)
  {
    try
    {
      BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
      byte [] input = new byte [bufferedInputStream.available()];
      bufferedInputStream.read(input);
      bufferedInputStream.close();
      return jdomFactory.createCompilationUnit(new String(input), "NAME");
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
  public IDOMCompilationUnit createCompilationUnitForContents(String contents)
  {
    return jdomFactory.createCompilationUnit(contents, "NAME");
  }


/////////////////////////////////  PULL  /////////////////////////////////////

  protected void pullTargetCompilationUnit()
  {
    if (targetCompilationUnit == null)
    {
      setTargetCompilationUnit((IDOMCompilationUnit)insertClone(sourceCompilationUnit));
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
      for (IDOMNode child = targetCompilationUnit.getFirstChild(); child != null; child = child.getNextNode())
      {
        switch (child.getNodeType())
        {
          case IDOMNode.PACKAGE: 
          {
            pullTargetPackage((IDOMPackage)child);
            break;
          }
          case IDOMNode.IMPORT:
          {
            pullTargetImport((IDOMImport)child);
            break;
          }
          case IDOMNode.TYPE: 
          {
            IDOMType type = (IDOMType)child;
            isBlocked = 
              jControlModel.getBlockPattern() != null && 
                type.getComment() != null &&
                jControlModel.getBlockPattern().matcher(type.getComment()).find();
            if (!isBlocked)
            {
              pullTargetType(type);
            }
            break;
          }
        }
      }
    }
  }

  protected void pullTargetPackage(IDOMPackage targetPackage)
  {
    IDOMPackage sourcePackage = sourcePatternDictionary.getPackage();
    map(sourcePackage, targetPackage);
    applyPullRules(sourcePackage, targetPackage);

/*
    // PULL Name
    //
    targetPackage.setName(sourcePackage.getName());
*/
  }

  protected void pullTargetImport(IDOMImport targetImport)
  {
    IDOMImport sourceImport = 
      (IDOMImport)sourcePatternDictionary.getImportMap().get(targetPatternDictionary.getQualifiedName(targetImport));
    map(sourceImport, targetImport);
    if (sourceImport != null)
    {
      applyPullRules(sourceImport, targetImport);
    }
  }

  protected void pullTargetType(IDOMType targetType)
  {
    IDOMType sourceType = (IDOMType)sourcePatternDictionary.getTypeMap().get(targetPatternDictionary.getQualifiedName(targetType));

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

    for (IDOMNode child = targetType.getFirstChild(); child != null; child = child.getNextNode())
    {
      switch (child.getNodeType())
      {
        case IDOMNode.INITIALIZER:
        {
          pullTargetInitializer((IDOMInitializer)child);
          break;
        }
        case IDOMNode.FIELD:
        {
          pullTargetField((IDOMField)child);
          break;
        }
        case IDOMNode.METHOD: 
        {
          pullTargetMethod((IDOMMethod)child);
          break;
        }
        case IDOMNode.TYPE: 
        {
          pullTargetType((IDOMType)child);
          break;
        }
      }
    }
  }

  protected void pullTargetInitializer(IDOMInitializer targetInitializer)
  {
    IDOMInitializer sourceInitializer = 
      (IDOMInitializer)sourcePatternDictionary.getInitializerMap().get(targetPatternDictionary.getQualifiedName(targetInitializer));
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

  protected void pullTargetField(IDOMField targetField)
  {
    IDOMField sourceField = 
      (IDOMField)sourcePatternDictionary.getFieldMap().get(targetPatternDictionary.getQualifiedName(targetField));

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

  protected void pullTargetMethod(IDOMMethod targetMethod)
  {
    String qualifiedTargetMethodName = targetPatternDictionary.getQualifiedName(targetMethod);
    IDOMMethod sourceMethod = 
      (IDOMMethod)sourcePatternDictionary.getMethodMap().get(qualifiedTargetMethodName);

    if (sourceMethod == null && 
          jControlModel.getRedirect() != null && 
          targetMethod.getName() != null &&
          targetMethod.getName().endsWith(jControlModel.getRedirect()))
    {
      int index = qualifiedTargetMethodName.indexOf("("); //)
      qualifiedTargetMethodName =
        qualifiedTargetMethodName.substring(0, index - jControlModel.getRedirect().length()) + 
          qualifiedTargetMethodName.substring(index);
      sourceMethod = 
        (IDOMMethod)sourcePatternDictionary.getMethodMap().get(qualifiedTargetMethodName);
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
  
  protected String applyFormatRules(String value)
  {
    return CodeGenUtil.convertFormat(jControlModel.getLeadingTabReplacement(), jControlModel.convertToStandardBraceStyle(), value);
  }

  protected static Object [] noArguments = new Object [0];
  protected void applyPullRules(IDOMNode sourceNode, IDOMNode targetNode)
  {
    try
    {
      for (Iterator pullRules = jControlModel.getPullRules().iterator(); pullRules.hasNext(); )
      {
        JControlModel.PullRule pullRule = (JControlModel.PullRule)pullRules.next();
        if (sourcePatternDictionary.isMarkedUp(pullRule.getSourceMarkup(), sourceNode) && 
              targetPatternDictionary.isMarkedUp(pullRule.getTargetMarkup(), targetNode) && 
              pullRule.getSourceGetFeature().getFeatureMethod() != null &&
              pullRule.getSourceGetFeature().getFeatureClass().isInstance(sourceNode) &&
              pullRule.getTargetPutFeature().getFeatureMethod() != null &&
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
              stringValue = applyFormatRules(stringValue);
              Pattern sourceTransfer = pullRule.getSourceTransfer();
              if (sourceTransfer != null)
              {
                String oldStringValue = (String)sourceGetMethod.invoke(targetNode, noArguments);
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
                  jControlModel.getBlockPattern() != null && 
                  ((IDOMMethod)targetNode).getComment() != null &&
                  jControlModel.getBlockPattern().matcher(((IDOMMethod)targetNode).getComment()).find())
              {
                continue;
              }

              targetPutMethod.invoke(targetNode, new Object [] { value });
              if (targetPutMethod.getName().equals("setBody") && sourceNode instanceof IDOMMethod)
              {
                IDOMMethod sourceMethod = (IDOMMethod)sourceNode;
                IDOMMethod targetMethod = (IDOMMethod)targetNode;
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
                String comment = ((IDOMMember)targetNode).getComment();
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

              IDOMType type = (IDOMType)targetNode;
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
    for (IDOMNode child = sourceCompilationUnit.getFirstChild(); child != null; child = child.getNextNode())
    {
      switch (child.getNodeType())
      {
        case IDOMNode.PACKAGE: 
        {
          pushSourcePackage((IDOMPackage)child);
          break;
        }
        case IDOMNode.IMPORT:
        {
          pushSourceImport((IDOMImport)child);
          break;
        }
        case IDOMNode.TYPE: 
        {
          pushSourceType((IDOMType)child);
          break;
        }
      }
    }
  }

  protected void pushSourcePackage(IDOMPackage sourcePackage)
  {
    if (!sourceToTargetMap.containsKey(sourcePackage))
    {
      insertClone(sourcePackage);
    }
  }

  protected void pushSourceImport(IDOMImport sourceImport)
  {
    if (!sourceToTargetMap.containsKey(sourceImport) && !targetPatternDictionary.isNoImport(sourceImport))
    {
      insertClone(sourceImport);
    }
  }

  protected void pushSourceType(IDOMType sourceType)
  {
    if (!sourceToTargetMap.containsKey(sourceType))
    {
      insertClone(sourceType);
    }
    else
    {
      for (IDOMNode child = sourceType.getFirstChild(); child != null; child = child.getNextNode())
      {
        switch (child.getNodeType())
        {
          case IDOMNode.INITIALIZER:
          {
            pushSourceInitializer((IDOMInitializer)child);
            break;
          }
          case IDOMNode.FIELD:
          {
            pushSourceField((IDOMField)child);
            break;
          }
          case IDOMNode.METHOD: 
          {
            pushSourceMethod((IDOMMethod)child);
            break;
          }
          case IDOMNode.TYPE: 
          {
            pushSourceType((IDOMType)child);
            break;
          }
        }
      }
    }
  }

  protected void pushSourceInitializer(IDOMInitializer sourceInitializer)
  {
    if (!sourceToTargetMap.containsKey(sourceInitializer))
    {
      insertClone(sourceInitializer);
    }
  }

  protected void pushSourceField(IDOMField sourceField)
  {
    applySortRules(sourceField);
    if (!sourceToTargetMap.containsKey(sourceField))
    {
      insertClone(sourceField);
    }
  }

  protected void pushSourceMethod(IDOMMethod sourceMethod)
  {
    if (!sourceToTargetMap.containsKey(sourceMethod))
    {
      insertClone(sourceMethod);
    }
  }

  public void applySortRules(IDOMNode sourceNode)
  {
    for (Iterator sortRules = jControlModel.getSortRules().iterator(); sortRules.hasNext(); )
    {
      JControlModel.SortRule sortRule = (JControlModel.SortRule)sortRules.next();
      if (sourcePatternDictionary.isMarkedUp(sortRule.getMarkup(), sourceNode)  &&
            sortRule.getSelector().isInstance(sourceNode))
      {
        IDOMNode parent = sourceNode.getParent();
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
        applySweepRules((IDOMNode)entry.getKey());
      }
    }
  }

  protected void applySweepRules(IDOMNode targetNode)
  {
    for (Iterator sweepRules = jControlModel.getSweepRules().iterator(); sweepRules.hasNext(); )
    {
      JControlModel.SweepRule sweepRule = (JControlModel.SweepRule)sweepRules.next();
      if (sweepRule.getSelector() == IDOMImport.class && targetNode instanceof IDOMImport)
      {
        if (sweepRule.getMarkup().matcher(targetNode.getName()).find())
        {
          targetNode.remove();
          break;
        }
      }
      else if (targetPatternDictionary.isMarkedUp(sweepRule.getMarkup(), targetNode)  &&
            sweepRule.getSelector().isInstance(targetNode))
      {
        targetNode.remove();
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
        IDOMNode sourceNode = (IDOMNode)i.next();
        IDOMNode previousTargetNode = (IDOMNode)sourceToTargetMap.get(sourceNode);
        do
        {
          sourceNode = (IDOMNode)i.next();
          IDOMNode nextTargetNode = (IDOMNode)sourceToTargetMap.get(sourceNode);

          boolean reorder = true;
          for (IDOMNode domNode = nextTargetNode.getPreviousNode(); domNode != null; domNode = domNode.getPreviousNode())
          {
            if (domNode == previousTargetNode)
            {
              reorder = false;
              break;
            }
          }

          if (reorder)
          {
            nextTargetNode.remove();
            if (previousTargetNode.getNextNode() == null)
            {
              previousTargetNode.getParent().addChild(nextTargetNode);
            }
            else
            {
              previousTargetNode.getNextNode().insertSibling(nextTargetNode);
            }
          }

          previousTargetNode = nextTargetNode;
        }
        while (i.hasNext());
      }
    }
  }

/////////////////////////////////  CLONE AND MAP  /////////////////////////////////////

  protected IDOMNode insertClone(IDOMNode sourceNode)
  {
    IDOMNode targetNode = null;
    switch (sourceNode.getNodeType())
    {
      case IDOMNode.COMPILATION_UNIT:
      {
        targetNode = 
          jdomFactory.createCompilationUnit
            (applyFormatRules(sourceNode.getContents()), ((IDOMCompilationUnit)sourceNode).getName());
        break;
      }
      case IDOMNode.PACKAGE:
      {
        targetNode = jdomFactory.createPackage(applyFormatRules(sourceNode.getContents()));
        break;
      }
      case IDOMNode.IMPORT:
      {
        targetNode = jdomFactory.createImport(applyFormatRules(sourceNode.getContents()));
        break;
      }
      case IDOMNode.TYPE:
      {
        targetNode = jdomFactory.createType(applyFormatRules(sourceNode.getContents()));
        break;
      }
      case IDOMNode.INITIALIZER:
      {
        targetNode = jdomFactory.createInitializer(applyFormatRules(sourceNode.getContents()));
        break;
      }
      case IDOMNode.FIELD:
      {
        targetNode = jdomFactory.createField(applyFormatRules(sourceNode.getContents()));
        break;
      }
      case IDOMNode.METHOD:
      {
        targetNode = jdomFactory.createMethod(applyFormatRules(sourceNode.getContents()));
        break;
      }
      default:
      {
        targetNode = (IDOMNode)sourceNode.clone();
        break;
      }
    }

    if (targetNode != null)
    {
      map(sourceNode, targetNode);
      mapChildren(sourceNode, targetNode);
    }
    else
    {
      // System.err.println("Warning: Cannot clone '" + sourceNode.getContents() + "'");
    }
    for (IDOMNode previousNode = sourceNode.getPreviousNode(); previousNode != null; previousNode = previousNode.getPreviousNode())
    {
      IDOMNode targetSibling = (IDOMNode)sourceToTargetMap.get(previousNode);
      if (targetSibling != null)
      {
        IDOMNode targetNextSibling = targetSibling.getNextNode();
        if (targetNextSibling == null)
        {
          targetSibling.getParent().addChild(targetNode);
        }
        else
        {
          targetNextSibling.insertSibling(targetNode);
        }

        return targetNode;
      }
    }
    if (sourceNode.getParent() != null)
    {
      IDOMNode targetParent = (IDOMNode)sourceToTargetMap.get(sourceNode.getParent());
      IDOMNode targetSibling = targetParent.getFirstChild();
      if (targetSibling == null)
      {
        targetParent.addChild(targetNode);
      }
      else
      {
        targetSibling.insertSibling(targetNode);
      }
    }
    return targetNode;
  }

  protected void mapChildren(IDOMNode sourceNode, IDOMNode targetNode)
  {
    map(sourceNode, targetNode);
    for (IDOMNode sourceChild = sourceNode.getFirstChild(), targetChild = targetNode.getFirstChild(); 
         sourceChild != null;
         sourceChild = sourceChild.getNextNode(), targetChild = targetChild.getNextNode())
    {
      mapChildren(sourceChild, targetChild);
    }
  }

  protected void map(IDOMNode sourceNode, IDOMNode targetNode)
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
      // Three arguments are expected: the .xml jControlModel URI, the source java URI, and the target java URI.
      //
      String contents = execute(new NullProgressMonitor(), (String[])object);

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
   * @since 2.1.0
   */
  public String execute(IProgressMonitor progressMonitor, String[] arguments)
  {
    String mergeXML = arguments[0];
    String sourceURI = arguments[1];
    String targetURI = arguments[2];
    
    // Create the options model.
    //
    jControlModel = new JControlModel(mergeXML);

    // Create the source and target JDOMs.
    //
    sourceCompilationUnit = createCompilationUnitForURI(sourceURI);
    targetCompilationUnit = createCompilationUnitForURI(targetURI);

    // Create a pattern dictionary for each.
    //
    sourcePatternDictionary = new JPatternDictionary(sourceCompilationUnit, jControlModel);
    targetPatternDictionary = new JPatternDictionary(targetCompilationUnit, jControlModel);
    
    merge();
    
    return targetCompilationUnit.getContents();
  }
  
/////////////////////////////////  HEADLESS INVOCATION  /////////////////////////////////////

  /**
   * @deprecated
   */
  @Deprecated
  public static class PlatformRunnable extends PropertyMerger implements IPlatformRunnable 
  {
    // Empty
  }
}
