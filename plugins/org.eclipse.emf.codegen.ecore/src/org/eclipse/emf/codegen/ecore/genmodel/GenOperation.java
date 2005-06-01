/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenOperation.java,v 1.8 2005/06/01 19:30:50 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenClass <em>Gen Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getEcoreOperation <em>Ecore Operation</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenParameters <em>Gen Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenOperation()
 * @model
 * @generated
 */
public interface GenOperation extends GenTypedElement {
  /**
   * Returns the value of the '<em><b>Gen Class</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenOperations <em>Gen Operations</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gen Class</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Class</em>' container reference.
   * @see #setGenClass(GenClass)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenOperation_GenClass()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenOperations
   * @model opposite="genOperations" required="true"
   * @generated
   */
  GenClass getGenClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenClass <em>Gen Class</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Gen Class</em>' container reference.
   * @see #getGenClass()
   * @generated
   */
  void setGenClass(GenClass value);

  /**
   * Returns the value of the '<em><b>Ecore Operation</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ecore Operation</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ecore Operation</em>' reference.
   * @see #setEcoreOperation(EOperation)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenOperation_EcoreOperation()
   * @model required="true"
   * @generated
   */
  EOperation getEcoreOperation();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getEcoreOperation <em>Ecore Operation</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ecore Operation</em>' reference.
   * @see #getEcoreOperation()
   * @generated
   */
  void setEcoreOperation(EOperation value);

  /**
   * Returns the value of the '<em><b>Gen Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenParameter}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getGenOperation <em>Gen Operation</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gen Parameters</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Parameters</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenOperation_GenParameters()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getGenOperation
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenParameter" opposite="genOperation" containment="true"
   * @generated
   */
  EList getGenParameters();

  String getName();
  String getCapName();
  String getFormattedName();

  boolean isVoid();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#getTypeClassifierAccessorName getTypeClassifierAccessorName} instead.
   */
  String getReturnTypeClassifier();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#getTypeGenPackage getTypeGenPackage} instead.
   */
  GenPackage getReturnTypeGenPackage();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#getType getType} instead.
   */
  String getReturnType();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#getImportedType getImportedType} instead.
   */
  String getImportedReturnType();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#getObjectType getObjectType} instead.
   */
  String getObjectReturnType();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#isPrimitiveType isPrimitiveType} instead.
   */
  boolean isPrimitiveReturnType();

  String getParameters();
  String getParameterTypes(String separator);
  String getParameterTypes(String separator, boolean qualified);
  String getParameterNames(String separator);

  String getImportedMetaType();

  GenPackage getGenPackage();

  void initialize(EOperation eOperation);
  String getModelInfo();

  boolean reconcile(GenOperation oldGenOperationVersion);
  boolean hasBody();
  String getBody(String indentation);

  List getGenExceptions();
  String getThrows();

  boolean isInvariant();
}
