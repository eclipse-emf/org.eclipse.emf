/**
 * <copyright>
 * </copyright>
 *
 * $Id: DbpriceResourceFactoryImpl.java,v 1.1.2.1 2005/01/14 22:56:18 nickb Exp $
 */
package org.eclipse.emf.test.models.dbprice.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.util.ExtendedMetaData;

import org.eclipse.emf.ecore.xmi.XMLResource;

import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.dbprice.util.DbpriceResourceImpl
 * @generated
 */
public class DbpriceResourceFactoryImpl extends XMLResourceFactoryImpl
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExtendedMetaData extendedMetaData;

  /**
   * Creates an instance of the resource factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbpriceResourceFactoryImpl()
  {
    super();
    extendedMetaData = ExtendedMetaData.INSTANCE;
  }

  /**
   * Creates an instance of the resource.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Resource createResource(URI uri)
  {
    XMLResource result = new DbpriceResourceImpl(uri);
    result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
    result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

    result.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);

    result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
    return result;
  }
} //DbpriceResourceFactoryImpl
