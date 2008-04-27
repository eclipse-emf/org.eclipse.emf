/**
 * <copyright>
 * 
 * Copyright (c) 2004-2006 IBM Corporation and others.
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
 * $Id: Ecore2EcorePackageImpl.java,v 1.11 2008/04/27 20:53:00 davidms Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.mapping.MappingPackage;

import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreFactory;
import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreMappingRoot;
import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcorePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ecore2EcorePackageImpl extends EPackageImpl implements Ecore2EcorePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ecore2EcoreMappingRootEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.emf.mapping.ecore2ecore.Ecore2EcorePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private Ecore2EcorePackageImpl()
  {
    super(eNS_URI, Ecore2EcoreFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this
   * model, and for any others upon which it depends.  Simple
   * dependencies are satisfied by calling this method on all
   * dependent packages before doing anything else.  This method drives
   * initialization for interdependent packages directly, in parallel
   * with this package, itself.
   * <p>Of this package and its interdependencies, all packages which
   * have not yet been registered by their URI values are first created
   * and registered.  The packages are then initialized in two steps:
   * meta-model objects for all of the packages are created before any
   * are initialized, since one package's meta-model objects may refer to
   * those of another.
   * <p>Invocation of this method will not affect any packages that have
   * already been initialized.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static Ecore2EcorePackage init()
  {
    if (isInited) return (Ecore2EcorePackage)EPackage.Registry.INSTANCE.getEPackage(Ecore2EcorePackage.eNS_URI);

    // Obtain or create and register package
    Ecore2EcorePackageImpl theEcore2EcorePackage = (Ecore2EcorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof Ecore2EcorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new Ecore2EcorePackageImpl());

    isInited = true;

    // Initialize simple dependencies
    MappingPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theEcore2EcorePackage.createPackageContents();

    // Initialize created meta-data
    theEcore2EcorePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theEcore2EcorePackage.freeze();

    return theEcore2EcorePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEcore2EcoreMappingRoot()
  {
    return ecore2EcoreMappingRootEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Ecore2EcoreFactory getEcore2EcoreFactory()
  {
    return (Ecore2EcoreFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    ecore2EcoreMappingRootEClass = createEClass(ECORE2_ECORE_MAPPING_ROOT);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    MappingPackage theMappingPackage = (MappingPackage)EPackage.Registry.INSTANCE.getEPackage(MappingPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    ecore2EcoreMappingRootEClass.getESuperTypes().add(theMappingPackage.getMappingRoot());

    // Initialize classes and features; add operations and parameters
    initEClass(ecore2EcoreMappingRootEClass, Ecore2EcoreMappingRoot.class, "Ecore2EcoreMappingRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //Ecore2EcorePackageImpl
