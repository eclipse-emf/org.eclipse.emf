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
 * $Id: OutputStream.java,v 1.1 2011/05/12 15:08:17 khussey Exp $
 */
package java.io;


import java.io.IOException;


public abstract class OutputStream
{
  //public abstract void write(int byteValue) throws IOException;
  public void write(int byteValue) throws IOException {
	  //can't be abstract because of the default GWT implementations that do not implement this method
	  throw new UnsupportedOperationException("This method body is just a replacement for an abstract method declaration");
  }

  public void write(byte[] bytes) throws IOException 
  {
	write(bytes, 0, bytes.length);
  }

  public void write(byte[] bytes, int start, int length) throws IOException 
  {
    for (int i = start, limit = start + length; i < limit; ++i)
    {
      write(bytes[i]);
    }
  }

  public void flush() throws IOException 
  {
    // Do nothing.
  }

  public void close() throws IOException 
  {
    // Do nothing.
  }
}