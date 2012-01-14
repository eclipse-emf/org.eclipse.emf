/**
 * Copyright (c) 2009 Kenn Hussey and others.
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

import org.eclipse.emf.ecore.EOperation;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.EOperation.Internal.InvocationDelegate.Factory.Registry#INSTANCE global} operation delegate factory registry.
 * Clients are not expected to use this class directly.
 */
class InvocationDelegateFactoryRegistryReader extends RegistryReader
{
  static class InvocationDelegateFactoryDescriptor extends PluginClassDescriptor implements EOperation.Internal.InvocationDelegate.Factory.Descriptor
  {
    protected EOperation.Internal.InvocationDelegate.Factory factory;

    public InvocationDelegateFactoryDescriptor(IConfigurationElement e, String attrName)
    {
      super(e, attrName);
    }

    public EOperation.Internal.InvocationDelegate.Factory getFactory()
    {
      if (factory == null)
      {
        factory = (EOperation.Internal.InvocationDelegate.Factory)createInstance();
      }
      return factory;
    }
  }

  static final String TAG_FACTORY = "factory";
  static final String ATT_URI = "uri";
  static final String ATT_CLASS = "class";

  public InvocationDelegateFactoryRegistryReader()
  {
    super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), EcorePlugin.INVOCATION_DELEGATE_PPID);
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
        Object previous = EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.put(uri, new InvocationDelegateFactoryDescriptor(element, ATT_CLASS));
        if (previous instanceof InvocationDelegateFactoryDescriptor)
        {
          InvocationDelegateFactoryDescriptor descriptor = (InvocationDelegateFactoryDescriptor)previous;
          EcorePlugin.INSTANCE.log("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register an invocation delegate factory for '" + uri + "'");
        }
        return true;
      }
      else
      {
        EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.remove(uri);
        return true;
      }
    }

    return false;
  }
}
