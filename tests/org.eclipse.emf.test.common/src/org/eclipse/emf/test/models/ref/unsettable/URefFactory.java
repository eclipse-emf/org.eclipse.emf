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
package org.eclipse.emf.test.models.ref.unsettable;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage
 * @generated
 */
public interface URefFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  URefFactory eINSTANCE = org.eclipse.emf.test.models.ref.unsettable.impl.URefFactoryImpl.init();

  /**
   * Returns a new object of class '<em>C1U</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>C1U</em>'.
   * @generated
   */
  C1U createC1U();

  /**
   * Returns a new object of class '<em>C2U</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>C2U</em>'.
   * @generated
   */
  C2U createC2U();

  /**
   * Returns a new object of class '<em>AU</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>AU</em>'.
   * @generated
   */
  AU createAU();

  /**
   * Returns a new object of class '<em>BU</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>BU</em>'.
   * @generated
   */
  BU createBU();

  /**
   * Returns a new object of class '<em>CU</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>CU</em>'.
   * @generated
   */
  CU createCU();

  /**
   * Returns a new object of class '<em>DU</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>DU</em>'.
   * @generated
   */
  DU createDU();

  /**
   * Returns a new object of class '<em>C4U</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>C4U</em>'.
   * @generated
   */
  C4U createC4U();

  /**
   * Returns a new object of class '<em>C3U</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>C3U</em>'.
   * @generated
   */
  C3U createC3U();

  /**
   * Returns a new object of class '<em>EU</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EU</em>'.
   * @generated
   */
  EU createEU();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  URefPackage getURefPackage();

} //URefFactory
