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
 * $Id: Ecore2XMLFactoryImpl.java,v 1.1 2005/03/18 21:02:01 khussey Exp $
 */
package org.eclipse.emf.mapping.ecore2xml.impl;


import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.mapping.ecore2xml.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ecore2XMLFactoryImpl extends EFactoryImpl implements Ecore2XMLFactory
{

  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Ecore2XMLFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case Ecore2XMLPackage.XML_INFO: return createXMLInfo();
      case Ecore2XMLPackage.XML_MAP: return createXMLMap();
      case Ecore2XMLPackage.ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY: return (EObject)createENamedElementToXMLInfoMapEntry();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public XMLInfo createXMLInfo()
  {
    XMLInfoImpl xmlInfo = new XMLInfoImpl();
    return xmlInfo;
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public XMLMap createXMLMap()
  {
    XMLMapImpl xmlMap = new XMLMapImpl();
    return xmlMap;
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Map.Entry createENamedElementToXMLInfoMapEntry()
  {
    ENamedElementToXMLInfoMapEntryImpl eNamedElementToXMLInfoMapEntry = new ENamedElementToXMLInfoMapEntryImpl();
    return eNamedElementToXMLInfoMapEntry;
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Ecore2XMLPackage getEcore2XMLPackage()
  {
    return (Ecore2XMLPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	public static Ecore2XMLPackage getPackage()
  {
    return Ecore2XMLPackage.eINSTANCE;
  }

} //Ecore2XMLFactoryImpl
