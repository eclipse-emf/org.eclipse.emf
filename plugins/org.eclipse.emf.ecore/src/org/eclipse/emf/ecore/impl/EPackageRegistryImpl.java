/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EPackageRegistryImpl.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.HashMap;

import org.eclipse.emf.ecore.EPackage;


/**
 * An implementation of a package registry.
 */
public class EPackageRegistryImpl extends HashMap implements EPackage.Registry
{
  protected EPackage.Registry delegateRegistry;

  public EPackageRegistryImpl()
  {
  }

  public EPackageRegistryImpl(EPackage.Registry delegateRegistry)
  {
    this.delegateRegistry = delegateRegistry;
  }

  public EPackage getEPackage(String nsURI)
  {
    Object ePackage = get(nsURI);
    if (ePackage instanceof EPackage)
    {
      EPackage result = (EPackage)ePackage;
      if (result.getNsURI() == null)
      {
        initialize(result);
      }
      return result;
    }
    else if (ePackage instanceof EPackage.Descriptor)
    {
      EPackage.Descriptor ePackageDescriptor = (EPackage.Descriptor)ePackage;
      EPackage result = ePackageDescriptor.getEPackage();
      put(nsURI, result);
      initialize(result);
      return result;
    }
    else
    {
      return delegatedGetEPackage(nsURI);
    }
  }

  protected void initialize(EPackage ePackage)
  {
/*
    // Try to invoke init method to initialize prerequisite packages
    //
    try
    {
      Method initMethod = ePackage.getClass().getMethod("init", null);
      initMethod.invoke(null, null);
    }
    catch (NoSuchMethodException e)
    {
       throw new WrappedException(e);
    }
    catch (IllegalAccessException e)
    {
       throw new WrappedException(e);
    }
    catch (InvocationTargetException e)
    {
      throw new WrappedException(e);
    }
*/
  }

  public EPackage delegatedGetEPackage(String nsURI)
  {
    if (delegateRegistry != null)
    {
      return delegateRegistry.getEPackage(nsURI);
    }

    return null;
  }

  public boolean containsKey(Object key)
  {
    return super.containsKey(key) || delegateRegistry != null && delegateRegistry.containsKey(key);
  }
}
