/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XMIHandler.java,v 1.7 2005/12/05 20:49:02 elena Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;


/**
 * This class is a SAX handler for creating MOF2 objects from an XMI 2.0 file.
 */
public abstract class XMIHandler extends XMLHandler
{
  protected static final String XMI_ELEMENT_TYPE = "xmi";
  protected static final String XMI_UUID = "uuid";
  protected static final String XMI_EXTENSION = "Extension";

  protected final static String XMI_TYPE_ATTRIB = XMIResource.XMI_NS + ":" + XMLResource.TYPE;
  protected final static String ID_ATTRIB = XMIResource.XMI_NS + ":" + XMIResource.XMI_ID;
  protected final static String VERSION_ATTRIB = XMIResource.XMI_NS + ":" + XMIResource.VERSION_NAME;
  protected final static String UUID_ATTRIB = XMIResource.XMI_NS + ":" + XMI_UUID;
  protected final static String XMI_ELEMENT_NAME = XMIResource.XMI_NS + ":" + XMIResource.XMI_TAG_NAME;

  /**
   * Constructor.
   */
  public XMIHandler(XMLResource xmiResource, XMLHelper helper, Map options)
  {
    super(xmiResource, helper, options);

    hrefAttribute = XMLResource.HREF;
    notFeatures.add(VERSION_ATTRIB);
    notFeatures.add(XMI_TYPE_ATTRIB);
    notFeatures.add(UUID_ATTRIB);
  }

  protected void processElement(String name, String prefix, String localName)
  {
    if (localName.equals(XMIResource.XMI_TAG_NAME))
    {
      types.push(XMI_ELEMENT_TYPE); 
      String namespace = helper.getURI(XMIResource.XMI_NS);
      if (namespace.startsWith(XMIResource.XMI_NAMESPACE_PREFIX))
      {
        ((XMIResource)xmlResource).setXMIVersion(namespace.substring(XMIResource.XMI_NAMESPACE_PREFIX.length()));
      }  
      isRoot = false;
    }
    else if (isRoot)
    {   
      String namespace = helper.getURI(XMIResource.XMI_NS);
      if (namespace.startsWith(XMIResource.XMI_NAMESPACE_PREFIX))
      {
        ((XMIResource)xmlResource).setXMIVersion(namespace.substring(XMIResource.XMI_NAMESPACE_PREFIX.length()));
      }  
      super.processElement(name, prefix, localName);
    }
    else
    {
      super.processElement(name, prefix, localName);
    }
  }

  protected boolean isTextFeatureValue(Object type)
  {
    return super.isTextFeatureValue(type) && type != XMI_ELEMENT_TYPE;
  }

  protected void handleUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject, String value)
  {
    if (XMI_EXTENSION.equals(name) && XMIResource.XMI_URI.equals(helper.getURI(prefix)))
    {
      if (extendedMetaData == null)
      {
        setExtendedMetaDataOption(Boolean.TRUE);
      }

      recordUnknownFeature(prefix, name, isElement, peekObject, value);
    }
    else
    {
      super.handleUnknownFeature(prefix, name, isElement, peekObject, value);
    }
  }
} // XMIHandler
