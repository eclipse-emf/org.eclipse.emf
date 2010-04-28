/**
 * <copyright>
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: GenModelFactoryImpl.java,v 1.13 2010/04/28 20:38:10 khussey Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;


import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GenModelFactoryImpl extends EFactoryImpl implements GenModelFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenModelFactory init()
  {
    try
    {
      GenModelFactory theGenModelFactory = (GenModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/2002/GenModel"); 
      if (theGenModelFactory != null)
      {
        return theGenModelFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new GenModelFactoryImpl();
  }

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
  @Override
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
      case GenModelPackage.GEN_ANNOTATION: return createGenAnnotation();
      case GenModelPackage.GEN_TYPE_PARAMETER: return createGenTypeParameter();
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
      case GenModelPackage.GEN_PROVIDER_KIND:
        return createGenProviderKindFromString(eDataType, initialValue);
      case GenModelPackage.GEN_PROPERTY_KIND:
        return createGenPropertyKindFromString(eDataType, initialValue);
      case GenModelPackage.GEN_RESOURCE_KIND:
        return createGenResourceKindFromString(eDataType, initialValue);
      case GenModelPackage.GEN_DELEGATION_KIND:
        return createGenDelegationKindFromString(eDataType, initialValue);
      case GenModelPackage.GEN_JDK_LEVEL:
        return createGenJDKLevelFromString(eDataType, initialValue);
      case GenModelPackage.GEN_RUNTIME_VERSION:
        return createGenRuntimeVersionFromString(eDataType, initialValue);
      case GenModelPackage.GEN_RUNTIME_PLATFORM:
        return createGenRuntimePlatformFromString(eDataType, initialValue);
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
      case GenModelPackage.GEN_PROVIDER_KIND:
        return convertGenProviderKindToString(eDataType, instanceValue);
      case GenModelPackage.GEN_PROPERTY_KIND:
        return convertGenPropertyKindToString(eDataType, instanceValue);
      case GenModelPackage.GEN_RESOURCE_KIND:
        return convertGenResourceKindToString(eDataType, instanceValue);
      case GenModelPackage.GEN_DELEGATION_KIND:
        return convertGenDelegationKindToString(eDataType, instanceValue);
      case GenModelPackage.GEN_JDK_LEVEL:
        return convertGenJDKLevelToString(eDataType, instanceValue);
      case GenModelPackage.GEN_RUNTIME_VERSION:
        return convertGenRuntimeVersionToString(eDataType, instanceValue);
      case GenModelPackage.GEN_RUNTIME_PLATFORM:
        return convertGenRuntimePlatformToString(eDataType, instanceValue);
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
  public GenAnnotation createGenAnnotation()
  {
    GenAnnotationImpl genAnnotation = new GenAnnotationImpl();
    return genAnnotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenTypeParameter createGenTypeParameter()
  {
    GenTypeParameterImpl genTypeParameter = new GenTypeParameterImpl();
    return genTypeParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenProviderKind createGenProviderKindFromString(EDataType eDataType, String initialValue)
  {
    GenProviderKind result = GenProviderKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGenProviderKindToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenPropertyKind createGenPropertyKindFromString(EDataType eDataType, String initialValue)
  {
    GenPropertyKind result = GenPropertyKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGenPropertyKindToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenResourceKind createGenResourceKindFromString(EDataType eDataType, String initialValue)
  {
    GenResourceKind result = GenResourceKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGenResourceKindToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenDelegationKind createGenDelegationKindFromString(EDataType eDataType, String initialValue)
  {
    GenDelegationKind result = GenDelegationKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGenDelegationKindToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenJDKLevel createGenJDKLevelFromString(EDataType eDataType, String initialValue)
  {
    GenJDKLevel result = GenJDKLevel.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGenJDKLevelToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenRuntimeVersion createGenRuntimeVersionFromString(EDataType eDataType, String initialValue)
  {
    GenRuntimeVersion result = GenRuntimeVersion.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGenRuntimeVersionToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenRuntimePlatform createGenRuntimePlatformFromString(EDataType eDataType, String initialValue)
  {
    GenRuntimePlatform result = GenRuntimePlatform.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGenRuntimePlatformToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
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
  @Deprecated
  public static GenModelPackage getPackage()
  {
    return GenModelPackage.eINSTANCE;
  }

} //GenModelFactoryImpl
