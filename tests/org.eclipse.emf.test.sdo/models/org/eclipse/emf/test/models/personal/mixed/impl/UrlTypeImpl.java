/**
 * <copyright>
 * </copyright>
 *
 * $Id: UrlTypeImpl.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.test.models.personal.mixed.MixedPackage;
import org.eclipse.emf.test.models.personal.mixed.UrlType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Url Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.UrlTypeImpl#getHref <em>Href</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UrlTypeImpl extends EDataObjectImpl implements UrlType
{
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
  protected EClass eStaticClass()
  {
    return MixedPackage.eINSTANCE.getUrlType();
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
      eNotify(new ENotificationImpl(this, Notification.SET, MixedPackage.URL_TYPE__HREF, oldHref, href, !oldHrefESet));
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
      eNotify(new ENotificationImpl(this, Notification.UNSET, MixedPackage.URL_TYPE__HREF, oldHref, HREF_EDEFAULT, oldHrefESet));
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
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case MixedPackage.URL_TYPE__HREF:
        return getHref();
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
      case MixedPackage.URL_TYPE__HREF:
        setHref((String)newValue);
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
      case MixedPackage.URL_TYPE__HREF:
        unsetHref();
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
      case MixedPackage.URL_TYPE__HREF:
        return isSetHref();
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
    result.append(" (href: ");
    if (hrefESet) result.append(href); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //UrlTypeImpl
