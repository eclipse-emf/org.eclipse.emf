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


import org.eclipse.emf.ecore.EEnumLiteral;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Enum Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getGenEnum <em>Gen Enum</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getEcoreEnumLiteral <em>Ecore Enum Literal</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEnumLiteral()
 * @model
 * @generated
 */
public interface GenEnumLiteral extends GenBase
{
  /**
   * Returns the value of the '<em><b>Gen Enum</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getGenEnumLiterals <em>Gen Enum Literals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Enum</em>' container reference.
   * @see #setGenEnum(GenEnum)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEnumLiteral_GenEnum()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getGenEnumLiterals
   * @model opposite="genEnumLiterals" required="true"
   * @generated
   */
  GenEnum getGenEnum();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getGenEnum <em>Gen Enum</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Gen Enum</em>' container reference.
   * @see #getGenEnum()
   * @generated
   */
  void setGenEnum(GenEnum value);

  /**
   * Returns the value of the '<em><b>Ecore Enum Literal</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ecore Enum Literal</em>' reference.
   * @see #setEcoreEnumLiteral(EEnumLiteral)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEnumLiteral_EcoreEnumLiteral()
   * @model required="true"
   * @generated
   */
  EEnumLiteral getEcoreEnumLiteral();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getEcoreEnumLiteral <em>Ecore Enum Literal</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ecore Enum Literal</em>' reference.
   * @see #getEcoreEnumLiteral()
   * @generated
   */
  void setEcoreEnumLiteral(EEnumLiteral value);

  /**
   * Returns the value of the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.14
   * <!-- end-model-doc -->
   * @return the value of the '<em>Documentation</em>' attribute.
   * @see #setDocumentation(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEnumLiteral_Documentation()
   * @model unsettable="true" suppressedIsSetVisibility="true" suppressedUnsetVisibility="true"
   * @generated
   */
  String getDocumentation();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getDocumentation <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Documentation</em>' attribute.
   * @see #getDocumentation()
   * @since 2.14
   * @generated
   */
  void setDocumentation(String value);

  String getName();
  String getCapName();
  int getValue();
  String getEnumLiteralID();
  String getLiteral();
  
  /**
   * @since 2.3
   */
  String getEnumLiteralInstanceConstantName();

  /**
   * @since 2.3
   */
  String getEnumLiteralValueConstantName();

  GenPackage getGenPackage(); // returns the package in which this enum literal is defined
  String getEnumLiteralAccessorName(); // returns the name of the enum literal get method in the package interface

  String getFormattedName();

  void initialize(EEnumLiteral eEnumLiteral);
  String getModelInfo();

  boolean reconcile(GenEnumLiteral oldGenEnumLiteralVersion);

} 
