/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: GenClassifier.java,v 1.9 2007/05/10 13:52:56 emerks Exp $
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
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenPackage <em>Gen Package</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenClassifier#getGenTypeParameters <em>Gen Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenClassifier()
 * @model abstract="true"
 * @generated
 */
public interface GenClassifier extends GenBase {
  /**
   * Returns the value of the '<em><b>Gen Package</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenPackage#getGenClassifiers <em>Gen Classifiers</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gen Package</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
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
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter" containment="true"
   * @generated
   */
  EList<GenTypeParameter> getGenTypeParameters();

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
}
