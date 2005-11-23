/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: XMLMapImpl.java,v 1.5 2005/11/23 18:09:44 emerks Exp $
 */
package org.eclipse.emf.mapping.ecore2xml.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.emf.mapping.ecore2xml.XMLInfo;
import org.eclipse.emf.mapping.ecore2xml.XMLMap;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.impl.XMLMapImpl#getIDAttributeName <em>ID Attribute Name</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.impl.XMLMapImpl#getEcoreToXMLInfo <em>Ecore To XML Info</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.impl.XMLMapImpl#getNoNamespacePackage <em>No Namespace Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLMapImpl extends EObjectImpl implements XMLMap
{
  
  /**
   * The default value of the '{@link #getIDAttributeName() <em>ID Attribute Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIDAttributeName()
   * @generated
   * @ordered
   */
  protected static final String ID_ATTRIBUTE_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEcoreToXMLInfo() <em>Ecore To XML Info</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreToXMLInfo()
   * @generated
   * @ordered
   */
  protected EMap ecoreToXMLInfo = null;
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XMLMapImpl()
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
    return Ecore2XMLPackage.Literals.XML_MAP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getIDAttributeName()
  {
    return delegateXMLMap.getIDAttributeName();
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setIDAttributeName(String newIDAttributeName)
  {
    String oldIDAttributeName = delegateXMLMap.getIDAttributeName();
    delegateXMLMap.setIDAttributeName(newIDAttributeName);
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Ecore2XMLPackage.XML_MAP__ID_ATTRIBUTE_NAME, oldIDAttributeName, newIDAttributeName));
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getEcoreToXMLInfo()
  {
    if (ecoreToXMLInfo == null)
    {
      ecoreToXMLInfo = new EcoreEMap(Ecore2XMLPackage.Literals.ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY, ENamedElementToXMLInfoMapEntryImpl.class, this, Ecore2XMLPackage.XML_MAP__ECORE_TO_XML_INFO);
    }
    return ecoreToXMLInfo;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EPackage getNoNamespacePackage()
  {
    EPackage noNamespacePackage = basicGetNoNamespacePackage();
    return noNamespacePackage != null && noNamespacePackage.eIsProxy() ? (EPackage)eResolveProxy((InternalEObject)noNamespacePackage) : noNamespacePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EPackage basicGetNoNamespacePackage()
  {
    return delegateXMLMap.getNoNamespacePackage();
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setNoNamespacePackage(EPackage newNoNamespacePackage)
  {
    EPackage oldNoNamespacePackage = delegateXMLMap.getNoNamespacePackage();
    delegateXMLMap.setNoNamespacePackage(newNoNamespacePackage);
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Ecore2XMLPackage.XML_MAP__NO_NAMESPACE_PACKAGE, oldNoNamespacePackage, newNoNamespacePackage));
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
        case Ecore2XMLPackage.XML_MAP__ECORE_TO_XML_INFO:
          return ((InternalEList)getEcoreToXMLInfo()).basicRemove(otherEnd, msgs);
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
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case Ecore2XMLPackage.XML_MAP__ID_ATTRIBUTE_NAME:
        return getIDAttributeName();
      case Ecore2XMLPackage.XML_MAP__ECORE_TO_XML_INFO:
        if (coreType) return getEcoreToXMLInfo();
        else return getEcoreToXMLInfo().map();
      case Ecore2XMLPackage.XML_MAP__NO_NAMESPACE_PACKAGE:
        if (resolve) return getNoNamespacePackage();
        return basicGetNoNamespacePackage();
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
      case Ecore2XMLPackage.XML_MAP__ID_ATTRIBUTE_NAME:
        setIDAttributeName((String)newValue);
        return;
      case Ecore2XMLPackage.XML_MAP__ECORE_TO_XML_INFO:
        ((EStructuralFeature.Setting)getEcoreToXMLInfo()).set(newValue);
        return;
      case Ecore2XMLPackage.XML_MAP__NO_NAMESPACE_PACKAGE:
        setNoNamespacePackage((EPackage)newValue);
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
      case Ecore2XMLPackage.XML_MAP__ID_ATTRIBUTE_NAME:
        setIDAttributeName(ID_ATTRIBUTE_NAME_EDEFAULT);
        return;
      case Ecore2XMLPackage.XML_MAP__ECORE_TO_XML_INFO:
        getEcoreToXMLInfo().clear();
        return;
      case Ecore2XMLPackage.XML_MAP__NO_NAMESPACE_PACKAGE:
        setNoNamespacePackage((EPackage)null);
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
      case Ecore2XMLPackage.XML_MAP__ID_ATTRIBUTE_NAME:
        return ID_ATTRIBUTE_NAME_EDEFAULT == null ? getIDAttributeName() != null : !ID_ATTRIBUTE_NAME_EDEFAULT.equals(getIDAttributeName());
      case Ecore2XMLPackage.XML_MAP__ECORE_TO_XML_INFO:
        return ecoreToXMLInfo != null && !ecoreToXMLInfo.isEmpty();
      case Ecore2XMLPackage.XML_MAP__NO_NAMESPACE_PACKAGE:
        return basicGetNoNamespacePackage() != null;
    }
    return eDynamicIsSet(featureID);
  }

  protected class DelegateXMLMapImpl extends org.eclipse.emf.ecore.xmi.impl.XMLMapImpl
  {
    protected DelegateXMLMapImpl()
    {
      super();
      this.ecoreToXMLInfo = XMLMapImpl.this.getEcoreToXMLInfo().map();
    }
  }
  
  protected XMLResource.XMLMap delegateXMLMap = new DelegateXMLMapImpl();
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.ecore.xmi.XMLResource.XMLMap#add(org.eclipse.emf.ecore.ENamedElement, org.eclipse.emf.ecore.xmi.XMLResource.XMLInfo)
   */
  public void add(ENamedElement element, XMLResource.XMLInfo info)
  {
    delegateXMLMap.add(element, XMLInfo.class.isInstance(info) ? info : new XMLInfoImpl(info));
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.ecore.xmi.XMLResource.XMLMap#getClassifier(java.lang.String, java.lang.String)
   */
  public EClassifier getClassifier(String namespaceURI, String name)
  {
    return delegateXMLMap.getClassifier(namespaceURI, name);
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.ecore.xmi.XMLResource.XMLMap#getFeature(org.eclipse.emf.ecore.EClass, java.lang.String, java.lang.String)
   */
  public EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name)
  {
    return delegateXMLMap.getFeature(eClass, namespaceURI, name);
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.ecore.xmi.XMLResource.XMLMap#getFeatures(org.eclipse.emf.ecore.EClass)
   */
  public List getFeatures(EClass eClass)
  {
    return delegateXMLMap.getFeatures(eClass);
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.ecore.xmi.XMLResource.XMLMap#getInfo(org.eclipse.emf.ecore.ENamedElement)
   */
  public XMLResource.XMLInfo getInfo(ENamedElement element)
  {
    return delegateXMLMap.getInfo(element);
  }
  
} //XMLMapImpl
