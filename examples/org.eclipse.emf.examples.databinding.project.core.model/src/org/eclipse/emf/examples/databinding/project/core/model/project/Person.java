/**
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 */
package org.eclipse.emf.examples.databinding.project.core.model.project;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getLastname <em>Lastname</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getFirstname <em>Firstname</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getEmail <em>Email</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getCommitterships <em>Committerships</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getImage <em>Image</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getPerson()
 * @model
 * @generated
 */
public interface Person extends EObject
{
  /**
   * Returns the value of the '<em><b>Lastname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lastname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lastname</em>' attribute.
   * @see #setLastname(String)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getPerson_Lastname()
   * @model
   * @generated
   */
  String getLastname();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getLastname <em>Lastname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lastname</em>' attribute.
   * @see #getLastname()
   * @generated
   */
  void setLastname(String value);

  /**
   * Returns the value of the '<em><b>Firstname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Firstname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Firstname</em>' attribute.
   * @see #setFirstname(String)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getPerson_Firstname()
   * @model
   * @generated
   */
  String getFirstname();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getFirstname <em>Firstname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Firstname</em>' attribute.
   * @see #getFirstname()
   * @generated
   */
  void setFirstname(String value);

  /**
   * Returns the value of the '<em><b>Email</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Email</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Email</em>' attribute.
   * @see #setEmail(String)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getPerson_Email()
   * @model
   * @generated
   */
  String getEmail();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getEmail <em>Email</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Email</em>' attribute.
   * @see #getEmail()
   * @generated
   */
  void setEmail(String value);

  /**
   * Returns the value of the '<em><b>Committerships</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getPerson <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Committerships</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Committerships</em>' reference list.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getPerson_Committerships()
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getPerson
   * @model opposite="person"
   * @generated
   */
  EList<CommitterShip> getCommitterships();

  /**
   * Returns the value of the '<em><b>Image</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Image</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Image</em>' attribute.
   * @see #setImage(String)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getPerson_Image()
   * @model
   * @generated
   */
  String getImage();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getImage <em>Image</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Image</em>' attribute.
   * @see #getImage()
   * @generated
   */
  void setImage(String value);

} // Person
