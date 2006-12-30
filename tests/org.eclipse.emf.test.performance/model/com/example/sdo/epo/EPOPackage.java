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
 * $Id: EPOPackage.java,v 1.2 2006/12/30 03:43:52 marcelop Exp $
 */
package com.example.sdo.epo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see com.example.sdo.epo.EPOFactory
 * @model kind="package"
 * @generated
 */
public interface EPOPackage extends EPackage {
	/**
   * The package name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNAME = "epo";

	/**
   * The package namespace URI.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_URI = "http:///com/example/epo.ecore";

	/**
   * The package namespace name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	String eNS_PREFIX = "com.example.epo";

	/**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	EPOPackage eINSTANCE = com.example.sdo.epo.impl.EPOPackageImpl.init();

	/**
   * The meta object id for the '{@link com.example.sdo.epo.impl.ItemImpl <em>Item</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see com.example.sdo.epo.impl.ItemImpl
   * @see com.example.sdo.epo.impl.EPOPackageImpl#getItem()
   * @generated
   */
	int ITEM = 0;

	/**
   * The feature id for the '<em><b>Product Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM__PRODUCT_NAME = 0;

	/**
   * The feature id for the '<em><b>Quantity</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM__QUANTITY = 1;

	/**
   * The feature id for the '<em><b>US Price</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM__US_PRICE = 2;

	/**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM__COMMENT = 3;

	/**
   * The feature id for the '<em><b>Ship Date</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM__SHIP_DATE = 4;

	/**
   * The feature id for the '<em><b>Part Num</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM__PART_NUM = 5;

	/**
   * The number of structural features of the '<em>Item</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int ITEM_FEATURE_COUNT = 6;

	/**
   * The meta object id for the '{@link com.example.sdo.epo.impl.USAddressImpl <em>US Address</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see com.example.sdo.epo.impl.USAddressImpl
   * @see com.example.sdo.epo.impl.EPOPackageImpl#getUSAddress()
   * @generated
   */
	int US_ADDRESS = 1;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int US_ADDRESS__NAME = 0;

	/**
   * The feature id for the '<em><b>Street</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int US_ADDRESS__STREET = 1;

	/**
   * The feature id for the '<em><b>City</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int US_ADDRESS__CITY = 2;

	/**
   * The feature id for the '<em><b>State</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int US_ADDRESS__STATE = 3;

	/**
   * The feature id for the '<em><b>Zip</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int US_ADDRESS__ZIP = 4;

	/**
   * The feature id for the '<em><b>Country</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int US_ADDRESS__COUNTRY = 5;

	/**
   * The number of structural features of the '<em>US Address</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int US_ADDRESS_FEATURE_COUNT = 6;

	/**
   * The meta object id for the '{@link com.example.sdo.epo.impl.PurchaseOrderImpl <em>Purchase Order</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see com.example.sdo.epo.impl.PurchaseOrderImpl
   * @see com.example.sdo.epo.impl.EPOPackageImpl#getPurchaseOrder()
   * @generated
   */
	int PURCHASE_ORDER = 2;

	/**
   * The feature id for the '<em><b>Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PURCHASE_ORDER__ITEMS = 0;

	/**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PURCHASE_ORDER__COMMENT = 1;

	/**
   * The feature id for the '<em><b>Order Date</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PURCHASE_ORDER__ORDER_DATE = 2;

	/**
   * The feature id for the '<em><b>Bill To</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PURCHASE_ORDER__BILL_TO = 3;

	/**
   * The feature id for the '<em><b>Ship To</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PURCHASE_ORDER__SHIP_TO = 4;

	/**
   * The number of structural features of the '<em>Purchase Order</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int PURCHASE_ORDER_FEATURE_COUNT = 5;

	/**
   * The meta object id for the '{@link com.example.sdo.epo.impl.SupplierImpl <em>Supplier</em>}' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see com.example.sdo.epo.impl.SupplierImpl
   * @see com.example.sdo.epo.impl.EPOPackageImpl#getSupplier()
   * @generated
   */
	int SUPPLIER = 3;

	/**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SUPPLIER__NAME = 0;

	/**
   * The feature id for the '<em><b>Orders</b></em>' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SUPPLIER__ORDERS = 1;

	/**
   * The feature id for the '<em><b>Priority Orders</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SUPPLIER__PRIORITY_ORDERS = 2;

	/**
   * The feature id for the '<em><b>Standard Orders</b></em>' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SUPPLIER__STANDARD_ORDERS = 3;

	/**
   * The number of structural features of the '<em>Supplier</em>' class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
	int SUPPLIER_FEATURE_COUNT = 4;

	/**
   * The meta object id for the '<em>SKU</em>' data type.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see java.lang.String
   * @see com.example.sdo.epo.impl.EPOPackageImpl#getSKU()
   * @generated
   */
	int SKU = 4;

	/**
   * The meta object id for the '<em>Date</em>' data type.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see java.util.Date
   * @see com.example.sdo.epo.impl.EPOPackageImpl#getDate()
   * @generated
   */
	int DATE = 5;


	/**
   * Returns the meta object for class '{@link com.example.sdo.epo.Item <em>Item</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Item</em>'.
   * @see com.example.sdo.epo.Item
   * @generated
   */
	EClass getItem();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.Item#getProductName <em>Product Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Product Name</em>'.
   * @see com.example.sdo.epo.Item#getProductName()
   * @see #getItem()
   * @generated
   */
	EAttribute getItem_ProductName();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.Item#getQuantity <em>Quantity</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Quantity</em>'.
   * @see com.example.sdo.epo.Item#getQuantity()
   * @see #getItem()
   * @generated
   */
	EAttribute getItem_Quantity();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.Item#getUSPrice <em>US Price</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>US Price</em>'.
   * @see com.example.sdo.epo.Item#getUSPrice()
   * @see #getItem()
   * @generated
   */
	EAttribute getItem_USPrice();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.Item#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see com.example.sdo.epo.Item#getComment()
   * @see #getItem()
   * @generated
   */
	EAttribute getItem_Comment();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.Item#getShipDate <em>Ship Date</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ship Date</em>'.
   * @see com.example.sdo.epo.Item#getShipDate()
   * @see #getItem()
   * @generated
   */
	EAttribute getItem_ShipDate();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.Item#getPartNum <em>Part Num</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Part Num</em>'.
   * @see com.example.sdo.epo.Item#getPartNum()
   * @see #getItem()
   * @generated
   */
	EAttribute getItem_PartNum();

	/**
   * Returns the meta object for class '{@link com.example.sdo.epo.USAddress <em>US Address</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>US Address</em>'.
   * @see com.example.sdo.epo.USAddress
   * @generated
   */
	EClass getUSAddress();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.USAddress#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.example.sdo.epo.USAddress#getName()
   * @see #getUSAddress()
   * @generated
   */
	EAttribute getUSAddress_Name();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.USAddress#getStreet <em>Street</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Street</em>'.
   * @see com.example.sdo.epo.USAddress#getStreet()
   * @see #getUSAddress()
   * @generated
   */
	EAttribute getUSAddress_Street();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.USAddress#getCity <em>City</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>City</em>'.
   * @see com.example.sdo.epo.USAddress#getCity()
   * @see #getUSAddress()
   * @generated
   */
	EAttribute getUSAddress_City();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.USAddress#getState <em>State</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>State</em>'.
   * @see com.example.sdo.epo.USAddress#getState()
   * @see #getUSAddress()
   * @generated
   */
	EAttribute getUSAddress_State();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.USAddress#getZip <em>Zip</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Zip</em>'.
   * @see com.example.sdo.epo.USAddress#getZip()
   * @see #getUSAddress()
   * @generated
   */
	EAttribute getUSAddress_Zip();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.USAddress#getCountry <em>Country</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Country</em>'.
   * @see com.example.sdo.epo.USAddress#getCountry()
   * @see #getUSAddress()
   * @generated
   */
	EAttribute getUSAddress_Country();

	/**
   * Returns the meta object for class '{@link com.example.sdo.epo.PurchaseOrder <em>Purchase Order</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Purchase Order</em>'.
   * @see com.example.sdo.epo.PurchaseOrder
   * @generated
   */
	EClass getPurchaseOrder();

	/**
   * Returns the meta object for the containment reference list '{@link com.example.sdo.epo.PurchaseOrder#getItems <em>Items</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Items</em>'.
   * @see com.example.sdo.epo.PurchaseOrder#getItems()
   * @see #getPurchaseOrder()
   * @generated
   */
	EReference getPurchaseOrder_Items();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.PurchaseOrder#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see com.example.sdo.epo.PurchaseOrder#getComment()
   * @see #getPurchaseOrder()
   * @generated
   */
	EAttribute getPurchaseOrder_Comment();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.PurchaseOrder#getOrderDate <em>Order Date</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Order Date</em>'.
   * @see com.example.sdo.epo.PurchaseOrder#getOrderDate()
   * @see #getPurchaseOrder()
   * @generated
   */
	EAttribute getPurchaseOrder_OrderDate();

	/**
   * Returns the meta object for the containment reference '{@link com.example.sdo.epo.PurchaseOrder#getBillTo <em>Bill To</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bill To</em>'.
   * @see com.example.sdo.epo.PurchaseOrder#getBillTo()
   * @see #getPurchaseOrder()
   * @generated
   */
	EReference getPurchaseOrder_BillTo();

	/**
   * Returns the meta object for the containment reference '{@link com.example.sdo.epo.PurchaseOrder#getShipTo <em>Ship To</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ship To</em>'.
   * @see com.example.sdo.epo.PurchaseOrder#getShipTo()
   * @see #getPurchaseOrder()
   * @generated
   */
	EReference getPurchaseOrder_ShipTo();

	/**
   * Returns the meta object for class '{@link com.example.sdo.epo.Supplier <em>Supplier</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for class '<em>Supplier</em>'.
   * @see com.example.sdo.epo.Supplier
   * @generated
   */
	EClass getSupplier();

	/**
   * Returns the meta object for the attribute '{@link com.example.sdo.epo.Supplier#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.example.sdo.epo.Supplier#getName()
   * @see #getSupplier()
   * @generated
   */
	EAttribute getSupplier_Name();

	/**
   * Returns the meta object for the attribute list '{@link com.example.sdo.epo.Supplier#getOrders <em>Orders</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Orders</em>'.
   * @see com.example.sdo.epo.Supplier#getOrders()
   * @see #getSupplier()
   * @generated
   */
	EAttribute getSupplier_Orders();

	/**
   * Returns the meta object for the containment reference list '{@link com.example.sdo.epo.Supplier#getPriorityOrders <em>Priority Orders</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Priority Orders</em>'.
   * @see com.example.sdo.epo.Supplier#getPriorityOrders()
   * @see #getSupplier()
   * @generated
   */
	EReference getSupplier_PriorityOrders();

	/**
   * Returns the meta object for the containment reference list '{@link com.example.sdo.epo.Supplier#getStandardOrders <em>Standard Orders</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Standard Orders</em>'.
   * @see com.example.sdo.epo.Supplier#getStandardOrders()
   * @see #getSupplier()
   * @generated
   */
	EReference getSupplier_StandardOrders();

	/**
   * Returns the meta object for data type '{@link java.lang.String <em>SKU</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for data type '<em>SKU</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   * @generated
   */
	EDataType getSKU();

	/**
   * Returns the meta object for data type '{@link java.util.Date <em>Date</em>}'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Date</em>'.
   * @see java.util.Date
   * @model instanceClass="java.util.Date"
   * @generated
   */
	EDataType getDate();

	/**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
	EPOFactory getEPOFactory();

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
     * The meta object literal for the '{@link com.example.sdo.epo.impl.ItemImpl <em>Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.sdo.epo.impl.ItemImpl
     * @see com.example.sdo.epo.impl.EPOPackageImpl#getItem()
     * @generated
     */
    EClass ITEM = eINSTANCE.getItem();

    /**
     * The meta object literal for the '<em><b>Product Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__PRODUCT_NAME = eINSTANCE.getItem_ProductName();

    /**
     * The meta object literal for the '<em><b>Quantity</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__QUANTITY = eINSTANCE.getItem_Quantity();

    /**
     * The meta object literal for the '<em><b>US Price</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__US_PRICE = eINSTANCE.getItem_USPrice();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__COMMENT = eINSTANCE.getItem_Comment();

    /**
     * The meta object literal for the '<em><b>Ship Date</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__SHIP_DATE = eINSTANCE.getItem_ShipDate();

    /**
     * The meta object literal for the '<em><b>Part Num</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITEM__PART_NUM = eINSTANCE.getItem_PartNum();

    /**
     * The meta object literal for the '{@link com.example.sdo.epo.impl.USAddressImpl <em>US Address</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.sdo.epo.impl.USAddressImpl
     * @see com.example.sdo.epo.impl.EPOPackageImpl#getUSAddress()
     * @generated
     */
    EClass US_ADDRESS = eINSTANCE.getUSAddress();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute US_ADDRESS__NAME = eINSTANCE.getUSAddress_Name();

    /**
     * The meta object literal for the '<em><b>Street</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute US_ADDRESS__STREET = eINSTANCE.getUSAddress_Street();

    /**
     * The meta object literal for the '<em><b>City</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute US_ADDRESS__CITY = eINSTANCE.getUSAddress_City();

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
     * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute US_ADDRESS__COUNTRY = eINSTANCE.getUSAddress_Country();

    /**
     * The meta object literal for the '{@link com.example.sdo.epo.impl.PurchaseOrderImpl <em>Purchase Order</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.sdo.epo.impl.PurchaseOrderImpl
     * @see com.example.sdo.epo.impl.EPOPackageImpl#getPurchaseOrder()
     * @generated
     */
    EClass PURCHASE_ORDER = eINSTANCE.getPurchaseOrder();

    /**
     * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PURCHASE_ORDER__ITEMS = eINSTANCE.getPurchaseOrder_Items();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PURCHASE_ORDER__COMMENT = eINSTANCE.getPurchaseOrder_Comment();

    /**
     * The meta object literal for the '<em><b>Order Date</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PURCHASE_ORDER__ORDER_DATE = eINSTANCE.getPurchaseOrder_OrderDate();

    /**
     * The meta object literal for the '<em><b>Bill To</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PURCHASE_ORDER__BILL_TO = eINSTANCE.getPurchaseOrder_BillTo();

    /**
     * The meta object literal for the '<em><b>Ship To</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PURCHASE_ORDER__SHIP_TO = eINSTANCE.getPurchaseOrder_ShipTo();

    /**
     * The meta object literal for the '{@link com.example.sdo.epo.impl.SupplierImpl <em>Supplier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.example.sdo.epo.impl.SupplierImpl
     * @see com.example.sdo.epo.impl.EPOPackageImpl#getSupplier()
     * @generated
     */
    EClass SUPPLIER = eINSTANCE.getSupplier();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SUPPLIER__NAME = eINSTANCE.getSupplier_Name();

    /**
     * The meta object literal for the '<em><b>Orders</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SUPPLIER__ORDERS = eINSTANCE.getSupplier_Orders();

    /**
     * The meta object literal for the '<em><b>Priority Orders</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SUPPLIER__PRIORITY_ORDERS = eINSTANCE.getSupplier_PriorityOrders();

    /**
     * The meta object literal for the '<em><b>Standard Orders</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SUPPLIER__STANDARD_ORDERS = eINSTANCE.getSupplier_StandardOrders();

    /**
     * The meta object literal for the '<em>SKU</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see com.example.sdo.epo.impl.EPOPackageImpl#getSKU()
     * @generated
     */
    EDataType SKU = eINSTANCE.getSKU();

    /**
     * The meta object literal for the '<em>Date</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.Date
     * @see com.example.sdo.epo.impl.EPOPackageImpl#getDate()
     * @generated
     */
    EDataType DATE = eINSTANCE.getDate();

  }

} //EPOPackage
