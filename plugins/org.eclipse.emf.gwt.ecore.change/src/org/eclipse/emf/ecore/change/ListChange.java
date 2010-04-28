/**
 * <copyright>
 *
 * Copyright (c) 2003-2006 IBM Corporation and others.
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
 * $Id: ListChange.java,v 1.1 2010/04/28 14:46:51 emerks Exp $
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
 *   <li>{@link org.eclipse.emf.ecore.change.ListChange#getFeatureMapEntryValues <em>Feature Map Entry Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange()
 * @model
 * @generated
 */
public interface ListChange extends EObject
{
  /**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.ecore.change.ChangeKind}.
   * <!-- begin-user-doc -->
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
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Values</em>' attribute list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_DataValues()
   * @model unique="false"
   * @generated
   */
  EList<String> getDataValues();

  /**
   * Returns the value of the '<em><b>Index</b></em>' attribute.
   * The default value is <code>"-1"</code>.
   * <!-- begin-user-doc -->
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
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Values</em>' reference list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_ReferenceValues()
   * @model
   * @generated
   */
  EList<EObject> getReferenceValues();

  /**
   * Returns the value of the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
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
   * Returns the value of the '<em><b>Feature Map Entry Values</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.change.FeatureMapEntry}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Map Entry Values</em>' containment reference list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_FeatureMapEntryValues()
   * @model containment="true"
   * @generated
   */
  EList<FeatureMapEntry> getFeatureMapEntryValues();

  /**
   * Returns the value of the '<em><b>Values</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Object}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Values</em>' attribute list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getListChange_Values()
   * @model transient="true" volatile="true" derived="true"
   * @generated
   */
  EList<Object> getValues();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model originalListMany="false"
   * @generated
   */
  void apply(EList<Object> originalList);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model originalListMany="false"
   * @generated
   */
  void applyAndReverse(EList<Object> originalList);

} // ListChange
