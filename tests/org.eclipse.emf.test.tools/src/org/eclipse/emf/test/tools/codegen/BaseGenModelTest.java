package org.eclipse.emf.test.tools.codegen;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.tools.AllSuites;

import junit.framework.TestCase;

public class BaseGenModelTest extends TestCase
{
  public BaseGenModelTest(String name)
  {
    super(name);
  }

  // For now, I've only used single-package models, but this could be expanded to handle multi-package scenarios.
  //
  protected GenModel loadModel(String filename)
  {
    ResourceSet rs = new ResourceSetImpl();
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

    URI baseURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID)).appendSegment("data").appendSegment("");
    URI uri = URI.createFileURI(filename).resolve(baseURI);
    Resource resource = rs.getResource(uri, true);
    assertEquals(1, resource.getContents().size());

    EPackage ePackage = (EPackage)resource.getContents().get(0);
    GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
    genModel.initialize(Collections.singletonList(ePackage));
    genModel.setComplianceLevel(GenJDKLevel.JDK50_LITERAL);
    assertEquals(1, genModel.getGenPackages().size());
    genModel.getGenPackages().get(0).prepareCache();

    return genModel;
  }

  protected void initializeModel(GenModel genModel)
  {
    for (Iterator<EObject> i = genModel.eAllContents(); i.hasNext(); )
    {
      EObject object = i.next();
      if (object instanceof GenBase)
      {
        GenBase genBase = (GenBase)object;
        EModelElement eModelElement = genBase.getEcoreModelElement();
        if (eModelElement != null)
        {
          EAnnotation eAnnotation = eModelElement.getEAnnotation(GenModelPackage.eNS_URI);
          if (eAnnotation != null)
          {
            for (Map.Entry<String, String> entry : eAnnotation.getDetails())
            {
              EStructuralFeature feature = genBase.eClass().getEStructuralFeature(entry.getKey());
              if (feature instanceof EAttribute)
              {
                EAttribute attribute = (EAttribute)feature;
                genBase.eSet(attribute, EcoreUtil.createFromString(attribute.getEAttributeType(), entry.getValue()));
              }
            }
          }
        }
      }
    }
  }

  protected void prepareModel(GenModel genModel, String qualifiedClassName)
  {
    int i = qualifiedClassName.lastIndexOf('.');
    String packageName = i != -1 ? qualifiedClassName.substring(0, i) : null;
    String className = i != -1 ? qualifiedClassName.substring(i + 1) : qualifiedClassName;

    ImportManager importManager = new ImportManager(packageName);
    importManager.addMasterImport(packageName, className);
    genModel.setImportManager(importManager);
  }

  protected static final String GEN_MODEL_TEST_URI = "http://www.eclipse.org/emf/GenModelTest";

  protected String getExpectedValue(EModelElement modelElement, String name, String detail)
  {
    EAnnotation annotation = modelElement.getEAnnotation(GEN_MODEL_TEST_URI);
    if (annotation != null)
    {
      if (detail != null)
      {
        String key = name + detail;
        if (annotation.getDetails().containsKey(key))
        {
          return annotation.getDetails().get(key);
        }
      }
      return annotation.getDetails().get(name);
    }
    return null;
  }
}
