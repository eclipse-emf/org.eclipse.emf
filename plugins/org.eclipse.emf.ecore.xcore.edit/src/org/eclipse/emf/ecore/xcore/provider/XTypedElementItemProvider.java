/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.xcore.XTypedElement;
import org.eclipse.emf.ecore.xcore.XcoreFactory;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.xcore.XTypedElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class XTypedElementItemProvider
  extends XNamedElementItemProvider
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
  public XTypedElementItemProvider(AdapterFactory adapterFactory)
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

      addUnorderedPropertyDescriptor(object);
      addUniquePropertyDescriptor(object);
      addMultiplicityPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Unordered feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addUnorderedPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_XTypedElement_unordered_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_XTypedElement_unordered_feature", "_UI_XTypedElement_type"),
         XcorePackage.Literals.XTYPED_ELEMENT__UNORDERED,
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
         getString("_UI_XTypedElement_unique_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_XTypedElement_unique_feature", "_UI_XTypedElement_type"),
         XcorePackage.Literals.XTYPED_ELEMENT__UNIQUE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Multiplicity feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addMultiplicityPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_XTypedElement_multiplicity_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_XTypedElement_multiplicity_feature", "_UI_XTypedElement_type"),
         XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY,
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
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(XcorePackage.Literals.XTYPED_ELEMENT__TYPE);
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected boolean shouldComposeCreationImage() 
  {
    return true;
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public String getText(Object object)
  {
    return super.getText(object);
  }

  public Object getComposedImage(Object object, Object imageToCompose)
  {
    XTypedElement xTypedElement = (XTypedElement)object;
    Collection<Object> images = new ArrayList<Object>();
    images.add(imageToCompose);
    String imageName = "full/obj16/EOccurrence";
    int[] multiplicity = xTypedElement.getMultiplicity();
    
    int minOccurs = 0;
    int maxOccurs = 1;
    
    if (multiplicity == null)
    {
      // optional is the default
      //
    }
    else if (multiplicity.length == 0)
    {
      maxOccurs =  EStructuralFeature.UNBOUNDED_MULTIPLICITY;
    }
    else if (multiplicity.length == 1)
    {
      if (multiplicity[0] == -3)
      {
        // [?]
        //
      }
      else if (multiplicity[0] == -2)
      {
        // [+]
        //
        minOccurs = 1;
        maxOccurs = EStructuralFeature.UNBOUNDED_MULTIPLICITY;
      }
      else if (multiplicity[0] == -1)
      {
        // [*]
        //
        maxOccurs = EStructuralFeature.UNBOUNDED_MULTIPLICITY;
      }
      else
      {
        // [n]
        //
        minOccurs = multiplicity[0];
        maxOccurs = multiplicity[0];
      }
    }
    else
    {
      minOccurs = multiplicity[0];
      maxOccurs = multiplicity[1];
    }

    if (minOccurs >= 0 && (minOccurs <= maxOccurs || maxOccurs == ETypedElement.UNBOUNDED_MULTIPLICITY || maxOccurs == ETypedElement.UNSPECIFIED_MULTIPLICITY))
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
          case ETypedElement.UNSPECIFIED_MULTIPLICITY:
          {
            imageName += "ToUnspecified";
            break;
          }
          case ETypedElement.UNBOUNDED_MULTIPLICITY:
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
  
    if (!imageName.equals("full/obj16/EOccurrenceZeroToOne"))
    {
      images.add(EcoreEditPlugin.INSTANCE.getImage(imageName));
    }
  
    final int offset =
      !imageName.endsWith("Unspecified") && object instanceof EStructuralFeature ?
        -2 : 
        -3;

    return 
      new ComposedImage(images)
      {
        @Override
        public List<ComposedImage.Point> getDrawPoints(Size size)
        {
          List<ComposedImage.Point> result = super.getDrawPoints(size);
          if (result.size() > 1)
          {
            result.get(0).y = offset;
          }
          return result;
        }
      };
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

    switch (notification.getFeatureID(XTypedElement.class))
    {
      case XcorePackage.XTYPED_ELEMENT__UNORDERED:
      case XcorePackage.XTYPED_ELEMENT__UNIQUE:
      case XcorePackage.XTYPED_ELEMENT__MULTIPLICITY:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case XcorePackage.XTYPED_ELEMENT__TYPE:
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
        (XcorePackage.Literals.XTYPED_ELEMENT__TYPE,
         XcoreFactory.eINSTANCE.createXGenericType()));
  }

}
