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
 * $Id: XMLLoadImpl.java,v 1.9 2005/07/21 19:47:33 elena Exp $
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

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
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
    parserFeatures = (parserFeatures == null) ? Collections.EMPTY_MAP : parserFeatures;
    parserProperties = (parserProperties == null) ? Collections.EMPTY_MAP : parserProperties;

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
  
  
  public void load(XMLResource resource, InputSource inputSource, Map options) throws IOException
  {
    this.resource = resource;
   
    this.options = options;
    XMLParserPool pool = (XMLParserPool)options.get(XMLResource.OPTION_USE_PARSER_POOL);
    Map parserFeatures = (Map) options.get(XMLResource.OPTION_PARSER_FEATURES);
    Map parserProperties = (Map)options.get(XMLResource.OPTION_PARSER_PROPERTIES);
    parserFeatures = (parserFeatures == null) ? Collections.EMPTY_MAP : parserFeatures;
    parserProperties = (parserProperties == null) ? Collections.EMPTY_MAP : parserProperties;

    // Don't read encoding - rely on XML parser to provide one via Locator2

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

  /* (non-Javadoc)
   * @see org.eclipse.emf.ecore.xmi.XMLLoad#load(org.eclipse.emf.ecore.xmi.XMLResource, org.w3c.dom.Node, java.util.Map)
   */
  public void load(XMLResource resource, Node node, Map options) throws IOException
  {
    this.resource = resource;
    this.options = options;
    DefaultHandler handler = makeDefaultHandler();
    LexicalHandler lexicalHandler = null;

    if (options != null && Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)))
    {
      lexicalHandler = (LexicalHandler)handler;
    }

    AttributesProxy attributesProxy = new AttributesProxy();
    try
    {
      short type = node.getNodeType();
      if (type == Node.ELEMENT_NODE)
      {
        handler.startDocument();
        if (options != null && Boolean.TRUE.equals(options.get(XMLResource.OPTION_DOM_USE_NAMESPACES_IN_SCOPE)))
        {
          traverseElement((Element)node, attributesProxy, handler, lexicalHandler);
        }
        else
        {
          traverse(node, attributesProxy, handler, lexicalHandler);
        }
        handler.endDocument();      
      }
      else
      {
        traverse(node, attributesProxy, handler, lexicalHandler);
      }
    }
    catch (SAXException e)
    {
      // ignore
    }

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

    if (handler instanceof SAXWrapper)
    {
      ((SAXWrapper)handler).handler = null;
    }
    
    attributesProxy = null;
    handler = null;
    lexicalHandler = null;
    helper = null;
  }
  
  /**
   * Special case: traversing root element using namespaces in scope
   */
  protected void traverseElement(Element element, AttributesProxy attributesProxy, DefaultHandler handler, LexicalHandler lexicalHandler) throws SAXException
  {   
    // temporary structure to hold node's attributes + namespaces in scope
    AttributesImpl attrs = new AttributesImpl();
    
    // record node's attributes
    if (element.hasAttributes())
    {
      NamedNodeMap attributes = element.getAttributes();
      for (int i = 0; i < attributes.getLength(); i++)
      {
        Node attr = attributes.item(i);
        String namespaceURI = attr.getNamespaceURI();
        if (ExtendedMetaData.XMLNS_URI.equals(namespaceURI))
        {
          // add non-duplicate namespace declaration
          if (attrs.getIndex(attr.getNodeName()) < 0)
          {
            attrs.addAttribute("", "", attr.getNodeName(), "CDATA", attr.getNodeValue());
          }
        }
        else
        {
          attrs.addAttribute(namespaceURI, attr.getLocalName(), attr.getNodeName(), "CDATA", attr.getNodeValue());
        }
      }
    }
    
    Node parent = element.getParentNode(); 
    // record namespaces in scope
    while (parent.getNodeType() != Node.DOCUMENT_NODE)
    {
      if (parent.hasAttributes())
      {
        NamedNodeMap attributes = parent.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++)
        {
          Node attr = attributes.item(i);
          // add non-duplicate namespace declaration
          if (ExtendedMetaData.XMLNS_URI.equals(attr.getNamespaceURI()) && attrs.getIndex(attr.getNodeName()) < 0)
          {          
            attrs.addAttribute("", "", attr.getNodeName(), "CDATA", attr.getNodeValue());
          }
        }
      }
      parent = parent.getParentNode();
    }  

    // traverse element node
    String namespaceURI = element.getNamespaceURI();
    String localName = element.getLocalName();
    String qname = element.getNodeName();   
    
    handler.startElement(namespaceURI, localName , qname, attrs);
    Node child = element.getFirstChild();
    while (child != null)
    {
      traverse(child, attributesProxy, handler, lexicalHandler);
      child = child.getNextSibling();
    }
    handler.endElement(namespaceURI, localName , qname);
  }

  protected void traverse(Node node, AttributesProxy attributesProxy, DefaultHandler handler, LexicalHandler lexicalHandler) throws SAXException
  {

    if (node == null)
    {
      return;
    }

    short type = node.getNodeType();
    switch (type)
    {
      case Node.DOCUMENT_NODE:
      {
        Document document = (Document)node;
        handler.startDocument();
        Node root = document.getDocumentElement();
        if (lexicalHandler != null)
        {
          DocumentType doctype = (DocumentType)document.getDoctype();
          if (doctype != null)
          {
            String publicId = doctype.getPublicId();
            String systemId = doctype.getSystemId();
            lexicalHandler.startDTD(root.getNodeName(), publicId, systemId);
          }
        }
        traverse(root, attributesProxy, handler, lexicalHandler);
        handler.endDocument();
        break;
      }
      case Node.ELEMENT_NODE:
      {
        attributesProxy.setAttributes(node.getAttributes());
        String namespaceURI = node.getNamespaceURI();
        String localName = node.getLocalName();
        String qname = node.getNodeName();   
        
        handler.startElement(namespaceURI, localName, qname, attributesProxy);

        Node child = node.getFirstChild();
        while (child != null)
        {
          traverse(child, attributesProxy, handler, lexicalHandler);
          child = child.getNextSibling();
        }
        handler.endElement(namespaceURI, localName, qname);
        break;
      }

      case Node.CDATA_SECTION_NODE:
      {
        if (lexicalHandler != null)
        {
          lexicalHandler.startCDATA();
        }
        char[] chars = ((CDATASection)node).getData().toCharArray();
        handler.characters(chars, 0, chars.length);
        if (lexicalHandler != null)
        {
          lexicalHandler.endCDATA();
        }
        break;
      }
      case Node.TEXT_NODE:
      {
        char[] chars = node.getNodeValue().toCharArray();
        handler.characters(chars, 0, chars.length);
        break;
      }
      case Node.COMMENT_NODE:
      {
        if (lexicalHandler != null)
        {
          char[] chars = ((Comment)node).getData().toCharArray();
          lexicalHandler.comment(chars, 0, chars.length);
        }
      }
    }
  }

  protected static final class AttributesProxy implements Attributes
  {
    /** DOM attributes. */
    protected NamedNodeMap attributes;

    /** Sets the DOM attributes. */
    public void setAttributes(NamedNodeMap attributes)
    {
      this.attributes = attributes;
    }

    public int getLength()
    {
      return attributes.getLength();
    }

    public String getQName(int index)
    {
      Node node = attributes.item(index);
      return (node != null) ? node.getNodeName() : null;
    }

    public String getURI(int index)
    {
      Node node = attributes.item(index);
      if (node != null)
      {
        String namespaceURI = node.getNamespaceURI();
        if (ExtendedMetaData.XMLNS_URI.equals(namespaceURI))
        {
          return "";
        }
        return namespaceURI;
      }
      return null;
    }

    public String getLocalName(int index)
    {
      Node node = attributes.item(index);
      if (node != null)
      {
        String prefix = node.getPrefix();
        if (ExtendedMetaData.XMLNS_PREFIX.equals(prefix))
        {
          return "";
        }
        return node.getLocalName();
      }
      return null;
    }

    public String getType(int i)
    {
      return "CDATA";
    }

    public String getType(String name)
    {
      return "CDATA";
    }

    public String getType(String uri, String localName)
    {
      return "CDATA";
    }

    public String getValue(int i)
    {
      Node node = attributes.item(i);
      return (node != null) ? node.getNodeValue() : null;
    }

    public String getValue(String name)
    {
      Node node = attributes.getNamedItem(name);
      return (node != null) ? node.getNodeValue() : null;
    }

    public String getValue(String uri, String localName)
    {
      Node node = attributes.getNamedItemNS(uri, localName);
      return (node != null) ? node.getNodeValue() : null;
    }

    public int getIndex(String qName)
    {
      Node node = attributes.getNamedItem(qName);
      if (node != null)
      {
        for (int i = 0; i < attributes.getLength(); i++)
        {
          Node item = attributes.item(i);
          if (item == node)
          {
            return i;
          }
        }
      }
      return -1;
    }

    public int getIndex(String uri, String localPart)
    {
      Node node = attributes.getNamedItemNS(uri, localPart);
      if (node != null)
      {
        for (int i = 0; i < attributes.getLength(); i++)
        {
          Node item = attributes.item(i);
          if (item == node)
          {
            return i;
          }
        }
      }
      return -1;
    }
  } // class AttributesProxy

} // XMLLoad
