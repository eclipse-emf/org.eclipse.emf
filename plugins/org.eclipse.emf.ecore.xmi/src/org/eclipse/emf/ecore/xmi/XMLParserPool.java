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
 *$Id: XMLParserPool.java,v 1.2 2005/06/08 06:16:07 nickb Exp $
 */

package org.eclipse.emf.ecore.xmi;

import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import org.xml.sax.SAXException;

/**
 * A pool of parsers that a resource implementation can use to get a parser instance for parsing XML instance documents. 
 * The use of a parser pool can be specified using {@link XMLResource#OPTION_USE_PARSER_POOL} load option.
 * <p>
 * The parser instance is retrieved and placed back to the pool
 * based on the features and properties specified for this parser. 
 * The default implementation is provided by {@link org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl XMLParserPoolImpl}.
 * </p>
 * <p>
 * Note that the correct properties and feature maps for this parser instance 
 * <b>must</b> be provided when retrieving the parser from the pool and, even more importantly when, 
 * releasing the parser back to the pool.
 * Failure to do so will result in improperly configured parsers.
 * </p>
 */
public interface XMLParserPool
{
  /**
   * Retrieves a parser from the pool given specified properties and features.
   * If parser can't be created using specified properties or features, 
   * an exception can be thrown.
   *
   * @param features a map of the parser features and their values.
   * @param properties a map of a parser properties and their values.
   * @param useLexicalHandler whether a lexical handler was set during loading.
   * @return A parser instance with given features and properties.
   * @throws ParserConfigurationException
   * @throws SAXException
   */
  public SAXParser get(Map features, Map properties, boolean useLexicalHandler) throws ParserConfigurationException, SAXException;

  /**
   * Returns the parser to the pool.
   * @param parser the parser to return to the pool.
   * @param features a map of the parser features and their values.
   * @param properties a map of a parser properties and their values.
   * @param useLexicalHandler whether a lexical handler was set during loading.
   */
  public void release(SAXParser parser, Map features, Map properties, boolean useLexicalHandler);
}
