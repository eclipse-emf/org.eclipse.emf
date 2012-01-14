/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.ETypeParameter;
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
  protected EParameter ecoreParameter;

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
  @Override
  protected EClass eStaticClass()
  {
    return GenModelPackage.Literals.GEN_PARAMETER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenOperation getGenOperation()
  {
    if (eContainerFeatureID() != GenModelPackage.GEN_PARAMETER__GEN_OPERATION) return null;
    return (GenOperation)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGenOperation(GenOperation newGenOperation, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newGenOperation, GenModelPackage.GEN_PARAMETER__GEN_OPERATION, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenOperation(GenOperation newGenOperation)
  {
    if (newGenOperation != eInternalContainer() || (eContainerFeatureID() != GenModelPackage.GEN_PARAMETER__GEN_OPERATION && newGenOperation != null))
    {
      if (EcoreUtil.isAncestor(this, newGenOperation))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenOperation != null)
        msgs = ((InternalEObject)newGenOperation).eInverseAdd(this, GenModelPackage.GEN_OPERATION__GEN_PARAMETERS, GenOperation.class, msgs);
      msgs = basicSetGenOperation(newGenOperation, msgs);
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
      InternalEObject oldEcoreParameter = (InternalEObject)ecoreParameter;
      ecoreParameter = (EParameter)eResolveProxy(oldEcoreParameter);
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
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetGenOperation((GenOperation)otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
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
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        return basicSetGenOperation(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        return eInternalContainer().eInverseRemove(this, GenModelPackage.GEN_OPERATION__GEN_PARAMETERS, GenOperation.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
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
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        return getGenOperation();
      case GenModelPackage.GEN_PARAMETER__ECORE_PARAMETER:
        if (resolve) return getEcoreParameter();
        return basicGetEcoreParameter();
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
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        setGenOperation((GenOperation)newValue);
        return;
      case GenModelPackage.GEN_PARAMETER__ECORE_PARAMETER:
        setEcoreParameter((EParameter)newValue);
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
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        setGenOperation((GenOperation)null);
        return;
      case GenModelPackage.GEN_PARAMETER__ECORE_PARAMETER:
        setEcoreParameter((EParameter)null);
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
      case GenModelPackage.GEN_PARAMETER__GEN_OPERATION:
        return getGenOperation() != null;
      case GenModelPackage.GEN_PARAMETER__ECORE_PARAMETER:
        return ecoreParameter != null;
    }
    return super.eIsSet(featureID);
  }

  @Override
  public ETypedElement getEcoreTypedElement()
  {
    return getEcoreParameter();
  }

  @Override
  public String getName()
  {
    return safeName(getEcoreParameter().getName());
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
    reconcileSettings(oldGenParameterVersion);
    return true;
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
      EClassifier type = eParameter.getEType();
      if (type instanceof EClass)
      {
        if (eParameter.isMany() && getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50 || 
              hasReferenceToClassifierWithInstanceTypeName(eParameter.getEGenericType()))
        {
          appendModelSetting(result, qualified, "type", getEcoreType(eParameter.getEGenericType()));
        }
      }
      else if (eParameter.isMany() && getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50 || 
                 hasReferenceToClassifierWithInstanceTypeName(eParameter.getEGenericType()))
      {
        GenPackage genPackage = findGenPackage(type.getEPackage());
        if (genPackage != null && (isFeatureMapType() || !genPackage.isEcorePackage()))
        {
          appendModelSetting(result, qualified, "dataType", getEcoreType(eParameter.getEGenericType()));
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
    reconcileGenAnnotations(oldGenParameterVersion);
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

  public boolean usesOperationTypeParameters()
  {
    EParameter eParameter = getEcoreParameter();
    for (TreeIterator<EObject> i = eParameter.eAllContents(); i.hasNext();)
    {
      EObject eObject = i.next();
      if (eObject instanceof EGenericType)
      {
        EGenericType eGenericType = (EGenericType)eObject;
        ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
        if (eTypeParameter != null && eTypeParameter.eContainer() instanceof EOperation)
        {
          return true;
        }
      }
      else
      {
        i.prune();
      }
    }
    return false;
  }
}
