/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDFeatureItemProvider.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
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

import org.eclipse.xsd.XSDFeature;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDFeature} object.
 */
public class XSDFeatureItemProvider
  extends XSDNamedComponentItemProvider
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
  public XSDFeatureItemProvider(AdapterFactory adapterFactory)
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
      createLexicalValuePropertyDescriptor(object);
      createConstraintPropertyDescriptor(object);
      createScopePropertyDescriptor(object);

    }
    return itemPropertyDescriptors;
  }

  protected void createLexicalValuePropertyDescriptor(Object object)
  {
    XSDFeature xsdFeature = ((XSDFeature)object);

    // This is for the value feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.getString("_UI_LexicalValue_label"),
         XSDEditPlugin.getString("_UI_LexicalValueOfFeature_description"),
         xsdPackage.getXSDFeature_LexicalValue(),
         true,
         ItemPropertyDescriptor.TEXT_VALUE_IMAGE));
  }

  protected void createConstraintPropertyDescriptor(Object object)
  {
    XSDFeature xsdFeature = ((XSDFeature)object);

    // This is for the constraint feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.getString("_UI_Constraint_label"),
         XSDEditPlugin.getString("_UI_ConstraintOfFeature_description"),
         xsdPackage.getXSDFeature_Constraint(),
         true,
         ItemPropertyDescriptor.TEXT_VALUE_IMAGE));
  }

  protected void createScopePropertyDescriptor(Object object)
  {
    XSDFeature xsdFeature = ((XSDFeature)object);

    // This is for the scope feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.getString("_UI_Scope_label"),
         XSDEditPlugin.getString("_UI_ScopeOfFeature_description"),
         xsdPackage.getXSDFeature_Scope(), 
         false));
  }


  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  public void notifyChanged(Notification msg) 
  {
    if (
         msg.getFeature() == xsdPackage.getXSDFeature_LexicalValue() || 
         msg.getFeature() == xsdPackage.getXSDFeature_Constraint() || 
         msg.getFeature() == xsdPackage.getXSDFeature_Scope()
       )
    {
      fireNotifyChanged(msg);
      return;
    }
    super.notifyChanged(msg);
  }
 }
