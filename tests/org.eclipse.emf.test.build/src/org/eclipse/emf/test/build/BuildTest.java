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
 * $Id: BuildTest.java,v 1.24 2008/12/01 23:38:55 nickb Exp $
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
  protected final static String[] REQUIRED_SOURCE_PLUGIN_FILES = 
   {"src/"};
  protected final static String[] REQUIRED_SRC_SUBDIR_FILES = 
   {"about.html", REGEX_SYMBOL + "^.*src.zip$"};
  
  protected static int expectedNumberOfDocPlugins = 2;
  protected static int expectedNumberOfSourcePlugins = 3;
  
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
          String missingFiles = getMissingFiles(plugin, REQUIRED_SOURCE_PLUGIN_FILES); 
          if (missingFiles.length() > 0)
          {
            result.append(",").append(missingFiles);
          }
          else
          {
            String sourceDirResult = testSrcDir(plugin);
            if (sourceDirResult.length() > 0)
            {
              result.append(",").append(sourceDirResult);
            }
          }          
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
    brandingPluginNames.add("org.eclipse.emf.doc");
    brandingPluginNames.add("org.eclipse.emf.source");
    brandingPluginNames.add("org.eclipse.emf");
    brandingPluginNames.add("org.eclipse.xsd.doc");
    brandingPluginNames.add("org.eclipse.xsd.source");
    brandingPluginNames.add("org.eclipse.xsd");
    return brandingPluginNames;
  }
  
  /*
   * For example, looking for these folders, but should have no org/eclipse/ folder as well.
   *
   * nickb@emf:/tmp/xsd221/eclipse/plugins/org.eclipse.xsd.source_2.2.1.v200609210005/src $ 
   *    ls . org.eclipse.emf.mapping.xsd2ecore.editor_2.1.0.v200609210005
   * .:
   * org.eclipse.emf.mapping.xsd2ecore.editor_2.1.0.v200609210005  org.eclipse.xsd.edit_2.2.1.v200609210005
   * org.eclipse.emf.mapping.xsd2ecore_2.1.0.v200609210005         org.eclipse.xsd.editor_2.2.0.v200609210005
   * org.eclipse.xsd.ecore.exporter_2.2.1.v200609210005            org.eclipse.xsd_2.2.1.v200609210005
   * org.eclipse.xsd.ecore.importer_2.2.0.v200609210005
   * 
   * org.eclipse.emf.mapping.xsd2ecore.editor_2.1.0.v200609210005:
   * about.html  src.zip
   */
  protected String testSrcDir(File pluginDir)
  {
    File srcDir = new File(pluginDir, "src");
    assertTrue("Directory '" + srcDir + "' is not a directory", srcDir.isDirectory());
    
    StringBuffer result = new StringBuffer();
    File[] files = srcDir.listFiles();
    for (int i = 0; i < files.length; i++)
    {
      File dir = files[i];
      assertTrue("Directory '" + dir + "' is not a directory", dir.isDirectory());
      
      String name = dir.getName(); 
      assertTrue("Directory '" + dir + "' has name '" + name+ "' which does not contain an '_'", name.indexOf('_') >= 0);
      
      String missingFiles = getMissingFiles(dir, REQUIRED_SRC_SUBDIR_FILES);
      if (missingFiles.length() > 0)
      {
        result.append(",").append(missingFiles);
      }
    }
    return result.length() > 0 ? result.deleteCharAt(0).toString() : "";
  }  
}
