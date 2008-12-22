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
 * $Id$
 */
package org.eclipse.xsd.util;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;


/**
 * This class provides a default implementation of JAXPConfiguration interface. It creates
 * and configures SAX parser, DOM parser and transformer using options needed by XSD implementation.
 */
public class DefaultJAXPConfiguration implements JAXPConfiguration
{
  public Transformer createTransformer(String encoding) throws TransformerException
  {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();

    try
    {
      transformerFactory.setAttribute("indent-number", 2);
    }
    catch (IllegalArgumentException exception)
    {
      // Ignore
    }

    Transformer transformer = transformerFactory.newTransformer();

    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");

    // Unless a width is set, there will be only line breaks but no indentation.
    // The IBM JDK and the Sun JDK don't agree on the property name,
    // so we set them both.
    //
    transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    if (encoding != null)
    {
      transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
    }
    return transformer;
  }

  /**
   * Creates a SAXParser and sets some default options.
   */
  public SAXParser createSAXParser(LexicalHandler lexicalHandler) throws ParserConfigurationException, SAXException
  {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    saxParserFactory.setNamespaceAware(true);
    saxParserFactory.setValidating(false);
    saxParserFactory.setFeature("http://xml.org/sax/features/namespace-prefixes", true);

    SAXParser saxParser = saxParserFactory.newSAXParser();
    saxParser.setProperty("http://xml.org/sax/properties/lexical-handler", lexicalHandler);
    return saxParser;

  }

  /**
   * Creates a DocumentBuilder and sets some default options.
   * If features or properties are available, these features and properties are set on the documentBuilder.
   */
  public DocumentBuilder createDocumentBuilder(ErrorHandler errorHandler) throws ParserConfigurationException
  {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    documentBuilderFactory.setValidating(false);
    try
    {
      documentBuilderFactory.setAttribute("http://apache.org/xml/features/dom/defer-node-expansion", Boolean.FALSE);
    }
    catch (IllegalArgumentException e)
    {
      // do nothing
    }
    DocumentBuilder parser = documentBuilderFactory.newDocumentBuilder();
    parser.setEntityResolver(createEntityResolver());
    parser.setErrorHandler(errorHandler);
    return parser;
  }

  public EntityResolver createEntityResolver()
  {
    return XSDResourceImpl.createEntityResolver();
  }

}
