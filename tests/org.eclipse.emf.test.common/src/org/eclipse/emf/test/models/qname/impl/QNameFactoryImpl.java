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
 * $Id: QNameFactoryImpl.java,v 1.3 2007/05/07 17:26:29 marcelop Exp $
 */
package org.eclipse.emf.test.models.qname.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.namespace.QName;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.Diagnostician;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.qname.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QNameFactoryImpl extends EFactoryImpl implements QNameFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static QNameFactory init()
  {
    try
    {
      QNameFactory theQNameFactory = (QNameFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org.eclipse.emf.test.models/qname"); 
      if (theQNameFactory != null)
      {
        return theQNameFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new QNameFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QNameFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case QNamePackage.DOCUMENT_ROOT: return createDocumentRoot();
      case QNamePackage.RESOURCE_TYPE: return createResourceType();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case QNamePackage.INT_QNAME_UNION:
        return createIntQNameUnionFromString(eDataType, initialValue);
      case QNamePackage.LIST_UNION:
        return createListUnionFromString(eDataType, initialValue);
      case QNamePackage.QNAME_LIST:
        return createQnameListFromString(eDataType, initialValue);
      case QNamePackage.UNION:
        return createUnionFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case QNamePackage.INT_QNAME_UNION:
        return convertIntQNameUnionToString(eDataType, instanceValue);
      case QNamePackage.LIST_UNION:
        return convertListUnionToString(eDataType, instanceValue);
      case QNamePackage.QNAME_LIST:
        return convertQnameListToString(eDataType, instanceValue);
      case QNamePackage.UNION:
        return convertUnionToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DocumentRoot createDocumentRoot()
  {
    DocumentRootImpl documentRoot = new DocumentRootImpl();
    return documentRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceType createResourceType()
  {
    ResourceTypeImpl resourceType = new ResourceTypeImpl();
    return resourceType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createIntQNameUnionFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    Object result = null;
    RuntimeException exception = null;
    try
    {
      result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.INT, initialValue);
      if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null))
      {
        return result;
      }
    }
    catch (RuntimeException e)
    {
      exception = e;
    }
    try
    {
      result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.QNAME, initialValue);
      if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null))
      {
        return result;
      }
    }
    catch (RuntimeException e)
    {
      exception = e;
    }
    if (result != null || exception == null) return result;
    
    throw exception;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIntQNameUnionToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null) return null;
    if (XMLTypePackage.Literals.INT.isInstance(instanceValue))
    {
      try
      {
        String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.INT, instanceValue);
        if (value != null) return value;
      }
      catch (Exception e)
      {
        // Keep trying other member types until all have failed.
      }
    }
    if (XMLTypePackage.Literals.QNAME.isInstance(instanceValue))
    {
      try
      {
        String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.QNAME, instanceValue);
        if (value != null) return value;
      }
      catch (Exception e)
      {
        // Keep trying other member types until all have failed.
      }
    }
    throw new IllegalArgumentException("Invalid value: '"+instanceValue+"' for datatype :"+eDataType.getName());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<Object> createListUnionFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    List<Object> result = new ArrayList<Object>();
    for (StringTokenizer stringTokenizer = new StringTokenizer(initialValue); stringTokenizer.hasMoreTokens(); )
    {
      String item = stringTokenizer.nextToken();
      result.add(createUnionFromString(QNamePackage.Literals.UNION, item));
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertListUnionToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null) return null;
    List<?> list = (List<?>)instanceValue;
    if (list.isEmpty()) return "";
    StringBuffer result = new StringBuffer();
    for (Object item : list)
    {
      result.append(convertUnionToString(QNamePackage.Literals.UNION, item));
      result.append(' ');
    }
    return result.substring(0, result.length() - 1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<QName> createQnameListFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    List<QName> result = new ArrayList<QName>();
    for (StringTokenizer stringTokenizer = new StringTokenizer(initialValue); stringTokenizer.hasMoreTokens(); )
    {
      String item = stringTokenizer.nextToken();
      result.add((QName)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.QNAME, item));
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertQnameListToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null) return null;
    List<?> list = (List<?>)instanceValue;
    if (list.isEmpty()) return "";
    StringBuffer result = new StringBuffer();
    for (Object item : list)
    {
      result.append(XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.QNAME, item));
      result.append(' ');
    }
    return result.substring(0, result.length() - 1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createUnionFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    Object result = null;
    RuntimeException exception = null;
    try
    {
      result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.BOOLEAN, initialValue);
      if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null))
      {
        return result;
      }
    }
    catch (RuntimeException e)
    {
      exception = e;
    }
    try
    {
      result = createIntQNameUnionFromString(QNamePackage.Literals.INT_QNAME_UNION, initialValue);
      if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null))
      {
        return result;
      }
    }
    catch (RuntimeException e)
    {
      exception = e;
    }
    if (result != null || exception == null) return result;
    
    throw exception;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUnionToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null) return null;
    if (XMLTypePackage.Literals.BOOLEAN.isInstance(instanceValue))
    {
      try
      {
        String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.BOOLEAN, instanceValue);
        if (value != null) return value;
      }
      catch (Exception e)
      {
        // Keep trying other member types until all have failed.
      }
    }
    if (QNamePackage.Literals.INT_QNAME_UNION.isInstance(instanceValue))
    {
      try
      {
        String value = convertIntQNameUnionToString(QNamePackage.Literals.INT_QNAME_UNION, instanceValue);
        if (value != null) return value;
      }
      catch (Exception e)
      {
        // Keep trying other member types until all have failed.
      }
    }
    throw new IllegalArgumentException("Invalid value: '"+instanceValue+"' for datatype :"+eDataType.getName());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QNamePackage getQNamePackage()
  {
    return (QNamePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static QNamePackage getPackage()
  {
    return QNamePackage.eINSTANCE;
  }

} //QNameFactoryImpl
