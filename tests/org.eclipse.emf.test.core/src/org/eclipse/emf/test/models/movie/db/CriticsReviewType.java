/**
 * <copyright>
 * </copyright>
 *
 * $Id: CriticsReviewType.java,v 1.1 2004/06/16 18:01:17 elena Exp $
 */
package org.eclipse.emf.test.models.movie.db;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Critics Review Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.CriticsReviewType#getRating <em>Rating</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.CriticsReviewType#getReviewedBy <em>Reviewed By</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.movie.db.DbPackage#getCriticsReviewType()
 * @model 
 * @generated
 */
public interface CriticsReviewType extends EObject
{
  /**
   * Returns the value of the '<em><b>Rating</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rating</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rating</em>' attribute.
   * @see #isSetRating()
   * @see #unsetRating()
   * @see #setRating(int)
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getCriticsReviewType_Rating()
   * @model unique="false" unsettable="true" dataType="org.eclipse.emf.test.models.movie.db.RatingType" required="true"
   * @generated
   */
  int getRating();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.CriticsReviewType#getRating <em>Rating</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rating</em>' attribute.
   * @see #isSetRating()
   * @see #unsetRating()
   * @see #getRating()
   * @generated
   */
  void setRating(int value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.movie.db.CriticsReviewType#getRating <em>Rating</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetRating()
   * @see #getRating()
   * @see #setRating(int)
   * @generated
   */
  void unsetRating();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.movie.db.CriticsReviewType#getRating <em>Rating</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Rating</em>' attribute is set.
   * @see #unsetRating()
   * @see #getRating()
   * @see #setRating(int)
   * @generated
   */
  boolean isSetRating();

  /**
   * Returns the value of the '<em><b>Reviewed By</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reviewed By</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reviewed By</em>' attribute.
   * @see #setReviewedBy(String)
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getCriticsReviewType_ReviewedBy()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   * @generated
   */
  String getReviewedBy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.CriticsReviewType#getReviewedBy <em>Reviewed By</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reviewed By</em>' attribute.
   * @see #getReviewedBy()
   * @generated
   */
  void setReviewedBy(String value);

} // CriticsReviewType
