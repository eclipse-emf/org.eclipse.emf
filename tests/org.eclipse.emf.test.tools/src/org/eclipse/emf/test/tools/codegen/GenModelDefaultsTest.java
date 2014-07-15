package org.eclipse.emf.test.tools.codegen;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.junit.Test;

public class GenModelDefaultsTest extends BaseGenModelTest
{
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

  @Test
  public void testGetStaticDefaultValue()
  {
    GenModel genModel = loadModel("Defaults.ecore");
    initializeModel(genModel);
    assertCorrectStaticDefaultValues(genModel.getGenPackages().get(0), "BigClass");
  }

  @Test
  public void testGetStaticDefaultValueForXSDTypes()
  {
    GenModel genModel = loadModel("XSDDefaults.ecore");
    initializeModel(genModel);
    assertCorrectStaticDefaultValues(genModel.getGenPackages().get(0), "BigElementType");
  }
}