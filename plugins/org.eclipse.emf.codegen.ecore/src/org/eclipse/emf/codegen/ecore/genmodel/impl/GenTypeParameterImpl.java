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
 * $Id: GenTypeParameterImpl.java,v 1.1 2006/12/05 20:30:02 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Type Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypeParameterImpl#getEcoreTypeParameter <em>Ecore Type Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenTypeParameterImpl extends GenBaseImpl implements GenTypeParameter
{
  /**
   * The cached value of the '{@link #getEcoreTypeParameter() <em>Ecore Type Parameter</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreTypeParameter()
   * @generated
   * @ordered
   */
  protected ETypeParameter ecoreTypeParameter = null;
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GenTypeParameterImpl()
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
    return GenModelPackage.Literals.GEN_TYPE_PARAMETER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ETypeParameter getEcoreTypeParameter()
  {
    if (ecoreTypeParameter != null && ecoreTypeParameter.eIsProxy())
    {
      InternalEObject oldEcoreTypeParameter = (InternalEObject)ecoreTypeParameter;
      ecoreTypeParameter = (ETypeParameter)eResolveProxy(oldEcoreTypeParameter);
      if (ecoreTypeParameter != oldEcoreTypeParameter)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER, oldEcoreTypeParameter, ecoreTypeParameter));
      }
    }
    return ecoreTypeParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ETypeParameter basicGetEcoreTypeParameter()
  {
    return ecoreTypeParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreTypeParameter(ETypeParameter newEcoreTypeParameter)
  {
    ETypeParameter oldEcoreTypeParameter = ecoreTypeParameter;
    ecoreTypeParameter = newEcoreTypeParameter;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER, oldEcoreTypeParameter, ecoreTypeParameter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER:
        if (resolve) return getEcoreTypeParameter();
        return basicGetEcoreTypeParameter();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER:
        setEcoreTypeParameter((ETypeParameter)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER:
        setEcoreTypeParameter((ETypeParameter)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER:
        return ecoreTypeParameter != null;
    }
    return super.eIsSet(featureID);
  }

  public String getName()
  {
    return getEcoreTypeParameter().getName();
  }

  public void initialize(ETypeParameter eTypeParameter)
  {
    setEcoreTypeParameter(eTypeParameter);
  }

  public boolean reconcile(GenTypeParameter oldGenTypeParameterVersion)
  {
    reconcileSettings(oldGenTypeParameterVersion);
    return true;
  }

  protected void reconcileSettings(GenTypeParameter oldGenTypeParameterVersion)
  {
    reconcileGenAnnotations(oldGenTypeParameterVersion);
  }

  public boolean reconcile()
  {
    ETypeParameter eTypeParameter = getEcoreTypeParameter();
    if (eTypeParameter == null || eTypeParameter.eIsProxy()  || eTypeParameter.eResource() == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

} //GenTypeParameterImpl
