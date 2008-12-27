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
 * $Id: DefaultEcoreBuilder.java,v 1.6 2008/12/27 19:49:58 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.util;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.EcoreBuilder;


/**
 * Default implementation of XML Schema to Ecore builder.
 */
public class DefaultEcoreBuilder implements EcoreBuilder
{
  protected static final Class<?> XSD_ECORE_BUILDER_CLASS;

  protected static final Constructor<?> XSD_ECORE_BUILDER_CONSTRUCTOR;

  protected static final Method XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD;

  protected static final Map<?, ?>  XSD_ECORE_BUILDER_OPTIONS;

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
    catch (Throwable exception)
    {
      // Assume the class isn't available.
    }

    XSD_ECORE_BUILDER_CLASS = theXSDEcoreBuilderClass;
    XSD_ECORE_BUILDER_CONSTRUCTOR = theXSDEcoreBuilderConstructor;
    XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD = theXSDEcoreBuilderGenerateResourcesMethod;

    Map<Object, Object> theXSDEcoreBuilderOptions = new HashMap<Object, Object>();
    theXSDEcoreBuilderOptions.put("REUSE_REGISTERED_PACKAGES", Boolean.TRUE);
    XSD_ECORE_BUILDER_OPTIONS = Collections.unmodifiableMap(theXSDEcoreBuilderOptions);
  }

  protected ExtendedMetaData extendedMetaData;

  public DefaultEcoreBuilder(ExtendedMetaData extendedMetaData)
  {
    this.extendedMetaData = extendedMetaData;
  }
  
  public void setExtendedMetaData(ExtendedMetaData extendedMetaData)
  {
    this.extendedMetaData = extendedMetaData;    
  }

  public Collection<? extends Resource> generate(URI uri) throws Exception
  {
    return generate(Collections.singleton(uri));
  }

  public Collection<? extends Resource> generate(Map<String, URI> targetNamespaceToURI) throws Exception
  {
    if (targetNamespaceToURI != null && XSD_ECORE_BUILDER_CONSTRUCTOR != null && XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD != null)
    {
      Object ecoreBuilder = XSD_ECORE_BUILDER_CONSTRUCTOR.newInstance(new Object [] { extendedMetaData, XSD_ECORE_BUILDER_OPTIONS });
      @SuppressWarnings("unchecked") Collection<? extends Resource> result = (Collection<? extends Resource>)
        XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD.invoke(ecoreBuilder, new Object [] { targetNamespaceToURI.values() });
      return result;
    }
    return Collections.emptyList();
  }

  public Collection<? extends Resource> generate(Collection<URI> uris) throws Exception
  {
    if (uris != null && XSD_ECORE_BUILDER_CONSTRUCTOR != null && XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD != null)
    {
      Object ecoreBuilder = XSD_ECORE_BUILDER_CONSTRUCTOR.newInstance(new Object [] { extendedMetaData, XSD_ECORE_BUILDER_OPTIONS });
      @SuppressWarnings("unchecked") Collection<? extends Resource> result = (Collection<? extends Resource>)
        XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD.invoke(ecoreBuilder, new Object [] { uris });
      return result;
    }
    return Collections.emptyList();
  }
}
