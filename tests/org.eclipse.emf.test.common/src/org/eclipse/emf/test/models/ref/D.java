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
 * A representation of the model object '<em><b>D</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.D#getC <em>C</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.D#getE <em>E</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.D#getC4 <em>C4</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.RefPackage#getD()
 * @model
 * @generated
 */
public interface D extends EObject
{
  /**
   * Returns the value of the '<em><b>C</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.C#getD <em>D</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>C</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>C</em>' reference.
   * @see #setC(C)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getD_C()
   * @see org.eclipse.emf.test.models.ref.C#getD
   * @model opposite="d" required="true"
   * @generated
   */
  C getC();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.D#getC <em>C</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C</em>' reference.
   * @see #getC()
   * @generated
   */
  void setC(C value);

  /**
   * Returns the value of the '<em><b>E</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.E}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.E#getD <em>D</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>E</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>E</em>' reference list.
   * @see org.eclipse.emf.test.models.ref.RefPackage#getD_E()
   * @see org.eclipse.emf.test.models.ref.E#getD
   * @model opposite="d"
   * @generated
   */
  EList<E> getE();

  /**
   * Returns the value of the '<em><b>C4</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.C4#getD <em>D</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>C4</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>C4</em>' container reference.
   * @see #setC4(C4)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getD_C4()
   * @see org.eclipse.emf.test.models.ref.C4#getD
   * @model opposite="d" required="true"
   * @generated
   */
  C4 getC4();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.D#getC4 <em>C4</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C4</em>' container reference.
   * @see #getC4()
   * @generated
   */
  void setC4(C4 value);

} // D
