/**
 * <copyright>
 * </copyright>
 *
 * $Id: PurchaseOrderTypeImpl.java,v 1.1.2.1 2005/05/30 19:26:59 nickb Exp $
 */
package com.example.ipo.impl;

import com.example.ipo.Address;
import com.example.ipo.IpoPackage;
import com.example.ipo.Items;
import com.example.ipo.PurchaseOrderType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Purchase Order Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.example.ipo.impl.PurchaseOrderTypeImpl#getShipTo <em>Ship To</em>}</li>
 *   <li>{@link com.example.ipo.impl.PurchaseOrderTypeImpl#getBillTo <em>Bill To</em>}</li>
 *   <li>{@link com.example.ipo.impl.PurchaseOrderTypeImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link com.example.ipo.impl.PurchaseOrderTypeImpl#getItems <em>Items</em>}</li>
 *   <li>{@link com.example.ipo.impl.PurchaseOrderTypeImpl#getOrderDate <em>Order Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PurchaseOrderTypeImpl extends EObjectImpl implements PurchaseOrderType
{
  /**
   * The cached value of the '{@link #getShipTo() <em>Ship To</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShipTo()
   * @generated
   * @ordered
   */
  protected Address shipTo = null;

  /**
   * The cached value of the '{@link #getBillTo() <em>Bill To</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBillTo()
   * @generated
   * @ordered
   */
  protected Address billTo = null;

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
  protected Items items = null;

  /**
   * The default value of the '{@link #getOrderDate() <em>Order Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOrderDate()
   * @generated
   * @ordered
   */
  protected static final Object ORDER_DATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOrderDate() <em>Order Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOrderDate()
   * @generated
   * @ordered
   */
  protected Object orderDate = ORDER_DATE_EDEFAULT;

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
  protected EClass eStaticClass()
  {
    return IpoPackage.eINSTANCE.getPurchaseOrderType();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IpoPackage.PURCHASE_ORDER_TYPE__SHIP_TO, oldShipTo, newShipTo);
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
        msgs = ((InternalEObject)shipTo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IpoPackage.PURCHASE_ORDER_TYPE__SHIP_TO, null, msgs);
      if (newShipTo != null)
        msgs = ((InternalEObject)newShipTo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IpoPackage.PURCHASE_ORDER_TYPE__SHIP_TO, null, msgs);
      msgs = basicSetShipTo(newShipTo, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.PURCHASE_ORDER_TYPE__SHIP_TO, newShipTo, newShipTo));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IpoPackage.PURCHASE_ORDER_TYPE__BILL_TO, oldBillTo, newBillTo);
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
        msgs = ((InternalEObject)billTo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IpoPackage.PURCHASE_ORDER_TYPE__BILL_TO, null, msgs);
      if (newBillTo != null)
        msgs = ((InternalEObject)newBillTo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IpoPackage.PURCHASE_ORDER_TYPE__BILL_TO, null, msgs);
      msgs = basicSetBillTo(newBillTo, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.PURCHASE_ORDER_TYPE__BILL_TO, newBillTo, newBillTo));
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
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.PURCHASE_ORDER_TYPE__COMMENT, oldComment, comment));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IpoPackage.PURCHASE_ORDER_TYPE__ITEMS, oldItems, newItems);
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
        msgs = ((InternalEObject)items).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IpoPackage.PURCHASE_ORDER_TYPE__ITEMS, null, msgs);
      if (newItems != null)
        msgs = ((InternalEObject)newItems).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IpoPackage.PURCHASE_ORDER_TYPE__ITEMS, null, msgs);
      msgs = basicSetItems(newItems, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.PURCHASE_ORDER_TYPE__ITEMS, newItems, newItems));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getOrderDate()
  {
    return orderDate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOrderDate(Object newOrderDate)
  {
    Object oldOrderDate = orderDate;
    orderDate = newOrderDate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.PURCHASE_ORDER_TYPE__ORDER_DATE, oldOrderDate, orderDate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case IpoPackage.PURCHASE_ORDER_TYPE__SHIP_TO:
          return basicSetShipTo(null, msgs);
        case IpoPackage.PURCHASE_ORDER_TYPE__BILL_TO:
          return basicSetBillTo(null, msgs);
        case IpoPackage.PURCHASE_ORDER_TYPE__ITEMS:
          return basicSetItems(null, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case IpoPackage.PURCHASE_ORDER_TYPE__SHIP_TO:
        return getShipTo();
      case IpoPackage.PURCHASE_ORDER_TYPE__BILL_TO:
        return getBillTo();
      case IpoPackage.PURCHASE_ORDER_TYPE__COMMENT:
        return getComment();
      case IpoPackage.PURCHASE_ORDER_TYPE__ITEMS:
        return getItems();
      case IpoPackage.PURCHASE_ORDER_TYPE__ORDER_DATE:
        return getOrderDate();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case IpoPackage.PURCHASE_ORDER_TYPE__SHIP_TO:
        setShipTo((Address)newValue);
        return;
      case IpoPackage.PURCHASE_ORDER_TYPE__BILL_TO:
        setBillTo((Address)newValue);
        return;
      case IpoPackage.PURCHASE_ORDER_TYPE__COMMENT:
        setComment((String)newValue);
        return;
      case IpoPackage.PURCHASE_ORDER_TYPE__ITEMS:
        setItems((Items)newValue);
        return;
      case IpoPackage.PURCHASE_ORDER_TYPE__ORDER_DATE:
        setOrderDate((Object)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case IpoPackage.PURCHASE_ORDER_TYPE__SHIP_TO:
        setShipTo((Address)null);
        return;
      case IpoPackage.PURCHASE_ORDER_TYPE__BILL_TO:
        setBillTo((Address)null);
        return;
      case IpoPackage.PURCHASE_ORDER_TYPE__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case IpoPackage.PURCHASE_ORDER_TYPE__ITEMS:
        setItems((Items)null);
        return;
      case IpoPackage.PURCHASE_ORDER_TYPE__ORDER_DATE:
        setOrderDate(ORDER_DATE_EDEFAULT);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case IpoPackage.PURCHASE_ORDER_TYPE__SHIP_TO:
        return shipTo != null;
      case IpoPackage.PURCHASE_ORDER_TYPE__BILL_TO:
        return billTo != null;
      case IpoPackage.PURCHASE_ORDER_TYPE__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case IpoPackage.PURCHASE_ORDER_TYPE__ITEMS:
        return items != null;
      case IpoPackage.PURCHASE_ORDER_TYPE__ORDER_DATE:
        return ORDER_DATE_EDEFAULT == null ? orderDate != null : !ORDER_DATE_EDEFAULT.equals(orderDate);
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
