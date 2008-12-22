/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: DocumentRootImpl.java,v 1.6 2008/12/22 14:25:24 emerks Exp $
 */
package org.eclipse.emf.test.models.qname.impl;

import java.util.List;

import javax.xml.namespace.QName;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.qname.DocumentRoot;
import org.eclipse.emf.test.models.qname.QNamePackage;
import org.eclipse.emf.test.models.qname.ResourceType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAnyE <em>Any E</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAnyEU <em>Any EU</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAInt <em>AInt</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAQname <em>AQname</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAUnion <em>AUnion</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot
{
  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected FeatureMap mixed;

  /**
   * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXMLNSPrefixMap()
   * @generated
   * @ordered
   */
  protected EMap<String, String> xMLNSPrefixMap;

  /**
   * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXSISchemaLocation()
   * @generated
   * @ordered
   */
  protected EMap<String, String> xSISchemaLocation;

  /**
   * The default value of the '{@link #getAnyE() <em>Any E</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnyE()
   * @generated
   * @ordered
   */
  protected static final QName ANY_E_EDEFAULT = null;

  /**
   * The default value of the '{@link #getAnyEU() <em>Any EU</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnyEU()
   * @generated
   * @ordered
   */
  protected static final List<Object> ANY_EU_EDEFAULT = null;

  /**
   * The default value of the '{@link #getAInt() <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAInt()
   * @generated
   * @ordered
   */
  protected static final int AINT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getAInt() <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAInt()
   * @generated
   * @ordered
   */
  protected int aInt = AINT_EDEFAULT;

  /**
   * This is true if the AInt attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aIntESet;

  /**
   * The default value of the '{@link #getAQname() <em>AQname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAQname()
   * @generated
   * @ordered
   */
  protected static final QName AQNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAQname() <em>AQname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAQname()
   * @generated
   * @ordered
   */
  protected QName aQname = AQNAME_EDEFAULT;

  /**
   * The default value of the '{@link #getAUnion() <em>AUnion</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAUnion()
   * @generated
   * @ordered
   */
  protected static final List<Object> AUNION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAUnion() <em>AUnion</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAUnion()
   * @generated
   * @ordered
   */
  protected List<Object> aUnion = AUNION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DocumentRootImpl()
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
    return QNamePackage.Literals.DOCUMENT_ROOT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicFeatureMap(this, QNamePackage.DOCUMENT_ROOT__MIXED);
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<String, String> getXMLNSPrefixMap()
  {
    if (xMLNSPrefixMap == null)
    {
      xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, QNamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    }
    return xMLNSPrefixMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<String, String> getXSISchemaLocation()
  {
    if (xSISchemaLocation == null)
    {
      xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, QNamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    }
    return xSISchemaLocation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QName getAnyE()
  {
    return (QName)getMixed().get(QNamePackage.Literals.DOCUMENT_ROOT__ANY_E, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnyE(QName newAnyE)
  {
    ((FeatureMap.Internal)getMixed()).set(QNamePackage.Literals.DOCUMENT_ROOT__ANY_E, newAnyE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public List<Object> getAnyEU()
  {
    return (List<Object>)getMixed().get(QNamePackage.Literals.DOCUMENT_ROOT__ANY_EU, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnyEU(List<Object> newAnyEU)
  {
    ((FeatureMap.Internal)getMixed()).set(QNamePackage.Literals.DOCUMENT_ROOT__ANY_EU, newAnyEU);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceType getResource()
  {
    return (ResourceType)getMixed().get(QNamePackage.Literals.DOCUMENT_ROOT__RESOURCE, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetResource(ResourceType newResource, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)getMixed()).basicAdd(QNamePackage.Literals.DOCUMENT_ROOT__RESOURCE, newResource, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResource(ResourceType newResource)
  {
    ((FeatureMap.Internal)getMixed()).set(QNamePackage.Literals.DOCUMENT_ROOT__RESOURCE, newResource);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getAInt()
  {
    return aInt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAInt(int newAInt)
  {
    int oldAInt = aInt;
    aInt = newAInt;
    boolean oldAIntESet = aIntESet;
    aIntESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QNamePackage.DOCUMENT_ROOT__AINT, oldAInt, aInt, !oldAIntESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAInt()
  {
    int oldAInt = aInt;
    boolean oldAIntESet = aIntESet;
    aInt = AINT_EDEFAULT;
    aIntESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, QNamePackage.DOCUMENT_ROOT__AINT, oldAInt, AINT_EDEFAULT, oldAIntESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAInt()
  {
    return aIntESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QName getAQname()
  {
    return aQname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAQname(QName newAQname)
  {
    QName oldAQname = aQname;
    aQname = newAQname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QNamePackage.DOCUMENT_ROOT__AQNAME, oldAQname, aQname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<Object> getAUnion()
  {
    return aUnion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAUnion(List<Object> newAUnion)
  {
    List<Object> oldAUnion = aUnion;
    aUnion = newAUnion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QNamePackage.DOCUMENT_ROOT__AUNION, oldAUnion, aUnion));
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
      case QNamePackage.DOCUMENT_ROOT__MIXED:
        return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
      case QNamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
      case QNamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
      case QNamePackage.DOCUMENT_ROOT__RESOURCE:
        return basicSetResource(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case QNamePackage.DOCUMENT_ROOT__MIXED:
        if (coreType) return getMixed();
        return ((FeatureMap.Internal)getMixed()).getWrapper();
      case QNamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        if (coreType) return getXMLNSPrefixMap();
        else return getXMLNSPrefixMap().map();
      case QNamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        if (coreType) return getXSISchemaLocation();
        else return getXSISchemaLocation().map();
      case QNamePackage.DOCUMENT_ROOT__ANY_E:
        return getAnyE();
      case QNamePackage.DOCUMENT_ROOT__ANY_EU:
        return getAnyEU();
      case QNamePackage.DOCUMENT_ROOT__RESOURCE:
        return getResource();
      case QNamePackage.DOCUMENT_ROOT__AINT:
        return getAInt();
      case QNamePackage.DOCUMENT_ROOT__AQNAME:
        return getAQname();
      case QNamePackage.DOCUMENT_ROOT__AUNION:
        return getAUnion();
    }
    return super.eGet(featureID, resolve, coreType);
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
      case QNamePackage.DOCUMENT_ROOT__MIXED:
        ((FeatureMap.Internal)getMixed()).set(newValue);
        return;
      case QNamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
        return;
      case QNamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
        return;
      case QNamePackage.DOCUMENT_ROOT__ANY_E:
        setAnyE((QName)newValue);
        return;
      case QNamePackage.DOCUMENT_ROOT__ANY_EU:
        setAnyEU((List<Object>)newValue);
        return;
      case QNamePackage.DOCUMENT_ROOT__RESOURCE:
        setResource((ResourceType)newValue);
        return;
      case QNamePackage.DOCUMENT_ROOT__AINT:
        setAInt((Integer)newValue);
        return;
      case QNamePackage.DOCUMENT_ROOT__AQNAME:
        setAQname((QName)newValue);
        return;
      case QNamePackage.DOCUMENT_ROOT__AUNION:
        setAUnion((List<Object>)newValue);
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
      case QNamePackage.DOCUMENT_ROOT__MIXED:
        getMixed().clear();
        return;
      case QNamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        return;
      case QNamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        return;
      case QNamePackage.DOCUMENT_ROOT__ANY_E:
        setAnyE(ANY_E_EDEFAULT);
        return;
      case QNamePackage.DOCUMENT_ROOT__ANY_EU:
        setAnyEU(ANY_EU_EDEFAULT);
        return;
      case QNamePackage.DOCUMENT_ROOT__RESOURCE:
        setResource((ResourceType)null);
        return;
      case QNamePackage.DOCUMENT_ROOT__AINT:
        unsetAInt();
        return;
      case QNamePackage.DOCUMENT_ROOT__AQNAME:
        setAQname(AQNAME_EDEFAULT);
        return;
      case QNamePackage.DOCUMENT_ROOT__AUNION:
        setAUnion(AUNION_EDEFAULT);
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
      case QNamePackage.DOCUMENT_ROOT__MIXED:
        return mixed != null && !mixed.isEmpty();
      case QNamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
      case QNamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
      case QNamePackage.DOCUMENT_ROOT__ANY_E:
        return ANY_E_EDEFAULT == null ? getAnyE() != null : !ANY_E_EDEFAULT.equals(getAnyE());
      case QNamePackage.DOCUMENT_ROOT__ANY_EU:
        return ANY_EU_EDEFAULT == null ? getAnyEU() != null : !ANY_EU_EDEFAULT.equals(getAnyEU());
      case QNamePackage.DOCUMENT_ROOT__RESOURCE:
        return getResource() != null;
      case QNamePackage.DOCUMENT_ROOT__AINT:
        return isSetAInt();
      case QNamePackage.DOCUMENT_ROOT__AQNAME:
        return AQNAME_EDEFAULT == null ? aQname != null : !AQNAME_EDEFAULT.equals(aQname);
      case QNamePackage.DOCUMENT_ROOT__AUNION:
        return AUNION_EDEFAULT == null ? aUnion != null : !AUNION_EDEFAULT.equals(aUnion);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (mixed: ");
    result.append(mixed);
    result.append(", aInt: ");
    if (aIntESet) result.append(aInt); else result.append("<unset>");
    result.append(", aQname: ");
    result.append(aQname);
    result.append(", aUnion: ");
    result.append(aUnion);
    result.append(')');
    return result.toString();
  }

} //DocumentRootImpl
