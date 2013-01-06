/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.test.common.reification.BoundedTListKeyValuePair;
import org.eclipse.emf.test.common.reification.Medium;
import org.eclipse.emf.test.common.reification.ReificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bounded TList Key Value Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class BoundedTListKeyValuePairImpl<T extends Medium> extends TListKeyValuePairImpl<T> implements BoundedTListKeyValuePair<T>
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BoundedTListKeyValuePairImpl()
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
    return ReificationPackage.Literals.BOUNDED_TLIST_KEY_VALUE_PAIR;
  }

} //BoundedTListKeyValuePairImpl
