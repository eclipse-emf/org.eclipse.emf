/**
 * <copyright>
 * </copyright>
 *
 * $Id: LibPackage.java,v 1.1 2006/02/08 21:30:38 marcelop Exp $
 */
package lib;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see lib.LibFactory
 * @model kind="package"
 * @generated
 */
public interface LibPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "lib";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///lib.ecore";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "lib";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  LibPackage eINSTANCE = lib.impl.LibPackageImpl.init();

  /**
   * The meta object id for the '{@link lib.impl.LibraryImpl <em>Library</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see lib.impl.LibraryImpl
   * @see lib.impl.LibPackageImpl#getLibrary()
   * @generated
   */
  int LIBRARY = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__NAME = 0;

  /**
   * The feature id for the '<em><b>Books</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__BOOKS = 1;

  /**
   * The feature id for the '<em><b>Address</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__ADDRESS = 2;

  /**
   * The feature id for the '<em><b>Writers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__WRITERS = 3;

  /**
   * The feature id for the '<em><b>Cafeteria</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__CAFETERIA = 4;

  /**
   * The number of structural features of the '<em>Library</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link lib.impl.BookImpl <em>Book</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see lib.impl.BookImpl
   * @see lib.impl.LibPackageImpl#getBook()
   * @generated
   */
  int BOOK = 1;

  /**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__TITLE = 0;

  /**
   * The number of structural features of the '<em>Book</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_FEATURE_COUNT = 1;


  /**
   * The meta object id for the '{@link lib.impl.AddressImpl <em>Address</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see lib.impl.AddressImpl
   * @see lib.impl.LibPackageImpl#getAddress()
   * @generated
   */
  int ADDRESS = 2;

  /**
   * The feature id for the '<em><b>Postal Code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDRESS__POSTAL_CODE = 0;

  /**
   * The number of structural features of the '<em>Address</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDRESS_FEATURE_COUNT = 1;


  /**
   * The meta object id for the '{@link lib.impl.PersonImpl <em>Person</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see lib.impl.PersonImpl
   * @see lib.impl.LibPackageImpl#getPerson()
   * @generated
   */
  int PERSON = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON__NAME = 0;

  /**
   * The feature id for the '<em><b>Library</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON__LIBRARY = 1;

  /**
   * The number of structural features of the '<em>Person</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_FEATURE_COUNT = 2;


  /**
   * The meta object id for the '{@link lib.impl.CafeteriaImpl <em>Cafeteria</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see lib.impl.CafeteriaImpl
   * @see lib.impl.LibPackageImpl#getCafeteria()
   * @generated
   */
  int CAFETERIA = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAFETERIA__NAME = 0;

  /**
   * The feature id for the '<em><b>Library</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAFETERIA__LIBRARY = 1;

  /**
   * The number of structural features of the '<em>Cafeteria</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAFETERIA_FEATURE_COUNT = 2;


  /**
   * Returns the meta object for class '{@link lib.Library <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Library</em>'.
   * @see lib.Library
   * @generated
   */
  EClass getLibrary();

  /**
   * Returns the meta object for the attribute '{@link lib.Library#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see lib.Library#getName()
   * @see #getLibrary()
   * @generated
   */
  EAttribute getLibrary_Name();

  /**
   * Returns the meta object for the containment reference list '{@link lib.Library#getBooks <em>Books</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Books</em>'.
   * @see lib.Library#getBooks()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Books();

  /**
   * Returns the meta object for the containment reference '{@link lib.Library#getAddress <em>Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Address</em>'.
   * @see lib.Library#getAddress()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Address();

  /**
   * Returns the meta object for the containment reference list '{@link lib.Library#getWriters <em>Writers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Writers</em>'.
   * @see lib.Library#getWriters()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Writers();

  /**
   * Returns the meta object for the containment reference '{@link lib.Library#getCafeteria <em>Cafeteria</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cafeteria</em>'.
   * @see lib.Library#getCafeteria()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Cafeteria();

  /**
   * Returns the meta object for class '{@link lib.Book <em>Book</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Book</em>'.
   * @see lib.Book
   * @generated
   */
  EClass getBook();

  /**
   * Returns the meta object for the attribute '{@link lib.Book#getTitle <em>Title</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Title</em>'.
   * @see lib.Book#getTitle()
   * @see #getBook()
   * @generated
   */
  EAttribute getBook_Title();

  /**
   * Returns the meta object for class '{@link lib.Address <em>Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Address</em>'.
   * @see lib.Address
   * @generated
   */
  EClass getAddress();

  /**
   * Returns the meta object for the attribute '{@link lib.Address#getPostalCode <em>Postal Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Postal Code</em>'.
   * @see lib.Address#getPostalCode()
   * @see #getAddress()
   * @generated
   */
  EAttribute getAddress_PostalCode();

  /**
   * Returns the meta object for class '{@link lib.Person <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Person</em>'.
   * @see lib.Person
   * @generated
   */
  EClass getPerson();

  /**
   * Returns the meta object for the attribute '{@link lib.Person#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see lib.Person#getName()
   * @see #getPerson()
   * @generated
   */
  EAttribute getPerson_Name();

  /**
   * Returns the meta object for the container reference '{@link lib.Person#getLibrary <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Library</em>'.
   * @see lib.Person#getLibrary()
   * @see #getPerson()
   * @generated
   */
  EReference getPerson_Library();

  /**
   * Returns the meta object for class '{@link lib.Cafeteria <em>Cafeteria</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Cafeteria</em>'.
   * @see lib.Cafeteria
   * @generated
   */
  EClass getCafeteria();

  /**
   * Returns the meta object for the attribute '{@link lib.Cafeteria#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see lib.Cafeteria#getName()
   * @see #getCafeteria()
   * @generated
   */
  EAttribute getCafeteria_Name();

  /**
   * Returns the meta object for the container reference '{@link lib.Cafeteria#getLibrary <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Library</em>'.
   * @see lib.Cafeteria#getLibrary()
   * @see #getCafeteria()
   * @generated
   */
  EReference getCafeteria_Library();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  LibFactory getLibFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals 
  {
    /**
     * The meta object literal for the '{@link lib.impl.LibraryImpl <em>Library</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see lib.impl.LibraryImpl
     * @see lib.impl.LibPackageImpl#getLibrary()
     * @generated
     */
    EClass LIBRARY = eINSTANCE.getLibrary();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIBRARY__NAME = eINSTANCE.getLibrary_Name();

    /**
     * The meta object literal for the '<em><b>Books</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__BOOKS = eINSTANCE.getLibrary_Books();

    /**
     * The meta object literal for the '<em><b>Address</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__ADDRESS = eINSTANCE.getLibrary_Address();

    /**
     * The meta object literal for the '<em><b>Writers</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__WRITERS = eINSTANCE.getLibrary_Writers();

    /**
     * The meta object literal for the '<em><b>Cafeteria</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__CAFETERIA = eINSTANCE.getLibrary_Cafeteria();

    /**
     * The meta object literal for the '{@link lib.impl.BookImpl <em>Book</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see lib.impl.BookImpl
     * @see lib.impl.LibPackageImpl#getBook()
     * @generated
     */
    EClass BOOK = eINSTANCE.getBook();

    /**
     * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOK__TITLE = eINSTANCE.getBook_Title();

    /**
     * The meta object literal for the '{@link lib.impl.AddressImpl <em>Address</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see lib.impl.AddressImpl
     * @see lib.impl.LibPackageImpl#getAddress()
     * @generated
     */
    EClass ADDRESS = eINSTANCE.getAddress();

    /**
     * The meta object literal for the '<em><b>Postal Code</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADDRESS__POSTAL_CODE = eINSTANCE.getAddress_PostalCode();

    /**
     * The meta object literal for the '{@link lib.impl.PersonImpl <em>Person</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see lib.impl.PersonImpl
     * @see lib.impl.LibPackageImpl#getPerson()
     * @generated
     */
    EClass PERSON = eINSTANCE.getPerson();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PERSON__NAME = eINSTANCE.getPerson_Name();

    /**
     * The meta object literal for the '<em><b>Library</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PERSON__LIBRARY = eINSTANCE.getPerson_Library();

    /**
     * The meta object literal for the '{@link lib.impl.CafeteriaImpl <em>Cafeteria</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see lib.impl.CafeteriaImpl
     * @see lib.impl.LibPackageImpl#getCafeteria()
     * @generated
     */
    EClass CAFETERIA = eINSTANCE.getCafeteria();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CAFETERIA__NAME = eINSTANCE.getCafeteria_Name();

    /**
     * The meta object literal for the '<em><b>Library</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CAFETERIA__LIBRARY = eINSTANCE.getCafeteria_Library();

  }

} //LibPackage
