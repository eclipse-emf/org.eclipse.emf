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
 * $Id: Periodical.java,v 1.2 2008/05/04 17:03:18 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Periodical</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.Periodical#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.Periodical#getIssuesPerYear <em>Issues Per Year</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.examples.extlibrary.EXTLibraryPackage#getPeriodical()
 * @model abstract="true"
 * @generated
 */
public interface Periodical extends Item
{
  /**
   * Returns the value of the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Title</em>' attribute.
   * @see #setTitle(String)
   * @see org.eclipse.emf.examples.extlibrary.EXTLibraryPackage#getPeriodical_Title()
   * @model
   * @generated
   */
  String getTitle();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.extlibrary.Periodical#getTitle <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Title</em>' attribute.
   * @see #getTitle()
   * @generated
   */
  void setTitle(String value);

  /**
   * Returns the value of the '<em><b>Issues Per Year</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Issues Per Year</em>' attribute.
   * @see #setIssuesPerYear(int)
   * @see org.eclipse.emf.examples.extlibrary.EXTLibraryPackage#getPeriodical_IssuesPerYear()
   * @model required="true"
   * @generated
   */
  int getIssuesPerYear();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.extlibrary.Periodical#getIssuesPerYear <em>Issues Per Year</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Issues Per Year</em>' attribute.
   * @see #getIssuesPerYear()
   * @generated
   */
  void setIssuesPerYear(int value);

} // Periodical
