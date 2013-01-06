/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.test.common.reification.Medium;
import org.eclipse.emf.test.common.reification.MediumBidirectional;
import org.eclipse.emf.test.common.reification.ReificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Medium Bidirectional</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.MediumBidirectionalImpl#getContentsList <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MediumBidirectionalImpl<T extends MediumBidirectional<T> & Medium> extends EObjectImpl implements MediumBidirectional<T>
{
  /**
   * The cached value of the '{@link #getContentsList() <em>Contents</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContentsList()
   * @generated
   * @ordered
   */
  protected EList<MediumBidirectional<T>> contents;

  /**
   * The empty value for the '{@link #getContents() <em>Contents</em>}' array accessor.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  @SuppressWarnings("rawtypes")
  protected static final MediumBidirectional[] CONTENTS_EEMPTY_ARRAY = new MediumBidirectional [0];

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MediumBidirectionalImpl()
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
    return ReificationPackage.Literals.MEDIUM_BIDIRECTIONAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public MediumBidirectional<T>[] getContents()
  {
    if (contents == null || contents.isEmpty()) return CONTENTS_EEMPTY_ARRAY;
    BasicEList<MediumBidirectional<T>> list = (BasicEList<MediumBidirectional<T>>)contents;
    list.shrink();
    return (MediumBidirectional<T>[])list.data();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MediumBidirectional<T> getContents(int index)
  {
    return getContentsList().get(index);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getContentsLength()
  {
    return contents == null ? 0 : contents.size();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContents(MediumBidirectional<T>[] newContents)
  {
    ((BasicEList<MediumBidirectional<T>>)getContentsList()).setData(newContents.length, newContents);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContents(int index, MediumBidirectional<T> element)
  {
    getContentsList().set(index, element);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<MediumBidirectional<T>> getContentsList()
  {
    if (contents == null)
    {
      contents = new EObjectResolvingEList<MediumBidirectional<T>>(MediumBidirectional.class, this, ReificationPackage.MEDIUM_BIDIRECTIONAL__CONTENTS);
    }
    return contents;
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
      case ReificationPackage.MEDIUM_BIDIRECTIONAL__CONTENTS:
        return getContentsList();
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
      case ReificationPackage.MEDIUM_BIDIRECTIONAL__CONTENTS:
        getContentsList().clear();
        getContentsList().addAll((Collection<? extends MediumBidirectional<T>>)newValue);
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
      case ReificationPackage.MEDIUM_BIDIRECTIONAL__CONTENTS:
        getContentsList().clear();
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
      case ReificationPackage.MEDIUM_BIDIRECTIONAL__CONTENTS:
        return contents != null && !contents.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //MediumBidirectionalImpl
