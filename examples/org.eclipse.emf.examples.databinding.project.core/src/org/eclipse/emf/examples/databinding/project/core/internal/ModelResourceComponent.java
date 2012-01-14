/**
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 */
package org.eclipse.emf.examples.databinding.project.core.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.examples.databinding.project.core.IModelLoadingService;
import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.IModelResourceProvider;

public class ModelResourceComponent implements IModelLoadingService
{
  private List<IModelResourceProvider> providers = new ArrayList<IModelResourceProvider>();

  public void addProvider(IModelResourceProvider provider)
  {
    synchronized (providers)
    {
      providers.add(provider);
    }
  }

  public void removeProvider(IModelResourceProvider provider)
  {
    synchronized (providers)
    {
      providers.remove(provider);
    }
  }

  public IModelResource findAndLoadResource(String uri)
  {
    synchronized (providers)
    {
      for (IModelResourceProvider p : providers)
      {
        if (p.canHandleUri(uri))
        {
          return p.loadResource(uri);
        }
      }
    }

    return null;
  }
}
