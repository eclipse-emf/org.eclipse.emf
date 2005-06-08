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
 * $Id: XMLLoadImpl.java,v 1.6 2005/06/08 06:16:07 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;


/**
 * This class begins parsing with the given input stream using the XML
 * deserializer.
 */
public class XMLLoadImpl implements XMLLoad
{
  protected static final String SAX_LEXICAL_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
  protected static final int BUFFER_SIZE = 200;
  protected XMLResource resource;
  protected InputStream is;
  protected XMLHelper helper;
  protected Map options;

  public XMLLoadImpl(XMLHelper helper)
  {
    this.helper = helper;
  }

  /**
   * Start parsing with the default handler; either XMI
   * or XML.
   */
  public void load(XMLResource resource, InputStream inputStream, Map options) throws IOException
  {
    this.resource = resource;
    is = inputStream;
    this.options = options;
    XMLParserPool pool = (XMLParserPool)options.get(XMLResource.OPTION_USE_PARSER_POOL);
    Map parserFeatures = (Map) options.get(XMLResource.OPTION_PARSER_FEATURES);
    Map parserProperties = (Map)options.get(XMLResource.OPTION_PARSER_PROPERTIES);
    parserFeatures = (parserFeatures == null)?Collections.EMPTY_MAP:parserFeatures;
    parserProperties = (parserProperties == null)?Collections.EMPTY_MAP:parserProperties;
    
    // HACK: reading encoding
    String encoding = getEncoding();
    resource.setEncoding(encoding);
    try
    {
      SAXParser parser;      

      if (pool != null)
      {
        // use the pool to retrieve the parser
        parser = pool.get(parserFeatures, parserProperties, Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)));
      } 
      else 
      {
        parser = makeParser();
        // set features and properties
        if (parserFeatures != null)
        {
          for (Iterator i = parserFeatures.keySet().iterator(); i.hasNext();)
          {
            String feature = (String) i.next();
            parser.getXMLReader().setFeature(feature, ((Boolean) parserFeatures.get(feature)).booleanValue());
          }
        }
        if (parserProperties !=null)
        {
          for (Iterator i = parserProperties.keySet().iterator(); i.hasNext();)
          {
            String property = (String) i.next();
            parser.getXMLReader().setProperty(property, parserProperties.get(property));
          }
        }
      }

      InputSource inputSource = new InputSource(is);
      if (resource.getURI() != null)
      {
        String resourceURI = resource.getURI().toString();
        inputSource.setPublicId(resourceURI);
        inputSource.setSystemId(resourceURI);
      }

      DefaultHandler defaultHandler = makeDefaultHandler(); 
      
      // set lexical handler
      if (options != null && Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)))
      {
        if (parserProperties == null || parserProperties.get(SAX_LEXICAL_PROPERTY) == null) 
        {
          parser.setProperty(SAX_LEXICAL_PROPERTY, defaultHandler);
        }
      }
      
      parser.parse(inputSource, defaultHandler);
      
      // avoid memory leak bug #85141
      if (defaultHandler instanceof SAXWrapper)
      {
        ((SAXWrapper)defaultHandler).handler = null;
      }
      
      // release parser back to the pool
      if (pool != null)
      {
        pool.release(parser, parserFeatures, parserProperties, Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)));
      }
      
      helper = null;
      if (!resource.getErrors().isEmpty())
      {
        Exception error = (Exception)resource.getErrors().get(0);
        if (error instanceof XMIException)
        {
          XMIException exception = (XMIException)error;
          if (exception.getWrappedException() != null)
          {
            throw new Resource.IOWrappedException(exception.getWrappedException());
          }
        }
        throw new Resource.IOWrappedException(error);
      }
    }
    catch (SAXException exception)
    {
      if (exception.getException() != null)
      {
        throw new Resource.IOWrappedException(exception.getException());
      }
      else
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
    catch (ParserConfigurationException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
  }

  /**
   * Make either a validating or non-validating parser;
   * throw an if one could not be made.
   */
  protected SAXParser makeParser() throws ParserConfigurationException, SAXException
  {
    SAXParserFactory f = SAXParserFactory.newInstance();
    return f.newSAXParser();
  }

  protected DefaultHandler makeDefaultHandler()
  {
    return new SAXWrapper(new SAXXMLHandler(resource, helper, options));
  }

  protected String getEncoding() throws IOException
  {
    if (!is.markSupported())
      is = new BufferedInputStream(is);
    
    byte[] buffer = readBuffer();
    return XMLHandler.getXMLEncoding(buffer);
  }  

  protected byte[] readBuffer() throws IOException
  {
    if (is.available() == 0)
    {
      return new byte[0];
    }

    byte[] buffer = new byte[BUFFER_SIZE];
    is.mark(BUFFER_SIZE);
    int bytesRead = is.read(buffer, 0, BUFFER_SIZE);
    int totalBytesRead = bytesRead;
    
    while (bytesRead != -1 && (totalBytesRead < BUFFER_SIZE))
    {
      bytesRead = is.read(buffer, totalBytesRead, BUFFER_SIZE - totalBytesRead);

      if (bytesRead != -1)
        totalBytesRead += bytesRead;
    }

    if (totalBytesRead < BUFFER_SIZE)
    {
      byte[] smallerBuffer = new byte[totalBytesRead];
      System.arraycopy(buffer, 0, smallerBuffer, 0, totalBytesRead);
      smallerBuffer = buffer;
    }
      
    is.reset();
    return buffer;
  }
    
} // XMLLoad
