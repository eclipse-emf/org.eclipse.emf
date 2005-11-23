/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: GenPackageImpl.java,v 1.43 2005/11/23 17:30:54 khussey Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;

import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
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
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.InternalEList;


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
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getReflectionPackageSuffix <em>Reflection Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getClassPackageSuffix <em>Class Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getUtilityPackageSuffix <em>Utility Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getProviderPackageSuffix <em>Provider Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getPresentationPackageSuffix <em>Presentation Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#getTestsPackageSuffix <em>Tests Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isGenerateExampleClass <em>Generate Example Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenPackageImpl#isLiteralsInterface <em>Literals Interface</em>}</li>
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
   * The default value of the '{@link #getReflectionPackageSuffix() <em>Reflection Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReflectionPackageSuffix()
   * @generated
   * @ordered
   */
  protected static final String REFLECTION_PACKAGE_SUFFIX_EDEFAULT = "";

  /**
   * The cached value of the '{@link #getReflectionPackageSuffix() <em>Reflection Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReflectionPackageSuffix()
   * @generated
   * @ordered
   */
  protected String reflectionPackageSuffix = REFLECTION_PACKAGE_SUFFIX_EDEFAULT;

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
   * The cached value of the '{@link #getEcorePackage() <em>Ecore Package</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcorePackage()
   * @generated
   * @ordered
   */
  protected EPackage ecorePackage = null;

  /**
   * The cached value of the '{@link #getGenEnums() <em>Gen Enums</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenEnums()
   * @generated
   * @ordered
   */
  protected EList genEnums = null;

  /**
   * The cached value of the '{@link #getGenDataTypes() <em>Gen Data Types</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenDataTypes()
   * @generated
   * @ordered
   */
  protected EList genDataTypes = null;

  /**
   * The cached value of the '{@link #getGenClasses() <em>Gen Classes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenClasses()
   * @generated
   * @ordered
   */
  protected EList genClasses = null;

  /**
   * The cached value of the '{@link #getNestedGenPackages() <em>Nested Gen Packages</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNestedGenPackages()
   * @generated
   * @ordered
   */
  protected EList nestedGenPackages = null;

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
  public String getReflectionPackageSuffix()
  {
    return reflectionPackageSuffix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReflectionPackageSuffix(String newReflectionPackageSuffix)
  {
    String oldReflectionPackageSuffix = reflectionPackageSuffix;
    reflectionPackageSuffix = newReflectionPackageSuffix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PACKAGE__REFLECTION_PACKAGE_SUFFIX, oldReflectionPackageSuffix, reflectionPackageSuffix));
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

  public  EModelElement getEcoreModelElement()
  {
    return getEcorePackage();
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

  public GenModel getGenModel() 
  {
    GenModel result = getGenModelGen();
    if (result == null && eContainer() != null)
    {
      result = ((GenBase)eContainer()).getGenModel();
    }

    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenModel getGenModelGen()
  {
    if (eContainerFeatureID != GenModelPackage.GEN_PACKAGE__GEN_MODEL) return null;
    return (GenModel)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenModel(GenModel newGenModel)
  {
    if (newGenModel != eInternalContainer() || (eContainerFeatureID != GenModelPackage.GEN_PACKAGE__GEN_MODEL && newGenModel != null))
    {
      if (EcoreUtil.isAncestor(this, newGenModel))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenModel != null)
        msgs = ((InternalEObject)newGenModel).eInverseAdd(this, GenModelPackage.GEN_MODEL__GEN_PACKAGES, GenModel.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newGenModel, GenModelPackage.GEN_PACKAGE__GEN_MODEL, msgs);
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
  public EList getGenEnums()
  {
    if (genEnums == null)
    {
      genEnums = new EObjectContainmentEList(GenEnum.class, this, GenModelPackage.GEN_PACKAGE__GEN_ENUMS);
    }
    return genEnums;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getGenDataTypes()
  {
    if (genDataTypes == null)
    {
      genDataTypes = new EObjectContainmentEList(GenDataType.class, this, GenModelPackage.GEN_PACKAGE__GEN_DATA_TYPES);
    }
    return genDataTypes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getGenClasses()
  {
    if (genClasses == null)
    {
      genClasses = new EObjectContainmentEList(GenClass.class, this, GenModelPackage.GEN_PACKAGE__GEN_CLASSES);
    }
    return genClasses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getNestedGenPackages()
  {
    if (nestedGenPackages == null)
    {
      nestedGenPackages = new EObjectContainmentEList(GenPackage.class, this, GenModelPackage.GEN_PACKAGE__NESTED_GEN_PACKAGES);
    }
    return nestedGenPackages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList getGenClassifiers()
  {
    EList result = new BasicEList();
    result.addAll(getGenClasses());
    result.addAll(getGenEnums());
    result.addAll(getGenDataTypes());
    return new BasicEList.UnmodifiableEList(result.size(), result.toArray());
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
        case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
          if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, GenModelPackage.GEN_PACKAGE__GEN_MODEL, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eInternalContainer() != null)
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
        case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
          return eBasicSetContainer(null, GenModelPackage.GEN_PACKAGE__GEN_MODEL, msgs);
        case GenModelPackage.GEN_PACKAGE__GEN_ENUMS:
          return ((InternalEList)getGenEnums()).basicRemove(otherEnd, msgs);
        case GenModelPackage.GEN_PACKAGE__GEN_DATA_TYPES:
          return ((InternalEList)getGenDataTypes()).basicRemove(otherEnd, msgs);
        case GenModelPackage.GEN_PACKAGE__GEN_CLASSES:
          return ((InternalEList)getGenClasses()).basicRemove(otherEnd, msgs);
        case GenModelPackage.GEN_PACKAGE__NESTED_GEN_PACKAGES:
          return ((InternalEList)getNestedGenPackages()).basicRemove(otherEnd, msgs);
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
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
          return eInternalContainer().eInverseRemove(this, GenModelPackage.GEN_MODEL__GEN_PACKAGES, GenModel.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return eInternalContainer().eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
        return isDisposableProviderFactory() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_PACKAGE__ADAPTER_FACTORY:
        return isAdapterFactory() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_PACKAGE__LOAD_INITIALIZATION:
        return isLoadInitialization() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX:
        return getInterfacePackageSuffix();
      case GenModelPackage.GEN_PACKAGE__REFLECTION_PACKAGE_SUFFIX:
        return getReflectionPackageSuffix();
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
        return isGenerateExampleClass() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_PACKAGE__LITERALS_INTERFACE:
        return isLiteralsInterface() ? Boolean.TRUE : Boolean.FALSE;
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
    return eDynamicGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
        setDisposableProviderFactory(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_PACKAGE__ADAPTER_FACTORY:
        setAdapterFactory(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_PACKAGE__LOAD_INITIALIZATION:
        setLoadInitialization(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_PACKAGE__INTERFACE_PACKAGE_SUFFIX:
        setInterfacePackageSuffix((String)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__REFLECTION_PACKAGE_SUFFIX:
        setReflectionPackageSuffix((String)newValue);
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
        setGenerateExampleClass(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_PACKAGE__LITERALS_INTERFACE:
        setLiteralsInterface(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_PACKAGE__ECORE_PACKAGE:
        setEcorePackage((EPackage)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_MODEL:
        setGenModel((GenModel)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_ENUMS:
        getGenEnums().clear();
        getGenEnums().addAll((Collection)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_DATA_TYPES:
        getGenDataTypes().clear();
        getGenDataTypes().addAll((Collection)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__GEN_CLASSES:
        getGenClasses().clear();
        getGenClasses().addAll((Collection)newValue);
        return;
      case GenModelPackage.GEN_PACKAGE__NESTED_GEN_PACKAGES:
        getNestedGenPackages().clear();
        getNestedGenPackages().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
      case GenModelPackage.GEN_PACKAGE__REFLECTION_PACKAGE_SUFFIX:
        setReflectionPackageSuffix(REFLECTION_PACKAGE_SUFFIX_EDEFAULT);
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
    eDynamicUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
      case GenModelPackage.GEN_PACKAGE__REFLECTION_PACKAGE_SUFFIX:
        return REFLECTION_PACKAGE_SUFFIX_EDEFAULT == null ? reflectionPackageSuffix != null : !REFLECTION_PACKAGE_SUFFIX_EDEFAULT.equals(reflectionPackageSuffix);
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
    return eDynamicIsSet(featureID);
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
    result.append(", reflectionPackageSuffix: ");
    result.append(reflectionPackageSuffix);
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
    result.append(')');
    return result.toString();
  }

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
    return getQualifiedPackageName(getReflectionPackageSuffix());
  }

  public String getClassPackageName()
  {
    return getQualifiedPackageName(getClassPackageSuffix());
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

  public String getPackageInterfaceName()
  {
    return getPrefixedName("Package");
  }

  public String getQualifiedPackageInterfaceName()
  {
    return getReflectionPackageName() + "." + getPackageInterfaceName();
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
    return getImplClassName(getPackageInterfaceName());
  }
  
  public String getQualifiedPackageClassName()
  {
    return getClassPackageName() + "." + getPackageClassName();
  }

  public String getImportedPackageClassName()
  {
    return getGenModel().getImportedName(getQualifiedPackageClassName());
  }

  public String getFactoryInterfaceName()
  {
    return getPrefixedName("Factory");
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
    return getImplClassName(getFactoryInterfaceName());
  }

  public String getQualifiedFactoryClassName()
  {
    return getClassPackageName() + "." + getFactoryClassName();
  }

  public String getImportedFactoryClassName()
  {
    return getGenModel().getImportedName(getQualifiedFactoryClassName());
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

  public String getTestSuiteClassName()
  {
    return getPrefixedName("Tests");
  }
  
  public String getExampleClassName()
  {
    return getPrefixedName("Example");
  }

  public String getQualifiedTestSuiteClassName()
  {
    return getTestsPackageName() + "." + getPrefixedName("Tests");
  }

  public String getImportedTestSuiteClassName()
  {
    return getGenModel().getImportedName(getQualifiedTestSuiteClassName());
  }

  protected static final boolean NO_CONSTRAINTS = "true".equals(System.getProperty("EMF_NO_CONSTRAINTS")); 

  public boolean hasConstraints()
  {
    if (NO_CONSTRAINTS) return false;
    for (Iterator i = getGenClassifiers().iterator(); i.hasNext(); )
    {
      GenClassifier genClassifier = (GenClassifier)i.next();
      if (!genClassifier.getGenConstraints().isEmpty())
      {
        return true;
      }
    }
    return false;
  }

  public String getValidatorClassName()
  {
    return getPrefixedName("Validator");
  }

  public String getImportedValidatorClassName()
  {
    return getGenModel().getImportedName(getUtilitiesPackageName() + "." + getPrefixedName("Validator"));
  }
  
  public String getImportedXMLProcessorBaseClassName()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.xmi.util.XMLProcessor");   
  }

  public String getQualifiedXMLProcessorClassName()
  {
    return getUtilitiesPackageName() + "." + getPrefixedName("XMLProcessor");
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

  public List getAllGenDataTypes()
  {
    List result = new ArrayList();
    result.addAll(getGenEnums());
    result.addAll(getGenDataTypes());
    return result;
  }

  public List getOrderedGenClasses()
  {
    List result = new ArrayList();
    Set resultSet = new HashSet();

    for (Iterator iter = getGenClasses().iterator(); iter.hasNext(); )
    {
      List extendChain = new LinkedList();
      for (GenClass genClass = (GenClass)iter.next(); genClass != null; genClass = genClass.getBaseGenClass())
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

  public List getOrderedGenClassifiers()
  {
    List result = new ArrayList(getOrderedGenClasses());
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

  public List getPackageSimpleDependencies()
  {
    return dependencyHelper.getSimpleDependencies();
  }

  public List getPackageInterDependencies()
  {
    return dependencyHelper.getInterDependencies();
  }

  public List getPackageLoadInterDependencies()
  {
    return dependencyHelper.getLoadInterDependencies();
  }

  public List getPackageBuildInterDependencies()
  {
    return dependencyHelper.getBuildInterDependencies();
  }

  public List getPackageInitializationDependencies()
  {
    return dependencyHelper.getInitializationDependencies();
  }

  public String getPackageInstanceVariable(GenPackage genPackage)
  {
    if (genPackage == this) return "this";
    if (genPackage == ecoreGenPackage) return "ecorePackage";

    return "the" + dependencyHelper.getUniqueName(genPackage);
  }

  private DependencyHelper dependencyHelper = null;

  private class DependencyHelper extends GenBaseImpl.UniqueNameHelper
  {
    private List simpleDependencies;
    private List interDependencies;
    private List loadInterDependencies;
    private List buildInterDependencies;
    private List initializationDependencies;

    public DependencyHelper()
    {
      super(2 * (getGenModel().getGenPackages().size() + getGenModel().getUsedGenPackages().size()));

      add(GenPackageImpl.this);

      simpleDependencies = new ArrayList();
      collectPackages(simpleDependencies, getGenModel().getUsedGenPackages(), 1);
      addAll(simpleDependencies);
      
      interDependencies = new ArrayList();
      collectPackages(interDependencies, getGenModel().getGenPackages(), -1);
      interDependencies.remove(GenPackageImpl.this);
      addAll(interDependencies);

      loadInterDependencies = new ArrayList();
      buildInterDependencies = new ArrayList();
      for (Iterator i = interDependencies.iterator(); i.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)i.next();
        if (genPackage.isLoadedInitialization())
        {
          loadInterDependencies.add(genPackage);
        }
        else
        {
          buildInterDependencies.add(genPackage);
        }
      }

      initializationDependencies = new UniqueEList();
      for (Iterator i = getSubGenPackages().iterator(); i.hasNext(); )
      {
        initializationDependencies.add(i.next());
      }
      for (Iterator i = getGenClasses().iterator(); i.hasNext(); )
      {
        GenClass genClass = (GenClass)i.next();
        for (Iterator j = genClass.getBaseGenClasses().iterator(); j.hasNext(); )
        {
          GenClass baseGenClass = (GenClass)j.next();
          initializationDependencies.add(baseGenClass.getGenPackage());
        }
        for (Iterator j = genClass.getGenFeatures().iterator(); j.hasNext(); )
        {
          GenFeature genFeature = (GenFeature)j.next();
          initializationDependencies.add(genFeature.getTypeGenPackage());

          if (genFeature.isReferenceType())
          {
            GenFeature reverseGenFeature = genFeature.getReverse();
            if (reverseGenFeature != null)
            {
              initializationDependencies.add(reverseGenFeature.getGenPackage());
            }
          }

          GenFeature delegateFeature = genFeature.getDelegateFeature();
          if (delegateFeature != null)
          {
            initializationDependencies.add(delegateFeature.getGenPackage());
          }
        }
        for (Iterator j = genClass.getGenOperations().iterator(); j.hasNext(); )
        {
          GenOperation genOperation = (GenOperation)j.next();
          if (!genOperation.isVoid())
          {
            initializationDependencies.add(genOperation.getTypeGenPackage());
          }
          for (Iterator k = genOperation.getGenParameters().iterator(); k.hasNext(); )
          {
            GenParameter genParameter = (GenParameter)k.next();
            initializationDependencies.add(genParameter.getTypeGenPackage());
          }
          for (Iterator k = genOperation.getGenExceptions().iterator(); k.hasNext(); )
          {
            GenClassifier genClassifier = (GenClassifier)k.next();
            initializationDependencies.add(genClassifier.getGenPackage());
          }
        }
      }
      for (Iterator i = getGenDataTypes().iterator(); i.hasNext(); )
      {
        GenDataType genDataType = (GenDataType)i.next();
        GenDataType baseType = genDataType.getBaseType();
        if (baseType != null)
        {
          initializationDependencies.add(baseType.getGenPackage());
        }
        GenDataType itemType = genDataType.getItemType();
        if (itemType != null)
        {
          initializationDependencies.add(itemType.getGenPackage());
        }
        for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); )
        {
          GenDataType memberType = (GenDataType)j.next();
          initializationDependencies.add(memberType.getGenPackage());
        }
      }

      if (initializationDependencies.contains(xmlTypeGenPackage) && !xmlTypeGenPackage.getNSURI().equals(getNSURI()))
      {
        simpleDependencies.add(xmlTypeGenPackage);
      }
      if (initializationDependencies.contains(xmlNamespaceGenPackage))
      {
        simpleDependencies.add(xmlNamespaceGenPackage);
      }

      initializationDependencies.remove(GenPackageImpl.this);
      initializationDependencies.remove(findGenPackage(EcorePackage.eINSTANCE));
    }

    protected String getName(Object o)
    {
      return ((GenPackage)o).getPackageInterfaceName();
    }

    public List getSimpleDependencies()
    {
      return simpleDependencies;
    }

    public List getInterDependencies()
    {
      return interDependencies;
    }

    public List getLoadInterDependencies()
    {
      return loadInterDependencies;
    }

    public List getBuildInterDependencies()
    {
      return buildInterDependencies;
    }

    public List getInitializationDependencies()
    {
      return initializationDependencies;
    }
  }
  
  /**
   * Collects into result nested GenPackages to the specified depth,
   * not counting those without classifiers (for which nothing is ever
   * generated); -1 for no limit.
   */
  protected void collectPackages(List result, List genPackages, int depth)
  {
    if (depth == 0) return;

    for (Iterator i = genPackages.iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
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

  public List getSubGenPackages()
  {
    List result = new ArrayList();
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
    
    for (Iterator i = genPackage.getSubGenPackages().iterator(); i.hasNext(); )
    {
      if (needsLoadInitialization((GenPackage)i.next())) return true;
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

  public List getJavaLangConflicts()
  {
    List result = new ArrayList();
    for (Iterator i = getGenClasses().iterator(); i.hasNext(); )
    {
      GenClass genClass = (GenClass)i.next();
      String name = genClass.getName();
      if (CodeGenUtil.isJavaDefaultType(name))
      {
        result.add(name);
      }
    }
    return result;
  }

  public boolean hasInterfaceImplConflict()
  {
    Set names = new HashSet();
    for (Iterator i = getGenClasses().iterator(); i.hasNext(); )
    {
      GenClass genClass = (GenClass)i.next();
      names.add(genClass.getName());
    }

    for (Iterator i = names.iterator(); i.hasNext(); )
    {
      String name = (String)i.next();
      if (names.contains(name + "Impl"))
      {
        return true;
      }
    }

    return false;
  }

  public List getAllSwitchGenClasses()
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
    return switchHelper.getUniqueName(genClass);
  }

  private SwitchHelper switchHelper = null;

  private class SwitchHelper extends GenBaseImpl.UniqueNameHelper
  {
    protected List allGenClasses = new LinkedList();
    protected List allBaseGenPackages = new UniqueEList();

    public SwitchHelper()
    {
      super(3 * getGenClasses().size());
      initLocal();
      initBases();
    }

    protected SwitchHelper(int size)
    {
      super(size);
    }

    protected void initLocal()
    {
      // Add all classes from this package first, to guarantee they get the
      // simple names
      for (Iterator i = getGenClasses().iterator(); i.hasNext(); )
      {
        GenClass genClass = (GenClass)i.next();
        
        // EObject is handled by default case
        if (!genClass.isEObject() && add(genClass))
        {
          allGenClasses.add(genClass);
        }
      }
    }

    protected void initBases()
    {
      // Go through class supertypes to catch any from other packages
      for (Iterator i = getGenClasses().iterator(); i.hasNext(); )
      {
        GenClass genClass = (GenClass)i.next();
        if (genClass.isEObject()) continue;

        for (Iterator j = genClass.getEcoreClass().getEAllSuperTypes().iterator(); j.hasNext(); )
        {
          EClass base = (EClass)j.next();
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

    protected String getName(Object o)
    {
      return ((GenClassifier)o).getName();
    }

    protected List getAlternateNames(Object o)
    {
      GenClassifier genClassifier = (GenClassifier)o;
      return Collections.singletonList(genClassifier.getGenPackage().getPrefix() + "_" + genClassifier.getName());
    }

    public List getAllGenClasses()
    {
      return allGenClasses;
    }
  }

  public List getAllValidatorBaseGenPackages()
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

  private class ValidatorHelper 
  {
    protected List allBaseGenPackages = new UniqueEList();

    public ValidatorHelper()
    {
      init();
    }

    protected void init()
    {
      // Go through class supertypes to catch constraints from other packages
      for (Iterator i = getGenClasses().iterator(); i.hasNext(); )
      {
        GenClass genClass = (GenClass)i.next();
        if (!genClass.isEObject()) 
        {
          for (Iterator j = genClass.getEcoreClass().getEAllSuperTypes().iterator(); j.hasNext(); )
          {
            EClass base = (EClass)j.next();
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

      for (Iterator i = getGenDataTypes().iterator(); i.hasNext(); )
      {
        GenDataType genDataType = (GenDataType)i.next();
        for (GenDataType baseType = genDataType.getBaseType(); baseType != null; baseType = baseType.getBaseType())
        {
          allBaseGenPackages.add(baseType.getGenPackage());
        }
        GenDataType itemType = genDataType.getItemType();
        if (itemType != null)
        {
          allBaseGenPackages.add(itemType.getGenPackage());
        }
        for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); )
        {
          GenDataType memberType = (GenDataType)j.next();
          allBaseGenPackages.add(memberType.getGenPackage());
        }
      }

      allBaseGenPackages.remove(GenPackageImpl.this);      
    }

    public List getAllBaseGenPackages()
    {
      return allBaseGenPackages;
    }

    public String getPackageUniqueSafeName(GenPackage genPackage)
    {
      return safeName(uncapPrefixedName(genPackage.getPrefix())); 
    }
  }

  public void initialize(EPackage ePackage)
  {
    boolean isDifferentPackage = ePackage != getEcorePackage();
    if (isDifferentPackage)
    {
      setEcorePackage(ePackage);
  
      if (hasExtendedMetaData(ePackage))
      {
        setResource(GenResourceKind.XML_LITERAL);
      }
      setDisposableProviderFactory(true);
    }

    CLASSIFIER_LOOP:
    for (Iterator iter = ePackage.getEClassifiers().iterator(); iter.hasNext(); )
    {
      EClassifier eClassifier = (EClassifier)iter.next();

      for (Iterator j = getGenClassifiers().iterator(); j.hasNext(); )
      {
        GenClassifier genClassifier = (GenClassifier)j.next();
        if (genClassifier.getEcoreClassifier() == eClassifier)
        {
          if (eClassifier instanceof EClass)
          {
            ((GenClass)genClassifier).initialize((EClass)eClassifier);
          }
          else if (eClassifier instanceof EEnum)
          {
            ((GenEnum)genClassifier).initialize((EEnum)eClassifier);
          }
          else if (eClassifier instanceof EDataType)
          {
            ((GenDataType)genClassifier).initialize((EDataType)eClassifier);
          }

          continue CLASSIFIER_LOOP;
        }
      }

      if (eClassifier instanceof EClass)
      {
        EClass eClass = (EClass)eClassifier;
        GenClass genClass = getGenModel().createGenClass();
        getGenClasses().add(genClass);
        genClass.initialize(eClass);

      }
      else if (eClassifier instanceof EEnum)
      {
        EEnum eEnum = (EEnum)eClassifier;
        GenEnum genEnum = getGenModel().createGenEnum();
        getGenEnums().add(genEnum);
        genEnum.initialize(eEnum);
      }
      else if (eClassifier instanceof EDataType)
      {
        EDataType eDataType = (EDataType)eClassifier;
        GenDataType genDataType = getGenModel().createGenDataType();
        getGenDataTypes().add(genDataType);
        genDataType.initialize(eDataType);
      }
    }

    PACKAGE_LOOP:
    for (Iterator iter = ePackage.getESubpackages().iterator(); iter.hasNext(); )
    {
      EPackage nestedEPackage = (EPackage)iter.next();

      for (Iterator j = getNestedGenPackages().iterator(); j.hasNext(); )
      {
        GenPackage nestedGenPackage = (GenPackage)j.next();
        if (nestedGenPackage.getEcorePackage() == nestedEPackage)
        {
          nestedGenPackage.initialize(nestedEPackage);
          continue PACKAGE_LOOP;
        }
      }

      GenPackage genPackage = getGenModel().createGenPackage();
      getNestedGenPackages().add(genPackage);
      genPackage.initialize(nestedEPackage);
    }

    if (isDifferentPackage)
    {
      setLoadInitialization(isBigModel());
    }
  }

  protected boolean hasModelContribution()
  {
    return hasClassifiers() || !getNestedGenPackages().isEmpty();
  }

  public void generate(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerate()) return;

      progressMonitor.beginTask("", 2 * getGenClasses().size() + 2 * getGenEnums().size() + 8 + getNestedGenPackages().size());
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingPackage_message", new Object [] { getPackageInterfaceName() }));

      // Create helpers to cache and supply information for unique naming
      switchHelper = new SwitchHelper();
      validatorHelper = new ValidatorHelper();
      dependencyHelper = new DependencyHelper();

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
           getGenModel().getPackageInterfaceEmitter());
  
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getClassPackageName() + "." + getPackageClassName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE,
           getGenModel().getEffectiveModelPluginVariables(),
           getGenModel().getModelDirectory(),
           getClassPackageName(), 
           getPackageClassName(), 
           getGenModel().getPackageClassEmitter());
  
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
           getGenModel().getFactoryInterfaceEmitter());
  
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getClassPackageName() + "." + getFactoryClassName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE,
           getGenModel().getEffectiveModelPluginVariables(),
           getGenModel().getModelDirectory(),
           getClassPackageName(), 
           getFactoryClassName(), 
           getGenModel().getFactoryClassEmitter());
        
        
        if (getResource().getValue() == GenResourceKind.XML)
        {
          progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getClassPackageName() + "." + getXMLProcessorClassName() }));
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
               ("_UI_GeneratingJavaClass_message", new Object [] { getUtilitiesPackageName() + "." + getValidatorClassName() }));
          generate
            (createMonitor(progressMonitor, 1), 
             Generator.EMF_MODEL_PROJECT_STYLE,
             getGenModel().getEffectiveModelPluginVariables(),
             getGenModel().getModelDirectory(),
             getUtilitiesPackageName(), 
             getValidatorClassName(), 
             getGenModel().getValidatorClassEmitter());
        }

        if (isAdapterFactory())
        {
          progressMonitor.subTask
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_GeneratingJavaClass_message", new Object [] { getUtilitiesPackageName() + "." + getSwitchClassName() }));
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
               ("_UI_GeneratingJavaClass_message", new Object [] { getUtilitiesPackageName() + "." + getAdapterFactoryClassName() }));
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
      // Clear the transient helpers
      switchHelper = null;
      validatorHelper = null;
      dependencyHelper = null;

      progressMonitor.done();
    }
  }
  
  /**
   * @deprecated Use {@link GenBase#generateSchema(Monitor)} instead.  This
   * method will be removed soon.
   */  
  public void generateSchema()
  {
    generateSchema(new BasicMonitor());
  }
  
  public boolean canGenerateSchema()
  {
    return canGenerate();
  }
  
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

  protected void generateXSD(String type)
  {
    Bundle xsdPlugin = Platform.getBundle("org.eclipse.xsd");
    if (xsdPlugin != null)
    {
      try
      {
        Class theGeneratorClass = 
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
              Class theMapperInterface = 
                xsdPlugin.loadClass
                  ("org.eclipse.xsd.ecore.MapBuilder$Mapper");

              Class theMapperClass = 
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

          Collection result = 
            (Collection)theGeneratorClass.getMethod
              ("generate", 
               new Class [] { EPackage.class }).invoke(generator, new Object [] { getEcorePackage() });

          Iterator i = result.iterator();
          Object xsdSchema = i.next();

          ResourceSet resourceSet = new ResourceSetImpl();
          resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
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
              Object otherXSDSchema = i.next();
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
              Object mappingRoot = i.next();
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
    String name = getQualifiedPackageName();
    return isBlank(suffix) ? name : name + "." + suffix;
  }
  
  public String getQualifiedPackageName()
  {
    String name = safeName(getPackageName());
    if (!isBlank(getBasePackage()))
    {
      name = getBasePackage() + "." + name;
    }    
    return name;
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

  public List/*GenFeature*/ getAllGenFeatures()
  {
    List result = new ArrayList();

    // Any features that delegate to features in this package.
    //
    List delegated = new ArrayList();

    for (Iterator iter = getGenClasses().iterator(); iter.hasNext(); )
    {
      GenClass genClass = (GenClass)iter.next();
      for (Iterator fIter = genClass.getGenFeatures().iterator(); fIter.hasNext(); )
      {
        GenFeature genFeature = (GenFeature)fIter.next();
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

  public List/*GenFeature*/ getFilteredAllGenFeatures()
  {
    ArrayList result = new ArrayList();

    // We need to screen out duplicates in the unlikely event that we have two
    //  features with the same class-qualifed name. We'll only generate one property
    //  string in that case and let the user add the second one mannually, if necessary.
    //
    HashSet noDupHash = new HashSet();
    for (Iterator iter = getGenClasses().iterator(); iter.hasNext(); )
    {
      GenClass genClass = (GenClass)iter.next();
      for (Iterator fIter = genClass.getGenFeatures().iterator(); fIter.hasNext(); )
      {
        GenFeature genFeature = (GenFeature)fIter.next();
        if (noDupHash.add(genFeature.getGenClass().getName() + genFeature.getName()))
        {
          result.add(genFeature);
        }
      }
    }

    return result;
  }

  public List/*GenPackage*/ getAdapterDelegatePackages()
  {
    List result = new ArrayList();

    //TBD return (user selected subset?) of referenced packages that contain super classes of classes in this package.

    return result;
  }

  public List/*GenClass*/ getAdapterDelegateSuperClasses()
  {
    List result = new ArrayList();

    //TBD return superclasses of classes in this package that come from a delegate package.

    return result;
  }

  public boolean hasStatefulProvider()
  {
    for (Iterator iter = getGenClasses().iterator(); iter.hasNext(); )
    {
      GenClass genClass = (GenClass)iter.next();
      if (!genClass.isAbstract() && genClass.getProvider() == GenProviderKind.STATEFUL_LITERAL)
      {
        return true;
      }
    }
    return false;
  }

  public void generateEdit(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerateEdit()) return;

      progressMonitor.beginTask("", getGenClasses().size() + getNestedGenPackages().size() + 2); //TBD 
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingItemProvidersForPackage_message", new Object [] { getPackageInterfaceName() }));

      if (hasClassifiers())
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
              new Object [] { getProviderPackageName() + "." + getItemProviderAdapterFactoryClassName() }));
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

  public void generateEditor(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerateEditor()) return;

      progressMonitor.beginTask("", 7); //TBD 
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingEditorForPackage_message", new Object [] { getPackageInterfaceName() }));

      if (hasClassifiers() && hasConcreteClasses())
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
    for (Iterator i = getGenClasses().iterator(); i.hasNext();)
    {
      if (((GenClass)i.next()).hasTests())
      {
        return true;
      }
    }

    return false;
  }

  public boolean canGenerateTests()
  {
    return getGenModel().canGenerateTests() && hasClassifiers(true);
  }

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
      for (Iterator i = getNestedGenPackages().iterator(); i.hasNext();)
      {
        GenPackage genPackage = (GenPackage)i.next();
        if(genPackage.hasClassifiers(true))
        {
          return true;
        }
      }
    }
    return false;
  }

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
      for (Iterator i = getGenClassifiers().iterator(); i.hasNext(); )
      {
        GenClassifier genClassifier = (GenClassifier)i.next();
        for (Iterator j = oldGenPackageVersion.getGenClassifiers().iterator(); j.hasNext(); )
        {
          GenClassifier oldGenClassifierVersion = (GenClassifier)j.next();
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

      for (Iterator i = getNestedGenPackages().iterator(); i.hasNext(); )
      {
        GenPackage nestedGenPackage = (GenPackage)i.next();
        for (Iterator j = oldGenPackageVersion.getNestedGenPackages().iterator(); j.hasNext(); )
        {
          GenPackage oldNestedGenPackageVersion = (GenPackage)j.next();
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
    setReflectionPackageSuffix(oldGenPackageVersion.getReflectionPackageSuffix());
    setClassPackageSuffix(oldGenPackageVersion.getClassPackageSuffix());
    setUtilityPackageSuffix(oldGenPackageVersion.getUtilityPackageSuffix());
    setProviderPackageSuffix(oldGenPackageVersion.getProviderPackageSuffix());
    setPresentationPackageSuffix(oldGenPackageVersion.getPresentationPackageSuffix());
    setTestsPackageSuffix(oldGenPackageVersion.getTestsPackageSuffix());
    setGenerateExampleClass(oldGenPackageVersion.isGenerateExampleClass());
    setLiteralsInterface(oldGenPackageVersion.isLiteralsInterface());
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
      for (Iterator i = getGenClasses().iterator(); i.hasNext(); )
      {
        GenClass genClass = (GenClass)i.next();
        if (!genClass.reconcile())
        {
          i.remove();
        }
      }
      
      for (Iterator i = getGenEnums().iterator(); i.hasNext(); )
      {
        GenEnum genEnum = (GenEnum)i.next();
        if (!genEnum.reconcile())
        {
          i.remove();
        }
      }
      
      for (Iterator i = getGenDataTypes().iterator(); i.hasNext(); )
      {
        GenDataType genDataType = (GenDataType)i.next();
        if (!genDataType.reconcile())
        {
          i.remove();
        }
      }

      for (Iterator i = getNestedGenPackages().iterator(); i.hasNext(); )
      {
        GenPackage nestedGenPackage = (GenPackage)i.next();
        if (!nestedGenPackage.reconcile())
        {
          i.remove();
        }
      }

      initialize(ePackage);
      return true;
    }
  }

  public List getAnnotationSources()
  {
    List result = new UniqueEList();
    EPackage ePackage = getEcorePackage();
    for (TreeIterator i = ePackage.eAllContents(); i.hasNext(); )
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

        // Ignore GenModel exceptions ones with a body for now.
        //
        if (source == null || !source.equals(GenModelPackage.eNS_URI) || eAnnotation.getDetails().containsKey("body"))
        {
          result.add(source);
        }
      }
    }
    return result;
  }

  public String getAnnotationSourceIdentifier(String annotationSource)
  {
    String result = annotationSource;
    if (annotationSource == null)
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
      return capName(result);
    }
  }

  public List getAllAnnotations()
  {
    List result = new UniqueEList();
    EPackage ePackage = getEcorePackage();
    for (TreeIterator i = ePackage.eAllContents(); i.hasNext(); )
    {
      Object object = i.next();
      if (object instanceof EPackage)
      {
        i.prune();
      }
      else if (object instanceof EAnnotation)
      {
        result.add(object);
        i.prune();
      }
    }
    return result;
  }

  public String getAnnotatedModelElementAccessor(EAnnotation eAnnotation)
  {
    EModelElement eModelElement = eAnnotation.getEModelElement();
    String result =
      (String)
        new EcoreSwitch()
        {
          public Object caseEPackage(EPackage ePackage)
          {
            return "this";
          }

          public Object caseEClassifier(EClassifier eClassifier)
          {
            return findGenClassifier(eClassifier).getClassifierInstanceName();
          }

          public Object caseEStructuralFeature(EStructuralFeature eStructuralFeature)
          {
            return "get" + findGenFeature(eStructuralFeature).getFeatureAccessorName() + "()";
          }

          public Object caseEOperation(EOperation eOperation)
          {
            EClass eClass = eOperation.getEContainingClass();
            return 
              "(" +
                getGenModel().getImportedName("org.eclipse.emf.ecore.EOperation") + 
                ")" + 
                caseEClassifier(eClass) + 
                ".getEOperations().get(" +
                eClass.getEOperations().indexOf(eOperation) +
                ")";
          }
          public Object caseEEnumLiteral(EEnumLiteral eEnumLiteral)
          {
            EEnum eEnum = eEnumLiteral.getEEnum();
            return 
              "(" + 
                getGenModel().getImportedName("org.eclipse.emf.ecore.EEnumLiteral") + 
                ")" + 
                caseEClassifier(eEnum) + 
                ".getELiterals().get(" +
                eEnum.getELiterals().indexOf(eEnumLiteral) +
                ")";
          }

          public Object caseEParameter(EParameter eParameter)
          {
            EOperation eOperation = eParameter.getEOperation();
            return 
              "(" + 
                getGenModel().getImportedName("org.eclipse.emf.ecore.EParameter") + 
                ")(" + 
                caseEOperation(eOperation) +
                ").getEParameters().get(" +
                eOperation.getEParameters().indexOf(eParameter) +
                ")";
          }
        }.doSwitch(eModelElement);

    return result;
  }

  public String getResourceClassName()
  {
    return getPrefix() + "ResourceImpl";
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
    return getPrefix() + "ResourceFactoryImpl";
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
    for (TreeIterator i = ePackage.eAllContents(); i.hasNext(); )
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
        if (ExtendedMetaData.ANNOTATION_URI.equals(source))
        {
          return true;
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
    
    for (Iterator i = getGenClasses().iterator(); i.hasNext(); )
    {
      GenClass genClass = (GenClass)i.next();
      supers += genClass.getEcoreClass().getESuperTypes().size();
      features += genClass.getGenFeatures().size();
      operations += genClass.getGenOperations().size();
      
      for (Iterator j = genClass.getGenOperations().iterator(); j.hasNext(); )
      {
        GenOperation genOperation = (GenOperation)j.next();
        parameters += genOperation.getGenParameters().size();
        exceptions += genOperation.getEcoreOperation().getEExceptions().size();
      }
    }

    int enums = getGenEnums().size();
    int literals = 0;

    for (Iterator i = getGenEnums().iterator(); i.hasNext(); )
    {
      GenEnum genEnum = (GenEnum)i.next();
      literals += genEnum.getGenEnumLiterals().size();
    }

    int datatypes = getGenDataTypes().size();

    return (classes + supers + features + operations + parameters + exceptions + enums + literals + datatypes) > 500;
  }
  
  public List /*String*/ getProviderSupportedTypes()
  {
    List result = new ArrayList();
    result.add("org.eclipse.emf.edit.provider.IEditingDomainItemProvider");
    result.add("org.eclipse.emf.edit.provider.IStructuredItemContentProvider");
    result.add("org.eclipse.emf.edit.provider.ITreeItemContentProvider");
    result.add("org.eclipse.emf.edit.provider.IItemLabelProvider");
    result.add("org.eclipse.emf.edit.provider.IItemPropertySource");
    return result;
  }
  
  public GenClass getRootClass()
  {
    GenFeature rootFeature = getRootFeature();
    if (rootFeature == null)
    {
      for (Iterator i = getGenClasses().iterator(); i.hasNext();)
      {
        GenClass genClass = (GenClass)i.next();
        if (!genClass.isAbstract())
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
      for (Iterator i = getExtendedMetaData().getAllElements(documentRoot).iterator(); i.hasNext();)
      {
        EStructuralFeature eStructuralFeature = (EStructuralFeature)i.next();
        if (eStructuralFeature instanceof EReference && !((EClass)eStructuralFeature.getEType()).isAbstract())
        {
          return findGenFeature(eStructuralFeature);
        }
      }
    }
    return null;
  }
    
  public boolean hasConcreteClasses()
  {
    for (Iterator i = getGenClasses().iterator(); i.hasNext(); )
    {
      GenClass genClass = (GenClass)i.next();
      if (!genClass.isAbstract())
      {
        return true;
      }
    }
    return false;
  }
} //GenPackageImpl
