/**
 * Copyright (c) 2006-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.featuremap.supplier.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.core.featuremap.supplier.PurchaseOrder;
import org.eclipse.emf.test.core.featuremap.supplier.Supplier;
import org.eclipse.emf.test.core.featuremap.supplier.SupplierPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Supplier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.featuremap.supplier.impl.SupplierImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.featuremap.supplier.impl.SupplierImpl#getOrders <em>Orders</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.featuremap.supplier.impl.SupplierImpl#getPreferredOrders <em>Preferred Orders</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.featuremap.supplier.impl.SupplierImpl#getStandardOrders <em>Standard Orders</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SupplierImpl extends EObjectImpl implements Supplier
{
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
  protected FeatureMap orders;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SupplierImpl()
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
    return SupplierPackage.Literals.SUPPLIER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SupplierPackage.SUPPLIER__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getOrders()
  {
    if (orders == null)
    {
      orders = new BasicFeatureMap(this, SupplierPackage.SUPPLIER__ORDERS);
    }
    return orders;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PurchaseOrder> getPreferredOrders()
  {
    return getOrders().list(SupplierPackage.Literals.SUPPLIER__PREFERRED_ORDERS);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PurchaseOrder> getStandardOrders()
  {
    return getOrders().list(SupplierPackage.Literals.SUPPLIER__STANDARD_ORDERS);
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
      case SupplierPackage.SUPPLIER__ORDERS:
        return ((InternalEList<?>)getOrders()).basicRemove(otherEnd, msgs);
      case SupplierPackage.SUPPLIER__PREFERRED_ORDERS:
        return ((InternalEList<?>)getPreferredOrders()).basicRemove(otherEnd, msgs);
      case SupplierPackage.SUPPLIER__STANDARD_ORDERS:
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
      case SupplierPackage.SUPPLIER__NAME:
        return getName();
      case SupplierPackage.SUPPLIER__ORDERS:
        if (coreType) return getOrders();
        return ((FeatureMap.Internal)getOrders()).getWrapper();
      case SupplierPackage.SUPPLIER__PREFERRED_ORDERS:
        return getPreferredOrders();
      case SupplierPackage.SUPPLIER__STANDARD_ORDERS:
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
      case SupplierPackage.SUPPLIER__NAME:
        setName((String)newValue);
        return;
      case SupplierPackage.SUPPLIER__ORDERS:
        ((FeatureMap.Internal)getOrders()).set(newValue);
        return;
      case SupplierPackage.SUPPLIER__PREFERRED_ORDERS:
        getPreferredOrders().clear();
        getPreferredOrders().addAll((Collection<? extends PurchaseOrder>)newValue);
        return;
      case SupplierPackage.SUPPLIER__STANDARD_ORDERS:
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
      case SupplierPackage.SUPPLIER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case SupplierPackage.SUPPLIER__ORDERS:
        getOrders().clear();
        return;
      case SupplierPackage.SUPPLIER__PREFERRED_ORDERS:
        getPreferredOrders().clear();
        return;
      case SupplierPackage.SUPPLIER__STANDARD_ORDERS:
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
      case SupplierPackage.SUPPLIER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case SupplierPackage.SUPPLIER__ORDERS:
        return orders != null && !orders.isEmpty();
      case SupplierPackage.SUPPLIER__PREFERRED_ORDERS:
        return !getPreferredOrders().isEmpty();
      case SupplierPackage.SUPPLIER__STANDARD_ORDERS:
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
  public String toString()
  {
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
