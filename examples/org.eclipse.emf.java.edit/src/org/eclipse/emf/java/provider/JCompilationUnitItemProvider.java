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
 * $Id: JCompilationUnitItemProvider.java,v 1.3 2004/06/08 18:25:28 emerks Exp $
 */
package org.eclipse.emf.java.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.java.JCompilationUnit;
import org.eclipse.emf.java.JavaPackage;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.java.JCompilationUnit} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JCompilationUnitItemProvider
  extends JModelElementItemProvider
  implements 
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JCompilationUnitItemProvider(AdapterFactory adapterFactory)
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

      addImportsPropertyDescriptor(object);
      addCommentPropertyDescriptor(object);
      addTypesPropertyDescriptor(object);
      addImportedPackagesPropertyDescriptor(object);
      addImportedTypesPropertyDescriptor(object);
      addPackagePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Imports feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addImportsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JCompilationUnit_imports_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JCompilationUnit_imports_feature", "_UI_JCompilationUnit_type"),
         JavaPackage.eINSTANCE.getJCompilationUnit_Imports(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
  }

  /**
   * This adds a property descriptor for the Package feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPackagePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JCompilationUnit_package_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JCompilationUnit_package_feature", "_UI_JCompilationUnit_type"),
         JavaPackage.eINSTANCE.getJCompilationUnit_Package(),
         true));
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Collection getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(JavaPackage.eINSTANCE.getJCompilationUnit_Types());
    }
    return childrenFeatures;
  }

  /**
   * This adds a property descriptor for the Comment feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCommentPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JCompilationUnit_comment_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JCompilationUnit_comment_feature", "_UI_JCompilationUnit_type"),
         JavaPackage.eINSTANCE.getJCompilationUnit_Comment(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
  }

  /**
   * This adds a property descriptor for the Types feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTypesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JCompilationUnit_types_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JCompilationUnit_types_feature", "_UI_JCompilationUnit_type"),
         JavaPackage.eINSTANCE.getJCompilationUnit_Types(),
         true));
  }

  /**
   * This adds a property descriptor for the Imported Packages feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addImportedPackagesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JCompilationUnit_importedPackages_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JCompilationUnit_importedPackages_feature", "_UI_JCompilationUnit_type"),
         JavaPackage.eINSTANCE.getJCompilationUnit_ImportedPackages(),
         true));
  }

  /**
   * This adds a property descriptor for the Imported Types feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addImportedTypesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JCompilationUnit_importedTypes_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JCompilationUnit_importedTypes_feature", "_UI_JCompilationUnit_type"),
         JavaPackage.eINSTANCE.getJCompilationUnit_ImportedTypes(),
         true));
  }

  /**
   * This returns JCompilationUnit.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImage(Object object)
  {
    return getResourceLocator().getImage("full/obj16/JCompilationUnit");
  }

  /**
   * This returns the label text for the adapted class.
   * @generated EATM
   */
  public String getText(Object object)
  {
    JCompilationUnit jCompilationUnit = (JCompilationUnit)object;
    return jCompilationUnit.getName();
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

    switch (notification.getFeatureID(JCompilationUnit.class))
    {
      case JavaPackage.JCOMPILATION_UNIT__IMPORTS:
      case JavaPackage.JCOMPILATION_UNIT__COMMENT:
      case JavaPackage.JCOMPILATION_UNIT__PACKAGE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case JavaPackage.JCOMPILATION_UNIT__TYPES:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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
    return JavaEditPlugin.INSTANCE;
  }

}
