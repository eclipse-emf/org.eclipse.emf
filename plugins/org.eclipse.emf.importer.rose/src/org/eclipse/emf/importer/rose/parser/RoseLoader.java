/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.rose.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.importer.rose.RoseImporterPlugin;


/**
 * A loader that creates Buffered Reader.
 *
 */
public class RoseLoader extends RoseComponent
{
  public static final String PROGRESS = "ROSE_LOADER_PROGRESS";

  protected boolean valid;
  protected BufferedReader bufferedReader;
  protected long length = 0;
  protected long currentLength = 0;
  protected int progressIncrement = 10;
  protected int oldValue;
  protected int lower = 0;
  protected int upper = 100;

  public RoseLoader(String fileName, URIConverter uriConverter) throws Exception
  {
    try
    {
      if (!fileName.startsWith("\\\\"))
      {
        fileName = Util.updateFileName(fileName, "\\\\");
      }
      fileName = Util.updateFileName(fileName, "\\");
      fileName = Util.updateFileName(fileName, "/");

      bufferedReader = new BufferedReader(new FileReader(fileName));
      valid = true;
      length = (new File(fileName)).length();
      currentLength = 0;
      oldValue = lower;
    }
    catch (Exception exception)
    {
      Pattern pattern = Pattern.compile(".*/([^/]*?)(?:_)?[0-9.]*/((src/)?model/[^/]*\\.cat)$");
      Matcher matcher = pattern.matcher(fileName.replaceAll("\\\\", "/"));
      if (matcher.matches())
      {
        String name = matcher.group(1);
        String tail = matcher.group(2);
        InputStream inputStream = null;
        try
        {
          inputStream = uriConverter.createInputStream(URI.createPlatformResourceURI(name + "/" + tail, true));
        }
        catch (Exception resourceException)
        {
          try
          {
            inputStream = uriConverter.createInputStream(URI.createPlatformPluginURI(name + "/" + tail, true));
          }
          catch (Exception pluginException)
          {
            // Ignore
          }
        }
        if (inputStream != null)
        {
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
          valid = true;
          length = inputStream.available();
          currentLength = 0;
          oldValue = lower;
        }
        else
        {
          RoseImporterPlugin.INSTANCE.log(exception);
        }
      }
      else
      {
        RoseImporterPlugin.INSTANCE.log(exception);
      }
    }
  }

  public boolean isValid()
  {
    return valid;
  }

  public void setProgressIncrement(int progressIncrement)
  {
    this.progressIncrement = progressIncrement;
  }

  public void setLower(int lower)
  {
    this.lower = lower;
    oldValue = lower;
  }

  public int getLower()
  {
    return lower;
  }

  public void setUpper(int upper)
  {
    this.upper = upper;
  }

  public int getUpper()
  {
    return upper;
  }

  public String readLine()
  {
    try
    {
      String line = bufferedReader.readLine();
      if (line != null)
      {
        currentLength += line.length();
        if (length > 0)
        {
          int newValue = lower + (int)(currentLength * (upper - lower) / length);
          if (newValue >= oldValue + progressIncrement && newValue < upper)
          {
            firePropertyChange(PROGRESS, oldValue, newValue);
            oldValue = newValue;
          }
        }
      }
      return line;
    }
    catch (Exception e)
    {
      return null;
    }
  }

  public void close() throws IOException
  {
    if (bufferedReader != null)
    {
      bufferedReader.close();
    }
  }
}
