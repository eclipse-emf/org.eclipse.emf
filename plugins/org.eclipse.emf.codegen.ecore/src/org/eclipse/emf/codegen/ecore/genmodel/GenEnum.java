/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
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
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#isTypeSafeEnumCompatible <em>Type Safe Enum Compatible</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getEcoreEnum <em>Ecore Enum</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#getGenEnumLiterals <em>Gen Enum Literals</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEnum()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NoEcoreDataType'"
 * @generated
 */
public interface GenEnum extends GenDataType
{
  /**
   * Returns the value of the '<em><b>Type Safe Enum Compatible</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * </p>
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Safe Enum Compatible</em>' attribute.
   * @see #setTypeSafeEnumCompatible(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEnum_TypeSafeEnumCompatible()
   * @model default="true"
   * @generated
   */
  boolean isTypeSafeEnumCompatible();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenEnum#isTypeSafeEnumCompatible <em>Type Safe Enum Compatible</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Safe Enum Compatible</em>' attribute.
   * @see #isTypeSafeEnumCompatible()
   * @generated
   */
  void setTypeSafeEnumCompatible(boolean value);

  /**
   * Returns the value of the '<em><b>Ecore Enum</b></em>' reference.
   * <!-- begin-user-doc -->
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
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Enum Literals</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEnum_GenEnumLiterals()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral#getGenEnum
   * @model opposite="genEnum" containment="true"
   * @generated
   */
  EList<GenEnumLiteral> getGenEnumLiterals();

  String getQualifiedName();
  String getImportedName();

  GenEnumLiteral getGenEnumLiteral(String literal);
  String getEnumLiteralID(GenEnumLiteral genEnumLiteral);
  int getEnumLiteralValue(GenEnumLiteral genEnumLiteral);

  List<GenEnumLiteral> getUniqueValuedGenEnumLiterals();

  void initialize(EEnum eEnum);

  boolean reconcile(GenEnum oldGenEnumVersion);

}
