/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: EMOFHandler.java,v 1.3 2005/06/08 06:16:07 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;


public class EMOFHandler extends SAXXMIHandler
{
  protected EMOFHandler.Helper emofHelper;
  protected static final String ECORE_EXTENSION_TYPE = "ecoreExtension";

  public EMOFHandler(XMLResource xmiResource, EMOFHandler.Helper helper, Map options)
  {
    super(xmiResource, helper, options);
    emofHelper = helper;
  }

  protected void handleProxy(InternalEObject proxy, String uriLiteral)
  {
    if (uriLiteral.startsWith(EMOFExtendedMetaData.MAPPED_EMOF_EDATATYPE_HREF_PREFIX))
    {
      String dataType = uriLiteral.substring(EMOFExtendedMetaData.MAPPED_EMOF_EDATATYPE_HREF_PREFIX.length());
      for (int i = 0; i < EMOFExtendedMetaData.MAPPED_EMOF_EDATATYPES.length; i++)
      {
        if (dataType.equals(EMOFExtendedMetaData.MAPPED_EMOF_EDATATYPES[i]))
        {
          uriLiteral = EMOFExtendedMetaData.ECORE_EDATATYPE_HREF_PREFIX + EMOFExtendedMetaData.MAPPED_ECORE_EDATATYPES[i];
          break;
        }
      }
    }
    else if (uriLiteral.startsWith(EMOFExtendedMetaData.UNMAPPED_EMOF_EDATATYPE_HREF_PREFIX))
    {
      String dataType = uriLiteral.substring(EMOFExtendedMetaData.UNMAPPED_EMOF_EDATATYPE_HREF_PREFIX.length());
      uriLiteral = EMOFExtendedMetaData.ECORE_EDATATYPE_HREF_PREFIX + dataType;
    }

    super.handleProxy(proxy, uriLiteral);
  }

  protected void handleForwardReferences(boolean isEndDocument)
  {
    super.handleForwardReferences(isEndDocument);

    if (isEndDocument)
    {
      emofHelper.convertPropertyFeatures();
    }
  }

  protected void processElement(String name, String prefix, String localName)
  {
    if (EMOFExtendedMetaData.EXTENSION.equals(localName) && XMIResource.XMI_URI.equals(helper.getURI(prefix)))
    {
      if (attribs != null && EcorePackage.eNS_URI.equals(attribs.getValue(EMOFExtendedMetaData.XMI_EXTENDER_ATTRIBUTE)))
      {
        types.push(ECORE_EXTENSION_TYPE);
      }
      else
      {
        types.push(ERROR_TYPE);
      }
    }
    else
    {
      super.processElement(name, prefix, localName);
    }
  }

  public void endElement(String uri, String localName, String name)
  {
    if (types.peek() == ECORE_EXTENSION_TYPE)
    {
      elements.pop();
      types.pop();
      helper.popContext();
      mixedTargets.pop();
    }
    else
    {
      super.endElement(uri, localName, name);
    }
  }

  protected void setAttribValue(EObject object, String name, String value)
  {
    if (object instanceof EAnnotation)
    {
      EAnnotation annotation = (EAnnotation)object;
      if (EMOFExtendedMetaData.EMOF_PACKAGE_NS_URI.equals(annotation.getSource()))
      {
        if (EMOFExtendedMetaData.EMOF_TAG_NAME.equals(name) || EMOFExtendedMetaData.EMOF_TAG_VALUE.equals(name))
        {
          annotation.getDetails().put(name, value);
          return;
        }
      }
    }
    super.setAttribValue(object, name, value);
  }

  public static interface Helper extends XMLHelper
  {
    void convertPropertyFeatures();
  }
}
