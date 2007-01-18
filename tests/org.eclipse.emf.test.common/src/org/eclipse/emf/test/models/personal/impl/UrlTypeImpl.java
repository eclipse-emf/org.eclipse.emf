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
 * $Id: UrlTypeImpl.java,v 1.2 2007/01/18 22:06:41 marcelop Exp $
 */
package org.eclipse.emf.test.models.personal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.test.models.personal.UrlType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Url Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.UrlTypeImpl#getHref <em>Href</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UrlTypeImpl extends EDataObjectImpl implements UrlType
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The default value of the '{@link #getHref() <em>Href</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHref()
   * @generated
   * @ordered
   */
  protected static final String HREF_EDEFAULT = "http://";

  /**
   * The cached value of the '{@link #getHref() <em>Href</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHref()
   * @generated
   * @ordered
   */
  protected String href = HREF_EDEFAULT;

  /**
   * This is true if the Href attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean hrefESet = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UrlTypeImpl()
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
    return PersonalPackageImpl.Literals.URL_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getHref()
  {
    return href;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHref(String newHref)
  {
    String oldHref = href;
    href = newHref;
    boolean oldHrefESet = hrefESet;
    hrefESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackageImpl.URL_TYPE__HREF, oldHref, href, !oldHrefESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetHref()
  {
    String oldHref = href;
    boolean oldHrefESet = hrefESet;
    href = HREF_EDEFAULT;
    hrefESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, PersonalPackageImpl.URL_TYPE__HREF, oldHref, HREF_EDEFAULT, oldHrefESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetHref()
  {
    return hrefESet;
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
      case PersonalPackageImpl.URL_TYPE__HREF:
        return getHref();
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
      case PersonalPackageImpl.URL_TYPE__HREF:
        setHref((String)newValue);
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
      case PersonalPackageImpl.URL_TYPE__HREF:
        unsetHref();
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
      case PersonalPackageImpl.URL_TYPE__HREF:
        return isSetHref();
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
    result.append(" (href: ");
    if (hrefESet) result.append(href); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //UrlTypeImpl
