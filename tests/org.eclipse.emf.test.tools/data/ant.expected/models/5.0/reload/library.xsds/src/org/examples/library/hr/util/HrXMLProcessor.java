/**
 * This is my code.
 *
 * $Id: HrXMLProcessor.java,v 1.2 2007/04/26 20:57:13 emerks Exp $
 */
package org.examples.library.hr.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.examples.library.hr.HrPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class HrXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HrXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    HrPackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the HrResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new HrResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new HrResourceFactoryImpl());
    }
    return registrations;
  }

} //HrXMLProcessor
