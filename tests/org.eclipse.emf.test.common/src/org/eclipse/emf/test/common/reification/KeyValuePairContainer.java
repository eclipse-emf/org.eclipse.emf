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
 * A representation of the model object '<em><b>Key Value Pair Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.common.reification.KeyValuePairContainer#getKeyValuePairsList <em>Key Value Pairs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.common.reification.ReificationPackage#getKeyValuePairContainer()
 * @model KBounds="org.eclipse.emf.ecore.EEList<?>"
 * @generated
 */
public interface KeyValuePairContainer<K extends EList<?>, V extends KeyValuePair<K, V>> extends EObject
{
  /**
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Key Value Pairs</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @generated
   */
  KeyValuePair<K, V>[] getKeyValuePairs();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  KeyValuePair<K, V> getKeyValuePairs(int index);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  int getKeyValuePairsLength();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  void setKeyValuePairs(KeyValuePair<K, V>[] newKeyValuePairs);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  void setKeyValuePairs(int index, KeyValuePair<K, V> element);

  /**
   * Returns the value of the '<em><b>Key Value Pairs</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.common.reification.KeyValuePair}&lt;K, V>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Key Value Pairs</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Key Value Pairs</em>' reference list.
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getKeyValuePairContainer_KeyValuePairs()
   * @model
   * @generated
   */
  EList<KeyValuePair<K, V>> getKeyValuePairsList();

} // KeyValuePairContainer
