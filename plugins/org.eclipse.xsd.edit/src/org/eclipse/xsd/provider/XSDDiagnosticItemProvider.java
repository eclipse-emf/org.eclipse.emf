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

import org.eclipse.xsd.XSDDiagnostic;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDDiagnostic} object.
 */
public class XSDDiagnosticItemProvider
  extends XSDItemProviderAdapter
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
  public XSDDiagnosticItemProvider(AdapterFactory adapterFactory)
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

      // This is for the severity feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Severity_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_Severity_description"),
           xsdPackage.getXSDDiagnostic_Severity(),
       true,
       ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));

      // This is for the message feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Message_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_Message_description"),
           xsdPackage.getXSDDiagnostic_Message(),
       true,
       ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

      // This is for the locationURI feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_LocationURI_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_LocationURI_description"),
           xsdPackage.getXSDDiagnostic_LocationURI(),
       true,
       ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

      // This is for the line feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Line_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_Line_description"),
           xsdPackage.getXSDDiagnostic_Line(),
       true,
       ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE));

      // This is for the column feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Column_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_Column_description"),
           xsdPackage.getXSDDiagnostic_Column(),
       true,
       ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE));

      // This is for the node feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Node_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_Node_description"),
           xsdPackage.getXSDDiagnostic_Node(),
       true,
       ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));

      // This is for the annotationURI feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_AnnotationURI_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AnnotationURI_description"),
           xsdPackage.getXSDDiagnostic_AnnotationURI(),
       true,
       ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

    }
    return itemPropertyDescriptors;
  }

  /**
   * This returns XSDDiagnostic.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    return XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDDiagnostic");
  }

  @Override
  public String getText(Object object)
  {
    XSDDiagnostic xsdDiagnostic = ((XSDDiagnostic)object);
    return xsdDiagnostic.getMessage();
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    if (msg.getFeature() == xsdPackage.getXSDDiagnostic_Severity() || 
         msg.getFeature() == xsdPackage.getXSDDiagnostic_Message() || 
         msg.getFeature() == xsdPackage.getXSDDiagnostic_LocationURI() || 
         msg.getFeature() == xsdPackage.getXSDDiagnostic_Line() || 
         msg.getFeature() == xsdPackage.getXSDDiagnostic_Column() || 
         msg.getFeature() == xsdPackage.getXSDDiagnostic_Node() || 
         msg.getFeature() == xsdPackage.getXSDDiagnostic_AnnotationURI() || 
         msg.getFeature() == xsdPackage.getXSDDiagnostic_Components())
    {
      fireNotifyChanged(msg);
    }
    else
    {
      super.notifyChanged(msg);
    }
  }
}
