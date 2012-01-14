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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.test.models.movie.db.CriticsReviewType;
import org.eclipse.emf.test.models.movie.db.DBPackage;

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
  protected boolean ratingESet;

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
  @Override
  protected EClass eStaticClass()
  {
    return DBPackage.Literals.CRITICS_REVIEW_TYPE;
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
      eNotify(new ENotificationImpl(this, Notification.SET, DBPackage.CRITICS_REVIEW_TYPE__RATING, oldRating, rating, !oldRatingESet));
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
      eNotify(new ENotificationImpl(this, Notification.UNSET, DBPackage.CRITICS_REVIEW_TYPE__RATING, oldRating, RATING_EDEFAULT, oldRatingESet));
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
      eNotify(new ENotificationImpl(this, Notification.SET, DBPackage.CRITICS_REVIEW_TYPE__REVIEWED_BY, oldReviewedBy, reviewedBy));
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
      case DBPackage.CRITICS_REVIEW_TYPE__RATING:
        return getRating();
      case DBPackage.CRITICS_REVIEW_TYPE__REVIEWED_BY:
        return getReviewedBy();
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
      case DBPackage.CRITICS_REVIEW_TYPE__RATING:
        setRating((Integer)newValue);
        return;
      case DBPackage.CRITICS_REVIEW_TYPE__REVIEWED_BY:
        setReviewedBy((String)newValue);
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
      case DBPackage.CRITICS_REVIEW_TYPE__RATING:
        unsetRating();
        return;
      case DBPackage.CRITICS_REVIEW_TYPE__REVIEWED_BY:
        setReviewedBy(REVIEWED_BY_EDEFAULT);
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
      case DBPackage.CRITICS_REVIEW_TYPE__RATING:
        return isSetRating();
      case DBPackage.CRITICS_REVIEW_TYPE__REVIEWED_BY:
        return REVIEWED_BY_EDEFAULT == null ? reviewedBy != null : !REVIEWED_BY_EDEFAULT.equals(reviewedBy);
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
    result.append(" (rating: ");
    if (ratingESet) result.append(rating); else result.append("<unset>");
    result.append(", reviewedBy: ");
    result.append(reviewedBy);
    result.append(')');
    return result.toString();
  }

} //CriticsReviewTypeImpl
