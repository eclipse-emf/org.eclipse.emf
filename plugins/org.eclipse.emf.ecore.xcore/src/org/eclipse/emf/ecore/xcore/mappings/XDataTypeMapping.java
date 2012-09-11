/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.mappings;


import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer;
import org.eclipse.xtext.common.types.JvmOperation;


public class XDataTypeMapping extends AbstractMapping
{
  private EDataType eDataType;

  private GenDataType genDataType;

  public EDataType getEDataType()
  {
    return eDataType;
  }

  public void setEDataType(EDataType eDataType)
  {
    this.eDataType = eDataType;
  }

  public GenDataType getGenDataType()
  {
    return genDataType;
  }

  public void setGenDataType(GenDataType genDataType)
  {
    this.genDataType = genDataType;
  }

  public JvmOperation getCreator()
  {
    return XcoreJvmInferrer.getInferredElement(genDataType, genDataType.getGenPackage().getQualifiedFactoryClassName() + ".create" + genDataType.getName());
  }

  public JvmOperation getConverter()
  {
    return XcoreJvmInferrer.getInferredElement(genDataType, genDataType.getGenPackage().getQualifiedFactoryClassName() + ".convert" + genDataType.getName());
  }
}
