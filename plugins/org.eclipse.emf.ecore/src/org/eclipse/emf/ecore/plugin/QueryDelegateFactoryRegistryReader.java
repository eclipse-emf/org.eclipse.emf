/**
 * Copyright (c) 2010 Kenn Hussey and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Kenn Hussey - Initial API and implementation
 */
package org.eclipse.emf.ecore.plugin;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.util.QueryDelegate;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.util.QueryDelegate.Factory.Registry#INSTANCE global} query delegate factory registry.
 * Clients are not expected to use this class directly.
 */
class QueryDelegateFactoryRegistryReader extends RegistryReader
{
  static class QueryDelegateFactoryDescriptor extends PluginClassDescriptor implements QueryDelegate.Factory.Descriptor
  {
    protected QueryDelegate.Factory factory;

    public QueryDelegateFactoryDescriptor(IConfigurationElement e, String attrName)
    {
      super(e, attrName);
    }

    public QueryDelegate.Factory getFactory()
    {
      if (factory == null)
      {
        factory = (QueryDelegate.Factory)createInstance();
      }
      return factory;
    }
  }

  static final String TAG_FACTORY = "factory";
  static final String ATT_URI = "uri";
  static final String ATT_CLASS = "class";

  public QueryDelegateFactoryRegistryReader()
  {
    super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), EcorePlugin.QUERY_DELEGATE_PPID);
  }

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_FACTORY))
    {
      String uri = element.getAttribute(ATT_URI);
      if (uri == null)
      {
        logMissingAttribute(element, ATT_URI);
      }
      else if (element.getAttribute(ATT_CLASS) == null)
      {
        logMissingAttribute(element, ATT_CLASS);
      }
      else if (add)
      {
        Object previous = QueryDelegate.Factory.Registry.INSTANCE.put(uri, new QueryDelegateFactoryDescriptor(element, ATT_CLASS));
        if (previous instanceof QueryDelegateFactoryDescriptor)
        {
          QueryDelegateFactoryDescriptor descriptor = (QueryDelegateFactoryDescriptor)previous;
          EcorePlugin.INSTANCE.log("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a query delegate factory for '" + uri + "'");
        }
        return true;
      }
      else
      {
        QueryDelegate.Factory.Registry.INSTANCE.remove(uri);
        return true;
      }
    }

    return false;
  }
}
