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
 * $Id: B.java,v 1.3 2007/06/12 15:08:10 emerks Exp $
 */
package org.eclipse.emf.test.models.ref;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>B</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.B#getA <em>A</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.B#getC2 <em>C2</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.B#getD <em>D</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.RefPackage#getB()
 * @model
 * @generated
 */
public interface B extends EObject
{
  /**
   * Returns the value of the '<em><b>A</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.A#getB <em>B</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>A</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>A</em>' reference.
   * @see #setA(A)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getB_A()
   * @see org.eclipse.emf.test.models.ref.A#getB
   * @model opposite="b" required="true"
   * @generated
   */
  A getA();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.B#getA <em>A</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>A</em>' reference.
   * @see #getA()
   * @generated
   */
  void setA(A value);

  /**
   * Returns the value of the '<em><b>C2</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.C2#getB <em>B</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>C2</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>C2</em>' container reference.
   * @see #setC2(C2)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getB_C2()
   * @see org.eclipse.emf.test.models.ref.C2#getB
   * @model opposite="b" required="true"
   * @generated
   */
  C2 getC2();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.B#getC2 <em>C2</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C2</em>' container reference.
   * @see #getC2()
   * @generated
   */
  void setC2(C2 value);

  /**
   * Returns the value of the '<em><b>D</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.D}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>D</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>D</em>' reference list.
   * @see org.eclipse.emf.test.models.ref.RefPackage#getB_D()
   * @model
   * @generated
   */
  EList<D> getD();

} // B
