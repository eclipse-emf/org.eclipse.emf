/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: GenEnum.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnum;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Enum</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getEcoreEnum <em>Ecore Enum</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getGenEnumLiterals <em>Gen Enum Literals</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEnum()
 * @model 
 * @generated
 */
public interface GenEnum extends GenDataType{
  /**
   * Returns the value of the '<em><b>Ecore Enum</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ecore Enum</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ecore Enum</em>' reference.
   * @see #setEcoreEnum(EEnum)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEnum_EcoreEnum()
   * @model required="true"
   * @generated
   */
  EEnum getEcoreEnum();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getEcoreEnum <em>Ecore Enum</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ecore Enum</em>' reference.
   * @see #getEcoreEnum()
   * @generated
   */
  void setEcoreEnum(EEnum value);

  /**
   * Returns the value of the '<em><b>Gen Enum Literals</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getGenEnum <em>Gen Enum</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gen Enum Literals</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Enum Literals</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEnum_GenEnumLiterals()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getGenEnum
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral" opposite="genEnum" containment="true"
   * @generated
   */
  EList getGenEnumLiterals();

  String getQualifiedName();
  String getImportedName();

  GenEnumLiteral getGenEnumLiteral(String literalName);
  String getEnumLiteralID(GenEnumLiteral genEnumLiteral);
  int getEnumLiteralValue(GenEnumLiteral genEnumLiteral);

  List/*of GenEnumLiteral*/ getUniqueValuedGenEnumLiterals();

  void initialize(EEnum eEnum);

  boolean reconcile(GenEnum oldGenEnumVersion);

}
