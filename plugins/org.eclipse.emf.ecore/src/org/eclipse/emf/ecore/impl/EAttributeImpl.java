/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EAttributeImpl.java,v 1.2 2004/05/06 18:37:13 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EAttribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EAttributeImpl#isID <em>ID</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EAttributeImpl#getEAttributeType <em>EAttribute Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EAttributeImpl extends EStructuralFeatureImpl implements EAttribute
{
  /**
   * The default value of the '{@link #isID() <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isID()
   * @generated
   * @ordered
   */
  protected static final boolean ID_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isID() <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isID()
   * @generated
   * @ordered
   */
  protected boolean iD = ID_EDEFAULT;

  protected EAttributeImpl()
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
    return EcorePackage.eINSTANCE.getEAttribute();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isID()
  {
    return iD;
  }

  protected byte effectiveIsMany;

  public boolean isMany()
  {
    switch (effectiveIsMany)
    {
      case -1:
      {
        return true;
      }
      case 0:
      {
        int upper = getUpperBound();
        if (upper > 1 || upper == UNBOUNDED_MULTIPLICITY)
        {
          effectiveIsMany = -1;
          return true;
        }
        else 
        {
          EClassifier eType = getEType();
          if (eType != null && FeatureMapUtil.isFeatureMapEntry(eType))
          {
            effectiveIsMany = -1;
            return true;
          }
          else
          {
            effectiveIsMany = 1;
            return false;
          }
        }
      }
      default:
      case 1:
      {
        return false;
      }
    }
  }

  public void setUpperBound(int upperBound)
  {
    effectiveIsMany = 0;
    super.setUpperBound(upperBound);
  }

  public void setEType(EClassifier eType)
  {
    effectiveIsMany = 0;
    super.setEType(eType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setID(boolean newID)
  {
    boolean oldID = iD;
    iD = newID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EATTRIBUTE__ID, oldID, iD));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public EDataType getEAttributeType()
  {
    return (EDataType)getEType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public EDataType basicGetEAttributeType()
  {
    return (EDataType)eType;
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
        case EcorePackage.EATTRIBUTE__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
        case EcorePackage.EATTRIBUTE__ECONTAINING_CLASS:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, EcorePackage.EATTRIBUTE__ECONTAINING_CLASS, msgs);
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
        case EcorePackage.EATTRIBUTE__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
        case EcorePackage.EATTRIBUTE__ECONTAINING_CLASS:
          return eBasicSetContainer(null, EcorePackage.EATTRIBUTE__ECONTAINING_CLASS, msgs);
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
        case EcorePackage.EATTRIBUTE__ECONTAINING_CLASS:
          return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.ECLASS__ESTRUCTURAL_FEATURES, EClass.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case EcorePackage.EATTRIBUTE__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.EATTRIBUTE__NAME:
        return getName();
      case EcorePackage.EATTRIBUTE__ORDERED:
        return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EATTRIBUTE__UNIQUE:
        return isUnique() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EATTRIBUTE__LOWER_BOUND:
        return new Integer(getLowerBound());
      case EcorePackage.EATTRIBUTE__UPPER_BOUND:
        return new Integer(getUpperBound());
      case EcorePackage.EATTRIBUTE__MANY:
        return isMany() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EATTRIBUTE__REQUIRED:
        return isRequired() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EATTRIBUTE__ETYPE:
        if (resolve) return getEType();
        return basicGetEType();
      case EcorePackage.EATTRIBUTE__CHANGEABLE:
        return isChangeable() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EATTRIBUTE__VOLATILE:
        return isVolatile() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EATTRIBUTE__TRANSIENT:
        return isTransient() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EATTRIBUTE__DEFAULT_VALUE_LITERAL:
        return getDefaultValueLiteral();
      case EcorePackage.EATTRIBUTE__DEFAULT_VALUE:
        return getDefaultValue();
      case EcorePackage.EATTRIBUTE__UNSETTABLE:
        return isUnsettable() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EATTRIBUTE__DERIVED:
        return isDerived() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EATTRIBUTE__ECONTAINING_CLASS:
        return getEContainingClass();
      case EcorePackage.EATTRIBUTE__ID:
        return isID() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EATTRIBUTE__EATTRIBUTE_TYPE:
        if (resolve) return getEAttributeType();
        return basicGetEAttributeType();
    }
    return eDynamicGet(eFeature, resolve);
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
      case EcorePackage.EATTRIBUTE__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EATTRIBUTE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.EATTRIBUTE__ORDERED:
        return ordered != ORDERED_EDEFAULT;
      case EcorePackage.EATTRIBUTE__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case EcorePackage.EATTRIBUTE__LOWER_BOUND:
        return lowerBound != LOWER_BOUND_EDEFAULT;
      case EcorePackage.EATTRIBUTE__UPPER_BOUND:
        return upperBound != UPPER_BOUND_EDEFAULT;
      case EcorePackage.EATTRIBUTE__MANY:
        return isMany() != false;
      case EcorePackage.EATTRIBUTE__REQUIRED:
        return isRequired() != false;
      case EcorePackage.EATTRIBUTE__ETYPE:
        return eType != null;
      case EcorePackage.EATTRIBUTE__CHANGEABLE:
        return changeable != CHANGEABLE_EDEFAULT;
      case EcorePackage.EATTRIBUTE__VOLATILE:
        return volatile_ != VOLATILE_EDEFAULT;
      case EcorePackage.EATTRIBUTE__TRANSIENT:
        return transient_ != TRANSIENT_EDEFAULT;
      case EcorePackage.EATTRIBUTE__DEFAULT_VALUE_LITERAL:
        return DEFAULT_VALUE_LITERAL_EDEFAULT == null ? defaultValueLiteral != null : !DEFAULT_VALUE_LITERAL_EDEFAULT.equals(defaultValueLiteral);
      case EcorePackage.EATTRIBUTE__DEFAULT_VALUE:
        return getDefaultValue() != null;
      case EcorePackage.EATTRIBUTE__UNSETTABLE:
        return unsettable != UNSETTABLE_EDEFAULT;
      case EcorePackage.EATTRIBUTE__DERIVED:
        return derived != DERIVED_EDEFAULT;
      case EcorePackage.EATTRIBUTE__ECONTAINING_CLASS:
        return getEContainingClass() != null;
      case EcorePackage.EATTRIBUTE__ID:
        return iD != ID_EDEFAULT;
      case EcorePackage.EATTRIBUTE__EATTRIBUTE_TYPE:
        return basicGetEAttributeType() != null;
    }
    return eDynamicIsSet(eFeature);
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
      case EcorePackage.EATTRIBUTE__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.EATTRIBUTE__NAME:
        setName((String)newValue);
        return;
      case EcorePackage.EATTRIBUTE__ORDERED:
        setOrdered(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EATTRIBUTE__UNIQUE:
        setUnique(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EATTRIBUTE__LOWER_BOUND:
        setLowerBound(((Integer)newValue).intValue());
        return;
      case EcorePackage.EATTRIBUTE__UPPER_BOUND:
        setUpperBound(((Integer)newValue).intValue());
        return;
      case EcorePackage.EATTRIBUTE__ETYPE:
        setEType((EClassifier)newValue);
        return;
      case EcorePackage.EATTRIBUTE__CHANGEABLE:
        setChangeable(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EATTRIBUTE__VOLATILE:
        setVolatile(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EATTRIBUTE__TRANSIENT:
        setTransient(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EATTRIBUTE__DEFAULT_VALUE_LITERAL:
        setDefaultValueLiteral((String)newValue);
        return;
      case EcorePackage.EATTRIBUTE__UNSETTABLE:
        setUnsettable(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EATTRIBUTE__DERIVED:
        setDerived(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EATTRIBUTE__ID:
        setID(((Boolean)newValue).booleanValue());
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
      case EcorePackage.EATTRIBUTE__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.EATTRIBUTE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__LOWER_BOUND:
        setLowerBound(LOWER_BOUND_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__UPPER_BOUND:
        setUpperBound(UPPER_BOUND_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__ETYPE:
        setEType((EClassifier)null);
        return;
      case EcorePackage.EATTRIBUTE__CHANGEABLE:
        setChangeable(CHANGEABLE_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__VOLATILE:
        setVolatile(VOLATILE_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__TRANSIENT:
        setTransient(TRANSIENT_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__DEFAULT_VALUE_LITERAL:
        setDefaultValueLiteral(DEFAULT_VALUE_LITERAL_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__UNSETTABLE:
        setUnsettable(UNSETTABLE_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__DERIVED:
        setDerived(DERIVED_EDEFAULT);
        return;
      case EcorePackage.EATTRIBUTE__ID:
        setID(ID_EDEFAULT);
        return;
    }
    eDynamicUnset(eFeature);
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
    result.append(" (iD: ");
    result.append(iD);
    result.append(')');
    return result.toString();
  }

}
