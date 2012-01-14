/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.ecore2xml;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage
 * @generated
 */
public interface Ecore2XMLFactory extends EFactory{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Ecore2XMLFactory eINSTANCE = org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLFactoryImpl.init();

  /**
   * Returns a new object of class '<em>XML Info</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XML Info</em>'.
   * @generated
   */
  XMLInfo createXMLInfo();

  /**
   * Returns a new object of class '<em>XML Map</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XML Map</em>'.
   * @generated
   */
  XMLMap createXMLMap();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  Ecore2XMLPackage getEcore2XMLPackage();

} //Ecore2XMLFactory
