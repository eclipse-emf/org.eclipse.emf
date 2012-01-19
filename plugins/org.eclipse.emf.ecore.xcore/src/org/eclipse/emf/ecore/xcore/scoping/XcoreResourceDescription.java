/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.impl.DefaultResourceDescription;
import org.eclipse.xtext.resource.impl.EObjectDescriptionLookUp;
import org.eclipse.xtext.util.IResourceScopeCache;


/**
 * This implementation ensures that the contents of the resource is not queried
 * to compute the exported elements.
 * @author Sebastian Zarnekow
 */
public class XcoreResourceDescription extends DefaultResourceDescription
{
  public XcoreResourceDescription(Resource resource, IDefaultResourceDescriptionStrategy strategy, IResourceScopeCache cache)
  {
    super(resource, strategy, cache);
  }

  @Override
  protected EObjectDescriptionLookUp getLookUp()
  {
    if (lookup == null)
    {
      lookup = new EObjectDescriptionLookUp(computeExportedObjects());
    }
    return lookup;
  }

}
