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


import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.EPackage.Registry#INSTANCE global} package registry.
 * Clients are not expected to use this class directly.
 */
class GeneratedPackageRegistryReader extends RegistryReader
{
  static final String TAG_PACKAGE = "package";
  static final String ATT_URI = "uri";
  static final String ATT_CLASS = "class";
  static final String ATT_GEN_MODEL = "genModel";
  
  protected Map<String, URI> ePackageNsURIToGenModelLocationMap;
  
  public GeneratedPackageRegistryReader()
  {
    super
      (Platform.getExtensionRegistry(),
       EcorePlugin.getPlugin().getBundle().getSymbolicName(), 
       EcorePlugin.GENERATED_PACKAGE_PPID);
  }
  
  public GeneratedPackageRegistryReader(Map<String, URI> ePackageNsURIToGenModelLocationMap)
  {
    this();
    this.ePackageNsURIToGenModelLocationMap = ePackageNsURIToGenModelLocationMap;
  }

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_PACKAGE))
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
        Object previous = EPackage.Registry.INSTANCE.put(packageURI, new EPackageDescriptor(element, ATT_CLASS));
        if (previous instanceof PluginClassDescriptor)
        {
          PluginClassDescriptor descriptor = (PluginClassDescriptor)previous;
          EcorePlugin.INSTANCE.log
            ("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a package for '" + packageURI + "'");
        }
        
        if (ePackageNsURIToGenModelLocationMap != null)
        {
          String genModel = element.getAttribute(ATT_GEN_MODEL);
          if (genModel != null)
          {
            URI genModelURI = URI.createURI(genModel);
            if (genModelURI.isRelative())
            {
              genModelURI = URI.createPlatformPluginURI(element.getDeclaringExtension().getContributor().getName() + "/" + genModel, true);
            }
            ePackageNsURIToGenModelLocationMap.put(packageURI, genModelURI);
          }
        }
        return true;
      }
      else
      {
        EPackage.Registry.INSTANCE.remove(packageURI);
        if (ePackageNsURIToGenModelLocationMap != null)
        {
          ePackageNsURIToGenModelLocationMap.remove(packageURI);
        }
        return true;
      }
    }

    return false;
  }
}
