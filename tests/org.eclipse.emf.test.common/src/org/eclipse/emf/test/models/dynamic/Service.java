/**
 * Copyright (c) 2022 Eclipse Contributor and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.models.dynamic;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.dynamic.Service#getDetails <em>Details</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.test.models.dynamic.DynamicPackage#getService()
 * @model
 * @generated
 */
public interface Service extends EObject
{
  /**
   * Returns the value of the '<em><b>Details</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.dynamic.Detail}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Details</em>' containment reference list.
   * @see org.eclipse.emf.test.models.dynamic.DynamicPackage#getService_Details()
   * @model containment="true"
   * @generated
   */
  EList<Detail> getDetails();

} // Service
