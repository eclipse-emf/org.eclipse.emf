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
 * $Id: AESCipherImpl.java,v 1.2 2007/06/14 18:32:46 emerks Exp $
 */
package org.eclipse.emf.ecore.resource.impl;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.eclipse.emf.ecore.resource.URIConverter;

/**
 * <p>EMF implementation for the {@link URIConverter.Cipher} interface using 
 * the AES encryption algorithm.</p>  
 * <p>This shows how this class can be used:</p>
 * <pre>
 * Map options = new HashMap();
 * options.put(Resource.OPTION_CIPHER, 
 *             new AESCipherImpl("12345")); // "That's amazing! I've got the same combination on my luggage!"
 * resource.save(options);
 * resource.load(options);
 * </pre> 
 */
public class AESCipherImpl implements URIConverter.Cipher
{
  private static final String ENCRYPTION_ALGORITHM = "AES/CFB8/PKCS5Padding";
  private static final int ENCRYPTION_IV_LENGTH = 16;
  private static final String ENCRYPTION_KEY_ALGORITHM = "AES";
  private static final String PBE_ALGORITHM = "PBEWithMD5AndDES";
  private static final int PBE_IV_LENGTH = 8;
  private static final int PBE_ITERATIONS = 1000;

  private static KeyGenerator keygen;
  private static SecureRandom random;

  private static Key generateKey(int keysize)
  {
    if (keygen == null)
    {
      try
      {
        keygen = KeyGenerator.getInstance(ENCRYPTION_KEY_ALGORITHM);
        keygen.init(keysize);
      }
      catch (Exception ex)
      {
        // all implementations of Java 1.5 should support AES
        throw new RuntimeException(ex);
      }
    }
    return keygen.generateKey();
  }

  private static byte[] randomBytes(int length)
  {
    if (random == null)
    {
      random = new SecureRandom();
    }

    byte[] bytes = new byte [length];
    random.nextBytes(bytes);

    return bytes;
  }

  private static byte[] readBytes(int length, InputStream in) throws Exception
  {
    byte[] bytes = new byte [length];
    int read = in.read(bytes);

    if (read != length)
    {
      throw new Exception("expected length != actual length");
    }

    return bytes;
  }

  private static byte[] transformWithPassword(byte[] bytes, byte[] iv, String password, int mode) throws Exception
  {
    // generate the key
    PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBE_ALGORITHM);
    SecretKey pbeKey = keyFactory.generateSecret(pbeKeySpec);
    PBEParameterSpec pbeParamSpec = new PBEParameterSpec(iv, PBE_ITERATIONS);

    // encrypt the input
    Cipher keyCipher = Cipher.getInstance(PBE_ALGORITHM);
    keyCipher.init(mode, pbeKey, pbeParamSpec);
    return keyCipher.doFinal(bytes);
  }

  private String password;
  private Key key;
  private int keysize = 128;
  private byte[] encryptedKeyBytes;
  private byte[] pbeIV;
  private byte[] encryptionIV;

  public AESCipherImpl(String password) throws Exception
  {
    this.password = password;
  }
  
  /**
   * <p>Sets the keysize to be used when creating the AES key. Using anything 
   * larger than 128 may make the data file non-portable.</p>
   * <p>The keysize cannot be changed after this Cipher is used.</p>
   * @param keysize
   */
  public void setKeysize(int keysize)
  {
    if (key == null)
    {
      this.keysize = keysize;
    }
  }
  
  public int getKeysize()
  {
    return keysize;
  }  

  public OutputStream encrypt(OutputStream outputStream) throws Exception
  {
    // If we haven't yet encrypted or decrypted, generate a key. This key will 
    // only be used for encryption. Decryption keys are always derived from
    // the header of the input stream itself.
    if (key == null)
    {
      // this is the key we will use to encrypt the data 
      key = generateKey(getKeysize());

      // create the IV for the password generation algorithm
      pbeIV = randomBytes(PBE_IV_LENGTH);

      // generate the IV for encryption
      encryptionIV = randomBytes(ENCRYPTION_IV_LENGTH);

      // turn the password into an AES key
      encryptedKeyBytes = transformWithPassword(key.getEncoded(), pbeIV, password, Cipher.ENCRYPT_MODE);
    }

    // write the header to the output stream. this has the format 
    // (delimeters are not written):
    // PBE IV|ENCRYPTION IV|ENCRYPTED KEY LENGTH|ENCRYPTED KEY
    outputStream.write(pbeIV);
    outputStream.write(encryptionIV);
    outputStream.write(encryptedKeyBytes.length);
    outputStream.write(encryptedKeyBytes);

    // now create the encryption cipher 
    Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(encryptionIV));
    
    // The CipherOutputStream shoudln't close the underlying stream
    //
    outputStream = new FilterOutputStream(outputStream)
    {
      @Override
      public void close() throws IOException
      {
        // Do nothing
      }
    };    
    return new CipherOutputStream(outputStream, cipher);
  }

  public void finish(OutputStream out) throws Exception
  {
    out.close();
  }

  public InputStream decrypt(InputStream in) throws Exception
  {
    // Read the header of the encrypted file.				
    byte[] pbeIV = readBytes(PBE_IV_LENGTH, in);
    byte[] encryptionIV = readBytes(ENCRYPTION_IV_LENGTH, in);
    int keyLength = in.read();
    byte[] encryptedKeyBytes = readBytes(keyLength, in);

    // Decrypt the key bytes
    byte[] decryptedKeyBytes = transformWithPassword(encryptedKeyBytes, pbeIV, password, Cipher.DECRYPT_MODE);

    // Create the key from the key bytes
    Key key = new SecretKeySpec(decryptedKeyBytes, ENCRYPTION_KEY_ALGORITHM);

    // If we haven't yet generated a key, just use this one
    if (this.key == null)
    {
      this.pbeIV = pbeIV;
      this.encryptionIV = encryptionIV;
      this.encryptedKeyBytes = encryptedKeyBytes;
      this.key = key;
    }

    // now create the decrypt cipher
    Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(encryptionIV));
    return new CipherInputStream(in, cipher);
  }

  public void finish(InputStream in) throws Exception
  {
    // Do nothing
  }  
}