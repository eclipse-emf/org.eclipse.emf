/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XMLTypePackage.java,v 1.4 2004/12/16 21:33:53 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.type;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see org.eclipse.emf.ecore.xml.type.XMLTypeFactory
 * @generated
 */
public interface XMLTypePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "type";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/2003/XMLType";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "xml.type";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  XMLTypePackage eINSTANCE = org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xml.type.impl.AnyTypeImpl <em>Any Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.AnyTypeImpl
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getAnyType()
   * @generated
   */
  int ANY_TYPE = 0;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_TYPE__MIXED = 0;

  /**
   * The feature id for the '<em><b>Any</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_TYPE__ANY = 1;

  /**
   * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_TYPE__ANY_ATTRIBUTE = 2;

  /**
   * The number of structural features of the the '<em>Any Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xml.type.impl.SimpleAnyTypeImpl <em>Simple Any Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.SimpleAnyTypeImpl
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getSimpleAnyType()
   * @generated
   */
  int SIMPLE_ANY_TYPE = 1;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_ANY_TYPE__MIXED = ANY_TYPE__MIXED;

  /**
   * The feature id for the '<em><b>Any</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_ANY_TYPE__ANY = ANY_TYPE__ANY;

  /**
   * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_ANY_TYPE__ANY_ATTRIBUTE = ANY_TYPE__ANY_ATTRIBUTE;

  /**
   * The feature id for the '<em><b>Raw Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_ANY_TYPE__RAW_VALUE = ANY_TYPE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_ANY_TYPE__VALUE = ANY_TYPE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Instance Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_ANY_TYPE__INSTANCE_TYPE = ANY_TYPE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the the '<em>Simple Any Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_ANY_TYPE_FEATURE_COUNT = ANY_TYPE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xml.type.impl.XMLTypeDocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypeDocumentRootImpl
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getXMLTypeDocumentRoot()
   * @generated
   */
  int XML_TYPE_DOCUMENT_ROOT = 2;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_TYPE_DOCUMENT_ROOT__MIXED = 0;

  /**
   * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

  /**
   * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

  /**
   * The feature id for the '<em><b>CDATA</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_TYPE_DOCUMENT_ROOT__CDATA = 3;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_TYPE_DOCUMENT_ROOT__COMMENT = 4;

  /**
   * The feature id for the '<em><b>Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_TYPE_DOCUMENT_ROOT__TEXT = 5;

  /**
   * The number of structural features of the the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_TYPE_DOCUMENT_ROOT_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '<em>Any Simple Type</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getAnySimpleType()
   * @generated
   */
  int ANY_SIMPLE_TYPE = 3;

  /**
   * The meta object id for the '<em>Any URI</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getAnyURI()
   * @generated
   */
  int ANY_URI = 4;

  /**
   * The meta object id for the '<em>Base64 Binary</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getBase64Binary()
   * @generated
   */
  int BASE64_BINARY = 5;

  /**
   * The meta object id for the '<em>Boolean</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getBoolean()
   * @generated
   */
  int BOOLEAN = 6;

  /**
   * The meta object id for the '<em>Boolean Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Boolean
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getBooleanObject()
   * @generated
   */
  int BOOLEAN_OBJECT = 7;

  /**
   * The meta object id for the '<em>Decimal</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.math.BigDecimal
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getDecimal()
   * @generated
   */
  int DECIMAL = 12;

  /**
   * The meta object id for the '<em>Integer</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.math.BigInteger
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getInteger()
   * @generated
   */
  int INTEGER = 32;

  /**
   * The meta object id for the '<em>Int Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Integer
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getIntObject()
   * @generated
   */
  int INT_OBJECT = 33;

  /**
   * The meta object id for the '<em>Long</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getLong()
   * @generated
   */
  int LONG = 35;

  /**
   * The meta object id for the '<em>Long Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Long
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getLongObject()
   * @generated
   */
  int LONG_OBJECT = 36;

  /**
   * The meta object id for the '<em>Int</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getInt()
   * @generated
   */
  int INT = 31;

  /**
   * The meta object id for the '<em>Short</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getShort()
   * @generated
   */
  int SHORT = 49;

  /**
   * The meta object id for the '<em>Short Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Short
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getShortObject()
   * @generated
   */
  int SHORT_OBJECT = 50;

  /**
   * The meta object id for the '<em>Byte</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getByte()
   * @generated
   */
  int BYTE = 8;

  /**
   * The meta object id for the '<em>Byte Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Byte
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getByteObject()
   * @generated
   */
  int BYTE_OBJECT = 9;

  /**
   * The meta object id for the '<em>Date</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getDate()
   * @generated
   */
  int DATE = 10;

  /**
   * The meta object id for the '<em>Date Time</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getDateTime()
   * @generated
   */
  int DATE_TIME = 11;

  /**
   * The meta object id for the '<em>String</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getString()
   * @generated
   */
  int STRING = 51;

  /**
   * The meta object id for the '<em>Double</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getDouble()
   * @generated
   */
  int DOUBLE = 13;

  /**
   * The meta object id for the '<em>Double Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Double
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getDoubleObject()
   * @generated
   */
  int DOUBLE_OBJECT = 14;

  /**
   * The meta object id for the '<em>Duration</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getDuration()
   * @generated
   */
  int DURATION = 15;

  /**
   * The meta object id for the '<em>ENTITIES Base</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getENTITIESBase()
   * @generated
   */
  int ENTITIES_BASE = 17;

  /**
   * The meta object id for the '<em>Normalized String</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getNormalizedString()
   * @generated
   */
  int NORMALIZED_STRING = 45;

  /**
   * The meta object id for the '<em>Token</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getToken()
   * @generated
   */
  int TOKEN = 53;

  /**
   * The meta object id for the '<em>Name</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getName_()
   * @generated
   */
  int NAME = 37;

  /**
   * The meta object id for the '<em>NC Name</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getNCName()
   * @generated
   */
  int NC_NAME = 38;

  /**
   * The meta object id for the '<em>ENTITY</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getENTITY()
   * @generated
   */
  int ENTITY = 18;

  /**
   * The meta object id for the '<em>ENTITIES</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getENTITIES()
   * @generated
   */
  int ENTITIES = 16;

  /**
   * The meta object id for the '<em>Float</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getFloat()
   * @generated
   */
  int FLOAT = 19;

  /**
   * The meta object id for the '<em>Float Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Float
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getFloatObject()
   * @generated
   */
  int FLOAT_OBJECT = 20;

  /**
   * The meta object id for the '<em>GDay</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getGDay()
   * @generated
   */
  int GDAY = 21;

  /**
   * The meta object id for the '<em>GMonth</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getGMonth()
   * @generated
   */
  int GMONTH = 22;

  /**
   * The meta object id for the '<em>GMonth Day</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getGMonthDay()
   * @generated
   */
  int GMONTH_DAY = 23;

  /**
   * The meta object id for the '<em>GYear</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getGYear()
   * @generated
   */
  int GYEAR = 24;

  /**
   * The meta object id for the '<em>GYear Month</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getGYearMonth()
   * @generated
   */
  int GYEAR_MONTH = 25;

  /**
   * The meta object id for the '<em>Hex Binary</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getHexBinary()
   * @generated
   */
  int HEX_BINARY = 26;

  /**
   * The meta object id for the '<em>ID</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getID()
   * @generated
   */
  int ID = 27;

  /**
   * The meta object id for the '<em>IDREF</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getIDREF()
   * @generated
   */
  int IDREF = 28;

  /**
   * The meta object id for the '<em>IDREFS Base</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getIDREFSBase()
   * @generated
   */
  int IDREFS_BASE = 30;

  /**
   * The meta object id for the '<em>IDREFS</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getIDREFS()
   * @generated
   */
  int IDREFS = 29;

  /**
   * The meta object id for the '<em>Language</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getLanguage()
   * @generated
   */
  int LANGUAGE = 34;

  /**
   * The meta object id for the '<em>Non Positive Integer</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.math.BigInteger
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getNonPositiveInteger()
   * @generated
   */
  int NON_POSITIVE_INTEGER = 44;

  /**
   * The meta object id for the '<em>Negative Integer</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.math.BigInteger
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getNegativeInteger()
   * @generated
   */
  int NEGATIVE_INTEGER = 39;

  /**
   * The meta object id for the '<em>NMTOKEN</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.String
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getNMTOKEN()
   * @generated
   */
  int NMTOKEN = 40;

  /**
   * The meta object id for the '<em>NMTOKENS Base</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getNMTOKENSBase()
   * @generated
   */
  int NMTOKENS_BASE = 42;

  /**
   * The meta object id for the '<em>NMTOKENS</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getNMTOKENS()
   * @generated
   */
  int NMTOKENS = 41;

  /**
   * The meta object id for the '<em>Non Negative Integer</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.math.BigInteger
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getNonNegativeInteger()
   * @generated
   */
  int NON_NEGATIVE_INTEGER = 43;

  /**
   * The meta object id for the '<em>NOTATION</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getNOTATION()
   * @generated
   */
  int NOTATION = 46;

  /**
   * The meta object id for the '<em>Positive Integer</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.math.BigInteger
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getPositiveInteger()
   * @generated
   */
  int POSITIVE_INTEGER = 47;

  /**
   * The meta object id for the '<em>QName</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getQName()
   * @generated
   */
  int QNAME = 48;

  /**
   * The meta object id for the '<em>Time</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Object
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getTime()
   * @generated
   */
  int TIME = 52;

  /**
   * The meta object id for the '<em>Unsigned Long</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.math.BigInteger
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getUnsignedLong()
   * @generated
   */
  int UNSIGNED_LONG = 58;

  /**
   * The meta object id for the '<em>Unsigned Int</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getUnsignedInt()
   * @generated
   */
  int UNSIGNED_INT = 56;

  /**
   * The meta object id for the '<em>Unsigned Int Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Long
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getUnsignedIntObject()
   * @generated
   */
  int UNSIGNED_INT_OBJECT = 57;

  /**
   * The meta object id for the '<em>Unsigned Short</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getUnsignedShort()
   * @generated
   */
  int UNSIGNED_SHORT = 59;

  /**
   * The meta object id for the '<em>Unsigned Short Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Integer
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getUnsignedShortObject()
   * @generated
   */
  int UNSIGNED_SHORT_OBJECT = 60;


  /**
   * The meta object id for the '<em>Unsigned Byte</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getUnsignedByte()
   * @generated
   */
  int UNSIGNED_BYTE = 54;


  /**
   * The meta object id for the '<em>Unsigned Byte Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Short
   * @see org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl#getUnsignedByteObject()
   * @generated
   */
  int UNSIGNED_BYTE_OBJECT = 55;

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xml.type.AnyType <em>Any Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Any Type</em>'.
   * @see org.eclipse.emf.ecore.xml.type.AnyType
   * @generated
   */
  EClass getAnyType();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.ecore.xml.type.AnyType#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.ecore.xml.type.AnyType#getMixed()
   * @see #getAnyType()
   * @generated
   */
  EAttribute getAnyType_Mixed();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.ecore.xml.type.AnyType#getAny <em>Any</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Any</em>'.
   * @see org.eclipse.emf.ecore.xml.type.AnyType#getAny()
   * @see #getAnyType()
   * @generated
   */
  EAttribute getAnyType_Any();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.ecore.xml.type.AnyType#getAnyAttribute <em>Any Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Any Attribute</em>'.
   * @see org.eclipse.emf.ecore.xml.type.AnyType#getAnyAttribute()
   * @see #getAnyType()
   * @generated
   */
  EAttribute getAnyType_AnyAttribute();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xml.type.SimpleAnyType <em>Simple Any Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simple Any Type</em>'.
   * @see org.eclipse.emf.ecore.xml.type.SimpleAnyType
   * @generated
   */
  EClass getSimpleAnyType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xml.type.SimpleAnyType#getRawValue <em>Raw Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Raw Value</em>'.
   * @see org.eclipse.emf.ecore.xml.type.SimpleAnyType#getRawValue()
   * @see #getSimpleAnyType()
   * @generated
   */
  EAttribute getSimpleAnyType_RawValue();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xml.type.SimpleAnyType#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.ecore.xml.type.SimpleAnyType#getValue()
   * @see #getSimpleAnyType()
   * @generated
   */
  EAttribute getSimpleAnyType_Value();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.ecore.xml.type.SimpleAnyType#getInstanceType <em>Instance Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Instance Type</em>'.
   * @see org.eclipse.emf.ecore.xml.type.SimpleAnyType#getInstanceType()
   * @see #getSimpleAnyType()
   * @generated
   */
  EReference getSimpleAnyType_InstanceType();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot
   * @generated
   */
  EClass getXMLTypeDocumentRoot();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getMixed()
   * @see #getXMLTypeDocumentRoot()
   * @generated
   */
  EAttribute getXMLTypeDocumentRoot_Mixed();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getXMLNSPrefixMap()
   * @see #getXMLTypeDocumentRoot()
   * @generated
   */
  EReference getXMLTypeDocumentRoot_XMLNSPrefixMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getXSISchemaLocation()
   * @see #getXMLTypeDocumentRoot()
   * @generated
   */
  EReference getXMLTypeDocumentRoot_XSISchemaLocation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getCDATA <em>CDATA</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>CDATA</em>'.
   * @see org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getCDATA()
   * @see #getXMLTypeDocumentRoot()
   * @generated
   */
  EAttribute getXMLTypeDocumentRoot_CDATA();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getComment()
   * @see #getXMLTypeDocumentRoot()
   * @generated
   */
  EAttribute getXMLTypeDocumentRoot_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getText <em>Text</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Text</em>'.
   * @see org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot#getText()
   * @see #getXMLTypeDocumentRoot()
   * @generated
   */
  EAttribute getXMLTypeDocumentRoot_Text();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>Any Simple Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Any Simple Type</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='anySimpleType'" 
   * @generated
   */
  EDataType getAnySimpleType();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>Any URI</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Any URI</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='anyURI' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getAnyURI();

  /**
   * Returns the meta object for data type '<em>Base64 Binary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Base64 Binary</em>'.
   * @model instanceClass="byte[]"
   *        extendedMetaData="name='base64Binary' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getBase64Binary();

  /**
   * Returns the meta object for data type '<em>Boolean</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Boolean</em>'.
   * @model instanceClass="boolean"
   *        extendedMetaData="name='boolean' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getBoolean();

  /**
   * Returns the meta object for data type '{@link java.lang.Boolean <em>Boolean Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Boolean Object</em>'.
   * @see java.lang.Boolean
   * @model instanceClass="java.lang.Boolean"
   *        extendedMetaData="name='boolean:Object' baseType='boolean'" 
   * @generated
   */
  EDataType getBooleanObject();

  /**
   * Returns the meta object for data type '{@link java.math.BigDecimal <em>Decimal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Decimal</em>'.
   * @see java.math.BigDecimal
   * @model instanceClass="java.math.BigDecimal"
   *        extendedMetaData="name='decimal' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getDecimal();

  /**
   * Returns the meta object for data type '{@link java.math.BigInteger <em>Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Integer</em>'.
   * @see java.math.BigInteger
   * @model instanceClass="java.math.BigInteger"
   *        extendedMetaData="name='integer'" 
   * @generated
   */
  EDataType getInteger();

  /**
   * Returns the meta object for data type '{@link java.lang.Integer <em>Int Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Int Object</em>'.
   * @see java.lang.Integer
   * @model instanceClass="java.lang.Integer"
   *        extendedMetaData="name='int:Object' baseType='int'" 
   * @generated
   */
  EDataType getIntObject();

  /**
   * Returns the meta object for data type '<em>Long</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Long</em>'.
   * @model instanceClass="long"
   *        extendedMetaData="name='long'" 
   * @generated
   */
  EDataType getLong();

  /**
   * Returns the meta object for data type '{@link java.lang.Long <em>Long Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Long Object</em>'.
   * @see java.lang.Long
   * @model instanceClass="java.lang.Long"
   *        extendedMetaData="name='long:Object' baseType='long'" 
   * @generated
   */
  EDataType getLongObject();

  /**
   * Returns the meta object for data type '<em>Int</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Int</em>'.
   * @model instanceClass="int"
   *        extendedMetaData="name='int'" 
   * @generated
   */
  EDataType getInt();

  /**
   * Returns the meta object for data type '<em>Short</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Short</em>'.
   * @model instanceClass="short"
   *        extendedMetaData="name='short'" 
   * @generated
   */
  EDataType getShort();

  /**
   * Returns the meta object for data type '{@link java.lang.Short <em>Short Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Short Object</em>'.
   * @see java.lang.Short
   * @model instanceClass="java.lang.Short"
   *        extendedMetaData="name='short:Object' baseType='short'" 
   * @generated
   */
  EDataType getShortObject();

  /**
   * Returns the meta object for data type '<em>Byte</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Byte</em>'.
   * @model instanceClass="byte"
   *        extendedMetaData="name='byte'" 
   * @generated
   */
  EDataType getByte();

  /**
   * Returns the meta object for data type '{@link java.lang.Byte <em>Byte Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Byte Object</em>'.
   * @see java.lang.Byte
   * @model instanceClass="java.lang.Byte"
   *        extendedMetaData="name='byte:Object' baseType='byte'" 
   * @generated
   */
  EDataType getByteObject();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Date</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='date' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getDate();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>Date Time</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Date Time</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='dateTime' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getDateTime();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>String</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='string' whiteSpace='preserve'" 
   * @generated
   */
  EDataType getString();

  /**
   * Returns the meta object for data type '<em>Double</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Double</em>'.
   * @model instanceClass="double"
   *        extendedMetaData="name='double' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getDouble();

  /**
   * Returns the meta object for data type '{@link java.lang.Double <em>Double Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Double Object</em>'.
   * @see java.lang.Double
   * @model instanceClass="java.lang.Double"
   *        extendedMetaData="name='double:Object' baseType='double'" 
   * @generated
   */
  EDataType getDoubleObject();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>Duration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Duration</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='duration' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getDuration();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>ENTITIES Base</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>ENTITIES Base</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List"
   *        extendedMetaData="name='ENTITIES_._base' itemType='ENTITY'" 
   * @generated
   */
  EDataType getENTITIESBase();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>Normalized String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Normalized String</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='normalizedString' baseType='string' whiteSpace='replace'" 
   * @generated
   */
  EDataType getNormalizedString();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>Token</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Token</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='token' baseType='normalizedString' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getToken();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Name</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='Name' baseType='token' pattern='\\i\\c*'" 
   * @generated
   */
  EDataType getName_();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>NC Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>NC Name</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='NCName' baseType='Name' pattern='[\\i-[:]][\\c-[:]]*'" 
   * @generated
   */
  EDataType getNCName();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>ENTITY</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>ENTITY</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='ENTITY' baseType='NCName'" 
   * @generated
   */
  EDataType getENTITY();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>ENTITIES</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>ENTITIES</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List"
   *        extendedMetaData="name='ENTITIES' baseType='ENTITIES_._base' minLength='1'" 
   * @generated
   */
  EDataType getENTITIES();

  /**
   * Returns the meta object for data type '<em>Float</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Float</em>'.
   * @model instanceClass="float"
   *        extendedMetaData="name='float' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getFloat();

  /**
   * Returns the meta object for data type '{@link java.lang.Float <em>Float Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Float Object</em>'.
   * @see java.lang.Float
   * @model instanceClass="java.lang.Float"
   *        extendedMetaData="name='float:Object' baseType='float'" 
   * @generated
   */
  EDataType getFloatObject();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>GDay</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>GDay</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='gDay' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getGDay();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>GMonth</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>GMonth</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='gMonth' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getGMonth();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>GMonth Day</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>GMonth Day</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='gMonthDay' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getGMonthDay();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>GYear</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>GYear</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='gYear' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getGYear();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>GYear Month</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>GYear Month</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='gYearMonth' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getGYearMonth();

  /**
   * Returns the meta object for data type '<em>Hex Binary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Hex Binary</em>'.
   * @model instanceClass="byte[]"
   *        extendedMetaData="name='hexBinary' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getHexBinary();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>ID</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='ID' baseType='NCName'" 
   * @generated
   */
  EDataType getID();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>IDREF</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IDREF</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='IDREF' baseType='NCName'" 
   * @generated
   */
  EDataType getIDREF();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>IDREFS Base</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IDREFS Base</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List"
   *        extendedMetaData="name='IDREFS_._base' itemType='IDREF'" 
   * @generated
   */
  EDataType getIDREFSBase();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>IDREFS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IDREFS</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List"
   *        extendedMetaData="name='IDREFS' baseType='IDREFS_._base' minLength='1'" 
   * @generated
   */
  EDataType getIDREFS();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>Language</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Language</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='language' baseType='token' pattern='[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*'" 
   * @generated
   */
  EDataType getLanguage();

  /**
   * Returns the meta object for data type '{@link java.math.BigInteger <em>Non Positive Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Non Positive Integer</em>'.
   * @see java.math.BigInteger
   * @model instanceClass="java.math.BigInteger"
   *        extendedMetaData="name='nonPositiveInteger' baseType='integer' maxInclusive='0'" 
   * @generated
   */
  EDataType getNonPositiveInteger();

  /**
   * Returns the meta object for data type '{@link java.math.BigInteger <em>Negative Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Negative Integer</em>'.
   * @see java.math.BigInteger
   * @model instanceClass="java.math.BigInteger"
   *        extendedMetaData="name='negativeInteger' baseType='nonPositiveInteger' maxInclusive='-1'" 
   * @generated
   */
  EDataType getNegativeInteger();

  /**
   * Returns the meta object for data type '{@link java.lang.String <em>NMTOKEN</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>NMTOKEN</em>'.
   * @see java.lang.String
   * @model instanceClass="java.lang.String"
   *        extendedMetaData="name='NMTOKEN' baseType='token' pattern='\\c+'" 
   * @generated
   */
  EDataType getNMTOKEN();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>NMTOKENS Base</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>NMTOKENS Base</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List"
   *        extendedMetaData="name='NMTOKENS_._base' itemType='NMTOKEN'" 
   * @generated
   */
  EDataType getNMTOKENSBase();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>NMTOKENS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>NMTOKENS</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List"
   *        extendedMetaData="name='NMTOKENS' baseType='NMTOKENS_._base' minLength='1'" 
   * @generated
   */
  EDataType getNMTOKENS();

  /**
   * Returns the meta object for data type '{@link java.math.BigInteger <em>Non Negative Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Non Negative Integer</em>'.
   * @see java.math.BigInteger
   * @model instanceClass="java.math.BigInteger"
   *        extendedMetaData="name='nonNegativeInteger' baseType='integer' minInclusive='0'" 
   * @generated
   */
  EDataType getNonNegativeInteger();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>NOTATION</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>NOTATION</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='NOTATION' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getNOTATION();

  /**
   * Returns the meta object for data type '{@link java.math.BigInteger <em>Positive Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Positive Integer</em>'.
   * @see java.math.BigInteger
   * @model instanceClass="java.math.BigInteger"
   *        extendedMetaData="name='positiveInteger' baseType='nonNegativeInteger' minInclusive='1'" 
   * @generated
   */
  EDataType getPositiveInteger();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>QName</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>QName</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='QName' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getQName();

  /**
   * Returns the meta object for data type '{@link java.lang.Object <em>Time</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Time</em>'.
   * @see java.lang.Object
   * @model instanceClass="java.lang.Object"
   *        extendedMetaData="name='time' baseType='anySimpleType' whiteSpace='collapse'" 
   * @generated
   */
  EDataType getTime();

  /**
   * Returns the meta object for data type '{@link java.math.BigInteger <em>Unsigned Long</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Unsigned Long</em>'.
   * @see java.math.BigInteger
   * @model instanceClass="java.math.BigInteger"
   *        extendedMetaData="name='unsignedLong' baseType='nonNegativeInteger' maxInclusive='18446744073709551615' minInclusive='0'" 
   * @generated
   */
  EDataType getUnsignedLong();

  /**
   * Returns the meta object for data type '<em>Unsigned Int</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Unsigned Int</em>'.
   * @model instanceClass="long"
   *        extendedMetaData="name='unsignedInt' maxInclusive='4294967295' minInclusive='0'" 
   * @generated
   */
  EDataType getUnsignedInt();

  /**
   * Returns the meta object for data type '{@link java.lang.Long <em>Unsigned Int Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Unsigned Int Object</em>'.
   * @see java.lang.Long
   * @model instanceClass="java.lang.Long"
   *        extendedMetaData="name='unsignedInt:Object' baseType='unsignedInt'" 
   * @generated
   */
  EDataType getUnsignedIntObject();

  /**
   * Returns the meta object for data type '<em>Unsigned Short</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Unsigned Short</em>'.
   * @model instanceClass="int"
   *        extendedMetaData="name='unsignedShort' maxInclusive='65535' minInclusive='0'" 
   * @generated
   */
  EDataType getUnsignedShort();

  /**
   * Returns the meta object for data type '{@link java.lang.Integer <em>Unsigned Short Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Unsigned Short Object</em>'.
   * @see java.lang.Integer
   * @model instanceClass="java.lang.Integer"
   *        extendedMetaData="name='unsignedShort:Object' baseType='unsignedShort'" 
   * @generated
   */
  EDataType getUnsignedShortObject();

  /**
   * Returns the meta object for data type '<em>Unsigned Byte</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Unsigned Byte</em>'.
   * @model instanceClass="short"
   *        extendedMetaData="name='unsignedByte' maxInclusive='255' minInclusive='0'" 
   * @generated
   */
  EDataType getUnsignedByte();

  /**
   * Returns the meta object for data type '{@link java.lang.Short <em>Unsigned Byte Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Unsigned Byte Object</em>'.
   * @see java.lang.Short
   * @model instanceClass="java.lang.Short"
   *        extendedMetaData="name='unsignedByte:Object' baseType='unsignedByte'" 
   * @generated
   */
  EDataType getUnsignedByteObject();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  XMLTypeFactory getXMLTypeFactory();

} //XMLTypePackage
