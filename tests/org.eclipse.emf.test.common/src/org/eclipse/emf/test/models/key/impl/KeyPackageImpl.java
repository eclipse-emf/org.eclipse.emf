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
 * $Id: KeyPackageImpl.java,v 1.4 2009/05/12 15:54:44 davidms Exp $
 */
package org.eclipse.emf.test.models.key.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.test.models.key.Item;
import org.eclipse.emf.test.models.key.KeyFactory;
import org.eclipse.emf.test.models.key.KeyPackage;
import org.eclipse.emf.test.models.key.Root;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KeyPackageImpl extends EPackageImpl implements KeyPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass itemEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass rootEClass = null;

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
   * @see org.eclipse.emf.test.models.key.KeyPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private KeyPackageImpl()
  {
    super(eNS_URI, KeyFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link KeyPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static KeyPackage init()
  {
    if (isInited) return (KeyPackage)EPackage.Registry.INSTANCE.getEPackage(KeyPackage.eNS_URI);

    // Obtain or create and register package
    KeyPackageImpl theKeyPackage = (KeyPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof KeyPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new KeyPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theKeyPackage.createPackageContents();

    // Initialize created meta-data
    theKeyPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theKeyPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(KeyPackage.eNS_URI, theKeyPackage);
    return theKeyPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getItem()
  {
    return itemEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getItem_Name()
  {
    return (EAttribute)itemEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getItem_Signature()
  {
    return (EAttribute)itemEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getItem_RelatedItems()
  {
    return (EReference)itemEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRoot()
  {
    return rootEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRoot_Items()
  {
    return (EReference)rootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KeyFactory getKeyFactory()
  {
    return (KeyFactory)getEFactoryInstance();
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
    itemEClass = createEClass(ITEM);
    createEAttribute(itemEClass, ITEM__NAME);
    createEAttribute(itemEClass, ITEM__SIGNATURE);
    createEReference(itemEClass, ITEM__RELATED_ITEMS);

    rootEClass = createEClass(ROOT);
    createEReference(rootEClass, ROOT__ITEMS);
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
    initEClass(itemEClass, Item.class, "Item", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getItem_Name(), ecorePackage.getEString(), "name", null, 0, 1, Item.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getItem_Signature(), ecorePackage.getEString(), "signature", null, 0, -1, Item.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getItem_RelatedItems(), this.getItem(), null, "relatedItems", null, 0, -1, Item.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(rootEClass, Root.class, "Root", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRoot_Items(), this.getItem(), null, "items", null, 0, -1, Root.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    getRoot_Items().getEKeys().add(this.getItem_Name());
    getRoot_Items().getEKeys().add(this.getItem_Signature());

    // Create resource
    createResource(eNS_URI);
  }

} //KeyPackageImpl
