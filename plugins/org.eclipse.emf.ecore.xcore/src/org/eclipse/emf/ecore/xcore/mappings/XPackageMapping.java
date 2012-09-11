/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.mappings;


import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EPackage;


public class XPackageMapping extends AbstractMapping
{
  private EPackage ePackage;

  private GenPackage genPackage;

  public EPackage getEPackage()
  {
    return ePackage;
  }

  public void setEPackage(EPackage ePackage)
  {
    this.ePackage = ePackage;
  }

  public GenPackage getGenPackage()
  {
    return genPackage;
  }

  public void setGenPackage(GenPackage genPackage)
  {
    this.genPackage = genPackage;
  }

}
