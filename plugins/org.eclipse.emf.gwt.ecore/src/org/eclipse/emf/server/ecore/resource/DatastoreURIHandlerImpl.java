/**
 * <copyright> 
 *
 * Copyright (c) 2010-2011 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Ed Merks - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: DatastoreURIHandlerImpl.java,v 1.3 2011/05/12 15:08:22 khussey Exp $
 */
package org.eclipse.emf.server.ecore.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;

public class DatastoreURIHandlerImpl extends URIHandlerImpl
{
  @Override
  public boolean canHandle(URI uri)
  {
    return "datastore".equals(uri.scheme());
  }

  @Override
  public OutputStream createOutputStream(final URI uri, final Map<?, ?> options) throws IOException
  {
    return
      new ByteArrayOutputStream()
      {
        @Override
        public void close() throws IOException
        {
          flush();
          super.close();
        }
        
        @Override
        public void flush() throws IOException
        {
          byte[] bytes = toByteArray();
          DatastoreUtil.store(uri.toString(), bytes, options);
          Map<Object, Object> response = getResponse(options);
          Long timestamp = (Long)response.get(URIConverter.RESPONSE_TIME_STAMP_PROPERTY);
          if (timestamp == null)
          {
            throw new IOException("Failed to save " + uri);
          }
          else
          {
            // TODO
            // If there is another flush, we'd need to use this timestamp for the options the next time...
          }
        }
      };
  }

  @Override
  public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException
  {
    String uriString = uri.toString();
    DatastoreUtil.fetch(uriString, options);
    Map<Object, Object> response = getResponse(options);
    Object result = response.get(URIConverter.RESPONSE_RESULT);
    if (result == null)
    {
      throw new IOException("URI not found " + uri);
    }
    else if (result instanceof byte[])
    {
      byte[] bytes = (byte[])result;
      return new ByteArrayInputStream(bytes);
    }
    else
    {
      throw (IOException)result;
    }
  }

  @Override
  public void delete(URI uri, Map<?, ?> options) throws IOException
  {
    DatastoreUtil.delete(uri.toString(), options);
  }

  @Override
  public boolean exists(URI uri, Map<?, ?> options)
  {
    return DatastoreUtil.exists(uri.toString(), options);
  }

  @Override
  public Map<String, ?> getAttributes(URI uri, Map<?, ?> options)
  {
    // TODO Auto-generated method stub
    return super.getAttributes(uri, options);
  }

  @Override
  public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException
  {
    // TODO Auto-generated method stub
    super.setAttributes(uri, attributes, options);
  }
}
