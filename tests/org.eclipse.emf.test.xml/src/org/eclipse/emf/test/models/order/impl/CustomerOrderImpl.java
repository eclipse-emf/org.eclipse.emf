/**
 * <copyright>
 * </copyright>
 *
 * $Id: CustomerOrderImpl.java,v 1.1.2.1 2005/01/14 22:56:17 nickb Exp $
 */
package org.eclipse.emf.test.models.order.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.order.CustomerOrder;
import org.eclipse.emf.test.models.order.Order;
import org.eclipse.emf.test.models.order.OrderPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Customer Order</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.order.impl.CustomerOrderImpl#getMoviesToSee <em>Movies To See</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.order.impl.CustomerOrderImpl#getMoviesSeen <em>Movies Seen</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.order.impl.CustomerOrderImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.order.impl.CustomerOrderImpl#getCustomerID <em>Customer ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomerOrderImpl extends EObjectImpl implements CustomerOrder
{
  /**
   * The cached value of the '{@link #getMoviesToSee() <em>Movies To See</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMoviesToSee()
   * @generated
   * @ordered
   */
  protected Order moviesToSee = null;

  /**
   * The cached value of the '{@link #getMoviesSeen() <em>Movies Seen</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMoviesSeen()
   * @generated
   * @ordered
   */
  protected Order moviesSeen = null;

  /**
   * The cached value of the '{@link #getAny() <em>Any</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAny()
   * @generated
   * @ordered
   */
  protected FeatureMap any = null;

  /**
   * The default value of the '{@link #getCustomerID() <em>Customer ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCustomerID()
   * @generated
   * @ordered
   */
  protected static final String CUSTOMER_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCustomerID() <em>Customer ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCustomerID()
   * @generated
   * @ordered
   */
  protected String customerID = CUSTOMER_ID_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CustomerOrderImpl()
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
    return OrderPackage.eINSTANCE.getCustomerOrder();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Order getMoviesToSee()
  {
    return moviesToSee;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMoviesToSee(Order newMoviesToSee, NotificationChain msgs)
  {
    Order oldMoviesToSee = moviesToSee;
    moviesToSee = newMoviesToSee;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrderPackage.CUSTOMER_ORDER__MOVIES_TO_SEE, oldMoviesToSee, newMoviesToSee);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMoviesToSee(Order newMoviesToSee)
  {
    if (newMoviesToSee != moviesToSee)
    {
      NotificationChain msgs = null;
      if (moviesToSee != null)
        msgs = ((InternalEObject)moviesToSee).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrderPackage.CUSTOMER_ORDER__MOVIES_TO_SEE, null, msgs);
      if (newMoviesToSee != null)
        msgs = ((InternalEObject)newMoviesToSee).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrderPackage.CUSTOMER_ORDER__MOVIES_TO_SEE, null, msgs);
      msgs = basicSetMoviesToSee(newMoviesToSee, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OrderPackage.CUSTOMER_ORDER__MOVIES_TO_SEE, newMoviesToSee, newMoviesToSee));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Order getMoviesSeen()
  {
    return moviesSeen;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMoviesSeen(Order newMoviesSeen, NotificationChain msgs)
  {
    Order oldMoviesSeen = moviesSeen;
    moviesSeen = newMoviesSeen;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrderPackage.CUSTOMER_ORDER__MOVIES_SEEN, oldMoviesSeen, newMoviesSeen);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMoviesSeen(Order newMoviesSeen)
  {
    if (newMoviesSeen != moviesSeen)
    {
      NotificationChain msgs = null;
      if (moviesSeen != null)
        msgs = ((InternalEObject)moviesSeen).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrderPackage.CUSTOMER_ORDER__MOVIES_SEEN, null, msgs);
      if (newMoviesSeen != null)
        msgs = ((InternalEObject)newMoviesSeen).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrderPackage.CUSTOMER_ORDER__MOVIES_SEEN, null, msgs);
      msgs = basicSetMoviesSeen(newMoviesSeen, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OrderPackage.CUSTOMER_ORDER__MOVIES_SEEN, newMoviesSeen, newMoviesSeen));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getAny()
  {
    if (any == null)
    {
      any = new BasicFeatureMap(this, OrderPackage.CUSTOMER_ORDER__ANY);
    }
    return any;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCustomerID()
  {
    return customerID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCustomerID(String newCustomerID)
  {
    String oldCustomerID = customerID;
    customerID = newCustomerID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OrderPackage.CUSTOMER_ORDER__CUSTOMER_ID, oldCustomerID, customerID));
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
        case OrderPackage.CUSTOMER_ORDER__MOVIES_TO_SEE:
          return basicSetMoviesToSee(null, msgs);
        case OrderPackage.CUSTOMER_ORDER__MOVIES_SEEN:
          return basicSetMoviesSeen(null, msgs);
        case OrderPackage.CUSTOMER_ORDER__ANY:
          return ((InternalEList)getAny()).basicRemove(otherEnd, msgs);
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
      case OrderPackage.CUSTOMER_ORDER__MOVIES_TO_SEE:
        return getMoviesToSee();
      case OrderPackage.CUSTOMER_ORDER__MOVIES_SEEN:
        return getMoviesSeen();
      case OrderPackage.CUSTOMER_ORDER__ANY:
        return getAny();
      case OrderPackage.CUSTOMER_ORDER__CUSTOMER_ID:
        return getCustomerID();
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
      case OrderPackage.CUSTOMER_ORDER__MOVIES_TO_SEE:
        setMoviesToSee((Order)newValue);
        return;
      case OrderPackage.CUSTOMER_ORDER__MOVIES_SEEN:
        setMoviesSeen((Order)newValue);
        return;
      case OrderPackage.CUSTOMER_ORDER__ANY:
        getAny().clear();
        getAny().addAll((Collection)newValue);
        return;
      case OrderPackage.CUSTOMER_ORDER__CUSTOMER_ID:
        setCustomerID((String)newValue);
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
      case OrderPackage.CUSTOMER_ORDER__MOVIES_TO_SEE:
        setMoviesToSee((Order)null);
        return;
      case OrderPackage.CUSTOMER_ORDER__MOVIES_SEEN:
        setMoviesSeen((Order)null);
        return;
      case OrderPackage.CUSTOMER_ORDER__ANY:
        getAny().clear();
        return;
      case OrderPackage.CUSTOMER_ORDER__CUSTOMER_ID:
        setCustomerID(CUSTOMER_ID_EDEFAULT);
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
      case OrderPackage.CUSTOMER_ORDER__MOVIES_TO_SEE:
        return moviesToSee != null;
      case OrderPackage.CUSTOMER_ORDER__MOVIES_SEEN:
        return moviesSeen != null;
      case OrderPackage.CUSTOMER_ORDER__ANY:
        return any != null && !any.isEmpty();
      case OrderPackage.CUSTOMER_ORDER__CUSTOMER_ID:
        return CUSTOMER_ID_EDEFAULT == null ? customerID != null : !CUSTOMER_ID_EDEFAULT.equals(customerID);
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
    result.append(" (any: ");
    result.append(any);
    result.append(", customerID: ");
    result.append(customerID);
    result.append(')');
    return result.toString();
  }

} //CustomerOrderImpl
