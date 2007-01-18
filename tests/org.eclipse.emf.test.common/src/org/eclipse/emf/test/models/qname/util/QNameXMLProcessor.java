/**
 * <copyright>
 * </copyright>
 *
 * $Id: QNameXMLProcessor.java,v 1.1 2007/01/18 15:50:24 marcelop Exp $
 */
package org.eclipse.emf.test.models.qname.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.models.qname.QNamePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class QNameXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QNameXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    QNamePackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the QNameResourceFactoryImpl factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected Map<String, Resource.Factory> getRegistrations()
  {
    if (registrations == null)
    {
      super.getRegistrations();
      registrations.put(XML_EXTENSION, new QNameResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new QNameResourceFactoryImpl());
    }
    return registrations;
  }

} //QNameXMLProcessor
