/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: Writer.java,v 1.3 2007/06/12 15:07:36 emerks Exp $
 */
package com.example.sdo.library;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Writer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.example.sdo.library.Writer#getName <em>Name</em>}</li>
 *   <li>{@link com.example.sdo.library.Writer#getBooks <em>Books</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.example.sdo.library.LibraryPackage#getWriter()
 * @model
 * @generated
 */
public interface Writer
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
   * @see com.example.sdo.library.LibraryPackage#getWriter_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.example.sdo.library.Writer#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Books</b></em>' reference list.
   * The list contents are of type {@link com.example.sdo.library.Book}.
   * It is bidirectional and its opposite is '{@link com.example.sdo.library.Book#getAuthor <em>Author</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Books</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Books</em>' reference list.
   * @see com.example.sdo.library.LibraryPackage#getWriter_Books()
   * @see com.example.sdo.library.Book#getAuthor
   * @model opposite="author"
   * @generated
   */
  List<Book> getBooks();

} // Writer
