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
package org.eclipse.emf.test.models.ref.impl;

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

import org.eclipse.emf.test.models.ref.C;
import org.eclipse.emf.test.models.ref.C4;
import org.eclipse.emf.test.models.ref.D;
import org.eclipse.emf.test.models.ref.RefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>C</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.CImpl#getD <em>D</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.CImpl#getC4 <em>C4</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CImpl extends EObjectImpl implements C
{
  /**
   * The cached value of the '{@link #getD() <em>D</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getD()
   * @generated
   * @ordered
   */
  protected EList<D> d;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CImpl()
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
    return RefPackage.Literals.C;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<D> getD()
  {
    if (d == null)
    {
      d = new EObjectWithInverseResolvingEList<D>(D.class, this, RefPackage.C__D, RefPackage.D__C);
    }
    return d;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public C4 getC4()
  {
    if (eContainerFeatureID() != RefPackage.C__C4) return null;
    return (C4)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetC4(C4 newC4, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newC4, RefPackage.C__C4, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setC4(C4 newC4)
  {
    if (newC4 != eInternalContainer() || (eContainerFeatureID() != RefPackage.C__C4 && newC4 != null))
    {
      if (EcoreUtil.isAncestor(this, newC4))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newC4 != null)
        msgs = ((InternalEObject)newC4).eInverseAdd(this, RefPackage.C4__C, C4.class, msgs);
      msgs = basicSetC4(newC4, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RefPackage.C__C4, newC4, newC4));
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
      case RefPackage.C__D:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getD()).basicAdd(otherEnd, msgs);
      case RefPackage.C__C4:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetC4((C4)otherEnd, msgs);
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
      case RefPackage.C__D:
        return ((InternalEList<?>)getD()).basicRemove(otherEnd, msgs);
      case RefPackage.C__C4:
        return basicSetC4(null, msgs);
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
      case RefPackage.C__C4:
        return eInternalContainer().eInverseRemove(this, RefPackage.C4__C, C4.class, msgs);
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
      case RefPackage.C__D:
        return getD();
      case RefPackage.C__C4:
        return getC4();
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
      case RefPackage.C__D:
        getD().clear();
        getD().addAll((Collection<? extends D>)newValue);
        return;
      case RefPackage.C__C4:
        setC4((C4)newValue);
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
      case RefPackage.C__D:
        getD().clear();
        return;
      case RefPackage.C__C4:
        setC4((C4)null);
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
      case RefPackage.C__D:
        return d != null && !d.isEmpty();
      case RefPackage.C__C4:
        return getC4() != null;
    }
    return super.eIsSet(featureID);
  }

} //CImpl
