/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenModelFactoryImpl.java,v 1.5 2005/06/08 06:18:44 nickb Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GenModelFactoryImpl extends EFactoryImpl implements GenModelFactory
{
  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenModelFactoryImpl()
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
      case GenModelPackage.GEN_MODEL: return createGenModel();
      case GenModelPackage.GEN_PACKAGE: return createGenPackage();
      case GenModelPackage.GEN_CLASS: return createGenClass();
      case GenModelPackage.GEN_FEATURE: return createGenFeature();
      case GenModelPackage.GEN_ENUM: return createGenEnum();
      case GenModelPackage.GEN_ENUM_LITERAL: return createGenEnumLiteral();
      case GenModelPackage.GEN_DATA_TYPE: return createGenDataType();
      case GenModelPackage.GEN_OPERATION: return createGenOperation();
      case GenModelPackage.GEN_PARAMETER: return createGenParameter();
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
      case GenModelPackage.GEN_PROVIDER_KIND:
      {
        GenProviderKind result = GenProviderKind.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case GenModelPackage.GEN_PROPERTY_KIND:
      {
        GenPropertyKind result = GenPropertyKind.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case GenModelPackage.GEN_RESOURCE_KIND:
      {
        GenResourceKind result = GenResourceKind.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
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
      case GenModelPackage.GEN_PROVIDER_KIND:
        return instanceValue == null ? null : instanceValue.toString();
      case GenModelPackage.GEN_PROPERTY_KIND:
        return instanceValue == null ? null : instanceValue.toString();
      case GenModelPackage.GEN_RESOURCE_KIND:
        return instanceValue == null ? null : instanceValue.toString();
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenModel createGenModel()
  {
    GenModelImpl genModel = new GenModelImpl();
    return genModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenPackage createGenPackage()
  {
    GenPackageImpl genPackage = new GenPackageImpl();
    return genPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenClass createGenClass()
  {
    GenClassImpl genClass = new GenClassImpl();
    return genClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenFeature createGenFeature()
  {
    GenFeatureImpl genFeature = new GenFeatureImpl();
    return genFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenEnum createGenEnum()
  {
    GenEnumImpl genEnum = new GenEnumImpl();
    return genEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenEnumLiteral createGenEnumLiteral()
  {
    GenEnumLiteralImpl genEnumLiteral = new GenEnumLiteralImpl();
    return genEnumLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenDataType createGenDataType()
  {
    GenDataTypeImpl genDataType = new GenDataTypeImpl();
    return genDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenOperation createGenOperation()
  {
    GenOperationImpl genOperation = new GenOperationImpl();
    return genOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenParameter createGenParameter()
  {
    GenParameterImpl genParameter = new GenParameterImpl();
    return genParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenModelPackage getGenModelPackage()
  {
    return (GenModelPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static GenModelPackage getPackage()
  {
    return GenModelPackage.eINSTANCE;
  }

} //GenModelFactoryImpl
