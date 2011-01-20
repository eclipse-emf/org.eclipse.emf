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
 * $Id: EClass3Impl.java,v 1.1 2011/01/20 01:10:23 emerks Exp $
 */
package org.eclipse.emf.test.models.switch2.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.test.models.switch2.EClass3;
import org.eclipse.emf.test.models.switch2.Switch2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EClass3</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.switch2.impl.EClass3Impl#getEAttribute6 <em>EAttribute6</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.switch2.impl.EClass3Impl#getEAttribute7 <em>EAttribute7</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EClass3Impl extends EClass2Impl implements EClass3
{
  /**
   * The default value of the '{@link #getEAttribute6() <em>EAttribute6</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute6()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE6_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute6() <em>EAttribute6</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute6()
   * @generated
   * @ordered
   */
  protected String eAttribute6 = EATTRIBUTE6_EDEFAULT;

  /**
   * The default value of the '{@link #getEAttribute7() <em>EAttribute7</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute7()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE7_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute7() <em>EAttribute7</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute7()
   * @generated
   * @ordered
   */
  protected String eAttribute7 = EATTRIBUTE7_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass3Impl()
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
    return Switch2Package.Literals.ECLASS3;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute6()
  {
    return eAttribute6;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute6(String newEAttribute6)
  {
    String oldEAttribute6 = eAttribute6;
    eAttribute6 = newEAttribute6;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch2Package.ECLASS3__EATTRIBUTE6, oldEAttribute6, eAttribute6));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute7()
  {
    return eAttribute7;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute7(String newEAttribute7)
  {
    String oldEAttribute7 = eAttribute7;
    eAttribute7 = newEAttribute7;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch2Package.ECLASS3__EATTRIBUTE7, oldEAttribute7, eAttribute7));
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
      case Switch2Package.ECLASS3__EATTRIBUTE6:
        return getEAttribute6();
      case Switch2Package.ECLASS3__EATTRIBUTE7:
        return getEAttribute7();
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
      case Switch2Package.ECLASS3__EATTRIBUTE6:
        setEAttribute6((String)newValue);
        return;
      case Switch2Package.ECLASS3__EATTRIBUTE7:
        setEAttribute7((String)newValue);
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
      case Switch2Package.ECLASS3__EATTRIBUTE6:
        setEAttribute6(EATTRIBUTE6_EDEFAULT);
        return;
      case Switch2Package.ECLASS3__EATTRIBUTE7:
        setEAttribute7(EATTRIBUTE7_EDEFAULT);
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
      case Switch2Package.ECLASS3__EATTRIBUTE6:
        return EATTRIBUTE6_EDEFAULT == null ? eAttribute6 != null : !EATTRIBUTE6_EDEFAULT.equals(eAttribute6);
      case Switch2Package.ECLASS3__EATTRIBUTE7:
        return EATTRIBUTE7_EDEFAULT == null ? eAttribute7 != null : !EATTRIBUTE7_EDEFAULT.equals(eAttribute7);
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
    result.append(" (EAttribute6: ");
    result.append(eAttribute6);
    result.append(", EAttribute7: ");
    result.append(eAttribute7);
    result.append(')');
    return result.toString();
  }

} //EClass3Impl
