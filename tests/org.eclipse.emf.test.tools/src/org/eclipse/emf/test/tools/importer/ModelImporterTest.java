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

import java.lang.reflect.Method;
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
    ModelImporter modelImporter = new ModelImporter()
    {
      public String getID()
      {
        return null;
      }
      
      public void makeEcoreFileNamesUnique()
      {
        super.makeEcoreFileNamesUnique();
      }
    };
    
    EPackage ePackage1 = EcoreFactory.eINSTANCE.createEPackage();
    modelImporter.getEPackageInfo(ePackage1).setEcoreFileName("package.ecore");
    EPackage ePackage2 = EcoreFactory.eINSTANCE.createEPackage();
    modelImporter.getEPackageInfo(ePackage2).setEcoreFileName("package.ecore");
    EPackage ePackage3 = EcoreFactory.eINSTANCE.createEPackage();
    modelImporter.getEPackageInfo(ePackage3).setEcoreFileName("package.ecore");
    EPackage ePackage4 = EcoreFactory.eINSTANCE.createEPackage();
    modelImporter.getEPackageInfo(ePackage4).setEcoreFileName("package1.ecore");
    EPackage ePackage5 = EcoreFactory.eINSTANCE.createEPackage();
    modelImporter.getEPackageInfo(ePackage5).setEcoreFileName("package1.ecore");
    EPackage ePackage6 = EcoreFactory.eINSTANCE.createEPackage();
    modelImporter.getEPackageInfo(ePackage6).setEcoreFileName("package2.ecore");
    
    assertEquals("package.ecore", modelImporter.getEPackageInfo(ePackage1).getEcoreFileName());
    assertEquals("package.ecore", modelImporter.getEPackageInfo(ePackage2).getEcoreFileName());
    assertEquals("package.ecore", modelImporter.getEPackageInfo(ePackage3).getEcoreFileName());
    assertEquals("package1.ecore", modelImporter.getEPackageInfo(ePackage4).getEcoreFileName());
    assertEquals("package1.ecore", modelImporter.getEPackageInfo(ePackage5).getEcoreFileName());
    assertEquals("package2.ecore", modelImporter.getEPackageInfo(ePackage6).getEcoreFileName());
    
    Method method = modelImporter.getClass().getMethod("makeEcoreFileNamesUnique", null);
    method.setAccessible(true);
    method.invoke(modelImporter, null);
    
    Set names = new HashSet();
    names.add("package.ecore");
    names.add("package1.ecore");
    names.add("package2.ecore");
    names.add("package3.ecore");
    names.add("package4.ecore");
    names.add("package11.ecore");

    String name = modelImporter.getEPackageInfo(ePackage1).getEcoreFileName();
    assertTrue("package.ecore".equals(name) || "package3.ecore".equals(name)  || "package4.ecore".equals(name));
    names.remove(name);
    
    name = modelImporter.getEPackageInfo(ePackage2).getEcoreFileName();
    assertTrue("package.ecore".equals(name) || "package3.ecore".equals(name)  || "package4.ecore".equals(name));
    names.remove(name);    

    name = modelImporter.getEPackageInfo(ePackage3).getEcoreFileName();
    assertTrue("package.ecore".equals(name) || "package3.ecore".equals(name)  || "package4.ecore".equals(name));
    names.remove(name);
    
    name = modelImporter.getEPackageInfo(ePackage4).getEcoreFileName();
    assertTrue("package1.ecore".equals(name) || "package11.ecore".equals(name));
    names.remove(name);    
    
    name = modelImporter.getEPackageInfo(ePackage5).getEcoreFileName();
    assertTrue("package1.ecore".equals(name) || "package11.ecore".equals(name));
    names.remove(name);

    name = modelImporter.getEPackageInfo(ePackage6).getEcoreFileName();
    assertEquals("package2.ecore", name);
    names.remove(name);
    
    assertTrue(names.isEmpty());
  }
}