/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * ImporterUtilTest.java,v 1.1 2005/05/16 14:16:30 marcelop Exp
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
      public String getID()
      {
        return null;
      }
      
      public void makeEcoreFileNamesUnique()
      {
        super.makeEcoreFileNamesUnique();
      }      
    }
    
    MyModelImporter myModelImporter = new MyModelImporter();
    
    EPackage ePackage1 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageInfo ePackageInfo1 = myModelImporter.getEPackageInfo(ePackage1);
    ePackageInfo1.setEcoreFileName("package.ecore");
    ePackageInfo1.setBasePackage("basePackage");
    
    EPackage ePackage2 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageInfo ePackageInfo2 = myModelImporter.getEPackageInfo(ePackage2);
    ePackageInfo2.setEcoreFileName("package.ecore");
    ePackageInfo2.setBasePackage("basePackage");

    EPackage ePackage3 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageInfo ePackageInfo3 = myModelImporter.getEPackageInfo(ePackage3);
    ePackageInfo3.setEcoreFileName("package.ecore");
    ePackageInfo3.setBasePackage("basePackage3");

    EPackage ePackage4 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageInfo ePackageInfo4 = myModelImporter.getEPackageInfo(ePackage4);
    ePackageInfo4.setEcoreFileName("package1.ecore");
    ePackageInfo4.setBasePackage("basePackage");
        
    EPackage ePackage5 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageInfo ePackageInfo5 = myModelImporter.getEPackageInfo(ePackage5);
    ePackageInfo5.setEcoreFileName("package1.ecore");
    ePackageInfo5.setBasePackage("basePackage5");

    EPackage ePackage6 = EcoreFactory.eINSTANCE.createEPackage();
    ModelImporter.EPackageInfo ePackageInfo6 = myModelImporter.getEPackageInfo(ePackage6);
    ePackageInfo6.setEcoreFileName("package2.ecore");
    ePackageInfo6.setBasePackage("basePackage6");
    
    myModelImporter.makeEcoreFileNamesUnique();
    
    Set names = new HashSet();
    names.add("package.ecore");
    names.add("package1.ecore");
    names.add("package2.ecore");
    names.add("package3.ecore");
    names.add("package4.ecore");
    names.add("package11.ecore");

    String name = myModelImporter.getEPackageInfo(ePackage1).getEcoreFileName();
    assertTrue("Name: " + name, "package.ecore".equals(name) || "package3.ecore".equals(name)  || "package4.ecore".equals(name));
    names.remove(name);
    
    name = myModelImporter.getEPackageInfo(ePackage2).getEcoreFileName();
    assertTrue("Name: " + name, "package.ecore".equals(name) || "package3.ecore".equals(name)  || "package4.ecore".equals(name));
    names.remove(name);    

    name = myModelImporter.getEPackageInfo(ePackage3).getEcoreFileName();
    assertTrue("Name: " + name, "package.ecore".equals(name) || "package3.ecore".equals(name)  || "package4.ecore".equals(name));
    names.remove(name);
    
    name = myModelImporter.getEPackageInfo(ePackage4).getEcoreFileName();
    assertTrue("Name: " + name, "package1.ecore".equals(name) || "package11.ecore".equals(name));
    names.remove(name);    
    
    name = myModelImporter.getEPackageInfo(ePackage5).getEcoreFileName();
    assertTrue("Name: " + name, "package1.ecore".equals(name) || "package11.ecore".equals(name));
    names.remove(name);

    name = myModelImporter.getEPackageInfo(ePackage6).getEcoreFileName();
    assertEquals("Name: " + name, "package2.ecore", name);
    names.remove(name);
    
    assertTrue(names.isEmpty());
  }
}