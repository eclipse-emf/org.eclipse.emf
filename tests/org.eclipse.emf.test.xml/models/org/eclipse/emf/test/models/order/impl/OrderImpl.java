/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.test.models.order.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

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
  protected static final Object DATE_REQUESTED_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDateRequested() <em>Date Requested</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDateRequested()
   * @generated
   * @ordered
   */
  protected Object dateRequested = DATE_REQUESTED_EDEFAULT;

  /**
   * The default value of the '{@link #getLastUpdated() <em>Last Updated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastUpdated()
   * @generated
   * @ordered
   */
  protected static final Object LAST_UPDATED_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLastUpdated() <em>Last Updated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastUpdated()
   * @generated
   * @ordered
   */
  protected Object lastUpdated = LAST_UPDATED_EDEFAULT;

  /**
   * The default value of the '{@link #getMovies() <em>Movies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMovies()
   * @generated
   * @ordered
   */
  protected static final List MOVIES_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMovies() <em>Movies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMovies()
   * @generated
   * @ordered
   */
  protected List movies = MOVIES_EDEFAULT;

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
  protected EClass eStaticClass()
  {
    return OrderPackage.eINSTANCE.getOrder();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getDateRequested()
  {
    return dateRequested;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDateRequested(Object newDateRequested)
  {
    Object oldDateRequested = dateRequested;
    dateRequested = newDateRequested;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OrderPackage.ORDER__DATE_REQUESTED, oldDateRequested, dateRequested));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getLastUpdated()
  {
    return lastUpdated;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastUpdated(Object newLastUpdated)
  {
    Object oldLastUpdated = lastUpdated;
    lastUpdated = newLastUpdated;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OrderPackage.ORDER__LAST_UPDATED, oldLastUpdated, lastUpdated));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getMovies()
  {
    return movies;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMovies(List newMovies)
  {
    List oldMovies = movies;
    movies = newMovies;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OrderPackage.ORDER__MOVIES, oldMovies, movies));
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
      case OrderPackage.ORDER__DATE_REQUESTED:
        return getDateRequested();
      case OrderPackage.ORDER__LAST_UPDATED:
        return getLastUpdated();
      case OrderPackage.ORDER__MOVIES:
        return getMovies();
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
      case OrderPackage.ORDER__DATE_REQUESTED:
        setDateRequested((Object)newValue);
        return;
      case OrderPackage.ORDER__LAST_UPDATED:
        setLastUpdated((Object)newValue);
        return;
      case OrderPackage.ORDER__MOVIES:
        setMovies((List)newValue);
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
      case OrderPackage.ORDER__DATE_REQUESTED:
        return DATE_REQUESTED_EDEFAULT == null ? dateRequested != null : !DATE_REQUESTED_EDEFAULT.equals(dateRequested);
      case OrderPackage.ORDER__LAST_UPDATED:
        return LAST_UPDATED_EDEFAULT == null ? lastUpdated != null : !LAST_UPDATED_EDEFAULT.equals(lastUpdated);
      case OrderPackage.ORDER__MOVIES:
        return MOVIES_EDEFAULT == null ? movies != null : !MOVIES_EDEFAULT.equals(movies);
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
