/**
 * <copyright>
 * 
 * Copyright (c) 2004-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Ecore2EcoreResourceFactoryImpl.java,v 1.3 2005/05/06 15:03:21 khussey Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.mapping.ecore2ecore.util.Ecore2EcoreResourceImpl
 * @generated
 */
public class Ecore2EcoreResourceFactoryImpl extends ResourceFactoryImpl
{
  /**
   * Creates an instance of the resource factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Ecore2EcoreResourceFactoryImpl()
  {
    super();
  }

  /**
   * Creates an instance of the resource.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Resource createResource(URI uri)
  {
    XMIResource result = new Ecore2EcoreResourceImpl(uri);
    result.setEncoding("UTF-8");
    return result;
  }

} //Ecore2EcoreResourceFactoryImpl
