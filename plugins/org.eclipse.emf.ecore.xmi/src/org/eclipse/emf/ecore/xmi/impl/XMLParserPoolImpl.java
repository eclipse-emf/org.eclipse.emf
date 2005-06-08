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
 * $Id: XMLParserPoolImpl.java,v 1.2.2.1 2005/06/08 18:27:42 nickb Exp $
 */

package org.eclipse.emf.ecore.xmi.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.xml.sax.SAXException;

/**
 * This is the default implementation of XMLParserPool. Note: this
 * implementation is not thread safe.
 */
public class XMLParserPoolImpl implements XMLParserPool
{

  private final HashMap parserCache = new HashMap();

  /**
   * @see XMLParserPool#get(Map, Map, boolean)
   */
  public synchronized SAXParser get(Map features, Map properties, boolean useLexicalHandler)
      throws ParserConfigurationException, SAXException
  {
    Map map = new HashMap();
    map.putAll(features);
    map.putAll(properties);
    map.put(XMLResource.OPTION_USE_LEXICAL_HANDLER, useLexicalHandler ? Boolean.TRUE : Boolean.FALSE);
    Object o = parserCache.get(map);
    if (o != null)
    {
      ArrayList list = (ArrayList) o;
      int size = list.size();
      if (size > 0)
      {
        return (SAXParser) list.remove(size - 1);
      } 
      else
      {
        return makeParser(features, properties);
      }
    } 
    else
    {
      parserCache.put(map, new ArrayList());
      return makeParser(features, properties);
    }
  }

  /**
   * @see XMLParserPool#release(SAXParser, Map, Map, boolean)
   */
  public synchronized void release(SAXParser parser, Map features, Map properties, boolean useLexicalHandler)
  {
    Map map = new HashMap();
    map.putAll(features);
    map.putAll(properties);
    map.put(XMLResource.OPTION_USE_LEXICAL_HANDLER, useLexicalHandler ? Boolean.TRUE : Boolean.FALSE);
    ArrayList list = (ArrayList) parserCache.get(map);
    list.add(parser);
  }

  private SAXParser makeParser(Map features, Map properties)
      throws ParserConfigurationException, SAXException
  {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parser = factory.newSAXParser();
    // set parser features and properties
    if (features != null)
    {
      for (Iterator i = features.keySet().iterator(); i.hasNext();)
      {
        String feature = (String) i.next();
        parser.getXMLReader().setFeature(feature,
            ((Boolean) features.get(feature)).booleanValue());
      }
    }
    if (properties != null)
    {
      for (Iterator i = properties.keySet().iterator(); i.hasNext();)
      {
        String property = (String) i.next();
        parser.getXMLReader().setProperty(property, properties.get(property));
      }
    }
    return parser;
  }
}
