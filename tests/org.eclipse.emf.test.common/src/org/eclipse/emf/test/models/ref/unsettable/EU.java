/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.ref.unsettable;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EU</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.EU#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.EU#getIds <em>Ids</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.EU#getLabels <em>Labels</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.EU#getDu <em>Du</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getEU()
 * @model
 * @generated
 */
public interface EU extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #isSetName()
   * @see #unsetName()
   * @see #setName(String)
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getEU_Name()
   * @model unsettable="true"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #isSetName()
   * @see #unsetName()
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetName()
   * @see #getName()
   * @see #setName(String)
   * @generated
   */
  void unsetName();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getName <em>Name</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Name</em>' attribute is set.
   * @see #unsetName()
   * @see #getName()
   * @see #setName(String)
   * @generated
   */
  boolean isSetName();

  /**
   * Returns the value of the '<em><b>Ids</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ids</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ids</em>' attribute list.
   * @see #isSetIds()
   * @see #unsetIds()
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getEU_Ids()
   * @model unsettable="true"
   * @generated
   */
  EList<String> getIds();

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getIds <em>Ids</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetIds()
   * @see #getIds()
   * @generated
   */
  void unsetIds();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getIds <em>Ids</em>}' attribute list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Ids</em>' attribute list is set.
   * @see #unsetIds()
   * @see #getIds()
   * @generated
   */
  boolean isSetIds();

  /**
   * Returns the value of the '<em><b>Labels</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Labels</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Labels</em>' attribute list.
   * @see #isSetLabels()
   * @see #unsetLabels()
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getEU_Labels()
   * @model unsettable="true"
   * @generated
   */
  EList<String> getLabels();

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getLabels <em>Labels</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetLabels()
   * @see #getLabels()
   * @generated
   */
  void unsetLabels();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getLabels <em>Labels</em>}' attribute list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Labels</em>' attribute list is set.
   * @see #unsetLabels()
   * @see #getLabels()
   * @generated
   */
  boolean isSetLabels();

  /**
   * Returns the value of the '<em><b>Du</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.unsettable.DU}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.unsettable.DU#getEu <em>Eu</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Du</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Du</em>' reference list.
   * @see #isSetDu()
   * @see #unsetDu()
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getEU_Du()
   * @see org.eclipse.emf.test.models.ref.unsettable.DU#getEu
   * @model opposite="eu" unsettable="true"
   * @generated
   */
  EList<DU> getDu();

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getDu <em>Du</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetDu()
   * @see #getDu()
   * @generated
   */
  void unsetDu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.EU#getDu <em>Du</em>}' reference list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Du</em>' reference list is set.
   * @see #unsetDu()
   * @see #getDu()
   * @generated
   */
  boolean isSetDu();

} // EU
