/**
 * <copyright>
 *
 * Copyright (c) 2009 TIBCO Software Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Adrian Price
 *
 * </copyright>
 *
 * $Id: Switch3PackageImpl.java,v 1.1 2011/01/20 01:10:22 emerks Exp $
 */
package org.eclipse.emf.test.models.switch3.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.test.models.switch1.Switch1Package;

import org.eclipse.emf.test.models.switch1.impl.Switch1PackageImpl;

import org.eclipse.emf.test.models.switch2.Switch2Package;

import org.eclipse.emf.test.models.switch2.impl.Switch2PackageImpl;

import org.eclipse.emf.test.models.switch3.EClass4;
import org.eclipse.emf.test.models.switch3.EClass5;
import org.eclipse.emf.test.models.switch3.Switch3Factory;
import org.eclipse.emf.test.models.switch3.Switch3Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Switch3PackageImpl extends EPackageImpl implements Switch3Package
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eClass4EClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eClass5EClass = null;

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
   * @see org.eclipse.emf.test.models.switch3.Switch3Package#eNS_URI
   * @see #init()
   * @generated
   */
  private Switch3PackageImpl()
  {
    super(eNS_URI, Switch3Factory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link Switch3Package#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static Switch3Package init()
  {
    if (isInited) return (Switch3Package)EPackage.Registry.INSTANCE.getEPackage(Switch3Package.eNS_URI);

    // Obtain or create and register package
    Switch3PackageImpl theSwitch3Package = (Switch3PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Switch3PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Switch3PackageImpl());

    isInited = true;

    // Obtain or create and register interdependencies
    Switch1PackageImpl theSwitch1Package = (Switch1PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Switch1Package.eNS_URI) instanceof Switch1PackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Switch1Package.eNS_URI) : Switch1Package.eINSTANCE);
    Switch2PackageImpl theSwitch2Package = (Switch2PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Switch2Package.eNS_URI) instanceof Switch2PackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Switch2Package.eNS_URI) : Switch2Package.eINSTANCE);

    // Create package meta-data objects
    theSwitch3Package.createPackageContents();
    theSwitch1Package.createPackageContents();
    theSwitch2Package.createPackageContents();

    // Initialize created meta-data
    theSwitch3Package.initializePackageContents();
    theSwitch1Package.initializePackageContents();
    theSwitch2Package.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theSwitch3Package.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(Switch3Package.eNS_URI, theSwitch3Package);
    return theSwitch3Package;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEClass4()
  {
    return eClass4EClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass4_EAttribute8()
  {
    return (EAttribute)eClass4EClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass4_EAttribute9()
  {
    return (EAttribute)eClass4EClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEClass5()
  {
    return eClass5EClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass5_EAttribute10()
  {
    return (EAttribute)eClass5EClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass5_EAttribute11()
  {
    return (EAttribute)eClass5EClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Switch3Factory getSwitch3Factory()
  {
    return (Switch3Factory)getEFactoryInstance();
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
    eClass4EClass = createEClass(ECLASS4);
    createEAttribute(eClass4EClass, ECLASS4__EATTRIBUTE8);
    createEAttribute(eClass4EClass, ECLASS4__EATTRIBUTE9);

    eClass5EClass = createEClass(ECLASS5);
    createEAttribute(eClass5EClass, ECLASS5__EATTRIBUTE10);
    createEAttribute(eClass5EClass, ECLASS5__EATTRIBUTE11);
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
    Switch2Package theSwitch2Package = (Switch2Package)EPackage.Registry.INSTANCE.getEPackage(Switch2Package.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    eClass4EClass.getESuperTypes().add(theSwitch2Package.getEClass3());
    eClass5EClass.getESuperTypes().add(this.getEClass4());

    // Initialize classes and features; add operations and parameters
    initEClass(eClass4EClass, EClass4.class, "EClass4", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEClass4_EAttribute8(), ecorePackage.getEString(), "EAttribute8", null, 0, 1, EClass4.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEClass4_EAttribute9(), ecorePackage.getEString(), "EAttribute9", null, 0, 1, EClass4.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eClass5EClass, EClass5.class, "EClass5", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEClass5_EAttribute10(), ecorePackage.getEString(), "EAttribute10", null, 0, 1, EClass5.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEClass5_EAttribute11(), ecorePackage.getEString(), "EAttribute11", null, 0, 1, EClass5.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //Switch3PackageImpl
