/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: BuildTest.java,v 1.26 2009/03/21 02:03:58 davidms Exp $
 */
package org.eclipse.emf.test.build;

import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BuildTest extends TestCase
{
  protected final static String REGEX_SYMBOL = ",/,";
  
  protected final static String[] REQUIRED_FEATURE_FILES = 
   {"eclipse_update_120.jpg", "epl-v10.html", "feature.properties", "feature.xml", "license.html"};
  protected final static String[] REQUIRED_REGULAR_PLUGIN_FILES = 
   {"about.html", "plugin.properties", "META-INF/MANIFEST.MF"};
  protected final static String[] REQUIRED_DOC_PLUGIN_FILES = 
   {"toc.xml", "topics_Reference.xml"};  
  protected final static String[] REQUIRED_BRANDING_PLUGIN_FILES = 
   {"about.ini", "about.mappings", "about.properties", "modeling32.png"};
  
  protected static int expectedNumberOfDocPlugins = 2;
  protected static int expectedNumberOfSourcePlugins = 45;
  
  protected File featuresDir;
  protected File pluginsDir;
  
  protected Set<String> brandingPluginNames;
  
  public BuildTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("BuildTests");
    ts.addTest(new BuildTest("testFeatures"));
    ts.addTest(new BuildTest("testPlugins"));
    return ts;
  }
  
  @Override
  protected void setUp() throws Exception
  {
    String directory = TestUtil.getPluginDirectory();
    if (directory.indexOf(".metadata") < 0)
    {
      File dir = new File(directory).getAbsoluteFile().getParentFile();
      if ("plugins".equals(dir.getName()))
      {
        pluginsDir = dir;
      }
      
      dir = new File(dir.getParentFile(), "features");
      if (dir.isDirectory())
      {
        featuresDir = dir;
      }
    }
  }
  
  protected boolean isFeatureToTest(File feature)
  {
    return feature.isDirectory() && isFileToTest(feature);
  }
  
  protected boolean isPluginToTest(File plugin)
  {
    return isFileToTest(plugin) && !plugin.getName().endsWith("-feature");
  }
  
  protected boolean isFileToTest(File plugin)
  {
    String name = plugin.getName();
    return 
      (name.startsWith("org.eclipse.emf") || name.startsWith("org.eclipse.xsd"))
      && !name.equals("org.eclipse.emf.releng.build")
      && !name.equals("org.eclipse.emf.examples.jet.article2");
  }
 
  public void testFeatures() throws Exception
  {
    assertNotNull("Features Dir '" + featuresDir + "' is null", featuresDir);
    assertTrue("Features Dir '" + featuresDir + "' is not a directory", featuresDir.isDirectory());
    
    StringBuffer result = new StringBuffer();
    File[] features = featuresDir.listFiles();
    for (int i = 0; i < features.length; i++)
    {
      File feature = features[i];
      if (isFeatureToTest(feature))
      {
        String name = feature.getName().replaceAll("(_\\d\\.\\d\\.\\d)?(\\.jar)?$", "");
        if (name.equals("org.eclipse.emf.sdk"))
        {
          if (brandingPluginNames == null)
          {
            brandingPluginNames = new HashSet<String>();
          }
          brandingPluginNames.add(name);
        }
        
        String missingFiles = getMissingFiles(feature, REQUIRED_FEATURE_FILES);
        if (missingFiles.length() > 0)
        {
          result.append(",").append(missingFiles);
        }
      }
    }
    
    if (result.length() > 0)
    {
      result.deleteCharAt(0);
    }  
    assertTrue("'" + result.toString() + "' is not of length 0", result.length() == 0);
  }

  public void testPlugins() throws Exception
  {
    assertNotNull("Plugin Dir '" + pluginsDir + "' is null", pluginsDir);
    assertTrue("Plugin Dir '" + pluginsDir + "' is not a directory", pluginsDir.isDirectory());
    
    StringBuffer result = new StringBuffer();
    int docPluginsCounter = 0;
    int brandingPluginsCounter = 0;
    int sourcePluginsCounter = 0;
    
    File[] plugins = pluginsDir.listFiles();
    for (int i = 0; i < plugins.length; i++)
    {
      File plugin = plugins[i];
      if (isPluginToTest(plugin))
      {
        JarFile jarFile = plugin.isFile() ? new JarFile(plugin) : null;
        String name = plugin.getName().replaceAll("(_\\d\\.\\d\\.\\d(\\..*)?)?(\\.jar)?$", "");
        
        if (isDocPlugin(name))
        {
          docPluginsCounter++;
          String missingFiles = jarFile == null ?
            getMissingFiles(plugin, REQUIRED_DOC_PLUGIN_FILES) : 
            getMissingFiles(jarFile, REQUIRED_DOC_PLUGIN_FILES); 
          if (missingFiles.length() > 0)
          {
            result.append(",").append(missingFiles);
          }
        }
        
        if (isBrandingPlugin(name))
        {
          brandingPluginsCounter++;
          String missingFiles = jarFile == null ? 
            getMissingFiles(plugin, REQUIRED_BRANDING_PLUGIN_FILES) : 
            getMissingFiles(jarFile, REQUIRED_BRANDING_PLUGIN_FILES);
          if (missingFiles.length() > 0)
          {
            result.append(",").append(missingFiles);
          }
        }
        
        if (isSourcePlugin(name))
        {
          sourcePluginsCounter++;
          File srcDir = new File(plugin, "src");

          // Old multi-plugin source plugins had a src directory.
          //
          assertFalse("Source plugin '" + plugin + "' is not of new single-plugin style", srcDir.exists());
        }
        
        {
          String missingFiles = jarFile == null ? 
            getMissingFiles(plugin, REQUIRED_REGULAR_PLUGIN_FILES) : 
            getMissingFiles(jarFile, REQUIRED_REGULAR_PLUGIN_FILES);
          if (missingFiles.length() > 0)
          {
            result.append(",").append(missingFiles);
          }
        }
      }
    }
    
    if (result.length() > 0)
    {
      result.deleteCharAt(0);
    }    
    assertTrue("'" + result.toString() + "' is not of length 0", result.length() == 0);
    
    assertEquals("Expected number of doc plugins is not " + expectedNumberOfDocPlugins, expectedNumberOfDocPlugins, docPluginsCounter);
    assertEquals("Expected number of source plugins is not " + expectedNumberOfSourcePlugins, expectedNumberOfSourcePlugins, sourcePluginsCounter);
    assertEquals("Expected number of branding plugins is not " + brandingPluginNames.size(), brandingPluginNames.size(), brandingPluginsCounter);
  }
  
  protected String getMissingFiles(File dir, String[] requiredFiles)
  {
    assertTrue("Directory '" + dir + "' is not a directory", dir.isDirectory());
    
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < requiredFiles.length; i++)
    {
      String requiredFile = requiredFiles[i];
      boolean isDirectory = requiredFile.endsWith("/");
      
      if (requiredFile.startsWith(REGEX_SYMBOL))
      {
        boolean found = false;
        String regex = requiredFile.substring(REGEX_SYMBOL.length());
        requiredFile = "[" + regex + "]";
        File[] files = dir.listFiles();
        for (File file : files)
        {
          if (file.getName().matches(regex))
          {
            found =  isDirectory ? file.isDirectory() : file.isFile();
            if (found)
            {
              break;
            }
          }
        }
        
        if (!found)
        {
          result.append(",").append(dir.getAbsolutePath()+File.separator+requiredFile);
        }
      }
      else
      {
        File file = new File(dir, requiredFiles[i]);
        if (isDirectory ? file.isFile() : file.isDirectory())
        {
          result.append(",").append(file.getAbsoluteFile());
        }
      }
    }
    return result.length() > 0 ? result.deleteCharAt(0).toString() : "";
  }
  
  protected String getMissingFiles(JarFile jar, String[] requiredFiles)
  {
    Set<String> requiredFilesSet = new HashSet<String>(Arrays.asList(requiredFiles));
    for (Enumeration<JarEntry> e = jar.entries(); e.hasMoreElements();)
    {
      JarEntry  entry = e.nextElement();
      String name = entry.getName();
      requiredFilesSet.remove(name);
    }
    if(requiredFilesSet.isEmpty())
    {
      return "";
    }
    else
    {
      StringBuffer result = new StringBuffer();
      for (Iterator<String> i = requiredFilesSet.iterator(); i.hasNext();)
      {
        result.append(",").append(jar.getName()).append("-").append(i.next());
      }
      return result.deleteCharAt(0).toString();
    }
  }
  
  protected boolean isDocPlugin(String name)
  {
    return name.endsWith(".doc");
  }
  
  protected boolean isSourcePlugin(String name)
  {
    return name.endsWith(".source");
  }

  protected boolean isBrandingPlugin(String name)
  {
    if (brandingPluginNames == null)
    {
      brandingPluginNames = retrieveBrandingPluginNames();
    }
    return brandingPluginNames.contains(name);
  }

  protected Set<String> retrieveBrandingPluginNames()
  {
    Set<String> brandingPluginNames = new HashSet<String>();
    brandingPluginNames.add("org.eclipse.emf");
    brandingPluginNames.add("org.eclipse.emf.codegen");
    brandingPluginNames.add("org.eclipse.emf.codegen.ecore");
    brandingPluginNames.add("org.eclipse.emf.codegen.ecore.ui");
    brandingPluginNames.add("org.eclipse.emf.codegen.ui");
    brandingPluginNames.add("org.eclipse.emf.common");
    brandingPluginNames.add("org.eclipse.emf.common.ui");
    brandingPluginNames.add("org.eclipse.emf.converter");
    brandingPluginNames.add("org.eclipse.emf.databinding");
    brandingPluginNames.add("org.eclipse.emf.databinding.edit");
    brandingPluginNames.add("org.eclipse.emf.doc");
    brandingPluginNames.add("org.eclipse.emf.ecore");
    brandingPluginNames.add("org.eclipse.emf.ecore.edit");
    brandingPluginNames.add("org.eclipse.emf.ecore.editor");
    brandingPluginNames.add("org.eclipse.emf.edit");
    brandingPluginNames.add("org.eclipse.emf.edit.ui");
    brandingPluginNames.add("org.eclipse.emf.examples");
    brandingPluginNames.add("org.eclipse.emf.examples.source");
    brandingPluginNames.add("org.eclipse.emf.mapping");
    brandingPluginNames.add("org.eclipse.emf.mapping.ecore");
    brandingPluginNames.add("org.eclipse.emf.mapping.ecore.editor");
    brandingPluginNames.add("org.eclipse.emf.mapping.ui");
    brandingPluginNames.add("org.eclipse.emf.source");
    brandingPluginNames.add("org.eclipse.emf.tests");
    brandingPluginNames.add("org.eclipse.xsd");
    brandingPluginNames.add("org.eclipse.xsd.doc");
    brandingPluginNames.add("org.eclipse.xsd.ecore.converter");
    brandingPluginNames.add("org.eclipse.xsd.edit");
    brandingPluginNames.add("org.eclipse.xsd.editor");
    brandingPluginNames.add("org.eclipse.xsd.example");
    brandingPluginNames.add("org.eclipse.xsd.mapping");
    brandingPluginNames.add("org.eclipse.xsd.mapping.editor");
    brandingPluginNames.add("org.eclipse.xsd.source");
    return brandingPluginNames;
  }
}
