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
 * $Id: EClass0Impl.java,v 1.1 2011/01/20 01:10:22 emerks Exp $
 */
package org.eclipse.emf.test.models.switch1.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.test.models.switch1.EClass0;
import org.eclipse.emf.test.models.switch1.Switch1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EClass0</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.switch1.impl.EClass0Impl#getEAttribute0 <em>EAttribute0</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.switch1.impl.EClass0Impl#getEAttribute1 <em>EAttribute1</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EClass0Impl extends EObjectImpl implements EClass0
{
  /**
   * The default value of the '{@link #getEAttribute0() <em>EAttribute0</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute0()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE0_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute0() <em>EAttribute0</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute0()
   * @generated
   * @ordered
   */
  protected String eAttribute0 = EATTRIBUTE0_EDEFAULT;

  /**
   * The default value of the '{@link #getEAttribute1() <em>EAttribute1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute1()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE1_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute1() <em>EAttribute1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute1()
   * @generated
   * @ordered
   */
  protected String eAttribute1 = EATTRIBUTE1_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass0Impl()
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
    return Switch1Package.Literals.ECLASS0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute0()
  {
    return eAttribute0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute0(String newEAttribute0)
  {
    String oldEAttribute0 = eAttribute0;
    eAttribute0 = newEAttribute0;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch1Package.ECLASS0__EATTRIBUTE0, oldEAttribute0, eAttribute0));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute1()
  {
    return eAttribute1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute1(String newEAttribute1)
  {
    String oldEAttribute1 = eAttribute1;
    eAttribute1 = newEAttribute1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch1Package.ECLASS0__EATTRIBUTE1, oldEAttribute1, eAttribute1));
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
      case Switch1Package.ECLASS0__EATTRIBUTE0:
        return getEAttribute0();
      case Switch1Package.ECLASS0__EATTRIBUTE1:
        return getEAttribute1();
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
      case Switch1Package.ECLASS0__EATTRIBUTE0:
        setEAttribute0((String)newValue);
        return;
      case Switch1Package.ECLASS0__EATTRIBUTE1:
        setEAttribute1((String)newValue);
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
      case Switch1Package.ECLASS0__EATTRIBUTE0:
        setEAttribute0(EATTRIBUTE0_EDEFAULT);
        return;
      case Switch1Package.ECLASS0__EATTRIBUTE1:
        setEAttribute1(EATTRIBUTE1_EDEFAULT);
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
      case Switch1Package.ECLASS0__EATTRIBUTE0:
        return EATTRIBUTE0_EDEFAULT == null ? eAttribute0 != null : !EATTRIBUTE0_EDEFAULT.equals(eAttribute0);
      case Switch1Package.ECLASS0__EATTRIBUTE1:
        return EATTRIBUTE1_EDEFAULT == null ? eAttribute1 != null : !EATTRIBUTE1_EDEFAULT.equals(eAttribute1);
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
    result.append(" (EAttribute0: ");
    result.append(eAttribute0);
    result.append(", EAttribute1: ");
    result.append(eAttribute1);
    result.append(')');
    return result.toString();
  }

} //EClass0Impl
