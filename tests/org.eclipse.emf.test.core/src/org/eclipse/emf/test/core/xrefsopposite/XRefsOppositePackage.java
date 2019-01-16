/**
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   Thales - initial API and implementation
 */
package org.eclipse.emf.test.core.xrefsopposite;

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
 * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositeFactory
 * @model kind="package"
 * @generated
 */
public interface XRefsOppositePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "xrefsopposite";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/EMF/2019/test/xrefsopposite";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "xrefso";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  XRefsOppositePackage eINSTANCE = org.eclipse.emf.test.core.xrefsopposite.impl.XRefsOppositePackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.core.xrefsopposite.impl.AbstractAImpl <em>Abstract A</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.core.xrefsopposite.impl.AbstractAImpl
   * @see org.eclipse.emf.test.core.xrefsopposite.impl.XRefsOppositePackageImpl#getAbstractA()
   * @generated
   */
  int ABSTRACT_A = 2;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACT_A__ID = 0;

  /**
   * The number of structural features of the '<em>Abstract A</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACT_A_FEATURE_COUNT = 1;

  /**
   * The number of operations of the '<em>Abstract A</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACT_A_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.core.xrefsopposite.impl.AImpl <em>A</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.core.xrefsopposite.impl.AImpl
   * @see org.eclipse.emf.test.core.xrefsopposite.impl.XRefsOppositePackageImpl#getA()
   * @generated
   */
  int A = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__ID = ABSTRACT_A__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__NAME = ABSTRACT_A_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Ref1</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__REF1 = ABSTRACT_A_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Oref1</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__OREF1 = ABSTRACT_A_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Ref2</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__REF2 = ABSTRACT_A_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Oref2</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__OREF2 = ABSTRACT_A_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Ref3</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__REF3 = ABSTRACT_A_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Owning APkg</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__OWNING_APKG = ABSTRACT_A_FEATURE_COUNT + 6;

  /**
   * The number of structural features of the '<em>A</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A_FEATURE_COUNT = ABSTRACT_A_FEATURE_COUNT + 7;

  /**
   * The number of operations of the '<em>A</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A_OPERATION_COUNT = ABSTRACT_A_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.core.xrefsopposite.impl.APkgImpl <em>APkg</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.core.xrefsopposite.impl.APkgImpl
   * @see org.eclipse.emf.test.core.xrefsopposite.impl.XRefsOppositePackageImpl#getAPkg()
   * @generated
   */
  int APKG = 1;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int APKG__ID = ABSTRACT_A__ID;

  /**
   * The feature id for the '<em><b>Owned As</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int APKG__OWNED_AS = ABSTRACT_A_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>APkg</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int APKG_FEATURE_COUNT = ABSTRACT_A_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>APkg</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int APKG_OPERATION_COUNT = ABSTRACT_A_OPERATION_COUNT + 0;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.core.xrefsopposite.A <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>A</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.A
   * @generated
   */
  EClass getA();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.xrefsopposite.A#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getName()
   * @see #getA()
   * @generated
   */
  EAttribute getA_Name();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.core.xrefsopposite.A#getRef1 <em>Ref1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Ref1</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getRef1()
   * @see #getA()
   * @generated
   */
  EReference getA_Ref1();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.core.xrefsopposite.A#getOref1 <em>Oref1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Oref1</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getOref1()
   * @see #getA()
   * @generated
   */
  EReference getA_Oref1();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.core.xrefsopposite.A#getRef2 <em>Ref2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Ref2</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getRef2()
   * @see #getA()
   * @generated
   */
  EReference getA_Ref2();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.core.xrefsopposite.A#getOref2 <em>Oref2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Oref2</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getOref2()
   * @see #getA()
   * @generated
   */
  EReference getA_Oref2();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.core.xrefsopposite.A#getRef3 <em>Ref3</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Ref3</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getRef3()
   * @see #getA()
   * @generated
   */
  EReference getA_Ref3();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.test.core.xrefsopposite.A#getOwningAPkg <em>Owning APkg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Owning APkg</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getOwningAPkg()
   * @see #getA()
   * @generated
   */
  EReference getA_OwningAPkg();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.core.xrefsopposite.APkg <em>APkg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>APkg</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.APkg
   * @generated
   */
  EClass getAPkg();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.core.xrefsopposite.APkg#getOwnedAs <em>Owned As</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned As</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.APkg#getOwnedAs()
   * @see #getAPkg()
   * @generated
   */
  EReference getAPkg_OwnedAs();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.core.xrefsopposite.AbstractA <em>Abstract A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract A</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.AbstractA
   * @generated
   */
  EClass getAbstractA();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.xrefsopposite.AbstractA#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.emf.test.core.xrefsopposite.AbstractA#getId()
   * @see #getAbstractA()
   * @generated
   */
  EAttribute getAbstractA_Id();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  XRefsOppositeFactory getXRefsOppositeFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.test.core.xrefsopposite.impl.AImpl <em>A</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.core.xrefsopposite.impl.AImpl
     * @see org.eclipse.emf.test.core.xrefsopposite.impl.XRefsOppositePackageImpl#getA()
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
     * The meta object literal for the '<em><b>Ref1</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference A__REF1 = eINSTANCE.getA_Ref1();

    /**
     * The meta object literal for the '<em><b>Oref1</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference A__OREF1 = eINSTANCE.getA_Oref1();

    /**
     * The meta object literal for the '<em><b>Ref2</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference A__REF2 = eINSTANCE.getA_Ref2();

    /**
     * The meta object literal for the '<em><b>Oref2</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference A__OREF2 = eINSTANCE.getA_Oref2();

    /**
     * The meta object literal for the '<em><b>Ref3</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference A__REF3 = eINSTANCE.getA_Ref3();

    /**
     * The meta object literal for the '<em><b>Owning APkg</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference A__OWNING_APKG = eINSTANCE.getA_OwningAPkg();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.core.xrefsopposite.impl.APkgImpl <em>APkg</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.core.xrefsopposite.impl.APkgImpl
     * @see org.eclipse.emf.test.core.xrefsopposite.impl.XRefsOppositePackageImpl#getAPkg()
     * @generated
     */
    EClass APKG = eINSTANCE.getAPkg();

    /**
     * The meta object literal for the '<em><b>Owned As</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference APKG__OWNED_AS = eINSTANCE.getAPkg_OwnedAs();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.core.xrefsopposite.impl.AbstractAImpl <em>Abstract A</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.core.xrefsopposite.impl.AbstractAImpl
     * @see org.eclipse.emf.test.core.xrefsopposite.impl.XRefsOppositePackageImpl#getAbstractA()
     * @generated
     */
    EClass ABSTRACT_A = eINSTANCE.getAbstractA();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ABSTRACT_A__ID = eINSTANCE.getAbstractA_Id();

  }

} //XRefsOppositePackage
