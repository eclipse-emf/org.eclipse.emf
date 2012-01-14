/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.eclipse.emf.mapping.MappingFactory
 * @model kind="package"
 * @generated
 */
public interface MappingPackage extends EPackage{

  /**
   * The package name.
   * @generated
   */
  String eNAME = "mapping";

  /**
   * The package namespace URI.
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/2002/Mapping";

  /**
   * The package namespace name.
   * @generated
   */
  String eNS_PREFIX = "mapping";

  /**
   * The singleton instance of the package.
   * @generated
   */
  MappingPackage eINSTANCE = org.eclipse.emf.mapping.impl.MappingPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.impl.MappingHelperImpl <em>Helper</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.impl.MappingHelperImpl
   * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getMappingHelper()
   * @generated
   */
  int MAPPING_HELPER = 0;

  /**
   * The feature id for the '<em><b>Mapper</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_HELPER__MAPPER = 0;

  /**
   * The feature id for the '<em><b>Helped Object</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_HELPER__HELPED_OBJECT = 1;

  /**
   * The feature id for the '<em><b>Nested In</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_HELPER__NESTED_IN = 2;

  /**
   * The feature id for the '<em><b>Nested</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_HELPER__NESTED = 3;

  /**
   * The number of structural features of the '<em>Helper</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_HELPER_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.impl.MappingImpl <em>Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.impl.MappingImpl
   * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getMapping()
   * @generated
   */
  int MAPPING = 1;

  /**
   * The feature id for the '<em><b>Helper</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING__HELPER = 0;

  /**
   * The feature id for the '<em><b>Nested</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING__NESTED = 1;

  /**
   * The feature id for the '<em><b>Nested In</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING__NESTED_IN = 2;

  /**
   * The feature id for the '<em><b>Inputs</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING__INPUTS = 3;

  /**
   * The feature id for the '<em><b>Outputs</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING__OUTPUTS = 4;

  /**
   * The feature id for the '<em><b>Type Mapping</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING__TYPE_MAPPING = 5;

  /**
   * The number of structural features of the '<em>Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.impl.TypeConverterImpl <em>Type Converter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.impl.TypeConverterImpl
   * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getTypeConverter()
   * @generated
   */
  int TYPE_CONVERTER = 2;

  /**
   * The feature id for the '<em><b>Mapper</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_CONVERTER__MAPPER = MAPPING_HELPER__MAPPER;

  /**
   * The feature id for the '<em><b>Helped Object</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_CONVERTER__HELPED_OBJECT = MAPPING_HELPER__HELPED_OBJECT;

  /**
   * The feature id for the '<em><b>Nested In</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_CONVERTER__NESTED_IN = MAPPING_HELPER__NESTED_IN;

  /**
   * The feature id for the '<em><b>Nested</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_CONVERTER__NESTED = MAPPING_HELPER__NESTED;

  /**
   * The number of structural features of the '<em>Type Converter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_CONVERTER_FEATURE_COUNT = MAPPING_HELPER_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.impl.FunctionPairImpl <em>Function Pair</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.impl.FunctionPairImpl
   * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getFunctionPair()
   * @generated
   */
  int FUNCTION_PAIR = 3;

  /**
   * The feature id for the '<em><b>Mapper</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_PAIR__MAPPER = TYPE_CONVERTER__MAPPER;

  /**
   * The feature id for the '<em><b>Helped Object</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_PAIR__HELPED_OBJECT = TYPE_CONVERTER__HELPED_OBJECT;

  /**
   * The feature id for the '<em><b>Nested In</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_PAIR__NESTED_IN = TYPE_CONVERTER__NESTED_IN;

  /**
   * The feature id for the '<em><b>Nested</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_PAIR__NESTED = TYPE_CONVERTER__NESTED;

  /**
   * The feature id for the '<em><b>In2out</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_PAIR__IN2OUT = TYPE_CONVERTER_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Out2in</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_PAIR__OUT2IN = TYPE_CONVERTER_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Function Pair</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_PAIR_FEATURE_COUNT = TYPE_CONVERTER_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.impl.FunctionNamePairImpl <em>Function Name Pair</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.impl.FunctionNamePairImpl
   * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getFunctionNamePair()
   * @generated
   */
  int FUNCTION_NAME_PAIR = 4;

  /**
   * The feature id for the '<em><b>Mapper</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_NAME_PAIR__MAPPER = TYPE_CONVERTER__MAPPER;

  /**
   * The feature id for the '<em><b>Helped Object</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_NAME_PAIR__HELPED_OBJECT = TYPE_CONVERTER__HELPED_OBJECT;

  /**
   * The feature id for the '<em><b>Nested In</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_NAME_PAIR__NESTED_IN = TYPE_CONVERTER__NESTED_IN;

  /**
   * The feature id for the '<em><b>Nested</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_NAME_PAIR__NESTED = TYPE_CONVERTER__NESTED;

  /**
   * The feature id for the '<em><b>In2out</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_NAME_PAIR__IN2OUT = TYPE_CONVERTER_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Out2in</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_NAME_PAIR__OUT2IN = TYPE_CONVERTER_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Function Name Pair</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_NAME_PAIR_FEATURE_COUNT = TYPE_CONVERTER_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.impl.MappingStrategyImpl <em>Strategy</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.impl.MappingStrategyImpl
   * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getMappingStrategy()
   * @generated
   */
  int MAPPING_STRATEGY = 5;

  /**
   * The feature id for the '<em><b>Mapper</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_STRATEGY__MAPPER = MAPPING_HELPER__MAPPER;

  /**
   * The feature id for the '<em><b>Helped Object</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_STRATEGY__HELPED_OBJECT = MAPPING_HELPER__HELPED_OBJECT;

  /**
   * The feature id for the '<em><b>Nested In</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_STRATEGY__NESTED_IN = MAPPING_HELPER__NESTED_IN;

  /**
   * The feature id for the '<em><b>Nested</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_STRATEGY__NESTED = MAPPING_HELPER__NESTED;

  /**
   * The number of structural features of the '<em>Strategy</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_STRATEGY_FEATURE_COUNT = MAPPING_HELPER_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.impl.MappingRootImpl <em>Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.impl.MappingRootImpl
   * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getMappingRoot()
   * @generated
   */
  int MAPPING_ROOT = 6;

  /**
   * The feature id for the '<em><b>Helper</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ROOT__HELPER = MAPPING__HELPER;

  /**
   * The feature id for the '<em><b>Nested</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ROOT__NESTED = MAPPING__NESTED;

  /**
   * The feature id for the '<em><b>Nested In</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ROOT__NESTED_IN = MAPPING__NESTED_IN;

  /**
   * The feature id for the '<em><b>Inputs</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ROOT__INPUTS = MAPPING__INPUTS;

  /**
   * The feature id for the '<em><b>Outputs</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ROOT__OUTPUTS = MAPPING__OUTPUTS;

  /**
   * The feature id for the '<em><b>Type Mapping</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ROOT__TYPE_MAPPING = MAPPING__TYPE_MAPPING;

  /**
   * The feature id for the '<em><b>Output Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ROOT__OUTPUT_READ_ONLY = MAPPING_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Top To Bottom</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ROOT__TOP_TO_BOTTOM = MAPPING_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Command Stack</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ROOT__COMMAND_STACK = MAPPING_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ROOT_FEATURE_COUNT = MAPPING_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.impl.ComplexTypeConverterImpl <em>Complex Type Converter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.impl.ComplexTypeConverterImpl
   * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getComplexTypeConverter()
   * @generated
   */
  int COMPLEX_TYPE_CONVERTER = 7;

  /**
   * The feature id for the '<em><b>Mapper</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_TYPE_CONVERTER__MAPPER = TYPE_CONVERTER__MAPPER;

  /**
   * The feature id for the '<em><b>Helped Object</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_TYPE_CONVERTER__HELPED_OBJECT = TYPE_CONVERTER__HELPED_OBJECT;

  /**
   * The feature id for the '<em><b>Nested In</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_TYPE_CONVERTER__NESTED_IN = TYPE_CONVERTER__NESTED_IN;

  /**
   * The feature id for the '<em><b>Nested</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_TYPE_CONVERTER__NESTED = TYPE_CONVERTER__NESTED;

  /**
   * The feature id for the '<em><b>In2out</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_TYPE_CONVERTER__IN2OUT = TYPE_CONVERTER_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Out2in</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_TYPE_CONVERTER__OUT2IN = TYPE_CONVERTER_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Complex Type Converter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_TYPE_CONVERTER_FEATURE_COUNT = TYPE_CONVERTER_FEATURE_COUNT + 2;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.MappingHelper <em>Helper</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Helper</em>'.
   * @see org.eclipse.emf.mapping.MappingHelper
   * @generated
   */
  EClass getMappingHelper();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.mapping.MappingHelper#getMapper <em>Mapper</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Mapper</em>'.
   * @see org.eclipse.emf.mapping.MappingHelper#getMapper()
   * @see #getMappingHelper()
   * @generated
   */
  EReference getMappingHelper_Mapper();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.mapping.MappingHelper#getHelpedObject <em>Helped Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Helped Object</em>'.
   * @see org.eclipse.emf.mapping.MappingHelper#getHelpedObject()
   * @see #getMappingHelper()
   * @generated
   */
  EReference getMappingHelper_HelpedObject();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.mapping.MappingHelper#getNestedIn <em>Nested In</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Nested In</em>'.
   * @see org.eclipse.emf.mapping.MappingHelper#getNestedIn()
   * @see #getMappingHelper()
   * @generated
   */
  EReference getMappingHelper_NestedIn();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.mapping.MappingHelper#getNested <em>Nested</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Nested</em>'.
   * @see org.eclipse.emf.mapping.MappingHelper#getNested()
   * @see #getMappingHelper()
   * @generated
   */
  EReference getMappingHelper_Nested();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.Mapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mapping</em>'.
   * @see org.eclipse.emf.mapping.Mapping
   * @generated
   */
  EClass getMapping();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.mapping.Mapping#getHelper <em>Helper</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Helper</em>'.
   * @see org.eclipse.emf.mapping.Mapping#getHelper()
   * @see #getMapping()
   * @generated
   */
  EReference getMapping_Helper();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.mapping.Mapping#getNested <em>Nested</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Nested</em>'.
   * @see org.eclipse.emf.mapping.Mapping#getNested()
   * @see #getMapping()
   * @generated
   */
  EReference getMapping_Nested();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.mapping.Mapping#getNestedIn <em>Nested In</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Nested In</em>'.
   * @see org.eclipse.emf.mapping.Mapping#getNestedIn()
   * @see #getMapping()
   * @generated
   */
  EReference getMapping_NestedIn();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.mapping.Mapping#getInputs <em>Inputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Inputs</em>'.
   * @see org.eclipse.emf.mapping.Mapping#getInputs()
   * @see #getMapping()
   * @generated
   */
  EReference getMapping_Inputs();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.mapping.Mapping#getOutputs <em>Outputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Outputs</em>'.
   * @see org.eclipse.emf.mapping.Mapping#getOutputs()
   * @see #getMapping()
   * @generated
   */
  EReference getMapping_Outputs();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.mapping.Mapping#getTypeMapping <em>Type Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type Mapping</em>'.
   * @see org.eclipse.emf.mapping.Mapping#getTypeMapping()
   * @see #getMapping()
   * @generated
   */
  EReference getMapping_TypeMapping();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.TypeConverter <em>Type Converter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Converter</em>'.
   * @see org.eclipse.emf.mapping.TypeConverter
   * @generated
   */
  EClass getTypeConverter();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.FunctionPair <em>Function Pair</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Pair</em>'.
   * @see org.eclipse.emf.mapping.FunctionPair
   * @generated
   */
  EClass getFunctionPair();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.mapping.FunctionPair#getIn2out <em>In2out</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>In2out</em>'.
   * @see org.eclipse.emf.mapping.FunctionPair#getIn2out()
   * @see #getFunctionPair()
   * @generated
   */
  EReference getFunctionPair_In2out();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.mapping.FunctionPair#getOut2in <em>Out2in</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Out2in</em>'.
   * @see org.eclipse.emf.mapping.FunctionPair#getOut2in()
   * @see #getFunctionPair()
   * @generated
   */
  EReference getFunctionPair_Out2in();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.FunctionNamePair <em>Function Name Pair</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Name Pair</em>'.
   * @see org.eclipse.emf.mapping.FunctionNamePair
   * @generated
   */
  EClass getFunctionNamePair();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.mapping.FunctionNamePair#getIn2out <em>In2out</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>In2out</em>'.
   * @see org.eclipse.emf.mapping.FunctionNamePair#getIn2out()
   * @see #getFunctionNamePair()
   * @generated
   */
  EAttribute getFunctionNamePair_In2out();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.mapping.FunctionNamePair#getOut2in <em>Out2in</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Out2in</em>'.
   * @see org.eclipse.emf.mapping.FunctionNamePair#getOut2in()
   * @see #getFunctionNamePair()
   * @generated
   */
  EAttribute getFunctionNamePair_Out2in();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.MappingStrategy <em>Strategy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Strategy</em>'.
   * @see org.eclipse.emf.mapping.MappingStrategy
   * @generated
   */
  EClass getMappingStrategy();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.MappingRoot <em>Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Root</em>'.
   * @see org.eclipse.emf.mapping.MappingRoot
   * @generated
   */
  EClass getMappingRoot();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.mapping.MappingRoot#isOutputReadOnly <em>Output Read Only</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Output Read Only</em>'.
   * @see org.eclipse.emf.mapping.MappingRoot#isOutputReadOnly()
   * @see #getMappingRoot()
   * @generated
   */
  EAttribute getMappingRoot_OutputReadOnly();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.mapping.MappingRoot#isTopToBottom <em>Top To Bottom</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Top To Bottom</em>'.
   * @see org.eclipse.emf.mapping.MappingRoot#isTopToBottom()
   * @see #getMappingRoot()
   * @generated
   */
  EAttribute getMappingRoot_TopToBottom();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.mapping.MappingRoot#getCommandStack <em>Command Stack</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Command Stack</em>'.
   * @see org.eclipse.emf.mapping.MappingRoot#getCommandStack()
   * @see #getMappingRoot()
   * @generated
   */
  EAttribute getMappingRoot_CommandStack();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.ComplexTypeConverter <em>Complex Type Converter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Complex Type Converter</em>'.
   * @see org.eclipse.emf.mapping.ComplexTypeConverter
   * @generated
   */
  EClass getComplexTypeConverter();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.mapping.ComplexTypeConverter#getIn2out <em>In2out</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>In2out</em>'.
   * @see org.eclipse.emf.mapping.ComplexTypeConverter#getIn2out()
   * @see #getComplexTypeConverter()
   * @generated
   */
  EReference getComplexTypeConverter_In2out();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.mapping.ComplexTypeConverter#getOut2in <em>Out2in</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Out2in</em>'.
   * @see org.eclipse.emf.mapping.ComplexTypeConverter#getOut2in()
   * @see #getComplexTypeConverter()
   * @generated
   */
  EReference getComplexTypeConverter_Out2in();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  MappingFactory getMappingFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.mapping.impl.MappingHelperImpl <em>Helper</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.impl.MappingHelperImpl
     * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getMappingHelper()
     * @generated
     */
    EClass MAPPING_HELPER = eINSTANCE.getMappingHelper();

    /**
     * The meta object literal for the '<em><b>Mapper</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_HELPER__MAPPER = eINSTANCE.getMappingHelper_Mapper();

    /**
     * The meta object literal for the '<em><b>Helped Object</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_HELPER__HELPED_OBJECT = eINSTANCE.getMappingHelper_HelpedObject();

    /**
     * The meta object literal for the '<em><b>Nested In</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_HELPER__NESTED_IN = eINSTANCE.getMappingHelper_NestedIn();

    /**
     * The meta object literal for the '<em><b>Nested</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_HELPER__NESTED = eINSTANCE.getMappingHelper_Nested();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.mapping.impl.MappingImpl <em>Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.impl.MappingImpl
     * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getMapping()
     * @generated
     */
    EClass MAPPING = eINSTANCE.getMapping();

    /**
     * The meta object literal for the '<em><b>Helper</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING__HELPER = eINSTANCE.getMapping_Helper();

    /**
     * The meta object literal for the '<em><b>Nested</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING__NESTED = eINSTANCE.getMapping_Nested();

    /**
     * The meta object literal for the '<em><b>Nested In</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING__NESTED_IN = eINSTANCE.getMapping_NestedIn();

    /**
     * The meta object literal for the '<em><b>Inputs</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING__INPUTS = eINSTANCE.getMapping_Inputs();

    /**
     * The meta object literal for the '<em><b>Outputs</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING__OUTPUTS = eINSTANCE.getMapping_Outputs();

    /**
     * The meta object literal for the '<em><b>Type Mapping</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING__TYPE_MAPPING = eINSTANCE.getMapping_TypeMapping();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.mapping.impl.TypeConverterImpl <em>Type Converter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.impl.TypeConverterImpl
     * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getTypeConverter()
     * @generated
     */
    EClass TYPE_CONVERTER = eINSTANCE.getTypeConverter();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.mapping.impl.FunctionPairImpl <em>Function Pair</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.impl.FunctionPairImpl
     * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getFunctionPair()
     * @generated
     */
    EClass FUNCTION_PAIR = eINSTANCE.getFunctionPair();

    /**
     * The meta object literal for the '<em><b>In2out</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_PAIR__IN2OUT = eINSTANCE.getFunctionPair_In2out();

    /**
     * The meta object literal for the '<em><b>Out2in</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_PAIR__OUT2IN = eINSTANCE.getFunctionPair_Out2in();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.mapping.impl.FunctionNamePairImpl <em>Function Name Pair</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.impl.FunctionNamePairImpl
     * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getFunctionNamePair()
     * @generated
     */
    EClass FUNCTION_NAME_PAIR = eINSTANCE.getFunctionNamePair();

    /**
     * The meta object literal for the '<em><b>In2out</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_NAME_PAIR__IN2OUT = eINSTANCE.getFunctionNamePair_In2out();

    /**
     * The meta object literal for the '<em><b>Out2in</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_NAME_PAIR__OUT2IN = eINSTANCE.getFunctionNamePair_Out2in();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.mapping.impl.MappingStrategyImpl <em>Strategy</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.impl.MappingStrategyImpl
     * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getMappingStrategy()
     * @generated
     */
    EClass MAPPING_STRATEGY = eINSTANCE.getMappingStrategy();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.mapping.impl.MappingRootImpl <em>Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.impl.MappingRootImpl
     * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getMappingRoot()
     * @generated
     */
    EClass MAPPING_ROOT = eINSTANCE.getMappingRoot();

    /**
     * The meta object literal for the '<em><b>Output Read Only</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAPPING_ROOT__OUTPUT_READ_ONLY = eINSTANCE.getMappingRoot_OutputReadOnly();

    /**
     * The meta object literal for the '<em><b>Top To Bottom</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAPPING_ROOT__TOP_TO_BOTTOM = eINSTANCE.getMappingRoot_TopToBottom();

    /**
     * The meta object literal for the '<em><b>Command Stack</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAPPING_ROOT__COMMAND_STACK = eINSTANCE.getMappingRoot_CommandStack();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.mapping.impl.ComplexTypeConverterImpl <em>Complex Type Converter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.impl.ComplexTypeConverterImpl
     * @see org.eclipse.emf.mapping.impl.MappingPackageImpl#getComplexTypeConverter()
     * @generated
     */
    EClass COMPLEX_TYPE_CONVERTER = eINSTANCE.getComplexTypeConverter();

    /**
     * The meta object literal for the '<em><b>In2out</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPLEX_TYPE_CONVERTER__IN2OUT = eINSTANCE.getComplexTypeConverter_In2out();

    /**
     * The meta object literal for the '<em><b>Out2in</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPLEX_TYPE_CONVERTER__OUT2IN = eINSTANCE.getComplexTypeConverter_Out2in();

  }

} //MappingPackage


