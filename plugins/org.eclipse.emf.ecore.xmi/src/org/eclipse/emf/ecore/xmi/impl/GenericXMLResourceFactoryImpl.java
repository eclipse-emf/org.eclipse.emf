/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: GenericXMLResourceFactoryImpl.java,v 1.1 2005/06/10 20:29:11 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

public class GenericXMLResourceFactoryImpl extends XMLResourceFactoryImpl
{
  public GenericXMLResourceFactoryImpl()
  {
    super();
  }

  public Resource createResource(URI uri)
  {
    XMLResource result = new GenericXMLResourceImpl(uri);
    
    result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
    result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

    result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);

    result.getDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, new Integer(80));

    result.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    
    return result;
  }
}