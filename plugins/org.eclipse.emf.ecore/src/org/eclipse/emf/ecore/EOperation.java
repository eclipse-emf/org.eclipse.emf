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
 * $Id: EOperation.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
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
 *   <li>{@link org.eclipse.emf.ecore.EOperation#getEParameters <em>EParameters</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EOperation#getEExceptions <em>EExceptions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.EcorePackage#getEOperation()
 * @model 
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
   * @model opposite="eOperations" changeable="false"
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
   * @model type="org.eclipse.emf.ecore.EParameter" opposite="eOperation" containment="true"
   * @generated
   */
  EList getEParameters();

  /**
   * Returns the value of the '<em><b>EExceptions</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EClassifier}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the exceptions thrown by this operation.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EExceptions</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEOperation_EExceptions()
   * @model type="org.eclipse.emf.ecore.EClassifier"
   * @generated
   */
  EList getEExceptions();

} //EOperation
