/**
 * <copyright>
 *
 * Copyright (c) 2009 TIBCO Software Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Adrian Price
 *
 * </copyright>
 *
 * $Id: Switch2Package.java,v 1.1 2011/01/20 01:10:22 emerks Exp $
 */
package org.eclipse.emf.test.models.switch2;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.test.models.switch1.Switch1Package;

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
 * @see org.eclipse.emf.test.models.switch2.Switch2Factory
 * @model kind="package"
 * @generated
 */
public interface Switch2Package extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "switch2";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org.eclipse.emf.test.models/switch2";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "s2";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Switch2Package eINSTANCE = org.eclipse.emf.test.models.switch2.impl.Switch2PackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.switch2.impl.EClass2Impl <em>EClass2</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.switch2.impl.EClass2Impl
   * @see org.eclipse.emf.test.models.switch2.impl.Switch2PackageImpl#getEClass2()
   * @generated
   */
  int ECLASS2 = 0;

  /**
   * The feature id for the '<em><b>EAttribute0</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS2__EATTRIBUTE0 = Switch1Package.ECLASS1__EATTRIBUTE0;

  /**
   * The feature id for the '<em><b>EAttribute1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS2__EATTRIBUTE1 = Switch1Package.ECLASS1__EATTRIBUTE1;

  /**
   * The feature id for the '<em><b>EAttribute2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS2__EATTRIBUTE2 = Switch1Package.ECLASS1__EATTRIBUTE2;

  /**
   * The feature id for the '<em><b>EAttribute3</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS2__EATTRIBUTE3 = Switch1Package.ECLASS1__EATTRIBUTE3;

  /**
   * The feature id for the '<em><b>EAttribute4</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS2__EATTRIBUTE4 = Switch1Package.ECLASS1_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>EAttribute5</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS2__EATTRIBUTE5 = Switch1Package.ECLASS1_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>EClass2</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS2_FEATURE_COUNT = Switch1Package.ECLASS1_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.switch2.impl.EClass3Impl <em>EClass3</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.switch2.impl.EClass3Impl
   * @see org.eclipse.emf.test.models.switch2.impl.Switch2PackageImpl#getEClass3()
   * @generated
   */
  int ECLASS3 = 1;

  /**
   * The feature id for the '<em><b>EAttribute0</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS3__EATTRIBUTE0 = ECLASS2__EATTRIBUTE0;

  /**
   * The feature id for the '<em><b>EAttribute1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS3__EATTRIBUTE1 = ECLASS2__EATTRIBUTE1;

  /**
   * The feature id for the '<em><b>EAttribute2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS3__EATTRIBUTE2 = ECLASS2__EATTRIBUTE2;

  /**
   * The feature id for the '<em><b>EAttribute3</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS3__EATTRIBUTE3 = ECLASS2__EATTRIBUTE3;

  /**
   * The feature id for the '<em><b>EAttribute4</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS3__EATTRIBUTE4 = ECLASS2__EATTRIBUTE4;

  /**
   * The feature id for the '<em><b>EAttribute5</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS3__EATTRIBUTE5 = ECLASS2__EATTRIBUTE5;

  /**
   * The feature id for the '<em><b>EAttribute6</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS3__EATTRIBUTE6 = ECLASS2_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>EAttribute7</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS3__EATTRIBUTE7 = ECLASS2_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>EClass3</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS3_FEATURE_COUNT = ECLASS2_FEATURE_COUNT + 2;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.switch2.EClass2 <em>EClass2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EClass2</em>'.
   * @see org.eclipse.emf.test.models.switch2.EClass2
   * @generated
   */
  EClass getEClass2();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.switch2.EClass2#getEAttribute4 <em>EAttribute4</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>EAttribute4</em>'.
   * @see org.eclipse.emf.test.models.switch2.EClass2#getEAttribute4()
   * @see #getEClass2()
   * @generated
   */
  EAttribute getEClass2_EAttribute4();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.switch2.EClass2#getEAttribute5 <em>EAttribute5</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>EAttribute5</em>'.
   * @see org.eclipse.emf.test.models.switch2.EClass2#getEAttribute5()
   * @see #getEClass2()
   * @generated
   */
  EAttribute getEClass2_EAttribute5();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.switch2.EClass3 <em>EClass3</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EClass3</em>'.
   * @see org.eclipse.emf.test.models.switch2.EClass3
   * @generated
   */
  EClass getEClass3();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.switch2.EClass3#getEAttribute6 <em>EAttribute6</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>EAttribute6</em>'.
   * @see org.eclipse.emf.test.models.switch2.EClass3#getEAttribute6()
   * @see #getEClass3()
   * @generated
   */
  EAttribute getEClass3_EAttribute6();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.switch2.EClass3#getEAttribute7 <em>EAttribute7</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>EAttribute7</em>'.
   * @see org.eclipse.emf.test.models.switch2.EClass3#getEAttribute7()
   * @see #getEClass3()
   * @generated
   */
  EAttribute getEClass3_EAttribute7();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  Switch2Factory getSwitch2Factory();

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
     * The meta object literal for the '{@link org.eclipse.emf.test.models.switch2.impl.EClass2Impl <em>EClass2</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.switch2.impl.EClass2Impl
     * @see org.eclipse.emf.test.models.switch2.impl.Switch2PackageImpl#getEClass2()
     * @generated
     */
    EClass ECLASS2 = eINSTANCE.getEClass2();

    /**
     * The meta object literal for the '<em><b>EAttribute4</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECLASS2__EATTRIBUTE4 = eINSTANCE.getEClass2_EAttribute4();

    /**
     * The meta object literal for the '<em><b>EAttribute5</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECLASS2__EATTRIBUTE5 = eINSTANCE.getEClass2_EAttribute5();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.switch2.impl.EClass3Impl <em>EClass3</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.switch2.impl.EClass3Impl
     * @see org.eclipse.emf.test.models.switch2.impl.Switch2PackageImpl#getEClass3()
     * @generated
     */
    EClass ECLASS3 = eINSTANCE.getEClass3();

    /**
     * The meta object literal for the '<em><b>EAttribute6</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECLASS3__EATTRIBUTE6 = eINSTANCE.getEClass3_EAttribute6();

    /**
     * The meta object literal for the '<em><b>EAttribute7</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECLASS3__EATTRIBUTE7 = eINSTANCE.getEClass3_EAttribute7();

  }

} //Switch2Package
