/**
 * <copyright>
 *
 * Copyright (c) 2011 Hallvard Traetteberg and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Hallvard Traetteberg - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ConversionDelegateFactoryRegistryReader.java,v 1.1 2011/10/25 12:28:05 emerks Exp $
 */
package org.eclipse.emf.ecore.plugin;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EDataType;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.util.EDataType.ConversionDelegate.Factory.Registry#INSTANCE global} EDataType delegate factory registry.
 * Clients are not expected to use this class directly.
 */
class ConversionDelegateFactoryRegistryReader extends RegistryReader
{
  static class ConversionDelegateFactoryDescriptor extends PluginClassDescriptor implements EDataType.Internal.ConversionDelegate.Factory.Descriptor
  {
    protected EDataType.Internal.ConversionDelegate.Factory factory;

    public ConversionDelegateFactoryDescriptor(IConfigurationElement e, String attrName)
    {
      super(e, attrName);
    }

    public EDataType.Internal.ConversionDelegate.Factory getFactory()
    {
      if (factory == null)
      {
        factory = (EDataType.Internal.ConversionDelegate.Factory)createInstance();
      }
      return factory;
    }
  }

  static final String TAG_FACTORY = "factory";
  static final String ATT_URI = "uri";
  static final String ATT_CLASS = "class";

  public ConversionDelegateFactoryRegistryReader()
  {
    super(Platform.getExtensionRegistry(), EcorePlugin.getPlugin().getBundle().getSymbolicName(), EcorePlugin.CONVERSION_DELEGATE_PPID);
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
        Object previous = EDataType.Internal.ConversionDelegate.Factory.Registry.INSTANCE.put(uri, new ConversionDelegateFactoryDescriptor(element, ATT_CLASS));
        if (previous instanceof ConversionDelegateFactoryDescriptor)
        {
          ConversionDelegateFactoryDescriptor descriptor = (ConversionDelegateFactoryDescriptor)previous;
          EcorePlugin.INSTANCE.log("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a conversion delegate factory for '" + uri + "'");
        }
        return true;
      }
      else
      {
        EDataType.Internal.ConversionDelegate.Factory.Registry.INSTANCE.remove(uri);
        return true;
      }
    }

    return false;
  }
}
