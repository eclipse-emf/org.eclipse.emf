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
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescriber;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIConverter;


/**
 * An implementation of a content handler.
 */
public class ContentHandlerImpl implements ContentHandler
{
  /**
   * Creates a map with a single entry from {@link ContentHandler#VALIDITY_PROPERTY} to the given validity value.
   * @param validity the value of the validity property.
   * @return a map with a single entry from {@link ContentHandler#VALIDITY_PROPERTY} to the given validity value.
   */
  public static Map<String, Object> createContentDescription(Validity validity)
  {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put(VALIDITY_PROPERTY, validity);
    return result;
  }

  /**
   * Creates an instance.
   */
  public ContentHandlerImpl()
  {
    super();
  }

  /**
   * Returns the value of {@link ContentHandler#OPTION_REQUESTED_PROPERTIES} in the options map.
   * @param options the options in which to look up the property.
   * @return value of {@link ContentHandler#OPTION_REQUESTED_PROPERTIES} in the options map.
   */
  @SuppressWarnings("unchecked")
  protected Set<String> getRequestedProperties(Map<?, ?> options)
  {
    return (Set<String>)options.get(OPTION_REQUESTED_PROPERTIES);
  }

  /**
   * Returns whether the named property is one requested in the options.
   * @param property the property in question.
   * @param options the options in which to look for the requested property.
   * @return whether the named property is one requested in the options.
   * @see #getRequestedProperties(Map)
   */
  protected boolean isRequestedProperty(String property, Map<?, ?> options)
  {
    if (ContentHandler.VALIDITY_PROPERTY.equals(property) || ContentHandler.CONTENT_TYPE_PROPERTY.equals(property))
    {
      return  true;
    }
    else
    {
      Set<String> requestedProperties = getRequestedProperties(options);
      if (requestedProperties == null)
      {
        return true;
      }
      else
      {
        return requestedProperties.contains(property);
      }
    }
  }

  /**
   * This implementations always return true; clients are generally expected to override this.
   * @param uri the URI in questions.
   * @return true;
   */
  public boolean canHandle(URI uri)
  {
    return true;
  }

  /**
   * This base implementation handles computing the {@link ContentHandler#BYTE_ORDER_MARK_PROPERTY}, 
   * the {@link ContentHandler#CHARSET_PROPERTY character set property},
   * and the {@link ContentHandler#LINE_DELIMITER_PROPERTY line delimiter property}.
   * for each such {@link #isRequestedProperty(String, Map) requested property}.
   */
  public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    Map<String, Object> result = createContentDescription(ContentHandler.Validity.INDETERMINATE);
    if (isRequestedProperty(ContentHandler.BYTE_ORDER_MARK_PROPERTY, options))
    {
      ByteOrderMark byteOrderMark = getByteOrderMark(uri, inputStream, options, context);
      if (byteOrderMark != null)
      {
        result.put(ContentHandler.BYTE_ORDER_MARK_PROPERTY, byteOrderMark);
      }
    }
    if (isRequestedProperty(ContentHandler.CHARSET_PROPERTY, options))
    {
      String charset = getCharset(uri, inputStream, options, context);
      if (charset != null)
      {
        result.put(ContentHandler.CHARSET_PROPERTY, charset);
      }
    }
    if (isRequestedProperty(ContentHandler.LINE_DELIMITER_PROPERTY, options))
    {
      String lineDelimiter = getLineDelimiter(uri, inputStream, options, context);
      if (lineDelimiter != null)
      {
        result.put(ContentHandler.LINE_DELIMITER_PROPERTY, lineDelimiter);
      }
    }
    return result;
  }

  /**
   * Returns the character set of the input stream; this implementation simply returns null.
   * @param uri the URI of the input stream.
   * @param inputStream the input stream.
   * @param options any options that might influence the interpretation of the content.
   * @param context a cache for previously computed information.
   * @return the character set of the input stream.
   * @throws IOException if there is a problem loading the content.
   * @since 2.9
   */
  protected String getCharset(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    return null;
  }

  /**
   * Returns the line delimiter of the input stream; it's computed from the bytes interpreted using the {@link #getCharset(URI, InputStream, Map, Map) appropriate character set}.
   * @param uri the URI of the input stream.
   * @param inputStream the input stream.
   * @param options any options that might influence the interpretation of the content.
   * @param context a cache for previously computed information.
   * @return the line delimiter of the input stream.
   * @throws IOException if there is a problem loading the content.
   * @since 2.9
   */
  protected String getLineDelimiter(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    String result = (String)context.get(ContentHandler.LINE_DELIMITER_PROPERTY);
    if (result == null)
    {
      String charset = getCharset(uri, inputStream, options, context);
      if (charset != null)
      {
        result = getLineDelimiter(inputStream, charset);
        if (result != null)
        {
          context.put(ContentHandler.LINE_DELIMITER_PROPERTY, result);
        }
      }
    }
    return result;
  }

  /**
   * Returns the line delimiter of the input stream interpreted using the specified character set.
   * It is the caller's responsibility to close the input stream.
   * @since 2.9
   */
  public static String getLineDelimiter(InputStream inputStream, String charset) throws IOException
  {
    @SuppressWarnings("resource")
    Reader reader = charset == null ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, charset);
    char [] text = new char [4048];
    char target = 0;
    for (int count = reader.read(text); count > -1; count = reader.read(text))
    {
      for (int i = 0; i < count; ++i)
      {
        char character = text[i];
        if (character == '\n')
        {
          if (target == '\n')
          {
            return "\n";
          }
          else if (target == '\r')
          {
            return "\r\n";
          }
          else
          {
            target = '\n';
          }
        }
        else if (character == '\r')
        {
          if (target == '\n')
          {
            return "\n\r";
          }
          else if (target == '\r')
          {
            return "\r";
          }
          else
          {
            target = '\r';
          }
        }
      }
    }
    return null;
  }

  /**
   * Returns the byte order marker at the start of the input stream.
   * @param uri the URI of the input stream.
   * @param inputStream the input stream to scan.
   * @param options any options to influence the behavior; this base implementation ignores this.
   * @param context the cache for fetching and storing a previous computation of the byte order marker; this base implementation caches {@link ContentHandler#BYTE_ORDER_MARK_PROPERTY}.
   * @return the byte order marker at the start of the input stream.
   * @throws IOException
   */
  protected ByteOrderMark getByteOrderMark(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
  {
    ByteOrderMark result = (ByteOrderMark)context.get(ContentHandler.BYTE_ORDER_MARK_PROPERTY);
    if (result == null)
    {
      result = ByteOrderMark.read(inputStream);
      inputStream.reset();
      context.put(ContentHandler.BYTE_ORDER_MARK_PROPERTY, result);
    }
    return result;
  }

  /**
   * An implementation of a describer that delegates to a {@link ContentHandler}.
   */
  public static class Describer implements IContentDescriber, ITextContentDescriber, IExecutableExtension
  {
    /**
     * An property that, in addition to {@link IContentDescription#CHARSET} and {@link IContentDescription#BYTE_ORDER_MARK}, is supported by all EMF's content describers.
     * @since 2.9
     */
    public static final QualifiedName LINE_DELIMITER = new QualifiedName(LINE_DELIMITER_PROPERTY.substring(0, LINE_DELIMITER_PROPERTY.indexOf(':')), LINE_DELIMITER_PROPERTY.substring(LINE_DELIMITER_PROPERTY.indexOf(':') + 1)) ;
    
    /**
     * The content handler delegate.
     */
    protected ContentHandler contentHandler;

    /**
     * Returns the qualified names of the supported options.
     * This base implementation supports only {@link #LINE_DELIMITER}, {@link IContentDescription#CHARSET}, and {@link IContentDescription#BYTE_ORDER_MARK}.
     * @return the qualified names of the supported options.
     */
    public QualifiedName[] getSupportedOptions()
    {
      return SUPPORTED_OPTIONS;
    }

    /**
     * This base implementation supports only {@link #LINE_DELIMITER}, {@link IContentDescription#CHARSET}, and {@link IContentDescription#BYTE_ORDER_MARK}.
     */
    private static final QualifiedName [] SUPPORTED_OPTIONS = 
      { 
        IContentDescription.CHARSET, 
        IContentDescription.BYTE_ORDER_MARK, 
        LINE_DELIMITER
      };

    /**
     * Returns the qualified name converted to the corresponding property string.
     * @param qualifiedName the qualified name to convert.
     * @return the qualified name converted to the corresponding property string.
     */
    protected String getProperty(QualifiedName qualifiedName)
    {
      return qualifiedName.toString();
    }

    /**
     * Returns the given property's basic EMF value converted to the corresponding Eclipse value.
     * @param qualifiedName the name of the property for which this value applies.
     * @param value the value to convert.
     * @return the given property's basic EMF value converted to the corresponding Eclipse value.
     */
    protected Object getDescriptionValue(QualifiedName qualifiedName, Object value)
    {
      if (value == null)
      {
        return null;
      }
      else if (IContentDescription.BYTE_ORDER_MARK.equals(qualifiedName))
      {
        return ((ContentHandler.ByteOrderMark)value).bytes();
      }
      else
      {
        return value;
      }
    }

    public int describe(InputStream inputStream, IContentDescription description) throws IOException
    {
      Map<Object, Object> options = new HashMap<Object, Object>();
      Map<String, ?> result;
      if (description != null)
      {
        Map<String, QualifiedName> requestedPropertyToQualifiedNameMap = new HashMap<String, QualifiedName>();
        Set<String> requestedProperties = new HashSet<String>();
        for (QualifiedName qualifiedName : getSupportedOptions())
        {
          if (description.isRequested(qualifiedName))
          {
            String property = getProperty(qualifiedName);
            if (property != null)
            {
              requestedPropertyToQualifiedNameMap.put(property, qualifiedName);
              requestedProperties.add(property);
            }
          }
        }
        options.put(ContentHandler.OPTION_REQUESTED_PROPERTIES, requestedProperties);
        result = contentHandler.contentDescription(URI.createURI("*"), inputStream, options, new HashMap<Object, Object>());
        for (Map.Entry<String, ?> property : result.entrySet())
        {
          QualifiedName qualifiedName = requestedPropertyToQualifiedNameMap.get(property.getKey());
          if (qualifiedName != null)
          {
            Object value = getDescriptionValue(qualifiedName, property.getValue());
            if (value != null)
            {
              description.setProperty(qualifiedName, value);
            }
          }
        }
      }
      else
      {
        options.put(ContentHandler.OPTION_REQUESTED_PROPERTIES, Collections.emptySet());
        result = contentHandler.contentDescription(URI.createURI("*"), inputStream, options, new HashMap<Object, Object>());
      }
      return ((ContentHandler.Validity)result.get(ContentHandler.VALIDITY_PROPERTY)).ordinal();
    }

    public int describe(Reader reader, IContentDescription description) throws IOException
    {
      return describe(new URIConverter.ReadableInputStream(reader), description);
    }

    public void setInitializationData(IConfigurationElement configurationElement, String propertyName, Object data) throws CoreException
    {
      Map<String, String> parameters = getParameters(configurationElement, propertyName, data);
      contentHandler = createContentHandler(parameters);
    }

    /**
     * Returns the new content handler for the given parameters that were supplied by the registration of the Eclipse content type.
     * @param parameters the parameter for configuring the content handler.
     * @return the next content handler.
     */
    protected ContentHandler createContentHandler(Map<String, String> parameters)
    {
      return null;
    }

    /**
     * The key in the {@link #getParameters(IConfigurationElement, String, Object) parameters map} representing the content type identifier String.
     */
    protected static final String CONTENT_TYPE_ID = "contentTypeID";

    /**
     * The key in the {@link #getParameters(IConfigurationElement, String, Object) parameters map} representing the extensions,
     * which are encoded as a space separate list of suffixes.
     */
    protected static final String EXTENSIONS = "extensions";

    /**
     * Returns the map of parameters as fetched from the given configuration element's information.
     * This implementation populates the {@link #CONTENT_TYPE_ID content type identifier} and the {@link #EXTENSIONS extensions}.
     * @param configurationElement the configuration element of the content type.
     * @param propertyName the property for this particular for this instance.
     * @param data the data associated with this instance.
     * @return the map of parameters as fetched from the given configuration element's information.
     */
    protected Map<String, String> getParameters(IConfigurationElement configurationElement, String propertyName, Object data)
    {
      Map<String, String> parameters = new HashMap<String, String>();
      if (data != null)
      {
        @SuppressWarnings("unchecked")
        Map<String,String> dataMap = (Map<String,String>)data;
        parameters.putAll(dataMap);
        parameters.put(CONTENT_TYPE_ID, configurationElement.getAttribute("id"));
        String fileExtensions = configurationElement.getAttribute("file-extensions");
        if (fileExtensions != null)
        {
          parameters.put(EXTENSIONS, fileExtensions.replace(',', ' '));
        }
      }
      return parameters;
    }
  }
}
