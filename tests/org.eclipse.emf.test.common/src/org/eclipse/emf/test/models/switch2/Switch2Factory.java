/**
 * Copyright (c) 2009 TIBCO Software Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Adrian Price
 */
package org.eclipse.emf.test.models.switch2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.switch2.Switch2Package
 * @generated
 */
public interface Switch2Factory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Switch2Factory eINSTANCE = org.eclipse.emf.test.models.switch2.impl.Switch2FactoryImpl.init();

  /**
   * Returns a new object of class '<em>EClass2</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EClass2</em>'.
   * @generated
   */
  EClass2 createEClass2();

  /**
   * Returns a new object of class '<em>EClass3</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EClass3</em>'.
   * @generated
   */
  EClass3 createEClass3();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  Switch2Package getSwitch2Package();

} //Switch2Factory
