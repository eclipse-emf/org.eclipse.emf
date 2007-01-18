/**
 * <copyright>
 * </copyright>
 *
 * $Id: DBItemXMLProcessor.java,v 1.1 2007/01/18 15:50:23 marcelop Exp $
 */
package org.eclipse.emf.test.models.dbitem.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.models.dbitem.DBItemPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DBItemXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DBItemXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    DBItemPackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the DBItemResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new DBItemResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new DBItemResourceFactoryImpl());
    }
    return registrations;
  }

} //DBItemXMLProcessor
