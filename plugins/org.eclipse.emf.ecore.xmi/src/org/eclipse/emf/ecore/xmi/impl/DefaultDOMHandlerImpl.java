/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: DefaultDOMHandlerImpl.java,v 1.1 2004/12/23 19:32:59 elena Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.DOMHandler;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;


/**
 * Implementation of the default {@link DOMHandler}.
 * @since 2.1.0
 */
public class DefaultDOMHandlerImpl implements DOMHandler
{
  protected final HashMap nodeToEObject = new HashMap();

  protected final HashMap nodeToContainer = new HashMap();

  protected final HashMap nodeToFeature = new HashMap();
  
  protected ExtendedMetaData extendedMetaData;
    
  void setExtendedMetaData(ExtendedMetaData extendedMetaData)
  {
    this.extendedMetaData = extendedMetaData;
  }

  public EObject getContainer(Node node)
  {
    short type = node.getNodeType();
    switch (type)
    {
      case Node.ELEMENT_NODE:
        return (EObject)nodeToContainer.get(node);
      case Node.TEXT_NODE:
      case Node.CDATA_SECTION_NODE:
        return (EObject)nodeToContainer.get(node.getParentNode());
      case Node.ATTRIBUTE_NODE:
        return (EObject)nodeToEObject.get(((Attr)node).getOwnerElement());
      default:
        return null;
    }
  }

  public EStructuralFeature getEStructuralFeature(Node node)
  {
    short type = node.getNodeType();
    switch (type)
    {
      case Node.ELEMENT_NODE:
        return (EStructuralFeature)nodeToFeature.get(node);
      case Node.TEXT_NODE:
      case Node.CDATA_SECTION_NODE:
        return (EStructuralFeature)nodeToFeature.get(node.getParentNode());
      case Node.ATTRIBUTE_NODE: 
      {
        EObject obj = (EObject)nodeToEObject.get(((Attr)node).getOwnerElement());
        if (extendedMetaData == null)
        {
          return obj.eClass().getEStructuralFeature(node.getLocalName());
        }
        else
        {
          return extendedMetaData.getAttribute(obj.eClass(), node.getNamespaceURI(), node.getLocalName());
        }
      }
      default:
        return null;
    }
  }

  public EObject getEObject(Node node)
  {
    return (EObject)nodeToEObject.get(node);
  }

  public void recordEObject(Node node, EObject object, EObject container, EStructuralFeature feature)
  {
    if (object != null)
    {
      nodeToEObject.put(node, object);
    }
    nodeToContainer.put(node, container);
    nodeToFeature.put(node, feature);
  }
}
