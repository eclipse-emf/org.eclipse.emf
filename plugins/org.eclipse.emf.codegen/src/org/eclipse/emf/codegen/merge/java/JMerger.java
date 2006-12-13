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
 * $Id: JMerger.java,v 1.12 2006/12/13 20:19:11 marcelop Exp $
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

import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.FacadeVisitor;
import org.eclipse.emf.codegen.merge.java.facade.JAbstractType;
import org.eclipse.emf.codegen.merge.java.facade.JAnnotation;
import org.eclipse.emf.codegen.merge.java.facade.JAnnotationTypeMember;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JEnumConstant;
import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.emf.codegen.merge.java.facade.JInitializer;
import org.eclipse.emf.codegen.merge.java.facade.JMember;
import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JPackage;
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
  
  protected static final Pattern INTERFACE_BRACE_PATTERN = 
    Pattern.compile
      ("(?:\\n\\r|\\r\\n|\\n|\\r)(\\s*)(?:public|private|protected|static|\\s)*(?:interface|class)\\s*[^\\{\\n\\r]*(\\{)(\\n\\r|\\r\\n|\\n|\\r)", 
       Pattern.MULTILINE); // }}
  
  protected static final Object[] NO_ARGUMENTS = new Object[0];

  
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
    protected boolean visit(JAbstractType abstractType)
    {
      isBlocked =
        abstractType.getParent() instanceof JCompilationUnit &&
        getControlModel().getBlockPattern() != null && 
        abstractType.getComment() != null &&
        getControlModel().getBlockPattern().matcher(abstractType.getComment()).find();
      
      if (!isBlocked)
      {
        JAbstractType sourceType = sourcePatternDictionary.getAbstractTypeMap().get(abstractType.getQualifiedName());

        // check if nodes can be merged
        if (!areCompatible(sourceType, abstractType))
        {
          // convert the target node to a compatible node
          JAbstractType newTargetType = convertTarget(abstractType, sourceType);
          if (newTargetType == null)
          {
            // do not merge incompatible types, but still map them to avoid pushing
            map(sourceType, abstractType);
            return false;
          }
          else
          {
            abstractType = newTargetType;
          }
        }
        
        doPull(sourceType, abstractType);
        return true;
      }
      else
      {
        return false;
      }
    }
    
    @Override
    protected boolean visit(JCompilationUnit compilationUnit)
    {
      return true;
    }
    
    @Override
    protected boolean visit(JAnnotation annotation)
    {
      JAnnotation sourceAnnotation = sourcePatternDictionary.getAnnotationMap().get(annotation.getQualifiedName());      
      doPull(sourceAnnotation, annotation);
      return false;
    }
    
    @Override
    protected boolean visit(JAnnotationTypeMember annotationTypeMember)
    {
      JAnnotationTypeMember sourceannotationTypeMember = sourcePatternDictionary.getAnnotationTypeMemberMap().get(annotationTypeMember.getQualifiedName());
      doPull(sourceannotationTypeMember, annotationTypeMember);
      return true;
    }
    
    @Override
    protected boolean visit(JEnumConstant enumConstant)
    {
      JEnumConstant sourceEnumConstant = sourcePatternDictionary.getEnumConstantMap().get(enumConstant.getQualifiedName());
      doPull(sourceEnumConstant, enumConstant);
      return true;
    }
    
    @Override
    protected boolean visit(JField field)
    {
      JField sourceField = sourcePatternDictionary.getFieldMap().get(field.getQualifiedName());
      doPull(sourceField, field);
      return true;
    }
    
    @Override
    protected boolean visit(JImport jImport)
    {
      JImport sourceImport = sourcePatternDictionary.getImportMap().get(jImport.getQualifiedName());
      doPull(sourceImport, jImport);
      return false;
    }
    
    @Override
    protected boolean visit(JInitializer initializer)
    {
      JInitializer sourceInitializer = sourcePatternDictionary.getInitializerMap().get(initializer.getQualifiedName());
      doPull(sourceInitializer, initializer);
      return true;
    }
    
    @Override
    protected boolean visit(JMethod method)
    {
      String qualifiedTargetMethodName = method.getQualifiedName();
      JMethod sourceMethod = sourcePatternDictionary.getMethodMap().get(qualifiedTargetMethodName);

      if (sourceMethod == null && 
            getControlModel().getRedirect() != null && 
            method.getName() != null &&
            method.getName().endsWith(getControlModel().getRedirect()))
      {
        int index = qualifiedTargetMethodName.indexOf("("); //)
        qualifiedTargetMethodName =
          qualifiedTargetMethodName.substring(0, index - getControlModel().getRedirect().length()) + 
            qualifiedTargetMethodName.substring(index);
        sourceMethod = sourcePatternDictionary.getMethodMap().get(qualifiedTargetMethodName);
      }
      
      doPull(sourceMethod, method);
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
    @Override
    protected boolean basicVisit(JNode node)
    {
      return doPush(node);
    }
    
    /**
     * Returns whether the children should be visited (ie, when the source is not in the target).
     * @param sourceNode
     * @return
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

  protected Map<JNode, JNode> sourceToTargetMap = new HashMap<JNode, JNode>();
  protected Map<JNode, JNode> targetToSourceMap = new HashMap<JNode, JNode>();
  protected Map<JNode, List<JNode>> orderedSourceChildrenMap = new HashMap<JNode, List<JNode>>();

  protected boolean fixInterfaceBrace;
  protected boolean isBlocked;
  
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
    if (targetCompilationUnit == null)
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
            facadeHelper.remove(nextTargetNode);

            boolean addNewNode = false;            
            if (children != null)
            {
              children.remove(nextTargetNode);
              addNewNode = children.get(children.size()-1) == previousTargetNode; 
              if (addNewNode)
              {
                children.add(nextTargetNode);
              }
              else
              {
                children.add(nextTargetNodeIndex, nextTargetNode);
              }
            }
            else
            {
              addNewNode = facadeHelper.getNext(previousTargetNode) == null; 
            }
            
            if (addNewNode)
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
    for (Map.Entry<JNode, JNode> entry : targetToSourceMap.entrySet())
    {
      if (entry.getValue() == null)
      {
        applySweepRules(entry.getKey());
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
        if (sourcePatternDictionary.isMarkedUp(pullRule.getSourceMarkup(), pullRule.getSourceParentMarkup(), sourceNode) && 
              targetPatternDictionary.isMarkedUp(pullRule.getTargetMarkup(), pullRule.getTargetParentMarkup(), targetNode) && 
              pullRule.getSourceGetFeature().getFeatureClass().isInstance(sourceNode) &&
              pullRule.getTargetPutFeature().getFeatureClass().isInstance(targetNode))
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
                    StringBuffer result = new StringBuffer();
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
                String[] sourceParameterNames = sourceMethod.getParameterNames();
                targetMethod.setParameterNames(sourceParameterNames);
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

  protected void applySweepRules(JNode targetNode)
  {
    for (JControlModel.SweepRule sweepRule : getControlModel().getSweepRules())
    {
      boolean sweep = 
           (sweepRule.getSelector() == JImport.class 
              && targetNode instanceof JImport 
              && sweepRule.getMarkup().matcher(targetNode.getName()).find())
        || (targetPatternDictionary.isMarkedUp(sweepRule.getMarkup(), sweepRule.getParentMarkup(), targetNode)  
              && sweepRule.getSelector().isInstance(targetNode));
      
      if (sweep)
      {
        getControlModel().getFacadeHelper().remove(targetNode);
        break;        
      }
    }
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
   * Converts target node to be compatible with the source node
   * 
   * @param <T> type of the source node and returned result node
   * @param targetNode
   * @param sourceNode
   * @return <code>null</code> when conversion not possible, converted node otherwise
   */
  protected <T> T convertTarget(JNode targetNode, T sourceNode)
  {
    // TODO implement dispatching and create convert methods
    return null;
  }

  protected JNode insertClone(JNode sourceNode)
  {
    Object context = targetCompilationUnit != null ? getControlModel().getFacadeHelper().getContext(targetCompilationUnit) : null; 
    JNode targetNode = getControlModel().getFacadeHelper().cloneNode(context, sourceNode);
    if (targetNode != null)
    {
      mapChildren(sourceNode, targetNode);
    }
    else
    {
      // System.err.println("Warning: Cannot clone '" + sourceNode.getContents() + "'");
    }
        
    for (JNode previousNode = getControlModel().getFacadeHelper().getPrevious(sourceNode); previousNode != null; previousNode = getControlModel().getFacadeHelper().getPrevious(previousNode))
    {
      JNode targetSibling = sourceToTargetMap.get(previousNode);
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
      JNode targetParent = sourceToTargetMap.get(sourceParent);
      JNode targetSibling = getControlModel().getFacadeHelper().getFirstChild(targetParent);
      if (targetSibling == null)
      {
        getControlModel().getFacadeHelper().addChild(targetParent, targetNode);
      }
      else
      {
        getControlModel().getFacadeHelper().insertSibling(targetSibling, targetNode, true);
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
    JNode targetParent = sourceToTargetMap.get(node.getParent());
    assert targetParent != null; // if the parent is not in target, there is no point on checking the child
    
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
    for (JNode sourceChild = getControlModel().getFacadeHelper().getFirstChild(sourceNode), targetChild = getControlModel().getFacadeHelper().getFirstChild(targetNode); 
         sourceChild != null;
         sourceChild = getControlModel().getFacadeHelper().getNext(sourceChild), targetChild = getControlModel().getFacadeHelper().getNext(targetChild))
    {
      mapChildren(sourceChild, targetChild);
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
  
    // Create the source and target JCompilationUnit.
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
