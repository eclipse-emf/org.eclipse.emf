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
 * $Id: XMLInfoImpl.java,v 1.1 2005/03/18 21:02:01 khussey Exp $
 */
package org.eclipse.emf.mapping.ecore2xml.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

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
	protected EClass eStaticClass()
  {
    return Ecore2XMLPackage.eINSTANCE.getXMLInfo();
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
		delegateXMLInfo.setName(newName);
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
		delegateXMLInfo.setTargetNamespace(newTargetNamespace);
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
		delegateXMLInfo.setXMLRepresentation(newXMLRepresentation);
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
      case Ecore2XMLPackage.XML_INFO__NAME:
        return getName();
      case Ecore2XMLPackage.XML_INFO__TARGET_NAMESPACE:
        return getTargetNamespace();
      case Ecore2XMLPackage.XML_INFO__XML_REPRESENTATION:
        return new Integer(getXMLRepresentation());
    }
    return eDynamicGet(eFeature, resolve);
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
      case Ecore2XMLPackage.XML_INFO__NAME:
        setName((String)newValue);
        return;
      case Ecore2XMLPackage.XML_INFO__TARGET_NAMESPACE:
        setTargetNamespace((String)newValue);
        return;
      case Ecore2XMLPackage.XML_INFO__XML_REPRESENTATION:
        setXMLRepresentation(((Integer)newValue).intValue());
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
    eDynamicUnset(eFeature);
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
      case Ecore2XMLPackage.XML_INFO__NAME:
        return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
      case Ecore2XMLPackage.XML_INFO__TARGET_NAMESPACE:
        return TARGET_NAMESPACE_EDEFAULT == null ? getTargetNamespace() != null : !TARGET_NAMESPACE_EDEFAULT.equals(getTargetNamespace());
      case Ecore2XMLPackage.XML_INFO__XML_REPRESENTATION:
        return getXMLRepresentation() != XML_REPRESENTATION_EDEFAULT;
    }
    return eDynamicIsSet(eFeature);
  }

	protected XMLResource.XMLInfo delegateXMLInfo = null;
	
	protected XMLInfoImpl(XMLResource.XMLInfo delegateXMLInfo)
  {
		super();
		
		this.delegateXMLInfo = delegateXMLInfo;
	}

} //XMLInfoImpl
