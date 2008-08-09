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
 * $Id: XSDResourceImpl.java,v 1.20 2008/08/09 13:28:47 emerks Exp $
 */
package org.eclipse.xsd.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.impl.XSDSchemaImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * The <b>Resource</b> implementation for the model.
 * This specialized resource implementation supports it's own way of making keys and hrefs, and it's own serialization.
 * This class is not intended for subclassing outside of the model implementation;
 * it is intended to be used as is with the Resource framework.
 */
public class XSDResourceImpl extends ResourceImpl
{
  public static String XSD_TRACK_LOCATION = "XSD_TRACK_LOCATION";

  public static String XSD_PROGRESS_MONITOR = "XSD_PROGRESS_MONITOR";

  public static String XSD_ENCODING = "XSD_ENCODING";
  
  /**
   * This option can be used as an option on Resource#load methods to specify JAXP pool to be used
   * for loading and serializing XML Schemas.
   * @see Resource#load(InputStream, Map)
   * @see Resource#load(Map)
   */
  public static String XSD_JAXP_POOL = "XSD_JAXP_POOL";
  
  /**
   * This option can be used as an option on Resource#load methods to specify JAXP configuration
   * that creates and configures SAX, DOM parsers and Transformer.
   * @see Resource#load(InputStream, Map)
   * @see Resource#load(Map)
   */
  public static String XSD_JAXP_CONFIG = "XSD_JAXP_CONFIG";

  public static class SchemaLocator extends AdapterImpl implements XSDSchemaLocator
  {
    public XSDSchema locateSchema(XSDSchema xsdSchema, String namespaceURI,  String rawSchemaLocationURI, String resolvedSchemaLocation)
    {
      if (rawSchemaLocationURI == null)
      {
        Resource resource = xsdSchema.eResource();
        if (resource != null)
        {
          for (Iterator<?> i = resource.getContents().iterator(); i.hasNext(); )
          {
            XSDSchema otherSchema = (XSDSchema)i.next();
            if (namespaceURI == null ? otherSchema.getTargetNamespace() == null : namespaceURI.equals(otherSchema.getTargetNamespace()))
            {
              return otherSchema;
            }
          }
        }
      }
      return  null;
    }

    @Override
    public boolean isAdapterForType(Object type)
    {
      return type == XSDSchemaLocator.class;
    }
  }

  protected static final SchemaLocator SCHEMA_LOCATOR = new SchemaLocator();

  public static void serialize(OutputStream outputStream, Element element)
  {
    serialize(outputStream, element, null);
  }

  public static void serialize(OutputStream outputStream, Element element, String encoding)
  {
    try
    {
      doSerialize(outputStream, element, encoding);
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
    }
  }

  protected static void doSerialize(OutputStream outputStream, Element element) throws IOException
  {
    doSerialize(outputStream, element, null);
  }

  protected static void doSerialize(OutputStream outputStream, Element element, String encoding) throws IOException
  {
    try
    {
      Transformer transformer = new DefaultJAXPConfiguration().createTransformer(encoding);
      // Be sure to use actual encoding of the transformer which might be non-null even if encoding started as null.
      encoding = transformer.getOutputProperty(OutputKeys.ENCODING);
      Writer writer = encoding == null ? new OutputStreamWriter(outputStream) : new OutputStreamWriter(outputStream, encoding);
      transformer.transform(new DOMSource(element), new StreamResult(writer));
    }
    catch (TransformerException exception)
    {
      XSDPlugin.INSTANCE.log(exception);
    }
  }

  public static void serialize(OutputStream outputStream, Document document)
  {
    serialize(outputStream, document, null);
  }

  public static void serialize(OutputStream outputStream, Document document, String encoding)
  {
    try
    {
      doSerialize(outputStream, document, encoding);
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
    }
  }

  /**
   * @deprecated since 2.2
   * @see #doSerialize(OutputStream, Document, Map)
   * @param outputStream
   * @param document
   * @see #doSerialize(OutputStream, Document, Map)
   * @throws IOException
   */
  @Deprecated
  protected static void doSerialize(OutputStream outputStream, Document document) throws IOException
  {
    doSerialize(outputStream, document, Collections.EMPTY_MAP);
  }
  
  protected static void doSerialize(OutputStream outputStream, Document document, Map<?, ?> options) throws IOException
  {
    JAXPPool jaxpPool = null;
    JAXPConfiguration config = null;
    String encoding = null;
    if (options != null)
    {
      jaxpPool = (JAXPPool)options.get(XSDResourceImpl.XSD_JAXP_POOL);
      config = (JAXPConfiguration)options.get(XSDResourceImpl.XSD_JAXP_CONFIG);
      encoding = (String)options.get(XSD_ENCODING);
    }
    
    if (jaxpPool == null)
    {
      if (config == null)
      {
        doSerialize(outputStream, document, encoding);
      }
      else
      {
        try
        {
          Transformer transformer = config.createTransformer(encoding);
          // Be sure to use actual encoding of the transformer which might be non-null even if encoding started as null.
          encoding = transformer.getOutputProperty(OutputKeys.ENCODING);
          Writer writer = encoding == null ? new OutputStreamWriter(outputStream) : new OutputStreamWriter(outputStream, encoding);
          transformer.transform(new DOMSource(document), new StreamResult(writer));
        }
        catch (TransformerException exception)
        {
          XSDPlugin.INSTANCE.log(exception);
        }
      }
    }
    else
    {
      Transformer transformer = null;
      try
      {
        transformer = jaxpPool.getTransformer(encoding);
        Writer writer = encoding == null ? new OutputStreamWriter(outputStream) : new OutputStreamWriter(outputStream, encoding);
        transformer.transform(new DOMSource(document), new StreamResult(writer));
      }
      catch (TransformerException exception)
      {
        XSDPlugin.INSTANCE.log(exception);
      }
      finally
      {
        jaxpPool.releaseTransformer(transformer);
      }
    }
  }

  /**
   * @deprecated since 2.2
   * @see #doSerialize(OutputStream, Document, Map)
   * @param outputStream
   * @param document
   * @param encoding
   * @throws IOException
   */
  @Deprecated
  protected static void doSerialize(OutputStream outputStream, Document document, String encoding) throws IOException
  {
    try
    {
      Transformer transformer = new DefaultJAXPConfiguration().createTransformer(encoding);
      // Be sure to use actual encoding of the transformer which might be non-null even if encoding started as null.
      encoding = transformer.getOutputProperty(OutputKeys.ENCODING);
      Writer writer = encoding == null ? new OutputStreamWriter(outputStream) : new OutputStreamWriter(outputStream, encoding);
      transformer.transform(new DOMSource(document), new StreamResult(writer));
    }
    catch (TransformerException exception)
    {
      XSDPlugin.INSTANCE.log(exception);
    }
  }
  
  protected static void doSerialize(Writer writer, Document document, Map<?, ?> options) throws IOException
  {
    JAXPPool jaxpPool = null;
    JAXPConfiguration config = null;
    String encoding = null;
    if (options != null)
    {
      jaxpPool = (JAXPPool)options.get(XSDResourceImpl.XSD_JAXP_POOL);
      config = (JAXPConfiguration)options.get(XSDResourceImpl.XSD_JAXP_CONFIG);
      encoding = (String)options.get(XSD_ENCODING);
    }

    if (jaxpPool == null)
    {
      try
      {
        if (config == null)
        {
          new DefaultJAXPConfiguration().createTransformer(encoding).transform(new DOMSource(document), new StreamResult(writer));
        }
        else
        {
          config.createTransformer(encoding).transform(new DOMSource(document), new StreamResult(writer));
        }
      }
      catch (TransformerException exception)
      {
        XSDPlugin.INSTANCE.log(exception);
      }
    }
    else
    {
      Transformer transformer = null;
      try
      {
        transformer = jaxpPool.getTransformer(encoding);
        transformer.transform(new DOMSource(document), new StreamResult(writer));
      }
      catch (TransformerException exception)
      {
        XSDPlugin.INSTANCE.log(exception);
      }
      finally
      {
        jaxpPool.releaseTransformer(transformer);
      }
    }
  }

  /**
   * Builds a document using default JAXP.
   * @deprecated since 2.2
   * @see #getDocument(InputSource, ErrorHandler, Map)
   * @param inputSource the contents to parse.
   * @param errorHandler the handled used by the parser.
   * @return a document.
   */
  @Deprecated
  protected static Document getDocument(InputSource inputSource, ErrorHandler errorHandler)  throws IOException
  {
    try
    {
      DocumentBuilder documentBuilder = new DefaultJAXPConfiguration().createDocumentBuilder(errorHandler);
      Document document = documentBuilder.parse(inputSource);
      return document;
    }
    catch (ParserConfigurationException exception)
    {
      throw new IOWrappedException(exception);
    }
    catch (SAXException exception)
    {
      throw new IOWrappedException(exception);
    }
  }
  
  /**
   * Builds DOM document using JAXP DocumentBuilder
   * @see #XSD_JAXP_POOL
   * @see #XSD_JAXP_CONFIG
   * @param inputSource the content to parser
   * @param errorHandler error handler for recording any loading errors
   * @param options loading options
   * @return document DOM document
   * @throws IOException
   */
  protected static Document getDocument(InputSource inputSource, ErrorHandler errorHandler, Map<?, ?> options) throws IOException
  {
    JAXPPool jaxpPool = null;
    JAXPConfiguration config = null;
    if (options != null)
    {
      jaxpPool = (JAXPPool)options.get(XSDResourceImpl.XSD_JAXP_POOL);
      config = (JAXPConfiguration)options.get(XSDResourceImpl.XSD_JAXP_CONFIG);
    }

    if (jaxpPool == null)
    {
      if (config == null)
      {
        return getDocument(inputSource, errorHandler);
      }
      else
      {
        try
        {
          DocumentBuilder documentBuilder = config.createDocumentBuilder(errorHandler);
          Document document = documentBuilder.parse(inputSource);
          return document;
        }
        catch (ParserConfigurationException exception)
        {
          throw new IOWrappedException(exception);
        }
        catch (SAXException exception)
        {
          throw new IOWrappedException(exception);
        }
      }
    }
    else
    {
      DocumentBuilder documentBuilder = null;
      try
      {
        documentBuilder = jaxpPool.getDocumentBuilder(errorHandler);
        Document document = documentBuilder.parse(inputSource);
        return document;
      }
      catch (ParserConfigurationException exception)
      {
        throw new IOWrappedException(exception);
      }
      catch (SAXException exception)
      {
        throw new IOWrappedException(exception);
      }
      finally
      {
        jaxpPool.releaseDocumentBuilder(documentBuilder);
      }
    }
  }
  
  /**
   * @deprecated since 2.2
   * @see #getDocument(InputSource, ErrorHandler, Map)
   * Builds a document using default JAXP implementation.
   * @param inputStream the contents to parse.
   * @param errorHandler the handled used by the parser.
   * @return a document.
   */
  @Deprecated
  protected static Document getDocument(InputStream inputStream, ErrorHandler errorHandler)  throws IOException
  {
    return getDocument(new InputSource(inputStream), errorHandler);
  }

  /**
   * Creates an EntityResovler that can be used to help with parsing schema documents.
   * @return an EntityResolver.
   */
  protected static EntityResolver createEntityResolver()
  {
    final String baseURL = XSDPlugin.INSTANCE.getBaseURL().toString();

    // Create a catalog with an entity mapping URL for the cached DTD for XML Schemas and set to into the build.
    //
    EntityResolver entityResolver = 
      new DefaultHandler()
      {
        @Override
        public InputSource resolveEntity(String publicId, String systemId) throws SAXException
        {
          InputSource inputSource;
          if ("-//W3C//DTD XMLSCHEMA 200102//EN".equalsIgnoreCase(publicId) || "http://www.w3.org/2001/XMLSchema.dtd".equalsIgnoreCase(systemId))
          {
            inputSource = new InputSource(baseURL + "cache/www.w3.org/2001/XMLSchema.dtd");
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
              if (false)
              {
                throw new IOException();
              }
            }
            catch (IOException exception)
            {
               throw new SAXException(exception);
            }
          }

          return inputSource;
        }
      };

    return entityResolver;
  }

  /**
   * Assigns diagnostics to a schema.
   * @param xsdSchema the schema.
   * @param xsdDiagnostics the {@link XSDDiagnostic}s.
   */
  protected static void assignDiagnostics(XSDSchema xsdSchema, Collection<XSDDiagnostic> xsdDiagnostics)
  {
    if (!xsdDiagnostics.isEmpty())
    {
      xsdSchema.getDiagnostics().addAll(xsdDiagnostics);
      for (XSDDiagnostic xsdDiagnostic : xsdDiagnostics)
      {
        xsdDiagnostic.getComponents().add(xsdSchema);
        if (xsdSchema.getElement() != null)
        {
          xsdDiagnostic.setNode(xsdSchema.getElement());
        }
      }
    }
  }

  protected Collection<XSDSchema> attachedSchemas;

  public XSDResourceImpl()
  {
    super();
  }

  public XSDResourceImpl(URI uri)
  {
    super(uri);
  }

  @Override
  protected void doSave(OutputStream os, Map<?, ?> options) throws IOException
  {
    if (os instanceof URIConverter.WriteableOutputStream)
    {
      doSave(((URIConverter.WriteableOutputStream)os).asWriter(), options);
    }
    else
    {
      XSDSchema xsdSchema = getSchema();
      if (xsdSchema != null)
      {
        Document document = xsdSchema.getDocument();
        if (document == null)
        {
          xsdSchema.updateDocument();
          document = xsdSchema.getDocument();
        }
  
        if (xsdSchema.getElement() == null)
        {
          xsdSchema.updateElement();
        }
  
        doSerialize(os, document, options);
      }
    }
  }

  /**
   * Saves the resource to the writer using the specified options.
   * @param writer the writer
   * @param options the save options.
   */
  public final void save(Writer writer, Map<?, ?> options) throws IOException
  {
    if (defaultSaveOptions == null || defaultSaveOptions.isEmpty())
    {
      doSave(writer, options);
    }
    else if (options == null)
    {
      doSave(writer, defaultSaveOptions);
    }
    else
    {
      Map<Object, Object> mergedOptions = new HashMap<Object, Object>(defaultSaveOptions);
      mergedOptions.putAll(options);
      doSave(writer, mergedOptions);
    }
    setModified(false);
  }

  
  protected void doSave(Writer writer, Map<?, ?> options) throws IOException
  {
    XSDSchema xsdSchema = getSchema();
    if (xsdSchema != null)
    {
      Document document = xsdSchema.getDocument();
      if (document == null)
      {
        xsdSchema.updateDocument();
        document = xsdSchema.getDocument();
      }

      if (xsdSchema.getElement() == null)
      {
        xsdSchema.updateElement();
      }
      doSerialize(writer, document, options);
    }
  }
  
  /**
   * This gets the resource's schema.
   */
  public XSDSchema getSchema()
  {
    return 
      getContents().size() >= 1 && getContents().get(0) instanceof XSDSchema ?
        (XSDSchema)getContents().get(0) :
        null;
  }
  
  public final void load(InputSource inputSource, Map<?, ?> options) throws IOException
  {
    if (!isLoaded)
    {
      Notification notification = setLoaded(true);
      isLoading = true;

      if (errors != null)
      {
        errors.clear();
      }

      if (warnings != null)
      {
        warnings.clear();
      }

      try
      {
        if (defaultLoadOptions == null || defaultLoadOptions.isEmpty())
        {
          doLoad(inputSource, options);
        }
        else if (options == null)
        {
          doLoad(inputSource, defaultLoadOptions);
        }
        else
        {
          Map<Object, Object> mergedOptions = new HashMap<Object, Object>(defaultLoadOptions);
          mergedOptions.putAll(options);
  
          doLoad(inputSource, mergedOptions);
        }
      }
      finally
      {
        isLoading = false;

        if (notification != null)
        {
          eNotify(notification);
        }
  
        setModified(false);
      } 
    }
  }
  
  protected void doLoad(InputSource inputSource, Map<?, ?> options) throws IOException
  {
    attachedSchemas = new ArrayList<XSDSchema>();

    // This pattern avoids loading the IProgressMonitor class when there is no progress monitor.
    // This is important for stand-alone execution to work correctly.
    //
    IProgressMonitor progressMonitor = null;
    Object monitor = options == null ? null : options.get("XSD_PROGRESS_MONITOR");
    if (monitor != null)
    {
      progressMonitor = (IProgressMonitor)monitor;
      progressMonitor.setTaskName(XSDPlugin.INSTANCE.getString("_UI_ResourceLoad_progress"));
      progressMonitor.subTask(getURI().toString());
    }

    IOException ioException = null;
    Collection<XSDDiagnostic> errors = null;
    try
    {
      Document document;

      if (options != null && Boolean.TRUE.equals(options.get("XSD_TRACK_LOCATION")))
      {
        XSDParser xsdParser = new XSDParser(options);
        xsdParser.parse(inputSource);
        document = xsdParser.getDocument();
        if (xsdParser.getEncoding() != null)
        {
          getDefaultSaveOptions().put(XSD_ENCODING, xsdParser.getEncoding());
        }
        errors = xsdParser.getDiagnostics();
      }
      else
      {
        DefaultErrorHandler errorHandler = new DefaultErrorHandler();
        try
        {
          document = getDocument(inputSource, errorHandler, options);
        }
        finally
        {
          errors = errorHandler.getDiagnostics();
        }
      }

      if (document != null && document.getDocumentElement() != null)
      {
        ResourceSet globalResourceSet = XSDSchemaImpl.getGlobalResourceSet();
        Object oldMonitor = globalResourceSet.getLoadOptions().get("XSD_PROGRESS_MONITOR");
        try
        {
          XSDSchemaImpl.getGlobalResourceSet().getLoadOptions().put("XSD_PROGRESS_MONITOR", progressMonitor);
          if (options != null && (options.containsKey("XSD_MAGIC_XML_SCHEMA") || options.containsKey("XSD_XML_SCHEMA")))
          {
            handleSchemaElement(document.getDocumentElement(), true);
          }
          else if (!findSchemas(document.getDocumentElement()))
          {
            handleSchemaElement(document.getDocumentElement(), false);
          }
        }
        finally
        {
          XSDSchemaImpl.getGlobalResourceSet().getLoadOptions().put("XSD_PROGRESS_MONITOR", oldMonitor);
        }
      }
      else
      {
        handleSchemaElement(null, false);
      }
    }
    catch (IOException exception)
    {
      handleSchemaElement(null, false);
      ioException = exception;
    }

    if (errors != null)
    {
      for (Iterator<?> i = getContents().iterator(); i.hasNext();)
      {
        XSDSchema xsdSchema = (XSDSchema)i.next();
        assignDiagnostics(xsdSchema, errors);
  
        for (XSDDiagnostic xsdDiagnostic : errors)
        {
          switch (xsdDiagnostic.getSeverity().getValue())
          {
            case XSDDiagnosticSeverity.FATAL:
            case XSDDiagnosticSeverity.ERROR:
            {
              getErrors().add(xsdDiagnostic);
              break;
            }
            case XSDDiagnosticSeverity.WARNING:
            case XSDDiagnosticSeverity.INFORMATION:
            {
              getWarnings().add(xsdDiagnostic);
              break;
            }
          }
        }
      }
    }

    if (getContents().size() > 1)
    {
      eAdapters().add(SCHEMA_LOCATOR);
    }

    String schemaLocation = getURI().toString();
    Collection<XSDSchema> previouslyAttachedSchemas = attachedSchemas;
    attachedSchemas = null;
    for (XSDSchema xsdSchema : previouslyAttachedSchemas)
    {
      xsdSchema.setSchemaLocation(schemaLocation);
    }

    if (progressMonitor != null)
    {
      progressMonitor.worked(1);
    }

    if (ioException != null)
    {
      throw ioException;
    }
  }

  /**
   * Loads a new {@link XSDResourceImpl} into the resource set.
   * @param inputStream the contents of the new resource.
   * @param options any options to influence loading behavior.
   */
  @Override
  protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException
  {
    InputSource inputSource = 
      inputStream instanceof URIConverter.ReadableInputStream ? 
      new InputSource(((URIConverter.ReadableInputStream)inputStream).asReader()) :
      new InputSource(inputStream);

    if (getURI() != null)
    {
      String id = getURI().toString();
      inputSource.setPublicId(id);
      inputSource.setSystemId(id);
    }
    doLoad(inputSource, options);
  }

  protected boolean findSchemas(Element element)
  {
    if (XSDConstants.nodeType(element) == XSDConstants.SCHEMA_ELEMENT)
    {
      handleSchemaElement(element, false);
      return true;
    }
    else
    {
      boolean result = false;
      for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
      {
        if (child instanceof Element)
        {
          if (findSchemas((Element)child))
          {
            result = true;
          }
        }
      }
      return result;
    }
  }

  protected void handleSchemaElement(Element element, boolean isMeta)
  {
    XSDSchema xsdSchema;
    if (element == null)
    {
      xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();
      xsdSchema.getQNamePrefixToNamespaceMap().put(null, XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
    }
    else if (isMeta)
    {
      xsdSchema = XSDSchemaImpl.createMetaSchema(element);
    }
    else
    {
      xsdSchema = XSDSchemaImpl.createSchema(element);
    }
    getContents().add(xsdSchema);
  }

  @Override
  public void attached(EObject eObject)
  {
    super.attached(eObject);

    if (eObject instanceof XSDSchema)
    {
      if (attachedSchemas != null)
      {
        attachedSchemas.add((XSDSchema)eObject);
      }
      else if (!(eObject instanceof XSDSchemaImpl) || ((XSDSchemaImpl)eObject).getPendingSchemaLocation() == null)
      {
        ((XSDSchema)eObject).setSchemaLocation(getURI().toString());
      }
    }
  }

  @Override
  public String getURIFragment(EObject eObject)
  {
    if (eObject instanceof XSDConcreteComponent)
    {
      XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)eObject;
      Element theElement = xsdConcreteComponent.getElement();
      if (theElement != null &&
            theElement.hasAttributeNS(null, XSDConstants.ID_ATTRIBUTE) &&
            theElement.getOwnerDocument().getElementById(theElement.getAttributeNS(null, XSDConstants.ID_ATTRIBUTE)) == theElement)
      {
        return theElement.getAttributeNS(null, XSDConstants.ID_ATTRIBUTE);
      }
    }

    return super.getURIFragment(eObject);
  }

  @Override
  public EObject getEObject(String uriFragment)
  {
    // Do ID-based lookup.
    //
    if (!uriFragment.startsWith("/"))
    {
      Element theElement = getSchema().getElement();
      if (theElement != null)
      {
        // Navigate out through the elements.
        //
        Element resultElement =  theElement.getOwnerDocument().getElementById(uriFragment);
        List<Element> parents = new ArrayList<Element>();
        for (Node parent = resultElement; parent != null; parent = parent.getParentNode())
        {
          if (parent.getNodeType() == Node.ELEMENT_NODE)
          {
            parents.add((Element)parent);
          }
        }
        return ((org.eclipse.xsd.impl.XSDSchemaImpl)getSchema()).getBestConcreteComponent(parents);
      } 
    }

    return super.getEObject(uriFragment); 
  }

  public Map<Object, Object> getDefaultSaveOptions()
  {
    if (defaultSaveOptions == null)
    {
      defaultSaveOptions = new HashMap<Object, Object>();
    }

    return defaultSaveOptions;
  }

  public Map<Object, Object> getLoadSaveOptions()
  {
    if (defaultLoadOptions == null)
    {
      defaultLoadOptions = new HashMap<Object, Object>();
    }

    return defaultLoadOptions;
  }

  @Override
  public void setModified(boolean isModified)
  {
    // Avoid generating touch notification.
    //
    if (this.isModified != isModified)
    {
      super.setModified(isModified);
    }
  }
}
