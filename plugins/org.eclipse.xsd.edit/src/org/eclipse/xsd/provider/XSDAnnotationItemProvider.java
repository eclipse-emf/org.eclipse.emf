/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.provider;


import java.util.List;

import org.w3c.dom.Element;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.util.XSDConstants;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDAnnotation} object.
 */
public class XSDAnnotationItemProvider
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
  public XSDAnnotationItemProvider(AdapterFactory adapterFactory)
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
    }
    return itemPropertyDescriptors;
  }

  /**
   * This returns XSDAnnotation.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    return XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDAnnotation");
  }

  @Override
  public String getText(Object object)
  {
    XSDAnnotation xsdAnnotation = ((XSDAnnotation)object);
    String result = "";
    List<Element> userInformation = xsdAnnotation.getUserInformation();
    if (!userInformation.isEmpty())
    {
      Element element = userInformation.get(0);
      if (element.hasAttribute(XSDConstants.SOURCE_ATTRIBUTE))
      {
        result = element.getAttribute(XSDConstants.SOURCE_ATTRIBUTE);
      }
      else
      {
        org.w3c.dom.Node text = element.getFirstChild(); 
        while (text instanceof Element)
        {
          text = ((Element)text).getFirstChild();
        }
        if (text != null && text.getNodeValue() != null)
        {
          result = text.getNodeValue();
          result = result.trim();
          if (result.length() > 50)
          {
            result = result.substring(0, 50) + XSDEditPlugin.INSTANCE.getString("_UI_DotDotDot_label");
          }
        }
      }
    }

    return result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    super.notifyChanged(msg);
  }

}
