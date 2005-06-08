/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDXPathDefinitionItemProvider.java,v 1.2 2005/06/08 06:16:36 nickb Exp $
 */
package org.eclipse.xsd.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
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
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      XSDXPathDefinition xsdXPathDefinition = ((XSDXPathDefinition)object);

      // This is for the variety feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_Variety_label"),
           XSDEditPlugin.getString("_UI_VarietyOfXPath_description"),
           xsdPackage.getXSDXPathDefinition_Variety(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

    }
    return itemPropertyDescriptors;
  }

  /**
   * This returns XSDXPathDefinition.gif.
   */
  public Object getImage(Object object)
  {
    XSDXPathDefinition xsdXPathDefinition = ((XSDXPathDefinition)object);
    return
      XSDEditPlugin.getImage
        (XSDXPathVariety.SELECTOR_LITERAL == xsdXPathDefinition.getVariety() ?
          "full/obj16/XSDXPathDefinitionSelector" :
          "full/obj16/XSDXPathDefinitionField");
  }

  public String getText(Object object)
  {
    XSDXPathDefinition xsdXPathDefinition = ((XSDXPathDefinition)object);
    String result = xsdXPathDefinition.getValue();
    return result == null ? XSDEditPlugin.getString("_UI_Absent_label") : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
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
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s 
   * describing all of the children that can be created under this object.
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors,
                                            Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
    XSDXPathDefinition xpd = (XSDXPathDefinition) object;

    // annotation
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDXPathDefinition_Annotation(), xsdFactory.createXSDAnnotation()));
  }
}
