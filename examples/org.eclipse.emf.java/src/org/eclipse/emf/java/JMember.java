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
 * A representation of the model object '<em><b>JMember</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.JMember#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMember#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMember#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMember#getContainingType <em>Containing Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.java.JavaPackage#getJMember()
 * @model abstract="true"
 * @generated
 */
public interface JMember extends JModelElement
{
  /**
   * Returns the value of the '<em><b>Static</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Static</em>' attribute.
   * @see #setStatic(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJMember_Static()
   * @model
   * @generated
   */
  boolean isStatic();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMember#isStatic <em>Static</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Static</em>' attribute.
   * @see #isStatic()
   * @generated
   */
  void setStatic(boolean value);

  /**
   * Returns the value of the '<em><b>Visibility</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.java.JVisibility}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Visibility</em>' attribute.
   * @see org.eclipse.emf.java.JVisibility
   * @see #setVisibility(JVisibility)
   * @see org.eclipse.emf.java.JavaPackage#getJMember_Visibility()
   * @model
   * @generated
   */
  JVisibility getVisibility();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMember#getVisibility <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visibility</em>' attribute.
   * @see org.eclipse.emf.java.JVisibility
   * @see #getVisibility()
   * @generated
   */
  void setVisibility(JVisibility value);

  /**
   * Returns the value of the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comment</em>' attribute.
   * @see #setComment(String)
   * @see org.eclipse.emf.java.JavaPackage#getJMember_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMember#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Containing Type</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.java.JClass#getMembers <em>Members</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Containing Type</em>' container reference.
   * @see #setContainingType(JClass)
   * @see org.eclipse.emf.java.JavaPackage#getJMember_ContainingType()
   * @see org.eclipse.emf.java.JClass#getMembers
   * @model opposite="members"
   * @generated
   */
  JClass getContainingType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMember#getContainingType <em>Containing Type</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Containing Type</em>' container reference.
   * @see #getContainingType()
   * @generated
   */
  void setContainingType(JClass value);

} // JMember
