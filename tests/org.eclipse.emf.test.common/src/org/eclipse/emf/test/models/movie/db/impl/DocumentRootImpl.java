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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.customer.CustomerType;

import org.eclipse.emf.test.models.movie.db.CriticsReviewType;
import org.eclipse.emf.test.models.movie.db.CustomerReviewType;
import org.eclipse.emf.test.models.movie.db.DBPackage;
import org.eclipse.emf.test.models.movie.db.DocumentRoot;
import org.eclipse.emf.test.models.movie.db.MovieDBType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl#getCheckedOutBy <em>Checked Out By</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl#getCriticsReview <em>Critics Review</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl#getCustomerReview <em>Customer Review</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl#getMovieDB <em>Movie DB</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.DocumentRootImpl#getSpecialFeatures <em>Special Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot
{
  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected FeatureMap mixed;

  /**
   * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXMLNSPrefixMap()
   * @generated
   * @ordered
   */
  protected EMap<String, String> xMLNSPrefixMap;

  /**
   * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXSISchemaLocation()
   * @generated
   * @ordered
   */
  protected EMap<String, String> xSISchemaLocation;

  /**
   * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLanguage()
   * @generated
   * @ordered
   */
  protected static final String LANGUAGE_EDEFAULT = null;

  /**
   * The default value of the '{@link #getSpecialFeatures() <em>Special Features</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSpecialFeatures()
   * @generated
   * @ordered
   */
  protected static final String SPECIAL_FEATURES_EDEFAULT = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DocumentRootImpl()
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
    return DBPackage.Literals.DOCUMENT_ROOT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicFeatureMap(this, DBPackage.DOCUMENT_ROOT__MIXED);
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<String, String> getXMLNSPrefixMap()
  {
    if (xMLNSPrefixMap == null)
    {
      xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, DBPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    }
    return xMLNSPrefixMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<String, String> getXSISchemaLocation()
  {
    if (xSISchemaLocation == null)
    {
      xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, DBPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    }
    return xSISchemaLocation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerType getCheckedOutBy()
  {
    CustomerType checkedOutBy = basicGetCheckedOutBy();
    return checkedOutBy != null && checkedOutBy.eIsProxy() ? (CustomerType)eResolveProxy((InternalEObject)checkedOutBy) : checkedOutBy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerType basicGetCheckedOutBy()
  {
    return (CustomerType)getMixed().get(DBPackage.Literals.DOCUMENT_ROOT__CHECKED_OUT_BY, false);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCheckedOutBy(CustomerType newCheckedOutBy)
  {
    ((FeatureMap.Internal)getMixed()).set(DBPackage.Literals.DOCUMENT_ROOT__CHECKED_OUT_BY, newCheckedOutBy);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CriticsReviewType getCriticsReview()
  {
    return (CriticsReviewType)getMixed().get(DBPackage.Literals.DOCUMENT_ROOT__CRITICS_REVIEW, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCriticsReview(CriticsReviewType newCriticsReview, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)getMixed()).basicAdd(DBPackage.Literals.DOCUMENT_ROOT__CRITICS_REVIEW, newCriticsReview, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCriticsReview(CriticsReviewType newCriticsReview)
  {
    ((FeatureMap.Internal)getMixed()).set(DBPackage.Literals.DOCUMENT_ROOT__CRITICS_REVIEW, newCriticsReview);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerReviewType getCustomerReview()
  {
    return (CustomerReviewType)getMixed().get(DBPackage.Literals.DOCUMENT_ROOT__CUSTOMER_REVIEW, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCustomerReview(CustomerReviewType newCustomerReview, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)getMixed()).basicAdd(DBPackage.Literals.DOCUMENT_ROOT__CUSTOMER_REVIEW, newCustomerReview, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCustomerReview(CustomerReviewType newCustomerReview)
  {
    ((FeatureMap.Internal)getMixed()).set(DBPackage.Literals.DOCUMENT_ROOT__CUSTOMER_REVIEW, newCustomerReview);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLanguage()
  {
    return (String)getMixed().get(DBPackage.Literals.DOCUMENT_ROOT__LANGUAGE, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLanguage(String newLanguage)
  {
    ((FeatureMap.Internal)getMixed()).set(DBPackage.Literals.DOCUMENT_ROOT__LANGUAGE, newLanguage);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MovieDBType getMovieDB()
  {
    return (MovieDBType)getMixed().get(DBPackage.Literals.DOCUMENT_ROOT__MOVIE_DB, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMovieDB(MovieDBType newMovieDB, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)getMixed()).basicAdd(DBPackage.Literals.DOCUMENT_ROOT__MOVIE_DB, newMovieDB, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMovieDB(MovieDBType newMovieDB)
  {
    ((FeatureMap.Internal)getMixed()).set(DBPackage.Literals.DOCUMENT_ROOT__MOVIE_DB, newMovieDB);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSpecialFeatures()
  {
    return (String)getMixed().get(DBPackage.Literals.DOCUMENT_ROOT__SPECIAL_FEATURES, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSpecialFeatures(String newSpecialFeatures)
  {
    ((FeatureMap.Internal)getMixed()).set(DBPackage.Literals.DOCUMENT_ROOT__SPECIAL_FEATURES, newSpecialFeatures);
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
      case DBPackage.DOCUMENT_ROOT__MIXED:
        return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
      case DBPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
      case DBPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
      case DBPackage.DOCUMENT_ROOT__CRITICS_REVIEW:
        return basicSetCriticsReview(null, msgs);
      case DBPackage.DOCUMENT_ROOT__CUSTOMER_REVIEW:
        return basicSetCustomerReview(null, msgs);
      case DBPackage.DOCUMENT_ROOT__MOVIE_DB:
        return basicSetMovieDB(null, msgs);
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
      case DBPackage.DOCUMENT_ROOT__MIXED:
        if (coreType) return getMixed();
        return ((FeatureMap.Internal)getMixed()).getWrapper();
      case DBPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        if (coreType) return getXMLNSPrefixMap();
        else return getXMLNSPrefixMap().map();
      case DBPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        if (coreType) return getXSISchemaLocation();
        else return getXSISchemaLocation().map();
      case DBPackage.DOCUMENT_ROOT__CHECKED_OUT_BY:
        if (resolve) return getCheckedOutBy();
        return basicGetCheckedOutBy();
      case DBPackage.DOCUMENT_ROOT__CRITICS_REVIEW:
        return getCriticsReview();
      case DBPackage.DOCUMENT_ROOT__CUSTOMER_REVIEW:
        return getCustomerReview();
      case DBPackage.DOCUMENT_ROOT__LANGUAGE:
        return getLanguage();
      case DBPackage.DOCUMENT_ROOT__MOVIE_DB:
        return getMovieDB();
      case DBPackage.DOCUMENT_ROOT__SPECIAL_FEATURES:
        return getSpecialFeatures();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case DBPackage.DOCUMENT_ROOT__MIXED:
        ((FeatureMap.Internal)getMixed()).set(newValue);
        return;
      case DBPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
        return;
      case DBPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
        return;
      case DBPackage.DOCUMENT_ROOT__CHECKED_OUT_BY:
        setCheckedOutBy((CustomerType)newValue);
        return;
      case DBPackage.DOCUMENT_ROOT__CRITICS_REVIEW:
        setCriticsReview((CriticsReviewType)newValue);
        return;
      case DBPackage.DOCUMENT_ROOT__CUSTOMER_REVIEW:
        setCustomerReview((CustomerReviewType)newValue);
        return;
      case DBPackage.DOCUMENT_ROOT__LANGUAGE:
        setLanguage((String)newValue);
        return;
      case DBPackage.DOCUMENT_ROOT__MOVIE_DB:
        setMovieDB((MovieDBType)newValue);
        return;
      case DBPackage.DOCUMENT_ROOT__SPECIAL_FEATURES:
        setSpecialFeatures((String)newValue);
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
      case DBPackage.DOCUMENT_ROOT__MIXED:
        getMixed().clear();
        return;
      case DBPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        return;
      case DBPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        return;
      case DBPackage.DOCUMENT_ROOT__CHECKED_OUT_BY:
        setCheckedOutBy((CustomerType)null);
        return;
      case DBPackage.DOCUMENT_ROOT__CRITICS_REVIEW:
        setCriticsReview((CriticsReviewType)null);
        return;
      case DBPackage.DOCUMENT_ROOT__CUSTOMER_REVIEW:
        setCustomerReview((CustomerReviewType)null);
        return;
      case DBPackage.DOCUMENT_ROOT__LANGUAGE:
        setLanguage(LANGUAGE_EDEFAULT);
        return;
      case DBPackage.DOCUMENT_ROOT__MOVIE_DB:
        setMovieDB((MovieDBType)null);
        return;
      case DBPackage.DOCUMENT_ROOT__SPECIAL_FEATURES:
        setSpecialFeatures(SPECIAL_FEATURES_EDEFAULT);
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
      case DBPackage.DOCUMENT_ROOT__MIXED:
        return mixed != null && !mixed.isEmpty();
      case DBPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
      case DBPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
      case DBPackage.DOCUMENT_ROOT__CHECKED_OUT_BY:
        return basicGetCheckedOutBy() != null;
      case DBPackage.DOCUMENT_ROOT__CRITICS_REVIEW:
        return getCriticsReview() != null;
      case DBPackage.DOCUMENT_ROOT__CUSTOMER_REVIEW:
        return getCustomerReview() != null;
      case DBPackage.DOCUMENT_ROOT__LANGUAGE:
        return LANGUAGE_EDEFAULT == null ? getLanguage() != null : !LANGUAGE_EDEFAULT.equals(getLanguage());
      case DBPackage.DOCUMENT_ROOT__MOVIE_DB:
        return getMovieDB() != null;
      case DBPackage.DOCUMENT_ROOT__SPECIAL_FEATURES:
        return SPECIAL_FEATURES_EDEFAULT == null ? getSpecialFeatures() != null : !SPECIAL_FEATURES_EDEFAULT.equals(getSpecialFeatures());
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
    result.append(" (mixed: ");
    result.append(mixed);
    result.append(')');
    return result.toString();
  }

} //DocumentRootImpl
