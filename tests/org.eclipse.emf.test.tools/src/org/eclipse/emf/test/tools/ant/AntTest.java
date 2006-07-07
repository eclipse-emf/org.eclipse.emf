/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: AntTest.java,v 1.22 2006/07/07 17:22:37 marcelop Exp $
 */
package org.eclipse.emf.test.tools.ant;

import java.io.File;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.test.tools.TestUtil;

public class AntTest extends TestCase
{
  public static boolean UPDATE_EXPECTED_RESULT_ON_FAILURE = System.getProperty("org.eclipse.emf.test.tools.ant.Update") != null;

  public static final String TEST_TOKEN = "@TEST_TOKEN@";
    
  private static final File EXAMPLES_COPY_DIR = new File(TestUtil.getPluginDirectory() + "/ant.example.tmp");
  private static final File EXPECTED_DIR = new File(TestUtil.getPluginDirectory() + "/data/ant.expected");
  private static final File RELOAD_EXPECTED_DIR = new File(TestUtil.getPluginDirectory() + "/data/ant.reload/expected");
  
  
  /**
   * @param name
   */
  public AntTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("EMFAntTest");
    
    // Don't comment out this test
    ts.addTest(new AntTest("suiteSetUp"));
    
    ts.addTest(new AntTest("testJET"));
    ts.addTest(new AntTest("testJMerger"));
    
    ts.addTest(new AntTest("testRose"));
    ts.addTest(new AntTest("testRoseReload"));
    
    ts.addTest(new AntTest("testXSD"));
    ts.addTest(new AntTest("testXSDReload"));
    
    ts.addTest(new AntTest("testXSDs"));
    ts.addTest(new AntTest("testXSDsReload"));
    
    ts.addTest(new AntTest("testEcore"));
    ts.addTest(new AntTest("testEcoreReload"));
    
    // Deletes the temp directory created during the tests to store the
    // generated artifacts
    ts.addTest(new AntTest("suiteTearDown"));
    
    return ts;
  }
  
  public void suiteSetUp() throws Exception
  {
    assertTrue(EXPECTED_DIR.getAbsolutePath() + " doesn't exist", EXPECTED_DIR.isDirectory());
    
    TestUtil.delete(EXAMPLES_COPY_DIR);
    assertFalse(EXAMPLES_COPY_DIR.exists());
    assertTrue(EXAMPLES_COPY_DIR.mkdir());

    String emfAntPluginDir = TestUtil.getPluginDirectory("org.eclipse.emf.ant"); 
    String roseImporterPluginDir = TestUtil.getPluginDirectory("org.eclipse.emf.importer.rose");
    String xsdImporterPluginDir = TestUtil.getPluginDirectory("org.eclipse.xsd.ecore.importer");
    String ecoreImporterPluginDir = TestUtil.getPluginDirectory("org.eclipse.emf.importer.ecore");

    String emfSourcePlugin = TestUtil.getPluginDirectory("org.eclipse.emf.source");
    String xsdSourcePlugin = TestUtil.getPluginDirectory("org.eclipse.xsd.source");

    // JET and Merge
    File examplesDir = null;
    if (emfAntPluginDir != null)
    {
      examplesDir = new File(emfAntPluginDir + "/examples");
    }
    
    if (!examplesDir.isDirectory() && emfSourcePlugin != null)
    {
      File emfAntPluginSrcDir = getPluginSourceSubDirectory(emfSourcePlugin, "org.eclipse.emf.ant");
      if (emfAntPluginSrcDir != null)
      {
        examplesDir = new File(emfAntPluginSrcDir + "/examples");
      }
    }
    assertNotNull(examplesDir);
    assertTrue(examplesDir.getAbsolutePath() + " doesn't exist", examplesDir.isDirectory());
    TestUtil.copyFiles(examplesDir, EXAMPLES_COPY_DIR, true);

    // Rose
    File libraryDir = null;
    if (roseImporterPluginDir != null)
    {
      libraryDir = new File(roseImporterPluginDir + "/examples/library");
    }
    
    if (!libraryDir.isDirectory() && emfSourcePlugin != null)
    {
      File roseImporterPluginSrcDir = getPluginSourceSubDirectory(emfSourcePlugin, "org.eclipse.emf.importer.rose");
      if (roseImporterPluginSrcDir != null)
      {
        libraryDir = new File(roseImporterPluginSrcDir + "/examples/library");
      }
    }
    assertNotNull(libraryDir);
    assertTrue(libraryDir.getAbsolutePath() + " doesn't exist", libraryDir.isDirectory());
    TestUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "library.rose"), true);
    
    // XSD and XSDs
    libraryDir = null;
    if (xsdImporterPluginDir != null)
    {
      libraryDir = new File(xsdImporterPluginDir + "/examples/library");
    }
    
    if (!libraryDir.isDirectory() && emfSourcePlugin != null)
    {
      File xsdImporterPluginSrcDir = getPluginSourceSubDirectory(xsdSourcePlugin, "org.eclipse.xsd.ecore.importer");
      if (xsdImporterPluginSrcDir != null)
      {
        libraryDir = new File(xsdImporterPluginSrcDir + "/examples/library");
      }
    }
    assertNotNull(libraryDir);
    assertTrue(libraryDir.getAbsolutePath() + " doesn't exist", libraryDir.isDirectory());
    TestUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "library.xsd"), true);
    TestUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "library.xsds"), true);
    
    // Ecore
    libraryDir = null;
    if (ecoreImporterPluginDir != null)
    {
      libraryDir = new File(ecoreImporterPluginDir + "/examples/library");
    }
    
    if (!libraryDir.isDirectory() && emfSourcePlugin != null)
    {
      File ecoreImporterPluginSrcDir = getPluginSourceSubDirectory(emfSourcePlugin, "org.eclipse.emf.importer.ecore");
      if (ecoreImporterPluginSrcDir != null)
      {
        libraryDir = new File(ecoreImporterPluginSrcDir + "/examples/library");
      }
    }
    assertNotNull(libraryDir);
    assertTrue(libraryDir.getAbsolutePath() + " doesn't exist", libraryDir.isDirectory());
    TestUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "library.ecore"), true);
  }
  
  protected File getPluginSourceSubDirectory(String sourcePluginDir, String pluginID)
  {
    File sourceDir = new File(sourcePluginDir + "/src");
    if (sourceDir.isDirectory())
    {
      File[] files = sourceDir.listFiles();
      for (int i = 0; i < files.length; i++)
      {
        if (files[i].isDirectory())
        {
          String name = files[i].getName();
          if (name.equals(pluginID) || name.startsWith(pluginID + "_"))
          {
            return files[i].getAbsoluteFile();
          }
        }
      }
    }
    
    return null;
  }
  
  public void suiteTearDown() throws Exception
  {
    TestUtil.delete(EXAMPLES_COPY_DIR);
  }
  
  public void testJET() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "jet");
    File rootExpectedDir = new File(EXPECTED_DIR, "jet");
    File antScript = new File(rootDir, "build.xml");
    runAntAndTest(rootDir, rootExpectedDir, antScript, "genClasses", null);
  }
  
  public void testJMerger() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "jmerger");
    File rootExpectedDir = new File(EXPECTED_DIR, "jmerger");
    File antScript = new File(rootDir, "build.xml");
    
    TestUtil.runAnt(antScript, "createNewFile");
    assertGeneratedFile(rootDir, rootExpectedDir, "NewFile.java", null);
    TestUtil.runAnt(antScript, "overwriteTarget");
    assertGeneratedFile(rootDir, rootExpectedDir, "Target.java", null);
  }

  public void testRose() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "library.rose");
    File rootExpectedDir = new File(EXPECTED_DIR, "library.rose");
    File antScript = new File(rootDir, "build/build.xml");
    
    String[] testTokenReplacements = new String[2];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(EXAMPLES_COPY_DIR.getAbsolutePath()).toString());
    testTokenReplacements[1] = File.separator;
           
    runAntAndTest(rootDir, rootExpectedDir, antScript, null, testTokenReplacements);
  }

  public void testRoseReload() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "library.rose");
    File rootExpectedDir = new File(RELOAD_EXPECTED_DIR, "library.rose");
    File antScript = new File(rootDir, "build/reload.xml");
    
    TestUtil.copyFiles(new File(rootExpectedDir, "model"), new File(rootDir, "model"), true);
    TestUtil.copyFiles(new File(rootExpectedDir, "build"), new File(rootDir, "build"), true);
   
    String[] testTokenReplacements = new String[2];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(EXAMPLES_COPY_DIR.getAbsolutePath()).toString());
    testTokenReplacements[1] = File.separator;
           
    adjustGenModelForReload(new File(rootDir, "emf/library.genmodel"));
    runAntAndTest(rootDir, rootExpectedDir, antScript, "rose", testTokenReplacements);
  }
  
  public void testXSD() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "library.xsd");
    File rootExpectedDir = new File(EXPECTED_DIR, "library.xsd");
    File antScript = new File(rootDir, "build/codeGenFromXSD.xml");

    String[] testTokenReplacements = new String[1];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(EXAMPLES_COPY_DIR.getAbsolutePath()).toString());
           
    runAntAndTest(rootDir, rootExpectedDir, antScript, null, testTokenReplacements);
  }
  
  public void testXSDReload() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "library.xsd");
    File rootExpectedDir = new File(RELOAD_EXPECTED_DIR, "library.xsd");
    File antScript = new File(rootDir, "build/reload.xml");
    
    TestUtil.copyFiles(new File(rootExpectedDir, "model"), new File(rootDir, "model"), true);
    TestUtil.copyFiles(new File(rootExpectedDir, "build"), new File(rootDir, "build"), true);
   
    String[] testTokenReplacements = new String[1];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(EXAMPLES_COPY_DIR.getAbsolutePath()).toString());
        
    adjustGenModelForReload(new File(rootDir, "emf/library.genmodel"));
    runAntAndTest(rootDir, rootExpectedDir, antScript, "xsd", testTokenReplacements);
    
    assertFalse(ResourcesPlugin.getWorkspace().getDescription().isAutoBuilding());
  }  

  public void testXSDs() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "library.xsds");
    File rootExpectedDir = new File(EXPECTED_DIR, "library.xsds");
    File antScript = new File(rootDir, "build/codeGenFromMultipleXSD.xml");

    String[] testTokenReplacements = new String[1];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(EXAMPLES_COPY_DIR.getAbsolutePath()).toString());
           
    runAntAndTest(rootDir, rootExpectedDir, antScript, null, testTokenReplacements);
  }
  
  public void testXSDsReload() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "library.xsds");
    File rootExpectedDir = new File(RELOAD_EXPECTED_DIR, "library.xsds");
    File antScript = new File(rootDir, "build/reload.xml");
    
    TestUtil.copyFiles(new File(rootExpectedDir, "model"), new File(rootDir, "model"), true);
    TestUtil.copyFiles(new File(rootExpectedDir, "build"), new File(rootDir, "build"), true);
   
    String[] testTokenReplacements = new String[2];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(EXAMPLES_COPY_DIR.getAbsolutePath()).toString());
    testTokenReplacements[1] = testTokenReplacements[0].charAt(1) == ':' ? "/" : "";
           
    adjustGenModelForReload(new File(rootDir, "emf/library.genmodel"));
    runAntAndTest(rootDir, rootExpectedDir, antScript, "xsds", testTokenReplacements);
    
    assertTrue(ResourcesPlugin.getWorkspace().getDescription().isAutoBuilding());
  }  
  
  public void testEcore() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "library.ecore");
    File rootExpectedDir = new File(EXPECTED_DIR, "library.ecore");
    File antScript = new File(rootDir, "build/build.xml");
    
    String[] testTokenReplacements = new String[2];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(EXAMPLES_COPY_DIR.getAbsolutePath()).toString());
    testTokenReplacements[1] = File.separator;
           
    runAntAndTest(rootDir, rootExpectedDir, antScript, null, testTokenReplacements);
  }

  public void testEcoreReload() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "library.ecore");
    File rootExpectedDir = new File(RELOAD_EXPECTED_DIR, "library.ecore");
    File antScript = new File(rootDir, "build/reload.xml");
    
    TestUtil.copyFiles(new File(rootExpectedDir, "model"), new File(rootDir, "model"), true);
    TestUtil.copyFiles(new File(rootExpectedDir, "build"), new File(rootDir, "build"), true);
   
    String[] testTokenReplacements = new String[2];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(EXAMPLES_COPY_DIR.getAbsolutePath()).toString());
    testTokenReplacements[1] = File.separator;
           
    adjustGenModelForReload(new File(rootDir, "emf/library.genmodel"));
    runAntAndTest(rootDir, rootExpectedDir, antScript, "ecore", testTokenReplacements);
  }  

  private void runAntAndTest(File rootDir, File rootExpectedDir, File antScript, String antScriptArguments, String[] testTokenReplacements) throws CoreException
  {
    assertTrue(rootDir.getAbsolutePath() + " doesn't exist", rootDir.isDirectory());
    assertTrue(rootExpectedDir.getAbsolutePath() + " doesn't exist", rootExpectedDir.isDirectory());
    assertTrue(antScript.getAbsolutePath() + " doesn't exist", antScript.isFile());
    
    TestUtil.runAnt(antScript, antScriptArguments);   
    assertGeneratedFiles(rootDir, rootExpectedDir, null, testTokenReplacements);    
  }
   
  /**
   * Compares each expected file in rootExpectedDir with the equivalent file in  rootDir.
   * @param rootDir
   * @param rootExpectedDir
   * @param expectedDir
   */
  private void assertGeneratedFiles(File rootDir, File rootExpectedDir, File expectedDir, String[] testTokenReplacements)
  {
    if (expectedDir == null) expectedDir = rootExpectedDir;
      
    File[] children = expectedDir.listFiles();
    for (int i = 0, maxi = children.length; i < maxi; i++)
    {
      if (children[i].isFile())
      {
        String file = children[i].getAbsolutePath().substring(rootExpectedDir.getAbsolutePath().length());
        assertGeneratedFile(rootDir, rootExpectedDir, file, testTokenReplacements);
      }
      else if (children[i].isDirectory())
      {
        if (!"CVS".equals(children[i].getName()))
        {
          assertGeneratedFiles(rootDir, rootExpectedDir, children[i], testTokenReplacements);
        }
      }
    }    
  }
  
  private void assertGeneratedFile(File dir, File expectedDir, String file, String[] testTokenReplacements)
  {
    File expectedFile = new File(expectedDir, file);
    File generatedFile = new File(dir, file);
    
    assertTrue("File: " + expectedFile, expectedFile.isFile());
    assertTrue("File: " + generatedFile, generatedFile.isFile());
    
    String expectedContent = TestUtil.readFile(expectedFile, true);
    if (testTokenReplacements != null)
    {
      for (int i=testTokenReplacements.length-1; i >= 0; i--)
      {
        String replacement = testTokenReplacements[i];
        expectedContent = expectedContent.replaceAll(TEST_TOKEN+i, replacement.replaceAll("\\\\", "\\\\\\\\"));
      }
    }
    String generatedContent = TestUtil.readFile(generatedFile, true);
    
    //Remove CVS tags
    expectedContent = expectedContent.replaceAll("\\$Id.*\\$", "");
    generatedContent = generatedContent.replaceAll("\\$Id.*\\$", "");
        
    if (UPDATE_EXPECTED_RESULT_ON_FAILURE)
    {
      try
      {
        assertEquals("File: " + file, expectedContent, generatedContent);
      }
      catch (Throwable exception)
      {
        TestUtil.copyFile(generatedFile, expectedFile, true);
      }
    }
    else
    {
      assertEquals("File: " + file, expectedContent, generatedContent);
    }
  }
  
  private String upperCaseDriveLetter(String path)
  {
    if (path != null && path.charAt(1) == ':')
    {
      char originalChar = path.charAt(0);
      char upperCase = Character.toUpperCase(originalChar);
      if (originalChar != upperCase)
      {
        StringBuffer buffer = new StringBuffer(path);
        buffer.setCharAt(0, upperCase);
        path = buffer.toString();
      }
    }
    return path;
  }
  
  protected void adjustGenModelForReload(File genModelFile)
  {
    if (genModelFile.exists())
    {
      ResourceSet resourceSet = new ResourceSetImpl();
      Resource resource = resourceSet.getResource(URI.createFileURI(genModelFile.getAbsolutePath()), true);
      if (!resource.getContents().isEmpty())
      {
        Object object = resource.getContents().get(0);
        if (object instanceof GenModel)
        {
          adjustGenModelForReload((GenModel)object);
          try
          {
            resource.save(null);
          }
          catch (IOException e)
          {
          }
        }
      }
    }
  }
  
  protected void adjustGenModelForReload(GenModel genModel)
  {
    if (genModel != null)
    {
      genModel.setNonNLSMarkers(true);
    }
  }  
}