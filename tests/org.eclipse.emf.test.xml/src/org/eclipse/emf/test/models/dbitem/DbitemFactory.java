/**
 * <copyright>
 * </copyright>
 *
 * $Id: DbitemFactory.java,v 1.1 2004/11/04 05:52:46 marcelop Exp $
 */
package org.eclipse.emf.test.models.dbitem;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.dbitem.DbitemPackage
 * @generated
 */
public interface DbitemFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DbitemFactory eINSTANCE = new org.eclipse.emf.test.models.dbitem.impl.DbitemFactoryImpl();

  /**
   * Returns a new object of class '<em>Db Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Db Type</em>'.
   * @generated
   */
  DbType createDbType();

  /**
   * Returns a new object of class '<em>Document Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Document Root</em>'.
   * @generated
   */
  DocumentRoot createDocumentRoot();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  DbitemPackage getDbitemPackage();

} //DbitemFactory
