/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bidirectional</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.common.reification.Bidirectional#getIn <em>In</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Bidirectional#getOut <em>Out</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Bidirectional#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Bidirectional#getValuesList <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.common.reification.ReificationPackage#getBidirectional()
 * @model
 * @generated
 */
public interface Bidirectional<T extends Bidirectional<T>> extends EObject
{
  /**
   * Returns the value of the '<em><b>In</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.common.reification.Bidirectional#getOut <em>Out</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>In</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>In</em>' reference.
   * @see #setIn(Bidirectional)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getBidirectional_In()
   * @see org.eclipse.emf.test.common.reification.Bidirectional#getOut
   * @model opposite="out"
   * @generated
   */
  T getIn();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Bidirectional#getIn <em>In</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>In</em>' reference.
   * @see #getIn()
   * @generated
   */
  void setIn(T value);

  /**
   * Returns the value of the '<em><b>Out</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.common.reification.Bidirectional#getIn <em>In</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Out</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Out</em>' reference.
   * @see #setOut(Bidirectional)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getBidirectional_Out()
   * @see org.eclipse.emf.test.common.reification.Bidirectional#getIn
   * @model opposite="in"
   * @generated
   */
  T getOut();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Bidirectional#getOut <em>Out</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Out</em>' reference.
   * @see #getOut()
   * @generated
   */
  void setOut(T value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' reference.
   * @see #setValue(Bidirectional)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getBidirectional_Value()
   * @model
   * @generated
   */
  T getValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Bidirectional#getValue <em>Value</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' reference.
   * @see #getValue()
   * @generated
   */
  void setValue(T value);

  /**
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Values</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @generated
   */
  Bidirectional<T>[] getValues();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  T getValues(int index);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  int getValuesLength();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  void setValues(T[] newValues);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  void setValues(int index, T element);

  /**
   * Returns the value of the '<em><b>Values</b></em>' reference list.
   * The list contents are of type {@link T}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Values</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Values</em>' reference list.
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getBidirectional_Values()
   * @model
   * @generated
   */
  EList<T> getValuesList();

} // Bidirectional
