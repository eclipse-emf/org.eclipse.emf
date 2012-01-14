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
import org.eclipse.emf.test.models.ref.E;
import org.eclipse.emf.test.models.ref.RefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>D</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.DImpl#getC <em>C</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.DImpl#getE <em>E</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.DImpl#getC4 <em>C4</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DImpl extends EObjectImpl implements D
{
  /**
   * The cached value of the '{@link #getC() <em>C</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getC()
   * @generated
   * @ordered
   */
  protected C c;

  /**
   * The cached value of the '{@link #getE() <em>E</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getE()
   * @generated
   * @ordered
   */
  protected EList<E> e;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DImpl()
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
    return RefPackage.Literals.D;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public C getC()
  {
    if (c != null && c.eIsProxy())
    {
      InternalEObject oldC = (InternalEObject)c;
      c = (C)eResolveProxy(oldC);
      if (c != oldC)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, RefPackage.D__C, oldC, c));
      }
    }
    return c;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public C basicGetC()
  {
    return c;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetC(C newC, NotificationChain msgs)
  {
    C oldC = c;
    c = newC;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RefPackage.D__C, oldC, newC);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setC(C newC)
  {
    if (newC != c)
    {
      NotificationChain msgs = null;
      if (c != null)
        msgs = ((InternalEObject)c).eInverseRemove(this, RefPackage.C__D, C.class, msgs);
      if (newC != null)
        msgs = ((InternalEObject)newC).eInverseAdd(this, RefPackage.C__D, C.class, msgs);
      msgs = basicSetC(newC, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RefPackage.D__C, newC, newC));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<E> getE()
  {
    if (e == null)
    {
      e = new EObjectWithInverseResolvingEList.ManyInverse<E>(E.class, this, RefPackage.D__E, RefPackage.E__D);
    }
    return e;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public C4 getC4()
  {
    if (eContainerFeatureID() != RefPackage.D__C4) return null;
    return (C4)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetC4(C4 newC4, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newC4, RefPackage.D__C4, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setC4(C4 newC4)
  {
    if (newC4 != eInternalContainer() || (eContainerFeatureID() != RefPackage.D__C4 && newC4 != null))
    {
      if (EcoreUtil.isAncestor(this, newC4))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newC4 != null)
        msgs = ((InternalEObject)newC4).eInverseAdd(this, RefPackage.C4__D, C4.class, msgs);
      msgs = basicSetC4(newC4, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RefPackage.D__C4, newC4, newC4));
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
      case RefPackage.D__C:
        if (c != null)
          msgs = ((InternalEObject)c).eInverseRemove(this, RefPackage.C__D, C.class, msgs);
        return basicSetC((C)otherEnd, msgs);
      case RefPackage.D__E:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getE()).basicAdd(otherEnd, msgs);
      case RefPackage.D__C4:
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
      case RefPackage.D__C:
        return basicSetC(null, msgs);
      case RefPackage.D__E:
        return ((InternalEList<?>)getE()).basicRemove(otherEnd, msgs);
      case RefPackage.D__C4:
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
      case RefPackage.D__C4:
        return eInternalContainer().eInverseRemove(this, RefPackage.C4__D, C4.class, msgs);
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
      case RefPackage.D__C:
        if (resolve) return getC();
        return basicGetC();
      case RefPackage.D__E:
        return getE();
      case RefPackage.D__C4:
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
      case RefPackage.D__C:
        setC((C)newValue);
        return;
      case RefPackage.D__E:
        getE().clear();
        getE().addAll((Collection<? extends E>)newValue);
        return;
      case RefPackage.D__C4:
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
      case RefPackage.D__C:
        setC((C)null);
        return;
      case RefPackage.D__E:
        getE().clear();
        return;
      case RefPackage.D__C4:
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
      case RefPackage.D__C:
        return c != null;
      case RefPackage.D__E:
        return e != null && !e.isEmpty();
      case RefPackage.D__C4:
        return getC4() != null;
    }
    return super.eIsSet(featureID);
  }

} //DImpl
