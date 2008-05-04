/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: XMLProcessor.java,v 1.10 2008/05/04 10:58:48 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.DOMHandler;
import org.eclipse.emf.ecore.xmi.EcoreBuilder;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * XMLProcessor provides helper methods to serialize and deserialize XML using EMF framework.
 *
 */
public class XMLProcessor
{
  protected EcoreBuilder ecoreBuilder;

  protected final static String XML_EXTENSION = "xml";

  protected final static String STAR_EXTENSION = "*";

  protected final static URI XML_URI = URI.createFileURI(XML_EXTENSION);

  protected Map<String, Resource.Factory> registrations;

  protected Map<Object, Object> loadOptions = new HashMap<Object, Object>();

  protected Map<Object, Object> saveOptions = new HashMap<Object, Object>();

  protected ExtendedMetaData extendedMetaData;

  protected EPackage.Registry registry;

  /**
   * Protected constructor - should be used for in statically generated models, 
   * or in the case schema(s) is known when XMLProcessor is created
   */
  protected XMLProcessor(EPackage.Registry registry)
  {
    this.registry = registry;
    this.extendedMetaData = createExtendedMetaData();
    ecoreBuilder = createEcoreBuilder();
    loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
    loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl(true));
    loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap<String, EStructuralFeature>());
    loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
    loadOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
    saveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
    saveOptions.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, new ArrayList<Object>()); // TODO
    saveOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
  }

  /**
   * Use this constructor to create an XML processor that is not aware of any schema.
   * Use {@link org.eclipse.emf.ecore.xmi.XMLOptions} to configure this processor to 
   * process, for example, schemaLocation/noNamespaceSchema location attributes.
   */
  public XMLProcessor()
  {
    this.extendedMetaData = createExtendedMetaData();
    ecoreBuilder = createEcoreBuilder();
    // register default options
    loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
    loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl(true));
    loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
    loadOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
    saveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
    saveOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
  }

  /*
   * Create an XML processor that is aware of XML Schema specified.
   * @param schemaURIs - a URI {@link org.eclipse.common.util.URI} which point to an XML Schema
   */
  public XMLProcessor(URI schemaURI) throws SAXException
  {
    this(Collections.singleton(schemaURI));
  }

  /**
   * Create an XML processor that is aware of XML Schemas specified.
   * @param schemaURIs - a list of URI {@link URI} which point to XML Schemas
   * @throws SAXException
   */
  public XMLProcessor(Collection<URI> schemaURIs) throws SAXException
  {
    this(new EPackageRegistryImpl());
    loadOptions.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    loadOptions.put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
    saveOptions.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE); 
    saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    try
    {
      for (Resource resource : ecoreBuilder.generate(schemaURIs))
      {
        for (EPackage ePackage : EcoreUtil.<EPackage>getObjectsByType(resource.getContents(), EcorePackage.Literals.EPACKAGE))
        {
          EcoreUtil.freeze(ePackage);
        }
      }
    }
    catch (InvocationTargetException ie)
    {
      throw new SAXException((Exception)ie.getTargetException());
    }
    catch (Exception e)
    {
      throw new SAXException(e);
    }
  }

  protected Map<String, Resource.Factory> getRegistrations()
  {
    if (registrations == null)
    {
      Map<String, Resource.Factory> result = new HashMap<String, Resource.Factory>();
      result.put(STAR_EXTENSION, new XMLResourceFactoryImpl());
      registrations = result;
    }

    return registrations;
  }

  /**
   * @return a map of namespace URI to EPackage. This registry should be treated as read-only. The registry
   * does not include packages loaded on demand (@see #getExtendedMetaData to retrieve demand loaded packages)
   */
  public EPackage.Registry getEPackageRegistry()
  {
    return registry;
  }

  /**
   * @return The ExtendedMetaData used by XMLProcessor to register all Ecore packages. 
   * The ExtendedMetaData can be used to retieve information about the model (e.g. @see ExtendedMetaData#getElement(String, String)).
   * The ExtendedMetaData should be treated as read-only.
   */
  public ExtendedMetaData getExtendedMetaData()
  {
    return extendedMetaData;
  }

  /**
   * Given a system identifier and option, this methods creates an EMF Resource 
   * (using URI#createURI method) and loads the resource data.
   * @param systemId - system identifier
   * @param options - options map
   * @return Loaded resource
   * @throws IOException
   * @see org.eclipse.emf.ecore.resource.Resource
   * @see org.eclipse.emf.common.util.URI
   */
  
  public Resource load(String systemId, Map<?, ?> options) throws IOException
  {
    ResourceSet resourceSet = createResourceSet();
    XMLResource resource = (XMLResource)resourceSet.createResource(URI.createURI(systemId));
    InputSource inputSource = new InputSource();
    inputSource.setSystemId(systemId);
    if (options != null)
    {
      Map<Object, Object> mergedOptions = new HashMap<Object, Object>(loadOptions);
      mergedOptions.putAll(options);
      resource.load(inputSource, mergedOptions);
    }
    else
    {
      resource.load(inputSource, loadOptions);
    }
    resourceSet.getPackageRegistry().putAll(registry);
    return resource;
  }

  public Resource load(InputStream is, Map<?, ?> options) throws IOException
  {
    ResourceSet resourceSet = createResourceSet();
    Resource resource = resourceSet.createResource(XML_URI);
    if (options != null)
    {
      Map<Object, Object> mergedOptions = new HashMap<Object, Object>(loadOptions);
      mergedOptions.putAll(options);
      resource.load(is, mergedOptions);
    }
    else
    {
      resource.load(is, loadOptions);
    }
    resourceSet.getPackageRegistry().putAll(registry);
    return resource;
  }

  public Resource load(InputSource inputSource, Map<?, ?> options) throws IOException
  {
    ResourceSet resourceSet = createResourceSet();
    XMLResource resource = (XMLResource)resourceSet.createResource(XML_URI);
    if (options != null)
    {
      Map<Object, Object> mergedOptions = new HashMap<Object, Object>(loadOptions);
      mergedOptions.putAll(options);
      resource.load(inputSource, mergedOptions);
    }
    else
    {
      resource.load(inputSource, loadOptions);
    }
    resourceSet.getPackageRegistry().putAll(registry);
    return resource;
  }

  public Resource load(Node node, Map<?, ?> options) throws IOException
  {
    ResourceSet resourceSet = createResourceSet();
    XMLResource resource = (XMLResource)resourceSet.createResource(XML_URI);
    if (options != null)
    {
      Map<Object, Object> mergedOptions = new HashMap<Object, Object>(loadOptions);
      mergedOptions.putAll(options);
      resource.load(node, mergedOptions);
    }
    else
    {
      resource.load(node, loadOptions);
    }
    resourceSet.getPackageRegistry().putAll(registry);
    return resource;
  }

  public void save(OutputStream outputStream, Resource resource, Map<?, ?> options) throws IOException
  {
    if (options != null)
    {
      Map<Object, Object> mergedOptions = new HashMap<Object, Object>(saveOptions);
      mergedOptions.putAll(options);
      resource.save(outputStream, mergedOptions);
    }
    else
    {
      resource.save(outputStream, saveOptions);
    }
  }
  
  public void save(Writer writer, Resource resource, Map<?, ?> options) throws IOException
  {
    if (options != null)
    {
      Map<Object, Object> mergedOptions = new HashMap<Object, Object>(saveOptions);
      mergedOptions.putAll(options);
      ((XMLResource)resource).save(writer, mergedOptions);
    }
    else
    {
      ((XMLResource)resource).save(writer, saveOptions);
    }
  }

  public void save(Document document, Resource resource, DOMHandler handler, Map<?, ?> options) throws IOException
  {
    if (options != null)
    {
      Map<Object, Object> mergedOptions = new HashMap<Object, Object>(saveOptions);
      mergedOptions.putAll(options);
      ((XMLResource)resource).save(document, mergedOptions, handler);
    }
    else
    {
      ((XMLResource)resource).save(document, saveOptions, handler);
    }
  }
  
  public String saveToString(Resource resource, Map<?, ?> options) throws IOException
  {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    if (options != null)
    {
      Map<Object, Object> mergedOptions = new HashMap<Object, Object>(saveOptions);
      mergedOptions.putAll(options);
      
      ((XMLResource)resource).save(os, mergedOptions);
    }
    else
    {
      ((XMLResource)resource).save(os, saveOptions);
    }
    return os.toString();
  }

  protected ResourceSet createResourceSet()
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().putAll(getRegistrations());
    return resourceSet;
  }

  protected EcoreBuilder createEcoreBuilder()
  {
    return new DefaultEcoreBuilder(extendedMetaData);
  }

  /**
   * This methods can be used to provide a different ExtendedMetaData. 
   * Note that if this method creates a new EPackage.Registry it must also assign the global registry
   * variable.
   * @return ExtendedMetaData
   */
  protected ExtendedMetaData createExtendedMetaData()
  {
    if (registry == null)
    {
      registry = new EPackageRegistryImpl();
    }
    return new BasicExtendedMetaData(registry);
  }
}
