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
 * $Id: C2U.java,v 1.3 2007/06/12 15:08:10 emerks Exp $
 */
package org.eclipse.emf.test.models.ref.unsettable;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>C2U</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getAu <em>Au</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getBu <em>Bu</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getC2U()
 * @model
 * @generated
 */
public interface C2U extends EObject
{
  /**
   * Returns the value of the '<em><b>Au</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getC2u <em>C2u</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Au</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Au</em>' containment reference.
   * @see #isSetAu()
   * @see #unsetAu()
   * @see #setAu(AU)
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getC2U_Au()
   * @see org.eclipse.emf.test.models.ref.unsettable.AU#getC2u
   * @model opposite="c2u" containment="true" unsettable="true" required="true"
   * @generated
   */
  AU getAu();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getAu <em>Au</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Au</em>' containment reference.
   * @see #isSetAu()
   * @see #unsetAu()
   * @see #getAu()
   * @generated
   */
  void setAu(AU value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getAu <em>Au</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAu()
   * @see #getAu()
   * @see #setAu(AU)
   * @generated
   */
  void unsetAu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getAu <em>Au</em>}' containment reference is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Au</em>' containment reference is set.
   * @see #unsetAu()
   * @see #getAu()
   * @see #setAu(AU)
   * @generated
   */
  boolean isSetAu();

  /**
   * Returns the value of the '<em><b>Bu</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.unsettable.BU}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getC2u <em>C2u</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bu</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bu</em>' containment reference list.
   * @see #isSetBu()
   * @see #unsetBu()
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getC2U_Bu()
   * @see org.eclipse.emf.test.models.ref.unsettable.BU#getC2u
   * @model opposite="c2u" containment="true" unsettable="true"
   * @generated
   */
  EList<BU> getBu();

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getBu <em>Bu</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetBu()
   * @see #getBu()
   * @generated
   */
  void unsetBu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getBu <em>Bu</em>}' containment reference list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Bu</em>' containment reference list is set.
   * @see #unsetBu()
   * @see #getBu()
   * @generated
   */
  boolean isSetBu();

} // C2U
