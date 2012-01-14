/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping;


import org.eclipse.emf.ecore.EFactory;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.mapping.MappingPackage
 * @generated
 */
public interface MappingFactory extends EFactory{
  /**
   * The singleton instance of the factory.
   * @generated
   */
  MappingFactory eINSTANCE = org.eclipse.emf.mapping.impl.MappingFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Helper</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Helper</em>'.
   * @generated
   */
  MappingHelper createMappingHelper();

  /**
   * Returns a new object of class '<em>Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mapping</em>'.
   * @generated
   */
  Mapping createMapping();

  /**
   * Returns a new object of class '<em>Type Converter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Converter</em>'.
   * @generated
   */
  TypeConverter createTypeConverter();

  /**
   * Returns a new object of class '<em>Function Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Function Pair</em>'.
   * @generated
   */
  FunctionPair createFunctionPair();

  /**
   * Returns a new object of class '<em>Function Name Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Function Name Pair</em>'.
   * @generated
   */
  FunctionNamePair createFunctionNamePair();

  /**
   * Returns a new object of class '<em>Strategy</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Strategy</em>'.
   * @generated
   */
  MappingStrategy createMappingStrategy();

  /**
   * Returns a new object of class '<em>Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Root</em>'.
   * @generated
   */
  MappingRoot createMappingRoot();

  /**
   * Returns a new object of class '<em>Complex Type Converter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Complex Type Converter</em>'.
   * @generated
   */
  ComplexTypeConverter createComplexTypeConverter();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  MappingPackage getMappingPackage();

} //MappingFactory

