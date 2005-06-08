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
 * $Id: GenClassifier.java,v 1.5 2005/06/08 06:18:44 nickb Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


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

  EClassifier getEcoreClassifier();
  String getImportedMetaType();
  String getMetaType();

  String getName();
  String getClassifierAccessorName();

  String getSafeUncapName();

  String getImportedInstanceClassName();

  /**
   * This returns a name like 'XSDElementDeclaration' formatted like 'XSD Element Declaration'
   */
  String getFormattedName();

  String getClassifierInstanceName();
  String getClassifierID();

  List /* of String */ getGenConstraints();
  List /* of String */ getAllGenConstraints();
  GenClassifier getConstraintImplementor(String constraint);
  GenClassifier getConstraintDelegate(String constraint);
  boolean hasOnlyDefaultConstraints();

  String getGeneratedInstanceClassFlag();
}
