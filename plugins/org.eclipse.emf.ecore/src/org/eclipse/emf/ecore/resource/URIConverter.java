/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: URIConverter.java,v 1.9 2007/06/15 21:57:52 emerks Exp $
 */
package org.eclipse.emf.ecore.resource;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;


/**
 * A converter to normalize a URI or to produce an input or output stream for a URI.
 * <p>
 * A resource set provides {@link ResourceSet#getURIConverter one} of these
 * for use by it's {@link ResourceSet#getResources resources}
 * when they are {@link Resource#save(java.util.Map) serialized} and {@link Resource#load(java.util.Map) deserialized}.
 * A resource set also uses this directly when it {@link ResourceSet#getResource looks up} a resource:
 * a resource is considered a match if {@link Resource#getURI it's URI}, 
 * and the URI being looked up, 
 * {@link #normalize normalize} to {@link URI#equals(Object) equal} URIs.
 * Clients must extend the default {@link org.eclipse.emf.ecore.resource.impl.URIConverterImpl implementation},
 * since methods can and will be added to this API.
 * </p>
 */
public interface URIConverter
{
  /**
   * Returns the normalized form of the URI.
   * <p>
   * This may, in theory, do absolutely anything.
   * Default behaviour includes 
   * applying URI {@link URIConverter#getURIMap mapping},
   * assuming <code>"file:"</code> protocol 
   * for a {@link URI#isRelative relative} URI with a {@link URI#hasRelativePath relative path}:
   *<pre>
   *  ./WhateverDirectory/Whatever.file 
   *    -> 
   *  file:./WhateverDirectory/Whatever.file
   *</pre>
   * and assuming <code>"platform:/resource"</code> protocol 
   * for a relative URI with an {@link URI#hasAbsolutePath absolute path}:
   *<pre>
   *  /WhateverRelocatableProject/Whatever.file 
   *    -> 
   *  platform:/resource/WhateverRelocatableProject/Whatever.file
   *</pre>
   * </p>
   * <p>
   * It is important to emphasize that normalization can result it loss of information.
   * The normalized URI should generally be used only for comparison and for access to input or output streams.
   * </p>
   * @param uri the URI to normalize.
   * @return the normalized form.
   * @see org.eclipse.emf.ecore.plugin.EcorePlugin#getPlatformResourceMap
   */
  URI normalize(URI uri);

  /**
   * Returns the map used for remapping a logical URI to a physical URI when {@link #normalize normalizing}.
   * <p>
   * An implementation will typically also delegate to the {@link URIConverter#URI_MAP global} map,
   * so registrations made in this map are <em>local</em> to this URI converter,
   * i.e., they augment or override those of the global map.
   * </p>
   * <p>
   * The map generally specifies instance to instance mapping,
   * except for the case that both the key URI and the value URI end with "/", 
   * which specifies a folder to folder mapping.
   * A folder mapping will remap any URI that has the key as its {@link URI#replacePrefix prefix}, 
   * e.g., if the map contains:
   *<pre>
   *  http://www.example.com/ -> platform:/resource/example/
   *</pre>
   * then the URI
   *<pre>
   *  http://www.example.com/a/b/c.d
   *</pre>
   * will map to 
   *<pre>
   *  platform:/resource/example/a/b/c.d
   *</pre>
   * A matching instance mapping is considered first.
   * If there isn't one, the folder mappings are considered starting with the {@link URI#segmentCount() longest} prefix. 
   * </p>
   * @see #normalize(URI)
   * @see #URI_MAP
   * @return the map used for remapping a logical URI to a physical URI.
   */
  Map<URI, URI> getURIMap();

  /**
   * The global static URI map.
   * Registrations made in this instance will (typically) be available
   * for {@link URIConverter#normalize use} by any URI converter.
   * It is populated by URI mappings registered via
   * {@link org.eclipse.emf.ecore.plugin.EcorePlugin.Implementation#startup() plugin registration}.
   * @see #normalize(URI)
   */
  Map<URI, URI> URI_MAP = org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl.INSTANCE.map();

  /**
   * Creates an input stream for the URI and returns it.
   * <p>
   * It {@link #normalize normalizes} the URI and uses that as the basis for further processing.
   * Special requirements, such as an Eclipse file refresh, 
   * are handled by the {@link org.eclipse.emf.ecore.resource.impl.URIConverterImpl default implementation}.
   * </p>
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   */
  InputStream createInputStream(URI uri) throws IOException;

  /**
   * An interface that is optionally implemented by the input streams returned from 
   * {@link URIConverter#createInputStream(URI)}.
   * @see ReadableInputStream
   */
  interface Readable
  {
    /**
     * Returns a reader that provides access to the same underlying data as the input stream itself.
     * @return a reader that provides access to the same underlying data as the input stream itself.
     */
    Reader asReader();
    
    /**
     * Returns the encoding used to convert the reader's characters to bytes.
     * @return the encoding used to convert the reader's characters to bytes.
     */
    String getEncoding();
  }

  /**
   * A wrapper around a reader that implements an input stream but can be unwrapped to access the reader directly.
   */
  public class ReadableInputStream extends InputStream implements Readable
  {
    private static final Pattern XML_HEADER = Pattern.compile("<\\?xml\\s+(?:version\\s*=\\s*\"[^\"]*\"\\s+)encoding\\s*=\\s*\"\\s*([^\\s\"]*)\"\\s*\\?>");
    
    public static String getEncoding(String xmlString)
    {
      Matcher matcher = XML_HEADER.matcher(xmlString);
      return
        matcher.lookingAt() ?
          matcher.group(1) :
          null;
    }
    
    protected String encoding;
    protected Reader reader;
    protected Buffer buffer;
    
    public ReadableInputStream(Reader reader, String encoding)
    {
      super();
      this.reader = reader;
      this.encoding = encoding;
    }
    
    public ReadableInputStream(String string, String encoding)
    {
      this(new StringReader(string), encoding);
    }
    
    public ReadableInputStream(String xmlString)
    {
      this(new StringReader(xmlString), getEncoding(xmlString));
    }
    
    @Override
    public int read() throws IOException
    {
      if (buffer == null)
      {
        buffer = new Buffer(100);
      }
      
      return buffer.read();
    }

    public Reader asReader()
    {
      return reader;
    }
    
    public String getEncoding()
    {
      return encoding; 
    }

    @Override
    public void close() throws IOException
    {
      super.close();
      reader.close();
    }

    @Override
    public void reset() throws IOException
    {
      super.reset();
      reader.reset();
    }
    
    protected class Buffer extends ByteArrayOutputStream
    {
      protected int index;
      protected char [] characters;
      protected OutputStreamWriter writer;
      
      public Buffer(int size) throws IOException
      {
        super(size);
        characters = new char [size];
        writer = new OutputStreamWriter(this, encoding);
      }
      
      public int read() throws IOException
      {
        if (index < count)
        {
          return buf[index++];
        }
        else
        {
          index = 0;
          reset();
          
          int readCount = reader.read(characters);
          if (readCount < 0)
          {
            return -1;
          }
          else
          {
            writer.write(characters, 0, readCount);
            writer.flush();
            return buf[index++];
          }
        }
      }
    }
  }
  
  /**
   * Creates an output stream for the URI and returns it.
   * <p>
   * It {@link #normalize normalizes} the URI and uses that as the basis for further processing.
   * Special requirements, such as an Eclipse file refresh and automatic subdirectory creation, 
   * are handled by the {@link org.eclipse.emf.ecore.resource.impl.URIConverterImpl default implementation}.
   * </p>
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   */
  OutputStream createOutputStream(URI uri) throws IOException;
  
  /**
   * An interface that is optionally implemented by the output streams returned from 
   * {@link URIConverter#createOutputStream(URI)}.
   * @see WriteableOutputStream
   */
  interface Writeable
  {
    /**
     * Returns a writer that provides access to the same underlying data as the input stream itself.
     * @return a writer that provides access to the same underlying data as the input stream itself.
     */
    Writer asWriter();
    
    /**
     * Returns the encoding used to convert the writer's bytes to characters.
     * @return the encoding used to convert the writer's bytes to characters.
     */
    String getEncoding();
  }

  /**
   * A wrapper around a writer that implements an output stream but can be unwrapped to access the writer directly.
   */
  public static class WriteableOutputStream extends OutputStream implements Writeable
  {
    protected String encoding;
    protected Writer writer;
    protected Buffer buffer;

    public WriteableOutputStream(Writer writer, String encoding)
    {
      super();
      this.writer = writer;
      this.encoding = encoding;
    }
    
    @Override
    public void write(int b) throws IOException
    {
      if (buffer == null)
      {
        buffer = new Buffer(100);
      }
      
      buffer.write(b);
    }

    public Writer asWriter()
    {
      return writer;
    }

    public String getEncoding()
    {
      return encoding;
    }
    
    @Override
    public void close() throws IOException
    {
      super.close();
      writer.close();
    }
    
    @Override
    public void flush() throws IOException
    {
      super.flush();
      buffer.flush();
      writer.flush();
    }

    protected class Buffer extends ByteArrayInputStream
    {
      protected int index;
      protected char [] characters;
      protected InputStreamReader reader;
      
      public Buffer(int size) throws IOException
      {
        super(new byte [size], 0, 0);
        characters = new char [size];
        reader = new InputStreamReader(this, encoding);
      }
      
      public void write(int b) throws IOException
      {
        if (count < buf.length)
        {
          buf[count++] = (byte)b;
        }
        else
        {
          int readCount = reader.read(characters);
          if (readCount > 0)
          {
            writer.write(characters, 0, readCount);
          }
          count = 0;
          index = 0;
          pos = 0;
          write(b);
        }
      }
      
      public void flush() throws IOException
      {
        int readCount = reader.read(characters);
        if (readCount > 0)
        {
          writer.write(characters, 0, readCount);
        }
        count = 0;
        index = 0;
        pos = 0;
      }
    }
  }

  /**
   * An interface to be implemented by encryption service providers.
   * @since 2.2.0
   */
  interface Cipher
  {
    /**
     * Encrypts the specified output stream.
     * @param outputStream
     * @return an encrypted output stream
     */
    OutputStream encrypt(OutputStream outputStream) throws Exception;
  
    /**
     * This method is invoked after the encrypted output stream is used
     * allowing the Cipher implementation to do any maintenance work required,
     * such as flushing an internal cache.
     * @param outputStream the encrypted stream returned by {@link #encrypt(OutputStream)}.
     */
    void finish(OutputStream outputStream) throws Exception;
  
    /**
     * Decrypts the specified input stream.
     * @param inputStream
     * @return a decrypted input stream
     */
    InputStream decrypt(InputStream inputStream) throws Exception;
    
    /**
     * This method is invoked after the decrypted input stream is used
     * allowing the Cipher implementation to do any maintenance work required,
     * such as flushing internal cache.
     * @param inputStream the stream returned by {@link #decrypt(InputStream)}.
     */
    void finish(InputStream inputStream) throws Exception;
  }
}
