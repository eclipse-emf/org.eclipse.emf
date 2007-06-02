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
 * $Id: CustomerTypeImpl.java,v 1.4 2007/06/02 19:35:32 emerks Exp $
 */
package org.eclipse.emf.test.models.customer.impl;

import javax.xml.datatype.XMLGregorianCalendar;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.test.models.customer.AddressType;
import org.eclipse.emf.test.models.customer.CreditInfo;
import org.eclipse.emf.test.models.customer.CustomerPackage;
import org.eclipse.emf.test.models.customer.CustomerType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.CustomerTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.CustomerTypeImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.CustomerTypeImpl#getCreditCard <em>Credit Card</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.CustomerTypeImpl#getPaymentDay <em>Payment Day</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.CustomerTypeImpl#getID <em>ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomerTypeImpl extends EObjectImpl implements CustomerType
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getAddress() <em>Address</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddress()
   * @generated
   * @ordered
   */
  protected AddressType address;

  /**
   * The cached value of the '{@link #getCreditCard() <em>Credit Card</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCreditCard()
   * @generated
   * @ordered
   */
  protected CreditInfo creditCard;

  /**
   * The default value of the '{@link #getPaymentDay() <em>Payment Day</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPaymentDay()
   * @generated
   * @ordered
   */
  protected static final XMLGregorianCalendar PAYMENT_DAY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPaymentDay() <em>Payment Day</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPaymentDay()
   * @generated
   * @ordered
   */
  protected XMLGregorianCalendar paymentDay = PAYMENT_DAY_EDEFAULT;

  /**
   * This is true if the Payment Day attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean paymentDayESet;

  /**
   * The default value of the '{@link #getID() <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getID()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getID() <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getID()
   * @generated
   * @ordered
   */
  protected String iD = ID_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CustomerTypeImpl()
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
    return CustomerPackage.Literals.CUSTOMER_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.CUSTOMER_TYPE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AddressType getAddress()
  {
    return address;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAddress(AddressType newAddress, NotificationChain msgs)
  {
    AddressType oldAddress = address;
    address = newAddress;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CustomerPackage.CUSTOMER_TYPE__ADDRESS, oldAddress, newAddress);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAddress(AddressType newAddress)
  {
    if (newAddress != address)
    {
      NotificationChain msgs = null;
      if (address != null)
        msgs = ((InternalEObject)address).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CustomerPackage.CUSTOMER_TYPE__ADDRESS, null, msgs);
      if (newAddress != null)
        msgs = ((InternalEObject)newAddress).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CustomerPackage.CUSTOMER_TYPE__ADDRESS, null, msgs);
      msgs = basicSetAddress(newAddress, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.CUSTOMER_TYPE__ADDRESS, newAddress, newAddress));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CreditInfo getCreditCard()
  {
    return creditCard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCreditCard(CreditInfo newCreditCard, NotificationChain msgs)
  {
    CreditInfo oldCreditCard = creditCard;
    creditCard = newCreditCard;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CustomerPackage.CUSTOMER_TYPE__CREDIT_CARD, oldCreditCard, newCreditCard);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCreditCard(CreditInfo newCreditCard)
  {
    if (newCreditCard != creditCard)
    {
      NotificationChain msgs = null;
      if (creditCard != null)
        msgs = ((InternalEObject)creditCard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CustomerPackage.CUSTOMER_TYPE__CREDIT_CARD, null, msgs);
      if (newCreditCard != null)
        msgs = ((InternalEObject)newCreditCard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CustomerPackage.CUSTOMER_TYPE__CREDIT_CARD, null, msgs);
      msgs = basicSetCreditCard(newCreditCard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.CUSTOMER_TYPE__CREDIT_CARD, newCreditCard, newCreditCard));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLGregorianCalendar getPaymentDay()
  {
    return paymentDay;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPaymentDay(XMLGregorianCalendar newPaymentDay)
  {
    XMLGregorianCalendar oldPaymentDay = paymentDay;
    paymentDay = newPaymentDay;
    boolean oldPaymentDayESet = paymentDayESet;
    paymentDayESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.CUSTOMER_TYPE__PAYMENT_DAY, oldPaymentDay, paymentDay, !oldPaymentDayESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetPaymentDay()
  {
    XMLGregorianCalendar oldPaymentDay = paymentDay;
    boolean oldPaymentDayESet = paymentDayESet;
    paymentDay = PAYMENT_DAY_EDEFAULT;
    paymentDayESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, CustomerPackage.CUSTOMER_TYPE__PAYMENT_DAY, oldPaymentDay, PAYMENT_DAY_EDEFAULT, oldPaymentDayESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetPaymentDay()
  {
    return paymentDayESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getID()
  {
    return iD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setID(String newID)
  {
    String oldID = iD;
    iD = newID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.CUSTOMER_TYPE__ID, oldID, iD));
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
      case CustomerPackage.CUSTOMER_TYPE__ADDRESS:
        return basicSetAddress(null, msgs);
      case CustomerPackage.CUSTOMER_TYPE__CREDIT_CARD:
        return basicSetCreditCard(null, msgs);
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
      case CustomerPackage.CUSTOMER_TYPE__NAME:
        return getName();
      case CustomerPackage.CUSTOMER_TYPE__ADDRESS:
        return getAddress();
      case CustomerPackage.CUSTOMER_TYPE__CREDIT_CARD:
        return getCreditCard();
      case CustomerPackage.CUSTOMER_TYPE__PAYMENT_DAY:
        return getPaymentDay();
      case CustomerPackage.CUSTOMER_TYPE__ID:
        return getID();
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
      case CustomerPackage.CUSTOMER_TYPE__NAME:
        setName((String)newValue);
        return;
      case CustomerPackage.CUSTOMER_TYPE__ADDRESS:
        setAddress((AddressType)newValue);
        return;
      case CustomerPackage.CUSTOMER_TYPE__CREDIT_CARD:
        setCreditCard((CreditInfo)newValue);
        return;
      case CustomerPackage.CUSTOMER_TYPE__PAYMENT_DAY:
        setPaymentDay((XMLGregorianCalendar)newValue);
        return;
      case CustomerPackage.CUSTOMER_TYPE__ID:
        setID((String)newValue);
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
      case CustomerPackage.CUSTOMER_TYPE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CustomerPackage.CUSTOMER_TYPE__ADDRESS:
        setAddress((AddressType)null);
        return;
      case CustomerPackage.CUSTOMER_TYPE__CREDIT_CARD:
        setCreditCard((CreditInfo)null);
        return;
      case CustomerPackage.CUSTOMER_TYPE__PAYMENT_DAY:
        unsetPaymentDay();
        return;
      case CustomerPackage.CUSTOMER_TYPE__ID:
        setID(ID_EDEFAULT);
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
      case CustomerPackage.CUSTOMER_TYPE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CustomerPackage.CUSTOMER_TYPE__ADDRESS:
        return address != null;
      case CustomerPackage.CUSTOMER_TYPE__CREDIT_CARD:
        return creditCard != null;
      case CustomerPackage.CUSTOMER_TYPE__PAYMENT_DAY:
        return isSetPaymentDay();
      case CustomerPackage.CUSTOMER_TYPE__ID:
        return ID_EDEFAULT == null ? iD != null : !ID_EDEFAULT.equals(iD);
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
    result.append(" (name: ");
    result.append(name);
    result.append(", paymentDay: ");
    if (paymentDayESet) result.append(paymentDay); else result.append("<unset>");
    result.append(", iD: ");
    result.append(iD);
    result.append(')');
    return result.toString();
  }

} //CustomerTypeImpl
