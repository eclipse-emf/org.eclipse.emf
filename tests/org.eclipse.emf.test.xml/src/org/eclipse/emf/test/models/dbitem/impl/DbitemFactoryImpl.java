/**
 * <copyright>
 * </copyright>
 *
 * $Id: DbitemFactoryImpl.java,v 1.1.2.1 2005/01/14 22:56:19 nickb Exp $
 */
package org.eclipse.emf.test.models.dbitem.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.test.models.dbitem.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DbitemFactoryImpl extends EFactoryImpl implements DbitemFactory
{
  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbitemFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case DbitemPackage.DB_TYPE: return createDbType();
      case DbitemPackage.DOCUMENT_ROOT: return createDocumentRoot();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbType createDbType()
  {
    DbTypeImpl dbType = new DbTypeImpl();
    return dbType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DocumentRoot createDocumentRoot()
  {
    DocumentRootImpl documentRoot = new DocumentRootImpl();
    return documentRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbitemPackage getDbitemPackage()
  {
    return (DbitemPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static DbitemPackage getPackage()
  {
    return DbitemPackage.eINSTANCE;
  }
} //DbitemFactoryImpl
