/**
 * <copyright>
 * </copyright>
 *
 * $Id: USAddrImpl.java,v 1.1 2004/06/16 18:01:17 elena Exp $
 */
package org.eclipse.emf.test.models.customer.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.test.models.customer.CustomerPackage;
import org.eclipse.emf.test.models.customer.USAddr;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>US Addr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.USAddrImpl#getZip <em>Zip</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.USAddrImpl#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class USAddrImpl extends AddressTypeImpl implements USAddr
{
  /**
   * The default value of the '{@link #getZip() <em>Zip</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getZip()
   * @generated
   * @ordered
   */
  protected static final Object ZIP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getZip() <em>Zip</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getZip()
   * @generated
   * @ordered
   */
  protected Object zip = ZIP_EDEFAULT;

  /**
   * The default value of the '{@link #getState() <em>State</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getState()
   * @generated
   * @ordered
   */
  protected static final String STATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getState() <em>State</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getState()
   * @generated
   * @ordered
   */
  protected String state = STATE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected USAddrImpl()
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
    return CustomerPackage.eINSTANCE.getUSAddr();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getZip()
  {
    return zip;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setZip(Object newZip)
  {
    Object oldZip = zip;
    zip = newZip;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.US_ADDR__ZIP, oldZip, zip));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getState()
  {
    return state;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setState(String newState)
  {
    String oldState = state;
    state = newState;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.US_ADDR__STATE, oldState, state));
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
      case CustomerPackage.US_ADDR__STREET:
        return getStreet();
      case CustomerPackage.US_ADDR__TOWN:
        return getTown();
      case CustomerPackage.US_ADDR__ZIP:
        return getZip();
      case CustomerPackage.US_ADDR__STATE:
        return getState();
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
      case CustomerPackage.US_ADDR__STREET:
        setStreet((String)newValue);
        return;
      case CustomerPackage.US_ADDR__TOWN:
        setTown((String)newValue);
        return;
      case CustomerPackage.US_ADDR__ZIP:
        setZip((Object)newValue);
        return;
      case CustomerPackage.US_ADDR__STATE:
        setState((String)newValue);
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
      case CustomerPackage.US_ADDR__STREET:
        setStreet(STREET_EDEFAULT);
        return;
      case CustomerPackage.US_ADDR__TOWN:
        setTown(TOWN_EDEFAULT);
        return;
      case CustomerPackage.US_ADDR__ZIP:
        setZip(ZIP_EDEFAULT);
        return;
      case CustomerPackage.US_ADDR__STATE:
        setState(STATE_EDEFAULT);
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
      case CustomerPackage.US_ADDR__STREET:
        return STREET_EDEFAULT == null ? street != null : !STREET_EDEFAULT.equals(street);
      case CustomerPackage.US_ADDR__TOWN:
        return TOWN_EDEFAULT == null ? town != null : !TOWN_EDEFAULT.equals(town);
      case CustomerPackage.US_ADDR__ZIP:
        return ZIP_EDEFAULT == null ? zip != null : !ZIP_EDEFAULT.equals(zip);
      case CustomerPackage.US_ADDR__STATE:
        return STATE_EDEFAULT == null ? state != null : !STATE_EDEFAULT.equals(state);
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
    result.append(" (zip: ");
    result.append(zip);
    result.append(", state: ");
    result.append(state);
    result.append(')');
    return result.toString();
  }

} //USAddrImpl
