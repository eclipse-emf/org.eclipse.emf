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
 * $Id: EClass5Impl.java,v 1.1 2011/01/20 01:10:22 emerks Exp $
 */
package org.eclipse.emf.test.models.switch3.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.test.models.switch3.EClass5;
import org.eclipse.emf.test.models.switch3.Switch3Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EClass5</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.switch3.impl.EClass5Impl#getEAttribute10 <em>EAttribute10</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.switch3.impl.EClass5Impl#getEAttribute11 <em>EAttribute11</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EClass5Impl extends EClass4Impl implements EClass5
{
  /**
   * The default value of the '{@link #getEAttribute10() <em>EAttribute10</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute10()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE10_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute10() <em>EAttribute10</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute10()
   * @generated
   * @ordered
   */
  protected String eAttribute10 = EATTRIBUTE10_EDEFAULT;

  /**
   * The default value of the '{@link #getEAttribute11() <em>EAttribute11</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute11()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE11_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute11() <em>EAttribute11</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute11()
   * @generated
   * @ordered
   */
  protected String eAttribute11 = EATTRIBUTE11_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass5Impl()
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
    return Switch3Package.Literals.ECLASS5;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute10()
  {
    return eAttribute10;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute10(String newEAttribute10)
  {
    String oldEAttribute10 = eAttribute10;
    eAttribute10 = newEAttribute10;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch3Package.ECLASS5__EATTRIBUTE10, oldEAttribute10, eAttribute10));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute11()
  {
    return eAttribute11;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute11(String newEAttribute11)
  {
    String oldEAttribute11 = eAttribute11;
    eAttribute11 = newEAttribute11;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch3Package.ECLASS5__EATTRIBUTE11, oldEAttribute11, eAttribute11));
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
      case Switch3Package.ECLASS5__EATTRIBUTE10:
        return getEAttribute10();
      case Switch3Package.ECLASS5__EATTRIBUTE11:
        return getEAttribute11();
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
      case Switch3Package.ECLASS5__EATTRIBUTE10:
        setEAttribute10((String)newValue);
        return;
      case Switch3Package.ECLASS5__EATTRIBUTE11:
        setEAttribute11((String)newValue);
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
      case Switch3Package.ECLASS5__EATTRIBUTE10:
        setEAttribute10(EATTRIBUTE10_EDEFAULT);
        return;
      case Switch3Package.ECLASS5__EATTRIBUTE11:
        setEAttribute11(EATTRIBUTE11_EDEFAULT);
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
      case Switch3Package.ECLASS5__EATTRIBUTE10:
        return EATTRIBUTE10_EDEFAULT == null ? eAttribute10 != null : !EATTRIBUTE10_EDEFAULT.equals(eAttribute10);
      case Switch3Package.ECLASS5__EATTRIBUTE11:
        return EATTRIBUTE11_EDEFAULT == null ? eAttribute11 != null : !EATTRIBUTE11_EDEFAULT.equals(eAttribute11);
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
    result.append(" (EAttribute10: ");
    result.append(eAttribute10);
    result.append(", EAttribute11: ");
    result.append(eAttribute11);
    result.append(')');
    return result.toString();
  }

} //EClass5Impl
