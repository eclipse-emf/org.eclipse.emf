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
package org.eclipse.emf.test.models.order.impl;

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.test.models.order.Order;
import org.eclipse.emf.test.models.order.OrderPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Order</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.order.impl.OrderImpl#getDateRequested <em>Date Requested</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.order.impl.OrderImpl#getLastUpdated <em>Last Updated</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.order.impl.OrderImpl#getMovies <em>Movies</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderImpl extends EObjectImpl implements Order
{
  /**
   * The default value of the '{@link #getDateRequested() <em>Date Requested</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDateRequested()
   * @generated
   * @ordered
   */
  protected static final XMLGregorianCalendar DATE_REQUESTED_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDateRequested() <em>Date Requested</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDateRequested()
   * @generated
   * @ordered
   */
  protected XMLGregorianCalendar dateRequested = DATE_REQUESTED_EDEFAULT;

  /**
   * The default value of the '{@link #getLastUpdated() <em>Last Updated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastUpdated()
   * @generated
   * @ordered
   */
  protected static final XMLGregorianCalendar LAST_UPDATED_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLastUpdated() <em>Last Updated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastUpdated()
   * @generated
   * @ordered
   */
  protected XMLGregorianCalendar lastUpdated = LAST_UPDATED_EDEFAULT;

  /**
   * The default value of the '{@link #getMovies() <em>Movies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMovies()
   * @generated
   * @ordered
   */
  protected static final List<String> MOVIES_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMovies() <em>Movies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMovies()
   * @generated
   * @ordered
   */
  protected List<String> movies = MOVIES_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected OrderImpl()
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
    return OrderPackage.Literals.ORDER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLGregorianCalendar getDateRequested()
  {
    return dateRequested;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDateRequested(XMLGregorianCalendar newDateRequested)
  {
    XMLGregorianCalendar oldDateRequested = dateRequested;
    dateRequested = newDateRequested;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OrderPackage.ORDER__DATE_REQUESTED, oldDateRequested, dateRequested));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLGregorianCalendar getLastUpdated()
  {
    return lastUpdated;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastUpdated(XMLGregorianCalendar newLastUpdated)
  {
    XMLGregorianCalendar oldLastUpdated = lastUpdated;
    lastUpdated = newLastUpdated;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OrderPackage.ORDER__LAST_UPDATED, oldLastUpdated, lastUpdated));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<String> getMovies()
  {
    return movies;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMovies(List<String> newMovies)
  {
    List<String> oldMovies = movies;
    movies = newMovies;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OrderPackage.ORDER__MOVIES, oldMovies, movies));
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
      case OrderPackage.ORDER__DATE_REQUESTED:
        return getDateRequested();
      case OrderPackage.ORDER__LAST_UPDATED:
        return getLastUpdated();
      case OrderPackage.ORDER__MOVIES:
        return getMovies();
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
      case OrderPackage.ORDER__DATE_REQUESTED:
        setDateRequested((XMLGregorianCalendar)newValue);
        return;
      case OrderPackage.ORDER__LAST_UPDATED:
        setLastUpdated((XMLGregorianCalendar)newValue);
        return;
      case OrderPackage.ORDER__MOVIES:
        setMovies((List<String>)newValue);
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
      case OrderPackage.ORDER__DATE_REQUESTED:
        setDateRequested(DATE_REQUESTED_EDEFAULT);
        return;
      case OrderPackage.ORDER__LAST_UPDATED:
        setLastUpdated(LAST_UPDATED_EDEFAULT);
        return;
      case OrderPackage.ORDER__MOVIES:
        setMovies(MOVIES_EDEFAULT);
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
      case OrderPackage.ORDER__DATE_REQUESTED:
        return DATE_REQUESTED_EDEFAULT == null ? dateRequested != null : !DATE_REQUESTED_EDEFAULT.equals(dateRequested);
      case OrderPackage.ORDER__LAST_UPDATED:
        return LAST_UPDATED_EDEFAULT == null ? lastUpdated != null : !LAST_UPDATED_EDEFAULT.equals(lastUpdated);
      case OrderPackage.ORDER__MOVIES:
        return MOVIES_EDEFAULT == null ? movies != null : !MOVIES_EDEFAULT.equals(movies);
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
    result.append(" (dateRequested: ");
    result.append(dateRequested);
    result.append(", lastUpdated: ");
    result.append(lastUpdated);
    result.append(", movies: ");
    result.append(movies);
    result.append(')');
    return result.toString();
  }

} //OrderImpl
