/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EcoreFactoryImpl.java,v 1.3 2004/03/10 00:25:09 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EcoreFactoryImpl extends EFactoryImpl implements EcoreFactory
{
  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EcoreFactoryImpl()
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
      case EcorePackage.EATTRIBUTE: return createEAttribute();
      case EcorePackage.EANNOTATION: return createEAnnotation();
      case EcorePackage.ECLASS: return createEClass();
      case EcorePackage.EDATA_TYPE: return createEDataType();
      case EcorePackage.EENUM: return createEEnum();
      case EcorePackage.EENUM_LITERAL: return createEEnumLiteral();
      case EcorePackage.EFACTORY: return createEFactory();
      case EcorePackage.EOBJECT: return createEObject();
      case EcorePackage.EOPERATION: return createEOperation();
      case EcorePackage.EPACKAGE: return createEPackage();
      case EcorePackage.EPARAMETER: return createEParameter();
      case EcorePackage.EREFERENCE: return createEReference();
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY: return (EObject)createEStringToStringMapEntry();
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
      case EcorePackage.EBIG_DECIMAL:
        return createEBigDecimalFromString(eDataType, initialValue);
      case EcorePackage.EBIG_INTEGER:
        return createEBigIntegerFromString(eDataType, initialValue);
      case EcorePackage.EBOOLEAN:
        return createEBooleanFromString(eDataType, initialValue);
      case EcorePackage.EBOOLEAN_OBJECT:
        return createEBooleanObjectFromString(eDataType, initialValue);
      case EcorePackage.EBYTE:
        return createEByteFromString(eDataType, initialValue);
      case EcorePackage.EBYTE_ARRAY:
        return createEByteArrayFromString(eDataType, initialValue);
      case EcorePackage.EBYTE_OBJECT:
        return createEByteObjectFromString(eDataType, initialValue);
      case EcorePackage.ECHAR:
        return createECharFromString(eDataType, initialValue);
      case EcorePackage.ECHARACTER_OBJECT:
        return createECharacterObjectFromString(eDataType, initialValue);
      case EcorePackage.EDATE:
        return createEDateFromString(eDataType, initialValue);
      case EcorePackage.EDOUBLE:
        return createEDoubleFromString(eDataType, initialValue);
      case EcorePackage.EDOUBLE_OBJECT:
        return createEDoubleObjectFromString(eDataType, initialValue);
      case EcorePackage.EFEATURE_MAP_ENTRY:
        return createEFeatureMapEntryFromString(eDataType, initialValue);
      case EcorePackage.EFLOAT:
        return createEFloatFromString(eDataType, initialValue);
      case EcorePackage.EFLOAT_OBJECT:
        return createEFloatObjectFromString(eDataType, initialValue);
      case EcorePackage.EINT:
        return createEIntFromString(eDataType, initialValue);
      case EcorePackage.EINTEGER_OBJECT:
        return createEIntegerObjectFromString(eDataType, initialValue);
      case EcorePackage.EJAVA_CLASS:
        return createEJavaClassFromString(eDataType, initialValue);
      case EcorePackage.ELONG:
        return createELongFromString(eDataType, initialValue);
      case EcorePackage.ELONG_OBJECT:
        return createELongObjectFromString(eDataType, initialValue);
      case EcorePackage.ESHORT:
        return createEShortFromString(eDataType, initialValue);
      case EcorePackage.ESHORT_OBJECT:
        return createEShortObjectFromString(eDataType, initialValue);
      case EcorePackage.ESTRING:
        return createEStringFromString(eDataType, initialValue);
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
      case EcorePackage.EBIG_DECIMAL:
        return convertEBigDecimalToString(eDataType, instanceValue);
      case EcorePackage.EBIG_INTEGER:
        return convertEBigIntegerToString(eDataType, instanceValue);
      case EcorePackage.EBOOLEAN:
        return convertEBooleanToString(eDataType, instanceValue);
      case EcorePackage.EBOOLEAN_OBJECT:
        return convertEBooleanObjectToString(eDataType, instanceValue);
      case EcorePackage.EBYTE:
        return convertEByteToString(eDataType, instanceValue);
      case EcorePackage.EBYTE_ARRAY:
        return convertEByteArrayToString(eDataType, instanceValue);
      case EcorePackage.EBYTE_OBJECT:
        return convertEByteObjectToString(eDataType, instanceValue);
      case EcorePackage.ECHAR:
        return convertECharToString(eDataType, instanceValue);
      case EcorePackage.ECHARACTER_OBJECT:
        return convertECharacterObjectToString(eDataType, instanceValue);
      case EcorePackage.EDATE:
        return convertEDateToString(eDataType, instanceValue);
      case EcorePackage.EDOUBLE:
        return convertEDoubleToString(eDataType, instanceValue);
      case EcorePackage.EDOUBLE_OBJECT:
        return convertEDoubleObjectToString(eDataType, instanceValue);
      case EcorePackage.EFEATURE_MAP_ENTRY:
        return convertEFeatureMapEntryToString(eDataType, instanceValue);
      case EcorePackage.EFLOAT:
        return convertEFloatToString(eDataType, instanceValue);
      case EcorePackage.EFLOAT_OBJECT:
        return convertEFloatObjectToString(eDataType, instanceValue);
      case EcorePackage.EINT:
        return convertEIntToString(eDataType, instanceValue);
      case EcorePackage.EINTEGER_OBJECT:
        return convertEIntegerObjectToString(eDataType, instanceValue);
      case EcorePackage.EJAVA_CLASS:
        return convertEJavaClassToString(eDataType, instanceValue);
      case EcorePackage.ELONG:
        return convertELongToString(eDataType, instanceValue);
      case EcorePackage.ELONG_OBJECT:
        return convertELongObjectToString(eDataType, instanceValue);
      case EcorePackage.ESHORT:
        return convertEShortToString(eDataType, instanceValue);
      case EcorePackage.ESHORT_OBJECT:
        return convertEShortObjectToString(eDataType, instanceValue);
      case EcorePackage.ESTRING:
        return convertEStringToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject createEObject()
  {
    EObjectImpl eObject = new EObjectImpl();
    return eObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute createEAttribute()
  {
    EAttributeImpl eAttribute = new EAttributeImpl();
    return eAttribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAnnotation createEAnnotation()
  {
    EAnnotationImpl eAnnotation = new EAnnotationImpl();
    return eAnnotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass createEClass()
  {
    EClassImpl eClass = new EClassImpl();
    return eClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType createEDataType()
  {
    EDataTypeImpl eDataType = new EDataTypeImpl();
    return eDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EParameter createEParameter()
  {
    EParameterImpl eParameter = new EParameterImpl();
    return eParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation createEOperation()
  {
    EOperationImpl eOperation = new EOperationImpl();
    return eOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EPackage createEPackage()
  {
    EPackageImpl ePackage = new EPackageImpl();
    return ePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EFactory createEFactory()
  {
    EFactoryImpl eFactory = new EFactoryImpl();
    return eFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnumLiteral createEEnumLiteral()
  {
    EEnumLiteralImpl eEnumLiteral = new EEnumLiteralImpl();
    return eEnumLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum createEEnum()
  {
    EEnumImpl eEnum = new EEnumImpl();
    return eEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Boolean createEBooleanObjectFromString(EDataType metaObject, String initialValue) 
  {
    return Boolean.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEBooleanObjectToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Character createECharacterObjectFromString(EDataType metaObject, String initialValue) 
  {
    char charValue = 0;
    try
    {
      Integer value = new Integer(initialValue);
      charValue = (char) value.intValue();
    }
    catch (NumberFormatException e)
    {
      char[] carray = initialValue.toCharArray();
      charValue = carray[0];
    }
    return new Character(charValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertECharacterObjectToString(EDataType metaObject, Object instanceValue) 
  {
    if (instanceValue instanceof Character)
    {
      int charInt = ((Character) instanceValue).charValue();
      Integer value = new Integer(charInt);
      return value.toString();
    }

    return instanceValue == null ? null : instanceValue.toString();
  }

  protected static final DateFormat [] EDATE_FORMATS = 
    {
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSZ"),
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS"),
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"),
      new SimpleDateFormat("yyyy-MM-dd")
    };

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Date createEDateFromString(EDataType eDataType, String initialValue)
  {
    Exception exception = null;
    for (int i = 0; i < EDATE_FORMATS.length; ++i)
    {
      try
      {
        return EDATE_FORMATS[i].parse(initialValue);
      }
      catch (ParseException parseException)
      {
        exception = parseException;
      }
    }
    throw new WrappedException(exception);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEDateToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null)
    {
      return null;
    }
    else
    {
      return EDATE_FORMATS[0].format((Date)instanceValue);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Double createEDoubleObjectFromString(EDataType metaObject, String initialValue) 
  {
    return Double.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEDoubleObjectToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Float createEFloatObjectFromString(EDataType metaObject, String initialValue) 
  {
    return Float.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEFloatObjectToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Integer createEIntegerObjectFromString(EDataType metaObject, String initialValue) 
  {
    return Integer.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEIntegerObjectToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference createEReference()
  {
    EReferenceImpl eReference = new EReferenceImpl();
    return eReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry createEStringToStringMapEntry()
  {
    EStringToStringMapEntryImpl eStringToStringMapEntry = new EStringToStringMapEntryImpl();
    return eStringToStringMapEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public BigDecimal createEBigDecimalFromString(EDataType eDataType, String initialValue)
  {
    return new BigDecimal(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEBigDecimalToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public BigInteger createEBigIntegerFromString(EDataType eDataType, String initialValue)
  {
    return new BigInteger(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEBigIntegerToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EcorePackage getEcorePackage()
  {
    return (EcorePackage)getEPackage();
  }

  /**
   * @deprecated
   */
  public static EcorePackage getPackage()
  {
    return EcorePackage.eINSTANCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String createEStringFromString(EDataType metaObject, String initialValue) 
  {
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEStringToString(EDataType metaObject, Object instanceValue) 
  {
    return (String)instanceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap.Entry createEFeatureMapEntryFromString(EDataType eDataType, String initialValue)
  {
    return (FeatureMap.Entry)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertEFeatureMapEntryToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Integer createEIntFromString(EDataType metaObject, String initialValue) 
  {
    return Integer.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEIntToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Boolean createEBooleanFromString(EDataType metaObject, String initialValue) 
  {
    return Boolean.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEBooleanToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Byte createEByteObjectFromString(EDataType metaObject, String initialValue) 
  {
    return Byte.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEByteObjectToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Float createEFloatFromString(EDataType metaObject, String initialValue) 
  {
    return Float.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEFloatToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Character createECharFromString(EDataType metaObject, String initialValue) 
  {
    char charValue = 0;
    try
    {
      Integer value = new Integer(initialValue);
      charValue = (char) value.intValue();
    }
    catch (NumberFormatException e)
    {
      char[] carray = initialValue.toCharArray();
      charValue = carray[0];
    }
    return new Character(charValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertECharToString(EDataType metaObject, Object instanceValue) 
  {
    if (instanceValue instanceof Character)
    {
      int charInt = ((Character) instanceValue).charValue();
      Integer value = new Integer(charInt);
      return value.toString();
    }

    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Long createELongFromString(EDataType metaObject, String initialValue) 
  {
    return Long.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertELongToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Double createEDoubleFromString(EDataType metaObject, String initialValue) 
  {
    return Double.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEDoubleToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Byte createEByteFromString(EDataType metaObject, String initialValue) 
  {
    return Byte.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEByteToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public byte[] createEByteArrayFromString(EDataType eDataType, String initialValue)
  {
    int size = initialValue.length();
    int limit = (size + 1) / 2;
    byte [] result = new byte[limit];
    if (size % 2 != 0)
    {
      result[--limit] = hexCharToByte(initialValue.charAt(size - 1));
    }
    
    for (int i = 0, j = 0; i < limit; ++i)
    {
      byte high = hexCharToByte(initialValue.charAt(j++));
      byte low = hexCharToByte(initialValue.charAt(j++));
      result[i] = (byte)(high << 4 | low);
    }
    return result;
  }

  protected static byte hexCharToByte(char character)
  {
    switch (character)
    {
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
      {
        return (byte)(character - '0');
      }
      case 'a':
      case 'b':
      case 'c':
      case 'd':
      case 'e':
      case 'f':
      {
        return (byte)(character - 'a' + 10);
      }
      case 'A':
      case 'B':
      case 'C':
      case 'D':
      case 'E':
      case 'F':
      {
        return (byte)(character - 'A' + 10);
      }
      default:
      {
        throw new NumberFormatException("Invalid hexadecimal");
      }
    }
  }

  protected static final char [] HEX_DIGITS = 
    { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEByteArrayToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null)
    {
      return null;
    }
    else
    {
      byte [] bytes = (byte[])instanceValue;
      char [] result = new char[2 * bytes.length];
      for (int i = 0, j = 0; i < bytes.length; ++i)
      {
        int high = (bytes[i] >> 4) & 0xF;
        int low = bytes[i] & 0xF;
        result[j++] = HEX_DIGITS[high];
        result[j++] = HEX_DIGITS[low];
      }
      return new String(result);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Short createEShortFromString(EDataType metaObject, String initialValue) 
  {
    return Short.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEShortToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Class createEJavaClassFromString(EDataType metaObject, String initialValue) 
  {
    try
    {
      if ("boolean".equals(initialValue)) return boolean.class;
      else if ("byte".equals(initialValue)) return byte.class;
      else if ("char".equals(initialValue)) return char.class;
      else if ("double".equals(initialValue)) return double.class;
      else if ("float".equals(initialValue)) return float.class;
      else if ("int".equals(initialValue)) return int.class;
      else if ("long".equals(initialValue)) return long.class;
      else if ("short".equals(initialValue)) return short.class;
      else return Class.forName(initialValue);
    }
    catch (ClassNotFoundException e)
    {
      throw new WrappedException(e);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEJavaClassToString(EDataType metaObject, Object instanceValue)
  {
    return instanceValue == null ? "" : ((Class)instanceValue).getName();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Long createELongObjectFromString(EDataType metaObject, String initialValue) 
  {
    return Long.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertELongObjectToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Short createEShortObjectFromString(EDataType metaObject, String initialValue) 
  {
    return Short.valueOf(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertEShortObjectToString(EDataType metaObject, Object instanceValue) 
  {
    return instanceValue == null ? null : instanceValue.toString();
  }
} //EcoreFactoryImpl
