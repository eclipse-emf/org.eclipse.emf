/**
 * <copyright>
 * </copyright>
 *
 * $Id: TypesPackageImpl.java,v 1.1 2004/11/03 23:07:10 marcelop Exp $
 */
package org.eclipse.emf.test.core.sdo.types.model.types.impl;

import java.util.Date;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl;

import org.eclipse.emf.test.core.sdo.types.model.types.AThing;
import org.eclipse.emf.test.core.sdo.types.model.types.TypesFactory;
import org.eclipse.emf.test.core.sdo.types.model.types.TypesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypesPackageImpl extends EPackageImpl implements TypesPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass aThingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType myBytesEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType myCharEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType myCharObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType myDateEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType myNumberEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType myObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType myThreadEDataType = null;

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
   * @see org.eclipse.emf.test.core.sdo.types.model.types.TypesPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private TypesPackageImpl()
  {
    super(eNS_URI, TypesFactory.eINSTANCE);
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
  public static TypesPackage init()
  {
    if (isInited) return (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

    // Obtain or create and register package
    TypesPackageImpl theTypesPackage = (TypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new TypesPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackageImpl.init();

    // Create package meta-data objects
    theTypesPackage.createPackageContents();

    // Initialize created meta-data
    theTypesPackage.initializePackageContents();

    return theTypesPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAThing()
  {
    return aThingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ABoolean()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_AByte()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ADecimal()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ADate()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ABytes()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ANumber()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_AObject()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_AThread()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyBoolean()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyByte()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(17);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyDecimal()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(18);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyFloat()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(19);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyDouble()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(20);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyInt()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(21);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyInteger()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(22);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyLong()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(23);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyShort()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(24);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyString()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(25);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyChar()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(26);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyDate()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(27);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyBytes()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(28);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyNumber()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(29);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyObject()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(30);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ManyThread()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(31);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getMyBytes()
  {
    return myBytesEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getMyChar()
  {
    return myCharEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getMyCharObject()
  {
    return myCharObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getMyDate()
  {
    return myDateEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getMyNumber()
  {
    return myNumberEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getMyObject()
  {
    return myObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getMyThread()
  {
    return myThreadEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_AFloat()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ADouble()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_AInt()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_AInteger()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_ALong()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_AShort()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_AString()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAThing_AChar()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypesFactory getTypesFactory()
  {
    return (TypesFactory)getEFactoryInstance();
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
    aThingEClass = createEClass(ATHING);
    createEAttribute(aThingEClass, ATHING__ABOOLEAN);
    createEAttribute(aThingEClass, ATHING__ABYTE);
    createEAttribute(aThingEClass, ATHING__ADECIMAL);
    createEAttribute(aThingEClass, ATHING__AFLOAT);
    createEAttribute(aThingEClass, ATHING__ADOUBLE);
    createEAttribute(aThingEClass, ATHING__AINT);
    createEAttribute(aThingEClass, ATHING__AINTEGER);
    createEAttribute(aThingEClass, ATHING__ALONG);
    createEAttribute(aThingEClass, ATHING__ASHORT);
    createEAttribute(aThingEClass, ATHING__ASTRING);
    createEAttribute(aThingEClass, ATHING__ACHAR);
    createEAttribute(aThingEClass, ATHING__ADATE);
    createEAttribute(aThingEClass, ATHING__ABYTES);
    createEAttribute(aThingEClass, ATHING__ANUMBER);
    createEAttribute(aThingEClass, ATHING__AOBJECT);
    createEAttribute(aThingEClass, ATHING__ATHREAD);
    createEAttribute(aThingEClass, ATHING__MANY_BOOLEAN);
    createEAttribute(aThingEClass, ATHING__MANY_BYTE);
    createEAttribute(aThingEClass, ATHING__MANY_DECIMAL);
    createEAttribute(aThingEClass, ATHING__MANY_FLOAT);
    createEAttribute(aThingEClass, ATHING__MANY_DOUBLE);
    createEAttribute(aThingEClass, ATHING__MANY_INT);
    createEAttribute(aThingEClass, ATHING__MANY_INTEGER);
    createEAttribute(aThingEClass, ATHING__MANY_LONG);
    createEAttribute(aThingEClass, ATHING__MANY_SHORT);
    createEAttribute(aThingEClass, ATHING__MANY_STRING);
    createEAttribute(aThingEClass, ATHING__MANY_CHAR);
    createEAttribute(aThingEClass, ATHING__MANY_DATE);
    createEAttribute(aThingEClass, ATHING__MANY_BYTES);
    createEAttribute(aThingEClass, ATHING__MANY_NUMBER);
    createEAttribute(aThingEClass, ATHING__MANY_OBJECT);
    createEAttribute(aThingEClass, ATHING__MANY_THREAD);

    // Create data types
    myBytesEDataType = createEDataType(MY_BYTES);
    myCharEDataType = createEDataType(MY_CHAR);
    myCharObjectEDataType = createEDataType(MY_CHAR_OBJECT);
    myDateEDataType = createEDataType(MY_DATE);
    myNumberEDataType = createEDataType(MY_NUMBER);
    myObjectEDataType = createEDataType(MY_OBJECT);
    myThreadEDataType = createEDataType(MY_THREAD);
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
    initEClass(aThingEClass, AThing.class, "AThing", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAThing_ABoolean(), theXMLTypePackage.getBoolean(), "aBoolean", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_AByte(), theXMLTypePackage.getByte(), "aByte", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ADecimal(), theXMLTypePackage.getDecimal(), "aDecimal", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_AFloat(), theXMLTypePackage.getFloat(), "aFloat", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ADouble(), theXMLTypePackage.getDouble(), "aDouble", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_AInt(), theXMLTypePackage.getInt(), "aInt", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_AInteger(), theXMLTypePackage.getInteger(), "aInteger", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ALong(), theXMLTypePackage.getLong(), "aLong", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_AShort(), theXMLTypePackage.getShort(), "aShort", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_AString(), theXMLTypePackage.getString(), "aString", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_AChar(), this.getMyChar(), "aChar", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ADate(), this.getMyDate(), "aDate", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ABytes(), this.getMyBytes(), "aBytes", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ANumber(), this.getMyNumber(), "aNumber", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_AObject(), this.getMyObject(), "aObject", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_AThread(), this.getMyThread(), "aThread", null, 1, 1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyBoolean(), theXMLTypePackage.getBoolean(), "manyBoolean", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyByte(), theXMLTypePackage.getByte(), "manyByte", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyDecimal(), theXMLTypePackage.getDecimal(), "manyDecimal", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyFloat(), theXMLTypePackage.getFloat(), "manyFloat", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyDouble(), theXMLTypePackage.getDouble(), "manyDouble", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyInt(), theXMLTypePackage.getInt(), "manyInt", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyInteger(), theXMLTypePackage.getInteger(), "manyInteger", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyLong(), theXMLTypePackage.getLong(), "manyLong", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyShort(), theXMLTypePackage.getShort(), "manyShort", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyString(), theXMLTypePackage.getString(), "manyString", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyChar(), this.getMyChar(), "manyChar", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyDate(), this.getMyDate(), "manyDate", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyBytes(), this.getMyBytes(), "manyBytes", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyNumber(), this.getMyNumber(), "manyNumber", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyObject(), this.getMyObject(), "manyObject", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAThing_ManyThread(), this.getMyThread(), "manyThread", null, 1, -1, AThing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize data types
    initEDataType(myBytesEDataType, byte[].class, "MyBytes", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(myCharEDataType, char.class, "MyChar", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(myCharObjectEDataType, Character.class, "MyCharObject", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(myDateEDataType, Date.class, "MyDate", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(myNumberEDataType, Number.class, "MyNumber", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(myObjectEDataType, Object.class, "MyObject", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(myThreadEDataType, Thread.class, "MyThread", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

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
      (aThingEClass, 
       source, 
       new String[] 
       {
       "name", "AThing",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getAThing_ABoolean(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aBoolean"
       });		
    addAnnotation
      (getAThing_AByte(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aByte"
       });		
    addAnnotation
      (getAThing_ADecimal(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aDecimal"
       });		
    addAnnotation
      (getAThing_AFloat(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aFloat"
       });		
    addAnnotation
      (getAThing_ADouble(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aDouble"
       });		
    addAnnotation
      (getAThing_AInt(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aInt"
       });		
    addAnnotation
      (getAThing_AInteger(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aInteger"
       });		
    addAnnotation
      (getAThing_ALong(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aLong"
       });		
    addAnnotation
      (getAThing_AShort(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aShort"
       });		
    addAnnotation
      (getAThing_AString(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aString"
       });		
    addAnnotation
      (getAThing_AChar(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aChar"
       });		
    addAnnotation
      (getAThing_ADate(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aDate"
       });		
    addAnnotation
      (getAThing_ABytes(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aBytes"
       });		
    addAnnotation
      (getAThing_ANumber(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aNumber"
       });		
    addAnnotation
      (getAThing_AObject(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aObject"
       });		
    addAnnotation
      (getAThing_AThread(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "aThread"
       });		
    addAnnotation
      (getAThing_ManyBoolean(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyBoolean"
       });		
    addAnnotation
      (getAThing_ManyByte(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyByte"
       });		
    addAnnotation
      (getAThing_ManyDecimal(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyDecimal"
       });		
    addAnnotation
      (getAThing_ManyFloat(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyFloat"
       });		
    addAnnotation
      (getAThing_ManyDouble(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyDouble"
       });		
    addAnnotation
      (getAThing_ManyInt(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyInt"
       });		
    addAnnotation
      (getAThing_ManyInteger(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyInteger"
       });		
    addAnnotation
      (getAThing_ManyLong(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyLong"
       });		
    addAnnotation
      (getAThing_ManyShort(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyShort"
       });		
    addAnnotation
      (getAThing_ManyString(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyString"
       });		
    addAnnotation
      (getAThing_ManyChar(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyChar"
       });		
    addAnnotation
      (getAThing_ManyDate(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyDate"
       });		
    addAnnotation
      (getAThing_ManyBytes(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyBytes"
       });		
    addAnnotation
      (getAThing_ManyNumber(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyNumber"
       });		
    addAnnotation
      (getAThing_ManyObject(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyObject"
       });		
    addAnnotation
      (getAThing_ManyThread(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "manyThread"
       });		
    addAnnotation
      (myBytesEDataType, 
       source, 
       new String[] 
       {
       "name", "myBytes"
       });		
    addAnnotation
      (myCharEDataType, 
       source, 
       new String[] 
       {
       "name", "myChar"
       });		
    addAnnotation
      (myCharObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "myChar:Object",
       "baseType", "myChar"
       });		
    addAnnotation
      (myDateEDataType, 
       source, 
       new String[] 
       {
       "name", "myDate"
       });		
    addAnnotation
      (myNumberEDataType, 
       source, 
       new String[] 
       {
       "name", "myNumber"
       });		
    addAnnotation
      (myObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "myObject"
       });		
    addAnnotation
      (myThreadEDataType, 
       source, 
       new String[] 
       {
       "name", "myThread"
       });
  }

} //TypesPackageImpl
