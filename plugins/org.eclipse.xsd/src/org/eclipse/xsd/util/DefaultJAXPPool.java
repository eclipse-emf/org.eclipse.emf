/**
 * Copyright (c) 2002-2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

package org.eclipse.xsd.util;


import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;


/**
 * The DefaultJAXPPool class provides a thread-safe implementation of JAXPPool interface.
 *
 */
public class DefaultJAXPPool implements JAXPPool
{
  private final List<DocumentBuilder> documentBuilderCache = new ArrayList<DocumentBuilder>();

  private final List<Transformer> transformerCache = new ArrayList<Transformer>();

  private final List<SAXParser> saxParserCache = new ArrayList<SAXParser>();

  private final JAXPConfiguration configuration;

  /**
   * Creates an instance using {@link DefaultJAXPConfiguration}.
   */
  public DefaultJAXPPool()
  {
    this(new DefaultJAXPConfiguration());
  }

  /**
   * Creates an instance using the specified JAXPConfiguration.
   * @param configuration the non-null configuration used to create new 
   * {@link #getDocumentBuilder(ErrorHandler) builders}, 
   * {@link #getSAXParser(LexicalHandler) parsers}, 
   * and {@link #getTransformer(String) transformers}.
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
      DocumentBuilder documentBuilder = documentBuilderCache.remove(size - 1);
      documentBuilder.setErrorHandler(errorHandler);
      return documentBuilder;
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
      documentBuilder.setErrorHandler
        (new ErrorHandler()
         {
           public void error(SAXParseException exception) throws SAXException
           {
             // Ignore.
           }

           public void fatalError(SAXParseException exception) throws SAXException
           {
             // Ignore.
           }

           public void warning(SAXParseException exception) throws SAXException
           {
             // Ignore.
           }
         });
      documentBuilderCache.add(documentBuilder);
    }
  }

  public synchronized SAXParser getSAXParser(LexicalHandler lexicalHandler) throws ParserConfigurationException, SAXException
  {
    int size = saxParserCache.size();
    if (size > 0)
    {
      SAXParser saxParser = saxParserCache.remove(size - 1);
      saxParser.setProperty("http://xml.org/sax/properties/lexical-handler", lexicalHandler);
      return saxParser;
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
      try
      {
        parser.setProperty
          ("http://xml.org/sax/properties/lexical-handler",
            new LexicalHandler()
            {
              public void comment(char[] comment, int start, int length) throws SAXException
              {
                // Ignore.
              }

              public void endCDATA() throws SAXException
              {
                // Ignore.
              }

              public void endDTD() throws SAXException
              {
                // Ignore.
              }

              public void endEntity(String name) throws SAXException
              {
                // Ignore.
              }

              public void startCDATA() throws SAXException
              {
                // Ignore.
              }

              public void startDTD(String name, String publicId, String systemId) throws SAXException
              {
                // Ignore.
              }

              public void startEntity(String name) throws SAXException
              {
                // Ignore.
              }
            });
      }
      catch (SAXNotRecognizedException exception)
      {
        // Ignore.
      }
      catch (SAXNotSupportedException exception)
      {
        // Ignore.
      }
      saxParserCache.add(parser);
    }
  }

  public synchronized Transformer getTransformer(String encoding) throws TransformerException
  {
    int size = transformerCache.size();
    if (size > 0)
    {
      Transformer transformer = transformerCache.remove(size - 1);
      if (encoding != null)
      {
        transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
      }
      return transformer;
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
