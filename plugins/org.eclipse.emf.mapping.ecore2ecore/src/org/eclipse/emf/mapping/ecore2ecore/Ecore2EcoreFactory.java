/**
 * Copyright (c) 2004-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.ecore2ecore;


import org.eclipse.emf.ecore.EFactory;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.mapping.ecore2ecore.Ecore2EcorePackage
 * @generated
 */
public interface Ecore2EcoreFactory extends EFactory{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Ecore2EcoreFactory eINSTANCE = org.eclipse.emf.mapping.ecore2ecore.impl.Ecore2EcoreFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Mapping Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mapping Root</em>'.
   * @generated
   */
  Ecore2EcoreMappingRoot createEcore2EcoreMappingRoot();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  Ecore2EcorePackage getEcore2EcorePackage();

} //Ecore2EcoreFactory
