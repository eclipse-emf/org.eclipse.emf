package org.eclipse.emf.test.core.change;


import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;


public class ChangeDescriptionTest
extends TestCase
{
  private ResourceSet resourceSet;
  private EAnnotation eAnnotation;
  private EClass eClass0;
  
  public ChangeDescriptionTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite();
    ts.addTest(new ChangeDescriptionTest("testChangeRecorder"));
    return ts;
  }
  
  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    EPackage.Registry.INSTANCE.put("http://www.eclipse.org/emf/2002/Ecore", EcorePackage.eINSTANCE);

    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

    resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(URI.createURI("test.ecore"));

    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("testEPackage");

    resource.getContents().add(ePackage);

    eClass0 = EcoreFactory.eINSTANCE.createEClass();
    eClass0.setName("testEClass0");

    eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
    eAnnotation.setSource("testEAnnotation");
    eAnnotation.getContents().add(eClass0);

    ePackage.getEAnnotations().add(eAnnotation);
  }

  public void testChangeRecorder()
  {
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);

    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    eClass1.setName("testEClass1");
    eAnnotation.getContents().set(0, eClass1);
    
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    EMap objectChanges = changeDescription.getObjectChanges();
    assertEquals(1, objectChanges.size());
    assertTrue(objectChanges.containsKey(eAnnotation));
    assertEquals(1, objectChanges.values().size());
    assertTrue(objectChanges.values().iterator().next() instanceof EList);
    
    EList eList = (EList)objectChanges.values().iterator().next();
    assertEquals(1, eList.size());
    assertTrue(eList.iterator().next() instanceof FeatureChange);
    
    FeatureChange featureChange = (FeatureChange)eList.iterator().next();
    assertEquals(EcorePackage.eINSTANCE.getEAnnotation_Contents(), featureChange.getFeature());
    assertEquals(2, featureChange.getListChanges().size());
    
    int checker = 0;
    for (Iterator i = featureChange.getListChanges().iterator(); i.hasNext();)
    {
      ListChange listChange = (ListChange)i.next();
      switch (listChange.getKind().getValue())
      {
        case ChangeKind.ADD:
          assertEquals(0, listChange.getIndex());
        	assertEquals(1, listChange.getValues().size());
        	//assertEquals(eClass1, listChange.getValues().get(0));
        	checker += 1;
          break;
        
        case ChangeKind.REMOVE:
        	assertEquals(1, listChange.getIndex());
        	assertEquals(0, listChange.getReferenceValues().size());
          checker += 4;
          break;
      }
    }
    assertEquals(5, checker);
  }
}