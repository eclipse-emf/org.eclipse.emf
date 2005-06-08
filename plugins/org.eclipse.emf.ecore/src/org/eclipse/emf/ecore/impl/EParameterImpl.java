/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EParameterImpl.java,v 1.2.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EParameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EParameterImpl#getEOperation <em>EOperation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EParameterImpl extends ETypedElementImpl implements EParameter
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EParameterImpl()
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
    return EcorePackage.eINSTANCE.getEParameter();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public EOperation getEOperation()
  {
    return (eContainerFeatureID == EcorePackage.EPARAMETER__EOPERATION) ? (EOperation)eContainer : null;
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
        case EcorePackage.EPARAMETER__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
        case EcorePackage.EPARAMETER__EOPERATION:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, EcorePackage.EPARAMETER__EOPERATION, msgs);
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
        case EcorePackage.EPARAMETER__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
        case EcorePackage.EPARAMETER__EOPERATION:
          return eBasicSetContainer(null, EcorePackage.EPARAMETER__EOPERATION, msgs);
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
        case EcorePackage.EPARAMETER__EOPERATION:
          return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.EOPERATION__EPARAMETERS, EOperation.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case EcorePackage.EPARAMETER__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.EPARAMETER__NAME:
        return getName();
      case EcorePackage.EPARAMETER__ORDERED:
        return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EPARAMETER__UNIQUE:
        return isUnique() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EPARAMETER__LOWER_BOUND:
        return new Integer(getLowerBound());
      case EcorePackage.EPARAMETER__UPPER_BOUND:
        return new Integer(getUpperBound());
      case EcorePackage.EPARAMETER__MANY:
        return isMany() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EPARAMETER__REQUIRED:
        return isRequired() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EPARAMETER__ETYPE:
        if (resolve) return getEType();
        return basicGetEType();
      case EcorePackage.EPARAMETER__EOPERATION:
        return getEOperation();
    }
    return eDynamicGet(eFeature, resolve);
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
      case EcorePackage.EPARAMETER__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EPARAMETER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.EPARAMETER__ORDERED:
        return ordered != ORDERED_EDEFAULT;
      case EcorePackage.EPARAMETER__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case EcorePackage.EPARAMETER__LOWER_BOUND:
        return lowerBound != LOWER_BOUND_EDEFAULT;
      case EcorePackage.EPARAMETER__UPPER_BOUND:
        return upperBound != UPPER_BOUND_EDEFAULT;
      case EcorePackage.EPARAMETER__MANY:
        return isMany() != MANY_EDEFAULT;
      case EcorePackage.EPARAMETER__REQUIRED:
        return isRequired() != REQUIRED_EDEFAULT;
      case EcorePackage.EPARAMETER__ETYPE:
        return eType != null;
      case EcorePackage.EPARAMETER__EOPERATION:
        return getEOperation() != null;
    }
    return eDynamicIsSet(eFeature);
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
      case EcorePackage.EPARAMETER__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.EPARAMETER__NAME:
        setName((String)newValue);
        return;
      case EcorePackage.EPARAMETER__ORDERED:
        setOrdered(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EPARAMETER__UNIQUE:
        setUnique(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EPARAMETER__LOWER_BOUND:
        setLowerBound(((Integer)newValue).intValue());
        return;
      case EcorePackage.EPARAMETER__UPPER_BOUND:
        setUpperBound(((Integer)newValue).intValue());
        return;
      case EcorePackage.EPARAMETER__ETYPE:
        setEType((EClassifier)newValue);
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
      case EcorePackage.EPARAMETER__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.EPARAMETER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.EPARAMETER__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case EcorePackage.EPARAMETER__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case EcorePackage.EPARAMETER__LOWER_BOUND:
        setLowerBound(LOWER_BOUND_EDEFAULT);
        return;
      case EcorePackage.EPARAMETER__UPPER_BOUND:
        setUpperBound(UPPER_BOUND_EDEFAULT);
        return;
      case EcorePackage.EPARAMETER__ETYPE:
        setEType((EClassifier)null);
        return;
    }
    eDynamicUnset(eFeature);
  }

} //EParameterImpl
