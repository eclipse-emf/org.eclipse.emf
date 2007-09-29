/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: XMLContentHandlerImpl.java,v 1.1 2007/09/29 17:24:26 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLDefaultHandler;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLOptions;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * A {@link ContentHandler} content handler implementation for dealing with XML content.
 */
public class XMLContentHandlerImpl extends ContentHandlerImpl
{
  /**
   * Returns whether the given name and namespace represents a specialize XMI root element tag.
   * @param name the element name to consider.
   * @param namespace the element namespace to consider.
   * @return whether the given name and namespace represents a specialize XMI root element tag.
   */
  public static boolean isXMINameAndNamespace(String name, String namespace)
  {
    return XMIResource.XMI_TAG_NAME.equals(name) && isXMINamespace(namespace);
  }

  /**
   * Returns whether the given namespace represents an XMI namespace.
   * @param namespace the element namespace to consider.
   * @return whether the given namespace represents an XMI namespace.
   */
  public static boolean isXMINamespace(String namespace)
  {
    return
      namespace != null &&
        (namespace.startsWith(XMIResource.XMI_NAMESPACE_PREFIX) ||
           namespace.equals(XMIResource.XMI_URI));
  }

  private static final XMLParserPool XML_PARSER_POOL = new XMLParserPoolImpl(1, true);
  private static final Map<Object, Object> DEFAULT_SAVE_OPTIONS;
  private static final Map<Object, Object> DEFAULT_LOAD_OPTIONS;
  static
  {
    Map<Object, Object> defaultLoadOptions = new HashMap<Object, Object>();
    Map<Object, Object> defaultSaveOptions = new HashMap<Object, Object>();

    defaultLoadOptions.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    defaultSaveOptions.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);

    ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(new EPackageRegistryImpl());
    defaultLoadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
    defaultSaveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

    XMLOptions xmlOptions = new XMLOptionsImpl();
    xmlOptions.setProcessAnyXML(true);
    defaultLoadOptions.put(XMLResource.OPTION_XML_OPTIONS, xmlOptions);

    defaultLoadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, XML_PARSER_POOL);

    DEFAULT_LOAD_OPTIONS = defaultLoadOptions;
    DEFAULT_SAVE_OPTIONS = defaultSaveOptions;
  }

  /**
   * Creates a specialized XML resource for consuming just the root elements
   * as well as the first nested element,
   * if the root element is an {@link #isXMINameAndNamespace(String, String) XMI root element}.
   * @return an XML Resource for the root element and possibly the first nested element.
   */
  protected XMLResource createXMLResource()
  {
    return
      new XMLResourceImpl()
      {
        {
          defaultLoadOptions = DEFAULT_LOAD_OPTIONS;
          defaultSaveOptions = DEFAULT_SAVE_OPTIONS;
        }

        @Override
        protected XMLLoad createXMLLoad()
        {
          return
            new XMLLoadImpl(createXMLHelper())
            {
              @Override
              public XMLDefaultHandler createDefaultHandler()
              {
                return
                  new SAXXMLHandler(resource, helper, options)
                  {
                    protected int count = 0;

                    @Override
                    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
                    {
                      super.startElement(uri, localName, name, attributes);
                      ++count;
                      if (!isXMINameAndNamespace(localName, uri) || count >= 2)
                      {
                        throw new RuntimeException();
                      }
                    }
                  };
              }
            };
        }
      };
  }

  /**
   * Returns the character set of the input stream; it's computed from the encoding specified in the XML content.
   * @param uri the URI of the input stream.
   * @param inputStream the input stream of the XML content.
   * @param options any options that might influence the loading the the XML content.
   * @param context a cache for previously computed information.
   * @return the character set of the input stream.
   * @throws IOException if there is a problem loading the XML content.
   */
  protected String getCharset(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    String result = (String)context.get(ContentHandler.CHARSET_PROPERTY);
    if (result == null)
    {
      result = load(uri, inputStream, options, context).getEncoding();
      context.put(ContentHandler.CHARSET_PROPERTY, result);
    }
    return result;
  }

  /**
   * Returns the XML resource loaded from the input stream.
   * @param uri the URI of the input stream.
   * @param inputStream the input stream of the XML content.
   * @param options any options that might influence the loading the the XML content.
   * @param context a cache for previously computed information.
   * @return the XML resource loaded from the input stream.
   * @throws IOException if there is a problem loading the XML content.
   */
  protected XMLResource load(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    XMLResource result = (XMLResource)context.get("XMLResource");
    if (result == null)
    {
      try
      {
        result = createXMLResource();
        result.setURI(uri);
        result.load
          (new BufferedInputStream(inputStream)
           {
             @Override
             public void close() throws IOException
             {
               // Ignore close since we don't want to let the parser close the stream when it's done.
             }
           },
           null);
      }
      catch (Throwable exception)
      {
        // Ignore since we expect this to fail because of early termination
        // and if there is some other kind of failure, then we'll simply not be able to match the content type.
      }
      finally
      {
        inputStream.reset();
      }
      context.put("XMLResource", result);
    }
    return result;
  }

  /**
   * This implementation augments the base implementation by computing the {@link ContentHandler#CHARSET_PROPERTY character set property}.
   */
  @Override
  public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    Map<String, Object> result = super.contentDescription(uri, inputStream, options, context);
    if (isRequestedProperty(ContentHandler.CHARSET_PROPERTY, options))
    {
      result.put(ContentHandler.CHARSET_PROPERTY, getCharset(uri, inputStream, options, context));
    }
    return result;
  }
}
