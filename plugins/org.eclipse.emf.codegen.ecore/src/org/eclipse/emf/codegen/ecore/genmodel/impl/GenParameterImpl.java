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
 * $Id: GenParameterImpl.java,v 1.5 2005/06/01 19:30:53 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenParameterImpl#getGenOperation <em>Gen Operation</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenParameterImpl#getEcoreParameter <em>Ecore Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenParameterImpl extends GenTypedElementImpl implements GenParameter
{
  /**
   * The cached value of the '{@link #getEcoreParameter() <em>Ecore Parameter</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreParameter()
   * @generated
   * @ordered
   */
  protected EParameter ecoreParameter = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenParameterImpl() 
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
    return GenModelPackage.eINSTANCE.getGenParameter();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenOperation getGenOperation()
  {
    if (eContainerFeatureID != GenModelPackage.GEN_PARAMETER__GEN_OPERATION) return null;
    return (GenOperation)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenOperation(GenOperation newGenOperation)
  {
    if (newGenOperation != eContainer || (eContainerFeatureID != GenModelPackage.GEN_PARAMETER__GEN_OPERATION && newGenOperation != null))
    {
      if (EcoreUtil.isAncestor(this, newGenOperation))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenOperation != null)
        msgs = ((InternalEObject)newGenOperation).eInverseAdd(this, GenModelPackage.GEN_OPERATION__GEN_PARAMETERS, GenOperation.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newGenOperation, GenModelPackage.GEN_PARAMETER__GEN_OPERATION, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PARAMETER__GEN_OPERATION, newGenOperation, newGenOperation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EParameter getEcoreParameter()
  {
    if (ecoreParameter != null && ecoreParameter.eIsProxy())
    {
      EParameter oldEcoreParameter = ecoreParameter;
      ecoreParameter = (EParameter)eResolveProxy((InternalEObject)ecoreParameter);
      if (ecoreParameter != oldEcoreParameter)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_PARAMETER__ECORE_PARAMETER, oldEcoreParameter, ecoreParameter));
      }
    }
    return ecoreParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EParameter basicGetEcoreParameter()
  {
    return ecoreParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreParameter(EParameter newEcoreParameter)
  {
    EParameter oldEcoreParameter = ecoreParameter;
    ecoreParameter = newEcoreParameter;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_PARAMETER__ECORE_PARAMETER, oldEcoreParameter, ecoreParameter));
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
        case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, GenModelPackage.GEN_PARAMETER__GEN_OPERATION, msgs);
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
        case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
          return eBasicSetContainer(null, GenModelPackage.GEN_PARAMETER__GEN_OPERATION, msgs);
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
        case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
          return eContainer.eInverseRemove(this, GenModelPackage.GEN_OPERATION__GEN_PARAMETERS, GenOperation.class, msgs);
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
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        return getGenOperation();
      case GenModelPackage.GEN_PARAMETER__ECORE_PARAMETER:
        if (resolve) return getEcoreParameter();
        return basicGetEcoreParameter();
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
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        return getGenOperation() != null;
      case GenModelPackage.GEN_PARAMETER__ECORE_PARAMETER:
        return ecoreParameter != null;
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
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        setGenOperation((GenOperation)newValue);
        return;
      case GenModelPackage.GEN_PARAMETER__ECORE_PARAMETER:
        setEcoreParameter((EParameter)newValue);
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
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        setGenOperation((GenOperation)null);
        return;
      case GenModelPackage.GEN_PARAMETER__ECORE_PARAMETER:
        setEcoreParameter((EParameter)null);
        return;
    }
    eDynamicUnset(eFeature);
  }

  public ETypedElement getEcoreTypedElement()
  {
    return getEcoreParameter();
  }

  public String getName()
  {
    return getEcoreParameter().getName();
  }

  public GenPackage getGenPackage()
  {
    return getGenOperation().getGenPackage();
  }

  public void initialize(EParameter eParameter)
  {
    setEcoreParameter(eParameter);
  }

  public boolean reconcile(GenParameter oldGenParameterVersion)
  {
    if (getEcoreParameter().getName().equals(oldGenParameterVersion.getEcoreParameter().getName()))
    {
      reconcileSettings(oldGenParameterVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  public String getQualifiedModelInfo()
  {
    return getModelInfo(true);
  }

  protected String getModelInfo(boolean qualified)
  {
    EParameter eParameter = getEcoreParameter();
    StringBuffer result = new StringBuffer();

    String mapModelInfo = getMapModelInfo(qualified, true);
    if (mapModelInfo != null)
    {
      result.append(mapModelInfo);
    }
    else
    {
      if (eParameter.isMany() && !isFeatureMapType())
      {
        appendModelSetting(result, qualified, "type", getType(eParameter.getEType(), false));
      }

      EClassifier type = eParameter.getEType();
      if (type instanceof EDataType && !(type instanceof EEnum))
      {
        GenPackage genPackage = findGenPackage(type.getEPackage());
        if (genPackage != null && (isFeatureMapType() || !genPackage.isEcorePackage()))
        {
          appendModelSetting(result, qualified, "dataType", genPackage.getInterfacePackageName() + '.' + type.getName());
        }
      }
      
      if (!eParameter.isUnique())
      {
        appendModelSetting(result, qualified, "unique", "false");
      }

      result.append(getMultiplicityModelInfo(qualified));

      if (!eParameter.isOrdered()) 
      {
        appendModelSetting(result, qualified, "ordered", "false");
      }
    }

    appendAnnotationInfo(result, qualified, eParameter, DEFAULT_ANNOTATION_FILTER);
    return result.toString().trim();
  }

  protected void reconcileSettings(GenParameter oldGenParameterVersion)
  {
  }

  public boolean reconcile()
  {
    EParameter eParameter = getEcoreParameter();
    if (eParameter == null || eParameter.eIsProxy()  || eParameter.eResource() == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
}
