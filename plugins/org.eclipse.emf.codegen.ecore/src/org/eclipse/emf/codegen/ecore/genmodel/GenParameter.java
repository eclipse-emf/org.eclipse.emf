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
 * $Id: GenParameter.java,v 1.8 2011/10/28 01:20:13 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import org.eclipse.emf.ecore.EParameter;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getGenOperation <em>Gen Operation</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getEcoreParameter <em>Ecore Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenParameter()
 * @model
 * @generated
 */
public interface GenParameter extends GenTypedElement
{
  /**
   * Returns the value of the '<em><b>Gen Operation</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenParameters <em>Gen Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Operation</em>' container reference.
   * @see #setGenOperation(GenOperation)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenParameter_GenOperation()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenParameters
   * @model opposite="genParameters" required="true"
   * @generated
   */
  GenOperation getGenOperation();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getGenOperation <em>Gen Operation</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Gen Operation</em>' container reference.
   * @see #getGenOperation()
   * @generated
   */
  void setGenOperation(GenOperation value);

  /**
   * Returns the value of the '<em><b>Ecore Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ecore Parameter</em>' reference.
   * @see #setEcoreParameter(EParameter)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenParameter_EcoreParameter()
   * @model required="true"
   * @generated
   */
  EParameter getEcoreParameter();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getEcoreParameter <em>Ecore Parameter</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ecore Parameter</em>' reference.
   * @see #getEcoreParameter()
   * @generated
   */
  void setEcoreParameter(EParameter value);

  String getName();
  GenPackage getGenPackage();

  void initialize(EParameter eParameter);
  String getQualifiedModelInfo();

  boolean reconcile(GenParameter oldGenParameterVersion);
  
  /**
   * Returns whether the parameter's type depends on the parameter's operation's type parameters.
   * @since 2.8
   */
  boolean usesOperationTypeParameters();
}
