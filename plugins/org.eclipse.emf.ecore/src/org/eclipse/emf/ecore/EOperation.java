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
 * $Id: EOperation.java,v 1.8 2007/06/12 15:07:48 emerks Exp $
 */
package org.eclipse.emf.ecore;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EOperation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.EOperation#getEContainingClass <em>EContaining Class</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EOperation#getETypeParameters <em>EType Parameters</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EOperation#getEParameters <em>EParameters</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EOperation#getEExceptions <em>EExceptions</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EOperation#getEGenericExceptions <em>EGeneric Exceptions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.EcorePackage#getEOperation()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='UniqueParameterNames UniqueTypeParameterNames NoRepeatingVoid'"
 * @generated
 */
public interface EOperation extends ETypedElement
{
  /**
   * Returns the value of the '<em><b>EContaining Class</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.EClass#getEOperations <em>EOperations</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * It represents a method in the Java sense.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EContaining Class</em>' container reference.
   * @see org.eclipse.emf.ecore.EcorePackage#getEOperation_EContainingClass()
   * @see org.eclipse.emf.ecore.EClass#getEOperations
   * @model opposite="eOperations" resolveProxies="false" changeable="false"
   * @generated
   */
  EClass getEContainingClass();

  /**
   * Returns the value of the '<em><b>EParameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EParameter}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.EParameter#getEOperation <em>EOperation</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the valid arguments for this operation.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EParameters</em>' containment reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEOperation_EParameters()
   * @see org.eclipse.emf.ecore.EParameter#getEOperation
   * @model opposite="eOperation" containment="true"
   * @generated
   */
  EList<EParameter> getEParameters();

  /**
   * Returns the value of the '<em><b>EExceptions</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EClassifier}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the exceptions thrown by this operation
   * and is derived from the {@link #getEGenericExceptions() generic exceptions}.
   * </p>
   * @see #getEGenericExceptions()
   * <!-- end-user-doc -->
   * @return the value of the '<em>EExceptions</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEOperation_EExceptions()
   * @model unsettable="true" suppressedIsSetVisibility="true" suppressedUnsetVisibility="true"
   * @generated
   */
  EList<EClassifier> getEExceptions();

  /**
   * Returns the value of the '<em><b>EGeneric Exceptions</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EGenericType}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the generic exceptions thrown by this operation.
   * The {@link #getEExceptions() exceptions} list is derived from this one, i.e., it represents the {@link EGenericType#getERawType() erasure} of each generic exception.
   * </p>
   * @see #getEExceptions()
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>EGeneric Exceptions</em>' containment reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEOperation_EGenericExceptions()
   * @model containment="true" unsettable="true" suppressedIsSetVisibility="true" suppressedUnsetVisibility="true"
   * @generated
   */
  EList<EGenericType> getEGenericExceptions();

  /**
   * Returns the value of the '<em><b>EType Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.ETypeParameter}.
   * <!-- begin-user-doc -->
   * <p>
   * An operation can optionally introduce type parameters.
   * </p>
   * @since 2.3
   * <!-- end-user-doc -->
   * @return the value of the '<em>EType Parameters</em>' containment reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEOperation_ETypeParameters()
   * @model containment="true" resolveProxies="true"
   * @generated
   */
  EList<ETypeParameter> getETypeParameters();

} //EOperation
