/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.codegen.ecore.xtext.ui;

import java.net.URL;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.ui.editor.hover.html.DefaultEObjectHoverProvider;

import com.google.inject.Inject;

public class GenModelHoverProvider extends DefaultEObjectHoverProvider
{
  protected static final String LEADING_PADDING = "<div style='position: relative; left: 16;'>";
  protected static final String TRAILING_PADDING = "</div><div style='visibility: hidden;'>xx</div>";

  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IQualifiedNameConverter nameConverter;

  private AdapterFactoryItemDelegator labelProvider = new AdapterFactoryItemDelegator(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));

  protected String getImageTagLink(ImageDescriptor imageDescriptor)
  {
    URL url = getURL(imageDescriptor);
    return url == null ? "" : "<div style='position: absolute; left: 0; top: 0;'><image src='" + url.toExternalForm() + "'/></div>";
  }

  @SuppressWarnings("restriction")
  protected URL getURL(ImageDescriptor descriptor)
  {
    return org.eclipse.jdt.internal.ui.JavaPlugin.getDefault().getImagesOnFSRegistry().getImageURL(descriptor);
  }

  protected String getImage(final EObject eObject)
  {
    final String[] result = new String[1];
    Display.getDefault().syncExec
      (new Runnable()
       {
         public void run()
         {
           ImageDescriptor image = ExtendedImageRegistry.INSTANCE.getImageDescriptor(labelProvider.getImage(eObject));
           result[0] = getImageTagLink(image);
         }
       });
    return result[0];
  }

  @Override
  protected String getFirstLine(EObject eObject)
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
        return image + LEADING_PADDING + "<b>" + name + "</b>" + TRAILING_PADDING;
      }
    }
    return null;
  }

  @Override
  protected boolean hasHover(EObject eObject)
  {
    return eObject instanceof GenFeature || super.hasHover(eObject);
  }
}
