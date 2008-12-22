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
 * $Id: AudioVisualItemImpl.java,v 1.3 2008/12/22 14:25:21 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.examples.extlibrary.AudioVisualItem;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Audo Visual Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.AudioVisualItemImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.AudioVisualItemImpl#getMinutesLength <em>Minutes Length</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.AudioVisualItemImpl#isDamaged <em>Damaged</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AudioVisualItemImpl extends CirculatingItemImpl implements AudioVisualItem
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
  protected AudioVisualItemImpl()
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
    return EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM;
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
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.AUDIO_VISUAL_ITEM__TITLE, oldTitle, title));
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
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.AUDIO_VISUAL_ITEM__MINUTES_LENGTH, oldMinutesLength, minutesLength));
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
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.AUDIO_VISUAL_ITEM__DAMAGED, oldDamaged, newDamaged));
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
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__TITLE:
        return getTitle();
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__MINUTES_LENGTH:
        return getMinutesLength();
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__DAMAGED:
        return isDamaged();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__TITLE:
        setTitle((String)newValue);
        return;
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__MINUTES_LENGTH:
        setMinutesLength((Integer)newValue);
        return;
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__DAMAGED:
        setDamaged((Boolean)newValue);
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
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__TITLE:
        setTitle(TITLE_EDEFAULT);
        return;
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__MINUTES_LENGTH:
        setMinutesLength(MINUTES_LENGTH_EDEFAULT);
        return;
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__DAMAGED:
        setDamaged(DAMAGED_EDEFAULT);
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
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__TITLE:
        return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__MINUTES_LENGTH:
        return minutesLength != MINUTES_LENGTH_EDEFAULT;
      case EXTLibraryPackage.AUDIO_VISUAL_ITEM__DAMAGED:
        return ((eFlags & DAMAGED_EFLAG) != 0) != DAMAGED_EDEFAULT;
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
    result.append(" (title: "); //$NON-NLS-1$
    result.append(title);
    result.append(", minutesLength: "); //$NON-NLS-1$
    result.append(minutesLength);
    result.append(", damaged: "); //$NON-NLS-1$
    result.append((eFlags & DAMAGED_EFLAG) != 0);
    result.append(')');
    return result.toString();
  }

} //AudioVisualItemImpl
