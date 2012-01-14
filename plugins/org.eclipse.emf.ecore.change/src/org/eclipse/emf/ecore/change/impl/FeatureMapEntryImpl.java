/**
 * Copyright (c) 2003-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.change.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
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
import org.eclipse.emf.ecore.change.FeatureMapEntry;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
//import org.eclipse.emf.ecore.InternalEObject;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Map Entry</b></em>'.
 * @extends FeatureMap.Entry.Internal
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureMapEntryImpl#getFeatureName <em>Feature Name</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureMapEntryImpl#getDataValue <em>Data Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureMapEntryImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureMapEntryImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.FeatureMapEntryImpl#getReferenceValue <em>Reference Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureMapEntryImpl extends EObjectImpl implements FeatureMapEntry, FeatureMap.Entry.Internal
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
   * The default value of the '{@link #getDataValue() <em>Data Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataValue()
   * @generated
   * @ordered
   */
  protected static final String DATA_VALUE_EDEFAULT = null;

  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final Object VALUE_EDEFAULT = null;

  protected EStructuralFeature feature = null;
  
  protected String featureName = null;

  protected Object value = null;
  
  protected String valueString = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FeatureMapEntryImpl()
  {
    super();
  }

  protected FeatureMapEntryImpl(EStructuralFeature feature, Object value)
  {
    this();
    this.feature = feature;
    setValue(value);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ChangePackage.Literals.FEATURE_MAP_ENTRY;
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
    if (featureName != null)
    {
      return true;
    }
    else if (feature == null)
    {
      return false;
    }
    else
    {
      EObject greatGrandParent = getGreatGrandParent();
      return
        greatGrandParent instanceof EObjectToChangesMapEntryImpl &&
          ((EObjectToChangesMapEntryImpl)greatGrandParent).getTypedKey().eClass().getEAllStructuralFeatures().contains(feature);
    }
  }
  
  protected EObject getGreatGrandParent()
  {
    EObject container = eContainer();
    if (container != null)
    {
      container = container.eContainer();
      if (container != null)
      {
        return container.eContainer();
      }
    }
    return  null;
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
  
  public EStructuralFeature getEStructuralFeature()
  {
    return getFeature();
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
      EObject greatGrandParent = getGreatGrandParent();
      if (greatGrandParent instanceof EObjectToChangesMapEntryImpl)
      {
        feature = ((EObjectToChangesMapEntryImpl)greatGrandParent).getKey().eClass().getEStructuralFeature(featureName);
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
    if (feature == null)
    {
      return false;
    }
    else
    {
      EObject greatGrandParent = getGreatGrandParent();
      return 
        !(greatGrandParent instanceof EObjectToChangesMapEntryImpl) ||
          !((EObjectToChangesMapEntryImpl)greatGrandParent).getTypedKey().eClass().getEAllStructuralFeatures().contains(feature);
    }
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
    return feature instanceof EReference ? (EObject)value : null;
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
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ChangePackage.FEATURE_MAP_ENTRY__FEATURE_NAME:
        return getFeatureName();
      case ChangePackage.FEATURE_MAP_ENTRY__DATA_VALUE:
        return getDataValue();
      case ChangePackage.FEATURE_MAP_ENTRY__VALUE:
        return getValue();
      case ChangePackage.FEATURE_MAP_ENTRY__FEATURE:
        if (resolve) return getFeature();
        return basicGetFeature();
      case ChangePackage.FEATURE_MAP_ENTRY__REFERENCE_VALUE:
        if (resolve) return getReferenceValue();
        return basicGetReferenceValue();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ChangePackage.FEATURE_MAP_ENTRY__FEATURE_NAME:
        setFeatureName((String)newValue);
        return;
      case ChangePackage.FEATURE_MAP_ENTRY__DATA_VALUE:
        setDataValue((String)newValue);
        return;
      case ChangePackage.FEATURE_MAP_ENTRY__FEATURE:
        setFeature((EStructuralFeature)newValue);
        return;
      case ChangePackage.FEATURE_MAP_ENTRY__REFERENCE_VALUE:
        setReferenceValue((EObject)newValue);
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
      case ChangePackage.FEATURE_MAP_ENTRY__FEATURE_NAME:
        unsetFeatureName();
        return;
      case ChangePackage.FEATURE_MAP_ENTRY__DATA_VALUE:
        setDataValue(DATA_VALUE_EDEFAULT);
        return;
      case ChangePackage.FEATURE_MAP_ENTRY__FEATURE:
        unsetFeature();
        return;
      case ChangePackage.FEATURE_MAP_ENTRY__REFERENCE_VALUE:
        setReferenceValue((EObject)null);
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
      case ChangePackage.FEATURE_MAP_ENTRY__FEATURE_NAME:
        return isSetFeatureName();
      case ChangePackage.FEATURE_MAP_ENTRY__DATA_VALUE:
        return DATA_VALUE_EDEFAULT == null ? getDataValue() != null : !DATA_VALUE_EDEFAULT.equals(getDataValue());
      case ChangePackage.FEATURE_MAP_ENTRY__VALUE:
        return VALUE_EDEFAULT == null ? getValue() != null : !VALUE_EDEFAULT.equals(getValue());
      case ChangePackage.FEATURE_MAP_ENTRY__FEATURE:
        return isSetFeature();
      case ChangePackage.FEATURE_MAP_ENTRY__REFERENCE_VALUE:
        return basicGetReferenceValue() != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object getValue()
  {
    EStructuralFeature feature = getFeature();
    if (feature instanceof EReference)
    {
      return getReferenceValue();
    }
    else if (value == null) // feature is instance of EAttribute
    {
      EDataType type = (EDataType)feature.getEType();
      value = EcoreUtil.createFromString(type, valueString);
    }
    return value;    
  }

  protected void setValue(Object value)
  {
    EStructuralFeature feature = getFeature();
    if (!eNotificationRequired())
    {
      valueString = null;
      this.value = value;
    }
    else
    {
      if (feature instanceof EAttribute)
      {
        EDataType type = (EDataType)feature.getEType();
        setDataValue(valueString = EcoreUtil.convertToString(type, value));
        this.value = value;
      }
      else
      {
        setReferenceValue((EObject)value);
      }
    }
  }

  protected ListChange createListChange(EList<ListChange> changesList, ChangeKind kind, int index)
  {
    ListChange listChange = ChangeFactory.eINSTANCE.createListChange();
    listChange.setKind(kind);
    listChange.setIndex(index);
    changesList.add(listChange);
    return listChange;
  }

  public NotificationChain inverseAdd(InternalEObject owner, int featureID, NotificationChain notifications)
  {
    return ((EStructuralFeature.Internal)getFeature()).getFeatureMapEntryPrototype().inverseAdd(owner, getValue(), featureID, notifications);
  }

  public NotificationChain inverseRemove(InternalEObject owner, int featureID, NotificationChain notifications)
  {
    return ((EStructuralFeature.Internal)getFeature()).getFeatureMapEntryPrototype().inverseRemove(owner, getValue(), featureID, notifications);
  }

  public NotificationChain inverseAdd(InternalEObject owner, Object otherEnd, int featureID, NotificationChain notifications)
  {
    return ((EStructuralFeature.Internal)getFeature()).getFeatureMapEntryPrototype().inverseAdd(owner, otherEnd, featureID, notifications);
  }

  public NotificationChain inverseRemove(InternalEObject owner, Object otherEnd, int featureID, NotificationChain notifications)
  {
    return ((EStructuralFeature.Internal)getFeature()).getFeatureMapEntryPrototype().inverseRemove(owner, otherEnd, featureID, notifications);
  }

  public void validate(Object value)
  {
    ((EStructuralFeature.Internal)getFeature()).getFeatureMapEntryPrototype().validate(value);
  }

  public Internal createEntry(Object value)
  {
    return ((EStructuralFeature.Internal)getFeature()).getFeatureMapEntryPrototype().createEntry(value);
  }

  public Internal createEntry(InternalEObject value)
  {
    return ((EStructuralFeature.Internal)getFeature()).getFeatureMapEntryPrototype().createEntry(value);
  }
} //FeatureChangeImpl
