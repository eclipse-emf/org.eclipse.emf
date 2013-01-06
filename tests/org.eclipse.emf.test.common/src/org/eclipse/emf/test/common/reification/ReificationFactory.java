/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.common.reification.ReificationPackage
 * @generated
 */
public interface ReificationFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ReificationFactory eINSTANCE = org.eclipse.emf.test.common.reification.impl.ReificationFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Root</em>'.
   * @generated
   */
  Root createRoot();

  /**
   * Returns a new object of class '<em>Very Low</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Very Low</em>'.
   * @generated
   */
  VeryLow createVeryLow();

  /**
   * Returns a new object of class '<em>Low</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Low</em>'.
   * @generated
   */
  Low createLow();

  /**
   * Returns a new object of class '<em>Medium</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Medium</em>'.
   * @generated
   */
  Medium createMedium();

  /**
   * Returns a new object of class '<em>High</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>High</em>'.
   * @generated
   */
  High createHigh();

  /**
   * Returns a new object of class '<em>Very High</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Very High</em>'.
   * @generated
   */
  VeryHigh createVeryHigh();

  /**
   * Returns a new object of class '<em>Container</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Container</em>'.
   * @generated
   */
  Container createContainer();

  /**
   * Returns a new object of class '<em>Unbounded Generic Container</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Unbounded Generic Container</em>'.
   * @generated
   */
  <T> UnboundedGenericContainer<T> createUnboundedGenericContainer();

  /**
   * Returns a new object of class '<em>Raw Unbounded Container</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Raw Unbounded Container</em>'.
   * @generated
   */
  RawUnboundedContainer createRawUnboundedContainer();

  /**
   * Returns a new object of class '<em>Medium Unbounded Container</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Medium Unbounded Container</em>'.
   * @generated
   */
  MediumUnboundedContainer createMediumUnboundedContainer();

  /**
   * Returns a new object of class '<em>Bounded Generic Container</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Bounded Generic Container</em>'.
   * @generated
   */
  <T extends Medium> BoundedGenericContainer<T> createBoundedGenericContainer();

  /**
   * Returns a new object of class '<em>High Bounded Container</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>High Bounded Container</em>'.
   * @generated
   */
  HighBoundedContainer createHighBoundedContainer();

  /**
   * Returns a new object of class '<em>Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Key Value Pair</em>'.
   * @generated
   */
  <K extends EList<?>, V extends KeyValuePair<K, V>> KeyValuePair<K, V> createKeyValuePair();

  /**
   * Returns a new object of class '<em>Raw Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Raw Key Value Pair</em>'.
   * @generated
   */
  RawKeyValuePair createRawKeyValuePair();

  /**
   * Returns a new object of class '<em>String List Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String List Key Value Pair</em>'.
   * @generated
   */
  StringListKeyValuePair createStringListKeyValuePair();

  /**
   * Returns a new object of class '<em>TList Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>TList Key Value Pair</em>'.
   * @generated
   */
  <T> TListKeyValuePair<T> createTListKeyValuePair();

  /**
   * Returns a new object of class '<em>Bounded TList Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Bounded TList Key Value Pair</em>'.
   * @generated
   */
  <T extends Medium> BoundedTListKeyValuePair<T> createBoundedTListKeyValuePair();

  /**
   * Returns a new object of class '<em>High List Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>High List Key Value Pair</em>'.
   * @generated
   */
  HighListKeyValuePair createHighListKeyValuePair();

  /**
   * Returns a new object of class '<em>Integer List Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Integer List Key Value Pair</em>'.
   * @generated
   */
  IntegerListKeyValuePair createIntegerListKeyValuePair();

  /**
   * Returns a new object of class '<em>Key Value Pair Container</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Key Value Pair Container</em>'.
   * @generated
   */
  <K extends EList<?>, V extends KeyValuePair<K, V>> KeyValuePairContainer<K, V> createKeyValuePairContainer();

  /**
   * Returns a new object of class '<em>String List Key Value Pair Container</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String List Key Value Pair Container</em>'.
   * @generated
   */
  StringListKeyValuePairContainer createStringListKeyValuePairContainer();

  /**
   * Returns a new object of class '<em>Bidirectional</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Bidirectional</em>'.
   * @generated
   */
  <T extends Bidirectional<T>> Bidirectional<T> createBidirectional();

  /**
   * Returns a new object of class '<em>Link</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Link</em>'.
   * @generated
   */
  Link createLink();

  /**
   * Returns a new object of class '<em>Raw Link</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Raw Link</em>'.
   * @generated
   */
  RawLink createRawLink();

  /**
   * Returns a new object of class '<em>Link Item</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Link Item</em>'.
   * @generated
   */
  LinkItem createLinkItem();

  /**
   * Returns a new object of class '<em>Medium Bidirectional</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Medium Bidirectional</em>'.
   * @generated
   */
  <T extends MediumBidirectional<T> & Medium> MediumBidirectional<T> createMediumBidirectional();

  /**
   * Returns a new object of class '<em>Medium Link</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Medium Link</em>'.
   * @generated
   */
  MediumLink createMediumLink();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ReificationPackage getReificationPackage();

} //ReificationFactory
