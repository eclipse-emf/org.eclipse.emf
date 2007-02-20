/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: LinkTypeImpl.java,v 1.3 2007/02/20 17:42:40 emerks Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import commonj.sdo.Sequence;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.ecore.sdo.util.BasicESequence;
import org.eclipse.emf.ecore.sdo.util.ESequence;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.personal.mixed.LinkType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl#getManager <em>Manager</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl#getSubordinates <em>Subordinates</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkTypeImpl extends EDataObjectImpl implements LinkType
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected ESequence mixed;

  /**
   * The default value of the '{@link #getManager() <em>Manager</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getManager()
   * @generated
   * @ordered
   */
  protected static final String MANAGER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getManager() <em>Manager</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getManager()
   * @generated
   * @ordered
   */
  protected String manager = MANAGER_EDEFAULT;

  /**
   * The default value of the '{@link #getSubordinates() <em>Subordinates</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubordinates()
   * @generated
   * @ordered
   */
  protected static final List<String> SUBORDINATES_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSubordinates() <em>Subordinates</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubordinates()
   * @generated
   * @ordered
   */
  protected List<String> subordinates = SUBORDINATES_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LinkTypeImpl()
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
    return MixedPackageImpl.Literals.LINK_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Sequence getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicESequence(new BasicFeatureMap(this, MixedPackageImpl.LINK_TYPE__MIXED));
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getManager()
  {
    return manager;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setManager(String newManager)
  {
    String oldManager = manager;
    manager = newManager;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MixedPackageImpl.LINK_TYPE__MANAGER, oldManager, manager));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<String> getSubordinates()
  {
    return subordinates;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubordinates(List<String> newSubordinates)
  {
    List<String> oldSubordinates = subordinates;
    subordinates = newSubordinates;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MixedPackageImpl.LINK_TYPE__SUBORDINATES, oldSubordinates, subordinates));
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
      case MixedPackageImpl.LINK_TYPE__MIXED:
        return ((InternalEList<?>)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).basicRemove(otherEnd, msgs);
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
      case MixedPackageImpl.LINK_TYPE__MIXED:
        if (coreType) return ((FeatureMap.Internal.Wrapper)getMixed()).featureMap();
        return getMixed();
      case MixedPackageImpl.LINK_TYPE__MANAGER:
        return getManager();
      case MixedPackageImpl.LINK_TYPE__SUBORDINATES:
        return getSubordinates();
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
      case MixedPackageImpl.LINK_TYPE__MIXED:
        ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).set(newValue);
        return;
      case MixedPackageImpl.LINK_TYPE__MANAGER:
        setManager((String)newValue);
        return;
      case MixedPackageImpl.LINK_TYPE__SUBORDINATES:
        setSubordinates((List<String>)newValue);
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
      case MixedPackageImpl.LINK_TYPE__MIXED:
        ((FeatureMap.Internal.Wrapper)getMixed()).featureMap().clear();
        return;
      case MixedPackageImpl.LINK_TYPE__MANAGER:
        setManager(MANAGER_EDEFAULT);
        return;
      case MixedPackageImpl.LINK_TYPE__SUBORDINATES:
        setSubordinates(SUBORDINATES_EDEFAULT);
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
      case MixedPackageImpl.LINK_TYPE__MIXED:
        return mixed != null && !mixed.featureMap().isEmpty();
      case MixedPackageImpl.LINK_TYPE__MANAGER:
        return MANAGER_EDEFAULT == null ? manager != null : !MANAGER_EDEFAULT.equals(manager);
      case MixedPackageImpl.LINK_TYPE__SUBORDINATES:
        return SUBORDINATES_EDEFAULT == null ? subordinates != null : !SUBORDINATES_EDEFAULT.equals(subordinates);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (mixed: ");
    result.append(mixed);
    result.append(", manager: ");
    result.append(manager);
    result.append(", subordinates: ");
    result.append(subordinates);
    result.append(')');
    return result.toString();
  }

} //LinkTypeImpl
