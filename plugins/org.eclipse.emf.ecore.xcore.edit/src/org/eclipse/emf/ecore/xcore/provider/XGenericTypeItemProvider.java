/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.provider;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XcoreFactory;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.provider.XModelElementItemProvider.UnderlayedImage;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.xcore.XGenericType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class XGenericTypeItemProvider
  extends ItemProviderAdapter
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
  public XGenericTypeItemProvider(AdapterFactory adapterFactory)
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

      addTypePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Type feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTypePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_XGenericType_type_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_XGenericType_type_feature", "_UI_XGenericType_type"),
         XcorePackage.Literals.XGENERIC_TYPE__TYPE,
         true,
         false,
         true,
         null,
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
      childrenFeatures.add(XcorePackage.Literals.XGENERIC_TYPE__UPPER_BOUND);
      childrenFeatures.add(XcorePackage.Literals.XGENERIC_TYPE__TYPE_ARGUMENTS);
      childrenFeatures.add(XcorePackage.Literals.XGENERIC_TYPE__LOWER_BOUND);
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
   */
  @Override
  public Object getImage(Object object)
  {
    XGenericType xGenericType = (XGenericType)object;
    EReference eContainmentFeature = xGenericType.eContainmentFeature();
    return 
      overlayImage
        (object, 
         new UnderlayedImage
           (getResourceLocator().getImage
              (eContainmentFeature == XcorePackage.Literals.XCLASS__SUPER_TYPES?
                "full/obj16/EGenericSuperType" :
                eContainmentFeature == XcorePackage.Literals.XTYPED_ELEMENT__TYPE ?
                  "full/obj16/EGenericElementType" :
                  eContainmentFeature == XcorePackage.Literals.XOPERATION__EXCEPTIONS ?
                    "full/obj16/EGenericException" :
                    eContainmentFeature == XcorePackage.Literals.XGENERIC_TYPE__TYPE_ARGUMENTS ?
                      "full/obj16/EGenericTypeArgument" :
                      eContainmentFeature == XcorePackage.Literals.XGENERIC_TYPE__LOWER_BOUND ||
                      eContainmentFeature == XcorePackage.Literals.XGENERIC_TYPE__UPPER_BOUND ?
                        "full/obj16/EGenericWildcard" :
                        "full/obj16/EGenericType")));
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
    return getText((XGenericType)object);
  }

  static String getText(XGenericType xGenericType)
  {
    GenBase type = xGenericType.getType();
    if (type instanceof GenTypeParameter)
    {
      String name = ((GenTypeParameter)type).getName();
      return name == null ? "null" : name;
    }
    else
    {
      GenClassifier genClassifier = (GenClassifier)type;
      if (genClassifier != null)
      {
        List<XGenericType> typeArguments = xGenericType.getTypeArguments();
        if (typeArguments.isEmpty())
        {
          String name = genClassifier.getName();
          return name == null ? "null" : name;
        }
        else
        {
          StringBuilder result = new StringBuilder();
          result.append(genClassifier.getName());
          result.append('<');
          for (Iterator<XGenericType> i = typeArguments.iterator(); ; )
          {
            result.append(getText(i.next()));
            if (i.hasNext())
            {
              result.append(", ");
            }
            else
            {
              break;
            }
          }
          result.append('>');
          return result.toString();
        }
      }
      else
      {
        XGenericType upperBound = xGenericType.getUpperBound();
        if (upperBound != null)
        {
          return "? extends " + getText(upperBound);
        }
        else
        {
          XGenericType lowerBound = xGenericType.getLowerBound();
          if (lowerBound != null)
          {
            return "? super " + getText(lowerBound);
          }
          else
          {
            return "?";
          }
        }
      }
    }
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

    switch (notification.getFeatureID(XGenericType.class))
    {
      case XcorePackage.XGENERIC_TYPE__UPPER_BOUND:
      case XcorePackage.XGENERIC_TYPE__TYPE_ARGUMENTS:
      case XcorePackage.XGENERIC_TYPE__LOWER_BOUND:
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
        (XcorePackage.Literals.XGENERIC_TYPE__UPPER_BOUND,
         XcoreFactory.eINSTANCE.createXGenericType()));

    newChildDescriptors.add
      (createChildParameter
        (XcorePackage.Literals.XGENERIC_TYPE__TYPE_ARGUMENTS,
         XcoreFactory.eINSTANCE.createXGenericType()));

    newChildDescriptors.add
      (createChildParameter
        (XcorePackage.Literals.XGENERIC_TYPE__LOWER_BOUND,
         XcoreFactory.eINSTANCE.createXGenericType()));
  }

  /**
   * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection)
  {
    Object childFeature = feature;
    Object childObject = child;

    boolean qualify =
      childFeature == XcorePackage.Literals.XGENERIC_TYPE__UPPER_BOUND ||
      childFeature == XcorePackage.Literals.XGENERIC_TYPE__TYPE_ARGUMENTS ||
      childFeature == XcorePackage.Literals.XGENERIC_TYPE__LOWER_BOUND;

    if (qualify)
    {
      return getString
        ("_UI_CreateChild_text2",
         new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
    }
    return super.getCreateChildText(owner, feature, child, selection);
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
    return XcoreEditPlugin.INSTANCE;
  }

}
