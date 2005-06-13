package org.eclipse.emf.test.build;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BuildTest extends TestCase
{
  protected final static String[] REQUIRED_FEATURE_FILES = 
   {"eclipse_update_120.jpg", "epl-v10.html", "feature.properties", "feature.xml", "license.html"};
  protected final static String[] REQUIRED_REGULAR_PLUGIN_FILES = 
   {"about.html", "plugin.properties", "plugin.xml", "META-INF/MANIFEST.MF"};
  protected final static String[] REQUIRED_DOC_PLUGIN_FILES = 
   {"about.html", "plugin.properties", "plugin.xml", "META-INF/MANIFEST.MF",
   "doc.zip", "toc.xml"};  
  protected final static String[] REQUIRED_BRANDING_PLUGIN_FILES = 
   {"about.html", "plugin.properties", "plugin.xml", "META-INF/MANIFEST.MF",
    "about.ini", "about.mappings", "about.properties"};
  
  protected static int expectedNumberOfDocPlugins = 3;
  
  protected File featuresDir;
  protected File pluginsDir;
  
  protected Set brandingPluginNames;
  
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
    assertNotNull(featuresDir);
    assertTrue(featuresDir.isDirectory());
    
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
            brandingPluginNames = new HashSet();
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
    assertTrue(result.toString(), result.length() == 0);
  }

  public void testPlugins() throws Exception
  {
    assertNotNull(pluginsDir);
    assertTrue(pluginsDir.isDirectory());
    
    StringBuffer result = new StringBuffer();
    int docPluginsCounter = 0;
    int brandingPluginsCounter = 0;
    
    File[] plugins = pluginsDir.listFiles();
    for (int i = 0; i < plugins.length; i++)
    {
      File plugin = plugins[i];
      if (isPluginToTest(plugin))
      {
        JarFile jarFile = plugin.isFile() ? new JarFile(plugin) : null;
        String name = plugin.getName().replaceAll("(_\\d\\.\\d\\.\\d)?(\\.jar)?$", "");
        if (isDocPlugin(name))
        {
          docPluginsCounter++;
          String missingFiles = getMissingFiles(plugin, REQUIRED_DOC_PLUGIN_FILES); 
          if (missingFiles.length() > 0)
          {
            result.append(",").append(missingFiles);
          }
          else
          {
            String docZipResult = testDocZip(plugin);
            if (docZipResult.length() > 0)
            {
              result.append(",").append(docZipResult);
            }
          }
        }
        if (isBrandingPlugin(name))
        {
          System.out.println(name);
          brandingPluginsCounter++;
          String missingFiles = jarFile == null ? 
            getMissingFiles(plugin, REQUIRED_BRANDING_PLUGIN_FILES) : 
            getMissingFiles(jarFile, REQUIRED_BRANDING_PLUGIN_FILES);
          if (missingFiles.length() > 0)
          {
            result.append(",").append(missingFiles);
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
    assertTrue(result.toString(), result.length() == 0);
    
    assertEquals("expected number of doc plugins", expectedNumberOfDocPlugins, docPluginsCounter);
    assertEquals("expected number of branding plugins", brandingPluginNames.size(), brandingPluginsCounter);
  }
  
  protected String getMissingFiles(File dir, String[] requiredFiles)
  {
    assertTrue(dir.isDirectory());
    
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < requiredFiles.length; i++)
    {
      File file = new File(dir, requiredFiles[i]);
      if (!file.isFile())
      {
        result.append(",").append(file.getAbsoluteFile());
      }
    }
    return result.length() > 0 ? result.deleteCharAt(0).toString() : "";
  }
  
  protected String getMissingFiles(JarFile jar, String[] requiredFiles)
  {
    Set requiredFilesSet = new HashSet(Arrays.asList(requiredFiles));
    for (Enumeration e = jar.entries(); e.hasMoreElements();)
    {
      JarEntry  entry = (JarEntry)e.nextElement();
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
      for (Iterator i = requiredFilesSet.iterator(); i.hasNext();)
      {
        result.append(",").append(jar.getName()).append("-").append((String)i.next());
      }
      return result.deleteCharAt(0).toString();
    }
  }
  
  protected boolean isDocPlugin(String name)
  {
    return name.endsWith(".doc");
  }
  
  protected boolean isBrandingPlugin(String name)
  {
    if (brandingPluginNames == null)
    {
      brandingPluginNames = retrieveBrandingPluginNames();
    }
    return brandingPluginNames.contains(name);
  }

  protected Set retrieveBrandingPluginNames()
  {
    Set brandingPluginNames = new HashSet();
    brandingPluginNames.add("org.eclipse.emf.doc");
    brandingPluginNames.add("org.eclipse.emf.ecore.sdo.doc");
    brandingPluginNames.add("org.eclipse.emf.ecore.sdo.source");
    brandingPluginNames.add("org.eclipse.emf.ecore.sdo");
    brandingPluginNames.add("org.eclipse.emf.source");
    brandingPluginNames.add("org.eclipse.emf");
    brandingPluginNames.add("org.eclipse.xsd.doc");
    brandingPluginNames.add("org.eclipse.xsd.source");
    brandingPluginNames.add("org.eclipse.xsd");
    return brandingPluginNames;
  }
  
  protected String testDocZip(File pluginDir) throws ZipException, IOException
  {
    boolean hasJavadocGif = false;
    ZipFile docZip = new ZipFile(new File(pluginDir, "doc.zip"));
    for (Enumeration entries=docZip.entries(); entries.hasMoreElements();)
    {
      ZipEntry entry = (ZipEntry)entries.nextElement();
      String name = entry.getName();
      if(name.matches("references\\/javadoc\\/.*\\/doc-files/.*\\.gif$"))
      {
        hasJavadocGif = true;
        break;
      }
    }
    docZip.close();
    
    return hasJavadocGif ? "" : 
      pluginDir + "/doc.zip doesn't have a reference/javadoc/*/doc-files/*.gif";
  }
}
