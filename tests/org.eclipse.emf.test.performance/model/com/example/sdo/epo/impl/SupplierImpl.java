/**
 * <copyright>
 * </copyright>
 *
 * $Id: SupplierImpl.java,v 1.1.2.1 2005/05/30 19:26:59 nickb Exp $
 */
package com.example.sdo.epo.impl;

import com.example.sdo.epo.EPOPackage;
import com.example.sdo.epo.Supplier;

import commonj.sdo.Sequence;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.ecore.sdo.util.BasicESequence;
import org.eclipse.emf.ecore.sdo.util.ESequence;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

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
	protected ESequence orders = null;

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
	protected EClass eStaticClass() {
		return EPOPackage.eINSTANCE.getSupplier();
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
		if (orders == null) {
			orders = new BasicESequence(new BasicFeatureMap(this, EPOPackage.SUPPLIER__ORDERS));
		}
		return orders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPriorityOrders() {
		return ((ESequence)getOrders()).featureMap().list(EPOPackage.eINSTANCE.getSupplier_PriorityOrders());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getStandardOrders() {
		return ((ESequence)getOrders()).featureMap().list(EPOPackage.eINSTANCE.getSupplier_StandardOrders());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EPOPackage.SUPPLIER__ORDERS:
				return ((InternalEList)((ESequence)getOrders()).featureMap()).basicRemove(otherEnd, msgs);
				case EPOPackage.SUPPLIER__PRIORITY_ORDERS:
					return ((InternalEList)getPriorityOrders()).basicRemove(otherEnd, msgs);
				case EPOPackage.SUPPLIER__STANDARD_ORDERS:
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EPOPackage.SUPPLIER__NAME:
				return getName();
			case EPOPackage.SUPPLIER__ORDERS:
				return ((ESequence)getOrders()).featureMap();
			case EPOPackage.SUPPLIER__PRIORITY_ORDERS:
				return getPriorityOrders();
			case EPOPackage.SUPPLIER__STANDARD_ORDERS:
				return getStandardOrders();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EPOPackage.SUPPLIER__NAME:
				setName((String)newValue);
				return;
			case EPOPackage.SUPPLIER__ORDERS:
				((ESequence)getOrders()).featureMap().clear();
				((ESequence)getOrders()).featureMap().addAll((Collection)newValue);
				return;
			case EPOPackage.SUPPLIER__PRIORITY_ORDERS:
				getPriorityOrders().clear();
				getPriorityOrders().addAll((Collection)newValue);
				return;
			case EPOPackage.SUPPLIER__STANDARD_ORDERS:
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
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EPOPackage.SUPPLIER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EPOPackage.SUPPLIER__ORDERS:
				((ESequence)getOrders()).featureMap().clear();
				return;
			case EPOPackage.SUPPLIER__PRIORITY_ORDERS:
				getPriorityOrders().clear();
				return;
			case EPOPackage.SUPPLIER__STANDARD_ORDERS:
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
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EPOPackage.SUPPLIER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EPOPackage.SUPPLIER__ORDERS:
				return orders != null && !orders.featureMap().isEmpty();
			case EPOPackage.SUPPLIER__PRIORITY_ORDERS:
				return !getPriorityOrders().isEmpty();
			case EPOPackage.SUPPLIER__STANDARD_ORDERS:
				return !getStandardOrders().isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
