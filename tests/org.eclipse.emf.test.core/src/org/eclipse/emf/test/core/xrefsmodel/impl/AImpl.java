/**
 * Copyright (c) 2014 CEA and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA - Initial API and implementation
 *
 */
package org.eclipse.emf.test.core.xrefsmodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.test.core.xrefsmodel.A;
import org.eclipse.emf.test.core.xrefsmodel.XRefsModelPackage;
import org.eclipse.emf.test.core.xrefsmodel.util.XRefsModelUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>A</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.xrefsmodel.impl.AImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsmodel.impl.AImpl#getOthers <em>Others</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsmodel.impl.AImpl#getAllOthers <em>All Others</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsmodel.impl.AImpl#getNonOthers <em>Non Others</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AImpl extends MinimalEObjectImpl.Container implements A
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getOthers() <em>Others</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOthers()
   * @generated
   * @ordered
   */
  protected EList<A> others;

  /**
   * The cached value of the '{@link #getNonOthers() <em>Non Others</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNonOthers()
   * @generated
   * @ordered
   */
  protected EList<A> nonOthers;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AImpl()
  {
    super();
  }

  @Override
  public EList<EObject> eCrossReferences()
  {
    return XRefsModelUtil.getCrossReferences(super.eCrossReferences());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return XRefsModelPackage.Literals.A;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XRefsModelPackage.A__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<A> getOthers()
  {
    if (others == null)
    {
      others = new EObjectResolvingEList<A>(A.class, this, XRefsModelPackage.A__OTHERS);
    }
    return others;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<A> getAllOthers()
  {
    return XRefsModelUtil.getAllOthers(this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<A> getNonOthers()
  {
    if (nonOthers == null)
    {
      nonOthers = new EObjectResolvingEList<A>(A.class, this, XRefsModelPackage.A__NON_OTHERS);
    }
    return nonOthers;
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
      case XRefsModelPackage.A__NAME:
        return getName();
      case XRefsModelPackage.A__OTHERS:
        return getOthers();
      case XRefsModelPackage.A__ALL_OTHERS:
        return getAllOthers();
      case XRefsModelPackage.A__NON_OTHERS:
        return getNonOthers();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XRefsModelPackage.A__NAME:
        setName((String)newValue);
        return;
      case XRefsModelPackage.A__OTHERS:
        getOthers().clear();
        getOthers().addAll((Collection<? extends A>)newValue);
        return;
      case XRefsModelPackage.A__NON_OTHERS:
        getNonOthers().clear();
        getNonOthers().addAll((Collection<? extends A>)newValue);
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
      case XRefsModelPackage.A__NAME:
        setName(NAME_EDEFAULT);
        return;
      case XRefsModelPackage.A__OTHERS:
        getOthers().clear();
        return;
      case XRefsModelPackage.A__NON_OTHERS:
        getNonOthers().clear();
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
      case XRefsModelPackage.A__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case XRefsModelPackage.A__OTHERS:
        return others != null && !others.isEmpty();
      case XRefsModelPackage.A__ALL_OTHERS:
        return !getAllOthers().isEmpty();
      case XRefsModelPackage.A__NON_OTHERS:
        return nonOthers != null && !nonOthers.isEmpty();
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //AImpl
