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
 * $Id: ByteArrayOutputStream.java,v 1.1 2011/05/12 15:08:17 khussey Exp $
 */
package java.io;

import java.io.IOException;

public class ByteArrayOutputStream extends OutputStream
{
  protected byte [] bytes;
  protected int index;

  public ByteArrayOutputStream()
  {
    this(1024);
  }

  public ByteArrayOutputStream(int capacity)
  {
    bytes = new byte[capacity];
  }

  @Override
  public void write(int byteValue) throws IOException
  {
    if (index >= bytes.length)
    {
      byte[] newBytes = new byte[bytes.length * 2];
      System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
      bytes = newBytes;
    }
    bytes[index++] = (byte)byteValue;
  }

  public byte[] toByteArray()
  {
    byte[] result = new byte[index];
    System.arraycopy(bytes, 0, result, 0, index);
    return result;
  }
}
