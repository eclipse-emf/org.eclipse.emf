/**
 * <copyright>
 * </copyright>
 *
 * $Id: AUImpl.java,v 1.1 2005/08/09 04:43:08 davidms Exp $
 */
package org.eclipse.emf.test.models.ref.unsettable.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.test.models.ref.unsettable.AU;
import org.eclipse.emf.test.models.ref.unsettable.BU;
import org.eclipse.emf.test.models.ref.unsettable.C2U;
import org.eclipse.emf.test.models.ref.unsettable.CU;
import org.eclipse.emf.test.models.ref.unsettable.URefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AU</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.AUImpl#getBu <em>Bu</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.AUImpl#getC2u <em>C2u</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.AUImpl#getCu <em>Cu</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AUImpl extends EObjectImpl implements AU
{
  /**
   * The cached value of the '{@link #getBu() <em>Bu</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBu()
   * @generated
   * @ordered
   */
  protected BU bu = null;

  /**
   * This is true if the Bu reference has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean buESet = false;

  /**
   * The cached value of the '{@link #getCu() <em>Cu</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCu()
   * @generated
   * @ordered
   */
  protected CU cu = null;

  /**
   * This is true if the Cu reference has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean cuESet = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AUImpl()
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
    return URefPackage.eINSTANCE.getAU();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BU getBu()
  {
    if (bu != null && bu.eIsProxy())
    {
      BU oldBu = bu;
      bu = (BU)eResolveProxy((InternalEObject)bu);
      if (bu != oldBu)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, URefPackage.AU__BU, oldBu, bu));
      }
    }
    return bu;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BU basicGetBu()
  {
    return bu;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBu(BU newBu, NotificationChain msgs)
  {
    BU oldBu = bu;
    bu = newBu;
    boolean oldBuESet = buESet;
    buESet = true;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, URefPackage.AU__BU, oldBu, newBu, !oldBuESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBu(BU newBu)
  {
    if (newBu != bu)
    {
      NotificationChain msgs = null;
      if (bu != null)
        msgs = ((InternalEObject)bu).eInverseRemove(this, URefPackage.BU__AU, BU.class, msgs);
      if (newBu != null)
        msgs = ((InternalEObject)newBu).eInverseAdd(this, URefPackage.BU__AU, BU.class, msgs);
      msgs = basicSetBu(newBu, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    	{
      boolean oldBuESet = buESet;
      buESet = true;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.AU__BU, newBu, newBu, !oldBuESet));
    	}
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicUnsetBu(NotificationChain msgs)
  {
    BU oldBu = bu;
    bu = null;
    boolean oldBuESet = buESet;
    buESet = false;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, URefPackage.AU__BU, oldBu, null, oldBuESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetBu()
  {
    if (bu != null)
    {
      NotificationChain msgs = null;
      msgs = ((InternalEObject)bu).eInverseRemove(this, URefPackage.BU__AU, BU.class, msgs);
      msgs = basicUnsetBu(msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    	{
      boolean oldBuESet = buESet;
      buESet = false;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.UNSET, URefPackage.AU__BU, null, null, oldBuESet));
    	}
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetBu()
  {
    return buESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public C2U getC2u()
  {
    if (eContainerFeatureID != URefPackage.AU__C2U) return null;
    return (C2U)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setC2u(C2U newC2u)
  {
    if (newC2u != eContainer || (eContainerFeatureID != URefPackage.AU__C2U && newC2u != null))
    {
      if (EcoreUtil.isAncestor(this, newC2u))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newC2u != null)
        msgs = ((InternalEObject)newC2u).eInverseAdd(this, URefPackage.C2U__AU, C2U.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newC2u, URefPackage.AU__C2U, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.AU__C2U, newC2u, newC2u));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CU getCu()
  {
    if (cu != null && cu.eIsProxy())
    {
      CU oldCu = cu;
      cu = (CU)eResolveProxy((InternalEObject)cu);
      if (cu != oldCu)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, URefPackage.AU__CU, oldCu, cu));
      }
    }
    return cu;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CU basicGetCu()
  {
    return cu;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCu(CU newCu)
  {
    CU oldCu = cu;
    cu = newCu;
    boolean oldCuESet = cuESet;
    cuESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.AU__CU, oldCu, cu, !oldCuESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetCu()
  {
    CU oldCu = cu;
    boolean oldCuESet = cuESet;
    cu = null;
    cuESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, URefPackage.AU__CU, oldCu, null, oldCuESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetCu()
  {
    return cuESet;
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
        case URefPackage.AU__BU:
          if (bu != null)
            msgs = ((InternalEObject)bu).eInverseRemove(this, URefPackage.BU__AU, BU.class, msgs);
          return basicSetBu((BU)otherEnd, msgs);
        case URefPackage.AU__C2U:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, URefPackage.AU__C2U, msgs);
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
        case URefPackage.AU__BU:
          return basicUnsetBu(msgs);
        case URefPackage.AU__C2U:
          return eBasicSetContainer(null, URefPackage.AU__C2U, msgs);
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
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case URefPackage.AU__C2U:
          return eContainer.eInverseRemove(this, URefPackage.C2U__AU, C2U.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case URefPackage.AU__BU:
        if (resolve) return getBu();
        return basicGetBu();
      case URefPackage.AU__C2U:
        return getC2u();
      case URefPackage.AU__CU:
        if (resolve) return getCu();
        return basicGetCu();
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
      case URefPackage.AU__BU:
        setBu((BU)newValue);
        return;
      case URefPackage.AU__C2U:
        setC2u((C2U)newValue);
        return;
      case URefPackage.AU__CU:
        setCu((CU)newValue);
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
      case URefPackage.AU__BU:
        unsetBu();
        return;
      case URefPackage.AU__C2U:
        setC2u((C2U)null);
        return;
      case URefPackage.AU__CU:
        unsetCu();
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
      case URefPackage.AU__BU:
        return isSetBu();
      case URefPackage.AU__C2U:
        return getC2u() != null;
      case URefPackage.AU__CU:
        return isSetCu();
    }
    return eDynamicIsSet(eFeature);
  }

} //AUImpl
