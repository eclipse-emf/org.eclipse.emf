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
 * $Id: GenOperationItemProvider.java,v 1.13 2008/03/10 19:10:32 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
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
 * This is the item provider adapter for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenOperationItemProvider
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
  public GenOperationItemProvider(AdapterFactory adapterFactory)
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

      addEcoreOperationPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Ecore Operation feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEcoreOperationPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenOperation_ecoreOperation_feature"),
         getString("_UI_GenOperation_ecoreOperation_description"),
         GenModelPackage.Literals.GEN_OPERATION__ECORE_OPERATION,
         false,
         false,
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
    return new UnderlayedImage(getResourceLocator().getImage("full/obj16/EOperation"));
  }

  /**
   * This returns the label text for the adapted class.
   */
  @Override
  public String getText(Object object)
  {
    GenOperation genOperation = (GenOperation)object;
    EOperation eOperation = genOperation.getEcoreOperation();
    StringBuffer result = new StringBuffer();
    result.append(eOperation.getName());
    result.append("("); //)
    for (Iterator<EParameter> i = eOperation.getEParameters().iterator(); i.hasNext(); )
    {
      EParameter eParameter = i.next();
      if (eParameter.getEType() != null)
      {
        result.append(eParameter.getEType().getName());
        if (i.hasNext())
        {
          result.append(", ");
        }
      }
    }
    // (
    result.append(")");
    if (eOperation.getEType() != null)
    {
      result.append(" : ");
      result.append(eOperation.getEType().getName());
    }

    if (!eOperation.getEExceptions().isEmpty())
    {
      result.append(" throws ");
      for (Iterator<EClassifier> i = eOperation.getEExceptions().iterator(); i.hasNext(); )
      {
        EClassifier eClassifier = i.next();
        result.append(eClassifier.getName());
        if (i.hasNext())
        {
          result.append(", ");
        }
      }
    }

    return result.toString();
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

    switch (notification.getFeatureID(GenOperation.class))
    {
      case GenModelPackage.GEN_OPERATION__ECORE_OPERATION:
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
      case GenModelPackage.GEN_OPERATION__GEN_TYPE_PARAMETERS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
    }
    super.notifyChanged(notification);
  }

}
