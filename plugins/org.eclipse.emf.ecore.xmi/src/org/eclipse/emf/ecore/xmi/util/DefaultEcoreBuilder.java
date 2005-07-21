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
 * $Id: DefaultEcoreBuilder.java,v 1.1 2005/07/21 19:47:33 elena Exp $
 */
package org.eclipse.emf.ecore.xmi.util;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.EcoreBuilder;
import org.eclipse.emf.ecore.xmi.XMIPlugin;


/**
 * Default implementation of XML Schema to Ecore builder.
 * {@link org.eclipse.xsd.ecore.XSDEcoreBuilder}
 *
 */
public class DefaultEcoreBuilder implements EcoreBuilder
{
  protected static final Class XSD_ECORE_BUILDER_CLASS;

  protected static final Constructor XSD_ECORE_BUILDER_CONSTRUCTOR;

  protected static final Method XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD;

  static
  {
    Class theXSDEcoreBuilderClass = null;
    Constructor theXSDEcoreBuilderConstructor = null;
    Method theXSDEcoreBuilderGenerateResourcesMethod = null;

    try
    {
      theXSDEcoreBuilderClass = CommonPlugin.loadClass("org.eclipse.xsd", "org.eclipse.xsd.ecore.XSDEcoreBuilder");
      theXSDEcoreBuilderConstructor = theXSDEcoreBuilderClass.getConstructor(new Class []{ ExtendedMetaData.class });
      theXSDEcoreBuilderGenerateResourcesMethod = theXSDEcoreBuilderClass.getMethod("generateResources", new Class []{ Collection.class });
    }
    catch (Exception exception)
    {
      XMIPlugin.INSTANCE.log(exception);
      exception.printStackTrace();
    }

    XSD_ECORE_BUILDER_CLASS = theXSDEcoreBuilderClass;
    XSD_ECORE_BUILDER_CONSTRUCTOR = theXSDEcoreBuilderConstructor;
    XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD = theXSDEcoreBuilderGenerateResourcesMethod;
  }

  protected Object ecoreBuilder;

  protected Collection generatedResources;

  protected ExtendedMetaData extendedMetaData;

  public DefaultEcoreBuilder(ExtendedMetaData extendedMetaData)
  {
    this.extendedMetaData = extendedMetaData;
  }

  public Collection generate(URI uri) throws Exception
  {
    return generate(Collections.singleton(uri));
  }

  public Collection generate(Map targetNamespaceToURI) throws Exception
  {
    if (targetNamespaceToURI != null && XSD_ECORE_BUILDER_CONSTRUCTOR != null && XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD != null)
    {
      ecoreBuilder = XSD_ECORE_BUILDER_CONSTRUCTOR.newInstance(new Object []{ extendedMetaData });
      return (Collection)XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD.invoke(ecoreBuilder, new Object []{ targetNamespaceToURI.values() });
    }
    return Collections.EMPTY_LIST;
  }

  public Collection generate(Collection uris) throws Exception
  {
    if (uris != null && XSD_ECORE_BUILDER_CONSTRUCTOR != null && XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD != null)
    {
      ecoreBuilder = XSD_ECORE_BUILDER_CONSTRUCTOR.newInstance(new Object []{ extendedMetaData });
      return (Collection)XSD_ECORE_BUILDER_GENERATE_RESOURCES_METHOD.invoke(ecoreBuilder, new Object []{ uris });
    }
    return Collections.EMPTY_LIST;
  }
}
