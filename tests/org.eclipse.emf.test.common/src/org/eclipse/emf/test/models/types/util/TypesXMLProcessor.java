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
 * $Id: TypesXMLProcessor.java,v 1.2 2007/01/18 22:06:48 marcelop Exp $
 */
package org.eclipse.emf.test.models.types.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.models.types.impl.TypesPackageImpl;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TypesXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypesXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    TypesPackageImpl.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the TypesResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new TypesResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new TypesResourceFactoryImpl());
    }
    return registrations;
  }

} //TypesXMLProcessor
