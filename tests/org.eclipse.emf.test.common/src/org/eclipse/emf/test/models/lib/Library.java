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
 * $Id: Library.java,v 1.3 2007/06/12 15:08:11 emerks Exp $
 */
package org.eclipse.emf.test.models.lib;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.lib.Library#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.lib.Library#getBooks <em>Books</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.lib.Library#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.lib.Library#getWriters <em>Writers</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.lib.Library#getCafeteria <em>Cafeteria</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.lib.LibPackage#getLibrary()
 * @model
 * @generated
 */
public interface Library extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.emf.test.models.lib.LibPackage#getLibrary_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.lib.Library#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Books</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.lib.Book}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Books</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Books</em>' containment reference list.
   * @see org.eclipse.emf.test.models.lib.LibPackage#getLibrary_Books()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  EList<Book> getBooks();

  /**
   * Returns the value of the '<em><b>Address</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Address</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Address</em>' containment reference.
   * @see #setAddress(Address)
   * @see org.eclipse.emf.test.models.lib.LibPackage#getLibrary_Address()
   * @model containment="true" resolveProxies="true" required="true"
   * @generated
   */
  Address getAddress();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.lib.Library#getAddress <em>Address</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Address</em>' containment reference.
   * @see #getAddress()
   * @generated
   */
  void setAddress(Address value);

  /**
   * Returns the value of the '<em><b>Writers</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.lib.Person}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.lib.Person#getLibrary <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Writers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Writers</em>' containment reference list.
   * @see org.eclipse.emf.test.models.lib.LibPackage#getLibrary_Writers()
   * @see org.eclipse.emf.test.models.lib.Person#getLibrary
   * @model opposite="library" containment="true" resolveProxies="true"
   * @generated
   */
  EList<Person> getWriters();

  /**
   * Returns the value of the '<em><b>Cafeteria</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.lib.Cafeteria#getLibrary <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cafeteria</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cafeteria</em>' containment reference.
   * @see #setCafeteria(Cafeteria)
   * @see org.eclipse.emf.test.models.lib.LibPackage#getLibrary_Cafeteria()
   * @see org.eclipse.emf.test.models.lib.Cafeteria#getLibrary
   * @model opposite="library" containment="true" resolveProxies="true"
   * @generated
   */
  Cafeteria getCafeteria();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.lib.Library#getCafeteria <em>Cafeteria</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cafeteria</em>' containment reference.
   * @see #getCafeteria()
   * @generated
   */
  void setCafeteria(Cafeteria value);

} // Library
