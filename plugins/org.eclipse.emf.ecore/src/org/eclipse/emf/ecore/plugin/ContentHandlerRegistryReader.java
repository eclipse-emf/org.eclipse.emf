/**
 * Copyright (c) 2007-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.plugin;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
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
      (RegistryFactory.getRegistry(),
       EcorePlugin.INSTANCE.getSymbolicName(), 
       EcorePlugin.CONTENT_HANDLER_PPID);
  }

  private static final Map<String, List<ContentHandler>> CONTRIBUTION_MAP = new HashMap<String, List<ContentHandler>>();

  @Override
  protected boolean readElement(final IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_HANDLER))
    {
      int priority = 0;
      if (element.getAttribute(ATT_PRIORITY) != null)
      {
        priority = Integer.parseInt(element.getAttribute(ATT_PRIORITY));
      }
      final String contributorClassName = element.getAttribute(ATT_CLASS);
      if (contributorClassName == null)
      {
        logMissingAttribute(element, ATT_CLASS);
      }
      else
      {
        String contributorName = element.getContributor().getName();
        if (add)
        {
          class Delegate implements ContentHandler
          {
            private volatile ContentHandler delegate;

            private ContentHandler getDelegate()
            {
              if (delegate == null)
              {
                synchronized (this)
                {
                  if (delegate == null)
                  {
                    try
                    {
                      @SuppressWarnings("unchecked")
                      Class<ContentHandler> contributorHandlerClass = (Class<ContentHandler>)CommonPlugin.loadClass(element.getNamespaceIdentifier(), contributorClassName);
                      Map<String, String> parameters = new HashMap<String, String>();
                      for (IConfigurationElement parameter : element.getChildren("parameter"))
                      {
                        parameters.put(parameter.getAttribute("name"), parameter.getAttribute("value"));
                      }
                      delegate = parameters.isEmpty()
                        ? contributorHandlerClass.getDeclaredConstructor().newInstance() : contributorHandlerClass.getConstructor(Map.class).newInstance(parameters);
                    }
                    catch (Exception exception)
                    {
                      delegate = this;
                      EcorePlugin.INSTANCE.log(exception);
                    }
                  }
                }
              }
              return delegate;
            }

            public boolean canHandle(URI uri)
            {
              ContentHandler delegate = getDelegate();
              return delegate != this && delegate.canHandle(uri);
            }

            public Map<String, ?> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
            {
              ContentHandler delegate = getDelegate();
              if (delegate != this)
              {
                return delegate.contentDescription(uri, inputStream, options, context);
              }
              else
              {
                return Collections.emptyMap();
              }
            }
          }

          ContentHandler contentHandler = new Delegate();
          ContentHandler.Registry.INSTANCE.put(priority, contentHandler);
          List<ContentHandler> contributions = CONTRIBUTION_MAP.get(contributorName);
          if (contributions == null)
          {
            CONTRIBUTION_MAP.put(contributorName, contributions = new ArrayList<ContentHandler>());
          }
          contributions.add(contentHandler);
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
