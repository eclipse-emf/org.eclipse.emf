/**
 * <copyright>
 * </copyright>
 *
 * $Id: USAddressImpl.java,v 1.1 2005/02/04 21:16:37 elena Exp $
 */
package com.example.ipo.impl;

import com.example.ipo.IpoPackage;
import com.example.ipo.USAddress;
import com.example.ipo.USState;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>US Address</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.example.ipo.impl.USAddressImpl#getState <em>State</em>}</li>
 *   <li>{@link com.example.ipo.impl.USAddressImpl#getZip <em>Zip</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class USAddressImpl extends AddressImpl implements USAddress
{
  /**
   * The default value of the '{@link #getState() <em>State</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getState()
   * @generated
   * @ordered
   */
  protected static final USState STATE_EDEFAULT = USState.AK_LITERAL;

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
  protected EClass eStaticClass()
  {
    return IpoPackage.eINSTANCE.getUSAddress();
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
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.US_ADDRESS__STATE, oldState, state, !oldStateESet));
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
      eNotify(new ENotificationImpl(this, Notification.UNSET, IpoPackage.US_ADDRESS__STATE, oldState, STATE_EDEFAULT, oldStateESet));
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
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.US_ADDRESS__ZIP, oldZip, zip));
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
      case IpoPackage.US_ADDRESS__NAME:
        return getName();
      case IpoPackage.US_ADDRESS__STREET:
        return getStreet();
      case IpoPackage.US_ADDRESS__CITY:
        return getCity();
      case IpoPackage.US_ADDRESS__STATE:
        return getState();
      case IpoPackage.US_ADDRESS__ZIP:
        return getZip();
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
      case IpoPackage.US_ADDRESS__NAME:
        setName((String)newValue);
        return;
      case IpoPackage.US_ADDRESS__STREET:
        setStreet((String)newValue);
        return;
      case IpoPackage.US_ADDRESS__CITY:
        setCity((String)newValue);
        return;
      case IpoPackage.US_ADDRESS__STATE:
        setState((USState)newValue);
        return;
      case IpoPackage.US_ADDRESS__ZIP:
        setZip((BigInteger)newValue);
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
      case IpoPackage.US_ADDRESS__NAME:
        setName(NAME_EDEFAULT);
        return;
      case IpoPackage.US_ADDRESS__STREET:
        setStreet(STREET_EDEFAULT);
        return;
      case IpoPackage.US_ADDRESS__CITY:
        setCity(CITY_EDEFAULT);
        return;
      case IpoPackage.US_ADDRESS__STATE:
        unsetState();
        return;
      case IpoPackage.US_ADDRESS__ZIP:
        setZip(ZIP_EDEFAULT);
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
      case IpoPackage.US_ADDRESS__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case IpoPackage.US_ADDRESS__STREET:
        return STREET_EDEFAULT == null ? street != null : !STREET_EDEFAULT.equals(street);
      case IpoPackage.US_ADDRESS__CITY:
        return CITY_EDEFAULT == null ? city != null : !CITY_EDEFAULT.equals(city);
      case IpoPackage.US_ADDRESS__STATE:
        return isSetState();
      case IpoPackage.US_ADDRESS__ZIP:
        return ZIP_EDEFAULT == null ? zip != null : !ZIP_EDEFAULT.equals(zip);
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
    result.append(" (state: ");
    if (stateESet) result.append(state); else result.append("<unset>");
    result.append(", zip: ");
    result.append(zip);
    result.append(')');
    return result.toString();
  }

} //USAddressImpl
