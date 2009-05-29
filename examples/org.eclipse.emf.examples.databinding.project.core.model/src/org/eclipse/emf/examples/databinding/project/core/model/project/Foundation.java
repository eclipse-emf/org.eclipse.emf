/**
 * <copyright>
 * </copyright>
 *
 * $Id: Foundation.java,v 1.1 2009/05/29 15:03:43 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core.model.project;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Foundation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Foundation#getProjects <em>Projects</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Foundation#getPersons <em>Persons</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getFoundation()
 * @model
 * @generated
 */
public interface Foundation extends EObject
{
  /**
   * Returns the value of the '<em><b>Projects</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.examples.databinding.project.core.model.project.Project}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Projects</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Projects</em>' containment reference list.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getFoundation_Projects()
   * @model containment="true"
   * @generated
   */
  EList<Project> getProjects();

  /**
   * Returns the value of the '<em><b>Persons</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.examples.databinding.project.core.model.project.Person}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Persons</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Persons</em>' containment reference list.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getFoundation_Persons()
   * @model containment="true"
   * @generated
   */
  EList<Person> getPersons();

} // Foundation
