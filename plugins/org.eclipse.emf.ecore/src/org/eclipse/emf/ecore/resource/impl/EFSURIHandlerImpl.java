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
 * $Id: EFSURIHandlerImpl.java,v 1.1 2007/09/29 16:41:42 emerks Exp $
 */
package org.eclipse.emf.ecore.resource.impl;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;


public class EFSURIHandlerImpl extends URIHandlerImpl
{
  private static Map<String, Boolean> efsScheme;
  private static final Method EFS_GET_FILE_SYSTEM_METHOD;
  private static final Method EFS_GET_STORE_METHOD;
  private static final Method FILE_STORE_OPEN_INPUT_STREAM_METHOD;
  private static final Method FILE_STORE_OPEN_OUTPUT_STREAM_METHOD;
  private static final Method FILE_STORE_DELETE_METHOD;
  private static final Method FILE_STORE_FETCH_INFO_METHOD;
  private static final Method FILE_INFO_EXISTS_METHOD;
  private static final Method FILE_INFO_GET_ATTRIBUTE_METHOD;
  private static final Method FILE_INFO_GET_LAST_MODIFIED;
  static
  {
    Method efsGetStoreMethod = null;
    Method efsGetFileSystemMethod = null;
    Method fileStoreOpenInputStreamMethod = null;
    Method fileStoreOpenOutputStreamMethod = null;
    Method fileStoreDeleteMethod = null;
    Method fileStoreFetchInfoMethod = null;
    Method fileInfoExistsMethod = null;
    Method fileInfoGetAttributeMethod = null;
    Method fileInfoGetLastModifiedMethod = null;
    try
    {
      Class <?> efsClass  = CommonPlugin.loadClass("org.eclipse.core.filesystem", "org.eclipse.core.filesystem.EFS");
      efsGetStoreMethod = efsClass.getMethod("getStore", java.net.URI.class);
      efsGetFileSystemMethod = efsClass.getMethod("getFileSystem", String.class);
      Class <?> fileStoreClass = efsGetStoreMethod.getReturnType();
      fileStoreOpenInputStreamMethod = fileStoreClass.getMethod("openInputStream", Integer.TYPE, IProgressMonitor.class);
      fileStoreOpenOutputStreamMethod = fileStoreClass.getMethod("openOutputStream", Integer.TYPE, IProgressMonitor.class);
      fileStoreDeleteMethod = fileStoreClass.getMethod("delete", Integer.TYPE, IProgressMonitor.class);
      fileStoreFetchInfoMethod = fileStoreClass.getMethod("fetchInfo");
      Class <?> fileInfoClass = fileStoreFetchInfoMethod.getReturnType();
      fileInfoExistsMethod = fileInfoClass.getMethod("exists");
      fileInfoGetAttributeMethod = fileInfoClass.getMethod("getAttribute", Integer.TYPE);
      fileInfoGetLastModifiedMethod = fileInfoClass.getMethod("getLastModified");
    }
    catch (Throwable exeption)
    {
      // Ignore any exceptions and assume the class isn't available.
    }
    EFS_GET_STORE_METHOD = efsGetStoreMethod;
    EFS_GET_FILE_SYSTEM_METHOD = efsGetFileSystemMethod;
    FILE_STORE_OPEN_INPUT_STREAM_METHOD = fileStoreOpenInputStreamMethod;
    FILE_STORE_OPEN_OUTPUT_STREAM_METHOD = fileStoreOpenOutputStreamMethod;
    FILE_STORE_DELETE_METHOD = fileStoreDeleteMethod;
    FILE_STORE_FETCH_INFO_METHOD = fileStoreFetchInfoMethod;
    FILE_INFO_EXISTS_METHOD = fileInfoExistsMethod;
    FILE_INFO_GET_ATTRIBUTE_METHOD = fileInfoGetAttributeMethod;
    FILE_INFO_GET_LAST_MODIFIED = fileInfoGetLastModifiedMethod;
  }

  /**
   * Creates an instance.
   */
  public EFSURIHandlerImpl()
  {
    super();
  }
  
  /**
   * Returns whether the URI is one that this implementation should treat as a supported Eclipse File System scheme.
   * This implementation uses Java reflection to check whether there is an Eclipse File System available 
   * and if so whether it supports this scheme.
   * @param uri the URI to consider.
   * @return whether the URI is one that this implementation treats as an Eclipse File System scheme.
   */
  @Override
  public boolean canHandle(URI uri)
  {
    String scheme = uri.scheme();
    if (scheme == null || EFS_GET_FILE_SYSTEM_METHOD == null)
    {
      return false;
    }
    else
    {
      Boolean result = efsScheme == null ? null : efsScheme.get(scheme);
      if (result == null)
      {
        try
        {
          result = EFS_GET_FILE_SYSTEM_METHOD.invoke(null, scheme) != null;
        }
        catch (Throwable exception)
        {
          result = Boolean.FALSE;
        }
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if (efsScheme != null)
        {
          map.putAll(efsScheme);
        }
        map.put(scheme, result);
        efsScheme = map;
      }
      return result == Boolean.TRUE;
    }
  }

  protected Object getStore(URI uri, Map<?, ?> options) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException
  {
    if (EFS_GET_STORE_METHOD != null)
    {
      Object store = EFS_GET_STORE_METHOD.invoke(null, new java.net.URI(uri.toString()));
      if (store != null)
      {
        return store;
      }
    }
    throw new IOException("EFS unavailable");
  }

  protected Object getInfo(URI uri, Map<?, ?> options) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException
  {
    return FILE_STORE_FETCH_INFO_METHOD.invoke(getStore(uri, options));
  }
  
  /**
   * Creates an output stream for the URI, assuming it's a URI recognized by the Eclipse File System, and returns it.
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   */
  @Override
  public OutputStream createOutputStream(final URI uri, final Map<?, ?> options) throws IOException
  {
    try
    {
      OutputStream result = (OutputStream)FILE_STORE_OPEN_OUTPUT_STREAM_METHOD.invoke(getStore(uri, options), 0, null);
      final Map<Object, Object> response = getResponse(options);
      if (response != null)
      {
        result = 
          new BufferedOutputStream(result)
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
                response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, timeStamp(uri, options));
              }
            }
          };
      }
      return result;
    }
    catch (IllegalArgumentException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
    catch (IllegalAccessException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
    catch (InvocationTargetException exception)
    {
      throw new Resource.IOWrappedException(exception.getCause());
    }
    catch (URISyntaxException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
  }

  /**
   * Creates an input stream for the URI, assuming it's a URI recognized by the Eclipse File System, and returns it.
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   */
  @Override
  public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException
  {
    try
    {
      InputStream result = (InputStream)FILE_STORE_OPEN_INPUT_STREAM_METHOD.invoke(getStore(uri, options), 0, null);
      final Map<Object, Object> response = getResponse(options);
      if (response != null)
      {
        response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, timeStamp(uri, options));
      }
      return result;
    }
    catch (IllegalArgumentException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
    catch (IllegalAccessException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
    catch (InvocationTargetException exception)
    {
      throw new Resource.IOWrappedException(exception.getCause());
    }
    catch (URISyntaxException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
  }

  @Override
  public void delete(URI uri, Map<?, ?> options) throws IOException
  {
    try
    {
      FILE_STORE_DELETE_METHOD.invoke(getStore(uri, options), 0, null);
    }
    catch (IllegalArgumentException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
    catch (IllegalAccessException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
    catch (InvocationTargetException exception)
    {
      throw new Resource.IOWrappedException(exception.getCause());
    }
    catch (URISyntaxException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
  }

  @Override
  public boolean exists(URI uri, Map<?, ?> options)
  {
    try
    {
      return Boolean.TRUE.equals(FILE_INFO_EXISTS_METHOD.invoke(getInfo(uri, options)));
    }
    catch (Exception exception)
    {
      return false;
    }
  }

  private static final Integer ATTRIBUTE_READ_ONLY = 1 << 1;

  @Override
  public boolean isReadOnly(URI uri, Map<?, ?> options)
  {
    try
    {
      return Boolean.TRUE.equals(FILE_INFO_GET_ATTRIBUTE_METHOD.invoke(getInfo(uri, options), ATTRIBUTE_READ_ONLY));
    }
    catch (Exception exception)
    {
      return true;
    }
  }

  @Override
  public long timeStamp(URI uri, Map<?, ?> options)
  {
    try
    {
      return (Long)FILE_INFO_GET_LAST_MODIFIED.invoke(getInfo(uri, options));
    }
    catch (Exception exception)
    {
      return URIConverter.NULL_TIME_STAMP;
    }
  }
}
