/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XAnnotation Directive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XAnnotationDirectiveImpl#getSourceURI <em>Source URI</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XAnnotationDirectiveImpl#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XAnnotationDirectiveImpl extends XNamedElementImpl implements XAnnotationDirective
{
  /**
   * The default value of the '{@link #getSourceURI() <em>Source URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceURI()
   * @generated
   * @ordered
   */
  protected static final String SOURCE_URI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSourceURI() <em>Source URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceURI()
   * @generated
   * @ordered
   */
  protected String sourceURI = SOURCE_URI_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XAnnotationDirectiveImpl()
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
    return XcorePackage.Literals.XANNOTATION_DIRECTIVE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSourceURI()
  {
    return sourceURI;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSourceURI(String newSourceURI)
  {
    String oldSourceURI = sourceURI;
    sourceURI = newSourceURI;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XANNOTATION_DIRECTIVE__SOURCE_URI, oldSourceURI, sourceURI));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XPackage getPackage()
  {
    if (eContainerFeatureID() != XcorePackage.XANNOTATION_DIRECTIVE__PACKAGE) return null;
    return (XPackage)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XcorePackage.XANNOTATION_DIRECTIVE__PACKAGE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, XcorePackage.XANNOTATION_DIRECTIVE__PACKAGE, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XcorePackage.XANNOTATION_DIRECTIVE__PACKAGE:
        return eBasicSetContainer(null, XcorePackage.XANNOTATION_DIRECTIVE__PACKAGE, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case XcorePackage.XANNOTATION_DIRECTIVE__PACKAGE:
        return eInternalContainer().eInverseRemove(this, XcorePackage.XPACKAGE__ANNOTATION_DIRECTIVES, XPackage.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
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
      case XcorePackage.XANNOTATION_DIRECTIVE__SOURCE_URI:
        return getSourceURI();
      case XcorePackage.XANNOTATION_DIRECTIVE__PACKAGE:
        return getPackage();
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
      case XcorePackage.XANNOTATION_DIRECTIVE__SOURCE_URI:
        setSourceURI((String)newValue);
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
      case XcorePackage.XANNOTATION_DIRECTIVE__SOURCE_URI:
        setSourceURI(SOURCE_URI_EDEFAULT);
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
      case XcorePackage.XANNOTATION_DIRECTIVE__SOURCE_URI:
        return SOURCE_URI_EDEFAULT == null ? sourceURI != null : !SOURCE_URI_EDEFAULT.equals(sourceURI);
      case XcorePackage.XANNOTATION_DIRECTIVE__PACKAGE:
        return getPackage() != null;
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
    result.append(" (sourceURI: ");
    result.append(sourceURI);
    result.append(')');
    return result.toString();
  }

} //XAnnotationDirectiveImpl
