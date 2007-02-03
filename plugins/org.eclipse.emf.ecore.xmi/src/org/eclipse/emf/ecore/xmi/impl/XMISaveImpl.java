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
 * $Id: XMISaveImpl.java,v 1.15 2007/02/03 18:26:59 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.w3c.dom.Element;


public class XMISaveImpl extends XMLSaveImpl
{
  protected boolean xmiType;
  protected String xmiURI = XMIResource.XMI_URI;

  protected static final String XMI_ID_NS = XMIResource.XMI_NS + ":" + XMIResource.XMI_ID; // xmi:id
  protected static final String XMI_TAG_NS = XMIResource.XMI_NS + ":" + XMIResource.XMI_TAG_NAME; // xmi:XMI
  protected static final String XMI_TYPE_NS = XMIResource.XMI_NS + ":" + XMLResource.TYPE; // xmi:type
  protected static final String XMI_VER_NS = XMIResource.XMI_NS + ":" + XMIResource.VERSION_NAME; // xmi:version
  protected static final String XMI_XMLNS = XMLResource.XML_NS + ":" + XMIResource.XMI_NS; // xmlns:xmi

  public XMISaveImpl(XMLHelper helper)
  {
    super(helper);
    idAttributeName = XMI_ID_NS;
    idAttributeNS = XMIResource.XMI_NS;
  }

  public XMISaveImpl(Map<?, ?> options, XMLHelper helper, String encoding)
  {
    this(options, helper, encoding, "1.0");
  }

  public XMISaveImpl(Map<?, ?> options, XMLHelper helper, String encoding, String xmlVersion)
  {
    super(options, helper, encoding, xmlVersion);
    this.xmiType = Boolean.TRUE.equals(options.get(XMIResource.OPTION_USE_XMI_TYPE));
    idAttributeName = XMI_ID_NS;
    idAttributeNS = XMIResource.XMI_NS;
  }

  @Override
  protected void init(XMLResource resource, Map<?, ?> options)
  {
    super.init(resource, options);
    this.xmiType = Boolean.TRUE.equals(options.get(XMIResource.OPTION_USE_XMI_TYPE));
    xmiURI = xmlResource == null ? XMIResource.XMI_URI : ((XMIResource)xmlResource).getXMINamespace();
    helper.getPrefixToNamespaceMap().put(XMIResource.XMI_NS, xmiURI);
  }

  @Override
  public Object writeTopObjects(List<? extends EObject> contents)
  {
    if (!toDOM)
    {
      doc.startElement(XMI_TAG_NS);
      Object mark = doc.mark();

      for (int i = 0, size = contents.size(); i < size; i++)
      {
        EObject top = contents.get(i);
        EClass eClass = top.eClass();
        if (extendedMetaData == null || featureTable.getDocumentRoot(eClass.getEPackage()) != eClass)
        {
          String name = helper.getQName(eClass);
          doc.startElement(name);
          root = top;
          saveElementID(top);
        }
        else
        {
          doc.startElement(null);
          root = top;
          saveFeatures(top);
          doc.addLine();
        }
      }

      doc.endElement();
      return mark;
    }
    else
    {
      // create dummy documentElement
      currentNode = document.createElementNS(XMIResource.XMI_URI, XMI_TAG_NS);
      document.appendChild(currentNode);
      for (int i = 0, size = contents.size(); i < size; i++)
      {
        EObject top = contents.get(i);
        EClass eClass = top.eClass();
        helper.populateNameInfo(nameInfo, eClass);
        if (extendedMetaData == null || extendedMetaData.getDocumentRoot(eClass.getEPackage()) != eClass)
        {
          currentNode = currentNode.appendChild(document.createElementNS(nameInfo.getNamespaceURI(), nameInfo.getQualifiedName()));
          handler.recordValues(currentNode, null, null, top);
          root = top;
          saveElementID(top);
          return null;
        }
        else
        {
          root = top;
          currentNode = currentNode.appendChild(document.createElementNS(nameInfo.getNamespaceURI(), nameInfo.getQualifiedName()));
          saveFeatures(top);
          return null;
        }
      }
      return null;
    }
  }

  @Override
  protected void saveTypeAttribute(EClass eClass)
  {
    if (xmiType)
    {
      if (!toDOM)
      {
        doc.addAttribute(XMI_TYPE_NS, helper.getQName(eClass));
      }
      else
      {
        ((Element)currentNode).setAttributeNS(XMIResource.XMI_URI, XMI_TYPE_NS, helper.getQName(eClass));
      }
    }
    else
    {
      super.saveTypeAttribute(eClass);
    }
  }

  @Override
  public void addNamespaceDeclarations()
  {
    String version = XMIResource.VERSION_VALUE;
    if (xmlResource != null)
    {
      version = ((XMIResource)xmlResource).getXMIVersion();
    }
    if (!toDOM)
    {
        doc.addAttribute(XMI_VER_NS, version);
        doc.addAttribute(XMI_XMLNS, xmiURI);         
    }
    else
    {
      ((Element)currentNode).setAttributeNS(XMIResource.XMI_URI, XMI_VER_NS, version);
      ((Element)currentNode).setAttributeNS(ExtendedMetaData.XMLNS_URI, XMI_XMLNS, xmiURI);
    }
    super.addNamespaceDeclarations();
  }

  @Override
  public boolean isDuplicateURI(String nsURI)
  {
    return xmiURI.equals(nsURI);
  }

  @Override
  protected void saveFeatureMapElementReference(EObject o, EReference f)
  {
    if (extendedMetaData == null || extendedMetaData.getFeatureKind(f) != ExtendedMetaData.ELEMENT_FEATURE)
    {
      saveHref(o, f);
    }
    else
    {
      saveElementReference(o, f);
    } 
  }
}
