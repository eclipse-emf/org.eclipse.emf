/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: XMLInfoImpl.java,v 1.8 2008/12/22 14:26:16 emerks Exp $
 */
package org.eclipse.emf.mapping.ecore2xml.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.emf.mapping.ecore2xml.XMLInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.impl.XMLInfoImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.impl.XMLInfoImpl#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.ecore2xml.impl.XMLInfoImpl#getXMLRepresentation <em>XML Representation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLInfoImpl extends EObjectImpl implements XMLInfo
{
  
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;
  
  /**
   * The default value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetNamespace()
   * @generated
   * @ordered
   */
  protected static final String TARGET_NAMESPACE_EDEFAULT = null;
  
  /**
   * The default value of the '{@link #getXMLRepresentation() <em>XML Representation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXMLRepresentation()
   * @generated
   * @ordered
   */
  protected static final int XML_REPRESENTATION_EDEFAULT = -1;
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected XMLInfoImpl()
  {
    super();

    this.delegateXMLInfo = new org.eclipse.emf.ecore.xmi.impl.XMLInfoImpl();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return Ecore2XMLPackage.Literals.XML_INFO;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getName()
  {
    return delegateXMLInfo.getName();
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setName(String newName)
  {
    String oldName = delegateXMLInfo.getName();
    delegateXMLInfo.setName(newName);
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Ecore2XMLPackage.XML_INFO__NAME, oldName, newName));
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getTargetNamespace()
  {
    return delegateXMLInfo.getTargetNamespace();
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setTargetNamespace(String newTargetNamespace)
  {
    String oldTargetNamespace = delegateXMLInfo.getTargetNamespace();
    delegateXMLInfo.setTargetNamespace(newTargetNamespace);
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Ecore2XMLPackage.XML_INFO__TARGET_NAMESPACE, oldTargetNamespace, newTargetNamespace));
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public int getXMLRepresentation()
  {
    return delegateXMLInfo.getXMLRepresentation();
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setXMLRepresentation(int newXMLRepresentation)
  {
    int oldXMLRepresentation = delegateXMLInfo.getXMLRepresentation();
    delegateXMLInfo.setXMLRepresentation(newXMLRepresentation);
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Ecore2XMLPackage.XML_INFO__XML_REPRESENTATION, oldXMLRepresentation, newXMLRepresentation));
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
      case Ecore2XMLPackage.XML_INFO__NAME:
        return getName();
      case Ecore2XMLPackage.XML_INFO__TARGET_NAMESPACE:
        return getTargetNamespace();
      case Ecore2XMLPackage.XML_INFO__XML_REPRESENTATION:
        return getXMLRepresentation();
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
      case Ecore2XMLPackage.XML_INFO__NAME:
        setName((String)newValue);
        return;
      case Ecore2XMLPackage.XML_INFO__TARGET_NAMESPACE:
        setTargetNamespace((String)newValue);
        return;
      case Ecore2XMLPackage.XML_INFO__XML_REPRESENTATION:
        setXMLRepresentation((Integer)newValue);
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
      case Ecore2XMLPackage.XML_INFO__NAME:
        setName(NAME_EDEFAULT);
        return;
      case Ecore2XMLPackage.XML_INFO__TARGET_NAMESPACE:
        setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
        return;
      case Ecore2XMLPackage.XML_INFO__XML_REPRESENTATION:
        setXMLRepresentation(XML_REPRESENTATION_EDEFAULT);
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
      case Ecore2XMLPackage.XML_INFO__NAME:
        return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
      case Ecore2XMLPackage.XML_INFO__TARGET_NAMESPACE:
        return TARGET_NAMESPACE_EDEFAULT == null ? getTargetNamespace() != null : !TARGET_NAMESPACE_EDEFAULT.equals(getTargetNamespace());
      case Ecore2XMLPackage.XML_INFO__XML_REPRESENTATION:
        return getXMLRepresentation() != XML_REPRESENTATION_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  protected XMLResource.XMLInfo delegateXMLInfo = null;
  
  protected XMLInfoImpl(XMLResource.XMLInfo delegateXMLInfo)
  {
    super();
    
    this.delegateXMLInfo = delegateXMLInfo;
  }
  
} //XMLInfoImpl
