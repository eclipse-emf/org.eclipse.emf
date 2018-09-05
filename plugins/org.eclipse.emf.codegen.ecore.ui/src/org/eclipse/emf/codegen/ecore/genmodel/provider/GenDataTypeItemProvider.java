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
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IPropertyEditorFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.PropertyEditorFactory;
import org.eclipse.emf.edit.provider.ViewerNotification;


/**
 * This is the item provider adapter for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenDataType} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenDataTypeItemProvider extends GenBaseItemProvider
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenDataTypeItemProvider(AdapterFactory adapterFactory)
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
      addEcoreDataTypePropertyDescriptor(object);
      addCreatePropertyDescriptor(object);
      addConvertPropertyDescriptor(object);
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
         getString("_UI_GenClassifier_documentation_feature"),
         getString("_UI_GenClassifier_documentation_description"),
         GenModelPackage.Literals.GEN_CLASSIFIER__DOCUMENTATION,
         true,
         true,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Ecore Data Type feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addEcoreDataTypePropertyDescriptor(Object object)
  {
    if (object instanceof GenEnum) return;

    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenDataType_ecoreDataType_feature"),
         getString("_UI_GenDataType_ecoreDataType_description"),
         GenModelPackage.eINSTANCE.getGenDataType_EcoreDataType(),
         false,
         null,
         getString("_UI_EcorePropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Create feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  protected void addCreatePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenDataType_create_feature"),
         getString("_UI_GenDataType_create_description"),
         GenModelPackage.Literals.GEN_DATA_TYPE__CREATE,
         true,
         true,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Convert feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  protected void addConvertPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenDataType_convert_feature"),
         getString("_UI_GenDataType_convert_description"),
         GenModelPackage.Literals.GEN_DATA_TYPE__CONVERT,
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
    itemPropertyDescriptors.add(
      (ItemPropertyDescriptor)new GenItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_GenDataType_propertyEditorFactory_feature"),
        getString("_UI_GenDataType_propertyEditorFactory_description"),
        GenModelPackage.Literals.GEN_DATA_TYPE__PROPERTY_EDITOR_FACTORY,
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
            GenDataType genDataType = (GenDataType)object;
            String propertyEditorFactory = genDataType.getPropertyEditorFactory();
            return getPropertyEditorChoices(propertyEditorFactory, genDataType.getEcoreDataType());
          }

          @Override
          public boolean isChoiceArbitrary(Object object)
          {
            return true;
          }
        });
  }

  static List<String> getPropertyEditorChoices(String propertyEditorFactory, EModelElement eModelElement)
  {
    List<String> result = new UniqueEList<String>();
    result.add(propertyEditorFactory);
    Set<URI> targetPlatformFactories = IPropertyEditorFactory.Registry.INSTANCE.getTargetPlatformFactories();
    for (URI uri : targetPlatformFactories)
    {
      IPropertyEditorFactory registeredPropertyEditorFactory = IPropertyEditorFactory.Registry.INSTANCE.getPropertyEditorFactory(uri);
      if (registeredPropertyEditorFactory instanceof PropertyEditorFactory)
      {
        PropertyEditorFactory propertyEditorFactoryImplementation = (PropertyEditorFactory)registeredPropertyEditorFactory;
        Set<String> choices = propertyEditorFactoryImplementation.getChoices(eModelElement);
        for (String choice : choices)
        {
          result.add(uri + (uri.isPrefix() ? "" : "/") + choice);
        }
      }
      else
      {
        result.add(uri.toString());
      }
    }
    result.add("");
    return result;
  }

  /**
   */
  @Override
  public Object getImage(Object object)
  {
    return new UnderlayedImage(getResourceLocator().getImage("full/obj16/EDataType"));
  }

  /**
   * This returns the label text for the adapted class.
   */
  @Override
  public String getText(Object object)
  {
    GenDataType genDataType = (GenDataType)object;
    EDataType ecoreDataType = genDataType.getEcoreDataType();
    return 
      ecoreDataType == null || ecoreDataType.getName() == null ?
        "" :
        genDataType.getName() + 
          (genDataType.getQualifiedInstanceClassName() == null ? "" : " [" + genDataType.getQualifiedInstanceClassName() + "]");
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

    switch (notification.getFeatureID(GenDataType.class))
    {
      case GenModelPackage.GEN_DATA_TYPE__GEN_PACKAGE:
      case GenModelPackage.GEN_DATA_TYPE__GEN_TYPE_PARAMETERS:
      case GenModelPackage.GEN_DATA_TYPE__DOCUMENTATION:
      case GenModelPackage.GEN_DATA_TYPE__ECORE_DATA_TYPE:
      case GenModelPackage.GEN_DATA_TYPE__CREATE:
      case GenModelPackage.GEN_DATA_TYPE__CONVERT:
      case GenModelPackage.GEN_DATA_TYPE__PROPERTY_EDITOR_FACTORY:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
    }
    super.notifyChanged(notification);
  }

}
