/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: JField.java,v 1.4 2008/05/04 17:03:37 emerks Exp $
 */
package org.eclipse.emf.java;


import java.lang.reflect.Field;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JField</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.JField#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JField#isTransient <em>Transient</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JField#isVolatile <em>Volatile</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JField#getJavaField <em>Java Field</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JField#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JField#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.java.JavaPackage#getJField()
 * @model
 * @generated
 */
public interface JField extends JMember
{
  /**
   * Returns the value of the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Final</em>' attribute.
   * @see #setFinal(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJField_Final()
   * @model
   * @generated
   */
  boolean isFinal();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JField#isFinal <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Final</em>' attribute.
   * @see #isFinal()
   * @generated
   */
  void setFinal(boolean value);

  /**
   * Returns the value of the '<em><b>Transient</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transient</em>' attribute.
   * @see #setTransient(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJField_Transient()
   * @model
   * @generated
   */
  boolean isTransient();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JField#isTransient <em>Transient</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Transient</em>' attribute.
   * @see #isTransient()
   * @generated
   */
  void setTransient(boolean value);

  /**
   * Returns the value of the '<em><b>Volatile</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Volatile</em>' attribute.
   * @see #setVolatile(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJField_Volatile()
   * @model
   * @generated
   */
  boolean isVolatile();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JField#isVolatile <em>Volatile</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Volatile</em>' attribute.
   * @see #isVolatile()
   * @generated
   */
  void setVolatile(boolean value);

  /**
   * Returns the value of the '<em><b>Java Field</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Java Field</em>' attribute.
   * @see #setJavaField(Field)
   * @see org.eclipse.emf.java.JavaPackage#getJField_JavaField()
   * @model dataType="org.eclipse.emf.java.JavaField" transient="true"
   * @generated
   */
  Field getJavaField();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JField#getJavaField <em>Java Field</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Java Field</em>' attribute.
   * @see #getJavaField()
   * @generated
   */
  void setJavaField(Field value);

  /**
   * Returns the value of the '<em><b>Initializer</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initializer</em>' attribute.
   * @see #setInitializer(String)
   * @see org.eclipse.emf.java.JavaPackage#getJField_Initializer()
   * @model
   * @generated
   */
  String getInitializer();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JField#getInitializer <em>Initializer</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Initializer</em>' attribute.
   * @see #getInitializer()
   * @generated
   */
  void setInitializer(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(JClass)
   * @see org.eclipse.emf.java.JavaPackage#getJField_Type()
   * @model
   * @generated
   */
  JClass getType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JField#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(JClass value);

} // JField
