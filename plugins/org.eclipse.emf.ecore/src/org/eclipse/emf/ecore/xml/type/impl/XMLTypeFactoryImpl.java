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
 * $Id: XMLTypeFactoryImpl.java,v 1.5 2004/05/11 15:40:04 elena Exp $
 */
package org.eclipse.emf.ecore.xml.type.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.xml.type.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XMLTypeFactoryImpl extends EFactoryImpl implements XMLTypeFactory
{
  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLTypeFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case XMLTypePackage.ANY_TYPE: return createAnyType();
      case XMLTypePackage.SIMPLE_ANY_TYPE: return createSimpleAnyType();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT: return createXMLTypeDocumentRoot();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case XMLTypePackage.ANY_SIMPLE_TYPE:
        return createAnySimpleTypeFromString(eDataType, initialValue);
      case XMLTypePackage.ANY_URI:
        return createAnyURIFromString(eDataType, initialValue);
      case XMLTypePackage.BASE64_BINARY:
        return createBase64BinaryFromString(eDataType, initialValue);
      case XMLTypePackage.BOOLEAN:
        return createBooleanFromString(eDataType, initialValue);
      case XMLTypePackage.BOOLEAN_OBJECT:
        return createBooleanObjectFromString(eDataType, initialValue);
      case XMLTypePackage.BYTE:
        return createByteFromString(eDataType, initialValue);
      case XMLTypePackage.BYTE_OBJECT:
        return createByteObjectFromString(eDataType, initialValue);
      case XMLTypePackage.DATE:
        return createDateFromString(eDataType, initialValue);
      case XMLTypePackage.DATE_TIME:
        return createDateTimeFromString(eDataType, initialValue);
      case XMLTypePackage.DECIMAL:
        return createDecimalFromString(eDataType, initialValue);
      case XMLTypePackage.DOUBLE:
        return createDoubleFromString(eDataType, initialValue);
      case XMLTypePackage.DOUBLE_OBJECT:
        return createDoubleObjectFromString(eDataType, initialValue);
      case XMLTypePackage.DURATION:
        return createDurationFromString(eDataType, initialValue);
      case XMLTypePackage.ENTITIES:
        return createENTITIESFromString(eDataType, initialValue);
      case XMLTypePackage.ENTITIES_BASE:
        return createENTITIESBaseFromString(eDataType, initialValue);
      case XMLTypePackage.ENTITY:
        return createENTITYFromString(eDataType, initialValue);
      case XMLTypePackage.FLOAT:
        return createFloatFromString(eDataType, initialValue);
      case XMLTypePackage.FLOAT_OBJECT:
        return createFloatObjectFromString(eDataType, initialValue);
      case XMLTypePackage.GDAY:
        return createGDayFromString(eDataType, initialValue);
      case XMLTypePackage.GMONTH:
        return createGMonthFromString(eDataType, initialValue);
      case XMLTypePackage.GMONTH_DAY:
        return createGMonthDayFromString(eDataType, initialValue);
      case XMLTypePackage.GYEAR:
        return createGYearFromString(eDataType, initialValue);
      case XMLTypePackage.GYEAR_MONTH:
        return createGYearMonthFromString(eDataType, initialValue);
      case XMLTypePackage.HEX_BINARY:
        return createHexBinaryFromString(eDataType, initialValue);
      case XMLTypePackage.ID:
        return createIDFromString(eDataType, initialValue);
      case XMLTypePackage.IDREF:
        return createIDREFFromString(eDataType, initialValue);
      case XMLTypePackage.IDREFS:
        return createIDREFSFromString(eDataType, initialValue);
      case XMLTypePackage.IDREFS_BASE:
        return createIDREFSBaseFromString(eDataType, initialValue);
      case XMLTypePackage.INT:
        return createIntFromString(eDataType, initialValue);
      case XMLTypePackage.INTEGER:
        return createIntegerFromString(eDataType, initialValue);
      case XMLTypePackage.INT_OBJECT:
        return createIntObjectFromString(eDataType, initialValue);
      case XMLTypePackage.LANGUAGE:
        return createLanguageFromString(eDataType, initialValue);
      case XMLTypePackage.LONG:
        return createLongFromString(eDataType, initialValue);
      case XMLTypePackage.LONG_OBJECT:
        return createLongObjectFromString(eDataType, initialValue);
      case XMLTypePackage.NAME:
        return createNameFromString(eDataType, initialValue);
      case XMLTypePackage.NC_NAME:
        return createNCNameFromString(eDataType, initialValue);
      case XMLTypePackage.NEGATIVE_INTEGER:
        return createNegativeIntegerFromString(eDataType, initialValue);
      case XMLTypePackage.NMTOKEN:
        return createNMTOKENFromString(eDataType, initialValue);
      case XMLTypePackage.NMTOKENS:
        return createNMTOKENSFromString(eDataType, initialValue);
      case XMLTypePackage.NMTOKENS_BASE:
        return createNMTOKENSBaseFromString(eDataType, initialValue);
      case XMLTypePackage.NON_NEGATIVE_INTEGER:
        return createNonNegativeIntegerFromString(eDataType, initialValue);
      case XMLTypePackage.NON_POSITIVE_INTEGER:
        return createNonPositiveIntegerFromString(eDataType, initialValue);
      case XMLTypePackage.NORMALIZED_STRING:
        return createNormalizedStringFromString(eDataType, initialValue);
      case XMLTypePackage.NOTATION:
        return createNOTATIONFromString(eDataType, initialValue);
      case XMLTypePackage.POSITIVE_INTEGER:
        return createPositiveIntegerFromString(eDataType, initialValue);
      case XMLTypePackage.QNAME:
        return createQNameFromString(eDataType, initialValue);
      case XMLTypePackage.SHORT:
        return createShortFromString(eDataType, initialValue);
      case XMLTypePackage.SHORT_OBJECT:
        return createShortObjectFromString(eDataType, initialValue);
      case XMLTypePackage.STRING:
        return createStringFromString(eDataType, initialValue);
      case XMLTypePackage.TIME:
        return createTimeFromString(eDataType, initialValue);
      case XMLTypePackage.TOKEN:
        return createTokenFromString(eDataType, initialValue);
      case XMLTypePackage.UNSIGNED_BYTE:
        return createUnsignedByteFromString(eDataType, initialValue);
      case XMLTypePackage.UNSIGNED_BYTE_OBJECT:
        return createUnsignedByteObjectFromString(eDataType, initialValue);
      case XMLTypePackage.UNSIGNED_INT:
        return createUnsignedIntFromString(eDataType, initialValue);
      case XMLTypePackage.UNSIGNED_INT_OBJECT:
        return createUnsignedIntObjectFromString(eDataType, initialValue);
      case XMLTypePackage.UNSIGNED_LONG:
        return createUnsignedLongFromString(eDataType, initialValue);
      case XMLTypePackage.UNSIGNED_SHORT:
        return createUnsignedShortFromString(eDataType, initialValue);
      case XMLTypePackage.UNSIGNED_SHORT_OBJECT:
        return createUnsignedShortObjectFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case XMLTypePackage.ANY_SIMPLE_TYPE:
        return convertAnySimpleTypeToString(eDataType, instanceValue);
      case XMLTypePackage.ANY_URI:
        return convertAnyURIToString(eDataType, instanceValue);
      case XMLTypePackage.BASE64_BINARY:
        return convertBase64BinaryToString(eDataType, instanceValue);
      case XMLTypePackage.BOOLEAN:
        return convertBooleanToString(eDataType, instanceValue);
      case XMLTypePackage.BOOLEAN_OBJECT:
        return convertBooleanObjectToString(eDataType, instanceValue);
      case XMLTypePackage.BYTE:
        return convertByteToString(eDataType, instanceValue);
      case XMLTypePackage.BYTE_OBJECT:
        return convertByteObjectToString(eDataType, instanceValue);
      case XMLTypePackage.DATE:
        return convertDateToString(eDataType, instanceValue);
      case XMLTypePackage.DATE_TIME:
        return convertDateTimeToString(eDataType, instanceValue);
      case XMLTypePackage.DECIMAL:
        return convertDecimalToString(eDataType, instanceValue);
      case XMLTypePackage.DOUBLE:
        return convertDoubleToString(eDataType, instanceValue);
      case XMLTypePackage.DOUBLE_OBJECT:
        return convertDoubleObjectToString(eDataType, instanceValue);
      case XMLTypePackage.DURATION:
        return convertDurationToString(eDataType, instanceValue);
      case XMLTypePackage.ENTITIES:
        return convertENTITIESToString(eDataType, instanceValue);
      case XMLTypePackage.ENTITIES_BASE:
        return convertENTITIESBaseToString(eDataType, instanceValue);
      case XMLTypePackage.ENTITY:
        return convertENTITYToString(eDataType, instanceValue);
      case XMLTypePackage.FLOAT:
        return convertFloatToString(eDataType, instanceValue);
      case XMLTypePackage.FLOAT_OBJECT:
        return convertFloatObjectToString(eDataType, instanceValue);
      case XMLTypePackage.GDAY:
        return convertGDayToString(eDataType, instanceValue);
      case XMLTypePackage.GMONTH:
        return convertGMonthToString(eDataType, instanceValue);
      case XMLTypePackage.GMONTH_DAY:
        return convertGMonthDayToString(eDataType, instanceValue);
      case XMLTypePackage.GYEAR:
        return convertGYearToString(eDataType, instanceValue);
      case XMLTypePackage.GYEAR_MONTH:
        return convertGYearMonthToString(eDataType, instanceValue);
      case XMLTypePackage.HEX_BINARY:
        return convertHexBinaryToString(eDataType, instanceValue);
      case XMLTypePackage.ID:
        return convertIDToString(eDataType, instanceValue);
      case XMLTypePackage.IDREF:
        return convertIDREFToString(eDataType, instanceValue);
      case XMLTypePackage.IDREFS:
        return convertIDREFSToString(eDataType, instanceValue);
      case XMLTypePackage.IDREFS_BASE:
        return convertIDREFSBaseToString(eDataType, instanceValue);
      case XMLTypePackage.INT:
        return convertIntToString(eDataType, instanceValue);
      case XMLTypePackage.INTEGER:
        return convertIntegerToString(eDataType, instanceValue);
      case XMLTypePackage.INT_OBJECT:
        return convertIntObjectToString(eDataType, instanceValue);
      case XMLTypePackage.LANGUAGE:
        return convertLanguageToString(eDataType, instanceValue);
      case XMLTypePackage.LONG:
        return convertLongToString(eDataType, instanceValue);
      case XMLTypePackage.LONG_OBJECT:
        return convertLongObjectToString(eDataType, instanceValue);
      case XMLTypePackage.NAME:
        return convertNameToString(eDataType, instanceValue);
      case XMLTypePackage.NC_NAME:
        return convertNCNameToString(eDataType, instanceValue);
      case XMLTypePackage.NEGATIVE_INTEGER:
        return convertNegativeIntegerToString(eDataType, instanceValue);
      case XMLTypePackage.NMTOKEN:
        return convertNMTOKENToString(eDataType, instanceValue);
      case XMLTypePackage.NMTOKENS:
        return convertNMTOKENSToString(eDataType, instanceValue);
      case XMLTypePackage.NMTOKENS_BASE:
        return convertNMTOKENSBaseToString(eDataType, instanceValue);
      case XMLTypePackage.NON_NEGATIVE_INTEGER:
        return convertNonNegativeIntegerToString(eDataType, instanceValue);
      case XMLTypePackage.NON_POSITIVE_INTEGER:
        return convertNonPositiveIntegerToString(eDataType, instanceValue);
      case XMLTypePackage.NORMALIZED_STRING:
        return convertNormalizedStringToString(eDataType, instanceValue);
      case XMLTypePackage.NOTATION:
        return convertNOTATIONToString(eDataType, instanceValue);
      case XMLTypePackage.POSITIVE_INTEGER:
        return convertPositiveIntegerToString(eDataType, instanceValue);
      case XMLTypePackage.QNAME:
        return convertQNameToString(eDataType, instanceValue);
      case XMLTypePackage.SHORT:
        return convertShortToString(eDataType, instanceValue);
      case XMLTypePackage.SHORT_OBJECT:
        return convertShortObjectToString(eDataType, instanceValue);
      case XMLTypePackage.STRING:
        return convertStringToString(eDataType, instanceValue);
      case XMLTypePackage.TIME:
        return convertTimeToString(eDataType, instanceValue);
      case XMLTypePackage.TOKEN:
        return convertTokenToString(eDataType, instanceValue);
      case XMLTypePackage.UNSIGNED_BYTE:
        return convertUnsignedByteToString(eDataType, instanceValue);
      case XMLTypePackage.UNSIGNED_BYTE_OBJECT:
        return convertUnsignedByteObjectToString(eDataType, instanceValue);
      case XMLTypePackage.UNSIGNED_INT:
        return convertUnsignedIntToString(eDataType, instanceValue);
      case XMLTypePackage.UNSIGNED_INT_OBJECT:
        return convertUnsignedIntObjectToString(eDataType, instanceValue);
      case XMLTypePackage.UNSIGNED_LONG:
        return convertUnsignedLongToString(eDataType, instanceValue);
      case XMLTypePackage.UNSIGNED_SHORT:
        return convertUnsignedShortToString(eDataType, instanceValue);
      case XMLTypePackage.UNSIGNED_SHORT_OBJECT:
        return convertUnsignedShortObjectToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnyType createAnyType()
  {
    AnyTypeImpl anyType = new AnyTypeImpl();
    return anyType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleAnyType createSimpleAnyType()
  {
    SimpleAnyTypeImpl simpleAnyType = new SimpleAnyTypeImpl();
    return simpleAnyType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLTypeDocumentRoot createXMLTypeDocumentRoot()
  {
    XMLTypeDocumentRootImpl xmlTypeDocumentRoot = new XMLTypeDocumentRootImpl();
    return xmlTypeDocumentRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createAnySimpleTypeFromString(EDataType eDataType, String initialValue)
  {
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertAnySimpleTypeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String createAnyURIFromString(EDataType eDataType, String initialValue)
  {
    // TODO no validation is performed on anyURI value
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertAnyURIToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createBase64BinaryFromString(EDataType eDataType, String initialValue)
  {
    // TODO no validation is performed on base64Binary
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertBase64BinaryToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Boolean createBooleanFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : booleanValueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertBooleanToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Boolean createBooleanObjectFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : booleanValueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertBooleanObjectToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public BigDecimal createDecimalFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : new BigDecimal(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertDecimalToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public BigInteger createIntegerFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : new BigInteger(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertIntegerToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createIntObjectFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Integer.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIntObjectToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Long createLongFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Long.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertLongToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Long createLongObjectFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Long.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertLongObjectToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Integer createIntFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Integer.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertIntToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Short createShortFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Short.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertShortToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Short createShortObjectFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Short.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertShortObjectToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Byte createByteFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Byte.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertByteToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Byte createByteObjectFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Byte.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertByteObjectToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createDateFromString(EDataType eDataType, String initialValue)
  {
    // TODO date value is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertDateToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createDateTimeFromString(EDataType eDataType, String initialValue)
  {
    // TODO dateTime is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertDateTimeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String createStringFromString(EDataType eDataType, String initialValue)
  {
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertStringToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Double createDoubleFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Double.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertDoubleToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Double createDoubleObjectFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Double.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertDoubleObjectToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createDurationFromString(EDataType eDataType, String initialValue)
  {
    // TODO duration is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertDurationToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List createENTITIESBaseFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    List result = new ArrayList();
    for (StringTokenizer stringTokenizer = new StringTokenizer(initialValue); stringTokenizer.hasMoreTokens(); )
    {
      String item = stringTokenizer.nextToken();
      result.add(XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getENTITY(), item));
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertENTITIESBaseToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null) return null;
    List list = (List)instanceValue;
    if (list.isEmpty()) return "";
    StringBuffer result = new StringBuffer();
    for (Iterator i = list.iterator(); i.hasNext(); )
    {
      result.append(XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getENTITY(), i.next()));
      result.append(' ');
    }
    return result.substring(0, result.length() - 1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String createNormalizedStringFromString(EDataType eDataType, String initialValue)
  {
    // TODO normalizedString is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertNormalizedStringToString(EDataType eDataType, Object instanceValue)
  {    
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String createTokenFromString(EDataType eDataType, String initialValue)
  {
    // TODO token is not validated
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNormalizedString(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertTokenToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String createNameFromString(EDataType eDataType, String initialValue)
  {
    // TODO name is not validated
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getToken(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertNameToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String createNCNameFromString(EDataType eDataType, String initialValue)
  {
    // TODO NCName is not validated
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getName_(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertNCNameToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String createENTITYFromString(EDataType eDataType, String initialValue)
  {
    // TODO entity is not validated
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNCName(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertENTITYToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List createENTITIESFromString(EDataType eDataType, String initialValue)
  {
    return (List)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getENTITIESBase(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertENTITIESToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getENTITIESBase(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Float createFloatFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Float.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertFloatToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Float createFloatObjectFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Float.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertFloatObjectToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createGDayFromString(EDataType eDataType, String initialValue)
  {
    //TODO gDay is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertGDayToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createGMonthFromString(EDataType eDataType, String initialValue)
  {
    // TODO gMonth is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertGMonthToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createGMonthDayFromString(EDataType eDataType, String initialValue)
  {
    // TODO gMonthDay is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertGMonthDayToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createGYearFromString(EDataType eDataType, String initialValue)
  {
    // TODO gYear is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertGYearToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createGYearMonthFromString(EDataType eDataType, String initialValue)
  {
    // TODO: gYearMonth is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertGYearMonthToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createHexBinaryFromString(EDataType eDataType, String initialValue)
  {
    // TODO hexBinary is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertHexBinaryToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createIDFromString(EDataType eDataType, String initialValue)
  {
    // TODO ID is not validated
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNCName(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertIDToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String createIDREFFromString(EDataType eDataType, String initialValue)
  {
    // TODO IDREF is not validated
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNCName(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertIDREFToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List createIDREFSBaseFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    List result = new ArrayList();
    for (StringTokenizer stringTokenizer = new StringTokenizer(initialValue); stringTokenizer.hasMoreTokens(); )
    {
      String item = stringTokenizer.nextToken();
      result.add(XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getIDREF(), item));
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIDREFSBaseToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null) return null;
    List list = (List)instanceValue;
    if (list.isEmpty()) return "";
    StringBuffer result = new StringBuffer();
    for (Iterator i = list.iterator(); i.hasNext(); )
    {
      result.append(XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getIDREF(), i.next()));
      result.append(' ');
    }
    return result.substring(0, result.length() - 1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List createIDREFSFromString(EDataType eDataType, String initialValue)
  {
    return (List)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getIDREFSBase(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIDREFSToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getIDREFSBase(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createLanguageFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getToken(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertLanguageToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public BigInteger createNonPositiveIntegerFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : new BigInteger(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertNonPositiveIntegerToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger createNegativeIntegerFromString(EDataType eDataType, String initialValue)
  {
    return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNonPositiveInteger(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertNegativeIntegerToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getNonPositiveInteger(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createNMTOKENFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getToken(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertNMTOKENToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List createNMTOKENSBaseFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    List result = new ArrayList();
    for (StringTokenizer stringTokenizer = new StringTokenizer(initialValue); stringTokenizer.hasMoreTokens(); )
    {
      String item = stringTokenizer.nextToken();
      result.add(XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNMTOKEN(), item));
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertNMTOKENSBaseToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null) return null;
    List list = (List)instanceValue;
    if (list.isEmpty()) return "";
    StringBuffer result = new StringBuffer();
    for (Iterator i = list.iterator(); i.hasNext(); )
    {
      result.append(XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getNMTOKEN(), i.next()));
      result.append(' ');
    }
    return result.substring(0, result.length() - 1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List createNMTOKENSFromString(EDataType eDataType, String initialValue)
  {
    return (List)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNMTOKENSBase(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertNMTOKENSToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getNMTOKENSBase(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public BigInteger createNonNegativeIntegerFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : new BigInteger(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertNonNegativeIntegerToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createNOTATIONFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertNOTATIONToString(EDataType eDataType, Object instanceValue)
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger createPositiveIntegerFromString(EDataType eDataType, String initialValue)
  {
    return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNonNegativeInteger(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertPositiveIntegerToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getNonNegativeInteger(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createQNameFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    
    String rawname = initialValue;
    int index = rawname.indexOf(":");

    String prefix = "";
    String localName = rawname;
    if (index != -1)
    {
      prefix    = rawname.substring(0, index).intern();
      localName = rawname.substring(index + 1).intern();
    }
    // TODO use XMLChar in xsd.DataValue to check local name and prefix

    return new QName("", localName, prefix);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertQNameToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object createTimeFromString(EDataType eDataType, String initialValue)
  {
    // TODO time is not validated
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertTimeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger createUnsignedLongFromString(EDataType eDataType, String initialValue)
  {
    return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNonNegativeInteger(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUnsignedLongToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getNonNegativeInteger(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Long createUnsignedIntFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Long.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertUnsignedIntToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Long createUnsignedIntObjectFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Long.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertUnsignedIntObjectToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Integer createUnsignedShortFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Integer.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertUnsignedShortToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Integer createUnsignedShortObjectFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : Integer.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertUnsignedShortObjectToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Short createUnsignedByteFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : new Short(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertUnsignedByteToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Short createUnsignedByteObjectFromString(EDataType eDataType, String initialValue)
  {
    return initialValue == null ? null : new Short(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUnsignedByteObjectToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLTypePackage getXMLTypePackage()
  {
    return (XMLTypePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static XMLTypePackage getPackage()
  {
    return XMLTypePackage.eINSTANCE;
  }
  
  protected Boolean booleanValueOf(String initialValue)
  {
    if ("true".equals(initialValue) || "1".equals(initialValue))
    {
      return Boolean.TRUE;
    }
    else if ("false".equals(initialValue) || "0".equals(initialValue))
    {
      return Boolean.FALSE;
    }
    throw new IllegalArgumentException("'" + initialValue + "' is no a valid 'boolean' value");
  }
} //XMLTypeFactoryImpl
