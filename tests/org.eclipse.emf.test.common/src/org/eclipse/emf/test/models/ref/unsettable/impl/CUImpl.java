/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.ref.unsettable.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ref.unsettable.C4U;
import org.eclipse.emf.test.models.ref.unsettable.CU;
import org.eclipse.emf.test.models.ref.unsettable.DU;
import org.eclipse.emf.test.models.ref.unsettable.URefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CU</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.CUImpl#getDu <em>Du</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.CUImpl#getC4u <em>C4u</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CUImpl extends EObjectImpl implements CU
{
  /**
   * The cached value of the '{@link #getDu() <em>Du</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDu()
   * @generated
   * @ordered
   */
  protected EList<DU> du;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CUImpl()
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
    return URefPackage.Literals.CU;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DU> getDu()
  {
    if (du == null)
    {
      du = new EObjectWithInverseResolvingEList.Unsettable<DU>(DU.class, this, URefPackage.CU__DU, URefPackage.DU__CU);
    }
    return du;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetDu()
  {
    if (du != null) ((InternalEList.Unsettable<?>)du).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetDu()
  {
    return du != null && ((InternalEList.Unsettable<?>)du).isSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public C4U getC4u()
  {
    if (eContainerFeatureID() != URefPackage.CU__C4U) return null;
    return (C4U)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetC4u(C4U newC4u, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newC4u, URefPackage.CU__C4U, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setC4u(C4U newC4u)
  {
    if (newC4u != eInternalContainer() || (eContainerFeatureID() != URefPackage.CU__C4U && newC4u != null))
    {
      if (EcoreUtil.isAncestor(this, newC4u))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newC4u != null)
        msgs = ((InternalEObject)newC4u).eInverseAdd(this, URefPackage.C4U__CU, C4U.class, msgs);
      msgs = basicSetC4u(newC4u, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.CU__C4U, newC4u, newC4u));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case URefPackage.CU__DU:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getDu()).basicAdd(otherEnd, msgs);
      case URefPackage.CU__C4U:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetC4u((C4U)otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
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
      case URefPackage.CU__DU:
        return ((InternalEList<?>)getDu()).basicRemove(otherEnd, msgs);
      case URefPackage.CU__C4U:
        return basicSetC4u(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case URefPackage.CU__C4U:
        return eInternalContainer().eInverseRemove(this, URefPackage.C4U__CU, C4U.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
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
      case URefPackage.CU__DU:
        return getDu();
      case URefPackage.CU__C4U:
        return getC4u();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case URefPackage.CU__DU:
        getDu().clear();
        getDu().addAll((Collection<? extends DU>)newValue);
        return;
      case URefPackage.CU__C4U:
        setC4u((C4U)newValue);
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
      case URefPackage.CU__DU:
        unsetDu();
        return;
      case URefPackage.CU__C4U:
        setC4u((C4U)null);
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
      case URefPackage.CU__DU:
        return isSetDu();
      case URefPackage.CU__C4U:
        return getC4u() != null;
    }
    return super.eIsSet(featureID);
  }

} //CUImpl
