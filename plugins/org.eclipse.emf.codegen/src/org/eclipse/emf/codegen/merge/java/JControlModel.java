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
 * $Id: JControlModel.java,v 1.4 2006/02/14 12:58:01 emerks Exp $
 */
package org.eclipse.emf.codegen.merge.java;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.common.EMFPlugin;

class PrefixHandler
{
  protected String classPrefix;
  
  protected PrefixHandler()
  {
  }

  protected PrefixHandler(String classPrefix)
  {
    this.classPrefix = classPrefix;
  }

  protected void setClassPrefix(String classPrefix)
  {
    this.classPrefix = classPrefix;
  }

  public String getClassPrefix()
  {
    return classPrefix;
  }

  public String computeClassName(String className)
  {
    return getClassPrefix() == null ?
      className : getClassPrefix() + className;
  }
}

/**
 *  A control model that  provides dictionaries and rules to drive a merge process.
 */
public class JControlModel extends PrefixHandler
{  
  public static class Feature extends PrefixHandler
  {
    protected Class featureClass;
    protected Method featureMethod;

    public Feature(String classPrefix)
    {
      super(classPrefix);
    }

    public Feature(String classPrefix, String path, Class[] parameterTypes)
    {
      super(classPrefix);
      initialize(path, parameterTypes);
    }
    
    public Class getFeatureClass()
    {
      return featureClass;
    }

    public Method getFeatureMethod()
    {
      return featureMethod;
    }
    
    public void initialize(String path, Class[] parameterTypes)
    {
      int index = path.indexOf('/');
      String className = computeClassName(path.substring(0, index));
      String methodName = path.substring(index + 1);
      try
      {
        featureClass = Class.forName(className);
        featureMethod = featureClass.getMethod(methodName, parameterTypes);
      }
      catch (NoSuchMethodException exception)
      {
      }
      catch (ClassNotFoundException exception)
      {
      }
    }
  }

  public static class DictionaryPattern extends PrefixHandler
  {
    protected static Class[] noParameterTypes = new Class[0];
    protected static Class[] stringParameterType = new Class[] { String.class };
    protected String name;
    protected Feature selectorFeature;
    protected Pattern pattern;

    public DictionaryPattern(String classPrefix)
    {
      super(classPrefix);
    }

    public DictionaryPattern(String classPrefix, Element element)
    {
      this(classPrefix);
      initialize(element);
    }

    public void initialize(Element element)
    {
      name = element.getAttribute("name");
      selectorFeature = createFeature(getClassPrefix(), element.getAttribute("select"), noParameterTypes);
      pattern = Pattern.compile(element.getAttribute("match"), Pattern.MULTILINE | Pattern.DOTALL);
    }
    
    protected Feature createFeature(String classPrefix, String path, Class[] parameterTypes)
    {
      return new Feature(getClassPrefix(), path, parameterTypes);
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public Feature getSelectorFeature()
    {
      return selectorFeature;
    }

    public void setSelectorFeature(Feature selectorFeature)
    {
      this.selectorFeature = selectorFeature;
    }

    public Pattern getPattern()
    {
      return pattern;
    }

    public void setPattern(Pattern pattern)
    {
      this.pattern = pattern;
    }
  }

  public static class PullRule extends PrefixHandler
  {
    protected static Class[] noParameterTypes = new Class[0];
    protected String name;

    protected Pattern sourceMarkup;
    protected Feature sourceGetFeature;
    protected Pattern sourceTransfer;

    protected Pattern targetMarkup;
    protected Feature targetPutFeature;
    protected Pattern targetTransfer;
    
    protected Feature equalityFeature;

    public PullRule(String classPrefix)
    {
      super(classPrefix);
    }

    public PullRule(String classPrefix, Element element)
    {
      this(classPrefix);
      initialize(element);
    }

    public void initialize(Element element)
    {
      String classPrefix = getClassPrefix();
      sourceGetFeature = createFeature(classPrefix, element.getAttribute("sourceGet"), noParameterTypes);
      if (sourceGetFeature != null)
      {
        Method featureMethod = sourceGetFeature.getFeatureMethod();
        if (featureMethod != null)
        {
          Class sourceReturnType = featureMethod.getReturnType();
          targetPutFeature = createFeature(classPrefix, element.getAttribute("targetPut"), new Class[] { sourceReturnType });
          if (targetPutFeature.getFeatureMethod() == null && sourceReturnType.isArray())
          {
            targetPutFeature = createFeature(classPrefix, element.getAttribute("targetPut"), new Class[] { sourceReturnType.getComponentType() });
          }
        }
      }
      if (element.hasAttribute("sourceMarkup"))
      {
        sourceMarkup= Pattern.compile(element.getAttribute("sourceMarkup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("targetMarkup"))
      {
        targetMarkup= Pattern.compile(element.getAttribute("targetMarkup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("sourceTransfer"))
      {
        sourceTransfer= Pattern.compile(element.getAttribute("sourceTransfer"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("targetTransfer"))
      {
        targetTransfer= Pattern.compile(element.getAttribute("targetTransfer"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("equals"))
      {
        equalityFeature = createFeature(classPrefix, element.getAttribute("equals"), noParameterTypes);
      }
    }
    
    protected Feature createFeature(String classPrefix, String path, Class[] parameterTypes)
    {
      return new Feature(getClassPrefix(), path, parameterTypes);
    }    

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public Feature getSourceGetFeature()
    {
      return sourceGetFeature;
    }

    public void setSourceGetFeature(Feature sourceGetFeature)
    {
      this.sourceGetFeature = sourceGetFeature;
    }

    public Feature getEqualityFeature()
    {
      return equalityFeature;
    }

    public void setEqualityFeature(Feature equalityFeature)
    {
      this.equalityFeature = equalityFeature;
    }

    public Feature getTargetPutFeature()
    {
      return targetPutFeature;
    }

    public void setTargetPutFeature(Feature targetPutFeature)
    {
      this.targetPutFeature = targetPutFeature;
    }

    public Pattern getSourceTransfer()
    {
      return sourceTransfer;
    }

    public void setSourceTransfer(Pattern sourceTransfer)
    {
      this.sourceTransfer = sourceTransfer;
    }

    public Pattern getTargetTransfer()
    {
      return targetTransfer;
    }

    public void setTargetTransfer(Pattern targetTransfer)
    {
      this.targetTransfer = targetTransfer;
    }

    public Pattern getSourceMarkup()
    {
      return sourceMarkup;
    }

    public void setSourceMarkup(Pattern sourceMarkup)
    {
      this.sourceMarkup = sourceMarkup;
    }

    public Pattern getTargetMarkup()
    {
      return targetMarkup;
    }

    public void setTargetMarkup(Pattern targetMarkup)
    {
      this.targetMarkup = targetMarkup;
    }
  }

  /**
   * <p>A sweep rule removes elements from the target if they are <b>NOT</b> available
   * in the source.  It can work on available Dictionary Patterns or be used to
   * filter out import statements as follows:</p>
   * <pre>
   *   &lt;merge:sweep markup=&quot;^gen$&quot; select=&quot;Member&quot;/&gt;
   *   &lt;merge:sweep markup=&quot;^org.eclipse.emf.ecore.EMetaObject$&quot; select=&quot;Import&quot;/&gt;
   * </pre>
   * <p>The first line removes all &quot;members&quot; (attribute, method, ...) that matches
   * the expression defined by the &quot;^gen$&quot; Dictionary Pattern.  The second
   * removes the &quot;org.eclipse.emf.ecore.EMetaObject&quot; import.</p>
   */
  public static class SweepRule extends PrefixHandler
  {
    protected String name;
    protected Class selector;
    protected Pattern markup;

    public SweepRule(String classPrefix)
    {
      super(classPrefix);
    }

    public SweepRule(String classPrefix, Element element)
    {
      this(classPrefix);
      initialize(element);
    }

    public void initialize(Element element)
    {
      if (element.hasAttribute("select"))
      {
        selector = classForClassName(getClassPrefix(), element.getAttribute("select"));
      }
      if (element.hasAttribute("markup"))
      {
        markup= Pattern.compile(element.getAttribute("markup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public Class getSelector()
    {
      return selector;
    }

    public void setSelector(Class selector)
    {
      this.selector = selector;
    }

    public Pattern getMarkup()
    {
      return markup;
    }

    public void setMarkup(Pattern markup)
    {
      this.markup = markup;
    }
  }

  /**
   * <p>The sort rule is used to ensure that the order of the attributes as declared
   * on the source is respected  (in theory this rule could be used to sort any
   * member, but JMerger only applies it to attributes).  As usual you need to
   * specify a Dictionary Pattern to identify the attributes that should be
   * treated.  Here's an example:</p>
   * <pre>
   *   &lt;merge:sort markup=&quot;^ordered$&quot; select=&quot;Member&quot;/&gt;
   * </pre>
   */
  public static class SortRule extends PrefixHandler
  {
    protected String name;
    protected Class selector;
    protected Pattern markup;

    public SortRule(String classPrefix)
    {
      super(classPrefix);
    }

    public SortRule(String classPrefix, Element element)
    {
      this(classPrefix);
      initialize(element);
    }

    public void initialize(Element element)
    {
      if (element.hasAttribute("select"))
      {
        selector = classForClassName(getClassPrefix(), element.getAttribute("select"));
      }
      if (element.hasAttribute("markup"))
      {
        markup= Pattern.compile(element.getAttribute("markup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public Class getSelector()
    {
      return selector;
    }

    public void setSelector(Class selector)
    {
      this.selector = selector;
    }

    public Pattern getMarkup()
    {
      return markup;
    }

    public void setMarkup(Pattern markup)
    {
      this.markup = markup;
    }
  }
  
  public static Class classForClassName(String classPrefix, String className)
  {
    if (classPrefix != null)
    {
      className = classPrefix + className;
    }
    
    try
    {
      Class result = Class.forName(className);
      return result;
    }
    catch (ClassNotFoundException exception)
    {
      //  We expect this failure when running standalone
      //
      if (EMFPlugin.IS_ECLIPSE_RUNNING)
      {
        CodeGenPlugin.INSTANCE.log(exception);
      }
    }
    return null;
  }
  
  protected FacadeHelper facadeHelper;
  
  protected List dictionaryPatterns;
  protected List pullRules;
  protected List sweepRules;
  protected List sortRules;
  protected Pattern blockPattern;
  protected Pattern noImportPattern;
  protected String redirect;

  protected boolean indentIsSet = false;
  protected String indent;
  protected boolean standardBraceStyleIsSet = false;
  protected boolean standardBraceStyle;

  public JControlModel()
  {
  }
  
  protected void setFacadeHelper(FacadeHelper facadeHelper)
  {
    if (this.facadeHelper != null)
    {
      this.facadeHelper.setControlModel(null);
      dictionaryPatterns.clear();
      pullRules.clear();
      sweepRules.clear();
      sortRules.clear();
      blockPattern = null;
      noImportPattern = null;
      redirect = null;      
    }
    
    this.facadeHelper = facadeHelper;
    
    if (facadeHelper != null)
    {
      setClassPrefix(facadeHelper.getClassPrefix());
      facadeHelper.setControlModel(this);
    }
  }

  public FacadeHelper getFacadeHelper()
  {
    return facadeHelper;
  }
  
  public boolean convertToStandardBraceStyle()
  {
    return standardBraceStyle;
  }

  public void setConvertToStandardBraceStyle(boolean standardBraceStyle)
  {
    standardBraceStyleIsSet = true;
    this.standardBraceStyle = standardBraceStyle;
  }

  public String getLeadingTabReplacement()
  {
    return indent;
  }

  public void setLeadingTabReplacement(String indent)
  {
    indentIsSet = true;
    this.indent = indent;
  }

  public String getRedirect()
  {
    return redirect;
  }

  public Pattern getBlockPattern()
  {
    return blockPattern;
  }

  public Pattern getNoImportPattern()
  {
    return noImportPattern;
  }

  public List getDictionaryPatterns()
  {
    if (dictionaryPatterns == null)
    {
      dictionaryPatterns = new ArrayList();
    }
    return dictionaryPatterns;
  }

  public List getPullRules()
  {
    if (pullRules == null)
    {
      pullRules = new ArrayList();
    }
    return pullRules;
  }

  public List getSweepRules()
  {
    if (sweepRules == null)
    {
      sweepRules = new ArrayList();
    }
    return sweepRules;
  }

  public List getSortRules()
  {
    if (sortRules == null)
    {
      sortRules = new ArrayList();
    }
    return sortRules;
  }
  
  public boolean canMerge()
  {
    return getFacadeHelper() != null;
  }
  
  public void initialize(FacadeHelper facadeHelper, String uri)
  {
    setFacadeHelper(facadeHelper);
    initialize(uri);
  }

  protected void initialize(String uri)
  {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    documentBuilderFactory.setValidating(false);
    try
    {
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(new InputSource(uri));
      initialize(document.getDocumentElement());
    }
    catch (Exception exception)
    {
      CodeGenPlugin.INSTANCE.log(exception);
    }
  }

  protected void initialize(Element element)
  {
    if (element.getLocalName().equals("options"))
    {
      if (!standardBraceStyleIsSet && "standard".equals(element.getAttributeNS(null, "braceStyle")))
      {
        standardBraceStyle = true;
      }

      if (!indentIsSet && element.hasAttributeNS(null, "indent"))
      {
        indent = element.getAttributeNS(null, "indent");
      }

      if (element.hasAttributeNS(null, "redirect"))
      {
        redirect = element.getAttributeNS(null, "redirect");
      }

      if (element.hasAttributeNS(null, "block"))
      {
        blockPattern = Pattern.compile(element.getAttributeNS(null, "block"), Pattern.MULTILINE | Pattern.DOTALL); 
      }

      if (element.hasAttributeNS(null, "noImport"))
      {
        noImportPattern = Pattern.compile(element.getAttributeNS(null, "noImport"), Pattern.MULTILINE | Pattern.DOTALL); 
      }

      String classPrefix = getClassPrefix();
      for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
      {
        if (child.getNodeType() == Node.ELEMENT_NODE)
        {
          Element elementChild = (Element)child;
          if (elementChild.getLocalName().equals("dictionaryPattern"))
          {
            getDictionaryPatterns().add(createDictionaryPattern(classPrefix, elementChild));
          }
          else if (elementChild.getLocalName().equals("pull"))
          {
            getPullRules().add(createPullRule(classPrefix, elementChild));
          }
          else if (elementChild.getLocalName().equals("sweep"))
          {
            getSweepRules().add(createSweepRule(classPrefix, elementChild));
          }
          else if (elementChild.getLocalName().equals("sort"))
          {
            getSortRules().add(createSortRule(classPrefix, elementChild));
          }
        }
      }
    }
  }
  
  protected DictionaryPattern createDictionaryPattern(String classPrefix, Element elementChild)
  {
    return new DictionaryPattern(classPrefix, elementChild);
  }
  
  protected PullRule createPullRule(String classPrefix, Element elementChild)
  {
    return new PullRule(classPrefix, elementChild);
  }
  
  protected SweepRule createSweepRule(String classPrefix, Element elementChild)
  {
    return new SweepRule(classPrefix, elementChild);
  }
  
  protected SortRule createSortRule(String classPrefix, Element elementChild)
  {
    return new SortRule(classPrefix, elementChild);
  }  
}
