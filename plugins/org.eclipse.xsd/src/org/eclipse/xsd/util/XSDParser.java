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
 * $Id: XSDParser.java,v 1.18 2008/12/22 14:25:48 emerks Exp $
 */
package org.eclipse.xsd.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.WeakHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.impl.XSDSchemaImpl;


/**
 * The <b>SAX Parser</b> for the model.
 * This handles the conversion of SAX parse exceptions to {@link XSDDiagnostic}s.
 * It also computes line column information, 
 * which is stored in a map of each node to its user data.
 * This class is not intended for reuse outside of the model implementation and is subject to change.
 */
public class XSDParser extends DefaultHandler implements LexicalHandler
{
  protected static final Map<Node, Map<Object, Object>> userDataMap = Collections.synchronizedMap(new WeakHashMap<Node, Map<Object, Object>>());

  private static final class LocationMap extends AbstractMap<Object,Object>
  {
    protected static final int UNSET = Integer.MAX_VALUE;
    protected Map<Object, Object> delegateMap;
    private int startLine = UNSET;
    private int startColumn = UNSET;
    private int endLine = UNSET;
    private int endColumn = UNSET;

    @Override
    public Set<java.util.Map.Entry<Object, Object>> entrySet()
    {
      return getDelegateMap().entrySet();
    }

    protected Map<Object, Object> getDelegateMap()
    {
      if (delegateMap == null)
      {
        delegateMap = new HashMap<Object, Object>();
        if (startLine != UNSET)
        {
          delegateMap.put("startLine", startLine);
        }
        if (startColumn != UNSET)
        {
          delegateMap.put("startColumn", startColumn);
        }
        if (endLine != UNSET)
        {
          delegateMap.put("endLine", endLine);
        }
        if (endColumn != UNSET)
        {
          delegateMap.put("endColumn", endColumn);
        }
      }
      return delegateMap;
    }

    protected int objectToInt(Object value)
    {
      return value == null ? UNSET : (Integer)value;
    }

    protected Object intToObject(int value)
    {
      return value == UNSET ? null : value;
    }

    @Override
    public Object put(Object key, Object value)
    {
      Object result;
      if (delegateMap == null)
      {
        if ("startLine".equals(key))
        {
          result = intToObject(startLine);
          startLine  = objectToInt(value);
        }
        else if ("endLine".equals(key))
        {
          result = intToObject(endLine);
          endLine  = objectToInt(value);
        }
        else if ("startColumn".equals(key))
        {
          result = intToObject(startColumn);
          startColumn  = objectToInt(value);
        }
        else if ("endColumn".equals(key))
        {
          result = intToObject(endColumn);
          endColumn  = objectToInt(value);
        }
        else
        {
          result = getDelegateMap().put(key, value);
        }
      }
      else
      {
        result = getDelegateMap().put(key, value);
      }
      return result;
    }

    public int getStartLine()
    {
      return delegateMap == null ? startLine : objectToInt(delegateMap.get("startLine"));
    }

    public void setStartLine(int startLine)
    {
      if (delegateMap == null)
      {
        this.startLine = startLine;
      }
      else
      {
        delegateMap.put("startLine", startLine);
      }
    }

    public int getStartColumn()
    {
      return delegateMap == null ? startColumn : objectToInt(delegateMap.get("startColumn"));
    }

    public void setStartColumn(int startColumn)
    {
      if (delegateMap == null)
      {
        this.startColumn = startColumn;
      }
      else
      {
        delegateMap.put("startColumn", startColumn);
      }
    }

    public int getEndLine()
    {
      return delegateMap == null ? endLine : objectToInt(delegateMap.get("endLine"));
    }

    public void setEndLine(int endLine)
    {
      if (delegateMap == null)
      {
        this.endLine = endLine;
      }
      else
      {
        delegateMap.put("endLine", endLine);
      }
    }

    public int getEndColumn()
    {
      return delegateMap == null ? endColumn : objectToInt(delegateMap.get("endColumn"));
    }

    public void setEndColumn(int endColumn)
    {
      if (delegateMap == null)
      {
        this.endColumn = endColumn;
      }
      else
      {
        delegateMap.put("endColumn", endColumn);
      }
    }
  }

  /**
   * Returns the user data associated with the node.
   * If the node has no user data, a new empty map is created.
   * @param node the node to query.
   * @return the user data associated with the node.
   */
  public static Map<Object, Object> getUserData(Node node)
  {
    return getLocationMap(node);
  }

  private static LocationMap getLocationMap(Node node)
  {
    LocationMap result = (LocationMap)userDataMap.get(node);
    if (result == null)
    {
      result = new LocationMap();
      userDataMap.put(node, result);
    }
    return result;
  }

  /**
   * Returns the line at which the given node starts.
   * @param node the node to query.
   * @return the line at which the given node starts.
   */
  public static int getStartLine(Node node)
  {
    int result = getLocationMap(node).getStartLine();
    return result == LocationMap.UNSET ? 1 : result;
  }

  /**
   * Sets the line at which the given node starts.
   * @param node the node to set.
   * @param value the line at which the given node starts.
   */
  public static void setStartLine(Node node, int value)
  {
    getLocationMap(node).setStartLine(value);
  }

  /**
   * Returns the column at which the given node starts.
   * @param node the node to query.
   * @return the column at which the given node starts.
   */
  public static int getStartColumn(Node node)
  {
    int result = getLocationMap(node).getStartColumn();
    return result == LocationMap.UNSET ? 1 : result;
  }

  /**
   * Sets the column at which the given node starts.
   * @param node the node to set.
   * @param value the column at which the given node starts.
   */
  public static void setStartColumn(Node node, int value)
  {
    getLocationMap(node).setStartColumn(value);
  }

  /**
   * Returns the line at which the given node ends.
   * @param node the node to query.
   * @return the line at which the given node ends.
   */
  public static int getEndLine(Node node)
  {
    int result = getLocationMap(node).getEndLine();
    return result == LocationMap.UNSET ? 1 : result;
  }

  /**
   * Sets the line at which the given node ends.
   * @param node the node to set.
   * @param value the line at which the given node ends.
   */
  public static void setEndLine(Node node, int value)
  {
    getLocationMap(node).setEndLine(value);
  }

  /**
   * Returns the column at which the given node ends.
   * @param node the node to query.
   * @return the column at which the given node ends.
   */
  public static int getEndColumn(Node node)
  {
    int result = getLocationMap(node).getEndColumn();
    return result == LocationMap.UNSET ? 1 : result;
  }

  /**
   * Sets the column at which the given node ends.
   * @param node the node to set.
   * @param value the column at which the given node ends.
   */
  public static void setEndColumn(Node node, int value)
  {
    getLocationMap(node).setEndColumn(value);
  }

  protected XSDFactory xsdFactory = XSDFactory.eINSTANCE;
  protected XSDSchema xsdSchema;
  protected List<XSDDiagnostic> xsdDiagnostics = new ArrayList<XSDDiagnostic>();
  protected SAXParser saxParser;
  protected JAXPPool jaxpPool;
  protected Document document;
  protected Stack<Element> stack = new Stack<Element>();
  protected Element element;
  protected Locator locator;
  protected int line;
  protected int column;
  protected String encoding;
  protected StringBuilder cdata;
  protected CDATASection cdataSection;

  /**
   * @deprecated since 2.2
   *
   */
  @Deprecated
  public XSDParser()
  {
    try 
    {
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
      saxParserFactory.setNamespaceAware(true);
      saxParserFactory.setValidating(false);

      saxParserFactory.setFeature("http://xml.org/sax/features/validation", false);
      saxParserFactory.setFeature("http://xml.org/sax/features/namespaces", true);
      saxParserFactory.setFeature("http://xml.org/sax/features/namespace-prefixes", true);

      saxParser = saxParserFactory.newSAXParser();

      saxParser.setProperty("http://xml.org/sax/properties/lexical-handler", this);
    } 
    catch (SAXException exception)
    {
      fatalError(exception);
    }
    catch (ParserConfigurationException exception) 
    {
      fatalError(exception);
    }
  }
  
  public XSDParser(Map<?, ?> options)
  {
    JAXPConfiguration config = null;
    if (options != null)
    {
      jaxpPool = (JAXPPool)options.get(XSDResourceImpl.XSD_JAXP_POOL);
      config = (JAXPConfiguration)options.get(XSDResourceImpl.XSD_JAXP_CONFIG);
    }
    try
    {
      if (jaxpPool == null)
      {      
        if (config != null)
        {
          saxParser = config.createSAXParser(this);
        }
        else
        {
          saxParser = new DefaultJAXPConfiguration().createSAXParser(this);
        }
      }
      else
      {
        saxParser = jaxpPool.getSAXParser(this);
      }
    }
    catch (SAXException exception)
    {
      fatalError(exception);
    }
    catch (ParserConfigurationException exception)
    {
      fatalError(exception);
    }
  }

  public void parse(String uri)
  {
    try
    {
      saxParser.parse(new InputSource(uri), this);
    }
    catch (IOException exception)
    {
      fatalError(exception);
    }
    catch (SAXException exception)
    {
      if (xsdDiagnostics.isEmpty())
      {
        fatalError(exception);
      }
    }
    finally
    {
      if (jaxpPool != null)
      {
        jaxpPool.releaseSAXParser(saxParser);
      }
    }
  }

  public void parseString(String input)
  {
    parse(new InputSource(new StringReader(input)));
  }

  public void parse(InputSource inputSource)
  {
    try
    {
      saxParser.parse(inputSource, this);
    }
    catch (IOException exception)
    {
      fatalError(exception);
    }
    catch (SAXException exception)
    {
      if (xsdDiagnostics.isEmpty())
      {
        fatalError(exception);
      }
    }
    finally
    {
      if (jaxpPool != null)
      {
        jaxpPool.releaseSAXParser(saxParser);
      }
    }
  }

  public void parse(InputStream inputStream)
  {
    try
    {
      saxParser.parse(new InputSource(inputStream), this);
    }
    catch (IOException exception)
    {
      fatalError(exception);
    }
    catch (SAXException exception)
    {
      if (xsdDiagnostics.isEmpty())
      {
        fatalError(exception);
      }
    }
    finally
    {
      if (jaxpPool != null)
      {
        jaxpPool.releaseSAXParser(saxParser);
      }
    }
  }

  public String getEncoding()
  {
    if (locator != null)
    {
      try 
      {
        Method getEncodingMethod = locator.getClass().getMethod("getEncoding", new Class[]{});
        if (getEncodingMethod != null)
        {
          encoding = (String)getEncodingMethod.invoke(locator);
        }
      }
      catch (Exception e) 
      {
        // If we can't find it, there's nothing we can do...
      }
    }

    return encoding;
  }

  public Document getDocument()
  {
    return document;
  }

  public void setSchema(XSDSchema xsdSchema)
  {
    if (document != null)
    {
      this.xsdSchema = xsdSchema;
      Element element = document.getDocumentElement();
      if (element != null)
      {
        xsdSchema.setElement(element);
      }
      xsdSchema.clearDiagnostics();
      XSDResourceImpl.assignDiagnostics(xsdSchema, xsdDiagnostics);
      XSDResourceImpl resource = (XSDResourceImpl)xsdSchema.eResource();
      if (resource != null && getEncoding() != null)
      {
        resource.getDefaultSaveOptions().put(XSDResourceImpl.XSD_ENCODING, getEncoding());
      }
    }
  }

  public XSDSchema getSchema()
  {
    if (xsdSchema == null && document != null)
    {
      Element element = document.getDocumentElement();
      xsdSchema = element == null ? xsdFactory.createXSDSchema() : XSDSchemaImpl.createSchema(element);
      XSDResourceImpl.assignDiagnostics(xsdSchema, xsdDiagnostics);
    }
    return xsdSchema;
  }
  
  public Collection<XSDDiagnostic> getDiagnostics()
  {
    return xsdDiagnostics;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
  {
    Element newElement = document.createElementNS(uri, qName);
    for (int i = 0, length = attributes.getLength(); i < length; ++i)
    {
      String attributeURI = attributes.getURI(i);
      String attributeQName = attributes.getQName(i);
      String attributeValue = attributes.getValue(i);
      if (attributeQName.equals("xmlns") || attributeQName.startsWith("xmlns:"))
      {
        attributeURI = XSDConstants.XMLNS_URI_2000;
      }
      else if ("".equals(attributeURI))
      {
        attributeURI = null;
      }
      newElement.setAttributeNS(attributeURI, attributeQName, attributeValue);
    }

    if (stack.isEmpty())
    {
      document.appendChild(newElement);
    }
    else
    {
      element.appendChild(newElement);
    }

    stack.push(element);
    element = newElement;

    LocationMap extendedAttributes = getLocationMap(element);
    extendedAttributes.setStartLine(line);
    extendedAttributes.setStartColumn(column);

    saveLocation();
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException
  {
    saveLocation();

    LocationMap extendedAttributes = getLocationMap(element);
    extendedAttributes.setEndLine(line);
    extendedAttributes.setEndColumn(column);

    element = stack.pop();
  }

  @Override
  public void setDocumentLocator(Locator locator)
  {
    this.locator = locator;
    super.setDocumentLocator(locator);
  }

  @Override
  public void startDocument()
  {
    saveLocation();
    document = createDocument();
    xsdSchema = null;
    stack.clear();

    getEncoding();
  }

  public static Document createDocument()
  {
    try
    {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      documentBuilderFactory.setNamespaceAware(true);
      documentBuilderFactory.setValidating(false);
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      return documentBuilder.newDocument();
    }
    catch (ParserConfigurationException exception)
    {
      XSDPlugin.INSTANCE.log(exception);
      return null;
    }
  }

  @Override
  public void endDocument()
  {
    element = null;
    saveLocation();
    this.locator = null;
  }

  @Override
  public void characters(char [] characters, int start, int length) throws SAXException
  {
    if (cdata != null)
    {
      cdata.append(characters, start, length);
    }
    else
    {
      int lineFeed = 0;
      int carriageReturn = 0;
      for (int i = 0; i < length; ++i)
      {
        char character = characters[start + i];
        if (character == '\n')
        {
          ++lineFeed;
          column = 1;
        }
        else if (character == '\r')
        {
          ++carriageReturn;
          column = 1;
        }
        else if (character == ' ' && character == '\t')
        {
          ++column;
        }
        else
        {
          // Put the leading whitespace in its own text node.
          if (i != 0)
          {
            Text textNode = document.createTextNode(new String(characters, start, i));
            element.appendChild(textNode);
          }
          
          // Create a next node that starts with the non-whitespace.
          //
          line += Math.max(lineFeed, carriageReturn);
          Text textNode = document.createTextNode(new String(characters, start + i, length - i));
          element.appendChild(textNode);
          LocationMap extendedAttributes = getLocationMap(textNode);
          extendedAttributes.setStartLine(line);
          extendedAttributes.setStartColumn(column);
          saveLocation();
          extendedAttributes.setEndLine(line);
          extendedAttributes.setEndColumn(column);
          return;
        }
      }
      Text textNode = document.createTextNode(new String(characters, start, length));
      element.appendChild(textNode);
      saveLocation();
    }
  }

  public void comment(char [] characters, int start, int length) throws SAXException
  {
    Comment commentNode = document.createComment(new String(characters, start, length));
    if (element == null)
    {
      document.appendChild(commentNode);
    }
    else
    {
      element.appendChild(commentNode);
    }
    saveLocation();
  }

  public void startCDATA() 
  {
    cdata = new StringBuilder();
    cdataSection = document.createCDATASection("");
    LocationMap extendedAttributes = getLocationMap(cdataSection);
    extendedAttributes.setStartLine(line);
    extendedAttributes.setStartColumn(column);
  }

  public void endCDATA() 
  {
    cdataSection.setData(cdata.toString());
    element.appendChild(cdataSection);
    LocationMap extendedAttributes = getLocationMap(cdataSection);
    cdata = null;
    saveLocation();
    extendedAttributes.setEndLine(line);
    extendedAttributes.setEndColumn(column);
  }

  public void startDTD(String name, String publicId, String systemId) 
  {
    // Ignore
  }

  public void endDTD() 
  {
    // Ignore
  }

  public void startEntity(String name) 
  {
    // Ignore
  }

  public void endEntity(String name) 
  {
    // Ignore
  }

  @Override
  public void processingInstruction(String target, String data) 
  {
    Node processingInstruction = document.createProcessingInstruction(target, data);
    if (stack.isEmpty())
    {
      document.appendChild(processingInstruction);
    }
    else
    {
      element.appendChild(processingInstruction);
    }
  }

  protected void fatalError(IOException exception)
  {
    XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
    xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.FATAL_LITERAL);
    xsdDiagnostic.setMessage(XSDPlugin.INSTANCE.getString("_UI_IOError_message", new Object [] { exception.getLocalizedMessage() }));
    xsdDiagnostics.add(xsdDiagnostic);
  }

  protected void fatalError(ParserConfigurationException exception)
  {
    XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
    xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.FATAL_LITERAL);
    xsdDiagnostic.setMessage(XSDPlugin.INSTANCE.getString("_UI_ParserError_message", new Object [] { exception.getLocalizedMessage() }));
    xsdDiagnostics.add(xsdDiagnostic);
  }

  public void fatalError(SAXException exception)
  {
    XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
    xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.FATAL_LITERAL);
    xsdDiagnostic.setMessage(XSDPlugin.INSTANCE.getString("_UI_ParserError_message", new Object [] { exception.getMessage() }));
    xsdDiagnostics.add(xsdDiagnostic);
  }

  @Override
  public void fatalError(SAXParseException exception)
  {
    XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
    xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.FATAL_LITERAL);
    xsdDiagnostic.setMessage(XSDPlugin.INSTANCE.getString("_UI_ParserError_message", new Object [] { exception.getMessage() }));
    xsdDiagnostic.setLine(exception.getLineNumber());
    xsdDiagnostic.setColumn(exception.getColumnNumber());
    xsdDiagnostics.add(xsdDiagnostic);
  }

  @Override
  public void error(SAXParseException exception)
  {
    XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
    xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
    xsdDiagnostic.setMessage("DOM:" + exception.getMessage());
    xsdDiagnostic.setLine(exception.getLineNumber());
    xsdDiagnostic.setColumn(exception.getColumnNumber());
    xsdDiagnostics.add(xsdDiagnostic);
  }

  @Override
  public void warning(SAXParseException exception)
  {
    XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
    xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.WARNING_LITERAL);
    xsdDiagnostic.setMessage("DOM:" + exception.getMessage());
    xsdDiagnostic.setLine(exception.getLineNumber());
    xsdDiagnostic.setColumn(exception.getColumnNumber());
    xsdDiagnostics.add(xsdDiagnostic);
  }

  protected void saveLocation()
  {
    if (locator != null)
    {
      line = locator.getLineNumber();
      column = locator.getColumnNumber();
      // The crimson parser seems to give poor coodinates and is 0-based for line count.
      //
      if (column == -1)
      {
        column = 1;
      }
    }
  }

  @Override
  public InputSource resolveEntity(String publicId, String systemId) throws SAXException
  {
    InputSource inputSource;
    if ("-//W3C//DTD XMLSCHEMA 200102//EN".equalsIgnoreCase(publicId))
    {
      inputSource = new InputSource( XSDPlugin.INSTANCE.getBaseURL() + "cache/www.w3.org/2001/XMLSchema.dtd");
      inputSource.setPublicId(publicId);
    }
    else if (systemId != null && systemId.startsWith("file://bundleentry:"))
    {
      inputSource = new InputSource(systemId.substring(7));
      inputSource.setPublicId(publicId);
    }
    else
    {
      try
      {
        inputSource = super.resolveEntity(publicId, systemId);
      }
      catch (IOException exception)
      {
        throw new SAXException(exception);
      }
    }

    return inputSource;
  }
}
