/**
 * <copyright>
 *
 * Copyright (c) 2003-2011 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: FeatureChangeImpl.java,v 1.6.2.1 2011/08/29 19:15:56 khussey Exp $
 */
package org.eclipse.emf.ecore.change.impl;


import com.google.gwt.user.client.rpc.GwtTransient;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
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
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
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
  @GwtTransient
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
   * The cached value of the '{@link #getListChanges() <em>List Changes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getListChanges()
   * @generated
   * @ordered
   */
  @GwtTransient
  protected EList<ListChange> listChanges;

  @GwtTransient
  protected EStructuralFeature feature;

  @GwtTransient
  protected String featureName;

  @GwtTransient
  protected Object value;

  @GwtTransient
  protected String valueString;

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
  @Override
  protected EClass eStaticClass()
  {
    return ChangePackage.Literals.FEATURE_CHANGE;
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
    return
      featureName != null ||
        feature != null &&
         eContainer() instanceof EObjectToChangesMapEntryImpl;
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
      if (feature instanceof EAttribute && !feature.isMany())
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
      if (featureName != null)
      {
        EObject container = eContainer();
        if (container instanceof EObjectToChangesMapEntryImpl)
        {
          EObject typedKey = ((EObjectToChangesMapEntryImpl)container).getTypedKey();
          if (typedKey != null)
          {
            feature = typedKey.eClass().getEStructuralFeature(featureName);
          }
        }
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
    // If there is a feature name, we should try to get the feature so we can correctly determine if we should return the value.
    //
    if (feature == null && featureName != null)
    {
      getFeature();
    }
    return (feature instanceof EReference && value instanceof EObject) ? (EObject)value : null;
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
  public EList<ListChange> getListChanges()
  {
    if (listChanges == null)
    {
      listChanges = new EObjectContainmentEList<ListChange>(ListChange.class, this, ChangePackage.FEATURE_CHANGE__LIST_CHANGES);
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
    if (feature.getUpperBound() != 1)
    {
      if (value == null && eContainer() instanceof EObjectToChangesMapEntryImpl)
      {
        value = getListValue((EList<?>)((EObjectToChangesMapEntryImpl)eContainer()).getTypedKey().eGet(feature));
      }
    }
    else if (feature instanceof EReference)
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

  protected EList<?> getListValue(EList<?> originalList)
  {
    if (isSet() && getFeature().getUpperBound() != 1)
    {
      if (value instanceof EList<?>) // cached already?
      {
        return (EList<?>)value;
      }

      EList<Object> changedList =  new BasicEList<Object>(originalList);
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
    apply(originalObject, false);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void applyAndReverse(EObject originalObject)
  {
    apply(originalObject, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void reverse(EObject originalObject)
  {
    process(originalObject, true, false);
  }

  protected void apply(EObject originalObject, boolean reverse)
  {
    process(originalObject, reverse, true);
  }

  protected void process(EObject originalObject, boolean reverse, boolean apply)
  {
    EStructuralFeature.Internal internalFeature = (EStructuralFeature.Internal)getFeature();
    if (internalFeature != null && internalFeature.isChangeable())
    {
      if (!internalFeature.isContainer())
      {
        if (!isSet())
        {
          if (reverse && internalFeature.isMany())
          {
            ListChange listChange = createListChange(getListChanges(), ChangeKind.ADD_LITERAL, 0);
            if (internalFeature.getEOpposite() != null || internalFeature.isContainment())
            {
              listChange.getValues().addAll((EList<?>)getValue());
            }
            else
            {
              listChange.getValues().addAll((EList<?>)originalObject.eGet(internalFeature));
            }
          }
          if (apply)
          {
            originalObject.eUnset(internalFeature);
          }
        }
        else if (internalFeature.isMany())
        {
          if (listChanges != null)
          {
            if (internalFeature.isFeatureMap())
            {
              FeatureMap.Internal result = (FeatureMap.Internal)originalObject.eGet(internalFeature);
              if (apply)
              {
                @SuppressWarnings("unchecked") EList<FeatureMap.Entry.Internal> prototype = (EList<FeatureMap.Entry.Internal>)getValue();
                EList<FeatureMap.Entry> featureMapEntryList = new BasicEList<FeatureMap.Entry>(prototype.size());
                for (FeatureMap.Entry.Internal entry : prototype)
                {
                  Object entryValue = entry.getValue();
                  // Create a proper feature map entry.
                  //
                  entry = entry.createEntry(entryValue);
                  featureMapEntryList.add(entry);
                  EStructuralFeature entryFeature = entry.getEStructuralFeature();
                  if (!FeatureMapUtil.isMany(originalObject, entry.getEStructuralFeature()))
                  {
                    for (int i = 0, size = result.size(); i < size; ++i)
                    {
                      if (result.getEStructuralFeature(i) == entryFeature && !(entryValue == null ? result.getValue(i) == null : entryValue.equals(result.getValue(i))))
                      {
                        result.set(i, entry);
                      }
                    }
                  }
                }
                ECollections.setEList(result, featureMapEntryList);
              }
              else
              {
                newValue = new BasicEList<Object>(result);
              }
            }
            else if (internalFeature.getEOpposite() != null || internalFeature.isContainment())
            {
              @SuppressWarnings("unchecked") EList<Object> result = (EList<Object>)originalObject.eGet(internalFeature);
              if (apply)
              {
                // Bidirectional references need to use this less efficient approach because some
                //  or all of the changes may already have been made from the other end.
                //
                @SuppressWarnings("unchecked") EList<Object> prototype = (EList<Object>)getValue();
                ECollections.setEList(result, prototype);
              }
              else
              {
                newValue = new BasicEList<Object>(result);
              }
            }
            else
            {
              @SuppressWarnings("unchecked") EList<Object> applyToList = (EList<Object>)originalObject.eGet(internalFeature);
              if (apply)
              {
                if (reverse)
                {
                  applyAndReverse(applyToList);
                }
                else
                {
                  apply(applyToList);
                }
              }
              else
              {
                reverse(applyToList);
              }
            }

            if (reverse)
            {
              ECollections.reverse(getListChanges());
            }
          }
        }
        else if (apply)
        {
          originalObject.eSet(internalFeature, getValue());
        }
      }

      if (reverse)
      {
        setSet(newIsSet);
        setValue(newValue);

        if (!isSet())
        {
          getListChanges().clear();
        }
      }
    }
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
      case ChangePackage.FEATURE_CHANGE__LIST_CHANGES:
        return ((InternalEList<?>)getListChanges()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  @GwtTransient
  protected boolean newIsSet;

  @GwtTransient
  protected Object newValue;

  public void preApply(EObject originalObject, boolean reverse)
  {
    if (reverse)
    {
      EStructuralFeature feature = getFeature();
      if (feature == EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES)
      {
        newIsSet = !((EClass)originalObject).getEGenericSuperTypes().isEmpty();
        newValue = null;
      }
      else if (feature == EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE)
      {
        newValue = ((ETypedElement)originalObject).getEGenericType();
        newIsSet = newValue != null;
      }
      else if (feature == EcorePackage.Literals.EOPERATION__EGENERIC_EXCEPTIONS)
      {
        newIsSet = !((EOperation)originalObject).getEGenericExceptions().isEmpty();
        newValue = null;
      }
      else if (feature == EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME)
      {
        newValue = ((EClassifier)originalObject).getInstanceTypeName();
        newIsSet = newValue != null;
      }
      else if (feature != null)
      {
        newIsSet = originalObject.eIsSet(feature);
        newValue = feature.isMany() ? null : originalObject.eGet(feature);
      }
    }
  }

  protected void apply(EList<Object> toList)
  {
    for (ListChange listChange : getListChanges())
    {
      listChange.apply(toList);
    }
  }

  protected void applyAndReverse(EList<Object> toList)
  {
    for (ListChange listChange : getListChanges())
    {
      listChange.applyAndReverse(toList);
    }
  }

  protected void reverse(EList<Object> toList)
  {
    EList<Object> copy = null;
    for (ListChange listChange : getListChanges())
    {
      if (listChange.getKind() == ChangeKind.REMOVE_LITERAL)
      {
        if (copy == null)
        {
          copy = new BasicEList<Object>(toList);
        }
        listChange.applyAndReverse(copy);
      }
      else
      {
        listChange.reverse(toList);
      } 
    }
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
      case ChangePackage.FEATURE_CHANGE__FEATURE_NAME:
        return getFeatureName();
      case ChangePackage.FEATURE_CHANGE__DATA_VALUE:
        return getDataValue();
      case ChangePackage.FEATURE_CHANGE__SET:
        return isSet();
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
      case ChangePackage.FEATURE_CHANGE__FEATURE_NAME:
        setFeatureName((String)newValue);
        return;
      case ChangePackage.FEATURE_CHANGE__DATA_VALUE:
        setDataValue((String)newValue);
        return;
      case ChangePackage.FEATURE_CHANGE__SET:
        setSet((Boolean)newValue);
        return;
      case ChangePackage.FEATURE_CHANGE__FEATURE:
        setFeature((EStructuralFeature)newValue);
        return;
      case ChangePackage.FEATURE_CHANGE__REFERENCE_VALUE:
        setReferenceValue((EObject)newValue);
        return;
      case ChangePackage.FEATURE_CHANGE__LIST_CHANGES:
        getListChanges().clear();
        getListChanges().addAll((Collection<? extends ListChange>)newValue);
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
    result.append(" (set: ");
    result.append(set);
    result.append(')');
    return result.toString();
  }

  protected ListChange createListChange(EList<ListChange> changesList, ChangeKind kind, int index)
  {
    ListChange listChange = ChangeFactory.eINSTANCE.createListChange();
    listChange.setKind(kind);
    listChange.setIndex(index);
    changesList.add(listChange);
    return listChange;
  }
} //FeatureChangeImpl