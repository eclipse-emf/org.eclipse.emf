/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.ext.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.test.models.ext.ExtE;
import org.eclipse.emf.test.models.ext.ExtFactory;
import org.eclipse.emf.test.models.ext.ExtPackage;

import org.eclipse.emf.test.models.ref.RefPackage;

import org.eclipse.emf.test.models.ref.impl.RefPackageImpl;

import org.eclipse.emf.test.models.ref.unsettable.URefPackage;

import org.eclipse.emf.test.models.ref.unsettable.impl.URefPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtPackageImpl extends EPackageImpl implements ExtPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extEEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fEClass = null;

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
   * @see org.eclipse.emf.test.models.ext.ExtPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ExtPackageImpl()
  {
    super(eNS_URI, ExtFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link ExtPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ExtPackage init()
  {
    if (isInited) return (ExtPackage)EPackage.Registry.INSTANCE.getEPackage(ExtPackage.eNS_URI);

    // Obtain or create and register package
    ExtPackageImpl theExtPackage = (ExtPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExtPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExtPackageImpl());

    isInited = true;

    // Obtain or create and register interdependencies
    RefPackageImpl theRefPackage = (RefPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RefPackage.eNS_URI) instanceof RefPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RefPackage.eNS_URI) : RefPackage.eINSTANCE);
    URefPackageImpl theURefPackage = (URefPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(URefPackage.eNS_URI) instanceof URefPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(URefPackage.eNS_URI) : URefPackage.eINSTANCE);

    // Create package meta-data objects
    theExtPackage.createPackageContents();
    theRefPackage.createPackageContents();
    theURefPackage.createPackageContents();

    // Initialize created meta-data
    theExtPackage.initializePackageContents();
    theRefPackage.initializePackageContents();
    theURefPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theExtPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ExtPackage.eNS_URI, theExtPackage);
    return theExtPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtE()
  {
    return extEEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExtE_Value()
  {
    return (EAttribute)extEEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtE_F()
  {
    return (EReference)extEEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getF()
  {
    return fEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getF_Id()
  {
    return (EAttribute)fEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getF_E()
  {
    return (EReference)fEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtFactory getExtFactory()
  {
    return (ExtFactory)getEFactoryInstance();
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
    extEEClass = createEClass(EXT_E);
    createEAttribute(extEEClass, EXT_E__VALUE);
    createEReference(extEEClass, EXT_E__F);

    fEClass = createEClass(F);
    createEAttribute(fEClass, F__ID);
    createEReference(fEClass, F__E);
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
    RefPackage theRefPackage = (RefPackage)EPackage.Registry.INSTANCE.getEPackage(RefPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    extEEClass.getESuperTypes().add(theRefPackage.getE());

    // Initialize classes and features; add operations and parameters
    initEClass(extEEClass, ExtE.class, "ExtE", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExtE_Value(), ecorePackage.getEInt(), "value", null, 0, 1, ExtE.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getExtE_F(), this.getF(), this.getF_E(), "f", null, 0, -1, ExtE.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fEClass, org.eclipse.emf.test.models.ext.F.class, "F", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getF_Id(), ecorePackage.getEString(), "id", null, 0, 1, org.eclipse.emf.test.models.ext.F.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getF_E(), this.getExtE(), this.getExtE_F(), "e", null, 1, 1, org.eclipse.emf.test.models.ext.F.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //ExtPackageImpl
