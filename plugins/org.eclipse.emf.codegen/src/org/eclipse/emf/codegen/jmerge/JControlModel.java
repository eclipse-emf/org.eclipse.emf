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
 * $Id: JControlModel.java,v 1.5 2006/01/18 20:41:29 marcelop Exp $
 */
package org.eclipse.emf.codegen.jmerge;


import org.w3c.dom.Element;

import org.eclipse.emf.codegen.CodeGenPlugin;


/**
 *  A control model that  provides dictionaries and rules to drive a merge process.
 *  @deprecated in 2.2.0. Use {@link org.eclipse.emf.codegen.merge.java.JControlModel} instead.
 */
public class JControlModel extends org.eclipse.emf.codegen.merge.java.JControlModel
{
  protected final static String CLASS_PREFIX = "org.eclipse.jdt.core.jdom.IDOM";
  
  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel.Feature} instead.
   */
  public static class Feature extends org.eclipse.emf.codegen.merge.java.JControlModel.Feature
  {
    public Feature(String path, Class [] parameterTypes)
    {
      super(CLASS_PREFIX, path, parameterTypes);
    }
  }

  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel.DictionaryPattern} instead.
   */
  public static class DictionaryPattern extends org.eclipse.emf.codegen.merge.java.JControlModel.DictionaryPattern
  {
    public DictionaryPattern()
    {
      super(CLASS_PREFIX);
    }

    public DictionaryPattern(Element element)
    {
      super(CLASS_PREFIX, element);
    }
    
    protected org.eclipse.emf.codegen.merge.java.JControlModel.Feature createFeature(String classPrefix, String path, Class [] parameterTypes)
    {
      return new Feature(path, parameterTypes);
    }    
  }

  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel.PullRule} instead.
   */
  public static class PullRule extends org.eclipse.emf.codegen.merge.java.JControlModel.PullRule
  {
    public PullRule()
    {
      super(CLASS_PREFIX);
    }

    public PullRule(Element element)
    {
      super(CLASS_PREFIX, element);
    }

    protected org.eclipse.emf.codegen.merge.java.JControlModel.Feature createFeature(String classPrefix, String path, Class [] parameterTypes)
    {
      return new Feature(path, parameterTypes);
    }    

    public void setSourceGetFeature(Feature sourceGetFeature)
    {
      this.sourceGetFeature = sourceGetFeature;
    }

    public void setTargetPutFeature(Feature targetPutFeature)
    {
      this.targetPutFeature = targetPutFeature;
    }
  }

  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel.SweepRule} instead.
   */
  public static class SweepRule extends org.eclipse.emf.codegen.merge.java.JControlModel.SweepRule
  {
    public SweepRule()
    {
      super(CLASS_PREFIX);
    }

    public SweepRule(Element element)
    {
      super(CLASS_PREFIX, element);
    }
  }

  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel.SortRule} instead.
   */
  public static class SortRule extends org.eclipse.emf.codegen.merge.java.JControlModel.SortRule
  {
    public SortRule()
    {
      super(CLASS_PREFIX);
    }

    public SortRule(Element element)
    {
      super(CLASS_PREFIX, element);
    }
  }
  
  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel#classForClassName(String, String)} instead.
   */  
  public static Class classForClassName(String className)
  {
    return org.eclipse.emf.codegen.merge.java.JControlModel.classForClassName(CLASS_PREFIX, className);
  }

  /**
   * This creates an instance.
   */
  public JControlModel(String uri) 
  {
    setClassPrefix(CLASS_PREFIX);
    initialize(uri);
  }

  public JControlModel(Element element)
  {
    setClassPrefix(CLASS_PREFIX);
    initialize(element);
  }
  
  public boolean canMerge()
  {
    return CodeGenPlugin.IS_ECLIPSE_RUNNING;
  }

  protected org.eclipse.emf.codegen.merge.java.JControlModel.DictionaryPattern createDictionaryPattern(String classPrefix, Element elementChild)
  {
    return new DictionaryPattern(elementChild);
  }
  
  protected org.eclipse.emf.codegen.merge.java.JControlModel.PullRule createPullRule(String classPrefix, Element elementChild)
  {
    return new PullRule(elementChild);
  }
  
  protected org.eclipse.emf.codegen.merge.java.JControlModel.SweepRule createSweepRule(String classPrefix, Element elementChild)
  {
    return new SweepRule(elementChild);
  }
  
  protected org.eclipse.emf.codegen.merge.java.JControlModel.SortRule createSortRule(String classPrefix, Element elementChild)
  {
    return new SortRule(elementChild);
  }  
}
