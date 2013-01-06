/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.test.common.reification.ReificationPackage;
import org.eclipse.emf.test.common.reification.StringListKeyValuePair;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String List Key Value Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class StringListKeyValuePairImpl extends KeyValuePairImpl<EList<String>, StringListKeyValuePair> implements StringListKeyValuePair
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StringListKeyValuePairImpl()
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
    return ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * This is specialized for the more specific type known in this context.
   * @generated
   */
  @Override
  public NotificationChain basicSetValue(StringListKeyValuePair newValue, NotificationChain msgs)
  {
    return super.basicSetValue(newValue, msgs);
  }

} //StringListKeyValuePairImpl
