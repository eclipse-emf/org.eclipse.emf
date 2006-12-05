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
 * $Id: EClassifierItemProvider.java,v 1.13 2006/12/05 20:26:31 emerks Exp $
 */
package org.eclipse.emf.ecore.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;


import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.EClassifier} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EClassifierItemProvider
  extends ENamedElementItemProvider
  implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClassifierItemProvider(AdapterFactory adapterFactory)
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

      addInstanceClassNamePropertyDescriptor(object);
      addDefaultValuePropertyDescriptor(object);
      addInstanceTypeNamePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Instance Class Name feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addInstanceClassNamePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EClassifier_instanceClassName_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_EClassifier_instanceClassName_feature", "_UI_EClassifier_type"),
         EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME,
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null)
       {
         public void setPropertyValue(Object object, Object value)
         {
           EObject eObject = (EObject)object;
           EditingDomain editingDomain = getEditingDomain(object);
           value = stripToNull((String)value);
           if (editingDomain == null)
           {
             eObject.eSet(EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME, value);
           }
           else
           {
             editingDomain.getCommandStack().execute
               (SetCommand.create(editingDomain, getCommandOwner(eObject), EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME, value));
           }
         }
       });
  }

  /**
   * This adds a property descriptor for the Default Value feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addDefaultValuePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EClassifier_defaultValue_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_EClassifier_defaultValue_feature", "_UI_EClassifier_type"),
         EcorePackage.Literals.ECLASSIFIER__DEFAULT_VALUE,
         false,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Instance Type Name feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addInstanceTypeNamePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EClassifier_instanceTypeName_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_EClassifier_instanceTypeName_feature", "_UI_EClassifier_type"),
         EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
  public Collection getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(EcorePackage.Literals.ECLASSIFIER__ETYPE_PARAMETERS);
    }
    return childrenFeatures;
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getText(Object object)
  {
    String label = ((EClassifier)object).getName();
    return label == null || label.length() == 0 ?
      getString("_UI_EClassifier_type") :
      getString("_UI_EClassifier_type") + " " + label;
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

    switch (notification.getFeatureID(EClassifier.class))
    {
      case EcorePackage.ECLASSIFIER__INSTANCE_CLASS_NAME:
      case EcorePackage.ECLASSIFIER__DEFAULT_VALUE:
      case EcorePackage.ECLASSIFIER__INSTANCE_TYPE_NAME:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case EcorePackage.ECLASSIFIER__ETYPE_PARAMETERS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
   * describing all of the children that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    EClassifier eClassifier = (EClassifier)object;
    if (!(object instanceof EEnum) && 
          eClassifier.getInstanceClassName() != null)
    {
      newChildDescriptors.add
        (createChildParameter
          (EcorePackage.Literals.ECLASSIFIER__ETYPE_PARAMETERS,
           EcoreFactory.eINSTANCE.createETypeParameter()));
    }
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return EcoreEditPlugin.INSTANCE;
  }

}
