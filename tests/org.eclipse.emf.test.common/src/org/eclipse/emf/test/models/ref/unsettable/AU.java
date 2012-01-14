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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AU</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.AU#getBu <em>Bu</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.AU#getC2u <em>C2u</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.AU#getCu <em>Cu</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getAU()
 * @model
 * @generated
 */
public interface AU extends EObject
{
  /**
   * Returns the value of the '<em><b>Bu</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getAu <em>Au</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bu</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bu</em>' reference.
   * @see #isSetBu()
   * @see #unsetBu()
   * @see #setBu(BU)
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getAU_Bu()
   * @see org.eclipse.emf.test.models.ref.unsettable.BU#getAu
   * @model opposite="au" unsettable="true" required="true"
   * @generated
   */
  BU getBu();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getBu <em>Bu</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bu</em>' reference.
   * @see #isSetBu()
   * @see #unsetBu()
   * @see #getBu()
   * @generated
   */
  void setBu(BU value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getBu <em>Bu</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetBu()
   * @see #getBu()
   * @see #setBu(BU)
   * @generated
   */
  void unsetBu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getBu <em>Bu</em>}' reference is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Bu</em>' reference is set.
   * @see #unsetBu()
   * @see #getBu()
   * @see #setBu(BU)
   * @generated
   */
  boolean isSetBu();

  /**
   * Returns the value of the '<em><b>C2u</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getAu <em>Au</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>C2u</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>C2u</em>' container reference.
   * @see #setC2u(C2U)
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getAU_C2u()
   * @see org.eclipse.emf.test.models.ref.unsettable.C2U#getAu
   * @model opposite="au" unsettable="true" required="true"
   * @generated
   */
  C2U getC2u();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getC2u <em>C2u</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C2u</em>' container reference.
   * @see #getC2u()
   * @generated
   */
  void setC2u(C2U value);

  /**
   * Returns the value of the '<em><b>Cu</b></em>' reference.
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
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getAU_Cu()
   * @model unsettable="true" required="true"
   * @generated
   */
  CU getCu();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getCu <em>Cu</em>}' reference.
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
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getCu <em>Cu</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetCu()
   * @see #getCu()
   * @see #setCu(CU)
   * @generated
   */
  void unsetCu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getCu <em>Cu</em>}' reference is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Cu</em>' reference is set.
   * @see #unsetCu()
   * @see #getCu()
   * @see #setCu(CU)
   * @generated
   */
  boolean isSetCu();

} // AU
