/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.mappings;


import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.xtext.common.types.JvmTypeParameter;


public class XTypeParameterMapping extends AbstractMapping
{
  private GenTypeParameter genTypeParameter;

  private JvmTypeParameter typeParameter;

  private ETypeParameter eTypeParameter;

  public GenTypeParameter getGenTypeParameter()
  {
    return genTypeParameter;
  }

  public void setGenTypeParameter(GenTypeParameter genTypeParameter)
  {
    this.genTypeParameter = genTypeParameter;
  }

  public JvmTypeParameter getJvmTypeParameter()
  {
    return typeParameter;
  }

  public void setJvmTypeParameter(JvmTypeParameter typeParameter)
  {
    this.typeParameter = typeParameter;
  }

  public ETypeParameter getETypeParameter()
  {
    return eTypeParameter;
  }

  public void setETypeParameter(ETypeParameter eTypeParameter)
  {
    this.eTypeParameter = eTypeParameter;
  }
}
