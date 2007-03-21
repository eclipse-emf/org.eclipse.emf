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
 * $Id: SupplierImpl.java,v 1.3 2007/03/21 18:10:17 marcelop Exp $
 */
package com.example.sdo.epo.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;
import org.eclipse.emf.ecore.sdo.util.BasicESequence;
import org.eclipse.emf.ecore.sdo.util.ESequence;

import com.example.sdo.epo.EPOPackage;
import com.example.sdo.epo.PurchaseOrder;
import com.example.sdo.epo.Supplier;

import commonj.sdo.Sequence;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Supplier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.example.sdo.epo.impl.SupplierImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.example.sdo.epo.impl.SupplierImpl#getOrders <em>Orders</em>}</li>
 *   <li>{@link com.example.sdo.epo.impl.SupplierImpl#getPriorityOrders <em>Priority Orders</em>}</li>
 *   <li>{@link com.example.sdo.epo.impl.SupplierImpl#getStandardOrders <em>Standard Orders</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SupplierImpl extends EDataObjectImpl implements Supplier {
	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
	protected static final String NAME_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
	protected String name = NAME_EDEFAULT;

	/**
   * The cached value of the '{@link #getOrders() <em>Orders</em>}' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOrders()
   * @generated
   * @ordered
   */
	protected ESequence orders;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SupplierImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  protected EClass eStaticClass() {
    return EPOPackage.Literals.SUPPLIER;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String getName() {
    return name;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setName(String newName) {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EPOPackage.SUPPLIER__NAME, oldName, name));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Sequence getOrders() {
    if (orders == null)
    {
      orders = new BasicESequence(new BasicFeatureMap(this, EPOPackage.SUPPLIER__ORDERS));
    }
    return orders;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public List<PurchaseOrder> getPriorityOrders() {
    return ((FeatureMap.Internal.Wrapper)getOrders()).featureMap().list(EPOPackage.Literals.SUPPLIER__PRIORITY_ORDERS);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public List<PurchaseOrder> getStandardOrders() {
    return ((FeatureMap.Internal.Wrapper)getOrders()).featureMap().list(EPOPackage.Literals.SUPPLIER__STANDARD_ORDERS);
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
      case EPOPackage.SUPPLIER__ORDERS:
        return ((InternalEList<?>)((FeatureMap.Internal.Wrapper)getOrders()).featureMap()).basicRemove(otherEnd, msgs);
      case EPOPackage.SUPPLIER__PRIORITY_ORDERS:
        return ((InternalEList<?>)getPriorityOrders()).basicRemove(otherEnd, msgs);
      case EPOPackage.SUPPLIER__STANDARD_ORDERS:
        return ((InternalEList<?>)getStandardOrders()).basicRemove(otherEnd, msgs);
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
      case EPOPackage.SUPPLIER__NAME:
        return getName();
      case EPOPackage.SUPPLIER__ORDERS:
        if (coreType) return ((FeatureMap.Internal.Wrapper)getOrders()).featureMap();
        return getOrders();
      case EPOPackage.SUPPLIER__PRIORITY_ORDERS:
        return getPriorityOrders();
      case EPOPackage.SUPPLIER__STANDARD_ORDERS:
        return getStandardOrders();
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
      case EPOPackage.SUPPLIER__NAME:
        setName((String)newValue);
        return;
      case EPOPackage.SUPPLIER__ORDERS:
        ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getOrders()).featureMap()).set(newValue);
        return;
      case EPOPackage.SUPPLIER__PRIORITY_ORDERS:
        getPriorityOrders().clear();
        getPriorityOrders().addAll((Collection<? extends PurchaseOrder>)newValue);
        return;
      case EPOPackage.SUPPLIER__STANDARD_ORDERS:
        getStandardOrders().clear();
        getStandardOrders().addAll((Collection<? extends PurchaseOrder>)newValue);
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
      case EPOPackage.SUPPLIER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EPOPackage.SUPPLIER__ORDERS:
        ((FeatureMap.Internal.Wrapper)getOrders()).featureMap().clear();
        return;
      case EPOPackage.SUPPLIER__PRIORITY_ORDERS:
        getPriorityOrders().clear();
        return;
      case EPOPackage.SUPPLIER__STANDARD_ORDERS:
        getStandardOrders().clear();
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
      case EPOPackage.SUPPLIER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EPOPackage.SUPPLIER__ORDERS:
        return orders != null && !orders.featureMap().isEmpty();
      case EPOPackage.SUPPLIER__PRIORITY_ORDERS:
        return !getPriorityOrders().isEmpty();
      case EPOPackage.SUPPLIER__STANDARD_ORDERS:
        return !getStandardOrders().isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", orders: ");
    result.append(orders);
    result.append(')');
    return result.toString();
  }

} //SupplierImpl
