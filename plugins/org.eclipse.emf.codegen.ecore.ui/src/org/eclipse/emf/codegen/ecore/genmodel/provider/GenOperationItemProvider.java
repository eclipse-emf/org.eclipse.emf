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
 * $Id: GenOperationItemProvider.java,v 1.4.2.1 2005/06/08 18:27:44 nickb Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.emf.edit.provider.ViewerNotification;

// import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation} object.
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
  public List getPropertyDescriptors(Object object)
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
   * @generated NOT
   */
  protected void addEcoreOperationPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new GenItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getString("_UI_GenOperation_ecoreOperation_feature"),
         getString("_UI_GenOperation_ecoreOperation_description"),
         GenModelPackage.eINSTANCE.getGenOperation_EcoreOperation(),
         false,
         getString("_UI_EcorePropertyCategory")));
  }


  /**
   */
  public Object getImage(Object object)
  {
    return new UnderlayedImage(getResourceLocator().getImage("full/obj16/EOperation"));
  }

  /**
   * This returns the label text for the adapted class.
   */
  public String getText(Object object)
  {
    GenOperation genOperation = (GenOperation)object;
    EOperation eOperation = genOperation.getEcoreOperation();
    StringBuffer result = new StringBuffer();
    result.append(eOperation.getName());
    result.append("("); //)
    for (Iterator i = eOperation.getEParameters().iterator(); i.hasNext(); )
    {
      EParameter eParameter = (EParameter)i.next();
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
      for (Iterator i = eOperation.getEExceptions().iterator(); i.hasNext(); )
      {
        EClassifier eClassifier = (EClassifier)i.next();
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
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(GenOperation.class))
    {
      case GenModelPackage.GEN_OPERATION__ECORE_OPERATION:
      case GenModelPackage.GEN_OPERATION__GEN_PARAMETERS:
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
    return GenModelEditPlugin.INSTANCE;
  }

}
