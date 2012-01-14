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
 * @see org.eclipse.emf.test.models.ref.unsettable.URefFactory
 * @model kind="package"
 * @generated
 */
public interface URefPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "unsettable";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org/eclipse/emf/test/models/ref/unsettable.ecore";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.emf.test.models.ref.unsettable";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  URefPackage eINSTANCE = org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.C1UImpl <em>C1U</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.C1UImpl
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getC1U()
   * @generated
   */
  int C1U = 0;

  /**
   * The feature id for the '<em><b>Au</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C1U__AU = 0;

  /**
   * The feature id for the '<em><b>Bu</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C1U__BU = 1;

  /**
   * The number of structural features of the '<em>C1U</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C1U_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.C2UImpl <em>C2U</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.C2UImpl
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getC2U()
   * @generated
   */
  int C2U = 1;

  /**
   * The feature id for the '<em><b>Au</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C2U__AU = 0;

  /**
   * The feature id for the '<em><b>Bu</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C2U__BU = 1;

  /**
   * The number of structural features of the '<em>C2U</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C2U_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.AUImpl <em>AU</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.AUImpl
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getAU()
   * @generated
   */
  int AU = 2;

  /**
   * The feature id for the '<em><b>Bu</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AU__BU = 0;

  /**
   * The feature id for the '<em><b>C2u</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AU__C2U = 1;

  /**
   * The feature id for the '<em><b>Cu</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AU__CU = 2;

  /**
   * The number of structural features of the '<em>AU</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AU_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.BUImpl <em>BU</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.BUImpl
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getBU()
   * @generated
   */
  int BU = 3;

  /**
   * The feature id for the '<em><b>Au</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BU__AU = 0;

  /**
   * The feature id for the '<em><b>C2u</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BU__C2U = 1;

  /**
   * The feature id for the '<em><b>Du</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BU__DU = 2;

  /**
   * The number of structural features of the '<em>BU</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BU_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.CUImpl <em>CU</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.CUImpl
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getCU()
   * @generated
   */
  int CU = 4;

  /**
   * The feature id for the '<em><b>Du</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CU__DU = 0;

  /**
   * The feature id for the '<em><b>C4u</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CU__C4U = 1;

  /**
   * The number of structural features of the '<em>CU</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CU_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.DUImpl <em>DU</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.DUImpl
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getDU()
   * @generated
   */
  int DU = 5;

  /**
   * The feature id for the '<em><b>Cu</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DU__CU = 0;

  /**
   * The feature id for the '<em><b>C4u</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DU__C4U = 1;

  /**
   * The feature id for the '<em><b>Eu</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DU__EU = 2;

  /**
   * The number of structural features of the '<em>DU</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DU_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.C4UImpl <em>C4U</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.C4UImpl
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getC4U()
   * @generated
   */
  int C4U = 6;

  /**
   * The feature id for the '<em><b>Cu</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C4U__CU = 0;

  /**
   * The feature id for the '<em><b>Du</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C4U__DU = 1;

  /**
   * The number of structural features of the '<em>C4U</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C4U_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.C3UImpl <em>C3U</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.C3UImpl
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getC3U()
   * @generated
   */
  int C3U = 7;

  /**
   * The feature id for the '<em><b>Cu</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C3U__CU = 0;

  /**
   * The feature id for the '<em><b>Du</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C3U__DU = 1;

  /**
   * The number of structural features of the '<em>C3U</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C3U_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.EUImpl <em>EU</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.EUImpl
   * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getEU()
   * @generated
   */
  int EU = 8;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EU__NAME = 0;

  /**
   * The feature id for the '<em><b>Ids</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EU__IDS = 1;

  /**
   * The feature id for the '<em><b>Labels</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EU__LABELS = 2;

  /**
   * The feature id for the '<em><b>Du</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EU__DU = 3;

  /**
   * The number of structural features of the '<em>EU</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EU_FEATURE_COUNT = 4;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.unsettable.C1U <em>C1U</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>C1U</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C1U
   * @generated
   */
  EClass getC1U();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ref.unsettable.C1U#getAu <em>Au</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Au</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C1U#getAu()
   * @see #getC1U()
   * @generated
   */
  EReference getC1U_Au();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.ref.unsettable.C1U#getBu <em>Bu</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Bu</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C1U#getBu()
   * @see #getC1U()
   * @generated
   */
  EReference getC1U_Bu();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.unsettable.C2U <em>C2U</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>C2U</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C2U
   * @generated
   */
  EClass getC2U();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getAu <em>Au</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Au</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C2U#getAu()
   * @see #getC2U()
   * @generated
   */
  EReference getC2U_Au();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.ref.unsettable.C2U#getBu <em>Bu</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Bu</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C2U#getBu()
   * @see #getC2U()
   * @generated
   */
  EReference getC2U_Bu();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.unsettable.AU <em>AU</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>AU</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.AU
   * @generated
   */
  EClass getAU();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getBu <em>Bu</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Bu</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.AU#getBu()
   * @see #getAU()
   * @generated
   */
  EReference getAU_Bu();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getC2u <em>C2u</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>C2u</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.AU#getC2u()
   * @see #getAU()
   * @generated
   */
  EReference getAU_C2u();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.test.models.ref.unsettable.AU#getCu <em>Cu</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Cu</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.AU#getCu()
   * @see #getAU()
   * @generated
   */
  EReference getAU_Cu();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.unsettable.BU <em>BU</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>BU</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.BU
   * @generated
   */
  EClass getBU();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getAu <em>Au</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Au</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.BU#getAu()
   * @see #getBU()
   * @generated
   */
  EReference getBU_Au();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getC2u <em>C2u</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>C2u</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.BU#getC2u()
   * @see #getBU()
   * @generated
   */
  EReference getBU_C2u();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.models.ref.unsettable.BU#getDu <em>Du</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Du</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.BU#getDu()
   * @see #getBU()
   * @generated
   */
  EReference getBU_Du();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.unsettable.CU <em>CU</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>CU</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.CU
   * @generated
   */
  EClass getCU();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.models.ref.unsettable.CU#getDu <em>Du</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Du</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.CU#getDu()
   * @see #getCU()
   * @generated
   */
  EReference getCU_Du();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.test.models.ref.unsettable.CU#getC4u <em>C4u</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>C4u</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.CU#getC4u()
   * @see #getCU()
   * @generated
   */
  EReference getCU_C4u();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.unsettable.DU <em>DU</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>DU</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.DU
   * @generated
   */
  EClass getDU();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getCu <em>Cu</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Cu</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.DU#getCu()
   * @see #getDU()
   * @generated
   */
  EReference getDU_Cu();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getC4u <em>C4u</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>C4u</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.DU#getC4u()
   * @see #getDU()
   * @generated
   */
  EReference getDU_C4u();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getEu <em>Eu</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Eu</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.DU#getEu()
   * @see #getDU()
   * @generated
   */
  EReference getDU_Eu();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.unsettable.C4U <em>C4U</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>C4U</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C4U
   * @generated
   */
  EClass getC4U();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ref.unsettable.C4U#getCu <em>Cu</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cu</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C4U#getCu()
   * @see #getC4U()
   * @generated
   */
  EReference getC4U_Cu();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.ref.unsettable.C4U#getDu <em>Du</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Du</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C4U#getDu()
   * @see #getC4U()
   * @generated
   */
  EReference getC4U_Du();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.unsettable.C3U <em>C3U</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>C3U</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C3U
   * @generated
   */
  EClass getC3U();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ref.unsettable.C3U#getCu <em>Cu</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cu</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C3U#getCu()
   * @see #getC3U()
   * @generated
   */
  EReference getC3U_Cu();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.ref.unsettable.C3U#getDu <em>Du</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Du</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.C3U#getDu()
   * @see #getC3U()
   * @generated
   */
  EReference getC3U_Du();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.unsettable.EU <em>EU</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EU</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.EU
   * @generated
   */
  EClass getEU();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.EU#getName()
   * @see #getEU()
   * @generated
   */
  EAttribute getEU_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getIds <em>Ids</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Ids</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.EU#getIds()
   * @see #getEU()
   * @generated
   */
  EAttribute getEU_Ids();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getLabels <em>Labels</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Labels</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.EU#getLabels()
   * @see #getEU()
   * @generated
   */
  EAttribute getEU_Labels();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getDu <em>Du</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Du</em>'.
   * @see org.eclipse.emf.test.models.ref.unsettable.EU#getDu()
   * @see #getEU()
   * @generated
   */
  EReference getEU_Du();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  URefFactory getURefFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.C1UImpl <em>C1U</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.C1UImpl
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getC1U()
     * @generated
     */
    EClass C1U = eINSTANCE.getC1U();

    /**
     * The meta object literal for the '<em><b>Au</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference C1U__AU = eINSTANCE.getC1U_Au();

    /**
     * The meta object literal for the '<em><b>Bu</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference C1U__BU = eINSTANCE.getC1U_Bu();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.C2UImpl <em>C2U</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.C2UImpl
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getC2U()
     * @generated
     */
    EClass C2U = eINSTANCE.getC2U();

    /**
     * The meta object literal for the '<em><b>Au</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference C2U__AU = eINSTANCE.getC2U_Au();

    /**
     * The meta object literal for the '<em><b>Bu</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference C2U__BU = eINSTANCE.getC2U_Bu();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.AUImpl <em>AU</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.AUImpl
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getAU()
     * @generated
     */
    EClass AU = eINSTANCE.getAU();

    /**
     * The meta object literal for the '<em><b>Bu</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AU__BU = eINSTANCE.getAU_Bu();

    /**
     * The meta object literal for the '<em><b>C2u</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AU__C2U = eINSTANCE.getAU_C2u();

    /**
     * The meta object literal for the '<em><b>Cu</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AU__CU = eINSTANCE.getAU_Cu();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.BUImpl <em>BU</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.BUImpl
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getBU()
     * @generated
     */
    EClass BU = eINSTANCE.getBU();

    /**
     * The meta object literal for the '<em><b>Au</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BU__AU = eINSTANCE.getBU_Au();

    /**
     * The meta object literal for the '<em><b>C2u</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BU__C2U = eINSTANCE.getBU_C2u();

    /**
     * The meta object literal for the '<em><b>Du</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BU__DU = eINSTANCE.getBU_Du();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.CUImpl <em>CU</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.CUImpl
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getCU()
     * @generated
     */
    EClass CU = eINSTANCE.getCU();

    /**
     * The meta object literal for the '<em><b>Du</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CU__DU = eINSTANCE.getCU_Du();

    /**
     * The meta object literal for the '<em><b>C4u</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CU__C4U = eINSTANCE.getCU_C4u();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.DUImpl <em>DU</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.DUImpl
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getDU()
     * @generated
     */
    EClass DU = eINSTANCE.getDU();

    /**
     * The meta object literal for the '<em><b>Cu</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DU__CU = eINSTANCE.getDU_Cu();

    /**
     * The meta object literal for the '<em><b>C4u</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DU__C4U = eINSTANCE.getDU_C4u();

    /**
     * The meta object literal for the '<em><b>Eu</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DU__EU = eINSTANCE.getDU_Eu();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.C4UImpl <em>C4U</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.C4UImpl
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getC4U()
     * @generated
     */
    EClass C4U = eINSTANCE.getC4U();

    /**
     * The meta object literal for the '<em><b>Cu</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference C4U__CU = eINSTANCE.getC4U_Cu();

    /**
     * The meta object literal for the '<em><b>Du</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference C4U__DU = eINSTANCE.getC4U_Du();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.C3UImpl <em>C3U</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.C3UImpl
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getC3U()
     * @generated
     */
    EClass C3U = eINSTANCE.getC3U();

    /**
     * The meta object literal for the '<em><b>Cu</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference C3U__CU = eINSTANCE.getC3U_Cu();

    /**
     * The meta object literal for the '<em><b>Du</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference C3U__DU = eINSTANCE.getC3U_Du();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.ref.unsettable.impl.EUImpl <em>EU</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.EUImpl
     * @see org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl#getEU()
     * @generated
     */
    EClass EU = eINSTANCE.getEU();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EU__NAME = eINSTANCE.getEU_Name();

    /**
     * The meta object literal for the '<em><b>Ids</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EU__IDS = eINSTANCE.getEU_Ids();

    /**
     * The meta object literal for the '<em><b>Labels</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EU__LABELS = eINSTANCE.getEU_Labels();

    /**
     * The meta object literal for the '<em><b>Du</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EU__DU = eINSTANCE.getEU_Du();

  }

} //URefPackage
