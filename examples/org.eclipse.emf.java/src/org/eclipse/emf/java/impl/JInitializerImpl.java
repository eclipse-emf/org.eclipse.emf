/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JInitializerImpl.java,v 1.1 2004/04/13 02:50:33 marcelop Exp $
 */
package org.eclipse.emf.java.impl;


import org.eclipse.jdt.core.jdom.IDOMInitializer;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JInitializer;
import org.eclipse.emf.java.JVisibility;
import org.eclipse.emf.java.JavaPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JInitializer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.impl.JInitializerImpl#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JInitializerImpl extends JMemberImpl implements JInitializer
{
  /**
   * The default value of the '{@link #getBody() <em>Body</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected static final String BODY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBody() <em>Body</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected String body = BODY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JInitializerImpl()
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
    return JavaPackage.eINSTANCE.getJInitializer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBody()
  {
    return body;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBody(String newBody)
  {
    String oldBody = body;
    body = newBody;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JINITIALIZER__BODY, oldBody, body));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case JavaPackage.JINITIALIZER__CONTAINING_TYPE:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, JavaPackage.JINITIALIZER__CONTAINING_TYPE, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case JavaPackage.JINITIALIZER__CONTAINING_TYPE:
          return eBasicSetContainer(null, JavaPackage.JINITIALIZER__CONTAINING_TYPE, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case JavaPackage.JINITIALIZER__CONTAINING_TYPE:
          return ((InternalEObject)eContainer).eInverseRemove(this, JavaPackage.JCLASS__MEMBERS, JClass.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case JavaPackage.JINITIALIZER__NAME:
        return getName();
      case JavaPackage.JINITIALIZER__JNODE:
        return getJNode();
      case JavaPackage.JINITIALIZER__STATIC:
        return isStatic() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JINITIALIZER__VISIBILITY:
        return getVisibility();
      case JavaPackage.JINITIALIZER__COMMENT:
        return getComment();
      case JavaPackage.JINITIALIZER__CONTAINING_TYPE:
        return getContainingType();
      case JavaPackage.JINITIALIZER__BODY:
        return getBody();
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
      case JavaPackage.JINITIALIZER__NAME:
        setName((String)newValue);
        return;
      case JavaPackage.JINITIALIZER__JNODE:
        setJNode((Object)newValue);
        return;
      case JavaPackage.JINITIALIZER__STATIC:
        setStatic(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JINITIALIZER__VISIBILITY:
        setVisibility((JVisibility)newValue);
        return;
      case JavaPackage.JINITIALIZER__COMMENT:
        setComment((String)newValue);
        return;
      case JavaPackage.JINITIALIZER__CONTAINING_TYPE:
        setContainingType((JClass)newValue);
        return;
      case JavaPackage.JINITIALIZER__BODY:
        setBody((String)newValue);
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
      case JavaPackage.JINITIALIZER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case JavaPackage.JINITIALIZER__JNODE:
        setJNode(JNODE_EDEFAULT);
        return;
      case JavaPackage.JINITIALIZER__STATIC:
        setStatic(STATIC_EDEFAULT);
        return;
      case JavaPackage.JINITIALIZER__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case JavaPackage.JINITIALIZER__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case JavaPackage.JINITIALIZER__CONTAINING_TYPE:
        setContainingType((JClass)null);
        return;
      case JavaPackage.JINITIALIZER__BODY:
        setBody(BODY_EDEFAULT);
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
      case JavaPackage.JINITIALIZER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case JavaPackage.JINITIALIZER__JNODE:
        return JNODE_EDEFAULT == null ? jNode != null : !JNODE_EDEFAULT.equals(jNode);
      case JavaPackage.JINITIALIZER__STATIC:
        return static_ != STATIC_EDEFAULT;
      case JavaPackage.JINITIALIZER__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case JavaPackage.JINITIALIZER__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case JavaPackage.JINITIALIZER__CONTAINING_TYPE:
        return getContainingType() != null;
      case JavaPackage.JINITIALIZER__BODY:
        return BODY_EDEFAULT == null ? body != null : !BODY_EDEFAULT.equals(body);
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
    result.append(" (body: ");
    result.append(body);
    result.append(')');
    return result.toString();
  }

  protected void changeAttribute(Notification notification)
  {
    if (notification.getFeature() == JavaPackage.eINSTANCE.getJModelElement_JNode())
    {
      if (getJNode() != null)
      {
        IDOMInitializer iDOMInitializer = (IDOMInitializer)getJNode();
        setBody(iDOMInitializer.getBody());
      }
    }
  }

} //JInitializerImpl
