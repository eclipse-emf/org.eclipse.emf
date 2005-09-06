/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.test.models.dbprice;

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
 * @see org.eclipse.emf.test.models.dbprice.DbpriceFactory
 * @generated
 */
public interface DbpricePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "dbprice";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://org/eclipse/emf/test/models/dbprice";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "dbprice";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DbpricePackage eINSTANCE = org.eclipse.emf.test.models.dbprice.impl.DbpricePackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.dbprice.impl.PencilTypeImpl <em>Pencil Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.dbprice.impl.PencilTypeImpl
   * @see org.eclipse.emf.test.models.dbprice.impl.DbpricePackageImpl#getPencilType()
   * @generated
   */
  int PENCIL_TYPE = 0;

  /**
   * The feature id for the '<em><b>Price</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PENCIL_TYPE__PRICE = 0;

  /**
   * The number of structural features of the the '<em>Pencil Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PENCIL_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.dbprice.impl.PenTypeImpl <em>Pen Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.dbprice.impl.PenTypeImpl
   * @see org.eclipse.emf.test.models.dbprice.impl.DbpricePackageImpl#getPenType()
   * @generated
   */
  int PEN_TYPE = 1;

  /**
   * The feature id for the '<em><b>Price</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PEN_TYPE__PRICE = 0;

  /**
   * The number of structural features of the the '<em>Pen Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PEN_TYPE_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.dbprice.PencilType <em>Pencil Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pencil Type</em>'.
   * @see org.eclipse.emf.test.models.dbprice.PencilType
   * @generated
   */
  EClass getPencilType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.dbprice.PencilType#getPrice <em>Price</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Price</em>'.
   * @see org.eclipse.emf.test.models.dbprice.PencilType#getPrice()
   * @see #getPencilType()
   * @generated
   */
  EAttribute getPencilType_Price();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.dbprice.PenType <em>Pen Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pen Type</em>'.
   * @see org.eclipse.emf.test.models.dbprice.PenType
   * @generated
   */
  EClass getPenType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.dbprice.PenType#getPrice <em>Price</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Price</em>'.
   * @see org.eclipse.emf.test.models.dbprice.PenType#getPrice()
   * @see #getPenType()
   * @generated
   */
  EAttribute getPenType_Price();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  DbpriceFactory getDbpriceFactory();

} //DbpricePackage
