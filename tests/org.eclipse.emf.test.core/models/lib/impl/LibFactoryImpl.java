/**
 * <copyright>
 * </copyright>
 *
 * $Id: LibFactoryImpl.java,v 1.1 2006/02/08 21:30:37 marcelop Exp $
 */
package lib.impl;

import lib.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LibFactoryImpl extends EFactoryImpl implements LibFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static LibFactory init()
  {
    try
    {
      LibFactory theLibFactory = (LibFactory)EPackage.Registry.INSTANCE.getEFactory("http:///lib.ecore"); 
      if (theLibFactory != null)
      {
        return theLibFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new LibFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LibFactoryImpl()
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
      case LibPackage.LIBRARY: return createLibrary();
      case LibPackage.BOOK: return createBook();
      case LibPackage.ADDRESS: return createAddress();
      case LibPackage.PERSON: return createPerson();
      case LibPackage.CAFETERIA: return createCafeteria();
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
  public Book createBook()
  {
    BookImpl book = new BookImpl();
    return book;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Address createAddress()
  {
    AddressImpl address = new AddressImpl();
    return address;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Person createPerson()
  {
    PersonImpl person = new PersonImpl();
    return person;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Cafeteria createCafeteria()
  {
    CafeteriaImpl cafeteria = new CafeteriaImpl();
    return cafeteria;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LibPackage getLibPackage()
  {
    return (LibPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static LibPackage getPackage()
  {
    return LibPackage.eINSTANCE;
  }

} //LibFactoryImpl
