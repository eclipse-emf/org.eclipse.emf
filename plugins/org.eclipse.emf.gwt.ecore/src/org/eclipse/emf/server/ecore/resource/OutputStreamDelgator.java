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
 * $Id: OutputStreamDelgator.java,v 1.1 2010/12/12 20:29:37 emerks Exp $
 */
package org.eclipse.emf.server.ecore.resource;

import java.io.IOException;

import org.eclipse.emf.common.util.OutputStream;

public class OutputStreamDelgator extends OutputStream
{
  protected java.io.OutputStream outputStream;
  
  public OutputStreamDelgator(java.io.OutputStream outputStream)
  {
    this.outputStream = outputStream;
  }

  @Override
  public void write(int value) throws IOException
  {
    outputStream.write(value);
  }

  @Override
  public void write(byte[] value) throws IOException
  {
    outputStream.write(value);
  }

  @Override
  public void write(byte[] value, int offset, int length) throws IOException
  {
    outputStream.write(value, offset, length);
  }

  @Override
  public void flush() throws IOException
  {
    outputStream.flush();
  }

  @Override
  public void close() throws IOException
  {
    outputStream.close();
  }
}
