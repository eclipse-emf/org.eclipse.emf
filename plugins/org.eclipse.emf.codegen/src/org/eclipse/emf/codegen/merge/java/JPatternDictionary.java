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
 * $Id: JPatternDictionary.java,v 1.14 2008/12/13 15:50:44 emerks Exp $
 */

package org.eclipse.emf.codegen.merge.java;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.codegen.merge.java.facade.FacadeVisitor;
import org.eclipse.emf.codegen.merge.java.facade.JAbstractType;
import org.eclipse.emf.codegen.merge.java.facade.JAnnotation;
import org.eclipse.emf.codegen.merge.java.facade.JAnnotationTypeMember;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JEnumConstant;
import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.emf.codegen.merge.java.facade.JInitializer;
import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JPackage;

/**
 * A dictionary of signatures and {@link JNode}s.
 */
public class JPatternDictionary extends FacadeVisitor
{
  protected static final Pattern COMMENT = Pattern.compile("/\\*.*?\\*/", Pattern.MULTILINE | Pattern.DOTALL);
  protected static final Object [] NO_ARGUMENTS = new Object[0];
  
  protected static final boolean DEBUG = JMerger.DEBUG;
  
  protected JControlModel controlModel;
  protected JPackage jPackage;
  
  protected Map<String, JAnnotation> annotationMap;
  protected Map<String, JAnnotationTypeMember> annotationTypeMemberMap;
  protected Map<String, JEnumConstant> enumConstantMap;
  protected Map<String, JImport> importMap;
  protected Map<String, JAbstractType> abstractTypeMap;
  protected Map<String, JInitializer> initializerMap;
  protected Map<String, JField> fieldMap;
  protected Map<String, JMethod> methodMap;
  
  protected Map<String, Collection<JNode>> markupMap;
  protected Set<String> noImportSet;
  
  protected Map<JNode, String> nodeIdentifierMap;
  
  public JPatternDictionary(JCompilationUnit compilationUnit, JControlModel controlModel)
  {
    super();
    this.controlModel = controlModel;
    start(compilationUnit);
    if (DEBUG)
    {
      System.out.println("Maps of compilation unit: " + compilationUnit.getName());
      dumpMaps();
    }
  }
  
  /**
   * Resets this JPatternDictionary.  After calling this method, it is necessary to invoke 
   * {@link #start(JNode)} passing a compilation unit to reuse this instance. 
   */
  public void reset()
  {
    jPackage = null;
    
    if (annotationMap != null)
    {
      annotationMap.clear();
      annotationMap = null;
    }
    if (annotationTypeMemberMap != null)
    {
      annotationTypeMemberMap.clear();
      annotationMap = null;
    }
    if (enumConstantMap != null)
    {
      enumConstantMap.clear();
      enumConstantMap = null;
    }
    if (importMap != null)
    {
      importMap.clear();
      importMap = null;
    }
    if (abstractTypeMap != null)
    {
      abstractTypeMap.clear();
      abstractTypeMap = null;
    }
    if (initializerMap != null)
    {
      initializerMap.clear();
      initializerMap = null;
    }
    if (fieldMap != null)
    {
      fieldMap.clear();
      fieldMap = null;
    }
    if (methodMap != null)
    {
      methodMap.clear();
      methodMap = null;
    }
    
    if (markupMap != null)
    {
      markupMap.clear();
      markupMap = null;      
    }
    if (noImportSet != null)
    {
      noImportSet.clear();
      noImportSet = null;
    }
    
    if (nodeIdentifierMap != null)
    {
      nodeIdentifierMap.clear();
      nodeIdentifierMap = null;
    }
  }

  public JPackage getJPackage()
  {
    return jPackage;
  }
  
  public Map<String, ? extends JNode> getNodeMap(JNode node)
  {
    if (node instanceof JAnnotation) return getAnnotationMap();
    if (node instanceof JMethod) return getMethodMap();
    if (node instanceof JField) return getFieldMap();
    if (node instanceof JImport) return getImportMap();
    if (node instanceof JEnumConstant) return getEnumConstantMap();
    if (node instanceof JAbstractType) return getAbstractTypeMap();
    if (node instanceof JAnnotationTypeMember) return getAnnotationTypeMemberMap();
    if (node instanceof JInitializer) return getInitializerMap();
    return Collections.emptyMap();
  }
  
  public Map<String, JImport> getImportMap()
  {
    if (importMap == null)
    {
      importMap = new HashMap<String, JImport>();
    }
    return importMap;
  }

  public Map<String, JAbstractType> getAbstractTypeMap()
  {
    if (abstractTypeMap == null)
    {
      abstractTypeMap = new HashMap<String, JAbstractType>();
    }
    return abstractTypeMap;
  }

  public Map<String, JInitializer> getInitializerMap()
  {
    if (initializerMap == null)
    {
      initializerMap = new HashMap<String, JInitializer>();
    }
    return initializerMap;
  }

  public Map<String, JField> getFieldMap()
  {
    if (fieldMap == null)
    {
      fieldMap = new HashMap<String, JField>();
    }
    return fieldMap;
  }

  public Map<String, JMethod> getMethodMap()
  {
    if (methodMap == null)
    {
      methodMap = new HashMap<String, JMethod>();
    }
    return methodMap;
  }  
  
  public Map<String, JAnnotation> getAnnotationMap()
  {
    if (annotationMap == null)
    {
      annotationMap = new HashMap<String, JAnnotation>();
    }
    return annotationMap;
  }
  
  public Map<String, JAnnotationTypeMember> getAnnotationTypeMemberMap()
  {
    if (annotationTypeMemberMap == null)
    {
      annotationTypeMemberMap = new HashMap<String, JAnnotationTypeMember>();
    }
    return annotationTypeMemberMap;
  }
  
  public Map<String, JEnumConstant> getEnumConstantMap()
  {
    if (enumConstantMap == null)
    {
      enumConstantMap = new HashMap<String, JEnumConstant>();
    }
    return enumConstantMap;
  }  
  
  public Map<String, Collection<JNode>> getMarkupMap()
  {
    if (markupMap == null)
    {
      markupMap = new HashMap<String, Collection<JNode>>();
    }
    return markupMap;
  }
  
  /**
   * Determines if the node is marked up based on the node and parent markup.
   * <p>
   * If both patterns are <code>null</code>, the node is marked up.
   * <p>
   * If both patterns are not <code>null</code>, the node is marked up if the node itself and its 
   * parent is marked up. If the node does not have a parent, the node is marked up if the node itself is marked up. 
   * <p>
   * If only <code>markupPattern</code> or <code>parentMarkupPattern</code> is set, the node is marked up 
   * if the node or its parent is marked up respectively.
   * 
   * @param markupPattern
   * @param parentMarkupPattern
   * @param node
   * 
   * @see #isMarkedUp(Pattern, JNode) 
   */
  public boolean isMarkedUp(Pattern markupPattern, Pattern parentMarkupPattern, JNode node)
  {
    return 
      (markupPattern == null || isMarkedUp(markupPattern, node)) &&
      (parentMarkupPattern == null || node.getParent() == null || isMarkedUp(parentMarkupPattern, node.getParent()));
  }
  
  public boolean isMarkedUp(Pattern markupPattern, JNode node)
  {
    if (markupPattern == null)
    {
      return true;
    }
    else
    {
      for (Map.Entry<String, Collection<JNode>> markupEntry : getMarkupMap().entrySet())
      {
        String key = markupEntry.getKey();
        if (key != null && markupPattern.matcher(key).find())
        {
          if (markupEntry.getValue().contains(node))
          {
            return true;
          }
        }
      }
      return false;
    }
  }
  
  protected Set<String> getNoImporterSet()
  {
    if (noImportSet == null)
    {
      noImportSet = new HashSet<String>();
    }
    return noImportSet;
  }

  public boolean isNoImport(JImport jImport)
  {
    return noImportSet != null && getNoImporterSet().contains(getNodeIdentifier(jImport));
  }
    
  @Override
  protected boolean visit(JCompilationUnit compilationUnit)
  {
    if (controlModel.getNoImportPattern() != null)
    {
      // Optimize the performance of applying the import regular 
      // expressions locating the last import line
      //      
      String contents = compilationUnit.getContents();
      int lastIndex = contents.length() - 1;
      int endIndex = contents.lastIndexOf("import");
      while (endIndex >= 0)
      {
        int index = endIndex + "import".length() - 1;
        if (index == lastIndex || !Character.isWhitespace(contents.charAt(index+1)))
        {
          endIndex = contents.lastIndexOf("import", endIndex-1);
        }
        else
        {
          index = contents.indexOf(';', index);
          if (index >= 0)
          {
            for (int length = contents.length(); index < length; ++index)
            {
              char character = contents.charAt(index);
              if (character == '\n' || character == '\r')
              {
                break;
              }
            }
            endIndex = index+1;
            break;
          }
          else
          {
            endIndex -= "import".length();
          }
        }
      }
      if (endIndex != -1)
      {
        contents = contents.substring(0, endIndex);
      }
      
      Matcher matcher = controlModel.getNoImportPattern().matcher(contents);
      while (matcher.find())
      {
        getNoImporterSet().add(matcher.group(1));
      }
    }
    return super.visit(compilationUnit);
  }
  
  @Override
  protected boolean visit(JPackage jPackage)
  {
    this.jPackage = jPackage;
    return super.visit(jPackage);
  }
  
  @Override
  protected boolean visit(JAbstractType abstractType)
  {
    getAbstractTypeMap().put(getNodeIdentifier(abstractType), abstractType);
    return super.visit(abstractType);
  }  
  
  @Override
  protected boolean visit(JImport jImport)
  {
    getImportMap().put(getNodeIdentifier(jImport), jImport);
    return super.visit(jImport);
  }

  @Override
  protected boolean visit(JInitializer initializer)
  {
    getInitializerMap().put(getNodeIdentifier(initializer), initializer);
    return super.visit(initializer);
  }
  
  @Override
  protected boolean visit(JField field)
  {
    getFieldMap().put(getNodeIdentifier(field), field);
    return super.visit(field);
  }
  
  @Override
  protected boolean visit(JMethod method)
  {
    getMethodMap().put(getNodeIdentifier(method), method);
    return super.visit(method);
  }
    
  @Override
  protected boolean visit(JAnnotation annotation)
  {
    getAnnotationMap().put(getNodeIdentifier(annotation), annotation);
    return super.visit(annotation);
  }
  
  @Override
  protected boolean visit(JAnnotationTypeMember annotationTypeMember)
  {
    getAnnotationTypeMemberMap().put(getNodeIdentifier(annotationTypeMember), annotationTypeMember);
    return super.visit(annotationTypeMember);
  }
  
  @Override
  protected boolean visit(JEnumConstant enumConstant)
  {
    getEnumConstantMap().put(getNodeIdentifier(enumConstant), enumConstant);
    return super.visit(enumConstant);
  }
  
  @Override
  protected void beforeVisit(JNode node)
  {
    Method previousMethod = null; 
    String previousSelection = null;    
    for (JControlModel.DictionaryPattern dictionaryPattern : controlModel.getDictionaryPatterns())
    {
      JControlModel.Feature feature = dictionaryPattern.getSelectorFeature();
      Method method = feature.getFeatureMethod();
      String selection = null;
      if (feature.getFeatureClass() != null && feature.getFeatureClass().isInstance(node))
      {
        try
        {
          if (method.equals(previousMethod))
          {
            selection = previousSelection;
          }
          else
          {
            selection = (String)method.invoke(node, NO_ARGUMENTS);
            if (controlModel.getFacadeHelper() == null || controlModel.getFacadeHelper().canYieldWrongJavadoc())
            {
              selection = checkSelection(selection, dictionaryPattern, node);
            }
            previousMethod = method;
            previousSelection = selection;
          }          
          if (selection != null)
          {
            markupNode(selection, dictionaryPattern, node);
          }
        }
        catch (IllegalAccessException exception)
        {
          if (DEBUG)
          {
            exception.printStackTrace();
          }
        }
        catch (InvocationTargetException exception)
        {
          if (DEBUG)
          {
            exception.printStackTrace();
          }
        }
      }
    }
  }

  /**
   * Checks the selection to fix the problem with facade implementations that assign
   * wrong javadoc to the node.
   * 
   * @param selection
   * @param dictionaryPattern
   * @param node
   * @return <code>null</code> if the node should be skipped 
   */
  protected String checkSelection(String selection, JControlModel.DictionaryPattern dictionaryPattern, JNode node)
  {
    if (selection != null 
        && !(node instanceof JAbstractType)
        && dictionaryPattern.getSelectorFeature().getFeatureMethod().getName().equals("getComment"))
    {
      String contents = node.getContents();
      if (contents != null)
      {
        for (int start = 0, end = contents.length(), count = 0; start < end; )
        {
          contents = contents.substring(start, end);
          Matcher matcher = COMMENT.matcher(contents);
          if (matcher.find())
          {
            if (++count > 1)
            {
              // Ignore the further-most javadoc
              //
              selection = contents;
            }
            start += matcher.end(0) + 1;
            end = contents.length();
          }
          else
          {
            break;
          }
        }
      }
    }
    return selection;
  }
  
  /**
   * Matches pattern in dictionary pattern against selection, and marks up node
   * with all matching groups.
   * <p>
   * If pattern matches selection, but no groups are defined, node is marked up with 
   * dictionary pattern name. 
   * 
   * @param selection
   * @param dictionaryPattern
   * @param node
   */
  protected void markupNode(String selection, JControlModel.DictionaryPattern dictionaryPattern, JNode node)
  {
    Pattern pattern = dictionaryPattern.getPattern();
    if (pattern.pattern().startsWith("@"))
    {
      int index = selection.indexOf('@');
      if (index != -1)
      {
        selection = selection.substring(index, selection.length());
      }
    }
    
    Matcher matcher = pattern.matcher(selection);
    if (matcher.find())
    {
      if (matcher.groupCount() > 0)
      {
        for (int i = 1; i <= matcher.groupCount(); ++i)
        {
          String markup = matcher.group(i);
          markupNode(markup, node);
        }
      }
      else
      {
        // if there are no groups defined or matched, but the whole pattern matches,
        // then markup nodes with pattern name
        //
        String markup = dictionaryPattern.getName();
        if (markup != null && !"".equals(markup))
        {
          markupNode(markup, node);
        }
      }
    }
  }

  /**
   * Marks up node with the given markup string.
   * 
   * @param markup
   * @param node
   */
  protected void markupNode(String markup, JNode node)
  {
    Collection<JNode> collection = getMarkupMap().get(markup);
    if (collection == null)
    {
      collection = new HashSet<JNode>();
      getMarkupMap().put(markup, collection);
    }
    collection.add(node);
  }

//  /**
//  * Markup node same as the other node if the markup pattern in dictionary pattern 
//  * matches markup of other node.
//  * <br>
//  * This method allows copying markup from parent using Node/getParent pull rule.  
//  *   
//  * @param node
//  * @param dictionaryPattern
//  * @param otherNode
//  */
//  private void addMarkupFromOtherNode(JNode node, JControlModel.DictionaryPattern dictionaryPattern, JNode otherNode)
//  {
//   Pattern markupPattern = dictionaryPattern.getPattern();
//   for (Map.Entry<String, Collection<JNode>> markupEntry : getMarkupMap().entrySet())
//   {
//     String key = markupEntry.getKey();
//     if (key != null && markupPattern.matcher(key).find())
//     {
//       Collection<JNode> markedUpNodes = markupEntry.getValue();
//       if (markedUpNodes.contains(otherNode))
//       {
//         markedUpNodes.add(node);
//       }
//     }
//   }
//  }  
    
  protected void dumpMaps()
  {
    try
    {
      Field[] fields = this.getClass().getDeclaredFields();
      for (int i = 0; i < fields.length; i++)
      {
        if (fields[i].getName().endsWith("Map") && Map.class.isAssignableFrom(fields[i].getType()))
        {
          Map< ? , ? > map = (Map< ? , ? >)fields[i].get(this);
          String mapString = String.format("%s = %s\n", fields[i].getName(), dumpMap("\t", map));
          System.out.print(mapString);
        }
      }
    }
    catch (Exception e)
    {
      if (DEBUG)
      {
        e.printStackTrace();
      }
    }
  }

  protected static <K, V> String dumpMap(String lineIndent, Map<K, V> map)
  {
    int columnWidth = 10;
    if (map != null)
    {
      StringBuilder sb = new StringBuilder("\n");
      for (Map.Entry<K, V> entry : map.entrySet())
      {
        K key = entry.getKey();
        String keyString = (key == null ? null : key.toString());
        if (keyString != null && columnWidth < keyString.length())
        {
          columnWidth = keyString.length() + 25;
        }
        sb.append(String.format("%s%-" + columnWidth + "s = ", lineIndent, keyString));
        if (entry.getValue() instanceof Collection<?>)
        {
          sb.append("\n");
          Collection<?> values = (Collection<?>)entry.getValue();
          for (Object element : values)
          {
            sb.append(String.format("%s%<s%s\n", lineIndent, element.toString()));
          }
        }
        else
        {
          sb.append(String.format("%s\n", entry.getValue()));
        }
      }
      return sb.toString();
    }
    else
    {
      return null;
    }
  }
  
  public String getNodeIdentifier(JNode node)
  {
    String identifier = nodeIdentifierMap == null ? null : nodeIdentifierMap.get(node);
    if (identifier == null)
    {
      StringBuilder sb = new StringBuilder();
      for (JControlModel.MatchRule matchRule : controlModel.getMatchRules())
      {
        if (isMarkedUp(matchRule.getMarkup(), node) && 
            matchRule.getGetFeature().getFeatureClass().isInstance(node))
        {
          try
          {
            Method getMethod = matchRule.getGetFeature().getFeatureMethod();
            Object value = getMethod.invoke(node, JMerger.NO_ARGUMENTS);
            if (value instanceof String)
            {
              String stringValue = (String)value;
              Pattern signature = matchRule.getSignature();
              if (signature != null)
              {
                Matcher matcher = signature.matcher(stringValue);
                stringValue = matcher.find() && matcher.groupCount() == 1 ?
                  stringValue = matcher.group(1)
                  : null;
              }
              if (stringValue != null && stringValue.length() > 0)
              {
                sb.append(stringValue);
                if (matchRule.isStopMatching())
                {
                  break;
                }
              }
            }
          }
          catch (Exception e)
          {
            // Ignore
          }
        }
      }
      
      identifier = sb.length() > 0 ? sb.toString() : getDefaultNodeIdentifier(node);
      if (nodeIdentifierMap == null)
      {
        nodeIdentifierMap = new HashMap<JNode, String>();
      }
      nodeIdentifierMap.put(node, identifier);
    }
    return identifier;
  }
  
  protected String getDefaultNodeIdentifier(JNode node)
  {
    return node.getQualifiedName();
  }
  
  public JNode getNode(String nodeIdentifier)
  {
    if (nodeIdentifier != null && nodeIdentifierMap != null)
    {
      for (Map.Entry<JNode, String> entry : nodeIdentifierMap.entrySet())
      {
        if (nodeIdentifier.equals(entry.getValue()))
        {
          return entry.getKey();
        }
      }
    }
    return null;
  }  
}
