/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: CirculatingItemImpl.java,v 1.5 2008/12/22 14:25:22 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.examples.extlibrary.Borrower;
import org.eclipse.emf.examples.extlibrary.CirculatingItem;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Lendable;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Circulating Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.CirculatingItemImpl#getCopies <em>Copies</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.CirculatingItemImpl#getBorrowers <em>Borrowers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CirculatingItemImpl extends ItemImpl implements CirculatingItem
{
  /**
   * The default value of the '{@link #getCopies() <em>Copies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCopies()
   * @generated
   * @ordered
   */
  protected static final int COPIES_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getCopies() <em>Copies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCopies()
   * @generated
   * @ordered
   */
  protected int copies = COPIES_EDEFAULT;

  /**
   * The cached value of the '{@link #getBorrowers() <em>Borrowers</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBorrowers()
   * @generated
   * @ordered
   */
  protected EList<Borrower> borrowers;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CirculatingItemImpl()
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
    return EXTLibraryPackage.Literals.CIRCULATING_ITEM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getCopies()
  {
    return copies;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCopies(int newCopies)
  {
    int oldCopies = copies;
    copies = newCopies;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.CIRCULATING_ITEM__COPIES, oldCopies, copies));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Borrower> getBorrowers()
  {
    if (borrowers == null)
    {
      borrowers = new EObjectWithInverseResolvingEList.ManyInverse<Borrower>(Borrower.class, this, EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS, EXTLibraryPackage.BORROWER__BORROWED);
    }
    return borrowers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getBorrowers()).basicAdd(otherEnd, msgs);
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
      case EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS:
        return ((InternalEList<?>)getBorrowers()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case EXTLibraryPackage.CIRCULATING_ITEM__COPIES:
        return getCopies();
      case EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS:
        return getBorrowers();
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
      case EXTLibraryPackage.CIRCULATING_ITEM__COPIES:
        setCopies((Integer)newValue);
        return;
      case EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS:
        getBorrowers().clear();
        getBorrowers().addAll((Collection<? extends Borrower>)newValue);
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
      case EXTLibraryPackage.CIRCULATING_ITEM__COPIES:
        setCopies(COPIES_EDEFAULT);
        return;
      case EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS:
        getBorrowers().clear();
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
      case EXTLibraryPackage.CIRCULATING_ITEM__COPIES:
        return copies != COPIES_EDEFAULT;
      case EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS:
        return borrowers != null && !borrowers.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == Lendable.class)
    {
      switch (derivedFeatureID)
      {
        case EXTLibraryPackage.CIRCULATING_ITEM__COPIES: return EXTLibraryPackage.LENDABLE__COPIES;
        case EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS: return EXTLibraryPackage.LENDABLE__BORROWERS;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == Lendable.class)
    {
      switch (baseFeatureID)
      {
        case EXTLibraryPackage.LENDABLE__COPIES: return EXTLibraryPackage.CIRCULATING_ITEM__COPIES;
        case EXTLibraryPackage.LENDABLE__BORROWERS: return EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
    result.append(" (copies: "); //$NON-NLS-1$
    result.append(copies);
    result.append(')');
    return result.toString();
  }

} //CirculatingItemImpl
