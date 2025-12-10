/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
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
  @Deprecated
  protected XMLHandler handler;
  
  
  @Deprecated
  public void prepare(XMLResource resource, XMLHelper helper, Map<?,?> options)
  {
    handler.prepare(resource, helper, options);  
  }

  @Deprecated
  public void reset()
  {
   handler.reset();    
  }

  /**
   * Constructor for SAXWrapper.
   */
  @Deprecated
  public SAXWrapper(XMLHandler handler)
  {
    super();
    this.handler = handler;
  }

  @Deprecated
  @Override
  public void setDocumentLocator(Locator locator)
  {
    handler.setLocator(locator);
  }

  @Deprecated
  @Override
  public void startDocument() throws SAXException
  {
    handler.startDocument();
  }

  @Deprecated
  @Override
  public void endDocument() throws SAXException
  {
    handler.endDocument();
  }

  @Deprecated
  @Override
  public void startPrefixMapping(String prefix, String uri) throws SAXException
  {
    handler.startPrefixMapping(prefix, uri);
  }

  @Deprecated
  @Override
  public void endPrefixMapping(String prefix) throws SAXException
  {
    // Ignore.
  }

  @Deprecated
  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
  {
    handler.setAttributes(attributes);
    handler.startElement(uri, localName, qName);
  }

  @Deprecated
  @Override
  public void endElement (String uri, String localName, String qName) throws SAXException
  {
    handler.endElement(uri, localName, qName);
  }

  @Deprecated
  @Override
  public void warning (SAXParseException e) throws SAXException
  {
    XMIException xmi = new XMIException(e.getException() == null ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
    handler.warning(xmi);
  }

  @Deprecated
  @Override
  public void error (SAXParseException e) throws SAXException
  {
    XMIException xmi = new XMIException(e.getException() == null ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
    handler.error(xmi);
  }

  @Deprecated
  @Override
  public void fatalError (SAXParseException e) throws SAXException
  {
    XMIException xmi = new XMIException(e.getException() == null ? e : e.getException(), e.getSystemId(), e.getLineNumber(), e.getColumnNumber());
    handler.fatalError(xmi);
    throw e;
  }

  @Deprecated
  @Override
  public void characters (char ch[], int start, int length) throws SAXException
  {
    handler.characters(ch, start, length);
  }

  @Deprecated
  @Override
  public void ignorableWhitespace (char ch[], int start, int length) throws SAXException
  {
    // handler.ignorableWhitespace(ch, start, length);
  }

  @Deprecated
  @Override
  public void processingInstruction (String target, String data) throws SAXException
  {
    handler.processingInstruction(target, data);
  }

  @Deprecated
  @Override
  public void skippedEntity (String name) throws SAXException
  {
    // handler.skippedEntity(name);
  }

  @Deprecated
  @Override
  public InputSource resolveEntity(String publicId, String systemId) throws SAXException
  {
    // handler.resolveEntity(publicId, systemId);
    return null;
  }

  @Deprecated
  @Override
  public void notationDecl(String name, String publicId, String systemId) throws SAXException
  {
    // handler.notationDecl(name, publicId, systemId);
  }

  @Deprecated
  @Override
  public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException
  {
    // handler.unparsedEntityDecl(name, publicId, systemId, notationName);
  }

  @Deprecated
  public void startDTD(java.lang.String name, java.lang.String publicId, java.lang.String systemId)
  {
    handler.startDTD(name, publicId, systemId);
  }

  @Deprecated
  public void endDTD()
  {
    // Ignore.
  }

  @Deprecated
  public void startEntity(java.lang.String name)
  {
    // Ignore.
  }

  @Deprecated
  public void endEntity(java.lang.String name)
  {
    // Ignore.
  }

  @Deprecated
  public void startCDATA()
  {
    handler.startCDATA();
  }

  @Deprecated
  public void endCDATA()
  {
    handler.endCDATA();
  }

  @Deprecated
  public void comment(char [] characters, int start, int length) throws SAXException
  {
    handler.comment(characters, start, length);
  }
}
