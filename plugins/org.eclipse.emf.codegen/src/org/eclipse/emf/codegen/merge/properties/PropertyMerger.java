/**
 * Copyright (c) 2006-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.properties;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This implements the method {@link #run}, 
 * which is called just like main during headless workbench invocation.
 */
public class PropertyMerger 
{
  protected String sourceProperties;
  protected String targetProperties;
  protected Map<String, String> sourceToTargetMap = new LinkedHashMap<String, String>();
  protected Map<String, String> targetToSourceMap = new LinkedHashMap<String, String>();

  /**
   * This creates an empty instances, when used as a runnable.
   */
  public PropertyMerger()
  {
    super();
  }

  public String getSourceProperties()
  {
    return sourceProperties;
  }

  public void setSourceProperties(String sourceProperties)
  {
    this.sourceProperties = sourceProperties;
  }

  public String getTargetProperties()
  {
    return targetProperties;
  }

  public void setTargetProperties(String targetProperties)
  {
    this.targetProperties = targetProperties;
  }

  public Map<String, String> getSourceToTargetMap()
  {
    return sourceToTargetMap;
  }

  /**
   * Create a JDOM from a URI.
   */
  public String createPropertiesForURI(String uri)
  {
    try
    {
      URL url = null;
      try
      {
        url = new URL(uri);
      }
      catch (MalformedURLException exception)
      {
        url = new URL("file:" + uri);
      }
      BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
      byte [] input = new byte [bufferedInputStream.available()];
      bufferedInputStream.read(input);
      bufferedInputStream.close();
      return new String(input, "ISO-8859-1");
    }
    catch (IOException exception)
    {
      // Ignore
    }

    return null;
  }

  public String createPropertiesForInputStream(InputStream inputStream)
  {
    try
    {
      BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
      byte [] input = new byte [bufferedInputStream.available()];
      bufferedInputStream.read(input);
      bufferedInputStream.close();
      return new String(input, "ISO-8859-1");
    }
    catch (IOException exception)
    {
      // Ignore
    }
    return null;
  }

  protected static Pattern nlPattern = Pattern.compile("([\\n][\\r]?|[\\r][\\n]?)", Pattern.MULTILINE);
  
  public void merge()
  {
    Matcher matcher = nlPattern.matcher(targetProperties);
    String nl = null;
    if (matcher.find())
    {
      nl = matcher.group(1);  
      
      matcher = nlPattern.matcher(sourceProperties);
      if (matcher.find())
      {
        String sourceNL = matcher.group(1);
        if (!sourceNL.equals(nl))
        {
          sourceProperties = sourceProperties.replaceAll(sourceNL, nl);
        }
      }
    }
    else
    {
      matcher = nlPattern.matcher(sourceProperties);
      if (matcher.find())
      {
        nl = matcher.group(1);  
      }
    }
    
    if (nl != null)
    {
      if (!targetProperties.endsWith(nl))
      {
        targetProperties += nl;
      }
      
      if (!sourceProperties.endsWith(nl))
      {
        sourceProperties += nl;
      }
    }
    
    Map<String, String> sourcePropertyFragments = parse(sourceProperties);
    Map<String, String> targetPropertyFragments = parse(targetProperties);
    
    StringBuffer result = new StringBuffer(targetProperties);
    for (Map.Entry<String, String> entry : sourcePropertyFragments.entrySet())
    {
      if (!targetPropertyFragments.containsKey(entry.getKey()))
      {
        result.append(entry.getValue());
      }
    }

    targetProperties = result.toString();
  }

  protected static Pattern propertyLine = Pattern.compile("\\s*(\\S+)\\s*=.*", Pattern.MULTILINE);

  public Map<String, String> parse(String properties)
  {
    Map<String, String> result = new LinkedHashMap<String, String>();
    int i = 0;
    while (i < properties.length())
    {
      int eol;
      for (int start = i;;)
      {
        eol = properties.indexOf("\n", start);
        if (eol != -1)
        {
          if (eol + 1 < properties.length() && properties.charAt(eol + 1) == '\r')
          {
            if (eol > start && properties.charAt(eol - 1) == '\\')
            {
              start = eol + 2;
            }
            else
            {
              ++eol;
              break;
            }
          }
          else if (eol > start && properties.charAt(eol - 1) == '\\' ||
                     eol - 1 > start && properties.charAt(eol - 1) == '\r' && properties.charAt(eol - 2) == '\\')
          {
            start = eol + 1;
          }
          else
          {
            break;
          }
        }
        else
        {
          eol = properties.indexOf("\r", start);
          if (eol == -1)
          {
            eol = properties.length() - 1;
            break;
          }
          else if (eol > start && properties.charAt(eol - 1) == '\\')
          {
            start = eol + 1;
          }
          else
          {
            break;
          }
        }
      }

      String property = properties.substring(i, eol + 1);
      Matcher matcher = propertyLine.matcher(property);
      if (matcher.find() && matcher.groupCount() >= 1)
      {
        int begin = matcher.start(1);
        int end = matcher.end(1);
        String propertyName =  property.substring(begin, end);
        if (propertyName.indexOf("#") == -1)
        {
          result.put(propertyName, property);
        }
        else if (propertyName.startsWith("#"))
        {
          result.put(propertyName.substring(1), property);
        }
      }

      i = eol + 1;
    }

    return result;
  }


  /**
   * This is called with the command line arguments of a headless workbench invocation.
   */
  public Object run(Object object) 
  {
    try
    {
      // Three arguments are expected: the .xml jControlModel URI, the source java URI, and the target java URI.
      //
      String[] arguments = (String[])object;

      // Create the source and target JDOMs.
      //
      sourceProperties = createPropertiesForURI(arguments[0]);
      targetProperties = createPropertiesForURI(arguments[1]);

      merge();

      System.out.println("**********************************************");
      System.out.println(targetProperties);

      return 0;
    }
    catch (Exception exception)
    {
      return 1;
    }
  }
  
/////////////////////////////////  HEADLESS INVOCATION  /////////////////////////////////////

  @Deprecated
  public static class PlatformRunnable extends PropertyMerger implements org.eclipse.core.runtime.IPlatformRunnable 
  {
    // Empty
  }
}
