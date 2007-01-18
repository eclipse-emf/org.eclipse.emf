/**
 * <copyright>
 * </copyright>
 *
 * $Id: DBItemFactory.java,v 1.1 2007/01/18 15:50:15 marcelop Exp $
 */
package org.eclipse.emf.test.models.dbitem;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.dbitem.DBItemPackage
 * @generated
 */
public interface DBItemFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DBItemFactory eINSTANCE = org.eclipse.emf.test.models.dbitem.impl.DBItemFactoryImpl.init();

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
  DBItemPackage getDBItemPackage();

} //DBItemFactory
