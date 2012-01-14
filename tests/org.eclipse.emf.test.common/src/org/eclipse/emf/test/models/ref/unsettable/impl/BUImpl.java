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

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ref.unsettable.AU;
import org.eclipse.emf.test.models.ref.unsettable.BU;
import org.eclipse.emf.test.models.ref.unsettable.C2U;
import org.eclipse.emf.test.models.ref.unsettable.DU;
import org.eclipse.emf.test.models.ref.unsettable.URefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BU</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.BUImpl#getAu <em>Au</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.BUImpl#getC2u <em>C2u</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.BUImpl#getDu <em>Du</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BUImpl extends EObjectImpl implements BU
{
  /**
   * The cached value of the '{@link #getAu() <em>Au</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAu()
   * @generated
   * @ordered
   */
  protected AU au;

  /**
   * This is true if the Au reference has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean auESet;

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
  protected BUImpl()
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
    return URefPackage.Literals.BU;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AU getAu()
  {
    if (au != null && au.eIsProxy())
    {
      InternalEObject oldAu = (InternalEObject)au;
      au = (AU)eResolveProxy(oldAu);
      if (au != oldAu)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, URefPackage.BU__AU, oldAu, au));
      }
    }
    return au;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AU basicGetAu()
  {
    return au;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAu(AU newAu, NotificationChain msgs)
  {
    AU oldAu = au;
    au = newAu;
    boolean oldAuESet = auESet;
    auESet = true;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, URefPackage.BU__AU, oldAu, newAu, !oldAuESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAu(AU newAu)
  {
    if (newAu != au)
    {
      NotificationChain msgs = null;
      if (au != null)
        msgs = ((InternalEObject)au).eInverseRemove(this, URefPackage.AU__BU, AU.class, msgs);
      if (newAu != null)
        msgs = ((InternalEObject)newAu).eInverseAdd(this, URefPackage.AU__BU, AU.class, msgs);
      msgs = basicSetAu(newAu, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    {
      boolean oldAuESet = auESet;
      auESet = true;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.BU__AU, newAu, newAu, !oldAuESet));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicUnsetAu(NotificationChain msgs)
  {
    AU oldAu = au;
    au = null;
    boolean oldAuESet = auESet;
    auESet = false;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, URefPackage.BU__AU, oldAu, null, oldAuESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAu()
  {
    if (au != null)
    {
      NotificationChain msgs = null;
      msgs = ((InternalEObject)au).eInverseRemove(this, URefPackage.AU__BU, AU.class, msgs);
      msgs = basicUnsetAu(msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    {
      boolean oldAuESet = auESet;
      auESet = false;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.UNSET, URefPackage.BU__AU, null, null, oldAuESet));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAu()
  {
    return auESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public C2U getC2u()
  {
    if (eContainerFeatureID() != URefPackage.BU__C2U) return null;
    return (C2U)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetC2u(C2U newC2u, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newC2u, URefPackage.BU__C2U, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setC2u(C2U newC2u)
  {
    if (newC2u != eInternalContainer() || (eContainerFeatureID() != URefPackage.BU__C2U && newC2u != null))
    {
      if (EcoreUtil.isAncestor(this, newC2u))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newC2u != null)
        msgs = ((InternalEObject)newC2u).eInverseAdd(this, URefPackage.C2U__BU, C2U.class, msgs);
      msgs = basicSetC2u(newC2u, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.BU__C2U, newC2u, newC2u));
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
      du = new EObjectResolvingEList.Unsettable<DU>(DU.class, this, URefPackage.BU__DU);
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
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case URefPackage.BU__AU:
        if (au != null)
          msgs = ((InternalEObject)au).eInverseRemove(this, URefPackage.AU__BU, AU.class, msgs);
        return basicSetAu((AU)otherEnd, msgs);
      case URefPackage.BU__C2U:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetC2u((C2U)otherEnd, msgs);
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
      case URefPackage.BU__AU:
        return basicUnsetAu(msgs);
      case URefPackage.BU__C2U:
        return basicSetC2u(null, msgs);
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
      case URefPackage.BU__C2U:
        return eInternalContainer().eInverseRemove(this, URefPackage.C2U__BU, C2U.class, msgs);
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
      case URefPackage.BU__AU:
        if (resolve) return getAu();
        return basicGetAu();
      case URefPackage.BU__C2U:
        return getC2u();
      case URefPackage.BU__DU:
        return getDu();
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
      case URefPackage.BU__AU:
        setAu((AU)newValue);
        return;
      case URefPackage.BU__C2U:
        setC2u((C2U)newValue);
        return;
      case URefPackage.BU__DU:
        getDu().clear();
        getDu().addAll((Collection<? extends DU>)newValue);
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
      case URefPackage.BU__AU:
        unsetAu();
        return;
      case URefPackage.BU__C2U:
        setC2u((C2U)null);
        return;
      case URefPackage.BU__DU:
        unsetDu();
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
      case URefPackage.BU__AU:
        return isSetAu();
      case URefPackage.BU__C2U:
        return getC2u() != null;
      case URefPackage.BU__DU:
        return isSetDu();
    }
    return super.eIsSet(featureID);
  }

} //BUImpl
