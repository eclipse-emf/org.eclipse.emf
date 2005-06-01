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
 * $Id: GenPackageItemProvider.java,v 1.13 2005/06/01 15:27:59 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;


import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenPackageItemProvider
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
  public GenPackageItemProvider(AdapterFactory adapterFactory)
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

      addPrefixPropertyDescriptor(object);
      addBasePackagePropertyDescriptor(object);
      addResourcePropertyDescriptor(object);
      addDisposableProviderFactoryPropertyDescriptor(object);
      addAdapterFactoryPropertyDescriptor(object);
      addLoadInitializationPropertyDescriptor(object);
      addInterfacePackageSuffixPropertyDescriptor(object);
      addClassPackageSuffixPropertyDescriptor(object);
      addUtilityPackageSuffixPropertyDescriptor(object);
      addProviderPackageSuffixPropertyDescriptor(object);
      addPresentationPackageSuffixPropertyDescriptor(object);
      addTestsPackageSuffixPropertyDescriptor(object);
      addGenerateExampleClassPropertyDescriptor(object);
      addEcorePackagePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Prefix feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPrefixPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_prefix_feature"),
         getString("_UI_GenPackage_prefix_description"),
         GenModelPackage.eINSTANCE.getGenPackage_Prefix(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Base Package feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addBasePackagePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_basePackage_feature"),
         getString("_UI_GenPackage_basePackage_description"),
         GenModelPackage.eINSTANCE.getGenPackage_BasePackage(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Resource feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addResourcePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_resource_feature"),
         getString("_UI_GenPackage_resource_description"),
         GenModelPackage.eINSTANCE.getGenPackage_Resource(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Disposable Provider Factory feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addDisposableProviderFactoryPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_disposableProviderFactory_feature"),
         getString("_UI_GenPackage_disposableProviderFactory_description"),
         GenModelPackage.eINSTANCE.getGenPackage_DisposableProviderFactory(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Adapter Factory feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAdapterFactoryPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_adapterFactory_feature"),
         getString("_UI_GenPackage_adapterFactory_description"),
         GenModelPackage.eINSTANCE.getGenPackage_AdapterFactory(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Load Initialization feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addLoadInitializationPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_loadInitialization_feature"),
         getString("_UI_GenPackage_loadInitialization_description"),
         GenModelPackage.eINSTANCE.getGenPackage_LoadInitialization(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Interface Package Suffix feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addInterfacePackageSuffixPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_interfacePackageSuffix_feature"),
         getString("_UI_GenPackage_interfacePackageSuffix_description"),
         GenModelPackage.eINSTANCE.getGenPackage_InterfacePackageSuffix(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_PackageSuffixesPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Class Package Suffix feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addClassPackageSuffixPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_classPackageSuffix_feature"),
         getString("_UI_GenPackage_classPackageSuffix_description"),
         GenModelPackage.eINSTANCE.getGenPackage_ClassPackageSuffix(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_PackageSuffixesPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Utility Package Suffix feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addUtilityPackageSuffixPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_utilityPackageSuffix_feature"),
         getString("_UI_GenPackage_utilityPackageSuffix_description"),
         GenModelPackage.eINSTANCE.getGenPackage_UtilityPackageSuffix(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_PackageSuffixesPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Provider Package Suffix feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addProviderPackageSuffixPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_providerPackageSuffix_feature"),
         getString("_UI_GenPackage_providerPackageSuffix_description"),
         GenModelPackage.eINSTANCE.getGenPackage_ProviderPackageSuffix(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_PackageSuffixesPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Presentation Package Suffix feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPresentationPackageSuffixPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_presentationPackageSuffix_feature"),
         getString("_UI_GenPackage_presentationPackageSuffix_description"),
         GenModelPackage.eINSTANCE.getGenPackage_PresentationPackageSuffix(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_PackageSuffixesPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Tests Package Suffix feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTestsPackageSuffixPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_testsPackageSuffix_feature"),
         getString("_UI_GenPackage_testsPackageSuffix_description"),
         GenModelPackage.eINSTANCE.getGenPackage_TestsPackageSuffix(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_PackageSuffixesPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Generate Example Class feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addGenerateExampleClassPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_generateExampleClass_feature"),
         getString("_UI_GenPackage_generateExampleClass_description"),
         GenModelPackage.eINSTANCE.getGenPackage_GenerateExampleClass(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_TestsPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Ecore Package feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEcorePackagePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenPackage_ecorePackage_feature"),
         getString("_UI_GenPackage_ecorePackage_description"),
         GenModelPackage.eINSTANCE.getGenPackage_EcorePackage(),
         false,
         null,
         getString("_UI_EcorePropertyCategory"),
         null));
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
   * This specifies how to implement {@link #getChildren} 
   * and {@link org.eclipse.emf.edit.command.AddCommand} and {@link org.eclipse.emf.edit.command.RemoveCommand} 
   * support in {@link #createCommand}.
   */
  public Collection getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(GenModelPackage.eINSTANCE.getGenPackage_NestedGenPackages());
      childrenFeatures.add(GenModelPackage.eINSTANCE.getGenPackage_GenClasses());
      childrenFeatures.add(GenModelPackage.eINSTANCE.getGenPackage_GenEnums());
      childrenFeatures.add(GenModelPackage.eINSTANCE.getGenPackage_GenDataTypes());
    }
    return childrenFeatures;
  }

  /**
   */
  public Object getImage(Object object)
  {
    GenPackage genPackage = (GenPackage)object;
    String imageName = genPackage.eContainer() instanceof GenPackage || genPackage.canGenerate() ?
      "full/obj16/EPackage" : "full/obj16/UsedGenPackage";
    return new UnderlayedImage(getResourceLocator().getImage(imageName));
  }

  /**
   * This returns the label text for the adapted class.
   */
  public String getText(Object object)
  {
    GenPackage genPackage = (GenPackage)object;
    String text = genPackage.getPrefix();
    if (text == null || text.length() == 0)
    {
      text = "[" + genPackage.getEcorePackage().getName() + "]";
    }
    return text;
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

    switch (notification.getFeatureID(GenPackage.class))
    {
      case GenModelPackage.GEN_PACKAGE__PREFIX:
      case GenModelPackage.GEN_PACKAGE__BASE_PACKAGE:
      case GenModelPackage.GEN_PACKAGE__RESOURCE:
      case GenModelPackage.GEN_PACKAGE__DISPOSABLE_PROVIDER_FACTORY:
      case GenModelPackage.GEN_PACKAGE__ADAPTER_FACTORY:
      case GenModelPackage.GEN_PACKAGE__LOAD_INITIALIZATION:
      case GenModelPackage.GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX:
      case GenModelPackage.GEN_PACKAGE__CLASS_PACKAGE_SUFFIX:
      case GenModelPackage.GEN_PACKAGE__UTILITY_PACKAGE_SUFFIX:
      case GenModelPackage.GEN_PACKAGE__PROVIDER_PACKAGE_SUFFIX:
      case GenModelPackage.GEN_PACKAGE__PRESENTATION_PACKAGE_SUFFIX:
      case GenModelPackage.GEN_PACKAGE__TESTS_PACKAGE_SUFFIX:
      case GenModelPackage.GEN_PACKAGE__GENERATE_EXAMPLE_CLASS:
      case GenModelPackage.GEN_PACKAGE__ECORE_PACKAGE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_ENUMS:
      case GenModelPackage.GEN_PACKAGE__GEN_DATA_TYPES:
      case GenModelPackage.GEN_PACKAGE__GEN_CLASSES:
      case GenModelPackage.GEN_PACKAGE__NESTED_GEN_PACKAGES:
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
