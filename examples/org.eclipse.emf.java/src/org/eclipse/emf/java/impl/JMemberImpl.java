/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JMemberImpl.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.java.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JMember;
import org.eclipse.emf.java.JVisibility;
import org.eclipse.emf.java.JavaPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JMember</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.impl.JMemberImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMemberImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMemberImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMemberImpl#getContainingType <em>Containing Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class JMemberImpl extends JModelElementImpl implements JMember
{
  /**
   * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStatic()
   * @generated
   * @ordered
   */
  protected static final boolean STATIC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStatic()
   * @generated
   * @ordered
   */
  protected boolean static_ = STATIC_EDEFAULT;

  /**
   * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
  protected static final JVisibility VISIBILITY_EDEFAULT = JVisibility.PUBLIC_LITERAL;

  /**
   * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
  protected JVisibility visibility = VISIBILITY_EDEFAULT;

  /**
   * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected static final String COMMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected String comment = COMMENT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JMemberImpl()
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
    return JavaPackage.eINSTANCE.getJMember();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isStatic()
  {
    return static_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStatic(boolean newStatic)
  {
    boolean oldStatic = static_;
    static_ = newStatic;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMEMBER__STATIC, oldStatic, static_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JVisibility getVisibility()
  {
    return visibility;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVisibility(JVisibility newVisibility)
  {
    JVisibility oldVisibility = visibility;
    visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMEMBER__VISIBILITY, oldVisibility, visibility));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    String oldComment = comment;
    comment = newComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMEMBER__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JClass getContainingType()
  {
    if (eContainerFeatureID != JavaPackage.JMEMBER__CONTAINING_TYPE) return null;
    return (JClass)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContainingType(JClass newContainingType)
  {
    if (newContainingType != eContainer || (eContainerFeatureID != JavaPackage.JMEMBER__CONTAINING_TYPE && newContainingType != null))
    {
      if (EcoreUtil.isAncestor(this, newContainingType))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newContainingType != null)
        msgs = ((InternalEObject)newContainingType).eInverseAdd(this, JavaPackage.JCLASS__MEMBERS, JClass.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newContainingType, JavaPackage.JMEMBER__CONTAINING_TYPE, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMEMBER__CONTAINING_TYPE, newContainingType, newContainingType));
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
        case JavaPackage.JMEMBER__CONTAINING_TYPE:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, JavaPackage.JMEMBER__CONTAINING_TYPE, msgs);
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
        case JavaPackage.JMEMBER__CONTAINING_TYPE:
          return eBasicSetContainer(null, JavaPackage.JMEMBER__CONTAINING_TYPE, msgs);
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
        case JavaPackage.JMEMBER__CONTAINING_TYPE:
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
      case JavaPackage.JMEMBER__NAME:
        return getName();
      case JavaPackage.JMEMBER__JNODE:
        return getJNode();
      case JavaPackage.JMEMBER__STATIC:
        return isStatic() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JMEMBER__VISIBILITY:
        return getVisibility();
      case JavaPackage.JMEMBER__COMMENT:
        return getComment();
      case JavaPackage.JMEMBER__CONTAINING_TYPE:
        return getContainingType();
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
      case JavaPackage.JMEMBER__NAME:
        setName((String)newValue);
        return;
      case JavaPackage.JMEMBER__JNODE:
        setJNode((Object)newValue);
        return;
      case JavaPackage.JMEMBER__STATIC:
        setStatic(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JMEMBER__VISIBILITY:
        setVisibility((JVisibility)newValue);
        return;
      case JavaPackage.JMEMBER__COMMENT:
        setComment((String)newValue);
        return;
      case JavaPackage.JMEMBER__CONTAINING_TYPE:
        setContainingType((JClass)newValue);
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
      case JavaPackage.JMEMBER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case JavaPackage.JMEMBER__JNODE:
        setJNode(JNODE_EDEFAULT);
        return;
      case JavaPackage.JMEMBER__STATIC:
        setStatic(STATIC_EDEFAULT);
        return;
      case JavaPackage.JMEMBER__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case JavaPackage.JMEMBER__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case JavaPackage.JMEMBER__CONTAINING_TYPE:
        setContainingType((JClass)null);
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
      case JavaPackage.JMEMBER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case JavaPackage.JMEMBER__JNODE:
        return JNODE_EDEFAULT == null ? jNode != null : !JNODE_EDEFAULT.equals(jNode);
      case JavaPackage.JMEMBER__STATIC:
        return static_ != STATIC_EDEFAULT;
      case JavaPackage.JMEMBER__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case JavaPackage.JMEMBER__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case JavaPackage.JMEMBER__CONTAINING_TYPE:
        return getContainingType() != null;
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
    result.append(" (static: ");
    result.append(static_);
    result.append(", visibility: ");
    result.append(visibility);
    result.append(", comment: ");
    result.append(comment);
    result.append(')');
    return result.toString();
  }

} //JMemberImpl
