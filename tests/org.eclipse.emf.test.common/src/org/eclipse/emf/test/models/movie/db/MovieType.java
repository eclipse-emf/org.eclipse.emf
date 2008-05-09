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
 * $Id: MovieType.java,v 1.7 2008/05/09 20:10:32 emerks Exp $
 */
package org.eclipse.emf.test.models.movie.db;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.emf.test.models.customer.CustomerType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Movie Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieType#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieType#getActors <em>Actors</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieType#getDirector <em>Director</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieType#getGenre <em>Genre</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieType#getSummary <em>Summary</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieType#getCriticsReviewGroup <em>Critics Review Group</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieType#getCriticsReview <em>Critics Review</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieType#getCheckedOutBy <em>Checked Out By</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieType#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieType#getID <em>ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType()
 * @model extendedMetaData="name='movie' kind='elementOnly'"
 * @generated
 */
public interface MovieType extends EObject
{
  /**
   * Returns the value of the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Title</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Title</em>' attribute.
   * @see #setTitle(String)
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType_Title()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='title'"
   * @generated
   */
  String getTitle();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.MovieType#getTitle <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Title</em>' attribute.
   * @see #getTitle()
   * @generated
   */
  void setTitle(String value);

  /**
   * Returns the value of the '<em><b>Actors</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Actors</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Actors</em>' attribute.
   * @see #setActors(List)
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType_Actors()
   * @model dataType="org.eclipse.emf.test.models.movie.db.ActorsList" required="true" many="false"
   *        extendedMetaData="kind='element' name='actors'"
   * @generated
   */
  List<String> getActors();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.MovieType#getActors <em>Actors</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Actors</em>' attribute.
   * @see #getActors()
   * @generated
   */
  void setActors(List<String> value);

  /**
   * Returns the value of the '<em><b>Director</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Director</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Director</em>' attribute.
   * @see #setDirector(String)
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType_Director()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='director'"
   * @generated
   */
  String getDirector();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.MovieType#getDirector <em>Director</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Director</em>' attribute.
   * @see #getDirector()
   * @generated
   */
  void setDirector(String value);

  /**
   * Returns the value of the '<em><b>Genre</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.test.models.movie.db.GenreTypes}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Genre</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Genre</em>' attribute.
   * @see org.eclipse.emf.test.models.movie.db.GenreTypes
   * @see #isSetGenre()
   * @see #unsetGenre()
   * @see #setGenre(GenreTypes)
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType_Genre()
   * @model unsettable="true" required="true"
   *        extendedMetaData="kind='element' name='genre'"
   * @generated
   */
  GenreTypes getGenre();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.MovieType#getGenre <em>Genre</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Genre</em>' attribute.
   * @see org.eclipse.emf.test.models.movie.db.GenreTypes
   * @see #isSetGenre()
   * @see #unsetGenre()
   * @see #getGenre()
   * @generated
   */
  void setGenre(GenreTypes value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.movie.db.MovieType#getGenre <em>Genre</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetGenre()
   * @see #getGenre()
   * @see #setGenre(GenreTypes)
   * @generated
   */
  void unsetGenre();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.movie.db.MovieType#getGenre <em>Genre</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Genre</em>' attribute is set.
   * @see #unsetGenre()
   * @see #getGenre()
   * @see #setGenre(GenreTypes)
   * @generated
   */
  boolean isSetGenre();

  /**
   * Returns the value of the '<em><b>Summary</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Summary</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Summary</em>' attribute.
   * @see #setSummary(String)
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType_Summary()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='summary'"
   * @generated
   */
  String getSummary();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.MovieType#getSummary <em>Summary</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Summary</em>' attribute.
   * @see #getSummary()
   * @generated
   */
  void setSummary(String value);

  /**
   * Returns the value of the '<em><b>Critics Review Group</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Critics Review Group</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Critics Review Group</em>' attribute list.
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType_CriticsReviewGroup()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
   *        extendedMetaData="kind='group' name='criticsReview:group' namespace='##targetNamespace'"
   * @generated
   */
  FeatureMap getCriticsReviewGroup();

  /**
   * Returns the value of the '<em><b>Critics Review</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.movie.db.CriticsReviewType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Critics Review</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Critics Review</em>' containment reference list.
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType_CriticsReview()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='criticsReview' namespace='##targetNamespace' group='criticsReview:group'"
   * @generated
   */
  EList<CriticsReviewType> getCriticsReview();

  /**
   * Returns the value of the '<em><b>Checked Out By</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Checked Out By</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Checked Out By</em>' reference.
   * @see #setCheckedOutBy(CustomerType)
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType_CheckedOutBy()
   * @model required="true"
   *        extendedMetaData="kind='element' name='checkedOutBy' namespace='##targetNamespace'"
   * @generated
   */
  CustomerType getCheckedOutBy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.MovieType#getCheckedOutBy <em>Checked Out By</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Checked Out By</em>' reference.
   * @see #getCheckedOutBy()
   * @generated
   */
  void setCheckedOutBy(CustomerType value);

  /**
   * Returns the value of the '<em><b>Any</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Any</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Any</em>' attribute list.
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType_Any()
   * @model dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="false"
   *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':8' processing='strict'"
   * @generated
   */
  FeatureMap getAny();

  /**
   * Returns the value of the '<em><b>ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ID</em>' attribute.
   * @see #setID(String)
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieType_ID()
   * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
   *        extendedMetaData="kind='attribute' name='ID' namespace='http:///org.eclipse.emf.test.models/Customer'"
   * @generated
   */
  String getID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.MovieType#getID <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ID</em>' attribute.
   * @see #getID()
   * @generated
   */
  void setID(String value);

} // MovieType
