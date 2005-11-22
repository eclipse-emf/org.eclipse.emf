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
 * $Id: ETypedElementImpl.java,v 1.7 2005/11/22 22:34:11 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ETyped Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.ETypedElementImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.ETypedElementImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.ETypedElementImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.ETypedElementImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.ETypedElementImpl#isMany <em>Many</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.ETypedElementImpl#isRequired <em>Required</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.ETypedElementImpl#getEType <em>EType</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ETypedElementImpl extends ENamedElementImpl implements ETypedElement
{
  /**
   * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOrdered()
   * @generated
   * @ordered
   */
  protected static final boolean ORDERED_EDEFAULT = true;

  /**
   * The flag representing the value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOrdered()
   * @generated
   * @ordered
   */
  protected static final int ORDERED_EFLAG = 1 << 8;

  /**
   * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnique()
   * @generated
   * @ordered
   */
  protected static final boolean UNIQUE_EDEFAULT = true;

  /**
   * The flag representing the value of the '{@link #isUnique() <em>Unique</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnique()
   * @generated
   * @ordered
   */
  protected static final int UNIQUE_EFLAG = 1 << 9;

  /**
   * The default value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLowerBound()
   * @generated
   * @ordered
   */
  protected static final int LOWER_BOUND_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLowerBound()
   * @generated
   * @ordered
   */
  protected int lowerBound = LOWER_BOUND_EDEFAULT;

  /**
   * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpperBound()
   * @generated
   * @ordered
   */
  protected static final int UPPER_BOUND_EDEFAULT = 1;

  /**
   * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpperBound()
   * @generated
   * @ordered
   */
  protected int upperBound = UPPER_BOUND_EDEFAULT;

  /**
   * The default value of the '{@link #isMany() <em>Many</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMany()
   * @generated
   * @ordered
   */
  protected static final boolean MANY_EDEFAULT = false;

  /**
   * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRequired()
   * @generated
   * @ordered
   */
  protected static final boolean REQUIRED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #getEType() <em>EType</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEType()
   * @generated
   * @ordered
   */
  protected EClassifier eType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ETypedElementImpl()
  {
    super();
    eFlags |= ORDERED_EFLAG;
    eFlags |= UNIQUE_EFLAG;
  }

  public void freeze()
  {
    getEType();
    super.freeze();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return EcorePackage.eINSTANCE.getETypedElement();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isOrdered()
  {
    return (eFlags & ORDERED_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOrdered(boolean newOrdered)
  {
    boolean oldOrdered = (eFlags & ORDERED_EFLAG) != 0;
    if (newOrdered) eFlags |= ORDERED_EFLAG; else eFlags &= ~ORDERED_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.ETYPED_ELEMENT__ORDERED, oldOrdered, newOrdered));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isUnique()
  {
    return (eFlags & UNIQUE_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnique(boolean newUnique)
  {
    boolean oldUnique = (eFlags & UNIQUE_EFLAG) != 0;
    if (newUnique) eFlags |= UNIQUE_EFLAG; else eFlags &= ~UNIQUE_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.ETYPED_ELEMENT__UNIQUE, oldUnique, newUnique));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getLowerBound()
  {
    return lowerBound;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLowerBound(int newLowerBound)
  {
    int oldLowerBound = lowerBound;
    lowerBound = newLowerBound;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.ETYPED_ELEMENT__LOWER_BOUND, oldLowerBound, lowerBound));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getUpperBound()
  {
    return upperBound;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpperBound(int newUpperBound)
  {
    int oldUpperBound = upperBound;
    upperBound = newUpperBound;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.ETYPED_ELEMENT__UPPER_BOUND, oldUpperBound, upperBound));
  }

  public boolean isMany()
  {
    int upper = getUpperBound();
    return upper > 1 || upper == UNBOUNDED_MULTIPLICITY;
  }

  public boolean isRequired()
  {
    int lower = getLowerBound();
    return lower >= 1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClassifier getETypeGen()
  {
    if (eType != null && eType.eIsProxy())
    {
      InternalEObject oldEType = (InternalEObject)eType;
      eType = (EClassifier)eResolveProxy(oldEType);
      if (eType != oldEType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EcorePackage.ETYPED_ELEMENT__ETYPE, oldEType, eType));
      }
    }
    return eType;
  }

  public EClassifier getEType()
  {
    return isFrozen() ? eType : getETypeGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClassifier basicGetEType()
  {
    return eType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEType(EClassifier newEType)
  {
    EClassifier oldEType = eType;
    eType = newEType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.ETYPED_ELEMENT__ETYPE, oldEType, eType));
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
      case EcorePackage.ETYPED_ELEMENT__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.ETYPED_ELEMENT__NAME:
        return getName();
      case EcorePackage.ETYPED_ELEMENT__ORDERED:
        return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.ETYPED_ELEMENT__UNIQUE:
        return isUnique() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.ETYPED_ELEMENT__LOWER_BOUND:
        return new Integer(getLowerBound());
      case EcorePackage.ETYPED_ELEMENT__UPPER_BOUND:
        return new Integer(getUpperBound());
      case EcorePackage.ETYPED_ELEMENT__MANY:
        return isMany() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.ETYPED_ELEMENT__REQUIRED:
        return isRequired() ? Boolean.TRUE : Boolean.FALSE;
      case EcorePackage.ETYPED_ELEMENT__ETYPE:
        if (resolve) return getEType();
        return basicGetEType();
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
      case EcorePackage.ETYPED_ELEMENT__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.ETYPED_ELEMENT__NAME:
        setName((String)newValue);
        return;
      case EcorePackage.ETYPED_ELEMENT__ORDERED:
        setOrdered(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.ETYPED_ELEMENT__UNIQUE:
        setUnique(((Boolean)newValue).booleanValue());
        return;
      case EcorePackage.ETYPED_ELEMENT__LOWER_BOUND:
        setLowerBound(((Integer)newValue).intValue());
        return;
      case EcorePackage.ETYPED_ELEMENT__UPPER_BOUND:
        setUpperBound(((Integer)newValue).intValue());
        return;
      case EcorePackage.ETYPED_ELEMENT__ETYPE:
        setEType((EClassifier)newValue);
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
      case EcorePackage.ETYPED_ELEMENT__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.ETYPED_ELEMENT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.ETYPED_ELEMENT__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case EcorePackage.ETYPED_ELEMENT__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case EcorePackage.ETYPED_ELEMENT__LOWER_BOUND:
        setLowerBound(LOWER_BOUND_EDEFAULT);
        return;
      case EcorePackage.ETYPED_ELEMENT__UPPER_BOUND:
        setUpperBound(UPPER_BOUND_EDEFAULT);
        return;
      case EcorePackage.ETYPED_ELEMENT__ETYPE:
        setEType((EClassifier)null);
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
      case EcorePackage.ETYPED_ELEMENT__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.ETYPED_ELEMENT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.ETYPED_ELEMENT__ORDERED:
        return ((eFlags & ORDERED_EFLAG) != 0) != ORDERED_EDEFAULT;
      case EcorePackage.ETYPED_ELEMENT__UNIQUE:
        return ((eFlags & UNIQUE_EFLAG) != 0) != UNIQUE_EDEFAULT;
      case EcorePackage.ETYPED_ELEMENT__LOWER_BOUND:
        return lowerBound != LOWER_BOUND_EDEFAULT;
      case EcorePackage.ETYPED_ELEMENT__UPPER_BOUND:
        return upperBound != UPPER_BOUND_EDEFAULT;
      case EcorePackage.ETYPED_ELEMENT__MANY:
        return isMany() != MANY_EDEFAULT;
      case EcorePackage.ETYPED_ELEMENT__REQUIRED:
        return isRequired() != REQUIRED_EDEFAULT;
      case EcorePackage.ETYPED_ELEMENT__ETYPE:
        return eType != null;
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
    result.append(" (ordered: ");
    result.append((eFlags & ORDERED_EFLAG) != 0);
    result.append(", unique: ");
    result.append((eFlags & UNIQUE_EFLAG) != 0);
    result.append(", lowerBound: ");
    result.append(lowerBound);
    result.append(", upperBound: ");
    result.append(upperBound);
    result.append(')');
    return result.toString();
  }

} //ETypedElementImpl
