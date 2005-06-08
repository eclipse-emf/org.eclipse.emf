/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: GenPackage.java,v 1.5.2.1 2005/06/08 18:27:45 nickb Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import java.util.List;

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
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isAdapterFactory <em>Adapter Factory</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#isLoadInitialization <em>Load Initialization</em>}</li>
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
public interface GenPackage extends GenBase{
  /**
   * Returns the value of the '<em><b>Prefix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prefix</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
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
   * <p>
   * If the meaning of the '<em>Base Package</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
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
   * <p>
   * If the meaning of the '<em>Resource</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
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
   * Returns the value of the '<em><b>Adapter Factory</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Adapter Factory</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
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
   * Returns the value of the '<em><b>Ecore Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ecore Package</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
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
   * <p>
   * If the meaning of the '<em>Gen Model</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
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
   * <p>
   * If the meaning of the '<em>Gen Enums</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Enums</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenEnums()
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenEnum" containment="true"
   * @generated
   */
  EList getGenEnums();

  /**
   * Returns the value of the '<em><b>Gen Data Types</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenDataType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gen Data Types</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Data Types</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenDataTypes()
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenDataType" containment="true"
   * @generated
   */
  EList getGenDataTypes();

  /**
   * Returns the value of the '<em><b>Gen Classes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenClass}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gen Classes</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Classes</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenClasses()
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenClass" containment="true"
   * @generated
   */
  EList getGenClasses();

  /**
   * Returns the value of the '<em><b>Nested Gen Packages</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nested Gen Packages</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nested Gen Packages</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_NestedGenPackages()
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenPackage" containment="true"
   * @generated
   */
  EList getNestedGenPackages();

  /**
   * Returns the value of the '<em><b>Gen Classifiers</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenPackage <em>Gen Package</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gen Meta Objects</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Classifiers</em>' reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPackage_GenClassifiers()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenPackage
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenClassifier" opposite="genPackage" resolveProxies="false" transient="true" changeable="false" volatile="true"
   * @generated
   */
  EList getGenClassifiers();

  String getInterfacePackageName();
  String getClassPackageName();
  String getUtilitiesPackageName();

  // String getPackageID();
  String getNSName();
  String getNSURI();
  String getPackageName();

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

  String getAdapterFactoryClassName();
  String getQualifiedAdapterFactoryClassName();
  String getImportedAdapterFactoryClassName();
  String getUncapAdapterFactoryClassName();

  String getSwitchClassName();
  String getValidatorClassName();
  String getImportedValidatorClassName();

  List getAllGenDataTypes();

  /**
   * Returns the list of GenClasses in the GenPackage, ordered such that
   * a class always follows all of the classes it extends.  Appropriate
   * for use in package interface to declare feature constants, which will
   * depend on (and hence must follow) constants for the extended class.
   */
  List getOrderedGenClasses();
  List getOrderedGenClassifiers();

  String getClassifierID(GenClassifier genClassifier);
  int getClassifierValue(GenClassifier genClassifier);
  int getLocalClassifierIndex(GenClassifier genClassifier);

  List /*of GenPackage*/ getPackageSimpleDependencies();
  List /*of GenPackage*/ getPackageInterDependencies();
  List /*of GenPackage*/ getPackageLoadInterDependencies();
  List /*of GenPackage*/ getPackageBuildInterDependencies();
  List /*of GenPackage*/ getPackageInitializationDependencies();
  String getPackageInstanceVariable(GenPackage genPackage);

  /**
   * Returns a list of those nestedGenPackages for which hasClassifiers() is
   * true.
   */
  List/*of GenPackage*/ getSubGenPackages();

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
  List getJavaLangConflicts();
  boolean hasClassifiers();

  List /*of GenClass*/ getAllSwitchGenClasses();
  String getClassUniqueName(GenClass genClass);

  List /*of GenPackage*/ getAllValidatorBaseGenPackages();
  String getValidatorPackageUniqueSafeName(GenPackage genPackage);

  void initialize(EPackage ePackage);

  /**
   * @deprecated Use {@link GenBase#generateSchema(IProgressMonitor)} instead.  This
   * method will be removed soon.
   */
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

  // String getCapPackageID();
  List/*GenFeature*/ getAllGenFeatures();

  List/*GenPackage*/ getAdapterDelegatePackages();
  List/*GenClass*/ getAdapterDelegateSuperClasses();

  boolean reconcile(GenPackage oldGenPackageVersion);

  List getAnnotationSources();
  String getAnnotationSourceIdentifier(String annotationSource);
  List getAllAnnotations();
  String getAnnotatedModelElementAccessor(EAnnotation eAnnotation);

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
  boolean hasExtendedMetaData();
  boolean hasTargetNamespace();

  boolean hasConstraints();

  String getSerializedPackageFilename();
}
