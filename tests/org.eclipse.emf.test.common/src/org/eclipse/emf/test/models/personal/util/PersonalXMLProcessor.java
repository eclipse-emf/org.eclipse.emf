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
 * $Id: PersonalXMLProcessor.java,v 1.2 2007/01/18 22:06:38 marcelop Exp $
 */
package org.eclipse.emf.test.models.personal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PersonalXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersonalXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    PersonalPackageImpl.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the PersonalResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new PersonalResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new PersonalResourceFactoryImpl());
    }
    return registrations;
  }

} //PersonalXMLProcessor
