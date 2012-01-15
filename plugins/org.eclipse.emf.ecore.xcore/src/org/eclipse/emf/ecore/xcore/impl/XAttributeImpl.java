/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.xcore.XAttribute;
import org.eclipse.emf.ecore.xcore.XcorePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XAttribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XAttributeImpl#getDefaultValueLiteral <em>Default Value Literal</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XAttributeImpl#isID <em>ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XAttributeImpl extends XStructuralFeatureImpl implements XAttribute
{
  /**
   * The default value of the '{@link #getDefaultValueLiteral() <em>Default Value Literal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultValueLiteral()
   * @generated
   * @ordered
   */
  protected static final String DEFAULT_VALUE_LITERAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDefaultValueLiteral() <em>Default Value Literal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultValueLiteral()
   * @generated
   * @ordered
   */
  protected String defaultValueLiteral = DEFAULT_VALUE_LITERAL_EDEFAULT;

  /**
   * The default value of the '{@link #isID() <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isID()
   * @generated
   * @ordered
   */
  protected static final boolean ID_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isID() <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isID()
   * @generated
   * @ordered
   */
  protected boolean iD = ID_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XAttributeImpl()
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
    return XcorePackage.Literals.XATTRIBUTE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDefaultValueLiteral()
  {
    return defaultValueLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDefaultValueLiteral(String newDefaultValueLiteral)
  {
    String oldDefaultValueLiteral = defaultValueLiteral;
    defaultValueLiteral = newDefaultValueLiteral;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XATTRIBUTE__DEFAULT_VALUE_LITERAL, oldDefaultValueLiteral, defaultValueLiteral));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isID()
  {
    return iD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setID(boolean newID)
  {
    boolean oldID = iD;
    iD = newID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XATTRIBUTE__ID, oldID, iD));
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
      case XcorePackage.XATTRIBUTE__DEFAULT_VALUE_LITERAL:
        return getDefaultValueLiteral();
      case XcorePackage.XATTRIBUTE__ID:
        return isID();
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
      case XcorePackage.XATTRIBUTE__DEFAULT_VALUE_LITERAL:
        setDefaultValueLiteral((String)newValue);
        return;
      case XcorePackage.XATTRIBUTE__ID:
        setID((Boolean)newValue);
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
      case XcorePackage.XATTRIBUTE__DEFAULT_VALUE_LITERAL:
        setDefaultValueLiteral(DEFAULT_VALUE_LITERAL_EDEFAULT);
        return;
      case XcorePackage.XATTRIBUTE__ID:
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
      case XcorePackage.XATTRIBUTE__DEFAULT_VALUE_LITERAL:
        return DEFAULT_VALUE_LITERAL_EDEFAULT == null ? defaultValueLiteral != null : !DEFAULT_VALUE_LITERAL_EDEFAULT.equals(defaultValueLiteral);
      case XcorePackage.XATTRIBUTE__ID:
        return iD != ID_EDEFAULT;
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
    result.append(" (defaultValueLiteral: ");
    result.append(defaultValueLiteral);
    result.append(", iD: ");
    result.append(iD);
    result.append(')');
    return result.toString();
  }

} //XAttributeImpl
