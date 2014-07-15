/**
 * Copyright (c) 2014 CEA and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA - Initial API and implementation
 *
 */
package org.eclipse.emf.test.core.xrefsmodel;

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
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.core.xrefsmodel.XRefsModelFactory
 * @model kind="package"
 * @generated
 */
public interface XRefsModelPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "xrefsmodel";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/EMF/2014/test/xrefsmodel";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "xrefs";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  XRefsModelPackage eINSTANCE = org.eclipse.emf.test.core.xrefsmodel.impl.XRefsModelPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.core.xrefsmodel.impl.AImpl <em>A</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.core.xrefsmodel.impl.AImpl
   * @see org.eclipse.emf.test.core.xrefsmodel.impl.XRefsModelPackageImpl#getA()
   * @generated
   */
  int A = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__NAME = 0;

  /**
   * The feature id for the '<em><b>Others</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__OTHERS = 1;

  /**
   * The feature id for the '<em><b>All Others</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__ALL_OTHERS = 2;

  /**
   * The feature id for the '<em><b>Non Others</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__NON_OTHERS = 3;

  /**
   * The number of structural features of the '<em>A</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A_FEATURE_COUNT = 4;

  /**
   * The number of operations of the '<em>A</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A_OPERATION_COUNT = 0;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.core.xrefsmodel.A <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>A</em>'.
   * @see org.eclipse.emf.test.core.xrefsmodel.A
   * @generated
   */
  EClass getA();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.xrefsmodel.A#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.test.core.xrefsmodel.A#getName()
   * @see #getA()
   * @generated
   */
  EAttribute getA_Name();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.core.xrefsmodel.A#getOthers <em>Others</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Others</em>'.
   * @see org.eclipse.emf.test.core.xrefsmodel.A#getOthers()
   * @see #getA()
   * @generated
   */
  EReference getA_Others();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.core.xrefsmodel.A#getAllOthers <em>All Others</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>All Others</em>'.
   * @see org.eclipse.emf.test.core.xrefsmodel.A#getAllOthers()
   * @see #getA()
   * @generated
   */
  EReference getA_AllOthers();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.core.xrefsmodel.A#getNonOthers <em>Non Others</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Non Others</em>'.
   * @see org.eclipse.emf.test.core.xrefsmodel.A#getNonOthers()
   * @see #getA()
   * @generated
   */
  EReference getA_NonOthers();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  XRefsModelFactory getXRefsModelFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.core.xrefsmodel.impl.AImpl <em>A</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.core.xrefsmodel.impl.AImpl
     * @see org.eclipse.emf.test.core.xrefsmodel.impl.XRefsModelPackageImpl#getA()
     * @generated
     */
    EClass A = eINSTANCE.getA();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute A__NAME = eINSTANCE.getA_Name();

    /**
     * The meta object literal for the '<em><b>Others</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference A__OTHERS = eINSTANCE.getA_Others();

    /**
     * The meta object literal for the '<em><b>All Others</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference A__ALL_OTHERS = eINSTANCE.getA_AllOthers();

    /**
     * The meta object literal for the '<em><b>Non Others</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference A__NON_OTHERS = eINSTANCE.getA_NonOthers();

  }

} //XRefsModelPackage
