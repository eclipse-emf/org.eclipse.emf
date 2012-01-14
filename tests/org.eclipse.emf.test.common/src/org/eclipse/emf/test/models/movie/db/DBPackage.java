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
package org.eclipse.emf.test.models.movie.db;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.movie.db.DBFactory
 * @model kind="package"
 * @generated
 */
public interface DBPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "db";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org.eclipse.emf.test.models/MovieDB";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "db";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DBPackage eINSTANCE = org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.movie.db.impl.CriticsReviewTypeImpl <em>Critics Review Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.movie.db.impl.CriticsReviewTypeImpl
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getCriticsReviewType()
   * @generated
   */
  int CRITICS_REVIEW_TYPE = 0;

  /**
   * The feature id for the '<em><b>Rating</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CRITICS_REVIEW_TYPE__RATING = 0;

  /**
   * The feature id for the '<em><b>Reviewed By</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CRITICS_REVIEW_TYPE__REVIEWED_BY = 1;

  /**
   * The number of structural features of the '<em>Critics Review Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CRITICS_REVIEW_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.movie.db.impl.CustomerReviewTypeImpl <em>Customer Review Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.movie.db.impl.CustomerReviewTypeImpl
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getCustomerReviewType()
   * @generated
   */
  int CUSTOMER_REVIEW_TYPE = 1;

  /**
   * The feature id for the '<em><b>Rating</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOMER_REVIEW_TYPE__RATING = CRITICS_REVIEW_TYPE__RATING;

  /**
   * The feature id for the '<em><b>Reviewed By</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOMER_REVIEW_TYPE__REVIEWED_BY = CRITICS_REVIEW_TYPE__REVIEWED_BY;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOMER_REVIEW_TYPE__COMMENT = CRITICS_REVIEW_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Customer Review Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CUSTOMER_REVIEW_TYPE_FEATURE_COUNT = CRITICS_REVIEW_TYPE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getDocumentRoot()
   * @generated
   */
  int DOCUMENT_ROOT = 2;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__MIXED = 0;

  /**
   * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

  /**
   * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

  /**
   * The feature id for the '<em><b>Checked Out By</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__CHECKED_OUT_BY = 3;

  /**
   * The feature id for the '<em><b>Critics Review</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__CRITICS_REVIEW = 4;

  /**
   * The feature id for the '<em><b>Customer Review</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__CUSTOMER_REVIEW = 5;

  /**
   * The feature id for the '<em><b>Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__LANGUAGE = 6;

  /**
   * The feature id for the '<em><b>Movie DB</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__MOVIE_DB = 7;

  /**
   * The feature id for the '<em><b>Special Features</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__SPECIAL_FEATURES = 8;

  /**
   * The number of structural features of the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.movie.db.impl.MovieDBTypeImpl <em>Movie DB Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.movie.db.impl.MovieDBTypeImpl
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getMovieDBType()
   * @generated
   */
  int MOVIE_DB_TYPE = 3;

  /**
   * The feature id for the '<em><b>Movie DB Feature Map</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP = 0;

  /**
   * The feature id for the '<em><b>Movie</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_DB_TYPE__MOVIE = 1;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_DB_TYPE__COMMENT = 2;

  /**
   * The number of structural features of the '<em>Movie DB Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_DB_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.movie.db.impl.MovieTypeImpl <em>Movie Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.movie.db.impl.MovieTypeImpl
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getMovieType()
   * @generated
   */
  int MOVIE_TYPE = 4;

  /**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE__TITLE = 0;

  /**
   * The feature id for the '<em><b>Actors</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE__ACTORS = 1;

  /**
   * The feature id for the '<em><b>Director</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE__DIRECTOR = 2;

  /**
   * The feature id for the '<em><b>Genre</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE__GENRE = 3;

  /**
   * The feature id for the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE__SUMMARY = 4;

  /**
   * The feature id for the '<em><b>Critics Review Group</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE__CRITICS_REVIEW_GROUP = 5;

  /**
   * The feature id for the '<em><b>Critics Review</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE__CRITICS_REVIEW = 6;

  /**
   * The feature id for the '<em><b>Checked Out By</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE__CHECKED_OUT_BY = 7;

  /**
   * The feature id for the '<em><b>Any</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE__ANY = 8;

  /**
   * The feature id for the '<em><b>ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE__ID = 9;

  /**
   * The number of structural features of the '<em>Movie Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MOVIE_TYPE_FEATURE_COUNT = 10;

  /**
   * The meta object id for the '{@link org.eclipse.emf.test.models.movie.db.GenreTypes <em>Genre Types</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.movie.db.GenreTypes
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getGenreTypes()
   * @generated
   */
  int GENRE_TYPES = 5;

  /**
   * The meta object id for the '<em>Actors List</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.util.List
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getActorsList()
   * @generated
   */
  int ACTORS_LIST = 6;

  /**
   * The meta object id for the '<em>Genre Types Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.movie.db.GenreTypes
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getGenreTypesObject()
   * @generated
   */
  int GENRE_TYPES_OBJECT = 7;

  /**
   * The meta object id for the '<em>Rating Type</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getRatingType()
   * @generated
   */
  int RATING_TYPE = 8;

  /**
   * The meta object id for the '<em>Rating Type Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Integer
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getRatingTypeObject()
   * @generated
   */
  int RATING_TYPE_OBJECT = 9;

  /**
   * The meta object id for the '<em>Rating Values</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getRatingValues()
   * @generated
   */
  int RATING_VALUES = 10;

  /**
   * The meta object id for the '<em>Rating Values Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Integer
   * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getRatingValuesObject()
   * @generated
   */
  int RATING_VALUES_OBJECT = 11;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.movie.db.CriticsReviewType <em>Critics Review Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Critics Review Type</em>'.
   * @see org.eclipse.emf.test.models.movie.db.CriticsReviewType
   * @generated
   */
  EClass getCriticsReviewType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.CriticsReviewType#getRating <em>Rating</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Rating</em>'.
   * @see org.eclipse.emf.test.models.movie.db.CriticsReviewType#getRating()
   * @see #getCriticsReviewType()
   * @generated
   */
  EAttribute getCriticsReviewType_Rating();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.CriticsReviewType#getReviewedBy <em>Reviewed By</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Reviewed By</em>'.
   * @see org.eclipse.emf.test.models.movie.db.CriticsReviewType#getReviewedBy()
   * @see #getCriticsReviewType()
   * @generated
   */
  EAttribute getCriticsReviewType_ReviewedBy();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.movie.db.CustomerReviewType <em>Customer Review Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Customer Review Type</em>'.
   * @see org.eclipse.emf.test.models.movie.db.CustomerReviewType
   * @generated
   */
  EClass getCustomerReviewType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.CustomerReviewType#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.eclipse.emf.test.models.movie.db.CustomerReviewType#getComment()
   * @see #getCustomerReviewType()
   * @generated
   */
  EAttribute getCustomerReviewType_Comment();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.eclipse.emf.test.models.movie.db.DocumentRoot
   * @generated
   */
  EClass getDocumentRoot();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.eclipse.emf.test.models.movie.db.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Mixed();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.eclipse.emf.test.models.movie.db.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XMLNSPrefixMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.eclipse.emf.test.models.movie.db.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XSISchemaLocation();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getCheckedOutBy <em>Checked Out By</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Checked Out By</em>'.
   * @see org.eclipse.emf.test.models.movie.db.DocumentRoot#getCheckedOutBy()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_CheckedOutBy();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getCriticsReview <em>Critics Review</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Critics Review</em>'.
   * @see org.eclipse.emf.test.models.movie.db.DocumentRoot#getCriticsReview()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_CriticsReview();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getCustomerReview <em>Customer Review</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Customer Review</em>'.
   * @see org.eclipse.emf.test.models.movie.db.DocumentRoot#getCustomerReview()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_CustomerReview();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getLanguage <em>Language</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Language</em>'.
   * @see org.eclipse.emf.test.models.movie.db.DocumentRoot#getLanguage()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Language();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getMovieDB <em>Movie DB</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Movie DB</em>'.
   * @see org.eclipse.emf.test.models.movie.db.DocumentRoot#getMovieDB()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_MovieDB();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getSpecialFeatures <em>Special Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Special Features</em>'.
   * @see org.eclipse.emf.test.models.movie.db.DocumentRoot#getSpecialFeatures()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_SpecialFeatures();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.movie.db.MovieDBType <em>Movie DB Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Movie DB Type</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieDBType
   * @generated
   */
  EClass getMovieDBType();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.movie.db.MovieDBType#getMovieDBFeatureMap <em>Movie DB Feature Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Movie DB Feature Map</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieDBType#getMovieDBFeatureMap()
   * @see #getMovieDBType()
   * @generated
   */
  EAttribute getMovieDBType_MovieDBFeatureMap();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.movie.db.MovieDBType#getMovie <em>Movie</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Movie</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieDBType#getMovie()
   * @see #getMovieDBType()
   * @generated
   */
  EReference getMovieDBType_Movie();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.MovieDBType#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieDBType#getComment()
   * @see #getMovieDBType()
   * @generated
   */
  EAttribute getMovieDBType_Comment();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.test.models.movie.db.MovieType <em>Movie Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Movie Type</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType
   * @generated
   */
  EClass getMovieType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.MovieType#getTitle <em>Title</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Title</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType#getTitle()
   * @see #getMovieType()
   * @generated
   */
  EAttribute getMovieType_Title();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.MovieType#getActors <em>Actors</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Actors</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType#getActors()
   * @see #getMovieType()
   * @generated
   */
  EAttribute getMovieType_Actors();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.MovieType#getDirector <em>Director</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Director</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType#getDirector()
   * @see #getMovieType()
   * @generated
   */
  EAttribute getMovieType_Director();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.MovieType#getGenre <em>Genre</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Genre</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType#getGenre()
   * @see #getMovieType()
   * @generated
   */
  EAttribute getMovieType_Genre();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.MovieType#getSummary <em>Summary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Summary</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType#getSummary()
   * @see #getMovieType()
   * @generated
   */
  EAttribute getMovieType_Summary();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.movie.db.MovieType#getCriticsReviewGroup <em>Critics Review Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Critics Review Group</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType#getCriticsReviewGroup()
   * @see #getMovieType()
   * @generated
   */
  EAttribute getMovieType_CriticsReviewGroup();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.test.models.movie.db.MovieType#getCriticsReview <em>Critics Review</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Critics Review</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType#getCriticsReview()
   * @see #getMovieType()
   * @generated
   */
  EReference getMovieType_CriticsReview();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.test.models.movie.db.MovieType#getCheckedOutBy <em>Checked Out By</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Checked Out By</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType#getCheckedOutBy()
   * @see #getMovieType()
   * @generated
   */
  EReference getMovieType_CheckedOutBy();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.test.models.movie.db.MovieType#getAny <em>Any</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Any</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType#getAny()
   * @see #getMovieType()
   * @generated
   */
  EAttribute getMovieType_Any();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.test.models.movie.db.MovieType#getID <em>ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ID</em>'.
   * @see org.eclipse.emf.test.models.movie.db.MovieType#getID()
   * @see #getMovieType()
   * @generated
   */
  EAttribute getMovieType_ID();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.test.models.movie.db.GenreTypes <em>Genre Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Genre Types</em>'.
   * @see org.eclipse.emf.test.models.movie.db.GenreTypes
   * @generated
   */
  EEnum getGenreTypes();

  /**
   * Returns the meta object for data type '{@link java.util.List <em>Actors List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Actors List</em>'.
   * @see java.util.List
   * @model instanceClass="java.util.List"
   *        extendedMetaData="name='actorsList' itemType='http://www.eclipse.org/emf/2003/XMLType#NCName'"
   * @generated
   */
  EDataType getActorsList();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.test.models.movie.db.GenreTypes <em>Genre Types Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Genre Types Object</em>'.
   * @see org.eclipse.emf.test.models.movie.db.GenreTypes
   * @model instanceClass="org.eclipse.emf.test.models.movie.db.GenreTypes"
   *        extendedMetaData="name='genreTypes:Object' baseType='genreTypes'"
   * @generated
   */
  EDataType getGenreTypesObject();

  /**
   * Returns the meta object for data type '<em>Rating Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Rating Type</em>'.
   * @model instanceClass="int"
   *        extendedMetaData="name='rating_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#int' minInclusive='0' maxInclusive='10'"
   * @generated
   */
  EDataType getRatingType();

  /**
   * Returns the meta object for data type '{@link java.lang.Integer <em>Rating Type Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Rating Type Object</em>'.
   * @see java.lang.Integer
   * @model instanceClass="java.lang.Integer"
   *        extendedMetaData="name='rating_._type:Object' baseType='rating_._type'"
   * @generated
   */
  EDataType getRatingTypeObject();

  /**
   * Returns the meta object for data type '<em>Rating Values</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Rating Values</em>'.
   * @model instanceClass="int"
   *        extendedMetaData="name='ratingValues' baseType='http://www.eclipse.org/emf/2003/XMLType#int' minInclusive='0' maxInclusive='10'"
   * @generated
   */
  EDataType getRatingValues();

  /**
   * Returns the meta object for data type '{@link java.lang.Integer <em>Rating Values Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Rating Values Object</em>'.
   * @see java.lang.Integer
   * @model instanceClass="java.lang.Integer"
   *        extendedMetaData="name='ratingValues:Object' baseType='ratingValues'"
   * @generated
   */
  EDataType getRatingValuesObject();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  DBFactory getDBFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.movie.db.impl.CriticsReviewTypeImpl <em>Critics Review Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.movie.db.impl.CriticsReviewTypeImpl
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getCriticsReviewType()
     * @generated
     */
    EClass CRITICS_REVIEW_TYPE = eINSTANCE.getCriticsReviewType();

    /**
     * The meta object literal for the '<em><b>Rating</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CRITICS_REVIEW_TYPE__RATING = eINSTANCE.getCriticsReviewType_Rating();

    /**
     * The meta object literal for the '<em><b>Reviewed By</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CRITICS_REVIEW_TYPE__REVIEWED_BY = eINSTANCE.getCriticsReviewType_ReviewedBy();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.movie.db.impl.CustomerReviewTypeImpl <em>Customer Review Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.movie.db.impl.CustomerReviewTypeImpl
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getCustomerReviewType()
     * @generated
     */
    EClass CUSTOMER_REVIEW_TYPE = eINSTANCE.getCustomerReviewType();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CUSTOMER_REVIEW_TYPE__COMMENT = eINSTANCE.getCustomerReviewType_Comment();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getDocumentRoot()
     * @generated
     */
    EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

    /**
     * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

    /**
     * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

    /**
     * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

    /**
     * The meta object literal for the '<em><b>Checked Out By</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__CHECKED_OUT_BY = eINSTANCE.getDocumentRoot_CheckedOutBy();

    /**
     * The meta object literal for the '<em><b>Critics Review</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__CRITICS_REVIEW = eINSTANCE.getDocumentRoot_CriticsReview();

    /**
     * The meta object literal for the '<em><b>Customer Review</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__CUSTOMER_REVIEW = eINSTANCE.getDocumentRoot_CustomerReview();

    /**
     * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__LANGUAGE = eINSTANCE.getDocumentRoot_Language();

    /**
     * The meta object literal for the '<em><b>Movie DB</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__MOVIE_DB = eINSTANCE.getDocumentRoot_MovieDB();

    /**
     * The meta object literal for the '<em><b>Special Features</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__SPECIAL_FEATURES = eINSTANCE.getDocumentRoot_SpecialFeatures();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.movie.db.impl.MovieDBTypeImpl <em>Movie DB Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.movie.db.impl.MovieDBTypeImpl
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getMovieDBType()
     * @generated
     */
    EClass MOVIE_DB_TYPE = eINSTANCE.getMovieDBType();

    /**
     * The meta object literal for the '<em><b>Movie DB Feature Map</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP = eINSTANCE.getMovieDBType_MovieDBFeatureMap();

    /**
     * The meta object literal for the '<em><b>Movie</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MOVIE_DB_TYPE__MOVIE = eINSTANCE.getMovieDBType_Movie();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MOVIE_DB_TYPE__COMMENT = eINSTANCE.getMovieDBType_Comment();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.movie.db.impl.MovieTypeImpl <em>Movie Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.movie.db.impl.MovieTypeImpl
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getMovieType()
     * @generated
     */
    EClass MOVIE_TYPE = eINSTANCE.getMovieType();

    /**
     * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MOVIE_TYPE__TITLE = eINSTANCE.getMovieType_Title();

    /**
     * The meta object literal for the '<em><b>Actors</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MOVIE_TYPE__ACTORS = eINSTANCE.getMovieType_Actors();

    /**
     * The meta object literal for the '<em><b>Director</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MOVIE_TYPE__DIRECTOR = eINSTANCE.getMovieType_Director();

    /**
     * The meta object literal for the '<em><b>Genre</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MOVIE_TYPE__GENRE = eINSTANCE.getMovieType_Genre();

    /**
     * The meta object literal for the '<em><b>Summary</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MOVIE_TYPE__SUMMARY = eINSTANCE.getMovieType_Summary();

    /**
     * The meta object literal for the '<em><b>Critics Review Group</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MOVIE_TYPE__CRITICS_REVIEW_GROUP = eINSTANCE.getMovieType_CriticsReviewGroup();

    /**
     * The meta object literal for the '<em><b>Critics Review</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MOVIE_TYPE__CRITICS_REVIEW = eINSTANCE.getMovieType_CriticsReview();

    /**
     * The meta object literal for the '<em><b>Checked Out By</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MOVIE_TYPE__CHECKED_OUT_BY = eINSTANCE.getMovieType_CheckedOutBy();

    /**
     * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MOVIE_TYPE__ANY = eINSTANCE.getMovieType_Any();

    /**
     * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MOVIE_TYPE__ID = eINSTANCE.getMovieType_ID();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.test.models.movie.db.GenreTypes <em>Genre Types</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.movie.db.GenreTypes
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getGenreTypes()
     * @generated
     */
    EEnum GENRE_TYPES = eINSTANCE.getGenreTypes();

    /**
     * The meta object literal for the '<em>Actors List</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.util.List
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getActorsList()
     * @generated
     */
    EDataType ACTORS_LIST = eINSTANCE.getActorsList();

    /**
     * The meta object literal for the '<em>Genre Types Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.movie.db.GenreTypes
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getGenreTypesObject()
     * @generated
     */
    EDataType GENRE_TYPES_OBJECT = eINSTANCE.getGenreTypesObject();

    /**
     * The meta object literal for the '<em>Rating Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getRatingType()
     * @generated
     */
    EDataType RATING_TYPE = eINSTANCE.getRatingType();

    /**
     * The meta object literal for the '<em>Rating Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Integer
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getRatingTypeObject()
     * @generated
     */
    EDataType RATING_TYPE_OBJECT = eINSTANCE.getRatingTypeObject();

    /**
     * The meta object literal for the '<em>Rating Values</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getRatingValues()
     * @generated
     */
    EDataType RATING_VALUES = eINSTANCE.getRatingValues();

    /**
     * The meta object literal for the '<em>Rating Values Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Integer
     * @see org.eclipse.emf.test.models.movie.db.impl.DBPackageImpl#getRatingValuesObject()
     * @generated
     */
    EDataType RATING_VALUES_OBJECT = eINSTANCE.getRatingValuesObject();

  }

} //DBPackage
