/**
 * <copyright>
 * </copyright>
 *
 * $Id: CustomerReviewType.java,v 1.1.2.1 2005/01/14 22:56:18 nickb Exp $
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
 * @see org.eclipse.emf.test.models.movie.db.DbPackage#getCustomerReviewType()
 * @model 
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
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getCustomerReviewType_Comment()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
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
