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
 * $Id: BookOnTape.java,v 1.3 2008/05/04 17:03:17 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Book On Tape</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.BookOnTape#getReader <em>Reader</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.BookOnTape#getAuthor <em>Author</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.examples.extlibrary.EXTLibraryPackage#getBookOnTape()
 * @model
 * @generated
 */
public interface BookOnTape extends AudioVisualItem
{
  /**
   * Returns the value of the '<em><b>Reader</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reader</em>' reference.
   * @see #setReader(Person)
   * @see org.eclipse.emf.examples.extlibrary.EXTLibraryPackage#getBookOnTape_Reader()
   * @model
   * @generated
   */
  Person getReader();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.extlibrary.BookOnTape#getReader <em>Reader</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reader</em>' reference.
   * @see #getReader()
   * @generated
   */
  void setReader(Person value);

  /**
   * Returns the value of the '<em><b>Author</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Author</em>' reference.
   * @see #setAuthor(Writer)
   * @see org.eclipse.emf.examples.extlibrary.EXTLibraryPackage#getBookOnTape_Author()
   * @model
   * @generated
   */
  Writer getAuthor();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.extlibrary.BookOnTape#getAuthor <em>Author</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Author</em>' reference.
   * @see #getAuthor()
   * @generated
   */
  void setAuthor(Writer value);

} // BookOnTape
