/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: MappingStrategyImpl.java,v 1.2 2004/12/16 21:55:06 emerks Exp $
 */
package org.eclipse.emf.mapping.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingHelper;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingStrategy;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Strategy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class MappingStrategyImpl extends MappingHelperImpl implements MappingStrategy
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MappingStrategyImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return MappingPackage.eINSTANCE.getMappingStrategy();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case MappingPackage.MAPPING_STRATEGY__MAPPER:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, MappingPackage.MAPPING_STRATEGY__MAPPER, msgs);
        case MappingPackage.MAPPING_STRATEGY__NESTED_IN:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, MappingPackage.MAPPING_STRATEGY__NESTED_IN, msgs);
        case MappingPackage.MAPPING_STRATEGY__NESTED:
          return ((InternalEList)getNested()).basicAdd(otherEnd, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case MappingPackage.MAPPING_STRATEGY__MAPPER:
          return eBasicSetContainer(null, MappingPackage.MAPPING_STRATEGY__MAPPER, msgs);
        case MappingPackage.MAPPING_STRATEGY__NESTED_IN:
          return eBasicSetContainer(null, MappingPackage.MAPPING_STRATEGY__NESTED_IN, msgs);
        case MappingPackage.MAPPING_STRATEGY__NESTED:
          return ((InternalEList)getNested()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case MappingPackage.MAPPING_STRATEGY__MAPPER:
          return eContainer.eInverseRemove(this, MappingPackage.MAPPING__HELPER, Mapping.class, msgs);
        case MappingPackage.MAPPING_STRATEGY__NESTED_IN:
          return eContainer.eInverseRemove(this, MappingPackage.MAPPING_HELPER__NESTED, MappingHelper.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case MappingPackage.MAPPING_STRATEGY__MAPPER:
        return getMapper();
      case MappingPackage.MAPPING_STRATEGY__HELPED_OBJECT:
        if (resolve) return getHelpedObject();
        return basicGetHelpedObject();
      case MappingPackage.MAPPING_STRATEGY__NESTED_IN:
        return getNestedIn();
      case MappingPackage.MAPPING_STRATEGY__NESTED:
        return getNested();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case MappingPackage.MAPPING_STRATEGY__MAPPER:
        setMapper((Mapping)newValue);
        return;
      case MappingPackage.MAPPING_STRATEGY__HELPED_OBJECT:
        setHelpedObject((EObject)newValue);
        return;
      case MappingPackage.MAPPING_STRATEGY__NESTED_IN:
        setNestedIn((MappingHelper)newValue);
        return;
      case MappingPackage.MAPPING_STRATEGY__NESTED:
        getNested().clear();
        getNested().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case MappingPackage.MAPPING_STRATEGY__MAPPER:
        setMapper((Mapping)null);
        return;
      case MappingPackage.MAPPING_STRATEGY__HELPED_OBJECT:
        setHelpedObject((EObject)null);
        return;
      case MappingPackage.MAPPING_STRATEGY__NESTED_IN:
        setNestedIn((MappingHelper)null);
        return;
      case MappingPackage.MAPPING_STRATEGY__NESTED:
        getNested().clear();
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case MappingPackage.MAPPING_STRATEGY__MAPPER:
        return getMapper() != null;
      case MappingPackage.MAPPING_STRATEGY__HELPED_OBJECT:
        return helpedObject != null;
      case MappingPackage.MAPPING_STRATEGY__NESTED_IN:
        return getNestedIn() != null;
      case MappingPackage.MAPPING_STRATEGY__NESTED:
        return nested != null && !nested.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

} //MappingStrategyImpl

