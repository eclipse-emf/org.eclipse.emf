/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.lib.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.lib.Address;
import org.eclipse.emf.test.models.lib.Book;
import org.eclipse.emf.test.models.lib.Cafeteria;
import org.eclipse.emf.test.models.lib.LibPackage;
import org.eclipse.emf.test.models.lib.Library;
import org.eclipse.emf.test.models.lib.Person;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.lib.impl.LibraryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.lib.impl.LibraryImpl#getBooks <em>Books</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.lib.impl.LibraryImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.lib.impl.LibraryImpl#getWriters <em>Writers</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.lib.impl.LibraryImpl#getCafeteria <em>Cafeteria</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LibraryImpl extends EObjectImpl implements Library
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
   * The cached value of the '{@link #getBooks() <em>Books</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBooks()
   * @generated
   * @ordered
   */
  protected EList<Book> books;

  /**
   * The cached value of the '{@link #getAddress() <em>Address</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddress()
   * @generated
   * @ordered
   */
  protected Address address;

  /**
   * The cached value of the '{@link #getWriters() <em>Writers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWriters()
   * @generated
   * @ordered
   */
  protected EList<Person> writers;

  /**
   * The cached value of the '{@link #getCafeteria() <em>Cafeteria</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCafeteria()
   * @generated
   * @ordered
   */
  protected Cafeteria cafeteria;

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
  @Override
  protected EClass eStaticClass()
  {
    return LibPackage.Literals.LIBRARY;
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
      eNotify(new ENotificationImpl(this, Notification.SET, LibPackage.LIBRARY__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Book> getBooks()
  {
    if (books == null)
    {
      books = new EObjectContainmentEList.Resolving<Book>(Book.class, this, LibPackage.LIBRARY__BOOKS);
    }
    return books;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Address getAddress()
  {
    if (address != null && address.eIsProxy())
    {
      InternalEObject oldAddress = (InternalEObject)address;
      address = (Address)eResolveProxy(oldAddress);
      if (address != oldAddress)
      {
        InternalEObject newAddress = (InternalEObject)address;
        NotificationChain msgs = oldAddress.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LibPackage.LIBRARY__ADDRESS, null, null);
        if (newAddress.eInternalContainer() == null)
        {
          msgs = newAddress.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LibPackage.LIBRARY__ADDRESS, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LibPackage.LIBRARY__ADDRESS, oldAddress, address));
      }
    }
    return address;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Address basicGetAddress()
  {
    return address;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAddress(Address newAddress, NotificationChain msgs)
  {
    Address oldAddress = address;
    address = newAddress;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LibPackage.LIBRARY__ADDRESS, oldAddress, newAddress);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAddress(Address newAddress)
  {
    if (newAddress != address)
    {
      NotificationChain msgs = null;
      if (address != null)
        msgs = ((InternalEObject)address).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LibPackage.LIBRARY__ADDRESS, null, msgs);
      if (newAddress != null)
        msgs = ((InternalEObject)newAddress).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LibPackage.LIBRARY__ADDRESS, null, msgs);
      msgs = basicSetAddress(newAddress, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibPackage.LIBRARY__ADDRESS, newAddress, newAddress));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Person> getWriters()
  {
    if (writers == null)
    {
      writers = new EObjectContainmentWithInverseEList.Resolving<Person>(Person.class, this, LibPackage.LIBRARY__WRITERS, LibPackage.PERSON__LIBRARY);
    }
    return writers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Cafeteria getCafeteria()
  {
    if (cafeteria != null && cafeteria.eIsProxy())
    {
      InternalEObject oldCafeteria = (InternalEObject)cafeteria;
      cafeteria = (Cafeteria)eResolveProxy(oldCafeteria);
      if (cafeteria != oldCafeteria)
      {
        InternalEObject newCafeteria = (InternalEObject)cafeteria;
        NotificationChain msgs =  oldCafeteria.eInverseRemove(this, LibPackage.CAFETERIA__LIBRARY, Cafeteria.class, null);
        if (newCafeteria.eInternalContainer() == null)
        {
          msgs =  newCafeteria.eInverseAdd(this, LibPackage.CAFETERIA__LIBRARY, Cafeteria.class, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LibPackage.LIBRARY__CAFETERIA, oldCafeteria, cafeteria));
      }
    }
    return cafeteria;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Cafeteria basicGetCafeteria()
  {
    return cafeteria;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCafeteria(Cafeteria newCafeteria, NotificationChain msgs)
  {
    Cafeteria oldCafeteria = cafeteria;
    cafeteria = newCafeteria;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LibPackage.LIBRARY__CAFETERIA, oldCafeteria, newCafeteria);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCafeteria(Cafeteria newCafeteria)
  {
    if (newCafeteria != cafeteria)
    {
      NotificationChain msgs = null;
      if (cafeteria != null)
        msgs = ((InternalEObject)cafeteria).eInverseRemove(this, LibPackage.CAFETERIA__LIBRARY, Cafeteria.class, msgs);
      if (newCafeteria != null)
        msgs = ((InternalEObject)newCafeteria).eInverseAdd(this, LibPackage.CAFETERIA__LIBRARY, Cafeteria.class, msgs);
      msgs = basicSetCafeteria(newCafeteria, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibPackage.LIBRARY__CAFETERIA, newCafeteria, newCafeteria));
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
      case LibPackage.LIBRARY__WRITERS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getWriters()).basicAdd(otherEnd, msgs);
      case LibPackage.LIBRARY__CAFETERIA:
        if (cafeteria != null)
          msgs = ((InternalEObject)cafeteria).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LibPackage.LIBRARY__CAFETERIA, null, msgs);
        return basicSetCafeteria((Cafeteria)otherEnd, msgs);
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
      case LibPackage.LIBRARY__BOOKS:
        return ((InternalEList<?>)getBooks()).basicRemove(otherEnd, msgs);
      case LibPackage.LIBRARY__ADDRESS:
        return basicSetAddress(null, msgs);
      case LibPackage.LIBRARY__WRITERS:
        return ((InternalEList<?>)getWriters()).basicRemove(otherEnd, msgs);
      case LibPackage.LIBRARY__CAFETERIA:
        return basicSetCafeteria(null, msgs);
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
      case LibPackage.LIBRARY__NAME:
        return getName();
      case LibPackage.LIBRARY__BOOKS:
        return getBooks();
      case LibPackage.LIBRARY__ADDRESS:
        if (resolve) return getAddress();
        return basicGetAddress();
      case LibPackage.LIBRARY__WRITERS:
        return getWriters();
      case LibPackage.LIBRARY__CAFETERIA:
        if (resolve) return getCafeteria();
        return basicGetCafeteria();
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
      case LibPackage.LIBRARY__NAME:
        setName((String)newValue);
        return;
      case LibPackage.LIBRARY__BOOKS:
        getBooks().clear();
        getBooks().addAll((Collection<? extends Book>)newValue);
        return;
      case LibPackage.LIBRARY__ADDRESS:
        setAddress((Address)newValue);
        return;
      case LibPackage.LIBRARY__WRITERS:
        getWriters().clear();
        getWriters().addAll((Collection<? extends Person>)newValue);
        return;
      case LibPackage.LIBRARY__CAFETERIA:
        setCafeteria((Cafeteria)newValue);
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
      case LibPackage.LIBRARY__NAME:
        setName(NAME_EDEFAULT);
        return;
      case LibPackage.LIBRARY__BOOKS:
        getBooks().clear();
        return;
      case LibPackage.LIBRARY__ADDRESS:
        setAddress((Address)null);
        return;
      case LibPackage.LIBRARY__WRITERS:
        getWriters().clear();
        return;
      case LibPackage.LIBRARY__CAFETERIA:
        setCafeteria((Cafeteria)null);
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
      case LibPackage.LIBRARY__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case LibPackage.LIBRARY__BOOKS:
        return books != null && !books.isEmpty();
      case LibPackage.LIBRARY__ADDRESS:
        return address != null;
      case LibPackage.LIBRARY__WRITERS:
        return writers != null && !writers.isEmpty();
      case LibPackage.LIBRARY__CAFETERIA:
        return cafeteria != null;
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

} //LibraryImpl
