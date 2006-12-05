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
 * $Id: SAXWrapper.java,v 1.7 2006/12/05 20:23:28 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.emf.ecore.xmi.XMLDefaultHandler;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;


/**
 * @deprecated 
 * This class wraps an XMLHandler with a SAX DefaultHandler.
 */
@Deprecated
public class SAXWrapper extends DefaultHandler implements XMLDefaultHandler
{
  protected XMLHandler handler;
  
  
  public void prepare(XMLResource resource, XMLHelper helper, Map<?,?> options)
  {
    handler.prepare(resource, helper, options);  
  }

  public void reset()
  {
   handler.reset();    
  }

  /**
   * Constructor for SAXWrapper.
   */
  public SAXWrapper(XMLHandler handler)
  {
    super();
    this.handler = handler;
  }

  @Override
  public void setDocumentLocator(Locator locator)
  {
    handler.setLocator(locator);
  }

  @Override
  public void startDocument() throws SAXException
  {
    handler.startDocument();
  }

  @Override
  public void endDocument() throws SAXException
  {
    handler.endDocument();
  }

  @Override
  public void startPrefixMapping(String prefix, String uri) throws SAXException
  {
    handler.startPrefixMapping(prefix, uri);
  }

  @Override
  public void endPrefixMapping(String prefix) throws SAXException
  {
    // Ignore.
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
  {
    handler.setAttributes(attributes);
    handler.startElement(uri, localName, qName);
  }

  @Override
  public void endElement (String uri, String localName, String qName) throws SAXException
  {
    handler.endElement(uri, localName, qName);
  }

  @Override
  public void warning (SAXParseException e) throws SAXException
  {
    XMIException xmi = new XMIException(e.getException() == null ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
    handler.warning(xmi);
  }

  @Override
  public void error (SAXParseException e) throws SAXException
  {
    XMIException xmi = new XMIException(e.getException() == null ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
    handler.error(xmi);
  }

  @Override
  public void fatalError (SAXParseException e) throws SAXException
  {
    XMIException xmi = new XMIException(e.getException() == null ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
    handler.fatalError(xmi);
    throw e;
  }

  @Override
  public void characters (char ch[], int start, int length) throws SAXException
  {
    handler.characters(ch, start, length);
  }

  @Override
  public void ignorableWhitespace (char ch[], int start, int length) throws SAXException
  {
    // handler.ignorableWhitespace(ch, start, length);
  }

  @Override
  public void processingInstruction (String target, String data) throws SAXException
  {
    handler.processingInstruction(target, data);
  }

  @Override
  public void skippedEntity (String name) throws SAXException
  {
    // handler.skippedEntity(name);
  }

  @Override
  public InputSource resolveEntity(String publicId, String systemId) throws SAXException
  {
    // handler.resolveEntity(publicId, systemId);
    return null;
  }

  @Override
  public void notationDecl(String name, String publicId, String systemId) throws SAXException
  {
    // handler.notationDecl(name, publicId, systemId);
  }

  @Override
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
    // Ignore.
  }

  public void startEntity(java.lang.String name)
  {
    // Ignore.
  }

  public void endEntity(java.lang.String name)
  {
    // Ignore.
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
