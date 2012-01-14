/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.ppo;

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
 * @see org.eclipse.emf.test.models.ppo.PPOFactory
 * @model kind="package"
 * @generated
 */
public interface PPOPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "ppo";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org.eclipse.emf.test.models/ppo";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.emf.test.models.ppo";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PPOPackage eINSTANCE = org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ppo.impl.ItemImpl <em>Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ppo.impl.ItemImpl
   * @see org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl#getItem()
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
   * The meta object id for the '{@link org.eclipse.emf.test.models.ppo.impl.USAddressImpl <em>US Address</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ppo.impl.USAddressImpl
   * @see org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl#getUSAddress()
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
   * The meta object id for the '{@link org.eclipse.emf.test.models.ppo.impl.PurchaseOrderImpl <em>Purchase Order</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ppo.impl.PurchaseOrderImpl
   * @see org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl#getPurchaseOrder()
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
   * The meta object id for the '<em>SKU</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl#getSKU()
   * @generated
   */
  int SKU = 3;

  /**
   * The meta object id for the '<em>Date</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.Date
   * @see org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl#getDate()
   * @generated
   */
  int DATE = 4;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ppo.Item <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Item</em>'.
   * @see org.eclipse.emf.test.models.ppo.Item
   * @generated
   */
  EClass getItem();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.Item#getProductName <em>Product Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Product Name</em>'.
   * @see org.eclipse.emf.test.models.ppo.Item#getProductName()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_ProductName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.Item#getQuantity <em>Quantity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Quantity</em>'.
   * @see org.eclipse.emf.test.models.ppo.Item#getQuantity()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_Quantity();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.Item#getUSPrice <em>US Price</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>US Price</em>'.
   * @see org.eclipse.emf.test.models.ppo.Item#getUSPrice()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_USPrice();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.Item#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.eclipse.emf.test.models.ppo.Item#getComment()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.Item#getShipDate <em>Ship Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ship Date</em>'.
   * @see org.eclipse.emf.test.models.ppo.Item#getShipDate()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_ShipDate();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.Item#getPartNum <em>Part Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Part Num</em>'.
   * @see org.eclipse.emf.test.models.ppo.Item#getPartNum()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_PartNum();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ppo.USAddress <em>US Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>US Address</em>'.
   * @see org.eclipse.emf.test.models.ppo.USAddress
   * @generated
   */
  EClass getUSAddress();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.USAddress#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.ppo.USAddress#getName()
   * @see #getUSAddress()
   * @generated
   */
  EAttribute getUSAddress_Name();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.USAddress#getStreet <em>Street</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Street</em>'.
   * @see org.eclipse.emf.test.models.ppo.USAddress#getStreet()
   * @see #getUSAddress()
   * @generated
   */
  EAttribute getUSAddress_Street();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.USAddress#getCity <em>City</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>City</em>'.
   * @see org.eclipse.emf.test.models.ppo.USAddress#getCity()
   * @see #getUSAddress()
   * @generated
   */
  EAttribute getUSAddress_City();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.USAddress#getState <em>State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>State</em>'.
   * @see org.eclipse.emf.test.models.ppo.USAddress#getState()
   * @see #getUSAddress()
   * @generated
   */
  EAttribute getUSAddress_State();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.USAddress#getZip <em>Zip</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Zip</em>'.
   * @see org.eclipse.emf.test.models.ppo.USAddress#getZip()
   * @see #getUSAddress()
   * @generated
   */
  EAttribute getUSAddress_Zip();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.USAddress#getCountry <em>Country</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Country</em>'.
   * @see org.eclipse.emf.test.models.ppo.USAddress#getCountry()
   * @see #getUSAddress()
   * @generated
   */
  EAttribute getUSAddress_Country();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ppo.PurchaseOrder <em>Purchase Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Purchase Order</em>'.
   * @see org.eclipse.emf.test.models.ppo.PurchaseOrder
   * @generated
   */
  EClass getPurchaseOrder();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.ppo.PurchaseOrder#getItems <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Items</em>'.
   * @see org.eclipse.emf.test.models.ppo.PurchaseOrder#getItems()
   * @see #getPurchaseOrder()
   * @generated
   */
  EReference getPurchaseOrder_Items();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.PurchaseOrder#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.eclipse.emf.test.models.ppo.PurchaseOrder#getComment()
   * @see #getPurchaseOrder()
   * @generated
   */
  EAttribute getPurchaseOrder_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ppo.PurchaseOrder#getOrderDate <em>Order Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Order Date</em>'.
   * @see org.eclipse.emf.test.models.ppo.PurchaseOrder#getOrderDate()
   * @see #getPurchaseOrder()
   * @generated
   */
  EAttribute getPurchaseOrder_OrderDate();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ppo.PurchaseOrder#getBillTo <em>Bill To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bill To</em>'.
   * @see org.eclipse.emf.test.models.ppo.PurchaseOrder#getBillTo()
   * @see #getPurchaseOrder()
   * @generated
   */
  EReference getPurchaseOrder_BillTo();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ppo.PurchaseOrder#getShipTo <em>Ship To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ship To</em>'.
   * @see org.eclipse.emf.test.models.ppo.PurchaseOrder#getShipTo()
   * @see #getPurchaseOrder()
   * @generated
   */
  EReference getPurchaseOrder_ShipTo();

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
  PPOFactory getPPOFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ppo.impl.ItemImpl <em>Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ppo.impl.ItemImpl
     * @see org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl#getItem()
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
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ppo.impl.USAddressImpl <em>US Address</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ppo.impl.USAddressImpl
     * @see org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl#getUSAddress()
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
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ppo.impl.PurchaseOrderImpl <em>Purchase Order</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ppo.impl.PurchaseOrderImpl
     * @see org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl#getPurchaseOrder()
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
     * The meta object literal for the '<em>SKU</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl#getSKU()
     * @generated
     */
    EDataType SKU = eINSTANCE.getSKU();

    /**
     * The meta object literal for the '<em>Date</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.Date
     * @see org.eclipse.emf.test.models.ppo.impl.PPOPackageImpl#getDate()
     * @generated
     */
    EDataType DATE = eINSTANCE.getDate();

  }

} //PPOPackage
