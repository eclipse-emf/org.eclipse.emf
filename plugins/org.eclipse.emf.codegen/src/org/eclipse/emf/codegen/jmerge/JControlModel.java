/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.jmerge;


import org.w3c.dom.Element;

import org.eclipse.emf.common.EMFPlugin;


/**
 *  A control model that  provides dictionaries and rules to drive a merge process.
 *  @deprecated in 2.2.0. Use {@link org.eclipse.emf.codegen.merge.java.JControlModel} instead.
 */
@Deprecated
public class JControlModel extends org.eclipse.emf.codegen.merge.java.JControlModel
{
  @Deprecated
  protected final static String CLASS_PREFIX = "org.eclipse.jdt.core.jdom.IDOM";
  
  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel.Feature} instead.
   */
  @Deprecated
  public static class Feature extends org.eclipse.emf.codegen.merge.java.JControlModel.Feature
  {
    @Deprecated
    public Feature(String path, Class<?> [] parameterTypes)
    {
      super(CLASS_PREFIX, path, parameterTypes);
    }
  }

  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel.DictionaryPattern} instead.
   */
  @Deprecated
  public static class DictionaryPattern extends org.eclipse.emf.codegen.merge.java.JControlModel.DictionaryPattern
  {
    @Deprecated
    public DictionaryPattern()
    {
      super(CLASS_PREFIX);
    }

    @Deprecated
    public DictionaryPattern(Element element)
    {
      super(CLASS_PREFIX, element);
    }
    
    @Deprecated
    @Override
    protected org.eclipse.emf.codegen.merge.java.JControlModel.Feature createFeature(String classPrefix, String path, Class<?> [] parameterTypes)
    {
      return new Feature(path, parameterTypes);
    }    
  }

  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel.PullRule} instead.
   */
  @Deprecated
  public static class PullRule extends org.eclipse.emf.codegen.merge.java.JControlModel.PullRule
  {
    @Deprecated
    public PullRule()
    {
      super(CLASS_PREFIX);
    }

    @Deprecated
    public PullRule(Element element)
    {
      super(CLASS_PREFIX, element);
    }

    
    @Deprecated
    @Override
    protected org.eclipse.emf.codegen.merge.java.JControlModel.Feature createFeature(String classPrefix, String path, Class<?> [] parameterTypes)
    {
      return new Feature(path, parameterTypes);
    }    

    @Deprecated
    public void setSourceGetFeature(Feature sourceGetFeature)
    {
      this.sourceGetFeature = sourceGetFeature;
    }

    @Deprecated
    public void setTargetPutFeature(Feature targetPutFeature)
    {
      this.targetPutFeature = targetPutFeature;
    }
  }

  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel.SweepRule} instead.
   */
  @Deprecated
  public static class SweepRule extends org.eclipse.emf.codegen.merge.java.JControlModel.SweepRule
  {
    @Deprecated
    public SweepRule()
    {
      super(CLASS_PREFIX);
    }

    @Deprecated
    public SweepRule(Element element)
    {
      super(CLASS_PREFIX, element);
    }
  }

  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel.SortRule} instead.
   */
  @Deprecated
  public static class SortRule extends org.eclipse.emf.codegen.merge.java.JControlModel.SortRule
  {
    @Deprecated
    public SortRule()
    {
      super(CLASS_PREFIX);
    }

    @Deprecated
    public SortRule(Element element)
    {
      super(CLASS_PREFIX, element);
    }
  }
  
  /**
   * @deprecated in 2.2.0. 
   * Use {@link org.eclipse.emf.codegen.merge.java.JControlModel#classForClassName(String, String)} instead.
   */  
  @Deprecated
  public static Class<?> classForClassName(String className)
  {
    return org.eclipse.emf.codegen.merge.java.JControlModel.classForClassName(CLASS_PREFIX, className);
  }

  /**
   * This creates an instance.
   */
  @Deprecated
  public JControlModel(String uri) 
  {
    setClassPrefix(CLASS_PREFIX);
    initialize(uri);
  }

  @Deprecated
  public JControlModel(Element element)
  {
    setClassPrefix(CLASS_PREFIX);
    initialize(element);
  }
  
  @Deprecated
  @Override
  public boolean canMerge()
  {
    return EMFPlugin.IS_ECLIPSE_RUNNING;
  }

  @Deprecated
  @Override
  protected org.eclipse.emf.codegen.merge.java.JControlModel.DictionaryPattern createDictionaryPattern(String classPrefix, Element elementChild)
  {
    return new DictionaryPattern(elementChild);
  }
  
  @Deprecated
  @Override
  protected org.eclipse.emf.codegen.merge.java.JControlModel.PullRule createPullRule(String classPrefix, Element elementChild)
  {
    return new PullRule(elementChild);
  }
  
  @Deprecated
  @Override
  protected org.eclipse.emf.codegen.merge.java.JControlModel.SweepRule createSweepRule(String classPrefix, Element elementChild)
  {
    return new SweepRule(elementChild);
  }
  
  @Deprecated
  @Override
  protected org.eclipse.emf.codegen.merge.java.JControlModel.SortRule createSortRule(String classPrefix, Element elementChild)
  {
    return new SortRule(elementChild);
  }  
}
