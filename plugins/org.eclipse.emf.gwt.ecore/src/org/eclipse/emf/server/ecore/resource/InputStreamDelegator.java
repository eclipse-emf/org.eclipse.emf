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
 * $Id: InputStreamDelegator.java,v 1.1 2010/12/12 20:29:37 emerks Exp $
 */
package org.eclipse.emf.server.ecore.resource;

import java.io.IOException;

import org.eclipse.emf.common.util.InputStream;

public class InputStreamDelegator extends InputStream
{
  protected java.io.InputStream inputStream;

  public InputStreamDelegator(java.io.InputStream inputStream)
  {
    this.inputStream = inputStream;
  }

  @Override
  public int available() throws IOException
  {
    return inputStream.available();
  }

  @Override
  public int read() throws IOException
  {
    return inputStream.read();
  }

  @Override
  public int read(byte[] bytes, int start, int length) throws IOException
  {
    return inputStream.read(bytes, start, length);
  }

  @Override
  public int read(byte[] bytes) throws IOException
  {
    return inputStream.read(bytes);
  }

  @Override
  public boolean markSupported()
  {
    return inputStream.markSupported();
  }

  @Override
  public void reset() throws IOException
  {
    inputStream.reset();
  }

  @Override
  public void mark(int mark)
  {
    inputStream.mark(mark);
  }

  @Override
  public void close() throws IOException
  {
    inputStream.close();
  }
}
