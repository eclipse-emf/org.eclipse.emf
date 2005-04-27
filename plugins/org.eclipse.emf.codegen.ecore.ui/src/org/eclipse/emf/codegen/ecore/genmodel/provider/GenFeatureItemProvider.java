/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: GenFeatureItemProvider.java,v 1.10 2005/04/27 21:28:22 khussey Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import org.eclipse.emf.edit.provider.ViewerNotification;

// import org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind;


/**
 * This is the item provider adapter for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenFeatureItemProvider
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
  public GenFeatureItemProvider(AdapterFactory adapterFactory)
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

      addPropertyPropertyDescriptor(object);
      addNotifyPropertyDescriptor(object);
      addChildrenPropertyDescriptor(object);
      addCreateChildPropertyDescriptor(object);
      addPropertyCategoryPropertyDescriptor(object);
      addPropertyFilterFlagsPropertyDescriptor(object);
      addPropertyDescriptionPropertyDescriptor(object);
      addEcoreFeaturePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Property feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPropertyPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_property_feature"),
         getString("_UI_GenFeature_property_description"),
         GenModelPackage.eINSTANCE.getGenFeature_Property(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Notify feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addNotifyPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_notify_feature"),
         getString("_UI_GenFeature_notify_description"),
         GenModelPackage.eINSTANCE.getGenFeature_Notify(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Children feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addChildrenPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_children_feature"),
         getString("_UI_GenFeature_children_description"),
         GenModelPackage.eINSTANCE.getGenFeature_Children(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Create Child feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCreateChildPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_createChild_feature"),
         getString("_UI_GenFeature_createChild_description"),
         GenModelPackage.eINSTANCE.getGenFeature_CreateChild(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Property Category feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPropertyCategoryPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_propertyCategory_feature"),
         getString("_UI_GenFeature_propertyCategory_description"),
         GenModelPackage.eINSTANCE.getGenFeature_PropertyCategory(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Property Filter Flags feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPropertyFilterFlagsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_propertyFilterFlags_feature"),
         getString("_UI_GenFeature_propertyFilterFlags_description"),
         GenModelPackage.eINSTANCE.getGenFeature_PropertyFilterFlags(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Property Description feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPropertyDescriptionPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_propertyDescription_feature"),
         getString("_UI_GenFeature_propertyDescription_description"),
         GenModelPackage.eINSTANCE.getGenFeature_PropertyDescription(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Ecore Feature feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEcoreFeaturePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_ecoreFeature_feature"),
         getString("_UI_GenFeature_ecoreFeature_description"),
         GenModelPackage.eINSTANCE.getGenFeature_EcoreFeature(),
         false,
         null,
         getString("_UI_EcorePropertyCategory"),
         null));
  }

  /**
   */
  public Object getImage(Object object)
  {
    GenFeature genFeature = (GenFeature)object;
    EStructuralFeature eStructuralFeature = genFeature.getEcoreFeature();
    Object image = 
      getResourceLocator().getImage(eStructuralFeature instanceof EAttribute ? "full/obj16/EAttribute" : "full/obj16/EReference");

    String imageName = "full/obj16/EOccurrence";
    int minOccurs = eStructuralFeature.getLowerBound();
    int maxOccurs = eStructuralFeature.getUpperBound();
    if (minOccurs >= 0 && (minOccurs <= maxOccurs || maxOccurs == -1))
    {
      switch (minOccurs)
      {
        case 0:
        {
          imageName += "Zero";
          break;
        }
        case 1:
        {
          imageName += "One";
          break;
        }
        default:
        {
          imageName += "N";
          break;
        }
      }

      if (minOccurs != maxOccurs)
      {
        switch (maxOccurs)
        {
          case -1:
          {
            imageName += "ToUnbounded";
            break;
          }
          case 0:
          {
            break;
          }
          case 1:
          {
            imageName += "ToOne";
            break;
          }
          default:
          {
            imageName += minOccurs <= 1 ? "ToN" : "ToM";
            break;
          }
        }
      }
    }
    else
    {
      imageName += "NToM";
    }

    if (imageName.equals("full/obj16/EOccurrenceZeroToOne"))
    {
      return new UnderlayedImage(image);
    }
    else
    {
      return new UnderlayedImage(image, getResourceLocator().getImage(imageName));
    }
  }

  /**
   * This returns the label text for the adapted class.
   */
  public String getText(Object object)
  {
    GenFeature genFeature = (GenFeature)object;
    EStructuralFeature eStructuralFeature = genFeature.getEcoreFeature();
    StringBuffer result = new StringBuffer();
    result.append(genFeature.getName());
    if (eStructuralFeature.getEType() != null)
    {
      result.append(" : ");
      result.append(eStructuralFeature.getEType().getName());
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

    switch (notification.getFeatureID(GenFeature.class))
    {
      case GenModelPackage.GEN_FEATURE__PROPERTY:
      case GenModelPackage.GEN_FEATURE__NOTIFY:
      case GenModelPackage.GEN_FEATURE__CHILDREN:
      case GenModelPackage.GEN_FEATURE__CREATE_CHILD:
      case GenModelPackage.GEN_FEATURE__PROPERTY_CATEGORY:
      case GenModelPackage.GEN_FEATURE__PROPERTY_FILTER_FLAGS:
      case GenModelPackage.GEN_FEATURE__PROPERTY_DESCRIPTION:
      case GenModelPackage.GEN_FEATURE__ECORE_FEATURE:
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
