/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.test.common.reification.Link;
import org.eclipse.emf.test.common.reification.ReificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class LinkImpl extends BidirectionalImpl<Link> implements Link
{
  /**
   * The empty value for the '{@link #getValues() <em>Values</em>}' array accessor.
   * This is specialized for the more specific element type known in this context.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValues()
   * @generated
   * @ordered
   */
  protected static final Link[] VALUES_EEMPTY_ARRAY = new Link [0];

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LinkImpl()
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
    return ReificationPackage.Literals.LINK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * This is specialized for the more specific type known in this context.
   * @generated
   */
  @Override
  public NotificationChain basicSetIn(Link newIn, NotificationChain msgs)
  {
    return super.basicSetIn(newIn, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * This is specialized for the more specific type known in this context.
   * @generated
   */
  @Override
  public NotificationChain basicSetOut(Link newOut, NotificationChain msgs)
  {
    return super.basicSetOut(newOut, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * This is specialized for the more specific type known in this context.
   * @generated
   */
  @Override
  public void setValue(Link newValue)
  {
    super.setValue(newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * This is specialized for the more specific element type known in this context.
   * @generated
   */
  @Override
  public Link[] getValues()
  {
    if (values == null || values.isEmpty()) return VALUES_EEMPTY_ARRAY;
    BasicEList<Link> list = (BasicEList<Link>)values;
    list.shrink();
    return (Link[])list.data();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * This is specialized for the more specific element type known in this context.
   * @generated
   */
  @Override
  public EList<Link> getValuesList()
  {
    if (values == null)
    {
      values = new EObjectResolvingEList<Link>(Link.class, this, ReificationPackage.LINK__VALUES);
    }
    return values;
  }

} //LinkImpl
