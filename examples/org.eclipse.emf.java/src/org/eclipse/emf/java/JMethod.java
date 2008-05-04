/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: JMethod.java,v 1.7 2008/05/04 17:03:37 emerks Exp $
 */
package org.eclipse.emf.java;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JMethod</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.JMethod#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMethod#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMethod#isNative <em>Native</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMethod#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMethod#getJavaMethod <em>Java Method</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMethod#isConstructor <em>Constructor</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMethod#getJavaConstructor <em>Java Constructor</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMethod#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMethod#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMethod#getExceptions <em>Exceptions</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JMethod#getReturnType <em>Return Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.java.JavaPackage#getJMethod()
 * @model
 * @generated
 */
public interface JMethod extends JMember
{
  /**
   * Returns the value of the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Abstract</em>' attribute.
   * @see #setAbstract(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_Abstract()
   * @model
   * @generated
   */
  boolean isAbstract();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMethod#isAbstract <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Abstract</em>' attribute.
   * @see #isAbstract()
   * @generated
   */
  void setAbstract(boolean value);

  /**
   * Returns the value of the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Final</em>' attribute.
   * @see #setFinal(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_Final()
   * @model
   * @generated
   */
  boolean isFinal();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMethod#isFinal <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Final</em>' attribute.
   * @see #isFinal()
   * @generated
   */
  void setFinal(boolean value);

  /**
   * Returns the value of the '<em><b>Native</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Native</em>' attribute.
   * @see #setNative(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_Native()
   * @model
   * @generated
   */
  boolean isNative();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMethod#isNative <em>Native</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Native</em>' attribute.
   * @see #isNative()
   * @generated
   */
  void setNative(boolean value);

  /**
   * Returns the value of the '<em><b>Synchronized</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Synchronized</em>' attribute.
   * @see #setSynchronized(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_Synchronized()
   * @model
   * @generated
   */
  boolean isSynchronized();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMethod#isSynchronized <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Synchronized</em>' attribute.
   * @see #isSynchronized()
   * @generated
   */
  void setSynchronized(boolean value);

  /**
   * Returns the value of the '<em><b>Java Method</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Java Method</em>' attribute.
   * @see #setJavaMethod(Method)
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_JavaMethod()
   * @model dataType="org.eclipse.emf.java.JavaMethod" transient="true"
   * @generated
   */
  Method getJavaMethod();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMethod#getJavaMethod <em>Java Method</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Java Method</em>' attribute.
   * @see #getJavaMethod()
   * @generated
   */
  void setJavaMethod(Method value);

  /**
   * Returns the value of the '<em><b>Constructor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constructor</em>' attribute.
   * @see #setConstructor(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_Constructor()
   * @model
   * @generated
   */
  boolean isConstructor();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMethod#isConstructor <em>Constructor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constructor</em>' attribute.
   * @see #isConstructor()
   * @generated
   */
  void setConstructor(boolean value);

  /**
   * Returns the value of the '<em><b>Java Constructor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Java Constructor</em>' attribute.
   * @see #setJavaConstructor(Constructor)
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_JavaConstructor()
   * @model dataType="org.eclipse.emf.java.JavaConstructor<?>" transient="true"
   * @generated
   */
  Constructor<?> getJavaConstructor();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMethod#getJavaConstructor <em>Java Constructor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Java Constructor</em>' attribute.
   * @see #getJavaConstructor()
   * @generated
   */
  void setJavaConstructor(Constructor<?> value);

  /**
   * Returns the value of the '<em><b>Body</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' attribute.
   * @see #setBody(String)
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_Body()
   * @model
   * @generated
   */
  String getBody();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMethod#getBody <em>Body</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' attribute.
   * @see #getBody()
   * @generated
   */
  void setBody(String value);

  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JParameter}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.java.JParameter#getMethod <em>Method</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_Parameters()
   * @see org.eclipse.emf.java.JParameter#getMethod
   * @model opposite="method" containment="true"
   * @generated
   */
  EList<JParameter> getParameters();

  /**
   * Returns the value of the '<em><b>Exceptions</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JClass}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exceptions</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_Exceptions()
   * @model
   * @generated
   */
  EList<JClass> getExceptions();

  /**
   * Returns the value of the '<em><b>Return Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Return Type</em>' reference.
   * @see #setReturnType(JClass)
   * @see org.eclipse.emf.java.JavaPackage#getJMethod_ReturnType()
   * @model
   * @generated
   */
  JClass getReturnType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JMethod#getReturnType <em>Return Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Return Type</em>' reference.
   * @see #getReturnType()
   * @generated
   */
  void setReturnType(JClass value);

} // JMethod
