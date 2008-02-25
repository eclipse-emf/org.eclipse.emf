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
 * $Id: CompareXML.java,v 1.3 2008/02/25 15:09:13 emerks Exp $
 */
package org.eclipse.emf.test.xml.xmi;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;

import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * A helper class that provides methods that allow comparing two XML instances.
 */
public class CompareXML extends TestCase
{
  public static void compareFiles(DocumentBuilder builder, String expectedOutput, ByteArrayInputStream currentOutput) throws Exception
  {
    Document doc = builder.parse(currentOutput);
    Document doc2 = builder.parse(expectedOutput);
    compareNodes(expectedOutput, doc2.getDocumentElement(), doc.getDocumentElement());
  }

  public static void compareDocuments(String uri, Document expected, Document document) throws Exception
  {
    compareNodes(uri, expected.getDocumentElement(), document.getDocumentElement());
  }

  protected static void compareNodes(String uri, Node node1, Node node2)
  {
    compareData(uri, node1, node2);

    switch (node1.getNodeType())
    {
      case Node.ELEMENT_NODE: {
        boolean hasAttrs = node1.hasAttributes();
        assertEquals(uri+": HasAttributes: " + node1 + "!=" + node2, hasAttrs, ((Element)node2).hasAttributes());
        if (hasAttrs)
        {
          NamedNodeMap map1 = node1.getAttributes();
          NamedNodeMap map2 = ((Element)node2).getAttributes();
          int len = map1.getLength();
          assertEquals(uri+": MissingAttr: " + node1 + "!=" + node2, len, map2.getLength());
          for (int i = 0; i < len; i++)
          {
            Node n1 = map1.item(i);
            if (n1.getLocalName() == null)
            { // DOM Level 1 Node
              Node n2 = map2.getNamedItem(n1.getNodeName());
              assertNotNull(uri+": AttrNull: " + n1, n2);
              compareData(uri, n1, n2);
            }
            else
            {
              Node n2 = map2.getNamedItemNS(n1.getNamespaceURI(), n1.getLocalName());
              assertNotNull(uri+": AttrNull: " + n1, n2);
              compareData(uri, n1, n2);
            }
          }
        }
      }
    }

    Node child1 = node1.getFirstChild();
    Node child2 = node2.getFirstChild();
    while (child1 != null && child2 != null)
    {
      compareNodes(uri, child1, child2);
      child1 = child1.getNextSibling();
      child2 = child2.getNextSibling();
    }
    assertEquals(uri+": NotEquals: " + node1 + "!=" + node2, child1, child2);
  }

  protected static void compareData(String uri, Node node1, Node node2)
  {
    assertEquals(uri+": NodeType error: " + node1 + "!=" + node2, node1.getNodeType(), node2.getNodeType());
    assertEquals(uri+": NodeName: " + node1 + "!=" + node2, node1.getNodeName(), node2.getNodeName());
    assertEquals(uri+": NodeLocalName: " + node1 + "!=" + node2, node1.getLocalName(), node2.getLocalName());
    assertEquals(uri+": NodeNamespaceURI: " + node1 + "!=" + node2, node1.getNamespaceURI(), node2.getNamespaceURI());
    assertEquals(uri+": NodeValue: " + node1 + "!=" + node2, node1.getNodeValue(), node2.getNodeValue());
  }


}
