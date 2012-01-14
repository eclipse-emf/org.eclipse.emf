/**
 * Copyright (c) 2009 TIBCO Software Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Adrian Price
 */
package org.eclipse.emf.test.models.switch1;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see org.eclipse.emf.test.models.switch1.Switch1Factory
 * @model kind="package"
 * @generated
 */
public interface Switch1Package extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "switch1";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org.eclipse.emf.test.models/switch1";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "s1";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Switch1Package eINSTANCE = org.eclipse.emf.test.models.switch1.impl.Switch1PackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.switch1.impl.EClass0Impl <em>EClass0</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.switch1.impl.EClass0Impl
   * @see org.eclipse.emf.test.models.switch1.impl.Switch1PackageImpl#getEClass0()
   * @generated
   */
  int ECLASS0 = 0;

  /**
   * The feature id for the '<em><b>EAttribute0</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS0__EATTRIBUTE0 = 0;

  /**
   * The feature id for the '<em><b>EAttribute1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS0__EATTRIBUTE1 = 1;

  /**
   * The number of structural features of the '<em>EClass0</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS0_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.switch1.impl.EClass1Impl <em>EClass1</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.switch1.impl.EClass1Impl
   * @see org.eclipse.emf.test.models.switch1.impl.Switch1PackageImpl#getEClass1()
   * @generated
   */
  int ECLASS1 = 1;

  /**
   * The feature id for the '<em><b>EAttribute0</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS1__EATTRIBUTE0 = ECLASS0__EATTRIBUTE0;

  /**
   * The feature id for the '<em><b>EAttribute1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS1__EATTRIBUTE1 = ECLASS0__EATTRIBUTE1;

  /**
   * The feature id for the '<em><b>EAttribute2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS1__EATTRIBUTE2 = ECLASS0_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>EAttribute3</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS1__EATTRIBUTE3 = ECLASS0_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>EClass1</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECLASS1_FEATURE_COUNT = ECLASS0_FEATURE_COUNT + 2;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.switch1.EClass0 <em>EClass0</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EClass0</em>'.
   * @see org.eclipse.emf.test.models.switch1.EClass0
   * @generated
   */
  EClass getEClass0();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.switch1.EClass0#getEAttribute0 <em>EAttribute0</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>EAttribute0</em>'.
   * @see org.eclipse.emf.test.models.switch1.EClass0#getEAttribute0()
   * @see #getEClass0()
   * @generated
   */
  EAttribute getEClass0_EAttribute0();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.switch1.EClass0#getEAttribute1 <em>EAttribute1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>EAttribute1</em>'.
   * @see org.eclipse.emf.test.models.switch1.EClass0#getEAttribute1()
   * @see #getEClass0()
   * @generated
   */
  EAttribute getEClass0_EAttribute1();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.switch1.EClass1 <em>EClass1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EClass1</em>'.
   * @see org.eclipse.emf.test.models.switch1.EClass1
   * @generated
   */
  EClass getEClass1();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.switch1.EClass1#getEAttribute2 <em>EAttribute2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>EAttribute2</em>'.
   * @see org.eclipse.emf.test.models.switch1.EClass1#getEAttribute2()
   * @see #getEClass1()
   * @generated
   */
  EAttribute getEClass1_EAttribute2();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.switch1.EClass1#getEAttribute3 <em>EAttribute3</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>EAttribute3</em>'.
   * @see org.eclipse.emf.test.models.switch1.EClass1#getEAttribute3()
   * @see #getEClass1()
   * @generated
   */
  EAttribute getEClass1_EAttribute3();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  Switch1Factory getSwitch1Factory();

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
     * The meta object literal for the '{@link org.eclipse.emf.test.models.switch1.impl.EClass0Impl <em>EClass0</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.switch1.impl.EClass0Impl
     * @see org.eclipse.emf.test.models.switch1.impl.Switch1PackageImpl#getEClass0()
     * @generated
     */
    EClass ECLASS0 = eINSTANCE.getEClass0();

    /**
     * The meta object literal for the '<em><b>EAttribute0</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECLASS0__EATTRIBUTE0 = eINSTANCE.getEClass0_EAttribute0();

    /**
     * The meta object literal for the '<em><b>EAttribute1</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECLASS0__EATTRIBUTE1 = eINSTANCE.getEClass0_EAttribute1();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.switch1.impl.EClass1Impl <em>EClass1</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.switch1.impl.EClass1Impl
     * @see org.eclipse.emf.test.models.switch1.impl.Switch1PackageImpl#getEClass1()
     * @generated
     */
    EClass ECLASS1 = eINSTANCE.getEClass1();

    /**
     * The meta object literal for the '<em><b>EAttribute2</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECLASS1__EATTRIBUTE2 = eINSTANCE.getEClass1_EAttribute2();

    /**
     * The meta object literal for the '<em><b>EAttribute3</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECLASS1__EATTRIBUTE3 = eINSTANCE.getEClass1_EAttribute3();

  }

} //Switch1Package
