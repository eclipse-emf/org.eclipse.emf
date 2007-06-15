/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: CustomerPackageImpl.java,v 1.3 2007/06/15 21:22:17 emerks Exp $
 */
package org.eclipse.emf.test.models.customer.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.customer.AddressType;
import org.eclipse.emf.test.models.customer.CanadaAddr;
import org.eclipse.emf.test.models.customer.CreditInfo;
import org.eclipse.emf.test.models.customer.CustomerFactory;
import org.eclipse.emf.test.models.customer.CustomerPackage;
import org.eclipse.emf.test.models.customer.CustomerType;
import org.eclipse.emf.test.models.customer.CustomersType;
import org.eclipse.emf.test.models.customer.DocumentRoot;
import org.eclipse.emf.test.models.customer.USAddr;
import org.eclipse.emf.test.models.customer.USState;

import org.eclipse.emf.test.models.customer.util.CustomerValidator;

import org.eclipse.emf.test.models.movie.db.DBPackage;

import org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl;

import org.eclipse.emf.test.models.order.OrderPackage;

import org.eclipse.emf.test.models.order.impl.OrderPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CustomerPackageImpl extends EPackageImpl implements CustomerPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass addressTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass canadaAddrEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass creditInfoEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass customersTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass customerTypeEClass = null;

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
  private EClass usAddrEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum usStateEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType usStateObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType zipCodesEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType zipUnionEDataType = null;

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
   * @see org.eclipse.emf.test.models.customer.CustomerPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private CustomerPackageImpl()
  {
    super(eNS_URI, CustomerFactory.eINSTANCE);
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
  public static CustomerPackage init()
  {
    if (isInited) return (CustomerPackage)EPackage.Registry.INSTANCE.getEPackage(CustomerPackage.eNS_URI);

    // Obtain or create and register package
    CustomerPackageImpl theCustomerPackage = (CustomerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof CustomerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new CustomerPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Obtain or create and register interdependencies
    DBPackageImpl theDBPackage = (DBPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DBPackage.eNS_URI) instanceof DBPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DBPackage.eNS_URI) : DBPackage.eINSTANCE);
    OrderPackageImpl theOrderPackage = (OrderPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OrderPackage.eNS_URI) instanceof OrderPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OrderPackage.eNS_URI) : OrderPackage.eINSTANCE);

    // Create package meta-data objects
    theCustomerPackage.createPackageContents();
    theDBPackage.createPackageContents();
    theOrderPackage.createPackageContents();

    // Initialize created meta-data
    theCustomerPackage.initializePackageContents();
    theDBPackage.initializePackageContents();
    theOrderPackage.initializePackageContents();

    // Register package validator
    EValidator.Registry.INSTANCE.put
      (theCustomerPackage, 
       new EValidator.Descriptor()
       {
         public EValidator getEValidator()
         {
           return CustomerValidator.INSTANCE;
         }
       });

    // Mark meta-data to indicate it can't be changed
    theCustomerPackage.freeze();

    return theCustomerPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAddressType()
  {
    return addressTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAddressType_Street()
  {
    return (EAttribute)addressTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAddressType_Town()
  {
    return (EAttribute)addressTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCanadaAddr()
  {
    return canadaAddrEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCanadaAddr_Zip()
  {
    return (EAttribute)canadaAddrEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCanadaAddr_Province()
  {
    return (EAttribute)canadaAddrEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCreditInfo()
  {
    return creditInfoEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCreditInfo_Holder()
  {
    return (EAttribute)creditInfoEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCreditInfo_CcNumber()
  {
    return (EAttribute)creditInfoEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCreditInfo_ExpireDate()
  {
    return (EAttribute)creditInfoEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCustomersType()
  {
    return customersTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCustomersType_Mixed()
  {
    return (EAttribute)customersTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCustomersType_Customer()
  {
    return (EReference)customersTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCustomerType()
  {
    return customerTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCustomerType_Name()
  {
    return (EAttribute)customerTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCustomerType_Address()
  {
    return (EReference)customerTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCustomerType_CreditCard()
  {
    return (EReference)customerTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCustomerType_PaymentDay()
  {
    return (EAttribute)customerTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCustomerType_ID()
  {
    return (EAttribute)customerTypeEClass.getEStructuralFeatures().get(4);
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
  public EReference getDocumentRoot_Customers()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_ID()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUSAddr()
  {
    return usAddrEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getUSAddr_Zip()
  {
    return (EAttribute)usAddrEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getUSAddr_State()
  {
    return (EAttribute)usAddrEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getUSState()
  {
    return usStateEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getUSStateObject()
  {
    return usStateObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getZipCodes()
  {
    return zipCodesEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getZipUnion()
  {
    return zipUnionEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerFactory getCustomerFactory()
  {
    return (CustomerFactory)getEFactoryInstance();
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
    addressTypeEClass = createEClass(ADDRESS_TYPE);
    createEAttribute(addressTypeEClass, ADDRESS_TYPE__STREET);
    createEAttribute(addressTypeEClass, ADDRESS_TYPE__TOWN);

    canadaAddrEClass = createEClass(CANADA_ADDR);
    createEAttribute(canadaAddrEClass, CANADA_ADDR__ZIP);
    createEAttribute(canadaAddrEClass, CANADA_ADDR__PROVINCE);

    creditInfoEClass = createEClass(CREDIT_INFO);
    createEAttribute(creditInfoEClass, CREDIT_INFO__HOLDER);
    createEAttribute(creditInfoEClass, CREDIT_INFO__CC_NUMBER);
    createEAttribute(creditInfoEClass, CREDIT_INFO__EXPIRE_DATE);

    customersTypeEClass = createEClass(CUSTOMERS_TYPE);
    createEAttribute(customersTypeEClass, CUSTOMERS_TYPE__MIXED);
    createEReference(customersTypeEClass, CUSTOMERS_TYPE__CUSTOMER);

    customerTypeEClass = createEClass(CUSTOMER_TYPE);
    createEAttribute(customerTypeEClass, CUSTOMER_TYPE__NAME);
    createEReference(customerTypeEClass, CUSTOMER_TYPE__ADDRESS);
    createEReference(customerTypeEClass, CUSTOMER_TYPE__CREDIT_CARD);
    createEAttribute(customerTypeEClass, CUSTOMER_TYPE__PAYMENT_DAY);
    createEAttribute(customerTypeEClass, CUSTOMER_TYPE__ID);

    documentRootEClass = createEClass(DOCUMENT_ROOT);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    createEReference(documentRootEClass, DOCUMENT_ROOT__CUSTOMERS);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__ID);

    usAddrEClass = createEClass(US_ADDR);
    createEAttribute(usAddrEClass, US_ADDR__ZIP);
    createEAttribute(usAddrEClass, US_ADDR__STATE);

    // Create enums
    usStateEEnum = createEEnum(US_STATE);

    // Create data types
    usStateObjectEDataType = createEDataType(US_STATE_OBJECT);
    zipCodesEDataType = createEDataType(ZIP_CODES);
    zipUnionEDataType = createEDataType(ZIP_UNION);
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
    canadaAddrEClass.getESuperTypes().add(this.getAddressType());
    usAddrEClass.getESuperTypes().add(this.getAddressType());

    // Initialize classes and features; add operations and parameters
    initEClass(addressTypeEClass, AddressType.class, "AddressType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAddressType_Street(), theXMLTypePackage.getString(), "street", null, 1, 1, AddressType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAddressType_Town(), theXMLTypePackage.getString(), "town", null, 1, 1, AddressType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(canadaAddrEClass, CanadaAddr.class, "CanadaAddr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCanadaAddr_Zip(), theXMLTypePackage.getString(), "zip", null, 1, 1, CanadaAddr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCanadaAddr_Province(), theXMLTypePackage.getString(), "province", null, 1, 1, CanadaAddr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(creditInfoEClass, CreditInfo.class, "CreditInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCreditInfo_Holder(), theXMLTypePackage.getString(), "holder", null, 1, 1, CreditInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCreditInfo_CcNumber(), theXMLTypePackage.getInteger(), "ccNumber", null, 1, 1, CreditInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCreditInfo_ExpireDate(), theXMLTypePackage.getGYearMonth(), "expireDate", null, 1, 1, CreditInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(customersTypeEClass, CustomersType.class, "CustomersType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCustomersType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, CustomersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCustomersType_Customer(), this.getCustomerType(), null, "customer", null, 1, -1, CustomersType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    initEClass(customerTypeEClass, CustomerType.class, "CustomerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCustomerType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, CustomerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCustomerType_Address(), this.getAddressType(), null, "address", null, 1, 1, CustomerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCustomerType_CreditCard(), this.getCreditInfo(), null, "creditCard", null, 1, 1, CustomerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCustomerType_PaymentDay(), theXMLTypePackage.getGDay(), "paymentDay", null, 1, 1, CustomerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCustomerType_ID(), theXMLTypePackage.getID(), "iD", null, 1, 1, CustomerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Customers(), this.getCustomersType(), null, "customers", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_ID(), theXMLTypePackage.getID(), "iD", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(usAddrEClass, USAddr.class, "USAddr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getUSAddr_Zip(), this.getZipUnion(), "zip", null, 1, 1, USAddr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getUSAddr_State(), theXMLTypePackage.getString(), "state", null, 1, 1, USAddr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(usStateEEnum, USState.class, "USState");
    addEEnumLiteral(usStateEEnum, USState.AK);
    addEEnumLiteral(usStateEEnum, USState.AL);
    addEEnumLiteral(usStateEEnum, USState.AR);

    // Initialize data types
    initEDataType(usStateObjectEDataType, USState.class, "USStateObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
    initEDataType(zipCodesEDataType, BigInteger.class, "ZipCodes", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(zipUnionEDataType, Object.class, "ZipUnion", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

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
      (addressTypeEClass, 
       source, 
       new String[] 
       {
       "name", "addressType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getAddressType_Street(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "street"
       });		
    addAnnotation
      (getAddressType_Town(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "town"
       });		
    addAnnotation
      (canadaAddrEClass, 
       source, 
       new String[] 
       {
       "name", "canadaAddr",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getCanadaAddr_Zip(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "zip"
       });		
    addAnnotation
      (getCanadaAddr_Province(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "province"
       });		
    addAnnotation
      (creditInfoEClass, 
       source, 
       new String[] 
       {
       "name", "creditInfo",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getCreditInfo_Holder(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "holder"
       });		
    addAnnotation
      (getCreditInfo_CcNumber(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "cc_number"
       });		
    addAnnotation
      (getCreditInfo_ExpireDate(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "expireDate"
       });		
    addAnnotation
      (customersTypeEClass, 
       source, 
       new String[] 
       {
       "name", "customersType",
       "kind", "mixed"
       });		
    addAnnotation
      (getCustomersType_Mixed(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "name", ":mixed"
       });		
    addAnnotation
      (getCustomersType_Customer(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "customer",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (customerTypeEClass, 
       source, 
       new String[] 
       {
       "name", "dbcustomer",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getCustomerType_Name(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "name"
       });		
    addAnnotation
      (getCustomerType_Address(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "address"
       });		
    addAnnotation
      (getCustomerType_CreditCard(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "creditCard"
       });		
    addAnnotation
      (getCustomerType_PaymentDay(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "paymentDay"
       });		
    addAnnotation
      (getCustomerType_ID(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "ID",
       "namespace", "##targetNamespace"
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
      (getDocumentRoot_Customers(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "customers",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_ID(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "ID",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (usAddrEClass, 
       source, 
       new String[] 
       {
       "name", "USAddr",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getUSAddr_Zip(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "zip"
       });		
    addAnnotation
      (getUSAddr_State(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "state"
       });		
    addAnnotation
      (usStateEEnum, 
       source, 
       new String[] 
       {
       "name", "USState"
       });		
    addAnnotation
      (usStateObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "USState:Object",
       "baseType", "USState"
       });		
    addAnnotation
      (zipCodesEDataType, 
       source, 
       new String[] 
       {
       "name", "zipCodes",
       "baseType", "http://www.eclipse.org/emf/2003/XMLType#integer",
       "minInclusive", "10000",
       "maxInclusive", "99999"
       });		
    addAnnotation
      (zipUnionEDataType, 
       source, 
       new String[] 
       {
       "name", "zipUnion",
       "memberTypes", "USState zipCodes"
       });
  }

} //CustomerPackageImpl
