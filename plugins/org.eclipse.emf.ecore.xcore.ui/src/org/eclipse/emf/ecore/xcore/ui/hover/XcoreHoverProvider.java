/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.hover;


import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.ui.internal.XcoreActivator;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.ComposedImage.Point;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.ui.hover.XbaseHoverProvider;

import com.google.inject.Inject;


public class XcoreHoverProvider extends XbaseHoverProvider
{
  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private XcoreMapper mapper;

  protected String getImage(EObject eObject)
  {
    if (eObject instanceof XAnnotationDirective)
    {
      return toImage(XcoreActivator.getInstance().getBundle().getEntry("icons/full/obj16/annotation_obj.gif"));
    }
    else
    {
      ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
      AdapterFactoryItemDelegator labelProvider = new AdapterFactoryItemDelegator(adapterFactory);
      Object image = labelProvider.getImage(eObject);
      return toImage(image);
    }
  }

  protected String toImage(Object image)
  {
    if (image instanceof URL)
    {
      try
      {
        image = FileLocator.resolve((URL)image);
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
      List<Point> drawPoints = composedImage.getDrawPoints(size);
      List<Object> images = composedImage.getImages();
      StringBuilder result = new StringBuilder("<div style='position: absolute; left: 0; top: 0;'><div style='position: relative; left: 0; top: 0;'>");
      for (int i = 0, length = drawPoints.size(); i < length; ++i)
      {
        Point point = drawPoints.get(i);
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

  private static final String LEADING_PADDING = "<div style='position: relative; left: 16;'>";
  private static final String TRAILING_PADDING = "</div><div style='visibility: hidden;'>xx</div>";
  
  @Override
  protected String getFirstLine(EObject eObject)
  {
    if (eObject instanceof XNamedElement)
    {
      QualifiedName qualifiedName = nameProvider.getFullyQualifiedName(eObject);
      String name = nameConverter.toString(qualifiedName);
      if (eObject instanceof XAnnotationDirective)
      {
        return getImage(eObject) + LEADING_PADDING + "<b>annotation \"" + ((XAnnotationDirective)eObject).getSourceURI() + "\" as " + name + "</b>" + TRAILING_PADDING;
      }
      else
      {
        ENamedElement eNamedElement = mapper.getEcore((XNamedElement)eObject);
        if (eNamedElement != null)
        {
          String image = getImage(eNamedElement);
          if (image != null)
          {
            return image + LEADING_PADDING + "<b>" + name + "</b>" + TRAILING_PADDING;
          }
        }
      }
      return "<b>" + name + "</b>";
    }
    else if (eObject instanceof GenBase)
    {
      String image = getImage(eObject);
      if (image != null)
      {
        QualifiedName qualifiedName =
          eObject instanceof GenFeature ?
            nameProvider.getFullyQualifiedName(eObject.eContainer()).append(((GenFeature)eObject).getName()) :
            nameProvider.getFullyQualifiedName(eObject);
        String name = nameConverter.toString(qualifiedName);
        return image + LEADING_PADDING + "<b>" + name + "</b>" + TRAILING_PADDING;
      }
    }
    return super.getFirstLine(eObject);
  }

  @Override
  protected boolean hasHover(EObject eObject)
  {
    return eObject instanceof GenFeature || super.hasHover(eObject);
  }
}
