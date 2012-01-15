/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.impl;


import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.xcore.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XcoreFactoryImpl extends EFactoryImpl implements XcoreFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XcoreFactory init()
  {
    try
    {
      XcoreFactory theXcoreFactory = (XcoreFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/2011/Xcore");
      if (theXcoreFactory != null)
      {
        return theXcoreFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new XcoreFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XcoreFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case XcorePackage.XANNOTATION:
      return createXAnnotation();
      case XcorePackage.XANNOTATION_DIRECTIVE:
      return createXAnnotationDirective();
      case XcorePackage.XATTRIBUTE:
      return createXAttribute();
      case XcorePackage.XCLASS:
      return createXClass();
      case XcorePackage.XDATA_TYPE:
      return createXDataType();
      case XcorePackage.XENUM:
      return createXEnum();
      case XcorePackage.XENUM_LITERAL:
      return createXEnumLiteral();
      case XcorePackage.XGENERIC_TYPE:
      return createXGenericType();
      case XcorePackage.XIMPORT_DIRECTIVE:
      return createXImportDirective();
      case XcorePackage.XOPERATION:
      return createXOperation();
      case XcorePackage.XPACKAGE:
      return createXPackage();
      case XcorePackage.XPARAMETER:
      return createXParameter();
      case XcorePackage.XREFERENCE:
      return createXReference();
      case XcorePackage.XSTRING_TO_STRING_MAP_ENTRY:
      return (EObject)createXStringToStringMapEntry();
      case XcorePackage.XTYPE_PARAMETER:
      return createXTypeParameter();
      default:
      throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case XcorePackage.XMULTIPLICITY:
      return createXMultiplicityFromString(eDataType, initialValue);
      default:
      throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case XcorePackage.XMULTIPLICITY:
      return convertXMultiplicityToString(eDataType, instanceValue);
      default:
      throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XAnnotation createXAnnotation()
  {
    XAnnotationImpl xAnnotation = new XAnnotationImpl();
    return xAnnotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XAnnotationDirective createXAnnotationDirective()
  {
    XAnnotationDirectiveImpl xAnnotationDirective = new XAnnotationDirectiveImpl();
    return xAnnotationDirective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XAttribute createXAttribute()
  {
    XAttributeImpl xAttribute = new XAttributeImpl();
    return xAttribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XClass createXClass()
  {
    XClassImpl xClass = new XClassImpl();
    return xClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XDataType createXDataType()
  {
    XDataTypeImpl xDataType = new XDataTypeImpl();
    return xDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XEnum createXEnum()
  {
    XEnumImpl xEnum = new XEnumImpl();
    return xEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XEnumLiteral createXEnumLiteral()
  {
    XEnumLiteralImpl xEnumLiteral = new XEnumLiteralImpl();
    return xEnumLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XGenericType createXGenericType()
  {
    XGenericTypeImpl xGenericType = new XGenericTypeImpl();
    return xGenericType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XImportDirective createXImportDirective()
  {
    XImportDirectiveImpl xImportDirective = new XImportDirectiveImpl();
    return xImportDirective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XOperation createXOperation()
  {
    XOperationImpl xOperation = new XOperationImpl();
    return xOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XPackage createXPackage()
  {
    XPackageImpl xPackage = new XPackageImpl();
    return xPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XParameter createXParameter()
  {
    XParameterImpl xParameter = new XParameterImpl();
    return xParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XReference createXReference()
  {
    XReferenceImpl xReference = new XReferenceImpl();
    return xReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<String, String> createXStringToStringMapEntry()
  {
    XStringToStringMapEntryImpl xStringToStringMapEntry = new XStringToStringMapEntryImpl();
    return xStringToStringMapEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XTypeParameter createXTypeParameter()
  {
    XTypeParameterImpl xTypeParameter = new XTypeParameterImpl();
    return xTypeParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public int[] createXMultiplicityFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null)
    {
      return null;
    }
    else
    {
      int startingIndex = initialValue.indexOf('[');
      if (startingIndex != -1)
      {
        int endingIndex = initialValue.indexOf(']');
        if (endingIndex != -1)
        {
          String bounds = initialValue.substring(startingIndex + 1, endingIndex).trim();
          int rangeIndex = bounds.indexOf("..");
          if (rangeIndex == -1)
          {
            if ("".equals(bounds))
            {
              return new int [0];
            }
            int[] result = new int [1];
            if ("?".equals(bounds))
            {
              result[0] = -3;
            }
            else if ("+".equals(bounds))
            {
              result[0] = -2;
            }
            else if ("*".equals(bounds))
            {
              result[0] = -1;
            }
            else
            {
              result[0] = Integer.valueOf(bounds);
            }
            return result;
          }
          else
          {
            String lowerBound = bounds.substring(0, rangeIndex).trim();
            String upperBound = bounds.substring(rangeIndex + 2).trim();
            int[] result = new int [2];
            result[0] = Integer.valueOf(lowerBound);
            if ("?".equals(upperBound))
            {
              result[1] = -2;
            }
            else if ("*".equals(upperBound))
            {
              result[1] = -1;
            }
            else
            {
              result[1] = Integer.valueOf(upperBound);
            }
            return result;
          }
        }
        else
        {
          throw new RuntimeException("Expecting a final ']'");

        }
      }
      else
      {
        throw new RuntimeException("Expecting an initial '['");
      }
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String convertXMultiplicityToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null)
    {
      return null;
    }
    else
    {
      int[] multiplicity = (int[])instanceValue;
      if (multiplicity.length == 0)
      {
        return "[]";
      }
      else if (multiplicity.length == 1)
      {
        if (multiplicity[0] == -3)
        {
          return "[?]";
        }
        else if (multiplicity[0] == -2)
        {
          return "[+]";
        }
        else if (multiplicity[0] == -1)
        {
          return "[*]";
        }
        else
        {
          return "[" + multiplicity[0] + "]";
        }
      }
      else if (multiplicity[1] == -1)
      {
        return "[" + multiplicity[0] + "..*]";
      }
      else if (multiplicity[1] == -2)
      {
        return "[" + multiplicity[0] + "..?]";
      }
      else
      {
        return "[" + multiplicity[0] + ".." + multiplicity[1] + "]";
      }
    }

  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XcorePackage getXcorePackage()
  {
    return (XcorePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static XcorePackage getPackage()
  {
    return XcorePackage.eINSTANCE;
  }

} //XcoreFactoryImpl
