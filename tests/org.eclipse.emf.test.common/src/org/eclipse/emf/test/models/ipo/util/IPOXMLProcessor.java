/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: IPOXMLProcessor.java,v 1.2 2007/01/18 22:06:43 marcelop Exp $
 */
package org.eclipse.emf.test.models.ipo.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.models.ipo.impl.IPOPackageImpl;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IPOXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IPOXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    IPOPackageImpl.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the IPOResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new IPOResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new IPOResourceFactoryImpl());
    }
    return registrations;
  }

} //IPOXMLProcessor
