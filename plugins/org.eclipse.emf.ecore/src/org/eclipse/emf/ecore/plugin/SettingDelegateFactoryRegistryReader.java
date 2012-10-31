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
import org.eclipse.core.runtime.RegistryFactory;

import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate.Factory.Registry#INSTANCE global} setting delegate factory registry.
 * Clients are not expected to use this class directly.
 */
class SettingDelegateFactoryRegistryReader extends RegistryReader
{
  static class SettingDelegateFactoryDescriptor extends PluginClassDescriptor implements EStructuralFeature.Internal.SettingDelegate.Factory.Descriptor
  {
    protected EStructuralFeature.Internal.SettingDelegate.Factory factory;

    public SettingDelegateFactoryDescriptor(IConfigurationElement e, String attrName)
    {
      super(e, attrName);
    }

    public EStructuralFeature.Internal.SettingDelegate.Factory getFactory()
    {
      if (factory == null)
      {
        factory = (EStructuralFeature.Internal.SettingDelegate.Factory)createInstance();
      }
      return factory;
    }
  }

  static final String TAG_FACTORY = "factory";
  static final String ATT_URI = "uri";
  static final String ATT_CLASS = "class";

  public SettingDelegateFactoryRegistryReader()
  {
    super(RegistryFactory.getRegistry(), EcorePlugin.INSTANCE.getSymbolicName(), EcorePlugin.SETTING_DELEGATE_PPID);
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
        Object previous = EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.put(uri, new SettingDelegateFactoryDescriptor(element, ATT_CLASS));
        if (previous instanceof SettingDelegateFactoryDescriptor)
        {
          SettingDelegateFactoryDescriptor descriptor = (SettingDelegateFactoryDescriptor)previous;
          EcorePlugin.INSTANCE.log("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a setting delegate factory for '" + uri + "'");
        }
        return true;
      }
      else
      {
        EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.remove(uri);
        return true;
      }
    }

    return false;
  }
}
