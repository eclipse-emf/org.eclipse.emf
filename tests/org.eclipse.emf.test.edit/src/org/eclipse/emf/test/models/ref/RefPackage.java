/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefPackage.java,v 1.1 2004/11/04 05:52:03 marcelop Exp $
 */
package org.eclipse.emf.test.models.ref;

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
 * @see org.eclipse.emf.test.models.ref.RefFactory
 * @generated
 */
public interface RefPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "ref";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org/eclipse/emf/test/models/ref.ecore";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.emf.test.models.ref";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  RefPackage eINSTANCE = org.eclipse.emf.test.models.ref.impl.RefPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.impl.AImpl <em>A</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.impl.AImpl
   * @see org.eclipse.emf.test.models.ref.impl.RefPackageImpl#getA()
   * @generated
   */
  int A = 0;

  /**
   * The feature id for the '<em><b>B</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__B = 0;

  /**
   * The feature id for the '<em><b>C2</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__C2 = 1;

  /**
   * The feature id for the '<em><b>C</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A__C = 2;

  /**
   * The number of structural features of the the '<em>A</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int A_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.impl.BImpl <em>B</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.impl.BImpl
   * @see org.eclipse.emf.test.models.ref.impl.RefPackageImpl#getB()
   * @generated
   */
  int B = 1;

  /**
   * The feature id for the '<em><b>A</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int B__A = 0;

  /**
   * The feature id for the '<em><b>C2</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int B__C2 = 1;

  /**
   * The feature id for the '<em><b>D</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int B__D = 2;

  /**
   * The number of structural features of the the '<em>B</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int B_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.impl.C1Impl <em>C1</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.impl.C1Impl
   * @see org.eclipse.emf.test.models.ref.impl.RefPackageImpl#getC1()
   * @generated
   */
  int C1 = 2;

  /**
   * The feature id for the '<em><b>A</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C1__A = 0;

  /**
   * The feature id for the '<em><b>B</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C1__B = 1;

  /**
   * The number of structural features of the the '<em>C1</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C1_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.impl.C2Impl <em>C2</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.impl.C2Impl
   * @see org.eclipse.emf.test.models.ref.impl.RefPackageImpl#getC2()
   * @generated
   */
  int C2 = 3;

  /**
   * The feature id for the '<em><b>B</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C2__B = 0;

  /**
   * The feature id for the '<em><b>A</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C2__A = 1;

  /**
   * The number of structural features of the the '<em>C2</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C2_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.impl.CImpl <em>C</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.impl.CImpl
   * @see org.eclipse.emf.test.models.ref.impl.RefPackageImpl#getC()
   * @generated
   */
  int C = 4;

  /**
   * The feature id for the '<em><b>D</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C__D = 0;

  /**
   * The number of structural features of the the '<em>C</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int C_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.impl.DImpl <em>D</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.impl.DImpl
   * @see org.eclipse.emf.test.models.ref.impl.RefPackageImpl#getD()
   * @generated
   */
  int D = 5;

  /**
   * The feature id for the '<em><b>C</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int D__C = 0;

  /**
   * The feature id for the '<em><b>E</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int D__E = 1;

  /**
   * The number of structural features of the the '<em>D</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int D_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.ref.impl.EImpl <em>E</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.ref.impl.EImpl
   * @see org.eclipse.emf.test.models.ref.impl.RefPackageImpl#getE()
   * @generated
   */
  int E = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int E__NAME = 0;

  /**
   * The feature id for the '<em><b>Ids</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int E__IDS = 1;

  /**
   * The feature id for the '<em><b>Labels</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int E__LABELS = 2;

  /**
   * The feature id for the '<em><b>D</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int E__D = 3;

  /**
   * The number of structural features of the the '<em>E</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int E_FEATURE_COUNT = 4;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.A <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>A</em>'.
   * @see org.eclipse.emf.test.models.ref.A
   * @generated
   */
  EClass getA();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.test.models.ref.A#getB <em>B</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>B</em>'.
   * @see org.eclipse.emf.test.models.ref.A#getB()
   * @see #getA()
   * @generated
   */
  EReference getA_B();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.test.models.ref.A#getC2 <em>C2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>C2</em>'.
   * @see org.eclipse.emf.test.models.ref.A#getC2()
   * @see #getA()
   * @generated
   */
  EReference getA_C2();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.test.models.ref.A#getC <em>C</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>C</em>'.
   * @see org.eclipse.emf.test.models.ref.A#getC()
   * @see #getA()
   * @generated
   */
  EReference getA_C();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.B <em>B</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>B</em>'.
   * @see org.eclipse.emf.test.models.ref.B
   * @generated
   */
  EClass getB();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.test.models.ref.B#getA <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>A</em>'.
   * @see org.eclipse.emf.test.models.ref.B#getA()
   * @see #getB()
   * @generated
   */
  EReference getB_A();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.test.models.ref.B#getC2 <em>C2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>C2</em>'.
   * @see org.eclipse.emf.test.models.ref.B#getC2()
   * @see #getB()
   * @generated
   */
  EReference getB_C2();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.models.ref.B#getD <em>D</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>D</em>'.
   * @see org.eclipse.emf.test.models.ref.B#getD()
   * @see #getB()
   * @generated
   */
  EReference getB_D();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.C1 <em>C1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>C1</em>'.
   * @see org.eclipse.emf.test.models.ref.C1
   * @generated
   */
  EClass getC1();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ref.C1#getA <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>A</em>'.
   * @see org.eclipse.emf.test.models.ref.C1#getA()
   * @see #getC1()
   * @generated
   */
  EReference getC1_A();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.ref.C1#getB <em>B</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>B</em>'.
   * @see org.eclipse.emf.test.models.ref.C1#getB()
   * @see #getC1()
   * @generated
   */
  EReference getC1_B();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.C2 <em>C2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>C2</em>'.
   * @see org.eclipse.emf.test.models.ref.C2
   * @generated
   */
  EClass getC2();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.ref.C2#getB <em>B</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>B</em>'.
   * @see org.eclipse.emf.test.models.ref.C2#getB()
   * @see #getC2()
   * @generated
   */
  EReference getC2_B();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.ref.C2#getA <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>A</em>'.
   * @see org.eclipse.emf.test.models.ref.C2#getA()
   * @see #getC2()
   * @generated
   */
  EReference getC2_A();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.C <em>C</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>C</em>'.
   * @see org.eclipse.emf.test.models.ref.C
   * @generated
   */
  EClass getC();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.models.ref.C#getD <em>D</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>D</em>'.
   * @see org.eclipse.emf.test.models.ref.C#getD()
   * @see #getC()
   * @generated
   */
  EReference getC_D();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.D <em>D</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>D</em>'.
   * @see org.eclipse.emf.test.models.ref.D
   * @generated
   */
  EClass getD();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.test.models.ref.D#getC <em>C</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>C</em>'.
   * @see org.eclipse.emf.test.models.ref.D#getC()
   * @see #getD()
   * @generated
   */
  EReference getD_C();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.models.ref.D#getE <em>E</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>E</em>'.
   * @see org.eclipse.emf.test.models.ref.D#getE()
   * @see #getD()
   * @generated
   */
  EReference getD_E();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.ref.E <em>E</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>E</em>'.
   * @see org.eclipse.emf.test.models.ref.E
   * @generated
   */
  EClass getE();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.ref.E#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.test.models.ref.E#getName()
   * @see #getE()
   * @generated
   */
  EAttribute getE_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.ref.E#getIds <em>Ids</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Ids</em>'.
   * @see org.eclipse.emf.test.models.ref.E#getIds()
   * @see #getE()
   * @generated
   */
  EAttribute getE_Ids();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.ref.E#getLabels <em>Labels</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Labels</em>'.
   * @see org.eclipse.emf.test.models.ref.E#getLabels()
   * @see #getE()
   * @generated
   */
  EAttribute getE_Labels();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.test.models.ref.E#getD <em>D</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>D</em>'.
   * @see org.eclipse.emf.test.models.ref.E#getD()
   * @see #getE()
   * @generated
   */
  EReference getE_D();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  RefFactory getRefFactory();

} //RefPackage
