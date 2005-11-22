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
 * $Id: EAttributeImpl.java,v 1.9 2005/11/22 22:34:11 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

//import org.eclipse.emf.ecore.InternalEObject;


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
   * The flag representing the value of the '{@link #isID() <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isID()
   * @generated
   * @ordered
   */
  protected static final int ID_EFLAG = 1 << 15;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
    return (eFlags & ID_EFLAG) != 0;
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
    boolean oldID = (eFlags & ID_EFLAG) != 0;
    if (newID) eFlags |= ID_EFLAG; else eFlags &= ~ID_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EATTRIBUTE__ID, oldID, newID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EDataType getEAttributeType()
  {
    return (EDataType)getEType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
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
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
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
    return eDynamicGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
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
    eDynamicSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
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
    eDynamicUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case EcorePackage.EATTRIBUTE__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EATTRIBUTE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.EATTRIBUTE__ORDERED:
        return ((eFlags & ORDERED_EFLAG) != 0) != ORDERED_EDEFAULT;
      case EcorePackage.EATTRIBUTE__UNIQUE:
        return ((eFlags & UNIQUE_EFLAG) != 0) != UNIQUE_EDEFAULT;
      case EcorePackage.EATTRIBUTE__LOWER_BOUND:
        return lowerBound != LOWER_BOUND_EDEFAULT;
      case EcorePackage.EATTRIBUTE__UPPER_BOUND:
        return upperBound != UPPER_BOUND_EDEFAULT;
      case EcorePackage.EATTRIBUTE__MANY:
        return isMany() != MANY_EDEFAULT;
      case EcorePackage.EATTRIBUTE__REQUIRED:
        return isRequired() != REQUIRED_EDEFAULT;
      case EcorePackage.EATTRIBUTE__ETYPE:
        return eType != null;
      case EcorePackage.EATTRIBUTE__CHANGEABLE:
        return ((eFlags & CHANGEABLE_EFLAG) != 0) != CHANGEABLE_EDEFAULT;
      case EcorePackage.EATTRIBUTE__VOLATILE:
        return ((eFlags & VOLATILE_EFLAG) != 0) != VOLATILE_EDEFAULT;
      case EcorePackage.EATTRIBUTE__TRANSIENT:
        return ((eFlags & TRANSIENT_EFLAG) != 0) != TRANSIENT_EDEFAULT;
      case EcorePackage.EATTRIBUTE__DEFAULT_VALUE_LITERAL:
        return DEFAULT_VALUE_LITERAL_EDEFAULT == null ? defaultValueLiteral != null : !DEFAULT_VALUE_LITERAL_EDEFAULT.equals(defaultValueLiteral);
      case EcorePackage.EATTRIBUTE__DEFAULT_VALUE:
        return DEFAULT_VALUE_EDEFAULT == null ? getDefaultValue() != null : !DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue());
      case EcorePackage.EATTRIBUTE__UNSETTABLE:
        return ((eFlags & UNSETTABLE_EFLAG) != 0) != UNSETTABLE_EDEFAULT;
      case EcorePackage.EATTRIBUTE__DERIVED:
        return ((eFlags & DERIVED_EFLAG) != 0) != DERIVED_EDEFAULT;
      case EcorePackage.EATTRIBUTE__ECONTAINING_CLASS:
        return getEContainingClass() != null;
      case EcorePackage.EATTRIBUTE__ID:
        return ((eFlags & ID_EFLAG) != 0) != ID_EDEFAULT;
      case EcorePackage.EATTRIBUTE__EATTRIBUTE_TYPE:
        return basicGetEAttributeType() != null;
    }
    return eDynamicIsSet(featureID);
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
    result.append((eFlags & ID_EFLAG) != 0);
    result.append(')');
    return result.toString();
  }

}
