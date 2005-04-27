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
 * $Id: GenModelImpl.java,v 1.29 2005/04/27 20:39:06 khussey Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.jmerge.JControlModel;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;


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
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getFeatureMapWrapperInterface <em>Feature Map Wrapper Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getFeatureMapWrapperInternalInterface <em>Feature Map Wrapper Internal Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getFeatureMapWrapperClass <em>Feature Map Wrapper Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isRuntimeCompatibility <em>Runtime Compatibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isRichClientPlatform <em>Rich Client Platform</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isReflectiveDelegation <em>Reflective Delegation</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#isCodeFormatting <em>Code Formatting</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getTestsDirectory <em>Tests Directory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getTestSuiteClass <em>Test Suite Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getBooleanFlagsField <em>Boolean Flags Field</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getBooleanFlagsReservedBits <em>Boolean Flags Reserved Bits</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getGenPackages <em>Gen Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl#getUsedGenPackages <em>Used Gen Packages</em>}</li>
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
  protected EList foreignModel = null;

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
  protected EList staticPackages = null;

  /**
   * The cached value of the '{@link #getModelPluginVariables() <em>Model Plugin Variables</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelPluginVariables()
   * @generated
   * @ordered
   */
  protected EList modelPluginVariables = null;

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
  protected static final boolean RUNTIME_COMPATIBILITY_EDEFAULT = true;

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
   * The cached value of the '{@link #isRichClientPlatform() <em>Rich Client Platform</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRichClientPlatform()
   * @generated
   * @ordered
   */
  protected boolean richClientPlatform = RICH_CLIENT_PLATFORM_EDEFAULT;

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
   * The cached value of the '{@link #isReflectiveDelegation() <em>Reflective Delegation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isReflectiveDelegation()
   * @generated
   * @ordered
   */
  protected boolean reflectiveDelegation = REFLECTIVE_DELEGATION_EDEFAULT;

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
   * The cached value of the '{@link #getGenPackages() <em>Gen Packages</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenPackages()
   * @generated
   * @ordered
   */
  protected EList genPackages = null;

  protected EList staticGenPackages = null;

  /**
   * The cached value of the '{@link #getUsedGenPackages() <em>Used Gen Packages</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUsedGenPackages()
   * @generated
   * @ordered
   */
  protected EList usedGenPackages = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenModelImpl() 
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return GenModelPackage.eINSTANCE.getGenModel();
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

  protected ImportManager importManager;
  protected StringBuffer importStringBuffer;
  protected int importInsertionPoint;
  protected boolean canGenerate;

  public void markImportLocation(StringBuffer stringBuffer, GenPackage genPackage)
  {
    markImportLocation(stringBuffer);
    importManager.addJavaLangImports(genPackage.getJavaLangConflicts());
  }

  public void markImportLocation(StringBuffer stringBuffer)
  {
    importStringBuffer = stringBuffer;
    importInsertionPoint = stringBuffer.length();
    importManager.addCompilationUnitImports(stringBuffer.toString());
  }

  public void emitSortedImports()
  {
    String NL = System.getProperties().getProperty("line.separator");
    StringBuffer imports = new StringBuffer();

    String previousPackageName = null;
    for (Iterator iter = importManager.getImports().iterator(); iter.hasNext(); )
    {
      String importName = (String)iter.next();
      int index = importName.lastIndexOf(".");
      if (index != -1)
      {
        String packageName = importName.substring(0, index);
        if (previousPackageName != null && !previousPackageName.equals(packageName))
        {
          imports.append(NL);
        }
        previousPackageName = packageName;
      }
      imports.append(NL + "import " + importName + ";");
    }

    importStringBuffer.insert(importInsertionPoint, imports);
  }

  public String getImportedName(String qualifiedName)
  {
    int index = qualifiedName.indexOf("$");
    importManager.addImport(index == -1 ? qualifiedName : qualifiedName.substring(0, index));
    return importManager.getImportedName(qualifiedName);
  }

  public void addImport(String qualifiedName)
  {
    importManager.addImport(qualifiedName);
  }

  public void addPseudoImport(String qualifiedName)
  {
    importManager.addPseudoImport(qualifiedName);
  }

  protected ImportManager getImportManager()
  {
    return importManager;
  }

  protected void setImportManager(ImportManager importManager)
  {
    this.importManager = importManager;

    for (Iterator iter = getUsedGenPackages().iterator(); iter.hasNext(); )
    {
      GenBaseImpl genPackage = (GenBaseImpl)iter.next();
      genPackage.setImportManager(importManager); //FB TBD need to rethink the GenModel role
    }

    for (Iterator iter = getStaticGenPackages().iterator(); iter.hasNext(); )
    {
      GenBaseImpl genPackage = (GenBaseImpl)iter.next();
      genPackage.setImportManager(importManager); //FB TBD need to rethink the GenModel role
    }
  }

  public String getDriverNumber()
  {
    //TBD
    // EATM return "jet0000";
    return null;
  }

  public String getDate()
  {
    //TBD
    // EATM return "mm-dd-yyyy";
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

  public void initialize(Collection ePackages)
  {
    if (getGenPackages().isEmpty())
    {
      setRuntimeCompatibility(false);
      setRuntimeJar(true);
    }

    LOOP:
    for (Iterator iter = ePackages.iterator(); iter.hasNext(); )
    {
      EPackage ePackage = (EPackage)iter.next();

      for (Iterator j = getGenPackages().iterator(); j.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)j.next();
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

  protected String jControlModelName = "emf-merge.xml";

  protected String interfaceTemplateName = "model/Interface.javajet";
  protected String classTemplateName = "model/Class.javajet";
  protected String enumClassTemplateName = "model/EnumClass.javajet";
  protected String packageInterfaceTemplateName = "model/PackageInterface.javajet";
  protected String packageClassTemplateName = "model/PackageClass.javajet";
  protected String factoryInterfaceTemplateName = "model/FactoryInterface.javajet";
  protected String factoryClassTemplateName = "model/FactoryClass.javajet";
  protected String adapterFactoryClassTemplateName = "model/AdapterFactoryClass.javajet";
  protected String switchClassTemplateName = "model/SwitchClass.javajet";
  protected String validatorSwitchClassTemplateName = "model/ValidatorClass.javajet";
  protected String pluginXMLTemplateName = "model/plugin.xmljet";
  protected String pluginPropertiesTemplateName = "model/plugin.propertiesjet";
  protected String buildPropertiesTemplateName = "model/build.propertiesjet";
  protected String modelPluginTemplateName = "model/Plugin.javajet";
  protected String resourceTemplateName = "model/ResourceClass.javajet";
  protected String resourceFactoryTemplateName = "model/ResourceFactoryClass.javajet";

  protected String [] templatePath = null;

  protected JControlModel jControlModel = null;

  protected JETEmitter interfaceEmitter = null;
  protected JETEmitter classEmitter = null;
  protected JETEmitter enumClassEmitter = null;
  protected JETEmitter packageInterfaceEmitter = null;
  protected JETEmitter packageClassEmitter = null;
  protected JETEmitter factoryInterfaceEmitter = null;
  protected JETEmitter factoryClassEmitter = null;
  protected JETEmitter adapterFactoryClassEmitter = null;
  protected JETEmitter switchClassEmitter = null;
  protected JETEmitter validatorSwitchClassEmitter = null;
  protected JETEmitter pluginXMLEmitter = null;
  protected JETEmitter pluginPropertiesEmitter = null;
  protected JETEmitter buildPropertiesEmitter = null;
  protected JETEmitter modelPluginClassEmitter = null;
  protected JETEmitter resourceClassEmitter = null;
  protected JETEmitter resourceFactoryClassEmitter = null;

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

  public JControlModel getJControlModel()
  {
    if (jControlModel == null)
    {
      jControlModel = new JControlModel(JETCompiler.find(getTemplatePath(), jControlModelName));
    }
    return jControlModel;
  }

  public static final Class [] OBJECT_ARGUMENT = new Class [ ] { Object.class };
  public void setMethod(JETEmitter jetEmitter, String className)
  {
    if (!isDynamicTemplates())
    {
      try
      {
        Class emitterClass = getClass().getClassLoader().loadClass(className);
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

  protected JETEmitter createJETEmitter(String relativeTemplateURI)
  {
    JETEmitter jetEmitter = 
      new JETEmitter(getTemplatePath(), relativeTemplateURI)
      {
        public void initialize(IProgressMonitor progressMonitor) throws JETException
        {
          if (getClasspathEntries().isEmpty())
          {
            addVariable("EMF_CODEGEN", "org.eclipse.emf.codegen");
            addVariable("EMF_CODEGEN_ECORE", "org.eclipse.emf.codegen.ecore");
            addVariable("EMF_COMMON", "org.eclipse.emf.common");
            addVariable("EMF_ECORE", "org.eclipse.emf.ecore");
          }
          super.initialize(progressMonitor);
        }
      };
    return jetEmitter;
  }

  public JETEmitter getInterfaceEmitter()
  {
    if (interfaceEmitter == null)
    {
      interfaceEmitter = createJETEmitter(interfaceTemplateName);
      setMethod(interfaceEmitter, "org.eclipse.emf.codegen.ecore.templates.model.Interface");
    }
    return interfaceEmitter;
  } 

  public JETEmitter getClassEmitter()
  {
    if (classEmitter == null)
    {
      classEmitter = createJETEmitter(classTemplateName);
      setMethod(classEmitter, "org.eclipse.emf.codegen.ecore.templates.model.Class");
    }
    return classEmitter;
  } 

  public JETEmitter getEnumClassEmitter()
  {
    if (enumClassEmitter == null)
    {
      enumClassEmitter = createJETEmitter(enumClassTemplateName);
      setMethod(enumClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.EnumClass");
    }
    return enumClassEmitter;
  } 

  public JETEmitter getFactoryInterfaceEmitter()
  {
    if (factoryInterfaceEmitter == null)
    {
      factoryInterfaceEmitter = createJETEmitter(factoryInterfaceTemplateName);
      setMethod(factoryInterfaceEmitter, "org.eclipse.emf.codegen.ecore.templates.model.FactoryInterface");
    }
    return factoryInterfaceEmitter;
  } 

  public JETEmitter getFactoryClassEmitter()
  {
    if (factoryClassEmitter == null)
    {
      factoryClassEmitter = createJETEmitter(factoryClassTemplateName);
      setMethod(factoryClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.FactoryClass");
    }
    return factoryClassEmitter;
  } 

  public JETEmitter getPackageInterfaceEmitter()
  {
    if (packageInterfaceEmitter == null)
    {
      packageInterfaceEmitter = createJETEmitter(packageInterfaceTemplateName);
      setMethod(packageInterfaceEmitter, "org.eclipse.emf.codegen.ecore.templates.model.PackageInterface");
    }
    return packageInterfaceEmitter;
  } 

  public JETEmitter getPackageClassEmitter()
  {
    if (packageClassEmitter == null)
    {
      packageClassEmitter = createJETEmitter(packageClassTemplateName);
      setMethod(packageClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.PackageClass");
    }
    return packageClassEmitter;
  } 

  public JETEmitter getAdapterFactoryClassEmitter()
  {
    if (adapterFactoryClassEmitter == null)
    {
      adapterFactoryClassEmitter = createJETEmitter(adapterFactoryClassTemplateName);
      setMethod(adapterFactoryClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.AdapterFactoryClass");
    }
    return adapterFactoryClassEmitter;
  } 

  public JETEmitter getSwitchClassEmitter()
  {
    if (switchClassEmitter == null)
    {
      switchClassEmitter = createJETEmitter(switchClassTemplateName);
      setMethod(switchClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.SwitchClass");
    }
    return switchClassEmitter;
  } 

  public JETEmitter getValidatorClassEmitter()
  {
    if (validatorSwitchClassEmitter == null)
    {
      validatorSwitchClassEmitter = createJETEmitter(validatorSwitchClassTemplateName);
      setMethod(validatorSwitchClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.ValidatorClass");
    }
    return validatorSwitchClassEmitter;
  }

  public JETEmitter getPluginXMLEmitter()
  {
    if (pluginXMLEmitter == null)
    {
      pluginXMLEmitter = createJETEmitter(pluginXMLTemplateName);
      setMethod(pluginXMLEmitter, "org.eclipse.emf.codegen.ecore.templates.model.PluginXML");
    }
    return pluginXMLEmitter;
  }

  public JETEmitter getPluginPropertiesEmitter()
  {
    if (pluginPropertiesEmitter == null)
    {
      pluginPropertiesEmitter = createJETEmitter(pluginPropertiesTemplateName);
      setMethod(pluginPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.model.PluginProperties");
    }
    return pluginPropertiesEmitter;
  }

  public JETEmitter getBuildPropertiesEmitter()
  {
    if (buildPropertiesEmitter == null)
    {
      buildPropertiesEmitter = createJETEmitter(buildPropertiesTemplateName);
      setMethod(buildPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.model.BuildProperties");
    }
    return buildPropertiesEmitter;
  }

  public JETEmitter getModelPluginClassEmitter()
  {
    if (modelPluginClassEmitter == null)
    {
      modelPluginClassEmitter = createJETEmitter(modelPluginTemplateName);
      setMethod(modelPluginClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.Plugin");
    }
    return modelPluginClassEmitter;
  }

  public JETEmitter getResourceClassEmitter()
  {
    if (resourceClassEmitter == null)
    {
      resourceClassEmitter = createJETEmitter(resourceTemplateName);
      setMethod(resourceClassEmitter, "org.eclipse.emf.codegen.ecore.templates.model.ResourceClass");
    }
    return resourceClassEmitter;
  }

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
    MultiStatus status =
      new MultiStatus
        (CodeGenEcorePlugin.INSTANCE.getSymbolicName(),
         0,
         CodeGenEcorePlugin.INSTANCE.getString("_UI_ProblemsEncounteredInTheModel_message"),
         null);

    List all = new ArrayList(getGenPackages());

    all.addAll(getUsedGenPackages());
    for (Iterator i = new ArrayList(all).iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      EObject root = EcoreUtil.getRootContainer(genPackage.getEcorePackage());
      if (!all.contains(root))
      {
        all.add(root);
      }
    }

    Map map = EcoreUtil.UnresolvedProxyCrossReferencer.find(all);
    if (!map.isEmpty())
    {
      for (Iterator i = map.entrySet().iterator(); i.hasNext(); )
      {
        Map.Entry entry = (Map.Entry)i.next();
        EObject unresolvedProxy = (EObject)entry.getKey();

        MultiStatus nestedStatus =
          new MultiStatus
            (CodeGenEcorePlugin.INSTANCE.getSymbolicName(),
             0,
             CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_UnableToResolveProxy_message", new Object [] { EcoreUtil.getURI(unresolvedProxy) }),
             null);

        for (Iterator j = ((List)entry.getValue()).iterator(); j.hasNext(); )
        {
          EStructuralFeature.Setting setting = (EStructuralFeature.Setting)j.next();
          nestedStatus.add
            (new Status
              (IStatus.ERROR,
                  CodeGenEcorePlugin.INSTANCE.getSymbolicName(),
               0,
               CodeGenEcorePlugin.INSTANCE.getString
                 ("_UI_ItsUseIsBy_message", new Object [] { EcoreUtil.getURI(setting.getEObject()) }),
               null));
        }

        status.add(nestedStatus);
      }
    }
    else
    {
      List referencedEPackages = new UniqueEList();
      for (Iterator i = all.iterator(); i.hasNext(); )
      {
        Object object = i.next();
        if (object instanceof EPackage)
        {
          EPackage ePackage = (EPackage)object;
          for (Iterator j = ePackage.eAllContents(); j.hasNext();)
          {
            EObject eObject = (EObject)j.next();
            for (Iterator k = eObject.eCrossReferences().iterator(); k.hasNext(); )
            {
              Object o = k.next();
              if (o instanceof EClassifier)
              {
                EClassifier eClassifier = (EClassifier)o;
                referencedEPackages.add(eClassifier.getEPackage());
              }
            }
          }
        }
      }
    
      for (Iterator i = referencedEPackages.iterator(); i.hasNext(); )
      {
        EPackage ePackage = (EPackage)i.next();
        GenPackage genPackage = findGenPackage(ePackage);
        if (genPackage == null)
        {
          status.add
            (new Status
              (IStatus.ERROR,
                  CodeGenEcorePlugin.INSTANCE.getSymbolicName(),
               0,
               CodeGenEcorePlugin.INSTANCE.getString("_UI_ThePackageIsNeeded_message", new Object [] { EcoreUtil.getURI(ePackage) }),
               null));
        }
        else if (genPackage.getEcorePackage() != ePackage)
        {
          status.add
            (new Status
              (IStatus.ERROR,
                  CodeGenEcorePlugin.INSTANCE.getSymbolicName(),
               0,
               CodeGenEcorePlugin.INSTANCE.getString
                 ("_UI_ThePackageHasTheSameNamespaceURI", 
                  new Object [] { EcoreUtil.getURI(ePackage), ePackage.getNsURI(), EcoreUtil.getURI(genPackage.getEcorePackage()) }),
               null));
        }
      }
    }

    return status;
  }

  protected ExtendedMetaData extendedMetaData;
  protected ExtendedMetaData getExtendedMetaData()
  {
    if (extendedMetaData == null)
    {
      extendedMetaData = 
        eResource() == null || eResource().getResourceSet() == null ? 
          ExtendedMetaData.INSTANCE : 
          new BasicExtendedMetaData(eResource().getResourceSet().getPackageRegistry());
      populateExtendedMetaData(getGenPackages());
      populateExtendedMetaData(getStaticGenPackages());
      populateExtendedMetaData(getUsedGenPackages());
    }
    return extendedMetaData;
  }

  protected void populateExtendedMetaData(List genPackages)
  {
    for (Iterator i = genPackages.iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      EPackage ePackage = genPackage.getEcorePackage();
      if (!EcorePackage.eNS_URI.equals(ePackage.getNsURI()) && ! GenModelPackage.eNS_URI.equals(ePackage.getNsURI()))
      {
        extendedMetaData.putPackage(ePackage.getNsURI(), ePackage);
      }
      populateExtendedMetaData(genPackage.getSubGenPackages());
    }
  }

  public boolean hasPluginSupport()
  {
    return !isBlank(getModelPluginID());
  }

  public void generate(IProgressMonitor progressMonitor)
  {
    try
    {
      if (!canGenerate()) return;

      getStaticGenPackages();

      progressMonitor.beginTask("", getGenPackages().size() + 3);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingPackages_message"));

      // Force the project to be setup properly.
      //
      if (isUpdateClasspath())
      {
        findOrCreateContainer
          (new SubProgressMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE, 
           getEffectiveModelPluginVariables(),
           new Path(getModelDirectory()), true);
      }

      for (Iterator iter = getGenPackages().iterator(); iter.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)iter.next();
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaPackage_message", new Object [] { genPackage.getInterfacePackageName() }));
        genPackage.generate(new SubProgressMonitor(progressMonitor, 1));
      }

      if (hasModelPluginClass())
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedModelPluginClassName() }));
        generate
          (new SubProgressMonitor(progressMonitor, 1),
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
          progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelPluginXML_message"));
          generate
            (new SubProgressMonitor(progressMonitor, 1),
             Generator.EMF_MODEL_PROJECT_STYLE,
             getEffectiveModelPluginVariables(),
             getModelProjectDirectory() + "/plugin.xml",
             getPluginXMLEmitter());
        }
  
        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelPluginProperties_message"));
        generate
          (new SubProgressMonitor(progressMonitor, 1),
           Generator.EMF_MODEL_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getModelProjectDirectory() + "/plugin.properties",
           getPluginPropertiesEmitter());
        
        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelBuildProperties_message"));
        generate
          (new SubProgressMonitor(progressMonitor, 1),
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

  public boolean canGenerateEdit()
  {
    return canGenerate && hasEditSupport();
  }

  public void generateEdit(IProgressMonitor progressMonitor)
  {
    try
    {
      if (!canGenerateEdit()) return;

      getStaticGenPackages();

      progressMonitor.beginTask("", getGenPackages().size() + 1);

      // Force the project to be setup properly.
      //
      if (isUpdateClasspath())
      {
        findOrCreateContainer
          (new SubProgressMonitor(progressMonitor, 1), 
           Generator.EMF_EDIT_PROJECT_STYLE, 
           getEffectiveModelPluginVariables(),
           new Path(getEditDirectory()), 
           true);
      }

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditPackages_message"));
      for (Iterator iter = getGenPackages().iterator(); iter.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)iter.next();
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaPackage_message", new Object [] { genPackage.getInterfacePackageName() }));
        genPackage.generateEdit(new SubProgressMonitor(progressMonitor, 1));
      }

      if (!sameEditEditorProject())
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedEditPluginClassName() }));
        generate
          (new SubProgressMonitor(progressMonitor, 1),
           Generator.EMF_EDIT_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getEditPluginDirectory(),
           getEditPluginPackageName(),
           getEditPluginClassName(),
           getEditPluginClassEmitter());

        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditPluginXML_message"));
        generate
          (new SubProgressMonitor(progressMonitor, 1),
           Generator.EMF_EDIT_PROJECT_STYLE,
           getEffectiveModelPluginVariables(),
           getEditProjectDirectory() + "/plugin.xml",
           getEditPluginXMLEmitter());
      }

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditPluginProperties_message"));
      generate
        (new SubProgressMonitor(progressMonitor, 1),
         Generator.EMF_EDIT_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditProjectDirectory() + "/plugin.properties",
         getEditPluginPropertiesEmitter());

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditBuildProperties_message"));
      generate
        (new SubProgressMonitor(progressMonitor, 1),
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

  public boolean canGenerateEditor()
  {
    return canGenerate && hasEditorSupport();
  }

  public void generateEditor(IProgressMonitor progressMonitor)
  {
    try
    {
      if (!canGenerateEditor()) return;

      getStaticGenPackages();

      progressMonitor.beginTask("", getGenPackages().size() + 1);

      // Force the project to be setup properly.
      //
      if (isUpdateClasspath())
      {
        findOrCreateContainer
          (new SubProgressMonitor(progressMonitor, 1), 
           Generator.EMF_EDITOR_PROJECT_STYLE, 
           getEffectiveModelPluginVariables(),
           new Path(getEditorDirectory()), 
           true);
      }

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorPackages"));
      for (Iterator iter = getGenPackages().iterator(); iter.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)iter.next();
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaPackage_message", new Object [] { genPackage.getInterfacePackageName() }));
        genPackage.generateEditor(new SubProgressMonitor(progressMonitor, 1));
      }

      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedEditorPluginClassName() }));
      generate
        (new SubProgressMonitor(progressMonitor, 1),
         Generator.EMF_EDITOR_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditorPluginDirectory(),
         getEditorPluginPackageName(),
         getEditorPluginClassName(),
         getEditorPluginClassEmitter());

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorPluginXML_message"));
      generate
        (new SubProgressMonitor(progressMonitor, 1),
         Generator.EMF_EDITOR_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditorProjectDirectory() + "/plugin.xml",
         getEditorPluginXMLEmitter());

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorPluginProperties_message"));
      generate
        (new SubProgressMonitor(progressMonitor, 1),
         Generator.EMF_EDITOR_PROJECT_STYLE,
         getEffectiveModelPluginVariables(),
         getEditorProjectDirectory() + "/plugin.properties",
         getEditorPluginPropertiesEmitter());

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingEditorBuildProperties_message"));
      generate
        (new SubProgressMonitor(progressMonitor, 1),
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
        (new SubProgressMonitor(progressMonitor, 1),
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
  
  public boolean canGenerateSchema()
  {
    return canGenerate();
  }
  
  public void generateSchema(IProgressMonitor progressMonitor)
  {
    for (Iterator i = getGenPackages().iterator(); i.hasNext();)
    {
      ((GenPackage)i.next()).generateSchema(new SubProgressMonitor(progressMonitor, 1));
    }
  }
  
  public boolean hasTestSupport()
  {
    return hasModelSupport() && !isBlank(getTestsDirectory());
  }

  public boolean canGenerateTests()
  {
    return canGenerate && hasTestSupport();
  }

  public void generateTests(IProgressMonitor progressMonitor)
  {
    try
    {
      if (!canGenerateTests())
        return;

      progressMonitor.beginTask("", getGenPackages().size() + 4);

      if (isUpdateClasspath())
      {
        findOrCreateContainer(
          new SubProgressMonitor(progressMonitor, 1),
          Generator.EMF_TESTS_PROJECT_STYLE,
          getEffectiveModelPluginVariables(),
          new Path(getTestsDirectory()),
          true);
      }

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsPackages_message"));
      for (Iterator genPackages = getGenPackages().iterator(); genPackages.hasNext();)
      {
        GenPackage genPackage = (GenPackage)genPackages.next();
        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString(
          "_UI_GeneratingJavaPackage_message",
          new Object []{ genPackage.getTestsPackageName() }));
        genPackage.generateTests(new SubProgressMonitor(progressMonitor, 1));
      }

      if (!isBlank(getTestSuiteClass()))
      {
        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString(
          "_UI_GeneratingJavaClass_message",
          new Object []{ getQualifiedTestSuiteClassName() }));
        generate(
          new SubProgressMonitor(progressMonitor, 1),
          Generator.EMF_TESTS_PROJECT_STYLE,
          getEffectiveModelPluginVariables(),
          getTestsDirectory(),
          getTestSuitePackageName(),
          getTestSuiteClassName(),
          getModelTestSuiteEmitter());
      }

      if (!sameModelTestsProject())
      {
        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsPluginXML_message"));
        generate(
          new SubProgressMonitor(progressMonitor, 1),
          Generator.EMF_TESTS_PROJECT_STYLE,
          getEffectiveModelPluginVariables(),
          getTestsProjectDirectory() + "/plugin.xml",
          getTestsPluginXMLEmitter());

        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsPluginProperties_message"));
        generate(
          new SubProgressMonitor(progressMonitor, 1),
          Generator.EMF_TESTS_PROJECT_STYLE,
          getEffectiveModelPluginVariables(),
          getTestsProjectDirectory() + "/plugin.properties",
          getTestsPluginPropertiesEmitter());

        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestsBuildProperties_message"));
        generate(
          new SubProgressMonitor(progressMonitor, 1),
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

  protected String itemProviderTemplateName = "edit/ItemProvider.javajet";
  protected String itemProviderAdapterFactoryTemplateName = "edit/ItemProviderAdapterFactory.javajet";
  protected String editPluginTemplateName = "edit/Plugin.javajet";
  protected String editPluginXMLTemplateName = "edit/plugin.xmljet";
  protected String editPluginPropertiesTemplateName = "edit/plugin.propertiesjet";
  protected String editBuildPropertiesTemplateName = "edit/build.propertiesjet";
  protected String itemGIFName = "edit/Item.gif";
  protected String createChildGIFName = "edit/CreateChild.gif";

  protected String editorTemplateName = "editor/Editor.javajet";
  protected String actionBarContributorTemplateName = "editor/ActionBarContributor.javajet";
  protected String modelWizardTemplateName = "editor/ModelWizard.javajet";
  protected String advisorTemplateName = "editor/Advisor.javajet";
  protected String editorPluginTemplateName = "editor/Plugin.javajet";
  protected String editorPluginXMLTemplateName = "editor/plugin.xmljet";
  protected String editorPluginPropertiesTemplateName = "editor/plugin.propertiesjet";
  protected String editorBuildPropertiesTemplateName = "editor/build.propertiesjet";
  protected String modelGIFName = "editor/ModelFile.gif";
  protected String modelWizardGIFName = "editor/NewModel.gif";

  protected JETEmitter itemProviderEmitter = null;
 //   protected JETEmitter extendedItemProviderEmitter = null;
  protected JETEmitter itemProviderAdapterFactoryEmitter = null;
  protected JETEmitter editPluginClassEmitter = null;
  protected JETEmitter editPluginXMLEmitter = null;
  protected JETEmitter editPluginPropertiesEmitter = null;
  protected JETEmitter editBuildPropertiesEmitter = null;
  protected GIFEmitter itemGIFEmitter = null;
  protected GIFEmitter createChildGIFEmitter = null;

  protected JETEmitter editorEmitter = null;
  protected JETEmitter actionBarContributorEmitter = null;
  protected JETEmitter modelWizardEmitter = null;
  protected JETEmitter advisorEmitter = null;
  protected JETEmitter editorPluginClassEmitter = null;
  protected JETEmitter editorPluginXMLEmitter = null;
  protected JETEmitter editorPluginPropertiesEmitter = null;
  protected JETEmitter editorBuildPropertiesEmitter = null;
  protected GIFEmitter modelGIFEmitter = null;
  protected GIFEmitter modelWizardGIFEmitter = null;

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

  public JETEmitter getItemProviderAdapterFactoryEmitter()
  {
    if (itemProviderAdapterFactoryEmitter == null)
    {
      itemProviderAdapterFactoryEmitter = createJETEmitter(itemProviderAdapterFactoryTemplateName);
      setMethod(itemProviderAdapterFactoryEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.ItemProviderAdapterFactory");
    }
    return itemProviderAdapterFactoryEmitter;
  }

  public JETEmitter getEditPluginClassEmitter()
  {
    if (editPluginClassEmitter == null)
    {
      editPluginClassEmitter = createJETEmitter(editPluginTemplateName);
      setMethod(editPluginClassEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.Plugin");
    }
    return editPluginClassEmitter;
  }

  public JETEmitter getEditPluginXMLEmitter()
  {
    if (editPluginXMLEmitter == null)
    {
      editPluginXMLEmitter = createJETEmitter(editPluginXMLTemplateName);
      setMethod(editPluginXMLEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.PluginXML");
    }
    return editPluginXMLEmitter;
  }

  public JETEmitter getEditPluginPropertiesEmitter()
  {
    if (editPluginPropertiesEmitter == null)
    {
      editPluginPropertiesEmitter = createJETEmitter(editPluginPropertiesTemplateName);
      setMethod(editPluginPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.PluginProperties");
    }
    return editPluginPropertiesEmitter;
  }

  public JETEmitter getEditBuildPropertiesEmitter()
  {
    if (editBuildPropertiesEmitter == null)
    {
      editBuildPropertiesEmitter = createJETEmitter(editBuildPropertiesTemplateName);
      setMethod(editBuildPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.edit.BuildProperties");
    }
    return editBuildPropertiesEmitter;
  }
  
  public GIFEmitter getItemGIFEmitter()
  {
    if (itemGIFEmitter == null)
    {
      itemGIFEmitter = new GIFEmitter(JETCompiler.find(getTemplatePath(), itemGIFName));
    }
    return itemGIFEmitter;
  }

  public GIFEmitter getCreateChildGIFEmitter()
  {
    if (createChildGIFEmitter == null)
    {
      createChildGIFEmitter = new GIFEmitter(JETCompiler.find(getTemplatePath(), createChildGIFName));
    }
    return createChildGIFEmitter;
  }

  public GIFEmitter getModelGIFEmitter()
  {
    if (modelGIFEmitter == null)
    {
      modelGIFEmitter = new GIFEmitter(JETCompiler.find(getTemplatePath(), modelGIFName));
    }
    return modelGIFEmitter;
  }

  public GIFEmitter getModelWizardGIFEmitter()
  {
    if (modelWizardGIFEmitter == null)
    {
      modelWizardGIFEmitter = new GIFEmitter(JETCompiler.find(getTemplatePath(), modelWizardGIFName));
    }
    return modelWizardGIFEmitter;
  }

  public JETEmitter getEditorEmitter()
  {
    if (editorEmitter == null)
    {
      editorEmitter = createJETEmitter(editorTemplateName);
      setMethod(editorEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.Editor");
    }
    return editorEmitter;
  }

  public JETEmitter getActionBarContributorEmitter()
  {
    if (actionBarContributorEmitter == null)
    {
      actionBarContributorEmitter = createJETEmitter(actionBarContributorTemplateName);
      setMethod(actionBarContributorEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.ActionBarContributor");
    }
    return actionBarContributorEmitter;
  }

  public JETEmitter getModelWizardEmitter()
  {
    if (modelWizardEmitter == null)
    {
      modelWizardEmitter = createJETEmitter(modelWizardTemplateName);
      setMethod(modelWizardEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.ModelWizard");
    }
    return modelWizardEmitter;
  }

  public JETEmitter getEditorAdvisorEmitter()
  {
    if (advisorEmitter == null)
    {
      advisorEmitter = createJETEmitter(advisorTemplateName);
      setMethod(advisorEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.Advisor");
    }
    return advisorEmitter;
  }

  public JETEmitter getEditorPluginClassEmitter()
  {
    if (editorPluginClassEmitter == null)
    {
      editorPluginClassEmitter = createJETEmitter(editorPluginTemplateName);
      setMethod(editorPluginClassEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.Plugin");
    }
    return editorPluginClassEmitter;
  }

  public JETEmitter getEditorPluginXMLEmitter()
  {
    if (editorPluginXMLEmitter == null)
    {
      editorPluginXMLEmitter = createJETEmitter(editorPluginXMLTemplateName);
      setMethod(editorPluginXMLEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.PluginXML");
    }
    return editorPluginXMLEmitter;
  }

  public JETEmitter getEditorPluginPropertiesEmitter()
  {
    if (editorPluginPropertiesEmitter == null)
    {
      editorPluginPropertiesEmitter = createJETEmitter(editorPluginPropertiesTemplateName);
      setMethod(editorPluginPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.editor.PluginProperties");
    }
    return editorPluginPropertiesEmitter;
  }

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
  
  protected String testCaseTemplateName = "model.tests/TestCase.javajet";
  protected String modelTestSuiteTemplateName = "model.tests/ModelTestSuite.javajet";
  protected String packageTestSuiteTemplateName = "model.tests/PackageTestSuite.javajet";
  protected String testsPluginXMLTemplateName = "model.tests/plugin.xmljet";
  protected String testsPluginPropertiesTemplateName = "model.tests/plugin.propertiesjet";
  protected String testsBuildPropertiesTemplateName = "model.tests/build.propertiesjet";

  protected JETEmitter testCaseEmitter = null;
  protected JETEmitter modelTestSuiteEmitter = null;
  protected JETEmitter packageTestSuiteEmitter = null;
  protected JETEmitter testsPluginXMLEmitter = null;
  protected JETEmitter testsPluginPropertiesEmitter = null;
  protected JETEmitter testsBuildPropertiesEmitter = null;

  public JETEmitter getTestCaseEmitter()
  {
    if (testCaseEmitter == null)
    {
      testCaseEmitter = createJETEmitter(testCaseTemplateName);
      setMethod(testCaseEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.TestCase");
    }

    return testCaseEmitter;
  }

  public JETEmitter getModelTestSuiteEmitter()
  {
    if (modelTestSuiteEmitter == null)
    {
      modelTestSuiteEmitter = createJETEmitter(modelTestSuiteTemplateName);
      setMethod(modelTestSuiteEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.ModelTestSuite");
    }

    return modelTestSuiteEmitter;
  }

  public JETEmitter getPackageTestSuiteEmitter()
  {
    if (packageTestSuiteEmitter == null)
    {
      packageTestSuiteEmitter = createJETEmitter(packageTestSuiteTemplateName);
      setMethod(packageTestSuiteEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.PackageTestSuite");
    }

    return packageTestSuiteEmitter;
  }

  public JETEmitter getTestsPluginXMLEmitter()
  {
    if (testsPluginXMLEmitter == null)
    {
      testsPluginXMLEmitter = createJETEmitter(testsPluginXMLTemplateName);
      setMethod(testsPluginXMLEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.PluginXML");
    }

    return testsPluginXMLEmitter;
  }

  public JETEmitter getTestsPluginPropertiesEmitter()
  {
    if (testsPluginPropertiesEmitter == null)
    {
      testsPluginPropertiesEmitter = createJETEmitter(testsPluginPropertiesTemplateName);
      setMethod(testsPluginPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.PluginProperties");
    }

    return testsPluginPropertiesEmitter;
  }

  public JETEmitter getTestsBuildPropertiesEmitter()
  {
    if (testsBuildPropertiesEmitter == null)
    {
      testsBuildPropertiesEmitter = createJETEmitter(testsBuildPropertiesTemplateName);
      setMethod(testsBuildPropertiesEmitter, "org.eclipse.emf.codegen.ecore.templates.model.tests.BuildProperties");
    }

    return testsBuildPropertiesEmitter;
  }

  /*
  public boolean isGenerateEditPlugin()
  {
    return getEditDirectory() != null;
  }

  public boolean isGenerateEditorPlugin()
  {
    return getEditorDirectory() != null;
  }
 */

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEditDirectoryGen()
  {
    return editDirectory;
  }

  public String getEditDirectory()
  {
    if (getEditDirectoryGen() != null)
    {
      return getEditDirectoryGen();
    }
    else
    {
      return getModelDirectory();
    }
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
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__EDIT_DIRECTORY, oldEditDirectory, editDirectory));
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

  public String getEditorDirectory()
  {
    if (getEditorDirectoryGen() != null)
    {
      return getEditorDirectoryGen();
    }
    else
    {
      return getEditDirectory();
    }
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
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY, oldEditorDirectory, editorDirectory));
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
  public EList getForeignModel()
  {
    if (foreignModel == null)
    {
      foreignModel = new EDataTypeUniqueEList(String.class, this, GenModelPackage.GEN_MODEL__FOREIGN_MODEL);
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

  public String getName()
  {
    return getModelName();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getModelNameGen()
  {
    return modelName;
  }

  public String getModelName()
  {
    String result = getModelNameGen();
    if (result == null)
    {
      result = getModelPluginID();
      if (result != null)
      {
        int index = result.lastIndexOf(".");
        if (index != -1)
        {
          result = result.substring(index + 1);
        }

        result = capName(result);
      }
    }

    return result;
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

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEditPluginClass()
  {
    return editPluginClass;
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
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__EDIT_PLUGIN_CLASS, oldEditPluginClass, editPluginClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEditorPluginClass()
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
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_CLASS, oldEditorPluginClass, editorPluginClass));
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
  public EList getStaticPackages()
  {
    if (staticPackages == null)
    {
      staticPackages = 
        new EDataTypeUniqueEList(String.class, this, GenModelPackage.GEN_MODEL__STATIC_PACKAGES)
        {
          protected void didChange()
          {
            super.didChange();
            staticGenPackages = null;
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
  public EList getModelPluginVariables()
  {
    if (modelPluginVariables == null)
    {
      modelPluginVariables = new EDataTypeUniqueEList(String.class, this, GenModelPackage.GEN_MODEL__MODEL_PLUGIN_VARIABLES);
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
    if (rootImplementsInterfaceGenClass == null)
    {
      for (Iterator i = getAllGenUsedAndStaticGenPackagesWithClassifiers().iterator(); i.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)i.next();
        for (Iterator j = genPackage.getGenClasses().iterator(); j.hasNext(); )
        {
          GenClass genClass = (GenClass)j.next();
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

  public List getEffectiveModelPluginVariables()
  {
    return getModelPluginVariables();
  }

  public List getEffectiveModelPluginIDs()
  {
    List result = new ArrayList(getModelPluginVariables());
    for (ListIterator i = result.listIterator(); i.hasNext(); )
    {
      Object item = i.next();
      if (item instanceof String)
      {
        String variable = (String)item;
        int index = variable.indexOf("=");
        if (index != -1)
        {
          i.set(variable.substring(index + 1));
        }
      }
      else
      {
        i.remove();
      }
    }

    return result;
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
    return isRuntimeCompatibility() && !isRichClientPlatform();
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
   * @generated
   */
  public boolean isRichClientPlatform()
  {
    return richClientPlatform;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRichClientPlatform(boolean newRichClientPlatform)
  {
    boolean oldRichClientPlatform = richClientPlatform;
    richClientPlatform = newRichClientPlatform;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__RICH_CLIENT_PLATFORM, oldRichClientPlatform, richClientPlatform));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isReflectiveDelegation()
  {
    return reflectiveDelegation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReflectiveDelegation(boolean newReflectiveDelegation)
  {
    boolean oldReflectiveDelegation = reflectiveDelegation;
    reflectiveDelegation = newReflectiveDelegation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__REFLECTIVE_DELEGATION, oldReflectiveDelegation, reflectiveDelegation));
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
  public String getTestsDirectory()
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
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__TESTS_DIRECTORY, oldTestsDirectory, testsDirectory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTestSuiteClass()
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
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS, oldTestSuiteClass, testSuiteClass));
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
  public EList getGenPackages()
  {
    if (genPackages == null)
    {
      genPackages = new EObjectContainmentWithInverseEList(GenPackage.class, this, GenModelPackage.GEN_MODEL__GEN_PACKAGES, GenModelPackage.GEN_PACKAGE__GEN_MODEL);
    }
    return genPackages;
  }

  public EList getStaticGenPackages()
  {
    if (staticGenPackages == null)
    {
      staticGenPackages = new UniqueEList();
      LOOP:
      for (Iterator i = getStaticPackages().iterator(); i.hasNext(); )
      {
        String nsURI = (String)i.next();
        for (Iterator j = getStaticGenPackages().iterator(); j.hasNext(); )
        {
          GenPackage staticGenPackage = (GenPackage)j.next();
          if (staticGenPackage.getNSURI().equals(nsURI))
          {
            continue LOOP;
          }
        }
        EPackage staticEPackage = eResource().getResourceSet().getPackageRegistry().getEPackage(nsURI);
        if (staticEPackage != null)
        {
          GenModel genModel = getGenModel().createGenModel();
          GenPackage staticGenPackage = getGenModel().createGenPackage();
          staticGenPackages.add(staticGenPackage);
          genModel.getGenPackages().add(staticGenPackage);
          staticGenPackage.initialize(staticEPackage);
        }
      }
    }
    return staticGenPackages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getUsedGenPackages()
  {
    if (usedGenPackages == null)
    {
      usedGenPackages = new EObjectResolvingEList(GenPackage.class, this, GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES);
    }
    return usedGenPackages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
          return ((InternalEList)getGenPackages()).basicAdd(otherEnd, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
          return ((InternalEList)getGenPackages()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case GenModelPackage.GEN_MODEL__COPYRIGHT_TEXT:
        return getCopyrightText();
      case GenModelPackage.GEN_MODEL__MODEL_DIRECTORY:
        return getModelDirectory();
      case GenModelPackage.GEN_MODEL__CREATION_COMMANDS:
        return isCreationCommands() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__CREATION_ICONS:
        return isCreationIcons() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__EDIT_DIRECTORY:
        return getEditDirectory();
      case GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY:
        return getEditorDirectory();
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_ID:
        return getModelPluginID();
      case GenModelPackage.GEN_MODEL__TEMPLATE_DIRECTORY:
        return getTemplateDirectory();
      case GenModelPackage.GEN_MODEL__RUNTIME_JAR:
        return isRuntimeJar() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__FOREIGN_MODEL:
        return getForeignModel();
      case GenModelPackage.GEN_MODEL__DYNAMIC_TEMPLATES:
        return isDynamicTemplates() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__REDIRECTION:
        return getRedirection();
      case GenModelPackage.GEN_MODEL__FORCE_OVERWRITE:
        return isForceOverwrite() ? Boolean.TRUE : Boolean.FALSE;
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
        return isUpdateClasspath() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__GENERATE_SCHEMA:
        return isGenerateSchema() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__NON_NLS_MARKERS:
        return isNonNLSMarkers() ? Boolean.TRUE : Boolean.FALSE;
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
        return isSuppressEMFTypes() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE:
        return getFeatureMapWrapperInterface();
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE:
        return getFeatureMapWrapperInternalInterface();
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS:
        return getFeatureMapWrapperClass();
      case GenModelPackage.GEN_MODEL__RUNTIME_COMPATIBILITY:
        return isRuntimeCompatibility() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__RICH_CLIENT_PLATFORM:
        return isRichClientPlatform() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__REFLECTIVE_DELEGATION:
        return isReflectiveDelegation() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__CODE_FORMATTING:
        return isCodeFormatting() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_MODEL__TESTS_DIRECTORY:
        return getTestsDirectory();
      case GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS:
        return getTestSuiteClass();
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_FIELD:
        return getBooleanFlagsField();
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS:
        return new Integer(getBooleanFlagsReservedBits());
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
        return getGenPackages();
      case GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES:
        return getUsedGenPackages();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case GenModelPackage.GEN_MODEL__COPYRIGHT_TEXT:
        return COPYRIGHT_TEXT_EDEFAULT == null ? copyrightText != null : !COPYRIGHT_TEXT_EDEFAULT.equals(copyrightText);
      case GenModelPackage.GEN_MODEL__MODEL_DIRECTORY:
        return MODEL_DIRECTORY_EDEFAULT == null ? modelDirectory != null : !MODEL_DIRECTORY_EDEFAULT.equals(modelDirectory);
      case GenModelPackage.GEN_MODEL__CREATION_COMMANDS:
        return creationCommands != CREATION_COMMANDS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__CREATION_ICONS:
        return creationIcons != CREATION_ICONS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__EDIT_DIRECTORY:
        return EDIT_DIRECTORY_EDEFAULT == null ? editDirectory != null : !EDIT_DIRECTORY_EDEFAULT.equals(editDirectory);
      case GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY:
        return EDITOR_DIRECTORY_EDEFAULT == null ? editorDirectory != null : !EDITOR_DIRECTORY_EDEFAULT.equals(editorDirectory);
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
        return EDIT_PLUGIN_CLASS_EDEFAULT == null ? editPluginClass != null : !EDIT_PLUGIN_CLASS_EDEFAULT.equals(editPluginClass);
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_CLASS:
        return EDITOR_PLUGIN_CLASS_EDEFAULT == null ? editorPluginClass != null : !EDITOR_PLUGIN_CLASS_EDEFAULT.equals(editorPluginClass);
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
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE:
        return FEATURE_MAP_WRAPPER_INTERFACE_EDEFAULT == null ? featureMapWrapperInterface != null : !FEATURE_MAP_WRAPPER_INTERFACE_EDEFAULT.equals(featureMapWrapperInterface);
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE:
        return FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE_EDEFAULT == null ? featureMapWrapperInternalInterface != null : !FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE_EDEFAULT.equals(featureMapWrapperInternalInterface);
      case GenModelPackage.GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS:
        return FEATURE_MAP_WRAPPER_CLASS_EDEFAULT == null ? featureMapWrapperClass != null : !FEATURE_MAP_WRAPPER_CLASS_EDEFAULT.equals(featureMapWrapperClass);
      case GenModelPackage.GEN_MODEL__RUNTIME_COMPATIBILITY:
        return runtimeCompatibility != RUNTIME_COMPATIBILITY_EDEFAULT;
      case GenModelPackage.GEN_MODEL__RICH_CLIENT_PLATFORM:
        return richClientPlatform != RICH_CLIENT_PLATFORM_EDEFAULT;
      case GenModelPackage.GEN_MODEL__REFLECTIVE_DELEGATION:
        return reflectiveDelegation != REFLECTIVE_DELEGATION_EDEFAULT;
      case GenModelPackage.GEN_MODEL__CODE_FORMATTING:
        return codeFormatting != CODE_FORMATTING_EDEFAULT;
      case GenModelPackage.GEN_MODEL__TESTS_DIRECTORY:
        return TESTS_DIRECTORY_EDEFAULT == null ? testsDirectory != null : !TESTS_DIRECTORY_EDEFAULT.equals(testsDirectory);
      case GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS:
        return TEST_SUITE_CLASS_EDEFAULT == null ? testSuiteClass != null : !TEST_SUITE_CLASS_EDEFAULT.equals(testSuiteClass);
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_FIELD:
        return BOOLEAN_FLAGS_FIELD_EDEFAULT == null ? booleanFlagsField != null : !BOOLEAN_FLAGS_FIELD_EDEFAULT.equals(booleanFlagsField);
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS:
        return booleanFlagsReservedBits != BOOLEAN_FLAGS_RESERVED_BITS_EDEFAULT;
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
        return genPackages != null && !genPackages.isEmpty();
      case GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES:
        return usedGenPackages != null && !usedGenPackages.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case GenModelPackage.GEN_MODEL__COPYRIGHT_TEXT:
        setCopyrightText((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__MODEL_DIRECTORY:
        setModelDirectory((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__CREATION_COMMANDS:
        setCreationCommands(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_MODEL__CREATION_ICONS:
        setCreationIcons(((Boolean)newValue).booleanValue());
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
        setRuntimeJar(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_MODEL__FOREIGN_MODEL:
        getForeignModel().clear();
        getForeignModel().addAll((Collection)newValue);
        return;
      case GenModelPackage.GEN_MODEL__DYNAMIC_TEMPLATES:
        setDynamicTemplates(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_MODEL__REDIRECTION:
        setRedirection((String)newValue);
        return;
      case GenModelPackage.GEN_MODEL__FORCE_OVERWRITE:
        setForceOverwrite(((Boolean)newValue).booleanValue());
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
        setUpdateClasspath(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_MODEL__GENERATE_SCHEMA:
        setGenerateSchema(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_MODEL__NON_NLS_MARKERS:
        setNonNLSMarkers(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_MODEL__STATIC_PACKAGES:
        getStaticPackages().clear();
        getStaticPackages().addAll((Collection)newValue);
        return;
      case GenModelPackage.GEN_MODEL__MODEL_PLUGIN_VARIABLES:
        getModelPluginVariables().clear();
        getModelPluginVariables().addAll((Collection)newValue);
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
        setSuppressEMFTypes(((Boolean)newValue).booleanValue());
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
        setRuntimeCompatibility(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_MODEL__RICH_CLIENT_PLATFORM:
        setRichClientPlatform(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_MODEL__REFLECTIVE_DELEGATION:
        setReflectiveDelegation(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_MODEL__CODE_FORMATTING:
        setCodeFormatting(((Boolean)newValue).booleanValue());
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
        setBooleanFlagsReservedBits(((Integer)newValue).intValue());
        return;
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
        getGenPackages().clear();
        getGenPackages().addAll((Collection)newValue);
        return;
      case GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES:
        getUsedGenPackages().clear();
        getUsedGenPackages().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
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
      case GenModelPackage.GEN_MODEL__EDIT_DIRECTORY:
        setEditDirectory(EDIT_DIRECTORY_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__EDITOR_DIRECTORY:
        setEditorDirectory(EDITOR_DIRECTORY_EDEFAULT);
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
        setEditPluginClass(EDIT_PLUGIN_CLASS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__EDITOR_PLUGIN_CLASS:
        setEditorPluginClass(EDITOR_PLUGIN_CLASS_EDEFAULT);
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
      case GenModelPackage.GEN_MODEL__TESTS_DIRECTORY:
        setTestsDirectory(TESTS_DIRECTORY_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__TEST_SUITE_CLASS:
        setTestSuiteClass(TEST_SUITE_CLASS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_FIELD:
        setBooleanFlagsField(BOOLEAN_FLAGS_FIELD_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS:
        setBooleanFlagsReservedBits(BOOLEAN_FLAGS_RESERVED_BITS_EDEFAULT);
        return;
      case GenModelPackage.GEN_MODEL__GEN_PACKAGES:
        getGenPackages().clear();
        return;
      case GenModelPackage.GEN_MODEL__USED_GEN_PACKAGES:
        getUsedGenPackages().clear();
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
    result.append(", editDirectory: ");
    result.append(editDirectory);
    result.append(", editorDirectory: ");
    result.append(editorDirectory);
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
    result.append(editPluginClass);
    result.append(", editorPluginClass: ");
    result.append(editorPluginClass);
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
    result.append(", featureMapWrapperInterface: ");
    result.append(featureMapWrapperInterface);
    result.append(", featureMapWrapperInternalInterface: ");
    result.append(featureMapWrapperInternalInterface);
    result.append(", featureMapWrapperClass: ");
    result.append(featureMapWrapperClass);
    result.append(", runtimeCompatibility: ");
    result.append(runtimeCompatibility);
    result.append(", richClientPlatform: ");
    result.append(richClientPlatform);
    result.append(", reflectiveDelegation: ");
    result.append(reflectiveDelegation);
    result.append(", codeFormatting: ");
    result.append(codeFormatting);
    result.append(", testsDirectory: ");
    result.append(testsDirectory);
    result.append(", testSuiteClass: ");
    result.append(testSuiteClass);
    result.append(", booleanFlagsField: ");
    result.append(booleanFlagsField);
    result.append(", booleanFlagsReservedBits: ");
    result.append(booleanFlagsReservedBits);
    result.append(')');
    return result.toString();
  }

  static protected String getProjectPath(String path)
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
    return getModelProjectDirectory().equals(getEditProjectDirectory());
  }

  public boolean sameEditEditorProject()
  {
    return getEditProjectDirectory().equals(getEditorProjectDirectory());
  }

  public boolean sameModelEditorProject()
  {
    return getModelProjectDirectory().equals(getEditorProjectDirectory());
  }

  public boolean sameModelTestsProject()
  {
    // Different than the Edit and Editor projects, this method is invoked while 
    // generating the model plugin xml.
    return getTestsDirectory() == null ? false : getModelProjectDirectory().equals(getTestsProjectDirectory());
  }

  public String getEditIconsDirectory()
  {
    return getEditProjectDirectory() + "/icons";
  }

  public String getEditorIconsDirectory()
  {
    return getEditorProjectDirectory() + "/icons";
  }

  public String getEditPluginID()
  {
    if (sameModelEditProject())
    {
      return getModelPluginID();
    }
    else
    {
      return getModelPluginID() + ".edit";
    }
  }

  public String getEditorPluginID()
  {
    if (sameEditEditorProject())
    {
      return getEditPluginID();
    }
    else
    {
      return getModelPluginID() + ".editor";
    }
  }

  public String getTestsPluginID()
  {
    if (sameModelTestsProject())
    {
      return getModelPluginID();
    }
    else
    {
      return getModelPluginID() + ".tests";
    }
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

  protected String getEditPluginDirectory()
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

  protected String getEditorPluginDirectory()
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
  
  public String getModelPluginPackageName()
  {
    String result = getModelPluginClassToUse();
    if (result != null)
    {
      int index = result.lastIndexOf(".");
      if (index == -1)
      {
        result = getModelPluginID();
      }
      else
      {
        result = result.substring(0, index);
      }
    }
    return result;
  }

  public String getModelPluginClassName()
  {
    String result = getModelPluginClassToUse();
    if (result != null)
    {
      int index = result.lastIndexOf(".");
      if (index != -1)
      {
        result = result.substring(index + 1);
      }
    }
    return result;
  }

  public String getQualifiedModelPluginClassName()
  {
    String result = getModelPluginClassToUse();
    if (result != null)
    {
      int index = result.lastIndexOf(".");
      if (index == -1)
      {
        result = getEditPluginID() + "." + result;
      }
    }
    return result;
  }

  public String getEditPluginPackageName()
  {
    String result = getEditPluginClassToUse();
    if (result == null)
    {
      result = getEditPluginID();
    }
    else
    {
      int index = result.lastIndexOf(".");
      if (index == -1)
      {
        result = getEditPluginID();
      }
      else
      {
        result = result.substring(0, index);
      }
    }
    return result;
  }

  public String getEditPluginClassName()
  {
    String result = getEditPluginClassToUse();
    if (result == null)
    {
      result = getModelName() + "EditPlugin";  
    }
    else
    {
      int index = result.lastIndexOf(".");
      if (index != -1)
      {
        result = result.substring(index + 1);
      }
    }
    return result;
  }

  public String getQualifiedEditPluginClassName()
  {
    String result = getEditPluginClassToUse();
    if (result == null)
    {
      result = getEditPluginID() + "." + getModelName() + "EditPlugin";
    }
    else
    {
      int index = result.lastIndexOf(".");
      if (index == -1)
      {
        result = getEditPluginID() + "." + result;
      }
    }
    return result;
  }

  public String getEditorPluginPackageName()
  {
    String result = getEditorPluginClassToUse();
    if (result == null)
    {
      result = getEditorPluginID();
    }
    else
    {
      int index = result.lastIndexOf(".");
      if (index == -1)
      {
        result = getEditorPluginID();
      }
      else
      {
        result = result.substring(0, index);
      }
    }
    return result;
  }

  public String getEditorPluginClassName()
  {
    String result = getEditorPluginClassToUse();
    if (result == null)
    {
      result = getModelName() + "EditorPlugin";  
    }
    else
    {
      int index = result.lastIndexOf(".");
      if (index != -1)
      {
        result = result.substring(index + 1);
      }
    }
    return result;
  }

  public String getQualifiedEditorPluginClassName()
  {
    String result = getEditorPluginClassToUse();
    if (result == null)
    {
      result = getEditorPluginID() + "." + getModelName() + "EditorPlugin";
    }
    else
    {
      int index = result.lastIndexOf(".");
      if (index == -1)
      {
        result = getEditorPluginID() + "." + result;
      }
    }
    return result;
  }

  public String getQualifiedEditorAdvisorClassName()
  {
//    String packageName = getQualifiedEditorPluginClassName();
//    int index = packageName.lastIndexOf('.');
//    if (index >= 0)
//    {
//      packageName = packageName.substring(0, index);
//    }
    return getEditorPluginPackageName() + "." + getEditorAdvisorClassName();
  }
  
  public String getEditorAdvisorClassName()
  {
    return getModelName() + "EditorAdvisor";
  }

  public String getTestSuitePackageName()
  {
    String result = getTestSuiteClass();
    if (isBlank(result))
    {
      result = getTestsPluginID();
    }
    else
    {
      int index = result.lastIndexOf(".");
      if (index != -1)
      {
        result = result.substring(0, index);
      }
    }
    return result;
  }

  public String getTestSuiteClassName()
  {
    String result = getTestSuiteClass();
    if (isBlank(result))
    {
      result = getModelName() + "AllTests";  
    }
    else
    {
      int index = result.lastIndexOf(".");
      if (index != -1)
      {
        result = result.substring(index + 1);
      }
    }
    return result;
  }

  public String getQualifiedTestSuiteClassName()
  {
    return getTestSuitePackageName() + "." + getTestSuiteClassName();
  }

  protected void getAllGenPackagesWithClassifiersHelper(List result, List genPackages)
  {
    for (Iterator i = genPackages.iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      if (genPackage.hasClassifiers())
      {
        result.add(genPackage);
      }
      getAllGenPackagesWithClassifiersHelper(result, genPackage.getNestedGenPackages());
    }
  }

  public List getAllGenPackagesWithClassifiers()
  {
    List result = new ArrayList();
    getAllGenPackagesWithClassifiersHelper(result, getGenPackages());
    return result;
  }

  public List getAllUsedGenPackagesWithClassifiers()
  {
    List result = new ArrayList();
    getAllGenPackagesWithClassifiersHelper(result, getUsedGenPackages());
    return result;
  }

  public List getAllGenAndUsedGenPackagesWithClassifiers()
  {
    List result = new ArrayList();
    getAllGenPackagesWithClassifiersHelper(result, getGenPackages());
    getAllGenPackagesWithClassifiersHelper(result, getUsedGenPackages());
    return result;
  }

  public List getAllGenUsedAndStaticGenPackagesWithClassifiers()
  {
    List result = new ArrayList();
    getAllGenPackagesWithClassifiersHelper(result, getGenPackages());
    getAllGenPackagesWithClassifiersHelper(result, getUsedGenPackages());
    getAllGenPackagesWithClassifiersHelper(result, getStaticGenPackages());
    return result;
  }

  public List getModelRequiredPlugins()
  {
    List result = new UniqueEList();
    result.add(needsRuntimeCompatibility() ? "org.eclipse.core.runtime.compatibility" : "org.eclipse.core.runtime");
    result.add("org.eclipse.emf.ecore");
    result.addAll(getEffectiveModelPluginIDs());
    for (Iterator i = getGenPackages().iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      if (genPackage.getResource() != GenResourceKind.NONE_LITERAL || genPackage.isLoadingInitialization())
      {
        result.add("org.eclipse.emf.ecore.xmi");
        break;
      }
    }
    for (Iterator i = getUsedGenPackages().iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      result.add(genPackage.getGenModel().getModelPluginID());
    }

    if (sameModelTestsProject())
    {
      result.add("org.junit");
    }

    return result;
  }

  public List getEditRequiredPlugins()
  {
    List result = new UniqueEList();
    result.add(needsRuntimeCompatibility() ? "org.eclipse.core.runtime.compatibility" : "org.eclipse.core.runtime");

    if (!sameModelEditProject())
    {
      for (Iterator i = getGenPackages().iterator(); i.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)i.next();
        result.add(genPackage.getGenModel().getModelPluginID());
      }
    }
    else
    {
      result.addAll(getModelRequiredPlugins());
    }
    
    result.add("org.eclipse.emf.edit");
    
    for (Iterator i = getUsedGenPackages().iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      GenModel genModel = genPackage.getGenModel();
      result.add(genModel.getModelPluginID());
      if (genModel.hasEditSupport())
      {
        result.add(genModel.getEditPluginID());
      }
    }
    return result;
  }

  public List getEditorRequiredPlugins()
  {
    List result = new UniqueEList();
    result.add(needsRuntimeCompatibility() ? "org.eclipse.core.runtime.compatibility" : "org.eclipse.core.runtime");
    if (!isRichClientPlatform())
    {
      result.add("org.eclipse.core.resources");
    }
    
    if (!sameEditEditorProject())
    {
      for (Iterator i = getGenPackages().iterator(); i.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)i.next();
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
    result.add("org.eclipse.emf.ecore.xmi");
    result.add("org.eclipse.emf.edit.ui");
    if (!isRichClientPlatform())
    {
      result.add("org.eclipse.ui.ide");
    }
    for (Iterator i = getUsedGenPackages().iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      GenModel genModel = genPackage.getGenModel();
      if (genModel.hasEditSupport())
      {
        result.add(genModel.getEditPluginID());
      }
    }
    return result;
  }

  public List getTestsRequiredPlugins()
  {
    List result = new UniqueEList();
    result.add(needsRuntimeCompatibility() ? "org.eclipse.core.runtime.compatibility" : "org.eclipse.core.runtime");

    result.add(getModelPluginID());
    for (Iterator i = getUsedGenPackages().iterator(); i.hasNext();)
    {
      GenPackage genPackage = (GenPackage)i.next();
      GenModel genModel = genPackage.getGenModel();
      result.add(genModel.getModelPluginID());
    }

    result.add("org.junit");

    return result;
  }

  public List getEditResourceDelegateImportedPluginClassNames()
  {
    List result = new UniqueEList();
    for (Iterator i = getUsedGenPackages().iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
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
      for (Iterator i = getGenPackages().iterator(); i.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)i.next();
        for (Iterator j = oldGenModelVersion.getGenPackages().iterator(); j.hasNext(); )
        {
          GenPackage oldGenPackageVersion = (GenPackage)j.next();
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
    setCopyrightText(oldGenModelVersion.getCopyrightText());
    setModelDirectory(oldGenModelVersion.getModelDirectory());
    setCreationCommands(oldGenModelVersion.isCreationCommands());
    setCreationIcons(oldGenModelVersion.isCreationIcons());
    setEditDirectory(oldGenModelVersion.getEditDirectory());
    setEditorDirectory(oldGenModelVersion.getEditorDirectory());
    setModelPluginID(oldGenModelVersion.getModelPluginID());
    setTemplateDirectory(oldGenModelVersion.getTemplateDirectory());
    setRuntimeJar(oldGenModelVersion.isRuntimeJar());

    // EATM 
    // Foreign Model

    setDynamicTemplates(oldGenModelVersion.isDynamicTemplates());
    setRedirection(oldGenModelVersion.getRedirection());
    setForceOverwrite(oldGenModelVersion.isForceOverwrite());
    setNonExternalizedStringTag(oldGenModelVersion.getNonExternalizedStringTag());

    setModelName(oldGenModelVersion.getModelName());

    setModelPluginClass(oldGenModelVersion.getModelPluginClass());
    setEditPluginClass(oldGenModelVersion.getEditPluginClass());
    setEditorPluginClass(oldGenModelVersion.getEditorPluginClass());
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
    setRichClientPlatform(oldGenModelVersion.isRichClientPlatform());
    setCodeFormatting(oldGenModelVersion.isCodeFormatting());
    
    setTestsDirectory(oldGenModelVersion.getTestsDirectory());
    setTestSuiteClass(oldGenModelVersion.getTestSuiteClass());
    
    setBooleanFlagsField(oldGenModelVersion.getBooleanFlagsField());
    setBooleanFlagsReservedBits(oldGenModelVersion.getBooleanFlagsReservedBits());
  }

  public boolean reconcile()
  {
    for (Iterator i = getGenPackages().iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      if (!genPackage.reconcile())
      {
        i.remove();
      }
    }
    for (Iterator i = getUsedGenPackages().iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      if (!genPackage.reconcile())
      {
        i.remove();
      }
    }

    return !getGenPackages().isEmpty();
  }

  public List getMissingPackages()
  {
    List ePackages = new UniqueEList();
    getMissingPackagesHelper(ePackages, getGenPackages());
    getMissingPackagesHelper(ePackages, getUsedGenPackages());
    return ePackages;
  }

  protected void getMissingPackagesHelper(List ePackages, List genPackages)
  {
    for (Iterator i = genPackages.iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      for (Iterator j = genPackage.getEcorePackage().eAllContents(); j.hasNext();)
      {
        EObject eObject = (EObject)j.next();
        for (Iterator k = eObject.eCrossReferences().iterator(); k.hasNext(); )
        {
          Object o = k.next();
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


  public boolean hasXMLDependency()
  {
    for (Iterator i = getAllGenPackagesWithClassifiers().iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
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

  public  EModelElement getEcoreModelElement()
  {
    return ((GenPackage)getGenPackages().get(0)).getEcorePackage();
  }

  public List/*GenFeature*/ getAllGenFeatures()
  {
    List result = new ArrayList();

    // Any features from one package that delegate to features in another.
    //
    List delegated = new ArrayList();

    for (Iterator iter = getAllGenAndUsedGenPackagesWithClassifiers().iterator(); iter.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)iter.next();
      if (genPackage.getGenModel() == this || !genPackage.getGenModel().hasEditSupport())
      {
        for (Iterator fIter = genPackage.getAllGenFeatures().iterator(); fIter.hasNext(); )
        {
          GenFeature genFeature = (GenFeature)fIter.next();
          List addTo = genFeature.getGenPackage() == genPackage ? result : delegated;
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

  public List/*GenFeature*/ getFilteredAllGenFeatures()
  {
    ArrayList result = new ArrayList();

    // We need to filer out duplicates in the unlikely event that we have two
    // features with the same class-qualifed name. We'll only generate one property
    // string in that case and let the user add the second one mannually, if necessary.
    //
    Set noDupSet = new HashSet();
    for (Iterator iter = getAllGenFeatures().iterator(); iter.hasNext(); )
    {
      GenFeature genFeature = (GenFeature)iter.next();
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

  protected Map codeFormatterOptions = null;

  public void setCodeFormatterOptions(Map options)
  {
    codeFormatterOptions = options;
  }

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
  
  public Set getPropertyCategories()
  {
    Set categories = new HashSet();
    for (Iterator i = getFilteredAllGenFeatures().iterator(); i.hasNext();)
    {
      String category = ((GenFeature)i.next()).getPropertyCategory();

      if (!isBlank(category))
      {
        categories.add(category);
      }
    }
    return categories;
  }

} //GenModelImpl
