/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderXMLProcessor.java,v 1.1 2007/01/18 15:50:16 marcelop Exp $
 */
package org.eclipse.emf.test.models.order.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.models.order.OrderPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OrderXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    OrderPackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the OrderResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new OrderResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new OrderResourceFactoryImpl());
    }
    return registrations;
  }

} //OrderXMLProcessor
