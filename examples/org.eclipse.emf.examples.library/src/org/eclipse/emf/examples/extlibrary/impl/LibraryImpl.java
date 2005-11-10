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
 * $Id: LibraryImpl.java,v 1.1 2005/11/10 18:55:52 marcelop Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Item;
import org.eclipse.emf.examples.extlibrary.Library;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getWriters <em>Writers</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getEmployees <em>Employees</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getBorrowers <em>Borrowers</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getStock <em>Stock</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getBooks <em>Books</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getBranches <em>Branches</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getParentBranch <em>Parent Branch</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getPeople <em>People</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LibraryImpl extends EObjectImpl implements Library
{
  /**
   * The default value of the '{@link #getAddress() <em>Address</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddress()
   * @generated
   * @ordered
   */
  protected static final String ADDRESS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAddress() <em>Address</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddress()
   * @generated
   * @ordered
   */
  protected String address = ADDRESS_EDEFAULT;

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
   * The cached value of the '{@link #getStock() <em>Stock</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStock()
   * @generated
   * @ordered
   */
  protected EList stock = null;

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
   * The cached value of the '{@link #getBranches() <em>Branches</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBranches()
   * @generated
   * @ordered
   */
  protected EList branches = null;

  /**
   * The cached value of the '{@link #getPeople() <em>People</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPeople()
   * @generated
   * @ordered
   */
  protected FeatureMap people = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LibraryImpl()
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
    return EXTLibraryPackage.eINSTANCE.getLibrary();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAddress()
  {
    return address;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAddress(String newAddress)
  {
    String oldAddress = address;
    address = newAddress;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.LIBRARY__ADDRESS, oldAddress, address));
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
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.LIBRARY__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getWriters()
  {
    return ((FeatureMap)getPeople()).list(EXTLibraryPackage.eINSTANCE.getLibrary_Writers());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getEmployees()
  {
    return ((FeatureMap)getPeople()).list(EXTLibraryPackage.eINSTANCE.getLibrary_Employees());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getBorrowers()
  {
    return ((FeatureMap)getPeople()).list(EXTLibraryPackage.eINSTANCE.getLibrary_Borrowers());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList getStock()
  {
    if (stock == null)
    {
      // create a custom list implementation that synchronizes its book
      //    content with the "books" subset
      stock = new EObjectContainmentEList(Item.class, this, EXTLibraryPackage.LIBRARY__STOCK)
        {
          private static final long serialVersionUID = 1L;

          protected void didAdd(int index, Object newObject)
          {
            if ((newObject instanceof Book) && !getBooks().contains(newObject))
            {
              // these lists are unordered, so index doesn't matter
              getBooks().add(newObject);
            }
          }

          protected void didRemove(int index, Object oldObject)
          {
            if ((oldObject instanceof Book) && getBooks().contains(oldObject))
            {
              // these lists are unordered, so index doesn't matter
              getBooks().remove(oldObject);
            }
          }

          protected void didSet(int index, Object newObject, Object oldObject)
          {
            didRemove(index, oldObject);
            didAdd(index, newObject);
          }
        };
    }
    return stock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList getBooks()
  {
    if (books == null)
    {
      // create a custom list implementation that synchronizes its content
      //    with the "stock" superset
      books = new EObjectEList(Book.class, this, EXTLibraryPackage.LIBRARY__BOOKS)
        {
          private static final long serialVersionUID = 1L;

          protected void didAdd(int index, Object newObject)
          {
            if (!getStock().contains(newObject))
            {
              // these lists are unordered, so index doesn't matter
              getStock().add(newObject);
            }
          }

          protected void didRemove(int index, Object oldObject)
          {
            if (getStock().contains(oldObject))
            {
              // these lists are unordered, so index doesn't matter
              getStock().remove(oldObject);
            }
          }

          protected void didSet(int index, Object newObject, Object oldObject)
          {
            didRemove(index, oldObject);
            didAdd(index, newObject);
          }
        };
    }

    return books;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getBranches()
  {
    if (branches == null)
    {
      branches = new EObjectContainmentWithInverseEList(Library.class, this, EXTLibraryPackage.LIBRARY__BRANCHES, EXTLibraryPackage.LIBRARY__PARENT_BRANCH);
    }
    return branches;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Library getParentBranch()
  {
    if (eContainerFeatureID != EXTLibraryPackage.LIBRARY__PARENT_BRANCH) return null;
    return (Library)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParentBranch(Library newParentBranch)
  {
    if (newParentBranch != eContainer || (eContainerFeatureID != EXTLibraryPackage.LIBRARY__PARENT_BRANCH && newParentBranch != null))
    {
      if (EcoreUtil.isAncestor(this, newParentBranch))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newParentBranch != null)
        msgs = ((InternalEObject)newParentBranch).eInverseAdd(this, EXTLibraryPackage.LIBRARY__BRANCHES, Library.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newParentBranch, EXTLibraryPackage.LIBRARY__PARENT_BRANCH, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.LIBRARY__PARENT_BRANCH, newParentBranch, newParentBranch));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getPeople()
  {
    if (people == null)
    {
      people = new BasicFeatureMap(this, EXTLibraryPackage.LIBRARY__PEOPLE);
    }
    return people;
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
        case EXTLibraryPackage.LIBRARY__BRANCHES:
          return ((InternalEList)getBranches()).basicAdd(otherEnd, msgs);
        case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, EXTLibraryPackage.LIBRARY__PARENT_BRANCH, msgs);
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
        case EXTLibraryPackage.LIBRARY__WRITERS:
          return ((InternalEList)getWriters()).basicRemove(otherEnd, msgs);
        case EXTLibraryPackage.LIBRARY__EMPLOYEES:
          return ((InternalEList)getEmployees()).basicRemove(otherEnd, msgs);
        case EXTLibraryPackage.LIBRARY__BORROWERS:
          return ((InternalEList)getBorrowers()).basicRemove(otherEnd, msgs);
        case EXTLibraryPackage.LIBRARY__STOCK:
          return ((InternalEList)getStock()).basicRemove(otherEnd, msgs);
        case EXTLibraryPackage.LIBRARY__BRANCHES:
          return ((InternalEList)getBranches()).basicRemove(otherEnd, msgs);
        case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
          return eBasicSetContainer(null, EXTLibraryPackage.LIBRARY__PARENT_BRANCH, msgs);
        case EXTLibraryPackage.LIBRARY__PEOPLE:
          return ((InternalEList)getPeople()).basicRemove(otherEnd, msgs);
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
        case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
          return eContainer.eInverseRemove(this, EXTLibraryPackage.LIBRARY__BRANCHES, Library.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case EXTLibraryPackage.LIBRARY__ADDRESS:
        return getAddress();
      case EXTLibraryPackage.LIBRARY__NAME:
        return getName();
      case EXTLibraryPackage.LIBRARY__WRITERS:
        return getWriters();
      case EXTLibraryPackage.LIBRARY__EMPLOYEES:
        return getEmployees();
      case EXTLibraryPackage.LIBRARY__BORROWERS:
        return getBorrowers();
      case EXTLibraryPackage.LIBRARY__STOCK:
        return getStock();
      case EXTLibraryPackage.LIBRARY__BOOKS:
        return getBooks();
      case EXTLibraryPackage.LIBRARY__BRANCHES:
        return getBranches();
      case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
        return getParentBranch();
      case EXTLibraryPackage.LIBRARY__PEOPLE:
        return getPeople();
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
      case EXTLibraryPackage.LIBRARY__ADDRESS:
        setAddress((String)newValue);
        return;
      case EXTLibraryPackage.LIBRARY__NAME:
        setName((String)newValue);
        return;
      case EXTLibraryPackage.LIBRARY__WRITERS:
        getWriters().clear();
        getWriters().addAll((Collection)newValue);
        return;
      case EXTLibraryPackage.LIBRARY__EMPLOYEES:
        getEmployees().clear();
        getEmployees().addAll((Collection)newValue);
        return;
      case EXTLibraryPackage.LIBRARY__BORROWERS:
        getBorrowers().clear();
        getBorrowers().addAll((Collection)newValue);
        return;
      case EXTLibraryPackage.LIBRARY__STOCK:
        getStock().clear();
        getStock().addAll((Collection)newValue);
        return;
      case EXTLibraryPackage.LIBRARY__BOOKS:
        getBooks().clear();
        getBooks().addAll((Collection)newValue);
        return;
      case EXTLibraryPackage.LIBRARY__BRANCHES:
        getBranches().clear();
        getBranches().addAll((Collection)newValue);
        return;
      case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
        setParentBranch((Library)newValue);
        return;
      case EXTLibraryPackage.LIBRARY__PEOPLE:
        getPeople().clear();
        getPeople().addAll((Collection)newValue);
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
      case EXTLibraryPackage.LIBRARY__ADDRESS:
        setAddress(ADDRESS_EDEFAULT);
        return;
      case EXTLibraryPackage.LIBRARY__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EXTLibraryPackage.LIBRARY__WRITERS:
        getWriters().clear();
        return;
      case EXTLibraryPackage.LIBRARY__EMPLOYEES:
        getEmployees().clear();
        return;
      case EXTLibraryPackage.LIBRARY__BORROWERS:
        getBorrowers().clear();
        return;
      case EXTLibraryPackage.LIBRARY__STOCK:
        getStock().clear();
        return;
      case EXTLibraryPackage.LIBRARY__BOOKS:
        getBooks().clear();
        return;
      case EXTLibraryPackage.LIBRARY__BRANCHES:
        getBranches().clear();
        return;
      case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
        setParentBranch((Library)null);
        return;
      case EXTLibraryPackage.LIBRARY__PEOPLE:
        getPeople().clear();
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
      case EXTLibraryPackage.LIBRARY__ADDRESS:
        return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
      case EXTLibraryPackage.LIBRARY__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EXTLibraryPackage.LIBRARY__WRITERS:
        return !getWriters().isEmpty();
      case EXTLibraryPackage.LIBRARY__EMPLOYEES:
        return !getEmployees().isEmpty();
      case EXTLibraryPackage.LIBRARY__BORROWERS:
        return !getBorrowers().isEmpty();
      case EXTLibraryPackage.LIBRARY__STOCK:
        return stock != null && !stock.isEmpty();
      case EXTLibraryPackage.LIBRARY__BOOKS:
        return books != null && !books.isEmpty();
      case EXTLibraryPackage.LIBRARY__BRANCHES:
        return branches != null && !branches.isEmpty();
      case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
        return getParentBranch() != null;
      case EXTLibraryPackage.LIBRARY__PEOPLE:
        return people != null && !people.isEmpty();
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
    result.append(" (address: "); //$NON-NLS-1$
    result.append(address);
    result.append(", name: "); //$NON-NLS-1$
    result.append(name);
    result.append(", people: "); //$NON-NLS-1$
    result.append(people);
    result.append(')');
    return result.toString();
  }

} //LibraryImpl
