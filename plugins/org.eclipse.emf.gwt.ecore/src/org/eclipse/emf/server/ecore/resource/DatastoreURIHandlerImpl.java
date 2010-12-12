/**
 * <copyright> 
 *
 * Copyright (c) 2010 Ed Merks and others.
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
 * $Id: DatastoreURIHandlerImpl.java,v 1.1 2010/12/12 20:29:37 emerks Exp $
 */
package org.eclipse.emf.server.ecore.resource;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.ByteArrayInputStream;
import org.eclipse.emf.common.util.ByteArrayOutputStream;
import org.eclipse.emf.common.util.InputStream;
import org.eclipse.emf.common.util.OutputStream;
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
    Map<Object, Object> result = getResponse(options);
    byte[] bytes = (byte[])result.get(URIConverter.RESPONSE_RESULT);
    if (bytes == null)
    {
      throw new IOException("URI not found " + uri);
    }
    return new ByteArrayInputStream(bytes);
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
