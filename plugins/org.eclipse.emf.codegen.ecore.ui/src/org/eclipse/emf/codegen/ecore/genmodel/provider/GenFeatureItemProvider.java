/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
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
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addDocumentationPropertyDescriptor(object);
      addPropertyPropertyDescriptor(object);
      addNotifyPropertyDescriptor(object);
      addChildrenPropertyDescriptor(object);
      addCreateChildPropertyDescriptor(object);
      addPropertyCategoryPropertyDescriptor(object);
      addPropertyFilterFlagsPropertyDescriptor(object);
      addPropertyDescriptionPropertyDescriptor(object);
      addPropertyMultiLinePropertyDescriptor(object);
      addPropertySortChoicesPropertyDescriptor(object);
      addEcoreFeaturePropertyDescriptor(object);
      addSuppressedGetVisibilityPropertyDescriptor(object);
      addSuppressedSetVisibilityPropertyDescriptor(object);
      addSuppressedIsSetVisibilityPropertyDescriptor(object);
      addSuppressedUnsetVisibilityPropertyDescriptor(object);
      addGetPropertyDescriptor(object);
      addPropertyEditorFactoryPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Documentation feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  protected void addDocumentationPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenTypedElement_documentation_feature"),
         getString("_UI_GenTypedElement_documentation_description"),
         GenModelPackage.Literals.GEN_TYPED_ELEMENT__DOCUMENTATION,
         true,
         true,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
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
         GenModelPackage.Literals.GEN_FEATURE__PROPERTY,
         true,
         false,
         false,
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
         GenModelPackage.Literals.GEN_FEATURE__NOTIFY,
         true,
         false,
         false,
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
         GenModelPackage.Literals.GEN_FEATURE__CHILDREN,
         true,
         false,
         false,
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
         GenModelPackage.Literals.GEN_FEATURE__CREATE_CHILD,
         true,
         false,
         false,
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
         GenModelPackage.Literals.GEN_FEATURE__PROPERTY_CATEGORY,
         true,
         false,
         false,
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
         GenModelPackage.Literals.GEN_FEATURE__PROPERTY_FILTER_FLAGS,
         true,
         false,
         false,
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
         GenModelPackage.Literals.GEN_FEATURE__PROPERTY_DESCRIPTION,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Property Multi Line feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPropertyMultiLinePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_propertyMultiLine_feature"),
         getString("_UI_GenFeature_propertyMultiLine_description"),
         GenModelPackage.Literals.GEN_FEATURE__PROPERTY_MULTI_LINE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Property Sort Choices feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPropertySortChoicesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_propertySortChoices_feature"),
         getString("_UI_GenFeature_propertySortChoices_description"),
         GenModelPackage.Literals.GEN_FEATURE__PROPERTY_SORT_CHOICES,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
         GenModelPackage.Literals.GEN_FEATURE__ECORE_FEATURE,
         false,
         false,
         false,
         null,
         getString("_UI_EcorePropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppressed Get Visibility feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  protected void addSuppressedGetVisibilityPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_suppressedGetVisibility_feature"),
         getString("_UI_GenFeature_suppressedGetVisibility_description"),
         GenModelPackage.Literals.GEN_FEATURE__SUPPRESSED_GET_VISIBILITY,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppressed Set Visibility feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  protected void addSuppressedSetVisibilityPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_suppressedSetVisibility_feature"),
         getString("_UI_GenFeature_suppressedSetVisibility_description"),
         GenModelPackage.Literals.GEN_FEATURE__SUPPRESSED_SET_VISIBILITY,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppressed Is Set Visibility feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  protected void addSuppressedIsSetVisibilityPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_suppressedIsSetVisibility_feature"),
         getString("_UI_GenFeature_suppressedIsSetVisibility_description"),
         GenModelPackage.Literals.GEN_FEATURE__SUPPRESSED_IS_SET_VISIBILITY,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppressed Unset Visibility feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  protected void addSuppressedUnsetVisibilityPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_suppressedUnsetVisibility_feature"),
         getString("_UI_GenFeature_suppressedUnsetVisibility_description"),
         GenModelPackage.Literals.GEN_FEATURE__SUPPRESSED_UNSET_VISIBILITY,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Get feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  protected void addGetPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenFeature_get_feature"),
         getString("_UI_GenFeature_get_description"),
         GenModelPackage.Literals.GEN_FEATURE__GET,
         true,
         true,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Property Editor Factory feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated NOT
   */
  protected void addPropertyEditorFactoryPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      ((ItemPropertyDescriptor)new GenItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_GenFeature_propertyEditorFactory_feature"),
        getString("_UI_GenFeature_propertyEditorFactory_description"),
        GenModelPackage.Literals.GEN_FEATURE__PROPERTY_EDITOR_FACTORY,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        getString("_UI_EditPropertyCategory"),
        null,
        null) 
      {
        @Override
        public Collection<?> getChoiceOfValues(Object object)
        {
          GenFeature genFeature = (GenFeature)object;
          String propertyEditorFactory = genFeature.getPropertyEditorFactory();
          return GenDataTypeItemProvider.getPropertyEditorChoices(propertyEditorFactory, genFeature.getEcoreFeature());
        }

        @Override
        public boolean isChoiceArbitrary(Object object)
        {
          return true;
        }
      });
  }

  /**
   */
  @Override
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
  @Override
  public String getText(Object object)
  {
    GenFeature genFeature = (GenFeature)object;
    EStructuralFeature eStructuralFeature = genFeature.getEcoreFeature();
    if (eStructuralFeature == null)
    {
      return "";
    }

    StringBuffer result = new StringBuffer();
    result.append(genFeature.getName());
    EClassifier eType = eStructuralFeature.getEType();
    if (eType != null && eType.getName() != null)
    {
      result.append(" : ");
      result.append(eType.getName());
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

    switch (notification.getFeatureID(GenFeature.class))
    {
      case GenModelPackage.GEN_FEATURE__DOCUMENTATION:
      case GenModelPackage.GEN_FEATURE__PROPERTY:
      case GenModelPackage.GEN_FEATURE__NOTIFY:
      case GenModelPackage.GEN_FEATURE__CHILDREN:
      case GenModelPackage.GEN_FEATURE__CREATE_CHILD:
      case GenModelPackage.GEN_FEATURE__PROPERTY_CATEGORY:
      case GenModelPackage.GEN_FEATURE__PROPERTY_FILTER_FLAGS:
      case GenModelPackage.GEN_FEATURE__PROPERTY_DESCRIPTION:
      case GenModelPackage.GEN_FEATURE__PROPERTY_MULTI_LINE:
      case GenModelPackage.GEN_FEATURE__PROPERTY_SORT_CHOICES:
      case GenModelPackage.GEN_FEATURE__ECORE_FEATURE:
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_GET_VISIBILITY:
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_SET_VISIBILITY:
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_IS_SET_VISIBILITY:
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_UNSET_VISIBILITY:
      case GenModelPackage.GEN_FEATURE__GET:
      case GenModelPackage.GEN_FEATURE__PROPERTY_EDITOR_FACTORY:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
    }
    super.notifyChanged(notification);
  }

}
