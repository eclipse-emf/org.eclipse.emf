/**
 * <copyright>
 * </copyright>
 *
 * $Id: CriticsReviewTypeImpl.java,v 1.1 2004/11/04 05:52:46 marcelop Exp $
 */
package org.eclipse.emf.test.models.movie.db.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.test.models.movie.db.CriticsReviewType;
import org.eclipse.emf.test.models.movie.db.DbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Critics Review Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.CriticsReviewTypeImpl#getRating <em>Rating</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.CriticsReviewTypeImpl#getReviewedBy <em>Reviewed By</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CriticsReviewTypeImpl extends EObjectImpl implements CriticsReviewType
{
  /**
   * The default value of the '{@link #getRating() <em>Rating</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRating()
   * @generated
   * @ordered
   */
  protected static final int RATING_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getRating() <em>Rating</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRating()
   * @generated
   * @ordered
   */
  protected int rating = RATING_EDEFAULT;

  /**
   * This is true if the Rating attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean ratingESet = false;

  /**
   * The default value of the '{@link #getReviewedBy() <em>Reviewed By</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReviewedBy()
   * @generated
   * @ordered
   */
  protected static final String REVIEWED_BY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getReviewedBy() <em>Reviewed By</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReviewedBy()
   * @generated
   * @ordered
   */
  protected String reviewedBy = REVIEWED_BY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CriticsReviewTypeImpl()
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
    return DbPackage.eINSTANCE.getCriticsReviewType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getRating()
  {
    return rating;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRating(int newRating)
  {
    int oldRating = rating;
    rating = newRating;
    boolean oldRatingESet = ratingESet;
    ratingESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DbPackage.CRITICS_REVIEW_TYPE__RATING, oldRating, rating, !oldRatingESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetRating()
  {
    int oldRating = rating;
    boolean oldRatingESet = ratingESet;
    rating = RATING_EDEFAULT;
    ratingESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, DbPackage.CRITICS_REVIEW_TYPE__RATING, oldRating, RATING_EDEFAULT, oldRatingESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetRating()
  {
    return ratingESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getReviewedBy()
  {
    return reviewedBy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReviewedBy(String newReviewedBy)
  {
    String oldReviewedBy = reviewedBy;
    reviewedBy = newReviewedBy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DbPackage.CRITICS_REVIEW_TYPE__REVIEWED_BY, oldReviewedBy, reviewedBy));
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
      case DbPackage.CRITICS_REVIEW_TYPE__RATING:
        return new Integer(getRating());
      case DbPackage.CRITICS_REVIEW_TYPE__REVIEWED_BY:
        return getReviewedBy();
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
      case DbPackage.CRITICS_REVIEW_TYPE__RATING:
        setRating(((Integer)newValue).intValue());
        return;
      case DbPackage.CRITICS_REVIEW_TYPE__REVIEWED_BY:
        setReviewedBy((String)newValue);
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
      case DbPackage.CRITICS_REVIEW_TYPE__RATING:
        unsetRating();
        return;
      case DbPackage.CRITICS_REVIEW_TYPE__REVIEWED_BY:
        setReviewedBy(REVIEWED_BY_EDEFAULT);
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
      case DbPackage.CRITICS_REVIEW_TYPE__RATING:
        return isSetRating();
      case DbPackage.CRITICS_REVIEW_TYPE__REVIEWED_BY:
        return REVIEWED_BY_EDEFAULT == null ? reviewedBy != null : !REVIEWED_BY_EDEFAULT.equals(reviewedBy);
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
    result.append(" (rating: ");
    if (ratingESet) result.append(rating); else result.append("<unset>");
    result.append(", reviewedBy: ");
    result.append(reviewedBy);
    result.append(')');
    return result.toString();
  }

} //CriticsReviewTypeImpl
