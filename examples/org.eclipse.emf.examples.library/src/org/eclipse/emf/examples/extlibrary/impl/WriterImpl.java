/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: WriterImpl.java,v 1.1 2005/11/10 18:55:52 marcelop Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Writer;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Writer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.WriterImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.WriterImpl#getBooks <em>Books</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WriterImpl extends PersonImpl implements Writer
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
   * The cached value of the '{@link #getBooks() <em>Books</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBooks()
   * @generated
   * @ordered
   */
  protected EList books = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WriterImpl()
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
    return EXTLibraryPackage.eINSTANCE.getWriter();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getName()
  {
    if (getFirstName() == null)
    {
      if (getLastName() == null)
      {
        return ""; //$NON-NLS-1$
      }
      else
      {
        return getLastName();
      }
    }
    else if (getLastName() == null)
    {
      return getFirstName();
    }
    else
    {
      StringBuffer result = new StringBuffer();

      result.append(getFirstName()).append(' ').append(getLastName());

      return result.toString();
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setName(String newName)
  {
    if (newName == null || newName.length() == 0)
    {
      setFirstName(null);
      setLastName(null);
    }
    else
    {
      int comma = newName.indexOf(',');
      if (comma < 0)
      {
        comma = newName.indexOf(' ');
      }

      if (comma >= 0)
      {
        setFirstName(newName.substring(0, comma).trim());
        setLastName(newName.substring(comma + 1).trim());
      }
      else
      {
        setFirstName(newName);
        setLastName(null);
      }
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getBooks()
  {
    if (books == null)
    {
      books = new EObjectWithInverseResolvingEList(Book.class, this, EXTLibraryPackage.WRITER__BOOKS, EXTLibraryPackage.BOOK__AUTHOR);
    }
    return books;
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
        case EXTLibraryPackage.WRITER__BOOKS:
          return ((InternalEList)getBooks()).basicAdd(otherEnd, msgs);
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
        case EXTLibraryPackage.WRITER__BOOKS:
          return ((InternalEList)getBooks()).basicRemove(otherEnd, msgs);
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
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case EXTLibraryPackage.WRITER__ADDRESS:
        return getAddress();
      case EXTLibraryPackage.WRITER__FIRST_NAME:
        return getFirstName();
      case EXTLibraryPackage.WRITER__LAST_NAME:
        return getLastName();
      case EXTLibraryPackage.WRITER__NAME:
        return getName();
      case EXTLibraryPackage.WRITER__BOOKS:
        return getBooks();
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
      case EXTLibraryPackage.WRITER__ADDRESS:
        setAddress((String)newValue);
        return;
      case EXTLibraryPackage.WRITER__FIRST_NAME:
        setFirstName((String)newValue);
        return;
      case EXTLibraryPackage.WRITER__LAST_NAME:
        setLastName((String)newValue);
        return;
      case EXTLibraryPackage.WRITER__NAME:
        setName((String)newValue);
        return;
      case EXTLibraryPackage.WRITER__BOOKS:
        getBooks().clear();
        getBooks().addAll((Collection)newValue);
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
      case EXTLibraryPackage.WRITER__ADDRESS:
        setAddress(ADDRESS_EDEFAULT);
        return;
      case EXTLibraryPackage.WRITER__FIRST_NAME:
        setFirstName(FIRST_NAME_EDEFAULT);
        return;
      case EXTLibraryPackage.WRITER__LAST_NAME:
        setLastName(LAST_NAME_EDEFAULT);
        return;
      case EXTLibraryPackage.WRITER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EXTLibraryPackage.WRITER__BOOKS:
        getBooks().clear();
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
      case EXTLibraryPackage.WRITER__ADDRESS:
        return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
      case EXTLibraryPackage.WRITER__FIRST_NAME:
        return FIRST_NAME_EDEFAULT == null ? firstName != null : !FIRST_NAME_EDEFAULT.equals(firstName);
      case EXTLibraryPackage.WRITER__LAST_NAME:
        return LAST_NAME_EDEFAULT == null ? lastName != null : !LAST_NAME_EDEFAULT.equals(lastName);
      case EXTLibraryPackage.WRITER__NAME:
        return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
      case EXTLibraryPackage.WRITER__BOOKS:
        return books != null && !books.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

} //WriterImpl
