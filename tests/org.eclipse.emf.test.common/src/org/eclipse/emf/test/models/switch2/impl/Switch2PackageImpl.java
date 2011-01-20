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
 * $Id: Switch2PackageImpl.java,v 1.1 2011/01/20 01:10:23 emerks Exp $
 */
package org.eclipse.emf.test.models.switch2.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.test.models.switch1.Switch1Package;

import org.eclipse.emf.test.models.switch1.impl.Switch1PackageImpl;

import org.eclipse.emf.test.models.switch2.EClass2;
import org.eclipse.emf.test.models.switch2.EClass3;
import org.eclipse.emf.test.models.switch2.Switch2Factory;
import org.eclipse.emf.test.models.switch2.Switch2Package;

import org.eclipse.emf.test.models.switch3.Switch3Package;

import org.eclipse.emf.test.models.switch3.impl.Switch3PackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Switch2PackageImpl extends EPackageImpl implements Switch2Package
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eClass2EClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eClass3EClass = null;

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
   * @see org.eclipse.emf.test.models.switch2.Switch2Package#eNS_URI
   * @see #init()
   * @generated
   */
  private Switch2PackageImpl()
  {
    super(eNS_URI, Switch2Factory.eINSTANCE);
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
   * <p>This method is used to initialize {@link Switch2Package#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static Switch2Package init()
  {
    if (isInited) return (Switch2Package)EPackage.Registry.INSTANCE.getEPackage(Switch2Package.eNS_URI);

    // Obtain or create and register package
    Switch2PackageImpl theSwitch2Package = (Switch2PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Switch2PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Switch2PackageImpl());

    isInited = true;

    // Obtain or create and register interdependencies
    Switch1PackageImpl theSwitch1Package = (Switch1PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Switch1Package.eNS_URI) instanceof Switch1PackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Switch1Package.eNS_URI) : Switch1Package.eINSTANCE);
    Switch3PackageImpl theSwitch3Package = (Switch3PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Switch3Package.eNS_URI) instanceof Switch3PackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Switch3Package.eNS_URI) : Switch3Package.eINSTANCE);

    // Create package meta-data objects
    theSwitch2Package.createPackageContents();
    theSwitch1Package.createPackageContents();
    theSwitch3Package.createPackageContents();

    // Initialize created meta-data
    theSwitch2Package.initializePackageContents();
    theSwitch1Package.initializePackageContents();
    theSwitch3Package.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theSwitch2Package.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(Switch2Package.eNS_URI, theSwitch2Package);
    return theSwitch2Package;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEClass2()
  {
    return eClass2EClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass2_EAttribute4()
  {
    return (EAttribute)eClass2EClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass2_EAttribute5()
  {
    return (EAttribute)eClass2EClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEClass3()
  {
    return eClass3EClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass3_EAttribute6()
  {
    return (EAttribute)eClass3EClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass3_EAttribute7()
  {
    return (EAttribute)eClass3EClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Switch2Factory getSwitch2Factory()
  {
    return (Switch2Factory)getEFactoryInstance();
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
    eClass2EClass = createEClass(ECLASS2);
    createEAttribute(eClass2EClass, ECLASS2__EATTRIBUTE4);
    createEAttribute(eClass2EClass, ECLASS2__EATTRIBUTE5);

    eClass3EClass = createEClass(ECLASS3);
    createEAttribute(eClass3EClass, ECLASS3__EATTRIBUTE6);
    createEAttribute(eClass3EClass, ECLASS3__EATTRIBUTE7);
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
    Switch1Package theSwitch1Package = (Switch1Package)EPackage.Registry.INSTANCE.getEPackage(Switch1Package.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    eClass2EClass.getESuperTypes().add(theSwitch1Package.getEClass1());
    eClass3EClass.getESuperTypes().add(this.getEClass2());

    // Initialize classes and features; add operations and parameters
    initEClass(eClass2EClass, EClass2.class, "EClass2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEClass2_EAttribute4(), ecorePackage.getEString(), "EAttribute4", null, 0, 1, EClass2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEClass2_EAttribute5(), ecorePackage.getEString(), "EAttribute5", null, 0, 1, EClass2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eClass3EClass, EClass3.class, "EClass3", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEClass3_EAttribute6(), ecorePackage.getEString(), "EAttribute6", null, 0, 1, EClass3.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEClass3_EAttribute7(), ecorePackage.getEString(), "EAttribute7", null, 0, 1, EClass3.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //Switch2PackageImpl
