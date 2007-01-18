/**
 * <copyright>
 * </copyright>
 *
 * $Id: DBItemFactoryImpl.java,v 1.1 2007/01/18 15:50:15 marcelop Exp $
 */
package org.eclipse.emf.test.models.dbitem.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.test.models.dbitem.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DBItemFactoryImpl extends EFactoryImpl implements DBItemFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static DBItemFactory init()
  {
    try
    {
      DBItemFactory theDBItemFactory = (DBItemFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org.eclipse.emf.test.models/dbitem"); 
      if (theDBItemFactory != null)
      {
        return theDBItemFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DBItemFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DBItemFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case DBItemPackage.DB_TYPE: return createDbType();
      case DBItemPackage.DOCUMENT_ROOT: return createDocumentRoot();
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
  public DBItemPackage getDBItemPackage()
  {
    return (DBItemPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static DBItemPackage getPackage()
  {
    return DBItemPackage.eINSTANCE;
  }

} //DBItemFactoryImpl
