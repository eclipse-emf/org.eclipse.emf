/**
 * <copyright>
 * </copyright>
 *
 * $Id: DbpricePackageImpl.java,v 1.1 2004/11/04 05:52:46 marcelop Exp $
 */
package org.eclipse.emf.test.models.dbprice.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl;

import org.eclipse.emf.test.models.dbitem.DbitemPackage;

import org.eclipse.emf.test.models.dbitem.impl.DbitemPackageImpl;

import org.eclipse.emf.test.models.dbprice.DbpriceFactory;
import org.eclipse.emf.test.models.dbprice.DbpricePackage;
import org.eclipse.emf.test.models.dbprice.PenType;
import org.eclipse.emf.test.models.dbprice.PencilType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DbpricePackageImpl extends EPackageImpl implements DbpricePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pencilTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass penTypeEClass = null;

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
   * @see org.eclipse.emf.test.models.dbprice.DbpricePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private DbpricePackageImpl()
  {
    super(eNS_URI, DbpriceFactory.eINSTANCE);
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
  public static DbpricePackage init()
  {
    if (isInited) return (DbpricePackage)EPackage.Registry.INSTANCE.get(DbpricePackage.eNS_URI);

    // Obtain or create and register package.
    DbpricePackageImpl theDbpricePackage = (DbpricePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DbpricePackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackageImpl.init();

    // Obtain or create and register interdependencies
    DbitemPackageImpl theDbitemPackage = (DbitemPackageImpl)(EPackage.Registry.INSTANCE.get(DbitemPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.get(DbitemPackage.eNS_URI) : DbitemPackageImpl.eINSTANCE);

    // Step 1: create meta-model objects
    theDbpricePackage.createPackageContents();
    theDbitemPackage.createPackageContents();

    // Step 2: complete initialization
    theDbpricePackage.initializePackageContents();
    theDbitemPackage.initializePackageContents();

    return theDbpricePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPencilType()
  {
    return pencilTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPencilType_Price()
  {
    return (EAttribute)pencilTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPenType()
  {
    return penTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPenType_Price()
  {
    return (EAttribute)penTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbpriceFactory getDbpriceFactory()
  {
    return (DbpriceFactory)getEFactoryInstance();
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
    pencilTypeEClass = createEClass(PENCIL_TYPE);
    createEAttribute(pencilTypeEClass, PENCIL_TYPE__PRICE);

    penTypeEClass = createEClass(PEN_TYPE);
    createEAttribute(penTypeEClass, PEN_TYPE__PRICE);
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
    initEClass(pencilTypeEClass, PencilType.class, "PencilType", !IS_ABSTRACT, !IS_INTERFACE);
    initEAttribute(getPencilType_Price(), theXMLTypePackage.getInt(), "price", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED);

    initEClass(penTypeEClass, PenType.class, "PenType", !IS_ABSTRACT, !IS_INTERFACE);
    initEAttribute(getPenType_Price(), theXMLTypePackage.getInt(), "price", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED);

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
      (pencilTypeEClass, 
       source, 
       new String[] 
       {
       "name", "pencilType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getPencilType_Price(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "price"
       });		
    addAnnotation
      (penTypeEClass, 
       source, 
       new String[] 
       {
       "name", "penType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getPenType_Price(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "price",
       "namespace", "##targetNamespace"
       });
  }
} //DbpricePackageImpl
