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
 * $Id: USAddressImpl.java,v 1.2 2007/01/18 22:06:39 marcelop Exp $
 */
package org.eclipse.emf.test.models.ipo.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.test.models.ipo.USAddress;
import org.eclipse.emf.test.models.ipo.USState;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>US Address</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.USAddressImpl#getState <em>State</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.USAddressImpl#getZip <em>Zip</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class USAddressImpl extends AddressImpl implements USAddress
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The default value of the '{@link #getState() <em>State</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getState()
   * @generated
   * @ordered
   */
  protected static final USState STATE_EDEFAULT = USState.AK;

  /**
   * The cached value of the '{@link #getState() <em>State</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getState()
   * @generated
   * @ordered
   */
  protected USState state = STATE_EDEFAULT;

  /**
   * This is true if the State attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean stateESet = false;

  /**
   * The default value of the '{@link #getZip() <em>Zip</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getZip()
   * @generated
   * @ordered
   */
  protected static final BigInteger ZIP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getZip() <em>Zip</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getZip()
   * @generated
   * @ordered
   */
  protected BigInteger zip = ZIP_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected USAddressImpl()
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
    return IPOPackageImpl.Literals.US_ADDRESS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public USState getState()
  {
    return state;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setState(USState newState)
  {
    USState oldState = state;
    state = newState == null ? STATE_EDEFAULT : newState;
    boolean oldStateESet = stateESet;
    stateESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.US_ADDRESS__STATE, oldState, state, !oldStateESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetState()
  {
    USState oldState = state;
    boolean oldStateESet = stateESet;
    state = STATE_EDEFAULT;
    stateESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, IPOPackageImpl.US_ADDRESS__STATE, oldState, STATE_EDEFAULT, oldStateESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetState()
  {
    return stateESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getZip()
  {
    return zip;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setZip(BigInteger newZip)
  {
    BigInteger oldZip = zip;
    zip = newZip;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.US_ADDRESS__ZIP, oldZip, zip));
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
      case IPOPackageImpl.US_ADDRESS__STATE:
        return getState();
      case IPOPackageImpl.US_ADDRESS__ZIP:
        return getZip();
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
      case IPOPackageImpl.US_ADDRESS__STATE:
        setState((USState)newValue);
        return;
      case IPOPackageImpl.US_ADDRESS__ZIP:
        setZip((BigInteger)newValue);
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
      case IPOPackageImpl.US_ADDRESS__STATE:
        unsetState();
        return;
      case IPOPackageImpl.US_ADDRESS__ZIP:
        setZip(ZIP_EDEFAULT);
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
      case IPOPackageImpl.US_ADDRESS__STATE:
        return isSetState();
      case IPOPackageImpl.US_ADDRESS__ZIP:
        return ZIP_EDEFAULT == null ? zip != null : !ZIP_EDEFAULT.equals(zip);
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
    result.append(" (state: ");
    if (stateESet) result.append(state); else result.append("<unset>");
    result.append(", zip: ");
    result.append(zip);
    result.append(')');
    return result.toString();
  }

} //USAddressImpl
