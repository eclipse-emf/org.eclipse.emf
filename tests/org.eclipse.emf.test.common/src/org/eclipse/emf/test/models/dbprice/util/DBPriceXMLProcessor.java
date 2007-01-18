/**
 * <copyright>
 * </copyright>
 *
 * $Id: DBPriceXMLProcessor.java,v 1.1 2007/01/18 15:50:23 marcelop Exp $
 */
package org.eclipse.emf.test.models.dbprice.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.models.dbprice.DBPricePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DBPriceXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DBPriceXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    DBPricePackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the DBPriceResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new DBPriceResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new DBPriceResourceFactoryImpl());
    }
    return registrations;
  }

} //DBPriceXMLProcessor
