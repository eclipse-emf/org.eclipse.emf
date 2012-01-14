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
package org.eclipse.emf.test.models.customer.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.test.models.customer.AddressType;
import org.eclipse.emf.test.models.customer.CustomerPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Address Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.AddressTypeImpl#getStreet <em>Street</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.AddressTypeImpl#getTown <em>Town</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AddressTypeImpl extends EObjectImpl implements AddressType
{
  /**
   * The default value of the '{@link #getStreet() <em>Street</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStreet()
   * @generated
   * @ordered
   */
  protected static final String STREET_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStreet() <em>Street</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStreet()
   * @generated
   * @ordered
   */
  protected String street = STREET_EDEFAULT;

  /**
   * The default value of the '{@link #getTown() <em>Town</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTown()
   * @generated
   * @ordered
   */
  protected static final String TOWN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTown() <em>Town</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTown()
   * @generated
   * @ordered
   */
  protected String town = TOWN_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AddressTypeImpl()
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
    return CustomerPackage.Literals.ADDRESS_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStreet()
  {
    return street;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStreet(String newStreet)
  {
    String oldStreet = street;
    street = newStreet;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.ADDRESS_TYPE__STREET, oldStreet, street));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTown()
  {
    return town;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTown(String newTown)
  {
    String oldTown = town;
    town = newTown;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.ADDRESS_TYPE__TOWN, oldTown, town));
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
      case CustomerPackage.ADDRESS_TYPE__STREET:
        return getStreet();
      case CustomerPackage.ADDRESS_TYPE__TOWN:
        return getTown();
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
      case CustomerPackage.ADDRESS_TYPE__STREET:
        setStreet((String)newValue);
        return;
      case CustomerPackage.ADDRESS_TYPE__TOWN:
        setTown((String)newValue);
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
      case CustomerPackage.ADDRESS_TYPE__STREET:
        setStreet(STREET_EDEFAULT);
        return;
      case CustomerPackage.ADDRESS_TYPE__TOWN:
        setTown(TOWN_EDEFAULT);
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
      case CustomerPackage.ADDRESS_TYPE__STREET:
        return STREET_EDEFAULT == null ? street != null : !STREET_EDEFAULT.equals(street);
      case CustomerPackage.ADDRESS_TYPE__TOWN:
        return TOWN_EDEFAULT == null ? town != null : !TOWN_EDEFAULT.equals(town);
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
    result.append(" (street: ");
    result.append(street);
    result.append(", town: ");
    result.append(town);
    result.append(')');
    return result.toString();
  }

} //AddressTypeImpl
