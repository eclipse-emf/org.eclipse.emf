/**
 * <copyright>
 * </copyright>
 *
 * $Id: LibraryFactoryImpl.java,v 1.1 2005/02/10 22:11:11 marcelop Exp $
 */
package org.examples.library.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.examples.library.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LibraryFactoryImpl extends EFactoryImpl implements LibraryFactory
{
  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LibraryFactoryImpl()
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
      case LibraryPackage.LIBRARY: return createLibrary();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Library createLibrary()
  {
    LibraryImpl library = new LibraryImpl();
    return library;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LibraryPackage getLibraryPackage()
  {
    return (LibraryPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static LibraryPackage getPackage()
  {
    return LibraryPackage.eINSTANCE;
  }

} //LibraryFactoryImpl
