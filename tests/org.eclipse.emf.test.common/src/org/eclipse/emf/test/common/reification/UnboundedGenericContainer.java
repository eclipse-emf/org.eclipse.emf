/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unbounded Generic Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.common.reification.UnboundedGenericContainer#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.common.reification.ReificationPackage#getUnboundedGenericContainer()
 * @model
 * @generated
 */
public interface UnboundedGenericContainer<T> extends EObject
{
  /**
   * Returns the value of the '<em><b>Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Content</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Content</em>' containment reference.
   * @see #setContent(Object)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getUnboundedGenericContainer_Content()
   * @model kind="reference" containment="true"
   * @generated
   */
  T getContent();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.UnboundedGenericContainer#getContent <em>Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Content</em>' containment reference.
   * @see #getContent()
   * @generated
   */
  void setContent(T value);

} // UnboundedGenericContainer
