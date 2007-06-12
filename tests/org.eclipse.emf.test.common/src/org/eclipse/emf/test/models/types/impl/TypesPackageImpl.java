/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: TypesPackageImpl.java,v 1.3 2007/06/12 15:08:09 emerks Exp $
 */
package org.eclipse.emf.test.models.types.impl;

import java.util.Date;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.types.AThing;
import org.eclipse.emf.test.models.types.TypesFactory;

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
 * @see org.eclipse.emf.test.models.types.TypesFactory
 * @model kind="package"
 * @generated
 */
public class TypesPackageImpl extends EPackageImpl
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNAME = "types";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_URI = "http:///org.eclipse.emf.test.models/types";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_PREFIX = "types";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final TypesPackageImpl eINSTANCE = org.eclipse.emf.test.models.types.impl.TypesPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.types.impl.AThingImpl <em>AThing</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.types.impl.AThingImpl
   * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getAThing()
   * @generated
   */
  public static final int ATHING = 0;

  /**
   * The feature id for the '<em><b>ABoolean</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ABOOLEAN = 0;

  /**
   * The feature id for the '<em><b>AByte</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ABYTE = 1;

  /**
   * The feature id for the '<em><b>ADecimal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ADECIMAL = 2;

  /**
   * The feature id for the '<em><b>AFloat</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__AFLOAT = 3;

  /**
   * The feature id for the '<em><b>ADouble</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ADOUBLE = 4;

  /**
   * The feature id for the '<em><b>AInt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__AINT = 5;

  /**
   * The feature id for the '<em><b>AInteger</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__AINTEGER = 6;

  /**
   * The feature id for the '<em><b>ALong</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ALONG = 7;

  /**
   * The feature id for the '<em><b>AShort</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ASHORT = 8;

  /**
   * The feature id for the '<em><b>AString</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ASTRING = 9;

  /**
   * The feature id for the '<em><b>AChar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ACHAR = 10;

  /**
   * The feature id for the '<em><b>ADate</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ADATE = 11;

  /**
   * The feature id for the '<em><b>ABytes</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ABYTES = 12;

  /**
   * The feature id for the '<em><b>ANumber</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ANUMBER = 13;

  /**
   * The feature id for the '<em><b>AObject</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__AOBJECT = 14;

  /**
   * The feature id for the '<em><b>AThread</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__ATHREAD = 15;

  /**
   * The feature id for the '<em><b>Many Boolean</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_BOOLEAN = 16;

  /**
   * The feature id for the '<em><b>Many Byte</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_BYTE = 17;

  /**
   * The feature id for the '<em><b>Many Decimal</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_DECIMAL = 18;

  /**
   * The feature id for the '<em><b>Many Float</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_FLOAT = 19;

  /**
   * The feature id for the '<em><b>Many Double</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_DOUBLE = 20;

  /**
   * The feature id for the '<em><b>Many Int</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_INT = 21;

  /**
   * The feature id for the '<em><b>Many Integer</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_INTEGER = 22;

  /**
   * The feature id for the '<em><b>Many Long</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_LONG = 23;

  /**
   * The feature id for the '<em><b>Many Short</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_SHORT = 24;

  /**
   * The feature id for the '<em><b>Many String</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_STRING = 25;

  /**
   * The feature id for the '<em><b>Many Char</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_CHAR = 26;

  /**
   * The feature id for the '<em><b>Many Date</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_DATE = 27;

  /**
   * The feature id for the '<em><b>Many Bytes</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_BYTES = 28;

  /**
   * The feature id for the '<em><b>Many Number</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_NUMBER = 29;

  /**
   * The feature id for the '<em><b>Many Object</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_OBJECT = 30;

  /**
   * The feature id for the '<em><b>Many Thread</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING__MANY_THREAD = 31;

  /**
   * The number of structural features of the '<em>AThing</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ATHING_FEATURE_COUNT = 32;

  /**
   * The meta object id for the '<em>My Bytes</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyBytes()
   * @generated
   */
  public static final int MY_BYTES = 1;

  /**
   * The meta object id for the '<em>My Char</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyChar()
   * @generated
   */
  public static final int MY_CHAR = 2;

  /**
   * The meta object id for the '<em>My Char Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Character
   * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyCharObject()
   * @generated
   */
  public static final int MY_CHAR_OBJECT = 3;

  /**
   * The meta object id for the '<em>My Date</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.Date
   * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyDate()
   * @generated
   */
  public static final int MY_DATE = 4;

  /**
   * The meta object id for the '<em>My Number</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Number
   * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyNumber()
   * @generated
   */
  public static final int MY_NUMBER = 5;

  /**
   * The meta object id for the '<em>My Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyObject()
   * @generated
   */
  public static final int MY_OBJECT = 6;

  /**
   * The meta object id for the '<em>My Thread</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Thread
   * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyThread()
   * @generated
   */
  public static final int MY_THREAD = 7;

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
   * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#eNS_URI
   * @see #init()
   * @generated
   */
  private TypesPackageImpl()
  {
    super(eNS_URI, ((EFactory)TypesFactory.INSTANCE));
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
  public static TypesPackageImpl init()
  {
    if (isInited) return (TypesPackageImpl)EPackage.Registry.INSTANCE.getEPackage(TypesPackageImpl.eNS_URI);

    // Obtain or create and register package
    TypesPackageImpl theTypesPackageImpl = (TypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new TypesPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theTypesPackageImpl.createPackageContents();

    // Initialize created meta-data
    theTypesPackageImpl.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theTypesPackageImpl.freeze();

    return theTypesPackageImpl;
  }


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.types.AThing <em>AThing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>AThing</em>'.
   * @see org.eclipse.emf.test.models.types.AThing
   * @generated
   */
  public EClass getAThing()
  {
    return aThingEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#isABoolean <em>ABoolean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ABoolean</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#isABoolean()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ABoolean()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getAByte <em>AByte</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AByte</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getAByte()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_AByte()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getADecimal <em>ADecimal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ADecimal</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getADecimal()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ADecimal()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getAFloat <em>AFloat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AFloat</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getAFloat()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_AFloat()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getADouble <em>ADouble</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ADouble</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getADouble()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ADouble()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getAInt <em>AInt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AInt</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getAInt()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_AInt()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(5);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getAInteger <em>AInteger</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AInteger</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getAInteger()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_AInteger()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(6);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getALong <em>ALong</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ALong</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getALong()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ALong()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(7);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getAShort <em>AShort</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AShort</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getAShort()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_AShort()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(8);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getAString <em>AString</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AString</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getAString()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_AString()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(9);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getAChar <em>AChar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AChar</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getAChar()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_AChar()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(10);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getADate <em>ADate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ADate</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getADate()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ADate()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(11);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getABytes <em>ABytes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ABytes</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getABytes()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ABytes()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(12);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getANumber <em>ANumber</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ANumber</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getANumber()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ANumber()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(13);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getAObject <em>AObject</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AObject</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getAObject()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_AObject()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(14);
  }

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.types.AThing#getAThread <em>AThread</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AThread</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getAThread()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_AThread()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(15);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyBoolean <em>Many Boolean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Boolean</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyBoolean()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyBoolean()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(16);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyByte <em>Many Byte</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Byte</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyByte()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyByte()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(17);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyDecimal <em>Many Decimal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Decimal</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyDecimal()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyDecimal()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(18);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyFloat <em>Many Float</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Float</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyFloat()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyFloat()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(19);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyDouble <em>Many Double</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Double</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyDouble()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyDouble()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(20);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyInt <em>Many Int</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Int</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyInt()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyInt()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(21);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyInteger <em>Many Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Integer</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyInteger()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyInteger()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(22);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyLong <em>Many Long</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Long</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyLong()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyLong()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(23);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyShort <em>Many Short</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Short</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyShort()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyShort()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(24);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyString <em>Many String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many String</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyString()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyString()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(25);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyChar <em>Many Char</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Char</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyChar()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyChar()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(26);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyDate <em>Many Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Date</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyDate()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyDate()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(27);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyBytes <em>Many Bytes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Bytes</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyBytes()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyBytes()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(28);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyNumber <em>Many Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Number</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyNumber()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyNumber()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(29);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyObject <em>Many Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Object</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyObject()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyObject()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(30);
  }

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.types.AThing#getManyThread <em>Many Thread</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Many Thread</em>'.
   * @see org.eclipse.emf.test.models.types.AThing#getManyThread()
   * @see #getAThing()
   * @generated
   */
  public EAttribute getAThing_ManyThread()
  {
    return (EAttribute)aThingEClass.getEStructuralFeatures().get(31);
  }

  /**
   * Returns the meta object for data type '<em>My Bytes</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Bytes</em>'.
   * @model instanceClass="byte[]"
   *        extendedMetaData="name='myBytes'"
   * @generated
   */
  public EDataType getMyBytes()
  {
    return myBytesEDataType;
  }

  /**
   * Returns the meta object for data type '<em>My Char</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Char</em>'.
   * @model instanceClass="char"
   *        extendedMetaData="name='myChar'"
   * @generated
   */
  public EDataType getMyChar()
  {
    return myCharEDataType;
  }

  /**
   * Returns the meta object for data type '{@link java.lang.Character <em>My Char Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Char Object</em>'.
   * @see java.lang.Character
   * @model instanceClass="java.lang.Character"
   *        extendedMetaData="name='myChar:Object' baseType='myChar'"
   * @generated
   */
  public EDataType getMyCharObject()
  {
    return myCharObjectEDataType;
  }

  /**
   * Returns the meta object for data type '{@link java.util.Date <em>My Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Date</em>'.
   * @see java.util.Date
   * @model instanceClass="java.util.Date"
   *        extendedMetaData="name='myDate'"
   * @generated
   */
  public EDataType getMyDate()
  {
    return myDateEDataType;
  }

  /**
   * Returns the meta object for data type '{@link java.lang.Number <em>My Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Number</em>'.
   * @see java.lang.Number
   * @model instanceClass="java.lang.Number"
   *        extendedMetaData="name='myNumber'"
   * @generated
   */
  public EDataType getMyNumber()
  {
    return myNumberEDataType;
  }

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>My Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Object</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='myObject'"
   * @generated
   */
  public EDataType getMyObject()
  {
    return myObjectEDataType;
  }

  /**
   * Returns the meta object for data type '{@link java.lang.Thread <em>My Thread</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Thread</em>'.
   * @see java.lang.Thread
   * @model instanceClass="java.lang.Thread"
   *        extendedMetaData="name='myThread'"
   * @generated
   */
  public EDataType getMyThread()
  {
    return myThreadEDataType;
  }

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
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
    XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

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
  public interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.types.impl.AThingImpl <em>AThing</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.types.impl.AThingImpl
     * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getAThing()
     * @generated
     */
    public static final EClass ATHING = eINSTANCE.getAThing();

    /**
     * The meta object literal for the '<em><b>ABoolean</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ABOOLEAN = eINSTANCE.getAThing_ABoolean();

    /**
     * The meta object literal for the '<em><b>AByte</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ABYTE = eINSTANCE.getAThing_AByte();

    /**
     * The meta object literal for the '<em><b>ADecimal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ADECIMAL = eINSTANCE.getAThing_ADecimal();

    /**
     * The meta object literal for the '<em><b>AFloat</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__AFLOAT = eINSTANCE.getAThing_AFloat();

    /**
     * The meta object literal for the '<em><b>ADouble</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ADOUBLE = eINSTANCE.getAThing_ADouble();

    /**
     * The meta object literal for the '<em><b>AInt</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__AINT = eINSTANCE.getAThing_AInt();

    /**
     * The meta object literal for the '<em><b>AInteger</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__AINTEGER = eINSTANCE.getAThing_AInteger();

    /**
     * The meta object literal for the '<em><b>ALong</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ALONG = eINSTANCE.getAThing_ALong();

    /**
     * The meta object literal for the '<em><b>AShort</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ASHORT = eINSTANCE.getAThing_AShort();

    /**
     * The meta object literal for the '<em><b>AString</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ASTRING = eINSTANCE.getAThing_AString();

    /**
     * The meta object literal for the '<em><b>AChar</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ACHAR = eINSTANCE.getAThing_AChar();

    /**
     * The meta object literal for the '<em><b>ADate</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ADATE = eINSTANCE.getAThing_ADate();

    /**
     * The meta object literal for the '<em><b>ABytes</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ABYTES = eINSTANCE.getAThing_ABytes();

    /**
     * The meta object literal for the '<em><b>ANumber</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ANUMBER = eINSTANCE.getAThing_ANumber();

    /**
     * The meta object literal for the '<em><b>AObject</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__AOBJECT = eINSTANCE.getAThing_AObject();

    /**
     * The meta object literal for the '<em><b>AThread</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__ATHREAD = eINSTANCE.getAThing_AThread();

    /**
     * The meta object literal for the '<em><b>Many Boolean</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_BOOLEAN = eINSTANCE.getAThing_ManyBoolean();

    /**
     * The meta object literal for the '<em><b>Many Byte</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_BYTE = eINSTANCE.getAThing_ManyByte();

    /**
     * The meta object literal for the '<em><b>Many Decimal</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_DECIMAL = eINSTANCE.getAThing_ManyDecimal();

    /**
     * The meta object literal for the '<em><b>Many Float</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_FLOAT = eINSTANCE.getAThing_ManyFloat();

    /**
     * The meta object literal for the '<em><b>Many Double</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_DOUBLE = eINSTANCE.getAThing_ManyDouble();

    /**
     * The meta object literal for the '<em><b>Many Int</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_INT = eINSTANCE.getAThing_ManyInt();

    /**
     * The meta object literal for the '<em><b>Many Integer</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_INTEGER = eINSTANCE.getAThing_ManyInteger();

    /**
     * The meta object literal for the '<em><b>Many Long</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_LONG = eINSTANCE.getAThing_ManyLong();

    /**
     * The meta object literal for the '<em><b>Many Short</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_SHORT = eINSTANCE.getAThing_ManyShort();

    /**
     * The meta object literal for the '<em><b>Many String</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_STRING = eINSTANCE.getAThing_ManyString();

    /**
     * The meta object literal for the '<em><b>Many Char</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_CHAR = eINSTANCE.getAThing_ManyChar();

    /**
     * The meta object literal for the '<em><b>Many Date</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_DATE = eINSTANCE.getAThing_ManyDate();

    /**
     * The meta object literal for the '<em><b>Many Bytes</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_BYTES = eINSTANCE.getAThing_ManyBytes();

    /**
     * The meta object literal for the '<em><b>Many Number</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_NUMBER = eINSTANCE.getAThing_ManyNumber();

    /**
     * The meta object literal for the '<em><b>Many Object</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_OBJECT = eINSTANCE.getAThing_ManyObject();

    /**
     * The meta object literal for the '<em><b>Many Thread</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute ATHING__MANY_THREAD = eINSTANCE.getAThing_ManyThread();

    /**
     * The meta object literal for the '<em>My Bytes</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyBytes()
     * @generated
     */
    public static final EDataType MY_BYTES = eINSTANCE.getMyBytes();

    /**
     * The meta object literal for the '<em>My Char</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyChar()
     * @generated
     */
    public static final EDataType MY_CHAR = eINSTANCE.getMyChar();

    /**
     * The meta object literal for the '<em>My Char Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Character
     * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyCharObject()
     * @generated
     */
    public static final EDataType MY_CHAR_OBJECT = eINSTANCE.getMyCharObject();

    /**
     * The meta object literal for the '<em>My Date</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.Date
     * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyDate()
     * @generated
     */
    public static final EDataType MY_DATE = eINSTANCE.getMyDate();

    /**
     * The meta object literal for the '<em>My Number</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Number
     * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyNumber()
     * @generated
     */
    public static final EDataType MY_NUMBER = eINSTANCE.getMyNumber();

    /**
     * The meta object literal for the '<em>My Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Object
     * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyObject()
     * @generated
     */
    public static final EDataType MY_OBJECT = eINSTANCE.getMyObject();

    /**
     * The meta object literal for the '<em>My Thread</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Thread
     * @see org.eclipse.emf.test.models.types.impl.TypesPackageImpl#getMyThread()
     * @generated
     */
    public static final EDataType MY_THREAD = eINSTANCE.getMyThread();

  }

} //TypesPackageImpl
