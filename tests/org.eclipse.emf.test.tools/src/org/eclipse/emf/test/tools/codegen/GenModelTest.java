/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * ImporterUtilTest.java,v 1.1 2005/05/16 14:16:30 marcelop Exp
 */
package org.eclipse.emf.test.tools.codegen;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;

public class GenModelTest extends TestCase
{
  protected  GenModel genModel;
  
  /**
   * @param name
   */
  public GenModelTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("GenModelTest");
    ts.addTest(new GenModelTest("testGetModelQualifiedPackageNames"));
    ts.addTest(new GenModelTest("testGetEditQualifiedPackageNames"));
    ts.addTest(new GenModelTest("testGetEditorQualifiedPackageNames"));
    return ts;
  }
  
  protected void setUp() throws Exception
  {
    List ePackages = new ArrayList();
    ePackages.add(createEPackage(0, true, false));
    ePackages.add(createEPackage(1, false, false));
    ePackages.add(createEPackage(2, true, true));
    ePackages.add(createEPackage(3, false, true));
    
    genModel = GenModelFactory.eINSTANCE.createGenModel();
    genModel.setModelDirectory("project1/f1");
    genModel.initialize(ePackages);
    genModel.setModelPluginClass("modelPluginClass.PC");
    genModel.setEditPluginClass("editPluginClass.PC");
    genModel.setEditorPluginClass("editorPluginClass.PC");
    genModel.setTestSuiteClass("testSuiteClass.TC");
    
    {
      GenPackage genPackage = (GenPackage)genModel.getGenPackages().get(0);
      assertEquals("ePackage0", genPackage.getEcorePackage().getName());
      genPackage.setPrefix("P0");
      genPackage.setBasePackage("org.example0");
      genPackage.setInterfacePackageSuffix("pub");
      genPackage.setClassPackageSuffix("internal");
      genPackage.setUtilityPackageSuffix("utility.pub");
      genPackage.setProviderPackageSuffix("edit");
      genPackage.setPresentationPackageSuffix("ui");
      genPackage.setTestsPackageSuffix("junit");
    }
    {
      GenPackage genPackage = (GenPackage)genModel.getGenPackages().get(1);
      assertEquals("ePackage1", genPackage.getEcorePackage().getName());
      genPackage.setPrefix("P1");
      genPackage.setBasePackage("org.example1");
      genPackage.setInterfacePackageSuffix("pub1");
      genPackage.setClassPackageSuffix("internal1");
      genPackage.setUtilityPackageSuffix("utility1.pub1");
      genPackage.setProviderPackageSuffix("edit1");
      genPackage.setPresentationPackageSuffix("ui1");
      genPackage.setTestsPackageSuffix("junit1");
    }
    {
      GenPackage genPackage = (GenPackage)genModel.getGenPackages().get(2);
      assertEquals("ePackage2", genPackage.getEcorePackage().getName());
      genPackage.setPrefix("P2");
      genPackage.setBasePackage("org.example2");
      GenPackage subPackage = (GenPackage)genPackage.getNestedGenPackages().get(0);
      assertEquals("subEPackage2", subPackage.getEcorePackage().getName());
      subPackage.setPrefix("S2");
      subPackage.setBasePackage("org.exampleS2");
    }
    {
      GenPackage genPackage = (GenPackage)genModel.getGenPackages().get(3);
      assertEquals("ePackage3", genPackage.getEcorePackage().getName());
      genPackage.setPrefix("P3");
      genPackage.setInterfacePackageSuffix("pub3");
      GenPackage subPackage = (GenPackage)genPackage.getNestedGenPackages().get(0);
      assertEquals("subEPackage3", subPackage.getEcorePackage().getName());
      subPackage.setPrefix("S3");
    }
  }
  
  protected EPackage createEPackage(int id, boolean hasClass, boolean subPackage)
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("ePackage" + id);
    ePackage.setNsPrefix("NSPrefix_" + ePackage.getName());
    ePackage.setNsURI("NSURI_" + ePackage.getName());
    
    if (hasClass)
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("Class1");
      ePackage.getEClassifiers().add(eClass);
    }

    if (subPackage)
    {
      EPackage subEPackage = EcoreFactory.eINSTANCE.createEPackage();
      subEPackage.setName("subEPackage" + id);
      subEPackage.setNsPrefix("NSPrefix_" + subEPackage.getName());
      subEPackage.setNsURI("NSURI_" + subEPackage.getName());
      ePackage.getESubpackages().add(subEPackage);

      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("Class2");
      subEPackage.getEClassifiers().add(eClass);
    }
    
    return ePackage;
  }
  
  /*
   * Bugzilla 98140
   */
  public void testGetModelQualifiedPackageNames() throws Exception
  {
    List packageNames = genModel.getModelQualifiedPackageNames();
    modelQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());
    
    genModel.setTestsDirectory(genModel.getModelDirectory());
    packageNames = genModel.getModelQualifiedPackageNames();
    modelQualifiedPackageNamesTest(packageNames);
    testsQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());    
  }

  /*
   * Bugzilla 98140
   */
  public void testGetEditQualifiedPackageNames() throws Exception
  {
    List packageNames = genModel.getEditQualifiedPackageNames();
    editQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());
    
    genModel.setEditDirectory(genModel.getModelDirectory());
    packageNames = genModel.getEditQualifiedPackageNames();
    modelQualifiedPackageNamesTest(packageNames);
    editQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty()); 
    //
    packageNames = genModel.getModelQualifiedPackageNames();
    modelQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());    

    genModel.setTestsDirectory(genModel.getModelDirectory());
    packageNames = genModel.getEditQualifiedPackageNames();
    modelQualifiedPackageNamesTest(packageNames);
    editQualifiedPackageNamesTest(packageNames);
    testsQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());
    //
    packageNames = genModel.getModelQualifiedPackageNames();
    modelQualifiedPackageNamesTest(packageNames);
    testsQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());    
  }
  
  /*
   * Bugzilla 98140
   */
  public void testGetEditorQualifiedPackageNames() throws Exception
  {
    List packageNames = genModel.getEditorQualifiedPackageNames();
    editorQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());
    
    genModel.setEditorDirectory(genModel.getEditDirectory());
    packageNames = genModel.getEditorQualifiedPackageNames();
    editorQualifiedPackageNamesTest(packageNames);
    editQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty()); 
    //
    packageNames = genModel.getModelQualifiedPackageNames();
    modelQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());
    //
    packageNames = genModel.getEditQualifiedPackageNames();
    editQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());    
    
    genModel.setEditDirectory(genModel.getModelDirectory());
    genModel.setEditorDirectory(genModel.getModelDirectory());
    packageNames = genModel.getEditorQualifiedPackageNames();
    editorQualifiedPackageNamesTest(packageNames);
    modelQualifiedPackageNamesTest(packageNames);
    editQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty()); 
    //
    packageNames = genModel.getModelQualifiedPackageNames();
    modelQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());
    //
    packageNames = genModel.getEditQualifiedPackageNames();
    modelQualifiedPackageNamesTest(packageNames);
    editQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());    

    genModel.setTestsDirectory(genModel.getModelDirectory());
    packageNames = genModel.getEditorQualifiedPackageNames();
    editorQualifiedPackageNamesTest(packageNames);
    modelQualifiedPackageNamesTest(packageNames);
    editQualifiedPackageNamesTest(packageNames);
    testsQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());
    //
    packageNames = genModel.getModelQualifiedPackageNames();
    modelQualifiedPackageNamesTest(packageNames);
    testsQualifiedPackageNamesTest(packageNames);
    assertTrue(packageNames.isEmpty());    
  }

  protected void modelQualifiedPackageNamesTest(List packageNames)
  {
    assertTrue(packageNames.remove("org.example0.ePackage0.pub"));
    assertTrue(packageNames.remove("org.example0.ePackage0.internal"));
    assertTrue(packageNames.remove("org.example0.ePackage0.utility.pub"));
    assertTrue(packageNames.remove("org.example2.ePackage2"));
    assertTrue(packageNames.remove("org.example2.ePackage2.impl"));
    assertTrue(packageNames.remove("org.example2.ePackage2.util"));
    assertTrue(packageNames.remove("org.exampleS2.subEPackage2"));
    assertTrue(packageNames.remove("org.exampleS2.subEPackage2.impl"));
    assertTrue(packageNames.remove("org.exampleS2.subEPackage2.util"));
    assertTrue(packageNames.remove("ePackage3.subEPackage3"));
    assertTrue(packageNames.remove("ePackage3.subEPackage3.impl"));
    assertTrue(packageNames.remove("ePackage3.subEPackage3.util"));    
    
    assertTrue(packageNames.remove("modelPluginClass"));
  }

  protected void editQualifiedPackageNamesTest(List packageNames)
  {
    assertTrue(packageNames.remove("org.example0.ePackage0.edit"));
    assertTrue(packageNames.remove("org.example2.ePackage2.provider"));
    assertTrue(packageNames.remove("org.exampleS2.subEPackage2.provider"));
    assertTrue(packageNames.remove("ePackage3.subEPackage3.provider"));
    
    if (!genModel.getModelDirectory().equals(genModel.getEditDirectory()))
    {
      assertTrue(packageNames.remove("editPluginClass"));
    }
  }

  protected void editorQualifiedPackageNamesTest(List packageNames)
  {
    assertTrue(packageNames.remove("org.example0.ePackage0.ui"));
    assertTrue(packageNames.remove("org.example2.ePackage2.presentation"));
    assertTrue(packageNames.remove("org.exampleS2.subEPackage2.presentation"));
    assertTrue(packageNames.remove("ePackage3.subEPackage3.presentation"));

    if (!genModel.getModelDirectory().equals(genModel.getEditorDirectory()) &&
        !genModel.getEditDirectory().equals(genModel.getEditorDirectory()))
    {
      assertTrue(packageNames.remove("editorPluginClass"));
    }
  }

  protected void testsQualifiedPackageNamesTest(List packageNames)
  {
    assertTrue(packageNames.remove("org.example0.ePackage0.junit"));
    assertTrue(packageNames.remove("org.example2.ePackage2.tests"));
    assertTrue(packageNames.remove("org.exampleS2.subEPackage2.tests"));
    assertTrue(packageNames.remove("ePackage3.subEPackage3.tests"));

    assertTrue(packageNames.remove("testSuiteClass"));    
  }
}