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
 * $Id: GenDataTypeImpl.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenDataTypeImpl#getEcoreDataType <em>Ecore Data Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenDataTypeImpl extends GenClassifierImpl implements GenDataType
{
  /**
   * The cached value of the '{@link #getEcoreDataType() <em>Ecore Data Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreDataType()
   * @generated
   * @ordered
   */
  protected EDataType ecoreDataType = null;

  protected GenDataTypeImpl()
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
    return GenModelPackage.eINSTANCE.getGenDataType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getEcoreDataType()
  {
    if (ecoreDataType != null && ecoreDataType.eIsProxy())
    {
      EDataType oldEcoreDataType = ecoreDataType;
      ecoreDataType = (EDataType)eResolveProxy((InternalEObject)ecoreDataType);
      if (ecoreDataType != oldEcoreDataType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_DATA_TYPE__ECORE_DATA_TYPE, oldEcoreDataType, ecoreDataType));
      }
    }
    return ecoreDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType basicGetEcoreDataType()
  {
    return ecoreDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreDataType(EDataType newEcoreDataType)
  {
    EDataType oldEcoreDataType = ecoreDataType;
    ecoreDataType = newEcoreDataType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_DATA_TYPE__ECORE_DATA_TYPE, oldEcoreDataType, ecoreDataType));
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
      case GenModelPackage.GEN_DATA_TYPE__GEN_PACKAGE:
        return getGenPackage();
      case GenModelPackage.GEN_DATA_TYPE__ECORE_DATA_TYPE:
        if (resolve) return getEcoreDataType();
        return basicGetEcoreDataType();
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
      case GenModelPackage.GEN_DATA_TYPE__ECORE_DATA_TYPE:
        setEcoreDataType((EDataType)newValue);
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
      case GenModelPackage.GEN_DATA_TYPE__ECORE_DATA_TYPE:
        setEcoreDataType((EDataType)null);
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
      case GenModelPackage.GEN_DATA_TYPE__GEN_PACKAGE:
        return getGenPackage() != null;
      case GenModelPackage.GEN_DATA_TYPE__ECORE_DATA_TYPE:
        return ecoreDataType != null;
    }
    return eDynamicIsSet(eFeature);
  }

  public EClassifier getEcoreClassifier()
  {
    return getEcoreDataType();
  }

  public String getImportedMetaType()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.EDataType");
  }

  public String getQualifiedInstanceClassName()
  {
    return getRawQualifiedInstanceClassName().replace('$','.');
  }

  public String getRawQualifiedInstanceClassName()
  {
    String name = getEcoreDataType().getInstanceClassName();
    if (name == null) name = "java.lang.Object";

    if (name.equals("org.eclipse.emf.common.util.AbstractEnumerator"))
    {
      EDataType baseType = ExtendedMetaData.INSTANCE.getBaseType(getEcoreDataType());
      if (baseType instanceof EEnum)
      {
        GenEnum genEnum = findGenEnum((EEnum)baseType);
        if (genEnum != null)
        {
          name = genEnum.getQualifiedName();
        }
      }
    }

    return name;
  }

  public String getImportedInstanceClassName()
  {
   return getGenModel().getImportedName(getRawQualifiedInstanceClassName()); 
  }

  public String getObjectInstanceClassName()
  {
    return getImportedType(getEcoreDataType(), true);
  }

  public boolean isSerializable()
  {
    return getEcoreDataType().isSerializable();
  }

  public String getSerializableFlag()
  {
    String result = !getEcoreDataType().isSerializable() ? "!" : "";
    return result + "IS_SERIALIZABLE";
  }

  public boolean isPrimitiveType()
  {
    return isPrimitiveType(getEcoreDataType());
  }

  public boolean isArrayType()
  {
    return getEcoreDataType().getInstanceClassName().indexOf('[') != -1;
  }

  public String getModelInfo()
  {
    StringBuffer result = new StringBuffer();

    result.append("instanceClass=\"" + getRawQualifiedInstanceClassName() + "\"");

    if (!isSerializable())
    {
      result.append(" serializable=\"false\"");
    }

    return result.toString();
  }

  public GenDataType getBaseType()
  {
    EDataType baseType = ExtendedMetaData.INSTANCE.getBaseType(getEcoreDataType());
    return baseType == null ? null : (GenDataType)findGenClassifier(baseType);
  }

  public GenDataType getItemType()
  {
    EDataType itemType = ExtendedMetaData.INSTANCE.getItemType(getEcoreDataType());
    return itemType == null ? null : (GenDataType)findGenClassifier(itemType);
  }

  public List /*GenDataType*/ getMemberTypes()
  {
    List result = new ArrayList();
    for (Iterator i = ExtendedMetaData.INSTANCE.getMemberTypes(getEcoreDataType()).iterator(); i.hasNext(); )
    {
      EDataType memberType = (EDataType)i.next();
      result.add(findGenClassifier(memberType));
    }
    return result;
  }

  public void initialize(EDataType eDataType)
  {
    setEcoreDataType(eDataType);
  }

  public boolean reconcile(GenDataType oldGenDataTypeVersion)
  {
    if (getEcoreDataType().getName().equals(oldGenDataTypeVersion.getEcoreDataType().getName()))
    {
      reconcileSettings(oldGenDataTypeVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void reconcileSettings(GenDataType oldGenDataTypeVersion)
  {
  }

  public boolean reconcile()
  {
    EDataType eDataType = getEcoreDataType();
    if (eDataType == null || eDataType.eIsProxy() || eDataType.eResource() == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
}
