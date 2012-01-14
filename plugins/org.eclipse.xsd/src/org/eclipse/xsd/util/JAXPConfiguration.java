/**
 * Copyright (c) 2002-2005 IBM Corporation and others.
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

import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/** 
 * This interface defines methods to create and configure SAX parser, DOM parser and Transformer.
 */
public interface JAXPConfiguration
{
  SAXParser createSAXParser(LexicalHandler lexicalHandler) throws ParserConfigurationException, SAXException;

  DocumentBuilder createDocumentBuilder(ErrorHandler errorHandler) throws ParserConfigurationException;

  Transformer createTransformer(String encoding) throws TransformerException;

  EntityResolver createEntityResolver();

}
