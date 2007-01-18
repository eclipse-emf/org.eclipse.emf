/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimpleXMLProcessor.java,v 1.1 2007/01/18 15:50:23 marcelop Exp $
 */
package org.eclipse.emf.test.models.sdo.simple.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.models.sdo.simple.impl.SimplePackageImpl;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SimpleXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    SimplePackageImpl.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the SimpleResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new SimpleResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new SimpleResourceFactoryImpl());
    }
    return registrations;
  }

} //SimpleXMLProcessor
