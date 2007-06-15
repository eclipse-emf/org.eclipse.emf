/**
 * This is my code.
 *
 * $Id: LibraryPackageImpl.java,v 1.3 2007/06/15 21:22:33 emerks Exp $
 */
package org.examples.library.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.examples.library.Book;
import org.examples.library.BookCategory;
import org.examples.library.Library;
import org.examples.library.LibraryFactory;
import org.examples.library.LibraryPackage;
import org.examples.library.Writer;

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
  private EEnum bookCategoryEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType bookCategoryObjectEDataType = null;

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
   * @see org.examples.library.LibraryPackage#eNS_URI
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
   * Creates, registers, and initializes the <b>Package</b> for this
   * model, and for any others upon which it depends.  Simple
   * dependencies are satisfied by calling this method on all
   * dependent packages before doing anything else.  This method drives
   * initialization for interdependent packages directly, in parallel
   * with this package, itself.
   * <p>Of this package and its interdependencies, all packages which
   * have not yet been registered by their URI values are first created
   * and registered.  The packages are then initialized in two steps:
   * meta-model objects for all of the packages are created before any
   * are initialized, since one package's meta-model objects may refer to
   * those of another.
   * <p>Invocation of this method will not affect any packages that have
   * already been initialized.
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
    LibraryPackageImpl theLibraryPackage = (LibraryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof LibraryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new LibraryPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theLibraryPackage.createPackageContents();

    // Initialize created meta-data
    theLibraryPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theLibraryPackage.freeze();

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
  public EAttribute getLibrary_Site()
  {
    return (EAttribute)libraryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLibrary_Writers()
  {
    return (EReference)libraryEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLibrary_Books()
  {
    return (EReference)libraryEClass.getEStructuralFeatures().get(3);
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
  public EEnum getBookCategory()
  {
    return bookCategoryEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getBookCategoryObject()
  {
    return bookCategoryObjectEDataType;
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
    createEAttribute(libraryEClass, LIBRARY__SITE);
    createEReference(libraryEClass, LIBRARY__WRITERS);
    createEReference(libraryEClass, LIBRARY__BOOKS);

    writerEClass = createEClass(WRITER);
    createEAttribute(writerEClass, WRITER__NAME);
    createEReference(writerEClass, WRITER__BOOKS);

    // Create enums
    bookCategoryEEnum = createEEnum(BOOK_CATEGORY);

    // Create data types
    bookCategoryObjectEDataType = createEDataType(BOOK_CATEGORY_OBJECT);
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

    // Obtain other dependent packages
    XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(bookEClass, Book.class, "Book", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getBook_Title(), theXMLTypePackage.getString(), "title", null, 1, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getBook_Pages(), theXMLTypePackage.getInt(), "pages", null, 1, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getBook_Category(), this.getBookCategory(), "category", "Mystery", 1, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEReference(getBook_Author(), this.getWriter(), this.getWriter_Books(), "author", null, 1, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(libraryEClass, Library.class, "Library", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getLibrary_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getLibrary_Site(), theXMLTypePackage.getString(), "site", null, 1, 1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLibrary_Writers(), this.getWriter(), null, "writers", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getLibrary_Books(), this.getBook(), null, "books", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(writerEClass, Writer.class, "Writer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getWriter_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Writer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getWriter_Books(), this.getBook(), this.getBook_Author(), "books", null, 0, -1, Writer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(bookCategoryEEnum, BookCategory.class, "BookCategory"); //$NON-NLS-1$
    addEEnumLiteral(bookCategoryEEnum, BookCategory.MYSTERY_LITERAL);
    addEEnumLiteral(bookCategoryEEnum, BookCategory.SCIENCE_FICTION_LITERAL);
    addEEnumLiteral(bookCategoryEEnum, BookCategory.BIOGRAPHY_LITERAL);
    addEEnumLiteral(bookCategoryEEnum, BookCategory.IT_LITERAL);

    // Initialize data types
    initEDataType(bookCategoryObjectEDataType, BookCategory.class, "BookCategoryObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
    createExtendedMetaDataAnnotations();
  }

  /**
   * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createExtendedMetaDataAnnotations()
  {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$		
    addAnnotation
      (bookEClass, 
       source, 
       new String[] 
       {
       "name", "Book", //$NON-NLS-1$ //$NON-NLS-2$
       "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (getBook_Title(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "title" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (getBook_Pages(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "pages" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (getBook_Category(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "category" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (getBook_Author(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "author" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (bookCategoryEEnum, 
       source, 
       new String[] 
       {
       "name", "BookCategory" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (bookCategoryObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "BookCategory:Object", //$NON-NLS-1$ //$NON-NLS-2$
       "baseType", "BookCategory" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (libraryEClass, 
       source, 
       new String[] 
       {
       "name", "Library", //$NON-NLS-1$ //$NON-NLS-2$
       "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (getLibrary_Name(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (getLibrary_Site(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "site" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (getLibrary_Writers(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "writers" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (getLibrary_Books(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "books" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (writerEClass, 
       source, 
       new String[] 
       {
       "name", "Writer", //$NON-NLS-1$ //$NON-NLS-2$
       "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (getWriter_Name(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
       });		
    addAnnotation
      (getWriter_Books(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "books" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

} //LibraryPackageImpl
