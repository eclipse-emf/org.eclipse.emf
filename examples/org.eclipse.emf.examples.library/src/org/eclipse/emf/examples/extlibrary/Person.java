/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: Person.java,v 1.2 2008/05/04 17:03:18 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.Person#getFirstName <em>First Name</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.Person#getLastName <em>Last Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.examples.extlibrary.EXTLibraryPackage#getPerson()
 * @model
 * @generated
 */
public interface Person extends Addressable
{
  /**
   * Returns the value of the '<em><b>First Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>First Name</em>' attribute.
   * @see #setFirstName(String)
   * @see org.eclipse.emf.examples.extlibrary.EXTLibraryPackage#getPerson_FirstName()
   * @model required="true"
   * @generated
   */
  String getFirstName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.extlibrary.Person#getFirstName <em>First Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>First Name</em>' attribute.
   * @see #getFirstName()
   * @generated
   */
  void setFirstName(String value);

  /**
   * Returns the value of the '<em><b>Last Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Last Name</em>' attribute.
   * @see #setLastName(String)
   * @see org.eclipse.emf.examples.extlibrary.EXTLibraryPackage#getPerson_LastName()
   * @model required="true"
   * @generated
   */
  String getLastName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.extlibrary.Person#getLastName <em>Last Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Last Name</em>' attribute.
   * @see #getLastName()
   * @generated
   */
  void setLastName(String value);

} // Person
