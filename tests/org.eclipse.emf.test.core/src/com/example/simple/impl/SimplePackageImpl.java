/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimplePackageImpl.java,v 1.3 2004/07/29 13:54:09 marcelop Exp $
 */
package com.example.simple.impl;

import com.example.simple.Quote;
import com.example.simple.SimpleFactory;
import com.example.simple.SimplePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SimplePackageImpl extends EPackageImpl implements SimplePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass quoteEClass = null;

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
   * @see com.example.simple.SimplePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private SimplePackageImpl()
  {
    super(eNS_URI, SimpleFactory.eINSTANCE);
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
  public static SimplePackage init()
  {
    if (isInited) return (SimplePackage)EPackage.Registry.INSTANCE.getEPackage(SimplePackage.eNS_URI);

    // Obtain or create and register package
    SimplePackageImpl theSimplePackage = (SimplePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof SimplePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new SimplePackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackageImpl.init();

    // Create package meta-data objects
    theSimplePackage.createPackageContents();

    // Initialize created meta-data
    theSimplePackage.initializePackageContents();

    return theSimplePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getQuote()
  {
    return quoteEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuote_Symbol()
  {
    return (EAttribute)quoteEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuote_CompanyName()
  {
    return (EAttribute)quoteEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuote_Price()
  {
    return (EAttribute)quoteEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuote_Open1()
  {
    return (EAttribute)quoteEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuote_High()
  {
    return (EAttribute)quoteEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuote_Low()
  {
    return (EAttribute)quoteEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuote_Volume()
  {
    return (EAttribute)quoteEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuote_Change1()
  {
    return (EAttribute)quoteEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getQuote_Quotes()
  {
    return (EReference)quoteEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleFactory getSimpleFactory()
  {
    return (SimpleFactory)getEFactoryInstance();
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
    quoteEClass = createEClass(QUOTE);
    createEAttribute(quoteEClass, QUOTE__SYMBOL);
    createEAttribute(quoteEClass, QUOTE__COMPANY_NAME);
    createEAttribute(quoteEClass, QUOTE__PRICE);
    createEAttribute(quoteEClass, QUOTE__OPEN1);
    createEAttribute(quoteEClass, QUOTE__HIGH);
    createEAttribute(quoteEClass, QUOTE__LOW);
    createEAttribute(quoteEClass, QUOTE__VOLUME);
    createEAttribute(quoteEClass, QUOTE__CHANGE1);
    createEReference(quoteEClass, QUOTE__QUOTES);
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
    XMLTypePackageImpl theXMLTypePackage = (XMLTypePackageImpl)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(quoteEClass, Quote.class, "Quote", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getQuote_Symbol(), theXMLTypePackage.getString(), "symbol", null, 1, 1, Quote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getQuote_CompanyName(), theXMLTypePackage.getString(), "companyName", null, 1, 1, Quote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getQuote_Price(), theXMLTypePackage.getDecimal(), "price", null, 1, 1, Quote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getQuote_Open1(), theXMLTypePackage.getDecimal(), "open1", null, 1, 1, Quote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getQuote_High(), theXMLTypePackage.getDecimal(), "high", null, 1, 1, Quote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getQuote_Low(), theXMLTypePackage.getDecimal(), "low", null, 1, 1, Quote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getQuote_Volume(), theXMLTypePackage.getDouble(), "volume", null, 1, 1, Quote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getQuote_Change1(), theXMLTypePackage.getDouble(), "change1", null, 1, 1, Quote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getQuote_Quotes(), this.getQuote(), null, "quotes", null, 0, -1, Quote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
    addAnnotation
      (quoteEClass, 
       source, 
       new String[] 
       {
       "name", "Quote",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getQuote_Symbol(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "symbol"
       });		
    addAnnotation
      (getQuote_CompanyName(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "companyName"
       });		
    addAnnotation
      (getQuote_Price(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "price"
       });		
    addAnnotation
      (getQuote_Open1(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "open1"
       });		
    addAnnotation
      (getQuote_High(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "high"
       });		
    addAnnotation
      (getQuote_Low(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "low"
       });		
    addAnnotation
      (getQuote_Volume(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "volume"
       });		
    addAnnotation
      (getQuote_Change1(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "change1"
       });		
    addAnnotation
      (getQuote_Quotes(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "quotes"
       });
  }

} //SimplePackageImpl
