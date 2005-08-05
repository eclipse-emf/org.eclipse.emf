/**
 * <copyright>
 * </copyright>
 *
 * $Id: CImpl.java,v 1.1.4.1 2005/08/05 20:42:43 nickb Exp $
 */
package org.eclipse.emf.test.models.ref.impl;

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

import org.eclipse.emf.test.models.ref.C;
import org.eclipse.emf.test.models.ref.C4;
import org.eclipse.emf.test.models.ref.D;
import org.eclipse.emf.test.models.ref.RefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>C</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.CImpl#getD <em>D</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.CImpl#getC4 <em>C4</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CImpl extends EObjectImpl implements C
{
  /**
   * The cached value of the '{@link #getD() <em>D</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getD()
   * @generated
   * @ordered
   */
  protected EList d = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CImpl()
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
    return RefPackage.eINSTANCE.getC();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getD()
  {
    if (d == null)
    {
      d = new EObjectWithInverseResolvingEList(D.class, this, RefPackage.C__D, RefPackage.D__C);
    }
    return d;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public C4 getC4()
  {
    if (eContainerFeatureID != RefPackage.C__C4) return null;
    return (C4)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setC4(C4 newC4)
  {
    if (newC4 != eContainer || (eContainerFeatureID != RefPackage.C__C4 && newC4 != null))
    {
      if (EcoreUtil.isAncestor(this, newC4))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newC4 != null)
        msgs = ((InternalEObject)newC4).eInverseAdd(this, RefPackage.C4__C, C4.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newC4, RefPackage.C__C4, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RefPackage.C__C4, newC4, newC4));
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
        case RefPackage.C__D:
          return ((InternalEList)getD()).basicAdd(otherEnd, msgs);
        case RefPackage.C__C4:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, RefPackage.C__C4, msgs);
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
        case RefPackage.C__D:
          return ((InternalEList)getD()).basicRemove(otherEnd, msgs);
        case RefPackage.C__C4:
          return eBasicSetContainer(null, RefPackage.C__C4, msgs);
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
        case RefPackage.C__C4:
          return eContainer.eInverseRemove(this, RefPackage.C4__C, C4.class, msgs);
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
      case RefPackage.C__D:
        return getD();
      case RefPackage.C__C4:
        return getC4();
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
      case RefPackage.C__D:
        getD().clear();
        getD().addAll((Collection)newValue);
        return;
      case RefPackage.C__C4:
        setC4((C4)newValue);
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
      case RefPackage.C__D:
        getD().clear();
        return;
      case RefPackage.C__C4:
        setC4((C4)null);
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
      case RefPackage.C__D:
        return d != null && !d.isEmpty();
      case RefPackage.C__C4:
        return getC4() != null;
    }
    return eDynamicIsSet(eFeature);
  }

} //CImpl
