/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMIPlugin;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.xml.sax.helpers.DefaultHandler;


public class GenericXMLResourceImpl extends XMLResourceImpl
{
  protected static final Class<?> xsdEcoreBuilderClass;

  protected static final Constructor<?> xsdEcoreBuilderConstructor;

  protected static final Method xsdEcoreBuilderGenerateResourcesMethod;

  protected static final Map<?, ?> xsdEcoreBuilderOptions;

  static
  {
    Class<?> theXSDEcoreBuilderClass = null;
    Constructor<?> theXSDEcoreBuilderConstructor = null;
    Method theXSDEcoreBuilderGenerateResourcesMethod = null;

    try
    {
      theXSDEcoreBuilderClass = CommonPlugin.loadClass("org.eclipse.xsd", "org.eclipse.xsd.ecore.XSDEcoreBuilder");
      theXSDEcoreBuilderConstructor = theXSDEcoreBuilderClass.getConstructor(new Class [] { ExtendedMetaData.class, Map.class });
      theXSDEcoreBuilderGenerateResourcesMethod = theXSDEcoreBuilderClass.getMethod("generateResources", new Class [] { Collection.class });
    }
    catch (Exception exception)
    {
      XMIPlugin.INSTANCE.log(exception);
      exception.printStackTrace();
    }

    xsdEcoreBuilderClass = theXSDEcoreBuilderClass;
    xsdEcoreBuilderConstructor = theXSDEcoreBuilderConstructor;
    xsdEcoreBuilderGenerateResourcesMethod = theXSDEcoreBuilderGenerateResourcesMethod;

    Map<Object, Object> theXSDEcoreBuilderOptions = new HashMap<Object, Object>();
    theXSDEcoreBuilderOptions.put("REUSE_REGISTERED_PACKAGES", Boolean.TRUE);
    xsdEcoreBuilderOptions = Collections.unmodifiableMap(theXSDEcoreBuilderOptions);
  }

  public GenericXMLResourceImpl(URI uri)
  {
    super(uri);
  }

  protected static class GenericXMLLoadImpl extends XMLLoadImpl
  {
    protected GenericXMLLoadImpl(XMLHelper helper)
    {
      super(helper);
    }

    @Override
    protected DefaultHandler makeDefaultHandler()
    {
      return new GenericSAXXMLHandler(resource, helper, options);
    }
  }

  protected static class GenericSAXXMLHandler extends SAXXMLHandler
  {
    protected Object xsdEcoreBuilder;

    protected Collection<? extends Resource> generatedResources;

    protected GenericSAXXMLHandler(XMLResource xmlResource, XMLHelper helper, Map<?, ?> options)
    {
      super(xmlResource, helper, options);
    }

    @Override
    protected void handleTopLocations(String prefix, String name)
    {
      if (urisToLocations != null && xsdEcoreBuilderConstructor != null && xsdEcoreBuilderGenerateResourcesMethod != null)
      {
        try
        {
          xsdEcoreBuilder = xsdEcoreBuilderConstructor.newInstance(new Object []{ extendedMetaData, xsdEcoreBuilderOptions });
          @SuppressWarnings("unchecked") Collection<? extends Resource> newGeneratedResources = 
            (Collection<? extends Resource>)xsdEcoreBuilderGenerateResourcesMethod.invoke
               (xsdEcoreBuilder, new Object []{ urisToLocations.values() });
          generatedResources = newGeneratedResources;
          // xmlResource.getResourceSet().getResources().addAll(generatedResources);
        }
        catch (Exception exception)
        {
          XMIPlugin.INSTANCE.log(exception);
        }
      }

      // Ensure that anything can be handled, even if it's not recognized.
      //
      String namespaceURI = helper.getURI(prefix);
      if (extendedMetaData.getPackage(namespaceURI) == null)
      {
        extendedMetaData.demandFeature(namespaceURI, name, true);
      }
    }

    @Override
    protected EPackage handleMissingPackage(String uriString)
    {
      return objects.isEmpty() ? extendedMetaData.demandPackage(uriString) : super.handleMissingPackage(uriString);
    }
  }

  @Override
  protected XMLLoad createXMLLoad()
  {
    return new GenericXMLLoadImpl(createXMLHelper());
  }
}