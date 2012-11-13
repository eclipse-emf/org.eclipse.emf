/**
 * Copyright (c) 2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.common.ui;


import java.io.File;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;


/**
 * A registry for mapping an image or image descriptor to a URI representing a local file system cached location for that image.
 *
 * @since 2.9
 */
public final class ImageURIRegistry
{
  /**
   * The static instance for this registry.
   */
  public static final ImageURIRegistry INSTANCE = new ImageURIRegistry();

  private static final String IMAGE_DIR = "emf-common-ui-images";

  private HashMap<ImageDescriptor, URI> descriptorToURIMap;
  private final File imageDirectory;
  private int imageCount;

  private ImageURIRegistry()
  {
    descriptorToURIMap = new HashMap<ImageDescriptor, URI>();
    imageDirectory = getTempDir();
  }

  /**
   * Returns a URI to the cached local file system instance of this given image.
   */
  public URI getImageURI(Image image)
  {
    return getImageURI(ImageDescriptor.createFromImage(image));
  }

  /**
   * Returns a URI to the cached local file system instance of this given image descriptor.
   */
  public synchronized URI getImageURI(ImageDescriptor descriptor)
  {
    if (imageDirectory == null)
      return null;

    URI uri = descriptorToURIMap.get(descriptor);
    if (uri != null)
      return uri;

    File imageFile = getNewFile();
    ImageData imageData = descriptor.getImageData();
    if (imageData == null)
    {
      return null;
    }

    ImageLoader loader = new ImageLoader();
    loader.data = new ImageData []{ imageData };
    loader.save(imageFile.getAbsolutePath(), SWT.IMAGE_PNG);

    uri = URI.createURI(imageFile.toURI().toString());
    descriptorToURIMap.put(descriptor, uri);
    return uri;
  }

  private File getTempDir()
  {
    try
    {
      File imageDirectory = CommonUIPlugin.getPlugin().getStateLocation().append(IMAGE_DIR).toFile();
      if (imageDirectory.exists())
      {
        delete(imageDirectory);
      }
      if (!imageDirectory.exists())
      {
        imageDirectory.mkdir();
      }
      if (!imageDirectory.isDirectory())
      {
        CommonUIPlugin.INSTANCE.log("Failed to create image directory " + imageDirectory.toString()); //$NON-NLS-1$
        return null;
      }
      return imageDirectory;
    }
    catch (IllegalStateException e)
    {
      return null;
    }
  }

  private void delete(File file)
  {
    if (file.isDirectory())
    {
      File[] listFiles = file.listFiles();
      for (int i = 0; i < listFiles.length; i++)
      {
        delete(listFiles[i]);
      }
    }
    file.delete();
  }

  private File getNewFile()
  {
    File file;
    do
    {
      file = new File(imageDirectory, String.valueOf(imageCount++) + ".png"); //$NON-NLS-1$
    }
    while (file.exists());
    return file;
  }

  void dispose()
  {
    if (imageDirectory != null)
    {
      delete(imageDirectory);
    }
    descriptorToURIMap = null;
  }
}
