/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: XMLNamespaceFactoryImpl.java,v 1.3 2004/05/16 17:13:02 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.namespace.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.xml.namespace.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XMLNamespaceFactoryImpl extends EFactoryImpl implements XMLNamespaceFactory
{
  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLNamespaceFactoryImpl()
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
      case XMLNamespacePackage.XML_NAMESPACE_DOCUMENT_ROOT: return createXMLNamespaceDocumentRoot();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case XMLNamespacePackage.SPACE_TYPE:
      {
        SpaceType result = SpaceType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case XMLNamespacePackage.SPACE_TYPE_OBJECT:
        return createSpaceTypeObjectFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case XMLNamespacePackage.SPACE_TYPE:
        return instanceValue == null ? null : instanceValue.toString();
      case XMLNamespacePackage.SPACE_TYPE_OBJECT:
        return convertSpaceTypeObjectToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLNamespaceDocumentRoot createXMLNamespaceDocumentRoot()
  {
    XMLNamespaceDocumentRootImpl xmlNamespaceDocumentRoot = new XMLNamespaceDocumentRootImpl();
    return xmlNamespaceDocumentRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SpaceType createSpaceTypeObjectFromString(EDataType eDataType, String initialValue)
  {
    return (SpaceType)XMLNamespaceFactory.eINSTANCE.createFromString(XMLNamespacePackage.eINSTANCE.getSpaceType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertSpaceTypeObjectToString(EDataType eDataType, Object instanceValue)
  {
    return XMLNamespaceFactory.eINSTANCE.convertToString(XMLNamespacePackage.eINSTANCE.getSpaceType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLNamespacePackage getXMLNamespacePackage()
  {
    return (XMLNamespacePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static XMLNamespacePackage getPackage()
  {
    return XMLNamespacePackage.eINSTANCE;
  }

} //XMLNamespaceFactoryImpl
