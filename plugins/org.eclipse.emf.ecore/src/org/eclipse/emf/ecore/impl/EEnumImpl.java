/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EEnumImpl.java,v 1.6 2005/06/08 06:20:10 nickb Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EEnum</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EEnumImpl#getELiterals <em>ELiterals</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EEnumImpl extends EDataTypeImpl implements EEnum
{
  /**
   * The cached value of the '{@link #getELiterals() <em>ELiterals</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getELiterals()
   * @generated
   * @ordered
   */
  protected EList eLiterals = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EEnumImpl()
  {
    super();
  }

  protected void setDataTypeGeneratedInstanceClass(boolean isGenerated)
  {
  }

  public Object getDefaultValue()
  {
    EList eLiterals = getELiterals();
    if (!eLiterals.isEmpty())
    {
      return ((EEnumLiteral)eLiterals.get(0)).getInstance();
    }
    return null;
  }

  /**
   * Determines if the specified Object is an instance of this.
   */
  public boolean isInstance(Object object)
  {
    if (object != null)
    {
      Class instanceClass = getInstanceClass();
      if (instanceClass != null)
      {
        return instanceClass.isInstance(object);
      }
      else
      {
        return getELiterals().contains(object);
      }
    }
    return false;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return EcorePackage.eINSTANCE.getEEnum();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getELiterals()
  {
    if (eLiterals == null)
    {
      eLiterals = new EObjectContainmentWithInverseEList(EEnumLiteral.class, this, EcorePackage.EENUM__ELITERALS, EcorePackage.EENUM_LITERAL__EENUM);
    }
    return eLiterals;
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
        case EcorePackage.EENUM__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
        case EcorePackage.EENUM__EPACKAGE:
          return eBasicSetContainer(null, EcorePackage.EENUM__EPACKAGE, msgs);
        case EcorePackage.EENUM__ELITERALS:
          return ((InternalEList)getELiterals()).basicRemove(otherEnd, msgs);
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
        case EcorePackage.EENUM__EPACKAGE:
          return eContainer.eInverseRemove(this, EcorePackage.EPACKAGE__ECLASSIFIERS, EPackage.class, msgs);
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
      case EcorePackage.EENUM__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.EENUM__NAME:
        return getName();
      case EcorePackage.EENUM__INSTANCE_CLASS_NAME:
        return getInstanceClassName();
      case EcorePackage.EENUM__INSTANCE_CLASS:
        return getInstanceClass();
      case EcorePackage.EENUM__DEFAULT_VALUE:
        return getDefaultValue();
      case EcorePackage.EENUM__EPACKAGE:
        return getEPackage();
      case EcorePackage.EENUM__SERIALIZABLE:
        return isSerializable() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EENUM__ELITERALS:
        return getELiterals();
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
      case EcorePackage.EENUM__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EENUM__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.EENUM__INSTANCE_CLASS_NAME:
        return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
      case EcorePackage.EENUM__INSTANCE_CLASS:
        return INSTANCE_CLASS_EDEFAULT == null ? getInstanceClass() != null : !INSTANCE_CLASS_EDEFAULT.equals(getInstanceClass());
      case EcorePackage.EENUM__DEFAULT_VALUE:
        return DEFAULT_VALUE_EDEFAULT == null ? getDefaultValue() != null : !DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue());
      case EcorePackage.EENUM__EPACKAGE:
        return getEPackage() != null;
      case EcorePackage.EENUM__SERIALIZABLE:
        return ((eFlags & SERIALIZABLE_EFLAG) != 0) != SERIALIZABLE_EDEFAULT;
      case EcorePackage.EENUM__ELITERALS:
        return eLiterals != null && !eLiterals.isEmpty();
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
      case EcorePackage.EENUM__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.EENUM__NAME:
        setName((String)newValue);
        return;
      case EcorePackage.EENUM__INSTANCE_CLASS_NAME:
        setInstanceClassName((String)newValue);
        return;
      case EcorePackage.EENUM__SERIALIZABLE:
        setSerializable(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EENUM__ELITERALS:
        getELiterals().clear();
        getELiterals().addAll((Collection)newValue);
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
      case EcorePackage.EENUM__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.EENUM__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.EENUM__INSTANCE_CLASS_NAME:
        setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
        return;
      case EcorePackage.EENUM__SERIALIZABLE:
        setSerializable(SERIALIZABLE_EDEFAULT);
        return;
      case EcorePackage.EENUM__ELITERALS:
        getELiterals().clear();
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * @generated modifiable
   */
  public EEnumLiteral getEEnumLiteral(String stringValue)
  {
    for (Iterator i = getELiterals().iterator(); i.hasNext(); )
    {
      EEnumLiteral eEnumLiteral = (EEnumLiteral) i.next();
      if (eEnumLiteral.getName().equals(stringValue))
      {
        return eEnumLiteral;
      }
    }
    return null;
  }

  /**
   * @generated modifiable
   */
  public EEnumLiteral getEEnumLiteral(int intValue)
  {
    for (Iterator i = getELiterals().iterator(); i.hasNext(); )
    {
      EEnumLiteral eEnumLiteral = (EEnumLiteral) i.next();
      if (eEnumLiteral.getValue() == intValue)
      {
        return eEnumLiteral;
      }
    }
    return null;
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
        case EcorePackage.EENUM__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
        case EcorePackage.EENUM__EPACKAGE:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, EcorePackage.EENUM__EPACKAGE, msgs);
        case EcorePackage.EENUM__ELITERALS:
          return ((InternalEList)getELiterals()).basicAdd(otherEnd, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
  }

}
