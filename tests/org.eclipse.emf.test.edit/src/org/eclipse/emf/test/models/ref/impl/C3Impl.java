/**
 * <copyright>
 * </copyright>
 *
 * $Id: C3Impl.java,v 1.1 2005/07/08 02:16:32 davidms Exp $
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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ref.C;
import org.eclipse.emf.test.models.ref.C3;
import org.eclipse.emf.test.models.ref.D;
import org.eclipse.emf.test.models.ref.RefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>C3</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.C3Impl#getD <em>D</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.C3Impl#getC <em>C</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class C3Impl extends EObjectImpl implements C3
{
  /**
   * The cached value of the '{@link #getD() <em>D</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getD()
   * @generated
   * @ordered
   */
  protected EList d = null;

  /**
   * The cached value of the '{@link #getC() <em>C</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getC()
   * @generated
   * @ordered
   */
  protected C c = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected C3Impl()
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
    return RefPackage.eINSTANCE.getC3();
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
      d = new EObjectContainmentEList(D.class, this, RefPackage.C3__D);
    }
    return d;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public C getC()
  {
    return c;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetC(C newC, NotificationChain msgs)
  {
    C oldC = c;
    c = newC;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RefPackage.C3__C, oldC, newC);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setC(C newC)
  {
    if (newC != c)
    {
      NotificationChain msgs = null;
      if (c != null)
        msgs = ((InternalEObject)c).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RefPackage.C3__C, null, msgs);
      if (newC != null)
        msgs = ((InternalEObject)newC).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RefPackage.C3__C, null, msgs);
      msgs = basicSetC(newC, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RefPackage.C3__C, newC, newC));
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
        case RefPackage.C3__D:
          return ((InternalEList)getD()).basicRemove(otherEnd, msgs);
        case RefPackage.C3__C:
          return basicSetC(null, msgs);
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
      case RefPackage.C3__D:
        return getD();
      case RefPackage.C3__C:
        return getC();
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
      case RefPackage.C3__D:
        getD().clear();
        getD().addAll((Collection)newValue);
        return;
      case RefPackage.C3__C:
        setC((C)newValue);
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
      case RefPackage.C3__D:
        getD().clear();
        return;
      case RefPackage.C3__C:
        setC((C)null);
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
      case RefPackage.C3__D:
        return d != null && !d.isEmpty();
      case RefPackage.C3__C:
        return c != null;
    }
    return eDynamicIsSet(eFeature);
  }

} //C3Impl
