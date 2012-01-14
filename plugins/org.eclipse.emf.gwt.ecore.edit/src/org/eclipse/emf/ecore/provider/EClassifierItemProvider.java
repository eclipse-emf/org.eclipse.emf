/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
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
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
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
         getString("_UI_EClassifier_instanceClassName_description"),
         EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME,
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         new String[] 
         {
           "org.eclipse.ui.views.properties.expert"
         })
       {
         @Override
         public void setPropertyValue(Object object, Object value)
         {
           EObject eObject = (EObject)object;
           EditingDomain editingDomain = getEditingDomain(object);
           value = normalizeInstanceTypeName(stripToNull((String)value));
           if (editingDomain == null)
           {
             eObject.eSet(EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME, value);
           }
           else
           {
             // Set the instance type name instead, since that will also set the instance class name 
             // but this way will make undo restore the current instance type name correctly
             //
             editingDomain.getCommandStack().execute
               (SetCommand.create(editingDomain, getCommandOwner(eObject), EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME, value));
           }
         }
       });
  }

  protected String normalizeInstanceTypeName(String value)
  {
    if (value != null)
    {
      Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(value);
      if (diagnostic.getSeverity() == Diagnostic.OK)
      {
        value = EcoreUtil.toJavaInstanceTypeName(((EGenericType)diagnostic.getData().get(0)));
      }
    }
    return value;
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
         getString("_UI_EClassifier_defaultValue_description"),
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
   * @generated NOT
   */
  protected void addInstanceTypeNamePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EClassifier_instanceTypeName_feature"),
         getString("_UI_EClassifier_instanceTypeName_description"),
         EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null)
       {
         @Override
         public void setPropertyValue(Object object, Object value)
         {
           super.setPropertyValue(object, normalizeInstanceTypeName(stripToNull((String)value)));
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
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(EcorePackage.Literals.ECLASSIFIER__ETYPE_PARAMETERS);
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
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
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
  @Override
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
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    EClassifier eClassifier = (EClassifier)object;
    if (!(object instanceof EEnum) && 
          eClassifier.getInstanceClassName() == eClassifier.getInstanceTypeName() && 
          !"org.eclipse.emf.common.util.Enumerator".equals(eClassifier.getInstanceClassName()))
    {
      newChildDescriptors.add
        (createChildParameter
          (EcorePackage.Literals.ECLASSIFIER__ETYPE_PARAMETERS,
           EcoreFactory.eINSTANCE.createETypeParameter()));
    }
  }

}
