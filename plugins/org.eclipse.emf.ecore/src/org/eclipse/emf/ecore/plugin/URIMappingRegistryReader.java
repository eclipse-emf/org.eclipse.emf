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
 * $Id: URIMappingRegistryReader.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.plugin;


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
   
  public URIMappingRegistryReader() 
  {
    super
      (Platform.getPluginRegistry(),
       EcorePlugin.getPlugin().getDescriptor().getUniqueIdentifier(),
       EcorePlugin.URI_MAPPING_PPID);
  }

  protected boolean readElement(IConfigurationElement element) 
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
        else
        {
          URI sourceURI = URI.createURI(sourceURIValue);
          URI targetURI = URI.createURI(targetURIValue);
          if (targetURI.isRelative() && targetURI.hasRelativePath())
          {
            targetURI = 
              targetURI.resolve
                (URI.createURI
                  (element.getDeclaringExtension().getDeclaringPluginDescriptor().getInstallURL().toString()));
          }
          URIConverter.URI_MAP.put(sourceURI, targetURI);
          return true;
        }
      }
    }
    return false;
  }
}
