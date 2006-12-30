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
 * $Id: IpoPackageImpl.java,v 1.3 2006/12/30 07:13:48 marcelop Exp $
 */
package com.example.ipo.impl;

import com.example.ipo.Address;
import com.example.ipo.DocumentRoot;
import com.example.ipo.IpoFactory;
import com.example.ipo.IpoPackage;
import com.example.ipo.ItemType;
import com.example.ipo.Items;
import com.example.ipo.PurchaseOrderType;
import com.example.ipo.UKAddress;
import com.example.ipo.USAddress;
import com.example.ipo.USState;

import com.example.ipo.util.IpoValidator;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IpoPackageImpl extends EPackageImpl implements IpoPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass addressEClass = null;

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
	private EClass itemsEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass itemTypeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass purchaseOrderTypeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass ukAddressEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass usAddressEClass = null;

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
	private EDataType postcodeEDataType = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EDataType quantityTypeEDataType = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EDataType skuEDataType = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EDataType ukPostcodeEDataType = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EDataType usStateObjectEDataType = null;

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
   * @see com.example.ipo.IpoPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private IpoPackageImpl() {
    super(eNS_URI, IpoFactory.eINSTANCE);
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
	public static IpoPackage init() {
    if (isInited) return (IpoPackage)EPackage.Registry.INSTANCE.getEPackage(IpoPackage.eNS_URI);

    // Obtain or create and register package
    IpoPackageImpl theIpoPackage = (IpoPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof IpoPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new IpoPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theIpoPackage.createPackageContents();

    // Initialize created meta-data
    theIpoPackage.initializePackageContents();

    // Register package validator
    EValidator.Registry.INSTANCE.put
      (theIpoPackage, 
       new EValidator.Descriptor()
       {
         public EValidator getEValidator()
         {
           return IpoValidator.INSTANCE;
         }
       });

    // Mark meta-data to indicate it can't be changed
    theIpoPackage.freeze();

    return theIpoPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getAddress() {
    return addressEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getAddress_Name() {
    return (EAttribute)addressEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getAddress_Street() {
    return (EAttribute)addressEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getAddress_City() {
    return (EAttribute)addressEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getDocumentRoot() {
    return documentRootEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getDocumentRoot_Mixed() {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDocumentRoot_XSISchemaLocation() {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getDocumentRoot_Comment() {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getDocumentRoot_PurchaseOrder() {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getItems() {
    return itemsEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getItems_Item() {
    return (EReference)itemsEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getItemType() {
    return itemTypeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getItemType_ProductName() {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getItemType_Quantity() {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getItemType_USPrice() {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getItemType_Comment() {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getItemType_ShipDate() {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getItemType_PartNum() {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getPurchaseOrderType() {
    return purchaseOrderTypeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPurchaseOrderType_ShipTo() {
    return (EReference)purchaseOrderTypeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPurchaseOrderType_BillTo() {
    return (EReference)purchaseOrderTypeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getPurchaseOrderType_Comment() {
    return (EAttribute)purchaseOrderTypeEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getPurchaseOrderType_Items() {
    return (EReference)purchaseOrderTypeEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getPurchaseOrderType_OrderDate() {
    return (EAttribute)purchaseOrderTypeEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getUKAddress() {
    return ukAddressEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getUKAddress_Postcode() {
    return (EAttribute)ukAddressEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getUKAddress_ExportCode() {
    return (EAttribute)ukAddressEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getUSAddress() {
    return usAddressEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getUSAddress_State() {
    return (EAttribute)usAddressEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getUSAddress_Zip() {
    return (EAttribute)usAddressEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EEnum getUSState() {
    return usStateEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EDataType getPostcode() {
    return postcodeEDataType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EDataType getQuantityType() {
    return quantityTypeEDataType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EDataType getSKU() {
    return skuEDataType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EDataType getUKPostcode() {
    return ukPostcodeEDataType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EDataType getUSStateObject() {
    return usStateObjectEDataType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public IpoFactory getIpoFactory() {
    return (IpoFactory)getEFactoryInstance();
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
	public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    addressEClass = createEClass(ADDRESS);
    createEAttribute(addressEClass, ADDRESS__NAME);
    createEAttribute(addressEClass, ADDRESS__STREET);
    createEAttribute(addressEClass, ADDRESS__CITY);

    documentRootEClass = createEClass(DOCUMENT_ROOT);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__COMMENT);
    createEReference(documentRootEClass, DOCUMENT_ROOT__PURCHASE_ORDER);

    itemsEClass = createEClass(ITEMS);
    createEReference(itemsEClass, ITEMS__ITEM);

    itemTypeEClass = createEClass(ITEM_TYPE);
    createEAttribute(itemTypeEClass, ITEM_TYPE__PRODUCT_NAME);
    createEAttribute(itemTypeEClass, ITEM_TYPE__QUANTITY);
    createEAttribute(itemTypeEClass, ITEM_TYPE__US_PRICE);
    createEAttribute(itemTypeEClass, ITEM_TYPE__COMMENT);
    createEAttribute(itemTypeEClass, ITEM_TYPE__SHIP_DATE);
    createEAttribute(itemTypeEClass, ITEM_TYPE__PART_NUM);

    purchaseOrderTypeEClass = createEClass(PURCHASE_ORDER_TYPE);
    createEReference(purchaseOrderTypeEClass, PURCHASE_ORDER_TYPE__SHIP_TO);
    createEReference(purchaseOrderTypeEClass, PURCHASE_ORDER_TYPE__BILL_TO);
    createEAttribute(purchaseOrderTypeEClass, PURCHASE_ORDER_TYPE__COMMENT);
    createEReference(purchaseOrderTypeEClass, PURCHASE_ORDER_TYPE__ITEMS);
    createEAttribute(purchaseOrderTypeEClass, PURCHASE_ORDER_TYPE__ORDER_DATE);

    ukAddressEClass = createEClass(UK_ADDRESS);
    createEAttribute(ukAddressEClass, UK_ADDRESS__POSTCODE);
    createEAttribute(ukAddressEClass, UK_ADDRESS__EXPORT_CODE);

    usAddressEClass = createEClass(US_ADDRESS);
    createEAttribute(usAddressEClass, US_ADDRESS__STATE);
    createEAttribute(usAddressEClass, US_ADDRESS__ZIP);

    // Create enums
    usStateEEnum = createEEnum(US_STATE);

    // Create data types
    postcodeEDataType = createEDataType(POSTCODE);
    quantityTypeEDataType = createEDataType(QUANTITY_TYPE);
    skuEDataType = createEDataType(SKU);
    ukPostcodeEDataType = createEDataType(UK_POSTCODE);
    usStateObjectEDataType = createEDataType(US_STATE_OBJECT);
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
	public void initializePackageContents() {
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
    ukAddressEClass.getESuperTypes().add(this.getAddress());
    usAddressEClass.getESuperTypes().add(this.getAddress());

    // Initialize classes and features; add operations and parameters
    initEClass(addressEClass, Address.class, "Address", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAddress_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Address.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAddress_Street(), theXMLTypePackage.getString(), "street", null, 1, 1, Address.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAddress_City(), theXMLTypePackage.getString(), "city", null, 1, 1, Address.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_Comment(), theXMLTypePackage.getString(), "comment", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_PurchaseOrder(), this.getPurchaseOrderType(), null, "purchaseOrder", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    initEClass(itemsEClass, Items.class, "Items", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getItems_Item(), this.getItemType(), null, "item", null, 0, -1, Items.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(itemTypeEClass, ItemType.class, "ItemType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getItemType_ProductName(), theXMLTypePackage.getString(), "productName", null, 1, 1, ItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getItemType_Quantity(), this.getQuantityType(), "quantity", null, 1, 1, ItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getItemType_USPrice(), theXMLTypePackage.getDecimal(), "uSPrice", null, 1, 1, ItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getItemType_Comment(), theXMLTypePackage.getString(), "comment", null, 0, 1, ItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getItemType_ShipDate(), theXMLTypePackage.getDate(), "shipDate", null, 0, 1, ItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getItemType_PartNum(), this.getSKU(), "partNum", null, 1, 1, ItemType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(purchaseOrderTypeEClass, PurchaseOrderType.class, "PurchaseOrderType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPurchaseOrderType_ShipTo(), this.getAddress(), null, "shipTo", null, 1, 1, PurchaseOrderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPurchaseOrderType_BillTo(), this.getAddress(), null, "billTo", null, 1, 1, PurchaseOrderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPurchaseOrderType_Comment(), theXMLTypePackage.getString(), "comment", null, 0, 1, PurchaseOrderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPurchaseOrderType_Items(), this.getItems(), null, "items", null, 1, 1, PurchaseOrderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPurchaseOrderType_OrderDate(), theXMLTypePackage.getDateTime(), "orderDate", null, 0, 1, PurchaseOrderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ukAddressEClass, UKAddress.class, "UKAddress", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getUKAddress_Postcode(), this.getUKPostcode(), "postcode", null, 1, 1, UKAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getUKAddress_ExportCode(), theXMLTypePackage.getPositiveInteger(), "exportCode", "1", 0, 1, UKAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(usAddressEClass, USAddress.class, "USAddress", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getUSAddress_State(), this.getUSState(), "state", "AK", 1, 1, USAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getUSAddress_Zip(), theXMLTypePackage.getPositiveInteger(), "zip", null, 1, 1, USAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(usStateEEnum, USState.class, "USState");
    addEEnumLiteral(usStateEEnum, USState.AK_LITERAL);
    addEEnumLiteral(usStateEEnum, USState.AL_LITERAL);
    addEEnumLiteral(usStateEEnum, USState.AR_LITERAL);
    addEEnumLiteral(usStateEEnum, USState.PA_LITERAL);

    // Initialize data types
    initEDataType(postcodeEDataType, String.class, "Postcode", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(quantityTypeEDataType, BigInteger.class, "QuantityType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(skuEDataType, String.class, "SKU", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(ukPostcodeEDataType, String.class, "UKPostcode", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(usStateObjectEDataType, USState.class, "USStateObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

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
	protected void createExtendedMetaDataAnnotations() {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";			
    addAnnotation
      (addressEClass, 
       source, 
       new String[] 
       {
       "name", "Address",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getAddress_Name(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "name"
       });		
    addAnnotation
      (getAddress_Street(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "street"
       });		
    addAnnotation
      (getAddress_City(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "city"
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
      (getDocumentRoot_Comment(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "comment",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_PurchaseOrder(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "purchaseOrder",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (itemsEClass, 
       source, 
       new String[] 
       {
       "name", "Items",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getItems_Item(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "item"
       });		
    addAnnotation
      (itemTypeEClass, 
       source, 
       new String[] 
       {
       "name", "item_._type",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getItemType_ProductName(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "productName"
       });		
    addAnnotation
      (getItemType_Quantity(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "quantity"
       });		
    addAnnotation
      (getItemType_USPrice(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "USPrice"
       });		
    addAnnotation
      (getItemType_Comment(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "comment",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getItemType_ShipDate(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "shipDate"
       });		
    addAnnotation
      (getItemType_PartNum(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "partNum"
       });		
    addAnnotation
      (postcodeEDataType, 
       source, 
       new String[] 
       {
       "name", "Postcode",
       "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
       "length", "7"
       });		
    addAnnotation
      (purchaseOrderTypeEClass, 
       source, 
       new String[] 
       {
       "name", "PurchaseOrderType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getPurchaseOrderType_ShipTo(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "shipTo"
       });		
    addAnnotation
      (getPurchaseOrderType_BillTo(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "billTo"
       });		
    addAnnotation
      (getPurchaseOrderType_Comment(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "comment",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getPurchaseOrderType_Items(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "items"
       });		
    addAnnotation
      (getPurchaseOrderType_OrderDate(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "orderDate"
       });		
    addAnnotation
      (quantityTypeEDataType, 
       source, 
       new String[] 
       {
       "name", "quantity_._type",
       "baseType", "http://www.eclipse.org/emf/2003/XMLType#positiveInteger",
       "maxExclusive", "100"
       });		
    addAnnotation
      (skuEDataType, 
       source, 
       new String[] 
       {
       "name", "SKU",
       "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
       "pattern", "\\d{3}-[A-Z]{2}"
       });		
    addAnnotation
      (ukAddressEClass, 
       source, 
       new String[] 
       {
       "name", "UKAddress",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getUKAddress_Postcode(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "postcode"
       });		
    addAnnotation
      (getUKAddress_ExportCode(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "exportCode"
       });		
    addAnnotation
      (ukPostcodeEDataType, 
       source, 
       new String[] 
       {
       "name", "UKPostcode",
       "baseType", "Postcode",
       "pattern", "[A-Z]{2}\\d\\s\\d[A-Z]{2}"
       });		
    addAnnotation
      (usAddressEClass, 
       source, 
       new String[] 
       {
       "name", "USAddress",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getUSAddress_State(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "state"
       });		
    addAnnotation
      (getUSAddress_Zip(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "zip"
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
  }

} //IpoPackageImpl
