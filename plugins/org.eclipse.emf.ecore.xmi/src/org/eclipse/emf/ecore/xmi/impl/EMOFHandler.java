/**
 * <copyright>
 *
 * Copyright (c) 2003-2006 IBM Corporation and others.
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
 * $Id: EMOFHandler.java,v 1.6 2008/05/03 22:35:32 emerks Exp $
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

  public EMOFHandler(XMLResource xmiResource, EMOFHandler.Helper helper, Map<?, ?> options)
  {
    super(xmiResource, helper, options);
    emofHelper = helper;
  }

  @Override
  protected void handleProxy(InternalEObject proxy, String uriLiteral)
  {
    if (uriLiteral.startsWith(EMOFExtendedMetaData.MAPPED_EMOF_EDATATYPE_HREF_PREFIX) || uriLiteral.startsWith(EMOFExtendedMetaData.MAPPED_EMOF_EDATATYPE_HREF_PREFIX_2_0))
    {
      String dataType = uriLiteral.substring(uriLiteral.indexOf("#") + 1);
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

  @Override
  protected void handleForwardReferences(boolean isEndDocument)
  {
    super.handleForwardReferences(isEndDocument);

    if (isEndDocument)
    {
      emofHelper.convertPropertyFeatures();
    }
  }

  @Override
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

  @Override
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

  @Override
  protected void setAttribValue(EObject object, String name, String value)
  {
    if (object instanceof EAnnotation)
    {
      EAnnotation annotation = (EAnnotation)object;
      String source = annotation.getSource();
      if (EMOFExtendedMetaData.EMOF_PACKAGE_NS_URI.equals(source) || EMOFExtendedMetaData.EMOF_PACKAGE_NS_URI_2_0.equals(source))
      {
        if (EMOFExtendedMetaData.EMOF_TAG_NAME.equals(name) || EMOFExtendedMetaData.EMOF_TAG_VALUE.equals(name))
        {
          annotation.getDetails().put(name, value);
          return;
        }
        else if (EMOFExtendedMetaData.EMOF_COMMENT_BODY.equals(name))
        {
          annotation.setSource(EMOFExtendedMetaData.EMOF_COMMENT_ANNOTATION_SOURCE);
          annotation.getDetails().put(name, value);
          return;
        }
      }
      else if (source.startsWith(EMOFExtendedMetaData.EMOF_PACKAGE_NS_URI_2_0) && EMOFExtendedMetaData.EMOF_COMMENT_BODY.equals(name))
      {
        annotation.getDetails().put(name, value);
        return;
      }
    }
    super.setAttribValue(object, name, value);
  }

  @Override
  protected void handleFeature(String prefix, String name)
  {
    super.handleFeature(prefix, name);

    //  Interpret the body of a nested ownedComment as a comment-type and assign it as the EAnnotation.source of the parent.
    //
    if (EMOFExtendedMetaData.EMOF_OWNED_COMMENT.equals(name) && objects.peekEObject() instanceof EAnnotation) 
    {
      EAnnotation annotation = (EAnnotation)objects.peekEObject();
      EObject container = annotation.eContainer();
      if (container instanceof EAnnotation)
      {
        EAnnotation parentAnnotation = (EAnnotation)container;
        parentAnnotation.setSource(annotation.getDetails().get(EMOFExtendedMetaData.EMOF_COMMENT_BODY));
        parentAnnotation.getEAnnotations().remove(annotation);
      }
    }
  }

  public static interface Helper extends XMLHelper
  {
    void convertPropertyFeatures();
  }
}
