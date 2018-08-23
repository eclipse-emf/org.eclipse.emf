/**
 * Copyright (c) 2009 BestSolution and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   Tom Schindl - Initial API and implementation
 */
package org.eclipse.emf.test.databinding.emfdb.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.test.databinding.emfdb.A;
import org.eclipse.emf.test.databinding.emfdb.B;
import org.eclipse.emf.test.databinding.emfdb.EmfdbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>A</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.test.databinding.emfdb.impl.AImpl#getString <em>String</em>}</li>
 *   <li>{@link org.eclipse.emf.test.databinding.emfdb.impl.AImpl#getBlist <em>Blist</em>}</li>
 *   <li>{@link org.eclipse.emf.test.databinding.emfdb.impl.AImpl#getCmap <em>Cmap</em>}</li>
 *   <li>{@link org.eclipse.emf.test.databinding.emfdb.impl.AImpl#getStrings <em>Strings</em>}</li>
 *   <li>{@link org.eclipse.emf.test.databinding.emfdb.impl.AImpl#getPrimitiveValues <em>Primitive Values</em>}</li>
 *   <li>{@link org.eclipse.emf.test.databinding.emfdb.impl.AImpl#getNotUniqueValues <em>Not Unique Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AImpl extends EObjectImpl implements A
{
  /**
   * The default value of the '{@link #getString() <em>String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected static final String STRING_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getString() <em>String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected String string = STRING_EDEFAULT;

  /**
   * The cached value of the '{@link #getBlist() <em>Blist</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBlist()
   * @generated
   * @ordered
   */
  protected EList<B> blist;

  /**
   * The cached value of the '{@link #getCmap() <em>Cmap</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCmap()
   * @generated
   * @ordered
   */
  protected EMap<String, String> cmap;

  /**
   * The cached value of the '{@link #getStrings() <em>Strings</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStrings()
   * @generated
   * @ordered
   */
  protected EList<String> strings;

  /**
   * The cached value of the '{@link #getPrimitiveValues() <em>Primitive Values</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrimitiveValues()
   * @generated
   * @ordered
   */
  protected EList<Double> primitiveValues;

  /**
   * The cached value of the '{@link #getNotUniqueValues() <em>Not Unique Values</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNotUniqueValues()
   * @generated
   * @ordered
   */
  protected EList<Double> notUniqueValues;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AImpl()
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
    return EmfdbPackage.Literals.A;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getString()
  {
    return string;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setString(String newString)
  {
    String oldString = string;
    string = newString;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EmfdbPackage.A__STRING, oldString, string));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<B> getBlist()
  {
    if (blist == null)
    {
      blist = new EObjectContainmentEList<B>(B.class, this, EmfdbPackage.A__BLIST);
    }
    return blist;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<String, String> getCmap()
  {
    if (cmap == null)
    {
      cmap = new EcoreEMap<String,String>(EmfdbPackage.Literals.C, CImpl.class, this, EmfdbPackage.A__CMAP);
    }
    return cmap;
  }

    /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getStrings()
  {
    if (strings == null)
    {
      strings = new EDataTypeEList<String>(String.class, this, EmfdbPackage.A__STRINGS);
    }
    return strings;
  }

    /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Double> getPrimitiveValues()
  {
    if (primitiveValues == null)
    {
      primitiveValues = new EDataTypeUniqueEList<Double>(Double.class, this, EmfdbPackage.A__PRIMITIVE_VALUES);
    }
    return primitiveValues;
  }

    /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Double> getNotUniqueValues()
  {
    if (notUniqueValues == null)
    {
      notUniqueValues = new EDataTypeEList<Double>(Double.class, this, EmfdbPackage.A__NOT_UNIQUE_VALUES);
    }
    return notUniqueValues;
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
      case EmfdbPackage.A__BLIST:
        return ((InternalEList<?>)getBlist()).basicRemove(otherEnd, msgs);
      case EmfdbPackage.A__CMAP:
        return ((InternalEList<?>)getCmap()).basicRemove(otherEnd, msgs);
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
      case EmfdbPackage.A__STRING:
        return getString();
      case EmfdbPackage.A__BLIST:
        return getBlist();
      case EmfdbPackage.A__CMAP:
        if (coreType) return getCmap();
        else return getCmap().map();
      case EmfdbPackage.A__STRINGS:
        return getStrings();
      case EmfdbPackage.A__PRIMITIVE_VALUES:
        return getPrimitiveValues();
      case EmfdbPackage.A__NOT_UNIQUE_VALUES:
        return getNotUniqueValues();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("all")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case EmfdbPackage.A__STRING:
        setString((String)newValue);
        return;
      case EmfdbPackage.A__BLIST:
        getBlist().clear();
        getBlist().addAll((Collection<? extends B>)newValue);
        return;
      case EmfdbPackage.A__CMAP:
        ((EStructuralFeature.Setting)getCmap()).set(newValue);
        return;
      case EmfdbPackage.A__STRINGS:
        getStrings().clear();
        getStrings().addAll((Collection<? extends String>)newValue);
        return;
      case EmfdbPackage.A__PRIMITIVE_VALUES:
        getPrimitiveValues().clear();
        getPrimitiveValues().addAll((Collection<? extends Double>)newValue);
        return;
      case EmfdbPackage.A__NOT_UNIQUE_VALUES:
        getNotUniqueValues().clear();
        getNotUniqueValues().addAll((Collection<? extends Double>)newValue);
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
      case EmfdbPackage.A__STRING:
        setString(STRING_EDEFAULT);
        return;
      case EmfdbPackage.A__BLIST:
        getBlist().clear();
        return;
      case EmfdbPackage.A__CMAP:
        getCmap().clear();
        return;
      case EmfdbPackage.A__STRINGS:
        getStrings().clear();
        return;
      case EmfdbPackage.A__PRIMITIVE_VALUES:
        getPrimitiveValues().clear();
        return;
      case EmfdbPackage.A__NOT_UNIQUE_VALUES:
        getNotUniqueValues().clear();
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
      case EmfdbPackage.A__STRING:
        return STRING_EDEFAULT == null ? string != null : !STRING_EDEFAULT.equals(string);
      case EmfdbPackage.A__BLIST:
        return blist != null && !blist.isEmpty();
      case EmfdbPackage.A__CMAP:
        return cmap != null && !cmap.isEmpty();
      case EmfdbPackage.A__STRINGS:
        return strings != null && !strings.isEmpty();
      case EmfdbPackage.A__PRIMITIVE_VALUES:
        return primitiveValues != null && !primitiveValues.isEmpty();
      case EmfdbPackage.A__NOT_UNIQUE_VALUES:
        return notUniqueValues != null && !notUniqueValues.isEmpty();
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

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (string: ");
    result.append(string);
    result.append(", strings: ");
    result.append(strings);
    result.append(", primitiveValues: ");
    result.append(primitiveValues);
    result.append(", notUniqueValues: ");
    result.append(notUniqueValues);
    result.append(')');
    return result.toString();
  }

} //AImpl
