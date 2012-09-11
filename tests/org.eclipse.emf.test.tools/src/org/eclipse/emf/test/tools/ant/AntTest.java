/**
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
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

import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.tools.AllSuites;
import org.eclipse.emf.test.tools.AntUtil;
import org.eclipse.jdt.core.JavaCore;

public class AntTest extends TestCase
{
  public static boolean UPDATE_EXPECTED_RESULT_ON_FAILURE = System.getProperty("org.eclipse.emf.test.tools.ant.Update") != null;

  public static final String TEST_TOKEN = "@TEST_TOKEN@";
    
  private static final File EXAMPLES_COPY_DIR = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/ant.example.tmp");
  private static final File EXPECTED_DIR = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/ant.expected/");
  
  
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
    
    ts.addTest(new AntTest("testJETCompiler"));
    ts.addTest(new AntTest("testJETEmitter"));
    ts.addTest(new AntTest("testJMerger"));
    
    ts.addTest(new AntTest("testRose14"));
    ts.addTest(new AntTest("testRoseReload14"));
    ts.addTest(new AntTest("testRose50"));
    ts.addTest(new AntTest("testRoseReload50"));
    ts.addTest(new AntTest("testRose1450"));
    ts.addTest(new AntTest("testRoseReload1450"));
    
    ts.addTest(new AntTest("testXSD14"));
    ts.addTest(new AntTest("testXSDReload14"));
    ts.addTest(new AntTest("testXSD50"));
    ts.addTest(new AntTest("testXSDReload50"));
    ts.addTest(new AntTest("testXSD1450"));
    ts.addTest(new AntTest("testXSDReload1450"));
    
    ts.addTest(new AntTest("testXSDs14"));
    ts.addTest(new AntTest("testXSDsReload14"));
    ts.addTest(new AntTest("testXSDs50"));
    ts.addTest(new AntTest("testXSDsReload50"));
    ts.addTest(new AntTest("testXSDs1450"));
    ts.addTest(new AntTest("testXSDsReload1450"));
    
    ts.addTest(new AntTest("testEcore14"));
    ts.addTest(new AntTest("testEcoreReload14"));
    ts.addTest(new AntTest("testEcore50"));
    ts.addTest(new AntTest("testEcoreReload50"));
    ts.addTest(new AntTest("testEcore1450"));
    ts.addTest(new AntTest("testEcoreReload1450"));
   
    ts.addTest(new AntTest("testJava14"));
//    ts.addTest(new AntTest("testJavaReload14"));
    ts.addTest(new AntTest("testJava50"));
//    ts.addTest(new AntTest("testJavaReload50"));
//    ts.addTest(new AntTest("testJava1450"));
//    ts.addTest(new AntTest("testJavaReload1450"));
    
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
    String javaImporterPluginDir = TestUtil.getPluginDirectory("org.eclipse.emf.importer.java");

    String emfAntSourcePluginDir = TestUtil.getPluginDirectory("org.eclipse.emf.ant.source");
    String roseImporterSourcePluginDir = TestUtil.getPluginDirectory("org.eclipse.emf.importer.rose.source");
    String xsdImporterSourcePluginDir = TestUtil.getPluginDirectory("org.eclipse.xsd.ecore.importer.source");
    String ecoreImporterSourcePluginDir = TestUtil.getPluginDirectory("org.eclipse.emf.importer.ecore.source");
    String javaImporterSourcePluginDir = TestUtil.getPluginDirectory("org.eclipse.emf.importer.java.source");

    // JET and Merge
    File examplesDir = null;
    if (emfAntPluginDir != null)
    {
      examplesDir = new File(emfAntPluginDir + "/examples");
    }
    
    assertNotNull(examplesDir);
    if (examplesDir == null)
    {
      throw new AssertionError("examplesDir can't be null");
    }
    if (!examplesDir.isDirectory() && emfAntSourcePluginDir != null)
    {
      examplesDir = new File(emfAntSourcePluginDir + "/examples");
    }
    assertTrue(examplesDir.getAbsolutePath() + " doesn't exist", examplesDir.isDirectory());
    AntUtil.copyFiles(examplesDir, EXAMPLES_COPY_DIR, true);

    // Rose
    File libraryDir = null;
    if (roseImporterPluginDir != null)
    {
      libraryDir = new File(roseImporterPluginDir + "/examples/library");
    }
    
    assertNotNull(libraryDir);
    assert libraryDir != null;
    if (libraryDir == null)
    {
      throw new AssertionError("libraryDir can't be null");
    }
    if (!libraryDir.isDirectory() && roseImporterSourcePluginDir != null)
    {
      libraryDir = new File(roseImporterSourcePluginDir + "/examples/library");
    }
    assertTrue(libraryDir.getAbsolutePath() + " doesn't exist", libraryDir.isDirectory());
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.rose.1.4"), true);
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.rose.5.0"), true);
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.rose.1.4_5.0"), true);
    
    // XSD and XSDs
    libraryDir = null;
    if (xsdImporterPluginDir != null)
    {
      libraryDir = new File(xsdImporterPluginDir + "/examples/library");
    }
    
    assertNotNull(libraryDir);
    assert libraryDir != null;
    if (libraryDir == null)
    {
      throw new AssertionError("libraryDir can't be null");
    }
    if (!libraryDir.isDirectory() && xsdImporterSourcePluginDir != null)
    {
      libraryDir = new File(xsdImporterSourcePluginDir + "/examples/library");
    }
    assertTrue(libraryDir.getAbsolutePath() + " doesn't exist", libraryDir.isDirectory());
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.xsd.1.4"), true);
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.xsd.5.0"), true);
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.xsd.1.4_5.0"), true);
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.xsds.1.4"), true);
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.xsds.5.0"), true);
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.xsds.1.4_5.0"), true);
    
    // Ecore
    libraryDir = null;
    if (ecoreImporterPluginDir != null)
    {
      libraryDir = new File(ecoreImporterPluginDir + "/examples/library");
    }
    
    assertNotNull(libraryDir);
    if (libraryDir == null)
    {
      throw new AssertionError("libraryDir can't be null");
    }
    if (!libraryDir.isDirectory() && ecoreImporterSourcePluginDir != null)
    {
      libraryDir = new File(ecoreImporterSourcePluginDir + "/examples/library");
    }
    assertTrue(libraryDir.getAbsolutePath() + " doesn't exist", libraryDir.isDirectory());
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.ecore.1.4"), true);
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.ecore.5.0"), true);
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.ecore.1.4_5.0"), true);

    // Java
    libraryDir = null;
    if (javaImporterPluginDir != null)
    {
      libraryDir = new File(javaImporterPluginDir + "/examples/library");
    }
    
    assertNotNull(libraryDir);
    if (libraryDir == null)
    {
      throw new AssertionError("libraryDir can't be null");
    }
    if (!libraryDir.isDirectory() && javaImporterSourcePluginDir != null)
    {
      libraryDir = new File(javaImporterSourcePluginDir + "/examples/library");
    }
    assertTrue(libraryDir.getAbsolutePath() + " doesn't exist", libraryDir.isDirectory());
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.java.1.4"), true);
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.java.5.0"), true);
    File sourceDirectory = new File(EXAMPLES_COPY_DIR, "/library.java.5.0/src/org/eclipse/example/library");
    moveFile(new File(sourceDirectory, "BookCategory.java5.txt"), new File(sourceDirectory, "BookCategory.java"));
    moveFile(new File(sourceDirectory, "Library.java5.txt"), new File(sourceDirectory, "Library.java"));
    moveFile(new File(sourceDirectory, "Writer.java5.txt"), new File(sourceDirectory, "Writer.java"));
    
    AntUtil.copyFiles(libraryDir, new File(EXAMPLES_COPY_DIR, "/library.java.1.4_5.0"), true);
  }
  
  protected void moveFile(File source, File target)
  {
    if (target.exists())
    {
      assertTrue(target.delete());  
    }
    assertFalse(target.exists());
    
    assertTrue(source.exists());
    assertTrue(source.renameTo(target));
    assertTrue(target.exists());   
  }
  
  public void suiteTearDown() throws Exception
  {
    TestUtil.delete(EXAMPLES_COPY_DIR);
  }
  
  public void testJETEmitter() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "jetemitter");
    File rootExpectedDir = new File(EXPECTED_DIR, "jetemitter");
    File antScript = new File(rootDir, "build.xml");
    runAntAndTest(rootDir, rootExpectedDir, antScript, "genClasses", null);
  }
  
  public void testJETCompiler() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "jetcompiler");
    File rootExpectedDir = new File(EXPECTED_DIR, "jetcompiler");
    File antScript = new File(rootDir, "build.xml");
    runAntAndTest(rootDir, rootExpectedDir, antScript, null, null);
  }

  public void testJMerger() throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "jmerger");
    File rootExpectedDir = new File(EXPECTED_DIR, "jmerger");
    File antScript = new File(rootDir, "build.xml");
    
    AntUtil.runAnt(antScript, "createNewFile");
    assertGeneratedFile(rootDir, rootExpectedDir, "NewFile.java", null);
    AntUtil.runAnt(antScript, "overwriteTarget");
    assertGeneratedFile(rootDir, rootExpectedDir, "Target.java", null);
  }
  
  protected void roseTest(String jdkLevel, String directorySegment) throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "/library.rose." + directorySegment);
    File rootExpectedDir = new File(EXPECTED_DIR, "/models/" + directorySegment + "/creation/library.rose");
    File antScript = new File(rootDir, "build/build.xml");
    
    String[] testTokenReplacements = new String[3];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(rootDir.getAbsolutePath()).toString());
    testTokenReplacements[1] = File.separator;
    testTokenReplacements[2] = getVMType();
           
    runAntAndTest(rootDir, rootExpectedDir, antScript, "-DgenJDKLevel=\""+jdkLevel+"\"", testTokenReplacements);    
  }

  public void testRose14() throws Exception
  {
    roseTest("1.4", "1.4");
  }

  public void testRose50() throws Exception
  {
    roseTest("5.0", "5.0");
  }

  public void testRose1450() throws Exception
  {
    roseTest("1.4", "1.4_5.0");
  }
  
  protected void roseReloadTest(String jdkLevel, String directorySegment) throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "/library.rose." + directorySegment);
    File rootExpectedDir = new File(EXPECTED_DIR, "/models/" + directorySegment + "/reload/library.rose");
    File antScript = new File(rootDir, "build/reload.xml");
    
    AntUtil.copyFiles(new File(rootExpectedDir, "model"), new File(rootDir, "model"), true);
    AntUtil.copyFiles(new File(rootExpectedDir, "build"), new File(rootDir, "build"), true);
   
    String[] testTokenReplacements = new String[3];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(rootDir.getAbsolutePath()).toString());
    testTokenReplacements[1] = File.separator;
    testTokenReplacements[2] = getVMType();
           
    adjustGenModelForReload(new File(rootDir, "emf/library.genmodel"));
    runAntAndTest(rootDir, rootExpectedDir, antScript, "-DgenJDKLevel=\""+jdkLevel+"\" rose", testTokenReplacements);
  }
  
  public void testRoseReload14() throws Exception
  {
    roseReloadTest("1.4", "1.4");
  }  

  public void testRoseReload50() throws Exception
  {
    roseReloadTest("5.0", "5.0");
  } 
  
  public void testRoseReload1450() throws Exception
  {
    genJDKLevel = GenJDKLevel.JDK50_LITERAL;
    roseReloadTest("5.0", "1.4_5.0");
  }  
  protected void xsdTest(String jdkLevel, String directorySegment) throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "/library.xsd." + directorySegment);
    File rootExpectedDir = new File(EXPECTED_DIR, "/models/" + directorySegment + "/creation/library.xsd");
    File antScript = new File(rootDir, "build/codeGenFromXSD.xml");

    String[] testTokenReplacements = new String[3];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(rootDir.getAbsolutePath()).toString());
    testTokenReplacements[1] = rootDir.getName();
    testTokenReplacements[2] = getVMType();
           
    runAntAndTest(rootDir, rootExpectedDir, antScript, "-DgenJDKLevel=\""+jdkLevel+"\"", testTokenReplacements);
  }

  public void testXSD14() throws Exception
  {
    xsdTest("1.4", "1.4");
  }

  public void testXSD50() throws Exception
  {
    xsdTest("5.0", "5.0");
  }
  
  public void testXSD1450() throws Exception
  {
    xsdTest("1.4", "1.4_5.0");
  }
  
  protected void xsdReloadTest(String jdkLevel, String directorySegment) throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "/library.xsd." + directorySegment);
    File rootExpectedDir = new File(EXPECTED_DIR, "/models/" + directorySegment + "/reload/library.xsd");
    File antScript = new File(rootDir, "build/reload.xml");
    
    AntUtil.copyFiles(new File(rootExpectedDir, "model"), new File(rootDir, "model"), true);
    AntUtil.copyFiles(new File(rootExpectedDir, "build"), new File(rootDir, "build"), true);
   
    String[] testTokenReplacements = new String[3];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(rootDir.getAbsolutePath()).toString());
    testTokenReplacements[1] = rootDir.getName();
    testTokenReplacements[2] = getVMType();
        
    adjustGenModelForReload(new File(rootDir, "emf/library.genmodel"));
    runAntAndTest(rootDir, rootExpectedDir, antScript, "-DgenJDKLevel=\""+jdkLevel+"\" xsd", testTokenReplacements);
    
    assertFalse(ResourcesPlugin.getWorkspace().getDescription().isAutoBuilding());
  }  

  public void testXSDReload14() throws Exception
  {
    xsdReloadTest("1.4", "1.4");
  }

  public void testXSDReload50() throws Exception
  {
    xsdReloadTest("5.0", "5.0");
  }
  
  protected void xsdsTest(String jdkLevel, String directorySegment) throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "/library.xsds." + directorySegment);
    File rootExpectedDir = new File(EXPECTED_DIR, "/models/" + directorySegment + "/creation/library.xsds");
    File antScript = new File(rootDir, "build/codeGenFromMultipleXSD.xml");

    String[] testTokenReplacements = new String[2];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(rootDir.getAbsolutePath()).toString());
    testTokenReplacements[1] = getVMType();
           
    runAntAndTest(rootDir, rootExpectedDir, antScript, "-DgenJDKLevel=\""+jdkLevel+"\"", testTokenReplacements);
  }
  
  public void testXSDs14() throws Exception
  {
    xsdsTest("1.4", "1.4");
  }

  public void testXSDs50() throws Exception
  {
    xsdsTest("5.0", "5.0");
  }
    
  public void testXSDs1450() throws Exception
  {
    xsdsTest("1.4", "1.4_5.0");
  }
  
  public void testXSDReload1450() throws Exception
  {
    genJDKLevel = GenJDKLevel.JDK50_LITERAL;
    xsdReloadTest("5.0", "1.4_5.0");
  }
  
  protected void xsdsReloadTest(String jdkLevel, String directorySegment) throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "/library.xsds." + directorySegment);
    File rootExpectedDir = new File(EXPECTED_DIR, "/models/" + directorySegment + "/reload/library.xsds");
    File antScript = new File(rootDir, "build/reload.xml");
    
    AntUtil.copyFiles(new File(rootExpectedDir, "model"), new File(rootDir, "model"), true);
    AntUtil.copyFiles(new File(rootExpectedDir, "build"), new File(rootDir, "build"), true);
   
    String[] testTokenReplacements = new String[3];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(rootDir.getAbsolutePath()).toString());
    testTokenReplacements[1] = testTokenReplacements[0].charAt(1) == ':' ? "/" : "";
    testTokenReplacements[2] = getVMType();
           
    adjustGenModelForReload(new File(rootDir, "emf/library.genmodel"));
    runAntAndTest(rootDir, rootExpectedDir, antScript, "-DgenJDKLevel=\""+jdkLevel+"\" xsds", testTokenReplacements);
    
    assertTrue(ResourcesPlugin.getWorkspace().getDescription().isAutoBuilding());
  }  
  
  public void testXSDsReload14() throws Exception
  {
    xsdsReloadTest("1.4", "1.4");
  }

  public void testXSDsReload50() throws Exception
  {
    xsdsReloadTest("5.0", "5.0");
  }
  
  public void testXSDsReload1450() throws Exception
  {
    genJDKLevel = GenJDKLevel.JDK50_LITERAL;
    xsdsReloadTest("5.0", "1.4_5.0");
  }
  
  protected void ecoreTest(String jdkLevel, String directorySegment) throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "/library.ecore." + directorySegment);
    File rootExpectedDir = new File(EXPECTED_DIR, "/models/" + directorySegment + "/creation/library.ecore");
    File antScript = new File(rootDir, "build/build.xml");
    
    String[] testTokenReplacements = new String[3];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(rootDir.getAbsolutePath()).toString());
    testTokenReplacements[1] = File.separator;
    testTokenReplacements[2] = getVMType();
           
    runAntAndTest(rootDir, rootExpectedDir, antScript, "-DgenJDKLevel=\""+jdkLevel+"\"", testTokenReplacements);
  }

  public void testEcore14() throws Exception
  {
    ecoreTest("1.4", "1.4");
  }

  public void testEcore50() throws Exception
  {
    ecoreTest("5.0", "5.0");
  }
  
  public void testEcore1450() throws Exception
  {
    ecoreTest("1.4", "1.4_5.0");
  }
  
  protected void ecoreReloadTest(String jdkLevel, String directorySegment) throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "/library.ecore." + directorySegment);
    File rootExpectedDir = new File(EXPECTED_DIR, "/models/" + directorySegment + "/reload/library.ecore");
    File antScript = new File(rootDir, "build/reload.xml");
    
    AntUtil.copyFiles(new File(rootExpectedDir, "model"), new File(rootDir, "model"), true);
    AntUtil.copyFiles(new File(rootExpectedDir, "build"), new File(rootDir, "build"), true);
   
    String[] testTokenReplacements = new String[3];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(rootDir.getAbsolutePath()).toString());
    testTokenReplacements[1] = File.separator;
    testTokenReplacements[2] = getVMType();
           
    adjustGenModelForReload(new File(rootDir, "emf/library.genmodel"));
    runAntAndTest(rootDir, rootExpectedDir, antScript, "-DgenJDKLevel=\""+jdkLevel+"\" ecore", testTokenReplacements);
  }  
  
  public void testEcoreReload14() throws Exception
  {
    ecoreReloadTest("1.4", "1.4");
  }

  public void testEcoreReload50() throws Exception
  {
    ecoreReloadTest("5.0", "5.0");
  }
  
  public void testEcoreReload1450() throws Exception
  {
    genJDKLevel = GenJDKLevel.JDK50_LITERAL;
    ecoreReloadTest("5.0", "1.4_5.0");
  }

  
  protected void javaTest(String jdkLevel, String directorySegment) throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "/library.java." + directorySegment);
    File rootExpectedDir = new File(EXPECTED_DIR, "/models/" + directorySegment + "/creation/library.java");
    File antScript = new File(rootDir, "build/build.xml");
    
    String[] testTokenReplacements = new String[3];
    testTokenReplacements[0] = "/library.java." + directorySegment + "/src";
    testTokenReplacements[1] = File.separator;
    testTokenReplacements[2] = getVMType();
           
    runAntAndTest(rootDir, rootExpectedDir, antScript, "-DgenJDKLevel=\""+jdkLevel+"\"", testTokenReplacements);
  }

  public void testJava14() throws Exception
  {
    javaTest("1.4", "1.4");
  }

  public void testJava50() throws Exception
  {
    javaTest("5.0", "5.0");
  }
  
  public void testJava1450() throws Exception
  {
    javaTest("1.4", "1.4_5.0");
  }
  
  protected void javaReloadTest(String jdkLevel, String directorySegment) throws Exception
  {
    File rootDir = new File(EXAMPLES_COPY_DIR, "/library.java." + directorySegment);
    File rootExpectedDir = new File(EXPECTED_DIR, "/models/" + directorySegment + "/reload/library.java");
    File antScript = new File(rootDir, "build/reload.xml");
    
    AntUtil.copyFiles(new File(rootExpectedDir, "model"), new File(rootDir, "model"), true);
    AntUtil.copyFiles(new File(rootExpectedDir, "build"), new File(rootDir, "build"), true);
   
    String[] testTokenReplacements = new String[3];
    testTokenReplacements[0] = upperCaseDriveLetter(new Path(rootDir.getAbsolutePath()).toString());
    testTokenReplacements[1] = File.separator;
    testTokenReplacements[2] = getVMType();
           
    adjustGenModelForReload(new File(rootDir, "emf/library.genmodel"));
    runAntAndTest(rootDir, rootExpectedDir, antScript, "-DgenJDKLevel=\""+jdkLevel+"\" java", testTokenReplacements);
  }  
  
  public void testJavaReload14() throws Exception
  {
    javaReloadTest("1.4", "1.4");
  }

  public void testJavaReload50() throws Exception
  {
    javaReloadTest("5.0", "5.0");
  }
  
  public void testJavaReload1450() throws Exception
  {
    genJDKLevel = GenJDKLevel.JDK50_LITERAL;
    javaReloadTest("5.0", "1.4_5.0");
  }
  
  
  
  private void runAntAndTest(File rootDir, File rootExpectedDir, File antScript, String antScriptArguments, String[] testTokenReplacements) throws CoreException
  {
    assertTrue(rootDir.getAbsolutePath() + " doesn't exist", rootDir.isDirectory());
    assertTrue(rootExpectedDir.getAbsolutePath() + " doesn't exist", rootExpectedDir.isDirectory());
    assertTrue(antScript.getAbsolutePath() + " doesn't exist", antScript.isFile());
    
    AntUtil.runAnt(antScript, antScriptArguments);   
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
      if (children[i].isFile() /* &&  !".classpath".equals(children[i].getName())*/)
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
    if (UPDATE_EXPECTED_RESULT_ON_FAILURE)
    {
      try
      {
        assertEquals("File: " + file, expectedContent, generatedContent);
      }
      catch (Throwable exception)
      {
        AntUtil.copyFile(generatedFile, expectedFile, true);
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
            // Ignore
          }
        }
      }
    }
  }
  
  protected GenJDKLevel genJDKLevel = null;
  protected void adjustGenModelForReload(GenModel genModel)
  {
    if (genModel != null)
    {
      genModel.setNonNLSMarkers(true);
      if (genJDKLevel != null)
      {
        genModel.setComplianceLevel(genJDKLevel);
      }
    }
  }  
  
  protected String getVMType()
  {
    String compilerCompliance = JavaCore.getOption(JavaCore.COMPILER_COMPLIANCE);
    if (compilerCompliance.equals("1.5"))
    {
      return "J2SE-1.5";
    }
    else
    {
      return "JavaSE-" + compilerCompliance;
    }
  }
}