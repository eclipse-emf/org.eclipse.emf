/**
 * <copyright>
 * </copyright>
 *
 * $Id: TypesPackage.java,v 1.2 2004/06/07 19:46:46 marcelop Exp $
 */
package org.eclipse.emf.test.core.sdo.types.model.types;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

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
 * @see org.eclipse.emf.test.core.sdo.types.model.types.TypesFactory
 * @generated
 */
public interface TypesPackage extends EPackage{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "types";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.example.com/types";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "types";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  TypesPackage eINSTANCE = org.eclipse.emf.test.core.sdo.types.model.types.impl.TypesPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl <em>AThing</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.core.sdo.types.model.types.impl.AThingImpl
   * @see org.eclipse.emf.test.core.sdo.types.model.types.impl.TypesPackageImpl#getAThing()
   * @generated
   */
  int ATHING = 0;

  /**
   * The feature id for the '<em><b>ABoolean</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ABOOLEAN = 0;

  /**
   * The feature id for the '<em><b>AByte</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ABYTE = 1;

  /**
   * The feature id for the '<em><b>ADecimal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ADECIMAL = 2;

  /**
   * The feature id for the '<em><b>AFloat</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__AFLOAT = 3;

  /**
   * The feature id for the '<em><b>ADouble</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ADOUBLE = 4;

  /**
   * The feature id for the '<em><b>AInt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__AINT = 5;

  /**
   * The feature id for the '<em><b>AInteger</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__AINTEGER = 6;

  /**
   * The feature id for the '<em><b>ALong</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ALONG = 7;

  /**
   * The feature id for the '<em><b>AShort</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ASHORT = 8;

  /**
   * The feature id for the '<em><b>AString</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ASTRING = 9;

  /**
   * The feature id for the '<em><b>AChar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ACHAR = 10;

  /**
   * The feature id for the '<em><b>ADate</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ADATE = 11;

  /**
   * The feature id for the '<em><b>ABytes</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ABYTES = 12;

  /**
   * The feature id for the '<em><b>ANumber</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ANUMBER = 13;

  /**
   * The feature id for the '<em><b>AObject</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__AOBJECT = 14;

  /**
   * The feature id for the '<em><b>AThread</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING__ATHREAD = 15;

  /**
   * The number of structural features of the the '<em>AThing</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATHING_FEATURE_COUNT = 16;


  /**
   * The meta object id for the '<em>My Bytes</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.core.sdo.types.model.types.impl.TypesPackageImpl#getMyBytes()
   * @generated
   */
  int MY_BYTES = 1;

  /**
   * The meta object id for the '<em>My Char</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.core.sdo.types.model.types.impl.TypesPackageImpl#getMyChar()
   * @generated
   */
  int MY_CHAR = 2;

  /**
   * The meta object id for the '<em>My Char Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Character
   * @see org.eclipse.emf.test.core.sdo.types.model.types.impl.TypesPackageImpl#getMyCharObject()
   * @generated
   */
  int MY_CHAR_OBJECT = 3;

  /**
   * The meta object id for the '<em>My Date</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.Date
   * @see org.eclipse.emf.test.core.sdo.types.model.types.impl.TypesPackageImpl#getMyDate()
   * @generated
   */
  int MY_DATE = 4;


  /**
   * The meta object id for the '<em>My Number</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Number
   * @see org.eclipse.emf.test.core.sdo.types.model.types.impl.TypesPackageImpl#getMyNumber()
   * @generated
   */
  int MY_NUMBER = 5;

  /**
   * The meta object id for the '<em>My Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.test.core.sdo.types.model.types.impl.TypesPackageImpl#getMyObject()
   * @generated
   */
  int MY_OBJECT = 6;

  /**
   * The meta object id for the '<em>My Thread</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Thread
   * @see org.eclipse.emf.test.core.sdo.types.model.types.impl.TypesPackageImpl#getMyThread()
   * @generated
   */
  int MY_THREAD = 7;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing <em>AThing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>AThing</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing
   * @generated
   */
  EClass getAThing();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#isABoolean <em>ABoolean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ABoolean</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#isABoolean()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_ABoolean();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAByte <em>AByte</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AByte</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAByte()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_AByte();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getADecimal <em>ADecimal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ADecimal</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getADecimal()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_ADecimal();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getADate <em>ADate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ADate</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getADate()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_ADate();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getABytes <em>ABytes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ABytes</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getABytes()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_ABytes();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getANumber <em>ANumber</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ANumber</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getANumber()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_ANumber();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAObject <em>AObject</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AObject</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAObject()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_AObject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAThread <em>AThread</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AThread</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAThread()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_AThread();

  /**
   * Returns the meta object for data type '<em>My Bytes</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Bytes</em>'.
   * @model instanceClass="byte[]"
   * @generated
   */
  EDataType getMyBytes();

  /**
   * Returns the meta object for data type '<em>My Char</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Char</em>'.
   * @model instanceClass="char"
   * @generated
   */
  EDataType getMyChar();

  /**
   * Returns the meta object for data type '{@link java.lang.Character <em>My Char Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Char Object</em>'.
   * @see java.lang.Character
   * @model instanceClass="java.lang.Character"
   * @generated
   */
  EDataType getMyCharObject();

  /**
   * Returns the meta object for data type '{@link java.util.Date <em>My Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Date</em>'.
   * @see java.util.Date
   * @model instanceClass="java.util.Date"
   * @generated
   */
  EDataType getMyDate();

  /**
   * Returns the meta object for data type '{@link java.lang.Number <em>My Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Number</em>'.
   * @see java.lang.Number
   * @model instanceClass="java.lang.Number"
   * @generated
   */
  EDataType getMyNumber();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>My Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Object</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   * @generated
   */
  EDataType getMyObject();

  /**
   * Returns the meta object for data type '{@link java.lang.Thread <em>My Thread</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>My Thread</em>'.
   * @see java.lang.Thread
   * @model instanceClass="java.lang.Thread"
   * @generated
   */
  EDataType getMyThread();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAFloat <em>AFloat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AFloat</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAFloat()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_AFloat();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getADouble <em>ADouble</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ADouble</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getADouble()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_ADouble();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAInt <em>AInt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AInt</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAInt()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_AInt();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAInteger <em>AInteger</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AInteger</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAInteger()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_AInteger();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getALong <em>ALong</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ALong</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getALong()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_ALong();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAShort <em>AShort</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AShort</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAShort()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_AShort();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAString <em>AString</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AString</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAString()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_AString();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAChar <em>AChar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>AChar</em>'.
   * @see org.eclipse.emf.test.core.sdo.types.model.types.AThing#getAChar()
   * @see #getAThing()
   * @generated
   */
  EAttribute getAThing_AChar();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  TypesFactory getTypesFactory();

} //TypesPackage
