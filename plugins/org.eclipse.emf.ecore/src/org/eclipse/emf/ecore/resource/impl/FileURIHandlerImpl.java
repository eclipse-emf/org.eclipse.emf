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
 * $Id: FileURIHandlerImpl.java,v 1.1 2007/09/29 16:41:42 emerks Exp $
 */
package org.eclipse.emf.ecore.resource.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;


public class FileURIHandlerImpl extends URIHandlerImpl
{
  /**
   * Creates an instance.
   */
  public FileURIHandlerImpl()
  {
    super();
  }
  
  @Override
  public boolean canHandle(URI uri)
  {
    return uri.isFile();
  }

  /**
   * Creates an output stream for the file path and returns it.
   * <p>
   * This implementation allocates a {@link FileOutputStream} and creates subdirectories as necessary.
   * </p>
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   */
  @Override
  public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException
  {
    String filePath = uri.toFileString();
    final File file = new File(filePath);
    String parent = file.getParent();
    if (parent != null)
    {
      new File(parent).mkdirs();
    }
    final Map<Object, Object> response = getResponse(options);
    OutputStream outputStream = 
      new FileOutputStream(file)
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
              response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, file.lastModified());
            }
          }
        }
      };
    return outputStream;
  }

  /**
   * Creates an input stream for the file path and returns it.
   * <p>
   * This implementation allocates a {@link FileInputStream}.
   * </p>
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   */
  @Override
  public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException
  {
    String filePath = uri.toFileString();
    File file = new File(filePath);
    InputStream inputStream = new FileInputStream(file);
    Map<Object, Object> response = getResponse(options);
    if (response != null)
    {
      response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, file.lastModified());
    }
    return inputStream;
  }
  
  @Override
  public void delete(URI uri, Map<?, ?> options) throws IOException
  {
    String filePath = uri.toFileString();
    File file = new File(filePath);
    file.delete();
  }
  
  @Override
  public boolean exists(URI uri, Map<?, ?> options)
  {
    String filePath = uri.toFileString();
    File file = new File(filePath);
    return file.exists();
  }

  @Override
  public boolean isReadOnly(URI uri, Map<?, ?> options)
  {
    String filePath = uri.toFileString();
    File file = new File(filePath);
    return !file.canWrite();
  }

  @Override
  public long timeStamp(URI uri, Map<?, ?> options)
  {
    String filePath = uri.toFileString();
    File file = new File(filePath);
    return file.lastModified();
  }
}
