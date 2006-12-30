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
 * $Id: NameTypeImpl.java,v 1.2 2006/12/30 03:44:07 marcelop Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.test.models.personal.mixed.MixedPackage;
import org.eclipse.emf.test.models.personal.mixed.NameType;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;
import org.eclipse.emf.ecore.sdo.util.BasicESequence;
import org.eclipse.emf.ecore.sdo.util.ESequence;

import commonj.sdo.Sequence;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Name Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl#getFamily <em>Family</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl#getGiven <em>Given</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NameTypeImpl extends EDataObjectImpl implements NameType
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected ESequence mixed = null;

  /**
   * The default value of the '{@link #getFamily() <em>Family</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFamily()
   * @generated
   * @ordered
   */
  protected static final String FAMILY_EDEFAULT = null;

  /**
   * The default value of the '{@link #getGiven() <em>Given</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGiven()
   * @generated
   * @ordered
   */
  protected static final String GIVEN_EDEFAULT = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NameTypeImpl()
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
    return MixedPackage.Literals.NAME_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Sequence getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicESequence(new BasicFeatureMap(this, MixedPackage.NAME_TYPE__MIXED));
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFamily()
  {
    return (String)((FeatureMap.Internal.Wrapper)getMixed()).featureMap().get(MixedPackage.Literals.NAME_TYPE__FAMILY, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFamily(String newFamily)
  {
    ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).set(MixedPackage.Literals.NAME_TYPE__FAMILY, newFamily);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getGiven()
  {
    return (String)((FeatureMap.Internal.Wrapper)getMixed()).featureMap().get(MixedPackage.Literals.NAME_TYPE__GIVEN, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGiven(String newGiven)
  {
    ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).set(MixedPackage.Literals.NAME_TYPE__GIVEN, newGiven);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MixedPackage.NAME_TYPE__MIXED:
        return ((InternalEList<?>)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case MixedPackage.NAME_TYPE__MIXED:
        if (coreType) return ((FeatureMap.Internal.Wrapper)getMixed()).featureMap();
        return getMixed();
      case MixedPackage.NAME_TYPE__FAMILY:
        return getFamily();
      case MixedPackage.NAME_TYPE__GIVEN:
        return getGiven();
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
      case MixedPackage.NAME_TYPE__MIXED:
        ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).set(newValue);
        return;
      case MixedPackage.NAME_TYPE__FAMILY:
        setFamily((String)newValue);
        return;
      case MixedPackage.NAME_TYPE__GIVEN:
        setGiven((String)newValue);
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
      case MixedPackage.NAME_TYPE__MIXED:
        ((FeatureMap.Internal.Wrapper)getMixed()).featureMap().clear();
        return;
      case MixedPackage.NAME_TYPE__FAMILY:
        setFamily(FAMILY_EDEFAULT);
        return;
      case MixedPackage.NAME_TYPE__GIVEN:
        setGiven(GIVEN_EDEFAULT);
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
      case MixedPackage.NAME_TYPE__MIXED:
        return mixed != null && !mixed.featureMap().isEmpty();
      case MixedPackage.NAME_TYPE__FAMILY:
        return FAMILY_EDEFAULT == null ? getFamily() != null : !FAMILY_EDEFAULT.equals(getFamily());
      case MixedPackage.NAME_TYPE__GIVEN:
        return GIVEN_EDEFAULT == null ? getGiven() != null : !GIVEN_EDEFAULT.equals(getGiven());
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
    result.append(" (mixed: ");
    result.append(mixed);
    result.append(')');
    return result.toString();
  }

} //NameTypeImpl
