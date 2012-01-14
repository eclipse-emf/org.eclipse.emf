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
package org.eclipse.emf.test.models.ref.unsettable;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CU</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.CU#getDu <em>Du</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.CU#getC4u <em>C4u</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getCU()
 * @model
 * @generated
 */
public interface CU extends EObject
{
  /**
   * Returns the value of the '<em><b>Du</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.unsettable.DU}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getCu <em>Cu</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Du</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Du</em>' reference list.
   * @see #isSetDu()
   * @see #unsetDu()
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getCU_Du()
   * @see org.eclipse.emf.test.models.ref.unsettable.DU#getCu
   * @model opposite="cu" unsettable="true"
   * @generated
   */
  EList<DU> getDu();

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.CU#getDu <em>Du</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetDu()
   * @see #getDu()
   * @generated
   */
  void unsetDu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.CU#getDu <em>Du</em>}' reference list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Du</em>' reference list is set.
   * @see #unsetDu()
   * @see #getDu()
   * @generated
   */
  boolean isSetDu();

  /**
   * Returns the value of the '<em><b>C4u</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.C4U#getCu <em>Cu</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>C4u</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>C4u</em>' container reference.
   * @see #setC4u(C4U)
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getCU_C4u()
   * @see org.eclipse.emf.test.models.ref.unsettable.C4U#getCu
   * @model opposite="cu" unsettable="true" required="true"
   * @generated
   */
  C4U getC4u();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.CU#getC4u <em>C4u</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C4u</em>' container reference.
   * @see #getC4u()
   * @generated
   */
  void setC4u(C4U value);

} // CU
