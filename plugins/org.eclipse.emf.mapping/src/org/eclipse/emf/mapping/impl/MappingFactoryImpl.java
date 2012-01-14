/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.mapping.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MappingFactoryImpl extends EFactoryImpl implements MappingFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MappingFactory init()
  {
    try
    {
      MappingFactory theMappingFactory = (MappingFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/2002/Mapping"); 
      if (theMappingFactory != null)
      {
        return theMappingFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new MappingFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingFactoryImpl()
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
      case MappingPackage.MAPPING_HELPER: return createMappingHelper();
      case MappingPackage.MAPPING: return createMapping();
      case MappingPackage.TYPE_CONVERTER: return createTypeConverter();
      case MappingPackage.FUNCTION_PAIR: return createFunctionPair();
      case MappingPackage.FUNCTION_NAME_PAIR: return createFunctionNamePair();
      case MappingPackage.MAPPING_STRATEGY: return createMappingStrategy();
      case MappingPackage.MAPPING_ROOT: return createMappingRoot();
      case MappingPackage.COMPLEX_TYPE_CONVERTER: return createComplexTypeConverter();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingHelper createMappingHelper()
  {
    MappingHelperImpl mappingHelper = new MappingHelperImpl();
    return mappingHelper;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mapping createMapping()
  {
    MappingImpl mapping = new MappingImpl();
    return mapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeConverter createTypeConverter()
  {
    TypeConverterImpl typeConverter = new TypeConverterImpl();
    return typeConverter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionPair createFunctionPair()
  {
    FunctionPairImpl functionPair = new FunctionPairImpl();
    return functionPair;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionNamePair createFunctionNamePair()
  {
    FunctionNamePairImpl functionNamePair = new FunctionNamePairImpl();
    return functionNamePair;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingStrategy createMappingStrategy()
  {
    MappingStrategyImpl mappingStrategy = new MappingStrategyImpl();
    return mappingStrategy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingRoot createMappingRoot()
  {
    MappingRootImpl mappingRoot = new MappingRootImpl();
    return mappingRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComplexTypeConverter createComplexTypeConverter()
  {
    ComplexTypeConverterImpl complexTypeConverter = new ComplexTypeConverterImpl();
    return complexTypeConverter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingPackage getMappingPackage()
  {
    return (MappingPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static MappingPackage getPackage()
  {
    return MappingPackage.eINSTANCE;
  }

} //MappingFactoryImpl


