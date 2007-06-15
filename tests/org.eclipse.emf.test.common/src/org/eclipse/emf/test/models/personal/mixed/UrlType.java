/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: UrlType.java,v 1.3 2007/06/15 21:22:17 emerks Exp $
 */
package org.eclipse.emf.test.models.personal.mixed;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Url Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.UrlType#getHref <em>Href</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='url_._type' kind='empty'"
 * @generated
 */
public interface UrlType
{
  /**
   * Returns the value of the '<em><b>Href</b></em>' attribute.
   * The default value is <code>"http://"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Href</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Href</em>' attribute.
   * @see #isSetHref()
   * @see #unsetHref()
   * @see #setHref(String)
   * @model default="http://" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
   *        extendedMetaData="kind='attribute' name='href'"
   * @generated
   */
  String getHref();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.personal.mixed.UrlType#getHref <em>Href</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Href</em>' attribute.
   * @see #isSetHref()
   * @see #unsetHref()
   * @see #getHref()
   * @generated
   */
  void setHref(String value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.personal.mixed.UrlType#getHref <em>Href</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetHref()
   * @see #getHref()
   * @see #setHref(String)
   * @generated
   */
  void unsetHref();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.personal.mixed.UrlType#getHref <em>Href</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Href</em>' attribute is set.
   * @see #unsetHref()
   * @see #getHref()
   * @see #setHref(String)
   * @generated
   */
  boolean isSetHref();

} // UrlType
