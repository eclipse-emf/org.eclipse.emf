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
 * A representation of the model object '<em><b>Key Value Pair</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.common.reification.KeyValuePair#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.KeyValuePair#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.common.reification.ReificationPackage#getKeyValuePair()
 * @model KBounds="org.eclipse.emf.ecore.EEList<?>"
 * @generated
 */
public interface KeyValuePair<K extends EList<?>, V extends KeyValuePair<K, V>> extends EObject
{
  /**
   * Returns the value of the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Key</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Key</em>' attribute.
   * @see #setKey(EList)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getKeyValuePair_Key()
   * @model many="false" transient="true"
   * @generated
   */
  K getKey();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.KeyValuePair#getKey <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Key</em>' attribute.
   * @see #getKey()
   * @generated
   */
  void setKey(K value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(KeyValuePair)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getKeyValuePair_Value()
   * @model containment="true"
   * @generated
   */
  V getValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.KeyValuePair#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(V value);

} // KeyValuePair
