/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: GenPackage.java,v 1.34 2008/05/04 17:03:27 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getBasePackage <em>Base Package</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isDisposableProviderFactory <em>Disposable Provider Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isAdapterFactory <em>Adapter Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isLoadInitialization <em>Load Initialization</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getInterfacePackageSuffix <em>Interface Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getMetaDataPackageSuffix <em>Meta Data Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getClassPackageSuffix <em>Class Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getUtilityPackageSuffix <em>Utility Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getProviderPackageSuffix <em>Provider Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getPresentationPackageSuffix <em>Presentation Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getTestsPackageSuffix <em>Tests Package Suffix</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isGenerateExampleClass <em>Generate Example Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isLiteralsInterface <em>Literals Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isDataTypeConverters <em>Data Type Converters</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isMultipleEditorPages <em>Multiple Editor Pages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isGenerateModelWizard <em>Generate Model Wizard</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isExtensibleProviderFactory <em>Extensible Provider Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isChildCreationExtenders <em>Child Creation Extenders</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getContentTypeIdentifier <em>Content Type Identifier</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getFileExtensions <em>File Extensions</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getEcorePackage <em>Ecore Package</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenModel <em>Gen Model</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenEnums <em>Gen Enums</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenDataTypes <em>Gen Data Types</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenClasses <em>Gen Classes</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getNestedGenPackages <em>Nested Gen Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenClassifiers <em>Gen Classifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage()
 * @model
 * @generated
 */
public interface GenPackage extends GenBase
{
  /**
   * Returns the value of the '<em><b>Prefix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prefix</em>' attribute.
   * @see #setPrefix(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_Prefix()
   * @model
   * @generated
   */
  String getPrefix();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getPrefix <em>Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Prefix</em>' attribute.
   * @see #getPrefix()
   * @generated
   */
  void setPrefix(String value);

  /**
   * Returns the value of the '<em><b>Base Package</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Base Package</em>' attribute.
   * @see #setBasePackage(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_BasePackage()
   * @model
   * @generated
   */
  String getBasePackage();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getBasePackage <em>Base Package</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Base Package</em>' attribute.
   * @see #getBasePackage()
   * @generated
   */
  void setBasePackage(String value);

  /**
   * Returns the value of the '<em><b>Resource</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind
   * @see #setResource(GenResourceKind)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_Resource()
   * @model
   * @generated
   */
  GenResourceKind getResource();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getResource <em>Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resource</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind
   * @see #getResource()
   * @generated
   */
  void setResource(GenResourceKind value);

  /**
   * Returns the value of the '<em><b>Disposable Provider Factory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Disposable Provider Factory</em>' attribute.
   * @see #setDisposableProviderFactory(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_DisposableProviderFactory()
   * @model
   * @generated
   */
  boolean isDisposableProviderFactory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isDisposableProviderFactory <em>Disposable Provider Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Disposable Provider Factory</em>' attribute.
   * @see #isDisposableProviderFactory()
   * @generated
   */
  void setDisposableProviderFactory(boolean value);

  /**
   * Returns the value of the '<em><b>Adapter Factory</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Adapter Factory</em>' attribute.
   * @see #setAdapterFactory(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_AdapterFactory()
   * @model default="true"
   * @generated
   */
  boolean isAdapterFactory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isAdapterFactory <em>Adapter Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Adapter Factory</em>' attribute.
   * @see #isAdapterFactory()
   * @generated
   */
  void setAdapterFactory(boolean value);

  /**
   * Returns the value of the '<em><b>Load Initialization</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If true, the package meta-data will be initialized by loading it from a resource, instead of building it up programmatically.
   * This should be used for large packasges, for which the generated code for building it would excede the 64k method limit.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Load Initialization</em>' attribute.
   * @see #setLoadInitialization(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_LoadInitialization()
   * @model
   * @generated
   */
  boolean isLoadInitialization();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isLoadInitialization <em>Load Initialization</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Load Initialization</em>' attribute.
   * @see #isLoadInitialization()
   * @generated
   */
  void setLoadInitialization(boolean value);

  /**
   * Returns the value of the '<em><b>Interface Package Suffix</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Interface Package Suffix</em>' attribute.
   * @see #setInterfacePackageSuffix(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_InterfacePackageSuffix()
   * @model default=""
   * @generated
   */
  String getInterfacePackageSuffix();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getInterfacePackageSuffix <em>Interface Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interface Package Suffix</em>' attribute.
   * @see #getInterfacePackageSuffix()
   * @generated
   */
  void setInterfacePackageSuffix(String value);

  /**
   * Returns the value of the '<em><b>Meta Data Package Suffix</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Meta Data Package Suffix</em>' attribute.
   * @see #setMetaDataPackageSuffix(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_MetaDataPackageSuffix()
   * @model default=""
   * @generated
   */
  String getMetaDataPackageSuffix();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getMetaDataPackageSuffix <em>Meta Data Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Meta Data Package Suffix</em>' attribute.
   * @see #getMetaDataPackageSuffix()
   * @generated
   */
  void setMetaDataPackageSuffix(String value);

  /**
   * Returns the value of the '<em><b>Class Package Suffix</b></em>' attribute.
   * The default value is <code>"impl"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class Package Suffix</em>' attribute.
   * @see #setClassPackageSuffix(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_ClassPackageSuffix()
   * @model default="impl"
   * @generated
   */
  String getClassPackageSuffix();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getClassPackageSuffix <em>Class Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class Package Suffix</em>' attribute.
   * @see #getClassPackageSuffix()
   * @generated
   */
  void setClassPackageSuffix(String value);

  /**
   * Returns the value of the '<em><b>Utility Package Suffix</b></em>' attribute.
   * The default value is <code>"util"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Utility Package Suffix</em>' attribute.
   * @see #setUtilityPackageSuffix(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_UtilityPackageSuffix()
   * @model default="util"
   * @generated
   */
  String getUtilityPackageSuffix();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getUtilityPackageSuffix <em>Utility Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Utility Package Suffix</em>' attribute.
   * @see #getUtilityPackageSuffix()
   * @generated
   */
  void setUtilityPackageSuffix(String value);

  /**
   * Returns the value of the '<em><b>Provider Package Suffix</b></em>' attribute.
   * The default value is <code>"provider"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Provider Package Suffix</em>' attribute.
   * @see #setProviderPackageSuffix(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_ProviderPackageSuffix()
   * @model default="provider"
   * @generated
   */
  String getProviderPackageSuffix();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getProviderPackageSuffix <em>Provider Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Provider Package Suffix</em>' attribute.
   * @see #getProviderPackageSuffix()
   * @generated
   */
  void setProviderPackageSuffix(String value);

  /**
   * Returns the value of the '<em><b>Presentation Package Suffix</b></em>' attribute.
   * The default value is <code>"presentation"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Presentation Package Suffix</em>' attribute.
   * @see #setPresentationPackageSuffix(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_PresentationPackageSuffix()
   * @model default="presentation"
   * @generated
   */
  String getPresentationPackageSuffix();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getPresentationPackageSuffix <em>Presentation Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Presentation Package Suffix</em>' attribute.
   * @see #getPresentationPackageSuffix()
   * @generated
   */
  void setPresentationPackageSuffix(String value);

  /**
   * Returns the value of the '<em><b>Tests Package Suffix</b></em>' attribute.
   * The default value is <code>"tests"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tests Package Suffix</em>' attribute.
   * @see #setTestsPackageSuffix(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_TestsPackageSuffix()
   * @model default="tests"
   * @generated
   */
  String getTestsPackageSuffix();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getTestsPackageSuffix <em>Tests Package Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tests Package Suffix</em>' attribute.
   * @see #getTestsPackageSuffix()
   * @generated
   */
  void setTestsPackageSuffix(String value);

  /**
   * Returns the value of the '<em><b>Generate Example Class</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Generate Example Class</em>' attribute.
   * @see #setGenerateExampleClass(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenerateExampleClass()
   * @model default="true"
   * @generated
   */
  boolean isGenerateExampleClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isGenerateExampleClass <em>Generate Example Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Generate Example Class</em>' attribute.
   * @see #isGenerateExampleClass()
   * @generated
   */
  void setGenerateExampleClass(boolean value);

  /**
   * Returns the value of the '<em><b>Literals Interface</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Literals Interface</em>' attribute.
   * @see #setLiteralsInterface(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_LiteralsInterface()
   * @model default="true"
   * @generated
   */
  boolean isLiteralsInterface();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isLiteralsInterface <em>Literals Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Literals Interface</em>' attribute.
   * @see #isLiteralsInterface()
   * @generated
   */
  void setLiteralsInterface(boolean value);

  /**
   * Returns the value of the '<em><b>Data Type Converters</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Type Converters</em>' attribute.
   * @see #setDataTypeConverters(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_DataTypeConverters()
   * @model
   * @generated
   */
  boolean isDataTypeConverters();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isDataTypeConverters <em>Data Type Converters</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Type Converters</em>' attribute.
   * @see #isDataTypeConverters()
   * @generated
   */
  void setDataTypeConverters(boolean value);

  /**
   * Returns the value of the '<em><b>Multiple Editor Pages</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Multiple Editor Pages</em>' attribute.
   * @see #setMultipleEditorPages(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_MultipleEditorPages()
   * @model default="true"
   * @generated
   */
  boolean isMultipleEditorPages();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isMultipleEditorPages <em>Multiple Editor Pages</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Multiple Editor Pages</em>' attribute.
   * @see #isMultipleEditorPages()
   * @generated
   */
  void setMultipleEditorPages(boolean value);

  /**
   * Returns the value of the '<em><b>Generate Model Wizard</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Generate Model Wizard</em>' attribute.
   * @see #setGenerateModelWizard(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenerateModelWizard()
   * @model default="true"
   * @generated
   */
  boolean isGenerateModelWizard();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isGenerateModelWizard <em>Generate Model Wizard</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Generate Model Wizard</em>' attribute.
   * @see #isGenerateModelWizard()
   * @generated
   */
  void setGenerateModelWizard(boolean value);

  /**
   * Returns the value of the '<em><b>Extensible Provider Factory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.4
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Extensible Provider Factory</em>' attribute.
   * @see #setExtensibleProviderFactory(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_ExtensibleProviderFactory()
   * @model
   * @generated
   */
  boolean isExtensibleProviderFactory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isExtensibleProviderFactory <em>Extensible Provider Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Extensible Provider Factory</em>' attribute.
   * @see #isExtensibleProviderFactory()
   * @generated
   */
  void setExtensibleProviderFactory(boolean value);

  /**
   * Returns the value of the '<em><b>Child Creation Extenders</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.4
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Child Creation Extenders</em>' attribute.
   * @see #setChildCreationExtenders(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_ChildCreationExtenders()
   * @model
   * @generated
   */
  boolean isChildCreationExtenders();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isChildCreationExtenders <em>Child Creation Extenders</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Child Creation Extenders</em>' attribute.
   * @see #isChildCreationExtenders()
   * @generated
   */
  void setChildCreationExtenders(boolean value);

  /**
   * Returns the value of the '<em><b>Content Type Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * A content type identifier against which to register the resource factory for this package.
   * @since 2.4
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Content Type Identifier</em>' attribute.
   * @see #setContentTypeIdentifier(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_ContentTypeIdentifier()
   * @model
   * @generated
   */
  String getContentTypeIdentifier();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getContentTypeIdentifier <em>Content Type Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Content Type Identifier</em>' attribute.
   * @see #getContentTypeIdentifier()
   * @generated
   */
  void setContentTypeIdentifier(String value);

  /**
   * Returns the value of the '<em><b>File Extensions</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * The file extensions against which to register the resource factory for this package. Multiple extensions can be
   * specified as a comma-separated list, but only if this package defines a {@link #isContentType content type}.
   * This will always return a non-null string. The default (returned after the attribute is set to null) is based on
   * the {@link #getPrefix}. 
   * @since 2.4
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>File Extensions</em>' attribute.
   * @see #setFileExtensions(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_FileExtensions()
   * @model
   * @generated
   */
  String getFileExtensions();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getFileExtensions <em>File Extensions</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>File Extensions</em>' attribute.
   * @see #getFileExtensions()
   * @generated
   */
  void setFileExtensions(String value);

  /**
   * Returns the value of the '<em><b>Ecore Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ecore Package</em>' reference.
   * @see #setEcorePackage(EPackage)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_EcorePackage()
   * @model required="true"
   * @generated
   */
  EPackage getEcorePackage();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getEcorePackage <em>Ecore Package</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ecore Package</em>' reference.
   * @see #getEcorePackage()
   * @generated
   */
  void setEcorePackage(EPackage value);

  /**
   * Returns the value of the '<em><b>Gen Model</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenModel#getGenPackages <em>Gen Packages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Model</em>' container reference.
   * @see #setGenModel(GenModel)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenModel()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModel#getGenPackages
   * @model opposite="genPackages" required="true"
   * @generated
   */
  GenModel getGenModel();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenModel <em>Gen Model</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Gen Model</em>' container reference.
   * @see #getGenModel()
   * @generated
   */
  void setGenModel(GenModel value);

  /**
   * Returns the value of the '<em><b>Gen Enums</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Enums</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenEnums()
   * @model containment="true"
   * @generated
   */
  EList<GenEnum> getGenEnums();

  /**
   * Returns the value of the '<em><b>Gen Data Types</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenDataType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Data Types</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenDataTypes()
   * @model containment="true"
   * @generated
   */
  EList<GenDataType> getGenDataTypes();

  /**
   * Returns the value of the '<em><b>Gen Classes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenClass}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Classes</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenClasses()
   * @model containment="true"
   * @generated
   */
  EList<GenClass> getGenClasses();

  /**
   * Returns the value of the '<em><b>Nested Gen Packages</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nested Gen Packages</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_NestedGenPackages()
   * @model containment="true"
   * @generated
   */
  EList<GenPackage> getNestedGenPackages();

  /**
   * Returns the value of the '<em><b>Gen Classifiers</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenPackage <em>Gen Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Classifiers</em>' reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenClassifiers()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenPackage
   * @model opposite="genPackage" resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  EList<GenClassifier> getGenClassifiers();

  String getInterfacePackageName();
  String getReflectionPackageName();
  String getReflectionClassPackageName();
  String getClassPackageName();
  String getUtilitiesPackageName();
  String getTestsPackageName();

  // String getPackageID();
  String getNSName();
  String getNSURI();
  String getPackageName();
  String getQualifiedPackageName();

  String getPackageInterfaceName();
  String getQualifiedPackageInterfaceName();
  String getImportedPackageInterfaceName();
  String getUncapPackageInterfaceName();
  String getPackageClassName();
  String getQualifiedPackageClassName();
  String getImportedPackageClassName();

  String getFactoryInterfaceName();
  String getQualifiedFactoryInterfaceName();
  String getImportedFactoryInterfaceName();
  String getUncapFactoryInterfaceName();
  String getFactoryClassName();
  String getQualifiedFactoryClassName();
  String getImportedFactoryClassName();
  String getFactoryInstanceName();
  String getQualifiedFactoryInstanceAccessor();
  String getQualifiedEFactoryInstanceAccessor();
  String getQualifiedEFactoryInternalInstanceAccessor();

  String getAdapterFactoryClassName();
  String getQualifiedAdapterFactoryClassName();
  String getImportedAdapterFactoryClassName();
  String getUncapAdapterFactoryClassName();

  String getSwitchClassName();
  String getQualifiedSwitchClassName();
  String getValidatorClassName();
  String getQualifiedValidatorClassName();
  String getImportedValidatorClassName();

  String getTestSuiteClassName();
  String getQualifiedTestSuiteClassName();
  String getImportedTestSuiteClassName();
  String getExampleClassName();
  String getQualifiedExampleClassName();

  String getQualifiedXMLProcessorClassName();
  String getXMLProcessorClassName();
  String getImportedXMLProcessorBaseClassName();

  List<GenDataType> getAllGenDataTypes();

  /**
   * Returns the list of GenClasses in the GenPackage, ordered such that
   * a class always follows all of the classes it extends.  Appropriate
   * for use in package interface to declare feature constants, which will
   * depend on (and hence must follow) constants for the extended class.
   */
  List<GenClass> getOrderedGenClasses();
  List<GenClassifier> getOrderedGenClassifiers();

  String getClassifierID(GenClassifier genClassifier);
  int getClassifierValue(GenClassifier genClassifier);
  int getLocalClassifierIndex(GenClassifier genClassifier);

  List<GenPackage> getPackageSimpleDependencies();
  List<GenPackage> getPackageInterDependencies();
  List<GenPackage> getPackageLoadInterDependencies();
  List<GenPackage> getPackageBuildInterDependencies();
  List<GenPackage> getPackageInitializationDependencies();
  String getPackageInstanceVariable(GenPackage genPackage);

  /**
   * Returns a list of those nestedGenPackages for which hasClassifiers() is
   * true.
   */
  List<GenPackage> getSubGenPackages();

  /**
   * Returns the closest GenPackage-typed container for which
   * hasClassifiers() is true, or null if there is none.
   */
  GenPackage getSuperGenPackage();

  /**
   * Returns the top-most GenPackage-typed container for which
   * hasClassifiers() is true, which may be this GenPackage, itself.
   */
  GenPackage getRootGenPackage();
  
  /**
   * Returns true if this is a root package that requires, or has a direct or indirect subpackage that requires, load initialization.
   */
  boolean isLoadingInitialization();
  
  /**
   * Returns true if this package's root, which may be itself, requires, or has a subpackage that requires, load initialization.
   */
  boolean isLoadedInitialization();
  
  boolean isEcorePackage();
  boolean hasInterfaceImplConflict();
  boolean hasJavaLangConflict();
  List<String> getJavaLangConflicts();
  boolean hasClassifiers();
  boolean hasClassifiers(boolean traverseNestedPackages);

  List<GenClass> getAllSwitchGenClasses();
  String getClassUniqueName(GenClass genClass);

  List<GenPackage> getAllValidatorBaseGenPackages();
  String getValidatorPackageUniqueSafeName(GenPackage genPackage);

  void initialize(EPackage ePackage);

  void prepareCache();
  void clearCache();

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  void generateSchema();

  //
  // EMFEdit generation
  //

  String getProviderPackageName();
  String getPresentationPackageName();

  String getItemProviderAdapterFactoryClassName();
  String getQualifiedItemProviderAdapterFactoryClassName();
  String getImportedItemProviderAdapterFactoryClassName();
  String getEditorClassName();
  String getQualifiedEditorClassName();
  String getImportedEditorClassName();
  String getModelWizardClassName();
  String getQualifiedModelWizardClassName();
  String getImportedModelWizardClassName();
  String getActionBarContributorClassName();
  String getQualifiedActionBarContributorClassName();
  String getImportedActionBarContributorClassName();

  String getAdapterFactoryDelegateName(GenPackage genDelegate);
  String getUncapAdapterFactoryDelegateName(GenPackage genDelegate);

  String getEditPluginClassName();
  String getQualifiedEditPluginClassName();
  String getImportedEditPluginClassName();

  String getEditorPluginClassName();
  String getQualifiedEditorPluginClassName();
  String getImportedEditorPluginClassName();

  String getModelIconFileName();
  String getModelWizardIconFileName();

  // String getCapPackageID();
  List<GenFeature> getAllGenFeatures(); // Includes features in other packages that delegate to those in this one.

  List<GenPackage> getAdapterDelegatePackages();
  List<GenClass> getAdapterDelegateSuperClasses();

  boolean hasStatefulProvider();

  String getModelInfo();

  boolean reconcile(GenPackage oldGenPackageVersion);

  List<String> getAnnotationSources();
  String getAnnotationSourceIdentifier(String annotationSource);
  List<EAnnotation> getAllAnnotations();
  String getAnnotatedModelElementAccessor(EAnnotation eAnnotation);
  List<EAnnotation> getAllNestedAnnotations(EAnnotation eAnnotation);

  String getResourceClassName();
  String getQualifiedResourceClassName();
  String getImportedResourceClassName();
  String getImportedResourceBaseClassName();

  String getResourceFactoryClassName();
  String getQualifiedResourceFactoryClassName();
  String getImportedResourceFactoryClassName();
  String getImportedResourceFactoryBaseClassName();

  boolean hasXMLMap();
  boolean hasDocumentRoot();
  GenClass getDocumentRoot();
  boolean hasExtendedMetaData();
  boolean hasTargetNamespace();

  boolean hasConstraints();

  String getSerializedPackageFilename();
  
  List<String> getProviderSupportedTypes();
  
  GenClass getRootClass();
  GenFeature getRootFeature();
  
  boolean hasConcreteClasses();
  
  boolean hasTests();

  /**
   * Returns a nested map structure describing the child creation extensions that this package provides to other packages.
   * The result maps other packages to their classes that have child creation extension provided by this package.
   * Each inner map maps from the class to the list of child creation data for these extensions.
   * @since 2.4
   */
  Map<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> getExtendedChildCreationData();

  /**
   * @since 2.4
   */
  String getChildCreationExtenderName(GenPackage genPackage);

  /**
   * Returns whether the package should define a content type.
   * @see #getContentTypeIdentifier
   * @since 2.4
   */
  boolean isContentType();

  /**
   * Returns whether the package's {@link #getResource() resource} produces an XMI serialization.
   * @see #getResource
   * @since 2.4
   */
  boolean isXMIResource();

  /**
   * Returns the package's {@link #getQualifiedResourceFactoryClassName() resource factory class name}, or the framework's
   * default XMI resource factory class name if no {@link #getResource() resource} will be generated for the package.
   * @see #getQualifiedResourceFactoryClassName
   * @see #getResource 
   * @since 2.4
   */
  String getQualifiedEffectiveResourceFactoryClassName();

  /**
   * Returns whether the model supports multiple file extensions, based both on the specified
   * {@link #getFileExtensions() extensions} and the use of a {@link #isContentType() content type}.
   * @see #getFileExtensions
   * @see #isContentType
   */
  boolean isMultipleFileExtensions();

  /**
   * Returns the first {@link #getFileExtensions() file extension} in the comma-separated list.
   * @see #getFileExtensions
   * @since 2.4
   */
  String getFileExtension(); // getFirstFileExtension?
}
