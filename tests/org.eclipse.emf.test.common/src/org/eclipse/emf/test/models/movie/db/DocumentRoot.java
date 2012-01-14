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
package org.eclipse.emf.test.models.movie.db;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.emf.test.models.customer.CustomerType;

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
 * @see org.eclipse.emf.test.models.movie.db.DBPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
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
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getDocumentRoot_Mixed()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
   *        extendedMetaData="kind='elementWildcard' name=':mixed'"
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
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getDocumentRoot_XMLNSPrefixMap()
   * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
   *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
   * @generated
   */
  EMap<String, String> getXMLNSPrefixMap();

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
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getDocumentRoot_XSISchemaLocation()
   * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
   *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
   * @generated
   */
  EMap<String, String> getXSISchemaLocation();

  /**
   * Returns the value of the '<em><b>Checked Out By</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Checked Out By</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Checked Out By</em>' reference.
   * @see #setCheckedOutBy(CustomerType)
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getDocumentRoot_CheckedOutBy()
   * @model upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='checkedOutBy' namespace='##targetNamespace'"
   * @generated
   */
  CustomerType getCheckedOutBy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.movie.db.DocumentRoot#getCheckedOutBy <em>Checked Out By</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Checked Out By</em>' reference.
   * @see #getCheckedOutBy()
   * @generated
   */
  void setCheckedOutBy(CustomerType value);

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
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getDocumentRoot_CriticsReview()
   * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='criticsReview' namespace='##targetNamespace'"
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
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getDocumentRoot_CustomerReview()
   * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='customerReview' namespace='##targetNamespace' affiliation='criticsReview'"
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
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getDocumentRoot_Language()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='language' namespace='##targetNamespace'"
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
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getDocumentRoot_MovieDB()
   * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='movieDB' namespace='##targetNamespace'"
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
   * @see org.eclipse.emf.test.models.movie.db.DBPackage#getDocumentRoot_SpecialFeatures()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='specialFeatures' namespace='##targetNamespace'"
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
