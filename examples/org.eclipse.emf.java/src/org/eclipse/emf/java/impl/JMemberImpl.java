/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: JMemberImpl.java,v 1.10 2009/04/18 11:40:23 emerks Exp $
 */
package org.eclipse.emf.java.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
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
  @Override
  protected EClass eStaticClass()
  {
    return JavaPackage.Literals.JMEMBER;
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
    if (eContainerFeatureID() != JavaPackage.JMEMBER__CONTAINING_TYPE) return null;
    return (JClass)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContainingType(JClass newContainingType, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newContainingType, JavaPackage.JMEMBER__CONTAINING_TYPE, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContainingType(JClass newContainingType)
  {
    if (newContainingType != eInternalContainer() || (eContainerFeatureID() != JavaPackage.JMEMBER__CONTAINING_TYPE && newContainingType != null))
    {
      if (EcoreUtil.isAncestor(this, newContainingType))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newContainingType != null)
        msgs = ((InternalEObject)newContainingType).eInverseAdd(this, JavaPackage.JCLASS__MEMBERS, JClass.class, msgs);
      msgs = basicSetContainingType(newContainingType, msgs);
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
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case JavaPackage.JMEMBER__CONTAINING_TYPE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetContainingType((JClass)otherEnd, msgs);
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
      case JavaPackage.JMEMBER__CONTAINING_TYPE:
        return basicSetContainingType(null, msgs);
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
      case JavaPackage.JMEMBER__CONTAINING_TYPE:
        return eInternalContainer().eInverseRemove(this, JavaPackage.JCLASS__MEMBERS, JClass.class, msgs);
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
      case JavaPackage.JMEMBER__STATIC:
        return isStatic();
      case JavaPackage.JMEMBER__VISIBILITY:
        return getVisibility();
      case JavaPackage.JMEMBER__COMMENT:
        return getComment();
      case JavaPackage.JMEMBER__CONTAINING_TYPE:
        return getContainingType();
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
      case JavaPackage.JMEMBER__STATIC:
        setStatic((Boolean)newValue);
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
      case JavaPackage.JMEMBER__STATIC:
        return static_ != STATIC_EDEFAULT;
      case JavaPackage.JMEMBER__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case JavaPackage.JMEMBER__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case JavaPackage.JMEMBER__CONTAINING_TYPE:
        return getContainingType() != null;
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
