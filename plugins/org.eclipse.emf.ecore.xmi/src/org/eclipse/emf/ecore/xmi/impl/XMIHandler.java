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
 * $Id: XMIHandler.java,v 1.14 2007/09/29 19:03:55 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


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
  public XMIHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options)
  {
    super(xmiResource, helper, options);

    hrefAttribute = XMLResource.HREF;
    notFeatures.add(VERSION_ATTRIB);
    notFeatures.add(XMI_TYPE_ATTRIB);
    notFeatures.add(UUID_ATTRIB);
  }

  @Override
  protected void processElement(String name, String prefix, String localName)
  {
    if (localName.equals(XMIResource.XMI_TAG_NAME))
    {
      types.push(XMI_ELEMENT_TYPE); 
      String namespace = helper.getURI(XMIResource.XMI_NS);
      if (namespace != null && namespace.startsWith(XMIResource.XMI_NAMESPACE_PREFIX))
      {
        ((XMIResource)xmlResource).setXMIVersion(namespace.substring(XMIResource.XMI_NAMESPACE_PREFIX.length()));
      }  
      isRoot = false;
    }
    else if (isRoot)
    {   
      String namespace = helper.getURI(XMIResource.XMI_NS);
      if (namespace != null && namespace.startsWith(XMIResource.XMI_NAMESPACE_PREFIX))
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

  @Override
  protected boolean isTextFeatureValue(Object type)
  {
    return super.isTextFeatureValue(type) && type != XMI_ELEMENT_TYPE;
  }

  @Override
  protected EObject createDocumentRoot(String prefix, String uri, String name, EFactory eFactory, boolean top)
  {
    if (extendedMetaData != null && eFactory != null && extendedMetaData.demandedPackages().contains(eFactory.getEPackage()))
    {
      EClass eClass = (EClass)extendedMetaData.demandType(uri, name);
      @SuppressWarnings("deprecation") EObject newObject = useNewMethods ? createObject(eFactory, eClass, true) : helper.createObject(eFactory, name);          
      validateCreateObjectFromFactory(eFactory, name, newObject);
      handleObjectAttribs(newObject);
      if (top)
      {
        processTopObject(newObject);
      }
      return newObject;
    }
    else
    {
      return super.createDocumentRoot(prefix, uri, name, eFactory, top);
    }
  }
  
  @Override
  protected void createObject(EObject peekObject, EStructuralFeature feature)
  {
    String id = attribs.getValue("xmi:idref");
    if (id != null)
    {
      setValueFromId(peekObject, (EReference)feature, id);
      objects.push(null);
      mixedTargets.push(null);
      types.push(OBJECT_TYPE);
    }
    else
    {
      super.createObject(peekObject, feature);
    }
  }

  @Override
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

  @Override
  public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
  {
    if (documentRoot != null)
    {
      EObject eObject = objects.peekEObject();
      if (eObject == documentRoot && (extendedMetaData == null || extendedMetaData.isDocumentRoot(eObject.eClass())))
      {
        types.pop();
        objects.pop();
        mixedTargets.pop();
        documentRoot= null;
      }
    }
    super.startElement(uri, localName, name, attributes);
  }
} // XMIHandler
