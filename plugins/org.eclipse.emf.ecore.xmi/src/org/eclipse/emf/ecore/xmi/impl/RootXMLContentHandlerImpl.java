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
 * $Id: RootXMLContentHandlerImpl.java,v 1.4 2008/03/04 18:29:31 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;

/**
 * A specialized {@link ContentHandler} implementation that matches root element names and namespaces.
 */
public class RootXMLContentHandlerImpl extends XMLContentHandlerImpl
{
  /**
   * The content type identifier for content that matches this handler.
   */
  protected String contentTypeID;

  /**
   * The file extensions for which this handler applies.
   */
  protected String [] extensions;

  /**
   * The kind of content that's expected; {@link #XMI_KIND XMI content} has special handling.
   */
  protected String kind;

  /**
   * The namespace of the root element or the first non-XMI element.
   */
  protected String namespace;

  /**
   * The expected name of the root element or the first non-XMI element.
   */
  protected String [] elementNames;

  /**
   * The key in the {@link #RootXMLContentHandlerImpl(Map) constructor}'s parameter map representing the content type identifier String.
   * @see #contentTypeID
   */
  public static final String CONTENT_TYPE_ID = "contentTypeID";

  /**
   * The key in the {@link #RootXMLContentHandlerImpl(Map) constructor}'s parameter map representing the extensions
   * which are encoded as a space separate list of suffixes.
   * @see #extensions
   */
  public static final String EXTENSIONS = "extensions";

  /**
   * The key in the {@link #RootXMLContentHandlerImpl(Map) constructor}'s parameter map representing the kind of resource being processed;
   * only the value <code>xmi<code> is recognized.
   * @see #kind
   */
  public static final String KIND = "kind";
  
  /**
   * A {@link #kind} value indicating XMI content.
   */
  public static final String XMI_KIND = "xmi";

  /**
   * The key in the {@link #RootXMLContentHandlerImpl(Map) constructor}'s parameter map representing the namespace.
   * @see #namespace
   */
  public static final String NAMESPACE = "namespace";

  /**
   * A namespace value that matches any namespace.
   * @see #namespace
   */
  public static final String ANY_NAMESPACE = "##any";

  /**
   * The key in the {@link #RootXMLContentHandlerImpl(Map) constructor}'s parameter map representing the root element names
   * which are encoded as a space separated list of names.
   * @see #elementNames
   */
  public static final String ELEMENT_NAMES = "elementNames";
  

  /**
   * Creates an instance corresponding to the given parameters.
   * @param parameters the map of key value pairs.
   * @see #CONTENT_TYPE_ID
   * @see #EXTENSIONS
   * @see #KIND
   * @see #NAMESPACE
   * @see #ELEMENT_NAMES
   */
  public RootXMLContentHandlerImpl(Map<String, String> parameters)
  {
    this
      (parameters.get(CONTENT_TYPE_ID),
       parameters.containsKey(EXTENSIONS) ? parameters.get(EXTENSIONS).split(" ") : new String [0],
       parameters.get(KIND),
       parameters.get(NAMESPACE),
       parameters.containsKey(ELEMENT_NAMES) ? parameters.get(ELEMENT_NAMES).split(" ") : new String [0]);
  }

  /**
   * Creates an instance initialized with the given arguments.
   * @param contentTypeID the content type identifier for content recognized this this handler.
   * @param extensions the {@link URI#fileExtension() extensions} handled by this instance;
   * <code>null</code> or an empty array specifies that this handler applies for all URIs.
   * @param kind the kind of content that's expected; {@link #XMI_KIND} supports special handling.
   * @param namespace the expected namespace of the root element or first non-XMI element.
   * @param elementNames the expected element names of the root element or the first non-XMI element;
   * <code>null</code> or an empty array specifies that any root element name is allowed.
   */
  public RootXMLContentHandlerImpl
    (String contentTypeID,
     String [] extensions,
     String kind,
     String namespace,
     String [] elementNames)
  {
    this.contentTypeID = contentTypeID;
    this.extensions = extensions;
    this.kind = kind;
    this.namespace = namespace;
    this.elementNames = elementNames;
  }

  /**
   * Returns true if the {@link #extensions} are null or empty, of if the URI's {@link URI#fileExtension() file extension} matches one of the extension's values.
   */
  @Override
  public boolean canHandle(URI uri)
  {
    if (extensions == null || extensions.length == 0)
    {
      return true;
    }
    else
    {
      String fileExtension = uri.fileExtension();
      if (fileExtension != null)
      {
        for (String extension : extensions)
        {
          if (fileExtension.equals(extension))
          {
            return true;
          }
        }
      }
      return false;
    }
  }

  /**
   * Returns a valid content description if the XML content of the input stream has an element that matches the element names and namespace.
   */
  @Override
  public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    Map<String, Object> result = super.contentDescription(uri, inputStream, options, context);

    XMLResource xmlResource = load(uri, inputStream, options, context);
    EList<EObject> contents = xmlResource.getContents();
    if (!contents.isEmpty())
    {
      EObject eObject = contents.get(0);
      if (eObject instanceof XMLTypeDocumentRoot)
      {
        XMLTypeDocumentRoot documentRoot = (XMLTypeDocumentRoot)eObject;
        EList<EObject> rootContents = documentRoot.eContents();
        String rootElementName = null;
        String rootElementNamespace = null;
        if (!rootContents.isEmpty())
        {
          EObject root = rootContents.get(0);
          EReference eContainmentFeature = root.eContainmentFeature();
          rootElementName = eContainmentFeature.getName();
          rootElementNamespace = ExtendedMetaData.INSTANCE.getNamespace(eContainmentFeature);
          if (XMI_KIND.equals(kind) && isXMINameAndNamespace(rootElementName, rootElementNamespace))
          {
            rootContents = root.eContents();
            if (!rootContents.isEmpty())
            {
              root = rootContents.get(0);
              eContainmentFeature = root.eContainmentFeature();
              rootElementName = eContainmentFeature.getName();
              rootElementNamespace = ExtendedMetaData.INSTANCE.getNamespace(eContainmentFeature);
            }
          }
        }

        if (rootElementName != null && (ANY_NAMESPACE.equals(namespace) || (namespace == null ? rootElementNamespace == null : namespace.equals(rootElementNamespace))))
        {
          boolean elementNameMatched = false;
          if (elementNames == null || elementNames.length == 0)
          {
            elementNameMatched = true;
          }
          else
          {
            for (String elementName : elementNames)
            {
              if (rootElementName.equals(elementName))
              {
                elementNameMatched = true;
                break;
              }
            }
          }
          if (elementNameMatched)
          {
            result.put(VALIDITY_PROPERTY, ContentHandler.Validity.VALID);
            result.put(CONTENT_TYPE_PROPERTY, contentTypeID == null ? rootElementNamespace == null ? "" : rootElementNamespace : contentTypeID);
            return result;
          }
        }
      }
    }
    return result;
  }

  /**
   * A describer that {@link #createContentHandler(Map) creates} a {@link RootXMLContentHandlerImpl} instance.
   */
  public static class Describer extends ContentHandlerImpl.Describer
  {
    /**
     * Creates a {@link RootXMLContentHandlerImpl} instance.
     */
    @Override
    protected ContentHandler createContentHandler(Map<String, String> parameters)
    {
      return new RootXMLContentHandlerImpl(parameters);
    }
  }
}
