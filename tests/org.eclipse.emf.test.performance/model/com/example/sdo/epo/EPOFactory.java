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
 * $Id: EPOFactory.java,v 1.2 2006/12/30 03:43:52 marcelop Exp $
 */
package com.example.sdo.epo;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.example.sdo.epo.EPOPackage
 * @generated
 */
public interface EPOFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	EPOFactory eINSTANCE = com.example.sdo.epo.impl.EPOFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Item</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Item</em>'.
   * @generated
   */
	Item createItem();

	/**
   * Returns a new object of class '<em>US Address</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>US Address</em>'.
   * @generated
   */
	USAddress createUSAddress();

	/**
   * Returns a new object of class '<em>Purchase Order</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Purchase Order</em>'.
   * @generated
   */
	PurchaseOrder createPurchaseOrder();

	/**
   * Returns a new object of class '<em>Supplier</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Supplier</em>'.
   * @generated
   */
	Supplier createSupplier();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	EPOPackage getEPOPackage();

} //EPOFactory
