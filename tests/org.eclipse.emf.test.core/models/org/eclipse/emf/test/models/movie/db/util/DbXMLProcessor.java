/**
 * <copyright>
 * </copyright>
 *
 * $Id: DbXMLProcessor.java,v 1.1 2006/12/29 21:49:52 marcelop Exp $
 */
package org.eclipse.emf.test.models.movie.db.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.models.movie.db.DbPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DbXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    DbPackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the DbResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new DbResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new DbResourceFactoryImpl());
    }
    return registrations;
  }

} //DbXMLProcessor
