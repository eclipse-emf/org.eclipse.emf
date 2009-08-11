/**
 * This is my code.
 */
package org.examples.library.elements.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.examples.library.elements.ElementsPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ElementsXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementsXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    ElementsPackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the ElementsResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new ElementsResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new ElementsResourceFactoryImpl());
    }
    return registrations;
  }

} //ElementsXMLProcessor
