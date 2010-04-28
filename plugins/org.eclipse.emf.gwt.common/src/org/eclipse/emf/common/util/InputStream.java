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
 * $Id: InputStream.java,v 1.1 2010/04/28 14:49:26 emerks Exp $
 */
package org.eclipse.emf.common.util;

import java.io.IOException;

public abstract class InputStream
{
  public int available() throws IOException 
  {
    return 0;
  }

  public abstract int read() throws IOException;
  
  public int read(byte[] bytes, int start, int length) throws IOException
  {
    for (int i = 0; i < length; ++i)
    {
      int value = read();
      if (value == -1)
      {
        return i == 0 ? -1 : i;
      }
      bytes[i] = (byte)value;
    }
    return length;
  }

  public int read(byte[] bytes) throws IOException
  {
    return read(bytes, 0, bytes.length);
  }
  
  public boolean markSupported()
  {
    return false;
  }

  public void reset() throws IOException
  {
    //
  }
  
  public void mark(int mark)
  {
    //
  }

  public void close() throws IOException
  {
    // 
  }
}