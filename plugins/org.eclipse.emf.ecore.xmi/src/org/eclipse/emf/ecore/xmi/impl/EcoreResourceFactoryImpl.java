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
 * $Id: EcoreResourceFactoryImpl.java,v 1.2 2004/03/15 15:00:52 marcelop Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.Locale;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;


public class EcoreResourceFactoryImpl extends ResourceFactoryImpl
{
  protected static final boolean USE_ASCII = Locale.getDefault().getLanguage().equals("en");

  /**
   * Constructor for EcoreResourceFactoryImpl.
   */
  public EcoreResourceFactoryImpl()
  {
    super();
  }

  public Resource createResource(URI uri)
  {
    XMLResourceImpl result = new XMIResourceImpl(uri);
    if (!USE_ASCII)
    {
      result.setEncoding("UTF-8");
    }

    result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    result.getDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, new Integer(80));
    return result;
  }
}
