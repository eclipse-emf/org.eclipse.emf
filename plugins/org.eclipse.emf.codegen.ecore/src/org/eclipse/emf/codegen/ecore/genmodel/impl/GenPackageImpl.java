/**
 * Copyright (c) 2002-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.InternalEList;
import org.osgi.framework.Bundle;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getBasePackage <em>Base Package</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isDisposableProviderFactory <em>Disposable Provider Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isAdapterFactory <em>Adapter Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isLoadInitialization <em>Load Initialization</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getInterfacePackageSuffix <em>Interface Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getMetaDataPackageSuffix <em>Meta Data Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getClassPackageSuffix <em>Class Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getUtilityPackageSuffix <em>Utility Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getProviderPackageSuffix <em>Provider Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getPresentationPackageSuffix <em>Presentation Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getTestsPackageSuffix <em>Tests Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isGenerateExampleClass <em>Generate Example Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isLiteralsInterface <em>Literals Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isDataTypeConverters <em>Data Type Converters</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isMultipleEditorPages <em>Multiple Editor Pages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isGenerateModelWizard <em>Generate Model Wizard</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isExtensibleProviderFactory <em>Extensible Provider Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isChildCreationExtenders <em>Child Creation Extenders</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getContentTypeIdentifier <em>Content Type Identifier</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getFileExtensions <em>File Extensions</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getEcorePackage <em>Ecore Package</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getGenModel <em>Gen Model</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getGenEnums <em>Gen Enums</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getGenDataTypes <em>Gen Data Types</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getGenClasses <em>Gen Classes</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getNestedGenPackages <em>Nested Gen Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getGenClassifiers <em>Gen Classifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenPackageImpl extends GenBaseImpl implements GenPackage
{
  protected static final String XSD2ECORE_URI = "http:///org/eclipse/emf/mapping/xsd2ecore/XSD2Ecore";

  /**
   * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrefix()
   * @generated
   * @ordered
   */
  protected static final String PREFIX_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrefix()
   * @generated
   * @ordered
   */
  protected String prefix = PREFIX_EDEFAULT;

  /**
   * The default value of the '{@link #getBasePackage() <em>Base Package</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBasePackage()
   * @generated
   * @ordered
   */
  protected static final String BASE_PACKAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBasePackage() <em>Base Package</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBasePackage()
   * @generated
   * @ordered
   */
  protected String basePackage = BASE_PACKAGE_EDEFAULT;

  /**
   * The default value of the '{@link #getResource() <em>Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResource()
   * @generated
   * @ordered
   */
  protected static final GenResourceKind RESOURCE_EDEFAULT = GenResourceKind.NONE_LITERAL;

  /**
   * The cached value of the '{@link #getResource() <em>Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResource()
   * @generated
   * @ordered
   */
  protected GenResourceKind resource = RESOURCE_EDEFAULT;

  /**
   * The default value of the '{@link #isDisposableProviderFactory() <em>Disposable Provider Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDisposableProviderFactory()
   * @generated
   * @ordered
   */
  protected static final boolean DISPOSABLE_PROVIDER_FACTORY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDisposableProviderFactory() <em>Disposable Provider Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDisposableProviderFactory()
   * @generated
   * @ordered
   */
  protected boolean disposableProviderFactory = DISPOSABLE_PROVIDER_FACTORY_EDEFAULT;

  /**
   * The default value of the '{@link #isAdapterFactory() <em>Adapter Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAdapterFactory()
   * @generated
   * @ordered
   */
  protected static final boolean ADAPTER_FACTORY_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isAdapterFactory() <em>Adapter Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAdapterFactory()
   * @generated
   * @ordered
   */
  protected boolean adapterFactory = ADAPTER_FACTORY_EDEFAULT;

  /**
   * The default value of the '{@link #isLoadInitialization() <em>Load Initialization</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLoadInitialization()
   * @generated
   * @ordered
   */
  protected static final boolean LOAD_INITIALIZATION_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isLoadInitialization() <em>Load Initialization</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLoadInitialization()
   * @generated
   * @ordered
   */
  protected boolean loadInitialization = LOAD_INITIALIZATION_EDEFAULT;

  /**
   * The default value of the '{@link #getInterfacePackageSuffix() <em>Interface Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInterfacePackageSuffix()
   * @generated
   * @ordered
   */
  protected static final String INTERFACE_PACKAGE_SUFFIX_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getInterfacePackageSuffix() <em>Interface Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInterfacePackageSuffix()
   * @generated
   * @ordered
   */
  protected String interfacePackageSuffix = INTERFACE_PACKAGE_SUFFIX_EDEFAULT;

  /**
   * The default value of the '{@link #getMetaDataPackageSuffix() <em>Meta Data Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMetaDataPackageSuffix()
   * @generated
   * @ordered
   */
  protected static final String META_DATA_PACKAGE_SUFFIX_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getMetaDataPackageSuffix() <em>Meta Data Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMetaDataPackageSuffix()
   * @generated
   * @ordered
   */
  protected String metaDataPackageSuffix = META_DATA_PACKAGE_SUFFIX_EDEFAULT;

  /**
   * The default value of the '{@link #getClassPackageSuffix() <em>Class Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassPackageSuffix()
   * @generated
   * @ordered
   */
  protected static final String CLASS_PACKAGE_SUFFIX_EDEFAULT = "impl";

  /**
   * The cached value of the '{@link #getClassPackageSuffix() <em>Class Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassPackageSuffix()
   * @generated
   * @ordered
   */
  protected String classPackageSuffix = CLASS_PACKAGE_SUFFIX_EDEFAULT;

  /**
   * The default value of the '{@link #getUtilityPackageSuffix() <em>Utility Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUtilityPackageSuffix()
   * @generated
   * @ordered
   */
  protected static final String UTILITY_PACKAGE_SUFFIX_EDEFAULT = "util";

  /**
   * The cached value of the '{@link #getUtilityPackageSuffix() <em>Utility Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUtilityPackageSuffix()
   * @generated
   * @ordered
   */
  protected String utilityPackageSuffix = UTILITY_PACKAGE_SUFFIX_EDEFAULT;

  /**
   * The default value of the '{@link #getProviderPackageSuffix() <em>Provider Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProviderPackageSuffix()
   * @generated
   * @ordered
   */
  protected static final String PROVIDER_PACKAGE_SUFFIX_EDEFAULT = "provider";

  /**
   * The cached value of the '{@link #getProviderPackageSuffix() <em>Provider Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProviderPackageSuffix()
   * @generated
   * @ordered
   */
  protected String providerPackageSuffix = PROVIDER_PACKAGE_SUFFIX_EDEFAULT;

  /**
   * The default value of the '{@link #getPresentationPackageSuffix() <em>Presentation Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPresentationPackageSuffix()
   * @generated
   * @ordered
   */
  protected static final String PRESENTATION_PACKAGE_SUFFIX_EDEFAULT = "presentation";

  /**
   * The cached value of the '{@link #getPresentationPackageSuffix() <em>Presentation Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPresentationPackageSuffix()
   * @generated
   * @ordered
   */
  protected String presentationPackageSuffix = PRESENTATION_PACKAGE_SUFFIX_EDEFAULT;

  /**
   * The default value of the '{@link #getTestsPackageSuffix() <em>Tests Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTestsPackageSuffix()
   * @generated
   * @ordered
   */
  protected static final String TESTS_PACKAGE_SUFFIX_EDEFAULT = "tests";

  /**
   * The cached value of the '{@link #getTestsPackageSuffix() <em>Tests Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTestsPackageSuffix()
   * @generated
   * @ordered
   */
  protected String testsPackageSuffix = TESTS_PACKAGE_SUFFIX_EDEFAULT;

  /**
   * The default value of the '{@link #isGenerateExampleClass() <em>Generate Example Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGenerateExampleClass()
   * @generated
   * @ordered
   */
  protected static final boolean GENERATE_EXAMPLE_CLASS_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isGenerateExampleClass() <em>Generate Example Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGenerateExampleClass()
   * @generated
   * @ordered
   */
  protected boolean generateExampleClass = GENERATE_EXAMPLE_CLASS_EDEFAULT;

  /**
   * The default value of the '{@link #isLiteralsInterface() <em>Literals Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLiteralsInterface()
   * @generated
   * @ordered
   */
  protected static final boolean LITERALS_INTERFACE_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isLiteralsInterface() <em>Literals Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLiteralsInterface()
   * @generated
   * @ordered
   */
  protected boolean literalsInterface = LITERALS_INTERFACE_EDEFAULT;

  /**
   * The default value of the '{@link #isDataTypeConverters() <em>Data Type Converters</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDataTypeConverters()
   * @generated
   * @ordered
   */
  protected static final boolean DATA_TYPE_CONVERTERS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDataTypeConverters() <em>Data Type Converters</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDataTypeConverters()
   * @generated
   * @ordered
   */
  protected boolean dataTypeConverters = DATA_TYPE_CONVERTERS_EDEFAULT;

  /**
   * The default value of the '{@link #isMultipleEditorPages() <em>Multiple Editor Pages</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMultipleEditorPages()
   * @generated
   * @ordered
   */
  protected static final boolean MULTIPLE_EDITOR_PAGES_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isMultipleEditorPages() <em>Multiple Editor Pages</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMultipleEditorPages()
   * @generated
   * @ordered
   */
  protected boolean multipleEditorPages = MULTIPLE_EDITOR_PAGES_EDEFAULT;

  /**
   * The default value of the '{@link #isGenerateModelWizard() <em>Generate Model Wizard</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGenerateModelWizard()
   * @generated
   * @ordered
   */
  protected static final boolean GENERATE_MODEL_WIZARD_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isGenerateModelWizard() <em>Generate Model Wizard</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGenerateModelWizard()
   * @generated
   * @ordered
   */
  protected boolean generateModelWizard = GENERATE_MODEL_WIZARD_EDEFAULT;

  /**
   * The default value of the '{@link #isExtensibleProviderFactory() <em>Extensible Provider Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExtensibleProviderFactory()
   * @generated
   * @ordered
   */
  protected static final boolean EXTENSIBLE_PROVIDER_FACTORY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isExtensibleProviderFactory() <em>Extensible Provider Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExtensibleProviderFactory()
   * @generated
   * @ordered
   */
  protected boolean extensibleProviderFactory = EXTENSIBLE_PROVIDER_FACTORY_EDEFAULT;

  /**
   * The default value of the '{@link #isChildCreationExtenders() <em>Child Creation Extenders</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isChildCreationExtenders()
   * @generated
   * @ordered
   */
  protected static final boolean CHILD_CREATION_EXTENDERS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isChildCreationExtenders() <em>Child Creation Extenders</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isChildCreationExtenders()
   * @generated
   * @ordered
   */
  protected boolean childCreationExtenders = CHILD_CREATION_EXTENDERS_EDEFAULT;

  /**
   * The default value of the '{@link #getContentTypeIdentifier() <em>Content Type Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContentTypeIdentifier()
   * @generated
   * @ordered
   */
  protected static final String CONTENT_TYPE_IDENTIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getContentTypeIdentifier() <em>Content Type Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContentTypeIdentifier()
   * @generated
   * @ordered
   */
  protected String contentTypeIdentifier = CONTENT_TYPE_IDENTIFIER_EDEFAULT;

  /**
   * The default value of the '{@link #getFileExtensions() <em>File Extensions</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFileExtensions()
   * @generated
   * @ordered
   */
  protected static final String FILE_EXTENSIONS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFileExtensions() <em>File Extensions</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFileExtensions()
   * @generated
   * @ordered
   */
  protected String fileExtensions = FILE_EXTENSIONS_EDEFAULT;

  /**
   * The cached value of the '{@link #getEcorePackage() <em>Ecore Package</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcorePackage()
   * @generated
   * @ordered
   */
  protected EPackage ecorePackage;

  /**
   * The cached value of the '{@link #getGenEnums() <em>Gen Enums</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenEnums()
   * @generated
   * @ordered
   */
  protected EList<GenEnum> genEnums;

  /**
   * The cached value of the '{@link #getGenDataTypes() <em>Gen Data Types</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenDataTypes()
   * @generated
   * @ordered
   */
  protected EList<GenDataType> genDataTypes;

  /**
   * The cached value of the '{@link #getGenClasses() <em>Gen Classes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenClasses()
   * @generated
   * @ordered
   */
  protected EList<GenClass> genClasses;

  /**
   * The cached value of the '{@link #getNestedGenPackages() <em>Nested Gen Packages</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNestedGenPackages()
   * @generated
   * @ordered
   */
  protected EList<GenPackage> nestedGenPackages;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenPackageImpl() 
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return GenModelPackage.Literals.GEN_PACKAGE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPrefixGen()
  {
    return prefix;
  }

  public String getPrefix()
  {
    if (getPrefixGen() != null)
    {
      return getPrefixGen();
    }
    return "";
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrefix(String newPrefix)
  {
    String oldPrefix = prefix;
    prefix = newPrefix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__PREFIX, oldPrefix, prefix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBasePackageGen()
  {
    return basePackage;
  }

  public String getBasePackage()
  {
    // If the base package isn't set but there is a parent package, us it's information instead.
    //
    String result = getBasePackageGen();
    if (isBlank(result))
    {
      EObject container = eContainer();
      result = container instanceof GenPackage ? ((GenPackage)container).getQualifiedPackageName() : null;
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBasePackage(String newBasePackage)
  {
    String oldBasePackage = basePackage;
    basePackage = newBasePackage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__BASE_PACKAGE, oldBasePackage, basePackage));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenResourceKind getResource()
  {
    return resource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResource(GenResourceKind newResource)
  {
    GenResourceKind oldResource = resource;
    resource = newResource == null ? RESOURCE_EDEFAULT : newResource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__RESOURCE, oldResource, resource));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDisposableProviderFactory()
  {
    return disposableProviderFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDisposableProviderFactory(boolean newDisposableProviderFactory)
  {
    boolean oldDisposableProviderFactory = disposableProviderFactory;
    disposableProviderFactory = newDisposableProviderFactory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__DISPOSABLE_PROVIDER_FACTORY, oldDisposableProviderFactory, disposableProviderFactory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAdapterFactory()
  {
    return adapterFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAdapterFactory(boolean newAdapterFactory)
  {
    boolean oldAdapterFactory = adapterFactory;
    adapterFactory = newAdapterFactory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__ADAPTER_FACTORY, oldAdapterFactory, adapterFactory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isLoadInitialization()
  {
    return loadInitialization;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLoadInitialization(boolean newLoadInitialization)
  {
    boolean oldLoadInitialization = loadInitialization;
    loadInitialization = newLoadInitialization;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__LOAD_INITIALIZATION, oldLoadInitialization, loadInitialization));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getInterfacePackageSuffix()
  {
    return interfacePackageSuffix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInterfacePackageSuffix(String newInterfacePackageSuffix)
  {
    String oldInterfacePackageSuffix = interfacePackageSuffix;
    interfacePackageSuffix = newInterfacePackageSuffix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX, oldInterfacePackageSuffix, interfacePackageSuffix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMetaDataPackageSuffix()
  {
    return metaDataPackageSuffix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMetaDataPackageSuffix(String newMetaDataPackageSuffix)
  {
    String oldMetaDataPackageSuffix = metaDataPackageSuffix;
    metaDataPackageSuffix = newMetaDataPackageSuffix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__META_DATA_PACKAGE_SUFFIX, oldMetaDataPackageSuffix, metaDataPackageSuffix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getClassPackageSuffix()
  {
    return classPackageSuffix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClassPackageSuffix(String newClassPackageSuffix)
  {
    String oldClassPackageSuffix = classPackageSuffix;
    classPackageSuffix = newClassPackageSuffix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__CLASS_PACKAGE_SUFFIX, oldClassPackageSuffix, classPackageSuffix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUtilityPackageSuffix()
  {
    return utilityPackageSuffix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUtilityPackageSuffix(String newUtilityPackageSuffix)
  {
    String oldUtilityPackageSuffix = utilityPackageSuffix;
    utilityPackageSuffix = newUtilityPackageSuffix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__UTILITY_PACKAGE_SUFFIX, oldUtilityPackageSuffix, utilityPackageSuffix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getProviderPackageSuffix()
  {
    return providerPackageSuffix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProviderPackageSuffix(String newProviderPackageSuffix)
  {
    String oldProviderPackageSuffix = providerPackageSuffix;
    providerPackageSuffix = newProviderPackageSuffix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__PROVIDER_PACKAGE_SUFFIX, oldProviderPackageSuffix, providerPackageSuffix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPresentationPackageSuffix()
  {
    return presentationPackageSuffix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPresentationPackageSuffix(String newPresentationPackageSuffix)
  {
    String oldPresentationPackageSuffix = presentationPackageSuffix;
    presentationPackageSuffix = newPresentationPackageSuffix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__PRESENTATION_PACKAGE_SUFFIX, oldPresentationPackageSuffix, presentationPackageSuffix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTestsPackageSuffix()
  {
    return testsPackageSuffix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTestsPackageSuffix(String newTestsPackageSuffix)
  {
    String oldTestsPackageSuffix = testsPackageSuffix;
    testsPackageSuffix = newTestsPackageSuffix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__TESTS_PACKAGE_SUFFIX, oldTestsPackageSuffix, testsPackageSuffix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isGenerateExampleClass()
  {
    return generateExampleClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenerateExampleClass(boolean newGenerateExampleClass)
  {
    boolean oldGenerateExampleClass = generateExampleClass;
    generateExampleClass = newGenerateExampleClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__GENERATE_EXAMPLE_CLASS, oldGenerateExampleClass, generateExampleClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isLiteralsInterface()
  {
    return literalsInterface;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLiteralsInterface(boolean newLiteralsInterface)
  {
    boolean oldLiteralsInterface = literalsInterface;
    literalsInterface = newLiteralsInterface;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__LITERALS_INTERFACE, oldLiteralsInterface, literalsInterface));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDataTypeConverters()
  {
    return dataTypeConverters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataTypeConverters(boolean newDataTypeConverters)
  {
    boolean oldDataTypeConverters = dataTypeConverters;
    dataTypeConverters = newDataTypeConverters;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__DATA_TYPE_CONVERTERS, oldDataTypeConverters, dataTypeConverters));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isMultipleEditorPages()
  {
    return multipleEditorPages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMultipleEditorPages(boolean newMultipleEditorPages)
  {
    boolean oldMultipleEditorPages = multipleEditorPages;
    multipleEditorPages = newMultipleEditorPages;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__MULTIPLE_EDITOR_PAGES, oldMultipleEditorPages, multipleEditorPages));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isGenerateModelWizard()
  {
    return generateModelWizard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenerateModelWizard(boolean newGenerateModelWizard)
  {
    boolean oldGenerateModelWizard = generateModelWizard;
    generateModelWizard = newGenerateModelWizard;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__GENERATE_MODEL_WIZARD, oldGenerateModelWizard, generateModelWizard));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isExtensibleProviderFactory()
  {
    return extensibleProviderFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExtensibleProviderFactory(boolean newExtensibleProviderFactory)
  {
    boolean oldExtensibleProviderFactory = extensibleProviderFactory;
    extensibleProviderFactory = newExtensibleProviderFactory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__EXTENSIBLE_PROVIDER_FACTORY, oldExtensibleProviderFactory, extensibleProviderFactory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isChildCreationExtenders()
  {
    return childCreationExtenders;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setChildCreationExtenders(boolean newChildCreationExtenders)
  {
    boolean oldChildCreationExtenders = childCreationExtenders;
    childCreationExtenders = newChildCreationExtenders;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__CHILD_CREATION_EXTENDERS, oldChildCreationExtenders, childCreationExtenders));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getContentTypeIdentifier()
  {
    return contentTypeIdentifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContentTypeIdentifier(String newContentTypeIdentifier)
  {
    String oldContentTypeIdentifier = contentTypeIdentifier;
    contentTypeIdentifier = newContentTypeIdentifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__CONTENT_TYPE_IDENTIFIER, oldContentTypeIdentifier, contentTypeIdentifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFileExtensionsGen()
  {
    return fileExtensions;
  }

  private static final Pattern COMMA_SEPARATOR_PATTERN = Pattern.compile("\\s*,\\s*");

  public String getFileExtensions()
  {
    if (fileExtensions == null)
    {
      return getPrefix().toLowerCase(getGenModel().getLocale());
    }
    return COMMA_SEPARATOR_PATTERN.matcher(fileExtensions).replaceAll(",");
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFileExtensions(String newFileExtensions)
  {
    String oldFileExtensions = fileExtensions;
    fileExtensions = newFileExtensions;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__FILE_EXTENSIONS, oldFileExtensions, fileExtensions));
  }

  @Override
  public  EModelElement getEcoreModelElement()
  {
    return getEcorePackage();
  }

  @Override
  protected EModelElement basicGetEcoreModelElement()
  {
    return ecorePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EPackage getEcorePackage()
  {
    if (ecorePackage != null && ecorePackage.eIsProxy())
    {
      InternalEObject oldEcorePackage = (InternalEObject)ecorePackage;
      ecorePackage = (EPackage)eResolveProxy(oldEcorePackage);
      if (ecorePackage != oldEcorePackage)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_PACKAGE__ECORE_PACKAGE, oldEcorePackage, ecorePackage));
      }
    }
    return ecorePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EPackage basicGetEcorePackage()
  {
    return ecorePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcorePackage(EPackage newEcorePackage)
  {
    EPackage oldEcorePackage = ecorePackage;
    ecorePackage = newEcorePackage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__ECORE_PACKAGE, oldEcorePackage, ecorePackage));
  }

  GenModel genModelContainer;
  GenBase genBaseContainer;

  @Override
  public GenModel getGenModel() 
  {
    // We can't blindly cache the genModel, since we need to support moving the GenPackage to another container,
    // but the cast is expensive, so we'd like to do it no more than absolutely necessary.
    //
    if (eContainerFeatureID == GenModelPackage.GEN_PACKAGE__GEN_MODEL)
    {
      genBaseContainer = null;
      if (eContainer != genModelContainer)
      {
        genModelContainer = (GenModel)eContainer;
      }
      return genModelContainer;
    }
  
    genModelContainer = null;
    if (eContainer != genBaseContainer)
    {
      genBaseContainer = (GenBase)eContainer;
    }
    return genBaseContainer != null ? genBaseContainer.getGenModel() : null;
  }

  /**
   * <!-- begin-user-doc -->
   * @deprecated
   * <!-- end-user-doc -->
   * @generated
   */
  @Deprecated
  public GenModel getGenModelGen()
  {
    if (eContainerFeatureID() != GenModelPackage.GEN_PACKAGE__GEN_MODEL) return null;
    return (GenModel)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGenModel(GenModel newGenModel, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newGenModel, GenModelPackage.GEN_PACKAGE__GEN_MODEL, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenModel(GenModel newGenModel)
  {
    if (newGenModel != eInternalContainer() || (eContainerFeatureID() != GenModelPackage.GEN_PACKAGE__GEN_MODEL && newGenModel != null))
    {
      if (EcoreUtil.isAncestor(this, newGenModel))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenModel != null)
        msgs = ((InternalEObject)newGenModel).eInverseAdd(this, GenModelPackage.GEN_MODEL__GEN_PACKAGES, GenModel.class, msgs);
      msgs = basicSetGenModel(newGenModel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__GEN_MODEL, newGenModel, newGenModel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenEnum> getGenEnums()
  {
    if (genEnums == null)
    {
      genEnums = new EObjectContainmentEList<GenEnum>(GenEnum.class, this, GenModelPackage.GEN_PACKAGE__GEN_ENUMS);
    }
    return genEnums;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenDataType> getGenDataTypes()
  {
    if (genDataTypes == null)
    {
      genDataTypes = new EObjectContainmentEList<GenDataType>(GenDataType.class, this, GenModelPackage.GEN_PACKAGE__GEN_DATA_TYPES);
    }
    return genDataTypes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenClass> getGenClasses()
  {
    if (genClasses == null)
    {
      genClasses = new EObjectContainmentEList<GenClass>(GenClass.class, this, GenModelPackage.GEN_PACKAGE__GEN_CLASSES);
    }
    return genClasses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenPackage> getNestedGenPackages()
  {
    if (nestedGenPackages == null)
    {
      nestedGenPackages = new EObjectContainmentEList<GenPackage>(GenPackage.class, this, GenModelPackage.GEN_PACKAGE__NESTED_GEN_PACKAGES);
    }
    return nestedGenPackages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<GenClassifier> getGenClassifiers()
  {
    EList<GenClassifier> result = new BasicEList<GenClassifier>();
    result.addAll(getGenClasses());
    result.addAll(getGenEnums());
    result.addAll(getGenDataTypes());
    return
      new EcoreEList.UnmodifiableEList.FastCompare<GenClassifier>
        (this, GenModelPackage.Literals.GEN_PACKAGE__GEN_CLASSIFIERS, result.size(), result.toArray());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetGenModel((GenModel)otherEnd, msgs);
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
      case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
        return basicSetGenModel(null, msgs);
      case GenModelPackage.GEN_PACKAGE__GEN_ENUMS:
        return ((InternalEList<?>)getGenEnums()).basicRemove(otherEnd, msgs);
      case GenModelPackage.GEN_PACKAGE__GEN_DATA_TYPES:
        return ((InternalEList<?>)getGenDataTypes()).basicRemove(otherEnd, msgs);
      case GenModelPackage.GEN_PACKAGE__GEN_CLASSES:
        return ((InternalEList<?>)getGenClasses()).basicRemove(otherEnd, msgs);
      case GenModelPackage.GEN_PACKAGE__NESTED_GEN_PACKAGES:
        return ((InternalEList<?>)getNestedGenPackages()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
        return eInternalContainer().eInverseRemove(this, GenModelPackage.GEN_MODEL__GEN_PACKAGES, GenModel.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
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
      case GenModelPackage.GEN_PACKAGE__PREFIX:
        return getPrefix();
      case GenModelPackage.GEN_PACKAGE__BASE_PACKAGE:
        return getBasePackage();
      case GenModelPackage.GEN_PACKAGE__RESOURCE:
        return getResource();
      case GenModelPackage.GEN_PACKAGE__DISPOSABLE_PROVIDER_FACTORY:
        return isDisposableProviderFactory();
      case GenModelPackage.GEN_PACKAGE__ADAPTER_FACTORY:
        return isAdapterFactory();
      case GenModelPackage.GEN_PACKAGE__LOAD_INITIALIZATION:
        return isLoadInitialization();
      case GenModelPackage.GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX:
        return getInterfacePackageSuffix();
      case GenModelPackage.GEN_PACKAGE__META_DATA_PACKAGE_SUFFIX:
        return getMetaDataPackageSuffix();
      case GenModelPackage.GEN_PACKAGE__CLASS_PACKAGE_SUFFIX:
        return getClassPackageSuffix();
      case GenModelPackage.GEN_PACKAGE__UTILITY_PACKAGE_SUFFIX:
        return getUtilityPackageSuffix();
      case GenModelPackage.GEN_PACKAGE__PROVIDER_PACKAGE_SUFFIX:
        return getProviderPackageSuffix();
      case GenModelPackage.GEN_PACKAGE__PRESENTATION_PACKAGE_SUFFIX:
        return getPresentationPackageSuffix();
      case GenModelPackage.GEN_PACKAGE__TESTS_PACKAGE_SUFFIX:
        return getTestsPackageSuffix();
      case GenModelPackage.GEN_PACKAGE__GENERATE_EXAMPLE_CLASS:
        return isGenerateExampleClass();
      case GenModelPackage.GEN_PACKAGE__LITERALS_INTERFACE:
        return isLiteralsInterface();
      case GenModelPackage.GEN_PACKAGE__DATA_TYPE_CONVERTERS:
        return isDataTypeConverters();
      case GenModelPackage.GEN_PACKAGE__MULTIPLE_EDITOR_PAGES:
        return isMultipleEditorPages();
      case GenModelPackage.GEN_PACKAGE__GENERATE_MODEL_WIZARD:
        return isGenerateModelWizard();
      case GenModelPackage.GEN_PACKAGE__EXTENSIBLE_PROVIDER_FACTORY:
        return isExtensibleProviderFactory();
      case GenModelPackage.GEN_PACKAGE__CHILD_CREATION_EXTENDERS:
        return isChildCreationExtenders();
      case GenModelPackage.GEN_PACKAGE__CONTENT_TYPE_IDENTIFIER:
        return getContentTypeIdentifier();
      case GenModelPackage.GEN_PACKAGE__FILE_EXTENSIONS:
        return getFileExtensions();
      case GenModelPackage.GEN_PACKAGE__ECORE_PACKAGE:
        if (resolve) return getEcorePackage();
        return basicGetEcorePackage();
      case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
        return getGenModel();
      case GenModelPackage.GEN_PACKAGE__GEN_ENUMS:
        return getGenEnums();
      case GenModelPackage.GEN_PACKAGE__GEN_DATA_TYPES:
        return getGenDataTypes();
      case GenModelPackage.GEN_PACKAGE__GEN_CLASSES:
        return getGenClasses();
      case GenModelPackage.GEN_PACKAGE__NESTED_GEN_PACKAGES:
        return getNestedGenPackages();
      case GenModelPackage.GEN_PACKAGE__GEN_CLASSIFIERS:
        return getGenClassifiers();
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
      case GenModelPackage.GEN_PACKAGE__PREFIX:
        setPrefix((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__BASE_PACKAGE:
        setBasePackage((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__RESOURCE:
        setResource((GenResourceKind)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__DISPOSABLE_PROVIDER_FACTORY:
        setDisposableProviderFactory((Boolean)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__ADAPTER_FACTORY:
        setAdapterFactory((Boolean)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__LOAD_INITIALIZATION:
        setLoadInitialization((Boolean)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX:
        setInterfacePackageSuffix((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__META_DATA_PACKAGE_SUFFIX:
        setMetaDataPackageSuffix((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__CLASS_PACKAGE_SUFFIX:
        setClassPackageSuffix((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__UTILITY_PACKAGE_SUFFIX:
        setUtilityPackageSuffix((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__PROVIDER_PACKAGE_SUFFIX:
        setProviderPackageSuffix((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__PRESENTATION_PACKAGE_SUFFIX:
        setPresentationPackageSuffix((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__TESTS_PACKAGE_SUFFIX:
        setTestsPackageSuffix((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__GENERATE_EXAMPLE_CLASS:
        setGenerateExampleClass((Boolean)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__LITERALS_INTERFACE:
        setLiteralsInterface((Boolean)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__DATA_TYPE_CONVERTERS:
        setDataTypeConverters((Boolean)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__MULTIPLE_EDITOR_PAGES:
        setMultipleEditorPages((Boolean)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__GENERATE_MODEL_WIZARD:
        setGenerateModelWizard((Boolean)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__EXTENSIBLE_PROVIDER_FACTORY:
        setExtensibleProviderFactory((Boolean)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__CHILD_CREATION_EXTENDERS:
        setChildCreationExtenders((Boolean)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__CONTENT_TYPE_IDENTIFIER:
        setContentTypeIdentifier((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__FILE_EXTENSIONS:
        setFileExtensions((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__ECORE_PACKAGE:
        setEcorePackage((EPackage)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
        setGenModel((GenModel)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_ENUMS:
        getGenEnums().clear();
        getGenEnums().addAll((Collection<? extends GenEnum>)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_DATA_TYPES:
        getGenDataTypes().clear();
        getGenDataTypes().addAll((Collection<? extends GenDataType>)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_CLASSES:
        getGenClasses().clear();
        getGenClasses().addAll((Collection<? extends GenClass>)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__NESTED_GEN_PACKAGES:
        getNestedGenPackages().clear();
        getNestedGenPackages().addAll((Collection<? extends GenPackage>)newValue);
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
      case GenModelPackage.GEN_PACKAGE__PREFIX:
        setPrefix(PREFIX_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__BASE_PACKAGE:
        setBasePackage(BASE_PACKAGE_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__RESOURCE:
        setResource(RESOURCE_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__DISPOSABLE_PROVIDER_FACTORY:
        setDisposableProviderFactory(DISPOSABLE_PROVIDER_FACTORY_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__ADAPTER_FACTORY:
        setAdapterFactory(ADAPTER_FACTORY_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__LOAD_INITIALIZATION:
        setLoadInitialization(LOAD_INITIALIZATION_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX:
        setInterfacePackageSuffix(INTERFACE_PACKAGE_SUFFIX_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__META_DATA_PACKAGE_SUFFIX:
        setMetaDataPackageSuffix(META_DATA_PACKAGE_SUFFIX_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__CLASS_PACKAGE_SUFFIX:
        setClassPackageSuffix(CLASS_PACKAGE_SUFFIX_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__UTILITY_PACKAGE_SUFFIX:
        setUtilityPackageSuffix(UTILITY_PACKAGE_SUFFIX_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__PROVIDER_PACKAGE_SUFFIX:
        setProviderPackageSuffix(PROVIDER_PACKAGE_SUFFIX_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__PRESENTATION_PACKAGE_SUFFIX:
        setPresentationPackageSuffix(PRESENTATION_PACKAGE_SUFFIX_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__TESTS_PACKAGE_SUFFIX:
        setTestsPackageSuffix(TESTS_PACKAGE_SUFFIX_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__GENERATE_EXAMPLE_CLASS:
        setGenerateExampleClass(GENERATE_EXAMPLE_CLASS_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__LITERALS_INTERFACE:
        setLiteralsInterface(LITERALS_INTERFACE_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__DATA_TYPE_CONVERTERS:
        setDataTypeConverters(DATA_TYPE_CONVERTERS_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__MULTIPLE_EDITOR_PAGES:
        setMultipleEditorPages(MULTIPLE_EDITOR_PAGES_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__GENERATE_MODEL_WIZARD:
        setGenerateModelWizard(GENERATE_MODEL_WIZARD_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__EXTENSIBLE_PROVIDER_FACTORY:
        setExtensibleProviderFactory(EXTENSIBLE_PROVIDER_FACTORY_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__CHILD_CREATION_EXTENDERS:
        setChildCreationExtenders(CHILD_CREATION_EXTENDERS_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__CONTENT_TYPE_IDENTIFIER:
        setContentTypeIdentifier(CONTENT_TYPE_IDENTIFIER_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__FILE_EXTENSIONS:
        setFileExtensions(FILE_EXTENSIONS_EDEFAULT);
        return;
      case GenModelPackage.GEN_PACKAGE__ECORE_PACKAGE:
        setEcorePackage((EPackage)null);
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
        setGenModel((GenModel)null);
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_ENUMS:
        getGenEnums().clear();
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_DATA_TYPES:
        getGenDataTypes().clear();
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_CLASSES:
        getGenClasses().clear();
        return;
      case GenModelPackage.GEN_PACKAGE__NESTED_GEN_PACKAGES:
        getNestedGenPackages().clear();
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
      case GenModelPackage.GEN_PACKAGE__PREFIX:
        return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
      case GenModelPackage.GEN_PACKAGE__BASE_PACKAGE:
        return BASE_PACKAGE_EDEFAULT == null ? basePackage != null : !BASE_PACKAGE_EDEFAULT.equals(basePackage);
      case GenModelPackage.GEN_PACKAGE__RESOURCE:
        return resource != RESOURCE_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__DISPOSABLE_PROVIDER_FACTORY:
        return disposableProviderFactory != DISPOSABLE_PROVIDER_FACTORY_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__ADAPTER_FACTORY:
        return adapterFactory != ADAPTER_FACTORY_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__LOAD_INITIALIZATION:
        return loadInitialization != LOAD_INITIALIZATION_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX:
        return INTERFACE_PACKAGE_SUFFIX_EDEFAULT == null ? interfacePackageSuffix != null : !INTERFACE_PACKAGE_SUFFIX_EDEFAULT.equals(interfacePackageSuffix);
      case GenModelPackage.GEN_PACKAGE__META_DATA_PACKAGE_SUFFIX:
        return META_DATA_PACKAGE_SUFFIX_EDEFAULT == null ? metaDataPackageSuffix != null : !META_DATA_PACKAGE_SUFFIX_EDEFAULT.equals(metaDataPackageSuffix);
      case GenModelPackage.GEN_PACKAGE__CLASS_PACKAGE_SUFFIX:
        return CLASS_PACKAGE_SUFFIX_EDEFAULT == null ? classPackageSuffix != null : !CLASS_PACKAGE_SUFFIX_EDEFAULT.equals(classPackageSuffix);
      case GenModelPackage.GEN_PACKAGE__UTILITY_PACKAGE_SUFFIX:
        return UTILITY_PACKAGE_SUFFIX_EDEFAULT == null ? utilityPackageSuffix != null : !UTILITY_PACKAGE_SUFFIX_EDEFAULT.equals(utilityPackageSuffix);
      case GenModelPackage.GEN_PACKAGE__PROVIDER_PACKAGE_SUFFIX:
        return PROVIDER_PACKAGE_SUFFIX_EDEFAULT == null ? providerPackageSuffix != null : !PROVIDER_PACKAGE_SUFFIX_EDEFAULT.equals(providerPackageSuffix);
      case GenModelPackage.GEN_PACKAGE__PRESENTATION_PACKAGE_SUFFIX:
        return PRESENTATION_PACKAGE_SUFFIX_EDEFAULT == null ? presentationPackageSuffix != null : !PRESENTATION_PACKAGE_SUFFIX_EDEFAULT.equals(presentationPackageSuffix);
      case GenModelPackage.GEN_PACKAGE__TESTS_PACKAGE_SUFFIX:
        return TESTS_PACKAGE_SUFFIX_EDEFAULT == null ? testsPackageSuffix != null : !TESTS_PACKAGE_SUFFIX_EDEFAULT.equals(testsPackageSuffix);
      case GenModelPackage.GEN_PACKAGE__GENERATE_EXAMPLE_CLASS:
        return generateExampleClass != GENERATE_EXAMPLE_CLASS_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__LITERALS_INTERFACE:
        return literalsInterface != LITERALS_INTERFACE_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__DATA_TYPE_CONVERTERS:
        return dataTypeConverters != DATA_TYPE_CONVERTERS_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__MULTIPLE_EDITOR_PAGES:
        return multipleEditorPages != MULTIPLE_EDITOR_PAGES_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__GENERATE_MODEL_WIZARD:
        return generateModelWizard != GENERATE_MODEL_WIZARD_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__EXTENSIBLE_PROVIDER_FACTORY:
        return extensibleProviderFactory != EXTENSIBLE_PROVIDER_FACTORY_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__CHILD_CREATION_EXTENDERS:
        return childCreationExtenders != CHILD_CREATION_EXTENDERS_EDEFAULT;
      case GenModelPackage.GEN_PACKAGE__CONTENT_TYPE_IDENTIFIER:
        return CONTENT_TYPE_IDENTIFIER_EDEFAULT == null ? contentTypeIdentifier != null : !CONTENT_TYPE_IDENTIFIER_EDEFAULT.equals(contentTypeIdentifier);
      case GenModelPackage.GEN_PACKAGE__FILE_EXTENSIONS:
        return FILE_EXTENSIONS_EDEFAULT == null ? fileExtensions != null : !FILE_EXTENSIONS_EDEFAULT.equals(fileExtensions);
      case GenModelPackage.GEN_PACKAGE__ECORE_PACKAGE:
        return ecorePackage != null;
      case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
        return getGenModel() != null;
      case GenModelPackage.GEN_PACKAGE__GEN_ENUMS:
        return genEnums != null && !genEnums.isEmpty();
      case GenModelPackage.GEN_PACKAGE__GEN_DATA_TYPES:
        return genDataTypes != null && !genDataTypes.isEmpty();
      case GenModelPackage.GEN_PACKAGE__GEN_CLASSES:
        return genClasses != null && !genClasses.isEmpty();
      case GenModelPackage.GEN_PACKAGE__NESTED_GEN_PACKAGES:
        return nestedGenPackages != null && !nestedGenPackages.isEmpty();
      case GenModelPackage.GEN_PACKAGE__GEN_CLASSIFIERS:
        return !getGenClassifiers().isEmpty();
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
    result.append(" (prefix: ");
    result.append(prefix);
    result.append(", basePackage: ");
    result.append(basePackage);
    result.append(", resource: ");
    result.append(resource);
    result.append(", disposableProviderFactory: ");
    result.append(disposableProviderFactory);
    result.append(", adapterFactory: ");
    result.append(adapterFactory);
    result.append(", loadInitialization: ");
    result.append(loadInitialization);
    result.append(", interfacePackageSuffix: ");
    result.append(interfacePackageSuffix);
    result.append(", metaDataPackageSuffix: ");
    result.append(metaDataPackageSuffix);
    result.append(", classPackageSuffix: ");
    result.append(classPackageSuffix);
    result.append(", utilityPackageSuffix: ");
    result.append(utilityPackageSuffix);
    result.append(", providerPackageSuffix: ");
    result.append(providerPackageSuffix);
    result.append(", presentationPackageSuffix: ");
    result.append(presentationPackageSuffix);
    result.append(", testsPackageSuffix: ");
    result.append(testsPackageSuffix);
    result.append(", generateExampleClass: ");
    result.append(generateExampleClass);
    result.append(", literalsInterface: ");
    result.append(literalsInterface);
    result.append(", dataTypeConverters: ");
    result.append(dataTypeConverters);
    result.append(", multipleEditorPages: ");
    result.append(multipleEditorPages);
    result.append(", generateModelWizard: ");
    result.append(generateModelWizard);
    result.append(", extensibleProviderFactory: ");
    result.append(extensibleProviderFactory);
    result.append(", childCreationExtenders: ");
    result.append(childCreationExtenders);
    result.append(", contentTypeIdentifier: ");
    result.append(contentTypeIdentifier);
    result.append(", fileExtensions: ");
    result.append(fileExtensions);
    result.append(')');
    return result.toString();
  }

  @Override
  public String getName()
  {
    return getPackageName();
  }

  public String getPackageName()
  {
    return getEcorePackage().getName();
  }

  public String getInterfacePackageName()
  {
    return getQualifiedPackageName(getInterfacePackageSuffix());
  }
  
  public String getReflectionPackageName()
  {
    return getQualifiedPackageName(getMetaDataPackageSuffix());
  }

  public String getReflectionClassPackageName()
  {
    return getGenModel().isSuppressInterfaces() ? getReflectionPackageName() : getClassPackageName();
  }

  public String getClassPackageName()
  {
    return getQualifiedPackageName(getGenModel().isSuppressInterfaces() ? getInterfacePackageSuffix() : getClassPackageSuffix());
  }

  public String getUtilitiesPackageName()
  {
    return getQualifiedPackageName(getUtilityPackageSuffix());
  }

  public String getTestsPackageName()
  {
    return getQualifiedPackageName(getTestsPackageSuffix());
  }

  public String getPackageID()
  {
    return getEcorePackage().getNsPrefix();
  }

  public String getNSName()
  {
    return getEcorePackage().getNsPrefix();
  }

  public String getNSURI()
  {
    return getEcorePackage().getNsURI();
  }

  public String getBasicPackageName()
  {
    return getPrefixedName("Package");
  }

  public String getUncapPackageName()
  {
    return uncapPrefixedName(getBasicPackageName(), true);
  }

  public String getPackageInterfaceName()
  {
    String result = getBasicPackageName();
    if (!getGenModel().isSuppressInterfaces())
    {
      result = getGenModel().isSuppressEMFMetaData() ? getImplClassName(result) : getInterfaceName(result);
    }
    return result;
  }

  public String getQualifiedPackageInterfaceName()
  {
    return getGenModel().isSuppressEMFMetaData() ? getQualifiedPackageClassName() : getReflectionPackageName() + "." + getPackageInterfaceName();
  }

  public String getImportedPackageInterfaceName()
  {
    return getGenModel().getImportedName(getQualifiedPackageInterfaceName());
  }

  public String getUncapPackageInterfaceName()
  {
    return uncapPrefixedName(getPackageInterfaceName(), true);
  }

  public String getPackageClassName()
  {
    return getGenModel().isSuppressInterfaces() ? getBasicPackageName() : getImplClassName(getBasicPackageName());
  }

  public String getQualifiedPackageClassName()
  {
    return getReflectionClassPackageName() + "." + getPackageClassName();
  }

  public String getImportedPackageClassName()
  {
    return getGenModel().getImportedName(getQualifiedPackageClassName());
  }

  public String getFactoryName()
  {
    return getPrefixedName("Factory");
  }

  public String getUncapFactoryName()
  {
    return uncapPrefixedName(getFactoryName(), true);
  }

  public String getFactoryInterfaceName()
  {
    return getGenModel().isSuppressInterfaces() ? getFactoryName() :  getInterfaceName(getFactoryName());
  }

  public String getQualifiedFactoryInterfaceName()
  {
    return getReflectionPackageName() + "." + getFactoryInterfaceName();
  }

  public String getImportedFactoryInterfaceName()
  {
    return getGenModel().getImportedName(getQualifiedFactoryInterfaceName());
  }

  public String getUncapFactoryInterfaceName()
  {
    return uncapPrefixedName(getFactoryInterfaceName(), true);
  }

  public String getFactoryClassName()
  {
    return getGenModel().isSuppressInterfaces() ? getFactoryName() : getImplClassName(getFactoryName());
  }

  public String getQualifiedFactoryClassName()
  {
    return getReflectionClassPackageName() + "." + getFactoryClassName();
  }

  public String getImportedFactoryClassName()
  {
    return getGenModel().getImportedName(getQualifiedFactoryClassName());
  }

  public String getFactoryInstanceName()
  {
    return getGenModel().isSuppressEMFMetaData() ? "INSTANCE" : "eINSTANCE";
  }

  // eINSTANCE (or INSTANCE, if none) field of factory interface (or class, if none)
  //
  public String getQualifiedFactoryInstanceAccessor()
  {
    return getImportedFactoryInterfaceName() + "." + getFactoryInstanceName();
  }
 
  // Same as getQualifiedFactoryInstanceAccessor(), but with EFactory cast if the interface
  // doesn't already extend it (i.e. the case of suppressed metadata, but not interfaces).
  //
  public String getQualifiedEFactoryInstanceAccessor()
  {
    String result = getQualifiedFactoryInstanceAccessor();
    if (getGenModel().isSuppressEMFMetaData() && !getGenModel().isSuppressInterfaces())
    {
      result = "((" + getGenModel().getImportedName("org.eclipse.emf.ecore.EFactory") + ")" + result + ")";
    }
    return result;
  }

  // Same as getQualifiedFactoryInstanceAccessor(), or the underlying eINSTANCE field
  // of the class when the interface doesn't extend EFactory.
  //
  public String getQualifiedEFactoryInternalInstanceAccessor()
  {
    return 
      getGenModel().isSuppressEMFMetaData() && !getGenModel().isSuppressInterfaces() ? 
        getImportedFactoryClassName() + ".eINSTANCE" :
        getQualifiedFactoryInstanceAccessor();
  }

  public String getAdapterFactoryClassName()
  {
    return getPrefixedName("AdapterFactory");
  }

  public String getQualifiedAdapterFactoryClassName()
  {
    return getUtilitiesPackageName() + "." + getAdapterFactoryClassName();
  }

  public String getImportedAdapterFactoryClassName()
  {
    return getGenModel().getImportedName(getQualifiedAdapterFactoryClassName());
  }

  public String getUncapAdapterFactoryClassName()
  {
    return uncapPrefixedName(getAdapterFactoryClassName(), true);
  }

  public String getSwitchClassName()
  {
    return getPrefixedName("Switch");
  }

  public String getQualifiedSwitchClassName()
  {
    return getUtilitiesPackageName() + "." + getSwitchClassName();
  }

  public String getTestSuiteClassName()
  {
    return getPrefixedName("Tests");
  }
  
  public String getQualifiedTestSuiteClassName()
  {
    return getTestsPackageName() + "." + getTestSuiteClassName();
  }

  public String getImportedTestSuiteClassName()
  {
    return getGenModel().getImportedName(getQualifiedTestSuiteClassName());
  }

  public String getExampleClassName()
  {
    return getPrefixedName("Example");
  }

  public String getQualifiedExampleClassName()
  {
    return getTestsPackageName() + "." + getExampleClassName();
  }

  protected static final boolean NO_CONSTRAINTS = "true".equals(System.getProperty("EMF_NO_CONSTRAINTS")); 

  public boolean hasConstraints()
  {
    if (NO_CONSTRAINTS) return false;
    for (GenClassifier genClassifier : getGenClassifiers())
    {
      if (!genClassifier.getGenConstraints().isEmpty())
      {
        return true;
      }
    }
    return false;
  }

  public boolean hasInvariantExpressions()
  {
    if (getGenModel().getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF26_VALUE)
    {
      for (GenClass genClass : getGenClasses())
      {
        for (GenOperation genOperation : genClass.getGenOperations())
        {
          if (genOperation.isInvariant() && genOperation.hasInvariantExpression())
          {
            return true;
          }
        }
      }
    }
    return false;
  }

  
  public String getValidatorClassName()
  {
    return getPrefixedName("Validator");
  }

  public String getQualifiedValidatorClassName()
  {
    return getUtilitiesPackageName() + "." + getValidatorClassName();
  }

  public String getImportedValidatorClassName()
  {
    return getGenModel().getImportedName(getQualifiedValidatorClassName());
  }
  
  public String getImportedXMLProcessorBaseClassName()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.xmi.util.XMLProcessor");   
  }

  public String getQualifiedXMLProcessorClassName()
  {
    return getUtilitiesPackageName() + "." + getXMLProcessorClassName();
  }

  public String getXMLProcessorClassName()
  {
    return getPrefixedName("XMLProcessor");
  }

  protected String getPrefixedName(String name)
  {
    if (getPrefix() != null)
    {
      return getPrefix() + name;
    }
    return name;
  }

  public List<GenDataType> getAllGenDataTypes()
  {
    List<GenDataType> result = new ArrayList<GenDataType>();
    result.addAll(getGenEnums());
    result.addAll(getGenDataTypes());
    return result;
  }

  public List<GenClass> getOrderedGenClasses()
  {
    List<GenClass> result = new ArrayList<GenClass>();
    Set<GenClass> resultSet = new HashSet<GenClass>();

    for (Iterator<GenClass> iter = getGenClasses().iterator(); iter.hasNext(); )
    {
      List<GenClass> extendChain = new LinkedList<GenClass>();
      Set<GenClass> visited = new HashSet<GenClass>();
      for (GenClass genClass = iter.next(); genClass != null && visited.add(genClass); genClass = genClass.getBaseGenClass())
      {
        if (this == genClass.getGenPackage() && resultSet.add(genClass))
        {
          extendChain.add(0, genClass);
        }
      }
      result.addAll(extendChain);
    }
    return result;
  }

  public List<GenClassifier> getOrderedGenClassifiers()
  {
    List<GenClassifier> result = new ArrayList<GenClassifier>(getOrderedGenClasses());
    result.addAll(getGenEnums());
    result.addAll(getGenDataTypes());
    return result;
  }

  public String getClassifierID(GenClassifier genClassifier)
  {
    return genClassifier.getClassifierID();
  }

  public int getClassifierValue(GenClassifier genClassifier)
  {
    return getGenClassifiers().indexOf(genClassifier);
  }

  public int getLocalClassifierIndex(GenClassifier genClassifier)
  {
    return getEcorePackage().getEClassifiers().indexOf(genClassifier.getEcoreClassifier());
  }

  public List<GenPackage> getPackageSimpleDependencies()
  {
    if (isEcorePackage())
    {
      return new ArrayList<GenPackage>();
    }
    else
    {
      return dependencyHelper.getSimpleDependencies();
    }
  }

  public List<GenPackage> getPackageInterDependencies()
  {
    return dependencyHelper.getInterDependencies();
  }

  public List<GenPackage> getPackageLoadInterDependencies()
  {
    return dependencyHelper.getLoadInterDependencies();
  }

  public List<GenPackage> getPackageBuildInterDependencies()
  {
    return dependencyHelper.getBuildInterDependencies();
  }

  public List<GenPackage> getPackageInitializationDependencies()
  {
    if (isEcorePackage())
    {
      return new ArrayList<GenPackage>();
    }
    else
    {
      return dependencyHelper.getInitializationDependencies();
    }
  }

  public String getPackageInstanceVariable(GenPackage genPackage)
  {
    if (genPackage == this) return "this";
    if (genPackage.getEcorePackage() == EcorePackage.eINSTANCE) return "ecorePackage";

    return "the" + dependencyHelper.getUniqueName(genPackage);
  }

  private DependencyHelper dependencyHelper = null;

  private class DependencyHelper extends GenBaseImpl.UniqueNameHelper
  {
    private List<GenPackage> simpleDependencies;
    private List<GenPackage> interDependencies;
    private List<GenPackage> loadInterDependencies;
    private List<GenPackage> buildInterDependencies;
    private List<GenPackage> initializationDependencies;

    public DependencyHelper()
    {
      super(2 * (getGenModel().getGenPackages().size() + getGenModel().getUsedGenPackages().size()));

      add(GenPackageImpl.this);

      simpleDependencies = new ArrayList<GenPackage>();
      List<GenPackage> usedGenPackages = new ArrayList<GenPackage>(getGenModel().getUsedGenPackages());
      for (GenPackage usedGenPackage : usedGenPackages.toArray(new GenPackage [usedGenPackages.size()]))
      {
        usedGenPackages.removeAll(usedGenPackage.getGenModel().getUsedGenPackages());
      }

      collectPackages(simpleDependencies, usedGenPackages, 1);
      addAll(simpleDependencies);
      
      interDependencies = new UniqueEList<GenPackage>();
      collectPackages(interDependencies, getGenModel().getGenPackages(), -1);
      interDependencies.remove(GenPackageImpl.this);
      addAll(interDependencies);

      loadInterDependencies = new UniqueEList<GenPackage>();
      buildInterDependencies = new UniqueEList<GenPackage>();
      for (GenPackage genPackage : interDependencies)
      {
        if (genPackage.isLoadedInitialization())
        {
          loadInterDependencies.add(genPackage);
        }
        else
        {
          buildInterDependencies.add(genPackage);
        }
      }

      initializationDependencies = new UniqueEList<GenPackage>();
      for (Iterator<GenPackage> i = getSubGenPackages().iterator(); i.hasNext(); )
      {
        initializationDependencies.add(i.next());
      }
      for (GenClassifier genClassifier : getGenClassifiers())
      {
        EClassifier eClassifier = genClassifier.getEcoreClassifier();
        for (ETypeParameter eTypeParameter : eClassifier.getETypeParameters())
        {
          handle(eTypeParameter.getEBounds());
        }
        if (genClassifier instanceof GenClass)
        {
          GenClass genClass = (GenClass)genClassifier;
          handle(genClass.getEcoreClass().getEGenericSuperTypes());
          for (GenFeature genFeature : genClass.getGenFeatures())
          {
            handle(genFeature.getEcoreFeature().getEGenericType());
            if (genFeature.isReferenceType())
            {
              GenFeature reverseGenFeature = genFeature.getReverse();
              if (reverseGenFeature != null)
              {
                initializationDependencies.add(reverseGenFeature.getGenPackage());
              }
              for (GenFeature keyFeature : genFeature.getKeys())
              {
                initializationDependencies.add(keyFeature.getGenPackage());
              }
            }

            GenFeature delegateFeature = genFeature.getDelegateFeature();
            if (delegateFeature != null)
            {
              initializationDependencies.add(delegateFeature.getGenPackage());
            }
          }
          for (GenOperation genOperation : genClass.getGenOperations())
          {
            EOperation eOperation = genOperation.getEcoreOperation();
            for (ETypeParameter eTypeParameter : eOperation.getETypeParameters())
            {
              handle(eTypeParameter.getEBounds());
            }
            handle(eOperation.getEGenericType());
            for (GenParameter genParameter : genOperation.getGenParameters())
            {
              handle(genParameter.getEcoreParameter().getEGenericType());
            }
            handle(eOperation.getEGenericExceptions());
          }
        }
      }

      GenPackage xmlType = getGenModel().getXMLTypeGenPackage();
      if (initializationDependencies.contains(xmlType) && !xmlType.getNSURI().equals(getNSURI()))
      {
        simpleDependencies.add(xmlType);
      }

      GenPackage xmlNamespace = getGenModel().getXMLNamespaceGenPackage();
      if (initializationDependencies.contains(xmlNamespace))
      {
        simpleDependencies.add(xmlNamespace);
      }

      initializationDependencies.remove(GenPackageImpl.this);
      initializationDependencies.remove(findGenPackage(EcorePackage.eINSTANCE));

      // These are used packages for which there is a cyclic dependency.
      // So they're not really simple dependencies.
      //
      for (GenPackage genPackage : initializationDependencies)
      {
        if (simpleDependencies.contains(genPackage))
        {
          GenModel genModel = genPackage.getGenModel();
          if (genModel != getGenModel() && genModel.getAllUsedGenPackagesWithClassifiers().contains(GenPackageImpl.this))
          {
            simpleDependencies.remove(genPackage);
            interDependencies.add(genPackage);
            if (genPackage.isLoadedInitialization())
            {
              loadInterDependencies.add(genPackage);
            }
            else
            {
              buildInterDependencies.add(genPackage);
            }
          }
        }
      }
    }

    protected void handle(EList<EGenericType> eGenericTypes)
    {
      for (EGenericType eGenericType : eGenericTypes)
      {
        handle(eGenericType);
      }
    }

    protected void handle(EGenericType eGenericType)
    {
      if (eGenericType != null)
      {
        EClassifier eClassifier = eGenericType.getEClassifier();
        if (eClassifier != null)
        {
          initializationDependencies.add(findGenClassifier(eClassifier).getGenPackage());
        }
        handle(eGenericType.getETypeArguments());
        handle(eGenericType.getELowerBound());
        handle(eGenericType.getEUpperBound());
      }
    }

    @Override
    protected String getName(Object o)
    {
      return ((GenPackage)o).getBasicPackageName();
    }

    public List<GenPackage> getSimpleDependencies()
    {
      return simpleDependencies;
    }

    public List<GenPackage> getInterDependencies()
    {
      return interDependencies;
    }

    public List<GenPackage> getLoadInterDependencies()
    {
      return loadInterDependencies;
    }

    public List<GenPackage> getBuildInterDependencies()
    {
      return buildInterDependencies;
    }

    public List<GenPackage> getInitializationDependencies()
    {
      return initializationDependencies;
    }
  }
  
  /**
   * Collects into result nested GenPackages to the specified depth,
   * not counting those without classifiers (for which nothing is ever
   * generated); -1 for no limit.
   */
  protected void collectPackages(List<GenPackage> result, List<GenPackage> genPackages, int depth)
  {
    if (depth == 0) return;

    for (GenPackage genPackage : genPackages)
    {
      if (genPackage.hasClassifiers())
      {
        result.add(genPackage);
        collectPackages(result, genPackage.getNestedGenPackages(), depth - 1);
      }
      else
      {
        collectPackages(result, genPackage.getNestedGenPackages(), depth);
      }
    }
  }

  public List<GenPackage> getSubGenPackages()
  {
    List<GenPackage> result = new ArrayList<GenPackage>();
    collectPackages(result, getNestedGenPackages(), 1);
    return result;
  }

  public GenPackage getSuperGenPackage()
  {
    EObject container = eContainer();
    while (container instanceof GenPackage)
    {
      GenPackage outer = (GenPackage)container;
      if (this == outer)
      {
       throw new RuntimeException("inheritance loop at " + getPackageName());
      }

      if (outer.hasClassifiers()) return outer;
      container = outer.eContainer();
    }
    return null;
  }

  public GenPackage getRootGenPackage()
  {
    GenPackage root = this;
    while (true)
    {
      GenPackage container = root.getSuperGenPackage();
      if (container == null)
      {
        return root;
      }
      else if (this == container)
      {
        throw new RuntimeException("inheritance loop at " + getPackageName());
      }
      root = container;
    }
  }

  public boolean isLoadingInitialization()
  {
    return getRootGenPackage() == this && needsLoadInitialization(this);
  }

  public boolean isLoadedInitialization()
  {
    return needsLoadInitialization(getRootGenPackage());
  }

  private boolean needsLoadInitialization(GenPackage genPackage)
  {
    if (genPackage.isLoadInitialization()) return true;
    
    for (GenPackage subGenPackage : genPackage.getSubGenPackages())
    {
      if (needsLoadInitialization(subGenPackage)) return true;
    }
    return false;
  }
  
  public boolean isEcorePackage()
  {
    return (EcorePackage.eNS_URI.equals(getNSURI()));
  }

  public boolean hasJavaLangConflict()
  {
    return !getJavaLangConflicts().isEmpty();
  }

  public List<String> getJavaLangConflicts()
  {
    List<String> result = new ArrayList<String>();
    for (GenClass genClass : getGenClasses())
    {
      String name = genClass.getName();
      if (CodeGenUtil.isJavaDefaultType(name))
      {
        result.add(name);
      }
    }
    for (GenEnum genEnum : getGenEnums())
    {
      String name = genEnum.getName();
      if (CodeGenUtil.isJavaDefaultType(name))
      {
        result.add(name);
      }
    }
    return result;
  }

  public boolean hasInterfaceImplConflict()
  {
    Set<String> names = new HashSet<String>();
    for (GenClass genClass : getGenClasses())
    {
      names.add(genClass.getName());
    }

    for (String name : names)
    {
      if (names.contains(name + "Impl"))
      {
        return true;
      }
    }
    return false;
  }

  public List<GenClass> getAllSwitchGenClasses()
  {
    if (switchHelper == null)
    {
      switchHelper = new SwitchHelper();
    }
    return switchHelper.getAllGenClasses();
  }

  public String getClassUniqueName(GenClass genClass)
  {
    if (switchHelper == null)
    {
      switchHelper = new SwitchHelper();
    }

    if (genClass == null)
    {
      // Default (EObject) case. Need to qualify if there's already another EObject class.
      //
      return switchHelper.containsName("EObject") ? "Ecore_EObject" : "EObject";
    }

    return switchHelper.getUniqueName(genClass);
  }

  private SwitchHelper switchHelper = null;

  private class SwitchHelper extends GenBaseImpl.UniqueNameHelper
  {
    protected List<GenClass> allGenClasses = new LinkedList<GenClass>();
    protected List<GenPackage> allBaseGenPackages = new UniqueEList<GenPackage>();

    public SwitchHelper()
    {
      super(3 * getGenClasses().size());
      initLocal();
      initBases();
    }

    protected void initLocal()
    {
      // Add all classes from this package first, to guarantee they get the
      // simple names
      for (GenClass genClass : getGenClasses())
      {
        // EObject is handled by default case
        if (!genClass.isEObject() && add(genClass))
        {
          allGenClasses.add(genClass);
        }
      }
    }

    protected void initBases()
    {
      // Go through class super types to catch any from other packages
      for (GenClass genClass : getGenClasses())
      {
        if (genClass.isEObject()) continue;

        for (EClass base : genClass.getEcoreClass().getEAllSuperTypes())
        {
          GenClass baseGenClass = findGenClass(base);
          if (baseGenClass != null && !baseGenClass.isEObject() && add(baseGenClass))
          {
            allGenClasses.add(baseGenClass);
            if (baseGenClass.getGenPackage() != GenPackageImpl.this)
            {
              allBaseGenPackages.add(baseGenClass.getGenPackage());
            }
          }
        }
      }
    }

    @Override
    protected String getName(Object o)
    {
      return ((GenClassifier)o).getName();
    }

    @Override
    protected List<String> getAlternateNames(Object o)
    {
      GenClassifier genClassifier = (GenClassifier)o;
      return Collections.singletonList(genClassifier.getGenPackage().getPrefix() + "_" + genClassifier.getName());
    }

    public List<GenClass> getAllGenClasses()
    {
      return allGenClasses;
    }
  }

  public List<GenPackage> getAllValidatorBaseGenPackages()
  {
    if (validatorHelper == null)
    {
      validatorHelper = new ValidatorHelper();
    }
    return validatorHelper.getAllBaseGenPackages();
  }

  public String getValidatorPackageUniqueSafeName(GenPackage genPackage)
  {
    if (validatorHelper == null)
    {
      validatorHelper = new ValidatorHelper();
    }
    return validatorHelper.getPackageUniqueSafeName(genPackage);
  }

  private ValidatorHelper validatorHelper = null;

  private class ValidatorHelper extends UniqueNameHelper
  {
    protected List<GenPackage> allBaseGenPackages = new UniqueEList<GenPackage>();

    public ValidatorHelper()
    {
      init();
    }

    protected void init()
    {
      // Go through class super types to catch constraints from other packages
      for (GenClass genClass : getGenClasses())
      {
        if (!genClass.isEObject()) 
        {
          for (EClass base : genClass.getEcoreClass().getEAllSuperTypes())
          {
            GenClass baseGenClass = findGenClass(base);
            if (baseGenClass != null && 
                  !baseGenClass.isEObject() && 
                  baseGenClass.getGenPackage() != GenPackageImpl.this &&
                  !baseGenClass.getGenConstraints().isEmpty())
            {
              allBaseGenPackages.add(baseGenClass.getGenPackage());
            }
          }
        }
      }

      for (GenDataType genDataType : getGenDataTypes())
      {
        for (GenDataType baseType = genDataType.getBaseType(); baseType != null; baseType = baseType.getBaseType())
        {
          if (baseType.getGenPackage().hasConstraints())
          {
            allBaseGenPackages.add(baseType.getGenPackage());
          }
        }
        GenDataType itemType = genDataType.getItemType();
        if (itemType != null)
        {
          if (itemType.getGenPackage().hasConstraints())
          {
            allBaseGenPackages.add(itemType.getGenPackage());
          }
        }
        for (GenDataType memberType : genDataType.getMemberTypes())
        {
          if (memberType.getGenPackage().hasConstraints())
          {
            allBaseGenPackages.add(memberType.getGenPackage());
          }
        }
      }

      allBaseGenPackages.remove(GenPackageImpl.this);      
      
      for (Iterator<GenPackage> i = allBaseGenPackages.iterator(); i.hasNext(); )
      {
        getUniqueName(i.next());
      }
    }

    public List<GenPackage> getAllBaseGenPackages()
    {
      return allBaseGenPackages;
    }

    public String getPackageUniqueSafeName(GenPackage genPackage)
    {
      return getUniqueName(genPackage);
    }

    @Override
    protected String getName(Object o)
    {
      return safeName(uncapPrefixedName(((GenPackage)o).getPrefix(), true)); 
    }
  }

  public void initialize(EPackage ePackage)
  {
    if (getPrefixGen() == null && ePackage.getName() != null)
    {
      setPrefix(capName(ePackage.getName()));
    }

    if (getBasePackageGen() == null && EcoreUtil.getAnnotation(ePackage, GenModelPackage.eNS_URI, "basePackage") != null)
    {
      setBasePackage(EcoreUtil.getAnnotation(ePackage, GenModelPackage.eNS_URI, "basePackage"));
    }

    boolean isDifferentPackage = ePackage != getEcorePackage();
    if (isDifferentPackage)
    {
      setEcorePackage(ePackage);
  
      // Do this here because getExtendedMetaData() is used during initialization 
      // and the mappings are are populated the first time it's fetched
      // which will miss the packages we add as we initialize the tree.
      //
      if (!EcorePackage.eNS_URI.equals(ePackage.getNsURI()) && !GenModelPackage.eNS_URI.equals(ePackage.getNsURI()))
      {
        getGenModel().getExtendedMetaData().putPackage(ePackage.getNsURI(), ePackage);
      }

      if (hasExtendedMetaData(ePackage))
      {
        setResource(GenResourceKind.XML_LITERAL);
      }
      setDisposableProviderFactory(true);
    }

    int eClassIndex = 0;
    int eEnumIndex = 0;
    int eDataTypeIndex = 0;
    CLASSIFIER_LOOP:
    for (EClassifier eClassifier : ePackage.getEClassifiers())
    {
      for (GenClassifier genClassifier : getGenClassifiers())
      {
        if (genClassifier.getEcoreClassifier() == eClassifier)
        {
          if (eClassifier instanceof EClass)
          {
            ((GenClass)genClassifier).initialize((EClass)eClassifier);
            int index = getGenClasses().indexOf(genClassifier);
            if (index != eClassIndex)
            {
              getGenClasses().move(eClassIndex, index);
            }
            ++eClassIndex;
          }
          else if (eClassifier instanceof EEnum)
          {
            ((GenEnum)genClassifier).initialize((EEnum)eClassifier);
            int index = getGenEnums().indexOf(genClassifier);
            if (index != eEnumIndex)
            {
              getGenEnums().move(eEnumIndex, index);
            }
            ++eEnumIndex;
          }
          else if (eClassifier instanceof EDataType)
          {
            ((GenDataType)genClassifier).initialize((EDataType)eClassifier);
            int index = getGenDataTypes().indexOf(genClassifier);
            if (index != eDataTypeIndex)
            {
              getGenDataTypes().move(eDataTypeIndex, index);
            }
            ++eDataTypeIndex;
          }

          continue CLASSIFIER_LOOP;
        }
      }

      if (eClassifier instanceof EClass)
      {
        EClass eClass = (EClass)eClassifier;
        GenClass genClass = getGenModel().createGenClass();
        getGenClasses().add(eClassIndex++, genClass);
        genClass.initialize(eClass);

      }
      else if (eClassifier instanceof EEnum)
      {
        EEnum eEnum = (EEnum)eClassifier;
        GenEnum genEnum = getGenModel().createGenEnum();
        getGenEnums().add(eEnumIndex++, genEnum);
        genEnum.initialize(eEnum);
      }
      else if (eClassifier instanceof EDataType)
      {
        EDataType eDataType = (EDataType)eClassifier;
        GenDataType genDataType = getGenModel().createGenDataType();
        getGenDataTypes().add(eDataTypeIndex++, genDataType);
        genDataType.initialize(eDataType);
      }
    }

    int ePackageIndex = 0;
    PACKAGE_LOOP:
    for (EPackage nestedEPackage : ePackage.getESubpackages())
    {
      for (GenPackage nestedGenPackage : getNestedGenPackages())
      {
        if (nestedGenPackage.getEcorePackage() == nestedEPackage)
        {
          nestedGenPackage.initialize(nestedEPackage);
          int index = getNestedGenPackages().indexOf(nestedGenPackage);
          if (index != ePackageIndex)
          {
            getNestedGenPackages().move(ePackageIndex, index);
          }
          ++ePackageIndex;
          continue PACKAGE_LOOP;
        }
      }

      GenPackage genPackage = getGenModel().createGenPackage();
      getNestedGenPackages().add(ePackageIndex++, genPackage);
      genPackage.initialize(nestedEPackage);
    }

    if (isDifferentPackage)
    {
      boolean isBigModel = isBigModel();
      setLoadInitialization(isBigModel);
      setLiteralsInterface(!isBigModel);
    }
  }

  @Override
  protected boolean hasModelContribution()
  {
    return hasClassifiers() || !getNestedGenPackages().isEmpty();
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

      progressMonitor.beginTask("", 2 * getGenClasses().size() + 2 * getGenEnums().size() + 8 + getNestedGenPackages().size());
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingPackage_message", new Object [] { getBasicPackageName() }));

      prepareCache();

      for (Iterator iter = getGenClasses().iterator(); iter.hasNext(); )
      {
        GenClass genClass = (GenClass)iter.next();
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelClass_message", new Object [] { genClass.getFormattedName() }));
        genClass.generate(createMonitor(progressMonitor, 2));
      }

      for (Iterator iter = getGenEnums().iterator(); iter.hasNext(); )
      {
        GenEnum genEnum = (GenEnum)iter.next();
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingModelEnum_message", new Object [] { genEnum.getFormattedName() }));
        genEnum.generate(createMonitor(progressMonitor, 2));
      }

      if (hasClassifiers())
      {
        if (getGenModel().isGenerateSchema())
        {
          generateSchema(createMonitor(progressMonitor, 1));
        }

        if (isLoadingInitialization())
        {
          generatePackageSerialization(createMonitor(progressMonitor, 1));
        }

        if (!getGenModel().isSuppressEMFMetaData() && !getGenModel().isSuppressInterfaces())
        {
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_GeneratingJavaInterface_message", new Object [] { getQualifiedPackageInterfaceName() }));
          generate
            (createMonitor(progressMonitor, 1), 
             Generator.EMF_MODEL_PROJECT_STYLE,
             getGenModel().getEffectiveModelPluginVariables(),
             getGenModel().getModelDirectory(),
             getReflectionPackageName(), 
             getPackageInterfaceName(), 
             getGenModel().getPackageClassEmitter(),
             new Object [] { new Object [] { this , Boolean.TRUE, Boolean.FALSE }});
        }
    
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedPackageClassName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE,
           getGenModel().getEffectiveModelPluginVariables(),
           getGenModel().getModelDirectory(),
           getReflectionClassPackageName(),
           getPackageClassName(), 
           getGenModel().getPackageClassEmitter(),
           new Object [] 
           { 
             new Object [] 
             {  
               this , 
               getGenModel().isSuppressEMFMetaData() || getGenModel().isSuppressInterfaces() ? Boolean.TRUE: Boolean.FALSE, 
               Boolean.TRUE 
             }});
  
        if (!getGenModel().isSuppressInterfaces())
        {
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString
              ("_UI_GeneratingJavaInterface_message", new Object [] { getQualifiedFactoryInterfaceName() }));
          generate
            (createMonitor(progressMonitor, 1), 
             Generator.EMF_MODEL_PROJECT_STYLE,
             getGenModel().getEffectiveModelPluginVariables(),
             getGenModel().getModelDirectory(),
             getReflectionPackageName(), 
             getFactoryInterfaceName(), 
             getGenModel().getFactoryClassEmitter(),
             new Object [] { new Object [] { this , Boolean.TRUE, Boolean.FALSE }});
        }
  
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedFactoryClassName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE,
           getGenModel().getEffectiveModelPluginVariables(),
           getGenModel().getModelDirectory(),
           getReflectionClassPackageName(),
           getFactoryClassName(), 
           getGenModel().getFactoryClassEmitter(),
           new Object [] { new Object [] { this , getGenModel().isSuppressInterfaces() ? Boolean.TRUE : Boolean.FALSE, Boolean.TRUE }});
        
        if (getResource().getValue() == GenResourceKind.XML)
        {
          progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedXMLProcessorClassName() }));
          generate(
            createMonitor(progressMonitor, 1),
            Generator.EMF_MODEL_PROJECT_STYLE,
            getGenModel().getEffectiveModelPluginVariables(),
            getGenModel().getModelDirectory(),
            getUtilitiesPackageName(),
            getXMLProcessorClassName(),
            getGenModel().getXMLProcessorClassEmitter());
        }
        
        if (hasConstraints())
        {
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedValidatorClassName() }));
          generate
            (createMonitor(progressMonitor, 1), 
             Generator.EMF_MODEL_PROJECT_STYLE,
             getGenModel().getEffectiveModelPluginVariables(),
             getGenModel().getModelDirectory(),
             getUtilitiesPackageName(), 
             getValidatorClassName(), 
             getGenModel().getValidatorClassEmitter());
        }

        if (isAdapterFactory() && !getGenClasses().isEmpty())
        {
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedSwitchClassName() }));
          generate
            (createMonitor(progressMonitor, 1), 
             Generator.EMF_MODEL_PROJECT_STYLE,
             getGenModel().getEffectiveModelPluginVariables(),
             getGenModel().getModelDirectory(),
             getUtilitiesPackageName(), 
             getSwitchClassName(), 
             getGenModel().getSwitchClassEmitter());
  
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedAdapterFactoryClassName() }));
          generate
            (createMonitor(progressMonitor, 1), 
             Generator.EMF_MODEL_PROJECT_STYLE,
             getGenModel().getEffectiveModelPluginVariables(),
             getGenModel().getModelDirectory(),
             getUtilitiesPackageName(), 
             getAdapterFactoryClassName(), 
             getGenModel().getAdapterFactoryClassEmitter());
        }
      }

      if (getResource() != GenResourceKind.NONE_LITERAL)
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedResourceFactoryClassName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE,
           getGenModel().getEffectiveModelPluginVariables(),
           getGenModel().getModelDirectory(),
           getUtilitiesPackageName(), 
           getResourceFactoryClassName(), 
           getGenModel().getResourceFactoryClassEmitter());
  
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedResourceFactoryClassName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE,
           getGenModel().getEffectiveModelPluginVariables(),
           getGenModel().getModelDirectory(),
           getUtilitiesPackageName(), 
           getResourceClassName(), 
           getGenModel().getResourceClassEmitter());
      }

      for (Iterator nestedGenPackages = getNestedGenPackages().iterator(); nestedGenPackages.hasNext(); )
      {
        GenPackage nestedGenPackage = (GenPackage)nestedGenPackages.next();
        nestedGenPackage.generate(createMonitor(progressMonitor, 1));
      }
    }
    finally
    {
      clearCache();
      progressMonitor.done();
    }
  }
  
  /**
   * Create helpers to cache and supply information for unique naming.
   */
  public void prepareCache()
  {
    // Create helpers to cache and supply information for unique naming
    switchHelper = new SwitchHelper();
    validatorHelper = new ValidatorHelper();
    dependencyHelper = new DependencyHelper();
    annotationSourceHelper = new AnnotationSourceHelper();
  }

  /**
   * Clear the cache for unique naming information.
   */
  public void clearCache()
  {
    switchHelper = null;
    validatorHelper = null;
    dependencyHelper = null;
    annotationSourceHelper = null;
  }

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  public void generateSchema()
  {
    generateSchema(new BasicMonitor());
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
  @Override
  @SuppressWarnings("rawtypes")
  @Deprecated
  public void generateSchema(Monitor progressMonitor)
  {
    if (!canGenerateSchema()) return;
      
    if (hasClassifiers())
    {
      switch (getResource().getValue())
      {
        case GenResourceKind.XML:
        {
          generateXSD("XML");
          break;
        }
        case GenResourceKind.NONE:
        case GenResourceKind.XMI:
        {
          generateXSD("XMI");
          break;
        }
      }
    }
    
    for (Iterator i = getNestedGenPackages().iterator(); i.hasNext();)
    {
      ((GenPackage)i.next()).generateSchema(progressMonitor);
    }
  }

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  protected void generateXSD(String type)
  {
    Bundle xsdPlugin = Platform.getBundle("org.eclipse.xsd");
    if (xsdPlugin != null)
    {
      try
      {
        Class<?> theGeneratorClass = 
          xsdPlugin.loadClass
            ("org.eclipse.xsd.ecore.Ecore" + type + "SchemaBuilder");

        try
        {
          Object generator = theGeneratorClass.newInstance();

          // Set the mapper to build an XSD2EcoreMappingRoot, if available.
          //
          Bundle xsd2ecorePlugin = Platform.getBundle("org.eclipse.emf.mapping.xsd2ecore");
          if (xsd2ecorePlugin != null)
          {
            try
            {
              Class<?> theMapperInterface = 
                xsdPlugin.loadClass
                  ("org.eclipse.xsd.ecore.MapBuilder$Mapper");

              Class<?> theMapperClass = 
                xsd2ecorePlugin.loadClass
                  ("org.eclipse.emf.mapping.xsd2ecore.XSD2EcoreMapper");
  
              Object mapper = theMapperClass.newInstance();
  
              theGeneratorClass.getMethod
                ("setMapper", 
                 new Class [] { theMapperInterface }).invoke(generator, new Object [] { mapper });
            }
            catch (InstantiationException exception)
            {
              CodeGenEcorePlugin.INSTANCE.log(exception);
            }
            catch (NoSuchMethodException  exception)
            {
              CodeGenEcorePlugin.INSTANCE.log(exception);
            }
            catch (IllegalAccessException  exception)
            {
              CodeGenEcorePlugin.INSTANCE.log(exception);
            }
            catch (InvocationTargetException  exception)
            {
              CodeGenEcorePlugin.INSTANCE.log(exception);
            }
            catch (ClassNotFoundException exception)
            {
              // Ignore this soft dependency when it's not satisfied.
            }
          }

          Collection<?> result = 
            (Collection<?>)theGeneratorClass.getMethod
              ("generate", 
               new Class [] { EPackage.class }).invoke(generator, new Object [] { getEcorePackage() });

          Iterator<?> i = result.iterator();
          EObject xsdSchema = (EObject)i.next();

          ResourceSet resourceSet = new ResourceSetImpl();
          resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));
          URI uri = getEcorePackage().eResource().getURI().trimSegments(1).appendSegment(getPrefix() + type + ".xsd");
          Resource xsdSchemaResource = resourceSet.createResource(uri);
          xsdSchemaResource.getContents().add(xsdSchema);
          try
          {
            xsdSchemaResource.save(Collections.EMPTY_MAP);
          }
          catch (IOException exception)
          {
            CodeGenEcorePlugin.INSTANCE.log(exception);
          }

          if (i.hasNext())
          {
            if (!"XML".equals(type))
            {
              EObject otherXSDSchema = (EObject)i.next();
              URI otherURI = getEcorePackage().eResource().getURI().trimSegments(1).appendSegment("XMI.xsd");
              Resource otherXSDSchemaResource = resourceSet.createResource(otherURI);
              otherXSDSchemaResource.getContents().add(otherXSDSchema);
              try
              {
                otherXSDSchemaResource.save(Collections.EMPTY_MAP);
              }
              catch (IOException exception)
              {
                CodeGenEcorePlugin.INSTANCE.log(exception);
              }
            }
            if (i.hasNext())
            {
              EObject mappingRoot = (EObject)i.next();
              URI mappingURI = getEcorePackage().eResource().getURI().trimSegments(1).appendSegment(getPrefix() + type + ".xsd2ecore");
              Resource mappingResource = resourceSet.createResource(mappingURI);
              mappingResource.getContents().add(mappingRoot);
              try
              {
                mappingResource.save(Collections.EMPTY_MAP);
              }
              catch (IOException exception)
              {
                CodeGenEcorePlugin.INSTANCE.log(exception);
              }
            }
          }
        }
        catch (InstantiationException exception)
        {
          CodeGenEcorePlugin.INSTANCE.log(exception);
        }
        catch (NoSuchMethodException  exception)
        {
          CodeGenEcorePlugin.INSTANCE.log(exception);
        }
        catch (IllegalAccessException  exception)
        {
          CodeGenEcorePlugin.INSTANCE.log(exception);
        }
        catch (InvocationTargetException  exception)
        {
          CodeGenEcorePlugin.INSTANCE.log(exception);
        }
      }
      catch (ClassNotFoundException exception)
      {
        // Ignore this soft dependency when it's not satisfied.
      }
    }
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  @Deprecated
  public void generatePackageSerialization(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerate() || !isLoadingInitialization()) return;
      
      String outputFile = getGenModel().getModelDirectory() + "/" + getClassPackageName().replace('.', '/') + "/" + getSerializedPackageFilename();

      progressMonitor.beginTask("", 2);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingPackageSerialization_message", new Object [] { outputFile }));

      URI outputURI = URI.createPlatformResourceURI(outputFile);
      findOrCreateContainer
        (createMonitor(progressMonitor, 1),
         Generator.EMF_MODEL_PROJECT_STYLE,
         getGenModel().getEffectiveModelPluginVariables(),
         outputURI.trimSegments(1),
         false);

      Resource outputResource = getEcorePackage().eResource();
      ResourceSet set = outputResource.getResourceSet();

      Map oldURIs = new HashMap();

      // Set URIs of EPackage-containing resources: output resource to desired target URI, and others to package
      // namespace URIs (so cross-references will be resolved via package registry when deserialized). 
      //
      for (Iterator i = set.getResources().iterator(); i.hasNext(); )
      {
        Resource resource = (Resource)i.next();
        List contents = resource.getContents();
        
        if (!contents.isEmpty() && contents.get(0) instanceof EPackage)
        {
          EPackage ePackage = (EPackage)contents.get(0);
          oldURIs.put(resource, resource.getURI());
          resource.setURI(resource == outputResource ? outputURI : URI.createURI(ePackage.getNsURI()));
        }        
      }

      try
      {
        outputResource.save(null);
      }
      catch (IOException exception)
      {
        CodeGenEcorePlugin.INSTANCE.log(exception);
      }

      // Restore original resource URI values.
      //
      for (Iterator i = set.getResources().iterator(); i.hasNext(); )
      {
        Resource resource = (Resource)i.next();
        List contents = resource.getContents();
        
        if (!contents.isEmpty() && contents.get(0) instanceof EPackage)
        {
          resource.setURI((URI)oldURIs.get(resource));
        }        
      }

      progressMonitor.worked(1);
    }
    finally
    {
      progressMonitor.done();
    }
  }    

  //
  // EMFEdit generation
  //

  public String getProviderPackageName()
  {
    return getQualifiedPackageName(getProviderPackageSuffix());
  }

  public String getPresentationPackageName()
  {
    return getQualifiedPackageName(getPresentationPackageSuffix());
  }
  
  protected String getQualifiedPackageName(String suffix)
  {
    // The "suffix" can actually be a substitution pattern for the package name.
    //
    return hasSubstitution(suffix) ?
      addPackagePrefix(getRootGenPackage().getBasePackage(), MessageFormat.format(suffix, getPackageNameSubstitutions().toArray())) :
      addPackageSuffix(getQualifiedPackageName(), suffix);
  }

  /**
   * Returns a list of package name substitution strings, which always includes at least one element.
   * If the package is not nested, the list contains a single element, the EPackage name.
   * If the package is nested, the first element is the full package name, consisting of dot-separated EPackage names,
   * and it is followed by the individual names of the nested packages, beginning with the outer-most.  
   */
  private List<String> getPackageNameSubstitutions()
  {
    // Walk up the superpackages, collecting the package names.
    // Because empty packages are ommitted from the GenModel, we need to walk up the EPackages.
    //
    List<String> names = new ArrayList<String>();
    StringBuilder full = new StringBuilder();

    for (EPackage ePackage = getEcorePackage(); ePackage != null; ePackage = ePackage.getESuperPackage())
    {
      String name = safeName(ePackage.getName());
      names.add(0, name);
      if (full.length() > 0)
      {
        full.insert(0, '.');
      }
      full.insert(0, name);
    }

    if (names.size() > 1)
    {
      names.add(0, full.toString());
    }
    return names;
  }

  public String getQualifiedPackageName()
  {
    return addPackagePrefix(getBasePackage(), safeName(getPackageName()));
  }

//   public String getExtendedItemProviderClassName()
//   {
//     return getPrefixedName("ItemProviderAdapter");
//   }

  public String getItemProviderAdapterFactoryClassName()
  {
    return getPrefixedName("ItemProviderAdapterFactory");
  }

  public String getQualifiedItemProviderAdapterFactoryClassName()
  {
    return getProviderPackageName() + "." + getItemProviderAdapterFactoryClassName();
  }

  public String getImportedItemProviderAdapterFactoryClassName()
  {
    return getGenModel().getImportedName(getQualifiedItemProviderAdapterFactoryClassName());
  }

  public String getEditorClassName()
  {
    return getPrefixedName("Editor");
  }

  public String getQualifiedEditorClassName()
  {
    return getPresentationPackageName() + "." + getEditorClassName();
  }

  public String getImportedEditorClassName()
  {
    return getGenModel().getImportedName(getQualifiedEditorClassName());
  }

  public String getModelWizardClassName()
  {
    return getPrefixedName("ModelWizard");
  }

  public String getQualifiedModelWizardClassName()
  {
    return getPresentationPackageName() + "." + getModelWizardClassName();
  }

  public String getImportedModelWizardClassName()
  {
    return getGenModel().getImportedName(getQualifiedModelWizardClassName());
  }

  public String getActionBarContributorClassName()
  {
    return getPrefixedName("ActionBarContributor");
  }

  public String getQualifiedActionBarContributorClassName()
  {
    return getPresentationPackageName() + "." + getActionBarContributorClassName();
  }

  public String getImportedActionBarContributorClassName()
  {
    return getGenModel().getImportedName(getQualifiedActionBarContributorClassName());
  }

  public String getAdapterFactoryDelegateName(GenPackage genDelegate)
  {
    String result = genDelegate.getAdapterFactoryClassName();
    if (!result.equals(genDelegate.getImportedAdapterFactoryClassName()))
    {
      int count = getAdapterDelegatePackages().indexOf(genDelegate);
      result += "_" + count;
    }
    return result;
  }

  public String getUncapAdapterFactoryDelegateName(GenPackage genDelegate)
  {
    return uncapPrefixedName(getAdapterFactoryDelegateName(genDelegate), true);
  }

  //
  //TBD rethink where exactly some of the following methods belong (GenModel or GenPackage)
  //

  public String getEditPluginClassName()
  {
    // return getPrefixedName("EditPlugin");
    return getGenModel().getEditPluginClassName();
  }

  public String getQualifiedEditPluginClassName()
  {
    // return getProviderPackageName() + "." + getEditPluginClassName();
    return getGenModel().getQualifiedEditPluginClassName();
  }

  public String getImportedEditPluginClassName()
  {
    return getGenModel().getImportedName(getQualifiedEditPluginClassName());
  }

  public String getEditorPluginClassName()
  {
    // return getPrefixedName("EditorPlugin");
    return getGenModel().getEditorPluginClassName();
  }

  public String getQualifiedEditorPluginClassName()
  {
    return getGenModel().getQualifiedEditorPluginClassName();
    // return getPresentationPackageName() + "." + getEditorPluginClassName();
  }

  public String getImportedEditorPluginClassName()
  {
    return getGenModel().getImportedName(getQualifiedEditorPluginClassName());
  }

  public String getCapPackageID()
  {
    return capName(getPackageID());
  }

  public List<GenFeature> getAllGenFeatures()
  {
    List<GenFeature> result = new ArrayList<GenFeature>();

    // Any features that delegate to features in this package.
    //
    List<GenFeature> delegated = new ArrayList<GenFeature>();

    for (GenClass genClass : getGenClasses())
    {
      for (GenFeature genFeature : genClass.getGenFeatures())
      {
        result.add(genFeature);
        delegated.addAll(genFeature.getDelegatedFeatures());
      }
    }

    // If there are delegated features, add only those that aren't already in this package to the end of the list.
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

    // We need to screen out duplicates in the unlikely event that we have two
    //  features with the same class-qualified name. We'll only generate one property
    //  string in that case and let the user add the second one manually, if necessary.
    //
    Set<String> noDupHash = new HashSet<String>();
    for (GenClass genClass : getGenClasses())
    {
      for (GenFeature genFeature : genClass.getGenFeatures())
      {
        if (noDupHash.add(genFeature.getGenClass().getName() + genFeature.getName()))
        {
          result.add(genFeature);
        }
      }
    }

    return result;
  }

  public List<GenPackage> getAdapterDelegatePackages()
  {
    List<GenPackage> result = new ArrayList<GenPackage>();

    //TBD return (user selected subset?) of referenced packages that contain super classes of classes in this package.

    return result;
  }

  public List<GenClass> getAdapterDelegateSuperClasses()
  {
    List<GenClass> result = new ArrayList<GenClass>();

    //TBD return super classes of classes in this package that come from a delegate package.

    return result;
  }

  public boolean hasStatefulProvider()
  {
    for (GenClass genClass : getGenClasses())
    {
      if (!genClass.isAbstract() && genClass.getProvider() == GenProviderKind.STATEFUL_LITERAL)
      {
        return true;
      }
    }
    return false;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @SuppressWarnings("rawtypes")
  @Deprecated
  @Override
  public void generateEdit(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerateEdit()) return;

      progressMonitor.beginTask("", getGenClasses().size() + getNestedGenPackages().size() + 2); //TBD 
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingItemProvidersForPackage_message", new Object [] { getBasicPackageName() }));

      if (!getGenClasses().isEmpty())
      {
        for (Iterator iter = getGenClasses().iterator(); iter.hasNext(); )
        {
          GenClass genClass = (GenClass)iter.next();
          if (genClass.getProvider() == GenProviderKind.NONE_LITERAL) continue;
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_GeneratingItemProviderFor_message", new Object [] { genClass.getFormattedName() }));
          genClass.generateEdit(createMonitor(progressMonitor, 1));
        }
  
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", 
              new Object [] { getQualifiedItemProviderAdapterFactoryClassName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_EDIT_PROJECT_STYLE,
           getGenModel().getEffectiveModelPluginVariables(),
           getGenModel().getEditDirectory(),
           getProviderPackageName(), 
           getItemProviderAdapterFactoryClassName(), 
           getGenModel().getItemProviderAdapterFactoryEmitter());
      }

      for (Iterator nestedGenPackages = getNestedGenPackages().iterator(); nestedGenPackages.hasNext(); )
      {
        GenPackage nestedGenPackage = (GenPackage)nestedGenPackages.next();
        nestedGenPackage.generateEdit(createMonitor(progressMonitor, 1));
      }
    }
    finally
    {
      progressMonitor.done();
    }
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

      progressMonitor.beginTask("", 7); //TBD 
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingEditorForPackage_message", new Object [] { getBasicPackageName() }));

      if (hasConcreteClasses())
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedEditorClassName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_EDITOR_PROJECT_STYLE,
           getGenModel().getEffectiveModelPluginVariables(),
           getGenModel().getEditorDirectory(),
           getPresentationPackageName(), 
           getEditorClassName(), 
           getGenModel().getEditorEmitter());
  
        progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedModelWizardClassName() }));
      generate
        (createMonitor(progressMonitor, 1), 
         Generator.EMF_EDITOR_PROJECT_STYLE,
         getGenModel().getEffectiveModelPluginVariables(),
         getGenModel().getEditorDirectory(),
         getPresentationPackageName(), 
         getModelWizardClassName(), 
         getGenModel().getModelWizardEmitter());

        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedActionBarContributorClassName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_EDITOR_PROJECT_STYLE,
           getGenModel().getEffectiveModelPluginVariables(),
           getGenModel().getEditorDirectory(),
           getPresentationPackageName(), 
           getActionBarContributorClassName(), 
           getGenModel().getActionBarContributorEmitter());
  
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingModelIcon_message", new Object [] { getModelIconFileName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_EDIT_PROJECT_STYLE, 
           getGenModel().getEffectiveModelPluginVariables(),
           getModelIconFileName(), 
           ((GenModelImpl)getGenModel()).getModelGIFEmitter(),
           getPrefix());

        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingModelWizardIcon_message", new Object [] { getModelWizardIconFileName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_EDIT_PROJECT_STYLE, 
           getGenModel().getEffectiveModelPluginVariables(),
           getModelWizardIconFileName(), 
           ((GenModelImpl)getGenModel()).getModelWizardGIFEmitter(),
           getPrefix());
      }

      for (Iterator nestedGenPackages = getNestedGenPackages().iterator(); nestedGenPackages.hasNext(); )
      {
        GenPackage nestedGenPackage = (GenPackage)nestedGenPackages.next();
        nestedGenPackage.generateEditor(createMonitor(progressMonitor, 1));
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }

  public boolean hasTests()
  {
    for (GenClass genClass : getGenClasses())
    {
      if (genClass.hasTests())
      {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean canGenerateTests()
  {
    return getGenModel().canGenerateTests() && hasClassifiers(true);
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
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

      progressMonitor.beginTask("", getGenClasses().size() + getNestedGenPackages().size());

      if (hasClassifiers())
      {
        progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString(
          "_UI_GeneratingJavaClass_message",
          new Object []{ getQualifiedTestSuiteClassName() }));
          
        generate(
          createMonitor(progressMonitor, 1),
          Generator.EMF_TESTS_PROJECT_STYLE,
          getGenModel().getEffectiveModelPluginVariables(),
          getGenModel().getTestsDirectory(),
          getTestsPackageName(),
          getTestSuiteClassName(),
          getGenModel().getPackageTestSuiteEmitter());
  
        if (isGenerateExampleClass())
        {
          generate(
            createMonitor(progressMonitor, 1),
            Generator.EMF_TESTS_PROJECT_STYLE,
            getGenModel().getEffectiveModelPluginVariables(),
            getGenModel().getTestsDirectory(),
            getTestsPackageName(),
            getExampleClassName(),
            getGenModel().getPackageExampleEmitter());
        }
          
        for (Iterator genClasses = getGenClasses().iterator(); genClasses.hasNext();)
        {
          GenClass genClass = (GenClass)genClasses.next();
          progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString(
            "_UI_Generating_message",
            new Object []{ genClass.getFormattedName() }));
          genClass.generateTests(createMonitor(progressMonitor, 1));
        }
      }

      for (Iterator nestedGenPackages = getNestedGenPackages().iterator(); nestedGenPackages.hasNext();)
      {
        ((GenPackage)nestedGenPackages.next()).generateTests(createMonitor(progressMonitor, 1));
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }

  public String getModelIconFileName()
  {
    return getGenModel().getEditorIconsDirectory() + "/full/obj16/" + getPrefix() + "ModelFile.gif";
  }

  public String getModelWizardIconFileName()
  {
    return getGenModel().getEditorIconsDirectory() + "/full/wizban/New" + getPrefix() + ".gif";
  }

  public boolean hasClassifiers()
  {
    return getGenClasses().size() + getGenEnums().size() + getGenDataTypes().size() != 0;
  }
  
  public boolean hasClassifiers(boolean traverseNestedPackages)
  {
    if (hasClassifiers())
    {
      return true;
    }
    else if (traverseNestedPackages)
    {
      for (GenPackage genPackage : getNestedGenPackages())
      {
        if (genPackage.hasClassifiers(true))
        {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public String getModelInfo()
  {
    StringBuffer result = new StringBuffer();
    appendModelSetting(result, "kind", "package");
    appendAnnotationInfo(result, getEcorePackage());
    return result.toString().trim();
  }

  public boolean reconcile(GenPackage oldGenPackageVersion)
  {
    if (getEcorePackage().getNsURI() == null ? 
          getEcorePackage().getName().equals(oldGenPackageVersion.getEcorePackage().getName()) :
          getEcorePackage().getNsURI().equals(oldGenPackageVersion.getEcorePackage().getNsURI()))
    {
      for (GenClassifier genClassifier : getGenClassifiers())
      {
        for (GenClassifier oldGenClassifierVersion : oldGenPackageVersion.getGenClassifiers())
        {
          if (genClassifier instanceof GenEnum)
          {
            if (oldGenClassifierVersion instanceof GenEnum &&
                  ((GenEnum)genClassifier).reconcile((GenEnum)oldGenClassifierVersion))
            {
              break;
            }
          }
          else if (genClassifier instanceof GenDataType)
          {
            if (oldGenClassifierVersion instanceof GenDataType && 
                  !(oldGenClassifierVersion instanceof GenEnum) &&
                  ((GenDataType)genClassifier).reconcile((GenDataType)oldGenClassifierVersion))
            {
              break;
            }
          }
          else if (genClassifier instanceof GenClass)
          {
            if (oldGenClassifierVersion instanceof GenClass &&
                  ((GenClass)genClassifier).reconcile((GenClass)oldGenClassifierVersion))
            {
              break;
            }
          }
        }
      }

      for (GenPackage nestedGenPackage : getNestedGenPackages())
      {
        for (GenPackage oldNestedGenPackageVersion : oldGenPackageVersion.getNestedGenPackages())
        {
          if (nestedGenPackage.reconcile(oldNestedGenPackageVersion))
          {
            break;
          }
        }
      }
      reconcileSettings(oldGenPackageVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void reconcileSettings(GenPackage oldGenPackageVersion)
  {
    setPrefix(oldGenPackageVersion.getPrefix());
    setBasePackage(oldGenPackageVersion.getBasePackage());
    setResource(oldGenPackageVersion.getResource());
    setAdapterFactory(oldGenPackageVersion.isAdapterFactory());
    setLoadInitialization(oldGenPackageVersion.isLoadInitialization());
    setDisposableProviderFactory(oldGenPackageVersion.isDisposableProviderFactory());
    
    setInterfacePackageSuffix(oldGenPackageVersion.getInterfacePackageSuffix());
    setMetaDataPackageSuffix(oldGenPackageVersion.getMetaDataPackageSuffix());
    setClassPackageSuffix(oldGenPackageVersion.getClassPackageSuffix());
    setUtilityPackageSuffix(oldGenPackageVersion.getUtilityPackageSuffix());
    setProviderPackageSuffix(oldGenPackageVersion.getProviderPackageSuffix());
    setPresentationPackageSuffix(oldGenPackageVersion.getPresentationPackageSuffix());
    setTestsPackageSuffix(oldGenPackageVersion.getTestsPackageSuffix());
    setGenerateExampleClass(oldGenPackageVersion.isGenerateExampleClass());
    setLiteralsInterface(oldGenPackageVersion.isLiteralsInterface());
    setDataTypeConverters(oldGenPackageVersion.isDataTypeConverters());
    setMultipleEditorPages(oldGenPackageVersion.isMultipleEditorPages());
    setGenerateModelWizard(oldGenPackageVersion.isGenerateModelWizard());
    
    setExtensibleProviderFactory(oldGenPackageVersion.isExtensibleProviderFactory());
    setChildCreationExtenders(oldGenPackageVersion.isChildCreationExtenders());
    
    setContentTypeIdentifier(oldGenPackageVersion.getContentTypeIdentifier());

    if (oldGenPackageVersion.eIsSet(GenModelPackage.Literals.GEN_PACKAGE__FILE_EXTENSIONS))
    {
      setFileExtensions(oldGenPackageVersion.getFileExtensions());
    }

    reconcileGenAnnotations(oldGenPackageVersion);
  }

  public boolean reconcile()
  {
    EPackage ePackage = getEcorePackage();
    if (ePackage == null || ePackage.eIsProxy() || ePackage.eResource() == null)
    {
      return false;
    }
    else
    {
      for (Iterator<GenClass> i = getGenClasses().iterator(); i.hasNext(); )
      {
        GenClass genClass = i.next();
        if (!genClass.reconcile())
        {
          i.remove();
        }
      }
      
      for (Iterator<GenEnum> i = getGenEnums().iterator(); i.hasNext(); )
      {
        GenEnum genEnum = i.next();
        if (!genEnum.reconcile())
        {
          i.remove();
        }
      }
      
      for (Iterator<GenDataType> i = getGenDataTypes().iterator(); i.hasNext(); )
      {
        GenDataType genDataType = i.next();
        if (!genDataType.reconcile())
        {
          i.remove();
        }
      }

      for (Iterator<GenPackage> i = getNestedGenPackages().iterator(); i.hasNext(); )
      {
        GenPackage nestedGenPackage = i.next();
        if (!nestedGenPackage.reconcile())
        {
          i.remove();
        }
      }

      initialize(ePackage);
      return true;
    }
  }

  public List<String> getAnnotationSources()
  {
    GenModel genModel = getGenModel();
    List<String> result = new UniqueEList<String>();
    EPackage ePackage = getEcorePackage();
    for (TreeIterator<?> i = ePackage.eAllContents(); i.hasNext(); )
    {
      Object object = i.next();
      if (object instanceof EPackage)
      {
        i.prune();
      }
      else if (object instanceof EAnnotation)
      {
        EAnnotation eAnnotation = (EAnnotation)object;
        String source = eAnnotation.getSource();
        if (!genModel.isSuppressedAnnotation(source))
        {
          result.add(source);
        }
        i.prune();
      }
    }
    return result;
  }

  private AnnotationSourceHelper annotationSourceHelper = null;

  private class AnnotationSourceHelper extends GenBaseImpl.UniqueNameHelper
  {
    public AnnotationSourceHelper()
    {
      super(0);
      for (EAnnotation eAnnotation : getAllAnnotations())
      {
        getUniqueName(eAnnotation.getSource());
      }
    }

    @Override
    protected String getName(Object o)
    {
      String result = (String)o;
      if (result == null)
      {
        return "Null";
      }
      else
      {
        int index = result.lastIndexOf("/");
        if (index == result.length() - 1)
        {
          result = result.substring(0, index);
          index = result.lastIndexOf("/");
        }
        if (index != -1)
        {
          result = result.substring(index + 1);
        }
        index = result.indexOf(".");
        if (index != -1)
        {
          result = result.substring(0, index);
        }
        return capName(CodeGenUtil.validJavaIdentifier(result));
      }
    }
  }

  public String getAnnotationSourceIdentifier(String annotationSource)
  {
    return annotationSourceHelper.getUniqueName(annotationSource);
  }

  public List<EAnnotation> getAllAnnotations()
  {
    List<EAnnotation> result = new UniqueEList<EAnnotation>();
    EPackage ePackage = getEcorePackage();
    for (TreeIterator<?> i = ePackage.eAllContents(); i.hasNext(); )
    {
      Object object = i.next();
      if (object instanceof EPackage)
      {
        i.prune();
      }
      else if (object instanceof EAnnotation)
      {
        result.add((EAnnotation)object);
        i.prune();
      }
    }
    return result;
  }

  public String getAnnotatedModelElementAccessor(EAnnotation eAnnotation)
  {
    final boolean useGenerics = getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    EModelElement eModelElement = eAnnotation.getEModelElement();
    String result =
        new EcoreSwitch<String>()
        {
          @Override
          public String caseEPackage(EPackage ePackage)
          {
            return "this";
          }

          @Override
          public String caseEClassifier(EClassifier eClassifier)
          {
            return findGenClassifier(eClassifier).getClassifierInstanceName();
          }

          @Override
          public String caseEStructuralFeature(EStructuralFeature eStructuralFeature)
          {
            return "get" + findGenFeature(eStructuralFeature).getFeatureAccessorName() + "()";
          }

          @Override
          public String caseEOperation(EOperation eOperation)
          {
            if (getGenModel().isOperationReflection())
            {
              return "get" + findGenOperation(eOperation).getOperationAccessorName() + "()";
            }
            else
            {
              EClass eClass = eOperation.getEContainingClass();
              return (useGenerics ? "" : "(" + getGenModel().getImportedName("org.eclipse.emf.ecore.EOperation") + ")")
                + caseEClassifier(eClass) + ".getEOperations().get(" + eClass.getEOperations().indexOf(eOperation) + ")";
            }
        }

          @Override
          public String caseEEnumLiteral(EEnumLiteral eEnumLiteral)
          {
            EEnum eEnum = eEnumLiteral.getEEnum();
            return 
              (useGenerics ? "" : "(" + getGenModel().getImportedName("org.eclipse.emf.ecore.EEnumLiteral") +  ")") + 
                caseEClassifier(eEnum) + 
                ".getELiterals().get(" +
                eEnum.getELiterals().indexOf(eEnumLiteral) +
                ")";
          }

          @Override
          public String caseEParameter(EParameter eParameter)
          {
            EOperation eOperation = eParameter.getEOperation();
            return 
              (useGenerics ? "" : "(" +  getGenModel().getImportedName("org.eclipse.emf.ecore.EParameter") +  ")") + 
                "(" + 
                caseEOperation(eOperation) +
                ").getEParameters().get(" +
                eOperation.getEParameters().indexOf(eParameter) +
                ")";
          }
        }.doSwitch(eModelElement);

    return result;
  }

  public List<EAnnotation> getAllNestedAnnotations(EAnnotation eAnnotation)
  {
    List<EAnnotation> result = new ArrayList<EAnnotation>();
    for (TreeIterator<?> i = eAnnotation.eAllContents(); i.hasNext();)
    {
      Object content = i.next();
      if (content instanceof EAnnotation)
      {
        result.add((EAnnotation)content);
      }
      else
      {
        i.prune();
      }
    }
    return result;
  }
  
  public List<AnnotationReferenceData> getReferenceData(EAnnotation eAnnotation)
  {
    ArrayList<AnnotationReferenceData> result = new ArrayList<AnnotationReferenceData>();
    if (getGenModel().getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF25_VALUE)
    {
      for (EObject reference : eAnnotation.getReferences())
      {
        InternalEObject internalEObject = (InternalEObject)reference;
        List<String> uriFragmentPath = new ArrayList<String>();
        for (InternalEObject container = internalEObject.eInternalContainer(); container != null; container = internalEObject.eInternalContainer())
        {
          uriFragmentPath.add(container.eURIFragmentSegment(internalEObject.eContainingFeature(), internalEObject));
          internalEObject = container;
        }
        if (internalEObject instanceof EPackage)
        {
          GenPackage genPackage = findGenPackage((EPackage)internalEObject);
          if (genPackage != null)
          {
            StringBuilder fragment = new StringBuilder("/");
            for (int i = uriFragmentPath.size() - 1; i >= 0; --i)
            {
              fragment.append('/');
              fragment.append(uriFragmentPath.get(i));
            }
            result.add(new AnnotationReferenceData(genPackage, fragment.toString()));
          }
        }
      }
    }
    return result;
  }

  public String getResourceClassName()
  {
    return getImplClassName(getPrefixedName("Resource"));
  }

  public String getQualifiedResourceClassName()
  {
    return getUtilitiesPackageName() + "." + getResourceClassName();
  }

  public String getImportedResourceClassName()
  {
    return getGenModel().getImportedName(getQualifiedResourceClassName());
  }

  public String getImportedResourceBaseClassName()
  {
    switch (getResource().getValue())
    {
      case GenResourceKind.XML:
        return getGenModel().getImportedName("org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl");
      case GenResourceKind.XMI:
        return getGenModel().getImportedName("org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl");
      default:
        return getGenModel().getImportedName("org.eclipse.emf.ecore.resource.impl.ResourceImpl");
    }
  }

  public String getResourceFactoryClassName()
  {
    return getImplClassName(getPrefixedName("ResourceFactory"));
  }

  public String getQualifiedResourceFactoryClassName()
  {
    return getUtilitiesPackageName() + "." + getResourceFactoryClassName();
  }

  public String getImportedResourceFactoryClassName()
  {
    return getGenModel().getImportedName(getQualifiedResourceFactoryClassName());
  }

  public String getImportedResourceFactoryBaseClassName()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl");
  }

  public boolean hasDocumentRoot()
  {
    if (getResource() != GenResourceKind.XML_LITERAL)
    {
      return false;
    }

    return getExtendedMetaData().getDocumentRoot(getEcorePackage()) != null;
  }
  
  public GenClass getDocumentRoot()
  {
    EClass documentRoot = getExtendedMetaData().getDocumentRoot(getEcorePackage());
    return documentRoot == null ? null : findGenClass(documentRoot);
  }

  public boolean hasExtendedMetaData()
  {
    if (getResource() != GenResourceKind.XML_LITERAL)
    {
      return false;
    }
    return hasExtendedMetaData(getEcorePackage());
  }

  protected static boolean hasExtendedMetaData(EPackage ePackage)
  {
    List<EPackage> ePackages = new UniqueEList<EPackage>();
    ePackages.add(ePackage);
    for (int i = 0; i < ePackages.size(); ++i)
    {
      for (TreeIterator<EObject> j = ePackages.get(i).eAllContents(); j.hasNext(); )
      {
        EObject eObject = j.next();
        if (eObject instanceof EPackage || eObject instanceof EDataType)
        {
          j.prune();
        }
        else if (eObject instanceof EAnnotation)
        {
          EAnnotation eAnnotation = (EAnnotation)eObject;
          String source = eAnnotation.getSource();
          if (ExtendedMetaData.ANNOTATION_URI.equals(source))
          {
            return true;
          }
        }
        for (EObject eCrossReference : eObject.eCrossReferences())
        {
          if (eCrossReference instanceof EClass)
          {
            ePackages.add(((EClassifier)eCrossReference).getEPackage());
          }
        }
      }
    }

    return false;
  }

  public boolean hasXMLMap()
  {
    if (getResource() != GenResourceKind.XML_LITERAL)
    {
      return false;
    }

    EAnnotation eAnnotation = getEcorePackage().getEAnnotation(XSD2ECORE_URI);
    return eAnnotation != null;
  }

  public boolean hasTargetNamespace()
  {
    if (hasExtendedMetaData())
    {
      return getExtendedMetaData().getNamespace(getEcorePackage()) != null;
    }

    EAnnotation eAnnotation = getEcorePackage().getEAnnotation(XSD2ECORE_URI);
    return eAnnotation == null || eAnnotation.getDetails().get("targetNamespace") != null;
  }

  public String getSerializedPackageFilename()
  {
    return getName() + ".ecore";
  }
  
  protected boolean isBigModel()
  {
    int classes = getGenClasses().size();
    int supers = 0;
    int features = 0;
    int operations = 0;
    int parameters = 0;
    int exceptions = 0;
    
    for (GenClass genClass : getGenClasses())
    {
      supers += genClass.getEcoreClass().getESuperTypes().size();
      features += genClass.getGenFeatures().size();
      operations += genClass.getGenOperations().size();
      
      for (GenOperation genOperation : genClass.getGenOperations())
      {
        parameters += genOperation.getGenParameters().size();
        exceptions += genOperation.getEcoreOperation().getEExceptions().size();
      }
    }

    int enums = getGenEnums().size();
    int literals = 0;

    for (GenEnum genEnum : getGenEnums())
    {
      literals += genEnum.getGenEnumLiterals().size();
    }

    int datatypes = getGenDataTypes().size();

    return (classes + supers + features + operations + parameters + exceptions + enums + literals + datatypes) > 500;
  }
  
  public List<String> getProviderSupportedTypes()
  {
    List<String> result = new ArrayList<String>();
    result.add("org.eclipse.emf.edit.provider.IEditingDomainItemProvider");
    result.add("org.eclipse.emf.edit.provider.IStructuredItemContentProvider");
    result.add("org.eclipse.emf.edit.provider.ITreeItemContentProvider");
    result.add("org.eclipse.emf.edit.provider.IItemLabelProvider");
    result.add("org.eclipse.emf.edit.provider.IItemPropertySource");
    if (getGenModel().isTableProviders())
    {
      result.add("org.eclipse.emf.edit.provider.ITableItemLabelProvider");
      if (getGenModel().isColorProviders())
      {
        result.add("org.eclipse.emf.edit.provider.ITableItemColorProvider");
      }
      if (getGenModel().isFontProviders())
      {
        result.add("org.eclipse.emf.edit.provider.ITableItemFontProvider");
      }
    }
    if (getGenModel().isColorProviders())
    {
      result.add("org.eclipse.emf.edit.provider.IItemColorProvider");
    }
    if (getGenModel().isFontProviders())
    {
      result.add("org.eclipse.emf.edit.provider.IItemFontProvider");
    }
    if (getGenModel().isStyleProviders())
    {
      result.add("org.eclipse.emf.edit.provider.IItemStyledLabelProvider");
    }
    return result;
  }

  public GenClass getRootClass()
  {
    GenFeature rootFeature = getRootFeature();
    if (rootFeature == null)
    {
      for (GenClass genClass : getGenClasses())
      {
        if (!genClass.isAbstract() && !genClass.isMapEntry())
        {
          return genClass;
        }
      }
      return null;
    }
    else
    {
      return rootFeature.getTypeGenClass();
    }
  }

  public GenFeature getRootFeature()
  {
    EClass documentRoot = getExtendedMetaData().getDocumentRoot(getEcorePackage());
    if (documentRoot != null)
    {
      for (EStructuralFeature eStructuralFeature : getExtendedMetaData().getAllElements(documentRoot))
      {
        if (eStructuralFeature instanceof EReference && 
              !((EClass)eStructuralFeature.getEType()).isAbstract() && 
              eStructuralFeature.isChangeable())
        {
          return findGenFeature(eStructuralFeature);
        }
      }
    }
    return null;
  }
    
  public boolean hasConcreteClasses()
  {
    for (GenClass genClass : getGenClasses())
    {
      if (!genClass.isAbstract())
      {
        return true;
      }
    }
    return false;
  }

  public Map<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> getExtendedChildCreationData()
  {
    GenModel genModel = getGenModel();
    Map<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> result = new LinkedHashMap<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>>();
    List<GenPackage> allUsedGenPackagesWithClassifiers = getGenModel().getAllUsedGenPackagesWithClassifiers();
    for (GenPackage genPackage : allUsedGenPackagesWithClassifiers)
    {
      for (GenClass genClass : genPackage.getGenClasses())
      {
        List<GenClass.ChildCreationData> allChildCreationData = genClass.getChildCreationData(genModel);
        for (GenClass.ChildCreationData childCreationData : allChildCreationData)
        {
          // It belongs to this package if the delegate feature is from this package,
          // or the class is from this package and either there is no delegate feature or the delegate feature is from some used package.
          //
          if (childCreationData.delegatedFeature != null && childCreationData.delegatedFeature.getGenPackage() == this ||
                childCreationData.createClassifier.getGenPackage() == this && 
                  (childCreationData.delegatedFeature == null || allUsedGenPackagesWithClassifiers.contains(childCreationData.delegatedFeature.getGenPackage())))
          {
            GenClass featureClass = childCreationData.createFeature.getGenClass();
            GenPackage featureGenPackage = featureClass.getGenPackage();

            // If the GenClass for this creation data is from a different package...
            //
            if (genPackage != featureGenPackage)
            {
              // And its base GenClass doesn't contain this creation feature...
              //
              GenClass baseGenClass = genClass.getBaseGenClass();
              if (baseGenClass != null && !baseGenClass.getAllGenFeatures().contains(childCreationData.createFeature))
              {
                // Then we're getting this child from multiple inheritance and we'd better produce a specialized creation factory for this GenClass's package.
                //
                Map<GenClass, List<GenClass.ChildCreationData>> map = result.get(genPackage);
                if (map == null)
                {
                  result.put(genPackage, map = new LinkedHashMap<GenClass, List<GenClass.ChildCreationData>>());
                }
                List<GenClass.ChildCreationData> data = map.get(featureClass);
                if (data == null)
                {
                  map.put(featureClass, data = new UniqueEList<GenClass.ChildCreationData>());
                }
                data.add(childCreationData);
              }
            }

            Map<GenClass, List<GenClass.ChildCreationData>> map = result.get(featureGenPackage);
            if (map == null)
            {
              result.put(featureGenPackage, map = new LinkedHashMap<GenClass, List<GenClass.ChildCreationData>>());
            }
            List<GenClass.ChildCreationData> data = map.get(featureClass);
            if (data == null)
            {
              map.put(featureClass, data = new UniqueEList<GenClass.ChildCreationData>());
            }
            data.add(childCreationData);
          }
        }
      }
    }
    return result;
  }

  public String getChildCreationExtenderName(GenPackage genPackage)
  {
    UniqueNameHelper uniqueNameHelper = 
      new UniqueNameHelper()
      {
        @Override
        protected String getName(Object o)
        {
          return ((GenPackageImpl)o).getPrefixedName("ChildCreationExtender");
        }
      };
    for (GenPackage otherGenPackage : getGenModel().getAllUsedGenPackagesWithClassifiers())
    {
      uniqueNameHelper.add(otherGenPackage);
    }
    return uniqueNameHelper.getName(genPackage);
  }

  public boolean isContentType()
  {
    return getGenModel().getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE && !isBlank(getContentTypeIdentifier());
  }

  public String getQualifiedContentTypeIdentifier()
  {
    String rawID = getContentTypeIdentifier();
    String pluginID = getGenModel().getModelPluginID();
    return isBlank(pluginID) || isBlank(rawID) || rawID.indexOf('.') != -1 ? rawID : pluginID + "." + rawID;
  }

  public boolean isXMIResource()
  {
    return getResource() == GenResourceKind.NONE_LITERAL || getResource() == GenResourceKind.XMI_LITERAL;
  }

  public String getQualifiedEffectiveResourceFactoryClassName()
  {
    return getResource() == GenResourceKind.NONE_LITERAL ?
      "org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl" : getQualifiedResourceFactoryClassName();
  }

  public boolean isMultipleFileExtensions()
  {
    return isContentType() && getFileExtensions().indexOf(',') != -1;
  }

  public String getFileExtension()
  {
    String extensions = getFileExtensions();
    int i = extensions.indexOf(',');
    return i == -1 ? extensions : extensions.substring(0, i);
  }
} //GenPackageImpl