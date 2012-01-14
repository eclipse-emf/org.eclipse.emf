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


import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.ecore.resource.Resource;


/**
 * A plugin extension reader that populates the 
 * {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#INSTANCE global} resource factory's 
 * {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getExtensionToFactoryMap() extension} map.
 * Clients are not expected to use this class directly.
 */
class ExtensionParserRegistryReader extends RegistryReader
{
  static final String TAG_PARSER = "parser";
  static final String ATT_TYPE = "type";
  static final String ATT_CLASS = "class";

  public ExtensionParserRegistryReader()
  {
    super
      (Platform.getExtensionRegistry(),
       EcorePlugin.getPlugin().getBundle().getSymbolicName(), 
       EcorePlugin.EXTENSION_PARSER_PPID);
  }

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_PARSER))
    {
      String type = element.getAttribute(ATT_TYPE);
      if (type == null)
      {
        logMissingAttribute(element, ATT_TYPE);
      }
      else if (element.getAttribute(ATT_CLASS) == null)
      {
        logMissingAttribute(element, ATT_CLASS);
      }
      else if (add)
      {
        Object previous = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(type, new ResourceFactoryDescriptor(element, ATT_CLASS));
        if (previous instanceof ResourceFactoryDescriptor)
        {
          ResourceFactoryDescriptor descriptor = (ResourceFactoryDescriptor)previous;
          EcorePlugin.INSTANCE.log
            ("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register an extension parser for '" + type + "'");
        }
        return true;
      }
      else
      {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().remove(type);
        return true;
      }
    }
    return false;
  }
}
