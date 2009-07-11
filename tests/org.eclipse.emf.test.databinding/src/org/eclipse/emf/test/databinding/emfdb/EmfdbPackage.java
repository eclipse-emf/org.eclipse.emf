/**
 * <copyright>
 *
 * Copyright (c) 2009 BestSolution and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EmfdbPackage.java,v 1.1 2009/07/11 11:13:24 tschindl Exp $
 */
package org.eclipse.emf.test.databinding.emfdb;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.databinding.emfdb.EmfdbFactory
 * @model kind="package"
 * @generated
 */
public interface EmfdbPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "emfdb";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/emfdb/1.0.0";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "emfdb";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EmfdbPackage eINSTANCE = org.eclipse.emf.test.databinding.emfdb.impl.EmfdbPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.databinding.emfdb.impl.AImpl <em>A</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.databinding.emfdb.impl.AImpl
   * @see org.eclipse.emf.test.databinding.emfdb.impl.EmfdbPackageImpl#getA()
   * @generated
   */
  int A = 0;

  /**
   * The feature id for the '<em><b>String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__STRING = 0;

  /**
   * The feature id for the '<em><b>Blist</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__BLIST = 1;

  /**
   * The number of structural features of the '<em>A</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.databinding.emfdb.impl.BImpl <em>B</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.databinding.emfdb.impl.BImpl
   * @see org.eclipse.emf.test.databinding.emfdb.impl.EmfdbPackageImpl#getB()
   * @generated
   */
  int B = 1;

  /**
   * The feature id for the '<em><b>String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int B__STRING = 0;

  /**
   * The number of structural features of the '<em>B</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int B_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.databinding.emfdb.A <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>A</em>'.
   * @see org.eclipse.emf.test.databinding.emfdb.A
   * @generated
   */
  EClass getA();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.databinding.emfdb.A#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>String</em>'.
   * @see org.eclipse.emf.test.databinding.emfdb.A#getString()
   * @see #getA()
   * @generated
   */
  EAttribute getA_String();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.databinding.emfdb.A#getBlist <em>Blist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blist</em>'.
   * @see org.eclipse.emf.test.databinding.emfdb.A#getBlist()
   * @see #getA()
   * @generated
   */
  EReference getA_Blist();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.databinding.emfdb.B <em>B</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>B</em>'.
   * @see org.eclipse.emf.test.databinding.emfdb.B
   * @generated
   */
  EClass getB();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.databinding.emfdb.B#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>String</em>'.
   * @see org.eclipse.emf.test.databinding.emfdb.B#getString()
   * @see #getB()
   * @generated
   */
  EAttribute getB_String();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  EmfdbFactory getEmfdbFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.databinding.emfdb.impl.AImpl <em>A</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.databinding.emfdb.impl.AImpl
     * @see org.eclipse.emf.test.databinding.emfdb.impl.EmfdbPackageImpl#getA()
     * @generated
     */
    EClass A = eINSTANCE.getA();

    /**
     * The meta object literal for the '<em><b>String</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute A__STRING = eINSTANCE.getA_String();

    /**
     * The meta object literal for the '<em><b>Blist</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference A__BLIST = eINSTANCE.getA_Blist();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.databinding.emfdb.impl.BImpl <em>B</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.databinding.emfdb.impl.BImpl
     * @see org.eclipse.emf.test.databinding.emfdb.impl.EmfdbPackageImpl#getB()
     * @generated
     */
    EClass B = eINSTANCE.getB();

    /**
     * The meta object literal for the '<em><b>String</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute B__STRING = eINSTANCE.getB_String();

  }

} //EmfdbPackage
