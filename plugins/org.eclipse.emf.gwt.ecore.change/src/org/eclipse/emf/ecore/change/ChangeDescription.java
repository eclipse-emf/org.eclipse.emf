/**
 * <copyright>
 *
 * Copyright (c) 2003-2010 IBM Corporation and others.
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
 * $Id: ChangeDescription.java,v 1.2 2010/04/28 20:37:23 khussey Exp $
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
public interface ChangeDescription extends EObject
{
  /**
   * Returns the value of the '<em><b>Object Changes</b></em>' map.
   * The key is of type {@link org.eclipse.emf.ecore.EObject},
   * and the value is of type list of {@link org.eclipse.emf.ecore.change.FeatureChange},
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Object Changes</em>' map.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getChangeDescription_ObjectChanges()
   * @model mapType="org.eclipse.emf.ecore.change.EObjectToChangesMapEntry<org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.change.FeatureChange>"
   * @generated
   */
  EMap<EObject, EList<FeatureChange>> getObjectChanges();

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
   * @model resolveProxies="false" transient="true" derived="true"
   * @generated
   */
  EList<EObject> getObjectsToDetach();

  /**
   * Returns the value of the '<em><b>Objects To Attach</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Objects To Attach</em>' containment reference list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getChangeDescription_ObjectsToAttach()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getObjectsToAttach();

  /**
   * Returns the value of the '<em><b>Resource Changes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.change.ResourceChange}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource Changes</em>' containment reference list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getChangeDescription_ResourceChanges()
   * @model containment="true"
   * @generated
   */
  EList<ResourceChange> getResourceChanges();

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
