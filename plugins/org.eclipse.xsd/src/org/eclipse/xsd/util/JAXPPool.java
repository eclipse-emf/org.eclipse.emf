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


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/** 
 * This interface defines pool that can be used to fetch and cache document builders, SAX parsers, and transformers.
 * It's generally expected that instances will be thread safe.
 */
public interface JAXPPool
{
  /**
   * Fetches a document builder and sets the given error handler.
   * @param errorHandler the error handler to use while build documents.
   * @return a document builder.
   * @throws ParserConfigurationException
   * @throws SAXException
   */
  DocumentBuilder getDocumentBuilder(ErrorHandler errorHandler) throws ParserConfigurationException, SAXException;

  /**
   * Returns a document builder {@link #getDocumentBuilder(ErrorHandler) fetched} from this pool 
   * back to the pool for subsequent reuse.
   * @param documentBuilder the document builder to release.
   */
  void releaseDocumentBuilder(DocumentBuilder documentBuilder);

  /**
   * Fetches a SAX parser and set the given lexical handler.
   * @param lexicalHandler the lexical handler to use while parsing.
   * @return a SAX parser.
   * @throws ParserConfigurationException
   * @throws SAXException
   */
  SAXParser getSAXParser(LexicalHandler lexicalHandler) throws ParserConfigurationException, SAXException;

  /**
   * Returns a SAX parser {@link #getSAXParser(LexicalHandler) fetched} from this pool 
   * back to the pool for subsequent reuse.
   * @param parser the SAX parser to release.
   */
  void releaseSAXParser(SAXParser parser);

  /**
   * Fetches a transformer and set the given encoding.
   * @param encoding the encoding used to convert characters to bytes.
   * @return a transformer.
   * @throws TransformerException
   */
  Transformer getTransformer(String encoding) throws TransformerException;

  /**
   * Returns a transformer {@link #getTransformer(String) fetched} from this pool
   * back to the pool for subsequent reuse.
   * @param transformer the transformer to release.
   */
  void releaseTransformer(Transformer transformer);
}
