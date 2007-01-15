/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EcoreResourceFactoryImpl.java,v 1.7 2007/01/15 22:22:33 marcelop Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;


public class EcoreResourceFactoryImpl extends ResourceFactoryImpl
{
  /**
   * Constructor for EcoreResourceFactoryImpl.
   */
  public EcoreResourceFactoryImpl()
  {
    super();
  }

  @Override
  public Resource createResource(URI uri)
  {
    XMLResource result = 
      new XMIResourceImpl(uri)
      {
        @Override
        protected boolean useIDs()
        {
          return eObjectToIDMap != null || idToEObjectMap != null;
        }
      };
    result.setEncoding("UTF-8");

    result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    result.getDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, new Integer(80));
    result.getDefaultSaveOptions().put(XMLResource.OPTION_URI_HANDLER, new URIHandlerImpl.PlatformSchemeAware());
    return result;
  }
}
