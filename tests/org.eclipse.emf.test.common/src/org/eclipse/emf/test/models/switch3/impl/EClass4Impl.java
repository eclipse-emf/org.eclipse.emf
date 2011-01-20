/**
 * <copyright>
 *
 * Copyright (c) 2009 TIBCO Software Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Adrian Price
 *
 * </copyright>
 *
 * $Id: EClass4Impl.java,v 1.1 2011/01/20 01:10:22 emerks Exp $
 */
package org.eclipse.emf.test.models.switch3.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.test.models.switch2.impl.EClass3Impl;

import org.eclipse.emf.test.models.switch3.EClass4;
import org.eclipse.emf.test.models.switch3.Switch3Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EClass4</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.switch3.impl.EClass4Impl#getEAttribute8 <em>EAttribute8</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.switch3.impl.EClass4Impl#getEAttribute9 <em>EAttribute9</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EClass4Impl extends EClass3Impl implements EClass4
{
  /**
   * The default value of the '{@link #getEAttribute8() <em>EAttribute8</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute8()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE8_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute8() <em>EAttribute8</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute8()
   * @generated
   * @ordered
   */
  protected String eAttribute8 = EATTRIBUTE8_EDEFAULT;

  /**
   * The default value of the '{@link #getEAttribute9() <em>EAttribute9</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute9()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE9_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute9() <em>EAttribute9</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute9()
   * @generated
   * @ordered
   */
  protected String eAttribute9 = EATTRIBUTE9_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass4Impl()
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
    return Switch3Package.Literals.ECLASS4;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute8()
  {
    return eAttribute8;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute8(String newEAttribute8)
  {
    String oldEAttribute8 = eAttribute8;
    eAttribute8 = newEAttribute8;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch3Package.ECLASS4__EATTRIBUTE8, oldEAttribute8, eAttribute8));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute9()
  {
    return eAttribute9;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute9(String newEAttribute9)
  {
    String oldEAttribute9 = eAttribute9;
    eAttribute9 = newEAttribute9;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch3Package.ECLASS4__EATTRIBUTE9, oldEAttribute9, eAttribute9));
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
      case Switch3Package.ECLASS4__EATTRIBUTE8:
        return getEAttribute8();
      case Switch3Package.ECLASS4__EATTRIBUTE9:
        return getEAttribute9();
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
      case Switch3Package.ECLASS4__EATTRIBUTE8:
        setEAttribute8((String)newValue);
        return;
      case Switch3Package.ECLASS4__EATTRIBUTE9:
        setEAttribute9((String)newValue);
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
      case Switch3Package.ECLASS4__EATTRIBUTE8:
        setEAttribute8(EATTRIBUTE8_EDEFAULT);
        return;
      case Switch3Package.ECLASS4__EATTRIBUTE9:
        setEAttribute9(EATTRIBUTE9_EDEFAULT);
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
      case Switch3Package.ECLASS4__EATTRIBUTE8:
        return EATTRIBUTE8_EDEFAULT == null ? eAttribute8 != null : !EATTRIBUTE8_EDEFAULT.equals(eAttribute8);
      case Switch3Package.ECLASS4__EATTRIBUTE9:
        return EATTRIBUTE9_EDEFAULT == null ? eAttribute9 != null : !EATTRIBUTE9_EDEFAULT.equals(eAttribute9);
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
    result.append(" (EAttribute8: ");
    result.append(eAttribute8);
    result.append(", EAttribute9: ");
    result.append(eAttribute9);
    result.append(')');
    return result.toString();
  }

} //EClass4Impl
