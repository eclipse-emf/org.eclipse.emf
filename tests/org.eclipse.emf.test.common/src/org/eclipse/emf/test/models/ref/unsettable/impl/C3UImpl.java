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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ref.unsettable.C3U;
import org.eclipse.emf.test.models.ref.unsettable.CU;
import org.eclipse.emf.test.models.ref.unsettable.DU;
import org.eclipse.emf.test.models.ref.unsettable.URefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>C3U</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.C3UImpl#getCu <em>Cu</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.C3UImpl#getDu <em>Du</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class C3UImpl extends EObjectImpl implements C3U
{
  /**
   * The cached value of the '{@link #getCu() <em>Cu</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCu()
   * @generated
   * @ordered
   */
  protected CU cu;

  /**
   * This is true if the Cu containment reference has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean cuESet;

  /**
   * The cached value of the '{@link #getDu() <em>Du</em>}' containment reference list.
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
  protected C3UImpl()
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
    return URefPackage.Literals.C3U;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CU getCu()
  {
    return cu;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCu(CU newCu, NotificationChain msgs)
  {
    CU oldCu = cu;
    cu = newCu;
    boolean oldCuESet = cuESet;
    cuESet = true;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, URefPackage.C3U__CU, oldCu, newCu, !oldCuESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCu(CU newCu)
  {
    if (newCu != cu)
    {
      NotificationChain msgs = null;
      if (cu != null)
        msgs = ((InternalEObject)cu).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - URefPackage.C3U__CU, null, msgs);
      if (newCu != null)
        msgs = ((InternalEObject)newCu).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - URefPackage.C3U__CU, null, msgs);
      msgs = basicSetCu(newCu, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    {
      boolean oldCuESet = cuESet;
      cuESet = true;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.C3U__CU, newCu, newCu, !oldCuESet));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicUnsetCu(NotificationChain msgs)
  {
    CU oldCu = cu;
    cu = null;
    boolean oldCuESet = cuESet;
    cuESet = false;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, URefPackage.C3U__CU, oldCu, null, oldCuESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetCu()
  {
    if (cu != null)
    {
      NotificationChain msgs = null;
      msgs = ((InternalEObject)cu).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - URefPackage.C3U__CU, null, msgs);
      msgs = basicUnsetCu(msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    {
      boolean oldCuESet = cuESet;
      cuESet = false;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.UNSET, URefPackage.C3U__CU, null, null, oldCuESet));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetCu()
  {
    return cuESet;
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
      du = new EObjectContainmentEList.Unsettable<DU>(DU.class, this, URefPackage.C3U__DU);
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
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case URefPackage.C3U__CU:
        return basicUnsetCu(msgs);
      case URefPackage.C3U__DU:
        return ((InternalEList<?>)getDu()).basicRemove(otherEnd, msgs);
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
      case URefPackage.C3U__CU:
        return getCu();
      case URefPackage.C3U__DU:
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
      case URefPackage.C3U__CU:
        setCu((CU)newValue);
        return;
      case URefPackage.C3U__DU:
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
      case URefPackage.C3U__CU:
        unsetCu();
        return;
      case URefPackage.C3U__DU:
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
      case URefPackage.C3U__CU:
        return isSetCu();
      case URefPackage.C3U__DU:
        return isSetDu();
    }
    return super.eIsSet(featureID);
  }

} //C3UImpl
