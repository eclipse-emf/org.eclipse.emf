/**
 * <copyright>
 * </copyright>
 *
 * $Id: DbpriceFactory.java,v 1.1 2004/11/04 05:52:46 marcelop Exp $
 */
package org.eclipse.emf.test.models.dbprice;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.dbprice.DbpricePackage
 * @generated
 */
public interface DbpriceFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DbpriceFactory eINSTANCE = new org.eclipse.emf.test.models.dbprice.impl.DbpriceFactoryImpl();

  /**
   * Returns a new object of class '<em>Pencil Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Pencil Type</em>'.
   * @generated
   */
  PencilType createPencilType();

  /**
   * Returns a new object of class '<em>Pen Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Pen Type</em>'.
   * @generated
   */
  PenType createPenType();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  DbpricePackage getDbpricePackage();

} //DbpriceFactory
