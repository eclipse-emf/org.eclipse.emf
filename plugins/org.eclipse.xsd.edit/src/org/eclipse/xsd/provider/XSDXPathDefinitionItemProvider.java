/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import org.eclipse.xsd.XSDXPathDefinition;
import org.eclipse.xsd.XSDXPathVariety;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDXPathDefinition} object.
 */
public class XSDXPathDefinitionItemProvider
  extends XSDComponentItemProvider
  implements 
    IEditingDomainItemProvider,
    IStructuredItemContentProvider, 
    ITreeItemContentProvider, 
    IItemLabelProvider, 
    IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   */
  public XSDXPathDefinitionItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      // This is for the variety feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Variety_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_VarietyOfXPath_description"),
           xsdPackage.getXSDXPathDefinition_Variety(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

    }
    return itemPropertyDescriptors;
  }

  /**
   * This returns XSDXPathDefinition.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    XSDXPathDefinition xsdXPathDefinition = ((XSDXPathDefinition)object);
    return
      XSDEditPlugin.INSTANCE.getImage
        (XSDXPathVariety.SELECTOR_LITERAL == xsdXPathDefinition.getVariety() ?
          "full/obj16/XSDXPathDefinitionSelector" :
          "full/obj16/XSDXPathDefinitionField");
  }

  @Override
  public String getText(Object object)
  {
    XSDXPathDefinition xsdXPathDefinition = ((XSDXPathDefinition)object);
    String result = xsdXPathDefinition.getValue();
    return result == null ? XSDEditPlugin.INSTANCE.getString("_UI_Absent_label") : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    if (
         msg.getFeature() == xsdPackage.getXSDXPathDefinition_Variety()
       )
    {
      fireNotifyChanged(msg);
      return;
    }
    super.notifyChanged(msg);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    // annotation
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDXPathDefinition_Annotation(), xsdFactory.createXSDAnnotation()));
  }
}
