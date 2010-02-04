/**
 * <copyright> 
 *
 * Copyright (c) 2002-2009 IBM Corporation and others.
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
 * $Id: GenModel.java,v 1.66 2010/02/04 20:56:54 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;

import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.jdt.core.formatter.CodeFormatter;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GenModel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getCopyrightText <em>Copyright Text</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelDirectory <em>Model Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationCommands <em>Creation Commands</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationIcons <em>Creation Icons</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationSubmenus <em>Creation Submenus</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditDirectory <em>Edit Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorDirectory <em>Editor Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginID <em>Model Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTemplateDirectory <em>Template Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeJar <em>Runtime Jar</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getForeignModel <em>Foreign Model</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isDynamicTemplates <em>Dynamic Templates</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRedirection <em>Redirection</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isForceOverwrite <em>Force Overwrite</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getNonExternalizedStringTag <em>Non Externalized String Tag</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelName <em>Model Name</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginClass <em>Model Plugin Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginClass <em>Edit Plugin Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginClass <em>Editor Plugin Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isUpdateClasspath <em>Update Classpath</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isGenerateSchema <em>Generate Schema</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isNonNLSMarkers <em>Non NLS Markers</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getStaticPackages <em>Static Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginVariables <em>Model Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsInterface <em>Root Extends Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsClass <em>Root Extends Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootImplementsInterface <em>Root Implements Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFTypes <em>Suppress EMF Types</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFMetaData <em>Suppress EMF Meta Data</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFModelTags <em>Suppress EMF Model Tags</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressInterfaces <em>Suppress Interfaces</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInterface <em>Feature Map Wrapper Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInternalInterface <em>Feature Map Wrapper Internal Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperClass <em>Feature Map Wrapper Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeCompatibility <em>Runtime Compatibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRichClientPlatform <em>Rich Client Platform</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isReflectiveDelegation <em>Reflective Delegation</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCodeFormatting <em>Code Formatting</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsDirectory <em>Tests Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestSuiteClass <em>Test Suite Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getBooleanFlagsField <em>Boolean Flags Field</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getBooleanFlagsReservedBits <em>Boolean Flags Reserved Bits</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getImporterID <em>Importer ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isBundleManifest <em>Bundle Manifest</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureDelegation <em>Feature Delegation</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isContainmentProxies <em>Containment Proxies</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isMinimalReflectiveMethods <em>Minimal Reflective Methods</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressContainment <em>Suppress Containment</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressNotification <em>Suppress Notification</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isArrayAccessors <em>Array Accessors</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressUnsettable <em>Suppress Unsettable</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFacadeHelperClass <em>Facade Helper Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getComplianceLevel <em>Compliance Level</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressGenModelAnnotations <em>Suppress Gen Model Annotations</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCopyrightFields <em>Copyright Fields</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isBinaryCompatibleReflectiveMethods <em>Binary Compatible Reflective Methods</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isPublicConstructors <em>Public Constructors</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTemplatePluginVariables <em>Template Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getProviderRootExtendsClass <em>Provider Root Extends Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginID <em>Edit Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginVariables <em>Edit Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginID <em>Editor Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginVariables <em>Editor Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsPluginID <em>Tests Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsPluginVariables <em>Tests Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isOptimizedHasChildren <em>Optimized Has Children</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isTableProviders <em>Table Providers</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isColorProviders <em>Color Providers</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isFontProviders <em>Font Providers</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRuntimeVersion <em>Runtime Version</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isPackedEnums <em>Packed Enums</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getGenPackages <em>Gen Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getUsedGenPackages <em>Used Gen Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getInterfaceNamePattern <em>Interface Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getClassNamePattern <em>Class Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isOperationReflection <em>Operation Reflection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel()
 * @model
 * @generated
 */
public interface GenModel extends GenBase
{
  /**
   * Returns the value of the '<em><b>Copyright Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Copyright Text</em>' attribute.
   * @see #setCopyrightText(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_CopyrightText()
   * @model
   * @generated
   */
  String getCopyrightText();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getCopyrightText <em>Copyright Text</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Copyright Text</em>' attribute.
   * @see #getCopyrightText()
   * @generated
   */
  void setCopyrightText(String value);

  /**
   * Returns the value of the '<em><b>Model Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * When unset, this attribute takes a default value based on {@link #getModelPluginID() model plugin ID}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Directory</em>' attribute.
   * @see #setModelDirectory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ModelDirectory()
   * @model
   * @generated
   */
  String getModelDirectory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelDirectory <em>Model Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Directory</em>' attribute.
   * @see #getModelDirectory()
   * @generated
   */
  void setModelDirectory(String value);

  /**
   * Returns the value of the '<em><b>Creation Commands</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Creation Commands</em>' attribute.
   * @see #setCreationCommands(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_CreationCommands()
   * @model default="true"
   * @generated
   */
  boolean isCreationCommands();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationCommands <em>Creation Commands</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Creation Commands</em>' attribute.
   * @see #isCreationCommands()
   * @generated
   */
  void setCreationCommands(boolean value);

  /**
   * Returns the value of the '<em><b>Creation Icons</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Creation Icons</em>' attribute.
   * @see #setCreationIcons(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_CreationIcons()
   * @model default="true"
   * @generated
   */
  boolean isCreationIcons();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationIcons <em>Creation Icons</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Creation Icons</em>' attribute.
   * @see #isCreationIcons()
   * @generated
   */
  void setCreationIcons(boolean value);

  /**
   * Returns the value of the '<em><b>Edit Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * When unset, this attribute takes a default value based on the {@link #getModelDirectory model directory}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Edit Directory</em>' attribute.
   * @see #isSetEditDirectory()
   * @see #unsetEditDirectory()
   * @see #setEditDirectory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditDirectory()
   * @model unsettable="true"
   * @generated
   */
  String getEditDirectory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditDirectory <em>Edit Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Edit Directory</em>' attribute.
   * @see #isSetEditDirectory()
   * @see #unsetEditDirectory()
   * @see #getEditDirectory()
   * @generated
   */
  void setEditDirectory(String value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditDirectory <em>Edit Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetEditDirectory()
   * @see #getEditDirectory()
   * @see #setEditDirectory(String)
   * @generated
   */
  void unsetEditDirectory();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditDirectory <em>Edit Directory</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Edit Directory</em>' attribute is set.
   * @see #unsetEditDirectory()
   * @see #getEditDirectory()
   * @see #setEditDirectory(String)
   * @generated
   */
  boolean isSetEditDirectory();

  /**
   * Returns the value of the '<em><b>Creation Submenus</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Creation Submenus</em>' attribute.
   * @see #setCreationSubmenus(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_CreationSubmenus()
   * @model
   * @generated
   */
  boolean isCreationSubmenus();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationSubmenus <em>Creation Submenus</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Creation Submenus</em>' attribute.
   * @see #isCreationSubmenus()
   * @generated
   */
  void setCreationSubmenus(boolean value);

  /**
   * Returns the value of the '<em><b>Editor Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * When unset, this attribute takes a default value based on the {@link #getModelDirectory model directory}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Editor Directory</em>' attribute.
   * @see #isSetEditorDirectory()
   * @see #unsetEditorDirectory()
   * @see #setEditorDirectory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditorDirectory()
   * @model unsettable="true"
   * @generated
   */
  String getEditorDirectory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorDirectory <em>Editor Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Editor Directory</em>' attribute.
   * @see #isSetEditorDirectory()
   * @see #unsetEditorDirectory()
   * @see #getEditorDirectory()
   * @generated
   */
  void setEditorDirectory(String value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorDirectory <em>Editor Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetEditorDirectory()
   * @see #getEditorDirectory()
   * @see #setEditorDirectory(String)
   * @generated
   */
  void unsetEditorDirectory();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorDirectory <em>Editor Directory</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Editor Directory</em>' attribute is set.
   * @see #unsetEditorDirectory()
   * @see #getEditorDirectory()
   * @see #setEditorDirectory(String)
   * @generated
   */
  boolean isSetEditorDirectory();

  /**
   * Returns the value of the '<em><b>Model Plugin ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This attribute is unsettable for the benefit of other attributes, whose
   * default values are based on it. If it is unset, they will be, too.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Plugin ID</em>' attribute.
   * @see #setModelPluginID(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ModelPluginID()
   * @model
   * @generated
   */
  String getModelPluginID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginID <em>Model Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Plugin ID</em>' attribute.
   * @see #getModelPluginID()
   * @generated
   */
  void setModelPluginID(String value);

  /**
   * Returns the value of the '<em><b>Template Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Template Directory</em>' attribute.
   * @see #setTemplateDirectory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_TemplateDirectory()
   * @model
   * @generated
   */
  String getTemplateDirectory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTemplateDirectory <em>Template Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Template Directory</em>' attribute.
   * @see #getTemplateDirectory()
   * @generated
   */
  void setTemplateDirectory(String value);

  /**
   * Returns the value of the '<em><b>Runtime Jar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Runtime Jar</em>' attribute.
   * @see #setRuntimeJar(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RuntimeJar()
   * @model
   * @generated
   */
  boolean isRuntimeJar();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeJar <em>Runtime Jar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Runtime Jar</em>' attribute.
   * @see #isRuntimeJar()
   * @generated
   */
  void setRuntimeJar(boolean value);

  /**
   * Returns the value of the '<em><b>Foreign Model</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Foreign Model</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ForeignModel()
   * @model
   * @generated
   */
  EList<String> getForeignModel();

  /**
   * Returns the value of the '<em><b>Dynamic Templates</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dynamic Templates</em>' attribute.
   * @see #setDynamicTemplates(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_DynamicTemplates()
   * @model
   * @generated
   */
  boolean isDynamicTemplates();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isDynamicTemplates <em>Dynamic Templates</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dynamic Templates</em>' attribute.
   * @see #isDynamicTemplates()
   * @generated
   */
  void setDynamicTemplates(boolean value);

  /**
   * Returns the value of the '<em><b>Redirection</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Redirection</em>' attribute.
   * @see #setRedirection(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_Redirection()
   * @model
   * @generated
   */
  String getRedirection();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRedirection <em>Redirection</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Redirection</em>' attribute.
   * @see #getRedirection()
   * @generated
   */
  void setRedirection(String value);

  /**
   * Returns the value of the '<em><b>Force Overwrite</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Force Overwrite</em>' attribute.
   * @see #setForceOverwrite(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ForceOverwrite()
   * @model
   * @generated
   */
  boolean isForceOverwrite();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isForceOverwrite <em>Force Overwrite</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Force Overwrite</em>' attribute.
   * @see #isForceOverwrite()
   * @generated
   */
  void setForceOverwrite(boolean value);

  /**
   * Returns the value of the '<em><b>Non Externalized String Tag</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>This attribute has been replaced by {@link #isNonNLSMarkers
   * nonNLSMarkers}.  Even after the attribute has been set to a non-null
   * value, this method will continue to return <code>null</code>, though
   * <code>isNonNLSMarkers()</code> will return <code>true</code>.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Non Externalized String Tag</em>' attribute.
   * @see #setNonExternalizedStringTag(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_NonExternalizedStringTag()
   * @model
   * @generated
   */
  String getNonExternalizedStringTag();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getNonExternalizedStringTag <em>Non Externalized String Tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <p>This attribute has been replaced by {@link #setNonNLSMarkers
   * nonNLSMarkers}.  To automate the transition, this sets the attribute
   * <code>null</code>, and calls <code>setNonNLSMarkers(value != null)</code>.
   * </p>
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Non Externalized String Tag</em>' attribute.
   * @see #getNonExternalizedStringTag()
   * @generated
   */
  void setNonExternalizedStringTag(String value);

  /**
   * Returns the value of the '<em><b>Model Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Name</em>' attribute.
   * @see #setModelName(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ModelName()
   * @model
   * @generated
   */
  String getModelName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelName <em>Model Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Name</em>' attribute.
   * @see #getModelName()
   * @generated
   */
  void setModelName(String value);

  /**
   * Returns the value of the '<em><b>Model Plugin Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Plugin Class</em>' attribute.
   * @see #setModelPluginClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ModelPluginClass()
   * @model
   * @generated
   */
  String getModelPluginClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginClass <em>Model Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Plugin Class</em>' attribute.
   * @see #getModelPluginClass()
   * @generated
   */
  void setModelPluginClass(String value);

  /**
   * Returns the value of the '<em><b>Edit Plugin Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * When unset, this attribute takes a default value based on the {@link #getModelName model name},
   * and the first of the {@link #getGenPackages generated packages}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Edit Plugin Class</em>' attribute.
   * @see #isSetEditPluginClass()
   * @see #unsetEditPluginClass()
   * @see #setEditPluginClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditPluginClass()
   * @model unsettable="true"
   * @generated
   */
  String getEditPluginClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginClass <em>Edit Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Edit Plugin Class</em>' attribute.
   * @see #isSetEditPluginClass()
   * @see #unsetEditPluginClass()
   * @see #getEditPluginClass()
   * @generated
   */
  void setEditPluginClass(String value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginClass <em>Edit Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetEditPluginClass()
   * @see #getEditPluginClass()
   * @see #setEditPluginClass(String)
   * @generated
   */
  void unsetEditPluginClass();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginClass <em>Edit Plugin Class</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Edit Plugin Class</em>' attribute is set.
   * @see #unsetEditPluginClass()
   * @see #getEditPluginClass()
   * @see #setEditPluginClass(String)
   * @generated
   */
  boolean isSetEditPluginClass();

  /**
   * Returns the value of the '<em><b>Editor Plugin Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * When unset, this attribute takes a default value based on {@link #getModelName model name},
   * and the first of the {@link #getGenPackages generated packages}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Editor Plugin Class</em>' attribute.
   * @see #isSetEditorPluginClass()
   * @see #unsetEditorPluginClass()
   * @see #setEditorPluginClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditorPluginClass()
   * @model unsettable="true"
   * @generated
   */
  String getEditorPluginClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginClass <em>Editor Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Editor Plugin Class</em>' attribute.
   * @see #isSetEditorPluginClass()
   * @see #unsetEditorPluginClass()
   * @see #getEditorPluginClass()
   * @generated
   */
  void setEditorPluginClass(String value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginClass <em>Editor Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetEditorPluginClass()
   * @see #getEditorPluginClass()
   * @see #setEditorPluginClass(String)
   * @generated
   */
  void unsetEditorPluginClass();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginClass <em>Editor Plugin Class</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Editor Plugin Class</em>' attribute is set.
   * @see #unsetEditorPluginClass()
   * @see #getEditorPluginClass()
   * @see #setEditorPluginClass(String)
   * @generated
   */
  boolean isSetEditorPluginClass();

  /**
   * Returns the value of the '<em><b>Update Classpath</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Update Classpath</em>' attribute.
   * @see #setUpdateClasspath(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_UpdateClasspath()
   * @model default="true"
   * @generated
   */
  boolean isUpdateClasspath();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isUpdateClasspath <em>Update Classpath</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Update Classpath</em>' attribute.
   * @see #isUpdateClasspath()
   * @generated
   */
  void setUpdateClasspath(boolean value);

  /**
   * Returns the value of the '<em><b>Generate Schema</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Generate Schema</em>' attribute.
   * @see #setGenerateSchema(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_GenerateSchema()
   * @model
   * @generated
   */
  boolean isGenerateSchema();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isGenerateSchema <em>Generate Schema</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Generate Schema</em>' attribute.
   * @see #isGenerateSchema()
   * @generated
   */
  void setGenerateSchema(boolean value);

  /**
   * Returns the value of the '<em><b>Non NLS Markers</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Non NLS Markers</em>' attribute.
   * @see #setNonNLSMarkers(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_NonNLSMarkers()
   * @model default="false"
   * @generated
   */
  boolean isNonNLSMarkers();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isNonNLSMarkers <em>Non NLS Markers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <p>As a side effect, this method sets {@link #setNonExternalizedStringTag
   * nonExternalizedStringTag} to <code>null</code>.
   * </p>
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Non NLS Markers</em>' attribute.
   * @see #isNonNLSMarkers()
   * @generated
   */
  void setNonNLSMarkers(boolean value);

  /**
   * Returns the value of the '<em><b>Static Packages</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Static Packages</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_StaticPackages()
   * @model
   * @generated
   */
  EList<String> getStaticPackages();

  /**
   * Returns the value of the '<em><b>Model Plugin Variables</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Plugin Variables</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ModelPluginVariables()
   * @model
   * @generated
   */
  EList<String> getModelPluginVariables();

  /**
   * Returns the value of the '<em><b>Root Extends Interface</b></em>' attribute.
   * The default value is <code>"org.eclipse.emf.ecore.EObject"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Extends Interface</em>' attribute.
   * @see #setRootExtendsInterface(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RootExtendsInterface()
   * @model default="org.eclipse.emf.ecore.EObject"
   * @generated
   */
  String getRootExtendsInterface();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsInterface <em>Root Extends Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Root Extends Interface</em>' attribute.
   * @see #getRootExtendsInterface()
   * @generated
   */
  void setRootExtendsInterface(String value);

  /**
   * Returns the value of the '<em><b>Root Extends Class</b></em>' attribute.
   * The default value is <code>"org.eclipse.emf.ecore.impl.EObjectImpl"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Extends Class</em>' attribute.
   * @see #setRootExtendsClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RootExtendsClass()
   * @model default="org.eclipse.emf.ecore.impl.EObjectImpl"
   * @generated
   */
  String getRootExtendsClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsClass <em>Root Extends Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Root Extends Class</em>' attribute.
   * @see #getRootExtendsClass()
   * @generated
   */
  void setRootExtendsClass(String value);

  /**
   * Returns the value of the '<em><b>Root Implements Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root Implements Interface</em>' attribute.
   * @see #setRootImplementsInterface(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RootImplementsInterface()
   * @model
   * @generated
   */
  String getRootImplementsInterface();

  GenClass getRootImplementsInterfaceGenClass();


  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootImplementsInterface <em>Root Implements Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Root Implements Interface</em>' attribute.
   * @see #getRootImplementsInterface()
   * @generated
   */
  void setRootImplementsInterface(String value);

  /**
   * Returns the value of the '<em><b>Suppress EMF Types</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suppress EMF Types</em>' attribute.
   * @see #setSuppressEMFTypes(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_SuppressEMFTypes()
   * @model
   * @generated
   */
  boolean isSuppressEMFTypes();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFTypes <em>Suppress EMF Types</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppress EMF Types</em>' attribute.
   * @see #isSuppressEMFTypes()
   * @generated
   */
  void setSuppressEMFTypes(boolean value);

  /**
   * Returns the value of the '<em><b>Suppress EMF Meta Data</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suppress EMF Meta Data</em>' attribute.
   * @see #setSuppressEMFMetaData(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_SuppressEMFMetaData()
   * @model
   * @generated
   */
  boolean isSuppressEMFMetaData();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFMetaData <em>Suppress EMF Meta Data</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppress EMF Meta Data</em>' attribute.
   * @see #isSuppressEMFMetaData()
   * @generated
   */
  void setSuppressEMFMetaData(boolean value);

  /**
   * Returns the value of the '<em><b>Suppress EMF Model Tags</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suppress EMF Model Tags</em>' attribute.
   * @see #setSuppressEMFModelTags(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_SuppressEMFModelTags()
   * @model
   * @generated
   */
  boolean isSuppressEMFModelTags();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFModelTags <em>Suppress EMF Model Tags</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppress EMF Model Tags</em>' attribute.
   * @see #isSuppressEMFModelTags()
   * @generated
   */
  void setSuppressEMFModelTags(boolean value);

  /**
   * Returns the value of the '<em><b>Suppress Interfaces</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suppress Interfaces</em>' attribute.
   * @see #setSuppressInterfaces(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_SuppressInterfaces()
   * @model
   * @generated
   */
  boolean isSuppressInterfaces();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressInterfaces <em>Suppress Interfaces</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppress Interfaces</em>' attribute.
   * @see #isSuppressInterfaces()
   * @generated
   */
  void setSuppressInterfaces(boolean value);

  /**
   * Returns the value of the '<em><b>Feature Map Wrapper Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Map Wrapper Interface</em>' attribute.
   * @see #setFeatureMapWrapperInterface(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_FeatureMapWrapperInterface()
   * @model
   * @generated
   */
  String getFeatureMapWrapperInterface();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInterface <em>Feature Map Wrapper Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature Map Wrapper Interface</em>' attribute.
   * @see #getFeatureMapWrapperInterface()
   * @generated
   */
  void setFeatureMapWrapperInterface(String value);

  /**
   * Returns the value of the '<em><b>Feature Map Wrapper Internal Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Map Wrapper Internal Interface</em>' attribute.
   * @see #setFeatureMapWrapperInternalInterface(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_FeatureMapWrapperInternalInterface()
   * @model
   * @generated
   */
  String getFeatureMapWrapperInternalInterface();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInternalInterface <em>Feature Map Wrapper Internal Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature Map Wrapper Internal Interface</em>' attribute.
   * @see #getFeatureMapWrapperInternalInterface()
   * @generated
   */
  void setFeatureMapWrapperInternalInterface(String value);

  /**
   * Returns the value of the '<em><b>Feature Map Wrapper Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Map Wrapper Class</em>' attribute.
   * @see #setFeatureMapWrapperClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_FeatureMapWrapperClass()
   * @model
   * @generated
   */
  String getFeatureMapWrapperClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperClass <em>Feature Map Wrapper Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature Map Wrapper Class</em>' attribute.
   * @see #getFeatureMapWrapperClass()
   * @generated
   */
  void setFeatureMapWrapperClass(String value);

  /**
   * Returns the value of the '<em><b>Runtime Compatibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Runtime Compatibility</em>' attribute.
   * @see #setRuntimeCompatibility(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RuntimeCompatibility()
   * @model
   * @generated
   */
  boolean isRuntimeCompatibility();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeCompatibility <em>Runtime Compatibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Runtime Compatibility</em>' attribute.
   * @see #isRuntimeCompatibility()
   * @generated
   */
  void setRuntimeCompatibility(boolean value);

  /**
   * Returns the value of the '<em><b>Rich Client Platform</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rich Client Platform</em>' attribute.
   * @see #setRichClientPlatform(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RichClientPlatform()
   * @model
   * @generated
   */
  boolean isRichClientPlatform();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRichClientPlatform <em>Rich Client Platform</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rich Client Platform</em>' attribute.
   * @see #isRichClientPlatform()
   * @generated
   */
  void setRichClientPlatform(boolean value);

  /**
   * Returns the value of the '<em><b>Reflective Delegation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reflective Delegation</em>' attribute.
   * @see #setReflectiveDelegation(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ReflectiveDelegation()
   * @model volatile="true" derived="true"
   * @generated
   */
  boolean isReflectiveDelegation();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isReflectiveDelegation <em>Reflective Delegation</em>}' attribute.
   * <!-- begin-user-doc -->
   * @deprecated In 2.2. Please use {@link #setFeatureDelegation} instead.
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reflective Delegation</em>' attribute.
   * @see #isReflectiveDelegation()
   * @generated
   */
  @Deprecated
  void setReflectiveDelegation(boolean value);

  /**
   * Returns the value of the '<em><b>Code Formatting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Code Formatting</em>' attribute.
   * @see #setCodeFormatting(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_CodeFormatting()
   * @model
   * @generated
   */
  boolean isCodeFormatting();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCodeFormatting <em>Code Formatting</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Code Formatting</em>' attribute.
   * @see #isCodeFormatting()
   * @generated
   */
  void setCodeFormatting(boolean value);

  /**
   * Returns the value of the '<em><b>Tests Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * By default, this attribute is set to null.  
   * When unset, it takes a default value based on the {@link #getModelDirectory model directory}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tests Directory</em>' attribute.
   * @see #isSetTestsDirectory()
   * @see #unsetTestsDirectory()
   * @see #setTestsDirectory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_TestsDirectory()
   * @model unsettable="true"
   * @generated
   */
  String getTestsDirectory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsDirectory <em>Tests Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tests Directory</em>' attribute.
   * @see #isSetTestsDirectory()
   * @see #unsetTestsDirectory()
   * @see #getTestsDirectory()
   * @generated
   */
  void setTestsDirectory(String value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsDirectory <em>Tests Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetTestsDirectory()
   * @see #getTestsDirectory()
   * @see #setTestsDirectory(String)
   * @generated
   */
  void unsetTestsDirectory();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsDirectory <em>Tests Directory</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Tests Directory</em>' attribute is set.
   * @see #unsetTestsDirectory()
   * @see #getTestsDirectory()
   * @see #setTestsDirectory(String)
   * @generated
   */
  boolean isSetTestsDirectory();

  /**
   * Returns the value of the '<em><b>Test Suite Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * When unset, this attribute takes a default value based on the {@link #getModelName model name},
   * and the first of the {@link #getGenPackages generated packages}.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Test Suite Class</em>' attribute.
   * @see #isSetTestSuiteClass()
   * @see #unsetTestSuiteClass()
   * @see #setTestSuiteClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_TestSuiteClass()
   * @model unsettable="true"
   * @generated
   */
  String getTestSuiteClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestSuiteClass <em>Test Suite Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Test Suite Class</em>' attribute.
   * @see #isSetTestSuiteClass()
   * @see #unsetTestSuiteClass()
   * @see #getTestSuiteClass()
   * @generated
   */
  void setTestSuiteClass(String value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestSuiteClass <em>Test Suite Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetTestSuiteClass()
   * @see #getTestSuiteClass()
   * @see #setTestSuiteClass(String)
   * @generated
   */
  void unsetTestSuiteClass();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestSuiteClass <em>Test Suite Class</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Test Suite Class</em>' attribute is set.
   * @see #unsetTestSuiteClass()
   * @see #getTestSuiteClass()
   * @see #setTestSuiteClass(String)
   * @generated
   */
  boolean isSetTestSuiteClass();

  /**
   * Returns the value of the '<em><b>Boolean Flags Field</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Boolean Flags Field</em>' attribute.
   * @see #setBooleanFlagsField(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_BooleanFlagsField()
   * @model
   * @generated
   */
  String getBooleanFlagsField();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getBooleanFlagsField <em>Boolean Flags Field</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Boolean Flags Field</em>' attribute.
   * @see #getBooleanFlagsField()
   * @generated
   */
  void setBooleanFlagsField(String value);

  /**
   * Returns the value of the '<em><b>Boolean Flags Reserved Bits</b></em>' attribute.
   * The default value is <code>"-1"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Boolean Flags Reserved Bits</em>' attribute.
   * @see #setBooleanFlagsReservedBits(int)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_BooleanFlagsReservedBits()
   * @model default="-1"
   * @generated
   */
  int getBooleanFlagsReservedBits();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getBooleanFlagsReservedBits <em>Boolean Flags Reserved Bits</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Boolean Flags Reserved Bits</em>' attribute.
   * @see #getBooleanFlagsReservedBits()
   * @generated
   */
  void setBooleanFlagsReservedBits(int value);

  /**
   * Returns the value of the '<em><b>Importer ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Importer ID</em>' attribute.
   * @see #setImporterID(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ImporterID()
   * @model
   * @generated
   */
  String getImporterID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getImporterID <em>Importer ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Importer ID</em>' attribute.
   * @see #getImporterID()
   * @generated
   */
  void setImporterID(String value);

  /**
   * Returns the value of the '<em><b>Bundle Manifest</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bundle Manifest</em>' attribute.
   * @see #setBundleManifest(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_BundleManifest()
   * @model default="true"
   * @generated
   */
  boolean isBundleManifest();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isBundleManifest <em>Bundle Manifest</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bundle Manifest</em>' attribute.
   * @see #isBundleManifest()
   * @generated
   */
  void setBundleManifest(boolean value);

  /**
   * Returns the value of the '<em><b>Feature Delegation</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Delegation</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind
   * @see #setFeatureDelegation(GenDelegationKind)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_FeatureDelegation()
   * @model
   * @generated
   */
  GenDelegationKind getFeatureDelegation();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureDelegation <em>Feature Delegation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature Delegation</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind
   * @see #getFeatureDelegation()
   * @generated
   */
  void setFeatureDelegation(GenDelegationKind value);

  /**
   * Returns the value of the '<em><b>Containment Proxies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Containment Proxies</em>' attribute.
   * @see #setContainmentProxies(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ContainmentProxies()
   * @model
   * @generated
   */
  boolean isContainmentProxies();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isContainmentProxies <em>Containment Proxies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Containment Proxies</em>' attribute.
   * @see #isContainmentProxies()
   * @generated
   */
  void setContainmentProxies(boolean value);

  /**
   * Returns the value of the '<em><b>Minimal Reflective Methods</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Minimal Reflective Methods</em>' attribute.
   * @see #setMinimalReflectiveMethods(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_MinimalReflectiveMethods()
   * @model default="true"
   * @generated
   */
  boolean isMinimalReflectiveMethods();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isMinimalReflectiveMethods <em>Minimal Reflective Methods</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Minimal Reflective Methods</em>' attribute.
   * @see #isMinimalReflectiveMethods()
   * @generated
   */
  void setMinimalReflectiveMethods(boolean value);

  /**
   * Returns the value of the '<em><b>Suppress Containment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suppress Containment</em>' attribute.
   * @see #setSuppressContainment(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_SuppressContainment()
   * @model
   * @generated
   */
  boolean isSuppressContainment();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressContainment <em>Suppress Containment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppress Containment</em>' attribute.
   * @see #isSuppressContainment()
   * @generated
   */
  void setSuppressContainment(boolean value);

  /**
   * Returns the value of the '<em><b>Suppress Notification</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suppress Notification</em>' attribute.
   * @see #setSuppressNotification(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_SuppressNotification()
   * @model
   * @generated
   */
  boolean isSuppressNotification();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressNotification <em>Suppress Notification</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppress Notification</em>' attribute.
   * @see #isSuppressNotification()
   * @generated
   */
  void setSuppressNotification(boolean value);

  /**
   * Returns the value of the '<em><b>Array Accessors</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Accessors</em>' attribute.
   * @see #setArrayAccessors(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ArrayAccessors()
   * @model
   * @generated
   */
  boolean isArrayAccessors();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isArrayAccessors <em>Array Accessors</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Accessors</em>' attribute.
   * @see #isArrayAccessors()
   * @generated
   */
  void setArrayAccessors(boolean value);

  /**
   * Returns the value of the '<em><b>Suppress Unsettable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suppress Unsettable</em>' attribute.
   * @see #setSuppressUnsettable(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_SuppressUnsettable()
   * @model
   * @generated
   */
  boolean isSuppressUnsettable();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressUnsettable <em>Suppress Unsettable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppress Unsettable</em>' attribute.
   * @see #isSuppressUnsettable()
   * @generated
   */
  void setSuppressUnsettable(boolean value);

  /**
   * Returns the value of the '<em><b>Facade Helper Class</b></em>' attribute.
   * The default value is <code>"org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The default value must be always equals to JMerger.DEFAULT_FACADE_HELPER_CLASS.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Facade Helper Class</em>' attribute.
   * @see #setFacadeHelperClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_FacadeHelperClass()
   * @model default="org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper"
   * @generated
   */
  String getFacadeHelperClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFacadeHelperClass <em>Facade Helper Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Facade Helper Class</em>' attribute.
   * @see #getFacadeHelperClass()
   * @generated
   */
  void setFacadeHelperClass(String value);

  /**
   * Returns the value of the '<em><b>Compliance Level</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel}.
   * <!-- begin-user-doc -->
   * <p>
   * </p>
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Compliance Level</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel
   * @see #setComplianceLevel(GenJDKLevel)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ComplianceLevel()
   * @model
   * @generated
   */
  GenJDKLevel getComplianceLevel();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getComplianceLevel <em>Compliance Level</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Compliance Level</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel
   * @see #getComplianceLevel()
   * @generated
   */
  void setComplianceLevel(GenJDKLevel value);

  /**
   * Returns the value of the '<em><b>Suppress Gen Model Annotations</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.3
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suppress Gen Model Annotations</em>' attribute.
   * @see #setSuppressGenModelAnnotations(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_SuppressGenModelAnnotations()
   * @model default="true"
   * @generated
   */
  boolean isSuppressGenModelAnnotations();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressGenModelAnnotations <em>Suppress Gen Model Annotations</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppress Gen Model Annotations</em>' attribute.
   * @see #isSuppressGenModelAnnotations()
   * @generated
   */
  void setSuppressGenModelAnnotations(boolean value);

  /**
   * Returns the value of the '<em><b>Copyright Fields</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Copyright Fields</em>' attribute.
   * @see #setCopyrightFields(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_CopyrightFields()
   * @model default="true"
   * @generated
   */
  boolean isCopyrightFields();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCopyrightFields <em>Copyright Fields</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Copyright Fields</em>' attribute.
   * @see #isCopyrightFields()
   * @generated
   */
  void setCopyrightFields(boolean value);

  /**
   * Returns the value of the '<em><b>Binary Compatible Reflective Methods</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.3
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Binary Compatible Reflective Methods</em>' attribute.
   * @see #setBinaryCompatibleReflectiveMethods(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_BinaryCompatibleReflectiveMethods()
   * @model
   * @generated
   */
  boolean isBinaryCompatibleReflectiveMethods();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isBinaryCompatibleReflectiveMethods <em>Binary Compatible Reflective Methods</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Binary Compatible Reflective Methods</em>' attribute.
   * @see #isBinaryCompatibleReflectiveMethods()
   * @generated
   */
  void setBinaryCompatibleReflectiveMethods(boolean value);

  /**
   * Returns the value of the '<em><b>Public Constructors</b></em>' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Public Constructors</em>' attribute.
   * @see #setPublicConstructors(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_PublicConstructors()
   * @model
   * @generated
   */
  boolean isPublicConstructors();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isPublicConstructors <em>Public Constructors</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Public Constructors</em>' attribute.
   * @see #isPublicConstructors()
   * @generated
   */
  void setPublicConstructors(boolean value);

  /**
   * Returns the value of the '<em><b>Template Plugin Variables</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Template Plugin Variables</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_TemplatePluginVariables()
   * @model
   * @generated
   */
  EList<String> getTemplatePluginVariables();

  /**
   * Returns the value of the '<em><b>Provider Root Extends Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Provider Root Extends Class</em>' attribute.
   * @see #setProviderRootExtendsClass(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ProviderRootExtendsClass()
   * @model
   * @generated
   */
  String getProviderRootExtendsClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getProviderRootExtendsClass <em>Provider Root Extends Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Provider Root Extends Class</em>' attribute.
   * @see #getProviderRootExtendsClass()
   * @generated
   */
  void setProviderRootExtendsClass(String value);

  /**
   * Returns the value of the '<em><b>Edit Plugin ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Edit Plugin ID</em>' attribute.
   * @see #setEditPluginID(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditPluginID()
   * @model
   * @generated
   */
  String getEditPluginID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginID <em>Edit Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Edit Plugin ID</em>' attribute.
   * @see #getEditPluginID()
   * @generated
   */
  void setEditPluginID(String value);

  /**
   * Returns the value of the '<em><b>Edit Plugin Variables</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Edit Plugin Variables</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditPluginVariables()
   * @model
   * @generated
   */
  EList<String> getEditPluginVariables();

  /**
   * Returns the value of the '<em><b>Editor Plugin ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Editor Plugin ID</em>' attribute.
   * @see #setEditorPluginID(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditorPluginID()
   * @model
   * @generated
   */
  String getEditorPluginID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginID <em>Editor Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Editor Plugin ID</em>' attribute.
   * @see #getEditorPluginID()
   * @generated
   */
  void setEditorPluginID(String value);

  /**
   * Returns the value of the '<em><b>Editor Plugin Variables</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Editor Plugin Variables</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_EditorPluginVariables()
   * @model
   * @generated
   */
  EList<String> getEditorPluginVariables();

  /**
   * Returns the value of the '<em><b>Tests Plugin ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tests Plugin ID</em>' attribute.
   * @see #setTestsPluginID(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_TestsPluginID()
   * @model
   * @generated
   */
  String getTestsPluginID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsPluginID <em>Tests Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tests Plugin ID</em>' attribute.
   * @see #getTestsPluginID()
   * @generated
   */
  void setTestsPluginID(String value);

  /**
   * Returns the value of the '<em><b>Tests Plugin Variables</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tests Plugin Variables</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_TestsPluginVariables()
   * @model
   * @generated
   */
  EList<String> getTestsPluginVariables();

  /**
   * Returns the value of the '<em><b>Optimized Has Children</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
  * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Optimized Has Children</em>' attribute.
   * @see #setOptimizedHasChildren(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_OptimizedHasChildren()
   * @model
   * @generated
   */
  boolean isOptimizedHasChildren();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isOptimizedHasChildren <em>Optimized Has Children</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Optimized Has Children</em>' attribute.
   * @see #isOptimizedHasChildren()
   * @generated
   */
  void setOptimizedHasChildren(boolean value);

  /**
   * Returns the value of the '<em><b>Table Providers</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.4
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Table Providers</em>' attribute.
   * @see #setTableProviders(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_TableProviders()
   * @model
   * @generated
   */
  boolean isTableProviders();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isTableProviders <em>Table Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Table Providers</em>' attribute.
   * @see #isTableProviders()
   * @generated
   */
  void setTableProviders(boolean value);

  /**
   * Returns the value of the '<em><b>Color Providers</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.4
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Color Providers</em>' attribute.
   * @see #setColorProviders(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ColorProviders()
   * @model
   * @generated
   */
  boolean isColorProviders();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isColorProviders <em>Color Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Color Providers</em>' attribute.
   * @see #isColorProviders()
   * @generated
   */
  void setColorProviders(boolean value);

  /**
   * Returns the value of the '<em><b>Font Providers</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.4
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Font Providers</em>' attribute.
   * @see #setFontProviders(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_FontProviders()
   * @model
   * @generated
   */
  boolean isFontProviders();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isFontProviders <em>Font Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Font Providers</em>' attribute.
   * @see #isFontProviders()
   * @generated
   */
  void setFontProviders(boolean value);

  /**
   * Returns the value of the '<em><b>Runtime Version</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion}.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.4
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Runtime Version</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion
   * @see #isSetRuntimeVersion()
   * @see #unsetRuntimeVersion()
   * @see #setRuntimeVersion(GenRuntimeVersion)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_RuntimeVersion()
   * @model unsettable="true"
   * @generated
   */
  GenRuntimeVersion getRuntimeVersion();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRuntimeVersion <em>Runtime Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Runtime Version</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion
   * @see #isSetRuntimeVersion()
   * @see #unsetRuntimeVersion()
   * @see #getRuntimeVersion()
   * @generated
   */
  void setRuntimeVersion(GenRuntimeVersion value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRuntimeVersion <em>Runtime Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetRuntimeVersion()
   * @see #getRuntimeVersion()
   * @see #setRuntimeVersion(GenRuntimeVersion)
   * @generated
   */
  void unsetRuntimeVersion();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRuntimeVersion <em>Runtime Version</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Runtime Version</em>' attribute is set.
   * @see #unsetRuntimeVersion()
   * @see #getRuntimeVersion()
   * @see #setRuntimeVersion(GenRuntimeVersion)
   * @generated
   */
  boolean isSetRuntimeVersion();

  /**
   * Returns the value of the '<em><b>Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.4
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Language</em>' attribute.
   * @see #setLanguage(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_Language()
   * @model
   * @generated
   */
  String getLanguage();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getLanguage <em>Language</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Language</em>' attribute.
   * @see #getLanguage()
   * @generated
   */
  void setLanguage(String value);

  /**
   * Returns the value of the '<em><b>Packed Enums</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * Whether the values of enum attributes should be packed in the {@link #getBooleanFlagsField() boolean flags field}.
   * </p>
   * @since 2.4
   * <!-- end-user-doc -->
   * @return the value of the '<em>Packed Enums</em>' attribute.
   * @see #setPackedEnums(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_PackedEnums()
   * @model
   * @generated
   */
  boolean isPackedEnums();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isPackedEnums <em>Packed Enums</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Packed Enums</em>' attribute.
   * @see #isPackedEnums()
   * @generated
   */
  void setPackedEnums(boolean value);

  /**
   * Returns the value of the '<em><b>Gen Packages</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenModel <em>Gen Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Packages</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_GenPackages()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenModel
   * @model opposite="genModel" containment="true"
   * @generated
   */
  EList<GenPackage> getGenPackages();

  /**
   * Returns the value of the '<em><b>Used Gen Packages</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Used Gen Packages</em>' reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_UsedGenPackages()
   * @model
   * @generated
   */
  EList<GenPackage> getUsedGenPackages();

  /**
   * Returns the value of the '<em><b>Interface Name Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
   * @since 2.5
   * <!-- end-user-doc -->
   * @return the value of the '<em>Interface Name Pattern</em>' attribute.
   * @see #setInterfaceNamePattern(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_InterfaceNamePattern()
   * @model
   * @generated
   */
  String getInterfaceNamePattern();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getInterfaceNamePattern <em>Interface Name Pattern</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.5
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interface Name Pattern</em>' attribute.
   * @see #getInterfaceNamePattern()
   * @generated
   */
  void setInterfaceNamePattern(String value);

  /**
   * Returns the value of the '<em><b>Class Name Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
   * @since 2.5
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class Name Pattern</em>' attribute.
   * @see #setClassNamePattern(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_ClassNamePattern()
   * @model
   * @generated
   */
  String getClassNamePattern();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getClassNamePattern <em>Class Name Pattern</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.5
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class Name Pattern</em>' attribute.
   * @see #getClassNamePattern()
   * @generated
   */
  void setClassNamePattern(String value);

  /**
   * Returns the value of the '<em><b>Operation Reflection</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * @since 2.6
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operation Reflection</em>' attribute.
   * @see #setOperationReflection(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenModel_OperationReflection()
   * @model default="false"
   * @generated
   */
  boolean isOperationReflection();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isOperationReflection <em>Operation Reflection</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.6
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operation Reflection</em>' attribute.
   * @see #isOperationReflection()
   * @generated
   */
  void setOperationReflection(boolean value);

  EList<GenPackage> getStaticGenPackages();

  void initialize(Collection<? extends EPackage> ePackages);

  String getDriverNumber();
  String getDate();

  String getNonNLS();
  String getNonNLS(int i);
  String getNonNLS(String s);
  String getNonNLS(String s, int i);

  void markImportLocation(StringBuffer stringBuffer);
  void markImportLocation(StringBuffer stringBuffer, GenPackage genPackage);
  void emitSortedImports();
  String getIndentation(StringBuffer stringBuffer);

  ImportManager getImportManager();
  void setImportManager(ImportManager importManager);

  /**
   * @since 2.3
   */
  String getLineDelimiter();

  /**
   * @since 2.3
   */
  void setLineDelimiter(String lineDelimiter);

  String getImportedName(String qualifiedName);
  void addImport(String qualifiedName);
  void addPseudoImport(String qualifiedName);

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.Generator.Options Generator.Options} provides an
   * equivalent way to obtain this information. This method will be removed after 2.2.
   */
  @Deprecated
  String getMergeRulesLocation();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} provides an equivalent to this method.
   * This method will be removed after 2.2.
   */
  @Deprecated
  JControlModel getJControlModel();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEnumClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getFactoryClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getPackageClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getAdapterFactoryClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getSwitchClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getValidatorClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getPluginXMLEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getManifestMFEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getModelPluginClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getResourceClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getResourceFactoryClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getBuildPropertiesEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getXMLProcessorClassEmitter();

  //
  // EMF.Edit generation
  //
  boolean hasEditSupport();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getItemProviderEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getItemProviderAdapterFactoryEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditPluginClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditPluginXMLEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditManifestMFEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditPluginPropertiesEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditBuildPropertiesEmitter();

  //
  // EMF.Editor generation
  //
  boolean hasEditorSupport();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditorEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getActionBarContributorEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getModelWizardEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditorAdvisorEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditorPluginClassEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditorPluginXMLEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditorManifestMFEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditorPluginPropertiesEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getEditorBuildPropertiesEmitter();

  //
  // Tests generation
  //
  boolean hasTestSupport();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getTestCaseEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getModelTestSuiteEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getPackageTestSuiteEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getPackageExampleEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getTestsPluginXMLEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getTestsManifestMFEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getTestsPluginPropertiesEmitter();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  JETEmitter getTestsBuildPropertiesEmitter();

  // boolean isGenerateEditPlugin();
  // boolean isGenerateEditorPlugin();

  String getModelProjectDirectory();
  String getEditProjectDirectory();
  String getEditorProjectDirectory();
  String getTestsProjectDirectory();
  
  boolean sameModelEditProject();
  boolean sameEditEditorProject();
  boolean sameModelEditorProject();
  boolean sameModelTestsProject();

  String getEditIconsDirectory();
  String getEditorIconsDirectory();

  void setCanGenerate(boolean canGenerate);

  GenPackage findGenPackage(EPackage ePackage);

  /**
   * @since 2.3
   */
  GenClassifier findGenClassifier(EClassifier eClassifier);

  List<GenPackage> getAllGenPackagesWithClassifiers();
  List<GenPackage> getAllUsedGenPackagesWithClassifiers();
  List<GenPackage> getAllGenAndUsedGenPackagesWithClassifiers();
  List<GenPackage> getAllGenUsedAndStaticGenPackagesWithClassifiers();

  /**
   * @since 2.5
   */
  List<GenPackage> getAllGenPackagesWithConcreteClasses();

  boolean hasModelPluginClass();
  boolean hasPluginSupport();

  String getModelPluginClassName();
  String getModelPluginPackageName();
  String getQualifiedModelPluginClassName();

  String getEditPluginDirectory();
  String getEditPluginClassName();
  String getEditPluginPackageName();
  String getQualifiedEditPluginClassName();

  String getEditorPluginDirectory();
  String getEditorPluginClassName();
  String getEditorPluginPackageName();
  String getEditorAdvisorClassName();
  String getQualifiedEditorPluginClassName();
  String getQualifiedEditorAdvisorClassName();

  boolean hasTestSuiteClass();
  String getTestSuiteClassName();
  String getTestSuitePackageName();
  String getQualifiedTestSuiteClassName();
  
  List<String> getModelQualifiedPackageNames();
  List<String> getModelRequiredPlugins();
  List<String> getEditQualifiedPackageNames();
  List<String> getEditRequiredPlugins();
  List<String> getEditorQualifiedPackageNames();
  List<String> getEditorRequiredPlugins();
  List<String> getTestsQualifiedPackageNames();
  List<String> getTestsRequiredPlugins();

  List<String> getEditResourceDelegateImportedPluginClassNames();

  /**
   * Restore all the corresponding settings of the old version into this version.
   */
  boolean reconcile(GenModel oldGenModelVersion);

  List<EPackage> getMissingPackages();

  boolean hasXMLDependency();

  IStatus validate();
  Diagnostic diagnose();

  String getXMLEncodingChoices();

  List<String> getEffectiveModelPluginVariables();

  boolean needsRuntimeCompatibility();
  
  List<GenFeature> getAllGenFeatures();
  List<GenFeature> getFilteredAllGenFeatures(); // Filtered for property keys to remove duplicates.

  /**
   * Set the code formatter options to be used to {@link #createCodeFormatter create} a new code formatter.
   *  
   * @deprecated In EMF 2.2, the {@link org.eclipse.emf.codegen.ecore.generator.Generator.Options Generator.Options} should be used to
   * record code formatter options in order to be used via the new Generator-based design. This method will be removed after 2.2.
   */
  @SuppressWarnings("rawtypes")
  @Deprecated
  void setCodeFormatterOptions(Map options);

  /**
   * Creates and returns a new JDT code formatter.
   * 
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  CodeFormatter createCodeFormatter();

  boolean isBooleanFlagsEnabled();

  GenModel createGenModel();
  GenPackage createGenPackage();
  GenClass createGenClass();
  GenFeature createGenFeature();
  GenEnum createGenEnum();
  GenEnumLiteral createGenEnumLiteral();
  GenDataType createGenDataType();
  GenOperation createGenOperation();
  GenParameter createGenParameter();
  GenAnnotation createGenAnnotation();
  GenBase create(EClass eClass);
  /**
   * @since 2.3
   */
  GenTypeParameter createGenTypeParameter();

  Set<String> getPropertyCategories();
  
  boolean hasLocalGenModel();
  String getRelativeGenModelLocation();
  
  String getPropertyCategoryKey(String category);  

  ExtendedMetaData getExtendedMetaData();
  
  List<GenPackage> computeMissingUsedGenPackages();

  boolean isVirtualDelegation();

  /**
   * @since 2.5
   */
  boolean isDynamicDelegation();

  /**
   * @since 2.3
   */
  boolean useClassOverrideAnnotation();

  /**
   * @since 2.3
   */
  boolean useGenerics();
  
  /**
   * @since 2.3
   */
  boolean isValidateModel();

  /**
   * @since 2.3
   */
  void setValidateModel(boolean validateModel);
  
  /**
   * @since 2.3
   */
  boolean isSuppressedAnnotation(String source);

  /**
   * @since 2.3
   */
  boolean hasCopyrightField();

  /**
   * @since 2.3
   */
  String getCopyrightFieldLiteral();

  /**
   * Returns the source folders for the model project. The returned 
   * list doesn't contain duplicated elements.
   * @return List<String>
   * @since 2.4
   */
  List<String> getModelSourceFolders();

  /**
   * Returns the source folders for the edit project. The returned 
   * list doesn't contain duplicated elements.
   * @return List<String>
   * @since 2.4
   */
  List<String> getEditSourceFolders();

  /**
   * Returns the source folders for the editor project. The returned 
   * list doesn't contain duplicated elements.
   * @return List<String>
   * @since 2.4
   */
  List<String> getEditorSourceFolders();

  /**
   * Returns the source folders for the tests project. The returned 
   * list doesn't contain duplicated elements.
   * @return List<String>
   * @since 2.4
   */
  List<String> getTestsSourceFolders();
  
  /**
   * @since 2.4
   */
  Locale getLocale();

  /**
   * If this is the GenModel of a used or static GenPackage or of the Ecore, XMLType, or XMLNamespace GenPackage in
   * another GenModel, this returns that GenModel. Otherwise, it returns this GenModel, itself.
   * @since 2.5
   */
  GenModel getMainGenModel();

  /**
   * @since 2.5
   */
  void setMainGenModel(GenModel genModel);

  /**
   * Returns the cached GenPackage for the Ecore metamodel EPackage, or null if it has not yet been requested via {@link #findGenPackage(EPackage)}.
   * @since 2.5
   */
  GenPackage getEcoreGenPackage();

  /**
   * Returns the cached GenPackage for the XMLType EPackage, or null if it has not yet been requested via {@link #findGenPackage(EPackage)}.
   * @since 2.5
   */
  GenPackage getXMLTypeGenPackage();

  /**
   * Returns the cached GenPackage for the XMLNamespace EPackage, or null if it has not yet been requested via {@link #findGenPackage(EPackage)}.
   * @since 2.5
   */
  GenPackage getXMLNamespaceGenPackage();
}
