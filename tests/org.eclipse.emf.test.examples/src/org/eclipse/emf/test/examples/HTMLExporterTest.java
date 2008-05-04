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
 * $Id: HTMLExporterTest.java,v 1.3 2008/05/04 10:58:46 emerks Exp $
 */
package org.eclipse.emf.test.examples;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.exporter.ModelExporter;
import org.eclipse.emf.exporter.html.HTMLExporter;
import org.eclipse.emf.test.common.TestUtil;

/**
 * @since 2.3.0
 */
public class HTMLExporterTest extends TestCase
{
  /**
   * @param name
   */
  public HTMLExporterTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("HTMLExporterTest");
    ts.addTest(new HTMLExporterTest("testExport"));
    return ts;
  }
  
  public void testExport() throws Exception
  {
    File workingDir = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/temp.folder/htmlExporter").getAbsoluteFile();
    if (workingDir.isDirectory())
    {
      TestUtil.delete(workingDir);
    }
    assertFalse(workingDir.isDirectory());

    File genModelFile = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/htmlExporter/HTMLExportTest.genmodel").getAbsoluteFile();
    assertTrue(genModelFile.getAbsolutePath(), genModelFile.isFile());


    ModelExporter modelExporter = createModelExporter(workingDir, genModelFile);


    Map<String, String> generatedFileNamesByEcoreURI = new HashMap<String, String>(3);
    generatedFileNamesByEcoreURI.put(
      "GenericsGoCrazy.html", 
      URI.createFileURI(new File(genModelFile.getParentFile(), "GenericsGoCrazy.ecore").getAbsolutePath()).toString());
    generatedFileNamesByEcoreURI.put(
      "multipackage_library.html", 
      URI.createFileURI(new File(genModelFile.getParentFile(), "multipackage_library.ecore").getAbsolutePath()).toString());
    generatedFileNamesByEcoreURI.put(
      "library.people.html",
      URI.createFileURI(new File(genModelFile.getParentFile(), "multipackage_library.ecore").getAbsolutePath()).toString());
    
    List<EPackage> ePackages = modelExporter.getEPackages();
    assertEquals(generatedFileNamesByEcoreURI.size(), ePackages.size());
    Set<String> generatedFileNames = new HashSet<String>(generatedFileNamesByEcoreURI.keySet());
    for (EPackage ePackage : ePackages)
    {
      ModelExporter.EPackageExportInfo info = modelExporter.getEPackageExportInfo(ePackage);
      assertTrue(generatedFileNames.remove(info.getArtifactLocation()));
    }
    assertTrue(generatedFileNames.isEmpty());


    modelExporter.export(null);


    String genModelResourceURI = URI.createFileURI(genModelFile.getAbsolutePath()).toString();
    for (Map.Entry<String, String> entry : generatedFileNamesByEcoreURI.entrySet())
    {
      String generateFileName = entry.getKey();
      File generatedFile = new File(workingDir, generateFileName);
      File expectedFile = new File(genModelFile.getParentFile(), generateFileName);

      assertTrue(generatedFile.getPath(), generatedFile.isFile());
      assertTrue(expectedFile.getPath(), expectedFile.isFile());
      
      String contents = TestUtil.readFile(generatedFile, false);
      String expectedContents = TestUtil.readFile(expectedFile, false);
      expectedContents = expectedContents.replace("@ecoreuri@", entry.getValue());
      expectedContents = expectedContents.replace("@genmodeluri@", genModelResourceURI);
      assertEquals(expectedContents, contents);
    }
  }
  
  protected ModelExporter createModelExporter(File workingDir, File genModelFile) throws Exception
  {
    EcorePackage.eINSTANCE.eClass();
    GenModelPackage.eINSTANCE.eClass();
    
    Resource.Factory resourceFactory = new EcoreResourceFactoryImpl();
    Map<String, Object> extensionRegistry = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    extensionRegistry.put("ecore", resourceFactory);
    extensionRegistry.put("genmodel", resourceFactory);
    
    ModelExporter modelExporter = new HTMLExporter();
    modelExporter.setDirectoryURI(URI.createFileURI(workingDir.getPath() + "/"));
    modelExporter.loadGenModel(URI.createFileURI(genModelFile.getPath()));
    
    return modelExporter;
  }
}
