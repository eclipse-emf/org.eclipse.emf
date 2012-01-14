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
package org.eclipse.emf.test.models.movie.db.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.movie.db.DBPackage;
import org.eclipse.emf.test.models.movie.db.MovieDBType;
import org.eclipse.emf.test.models.movie.db.MovieType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Movie DB Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.MovieDBTypeImpl#getMovieDBFeatureMap <em>Movie DB Feature Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.MovieDBTypeImpl#getMovie <em>Movie</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.MovieDBTypeImpl#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MovieDBTypeImpl extends EObjectImpl implements MovieDBType
{
  /**
   * The cached value of the '{@link #getMovieDBFeatureMap() <em>Movie DB Feature Map</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMovieDBFeatureMap()
   * @generated
   * @ordered
   */
  protected FeatureMap movieDBFeatureMap;

  /**
   * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected static final String COMMENT_EDEFAULT = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MovieDBTypeImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return DBPackage.Literals.MOVIE_DB_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getMovieDBFeatureMap()
  {
    if (movieDBFeatureMap == null)
    {
      movieDBFeatureMap = new BasicFeatureMap(this, DBPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP);
    }
    return movieDBFeatureMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<MovieType> getMovie()
  {
    return getMovieDBFeatureMap().list(DBPackage.Literals.MOVIE_DB_TYPE__MOVIE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return (String)getMovieDBFeatureMap().get(DBPackage.Literals.MOVIE_DB_TYPE__COMMENT, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    ((FeatureMap.Internal)getMovieDBFeatureMap()).set(DBPackage.Literals.MOVIE_DB_TYPE__COMMENT, newComment);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case DBPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP:
        return ((InternalEList<?>)getMovieDBFeatureMap()).basicRemove(otherEnd, msgs);
      case DBPackage.MOVIE_DB_TYPE__MOVIE:
        return ((InternalEList<?>)getMovie()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case DBPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP:
        if (coreType) return getMovieDBFeatureMap();
        return ((FeatureMap.Internal)getMovieDBFeatureMap()).getWrapper();
      case DBPackage.MOVIE_DB_TYPE__MOVIE:
        return getMovie();
      case DBPackage.MOVIE_DB_TYPE__COMMENT:
        return getComment();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case DBPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP:
        ((FeatureMap.Internal)getMovieDBFeatureMap()).set(newValue);
        return;
      case DBPackage.MOVIE_DB_TYPE__MOVIE:
        getMovie().clear();
        getMovie().addAll((Collection<? extends MovieType>)newValue);
        return;
      case DBPackage.MOVIE_DB_TYPE__COMMENT:
        setComment((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case DBPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP:
        getMovieDBFeatureMap().clear();
        return;
      case DBPackage.MOVIE_DB_TYPE__MOVIE:
        getMovie().clear();
        return;
      case DBPackage.MOVIE_DB_TYPE__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case DBPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP:
        return movieDBFeatureMap != null && !movieDBFeatureMap.isEmpty();
      case DBPackage.MOVIE_DB_TYPE__MOVIE:
        return !getMovie().isEmpty();
      case DBPackage.MOVIE_DB_TYPE__COMMENT:
        return COMMENT_EDEFAULT == null ? getComment() != null : !COMMENT_EDEFAULT.equals(getComment());
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (movieDBFeatureMap: ");
    result.append(movieDBFeatureMap);
    result.append(')');
    return result.toString();
  }

} //MovieDBTypeImpl
