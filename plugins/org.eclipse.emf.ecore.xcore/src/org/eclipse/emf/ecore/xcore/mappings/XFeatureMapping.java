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
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer;
import org.eclipse.xtext.common.types.JvmOperation;


public class XFeatureMapping extends AbstractMapping
{
  private GenFeature genFeature;

  private EStructuralFeature eStructuralFeature;

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
    return XcoreJvmInferrer.getInferredElement(genFeature, genFeature.getGenClass().getQualifiedInterfaceName() + "." + genFeature.getGetAccessor());
  }

  public JvmOperation getSetter()
  {
    return XcoreJvmInferrer.getInferredElement(genFeature, genFeature.getGenClass().getQualifiedInterfaceName() + ".set" + genFeature.getAccessorName());
  }

  public JvmOperation getIsSetter()
  {
    return XcoreJvmInferrer.getInferredElement(genFeature, genFeature.getGenClass().getQualifiedInterfaceName() + ".isSet" + genFeature.getAccessorName());
  }

  public JvmOperation getUnsetter()
  {
    return XcoreJvmInferrer.getInferredElement(genFeature, genFeature.getGenClass().getQualifiedInterfaceName() + ".unset" + genFeature.getAccessorName());
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
