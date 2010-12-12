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
 * $Id: ByteArrayInputStream.java,v 1.2 2010/12/12 20:29:31 emerks Exp $
 */
package org.eclipse.emf.common.util;


import java.io.IOException;

public class ByteArrayInputStream extends InputStream
{
  protected byte[] bytes;
  protected int index;
  
  public ByteArrayInputStream(byte [] bytes)
  {
    this.bytes = bytes;
  }

  @Override
  public int read() throws IOException
  {
    if (index >= bytes.length)
    {
      return -1;
    }
    else
    {
      return bytes[index++] & 0xFF;
    }
  }
}
