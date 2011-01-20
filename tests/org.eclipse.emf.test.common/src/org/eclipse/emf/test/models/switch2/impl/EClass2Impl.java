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
 * $Id: EClass2Impl.java,v 1.1 2011/01/20 01:10:23 emerks Exp $
 */
package org.eclipse.emf.test.models.switch2.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.test.models.switch1.impl.EClass1Impl;

import org.eclipse.emf.test.models.switch2.EClass2;
import org.eclipse.emf.test.models.switch2.Switch2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EClass2</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.switch2.impl.EClass2Impl#getEAttribute4 <em>EAttribute4</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.switch2.impl.EClass2Impl#getEAttribute5 <em>EAttribute5</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EClass2Impl extends EClass1Impl implements EClass2
{
  /**
   * The default value of the '{@link #getEAttribute4() <em>EAttribute4</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute4()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE4_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute4() <em>EAttribute4</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute4()
   * @generated
   * @ordered
   */
  protected String eAttribute4 = EATTRIBUTE4_EDEFAULT;

  /**
   * The default value of the '{@link #getEAttribute5() <em>EAttribute5</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute5()
   * @generated
   * @ordered
   */
  protected static final String EATTRIBUTE5_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEAttribute5() <em>EAttribute5</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAttribute5()
   * @generated
   * @ordered
   */
  protected String eAttribute5 = EATTRIBUTE5_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass2Impl()
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
    return Switch2Package.Literals.ECLASS2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute4()
  {
    return eAttribute4;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute4(String newEAttribute4)
  {
    String oldEAttribute4 = eAttribute4;
    eAttribute4 = newEAttribute4;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch2Package.ECLASS2__EATTRIBUTE4, oldEAttribute4, eAttribute4));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEAttribute5()
  {
    return eAttribute5;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEAttribute5(String newEAttribute5)
  {
    String oldEAttribute5 = eAttribute5;
    eAttribute5 = newEAttribute5;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Switch2Package.ECLASS2__EATTRIBUTE5, oldEAttribute5, eAttribute5));
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
      case Switch2Package.ECLASS2__EATTRIBUTE4:
        return getEAttribute4();
      case Switch2Package.ECLASS2__EATTRIBUTE5:
        return getEAttribute5();
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
      case Switch2Package.ECLASS2__EATTRIBUTE4:
        setEAttribute4((String)newValue);
        return;
      case Switch2Package.ECLASS2__EATTRIBUTE5:
        setEAttribute5((String)newValue);
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
      case Switch2Package.ECLASS2__EATTRIBUTE4:
        setEAttribute4(EATTRIBUTE4_EDEFAULT);
        return;
      case Switch2Package.ECLASS2__EATTRIBUTE5:
        setEAttribute5(EATTRIBUTE5_EDEFAULT);
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
      case Switch2Package.ECLASS2__EATTRIBUTE4:
        return EATTRIBUTE4_EDEFAULT == null ? eAttribute4 != null : !EATTRIBUTE4_EDEFAULT.equals(eAttribute4);
      case Switch2Package.ECLASS2__EATTRIBUTE5:
        return EATTRIBUTE5_EDEFAULT == null ? eAttribute5 != null : !EATTRIBUTE5_EDEFAULT.equals(eAttribute5);
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
    result.append(" (EAttribute4: ");
    result.append(eAttribute4);
    result.append(", EAttribute5: ");
    result.append(eAttribute5);
    result.append(')');
    return result.toString();
  }

} //EClass2Impl
