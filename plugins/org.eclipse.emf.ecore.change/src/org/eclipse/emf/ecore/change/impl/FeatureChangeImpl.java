/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: FeatureChangeImpl.java,v 1.8 2004/11/03 16:07:02 marcelop Exp $
 */
package org.eclipse.emf.ecore.change.impl;


import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.change.ChangeFactory;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.ChangePackage;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureChangeImpl#getFeatureName <em>Feature Name</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureChangeImpl#getDataValue <em>Data Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureChangeImpl#isSet <em>Set</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureChangeImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureChangeImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureChangeImpl#getReferenceValue <em>Reference Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureChangeImpl#getListChanges <em>List Changes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureChangeImpl extends EObjectImpl implements FeatureChange
{
  /**
   * The bit of {@link #eFlags} that is used to represent if feature is a proxy.
   */
  protected static final int EPROXY_FEATURECHANGE = ELAST_EOBJECT_FLAG << 1;
  
  /**
   * The default value of the '{@link #getFeatureName() <em>Feature Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatureName()
   * @generated
   * @ordered
   */
  protected static final String FEATURE_NAME_EDEFAULT = null;

  /**
   * This is true if the Feature Name attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean featureNameESet = false;

  /**
   * The default value of the '{@link #getDataValue() <em>Data Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataValue()
   * @generated
   * @ordered
   */
  protected static final String DATA_VALUE_EDEFAULT = null;

  /**
   * The default value of the '{@link #isSet() <em>Set</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSet()
   * @generated
   * @ordered
   */
  protected static final boolean SET_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isSet() <em>Set</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSet()
   * @generated
   * @ordered
   */
  protected boolean set = SET_EDEFAULT;

  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final Object VALUE_EDEFAULT = null;

  /**
   * This is true if the Feature reference has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean featureESet = false;

  /**
   * The cached value of the '{@link #getListChanges() <em>List Changes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getListChanges()
   * @generated
   * @ordered
   */
  protected EList listChanges = null;
  
  protected EStructuralFeature feature = null;
  
  protected String featureName = null;

  protected Object value = null;
  
  protected String valueString = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FeatureChangeImpl()
  {
    super();
  }

  protected FeatureChangeImpl(EStructuralFeature feature, Object value, boolean isSet)
  {
    this();
    this.feature = feature;
    setValue(value);
    this.set = isSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return ChangePackage.eINSTANCE.getFeatureChange();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getFeatureName()
  {
    return feature == null ? featureName : feature.getName();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setFeatureName(String newFeatureName)
  {
    featureName = newFeatureName;
    feature = null;
    eFlags &= ~EPROXY_FEATURECHANGE; 
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void unsetFeatureName()
  {
    setFeatureName(null);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean isSetFeatureName()
  {
    return (feature != null || featureName != null) && eContainer() instanceof EObjectToChangesMapEntryImpl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getDataValue()
  {
    if (valueString == null)
    {
      EStructuralFeature feature = getFeature();
      if (feature instanceof EAttribute)
      {
        EDataType type = (EDataType)feature.getEType();
        valueString = EcoreUtil.convertToString(type, value);
      }
    }
    return valueString;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setDataValue(String newDataValue)
  {
    String oldDataValue = getDataValue();
    valueString = newDataValue;
    value = null;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ChangePackage.FEATURE_CHANGE__DATA_VALUE, oldDataValue, newDataValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSet()
  {
    return set;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSet(boolean newSet)
  {
    boolean oldSet = set;
    set = newSet;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ChangePackage.FEATURE_CHANGE__SET, oldSet, set));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EStructuralFeature getFeature()
  {
    if (feature == null)
    {
      if (eContainer() instanceof EObjectToChangesMapEntryImpl)
      {
        feature = ((EObject)((Map.Entry)eContainer()).getKey()).eClass().getEStructuralFeature((String)featureName);
      }
    }
    else if ((eFlags & EPROXY_FEATURECHANGE) !=0)
    {
      EStructuralFeature oldFeature = feature;
      feature = (EStructuralFeature)EcoreUtil.resolve(feature, this);
      if (feature != oldFeature)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ChangePackage.FEATURE_CHANGE__FEATURE, oldFeature, feature));
      }
      eFlags &= ~ EPROXY_FEATURECHANGE;
    }
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EStructuralFeature basicGetFeature()
  {
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setFeature(EStructuralFeature newFeature)
  {
    EStructuralFeature oldFeature = basicGetFeature();
    feature = newFeature;
    featureName = null;
    if (feature != null && feature.eIsProxy())
      eFlags |= EPROXY_FEATURECHANGE;
    else 
      eFlags &= ~EPROXY_FEATURECHANGE;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ChangePackage.FEATURE_CHANGE__FEATURE, oldFeature, newFeature));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void unsetFeature()
  {
    setFeature(null);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean isSetFeature()
  {
    return feature != null && !(eContainer() instanceof EObjectToChangesMapEntryImpl);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EObject getReferenceValue()
  {
    EObject referenceValue = basicGetReferenceValue();
    if (referenceValue != null && referenceValue.eIsProxy())
    {
      EObject oldReferenceValue = referenceValue;
      referenceValue = EcoreUtil.resolve(referenceValue, this);
      if (referenceValue != oldReferenceValue)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ChangePackage.FEATURE_CHANGE__REFERENCE_VALUE, oldReferenceValue, referenceValue));
      }
    }
    return referenceValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EObject basicGetReferenceValue()
  {
    return 
      value instanceof EObject ?
        (EObject)value :
        null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setReferenceValue(EObject newReferenceValue)
  {
    EObject oldReferenceValue = basicGetReferenceValue();
    value = newReferenceValue;
    valueString = null;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ChangePackage.FEATURE_CHANGE__REFERENCE_VALUE, oldReferenceValue, newReferenceValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getListChanges()
  {
    if (listChanges == null)
    {
      listChanges = new EObjectContainmentEList(ListChange.class, this, ChangePackage.FEATURE_CHANGE__LIST_CHANGES);
    }
    return listChanges;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object getValue()
  {
    EStructuralFeature feature = getFeature();
    if (feature.isMany())
    {
      if (value == null && eContainer() instanceof EObjectToChangesMapEntryImpl)
      {
        value = getListValue((EList)((EObject)((Map.Entry)eContainer()).getKey()).eGet(feature));
      }
    }
    else if (value == null)
    {
      if (feature instanceof EAttribute)
      {
        EDataType type = (EDataType)feature.getEType();
        value = EcoreUtil.createFromString(type, valueString);
      }
      else
      {
        value = getReferenceValue();
      }
    }
    return value;
  }

  protected void setValue(Object value)
  {
    EStructuralFeature feature = getFeature();
    if (!eNotificationRequired() || feature.isMany())
    {
      valueString = null;
      this.value = value;
    }
    else
    {
      if (feature instanceof EAttribute)
      {
        EDataType type = (EDataType)feature.getEType();
        setDataValue(EcoreUtil.convertToString(type, value));
        this.value = value;
      }
      else
      {
        setReferenceValue((EObject)value);
      }
    }    
  }

  protected EList getListValue(EList originalList)
  {
    if (isSet() && getFeature().isMany())
    {
      if (value instanceof EList) // cached already?
      {
        return (EList)value;
      }
      EList changedList = new BasicEList(originalList);
      apply(changedList);
      value = changedList; // cache result
      return changedList;
    }
    return ECollections.EMPTY_ELIST;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void apply(EObject originalObject)
  {
    EStructuralFeature feature = getFeature();
    if (feature != null  && feature.isChangeable())
    {
      if (!isSet())
      {
        originalObject.eUnset(feature);
      }
      else if (feature.isMany())
      {
        if (listChanges != null)
        {
          if (feature instanceof EReference && (((EReference)feature).getEOpposite() != null || ((EReference)feature).isContainment()))
          {
            // Bi-directional references need to use this less efficient approach because some
            //  or all of the changes may already have been made from the other end.
            //
            EcoreUtil.setEList((EList)originalObject.eGet(feature), (EList)getValue());
          }
          else
          {
            apply((EList)originalObject.eGet(feature));
          }
        }
      }
      else
      {
        originalObject.eSet(feature, getValue());
      }
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void applyAndReverse(EObject originalObject)
  {
    EStructuralFeature feature = getFeature();
    boolean isEReference = feature instanceof EReference;
    
    if (feature != null && feature.isChangeable() && (!isEReference || !((EReference)feature).isContainer()))
    {
      if (!isSet())
      {
        if (feature.isMany())
        {
          ListChange listChange = ChangeFactory.eINSTANCE.createListChange();
          listChange.setKind(ChangeKind.ADD_LITERAL);
          listChange.setIndex(0);
          getListChanges().add(listChange);
          if (isEReference && (((EReference)feature).getEOpposite() != null || ((EReference)feature).isContainment()))
          {
            listChange.getValues().addAll((EList)getValue());
          }
          else
          {
            listChange.getValues().addAll((EList)originalObject.eGet(feature));
          }
        }
        originalObject.eUnset(feature);
      }
      else if (feature.isMany())
      {
        if (listChanges != null)
        {
          if (isEReference && (((EReference)feature).getEOpposite() != null || ((EReference)feature).isContainment()))
          {
            // Bi-directional references need to use this less efficient approach because some
            //  or all of the changes may already have been made from the other end.
            //
            EcoreUtil.setEList((EList)originalObject.eGet(feature), (EList)getValue());
          }
          else
          {
            EList applyToList = (EList)originalObject.eGet(feature);
            for (Iterator iter = getListChanges().iterator(); iter.hasNext(); )
            {
              ListChange listChange = (ListChange)iter.next();
              listChange.applyAndReverse(applyToList);
            }
          }
          ECollections.reverse(getListChanges());
        }
      }
      else
      {
        originalObject.eSet(feature, getValue());
      }

      setSet(newIsSet);
      setValue(newValue);
      
      if(!isSet())
      {
        getListChanges().clear();
      }
    }
  }
  
  protected boolean newIsSet;
  protected Object newValue;
  
  public void preApply(EObject originalObject, boolean reverse)
  {
    if (reverse)
    {
      EStructuralFeature feature = getFeature();
      if (feature != null)
      {
        newIsSet = originalObject.eIsSet(feature);
        newValue = feature.isMany() ? null : originalObject.eGet(feature);
      }
    }
  }

  protected void apply(EList toList)
  {
    for (Iterator iter = getListChanges().iterator(); iter.hasNext(); )
    {
      ListChange listChange = (ListChange)iter.next();
      listChange.apply(toList);
    }
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
        case ChangePackage.FEATURE_CHANGE__LIST_CHANGES:
          return ((InternalEList)getListChanges()).basicRemove(otherEnd, msgs);
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
      case ChangePackage.FEATURE_CHANGE__FEATURE_NAME:
        return getFeatureName();
      case ChangePackage.FEATURE_CHANGE__DATA_VALUE:
        return getDataValue();
      case ChangePackage.FEATURE_CHANGE__SET:
        return isSet() ? Boolean.TRUE : Boolean.FALSE;
      case ChangePackage.FEATURE_CHANGE__VALUE:
        return getValue();
      case ChangePackage.FEATURE_CHANGE__FEATURE:
        if (resolve) return getFeature();
        return basicGetFeature();
      case ChangePackage.FEATURE_CHANGE__REFERENCE_VALUE:
        if (resolve) return getReferenceValue();
        return basicGetReferenceValue();
      case ChangePackage.FEATURE_CHANGE__LIST_CHANGES:
        return getListChanges();
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
      case ChangePackage.FEATURE_CHANGE__FEATURE_NAME:
        setFeatureName((String)newValue);
        return;
      case ChangePackage.FEATURE_CHANGE__DATA_VALUE:
        setDataValue((String)newValue);
        return;
      case ChangePackage.FEATURE_CHANGE__SET:
        setSet(((Boolean)newValue).booleanValue());
        return;
      case ChangePackage.FEATURE_CHANGE__FEATURE:
        setFeature((EStructuralFeature)newValue);
        return;
      case ChangePackage.FEATURE_CHANGE__REFERENCE_VALUE:
        setReferenceValue((EObject)newValue);
        return;
      case ChangePackage.FEATURE_CHANGE__LIST_CHANGES:
        getListChanges().clear();
        getListChanges().addAll((Collection)newValue);
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
      case ChangePackage.FEATURE_CHANGE__FEATURE_NAME:
        unsetFeatureName();
        return;
      case ChangePackage.FEATURE_CHANGE__DATA_VALUE:
        setDataValue(DATA_VALUE_EDEFAULT);
        return;
      case ChangePackage.FEATURE_CHANGE__SET:
        setSet(SET_EDEFAULT);
        return;
      case ChangePackage.FEATURE_CHANGE__FEATURE:
        unsetFeature();
        return;
      case ChangePackage.FEATURE_CHANGE__REFERENCE_VALUE:
        setReferenceValue((EObject)null);
        return;
      case ChangePackage.FEATURE_CHANGE__LIST_CHANGES:
        getListChanges().clear();
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
      case ChangePackage.FEATURE_CHANGE__FEATURE_NAME:
        return isSetFeatureName();
      case ChangePackage.FEATURE_CHANGE__DATA_VALUE:
        return DATA_VALUE_EDEFAULT == null ? getDataValue() != null : !DATA_VALUE_EDEFAULT.equals(getDataValue());
      case ChangePackage.FEATURE_CHANGE__SET:
        return set != SET_EDEFAULT;
      case ChangePackage.FEATURE_CHANGE__VALUE:
        return VALUE_EDEFAULT == null ? getValue() != null : !VALUE_EDEFAULT.equals(getValue());
      case ChangePackage.FEATURE_CHANGE__FEATURE:
        return isSetFeature();
      case ChangePackage.FEATURE_CHANGE__REFERENCE_VALUE:
        return basicGetReferenceValue() != null;
      case ChangePackage.FEATURE_CHANGE__LIST_CHANGES:
        return listChanges != null && !listChanges.isEmpty();
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
    result.append(" (set: ");
    result.append(set);
    result.append(')');
    return result.toString();
  }

} //FeatureChangeImpl
