/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: XMLTypeValidator.java,v 1.3 2004/05/26 15:17:34 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.type.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.ecore.xml.type.XMLTypePackage
 * @generated
 */
public class XMLTypeValidator extends EObjectValidator
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final XMLTypeValidator INSTANCE = new XMLTypeValidator();

  /**
   * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.common.util.Diagnostic#getSource()
   * @see org.eclipse.emf.common.util.Diagnostic#getCode()
   * @generated
   */
  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore.xml.type";

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLTypeValidator()
  {
  }

  /**
   * Returns the package of this validator switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EPackage getEPackage()
  {
    return XMLTypePackage.eINSTANCE;
  }

  /**
   * Calls <code>validateXXX</code> for the corresonding classifier of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map context)
  {
    switch (classifierID)
    {
      case XMLTypePackage.ANY_TYPE:
        return validateAnyType((AnyType)value, diagnostics, context);
      case XMLTypePackage.SIMPLE_ANY_TYPE:
        return validateSimpleAnyType((SimpleAnyType)value, diagnostics, context);
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT:
        return validateXMLTypeDocumentRoot((XMLTypeDocumentRoot)value, diagnostics, context);
      case XMLTypePackage.ANY_SIMPLE_TYPE:
        return validateAnySimpleType((Object)value, diagnostics, context);
      case XMLTypePackage.ANY_URI:
        return validateAnyURI((String)value, diagnostics, context);
      case XMLTypePackage.BASE64_BINARY:
        return validateBase64Binary((byte[])value, diagnostics, context);
      case XMLTypePackage.BOOLEAN:
        return validateBoolean(((Boolean)value).booleanValue(), diagnostics, context);
      case XMLTypePackage.BOOLEAN_OBJECT:
        return validateBooleanObject((Boolean)value, diagnostics, context);
      case XMLTypePackage.BYTE:
        return validateByte(((Byte)value).byteValue(), diagnostics, context);
      case XMLTypePackage.BYTE_OBJECT:
        return validateByteObject((Byte)value, diagnostics, context);
      case XMLTypePackage.DATE:
        return validateDate((Object)value, diagnostics, context);
      case XMLTypePackage.DATE_TIME:
        return validateDateTime((Object)value, diagnostics, context);
      case XMLTypePackage.DECIMAL:
        return validateDecimal((BigDecimal)value, diagnostics, context);
      case XMLTypePackage.DOUBLE:
        return validateDouble(((Double)value).doubleValue(), diagnostics, context);
      case XMLTypePackage.DOUBLE_OBJECT:
        return validateDoubleObject((Double)value, diagnostics, context);
      case XMLTypePackage.DURATION:
        return validateDuration((Object)value, diagnostics, context);
      case XMLTypePackage.ENTITIES:
        return validateENTITIES((List)value, diagnostics, context);
      case XMLTypePackage.ENTITIES_BASE:
        return validateENTITIESBase((List)value, diagnostics, context);
      case XMLTypePackage.ENTITY:
        return validateENTITY((String)value, diagnostics, context);
      case XMLTypePackage.FLOAT:
        return validateFloat(((Float)value).floatValue(), diagnostics, context);
      case XMLTypePackage.FLOAT_OBJECT:
        return validateFloatObject((Float)value, diagnostics, context);
      case XMLTypePackage.GDAY:
        return validateGDay((Object)value, diagnostics, context);
      case XMLTypePackage.GMONTH:
        return validateGMonth((Object)value, diagnostics, context);
      case XMLTypePackage.GMONTH_DAY:
        return validateGMonthDay((Object)value, diagnostics, context);
      case XMLTypePackage.GYEAR:
        return validateGYear((Object)value, diagnostics, context);
      case XMLTypePackage.GYEAR_MONTH:
        return validateGYearMonth((Object)value, diagnostics, context);
      case XMLTypePackage.HEX_BINARY:
        return validateHexBinary((byte[])value, diagnostics, context);
      case XMLTypePackage.ID:
        return validateID((String)value, diagnostics, context);
      case XMLTypePackage.IDREF:
        return validateIDREF((String)value, diagnostics, context);
      case XMLTypePackage.IDREFS:
        return validateIDREFS((List)value, diagnostics, context);
      case XMLTypePackage.IDREFS_BASE:
        return validateIDREFSBase((List)value, diagnostics, context);
      case XMLTypePackage.INT:
        return validateInt(((Integer)value).intValue(), diagnostics, context);
      case XMLTypePackage.INTEGER:
        return validateInteger((BigInteger)value, diagnostics, context);
      case XMLTypePackage.INT_OBJECT:
        return validateIntObject((Integer)value, diagnostics, context);
      case XMLTypePackage.LANGUAGE:
        return validateLanguage((String)value, diagnostics, context);
      case XMLTypePackage.LONG:
        return validateLong(((Long)value).longValue(), diagnostics, context);
      case XMLTypePackage.LONG_OBJECT:
        return validateLongObject((Long)value, diagnostics, context);
      case XMLTypePackage.NAME:
        return validateName((String)value, diagnostics, context);
      case XMLTypePackage.NC_NAME:
        return validateNCName((String)value, diagnostics, context);
      case XMLTypePackage.NEGATIVE_INTEGER:
        return validateNegativeInteger((BigInteger)value, diagnostics, context);
      case XMLTypePackage.NMTOKEN:
        return validateNMTOKEN((String)value, diagnostics, context);
      case XMLTypePackage.NMTOKENS:
        return validateNMTOKENS((List)value, diagnostics, context);
      case XMLTypePackage.NMTOKENS_BASE:
        return validateNMTOKENSBase((List)value, diagnostics, context);
      case XMLTypePackage.NON_NEGATIVE_INTEGER:
        return validateNonNegativeInteger((BigInteger)value, diagnostics, context);
      case XMLTypePackage.NON_POSITIVE_INTEGER:
        return validateNonPositiveInteger((BigInteger)value, diagnostics, context);
      case XMLTypePackage.NORMALIZED_STRING:
        return validateNormalizedString((String)value, diagnostics, context);
      case XMLTypePackage.NOTATION:
        return validateNOTATION((Object)value, diagnostics, context);
      case XMLTypePackage.POSITIVE_INTEGER:
        return validatePositiveInteger((BigInteger)value, diagnostics, context);
      case XMLTypePackage.QNAME:
        return validateQName((Object)value, diagnostics, context);
      case XMLTypePackage.SHORT:
        return validateShort(((Short)value).shortValue(), diagnostics, context);
      case XMLTypePackage.SHORT_OBJECT:
        return validateShortObject((Short)value, diagnostics, context);
      case XMLTypePackage.STRING:
        return validateString((String)value, diagnostics, context);
      case XMLTypePackage.TIME:
        return validateTime((Object)value, diagnostics, context);
      case XMLTypePackage.TOKEN:
        return validateToken((String)value, diagnostics, context);
      case XMLTypePackage.UNSIGNED_BYTE:
        return validateUnsignedByte(((Short)value).shortValue(), diagnostics, context);
      case XMLTypePackage.UNSIGNED_BYTE_OBJECT:
        return validateUnsignedByteObject((Short)value, diagnostics, context);
      case XMLTypePackage.UNSIGNED_INT:
        return validateUnsignedInt(((Long)value).longValue(), diagnostics, context);
      case XMLTypePackage.UNSIGNED_INT_OBJECT:
        return validateUnsignedIntObject((Long)value, diagnostics, context);
      case XMLTypePackage.UNSIGNED_LONG:
        return validateUnsignedLong((BigInteger)value, diagnostics, context);
      case XMLTypePackage.UNSIGNED_SHORT:
        return validateUnsignedShort(((Integer)value).intValue(), diagnostics, context);
      case XMLTypePackage.UNSIGNED_SHORT_OBJECT:
        return validateUnsignedShortObject((Integer)value, diagnostics, context);
      default: 
        return true;
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateAnyType(AnyType anyType, DiagnosticChain diagnostics, Map context)
  {
    return validate_EveryDefaultConstraint(anyType, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateSimpleAnyType(SimpleAnyType simpleAnyType, DiagnosticChain diagnostics, Map context)
  {
    return validate_EveryDefaultConstraint(simpleAnyType, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateXMLTypeDocumentRoot(XMLTypeDocumentRoot xmlTypeDocumentRoot, DiagnosticChain diagnostics, Map context)
  {
    return validate_EveryDefaultConstraint(xmlTypeDocumentRoot, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateAnySimpleType(Object anySimpleType, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateAnyURI(String anyURI, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateBase64Binary(byte[] base64Binary, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateBoolean(boolean boolean_, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateBooleanObject(Boolean booleanObject, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateByte(byte byte_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateByte_Min(byte_, diagnostics, context);
    if (result || diagnostics != null) result &= validateByte_Max(byte_, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateByte_Min
   */
  public static final byte BYTE__MIN__VALUE = -128;

  /**
   * Validates the Min constraint of '<em>Byte</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateByte_Min(byte byte_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = byte_ >= BYTE__MIN__VALUE;
    if (!result && diagnostics != null) 
      reportMinViolation(XMLTypePackage.eINSTANCE.getByte(), new Byte(byte_), new Byte(BYTE__MIN__VALUE), true, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateByte_Max
   */
  public static final byte BYTE__MAX__VALUE = 127;

  /**
   * Validates the Max constraint of '<em>Byte</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateByte_Max(byte byte_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = byte_ <= BYTE__MAX__VALUE;
    if (!result && diagnostics != null) 
      reportMaxViolation(XMLTypePackage.eINSTANCE.getByte(), new Byte(byte_), new Byte(BYTE__MAX__VALUE), true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateByteObject(Byte byteObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateByte_Min(byteObject.byteValue(), diagnostics, context);
    if (result || diagnostics != null) result &= validateByte_Max(byteObject.byteValue(), diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateDate(Object date, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateDateTime(Object dateTime, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateDecimal(BigDecimal decimal, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateDouble(double double_, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateDoubleObject(Double doubleObject, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateDuration(Object duration, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateENTITIES(List entities, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateENTITIES_MinLength(entities, diagnostics, context);
    return result;
  }

  /**
   * Validates the MinLength constraint of '<em>ENTITIES</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateENTITIES_MinLength(List entities, DiagnosticChain diagnostics, Map context)
  {
    int length = entities.size();  
    boolean result = length < 1;
    if (!result && diagnostics != null) 
      reportMinLengthViolation(XMLTypePackage.eINSTANCE.getENTITIES(), entities, length, 1, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateENTITIESBase(List entitiesBase, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateENTITY(String entity, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateNCName_Pattern(entity, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateFloat(float float_, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateFloatObject(Float floatObject, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGDay(Object gDay, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGMonth(Object gMonth, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGMonthDay(Object gMonthDay, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGYear(Object gYear, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGYearMonth(Object gYearMonth, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateHexBinary(byte[] hexBinary, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateID(String id, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateNCName_Pattern(id, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateIDREF(String idref, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateNCName_Pattern(idref, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateIDREFS(List idrefs, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateIDREFS_MinLength(idrefs, diagnostics, context);
    return result;
  }

  /**
   * Validates the MinLength constraint of '<em>IDREFS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateIDREFS_MinLength(List idrefs, DiagnosticChain diagnostics, Map context)
  {
    int length = idrefs.size();  
    boolean result = length < 1;
    if (!result && diagnostics != null) 
      reportMinLengthViolation(XMLTypePackage.eINSTANCE.getIDREFS(), idrefs, length, 1, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateIDREFSBase(List idrefsBase, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateInt(int int_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateInt_Min(int_, diagnostics, context);
    if (result || diagnostics != null) result &= validateInt_Max(int_, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateInt_Min
   */
  public static final int INT__MIN__VALUE = -2147483648;

  /**
   * Validates the Min constraint of '<em>Int</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateInt_Min(int int_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = int_ >= INT__MIN__VALUE;
    if (!result && diagnostics != null) 
      reportMinViolation(XMLTypePackage.eINSTANCE.getInt(), new Integer(int_), new Integer(INT__MIN__VALUE), true, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateInt_Max
   */
  public static final int INT__MAX__VALUE = 2147483647;

  /**
   * Validates the Max constraint of '<em>Int</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateInt_Max(int int_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = int_ <= INT__MAX__VALUE;
    if (!result && diagnostics != null) 
      reportMaxViolation(XMLTypePackage.eINSTANCE.getInt(), new Integer(int_), new Integer(INT__MAX__VALUE), true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateInteger(BigInteger integer, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateInteger_Pattern(integer, diagnostics, context);
    if (result || diagnostics != null) result &= validateInteger_FractionDigits(integer, diagnostics, context);
    return result;
  }

  /**
   * Validates the Pattern constraint of '<em>Integer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateInteger_Pattern(BigInteger integer, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * Validates the FractionDigits constraint of '<em>Integer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateInteger_FractionDigits(BigInteger integer, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateIntObject(Integer intObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateInt_Min(intObject.intValue(), diagnostics, context);
    if (result || diagnostics != null) result &= validateInt_Max(intObject.intValue(), diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateLanguage(String language, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateLanguage_Pattern(language, diagnostics, context);
    return result;
  }

  /**
   * Validates the Pattern constraint of '<em>Language</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateLanguage_Pattern(String language, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateLong(long long_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateLong_Min(long_, diagnostics, context);
    if (result || diagnostics != null) result &= validateLong_Max(long_, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateLong_Min
   */
  public static final long LONG__MIN__VALUE = -9223372036854775808L;

  /**
   * Validates the Min constraint of '<em>Long</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateLong_Min(long long_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = long_ >= LONG__MIN__VALUE;
    if (!result && diagnostics != null) 
      reportMinViolation(XMLTypePackage.eINSTANCE.getLong(), new Long(long_), new Long(LONG__MIN__VALUE), true, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateLong_Max
   */
  public static final long LONG__MAX__VALUE = 9223372036854775807L;

  /**
   * Validates the Max constraint of '<em>Long</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateLong_Max(long long_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = long_ <= LONG__MAX__VALUE;
    if (!result && diagnostics != null) 
      reportMaxViolation(XMLTypePackage.eINSTANCE.getLong(), new Long(long_), new Long(LONG__MAX__VALUE), true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateLongObject(Long longObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateLong_Min(longObject.longValue(), diagnostics, context);
    if (result || diagnostics != null) result &= validateLong_Max(longObject.longValue(), diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateName(String name, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateName_Pattern(name, diagnostics, context);
    return result;
  }

  /**
   * Validates the Pattern constraint of '<em>Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateName_Pattern(String name, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNCName(String ncName, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateNCName_Pattern(ncName, diagnostics, context);
    return result;
  }

  /**
   * Validates the Pattern constraint of '<em>NC Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNCName_Pattern(String ncName, DiagnosticChain diagnostics, Map context)
  {
    // TODO override the constraint
    return validateName_Pattern(ncName, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNegativeInteger(BigInteger negativeInteger, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateNegativeInteger_Max(negativeInteger, diagnostics, context);
    if (result || diagnostics != null) result &= validateInteger_Pattern(negativeInteger, diagnostics, context);
    if (result || diagnostics != null) result &= validateInteger_FractionDigits(negativeInteger, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateNegativeInteger_Max
   */
  public static final BigInteger NEGATIVE_INTEGER__MAX__VALUE = new BigInteger("-1");

  /**
   * Validates the Max constraint of '<em>Negative Integer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNegativeInteger_Max(BigInteger negativeInteger, DiagnosticChain diagnostics, Map context)
  {
    boolean result = negativeInteger.compareTo(NEGATIVE_INTEGER__MAX__VALUE) <= 0;
    if (!result && diagnostics != null) 
      reportMaxViolation(XMLTypePackage.eINSTANCE.getNegativeInteger(), negativeInteger, NEGATIVE_INTEGER__MAX__VALUE, true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNMTOKEN(String nmtoken, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateNMTOKEN_Pattern(nmtoken, diagnostics, context);
    return result;
  }

  /**
   * Validates the Pattern constraint of '<em>NMTOKEN</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNMTOKEN_Pattern(String nmtoken, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNMTOKENS(List nmtokens, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateNMTOKENS_MinLength(nmtokens, diagnostics, context);
    return result;
  }

  /**
   * Validates the MinLength constraint of '<em>NMTOKENS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNMTOKENS_MinLength(List nmtokens, DiagnosticChain diagnostics, Map context)
  {
    int length = nmtokens.size();  
    boolean result = length < 1;
    if (!result && diagnostics != null) 
      reportMinLengthViolation(XMLTypePackage.eINSTANCE.getNMTOKENS(), nmtokens, length, 1, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNMTOKENSBase(List nmtokensBase, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNonNegativeInteger(BigInteger nonNegativeInteger, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateInteger_Pattern(nonNegativeInteger, diagnostics, context);
    if (result || diagnostics != null) result &= validateInteger_FractionDigits(nonNegativeInteger, diagnostics, context);
    if (result || diagnostics != null) result &= validateNonNegativeInteger_Min(nonNegativeInteger, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateNonNegativeInteger_Min
   */
  public static final BigInteger NON_NEGATIVE_INTEGER__MIN__VALUE = new BigInteger("0");

  /**
   * Validates the Min constraint of '<em>Non Negative Integer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNonNegativeInteger_Min(BigInteger nonNegativeInteger, DiagnosticChain diagnostics, Map context)
  {
    boolean result = nonNegativeInteger.compareTo(NON_NEGATIVE_INTEGER__MIN__VALUE) >= 0;
    if (!result && diagnostics != null) 
      reportMinViolation(XMLTypePackage.eINSTANCE.getNonNegativeInteger(), nonNegativeInteger, NON_NEGATIVE_INTEGER__MIN__VALUE, true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNonPositiveInteger(BigInteger nonPositiveInteger, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateInteger_Pattern(nonPositiveInteger, diagnostics, context);
    if (result || diagnostics != null) result &= validateInteger_FractionDigits(nonPositiveInteger, diagnostics, context);
    if (result || diagnostics != null) result &= validateNonPositiveInteger_Max(nonPositiveInteger, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateNonPositiveInteger_Max
   */
  public static final BigInteger NON_POSITIVE_INTEGER__MAX__VALUE = new BigInteger("0");

  /**
   * Validates the Max constraint of '<em>Non Positive Integer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNonPositiveInteger_Max(BigInteger nonPositiveInteger, DiagnosticChain diagnostics, Map context)
  {
    boolean result = nonPositiveInteger.compareTo(NON_POSITIVE_INTEGER__MAX__VALUE) <= 0;
    if (!result && diagnostics != null) 
      reportMaxViolation(XMLTypePackage.eINSTANCE.getNonPositiveInteger(), nonPositiveInteger, NON_POSITIVE_INTEGER__MAX__VALUE, true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNormalizedString(String normalizedString, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateNOTATION(Object notation, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validatePositiveInteger(BigInteger positiveInteger, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validatePositiveInteger_Min(positiveInteger, diagnostics, context);
    if (result || diagnostics != null) result &= validateInteger_Pattern(positiveInteger, diagnostics, context);
    if (result || diagnostics != null) result &= validateInteger_FractionDigits(positiveInteger, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validatePositiveInteger_Min
   */
  public static final BigInteger POSITIVE_INTEGER__MIN__VALUE = new BigInteger("1");

  /**
   * Validates the Min constraint of '<em>Positive Integer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validatePositiveInteger_Min(BigInteger positiveInteger, DiagnosticChain diagnostics, Map context)
  {
    boolean result = positiveInteger.compareTo(POSITIVE_INTEGER__MIN__VALUE) >= 0;
    if (!result && diagnostics != null) 
      reportMinViolation(XMLTypePackage.eINSTANCE.getPositiveInteger(), positiveInteger, POSITIVE_INTEGER__MIN__VALUE, true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateQName(Object qName, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateShort(short short_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateShort_Min(short_, diagnostics, context);
    if (result || diagnostics != null) result &= validateShort_Max(short_, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateShort_Min
   */
  public static final short SHORT__MIN__VALUE = -32768;

  /**
   * Validates the Min constraint of '<em>Short</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateShort_Min(short short_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = short_ >= SHORT__MIN__VALUE;
    if (!result && diagnostics != null) 
      reportMinViolation(XMLTypePackage.eINSTANCE.getShort(), new Short(short_), new Short(SHORT__MIN__VALUE), true, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateShort_Max
   */
  public static final short SHORT__MAX__VALUE = 32767;

  /**
   * Validates the Max constraint of '<em>Short</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateShort_Max(short short_, DiagnosticChain diagnostics, Map context)
  {
    boolean result = short_ <= SHORT__MAX__VALUE;
    if (!result && diagnostics != null) 
      reportMaxViolation(XMLTypePackage.eINSTANCE.getShort(), new Short(short_), new Short(SHORT__MAX__VALUE), true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateShortObject(Short shortObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateShort_Min(shortObject.shortValue(), diagnostics, context);
    if (result || diagnostics != null) result &= validateShort_Max(shortObject.shortValue(), diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateString(String string, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateTime(Object time, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateToken(String token, DiagnosticChain diagnostics, Map context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedByte(short unsignedByte, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateUnsignedByte_Max(unsignedByte, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateUnsignedByte_Max
   */
  public static final short UNSIGNED_BYTE__MAX__VALUE = 255;

  /**
   * Validates the Max constraint of '<em>Unsigned Byte</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedByte_Max(short unsignedByte, DiagnosticChain diagnostics, Map context)
  {
    boolean result = unsignedByte <= UNSIGNED_BYTE__MAX__VALUE;
    if (!result && diagnostics != null) 
      reportMaxViolation(XMLTypePackage.eINSTANCE.getUnsignedByte(), new Short(unsignedByte), new Short(UNSIGNED_BYTE__MAX__VALUE), true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedByteObject(Short unsignedByteObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateUnsignedByte_Max(unsignedByteObject.shortValue(), diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedInt(long unsignedInt, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateUnsignedInt_Max(unsignedInt, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateUnsignedInt_Max
   */
  public static final long UNSIGNED_INT__MAX__VALUE = 4294967295L;

  /**
   * Validates the Max constraint of '<em>Unsigned Int</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedInt_Max(long unsignedInt, DiagnosticChain diagnostics, Map context)
  {
    boolean result = unsignedInt <= UNSIGNED_INT__MAX__VALUE;
    if (!result && diagnostics != null) 
      reportMaxViolation(XMLTypePackage.eINSTANCE.getUnsignedInt(), new Long(unsignedInt), new Long(UNSIGNED_INT__MAX__VALUE), true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedIntObject(Long unsignedIntObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateUnsignedInt_Max(unsignedIntObject.longValue(), diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedLong(BigInteger unsignedLong, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateNonNegativeInteger_Min(unsignedLong, diagnostics, context);
    if (result || diagnostics != null) result &= validateInteger_Pattern(unsignedLong, diagnostics, context);
    if (result || diagnostics != null) result &= validateInteger_FractionDigits(unsignedLong, diagnostics, context);
    if (result || diagnostics != null) result &= validateUnsignedLong_Max(unsignedLong, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateUnsignedLong_Max
   */
  public static final BigInteger UNSIGNED_LONG__MAX__VALUE = new BigInteger("18446744073709551615");

  /**
   * Validates the Max constraint of '<em>Unsigned Long</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedLong_Max(BigInteger unsignedLong, DiagnosticChain diagnostics, Map context)
  {
    boolean result = unsignedLong.compareTo(UNSIGNED_LONG__MAX__VALUE) <= 0;
    if (!result && diagnostics != null) 
      reportMaxViolation(XMLTypePackage.eINSTANCE.getUnsignedLong(), unsignedLong, UNSIGNED_LONG__MAX__VALUE, true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedShort(int unsignedShort, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateUnsignedShort_Max(unsignedShort, diagnostics, context);
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @see #validateUnsignedShort_Max
   */
  public static final int UNSIGNED_SHORT__MAX__VALUE = 65535;

  /**
   * Validates the Max constraint of '<em>Unsigned Short</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedShort_Max(int unsignedShort, DiagnosticChain diagnostics, Map context)
  {
    boolean result = unsignedShort <= UNSIGNED_SHORT__MAX__VALUE;
    if (!result && diagnostics != null) 
      reportMaxViolation(XMLTypePackage.eINSTANCE.getUnsignedShort(), new Integer(unsignedShort), new Integer(UNSIGNED_SHORT__MAX__VALUE), true, diagnostics, context);
    return result; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateUnsignedShortObject(Integer unsignedShortObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = validateUnsignedShort_Max(unsignedShortObject.intValue(), diagnostics, context);
    return result;
  }

} //XMLTypeValidator
