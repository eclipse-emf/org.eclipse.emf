/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import org.eclipse.emf.ecore.ETypeParameter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Type Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter#getEcoreTypeParameter <em>Ecore Type Parameter</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenTypeParameter()
 * @model
 * @generated
 */
public interface GenTypeParameter extends GenBase
{
  /**
   * Returns the value of the '<em><b>Ecore Type Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ecore Type Parameter</em>' reference.
   * @see #setEcoreTypeParameter(ETypeParameter)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenTypeParameter_EcoreTypeParameter()
   * @model required="true"
   * @generated
   */
  ETypeParameter getEcoreTypeParameter();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter#getEcoreTypeParameter <em>Ecore Type Parameter</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ecore Type Parameter</em>' reference.
   * @see #getEcoreTypeParameter()
   * @generated
   */
  void setEcoreTypeParameter(ETypeParameter value);
  
  /**
   * Returns the value of the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.14
   * <!-- end-model-doc -->
   * @return the value of the '<em>Documentation</em>' attribute.
   * @see #setDocumentation(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenTypeParameter_Documentation()
   * @model unsettable="true" suppressedIsSetVisibility="true" suppressedUnsetVisibility="true"
   * @generated
   */
  String getDocumentation();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter#getDocumentation <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Documentation</em>' attribute.
   * @see #getDocumentation()
   * @since 2.14
   * @generated
   */
  void setDocumentation(String value);

  String getName();

  void initialize(ETypeParameter eTypeParameter);

  boolean reconcile(GenTypeParameter oldGenTypeParameterVersion);
  
  boolean isUsed();

  String getQualifiedModelInfo();

} // GenTypeParameter
