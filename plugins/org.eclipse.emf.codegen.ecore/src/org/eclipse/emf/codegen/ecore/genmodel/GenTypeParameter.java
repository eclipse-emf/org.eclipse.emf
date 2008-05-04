/**
 * <copyright> 
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: GenTypeParameter.java,v 1.5 2008/05/04 17:03:27 emerks Exp $
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
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter#getEcoreTypeParameter <em>Ecore Type Parameter</em>}</li>
 * </ul>
 * </p>
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
  
  String getName();

  void initialize(ETypeParameter eTypeParameter);

  boolean reconcile(GenTypeParameter oldGenTypeParameterVersion);
  
  boolean isUsed();

  String getQualifiedModelInfo();

} // GenTypeParameter
