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
 * $Id: ComposedImage.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * This implements a wrapper that can be used to specify how a composed image should look.
 */
public class ComposedImage 
{
  public static class Point
  {
    public int x;
    public int y;
  }

  public static class Size
  {
    public int width;
    public int height;
  }

  protected List images;
  protected List imageSizes;

  /**
   * This creates an empty instance.
   */
  public ComposedImage(Collection images)
  {
    this.images = new ArrayList(images);
  }

  public boolean equals(Object that)
  {
    return that instanceof ComposedImage && ((ComposedImage)that).getImages().equals(images);
  }

  public int hashCode()
  {
    return images.hashCode();
  }

  public List getImages()
  {
    return images;
  }

  public Size getSize(Collection imageSizes)
  {
    this.imageSizes = new ArrayList(imageSizes);
    Size result = new Size();
    for (Iterator sizes = imageSizes.iterator(); sizes.hasNext(); )
    {
      Size size = (Size)sizes.next();
      result.width = Math.max(result.width, size.width);
      result.height = Math.max(result.height, size.height);
    }
    return result;
  }

  public List getDrawPoints(Size size)
  {
    List results = new ArrayList();
    for (int i = imageSizes.size(); i > 0; --i)
    {
      Point result = new Point();
      results.add(result);
    }
    return results;
  }
}
