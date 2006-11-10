/**
 * <copyright> 
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: ValidatorGeneratorAdapterFactory.java,v 1.1 2006/11/10 23:04:27 davidms Exp $
 */
package org.eclipse.emf.examples.generator.validator;

import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;

public class ValidatorGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory
{
  public Adapter createGenPackageAdapter() { return null; }
  public Adapter createGenEnumAdapter() { return null; }

  public Adapter createGenModelAdapter()
  {
    if (genModelGeneratorAdapter == null)
    {
      genModelGeneratorAdapter = new GenModelValidatorGeneratorAdapter(this);
    }
    return genModelGeneratorAdapter;
  }

  public Adapter createGenClassAdapter()
  {
    if (genClassGeneratorAdapter == null)
    {
      genClassGeneratorAdapter = new GenClassValidatorGeneratorAdapter(this);
    }
    return genClassGeneratorAdapter;
  }
}
