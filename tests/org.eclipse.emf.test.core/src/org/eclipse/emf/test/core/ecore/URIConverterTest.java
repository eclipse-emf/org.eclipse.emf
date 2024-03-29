/**
 * Copyright (c) 2006-2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;
import org.junit.Before;
import org.junit.Test;

public class URIConverterTest
{
  protected URIConverter uriConverter;

  @Before
  public void setUp() throws Exception
  {
    // On real applications the URIConverter is usually obtained from
    // a ResourceSet
    //
    uriConverter = new ExtensibleURIConverterImpl();
  }

  @Test
  public void testReadArchiveURI() throws Exception
  {
    String pluginDirectory = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID);
    if (pluginDirectory.charAt(0) != '/')
    {
      pluginDirectory = "/" + pluginDirectory;
    }

    URI uri = URI.createFileURI(pluginDirectory + "/data/file.txt");
    String contentsFromUncompressedFile = readFile(uriConverter.createInputStream(uri));

    uri = URI.createURI("jar:file:" + pluginDirectory + "/%64ata/data.jar!/%66ile.txt");
    String contents = readFile(uriConverter.createInputStream(uri));
    assertEquals(contentsFromUncompressedFile, contents);

    uri = URI.createURI("archive:file:" + pluginDirectory + "/%64ata/data.jar!/%66ile.txt");
    contents = readFile(uriConverter.createInputStream(uri));
    assertEquals(contentsFromUncompressedFile, contents);

    uri = URI.createURI("archive:file:" + pluginDirectory + "/%661/data.jar!/%66ile.txt");
    new File(URI.createURI(uri.authority().replace("!", "")).toFileString()).delete();
    writeFile(uriConverter.createOutputStream(uri), contents);
    contents = readFile(uriConverter.createInputStream(uri));
    assertEquals(contentsFromUncompressedFile, contents);
    new File(URI.createURI(uri.authority().replace("!", "")).toFileString()).delete();

    uri = URI.createURI("archive:file:" + pluginDirectory + "/%64ata/data.zip!/%66ile.txt");
    contents = readFile(uriConverter.createInputStream(uri));
    assertEquals(contentsFromUncompressedFile, contents);

    uri = URI.createURI("archive:file:" + pluginDirectory + "/%661/data.zip!/%66ile.txt");
    new File(URI.createURI(uri.authority().replace("!", "")).toFileString()).delete();
    writeFile(uriConverter.createOutputStream(uri), contents);
    contents = readFile(uriConverter.createInputStream(uri));
    assertEquals(contentsFromUncompressedFile, contents);
    new File(URI.createURI(uri.authority().replace("!", "")).toFileString()).delete();

    // Reads the data.zip file from our git repository using https
    //
    uri = URI.createURI("archive:https://github.com/eclipse-emf/org.eclipse.emf/raw/master/tests/org.eclipse.emf.test.core/data/data.zip!/%66ile.txt");
    try
    {
      InputStream inputStream = uriConverter.createInputStream(uri);
      contents = readFile(inputStream);
      assertEquals(contentsFromUncompressedFile, contents);
    }
    catch (IOException exception)
    {
      // Ignore because github sometimes blocks access.
    }
  }

  protected String readFile(InputStream inputStream) throws IOException
  {
    try
    {
      if (!(inputStream instanceof BufferedInputStream))
      {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        inputStream = bufferedInputStream;
      }

      StringBuilder sb = new StringBuilder();
      byte[] buffer = new byte[1024];
      int size = 0;
      while ((size = inputStream.read(buffer)) > -1)
      {
        sb.append(new String(buffer, 0, size));
      }
      return sb.toString();
    }
    finally
    {
      inputStream.close();
    }
  }

  protected void writeFile(OutputStream outputStream, String contents) throws IOException
  {
    try
    {
      outputStream.write(contents.getBytes());
    }
    finally
    {
      outputStream.close();
    }
  }
}
