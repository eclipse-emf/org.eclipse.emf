/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: UKAddressImpl.java,v 1.2 2006/12/30 03:43:52 marcelop Exp $
 */
package com.example.ipo.impl;

import com.example.ipo.IpoPackage;
import com.example.ipo.UKAddress;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
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
  @Override
  protected EClass eStaticClass()
  {
    return IpoPackage.Literals.UK_ADDRESS;
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
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case IpoPackage.UK_ADDRESS__POSTCODE:
        return getPostcode();
      case IpoPackage.UK_ADDRESS__EXPORT_CODE:
        return getExportCode();
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
      case IpoPackage.UK_ADDRESS__POSTCODE:
        setPostcode((String)newValue);
        return;
      case IpoPackage.UK_ADDRESS__EXPORT_CODE:
        setExportCode((BigInteger)newValue);
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
      case IpoPackage.UK_ADDRESS__POSTCODE:
        setPostcode(POSTCODE_EDEFAULT);
        return;
      case IpoPackage.UK_ADDRESS__EXPORT_CODE:
        unsetExportCode();
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
      case IpoPackage.UK_ADDRESS__POSTCODE:
        return POSTCODE_EDEFAULT == null ? postcode != null : !POSTCODE_EDEFAULT.equals(postcode);
      case IpoPackage.UK_ADDRESS__EXPORT_CODE:
        return isSetExportCode();
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
    result.append(" (postcode: ");
    result.append(postcode);
    result.append(", exportCode: ");
    if (exportCodeESet) result.append(exportCode); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //UKAddressImpl
