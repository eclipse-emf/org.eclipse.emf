/**
 * <copyright>
 * </copyright>
 *
 * $Id: CanadaAddrImpl.java,v 1.1 2005/02/08 20:54:12 marcelop Exp $
 */
package org.eclipse.emf.test.models.customer.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.test.models.customer.CanadaAddr;
import org.eclipse.emf.test.models.customer.CustomerPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Canada Addr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.CanadaAddrImpl#getZip <em>Zip</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.CanadaAddrImpl#getProvince <em>Province</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CanadaAddrImpl extends AddressTypeImpl implements CanadaAddr
{
  /**
   * The default value of the '{@link #getZip() <em>Zip</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getZip()
   * @generated
   * @ordered
   */
  protected static final String ZIP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getZip() <em>Zip</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getZip()
   * @generated
   * @ordered
   */
  protected String zip = ZIP_EDEFAULT;

  /**
   * The default value of the '{@link #getProvince() <em>Province</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvince()
   * @generated
   * @ordered
   */
  protected static final String PROVINCE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getProvince() <em>Province</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvince()
   * @generated
   * @ordered
   */
  protected String province = PROVINCE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CanadaAddrImpl()
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
    return CustomerPackage.eINSTANCE.getCanadaAddr();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getZip()
  {
    return zip;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setZip(String newZip)
  {
    String oldZip = zip;
    zip = newZip;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.CANADA_ADDR__ZIP, oldZip, zip));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getProvince()
  {
    return province;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProvince(String newProvince)
  {
    String oldProvince = province;
    province = newProvince;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.CANADA_ADDR__PROVINCE, oldProvince, province));
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
      case CustomerPackage.CANADA_ADDR__STREET:
        return getStreet();
      case CustomerPackage.CANADA_ADDR__TOWN:
        return getTown();
      case CustomerPackage.CANADA_ADDR__ZIP:
        return getZip();
      case CustomerPackage.CANADA_ADDR__PROVINCE:
        return getProvince();
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
      case CustomerPackage.CANADA_ADDR__STREET:
        setStreet((String)newValue);
        return;
      case CustomerPackage.CANADA_ADDR__TOWN:
        setTown((String)newValue);
        return;
      case CustomerPackage.CANADA_ADDR__ZIP:
        setZip((String)newValue);
        return;
      case CustomerPackage.CANADA_ADDR__PROVINCE:
        setProvince((String)newValue);
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
      case CustomerPackage.CANADA_ADDR__STREET:
        setStreet(STREET_EDEFAULT);
        return;
      case CustomerPackage.CANADA_ADDR__TOWN:
        setTown(TOWN_EDEFAULT);
        return;
      case CustomerPackage.CANADA_ADDR__ZIP:
        setZip(ZIP_EDEFAULT);
        return;
      case CustomerPackage.CANADA_ADDR__PROVINCE:
        setProvince(PROVINCE_EDEFAULT);
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
      case CustomerPackage.CANADA_ADDR__STREET:
        return STREET_EDEFAULT == null ? street != null : !STREET_EDEFAULT.equals(street);
      case CustomerPackage.CANADA_ADDR__TOWN:
        return TOWN_EDEFAULT == null ? town != null : !TOWN_EDEFAULT.equals(town);
      case CustomerPackage.CANADA_ADDR__ZIP:
        return ZIP_EDEFAULT == null ? zip != null : !ZIP_EDEFAULT.equals(zip);
      case CustomerPackage.CANADA_ADDR__PROVINCE:
        return PROVINCE_EDEFAULT == null ? province != null : !PROVINCE_EDEFAULT.equals(province);
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
    result.append(", province: ");
    result.append(province);
    result.append(')');
    return result.toString();
  }

} //CanadaAddrImpl
