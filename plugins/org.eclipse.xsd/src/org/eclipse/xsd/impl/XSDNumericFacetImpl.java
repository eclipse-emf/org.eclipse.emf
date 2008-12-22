/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: XSDNumericFacetImpl.java,v 1.11 2008/12/22 14:25:48 emerks Exp $
 */
package org.eclipse.xsd.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xsd.XSDNumericFacet;
import org.eclipse.xsd.XSDPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Numeric Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDNumericFacetImpl#isValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDNumericFacetImpl 
  extends XSDFundamentalFacetImpl 
  implements XSDNumericFacet
{
  /**
   * The default value of the '{@link #isValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isValue()
   * @generated
   * @ordered
   */
  protected static final boolean VALUE_EDEFAULT = false;

  /**
   * The flag representing the value of the '{@link #isValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isValue()
   * @generated
   * @ordered
   */
  protected static final int VALUE_EFLAG = 1 << 8;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDNumericFacetImpl()
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
    return XSDPackage.Literals.XSD_NUMERIC_FACET;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isValue()
  {
    return (eFlags & VALUE_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(boolean newValue)
  {
    boolean oldValue = (eFlags & VALUE_EFLAG) != 0;
    if (newValue) eFlags |= VALUE_EFLAG; else eFlags &= ~VALUE_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_NUMERIC_FACET__VALUE, oldValue, newValue));
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
      case XSDPackage.XSD_NUMERIC_FACET__VALUE:
        return isValue();
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
      case XSDPackage.XSD_NUMERIC_FACET__VALUE:
        setValue((Boolean)newValue);
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
      case XSDPackage.XSD_NUMERIC_FACET__VALUE:
        setValue(VALUE_EDEFAULT);
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
      case XSDPackage.XSD_NUMERIC_FACET__VALUE:
        return ((eFlags & VALUE_EFLAG) != 0) != VALUE_EDEFAULT;
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
    result.append(" (value: ");
    result.append((eFlags & VALUE_EFLAG) != 0);
    result.append(')');
    return result.toString();
  }

  @Override
  public Object getEffectiveValue()
  {
    return isValue() ? Boolean.TRUE : Boolean.FALSE;
  }
} 
