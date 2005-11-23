/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id$
 */

package org.eclipse.xsd.util;


import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;


/**
 * The DefaultJAXPPool class provides a thread-safe implementation of JAXPPool interface.
 *
 */
public class DefaultJAXPPool implements JAXPPool
{

  private final List documentBuilderCache = new ArrayList();

  private final List transformerCache = new ArrayList();

  private final List saxParserCache = new ArrayList();

  private final JAXPConfiguration configuration;

  /**
   * Creates JAXPPool using DefaultJAXPConfiguration implementation
   */
  public DefaultJAXPPool()
  {
    this(new DefaultJAXPConfiguration());
  }

  /**
   * Creates JAXP pool using specified JAXPConfiguration.
   * @param configuration - specify JAXPConfiguration (null is not a valid value)
   */
  public DefaultJAXPPool(JAXPConfiguration configuration)
  {
    this.configuration = configuration;
  }

  public synchronized DocumentBuilder getDocumentBuilder(ErrorHandler errorHandler) throws ParserConfigurationException, SAXException
  {
    int size = documentBuilderCache.size();
    if (size > 0)
    {
      return (DocumentBuilder)documentBuilderCache.remove(size - 1);
    }
    else
    {
      return configuration.createDocumentBuilder(errorHandler);
    }
  }

  public synchronized void releaseDocumentBuilder(DocumentBuilder documentBuilder)
  {
    if (documentBuilder != null)
    {
      documentBuilderCache.add(documentBuilder);
    }
  }

  public synchronized SAXParser getSAXParser(LexicalHandler lexicalHandler) throws ParserConfigurationException, SAXException
  {
    int size = saxParserCache.size();
    if (size > 0)
    {
      return (SAXParser)saxParserCache.remove(size - 1);
    }
    else
    {
      return configuration.createSAXParser(lexicalHandler);
    }
  }

  public synchronized void releaseSAXParser(SAXParser parser)
  {
    if (parser != null)
    {
      saxParserCache.add(parser);
    }
  }

  public synchronized Transformer getTransformer(String encoding) throws TransformerException
  {
    int size = transformerCache.size();
    if (size > 0)
    {
      return (Transformer)transformerCache.remove(size - 1);
    }
    else
    {
      return configuration.createTransformer(encoding);
    }
  }

  public synchronized void releaseTransformer(Transformer transformer)
  {
    if (transformer != null)
    {
      transformerCache.add(transformer);
    }
  }

}
