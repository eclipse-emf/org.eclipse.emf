/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.impl.type;

import org.eclipse.emf.ecore.xml.type.internal.QName;
import org.eclipse.xsd.impl.XSDSimpleTypeDefinitionImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class XSDQNameType extends XSDAnySimpleType
{
  @Override
  public void assess(XSDSimpleTypeDefinitionImpl.AssessmentImpl assessment)
  {
    assessment.xsdAnySimpleType = this;
    Object value = null;
    if (assessment.context == null)
    {
      value = getValue(assessment.normalizedLiteral);    
    }
    else
    {
      value = getValue(assessment.context, assessment.normalizedLiteral);    
    }
    if (value != null)
    {
      assessment.value = value;
    }
    else
    {
      assessment.reportDatatypeDiagnostic();
    }
  }

  @Override
  public Object getValue(String literal)
  {
    try
    {
      return new QName(literal);
    }
    catch (RuntimeException e)
    {
      return null;
    }
  }

  public Object getValue(Element context, String literal)
  {
    try
    {
      String localPart;
      String prefix;
      String namespaceAttribute;
      int index = literal.indexOf(':');
      if (index == -1)
      {
        localPart = literal;
        prefix = "";
        namespaceAttribute = "xmlns";
      }
      else
      {
        localPart = literal.substring(index + 1);
        prefix = literal.substring(0, index);
        namespaceAttribute = "xmlns:" + prefix;
      }
      String namespace = null;
      for (Node node = context; node instanceof Element; node = node.getParentNode())
      {
        Element elementNode = (Element)node;
        if (elementNode.hasAttribute(namespaceAttribute))
        {
          namespace = elementNode.getAttribute(namespaceAttribute);
          break;
        }
      }
      if (namespace == null)
      {
        if (index == -1)
        {
          namespace = "";
        }
        else
        {
          return null;
        }
      }
      return new QName(namespace, localPart, prefix);
    }
    catch (RuntimeException e)
    {
      return null;
    }
  }
}
