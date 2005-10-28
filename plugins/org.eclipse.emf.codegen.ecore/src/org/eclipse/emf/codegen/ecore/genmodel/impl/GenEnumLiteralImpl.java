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
 * $Id: GenEnumLiteralImpl.java,v 1.5 2005/10/28 14:06:20 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Enum Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumLiteralImpl#getGenEnum <em>Gen Enum</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumLiteralImpl#getEcoreEnumLiteral <em>Ecore Enum Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenEnumLiteralImpl extends GenBaseImpl implements GenEnumLiteral
{
  /**
   * The cached value of the '{@link #getEcoreEnumLiteral() <em>Ecore Enum Literal</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreEnumLiteral()
   * @generated
   * @ordered
   */
  protected EEnumLiteral ecoreEnumLiteral = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenEnumLiteralImpl() 
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
    return GenModelPackage.eINSTANCE.getGenEnumLiteral();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenEnum getGenEnum()
  {
    if (eContainerFeatureID != GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM) return null;
    return (GenEnum)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenEnum(GenEnum newGenEnum)
  {
    if (newGenEnum != eContainer || (eContainerFeatureID != GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM && newGenEnum != null))
    {
      if (EcoreUtil.isAncestor(this, newGenEnum))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenEnum != null)
        msgs = ((InternalEObject)newGenEnum).eInverseAdd(this, GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS, GenEnum.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newGenEnum, GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM, newGenEnum, newGenEnum));
  }

  public EModelElement getEcoreModelElement()
  {
    return getEcoreEnumLiteral();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnumLiteral getEcoreEnumLiteral()
  {
    if (ecoreEnumLiteral != null && ecoreEnumLiteral.eIsProxy())
    {
      EEnumLiteral oldEcoreEnumLiteral = ecoreEnumLiteral;
      ecoreEnumLiteral = (EEnumLiteral)eResolveProxy((InternalEObject)ecoreEnumLiteral);
      if (ecoreEnumLiteral != oldEcoreEnumLiteral)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL, oldEcoreEnumLiteral, ecoreEnumLiteral));
      }
    }
    return ecoreEnumLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnumLiteral basicGetEcoreEnumLiteral()
  {
    return ecoreEnumLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreEnumLiteral(EEnumLiteral newEcoreEnumLiteral)
  {
    EEnumLiteral oldEcoreEnumLiteral = ecoreEnumLiteral;
    ecoreEnumLiteral = newEcoreEnumLiteral;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL, oldEcoreEnumLiteral, ecoreEnumLiteral));
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
        case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM, msgs);
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
        case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
          return eBasicSetContainer(null, GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM, msgs);
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
        case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
          return eContainer.eInverseRemove(this, GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS, GenEnum.class, msgs);
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        return getGenEnum();
      case GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL:
        if (resolve) return getEcoreEnumLiteral();
        return basicGetEcoreEnumLiteral();
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        return getGenEnum() != null;
      case GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL:
        return ecoreEnumLiteral != null;
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        setGenEnum((GenEnum)newValue);
        return;
      case GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL:
        setEcoreEnumLiteral((EEnumLiteral)newValue);
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        setGenEnum((GenEnum)null);
        return;
      case GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL:
        setEcoreEnumLiteral((EEnumLiteral)null);
        return;
    }
    eDynamicUnset(eFeature);
  }

  public String getName()
  {
    return getEcoreEnumLiteral().getName();
  }

  public String getCapName()
  {
    return capName(getName());
  }
  
  public String getLiteral()
  {
    return getEcoreEnumLiteral().getLiteral();
  }

  public String getFormattedName()
  {
    return format(getCapName(), ' ', null, false, false);
  }

  public int getValue()
  {
    return getEcoreEnumLiteral().getValue();
  }

  public String getEnumLiteralID()
  {
    return format(getName(), '_', null, false, true).toUpperCase();
  }

  public GenPackage getGenPackage()
  {
    return getGenEnum().getGenPackage();
  }

  public String getEnumLiteralAccessorName()
  {
    return getGenEnum().getName() + "_" + getCapName();
  }

  public void initialize(EEnumLiteral eEnumLiteral)
  {
    setEcoreEnumLiteral(eEnumLiteral);
  }

  public String getModelInfo()
  {
    StringBuffer result = new StringBuffer();
    if (!getEnumLiteralID().equals(getName()))
    {
      appendModelSetting(result, "name", getName());
    }
    if (!getName().equals(getLiteral()))
    {
      appendModelSetting(result, "literal", getLiteral());
    }
    appendAnnotationInfo(result, getEcoreEnumLiteral());
    return result.toString().trim();
  }

  public boolean reconcile(GenEnumLiteral oldGenEnumLiteralVersion)
  {
    if (getEcoreEnumLiteral().getName().equals(oldGenEnumLiteralVersion.getEcoreEnumLiteral().getName()))
    {
      reconcileSettings(oldGenEnumLiteralVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void reconcileSettings(GenEnumLiteral oldGenEnumLiteralVersion)
  {
  }

  public boolean reconcile()
  {
    EEnumLiteral eEnumLiteral = getEcoreEnumLiteral();
    if (eEnumLiteral == null || eEnumLiteral.eIsProxy() || eEnumLiteral.eResource() == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
}
