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
 * A representation of the model object '<em><b>DU</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.DU#getCu <em>Cu</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.DU#getC4u <em>C4u</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.DU#getEu <em>Eu</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getDU()
 * @model
 * @generated
 */
public interface DU extends EObject
{
  /**
   * Returns the value of the '<em><b>Cu</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.CU#getDu <em>Du</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cu</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cu</em>' reference.
   * @see #isSetCu()
   * @see #unsetCu()
   * @see #setCu(CU)
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getDU_Cu()
   * @see org.eclipse.emf.test.models.ref.unsettable.CU#getDu
   * @model opposite="du" unsettable="true" required="true"
   * @generated
   */
  CU getCu();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getCu <em>Cu</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cu</em>' reference.
   * @see #isSetCu()
   * @see #unsetCu()
   * @see #getCu()
   * @generated
   */
  void setCu(CU value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getCu <em>Cu</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetCu()
   * @see #getCu()
   * @see #setCu(CU)
   * @generated
   */
  void unsetCu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getCu <em>Cu</em>}' reference is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Cu</em>' reference is set.
   * @see #unsetCu()
   * @see #getCu()
   * @see #setCu(CU)
   * @generated
   */
  boolean isSetCu();

  /**
   * Returns the value of the '<em><b>C4u</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.C4U#getDu <em>Du</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>C4u</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>C4u</em>' container reference.
   * @see #setC4u(C4U)
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getDU_C4u()
   * @see org.eclipse.emf.test.models.ref.unsettable.C4U#getDu
   * @model opposite="du" unsettable="true" required="true"
   * @generated
   */
  C4U getC4u();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getC4u <em>C4u</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C4u</em>' container reference.
   * @see #getC4u()
   * @generated
   */
  void setC4u(C4U value);

  /**
   * Returns the value of the '<em><b>Eu</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.unsettable.EU}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getDu <em>Du</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Eu</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Eu</em>' reference list.
   * @see #isSetEu()
   * @see #unsetEu()
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getDU_Eu()
   * @see org.eclipse.emf.test.models.ref.unsettable.EU#getDu
   * @model opposite="du" unsettable="true"
   * @generated
   */
  EList<EU> getEu();

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getEu <em>Eu</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetEu()
   * @see #getEu()
   * @generated
   */
  void unsetEu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getEu <em>Eu</em>}' reference list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Eu</em>' reference list is set.
   * @see #unsetEu()
   * @see #getEu()
   * @generated
   */
  boolean isSetEu();

} // DU
