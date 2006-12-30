/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id$
 */
package org.eclipse.emf.test.models.qname.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
public class QnameFactoryImpl extends EFactoryImpl implements QnameFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static QnameFactory init()
  {
    try
    {
      QnameFactory theQnameFactory = (QnameFactory)EPackage.Registry.INSTANCE.getEFactory("http://org/eclipse/emf/test/models/qname"); 
      if (theQnameFactory != null)
      {
        return theQnameFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new QnameFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QnameFactoryImpl()
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
      case QnamePackage.DOCUMENT_ROOT: return createDocumentRoot();
      case QnamePackage.RESOURCE_TYPE: return createResourceType();
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
      case QnamePackage.INT_QNAME_UNION:
        return createIntQNameUnionFromString(eDataType, initialValue);
      case QnamePackage.LIST_UNION:
        return createListUnionFromString(eDataType, initialValue);
      case QnamePackage.QNAME_LIST:
        return createQnameListFromString(eDataType, initialValue);
      case QnamePackage.UNION:
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
      case QnamePackage.INT_QNAME_UNION:
        return convertIntQNameUnionToString(eDataType, instanceValue);
      case QnamePackage.LIST_UNION:
        return convertListUnionToString(eDataType, instanceValue);
      case QnamePackage.QNAME_LIST:
        return convertQnameListToString(eDataType, instanceValue);
      case QnamePackage.UNION:
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
      result.add(createUnionFromString(QnamePackage.Literals.UNION, item));
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
      result.append(convertUnionToString(QnamePackage.Literals.UNION, item));
      result.append(' ');
    }
    return result.substring(0, result.length() - 1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<Object> createQnameListFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    List<Object> result = new ArrayList<Object>();
    for (StringTokenizer stringTokenizer = new StringTokenizer(initialValue); stringTokenizer.hasMoreTokens(); )
    {
      String item = stringTokenizer.nextToken();
      result.add(XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.QNAME, item));
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
      result = createIntQNameUnionFromString(QnamePackage.Literals.INT_QNAME_UNION, initialValue);
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
    if (QnamePackage.Literals.INT_QNAME_UNION.isInstance(instanceValue))
    {
      try
      {
        String value = convertIntQNameUnionToString(QnamePackage.Literals.INT_QNAME_UNION, instanceValue);
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
  public QnamePackage getQnamePackage()
  {
    return (QnamePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static QnamePackage getPackage()
  {
    return QnamePackage.eINSTANCE;
  }

} //QnameFactoryImpl
