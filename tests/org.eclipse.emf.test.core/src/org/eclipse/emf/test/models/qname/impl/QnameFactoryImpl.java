/**
 * <copyright>
 * </copyright>
 *
 * $Id: QnameFactoryImpl.java,v 1.1 2004/06/16 18:01:17 elena Exp $
 */
package org.eclipse.emf.test.models.qname.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

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
   * Creates and instance of the factory.
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
    try
    {
      Object result = (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInt(), initialValue);
      if (result != null)
      {
        return result;
      }
    }
    catch (RuntimeException exception)
    {
    }
    return (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getQName(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIntQNameUnionToString(EDataType eDataType, Object instanceValue)
  {
    if (XMLTypePackage.eINSTANCE.getInt().isInstance(instanceValue))
    {
      return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInt(), instanceValue);
    }
    if (XMLTypePackage.eINSTANCE.getQName().isInstance(instanceValue))
    {
      return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getQName(), instanceValue);
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List createListUnionFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    List result = new ArrayList();
    for (StringTokenizer stringTokenizer = new StringTokenizer(initialValue); stringTokenizer.hasMoreTokens(); )
    {
      String item = stringTokenizer.nextToken();
      result.add(QnameFactory.eINSTANCE.createFromString(QnamePackage.eINSTANCE.getUnion(), item));
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
    List list = (List)instanceValue;
    if (list.isEmpty()) return "";
    StringBuffer result = new StringBuffer();
    for (Iterator i = list.iterator(); i.hasNext(); )
    {
      result.append(QnameFactory.eINSTANCE.convertToString(QnamePackage.eINSTANCE.getUnion(), i.next()));
      result.append(' ');
    }
    return result.substring(0, result.length() - 1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List createQnameListFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    List result = new ArrayList();
    for (StringTokenizer stringTokenizer = new StringTokenizer(initialValue); stringTokenizer.hasMoreTokens(); )
    {
      String item = stringTokenizer.nextToken();
      result.add(XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getQName(), item));
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
    List list = (List)instanceValue;
    if (list.isEmpty()) return "";
    StringBuffer result = new StringBuffer();
    for (Iterator i = list.iterator(); i.hasNext(); )
    {
      result.append(XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getQName(), i.next()));
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
    try
    {
      Object result = (Object)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getBoolean(), initialValue);
      if (result != null)
      {
        return result;
      }
    }
    catch (RuntimeException exception)
    {
    }
    return (Object)QnameFactory.eINSTANCE.createFromString(QnamePackage.eINSTANCE.getIntQNameUnion(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUnionToString(EDataType eDataType, Object instanceValue)
  {
    if (XMLTypePackage.eINSTANCE.getBoolean().isInstance(instanceValue))
    {
      return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getBoolean(), instanceValue);
    }
    if (QnamePackage.eINSTANCE.getIntQNameUnion().isInstance(instanceValue))
    {
      return QnameFactory.eINSTANCE.convertToString(QnamePackage.eINSTANCE.getIntQNameUnion(), instanceValue);
    }
    return null;
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
  public static QnamePackage getPackage()
  {
    return QnamePackage.eINSTANCE;
  }

} //QnameFactoryImpl
