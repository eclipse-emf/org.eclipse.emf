/**
 * <copyright>
 * </copyright>
 *
 * $Id: SupplierImpl.java,v 1.1 2004/08/20 22:47:32 marcelop Exp $
 */
package org.eclipse.emf.test.core.featuremap.supplier.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

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
  protected FeatureMap orders = null;

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
  protected EClass eStaticClass()
  {
    return SupplierPackage.eINSTANCE.getSupplier();
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
  public EList getPreferredOrders()
  {
    return ((FeatureMap)getOrders()).list(SupplierPackage.eINSTANCE.getSupplier_PreferredOrders());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getStandardOrders()
  {
    return ((FeatureMap)getOrders()).list(SupplierPackage.eINSTANCE.getSupplier_StandardOrders());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case SupplierPackage.SUPPLIER__ORDERS:
          return ((InternalEList)getOrders()).basicRemove(otherEnd, msgs);
        case SupplierPackage.SUPPLIER__PREFERRED_ORDERS:
          return ((InternalEList)getPreferredOrders()).basicRemove(otherEnd, msgs);
        case SupplierPackage.SUPPLIER__STANDARD_ORDERS:
          return ((InternalEList)getStandardOrders()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case SupplierPackage.SUPPLIER__NAME:
        return getName();
      case SupplierPackage.SUPPLIER__ORDERS:
        return getOrders();
      case SupplierPackage.SUPPLIER__PREFERRED_ORDERS:
        return getPreferredOrders();
      case SupplierPackage.SUPPLIER__STANDARD_ORDERS:
        return getStandardOrders();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case SupplierPackage.SUPPLIER__NAME:
        setName((String)newValue);
        return;
      case SupplierPackage.SUPPLIER__ORDERS:
        getOrders().clear();
        getOrders().addAll((Collection)newValue);
        return;
      case SupplierPackage.SUPPLIER__PREFERRED_ORDERS:
        getPreferredOrders().clear();
        getPreferredOrders().addAll((Collection)newValue);
        return;
      case SupplierPackage.SUPPLIER__STANDARD_ORDERS:
        getStandardOrders().clear();
        getStandardOrders().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
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
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
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
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
