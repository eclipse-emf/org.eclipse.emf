/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.examples.extlibrary.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Periodical;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Periodical</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.PeriodicalImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.PeriodicalImpl#getIssuesPerYear <em>Issues Per Year</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PeriodicalImpl extends ItemImpl implements Periodical
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
   * The default value of the '{@link #getIssuesPerYear() <em>Issues Per Year</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIssuesPerYear()
   * @generated
   * @ordered
   */
  protected static final int ISSUES_PER_YEAR_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getIssuesPerYear() <em>Issues Per Year</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIssuesPerYear()
   * @generated
   * @ordered
   */
  protected int issuesPerYear = ISSUES_PER_YEAR_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PeriodicalImpl()
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
    return EXTLibraryPackage.Literals.PERIODICAL;
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
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.PERIODICAL__TITLE, oldTitle, title));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getIssuesPerYear()
  {
    return issuesPerYear;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIssuesPerYear(int newIssuesPerYear)
  {
    int oldIssuesPerYear = issuesPerYear;
    issuesPerYear = newIssuesPerYear;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.PERIODICAL__ISSUES_PER_YEAR, oldIssuesPerYear, issuesPerYear));
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
      case EXTLibraryPackage.PERIODICAL__TITLE:
        return getTitle();
      case EXTLibraryPackage.PERIODICAL__ISSUES_PER_YEAR:
        return getIssuesPerYear();
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
      case EXTLibraryPackage.PERIODICAL__TITLE:
        setTitle((String)newValue);
        return;
      case EXTLibraryPackage.PERIODICAL__ISSUES_PER_YEAR:
        setIssuesPerYear((Integer)newValue);
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
      case EXTLibraryPackage.PERIODICAL__TITLE:
        setTitle(TITLE_EDEFAULT);
        return;
      case EXTLibraryPackage.PERIODICAL__ISSUES_PER_YEAR:
        setIssuesPerYear(ISSUES_PER_YEAR_EDEFAULT);
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
      case EXTLibraryPackage.PERIODICAL__TITLE:
        return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
      case EXTLibraryPackage.PERIODICAL__ISSUES_PER_YEAR:
        return issuesPerYear != ISSUES_PER_YEAR_EDEFAULT;
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
    result.append(", issuesPerYear: "); //$NON-NLS-1$
    result.append(issuesPerYear);
    result.append(')');
    return result.toString();
  }

} //PeriodicalImpl
