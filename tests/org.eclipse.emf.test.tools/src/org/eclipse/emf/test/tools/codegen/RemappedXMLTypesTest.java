package org.eclipse.emf.test.tools.codegen;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
public class RemappedXMLTypesTest extends BaseGenModelTest
{
  public RemappedXMLTypesTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("RemappedXMLTypesTest");
    ts.addTest(new RemappedXMLTypesTest("testNewXMLTypes"));
    ts.addTest(new RemappedXMLTypesTest("testOldXMLTypes"));
    return ts;
  }

  protected void assertCorrectTypes(GenPackage genPackage, String className, boolean old)
  {
    GenModel genModel = genPackage.getGenModel();
    GenClass genClass = (GenClass)genModel.findGenClassifier(genPackage.getEcorePackage().getEClassifier(className));
    prepareModel(genModel, genClass.getQualifiedClassName());

    for (GenFeature genFeature : genClass.getGenFeatures())
    {
      String expectedType = getExpectedValue(genFeature.getEcoreFeature(), "type", old ? "Old" : "New");
      String actualType = genFeature.getType(genClass);
      assertEquals("Incorrect type for " + genFeature.getName(), expectedType, actualType);

      boolean expectedIsObject = genFeature.getEcoreFeature().isMany() ? "org.eclipse.emf.common.util.EList<java.lang.Object>".equals(expectedType) : "java.lang.Object".equals(expectedType);
      boolean actualIsObject = genFeature.getTypeGenDataType().isObjectType();
      assertEquals("Incorrect isObject value for " + genFeature.getName(), expectedIsObject, actualIsObject);
    }
  }

  public void testNewXMLTypes()
  {
    GenModel genModel = loadModel("XMLTypes.ecore");
    initializeModel(genModel);
    assertCorrectTypes(genModel.getGenPackages().get(0), "Item", false);
  }

  public void testOldXMLTypes()
  {
    GenModel genModel = loadModel("XMLTypes.ecore");
    initializeModel(genModel);
    genModel.setRuntimeVersion(GenRuntimeVersion.EMF22);
    assertCorrectTypes(genModel.getGenPackages().get(0), "Item", true);
  }
}
