/**
 * <copyright>
 * </copyright>
 *
 * $Id: PurchaseOrderType.java,v 1.1.2.1 2005/05/30 19:29:31 nickb Exp $
 */
package com.example.sdo.ipo;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Purchase Order Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.example.sdo.ipo.PurchaseOrderType#getShipTo <em>Ship To</em>}</li>
 *   <li>{@link com.example.sdo.ipo.PurchaseOrderType#getBillTo <em>Bill To</em>}</li>
 *   <li>{@link com.example.sdo.ipo.PurchaseOrderType#getComment <em>Comment</em>}</li>
 *   <li>{@link com.example.sdo.ipo.PurchaseOrderType#getItems <em>Items</em>}</li>
 *   <li>{@link com.example.sdo.ipo.PurchaseOrderType#getOrderDate <em>Order Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.example.sdo.ipo.IpoPackage#getPurchaseOrderType()
 * @model extendedMetaData="name='PurchaseOrderType' kind='elementOnly'"
 * @generated
 */
public interface PurchaseOrderType
{
  /**
   * Returns the value of the '<em><b>Ship To</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ship To</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ship To</em>' containment reference.
   * @see #setShipTo(Address)
   * @see com.example.sdo.ipo.IpoPackage#getPurchaseOrderType_ShipTo()
   * @model containment="true" resolveProxies="false" required="true"
   *        extendedMetaData="kind='element' name='shipTo'"
   * @generated
   */
  Address getShipTo();

  /**
   * Sets the value of the '{@link com.example.sdo.ipo.PurchaseOrderType#getShipTo <em>Ship To</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ship To</em>' containment reference.
   * @see #getShipTo()
   * @generated
   */
  void setShipTo(Address value);

  /**
   * Returns the value of the '<em><b>Bill To</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bill To</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bill To</em>' containment reference.
   * @see #setBillTo(Address)
   * @see com.example.sdo.ipo.IpoPackage#getPurchaseOrderType_BillTo()
   * @model containment="true" resolveProxies="false" required="true"
   *        extendedMetaData="kind='element' name='billTo'"
   * @generated
   */
  Address getBillTo();

  /**
   * Sets the value of the '{@link com.example.sdo.ipo.PurchaseOrderType#getBillTo <em>Bill To</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bill To</em>' containment reference.
   * @see #getBillTo()
   * @generated
   */
  void setBillTo(Address value);

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
   * @see com.example.sdo.ipo.IpoPackage#getPurchaseOrderType_Comment()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
   *        extendedMetaData="kind='element' name='comment' namespace='##targetNamespace'"
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link com.example.sdo.ipo.PurchaseOrderType#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Items</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Items</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Items</em>' containment reference.
   * @see #setItems(Items)
   * @see com.example.sdo.ipo.IpoPackage#getPurchaseOrderType_Items()
   * @model containment="true" resolveProxies="false" required="true"
   *        extendedMetaData="kind='element' name='items'"
   * @generated
   */
  Items getItems();

  /**
   * Sets the value of the '{@link com.example.sdo.ipo.PurchaseOrderType#getItems <em>Items</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Items</em>' containment reference.
   * @see #getItems()
   * @generated
   */
  void setItems(Items value);

  /**
   * Returns the value of the '<em><b>Order Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Order Date</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Order Date</em>' attribute.
   * @see #setOrderDate(Object)
   * @see com.example.sdo.ipo.IpoPackage#getPurchaseOrderType_OrderDate()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Date"
   *        extendedMetaData="kind='attribute' name='orderDate'"
   * @generated
   */
  Object getOrderDate();

  /**
   * Sets the value of the '{@link com.example.sdo.ipo.PurchaseOrderType#getOrderDate <em>Order Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Order Date</em>' attribute.
   * @see #getOrderDate()
   * @generated
   */
  void setOrderDate(Object value);

} // PurchaseOrderType
