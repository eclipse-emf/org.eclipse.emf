/**
 * <copyright>
 * </copyright>
 *
 * $Id: CustomerReviewTypeImpl.java,v 1.1 2005/02/08 20:54:12 marcelop Exp $
 */
package org.eclipse.emf.test.models.movie.db.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.test.models.movie.db.CustomerReviewType;
import org.eclipse.emf.test.models.movie.db.DbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Customer Review Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.impl.CustomerReviewTypeImpl#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomerReviewTypeImpl extends CriticsReviewTypeImpl implements CustomerReviewType
{
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
   * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected String comment = COMMENT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CustomerReviewTypeImpl()
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
    return DbPackage.eINSTANCE.getCustomerReviewType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    String oldComment = comment;
    comment = newComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DbPackage.CUSTOMER_REVIEW_TYPE__COMMENT, oldComment, comment));
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
      case DbPackage.CUSTOMER_REVIEW_TYPE__RATING:
        return new Integer(getRating());
      case DbPackage.CUSTOMER_REVIEW_TYPE__REVIEWED_BY:
        return getReviewedBy();
      case DbPackage.CUSTOMER_REVIEW_TYPE__COMMENT:
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
      case DbPackage.CUSTOMER_REVIEW_TYPE__RATING:
        setRating(((Integer)newValue).intValue());
        return;
      case DbPackage.CUSTOMER_REVIEW_TYPE__REVIEWED_BY:
        setReviewedBy((String)newValue);
        return;
      case DbPackage.CUSTOMER_REVIEW_TYPE__COMMENT:
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
      case DbPackage.CUSTOMER_REVIEW_TYPE__RATING:
        unsetRating();
        return;
      case DbPackage.CUSTOMER_REVIEW_TYPE__REVIEWED_BY:
        setReviewedBy(REVIEWED_BY_EDEFAULT);
        return;
      case DbPackage.CUSTOMER_REVIEW_TYPE__COMMENT:
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
      case DbPackage.CUSTOMER_REVIEW_TYPE__RATING:
        return isSetRating();
      case DbPackage.CUSTOMER_REVIEW_TYPE__REVIEWED_BY:
        return REVIEWED_BY_EDEFAULT == null ? reviewedBy != null : !REVIEWED_BY_EDEFAULT.equals(reviewedBy);
      case DbPackage.CUSTOMER_REVIEW_TYPE__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
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
    result.append(" (comment: ");
    result.append(comment);
    result.append(')');
    return result.toString();
  }

} //CustomerReviewTypeImpl
