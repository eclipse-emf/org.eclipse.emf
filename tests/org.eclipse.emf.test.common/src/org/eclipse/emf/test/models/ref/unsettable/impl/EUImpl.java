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
package org.eclipse.emf.test.models.ref.unsettable.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ref.unsettable.DU;
import org.eclipse.emf.test.models.ref.unsettable.EU;
import org.eclipse.emf.test.models.ref.unsettable.URefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EU</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.EUImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.EUImpl#getIds <em>Ids</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.EUImpl#getLabels <em>Labels</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.impl.EUImpl#getDu <em>Du</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EUImpl extends EObjectImpl implements EU
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
   * This is true if the Name attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean nameESet;

  /**
   * The cached value of the '{@link #getIds() <em>Ids</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIds()
   * @generated
   * @ordered
   */
  protected EList<String> ids;

  /**
   * The cached value of the '{@link #getLabels() <em>Labels</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabels()
   * @generated
   * @ordered
   */
  protected EList<String> labels;

  /**
   * The cached value of the '{@link #getDu() <em>Du</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDu()
   * @generated
   * @ordered
   */
  protected EList<DU> du;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EUImpl()
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
    return URefPackage.Literals.EU;
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
    boolean oldNameESet = nameESet;
    nameESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, URefPackage.EU__NAME, oldName, name, !oldNameESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetName()
  {
    String oldName = name;
    boolean oldNameESet = nameESet;
    name = NAME_EDEFAULT;
    nameESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, URefPackage.EU__NAME, oldName, NAME_EDEFAULT, oldNameESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetName()
  {
    return nameESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getIds()
  {
    if (ids == null)
    {
      ids = new EDataTypeUniqueEList.Unsettable<String>(String.class, this, URefPackage.EU__IDS);
    }
    return ids;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetIds()
  {
    if (ids != null) ((InternalEList.Unsettable<?>)ids).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetIds()
  {
    return ids != null && ((InternalEList.Unsettable<?>)ids).isSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getLabels()
  {
    if (labels == null)
    {
      labels = new EDataTypeUniqueEList.Unsettable<String>(String.class, this, URefPackage.EU__LABELS);
    }
    return labels;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetLabels()
  {
    if (labels != null) ((InternalEList.Unsettable<?>)labels).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetLabels()
  {
    return labels != null && ((InternalEList.Unsettable<?>)labels).isSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DU> getDu()
  {
    if (du == null)
    {
      du = new EObjectWithInverseResolvingEList.Unsettable.ManyInverse<DU>(DU.class, this, URefPackage.EU__DU, URefPackage.DU__EU);
    }
    return du;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetDu()
  {
    if (du != null) ((InternalEList.Unsettable<?>)du).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetDu()
  {
    return du != null && ((InternalEList.Unsettable<?>)du).isSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case URefPackage.EU__DU:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getDu()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
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
      case URefPackage.EU__DU:
        return ((InternalEList<?>)getDu()).basicRemove(otherEnd, msgs);
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
      case URefPackage.EU__NAME:
        return getName();
      case URefPackage.EU__IDS:
        return getIds();
      case URefPackage.EU__LABELS:
        return getLabels();
      case URefPackage.EU__DU:
        return getDu();
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
      case URefPackage.EU__NAME:
        setName((String)newValue);
        return;
      case URefPackage.EU__IDS:
        getIds().clear();
        getIds().addAll((Collection<? extends String>)newValue);
        return;
      case URefPackage.EU__LABELS:
        getLabels().clear();
        getLabels().addAll((Collection<? extends String>)newValue);
        return;
      case URefPackage.EU__DU:
        getDu().clear();
        getDu().addAll((Collection<? extends DU>)newValue);
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
      case URefPackage.EU__NAME:
        unsetName();
        return;
      case URefPackage.EU__IDS:
        unsetIds();
        return;
      case URefPackage.EU__LABELS:
        unsetLabels();
        return;
      case URefPackage.EU__DU:
        unsetDu();
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
      case URefPackage.EU__NAME:
        return isSetName();
      case URefPackage.EU__IDS:
        return isSetIds();
      case URefPackage.EU__LABELS:
        return isSetLabels();
      case URefPackage.EU__DU:
        return isSetDu();
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
    result.append(" (name: ");
    if (nameESet) result.append(name); else result.append("<unset>");
    result.append(", ids: ");
    result.append(ids);
    result.append(", labels: ");
    result.append(labels);
    result.append(')');
    return result.toString();
  }

} //EUImpl
