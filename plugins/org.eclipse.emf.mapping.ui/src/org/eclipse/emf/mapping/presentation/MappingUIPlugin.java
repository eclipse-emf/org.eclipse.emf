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
 * $Id: MappingUIPlugin.java,v 1.1 2004/03/06 17:31:33 marcelop Exp $
 */
package org.eclipse.emf.mapping.presentation;


import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.eclipse.emf.mapping.MappingFactory;
import org.eclipse.emf.mapping.MappingRoot;


/**
 * This is the central singleton for the emf.mapping plugin.
 */
public class MappingUIPlugin extends AbstractUIPlugin
{  
  /**
   * Get the singleton instance.
   */
  public static MappingUIPlugin getPlugin()
  {
    return instance;
  }

  /**
   * Keep track of the singleton.
   */
  protected static MappingUIPlugin instance;

  /**
   * Create the instance.
   */
  public MappingUIPlugin(IPluginDescriptor descriptor) 
  {
    super(descriptor);

    // Remember the static instance.
    //
    instance = this;
  }

  /**
   * Do initialization stuff here.
   */
  public void startup() throws CoreException 
  {
    super.startup();
  }

  /**
   * Create a new sample model.
   */
  MappingRoot createInitialModel()
  {
    MappingRoot mappingRoot = MappingFactory.eINSTANCE.createMappingRoot();
    return mappingRoot;
  }

  public String getString(String key)
  {
    return getDescriptor().getResourceBundle().getString(key);
  }

  public String getString(String key, Object s1)
  {
    return MessageFormat.format(getString(key), new Object [] { s1 });
  }

  public String getString(String key, Object s1, Object s2)
  {
    return MessageFormat.format(getString(key), new Object [] { s1, s2 });
  }

  /**
   * This gets a .gif from the icons folder.
   */
  public ImageDescriptor getImageDescriptor(String key)
  {
    try
    {
      ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(new URL(getDescriptor().getInstallURL(), "icons/" + key + ".gif"));
      return imageDescriptor;
    }
    catch (MalformedURLException exception)
    {
      System.out.println("Failed to load image for '" + key + "'");
      exception.printStackTrace();
    }

    return null;
  }

  /**
   * This get a .gif from the image registry, which caches the icons folder.
   */
  public Image getImage(String key)
  {
    ImageRegistry imageRegistry = getImageRegistry();
    Image image = imageRegistry.get(key); 
    if (image == null)
    {
      imageRegistry.put(key, getImageDescriptor(key));
      image = imageRegistry.get(key);
    }

    return image;
  }
}
