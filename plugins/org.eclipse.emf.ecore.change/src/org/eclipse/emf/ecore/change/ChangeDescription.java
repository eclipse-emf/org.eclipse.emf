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
 * $Id: ChangeDescription.java,v 1.3 2005/02/08 21:07:46 marcelop Exp $
 */
package org.eclipse.emf.ecore.change;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.change.ChangeDescription#getObjectChanges <em>Object Changes</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ChangeDescription#getObjectsToDetach <em>Objects To Detach</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ChangeDescription#getObjectsToAttach <em>Objects To Attach</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ChangeDescription#getResourceChanges <em>Resource Changes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.change.ChangePackage#getChangeDescription()
 * @model
 * @generated
 */
public interface ChangeDescription extends EObject{
  /**
   * Returns the value of the '<em><b>Object Changes</b></em>' map.
   * The key is of type {@link org.eclipse.emf.ecore.EObject},
   * and the value is of type list of {@link org.eclipse.emf.ecore.change.FeatureChange},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Object Changes</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Object Changes</em>' map.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getChangeDescription_ObjectChanges()
   * @model mapType="org.eclipse.emf.ecore.change.EObjectToChangesMapEntry" keyType="org.eclipse.emf.ecore.EObject" valueType="org.eclipse.emf.ecore.change.FeatureChange"
   * @generated
   */
  EMap getObjectChanges();

  /**
   * Returns the value of the '<em><b>Objects To Detach</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * Returns the list of objects that will be detached when this Change Description
   * is applied.  The list is calculated every time this method is called.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Objects To Detach</em>' reference list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getChangeDescription_ObjectsToDetach()
   * @model type="org.eclipse.emf.ecore.EObject" resolveProxies="false" transient="true" derived="true"
   * @generated
   */
  EList getObjectsToDetach();

  /**
   * Returns the value of the '<em><b>Objects To Attach</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Objects To Attach</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Objects To Attach</em>' containment reference list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getChangeDescription_ObjectsToAttach()
   * @model type="org.eclipse.emf.ecore.EObject" containment="true" resolveProxies="false"
   * @generated
   */
  EList getObjectsToAttach();

  /**
   * Returns the value of the '<em><b>Resource Changes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.change.ResourceChange}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resource Changes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource Changes</em>' containment reference list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getChangeDescription_ResourceChanges()
   * @model type="org.eclipse.emf.ecore.change.ResourceChange" containment="true"
   * @generated
   */
  EList getResourceChanges();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void apply();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void applyAndReverse();

} // ChangeDescription
