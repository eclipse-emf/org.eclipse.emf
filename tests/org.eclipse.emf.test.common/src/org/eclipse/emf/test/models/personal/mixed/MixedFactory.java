/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: MixedFactory.java,v 1.2 2007/01/18 22:06:39 marcelop Exp $
 */
package org.eclipse.emf.test.models.personal.mixed;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @generated
 */
public interface MixedFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MixedFactory INSTANCE = org.eclipse.emf.test.models.personal.mixed.impl.MixedFactoryImpl.eINSTANCE;

  /**
   * Returns a new object of class '<em>Document Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Document Root</em>'.
   * @generated
   */
  DocumentRoot createDocumentRoot();

  /**
   * Returns a new object of class '<em>Link Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Link Type</em>'.
   * @generated
   */
  LinkType createLinkType();

  /**
   * Returns a new object of class '<em>Name Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Name Type</em>'.
   * @generated
   */
  NameType createNameType();

  /**
   * Returns a new object of class '<em>Personnel Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Personnel Type</em>'.
   * @generated
   */
  PersonnelType createPersonnelType();

  /**
   * Returns a new object of class '<em>Person Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Person Type</em>'.
   * @generated
   */
  PersonType createPersonType();

  /**
   * Returns a new object of class '<em>Url Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Url Type</em>'.
   * @generated
   */
  UrlType createUrlType();

} //MixedFactory
