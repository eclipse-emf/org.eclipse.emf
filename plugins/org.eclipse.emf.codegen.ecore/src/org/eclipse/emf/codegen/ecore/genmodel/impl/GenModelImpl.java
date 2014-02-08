/**
 * Copyright (c) 2002-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.jar.Manifest;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenDecoration;
import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimePlatform;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GenModel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getCopyrightText <em>Copyright Text</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getModelDirectory <em>Model Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isCreationCommands <em>Creation Commands</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isCreationIcons <em>Creation Icons</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isCreationSubmenus <em>Creation Submenus</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getEditDirectory <em>Edit Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getEditorDirectory <em>Editor Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getModelPluginID <em>Model Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getTemplateDirectory <em>Template Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isRuntimeJar <em>Runtime Jar</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getForeignModel <em>Foreign Model</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isDynamicTemplates <em>Dynamic Templates</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getRedirection <em>Redirection</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isForceOverwrite <em>Force Overwrite</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getNonExternalizedStringTag <em>Non Externalized String Tag</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getModelName <em>Model Name</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getModelPluginClass <em>Model Plugin Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getEditPluginClass <em>Edit Plugin Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getEditorPluginClass <em>Editor Plugin Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isUpdateClasspath <em>Update Classpath</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isGenerateSchema <em>Generate Schema</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isNonNLSMarkers <em>Non NLS Markers</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getStaticPackages <em>Static Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getModelPluginVariables <em>Model Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getRootExtendsInterface <em>Root Extends Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getRootExtendsClass <em>Root Extends Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getRootImplementsInterface <em>Root Implements Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isSuppressEMFTypes <em>Suppress EMF Types</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isSuppressEMFMetaData <em>Suppress EMF Meta Data</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isSuppressEMFModelTags <em>Suppress EMF Model Tags</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isSuppressInterfaces <em>Suppress Interfaces</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getFeatureMapWrapperInterface <em>Feature Map Wrapper Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getFeatureMapWrapperInternalInterface <em>Feature Map Wrapper Internal Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getFeatureMapWrapperClass <em>Feature Map Wrapper Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isRuntimeCompatibility <em>Runtime Compatibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isRichClientPlatform <em>Rich Client Platform</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isReflectiveDelegation <em>Reflective Delegation</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isCodeFormatting <em>Code Formatting</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isCommentFormatting <em>Comment Formatting</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getTestsDirectory <em>Tests Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getTestSuiteClass <em>Test Suite Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getBooleanFlagsField <em>Boolean Flags Field</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getBooleanFlagsReservedBits <em>Boolean Flags Reserved Bits</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getImporterID <em>Importer ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isBundleManifest <em>Bundle Manifest</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getFeatureDelegation <em>Feature Delegation</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isContainmentProxies <em>Containment Proxies</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isMinimalReflectiveMethods <em>Minimal Reflective Methods</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isSuppressContainment <em>Suppress Containment</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isSuppressNotification <em>Suppress Notification</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isArrayAccessors <em>Array Accessors</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isSuppressUnsettable <em>Suppress Unsettable</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getFacadeHelperClass <em>Facade Helper Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getComplianceLevel <em>Compliance Level</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isSuppressGenModelAnnotations <em>Suppress Gen Model Annotations</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isCopyrightFields <em>Copyright Fields</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isBinaryCompatibleReflectiveMethods <em>Binary Compatible Reflective Methods</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isPublicConstructors <em>Public Constructors</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getTemplatePluginVariables <em>Template Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getProviderRootExtendsClass <em>Provider Root Extends Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getEditPluginID <em>Edit Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getEditPluginVariables <em>Edit Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getEditorPluginID <em>Editor Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getEditorPluginVariables <em>Editor Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getTestsPluginID <em>Tests Plugin ID</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getTestsPluginVariables <em>Tests Plugin Variables</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isOptimizedHasChildren <em>Optimized Has Children</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isTableProviders <em>Table Providers</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isColorProviders <em>Color Providers</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isFontProviders <em>Font Providers</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getRuntimeVersion <em>Runtime Version</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isPackedEnums <em>Packed Enums</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getGenPackages <em>Gen Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getUsedGenPackages <em>Used Gen Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getInterfaceNamePattern <em>Interface Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getClassNamePattern <em>Class Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isOperationReflection <em>Operation Reflection</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isRichAjaxPlatform <em>Rich Ajax Platform</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getRuntimePlatform <em>Runtime Platform</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isImportOrganizing <em>Import Organizing</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getPluginKey <em>Plugin Key</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getDecoration <em>Decoration</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isStyleProviders <em>Style Providers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenModelImpl extends GenBaseImpl implements GenModel
{
  /**
   * The default value of the '{@link #getCopyrightText() <em>Copyright Text</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCopyrightText()
   * @generated
   * @ordered
   */
  protected static final String COPYRIGHT_TEXT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCopyrightText() <em>Copyright Text</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCopyrightText()
   * @generated
   * @ordered
   */
  protected String copyrightText = COPYRIGHT_TEXT_EDEFAULT;

  /**
   * The default value of the '{@link #getModelDirectory() <em>Model Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelDirectory()
   * @generated
   * @ordered
   */
  protected static final String MODEL_DIRECTORY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getModelDirectory() <em>Model Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelDirectory()
   * @generated
   * @ordered
   */
  protected String modelDirectory = MODEL_DIRECTORY_EDEFAULT;

  /**
   * The default value of the '{@link #isCreationCommands() <em>Creation Commands</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCreationCommands()
   * @generated
   * @ordered
   */
  protected static final boolean CREATION_COMMANDS_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isCreationCommands() <em>Creation Commands</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCreationCommands()
   * @generated
   * @ordered
   */
  protected boolean creationCommands = CREATION_COMMANDS_EDEFAULT;

  /**
   * The default value of the '{@link #isCreationIcons() <em>Creation Icons</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCreationIcons()
   * @generated
   * @ordered
   */
  protected static final boolean CREATION_ICONS_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isCreationIcons() <em>Creation Icons</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCreationIcons()
   * @generated
   * @ordered
   */
  protected boolean creationIcons = CREATION_ICONS_EDEFAULT;

  /**
   * The default value of the '{@link #isCreationSubmenus() <em>Creation Submenus</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCreationSubmenus()
   * @generated
   * @ordered
   */
  protected static final boolean CREATION_SUBMENUS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isCreationSubmenus() <em>Creation Submenus</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCreationSubmenus()
   * @generated
   * @ordered
   */
  protected boolean creationSubmenus = CREATION_SUBMENUS_EDEFAULT;

  /**
   * The default value of the '{@link #getEditDirectory() <em>Edit Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditDirectory()
   * @generated
   * @ordered
   */
  protected static final String EDIT_DIRECTORY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEditDirectory() <em>Edit Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditDirectory()
   * @generated
   * @ordered
   */
  protected String editDirectory = EDIT_DIRECTORY_EDEFAULT;

  /**
   * This is true if the Edit Directory attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean editDirectoryESet;

  /**
   * The default value of the '{@link #getEditorDirectory() <em>Editor Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditorDirectory()
   * @generated
   * @ordered
   */
  protected static final String EDITOR_DIRECTORY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEditorDirectory() <em>Editor Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditorDirectory()
   * @generated
   * @ordered
   */
  protected String editorDirectory = EDITOR_DIRECTORY_EDEFAULT;

  /**
   * This is true if the Editor Directory attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean editorDirectoryESet;

  /**
   * The default value of the '{@link #getModelPluginID() <em>Model Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelPluginID()
   * @generated
   * @ordered
   */
  protected static final String MODEL_PLUGIN_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getModelPluginID() <em>Model Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelPluginID()
   * @generated
   * @ordered
   */
  protected String modelPluginID = MODEL_PLUGIN_ID_EDEFAULT;

  /**
   * The default value of the '{@link #getTemplateDirectory() <em>Template Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTemplateDirectory()
   * @generated
   * @ordered
   */
  protected static final String TEMPLATE_DIRECTORY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTemplateDirectory() <em>Template Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTemplateDirectory()
   * @generated
   * @ordered
   */
  protected String templateDirectory = TEMPLATE_DIRECTORY_EDEFAULT;

  /**
   * The default value of the '{@link #isRuntimeJar() <em>Runtime Jar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRuntimeJar()
   * @generated
   * @ordered
   */
  protected static final boolean RUNTIME_JAR_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isRuntimeJar() <em>Runtime Jar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRuntimeJar()
   * @generated
   * @ordered
   */
  protected boolean runtimeJar = RUNTIME_JAR_EDEFAULT;

  /**
   * The cached value of the '{@link #getForeignModel() <em>Foreign Model</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForeignModel()
   * @generated
   * @ordered
   */
  protected EList<String> foreignModel;

  /**
   * The default value of the '{@link #isDynamicTemplates() <em>Dynamic Templates</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDynamicTemplates()
   * @generated
   * @ordered
   */
  protected static final boolean DYNAMIC_TEMPLATES_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDynamicTemplates() <em>Dynamic Templates</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDynamicTemplates()
   * @generated
   * @ordered
   */
  protected boolean dynamicTemplates = DYNAMIC_TEMPLATES_EDEFAULT;

  /**
   * The default value of the '{@link #getRedirection() <em>Redirection</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRedirection()
   * @generated
   * @ordered
   */
  protected static final String REDIRECTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRedirection() <em>Redirection</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRedirection()
   * @generated
   * @ordered
   */
  protected String redirection = REDIRECTION_EDEFAULT;

  /**
   * The default value of the '{@link #isForceOverwrite() <em>Force Overwrite</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isForceOverwrite()
   * @generated
   * @ordered
   */
  protected static final boolean FORCE_OVERWRITE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isForceOverwrite() <em>Force Overwrite</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isForceOverwrite()
   * @generated
   * @ordered
   */
  protected boolean forceOverwrite = FORCE_OVERWRITE_EDEFAULT;

  /**
   * The default value of the '{@link #getNonExternalizedStringTag() <em>Non Externalized String Tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNonExternalizedStringTag()
   * @generated
   * @ordered
   */
  protected static final String NON_EXTERNALIZED_STRING_TAG_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNonExternalizedStringTag() <em>Non Externalized String Tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNonExternalizedStringTag()
   * @generated
   * @ordered
   */
  protected String nonExternalizedStringTag = NON_EXTERNALIZED_STRING_TAG_EDEFAULT;

  /**
   * The default value of the '{@link #getModelName() <em>Model Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelName()
   * @generated
   * @ordered
   */
  protected static final String MODEL_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getModelName() <em>Model Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelName()
   * @generated
   * @ordered
   */
  protected String modelName = MODEL_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getModelPluginClass() <em>Model Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelPluginClass()
   * @generated
   * @ordered
   */
  protected static final String MODEL_PLUGIN_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getModelPluginClass() <em>Model Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelPluginClass()
   * @generated
   * @ordered
   */
  protected String modelPluginClass = MODEL_PLUGIN_CLASS_EDEFAULT;

  /**
   * The default value of the '{@link #getEditPluginClass() <em>Edit Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditPluginClass()
   * @generated
   * @ordered
   */
  protected static final String EDIT_PLUGIN_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEditPluginClass() <em>Edit Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditPluginClass()
   * @generated
   * @ordered
   */
  protected String editPluginClass = EDIT_PLUGIN_CLASS_EDEFAULT;

  /**
   * This is true if the Edit Plugin Class attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean editPluginClassESet;

  /**
   * The default value of the '{@link #getEditorPluginClass() <em>Editor Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditorPluginClass()
   * @generated
   * @ordered
   */
  protected static final String EDITOR_PLUGIN_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEditorPluginClass() <em>Editor Plugin Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditorPluginClass()
   * @generated
   * @ordered
   */
  protected String editorPluginClass = EDITOR_PLUGIN_CLASS_EDEFAULT;

  /**
   * This is true if the Editor Plugin Class attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean editorPluginClassESet;

  /**
   * The default value of the '{@link #isUpdateClasspath() <em>Update Classpath</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUpdateClasspath()
   * @generated
   * @ordered
   */
  protected static final boolean UPDATE_CLASSPATH_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isUpdateClasspath() <em>Update Classpath</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUpdateClasspath()
   * @generated
   * @ordered
   */
  protected boolean updateClasspath = UPDATE_CLASSPATH_EDEFAULT;

  /**
   * The default value of the '{@link #isGenerateSchema() <em>Generate Schema</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGenerateSchema()
   * @generated
   * @ordered
   */
  protected static final boolean GENERATE_SCHEMA_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isGenerateSchema() <em>Generate Schema</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGenerateSchema()
   * @generated
   * @ordered
   */
  protected boolean generateSchema = GENERATE_SCHEMA_EDEFAULT;

  /**
   * The default value of the '{@link #isNonNLSMarkers() <em>Non NLS Markers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNonNLSMarkers()
   * @generated
   * @ordered
   */
  protected static final boolean NON_NLS_MARKERS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isNonNLSMarkers() <em>Non NLS Markers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNonNLSMarkers()
   * @generated
   * @ordered
   */
  protected boolean nonNLSMarkers = NON_NLS_MARKERS_EDEFAULT;

  /**
   * The cached value of the '{@link #getStaticPackages() <em>Static Packages</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStaticPackages()
   * @generated
   * @ordered
   */
  protected EList<String> staticPackages;

  /**
   * The cached value of the '{@link #getModelPluginVariables() <em>Model Plugin Variables</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelPluginVariables()
   * @generated
   * @ordered
   */
  protected EList<String> modelPluginVariables;

  /**
   * The default value of the '{@link #getRootExtendsInterface() <em>Root Extends Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRootExtendsInterface()
   * @generated
   * @ordered
   */
  protected static final String ROOT_EXTENDS_INTERFACE_EDEFAULT = "org.eclipse.emf.ecore.EObject";

  /**
   * The cached value of the '{@link #getRootExtendsInterface() <em>Root Extends Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRootExtendsInterface()
   * @generated
   * @ordered
   */
  protected String rootExtendsInterface = ROOT_EXTENDS_INTERFACE_EDEFAULT;

  /**
   * The default value of the '{@link #getRootExtendsClass() <em>Root Extends Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRootExtendsClass()
   * @generated
   * @ordered
   */
  protected static final String ROOT_EXTENDS_CLASS_EDEFAULT = "org.eclipse.emf.ecore.impl.EObjectImpl";

  /**
   * The cached value of the '{@link #getRootExtendsClass() <em>Root Extends Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRootExtendsClass()
   * @generated
   * @ordered
   */
  protected String rootExtendsClass = ROOT_EXTENDS_CLASS_EDEFAULT;

  /**
   * The default value of the '{@link #getRootImplementsInterface() <em>Root Implements Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRootImplementsInterface()
   * @generated
   * @ordered
   */
  protected static final String ROOT_IMPLEMENTS_INTERFACE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRootImplementsInterface() <em>Root Implements Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRootImplementsInterface()
   * @generated
   * @ordered
   */
  protected String rootImplementsInterface = ROOT_IMPLEMENTS_INTERFACE_EDEFAULT;

  /**
   * The default value of the '{@link #isSuppressEMFTypes() <em>Suppress EMF Types</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressEMFTypes()
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESS_EMF_TYPES_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressEMFTypes() <em>Suppress EMF Types</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressEMFTypes()
   * @generated
   * @ordered
   */
  protected boolean suppressEMFTypes = SUPPRESS_EMF_TYPES_EDEFAULT;

  /**
   * The default value of the '{@link #isSuppressEMFMetaData() <em>Suppress EMF Meta Data</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressEMFMetaData()
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESS_EMF_META_DATA_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressEMFMetaData() <em>Suppress EMF Meta Data</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressEMFMetaData()
   * @generated
   * @ordered
   */
  protected boolean suppressEMFMetaData = SUPPRESS_EMF_META_DATA_EDEFAULT;

  /**
   * The default value of the '{@link #isSuppressEMFModelTags() <em>Suppress EMF Model Tags</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressEMFModelTags()
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESS_EMF_MODEL_TAGS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressEMFModelTags() <em>Suppress EMF Model Tags</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressEMFModelTags()
   * @generated
   * @ordered
   */
  protected boolean suppressEMFModelTags = SUPPRESS_EMF_MODEL_TAGS_EDEFAULT;

  /**
   * The default value of the '{@link #isSuppressInterfaces() <em>Suppress Interfaces</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressInterfaces()
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESS_INTERFACES_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressInterfaces() <em>Suppress Interfaces</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressInterfaces()
   * @generated
   * @ordered
   */
  protected boolean suppressInterfaces = SUPPRESS_INTERFACES_EDEFAULT;

  /**
   * The default value of the '{@link #getFeatureMapWrapperInterface() <em>Feature Map Wrapper Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureMapWrapperInterface()
   * @generated
   * @ordered
   */
  protected static final String FEATURE_MAP_WRAPPER_INTERFACE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFeatureMapWrapperInterface() <em>Feature Map Wrapper Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureMapWrapperInterface()
   * @generated
   * @ordered
   */
  protected String featureMapWrapperInterface = FEATURE_MAP_WRAPPER_INTERFACE_EDEFAULT;

  /**
   * The default value of the '{@link #getFeatureMapWrapperInternalInterface() <em>Feature Map Wrapper Internal Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureMapWrapperInternalInterface()
   * @generated
   * @ordered
   */
  protected static final String FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFeatureMapWrapperInternalInterface() <em>Feature Map Wrapper Internal Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureMapWrapperInternalInterface()
   * @generated
   * @ordered
   */
  protected String featureMapWrapperInternalInterface = FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE_EDEFAULT;

  /**
   * The default value of the '{@link #getFeatureMapWrapperClass() <em>Feature Map Wrapper Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureMapWrapperClass()
   * @generated
   * @ordered
   */
  protected static final String FEATURE_MAP_WRAPPER_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFeatureMapWrapperClass() <em>Feature Map Wrapper Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureMapWrapperClass()
   * @generated
   * @ordered
   */
  protected String featureMapWrapperClass = FEATURE_MAP_WRAPPER_CLASS_EDEFAULT;

  /**
   * The default value of the '{@link #isRuntimeCompatibility() <em>Runtime Compatibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRuntimeCompatibility()
   * @generated
   * @ordered
   */
  protected static final boolean RUNTIME_COMPATIBILITY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isRuntimeCompatibility() <em>Runtime Compatibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRuntimeCompatibility()
   * @generated
   * @ordered
   */
  protected boolean runtimeCompatibility = RUNTIME_COMPATIBILITY_EDEFAULT;

  /**
   * The default value of the '{@link #isRichClientPlatform() <em>Rich Client Platform</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRichClientPlatform()
   * @generated
   * @ordered
   */
  protected static final boolean RICH_CLIENT_PLATFORM_EDEFAULT = false;

  /**
   * The default value of the '{@link #isReflectiveDelegation() <em>Reflective Delegation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isReflectiveDelegation()
   * @generated
   * @ordered
   */
  protected static final boolean REFLECTIVE_DELEGATION_EDEFAULT = false;

  /**
   * The default value of the '{@link #isCodeFormatting() <em>Code Formatting</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCodeFormatting()
   * @generated
   * @ordered
   */
  protected static final boolean CODE_FORMATTING_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isCodeFormatting() <em>Code Formatting</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCodeFormatting()
   * @generated
   * @ordered
   */
  protected boolean codeFormatting = CODE_FORMATTING_EDEFAULT;

  /**
   * The default value of the '{@link #isCommentFormatting() <em>Comment Formatting</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCommentFormatting()
   * @generated
   * @ordered
   */
  protected static final boolean COMMENT_FORMATTING_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isCommentFormatting() <em>Comment Formatting</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCommentFormatting()
   * @generated
   * @ordered
   */
  protected boolean commentFormatting = COMMENT_FORMATTING_EDEFAULT;

  /**
   * The default value of the '{@link #getTestsDirectory() <em>Tests Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTestsDirectory()
   * @generated
   * @ordered
   */
  protected static final String TESTS_DIRECTORY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTestsDirectory() <em>Tests Directory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTestsDirectory()
   * @generated
   * @ordered
   */
  protected String testsDirectory = TESTS_DIRECTORY_EDEFAULT;

  /**
   * This is true if the Tests Directory attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean testsDirectoryESet;

  /**
   * The default value of the '{@link #getTestSuiteClass() <em>Test Suite Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTestSuiteClass()
   * @generated
   * @ordered
   */
  protected static final String TEST_SUITE_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTestSuiteClass() <em>Test Suite Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTestSuiteClass()
   * @generated
   * @ordered
   */
  protected String testSuiteClass = TEST_SUITE_CLASS_EDEFAULT;

  /**
   * This is true if the Test Suite Class attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean testSuiteClassESet;

  /**
   * The default value of the '{@link #getBooleanFlagsField() <em>Boolean Flags Field</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBooleanFlagsField()
   * @generated
   * @ordered
   */
  protected static final String BOOLEAN_FLAGS_FIELD_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBooleanFlagsField() <em>Boolean Flags Field</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBooleanFlagsField()
   * @generated
   * @ordered
   */
  protected String booleanFlagsField = BOOLEAN_FLAGS_FIELD_EDEFAULT;

  /**
   * The default value of the '{@link #getBooleanFlagsReservedBits() <em>Boolean Flags Reserved Bits</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBooleanFlagsReservedBits()
   * @generated
   * @ordered
   */
  protected static final int BOOLEAN_FLAGS_RESERVED_BITS_EDEFAULT = -1;

  /**
   * The cached value of the '{@link #getBooleanFlagsReservedBits() <em>Boolean Flags Reserved Bits</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBooleanFlagsReservedBits()
   * @generated
   * @ordered
   */
  protected int booleanFlagsReservedBits = BOOLEAN_FLAGS_RESERVED_BITS_EDEFAULT;

  /**
   * The default value of the '{@link #getImporterID() <em>Importer ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImporterID()
   * @generated
   * @ordered
   */
  protected static final String IMPORTER_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getImporterID() <em>Importer ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImporterID()
   * @generated
   * @ordered
   */
  protected String importerID = IMPORTER_ID_EDEFAULT;

  /**
   * The default value of the '{@link #isBundleManifest() <em>Bundle Manifest</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isBundleManifest()
   * @generated
   * @ordered
   */
  protected static final boolean BUNDLE_MANIFEST_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isBundleManifest() <em>Bundle Manifest</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isBundleManifest()
   * @generated
   * @ordered
   */
  protected boolean bundleManifest = BUNDLE_MANIFEST_EDEFAULT;

  /**
   * The default value of the '{@link #getFeatureDelegation() <em>Feature Delegation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureDelegation()
   * @generated
   * @ordered
   */
  protected static final GenDelegationKind FEATURE_DELEGATION_EDEFAULT = GenDelegationKind.NONE_LITERAL;

  /**
   * The cached value of the '{@link #getFeatureDelegation() <em>Feature Delegation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureDelegation()
   * @generated
   * @ordered
   */
  protected GenDelegationKind featureDelegation = FEATURE_DELEGATION_EDEFAULT;

  /**
   * The default value of the '{@link #isContainmentProxies() <em>Containment Proxies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isContainmentProxies()
   * @generated
   * @ordered
   */
  protected static final boolean CONTAINMENT_PROXIES_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isContainmentProxies() <em>Containment Proxies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isContainmentProxies()
   * @generated
   * @ordered
   */
  protected boolean containmentProxies = CONTAINMENT_PROXIES_EDEFAULT;

  /**
   * The default value of the '{@link #isMinimalReflectiveMethods() <em>Minimal Reflective Methods</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMinimalReflectiveMethods()
   * @generated
   * @ordered
   */
  protected static final boolean MINIMAL_REFLECTIVE_METHODS_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isMinimalReflectiveMethods() <em>Minimal Reflective Methods</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMinimalReflectiveMethods()
   * @generated
   * @ordered
   */
  protected boolean minimalReflectiveMethods = MINIMAL_REFLECTIVE_METHODS_EDEFAULT;

  /**
   * The default value of the '{@link #isSuppressContainment() <em>Suppress Containment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressContainment()
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESS_CONTAINMENT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressContainment() <em>Suppress Containment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressContainment()
   * @generated
   * @ordered
   */
  protected boolean suppressContainment = SUPPRESS_CONTAINMENT_EDEFAULT;

  /**
   * The default value of the '{@link #isSuppressNotification() <em>Suppress Notification</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressNotification()
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESS_NOTIFICATION_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressNotification() <em>Suppress Notification</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressNotification()
   * @generated
   * @ordered
   */
  protected boolean suppressNotification = SUPPRESS_NOTIFICATION_EDEFAULT;

  /**
   * The default value of the '{@link #isArrayAccessors() <em>Array Accessors</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isArrayAccessors()
   * @generated
   * @ordered
   */
  protected static final boolean ARRAY_ACCESSORS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isArrayAccessors() <em>Array Accessors</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isArrayAccessors()
   * @generated
   * @ordered
   */
  protected boolean arrayAccessors = ARRAY_ACCESSORS_EDEFAULT;

  /**
   * The default value of the '{@link #isSuppressUnsettable() <em>Suppress Unsettable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressUnsettable()
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESS_UNSETTABLE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressUnsettable() <em>Suppress Unsettable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressUnsettable()
   * @generated
   * @ordered
   */
  protected boolean suppressUnsettable = SUPPRESS_UNSETTABLE_EDEFAULT;

  /**
   * The default value of the '{@link #getFacadeHelperClass() <em>Facade Helper Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * This value should be always equals to JMerger.DEFAULT_FACADE_HELPER_CLASS.
   * <!-- end-user-doc -->
   * @see #getFacadeHelperClass()
   * @generated
   * @ordered
   */
  protected static final String FACADE_HELPER_CLASS_EDEFAULT = "org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper";

  /**
   * The cached value of the '{@link #getFacadeHelperClass() <em>Facade Helper Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFacadeHelperClass()
   * @generated
   * @ordered
   */
  protected String facadeHelperClass = FACADE_HELPER_CLASS_EDEFAULT;

  /**
   * The default value of the '{@link #getComplianceLevel() <em>Compliance Level</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComplianceLevel()
   * @generated
   * @ordered
   */
  protected static final GenJDKLevel COMPLIANCE_LEVEL_EDEFAULT = GenJDKLevel.JDK14_LITERAL;

  /**
   * The cached value of the '{@link #getComplianceLevel() <em>Compliance Level</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComplianceLevel()
   * @generated
   * @ordered
   */
  protected GenJDKLevel complianceLevel = COMPLIANCE_LEVEL_EDEFAULT;

  /**
   * The default value of the '{@link #isSuppressGenModelAnnotations() <em>Suppress Gen Model Annotations</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressGenModelAnnotations()
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESS_GEN_MODEL_ANNOTATIONS_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isSuppressGenModelAnnotations() <em>Suppress Gen Model Annotations</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressGenModelAnnotations()
   * @generated
   * @ordered
   */
  protected boolean suppressGenModelAnnotations = SUPPRESS_GEN_MODEL_ANNOTATIONS_EDEFAULT;

  /**
   * The default value of the '{@link #isCopyrightFields() <em>Copyright Fields</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCopyrightFields()
   * @generated
   * @ordered
   */
  protected static final boolean COPYRIGHT_FIELDS_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isCopyrightFields() <em>Copyright Fields</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCopyrightFields()
   * @generated
   * @ordered
   */
  protected boolean copyrightFields = COPYRIGHT_FIELDS_EDEFAULT;

  /**
   * The default value of the '{@link #isBinaryCompatibleReflectiveMethods() <em>Binary Compatible Reflective Methods</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isBinaryCompatibleReflectiveMethods()
   * @generated
   * @ordered
   */
  protected static final boolean BINARY_COMPATIBLE_REFLECTIVE_METHODS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isBinaryCompatibleReflectiveMethods() <em>Binary Compatible Reflective Methods</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isBinaryCompatibleReflectiveMethods()
   * @generated
   * @ordered
   */
  protected boolean binaryCompatibleReflectiveMethods = BINARY_COMPATIBLE_REFLECTIVE_METHODS_EDEFAULT;

  /**
   * The default value of the '{@link #isPublicConstructors() <em>Public Constructors</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPublicConstructors()
   * @generated
   * @ordered
   */
  protected static final boolean PUBLIC_CONSTRUCTORS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isPublicConstructors() <em>Public Constructors</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPublicConstructors()
   * @generated
   * @ordered
   */
  protected boolean publicConstructors = PUBLIC_CONSTRUCTORS_EDEFAULT;

  /**
   * The cached value of the '{@link #getTemplatePluginVariables() <em>Template Plugin Variables</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTemplatePluginVariables()
   * @generated
   * @ordered
   */
  protected EList<String> templatePluginVariables;

  /**
   * The default value of the '{@link #getProviderRootExtendsClass() <em>Provider Root Extends Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProviderRootExtendsClass()
   * @generated
   * @ordered
   */
  protected static final String PROVIDER_ROOT_EXTENDS_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getProviderRootExtendsClass() <em>Provider Root Extends Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProviderRootExtendsClass()
   * @generated
   * @ordered
   */
  protected String providerRootExtendsClass = PROVIDER_ROOT_EXTENDS_CLASS_EDEFAULT;

  /**
   * The default value of the '{@link #getEditPluginID() <em>Edit Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditPluginID()
   * @generated
   * @ordered
   */
  protected static final String EDIT_PLUGIN_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEditPluginID() <em>Edit Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditPluginID()
   * @generated
   * @ordered
   */
  protected String editPluginID = EDIT_PLUGIN_ID_EDEFAULT;

  /**
   * The cached value of the '{@link #getEditPluginVariables() <em>Edit Plugin Variables</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditPluginVariables()
   * @generated
   * @ordered
   */
  protected EList<String> editPluginVariables;

  /**
   * The default value of the '{@link #getEditorPluginID() <em>Editor Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditorPluginID()
   * @generated
   * @ordered
   */
  protected static final String EDITOR_PLUGIN_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEditorPluginID() <em>Editor Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditorPluginID()
   * @generated
   * @ordered
   */
  protected String editorPluginID = EDITOR_PLUGIN_ID_EDEFAULT;

  /**
   * The cached value of the '{@link #getEditorPluginVariables() <em>Editor Plugin Variables</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEditorPluginVariables()
   * @generated
   * @ordered
   */
  protected EList<String> editorPluginVariables;

  /**
   * The default value of the '{@link #getTestsPluginID() <em>Tests Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTestsPluginID()
   * @generated
   * @ordered
   */
  protected static final String TESTS_PLUGIN_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTestsPluginID() <em>Tests Plugin ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTestsPluginID()
   * @generated
   * @ordered
   */
  protected String testsPluginID = TESTS_PLUGIN_ID_EDEFAULT;

  /**
   * The cached value of the '{@link #getTestsPluginVariables() <em>Tests Plugin Variables</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTestsPluginVariables()
   * @generated
   * @ordered
   */
  protected EList<String> testsPluginVariables;

  /**
   * The default value of the '{@link #isOptimizedHasChildren() <em>Optimized Has Children</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOptimizedHasChildren()
   * @generated
   * @ordered
   */
  protected static final boolean OPTIMIZED_HAS_CHILDREN_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOptimizedHasChildren() <em>Optimized Has Children</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOptimizedHasChildren()
   * @generated
   * @ordered
   */
  protected boolean optimizedHasChildren = OPTIMIZED_HAS_CHILDREN_EDEFAULT;

  /**
   * The default value of the '{@link #isTableProviders() <em>Table Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTableProviders()
   * @generated
   * @ordered
   */
  protected static final boolean TABLE_PROVIDERS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isTableProviders() <em>Table Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTableProviders()
   * @generated
   * @ordered
   */
  protected boolean tableProviders = TABLE_PROVIDERS_EDEFAULT;

  /**
   * The default value of the '{@link #isColorProviders() <em>Color Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isColorProviders()
   * @generated
   * @ordered
   */
  protected static final boolean COLOR_PROVIDERS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isColorProviders() <em>Color Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isColorProviders()
   * @generated
   * @ordered
   */
  protected boolean colorProviders = COLOR_PROVIDERS_EDEFAULT;

  /**
   * The default value of the '{@link #isFontProviders() <em>Font Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFontProviders()
   * @generated
   * @ordered
   */
  protected static final boolean FONT_PROVIDERS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isFontProviders() <em>Font Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFontProviders()
   * @generated
   * @ordered
   */
  protected boolean fontProviders = FONT_PROVIDERS_EDEFAULT;

  /**
   * The default value of the '{@link #getRuntimeVersion() <em>Runtime Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRuntimeVersion()
   * @generated NOT
   * @ordered
   */
  protected static final GenRuntimeVersion RUNTIME_VERSION_EDEFAULT = GenRuntimeVersion.VALUES.get(GenRuntimeVersion.VALUES.size() - 1);

  /**
   * The cached value of the '{@link #getRuntimeVersion() <em>Runtime Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRuntimeVersion()
   * @generated
   * @ordered
   */
  protected GenRuntimeVersion runtimeVersion = RUNTIME_VERSION_EDEFAULT;

  /**
   * This is true if the Runtime Version attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean runtimeVersionESet;

  /**
   * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLanguage()
   * @generated
   * @ordered
   */
  protected static final String LANGUAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLanguage()
   * @generated
   * @ordered
   */
  protected String language = LANGUAGE_EDEFAULT;

  /**
   * The default value of the '{@link #isPackedEnums() <em>Packed Enums</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPackedEnums()
   * @generated
   * @ordered
   */
  protected static final boolean PACKED_ENUMS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isPackedEnums() <em>Packed Enums</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPackedEnums()
   * @generated
   * @ordered
   */
  protected boolean packedEnums = PACKED_ENUMS_EDEFAULT;

  /**
   * The cached value of the '{@link #getGenPackages() <em>Gen Packages</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenPackages()
   * @generated
   * @ordered
   */
  protected EList<GenPackage> genPackages;

  protected EList<GenPackage> staticGenPackages = null;

  /**
   * The cached value of the '{@link #getUsedGenPackages() <em>Used Gen Packages</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUsedGenPackages()
   * @generated
   * @ordered
   */
  protected EList<GenPackage> usedGenPackages;

  /**
   * The default value of the '{@link #getInterfaceNamePattern() <em>Interface Name Pattern</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInterfaceNamePattern()
   * @generated
   * @ordered
   */
  protected static final String INTERFACE_NAME_PATTERN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getInterfaceNamePattern() <em>Interface Name Pattern</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInterfaceNamePattern()
   * @generated
   * @ordered
   */
  protected String interfaceNamePattern = INTERFACE_NAME_PATTERN_EDEFAULT;

  /**
   * The default value of the '{@link #getClassNamePattern() <em>Class Name Pattern</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassNamePattern()
   * @generated
   * @ordered
   */
  protected static final String CLASS_NAME_PATTERN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getClassNamePattern() <em>Class Name Pattern</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassNamePattern()
   * @generated
   * @ordered
   */
  protected String classNamePattern = CLASS_NAME_PATTERN_EDEFAULT;

  /**
   * The default value of the '{@link #isOperationReflection() <em>Operation Reflection</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOperationReflection()
   * @generated
   * @ordered
   */
  protected static final boolean OPERATION_REFLECTION_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOperationReflection() <em>Operation Reflection</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOperationReflection()
   * @generated
   * @ordered
   */
  protected boolean operationReflection = OPERATION_REFLECTION_EDEFAULT;

  /**
   * The default value of the '{@link #isRichAjaxPlatform() <em>Rich Ajax Platform</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRichAjaxPlatform()
   * @generated
   * @ordered
   */
  protected static final boolean RICH_AJAX_PLATFORM_EDEFAULT = false;

  /**
   * The default value of the '{@link #getRuntimePlatform() <em>Runtime Platform</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRuntimePlatform()
   * @generated
   * @ordered
   */
  protected static final GenRuntimePlatform RUNTIME_PLATFORM_EDEFAULT = GenRuntimePlatform.IDE;

  /**
   * The cached value of the '{@link #getRuntimePlatform() <em>Runtime Platform</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRuntimePlatform()
   * @generated
   * @ordered
   */
  protected GenRuntimePlatform runtimePlatform = RUNTIME_PLATFORM_EDEFAULT;

  /**
   * The default value of the '{@link #isImportOrganizing() <em>Import Organizing</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isImportOrganizing()
   * @generated
   * @ordered
   */
  protected static final boolean IMPORT_ORGANIZING_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isImportOrganizing() <em>Import Organizing</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isImportOrganizing()
   * @generated
   * @ordered
   */
  protected boolean importOrganizing = IMPORT_ORGANIZING_EDEFAULT;

  /**
   * The default value of the '{@link #getPluginKey() <em>Plugin Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPluginKey()
   * @generated
   * @ordered
   */
  protected static final String PLUGIN_KEY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPluginKey() <em>Plugin Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPluginKey()
   * @generated
   * @ordered
   */
  protected String pluginKey = PLUGIN_KEY_EDEFAULT;

  /**
   * The default value of the '{@link #getDecoration() <em>Decoration</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDecoration()
   * @generated
   * @ordered
   */
  protected static final GenDecoration DECORATION_EDEFAULT = GenDecoration.NONE;

  /**
   * The cached value of the '{@link #getDecoration() <em>Decoration</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDecoration()
   * @generated
   * @ordered
   */
  protected GenDecoration decoration = DECORATION_EDEFAULT;

  /**
   * The default value of the '{@link #isStyleProviders() <em>Style Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.10
   * <!-- end-user-doc -->
   * @see #isStyleProviders()
   * @generated
   * @ordered
   */
  protected static final boolean STYLE_PROVIDERS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isStyleProviders() <em>Style Providers</em>}' attribute.
   * <!-- begin-user-doc -->
   * @since 2.10
   * <!-- end-user-doc -->
   * @see #isStyleProviders()
   * @generated
   * @ordered
   */
  protected boolean styleProviders = STYLE_PROVIDERS_EDEFAULT;

  protected boolean validateModel = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenModelImpl()
  {
    super();
  }

  @Override
  public GenModel getGenModel()
  {
    return this;
  }

  Map<EPackage, GenPackage> ePackageToGenPackageMap;

  private GenPackage ecoreGenPackage;
  private GenPackage xmlTypeGenPackage;
  private GenPackage xmlNamespaceGenPackage;

  public GenPackage getEcoreGenPackage()
  {
    return isMainGenModel() ? ecoreGenPackage : getMainGenModel().getEcoreGenPackage();
  }

  public GenPackage getXMLTypeGenPackage()
  {
    return isMainGenModel() ? xmlTypeGenPackage : getMainGenModel().getXMLTypeGenPackage();
  }

  public GenPackage getXMLNamespaceGenPackage()
  {
    return isMainGenModel() ? xmlNamespaceGenPackage : getMainGenModel().getXMLNamespaceGenPackage();
  }

  @Override
  public GenPackage findGenPackage(EPackage ePackage)
  {
    GenPackage result;
    if (ePackageToGenPackageMap == null)
    {
      ePackageToGenPackageMap = new HashMap<EPackage, GenPackage>();
      result = null;
    }
    else
    {
      result = ePackageToGenPackageMap.get(ePackage);
      if (result != null)
      {
        return result;
      }
    }

    // A single set of special packages should be cached by the main GenModel.
    //
    if (!isMainGenModel() &&
        (ePackage == EcorePackage.eINSTANCE || ePackage == XMLTypePackage.eINSTANCE || ePackage == XMLNamespacePackage.eINSTANCE))
    {
      result = getMainGenModel().findGenPackage(ePackage);
    }
    else if (ePackage == EcorePackage.eINSTANCE)
    {
      if (ecoreGenPackage == null)
      {
        GenModel ecoreGenModel = getGenModel().createGenModel();
        ecoreGenModel.initialize(Collections.singleton(EcorePackage.eINSTANCE));
        ecoreGenModel.setMainGenModel(this);
        ecoreGenModel.setImportManager(getImportManager());
        ecoreGenModel.setLanguage("en");
        ecoreGenPackage = ecoreGenModel.getGenPackages().get(0);
        ecoreGenPackage.setPrefix("Ecore");
        ecoreGenPackage.setBasePackage("org.eclipse.emf");
      }
      result = ecoreGenPackage;
    }
    else if (ePackage == XMLTypePackage.eINSTANCE)
    {
      if (xmlTypeGenPackage == null)
      {
        GenModel xmlTypeGenModel = getGenModel().createGenModel();
        xmlTypeGenModel.initialize(Collections.singleton(XMLTypePackage.eINSTANCE));
        xmlTypeGenModel.setMainGenModel(this);
        xmlTypeGenModel.setImportManager(getImportManager());
        xmlTypeGenModel.setLanguage("en");
        xmlTypeGenPackage = xmlTypeGenModel.getGenPackages().get(0);
        xmlTypeGenPackage.setPrefix("XMLType");
        xmlTypeGenPackage.setBasePackage("org.eclipse.emf.ecore.xml");
        xmlTypeGenPackage.setDataTypeConverters(true);
      }
      result = xmlTypeGenPackage;
    }
    else if (ePackage == XMLNamespacePackage.eINSTANCE)
    {
      if (xmlNamespaceGenPackage == null)
      {
        GenModel xmlNamespaceGenModel = getGenModel().createGenModel();
        xmlNamespaceGenModel.initialize(Collections.singleton(XMLNamespacePackage.eINSTANCE));
        xmlNamespaceGenModel.setMainGenModel(this);
        xmlNamespaceGenModel.setImportManager(getImportManager());
        xmlNamespaceGenModel.setLanguage("en");
        xmlNamespaceGenPackage = xmlNamespaceGenModel.getGenPackages().get(0);
        xmlNamespaceGenPackage.setPrefix("XMLNamespace");
        xmlNamespaceGenPackage.setBasePackage("org.eclipse.emf.ecore.xml");
      }
      result = xmlNamespaceGenPackage;
    }
    else if (ePackage != null)
    {
      for (Iterator<GenPackage> pIter = getAllGenPackages().iterator(); pIter.hasNext() && result == null; )
      {
        GenPackage genPackage = pIter.next();
        result = findGenPackageHelper(genPackage, ePackage);
      }
    }

    ePackageToGenPackageMap.put(ePackage, result);

    return result;
  }

  Map<EClassifier, GenClassifier> eClassifierToGenClassifierMap;

  @Override
  protected GenClass findGenClass(EClass eClass)
  {
    if (eClassifierToGenClassifierMap == null)
    {
      eClassifierToGenClassifierMap = new HashMap<EClassifier, GenClassifier>();
    }
    else
    {
      GenClass result = (GenClass)eClassifierToGenClassifierMap.get(eClass);
      if (result != null)
      {
        return result;
      }
    }

    EPackage ePackage = eClass.getEPackage();
    GenPackage genPackage = findGenPackage(ePackage);
    if (genPackage != null)
    {
      EPackage targetEPackage = genPackage.getEcorePackage();
      EClassifier targetEClassifier = targetEPackage == ePackage ? eClass : targetEPackage.getEClassifier(eClass.getName());
      EList<GenClass> genClasses = genPackage.getGenClasses();
      GenClass[] genClassesData = (GenClass[])((BasicEList<GenClass>)genClasses).data();
      for (int i = 0, size = genClasses.size(); i < size; ++i)
      {
        GenClass genClass = genClassesData[i];
        if (targetEClassifier == genClass.getEcoreClass())
        {
          eClassifierToGenClassifierMap.put(eClass, genClass);
          return genClass;
        }
      }
    }
    return null;
  }

  @Override
  protected GenEnum findGenEnum(EEnum eEnum)
  {
    if (eClassifierToGenClassifierMap == null)
    {
      eClassifierToGenClassifierMap = new HashMap<EClassifier, GenClassifier>();
    }
    else
    {
      GenEnum result = (GenEnum)eClassifierToGenClassifierMap.get(eEnum);
      if (result != null)
      {
        return result;
      }
    }

    GenPackage genPackage = findGenPackage(eEnum.getEPackage());
    if (genPackage != null)
    {
      for (GenEnum genEnum : genPackage.getGenEnums())
      {
        if (eEnum.getName().equals(genEnum.getEcoreEnum().getName())) //FB TBD different objects for ecore model!
        {
          eClassifierToGenClassifierMap.put(eEnum, genEnum);
          return genEnum;
        }
      }
    }
    return null;
  }

  @Override
  protected GenDataType findGenDataType(EDataType eDataType)
  {
    if (eClassifierToGenClassifierMap == null)
    {
      eClassifierToGenClassifierMap = new HashMap<EClassifier, GenClassifier>();
    }
    else
    {
      GenDataType result = (GenDataType)eClassifierToGenClassifierMap.get(eDataType);
      if (result != null)
      {
        return result;
      }
    }

    GenPackage genPackage = findGenPackage(eDataType.getEPackage());
    if (genPackage != null)
    {
      for (GenDataType genDataType : genPackage.getGenDataTypes())
      {
        if (eDataType.getName().equals(genDataType.getEcoreDataType().getName())) //FB TBD different objects for ecore model!
        {
          eClassifierToGenClassifierMap.put(eDataType, genDataType);
          return genDataType;
        }
      }
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return GenModelPackage.Literals.GEN_MODEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCopyrightText()
  {
    return copyrightText;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCopyrightText(String newCopyrightText)
  {
    String oldCopyrightText = copyrightText;
    copyrightText = newCopyrightText;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__COPYRIGHT_TEXT, oldCopyrightText, copyrightText));
  }

  protected String getSourceFragmentPath()
  {
    return "src";
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getModelDirectory()
  {
    return modelDirectory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getModelDirectoryGen()
  {
    return modelDirectory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelDirectory(String newModelDirectory)
  {
    String oldModelDirectory = modelDirectory;
    modelDirectory = newModelDirectory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__MODEL_DIRECTORY, oldModelDirectory, modelDirectory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCreationCommands()
  {
    return creationCommands;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCreationCommands(boolean newCreationCommands)
  {
    boolean oldCreationCommands = creationCommands;
    creationCommands = newCreationCommands;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__CREATION_COMMANDS, oldCreationCommands, creationCommands));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCreationIcons()
  {
    return creationIcons;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCreationIcons(boolean newCreationIcons)
  {
    boolean oldCreationIcons = creationIcons;
    creationIcons = newCreationIcons;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__CREATION_ICONS, oldCreationIcons, creationIcons));
  }

  protected boolean canGenerate;

  /**
   * @deprecated In 2.5, access this via {@link #getImportManager()}, not directly. This may not be stored on every GenModel in later versions.
   */
  @Deprecated
  protected ImportManager importManager;

  /**
   * @deprecated In 2.5, this is stored in the {@link #getImportManager() import manager}.
   */
  @Deprecated
  protected StringBuffer importStringBuffer;

  /**
   * @deprecated In 2.5, this is stored in the {@link #getImportManager() import manager}.
   */
  @Deprecated
  protected int importInsertionPoint;

  public void markImportLocation(StringBuffer stringBuffer, GenPackage genPackage)
  {
    markImportLocation(stringBuffer);
    getImportManager().addJavaLangImports(genPackage.getJavaLangConflicts());
  }

  public void markImportLocation(StringBuffer stringBuffer)
  {
    importStringBuffer = stringBuffer;
    importInsertionPoint = stringBuffer.length();
    getImportManager().markImportLocation(stringBuffer);
  }

  public void emitSortedImports()
  {
    getImportManager().emitSortedImports();
  }

  public String getImportedName(String qualifiedName)
  {
    return getImportManager().getImportedName(qualifiedName, true);
  }

  public void addImport(String qualifiedName)
  {
    getImportManager().addImport(qualifiedName);
  }

  public void addPseudoImport(String qualifiedName)
  {
    getImportManager().addPseudoImport(qualifiedName);
  }

  protected static final ImportManager DEGENERATE_IMPORT_MANAGER =
    new ImportManager("")
    {
      @Override
      protected String basicGetImportedName(String qualifiedName, boolean autoImport)
      {
        return qualifiedName;
      }
    };

  @Override
  public ImportManager getImportManager()
  {
    return
      importManager == null ? DEGENERATE_IMPORT_MANAGER : importManager;
  }

  @Override
  public void setImportManager(ImportManager importManager)
  {
    // Avoid doing this cyclically.
    //
    if (this.importManager != importManager)
    {
      this.importManager = importManager;

      // We also need to set it on any GenModels holding any used or static packages that may be refered to.
      //
      for (GenPackage genPackage : getUsedGenPackages())
      {
        genPackage.getGenModel().setImportManager(importManager);
      }

      for (GenPackage genPackage : getStaticGenPackages())
      {
        genPackage.getGenModel().setImportManager(importManager);
      }

      // And we need to set it on any cached GenModels holding the special Ecore and XML packages.
      //
      GenPackage ecore = getEcoreGenPackage();
      if (ecore != null && ecore.getGenModel().getImportManager() != importManager)
      {
        ecore.getGenModel().setImportManager(importManager);
      }
      GenPackage xmlType = getXMLTypeGenPackage();
      if (xmlType != null && xmlType.getGenModel().getImportManager() != importManager)
      {
        xmlType.getGenModel().setImportManager(importManager);
      }
      GenPackage xmlNamespace = getXMLNamespaceGenPackage();
      if (xmlNamespace != null && xmlNamespace.getGenModel().getImportManager() != importManager)
      {
        xmlNamespace.getGenModel().setImportManager(importManager);
      }
    }
  }

  /**
   * @deprecated In 2.5, access this via {@link #getLineDelimiter()}, not directly. This may not be stored on every GenModel in later versions.
   */
  @Deprecated
  protected String lineDelimiter;

  public String getLineDelimiter()
  {
    return lineDelimiter == null ? System.getProperty("line.separator") : lineDelimiter;
  }

  public void setLineDelimiter(String lineDelimiter)
  {
    // Avoid cycles
    //
    if (this.lineDelimiter != lineDelimiter)
    {
      this.lineDelimiter = lineDelimiter;
      if (importManager != null)
      {
        importManager.setLineDelimiter(lineDelimiter);
      }

      // We also need to set it on any GenModels holding any used or static packages that may be refered to.
      //
      for (GenPackage genPackage : getUsedGenPackages())
      {
        genPackage.getGenModel().setLineDelimiter(lineDelimiter);
      }

      for (GenPackage genPackage : getStaticGenPackages())
      {
        genPackage.getGenModel().setLineDelimiter(lineDelimiter);
      }

      // There was previously code intended to set it on the cached GenModels holding the special Ecore and XML packages,
      // but it erroneously set the import manager. So, it seems that was not necessary.
    }
  }

  public String getDriverNumber()
  {
    return null;
  }

  public String getDate()
  {
    return null;
  }

  public String getNonNLS()
  {
    return isNonNLSMarkers() ? " //$NON-NLS-1$" : "";
  }

  public String getNonNLS(int i)
  {
    if (isNonNLSMarkers())
    {
      StringBuffer result = new StringBuffer(15);
      result.append(" //$NON-NLS-");
      result.append(i);
      result.append('$');
      return result.toString();
    }
    return "";
  }

  public String getNonNLS(String s)
  {
    return getNonNLS(s, 1);
  }

  public String getNonNLS(String s, int i)
  {
    if (s == null) return "";

    StringBuffer result = new StringBuffer(15);
    boolean openQuote = true;
    int j = s.indexOf('\"');

    while (j != -1)
    {
      if (openQuote) result.append(getNonNLS(i++));
      openQuote = !openQuote;
      j = s.indexOf('\"', j + 1);
    }
    return result.toString();
  }

  public void initialize(Collection<? extends EPackage> ePackages)
  {
    setCopyrightFields(false);
    LOOP:
    for (EPackage ePackage : ePackages)
    {
      for (GenPackage genPackage : getGenPackages())
      {
        if (genPackage.getEcorePackage() == ePackage)
        {
          genPackage.initialize(ePackage);
          continue LOOP;
        }
      }

      GenPackage genPackage = createGenPackage();
      getGenPackages().add(genPackage);
      genPackage.initialize(ePackage);
    }
  }

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String jControlModelName = "emf-merge.xml";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String interfaceTemplateName = "model/Interface.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String classTemplateName = "model/Class.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String enumClassTemplateName = "model/EnumClass.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String packageClassTemplateName = "model/PackageClass.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String factoryInterfaceTemplateName = "model/FactoryInterface.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String factoryClassTemplateName = "model/FactoryClass.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String adapterFactoryClassTemplateName = "model/AdapterFactoryClass.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String switchClassTemplateName = "model/SwitchClass.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String validatorSwitchClassTemplateName = "model/ValidatorClass.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String xmlProcessorClassTemplateName = "model/XMLProcessorClass.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String pluginXMLTemplateName = "model/plugin.xmljet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String manifestMFTemplateName = "model/manifest.mfjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String pluginPropertiesTemplateName = "model/plugin.propertiesjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String buildPropertiesTemplateName = "model/build.propertiesjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String modelPluginTemplateName = "model/Plugin.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String resourceTemplateName = "model/ResourceClass.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String resourceFactoryTemplateName = "model/ResourceFactoryClass.javajet";


  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String [] templatePath = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JControlModel jControlModel = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter interfaceEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter classEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter enumClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter packageInterfaceEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter packageClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter factoryInterfaceEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter factoryClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter adapterFactoryClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter switchClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter validatorSwitchClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter xmlProcessorClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter pluginXMLEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter manifestMFEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter pluginPropertiesEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter buildPropertiesEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter modelPluginClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter resourceClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter resourceFactoryClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.Generator.Options Generator.Options} provides an
   * equivalent way to obtain this information. This method will be removed after 2.2.
   */
  @Deprecated
  protected String [] getTemplatePath()
  {
    if (templatePath == null)
    {
      templatePath = new String[2];
    }
    String theTemplateDirectory = getTemplateDirectory();
    templatePath[0] =
      !isDynamicTemplates() ?
        null :
        theTemplateDirectory != null && theTemplateDirectory.indexOf(":") == -1 ?
          URI.createPlatformResourceURI(theTemplateDirectory).toString() :
          theTemplateDirectory;
    templatePath[1] =  CodeGenEcorePlugin.INSTANCE.getBaseURL().toString() + "templates";
    return templatePath;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.Generator.Options Generator.Options} provides an
   * equivalent way to obtain this information. This method will be removed after 2.2.
   */
  @Deprecated
  public String getMergeRulesLocation()
  {
    return JETCompiler.find(getTemplatePath(), jControlModelName);
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} provides an equivalent to this method.
   * This method will be removed after 2.2.
   */
  @Deprecated
  public JControlModel getJControlModel()
  {
    if (jControlModel == null)
    {
      jControlModel = new JControlModel();
    }
    return jControlModel;
  }

  /**
   * @deprecated in EMF 2.2. This field will be removed after 2.2.
   */
  @Deprecated
  public static final Class<?>[] OBJECT_ARGUMENT = new Class[]{ Object.class };

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public void setMethod(JETEmitter jetEmitter, String className)
  {
    if (!isDynamicTemplates())
    {
      try
      {
        Class<?> emitterClass = getClass().getClassLoader().loadClass(className);
        Method emitterMethod = emitterClass.getDeclaredMethod("generate", OBJECT_ARGUMENT);
        jetEmitter.setMethod(emitterMethod);
      }
      catch (Exception exception)
      {
        // It's okay for there not be a precompiled template, so fail quietly.
        // exception.printStackTrace();
      }
    }
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter createJETEmitter(String relativeTemplateURI)
  {
    JETEmitter jetEmitter =
      new JETEmitter(getTemplatePath(), relativeTemplateURI, getClass().getClassLoader())
      {
        @Override
        public void initialize(Monitor progressMonitor) throws JETException
        {
          if (getClasspathEntries().isEmpty())
          {
            addClasspathEntries(this);
          }
          super.initialize(progressMonitor);
        }
      };
    return jetEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  protected void addClasspathEntries(JETEmitter jetEmitter) throws JETException
  {
    jetEmitter.addVariable("EMF_CODEGEN", "org.eclipse.emf.codegen");
    jetEmitter.addVariable("EMF_CODEGEN_ECORE", "org.eclipse.emf.codegen.ecore");
    jetEmitter.addVariable("EMF_COMMON", "org.eclipse.emf.common");
    jetEmitter.addVariable("EMF_ECORE", "org.eclipse.emf.ecore");
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getClassEmitter()
  {
    if (classEmitter == null)
    {
      classEmitter = createJETEmitter(classTemplateName);
      setMethod(classEmitter, "org.eclipse.emf.codegen.ecore.templates.model.Class");
    }
    return classEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEnumClassEmitter()
  {
    if (enumClassEmitter == null)
    {
      enumClassEmitter = createJETEmitter(enumClassTemplateName);
      setMethod(enumClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.EnumClass");
    }
    return enumClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getFactoryClassEmitter()
  {
    if (factoryClassEmitter == null)
    {
      factoryClassEmitter = createJETEmitter(factoryClassTemplateName);
      setMethod(factoryClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.FactoryClass");
    }
    return factoryClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getPackageClassEmitter()
  {
    if (packageClassEmitter == null)
    {
      packageClassEmitter = createJETEmitter(packageClassTemplateName);
      setMethod(packageClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.PackageClass");
    }
    return packageClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getAdapterFactoryClassEmitter()
  {
    if (adapterFactoryClassEmitter == null)
    {
      adapterFactoryClassEmitter = createJETEmitter(adapterFactoryClassTemplateName);
      setMethod(adapterFactoryClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.AdapterFactoryClass");
    }
    return adapterFactoryClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getSwitchClassEmitter()
  {
    if (switchClassEmitter == null)
    {
      switchClassEmitter = createJETEmitter(switchClassTemplateName);
      setMethod(switchClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.SwitchClass");
    }
    return switchClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getValidatorClassEmitter()
  {
    if (validatorSwitchClassEmitter == null)
    {
      validatorSwitchClassEmitter = createJETEmitter(validatorSwitchClassTemplateName);
      setMethod(validatorSwitchClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.ValidatorClass");
    }
    return validatorSwitchClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getXMLProcessorClassEmitter()
  {
    if (xmlProcessorClassEmitter == null)
    {
      xmlProcessorClassEmitter = createJETEmitter(xmlProcessorClassTemplateName);
      setMethod(xmlProcessorClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.XMLProcessorClass");
    }
    return xmlProcessorClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getPluginXMLEmitter()
  {
    if (pluginXMLEmitter == null)
    {
      pluginXMLEmitter = createJETEmitter(pluginXMLTemplateName);
      setMethod(pluginXMLEmitter, "org.eclipse.emf.codegen.ecore.templates.model.PluginXML");
    }
    return pluginXMLEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getManifestMFEmitter()
  {
    if (manifestMFEmitter == null)
    {
      manifestMFEmitter = createJETEmitter(manifestMFTemplateName);
      setMethod(manifestMFEmitter, "org.eclipse.emf.codegen.ecore.templates.model.ManifestMF");
    }
    return manifestMFEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getPluginPropertiesEmitter()
  {
    if (pluginPropertiesEmitter == null)
    {
      pluginPropertiesEmitter = createJETEmitter(pluginPropertiesTemplateName);
      setMethod(pluginPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.model.PluginProperties");
    }
    return pluginPropertiesEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getBuildPropertiesEmitter()
  {
    if (buildPropertiesEmitter == null)
    {
      buildPropertiesEmitter = createJETEmitter(buildPropertiesTemplateName);
      setMethod(buildPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.model.BuildProperties");
    }
    return buildPropertiesEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getModelPluginClassEmitter()
  {
    if (modelPluginClassEmitter == null)
    {
      modelPluginClassEmitter = createJETEmitter(modelPluginTemplateName);
      setMethod(modelPluginClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.Plugin");
    }
    return modelPluginClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getResourceClassEmitter()
  {
    if (resourceClassEmitter == null)
    {
      resourceClassEmitter = createJETEmitter(resourceTemplateName);
      setMethod(resourceClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.ResourceClass");
    }
    return resourceClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getResourceFactoryClassEmitter()
  {
    if (resourceFactoryClassEmitter == null)
    {
      resourceFactoryClassEmitter = createJETEmitter(resourceFactoryTemplateName);
      setMethod(resourceFactoryClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.ResourceFactoryClass");
    }
    return resourceFactoryClassEmitter;
  }

  protected boolean hasModelSupport()
  {
    return !isBlank(getModelDirectory());
    // Don't care about plugin class; we can generate a model without one.
  }

  @Override
  public boolean canGenerate()
  {
    return canGenerate && hasModelSupport();
  }

  public void setCanGenerate(boolean canGenerate)
  {
    this.canGenerate = canGenerate;
  }

  public IStatus validate()
  {
    return BasicDiagnostic.toIStatus(diagnose());
  }

  public boolean isValidateModel()
  {
    return validateModel;
  }

  public void setValidateModel(boolean validateModel)
  {
    this.validateModel = validateModel;
  }

  public Diagnostic diagnose()
  {
    BasicDiagnostic status =
      new BasicDiagnostic
        (CodeGenEcorePlugin.INSTANCE.getSymbolicName(),
         0,
         CodeGenEcorePlugin.INSTANCE.getString("_UI_ProblemsEncounteredInTheModel_message"),
         null);

    List<EObject> all = new ArrayList<EObject>(getGenPackages());
    all.addAll(getUsedGenPackages());
    for (EObject eObject : new ArrayList<EObject>(all))
    {
      EObject root = EcoreUtil.getRootContainer(((GenPackage)eObject).getEcorePackage());
      if (!all.contains(root))
      {
        all.add(root);
      }
    }

    all.addAll(getGenAnnotations());
    Map<EObject, Collection<EStructuralFeature.Setting>> map = EcoreUtil.UnresolvedProxyCrossReferencer.find(all);
    if (!map.isEmpty())
    {
      for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : map.entrySet())
      {
        EObject unresolvedProxy = entry.getKey();
        BasicDiagnostic nestedStatus =
          new BasicDiagnostic
            (CodeGenEcorePlugin.INSTANCE.getSymbolicName(),
             0,
             CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_UnableToResolveProxy_message", new Object [] { EcoreUtil.getURI(unresolvedProxy) }),
             null);

        for (EStructuralFeature.Setting setting : entry.getValue())
        {
          if (!setting.getEStructuralFeature().isDerived())
          {
            nestedStatus.add
              (new BasicDiagnostic
                (Diagnostic.ERROR,
                    CodeGenEcorePlugin.INSTANCE.getSymbolicName(),
                 0,
                 CodeGenEcorePlugin.INSTANCE.getString
                   ("_UI_ItsUseIsBy_message", new Object [] { EcoreUtil.getURI(setting.getEObject()) }),
                 null));
          }
        }

        status.add(nestedStatus);
      }
    }
    else
    {
      List<EPackage> referencedEPackages = new UniqueEList<EPackage>();
      for (EObject object : all)
      {
        if (object instanceof EPackage)
        {
          EPackage ePackage = (EPackage)object;
          for (Iterator<EObject> j = ePackage.eAllContents(); j.hasNext();)
          {
            EObject eObject = j.next();
            for (EObject o : eObject.eCrossReferences())
            {
              if (o instanceof EClassifier)
              {
                EClassifier eClassifier = (EClassifier)o;
                referencedEPackages.add(eClassifier.getEPackage());
              }
            }
          }
        }
      }

      for (EPackage ePackage : referencedEPackages)
      {
        GenPackage genPackage = findGenPackage(ePackage);
        if (genPackage == null)
        {
          status.add
            (new BasicDiagnostic
              (Diagnostic.ERROR,
                  CodeGenEcorePlugin.INSTANCE.getSymbolicName(),
               0,
               CodeGenEcorePlugin.INSTANCE.getString("_UI_ThePackageIsNeeded_message", new Object [] { EcoreUtil.getURI(ePackage) }),
               null));
        }
        else if (genPackage.getEcorePackage() != ePackage)
        {
          status.add
            (new BasicDiagnostic
              (Diagnostic.ERROR,
                  CodeGenEcorePlugin.INSTANCE.getSymbolicName(),
               0,
               CodeGenEcorePlugin.INSTANCE.getString
                 ("_UI_ThePackageHasTheSameNamespaceURI",
                  new Object [] { EcoreUtil.getURI(ePackage), ePackage.getNsURI(), EcoreUtil.getURI(genPackage.getEcorePackage()) }),
               null));
        }
      }

      if (isValidateModel())
      {
        for (GenPackage genPackage : getAllGenPackages())
        {
          EPackage ePackage = genPackage.getEcorePackage();
          if (ePackage != null)
          {
            Diagnostician diagnostician =
              new Diagnostician(EValidator.Registry.INSTANCE)
              {
                @Override
                public String getObjectLabel(EObject object)
                {
                  return EcoreUtil.getURI(object).toString();
                }
              };
            Diagnostic diagnostic = diagnostician.validate(ePackage);
            if (diagnostic.getSeverity() == Diagnostic.ERROR)
            {
              status.addAll(diagnostic);
            }
            else if (diagnostic.getSeverity() == Diagnostic.WARNING)
            {
              // If there are any warnings other than raw type warnings, include all the warnings.
              //
              for (Diagnostic child : diagnostic.getChildren())
              {
                if (!EcoreValidator.DIAGNOSTIC_SOURCE.equals(child.getSource()) ||
                      child.getCode() != EcoreValidator.CONSISTENT_ARGUMENTS_NONE)
                {
                  status.addAll(diagnostic);
                  break;
                }
              }
            }
          }
        }
      }
    }

    return status;
  }

  protected ExtendedMetaData extendedMetaData;

  @Override
  public ExtendedMetaData getExtendedMetaData()
  {
    if (extendedMetaData == null)
    {
      extendedMetaData =
        new BasicExtendedMetaData
          (eResource() == null || eResource().getResourceSet() == null ?
             new EPackageRegistryImpl(EPackage.Registry.INSTANCE) :
             eResource().getResourceSet().getPackageRegistry());
      populateExtendedMetaData(getGenPackages());
      populateExtendedMetaData(getStaticGenPackages());
      populateExtendedMetaData(getUsedGenPackages());

      if (extendedMetaData.getPackage(ExtendedMetaData.XML_SCHEMA_URI) == null)
      {
        extendedMetaData.putPackage(ExtendedMetaData.XML_SCHEMA_URI, extendedMetaData.getPackage(XMLTypePackage.eNS_URI));
      }
    }
    return extendedMetaData;
  }

  protected void populateExtendedMetaData(List<GenPackage> genPackages)
  {
    for (GenPackage genPackage : genPackages)
    {
      EPackage ePackage = genPackage.getEcorePackage();
      if (ePackage != null)  // genPackage can be a broken proxy
      {
        if (!EcorePackage.eNS_URI.equals(ePackage.getNsURI()) && ! GenModelPackage.eNS_URI.equals(ePackage.getNsURI()))
        {
          extendedMetaData.putPackage(extendedMetaData.getNamespace(ePackage), ePackage);
        }
        populateExtendedMetaData(genPackage.getSubGenPackages());
      }
    }
  }

  public boolean hasPluginSupport()
  {
    return !isBlank(getModelPluginID());
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @SuppressWarnings("rawtypes")
  @Override
  @Deprecated
  public void generate(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerate()) return;

      getStaticGenPackages();

      progressMonitor.beginTask("", getGenPackages().size() + 3);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingPackages_message"));

      // Force the project to be setup properly.
      //
      if (isUpdateClasspath() && EMFPlugin.IS_ECLIPSE_RUNNING)
      {
        EclipseUtil.findOrCreateContainer
          ((GenModel)this,
           createMonitor(progressMonitor, 1),
           Generator.EMF_MODEL_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getModelDirectory(),
           true);
      }

      for (Iterator iter = getGenPackages().iterator(); iter.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)iter.next();
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaPackage_message", new Object [] { genPackage.getInterfacePackageName() }));
        genPackage.generate(createMonitor(progressMonitor, 1));
      }

      if (hasModelPluginClass())
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedModelPluginClassName() }));
        generate
          (createMonitor(progressMonitor, 1),
           Generator.EMF_MODEL_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getModelDirectory(),
           getModelPluginPackageName(),
           getModelPluginClassName(),
           getModelPluginClassEmitter());
      }

      if (hasPluginSupport())
      {
        if (!sameModelEditProject() && !sameModelEditorProject())
        {
          if (isBundleManifest())
          {
            progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelManifestMF_message"));
            generate
              (createMonitor(progressMonitor, 1),
               Generator.EMF_MODEL_PROJECT_STYLE,
               getEffectiveModelPluginVariables(),
               getModelProjectDirectory() + "/META-INF/MANIFEST.MF",
               getManifestMFEmitter());
          }

          progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelPluginXML_message"));
          generate
            (createMonitor(progressMonitor, 1),
             Generator.EMF_MODEL_PROJECT_STYLE,
             getEffectiveModelPluginVariables(),
             getModelProjectDirectory() + "/plugin.xml",
             getPluginXMLEmitter());
        }

        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelPluginProperties_message"));
        generate
          (createMonitor(progressMonitor, 1),
           Generator.EMF_MODEL_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getModelProjectDirectory() + "/plugin.properties",
           getPluginPropertiesEmitter());

        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelBuildProperties_message"));
        generate
          (createMonitor(progressMonitor, 1),
           Generator.EMF_MODEL_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getModelProjectDirectory() + "/build.properties",
           getBuildPropertiesEmitter());
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }

  public boolean hasEditSupport()
  {
    return
      hasPluginSupport() &&
      !isBlank(getEditPluginClassToUse()) &&
      !isBlank(getEditDirectory());
  }

  @Override
  public boolean canGenerateEdit()
  {
    return canGenerate && hasEditSupport();
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @SuppressWarnings("rawtypes")
  @Override
  @Deprecated
  public void generateEdit(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerateEdit()) return;

      getStaticGenPackages();

      progressMonitor.beginTask("", getGenPackages().size() + 1);

      // Force the project to be setup properly.
      //
      if (EMFPlugin.IS_ECLIPSE_RUNNING && isUpdateClasspath())
      {
        EclipseUtil.findOrCreateContainer
          ((GenModel)this,
           createMonitor(progressMonitor, 1),
           Generator.EMF_EDIT_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getEditDirectory(),
           true);
      }

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditPackages_message"));
      for (Iterator iter = getGenPackages().iterator(); iter.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)iter.next();
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaPackage_message", new Object [] { genPackage.getInterfacePackageName() }));
        genPackage.generateEdit(createMonitor(progressMonitor, 1));
      }

      if (!sameEditEditorProject())
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedEditPluginClassName() }));
        generate
          (createMonitor(progressMonitor, 1),
           Generator.EMF_EDIT_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getEditPluginDirectory(),
           getEditPluginPackageName(),
           getEditPluginClassName(),
           getEditPluginClassEmitter());

        if (isBundleManifest())
        {
          progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditManifestMF_message"));
          generate
            (createMonitor(progressMonitor, 1),
             Generator.EMF_EDIT_PROJECT_STYLE,
             getEffectiveModelPluginVariables(),
             getEditProjectDirectory() + "/META-INF/MANIFEST.MF",
             getEditManifestMFEmitter());
        }

        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditPluginXML_message"));
        generate
          (createMonitor(progressMonitor, 1),
           Generator.EMF_EDIT_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getEditProjectDirectory() + "/plugin.xml",
           getEditPluginXMLEmitter());
      }

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditPluginProperties_message"));
      generate
        (createMonitor(progressMonitor, 1),
         Generator.EMF_EDIT_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditProjectDirectory() + "/plugin.properties",
         getEditPluginPropertiesEmitter());

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditBuildProperties_message"));
      generate
        (createMonitor(progressMonitor, 1),
         Generator.EMF_EDIT_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditProjectDirectory() + "/build.properties",
         getEditBuildPropertiesEmitter());
    }
    finally
    {
      progressMonitor.done();
    }
  }

  public boolean hasEditorSupport()
  {
    return
      hasPluginSupport() &&
      !isBlank(getEditorPluginClassToUse()) &&
      !isBlank(getEditorDirectory());
  }

  @Override
  public boolean canGenerateEditor()
  {
    return canGenerate && hasEditorSupport();
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @SuppressWarnings("rawtypes")
  @Override
  @Deprecated
  public void generateEditor(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerateEditor()) return;

      getStaticGenPackages();

      progressMonitor.beginTask("", getGenPackages().size() + 1);

      // Force the project to be setup properly.
      //
      if (EMFPlugin.IS_ECLIPSE_RUNNING && isUpdateClasspath())
      {
        EclipseUtil.findOrCreateContainer
          ((GenModel)this,
           createMonitor(progressMonitor, 1),
           Generator.EMF_EDITOR_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getEditorDirectory(),
           true);
      }

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorPackages_message"));
      for (Iterator iter = getGenPackages().iterator(); iter.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)iter.next();
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaPackage_message", new Object [] { genPackage.getInterfacePackageName() }));
        genPackage.generateEditor(createMonitor(progressMonitor, 1));
      }

      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedEditorPluginClassName() }));
      generate
        (createMonitor(progressMonitor, 1),
         Generator.EMF_EDITOR_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditorPluginDirectory(),
         getEditorPluginPackageName(),
         getEditorPluginClassName(),
         getEditorPluginClassEmitter());

      if (isBundleManifest())
      {
        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorManifestMF_message"));
        generate
          (createMonitor(progressMonitor, 1),
           Generator.EMF_EDITOR_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getEditorProjectDirectory() + "/META-INF/MANIFEST.MF",
           getEditorManifestMFEmitter());
      }

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorPluginXML_message"));
      generate
        (createMonitor(progressMonitor, 1),
         Generator.EMF_EDITOR_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditorProjectDirectory() + "/plugin.xml",
         getEditorPluginXMLEmitter());

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorPluginProperties_message"));
      generate
        (createMonitor(progressMonitor, 1),
         Generator.EMF_EDITOR_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditorProjectDirectory() + "/plugin.properties",
         getEditorPluginPropertiesEmitter());

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorBuildProperties_message"));
      generate
        (createMonitor(progressMonitor, 1),
         Generator.EMF_EDITOR_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditorProjectDirectory() + "/build.properties",
         getEditorBuildPropertiesEmitter());

      if (getGenModel().isRichClientPlatform())
      {
        progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedEditorAdvisorClassName() }));
      generate
        (createMonitor(progressMonitor, 1),
         Generator.EMF_EDITOR_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditorPluginDirectory(),
         getEditorPluginPackageName(),
         getEditorAdvisorClassName(),
         getEditorAdvisorEmitter());
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Override
  @Deprecated
  public boolean canGenerateSchema()
  {
    return canGenerate();
  }

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @SuppressWarnings("rawtypes")
  @Override
  @Deprecated
  public void generateSchema(Monitor progressMonitor)
  {
    for (Iterator i = getGenPackages().iterator(); i.hasNext();)
    {
      ((GenPackage)i.next()).generateSchema(createMonitor(progressMonitor, 1));
    }
  }

  public boolean hasTestSupport()
  {
    return hasModelSupport() && hasPluginSupport() && !isBlank(getTestsDirectory());
  }

  @Override
  public boolean canGenerateTests()
  {
    return canGenerate && hasTestSupport();
  }

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @SuppressWarnings("rawtypes")
  @Override
  @Deprecated
  public void generateTests(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerateTests())
        return;

      progressMonitor.beginTask("", getGenPackages().size() + 4);

      if (isUpdateClasspath() && EMFPlugin.IS_ECLIPSE_RUNNING)
      {
        EclipseUtil.findOrCreateContainer
          ((GenModel)this,
           createMonitor(progressMonitor, 1),
           Generator.EMF_TESTS_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getTestsDirectory(),
           true);
      }

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsPackages_message"));
      for (Iterator genPackages = getGenPackages().iterator(); genPackages.hasNext();)
      {
        GenPackage genPackage = (GenPackage)genPackages.next();
        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString(
          "_UI_GeneratingJavaPackage_message",
          new Object []{ genPackage.getTestsPackageName() }));
        genPackage.generateTests(createMonitor(progressMonitor, 1));
      }

      if (!isBlank(getTestSuiteClass()))
      {
        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString(
          "_UI_GeneratingJavaClass_message",
          new Object []{ getQualifiedTestSuiteClassName() }));
        generate(
          createMonitor(progressMonitor, 1),
          Generator.EMF_TESTS_PROJECT_STYLE,
          getEffectiveModelPluginVariables(),
          getTestsDirectory(),
          getTestSuitePackageName(),
          getTestSuiteClassName(),
          getModelTestSuiteEmitter());
      }

      if (!sameModelTestsProject())
      {
        if (isBundleManifest())
        {
          progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsManifestMF_message"));
          generate
            (createMonitor(progressMonitor, 1),
             Generator.EMF_TESTS_PROJECT_STYLE,
             getEffectiveModelPluginVariables(),
             getTestsProjectDirectory() + "/META-INF/MANIFEST.MF",
             getTestsManifestMFEmitter());
        }

        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsPluginXML_message"));
        generate(
          createMonitor(progressMonitor, 1),
          Generator.EMF_TESTS_PROJECT_STYLE,
          getEffectiveModelPluginVariables(),
          getTestsProjectDirectory() + "/plugin.xml",
          getTestsPluginXMLEmitter());

        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsPluginProperties_message"));
        generate(
          createMonitor(progressMonitor, 1),
          Generator.EMF_TESTS_PROJECT_STYLE,
          getEffectiveModelPluginVariables(),
          getTestsProjectDirectory() + "/plugin.properties",
          getTestsPluginPropertiesEmitter());

        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsBuildProperties_message"));
        generate(
          createMonitor(progressMonitor, 1),
          Generator.EMF_TESTS_PROJECT_STYLE,
          getEffectiveModelPluginVariables(),
          getTestsProjectDirectory() + "/build.properties",
          getTestsBuildPropertiesEmitter());
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }

  //
  // EMFEdit generation
  //

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String itemProviderTemplateName = "edit/ItemProvider.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String itemProviderAdapterFactoryTemplateName = "edit/ItemProviderAdapterFactory.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editPluginTemplateName = "edit/Plugin.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editPluginXMLTemplateName = "edit/plugin.xmljet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editManifestMFTemplateName = "edit/manifest.mfjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editPluginPropertiesTemplateName = "edit/plugin.propertiesjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editBuildPropertiesTemplateName = "edit/build.propertiesjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String itemGIFName = "edit/Item.gif";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String createChildGIFName = "edit/CreateChild.gif";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editorTemplateName = "editor/Editor.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String actionBarContributorTemplateName = "editor/ActionBarContributor.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String modelWizardTemplateName = "editor/ModelWizard.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String advisorTemplateName = "editor/Advisor.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editorPluginTemplateName = "editor/Plugin.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editorPluginXMLTemplateName = "editor/plugin.xmljet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editorManifestMFTemplateName = "editor/manifest.mfjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editorPluginPropertiesTemplateName = "editor/plugin.propertiesjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String editorBuildPropertiesTemplateName = "editor/build.propertiesjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String modelGIFName = "editor/ModelFile.gif";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String modelWizardGIFName = "editor/NewModel.gif";


  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter itemProviderEmitter = null;
 //   protected JETEmitter extendedItemProviderEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter itemProviderAdapterFactoryEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editPluginClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editPluginXMLEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editManifestMFEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editPluginPropertiesEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editBuildPropertiesEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected GIFEmitter itemGIFEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected GIFEmitter createChildGIFEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editorEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter actionBarContributorEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter modelWizardEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter advisorEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editorPluginClassEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editorManifestMFEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editorPluginXMLEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editorPluginPropertiesEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter editorBuildPropertiesEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected GIFEmitter modelGIFEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected GIFEmitter modelWizardGIFEmitter = null;

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getItemProviderEmitter()
  {
    if (itemProviderEmitter == null)
    {
      itemProviderEmitter = createJETEmitter(itemProviderTemplateName);
      setMethod(itemProviderEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.ItemProvider");
    }
    return itemProviderEmitter;
  }

 //   public JETEmitter getExtendedItemProviderEmitter()
 //   {
 //     if (extendedItemProviderEmitter == null)
 //     {
 //       extendedItemProviderEmitter = createJETEmitter(extendedItemProviderTemplateName);
 //       setMethod(extendedItemProviderEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.ExtendedItemProvider");
 //     }
 //     return extendedItemProviderEmitter;
 //   }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getItemProviderAdapterFactoryEmitter()
  {
    if (itemProviderAdapterFactoryEmitter == null)
    {
      itemProviderAdapterFactoryEmitter = createJETEmitter(itemProviderAdapterFactoryTemplateName);
      setMethod(itemProviderAdapterFactoryEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.ItemProviderAdapterFactory");
    }
    return itemProviderAdapterFactoryEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditPluginClassEmitter()
  {
    if (editPluginClassEmitter == null)
    {
      editPluginClassEmitter = createJETEmitter(editPluginTemplateName);
      setMethod(editPluginClassEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.Plugin");
    }
    return editPluginClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditPluginXMLEmitter()
  {
    if (editPluginXMLEmitter == null)
    {
      editPluginXMLEmitter = createJETEmitter(editPluginXMLTemplateName);
      setMethod(editPluginXMLEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.PluginXML");
    }
    return editPluginXMLEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditManifestMFEmitter()
  {
    if (editManifestMFEmitter == null)
    {
      editManifestMFEmitter = createJETEmitter(editManifestMFTemplateName);
      setMethod(editManifestMFEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.ManifestMF");
    }

    return editManifestMFEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditPluginPropertiesEmitter()
  {
    if (editPluginPropertiesEmitter == null)
    {
      editPluginPropertiesEmitter = createJETEmitter(editPluginPropertiesTemplateName);
      setMethod(editPluginPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.PluginProperties");
    }
    return editPluginPropertiesEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditBuildPropertiesEmitter()
  {
    if (editBuildPropertiesEmitter == null)
    {
      editBuildPropertiesEmitter = createJETEmitter(editBuildPropertiesTemplateName);
      setMethod(editBuildPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.BuildProperties");
    }
    return editBuildPropertiesEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public GIFEmitter getItemGIFEmitter()
  {
    if (itemGIFEmitter == null)
    {
      itemGIFEmitter = new GIFEmitter(JETCompiler.find(getTemplatePath(), itemGIFName));
    }
    return itemGIFEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public GIFEmitter getCreateChildGIFEmitter()
  {
    if (createChildGIFEmitter == null)
    {
      createChildGIFEmitter = new GIFEmitter(JETCompiler.find(getTemplatePath(), createChildGIFName));
    }
    return createChildGIFEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public GIFEmitter getModelGIFEmitter()
  {
    if (modelGIFEmitter == null)
    {
      modelGIFEmitter = new GIFEmitter(JETCompiler.find(getTemplatePath(), modelGIFName));
    }
    return modelGIFEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public GIFEmitter getModelWizardGIFEmitter()
  {
    if (modelWizardGIFEmitter == null)
    {
      modelWizardGIFEmitter = new GIFEmitter(JETCompiler.find(getTemplatePath(), modelWizardGIFName));
    }
    return modelWizardGIFEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditorEmitter()
  {
    if (editorEmitter == null)
    {
      editorEmitter = createJETEmitter(editorTemplateName);
      setMethod(editorEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.Editor");
    }
    return editorEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getActionBarContributorEmitter()
  {
    if (actionBarContributorEmitter == null)
    {
      actionBarContributorEmitter = createJETEmitter(actionBarContributorTemplateName);
      setMethod(actionBarContributorEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.ActionBarContributor");
    }
    return actionBarContributorEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getModelWizardEmitter()
  {
    if (modelWizardEmitter == null)
    {
      modelWizardEmitter = createJETEmitter(modelWizardTemplateName);
      setMethod(modelWizardEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.ModelWizard");
    }
    return modelWizardEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditorAdvisorEmitter()
  {
    if (advisorEmitter == null)
    {
      advisorEmitter = createJETEmitter(advisorTemplateName);
      setMethod(advisorEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.Advisor");
    }
    return advisorEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditorPluginClassEmitter()
  {
    if (editorPluginClassEmitter == null)
    {
      editorPluginClassEmitter = createJETEmitter(editorPluginTemplateName);
      setMethod(editorPluginClassEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.Plugin");
    }
    return editorPluginClassEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditorPluginXMLEmitter()
  {
    if (editorPluginXMLEmitter == null)
    {
      editorPluginXMLEmitter = createJETEmitter(editorPluginXMLTemplateName);
      setMethod(editorPluginXMLEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.PluginXML");
    }
    return editorPluginXMLEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditorManifestMFEmitter()
  {
    if (editorManifestMFEmitter == null)
    {
      editorManifestMFEmitter = createJETEmitter(editorManifestMFTemplateName);
      setMethod(editorManifestMFEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.ManifestMF");
    }
    return editorManifestMFEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditorPluginPropertiesEmitter()
  {
    if (editorPluginPropertiesEmitter == null)
    {
      editorPluginPropertiesEmitter = createJETEmitter(editorPluginPropertiesTemplateName);
      setMethod(editorPluginPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.PluginProperties");
    }
    return editorPluginPropertiesEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getEditorBuildPropertiesEmitter()
  {
    if (editorBuildPropertiesEmitter == null)
    {
      editorBuildPropertiesEmitter = createJETEmitter(editorBuildPropertiesTemplateName);
      setMethod(editorBuildPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.BuildProperties");
    }
    return editorBuildPropertiesEmitter;
  }

  //
  // Tests generation
  //

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String testCaseTemplateName = "model.tests/TestCase.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String modelTestSuiteTemplateName = "model.tests/ModelTestSuite.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String packageTestSuiteTemplateName = "model.tests/PackageTestSuite.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String packageExampleTemplateName = "model.tests/PackageExample.javajet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String testsPluginXMLTemplateName = "model.tests/plugin.xmljet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String testsManifestMFTemplateName = "model.tests/manifest.mfjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String testsPluginPropertiesTemplateName = "model.tests/plugin.propertiesjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected String testsBuildPropertiesTemplateName = "model.tests/build.propertiesjet";

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter testCaseEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter modelTestSuiteEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter packageTestSuiteEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter packageExampleEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter testsPluginXMLEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter testsManifestMFEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter testsPluginPropertiesEmitter = null;

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @Deprecated
  protected JETEmitter testsBuildPropertiesEmitter = null;

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getTestCaseEmitter()
  {
    if (testCaseEmitter == null)
    {
      testCaseEmitter = createJETEmitter(testCaseTemplateName);
      setMethod(testCaseEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.TestCase");
    }

    return testCaseEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getModelTestSuiteEmitter()
  {
    if (modelTestSuiteEmitter == null)
    {
      modelTestSuiteEmitter = createJETEmitter(modelTestSuiteTemplateName);
      setMethod(modelTestSuiteEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.ModelTestSuite");
    }

    return modelTestSuiteEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getPackageTestSuiteEmitter()
  {
    if (packageTestSuiteEmitter == null)
    {
      packageTestSuiteEmitter = createJETEmitter(packageTestSuiteTemplateName);
      setMethod(packageTestSuiteEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.PackageTestSuite");
    }

    return packageTestSuiteEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getPackageExampleEmitter()
  {
    if (packageExampleEmitter == null)
    {
      packageExampleEmitter = createJETEmitter(packageExampleTemplateName);
      setMethod(packageExampleEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.PackageExample");
    }
    return packageExampleEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getTestsPluginXMLEmitter()
  {
    if (testsPluginXMLEmitter == null)
    {
      testsPluginXMLEmitter = createJETEmitter(testsPluginXMLTemplateName);
      setMethod(testsPluginXMLEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.PluginXML");
    }
    return testsPluginXMLEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getTestsManifestMFEmitter()
  {
    if (testsManifestMFEmitter == null)
    {
      testsManifestMFEmitter = createJETEmitter(testsManifestMFTemplateName);
      setMethod(testsManifestMFEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.ManifestMF");
    }
    return testsManifestMFEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getTestsPluginPropertiesEmitter()
  {
    if (testsPluginPropertiesEmitter == null)
    {
      testsPluginPropertiesEmitter = createJETEmitter(testsPluginPropertiesTemplateName);
      setMethod(testsPluginPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.PluginProperties");
    }
    return testsPluginPropertiesEmitter;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public JETEmitter getTestsBuildPropertiesEmitter()
  {
    if (testsBuildPropertiesEmitter == null)
    {
      testsBuildPropertiesEmitter = createJETEmitter(testsBuildPropertiesTemplateName);
      setMethod(testsBuildPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.BuildProperties");
    }
    return testsBuildPropertiesEmitter;
  }

  protected String getPluginDirectory(boolean isSet, String baseDirectory, String suffix)
  {
    if (!isSet)
    {
      String modelProject = getModelProject();
      if (modelProject != null)
      {
        return new StringBuffer("/").append(modelProject).append(suffix).append(getSourceFragmentPath()).toString();
      }
    }
    return baseDirectory;
  }

  public String getEditDirectory()
  {
    return getPluginDirectory(isSetEditDirectory(), getEditDirectoryGen(), ".edit/");
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEditDirectoryGen()
  {
    return editDirectory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEditDirectory(String newEditDirectory)
  {
    String oldEditDirectory = editDirectory;
    editDirectory = newEditDirectory;
    boolean oldEditDirectoryESet = editDirectoryESet;
    editDirectoryESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__EDIT_DIRECTORY, oldEditDirectory, editDirectory, !oldEditDirectoryESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetEditDirectory()
  {
    String oldEditDirectory = editDirectory;
    boolean oldEditDirectoryESet = editDirectoryESet;
    editDirectory = EDIT_DIRECTORY_EDEFAULT;
    editDirectoryESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_MODEL__EDIT_DIRECTORY, oldEditDirectory, EDIT_DIRECTORY_EDEFAULT, oldEditDirectoryESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetEditDirectory()
  {
    return editDirectoryESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCreationSubmenus()
  {
    return creationSubmenus;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCreationSubmenus(boolean newCreationSubmenus)
  {
    boolean oldCreationSubmenus = creationSubmenus;
    creationSubmenus = newCreationSubmenus;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__CREATION_SUBMENUS, oldCreationSubmenus, creationSubmenus));
  }

  public String getEditorDirectory()
  {
    return getPluginDirectory(isSetEditorDirectory(), getEditorDirectoryGen(), ".editor/");
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEditorDirectoryGen()
  {
    return editorDirectory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEditorDirectory(String newEditorDirectory)
  {
    String oldEditorDirectory = editorDirectory;
    editorDirectory = newEditorDirectory;
    boolean oldEditorDirectoryESet = editorDirectoryESet;
    editorDirectoryESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY, oldEditorDirectory, editorDirectory, !oldEditorDirectoryESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetEditorDirectory()
  {
    String oldEditorDirectory = editorDirectory;
    boolean oldEditorDirectoryESet = editorDirectoryESet;
    editorDirectory = EDITOR_DIRECTORY_EDEFAULT;
    editorDirectoryESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY, oldEditorDirectory, EDITOR_DIRECTORY_EDEFAULT, oldEditorDirectoryESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetEditorDirectory()
  {
    return editorDirectoryESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getModelPluginID()
  {
    return modelPluginID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelPluginID(String newModelPluginID)
  {
    String oldModelPluginID = modelPluginID;
    modelPluginID = newModelPluginID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__MODEL_PLUGIN_ID, oldModelPluginID, modelPluginID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTemplateDirectory()
  {
    return templateDirectory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTemplateDirectory(String newTemplateDirectory)
  {
    String oldTemplateDirectory = templateDirectory;
    templateDirectory = newTemplateDirectory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__TEMPLATE_DIRECTORY, oldTemplateDirectory, templateDirectory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isRuntimeJar()
  {
    return runtimeJar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRuntimeJar(boolean newRuntimeJar)
  {
    boolean oldRuntimeJar = runtimeJar;
    runtimeJar = newRuntimeJar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__RUNTIME_JAR, oldRuntimeJar, runtimeJar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getForeignModel()
  {
    if (foreignModel == null)
    {
      foreignModel = new EDataTypeUniqueEList<String>(String.class, this, GenModelPackage.GEN_MODEL__FOREIGN_MODEL);
    }
    return foreignModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDynamicTemplates()
  {
    return dynamicTemplates;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDynamicTemplates(boolean newDynamicTemplates)
  {
    boolean oldDynamicTemplates = dynamicTemplates;
    dynamicTemplates = newDynamicTemplates;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__DYNAMIC_TEMPLATES, oldDynamicTemplates, dynamicTemplates));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRedirection()
  {
    return redirection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRedirection(String newRedirection)
  {
    String oldRedirection = redirection;
    redirection = newRedirection;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__REDIRECTION, oldRedirection, redirection));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isForceOverwrite()
  {
    return forceOverwrite;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setForceOverwrite(boolean newForceOverwrite)
  {
    boolean oldForceOverwrite = forceOverwrite;
    forceOverwrite = newForceOverwrite;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__FORCE_OVERWRITE, oldForceOverwrite, forceOverwrite));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNonExternalizedStringTag()
  {
    return nonExternalizedStringTag;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNonExternalizedStringTagGen(String newNonExternalizedStringTag)
  {
    String oldNonExternalizedStringTag = nonExternalizedStringTag;
    nonExternalizedStringTag = newNonExternalizedStringTag;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__NON_EXTERNALIZED_STRING_TAG, oldNonExternalizedStringTag, nonExternalizedStringTag));
  }

  public void setNonExternalizedStringTag(String newNonExternalizedStringTag)
  {
    setNonNLSMarkersGen(newNonExternalizedStringTag != null);
    setNonExternalizedStringTagGen(null);
  }

  @Override
  public String getName()
  {
    return getModelName();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getModelName()
  {
    return modelName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelName(String newModelName)
  {
    String oldModelName = modelName;
    modelName = newModelName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__MODEL_NAME, oldModelName, modelName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getModelPluginClass()
  {
    return modelPluginClass;
  }

  /**
   * @deprecated 2.9
   */
  @Deprecated
  protected String getPluginClass(boolean isSet, String baseName, String packageSuffix, String classSuffix)
  {
    if (!isSet)
    {
      String modelName = getModelName();
      if (!isBlank(modelName))
      {
        String mainPackage = getMainPackage();
        if (mainPackage != null)
        {
          StringBuffer pluginClass = new StringBuffer(mainPackage);
          if (!isBlank(packageSuffix))
          {
            pluginClass.append(".").append(packageSuffix);
          }
          pluginClass.append(".").append(CodeGenUtil.validJavaIdentifier(modelName)). append(classSuffix);
          return pluginClass.toString();
        }
      }
    }
    return baseName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelPluginClass(String newModelPluginClass)
  {
    String oldModelPluginClass = modelPluginClass;
    modelPluginClass = newModelPluginClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__MODEL_PLUGIN_CLASS, oldModelPluginClass, modelPluginClass));
  }

  // If we change to pattern defaults, will have to specify plain suffix in no main package case.
  //
  public String getEditPluginClass()
  {
    if (!isSetEditPluginClass())
    {
      String modelName = getModelName();
      if (!isBlank(modelName))
      {
        GenPackage mainGenPackage = getMainGenPackage();
        String packageName = mainGenPackage != null ?
          mainGenPackage.getProviderPackageName() :
          addPackageSuffix(getModelProject(), GenPackageImpl.PROVIDER_PACKAGE_SUFFIX_EDEFAULT);
        StringBuilder result = new StringBuilder(packageName);
        return result.append(".").append(CodeGenUtil.validJavaIdentifier(modelName)).append("EditPlugin").toString();
      }
    }
    return getEditPluginClassGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEditPluginClassGen()
  {
    return editPluginClass;
  }

  protected String getModelProject()
  {
    String modelDirectory = getModelDirectory();
    if (!isBlank(modelDirectory))
    {
      return URI.createURI(modelDirectory).segment(0);
    }
    return null;
  }

  /**
   * @deprecated 2.9
   */
  @Deprecated
  protected String getMainPackage()
  {
    GenPackage genPackage = getMainGenPackage();
    // Second alternative should be getModelProject()?
    return genPackage != null ? genPackage.getQualifiedPackageName() : getModelDirectory();
  }

  protected GenPackage getMainGenPackage()
  {
    if (!getGenPackages().isEmpty())
    {
      GenPackage genPackage = getGenPackages().get(0);
      while (genPackage.getGenClassifiers().isEmpty() && !genPackage.getNestedGenPackages().isEmpty())
      {
        genPackage = genPackage.getNestedGenPackages().get(0);
      }
      return genPackage;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEditPluginClass(String newEditPluginClass)
  {
    String oldEditPluginClass = editPluginClass;
    editPluginClass = newEditPluginClass;
    boolean oldEditPluginClassESet = editPluginClassESet;
    editPluginClassESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__EDIT_PLUGIN_CLASS, oldEditPluginClass, editPluginClass, !oldEditPluginClassESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetEditPluginClass()
  {
    String oldEditPluginClass = editPluginClass;
    boolean oldEditPluginClassESet = editPluginClassESet;
    editPluginClass = EDIT_PLUGIN_CLASS_EDEFAULT;
    editPluginClassESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_MODEL__EDIT_PLUGIN_CLASS, oldEditPluginClass, EDIT_PLUGIN_CLASS_EDEFAULT, oldEditPluginClassESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetEditPluginClass()
  {
    return editPluginClassESet;
  }

  // If we change to pattern defaults, will have to specify plain suffix in no main package case.
  //
  public String getEditorPluginClass()
  {
    if (!isSetEditorPluginClass())
    {
      String modelName = getModelName();
      if (!isBlank(modelName))
      {
        GenPackage mainGenPackage = getMainGenPackage();
        String packageName = mainGenPackage != null ?
          mainGenPackage.getPresentationPackageName() :
          addPackageSuffix(getModelProject(), GenPackageImpl.PRESENTATION_PACKAGE_SUFFIX_EDEFAULT);
        StringBuilder result = new StringBuilder(packageName);
        return result.append(".").append(CodeGenUtil.validJavaIdentifier(modelName)).append("EditorPlugin").toString();
      }
    }
    return getEditorPluginClassGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEditorPluginClassGen()
  {
    return editorPluginClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEditorPluginClass(String newEditorPluginClass)
  {
    String oldEditorPluginClass = editorPluginClass;
    editorPluginClass = newEditorPluginClass;
    boolean oldEditorPluginClassESet = editorPluginClassESet;
    editorPluginClassESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_CLASS, oldEditorPluginClass, editorPluginClass, !oldEditorPluginClassESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetEditorPluginClass()
  {
    String oldEditorPluginClass = editorPluginClass;
    boolean oldEditorPluginClassESet = editorPluginClassESet;
    editorPluginClass = EDITOR_PLUGIN_CLASS_EDEFAULT;
    editorPluginClassESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_CLASS, oldEditorPluginClass, EDITOR_PLUGIN_CLASS_EDEFAULT, oldEditorPluginClassESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetEditorPluginClass()
  {
    return editorPluginClassESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isUpdateClasspath()
  {
    return updateClasspath;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpdateClasspath(boolean newUpdateClasspath)
  {
    boolean oldUpdateClasspath = updateClasspath;
    updateClasspath = newUpdateClasspath;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__UPDATE_CLASSPATH, oldUpdateClasspath, updateClasspath));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isGenerateSchema()
  {
    return generateSchema;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenerateSchema(boolean newGenerateSchema)
  {
    boolean oldGenerateSchema = generateSchema;
    generateSchema = newGenerateSchema;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__GENERATE_SCHEMA, oldGenerateSchema, generateSchema));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isNonNLSMarkers()
  {
    return nonNLSMarkers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNonNLSMarkersGen(boolean newNonNLSMarkers)
  {
    boolean oldNonNLSMarkers = nonNLSMarkers;
    nonNLSMarkers = newNonNLSMarkers;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__NON_NLS_MARKERS, oldNonNLSMarkers, nonNLSMarkers));
  }

  public void setNonNLSMarkers(boolean newNonNLSMarkers)
  {
    setNonNLSMarkersGen(newNonNLSMarkers);
    setNonExternalizedStringTagGen(null);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<String> getStaticPackages()
  {
    if (staticPackages == null)
    {
      staticPackages =
        new EDataTypeUniqueEList<String>(String.class, this, GenModelPackage.GEN_MODEL__STATIC_PACKAGES)
        {
          private static final long serialVersionUID = 1L;

          @Override
          protected void didChange()
          {
            if (staticGenPackages != null)
            {
              for (GenPackage genPackage : staticGenPackages)
              {
                setMainGenModel(genPackage, null);
              }
              staticGenPackages = null;
            }
          }
        };
    }
    return staticPackages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getModelPluginVariables()
  {
    if (modelPluginVariables == null)
    {
      modelPluginVariables = new EDataTypeUniqueEList<String>(String.class, this, GenModelPackage.GEN_MODEL__MODEL_PLUGIN_VARIABLES);
    }
    return modelPluginVariables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRootExtendsInterface()
  {
    return rootExtendsInterface;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRootExtendsInterface(String newRootExtendsInterface)
  {
    String oldRootExtendsInterface = rootExtendsInterface;
    rootExtendsInterface = newRootExtendsInterface;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__ROOT_EXTENDS_INTERFACE, oldRootExtendsInterface, rootExtendsInterface));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRootExtendsClass()
  {
    return rootExtendsClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRootExtendsClass(String newRootExtendsClass)
  {
    String oldRootExtendsClass = rootExtendsClass;
    rootExtendsClass = newRootExtendsClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__ROOT_EXTENDS_CLASS, oldRootExtendsClass, rootExtendsClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRootImplementsInterface()
  {
    return rootImplementsInterface;
  }

  protected GenClass rootImplementsInterfaceGenClass;

  public GenClass getRootImplementsInterfaceGenClass()
  {
    if (rootImplementsInterfaceGenClass == null && !isBlank(rootImplementsInterface))
    {
      for (GenPackage genPackage : getAllGenUsedAndStaticGenPackagesWithClassifiers())
      {
        for (GenClass genClass : genPackage.getGenClasses())
        {
          if (genClass.getQualifiedInterfaceName().equals(rootImplementsInterface))
          {
            return rootImplementsInterfaceGenClass = genClass;
          }
        }
      }
    }

    return rootImplementsInterfaceGenClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRootImplementsInterfaceGen(String newRootImplementsInterface)
  {
    String oldRootImplementsInterface = rootImplementsInterface;
    rootImplementsInterface = newRootImplementsInterface;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__ROOT_IMPLEMENTS_INTERFACE, oldRootImplementsInterface, rootImplementsInterface));
  }

  public void setRootImplementsInterface(String newRootImplementsInterface)
  {
    setRootImplementsInterfaceGen(newRootImplementsInterface);
    rootImplementsInterfaceGenClass = null;
  }

  public List<String> getEffectiveModelPluginVariables()
  {
    return getModelPluginVariables();
  }

  protected static List<String> getEffectivePluginIDs(List<String> pluginVariables)
  {
    List<String> result = new ArrayList<String>(pluginVariables);
    for (ListIterator<String> i = result.listIterator(); i.hasNext(); )
    {
      String variable = i.next();
      int index = variable.indexOf("=");
      if (index != -1)
      {
        i.set(variable.substring(index + 1));
      }
    }
    return result;
  }

  public List<String> getEffectiveModelPluginIDs()
  {
    return getEffectivePluginIDs(getModelPluginVariables());
  }

  public List<String> getEffectiveEditPluginIDs()
  {
    return getEffectivePluginIDs(getEditPluginVariables());
  }

  public List<String> getEffectiveEditorPluginIDs()
  {
    return getEffectivePluginIDs(getEditorPluginVariables());
  }

  public List<String> getEffectiveTestsPluginIDs()
  {
    return getEffectivePluginIDs(getTestsPluginVariables());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSuppressEMFTypes()
  {
    return suppressEMFTypes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuppressEMFTypes(boolean newSuppressEMFTypes)
  {
    boolean oldSuppressEMFTypes = suppressEMFTypes;
    suppressEMFTypes = newSuppressEMFTypes;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__SUPPRESS_EMF_TYPES, oldSuppressEMFTypes, suppressEMFTypes));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSuppressEMFMetaData()
  {
    return suppressEMFMetaData;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuppressEMFMetaData(boolean newSuppressEMFMetaData)
  {
    boolean oldSuppressEMFMetaData = suppressEMFMetaData;
    suppressEMFMetaData = newSuppressEMFMetaData;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__SUPPRESS_EMF_META_DATA, oldSuppressEMFMetaData, suppressEMFMetaData));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSuppressEMFModelTags()
  {
    return suppressEMFModelTags;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuppressEMFModelTags(boolean newSuppressEMFModelTags)
  {
    boolean oldSuppressEMFModelTags = suppressEMFModelTags;
    suppressEMFModelTags = newSuppressEMFModelTags;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__SUPPRESS_EMF_MODEL_TAGS, oldSuppressEMFModelTags, suppressEMFModelTags));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSuppressInterfaces()
  {
    return suppressInterfaces;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuppressInterfaces(boolean newSuppressInterfaces)
  {
    boolean oldSuppressInterfaces = suppressInterfaces;
    suppressInterfaces = newSuppressInterfaces;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__SUPPRESS_INTERFACES, oldSuppressInterfaces, suppressInterfaces));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFeatureMapWrapperInterface()
  {
    return featureMapWrapperInterface;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeatureMapWrapperInterface(String newFeatureMapWrapperInterface)
  {
    String oldFeatureMapWrapperInterface = featureMapWrapperInterface;
    featureMapWrapperInterface = newFeatureMapWrapperInterface;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE, oldFeatureMapWrapperInterface, featureMapWrapperInterface));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFeatureMapWrapperInternalInterface()
  {
    return featureMapWrapperInternalInterface;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeatureMapWrapperInternalInterface(String newFeatureMapWrapperInternalInterface)
  {
    String oldFeatureMapWrapperInternalInterface = featureMapWrapperInternalInterface;
    featureMapWrapperInternalInterface = newFeatureMapWrapperInternalInterface;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE, oldFeatureMapWrapperInternalInterface, featureMapWrapperInternalInterface));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFeatureMapWrapperClass()
  {
    return featureMapWrapperClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeatureMapWrapperClass(String newFeatureMapWrapperClass)
  {
    String oldFeatureMapWrapperClass = featureMapWrapperClass;
    featureMapWrapperClass = newFeatureMapWrapperClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS, oldFeatureMapWrapperClass, featureMapWrapperClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isRuntimeCompatibility()
  {
    return runtimeCompatibility;
  }

  public boolean needsRuntimeCompatibility()
  {
    return isRuntimeCompatibility() && !isRichClientPlatform() && !isRichAjaxPlatform();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRuntimeCompatibility(boolean newRuntimeCompatibility)
  {
    boolean oldRuntimeCompatibility = runtimeCompatibility;
    runtimeCompatibility = newRuntimeCompatibility;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__RUNTIME_COMPATIBILITY, oldRuntimeCompatibility, runtimeCompatibility));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean isRichClientPlatform()
  {
    GenRuntimePlatform runtimePlatform = getRuntimePlatform();
    return runtimePlatform == GenRuntimePlatform.RAP || runtimePlatform == GenRuntimePlatform.RCP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setRichClientPlatform(boolean newRichClientPlatform)
  {
    if (getRuntimePlatform() != GenRuntimePlatform.RAP)
    {
      if (newRichClientPlatform)
      {
        setRuntimePlatform(GenRuntimePlatform.RCP);
      }
      else if (getRuntimePlatform() == GenRuntimePlatform.RCP)
      {
        setRuntimePlatform(GenRuntimePlatform.IDE);
      }
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean isReflectiveDelegation()
  {
    return getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setReflectiveDelegation(boolean newReflectiveDelegation)
  {
    setFeatureDelegation(newReflectiveDelegation ? GenDelegationKind.REFLECTIVE_LITERAL : GenDelegationKind.NONE_LITERAL);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCodeFormatting()
  {
    return codeFormatting;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCodeFormatting(boolean newCodeFormatting)
  {
    boolean oldCodeFormatting = codeFormatting;
    codeFormatting = newCodeFormatting;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__CODE_FORMATTING, oldCodeFormatting, codeFormatting));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCommentFormatting()
  {
    return commentFormatting;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCommentFormatting(boolean newCommentFormatting)
  {
    boolean oldCommentFormatting = commentFormatting;
    commentFormatting = newCommentFormatting;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__COMMENT_FORMATTING, oldCommentFormatting, commentFormatting));
  }

  public String getTestsDirectory()
  {
    return getPluginDirectory(isSetTestsDirectory(), getTestsDirectoryGen(), ".tests/");
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTestsDirectoryGen()
  {
    return testsDirectory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTestsDirectory(String newTestsDirectory)
  {
    String oldTestsDirectory = testsDirectory;
    testsDirectory = newTestsDirectory;
    boolean oldTestsDirectoryESet = testsDirectoryESet;
    testsDirectoryESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__TESTS_DIRECTORY, oldTestsDirectory, testsDirectory, !oldTestsDirectoryESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetTestsDirectory()
  {
    String oldTestsDirectory = testsDirectory;
    boolean oldTestsDirectoryESet = testsDirectoryESet;
    testsDirectory = TESTS_DIRECTORY_EDEFAULT;
    testsDirectoryESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_MODEL__TESTS_DIRECTORY, oldTestsDirectory, TESTS_DIRECTORY_EDEFAULT, oldTestsDirectoryESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetTestsDirectory()
  {
    return testsDirectoryESet;
  }

  // If we change to pattern defaults, will have to specify plain suffix in no main package case.
  //
  public String getTestSuiteClass()
  {
    if (!isSetTestSuiteClass())
    {
      String modelName = getModelName();
      if (!isBlank(modelName))
      {
        GenPackage mainGenPackage = getMainGenPackage();
        String packageName = mainGenPackage != null ?
          mainGenPackage.getTestsPackageName() :
          addPackageSuffix(getModelProject(), GenPackageImpl.TESTS_PACKAGE_SUFFIX_EDEFAULT);
        StringBuilder result = new StringBuilder(packageName);
        return result.append(".").append(CodeGenUtil.validJavaIdentifier(modelName)).append("AllTests").toString();
      }
    }
    return getTestSuiteClassGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTestSuiteClassGen()
  {
    return testSuiteClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTestSuiteClass(String newTestSuiteClass)
  {
    String oldTestSuiteClass = testSuiteClass;
    testSuiteClass = newTestSuiteClass;
    boolean oldTestSuiteClassESet = testSuiteClassESet;
    testSuiteClassESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS, oldTestSuiteClass, testSuiteClass, !oldTestSuiteClassESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetTestSuiteClass()
  {
    String oldTestSuiteClass = testSuiteClass;
    boolean oldTestSuiteClassESet = testSuiteClassESet;
    testSuiteClass = TEST_SUITE_CLASS_EDEFAULT;
    testSuiteClassESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS, oldTestSuiteClass, TEST_SUITE_CLASS_EDEFAULT, oldTestSuiteClassESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetTestSuiteClass()
  {
    return testSuiteClassESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBooleanFlagsField()
  {
    return booleanFlagsField;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBooleanFlagsField(String newBooleanFlagsField)
  {
    String oldBooleanFlagsField = booleanFlagsField;
    booleanFlagsField = newBooleanFlagsField;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_FIELD, oldBooleanFlagsField, booleanFlagsField));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getBooleanFlagsReservedBits()
  {
    return booleanFlagsReservedBits;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBooleanFlagsReservedBits(int newBooleanFlagsReservedBits)
  {
    int oldBooleanFlagsReservedBits = booleanFlagsReservedBits;
    booleanFlagsReservedBits = newBooleanFlagsReservedBits;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS, oldBooleanFlagsReservedBits, booleanFlagsReservedBits));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getImporterID()
  {
    return importerID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImporterID(String newImporterID)
  {
    String oldImporterID = importerID;
    importerID = newImporterID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__IMPORTER_ID, oldImporterID, importerID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isBundleManifest()
  {
    return bundleManifest;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBundleManifest(boolean newBundleManifest)
  {
    boolean oldBundleManifest = bundleManifest;
    bundleManifest = newBundleManifest;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__BUNDLE_MANIFEST, oldBundleManifest, bundleManifest));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenDelegationKind getFeatureDelegation()
  {
    return featureDelegation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeatureDelegation(GenDelegationKind newFeatureDelegation)
  {
    GenDelegationKind oldFeatureDelegation = featureDelegation;
    featureDelegation = newFeatureDelegation == null ? FEATURE_DELEGATION_EDEFAULT : newFeatureDelegation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__FEATURE_DELEGATION, oldFeatureDelegation, featureDelegation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isContainmentProxies()
  {
    return containmentProxies;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContainmentProxies(boolean newContainmentProxies)
  {
    boolean oldContainmentProxies = containmentProxies;
    containmentProxies = newContainmentProxies;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__CONTAINMENT_PROXIES, oldContainmentProxies, containmentProxies));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isMinimalReflectiveMethods()
  {
    return minimalReflectiveMethods;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMinimalReflectiveMethods(boolean newMinimalReflectiveMethods)
  {
    boolean oldMinimalReflectiveMethods = minimalReflectiveMethods;
    minimalReflectiveMethods = newMinimalReflectiveMethods;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__MINIMAL_REFLECTIVE_METHODS, oldMinimalReflectiveMethods, minimalReflectiveMethods));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSuppressContainment()
  {
    return suppressContainment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuppressContainment(boolean newSuppressContainment)
  {
    boolean oldSuppressContainment = suppressContainment;
    suppressContainment = newSuppressContainment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__SUPPRESS_CONTAINMENT, oldSuppressContainment, suppressContainment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSuppressNotification()
  {
    return suppressNotification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuppressNotification(boolean newSuppressNotification)
  {
    boolean oldSuppressNotification = suppressNotification;
    suppressNotification = newSuppressNotification;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__SUPPRESS_NOTIFICATION, oldSuppressNotification, suppressNotification));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isArrayAccessors()
  {
    return arrayAccessors;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArrayAccessors(boolean newArrayAccessors)
  {
    boolean oldArrayAccessors = arrayAccessors;
    arrayAccessors = newArrayAccessors;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__ARRAY_ACCESSORS, oldArrayAccessors, arrayAccessors));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSuppressUnsettable()
  {
    return suppressUnsettable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuppressUnsettable(boolean newSuppressUnsettable)
  {
    boolean oldSuppressUnsettable = suppressUnsettable;
    suppressUnsettable = newSuppressUnsettable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__SUPPRESS_UNSETTABLE, oldSuppressUnsettable, suppressUnsettable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFacadeHelperClass()
  {
    return facadeHelperClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFacadeHelperClass(String newFacadeHelperClass)
  {
    String oldFacadeHelperClass = facadeHelperClass;
    facadeHelperClass = newFacadeHelperClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__FACADE_HELPER_CLASS, oldFacadeHelperClass, facadeHelperClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenJDKLevel getComplianceLevel()
  {
    return complianceLevel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComplianceLevel(GenJDKLevel newComplianceLevel)
  {
    GenJDKLevel oldComplianceLevel = complianceLevel;
    complianceLevel = newComplianceLevel == null ? COMPLIANCE_LEVEL_EDEFAULT : newComplianceLevel;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__COMPLIANCE_LEVEL, oldComplianceLevel, complianceLevel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSuppressGenModelAnnotations()
  {
    return suppressGenModelAnnotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuppressGenModelAnnotations(boolean newSuppressGenModelAnnotations)
  {
    boolean oldSuppressGenModelAnnotations = suppressGenModelAnnotations;
    suppressGenModelAnnotations = newSuppressGenModelAnnotations;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__SUPPRESS_GEN_MODEL_ANNOTATIONS, oldSuppressGenModelAnnotations, suppressGenModelAnnotations));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCopyrightFields()
  {
    return copyrightFields;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCopyrightFields(boolean newCopyrightFields)
  {
    boolean oldCopyrightFields = copyrightFields;
    copyrightFields = newCopyrightFields;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__COPYRIGHT_FIELDS, oldCopyrightFields, copyrightFields));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isBinaryCompatibleReflectiveMethods()
  {
    return binaryCompatibleReflectiveMethods;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBinaryCompatibleReflectiveMethods(boolean newBinaryCompatibleReflectiveMethods)
  {
    boolean oldBinaryCompatibleReflectiveMethods = binaryCompatibleReflectiveMethods;
    binaryCompatibleReflectiveMethods = newBinaryCompatibleReflectiveMethods;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__BINARY_COMPATIBLE_REFLECTIVE_METHODS, oldBinaryCompatibleReflectiveMethods, binaryCompatibleReflectiveMethods));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPublicConstructors()
  {
    return publicConstructors;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPublicConstructors(boolean newPublicConstructors)
  {
    boolean oldPublicConstructors = publicConstructors;
    publicConstructors = newPublicConstructors;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__PUBLIC_CONSTRUCTORS, oldPublicConstructors, publicConstructors));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getTemplatePluginVariables()
  {
    if (templatePluginVariables == null)
    {
      templatePluginVariables = new EDataTypeUniqueEList<String>(String.class, this, GenModelPackage.GEN_MODEL__TEMPLATE_PLUGIN_VARIABLES);
    }
    return templatePluginVariables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getProviderRootExtendsClass()
  {
    return providerRootExtendsClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProviderRootExtendsClass(String newProviderRootExtendsClass)
  {
    String oldProviderRootExtendsClass = providerRootExtendsClass;
    providerRootExtendsClass = newProviderRootExtendsClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__PROVIDER_ROOT_EXTENDS_CLASS, oldProviderRootExtendsClass, providerRootExtendsClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenPackage> getGenPackages()
  {
    if (genPackages == null)
    {
      genPackages = new EObjectContainmentWithInverseEList<GenPackage>(GenPackage.class, this, GenModelPackage.GEN_MODEL__GEN_PACKAGES, GenModelPackage.GEN_PACKAGE__GEN_MODEL);
    }
    return genPackages;
  }

  public EList<GenPackage> getStaticGenPackages()
  {
    if (staticGenPackages == null)
    {
      staticGenPackages = new UniqueEList<GenPackage>();
      LOOP:
      for (String nsURI : getStaticPackages())
      {
        for (GenPackage staticGenPackage : getStaticGenPackages())
        {
          if (staticGenPackage.getNSURI().equals(nsURI))
          {
            continue LOOP;
          }
        }
        EPackage staticEPackage = eResource().getResourceSet().getPackageRegistry().getEPackage(nsURI);
        if (staticEPackage != null)
        {
          // See if a GenModel is registered for the package's URI, and if so, try to load it.
          //
          GenPackage staticGenPackage = null;
          URI genModelURI = EcorePlugin.getEPackageNsURIToGenModelLocationMap(true).get(nsURI);
          if (genModelURI != null)
          {
            try
            {
              Resource genModelResource = eResource().getResourceSet().getResource(genModelURI, true);
              GenModel genModel = (GenModel)genModelResource.getContents().get(0);
              staticGenPackage = genModel.findGenPackage(staticEPackage);
            }
            catch (Exception exception)
            {
              CodeGenEcorePlugin.INSTANCE.log(exception);
            }
          }

          // If that didn't work, just synthesize one.
          //
          if (staticGenPackage == null)
          {
            GenModel genModel = getGenModel().createGenModel();
            staticGenPackage = getGenModel().createGenPackage();
            genModel.getGenPackages().add(staticGenPackage);
            staticGenPackage.initialize(staticEPackage);
          }

          setMainGenModel(staticGenPackage, this);
          staticGenPackages.add(staticGenPackage);
        }
      }
    }
    return staticGenPackages;
  }

  private static void setMainGenModel(GenPackage genPackage, GenModel target)
  {
    if (!genPackage.eIsProxy())
    {
      GenModel genModel = genPackage.getGenModel();
      if (genModel != null)
      {
        genModel.setMainGenModel(target);
      }
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<GenPackage> getUsedGenPackages()
  {
    if (usedGenPackages == null)
    {
      usedGenPackages =
        new EObjectResolvingEList<GenPackage>(GenPackage.class, this, GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES)
        {
          private static final long serialVersionUID = 1L;

          @Override
          protected void didSet(int index, GenPackage newObject, GenPackage oldObject)
          {
            setMainGenModel(oldObject, null);
            setMainGenModel(newObject, GenModelImpl.this);
          }

          @Override
          protected void didAdd(int index, GenPackage newObject)
          {
            setMainGenModel(newObject, GenModelImpl.this);
          }

          @Override
          protected void didRemove(int index, GenPackage oldObject)
          {
            setMainGenModel(oldObject, null);
          }
        };
    }
    return usedGenPackages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getInterfaceNamePattern()
  {
    return interfaceNamePattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInterfaceNamePattern(String newInterfaceNamePattern)
  {
    String oldInterfaceNamePattern = interfaceNamePattern;
    interfaceNamePattern = newInterfaceNamePattern;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__INTERFACE_NAME_PATTERN, oldInterfaceNamePattern, interfaceNamePattern));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getClassNamePattern()
  {
    return classNamePattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClassNamePattern(String newClassNamePattern)
  {
    String oldClassNamePattern = classNamePattern;
    classNamePattern = newClassNamePattern;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__CLASS_NAME_PATTERN, oldClassNamePattern, classNamePattern));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isOperationReflectionGen()
  {
    return operationReflection;
  }

  public boolean isOperationReflection()
  {
    return getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF26_VALUE && isOperationReflectionGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOperationReflection(boolean newOperationReflection)
  {
    boolean oldOperationReflection = operationReflection;
    operationReflection = newOperationReflection;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__OPERATION_REFLECTION, oldOperationReflection, operationReflection));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean isRichAjaxPlatform()
  {
    return getRuntimePlatform() == GenRuntimePlatform.RAP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setRichAjaxPlatform(boolean newRichAjaxPlatform)
  {
    if (newRichAjaxPlatform)
    {
      setRuntimePlatform(GenRuntimePlatform.RAP);
    }
    else if (getRuntimePlatform() == GenRuntimePlatform.RAP)
    {
      setRuntimePlatform(GenRuntimePlatform.IDE);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenRuntimePlatform getRuntimePlatform()
  {
    return runtimePlatform;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRuntimePlatform(GenRuntimePlatform newRuntimePlatform)
  {
    GenRuntimePlatform oldRuntimePlatform = runtimePlatform;
    runtimePlatform = newRuntimePlatform == null ? RUNTIME_PLATFORM_EDEFAULT : newRuntimePlatform;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__RUNTIME_PLATFORM, oldRuntimePlatform, runtimePlatform));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isImportOrganizing()
  {
    return importOrganizing;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImportOrganizing(boolean newImportOrganizing)
  {
    boolean oldImportOrganizing = importOrganizing;
    importOrganizing = newImportOrganizing;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__IMPORT_ORGANIZING, oldImportOrganizing, importOrganizing));
  }

  public String getPluginKey()
  {
    if (pluginKey == null)
    {
      Resource resource = eResource();
      if (resource != null)
      {
        URI uri = resource.getURI();
        if (uri != null)
        {
          String fileName = uri.trimFileExtension().lastSegment();
          if (fileName != null)
          {
            return fileName;
          }
        }
      }
      String modelName = getModelName();
      if (modelName != null)
      {
        return modelName;
      }
    }
    return pluginKey;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPluginKeyGen()
  {
    return pluginKey;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPluginKey(String newPluginKey)
  {
    String oldPluginKey = pluginKey;
    pluginKey = newPluginKey;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__PLUGIN_KEY, oldPluginKey, pluginKey));
  }

  public GenDecoration getDecoration()
  {
    return getRuntimeVersion().ordinal() >= GenRuntimeVersion.EMF29_VALUE ? getDecorationGen() : GenDecoration.NONE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenDecoration getDecorationGen()
  {
    return decoration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDecoration(GenDecoration newDecoration)
  {
    GenDecoration oldDecoration = decoration;
    decoration = newDecoration == null ? DECORATION_EDEFAULT : newDecoration;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__DECORATION, oldDecoration, decoration));
  }

  /**
   * <!-- begin-user-doc -->
   * @since 2.10
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isStyleProviders()
  {
    return styleProviders;
  }

  /**
   * <!-- begin-user-doc -->
   * @since 2.10
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStyleProviders(boolean newStyleProviders)
  {
    boolean oldStyleProviders = styleProviders;
    styleProviders = newStyleProviders;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__STYLE_PROVIDERS, oldStyleProviders, styleProviders));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getGenPackages()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
        return ((InternalEList<?>)getGenPackages()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_MODEL__COPYRIGHT_TEXT:
        return getCopyrightText();
      case GenModelPackage.GEN_MODEL__MODEL_DIRECTORY:
        return getModelDirectory();
      case GenModelPackage.GEN_MODEL__CREATION_COMMANDS:
        return isCreationCommands();
      case GenModelPackage.GEN_MODEL__CREATION_ICONS:
        return isCreationIcons();
      case GenModelPackage.GEN_MODEL__CREATION_SUBMENUS:
        return isCreationSubmenus();
      case GenModelPackage.GEN_MODEL__EDIT_DIRECTORY:
        return getEditDirectory();
      case GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY:
        return getEditorDirectory();
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_ID:
        return getModelPluginID();
      case GenModelPackage.GEN_MODEL__TEMPLATE_DIRECTORY:
        return getTemplateDirectory();
      case GenModelPackage.GEN_MODEL__RUNTIME_JAR:
        return isRuntimeJar();
      case GenModelPackage.GEN_MODEL__FOREIGN_MODEL:
        return getForeignModel();
      case GenModelPackage.GEN_MODEL__DYNAMIC_TEMPLATES:
        return isDynamicTemplates();
      case GenModelPackage.GEN_MODEL__REDIRECTION:
        return getRedirection();
      case GenModelPackage.GEN_MODEL__FORCE_OVERWRITE:
        return isForceOverwrite();
      case GenModelPackage.GEN_MODEL__NON_EXTERNALIZED_STRING_TAG:
        return getNonExternalizedStringTag();
      case GenModelPackage.GEN_MODEL__MODEL_NAME:
        return getModelName();
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_CLASS:
        return getModelPluginClass();
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_CLASS:
        return getEditPluginClass();
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_CLASS:
        return getEditorPluginClass();
      case GenModelPackage.GEN_MODEL__UPDATE_CLASSPATH:
        return isUpdateClasspath();
      case GenModelPackage.GEN_MODEL__GENERATE_SCHEMA:
        return isGenerateSchema();
      case GenModelPackage.GEN_MODEL__NON_NLS_MARKERS:
        return isNonNLSMarkers();
      case GenModelPackage.GEN_MODEL__STATIC_PACKAGES:
        return getStaticPackages();
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_VARIABLES:
        return getModelPluginVariables();
      case GenModelPackage.GEN_MODEL__ROOT_EXTENDS_INTERFACE:
        return getRootExtendsInterface();
      case GenModelPackage.GEN_MODEL__ROOT_EXTENDS_CLASS:
        return getRootExtendsClass();
      case GenModelPackage.GEN_MODEL__ROOT_IMPLEMENTS_INTERFACE:
        return getRootImplementsInterface();
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_TYPES:
        return isSuppressEMFTypes();
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_META_DATA:
        return isSuppressEMFMetaData();
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_MODEL_TAGS:
        return isSuppressEMFModelTags();
      case GenModelPackage.GEN_MODEL__SUPPRESS_INTERFACES:
        return isSuppressInterfaces();
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE:
        return getFeatureMapWrapperInterface();
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE:
        return getFeatureMapWrapperInternalInterface();
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS:
        return getFeatureMapWrapperClass();
      case GenModelPackage.GEN_MODEL__RUNTIME_COMPATIBILITY:
        return isRuntimeCompatibility();
      case GenModelPackage.GEN_MODEL__RICH_CLIENT_PLATFORM:
        return isRichClientPlatform();
      case GenModelPackage.GEN_MODEL__REFLECTIVE_DELEGATION:
        return isReflectiveDelegation();
      case GenModelPackage.GEN_MODEL__CODE_FORMATTING:
        return isCodeFormatting();
      case GenModelPackage.GEN_MODEL__COMMENT_FORMATTING:
        return isCommentFormatting();
      case GenModelPackage.GEN_MODEL__TESTS_DIRECTORY:
        return getTestsDirectory();
      case GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS:
        return getTestSuiteClass();
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_FIELD:
        return getBooleanFlagsField();
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS:
        return getBooleanFlagsReservedBits();
      case GenModelPackage.GEN_MODEL__IMPORTER_ID:
        return getImporterID();
      case GenModelPackage.GEN_MODEL__BUNDLE_MANIFEST:
        return isBundleManifest();
      case GenModelPackage.GEN_MODEL__FEATURE_DELEGATION:
        return getFeatureDelegation();
      case GenModelPackage.GEN_MODEL__CONTAINMENT_PROXIES:
        return isContainmentProxies();
      case GenModelPackage.GEN_MODEL__MINIMAL_REFLECTIVE_METHODS:
        return isMinimalReflectiveMethods();
      case GenModelPackage.GEN_MODEL__SUPPRESS_CONTAINMENT:
        return isSuppressContainment();
      case GenModelPackage.GEN_MODEL__SUPPRESS_NOTIFICATION:
        return isSuppressNotification();
      case GenModelPackage.GEN_MODEL__ARRAY_ACCESSORS:
        return isArrayAccessors();
      case GenModelPackage.GEN_MODEL__SUPPRESS_UNSETTABLE:
        return isSuppressUnsettable();
      case GenModelPackage.GEN_MODEL__FACADE_HELPER_CLASS:
        return getFacadeHelperClass();
      case GenModelPackage.GEN_MODEL__COMPLIANCE_LEVEL:
        return getComplianceLevel();
      case GenModelPackage.GEN_MODEL__SUPPRESS_GEN_MODEL_ANNOTATIONS:
        return isSuppressGenModelAnnotations();
      case GenModelPackage.GEN_MODEL__COPYRIGHT_FIELDS:
        return isCopyrightFields();
      case GenModelPackage.GEN_MODEL__BINARY_COMPATIBLE_REFLECTIVE_METHODS:
        return isBinaryCompatibleReflectiveMethods();
      case GenModelPackage.GEN_MODEL__PUBLIC_CONSTRUCTORS:
        return isPublicConstructors();
      case GenModelPackage.GEN_MODEL__TEMPLATE_PLUGIN_VARIABLES:
        return getTemplatePluginVariables();
      case GenModelPackage.GEN_MODEL__PROVIDER_ROOT_EXTENDS_CLASS:
        return getProviderRootExtendsClass();
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_ID:
        return getEditPluginID();
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_VARIABLES:
        return getEditPluginVariables();
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_ID:
        return getEditorPluginID();
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_VARIABLES:
        return getEditorPluginVariables();
      case GenModelPackage.GEN_MODEL__TESTS_PLUGIN_ID:
        return getTestsPluginID();
      case GenModelPackage.GEN_MODEL__TESTS_PLUGIN_VARIABLES:
        return getTestsPluginVariables();
      case GenModelPackage.GEN_MODEL__OPTIMIZED_HAS_CHILDREN:
        return isOptimizedHasChildren();
      case GenModelPackage.GEN_MODEL__TABLE_PROVIDERS:
        return isTableProviders();
      case GenModelPackage.GEN_MODEL__COLOR_PROVIDERS:
        return isColorProviders();
      case GenModelPackage.GEN_MODEL__FONT_PROVIDERS:
        return isFontProviders();
      case GenModelPackage.GEN_MODEL__RUNTIME_VERSION:
        return getRuntimeVersion();
      case GenModelPackage.GEN_MODEL__LANGUAGE:
        return getLanguage();
      case GenModelPackage.GEN_MODEL__PACKED_ENUMS:
        return isPackedEnums();
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
        return getGenPackages();
      case GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES:
        return getUsedGenPackages();
      case GenModelPackage.GEN_MODEL__INTERFACE_NAME_PATTERN:
        return getInterfaceNamePattern();
      case GenModelPackage.GEN_MODEL__CLASS_NAME_PATTERN:
        return getClassNamePattern();
      case GenModelPackage.GEN_MODEL__OPERATION_REFLECTION:
        return isOperationReflection();
      case GenModelPackage.GEN_MODEL__RICH_AJAX_PLATFORM:
        return isRichAjaxPlatform();
      case GenModelPackage.GEN_MODEL__RUNTIME_PLATFORM:
        return getRuntimePlatform();
      case GenModelPackage.GEN_MODEL__IMPORT_ORGANIZING:
        return isImportOrganizing();
      case GenModelPackage.GEN_MODEL__PLUGIN_KEY:
        return getPluginKey();
      case GenModelPackage.GEN_MODEL__DECORATION:
        return getDecoration();
      case GenModelPackage.GEN_MODEL__STYLE_PROVIDERS:
        return isStyleProviders();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_MODEL__COPYRIGHT_TEXT:
        setCopyrightText((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__MODEL_DIRECTORY:
        setModelDirectory((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__CREATION_COMMANDS:
        setCreationCommands((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__CREATION_ICONS:
        setCreationIcons((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__CREATION_SUBMENUS:
        setCreationSubmenus((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__EDIT_DIRECTORY:
        setEditDirectory((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY:
        setEditorDirectory((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_ID:
        setModelPluginID((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__TEMPLATE_DIRECTORY:
        setTemplateDirectory((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__RUNTIME_JAR:
        setRuntimeJar((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__FOREIGN_MODEL:
        getForeignModel().clear();
        getForeignModel().addAll((Collection<? extends String>)newValue);
        return;
      case GenModelPackage.GEN_MODEL__DYNAMIC_TEMPLATES:
        setDynamicTemplates((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__REDIRECTION:
        setRedirection((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__FORCE_OVERWRITE:
        setForceOverwrite((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__NON_EXTERNALIZED_STRING_TAG:
        setNonExternalizedStringTag((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__MODEL_NAME:
        setModelName((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_CLASS:
        setModelPluginClass((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_CLASS:
        setEditPluginClass((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_CLASS:
        setEditorPluginClass((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__UPDATE_CLASSPATH:
        setUpdateClasspath((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__GENERATE_SCHEMA:
        setGenerateSchema((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__NON_NLS_MARKERS:
        setNonNLSMarkers((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__STATIC_PACKAGES:
        getStaticPackages().clear();
        getStaticPackages().addAll((Collection<? extends String>)newValue);
        return;
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_VARIABLES:
        getModelPluginVariables().clear();
        getModelPluginVariables().addAll((Collection<? extends String>)newValue);
        return;
      case GenModelPackage.GEN_MODEL__ROOT_EXTENDS_INTERFACE:
        setRootExtendsInterface((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__ROOT_EXTENDS_CLASS:
        setRootExtendsClass((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__ROOT_IMPLEMENTS_INTERFACE:
        setRootImplementsInterface((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_TYPES:
        setSuppressEMFTypes((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_META_DATA:
        setSuppressEMFMetaData((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_MODEL_TAGS:
        setSuppressEMFModelTags((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_INTERFACES:
        setSuppressInterfaces((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE:
        setFeatureMapWrapperInterface((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE:
        setFeatureMapWrapperInternalInterface((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS:
        setFeatureMapWrapperClass((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__RUNTIME_COMPATIBILITY:
        setRuntimeCompatibility((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__RICH_CLIENT_PLATFORM:
        setRichClientPlatform((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__REFLECTIVE_DELEGATION:
        setReflectiveDelegation((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__CODE_FORMATTING:
        setCodeFormatting((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__COMMENT_FORMATTING:
        setCommentFormatting((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__TESTS_DIRECTORY:
        setTestsDirectory((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS:
        setTestSuiteClass((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_FIELD:
        setBooleanFlagsField((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS:
        setBooleanFlagsReservedBits((Integer)newValue);
        return;
      case GenModelPackage.GEN_MODEL__IMPORTER_ID:
        setImporterID((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__BUNDLE_MANIFEST:
        setBundleManifest((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__FEATURE_DELEGATION:
        setFeatureDelegation((GenDelegationKind)newValue);
        return;
      case GenModelPackage.GEN_MODEL__CONTAINMENT_PROXIES:
        setContainmentProxies((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__MINIMAL_REFLECTIVE_METHODS:
        setMinimalReflectiveMethods((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_CONTAINMENT:
        setSuppressContainment((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_NOTIFICATION:
        setSuppressNotification((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__ARRAY_ACCESSORS:
        setArrayAccessors((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_UNSETTABLE:
        setSuppressUnsettable((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__FACADE_HELPER_CLASS:
        setFacadeHelperClass((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__COMPLIANCE_LEVEL:
        setComplianceLevel((GenJDKLevel)newValue);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_GEN_MODEL_ANNOTATIONS:
        setSuppressGenModelAnnotations((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__COPYRIGHT_FIELDS:
        setCopyrightFields((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__BINARY_COMPATIBLE_REFLECTIVE_METHODS:
        setBinaryCompatibleReflectiveMethods((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__PUBLIC_CONSTRUCTORS:
        setPublicConstructors((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__TEMPLATE_PLUGIN_VARIABLES:
        getTemplatePluginVariables().clear();
        getTemplatePluginVariables().addAll((Collection<? extends String>)newValue);
        return;
      case GenModelPackage.GEN_MODEL__PROVIDER_ROOT_EXTENDS_CLASS:
        setProviderRootExtendsClass((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_ID:
        setEditPluginID((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_VARIABLES:
        getEditPluginVariables().clear();
        getEditPluginVariables().addAll((Collection<? extends String>)newValue);
        return;
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_ID:
        setEditorPluginID((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_VARIABLES:
        getEditorPluginVariables().clear();
        getEditorPluginVariables().addAll((Collection<? extends String>)newValue);
        return;
      case GenModelPackage.GEN_MODEL__TESTS_PLUGIN_ID:
        setTestsPluginID((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__TESTS_PLUGIN_VARIABLES:
        getTestsPluginVariables().clear();
        getTestsPluginVariables().addAll((Collection<? extends String>)newValue);
        return;
      case GenModelPackage.GEN_MODEL__OPTIMIZED_HAS_CHILDREN:
        setOptimizedHasChildren((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__TABLE_PROVIDERS:
        setTableProviders((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__COLOR_PROVIDERS:
        setColorProviders((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__FONT_PROVIDERS:
        setFontProviders((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__RUNTIME_VERSION:
        setRuntimeVersion((GenRuntimeVersion)newValue);
        return;
      case GenModelPackage.GEN_MODEL__LANGUAGE:
        setLanguage((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__PACKED_ENUMS:
        setPackedEnums((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
        getGenPackages().clear();
        getGenPackages().addAll((Collection<? extends GenPackage>)newValue);
        return;
      case GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES:
        getUsedGenPackages().clear();
        getUsedGenPackages().addAll((Collection<? extends GenPackage>)newValue);
        return;
      case GenModelPackage.GEN_MODEL__INTERFACE_NAME_PATTERN:
        setInterfaceNamePattern((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__CLASS_NAME_PATTERN:
        setClassNamePattern((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__OPERATION_REFLECTION:
        setOperationReflection((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__RICH_AJAX_PLATFORM:
        setRichAjaxPlatform((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__RUNTIME_PLATFORM:
        setRuntimePlatform((GenRuntimePlatform)newValue);
        return;
      case GenModelPackage.GEN_MODEL__IMPORT_ORGANIZING:
        setImportOrganizing((Boolean)newValue);
        return;
      case GenModelPackage.GEN_MODEL__PLUGIN_KEY:
        setPluginKey((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__DECORATION:
        setDecoration((GenDecoration)newValue);
        return;
      case GenModelPackage.GEN_MODEL__STYLE_PROVIDERS:
        setStyleProviders((Boolean)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_MODEL__COPYRIGHT_TEXT:
        setCopyrightText(COPYRIGHT_TEXT_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__MODEL_DIRECTORY:
        setModelDirectory(MODEL_DIRECTORY_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__CREATION_COMMANDS:
        setCreationCommands(CREATION_COMMANDS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__CREATION_ICONS:
        setCreationIcons(CREATION_ICONS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__CREATION_SUBMENUS:
        setCreationSubmenus(CREATION_SUBMENUS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__EDIT_DIRECTORY:
        unsetEditDirectory();
        return;
      case GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY:
        unsetEditorDirectory();
        return;
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_ID:
        setModelPluginID(MODEL_PLUGIN_ID_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__TEMPLATE_DIRECTORY:
        setTemplateDirectory(TEMPLATE_DIRECTORY_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__RUNTIME_JAR:
        setRuntimeJar(RUNTIME_JAR_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__FOREIGN_MODEL:
        getForeignModel().clear();
        return;
      case GenModelPackage.GEN_MODEL__DYNAMIC_TEMPLATES:
        setDynamicTemplates(DYNAMIC_TEMPLATES_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__REDIRECTION:
        setRedirection(REDIRECTION_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__FORCE_OVERWRITE:
        setForceOverwrite(FORCE_OVERWRITE_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__NON_EXTERNALIZED_STRING_TAG:
        setNonExternalizedStringTag(NON_EXTERNALIZED_STRING_TAG_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__MODEL_NAME:
        setModelName(MODEL_NAME_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_CLASS:
        setModelPluginClass(MODEL_PLUGIN_CLASS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_CLASS:
        unsetEditPluginClass();
        return;
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_CLASS:
        unsetEditorPluginClass();
        return;
      case GenModelPackage.GEN_MODEL__UPDATE_CLASSPATH:
        setUpdateClasspath(UPDATE_CLASSPATH_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__GENERATE_SCHEMA:
        setGenerateSchema(GENERATE_SCHEMA_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__NON_NLS_MARKERS:
        setNonNLSMarkers(NON_NLS_MARKERS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__STATIC_PACKAGES:
        getStaticPackages().clear();
        return;
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_VARIABLES:
        getModelPluginVariables().clear();
        return;
      case GenModelPackage.GEN_MODEL__ROOT_EXTENDS_INTERFACE:
        setRootExtendsInterface(ROOT_EXTENDS_INTERFACE_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__ROOT_EXTENDS_CLASS:
        setRootExtendsClass(ROOT_EXTENDS_CLASS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__ROOT_IMPLEMENTS_INTERFACE:
        setRootImplementsInterface(ROOT_IMPLEMENTS_INTERFACE_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_TYPES:
        setSuppressEMFTypes(SUPPRESS_EMF_TYPES_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_META_DATA:
        setSuppressEMFMetaData(SUPPRESS_EMF_META_DATA_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_MODEL_TAGS:
        setSuppressEMFModelTags(SUPPRESS_EMF_MODEL_TAGS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_INTERFACES:
        setSuppressInterfaces(SUPPRESS_INTERFACES_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE:
        setFeatureMapWrapperInterface(FEATURE_MAP_WRAPPER_INTERFACE_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE:
        setFeatureMapWrapperInternalInterface(FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS:
        setFeatureMapWrapperClass(FEATURE_MAP_WRAPPER_CLASS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__RUNTIME_COMPATIBILITY:
        setRuntimeCompatibility(RUNTIME_COMPATIBILITY_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__RICH_CLIENT_PLATFORM:
        setRichClientPlatform(RICH_CLIENT_PLATFORM_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__REFLECTIVE_DELEGATION:
        setReflectiveDelegation(REFLECTIVE_DELEGATION_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__CODE_FORMATTING:
        setCodeFormatting(CODE_FORMATTING_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__COMMENT_FORMATTING:
        setCommentFormatting(COMMENT_FORMATTING_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__TESTS_DIRECTORY:
        unsetTestsDirectory();
        return;
      case GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS:
        unsetTestSuiteClass();
        return;
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_FIELD:
        setBooleanFlagsField(BOOLEAN_FLAGS_FIELD_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS:
        setBooleanFlagsReservedBits(BOOLEAN_FLAGS_RESERVED_BITS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__IMPORTER_ID:
        setImporterID(IMPORTER_ID_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__BUNDLE_MANIFEST:
        setBundleManifest(BUNDLE_MANIFEST_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__FEATURE_DELEGATION:
        setFeatureDelegation(FEATURE_DELEGATION_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__CONTAINMENT_PROXIES:
        setContainmentProxies(CONTAINMENT_PROXIES_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__MINIMAL_REFLECTIVE_METHODS:
        setMinimalReflectiveMethods(MINIMAL_REFLECTIVE_METHODS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_CONTAINMENT:
        setSuppressContainment(SUPPRESS_CONTAINMENT_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_NOTIFICATION:
        setSuppressNotification(SUPPRESS_NOTIFICATION_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__ARRAY_ACCESSORS:
        setArrayAccessors(ARRAY_ACCESSORS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_UNSETTABLE:
        setSuppressUnsettable(SUPPRESS_UNSETTABLE_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__FACADE_HELPER_CLASS:
        setFacadeHelperClass(FACADE_HELPER_CLASS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__COMPLIANCE_LEVEL:
        setComplianceLevel(COMPLIANCE_LEVEL_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__SUPPRESS_GEN_MODEL_ANNOTATIONS:
        setSuppressGenModelAnnotations(SUPPRESS_GEN_MODEL_ANNOTATIONS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__COPYRIGHT_FIELDS:
        setCopyrightFields(COPYRIGHT_FIELDS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__BINARY_COMPATIBLE_REFLECTIVE_METHODS:
        setBinaryCompatibleReflectiveMethods(BINARY_COMPATIBLE_REFLECTIVE_METHODS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__PUBLIC_CONSTRUCTORS:
        setPublicConstructors(PUBLIC_CONSTRUCTORS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__TEMPLATE_PLUGIN_VARIABLES:
        getTemplatePluginVariables().clear();
        return;
      case GenModelPackage.GEN_MODEL__PROVIDER_ROOT_EXTENDS_CLASS:
        setProviderRootExtendsClass(PROVIDER_ROOT_EXTENDS_CLASS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_ID:
        setEditPluginID(EDIT_PLUGIN_ID_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_VARIABLES:
        getEditPluginVariables().clear();
        return;
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_ID:
        setEditorPluginID(EDITOR_PLUGIN_ID_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_VARIABLES:
        getEditorPluginVariables().clear();
        return;
      case GenModelPackage.GEN_MODEL__TESTS_PLUGIN_ID:
        setTestsPluginID(TESTS_PLUGIN_ID_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__TESTS_PLUGIN_VARIABLES:
        getTestsPluginVariables().clear();
        return;
      case GenModelPackage.GEN_MODEL__OPTIMIZED_HAS_CHILDREN:
        setOptimizedHasChildren(OPTIMIZED_HAS_CHILDREN_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__TABLE_PROVIDERS:
        setTableProviders(TABLE_PROVIDERS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__COLOR_PROVIDERS:
        setColorProviders(COLOR_PROVIDERS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__FONT_PROVIDERS:
        setFontProviders(FONT_PROVIDERS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__RUNTIME_VERSION:
        unsetRuntimeVersion();
        return;
      case GenModelPackage.GEN_MODEL__LANGUAGE:
        setLanguage(LANGUAGE_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__PACKED_ENUMS:
        setPackedEnums(PACKED_ENUMS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
        getGenPackages().clear();
        return;
      case GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES:
        getUsedGenPackages().clear();
        return;
      case GenModelPackage.GEN_MODEL__INTERFACE_NAME_PATTERN:
        setInterfaceNamePattern(INTERFACE_NAME_PATTERN_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__CLASS_NAME_PATTERN:
        setClassNamePattern(CLASS_NAME_PATTERN_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__OPERATION_REFLECTION:
        setOperationReflection(OPERATION_REFLECTION_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__RICH_AJAX_PLATFORM:
        setRichAjaxPlatform(RICH_AJAX_PLATFORM_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__RUNTIME_PLATFORM:
        setRuntimePlatform(RUNTIME_PLATFORM_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__IMPORT_ORGANIZING:
        setImportOrganizing(IMPORT_ORGANIZING_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__PLUGIN_KEY:
        setPluginKey(PLUGIN_KEY_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__DECORATION:
        setDecoration(DECORATION_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__STYLE_PROVIDERS:
        setStyleProviders(STYLE_PROVIDERS_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_MODEL__COPYRIGHT_TEXT:
        return COPYRIGHT_TEXT_EDEFAULT == null ? copyrightText != null : !COPYRIGHT_TEXT_EDEFAULT.equals(copyrightText);
      case GenModelPackage.GEN_MODEL__MODEL_DIRECTORY:
        return MODEL_DIRECTORY_EDEFAULT == null ? modelDirectory != null : !MODEL_DIRECTORY_EDEFAULT.equals(modelDirectory);
      case GenModelPackage.GEN_MODEL__CREATION_COMMANDS:
        return creationCommands != CREATION_COMMANDS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__CREATION_ICONS:
        return creationIcons != CREATION_ICONS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__CREATION_SUBMENUS:
        return creationSubmenus != CREATION_SUBMENUS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__EDIT_DIRECTORY:
        return isSetEditDirectory();
      case GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY:
        return isSetEditorDirectory();
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_ID:
        return MODEL_PLUGIN_ID_EDEFAULT == null ? modelPluginID != null : !MODEL_PLUGIN_ID_EDEFAULT.equals(modelPluginID);
      case GenModelPackage.GEN_MODEL__TEMPLATE_DIRECTORY:
        return TEMPLATE_DIRECTORY_EDEFAULT == null ? templateDirectory != null : !TEMPLATE_DIRECTORY_EDEFAULT.equals(templateDirectory);
      case GenModelPackage.GEN_MODEL__RUNTIME_JAR:
        return runtimeJar != RUNTIME_JAR_EDEFAULT;
      case GenModelPackage.GEN_MODEL__FOREIGN_MODEL:
        return foreignModel != null && !foreignModel.isEmpty();
      case GenModelPackage.GEN_MODEL__DYNAMIC_TEMPLATES:
        return dynamicTemplates != DYNAMIC_TEMPLATES_EDEFAULT;
      case GenModelPackage.GEN_MODEL__REDIRECTION:
        return REDIRECTION_EDEFAULT == null ? redirection != null : !REDIRECTION_EDEFAULT.equals(redirection);
      case GenModelPackage.GEN_MODEL__FORCE_OVERWRITE:
        return forceOverwrite != FORCE_OVERWRITE_EDEFAULT;
      case GenModelPackage.GEN_MODEL__NON_EXTERNALIZED_STRING_TAG:
        return NON_EXTERNALIZED_STRING_TAG_EDEFAULT == null ? nonExternalizedStringTag != null : !NON_EXTERNALIZED_STRING_TAG_EDEFAULT.equals(nonExternalizedStringTag);
      case GenModelPackage.GEN_MODEL__MODEL_NAME:
        return MODEL_NAME_EDEFAULT == null ? modelName != null : !MODEL_NAME_EDEFAULT.equals(modelName);
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_CLASS:
        return MODEL_PLUGIN_CLASS_EDEFAULT == null ? modelPluginClass != null : !MODEL_PLUGIN_CLASS_EDEFAULT.equals(modelPluginClass);
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_CLASS:
        return isSetEditPluginClass();
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_CLASS:
        return isSetEditorPluginClass();
      case GenModelPackage.GEN_MODEL__UPDATE_CLASSPATH:
        return updateClasspath != UPDATE_CLASSPATH_EDEFAULT;
      case GenModelPackage.GEN_MODEL__GENERATE_SCHEMA:
        return generateSchema != GENERATE_SCHEMA_EDEFAULT;
      case GenModelPackage.GEN_MODEL__NON_NLS_MARKERS:
        return nonNLSMarkers != NON_NLS_MARKERS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__STATIC_PACKAGES:
        return staticPackages != null && !staticPackages.isEmpty();
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_VARIABLES:
        return modelPluginVariables != null && !modelPluginVariables.isEmpty();
      case GenModelPackage.GEN_MODEL__ROOT_EXTENDS_INTERFACE:
        return ROOT_EXTENDS_INTERFACE_EDEFAULT == null ? rootExtendsInterface != null : !ROOT_EXTENDS_INTERFACE_EDEFAULT.equals(rootExtendsInterface);
      case GenModelPackage.GEN_MODEL__ROOT_EXTENDS_CLASS:
        return ROOT_EXTENDS_CLASS_EDEFAULT == null ? rootExtendsClass != null : !ROOT_EXTENDS_CLASS_EDEFAULT.equals(rootExtendsClass);
      case GenModelPackage.GEN_MODEL__ROOT_IMPLEMENTS_INTERFACE:
        return ROOT_IMPLEMENTS_INTERFACE_EDEFAULT == null ? rootImplementsInterface != null : !ROOT_IMPLEMENTS_INTERFACE_EDEFAULT.equals(rootImplementsInterface);
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_TYPES:
        return suppressEMFTypes != SUPPRESS_EMF_TYPES_EDEFAULT;
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_META_DATA:
        return suppressEMFMetaData != SUPPRESS_EMF_META_DATA_EDEFAULT;
      case GenModelPackage.GEN_MODEL__SUPPRESS_EMF_MODEL_TAGS:
        return suppressEMFModelTags != SUPPRESS_EMF_MODEL_TAGS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__SUPPRESS_INTERFACES:
        return suppressInterfaces != SUPPRESS_INTERFACES_EDEFAULT;
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE:
        return FEATURE_MAP_WRAPPER_INTERFACE_EDEFAULT == null ? featureMapWrapperInterface != null : !FEATURE_MAP_WRAPPER_INTERFACE_EDEFAULT.equals(featureMapWrapperInterface);
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE:
        return FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE_EDEFAULT == null ? featureMapWrapperInternalInterface != null : !FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE_EDEFAULT.equals(featureMapWrapperInternalInterface);
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS:
        return FEATURE_MAP_WRAPPER_CLASS_EDEFAULT == null ? featureMapWrapperClass != null : !FEATURE_MAP_WRAPPER_CLASS_EDEFAULT.equals(featureMapWrapperClass);
      case GenModelPackage.GEN_MODEL__RUNTIME_COMPATIBILITY:
        return runtimeCompatibility != RUNTIME_COMPATIBILITY_EDEFAULT;
      case GenModelPackage.GEN_MODEL__RICH_CLIENT_PLATFORM:
        return isRichClientPlatform() != RICH_CLIENT_PLATFORM_EDEFAULT;
      case GenModelPackage.GEN_MODEL__REFLECTIVE_DELEGATION:
        return isReflectiveDelegation() != REFLECTIVE_DELEGATION_EDEFAULT;
      case GenModelPackage.GEN_MODEL__CODE_FORMATTING:
        return codeFormatting != CODE_FORMATTING_EDEFAULT;
      case GenModelPackage.GEN_MODEL__COMMENT_FORMATTING:
        return commentFormatting != COMMENT_FORMATTING_EDEFAULT;
      case GenModelPackage.GEN_MODEL__TESTS_DIRECTORY:
        return isSetTestsDirectory();
      case GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS:
        return isSetTestSuiteClass();
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_FIELD:
        return BOOLEAN_FLAGS_FIELD_EDEFAULT == null ? booleanFlagsField != null : !BOOLEAN_FLAGS_FIELD_EDEFAULT.equals(booleanFlagsField);
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS:
        return booleanFlagsReservedBits != BOOLEAN_FLAGS_RESERVED_BITS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__IMPORTER_ID:
        return IMPORTER_ID_EDEFAULT == null ? importerID != null : !IMPORTER_ID_EDEFAULT.equals(importerID);
      case GenModelPackage.GEN_MODEL__BUNDLE_MANIFEST:
        return bundleManifest != BUNDLE_MANIFEST_EDEFAULT;
      case GenModelPackage.GEN_MODEL__FEATURE_DELEGATION:
        return featureDelegation != FEATURE_DELEGATION_EDEFAULT;
      case GenModelPackage.GEN_MODEL__CONTAINMENT_PROXIES:
        return containmentProxies != CONTAINMENT_PROXIES_EDEFAULT;
      case GenModelPackage.GEN_MODEL__MINIMAL_REFLECTIVE_METHODS:
        return minimalReflectiveMethods != MINIMAL_REFLECTIVE_METHODS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__SUPPRESS_CONTAINMENT:
        return suppressContainment != SUPPRESS_CONTAINMENT_EDEFAULT;
      case GenModelPackage.GEN_MODEL__SUPPRESS_NOTIFICATION:
        return suppressNotification != SUPPRESS_NOTIFICATION_EDEFAULT;
      case GenModelPackage.GEN_MODEL__ARRAY_ACCESSORS:
        return arrayAccessors != ARRAY_ACCESSORS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__SUPPRESS_UNSETTABLE:
        return suppressUnsettable != SUPPRESS_UNSETTABLE_EDEFAULT;
      case GenModelPackage.GEN_MODEL__FACADE_HELPER_CLASS:
        return FACADE_HELPER_CLASS_EDEFAULT == null ? facadeHelperClass != null : !FACADE_HELPER_CLASS_EDEFAULT.equals(facadeHelperClass);
      case GenModelPackage.GEN_MODEL__COMPLIANCE_LEVEL:
        return complianceLevel != COMPLIANCE_LEVEL_EDEFAULT;
      case GenModelPackage.GEN_MODEL__SUPPRESS_GEN_MODEL_ANNOTATIONS:
        return suppressGenModelAnnotations != SUPPRESS_GEN_MODEL_ANNOTATIONS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__COPYRIGHT_FIELDS:
        return copyrightFields != COPYRIGHT_FIELDS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__BINARY_COMPATIBLE_REFLECTIVE_METHODS:
        return binaryCompatibleReflectiveMethods != BINARY_COMPATIBLE_REFLECTIVE_METHODS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__PUBLIC_CONSTRUCTORS:
        return publicConstructors != PUBLIC_CONSTRUCTORS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__TEMPLATE_PLUGIN_VARIABLES:
        return templatePluginVariables != null && !templatePluginVariables.isEmpty();
      case GenModelPackage.GEN_MODEL__PROVIDER_ROOT_EXTENDS_CLASS:
        return PROVIDER_ROOT_EXTENDS_CLASS_EDEFAULT == null ? providerRootExtendsClass != null : !PROVIDER_ROOT_EXTENDS_CLASS_EDEFAULT.equals(providerRootExtendsClass);
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_ID:
        return EDIT_PLUGIN_ID_EDEFAULT == null ? editPluginID != null : !EDIT_PLUGIN_ID_EDEFAULT.equals(editPluginID);
      case GenModelPackage.GEN_MODEL__EDIT_PLUGIN_VARIABLES:
        return editPluginVariables != null && !editPluginVariables.isEmpty();
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_ID:
        return EDITOR_PLUGIN_ID_EDEFAULT == null ? editorPluginID != null : !EDITOR_PLUGIN_ID_EDEFAULT.equals(editorPluginID);
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_VARIABLES:
        return editorPluginVariables != null && !editorPluginVariables.isEmpty();
      case GenModelPackage.GEN_MODEL__TESTS_PLUGIN_ID:
        return TESTS_PLUGIN_ID_EDEFAULT == null ? testsPluginID != null : !TESTS_PLUGIN_ID_EDEFAULT.equals(testsPluginID);
      case GenModelPackage.GEN_MODEL__TESTS_PLUGIN_VARIABLES:
        return testsPluginVariables != null && !testsPluginVariables.isEmpty();
      case GenModelPackage.GEN_MODEL__OPTIMIZED_HAS_CHILDREN:
        return optimizedHasChildren != OPTIMIZED_HAS_CHILDREN_EDEFAULT;
      case GenModelPackage.GEN_MODEL__TABLE_PROVIDERS:
        return tableProviders != TABLE_PROVIDERS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__COLOR_PROVIDERS:
        return colorProviders != COLOR_PROVIDERS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__FONT_PROVIDERS:
        return fontProviders != FONT_PROVIDERS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__RUNTIME_VERSION:
        return isSetRuntimeVersion();
      case GenModelPackage.GEN_MODEL__LANGUAGE:
        return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
      case GenModelPackage.GEN_MODEL__PACKED_ENUMS:
        return packedEnums != PACKED_ENUMS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
        return genPackages != null && !genPackages.isEmpty();
      case GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES:
        return usedGenPackages != null && !usedGenPackages.isEmpty();
      case GenModelPackage.GEN_MODEL__INTERFACE_NAME_PATTERN:
        return INTERFACE_NAME_PATTERN_EDEFAULT == null ? interfaceNamePattern != null : !INTERFACE_NAME_PATTERN_EDEFAULT.equals(interfaceNamePattern);
      case GenModelPackage.GEN_MODEL__CLASS_NAME_PATTERN:
        return CLASS_NAME_PATTERN_EDEFAULT == null ? classNamePattern != null : !CLASS_NAME_PATTERN_EDEFAULT.equals(classNamePattern);
      case GenModelPackage.GEN_MODEL__OPERATION_REFLECTION:
        return operationReflection != OPERATION_REFLECTION_EDEFAULT;
      case GenModelPackage.GEN_MODEL__RICH_AJAX_PLATFORM:
        return isRichAjaxPlatform() != RICH_AJAX_PLATFORM_EDEFAULT;
      case GenModelPackage.GEN_MODEL__RUNTIME_PLATFORM:
        return runtimePlatform != RUNTIME_PLATFORM_EDEFAULT;
      case GenModelPackage.GEN_MODEL__IMPORT_ORGANIZING:
        return importOrganizing != IMPORT_ORGANIZING_EDEFAULT;
      case GenModelPackage.GEN_MODEL__PLUGIN_KEY:
        return PLUGIN_KEY_EDEFAULT == null ? pluginKey != null : !PLUGIN_KEY_EDEFAULT.equals(pluginKey);
      case GenModelPackage.GEN_MODEL__DECORATION:
        return decoration != DECORATION_EDEFAULT;
      case GenModelPackage.GEN_MODEL__STYLE_PROVIDERS:
        return styleProviders != STYLE_PROVIDERS_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (copyrightText: ");
    result.append(copyrightText);
    result.append(", modelDirectory: ");
    result.append(modelDirectory);
    result.append(", creationCommands: ");
    result.append(creationCommands);
    result.append(", creationIcons: ");
    result.append(creationIcons);
    result.append(", creationSubmenus: ");
    result.append(creationSubmenus);
    result.append(", editDirectory: ");
    if (editDirectoryESet) result.append(editDirectory); else result.append("<unset>");
    result.append(", editorDirectory: ");
    if (editorDirectoryESet) result.append(editorDirectory); else result.append("<unset>");
    result.append(", modelPluginID: ");
    result.append(modelPluginID);
    result.append(", templateDirectory: ");
    result.append(templateDirectory);
    result.append(", runtimeJar: ");
    result.append(runtimeJar);
    result.append(", foreignModel: ");
    result.append(foreignModel);
    result.append(", dynamicTemplates: ");
    result.append(dynamicTemplates);
    result.append(", redirection: ");
    result.append(redirection);
    result.append(", forceOverwrite: ");
    result.append(forceOverwrite);
    result.append(", nonExternalizedStringTag: ");
    result.append(nonExternalizedStringTag);
    result.append(", modelName: ");
    result.append(modelName);
    result.append(", modelPluginClass: ");
    result.append(modelPluginClass);
    result.append(", editPluginClass: ");
    if (editPluginClassESet) result.append(editPluginClass); else result.append("<unset>");
    result.append(", editorPluginClass: ");
    if (editorPluginClassESet) result.append(editorPluginClass); else result.append("<unset>");
    result.append(", updateClasspath: ");
    result.append(updateClasspath);
    result.append(", generateSchema: ");
    result.append(generateSchema);
    result.append(", nonNLSMarkers: ");
    result.append(nonNLSMarkers);
    result.append(", staticPackages: ");
    result.append(staticPackages);
    result.append(", modelPluginVariables: ");
    result.append(modelPluginVariables);
    result.append(", rootExtendsInterface: ");
    result.append(rootExtendsInterface);
    result.append(", rootExtendsClass: ");
    result.append(rootExtendsClass);
    result.append(", rootImplementsInterface: ");
    result.append(rootImplementsInterface);
    result.append(", suppressEMFTypes: ");
    result.append(suppressEMFTypes);
    result.append(", suppressEMFMetaData: ");
    result.append(suppressEMFMetaData);
    result.append(", suppressEMFModelTags: ");
    result.append(suppressEMFModelTags);
    result.append(", suppressInterfaces: ");
    result.append(suppressInterfaces);
    result.append(", featureMapWrapperInterface: ");
    result.append(featureMapWrapperInterface);
    result.append(", featureMapWrapperInternalInterface: ");
    result.append(featureMapWrapperInternalInterface);
    result.append(", featureMapWrapperClass: ");
    result.append(featureMapWrapperClass);
    result.append(", runtimeCompatibility: ");
    result.append(runtimeCompatibility);
    result.append(", codeFormatting: ");
    result.append(codeFormatting);
    result.append(", commentFormatting: ");
    result.append(commentFormatting);
    result.append(", testsDirectory: ");
    if (testsDirectoryESet) result.append(testsDirectory); else result.append("<unset>");
    result.append(", testSuiteClass: ");
    if (testSuiteClassESet) result.append(testSuiteClass); else result.append("<unset>");
    result.append(", booleanFlagsField: ");
    result.append(booleanFlagsField);
    result.append(", booleanFlagsReservedBits: ");
    result.append(booleanFlagsReservedBits);
    result.append(", importerID: ");
    result.append(importerID);
    result.append(", bundleManifest: ");
    result.append(bundleManifest);
    result.append(", featureDelegation: ");
    result.append(featureDelegation);
    result.append(", containmentProxies: ");
    result.append(containmentProxies);
    result.append(", minimalReflectiveMethods: ");
    result.append(minimalReflectiveMethods);
    result.append(", suppressContainment: ");
    result.append(suppressContainment);
    result.append(", suppressNotification: ");
    result.append(suppressNotification);
    result.append(", arrayAccessors: ");
    result.append(arrayAccessors);
    result.append(", suppressUnsettable: ");
    result.append(suppressUnsettable);
    result.append(", facadeHelperClass: ");
    result.append(facadeHelperClass);
    result.append(", complianceLevel: ");
    result.append(complianceLevel);
    result.append(", suppressGenModelAnnotations: ");
    result.append(suppressGenModelAnnotations);
    result.append(", copyrightFields: ");
    result.append(copyrightFields);
    result.append(", binaryCompatibleReflectiveMethods: ");
    result.append(binaryCompatibleReflectiveMethods);
    result.append(", publicConstructors: ");
    result.append(publicConstructors);
    result.append(", templatePluginVariables: ");
    result.append(templatePluginVariables);
    result.append(", providerRootExtendsClass: ");
    result.append(providerRootExtendsClass);
    result.append(", editPluginID: ");
    result.append(editPluginID);
    result.append(", editPluginVariables: ");
    result.append(editPluginVariables);
    result.append(", editorPluginID: ");
    result.append(editorPluginID);
    result.append(", editorPluginVariables: ");
    result.append(editorPluginVariables);
    result.append(", testsPluginID: ");
    result.append(testsPluginID);
    result.append(", testsPluginVariables: ");
    result.append(testsPluginVariables);
    result.append(", optimizedHasChildren: ");
    result.append(optimizedHasChildren);
    result.append(", tableProviders: ");
    result.append(tableProviders);
    result.append(", colorProviders: ");
    result.append(colorProviders);
    result.append(", fontProviders: ");
    result.append(fontProviders);
    result.append(", runtimeVersion: ");
    if (runtimeVersionESet) result.append(runtimeVersion); else result.append("<unset>");
    result.append(", language: ");
    result.append(language);
    result.append(", packedEnums: ");
    result.append(packedEnums);
    result.append(", interfaceNamePattern: ");
    result.append(interfaceNamePattern);
    result.append(", classNamePattern: ");
    result.append(classNamePattern);
    result.append(", operationReflection: ");
    result.append(operationReflection);
    result.append(", runtimePlatform: ");
    result.append(runtimePlatform);
    result.append(", importOrganizing: ");
    result.append(importOrganizing);
    result.append(", pluginKey: ");
    result.append(pluginKey);
    result.append(", decoration: ");
    result.append(decoration);
    result.append(", styleProviders: ");
    result.append(styleProviders);
    result.append(')');
    return result.toString();
  }

  static protected String getProjectPath(String path)
  {
    if (path == null)
    {
      return null;
    }
    else
    {
      int beginIndex = path.startsWith("/") ? 1 : 0;
      int endIndex = path.indexOf("/", beginIndex);
      if (endIndex != -1)
      {
        return path.substring(0, endIndex);
      }
      else
      {
        return path;
      }
    }
  }

  public String getModelProjectDirectory()
  {
    return getProjectPath(getModelDirectory());
  }

  public String getEditProjectDirectory()
  {
    return getProjectPath(getEditDirectory());
  }

  public String getEditorProjectDirectory()
  {
    return getProjectPath(getEditorDirectory());
  }

  public String getTestsProjectDirectory()
  {
    return getProjectPath(getTestsDirectory());
  }

  public boolean sameModelEditProject()
  {
    String modelProjectDirectory = getModelProjectDirectory();
    return modelProjectDirectory != null && modelProjectDirectory.equals(getEditProjectDirectory());
  }

  public boolean sameEditEditorProject()
  {
    String editProjectDirectory = getEditProjectDirectory();
    return editProjectDirectory != null && editProjectDirectory.equals(getEditorProjectDirectory());
  }

  public boolean sameModelEditorProject()
  {
    String modelProjectDirectory = getModelProjectDirectory();
    return modelProjectDirectory != null && modelProjectDirectory.equals(getEditorProjectDirectory());
  }

  public boolean sameModelTestsProject()
  {
    // Different than the Edit and Editor projects, this method is invoked while
    // generating the model plugin xml.
    return getTestsDirectory() == null ? false : getModelProjectDirectory().equals(getTestsProjectDirectory());
  }

  public boolean sameEditTestsProject()
  {
    return getTestsDirectory() == null ? false : getEditProjectDirectory().equals(getTestsProjectDirectory());
  }

  public boolean sameEditorTestsProject()
  {
    return getTestsDirectory() == null ? false : getEditorProjectDirectory().equals(getTestsProjectDirectory());
  }

  public String getEditIconsDirectory()
  {
    return getEditProjectDirectory() + "/icons";
  }

  public String getEditorIconsDirectory()
  {
    return getEditorProjectDirectory() + "/icons";
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEditPluginIDGen()
  {
    return editPluginID;
  }

  public String getEditPluginID()
  {
    if (sameModelEditProject())
    {
      return getModelPluginID();
    }

    String result = getEditPluginIDGen();
    if (isBlank(result))
    {
      String model = getModelPluginID();
      if (!isBlank(model))
      {
        result = model + ".edit";
      }
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEditPluginID(String newEditPluginID)
  {
    String oldEditPluginID = editPluginID;
    editPluginID = newEditPluginID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__EDIT_PLUGIN_ID, oldEditPluginID, editPluginID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getEditPluginVariables()
  {
    if (editPluginVariables == null)
    {
      editPluginVariables = new EDataTypeUniqueEList<String>(String.class, this, GenModelPackage.GEN_MODEL__EDIT_PLUGIN_VARIABLES);
    }
    return editPluginVariables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEditorPluginIDGen()
  {
    return editorPluginID;
  }

  public String getEditorPluginID()
  {
    if (sameModelEditorProject())
    {
      return getModelPluginID();
    }
    if (sameEditEditorProject())
    {
      return getEditPluginID();
    }

    String result = getEditorPluginIDGen();
    if (isBlank(result))
    {
      String model = getModelPluginID();
      if (!isBlank(model))
      {
        result = model + ".editor";
      }
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEditorPluginID(String newEditorPluginID)
  {
    String oldEditorPluginID = editorPluginID;
    editorPluginID = newEditorPluginID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_ID, oldEditorPluginID, editorPluginID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getEditorPluginVariables()
  {
    if (editorPluginVariables == null)
    {
      editorPluginVariables = new EDataTypeUniqueEList<String>(String.class, this, GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_VARIABLES);
    }
    return editorPluginVariables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTestsPluginIDGen()
  {
    return testsPluginID;
  }

  public String getTestsPluginID()
  {
    if (sameModelTestsProject())
    {
      return getModelPluginID();
    }
    if (sameEditTestsProject())
    {
      return getEditPluginID();
    }
    if (sameEditorTestsProject())
    {
      return getEditorPluginID();
    }

    String result = getTestsPluginIDGen();
    if (isBlank(result))
    {
      String model = getModelPluginID();
      if (!isBlank(model))
      {
        result = model + ".tests";
      }
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTestsPluginID(String newTestsPluginID)
  {
    String oldTestsPluginID = testsPluginID;
    testsPluginID = newTestsPluginID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__TESTS_PLUGIN_ID, oldTestsPluginID, testsPluginID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getTestsPluginVariables()
  {
    if (testsPluginVariables == null)
    {
      testsPluginVariables = new EDataTypeUniqueEList<String>(String.class, this, GenModelPackage.GEN_MODEL__TESTS_PLUGIN_VARIABLES);
    }
    return testsPluginVariables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isOptimizedHasChildren()
  {
    return optimizedHasChildren;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOptimizedHasChildren(boolean newOptimizedHasChildren)
  {
    boolean oldOptimizedHasChildren = optimizedHasChildren;
    optimizedHasChildren = newOptimizedHasChildren;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__OPTIMIZED_HAS_CHILDREN, oldOptimizedHasChildren, optimizedHasChildren));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTableProviders()
  {
    return tableProviders;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTableProviders(boolean newTableProviders)
  {
    boolean oldTableProviders = tableProviders;
    tableProviders = newTableProviders;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__TABLE_PROVIDERS, oldTableProviders, tableProviders));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isColorProviders()
  {
    return colorProviders;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColorProviders(boolean newColorProviders)
  {
    boolean oldColorProviders = colorProviders;
    colorProviders = newColorProviders;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__COLOR_PROVIDERS, oldColorProviders, colorProviders));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isFontProviders()
  {
    return fontProviders;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFontProviders(boolean newFontProviders)
  {
    boolean oldFontProviders = fontProviders;
    fontProviders = newFontProviders;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__FONT_PROVIDERS, oldFontProviders, fontProviders));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenRuntimeVersion getRuntimeVersionGen()
  {
    return runtimeVersion;
  }

  public GenRuntimeVersion getRuntimeVersion()
  {
    return
      runtimeVersionESet || !GenJDKLevel.JDK14_LITERAL.equals(getComplianceLevel()) ?
        getRuntimeVersionGen() :
        GenRuntimeVersion.EMF22;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRuntimeVersion(GenRuntimeVersion newRuntimeVersion)
  {
    GenRuntimeVersion oldRuntimeVersion = runtimeVersion;
    runtimeVersion = newRuntimeVersion == null ? RUNTIME_VERSION_EDEFAULT : newRuntimeVersion;
    boolean oldRuntimeVersionESet = runtimeVersionESet;
    runtimeVersionESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__RUNTIME_VERSION, oldRuntimeVersion, runtimeVersion, !oldRuntimeVersionESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetRuntimeVersion()
  {
    GenRuntimeVersion oldRuntimeVersion = runtimeVersion;
    boolean oldRuntimeVersionESet = runtimeVersionESet;
    runtimeVersion = RUNTIME_VERSION_EDEFAULT;
    runtimeVersionESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_MODEL__RUNTIME_VERSION, oldRuntimeVersion, RUNTIME_VERSION_EDEFAULT, oldRuntimeVersionESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetRuntimeVersion()
  {
    return runtimeVersionESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLanguage()
  {
    return language;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLanguageGen(String newLanguage)
  {
    String oldLanguage = language;
    language = newLanguage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__LANGUAGE, oldLanguage, language));
  }

  public void setLanguage(String newLanguage)
  {
    setLanguageGen(newLanguage);
    locale = null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPackedEnums()
  {
    return packedEnums;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPackedEnums(boolean newPackedEnums)
  {
    boolean oldPackedEnums = packedEnums;
    packedEnums = newPackedEnums;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__PACKED_ENUMS, oldPackedEnums, packedEnums));
  }

  public boolean hasModelPluginClass()
  {
    return !sameModelEditProject() && !sameModelEditorProject() &&
      !isBlank(getModelPluginClass());
  }

  protected String getModelPluginClassToUse()
  {
    String result = getModelPluginClass();
    if (isBlank(result) && sameModelEditProject())
    {
      result = getEditPluginClass();
    }
    if (isBlank(result) && sameModelEditorProject())
    {
      result = getEditorPluginClass();
    }
    return result;
  }

  protected String getEditPluginClassToUse()
  {
    String result = null;
    if (sameModelEditProject())
    {
      result = getModelPluginClass();
    }
    if (isBlank(result))
    {
      result = getEditPluginClass();
    }
    if (isBlank(result) && sameEditEditorProject())
    {
      result = getEditorPluginClass();
    }
    return result;
  }

  public String getEditPluginDirectory()
  {
    String result =  getEditDirectory();
    String plugin = null;
    if (sameModelEditProject())
    {
      plugin = getModelPluginClass();
      result = getModelDirectory();
    }
    if (isBlank(plugin))
    {
      plugin = getEditPluginClass();
      result = getEditDirectory();
    }
    if (isBlank(plugin) && sameEditEditorProject())
    {
      plugin = getEditorPluginClass();
      result = getEditorDirectory();
    }
    return result;
  }

  protected String getEditorPluginClassToUse()
  {
    String result = null;
    if (sameModelEditorProject())
    {
      result = getModelPluginClass();
    }
    if (isBlank(result) && sameEditEditorProject())
    {
      result = getEditPluginClass();
    }
    if (isBlank(result))
    {
      result = getEditorPluginClass();
    }
    return result;
  }

  public String getEditorPluginDirectory()
  {
    String result =  getEditorDirectory();
    String plugin = null;
    if (sameModelEditorProject())
    {
      plugin = getModelPluginClass();
      result = getModelDirectory();
    }
    if (isBlank(plugin) && sameEditEditorProject())
    {
      plugin = getEditPluginClass();
      result = getEditDirectory();
    }
    if (isBlank(plugin))
    {
      plugin = getEditorPluginClass();
      result = getEditorDirectory();
    }
    return result;
  }

  protected String getPluginClassName(String baseName, String defaultSuffix)
  {
    if (baseName == null)
    {
      String modelName = getModelName();
      baseName = (isBlank(modelName) ? "" : modelName) + defaultSuffix;
    }
    else
    {
      baseName = CodeGenUtil.getSimpleClassName(baseName);
    }
    return baseName;
  }

  protected String getPluginPackageName(String baseName, String pluginID)
  {
    if (baseName == null)
    {
      baseName = createPackageName(pluginID);
    }
    else
    {
      int index = baseName.lastIndexOf(".");
      if (index == -1)
      {
        baseName = createPackageName(pluginID);
      }
      else
      {
        baseName = CodeGenUtil.getPackageName(baseName);
      }
    }
    return baseName;
  }

  protected String createPackageName(String text)
  {
    return text != null ? text.toLowerCase(getLocale()) : "";
  }

  public String getModelPluginPackageName()
  {
    return getPluginPackageName(getModelPluginClassToUse(), getModelPluginID());
  }

  public String getModelPluginClassName()
  {
    return getPluginClassName(getModelPluginClassToUse(), "Plugin");
  }

  public String getQualifiedModelPluginClassName()
  {
    return getModelPluginPackageName() + "." + getModelPluginClassName();
  }

  public String getEditPluginPackageName()
  {
    return getPluginPackageName(getEditPluginClassToUse(), getEditPluginID());
  }

  public String getEditPluginClassName()
  {
    return getPluginClassName(getEditPluginClassToUse(), "EditPlugin");
  }

  public String getQualifiedEditPluginClassName()
  {
    return getEditPluginPackageName() + "." + getEditPluginClassName();
  }

  public String getEditorPluginPackageName()
  {
    return getPluginPackageName(getEditorPluginClassToUse(), getEditorPluginID());
  }

  public String getEditorPluginClassName()
  {
    return getPluginClassName(getEditorPluginClassToUse(), "EditorPlugin");
  }

  public String getQualifiedEditorPluginClassName()
  {
    return getEditorPluginPackageName() + "." + getEditorPluginClassName();
  }

  public String getQualifiedEditorAdvisorClassName()
  {
    return getEditorPluginPackageName() + "." + getEditorAdvisorClassName();
  }

  public String getEditorAdvisorClassName()
  {
    String modelName = getModelName();
    return (modelName != null ? getModelName() : "") + "EditorAdvisor";
  }

  public String getQualifiedEditorEntryPointClassName()
  {
    return getEditorPluginPackageName() + "." + getEditorEntryPointClassName();
  }

  public String getEditorEntryPointClassName()
  {
    String modelName = getModelName();
    return (modelName != null ? getModelName() : "") + "EditorEntryPoint";
  }

  public boolean hasTestSuiteClass()
  {
    return !isBlank(getTestSuiteClass());
  }

  public String getTestSuitePackageName()
  {
    return getPluginPackageName(getTestSuiteClass(), getTestsPluginID());
  }

  public String getTestSuiteClassName()
  {
    return getPluginClassName(getTestSuiteClass(), "AllTests");
  }

  public String getQualifiedTestSuiteClassName()
  {
    return getTestSuitePackageName() + "." + getTestSuiteClassName();
  }

  protected void getAllGenPackagesWithClassifiersHelper(List<GenPackage> result, List<GenPackage> genPackages)
  {
    for (GenPackage genPackage : genPackages)
    {
      if (genPackage.hasClassifiers())
      {
        result.add(genPackage);
      }
      getAllGenPackagesWithClassifiersHelper(result, genPackage.getNestedGenPackages());
    }
  }

  public List<GenPackage> getAllGenPackagesWithClassifiers()
  {
    List<GenPackage> result = new ArrayList<GenPackage>();
    getAllGenPackagesWithClassifiersHelper(result, getGenPackages());
    return result;
  }

  public List<GenPackage> getAllUsedGenPackagesWithClassifiers()
  {
    List<GenPackage> result = new ArrayList<GenPackage>();
    getAllGenPackagesWithClassifiersHelper(result, getUsedGenPackages());
    return result;
  }

  public List<GenPackage> getAllGenAndUsedGenPackagesWithClassifiers()
  {
    List<GenPackage> result = new ArrayList<GenPackage>();
    getAllGenPackagesWithClassifiersHelper(result, getGenPackages());
    getAllGenPackagesWithClassifiersHelper(result, getUsedGenPackages());
    return result;
  }

  public List<GenPackage> getAllGenUsedAndStaticGenPackagesWithClassifiers()
  {
    List<GenPackage> result = new ArrayList<GenPackage>();
    getAllGenPackagesWithClassifiersHelper(result, getGenPackages());
    getAllGenPackagesWithClassifiersHelper(result, getUsedGenPackages());
    getAllGenPackagesWithClassifiersHelper(result, getStaticGenPackages());
    return result;
  }

  public List<GenPackage> getAllGenPackagesWithConcreteClasses()
  {
    List<GenPackage> result = new ArrayList<GenPackage>(getAllGenPackagesWithClassifiers());
    for (Iterator<GenPackage> i = result.iterator(); i.hasNext(); )
    {
      if (!i.next().hasConcreteClasses())
      {
        i.remove();
      }
    }
    return result;
  }

  public List<String> getModelQualifiedPackageNames()
  {
    EList<String> packageNames = sameModelTestsProject() ?
      (EList<String>)getTestsQualifiedPackageNames() :
      new UniqueEList<String>();

    TreeIterator<GenPackage> genPackagesIterator =
      new AbstractTreeIterator<GenPackage>(getGenPackages(), false)
      {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("unchecked")
        @Override
        protected Iterator<GenPackage> getChildren(Object object)
        {
          return object instanceof Collection<?> ?
            ((Collection<GenPackage>)object).iterator() :
            ((GenPackage)object).getNestedGenPackages().iterator();
        }
      };
    while(genPackagesIterator.hasNext())
    {
      GenPackage genPackage = genPackagesIterator.next();
      addQualifiedModelPackageNames(packageNames, genPackage);
    }

    String pluginClassPackage = CodeGenUtil.getPackageName(getModelPluginClassToUse());
    if (!isBlank(pluginClassPackage))
    {
      packageNames.add(pluginClassPackage);
    }

    ECollections.sort(packageNames);
    return packageNames;
  }

  protected void addQualifiedModelPackageNames(List<String> packageNames, GenPackage genPackage)
  {
    if (genPackage.hasClassifiers())
    {
      packageNames.add(genPackage.getInterfacePackageName());
      packageNames.add(genPackage.getClassPackageName());
      if (genPackage.isAdapterFactory() && !genPackage.getGenClasses().isEmpty() ||
            genPackage.hasClassifiers() && genPackage.hasConstraints() ||
            genPackage.getResource() != GenResourceKind.NONE_LITERAL)
      {
        packageNames.add(genPackage.getUtilitiesPackageName());
      }
      if (!isBlank(genPackage.getMetaDataPackageSuffix()))
      {
        packageNames.add(genPackage.getReflectionPackageName());
      }
    }
  }

  public List<String> getModelRequiredPlugins()
  {
    List<String> result = new UniqueEList<String>();
    if (getRuntimePlatform() == GenRuntimePlatform.GWT)
    {
      result.add("org.eclipse.emf.gwt.ecore");
    }
    else
    {
      result.add(needsRuntimeCompatibility() ? "org.eclipse.core.runtime.compatibility" : "org.eclipse.core.runtime");
      result.add("org.eclipse.emf.ecore");
    }
    result.addAll(getEffectiveModelPluginIDs());

    TreeIterator<GenPackage> genPackagesIterator =
      new AbstractTreeIterator<GenPackage>(getGenPackages(), false)
      {
        private static final long serialVersionUID = 1L;

        @Override
        @SuppressWarnings("unchecked")
        protected Iterator<GenPackage> getChildren(Object object)
        {
          return object instanceof Collection<?> ?
            ((Collection<GenPackage>)object).iterator() :
            ((GenPackage)object).getNestedGenPackages().iterator();
        }
      };
    while (genPackagesIterator.hasNext())
    {
      GenPackage genPackage = genPackagesIterator.next();
      if (genPackage.getResource() != GenResourceKind.NONE_LITERAL || genPackage.isLoadingInitialization() || genPackage.isContentType())
      {
        result.add("org.eclipse.emf.ecore.xmi");
        break;
      }
    }
    for (GenPackage genPackage : getUsedGenPackages())
    {
      result.add(genPackage.getGenModel().getModelPluginID());
    }

    if (sameModelTestsProject())
    {
      result.add("org.eclipse.emf.ecore.xmi");
      result.add("org.junit");
    }

    return result;
  }

  public List<String> getEditQualifiedPackageNames()
  {
    EList<String> packageNames = sameModelEditProject() ?
      (EList<String>)getModelQualifiedPackageNames() :
        sameEditTestsProject() ?
          (EList<String>)getTestsQualifiedPackageNames() :
          new UniqueEList<String>();

    TreeIterator<GenPackage> genPackagesIterator =
      new AbstractTreeIterator<GenPackage>(getGenPackages(), false)
      {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("unchecked")
        @Override
        protected Iterator<GenPackage> getChildren(Object object)
        {
          return object instanceof Collection<?> ?
            ((Collection<GenPackage>)object).iterator() :
            ((GenPackage)object).getNestedGenPackages().iterator();
        }
      };
    while(genPackagesIterator.hasNext())
    {
      GenPackage genPackage = genPackagesIterator.next();
      addQualifiedEditPackageNames(packageNames, genPackage);
    }

    String pluginClassPackage = CodeGenUtil.getPackageName(getEditPluginClassToUse());
    if (!isBlank(pluginClassPackage))
    {
      packageNames.add(pluginClassPackage);
    }

    ECollections.sort(packageNames);
    return packageNames;
  }

  protected void addQualifiedEditPackageNames(List<String> packageNames, GenPackage genPackage)
  {
    if (!genPackage.getGenClasses().isEmpty())
    {
      packageNames.add(genPackage.getProviderPackageName());
    }
  }

  public List<String> getEditRequiredPlugins()
  {
    List<String> result = new UniqueEList<String>();
    if (getRuntimePlatform() != GenRuntimePlatform.GWT)
    {
      result.add(needsRuntimeCompatibility() ? "org.eclipse.core.runtime.compatibility" : "org.eclipse.core.runtime");
    }
    result.addAll(getEffectiveEditPluginIDs());

    if (!sameModelEditProject())
    {
      for (GenPackage genPackage : getGenPackages())
      {
        result.add(genPackage.getGenModel().getModelPluginID());
      }
    }
    else
    {
      result.addAll(getModelRequiredPlugins());
    }

    result.add(getRuntimePlatform() == GenRuntimePlatform.GWT ? "org.eclipse.emf.gwt.edit" : "org.eclipse.emf.edit");

    if (sameEditTestsProject())
    {
      result.add("org.eclipse.emf.ecore.xmi");
      result.add("org.junit");
    }

    for (GenPackage genPackage : getUsedGenPackages())
    {
      GenModel genModel = genPackage.getGenModel();
      result.add(genModel.getModelPluginID());
      if (genModel.hasEditSupport())
      {
        result.add(genModel.getEditPluginID());
      }
    }
    return result;
  }

  public List<String> getEditorQualifiedPackageNames()
  {
    EList<String> packageNames = sameModelEditorProject() || sameEditEditorProject() ?
      (EList<String>)getEditQualifiedPackageNames() :
        sameEditorTestsProject() ?
          (EList<String>)getTestsQualifiedPackageNames() :
          new UniqueEList<String>();

    if (getRuntimePlatform() != GenRuntimePlatform.GWT)
    {
      TreeIterator<GenPackage> genPackagesIterator =
        new AbstractTreeIterator<GenPackage>(getGenPackages(), false)
        {
          private static final long serialVersionUID = 1L;

          @SuppressWarnings("unchecked")
          @Override
          protected Iterator<GenPackage> getChildren(Object object)
          {
            return object instanceof Collection<?> ?
              ((Collection<GenPackage>)object).iterator() :
              ((GenPackage)object).getNestedGenPackages().iterator();
          }
        };
      while(genPackagesIterator.hasNext())
      {
        GenPackage genPackage = genPackagesIterator.next();
        addQualifiedEditorPackageNames(packageNames, genPackage);
      }
    }

    String pluginClassPackage = CodeGenUtil.getPackageName(getEditorPluginClassToUse());
    if (!isBlank(pluginClassPackage))
    {
      packageNames.add(pluginClassPackage);
    }

    ECollections.sort(packageNames);
    return packageNames;
  }

  protected void addQualifiedEditorPackageNames(List<String> packageNames, GenPackage genPackage)
  {
    if (genPackage.hasConcreteClasses())
    {
      packageNames.add(genPackage.getPresentationPackageName());
    }
  }

  public List<String> getEditorRequiredPlugins()
  {
    List<String> result = new UniqueEList<String>();
    if (getRuntimePlatform() != GenRuntimePlatform.GWT)
    {
      result.add(needsRuntimeCompatibility() ? "org.eclipse.core.runtime.compatibility" : "org.eclipse.core.runtime");
      if (!isRichClientPlatform())
      {
        result.add("org.eclipse.core.resources");
      }
    }
    result.addAll(getEffectiveEditorPluginIDs());

    if (!sameEditEditorProject())
    {
      for (GenPackage genPackage : getGenPackages())
      {
        GenModel genModel = genPackage.getGenModel();
        if (genModel.hasEditSupport())
        {
          result.add(genModel.getEditPluginID());
        }
      }
    }
    else
    {
      result.addAll(getEditRequiredPlugins());
    }
    if (getRuntimePlatform() != GenRuntimePlatform.GWT)
    {
      result.add("org.eclipse.emf.ecore.xmi");
    }
    if (isRichAjaxPlatform())
    {
      result.add("org.eclipse.core.commands");
    }
    else
    {
      result.add(getRuntimePlatform() == GenRuntimePlatform.GWT ? "org.eclipse.emf.gwt.edit.ui" : "org.eclipse.emf.edit.ui");
    }
    if (!isRichClientPlatform() && getRuntimePlatform() != GenRuntimePlatform.GWT)
    {
      result.add("org.eclipse.ui.ide");
    }

    if (sameEditorTestsProject())
    {
      result.add("org.junit");
    }

    for (GenPackage genPackage : getUsedGenPackages())
    {
      GenModel genModel = genPackage.getGenModel();
      if (genModel.hasEditSupport())
      {
        result.add(genModel.getEditPluginID());
      }
    }
    return result;
  }

  public List<String> getTestsQualifiedPackageNames()
  {
    EList<String> packageNames = new UniqueEList<String>();

    TreeIterator<GenPackage> genPackagesIterator =
      new AbstractTreeIterator<GenPackage>(getGenPackages(), false)
      {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("unchecked")
        @Override
        protected Iterator<GenPackage> getChildren(Object object)
        {
          return object instanceof Collection<?> ?
            ((Collection<GenPackage>)object).iterator() :
            ((GenPackage)object).getNestedGenPackages().iterator();
        }
      };
    while(genPackagesIterator.hasNext())
    {
      GenPackage genPackage = genPackagesIterator.next();
      addQualifiedTestsPackageNames(packageNames, genPackage);
    }

    String pluginClassPackage = CodeGenUtil.getPackageName(getTestSuiteClass());
    if (!isBlank(pluginClassPackage))
    {
      packageNames.add(pluginClassPackage);
    }

    ECollections.sort(packageNames);
    return packageNames;
  }

  protected void addQualifiedTestsPackageNames(List<String> packageNames, GenPackage genPackage)
  {
    if (genPackage.hasClassifiers())
    {
      packageNames.add(genPackage.getTestsPackageName());
    }
  }

  public List<String> getTestsRequiredPlugins()
  {
    List<String> result = new UniqueEList<String>();
    result.add(needsRuntimeCompatibility() ? "org.eclipse.core.runtime.compatibility" : "org.eclipse.core.runtime");
    result.addAll(getEffectiveTestsPluginIDs());

    result.add(getModelPluginID());
    for (GenPackage genPackage : getUsedGenPackages())
    {
      GenModel genModel = genPackage.getGenModel();
      result.add(genModel.getModelPluginID());
    }

    result.add("org.eclipse.emf.ecore.xmi");
    result.add("org.junit");

    return result;
  }

  public List<String> getEditResourceDelegateImportedPluginClassNames()
  {
    List<String> result = new UniqueEList<String>();
    for (GenPackage genPackage : getUsedGenPackages())
    {
      GenModel genModel = genPackage.getGenModel();
      if (genModel.hasEditSupport())
      {
        result.add(genModel.getImportedName(genModel.getQualifiedEditPluginClassName()));
      }
    }
    return result;
  }

  public boolean reconcile(GenModel oldGenModelVersion)
  {
    boolean result = false;
    if (oldGenModelVersion != null)
    {
      for (GenPackage genPackage : getGenPackages())
      {
        for (GenPackage oldGenPackageVersion : oldGenModelVersion.getGenPackages())
        {
          if (genPackage.reconcile(oldGenPackageVersion))
          {
            result = true;
            break;
          }
        }
      }

      reconcileSettings(oldGenModelVersion);
    }

    return result;
  }

  protected void reconcileSettings(GenModel oldGenModelVersion)
  {
    if (oldGenModelVersion.isSetEditDirectory())
    {
      setEditDirectory(oldGenModelVersion.getEditDirectory());
    }
    else
    {
      unsetEditDirectory();
    }
    if (oldGenModelVersion.isSetEditPluginClass())
    {
      setEditPluginClass(oldGenModelVersion.getEditPluginClass());
    }
    else
    {
      unsetEditPluginClass();
    }
    if (oldGenModelVersion.isSetEditorDirectory())
    {
      setEditorDirectory(oldGenModelVersion.getEditorDirectory());
    }
    else
    {
      unsetEditorDirectory();
    }
    if (oldGenModelVersion.isSetEditorPluginClass())
    {
      setEditorPluginClass(oldGenModelVersion.getEditorPluginClass());
    }
    else
    {
      unsetEditorPluginClass();
    }
    if (oldGenModelVersion.isSetTestsDirectory())
    {
      setTestsDirectory(oldGenModelVersion.getTestsDirectory());
    }
    else
    {
      unsetTestsDirectory();
    }
    if (oldGenModelVersion.isSetTestSuiteClass())
    {
      setTestSuiteClass(oldGenModelVersion.getTestSuiteClass());
    }
    else
    {
      unsetTestSuiteClass();
    }

    setCopyrightText(oldGenModelVersion.getCopyrightText());
    setCopyrightFields(oldGenModelVersion.isCopyrightFields());
    setModelDirectory(oldGenModelVersion.getModelDirectory());
    setCreationCommands(oldGenModelVersion.isCreationCommands());
    setCreationIcons(oldGenModelVersion.isCreationIcons());
    setCreationSubmenus(oldGenModelVersion.isCreationSubmenus());
    setModelPluginID(oldGenModelVersion.getModelPluginID());
    setTemplateDirectory(oldGenModelVersion.getTemplateDirectory());
    setRuntimeJar(oldGenModelVersion.isRuntimeJar());
    setBundleManifest(oldGenModelVersion.isBundleManifest());

    // EATM
    // Foreign Model

    setDynamicTemplates(oldGenModelVersion.isDynamicTemplates());
    setRedirection(oldGenModelVersion.getRedirection());
    setForceOverwrite(oldGenModelVersion.isForceOverwrite());
    setNonExternalizedStringTag(oldGenModelVersion.getNonExternalizedStringTag());

    setModelName(oldGenModelVersion.getModelName());

    setModelPluginClass(oldGenModelVersion.getModelPluginClass());
    setUpdateClasspath(oldGenModelVersion.isUpdateClasspath());
    setGenerateSchema(oldGenModelVersion.isGenerateSchema());
    setNonNLSMarkers(oldGenModelVersion.isNonNLSMarkers());
    getModelPluginVariables().addAll(oldGenModelVersion.getModelPluginVariables());
    getStaticPackages().addAll(oldGenModelVersion.getStaticPackages());
    setRootExtendsInterface(oldGenModelVersion.getRootExtendsInterface());
    setRootImplementsInterface(oldGenModelVersion.getRootImplementsInterface());
    setRootExtendsClass(oldGenModelVersion.getRootExtendsClass());
    setSuppressEMFTypes(oldGenModelVersion.isSuppressEMFTypes());
    setFeatureMapWrapperInterface(oldGenModelVersion.getFeatureMapWrapperInterface());
    setFeatureMapWrapperInternalInterface(oldGenModelVersion.getFeatureMapWrapperInternalInterface());
    setFeatureMapWrapperClass(oldGenModelVersion.getFeatureMapWrapperClass());

    setRuntimeCompatibility(oldGenModelVersion.isRuntimeCompatibility());
    setCodeFormatting(oldGenModelVersion.isCodeFormatting());

    setBooleanFlagsField(oldGenModelVersion.getBooleanFlagsField());
    setBooleanFlagsReservedBits(oldGenModelVersion.getBooleanFlagsReservedBits());

    setFeatureDelegation(oldGenModelVersion.getFeatureDelegation());
    setContainmentProxies(oldGenModelVersion.isContainmentProxies());
    setMinimalReflectiveMethods(oldGenModelVersion.isMinimalReflectiveMethods());
    setBinaryCompatibleReflectiveMethods(oldGenModelVersion.isBinaryCompatibleReflectiveMethods());

    setSuppressEMFMetaData(oldGenModelVersion.isSuppressEMFMetaData());
    setSuppressEMFModelTags(oldGenModelVersion.isSuppressEMFModelTags());
    setSuppressInterfaces(oldGenModelVersion.isSuppressInterfaces());
    setSuppressNotification(oldGenModelVersion.isSuppressNotification());
    setSuppressContainment(oldGenModelVersion.isSuppressContainment());
    setSuppressUnsettable(oldGenModelVersion.isSuppressUnsettable());
    setArrayAccessors(oldGenModelVersion.isArrayAccessors());

    reconcileGenAnnotations(oldGenModelVersion);

    setComplianceLevel(oldGenModelVersion.getComplianceLevel());

    setSuppressGenModelAnnotations(oldGenModelVersion.isSuppressGenModelAnnotations());
    setPublicConstructors(oldGenModelVersion.isPublicConstructors());

    getTemplatePluginVariables().addAll(oldGenModelVersion.getTemplatePluginVariables());

    if (oldGenModelVersion.eIsSet(GenModelPackage.Literals.GEN_MODEL__EDIT_PLUGIN_ID))
    {
      setEditPluginID(oldGenModelVersion.getEditPluginID());
    }
    if (oldGenModelVersion.eIsSet(GenModelPackage.Literals.GEN_MODEL__EDITOR_PLUGIN_ID))
    {
      setEditorPluginID(oldGenModelVersion.getEditorPluginID());
    }
    if (oldGenModelVersion.eIsSet(GenModelPackage.Literals.GEN_MODEL__TESTS_PLUGIN_ID))
    {
      setTestsPluginID(oldGenModelVersion.getTestsPluginID());
    }

    getEditPluginVariables().addAll(oldGenModelVersion.getEditPluginVariables());
    getEditorPluginVariables().addAll(oldGenModelVersion.getEditorPluginVariables());
    getTestsPluginVariables().addAll(oldGenModelVersion.getTestsPluginVariables());

    setProviderRootExtendsClass(oldGenModelVersion.getProviderRootExtendsClass());

    setOptimizedHasChildren(oldGenModelVersion.isOptimizedHasChildren());
    setTableProviders(oldGenModelVersion.isTableProviders());
    setColorProviders(oldGenModelVersion.isColorProviders());
    setFontProviders(oldGenModelVersion.isFontProviders());

    if (oldGenModelVersion.isSetRuntimeVersion())
    {
      setRuntimeVersion(oldGenModelVersion.getRuntimeVersion());
    }

    setLanguage(oldGenModelVersion.getLanguage());
    setPackedEnums(oldGenModelVersion.isPackedEnums());
    setInterfaceNamePattern(oldGenModelVersion.getInterfaceNamePattern());
    setClassNamePattern(oldGenModelVersion.getClassNamePattern());
    setOperationReflection(oldGenModelVersion.isOperationReflection());
    setRuntimePlatform(oldGenModelVersion.getRuntimePlatform());

    setCommentFormatting(oldGenModelVersion.isCommentFormatting());
    setImportOrganizing(oldGenModelVersion.isImportOrganizing());

    if (oldGenModelVersion.eIsSet(GenModelPackage.Literals.GEN_MODEL__PLUGIN_KEY))
    {
      setPluginKey(oldGenModelVersion.getPluginKey());
    }

    setDecoration(oldGenModelVersion.getDecoration());
    setStyleProviders(oldGenModelVersion.isStyleProviders());
  }

  public boolean reconcile()
  {
    clearCache();
    EList<GenPackage> genPackages = getGenPackages();
    for (Iterator<GenPackage> i = genPackages.iterator(); i.hasNext(); )
    {
      GenPackage genPackage = i.next();
      if (!genPackage.reconcile())
      {
        i.remove();
      }
    }
    EList<GenPackage> usedGenPackages = getUsedGenPackages();
    for (Iterator<GenPackage> i = usedGenPackages.iterator(); i.hasNext(); )
    {
      GenPackage genPackage = i.next();
      if (!genPackage.reconcile())
      {
        i.remove();
      }
    }
    List<EPackage> missingEPackages = new UniqueEList<EPackage>(getMissingPackages());
    usedGenPackages.addAll(computeMissingUsedGenPackages(missingEPackages));

    for (EPackage ePackage : missingEPackages)
    {
      GenPackage genPackage = createGenPackage();
      genPackage.setEcorePackage(ePackage);
      genPackages.add(genPackage);
      if (!genPackage.reconcile())
      {
        genPackages.remove(genPackage);
      }
    }

    return !genPackages.isEmpty();
  }

  private void clearCache()
  {
    if (eClassifierToGenClassifierMap != null)
    {
      eClassifierToGenClassifierMap.clear();
    }
    if (ePackageToGenPackageMap != null)
    {
      ePackageToGenPackageMap.clear();
    }
  }

  public List<GenPackage> computeMissingUsedGenPackages()
  {
    List<EPackage> missingEPackages = new UniqueEList<EPackage>(getMissingPackages());
    return computeMissingUsedGenPackages(missingEPackages);
  }

  protected List<GenPackage> computeMissingUsedGenPackages(List<EPackage> missingEPackages)
  {
    if (!missingEPackages.isEmpty())
    {
      List<GenModel> allGenModels = new UniqueEList.FastCompare<GenModel>();
      allGenModels.add(this);
      for (int i = 0; i < allGenModels.size(); i++)
      {
        GenModel genModel = allGenModels.get(i);
        for (int j = 0; j < genModel.getUsedGenPackages().size(); j++)
        {
          GenPackage genPackage = genModel.getUsedGenPackages().get(j);
          if (genPackage.getGenModel() != null)
          {
            allGenModels.add(genPackage.getGenModel());
          }
        }
      }

      List<GenPackage> usedGenPackages = new UniqueEList.FastCompare<GenPackage>();
      for (int i = 0; i < missingEPackages.size(); i++)
      {
        EPackage ePackage = missingEPackages.get(i);
        for (int j = 0; j < allGenModels.size(); j++)
        {
          GenModel genModel = allGenModels.get(j);
          GenPackage genPackage = genModel.findGenPackage(ePackage);
          if (genPackage != null)
          {
            if (usedGenPackages.add(genPackage))
            {
              getMissingPackagesHelper(missingEPackages, Collections.singletonList(genPackage));
            }
            missingEPackages.remove(i--);
            break;
          }
        }
      }
      return usedGenPackages;
    }
    else
    {
      return Collections.emptyList();
    }
  }

  public List<EPackage> getMissingPackages()
  {
    List<EPackage> ePackages = new UniqueEList<EPackage>();
    getMissingPackagesHelper(ePackages, getGenPackages());
    getMissingPackagesHelper(ePackages, getUsedGenPackages());
    return ePackages;
  }

  protected void getMissingPackagesHelper(List<EPackage> ePackages, List<GenPackage> genPackages)
  {
    for (GenPackage genPackage : genPackages)
    {
      EPackage ePackage = genPackage.getEcorePackage();
      if (ePackage != null)
      {
        for (Iterator<EObject> j = ePackage.eAllContents(); j.hasNext();)
        {
          EObject eObject = j.next();
          for (Iterator<EObject> k = eObject.eCrossReferences().iterator(); k.hasNext(); )
          {
            EObject o = k.next();
            if (o instanceof EClassifier)
            {
              EClassifier eClassifier = (EClassifier)o;
              if (findGenClassifier(eClassifier) == null)
              {
                ePackages.add(eClassifier.getEPackage());
              }
            }
          }
        }
      }
    }
  }


  public boolean hasXMLDependency()
  {
    for (GenPackage genPackage : getAllGenPackagesWithClassifiers())
    {
      switch (genPackage.getResource().getValue())
      {
        case GenResourceKind.XML:
        case GenResourceKind.XMI:
        {
          return true;
        }
      }
    }
    return false;
  }

  public String getXMLEncodingChoices()
  {
    return CodeGenEcorePlugin.INSTANCE.getString("_UI_XMLEncodingChoices");
  }

  public String getIndentation(StringBuffer stringBuffer)
  {
    int index = stringBuffer.length();
    LOOP:
    while (--index >= 0)
    {
      switch (stringBuffer.charAt(index))
      {
        case '\n':
        case '\r':
        {
          break LOOP;
        }
      }
    }
    return stringBuffer.substring(index + 1);
  }

  public List<GenFeature> getAllGenFeatures()
  {
    List<GenFeature> result = new ArrayList<GenFeature>();

    // Any features from one package that delegate to features in another.
    //
    List<GenFeature> delegated = new ArrayList<GenFeature>();

    for (GenPackage genPackage : getAllGenAndUsedGenPackagesWithClassifiers())
    {
      if (genPackage.getGenModel() == this || !genPackage.getGenModel().hasEditSupport())
      {
        for (GenFeature genFeature : genPackage.getAllGenFeatures())
        {
          List<GenFeature> addTo = genFeature.getGenPackage() == genPackage ? result : delegated;
          addTo.add(genFeature);
        }
      }
    }

    // If there are features delegating across packages, add only those that aren't already being generated.
    //
    if (!delegated.isEmpty())
    {
      addNonDuplicates(result, delegated, null);
    }
    return result;
  }

  public List<GenFeature> getFilteredAllGenFeatures()
  {
    ArrayList<GenFeature> result = new ArrayList<GenFeature>();

    // We need to filer out duplicates in the unlikely event that we have two
    // features with the same class-qualified name. We'll only generate one property
    // string in that case and let the user add the second one manually, if necessary.
    //
    Set<String> noDupSet = new HashSet<String>();
    for (GenFeature genFeature : getAllGenFeatures())
    {
      if (isCreationCommands() || genFeature.isProperty())
      {
        if (noDupSet.add(genFeature.getGenClass().getName() + "_" + genFeature.getName()))
        {
          result.add(genFeature);
        }
      }
    }
    return result;
  }

  /**
   * @deprecated In EMF 2.2, the Generator-based design renders this field obsolete. It will be removed after 2.2.
   */
  @SuppressWarnings("rawtypes")
  @Deprecated
  protected Map codeFormatterOptions = null;

  /**
   * Set the code formatter options to be used to {@link #createCodeFormatter create} a new code formatter.
   *
   * @deprecated In EMF 2.2, the {@link org.eclipse.emf.codegen.ecore.generator.Generator.Options Generator.Options} should be used to
   * record code formatter options in order to be used via the new Generator-based design. This method will be removed after 2.2.
   */
  @SuppressWarnings("rawtypes")
  @Deprecated
  public void setCodeFormatterOptions(Map options)
  {
    codeFormatterOptions = options;
  }

  /**
   * Creates and returns a new JDT code formatter.
   *
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter GeneratorAdapter} should be used to
   * implement code generation. {@link org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter AbstractGeneratorAdapter} provides
   * an equivalent to this method. This method will be removed after 2.2.
   */
  @Deprecated
  public CodeFormatter createCodeFormatter()
  {
    return ToolFactory.createCodeFormatter(codeFormatterOptions);
  }

  public boolean isBooleanFlagsEnabled()
  {
    return !isBlank(getBooleanFlagsField()) && !isReflectiveDelegation();
  }

  public GenModel createGenModel()
  {
    return GenModelFactory.eINSTANCE.createGenModel();
  }

  public GenPackage createGenPackage()
  {
    return GenModelFactory.eINSTANCE.createGenPackage();
  }

  public GenClass createGenClass()
  {
    return GenModelFactory.eINSTANCE.createGenClass();
  }

  public GenFeature createGenFeature()
  {
    return GenModelFactory.eINSTANCE.createGenFeature();
  }

  public GenEnum createGenEnum()
  {
    return GenModelFactory.eINSTANCE.createGenEnum();
  }

  public GenEnumLiteral createGenEnumLiteral()
  {
    return GenModelFactory.eINSTANCE.createGenEnumLiteral();
  }

  public GenDataType createGenDataType()
  {
    return GenModelFactory.eINSTANCE.createGenDataType();
  }

  public GenOperation createGenOperation()
  {
    return GenModelFactory.eINSTANCE.createGenOperation();
  }

  public GenParameter createGenParameter()
  {
    return GenModelFactory.eINSTANCE.createGenParameter();
  }

  public GenTypeParameter createGenTypeParameter()
  {
    return GenModelFactory.eINSTANCE.createGenTypeParameter();
  }

  public GenAnnotation createGenAnnotation()
  {
    return GenModelFactory.eINSTANCE.createGenAnnotation();
  }

  public GenBase create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case GenModelPackage.GEN_MODEL: return createGenModel();
      case GenModelPackage.GEN_PACKAGE: return createGenPackage();
      case GenModelPackage.GEN_CLASS: return createGenClass();
      case GenModelPackage.GEN_FEATURE: return createGenFeature();
      case GenModelPackage.GEN_ENUM: return createGenEnum();
      case GenModelPackage.GEN_ENUM_LITERAL: return createGenEnumLiteral();
      case GenModelPackage.GEN_DATA_TYPE: return createGenDataType();
      case GenModelPackage.GEN_OPERATION: return createGenOperation();
      case GenModelPackage.GEN_PARAMETER: return createGenParameter();
      case GenModelPackage.GEN_ANNOTATION: return createGenAnnotation();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid GenModel classifier");
    }
  }

  public Set<String> getPropertyCategories()
  {
    Set<String> categories = new HashSet<String>();
    for (GenFeature genFeature : getFilteredAllGenFeatures())
    {
      String category = genFeature.getPropertyCategory();

      if (!isBlank(category))
      {
        categories.add(category);
      }
    }
    return categories;
  }

  public boolean hasLocalGenModel()
  {
    if (eResource() != null && eResource().getURI() != null)
    {
      URI genModelURI = eResource().getURI();
      URI modelDirectory = URI.createURI(getModelDirectory());
      return
        genModelURI.isPlatformResource() &&
           modelDirectory.segmentCount() > 0 &&
           genModelURI.segment(1).equals(modelDirectory.segment(0));
    }
    else
    {
      return false;
    }
  }

  public String getRelativeGenModelLocation()
  {
    URI genModelURI = eResource().getURI();
    String result = genModelURI.deresolve(genModelURI.trimSegments(genModelURI.segmentCount() - 2).appendSegment("")).toString();
    return result;
  }

  public String getPropertyCategoryKey(String category)
  {
    return "_UI_" + CodeGenUtil.validJavaIdentifier(category) + "PropertyCategory";
  }

  public boolean isVirtualDelegation()
  {
    return getFeatureDelegation() == GenDelegationKind.VIRTUAL_LITERAL;
  }

  public boolean isDynamicDelegation()
  {
    return getFeatureDelegation() == GenDelegationKind.DYNAMIC_LITERAL;
  }

  public boolean useClassOverrideAnnotation()
  {
    return getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
  }

  public boolean useGenerics()
  {
    return getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
  }

  @Override
  public GenClassifier findGenClassifier(EClassifier classifier)
  {
    return super.findGenClassifier(classifier);
  }

  @Override
  public GenFeature findGenFeature(EStructuralFeature feature)
  {
    return super.findGenFeature(feature);
  }

  @Override
  public GenOperation findGenOperation(EOperation operation)
  {
    return super.findGenOperation(operation);
  }

  public GenTypeParameter findGenTypeParameter(ETypeParameter eTypeParameter)
  {
    for (EObject eObject = eTypeParameter.eContainer(); eObject != null; eObject = eObject.eContainer())
    {
      if (eObject instanceof EOperation)
      {
        EOperation eOperation = (EOperation)eObject;
        int index = eOperation.getETypeParameters().indexOf(eTypeParameter);
        if (index != -1)
        {
          GenOperation genOperation = findGenOperation(eOperation);
          return genOperation.getGenTypeParameters().get(index);
        }
      }
      else if (eObject instanceof EClassifier)
      {
        EClassifier eClassifier = (EClassifier)eObject;
        int index = eClassifier.getETypeParameters().indexOf(eTypeParameter);
        if (index != -1)
        {
          GenClassifier genClassifier = findGenClassifier(eClassifier);
          return genClassifier.getGenTypeParameters().get(index);
        }
      }
    }
    return null;
  }

  public boolean isSuppressedAnnotation(String source)
  {
    return isSuppressGenModelAnnotations() && GenModelPackage.eNS_URI.equals(source);
  }

  @Override
  protected String getCopyright(boolean includeGenModelCopyrightTextAsDefault)
  {
    for (GenPackage genPackage : getGenPackages())
    {
      String copyright = ((GenBaseImpl)genPackage).getCopyright(false);
      if (copyright != null)
      {
        return copyright;
      }
    }
    if (includeGenModelCopyrightTextAsDefault)
    {
      String copyright = getCopyrightText();
      return isBlank(copyright) ? null : copyright;
    }
    else
    {
      return null;
    }
  }

  public boolean hasCopyrightField()
  {
    return isCopyrightFields() && !isBlank(getCopyrightText());
  }

  public String getCopyrightFieldLiteral()
  {
    if (isCopyrightFields())
    {
      String copyright = getCopyrightText();
      return isBlank(copyright) ? "null" : Literals.toUnsafeStringLiteral(copyright, this);
    }
    else
    {
      return "null";
    }
  }

  public List<String> getModelSourceFolders()
  {
    List<String> sourceFolders = new UniqueEList<String>(2);

    {
      String sourceFolder = getSourceFolder(getModelDirectory());
      if (sourceFolder != null)
      {
        sourceFolders.add(sourceFolder);
      }
    }

    if (sameModelTestsProject())
    {
      String sourceFolder = getSourceFolder(getTestsDirectory());
      if (sourceFolder != null)
      {
        sourceFolders.add(sourceFolder);
      }
    }

    return sourceFolders;
  }

  public List<String> getEditSourceFolders()
  {
    List<String> sourceFolders = new UniqueEList<String>(3);

    if (sameModelEditProject())
    {
      String sourceFolder = getSourceFolder(getModelDirectory());
      if (sourceFolder != null)
      {
        sourceFolders.add(sourceFolder);
      }
    }

    {
      String sourceFolder = getSourceFolder(getEditDirectory());
      if (sourceFolder != null)
      {
        sourceFolders.add(sourceFolder);
      }
    }

    if (sameEditTestsProject())
    {
      String sourceFolder = getSourceFolder(getTestsDirectory());
      if (sourceFolder != null)
      {
        sourceFolders.add(sourceFolder);
      }
    }

    return sourceFolders;
  }

  public List<String> getEditorSourceFolders()
  {
    List<String> sourceFolders = new UniqueEList<String>(4);

    if (sameModelEditorProject())
    {
      String sourceFolder = getSourceFolder(getModelDirectory());
      if (sourceFolder != null)
      {
        sourceFolders.add(sourceFolder);
      }
    }

    if (sameEditEditorProject())
    {
      String sourceFolder = getSourceFolder(getEditDirectory());
      if (sourceFolder != null)
      {
        sourceFolders.add(sourceFolder);
      }
    }

    {
      String sourceFolder = getSourceFolder(getEditorDirectory());
      if (sourceFolder != null)
      {
        sourceFolders.add(sourceFolder);
      }
    }

    if (sameEditorTestsProject())
    {
      String sourceFolder = getSourceFolder(getTestsDirectory());
      if (sourceFolder != null)
      {
        sourceFolders.add(sourceFolder);
      }
    }

    return sourceFolders;
  }

  public List<String> getTestsSourceFolders()
  {
    List<String> sourceFolders = new UniqueEList<String>(1);

    String sourceFolder = getSourceFolder(getTestsDirectory());
    if (sourceFolder != null)
    {
      sourceFolders.add(sourceFolder);
    }

    return sourceFolders;
  }

  protected String getSourceFolder(String projectDirectory)
  {
    if (!isBlank(projectDirectory))
    {
      URI uri = URI.createURI(projectDirectory);
      int segmentCount = uri.segmentCount();
      if (segmentCount > 1)
      {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < segmentCount; ++i)
        {
          result.append(uri.segment(i));
          result.append('/');
        }
        return result.toString();
      }
    }
    return null;
  }

  protected Locale locale;

  public Locale getLocale()
  {
    if (locale == null)
    {
      if (getLanguage() == null)
      {
        return Locale.getDefault();
      }
      else
      {
        locale = new Locale(getLanguage());
      }
    }
    return locale;
  }

  protected GenModel mainGenModel;

  @Override
  public GenModel getMainGenModel()
  {
    return mainGenModel == null ? this : mainGenModel;
  }

  public void setMainGenModel(GenModel genModel)
  {
    if (genModel == null)
    {
      mainGenModel = genModel;
    }
    else
    {
      // Avoid creating a cycle.
      //
      Set<GenModel> visited = new HashSet<GenModel>();
      for (GenModel otherGenModel = genModel.getMainGenModel(); visited.add(otherGenModel); otherGenModel = otherGenModel.getMainGenModel())
      {
        if (otherGenModel == this)
        {
          return;
        }
      }
      mainGenModel = genModel;
    }
  }

  protected boolean isMainGenModel()
  {
    return mainGenModel == null || mainGenModel == this;
  }

  public String getRootPackageName()
  {
    List<GenPackage> allGenPackagesWithClassifiers = getAllGenPackagesWithClassifiers();
    int size = allGenPackagesWithClassifiers.size();
    if (size > 1)
    {
      String[][] packageNames = new String[size][];
      for (int i = 0; i < size; ++i)
      {
        packageNames[i] = allGenPackagesWithClassifiers.get(i).getQualifiedPackageName().split(".");
      }
      int count = 0;
      LOOP:
      for (; packageNames[0].length > count; ++count)
      {
        String segment = packageNames[0][count];
        for (int i = 1; i < size; ++i)
        {
          if (packageNames[i].length <= count || !packageNames[i][count].equals(segment))
          {
            break LOOP;
          }
        }
      }
      StringBuilder result = new StringBuilder();
      for (int j = 0; j < count; ++j)
      {
        if (result.length() != 0)
        {
          result.append(".");
        }
        result.append(packageNames[0][j]);
      }
      return result.toString();
    }
    else
    {
      return allGenPackagesWithClassifiers.get(0).getQualifiedPackageName();
    }
  }

  public String getModelModuleName()
  {
    List<GenPackage> allGenPackagesWithClassifiers = getAllGenAndUsedGenPackagesWithClassifiers();
    return
      allGenPackagesWithClassifiers.size() == 1 || isBlank(getModelName()) ?
        allGenPackagesWithClassifiers.get(0).getPrefix() :
        CodeGenUtil.validJavaIdentifier(getModelName());
  }

  public String getQualifiedModelModuleName()
  {
    String rootPackageName = getRootPackageName();
    return isBlank(rootPackageName) ? getModelModuleName() : rootPackageName + "." + getModelModuleName();
  }

  public List<String> getModelModuleSources()
  {
    List<String> result = new ArrayList<String>();
    int prefixLength = getRootPackageName().length();
    for (String modelQualifiedPackageName : getModelQualifiedPackageNames())
    {
      result.add(modelQualifiedPackageName.length() == prefixLength ? ""  : modelQualifiedPackageName.substring(prefixLength + 1));
    }
    return result;
  }

  public List<String> getModelModuleInherits()
  {
    List<String> result = new UniqueEList<String>();
    result.add("com.google.gwt.user.User");
    result.add("org.eclipse.emf.ecore.Ecore");
    for (GenPackage genPackage : getUsedGenPackages())
    {
      result.add(genPackage.getGenModel().getQualifiedModelModuleName());
    }
    return result;
  }

  public String getEditModuleName()
  {
    if (sameModelEditProject())
    {
      return getModelModuleName();
    }
    List<GenPackage> allGenPackagesWithClassifiers = getAllGenAndUsedGenPackagesWithClassifiers();
    return
      (allGenPackagesWithClassifiers.size() == 1 || isBlank(getModelName()) ?
         allGenPackagesWithClassifiers.get(0).getPrefix() :
         CodeGenUtil.validJavaIdentifier(getModelName())) + "Edit";
  }

  public String getQualifiedEditModuleName()
  {
    String rootPackageName = getRootPackageName();
    return isBlank(rootPackageName) ? getEditModuleName() : rootPackageName + "." + getEditModuleName();
  }

  public List<String> getEditModuleSources()
  {
    List<String> result = new ArrayList<String>();
    int prefixLength = getRootPackageName().length();
    for (String editQualifiedPackageName : getEditQualifiedPackageNames())
    {
      result.add(editQualifiedPackageName.length() == prefixLength ? ""  : editQualifiedPackageName.substring(prefixLength + 1));
    }
    return result;
  }

  public List<String> getEditModuleInherits()
  {
    List<String> result = new UniqueEList<String>();
    result.add("org.eclipse.emf.edit.Edit");
    if (!sameModelEditProject())
    {
      result.add(getQualifiedModelModuleName());
    }
    else
    {
      result.addAll(getModelModuleInherits());
    }
    for (GenPackage genPackage : getUsedGenPackages())
    {
      result.add(genPackage.getGenModel().getQualifiedEditModuleName());
    }
    return result;
  }

  public String getEditorModuleName()
  {
    List<GenPackage> allGenPackagesWithClassifiers = getAllGenAndUsedGenPackagesWithClassifiers();
    return
      (allGenPackagesWithClassifiers.size() == 1 || isBlank(getModelName()) ?
         allGenPackagesWithClassifiers.get(0).getPrefix() :
         CodeGenUtil.validJavaIdentifier(getModelName())) + "Editor";
  }

  public String getQualifiedEditorModuleName()
  {
    String rootPackageName = getRootPackageName();
    return isBlank(rootPackageName) ? getEditorModuleName() : rootPackageName + "." + getEditorModuleName();
  }

  public List<String> getEditorModuleInherits()
  {
    List<String> result = new UniqueEList<String>();
    for (GenPackage genPackage : getGenPackages())
    {
      result.add(genPackage.getGenModel().getQualifiedEditModuleName());
    }
    return result;
  }

  public List<String> getEditorModuleSources()
  {
    List<String> result = new UniqueEList<String>();
    int prefixLength = getRootPackageName().length();
    for (String editorQualifiedPackageName : getEditorQualifiedPackageNames())
    {
      result.add(editorQualifiedPackageName.length() == prefixLength ? ""  : editorQualifiedPackageName.substring(prefixLength + 1));
    }
    return result;
  }

  public String getEditorHomePageName()
  {
    return getEditorModuleName();
  }

  public Collection<? extends Runnable> prelinkInitialize(boolean handleAnnotations)
  {
    return initialize(handleAnnotations, true);
  }

  public void initialize(boolean handleAnnotations)
  {
    initialize(handleAnnotations, false);
  }

  /**
   * @since 2.10
   */
  protected Collection<? extends Runnable> initialize(boolean handleAnnotations, boolean isPreLinking)
  {
    List<Runnable> result = isPreLinking ? new ArrayList<Runnable>() : null;
    if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE)
    {
      Resource resource = eResource();
      if (resource != null)
      {
        URI uri = resource.getURI();
        if (getModelDirectory() == null)
        {
          setModelDirectory(EclipseHelper.getModelDirectory(uri));
        }
        setComplianceLevel(EclipseHelper.getComplianceLevel(uri));
        if (getModelPluginID() == null)
        {
          setModelPluginID(EclipseHelper.getPluginID(uri));
        }
      }
    }

    setOperationReflection(true);
    setMinimalReflectiveMethods(true);
    setRootExtendsClass("org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container");
    setImportOrganizing(true);
    GenPackage mainGenPackage = getMainGenPackage();
    if (getModelName() == null)
    {
      setModelName(mainGenPackage.getPrefix());
    }

    if (handleAnnotations)
    {
      handleAnnotations(this, mainGenPackage.getEcorePackage());
      for (TreeIterator<EObject> i = eAllContents(); i.hasNext();)
      {
        EObject content = i.next();
        if (content instanceof GenBase)
        {
          GenBase genBase = (GenBase)content;
          EModelElement eModelElement = genBase.getEcoreModelElement();
          if (eModelElement != null)
          {
            if (result == null)
            {
              handleAnnotations(genBase, eModelElement);
            }
            else
            {
              handleAnnotations(result, genBase, eModelElement);
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * @since 2.8
   */
  protected void handleAnnotations(GenBase genBase, EModelElement eModelElement)
  {
    handleAnnotations(null, genBase, eModelElement);
  }

  /**
   * @since 2.10
   */
  protected void handleAnnotations(Collection<Runnable> runnables, final GenBase genBase, EModelElement eModelElement)
  {
    EAnnotation eAnnotation = eModelElement.getEAnnotation(GenModelPackage.eNS_URI);
    if (eAnnotation != null)
    {
      EClass eClass = genBase.eClass();
      for (Map.Entry<String, String> entry : eAnnotation.getDetails())
      {
        final EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(entry.getKey());
        final String literal = entry.getValue();
        if (eStructuralFeature instanceof EAttribute)
        {
          EAttribute eAttribute = (EAttribute)eStructuralFeature;
          Object value = null;
          try
          {
            if (eAttribute.isMany())
            {
              List<String> list = XMLTypeFactory.eINSTANCE.createENTITIESBase(literal);
              list.remove("");
              value = list;
            }
            else
            {
              value = EcoreUtil.createFromString(eAttribute.getEAttributeType(), literal);
            }
          }
          catch (Exception exception)
          {
            // Ignore ill formed values.
            //
            continue;
          }
          if (value != null)
          {
            genBase.eSet(eStructuralFeature, value);
          }
        }
        else if (eStructuralFeature instanceof EReference)
        {
          
          if (runnables == null)
          {
            for (GenFeature genFeature : ((GenClass)genBase).getAllGenFeatures())
            {
              if (literal.equals(genFeature.getName()))
              {
                genBase.eSet(eStructuralFeature, genFeature);
                break;
              }
            }
          }
          else
          {
            runnables.add
              (new Runnable()
               {
                 public void run()
                 {
                   for (GenFeature genFeature : ((GenClass)genBase).getAllGenFeatures())
                   {
                     if (literal.equals(genFeature.getName()))
                     {
                       genBase.eSet(eStructuralFeature, genFeature);
                       break;
                     }
                   }
                 }
               });
          }
        }
      }
    }
  }

  public boolean isUnnecessaryElse()
  {
    if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE)
    {
      return !JavaCore.IGNORE.equals(EclipseHelper.getJavaOptions(this).get(JavaCore.COMPILER_PB_UNNECESSARY_ELSE));
    }
    return false;
  }

  private static class EclipseHelper
  {
    static Map<String, String> getJavaOptions(GenModel genModel)
    {
      if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE)
      {
        return GenModelUtil.getJavaOptions(genModel);
      }
      else return Collections.emptyMap();
    }

    static String getModelDirectory(URI uri)
    {
      if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE && uri.segmentCount() >= 2)
      {
        try
        {
          IWorkspace workspace = ResourcesPlugin.getWorkspace();
          IProject project = workspace.getRoot().getProject(uri.segment(1));
          if (project.exists())
          {
            @SuppressWarnings("deprecation")
            String outputDirectory = 
              Platform.getPreferencesService().getString
                ("org.eclipse.emf.ecore.xcore.Xcore", 
                 "outlet.DEFAULT_OUTPUT.directory", 
                 null,
                 new IScopeContext[] { new ProjectScope(project), new InstanceScope() });
            if (outputDirectory != null)
            {
              return project.getFullPath().append(new Path(outputDirectory)).toString();
            }
            else
            {
              IJavaProject javaProject = JavaCore.create(project);
              IClasspathEntry[] classpath = javaProject.getRawClasspath();
              IClasspathEntry bestEntry = null;
              for (IClasspathEntry classpathEntry : classpath)
              {
                if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE)
                {
                  // Look for the first entry that's Java source.
                  if (bestEntry == null)
                  {
                    bestEntry = classpathEntry;
                  }
                  // If there's a src-gen entry, prefer that over all others.
                  //
                  else if (classpathEntry.getPath().toString().endsWith("src-gen"))
                  {
                    bestEntry = classpathEntry;
                  }
                }
              }
              return bestEntry == null ? project.getFullPath() + "/src" : bestEntry.getPath().toString();
            }
          }
        }
        catch (Exception exception)
        {
          // Ignore
        }
      }
      return null;
    }

    static GenJDKLevel getComplianceLevel(URI uri)
    {
      if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE && uri.segmentCount() >= 2)
      {
        try
        {
          IWorkspace workspace = ResourcesPlugin.getWorkspace();
          IProject project = workspace.getRoot().getProject(uri.segment(1));
          if (project.exists())
          {
            String complianceLevel = CodeGenUtil.EclipseUtil.getJavaComplianceLevel(project);
            if ("1.4".equals(complianceLevel))
            {
              return GenJDKLevel.JDK14_LITERAL;
            }
            else if ("1.5".equals(complianceLevel))
            {
              return GenJDKLevel.JDK50_LITERAL;
            }
            else if ("1.6".equals(complianceLevel))
            {
              return GenJDKLevel.JDK60_LITERAL;
            }
            else
            {
              return GenJDKLevel.JDK70_LITERAL;
            }
          }
        }
        catch (Exception exception)
        {
          // Ignore
        }
      }
      return GenJDKLevel.JDK50_LITERAL;
    }

    static String getPluginID(URI uri)
    {
      String pluginID = uri != null && uri.segmentCount() > 2 ? uri.segment(1) : null;
      if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE)
      {
        try
        {
          IWorkspace workspace = ResourcesPlugin.getWorkspace();
          IFile manifestFile = workspace.getRoot().getFile(new Path(pluginID + "/META-INF/MANIFEST.MF"));
          if (manifestFile.exists())
          {
            Manifest manifest = new Manifest(manifestFile.getContents());
            String name = manifest.getMainAttributes().getValue("Bundle-SymbolicName");
            if (name != null)
            {
               int index = name.indexOf(';');
               if (index > 0)
               {
                  name = name.substring(0, index);
               }
               pluginID = name.trim();
            }
          }
        }
        catch (Exception exception)
        {
          // Ignore
        }
      }
      return pluginID;
    }
  }

} //GenModelImpl
