/**
 * <copyright> 
 *
 * Copyright (c) 2002-2009 IBM Corporation and others.
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
 * $Id: GenOperation.java,v 1.19 2009/09/18 18:10:34 khussey Exp $
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
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenOperation#getGenTypeParameters <em>Gen Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenOperation()
 * @model
 * @generated
 */
public interface GenOperation extends GenTypedElement
{
  /**
   * Returns the value of the '<em><b>Gen Class</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenOperations <em>Gen Operations</em>}'.
   * <!-- begin-user-doc -->
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
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Parameters</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenOperation_GenParameters()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenParameter#getGenOperation
   * @model opposite="genOperation" containment="true"
   * @generated
   */
  EList<GenParameter> getGenParameters();

  /**
   * Returns the value of the '<em><b>Gen Type Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter}.
   * <!-- begin-user-doc -->
   * <p>
   * </p>
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Type Parameters</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenOperation_GenTypeParameters()
   * @model containment="true"
   * @generated
   */
  EList<GenTypeParameter> getGenTypeParameters();

  String getName();
  String getCapName();
  String getFormattedName();

  boolean isVoid();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#getTypeClassifierAccessorName getTypeClassifierAccessorName} instead.
   */
  @Deprecated
  String getReturnTypeClassifier();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#getTypeGenPackage getTypeGenPackage} instead.
   */
  @Deprecated
  GenPackage getReturnTypeGenPackage();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#getType(GenClass) getType(GenClass)} instead.
   */
  @Deprecated
  String getReturnType();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#getImportedType(GenClass) getImportedType(GenClass)} instead.
   */
  @Deprecated
  String getImportedReturnType();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#getObjectType(GenClass) getObjectType(GenClass)} instead.
   */
  @Deprecated
  String getObjectReturnType();

  /**
   * @deprecated As of EMF 2.1, use {@link GenTypedElement#isPrimitiveType isPrimitiveType} instead.
   */
  @Deprecated
  boolean isPrimitiveReturnType();

  /**
   * @since 2.3
   */
  String getParameters(GenClass context);

  /**
   * Use {@link #getParameters(GenClass)} with either <code>null</code> for erasing type parameter references 
   * or a {@link GenClass} context representing potential type substitutions for type parameter references.
   * By default, this will just do <code>getParameters(getGenClass())</code>.
   * @see #getParameters(GenClass)
   * @deprecated
   */
  @Deprecated
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

  /**
   * @since 2.6
   */
  boolean hasInvariantExpression();

  /**
   * @since 2.6
   */
  String getInvariantExpression(String indentation);

  /**
   * @since 2.6
   */
  String getValidationDelegate();

  List<GenClassifier> getGenExceptions();

  /**
   * @since 2.3
   */
  String getThrows(GenClass context);

  /**
   * Use {@link #getThrows(GenClass)} with either <code>null</code> for erasing type parameter references 
   * or a {@link GenClass} context representing potential type substitutions for type parameter references.
   * By default, this will just do <code>getThrows(getGenClass())</code>.
   * @see #getThrows(GenClass)
   * @deprecated
   */
  @Deprecated
  String getThrows();

  boolean isInvariant();

  /**
   * @since 2.3
   */
  boolean isOverrideOf(GenClass context, GenOperation genOperation);

  /**
   * Use {@link #isOverrideOf(GenClass, GenOperation)} with either <code>null</code> for erasing type parameter references 
   * or a {@link GenClass} context representing potential type substitutions for type parameter references.
   * By default, this will just do <code>isOverrideOf(getGenClass(), genOperation)</code>.
   * @see #isOverrideOf(GenClass, GenOperation)
   * @deprecated
   */
  @Deprecated
  boolean isOverrideOf(GenOperation genOperation);

  /**
   * @since 2.3
   */
  String getTypeParameters(GenClass context);

  /**
   * @since 2.3
   */
  boolean hasGenericExceptions();

  /**
   * @since 2.3
   */
  boolean hasParameterDocumentation();

  /**
   * @since 2.4
   */
  boolean isSuppressedVisibility();
}
