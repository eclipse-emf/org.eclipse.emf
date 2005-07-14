/**
 * <copyright>
 * </copyright>
 *
 * $Id: ExtEImpl.java,v 1.1 2005/07/14 19:35:16 davidms Exp $
 */
package org.eclipse.emf.test.models.ext.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ext.ExtE;
import org.eclipse.emf.test.models.ext.ExtPackage;

import org.eclipse.emf.test.models.ext.F;

import org.eclipse.emf.test.models.ref.impl.EImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>E</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ext.impl.ExtEImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ext.impl.ExtEImpl#getF <em>F</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtEImpl extends EImpl implements ExtE
{
  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final int VALUE_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected int value = VALUE_EDEFAULT;

  /**
   * The cached value of the '{@link #getF() <em>F</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getF()
   * @generated
   * @ordered
   */
  protected EList f = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExtEImpl()
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
    return ExtPackage.eINSTANCE.getExtE();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(int newValue)
  {
    int oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ExtPackage.EXT_E__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getF()
  {
    if (f == null)
    {
      f = new EObjectContainmentWithInverseEList(F.class, this, ExtPackage.EXT_E__F, ExtPackage.F__E);
    }
    return f;
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
        case ExtPackage.EXT_E__D:
          return ((InternalEList)getD()).basicAdd(otherEnd, msgs);
        case ExtPackage.EXT_E__F:
          return ((InternalEList)getF()).basicAdd(otherEnd, msgs);
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
        case ExtPackage.EXT_E__D:
          return ((InternalEList)getD()).basicRemove(otherEnd, msgs);
        case ExtPackage.EXT_E__F:
          return ((InternalEList)getF()).basicRemove(otherEnd, msgs);
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
      case ExtPackage.EXT_E__NAME:
        return getName();
      case ExtPackage.EXT_E__IDS:
        return getIds();
      case ExtPackage.EXT_E__LABELS:
        return getLabels();
      case ExtPackage.EXT_E__D:
        return getD();
      case ExtPackage.EXT_E__VALUE:
        return new Integer(getValue());
      case ExtPackage.EXT_E__F:
        return getF();
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
      case ExtPackage.EXT_E__NAME:
        setName((String)newValue);
        return;
      case ExtPackage.EXT_E__IDS:
        getIds().clear();
        getIds().addAll((Collection)newValue);
        return;
      case ExtPackage.EXT_E__LABELS:
        getLabels().clear();
        getLabels().addAll((Collection)newValue);
        return;
      case ExtPackage.EXT_E__D:
        getD().clear();
        getD().addAll((Collection)newValue);
        return;
      case ExtPackage.EXT_E__VALUE:
        setValue(((Integer)newValue).intValue());
        return;
      case ExtPackage.EXT_E__F:
        getF().clear();
        getF().addAll((Collection)newValue);
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
      case ExtPackage.EXT_E__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ExtPackage.EXT_E__IDS:
        getIds().clear();
        return;
      case ExtPackage.EXT_E__LABELS:
        getLabels().clear();
        return;
      case ExtPackage.EXT_E__D:
        getD().clear();
        return;
      case ExtPackage.EXT_E__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case ExtPackage.EXT_E__F:
        getF().clear();
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
      case ExtPackage.EXT_E__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ExtPackage.EXT_E__IDS:
        return ids != null && !ids.isEmpty();
      case ExtPackage.EXT_E__LABELS:
        return labels != null && !labels.isEmpty();
      case ExtPackage.EXT_E__D:
        return d != null && !d.isEmpty();
      case ExtPackage.EXT_E__VALUE:
        return value != VALUE_EDEFAULT;
      case ExtPackage.EXT_E__F:
        return f != null && !f.isEmpty();
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
    result.append(" (value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

} //ExtEImpl
