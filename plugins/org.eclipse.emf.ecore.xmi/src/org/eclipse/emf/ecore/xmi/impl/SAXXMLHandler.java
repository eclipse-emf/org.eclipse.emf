/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: SAXXMLHandler.java,v 1.12 2007/10/01 18:11:25 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLResource.XMLInfo;


/**
 * This class implements the XML deserializer which creates
 * EObjects from XML files.
 */
public class SAXXMLHandler extends XMLHandler
{

  /**
   * Constructor.
   */
  public SAXXMLHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options)
  {
    super(xmiResource, helper, options);
  }

  @Override
  protected String getXSIType()
  {
    return isNamespaceAware ? attribs.getValue(ExtendedMetaData.XSI_URI, XMLResource.TYPE) : attribs.getValue(TYPE_ATTRIB);
  }

  /**
   * Process the XML attributes for the newly created object.
   */
  @Override
  protected void handleObjectAttribs(EObject obj)
  {
    if (attribs != null)
    {
      InternalEObject internalEObject = (InternalEObject)obj;
      for (int i = 0, size = attribs.getLength(); i < size; ++i)
      {
        String name = attribs.getQName(i);
        if (name.equals(idAttribute))
        {
          xmlResource.setID(internalEObject, attribs.getValue(i));
        }
        else if (name.equals(hrefAttribute) && (!recordUnknownFeature || types.peek() != UNKNOWN_FEATURE_TYPE || obj.eClass() != anyType))
        {
          handleProxy(internalEObject, attribs.getValue(i));
        }
        else if (isNamespaceAware)
        {
          String namespace = attribs.getURI(i);
          if (!ExtendedMetaData.XSI_URI.equals(namespace))
          {
            setAttribValue(obj, name, attribs.getValue(i));
          }
        }
        else if (!name.startsWith(XMLResource.XML_NS) && !notFeatures.contains(name))
        {
          setAttribValue(obj, name, attribs.getValue(i));
        }
      }
    }
  }

  @Override
  protected void processObject(EObject object)
  {
    if (object != null)
    {
      EStructuralFeature valueFeature = getContentFeature(object);

      if (valueFeature != null)
      {
        text = new StringBuffer();
        objects.push(object);
        types.push(valueFeature);
        return;
      }
    }

    super.processObject(object);
  }

  protected EStructuralFeature getContentFeature(EObject object)
  {
    if (xmlMap != null)
    {
      List<EAttribute> eAttributes = object.eClass().getEAllAttributes();
      if (eAttributes.size() >= 1)
      {
        EAttribute eAttribute = eAttributes.get(0);
        XMLInfo info = xmlMap.getInfo(eAttribute);
        if (info != null && info.getXMLRepresentation() == XMLInfo.CONTENT)
        {
          return eAttribute;
        }
      }
    }

    return null;
  }
} // SAXXMLHandler
