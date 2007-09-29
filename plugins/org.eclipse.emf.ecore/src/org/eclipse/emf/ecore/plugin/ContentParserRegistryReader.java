/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: ContentParserRegistryReader.java,v 1.1 2007/09/29 16:41:42 emerks Exp $
 */
package org.eclipse.emf.ecore.plugin;


import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.ecore.resource.Resource;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#INSTANCE global} resource factory's
 * {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getContentTypeToFactoryMap() content type} map.
 * Clients are not expected to use this class directly.
 */
class ContentParserRegistryReader extends RegistryReader
{
  static final String TAG_PARSER = "parser";
  static final String ATT_CONTENT_TYPE_IDENTIFIER = "contentTypeIdentifier";
  static final String ATT_CLASS = "class";

  public ContentParserRegistryReader()
  {
    super
      (Platform.getExtensionRegistry(),
       EcorePlugin.getPlugin().getBundle().getSymbolicName(), 
       EcorePlugin.CONTENT_PARSER_PPID);
  }

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_PARSER))
    {
      String contentTypeIdentifier = element.getAttribute(ATT_CONTENT_TYPE_IDENTIFIER);
      if (contentTypeIdentifier == null)
      {
        logMissingAttribute(element, ATT_CONTENT_TYPE_IDENTIFIER);
      }
      else if (element.getAttribute(ATT_CLASS) == null)
      {
        logMissingAttribute(element, ATT_CLASS);
      }
      else if (add)
      {
        Object previous = Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().put(contentTypeIdentifier, new ResourceFactoryDescriptor(element, ATT_CLASS));
        if (previous instanceof ResourceFactoryDescriptor)
        {
          ResourceFactoryDescriptor descriptor = (ResourceFactoryDescriptor)previous;
          EcorePlugin.INSTANCE.log
            ("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a content parser for '" + contentTypeIdentifier + "'");
        }
        return true;
      }
      else
      {
        Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().remove(contentTypeIdentifier);
        return true;
      }
    }

    return false;
  }
}
