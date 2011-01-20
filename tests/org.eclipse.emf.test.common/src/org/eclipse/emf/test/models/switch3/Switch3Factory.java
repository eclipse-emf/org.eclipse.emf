/**
 * <copyright>
 *
 * Copyright (c) 2009 TIBCO Software Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Adrian Price
 *
 * </copyright>
 *
 * $Id: Switch3Factory.java,v 1.1 2011/01/20 01:10:22 emerks Exp $
 */
package org.eclipse.emf.test.models.switch3;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.switch3.Switch3Package
 * @generated
 */
public interface Switch3Factory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Switch3Factory eINSTANCE = org.eclipse.emf.test.models.switch3.impl.Switch3FactoryImpl.init();

  /**
   * Returns a new object of class '<em>EClass4</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EClass4</em>'.
   * @generated
   */
  EClass4 createEClass4();

  /**
   * Returns a new object of class '<em>EClass5</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EClass5</em>'.
   * @generated
   */
  EClass5 createEClass5();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  Switch3Package getSwitch3Package();

} //Switch3Factory
