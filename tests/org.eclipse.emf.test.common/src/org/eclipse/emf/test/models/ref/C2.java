/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.ref;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>C2</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.C2#getB <em>B</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.C2#getA <em>A</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.RefPackage#getC2()
 * @model
 * @generated
 */
public interface C2 extends EObject
{
  /**
   * Returns the value of the '<em><b>B</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.B}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.B#getC2 <em>C2</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>B</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>B</em>' containment reference list.
   * @see org.eclipse.emf.test.models.ref.RefPackage#getC2_B()
   * @see org.eclipse.emf.test.models.ref.B#getC2
   * @model opposite="c2" containment="true"
   * @generated
   */
  EList<B> getB();

  /**
   * Returns the value of the '<em><b>A</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.A#getC2 <em>C2</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>A</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>A</em>' containment reference.
   * @see #setA(A)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getC2_A()
   * @see org.eclipse.emf.test.models.ref.A#getC2
   * @model opposite="c2" containment="true" required="true"
   * @generated
   */
  A getA();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.C2#getA <em>A</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>A</em>' containment reference.
   * @see #getA()
   * @generated
   */
  void setA(A value);

} // C2
