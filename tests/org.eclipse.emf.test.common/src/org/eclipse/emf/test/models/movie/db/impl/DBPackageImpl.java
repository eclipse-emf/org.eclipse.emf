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
 * $Id: DBPackageImpl.java,v 1.5 2009/04/18 11:46:39 emerks Exp $
 */
package org.eclipse.emf.test.models.movie.db.impl;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.customer.CustomerPackage;

import org.eclipse.emf.test.models.customer.impl.CustomerPackageImpl;

import org.eclipse.emf.test.models.movie.db.CriticsReviewType;
import org.eclipse.emf.test.models.movie.db.CustomerReviewType;
import org.eclipse.emf.test.models.movie.db.DBFactory;
import org.eclipse.emf.test.models.movie.db.DBPackage;
import org.eclipse.emf.test.models.movie.db.DocumentRoot;
import org.eclipse.emf.test.models.movie.db.GenreTypes;
import org.eclipse.emf.test.models.movie.db.MovieDBType;
import org.eclipse.emf.test.models.movie.db.MovieType;

import org.eclipse.emf.test.models.movie.db.util.DBValidator;

import org.eclipse.emf.test.models.order.OrderPackage;

import org.eclipse.emf.test.models.order.impl.OrderPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DBPackageImpl extends EPackageImpl implements DBPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass criticsReviewTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass customerReviewTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass documentRootEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass movieDBTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass movieTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum genreTypesEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType actorsListEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType genreTypesObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType ratingTypeEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType ratingTypeObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType ratingValuesEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType ratingValuesObjectEDataType = null;

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
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private DBPackageImpl()
  {
    super(eNS_URI, DBFactory.eINSTANCE);
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
  public static DBPackage init()
  {
    if (isInited) return (DBPackage)EPackage.Registry.INSTANCE.getEPackage(DBPackage.eNS_URI);

    // Obtain or create and register package
    DBPackageImpl theDBPackage = (DBPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DBPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DBPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Obtain or create and register interdependencies
    CustomerPackageImpl theCustomerPackage = (CustomerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CustomerPackage.eNS_URI) instanceof CustomerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CustomerPackage.eNS_URI) : CustomerPackage.eINSTANCE);
    OrderPackageImpl theOrderPackage = (OrderPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OrderPackage.eNS_URI) instanceof OrderPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OrderPackage.eNS_URI) : OrderPackage.eINSTANCE);

    // Create package meta-data objects
    theDBPackage.createPackageContents();
    theCustomerPackage.createPackageContents();
    theOrderPackage.createPackageContents();

    // Initialize created meta-data
    theDBPackage.initializePackageContents();
    theCustomerPackage.initializePackageContents();
    theOrderPackage.initializePackageContents();

    // Register package validator
    EValidator.Registry.INSTANCE.put
      (theDBPackage, 
       new EValidator.Descriptor()
       {
         public EValidator getEValidator()
         {
           return DBValidator.INSTANCE;
         }
       });

    // Mark meta-data to indicate it can't be changed
    theDBPackage.freeze();

    return theDBPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCriticsReviewType()
  {
    return criticsReviewTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCriticsReviewType_Rating()
  {
    return (EAttribute)criticsReviewTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCriticsReviewType_ReviewedBy()
  {
    return (EAttribute)criticsReviewTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCustomerReviewType()
  {
    return customerReviewTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCustomerReviewType_Comment()
  {
    return (EAttribute)customerReviewTypeEClass.getEStructuralFeatures().get(0);
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
  public EReference getDocumentRoot_CheckedOutBy()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_CriticsReview()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_CustomerReview()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_Language()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDocumentRoot_MovieDB()
  {
    return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDocumentRoot_SpecialFeatures()
  {
    return (EAttribute)documentRootEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMovieDBType()
  {
    return movieDBTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMovieDBType_MovieDBFeatureMap()
  {
    return (EAttribute)movieDBTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMovieDBType_Movie()
  {
    return (EReference)movieDBTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMovieDBType_Comment()
  {
    return (EAttribute)movieDBTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMovieType()
  {
    return movieTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMovieType_Title()
  {
    return (EAttribute)movieTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMovieType_Actors()
  {
    return (EAttribute)movieTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMovieType_Director()
  {
    return (EAttribute)movieTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMovieType_Genre()
  {
    return (EAttribute)movieTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMovieType_Summary()
  {
    return (EAttribute)movieTypeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMovieType_CriticsReviewGroup()
  {
    return (EAttribute)movieTypeEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMovieType_CriticsReview()
  {
    return (EReference)movieTypeEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMovieType_CheckedOutBy()
  {
    return (EReference)movieTypeEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMovieType_Any()
  {
    return (EAttribute)movieTypeEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMovieType_ID()
  {
    return (EAttribute)movieTypeEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getGenreTypes()
  {
    return genreTypesEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getActorsList()
  {
    return actorsListEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getGenreTypesObject()
  {
    return genreTypesObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getRatingType()
  {
    return ratingTypeEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getRatingTypeObject()
  {
    return ratingTypeObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getRatingValues()
  {
    return ratingValuesEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getRatingValuesObject()
  {
    return ratingValuesObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DBFactory getDBFactory()
  {
    return (DBFactory)getEFactoryInstance();
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
    criticsReviewTypeEClass = createEClass(CRITICS_REVIEW_TYPE);
    createEAttribute(criticsReviewTypeEClass, CRITICS_REVIEW_TYPE__RATING);
    createEAttribute(criticsReviewTypeEClass, CRITICS_REVIEW_TYPE__REVIEWED_BY);

    customerReviewTypeEClass = createEClass(CUSTOMER_REVIEW_TYPE);
    createEAttribute(customerReviewTypeEClass, CUSTOMER_REVIEW_TYPE__COMMENT);

    documentRootEClass = createEClass(DOCUMENT_ROOT);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    createEReference(documentRootEClass, DOCUMENT_ROOT__CHECKED_OUT_BY);
    createEReference(documentRootEClass, DOCUMENT_ROOT__CRITICS_REVIEW);
    createEReference(documentRootEClass, DOCUMENT_ROOT__CUSTOMER_REVIEW);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__LANGUAGE);
    createEReference(documentRootEClass, DOCUMENT_ROOT__MOVIE_DB);
    createEAttribute(documentRootEClass, DOCUMENT_ROOT__SPECIAL_FEATURES);

    movieDBTypeEClass = createEClass(MOVIE_DB_TYPE);
    createEAttribute(movieDBTypeEClass, MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP);
    createEReference(movieDBTypeEClass, MOVIE_DB_TYPE__MOVIE);
    createEAttribute(movieDBTypeEClass, MOVIE_DB_TYPE__COMMENT);

    movieTypeEClass = createEClass(MOVIE_TYPE);
    createEAttribute(movieTypeEClass, MOVIE_TYPE__TITLE);
    createEAttribute(movieTypeEClass, MOVIE_TYPE__ACTORS);
    createEAttribute(movieTypeEClass, MOVIE_TYPE__DIRECTOR);
    createEAttribute(movieTypeEClass, MOVIE_TYPE__GENRE);
    createEAttribute(movieTypeEClass, MOVIE_TYPE__SUMMARY);
    createEAttribute(movieTypeEClass, MOVIE_TYPE__CRITICS_REVIEW_GROUP);
    createEReference(movieTypeEClass, MOVIE_TYPE__CRITICS_REVIEW);
    createEReference(movieTypeEClass, MOVIE_TYPE__CHECKED_OUT_BY);
    createEAttribute(movieTypeEClass, MOVIE_TYPE__ANY);
    createEAttribute(movieTypeEClass, MOVIE_TYPE__ID);

    // Create enums
    genreTypesEEnum = createEEnum(GENRE_TYPES);

    // Create data types
    actorsListEDataType = createEDataType(ACTORS_LIST);
    genreTypesObjectEDataType = createEDataType(GENRE_TYPES_OBJECT);
    ratingTypeEDataType = createEDataType(RATING_TYPE);
    ratingTypeObjectEDataType = createEDataType(RATING_TYPE_OBJECT);
    ratingValuesEDataType = createEDataType(RATING_VALUES);
    ratingValuesObjectEDataType = createEDataType(RATING_VALUES_OBJECT);
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
    XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
    CustomerPackage theCustomerPackage = (CustomerPackage)EPackage.Registry.INSTANCE.getEPackage(CustomerPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    customerReviewTypeEClass.getESuperTypes().add(this.getCriticsReviewType());

    // Initialize classes and features; add operations and parameters
    initEClass(criticsReviewTypeEClass, CriticsReviewType.class, "CriticsReviewType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCriticsReviewType_Rating(), this.getRatingType(), "rating", null, 1, 1, CriticsReviewType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCriticsReviewType_ReviewedBy(), theXMLTypePackage.getString(), "reviewedBy", null, 1, 1, CriticsReviewType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(customerReviewTypeEClass, CustomerReviewType.class, "CustomerReviewType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCustomerReviewType_Comment(), theXMLTypePackage.getString(), "comment", null, 1, 1, CustomerReviewType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_CheckedOutBy(), theCustomerPackage.getCustomerType(), null, "checkedOutBy", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_CriticsReview(), this.getCriticsReviewType(), null, "criticsReview", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_CustomerReview(), this.getCustomerReviewType(), null, "customerReview", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_Language(), theXMLTypePackage.getString(), "language", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getDocumentRoot_MovieDB(), this.getMovieDBType(), null, "movieDB", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getDocumentRoot_SpecialFeatures(), theXMLTypePackage.getString(), "specialFeatures", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    initEClass(movieDBTypeEClass, MovieDBType.class, "MovieDBType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMovieDBType_MovieDBFeatureMap(), ecorePackage.getEFeatureMapEntry(), "movieDBFeatureMap", null, 0, -1, MovieDBType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMovieDBType_Movie(), this.getMovieType(), null, "movie", null, 1, -1, MovieDBType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEAttribute(getMovieDBType_Comment(), theXMLTypePackage.getString(), "comment", null, 0, 1, MovieDBType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    initEClass(movieTypeEClass, MovieType.class, "MovieType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMovieType_Title(), theXMLTypePackage.getString(), "title", null, 1, 1, MovieType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMovieType_Actors(), this.getActorsList(), "actors", null, 1, 1, MovieType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMovieType_Director(), theXMLTypePackage.getString(), "director", null, 1, 1, MovieType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMovieType_Genre(), this.getGenreTypes(), "genre", null, 1, 1, MovieType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMovieType_Summary(), theXMLTypePackage.getString(), "summary", null, 1, 1, MovieType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMovieType_CriticsReviewGroup(), ecorePackage.getEFeatureMapEntry(), "criticsReviewGroup", null, 0, -1, MovieType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMovieType_CriticsReview(), this.getCriticsReviewType(), null, "criticsReview", null, 0, -1, MovieType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getMovieType_CheckedOutBy(), theCustomerPackage.getCustomerType(), null, "checkedOutBy", null, 1, 1, MovieType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMovieType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, 1, MovieType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMovieType_ID(), theXMLTypePackage.getID(), "iD", null, 1, 1, MovieType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(genreTypesEEnum, GenreTypes.class, "GenreTypes");
    addEEnumLiteral(genreTypesEEnum, GenreTypes.NEW_RELEASE);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.ACTION);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.ANIMATION);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.FAMILY);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.CLASSICS);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.COMEDY);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.DOCUMENTARY);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.DRAMA);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.HORROR);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.ROMANCE);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.SCI_FI);
    addEEnumLiteral(genreTypesEEnum, GenreTypes.THRILLER);

    // Initialize data types
    initEDataType(actorsListEDataType, List.class, "ActorsList", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(genreTypesObjectEDataType, GenreTypes.class, "GenreTypesObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
    initEDataType(ratingTypeEDataType, int.class, "RatingType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(ratingTypeObjectEDataType, Integer.class, "RatingTypeObject", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(ratingValuesEDataType, int.class, "RatingValues", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(ratingValuesObjectEDataType, Integer.class, "RatingValuesObject", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

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
      (actorsListEDataType, 
       source, 
       new String[] 
       {
       "name", "actorsList",
       "itemType", "http://www.eclipse.org/emf/2003/XMLType#NCName"
       });		
    addAnnotation
      (criticsReviewTypeEClass, 
       source, 
       new String[] 
       {
       "name", "criticsReviewType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getCriticsReviewType_Rating(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "rating"
       });		
    addAnnotation
      (getCriticsReviewType_ReviewedBy(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "ReviewedBy"
       });		
    addAnnotation
      (customerReviewTypeEClass, 
       source, 
       new String[] 
       {
       "name", "customerReviewType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getCustomerReviewType_Comment(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "comment"
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
      (getDocumentRoot_CheckedOutBy(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "checkedOutBy",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_CriticsReview(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "criticsReview",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_CustomerReview(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "customerReview",
       "namespace", "##targetNamespace",
       "affiliation", "criticsReview"
       });		
    addAnnotation
      (getDocumentRoot_Language(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "language",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_MovieDB(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "movieDB",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getDocumentRoot_SpecialFeatures(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "specialFeatures",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (genreTypesEEnum, 
       source, 
       new String[] 
       {
       "name", "genreTypes"
       });		
    addAnnotation
      (genreTypesObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "genreTypes:Object",
       "baseType", "genreTypes"
       });		
    addAnnotation
      (movieDBTypeEClass, 
       source, 
       new String[] 
       {
       "name", "movieDBType",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getMovieDBType_MovieDBFeatureMap(), 
       source, 
       new String[] 
       {
       "name", ":group",
       "kind", "group"
       });		
    addAnnotation
      (getMovieDBType_Movie(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "movie",
       "namespace", "##targetNamespace",
       "group", "#:group"
       });		
    addAnnotation
      (getMovieDBType_Comment(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "comment",
       "group", "#:group"
       });		
    addAnnotation
      (movieTypeEClass, 
       source, 
       new String[] 
       {
       "name", "movie",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getMovieType_Title(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "title"
       });		
    addAnnotation
      (getMovieType_Actors(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "actors"
       });		
    addAnnotation
      (getMovieType_Director(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "director"
       });		
    addAnnotation
      (getMovieType_Genre(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "genre"
       });		
    addAnnotation
      (getMovieType_Summary(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "summary"
       });		
    addAnnotation
      (getMovieType_CriticsReviewGroup(), 
       source, 
       new String[] 
       {
       "kind", "group",
       "name", "criticsReview:group",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getMovieType_CriticsReview(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "criticsReview",
       "namespace", "##targetNamespace",
       "group", "criticsReview:group"
       });		
    addAnnotation
      (getMovieType_CheckedOutBy(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "checkedOutBy",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getMovieType_Any(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "wildcards", "##any",
       "name", ":8",
       "processing", "strict"
       });		
    addAnnotation
      (getMovieType_ID(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "ID",
       "namespace", "http:///org.eclipse.emf.test.models/Customer"
       });		
    addAnnotation
      (ratingTypeEDataType, 
       source, 
       new String[] 
       {
       "name", "rating_._type",
       "baseType", "http://www.eclipse.org/emf/2003/XMLType#int",
       "minInclusive", "0",
       "maxInclusive", "10"
       });		
    addAnnotation
      (ratingTypeObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "rating_._type:Object",
       "baseType", "rating_._type"
       });		
    addAnnotation
      (ratingValuesEDataType, 
       source, 
       new String[] 
       {
       "name", "ratingValues",
       "baseType", "http://www.eclipse.org/emf/2003/XMLType#int",
       "minInclusive", "0",
       "maxInclusive", "10"
       });		
    addAnnotation
      (ratingValuesObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "ratingValues:Object",
       "baseType", "ratingValues"
       });
  }

} //DBPackageImpl
