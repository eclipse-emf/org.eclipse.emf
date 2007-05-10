/**
 * <copyright> 
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: GenModelItemProvider.java,v 1.34 2007/05/10 19:40:31 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;

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
 * This is the item provider adapter for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenModel} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenModelItemProvider
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
  public GenModelItemProvider(AdapterFactory adapterFactory)
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

      addCopyrightTextPropertyDescriptor(object);
      addModelDirectoryPropertyDescriptor(object);
      addCreationCommandsPropertyDescriptor(object);
      addCreationIconsPropertyDescriptor(object);
      addCreationSubmenusPropertyDescriptor(object);
      addEditDirectoryPropertyDescriptor(object);
      addEditorDirectoryPropertyDescriptor(object);
      addModelPluginIDPropertyDescriptor(object);
      addTemplateDirectoryPropertyDescriptor(object);
      addRuntimeJarPropertyDescriptor(object);
      addDynamicTemplatesPropertyDescriptor(object);
      addRedirectionPropertyDescriptor(object);
      addForceOverwritePropertyDescriptor(object);
      addModelNamePropertyDescriptor(object);
      addModelPluginClassPropertyDescriptor(object);
      addEditPluginClassPropertyDescriptor(object);
      addEditorPluginClassPropertyDescriptor(object);
      addUpdateClasspathPropertyDescriptor(object);
      addGenerateSchemaPropertyDescriptor(object);
      addNonNLSMarkersPropertyDescriptor(object);
      addStaticPackagesPropertyDescriptor(object);
      addModelPluginVariablesPropertyDescriptor(object);
      addRootExtendsInterfacePropertyDescriptor(object);
      addRootExtendsClassPropertyDescriptor(object);
      addRootImplementsInterfacePropertyDescriptor(object);
      addSuppressEMFTypesPropertyDescriptor(object);
      addSuppressEMFMetaDataPropertyDescriptor(object);
      addSuppressEMFModelTagsPropertyDescriptor(object);
      addSuppressInterfacesPropertyDescriptor(object);
      addFeatureMapWrapperInterfacePropertyDescriptor(object);
      addFeatureMapWrapperInternalInterfacePropertyDescriptor(object);
      addFeatureMapWrapperClassPropertyDescriptor(object);
      addRuntimeCompatibilityPropertyDescriptor(object);
      addRichClientPlatformPropertyDescriptor(object);
      addCodeFormattingPropertyDescriptor(object);
      addTestsDirectoryPropertyDescriptor(object);
      addTestSuiteClassPropertyDescriptor(object);
      addBooleanFlagsFieldPropertyDescriptor(object);
      addBooleanFlagsReservedBitsPropertyDescriptor(object);
      addBundleManifestPropertyDescriptor(object);
      addFeatureDelegationPropertyDescriptor(object);
      addContainmentProxiesPropertyDescriptor(object);
      addMinimalReflectiveMethodsPropertyDescriptor(object);
      addSuppressContainmentPropertyDescriptor(object);
      addSuppressNotificationPropertyDescriptor(object);
      addArrayAccessorsPropertyDescriptor(object);
      addSuppressUnsettablePropertyDescriptor(object);
      addFacadeHelperClassPropertyDescriptor(object);
      addComplianceLevelPropertyDescriptor(object);
      addSuppressGenModelAnnotationsPropertyDescriptor(object);
      addCopyrightFieldsPropertyDescriptor(object);
      addBinaryCompatibleReflectiveMethodsPropertyDescriptor(object);
      addPublicConstructorsPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Copyright Text feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCopyrightTextPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_copyrightText_feature"),
         getString("_UI_GenModel_copyrightText_description"),
         GenModelPackage.Literals.GEN_MODEL__COPYRIGHT_TEXT,
         true,
         true,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Model Directory feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addModelDirectoryPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_modelDirectory_feature"),
         getString("_UI_GenModel_modelDirectory_description"),
         GenModelPackage.Literals.GEN_MODEL__MODEL_DIRECTORY,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Creation Commands feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCreationCommandsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_creationCommands_feature"),
         getString("_UI_GenModel_creationCommands_description"),
         GenModelPackage.Literals.GEN_MODEL__CREATION_COMMANDS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Creation Icons feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCreationIconsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_creationIcons_feature"),
         getString("_UI_GenModel_creationIcons_description"),
         GenModelPackage.Literals.GEN_MODEL__CREATION_ICONS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Edit Directory feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEditDirectoryPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_editDirectory_feature"),
         getString("_UI_GenModel_editDirectory_description"),
         GenModelPackage.Literals.GEN_MODEL__EDIT_DIRECTORY,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Creation Submenus feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCreationSubmenusPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_creationSubmenus_feature"),
         getString("_UI_GenModel_creationSubmenus_description"),
         GenModelPackage.Literals.GEN_MODEL__CREATION_SUBMENUS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_EditorPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Editor Directory feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEditorDirectoryPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_editorDirectory_feature"),
         getString("_UI_GenModel_editorDirectory_description"),
         GenModelPackage.Literals.GEN_MODEL__EDITOR_DIRECTORY,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_EditorPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Model Plugin ID feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addModelPluginIDPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_modelPluginID_feature"),
         getString("_UI_GenModel_modelPluginID_description"),
         GenModelPackage.Literals.GEN_MODEL__MODEL_PLUGIN_ID,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Template Directory feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTemplateDirectoryPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_templateDirectory_feature"),
         getString("_UI_GenModel_templateDirectory_description"),
         GenModelPackage.Literals.GEN_MODEL__TEMPLATE_DIRECTORY,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_JETPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Runtime Jar feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addRuntimeJarPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_runtimeJar_feature"),
         getString("_UI_GenModel_runtimeJar_description"),
         GenModelPackage.Literals.GEN_MODEL__RUNTIME_JAR,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Dynamic Templates feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addDynamicTemplatesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_dynamicTemplates_feature"),
         getString("_UI_GenModel_dynamicTemplates_description"),
         GenModelPackage.Literals.GEN_MODEL__DYNAMIC_TEMPLATES,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_JETPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Redirection feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addRedirectionPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_redirection_feature"),
         getString("_UI_GenModel_redirection_description"),
         GenModelPackage.Literals.GEN_MODEL__REDIRECTION,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_JETPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Force Overwrite feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addForceOverwritePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_forceOverwrite_feature"),
         getString("_UI_GenModel_forceOverwrite_description"),
         GenModelPackage.Literals.GEN_MODEL__FORCE_OVERWRITE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_JETPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Model Name feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addModelNamePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_modelName_feature"),
         getString("_UI_GenModel_modelName_description"),
         GenModelPackage.Literals.GEN_MODEL__MODEL_NAME,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Model Plugin Class feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addModelPluginClassPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_modelPluginClass_feature"),
         getString("_UI_GenModel_modelPluginClass_description"),
         GenModelPackage.Literals.GEN_MODEL__MODEL_PLUGIN_CLASS,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Edit Plugin Class feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEditPluginClassPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_editPluginClass_feature"),
         getString("_UI_GenModel_editPluginClass_description"),
         GenModelPackage.Literals.GEN_MODEL__EDIT_PLUGIN_CLASS,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_EditPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Editor Plugin Class feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEditorPluginClassPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_editorPluginClass_feature"),
         getString("_UI_GenModel_editorPluginClass_description"),
         GenModelPackage.Literals.GEN_MODEL__EDITOR_PLUGIN_CLASS,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_EditorPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Update Classpath feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addUpdateClasspathPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_updateClasspath_feature"),
         getString("_UI_GenModel_updateClasspath_description"),
         GenModelPackage.Literals.GEN_MODEL__UPDATE_CLASSPATH,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_JETPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Generate Schema feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addGenerateSchemaPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_generateSchema_feature"),
         getString("_UI_GenModel_generateSchema_description"),
         GenModelPackage.Literals.GEN_MODEL__GENERATE_SCHEMA,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Non NLS Markers feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addNonNLSMarkersPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_nonNLSMarkers_feature"),
         getString("_UI_GenModel_nonNLSMarkers_description"),
         GenModelPackage.Literals.GEN_MODEL__NON_NLS_MARKERS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Static Packages feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addStaticPackagesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_staticPackages_feature"),
         getString("_UI_GenModel_staticPackages_description"),
         GenModelPackage.Literals.GEN_MODEL__STATIC_PACKAGES,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelClassPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Model Plugin Variables feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addModelPluginVariablesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_modelPluginVariables_feature"),
         getString("_UI_GenModel_modelPluginVariables_description"),
         GenModelPackage.Literals.GEN_MODEL__MODEL_PLUGIN_VARIABLES,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Root Extends Interface feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addRootExtendsInterfacePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_rootExtendsInterface_feature"),
         getString("_UI_GenModel_rootExtendsInterface_description"),
         GenModelPackage.Literals.GEN_MODEL__ROOT_EXTENDS_INTERFACE,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelClassPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Root Extends Class feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addRootExtendsClassPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_rootExtendsClass_feature"),
         getString("_UI_GenModel_rootExtendsClass_description"),
         GenModelPackage.Literals.GEN_MODEL__ROOT_EXTENDS_CLASS,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelClassPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Root Implements Interface feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addRootImplementsInterfacePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_rootImplementsInterface_feature"),
         getString("_UI_GenModel_rootImplementsInterface_description"),
         GenModelPackage.Literals.GEN_MODEL__ROOT_IMPLEMENTS_INTERFACE,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelClassPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppress EMF Types feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSuppressEMFTypesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_suppressEMFTypes_feature"),
         getString("_UI_GenModel_suppressEMFTypes_description"),
         GenModelPackage.Literals.GEN_MODEL__SUPPRESS_EMF_TYPES,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelFeaturePropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppress EMF Meta Data feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSuppressEMFMetaDataPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_suppressEMFMetaData_feature"),
         getString("_UI_GenModel_suppressEMFMetaData_description"),
         GenModelPackage.Literals.GEN_MODEL__SUPPRESS_EMF_META_DATA,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppress EMF Model Tags feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSuppressEMFModelTagsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_suppressEMFModelTags_feature"),
         getString("_UI_GenModel_suppressEMFModelTags_description"),
         GenModelPackage.Literals.GEN_MODEL__SUPPRESS_EMF_MODEL_TAGS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppress Interfaces feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSuppressInterfacesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_suppressInterfaces_feature"),
         getString("_UI_GenModel_suppressInterfaces_description"),
         GenModelPackage.Literals.GEN_MODEL__SUPPRESS_INTERFACES,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Feature Map Wrapper Interface feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addFeatureMapWrapperInterfacePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_featureMapWrapperInterface_feature"),
         getString("_UI_GenModel_featureMapWrapperInterface_description"),
         GenModelPackage.Literals.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelFeaturePropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Feature Map Wrapper Internal Interface feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addFeatureMapWrapperInternalInterfacePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_featureMapWrapperInternalInterface_feature"),
         getString("_UI_GenModel_featureMapWrapperInternalInterface_description"),
         GenModelPackage.Literals.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelFeaturePropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Feature Map Wrapper Class feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addFeatureMapWrapperClassPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_featureMapWrapperClass_feature"),
         getString("_UI_GenModel_featureMapWrapperClass_description"),
         GenModelPackage.Literals.GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelFeaturePropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Runtime Compatibility feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addRuntimeCompatibilityPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_runtimeCompatibility_feature"),
         getString("_UI_GenModel_runtimeCompatibility_description"),
         GenModelPackage.Literals.GEN_MODEL__RUNTIME_COMPATIBILITY,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Rich Client Platform feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addRichClientPlatformPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_richClientPlatform_feature"),
         getString("_UI_GenModel_richClientPlatform_description"),
         GenModelPackage.Literals.GEN_MODEL__RICH_CLIENT_PLATFORM,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_EditorPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Code Formatting feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCodeFormattingPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_codeFormatting_feature"),
         getString("_UI_GenModel_codeFormatting_description"),
         GenModelPackage.Literals.GEN_MODEL__CODE_FORMATTING,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_JETPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Tests Directory feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTestsDirectoryPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_testsDirectory_feature"),
         getString("_UI_GenModel_testsDirectory_description"),
         GenModelPackage.Literals.GEN_MODEL__TESTS_DIRECTORY,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_TestsPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Test Suite Class feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTestSuiteClassPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_testSuiteClass_feature"),
         getString("_UI_GenModel_testSuiteClass_description"),
         GenModelPackage.Literals.GEN_MODEL__TEST_SUITE_CLASS,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_TestsPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Boolean Flags Field feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addBooleanFlagsFieldPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_booleanFlagsField_feature"),
         getString("_UI_GenModel_booleanFlagsField_description"),
         GenModelPackage.Literals.GEN_MODEL__BOOLEAN_FLAGS_FIELD,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelFeaturePropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Boolean Flags Reserved Bits feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addBooleanFlagsReservedBitsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_booleanFlagsReservedBits_feature"),
         getString("_UI_GenModel_booleanFlagsReservedBits_description"),
         GenModelPackage.Literals.GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         getString("_UI_ModelFeaturePropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Bundle Manifest feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addBundleManifestPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_bundleManifest_feature"),
         getString("_UI_GenModel_bundleManifest_description"),
         GenModelPackage.Literals.GEN_MODEL__BUNDLE_MANIFEST,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Feature Delegation feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addFeatureDelegationPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_featureDelegation_feature"),
         getString("_UI_GenModel_featureDelegation_description"),
         GenModelPackage.Literals.GEN_MODEL__FEATURE_DELEGATION,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Containment Proxies feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addContainmentProxiesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_containmentProxies_feature"),
         getString("_UI_GenModel_containmentProxies_description"),
         GenModelPackage.Literals.GEN_MODEL__CONTAINMENT_PROXIES,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Minimal Reflective Methods feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addMinimalReflectiveMethodsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_minimalReflectiveMethods_feature"),
         getString("_UI_GenModel_minimalReflectiveMethods_description"),
         GenModelPackage.Literals.GEN_MODEL__MINIMAL_REFLECTIVE_METHODS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppress Containment feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSuppressContainmentPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_suppressContainment_feature"),
         getString("_UI_GenModel_suppressContainment_description"),
         GenModelPackage.Literals.GEN_MODEL__SUPPRESS_CONTAINMENT,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppress Notification feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSuppressNotificationPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_suppressNotification_feature"),
         getString("_UI_GenModel_suppressNotification_description"),
         GenModelPackage.Literals.GEN_MODEL__SUPPRESS_NOTIFICATION,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Array Accessors feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addArrayAccessorsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_arrayAccessors_feature"),
         getString("_UI_GenModel_arrayAccessors_description"),
         GenModelPackage.Literals.GEN_MODEL__ARRAY_ACCESSORS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppress Unsettable feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSuppressUnsettablePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_suppressUnsettable_feature"),
         getString("_UI_GenModel_suppressUnsettable_description"),
         GenModelPackage.Literals.GEN_MODEL__SUPPRESS_UNSETTABLE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelFeaturePropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Facade Helper Class feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addFacadeHelperClassPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_facadeHelperClass_feature"),
         getString("_UI_GenModel_facadeHelperClass_description"),
         GenModelPackage.Literals.GEN_MODEL__FACADE_HELPER_CLASS,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_JETPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Compliance Level feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addComplianceLevelPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_complianceLevel_feature"),
         getString("_UI_GenModel_complianceLevel_description"),
         GenModelPackage.Literals.GEN_MODEL__COMPLIANCE_LEVEL,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Suppress Gen Model Annotations feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSuppressGenModelAnnotationsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_suppressGenModelAnnotations_feature"),
         getString("_UI_GenModel_suppressGenModelAnnotations_description"),
         GenModelPackage.Literals.GEN_MODEL__SUPPRESS_GEN_MODEL_ANNOTATIONS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Copyright Fields feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCopyrightFieldsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_copyrightFields_feature"),
         getString("_UI_GenModel_copyrightFields_description"),
         GenModelPackage.Literals.GEN_MODEL__COPYRIGHT_FIELDS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_AllPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Binary Compatible Reflective Methods feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addBinaryCompatibleReflectiveMethodsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_binaryCompatibleReflectiveMethods_feature"),
         getString("_UI_GenModel_binaryCompatibleReflectiveMethods_description"),
         GenModelPackage.Literals.GEN_MODEL__BINARY_COMPATIBLE_REFLECTIVE_METHODS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Public Constructors feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPublicConstructorsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenModel_publicConstructors_feature"),
         getString("_UI_GenModel_publicConstructors_description"),
         GenModelPackage.Literals.GEN_MODEL__PUBLIC_CONSTRUCTORS,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         getString("_UI_ModelClassPropertyCategory"),
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
      childrenFeatures.add(GenModelPackage.Literals.GEN_MODEL__GEN_PACKAGES);
      childrenFeatures.add(GenModelPackage.Literals.GEN_MODEL__USED_GEN_PACKAGES);
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
   */
  @Override
  public Object getImage(Object object)
  {
    return new UnderlayedImage(getResourceLocator().getImage("full/obj16/GenModel"));
  }

  /**
   * This returns the label text for the adapted class.
   */
  @Override
  public String getText(Object object)
  {
    GenModel genModel = (GenModel)object;
    return genModel.getModelName();
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

    switch (notification.getFeatureID(GenModel.class))
    {
      case GenModelPackage.GEN_MODEL__COPYRIGHT_TEXT:
      case GenModelPackage.GEN_MODEL__MODEL_DIRECTORY:
      case GenModelPackage.GEN_MODEL__CREATION_COMMANDS:
      case GenModelPackage.GEN_MODEL__CREATION_ICONS:
      case GenModelPackage.GEN_MODEL__CREATION_SUBMENUS:
      case GenModelPackage.GEN_MODEL__EDIT_DIRECTORY:
      case GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY:
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_ID:
      case GenModelPackage.GEN_MODEL__TEMPLATE_DIRECTORY:
      case GenModelPackage.GEN_MODEL__RUNTIME_JAR:
      case GenModelPackage.GEN_MODEL__FOREIGN_MODEL:
      case GenModelPackage.GEN_MODEL__DYNAMIC_TEMPLATES:
      case GenModelPackage.GEN_MODEL__REDIRECTION:
      case GenModelPackage.GEN_MODEL__FORCE_OVERWRITE:
      case GenModelPackage.GEN_MODEL__NON_EXTERNALIZED_STRING_TAG:
      case GenModelPackage.GEN_MODEL__MODEL_NAME:
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_CLASS:
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_CLASS:
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_CLASS:
      case GenModelPackage.GEN_MODEL__UPDATE_CLASSPATH:
      case GenModelPackage.GEN_MODEL__GENERATE_SCHEMA:
      case GenModelPackage.GEN_MODEL__NON_NLS_MARKERS:
      case GenModelPackage.GEN_MODEL__STATIC_PACKAGES:
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_VARIABLES:
      case GenModelPackage.GEN_MODEL__ROOT_EXTENDS_INTERFACE:
      case GenModelPackage.GEN_MODEL__ROOT_EXTENDS_CLASS:
      case GenModelPackage.GEN_MODEL__ROOT_IMPLEMENTS_INTERFACE:
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_TYPES:
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_META_DATA:
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_MODEL_TAGS:
      case GenModelPackage.GEN_MODEL__SUPPRESS_INTERFACES:
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE:
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE:
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS:
      case GenModelPackage.GEN_MODEL__RUNTIME_COMPATIBILITY:
      case GenModelPackage.GEN_MODEL__RICH_CLIENT_PLATFORM:
      case GenModelPackage.GEN_MODEL__REFLECTIVE_DELEGATION:
      case GenModelPackage.GEN_MODEL__CODE_FORMATTING:
      case GenModelPackage.GEN_MODEL__TESTS_DIRECTORY:
      case GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS:
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_FIELD:
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS:
      case GenModelPackage.GEN_MODEL__IMPORTER_ID:
      case GenModelPackage.GEN_MODEL__BUNDLE_MANIFEST:
      case GenModelPackage.GEN_MODEL__FEATURE_DELEGATION:
      case GenModelPackage.GEN_MODEL__CONTAINMENT_PROXIES:
      case GenModelPackage.GEN_MODEL__MINIMAL_REFLECTIVE_METHODS:
      case GenModelPackage.GEN_MODEL__SUPPRESS_CONTAINMENT:
      case GenModelPackage.GEN_MODEL__SUPPRESS_NOTIFICATION:
      case GenModelPackage.GEN_MODEL__ARRAY_ACCESSORS:
      case GenModelPackage.GEN_MODEL__SUPPRESS_UNSETTABLE:
      case GenModelPackage.GEN_MODEL__FACADE_HELPER_CLASS:
      case GenModelPackage.GEN_MODEL__COMPLIANCE_LEVEL:
      case GenModelPackage.GEN_MODEL__SUPPRESS_GEN_MODEL_ANNOTATIONS:
      case GenModelPackage.GEN_MODEL__COPYRIGHT_FIELDS:
      case GenModelPackage.GEN_MODEL__BINARY_COMPATIBLE_REFLECTIVE_METHODS:
      case GenModelPackage.GEN_MODEL__PUBLIC_CONSTRUCTORS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
      case GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES:
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
  @Override
  public ResourceLocator getResourceLocator()
  {
    return GenModelEditPlugin.INSTANCE;
  }

}

