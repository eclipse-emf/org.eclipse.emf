/**
 * Copyright (c) 2007-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.plugin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.ecore.resource.ContentHandler;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#INSTANCE global} resource factory's
 * {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getContentTypeToFactoryMap() content type} map.
 * Clients are not expected to use this class directly.
 */
class ContentHandlerRegistryReader extends RegistryReader
{
  static final String TAG_HANDLER = "contentHandler";
  static final String ATT_CLASS = "class";
  static final String ATT_PRIORITY = "priority";

  public ContentHandlerRegistryReader()
  {
    super
      (Platform.getExtensionRegistry(),
       EcorePlugin.getPlugin().getBundle().getSymbolicName(), 
       EcorePlugin.CONTENT_HANDLER_PPID);
  }

  private static final Map<String, List<ContentHandler>> CONTRIBUTION_MAP = new HashMap<String, List<ContentHandler>>();

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_HANDLER))
    {
      int priority = 0;
      if (element.getAttribute(ATT_PRIORITY) != null)
      {
        priority = Integer.parseInt(element.getAttribute(ATT_PRIORITY));
      }
      String contributorClassName = element.getAttribute(ATT_CLASS);
      if (contributorClassName == null)
      {
        logMissingAttribute(element, ATT_CLASS);
      }
      else
      {
        String contributorName = element.getContributor().getName();
        if (add)
        {
          try
          {
            @SuppressWarnings("unchecked")
            Class<ContentHandler> contributorHandlerClass = (Class<ContentHandler>)Platform.getBundle(element.getNamespaceIdentifier()).loadClass(contributorClassName);
            Map<String, String> parameters = new HashMap<String, String>();
            for (IConfigurationElement parameter : element.getChildren("parameter"))
            {
              parameters.put(parameter.getAttribute("name"), parameter.getAttribute("value"));
            }
            ContentHandler contentHandler = 
              parameters.isEmpty() ?
                contributorHandlerClass.newInstance() : 
                contributorHandlerClass.getConstructor(Map.class).newInstance(parameters);
            ContentHandler.Registry.INSTANCE.put(priority, contentHandler);
            List<ContentHandler> contributions = CONTRIBUTION_MAP.get(contributorName);
            if (contributions == null)
            {
              CONTRIBUTION_MAP.put(contributorName, contributions = new ArrayList<ContentHandler>());
            }
            contributions.add(contentHandler);
          }
          catch (Exception exception)
          {
            EcorePlugin.INSTANCE.log(exception);
          }
          return true;
        }
        else
        {
          List<ContentHandler> contributions = CONTRIBUTION_MAP.get(contributorName);
          if (contributions != null)
          {
            for (List<ContentHandler> values : ContentHandler.Registry.INSTANCE.values())
            {
              values.removeAll(contributions);
            }
          }
          CONTRIBUTION_MAP.remove(contributorName);
          return true;
        }
      }
    }
    else if (element.getName().equals("parameter"))
    {
      return true;
    }

    return false;
  }
}
