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

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Committer Ship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getEnd <em>End</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getProject <em>Project</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getPerson <em>Person</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getCommitterShip()
 * @model
 * @generated
 */
public interface CommitterShip extends EObject
{
  /**
   * Returns the value of the '<em><b>Start</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start</em>' attribute.
   * @see #setStart(Date)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getCommitterShip_Start()
   * @model
   * @generated
   */
  Date getStart();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getStart <em>Start</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start</em>' attribute.
   * @see #getStart()
   * @generated
   */
  void setStart(Date value);

  /**
   * Returns the value of the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' attribute.
   * @see #setEnd(Date)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getCommitterShip_End()
   * @model
   * @generated
   */
  Date getEnd();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getEnd <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' attribute.
   * @see #getEnd()
   * @generated
   */
  void setEnd(Date value);

  /**
   * Returns the value of the '<em><b>Project</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getCommitters <em>Committers</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Project</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Project</em>' container reference.
   * @see #setProject(Project)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getCommitterShip_Project()
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getCommitters
   * @model opposite="committers" transient="false"
   * @generated
   */
  Project getProject();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getProject <em>Project</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Project</em>' container reference.
   * @see #getProject()
   * @generated
   */
  void setProject(Project value);

  /**
   * Returns the value of the '<em><b>Person</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getCommitterships <em>Committerships</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Person</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Person</em>' reference.
   * @see #setPerson(Person)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getCommitterShip_Person()
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Person#getCommitterships
   * @model opposite="committerships"
   * @generated
   */
  Person getPerson();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getPerson <em>Person</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Person</em>' reference.
   * @see #getPerson()
   * @generated
   */
  void setPerson(Person value);

} // CommitterShip
