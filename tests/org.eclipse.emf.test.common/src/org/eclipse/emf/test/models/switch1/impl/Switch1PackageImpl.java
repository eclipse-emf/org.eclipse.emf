/**
 * Copyright (c) 2009 TIBCO Software Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Adrian Price
 */
package org.eclipse.emf.test.models.switch1.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.test.models.switch1.EClass0;
import org.eclipse.emf.test.models.switch1.EClass1;
import org.eclipse.emf.test.models.switch1.Switch1Factory;
import org.eclipse.emf.test.models.switch1.Switch1Package;

import org.eclipse.emf.test.models.switch2.Switch2Package;

import org.eclipse.emf.test.models.switch2.impl.Switch2PackageImpl;

import org.eclipse.emf.test.models.switch3.Switch3Package;

import org.eclipse.emf.test.models.switch3.impl.Switch3PackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Switch1PackageImpl extends EPackageImpl implements Switch1Package
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eClass0EClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eClass1EClass = null;

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
   * @see org.eclipse.emf.test.models.switch1.Switch1Package#eNS_URI
   * @see #init()
   * @generated
   */
  private Switch1PackageImpl()
  {
    super(eNS_URI, Switch1Factory.eINSTANCE);
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
   * <p>This method is used to initialize {@link Switch1Package#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static Switch1Package init()
  {
    if (isInited) return (Switch1Package)EPackage.Registry.INSTANCE.getEPackage(Switch1Package.eNS_URI);

    // Obtain or create and register package
    Switch1PackageImpl theSwitch1Package = (Switch1PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Switch1PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Switch1PackageImpl());

    isInited = true;

    // Obtain or create and register interdependencies
    Switch2PackageImpl theSwitch2Package = (Switch2PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Switch2Package.eNS_URI) instanceof Switch2PackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Switch2Package.eNS_URI) : Switch2Package.eINSTANCE);
    Switch3PackageImpl theSwitch3Package = (Switch3PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Switch3Package.eNS_URI) instanceof Switch3PackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Switch3Package.eNS_URI) : Switch3Package.eINSTANCE);

    // Create package meta-data objects
    theSwitch1Package.createPackageContents();
    theSwitch2Package.createPackageContents();
    theSwitch3Package.createPackageContents();

    // Initialize created meta-data
    theSwitch1Package.initializePackageContents();
    theSwitch2Package.initializePackageContents();
    theSwitch3Package.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theSwitch1Package.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(Switch1Package.eNS_URI, theSwitch1Package);
    return theSwitch1Package;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEClass0()
  {
    return eClass0EClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass0_EAttribute0()
  {
    return (EAttribute)eClass0EClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass0_EAttribute1()
  {
    return (EAttribute)eClass0EClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEClass1()
  {
    return eClass1EClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass1_EAttribute2()
  {
    return (EAttribute)eClass1EClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEClass1_EAttribute3()
  {
    return (EAttribute)eClass1EClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Switch1Factory getSwitch1Factory()
  {
    return (Switch1Factory)getEFactoryInstance();
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
    eClass0EClass = createEClass(ECLASS0);
    createEAttribute(eClass0EClass, ECLASS0__EATTRIBUTE0);
    createEAttribute(eClass0EClass, ECLASS0__EATTRIBUTE1);

    eClass1EClass = createEClass(ECLASS1);
    createEAttribute(eClass1EClass, ECLASS1__EATTRIBUTE2);
    createEAttribute(eClass1EClass, ECLASS1__EATTRIBUTE3);
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

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    eClass1EClass.getESuperTypes().add(this.getEClass0());

    // Initialize classes and features; add operations and parameters
    initEClass(eClass0EClass, EClass0.class, "EClass0", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEClass0_EAttribute0(), ecorePackage.getEString(), "EAttribute0", null, 0, 1, EClass0.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEClass0_EAttribute1(), ecorePackage.getEString(), "EAttribute1", null, 0, 1, EClass0.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eClass1EClass, EClass1.class, "EClass1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEClass1_EAttribute2(), ecorePackage.getEString(), "EAttribute2", null, 0, 1, EClass1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEClass1_EAttribute3(), ecorePackage.getEString(), "EAttribute3", null, 0, 1, EClass1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //Switch1PackageImpl
