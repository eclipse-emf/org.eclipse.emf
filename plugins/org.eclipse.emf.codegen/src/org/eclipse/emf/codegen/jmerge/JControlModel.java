/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JControlModel.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.jmerge;


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


/**
 *  A control model that  provides dictionaries and rules to drive a merge process.
 */
public class JControlModel
{
  public static class Feature 
  {
    protected Class featureClass;
    protected Method featureMethod;

    public Feature(String path, Class [] parameterTypes)
    {
      int index = path.indexOf('/');
      String className = "org.eclipse.jdt.core.jdom.IDOM" + path.substring(0, index);
      String methodName = path.substring(index + 1);
      try
      {
        featureClass = Class.forName(className);
        featureMethod = featureClass.getMethod(methodName, parameterTypes);
      }
      catch (NoSuchMethodException exception)
      {
        // CodeGenPlugin.INSTANCE.log(exception);
      }
      catch (ClassNotFoundException exception)
      {
        // CodeGenPlugin.INSTANCE.log(exception);
      }
    }

    public Class getFeatureClass()
    {
      return featureClass;
    }

    public Method getFeatureMethod()
    {
      return featureMethod;
    }
  }

  public static class DictionaryPattern
  {
    protected static Class [] noParameterTypes = new Class [0];
    protected static Class [] stringParameterType = new Class [] { String.class };
    protected String name;
    protected Feature selectorFeature;
    protected Pattern pattern;

    public DictionaryPattern()
    {
    }

    public DictionaryPattern(Element element)
    {
      initialize(element);
    }

    public void initialize(Element element)
    {
      name = element.getAttribute("name");
      selectorFeature = new Feature(element.getAttribute("select"), noParameterTypes);
      pattern = Pattern.compile(element.getAttribute("match"), Pattern.MULTILINE | Pattern.DOTALL);
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

  public static class PullRule
  {
    protected static Class [] noParameterTypes = new Class [0];
    protected String name;

    protected Pattern sourceMarkup;
    protected Feature sourceGetFeature;
    protected Pattern sourceTransfer;

    protected Pattern targetMarkup;
    protected Feature targetPutFeature;
    protected Pattern targetTransfer;

    public PullRule()
    {
    }

    public PullRule(Element element)
    {
      initialize(element);
    }

    public void initialize(Element element)
    {
      sourceGetFeature = new Feature(element.getAttribute("sourceGet"), noParameterTypes);
      if (sourceGetFeature != null)
      {
        Class sourceReturnType = sourceGetFeature.getFeatureMethod().getReturnType();
        targetPutFeature = new Feature(element.getAttribute("targetPut"), new Class [] { sourceReturnType });
        if (targetPutFeature.getFeatureMethod() == null && sourceReturnType.isArray())
        {
          targetPutFeature = new Feature(element.getAttribute("targetPut"), new Class [] { sourceReturnType.getComponentType() });
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

  public static class SweepRule
  {
    protected String name;
    protected Class selector;
    protected Pattern markup;

    public SweepRule()
    {
    }

    public SweepRule(Element element)
    {
      initialize(element);
    }

    public void initialize(Element element)
    {
      if (element.hasAttribute("select"))
      {
        selector = classForClassName(element.getAttribute("select"));
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

  public static class SortRule
  {
    protected String name;
    protected Class selector;
    protected Pattern markup;

    public SortRule()
    {
    }

    public SortRule(Element element)
    {
      initialize(element);
    }

    public void initialize(Element element)
    {
      if (element.hasAttribute("select"))
      {
        selector = classForClassName(element.getAttribute("select"));
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


  protected List dictionaryPatterns;
  protected List pullRules;
  protected List sweepRules;
  protected List sortRules;
  protected String indent;
  protected String redirect;
  protected boolean standardBraceStyle;
  protected Pattern blockPattern;

  /**
   * This creates an instance.
   */
  public JControlModel(String uri) 
  {
    initialize(uri);
  }

  public JControlModel(Element element)
  {
    initialize(element);
  }

  public boolean convertToStandardBraceStyle()
  {
    return standardBraceStyle;
  }

  public void setConvertToStandardBraceStyle(boolean standardBraceStyle)
  {
    this.standardBraceStyle = standardBraceStyle;
  }

  public String getLeadingTabReplacement()
  {
    return indent;
  }

  public void setLeadingTabReplacement(String indent)
  {
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
      if ("standard".equals(element.getAttributeNS(null, "braceStyle")))
      {
        standardBraceStyle = true;
      }

      if (element.hasAttributeNS(null, "indent"))
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

      for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
      {
        if (child.getNodeType() == Node.ELEMENT_NODE)
        {
          Element elementChild = (Element)child;
          if (elementChild.getLocalName().equals("dictionaryPattern"))
          {
            getDictionaryPatterns().add(new DictionaryPattern(elementChild));
          }
          else if (elementChild.getLocalName().equals("pull"))
          {
            getPullRules().add(new PullRule(elementChild));
          }
          else if (elementChild.getLocalName().equals("sweep"))
          {
            getSweepRules().add(new SweepRule(elementChild));
          }
          else if (elementChild.getLocalName().equals("sort"))
          {
            getSortRules().add(new SortRule(elementChild));
          }
        }
      }
    }
  }

  public static Class classForClassName(String className)
  {
    className = "org.eclipse.jdt.core.jdom.IDOM" + className;
    try
    {
      Class result = Class.forName(className);
      return result;
    }
    catch (ClassNotFoundException exception)
    {
      CodeGenPlugin.INSTANCE.log(exception);
    }
    return null;
  }
}
