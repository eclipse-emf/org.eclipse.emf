/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools.importer;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.importer.ModelImporter;

public class ModelImporterTest extends TestCase
{
  /**
   * @param name
   */
  public ModelImporterTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ModelImporterTest");
    ts.addTest(new ModelImporterTest("testMakeEcoreFileNamesUnique"));
    return ts;
  }
  
  /*
   * Bugzilla 96608
   */
  public void testMakeEcoreFileNamesUnique() throws Exception
  {
    class MyModelImporter extends ModelImporter
    {
      @Override
      public String getID()
      {
        return null;
      }
    }
    
    MyModelImporter myModelImporter = new MyModelImporter();
    
    EPackage ePackage1 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageImportInfo ePackageInfo1 = myModelImporter.getEPackageImportInfo(ePackage1);
    ePackageInfo1.setEcoreFileName("package.ecore");
    ePackageInfo1.setBasePackage("basePackage");
    
    EPackage ePackage2 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageImportInfo ePackageInfo2 = myModelImporter.getEPackageImportInfo(ePackage2);
    ePackageInfo2.setEcoreFileName("package.ecore");
    ePackageInfo2.setBasePackage("basePackage");

    EPackage ePackage3 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageImportInfo ePackageInfo3 = myModelImporter.getEPackageImportInfo(ePackage3);
    ePackageInfo3.setEcoreFileName("package.ecore");
    ePackageInfo3.setBasePackage("basePackage3");

    EPackage ePackage4 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageImportInfo ePackageInfo4 = myModelImporter.getEPackageImportInfo(ePackage4);
    ePackageInfo4.setEcoreFileName("package1.ecore");
    ePackageInfo4.setBasePackage("basePackage");
        
    EPackage ePackage5 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageImportInfo ePackageInfo5 = myModelImporter.getEPackageImportInfo(ePackage5);
    ePackageInfo5.setEcoreFileName("package1.ecore");
    ePackageInfo5.setBasePackage("basePackage5");

    EPackage ePackage6 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageImportInfo ePackageInfo6 = myModelImporter.getEPackageImportInfo(ePackage6);
    ePackageInfo6.setEcoreFileName("package2.ecore");
    ePackageInfo6.setBasePackage("basePackage6");
    
    myModelImporter.makeEPackageConvertDataUnique();
    
    Set<String> names = new HashSet<String>();
    names.add("package.ecore");
    names.add("package1.ecore");
    names.add("package2.ecore");
    names.add("package3.ecore");
    names.add("package4.ecore");
    names.add("package11.ecore");

    String name = myModelImporter.getEPackageImportInfo(ePackage1).getEcoreFileName();
    assertTrue("Name: " + name, "package.ecore".equals(name) || "package3.ecore".equals(name)  || "package4.ecore".equals(name));
    names.remove(name);
    
    name = myModelImporter.getEPackageImportInfo(ePackage2).getEcoreFileName();
    assertTrue("Name: " + name, "package.ecore".equals(name) || "package3.ecore".equals(name)  || "package4.ecore".equals(name));
    names.remove(name);    

    name = myModelImporter.getEPackageImportInfo(ePackage3).getEcoreFileName();
    assertTrue("Name: " + name, "package.ecore".equals(name) || "package3.ecore".equals(name)  || "package4.ecore".equals(name));
    names.remove(name);
    
    name = myModelImporter.getEPackageImportInfo(ePackage4).getEcoreFileName();
    assertTrue("Name: " + name, "package1.ecore".equals(name) || "package11.ecore".equals(name));
    names.remove(name);    
    
    name = myModelImporter.getEPackageImportInfo(ePackage5).getEcoreFileName();
    assertTrue("Name: " + name, "package1.ecore".equals(name) || "package11.ecore".equals(name));
    names.remove(name);

    name = myModelImporter.getEPackageImportInfo(ePackage6).getEcoreFileName();
    assertEquals("Name: " + name, "package2.ecore", name);
    names.remove(name);
    
    assertTrue(names.isEmpty());
  }
}
