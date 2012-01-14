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
package org.eclipse.emf.mapping.ecore2xml;


import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLRegistryImpl;


/**
 * A registry for mappings from Ecore to XML.
 */
public interface Ecore2XMLRegistry extends Map<String, Object>
{
  /**
   * A descriptor for Ecore2XML mappings registered via an extension point.
   */
  public interface Descriptor
  {
    XMLResource.XMLMap getXMLMap();
  }

  /**
   * Reads and registers Ecore2XML mappings in the extension registry.
   */
  public static class Reader
  {
    protected static class Ecore2XMLDescriptor implements Descriptor
    {
      private final IConfigurationElement element;

      private XMLResource.XMLMap xmlMap = null;

      protected Ecore2XMLDescriptor(IConfigurationElement element)
      {
        super();

        this.element = element;
      }

      public XMLResource.XMLMap getXMLMap()
      {
        if (xmlMap == null)
        {
          try
          {
            xmlMap = 
              (XMLResource.XMLMap)EcoreUtil.getObjectByType
                (new ResourceSetImpl().getResource(URI.createURI(element.getAttribute(ATT_XMLMAP)), true).getContents(), 
                 Ecore2XMLPackage.eINSTANCE.getXMLMap());
          }
          catch (Exception e)
          {
            throw new WrappedException(e);
          }
        }

        return xmlMap;
      }
    }

    protected static final String TAG_ECORE2XML = "ecore2xml"; //$NON-NLS-1$

    protected static final String ATT_URI = "uri"; //$NON-NLS-1$

    protected static final String ATT_XMLMAP = "xmlmap"; //$NON-NLS-1$

    private final IExtensionRegistry extensionRegistry;

    private final String namespace;

    private final String extensionPointID;

    public Reader(IExtensionRegistry extensionRegistry, String namespace, String extensionPointID)
    {
      super();

      this.extensionRegistry = extensionRegistry;
      this.namespace = namespace;
      this.extensionPointID = extensionPointID;
    }

    protected void readElement(IConfigurationElement element)
    {
      if (TAG_ECORE2XML.equals(element.getName()))
      {
        String uri = element.getAttribute(ATT_URI);

        if (uri == null || element.getAttribute(ATT_XMLMAP) == null)
        {
          // missing attribute
        }
        else
        {
          Ecore2XMLRegistry.INSTANCE.put(uri, new Ecore2XMLDescriptor(element));
        }
      }
      else
      {
        // invalid element
      }
    }

    public void readRegistry()
    {
      IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(namespace, extensionPointID);

      if (extensionPoint != null)
      {
        IConfigurationElement[] elements = extensionPoint.getConfigurationElements();

        for (int i = 0; i < elements.length; i++)
        {
          readElement(elements[i]);
        }
      }
    }

  }

  Ecore2XMLRegistry INSTANCE = new Ecore2XMLRegistryImpl();

  XMLResource.XMLMap getXMLMap(String nsURI);
}
