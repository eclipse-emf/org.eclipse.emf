/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: ListChange.java,v 1.2 2004/12/13 20:22:40 marcelop Exp $
 */
package org.eclipse.emf.ecore.change;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.change.ListChange#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ListChange#getDataValues <em>Data Values</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ListChange#getIndex <em>Index</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ListChange#getMoveToIndex <em>Move To Index</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ListChange#getValues <em>Values</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ListChange#getReferenceValues <em>Reference Values</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ListChange#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange()
 * @model 
 * @generated
 */
public interface ListChange extends EObject{
  /**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.ecore.change.ChangeKind}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Kind</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.eclipse.emf.ecore.change.ChangeKind
   * @see #setKind(ChangeKind)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_Kind()
   * @model 
   * @generated
   */
  ChangeKind getKind();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.ListChange#getKind <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.eclipse.emf.ecore.change.ChangeKind
   * @see #getKind()
   * @generated
   */
  void setKind(ChangeKind value);

  /**
   * Returns the value of the '<em><b>Data Values</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Values</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Values</em>' attribute list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_DataValues()
   * @model type="java.lang.String"
   * @generated
   */
  EList getDataValues();

  /**
   * Returns the value of the '<em><b>Index</b></em>' attribute.
   * The default value is <code>"-1"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Index</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Index</em>' attribute.
   * @see #setIndex(int)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_Index()
   * @model default="-1"
   * @generated
   */
  int getIndex();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.ListChange#getIndex <em>Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Index</em>' attribute.
   * @see #getIndex()
   * @generated
   */
  void setIndex(int value);

  /**
   * Returns the value of the '<em><b>Move To Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Move To Index</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Move To Index</em>' attribute.
   * @see #setMoveToIndex(int)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_MoveToIndex()
   * @model 
   * @generated
   */
  int getMoveToIndex();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.ListChange#getMoveToIndex <em>Move To Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Move To Index</em>' attribute.
   * @see #getMoveToIndex()
   * @generated
   */
  void setMoveToIndex(int value);

  /**
   * Returns the value of the '<em><b>Reference Values</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Values</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Values</em>' reference list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_ReferenceValues()
   * @model type="org.eclipse.emf.ecore.EObject"
   * @generated
   */
  EList getReferenceValues();

  /**
   * Returns the value of the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature</em>' reference.
   * @see #setFeature(EStructuralFeature)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_Feature()
   * @model 
   * @generated
   */
  EStructuralFeature getFeature();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.ListChange#getFeature <em>Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature</em>' reference.
   * @see #getFeature()
   * @generated
   */
  void setFeature(EStructuralFeature value);

  /**
   * Returns the value of the '<em><b>Values</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Object}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Values</em>' attribute list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_Values()
   * @model type="java.lang.Object" transient="true" volatile="true" derived="true"
   * @generated
   */
  EList getValues();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model 
   * @generated
   */
  void apply(EList originalList);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model 
   * @generated
   */
  void applyAndReverse(EList originalList);

} // ListChange
