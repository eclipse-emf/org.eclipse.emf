/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.interpreter;

import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.inject.ImplementedBy;


/**
 *  Provides access to a resource-set-specific class loader.
 */
@ImplementedBy(IClassLoaderProvider.NullClassLoaderProvider.class)
public interface IClassLoaderProvider
{
  /**
   * Returns the class loader for this resource set.
   */
  ClassLoader getClassLoader(ResourceSet resourceSet);

  /**
   * A default implementation that always returns null.
   */
  class NullClassLoaderProvider implements IClassLoaderProvider
  {
    public ClassLoader getClassLoader(ResourceSet resourceSet)
    {
      return null;
    }
  }
}
