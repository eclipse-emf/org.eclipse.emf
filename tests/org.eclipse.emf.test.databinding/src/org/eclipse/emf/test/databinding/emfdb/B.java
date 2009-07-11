/**
 * <copyright>
 *
 * Copyright (c) 2009 BestSolution and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: B.java,v 1.1 2009/07/11 11:13:24 tschindl Exp $
 */
package org.eclipse.emf.test.databinding.emfdb;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>B</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.databinding.emfdb.B#getString <em>String</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.databinding.emfdb.EmfdbPackage#getB()
 * @model
 * @generated
 */
public interface B extends EObject
{
  /**
   * Returns the value of the '<em><b>String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>String</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>String</em>' attribute.
   * @see #setString(String)
   * @see org.eclipse.emf.test.databinding.emfdb.EmfdbPackage#getB_String()
   * @model
   * @generated
   */
  String getString();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.databinding.emfdb.B#getString <em>String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>String</em>' attribute.
   * @see #getString()
   * @generated
   */
  void setString(String value);

} // B
