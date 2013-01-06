/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.test.common.reification.ReificationPackage;
import org.eclipse.emf.test.common.reification.StringListKeyValuePair;
import org.eclipse.emf.test.common.reification.StringListKeyValuePairContainer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String List Key Value Pair Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class StringListKeyValuePairContainerImpl extends KeyValuePairContainerImpl<EList<String>, StringListKeyValuePair> implements StringListKeyValuePairContainer
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StringListKeyValuePairContainerImpl()
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
    return ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR_CONTAINER;
  }

} //StringListKeyValuePairContainerImpl
