/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.test.models.movie.db;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getCheckedOutBy <em>Checked Out By</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getCriticsReview <em>Critics Review</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getCustomerReview <em>Customer Review</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getMovieDB <em>Movie DB</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getSpecialFeatures <em>Special Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.movie.db.DbPackage#getDocumentRoot()
 * @model 
 * @generated
 */
public interface DocumentRoot extends EObject
{
  /**
   * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mixed</em>' attribute list.
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getDocumentRoot_Mixed()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
   * @generated
   */
  FeatureMap getMixed();

  /**
   * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
   * The key is of type {@link java.lang.String},
   * and the value is of type {@link java.lang.String},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>XMLNS Prefix Map</em>' map.
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getDocumentRoot_XMLNSPrefixMap()
   * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String" transient="true"
   * @generated
   */
  EMap getXMLNSPrefixMap();

  /**
   * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
   * The key is of type {@link java.lang.String},
   * and the value is of type {@link java.lang.String},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>XSI Schema Location</em>' map.
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getDocumentRoot_XSISchemaLocation()
   * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String" transient="true"
   * @generated
   */
  EMap getXSISchemaLocation();

  /**
   * Returns the value of the '<em><b>Checked Out By</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Checked Out By</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Checked Out By</em>' attribute.
   * @see #setCheckedOutBy(String)
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getDocumentRoot_CheckedOutBy()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnyURI" transient="true" volatile="true" derived="true"
   * @generated
   */
  String getCheckedOutBy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getCheckedOutBy <em>Checked Out By</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Checked Out By</em>' attribute.
   * @see #getCheckedOutBy()
   * @generated
   */
  void setCheckedOutBy(String value);

  /**
   * Returns the value of the '<em><b>Critics Review</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Critics Review</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Critics Review</em>' containment reference.
   * @see #setCriticsReview(CriticsReviewType)
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getDocumentRoot_CriticsReview()
   * @model containment="true" resolveProxies="false" transient="true" volatile="true" derived="true"
   * @generated
   */
  CriticsReviewType getCriticsReview();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getCriticsReview <em>Critics Review</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Critics Review</em>' containment reference.
   * @see #getCriticsReview()
   * @generated
   */
  void setCriticsReview(CriticsReviewType value);

  /**
   * Returns the value of the '<em><b>Customer Review</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Customer Review</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Customer Review</em>' containment reference.
   * @see #setCustomerReview(CustomerReviewType)
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getDocumentRoot_CustomerReview()
   * @model containment="true" resolveProxies="false" transient="true" volatile="true" derived="true"
   * @generated
   */
  CustomerReviewType getCustomerReview();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getCustomerReview <em>Customer Review</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Customer Review</em>' containment reference.
   * @see #getCustomerReview()
   * @generated
   */
  void setCustomerReview(CustomerReviewType value);

  /**
   * Returns the value of the '<em><b>Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Language</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Language</em>' attribute.
   * @see #setLanguage(String)
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getDocumentRoot_Language()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
   * @generated
   */
  String getLanguage();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getLanguage <em>Language</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Language</em>' attribute.
   * @see #getLanguage()
   * @generated
   */
  void setLanguage(String value);

  /**
   * Returns the value of the '<em><b>Movie DB</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Movie DB</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Movie DB</em>' containment reference.
   * @see #setMovieDB(MovieDBType)
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getDocumentRoot_MovieDB()
   * @model containment="true" resolveProxies="false" transient="true" volatile="true" derived="true"
   * @generated
   */
  MovieDBType getMovieDB();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getMovieDB <em>Movie DB</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Movie DB</em>' containment reference.
   * @see #getMovieDB()
   * @generated
   */
  void setMovieDB(MovieDBType value);

  /**
   * Returns the value of the '<em><b>Special Features</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Special Features</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Special Features</em>' attribute.
   * @see #setSpecialFeatures(String)
   * @see org.eclipse.emf.test.models.movie.db.DbPackage#getDocumentRoot_SpecialFeatures()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
   * @generated
   */
  String getSpecialFeatures();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getSpecialFeatures <em>Special Features</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Special Features</em>' attribute.
   * @see #getSpecialFeatures()
   * @generated
   */
  void setSpecialFeatures(String value);

} // DocumentRoot
