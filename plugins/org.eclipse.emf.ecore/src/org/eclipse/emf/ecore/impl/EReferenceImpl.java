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
 * $Id: EReferenceImpl.java,v 1.2 2004/06/11 22:14:52 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EReference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EReferenceImpl#isContainment <em>Containment</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EReferenceImpl#isContainer <em>Container</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EReferenceImpl#isResolveProxies <em>Resolve Proxies</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EReferenceImpl#getEOpposite <em>EOpposite</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EReferenceImpl#getEReferenceType <em>EReference Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EReferenceImpl extends EStructuralFeatureImpl implements EReference
{
  /**
   * The default value of the '{@link #isContainment() <em>Containment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isContainment()
   * @generated
   * @ordered
   */
  protected static final boolean CONTAINMENT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isContainment() <em>Containment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isContainment()
   * @generated
   * @ordered
   */
  protected boolean containment = CONTAINMENT_EDEFAULT;

  /**
   * The default value of the '{@link #isContainer() <em>Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isContainer()
   * @generated
   * @ordered
   */
  protected static final boolean CONTAINER_EDEFAULT = false;

  /**
   * The default value of the '{@link #isResolveProxies() <em>Resolve Proxies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isResolveProxies()
   * @generated
   * @ordered
   */
  protected static final boolean RESOLVE_PROXIES_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isResolveProxies() <em>Resolve Proxies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isResolveProxies()
   * @generated
   * @ordered
   */
  protected boolean resolveProxies = RESOLVE_PROXIES_EDEFAULT;

  /**
   * The cached value of the '{@link #getEOpposite() <em>EOpposite</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEOpposite()
   * @generated
   * @ordered
   */
  protected EReference eOpposite = null;

  protected EReferenceImpl()
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
    return EcorePackage.eINSTANCE.getEReference();
  }

  public boolean isBidirectional()
  {
    return getEOpposite() != null;
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
      case EcorePackage.EREFERENCE__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.EREFERENCE__NAME:
        return getName();
      case EcorePackage.EREFERENCE__ORDERED:
        return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__UNIQUE:
        return isUnique() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__LOWER_BOUND:
        return new Integer(getLowerBound());
      case EcorePackage.EREFERENCE__UPPER_BOUND:
        return new Integer(getUpperBound());
      case EcorePackage.EREFERENCE__MANY:
        return isMany() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__REQUIRED:
        return isRequired() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__ETYPE:
        if (resolve) return getEType();
        return basicGetEType();
      case EcorePackage.EREFERENCE__CHANGEABLE:
        return isChangeable() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__VOLATILE:
        return isVolatile() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__TRANSIENT:
        return isTransient() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__DEFAULT_VALUE_LITERAL:
        return getDefaultValueLiteral();
      case EcorePackage.EREFERENCE__DEFAULT_VALUE:
        return getDefaultValue();
      case EcorePackage.EREFERENCE__UNSETTABLE:
        return isUnsettable() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__DERIVED:
        return isDerived() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__ECONTAINING_CLASS:
        return getEContainingClass();
      case EcorePackage.EREFERENCE__CONTAINMENT:
        return isContainment() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__CONTAINER:
        return isContainer() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__RESOLVE_PROXIES:
        return isResolveProxies() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.EREFERENCE__EOPPOSITE:
        if (resolve) return getEOpposite();
        return basicGetEOpposite();
      case EcorePackage.EREFERENCE__EREFERENCE_TYPE:
        if (resolve) return getEReferenceType();
        return basicGetEReferenceType();
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
      case EcorePackage.EREFERENCE__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EREFERENCE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.EREFERENCE__ORDERED:
        return ordered != ORDERED_EDEFAULT;
      case EcorePackage.EREFERENCE__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case EcorePackage.EREFERENCE__LOWER_BOUND:
        return lowerBound != LOWER_BOUND_EDEFAULT;
      case EcorePackage.EREFERENCE__UPPER_BOUND:
        return upperBound != UPPER_BOUND_EDEFAULT;
      case EcorePackage.EREFERENCE__MANY:
        return isMany() != MANY_EDEFAULT;
      case EcorePackage.EREFERENCE__REQUIRED:
        return isRequired() != REQUIRED_EDEFAULT;
      case EcorePackage.EREFERENCE__ETYPE:
        return eType != null;
      case EcorePackage.EREFERENCE__CHANGEABLE:
        return changeable != CHANGEABLE_EDEFAULT;
      case EcorePackage.EREFERENCE__VOLATILE:
        return volatile_ != VOLATILE_EDEFAULT;
      case EcorePackage.EREFERENCE__TRANSIENT:
        return transient_ != TRANSIENT_EDEFAULT;
      case EcorePackage.EREFERENCE__DEFAULT_VALUE_LITERAL:
        return DEFAULT_VALUE_LITERAL_EDEFAULT == null ? defaultValueLiteral != null : !DEFAULT_VALUE_LITERAL_EDEFAULT.equals(defaultValueLiteral);
      case EcorePackage.EREFERENCE__DEFAULT_VALUE:
        return DEFAULT_VALUE_EDEFAULT == null ? getDefaultValue() != null : !DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue());
      case EcorePackage.EREFERENCE__UNSETTABLE:
        return unsettable != UNSETTABLE_EDEFAULT;
      case EcorePackage.EREFERENCE__DERIVED:
        return derived != DERIVED_EDEFAULT;
      case EcorePackage.EREFERENCE__ECONTAINING_CLASS:
        return getEContainingClass() != null;
      case EcorePackage.EREFERENCE__CONTAINMENT:
        return containment != CONTAINMENT_EDEFAULT;
      case EcorePackage.EREFERENCE__CONTAINER:
        return isContainer() != CONTAINER_EDEFAULT;
      case EcorePackage.EREFERENCE__RESOLVE_PROXIES:
        return resolveProxies != RESOLVE_PROXIES_EDEFAULT;
      case EcorePackage.EREFERENCE__EOPPOSITE:
        return eOpposite != null;
      case EcorePackage.EREFERENCE__EREFERENCE_TYPE:
        return basicGetEReferenceType() != null;
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
      case EcorePackage.EREFERENCE__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.EREFERENCE__NAME:
        setName((String)newValue);
        return;
      case EcorePackage.EREFERENCE__ORDERED:
        setOrdered(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EREFERENCE__UNIQUE:
        setUnique(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EREFERENCE__LOWER_BOUND:
        setLowerBound(((Integer)newValue).intValue());
        return;
      case EcorePackage.EREFERENCE__UPPER_BOUND:
        setUpperBound(((Integer)newValue).intValue());
        return;
      case EcorePackage.EREFERENCE__ETYPE:
        setEType((EClassifier)newValue);
        return;
      case EcorePackage.EREFERENCE__CHANGEABLE:
        setChangeable(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EREFERENCE__VOLATILE:
        setVolatile(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EREFERENCE__TRANSIENT:
        setTransient(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EREFERENCE__DEFAULT_VALUE_LITERAL:
        setDefaultValueLiteral((String)newValue);
        return;
      case EcorePackage.EREFERENCE__UNSETTABLE:
        setUnsettable(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EREFERENCE__DERIVED:
        setDerived(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EREFERENCE__CONTAINMENT:
        setContainment(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EREFERENCE__RESOLVE_PROXIES:
        setResolveProxies(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.EREFERENCE__EOPPOSITE:
        setEOpposite((EReference)newValue);
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
      case EcorePackage.EREFERENCE__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.EREFERENCE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__LOWER_BOUND:
        setLowerBound(LOWER_BOUND_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__UPPER_BOUND:
        setUpperBound(UPPER_BOUND_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__ETYPE:
        setEType((EClassifier)null);
        return;
      case EcorePackage.EREFERENCE__CHANGEABLE:
        setChangeable(CHANGEABLE_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__VOLATILE:
        setVolatile(VOLATILE_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__TRANSIENT:
        setTransient(TRANSIENT_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__DEFAULT_VALUE_LITERAL:
        setDefaultValueLiteral(DEFAULT_VALUE_LITERAL_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__UNSETTABLE:
        setUnsettable(UNSETTABLE_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__DERIVED:
        setDerived(DERIVED_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__CONTAINMENT:
        setContainment(CONTAINMENT_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__RESOLVE_PROXIES:
        setResolveProxies(RESOLVE_PROXIES_EDEFAULT);
        return;
      case EcorePackage.EREFERENCE__EOPPOSITE:
        setEOpposite((EReference)null);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isContainment()
  {
    return containment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContainment(boolean newContainment)
  {
    boolean oldContainment = containment;
    containment = newContainment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EREFERENCE__CONTAINMENT, oldContainment, containment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public boolean isContainer()
  {
    EReference theOpposite = getEOpposite();
    return theOpposite != null && theOpposite.isContainment();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isResolveProxies()
  {
    return resolveProxies;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResolveProxies(boolean newResolveProxies)
  {
    boolean oldResolveProxies = resolveProxies;
    resolveProxies = newResolveProxies;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EREFERENCE__RESOLVE_PROXIES, oldResolveProxies, resolveProxies));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEOpposite()
  {
    if (eOpposite != null && eOpposite.eIsProxy())
    {
      EReference oldEOpposite = eOpposite;
      eOpposite = (EReference)eResolveProxy((InternalEObject)eOpposite);
      if (eOpposite != oldEOpposite)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EcorePackage.EREFERENCE__EOPPOSITE, oldEOpposite, eOpposite));
      }
    }
    return eOpposite;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference basicGetEOpposite()
  {
    return eOpposite;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEOpposite(EReference newEOpposite)
  {
    EReference oldEOpposite = eOpposite;
    eOpposite = newEOpposite;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EREFERENCE__EOPPOSITE, oldEOpposite, eOpposite));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public EClass getEReferenceType()
  {
    return (EClass)getEType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public EClass basicGetEReferenceType()
  {
    return (EClass)eType;
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
        case EcorePackage.EREFERENCE__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
        case EcorePackage.EREFERENCE__ECONTAINING_CLASS:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, EcorePackage.EREFERENCE__ECONTAINING_CLASS, msgs);
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
        case EcorePackage.EREFERENCE__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
        case EcorePackage.EREFERENCE__ECONTAINING_CLASS:
          return eBasicSetContainer(null, EcorePackage.EREFERENCE__ECONTAINING_CLASS, msgs);
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
        case EcorePackage.EREFERENCE__ECONTAINING_CLASS:
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
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (containment: ");
    result.append(containment);
    result.append(", resolveProxies: ");
    result.append(resolveProxies);
    result.append(')');
    return result.toString();
  }

}
