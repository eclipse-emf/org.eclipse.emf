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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import org.eclipse.xsd.XSDNotationDeclaration;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDNotationDeclaration} object.
 */
public class XSDNotationDeclarationItemProvider
  extends XSDNamedComponentItemProvider
{
  /**
   * This constructs an instance from a factory and a notifier.
   */
  public XSDNotationDeclarationItemProvider(AdapterFactory adapterFactory)
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

      // This is for the systemIdentifier feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_SystemIdentifier_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_SystemIdentifier_description"),
           xsdPackage.getXSDNotationDeclaration_SystemIdentifier(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

      // This is for the publicIdentifier feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_PublicIdentifier_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_PublicIdentifier_description"),
           xsdPackage.getXSDNotationDeclaration_PublicIdentifier(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Annotation_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AnnotationOfNotation_description"),
           xsdPackage.getXSDNotationDeclaration_Annotation(), 
           false));

    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(xsdPackage.getXSDNotationDeclaration_Annotation());
    }
    return childrenFeatures;
  }

  /**
   * This returns XSDNotationDeclaration.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    return XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDNotationDeclaration");
  }

  @Override
  public String getText(Object object)
  {
    XSDNotationDeclaration xsdNotationDeclaration = ((XSDNotationDeclaration)object);
    String result = xsdNotationDeclaration.getName();
    return result == null ? "" : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    if (msg.getFeature() == xsdPackage.getXSDNotationDeclaration_SystemIdentifier() || 
         msg.getFeature() == xsdPackage.getXSDNotationDeclaration_PublicIdentifier() || 
         msg.getFeature() == xsdPackage.getXSDNotationDeclaration_Annotation())
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
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDNotationDeclaration_Annotation(), xsdFactory.createXSDAnnotation()));
  }
}
