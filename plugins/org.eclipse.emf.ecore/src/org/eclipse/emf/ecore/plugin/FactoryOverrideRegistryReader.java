/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
import org.eclipse.core.runtime.RegistryFactory;

import org.eclipse.emf.ecore.EPackage;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.EPackage.Registry#INSTANCE global} package registry.
 * Clients are not expected to use this class directly.
 */
class FactoryOverrideRegistryReader extends RegistryReader
{
  static final String TAG_FACTORY = "factory";
  static final String ATT_URI = "uri";
  static final String ATT_CLASS = "class";
  
  public FactoryOverrideRegistryReader()
  {
    super
      (RegistryFactory.getRegistry(),
       EcorePlugin.INSTANCE.getSymbolicName(), 
       EcorePlugin.FACTORY_OVERRIDE_PPID);
  }

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_FACTORY))
    {
      String packageURI = element.getAttribute(ATT_URI);
      if (packageURI == null)
      {
        logMissingAttribute(element, ATT_URI);
      }
      else if (element.getAttribute(ATT_CLASS) == null)
      {
        logMissingAttribute(element, ATT_CLASS);
      }
      else if (add)
      {
        Object ePackageDescriptor = EPackage.Registry.INSTANCE.get(packageURI);
        if (ePackageDescriptor instanceof EPackage.Descriptor)
        {
          EPackage.Registry.INSTANCE.put(packageURI, new EFactoryDescriptor(element, ATT_CLASS, (EPackage.Descriptor)ePackageDescriptor));
          if (ePackageDescriptor instanceof EFactoryDescriptor)
          {
            EFactoryDescriptor descriptor = (EFactoryDescriptor)ePackageDescriptor;
            EcorePlugin.INSTANCE.log
              ("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a factory override for '" + packageURI + "'");
          }
        }
        return true;
      }
      else
      {
        Object ePackageDescriptor = EPackage.Registry.INSTANCE.get(packageURI);
        if (ePackageDescriptor instanceof EFactoryDescriptor)
        {
          EPackage.Registry.INSTANCE.put(packageURI, ((EFactoryDescriptor)ePackageDescriptor).getOverridenDescriptor());
        }
        return true;
      }
    }

    return false;
  }
}
