/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: URefPackageImpl.java,v 1.4 2009/05/12 15:54:44 davidms Exp $
 */
package org.eclipse.emf.test.models.ref.unsettable.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.test.models.ext.ExtPackage;

import org.eclipse.emf.test.models.ext.impl.ExtPackageImpl;

import org.eclipse.emf.test.models.ref.RefPackage;

import org.eclipse.emf.test.models.ref.impl.RefPackageImpl;

import org.eclipse.emf.test.models.ref.unsettable.URefFactory;
import org.eclipse.emf.test.models.ref.unsettable.URefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class URefPackageImpl extends EPackageImpl implements URefPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass c1UEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass c2UEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass auEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass buEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass cuEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass duEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass c4UEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass c3UEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass euEClass = null;

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
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private URefPackageImpl()
  {
    super(eNS_URI, URefFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link URefPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static URefPackage init()
  {
    if (isInited) return (URefPackage)EPackage.Registry.INSTANCE.getEPackage(URefPackage.eNS_URI);

    // Obtain or create and register package
    URefPackageImpl theURefPackage = (URefPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof URefPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new URefPackageImpl());

    isInited = true;

    // Obtain or create and register interdependencies
    RefPackageImpl theRefPackage = (RefPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RefPackage.eNS_URI) instanceof RefPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RefPackage.eNS_URI) : RefPackage.eINSTANCE);
    ExtPackageImpl theExtPackage = (ExtPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExtPackage.eNS_URI) instanceof ExtPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExtPackage.eNS_URI) : ExtPackage.eINSTANCE);

    // Create package meta-data objects
    theURefPackage.createPackageContents();
    theRefPackage.createPackageContents();
    theExtPackage.createPackageContents();

    // Initialize created meta-data
    theURefPackage.initializePackageContents();
    theRefPackage.initializePackageContents();
    theExtPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theURefPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(URefPackage.eNS_URI, theURefPackage);
    return theURefPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getC1U()
  {
    return c1UEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getC1U_Au()
  {
    return (EReference)c1UEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getC1U_Bu()
  {
    return (EReference)c1UEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getC2U()
  {
    return c2UEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getC2U_Au()
  {
    return (EReference)c2UEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getC2U_Bu()
  {
    return (EReference)c2UEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAU()
  {
    return auEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAU_Bu()
  {
    return (EReference)auEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAU_C2u()
  {
    return (EReference)auEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAU_Cu()
  {
    return (EReference)auEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBU()
  {
    return buEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBU_Au()
  {
    return (EReference)buEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBU_C2u()
  {
    return (EReference)buEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBU_Du()
  {
    return (EReference)buEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCU()
  {
    return cuEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCU_Du()
  {
    return (EReference)cuEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCU_C4u()
  {
    return (EReference)cuEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDU()
  {
    return duEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDU_Cu()
  {
    return (EReference)duEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDU_C4u()
  {
    return (EReference)duEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDU_Eu()
  {
    return (EReference)duEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getC4U()
  {
    return c4UEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getC4U_Cu()
  {
    return (EReference)c4UEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getC4U_Du()
  {
    return (EReference)c4UEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getC3U()
  {
    return c3UEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getC3U_Cu()
  {
    return (EReference)c3UEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getC3U_Du()
  {
    return (EReference)c3UEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEU()
  {
    return euEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEU_Name()
  {
    return (EAttribute)euEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEU_Ids()
  {
    return (EAttribute)euEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEU_Labels()
  {
    return (EAttribute)euEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEU_Du()
  {
    return (EReference)euEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public URefFactory getURefFactory()
  {
    return (URefFactory)getEFactoryInstance();
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
    c1UEClass = createEClass(C1U);
    createEReference(c1UEClass, C1U__AU);
    createEReference(c1UEClass, C1U__BU);

    c2UEClass = createEClass(C2U);
    createEReference(c2UEClass, C2U__AU);
    createEReference(c2UEClass, C2U__BU);

    auEClass = createEClass(AU);
    createEReference(auEClass, AU__BU);
    createEReference(auEClass, AU__C2U);
    createEReference(auEClass, AU__CU);

    buEClass = createEClass(BU);
    createEReference(buEClass, BU__AU);
    createEReference(buEClass, BU__C2U);
    createEReference(buEClass, BU__DU);

    cuEClass = createEClass(CU);
    createEReference(cuEClass, CU__DU);
    createEReference(cuEClass, CU__C4U);

    duEClass = createEClass(DU);
    createEReference(duEClass, DU__CU);
    createEReference(duEClass, DU__C4U);
    createEReference(duEClass, DU__EU);

    c4UEClass = createEClass(C4U);
    createEReference(c4UEClass, C4U__CU);
    createEReference(c4UEClass, C4U__DU);

    c3UEClass = createEClass(C3U);
    createEReference(c3UEClass, C3U__CU);
    createEReference(c3UEClass, C3U__DU);

    euEClass = createEClass(EU);
    createEAttribute(euEClass, EU__NAME);
    createEAttribute(euEClass, EU__IDS);
    createEAttribute(euEClass, EU__LABELS);
    createEReference(euEClass, EU__DU);
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

    // Initialize classes and features; add operations and parameters
    initEClass(c1UEClass, org.eclipse.emf.test.models.ref.unsettable.C1U.class, "C1U", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getC1U_Au(), this.getAU(), null, "au", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.C1U.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getC1U_Bu(), this.getBU(), null, "bu", null, 0, -1, org.eclipse.emf.test.models.ref.unsettable.C1U.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(c2UEClass, org.eclipse.emf.test.models.ref.unsettable.C2U.class, "C2U", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getC2U_Au(), this.getAU(), this.getAU_C2u(), "au", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.C2U.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getC2U_Bu(), this.getBU(), this.getBU_C2u(), "bu", null, 0, -1, org.eclipse.emf.test.models.ref.unsettable.C2U.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(auEClass, org.eclipse.emf.test.models.ref.unsettable.AU.class, "AU", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAU_Bu(), this.getBU(), this.getBU_Au(), "bu", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.AU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAU_C2u(), this.getC2U(), this.getC2U_Au(), "c2u", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.AU.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAU_Cu(), this.getCU(), null, "cu", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.AU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(buEClass, org.eclipse.emf.test.models.ref.unsettable.BU.class, "BU", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBU_Au(), this.getAU(), this.getAU_Bu(), "au", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.BU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBU_C2u(), this.getC2U(), this.getC2U_Bu(), "c2u", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.BU.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBU_Du(), this.getDU(), null, "du", null, 0, -1, org.eclipse.emf.test.models.ref.unsettable.BU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(cuEClass, org.eclipse.emf.test.models.ref.unsettable.CU.class, "CU", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCU_Du(), this.getDU(), this.getDU_Cu(), "du", null, 0, -1, org.eclipse.emf.test.models.ref.unsettable.CU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCU_C4u(), this.getC4U(), this.getC4U_Cu(), "c4u", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.CU.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(duEClass, org.eclipse.emf.test.models.ref.unsettable.DU.class, "DU", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDU_Cu(), this.getCU(), this.getCU_Du(), "cu", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.DU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDU_C4u(), this.getC4U(), this.getC4U_Du(), "c4u", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.DU.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDU_Eu(), this.getEU(), this.getEU_Du(), "eu", null, 0, -1, org.eclipse.emf.test.models.ref.unsettable.DU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(c4UEClass, org.eclipse.emf.test.models.ref.unsettable.C4U.class, "C4U", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getC4U_Cu(), this.getCU(), this.getCU_C4u(), "cu", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.C4U.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getC4U_Du(), this.getDU(), this.getDU_C4u(), "du", null, 0, -1, org.eclipse.emf.test.models.ref.unsettable.C4U.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(c3UEClass, org.eclipse.emf.test.models.ref.unsettable.C3U.class, "C3U", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getC3U_Cu(), this.getCU(), null, "cu", null, 1, 1, org.eclipse.emf.test.models.ref.unsettable.C3U.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getC3U_Du(), this.getDU(), null, "du", null, 0, -1, org.eclipse.emf.test.models.ref.unsettable.C3U.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(euEClass, org.eclipse.emf.test.models.ref.unsettable.EU.class, "EU", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEU_Name(), ecorePackage.getEString(), "name", null, 0, 1, org.eclipse.emf.test.models.ref.unsettable.EU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEU_Ids(), ecorePackage.getEString(), "ids", null, 0, -1, org.eclipse.emf.test.models.ref.unsettable.EU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEU_Labels(), ecorePackage.getEString(), "labels", null, 0, -1, org.eclipse.emf.test.models.ref.unsettable.EU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEU_Du(), this.getDU(), this.getDU_Eu(), "du", null, 0, -1, org.eclipse.emf.test.models.ref.unsettable.EU.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
  }

} //URefPackageImpl
