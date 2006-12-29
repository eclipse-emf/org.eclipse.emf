/**
 * <copyright>
 * </copyright>
 *
 * $Id: CustomerXMLProcessor.java,v 1.1 2006/12/29 21:49:52 marcelop Exp $
 */
package org.eclipse.emf.test.models.customer.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.models.customer.CustomerPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CustomerXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    CustomerPackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the CustomerResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new CustomerResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new CustomerResourceFactoryImpl());
    }
    return registrations;
  }

} //CustomerXMLProcessor
