/**
 * <copyright>
 * </copyright>
 *
 * $Id: DbpriceFactoryImpl.java,v 1.1 2004/11/04 05:52:46 marcelop Exp $
 */
package org.eclipse.emf.test.models.dbprice.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.test.models.dbprice.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DbpriceFactoryImpl extends EFactoryImpl implements DbpriceFactory
{
  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbpriceFactoryImpl()
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
      case DbpricePackage.PENCIL_TYPE: return createPencilType();
      case DbpricePackage.PEN_TYPE: return createPenType();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PencilType createPencilType()
  {
    PencilTypeImpl pencilType = new PencilTypeImpl();
    return pencilType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PenType createPenType()
  {
    PenTypeImpl penType = new PenTypeImpl();
    return penType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbpricePackage getDbpricePackage()
  {
    return (DbpricePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static DbpricePackage getPackage()
  {
    return DbpricePackage.eINSTANCE;
  }
} //DbpriceFactoryImpl
