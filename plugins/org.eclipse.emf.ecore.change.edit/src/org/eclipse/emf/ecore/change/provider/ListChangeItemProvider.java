/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: ListChangeItemProvider.java,v 1.5 2005/04/20 03:00:18 davidms Exp $
 */
package org.eclipse.emf.ecore.change.provider;


import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.ChangePackage;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.change.ListChange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ListChangeItemProvider
  extends ItemProviderAdapter
  implements
    IEditingDomainItemProvider,
    IStructuredItemContentProvider,
    ITreeItemContentProvider,
    IItemLabelProvider,
    IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ListChangeItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addKindPropertyDescriptor(object);
      addIndexPropertyDescriptor(object);
      addMoveToIndexPropertyDescriptor(object);
      addValuesPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Kind feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addKindPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ListChange_kind_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ListChange_kind_feature", "_UI_ListChange_type"),
         ChangePackage.eINSTANCE.getListChange_Kind(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
  }

  /**
   * This adds a property descriptor for the Index feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addIndexPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ListChange_index_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ListChange_index_feature", "_UI_ListChange_type"),
         ChangePackage.eINSTANCE.getListChange_Index(),
         true,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE));
  }

  /**
   * This adds a property descriptor for the Move To Index feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addMoveToIndexPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ListChange_moveToIndex_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ListChange_moveToIndex_feature", "_UI_ListChange_type"),
         ChangePackage.eINSTANCE.getListChange_MoveToIndex(),
         true,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE));
  }

  /**
   * This adds a property descriptor for the Values feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addValuesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ListChange_values_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ListChange_values_feature", "_UI_ListChange_type"),
         ChangePackage.eINSTANCE.getListChange_Values(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
  }

  /**
   * This returns ListChange.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImage(Object object)
  {
    return getResourceLocator().getImage("full/obj16/ListChange");
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getText(Object object)
  {
    ChangeKind labelValue = ((ListChange)object).getKind();
    String label = labelValue == null ? null : labelValue.toString();
    return label == null || label.length() == 0 ?
      getString("_UI_ListChange_type") :
      getString("_UI_ListChange_type") + " " + label;
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(ListChange.class))
    {
      case ChangePackage.LIST_CHANGE__KIND:
      case ChangePackage.LIST_CHANGE__INDEX:
      case ChangePackage.LIST_CHANGE__MOVE_TO_INDEX:
      case ChangePackage.LIST_CHANGE__VALUES:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return ChangeEditPlugin.INSTANCE;
  }

}
