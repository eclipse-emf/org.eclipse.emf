/**
 * <copyright>
 *
 * Copyright (c) 2006-2008 IBM Corporation and others.
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
 * $Id: JMerger.java,v 1.24 2008/04/21 20:13:01 emerks Exp $
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.FacadeVisitor;
import org.eclipse.emf.codegen.merge.java.facade.JAbstractType;
import org.eclipse.emf.codegen.merge.java.facade.JAnnotation;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.emf.codegen.merge.java.facade.JMember;
import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JPackage;
import org.eclipse.emf.codegen.merge.java.facade.NodeConverter;
import org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;


/**
 * This implements the method {@link #run}, 
 * which is called just like main during headless workbench invocation.
 */
public class JMerger
{
  public static final boolean DEBUG = false;
  
  public static final String DEFAULT_FACADE_HELPER_CLASS = ASTFacadeHelper.class.getName();  
  protected static final Object[] NO_ARGUMENTS = new Object[0];

  protected static Pattern interfaceBracePattern = null;
  
  public class PullTargetVisitor extends FacadeVisitor
  {
    protected void doPull(JNode source, JNode target)
    {
      map(source, target);
      if (source != null)
      {
        applyPullRules(source, target);
      }
    }
    
    @Override
    protected void visit(JNode target)
    {
      // retrieve source node corresponding to target
      //
      if (target instanceof JAbstractType)
      {
        JAbstractType targetAbstractType = (JAbstractType)target;
        String comment = targetAbstractType.getComment();
        
        isBlocked =
          targetAbstractType.getParent() instanceof JCompilationUnit &&
          getControlModel().getBlockPattern() != null && 
          comment != null &&
          getControlModel().getBlockPattern().matcher(comment).find();    
        
        String nodeIdentifier = targetPatternDictionary.getNodeIdentifier(targetAbstractType);
        JAbstractType sourceAbstractType = sourcePatternDictionary.getAbstractTypeMap().get(nodeIdentifier);
        
        // convert the target node to a compatible node
        //
        if (sourceAbstractType != null && !areCompatible(sourceAbstractType, targetAbstractType))
        {
          JNode newTarget = convertTarget(targetAbstractType, sourceAbstractType.getClass());
          if (newTarget != null)
          {
            // use new node from now on
            target = newTarget;
            
            // redo the markup since nodes changed now
            targetPatternDictionary.start(target);
          }
          else if (!isBlocked)
          {
            map(sourceAbstractType, target);
          }
        }
      }
      
      if (!isBlocked)
      {
        // super is called on converted node
        super.visit(target);
      }
    }
    
    @Override
    protected boolean basicVisit(JNode node)
    {
      String nodeIdentifier = targetPatternDictionary.getNodeIdentifier(node);
      JNode sourceNode = sourcePatternDictionary.getNodeMap(node).get(nodeIdentifier);
      if (noAbstractTypeConversion && sourceNode == null)
      {
        sourceNode = sourcePatternDictionary.getNode(nodeIdentifier);
      }      
      
      doPull(sourceNode, node);
      return true;
    }
    
    @Override
    protected boolean visit(JCompilationUnit compilationUnit)
    {
      return true;
    }
    
    @Override
    protected boolean visit(JMethod method)
    {
      String nodeIdentifier = targetPatternDictionary.getNodeIdentifier(method);
      JNode sourceNode = sourcePatternDictionary.getMethodMap().get(nodeIdentifier);

      if (sourceNode == null && 
            getControlModel().getRedirect() != null && 
            method.getName() != null &&
            method.getName().endsWith(getControlModel().getRedirect()))
      {
        String qualifiedTargetMethodName = method.getQualifiedName();
        int index = qualifiedTargetMethodName.indexOf("("); //)
        qualifiedTargetMethodName =
          qualifiedTargetMethodName.substring(0, index - getControlModel().getRedirect().length()) + 
            qualifiedTargetMethodName.substring(index);
        sourceNode = sourcePatternDictionary.getMethodMap().get(qualifiedTargetMethodName);
      }
      
      if (noAbstractTypeConversion && sourceNode == null)
      {
        sourceNode = sourcePatternDictionary.getNode(nodeIdentifier);
      }            
      doPull(sourceNode, method);
      return true;
    }
    
    @Override
    protected boolean visit(JPackage jPackage)
    {
      JPackage sourcePackage = sourcePatternDictionary.getJPackage();
      doPull(sourcePackage, jPackage);
      return false;
    }
  } 
  
  public class PushSourceVisitor extends FacadeVisitor
  {
    /**
     * Returns whether the children should be visited (ie, when the source 
     * is not in the target).
     * @param sourceNode
     */
    protected boolean doPush(JNode sourceNode)
    {
      applySortRules(sourceNode);
      if (!sourceToTargetMap.containsKey(sourceNode))
      {
        if (isPushMarkedUp(sourceNode))
        {         
          insertClone(sourceNode);
        }
        return false;
      }
      return true;
    }
    
    @Override
    protected boolean basicVisit(JNode node)
    {
      return doPush(node);
    }
    
    @Override
    protected boolean visit(JCompilationUnit compilationUnit)
    {
      return true;
    }
    
    @Override
    protected boolean visit(JAbstractType abstractType)
    {
      return 
        super.visit(abstractType) &&
        areCompatible(abstractType, sourceToTargetMap.get(abstractType));
    }
    
    @Override
    protected boolean visit(JImport jImport)
    {
      return 
       !targetPatternDictionary.isNoImport(jImport) 
       && super.visit(jImport);
    }
  }  
  
  protected JControlModel controlModel;
  
  protected JCompilationUnit sourceCompilationUnit;
  protected JCompilationUnit targetCompilationUnit;
  protected JPatternDictionary sourcePatternDictionary;
  protected JPatternDictionary targetPatternDictionary;

  protected Map<JNode, JNode> sourceToTargetMap = new LinkedHashMap<JNode, JNode>();
  protected Map<JNode, JNode> targetToSourceMap = new LinkedHashMap<JNode, JNode>();
  protected Map<JNode, List<JNode>> orderedSourceChildrenMap = new HashMap<JNode, List<JNode>>();

  protected boolean fixInterfaceBrace;
  protected boolean isBlocked = false;
  protected boolean targetCompilationUnitExists;
  protected boolean targetCompilationChanged = false;
  
  protected boolean noAbstractTypeConversion = true;
  
  /**
   * This creates an empty instances, an when used as a runnable.
   */
  public JMerger()
  {
    super();
  }

  public JMerger(JControlModel controlModel)
  {
    this();
    this.controlModel = controlModel;
    setFixInterfaceBrace(getControlModel().getFacadeHelper().fixInterfaceBrace());
  }
  
  /**
   * Resets this JMerger.  After calling this method, it is necessary to 
   * set the source and target compilation unit to reuse this instance.
   */
  public void reset()
  {
    if (sourcePatternDictionary != null)
    {
      sourcePatternDictionary.reset();
      sourcePatternDictionary = null;
    }
    if (targetPatternDictionary != null)
    {
      targetPatternDictionary.reset();
      targetPatternDictionary = null;
    }
    sourceCompilationUnit = null;
    targetCompilationUnit = null;
    
    sourceToTargetMap.clear();
    targetToSourceMap.clear();
    orderedSourceChildrenMap.clear();
    
    isBlocked = false;
    targetCompilationChanged = false;
  }

  public void merge()
  {
    targetCompilationChanged = false;
    targetCompilationUnitExists = targetCompilationUnit != null;
    
    pullTargetCompilationUnit();
    if (!isBlocked && targetCompilationUnitExists)
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

  public JCompilationUnit createCompilationUnitForInputStream(InputStream inputStream)
  {
    return createCompilationUnitForInputStream(inputStream, null);
  }

  public JCompilationUnit createCompilationUnitForInputStream(InputStream inputStream, String encoding)
  {
    try
    {
      BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
      byte [] input = new byte [bufferedInputStream.available()];
      bufferedInputStream.read(input);
      bufferedInputStream.close();
      return getControlModel().getFacadeHelper().createCompilationUnit("NAME", encoding == null ? new String(input) : new String(input, encoding));
    }
    catch (IOException exception)
    {
      // exception.printStackTrace();
    }
    return null;
  }

  /**
   * Creates a JCompilationUnit from a URI.
   */
  public JCompilationUnit createCompilationUnitForURI(String uri)
  {
    return createCompilationUnitForURI(uri, null);
  }

  /**
   * Creates a JCompilationUnit from a URI.
   */
  public JCompilationUnit createCompilationUnitForURI(String uri, String encoding)
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
      
      return getControlModel().getFacadeHelper().createCompilationUnit(
        url.toString(), 
        encoding == null ? new String(input) : new String(input, encoding));
    }
    catch (IOException exception)
    {
      // exception.printStackTrace();
    }
  
    return null;
  }

  public JCompilationUnit createCompilationUnitForContents(String contents)
  {
    return getControlModel().getFacadeHelper().createCompilationUnit("NAME", contents);
  }

  public JControlModel getControlModel()
  {
    return controlModel;
  }

  public JCompilationUnit getSourceCompilationUnit()
  {
    return sourceCompilationUnit;
  }

  public void setSourceCompilationUnit(JCompilationUnit sourceCompilationUnit)
  {
    this.sourceCompilationUnit =  sourceCompilationUnit;
    sourcePatternDictionary = new JPatternDictionary(sourceCompilationUnit, getControlModel());
  }

  public String getSourceCompilationUnitContents()
  {
    return sourceCompilationUnit.getContents();
  }

  public JCompilationUnit getTargetCompilationUnit()
  {
    return targetCompilationUnit;
  }

  public void setTargetCompilationUnit(JCompilationUnit targetCompilationUnit)
  {
    this.targetCompilationUnit = targetCompilationUnit;
    targetPatternDictionary = new JPatternDictionary(targetCompilationUnit, getControlModel());
  }

  public String getTargetCompilationUnitContents()
  {
    String result = null;
    if (getControlModel().getFacadeHelper() != null && (!targetCompilationUnitExists || !targetCompilationChanged))
    {
      result = getControlModel().getFacadeHelper().getOriginalContents(targetCompilationUnit);
    }
    if (result == null)
    {
      result = targetCompilationUnit.getContents();
    }
    
    if (fixInterfaceBrace)
    {
      if (interfaceBracePattern == null)
      {
        interfaceBracePattern = Pattern.compile
          ("(?:\\n\\r|\\r\\n|\\n|\\r)(\\s*)(?:public|private|protected|static|\\s)*(?:interface|class)\\s*[^\\{\\n\\r]*(\\{)(\\n\\r|\\r\\n|\\n|\\r)", 
          Pattern.MULTILINE);
      }
      Matcher matcher = interfaceBracePattern.matcher(result);
      int offset = 0;
      while (matcher.find())
      {
        if (getControlModel().standardBraceStyle)
        {
          if (result.charAt(matcher.start(2) - 1) != ' ')
          {
            result = 
              result.substring(0, offset + matcher.start(2)) + 
                " {" + result.substring(offset + matcher.end(2), result.length());
            offset += 1;
          }
        }
        else
        {
          result = 
            result.substring(0, offset + matcher.start(2)) + matcher.group(3) + 
              matcher.group(1) + "{" + result.substring(offset + matcher.end(2), result.length());
          offset += matcher.group(1).length() + matcher.group(3).length();
        }
      }
    }
    return result;
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

  public boolean isFixInterfaceBrace()
  {
    return fixInterfaceBrace;
  }

  public void setFixInterfaceBrace(boolean fixInterfaceBrace)
  {
    this.fixInterfaceBrace = fixInterfaceBrace;
  }

  protected void pullTargetCompilationUnit()
  {
    if (!targetCompilationUnitExists)
    {
      setTargetCompilationUnit((JCompilationUnit)insertClone(sourceCompilationUnit));
    }
    else
    {
      map(sourceCompilationUnit, targetCompilationUnit);
      applyPullRules(sourceCompilationUnit, targetCompilationUnit);

      createPullTargetVisitor().start(targetCompilationUnit);
    }
  }

  protected FacadeVisitor createPullTargetVisitor()
  {
    return new PullTargetVisitor();
  }

  protected void pushSourceCompilationUnit()
  {
    createPushSourceVisitor().start(sourceCompilationUnit);
  }

  protected FacadeVisitor createPushSourceVisitor()
  {
    return new PushSourceVisitor();
  }

  protected void sortTargetCompilationUnit()
  {
    FacadeHelper facadeHelper = getControlModel().getFacadeHelper();
    JNode parent = null;
    List<JNode> children = null;
    
    for (List<JNode> nodes : orderedSourceChildrenMap.values())
    {
      if (nodes.size() >= 2)
      {
        Iterator<JNode> i = nodes.iterator();
        JNode sourceNode = i.next();
        JNode previousTargetNode = sourceToTargetMap.get(sourceNode);
        do
        {
          sourceNode = i.next();
          JNode nextTargetNode = sourceToTargetMap.get(sourceNode);
  
          boolean reorder = true;

          JNode nextTargetNodeParent = nextTargetNode.getParent();
          if (facadeHelper.isSibilingTraversalExpensive() && parent != nextTargetNodeParent)
          {
            parent = nextTargetNodeParent;
            children = nextTargetNodeParent == null ?
              null
              : new ArrayList<JNode>(nextTargetNodeParent.getChildren());
          }

          int previousTargetNodeIndex = 0;
          int nextTargetNodeIndex = 0;
          if (children != null)
          {
            previousTargetNodeIndex = children.indexOf(previousTargetNode);
            nextTargetNodeIndex = children.indexOf(nextTargetNode);
            reorder = previousTargetNodeIndex > nextTargetNodeIndex;
          }
          else
          {
            for (JNode node = facadeHelper.getPrevious(nextTargetNode); 
                 node != null; 
                 node = facadeHelper.getPrevious(node))
            {
              if (node == previousTargetNode)
              {
                reorder = false;
                break;
              }
            }
          }
          
          if (reorder)
          {
            targetCompilationChanged = true;
            facadeHelper.remove(nextTargetNode);

            boolean appendNode = false;            
            if (children != null)
            {
              children.remove(nextTargetNode);
              appendNode = children.get(children.size()-1) == previousTargetNode; 
              if (appendNode)
              {
                children.add(nextTargetNode);
              }
              else
              {
                children.add(previousTargetNodeIndex, nextTargetNode);
              }
            }
            else
            {
              appendNode = facadeHelper.getNext(previousTargetNode) == null; 
            }
            
            if (appendNode)
            {
              facadeHelper.addChild(previousTargetNode.getParent(), nextTargetNode);
            }
            else
            {
              facadeHelper.insertSibling(previousTargetNode, nextTargetNode, false);
            }
          }
  
          previousTargetNode = nextTargetNode;
        }
        while (i.hasNext());
      }
    }
  }

  protected void sweepTargetCompilationUnit()
  {
    Set<JNode> sweptNodes = new HashSet<JNode>(targetToSourceMap.size());
    for (Map.Entry<JNode, JNode> entry : targetToSourceMap.entrySet())
    {
      if (entry.getValue() == null)
      {
        JNode node = entry.getKey();
        JNode parent = node.getParent();        
        if (parent != null && sweptNodes.contains(parent))
        {
          sweptNodes.add(node);
          continue;
        }
        
        if (applySweepRules(node))
        {
          targetCompilationChanged = true;
          sweptNodes.add(node);
        }
      }
    }
  }

  // Method created to increase the performance of regular expressions
  // by reducing the length of the string that is matched.
  //
  private int getStartIndex(String string)
  {
    int index = string.indexOf("<!--"); 
    if (index > 0)
    {
      while (Character.isWhitespace(string.charAt(--index)) && index > 0)
      {
        // Back up over the whitespace.
      }
      return index;
    }
    return 0;
  }

  protected void applyPullRules(JNode sourceNode, JNode targetNode)
  {
    try
    {
      for (JControlModel.PullRule pullRule : getControlModel().getPullRules())
      {
        if (pullRule.getSourceGetFeature().getFeatureMethod() != null &&
        	pullRule.getSourceGetFeature().getFeatureClass().isInstance(sourceNode) &&
        	pullRule.getTargetPutFeature().getFeatureMethod() != null &&
            pullRule.getTargetPutFeature().getFeatureClass().isInstance(targetNode) &&
            sourcePatternDictionary.isMarkedUp(pullRule.getSourceMarkup(), pullRule.getSourceParentMarkup(), sourceNode) && 
            targetPatternDictionary.isMarkedUp(pullRule.getTargetMarkup(), pullRule.getTargetParentMarkup(), targetNode))
        {
          // Skip if there's an equality filter and the values aren't equal.
          //
          if (pullRule.getEqualityFeature() != null)
          {
            Method equalityFeatureMethod = pullRule.getEqualityFeature().getFeatureMethod();
            Object value1 = equalityFeatureMethod.invoke(sourceNode, NO_ARGUMENTS);
            Object value2 = equalityFeatureMethod.invoke(targetNode, NO_ARGUMENTS);
            if (value1 == null ? value2 != null : !value1.equals(value2))
            {
              continue;
            }
          }
          Method sourceGetMethod = pullRule.getSourceGetFeature().getFeatureMethod();
          Object value = sourceGetMethod.invoke(sourceNode, NO_ARGUMENTS);
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
                String oldStringValue = (String)sourceGetMethod.invoke(targetNode, NO_ARGUMENTS);
                if (oldStringValue != null)
                {
                  Matcher sourceMatcher = sourceTransfer.matcher(stringValue);
                  Matcher targetMatcher = sourceTransfer.matcher(oldStringValue);
                  if (sourceMatcher.groupCount() >= 1 && targetMatcher.groupCount() >= 1)
                  {
                    StringBuilder result = new StringBuilder();
                    int index = 0;
                    int sourceStart = 0;
                    int targetStart = 0;
                    if (sourceTransfer.pattern().startsWith("(\\s*<!--"))
                    {
                      sourceStart = getStartIndex(stringValue);
                      targetStart = getStartIndex(oldStringValue);
                    }
                    for (boolean match = sourceMatcher.find(sourceStart) && targetMatcher.find(targetStart); 
                         match; 
                         match = sourceMatcher.find() && targetMatcher.find())
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
              Object oldValue = sourceGetMethod.invoke(targetNode, NO_ARGUMENTS);
              if (value == null ? oldValue == null : value.equals(oldValue))
              {
                continue;
              }
              else if (value instanceof Object[] && oldValue instanceof Object[] && Arrays.equals((Object[])value, (Object[])oldValue))
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
              targetCompilationChanged = true;
              if (targetPutMethod.getName().equals("setBody") && sourceNode instanceof JMethod)
              {
                JMethod sourceMethod = (JMethod)sourceNode;
                JMethod targetMethod = (JMethod)targetNode;

                String[] sourceParameterTypes = sourceMethod.getParameterTypes();
                String[] targetParameterTypes = targetMethod.getParameterTypes();
                if (Arrays.equals(sourceParameterTypes, targetParameterTypes))
                {
                  targetMethod.setParameterNames(sourceMethod.getParameterNames());
                }
                else
                {
                  // The methods were matched by a process that doesn't consider the
                  // parameters
                  targetMethod.setParameters(sourceMethod.getParameters());
                }
              }
            }
          }
          // source method return type is array (getExceptions), target is not array (i.e. addException)
          else
          {
            ArrayList<String> additionalStrings = new ArrayList<String>();
            String[] sourceStrings = (String[])value;
            if (sourceStrings != null)
            {
              additionalStrings.addAll(Arrays.asList(sourceStrings));
            }
  
            if (targetPutMethod.getName().equals("addSuperInterface"))
            {
              Pattern sourceTransfer = pullRule.getSourceTransfer();
              String comment = ((JMember)targetNode).getComment();
              if (sourceTransfer != null && comment != null)
              {
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
  
              String[] oldSuperInterfaces = (String[])sourceGetMethod.invoke(targetNode);
              String[] superInterfaces = additionalStrings.toArray(new String[additionalStrings.size()]);
              if (oldSuperInterfaces == null ?
                   superInterfaces.length != 0 :
                   !Arrays.equals(oldSuperInterfaces, superInterfaces))
              {
                Method putMethod = targetNode.getClass().getMethod("setSuperInterfaces", String [].class);
                putMethod.invoke(targetNode, new Object []{ superInterfaces });
                targetCompilationChanged = true;
              }
            }
            // target method is NOT addSuperInterface
            else
            {
              String [] oldStringValues = (String [])sourceGetMethod.invoke(targetNode, NO_ARGUMENTS);
              List<String> old = oldStringValues == null ? Collections.<String>emptyList() : Arrays.<String>asList(oldStringValues);
              for (String string : additionalStrings)
              {
                if (!old.contains(string))
                {
                  targetPutMethod.invoke(targetNode, new Object [] { string });
                  targetCompilationChanged = true;
                }
              }
            }
          }
        }
      }
  
    }
    catch (InvocationTargetException exception)
    {
      if (DEBUG)
      {
        exception.printStackTrace();
      }
    }
    catch (IllegalAccessException exception)
    {
      if (DEBUG)
      {
        exception.printStackTrace();
      }
    }
    catch (SecurityException e)
    {
      if (DEBUG)
      {
        e.printStackTrace();
      }
    }
    catch (NoSuchMethodException e)
    {
      if (DEBUG)
      {
        e.printStackTrace();
      }
    }
  }

  protected void applySortRules(JNode sourceNode)
  {
    for (JControlModel.SortRule sortRule : getControlModel().getSortRules())
    {
      if (sourcePatternDictionary.isMarkedUp(sortRule.getMarkup(), sourceNode)  &&
          sortRule.getSelector().isInstance(sourceNode))
      {
        JNode parent = sourceNode.getParent();
        List<JNode> children = orderedSourceChildrenMap.get(parent);
        if (children == null)
        {
          children = new ArrayList<JNode>();
          orderedSourceChildrenMap.put(parent, children);
        }
        children.add(sourceNode);
        break;
      }
    }
  }

  protected boolean applySweepRules(JNode targetNode)
  {
    for (JControlModel.SweepRule sweepRule : getControlModel().getSweepRules())
    {
      boolean sweep = 
           (sweepRule.getSelector() == JImport.class 
              && targetNode instanceof JImport 
              && sweepRule.getMarkup().matcher(targetNode.getName()).find())
        || (sweepRule.getSelector().isInstance(targetNode)  
              && targetPatternDictionary.isMarkedUp(sweepRule.getMarkup(), sweepRule.getParentMarkup(), targetNode));
      
      if (sweep)
      {
        switch (sweepRule.getAction())
        {
          case REMOVE:
          {
            getControlModel().getFacadeHelper().remove(targetNode);
            return true;
          }
          case RENAME:
          {
            String newName = sweepRule.getNewName();
            int index = newName.indexOf("{0}");
            if (index >= 0)
            {
              String name = targetNode.getName();
              newName = newName.substring(0, index) + name + newName.substring(index + "{0}".length());
            }            
            targetNode.setName(newName);
            return true;
          }
          case COMMENT:
          {
            getControlModel().getFacadeHelper().commentOut(targetNode);
            return true;
          }
        }
        break;
      }
    }
    return false;
  }

  /**
   * Checks if two nodes are compatible and can be merged.
   * <p>
   * This method must always return <code>false</code> if target node
   * can have children that can not be added to source node. 
   * 
   * @param sourceNode
   * @param targetNode
   * @return <code>true</code> if nodes can be merged, <code>false</code> otherwise
   */
  protected boolean areCompatible(JNode sourceNode, JNode targetNode)
  {
    return targetNode != null && targetNode.getClass().isInstance(sourceNode);
  }

  /**
   * Converts the target abstract type to be compatible with the given source node class
   * 
   * @param targetAbstractType
   * @param sourceClass the class to which to convert the node.
   * @return <code>null</code> when conversion not possible, converted node otherwise
   */
  protected JNode convertTarget(JAbstractType targetAbstractType, Class<? extends JAbstractType> sourceClass)
  {
    NodeConverter converter = getControlModel().getFacadeHelper().getNodeConverter();
    if (converter != null)
    {
      noAbstractTypeConversion = false;
      return converter.convert(targetAbstractType, sourceClass);      
    }
    else
    {
      return null;
    }
  }

  protected JNode insertClone(JNode sourceNode)
  {
    FacadeHelper facadeHelper = getControlModel().getFacadeHelper();
    if (sourceNode == sourceCompilationUnit && !targetCompilationUnitExists)
    {
      String originalContents = facadeHelper.getOriginalContents(sourceCompilationUnit);
      if (originalContents != null)
      {
        return createCompilationUnitForContents(facadeHelper.applyFormatRules(originalContents));
      }
    }
    
    Object context = targetCompilationUnit != null ? facadeHelper.getContext(targetCompilationUnit) : null; 
    JNode targetNode = facadeHelper.cloneNode(context, sourceNode);
    if (targetNode != null)
    {
      mapChildren(sourceNode, targetNode);
    }
    
    JNode sourceParent = sourceNode.getParent();
    if (sourceParent != null)
    {
      JNode targetParent = sourceToTargetMap.get(sourceParent);
      if (targetParent != null)
      {
        targetCompilationChanged = true;
        JNode targetParentFirstChild = null;
        if (facadeHelper.isSibilingTraversalExpensive())
        { 
          List<JNode> sourceChildren = sourceParent.getChildren();
          List<JNode> targetChildren = targetParent.getChildren();
          
          for (int i = sourceChildren.indexOf(sourceNode); i >= 0; i--)
          {
            if (i > 0)
            {
              JNode previousNode = sourceChildren.get(i-1);
              JNode targetSibling = sourceToTargetMap.get(previousNode);
              if (targetSibling != null)
              {
                int targetSibilingIndex = targetChildren.indexOf(targetSibling);
                if (targetSibilingIndex == targetChildren.size()-1)
                {
                  facadeHelper.addChild(targetSibling.getParent(), targetNode);
                }
                else
                {
                  facadeHelper.insertSibling(targetChildren.get(targetSibilingIndex+1), targetNode, true);
                }
                return targetNode;
              }
            }            
          }
          targetParentFirstChild = targetChildren.isEmpty() ? null : targetChildren.get(0);
        }
        else
        {
          for (JNode previousNode = facadeHelper.getPrevious(sourceNode); previousNode != null; previousNode = facadeHelper.getPrevious(previousNode))
          {
            JNode targetSibling = sourceToTargetMap.get(previousNode);
            if (targetSibling != null)
            {
              JNode targetNextSibling = facadeHelper.getNext(targetSibling);
              if (targetNextSibling == null)
              {
                facadeHelper.addChild(targetSibling.getParent(), targetNode);
              }
              else
              {
                facadeHelper.insertSibling(targetNextSibling, targetNode, true);
              }
              return targetNode;
            }
          }
          targetParentFirstChild = facadeHelper.getFirstChild(targetParent);
        }
        
        if (targetParentFirstChild == null)
        {
          facadeHelper.addChild(targetParent, targetNode);
        }
        else
        {
          facadeHelper.insertSibling(targetParentFirstChild, targetNode, true);
        }
      }
    }
    return targetNode;
  }
  
  /**
   * Checks if the node is marked up for pushing.
   * <p>
   * Node is considered marked up by default if there are no push rules for this node class.
   * <p>
   * The first push rule that matches the node makes the node marked up, and no further checking is performed.
   * <p>
   * If the push rule does not match the node, but the push rule is defined for the same node class,
   * then the node will not be marked up, unless any of the following push rules will match the node.
   * 
   * @param node
   * @return <code>true</code> if node should be pushed, <code>false</code> otherwise
   */
  protected boolean isPushMarkedUp(JNode node)
  {
    JNode sourceParent = node.getParent();
    JNode targetParent = sourceToTargetMap.get(sourceParent);
    assert targetParent != null; // if the parent is not in target, there is no point on checking the child
    
    // Don't push method annotations into redirected methods.
    //
    if (node instanceof JAnnotation && 
          sourceParent instanceof JMethod &&
          targetParent instanceof JMethod &&
          getControlModel().getRedirect() != null &&
          ((JMethod)targetParent).getName().endsWith(getControlModel().getRedirect()) &&
          !((JMethod)sourceParent).getName().endsWith(getControlModel().getRedirect()))
    {
      return false;
    }

    // by default nodes are marked up
    boolean markedUp = true;
    for (JControlModel.PushRule rule : getControlModel().getPushRules())
    {
      // ignore rules for nodes of different class
      if (rule.getSelector().isInstance(node))
      {
        Pattern targetParentMarkup = rule.getTargetParentMarkup();
        Pattern sourceMarkup = rule.getMarkup();

        // apply target parent pattern first
        if (getSourcePatternDictionary().isMarkedUp(sourceMarkup, node)
          && (getTargetPatternDictionary().isMarkedUp(targetParentMarkup, targetParent)))
        {
          return true;
        }
        else
        {
          // node is not marked up by now, but keep checking other rules 
          markedUp = false;
        }
      }
    }
    return markedUp;
  }
  
  /**
   * Maps the specified source and target nodes.
   * @param sourceNode
   * @param targetNode
   */
  protected void map(JNode sourceNode, JNode targetNode)
  {
    if (sourceNode != null)
    {
      sourceToTargetMap.put(sourceNode, targetNode);
    }
    targetToSourceMap.put(targetNode, sourceNode);
  }  
  
  protected void mapChildren(JNode sourceNode, JNode targetNode)
  {
    map(sourceNode, targetNode);
    
    FacadeHelper facadeHelper = getControlModel().getFacadeHelper();
    if (facadeHelper.isSibilingTraversalExpensive())
    {
      if (sourceNode != null)
      {
        List<JNode> sourceChildren = sourceNode.getChildren();
        if (targetNode == null)
        {
          for (int i = 0, size = sourceChildren.size(); i < size; i++)
          {
            mapChildren(sourceChildren.get(i), null);
          }           
        }
        else
        {
          List<JNode> targetChildren = targetNode.getChildren();
          int targetChildrenSize = targetChildren.size();
          for (int i = 0, size = sourceChildren.size(); i < size; i++)
          {
            mapChildren(sourceChildren.get(i), targetChildrenSize > i ? targetChildren.get(i) : null);
          }   
        }
      }      
    }
    else
    {
      for (JNode sourceChild = facadeHelper.getFirstChild(sourceNode), targetChild = facadeHelper.getFirstChild(targetNode); 
           sourceChild != null;
           sourceChild = facadeHelper.getNext(sourceChild), targetChild = facadeHelper.getNext(targetChild))
      {
        mapChildren(sourceChild, targetChild);
      }
    }
  }
  
  
  // Command line execution methods
    
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
   * Utility for headless operations.
   * @return the merged content.
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
  
    // Create the source and target JCompilationUnit.
    //
    sourceCompilationUnit = createCompilationUnitForURI(sourceURI);
    targetCompilationUnit = createCompilationUnitForURI(targetURI);
  
    // Create a pattern dictionary for each.
    //
    sourcePatternDictionary = new JPatternDictionary(sourceCompilationUnit, getControlModel());
    targetPatternDictionary = new JPatternDictionary(targetCompilationUnit, getControlModel());
    
    merge();
    
    String contents = getTargetCompilationUnitContents();
    if (controlModel.getFacadeHelper() != null)
    {
      controlModel.getFacadeHelper().reset();
    }
    return contents;
  }
}
