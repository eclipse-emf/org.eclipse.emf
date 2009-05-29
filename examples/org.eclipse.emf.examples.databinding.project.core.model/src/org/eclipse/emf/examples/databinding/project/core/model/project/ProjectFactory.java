/**
 * <copyright>
 * </copyright>
 *
 * $Id: ProjectFactory.java,v 1.1 2009/05/29 15:03:43 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core.model.project;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage
 * @generated
 */
public interface ProjectFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ProjectFactory eINSTANCE = org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Foundation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Foundation</em>'.
   * @generated
   */
  Foundation createFoundation();

  /**
   * Returns a new object of class '<em>Project</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Project</em>'.
   * @generated
   */
  Project createProject();

  /**
   * Returns a new object of class '<em>Committer Ship</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Committer Ship</em>'.
   * @generated
   */
  CommitterShip createCommitterShip();

  /**
   * Returns a new object of class '<em>Person</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Person</em>'.
   * @generated
   */
  Person createPerson();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ProjectPackage getProjectPackage();

} //ProjectFactory
