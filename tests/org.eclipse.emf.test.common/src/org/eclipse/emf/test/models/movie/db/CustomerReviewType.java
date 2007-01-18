/**
 * <copyright>
 * </copyright>
 *
 * $Id: CustomerReviewType.java,v 1.1 2007/01/18 15:50:14 marcelop Exp $
 */
package org.eclipse.emf.test.models.movie.db;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Customer Review Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.CustomerReviewType#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.movie.db.DBPackage#getCustomerReviewType()
 * @model extendedMetaData="name='customerReviewType' kind='elementOnly'"
 * @generated
 */
public interface CustomerReviewType extends CriticsReviewType
{
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
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getCustomerReviewType_Comment()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='comment'"
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.CustomerReviewType#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

} // CustomerReviewType
