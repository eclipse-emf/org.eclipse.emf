/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EValidatorRegistryImpl.java,v 1.1 2004/05/05 19:51:42 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.HashMap;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.util.EObjectValidator;


/**
 * An implementation of a validator registry.
 */
public class EValidatorRegistryImpl extends HashMap implements EValidator.Registry
{
  protected EValidator.Registry delegateRegistry;

  public EValidatorRegistryImpl()
  {
  }

  public EValidatorRegistryImpl(EValidator.Registry delegateRegistry)
  {
    this.delegateRegistry = delegateRegistry;
  }

  public Object get(Object key)
  {
    EValidator eValidator = (EValidator)super.get(key);
    if (eValidator != null)
    {
      return eValidator;
    }
    else
    {
      return delegatedGet(key);
    }
  }

  public EValidator getEValidator(EPackage ePackage)
  {
    return (EValidator)get(ePackage);
  }

  protected Object delegatedGet(Object key)
  {
    if (delegateRegistry != null)
    {
      return delegateRegistry.get(key);
    }

    return EObjectValidator.INSTANCE;
  }

  public boolean containsKey(Object key)
  {
    return super.containsKey(key) || delegateRegistry != null && delegateRegistry.containsKey(key);
  }
}
