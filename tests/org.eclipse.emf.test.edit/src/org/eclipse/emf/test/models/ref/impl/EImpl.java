/**
 * <copyright>
 * </copyright>
 *
 * $Id: EImpl.java,v 1.1.2.1 2005/01/14 22:56:17 nickb Exp $
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

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ref.D;
import org.eclipse.emf.test.models.ref.E;
import org.eclipse.emf.test.models.ref.RefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>E</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.EImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.EImpl#getIds <em>Ids</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.EImpl#getLabels <em>Labels</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.impl.EImpl#getD <em>D</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EImpl extends EObjectImpl implements E
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getIds() <em>Ids</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIds()
   * @generated
   * @ordered
   */
  protected EList ids = null;

  /**
   * The cached value of the '{@link #getLabels() <em>Labels</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabels()
   * @generated
   * @ordered
   */
  protected EList labels = null;

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
  protected EImpl()
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
    return RefPackage.eINSTANCE.getE();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RefPackage.E__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getIds()
  {
    if (ids == null)
    {
      ids = new EDataTypeUniqueEList(String.class, this, RefPackage.E__IDS);
    }
    return ids;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getLabels()
  {
    if (labels == null)
    {
      labels = new EDataTypeEList(String.class, this, RefPackage.E__LABELS);
    }
    return labels;
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
      d = new EObjectWithInverseResolvingEList.ManyInverse(D.class, this, RefPackage.E__D, RefPackage.D__E);
    }
    return d;
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
        case RefPackage.E__D:
          return ((InternalEList)getD()).basicAdd(otherEnd, msgs);
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
        case RefPackage.E__D:
          return ((InternalEList)getD()).basicRemove(otherEnd, msgs);
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
      case RefPackage.E__NAME:
        return getName();
      case RefPackage.E__IDS:
        return getIds();
      case RefPackage.E__LABELS:
        return getLabels();
      case RefPackage.E__D:
        return getD();
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
      case RefPackage.E__NAME:
        setName((String)newValue);
        return;
      case RefPackage.E__IDS:
        getIds().clear();
        getIds().addAll((Collection)newValue);
        return;
      case RefPackage.E__LABELS:
        getLabels().clear();
        getLabels().addAll((Collection)newValue);
        return;
      case RefPackage.E__D:
        getD().clear();
        getD().addAll((Collection)newValue);
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
      case RefPackage.E__NAME:
        setName(NAME_EDEFAULT);
        return;
      case RefPackage.E__IDS:
        getIds().clear();
        return;
      case RefPackage.E__LABELS:
        getLabels().clear();
        return;
      case RefPackage.E__D:
        getD().clear();
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
      case RefPackage.E__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case RefPackage.E__IDS:
        return ids != null && !ids.isEmpty();
      case RefPackage.E__LABELS:
        return labels != null && !labels.isEmpty();
      case RefPackage.E__D:
        return d != null && !d.isEmpty();
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
    result.append(" (name: ");
    result.append(name);
    result.append(", ids: ");
    result.append(ids);
    result.append(", labels: ");
    result.append(labels);
    result.append(')');
    return result.toString();
  }

} //EImpl
