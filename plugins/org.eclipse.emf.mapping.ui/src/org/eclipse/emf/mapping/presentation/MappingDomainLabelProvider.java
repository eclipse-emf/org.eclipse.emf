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
package org.eclipse.emf.mapping.presentation;


import java.util.Collection;
import java.util.HashMap;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.domain.AdapterFactoryMappingDomain;
import org.eclipse.emf.mapping.provider.MappedObjectItemProvider;


public class MappingDomainLabelProvider extends AdapterFactoryLabelProvider
{
  static class Key
  {
    public Image mainImage;
    public Image overlayImage;
    public Key (Image mainImage, Image overlayImage)
    {
      this.mainImage = mainImage;
      this.overlayImage = overlayImage;
    }

    @Override
    public int hashCode()
    {
      return mainImage.hashCode() + overlayImage.hashCode();
    }

    @Override
    public boolean equals(Object object)
    {
      if (object instanceof Key)
      {
        Key key = (Key)object;
        return key.mainImage == mainImage && key.overlayImage == overlayImage;
      }
      else
      {
        return false;
      }
    }
  }

  protected static HashMap<Key, Object> images = new HashMap<Key, Object>();

  protected AdapterFactoryMappingDomain mappingDomain;

  public MappingDomainLabelProvider(AdapterFactoryMappingDomain mappingDomain)
  {
    super(mappingDomain.getAdapterFactory());
    this.mappingDomain = mappingDomain;
  }

  class MappingIndicatorCompositeImage extends CompositeImageDescriptor
  {
    protected ImageData mainImage;
    protected ImageData overlayImage;
    public MappingIndicatorCompositeImage(ImageData mainImage, ImageData overlayImage)
    {
      this.mainImage = mainImage;
      this.overlayImage = overlayImage;
    }

    @Override
    public void drawCompositeImage(int width, int height)
    {
      drawImage(mainImage, 0, 0);
      drawImage(overlayImage, mainImage.width, 0);
    }

    @Override
    public Point getSize()
    {
      return new Point(mainImage.width + overlayImage.width, Math.max(mainImage.height, overlayImage.height));
    }

    @Override
    public int hashCode()
    {
      return mainImage.hashCode() + overlayImage.hashCode();
    }

    @Override
    public boolean equals(Object object)
    {
      if (object instanceof MappingIndicatorCompositeImage)
      {
        MappingIndicatorCompositeImage that = (MappingIndicatorCompositeImage)object;
        return that.mainImage == mainImage && that.overlayImage == overlayImage;
      }
      else
      {
        return false;
      }
    }
  }

  @Override
  public Image getColumnImage(Object object, int columnIndex)
  {
    return getImage(object);
  }

  @Override
  public Image getImage(Object object)
  {
    Image result = super.getImage(object);

    if (result != null)
    {
      Object overlayImage;

      Object mappedObject = 
        object instanceof MappedObjectItemProvider ?
          ((MappedObjectItemProvider)object).getMappedObject() :
          object;

      Collection<? extends Mapping> mappings = mappingDomain.getMappingRoot().getMappings(mappedObject);

      if (mappingDomain.getMappingRoot().isTopObject(mappedObject))
      {
        overlayImage =
          MappingPlugin.getPlugin().getImage(mappings.isEmpty() ? "full/ovr16/OverlayBlank" : "full/ovr16/OverlayMappedTop");
      }
      else if (mappingDomain.getMappingRoot().isBottomObject(mappedObject))
      {
        overlayImage =
          MappingPlugin.getPlugin().getImage(mappings.isEmpty() ? "full/ovr16/OverlayBlank" : "full/ovr16/OverlayMappedBottom");
      }
      else
      {
        overlayImage= MappingPlugin.getPlugin().getImage("full/ovr16/OverlayBlank");
      }

      Image image = getImageFromObject(overlayImage);
      Key key = new Key(result, image);
      Image cachedImage = (Image)images.get(key);
      if (cachedImage == null)
      {
        result = new MappingIndicatorCompositeImage(result.getImageData(), image.getImageData()).createImage();
        images.put(key, result);
      }
      else
      {
        result = cachedImage;
      }
    }

    return result;
  }
}
