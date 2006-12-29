/**
 * <copyright>
 * </copyright>
 *
 * $Id: SupplierXMLProcessor.java,v 1.1 2006/12/29 21:49:52 marcelop Exp $
 */
package org.eclipse.emf.test.core.featuremap.supplier.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.core.featuremap.supplier.SupplierPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SupplierXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SupplierXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    SupplierPackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the SupplierResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new SupplierResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new SupplierResourceFactoryImpl());
    }
    return registrations;
  }

} //SupplierXMLProcessor
