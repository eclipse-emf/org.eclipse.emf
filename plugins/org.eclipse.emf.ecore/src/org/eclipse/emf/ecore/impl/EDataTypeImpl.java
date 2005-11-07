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
 * $Id: EDataTypeImpl.java,v 1.7 2005/11/07 21:59:29 davidms Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EData Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EDataTypeImpl#isSerializable <em>Serializable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EDataTypeImpl extends EClassifierImpl implements EDataType
{
  /**
   * The default value of the '{@link #isSerializable() <em>Serializable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSerializable()
   * @generated
   * @ordered
   */
  protected static final boolean SERIALIZABLE_EDEFAULT = true;

  /**
   * The flag representing the value of the '{@link #isSerializable() <em>Serializable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSerializable()
   * @generated
   * @ordered
   */
  protected static final int SERIALIZABLE_EFLAG = 1 << 8;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EDataTypeImpl()
  {
    super();
    eFlags |= SERIALIZABLE_EFLAG;
  }

  protected Object defaultValue = null;
  protected boolean defaultValueIsSet = false;

  public Object getDefaultValue()
  {
    if (!defaultValueIsSet)
    {
      Class instanceClass = null;
      try
      {
        instanceClass = getInstanceClass();
      }
      catch (Exception e) {}

      defaultValue = null;
      if (instanceClass != null && instanceClass.isPrimitive())
      {
        if (instanceClass == Boolean.TYPE)
          defaultValue = Boolean.FALSE;
        else if (instanceClass == Integer.TYPE)
          defaultValue = new Integer(0);
        else if (instanceClass == Float.TYPE)
          defaultValue = new Float(0.0F);
        else if (instanceClass == Double.TYPE)
          defaultValue = new Double(0.0);
        else if (instanceClass == Long.TYPE)
          defaultValue = new Long(0);
        else if (instanceClass == Short.TYPE)
          defaultValue = new Short((short)0);
        else if (instanceClass == Byte.TYPE)
          defaultValue = new Byte((byte)0);
        else // if (instanceClass == Character.TYPE)
          defaultValue = new Character('\u0000');
      }
      defaultValueIsSet = true;
    }
    return defaultValue;
  }

  public void setInstanceClassGen(Class instanceClass)
  {
    super.setInstanceClassGen(instanceClass);
    defaultValueIsSet = false;
  }

  public void setGeneratedInstanceClass(boolean isGenerated)
  {
    super.setGeneratedInstanceClass(isGenerated);

    setDataTypeGeneratedInstanceClass(isGenerated);

  }

  protected void setDataTypeGeneratedInstanceClass(boolean isGenerated)
  {
    // EEnumImpl overrides this to do nothing.
    // The only case that is special is when a more specific instance of AbstractEnumerator is generated/reused.
    //
    if (isGenerated)
    {
      instanceClassName = "org.eclipse.emf.common.util.AbstractEnumerator";
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return EcorePackage.eINSTANCE.getEDataType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSerializable()
  {
    return (eFlags & SERIALIZABLE_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSerializable(boolean newSerializable)
  {
    boolean oldSerializable = (eFlags & SERIALIZABLE_EFLAG) != 0;
    if (newSerializable) eFlags |= SERIALIZABLE_EFLAG; else eFlags &= ~SERIALIZABLE_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EDATA_TYPE__SERIALIZABLE, oldSerializable, newSerializable));
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
      case EcorePackage.EDATA_TYPE__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.EDATA_TYPE__NAME:
        return getName();
      case EcorePackage.EDATA_TYPE__INSTANCE_CLASS_NAME:
        return getInstanceClassName();
      case EcorePackage.EDATA_TYPE__INSTANCE_CLASS:
        return getInstanceClass();
      case EcorePackage.EDATA_TYPE__DEFAULT_VALUE:
        return getDefaultValue();
      case EcorePackage.EDATA_TYPE__EPACKAGE:
        return getEPackage();
      case EcorePackage.EDATA_TYPE__SERIALIZABLE:
        return isSerializable() ? Boolean.TRUE : Boolean.FALSE;
    }
    return eDynamicGet(eFeature, resolve);
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
    result.append(" (serializable: ");
    result.append((eFlags & SERIALIZABLE_EFLAG) != 0);
    result.append(')');
    return result.toString();
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
      case EcorePackage.EDATA_TYPE__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EDATA_TYPE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.EDATA_TYPE__INSTANCE_CLASS_NAME:
        return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
      case EcorePackage.EDATA_TYPE__INSTANCE_CLASS:
        return INSTANCE_CLASS_EDEFAULT == null ? getInstanceClass() != null : !INSTANCE_CLASS_EDEFAULT.equals(getInstanceClass());
      case EcorePackage.EDATA_TYPE__DEFAULT_VALUE:
        return DEFAULT_VALUE_EDEFAULT == null ? getDefaultValue() != null : !DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue());
      case EcorePackage.EDATA_TYPE__EPACKAGE:
        return getEPackage() != null;
      case EcorePackage.EDATA_TYPE__SERIALIZABLE:
        return ((eFlags & SERIALIZABLE_EFLAG) != 0) != SERIALIZABLE_EDEFAULT;
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
      case EcorePackage.EDATA_TYPE__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.EDATA_TYPE__NAME:
        setName((String)newValue);
        return;
      case EcorePackage.EDATA_TYPE__INSTANCE_CLASS_NAME:
        setInstanceClassName((String)newValue);
        return;
      case EcorePackage.EDATA_TYPE__SERIALIZABLE:
        setSerializable(((Boolean)newValue).booleanValue());
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
      case EcorePackage.EDATA_TYPE__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.EDATA_TYPE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.EDATA_TYPE__INSTANCE_CLASS_NAME:
        setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
        return;
      case EcorePackage.EDATA_TYPE__SERIALIZABLE:
        setSerializable(SERIALIZABLE_EDEFAULT);
        return;
    }
    eDynamicUnset(eFeature);
  }

}
