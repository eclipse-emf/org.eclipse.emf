/**
 * Copyright (c) 2007-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.resource.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;

public class PlatformContentHandlerImpl extends ContentHandlerImpl
{
  private static final Map<String, QualifiedName> PROPERTY_CACHE = new HashMap<String, QualifiedName>();

  /**
   * Returns the property name converted to a qualified name.
   * @param property the property to convert.
   * @return the property name converted to a qualified name.
   */
  protected QualifiedName getQualifiedName(String property)
  {
    QualifiedName result = PROPERTY_CACHE.get(property);
    if (result == null)
    {
      int index = property.lastIndexOf(":");
      if (index == -1)
      {
        result = new QualifiedName(null, property);
      }
      else
      {
        result = new QualifiedName(property.substring(0, index), property.substring(index + 1));
      }
      PROPERTY_CACHE.put(property, result);
    }
    return result;
  }

  /**
   * Returns the given property's Eclipse value converted to EMF's corresponding basic value.
   * @param qualifiedName the name of the property for which this value applies.
   * @param value the value to convert.
   * @return the given property's Eclipse value converted to EMF's corresponding basic value.
   */
  protected Object getDescriptionValue(QualifiedName qualifiedName, Object value)
  {
    if (value == null)
    {
      return null;
    }
    else if (IContentDescription.BYTE_ORDER_MARK.equals(qualifiedName))
    {
      for (ByteOrderMark byteOrderMarker : ContentHandler.ByteOrderMark.values())
      {
        if (value == byteOrderMarker.bytes())
        {
          return byteOrderMarker;
        }
      }
      return null;
    }
    else
    {
      return value;
    }
  }

  /**
   * This implementation delegates to the platform's content description support,
   */
  @Override
  public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    IContentDescription contentDescription;
    try
    {
      if (uri.isPlatformResource() && PlatformResourceURIHandlerImpl.workspaceRoot != null)
      {
        contentDescription = PlatformResourceURIHandlerImpl.WorkbenchHelper.getContentDescription(uri.toPlatformString(true), options);
      }
      else
      {
        contentDescription = Platform.getContentTypeManager().getDescriptionFor(inputStream, uri.lastSegment(), IContentDescription.ALL);
      }
    }
    catch (Throwable exception)
    {
      return super.contentDescription(uri, inputStream, options, context);
    }

    if (contentDescription == null)
    {
      return INVALID_CONTENT_DESCRIPTION;
    }
    else
    {
      Map<String, Object> result = createContentDescription(ContentHandler.Validity.VALID);
      result.put(ContentHandler.CONTENT_TYPE_PROPERTY, contentDescription.getContentType().getId());
      Set<String> requestedProperties = getRequestedProperties(options);
      if (requestedProperties == null)
      {
        Object bom = getDescriptionValue(IContentDescription.BYTE_ORDER_MARK, contentDescription.getProperty(IContentDescription.BYTE_ORDER_MARK));
        if (bom != null)
        {
          result.put(ContentHandler.BYTE_ORDER_MARK_PROPERTY, bom);
        }
        Object charset = getDescriptionValue(IContentDescription.CHARSET, contentDescription.getProperty(IContentDescription.CHARSET));
        if (charset != null)
        {
          result.put(ContentHandler.CHARSET_PROPERTY, charset);
        }
        Object lineDelimiter = getDescriptionValue(Describer.LINE_DELIMITER, contentDescription.getProperty(Describer.LINE_DELIMITER));
        if (lineDelimiter == null)
        {
          lineDelimiter = getLineDelimiter(uri, inputStream, options, context);
        }
        if (lineDelimiter != null)
        {
          result.put(ContentHandler.LINE_DELIMITER_PROPERTY, lineDelimiter);
        }
      }
      else
      {
        for (String property : requestedProperties)
        {
          if (ContentHandler.LINE_DELIMITER_PROPERTY.equals(property))
          {
            Object lineDelimiter = getDescriptionValue(Describer.LINE_DELIMITER, contentDescription.getProperty(Describer.LINE_DELIMITER));
            if (lineDelimiter == null)
            {
              lineDelimiter = getLineDelimiter(uri, inputStream, options, context);
            }
            if (lineDelimiter != null)
            {
              result.put(property, lineDelimiter);
            }
          }
          else
          {
            QualifiedName qualifiedName = getQualifiedName(property);
            if (qualifiedName != null)
            {
              Object value = getDescriptionValue(qualifiedName, contentDescription.getProperty(qualifiedName));
              if (value != null)
              {
                result.put(property, value);
              }
            }
          }
        }
      }
      return result;
    }
  }

  /**
   * This implementation only gets called when platform's content describer throws an exception, i.e., when the resource doesn't exist.
   * For {@link URI#isPlatformResource() platform resource URIs}, it determines the character set from the workspace.
   * @since 2.9
   */
  @Override
  protected String getCharset(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    if (uri.isPlatformResource() && PlatformResourceURIHandlerImpl.workspaceRoot != null)
    {
      return PlatformResourceURIHandlerImpl.WorkbenchHelper.getCharset(uri.toPlatformString(true), options);
    }
    else
    {
      return super.getCharset(uri, inputStream, options, context);
    }
  }

  /**
   * This implementation only gets called when platform's content describer throws an exception, i.e., when the resource doesn't exist.
   * For {@link URI#isPlatformResource() platform resource URIs}, it determines the line delimiter from the project/workspace preferences.
   * @since 2.9
   */
  @Override
  protected String getLineDelimiter(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    if (uri.isPlatformResource() && PlatformResourceURIHandlerImpl.workspaceRoot != null)
    {
      return PlatformResourceURIHandlerImpl.WorkbenchHelper.getLineDelimiter(uri.toPlatformString(true), options);
    }
    else
    {
      return super.getCharset(uri, inputStream, options, context);
    }
  }
}
