/**
 * <copyright>
 * </copyright>
 *
 * $Id: DocumentRootImpl.java,v 1.1 2005/02/08 20:54:12 marcelop Exp $
 */
package org.eclipse.emf.test.models.movie.db.impl;

import java.util.Collection;

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
import org.eclipse.emf.test.models.movie.db.DbPackage;
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
  protected FeatureMap mixed = null;

  /**
   * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXMLNSPrefixMap()
   * @generated
   * @ordered
   */
  protected EMap xMLNSPrefixMap = null;

  /**
   * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXSISchemaLocation()
   * @generated
   * @ordered
   */
  protected EMap xSISchemaLocation = null;

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
  protected EClass eStaticClass()
  {
    return DbPackage.eINSTANCE.getDocumentRoot();
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
      mixed = new BasicFeatureMap(this, DbPackage.DOCUMENT_ROOT__MIXED);
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getXMLNSPrefixMap()
  {
    if (xMLNSPrefixMap == null)
    {
      xMLNSPrefixMap = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, DbPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    }
    return xMLNSPrefixMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getXSISchemaLocation()
  {
    if (xSISchemaLocation == null)
    {
      xSISchemaLocation = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, DbPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
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
    return checkedOutBy == null ? null : (CustomerType)eResolveProxy((InternalEObject)checkedOutBy);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerType basicGetCheckedOutBy()
  {
    return (CustomerType)getMixed().get(DbPackage.eINSTANCE.getDocumentRoot_CheckedOutBy(), false);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCheckedOutBy(CustomerType newCheckedOutBy)
  {
    ((FeatureMap.Internal)getMixed()).set(DbPackage.eINSTANCE.getDocumentRoot_CheckedOutBy(), newCheckedOutBy);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CriticsReviewType getCriticsReview()
  {
    return (CriticsReviewType)getMixed().get(DbPackage.eINSTANCE.getDocumentRoot_CriticsReview(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCriticsReview(CriticsReviewType newCriticsReview, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)getMixed()).basicAdd(DbPackage.eINSTANCE.getDocumentRoot_CriticsReview(), newCriticsReview, null);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCriticsReview(CriticsReviewType newCriticsReview)
  {
    ((FeatureMap.Internal)getMixed()).set(DbPackage.eINSTANCE.getDocumentRoot_CriticsReview(), newCriticsReview);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerReviewType getCustomerReview()
  {
    return (CustomerReviewType)getMixed().get(DbPackage.eINSTANCE.getDocumentRoot_CustomerReview(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCustomerReview(CustomerReviewType newCustomerReview, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)getMixed()).basicAdd(DbPackage.eINSTANCE.getDocumentRoot_CustomerReview(), newCustomerReview, null);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCustomerReview(CustomerReviewType newCustomerReview)
  {
    ((FeatureMap.Internal)getMixed()).set(DbPackage.eINSTANCE.getDocumentRoot_CustomerReview(), newCustomerReview);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLanguage()
  {
    return (String)getMixed().get(DbPackage.eINSTANCE.getDocumentRoot_Language(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLanguage(String newLanguage)
  {
    ((FeatureMap.Internal)getMixed()).set(DbPackage.eINSTANCE.getDocumentRoot_Language(), newLanguage);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MovieDBType getMovieDB()
  {
    return (MovieDBType)getMixed().get(DbPackage.eINSTANCE.getDocumentRoot_MovieDB(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMovieDB(MovieDBType newMovieDB, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)getMixed()).basicAdd(DbPackage.eINSTANCE.getDocumentRoot_MovieDB(), newMovieDB, null);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMovieDB(MovieDBType newMovieDB)
  {
    ((FeatureMap.Internal)getMixed()).set(DbPackage.eINSTANCE.getDocumentRoot_MovieDB(), newMovieDB);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSpecialFeatures()
  {
    return (String)getMixed().get(DbPackage.eINSTANCE.getDocumentRoot_SpecialFeatures(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSpecialFeatures(String newSpecialFeatures)
  {
    ((FeatureMap.Internal)getMixed()).set(DbPackage.eINSTANCE.getDocumentRoot_SpecialFeatures(), newSpecialFeatures);
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
        case DbPackage.DOCUMENT_ROOT__MIXED:
          return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
        case DbPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
          return ((InternalEList)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
        case DbPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
          return ((InternalEList)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
        case DbPackage.DOCUMENT_ROOT__CRITICS_REVIEW:
          return basicSetCriticsReview(null, msgs);
        case DbPackage.DOCUMENT_ROOT__CUSTOMER_REVIEW:
          return basicSetCustomerReview(null, msgs);
        case DbPackage.DOCUMENT_ROOT__MOVIE_DB:
          return basicSetMovieDB(null, msgs);
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
      case DbPackage.DOCUMENT_ROOT__MIXED:
        return getMixed();
      case DbPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return getXMLNSPrefixMap();
      case DbPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return getXSISchemaLocation();
      case DbPackage.DOCUMENT_ROOT__CHECKED_OUT_BY:
        if (resolve) return getCheckedOutBy();
        return basicGetCheckedOutBy();
      case DbPackage.DOCUMENT_ROOT__CRITICS_REVIEW:
        return getCriticsReview();
      case DbPackage.DOCUMENT_ROOT__CUSTOMER_REVIEW:
        return getCustomerReview();
      case DbPackage.DOCUMENT_ROOT__LANGUAGE:
        return getLanguage();
      case DbPackage.DOCUMENT_ROOT__MOVIE_DB:
        return getMovieDB();
      case DbPackage.DOCUMENT_ROOT__SPECIAL_FEATURES:
        return getSpecialFeatures();
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
      case DbPackage.DOCUMENT_ROOT__MIXED:
        getMixed().clear();
        getMixed().addAll((Collection)newValue);
        return;
      case DbPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        getXMLNSPrefixMap().addAll((Collection)newValue);
        return;
      case DbPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        getXSISchemaLocation().addAll((Collection)newValue);
        return;
      case DbPackage.DOCUMENT_ROOT__CHECKED_OUT_BY:
        setCheckedOutBy((CustomerType)newValue);
        return;
      case DbPackage.DOCUMENT_ROOT__CRITICS_REVIEW:
        setCriticsReview((CriticsReviewType)newValue);
        return;
      case DbPackage.DOCUMENT_ROOT__CUSTOMER_REVIEW:
        setCustomerReview((CustomerReviewType)newValue);
        return;
      case DbPackage.DOCUMENT_ROOT__LANGUAGE:
        setLanguage((String)newValue);
        return;
      case DbPackage.DOCUMENT_ROOT__MOVIE_DB:
        setMovieDB((MovieDBType)newValue);
        return;
      case DbPackage.DOCUMENT_ROOT__SPECIAL_FEATURES:
        setSpecialFeatures((String)newValue);
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
      case DbPackage.DOCUMENT_ROOT__MIXED:
        getMixed().clear();
        return;
      case DbPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        return;
      case DbPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        return;
      case DbPackage.DOCUMENT_ROOT__CHECKED_OUT_BY:
        setCheckedOutBy((CustomerType)null);
        return;
      case DbPackage.DOCUMENT_ROOT__CRITICS_REVIEW:
        setCriticsReview((CriticsReviewType)null);
        return;
      case DbPackage.DOCUMENT_ROOT__CUSTOMER_REVIEW:
        setCustomerReview((CustomerReviewType)null);
        return;
      case DbPackage.DOCUMENT_ROOT__LANGUAGE:
        setLanguage(LANGUAGE_EDEFAULT);
        return;
      case DbPackage.DOCUMENT_ROOT__MOVIE_DB:
        setMovieDB((MovieDBType)null);
        return;
      case DbPackage.DOCUMENT_ROOT__SPECIAL_FEATURES:
        setSpecialFeatures(SPECIAL_FEATURES_EDEFAULT);
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
      case DbPackage.DOCUMENT_ROOT__MIXED:
        return mixed != null && !mixed.isEmpty();
      case DbPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
      case DbPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
      case DbPackage.DOCUMENT_ROOT__CHECKED_OUT_BY:
        return basicGetCheckedOutBy() != null;
      case DbPackage.DOCUMENT_ROOT__CRITICS_REVIEW:
        return getCriticsReview() != null;
      case DbPackage.DOCUMENT_ROOT__CUSTOMER_REVIEW:
        return getCustomerReview() != null;
      case DbPackage.DOCUMENT_ROOT__LANGUAGE:
        return LANGUAGE_EDEFAULT == null ? getLanguage() != null : !LANGUAGE_EDEFAULT.equals(getLanguage());
      case DbPackage.DOCUMENT_ROOT__MOVIE_DB:
        return getMovieDB() != null;
      case DbPackage.DOCUMENT_ROOT__SPECIAL_FEATURES:
        return SPECIAL_FEATURES_EDEFAULT == null ? getSpecialFeatures() != null : !SPECIAL_FEATURES_EDEFAULT.equals(getSpecialFeatures());
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
    result.append(" (mixed: ");
    result.append(mixed);
    result.append(')');
    return result.toString();
  }

} //DocumentRootImpl
