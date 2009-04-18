/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: Ecore2XMLPackageImpl.java,v 1.6 2009/04/18 11:46:04 emerks Exp $
 */
package org.eclipse.emf.mapping.ecore2xml.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLFactory;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.emf.mapping.ecore2xml.XMLInfo;
import org.eclipse.emf.mapping.ecore2xml.XMLMap;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ecore2XMLPackageImpl extends EPackageImpl implements Ecore2XMLPackage
{
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xmlInfoEClass = null;
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xmlMapEClass = null;
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eNamedElementToXMLInfoMapEntryEClass = null;
  
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
   * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private Ecore2XMLPackageImpl()
  {
    super(eNS_URI, Ecore2XMLFactory.eINSTANCE);
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
  public static Ecore2XMLPackage init()
  {
    if (isInited) return (Ecore2XMLPackage)EPackage.Registry.INSTANCE.getEPackage(Ecore2XMLPackage.eNS_URI);

    // Obtain or create and register package
    Ecore2XMLPackageImpl theEcore2XMLPackage = (Ecore2XMLPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Ecore2XMLPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Ecore2XMLPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theEcore2XMLPackage.createPackageContents();

    // Initialize created meta-data
    theEcore2XMLPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theEcore2XMLPackage.freeze();

    return theEcore2XMLPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXMLInfo()
  {
    return xmlInfoEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXMLInfo_Name()
  {
    return (EAttribute)xmlInfoEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXMLInfo_TargetNamespace()
  {
    return (EAttribute)xmlInfoEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXMLInfo_XMLRepresentation()
  {
    return (EAttribute)xmlInfoEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXMLMap()
  {
    return xmlMapEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXMLMap_IDAttributeName()
  {
    return (EAttribute)xmlMapEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXMLMap_EcoreToXMLInfo()
  {
    return (EReference)xmlMapEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXMLMap_NoNamespacePackage()
  {
    return (EReference)xmlMapEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getENamedElementToXMLInfoMapEntry()
  {
    return eNamedElementToXMLInfoMapEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getENamedElementToXMLInfoMapEntry_Key()
  {
    return (EReference)eNamedElementToXMLInfoMapEntryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getENamedElementToXMLInfoMapEntry_Value()
  {
    return (EReference)eNamedElementToXMLInfoMapEntryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Ecore2XMLFactory getEcore2XMLFactory()
  {
    return (Ecore2XMLFactory)getEFactoryInstance();
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
    xmlInfoEClass = createEClass(XML_INFO);
    createEAttribute(xmlInfoEClass, XML_INFO__NAME);
    createEAttribute(xmlInfoEClass, XML_INFO__TARGET_NAMESPACE);
    createEAttribute(xmlInfoEClass, XML_INFO__XML_REPRESENTATION);

    xmlMapEClass = createEClass(XML_MAP);
    createEAttribute(xmlMapEClass, XML_MAP__ID_ATTRIBUTE_NAME);
    createEReference(xmlMapEClass, XML_MAP__ECORE_TO_XML_INFO);
    createEReference(xmlMapEClass, XML_MAP__NO_NAMESPACE_PACKAGE);

    eNamedElementToXMLInfoMapEntryEClass = createEClass(ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY);
    createEReference(eNamedElementToXMLInfoMapEntryEClass, ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__KEY);
    createEReference(eNamedElementToXMLInfoMapEntryEClass, ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__VALUE);
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
    EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(xmlInfoEClass, XMLInfo.class, "XMLInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getXMLInfo_Name(), theEcorePackage.getEString(), "name", null, 0, 1, XMLInfo.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getXMLInfo_TargetNamespace(), theEcorePackage.getEString(), "targetNamespace", null, 0, 1, XMLInfo.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getXMLInfo_XMLRepresentation(), theEcorePackage.getEInt(), "xMLRepresentation", "-1", 0, 1, XMLInfo.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

    initEClass(xmlMapEClass, XMLMap.class, "XMLMap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getXMLMap_IDAttributeName(), theEcorePackage.getEString(), "iDAttributeName", null, 0, 1, XMLMap.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getXMLMap_EcoreToXMLInfo(), this.getENamedElementToXMLInfoMapEntry(), null, "ecoreToXMLInfo", null, 0, -1, XMLMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getXMLMap_NoNamespacePackage(), theEcorePackage.getEPackage(), null, "noNamespacePackage", null, 0, 1, XMLMap.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(eNamedElementToXMLInfoMapEntryEClass, Map.Entry.class, "ENamedElementToXMLInfoMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getENamedElementToXMLInfoMapEntry_Key(), theEcorePackage.getENamedElement(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getENamedElementToXMLInfoMapEntry_Value(), this.getXMLInfo(), null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Create resource
    createResource(eNS_URI);
  }

} //Ecore2XMLPackageImpl
