package org.eclipse.emf.test.tools.codegen;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;

public class GenModelDefaultsTest extends BaseGenModelTest
{
  public GenModelDefaultsTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("GenModelDefaultsTest");
    ts.addTest(new GenModelDefaultsTest("testGetStaticDefaultValue"));
    ts.addTest(new GenModelDefaultsTest("testGetStaticDefaultValueForXSDTypes"));
    return ts;
  }

  protected void assertCorrectStaticDefaultValues(GenPackage genPackage, String className)
  {
    GenModel genModel = genPackage.getGenModel();
    GenClass genClass = (GenClass)genModel.findGenClassifier(genPackage.getEcorePackage().getEClassifier(className));
    prepareModel(genModel, genClass.getQualifiedClassName());

    for (GenFeature genFeature : genClass.getGenFeatures())
    {
      String expected = getExpectedValue(genFeature.getEcoreFeature(), "staticDefaultValue", null);
      String actual = genFeature.getStaticDefaultValue();
      assertEquals("Incorrect static default value for " + genFeature.getName() + ", ", expected, actual);
    }
  }

  public void testGetStaticDefaultValue()
  {
    GenModel genModel = loadModel("Defaults.ecore");
    initializeModel(genModel);
    assertCorrectStaticDefaultValues(genModel.getGenPackages().get(0), "BigClass");
  }

  public void testGetStaticDefaultValueForXSDTypes()
  {
    GenModel genModel = loadModel("XSDDefaults.ecore");
    initializeModel(genModel);
    assertCorrectStaticDefaultValues(genModel.getGenPackages().get(0), "BigElementType");
  }
}