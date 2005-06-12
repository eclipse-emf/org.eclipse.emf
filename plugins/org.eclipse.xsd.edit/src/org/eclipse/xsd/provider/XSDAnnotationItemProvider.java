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
 * $Id: XSDAnnotationItemProvider.java,v 1.3 2005/06/12 12:34:45 emerks Exp $
 */
package org.eclipse.xsd.provider;


import java.util.List;

import org.w3c.dom.Element;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
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
  public List getPropertyDescriptors(Object object)
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
  public Object getImage(Object object)
  {
    return XSDEditPlugin.getImage("full/obj16/XSDAnnotation");
  }

  public String getText(Object object)
  {
    XSDAnnotation xsdAnnotation = ((XSDAnnotation)object);
    String result = "";
    List userInformation = xsdAnnotation.getUserInformation();
    if (!userInformation.isEmpty())
    {
      Element element = (Element)userInformation.get(0);
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
            result = result.substring(0, 50) + XSDEditPlugin.getString("_UI_DotDotDot_label");
          }
        }
      }
    }

    return result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  public void notifyChanged(Notification msg) 
  {
    super.notifyChanged(msg);
  }

}
