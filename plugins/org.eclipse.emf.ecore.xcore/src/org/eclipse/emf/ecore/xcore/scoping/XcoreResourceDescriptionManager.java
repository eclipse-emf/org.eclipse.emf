/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.DerivedStateAwareResourceDescriptionManager;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IResourceDescription;

import com.google.inject.Singleton;


/**
 * The resource description manager for Xcore resources. It produces an {@link XcoreResourceDescription} 
 * which allows to produce descriptions without iterating the derived contents of the resource.
 * 
 * @author Sebastian Zarnekow
 */
@Singleton
public class XcoreResourceDescriptionManager extends DerivedStateAwareResourceDescriptionManager
{
  @Override
  protected IResourceDescription createResourceDescription(Resource resource, IDefaultResourceDescriptionStrategy strategy)
  {
    return new XcoreResourceDescription(resource, strategy, getCache());
  }
}
