/**
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   Thales - initial API and implementation
 */
package org.eclipse.emf.test.core.xrefsopposite.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.core.xrefsopposite.A;
import org.eclipse.emf.test.core.xrefsopposite.APkg;
import org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>APkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.impl.APkgImpl#getOwnedAs <em>Owned As</em>}</li>
 * </ul>
 *
 * @generated
 */
public class APkgImpl extends AbstractAImpl implements APkg
{
  /**
   * The cached value of the '{@link #getOwnedAs() <em>Owned As</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOwnedAs()
   * @generated
   * @ordered
   */
  protected EList<A> ownedAs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected APkgImpl()
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
    return XRefsOppositePackage.Literals.APKG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<A> getOwnedAs()
  {
    if (ownedAs == null)
    {
      ownedAs = new EObjectContainmentWithInverseEList<A>(A.class, this, XRefsOppositePackage.APKG__OWNED_AS, XRefsOppositePackage.A__OWNING_APKG);
    }
    return ownedAs;
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
      case XRefsOppositePackage.APKG__OWNED_AS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedAs()).basicAdd(otherEnd, msgs);
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
      case XRefsOppositePackage.APKG__OWNED_AS:
        return ((InternalEList<?>)getOwnedAs()).basicRemove(otherEnd, msgs);
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
      case XRefsOppositePackage.APKG__OWNED_AS:
        return getOwnedAs();
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
      case XRefsOppositePackage.APKG__OWNED_AS:
        getOwnedAs().clear();
        getOwnedAs().addAll((Collection<? extends A>)newValue);
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
      case XRefsOppositePackage.APKG__OWNED_AS:
        getOwnedAs().clear();
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
      case XRefsOppositePackage.APKG__OWNED_AS:
        return ownedAs != null && !ownedAs.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //APkgImpl
