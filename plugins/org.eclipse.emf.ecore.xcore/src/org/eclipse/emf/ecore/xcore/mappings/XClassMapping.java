/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.mappings;


import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer;
import org.eclipse.xtext.common.types.JvmGenericType;


public class XClassMapping extends AbstractMapping
{
  private EClass eClass;

  private GenClass genClass;

  public EClass getEClass()
  {
    return eClass;
  }

  public void setEClass(EClass eclass)
  {
    this.eClass = eclass;
  }

  public GenClass getGenClass()
  {
    return genClass;
  }

  public void setGenClass(GenClass genClass)
  {
    this.genClass = genClass;
  }

  public JvmGenericType getInterfaceType()
  {
    return XcoreJvmInferrer.getInferredElement(genClass, genClass.getQualifiedInterfaceName());
  }

  public JvmGenericType getClassType()
  {
    return XcoreJvmInferrer.getInferredElement(genClass, genClass.getQualifiedClassName());
  }
}
