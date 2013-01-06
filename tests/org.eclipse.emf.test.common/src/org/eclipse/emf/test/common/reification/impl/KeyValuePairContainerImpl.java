/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.test.common.reification.KeyValuePair;
import org.eclipse.emf.test.common.reification.KeyValuePairContainer;
import org.eclipse.emf.test.common.reification.ReificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Key Value Pair Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.KeyValuePairContainerImpl#getKeyValuePairsList <em>Key Value Pairs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KeyValuePairContainerImpl<K extends EList<?>, V extends KeyValuePair<K, V>> extends EObjectImpl implements KeyValuePairContainer<K, V>
{
  /**
   * The cached value of the '{@link #getKeyValuePairsList() <em>Key Value Pairs</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKeyValuePairsList()
   * @generated
   * @ordered
   */
  protected EList<KeyValuePair<K, V>> keyValuePairs;

  /**
   * The empty value for the '{@link #getKeyValuePairs() <em>Key Value Pairs</em>}' array accessor.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKeyValuePairs()
   * @generated
   * @ordered
   */
  @SuppressWarnings("rawtypes")
  protected static final KeyValuePair[] KEY_VALUE_PAIRS_EEMPTY_ARRAY = new KeyValuePair [0];

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected KeyValuePairContainerImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public KeyValuePair<K, V>[] getKeyValuePairs()
  {
    if (keyValuePairs == null || keyValuePairs.isEmpty()) return KEY_VALUE_PAIRS_EEMPTY_ARRAY;
    BasicEList<KeyValuePair<K, V>> list = (BasicEList<KeyValuePair<K, V>>)keyValuePairs;
    list.shrink();
    return (KeyValuePair<K, V>[])list.data();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KeyValuePair<K, V> getKeyValuePairs(int index)
  {
    return getKeyValuePairsList().get(index);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getKeyValuePairsLength()
  {
    return keyValuePairs == null ? 0 : keyValuePairs.size();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKeyValuePairs(KeyValuePair<K, V>[] newKeyValuePairs)
  {
    ((BasicEList<KeyValuePair<K, V>>)getKeyValuePairsList()).setData(newKeyValuePairs.length, newKeyValuePairs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKeyValuePairs(int index, KeyValuePair<K, V> element)
  {
    getKeyValuePairsList().set(index, element);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<KeyValuePair<K, V>> getKeyValuePairsList()
  {
    if (keyValuePairs == null)
    {
      keyValuePairs = new EObjectResolvingEList<KeyValuePair<K, V>>(KeyValuePair.class, this, ReificationPackage.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS);
    }
    return keyValuePairs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ReificationPackage.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS:
        return getKeyValuePairsList();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ReificationPackage.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS:
        getKeyValuePairsList().clear();
        getKeyValuePairsList().addAll((Collection<? extends KeyValuePair<K, V>>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ReificationPackage.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS:
        getKeyValuePairsList().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ReificationPackage.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS:
        return keyValuePairs != null && !keyValuePairs.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //KeyValuePairContainerImpl
