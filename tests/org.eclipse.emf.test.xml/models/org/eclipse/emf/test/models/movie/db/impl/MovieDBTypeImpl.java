/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.test.models.movie.db.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.movie.db.DbPackage;
import org.eclipse.emf.test.models.movie.db.MovieDBType;

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
  protected FeatureMap movieDBFeatureMap = null;

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
  protected EClass eStaticClass()
  {
    return DbPackage.eINSTANCE.getMovieDBType();
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
      movieDBFeatureMap = new BasicFeatureMap(this, DbPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP);
    }
    return movieDBFeatureMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getMovie()
  {
    return ((FeatureMap)getMovieDBFeatureMap()).list(DbPackage.eINSTANCE.getMovieDBType_Movie());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return (String)getMovieDBFeatureMap().get(DbPackage.eINSTANCE.getMovieDBType_Comment(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    ((FeatureMap.Internal)getMovieDBFeatureMap()).set(DbPackage.eINSTANCE.getMovieDBType_Comment(), newComment);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case DbPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP:
          return ((InternalEList)getMovieDBFeatureMap()).basicRemove(otherEnd, msgs);
        case DbPackage.MOVIE_DB_TYPE__MOVIE:
          return ((InternalEList)getMovie()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case DbPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP:
        return getMovieDBFeatureMap();
      case DbPackage.MOVIE_DB_TYPE__MOVIE:
        return getMovie();
      case DbPackage.MOVIE_DB_TYPE__COMMENT:
        return getComment();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case DbPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP:
        getMovieDBFeatureMap().clear();
        getMovieDBFeatureMap().addAll((Collection)newValue);
        return;
      case DbPackage.MOVIE_DB_TYPE__MOVIE:
        getMovie().clear();
        getMovie().addAll((Collection)newValue);
        return;
      case DbPackage.MOVIE_DB_TYPE__COMMENT:
        setComment((String)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case DbPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP:
        getMovieDBFeatureMap().clear();
        return;
      case DbPackage.MOVIE_DB_TYPE__MOVIE:
        getMovie().clear();
        return;
      case DbPackage.MOVIE_DB_TYPE__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case DbPackage.MOVIE_DB_TYPE__MOVIE_DB_FEATURE_MAP:
        return movieDBFeatureMap != null && !movieDBFeatureMap.isEmpty();
      case DbPackage.MOVIE_DB_TYPE__MOVIE:
        return !getMovie().isEmpty();
      case DbPackage.MOVIE_DB_TYPE__COMMENT:
        return COMMENT_EDEFAULT == null ? getComment() != null : !COMMENT_EDEFAULT.equals(getComment());
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
