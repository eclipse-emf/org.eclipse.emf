/**
 * <copyright>
 *
 * Copyright (c) 2003-2011 IBM Corporation and others.
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
 * $Id: FeatureChange.java,v 1.7 2011/04/07 23:41:04 emerks Exp $
 */
package org.eclipse.emf.ecore.change;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureChange#getFeatureName <em>Feature Name</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureChange#getDataValue <em>Data Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureChange#isSet <em>Set</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureChange#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureChange#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureChange#getReferenceValue <em>Reference Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureChange#getListChanges <em>List Changes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureChange()
 * @model
 * @generated
 */
public interface FeatureChange extends EObject
{
  /**
   * Returns the value of the '<em><b>Feature Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Name</em>' attribute.
   * @see #isSetFeatureName()
   * @see #unsetFeatureName()
   * @see #setFeatureName(String)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureChange_FeatureName()
   * @model unsettable="true" volatile="true"
   * @generated
   */
  String getFeatureName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.FeatureChange#getFeatureName <em>Feature Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature Name</em>' attribute.
   * @see #isSetFeatureName()
   * @see #unsetFeatureName()
   * @see #getFeatureName()
   * @generated
   */
  void setFeatureName(String value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.ecore.change.FeatureChange#getFeatureName <em>Feature Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetFeatureName()
   * @see #getFeatureName()
   * @see #setFeatureName(String)
   * @generated
   */
  void unsetFeatureName();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.ecore.change.FeatureChange#getFeatureName <em>Feature Name</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Feature Name</em>' attribute is set.
   * @see #unsetFeatureName()
   * @see #getFeatureName()
   * @see #setFeatureName(String)
   * @generated
   */
  boolean isSetFeatureName();

  /**
   * Returns the value of the '<em><b>Data Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Value</em>' attribute.
   * @see #setDataValue(String)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureChange_DataValue()
   * @model volatile="true"
   * @generated
   */
  String getDataValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.FeatureChange#getDataValue <em>Data Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Value</em>' attribute.
   * @see #getDataValue()
   * @generated
   */
  void setDataValue(String value);

  /**
   * Returns the value of the '<em><b>Set</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Set</em>' attribute.
   * @see #setSet(boolean)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureChange_Set()
   * @model default="true"
   * @generated
   */
  boolean isSet();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.FeatureChange#isSet <em>Set</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Set</em>' attribute.
   * @see #isSet()
   * @generated
   */
  void setSet(boolean value);

  /**
   * Returns the value of the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature</em>' reference.
   * @see #isSetFeature()
   * @see #unsetFeature()
   * @see #setFeature(EStructuralFeature)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureChange_Feature()
   * @model unsettable="true" required="true" volatile="true"
   * @generated
   */
  EStructuralFeature getFeature();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.FeatureChange#getFeature <em>Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature</em>' reference.
   * @see #isSetFeature()
   * @see #unsetFeature()
   * @see #getFeature()
   * @generated
   */
  void setFeature(EStructuralFeature value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.ecore.change.FeatureChange#getFeature <em>Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetFeature()
   * @see #getFeature()
   * @see #setFeature(EStructuralFeature)
   * @generated
   */
  void unsetFeature();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.ecore.change.FeatureChange#getFeature <em>Feature</em>}' reference is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Feature</em>' reference is set.
   * @see #unsetFeature()
   * @see #getFeature()
   * @see #setFeature(EStructuralFeature)
   * @generated
   */
  boolean isSetFeature();

  /**
   * Returns the value of the '<em><b>Reference Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Value</em>' reference.
   * @see #setReferenceValue(EObject)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureChange_ReferenceValue()
   * @model volatile="true"
   * @generated
   */
  EObject getReferenceValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.FeatureChange#getReferenceValue <em>Reference Value</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference Value</em>' reference.
   * @see #getReferenceValue()
   * @generated
   */
  void setReferenceValue(EObject value);

  /**
   * Returns the value of the '<em><b>List Changes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.change.ListChange}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>List Changes</em>' containment reference list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureChange_ListChanges()
   * @model containment="true"
   * @generated
   */
  EList<ListChange> getListChanges();

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureChange_Value()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  Object getValue();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void apply(EObject originalObject);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void applyAndReverse(EObject originalObject);

  /**
   * <!-- begin-user-doc -->
   * @since 2.7
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void reverse(EObject originalObject);

} // FeatureChange
