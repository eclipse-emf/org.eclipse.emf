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
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
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
    // This is for the value feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_LexicalValue_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_LexicalValueOfFeature_description"),
         xsdPackage.getXSDFeature_LexicalValue(),
         true,
         ItemPropertyDescriptor.TEXT_VALUE_IMAGE));
  }

  protected void createConstraintPropertyDescriptor(Object object)
  {
    // This is for the constraint feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_Constraint_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_ConstraintOfFeature_description"),
         xsdPackage.getXSDFeature_Constraint(),
         true,
         ItemPropertyDescriptor.TEXT_VALUE_IMAGE));
  }

  protected void createScopePropertyDescriptor(Object object)
  {
    // This is for the scope feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_Scope_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_ScopeOfFeature_description"),
         xsdPackage.getXSDFeature_Scope(), 
         false));
  }


  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
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
