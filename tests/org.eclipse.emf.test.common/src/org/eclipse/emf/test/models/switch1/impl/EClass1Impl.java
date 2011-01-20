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
 * $Id: EClass1Impl.java,v 1.1 2011/01/20 01:10:22 emerks Exp $
 */
package org.eclipse.emf.test.models.switch1.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.test.models.switch1.EClass1;
import org.eclipse.emf.test.models.switch1.Switch1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EClass1</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.switch1.impl.EClass1Impl#getEAttribute2 <em>EAttribute2</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.switch1.impl.EClass1Impl#getEAttribute3 <em>EAttribute3</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EClass1Impl extends EClass0Impl implements EClass1
{
  /**
   * The default value of the '{@link #getEAttribute2() <em>EAttribute2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute2()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE2_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute2() <em>EAttribute2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute2()
   * @generated
   * @ordered
   */
  protected String eAttribute2 = EATTRIBUTE2_EDEFAULT;

  /**
   * The default value of the '{@link #getEAttribute3() <em>EAttribute3</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute3()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE3_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute3() <em>EAttribute3</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute3()
   * @generated
   * @ordered
   */
  protected String eAttribute3 = EATTRIBUTE3_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass1Impl()
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
    return Switch1Package.Literals.ECLASS1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute2()
  {
    return eAttribute2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute2(String newEAttribute2)
  {
    String oldEAttribute2 = eAttribute2;
    eAttribute2 = newEAttribute2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch1Package.ECLASS1__EATTRIBUTE2, oldEAttribute2, eAttribute2));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute3()
  {
    return eAttribute3;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute3(String newEAttribute3)
  {
    String oldEAttribute3 = eAttribute3;
    eAttribute3 = newEAttribute3;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch1Package.ECLASS1__EATTRIBUTE3, oldEAttribute3, eAttribute3));
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
      case Switch1Package.ECLASS1__EATTRIBUTE2:
        return getEAttribute2();
      case Switch1Package.ECLASS1__EATTRIBUTE3:
        return getEAttribute3();
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
      case Switch1Package.ECLASS1__EATTRIBUTE2:
        setEAttribute2((String)newValue);
        return;
      case Switch1Package.ECLASS1__EATTRIBUTE3:
        setEAttribute3((String)newValue);
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
      case Switch1Package.ECLASS1__EATTRIBUTE2:
        setEAttribute2(EATTRIBUTE2_EDEFAULT);
        return;
      case Switch1Package.ECLASS1__EATTRIBUTE3:
        setEAttribute3(EATTRIBUTE3_EDEFAULT);
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
      case Switch1Package.ECLASS1__EATTRIBUTE2:
        return EATTRIBUTE2_EDEFAULT == null ? eAttribute2 != null : !EATTRIBUTE2_EDEFAULT.equals(eAttribute2);
      case Switch1Package.ECLASS1__EATTRIBUTE3:
        return EATTRIBUTE3_EDEFAULT == null ? eAttribute3 != null : !EATTRIBUTE3_EDEFAULT.equals(eAttribute3);
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
    result.append(" (EAttribute2: ");
    result.append(eAttribute2);
    result.append(", EAttribute3: ");
    result.append(eAttribute3);
    result.append(')');
    return result.toString();
  }

} //EClass1Impl
