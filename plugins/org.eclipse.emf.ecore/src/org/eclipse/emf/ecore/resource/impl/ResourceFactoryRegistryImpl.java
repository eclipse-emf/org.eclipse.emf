/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: ResourceFactoryRegistryImpl.java,v 1.4 2006/12/05 20:22:27 emerks Exp $
 */
package org.eclipse.emf.ecore.resource.impl;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * An extensible implementation of a resource factory registry.
 */
public class ResourceFactoryRegistryImpl implements Resource.Factory.Registry
{
  /**
   * The protocol map.
   */
  protected Map<String, Object> protocolToFactoryMap = new HashMap<String, Object>();

  /**
   * The extension map.
   */
  protected Map<String, Object> extensionToFactoryMap = new HashMap<String, Object>();

  /**
   * Creates an instance.
   */
  public ResourceFactoryRegistryImpl()
  {
    super();
  }

  /**
   * Returns the resource factory appropriate for the given URI.
   * <p>
   * This implementation does the {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getFactory typical} thing.
   * It will delegate to {@link #delegatedGetFactory(URI)} 
   * in the case that the typical behaviour doesn't produce a result;
   * clients are encouraged to override that method only.
   * </p>
   * @param uri the URI.
   * @return the resource factory appropriate for the given URI.
   * @see org.eclipse.emf.ecore.resource.ResourceSet#createResource(URI)
   */
  public Resource.Factory getFactory(URI uri)
  {
    String protocol = uri.scheme();
    Object resourceFactory =  protocolToFactoryMap.get(protocol);
    if (resourceFactory == null)
    {
      String extension = uri.fileExtension();
      resourceFactory = extensionToFactoryMap.get(extension);
      if (resourceFactory == null)
      {
        resourceFactory = extensionToFactoryMap.get("*");
        if (resourceFactory == null)
        {
          resourceFactory = delegatedGetFactory(uri);
        }
      }
    }

    return 
      resourceFactory instanceof Resource.Factory.Descriptor ?
        ((Resource.Factory.Descriptor)resourceFactory).createFactory() :
        (Resource.Factory)resourceFactory;
  }

  /**
   * Returns the resource factory appropriate for the given URI, when standard alternatives fail.
   * <p>
   * This implementation returns <code>null</code>;
   * clients are encouraged to override it.
   * </p>
   * @param uri the URI.
   * @return the resource factory appropriate for the given URI.
   * @see #getFactory(URI)
   */
  protected Resource.Factory delegatedGetFactory(URI uri)
  {
    return null;
  }

  /*
   * Javadoc copied from interface.
   */
  public Map<String, Object> getExtensionToFactoryMap()
  {
    return extensionToFactoryMap;
  }

  /*
   * Javadoc copied from interface.
   */
  public Map<String, Object> getProtocolToFactoryMap()
  {
    return protocolToFactoryMap;
  }
}
