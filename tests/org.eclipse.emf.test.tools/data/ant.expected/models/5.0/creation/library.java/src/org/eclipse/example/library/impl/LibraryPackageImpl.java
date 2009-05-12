/**
 * This is my code.
 *
 * $Id: LibraryPackageImpl.java,v 1.8 2009/05/12 15:55:26 davidms Exp $
 */
package org.eclipse.example.library.impl;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.example.library.Book;
import org.eclipse.example.library.BookCategory;
import org.eclipse.example.library.Library;
import org.eclipse.example.library.LibraryFactory;
import org.eclipse.example.library.LibraryPackage;
import org.eclipse.example.library.Writer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LibraryPackageImpl extends EPackageImpl implements LibraryPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass bookEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass libraryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass writerEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass writerNameMapEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mapOfDataTypesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum bookCategoryEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType writerNumberEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType writerIDEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType manyURIsEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType mapEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType listEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType uriEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.example.library.LibraryPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private LibraryPackageImpl()
  {
    super(eNS_URI, LibraryFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link LibraryPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static LibraryPackage init()
  {
    if (isInited) return (LibraryPackage)EPackage.Registry.INSTANCE.getEPackage(LibraryPackage.eNS_URI);

    // Obtain or create and register package
    LibraryPackageImpl theLibraryPackage = (LibraryPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LibraryPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LibraryPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theLibraryPackage.createPackageContents();

    // Initialize created meta-data
    theLibraryPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theLibraryPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(LibraryPackage.eNS_URI, theLibraryPackage);
    return theLibraryPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBook()
  {
    return bookEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBook_Title()
  {
    return (EAttribute)bookEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBook_Pages()
  {
    return (EAttribute)bookEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBook_Category()
  {
    return (EAttribute)bookEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBook_Author()
  {
    return (EReference)bookEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLibrary()
  {
    return libraryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLibrary_Name()
  {
    return (EAttribute)libraryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLibrary_Writers()
  {
    return (EReference)libraryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLibrary_Books()
  {
    return (EReference)libraryEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLibrary_SpecialBooks()
  {
    return (EReference)libraryEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLibrary_BookByTitleMap()
  {
    return (EAttribute)libraryEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLibrary_WriterByNameMap()
  {
    return (EReference)libraryEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLibrary_WriterByIDMap()
  {
    return (EReference)libraryEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLibrary_Options()
  {
    return (EAttribute)libraryEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLibrary_Map1()
  {
    return (EAttribute)libraryEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLibrary_URIs_1()
  {
    return (EAttribute)libraryEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWriter()
  {
    return writerEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWriter_Name()
  {
    return (EAttribute)writerEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWriter_Books()
  {
    return (EReference)writerEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWriterNameMap()
  {
    return writerNameMapEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWriterNameMap_Key()
  {
    return (EAttribute)writerNameMapEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWriterNameMap_Value()
  {
    return (EReference)writerNameMapEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMapOfDataTypes()
  {
    return mapOfDataTypesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMapOfDataTypes_Key()
  {
    return (EAttribute)mapOfDataTypesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMapOfDataTypes_Value()
  {
    return (EAttribute)mapOfDataTypesEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getBookCategory()
  {
    return bookCategoryEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getWriterNumber()
  {
    return writerNumberEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getWriterID()
  {
    return writerIDEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getManyURIs()
  {
    return manyURIsEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getMap()
  {
    return mapEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getList()
  {
    return listEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getURI()
  {
    return uriEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LibraryFactory getLibraryFactory()
  {
    return (LibraryFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    bookEClass = createEClass(BOOK);
    createEAttribute(bookEClass, BOOK__TITLE);
    createEAttribute(bookEClass, BOOK__PAGES);
    createEAttribute(bookEClass, BOOK__CATEGORY);
    createEReference(bookEClass, BOOK__AUTHOR);

    libraryEClass = createEClass(LIBRARY);
    createEAttribute(libraryEClass, LIBRARY__NAME);
    createEReference(libraryEClass, LIBRARY__WRITERS);
    createEReference(libraryEClass, LIBRARY__BOOKS);
    createEReference(libraryEClass, LIBRARY__SPECIAL_BOOKS);
    createEAttribute(libraryEClass, LIBRARY__BOOK_BY_TITLE_MAP);
    createEReference(libraryEClass, LIBRARY__WRITER_BY_NAME_MAP);
    createEReference(libraryEClass, LIBRARY__WRITER_BY_ID_MAP);
    createEAttribute(libraryEClass, LIBRARY__OPTIONS);
    createEAttribute(libraryEClass, LIBRARY__MAP1);
    createEAttribute(libraryEClass, LIBRARY__UR_IS_1);

    writerEClass = createEClass(WRITER);
    createEAttribute(writerEClass, WRITER__NAME);
    createEReference(writerEClass, WRITER__BOOKS);

    writerNameMapEClass = createEClass(WRITER_NAME_MAP);
    createEAttribute(writerNameMapEClass, WRITER_NAME_MAP__KEY);
    createEReference(writerNameMapEClass, WRITER_NAME_MAP__VALUE);

    mapOfDataTypesEClass = createEClass(MAP_OF_DATA_TYPES);
    createEAttribute(mapOfDataTypesEClass, MAP_OF_DATA_TYPES__KEY);
    createEAttribute(mapOfDataTypesEClass, MAP_OF_DATA_TYPES__VALUE);

    // Create enums
    bookCategoryEEnum = createEEnum(BOOK_CATEGORY);

    // Create data types
    writerNumberEDataType = createEDataType(WRITER_NUMBER);
    writerIDEDataType = createEDataType(WRITER_ID);
    manyURIsEDataType = createEDataType(MANY_UR_IS);
    mapEDataType = createEDataType(MAP);
    listEDataType = createEDataType(LIST);
    uriEDataType = createEDataType(URI);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters
    addETypeParameter(mapEDataType, "T");
    addETypeParameter(mapEDataType, "T1");
    addETypeParameter(listEDataType, "T");

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(bookEClass, Book.class, "Book", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getBook_Title(), ecorePackage.getEString(), "title", null, 0, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getBook_Pages(), ecorePackage.getEInt(), "pages", "100", 0, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getBook_Category(), this.getBookCategory(), "category", null, 0, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBook_Author(), this.getWriter(), this.getWriter_Books(), "author", null, 0, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(libraryEClass, Library.class, "Library", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLibrary_Name(), ecorePackage.getEString(), "name", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLibrary_Writers(), this.getWriter(), null, "writers", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLibrary_Books(), this.getBook(), null, "books", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLibrary_SpecialBooks(), this.getBook(), null, "specialBooks", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    EGenericType g1 = createEGenericType(this.getMap());
    EGenericType g2 = createEGenericType(ecorePackage.getEString());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(this.getBook());
    g1.getETypeArguments().add(g2);
    initEAttribute(getLibrary_BookByTitleMap(), g1, "bookByTitleMap", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLibrary_WriterByNameMap(), this.getWriterNameMap(), null, "writerByNameMap", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLibrary_WriterByIDMap(), this.getMapOfDataTypes(), null, "writerByIDMap", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getMap());
    g2 = createEGenericType(ecorePackage.getEString());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEString());
    g1.getETypeArguments().add(g2);
    initEAttribute(getLibrary_Options(), g1, "options", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getMap());
    g2 = createEGenericType(ecorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(this.getList());
    g1.getETypeArguments().add(g2);
    EGenericType g3 = createEGenericType(this.getURI());
    g2.getETypeArguments().add(g3);
    initEAttribute(getLibrary_Map1(), g1, "map1", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLibrary_URIs_1(), this.getManyURIs(), "uRIs_1", null, 0, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(writerEClass, Writer.class, "Writer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getWriter_Name(), ecorePackage.getEString(), "name", null, 0, 1, Writer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWriter_Books(), this.getBook(), this.getBook_Author(), "books", null, 0, -1, Writer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(writerNameMapEClass, Map.Entry.class, "WriterNameMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getWriterNameMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWriterNameMap_Value(), this.getWriter(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mapOfDataTypesEClass, Map.Entry.class, "MapOfDataTypes", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMapOfDataTypes_Key(), this.getWriterNumber(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMapOfDataTypes_Value(), this.getWriterID(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(bookCategoryEEnum, BookCategory.class, "BookCategory");
    addEEnumLiteral(bookCategoryEEnum, BookCategory.MYSTERY);
    addEEnumLiteral(bookCategoryEEnum, BookCategory.SCIENCE_FICTION);
    addEEnumLiteral(bookCategoryEEnum, BookCategory.BIOGRAPHY);

    // Initialize data types
    initEDataType(writerNumberEDataType, Integer.class, "WriterNumber", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(writerIDEDataType, String.class, "WriterID", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(manyURIsEDataType, List.class, "ManyURIs", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS, "java.util.List<org.eclipse.emf.common.util.URI>");
    initEDataType(mapEDataType, Map.class, "Map", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(listEDataType, List.class, "List", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(uriEDataType, org.eclipse.emf.common.util.URI.class, "URI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //LibraryPackageImpl
