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
 * $Id: ArchiveURIHandlerImpl.java,v 1.1 2007/09/29 16:41:41 emerks Exp $
 */
package org.eclipse.emf.ecore.resource.impl;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
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
  public boolean isReadOnly(URI uri, Map<?, ?> options)
  {
    return createArchive(uri, options).isReadOnly();
  }
  
  /**
   * A specialized class for reading from an archive.
   */
  protected class Archive extends ArchiveURLConnection
  {
    protected Map<?, ?> options;

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
      Map<Object, Object> response = getResponse(options);
      if (response != null)
      {
        response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, zipEntry.getTime());
      }
      return super.yield(zipEntry, outputStream);
    }
    
    public boolean isReadOnly()
    {
      try
      {
        return getURIConverter(options).isReadOnly(URI.createURI(getNestedURL()), options);
      }
      catch (IOException e)
      {
        return true;
      }
    }
  }
  
  protected Archive createArchive(URI uri, Map<?, ?> options)
  {
    return new Archive(uri, options);
  }
}
