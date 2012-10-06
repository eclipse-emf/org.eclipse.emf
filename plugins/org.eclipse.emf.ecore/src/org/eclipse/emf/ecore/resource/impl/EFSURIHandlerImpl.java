/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
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
import java.util.Set;

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
  private static final Method FILE_STORE_PUT_INFO_METHOD;
  private static final Method FILE_INFO_EXISTS_METHOD;
  private static final Method FILE_INFO_GET_LENGTH_METHOD;
  private static final Method FILE_INFO_IS_DIRECOTRY_METHOD;
  private static final Method FILE_INFO_GET_ATTRIBUTE_METHOD;
  private static final Method FILE_INFO_SET_ATTRIBUTE_METHOD;
  private static final Method FILE_INFO_GET_LAST_MODIFIED;
  private static final Method FILE_INFO_SET_LAST_MODIFIED;
  static
  {
    Method efsGetStoreMethod = null;
    Method efsGetFileSystemMethod = null;
    Method fileStoreOpenInputStreamMethod = null;
    Method fileStoreOpenOutputStreamMethod = null;
    Method fileStoreDeleteMethod = null;
    Method fileStoreFetchInfoMethod = null;
    Method fileStorePutInfoMethod = null;
    Method fileInfoExistsMethod = null;
    Method fileInfoIsDirectoryMethod = null;
    Method fileInfoGetLengthMethod = null;
    Method fileInfoGetAttributeMethod = null;
    Method fileInfoSetAttributeMethod = null;
    Method fileInfoGetLastModifiedMethod = null;
    Method fileInfoSetLastModifiedMethod = null;
    if (CommonPlugin.IS_RESOURCES_BUNDLE_AVAILABLE)
    {
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
        fileStorePutInfoMethod = fileStoreClass.getMethod("putInfo", fileInfoClass, Integer.TYPE, IProgressMonitor.class);
        fileInfoExistsMethod = fileInfoClass.getMethod("exists");
        fileInfoIsDirectoryMethod = fileInfoClass.getMethod("isDirectory");
        fileInfoGetLengthMethod = fileInfoClass.getMethod("getLength");
        fileInfoGetAttributeMethod = fileInfoClass.getMethod("getAttribute", Integer.TYPE);
        fileInfoSetAttributeMethod = fileInfoClass.getMethod("setAttribute", Integer.TYPE, Boolean.TYPE);
        fileInfoGetLastModifiedMethod = fileInfoClass.getMethod("getLastModified");
        fileInfoSetLastModifiedMethod = fileInfoClass.getMethod("setLastModified", Long.TYPE);
      }
      catch (Throwable exeption)
      {
        // Ignore any exceptions and assume the class isn't available.
      }
    }
    EFS_GET_STORE_METHOD = efsGetStoreMethod;
    EFS_GET_FILE_SYSTEM_METHOD = efsGetFileSystemMethod;
    FILE_STORE_OPEN_INPUT_STREAM_METHOD = fileStoreOpenInputStreamMethod;
    FILE_STORE_OPEN_OUTPUT_STREAM_METHOD = fileStoreOpenOutputStreamMethod;
    FILE_STORE_DELETE_METHOD = fileStoreDeleteMethod;
    FILE_STORE_FETCH_INFO_METHOD = fileStoreFetchInfoMethod;
    FILE_STORE_PUT_INFO_METHOD = fileStorePutInfoMethod;
    FILE_INFO_EXISTS_METHOD = fileInfoExistsMethod;
    FILE_INFO_IS_DIRECOTRY_METHOD = fileInfoIsDirectoryMethod;
    FILE_INFO_GET_ATTRIBUTE_METHOD = fileInfoGetAttributeMethod;
    FILE_INFO_SET_ATTRIBUTE_METHOD = fileInfoSetAttributeMethod;
    FILE_INFO_GET_LENGTH_METHOD = fileInfoGetLengthMethod;
    FILE_INFO_GET_LAST_MODIFIED = fileInfoGetLastModifiedMethod;
    FILE_INFO_SET_LAST_MODIFIED = fileInfoSetLastModifiedMethod;
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

  protected Object getStore(URI uri, Map<?, ?> options)
    throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException
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

  protected Object getInfo(URI uri, Object store, Map<?, ?> options)
    throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException
  {
    return FILE_STORE_FETCH_INFO_METHOD.invoke(store);
  }

  protected Object getInfo(URI uri, Map<?, ?> options)
    throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException
  {
    return getInfo(uri, getStore(uri, options), options);
  }

  protected void setInfo(URI uri, Object store, Object info, int set, Map<?, ?> options)
    throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, URISyntaxException
  {
    FILE_STORE_PUT_INFO_METHOD.invoke(store, info, set, null);
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
                response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, getAttributes(uri, options).get(URIConverter.ATTRIBUTE_TIME_STAMP));
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
        response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, getAttributes(uri, options).get(URIConverter.ATTRIBUTE_TIME_STAMP));
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
  private static final Integer ATTRIBUTE_EXECUTABLE = 1 << 2;
  private static final Integer ATTRIBUTE_ARCHIVE = 1 << 3;
  private static final Integer ATTRIBUTE_HIDDEN = 1 << 4;

  private static final int SET_ATTRIBUTES = 1 << 10;
  private static final int SET_LAST_MODIFIED = 1 << 11;

  @Override
  public Map<String, ?> getAttributes(URI uri, Map<?, ?> options)
  {
    Map<String, Object> result = new HashMap<String, Object>();
    Set<String> requestedAttributes = getRequestedAttributes(options);
    Object info;
    try
    {
      info = getInfo(uri, options);
    }
    catch (Exception exception)
    {
      return result;
    }
    if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_TIME_STAMP))
    {
      try
      {
        Object timeStamp = FILE_INFO_GET_LAST_MODIFIED.invoke(info);
        result.put(URIConverter.ATTRIBUTE_TIME_STAMP, timeStamp);
      }
      catch (Exception exception)
      {
        // Ignore.
      }
    }
    if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_READ_ONLY))
    {
      try
      {
        Object isReadOnly = FILE_INFO_GET_ATTRIBUTE_METHOD.invoke(info, ATTRIBUTE_READ_ONLY);
        result.put(URIConverter.ATTRIBUTE_READ_ONLY, isReadOnly);
      }
      catch (Exception exception)
      {
        // Ignore.
      }
    }
    if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_ARCHIVE))
    {
      try
      {
        Object isArchive = FILE_INFO_GET_ATTRIBUTE_METHOD.invoke(info, ATTRIBUTE_ARCHIVE);
        result.put(URIConverter.ATTRIBUTE_ARCHIVE, isArchive);
      }
      catch (Exception exception)
      {
        // Ignore.
      }
    }
    if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_EXECUTABLE))
    {
      try
      {
        Object isExecutable = FILE_INFO_GET_ATTRIBUTE_METHOD.invoke(info, ATTRIBUTE_EXECUTABLE);
        result.put(URIConverter.ATTRIBUTE_EXECUTABLE, isExecutable);
      }
      catch (Exception exception)
      {
        // Ignore.
      }
    }
    if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_HIDDEN))
    {
      try
      {
        Object isHidden = FILE_INFO_GET_ATTRIBUTE_METHOD.invoke(info, ATTRIBUTE_HIDDEN);
        result.put(URIConverter.ATTRIBUTE_HIDDEN, isHidden);
      }
      catch (Exception exception)
      {
        // Ignore.
      }
    }
    if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_DIRECTORY))
    {
      try
      {
        Object isDirectory = FILE_INFO_IS_DIRECOTRY_METHOD.invoke(info);
        result.put(URIConverter.ATTRIBUTE_DIRECTORY, isDirectory);
      }
      catch (Exception exception)
      {
        // Ignore.
      }
    }
    if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_LENGTH))
    {
      try
      {
        Object length = FILE_INFO_GET_LENGTH_METHOD.invoke(info);
        result.put(URIConverter.ATTRIBUTE_LENGTH, length);
      }
      catch (Exception exception)
      {
        // Ignore.
      }
    }
    return result;
  }

  @Override
  public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException
  {
    int set = 0;
    Object store;
    Object info;
    try
    {
      store = getStore(uri, options);
      info = getInfo(uri, store, options);
    }
    catch (Exception exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
    Object timeStamp = attributes.get(URIConverter.ATTRIBUTE_TIME_STAMP);
    if (timeStamp != null)
    {
      try
      {
        FILE_INFO_SET_LAST_MODIFIED.invoke(info, timeStamp);
        set = SET_LAST_MODIFIED;
      }
      catch (Exception exception)
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
    Object isReadOnly = attributes.get(URIConverter.ATTRIBUTE_READ_ONLY);
    if (isReadOnly != null)
    {
      try
      {
        FILE_INFO_SET_ATTRIBUTE_METHOD.invoke(info, ATTRIBUTE_READ_ONLY, isReadOnly);
        set |= SET_ATTRIBUTES;
      }
      catch (Exception exception)
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
    Object isArchive = attributes.get(URIConverter.ATTRIBUTE_ARCHIVE);
    if (isArchive != null)
    {
      try
      {
        FILE_INFO_SET_ATTRIBUTE_METHOD.invoke(info, ATTRIBUTE_ARCHIVE, isArchive);
        set |= SET_ATTRIBUTES;
      }
      catch (Exception exception)
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
    Object isExecutable = attributes.get(URIConverter.ATTRIBUTE_ARCHIVE);
    if (isExecutable != null)
    {
      try
      {
        FILE_INFO_SET_ATTRIBUTE_METHOD.invoke(info, ATTRIBUTE_EXECUTABLE, isExecutable);
        set |= SET_ATTRIBUTES;
      }
      catch (Exception exception)
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
    Object isHidden = attributes.get(URIConverter.ATTRIBUTE_HIDDEN);
    if (isHidden != null)
    {
      try
      {
        FILE_INFO_SET_ATTRIBUTE_METHOD.invoke(info, ATTRIBUTE_HIDDEN, isHidden);
        set |= SET_ATTRIBUTES;
      }
      catch (Exception exception)
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
    if (set != 0)
    {
      try
      {
        setInfo(uri, store, info, set, options);
      }
      catch (Exception exception)
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
  }
}
