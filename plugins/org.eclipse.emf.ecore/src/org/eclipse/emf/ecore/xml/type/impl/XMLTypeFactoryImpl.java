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
 * $Id: XMLTypeFactoryImpl.java,v 1.4 2004/04/10 20:30:57 emerks Exp $
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
   * @generated
   */
  public String createAnyURIFromString(EDataType eDataType, String initialValue)
  {
    return (String)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertAnyURIToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createBase64BinaryFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertBase64BinaryToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Boolean createBooleanFromString(EDataType eDataType, String initialValue)
  {
    return (Boolean)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertBooleanToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Boolean createBooleanObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Boolean)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getBoolean(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertBooleanObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getBoolean(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigDecimal createDecimalFromString(EDataType eDataType, String initialValue)
  {
    return (BigDecimal)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertDecimalToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger createIntegerFromString(EDataType eDataType, String initialValue)
  {
    return (BigInteger)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIntegerToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createIntObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Integer)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInt(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIntObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInt(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Long createLongFromString(EDataType eDataType, String initialValue)
  {
    return (Long)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertLongToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Long createLongObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Long)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getLong(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertLongObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getLong(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createIntFromString(EDataType eDataType, String initialValue)
  {
    return (Integer)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIntToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Short createShortFromString(EDataType eDataType, String initialValue)
  {
    return (Short)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertShortToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Short createShortObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Short)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getShort(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertShortObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getShort(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Byte createByteFromString(EDataType eDataType, String initialValue)
  {
    return (Byte)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertByteToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Byte createByteObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Byte)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getByte(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertByteObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getByte(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createDateFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertDateToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createDateTimeFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertDateTimeToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createStringFromString(EDataType eDataType, String initialValue)
  {
    return (String)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertStringToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Double createDoubleFromString(EDataType eDataType, String initialValue)
  {
    return (Double)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertDoubleToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Double createDoubleObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Double)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getDouble(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertDoubleObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getDouble(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createDurationFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertDurationToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
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
   * @generated
   */
  public String createNormalizedStringFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertNormalizedStringToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createTokenFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNormalizedString(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertTokenToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getNormalizedString(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createNameFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getToken(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertNameToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getToken(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createNCNameFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getName_(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertNCNameToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getName_(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createENTITYFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNCName(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertENTITYToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getNCName(), instanceValue);
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
   * @generated
   */
  public Float createFloatFromString(EDataType eDataType, String initialValue)
  {
    return (Float)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertFloatToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Float createFloatObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Float)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getFloat(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertFloatObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getFloat(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createGDayFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGDayToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createGMonthFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGMonthToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createGMonthDayFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGMonthDayToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createGYearFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGYearToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createGYearMonthFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGYearMonthToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createHexBinaryFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertHexBinaryToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createIDFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNCName(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIDToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getNCName(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String createIDREFFromString(EDataType eDataType, String initialValue)
  {
    return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNCName(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIDREFToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getNCName(), instanceValue);
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
   * @generated
   */
  public String convertLanguageToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getToken(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger createNonPositiveIntegerFromString(EDataType eDataType, String initialValue)
  {
    return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertNonPositiveIntegerToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
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
   * @generated
   */
  public String convertNMTOKENToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getToken(), instanceValue);
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
   * @generated
   */
  public BigInteger createNonNegativeIntegerFromString(EDataType eDataType, String initialValue)
  {
    return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertNonNegativeIntegerToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
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
   * @generated
   */
  public String convertNOTATIONToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
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
   * @generated
   */
  public Object createQNameFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertQNameToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createTimeFromString(EDataType eDataType, String initialValue)
  {
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getAnySimpleType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertTimeToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getAnySimpleType(), instanceValue);
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
   * @generated
   */
  public Long createUnsignedIntFromString(EDataType eDataType, String initialValue)
  {
    return (Long)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUnsignedIntToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Long createUnsignedIntObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Long)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getUnsignedInt(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUnsignedIntObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getUnsignedInt(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createUnsignedShortFromString(EDataType eDataType, String initialValue)
  {
    return (Integer)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUnsignedShortToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createUnsignedShortObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Integer)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getUnsignedShort(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUnsignedShortObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getUnsignedShort(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Short createUnsignedByteFromString(EDataType eDataType, String initialValue)
  {
    return (Short)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUnsignedByteToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Short createUnsignedByteObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Short)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getUnsignedByte(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUnsignedByteObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getUnsignedByte(), instanceValue);
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
} //XMLTypeFactoryImpl
