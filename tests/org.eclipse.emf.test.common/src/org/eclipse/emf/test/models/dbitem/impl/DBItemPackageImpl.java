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
 * $Id: DBItemPackageImpl.java,v 1.4 2009/05/12 15:54:44 davidms Exp $
 */
package org.eclipse.emf.test.models.dbitem.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.test.models.dbitem.DBItemFactory;
import org.eclipse.emf.test.models.dbitem.DBItemPackage;
import org.eclipse.emf.test.models.dbitem.DbType;
import org.eclipse.emf.test.models.dbitem.DocumentRoot;

import org.eclipse.emf.test.models.dbprice.DBPricePackage;

import org.eclipse.emf.test.models.dbprice.impl.DBPricePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DBItemPackageImpl extends EPackageImpl implements DBItemPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dbTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass documentRootEClass = null;

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
   * @see org.eclipse.emf.test.models.dbitem.DBItemPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private DBItemPackageImpl()
  {
    super(eNS_URI, DBItemFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link DBItemPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static DBItemPackage init()
  {
    if (isInited) return (DBItemPackage)EPackage.Registry.INSTANCE.getEPackage(DBItemPackage.eNS_URI);

    // Obtain or create and register package
    DBItemPackageImpl theDBItemPackage = (DBItemPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DBItemPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DBItemPackageImpl());

    isInited = true;

    // Obtain or create and register interdependencies
    DBPricePackageImpl theDBPricePackage = (DBPricePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DBPricePackage.eNS_URI) instanceof DBPricePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DBPricePackage.eNS_URI) : DBPricePackage.eINSTANCE);

    // Create package meta-data objects
    theDBItemPackage.createPackageContents();
    theDBPricePackage.createPackageContents();

    // Initialize created meta-data
    theDBItemPackage.initializePackageContents();
    theDBPricePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theDBItemPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(DBItemPackage.eNS_URI, theDBItemPackage);
    return theDBItemPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDbType()
  {
    return dbTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDbType_RedPen()
  {
    return (EReference)dbTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDbType_BluePen()
  {
    return (EReference)dbTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDbType_RedPencil()
  {
    return (EReference)dbTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDbType_BluePencil()
  {
    return (EReference)dbTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDocumentRoot()
  {
    return documentRootEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_Mixed()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_XMLNSPrefixMap()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_XSISchemaLocation()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_Dbitem()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DBItemFactory getDBItemFactory()
  {
    return (DBItemFactory)getEFactoryInstance();
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
    dbTypeEClass = createEClass(DB_TYPE);
    createEReference(dbTypeEClass, DB_TYPE__RED_PEN);
    createEReference(dbTypeEClass, DB_TYPE__BLUE_PEN);
    createEReference(dbTypeEClass, DB_TYPE__RED_PENCIL);
    createEReference(dbTypeEClass, DB_TYPE__BLUE_PENCIL);

    documentRootEClass = createEClass(DOCUMENT_ROOT);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    createEReference(documentRootEClass, DOCUMENT_ROOT__DBITEM);
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
    DBPricePackage theDBPricePackage = (DBPricePackage)EPackage.Registry.INSTANCE.getEPackage(DBPricePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(dbTypeEClass, DbType.class, "DbType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDbType_RedPen(), theDBPricePackage.getPenType(), null, "redPen", null, 1, 1, DbType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDbType_BluePen(), theDBPricePackage.getPenType(), null, "bluePen", null, 1, 1, DbType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDbType_RedPencil(), theDBPricePackage.getPencilType(), null, "redPencil", null, 1, 1, DbType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDbType_BluePencil(), theDBPricePackage.getPencilType(), null, "bluePencil", null, 1, 1, DbType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_Dbitem(), this.getDbType(), null, "dbitem", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
    createExtendedMetaDataAnnotations();
  }

  /**
   * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createExtendedMetaDataAnnotations()
  {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
    addAnnotation
      (dbTypeEClass, 
       source, 
       new String[] 
       {
       "name", "dbType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getDbType_RedPen(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "red_pen",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDbType_BluePen(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "blue_pen"
       });		
    addAnnotation
      (getDbType_RedPencil(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "red_pencil",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDbType_BluePencil(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "blue_pencil"
       });		
    addAnnotation
      (documentRootEClass, 
       source, 
       new String[] 
       {
       "name", "",
       "kind", "mixed"
       });		
    addAnnotation
      (getDocumentRoot_Mixed(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "name", ":mixed"
       });		
    addAnnotation
      (getDocumentRoot_XMLNSPrefixMap(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "xmlns:prefix"
       });		
    addAnnotation
      (getDocumentRoot_XSISchemaLocation(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "xsi:schemaLocation"
       });		
    addAnnotation
      (getDocumentRoot_Dbitem(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "dbitem",
       "namespace", "##targetNamespace"
       });
  }

} //DBItemPackageImpl
