/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.examples.extlibrary.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.examples.extlibrary.Library} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LibraryItemProvider
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
  public LibraryItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addAddressPropertyDescriptor(object);
      addNamePropertyDescriptor(object);
      addBooksPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Address feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAddressPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Addressable_address_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Addressable_address_feature", "_UI_Addressable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         EXTLibraryPackage.Literals.ADDRESSABLE__ADDRESS,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Name feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addNamePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Library_name_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Library_name_feature", "_UI_Library_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         EXTLibraryPackage.Literals.LIBRARY__NAME,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Books feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addBooksPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_Library_books_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_Library_books_feature", "_UI_Library_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         EXTLibraryPackage.Literals.LIBRARY__BOOKS,
         false,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(EXTLibraryPackage.Literals.LIBRARY__WRITERS);
      childrenFeatures.add(EXTLibraryPackage.Literals.LIBRARY__EMPLOYEES);
      childrenFeatures.add(EXTLibraryPackage.Literals.LIBRARY__BORROWERS);
      childrenFeatures.add(EXTLibraryPackage.Literals.LIBRARY__STOCK);
      childrenFeatures.add(EXTLibraryPackage.Literals.LIBRARY__BRANCHES);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   * This returns Library.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/Library")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getText(Object object)
  {
    String label = ((Library)object).getName();
    return label == null || label.length() == 0 ?
      getString("_UI_Library_type") : //$NON-NLS-1$
      getString("_UI_Library_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(Library.class))
    {
      case EXTLibraryPackage.LIBRARY__ADDRESS:
      case EXTLibraryPackage.LIBRARY__NAME:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case EXTLibraryPackage.LIBRARY__WRITERS:
      case EXTLibraryPackage.LIBRARY__EMPLOYEES:
      case EXTLibraryPackage.LIBRARY__BORROWERS:
      case EXTLibraryPackage.LIBRARY__STOCK:
      case EXTLibraryPackage.LIBRARY__BRANCHES:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    newChildDescriptors.add
      (createChildParameter
        (EXTLibraryPackage.Literals.LIBRARY__WRITERS,
         EXTLibraryFactory.eINSTANCE.createWriter()));

    newChildDescriptors.add
      (createChildParameter
        (EXTLibraryPackage.Literals.LIBRARY__EMPLOYEES,
         EXTLibraryFactory.eINSTANCE.createEmployee()));

    newChildDescriptors.add
      (createChildParameter
        (EXTLibraryPackage.Literals.LIBRARY__BORROWERS,
         EXTLibraryFactory.eINSTANCE.createBorrower()));

    newChildDescriptors.add
      (createChildParameter
        (EXTLibraryPackage.Literals.LIBRARY__STOCK,
         EXTLibraryFactory.eINSTANCE.createBook()));

    newChildDescriptors.add
      (createChildParameter
        (EXTLibraryPackage.Literals.LIBRARY__STOCK,
         EXTLibraryFactory.eINSTANCE.createBookOnTape()));

    newChildDescriptors.add
      (createChildParameter
        (EXTLibraryPackage.Literals.LIBRARY__STOCK,
         EXTLibraryFactory.eINSTANCE.createVideoCassette()));

    newChildDescriptors.add
      (createChildParameter
        (EXTLibraryPackage.Literals.LIBRARY__BRANCHES,
         EXTLibraryFactory.eINSTANCE.createLibrary()));
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator()
  {
    return EXTLibraryEditPlugin.INSTANCE;
  }

}
