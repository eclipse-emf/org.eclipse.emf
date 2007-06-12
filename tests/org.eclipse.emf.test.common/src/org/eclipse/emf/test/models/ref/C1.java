/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: C1.java,v 1.3 2007/06/12 15:08:10 emerks Exp $
 */
package org.eclipse.emf.test.models.ref;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>C1</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.C1#getA <em>A</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.C1#getB <em>B</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.RefPackage#getC1()
 * @model
 * @generated
 */
public interface C1 extends EObject
{
  /**
   * Returns the value of the '<em><b>A</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>A</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>A</em>' containment reference.
   * @see #setA(A)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getC1_A()
   * @model containment="true" required="true"
   * @generated
   */
  A getA();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.C1#getA <em>A</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>A</em>' containment reference.
   * @see #getA()
   * @generated
   */
  void setA(A value);

  /**
   * Returns the value of the '<em><b>B</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.B}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>B</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>B</em>' containment reference list.
   * @see org.eclipse.emf.test.models.ref.RefPackage#getC1_B()
   * @model containment="true"
   * @generated
   */
  EList<B> getB();

} // C1
