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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>A</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.A#getB <em>B</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.A#getC2 <em>C2</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.A#getC <em>C</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.RefPackage#getA()
 * @model
 * @generated
 */
public interface A extends EObject
{
  /**
   * Returns the value of the '<em><b>B</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.B#getA <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>B</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>B</em>' reference.
   * @see #setB(B)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getA_B()
   * @see org.eclipse.emf.test.models.ref.B#getA
   * @model opposite="a" required="true"
   * @generated
   */
  B getB();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.A#getB <em>B</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>B</em>' reference.
   * @see #getB()
   * @generated
   */
  void setB(B value);

  /**
   * Returns the value of the '<em><b>C2</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.C2#getA <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>C2</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>C2</em>' container reference.
   * @see #setC2(C2)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getA_C2()
   * @see org.eclipse.emf.test.models.ref.C2#getA
   * @model opposite="a" required="true"
   * @generated
   */
  C2 getC2();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.A#getC2 <em>C2</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C2</em>' container reference.
   * @see #getC2()
   * @generated
   */
  void setC2(C2 value);

  /**
   * Returns the value of the '<em><b>C</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>C</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>C</em>' reference.
   * @see #setC(C)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getA_C()
   * @model required="true"
   * @generated
   */
  C getC();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.A#getC <em>C</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C</em>' reference.
   * @see #getC()
   * @generated
   */
  void setC(C value);

} // A
