/**
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.databinding;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;


/**
 * Converter which able to convert base64 encoded values into SWT-images. 
 * The convert remembers the last created image and disposes when a new one is allocated but the
 * last image set on the control has to be disposed by the control itself
 */
public class Base64ToImageConverter extends Converter
{
  private Image lastImage;
  private Display display;

  /**
   * Create a new converter
   * @param display the display the images are created on
   */
  public Base64ToImageConverter(Display display)
  {
    super(String.class, Image.class);
    this.display = display;
  }

  public Object convert(Object fromObject)
  {
    if (lastImage != null && !lastImage.isDisposed())
    {
      lastImage.dispose();
      lastImage = null;
    }

    if (fromObject != null)
    {
      ByteArrayInputStream in = new ByteArrayInputStream(XMLTypeFactory.eINSTANCE.createBase64Binary(fromObject.toString()));
      Image img = new Image(display, in);

      try
      {
        in.close();
      }
      catch (IOException e)
      {

      }

      lastImage = img;

      return img;
    }

    return null;
  }
}
