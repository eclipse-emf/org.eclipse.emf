/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GeneratedPackageRegistryReader.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.ecore.plugin;


import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

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

  public GeneratedPackageRegistryReader()
  {
    super
      (Platform.getPluginRegistry(),
       EcorePlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 
       EcorePlugin.GENERATED_PACKAGE_PPID);
  }

  protected boolean readElement(IConfigurationElement element)
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
      else
      {
        EPackage.Registry.INSTANCE.put(packageURI, new EPackageDescriptor(element, ATT_CLASS));
        return true;
      }
    }

    return false;
  }
}
