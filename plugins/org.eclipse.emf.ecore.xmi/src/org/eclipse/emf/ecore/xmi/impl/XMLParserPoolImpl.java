/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.emf.ecore.xmi.XMLDefaultHandler;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.xml.sax.SAXException;


/**
 * This is the default thread safe implementation of XMLParserPool. 
 * This implementation is tuned for caching parsers and handlers created using same loading options. 
 * To avoid possible memory leak (in case user is trying to parse documents using different options for every parse), 
 * there is a restriction on the size of the pool. 
 * The key used for handler caching is based on the option map passed to load.
 */
public class XMLParserPoolImpl implements XMLParserPool
{
  private final Map<Map<?, ?>, List<SAXParser>> parserCache = new HashMap<Map<?, ?>, List<SAXParser>>();

  private final Map<Map<?, ?>, List<XMLDefaultHandler>> handlersCache;
  
  private final int size;
  
  /**
   * Creates an instance that caches only parsers but not handlers.
   * @see #XMLParserPoolImpl(boolean)
   */
  public XMLParserPoolImpl()
  {
    this(200, false);
  }

  /**
   * Creates an instance that caches parsers and caches handlers as specified.
   * @param useHandlerCache indicates whether handler caching should be use.
   * @see #XMLParserPoolImpl(boolean)
   */
  public XMLParserPoolImpl(boolean useHandlerCache)
  {
    this(200, useHandlerCache);
  }

  /**
   * Creates an instance that caches parsers and caches handlers as specified.
   * @param size indicates the maximum number of instances parser or handler instances that will be cached.
   * @param useHandlerCache indicates whether handler caching should be use.
   * @see #XMLParserPoolImpl(boolean)
   */
  public XMLParserPoolImpl(int size, boolean useHandlerCache)
  {
    this.size = size;
    handlersCache = useHandlerCache ? new HashMap<Map<?, ?>, List<XMLDefaultHandler>>() : null;
  }
  
  /**
   * @see XMLParserPool#get(Map, Map, boolean)
   */
  public synchronized SAXParser get(Map<String, Boolean> features, Map<String, ?> properties, boolean useLexicalHandler) throws ParserConfigurationException, SAXException
  {
    Map<Object, Object> map = new HashMap<Object, Object>();
    map.putAll(features);
    map.putAll(properties);
    map.put(XMLResource.OPTION_USE_LEXICAL_HANDLER, useLexicalHandler ? Boolean.TRUE : Boolean.FALSE);
    if (parserCache.size() > size)
    {
      parserCache.clear();
    }
    List<SAXParser> list = parserCache.get(map);
    if (list != null)
    {
      int size = list.size();
      if (size > 0)
      {
        return list.remove(size - 1);
      }
      else
      {
        return makeParser(features, properties);
      }
    }
    else
    {
      parserCache.put(map, new ArrayList<SAXParser>());
      return makeParser(features, properties);
    }
  }

  /**
   * @see XMLParserPool#release(SAXParser, Map, Map, boolean)
   */
  public synchronized void release(SAXParser parser, Map<String, Boolean> features, Map<String, ?> properties, boolean useLexicalHandler)
  {
    Map<Object, Object> map = new HashMap<Object, Object>();
    map.putAll(features);
    map.putAll(properties);
    map.put(XMLResource.OPTION_USE_LEXICAL_HANDLER, useLexicalHandler ? Boolean.TRUE : Boolean.FALSE);
    List<SAXParser> list = parserCache.get(map);
    if (list.size() < size)
    {
      list.add(parser);
    }
  }

  protected SAXParser makeParser(Map<String, Boolean> features, Map<String, ?> properties) throws ParserConfigurationException, SAXException
  {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(false);
    factory.setNamespaceAware(true);
    SAXParser parser = factory.newSAXParser();
    
    // set parser features and properties
    if (features != null)
    {
      for (Map.Entry<String, Boolean> entry : features.entrySet())
      {
        parser.getXMLReader().setFeature(entry.getKey(), entry.getValue());
      }
    }
    if (properties != null)
    {
      for (Map.Entry<String, ?> entry : properties.entrySet())
      {
        parser.getXMLReader().setProperty(entry.getKey(), entry.getValue());
      }
    }
    return parser;
  }

  public synchronized XMLDefaultHandler getDefaultHandler(XMLResource resource, XMLLoad xmlLoad, XMLHelper helper, Map<?, ?> options)
  {
    if (handlersCache != null)
    {
      if (handlersCache.size() > size)
      {
        handlersCache.clear();
      }
      List<XMLDefaultHandler> list = handlersCache.get(options);
      if (list != null)
      {
        int size = list.size();
        if (size > 0)
        {
          XMLDefaultHandler handler = list.remove(size - 1);
          handler.prepare(resource, helper, options);
          return handler;
        }
      }
      else
      {
        handlersCache.put(options, new ArrayList<XMLDefaultHandler>());
      }
    }
    return xmlLoad.createDefaultHandler();
  }

  public synchronized void releaseDefaultHandler(XMLDefaultHandler handler, Map<?, ?> options)
  {
    if (handlersCache != null)
    {
      handler.reset();
      List<XMLDefaultHandler> list = handlersCache.get(options);
      if (list == null)
      {
        list = new ArrayList<XMLDefaultHandler>();
        handlersCache.put(options, list);
      }
      else if (list.size() < size)
      {
        list.add(handler);
      }
    }
  }
}
