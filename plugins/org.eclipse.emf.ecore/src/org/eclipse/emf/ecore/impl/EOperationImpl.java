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
 * $Id: EOperationImpl.java,v 1.2.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EOperation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EOperationImpl#getEContainingClass <em>EContaining Class</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EOperationImpl#getEParameters <em>EParameters</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EOperationImpl#getEExceptions <em>EExceptions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EOperationImpl extends ETypedElementImpl implements EOperation
{
  /**
   * The cached value of the '{@link #getEParameters() <em>EParameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEParameters()
   * @generated
   * @ordered
   */
  protected EList eParameters = null;

  /**
   * The cached value of the '{@link #getEExceptions() <em>EExceptions</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEExceptions()
   * @generated
   * @ordered
   */
  protected EList eExceptions = null;

  protected EOperationImpl()
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
    return EcorePackage.eINSTANCE.getEOperation();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public EClass getEContainingClass()
  {
    return (eContainerFeatureID == EcorePackage.EOPERATION__ECONTAINING_CLASS) ? (EClass)eContainer : null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getEParameters()
  {
    if (eParameters == null)
    {
      eParameters = new EObjectContainmentWithInverseEList(EParameter.class, this, EcorePackage.EOPERATION__EPARAMETERS, EcorePackage.EPARAMETER__EOPERATION);
    }
    return eParameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getEExceptions()
  {
    if (eExceptions == null)
    {
      eExceptions = new EObjectResolvingEList(EClassifier.class, this, EcorePackage.EOPERATION__EEXCEPTIONS);
    }
    return eExceptions;
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
        case EcorePackage.EOPERATION__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
        case EcorePackage.EOPERATION__ECONTAINING_CLASS:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, EcorePackage.EOPERATION__ECONTAINING_CLASS, msgs);
        case EcorePackage.EOPERATION__EPARAMETERS:
          return ((InternalEList)getEParameters()).basicAdd(otherEnd, msgs);
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
        case EcorePackage.EOPERATION__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
        case EcorePackage.EOPERATION__ECONTAINING_CLASS:
          return eBasicSetContainer(null, EcorePackage.EOPERATION__ECONTAINING_CLASS, msgs);
        case EcorePackage.EOPERATION__EPARAMETERS:
          return ((InternalEList)getEParameters()).basicRemove(otherEnd, msgs);
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
        case EcorePackage.EOPERATION__ECONTAINING_CLASS:
          return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.ECLASS__EOPERATIONS, EClass.class, msgs);
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
      case EcorePackage.EOPERATION__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.EOPERATION__NAME:
        return getName();
      case EcorePackage.EOPERATION__ORDERED:
        return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EOPERATION__UNIQUE:
        return isUnique() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EOPERATION__LOWER_BOUND:
        return new Integer(getLowerBound());
      case EcorePackage.EOPERATION__UPPER_BOUND:
        return new Integer(getUpperBound());
      case EcorePackage.EOPERATION__MANY:
        return isMany() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EOPERATION__REQUIRED:
        return isRequired() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EOPERATION__ETYPE:
        if (resolve) return getEType();
        return basicGetEType();
      case EcorePackage.EOPERATION__ECONTAINING_CLASS:
        return getEContainingClass();
      case EcorePackage.EOPERATION__EPARAMETERS:
        return getEParameters();
      case EcorePackage.EOPERATION__EEXCEPTIONS:
        return getEExceptions();
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
      case EcorePackage.EOPERATION__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EOPERATION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.EOPERATION__ORDERED:
        return ordered != ORDERED_EDEFAULT;
      case EcorePackage.EOPERATION__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case EcorePackage.EOPERATION__LOWER_BOUND:
        return lowerBound != LOWER_BOUND_EDEFAULT;
      case EcorePackage.EOPERATION__UPPER_BOUND:
        return upperBound != UPPER_BOUND_EDEFAULT;
      case EcorePackage.EOPERATION__MANY:
        return isMany() != MANY_EDEFAULT;
      case EcorePackage.EOPERATION__REQUIRED:
        return isRequired() != REQUIRED_EDEFAULT;
      case EcorePackage.EOPERATION__ETYPE:
        return eType != null;
      case EcorePackage.EOPERATION__ECONTAINING_CLASS:
        return getEContainingClass() != null;
      case EcorePackage.EOPERATION__EPARAMETERS:
        return eParameters != null && !eParameters.isEmpty();
      case EcorePackage.EOPERATION__EEXCEPTIONS:
        return eExceptions != null && !eExceptions.isEmpty();
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
      case EcorePackage.EOPERATION__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.EOPERATION__NAME:
        setName((String)newValue);
        return;
      case EcorePackage.EOPERATION__ORDERED:
        setOrdered(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EOPERATION__UNIQUE:
        setUnique(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EOPERATION__LOWER_BOUND:
        setLowerBound(((Integer)newValue).intValue());
        return;
      case EcorePackage.EOPERATION__UPPER_BOUND:
        setUpperBound(((Integer)newValue).intValue());
        return;
      case EcorePackage.EOPERATION__ETYPE:
        setEType((EClassifier)newValue);
        return;
      case EcorePackage.EOPERATION__EPARAMETERS:
        getEParameters().clear();
        getEParameters().addAll((Collection)newValue);
        return;
      case EcorePackage.EOPERATION__EEXCEPTIONS:
        getEExceptions().clear();
        getEExceptions().addAll((Collection)newValue);
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
      case EcorePackage.EOPERATION__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.EOPERATION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.EOPERATION__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case EcorePackage.EOPERATION__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case EcorePackage.EOPERATION__LOWER_BOUND:
        setLowerBound(LOWER_BOUND_EDEFAULT);
        return;
      case EcorePackage.EOPERATION__UPPER_BOUND:
        setUpperBound(UPPER_BOUND_EDEFAULT);
        return;
      case EcorePackage.EOPERATION__ETYPE:
        setEType((EClassifier)null);
        return;
      case EcorePackage.EOPERATION__EPARAMETERS:
        getEParameters().clear();
        return;
      case EcorePackage.EOPERATION__EEXCEPTIONS:
        getEExceptions().clear();
        return;
    }
    eDynamicUnset(eFeature);
  }

}
