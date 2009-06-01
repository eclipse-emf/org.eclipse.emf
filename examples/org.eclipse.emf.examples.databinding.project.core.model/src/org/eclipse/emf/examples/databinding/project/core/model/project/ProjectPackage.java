/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ProjectPackage.java,v 1.4 2009/06/01 17:19:22 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core.model.project;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.examples.databinding.project.core.model.project.ProjectFactory
 * @model kind="package"
 * @generated
 */
public interface ProjectPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "project";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/project/1.0.0";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "project";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ProjectPackage eINSTANCE = org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.FoundationImpl <em>Foundation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.FoundationImpl
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectPackageImpl#getFoundation()
   * @generated
   */
  int FOUNDATION = 0;

  /**
   * The feature id for the '<em><b>Projects</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOUNDATION__PROJECTS = 0;

  /**
   * The feature id for the '<em><b>Persons</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOUNDATION__PERSONS = 1;

  /**
   * The number of structural features of the '<em>Foundation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOUNDATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl <em>Project</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectPackageImpl#getProject()
   * @generated
   */
  int PROJECT = 1;

  /**
   * The feature id for the '<em><b>Shortname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT__SHORTNAME = 0;

  /**
   * The feature id for the '<em><b>Subprojects</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT__SUBPROJECTS = 1;

  /**
   * The feature id for the '<em><b>Committers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT__COMMITTERS = 2;

  /**
   * The feature id for the '<em><b>Parent</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT__PARENT = 3;

  /**
   * The feature id for the '<em><b>Projectleads</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT__PROJECTLEADS = 4;

  /**
   * The feature id for the '<em><b>Start</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT__START = 5;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT__END = 6;

  /**
   * The feature id for the '<em><b>Longname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT__LONGNAME = 7;

  /**
   * The feature id for the '<em><b>Devmail</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT__DEVMAIL = 8;

  /**
   * The feature id for the '<em><b>Homepage</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT__HOMEPAGE = 9;

  /**
   * The number of structural features of the '<em>Project</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROJECT_FEATURE_COUNT = 10;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.CommitterShipImpl <em>Committer Ship</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.CommitterShipImpl
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectPackageImpl#getCommitterShip()
   * @generated
   */
  int COMMITTER_SHIP = 2;

  /**
   * The feature id for the '<em><b>Start</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMMITTER_SHIP__START = 0;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMMITTER_SHIP__END = 1;

  /**
   * The feature id for the '<em><b>Project</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMMITTER_SHIP__PROJECT = 2;

  /**
   * The feature id for the '<em><b>Person</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMMITTER_SHIP__PERSON = 3;

  /**
   * The number of structural features of the '<em>Committer Ship</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMMITTER_SHIP_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.PersonImpl <em>Person</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.PersonImpl
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectPackageImpl#getPerson()
   * @generated
   */
  int PERSON = 3;

  /**
   * The feature id for the '<em><b>Lastname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON__LASTNAME = 0;

  /**
   * The feature id for the '<em><b>Firstname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON__FIRSTNAME = 1;

  /**
   * The feature id for the '<em><b>Email</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON__EMAIL = 2;

  /**
   * The feature id for the '<em><b>Committerships</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON__COMMITTERSHIPS = 3;

  /**
   * The feature id for the '<em><b>Image</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON__IMAGE = 4;

  /**
   * The number of structural features of the '<em>Person</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_FEATURE_COUNT = 5;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Foundation <em>Foundation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Foundation</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Foundation
   * @generated
   */
  EClass getFoundation();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Foundation#getProjects <em>Projects</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Projects</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Foundation#getProjects()
   * @see #getFoundation()
   * @generated
   */
  EReference getFoundation_Projects();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Foundation#getPersons <em>Persons</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Persons</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Foundation#getPersons()
   * @see #getFoundation()
   * @generated
   */
  EReference getFoundation_Persons();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project <em>Project</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Project</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project
   * @generated
   */
  EClass getProject();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getShortname <em>Shortname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Shortname</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getShortname()
   * @see #getProject()
   * @generated
   */
  EAttribute getProject_Shortname();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getSubprojects <em>Subprojects</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Subprojects</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getSubprojects()
   * @see #getProject()
   * @generated
   */
  EReference getProject_Subprojects();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getCommitters <em>Committers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Committers</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getCommitters()
   * @see #getProject()
   * @generated
   */
  EReference getProject_Committers();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getParent <em>Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Parent</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getParent()
   * @see #getProject()
   * @generated
   */
  EReference getProject_Parent();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getProjectleads <em>Projectleads</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Projectleads</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getProjectleads()
   * @see #getProject()
   * @generated
   */
  EReference getProject_Projectleads();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getStart()
   * @see #getProject()
   * @generated
   */
  EAttribute getProject_Start();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getEnd()
   * @see #getProject()
   * @generated
   */
  EAttribute getProject_End();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getLongname <em>Longname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Longname</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getLongname()
   * @see #getProject()
   * @generated
   */
  EAttribute getProject_Longname();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getDevmail <em>Devmail</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Devmail</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getDevmail()
   * @see #getProject()
   * @generated
   */
  EAttribute getProject_Devmail();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Project#getHomepage <em>Homepage</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Homepage</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Project#getHomepage()
   * @see #getProject()
   * @generated
   */
  EAttribute getProject_Homepage();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip <em>Committer Ship</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Committer Ship</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip
   * @generated
   */
  EClass getCommitterShip();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getStart()
   * @see #getCommitterShip()
   * @generated
   */
  EAttribute getCommitterShip_Start();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getEnd()
   * @see #getCommitterShip()
   * @generated
   */
  EAttribute getCommitterShip_End();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getProject <em>Project</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Project</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getProject()
   * @see #getCommitterShip()
   * @generated
   */
  EReference getCommitterShip_Project();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getPerson <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Person</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip#getPerson()
   * @see #getCommitterShip()
   * @generated
   */
  EReference getCommitterShip_Person();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Person</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Person
   * @generated
   */
  EClass getPerson();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getLastname <em>Lastname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Lastname</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Person#getLastname()
   * @see #getPerson()
   * @generated
   */
  EAttribute getPerson_Lastname();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getFirstname <em>Firstname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Firstname</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Person#getFirstname()
   * @see #getPerson()
   * @generated
   */
  EAttribute getPerson_Firstname();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getEmail <em>Email</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Email</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Person#getEmail()
   * @see #getPerson()
   * @generated
   */
  EAttribute getPerson_Email();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getCommitterships <em>Committerships</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Committerships</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Person#getCommitterships()
   * @see #getPerson()
   * @generated
   */
  EReference getPerson_Committerships();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.databinding.project.core.model.project.Person#getImage <em>Image</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Image</em>'.
   * @see org.eclipse.emf.examples.databinding.project.core.model.project.Person#getImage()
   * @see #getPerson()
   * @generated
   */
  EAttribute getPerson_Image();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ProjectFactory getProjectFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.FoundationImpl <em>Foundation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.FoundationImpl
     * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectPackageImpl#getFoundation()
     * @generated
     */
    EClass FOUNDATION = eINSTANCE.getFoundation();

    /**
     * The meta object literal for the '<em><b>Projects</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOUNDATION__PROJECTS = eINSTANCE.getFoundation_Projects();

    /**
     * The meta object literal for the '<em><b>Persons</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOUNDATION__PERSONS = eINSTANCE.getFoundation_Persons();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl <em>Project</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl
     * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectPackageImpl#getProject()
     * @generated
     */
    EClass PROJECT = eINSTANCE.getProject();

    /**
     * The meta object literal for the '<em><b>Shortname</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROJECT__SHORTNAME = eINSTANCE.getProject_Shortname();

    /**
     * The meta object literal for the '<em><b>Subprojects</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROJECT__SUBPROJECTS = eINSTANCE.getProject_Subprojects();

    /**
     * The meta object literal for the '<em><b>Committers</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROJECT__COMMITTERS = eINSTANCE.getProject_Committers();

    /**
     * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROJECT__PARENT = eINSTANCE.getProject_Parent();

    /**
     * The meta object literal for the '<em><b>Projectleads</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROJECT__PROJECTLEADS = eINSTANCE.getProject_Projectleads();

    /**
     * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROJECT__START = eINSTANCE.getProject_Start();

    /**
     * The meta object literal for the '<em><b>End</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROJECT__END = eINSTANCE.getProject_End();

    /**
     * The meta object literal for the '<em><b>Longname</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROJECT__LONGNAME = eINSTANCE.getProject_Longname();

    /**
     * The meta object literal for the '<em><b>Devmail</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROJECT__DEVMAIL = eINSTANCE.getProject_Devmail();

    /**
     * The meta object literal for the '<em><b>Homepage</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROJECT__HOMEPAGE = eINSTANCE.getProject_Homepage();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.CommitterShipImpl <em>Committer Ship</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.CommitterShipImpl
     * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectPackageImpl#getCommitterShip()
     * @generated
     */
    EClass COMMITTER_SHIP = eINSTANCE.getCommitterShip();

    /**
     * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COMMITTER_SHIP__START = eINSTANCE.getCommitterShip_Start();

    /**
     * The meta object literal for the '<em><b>End</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COMMITTER_SHIP__END = eINSTANCE.getCommitterShip_End();

    /**
     * The meta object literal for the '<em><b>Project</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMMITTER_SHIP__PROJECT = eINSTANCE.getCommitterShip_Project();

    /**
     * The meta object literal for the '<em><b>Person</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMMITTER_SHIP__PERSON = eINSTANCE.getCommitterShip_Person();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.PersonImpl <em>Person</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.PersonImpl
     * @see org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectPackageImpl#getPerson()
     * @generated
     */
    EClass PERSON = eINSTANCE.getPerson();

    /**
     * The meta object literal for the '<em><b>Lastname</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PERSON__LASTNAME = eINSTANCE.getPerson_Lastname();

    /**
     * The meta object literal for the '<em><b>Firstname</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PERSON__FIRSTNAME = eINSTANCE.getPerson_Firstname();

    /**
     * The meta object literal for the '<em><b>Email</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PERSON__EMAIL = eINSTANCE.getPerson_Email();

    /**
     * The meta object literal for the '<em><b>Committerships</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PERSON__COMMITTERSHIPS = eINSTANCE.getPerson_Committerships();

    /**
     * The meta object literal for the '<em><b>Image</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PERSON__IMAGE = eINSTANCE.getPerson_Image();

  }

} //ProjectPackage
