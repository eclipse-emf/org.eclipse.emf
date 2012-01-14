/**
 * Copyright (c) 2004-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.DOMHandler;
import org.eclipse.emf.ecore.xmi.DOMHelper;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;


/**
 * Implementation of the default {@link DOMHandler} and {@link DOMHelper}
 * @since 2.1.0
 */
public class DefaultDOMHandlerImpl implements DOMHandler, DOMHelper
{
  /** store node to actual value mapping */
  protected final HashMap<Node, Object> nodeToObject = new HashMap<Node, Object>();

  /** store node to containment feature mapping */
  protected final HashMap<Node, EStructuralFeature> nodeToFeature = new HashMap<Node, EStructuralFeature>();

  /** store node to container. used only to record some text/cdata nodes */
  protected final HashMap<Node, EObject> nodeToContainer = new HashMap<Node, EObject>();

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
      {
        Object o = nodeToObject.get(node);
        if (o != null && o instanceof EObject)
        {
          return ((EObject)o).eContainer();
        }
        return (EObject)nodeToObject.get(node.getParentNode());
      }
      case Node.TEXT_NODE:
      case Node.CDATA_SECTION_NODE:
      {
        Object o = nodeToContainer.get(node);
        if (o != null)
        {
          return (EObject)o;
        }
        return (EObject)nodeToObject.get(node.getParentNode().getParentNode());
      }
      case Node.ATTRIBUTE_NODE:
        return (EObject)nodeToObject.get(((Attr)node).getOwnerElement());
      default:
        return null;
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.ecore.xmi.DOMHelper#getValue(org.w3c.dom.Node)
   */
  public Object getValue(Node node)
  {
    Object value = nodeToObject.get(node);
    if (value == null)
    {
      if (node.getNodeType() == Node.TEXT_NODE)
      {
        value = nodeToObject.get(node.getParentNode());
      }
    }
    return value;
  }

  public EStructuralFeature getEStructuralFeature(Node node)
  {
    short type = node.getNodeType();
    switch (type)
    {
      case Node.ELEMENT_NODE:
        return nodeToFeature.get(node);
      case Node.ATTRIBUTE_NODE:
      {
        EObject obj = (EObject)nodeToObject.get(((Attr)node).getOwnerElement());
        if (extendedMetaData == null)
        {
          return obj.eClass().getEStructuralFeature(node.getLocalName());
        }
        else if (obj != null)
        {
          return extendedMetaData.getAttribute(obj.eClass(), node.getNamespaceURI(), node.getLocalName());
        }
      }
      case Node.TEXT_NODE:
      case Node.CDATA_SECTION_NODE:
      {
        EStructuralFeature feature = nodeToFeature.get(node);
        if (feature == null)
        {
          feature = nodeToFeature.get(node.getParentNode());
        }
        return feature;
      }
      default:
        return null;
    }
  }

  public void recordValues(Node node, EObject container, EStructuralFeature feature, Object value)
  {
    debug(node, container, feature, value);

    short type = node.getNodeType();
    switch (type)
    {
      case Node.ELEMENT_NODE:
      {
        nodeToFeature.put(node, feature);
        // fall through
      }
      case Node.ATTRIBUTE_NODE:
      {
        if (value != null)
        {
          nodeToObject.put(node, value);
        }
        break;
      }
      case Node.TEXT_NODE:
      {
        if (nodeToObject.get(node.getParentNode()) == value)
        {
          break;
        }
        //fall through...
      }
      case Node.CDATA_SECTION_NODE:
      {
        nodeToFeature.put(node, feature);
        nodeToContainer.put(node, container);
        nodeToObject.put(node, value);
      }
    }
  }

  public DOMHelper getDOMHelper()
  {
    return this;
  }

  final static boolean DEBUG = false;

  private static final void debug(Node node, EObject container, EStructuralFeature feature, Object value)
  {
    if (DEBUG)
    {
      StringBuffer buf = new StringBuffer();

      buf.append("recordValues( ");
      buf.append(" {");
      switch (node.getNodeType())
      {
        case Node.ELEMENT_NODE:
          buf.append("ELEMENT_NODE ");
          break;
        case Node.ATTRIBUTE_NODE:
          buf.append("ATTRIBUTE_NODE ");
          break;
        case Node.TEXT_NODE:
          buf.append("TEXT_NODE ");
          break;
        case Node.CDATA_SECTION_NODE:
          buf.append("CDATA_SECTION_NODE ");
          break;
        default:
          buf.append("UNKNOWN ");
          break;
      }
      buf.append(node.getNodeName());
      buf.append("{ " + node.getNodeValue() + " }, ");
      if (container != null)
      {
        buf.append(container.eClass().getName() + ", ");
      }
      else
      {
        buf.append("null, ");
      }
      if (feature != null)
      {
        buf.append(feature.getName() + ", ");
      }
      else
      {
        buf.append("null, ");
      }
      if (value != null)
      {
        buf.append(value.getClass().getName() + ": " + value.toString() + ");");
      }
      else
      {
        buf.append("null);");
      }
      System.out.println(buf.toString());
    }
  }
}
