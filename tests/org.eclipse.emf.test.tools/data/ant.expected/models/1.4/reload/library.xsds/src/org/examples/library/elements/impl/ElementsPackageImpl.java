/**
 * This is my code.
 *
 * $Id: ElementsPackageImpl.java,v 1.7 2009/05/12 15:55:26 davidms Exp $
 */
package org.examples.library.elements.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.examples.library.LibraryPackage;

import org.examples.library.elements.Book;
import org.examples.library.elements.BookCategory;
import org.examples.library.elements.ElementsFactory;
import org.examples.library.elements.ElementsPackage;
import org.examples.library.elements.Writer;

import org.examples.library.elements.util.ElementsValidator;
import org.examples.library.hr.HrPackage;
import org.examples.library.hr.impl.HrPackageImpl;
import org.examples.library.impl.LibraryPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ElementsPackageImpl extends EPackageImpl implements ElementsPackage
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType uuidEDataType = null;

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
   * @see org.examples.library.elements.ElementsPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ElementsPackageImpl()
  {
    super(eNS_URI, ElementsFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link ElementsPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ElementsPackage init()
  {
    if (isInited) return (ElementsPackage)EPackage.Registry.INSTANCE.getEPackage(ElementsPackage.eNS_URI);

    // Obtain or create and register package
    ElementsPackageImpl theElementsPackage = (ElementsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ElementsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ElementsPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Obtain or create and register interdependencies
    HrPackageImpl theHrPackage = (HrPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(HrPackage.eNS_URI) instanceof HrPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(HrPackage.eNS_URI) : HrPackage.eINSTANCE);
    LibraryPackageImpl theLibraryPackage = (LibraryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LibraryPackage.eNS_URI) instanceof LibraryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LibraryPackage.eNS_URI) : LibraryPackage.eINSTANCE);

    // Create package meta-data objects
    theElementsPackage.createPackageContents();
    theHrPackage.createPackageContents();
    theLibraryPackage.createPackageContents();

    // Initialize created meta-data
    theElementsPackage.initializePackageContents();
    theHrPackage.initializePackageContents();
    theLibraryPackage.initializePackageContents();

    // Register package validator
    EValidator.Registry.INSTANCE.put
      (theElementsPackage, 
       new EValidator.Descriptor()
       {
         public EValidator getEValidator()
         {
           return ElementsValidator.INSTANCE;
         }
       });

    // Mark meta-data to indicate it can't be changed
    theElementsPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ElementsPackage.eNS_URI, theElementsPackage);
    return theElementsPackage;
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
  public EAttribute getBook_Uuid()
  {
    return (EAttribute)bookEClass.getEStructuralFeatures().get(4);
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
  public EReference getWriter_Books()
  {
    return (EReference)writerEClass.getEStructuralFeatures().get(0);
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
  public EDataType getUUID()
  {
    return uuidEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementsFactory getElementsFactory()
  {
    return (ElementsFactory)getEFactoryInstance();
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
    createEAttribute(bookEClass, BOOK__UUID);

    writerEClass = createEClass(WRITER);
    createEReference(writerEClass, WRITER__BOOKS);

    // Create enums
    bookCategoryEEnum = createEEnum(BOOK_CATEGORY);

    // Create data types
    bookCategoryObjectEDataType = createEDataType(BOOK_CATEGORY_OBJECT);
    uuidEDataType = createEDataType(UUID);
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
    HrPackage theHrPackage = (HrPackage)EPackage.Registry.INSTANCE.getEPackage(HrPackage.eNS_URI);

    // Add supertypes to classes
    writerEClass.getESuperTypes().add(theHrPackage.getPerson());

    // Initialize classes and features; add operations and parameters
    initEClass(bookEClass, Book.class, "Book", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getBook_Title(), theXMLTypePackage.getString(), "title", null, 1, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getBook_Pages(), theXMLTypePackage.getInt(), "pages", null, 1, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getBook_Category(), this.getBookCategory(), "category", null, 1, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBook_Author(), ecorePackage.getEObject(), null, "author", null, 1, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getBook_Uuid(), this.getUUID(), "uuid", null, 0, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(writerEClass, Writer.class, "Writer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getWriter_Books(), ecorePackage.getEObject(), null, "books", null, 0, -1, Writer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(bookCategoryEEnum, BookCategory.class, "BookCategory"); //$NON-NLS-1$
    addEEnumLiteral(bookCategoryEEnum, BookCategory.MYSTERY_LITERAL);
    addEEnumLiteral(bookCategoryEEnum, BookCategory.SCIENCE_FICTION_LITERAL);
    addEEnumLiteral(bookCategoryEEnum, BookCategory.BIOGRAPHY_LITERAL);
    addEEnumLiteral(bookCategoryEEnum, BookCategory.IT_LITERAL);

    // Initialize data types
    initEDataType(bookCategoryObjectEDataType, BookCategory.class, "BookCategoryObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEDataType(uuidEDataType, byte[].class, "UUID", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

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
      (getBook_Uuid(), 
       source, 
       new String[] 
       {
       "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "uuid" //$NON-NLS-1$ //$NON-NLS-2$
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
      (uuidEDataType, 
       source, 
       new String[] 
       {
       "name", "UUID", //$NON-NLS-1$ //$NON-NLS-2$
       "baseType", "http://www.eclipse.org/emf/2003/XMLType#hexBinary", //$NON-NLS-1$ //$NON-NLS-2$
       "length", "16" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getWriter_Books(), 
       source, 
       new String[] 
       {
       "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
       "name", "books" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

} //ElementsPackageImpl
