/**
 * <copyright>
 * </copyright>
 *
 * $Id: UKAddressImpl.java,v 1.1.2.1 2005/05/30 19:26:59 nickb Exp $
 */
package com.example.ipo.impl;

import com.example.ipo.IpoPackage;
import com.example.ipo.UKAddress;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UK Address</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.example.ipo.impl.UKAddressImpl#getPostcode <em>Postcode</em>}</li>
 *   <li>{@link com.example.ipo.impl.UKAddressImpl#getExportCode <em>Export Code</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UKAddressImpl extends AddressImpl implements UKAddress
{
  /**
   * The default value of the '{@link #getPostcode() <em>Postcode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPostcode()
   * @generated
   * @ordered
   */
  protected static final String POSTCODE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPostcode() <em>Postcode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPostcode()
   * @generated
   * @ordered
   */
  protected String postcode = POSTCODE_EDEFAULT;

  /**
   * The default value of the '{@link #getExportCode() <em>Export Code</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExportCode()
   * @generated
   * @ordered
   */
  protected static final BigInteger EXPORT_CODE_EDEFAULT = new BigInteger("1");

  /**
   * The cached value of the '{@link #getExportCode() <em>Export Code</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExportCode()
   * @generated
   * @ordered
   */
  protected BigInteger exportCode = EXPORT_CODE_EDEFAULT;

  /**
   * This is true if the Export Code attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean exportCodeESet = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UKAddressImpl()
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
    return IpoPackage.eINSTANCE.getUKAddress();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPostcode()
  {
    return postcode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPostcode(String newPostcode)
  {
    String oldPostcode = postcode;
    postcode = newPostcode;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.UK_ADDRESS__POSTCODE, oldPostcode, postcode));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getExportCode()
  {
    return exportCode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExportCode(BigInteger newExportCode)
  {
    BigInteger oldExportCode = exportCode;
    exportCode = newExportCode;
    boolean oldExportCodeESet = exportCodeESet;
    exportCodeESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.UK_ADDRESS__EXPORT_CODE, oldExportCode, exportCode, !oldExportCodeESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetExportCode()
  {
    BigInteger oldExportCode = exportCode;
    boolean oldExportCodeESet = exportCodeESet;
    exportCode = EXPORT_CODE_EDEFAULT;
    exportCodeESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, IpoPackage.UK_ADDRESS__EXPORT_CODE, oldExportCode, EXPORT_CODE_EDEFAULT, oldExportCodeESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetExportCode()
  {
    return exportCodeESet;
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
      case IpoPackage.UK_ADDRESS__NAME:
        return getName();
      case IpoPackage.UK_ADDRESS__STREET:
        return getStreet();
      case IpoPackage.UK_ADDRESS__CITY:
        return getCity();
      case IpoPackage.UK_ADDRESS__POSTCODE:
        return getPostcode();
      case IpoPackage.UK_ADDRESS__EXPORT_CODE:
        return getExportCode();
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
      case IpoPackage.UK_ADDRESS__NAME:
        setName((String)newValue);
        return;
      case IpoPackage.UK_ADDRESS__STREET:
        setStreet((String)newValue);
        return;
      case IpoPackage.UK_ADDRESS__CITY:
        setCity((String)newValue);
        return;
      case IpoPackage.UK_ADDRESS__POSTCODE:
        setPostcode((String)newValue);
        return;
      case IpoPackage.UK_ADDRESS__EXPORT_CODE:
        setExportCode((BigInteger)newValue);
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
      case IpoPackage.UK_ADDRESS__NAME:
        setName(NAME_EDEFAULT);
        return;
      case IpoPackage.UK_ADDRESS__STREET:
        setStreet(STREET_EDEFAULT);
        return;
      case IpoPackage.UK_ADDRESS__CITY:
        setCity(CITY_EDEFAULT);
        return;
      case IpoPackage.UK_ADDRESS__POSTCODE:
        setPostcode(POSTCODE_EDEFAULT);
        return;
      case IpoPackage.UK_ADDRESS__EXPORT_CODE:
        unsetExportCode();
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
      case IpoPackage.UK_ADDRESS__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case IpoPackage.UK_ADDRESS__STREET:
        return STREET_EDEFAULT == null ? street != null : !STREET_EDEFAULT.equals(street);
      case IpoPackage.UK_ADDRESS__CITY:
        return CITY_EDEFAULT == null ? city != null : !CITY_EDEFAULT.equals(city);
      case IpoPackage.UK_ADDRESS__POSTCODE:
        return POSTCODE_EDEFAULT == null ? postcode != null : !POSTCODE_EDEFAULT.equals(postcode);
      case IpoPackage.UK_ADDRESS__EXPORT_CODE:
        return isSetExportCode();
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
    result.append(" (postcode: ");
    result.append(postcode);
    result.append(", exportCode: ");
    if (exportCodeESet) result.append(exportCode); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //UKAddressImpl
