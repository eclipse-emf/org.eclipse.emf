/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLDefaultHandler;
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
  protected Map<?, ?> options;
  protected boolean namespaceAware;

  public XMLLoadImpl(XMLHelper helper)
  {
    this.helper = helper;
  }

  protected void handleErrors() throws IOException
  {
    if (!resource.getErrors().isEmpty())
    {
      Resource.Diagnostic error = resource.getErrors().get(0);
      if (error instanceof Exception)
      {
        throw new Resource.IOWrappedException((Exception)error);
      }
      else
      {
        throw new IOException(error.getMessage());
      }
    }
  }

  /**
   * Start parsing with the default handler; either XMI
   * or XML.
   */
  public void load(XMLResource resource, InputStream inputStream, Map<?, ?> options) throws IOException
  {
    if (inputStream instanceof URIConverter.Readable)
    {
      URIConverter.Readable readable = (URIConverter.Readable)inputStream;
      resource.setEncoding(readable.getEncoding());
      
      InputSource inputSource =  new InputSource(readable.asReader());
      if (resource.getURI() != null)
      {
        String resourceURI = resource.getURI().toString();
        inputSource.setPublicId(resourceURI);
        inputSource.setSystemId(resourceURI);
        inputSource.setEncoding(resource.getEncoding());
      }
      load(resource, inputSource, options);
      return;
    }
    
    this.resource = resource;
    is = inputStream;
    this.options = options;
    XMLParserPool pool = (XMLParserPool)options.get(XMLResource.OPTION_USE_PARSER_POOL);
    @SuppressWarnings("unchecked") Map<String, Boolean> parserFeatures = (Map<String, Boolean>)options.get(XMLResource.OPTION_PARSER_FEATURES);
    @SuppressWarnings("unchecked") Map<String, ?> parserProperties = (Map<String, ?>)options.get(XMLResource.OPTION_PARSER_PROPERTIES);
    parserFeatures = (parserFeatures == null) ? Collections.<String, Boolean>emptyMap() : parserFeatures;
    parserProperties = (parserProperties == null) ? Collections.<String, Object>emptyMap() : parserProperties;

    // HACK: reading encoding
    String encoding = null;
    if (!Boolean.FALSE.equals(options.get(XMLResource.OPTION_USE_DEPRECATED_METHODS)))
    {
      encoding = getEncoding();
      resource.setEncoding(encoding);
    }
    try
    {
      SAXParser parser;  
      DefaultHandler handler;

      if (pool != null)
      {
        // use the pool to retrieve the parser
        parser = pool.get(parserFeatures, parserProperties, Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)));
        handler = (DefaultHandler)pool.getDefaultHandler(resource, this, helper, options);
      } 
      else 
      {
        parser = makeParser();
        handler = makeDefaultHandler();
        // set features and properties
        if (parserFeatures != null)
        {
          for (Map.Entry<String, Boolean> entry : parserFeatures.entrySet())
          {
            parser.getXMLReader().setFeature(entry.getKey(), entry.getValue());
          }
        }
        if (parserProperties !=null)
        {
          for (Map.Entry<String, ?> entry : parserProperties.entrySet())
          {
            parser.getXMLReader().setProperty(entry.getKey(), entry.getValue());
          }
        }
      }

      InputSource inputSource = new InputSource(is);
      if (resource.getURI() != null)
      {
        String resourceURI = resource.getURI().toString();
        inputSource.setPublicId(resourceURI);
        inputSource.setSystemId(resourceURI);
        inputSource.setEncoding(encoding);
      }
    
      // set lexical handler
      if (Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)))
      {
        if (parserProperties == null || parserProperties.get(SAX_LEXICAL_PROPERTY) == null) 
        {
          parser.setProperty(SAX_LEXICAL_PROPERTY, handler);
        }
      }
      
      parser.parse(inputSource, handler);
      
      // release parser back to the pool
      if (pool != null)
      {
        pool.release(parser, parserFeatures, parserProperties, Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)));
        pool.releaseDefaultHandler((XMLDefaultHandler)handler, options);
      }
      
      helper = null;
      handleErrors();
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
  
  public void load(XMLResource resource, InputSource inputSource, Map<?, ?> options) throws IOException
  {
    this.resource = resource;
   
    this.options = options;
    XMLParserPool pool = (XMLParserPool)options.get(XMLResource.OPTION_USE_PARSER_POOL);
    @SuppressWarnings("unchecked") Map<String, Boolean> parserFeatures = (Map<String, Boolean>)options.get(XMLResource.OPTION_PARSER_FEATURES);
    @SuppressWarnings("unchecked") Map<String, ?> parserProperties = (Map<String, ?>)options.get(XMLResource.OPTION_PARSER_PROPERTIES);
    parserFeatures = (parserFeatures == null) ? Collections.<String, Boolean>emptyMap() : parserFeatures;
    parserProperties = (parserProperties == null) ? Collections.<String, Object>emptyMap() : parserProperties;

    // Don't read encoding - rely on XML parser to provide one via Locator2

    try
    {
      SAXParser parser;  
      DefaultHandler handler;

      if (pool != null)
      {
        // use the pool to retrieve the parser
        parser = pool.get(parserFeatures, parserProperties, Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)));
        handler = (DefaultHandler)pool.getDefaultHandler(resource, this, helper, options);
      } 
      else 
      {
        parser = makeParser();
        handler = makeDefaultHandler();
        // set features and properties
        if (parserFeatures != null)
        {
          for (Map.Entry<String, Boolean> feature : parserFeatures.entrySet())
          {
            parser.getXMLReader().setFeature(feature.getKey(),  feature.getValue());
          }
        }
        if (parserProperties !=null)
        {
          for (Map.Entry<String, ?> property : parserProperties.entrySet())
          {
            parser.getXMLReader().setProperty(property.getKey(), property.getValue());
          }
        }
      }
  
      // set lexical handler
      if (Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)))
      {
        if (parserProperties == null || parserProperties.get(SAX_LEXICAL_PROPERTY) == null) 
        {
          parser.setProperty(SAX_LEXICAL_PROPERTY, handler);
        }
      }
      
      parser.parse(inputSource, handler);

      // release parser back to the pool
      if (pool != null)
      {
        pool.release(parser, parserFeatures, parserProperties, Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)));
        pool.releaseDefaultHandler((XMLDefaultHandler)handler, options);
      }

      helper = null;
      handleErrors();
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
  
  public XMLDefaultHandler createDefaultHandler()
  {
    return (XMLDefaultHandler)makeDefaultHandler();
  }

  protected DefaultHandler makeDefaultHandler()
  {
    return new SAXXMLHandler(resource, helper, options);
  }

  /** 
   * @deprecated since 2.2
   * The encoding will be reported by the parser using SAX 2 Locator
   */
  @Deprecated
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

    if (totalBytesRead < 0)
    {
      buffer = new byte[0];
    }
    else if (totalBytesRead < BUFFER_SIZE)
    {
      byte[] smallerBuffer = new byte[totalBytesRead];
      System.arraycopy(buffer, 0, smallerBuffer, 0, totalBytesRead);
      buffer = smallerBuffer;
    }
      
    is.reset();
    return buffer;
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.ecore.xmi.XMLLoad#load(org.eclipse.emf.ecore.xmi.XMLResource, org.w3c.dom.Node, java.util.Map)
   */
  public void load(XMLResource resource, Node node, Map<?, ?> options) throws IOException
  {
    this.resource = resource;
    this.options = options;
    this.namespaceAware = Boolean.FALSE.equals(options.get(XMLResource.OPTION_USE_DEPRECATED_METHODS));
    DefaultHandler handler;
    XMLParserPool pool = (XMLParserPool)options.get(XMLResource.OPTION_USE_PARSER_POOL);
    if (pool != null)
    {
      handler = (DefaultHandler)pool.getDefaultHandler(resource, this, helper, options);
    }
    else
    {
      handler = makeDefaultHandler();
    }
    LexicalHandler lexicalHandler = null;

    if (Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER)))
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
        if (Boolean.TRUE.equals(options.get(XMLResource.OPTION_DOM_USE_NAMESPACES_IN_SCOPE)))
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
    
    if (pool != null)
    {
      pool.releaseDefaultHandler((XMLDefaultHandler)handler, options);
    }
    
    attributesProxy = null;
    handler = null;
    lexicalHandler = null;
    helper = null;

    handleErrors();
  }
  
  /**
   * Special case: traversing root element using namespaces in scope
   */
  protected void traverseElement(Element element, AttributesProxy attributesProxy, DefaultHandler handler, LexicalHandler lexicalHandler) throws SAXException
  {   
    // temporary structure to hold node's attributes + namespaces in scope
    AttributesImpl attrs = new AttributesImpl();
    Set<String> prefixes = new HashSet<String>();
    
    // record node's attributes
    if (element.hasAttributes())
    {
      NamedNodeMap attributes = element.getAttributes();
      for (int i = 0; i < attributes.getLength(); i++)
      {
        Node attr = attributes.item(i);
        String namespaceURI = attr.getNamespaceURI();
        if (namespaceURI == null)
        {
          namespaceURI = "";
        }
        String nodeName = attr.getNodeName();
        String localName = attr.getLocalName();
        String nodeValue = attr.getNodeValue();
        if (ExtendedMetaData.XMLNS_URI.equals(namespaceURI))
        {
          // Include only non-duplicate namespace declarations.
          //
          if (namespaceAware)
          {
            if (prefixes.add(localName))
            {
              handler.startPrefixMapping(localName, nodeValue);
            }
          }
          else if (attrs.getIndex(nodeName) < 0)
          {
            attrs.addAttribute(namespaceURI, localName, nodeName, "CDATA", nodeValue);
          }
        }
        else
        {
          attrs.addAttribute(namespaceURI, localName, nodeName, "CDATA", nodeValue);
        }
      }
    }
    
    // record namespaces in scope
    //
    for (Node parent = element.getParentNode();  parent != null && parent.getNodeType() != Node.DOCUMENT_NODE;  parent = parent.getParentNode())
    {
      if (parent.hasAttributes())
      {
        NamedNodeMap attributes = parent.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++)
        {
          Node attr = attributes.item(i);
          String namespaceURI = attr.getNamespaceURI();
          if (ExtendedMetaData.XMLNS_URI.equals(namespaceURI))
          {          
            // Include only non-duplicate namespace declarations.
            //
            String localName = attr.getLocalName();
            String nodeValue = attr.getNodeValue();
            if (namespaceAware)
            {
              if (prefixes.add(localName))
              {
                handler.startPrefixMapping(localName, nodeValue);
              }
            }
            else 
            {
              String nodeName = attr.getNodeName();
              if (attrs.getIndex(nodeName) < 0)
              {
                attrs.addAttribute(namespaceURI, localName, nodeName, "CDATA", nodeValue);
              }
            }
          }
        }
      }
    }  

    // traverse element node
    String namespaceURI = element.getNamespaceURI();
    if (namespaceURI == null)
    {
      namespaceURI = "";
    }
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
          DocumentType doctype = document.getDoctype();
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
        AttributesImpl filteredAttributes = null;
        NamedNodeMap attributes = node.getAttributes();
        if (namespaceAware)
        {
          for (int i = 0, length = attributes.getLength(); i < length; i++)
          {
            Node attr = attributes.item(i);
            String namespaceURI = attr.getNamespaceURI();
            if (ExtendedMetaData.XMLNS_URI.equals(namespaceURI))
            {
              handler.startPrefixMapping(attr.getLocalName(), attr.getNodeValue());
              if (filteredAttributes == null)
              {
                filteredAttributes = new AttributesImpl();
                for (int j = 0; j < i; ++j)
                {
                  attr = attributes.item(j);
                  namespaceURI = attr.getNamespaceURI();
                  if (namespaceURI == null)
                  {
                    namespaceURI = "";
                  }
                  filteredAttributes.addAttribute(namespaceURI, attr.getLocalName(), attr.getNodeName(), "CDATA", attr.getNodeValue());
                }
              }
            }
            else if (filteredAttributes != null)
            {
              if (namespaceURI == null)
              {
                namespaceURI = "";
              }
              filteredAttributes.addAttribute(namespaceURI, attr.getLocalName(), attr.getNodeName(), "CDATA", attr.getNodeValue());
            }
          }
        }
        if (filteredAttributes == null)
        {
          attributesProxy.setAttributes(attributes);
        }
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI == null)
        {
          namespaceURI = "";
        }
        String localName = node.getLocalName();
        String qname = node.getNodeName();   
        
        handler.startElement(namespaceURI, localName, qname, filteredAttributes == null ? attributesProxy: filteredAttributes);

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
        break;
      }
      case Node.PROCESSING_INSTRUCTION_NODE:
      {
        ProcessingInstruction pi = (ProcessingInstruction) node;
        handler.processingInstruction(pi.getTarget(), pi.getData());
        break;
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
