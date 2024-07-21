/**
 * Copyright (c) 2006-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.resource.impl;


import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.eclipse.emf.ecore.resource.URIConverter;


/**
 * <p>EMF implementation for the {@link org.eclipse.emf.ecore.resource.URIConverter.Cipher} interface using 
 * the DES encryption algorithm.</p>  
 * <p>This shows how this class can be used:</p>
 * <pre>
 * Map options = new HashMap();
 * options.put(Resource.OPTION_CIPHER, 
 *             new DESCipherImpl("a very long key indeed"));
 * resource.save(options);
 * resource.load(options);
 * </pre> 
 * @since 2.3.0
 */
public class DESCipherImpl implements URIConverter.Cipher
{
  protected static final String ENCRYPTION_SCHEME = "DES";

  protected static final String UNICODE_FORMAT = "UTF-8";

  protected String stringKey;

  protected SecretKey key;

  public DESCipherImpl()
  {
    this(null);
  }

  public DESCipherImpl(String key)
  {
    this.stringKey = key;
  }

  public OutputStream encrypt(OutputStream outputStream) throws Exception
  {
    Cipher cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
    cipher.init(Cipher.ENCRYPT_MODE, getKey());

    // The CipherOutputStream shoudln't close the underlying stream
    //
    outputStream = new FilterOutputStream(outputStream)
      {
        @Override
        public void close() throws IOException
        {
          // Do nothing
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException
        {
          out.write(b, off, len);
        }
      };
    return new CipherOutputStream(outputStream, cipher);
  }

  public void finish(OutputStream outputStream) throws Exception
  {
    outputStream.close();
  }

  public InputStream decrypt(InputStream inputStream) throws Exception
  {
    Cipher cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
    cipher.init(Cipher.DECRYPT_MODE, getKey());
    return new CipherInputStream(inputStream, cipher);
  }

  public void finish(InputStream inputStream) throws Exception
  {
    // Do nothing.
  }

  protected SecretKey getKey() throws Exception
  {
    if (key == null)
    {
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPTION_SCHEME);
      DESKeySpec keySpec = new DESKeySpec(stringKey.getBytes(StandardCharsets.UTF_8));
      key = keyFactory.generateSecret(keySpec);
    }
    return key;
  }
}
