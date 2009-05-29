/**
 * <copyright>
 * </copyright>
 *
 * $Id: Project.java,v 1.1 2009/05/29 15:03:43 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core.model.project;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getShortname <em>Shortname</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getSubprojects <em>Subprojects</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getCommitters <em>Committers</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getProjectleads <em>Projectleads</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getEnd <em>End</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getLongname <em>Longname</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getProject()
 * @model
 * @generated
 */
public interface Project extends EObject
{
  /**
   * Returns the value of the '<em><b>Shortname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Shortname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Shortname</em>' attribute.
   * @see #setShortname(String)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getProject_Shortname()
   * @model
   * @generated
   */
  String getShortname();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getShortname <em>Shortname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Shortname</em>' attribute.
   * @see #getShortname()
   * @generated
   */
  void setShortname(String value);

  /**
   * Returns the value of the '<em><b>Subprojects</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.examples.databinding.project.core.model.project.Project}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getParent <em>Parent</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subprojects</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subprojects</em>' containment reference list.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getProject_Subprojects()
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getParent
   * @model opposite="parent" containment="true"
   * @generated
   */
  EList<Project> getSubprojects();

  /**
   * Returns the value of the '<em><b>Committers</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getProject <em>Project</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Committers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Committers</em>' containment reference list.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getProject_Committers()
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getProject
   * @model opposite="project" containment="true"
   * @generated
   */
  EList<CommitterShip> getCommitters();

  /**
   * Returns the value of the '<em><b>Parent</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getSubprojects <em>Subprojects</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parent</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parent</em>' container reference.
   * @see #setParent(Project)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getProject_Parent()
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getSubprojects
   * @model opposite="subprojects" transient="false"
   * @generated
   */
  Project getParent();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getParent <em>Parent</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parent</em>' container reference.
   * @see #getParent()
   * @generated
   */
  void setParent(Project value);

  /**
   * Returns the value of the '<em><b>Projectleads</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.examples.databinding.project.core.model.project.Person}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Projectleads</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Projectleads</em>' reference list.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getProject_Projectleads()
   * @model
   * @generated
   */
  EList<Person> getProjectleads();

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
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getProject_Start()
   * @model
   * @generated
   */
  Date getStart();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getStart <em>Start</em>}' attribute.
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
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getProject_End()
   * @model
   * @generated
   */
  Date getEnd();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getEnd <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' attribute.
   * @see #getEnd()
   * @generated
   */
  void setEnd(Date value);

  /**
   * Returns the value of the '<em><b>Longname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Longname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Longname</em>' attribute.
   * @see #setLongname(String)
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage#getProject_Longname()
   * @model
   * @generated
   */
  String getLongname();

  /**
   * Sets the value of the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getLongname <em>Longname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Longname</em>' attribute.
   * @see #getLongname()
   * @generated
   */
  void setLongname(String value);

} // Project
