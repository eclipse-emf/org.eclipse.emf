/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenModelFactory.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import org.eclipse.emf.ecore.EFactory;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
 * @generated
 */
public interface GenModelFactory extends EFactory {
  /**
   * The singleton instance of the factory.
   * @generated
   */
  GenModelFactory eINSTANCE = new org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelFactoryImpl();

  /**
   * Returns a new object of class '<em>GenModel</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>GenModel</em>'.
   * @generated
   */
  GenModel createGenModel();

  /**
   * Returns a new object of class '<em>Gen Package</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Gen Package</em>'.
   * @generated
   */
  GenPackage createGenPackage();

  /**
   * Returns a new object of class '<em>Gen Class</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Gen Class</em>'.
   * @generated
   */
  GenClass createGenClass();

  /**
   * Returns a new object of class '<em>Gen Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Gen Feature</em>'.
   * @generated
   */
  GenFeature createGenFeature();

  /**
   * Returns a new object of class '<em>Gen Enum</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Gen Enum</em>'.
   * @generated
   */
  GenEnum createGenEnum();

  /**
   * Returns a new object of class '<em>Gen Enum Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Gen Enum Literal</em>'.
   * @generated
   */
  GenEnumLiteral createGenEnumLiteral();

  /**
   * Returns a new object of class '<em>Gen Data Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Gen Data Type</em>'.
   * @generated
   */
  GenDataType createGenDataType();

  /**
   * Returns a new object of class '<em>Gen Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Gen Operation</em>'.
   * @generated
   */
  GenOperation createGenOperation();

  /**
   * Returns a new object of class '<em>Gen Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Gen Parameter</em>'.
   * @generated
   */
  GenParameter createGenParameter();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  GenModelPackage getGenModelPackage();

} //GenModelFactory
