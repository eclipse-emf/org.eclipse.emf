/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTypeResourceFactoryImpl.java,v 1.1 2007/06/12 21:15:30 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.type.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.ecore.xml.type.util.XMLTypeResourceImpl
 * @generated
 */
public class XMLTypeResourceFactoryImpl extends ResourceFactoryImpl
{
  /**
   * Creates an instance of the resource factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLTypeResourceFactoryImpl()
  {
    super();
  }

  /**
   * Creates an instance of the resource.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Resource createResource(URI uri)
  {
    Resource result = new XMLTypeResourceImpl(uri);
    return result;
  }

} //XMLTypeResourceFactoryImpl
