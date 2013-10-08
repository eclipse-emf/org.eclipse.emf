/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.outline;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.BackgroundOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.xbase.XBlockExpression;


/**
 * Customization of the background outline structure.
 * 
 */
public class XcoreOutlineTreeProvider extends BackgroundOutlineTreeProvider
{
  protected ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
  
  @Override
  protected Object getText(Object modelElement)
  {
    IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(modelElement, IItemLabelProvider.class);
    return itemLabelProvider != null ? itemLabelProvider.getText(modelElement) : super.getText(modelElement);
  }
  
  @Override
  protected ImageDescriptor getImageDescriptor(Object modelElement)
  {
    IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(modelElement, IItemLabelProvider.class);
    return 
      itemLabelProvider != null ?
        ExtendedImageRegistry.INSTANCE.getImageDescriptor(itemLabelProvider.getImage(modelElement)) :
        super.getImageDescriptor(modelElement);
  }
  
  @Override
  protected EObjectNode createNode(IOutlineNode parentNode, EObject modelElement)
  {
    return isExcluded(modelElement) ? null : super.createNode(parentNode, modelElement);
  }

  protected boolean isExcluded(EObject modelElement)
  {
    return modelElement instanceof XGenericType || modelElement instanceof JvmTypeReference || modelElement instanceof XBlockExpression;
  }

  @Override
  protected boolean isLeaf(EObject modelElement)
  {
    for (EObject child : modelElement.eContents())
    {
      if (!isExcluded(child))
      {
        return false;
      }
    }
    return true;
  }
}
