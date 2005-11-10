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
 * $Id: VideoCassetteImpl.java,v 1.1 2005/11/10 18:55:52 marcelop Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Person;
import org.eclipse.emf.examples.extlibrary.VideoCassette;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Video Cassette</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.VideoCassetteImpl#getCast <em>Cast</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VideoCassetteImpl extends AudoVisualItemImpl implements VideoCassette
{
  /**
   * The cached value of the '{@link #getCast() <em>Cast</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCast()
   * @generated
   * @ordered
   */
  protected EList cast = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VideoCassetteImpl()
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
    return EXTLibraryPackage.eINSTANCE.getVideoCassette();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getCast()
  {
    if (cast == null)
    {
      cast = new EObjectResolvingEList(Person.class, this, EXTLibraryPackage.VIDEO_CASSETTE__CAST);
    }
    return cast;
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
      case EXTLibraryPackage.VIDEO_CASSETTE__PUBLICATION_DATE:
        return getPublicationDate();
      case EXTLibraryPackage.VIDEO_CASSETTE__COPIES:
        return new Integer(getCopies());
      case EXTLibraryPackage.VIDEO_CASSETTE__BORROWERS:
        return getBorrowers();
      case EXTLibraryPackage.VIDEO_CASSETTE__TITLE:
        return getTitle();
      case EXTLibraryPackage.VIDEO_CASSETTE__MINUTES_LENGTH:
        return new Integer(getMinutesLength());
      case EXTLibraryPackage.VIDEO_CASSETTE__DAMAGED:
        return isDamaged() ? Boolean.TRUE : Boolean.FALSE;
      case EXTLibraryPackage.VIDEO_CASSETTE__CAST:
        return getCast();
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
      case EXTLibraryPackage.VIDEO_CASSETTE__PUBLICATION_DATE:
        setPublicationDate((Date)newValue);
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__COPIES:
        setCopies(((Integer)newValue).intValue());
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__BORROWERS:
        getBorrowers().clear();
        getBorrowers().addAll((Collection)newValue);
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__TITLE:
        setTitle((String)newValue);
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__MINUTES_LENGTH:
        setMinutesLength(((Integer)newValue).intValue());
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__DAMAGED:
        setDamaged(((Boolean)newValue).booleanValue());
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__CAST:
        getCast().clear();
        getCast().addAll((Collection)newValue);
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
      case EXTLibraryPackage.VIDEO_CASSETTE__PUBLICATION_DATE:
        setPublicationDate(PUBLICATION_DATE_EDEFAULT);
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__COPIES:
        setCopies(COPIES_EDEFAULT);
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__BORROWERS:
        getBorrowers().clear();
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__TITLE:
        setTitle(TITLE_EDEFAULT);
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__MINUTES_LENGTH:
        setMinutesLength(MINUTES_LENGTH_EDEFAULT);
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__DAMAGED:
        setDamaged(DAMAGED_EDEFAULT);
        return;
      case EXTLibraryPackage.VIDEO_CASSETTE__CAST:
        getCast().clear();
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
      case EXTLibraryPackage.VIDEO_CASSETTE__PUBLICATION_DATE:
        return PUBLICATION_DATE_EDEFAULT == null ? publicationDate != null : !PUBLICATION_DATE_EDEFAULT.equals(publicationDate);
      case EXTLibraryPackage.VIDEO_CASSETTE__COPIES:
        return copies != COPIES_EDEFAULT;
      case EXTLibraryPackage.VIDEO_CASSETTE__BORROWERS:
        return borrowers != null && !borrowers.isEmpty();
      case EXTLibraryPackage.VIDEO_CASSETTE__TITLE:
        return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
      case EXTLibraryPackage.VIDEO_CASSETTE__MINUTES_LENGTH:
        return minutesLength != MINUTES_LENGTH_EDEFAULT;
      case EXTLibraryPackage.VIDEO_CASSETTE__DAMAGED:
        return ((eFlags & DAMAGED_EFLAG) != 0) != DAMAGED_EDEFAULT;
      case EXTLibraryPackage.VIDEO_CASSETTE__CAST:
        return cast != null && !cast.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

} //VideoCassetteImpl
