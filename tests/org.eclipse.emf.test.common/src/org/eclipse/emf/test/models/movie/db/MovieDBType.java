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
 * $Id: MovieDBType.java,v 1.3 2007/06/12 15:08:10 emerks Exp $
 */
package org.eclipse.emf.test.models.movie.db;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Movie DB Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieDBType#getMovieDBFeatureMap <em>Movie DB Feature Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieDBType#getMovie <em>Movie</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.MovieDBType#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieDBType()
 * @model extendedMetaData="name='movieDBType' kind='elementOnly'"
 * @generated
 */
public interface MovieDBType extends EObject
{
  /**
   * Returns the value of the '<em><b>Movie DB Feature Map</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Movie DB Feature Map</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Movie DB Feature Map</em>' attribute list.
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieDBType_MovieDBFeatureMap()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
   *        extendedMetaData="name=':group' kind='group'"
   * @generated
   */
  FeatureMap getMovieDBFeatureMap();

  /**
   * Returns the value of the '<em><b>Movie</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.movie.db.MovieType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Movie</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Movie</em>' containment reference list.
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieDBType_Movie()
   * @model containment="true" required="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='movie' namespace='##targetNamespace' group='#:group'"
   * @generated
   */
  EList<MovieType> getMovie();

  /**
   * Returns the value of the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comment</em>' attribute.
   * @see #setComment(String)
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getMovieDBType_Comment()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='comment' group='#:group'"
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.MovieDBType#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

} // MovieDBType
