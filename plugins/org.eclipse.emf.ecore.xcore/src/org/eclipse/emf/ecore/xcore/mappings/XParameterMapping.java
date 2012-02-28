/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.mappings;


import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.xtext.common.types.JvmFormalParameter;


public class XParameterMapping extends AbstractMapping
{
  private GenParameter genParameter;

  private JvmFormalParameter formalParameter;

  private EParameter eParameter;

  public GenParameter getGenParameter()
  {
    return genParameter;
  }

  public void setGenParameter(GenParameter genParameter)
  {
    this.genParameter = genParameter;
  }

  public JvmFormalParameter getJvmFormalParameter()
  {
    return formalParameter;
  }

  public void setJvmFormalParameter(JvmFormalParameter formalParameter)
  {
    this.formalParameter = formalParameter;
  }

  public EParameter getEParameter()
  {
    return eParameter;
  }

  public void setEParameter(EParameter eParameter)
  {
    this.eParameter = eParameter;
  }
}
