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
 * $Id: SAXWrapper.java,v 1.5 2005/06/22 14:45:00 khussey Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

import org.eclipse.emf.ecore.xmi.XMIException;


/**
 * This class wraps an XMLHandler with a SAX DefaultHandler.
 */
public class SAXWrapper extends DefaultHandler implements LexicalHandler
{
  protected XMLHandler handler;
  
  /**
   * Constructor for SAXWrapper.
   */
  public SAXWrapper(XMLHandler handler)
  {
    super();
    this.handler = handler;
  }

  public void setDocumentLocator(Locator locator)
  {
    handler.setLocator(locator);
  }

  public void startDocument() throws SAXException
  {
    handler.startDocument();
  }

  public void endDocument() throws SAXException
  {
    handler.endDocument();
  }

  public void startPrefixMapping(String prefix, String uri) throws SAXException
  {
  }

  public void endPrefixMapping(String prefix) throws SAXException
  {
  }

  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
  {
    handler.setAttributes(attributes);
    handler.startElement(uri, localName, qName);
  }

  public void endElement (String uri, String localName, String qName) throws SAXException
  {
    handler.endElement(uri, localName, qName);
  }

  public void warning (SAXParseException e) throws SAXException
  {
    XMIException xmi = new XMIException(e.getException() == null ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
    handler.warning(xmi);
  }

  public void error (SAXParseException e) throws SAXException
  {
    XMIException xmi = new XMIException(e.getException() == null ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
    handler.error(xmi);
  }

  public void fatalError (SAXParseException e) throws SAXException
  {
    XMIException xmi = new XMIException(e.getException() == null ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
    handler.fatalError(xmi);
    throw e;
  }

  public void characters (char ch[], int start, int length) throws SAXException
  {
    handler.characters(ch, start, length);
  }

  public void ignorableWhitespace (char ch[], int start, int length) throws SAXException
  {
    // handler.ignorableWhitespace(ch, start, length);
  }

  public void processingInstruction (String target, String data) throws SAXException
  {
    handler.processingInstruction(target, data);
  }

  public void skippedEntity (String name) throws SAXException
  {
    // handler.skippedEntity(name);
  }

  public InputSource resolveEntity(String publicId, String systemId) throws SAXException
  {
    // handler.resolveEntity(publicId, systemId);
    return null;
  }

  public void notationDecl(String name, String publicId, String systemId) throws SAXException
  {
    // handler.notationDecl(name, publicId, systemId);
  }

  public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException
  {
    // handler.unparsedEntityDecl(name, publicId, systemId, notationName);
  }

  public void startDTD(java.lang.String name, java.lang.String publicId, java.lang.String systemId)
  {
    handler.startDTD(name, publicId, systemId);
  }

  public void endDTD()
  {
  }

  public void startEntity(java.lang.String name)
  {
  }

  public void endEntity(java.lang.String name)
  {
  }

  public void startCDATA()
  {
    handler.startCDATA();
  }

  public void endCDATA()
  {
    handler.endCDATA();
  }

  public void comment(char [] characters, int start, int length) throws SAXException
  {
    handler.comment(characters, start, length);
  }
}
