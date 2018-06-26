/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import java.util.List;

import org.eclipse.emf.ecore.EDataType;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenDataType#getEcoreDataType <em>Ecore Data Type</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenDataType#getPropertyEditorFactory <em>Property Editor Factory</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenDataType()
 * @model features="create convert" 
 *        createUnsettable="true" createDataType="org.eclipse.emf.ecore.EString" createSuppressedGetVisibility="true" createSuppressedSetVisibility="true" createSuppressedIsSetVisibility="true" createSuppressedUnsetVisibility="true"
 *        convertUnsettable="true" convertDataType="org.eclipse.emf.ecore.EString" convertSuppressedGetVisibility="true" convertSuppressedSetVisibility="true" convertSuppressedIsSetVisibility="true" convertSuppressedUnsetVisibility="true"
 * @generated
 */
public interface GenDataType extends GenClassifier
{
  /**
   * Returns the value of the '<em><b>Ecore Data Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ecore Data Type</em>' reference.
   * @see #setEcoreDataType(EDataType)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenDataType_EcoreDataType()
   * @model required="true"
   * @generated
   */
  EDataType getEcoreDataType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenDataType#getEcoreDataType <em>Ecore Data Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ecore Data Type</em>' reference.
   * @see #getEcoreDataType()
   * @generated
   */
  void setEcoreDataType(EDataType value);

  /**
   * Returns the value of the '<em><b>Property Editor Factory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.14
   * <!-- end-model-doc -->
   * @return the value of the '<em>Property Editor Factory</em>' attribute.
   * @see #setPropertyEditorFactory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenDataType_PropertyEditorFactory()
   * @model unsettable="true" dataType="org.eclipse.emf.codegen.ecore.genmodel.PropertyEditorFactory" suppressedIsSetVisibility="true" suppressedUnsetVisibility="true"
   * @generated
   */
  String getPropertyEditorFactory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenDataType#getPropertyEditorFactory <em>Property Editor Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property Editor Factory</em>' attribute.
   * @see #getPropertyEditorFactory()
   * @since 2.14
   * @generated
   */
  void setPropertyEditorFactory(String value);

  String getQualifiedInstanceClassName();
  String getImportedInstanceClassName();
  String getObjectInstanceClassName();
  
  /**
   * @since 2.3
   */
  String getImportedParameterizedObjectInstanceClassName();

  /**
   * @since 2.3
   */
  String getImportedWildcardObjectInstanceClassName();
  
  boolean isObjectType();
  boolean isPrimitiveType();
  boolean isArrayType();
  boolean isSerializable();
  String getPrimitiveValueFunction();
  String getSerializableFlag();
  String getModelInfo();

  GenDataType getBaseType();
  GenDataType getItemType();
  List<GenDataType> getMemberTypes();
  GenDataType getObjectType();

  String getMinLiteral();
  boolean isMinInclusive();

  String getMaxLiteral();
  boolean isMaxInclusive();

  String getLengthAccessorFunction();
  int getMinLength();
  int getMaxLength();

  int getTotalDigits();
  int getFractionDigits();

  List<String> getEnumerationLiterals();

  String getWhiteSpace();

  List<List<String>> getPatterns();

  boolean isXMLCalendar();
  boolean isXMLDuration();

  String getStaticValue(String literal);

  /**
   * @since 2.4
   */
  String getStaticValue(String literal, boolean includeCast);

  void initialize(EDataType eDataType);

  boolean reconcile(GenDataType oldGenDataVersion);

  /**
   * @since 2.8
   */
  boolean hasCreatorBody();

  /**
   * @since 2.8
   */
  String getCreatorBody(String indentation);

  /**
   * @since 2.8
   */
  boolean hasConverterBody();

  /**
   * @since 2.8
   */
  String getConverterBody(String indentation);

  /**
   * @since 2.9
   */
  boolean hasConversionDelegate();
}
