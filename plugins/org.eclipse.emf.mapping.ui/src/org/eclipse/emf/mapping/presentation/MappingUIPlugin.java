/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.presentation;


import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.Platform;
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
  public MappingUIPlugin() 
  {
    super();

    // Remember the static instance.
    //
    instance = this;
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
    return Platform.getResourceBundle(getBundle()).getString(key);
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
      ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(new URL(getBundle().getEntry("/"), "icons/" + key + ".gif"));
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
