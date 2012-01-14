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
package org.eclipse.emf.java;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JParameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.JParameter#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JParameter#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JParameter#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.java.JavaPackage#getJParameter()
 * @model
 * @generated
 */
public interface JParameter extends JModelElement
{
  /**
   * Returns the value of the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Final</em>' attribute.
   * @see #setFinal(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJParameter_Final()
   * @model
   * @generated
   */
  boolean isFinal();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JParameter#isFinal <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Final</em>' attribute.
   * @see #isFinal()
   * @generated
   */
  void setFinal(boolean value);

  /**
   * Returns the value of the '<em><b>Method</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.java.JMethod#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Method</em>' container reference.
   * @see #setMethod(JMethod)
   * @see org.eclipse.emf.java.JavaPackage#getJParameter_Method()
   * @see org.eclipse.emf.java.JMethod#getParameters
   * @model opposite="parameters"
   * @generated
   */
  JMethod getMethod();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JParameter#getMethod <em>Method</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Method</em>' container reference.
   * @see #getMethod()
   * @generated
   */
  void setMethod(JMethod value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(JClass)
   * @see org.eclipse.emf.java.JavaPackage#getJParameter_Type()
   * @model
   * @generated
   */
  JClass getType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JParameter#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(JClass value);

} // JParameter
