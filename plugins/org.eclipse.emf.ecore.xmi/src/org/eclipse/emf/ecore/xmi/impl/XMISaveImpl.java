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
 * $Id: XMISaveImpl.java,v 1.2 2004/03/15 15:00:52 marcelop Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;


public class XMISaveImpl extends XMLSaveImpl
{
  protected boolean xmiType;

  protected static final String XMI_ID_NS =   XMIResource.XMI_NS + ":" + XMIResource.XMI_ID;       // xmi:id
  protected static final String XMI_TAG_NS  = XMIResource.XMI_NS + ":" + XMIResource.XMI_TAG_NAME; // xmi:XMI
  protected static final String XMI_TYPE_NS = XMIResource.XMI_NS + ":" + XMLResource.TYPE;         // xmi:type
  protected static final String XMI_VER_NS =  XMIResource.XMI_NS + ":" + XMIResource.VERSION_NAME; // xmi:version
  protected static final String XMI_XMLNS =   XMLResource.XML_NS + ":" + XMIResource.XMI_NS;       // xmlns:xmi

  public XMISaveImpl(XMLHelper helper)
  {
    super(helper);
    idAttributeName = XMI_ID_NS;
  }

  public XMISaveImpl(Map options, XMLHelper helper, String encoding)
  {
    super(options, helper, encoding);
    this.xmiType = Boolean.TRUE.equals(options.get(XMIResource.OPTION_USE_XMI_TYPE));
    idAttributeName = XMI_ID_NS;
  }

  protected void init(XMLResource resource, Map options)
  {
    super.init(resource, options);
    this.xmiType = Boolean.TRUE.equals(options.get(XMIResource.OPTION_USE_XMI_TYPE));
  }

  public Object writeTopObjects(List contents)
  {
    doc.startElement(XMI_TAG_NS);
    Object mark = doc.mark();

    for (int i = 0, size = contents.size(); i < size; i++)
    {
      EObject top = (EObject)contents.get(i);
      EClass eClass = top.eClass();
      String name = helper.getQName(eClass);
      doc.startElement(name);
      saveElementID(top);
    }

    doc.endElement();
    return mark;
  }

  protected void saveTypeAttribute(EClass eClass)
  {
    if (xmiType)
    {
      doc.addAttribute(XMI_TYPE_NS, helper.getQName(eClass));
    }
    else
    {
      super.saveTypeAttribute(eClass);
    }
  }

  public void addNamespaceDeclarations()
  {
    doc.addAttribute(XMI_VER_NS, XMIResource.VERSION_VALUE);
    doc.addAttribute(XMI_XMLNS, XMIResource.XMI_URI);
    super.addNamespaceDeclarations();
  }
}
