/**
 * This is my code.
 *
 * $Id: LibraryPackage.java,v 1.5 2007/06/11 21:12:18 emerks Exp $
 */
package org.eclipse.example.library;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.example.library.LibraryFactory
 * @model kind="package"
 * @generated
 */
public interface LibraryPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "library";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org/eclipse/example/library.ecore";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.example.library";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  LibraryPackage eINSTANCE = org.eclipse.example.library.impl.LibraryPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.example.library.impl.BookImpl <em>Book</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.example.library.impl.BookImpl
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getBook()
   * @generated
   */
  int BOOK = 0;

  /**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__TITLE = 0;

  /**
   * The feature id for the '<em><b>Pages</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__PAGES = 1;

  /**
   * The feature id for the '<em><b>Category</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__CATEGORY = 2;

  /**
   * The feature id for the '<em><b>Author</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__AUTHOR = 3;

  /**
   * The number of structural features of the '<em>Book</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.example.library.impl.LibraryImpl <em>Library</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.example.library.impl.LibraryImpl
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getLibrary()
   * @generated
   */
  int LIBRARY = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__NAME = 0;

  /**
   * The feature id for the '<em><b>Writers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__WRITERS = 1;

  /**
   * The feature id for the '<em><b>Books</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__BOOKS = 2;

  /**
   * The feature id for the '<em><b>Special Books</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__SPECIAL_BOOKS = 3;

  /**
   * The feature id for the '<em><b>Book By Title Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__BOOK_BY_TITLE_MAP = 4;

  /**
   * The feature id for the '<em><b>Writer By Name Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__WRITER_BY_NAME_MAP = 5;

  /**
   * The feature id for the '<em><b>Options</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__OPTIONS = 6;

  /**
   * The feature id for the '<em><b>Writer By ID Map</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__WRITER_BY_ID_MAP = 7;

  /**
   * The number of structural features of the '<em>Library</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link org.eclipse.example.library.impl.WriterImpl <em>Writer</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.example.library.impl.WriterImpl
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getWriter()
   * @generated
   */
  int WRITER = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER__NAME = 0;

  /**
   * The feature id for the '<em><b>Books</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER__BOOKS = 1;

  /**
   * The number of structural features of the '<em>Writer</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.example.library.impl.EStringToBookMapEntryImpl <em>EString To Book Map Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.example.library.impl.EStringToBookMapEntryImpl
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getEStringToBookMapEntry()
   * @generated
   */
  int ESTRING_TO_BOOK_MAP_ENTRY = 3;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRING_TO_BOOK_MAP_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRING_TO_BOOK_MAP_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>EString To Book Map Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRING_TO_BOOK_MAP_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.example.library.impl.EStringToWriterMapEntryImpl <em>EString To Writer Map Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.example.library.impl.EStringToWriterMapEntryImpl
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getEStringToWriterMapEntry()
   * @generated
   */
  int ESTRING_TO_WRITER_MAP_ENTRY = 4;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRING_TO_WRITER_MAP_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRING_TO_WRITER_MAP_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>EString To Writer Map Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTRING_TO_WRITER_MAP_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.example.library.BookCategory <em>Book Category</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.example.library.BookCategory
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getBookCategory()
   * @generated
   */
  int BOOK_CATEGORY = 5;

  /**
   * The meta object id for the '<em>My Map Of Integers And Strings</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.Map
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getMyMapOfIntegersAndStrings()
   * @generated
   */
  int MY_MAP_OF_INTEGERS_AND_STRINGS = 6;

  /**
   * The meta object id for the '<em>Map</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.Map
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getMap()
   * @generated
   */
  int MAP = 7;


  /**
   * Returns the meta object for class '{@link org.eclipse.example.library.Book <em>Book</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Book</em>'.
   * @see org.eclipse.example.library.Book
   * @generated
   */
  EClass getBook();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.example.library.Book#getTitle <em>Title</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Title</em>'.
   * @see org.eclipse.example.library.Book#getTitle()
   * @see #getBook()
   * @generated
   */
  EAttribute getBook_Title();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.example.library.Book#getPages <em>Pages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pages</em>'.
   * @see org.eclipse.example.library.Book#getPages()
   * @see #getBook()
   * @generated
   */
  EAttribute getBook_Pages();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.example.library.Book#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Category</em>'.
   * @see org.eclipse.example.library.Book#getCategory()
   * @see #getBook()
   * @generated
   */
  EAttribute getBook_Category();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.example.library.Book#getAuthor <em>Author</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Author</em>'.
   * @see org.eclipse.example.library.Book#getAuthor()
   * @see #getBook()
   * @generated
   */
  EReference getBook_Author();

  /**
   * Returns the meta object for class '{@link org.eclipse.example.library.Library <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Library</em>'.
   * @see org.eclipse.example.library.Library
   * @generated
   */
  EClass getLibrary();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.example.library.Library#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.example.library.Library#getName()
   * @see #getLibrary()
   * @generated
   */
  EAttribute getLibrary_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.example.library.Library#getWriters <em>Writers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Writers</em>'.
   * @see org.eclipse.example.library.Library#getWriters()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Writers();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.example.library.Library#getBooks <em>Books</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Books</em>'.
   * @see org.eclipse.example.library.Library#getBooks()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Books();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.example.library.Library#getSpecialBooks <em>Special Books</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Special Books</em>'.
   * @see org.eclipse.example.library.Library#getSpecialBooks()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_SpecialBooks();

  /**
   * Returns the meta object for the map '{@link org.eclipse.example.library.Library#getBookByTitleMap <em>Book By Title Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Book By Title Map</em>'.
   * @see org.eclipse.example.library.Library#getBookByTitleMap()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_BookByTitleMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.example.library.Library#getWriterByNameMap <em>Writer By Name Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Writer By Name Map</em>'.
   * @see org.eclipse.example.library.Library#getWriterByNameMap()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_WriterByNameMap();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.example.library.Library#getOptions <em>Options</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Options</em>'.
   * @see org.eclipse.example.library.Library#getOptions()
   * @see #getLibrary()
   * @generated
   */
  EAttribute getLibrary_Options();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.example.library.Library#getWriterByIDMap <em>Writer By ID Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Writer By ID Map</em>'.
   * @see org.eclipse.example.library.Library#getWriterByIDMap()
   * @see #getLibrary()
   * @generated
   */
  EAttribute getLibrary_WriterByIDMap();

  /**
   * Returns the meta object for class '{@link org.eclipse.example.library.Writer <em>Writer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Writer</em>'.
   * @see org.eclipse.example.library.Writer
   * @generated
   */
  EClass getWriter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.example.library.Writer#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.example.library.Writer#getName()
   * @see #getWriter()
   * @generated
   */
  EAttribute getWriter_Name();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.example.library.Writer#getBooks <em>Books</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Books</em>'.
   * @see org.eclipse.example.library.Writer#getBooks()
   * @see #getWriter()
   * @generated
   */
  EReference getWriter_Books();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>EString To Book Map Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EString To Book Map Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EString"
   *        valueType="org.eclipse.example.library.Book"
   * @generated
   */
  EClass getEStringToBookMapEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getEStringToBookMapEntry()
   * @generated
   */
  EAttribute getEStringToBookMapEntry_Key();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getEStringToBookMapEntry()
   * @generated
   */
  EReference getEStringToBookMapEntry_Value();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>EString To Writer Map Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EString To Writer Map Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EString"
   *        valueType="org.eclipse.example.library.Writer"
   * @generated
   */
  EClass getEStringToWriterMapEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getEStringToWriterMapEntry()
   * @generated
   */
  EAttribute getEStringToWriterMapEntry_Key();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getEStringToWriterMapEntry()
   * @generated
   */
  EReference getEStringToWriterMapEntry_Value();

  /**
   * Returns the meta object for enum '{@link org.eclipse.example.library.BookCategory <em>Book Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Book Category</em>'.
   * @see org.eclipse.example.library.BookCategory
   * @generated
   */
  EEnum getBookCategory();

  /**
   * Returns the meta object for data type '{@link java.util.Map <em>My Map Of Integers And Strings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Map Of Integers And Strings</em>'.
   * @see java.util.Map
   * @model instanceClass="java.util.Map"
   * @generated
   */
  EDataType getMyMapOfIntegersAndStrings();

  /**
   * Returns the meta object for data type '{@link java.util.Map <em>Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Map</em>'.
   * @see java.util.Map
   * @model instanceClass="java.util.Map"
   * @generated
   */
  EDataType getMap();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  LibraryFactory getLibraryFactory();

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
     * The meta object literal for the '{@link org.eclipse.example.library.impl.BookImpl <em>Book</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.example.library.impl.BookImpl
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getBook()
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
     * The meta object literal for the '<em><b>Pages</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOK__PAGES = eINSTANCE.getBook_Pages();

    /**
     * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOK__CATEGORY = eINSTANCE.getBook_Category();

    /**
     * The meta object literal for the '<em><b>Author</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BOOK__AUTHOR = eINSTANCE.getBook_Author();

    /**
     * The meta object literal for the '{@link org.eclipse.example.library.impl.LibraryImpl <em>Library</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.example.library.impl.LibraryImpl
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getLibrary()
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
     * The meta object literal for the '<em><b>Writers</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__WRITERS = eINSTANCE.getLibrary_Writers();

    /**
     * The meta object literal for the '<em><b>Books</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__BOOKS = eINSTANCE.getLibrary_Books();

    /**
     * The meta object literal for the '<em><b>Special Books</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__SPECIAL_BOOKS = eINSTANCE.getLibrary_SpecialBooks();

    /**
     * The meta object literal for the '<em><b>Book By Title Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__BOOK_BY_TITLE_MAP = eINSTANCE.getLibrary_BookByTitleMap();

    /**
     * The meta object literal for the '<em><b>Writer By Name Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__WRITER_BY_NAME_MAP = eINSTANCE.getLibrary_WriterByNameMap();

    /**
     * The meta object literal for the '<em><b>Options</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIBRARY__OPTIONS = eINSTANCE.getLibrary_Options();

    /**
     * The meta object literal for the '<em><b>Writer By ID Map</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIBRARY__WRITER_BY_ID_MAP = eINSTANCE.getLibrary_WriterByIDMap();

    /**
     * The meta object literal for the '{@link org.eclipse.example.library.impl.WriterImpl <em>Writer</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.example.library.impl.WriterImpl
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getWriter()
     * @generated
     */
    EClass WRITER = eINSTANCE.getWriter();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WRITER__NAME = eINSTANCE.getWriter_Name();

    /**
     * The meta object literal for the '<em><b>Books</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WRITER__BOOKS = eINSTANCE.getWriter_Books();

    /**
     * The meta object literal for the '{@link org.eclipse.example.library.impl.EStringToBookMapEntryImpl <em>EString To Book Map Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.example.library.impl.EStringToBookMapEntryImpl
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getEStringToBookMapEntry()
     * @generated
     */
    EClass ESTRING_TO_BOOK_MAP_ENTRY = eINSTANCE.getEStringToBookMapEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ESTRING_TO_BOOK_MAP_ENTRY__KEY = eINSTANCE.getEStringToBookMapEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ESTRING_TO_BOOK_MAP_ENTRY__VALUE = eINSTANCE.getEStringToBookMapEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.example.library.impl.EStringToWriterMapEntryImpl <em>EString To Writer Map Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.example.library.impl.EStringToWriterMapEntryImpl
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getEStringToWriterMapEntry()
     * @generated
     */
    EClass ESTRING_TO_WRITER_MAP_ENTRY = eINSTANCE.getEStringToWriterMapEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ESTRING_TO_WRITER_MAP_ENTRY__KEY = eINSTANCE.getEStringToWriterMapEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ESTRING_TO_WRITER_MAP_ENTRY__VALUE = eINSTANCE.getEStringToWriterMapEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.example.library.BookCategory <em>Book Category</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.example.library.BookCategory
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getBookCategory()
     * @generated
     */
    EEnum BOOK_CATEGORY = eINSTANCE.getBookCategory();

    /**
     * The meta object literal for the '<em>My Map Of Integers And Strings</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.Map
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getMyMapOfIntegersAndStrings()
     * @generated
     */
    EDataType MY_MAP_OF_INTEGERS_AND_STRINGS = eINSTANCE.getMyMapOfIntegersAndStrings();

    /**
     * The meta object literal for the '<em>Map</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.Map
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getMap()
     * @generated
     */
    EDataType MAP = eINSTANCE.getMap();

  }

} //LibraryPackage
