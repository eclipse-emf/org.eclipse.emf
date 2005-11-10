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
 * $Id: AudoVisualItemImpl.java,v 1.1 2005/11/10 18:55:52 marcelop Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.examples.extlibrary.AudoVisualItem;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Audo Visual Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.AudoVisualItemImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.AudoVisualItemImpl#getMinutesLength <em>Minutes Length</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.AudoVisualItemImpl#isDamaged <em>Damaged</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AudoVisualItemImpl extends CirculatingItemImpl implements AudoVisualItem
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
   * The default value of the '{@link #getMinutesLength() <em>Minutes Length</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinutesLength()
   * @generated
   * @ordered
   */
  protected static final int MINUTES_LENGTH_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getMinutesLength() <em>Minutes Length</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinutesLength()
   * @generated
   * @ordered
   */
  protected int minutesLength = MINUTES_LENGTH_EDEFAULT;

  /**
   * The default value of the '{@link #isDamaged() <em>Damaged</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDamaged()
   * @generated
   * @ordered
   */
  protected static final boolean DAMAGED_EDEFAULT = false;

  /**
   * The flag representing the value of the '{@link #isDamaged() <em>Damaged</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDamaged()
   * @generated
   * @ordered
   */
  protected static final int DAMAGED_EFLAG = 1 << 8;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AudoVisualItemImpl()
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
    return EXTLibraryPackage.eINSTANCE.getAudoVisualItem();
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
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.AUDO_VISUAL_ITEM__TITLE, oldTitle, title));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getMinutesLength()
  {
    return minutesLength;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMinutesLength(int newMinutesLength)
  {
    int oldMinutesLength = minutesLength;
    minutesLength = newMinutesLength;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.AUDO_VISUAL_ITEM__MINUTES_LENGTH, oldMinutesLength, minutesLength));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDamaged()
  {
    return (eFlags & DAMAGED_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDamaged(boolean newDamaged)
  {
    boolean oldDamaged = (eFlags & DAMAGED_EFLAG) != 0;
    if (newDamaged) eFlags |= DAMAGED_EFLAG; else eFlags &= ~DAMAGED_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.AUDO_VISUAL_ITEM__DAMAGED, oldDamaged, newDamaged));
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
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__PUBLICATION_DATE:
        return getPublicationDate();
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__COPIES:
        return new Integer(getCopies());
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__BORROWERS:
        return getBorrowers();
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__TITLE:
        return getTitle();
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__MINUTES_LENGTH:
        return new Integer(getMinutesLength());
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__DAMAGED:
        return isDamaged() ? Boolean.TRUE : Boolean.FALSE;
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
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__PUBLICATION_DATE:
        setPublicationDate((Date)newValue);
        return;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__COPIES:
        setCopies(((Integer)newValue).intValue());
        return;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__BORROWERS:
        getBorrowers().clear();
        getBorrowers().addAll((Collection)newValue);
        return;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__TITLE:
        setTitle((String)newValue);
        return;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__MINUTES_LENGTH:
        setMinutesLength(((Integer)newValue).intValue());
        return;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__DAMAGED:
        setDamaged(((Boolean)newValue).booleanValue());
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
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__PUBLICATION_DATE:
        setPublicationDate(PUBLICATION_DATE_EDEFAULT);
        return;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__COPIES:
        setCopies(COPIES_EDEFAULT);
        return;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__BORROWERS:
        getBorrowers().clear();
        return;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__TITLE:
        setTitle(TITLE_EDEFAULT);
        return;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__MINUTES_LENGTH:
        setMinutesLength(MINUTES_LENGTH_EDEFAULT);
        return;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__DAMAGED:
        setDamaged(DAMAGED_EDEFAULT);
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
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__PUBLICATION_DATE:
        return PUBLICATION_DATE_EDEFAULT == null ? publicationDate != null : !PUBLICATION_DATE_EDEFAULT.equals(publicationDate);
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__COPIES:
        return copies != COPIES_EDEFAULT;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__BORROWERS:
        return borrowers != null && !borrowers.isEmpty();
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__TITLE:
        return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__MINUTES_LENGTH:
        return minutesLength != MINUTES_LENGTH_EDEFAULT;
      case EXTLibraryPackage.AUDO_VISUAL_ITEM__DAMAGED:
        return ((eFlags & DAMAGED_EFLAG) != 0) != DAMAGED_EDEFAULT;
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
    result.append(", minutesLength: "); //$NON-NLS-1$
    result.append(minutesLength);
    result.append(", damaged: "); //$NON-NLS-1$
    result.append((eFlags & DAMAGED_EFLAG) != 0);
    result.append(')');
    return result.toString();
  }

} //AudoVisualItemImpl
