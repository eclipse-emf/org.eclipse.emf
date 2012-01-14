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
package org.eclipse.emf.test.models.ppo.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ppo.Item;
import org.eclipse.emf.test.models.ppo.PPOPackage;
import org.eclipse.emf.test.models.ppo.PurchaseOrder;
import org.eclipse.emf.test.models.ppo.USAddress;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Purchase Order</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ppo.impl.PurchaseOrderImpl#getItems <em>Items</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ppo.impl.PurchaseOrderImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ppo.impl.PurchaseOrderImpl#getOrderDate <em>Order Date</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ppo.impl.PurchaseOrderImpl#getBillTo <em>Bill To</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ppo.impl.PurchaseOrderImpl#getShipTo <em>Ship To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PurchaseOrderImpl extends EObjectImpl implements PurchaseOrder
{
  /**
   * The cached value of the '{@link #getItems() <em>Items</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItems()
   * @generated
   * @ordered
   */
  protected EList<Item> items;

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
   * The default value of the '{@link #getOrderDate() <em>Order Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOrderDate()
   * @generated
   * @ordered
   */
  protected static final Date ORDER_DATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOrderDate() <em>Order Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOrderDate()
   * @generated
   * @ordered
   */
  protected Date orderDate = ORDER_DATE_EDEFAULT;

  /**
   * The cached value of the '{@link #getBillTo() <em>Bill To</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBillTo()
   * @generated
   * @ordered
   */
  protected USAddress billTo;

  /**
   * The cached value of the '{@link #getShipTo() <em>Ship To</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShipTo()
   * @generated
   * @ordered
   */
  protected USAddress shipTo;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PurchaseOrderImpl()
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
    return PPOPackage.Literals.PURCHASE_ORDER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Item> getItems()
  {
    if (items == null)
    {
      items = new EObjectContainmentEList<Item>(Item.class, this, PPOPackage.PURCHASE_ORDER__ITEMS);
    }
    return items;
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
      eNotify(new ENotificationImpl(this, Notification.SET, PPOPackage.PURCHASE_ORDER__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Date getOrderDate()
  {
    return orderDate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOrderDate(Date newOrderDate)
  {
    Date oldOrderDate = orderDate;
    orderDate = newOrderDate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PPOPackage.PURCHASE_ORDER__ORDER_DATE, oldOrderDate, orderDate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public USAddress getBillTo()
  {
    return billTo;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBillTo(USAddress newBillTo, NotificationChain msgs)
  {
    USAddress oldBillTo = billTo;
    billTo = newBillTo;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PPOPackage.PURCHASE_ORDER__BILL_TO, oldBillTo, newBillTo);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBillTo(USAddress newBillTo)
  {
    if (newBillTo != billTo)
    {
      NotificationChain msgs = null;
      if (billTo != null)
        msgs = ((InternalEObject)billTo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PPOPackage.PURCHASE_ORDER__BILL_TO, null, msgs);
      if (newBillTo != null)
        msgs = ((InternalEObject)newBillTo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PPOPackage.PURCHASE_ORDER__BILL_TO, null, msgs);
      msgs = basicSetBillTo(newBillTo, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PPOPackage.PURCHASE_ORDER__BILL_TO, newBillTo, newBillTo));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public USAddress getShipTo()
  {
    return shipTo;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetShipTo(USAddress newShipTo, NotificationChain msgs)
  {
    USAddress oldShipTo = shipTo;
    shipTo = newShipTo;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PPOPackage.PURCHASE_ORDER__SHIP_TO, oldShipTo, newShipTo);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setShipTo(USAddress newShipTo)
  {
    if (newShipTo != shipTo)
    {
      NotificationChain msgs = null;
      if (shipTo != null)
        msgs = ((InternalEObject)shipTo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PPOPackage.PURCHASE_ORDER__SHIP_TO, null, msgs);
      if (newShipTo != null)
        msgs = ((InternalEObject)newShipTo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PPOPackage.PURCHASE_ORDER__SHIP_TO, null, msgs);
      msgs = basicSetShipTo(newShipTo, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PPOPackage.PURCHASE_ORDER__SHIP_TO, newShipTo, newShipTo));
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
      case PPOPackage.PURCHASE_ORDER__ITEMS:
        return ((InternalEList<?>)getItems()).basicRemove(otherEnd, msgs);
      case PPOPackage.PURCHASE_ORDER__BILL_TO:
        return basicSetBillTo(null, msgs);
      case PPOPackage.PURCHASE_ORDER__SHIP_TO:
        return basicSetShipTo(null, msgs);
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
      case PPOPackage.PURCHASE_ORDER__ITEMS:
        return getItems();
      case PPOPackage.PURCHASE_ORDER__COMMENT:
        return getComment();
      case PPOPackage.PURCHASE_ORDER__ORDER_DATE:
        return getOrderDate();
      case PPOPackage.PURCHASE_ORDER__BILL_TO:
        return getBillTo();
      case PPOPackage.PURCHASE_ORDER__SHIP_TO:
        return getShipTo();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case PPOPackage.PURCHASE_ORDER__ITEMS:
        getItems().clear();
        getItems().addAll((Collection<? extends Item>)newValue);
        return;
      case PPOPackage.PURCHASE_ORDER__COMMENT:
        setComment((String)newValue);
        return;
      case PPOPackage.PURCHASE_ORDER__ORDER_DATE:
        setOrderDate((Date)newValue);
        return;
      case PPOPackage.PURCHASE_ORDER__BILL_TO:
        setBillTo((USAddress)newValue);
        return;
      case PPOPackage.PURCHASE_ORDER__SHIP_TO:
        setShipTo((USAddress)newValue);
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
      case PPOPackage.PURCHASE_ORDER__ITEMS:
        getItems().clear();
        return;
      case PPOPackage.PURCHASE_ORDER__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case PPOPackage.PURCHASE_ORDER__ORDER_DATE:
        setOrderDate(ORDER_DATE_EDEFAULT);
        return;
      case PPOPackage.PURCHASE_ORDER__BILL_TO:
        setBillTo((USAddress)null);
        return;
      case PPOPackage.PURCHASE_ORDER__SHIP_TO:
        setShipTo((USAddress)null);
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
      case PPOPackage.PURCHASE_ORDER__ITEMS:
        return items != null && !items.isEmpty();
      case PPOPackage.PURCHASE_ORDER__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case PPOPackage.PURCHASE_ORDER__ORDER_DATE:
        return ORDER_DATE_EDEFAULT == null ? orderDate != null : !ORDER_DATE_EDEFAULT.equals(orderDate);
      case PPOPackage.PURCHASE_ORDER__BILL_TO:
        return billTo != null;
      case PPOPackage.PURCHASE_ORDER__SHIP_TO:
        return shipTo != null;
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

} //PurchaseOrderImpl
