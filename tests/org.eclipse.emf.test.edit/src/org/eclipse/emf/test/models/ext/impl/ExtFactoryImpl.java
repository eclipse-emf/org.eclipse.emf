/**
 * <copyright>
 * </copyright>
 *
 * $Id: ExtFactoryImpl.java,v 1.1 2005/07/14 19:35:16 davidms Exp $
 */
package org.eclipse.emf.test.models.ext.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.test.models.ext.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtFactoryImpl extends EFactoryImpl implements ExtFactory
{
  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtFactoryImpl()
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
      case ExtPackage.EXT_E: return createExtE();
      case ExtPackage.F: return createF();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtE createExtE()
  {
    ExtEImpl extE = new ExtEImpl();
    return extE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public F createF()
  {
    FImpl f = new FImpl();
    return f;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtPackage getExtPackage()
  {
    return (ExtPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static ExtPackage getPackage()
  {
    return ExtPackage.eINSTANCE;
  }

} //ExtFactoryImpl
