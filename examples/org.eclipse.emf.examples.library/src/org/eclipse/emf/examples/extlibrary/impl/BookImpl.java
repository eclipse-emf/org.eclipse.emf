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
 * $Id: BookImpl.java,v 1.1 2005/11/10 18:55:52 marcelop Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.BookCategory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Writer;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookImpl#getPages <em>Pages</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookImpl#getAuthor <em>Author</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BookImpl extends CirculatingItemImpl implements Book
{
  /**
   * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTitle()
   * @generated
   * @ordered
   */
  protected static final String TITLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTitle()
   * @generated
   * @ordered
   */
  protected String title = TITLE_EDEFAULT;

  /**
   * The default value of the '{@link #getPages() <em>Pages</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPages()
   * @generated
   * @ordered
   */
  protected static final int PAGES_EDEFAULT = 100;

  /**
   * The cached value of the '{@link #getPages() <em>Pages</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPages()
   * @generated
   * @ordered
   */
  protected int pages = PAGES_EDEFAULT;

  /**
   * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategory()
   * @generated
   * @ordered
   */
  protected static final BookCategory CATEGORY_EDEFAULT = BookCategory.MYSTERY_LITERAL;

  /**
   * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategory()
   * @generated
   * @ordered
   */
  protected BookCategory category = CATEGORY_EDEFAULT;

  /**
   * The flag representing whether the Category attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int CATEGORY_ESETFLAG = 1 << 8;

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
  protected BookImpl()
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
    return EXTLibraryPackage.eINSTANCE.getBook();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTitle()
  {
    return title;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTitle(String newTitle)
  {
    String oldTitle = title;
    title = newTitle;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.BOOK__TITLE, oldTitle, title));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getPages()
  {
    return pages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPages(int newPages)
  {
    int oldPages = pages;
    pages = newPages;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.BOOK__PAGES, oldPages, pages));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BookCategory getCategory()
  {
    return category;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCategory(BookCategory newCategory)
  {
    BookCategory oldCategory = category;
    category = newCategory == null ? CATEGORY_EDEFAULT : newCategory;
    boolean oldCategoryESet = (eFlags & CATEGORY_ESETFLAG) != 0;
    eFlags |= CATEGORY_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.BOOK__CATEGORY, oldCategory, category, !oldCategoryESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetCategory()
  {
    BookCategory oldCategory = category;
    boolean oldCategoryESet = (eFlags & CATEGORY_ESETFLAG) != 0;
    category = CATEGORY_EDEFAULT;
    eFlags &= ~CATEGORY_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, EXTLibraryPackage.BOOK__CATEGORY, oldCategory, CATEGORY_EDEFAULT, oldCategoryESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetCategory()
  {
    return (eFlags & CATEGORY_ESETFLAG) != 0;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EXTLibraryPackage.BOOK__AUTHOR, oldAuthor, author));
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
  public NotificationChain basicSetAuthor(Writer newAuthor, NotificationChain msgs)
  {
    Writer oldAuthor = author;
    author = newAuthor;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.BOOK__AUTHOR, oldAuthor, newAuthor);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAuthor(Writer newAuthor)
  {
    if (newAuthor != author)
    {
      NotificationChain msgs = null;
      if (author != null)
        msgs = ((InternalEObject)author).eInverseRemove(this, EXTLibraryPackage.WRITER__BOOKS, Writer.class, msgs);
      if (newAuthor != null)
        msgs = ((InternalEObject)newAuthor).eInverseAdd(this, EXTLibraryPackage.WRITER__BOOKS, Writer.class, msgs);
      msgs = basicSetAuthor(newAuthor, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.BOOK__AUTHOR, newAuthor, newAuthor));
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
        case EXTLibraryPackage.BOOK__BORROWERS:
          return ((InternalEList)getBorrowers()).basicAdd(otherEnd, msgs);
        case EXTLibraryPackage.BOOK__AUTHOR:
          if (author != null)
            msgs = ((InternalEObject)author).eInverseRemove(this, EXTLibraryPackage.WRITER__BOOKS, Writer.class, msgs);
          return basicSetAuthor((Writer)otherEnd, msgs);
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
        case EXTLibraryPackage.BOOK__BORROWERS:
          return ((InternalEList)getBorrowers()).basicRemove(otherEnd, msgs);
        case EXTLibraryPackage.BOOK__AUTHOR:
          return basicSetAuthor(null, msgs);
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
      case EXTLibraryPackage.BOOK__PUBLICATION_DATE:
        return getPublicationDate();
      case EXTLibraryPackage.BOOK__COPIES:
        return new Integer(getCopies());
      case EXTLibraryPackage.BOOK__BORROWERS:
        return getBorrowers();
      case EXTLibraryPackage.BOOK__TITLE:
        return getTitle();
      case EXTLibraryPackage.BOOK__PAGES:
        return new Integer(getPages());
      case EXTLibraryPackage.BOOK__CATEGORY:
        return getCategory();
      case EXTLibraryPackage.BOOK__AUTHOR:
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
      case EXTLibraryPackage.BOOK__PUBLICATION_DATE:
        setPublicationDate((Date)newValue);
        return;
      case EXTLibraryPackage.BOOK__COPIES:
        setCopies(((Integer)newValue).intValue());
        return;
      case EXTLibraryPackage.BOOK__BORROWERS:
        getBorrowers().clear();
        getBorrowers().addAll((Collection)newValue);
        return;
      case EXTLibraryPackage.BOOK__TITLE:
        setTitle((String)newValue);
        return;
      case EXTLibraryPackage.BOOK__PAGES:
        setPages(((Integer)newValue).intValue());
        return;
      case EXTLibraryPackage.BOOK__CATEGORY:
        setCategory((BookCategory)newValue);
        return;
      case EXTLibraryPackage.BOOK__AUTHOR:
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
      case EXTLibraryPackage.BOOK__PUBLICATION_DATE:
        setPublicationDate(PUBLICATION_DATE_EDEFAULT);
        return;
      case EXTLibraryPackage.BOOK__COPIES:
        setCopies(COPIES_EDEFAULT);
        return;
      case EXTLibraryPackage.BOOK__BORROWERS:
        getBorrowers().clear();
        return;
      case EXTLibraryPackage.BOOK__TITLE:
        setTitle(TITLE_EDEFAULT);
        return;
      case EXTLibraryPackage.BOOK__PAGES:
        setPages(PAGES_EDEFAULT);
        return;
      case EXTLibraryPackage.BOOK__CATEGORY:
        unsetCategory();
        return;
      case EXTLibraryPackage.BOOK__AUTHOR:
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
      case EXTLibraryPackage.BOOK__PUBLICATION_DATE:
        return PUBLICATION_DATE_EDEFAULT == null ? publicationDate != null : !PUBLICATION_DATE_EDEFAULT.equals(publicationDate);
      case EXTLibraryPackage.BOOK__COPIES:
        return copies != COPIES_EDEFAULT;
      case EXTLibraryPackage.BOOK__BORROWERS:
        return borrowers != null && !borrowers.isEmpty();
      case EXTLibraryPackage.BOOK__TITLE:
        return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
      case EXTLibraryPackage.BOOK__PAGES:
        return pages != PAGES_EDEFAULT;
      case EXTLibraryPackage.BOOK__CATEGORY:
        return isSetCategory();
      case EXTLibraryPackage.BOOK__AUTHOR:
        return author != null;
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
    result.append(" (title: "); //$NON-NLS-1$
    result.append(title);
    result.append(", pages: "); //$NON-NLS-1$
    result.append(pages);
    result.append(", category: "); //$NON-NLS-1$
    if ((eFlags & CATEGORY_ESETFLAG) != 0) result.append(category); else result.append("<unset>"); //$NON-NLS-1$
    result.append(')');
    return result.toString();
  }

} //BookImpl
