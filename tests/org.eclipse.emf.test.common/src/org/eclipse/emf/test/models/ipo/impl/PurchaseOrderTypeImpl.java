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
 * $Id: PurchaseOrderTypeImpl.java,v 1.4 2007/06/02 19:35:32 emerks Exp $
 */
package org.eclipse.emf.test.models.ipo.impl;

import javax.xml.datatype.XMLGregorianCalendar;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.test.models.ipo.Address;
import org.eclipse.emf.test.models.ipo.Items;
import org.eclipse.emf.test.models.ipo.PurchaseOrderType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Purchase Order Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.PurchaseOrderTypeImpl#getShipTo <em>Ship To</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.PurchaseOrderTypeImpl#getBillTo <em>Bill To</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.PurchaseOrderTypeImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.PurchaseOrderTypeImpl#getItems <em>Items</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.PurchaseOrderTypeImpl#getOrderDate <em>Order Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PurchaseOrderTypeImpl extends EDataObjectImpl implements PurchaseOrderType
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The cached value of the '{@link #getShipTo() <em>Ship To</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShipTo()
   * @generated
   * @ordered
   */
  protected Address shipTo;

  /**
   * The cached value of the '{@link #getBillTo() <em>Bill To</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBillTo()
   * @generated
   * @ordered
   */
  protected Address billTo;

  /**
   * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected static final String COMMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected String comment = COMMENT_EDEFAULT;

  /**
   * The cached value of the '{@link #getItems() <em>Items</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItems()
   * @generated
   * @ordered
   */
  protected Items items;

  /**
   * The default value of the '{@link #getOrderDate() <em>Order Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOrderDate()
   * @generated
   * @ordered
   */
  protected static final XMLGregorianCalendar ORDER_DATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOrderDate() <em>Order Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOrderDate()
   * @generated
   * @ordered
   */
  protected XMLGregorianCalendar orderDate = ORDER_DATE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PurchaseOrderTypeImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return IPOPackageImpl.Literals.PURCHASE_ORDER_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Address getShipTo()
  {
    return shipTo;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetShipTo(Address newShipTo, NotificationChain msgs)
  {
    Address oldShipTo = shipTo;
    shipTo = newShipTo;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IPOPackageImpl.PURCHASE_ORDER_TYPE__SHIP_TO, oldShipTo, newShipTo);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setShipTo(Address newShipTo)
  {
    if (newShipTo != shipTo)
    {
      NotificationChain msgs = null;
      if (shipTo != null)
        msgs = ((InternalEObject)shipTo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IPOPackageImpl.PURCHASE_ORDER_TYPE__SHIP_TO, null, msgs);
      if (newShipTo != null)
        msgs = ((InternalEObject)newShipTo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IPOPackageImpl.PURCHASE_ORDER_TYPE__SHIP_TO, null, msgs);
      msgs = basicSetShipTo(newShipTo, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.PURCHASE_ORDER_TYPE__SHIP_TO, newShipTo, newShipTo));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Address getBillTo()
  {
    return billTo;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBillTo(Address newBillTo, NotificationChain msgs)
  {
    Address oldBillTo = billTo;
    billTo = newBillTo;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IPOPackageImpl.PURCHASE_ORDER_TYPE__BILL_TO, oldBillTo, newBillTo);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBillTo(Address newBillTo)
  {
    if (newBillTo != billTo)
    {
      NotificationChain msgs = null;
      if (billTo != null)
        msgs = ((InternalEObject)billTo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IPOPackageImpl.PURCHASE_ORDER_TYPE__BILL_TO, null, msgs);
      if (newBillTo != null)
        msgs = ((InternalEObject)newBillTo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IPOPackageImpl.PURCHASE_ORDER_TYPE__BILL_TO, null, msgs);
      msgs = basicSetBillTo(newBillTo, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.PURCHASE_ORDER_TYPE__BILL_TO, newBillTo, newBillTo));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    String oldComment = comment;
    comment = newComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.PURCHASE_ORDER_TYPE__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Items getItems()
  {
    return items;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetItems(Items newItems, NotificationChain msgs)
  {
    Items oldItems = items;
    items = newItems;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IPOPackageImpl.PURCHASE_ORDER_TYPE__ITEMS, oldItems, newItems);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setItems(Items newItems)
  {
    if (newItems != items)
    {
      NotificationChain msgs = null;
      if (items != null)
        msgs = ((InternalEObject)items).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IPOPackageImpl.PURCHASE_ORDER_TYPE__ITEMS, null, msgs);
      if (newItems != null)
        msgs = ((InternalEObject)newItems).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IPOPackageImpl.PURCHASE_ORDER_TYPE__ITEMS, null, msgs);
      msgs = basicSetItems(newItems, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.PURCHASE_ORDER_TYPE__ITEMS, newItems, newItems));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLGregorianCalendar getOrderDate()
  {
    return orderDate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOrderDate(XMLGregorianCalendar newOrderDate)
  {
    XMLGregorianCalendar oldOrderDate = orderDate;
    orderDate = newOrderDate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.PURCHASE_ORDER_TYPE__ORDER_DATE, oldOrderDate, orderDate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__SHIP_TO:
        return basicSetShipTo(null, msgs);
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__BILL_TO:
        return basicSetBillTo(null, msgs);
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__ITEMS:
        return basicSetItems(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__SHIP_TO:
        return getShipTo();
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__BILL_TO:
        return getBillTo();
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__COMMENT:
        return getComment();
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__ITEMS:
        return getItems();
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__ORDER_DATE:
        return getOrderDate();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__SHIP_TO:
        setShipTo((Address)newValue);
        return;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__BILL_TO:
        setBillTo((Address)newValue);
        return;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__COMMENT:
        setComment((String)newValue);
        return;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__ITEMS:
        setItems((Items)newValue);
        return;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__ORDER_DATE:
        setOrderDate((XMLGregorianCalendar)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__SHIP_TO:
        setShipTo((Address)null);
        return;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__BILL_TO:
        setBillTo((Address)null);
        return;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__ITEMS:
        setItems((Items)null);
        return;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__ORDER_DATE:
        setOrderDate(ORDER_DATE_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__SHIP_TO:
        return shipTo != null;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__BILL_TO:
        return billTo != null;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__ITEMS:
        return items != null;
      case IPOPackageImpl.PURCHASE_ORDER_TYPE__ORDER_DATE:
        return ORDER_DATE_EDEFAULT == null ? orderDate != null : !ORDER_DATE_EDEFAULT.equals(orderDate);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (comment: ");
    result.append(comment);
    result.append(", orderDate: ");
    result.append(orderDate);
    result.append(')');
    return result.toString();
  }

} //PurchaseOrderTypeImpl
