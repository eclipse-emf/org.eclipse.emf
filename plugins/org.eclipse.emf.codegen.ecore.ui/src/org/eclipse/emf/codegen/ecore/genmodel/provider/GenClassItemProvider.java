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
 * $Id: GenClassItemProvider.java,v 1.5 2004/05/16 17:20:25 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import org.eclipse.emf.edit.provider.ViewerNotification;

// import org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenClass} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenClassItemProvider
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
  public GenClassItemProvider(AdapterFactory adapterFactory)
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

      addProviderPropertyDescriptor(object);
      addImagePropertyDescriptor(object);
      addEcoreClassPropertyDescriptor(object);
      addLabelFeaturePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Provider feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addProviderPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new GenItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getString("_UI_GenClass_provider_feature"),
         getString("_UI_GenClass_provider_description"),
         GenModelPackage.eINSTANCE.getGenClass_Provider(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory")));
  }

  /**
   * This adds a property descriptor for the Image feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addImagePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new GenItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getString("_UI_GenClass_image_feature"),
         getString("_UI_GenClass_image_description"),
         GenModelPackage.eINSTANCE.getGenClass_Image(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory")));
  }

  /**
   * This adds a property descriptor for the Ecore Class feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addEcoreClassPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new GenItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getString("_UI_GenClass_ecoreClass_feature"),
         getString("_UI_GenClass_ecoreClass_description"),
         GenModelPackage.eINSTANCE.getGenClass_EcoreClass(),
         false,
         getString("_UI_EcorePropertyCategory")));
  }

  /**
   * This adds a property descriptor for the Label Feature feature.
   */
  protected void addLabelFeaturePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new GenItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getString("_UI_GenClass_labelFeature_feature"),
         getString("_UI_GenClass_labelFeature_description"),
         GenModelPackage.eINSTANCE.getGenClass_LabelFeature(),
         true,
         getString("_UI_EditPropertyCategory"))
       {
         protected Collection getComboBoxObjects(Object object)
         {
           GenClass genClass = (GenClass)object;
           return genClass.getLabelFeatureCandidates();
         }
       });
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
      childrenFeatures.add(GenModelPackage.eINSTANCE.getGenClass_GenFeatures());
      childrenFeatures.add(GenModelPackage.eINSTANCE.getGenClass_GenOperations());
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   */
  public Object getImage(Object object)
  {
    return new UnderlayedImage(getResourceLocator().getImage("full/obj16/EClass"));
  }

  /**
   * This returns the label text for the adapted class.
   */
  public String getText(Object object)
  {
    GenClass genClass = (GenClass)object;
    EClass eClass = (EClass)genClass.getEcoreClass();
    StringBuffer result = new StringBuffer();
    if (eClass.getName() != null)
    {
      result.append(eClass.getName());
    }
    if (!eClass.getESuperTypes().isEmpty())
    {
      result.append(" -> ");
      for (Iterator i = eClass.getESuperTypes().iterator(); i.hasNext(); )
      {
        EClass eSuperType = (EClass)i.next();
        result.append(eSuperType.getName());
        if (i.hasNext())
        {
          result.append(", ");
        }
      }
    }
    if (eClass.getInstanceClassName() != null)
    {
      result.append(" <");
      result.append(eClass.getInstanceClassName());
      result.append(">");
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

    switch (notification.getFeatureID(GenClass.class))
    {
      case GenModelPackage.GEN_CLASS__GEN_PACKAGE:
      case GenModelPackage.GEN_CLASS__PROVIDER:
      case GenModelPackage.GEN_CLASS__IMAGE:
      case GenModelPackage.GEN_CLASS__ECORE_CLASS:
      case GenModelPackage.GEN_CLASS__LABEL_FEATURE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
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
    return GenModelEditPlugin.INSTANCE;
  }

}
