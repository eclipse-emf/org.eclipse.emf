/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XSD2EcoreResourceFactoryImpl.java,v 1.2 2004/03/15 15:00:56 marcelop Exp $
 */
package org.eclipse.emf.mapping.xsd2ecore.util;


import java.util.Locale;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;


/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.mapping.xsd2ecore.util.XSD2EcoreResourceImpl
 * @generated
 */
public class XSD2EcoreResourceFactoryImpl extends ResourceFactoryImpl
{
  protected static final boolean USE_ASCII = Locale.getDefault().getLanguage().equals("en");

  /**
   * Creates an instance of the resource factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSD2EcoreResourceFactoryImpl()
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
    XSD2EcoreResourceImpl result = new XSD2EcoreResourceImpl(uri);

    if (!USE_ASCII)
    {
      result.setEncoding("UTF-8");
    }

    result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    result.getDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, new Integer(80));

    return result;
  }
} //XSD2EcoreResourceFactoryImpl
