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
 * $Id: XSDMaxLengthFacetItemProvider.java,v 1.3 2005/06/12 12:34:45 emerks Exp $
 */
package org.eclipse.xsd.provider;


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

import org.eclipse.xsd.XSDMaxLengthFacet;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDMaxLengthFacet} object.
 */
public class XSDMaxLengthFacetItemProvider
  extends XSDFixedFacetItemProvider
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
  public XSDMaxLengthFacetItemProvider(AdapterFactory adapterFactory)
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

      // This is for the value feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_Value_label"),
           XSDEditPlugin.getString("_UI_ValueMaxLengthFacet_description"),
           xsdPackage.getXSDMaxLengthFacet_Value(),
           true,
           ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE));

    }
    return itemPropertyDescriptors;
  }

  /**
   * This returns XSDMaxLengthFacet.gif.
   */
  public Object getImage(Object object)
  {
    return XSDEditPlugin.getImage("full/obj16/XSDMaxLengthFacet");
  }

  public String getText(Object object)
  {
    XSDMaxLengthFacet xsdMaxLengthFacet = ((XSDMaxLengthFacet)object);
    String result = xsdMaxLengthFacet.getLexicalValue();
    return result == null ? "" : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  public void notifyChanged(Notification msg) 
  {
    if (msg.getFeature() == xsdPackage.getXSDFacet_LexicalValue())
    {
      fireNotifyChanged(msg);
      return;
    }
    super.notifyChanged(msg);
  }

}
