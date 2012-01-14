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
 * A representation of the model object '<em><b>BU</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.BU#getAu <em>Au</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.BU#getC2u <em>C2u</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.BU#getDu <em>Du</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getBU()
 * @model
 * @generated
 */
public interface BU extends EObject
{
  /**
   * Returns the value of the '<em><b>Au</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getBu <em>Bu</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Au</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Au</em>' reference.
   * @see #isSetAu()
   * @see #unsetAu()
   * @see #setAu(AU)
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getBU_Au()
   * @see org.eclipse.emf.test.models.ref.unsettable.AU#getBu
   * @model opposite="bu" unsettable="true" required="true"
   * @generated
   */
  AU getAu();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getAu <em>Au</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Au</em>' reference.
   * @see #isSetAu()
   * @see #unsetAu()
   * @see #getAu()
   * @generated
   */
  void setAu(AU value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getAu <em>Au</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAu()
   * @see #getAu()
   * @see #setAu(AU)
   * @generated
   */
  void unsetAu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getAu <em>Au</em>}' reference is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Au</em>' reference is set.
   * @see #unsetAu()
   * @see #getAu()
   * @see #setAu(AU)
   * @generated
   */
  boolean isSetAu();

  /**
   * Returns the value of the '<em><b>C2u</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getBu <em>Bu</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>C2u</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>C2u</em>' container reference.
   * @see #setC2u(C2U)
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getBU_C2u()
   * @see org.eclipse.emf.test.models.ref.unsettable.C2U#getBu
   * @model opposite="bu" unsettable="true" required="true"
   * @generated
   */
  C2U getC2u();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getC2u <em>C2u</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C2u</em>' container reference.
   * @see #getC2u()
   * @generated
   */
  void setC2u(C2U value);

  /**
   * Returns the value of the '<em><b>Du</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.unsettable.DU}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Du</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Du</em>' reference list.
   * @see #isSetDu()
   * @see #unsetDu()
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getBU_Du()
   * @model unsettable="true"
   * @generated
   */
  EList<DU> getDu();

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getDu <em>Du</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetDu()
   * @see #getDu()
   * @generated
   */
  void unsetDu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getDu <em>Du</em>}' reference list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Du</em>' reference list is set.
   * @see #unsetDu()
   * @see #getDu()
   * @generated
   */
  boolean isSetDu();

} // BU
