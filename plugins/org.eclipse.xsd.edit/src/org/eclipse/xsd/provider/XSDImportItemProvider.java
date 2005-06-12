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
 * $Id: XSDImportItemProvider.java,v 1.4 2005/06/12 12:34:45 emerks Exp $
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

import org.eclipse.xsd.XSDImport;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDImport} object.
 */
public class XSDImportItemProvider
  extends XSDSchemaDirectiveItemProvider
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
  public XSDImportItemProvider(AdapterFactory adapterFactory)
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

      // This is for the namespace feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_Namespace_label"),
           XSDEditPlugin.getString("_UI_Namespace_description"),
           xsdPackage.getXSDImport_Namespace(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_Annotation_label"),
           XSDEditPlugin.getString("_UI_AnnotationOfImport_description"),
           xsdPackage.getXSDImport_Annotation(), 
           false));

    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   */
  public Collection getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(xsdPackage.getXSDImport_Annotation());
    }
    return childrenFeatures;
  }

  /**
   * This returns XSDImport.gif.
   */
  public Object getImage(Object object)
  {
    return XSDEditPlugin.getImage("full/obj16/XSDImport");
  }

  public String getText(Object object)
  {
    XSDImport xsdImport = ((XSDImport)object);
    String result = xsdImport.getSchemaLocation();
    return result == null ? "" : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  public void notifyChanged(Notification msg) 
  {
    if (
         msg.getFeature() == xsdPackage.getXSDImport_Namespace() || 
         msg.getFeature() == xsdPackage.getXSDImport_Annotation()
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
    // annotation
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDImport_Annotation(), xsdFactory.createXSDAnnotation()));
  }
}
