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
 * $Id: Ecore2XMLRegistryImpl.java,v 1.1 2005/03/18 21:02:01 khussey Exp $
 */
package org.eclipse.emf.mapping.ecore2xml.impl;

import java.util.HashMap;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLRegistry;

/**
 *  
 */
public class Ecore2XMLRegistryImpl extends HashMap implements Ecore2XMLRegistry
{

  protected Ecore2XMLRegistry delegateRegistry;

  public Ecore2XMLRegistryImpl()
  {
    super();
  }

  public Ecore2XMLRegistryImpl(Ecore2XMLRegistry delegateRegistry)
  {
    this();

    this.delegateRegistry = delegateRegistry;
  }

  public XMLResource.XMLMap getXMLMap(String nsURI)
  {
    Object value = get(nsURI);

    if (value instanceof XMLResource.XMLMap)
    {
      return (XMLResource.XMLMap)value;
    }
    else if (value instanceof Ecore2XMLRegistry.Descriptor)
    {
      XMLResource.XMLMap xmlMap = ((Ecore2XMLRegistry.Descriptor)value).getXMLMap();
      put(nsURI, xmlMap);

      return xmlMap;
    }
    else
    {
      return delegatedGetXMLMap(nsURI);
    }
  }

  protected XMLResource.XMLMap delegatedGetXMLMap(String nsURI)
  {
    return delegateRegistry == null ? null : delegateRegistry.getXMLMap(nsURI);
  }

  public boolean containsKey(Object key)
  {
    return super.containsKey(key) || delegateRegistry != null && delegateRegistry.containsKey(key);
  }

}