/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: EStringToStringMapEntryImpl.java,v 1.6 2006/05/12 21:10:08 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.ECrossReferenceEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EString To String Map Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EStringToStringMapEntryImpl extends EObjectImpl implements BasicEMap.Entry
{
  /**
   * The default value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedKey()
   * @generated
   * @ordered
   */
  protected static final String KEY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedKey()
   * @generated
   * @ordered
   */
  protected String key = KEY_EDEFAULT;

  /**
   * The default value of the '{@link #getTypedValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedValue()
   * @generated
   * @ordered
   */
  protected static final String VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTypedValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedValue()
   * @generated
   * @ordered
   */
  protected String value = VALUE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EStringToStringMapEntryImpl()
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
    return EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY;
  }

  protected static class EStringToStringMapEntryPropertiesHolderImpl extends EPropertiesHolderBaseImpl
  {
    protected EList eContents;
    protected EList eCrossReferences;

    public EList getEContents()
    {
      return eContents;
    }

    public void setEContents(EList eContents)
    {
      this.eContents = eContents;
    }

    public EList getECrossReferences()
    {
      return eCrossReferences;
    }

    public void setECrossReferences(EList eCrossReferences)
    {
      this.eCrossReferences = eCrossReferences;
    }
  }

  protected EPropertiesHolder eProperties()
  {
    if (eProperties == null)
    {
      eProperties = new EPropertiesHolderImpl();
    }
    return eProperties;
  }

  protected URI eProxyURI;

  public boolean eIsProxy()
  {
    return eProxyURI != null;
  }

  public URI eProxyURI()
  {
    return eProxyURI;
  }
  
  public void eSetProxyURI(URI uri)
  {
    eProxyURI = uri;
  }

  public EList eContents()
  {
    return
      eClass() == EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY ?
        EContentsEList.EMPTY_CONTENTS_ELIST :
        super.eContents();
  }

  public EList eCrossReferences()
  {
    return
      eClass() == EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY ?
        ECrossReferenceEList.EMPTY_CROSS_REFERENCE_ELIST :
        super.eContents();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTypedKey()
  {
    return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypedKey(String newKey)
  {
    String oldKey = key;
    key = newKey;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__KEY, oldKey, key));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTypedValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypedValue(String newValue)
  {
    String oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__VALUE, oldValue, value));
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
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__KEY:
        return getTypedKey();
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__VALUE:
        return getTypedValue();
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
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__KEY:
        setTypedKey((String)newValue);
        return;
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__VALUE:
        setTypedValue((String)newValue);
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
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__KEY:
        setTypedKey(KEY_EDEFAULT);
        return;
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__VALUE:
        setTypedValue(VALUE_EDEFAULT);
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
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__KEY:
        return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
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
    result.append(" (key: ");
    result.append(key);
    result.append(", value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected int hash= -1;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getHash()
  {
    if (hash == -1)
    {
      Object theKey = getKey();
      hash = (theKey == null ? 0 : theKey.hashCode());
    }
    return hash;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHash(int hash)
  {
    this.hash = hash;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getKey()
  {
    return getTypedKey();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKey(Object key)
  {
    setTypedKey((String)key);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getValue()
  {
    return getTypedValue();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object setValue(Object value)
  {
    Object oldValue = getValue();
    setTypedValue((String)value);
    return oldValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getEMap()
  {
    EObject container = eContainer();
    return container == null ? null : (EMap)container.eGet(eContainmentFeature());
  }

} //EStringToStringMapEntryImpl
