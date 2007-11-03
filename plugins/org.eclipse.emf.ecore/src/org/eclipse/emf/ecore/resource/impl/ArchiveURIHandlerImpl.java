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
 * $Id: ArchiveURIHandlerImpl.java,v 1.3 2007/11/03 13:15:48 emerks Exp $
 */
package org.eclipse.emf.ecore.resource.impl;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;

import org.eclipse.emf.common.archive.ArchiveURLConnection;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;


public class ArchiveURIHandlerImpl extends URIHandlerImpl
{
  /**
   * Creates an instance.
   */
  public ArchiveURIHandlerImpl()
  {
    super();
  }

  @Override
  public boolean canHandle(URI uri)
  {
    return "archive".equals(uri.scheme());
  }

  @Override
  public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException
  {
    return createArchive(uri, options).getOutputStream();
  }

  @Override
  public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException
  {
    return createArchive(uri, options).getInputStream();
  }

  @Override
  public void delete(URI uri, Map<?, ?> options) throws IOException
  {
    createArchive(uri, options).delete();
  }

  @Override
  public boolean exists(URI uri, Map<?, ?> options)
  {
    try
    {
      InputStream inputStream = createInputStream(uri, options);
      inputStream.close();
      return true;
    }
    catch (IOException exception)
    {
      return false;
    }
  }

  @Override
  public Map<String, ?> getAttributes(URI uri, Map<?, ?> options)
  {
    return createArchive(uri, options).getAttributes();
  }

  @Override
  public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException
  {
    createArchive(uri, options).setAttributes(attributes);
  }

  /**
   * A specialized class for reading from an archive.
   */
  protected class Archive extends ArchiveURLConnection
  {
    protected Map<?, ?> options;
    protected ZipEntry zipEntry;

    public Archive(URI uri, Map<?, ?> options)
    {
      super(uri.toString());
      this.options = options;
    }
    
    @Override
    protected boolean emulateArchiveScheme()
    {
      return false;
    }
    
    @Override
    protected boolean useZipFile()
    {
      return true;
    }
    
    @Override
    protected InputStream createInputStream(String nestedURL) throws IOException
    {
      return getURIConverter(options).createInputStream(URI.createURI(nestedURL), options);
    }
    
    @Override
    protected OutputStream createOutputStream(String nestedURL) throws IOException
    {
      return getURIConverter(options).createOutputStream(URI.createURI(nestedURL), options);
    }
    @Override
    protected InputStream yield(ZipEntry zipEntry, InputStream inputStream) throws IOException
    {
      this.zipEntry = zipEntry;
      Map<Object, Object> response = getResponse(options);
      if (response != null)
      {
        response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, zipEntry.getTime());
      }
      return super.yield(zipEntry, inputStream);
    }
    
    @Override
    protected OutputStream yield(ZipEntry zipEntry, OutputStream outputStream) throws IOException
    {
      this.zipEntry = zipEntry;
      Map<Object, Object> response = getResponse(options);
      if (response != null)
      {
        response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, zipEntry.getTime());
      }
      return super.yield(zipEntry, outputStream);
    }
    
    public Map<String, ?> getAttributes()
    {
      Map<String, Object> result = new HashMap<String, Object>();
      try
      {
        Set<String> requestedAttributes = getRequestedAttributes(options);
        if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_READ_ONLY))
        {
          Set<String> requestedSubAttributes = new HashSet<String>();
          requestedSubAttributes.add(URIConverter.ATTRIBUTE_READ_ONLY);
          Map<Object, Object> subOptions = new ExtensibleURIConverterImpl.OptionsMap(URIConverter.OPTION_REQUESTED_ATTRIBUTES, requestedSubAttributes, options);
          result.putAll(getURIConverter(subOptions).getAttributes(URI.createURI(getNestedURL()), subOptions));
        }

        InputStream inputStream = null;
        if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_DIRECTORY))
        {
          inputStream = getInputStream();
          inputStream.close();
          result.put(URIConverter.ATTRIBUTE_DIRECTORY, zipEntry.isDirectory());
        }
        if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_LENGTH))
        {
          if (inputStream == null)
          {
            inputStream = getInputStream();
            inputStream.close();
          }
          result.put(URIConverter.ATTRIBUTE_LENGTH, zipEntry.getSize());
        }
        if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_TIME_STAMP))
        {
          if (inputStream == null)
          {
            inputStream = getInputStream();
            inputStream.close();
          }
          result.put(URIConverter.ATTRIBUTE_TIME_STAMP, zipEntry.getTime());
        }
      }
      catch (IOException exception)
      {
        // Ignore exceptions.
      }
      return result;
    }

    public void setAttributes(Map<String, ?> attributes) throws IOException
    {
      Long timeStamp = (Long)attributes.get(URIConverter.ATTRIBUTE_TIME_STAMP);
      if (timeStamp != null)
      {
        setTimeStamp(timeStamp);
      }
    }
  }

  protected Archive createArchive(URI uri, Map<?, ?> options)
  {
    return new Archive(uri, options);
  }
}
