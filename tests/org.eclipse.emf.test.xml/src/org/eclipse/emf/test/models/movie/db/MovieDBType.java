/**
 * <copyright>
 * </copyright>
 *
 * $Id: MovieDBType.java,v 1.1.2.1 2005/01/14 22:56:18 nickb Exp $
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
 * @see org.eclipse.emf.test.models.movie.db.DbPackage#getMovieDBType()
 * @model 
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
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getMovieDBType_MovieDBFeatureMap()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
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
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getMovieDBType_Movie()
   * @model type="org.eclipse.emf.test.models.movie.db.MovieType" containment="true" resolveProxies="false" required="true" transient="true" volatile="true" derived="true"
   * @generated
   */
  EList getMovie();

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
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getMovieDBType_Comment()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
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
