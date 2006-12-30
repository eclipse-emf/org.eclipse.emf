/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: PersonalPackageImpl.java,v 1.2 2006/12/30 03:44:08 marcelop Exp $
 */
package org.eclipse.emf.test.models.personal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.personal.ContrType;
import org.eclipse.emf.test.models.personal.DocumentRoot;
import org.eclipse.emf.test.models.personal.LinkType;
import org.eclipse.emf.test.models.personal.NameType;
import org.eclipse.emf.test.models.personal.PersonType;
import org.eclipse.emf.test.models.personal.PersonalFactory;
import org.eclipse.emf.test.models.personal.PersonalPackage;
import org.eclipse.emf.test.models.personal.PersonnelType;
import org.eclipse.emf.test.models.personal.UrlType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PersonalPackageImpl extends EPackageImpl implements PersonalPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass documentRootEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass linkTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nameTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass personnelTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass personTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass urlTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum contrTypeEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType contrTypeObjectEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.emf.test.models.personal.PersonalPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private PersonalPackageImpl()
  {
    super(eNS_URI, PersonalFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this
   * model, and for any others upon which it depends.  Simple
   * dependencies are satisfied by calling this method on all
   * dependent packages before doing anything else.  This method drives
   * initialization for interdependent packages directly, in parallel
   * with this package, itself.
   * <p>Of this package and its interdependencies, all packages which
   * have not yet been registered by their URI values are first created
   * and registered.  The packages are then initialized in two steps:
   * meta-model objects for all of the packages are created before any
   * are initialized, since one package's meta-model objects may refer to
   * those of another.
   * <p>Invocation of this method will not affect any packages that have
   * already been initialized.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static PersonalPackage init()
  {
    if (isInited) return (PersonalPackage)EPackage.Registry.INSTANCE.getEPackage(PersonalPackage.eNS_URI);

    // Obtain or create and register package
    PersonalPackageImpl thePersonalPackage = (PersonalPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof PersonalPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new PersonalPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    thePersonalPackage.createPackageContents();

    // Initialize created meta-data
    thePersonalPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    thePersonalPackage.freeze();

    return thePersonalPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDocumentRoot()
  {
    return documentRootEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_Mixed()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_XMLNSPrefixMap()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_XSISchemaLocation()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_Email()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_Family()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_Given()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_Link()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_Name()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_Person()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_Personnel()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_Url()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLinkType()
  {
    return linkTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLinkType_Manager()
  {
    return (EAttribute)linkTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLinkType_Subordinates()
  {
    return (EAttribute)linkTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNameType()
  {
    return nameTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNameType_Family()
  {
    return (EAttribute)nameTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNameType_Given()
  {
    return (EAttribute)nameTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPersonnelType()
  {
    return personnelTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersonnelType_Person()
  {
    return (EReference)personnelTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPersonType()
  {
    return personTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersonType_Name()
  {
    return (EReference)personTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPersonType_Email()
  {
    return (EAttribute)personTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersonType_Url()
  {
    return (EReference)personTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersonType_Link()
  {
    return (EReference)personTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPersonType_Contr()
  {
    return (EAttribute)personTypeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPersonType_Id()
  {
    return (EAttribute)personTypeEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPersonType_Salary()
  {
    return (EAttribute)personTypeEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUrlType()
  {
    return urlTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getUrlType_Href()
  {
    return (EAttribute)urlTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getContrType()
  {
    return contrTypeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getContrTypeObject()
  {
    return contrTypeObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersonalFactory getPersonalFactory()
  {
    return (PersonalFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    documentRootEClass = createEClass(DOCUMENT_ROOT);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__EMAIL);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__FAMILY);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__GIVEN);
    createEReference(documentRootEClass, DOCUMENT_ROOT__LINK);
    createEReference(documentRootEClass, DOCUMENT_ROOT__NAME);
    createEReference(documentRootEClass, DOCUMENT_ROOT__PERSON);
    createEReference(documentRootEClass, DOCUMENT_ROOT__PERSONNEL);
    createEReference(documentRootEClass, DOCUMENT_ROOT__URL);

    linkTypeEClass = createEClass(LINK_TYPE);
    createEAttribute(linkTypeEClass, LINK_TYPE__MANAGER);
    createEAttribute(linkTypeEClass, LINK_TYPE__SUBORDINATES);

    nameTypeEClass = createEClass(NAME_TYPE);
    createEAttribute(nameTypeEClass, NAME_TYPE__FAMILY);
    createEAttribute(nameTypeEClass, NAME_TYPE__GIVEN);

    personnelTypeEClass = createEClass(PERSONNEL_TYPE);
    createEReference(personnelTypeEClass, PERSONNEL_TYPE__PERSON);

    personTypeEClass = createEClass(PERSON_TYPE);
    createEReference(personTypeEClass, PERSON_TYPE__NAME);
    createEAttribute(personTypeEClass, PERSON_TYPE__EMAIL);
    createEReference(personTypeEClass, PERSON_TYPE__URL);
    createEReference(personTypeEClass, PERSON_TYPE__LINK);
    createEAttribute(personTypeEClass, PERSON_TYPE__CONTR);
    createEAttribute(personTypeEClass, PERSON_TYPE__ID);
    createEAttribute(personTypeEClass, PERSON_TYPE__SALARY);

    urlTypeEClass = createEClass(URL_TYPE);
    createEAttribute(urlTypeEClass, URL_TYPE__HREF);

    // Create enums
    contrTypeEEnum = createEEnum(CONTR_TYPE);

    // Create data types
    contrTypeObjectEDataType = createEDataType(CONTR_TYPE_OBJECT);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_Email(), theXMLTypePackage.getString(), "email", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_Family(), theXMLTypePackage.getString(), "family", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_Given(), theXMLTypePackage.getString(), "given", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Link(), this.getLinkType(), null, "link", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Name(), this.getNameType(), null, "name", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Person(), this.getPersonType(), null, "person", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Personnel(), this.getPersonnelType(), null, "personnel", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Url(), this.getUrlType(), null, "url", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    initEClass(linkTypeEClass, LinkType.class, "LinkType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLinkType_Manager(), theXMLTypePackage.getIDREF(), "manager", null, 0, 1, LinkType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLinkType_Subordinates(), theXMLTypePackage.getIDREFS(), "subordinates", null, 0, 1, LinkType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nameTypeEClass, NameType.class, "NameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNameType_Family(), theXMLTypePackage.getString(), "family", null, 1, 1, NameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNameType_Given(), theXMLTypePackage.getString(), "given", null, 1, 1, NameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(personnelTypeEClass, PersonnelType.class, "PersonnelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPersonnelType_Person(), this.getPersonType(), null, "person", null, 1, -1, PersonnelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(personTypeEClass, PersonType.class, "PersonType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPersonType_Name(), this.getNameType(), null, "name", null, 1, 1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPersonType_Email(), theXMLTypePackage.getString(), "email", null, 0, -1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPersonType_Url(), this.getUrlType(), null, "url", null, 0, -1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPersonType_Link(), this.getLinkType(), null, "link", null, 0, 1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPersonType_Contr(), this.getContrType(), "contr", "false", 0, 1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPersonType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPersonType_Salary(), theXMLTypePackage.getInteger(), "salary", null, 0, 1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(urlTypeEClass, UrlType.class, "UrlType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getUrlType_Href(), theXMLTypePackage.getString(), "href", "http://", 0, 1, UrlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(contrTypeEEnum, ContrType.class, "ContrType");
    addEEnumLiteral(contrTypeEEnum, ContrType.TRUE_LITERAL);
    addEEnumLiteral(contrTypeEEnum, ContrType.FALSE_LITERAL);

    // Initialize data types
    initEDataType(contrTypeObjectEDataType, ContrType.class, "ContrTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
    createExtendedMetaDataAnnotations();
  }

  /**
   * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createExtendedMetaDataAnnotations()
  {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
    addAnnotation
      (contrTypeEEnum, 
       source, 
       new String[] 
       {
       "name", "contr_._type"
       });		
    addAnnotation
      (contrTypeObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "contr_._type:Object",
       "baseType", "contr_._type"
       });		
    addAnnotation
      (documentRootEClass, 
       source, 
       new String[] 
       {
       "name", "",
       "kind", "mixed"
       });		
    addAnnotation
      (getDocumentRoot_Mixed(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "name", ":mixed"
       });		
    addAnnotation
      (getDocumentRoot_XMLNSPrefixMap(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "xmlns:prefix"
       });		
    addAnnotation
      (getDocumentRoot_XSISchemaLocation(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "xsi:schemaLocation"
       });		
    addAnnotation
      (getDocumentRoot_Email(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "email",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Family(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "family",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Given(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "given",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Link(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "link",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Name(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "name",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Person(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "person",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Personnel(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "personnel",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_Url(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "url",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (linkTypeEClass, 
       source, 
       new String[] 
       {
       "name", "linkType",
       "kind", "empty"
       });		
    addAnnotation
      (getLinkType_Manager(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "manager"
       });		
    addAnnotation
      (getLinkType_Subordinates(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "subordinates"
       });		
    addAnnotation
      (nameTypeEClass, 
       source, 
       new String[] 
       {
       "name", "nameType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getNameType_Family(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "family",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getNameType_Given(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "given",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (personnelTypeEClass, 
       source, 
       new String[] 
       {
       "name", "personnel_._type",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getPersonnelType_Person(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "person",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (personTypeEClass, 
       source, 
       new String[] 
       {
       "name", "personType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getPersonType_Name(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "name",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getPersonType_Email(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "email",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getPersonType_Url(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "url",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getPersonType_Link(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "link",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getPersonType_Contr(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "contr"
       });		
    addAnnotation
      (getPersonType_Id(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "id"
       });		
    addAnnotation
      (getPersonType_Salary(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "salary"
       });		
    addAnnotation
      (urlTypeEClass, 
       source, 
       new String[] 
       {
       "name", "url_._type",
       "kind", "empty"
       });		
    addAnnotation
      (getUrlType_Href(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "href"
       });
  }

} //PersonalPackageImpl
