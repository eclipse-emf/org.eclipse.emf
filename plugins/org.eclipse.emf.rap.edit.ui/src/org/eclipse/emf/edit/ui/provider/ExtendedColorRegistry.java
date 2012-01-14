/**
 * Copyright (c) 2008-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.rwt.SessionSingletonBase;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.jface.resource.DeviceResourceException;


/**
 * A color registry for converting a color description into an actual color.
 * @see IItemColorProvider
 */
public class ExtendedColorRegistry
{
  public static final ExtendedColorRegistry INSTANCE = new ExtendedColorRegistry()
    {
      public Color getColor(Color foregroundColor, Color backgroundColor, Object object)
      {
        return ((ExtendedColorRegistry)SessionSingletonBase.getInstance(ExtendedColorRegistry.class)).getColor(
          foregroundColor,
          backgroundColor,
          object);
      }
    };

  protected Display display;
  protected HashMap<Collection<?>, Color> table = new HashMap<Collection<?>, Color>(10);

  public ExtendedColorRegistry()
  {
    display = Display.getCurrent();
    hookDisplayDispose(display);
  }

  public ExtendedColorRegistry(Display display)
  {
    this.display = display;
    hookDisplayDispose(display);
  }

  public Color getColor(Color foregroundColor, Color backgroundColor, Object object)
  {
    if (object instanceof Color)
    {
      return (Color)object;
    }
    else
    {
      Collection<Object> key = new ArrayList<Object>(2);
      key.add(foregroundColor);
      key.add(backgroundColor);
      key.add(object);

      Color result = table.get(key);
      if (result == null)
      {
        if (object instanceof ColorDescriptor)
        {
          ColorDescriptor colorDescriptor = (ColorDescriptor)object;
          try
          {
            result = colorDescriptor.createColor(display);
          }
          catch (DeviceResourceException exception)
          {
            EMFEditUIPlugin.INSTANCE.log(exception);
          }
        }
        else if (object instanceof URI)
        {
          URI colorURI = (URI)object;
          if (!"color".equals(colorURI.scheme()))
          {
            throw new IllegalArgumentException("Only 'color' scheme is recognized" + colorURI);
          }

          RGB colorData;
          if ("rgb".equals(colorURI.authority()))
          {
            int red = Integer.parseInt(colorURI.segment(0));
            int green = Integer.parseInt(colorURI.segment(1));
            int blue = Integer.parseInt(colorURI.segment(2));
            colorData = new RGB(red, green, blue);
          }
          else if ("hsb".equals(colorURI.authority()))
          {
            float[] hsb = new float[3];
            for (int i = 0; i < 3; ++i)
            {
              String segment = colorURI.segment(i);
              hsb[i] =
                "".equals(segment) || "foreground".equals(segment) ?
                  foregroundColor.getRGB().getHSB()[i]:
                  "background".equals(segment) ?
                    backgroundColor.getRGB().getHSB()[i]:
                    Float.parseFloat(segment);
            }
            colorData = new RGB(hsb[0], hsb[1], hsb[2]);
          }
          else
          {
            throw new IllegalArgumentException("Only 'rgb' and 'hsb' authority are recognized" + colorURI);
          }

          try
          {
            result = ColorDescriptor.createFrom(colorData).createColor(display);
          }
          catch (DeviceResourceException exception)
          {
            EMFEditUIPlugin.INSTANCE.log(exception);
          }
        }

        if (result != null)
        {
          table.put(key, result);
        }
      }
      return result;
    }
  }

  protected void handleDisplayDispose()
  {
    for (Color color : table.values())
    {
      color.dispose();
    }
    table = null;
  }

  protected void hookDisplayDispose(Display display)
  {
    display.disposeExec
      (new Runnable()
       {
         public void run()
         {
           handleDisplayDispose();
         }
       });
  }
}
