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
 * $Id: FeatureMapEntry.java,v 1.1 2010/04/28 14:46:51 emerks Exp $
 */
package org.eclipse.emf.ecore.change;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Map Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getFeatureName <em>Feature Name</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getDataValue <em>Data Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getReferenceValue <em>Reference Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureMapEntry()
 * @model
 * @generated
 */
public interface FeatureMapEntry extends EObject
{
  /**
   * Returns the value of the '<em><b>Feature Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature Name</em>' attribute.
   * @see #isSetFeatureName()
   * @see #unsetFeatureName()
   * @see #setFeatureName(String)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureMapEntry_FeatureName()
   * @model unsettable="true" volatile="true"
   * @generated
   */
  String getFeatureName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getFeatureName <em>Feature Name</em>}' attribute.
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
   * Unsets the value of the '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getFeatureName <em>Feature Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetFeatureName()
   * @see #getFeatureName()
   * @see #setFeatureName(String)
   * @generated
   */
  void unsetFeatureName();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getFeatureName <em>Feature Name</em>}' attribute is set.
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
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureMapEntry_DataValue()
   * @model volatile="true"
   * @generated
   */
  String getDataValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getDataValue <em>Data Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Value</em>' attribute.
   * @see #getDataValue()
   * @generated
   */
  void setDataValue(String value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureMapEntry_Value()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  Object getValue();

  /**
   * Returns the value of the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature</em>' reference.
   * @see #isSetFeature()
   * @see #unsetFeature()
   * @see #setFeature(EStructuralFeature)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureMapEntry_Feature()
   * @model unsettable="true" required="true" volatile="true"
   * @generated
   */
  EStructuralFeature getFeature();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getFeature <em>Feature</em>}' reference.
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
   * Unsets the value of the '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getFeature <em>Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetFeature()
   * @see #getFeature()
   * @see #setFeature(EStructuralFeature)
   * @generated
   */
  void unsetFeature();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getFeature <em>Feature</em>}' reference is set.
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
   * @see org.eclipse.emf.ecore.change.ChangePackage#getFeatureMapEntry_ReferenceValue()
   * @model volatile="true"
   * @generated
   */
  EObject getReferenceValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.FeatureMapEntry#getReferenceValue <em>Reference Value</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference Value</em>' reference.
   * @see #getReferenceValue()
   * @generated
   */
  void setReferenceValue(EObject value);

} // FeatureMapEntry
