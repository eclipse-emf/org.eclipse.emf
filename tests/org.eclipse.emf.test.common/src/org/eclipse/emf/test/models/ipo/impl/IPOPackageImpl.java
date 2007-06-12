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
 * $Id: IPOPackageImpl.java,v 1.3 2007/06/12 15:08:11 emerks Exp $
 */
package org.eclipse.emf.test.models.ipo.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.ipo.Address;
import org.eclipse.emf.test.models.ipo.DocumentRoot;
import org.eclipse.emf.test.models.ipo.IPOFactory;
import org.eclipse.emf.test.models.ipo.ItemType;
import org.eclipse.emf.test.models.ipo.Items;
import org.eclipse.emf.test.models.ipo.PurchaseOrderType;
import org.eclipse.emf.test.models.ipo.UKAddress;
import org.eclipse.emf.test.models.ipo.USAddress;
import org.eclipse.emf.test.models.ipo.USState;

import org.eclipse.emf.test.models.ipo.util.IPOValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *       International Purchase order schema for Example.com
 *       Copyright 2000 Example.com. All rights reserved.
 *     
 * <!-- end-model-doc -->
 * @see org.eclipse.emf.test.models.ipo.IPOFactory
 * @model kind="package"
 * @generated
 */
public class IPOPackageImpl extends EPackageImpl
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNAME = "ipo";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_URI = "http:///org.eclipse.emf.test.models/IPO";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_PREFIX = "ipo";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final IPOPackageImpl eINSTANCE = org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ipo.impl.AddressImpl <em>Address</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ipo.impl.AddressImpl
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getAddress()
   * @generated
   */
  public static final int ADDRESS = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ADDRESS__NAME = 0;

  /**
   * The feature id for the '<em><b>Street</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ADDRESS__STREET = 1;

  /**
   * The feature id for the '<em><b>City</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ADDRESS__CITY = 2;

  /**
   * The number of structural features of the '<em>Address</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ADDRESS_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ipo.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ipo.impl.DocumentRootImpl
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getDocumentRoot()
   * @generated
   */
  public static final int DOCUMENT_ROOT = 1;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__MIXED = 0;

  /**
   * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

  /**
   * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__COMMENT = 3;

  /**
   * The feature id for the '<em><b>Purchase Order</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT__PURCHASE_ORDER = 4;

  /**
   * The number of structural features of the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int DOCUMENT_ROOT_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ipo.impl.ItemsImpl <em>Items</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ipo.impl.ItemsImpl
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getItems()
   * @generated
   */
  public static final int ITEMS = 2;

  /**
   * The feature id for the '<em><b>Item</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ITEMS__ITEM = 0;

  /**
   * The number of structural features of the '<em>Items</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ITEMS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ipo.impl.ItemTypeImpl <em>Item Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ipo.impl.ItemTypeImpl
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getItemType()
   * @generated
   */
  public static final int ITEM_TYPE = 3;

  /**
   * The feature id for the '<em><b>Product Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ITEM_TYPE__PRODUCT_NAME = 0;

  /**
   * The feature id for the '<em><b>Quantity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ITEM_TYPE__QUANTITY = 1;

  /**
   * The feature id for the '<em><b>US Price</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ITEM_TYPE__US_PRICE = 2;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ITEM_TYPE__COMMENT = 3;

  /**
   * The feature id for the '<em><b>Ship Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ITEM_TYPE__SHIP_DATE = 4;

  /**
   * The feature id for the '<em><b>Part Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ITEM_TYPE__PART_NUM = 5;

  /**
   * The number of structural features of the '<em>Item Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ITEM_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ipo.impl.PurchaseOrderTypeImpl <em>Purchase Order Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ipo.impl.PurchaseOrderTypeImpl
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getPurchaseOrderType()
   * @generated
   */
  public static final int PURCHASE_ORDER_TYPE = 4;

  /**
   * The feature id for the '<em><b>Ship To</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PURCHASE_ORDER_TYPE__SHIP_TO = 0;

  /**
   * The feature id for the '<em><b>Bill To</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PURCHASE_ORDER_TYPE__BILL_TO = 1;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PURCHASE_ORDER_TYPE__COMMENT = 2;

  /**
   * The feature id for the '<em><b>Items</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PURCHASE_ORDER_TYPE__ITEMS = 3;

  /**
   * The feature id for the '<em><b>Order Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PURCHASE_ORDER_TYPE__ORDER_DATE = 4;

  /**
   * The number of structural features of the '<em>Purchase Order Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PURCHASE_ORDER_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ipo.impl.UKAddressImpl <em>UK Address</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ipo.impl.UKAddressImpl
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getUKAddress()
   * @generated
   */
  public static final int UK_ADDRESS = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int UK_ADDRESS__NAME = ADDRESS__NAME;

  /**
   * The feature id for the '<em><b>Street</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int UK_ADDRESS__STREET = ADDRESS__STREET;

  /**
   * The feature id for the '<em><b>City</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int UK_ADDRESS__CITY = ADDRESS__CITY;

  /**
   * The feature id for the '<em><b>Postcode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int UK_ADDRESS__POSTCODE = ADDRESS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Export Code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int UK_ADDRESS__EXPORT_CODE = ADDRESS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>UK Address</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int UK_ADDRESS_FEATURE_COUNT = ADDRESS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ipo.impl.USAddressImpl <em>US Address</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ipo.impl.USAddressImpl
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getUSAddress()
   * @generated
   */
  public static final int US_ADDRESS = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int US_ADDRESS__NAME = ADDRESS__NAME;

  /**
   * The feature id for the '<em><b>Street</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int US_ADDRESS__STREET = ADDRESS__STREET;

  /**
   * The feature id for the '<em><b>City</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int US_ADDRESS__CITY = ADDRESS__CITY;

  /**
   * The feature id for the '<em><b>State</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int US_ADDRESS__STATE = ADDRESS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Zip</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int US_ADDRESS__ZIP = ADDRESS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>US Address</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int US_ADDRESS_FEATURE_COUNT = ADDRESS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ipo.USState <em>US State</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ipo.USState
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getUSState()
   * @generated
   */
  public static final int US_STATE = 7;

  /**
   * The meta object id for the '<em>Postcode</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getPostcode()
   * @generated
   */
  public static final int POSTCODE = 8;

  /**
   * The meta object id for the '<em>Quantity Type</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.math.BigInteger
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getQuantityType()
   * @generated
   */
  public static final int QUANTITY_TYPE = 9;

  /**
   * The meta object id for the '<em>SKU</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getSKU()
   * @generated
   */
  public static final int SKU = 10;

  /**
   * The meta object id for the '<em>UK Postcode</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getUKPostcode()
   * @generated
   */
  public static final int UK_POSTCODE = 11;

  /**
   * The meta object id for the '<em>US State Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ipo.USState
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getUSStateObject()
   * @generated
   */
  public static final int US_STATE_OBJECT = 12;

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
   * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#eNS_URI
   * @see #init()
   * @generated
   */
  private IPOPackageImpl()
  {
    super(eNS_URI, ((EFactory)IPOFactory.INSTANCE));
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
  public static IPOPackageImpl init()
  {
    if (isInited) return (IPOPackageImpl)EPackage.Registry.INSTANCE.getEPackage(IPOPackageImpl.eNS_URI);

    // Obtain or create and register package
    IPOPackageImpl theIPOPackageImpl = (IPOPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof IPOPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new IPOPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theIPOPackageImpl.createPackageContents();

    // Initialize created meta-data
    theIPOPackageImpl.initializePackageContents();

    // Register package validator
    EValidator.Registry.INSTANCE.put
      (theIPOPackageImpl, 
       new EValidator.Descriptor()
       {
         public EValidator getEValidator()
         {
           return IPOValidator.INSTANCE;
         }
       });

    // Mark meta-data to indicate it can't be changed
    theIPOPackageImpl.freeze();

    return theIPOPackageImpl;
  }


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ipo.Address <em>Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Address</em>'.
   * @see org.eclipse.emf.test.models.ipo.Address
   * @generated
   */
  public EClass getAddress()
  {
    return addressEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.Address#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.ipo.Address#getName()
   * @see #getAddress()
   * @generated
   */
  public EAttribute getAddress_Name()
  {
    return (EAttribute)addressEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.Address#getStreet <em>Street</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Street</em>'.
   * @see org.eclipse.emf.test.models.ipo.Address#getStreet()
   * @see #getAddress()
   * @generated
   */
  public EAttribute getAddress_Street()
  {
    return (EAttribute)addressEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.Address#getCity <em>City</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>City</em>'.
   * @see org.eclipse.emf.test.models.ipo.Address#getCity()
   * @see #getAddress()
   * @generated
   */
  public EAttribute getAddress_City()
  {
    return (EAttribute)addressEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ipo.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.eclipse.emf.test.models.ipo.DocumentRoot
   * @generated
   */
  public EClass getDocumentRoot()
  {
    return documentRootEClass;
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.ipo.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.ipo.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  public EAttribute getDocumentRoot_Mixed()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.ipo.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.eclipse.emf.test.models.ipo.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_XMLNSPrefixMap()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.ipo.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.eclipse.emf.test.models.ipo.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_XSISchemaLocation()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.DocumentRoot#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.eclipse.emf.test.models.ipo.DocumentRoot#getComment()
   * @see #getDocumentRoot()
   * @generated
   */
  public EAttribute getDocumentRoot_Comment()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ipo.DocumentRoot#getPurchaseOrder <em>Purchase Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Purchase Order</em>'.
   * @see org.eclipse.emf.test.models.ipo.DocumentRoot#getPurchaseOrder()
   * @see #getDocumentRoot()
   * @generated
   */
  public EReference getDocumentRoot_PurchaseOrder()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ipo.Items <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Items</em>'.
   * @see org.eclipse.emf.test.models.ipo.Items
   * @generated
   */
  public EClass getItems()
  {
    return itemsEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.ipo.Items#getItem <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Item</em>'.
   * @see org.eclipse.emf.test.models.ipo.Items#getItem()
   * @see #getItems()
   * @generated
   */
  public EReference getItems_Item()
  {
    return (EReference)itemsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ipo.ItemType <em>Item Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Item Type</em>'.
   * @see org.eclipse.emf.test.models.ipo.ItemType
   * @generated
   */
  public EClass getItemType()
  {
    return itemTypeEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.ItemType#getProductName <em>Product Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Product Name</em>'.
   * @see org.eclipse.emf.test.models.ipo.ItemType#getProductName()
   * @see #getItemType()
   * @generated
   */
  public EAttribute getItemType_ProductName()
  {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.ItemType#getQuantity <em>Quantity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Quantity</em>'.
   * @see org.eclipse.emf.test.models.ipo.ItemType#getQuantity()
   * @see #getItemType()
   * @generated
   */
  public EAttribute getItemType_Quantity()
  {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.ItemType#getUSPrice <em>US Price</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>US Price</em>'.
   * @see org.eclipse.emf.test.models.ipo.ItemType#getUSPrice()
   * @see #getItemType()
   * @generated
   */
  public EAttribute getItemType_USPrice()
  {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.ItemType#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.eclipse.emf.test.models.ipo.ItemType#getComment()
   * @see #getItemType()
   * @generated
   */
  public EAttribute getItemType_Comment()
  {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.ItemType#getShipDate <em>Ship Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ship Date</em>'.
   * @see org.eclipse.emf.test.models.ipo.ItemType#getShipDate()
   * @see #getItemType()
   * @generated
   */
  public EAttribute getItemType_ShipDate()
  {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.ItemType#getPartNum <em>Part Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Part Num</em>'.
   * @see org.eclipse.emf.test.models.ipo.ItemType#getPartNum()
   * @see #getItemType()
   * @generated
   */
  public EAttribute getItemType_PartNum()
  {
    return (EAttribute)itemTypeEClass.getEStructuralFeatures().get(5);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ipo.PurchaseOrderType <em>Purchase Order Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Purchase Order Type</em>'.
   * @see org.eclipse.emf.test.models.ipo.PurchaseOrderType
   * @generated
   */
  public EClass getPurchaseOrderType()
  {
    return purchaseOrderTypeEClass;
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ipo.PurchaseOrderType#getShipTo <em>Ship To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ship To</em>'.
   * @see org.eclipse.emf.test.models.ipo.PurchaseOrderType#getShipTo()
   * @see #getPurchaseOrderType()
   * @generated
   */
  public EReference getPurchaseOrderType_ShipTo()
  {
    return (EReference)purchaseOrderTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ipo.PurchaseOrderType#getBillTo <em>Bill To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bill To</em>'.
   * @see org.eclipse.emf.test.models.ipo.PurchaseOrderType#getBillTo()
   * @see #getPurchaseOrderType()
   * @generated
   */
  public EReference getPurchaseOrderType_BillTo()
  {
    return (EReference)purchaseOrderTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.PurchaseOrderType#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.eclipse.emf.test.models.ipo.PurchaseOrderType#getComment()
   * @see #getPurchaseOrderType()
   * @generated
   */
  public EAttribute getPurchaseOrderType_Comment()
  {
    return (EAttribute)purchaseOrderTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ipo.PurchaseOrderType#getItems <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Items</em>'.
   * @see org.eclipse.emf.test.models.ipo.PurchaseOrderType#getItems()
   * @see #getPurchaseOrderType()
   * @generated
   */
  public EReference getPurchaseOrderType_Items()
  {
    return (EReference)purchaseOrderTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.PurchaseOrderType#getOrderDate <em>Order Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Order Date</em>'.
   * @see org.eclipse.emf.test.models.ipo.PurchaseOrderType#getOrderDate()
   * @see #getPurchaseOrderType()
   * @generated
   */
  public EAttribute getPurchaseOrderType_OrderDate()
  {
    return (EAttribute)purchaseOrderTypeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ipo.UKAddress <em>UK Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>UK Address</em>'.
   * @see org.eclipse.emf.test.models.ipo.UKAddress
   * @generated
   */
  public EClass getUKAddress()
  {
    return ukAddressEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.UKAddress#getPostcode <em>Postcode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Postcode</em>'.
   * @see org.eclipse.emf.test.models.ipo.UKAddress#getPostcode()
   * @see #getUKAddress()
   * @generated
   */
  public EAttribute getUKAddress_Postcode()
  {
    return (EAttribute)ukAddressEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.UKAddress#getExportCode <em>Export Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Export Code</em>'.
   * @see org.eclipse.emf.test.models.ipo.UKAddress#getExportCode()
   * @see #getUKAddress()
   * @generated
   */
  public EAttribute getUKAddress_ExportCode()
  {
    return (EAttribute)ukAddressEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ipo.USAddress <em>US Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>US Address</em>'.
   * @see org.eclipse.emf.test.models.ipo.USAddress
   * @generated
   */
  public EClass getUSAddress()
  {
    return usAddressEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.USAddress#getState <em>State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>State</em>'.
   * @see org.eclipse.emf.test.models.ipo.USAddress#getState()
   * @see #getUSAddress()
   * @generated
   */
  public EAttribute getUSAddress_State()
  {
    return (EAttribute)usAddressEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ipo.USAddress#getZip <em>Zip</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Zip</em>'.
   * @see org.eclipse.emf.test.models.ipo.USAddress#getZip()
   * @see #getUSAddress()
   * @generated
   */
  public EAttribute getUSAddress_Zip()
  {
    return (EAttribute)usAddressEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.test.models.ipo.USState <em>US State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>US State</em>'.
   * @see org.eclipse.emf.test.models.ipo.USState
   * @generated
   */
  public EEnum getUSState()
  {
    return usStateEEnum;
  }

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>Postcode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Postcode</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='Postcode' baseType='http://www.eclipse.org/emf/2003/XMLType#string' length='7'"
   * @generated
   */
  public EDataType getPostcode()
  {
    return postcodeEDataType;
  }

  /**
   * Returns the meta object for data type '{@link java.math.BigInteger <em>Quantity Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Quantity Type</em>'.
   * @see java.math.BigInteger
   * @model instanceClass="java.math.BigInteger"
   *        extendedMetaData="name='quantity_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#positiveInteger' maxExclusive='100'"
   * @generated
   */
  public EDataType getQuantityType()
  {
    return quantityTypeEDataType;
  }

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>SKU</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>SKU</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='SKU' baseType='http://www.eclipse.org/emf/2003/XMLType#string' pattern='\\d{3}-[A-Z]{2}'"
   * @generated
   */
  public EDataType getSKU()
  {
    return skuEDataType;
  }

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>UK Postcode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>UK Postcode</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='UKPostcode' baseType='Postcode' pattern='[A-Z]{2}\\d\\s\\d[A-Z]{2}'"
   * @generated
   */
  public EDataType getUKPostcode()
  {
    return ukPostcodeEDataType;
  }

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.test.models.ipo.USState <em>US State Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>US State Object</em>'.
   * @see org.eclipse.emf.test.models.ipo.USState
   * @model instanceClass="org.eclipse.emf.test.models.ipo.USState"
   *        extendedMetaData="name='USState:Object' baseType='USState'"
   * @generated
   */
  public EDataType getUSStateObject()
  {
    return usStateObjectEDataType;
  }

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  public IPOFactory getIPOFactory()
  {
    return (IPOFactory)getEFactoryInstance();
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
    addEEnumLiteral(usStateEEnum, USState.AK);
    addEEnumLiteral(usStateEEnum, USState.AL);
    addEEnumLiteral(usStateEEnum, USState.AR);
    addEEnumLiteral(usStateEEnum, USState.PA);

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
  protected void createExtendedMetaDataAnnotations()
  {
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
  public interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ipo.impl.AddressImpl <em>Address</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ipo.impl.AddressImpl
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getAddress()
     * @generated
     */
    public static final EClass ADDRESS = eINSTANCE.getAddress();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ADDRESS__NAME = eINSTANCE.getAddress_Name();

    /**
     * The meta object literal for the '<em><b>Street</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ADDRESS__STREET = eINSTANCE.getAddress_Street();

    /**
     * The meta object literal for the '<em><b>City</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ADDRESS__CITY = eINSTANCE.getAddress_City();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ipo.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ipo.impl.DocumentRootImpl
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getDocumentRoot()
     * @generated
     */
    public static final EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

    /**
     * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

    /**
     * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

    /**
     * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute DOCUMENT_ROOT__COMMENT = eINSTANCE.getDocumentRoot_Comment();

    /**
     * The meta object literal for the '<em><b>Purchase Order</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference DOCUMENT_ROOT__PURCHASE_ORDER = eINSTANCE.getDocumentRoot_PurchaseOrder();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ipo.impl.ItemsImpl <em>Items</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ipo.impl.ItemsImpl
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getItems()
     * @generated
     */
    public static final EClass ITEMS = eINSTANCE.getItems();

    /**
     * The meta object literal for the '<em><b>Item</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference ITEMS__ITEM = eINSTANCE.getItems_Item();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ipo.impl.ItemTypeImpl <em>Item Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ipo.impl.ItemTypeImpl
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getItemType()
     * @generated
     */
    public static final EClass ITEM_TYPE = eINSTANCE.getItemType();

    /**
     * The meta object literal for the '<em><b>Product Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ITEM_TYPE__PRODUCT_NAME = eINSTANCE.getItemType_ProductName();

    /**
     * The meta object literal for the '<em><b>Quantity</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ITEM_TYPE__QUANTITY = eINSTANCE.getItemType_Quantity();

    /**
     * The meta object literal for the '<em><b>US Price</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ITEM_TYPE__US_PRICE = eINSTANCE.getItemType_USPrice();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ITEM_TYPE__COMMENT = eINSTANCE.getItemType_Comment();

    /**
     * The meta object literal for the '<em><b>Ship Date</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ITEM_TYPE__SHIP_DATE = eINSTANCE.getItemType_ShipDate();

    /**
     * The meta object literal for the '<em><b>Part Num</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ITEM_TYPE__PART_NUM = eINSTANCE.getItemType_PartNum();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ipo.impl.PurchaseOrderTypeImpl <em>Purchase Order Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ipo.impl.PurchaseOrderTypeImpl
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getPurchaseOrderType()
     * @generated
     */
    public static final EClass PURCHASE_ORDER_TYPE = eINSTANCE.getPurchaseOrderType();

    /**
     * The meta object literal for the '<em><b>Ship To</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference PURCHASE_ORDER_TYPE__SHIP_TO = eINSTANCE.getPurchaseOrderType_ShipTo();

    /**
     * The meta object literal for the '<em><b>Bill To</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference PURCHASE_ORDER_TYPE__BILL_TO = eINSTANCE.getPurchaseOrderType_BillTo();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PURCHASE_ORDER_TYPE__COMMENT = eINSTANCE.getPurchaseOrderType_Comment();

    /**
     * The meta object literal for the '<em><b>Items</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference PURCHASE_ORDER_TYPE__ITEMS = eINSTANCE.getPurchaseOrderType_Items();

    /**
     * The meta object literal for the '<em><b>Order Date</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PURCHASE_ORDER_TYPE__ORDER_DATE = eINSTANCE.getPurchaseOrderType_OrderDate();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ipo.impl.UKAddressImpl <em>UK Address</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ipo.impl.UKAddressImpl
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getUKAddress()
     * @generated
     */
    public static final EClass UK_ADDRESS = eINSTANCE.getUKAddress();

    /**
     * The meta object literal for the '<em><b>Postcode</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute UK_ADDRESS__POSTCODE = eINSTANCE.getUKAddress_Postcode();

    /**
     * The meta object literal for the '<em><b>Export Code</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute UK_ADDRESS__EXPORT_CODE = eINSTANCE.getUKAddress_ExportCode();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ipo.impl.USAddressImpl <em>US Address</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ipo.impl.USAddressImpl
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getUSAddress()
     * @generated
     */
    public static final EClass US_ADDRESS = eINSTANCE.getUSAddress();

    /**
     * The meta object literal for the '<em><b>State</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute US_ADDRESS__STATE = eINSTANCE.getUSAddress_State();

    /**
     * The meta object literal for the '<em><b>Zip</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute US_ADDRESS__ZIP = eINSTANCE.getUSAddress_Zip();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ipo.USState <em>US State</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ipo.USState
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getUSState()
     * @generated
     */
    public static final EEnum US_STATE = eINSTANCE.getUSState();

    /**
     * The meta object literal for the '<em>Postcode</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getPostcode()
     * @generated
     */
    public static final EDataType POSTCODE = eINSTANCE.getPostcode();

    /**
     * The meta object literal for the '<em>Quantity Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.math.BigInteger
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getQuantityType()
     * @generated
     */
    public static final EDataType QUANTITY_TYPE = eINSTANCE.getQuantityType();

    /**
     * The meta object literal for the '<em>SKU</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getSKU()
     * @generated
     */
    public static final EDataType SKU = eINSTANCE.getSKU();

    /**
     * The meta object literal for the '<em>UK Postcode</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getUKPostcode()
     * @generated
     */
    public static final EDataType UK_POSTCODE = eINSTANCE.getUKPostcode();

    /**
     * The meta object literal for the '<em>US State Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ipo.USState
     * @see org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl#getUSStateObject()
     * @generated
     */
    public static final EDataType US_STATE_OBJECT = eINSTANCE.getUSStateObject();

  }

} //IPOPackageImpl
