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
 * $Id: IpoPackage.java,v 1.5 2007/06/12 15:07:36 emerks Exp $
 */
package com.example.ipo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see com.example.ipo.IpoFactory
 * @model kind="package"
 * @generated
 */
public interface IpoPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "ipo";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.example.com/IPO";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "ipo";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  IpoPackage eINSTANCE = com.example.ipo.impl.IpoPackageImpl.init();

  /**
   * The meta object id for the '{@link com.example.ipo.impl.AddressImpl <em>Address</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.example.ipo.impl.AddressImpl
   * @see com.example.ipo.impl.IpoPackageImpl#getAddress()
   * @generated
   */
  int ADDRESS = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDRESS__NAME = 0;

  /**
   * The feature id for the '<em><b>Street</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDRESS__STREET = 1;

  /**
   * The feature id for the '<em><b>City</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDRESS__CITY = 2;

  /**
   * The number of structural features of the '<em>Address</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDRESS_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link com.example.ipo.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.example.ipo.impl.DocumentRootImpl
   * @see com.example.ipo.impl.IpoPackageImpl#getDocumentRoot()
   * @generated
   */
  int DOCUMENT_ROOT = 1;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__MIXED = 0;

  /**
   * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

  /**
   * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__COMMENT = 3;

  /**
   * The feature id for the '<em><b>Purchase Order</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__PURCHASE_ORDER = 4;

  /**
   * The number of structural features of the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link com.example.ipo.impl.ItemsImpl <em>Items</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.example.ipo.impl.ItemsImpl
   * @see com.example.ipo.impl.IpoPackageImpl#getItems()
   * @generated
   */
  int ITEMS = 2;

  /**
   * The feature id for the '<em><b>Item</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEMS__ITEM = 0;

  /**
   * The number of structural features of the '<em>Items</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEMS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.example.ipo.impl.ItemTypeImpl <em>Item Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.example.ipo.impl.ItemTypeImpl
   * @see com.example.ipo.impl.IpoPackageImpl#getItemType()
   * @generated
   */
  int ITEM_TYPE = 3;

  /**
   * The feature id for the '<em><b>Product Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM_TYPE__PRODUCT_NAME = 0;

  /**
   * The feature id for the '<em><b>Quantity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM_TYPE__QUANTITY = 1;

  /**
   * The feature id for the '<em><b>US Price</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM_TYPE__US_PRICE = 2;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM_TYPE__COMMENT = 3;

  /**
   * The feature id for the '<em><b>Ship Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM_TYPE__SHIP_DATE = 4;

  /**
   * The feature id for the '<em><b>Part Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM_TYPE__PART_NUM = 5;

  /**
   * The number of structural features of the '<em>Item Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link com.example.ipo.impl.PurchaseOrderTypeImpl <em>Purchase Order Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.example.ipo.impl.PurchaseOrderTypeImpl
   * @see com.example.ipo.impl.IpoPackageImpl#getPurchaseOrderType()
   * @generated
   */
  int PURCHASE_ORDER_TYPE = 4;

  /**
   * The feature id for the '<em><b>Ship To</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PURCHASE_ORDER_TYPE__SHIP_TO = 0;

  /**
   * The feature id for the '<em><b>Bill To</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PURCHASE_ORDER_TYPE__BILL_TO = 1;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PURCHASE_ORDER_TYPE__COMMENT = 2;

  /**
   * The feature id for the '<em><b>Items</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PURCHASE_ORDER_TYPE__ITEMS = 3;

  /**
   * The feature id for the '<em><b>Order Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PURCHASE_ORDER_TYPE__ORDER_DATE = 4;

  /**
   * The number of structural features of the '<em>Purchase Order Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PURCHASE_ORDER_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link com.example.ipo.impl.UKAddressImpl <em>UK Address</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.example.ipo.impl.UKAddressImpl
   * @see com.example.ipo.impl.IpoPackageImpl#getUKAddress()
   * @generated
   */
  int UK_ADDRESS = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UK_ADDRESS__NAME = ADDRESS__NAME;

  /**
   * The feature id for the '<em><b>Street</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UK_ADDRESS__STREET = ADDRESS__STREET;

  /**
   * The feature id for the '<em><b>City</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UK_ADDRESS__CITY = ADDRESS__CITY;

  /**
   * The feature id for the '<em><b>Postcode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UK_ADDRESS__POSTCODE = ADDRESS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Export Code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UK_ADDRESS__EXPORT_CODE = ADDRESS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>UK Address</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UK_ADDRESS_FEATURE_COUNT = ADDRESS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.example.ipo.impl.USAddressImpl <em>US Address</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.example.ipo.impl.USAddressImpl
   * @see com.example.ipo.impl.IpoPackageImpl#getUSAddress()
   * @generated
   */
  int US_ADDRESS = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int US_ADDRESS__NAME = ADDRESS__NAME;

  /**
   * The feature id for the '<em><b>Street</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int US_ADDRESS__STREET = ADDRESS__STREET;

  /**
   * The feature id for the '<em><b>City</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int US_ADDRESS__CITY = ADDRESS__CITY;

  /**
   * The feature id for the '<em><b>State</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int US_ADDRESS__STATE = ADDRESS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Zip</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int US_ADDRESS__ZIP = ADDRESS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>US Address</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int US_ADDRESS_FEATURE_COUNT = ADDRESS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.example.ipo.USState <em>US State</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.example.ipo.USState
   * @see com.example.ipo.impl.IpoPackageImpl#getUSState()
   * @generated
   */
  int US_STATE = 7;

  /**
   * The meta object id for the '<em>Postcode</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see com.example.ipo.impl.IpoPackageImpl#getPostcode()
   * @generated
   */
  int POSTCODE = 8;

  /**
   * The meta object id for the '<em>Quantity Type</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.math.BigInteger
   * @see com.example.ipo.impl.IpoPackageImpl#getQuantityType()
   * @generated
   */
  int QUANTITY_TYPE = 9;

  /**
   * The meta object id for the '<em>SKU</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see com.example.ipo.impl.IpoPackageImpl#getSKU()
   * @generated
   */
  int SKU = 10;

  /**
   * The meta object id for the '<em>UK Postcode</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see com.example.ipo.impl.IpoPackageImpl#getUKPostcode()
   * @generated
   */
  int UK_POSTCODE = 11;

  /**
   * The meta object id for the '<em>US State Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.example.ipo.USState
   * @see com.example.ipo.impl.IpoPackageImpl#getUSStateObject()
   * @generated
   */
  int US_STATE_OBJECT = 12;


  /**
   * Returns the meta object for class '{@link com.example.ipo.Address <em>Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Address</em>'.
   * @see com.example.ipo.Address
   * @generated
   */
  EClass getAddress();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.Address#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.example.ipo.Address#getName()
   * @see #getAddress()
   * @generated
   */
  EAttribute getAddress_Name();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.Address#getStreet <em>Street</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Street</em>'.
   * @see com.example.ipo.Address#getStreet()
   * @see #getAddress()
   * @generated
   */
  EAttribute getAddress_Street();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.Address#getCity <em>City</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>City</em>'.
   * @see com.example.ipo.Address#getCity()
   * @see #getAddress()
   * @generated
   */
  EAttribute getAddress_City();

  /**
   * Returns the meta object for class '{@link com.example.ipo.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see com.example.ipo.DocumentRoot
   * @generated
   */
  EClass getDocumentRoot();

  /**
   * Returns the meta object for the attribute list '{@link com.example.ipo.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see com.example.ipo.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Mixed();

  /**
   * Returns the meta object for the map '{@link com.example.ipo.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see com.example.ipo.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XMLNSPrefixMap();

  /**
   * Returns the meta object for the map '{@link com.example.ipo.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see com.example.ipo.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XSISchemaLocation();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.DocumentRoot#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see com.example.ipo.DocumentRoot#getComment()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Comment();

  /**
   * Returns the meta object for the containment reference '{@link com.example.ipo.DocumentRoot#getPurchaseOrder <em>Purchase Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Purchase Order</em>'.
   * @see com.example.ipo.DocumentRoot#getPurchaseOrder()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_PurchaseOrder();

  /**
   * Returns the meta object for class '{@link com.example.ipo.Items <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Items</em>'.
   * @see com.example.ipo.Items
   * @generated
   */
  EClass getItems();

  /**
   * Returns the meta object for the containment reference list '{@link com.example.ipo.Items#getItem <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Item</em>'.
   * @see com.example.ipo.Items#getItem()
   * @see #getItems()
   * @generated
   */
  EReference getItems_Item();

  /**
   * Returns the meta object for class '{@link com.example.ipo.ItemType <em>Item Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Item Type</em>'.
   * @see com.example.ipo.ItemType
   * @generated
   */
  EClass getItemType();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.ItemType#getProductName <em>Product Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Product Name</em>'.
   * @see com.example.ipo.ItemType#getProductName()
   * @see #getItemType()
   * @generated
   */
  EAttribute getItemType_ProductName();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.ItemType#getQuantity <em>Quantity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Quantity</em>'.
   * @see com.example.ipo.ItemType#getQuantity()
   * @see #getItemType()
   * @generated
   */
  EAttribute getItemType_Quantity();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.ItemType#getUSPrice <em>US Price</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>US Price</em>'.
   * @see com.example.ipo.ItemType#getUSPrice()
   * @see #getItemType()
   * @generated
   */
  EAttribute getItemType_USPrice();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.ItemType#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see com.example.ipo.ItemType#getComment()
   * @see #getItemType()
   * @generated
   */
  EAttribute getItemType_Comment();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.ItemType#getShipDate <em>Ship Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ship Date</em>'.
   * @see com.example.ipo.ItemType#getShipDate()
   * @see #getItemType()
   * @generated
   */
  EAttribute getItemType_ShipDate();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.ItemType#getPartNum <em>Part Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Part Num</em>'.
   * @see com.example.ipo.ItemType#getPartNum()
   * @see #getItemType()
   * @generated
   */
  EAttribute getItemType_PartNum();

  /**
   * Returns the meta object for class '{@link com.example.ipo.PurchaseOrderType <em>Purchase Order Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Purchase Order Type</em>'.
   * @see com.example.ipo.PurchaseOrderType
   * @generated
   */
  EClass getPurchaseOrderType();

  /**
   * Returns the meta object for the containment reference '{@link com.example.ipo.PurchaseOrderType#getShipTo <em>Ship To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ship To</em>'.
   * @see com.example.ipo.PurchaseOrderType#getShipTo()
   * @see #getPurchaseOrderType()
   * @generated
   */
  EReference getPurchaseOrderType_ShipTo();

  /**
   * Returns the meta object for the containment reference '{@link com.example.ipo.PurchaseOrderType#getBillTo <em>Bill To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bill To</em>'.
   * @see com.example.ipo.PurchaseOrderType#getBillTo()
   * @see #getPurchaseOrderType()
   * @generated
   */
  EReference getPurchaseOrderType_BillTo();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.PurchaseOrderType#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see com.example.ipo.PurchaseOrderType#getComment()
   * @see #getPurchaseOrderType()
   * @generated
   */
  EAttribute getPurchaseOrderType_Comment();

  /**
   * Returns the meta object for the containment reference '{@link com.example.ipo.PurchaseOrderType#getItems <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Items</em>'.
   * @see com.example.ipo.PurchaseOrderType#getItems()
   * @see #getPurchaseOrderType()
   * @generated
   */
  EReference getPurchaseOrderType_Items();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.PurchaseOrderType#getOrderDate <em>Order Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Order Date</em>'.
   * @see com.example.ipo.PurchaseOrderType#getOrderDate()
   * @see #getPurchaseOrderType()
   * @generated
   */
  EAttribute getPurchaseOrderType_OrderDate();

  /**
   * Returns the meta object for class '{@link com.example.ipo.UKAddress <em>UK Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>UK Address</em>'.
   * @see com.example.ipo.UKAddress
   * @generated
   */
  EClass getUKAddress();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.UKAddress#getPostcode <em>Postcode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Postcode</em>'.
   * @see com.example.ipo.UKAddress#getPostcode()
   * @see #getUKAddress()
   * @generated
   */
  EAttribute getUKAddress_Postcode();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.UKAddress#getExportCode <em>Export Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Export Code</em>'.
   * @see com.example.ipo.UKAddress#getExportCode()
   * @see #getUKAddress()
   * @generated
   */
  EAttribute getUKAddress_ExportCode();

  /**
   * Returns the meta object for class '{@link com.example.ipo.USAddress <em>US Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>US Address</em>'.
   * @see com.example.ipo.USAddress
   * @generated
   */
  EClass getUSAddress();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.USAddress#getState <em>State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>State</em>'.
   * @see com.example.ipo.USAddress#getState()
   * @see #getUSAddress()
   * @generated
   */
  EAttribute getUSAddress_State();

  /**
   * Returns the meta object for the attribute '{@link com.example.ipo.USAddress#getZip <em>Zip</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Zip</em>'.
   * @see com.example.ipo.USAddress#getZip()
   * @see #getUSAddress()
   * @generated
   */
  EAttribute getUSAddress_Zip();

  /**
   * Returns the meta object for enum '{@link com.example.ipo.USState <em>US State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>US State</em>'.
   * @see com.example.ipo.USState
   * @generated
   */
  EEnum getUSState();

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
  EDataType getPostcode();

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
  EDataType getQuantityType();

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
  EDataType getSKU();

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
  EDataType getUKPostcode();

  /**
   * Returns the meta object for data type '{@link com.example.ipo.USState <em>US State Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>US State Object</em>'.
   * @see com.example.ipo.USState
   * @model instanceClass="com.example.ipo.USState"
   *        extendedMetaData="name='USState:Object' baseType='USState'"
   * @generated
   */
  EDataType getUSStateObject();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  IpoFactory getIpoFactory();

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
     * The meta object literal for the '{@link com.example.ipo.impl.AddressImpl <em>Address</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.ipo.impl.AddressImpl
     * @see com.example.ipo.impl.IpoPackageImpl#getAddress()
     * @generated
     */
    EClass ADDRESS = eINSTANCE.getAddress();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADDRESS__NAME = eINSTANCE.getAddress_Name();

    /**
     * The meta object literal for the '<em><b>Street</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADDRESS__STREET = eINSTANCE.getAddress_Street();

    /**
     * The meta object literal for the '<em><b>City</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADDRESS__CITY = eINSTANCE.getAddress_City();

    /**
     * The meta object literal for the '{@link com.example.ipo.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.ipo.impl.DocumentRootImpl
     * @see com.example.ipo.impl.IpoPackageImpl#getDocumentRoot()
     * @generated
     */
    EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

    /**
     * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

    /**
     * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

    /**
     * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__COMMENT = eINSTANCE.getDocumentRoot_Comment();

    /**
     * The meta object literal for the '<em><b>Purchase Order</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__PURCHASE_ORDER = eINSTANCE.getDocumentRoot_PurchaseOrder();

    /**
     * The meta object literal for the '{@link com.example.ipo.impl.ItemsImpl <em>Items</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.ipo.impl.ItemsImpl
     * @see com.example.ipo.impl.IpoPackageImpl#getItems()
     * @generated
     */
    EClass ITEMS = eINSTANCE.getItems();

    /**
     * The meta object literal for the '<em><b>Item</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ITEMS__ITEM = eINSTANCE.getItems_Item();

    /**
     * The meta object literal for the '{@link com.example.ipo.impl.ItemTypeImpl <em>Item Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.ipo.impl.ItemTypeImpl
     * @see com.example.ipo.impl.IpoPackageImpl#getItemType()
     * @generated
     */
    EClass ITEM_TYPE = eINSTANCE.getItemType();

    /**
     * The meta object literal for the '<em><b>Product Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM_TYPE__PRODUCT_NAME = eINSTANCE.getItemType_ProductName();

    /**
     * The meta object literal for the '<em><b>Quantity</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM_TYPE__QUANTITY = eINSTANCE.getItemType_Quantity();

    /**
     * The meta object literal for the '<em><b>US Price</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM_TYPE__US_PRICE = eINSTANCE.getItemType_USPrice();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM_TYPE__COMMENT = eINSTANCE.getItemType_Comment();

    /**
     * The meta object literal for the '<em><b>Ship Date</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM_TYPE__SHIP_DATE = eINSTANCE.getItemType_ShipDate();

    /**
     * The meta object literal for the '<em><b>Part Num</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM_TYPE__PART_NUM = eINSTANCE.getItemType_PartNum();

    /**
     * The meta object literal for the '{@link com.example.ipo.impl.PurchaseOrderTypeImpl <em>Purchase Order Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.ipo.impl.PurchaseOrderTypeImpl
     * @see com.example.ipo.impl.IpoPackageImpl#getPurchaseOrderType()
     * @generated
     */
    EClass PURCHASE_ORDER_TYPE = eINSTANCE.getPurchaseOrderType();

    /**
     * The meta object literal for the '<em><b>Ship To</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PURCHASE_ORDER_TYPE__SHIP_TO = eINSTANCE.getPurchaseOrderType_ShipTo();

    /**
     * The meta object literal for the '<em><b>Bill To</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PURCHASE_ORDER_TYPE__BILL_TO = eINSTANCE.getPurchaseOrderType_BillTo();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PURCHASE_ORDER_TYPE__COMMENT = eINSTANCE.getPurchaseOrderType_Comment();

    /**
     * The meta object literal for the '<em><b>Items</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PURCHASE_ORDER_TYPE__ITEMS = eINSTANCE.getPurchaseOrderType_Items();

    /**
     * The meta object literal for the '<em><b>Order Date</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PURCHASE_ORDER_TYPE__ORDER_DATE = eINSTANCE.getPurchaseOrderType_OrderDate();

    /**
     * The meta object literal for the '{@link com.example.ipo.impl.UKAddressImpl <em>UK Address</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.ipo.impl.UKAddressImpl
     * @see com.example.ipo.impl.IpoPackageImpl#getUKAddress()
     * @generated
     */
    EClass UK_ADDRESS = eINSTANCE.getUKAddress();

    /**
     * The meta object literal for the '<em><b>Postcode</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UK_ADDRESS__POSTCODE = eINSTANCE.getUKAddress_Postcode();

    /**
     * The meta object literal for the '<em><b>Export Code</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UK_ADDRESS__EXPORT_CODE = eINSTANCE.getUKAddress_ExportCode();

    /**
     * The meta object literal for the '{@link com.example.ipo.impl.USAddressImpl <em>US Address</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.ipo.impl.USAddressImpl
     * @see com.example.ipo.impl.IpoPackageImpl#getUSAddress()
     * @generated
     */
    EClass US_ADDRESS = eINSTANCE.getUSAddress();

    /**
     * The meta object literal for the '<em><b>State</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute US_ADDRESS__STATE = eINSTANCE.getUSAddress_State();

    /**
     * The meta object literal for the '<em><b>Zip</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute US_ADDRESS__ZIP = eINSTANCE.getUSAddress_Zip();

    /**
     * The meta object literal for the '{@link com.example.ipo.USState <em>US State</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.ipo.USState
     * @see com.example.ipo.impl.IpoPackageImpl#getUSState()
     * @generated
     */
    EEnum US_STATE = eINSTANCE.getUSState();

    /**
     * The meta object literal for the '<em>Postcode</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see com.example.ipo.impl.IpoPackageImpl#getPostcode()
     * @generated
     */
    EDataType POSTCODE = eINSTANCE.getPostcode();

    /**
     * The meta object literal for the '<em>Quantity Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.math.BigInteger
     * @see com.example.ipo.impl.IpoPackageImpl#getQuantityType()
     * @generated
     */
    EDataType QUANTITY_TYPE = eINSTANCE.getQuantityType();

    /**
     * The meta object literal for the '<em>SKU</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see com.example.ipo.impl.IpoPackageImpl#getSKU()
     * @generated
     */
    EDataType SKU = eINSTANCE.getSKU();

    /**
     * The meta object literal for the '<em>UK Postcode</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see com.example.ipo.impl.IpoPackageImpl#getUKPostcode()
     * @generated
     */
    EDataType UK_POSTCODE = eINSTANCE.getUKPostcode();

    /**
     * The meta object literal for the '<em>US State Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.ipo.USState
     * @see com.example.ipo.impl.IpoPackageImpl#getUSStateObject()
     * @generated
     */
    EDataType US_STATE_OBJECT = eINSTANCE.getUSStateObject();

  }

} //IpoPackage
