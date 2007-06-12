/**
 * This is my code.
 *
 * $Id: LibraryPackage.java,v 1.6 2007/06/12 00:20:35 marcelop Exp $
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
   * The feature id for the '<em><b>Book By Title Map</b></em>' attribute.
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
   * The feature id for the '<em><b>Writer By ID Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__WRITER_BY_ID_MAP = 6;

  /**
   * The feature id for the '<em><b>Options</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__OPTIONS = 7;

  /**
   * The feature id for the '<em><b>Map1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__MAP1 = 8;

  /**
   * The feature id for the '<em><b>UR Is 1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__UR_IS_1 = 9;

  /**
   * The number of structural features of the '<em>Library</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FEATURE_COUNT = 10;

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
   * The meta object id for the '{@link org.eclipse.example.library.impl.WriterNameMapImpl <em>Writer Name Map</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.example.library.impl.WriterNameMapImpl
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getWriterNameMap()
   * @generated
   */
  int WRITER_NAME_MAP = 3;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER_NAME_MAP__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER_NAME_MAP__VALUE = 1;

  /**
   * The number of structural features of the '<em>Writer Name Map</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER_NAME_MAP_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.example.library.impl.MapOfDataTypesImpl <em>Map Of Data Types</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.example.library.impl.MapOfDataTypesImpl
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getMapOfDataTypes()
   * @generated
   */
  int MAP_OF_DATA_TYPES = 4;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAP_OF_DATA_TYPES__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAP_OF_DATA_TYPES__VALUE = 1;

  /**
   * The number of structural features of the '<em>Map Of Data Types</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAP_OF_DATA_TYPES_FEATURE_COUNT = 2;

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
   * The meta object id for the '<em>Writer Number</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Integer
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getWriterNumber()
   * @generated
   */
  int WRITER_NUMBER = 6;

  /**
   * The meta object id for the '<em>Writer ID</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getWriterID()
   * @generated
   */
  int WRITER_ID = 7;

  /**
   * The meta object id for the '<em>Many UR Is</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getManyURIs()
   * @generated
   */
  int MANY_UR_IS = 8;

  /**
   * The meta object id for the '<em>Map</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.Map
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getMap()
   * @generated
   */
  int MAP = 9;

  /**
   * The meta object id for the '<em>List</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getList()
   * @generated
   */
  int LIST = 10;

  /**
   * The meta object id for the '<em>URI</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.common.util.URI
   * @see org.eclipse.example.library.impl.LibraryPackageImpl#getURI()
   * @generated
   */
  int URI = 11;


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
   * Returns the meta object for the attribute '{@link org.eclipse.example.library.Library#getBookByTitleMap <em>Book By Title Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Book By Title Map</em>'.
   * @see org.eclipse.example.library.Library#getBookByTitleMap()
   * @see #getLibrary()
   * @generated
   */
  EAttribute getLibrary_BookByTitleMap();

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
   * Returns the meta object for the map '{@link org.eclipse.example.library.Library#getWriterByIDMap <em>Writer By ID Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Writer By ID Map</em>'.
   * @see org.eclipse.example.library.Library#getWriterByIDMap()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_WriterByIDMap();

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
   * Returns the meta object for the attribute '{@link org.eclipse.example.library.Library#getMap1 <em>Map1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Map1</em>'.
   * @see org.eclipse.example.library.Library#getMap1()
   * @see #getLibrary()
   * @generated
   */
  EAttribute getLibrary_Map1();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.example.library.Library#getURIs_1 <em>UR Is 1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>UR Is 1</em>'.
   * @see org.eclipse.example.library.Library#getURIs_1()
   * @see #getLibrary()
   * @generated
   */
  EAttribute getLibrary_URIs_1();

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
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Writer Name Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Writer Name Map</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EString"
   *        valueType="org.eclipse.example.library.Writer"
   * @generated
   */
  EClass getWriterNameMap();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getWriterNameMap()
   * @generated
   */
  EAttribute getWriterNameMap_Key();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getWriterNameMap()
   * @generated
   */
  EReference getWriterNameMap_Value();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Map Of Data Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Map Of Data Types</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.example.library.WriterNumber"
   *        valueDataType="org.eclipse.example.library.WriterID"
   * @generated
   */
  EClass getMapOfDataTypes();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getMapOfDataTypes()
   * @generated
   */
  EAttribute getMapOfDataTypes_Key();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getMapOfDataTypes()
   * @generated
   */
  EAttribute getMapOfDataTypes_Value();

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
   * Returns the meta object for data type '{@link java.lang.Integer <em>Writer Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Writer Number</em>'.
   * @see java.lang.Integer
   * @model instanceClass="java.lang.Integer"
   * @generated
   */
  EDataType getWriterNumber();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>Writer ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Writer ID</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   * @generated
   */
  EDataType getWriterID();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>Many UR Is</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Many UR Is</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List<org.eclipse.emf.common.util.URI>"
   * @generated
   */
  EDataType getManyURIs();

  /**
   * Returns the meta object for data type '{@link java.util.Map <em>Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Map</em>'.
   * @see java.util.Map
   * @model instanceClass="java.util.Map" typeParameters="T T1"
   * @generated
   */
  EDataType getMap();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>List</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List" typeParameters="T"
   * @generated
   */
  EDataType getList();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.common.util.URI <em>URI</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>URI</em>'.
   * @see org.eclipse.emf.common.util.URI
   * @model instanceClass="org.eclipse.emf.common.util.URI"
   * @generated
   */
  EDataType getURI();

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
     * The meta object literal for the '<em><b>Book By Title Map</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIBRARY__BOOK_BY_TITLE_MAP = eINSTANCE.getLibrary_BookByTitleMap();

    /**
     * The meta object literal for the '<em><b>Writer By Name Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__WRITER_BY_NAME_MAP = eINSTANCE.getLibrary_WriterByNameMap();

    /**
     * The meta object literal for the '<em><b>Writer By ID Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY__WRITER_BY_ID_MAP = eINSTANCE.getLibrary_WriterByIDMap();

    /**
     * The meta object literal for the '<em><b>Options</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIBRARY__OPTIONS = eINSTANCE.getLibrary_Options();

    /**
     * The meta object literal for the '<em><b>Map1</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIBRARY__MAP1 = eINSTANCE.getLibrary_Map1();

    /**
     * The meta object literal for the '<em><b>UR Is 1</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIBRARY__UR_IS_1 = eINSTANCE.getLibrary_URIs_1();

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
     * The meta object literal for the '{@link org.eclipse.example.library.impl.WriterNameMapImpl <em>Writer Name Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.example.library.impl.WriterNameMapImpl
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getWriterNameMap()
     * @generated
     */
    EClass WRITER_NAME_MAP = eINSTANCE.getWriterNameMap();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WRITER_NAME_MAP__KEY = eINSTANCE.getWriterNameMap_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WRITER_NAME_MAP__VALUE = eINSTANCE.getWriterNameMap_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.example.library.impl.MapOfDataTypesImpl <em>Map Of Data Types</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.example.library.impl.MapOfDataTypesImpl
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getMapOfDataTypes()
     * @generated
     */
    EClass MAP_OF_DATA_TYPES = eINSTANCE.getMapOfDataTypes();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAP_OF_DATA_TYPES__KEY = eINSTANCE.getMapOfDataTypes_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAP_OF_DATA_TYPES__VALUE = eINSTANCE.getMapOfDataTypes_Value();

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
     * The meta object literal for the '<em>Writer Number</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Integer
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getWriterNumber()
     * @generated
     */
    EDataType WRITER_NUMBER = eINSTANCE.getWriterNumber();

    /**
     * The meta object literal for the '<em>Writer ID</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getWriterID()
     * @generated
     */
    EDataType WRITER_ID = eINSTANCE.getWriterID();

    /**
     * The meta object literal for the '<em>Many UR Is</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.List
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getManyURIs()
     * @generated
     */
    EDataType MANY_UR_IS = eINSTANCE.getManyURIs();

    /**
     * The meta object literal for the '<em>Map</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.Map
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getMap()
     * @generated
     */
    EDataType MAP = eINSTANCE.getMap();

    /**
     * The meta object literal for the '<em>List</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.List
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getList()
     * @generated
     */
    EDataType LIST = eINSTANCE.getList();

    /**
     * The meta object literal for the '<em>URI</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.common.util.URI
     * @see org.eclipse.example.library.impl.LibraryPackageImpl#getURI()
     * @generated
     */
    EDataType URI = eINSTANCE.getURI();

  }

} //LibraryPackage
