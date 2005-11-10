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
 * $Id: BookOnTapeImpl.java,v 1.1 2005/11/10 18:55:52 marcelop Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.examples.extlibrary.BookOnTape;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Person;
import org.eclipse.emf.examples.extlibrary.Writer;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Book On Tape</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookOnTapeImpl#getReader <em>Reader</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookOnTapeImpl#getAuthor <em>Author</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BookOnTapeImpl extends AudoVisualItemImpl implements BookOnTape
{
  /**
   * The cached value of the '{@link #getReader() <em>Reader</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReader()
   * @generated
   * @ordered
   */
  protected Person reader = null;

  /**
   * The cached value of the '{@link #getAuthor() <em>Author</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAuthor()
   * @generated
   * @ordered
   */
  protected Writer author = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BookOnTapeImpl()
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
    return EXTLibraryPackage.eINSTANCE.getBookOnTape();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Person getReader()
  {
    if (reader != null && reader.eIsProxy())
    {
      Person oldReader = reader;
      reader = (Person)eResolveProxy((InternalEObject)reader);
      if (reader != oldReader)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EXTLibraryPackage.BOOK_ON_TAPE__READER, oldReader, reader));
      }
    }
    return reader;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Person basicGetReader()
  {
    return reader;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReader(Person newReader)
  {
    Person oldReader = reader;
    reader = newReader;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.BOOK_ON_TAPE__READER, oldReader, reader));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Writer getAuthor()
  {
    if (author != null && author.eIsProxy())
    {
      Writer oldAuthor = author;
      author = (Writer)eResolveProxy((InternalEObject)author);
      if (author != oldAuthor)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EXTLibraryPackage.BOOK_ON_TAPE__AUTHOR, oldAuthor, author));
      }
    }
    return author;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Writer basicGetAuthor()
  {
    return author;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAuthor(Writer newAuthor)
  {
    Writer oldAuthor = author;
    author = newAuthor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.BOOK_ON_TAPE__AUTHOR, oldAuthor, author));
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
      case EXTLibraryPackage.BOOK_ON_TAPE__PUBLICATION_DATE:
        return getPublicationDate();
      case EXTLibraryPackage.BOOK_ON_TAPE__COPIES:
        return new Integer(getCopies());
      case EXTLibraryPackage.BOOK_ON_TAPE__BORROWERS:
        return getBorrowers();
      case EXTLibraryPackage.BOOK_ON_TAPE__TITLE:
        return getTitle();
      case EXTLibraryPackage.BOOK_ON_TAPE__MINUTES_LENGTH:
        return new Integer(getMinutesLength());
      case EXTLibraryPackage.BOOK_ON_TAPE__DAMAGED:
        return isDamaged() ? Boolean.TRUE : Boolean.FALSE;
      case EXTLibraryPackage.BOOK_ON_TAPE__READER:
        if (resolve) return getReader();
        return basicGetReader();
      case EXTLibraryPackage.BOOK_ON_TAPE__AUTHOR:
        if (resolve) return getAuthor();
        return basicGetAuthor();
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
      case EXTLibraryPackage.BOOK_ON_TAPE__PUBLICATION_DATE:
        setPublicationDate((Date)newValue);
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__COPIES:
        setCopies(((Integer)newValue).intValue());
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__BORROWERS:
        getBorrowers().clear();
        getBorrowers().addAll((Collection)newValue);
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__TITLE:
        setTitle((String)newValue);
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__MINUTES_LENGTH:
        setMinutesLength(((Integer)newValue).intValue());
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__DAMAGED:
        setDamaged(((Boolean)newValue).booleanValue());
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__READER:
        setReader((Person)newValue);
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__AUTHOR:
        setAuthor((Writer)newValue);
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
      case EXTLibraryPackage.BOOK_ON_TAPE__PUBLICATION_DATE:
        setPublicationDate(PUBLICATION_DATE_EDEFAULT);
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__COPIES:
        setCopies(COPIES_EDEFAULT);
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__BORROWERS:
        getBorrowers().clear();
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__TITLE:
        setTitle(TITLE_EDEFAULT);
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__MINUTES_LENGTH:
        setMinutesLength(MINUTES_LENGTH_EDEFAULT);
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__DAMAGED:
        setDamaged(DAMAGED_EDEFAULT);
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__READER:
        setReader((Person)null);
        return;
      case EXTLibraryPackage.BOOK_ON_TAPE__AUTHOR:
        setAuthor((Writer)null);
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
      case EXTLibraryPackage.BOOK_ON_TAPE__PUBLICATION_DATE:
        return PUBLICATION_DATE_EDEFAULT == null ? publicationDate != null : !PUBLICATION_DATE_EDEFAULT.equals(publicationDate);
      case EXTLibraryPackage.BOOK_ON_TAPE__COPIES:
        return copies != COPIES_EDEFAULT;
      case EXTLibraryPackage.BOOK_ON_TAPE__BORROWERS:
        return borrowers != null && !borrowers.isEmpty();
      case EXTLibraryPackage.BOOK_ON_TAPE__TITLE:
        return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
      case EXTLibraryPackage.BOOK_ON_TAPE__MINUTES_LENGTH:
        return minutesLength != MINUTES_LENGTH_EDEFAULT;
      case EXTLibraryPackage.BOOK_ON_TAPE__DAMAGED:
        return ((eFlags & DAMAGED_EFLAG) != 0) != DAMAGED_EDEFAULT;
      case EXTLibraryPackage.BOOK_ON_TAPE__READER:
        return reader != null;
      case EXTLibraryPackage.BOOK_ON_TAPE__AUTHOR:
        return author != null;
    }
    return eDynamicIsSet(eFeature);
  }

} //BookOnTapeImpl
