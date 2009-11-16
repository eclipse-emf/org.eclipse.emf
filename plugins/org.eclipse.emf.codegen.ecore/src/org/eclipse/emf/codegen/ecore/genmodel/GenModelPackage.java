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
 * $Id: GenModelPackage.java,v 1.54 2009/11/16 19:26:45 khussey Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;


/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent 
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each interface,</li>
 *   <li>each operation of each class or interface,</li>
 *   <li>each enum,</li>
 *   <li>each literal of each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory
 * @model kind="package"
 * @generated
 */
public interface GenModelPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "genmodel";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/2002/GenModel";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "genmodel";

  /**
   * The package content type ID.
   * <!-- begin-user-doc -->
   * @since 2.4
   * <!-- end-user-doc -->
   * @generated
   */
  String eCONTENT_TYPE = "org.eclipse.emf.codegen.genmodel";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  GenModelPackage eINSTANCE = org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenBaseImpl <em>Gen Base</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenBaseImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenBase()
   * @generated
   */
  int GEN_BASE = 4;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_BASE__GEN_ANNOTATIONS = 0;

  /**
   * The number of structural features of the '<em>Gen Base</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_BASE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl <em>Gen Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenModel()
   * @generated
   */
  int GEN_MODEL = 0;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__GEN_ANNOTATIONS = GEN_BASE__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Copyright Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__COPYRIGHT_TEXT = GEN_BASE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Model Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__MODEL_DIRECTORY = GEN_BASE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Creation Commands</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__CREATION_COMMANDS = GEN_BASE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Creation Icons</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__CREATION_ICONS = GEN_BASE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Creation Submenus</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__CREATION_SUBMENUS = GEN_BASE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Edit Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__EDIT_DIRECTORY = GEN_BASE_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Editor Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__EDITOR_DIRECTORY = GEN_BASE_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Model Plugin ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__MODEL_PLUGIN_ID = GEN_BASE_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Template Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__TEMPLATE_DIRECTORY = GEN_BASE_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Runtime Jar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__RUNTIME_JAR = GEN_BASE_FEATURE_COUNT + 9;

  /**
   * The feature id for the '<em><b>Foreign Model</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__FOREIGN_MODEL = GEN_BASE_FEATURE_COUNT + 10;

  /**
   * The feature id for the '<em><b>Dynamic Templates</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__DYNAMIC_TEMPLATES = GEN_BASE_FEATURE_COUNT + 11;

  /**
   * The feature id for the '<em><b>Redirection</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__REDIRECTION = GEN_BASE_FEATURE_COUNT + 12;

  /**
   * The feature id for the '<em><b>Force Overwrite</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__FORCE_OVERWRITE = GEN_BASE_FEATURE_COUNT + 13;

  /**
   * The feature id for the '<em><b>Non Externalized String Tag</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__NON_EXTERNALIZED_STRING_TAG = GEN_BASE_FEATURE_COUNT + 14;

  /**
   * The feature id for the '<em><b>Model Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__MODEL_NAME = GEN_BASE_FEATURE_COUNT + 15;

  /**
   * The feature id for the '<em><b>Model Plugin Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__MODEL_PLUGIN_CLASS = GEN_BASE_FEATURE_COUNT + 16;

  /**
   * The feature id for the '<em><b>Edit Plugin Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__EDIT_PLUGIN_CLASS = GEN_BASE_FEATURE_COUNT + 17;

  /**
   * The feature id for the '<em><b>Editor Plugin Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__EDITOR_PLUGIN_CLASS = GEN_BASE_FEATURE_COUNT + 18;

  /**
   * The feature id for the '<em><b>Update Classpath</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__UPDATE_CLASSPATH = GEN_BASE_FEATURE_COUNT + 19;

  /**
   * The feature id for the '<em><b>Generate Schema</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__GENERATE_SCHEMA = GEN_BASE_FEATURE_COUNT + 20;

  /**
   * The feature id for the '<em><b>Non NLS Markers</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__NON_NLS_MARKERS = GEN_BASE_FEATURE_COUNT + 21;

  /**
   * The feature id for the '<em><b>Static Packages</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__STATIC_PACKAGES = GEN_BASE_FEATURE_COUNT + 22;

  /**
   * The feature id for the '<em><b>Model Plugin Variables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__MODEL_PLUGIN_VARIABLES = GEN_BASE_FEATURE_COUNT + 23;

  /**
   * The feature id for the '<em><b>Root Extends Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__ROOT_EXTENDS_INTERFACE = GEN_BASE_FEATURE_COUNT + 24;

  /**
   * The feature id for the '<em><b>Root Extends Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__ROOT_EXTENDS_CLASS = GEN_BASE_FEATURE_COUNT + 25;

  /**
   * The feature id for the '<em><b>Root Implements Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__ROOT_IMPLEMENTS_INTERFACE = GEN_BASE_FEATURE_COUNT + 26;

  /**
   * The feature id for the '<em><b>Suppress EMF Types</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__SUPPRESS_EMF_TYPES = GEN_BASE_FEATURE_COUNT + 27;

  /**
   * The feature id for the '<em><b>Suppress EMF Meta Data</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__SUPPRESS_EMF_META_DATA = GEN_BASE_FEATURE_COUNT + 28;

  /**
   * The feature id for the '<em><b>Suppress EMF Model Tags</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__SUPPRESS_EMF_MODEL_TAGS = GEN_BASE_FEATURE_COUNT + 29;

  /**
   * The feature id for the '<em><b>Suppress Interfaces</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__SUPPRESS_INTERFACES = GEN_BASE_FEATURE_COUNT + 30;

  /**
   * The feature id for the '<em><b>Feature Map Wrapper Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE = GEN_BASE_FEATURE_COUNT + 31;

  /**
   * The feature id for the '<em><b>Feature Map Wrapper Internal Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE = GEN_BASE_FEATURE_COUNT + 32;

  /**
   * The feature id for the '<em><b>Feature Map Wrapper Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS = GEN_BASE_FEATURE_COUNT + 33;

  /**
   * The feature id for the '<em><b>Runtime Compatibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__RUNTIME_COMPATIBILITY = GEN_BASE_FEATURE_COUNT + 34;

  /**
   * The feature id for the '<em><b>Rich Client Platform</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__RICH_CLIENT_PLATFORM = GEN_BASE_FEATURE_COUNT + 35;

  /**
   * The feature id for the '<em><b>Reflective Delegation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__REFLECTIVE_DELEGATION = GEN_BASE_FEATURE_COUNT + 36;

  /**
   * The feature id for the '<em><b>Code Formatting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__CODE_FORMATTING = GEN_BASE_FEATURE_COUNT + 37;

  /**
   * The feature id for the '<em><b>Tests Directory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__TESTS_DIRECTORY = GEN_BASE_FEATURE_COUNT + 38;

  /**
   * The feature id for the '<em><b>Test Suite Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__TEST_SUITE_CLASS = GEN_BASE_FEATURE_COUNT + 39;

  /**
   * The feature id for the '<em><b>Boolean Flags Field</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__BOOLEAN_FLAGS_FIELD = GEN_BASE_FEATURE_COUNT + 40;

  /**
   * The feature id for the '<em><b>Boolean Flags Reserved Bits</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS = GEN_BASE_FEATURE_COUNT + 41;

  /**
   * The feature id for the '<em><b>Importer ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__IMPORTER_ID = GEN_BASE_FEATURE_COUNT + 42;

  /**
   * The feature id for the '<em><b>Bundle Manifest</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__BUNDLE_MANIFEST = GEN_BASE_FEATURE_COUNT + 43;

  /**
   * The feature id for the '<em><b>Feature Delegation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__FEATURE_DELEGATION = GEN_BASE_FEATURE_COUNT + 44;

  /**
   * The feature id for the '<em><b>Containment Proxies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__CONTAINMENT_PROXIES = GEN_BASE_FEATURE_COUNT + 45;

  /**
   * The feature id for the '<em><b>Minimal Reflective Methods</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__MINIMAL_REFLECTIVE_METHODS = GEN_BASE_FEATURE_COUNT + 46;

  /**
   * The feature id for the '<em><b>Suppress Containment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__SUPPRESS_CONTAINMENT = GEN_BASE_FEATURE_COUNT + 47;

  /**
   * The feature id for the '<em><b>Suppress Notification</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__SUPPRESS_NOTIFICATION = GEN_BASE_FEATURE_COUNT + 48;

  /**
   * The feature id for the '<em><b>Array Accessors</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__ARRAY_ACCESSORS = GEN_BASE_FEATURE_COUNT + 49;

  /**
   * The feature id for the '<em><b>Suppress Unsettable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__SUPPRESS_UNSETTABLE = GEN_BASE_FEATURE_COUNT + 50;

  /**
   * The feature id for the '<em><b>Facade Helper Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__FACADE_HELPER_CLASS = GEN_BASE_FEATURE_COUNT + 51;

  /**
   * The feature id for the '<em><b>Compliance Level</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__COMPLIANCE_LEVEL = GEN_BASE_FEATURE_COUNT + 52;

  /**
   * The feature id for the '<em><b>Suppress Gen Model Annotations</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__SUPPRESS_GEN_MODEL_ANNOTATIONS = GEN_BASE_FEATURE_COUNT + 53;

  /**
   * The feature id for the '<em><b>Copyright Fields</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__COPYRIGHT_FIELDS = GEN_BASE_FEATURE_COUNT + 54;

  /**
   * The feature id for the '<em><b>Binary Compatible Reflective Methods</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__BINARY_COMPATIBLE_REFLECTIVE_METHODS = GEN_BASE_FEATURE_COUNT + 55;

  /**
   * The feature id for the '<em><b>Public Constructors</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__PUBLIC_CONSTRUCTORS = GEN_BASE_FEATURE_COUNT + 56;

  /**
   * The feature id for the '<em><b>Template Plugin Variables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__TEMPLATE_PLUGIN_VARIABLES = GEN_BASE_FEATURE_COUNT + 57;

  /**
   * The feature id for the '<em><b>Provider Root Extends Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__PROVIDER_ROOT_EXTENDS_CLASS = GEN_BASE_FEATURE_COUNT + 58;

  /**
   * The feature id for the '<em><b>Edit Plugin ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__EDIT_PLUGIN_ID = GEN_BASE_FEATURE_COUNT + 59;

  /**
   * The feature id for the '<em><b>Edit Plugin Variables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__EDIT_PLUGIN_VARIABLES = GEN_BASE_FEATURE_COUNT + 60;

  /**
   * The feature id for the '<em><b>Editor Plugin ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__EDITOR_PLUGIN_ID = GEN_BASE_FEATURE_COUNT + 61;

  /**
   * The feature id for the '<em><b>Editor Plugin Variables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__EDITOR_PLUGIN_VARIABLES = GEN_BASE_FEATURE_COUNT + 62;

  /**
   * The feature id for the '<em><b>Tests Plugin ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__TESTS_PLUGIN_ID = GEN_BASE_FEATURE_COUNT + 63;

  /**
   * The feature id for the '<em><b>Tests Plugin Variables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__TESTS_PLUGIN_VARIABLES = GEN_BASE_FEATURE_COUNT + 64;

  /**
   * The feature id for the '<em><b>Optimized Has Children</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__OPTIMIZED_HAS_CHILDREN = GEN_BASE_FEATURE_COUNT + 65;

  /**
   * The feature id for the '<em><b>Table Providers</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__TABLE_PROVIDERS = GEN_BASE_FEATURE_COUNT + 66;

  /**
   * The feature id for the '<em><b>Color Providers</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__COLOR_PROVIDERS = GEN_BASE_FEATURE_COUNT + 67;

  /**
   * The feature id for the '<em><b>Font Providers</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__FONT_PROVIDERS = GEN_BASE_FEATURE_COUNT + 68;

  /**
   * The feature id for the '<em><b>Runtime Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__RUNTIME_VERSION = GEN_BASE_FEATURE_COUNT + 69;

  /**
   * The feature id for the '<em><b>Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__LANGUAGE = GEN_BASE_FEATURE_COUNT + 70;

  /**
   * The feature id for the '<em><b>Packed Enums</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__PACKED_ENUMS = GEN_BASE_FEATURE_COUNT + 71;

  /**
   * The feature id for the '<em><b>Gen Packages</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__GEN_PACKAGES = GEN_BASE_FEATURE_COUNT + 72;

  /**
   * The feature id for the '<em><b>Used Gen Packages</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__USED_GEN_PACKAGES = GEN_BASE_FEATURE_COUNT + 73;

  /**
   * The feature id for the '<em><b>Interface Name Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__INTERFACE_NAME_PATTERN = GEN_BASE_FEATURE_COUNT + 74;

  /**
   * The feature id for the '<em><b>Class Name Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__CLASS_NAME_PATTERN = GEN_BASE_FEATURE_COUNT + 75;

  /**
   * The feature id for the '<em><b>Operation Reflection</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL__OPERATION_REFLECTION = GEN_BASE_FEATURE_COUNT + 76;

  /**
   * The number of structural features of the '<em>Gen Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_MODEL_FEATURE_COUNT = GEN_BASE_FEATURE_COUNT + 77;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl <em>Gen Package</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenPackage()
   * @generated
   */
  int GEN_PACKAGE = 1;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__GEN_ANNOTATIONS = GEN_BASE__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Prefix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__PREFIX = GEN_BASE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Base Package</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__BASE_PACKAGE = GEN_BASE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__RESOURCE = GEN_BASE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Disposable Provider Factory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__DISPOSABLE_PROVIDER_FACTORY = GEN_BASE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Adapter Factory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__ADAPTER_FACTORY = GEN_BASE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Load Initialization</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__LOAD_INITIALIZATION = GEN_BASE_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Interface Package Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX = GEN_BASE_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Meta Data Package Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__META_DATA_PACKAGE_SUFFIX = GEN_BASE_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Class Package Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__CLASS_PACKAGE_SUFFIX = GEN_BASE_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Utility Package Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__UTILITY_PACKAGE_SUFFIX = GEN_BASE_FEATURE_COUNT + 9;

  /**
   * The feature id for the '<em><b>Provider Package Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__PROVIDER_PACKAGE_SUFFIX = GEN_BASE_FEATURE_COUNT + 10;

  /**
   * The feature id for the '<em><b>Presentation Package Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__PRESENTATION_PACKAGE_SUFFIX = GEN_BASE_FEATURE_COUNT + 11;

  /**
   * The feature id for the '<em><b>Tests Package Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__TESTS_PACKAGE_SUFFIX = GEN_BASE_FEATURE_COUNT + 12;

  /**
   * The feature id for the '<em><b>Generate Example Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__GENERATE_EXAMPLE_CLASS = GEN_BASE_FEATURE_COUNT + 13;

  /**
   * The feature id for the '<em><b>Literals Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__LITERALS_INTERFACE = GEN_BASE_FEATURE_COUNT + 14;

  /**
   * The feature id for the '<em><b>Data Type Converters</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__DATA_TYPE_CONVERTERS = GEN_BASE_FEATURE_COUNT + 15;

  /**
   * The feature id for the '<em><b>Multiple Editor Pages</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__MULTIPLE_EDITOR_PAGES = GEN_BASE_FEATURE_COUNT + 16;

  /**
   * The feature id for the '<em><b>Generate Model Wizard</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__GENERATE_MODEL_WIZARD = GEN_BASE_FEATURE_COUNT + 17;

  /**
   * The feature id for the '<em><b>Extensible Provider Factory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__EXTENSIBLE_PROVIDER_FACTORY = GEN_BASE_FEATURE_COUNT + 18;

  /**
   * The feature id for the '<em><b>Child Creation Extenders</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__CHILD_CREATION_EXTENDERS = GEN_BASE_FEATURE_COUNT + 19;

  /**
   * The feature id for the '<em><b>Content Type Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__CONTENT_TYPE_IDENTIFIER = GEN_BASE_FEATURE_COUNT + 20;

  /**
   * The feature id for the '<em><b>File Extensions</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__FILE_EXTENSIONS = GEN_BASE_FEATURE_COUNT + 21;

  /**
   * The feature id for the '<em><b>Ecore Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__ECORE_PACKAGE = GEN_BASE_FEATURE_COUNT + 22;

  /**
   * The feature id for the '<em><b>Gen Model</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__GEN_MODEL = GEN_BASE_FEATURE_COUNT + 23;

  /**
   * The feature id for the '<em><b>Gen Enums</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__GEN_ENUMS = GEN_BASE_FEATURE_COUNT + 24;

  /**
   * The feature id for the '<em><b>Gen Data Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__GEN_DATA_TYPES = GEN_BASE_FEATURE_COUNT + 25;

  /**
   * The feature id for the '<em><b>Gen Classes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__GEN_CLASSES = GEN_BASE_FEATURE_COUNT + 26;

  /**
   * The feature id for the '<em><b>Nested Gen Packages</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__NESTED_GEN_PACKAGES = GEN_BASE_FEATURE_COUNT + 27;

  /**
   * The feature id for the '<em><b>Gen Classifiers</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE__GEN_CLASSIFIERS = GEN_BASE_FEATURE_COUNT + 28;

  /**
   * The number of structural features of the '<em>Gen Package</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PACKAGE_FEATURE_COUNT = GEN_BASE_FEATURE_COUNT + 29;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassifierImpl <em>Gen Classifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassifierImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenClassifier()
   * @generated
   */
  int GEN_CLASSIFIER = 7;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASSIFIER__GEN_ANNOTATIONS = GEN_BASE__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Gen Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASSIFIER__GEN_PACKAGE = GEN_BASE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Gen Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASSIFIER__GEN_TYPE_PARAMETERS = GEN_BASE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Gen Classifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASSIFIER_FEATURE_COUNT = GEN_BASE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl <em>Gen Class</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenClass()
   * @generated
   */
  int GEN_CLASS = 2;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS__GEN_ANNOTATIONS = GEN_CLASSIFIER__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Gen Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS__GEN_PACKAGE = GEN_CLASSIFIER__GEN_PACKAGE;

  /**
   * The feature id for the '<em><b>Gen Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS__GEN_TYPE_PARAMETERS = GEN_CLASSIFIER__GEN_TYPE_PARAMETERS;

  /**
   * The feature id for the '<em><b>Provider</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS__PROVIDER = GEN_CLASSIFIER_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Image</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS__IMAGE = GEN_CLASSIFIER_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Dynamic</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS__DYNAMIC = GEN_CLASSIFIER_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Ecore Class</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS__ECORE_CLASS = GEN_CLASSIFIER_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Gen Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS__GEN_FEATURES = GEN_CLASSIFIER_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Gen Operations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS__GEN_OPERATIONS = GEN_CLASSIFIER_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Label Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS__LABEL_FEATURE = GEN_CLASSIFIER_FEATURE_COUNT + 6;

  /**
   * The number of structural features of the '<em>Gen Class</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_CLASS_FEATURE_COUNT = GEN_CLASSIFIER_FEATURE_COUNT + 7;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypedElementImpl <em>Gen Typed Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypedElementImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenTypedElement()
   * @generated
   */
  int GEN_TYPED_ELEMENT = 11;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_TYPED_ELEMENT__GEN_ANNOTATIONS = GEN_BASE__GEN_ANNOTATIONS;

  /**
   * The number of structural features of the '<em>Gen Typed Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_TYPED_ELEMENT_FEATURE_COUNT = GEN_BASE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl <em>Gen Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenFeature()
   * @generated
   */
  int GEN_FEATURE = 3;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__GEN_ANNOTATIONS = GEN_TYPED_ELEMENT__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Property</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__PROPERTY = GEN_TYPED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Notify</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__NOTIFY = GEN_TYPED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Children</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__CHILDREN = GEN_TYPED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Create Child</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__CREATE_CHILD = GEN_TYPED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Property Category</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__PROPERTY_CATEGORY = GEN_TYPED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Property Filter Flags</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__PROPERTY_FILTER_FLAGS = GEN_TYPED_ELEMENT_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Property Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__PROPERTY_DESCRIPTION = GEN_TYPED_ELEMENT_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Property Multi Line</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__PROPERTY_MULTI_LINE = GEN_TYPED_ELEMENT_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Property Sort Choices</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__PROPERTY_SORT_CHOICES = GEN_TYPED_ELEMENT_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Gen Class</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__GEN_CLASS = GEN_TYPED_ELEMENT_FEATURE_COUNT + 9;

  /**
   * The feature id for the '<em><b>Ecore Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE__ECORE_FEATURE = GEN_TYPED_ELEMENT_FEATURE_COUNT + 10;

  /**
   * The number of structural features of the '<em>Gen Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_FEATURE_FEATURE_COUNT = GEN_TYPED_ELEMENT_FEATURE_COUNT + 11;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenDataTypeImpl <em>Gen Data Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenDataTypeImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenDataType()
   * @generated
   */
  int GEN_DATA_TYPE = 8;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_DATA_TYPE__GEN_ANNOTATIONS = GEN_CLASSIFIER__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Gen Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_DATA_TYPE__GEN_PACKAGE = GEN_CLASSIFIER__GEN_PACKAGE;

  /**
   * The feature id for the '<em><b>Gen Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_DATA_TYPE__GEN_TYPE_PARAMETERS = GEN_CLASSIFIER__GEN_TYPE_PARAMETERS;

  /**
   * The feature id for the '<em><b>Ecore Data Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_DATA_TYPE__ECORE_DATA_TYPE = GEN_CLASSIFIER_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Gen Data Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_DATA_TYPE_FEATURE_COUNT = GEN_CLASSIFIER_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumImpl <em>Gen Enum</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenEnum()
   * @generated
   */
  int GEN_ENUM = 5;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM__GEN_ANNOTATIONS = GEN_DATA_TYPE__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Gen Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM__GEN_PACKAGE = GEN_DATA_TYPE__GEN_PACKAGE;

  /**
   * The feature id for the '<em><b>Gen Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM__GEN_TYPE_PARAMETERS = GEN_DATA_TYPE__GEN_TYPE_PARAMETERS;

  /**
   * The feature id for the '<em><b>Ecore Data Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM__ECORE_DATA_TYPE = GEN_DATA_TYPE__ECORE_DATA_TYPE;

  /**
   * The feature id for the '<em><b>Type Safe Enum Compatible</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM__TYPE_SAFE_ENUM_COMPATIBLE = GEN_DATA_TYPE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Ecore Enum</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM__ECORE_ENUM = GEN_DATA_TYPE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Gen Enum Literals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM__GEN_ENUM_LITERALS = GEN_DATA_TYPE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Gen Enum</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM_FEATURE_COUNT = GEN_DATA_TYPE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumLiteralImpl <em>Gen Enum Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumLiteralImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenEnumLiteral()
   * @generated
   */
  int GEN_ENUM_LITERAL = 6;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM_LITERAL__GEN_ANNOTATIONS = GEN_BASE__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Gen Enum</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM_LITERAL__GEN_ENUM = GEN_BASE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Ecore Enum Literal</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL = GEN_BASE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Gen Enum Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ENUM_LITERAL_FEATURE_COUNT = GEN_BASE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenOperationImpl <em>Gen Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenOperationImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenOperation()
   * @generated
   */
  int GEN_OPERATION = 9;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_OPERATION__GEN_ANNOTATIONS = GEN_TYPED_ELEMENT__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Gen Class</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_OPERATION__GEN_CLASS = GEN_TYPED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Ecore Operation</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_OPERATION__ECORE_OPERATION = GEN_TYPED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Gen Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_OPERATION__GEN_PARAMETERS = GEN_TYPED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Gen Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_OPERATION__GEN_TYPE_PARAMETERS = GEN_TYPED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Gen Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_OPERATION_FEATURE_COUNT = GEN_TYPED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenParameterImpl <em>Gen Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenParameterImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenParameter()
   * @generated
   */
  int GEN_PARAMETER = 10;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PARAMETER__GEN_ANNOTATIONS = GEN_TYPED_ELEMENT__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Gen Operation</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PARAMETER__GEN_OPERATION = GEN_TYPED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Ecore Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PARAMETER__ECORE_PARAMETER = GEN_TYPED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Gen Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_PARAMETER_FEATURE_COUNT = GEN_TYPED_ELEMENT_FEATURE_COUNT + 2;


  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenAnnotationImpl <em>Gen Annotation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenAnnotationImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenAnnotation()
   * @generated
   */
  int GEN_ANNOTATION = 12;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ANNOTATION__GEN_ANNOTATIONS = GEN_BASE__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ANNOTATION__SOURCE = GEN_BASE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Details</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ANNOTATION__DETAILS = GEN_BASE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Gen Base</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ANNOTATION__GEN_BASE = GEN_BASE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>References</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ANNOTATION__REFERENCES = GEN_BASE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Contents</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ANNOTATION__CONTENTS = GEN_BASE_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Gen Annotation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_ANNOTATION_FEATURE_COUNT = GEN_BASE_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypeParameterImpl <em>Gen Type Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypeParameterImpl
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenTypeParameter()
   * @generated
   */
  int GEN_TYPE_PARAMETER = 13;

  /**
   * The feature id for the '<em><b>Gen Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_TYPE_PARAMETER__GEN_ANNOTATIONS = GEN_BASE__GEN_ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Ecore Type Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER = GEN_BASE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Gen Type Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GEN_TYPE_PARAMETER_FEATURE_COUNT = GEN_BASE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind <em>Gen Provider Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenProviderKind()
   * @generated
   */
  int GEN_PROVIDER_KIND = 14;

  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind <em>Gen Property Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenPropertyKind()
   * @generated
   */
  int GEN_PROPERTY_KIND = 15;


  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind <em>Gen Resource Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenResourceKind()
   * @generated
   */
  int GEN_RESOURCE_KIND = 16;


  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind <em>Gen Delegation Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenDelegationKind()
   * @generated
   */
  int GEN_DELEGATION_KIND = 17;


  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel <em>Gen JDK Level</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenJDKLevel()
   * @generated
   */
  int GEN_JDK_LEVEL = 18;


  /**
   * The meta object id for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion <em>Gen Runtime Version</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion
   * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenRuntimeVersion()
   * @generated
   */
  int GEN_RUNTIME_VERSION = 19;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel <em>Gen Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Model</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel
   * @generated
   */
  EClass getGenModel();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getCopyrightText <em>Copyright Text</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Copyright Text</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getCopyrightText()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_CopyrightText();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelDirectory <em>Model Directory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Model Directory</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelDirectory()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ModelDirectory();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationCommands <em>Creation Commands</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Creation Commands</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationCommands()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_CreationCommands();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationIcons <em>Creation Icons</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Creation Icons</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationIcons()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_CreationIcons();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditDirectory <em>Edit Directory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Edit Directory</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditDirectory()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_EditDirectory();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationSubmenus <em>Creation Submenus</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Creation Submenus</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCreationSubmenus()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_CreationSubmenus();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorDirectory <em>Editor Directory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Editor Directory</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorDirectory()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_EditorDirectory();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginID <em>Model Plugin ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Model Plugin ID</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginID()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ModelPluginID();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTemplateDirectory <em>Template Directory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Template Directory</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTemplateDirectory()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_TemplateDirectory();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeJar <em>Runtime Jar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Runtime Jar</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeJar()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_RuntimeJar();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getForeignModel <em>Foreign Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Foreign Model</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getForeignModel()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ForeignModel();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isDynamicTemplates <em>Dynamic Templates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Dynamic Templates</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isDynamicTemplates()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_DynamicTemplates();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRedirection <em>Redirection</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Redirection</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRedirection()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_Redirection();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isForceOverwrite <em>Force Overwrite</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Force Overwrite</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isForceOverwrite()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ForceOverwrite();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getNonExternalizedStringTag <em>Non Externalized String Tag</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Non Externalized String Tag</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getNonExternalizedStringTag()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_NonExternalizedStringTag();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelName <em>Model Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Model Name</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelName()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ModelName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginClass <em>Model Plugin Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Model Plugin Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginClass()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ModelPluginClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginClass <em>Edit Plugin Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Edit Plugin Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginClass()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_EditPluginClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginClass <em>Editor Plugin Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Editor Plugin Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginClass()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_EditorPluginClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isUpdateClasspath <em>Update Classpath</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Update Classpath</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isUpdateClasspath()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_UpdateClasspath();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isGenerateSchema <em>Generate Schema</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Generate Schema</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isGenerateSchema()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_GenerateSchema();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isNonNLSMarkers <em>Non NLS Markers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Non NLS Markers</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isNonNLSMarkers()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_NonNLSMarkers();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getStaticPackages <em>Static Packages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Static Packages</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getStaticPackages()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_StaticPackages();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginVariables <em>Model Plugin Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Model Plugin Variables</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getModelPluginVariables()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ModelPluginVariables();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsInterface <em>Root Extends Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Root Extends Interface</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsInterface()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_RootExtendsInterface();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsClass <em>Root Extends Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Root Extends Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootExtendsClass()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_RootExtendsClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootImplementsInterface <em>Root Implements Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Root Implements Interface</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRootImplementsInterface()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_RootImplementsInterface();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFTypes <em>Suppress EMF Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suppress EMF Types</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFTypes()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_SuppressEMFTypes();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFMetaData <em>Suppress EMF Meta Data</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suppress EMF Meta Data</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFMetaData()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_SuppressEMFMetaData();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFModelTags <em>Suppress EMF Model Tags</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suppress EMF Model Tags</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressEMFModelTags()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_SuppressEMFModelTags();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressInterfaces <em>Suppress Interfaces</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suppress Interfaces</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressInterfaces()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_SuppressInterfaces();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInterface <em>Feature Map Wrapper Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Feature Map Wrapper Interface</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInterface()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_FeatureMapWrapperInterface();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInternalInterface <em>Feature Map Wrapper Internal Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Feature Map Wrapper Internal Interface</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperInternalInterface()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_FeatureMapWrapperInternalInterface();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperClass <em>Feature Map Wrapper Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Feature Map Wrapper Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureMapWrapperClass()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_FeatureMapWrapperClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeCompatibility <em>Runtime Compatibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Runtime Compatibility</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRuntimeCompatibility()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_RuntimeCompatibility();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRichClientPlatform <em>Rich Client Platform</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Rich Client Platform</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isRichClientPlatform()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_RichClientPlatform();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isReflectiveDelegation <em>Reflective Delegation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Reflective Delegation</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isReflectiveDelegation()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ReflectiveDelegation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCodeFormatting <em>Code Formatting</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Code Formatting</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCodeFormatting()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_CodeFormatting();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsDirectory <em>Tests Directory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tests Directory</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsDirectory()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_TestsDirectory();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestSuiteClass <em>Test Suite Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Test Suite Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestSuiteClass()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_TestSuiteClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getBooleanFlagsField <em>Boolean Flags Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Boolean Flags Field</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getBooleanFlagsField()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_BooleanFlagsField();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getBooleanFlagsReservedBits <em>Boolean Flags Reserved Bits</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Boolean Flags Reserved Bits</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getBooleanFlagsReservedBits()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_BooleanFlagsReservedBits();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getImporterID <em>Importer ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Importer ID</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getImporterID()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ImporterID();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isBundleManifest <em>Bundle Manifest</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Bundle Manifest</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isBundleManifest()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_BundleManifest();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureDelegation <em>Feature Delegation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Feature Delegation</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFeatureDelegation()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_FeatureDelegation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isContainmentProxies <em>Containment Proxies</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Containment Proxies</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isContainmentProxies()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ContainmentProxies();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isMinimalReflectiveMethods <em>Minimal Reflective Methods</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Minimal Reflective Methods</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isMinimalReflectiveMethods()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_MinimalReflectiveMethods();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressContainment <em>Suppress Containment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suppress Containment</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressContainment()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_SuppressContainment();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressNotification <em>Suppress Notification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suppress Notification</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressNotification()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_SuppressNotification();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isArrayAccessors <em>Array Accessors</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Array Accessors</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isArrayAccessors()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ArrayAccessors();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressUnsettable <em>Suppress Unsettable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suppress Unsettable</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressUnsettable()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_SuppressUnsettable();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFacadeHelperClass <em>Facade Helper Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Facade Helper Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getFacadeHelperClass()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_FacadeHelperClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getComplianceLevel <em>Compliance Level</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Compliance Level</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getComplianceLevel()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ComplianceLevel();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressGenModelAnnotations <em>Suppress Gen Model Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suppress Gen Model Annotations</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isSuppressGenModelAnnotations()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_SuppressGenModelAnnotations();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCopyrightFields <em>Copyright Fields</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Copyright Fields</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isCopyrightFields()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_CopyrightFields();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isBinaryCompatibleReflectiveMethods <em>Binary Compatible Reflective Methods</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Binary Compatible Reflective Methods</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isBinaryCompatibleReflectiveMethods()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_BinaryCompatibleReflectiveMethods();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isPublicConstructors <em>Public Constructors</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Public Constructors</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isPublicConstructors()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_PublicConstructors();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTemplatePluginVariables <em>Template Plugin Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Template Plugin Variables</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTemplatePluginVariables()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_TemplatePluginVariables();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getProviderRootExtendsClass <em>Provider Root Extends Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Provider Root Extends Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getProviderRootExtendsClass()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ProviderRootExtendsClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginID <em>Edit Plugin ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Edit Plugin ID</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginID()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_EditPluginID();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginVariables <em>Edit Plugin Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Edit Plugin Variables</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditPluginVariables()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_EditPluginVariables();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginID <em>Editor Plugin ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Editor Plugin ID</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginID()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_EditorPluginID();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginVariables <em>Editor Plugin Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Editor Plugin Variables</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getEditorPluginVariables()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_EditorPluginVariables();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsPluginID <em>Tests Plugin ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tests Plugin ID</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsPluginID()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_TestsPluginID();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsPluginVariables <em>Tests Plugin Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Tests Plugin Variables</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getTestsPluginVariables()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_TestsPluginVariables();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isOptimizedHasChildren <em>Optimized Has Children</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optimized Has Children</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isOptimizedHasChildren()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_OptimizedHasChildren();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isTableProviders <em>Table Providers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Table Providers</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isTableProviders()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_TableProviders();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isColorProviders <em>Color Providers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Color Providers</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isColorProviders()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ColorProviders();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isFontProviders <em>Font Providers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Font Providers</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isFontProviders()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_FontProviders();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRuntimeVersion <em>Runtime Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Runtime Version</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getRuntimeVersion()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_RuntimeVersion();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getLanguage <em>Language</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Language</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getLanguage()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_Language();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isPackedEnums <em>Packed Enums</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Packed Enums</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isPackedEnums()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_PackedEnums();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getGenPackages <em>Gen Packages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Packages</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getGenPackages()
   * @see #getGenModel()
   * @generated
   */
  EReference getGenModel_GenPackages();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getUsedGenPackages <em>Used Gen Packages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Used Gen Packages</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getUsedGenPackages()
   * @see #getGenModel()
   * @generated
   */
  EReference getGenModel_UsedGenPackages();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getInterfaceNamePattern <em>Interface Name Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Interface Name Pattern</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getInterfaceNamePattern()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_InterfaceNamePattern();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getClassNamePattern <em>Class Name Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Class Name Pattern</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getClassNamePattern()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_ClassNamePattern();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#isOperationReflection <em>Operation Reflection</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operation Reflection</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#isOperationReflection()
   * @see #getGenModel()
   * @generated
   */
  EAttribute getGenModel_OperationReflection();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage <em>Gen Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Package</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage
   * @generated
   */
  EClass getGenPackage();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getPrefix <em>Prefix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Prefix</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getPrefix()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_Prefix();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getBasePackage <em>Base Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Base Package</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getBasePackage()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_BasePackage();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getResource <em>Resource</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Resource</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getResource()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_Resource();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isDisposableProviderFactory <em>Disposable Provider Factory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Disposable Provider Factory</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isDisposableProviderFactory()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_DisposableProviderFactory();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isAdapterFactory <em>Adapter Factory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Adapter Factory</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isAdapterFactory()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_AdapterFactory();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isLoadInitialization <em>Load Initialization</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Load Initialization</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isLoadInitialization()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_LoadInitialization();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getInterfacePackageSuffix <em>Interface Package Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Interface Package Suffix</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getInterfacePackageSuffix()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_InterfacePackageSuffix();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getMetaDataPackageSuffix <em>Meta Data Package Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Meta Data Package Suffix</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getMetaDataPackageSuffix()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_MetaDataPackageSuffix();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getClassPackageSuffix <em>Class Package Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Class Package Suffix</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getClassPackageSuffix()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_ClassPackageSuffix();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getUtilityPackageSuffix <em>Utility Package Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Utility Package Suffix</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getUtilityPackageSuffix()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_UtilityPackageSuffix();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getProviderPackageSuffix <em>Provider Package Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Provider Package Suffix</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getProviderPackageSuffix()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_ProviderPackageSuffix();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getPresentationPackageSuffix <em>Presentation Package Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Presentation Package Suffix</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getPresentationPackageSuffix()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_PresentationPackageSuffix();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getTestsPackageSuffix <em>Tests Package Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tests Package Suffix</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getTestsPackageSuffix()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_TestsPackageSuffix();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isGenerateExampleClass <em>Generate Example Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Generate Example Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isGenerateExampleClass()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_GenerateExampleClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isLiteralsInterface <em>Literals Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Literals Interface</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isLiteralsInterface()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_LiteralsInterface();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isDataTypeConverters <em>Data Type Converters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Type Converters</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isDataTypeConverters()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_DataTypeConverters();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isMultipleEditorPages <em>Multiple Editor Pages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Multiple Editor Pages</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isMultipleEditorPages()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_MultipleEditorPages();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isGenerateModelWizard <em>Generate Model Wizard</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Generate Model Wizard</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isGenerateModelWizard()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_GenerateModelWizard();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isExtensibleProviderFactory <em>Extensible Provider Factory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Extensible Provider Factory</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isExtensibleProviderFactory()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_ExtensibleProviderFactory();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isChildCreationExtenders <em>Child Creation Extenders</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Child Creation Extenders</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isChildCreationExtenders()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_ChildCreationExtenders();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getContentTypeIdentifier <em>Content Type Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Content Type Identifier</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getContentTypeIdentifier()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_ContentTypeIdentifier();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getFileExtensions <em>File Extensions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>File Extensions</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getFileExtensions()
   * @see #getGenPackage()
   * @generated
   */
  EAttribute getGenPackage_FileExtensions();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getEcorePackage <em>Ecore Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ecore Package</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getEcorePackage()
   * @see #getGenPackage()
   * @generated
   */
  EReference getGenPackage_EcorePackage();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenModel <em>Gen Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Gen Model</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenModel()
   * @see #getGenPackage()
   * @generated
   */
  EReference getGenPackage_GenModel();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenEnums <em>Gen Enums</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Enums</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenEnums()
   * @see #getGenPackage()
   * @generated
   */
  EReference getGenPackage_GenEnums();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenDataTypes <em>Gen Data Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Data Types</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenDataTypes()
   * @see #getGenPackage()
   * @generated
   */
  EReference getGenPackage_GenDataTypes();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenClasses <em>Gen Classes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Classes</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenClasses()
   * @see #getGenPackage()
   * @generated
   */
  EReference getGenPackage_GenClasses();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getNestedGenPackages <em>Nested Gen Packages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Nested Gen Packages</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getNestedGenPackages()
   * @see #getGenPackage()
   * @generated
   */
  EReference getGenPackage_NestedGenPackages();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenClassifiers <em>Gen Classifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Gen Classifiers</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenClassifiers()
   * @see #getGenPackage()
   * @generated
   */
  EReference getGenPackage_GenClassifiers();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass <em>Gen Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClass
   * @generated
   */
  EClass getGenClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getProvider <em>Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Provider</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClass#getProvider()
   * @see #getGenClass()
   * @generated
   */
  EAttribute getGenClass_Provider();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#isImage <em>Image</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Image</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClass#isImage()
   * @see #getGenClass()
   * @generated
   */
  EAttribute getGenClass_Image();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#isDynamic <em>Dynamic</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Dynamic</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClass#isDynamic()
   * @see #getGenClass()
   * @generated
   */
  EAttribute getGenClass_Dynamic();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getEcoreClass <em>Ecore Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ecore Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClass#getEcoreClass()
   * @see #getGenClass()
   * @generated
   */
  EReference getGenClass_EcoreClass();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenFeatures <em>Gen Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Features</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenFeatures()
   * @see #getGenClass()
   * @generated
   */
  EReference getGenClass_GenFeatures();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenOperations <em>Gen Operations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Operations</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenOperations()
   * @see #getGenClass()
   * @generated
   */
  EReference getGenClass_GenOperations();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getLabelFeature <em>Label Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Label Feature</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClass#getLabelFeature()
   * @see #getGenClass()
   * @generated
   */
  EReference getGenClass_LabelFeature();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature <em>Gen Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Feature</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature
   * @generated
   */
  EClass getGenFeature();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getProperty <em>Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Property</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getProperty()
   * @see #getGenFeature()
   * @generated
   */
  EAttribute getGenFeature_Property();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isNotify <em>Notify</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Notify</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isNotify()
   * @see #getGenFeature()
   * @generated
   */
  EAttribute getGenFeature_Notify();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isChildren <em>Children</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Children</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isChildren()
   * @see #getGenFeature()
   * @generated
   */
  EAttribute getGenFeature_Children();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isCreateChild <em>Create Child</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Create Child</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isCreateChild()
   * @see #getGenFeature()
   * @generated
   */
  EAttribute getGenFeature_CreateChild();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyCategory <em>Property Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Property Category</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyCategory()
   * @see #getGenFeature()
   * @generated
   */
  EAttribute getGenFeature_PropertyCategory();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyFilterFlags <em>Property Filter Flags</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Property Filter Flags</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyFilterFlags()
   * @see #getGenFeature()
   * @generated
   */
  EAttribute getGenFeature_PropertyFilterFlags();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyDescription <em>Property Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Property Description</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyDescription()
   * @see #getGenFeature()
   * @generated
   */
  EAttribute getGenFeature_PropertyDescription();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isPropertyMultiLine <em>Property Multi Line</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Property Multi Line</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isPropertyMultiLine()
   * @see #getGenFeature()
   * @generated
   */
  EAttribute getGenFeature_PropertyMultiLine();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isPropertySortChoices <em>Property Sort Choices</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Property Sort Choices</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isPropertySortChoices()
   * @see #getGenFeature()
   * @generated
   */
  EAttribute getGenFeature_PropertySortChoices();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getGenClass <em>Gen Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Gen Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getGenClass()
   * @see #getGenFeature()
   * @generated
   */
  EReference getGenFeature_GenClass();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getEcoreFeature <em>Ecore Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ecore Feature</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getEcoreFeature()
   * @see #getGenFeature()
   * @generated
   */
  EReference getGenFeature_EcoreFeature();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenBase <em>Gen Base</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Base</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenBase
   * @generated
   */
  EClass getGenBase();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenBase#getGenAnnotations <em>Gen Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Annotations</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenBase#getGenAnnotations()
   * @see #getGenBase()
   * @generated
   */
  EReference getGenBase_GenAnnotations();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum <em>Gen Enum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Enum</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenEnum
   * @generated
   */
  EClass getGenEnum();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#isTypeSafeEnumCompatible <em>Type Safe Enum Compatible</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type Safe Enum Compatible</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenEnum#isTypeSafeEnumCompatible()
   * @see #getGenEnum()
   * @generated
   */
  EAttribute getGenEnum_TypeSafeEnumCompatible();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getEcoreEnum <em>Ecore Enum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ecore Enum</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getEcoreEnum()
   * @see #getGenEnum()
   * @generated
   */
  EReference getGenEnum_EcoreEnum();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getGenEnumLiterals <em>Gen Enum Literals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Enum Literals</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getGenEnumLiterals()
   * @see #getGenEnum()
   * @generated
   */
  EReference getGenEnum_GenEnumLiterals();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral <em>Gen Enum Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Enum Literal</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral
   * @generated
   */
  EClass getGenEnumLiteral();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getGenEnum <em>Gen Enum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Gen Enum</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getGenEnum()
   * @see #getGenEnumLiteral()
   * @generated
   */
  EReference getGenEnumLiteral_GenEnum();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getEcoreEnumLiteral <em>Ecore Enum Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ecore Enum Literal</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getEcoreEnumLiteral()
   * @see #getGenEnumLiteral()
   * @generated
   */
  EReference getGenEnumLiteral_EcoreEnumLiteral();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier <em>Gen Classifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Classifier</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClassifier
   * @generated
   */
  EClass getGenClassifier();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenPackage <em>Gen Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Gen Package</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenPackage()
   * @see #getGenClassifier()
   * @generated
   */
  EReference getGenClassifier_GenPackage();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenTypeParameters <em>Gen Type Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Type Parameters</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenTypeParameters()
   * @see #getGenClassifier()
   * @generated
   */
  EReference getGenClassifier_GenTypeParameters();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenDataType <em>Gen Data Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Data Type</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenDataType
   * @generated
   */
  EClass getGenDataType();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenDataType#getEcoreDataType <em>Ecore Data Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ecore Data Type</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenDataType#getEcoreDataType()
   * @see #getGenDataType()
   * @generated
   */
  EReference getGenDataType_EcoreDataType();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation <em>Gen Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Operation</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenOperation
   * @generated
   */
  EClass getGenOperation();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenClass <em>Gen Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Gen Class</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenClass()
   * @see #getGenOperation()
   * @generated
   */
  EReference getGenOperation_GenClass();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getEcoreOperation <em>Ecore Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ecore Operation</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getEcoreOperation()
   * @see #getGenOperation()
   * @generated
   */
  EReference getGenOperation_EcoreOperation();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenParameters <em>Gen Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Parameters</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenParameters()
   * @see #getGenOperation()
   * @generated
   */
  EReference getGenOperation_GenParameters();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenTypeParameters <em>Gen Type Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Gen Type Parameters</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenTypeParameters()
   * @see #getGenOperation()
   * @generated
   */
  EReference getGenOperation_GenTypeParameters();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenParameter <em>Gen Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Parameter</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenParameter
   * @generated
   */
  EClass getGenParameter();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getGenOperation <em>Gen Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Gen Operation</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getGenOperation()
   * @see #getGenParameter()
   * @generated
   */
  EReference getGenParameter_GenOperation();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getEcoreParameter <em>Ecore Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ecore Parameter</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getEcoreParameter()
   * @see #getGenParameter()
   * @generated
   */
  EReference getGenParameter_EcoreParameter();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenTypedElement <em>Gen Typed Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Typed Element</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenTypedElement
   * @generated
   */
  EClass getGenTypedElement();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation <em>Gen Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Annotation</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation
   * @generated
   */
  EClass getGenAnnotation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getSource()
   * @see #getGenAnnotation()
   * @generated
   */
  EAttribute getGenAnnotation_Source();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getDetails <em>Details</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Details</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getDetails()
   * @see #getGenAnnotation()
   * @generated
   */
  EReference getGenAnnotation_Details();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getGenBase <em>Gen Base</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Gen Base</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getGenBase()
   * @see #getGenAnnotation()
   * @generated
   */
  EReference getGenAnnotation_GenBase();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getReferences <em>References</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>References</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getReferences()
   * @see #getGenAnnotation()
   * @generated
   */
  EReference getGenAnnotation_References();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getContents <em>Contents</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Contents</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getContents()
   * @see #getGenAnnotation()
   * @generated
   */
  EReference getGenAnnotation_Contents();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter <em>Gen Type Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Gen Type Parameter</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter
   * @generated
   */
  EClass getGenTypeParameter();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter#getEcoreTypeParameter <em>Ecore Type Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ecore Type Parameter</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter#getEcoreTypeParameter()
   * @see #getGenTypeParameter()
   * @generated
   */
  EReference getGenTypeParameter_EcoreTypeParameter();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind <em>Gen Provider Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Gen Provider Kind</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind
   * @generated
   */
  EEnum getGenProviderKind();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind <em>Gen Property Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Gen Property Kind</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind
   * @generated
   */
  EEnum getGenPropertyKind();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind <em>Gen Resource Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Gen Resource Kind</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind
   * @generated
   */
  EEnum getGenResourceKind();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind <em>Gen Delegation Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Gen Delegation Kind</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind
   * @generated
   */
  EEnum getGenDelegationKind();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel <em>Gen JDK Level</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Gen JDK Level</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel
   * @generated
   */
  EEnum getGenJDKLevel();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion <em>Gen Runtime Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Gen Runtime Version</em>'.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion
   * @generated
   */
  EEnum getGenRuntimeVersion();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  GenModelFactory getGenModelFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals 
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl <em>Gen Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenModel()
     * @generated
     */
    EClass GEN_MODEL = eINSTANCE.getGenModel();

    /**
     * The meta object literal for the '<em><b>Copyright Text</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__COPYRIGHT_TEXT = eINSTANCE.getGenModel_CopyrightText();

    /**
     * The meta object literal for the '<em><b>Model Directory</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__MODEL_DIRECTORY = eINSTANCE.getGenModel_ModelDirectory();

    /**
     * The meta object literal for the '<em><b>Creation Commands</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__CREATION_COMMANDS = eINSTANCE.getGenModel_CreationCommands();

    /**
     * The meta object literal for the '<em><b>Creation Icons</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__CREATION_ICONS = eINSTANCE.getGenModel_CreationIcons();

    /**
     * The meta object literal for the '<em><b>Edit Directory</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__EDIT_DIRECTORY = eINSTANCE.getGenModel_EditDirectory();

    /**
     * The meta object literal for the '<em><b>Creation Submenus</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__CREATION_SUBMENUS = eINSTANCE.getGenModel_CreationSubmenus();

    /**
     * The meta object literal for the '<em><b>Editor Directory</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__EDITOR_DIRECTORY = eINSTANCE.getGenModel_EditorDirectory();

    /**
     * The meta object literal for the '<em><b>Model Plugin ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__MODEL_PLUGIN_ID = eINSTANCE.getGenModel_ModelPluginID();

    /**
     * The meta object literal for the '<em><b>Template Directory</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__TEMPLATE_DIRECTORY = eINSTANCE.getGenModel_TemplateDirectory();

    /**
     * The meta object literal for the '<em><b>Runtime Jar</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__RUNTIME_JAR = eINSTANCE.getGenModel_RuntimeJar();

    /**
     * The meta object literal for the '<em><b>Foreign Model</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__FOREIGN_MODEL = eINSTANCE.getGenModel_ForeignModel();

    /**
     * The meta object literal for the '<em><b>Dynamic Templates</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__DYNAMIC_TEMPLATES = eINSTANCE.getGenModel_DynamicTemplates();

    /**
     * The meta object literal for the '<em><b>Redirection</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__REDIRECTION = eINSTANCE.getGenModel_Redirection();

    /**
     * The meta object literal for the '<em><b>Force Overwrite</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__FORCE_OVERWRITE = eINSTANCE.getGenModel_ForceOverwrite();

    /**
     * The meta object literal for the '<em><b>Non Externalized String Tag</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__NON_EXTERNALIZED_STRING_TAG = eINSTANCE.getGenModel_NonExternalizedStringTag();

    /**
     * The meta object literal for the '<em><b>Model Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__MODEL_NAME = eINSTANCE.getGenModel_ModelName();

    /**
     * The meta object literal for the '<em><b>Model Plugin Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__MODEL_PLUGIN_CLASS = eINSTANCE.getGenModel_ModelPluginClass();

    /**
     * The meta object literal for the '<em><b>Edit Plugin Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__EDIT_PLUGIN_CLASS = eINSTANCE.getGenModel_EditPluginClass();

    /**
     * The meta object literal for the '<em><b>Editor Plugin Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__EDITOR_PLUGIN_CLASS = eINSTANCE.getGenModel_EditorPluginClass();

    /**
     * The meta object literal for the '<em><b>Update Classpath</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__UPDATE_CLASSPATH = eINSTANCE.getGenModel_UpdateClasspath();

    /**
     * The meta object literal for the '<em><b>Generate Schema</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__GENERATE_SCHEMA = eINSTANCE.getGenModel_GenerateSchema();

    /**
     * The meta object literal for the '<em><b>Non NLS Markers</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__NON_NLS_MARKERS = eINSTANCE.getGenModel_NonNLSMarkers();

    /**
     * The meta object literal for the '<em><b>Static Packages</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__STATIC_PACKAGES = eINSTANCE.getGenModel_StaticPackages();

    /**
     * The meta object literal for the '<em><b>Model Plugin Variables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__MODEL_PLUGIN_VARIABLES = eINSTANCE.getGenModel_ModelPluginVariables();

    /**
     * The meta object literal for the '<em><b>Root Extends Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__ROOT_EXTENDS_INTERFACE = eINSTANCE.getGenModel_RootExtendsInterface();

    /**
     * The meta object literal for the '<em><b>Root Extends Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__ROOT_EXTENDS_CLASS = eINSTANCE.getGenModel_RootExtendsClass();

    /**
     * The meta object literal for the '<em><b>Root Implements Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__ROOT_IMPLEMENTS_INTERFACE = eINSTANCE.getGenModel_RootImplementsInterface();

    /**
     * The meta object literal for the '<em><b>Suppress EMF Types</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__SUPPRESS_EMF_TYPES = eINSTANCE.getGenModel_SuppressEMFTypes();

    /**
     * The meta object literal for the '<em><b>Suppress EMF Meta Data</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__SUPPRESS_EMF_META_DATA = eINSTANCE.getGenModel_SuppressEMFMetaData();

    /**
     * The meta object literal for the '<em><b>Suppress EMF Model Tags</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__SUPPRESS_EMF_MODEL_TAGS = eINSTANCE.getGenModel_SuppressEMFModelTags();

    /**
     * The meta object literal for the '<em><b>Suppress Interfaces</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__SUPPRESS_INTERFACES = eINSTANCE.getGenModel_SuppressInterfaces();

    /**
     * The meta object literal for the '<em><b>Feature Map Wrapper Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__FEATURE_MAP_WRAPPER_INTERFACE = eINSTANCE.getGenModel_FeatureMapWrapperInterface();

    /**
     * The meta object literal for the '<em><b>Feature Map Wrapper Internal Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__FEATURE_MAP_WRAPPER_INTERNAL_INTERFACE = eINSTANCE.getGenModel_FeatureMapWrapperInternalInterface();

    /**
     * The meta object literal for the '<em><b>Feature Map Wrapper Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__FEATURE_MAP_WRAPPER_CLASS = eINSTANCE.getGenModel_FeatureMapWrapperClass();

    /**
     * The meta object literal for the '<em><b>Runtime Compatibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__RUNTIME_COMPATIBILITY = eINSTANCE.getGenModel_RuntimeCompatibility();

    /**
     * The meta object literal for the '<em><b>Rich Client Platform</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__RICH_CLIENT_PLATFORM = eINSTANCE.getGenModel_RichClientPlatform();

    /**
     * The meta object literal for the '<em><b>Reflective Delegation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__REFLECTIVE_DELEGATION = eINSTANCE.getGenModel_ReflectiveDelegation();

    /**
     * The meta object literal for the '<em><b>Code Formatting</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__CODE_FORMATTING = eINSTANCE.getGenModel_CodeFormatting();

    /**
     * The meta object literal for the '<em><b>Tests Directory</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__TESTS_DIRECTORY = eINSTANCE.getGenModel_TestsDirectory();

    /**
     * The meta object literal for the '<em><b>Test Suite Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__TEST_SUITE_CLASS = eINSTANCE.getGenModel_TestSuiteClass();

    /**
     * The meta object literal for the '<em><b>Boolean Flags Field</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__BOOLEAN_FLAGS_FIELD = eINSTANCE.getGenModel_BooleanFlagsField();

    /**
     * The meta object literal for the '<em><b>Boolean Flags Reserved Bits</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__BOOLEAN_FLAGS_RESERVED_BITS = eINSTANCE.getGenModel_BooleanFlagsReservedBits();

    /**
     * The meta object literal for the '<em><b>Importer ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__IMPORTER_ID = eINSTANCE.getGenModel_ImporterID();

    /**
     * The meta object literal for the '<em><b>Bundle Manifest</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__BUNDLE_MANIFEST = eINSTANCE.getGenModel_BundleManifest();

    /**
     * The meta object literal for the '<em><b>Feature Delegation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__FEATURE_DELEGATION = eINSTANCE.getGenModel_FeatureDelegation();

    /**
     * The meta object literal for the '<em><b>Containment Proxies</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__CONTAINMENT_PROXIES = eINSTANCE.getGenModel_ContainmentProxies();

    /**
     * The meta object literal for the '<em><b>Minimal Reflective Methods</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__MINIMAL_REFLECTIVE_METHODS = eINSTANCE.getGenModel_MinimalReflectiveMethods();

    /**
     * The meta object literal for the '<em><b>Suppress Containment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__SUPPRESS_CONTAINMENT = eINSTANCE.getGenModel_SuppressContainment();

    /**
     * The meta object literal for the '<em><b>Suppress Notification</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__SUPPRESS_NOTIFICATION = eINSTANCE.getGenModel_SuppressNotification();

    /**
     * The meta object literal for the '<em><b>Array Accessors</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__ARRAY_ACCESSORS = eINSTANCE.getGenModel_ArrayAccessors();

    /**
     * The meta object literal for the '<em><b>Suppress Unsettable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__SUPPRESS_UNSETTABLE = eINSTANCE.getGenModel_SuppressUnsettable();

    /**
     * The meta object literal for the '<em><b>Facade Helper Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__FACADE_HELPER_CLASS = eINSTANCE.getGenModel_FacadeHelperClass();

    /**
     * The meta object literal for the '<em><b>Compliance Level</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__COMPLIANCE_LEVEL = eINSTANCE.getGenModel_ComplianceLevel();

    /**
     * The meta object literal for the '<em><b>Suppress Gen Model Annotations</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__SUPPRESS_GEN_MODEL_ANNOTATIONS = eINSTANCE.getGenModel_SuppressGenModelAnnotations();

    /**
     * The meta object literal for the '<em><b>Copyright Fields</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__COPYRIGHT_FIELDS = eINSTANCE.getGenModel_CopyrightFields();

    /**
     * The meta object literal for the '<em><b>Binary Compatible Reflective Methods</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__BINARY_COMPATIBLE_REFLECTIVE_METHODS = eINSTANCE.getGenModel_BinaryCompatibleReflectiveMethods();

    /**
     * The meta object literal for the '<em><b>Public Constructors</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__PUBLIC_CONSTRUCTORS = eINSTANCE.getGenModel_PublicConstructors();

    /**
     * The meta object literal for the '<em><b>Template Plugin Variables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__TEMPLATE_PLUGIN_VARIABLES = eINSTANCE.getGenModel_TemplatePluginVariables();

    /**
     * The meta object literal for the '<em><b>Provider Root Extends Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__PROVIDER_ROOT_EXTENDS_CLASS = eINSTANCE.getGenModel_ProviderRootExtendsClass();

    /**
     * The meta object literal for the '<em><b>Edit Plugin ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__EDIT_PLUGIN_ID = eINSTANCE.getGenModel_EditPluginID();

    /**
     * The meta object literal for the '<em><b>Edit Plugin Variables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__EDIT_PLUGIN_VARIABLES = eINSTANCE.getGenModel_EditPluginVariables();

    /**
     * The meta object literal for the '<em><b>Editor Plugin ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__EDITOR_PLUGIN_ID = eINSTANCE.getGenModel_EditorPluginID();

    /**
     * The meta object literal for the '<em><b>Editor Plugin Variables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__EDITOR_PLUGIN_VARIABLES = eINSTANCE.getGenModel_EditorPluginVariables();

    /**
     * The meta object literal for the '<em><b>Tests Plugin ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__TESTS_PLUGIN_ID = eINSTANCE.getGenModel_TestsPluginID();

    /**
     * The meta object literal for the '<em><b>Tests Plugin Variables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__TESTS_PLUGIN_VARIABLES = eINSTANCE.getGenModel_TestsPluginVariables();

    /**
     * The meta object literal for the '<em><b>Optimized Has Children</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__OPTIMIZED_HAS_CHILDREN = eINSTANCE.getGenModel_OptimizedHasChildren();

    /**
     * The meta object literal for the '<em><b>Table Providers</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__TABLE_PROVIDERS = eINSTANCE.getGenModel_TableProviders();

    /**
     * The meta object literal for the '<em><b>Color Providers</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__COLOR_PROVIDERS = eINSTANCE.getGenModel_ColorProviders();

    /**
     * The meta object literal for the '<em><b>Font Providers</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__FONT_PROVIDERS = eINSTANCE.getGenModel_FontProviders();

    /**
     * The meta object literal for the '<em><b>Runtime Version</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__RUNTIME_VERSION = eINSTANCE.getGenModel_RuntimeVersion();

    /**
     * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__LANGUAGE = eINSTANCE.getGenModel_Language();

    /**
     * The meta object literal for the '<em><b>Packed Enums</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__PACKED_ENUMS = eINSTANCE.getGenModel_PackedEnums();

    /**
     * The meta object literal for the '<em><b>Gen Packages</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_MODEL__GEN_PACKAGES = eINSTANCE.getGenModel_GenPackages();

    /**
     * The meta object literal for the '<em><b>Used Gen Packages</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_MODEL__USED_GEN_PACKAGES = eINSTANCE.getGenModel_UsedGenPackages();

    /**
     * The meta object literal for the '<em><b>Interface Name Pattern</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__INTERFACE_NAME_PATTERN = eINSTANCE.getGenModel_InterfaceNamePattern();

    /**
     * The meta object literal for the '<em><b>Class Name Pattern</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__CLASS_NAME_PATTERN = eINSTANCE.getGenModel_ClassNamePattern();

    /**
     * The meta object literal for the '<em><b>Operation Reflection</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_MODEL__OPERATION_REFLECTION = eINSTANCE.getGenModel_OperationReflection();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl <em>Gen Package</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenPackage()
     * @generated
     */
    EClass GEN_PACKAGE = eINSTANCE.getGenPackage();

    /**
     * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__PREFIX = eINSTANCE.getGenPackage_Prefix();

    /**
     * The meta object literal for the '<em><b>Base Package</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__BASE_PACKAGE = eINSTANCE.getGenPackage_BasePackage();

    /**
     * The meta object literal for the '<em><b>Resource</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__RESOURCE = eINSTANCE.getGenPackage_Resource();

    /**
     * The meta object literal for the '<em><b>Disposable Provider Factory</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__DISPOSABLE_PROVIDER_FACTORY = eINSTANCE.getGenPackage_DisposableProviderFactory();

    /**
     * The meta object literal for the '<em><b>Adapter Factory</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__ADAPTER_FACTORY = eINSTANCE.getGenPackage_AdapterFactory();

    /**
     * The meta object literal for the '<em><b>Load Initialization</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__LOAD_INITIALIZATION = eINSTANCE.getGenPackage_LoadInitialization();

    /**
     * The meta object literal for the '<em><b>Interface Package Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX = eINSTANCE.getGenPackage_InterfacePackageSuffix();

    /**
     * The meta object literal for the '<em><b>Meta Data Package Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__META_DATA_PACKAGE_SUFFIX = eINSTANCE.getGenPackage_MetaDataPackageSuffix();

    /**
     * The meta object literal for the '<em><b>Class Package Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__CLASS_PACKAGE_SUFFIX = eINSTANCE.getGenPackage_ClassPackageSuffix();

    /**
     * The meta object literal for the '<em><b>Utility Package Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__UTILITY_PACKAGE_SUFFIX = eINSTANCE.getGenPackage_UtilityPackageSuffix();

    /**
     * The meta object literal for the '<em><b>Provider Package Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__PROVIDER_PACKAGE_SUFFIX = eINSTANCE.getGenPackage_ProviderPackageSuffix();

    /**
     * The meta object literal for the '<em><b>Presentation Package Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__PRESENTATION_PACKAGE_SUFFIX = eINSTANCE.getGenPackage_PresentationPackageSuffix();

    /**
     * The meta object literal for the '<em><b>Tests Package Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__TESTS_PACKAGE_SUFFIX = eINSTANCE.getGenPackage_TestsPackageSuffix();

    /**
     * The meta object literal for the '<em><b>Generate Example Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__GENERATE_EXAMPLE_CLASS = eINSTANCE.getGenPackage_GenerateExampleClass();

    /**
     * The meta object literal for the '<em><b>Literals Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__LITERALS_INTERFACE = eINSTANCE.getGenPackage_LiteralsInterface();

    /**
     * The meta object literal for the '<em><b>Data Type Converters</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__DATA_TYPE_CONVERTERS = eINSTANCE.getGenPackage_DataTypeConverters();

    /**
     * The meta object literal for the '<em><b>Multiple Editor Pages</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__MULTIPLE_EDITOR_PAGES = eINSTANCE.getGenPackage_MultipleEditorPages();

    /**
     * The meta object literal for the '<em><b>Generate Model Wizard</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__GENERATE_MODEL_WIZARD = eINSTANCE.getGenPackage_GenerateModelWizard();

    /**
     * The meta object literal for the '<em><b>Extensible Provider Factory</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__EXTENSIBLE_PROVIDER_FACTORY = eINSTANCE.getGenPackage_ExtensibleProviderFactory();

    /**
     * The meta object literal for the '<em><b>Child Creation Extenders</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__CHILD_CREATION_EXTENDERS = eINSTANCE.getGenPackage_ChildCreationExtenders();

    /**
     * The meta object literal for the '<em><b>Content Type Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__CONTENT_TYPE_IDENTIFIER = eINSTANCE.getGenPackage_ContentTypeIdentifier();

    /**
     * The meta object literal for the '<em><b>File Extensions</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_PACKAGE__FILE_EXTENSIONS = eINSTANCE.getGenPackage_FileExtensions();

    /**
     * The meta object literal for the '<em><b>Ecore Package</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_PACKAGE__ECORE_PACKAGE = eINSTANCE.getGenPackage_EcorePackage();

    /**
     * The meta object literal for the '<em><b>Gen Model</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_PACKAGE__GEN_MODEL = eINSTANCE.getGenPackage_GenModel();

    /**
     * The meta object literal for the '<em><b>Gen Enums</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_PACKAGE__GEN_ENUMS = eINSTANCE.getGenPackage_GenEnums();

    /**
     * The meta object literal for the '<em><b>Gen Data Types</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_PACKAGE__GEN_DATA_TYPES = eINSTANCE.getGenPackage_GenDataTypes();

    /**
     * The meta object literal for the '<em><b>Gen Classes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_PACKAGE__GEN_CLASSES = eINSTANCE.getGenPackage_GenClasses();

    /**
     * The meta object literal for the '<em><b>Nested Gen Packages</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_PACKAGE__NESTED_GEN_PACKAGES = eINSTANCE.getGenPackage_NestedGenPackages();

    /**
     * The meta object literal for the '<em><b>Gen Classifiers</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_PACKAGE__GEN_CLASSIFIERS = eINSTANCE.getGenPackage_GenClassifiers();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl <em>Gen Class</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenClass()
     * @generated
     */
    EClass GEN_CLASS = eINSTANCE.getGenClass();

    /**
     * The meta object literal for the '<em><b>Provider</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_CLASS__PROVIDER = eINSTANCE.getGenClass_Provider();

    /**
     * The meta object literal for the '<em><b>Image</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_CLASS__IMAGE = eINSTANCE.getGenClass_Image();

    /**
     * The meta object literal for the '<em><b>Dynamic</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_CLASS__DYNAMIC = eINSTANCE.getGenClass_Dynamic();

    /**
     * The meta object literal for the '<em><b>Ecore Class</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_CLASS__ECORE_CLASS = eINSTANCE.getGenClass_EcoreClass();

    /**
     * The meta object literal for the '<em><b>Gen Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_CLASS__GEN_FEATURES = eINSTANCE.getGenClass_GenFeatures();

    /**
     * The meta object literal for the '<em><b>Gen Operations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_CLASS__GEN_OPERATIONS = eINSTANCE.getGenClass_GenOperations();

    /**
     * The meta object literal for the '<em><b>Label Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_CLASS__LABEL_FEATURE = eINSTANCE.getGenClass_LabelFeature();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl <em>Gen Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenFeature()
     * @generated
     */
    EClass GEN_FEATURE = eINSTANCE.getGenFeature();

    /**
     * The meta object literal for the '<em><b>Property</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_FEATURE__PROPERTY = eINSTANCE.getGenFeature_Property();

    /**
     * The meta object literal for the '<em><b>Notify</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_FEATURE__NOTIFY = eINSTANCE.getGenFeature_Notify();

    /**
     * The meta object literal for the '<em><b>Children</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_FEATURE__CHILDREN = eINSTANCE.getGenFeature_Children();

    /**
     * The meta object literal for the '<em><b>Create Child</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_FEATURE__CREATE_CHILD = eINSTANCE.getGenFeature_CreateChild();

    /**
     * The meta object literal for the '<em><b>Property Category</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_FEATURE__PROPERTY_CATEGORY = eINSTANCE.getGenFeature_PropertyCategory();

    /**
     * The meta object literal for the '<em><b>Property Filter Flags</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_FEATURE__PROPERTY_FILTER_FLAGS = eINSTANCE.getGenFeature_PropertyFilterFlags();

    /**
     * The meta object literal for the '<em><b>Property Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_FEATURE__PROPERTY_DESCRIPTION = eINSTANCE.getGenFeature_PropertyDescription();

    /**
     * The meta object literal for the '<em><b>Property Multi Line</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_FEATURE__PROPERTY_MULTI_LINE = eINSTANCE.getGenFeature_PropertyMultiLine();

    /**
     * The meta object literal for the '<em><b>Property Sort Choices</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_FEATURE__PROPERTY_SORT_CHOICES = eINSTANCE.getGenFeature_PropertySortChoices();

    /**
     * The meta object literal for the '<em><b>Gen Class</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_FEATURE__GEN_CLASS = eINSTANCE.getGenFeature_GenClass();

    /**
     * The meta object literal for the '<em><b>Ecore Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_FEATURE__ECORE_FEATURE = eINSTANCE.getGenFeature_EcoreFeature();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenBaseImpl <em>Gen Base</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenBaseImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenBase()
     * @generated
     */
    EClass GEN_BASE = eINSTANCE.getGenBase();

    /**
     * The meta object literal for the '<em><b>Gen Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_BASE__GEN_ANNOTATIONS = eINSTANCE.getGenBase_GenAnnotations();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumImpl <em>Gen Enum</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenEnum()
     * @generated
     */
    EClass GEN_ENUM = eINSTANCE.getGenEnum();

    /**
     * The meta object literal for the '<em><b>Type Safe Enum Compatible</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_ENUM__TYPE_SAFE_ENUM_COMPATIBLE = eINSTANCE.getGenEnum_TypeSafeEnumCompatible();

    /**
     * The meta object literal for the '<em><b>Ecore Enum</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_ENUM__ECORE_ENUM = eINSTANCE.getGenEnum_EcoreEnum();

    /**
     * The meta object literal for the '<em><b>Gen Enum Literals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_ENUM__GEN_ENUM_LITERALS = eINSTANCE.getGenEnum_GenEnumLiterals();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumLiteralImpl <em>Gen Enum Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumLiteralImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenEnumLiteral()
     * @generated
     */
    EClass GEN_ENUM_LITERAL = eINSTANCE.getGenEnumLiteral();

    /**
     * The meta object literal for the '<em><b>Gen Enum</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_ENUM_LITERAL__GEN_ENUM = eINSTANCE.getGenEnumLiteral_GenEnum();

    /**
     * The meta object literal for the '<em><b>Ecore Enum Literal</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL = eINSTANCE.getGenEnumLiteral_EcoreEnumLiteral();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassifierImpl <em>Gen Classifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassifierImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenClassifier()
     * @generated
     */
    EClass GEN_CLASSIFIER = eINSTANCE.getGenClassifier();

    /**
     * The meta object literal for the '<em><b>Gen Package</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_CLASSIFIER__GEN_PACKAGE = eINSTANCE.getGenClassifier_GenPackage();

    /**
     * The meta object literal for the '<em><b>Gen Type Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_CLASSIFIER__GEN_TYPE_PARAMETERS = eINSTANCE.getGenClassifier_GenTypeParameters();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenDataTypeImpl <em>Gen Data Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenDataTypeImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenDataType()
     * @generated
     */
    EClass GEN_DATA_TYPE = eINSTANCE.getGenDataType();

    /**
     * The meta object literal for the '<em><b>Ecore Data Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_DATA_TYPE__ECORE_DATA_TYPE = eINSTANCE.getGenDataType_EcoreDataType();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenOperationImpl <em>Gen Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenOperationImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenOperation()
     * @generated
     */
    EClass GEN_OPERATION = eINSTANCE.getGenOperation();

    /**
     * The meta object literal for the '<em><b>Gen Class</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_OPERATION__GEN_CLASS = eINSTANCE.getGenOperation_GenClass();

    /**
     * The meta object literal for the '<em><b>Ecore Operation</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_OPERATION__ECORE_OPERATION = eINSTANCE.getGenOperation_EcoreOperation();

    /**
     * The meta object literal for the '<em><b>Gen Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_OPERATION__GEN_PARAMETERS = eINSTANCE.getGenOperation_GenParameters();

    /**
     * The meta object literal for the '<em><b>Gen Type Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_OPERATION__GEN_TYPE_PARAMETERS = eINSTANCE.getGenOperation_GenTypeParameters();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenParameterImpl <em>Gen Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenParameterImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenParameter()
     * @generated
     */
    EClass GEN_PARAMETER = eINSTANCE.getGenParameter();

    /**
     * The meta object literal for the '<em><b>Gen Operation</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_PARAMETER__GEN_OPERATION = eINSTANCE.getGenParameter_GenOperation();

    /**
     * The meta object literal for the '<em><b>Ecore Parameter</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_PARAMETER__ECORE_PARAMETER = eINSTANCE.getGenParameter_EcoreParameter();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypedElementImpl <em>Gen Typed Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypedElementImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenTypedElement()
     * @generated
     */
    EClass GEN_TYPED_ELEMENT = eINSTANCE.getGenTypedElement();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenAnnotationImpl <em>Gen Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenAnnotationImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenAnnotation()
     * @generated
     */
    EClass GEN_ANNOTATION = eINSTANCE.getGenAnnotation();

    /**
     * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GEN_ANNOTATION__SOURCE = eINSTANCE.getGenAnnotation_Source();

    /**
     * The meta object literal for the '<em><b>Details</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_ANNOTATION__DETAILS = eINSTANCE.getGenAnnotation_Details();

    /**
     * The meta object literal for the '<em><b>Gen Base</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_ANNOTATION__GEN_BASE = eINSTANCE.getGenAnnotation_GenBase();

    /**
     * The meta object literal for the '<em><b>References</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_ANNOTATION__REFERENCES = eINSTANCE.getGenAnnotation_References();

    /**
     * The meta object literal for the '<em><b>Contents</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_ANNOTATION__CONTENTS = eINSTANCE.getGenAnnotation_Contents();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypeParameterImpl <em>Gen Type Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypeParameterImpl
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenTypeParameter()
     * @generated
     */
    EClass GEN_TYPE_PARAMETER = eINSTANCE.getGenTypeParameter();

    /**
     * The meta object literal for the '<em><b>Ecore Type Parameter</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER = eINSTANCE.getGenTypeParameter_EcoreTypeParameter();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind <em>Gen Provider Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenProviderKind()
     * @generated
     */
    EEnum GEN_PROVIDER_KIND = eINSTANCE.getGenProviderKind();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind <em>Gen Property Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenPropertyKind()
     * @generated
     */
    EEnum GEN_PROPERTY_KIND = eINSTANCE.getGenPropertyKind();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind <em>Gen Resource Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenResourceKind()
     * @generated
     */
    EEnum GEN_RESOURCE_KIND = eINSTANCE.getGenResourceKind();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind <em>Gen Delegation Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenDelegationKind()
     * @generated
     */
    EEnum GEN_DELEGATION_KIND = eINSTANCE.getGenDelegationKind();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel <em>Gen JDK Level</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenJDKLevel()
     * @generated
     */
    EEnum GEN_JDK_LEVEL = eINSTANCE.getGenJDKLevel();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion <em>Gen Runtime Version</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion
     * @see org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelPackageImpl#getGenRuntimeVersion()
     * @generated
     */
    EEnum GEN_RUNTIME_VERSION = eINSTANCE.getGenRuntimeVersion();

  }

} //GenModelPackage
