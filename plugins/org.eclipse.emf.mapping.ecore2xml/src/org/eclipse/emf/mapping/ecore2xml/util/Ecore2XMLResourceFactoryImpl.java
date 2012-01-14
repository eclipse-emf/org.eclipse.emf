/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.ecore2xml.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * @extends Ecore2XMLResource.Factory
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.mapping.ecore2xml.util.Ecore2XMLResourceImpl
 * @generated
 */
public class Ecore2XMLResourceFactoryImpl extends ResourceFactoryImpl implements Ecore2XMLResource.Factory
{
  
  /**
   * Creates an instance of the resource factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Ecore2XMLResourceFactoryImpl()
  {
    super();
  }

  /**
   * Creates an instance of the resource.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public Resource createResource(URI uri)
  {
    Ecore2XMLResource result = new Ecore2XMLResourceImpl(uri);
    
    result.setEncoding(Ecore2XMLResource.DEFAULT_ENCODING);
    
    return result;
  }
  
} //Ecore2XMLResourceFactoryImpl
