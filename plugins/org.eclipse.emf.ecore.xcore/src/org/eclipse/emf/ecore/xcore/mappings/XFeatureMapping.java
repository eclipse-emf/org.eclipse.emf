/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.mappings;


import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.common.types.JvmOperation;


public class XFeatureMapping extends AbstractMapping
{
  private GenFeature genFeature;

  private EStructuralFeature eStructuralFeature;

  private JvmOperation getter;

  private JvmOperation setter;

  private JvmOperation isSetter;

  private JvmOperation unsetter;

  public GenFeature getGenFeature()
  {
    return genFeature;
  }

  public void setGenFeature(GenFeature genFeature)
  {
    this.genFeature = genFeature;
  }

  public JvmOperation getGetter()
  {
    return getter;
  }

  public void setGetter(JvmOperation getter)
  {
    this.getter = getter;
  }

  public JvmOperation getSetter()
  {
    return setter;
  }

  public void setSetter(JvmOperation setter)
  {
    this.setter = setter;
  }

  public JvmOperation getIsSetter()
  {
    return isSetter;
  }

  public void setIsSetter(JvmOperation isSetter)
  {
    this.isSetter = isSetter;
  }

  public JvmOperation getUnsetter()
  {
    return unsetter;
  }

  public void setUnsetter(JvmOperation unsetter)
  {
    this.unsetter = unsetter;
  }

  public EStructuralFeature getEStructuralFeature()
  {
    return eStructuralFeature;
  }

  public void setEStructuralFeature(EStructuralFeature eStructuralFeature)
  {
    this.eStructuralFeature = eStructuralFeature;
  }
}
