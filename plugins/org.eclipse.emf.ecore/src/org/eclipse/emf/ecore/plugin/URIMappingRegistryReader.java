/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.plugin;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.resource.URIConverter#URI_MAP global} mapping registry.
 * Clients are not expected to use this class directly.
 */
class URIMappingRegistryReader extends RegistryReader 
{
  static final String TAG_MAPPING  = "mapping";
  static final String ATT_SOURCE   = "source";
  static final String ATT_TARGET   = "target";
   
  protected Map<URI, IConfigurationElement> map = new HashMap<URI, IConfigurationElement>();

  public URIMappingRegistryReader() 
  {
    super
      (Platform.getExtensionRegistry(),
       EcorePlugin.getPlugin().getBundle().getSymbolicName(),
       EcorePlugin.URI_MAPPING_PPID);
  }

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add) 
  {
    if (element.getName().equals(TAG_MAPPING))
    {
      String sourceURIValue = element.getAttribute(ATT_SOURCE);
      if (sourceURIValue == null)
      {
        logMissingAttribute(element, ATT_SOURCE);
      }
      else
      {
        String targetURIValue = element.getAttribute(ATT_TARGET);
        if (targetURIValue == null)
        {
          logMissingAttribute(element, ATT_TARGET);
        }
        else if (add)
        {
          URI sourceURI = URI.createURI(sourceURIValue);
          URI targetURI = URI.createURI(targetURIValue);
          if (targetURI.isRelative() && targetURI.hasRelativePath())
          {
            targetURI = 
              targetURI.resolve
                (URI.createURI
                  (Platform.getBundle(element.getDeclaringExtension().getContributor().getName()).getEntry("/").toString()));
          }
          URIConverter.URI_MAP.put(sourceURI, targetURI);
          IConfigurationElement previous = map.put(sourceURI, element);
          if (previous != null)
          {
            EcorePlugin.INSTANCE.log
              ("Both '" + previous.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a URI mapping for '" + sourceURI + "'");
          }
          return true;
        }
        else
        {
          URI sourceURI = URI.createURI(sourceURIValue);
          URIConverter.URI_MAP.remove(sourceURI);
          map.remove(sourceURI);
          return true;
        }
      }
    }
    return false;
  }
}
