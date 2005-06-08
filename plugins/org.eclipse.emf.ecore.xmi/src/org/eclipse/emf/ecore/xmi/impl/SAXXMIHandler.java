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
 * $Id: SAXXMIHandler.java,v 1.4 2005/06/08 06:16:07 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;


/**
 * This class is a SAX handler for creating MOF2 objects from an XMI 2.0 file.
 */
public class SAXXMIHandler extends XMIHandler
{
  protected Locator locator;
  protected Attributes attribs;

  /**
   * Constructor.
   */
  public SAXXMIHandler(XMLResource xmiResource, XMLHelper helper, Map options)
  {
    super(xmiResource, helper, options);
  }

  protected Object setAttributes(Object attributes)
  {
    Object oldAttribs = attribs;
    this.attribs = (Attributes)attributes;
    return oldAttribs;
  }

  public void setLocator(Object locator)
  {
    this.locator = (Locator) locator;
  }

  protected int getLineNumber()
  {
    if (locator != null)
    {
      return locator.getLineNumber();
    }
    else
    {
      return super.getLineNumber();
    }
  }

  protected int getColumnNumber()
  {
    if (locator != null)
    {
      return locator.getColumnNumber();
    }
    else
    {
      return super.getColumnNumber();
    }
  }

  /**
   * Returns true if the xsi:null attribute is in the list of attributes.
   */
  protected boolean isNull()
  {
    return attribs.getValue(NIL_ATTRIB) != null;
  }


  /**
   * Handle the XML namespace attributes.
   */
  protected void handleNamespaceAttribs()
  {
    for (int i = 0, size = attribs.getLength(); i < size; ++i)
    {
      String attrib = attribs.getQName(i);
      if (attrib.startsWith(XMLResource.XML_NS))
      {
        handleXMLNSAttribute(attrib, attribs.getValue(i));
      }
      else if (SCHEMA_LOCATION_ATTRIB.equals(attrib))
      {
        handleXSISchemaLocation(attribs.getValue(i));
      }
      else if (NO_NAMESPACE_SCHEMA_LOCATION_ATTRIB.equals(attrib))
      {
        handleXSINoNamespaceSchemaLocation(attribs.getValue(i));
      }
    }
  }

  protected String getXSIType()
  {
    String xsiType = attribs.getValue(TYPE_ATTRIB);

    if (xsiType == null)
    {
      xsiType = attribs.getValue(XMI_TYPE_ATTRIB);
    }

    return xsiType;
  }

  /**
   * Process the XMI attributes for the newly created object.
   */
  protected void handleObjectAttribs(EObject obj)
  {
    if (attribs != null)
    {
      InternalEObject internalEObject = (InternalEObject)obj;
      for (int i = 0, size = attribs.getLength(); i < size; ++i)
      {
        String name = attribs.getQName(i);
        if (name.equals(ID_ATTRIB))
        {
          xmlResource.setID(internalEObject, attribs.getValue(i));
        }
        else if (name.equals(hrefAttribute) && (!recordUnknownFeature || types.peek() != UNKNOWN_FEATURE_TYPE))
        {
          handleProxy(internalEObject, attribs.getValue(i));
        }
        else if (!name.startsWith(XMLResource.XML_NS) && !notFeatures.contains(name))
        {
          setAttribValue(obj, name, attribs.getValue(i));
        }
      }
    }
  }
} // SAXXMIHandler
