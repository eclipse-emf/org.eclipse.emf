/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.codegen.ecore.xtext.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.ui.editor.hover.html.DefaultEObjectHoverProvider;

import com.google.inject.Inject;

public class GenModelHoverProvider extends DefaultEObjectHoverProvider
{
  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IQualifiedNameConverter nameConverter;

  private Helper helper;

  public static class Helper
  {
    private IQualifiedNameProvider nameProvider;
    private IQualifiedNameConverter nameConverter;
    public Helper(IQualifiedNameProvider nameProvider, IQualifiedNameConverter nameConverter)
    {
      this.nameProvider = nameProvider;
      this.nameConverter = nameConverter;
    }

    public String getImage(EObject eObject)
    {
      ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
      AdapterFactoryItemDelegator labelProvider = new AdapterFactoryItemDelegator(adapterFactory);
      Object image = labelProvider.getImage(eObject);
      return toImage(image);
    }

    public String toImage(Object image)
    {
      if (image instanceof URL)
      {
        try
        {
          image = FileLocator.toFileURL((URL)image);
        }
        catch (IOException e)
        {
          return null;
        }
        return "<div style='position: absolute; left: 0; top: 0;'><img src='" + image.toString() + "'/></div>";
      }
      else if (image instanceof ComposedImage)
      {
        ImageDescriptor imageDescriptor = ExtendedImageRegistry.INSTANCE.getImageDescriptor(image);
        ImageData imageData = imageDescriptor.getImageData();
        ComposedImage.Size size = new ComposedImage.Size();
        size.width = imageData.width;
        size.height = imageData.height;
        ComposedImage composedImage = (ComposedImage)image;
        List<ComposedImage.Point> drawPoints = composedImage.getDrawPoints(size);
        List<Object> images = composedImage.getImages();
        StringBuilder result = new StringBuilder("<div style='position: absolute; left: 0; top: 0;'><div style='position: relative; left: 0; top: 0;'>");
        for (int i = 0, length = drawPoints.size(); i < length; ++i)
        {
          ComposedImage.Point point = drawPoints.get(i);
          Object component = images.get(i);
          String componentHTML = toImage(component);
          result.append("<div style='position: " + (i == 0 ? "absolute" : "relative") +"; left : " + point.x +"; top: " + point.y + ";'>");
          result.append(componentHTML);
          result.append("</div>");
        }
        result.append("</div></div><div style='width: 20px;'/>");
        return result.toString();
      }
      else
      {
        return null;
      }
    }

    public String getFirstLine(EObject eObject)
    {
      if (eObject instanceof GenBase || eObject instanceof EModelElement)
      {
        String image = getImage(eObject);
        if (image != null)
        {
          QualifiedName qualifiedName =
            eObject instanceof GenFeature ?
              nameProvider.getFullyQualifiedName(eObject.eContainer()).append(((GenFeature)eObject).getName()) :
              nameProvider.getFullyQualifiedName(eObject);
          String name = nameConverter.toString(qualifiedName);
          return compose(image, name);
        }
      }
      return null;
    }


    private static final String LEADING_PADDING = "<div style='position: relative; left: 16;'>";
    private static final String TRAILING_PADDING = "</div><div style='visibility: hidden;'>xx</div>";

    public String compose(String image, String label)
    {
       return image + LEADING_PADDING + "<b>" + label + "</b>" + TRAILING_PADDING;
    }
  }

  protected Helper getHelper()
  {
    if (helper == null)
    {
      helper = new Helper(nameProvider, nameConverter);
    }
    return helper;
  }

  protected String getImage(EObject eObject)
  {
    return getHelper().getImage(eObject);
  }

  @Override
  protected String getFirstLine(EObject eObject)
  {
    return getHelper().getFirstLine(eObject);
  }

  @Override
  protected boolean hasHover(EObject eObject)
  {
    return eObject instanceof GenFeature || super.hasHover(eObject);
  }
}
