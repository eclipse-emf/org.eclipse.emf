/**
 * Copyright (c) 2002-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import org.eclipse.emf.common.util.EList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Meta Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenPackage <em>Gen Package</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenTypeParameters <em>Gen Type Parameters</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClassifier()
 * @model abstract="true"
 * @generated
 */
public interface GenClassifier extends GenBase
{
  /**
   * Returns the value of the '<em><b>Gen Package</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenClassifiers <em>Gen Classifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Package</em>' reference.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClassifier_GenPackage()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenClassifiers
   * @model opposite="genClassifiers" resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
   * @generated
   */
  GenPackage getGenPackage();

  /**
   * Returns the value of the '<em><b>Gen Type Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter}.
   * <!-- begin-user-doc -->
   * <p>
   * </p>
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Type Parameters</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClassifier_GenTypeParameters()
   * @model containment="true"
   * @generated
   */
  EList<GenTypeParameter> getGenTypeParameters();

  /**
   * Returns the value of the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.14
   * <!-- end-model-doc -->
   * @return the value of the '<em>Documentation</em>' attribute.
   * @see #setDocumentation(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClassifier_Documentation()
   * @model unsettable="true" suppressedIsSetVisibility="true" suppressedUnsetVisibility="true"
   * @generated
   */
  String getDocumentation();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getDocumentation <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Documentation</em>' attribute.
   * @see #getDocumentation()
   * @since 2.14
   * @generated
   */
  void setDocumentation(String value);

  EClassifier getEcoreClassifier();
  String getImportedMetaType();
  String getMetaType();

  String getName();
  String getClassifierAccessorName();
  String getQualifiedClassifierAccessor();

  String getSafeUncapName();

  String getImportedInstanceClassName();
  
  /**
   * @since 2.3
   */
  String getRawImportedInstanceClassName();

  /**
   * @since 2.3
   */
  String getRawInstanceClassName();
  
  /**
   * @since 2.3
   */
  String getImportedParameterizedInstanceClassName();

  /**
   * @since 2.3
   */
  String getImportedWildcardInstanceClassName();

  /**
   * @since 2.3
   */
  String getImportedBoundedWildcardInstanceClassName();

  /**
   * @since 2.3
   */
  boolean isUncheckedCast();

  /**
   * This returns a name like 'XSDElementDeclaration' formatted like 'XSD Element Declaration'
   */
  String getFormattedName();

  String getClassifierInstanceName();
  String getClassifierID();

  List<String> getGenConstraints();
  List<String> getAllGenConstraints();
  GenClassifier getConstraintImplementor(String constraint);
  GenClassifier getConstraintDelegate(String constraint);
  boolean hasOnlyDefaultConstraints();

  String getGeneratedInstanceClassFlag();

  /**
   * @since 2.6
   */
  boolean hasConstraintExpression(String constraint);

  /**
   * @since 2.6
   */
  String getConstraintExpression(String constraint, String indentation);

  /**
   * @since 2.6
   */
  String getValidationDelegate(String constraint);
}
