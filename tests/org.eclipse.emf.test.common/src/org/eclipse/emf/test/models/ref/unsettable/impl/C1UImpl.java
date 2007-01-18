/**
 * <copyright>
 * </copyright>
 *
 * $Id: C1UImpl.java,v 1.1 2007/01/18 15:50:22 marcelop Exp $
 */
package org.eclipse.emf.test.models.ref.unsettable.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ref.unsettable.AU;
import org.eclipse.emf.test.models.ref.unsettable.BU;
import org.eclipse.emf.test.models.ref.unsettable.C1U;
import org.eclipse.emf.test.models.ref.unsettable.URefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>C1U</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.C1UImpl#getAu <em>Au</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.C1UImpl#getBu <em>Bu</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class C1UImpl extends EObjectImpl implements C1U
{
  /**
   * The cached value of the '{@link #getAu() <em>Au</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAu()
   * @generated
   * @ordered
   */
  protected AU au = null;

  /**
   * This is true if the Au containment reference has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean auESet = false;

  /**
   * The cached value of the '{@link #getBu() <em>Bu</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBu()
   * @generated
   * @ordered
   */
  protected EList<BU> bu = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected C1UImpl()
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
    return URefPackage.Literals.C1U;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AU getAu()
  {
    return au;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAu(AU newAu, NotificationChain msgs)
  {
    AU oldAu = au;
    au = newAu;
    boolean oldAuESet = auESet;
    auESet = true;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, URefPackage.C1U__AU, oldAu, newAu, !oldAuESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAu(AU newAu)
  {
    if (newAu != au)
    {
      NotificationChain msgs = null;
      if (au != null)
        msgs = ((InternalEObject)au).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - URefPackage.C1U__AU, null, msgs);
      if (newAu != null)
        msgs = ((InternalEObject)newAu).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - URefPackage.C1U__AU, null, msgs);
      msgs = basicSetAu(newAu, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    {
      boolean oldAuESet = auESet;
      auESet = true;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.C1U__AU, newAu, newAu, !oldAuESet));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicUnsetAu(NotificationChain msgs)
  {
    AU oldAu = au;
    au = null;
    boolean oldAuESet = auESet;
    auESet = false;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, URefPackage.C1U__AU, oldAu, null, oldAuESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAu()
  {
    if (au != null)
    {
      NotificationChain msgs = null;
      msgs = ((InternalEObject)au).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - URefPackage.C1U__AU, null, msgs);
      msgs = basicUnsetAu(msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    {
      boolean oldAuESet = auESet;
      auESet = false;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.UNSET, URefPackage.C1U__AU, null, null, oldAuESet));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAu()
  {
    return auESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<BU> getBu()
  {
    if (bu == null)
    {
      bu = new EObjectContainmentEList.Unsettable<BU>(BU.class, this, URefPackage.C1U__BU);
    }
    return bu;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetBu()
  {
    if (bu != null) ((InternalEList.Unsettable<?>)bu).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetBu()
  {
    return bu != null && ((InternalEList.Unsettable<?>)bu).isSet();
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
      case URefPackage.C1U__AU:
        return basicUnsetAu(msgs);
      case URefPackage.C1U__BU:
        return ((InternalEList<?>)getBu()).basicRemove(otherEnd, msgs);
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
      case URefPackage.C1U__AU:
        return getAu();
      case URefPackage.C1U__BU:
        return getBu();
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
      case URefPackage.C1U__AU:
        setAu((AU)newValue);
        return;
      case URefPackage.C1U__BU:
        getBu().clear();
        getBu().addAll((Collection<? extends BU>)newValue);
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
      case URefPackage.C1U__AU:
        unsetAu();
        return;
      case URefPackage.C1U__BU:
        unsetBu();
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
      case URefPackage.C1U__AU:
        return isSetAu();
      case URefPackage.C1U__BU:
        return isSetBu();
    }
    return super.eIsSet(featureID);
  }

} //C1UImpl
