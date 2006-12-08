/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: EGenericTypeImpl.java,v 1.2 2006/12/08 19:51:47 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EGeneric Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EGenericTypeImpl#getEUpperBound <em>EUpper Bound</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EGenericTypeImpl#getETypeArguments <em>EType Arguments</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EGenericTypeImpl#getERawType <em>ERaw Type</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EGenericTypeImpl#getELowerBound <em>ELower Bound</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EGenericTypeImpl#getETypeParameter <em>EType Parameter</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EGenericTypeImpl#getEClassifier <em>EClassifier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EGenericTypeImpl extends EObjectImpl implements EGenericType
{
  /**
   * The cached value of the '{@link #getEUpperBound() <em>EUpper Bound</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEUpperBound()
   * @generated
   * @ordered
   */
  protected EGenericType eUpperBound = null;

  /**
   * The cached value of the '{@link #getETypeArguments() <em>EType Arguments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getETypeArguments()
   * @generated
   * @ordered
   */
  protected EList<EGenericType> eTypeArguments = null;

  /**
   * The cached value of the '{@link #getERawType() <em>ERaw Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getERawType()
   * @generated
   * @ordered
   */
  protected EClassifier eRawType = null;

  /**
   * The cached value of the '{@link #getELowerBound() <em>ELower Bound</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getELowerBound()
   * @generated
   * @ordered
   */
  protected EGenericType eLowerBound = null;

  /**
   * The cached value of the '{@link #getETypeParameter() <em>EType Parameter</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getETypeParameter()
   * @generated
   * @ordered
   */
  protected ETypeParameter eTypeParameter = null;

  /**
   * The cached value of the '{@link #getEClassifier() <em>EClassifier</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEClassifier()
   * @generated
   * @ordered
   */
  protected EClassifier eClassifier = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EGenericTypeImpl()
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
    return EcorePackage.Literals.EGENERIC_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EGenericType getEUpperBound()
  {
    return eUpperBound;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEUpperBound(EGenericType newEUpperBound, NotificationChain msgs)
  {
    EGenericType oldEUpperBound = eUpperBound;
    eUpperBound = newEUpperBound;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EcorePackage.EGENERIC_TYPE__EUPPER_BOUND, oldEUpperBound, newEUpperBound);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEUpperBound(EGenericType newEUpperBound)
  {
    if (newEUpperBound != eUpperBound)
    {
      NotificationChain msgs = null;
      if (eUpperBound != null)
        msgs = ((InternalEObject)eUpperBound).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EcorePackage.EGENERIC_TYPE__EUPPER_BOUND, null, msgs);
      if (newEUpperBound != null)
        msgs = ((InternalEObject)newEUpperBound).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EcorePackage.EGENERIC_TYPE__EUPPER_BOUND, null, msgs);
      msgs = basicSetEUpperBound(newEUpperBound, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EGENERIC_TYPE__EUPPER_BOUND, newEUpperBound, newEUpperBound));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EGenericType> getETypeArguments()
  {
    if (eTypeArguments == null)
    {
      eTypeArguments = new EObjectContainmentEList<EGenericType>(EGenericType.class, this, EcorePackage.EGENERIC_TYPE__ETYPE_ARGUMENTS);
    }
    return eTypeArguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClassifier getERawType()
  {
    if (eRawType != null && eRawType.eIsProxy())
    {
      InternalEObject oldERawType = (InternalEObject)eRawType;
      eRawType = (EClassifier)eResolveProxy(oldERawType);
      if (eRawType != oldERawType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EcorePackage.EGENERIC_TYPE__ERAW_TYPE, oldERawType, eRawType));
      }
    }
    return eRawType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClassifier basicGetERawType()
  {
    return eRawType;
  }

  public NotificationChain setERawType(EClassifier newERawType, NotificationChain msgs)
  {
    EClassifier oldERawType = eRawType;
    eRawType = newERawType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EcorePackage.EGENERIC_TYPE__ERAW_TYPE, oldERawType, eRawType);
      if (msgs == null)
      {
        msgs = notification;
      }
      else
      {
        msgs.add(notification);
      }
    }
    
    if (oldERawType != newERawType)
    {
      if (eContainer instanceof ETypedElementImpl)
      {
        if (eContainerFeatureID == (EOPPOSITE_FEATURE_BASE - EcorePackage.ETYPED_ELEMENT__EGENERIC_TYPE))
        {
          msgs = ((ETypedElementImpl)eContainer).setEType(newERawType, msgs);
        }
        else if (eContainerFeatureID == (EOPPOSITE_FEATURE_BASE - EcorePackage.EOPERATION__EGENERIC_EXCEPTIONS))
        {
          if (newERawType == null)
          {
            newERawType = EcorePackage.Literals.EJAVA_OBJECT;
          }
          if (oldERawType == null)
          {
            oldERawType = EcorePackage.Literals.EJAVA_OBJECT;
          }
  
          if (eContainer.eNotificationRequired())
          {
            ENotificationImpl notification =
              new ENotificationImpl
                (eContainer, 
                 Notification.SET, 
                 EcorePackage.EOPERATION__EEXCEPTIONS, 
                 oldERawType,
                 newERawType,
                 ((EOperation)eContainer).getEGenericExceptions().indexOf(this), 
                 false);
            
            if (msgs == null)
            {
              msgs = notification;
            }
            else
            {
              msgs.add(notification);
            }
          }
        }
      }
      else if (eContainer instanceof EClass)
      {
        if (eContainerFeatureID == (EOPPOSITE_FEATURE_BASE - EcorePackage.ECLASS__EGENERIC_SUPER_TYPES))
        {
          if (!(newERawType instanceof EClass))
          {
            newERawType = EcorePackage.Literals.EOBJECT;
          }
          if (!(oldERawType instanceof EClass))
          {
            oldERawType = EcorePackage.Literals.EOBJECT;
          }
  
          if (eContainer.eNotificationRequired())
          {
            ENotificationImpl notification =
              new ENotificationImpl
                (eContainer, 
                 Notification.SET, 
                 EcorePackage.ECLASS__ESUPER_TYPES, 
                 oldERawType,
                 newERawType,
                 ((EClass)eContainer).getEGenericSuperTypes().indexOf(this), 
                 false);
            
            if (msgs == null)
            {
              msgs = notification;
            }
            else
            {
              msgs.add(notification);
            }
          }
        }
      }
      else if (eContainer instanceof ETypeParameter)
      {
        ETypeParameter eTypeParameter = (ETypeParameter)eContainer;
        @SuppressWarnings("unchecked") EList<EGenericTypeImpl> eGenericTypes = (EList<EGenericTypeImpl>)(EList<?>)eTypeParameter.getEGenericTypes();
        for (EGenericTypeImpl eGenericType : eGenericTypes)
        {
          msgs = eGenericType.setERawType(eGenericType.getErasure(eTypeParameter), msgs);
        }
      }
    }
    return msgs;
  }
  
  @Override
  public NotificationChain eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID, NotificationChain msgs)
  {
    msgs = super.eBasicSetContainer(newContainer, newContainerFeatureID, msgs);
    if (eTypeParameter != null && newContainer instanceof EStructuralFeature)
    {
      // When we attached we have to recompute the erasure of the type parameter.
      //
      EClassifier newERawType = getErasure(eTypeParameter);
      if (newERawType != eRawType)
      {
        msgs = setERawType(newERawType, msgs);
      }
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EGenericType getELowerBound()
  {
    return eLowerBound;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetELowerBound(EGenericType newELowerBound, NotificationChain msgs)
  {
    EGenericType oldELowerBound = eLowerBound;
    eLowerBound = newELowerBound;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EcorePackage.EGENERIC_TYPE__ELOWER_BOUND, oldELowerBound, newELowerBound);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setELowerBound(EGenericType newELowerBound)
  {
    if (newELowerBound != eLowerBound)
    {
      NotificationChain msgs = null;
      if (eLowerBound != null)
        msgs = ((InternalEObject)eLowerBound).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EcorePackage.EGENERIC_TYPE__ELOWER_BOUND, null, msgs);
      if (newELowerBound != null)
        msgs = ((InternalEObject)newELowerBound).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EcorePackage.EGENERIC_TYPE__ELOWER_BOUND, null, msgs);
      msgs = basicSetELowerBound(newELowerBound, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EGENERIC_TYPE__ELOWER_BOUND, newELowerBound, newELowerBound));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ETypeParameter getETypeParameter()
  {
    return eTypeParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public NotificationChain basicSetETypeParameter(ETypeParameter newETypeParameter, NotificationChain msgs)
  {
    ETypeParameter oldETypeParameter = eTypeParameter;
    eTypeParameter = newETypeParameter;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EcorePackage.EGENERIC_TYPE__ETYPE_PARAMETER, oldETypeParameter, newETypeParameter);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    if (oldETypeParameter != newETypeParameter)
    {
      if (newETypeParameter != null)
      {
        msgs = setERawType(getErasure(newETypeParameter), msgs);
      }
      else 
      {
        msgs = setERawType(eClassifier, msgs);
      }
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setETypeParameter(ETypeParameter newETypeParameter)
  {
    if (newETypeParameter != eTypeParameter)
    {
      NotificationChain msgs = null;
      if (eTypeParameter != null)
        msgs = ((InternalEObject)eTypeParameter).eInverseRemove(this, EcorePackage.ETYPE_PARAMETER__EGENERIC_TYPES, ETypeParameter.class, msgs);
      if (newETypeParameter != null)
        msgs = ((InternalEObject)newETypeParameter).eInverseAdd(this, EcorePackage.ETYPE_PARAMETER__EGENERIC_TYPES, ETypeParameter.class, msgs);
      msgs = basicSetETypeParameter(newETypeParameter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EGENERIC_TYPE__ETYPE_PARAMETER, newETypeParameter, newETypeParameter));
  }
  
  protected EClassifier getErasure(ETypeParameter eTypeParameter)
  {
    if (eTypeParameter == null)
    {
      return null;
    }
    else
    {
      boolean needEClass = eContainer instanceof EClass || eContainer instanceof EReference;
      boolean needEDataType = !needEClass && eContainer instanceof EAttribute;
      for (EGenericType eBound : eTypeParameter.getEBounds())
      {
        EClassifier eRawType = eBound.getERawType();
        if (needEClass ? eRawType instanceof EClass : needEDataType ? eRawType instanceof EDataType : eRawType!= null)
        {
          return eRawType;
        }
      }
      return needEClass ? EcorePackage.Literals.EOBJECT : EcorePackage.Literals.EJAVA_OBJECT;
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClassifier getEClassifier()
  {
    if (eClassifier != null && eClassifier.eIsProxy())
    {
      InternalEObject oldEClassifier = (InternalEObject)eClassifier;
      eClassifier = (EClassifier)eResolveProxy(oldEClassifier);
      if (eClassifier != oldEClassifier)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EcorePackage.EGENERIC_TYPE__ECLASSIFIER, oldEClassifier, eClassifier));
      }
    }
    return eClassifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClassifier basicGetEClassifier()
  {
    return eClassifier;
  }

  public NotificationChain setEClassifier(EClassifier newEClassifier, NotificationChain msgs)
  {
    EClassifier oldEClassifier = eClassifier;
    eClassifier = newEClassifier;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = 
        new ENotificationImpl(this, Notification.SET, EcorePackage.EGENERIC_TYPE__ECLASSIFIER, oldEClassifier, eClassifier);
      if (msgs == null)
      {
        msgs = notification;
      }
      else
      {
        msgs.add(notification);
      }
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setEClassifier(EClassifier newEClassifier)
  {
    EClassifier oldEClassifier = eClassifier;
    NotificationChain msgs = setEClassifier(newEClassifier, null);
    if (oldEClassifier != newEClassifier && eTypeParameter == null)
    {
      msgs = setERawType(newEClassifier, msgs);
    }
    if (msgs != null)
    {
      msgs.dispatch();
    }
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
      case EcorePackage.EGENERIC_TYPE__ETYPE_PARAMETER:
        if (eTypeParameter != null)
          msgs = ((InternalEObject)eTypeParameter).eInverseRemove(this, EcorePackage.ETYPE_PARAMETER__EGENERIC_TYPES, ETypeParameter.class, msgs);
        return basicSetETypeParameter((ETypeParameter)otherEnd, msgs);
    }
    return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
      case EcorePackage.EGENERIC_TYPE__EUPPER_BOUND:
        return basicSetEUpperBound(null, msgs);
      case EcorePackage.EGENERIC_TYPE__ETYPE_ARGUMENTS:
        return ((InternalEList<?>)getETypeArguments()).basicRemove(otherEnd, msgs);
      case EcorePackage.EGENERIC_TYPE__ELOWER_BOUND:
        return basicSetELowerBound(null, msgs);
      case EcorePackage.EGENERIC_TYPE__ETYPE_PARAMETER:
        return basicSetETypeParameter(null, msgs);
    }
    return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
      case EcorePackage.EGENERIC_TYPE__EUPPER_BOUND:
        return getEUpperBound();
      case EcorePackage.EGENERIC_TYPE__ETYPE_ARGUMENTS:
        return getETypeArguments();
      case EcorePackage.EGENERIC_TYPE__ERAW_TYPE:
        if (resolve) return getERawType();
        return basicGetERawType();
      case EcorePackage.EGENERIC_TYPE__ELOWER_BOUND:
        return getELowerBound();
      case EcorePackage.EGENERIC_TYPE__ETYPE_PARAMETER:
        return getETypeParameter();
      case EcorePackage.EGENERIC_TYPE__ECLASSIFIER:
        if (resolve) return getEClassifier();
        return basicGetEClassifier();
    }
    return eDynamicGet(featureID, resolve, coreType);
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
      case EcorePackage.EGENERIC_TYPE__EUPPER_BOUND:
        setEUpperBound((EGenericType)newValue);
        return;
      case EcorePackage.EGENERIC_TYPE__ETYPE_ARGUMENTS:
        getETypeArguments().clear();
        getETypeArguments().addAll((Collection<? extends EGenericType>)newValue);
        return;
      case EcorePackage.EGENERIC_TYPE__ELOWER_BOUND:
        setELowerBound((EGenericType)newValue);
        return;
      case EcorePackage.EGENERIC_TYPE__ETYPE_PARAMETER:
        setETypeParameter((ETypeParameter)newValue);
        return;
      case EcorePackage.EGENERIC_TYPE__ECLASSIFIER:
        setEClassifier((EClassifier)newValue);
        return;
    }
    eDynamicSet(featureID, newValue);
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
      case EcorePackage.EGENERIC_TYPE__EUPPER_BOUND:
        setEUpperBound((EGenericType)null);
        return;
      case EcorePackage.EGENERIC_TYPE__ETYPE_ARGUMENTS:
        getETypeArguments().clear();
        return;
      case EcorePackage.EGENERIC_TYPE__ELOWER_BOUND:
        setELowerBound((EGenericType)null);
        return;
      case EcorePackage.EGENERIC_TYPE__ETYPE_PARAMETER:
        setETypeParameter((ETypeParameter)null);
        return;
      case EcorePackage.EGENERIC_TYPE__ECLASSIFIER:
        setEClassifier((EClassifier)null);
        return;
    }
    eDynamicUnset(featureID);
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
      case EcorePackage.EGENERIC_TYPE__EUPPER_BOUND:
        return eUpperBound != null;
      case EcorePackage.EGENERIC_TYPE__ETYPE_ARGUMENTS:
        return eTypeArguments != null && !eTypeArguments.isEmpty();
      case EcorePackage.EGENERIC_TYPE__ERAW_TYPE:
        return eRawType != null;
      case EcorePackage.EGENERIC_TYPE__ELOWER_BOUND:
        return eLowerBound != null;
      case EcorePackage.EGENERIC_TYPE__ETYPE_PARAMETER:
        return eTypeParameter != null;
      case EcorePackage.EGENERIC_TYPE__ECLASSIFIER:
        return eClassifier != null;
    }
    return eDynamicIsSet(featureID);
  }

} //EGenericTypeImpl
