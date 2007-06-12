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
 * $Id: PurchaseOrder.java,v 1.3 2007/06/12 15:07:36 emerks Exp $
 */
package com.example.sdo.epo;

import java.util.Date;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Purchase Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.example.sdo.epo.PurchaseOrder#getItems <em>Items</em>}</li>
 *   <li>{@link com.example.sdo.epo.PurchaseOrder#getComment <em>Comment</em>}</li>
 *   <li>{@link com.example.sdo.epo.PurchaseOrder#getOrderDate <em>Order Date</em>}</li>
 *   <li>{@link com.example.sdo.epo.PurchaseOrder#getBillTo <em>Bill To</em>}</li>
 *   <li>{@link com.example.sdo.epo.PurchaseOrder#getShipTo <em>Ship To</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.example.sdo.epo.EPOPackage#getPurchaseOrder()
 * @model
 * @generated
 */
public interface PurchaseOrder {
	/**
   * Returns the value of the '<em><b>Items</b></em>' containment reference list.
   * The list contents are of type {@link com.example.sdo.epo.Item}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Items</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Items</em>' containment reference list.
   * @see com.example.sdo.epo.EPOPackage#getPurchaseOrder_Items()
   * @model containment="true"
   * @generated
   */
	List<Item> getItems();

	/**
   * Returns the value of the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Comment</em>' attribute.
   * @see #setComment(String)
   * @see com.example.sdo.epo.EPOPackage#getPurchaseOrder_Comment()
   * @model
   * @generated
   */
	String getComment();

	/**
   * Sets the value of the '{@link com.example.sdo.epo.PurchaseOrder#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
	void setComment(String value);

	/**
   * Returns the value of the '<em><b>Order Date</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Order Date</em>' attribute.
   * @see #setOrderDate(Date)
   * @see com.example.sdo.epo.EPOPackage#getPurchaseOrder_OrderDate()
   * @model dataType="com.example.sdo.epo.Date"
   * @generated
   */
	Date getOrderDate();

	/**
   * Sets the value of the '{@link com.example.sdo.epo.PurchaseOrder#getOrderDate <em>Order Date</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Order Date</em>' attribute.
   * @see #getOrderDate()
   * @generated
   */
	void setOrderDate(Date value);

	/**
   * Returns the value of the '<em><b>Bill To</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bill To</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Bill To</em>' containment reference.
   * @see #setBillTo(USAddress)
   * @see com.example.sdo.epo.EPOPackage#getPurchaseOrder_BillTo()
   * @model containment="true" required="true"
   * @generated
   */
	USAddress getBillTo();

	/**
   * Sets the value of the '{@link com.example.sdo.epo.PurchaseOrder#getBillTo <em>Bill To</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bill To</em>' containment reference.
   * @see #getBillTo()
   * @generated
   */
	void setBillTo(USAddress value);

	/**
   * Returns the value of the '<em><b>Ship To</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ship To</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Ship To</em>' containment reference.
   * @see #setShipTo(USAddress)
   * @see com.example.sdo.epo.EPOPackage#getPurchaseOrder_ShipTo()
   * @model containment="true" required="true"
   * @generated
   */
	USAddress getShipTo();

	/**
   * Sets the value of the '{@link com.example.sdo.epo.PurchaseOrder#getShipTo <em>Ship To</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ship To</em>' containment reference.
   * @see #getShipTo()
   * @generated
   */
	void setShipTo(USAddress value);

} // PurchaseOrder
