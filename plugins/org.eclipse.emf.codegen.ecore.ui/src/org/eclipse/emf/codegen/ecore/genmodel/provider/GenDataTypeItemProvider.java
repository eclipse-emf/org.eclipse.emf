/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: GenDataTypeItemProvider.java,v 1.10 2007/06/11 21:10:23 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.emf.edit.provider.ViewerNotification;

// import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;


/**
 * This is the item provider adapter for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenDataType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenDataTypeItemProvider
  extends GenBaseItemProvider
  implements 
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenDataTypeItemProvider(AdapterFactory adapterFactory)
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

      addEcoreDataTypePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Ecore Data Type feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addEcoreDataTypePropertyDescriptor(Object object)
  {
    if (object instanceof GenEnum) return;

    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenDataType_ecoreDataType_feature"),
         getString("_UI_GenDataType_ecoreDataType_description"),
         GenModelPackage.eINSTANCE.getGenDataType_EcoreDataType(),
         false,
         null,
         getString("_UI_EcorePropertyCategory"),
         null));
  }

  /**
   */
  @Override
  public Object getImage(Object object)
  {
    return new UnderlayedImage(getResourceLocator().getImage("full/obj16/EDataType"));
  }

  /**
   * This returns the label text for the adapted class.
   */
  @Override
  public String getText(Object object)
  {
    GenDataType genDataType = (GenDataType)object;
    return 
      genDataType.getName() + 
        (genDataType.getQualifiedInstanceClassName() == null ? "" : " [" + genDataType.getQualifiedInstanceClassName() + "]");
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

    switch (notification.getFeatureID(GenDataType.class))
    {
      case GenModelPackage.GEN_DATA_TYPE__GEN_PACKAGE:
      case GenModelPackage.GEN_DATA_TYPE__GEN_TYPE_PARAMETERS:
      case GenModelPackage.GEN_DATA_TYPE__ECORE_DATA_TYPE:
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
  @Override
  public ResourceLocator getResourceLocator()
  {
    return GenModelEditPlugin.INSTANCE;
  }

}
