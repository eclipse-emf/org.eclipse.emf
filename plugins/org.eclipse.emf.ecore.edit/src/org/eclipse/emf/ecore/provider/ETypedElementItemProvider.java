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
 * $Id: ETypedElementItemProvider.java,v 1.20 2007/11/26 20:08:04 emerks Exp $
 */
package org.eclipse.emf.ecore.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
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
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.ETypedElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ETypedElementItemProvider
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
  public ETypedElementItemProvider(AdapterFactory adapterFactory)
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

      addOrderedPropertyDescriptor(object);
      addUniquePropertyDescriptor(object);
      addLowerBoundPropertyDescriptor(object);
      addUpperBoundPropertyDescriptor(object);
      addManyPropertyDescriptor(object);
      addRequiredPropertyDescriptor(object);
      addETypePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Ordered feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addOrderedPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ETypedElement_ordered_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ETypedElement_ordered_feature", "_UI_ETypedElement_type"),
         EcorePackage.Literals.ETYPED_ELEMENT__ORDERED,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Unique feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addUniquePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ETypedElement_unique_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ETypedElement_unique_feature", "_UI_ETypedElement_type"),
         EcorePackage.Literals.ETYPED_ELEMENT__UNIQUE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Lower Bound feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addLowerBoundPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ETypedElement_lowerBound_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ETypedElement_lowerBound_feature", "_UI_ETypedElement_type"),
         EcorePackage.Literals.ETYPED_ELEMENT__LOWER_BOUND,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Upper Bound feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addUpperBoundPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ETypedElement_upperBound_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ETypedElement_upperBound_feature", "_UI_ETypedElement_type"),
         EcorePackage.Literals.ETYPED_ELEMENT__UPPER_BOUND,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Many feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addManyPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ETypedElement_many_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ETypedElement_many_feature", "_UI_ETypedElement_type"),
         EcorePackage.Literals.ETYPED_ELEMENT__MANY,
         false,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Required feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addRequiredPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ETypedElement_required_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ETypedElement_required_feature", "_UI_ETypedElement_type"),
         EcorePackage.Literals.ETYPED_ELEMENT__REQUIRED,
         false,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the EType feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addETypePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ETypedElement_eType_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ETypedElement_eType_feature", "_UI_ETypedElement_type"),
         EcorePackage.eINSTANCE.getETypedElement_EType(),
         true,
         false,
         true,
         null,
         null,
         null)
       {
         @Override
        public Collection<?> getChoiceOfValues(Object object)
         {
           // Filter out types that aren't permitted.
           //
           Collection<Object> result = new ArrayList<Object>(super.getChoiceOfValues(object));

           for (Object classifier :  EcorePackage.eINSTANCE.getEClassifiers())
           {
             if (classifier instanceof EClass)
             {
               result.remove(classifier);
             }
             else if (!result.contains(classifier))
             {
               result.add(classifier);
             }
           }
           result.add(EcorePackage.Literals.EOBJECT);

           if (object instanceof EAttribute)
           {
             for (Iterator<Object> i = result.iterator(); i.hasNext(); )
             {
               if (i.next() instanceof EClass)
               {
                 i.remove();
               }
             }
           }
           else if (object instanceof EReference)
           {
             for (Iterator<Object> i = result.iterator(); i.hasNext(); )
             {
               if (i.next() instanceof EDataType)
               {
                 i.remove();
               }
             }
           }
           
           // Let them choose type parameters as well.
           //
           for (EObject eObject = (EObject)object; eObject != null; eObject = eObject.eContainer())
           {
             if (eObject instanceof EClassifier)
             {
               result.addAll(((EClassifier)eObject).getETypeParameters());
             }
             else if (eObject instanceof EOperation)
             {
               result.addAll(((EOperation)eObject).getETypeParameters());
             }
           }

           return result;
         }
         
         @Override
         public void setPropertyValue(Object object, Object value)
         {
           EditingDomain editingDomain = getEditingDomain(object);
           if (editingDomain == null)
           {
             super.setPropertyValue(object, value);
           }
           else 
           {
             
             EGenericType eGenericType = null;
             if (value instanceof EClassifier)
             {
               EClassifier eClassifier = (EClassifier)value;
               eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
               eGenericType.setEClassifier(eClassifier);
               for (int i = 0, size = eClassifier.getETypeParameters().size(); i < size; ++i)
               {
                 eGenericType.getETypeArguments().add(EcoreFactory.eINSTANCE.createEGenericType());
               }
             }
             else if (value instanceof ETypeParameter)
             {
               eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
               eGenericType.setETypeParameter((ETypeParameter)value);
             }
             editingDomain.getCommandStack().execute
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, eGenericType));
           }
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
      childrenFeatures.add(EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE);
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
    String label = ((ETypedElement)object).getName();
    return label == null || label.length() == 0 ?
      getString("_UI_ETypedElement_type") :
      getString("_UI_ETypedElement_type") + " " + label;
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

    switch (notification.getFeatureID(ETypedElement.class))
    {
      case EcorePackage.ETYPED_ELEMENT__ORDERED:
      case EcorePackage.ETYPED_ELEMENT__UNIQUE:
      case EcorePackage.ETYPED_ELEMENT__LOWER_BOUND:
      case EcorePackage.ETYPED_ELEMENT__UPPER_BOUND:
      case EcorePackage.ETYPED_ELEMENT__MANY:
      case EcorePackage.ETYPED_ELEMENT__REQUIRED:
      case EcorePackage.ETYPED_ELEMENT__ETYPE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case EcorePackage.ETYPED_ELEMENT__EGENERIC_TYPE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, true));
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
        (EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE,
         EcoreFactory.eINSTANCE.createEGenericType()));
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
    return EcoreEditPlugin.INSTANCE;
  }

}