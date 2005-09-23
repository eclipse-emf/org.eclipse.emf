/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: XMLProcessor.java,v 1.2 2005/09/23 22:49:07 elena Exp $
 */
package org.eclipse.emf.ecore.xmi.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
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

  protected Map registrations;

  protected Map loadOptions = new HashMap();

  protected Map saveOptions = new HashMap();

  protected ExtendedMetaData extendedMetaData;

  protected EPackage.Registry registry;

  /**
   * Protected constructor - should be used for in staticly generated models, 
   * or in the case schema(s) is known when XMLProcessor is created
   */
  protected XMLProcessor(EPackage.Registry registry)
  {
    this.registry = registry;
    this.extendedMetaData = createExtendedMetaData();
    ecoreBuilder = createEcoreBuilder();
    loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
    loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
    loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap());
    saveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
    saveOptions.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, new ArrayList());
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
    loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
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
   * @param schemaURIs - a list of URI {@link org.eclipse.common.util.URI} which point to XML Schemas
   * @throws SAXException
   */
  public XMLProcessor(Collection schemaURIs) throws SAXException
  {
    this(new EPackageRegistryImpl());
    try
    {
      Collection result = ecoreBuilder.generate(schemaURIs);
      for (Iterator i = result.iterator(); i.hasNext();)
      {
        Resource resource = (Resource)i.next();
        for (Iterator j = EcoreUtil.getObjectsByType(resource.getContents(), EcorePackage.eINSTANCE.getEPackage()).iterator(); j.hasNext();)
        {
          EcoreUtil.freeze((EPackage)j.next());
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

  protected Map getRegistrations()
  {
    if (registrations == null)
    {
      Map result = new HashMap();
      ResourceSet resourceSet = new ResourceSetImpl();

      if (resourceSet.getResourceFactoryRegistry().getFactory(URI.createURI(XML_EXTENSION)) == null)
      {
        result.put(XML_EXTENSION, new XMLResourceFactoryImpl());
      }

      if (resourceSet.getResourceFactoryRegistry().getFactory(URI.createURI(STAR_EXTENSION)) == null)
      {
        result.put(STAR_EXTENSION, new XMLResourceFactoryImpl());
      }

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

  public Resource load(String systemId, Map options) throws IOException
  {
    ResourceSet resourceSet = createResourceSet();
    XMLResource resource = (XMLResource)resourceSet.createResource(URI.createFileURI(systemId));
    InputSource inputSource = new InputSource();
    inputSource.setSystemId(systemId);
    if (options != null)
    {
      Map mergedOptions = new HashMap(loadOptions);
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

  public Resource load(InputStream is, Map options) throws IOException
  {
    ResourceSet resourceSet = createResourceSet();
    Resource resource = resourceSet.createResource(XML_URI);
    if (options != null)
    {
      Map mergedOptions = new HashMap(loadOptions);
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

  public Resource load(InputSource inputSource, Map options) throws IOException
  {
    ResourceSet resourceSet = createResourceSet();
    XMLResource resource = (XMLResource)resourceSet.createResource(XML_URI);
    if (options != null)
    {
      Map mergedOptions = new HashMap(loadOptions);
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

  public Resource load(Node node, Map options) throws IOException
  {
    ResourceSet resourceSet = createResourceSet();
    XMLResource resource = (XMLResource)resourceSet.createResource(XML_URI);
    if (options != null)
    {
      Map mergedOptions = new HashMap(loadOptions);
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

  public void save(OutputStream outputStream, Resource resource, Map options) throws IOException
  {
    if (options != null)
    {
      Map mergedOptions = new HashMap(saveOptions);
      mergedOptions.putAll(options);
      resource.save(outputStream, mergedOptions);
    }
    else
    {
      resource.save(outputStream, loadOptions);
    }
  }

  public void save(Document document, Resource resource, DOMHandler handler, Map options) throws IOException
  {
    if (options != null)
    {
      Map mergedOptions = new HashMap(saveOptions);
      mergedOptions.putAll(options);
      ((XMLResource)resource).save(document, mergedOptions, handler);
    }
    else
    {
      ((XMLResource)resource).save(document, loadOptions, handler);
    }
  }
  
  public String saveToString(Resource resource, Map options) throws IOException
  {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    if (options != null)
    {
      Map mergedOptions = new HashMap(saveOptions);
      mergedOptions.putAll(options);
      
      ((XMLResource)resource).save(os, mergedOptions);
    }
    else
    {
      ((XMLResource)resource).save(os, loadOptions);
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
