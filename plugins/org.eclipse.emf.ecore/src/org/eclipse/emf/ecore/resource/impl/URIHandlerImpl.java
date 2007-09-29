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
 * $Id: URIHandlerImpl.java,v 1.1 2007/09/29 16:41:42 emerks Exp $
 */
package org.eclipse.emf.ecore.resource.impl;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;


/**
 * An implementation of a {@link URIHandler URI handler}.
 *
 */
public class URIHandlerImpl implements URIHandler
{
  /**
   * Creates an instance.
   */
  public URIHandlerImpl()
  {
    super();
  }

  /**
   * This implementation always returns true; clients are generally expected to override this.
   */
  public boolean canHandle(URI uri)
  {
    return true;
  }

  /**
   * Returns the value of the {@link URIConverter#OPTION_URI_CONVERTER URI converter option}.
   * @param options the options in which to look for the URI converter.
   * @return the value of the URI converter option.
   */
  protected URIConverter getURIConverter(Map<?, ?> options)
  {
    return (URIConverter)options.get(URIConverter.OPTION_URI_CONVERTER);
  }

  /**
   * Returns the value of the {@link URIConverter#OPTION_RESPONSE response option}.
   * @param options the options in which to look for the response option.
   * @return the value of the response option.
   */
  @SuppressWarnings("unchecked")
  protected Map<Object, Object> getResponse(Map<?, ?> options)
  {
    return (Map<Object, Object>)options.get(URIConverter.OPTION_RESPONSE);
  }

  /**
   * Creates an output stream for the URI, assuming it's a URL, and returns it.
   * Specialized support is provided for HTTP URLs.
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   */
  public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException
  {
    try
    {
      URL url = new URL(uri.toString());
      final URLConnection urlConnection = url.openConnection();
      urlConnection.setDoOutput(true);
      if (urlConnection instanceof HttpURLConnection)
      {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
        httpURLConnection.setRequestMethod("PUT");
        return
          new FilterOutputStream(urlConnection.getOutputStream())
          {
            @Override
            public void close() throws IOException
            {
              super.close();
              int responseCode = httpURLConnection.getResponseCode();
              switch (responseCode)
              {
                case HttpURLConnection.HTTP_OK:
                case HttpURLConnection.HTTP_CREATED:
                case HttpURLConnection.HTTP_NO_CONTENT:
                {
                  break;
                }
                default:
                {
                  throw new IOException("PUT failed with HTTP response code " + responseCode);
                }
              }
            }
          };
      }
      else
      {
        OutputStream result = urlConnection.getOutputStream();
        final Map<Object, Object> response = getResponse(options);
        if (response != null)
        {
          result = 
            new FilterOutputStream(result)
            {
              @Override
              public void close() throws IOException
              {
                try
                {
                  super.close();
                }
                finally
                {
                  if (response != null)
                  {
                    response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, urlConnection.getLastModified());
                  }
                }
              }
            };
        }
        return result;
      }
    }
    catch (RuntimeException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
  }

  /**
   * Creates an input stream for the URI, assuming it's a URL, and returns it.
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   */
  public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException
  {
    try
    {
      URL url = new URL(uri.toString());
      final URLConnection urlConnection = url.openConnection();
      InputStream result = urlConnection.getInputStream();
      Map<Object, Object> response = getResponse(options);
      if (response != null)
      {
        response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, urlConnection.getLastModified());
      }
      return result;
    }
    catch (RuntimeException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
  }

  /**
   * Only HTTP connections support delete.
   */
  public void delete(URI uri, Map<?, ?> options) throws IOException
  {
    try
    {
      URL url = new URL(uri.toString());
      URLConnection urlConnection = url.openConnection();
      urlConnection.setDoOutput(true);
      if (urlConnection instanceof HttpURLConnection)
      {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
        httpURLConnection.setRequestMethod("DELETE");
        int responseCode = httpURLConnection.getResponseCode();
        switch (responseCode)
        {
          case HttpURLConnection.HTTP_OK:
          case HttpURLConnection.HTTP_ACCEPTED:
          case HttpURLConnection.HTTP_NO_CONTENT:
          {
            break;
          }
          default:
          {
            throw new IOException("DELETE failed with HTTP response code " + responseCode);
          }
        }
      }
      else
      {
        throw new IOException("Delete is not supported for " + uri);
      }
    }
    catch (RuntimeException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
  }

  /**
   * This implementation delegates to the {@link #getURIConverter(Map) URI converter}'s {@link URIConverter#getContentHandlers() content handlers}.
   */
  public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException
  {
    URIConverter uriConverter = (URIConverter)options.get(URIConverter.OPTION_URI_CONVERTER);
    InputStream inputStream = null;
    Map<String, ?> result = null;
    Map<Object, Object> context = new HashMap<Object, Object>();
    try
    {
      for (ContentHandler contentHandler : uriConverter.getContentHandlers())
      {
        if (contentHandler.canHandle(uri))
        {
          if (inputStream == null)
          {
            try
            {
              inputStream = createInputStream(uri, options);
            }
            catch (IOException exception)
            {
              inputStream = new ByteArrayInputStream(new byte [0]);
            }
            if (!inputStream.markSupported())
            {
              inputStream = new BufferedInputStream(inputStream);
            }
            inputStream.mark(Integer.MAX_VALUE);
          }
          else
          {
            inputStream.reset();
          }
          Map<String, ?> contentDescription = contentHandler.contentDescription(uri, inputStream, options, context);
          switch ((ContentHandler.Validity)contentDescription.get(ContentHandler.VALIDITY_PROPERTY))
          {
            case VALID:
            {
              return contentDescription;
            }
            case INDETERMINATE:
            {
              if (result == null)
              {
                result = contentDescription;
              }
              break;
            }
          }
        }
      }
    }
    finally
    {
      if (inputStream != null)
      {
        inputStream.close();
      }
    }

    return result == null ? ContentHandler.INVALID_CONTENT_DESCRIPTION : result;
  }

  /**
   * If a stream can be created the file exists.
   * Specialized support is provided for HTTP connections to avoid fetching the whole stream in that case.
   */
  public boolean exists(URI uri, Map<?, ?> options)
  {
    try
    {
      URL url = new URL(uri.toString());
      URLConnection urlConnection = url.openConnection();
      if (urlConnection instanceof HttpURLConnection)
      {
        HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
        httpURLConnection.setRequestMethod("HEAD");
        int responseCode = httpURLConnection.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_OK;
      }
      else
      {
        InputStream inputStream = urlConnection.getInputStream();
        inputStream.close();
        return true;
      }
    }
    catch (Throwable exception)
    {
      return false;
    }
  }

  /**
   * This generally returns <code>true</code>, except for HTTP connections where it's possible to determine if HTTP PUT is supported.
   */
  public boolean isReadOnly(URI uri, Map<?, ?> options)
  {
    try
    {
      URL url = new URL(uri.toString());
      URLConnection urlConnection = url.openConnection();
      if (urlConnection instanceof HttpURLConnection)
      {
        HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
        httpURLConnection.setRequestMethod("OPTIONS");
        int responseCode = httpURLConnection.getResponseCode();
        return
          responseCode != HttpURLConnection.HTTP_OK ||
            !httpURLConnection.getHeaderFields().containsKey("Allow") ||
            !httpURLConnection.getHeaderField("Allow").contains("PUT");
      }
      else
      {
        return true;
      }
    }
    catch (Throwable exception)
    {
      return true;
    }
  }

  public long timeStamp(URI uri, Map<?, ?> options)
  {
    try
    {
      URL url = new URL(uri.toString());
      URLConnection urlConnection = url.openConnection();
      if (urlConnection instanceof HttpURLConnection)
      {
        HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
        httpURLConnection.setRequestMethod("HEAD");
      }
      return urlConnection.getLastModified();
    }
    catch (IOException exception)
    {
      return URIConverter.NULL_TIME_STAMP;
    }
  }
}
