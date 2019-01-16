/**
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   Thales - initial API and implementation
 */
package org.eclipse.emf.test.core.xrefsopposite.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.test.core.xrefsopposite.APkg;
import org.eclipse.emf.test.core.xrefsopposite.AbstractA;
import org.eclipse.emf.test.core.xrefsopposite.XRefsOppositeFactory;
import org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XRefsOppositePackageImpl extends EPackageImpl implements XRefsOppositePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass aEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass aPkgEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass abstractAEClass = null;

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
   * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private XRefsOppositePackageImpl()
  {
    super(eNS_URI, XRefsOppositeFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link XRefsOppositePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static XRefsOppositePackage init()
  {
    if (isInited) return (XRefsOppositePackage)EPackage.Registry.INSTANCE.getEPackage(XRefsOppositePackage.eNS_URI);

    // Obtain or create and register package
    Object registeredXRefsOppositePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    XRefsOppositePackageImpl theXRefsOppositePackage = registeredXRefsOppositePackage instanceof XRefsOppositePackageImpl ? (XRefsOppositePackageImpl)registeredXRefsOppositePackage : new XRefsOppositePackageImpl();

    isInited = true;

    // Create package meta-data objects
    theXRefsOppositePackage.createPackageContents();

    // Initialize created meta-data
    theXRefsOppositePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theXRefsOppositePackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(XRefsOppositePackage.eNS_URI, theXRefsOppositePackage);
    return theXRefsOppositePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getA()
  {
    return aEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getA_Name()
  {
    return (EAttribute)aEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getA_Ref1()
  {
    return (EReference)aEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getA_Oref1()
  {
    return (EReference)aEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getA_Ref2()
  {
    return (EReference)aEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getA_Oref2()
  {
    return (EReference)aEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getA_Ref3()
  {
    return (EReference)aEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getA_OwningAPkg()
  {
    return (EReference)aEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAPkg()
  {
    return aPkgEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAPkg_OwnedAs()
  {
    return (EReference)aPkgEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAbstractA()
  {
    return abstractAEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAbstractA_Id()
  {
    return (EAttribute)abstractAEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XRefsOppositeFactory getXRefsOppositeFactory()
  {
    return (XRefsOppositeFactory)getEFactoryInstance();
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
    aEClass = createEClass(A);
    createEAttribute(aEClass, A__NAME);
    createEReference(aEClass, A__REF1);
    createEReference(aEClass, A__OREF1);
    createEReference(aEClass, A__REF2);
    createEReference(aEClass, A__OREF2);
    createEReference(aEClass, A__REF3);
    createEReference(aEClass, A__OWNING_APKG);

    aPkgEClass = createEClass(APKG);
    createEReference(aPkgEClass, APKG__OWNED_AS);

    abstractAEClass = createEClass(ABSTRACT_A);
    createEAttribute(abstractAEClass, ABSTRACT_A__ID);
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
    aEClass.getESuperTypes().add(this.getAbstractA());
    aPkgEClass.getESuperTypes().add(this.getAbstractA());

    // Initialize classes, features, and operations; add parameters
    initEClass(aEClass, org.eclipse.emf.test.core.xrefsopposite.A.class, "A", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getA_Name(), ecorePackage.getEString(), "name", null, 1, 1, org.eclipse.emf.test.core.xrefsopposite.A.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getA_Ref1(), this.getA(), this.getA_Oref1(), "ref1", null, 0, -1, org.eclipse.emf.test.core.xrefsopposite.A.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getA_Oref1(), this.getA(), this.getA_Ref1(), "oref1", null, 0, -1, org.eclipse.emf.test.core.xrefsopposite.A.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getA_Ref2(), this.getA(), this.getA_Oref2(), "ref2", null, 0, -1, org.eclipse.emf.test.core.xrefsopposite.A.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getA_Oref2(), this.getA(), this.getA_Ref2(), "oref2", null, 0, -1, org.eclipse.emf.test.core.xrefsopposite.A.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getA_Ref3(), this.getA(), null, "ref3", null, 0, -1, org.eclipse.emf.test.core.xrefsopposite.A.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getA_OwningAPkg(), this.getAPkg(), this.getAPkg_OwnedAs(), "owningAPkg", null, 0, 1, org.eclipse.emf.test.core.xrefsopposite.A.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(aPkgEClass, APkg.class, "APkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAPkg_OwnedAs(), this.getA(), this.getA_OwningAPkg(), "ownedAs", null, 0, -1, APkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(abstractAEClass, AbstractA.class, "AbstractA", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAbstractA_Id(), ecorePackage.getEString(), "id", null, 1, 1, AbstractA.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //XRefsOppositePackageImpl
