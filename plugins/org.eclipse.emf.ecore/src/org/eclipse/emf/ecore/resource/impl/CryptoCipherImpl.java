package org.eclipse.emf.ecore.resource.impl;

import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.eclipse.emf.ecore.resource.URIConverter;

/**
 * <p>EMF default implementation for the {@link URIConverter.Cipher} interface.  
 * This is an example of how this class can be used:<p>
 * <pre>Map options = new HashMap();
    options.put(Resource.OPTION_CIPHER, 
                new CryptoCipherImpl("a very long key indeed"));
    resource.save(options);
    resource.load(options);</pre>
 * 
 */
public class CryptoCipherImpl implements URIConverter.Cipher
{
  public static class LocalCipherOutputStream extends CipherOutputStream
  {
    protected Cipher cipher;
    
    public LocalCipherOutputStream(OutputStream os, Cipher cipher)
    {
      super(os, cipher);
      this.cipher = cipher;
    }
    
    public void finish() throws Exception
    {
      write(cipher.doFinal());
      flush();
    }
  }
  
  protected static final String ENCRYPTION_SCHEME = "DES";
  protected static final String UNICODE_FORMAT = "UTF-8";

  protected String key;
  
  public CryptoCipherImpl()
  {
    this(null);
  }
  
  public CryptoCipherImpl(String key)
  {
    this.key = key;
  }

  public OutputStream encrypt(OutputStream outputStream) throws Exception
  {
    Cipher cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
    cipher.init(Cipher.ENCRYPT_MODE, getKey());
    return new CipherOutputStream(outputStream, cipher);
  }
  
  public void finish(OutputStream outputStream) throws Exception
  {
    if (outputStream instanceof LocalCipherOutputStream)
    {
      ((LocalCipherOutputStream)outputStream).finish();
    }
  }

  public InputStream decrypt(InputStream inputStream) throws Exception
  {
    Cipher cipher = Cipher.getInstance(ENCRYPTION_SCHEME);
    cipher.init(Cipher.DECRYPT_MODE, getKey());
    return new CipherInputStream(inputStream, cipher);
  }
  
  public void finish(InputStream inputStream) throws Exception
  {
  }

  protected SecretKey getKey() throws Exception
  {
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPTION_SCHEME);
    DESKeySpec keySpec = new DESKeySpec(key.getBytes(UNICODE_FORMAT));
    return keyFactory.generateSecret(keySpec);
  }
}
