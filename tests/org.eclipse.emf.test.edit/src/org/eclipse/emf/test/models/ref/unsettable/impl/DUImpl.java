/**
 * <copyright>
 * </copyright>
 *
 * $Id: DUImpl.java,v 1.1 2005/08/09 04:43:08 davidms Exp $
 */
package org.eclipse.emf.test.models.ref.unsettable.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ref.unsettable.C4U;
import org.eclipse.emf.test.models.ref.unsettable.CU;
import org.eclipse.emf.test.models.ref.unsettable.DU;
import org.eclipse.emf.test.models.ref.unsettable.EU;
import org.eclipse.emf.test.models.ref.unsettable.URefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DU</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.DUImpl#getCu <em>Cu</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.DUImpl#getC4u <em>C4u</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.DUImpl#getEu <em>Eu</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DUImpl extends EObjectImpl implements DU
{
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
   * The cached value of the '{@link #getEu() <em>Eu</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEu()
   * @generated
   * @ordered
   */
  protected EList eu = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DUImpl()
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
    return URefPackage.eINSTANCE.getDU();
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, URefPackage.DU__CU, oldCu, cu));
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
  public NotificationChain basicSetCu(CU newCu, NotificationChain msgs)
  {
    CU oldCu = cu;
    cu = newCu;
    boolean oldCuESet = cuESet;
    cuESet = true;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, URefPackage.DU__CU, oldCu, newCu, !oldCuESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCu(CU newCu)
  {
    if (newCu != cu)
    {
      NotificationChain msgs = null;
      if (cu != null)
        msgs = ((InternalEObject)cu).eInverseRemove(this, URefPackage.CU__DU, CU.class, msgs);
      if (newCu != null)
        msgs = ((InternalEObject)newCu).eInverseAdd(this, URefPackage.CU__DU, CU.class, msgs);
      msgs = basicSetCu(newCu, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    	{
      boolean oldCuESet = cuESet;
      cuESet = true;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.DU__CU, newCu, newCu, !oldCuESet));
    	}
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicUnsetCu(NotificationChain msgs)
  {
    CU oldCu = cu;
    cu = null;
    boolean oldCuESet = cuESet;
    cuESet = false;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, URefPackage.DU__CU, oldCu, null, oldCuESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetCu()
  {
    if (cu != null)
    {
      NotificationChain msgs = null;
      msgs = ((InternalEObject)cu).eInverseRemove(this, URefPackage.CU__DU, CU.class, msgs);
      msgs = basicUnsetCu(msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    	{
      boolean oldCuESet = cuESet;
      cuESet = false;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.UNSET, URefPackage.DU__CU, null, null, oldCuESet));
    	}
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
  public C4U getC4u()
  {
    if (eContainerFeatureID != URefPackage.DU__C4U) return null;
    return (C4U)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setC4u(C4U newC4u)
  {
    if (newC4u != eContainer || (eContainerFeatureID != URefPackage.DU__C4U && newC4u != null))
    {
      if (EcoreUtil.isAncestor(this, newC4u))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newC4u != null)
        msgs = ((InternalEObject)newC4u).eInverseAdd(this, URefPackage.C4U__DU, C4U.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newC4u, URefPackage.DU__C4U, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.DU__C4U, newC4u, newC4u));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getEu()
  {
    if (eu == null)
    {
      eu = new EObjectWithInverseResolvingEList.Unsettable.ManyInverse(EU.class, this, URefPackage.DU__EU, URefPackage.EU__DU);
    }
    return eu;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetEu()
  {
    ((InternalEList.Unsettable)getEu()).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetEu()
  {
    return eu != null && ((InternalEList.Unsettable)eu).isSet();
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
        case URefPackage.DU__CU:
          if (cu != null)
            msgs = ((InternalEObject)cu).eInverseRemove(this, URefPackage.CU__DU, CU.class, msgs);
          return basicSetCu((CU)otherEnd, msgs);
        case URefPackage.DU__C4U:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, URefPackage.DU__C4U, msgs);
        case URefPackage.DU__EU:
          return ((InternalEList)getEu()).basicAdd(otherEnd, msgs);
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
        case URefPackage.DU__CU:
          return basicUnsetCu(msgs);
        case URefPackage.DU__C4U:
          return eBasicSetContainer(null, URefPackage.DU__C4U, msgs);
        case URefPackage.DU__EU:
          return ((InternalEList)getEu()).basicRemove(otherEnd, msgs);
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
        case URefPackage.DU__C4U:
          return eContainer.eInverseRemove(this, URefPackage.C4U__DU, C4U.class, msgs);
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
      case URefPackage.DU__CU:
        if (resolve) return getCu();
        return basicGetCu();
      case URefPackage.DU__C4U:
        return getC4u();
      case URefPackage.DU__EU:
        return getEu();
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
      case URefPackage.DU__CU:
        setCu((CU)newValue);
        return;
      case URefPackage.DU__C4U:
        setC4u((C4U)newValue);
        return;
      case URefPackage.DU__EU:
        getEu().clear();
        getEu().addAll((Collection)newValue);
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
      case URefPackage.DU__CU:
        unsetCu();
        return;
      case URefPackage.DU__C4U:
        setC4u((C4U)null);
        return;
      case URefPackage.DU__EU:
        unsetEu();
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
      case URefPackage.DU__CU:
        return isSetCu();
      case URefPackage.DU__C4U:
        return getC4u() != null;
      case URefPackage.DU__EU:
        return isSetEu();
    }
    return eDynamicIsSet(eFeature);
  }

} //DUImpl
