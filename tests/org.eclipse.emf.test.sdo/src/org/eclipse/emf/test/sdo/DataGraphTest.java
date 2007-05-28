/**
 * <copyright>
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
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
 * $Id: DataGraphTest.java,v 1.8 2007/05/28 18:50:33 emerks Exp $
 */
package org.eclipse.emf.test.sdo;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.sdo.EChangeSummary;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.ecore.sdo.impl.DynamicEDataObjectImpl;
import org.eclipse.emf.ecore.sdo.util.DataGraphResourceFactoryImpl;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataObject;


public class DataGraphTest extends TestCase
{
  private EDataGraph eDataGraph;

  private Map<String, Object> xmlOptions;

  private String expectedXML;

  private String modifiedXML;

  private String finalXML;

  /**
   * @param name
   */
  public DataGraphTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("DataGraphTest");
    ts.addTest(new DataGraphTest("testSave"));
    ts.addTest(new DataGraphTest("testLoad"));
    ts.addTest(new DataGraphTest("testChangeSummary"));
    ts.addTest(new DataGraphTest("testMultivalueXPathAccess"));
    return ts;
  }

  @Override
  protected void setUp() throws Exception
  {
    xmlOptions = new HashMap<String, Object>();
    xmlOptions.put(XMLResource.OPTION_DECLARE_XML, Boolean.FALSE);
    xmlOptions.put(XMLResource.OPTION_LINE_WIDTH, new Integer(Integer.MAX_VALUE));
    xmlOptions.put(XMLResource.OPTION_FORMATTED, Boolean.FALSE);

    expectedXML = "<sdo:datagraph xmlns=\"testPackage\" xmlns:sdo=\"commonj.sdo\"><testClass name=\"Root\"><child name=\"Parent\"><child name=\"Child\"/></child></testClass></sdo:datagraph>"
      + System.getProperties().getProperty("line.separator");

    modifiedXML = "<sdo:datagraph xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:sdo=\"commonj.sdo\" xmlns:sdo_1=\"http://www.eclipse.org/emf/2003/SDO\" xmlns:tp=\"testPackage\"><changeSummary xmlns=\"\"><objectChanges key=\"#//@eRootObject\"><value xsi:type=\"sdo_1:EChangeSummarySetting\" featureName=\"name\" dataValue=\"Root\"/></objectChanges></changeSummary><tp:testClass name=\"Root2\"><child name=\"Parent\"><child name=\"Child\"/><reference>//@eRootObject</reference></child></tp:testClass></sdo:datagraph>"
      + System.getProperties().getProperty("line.separator");

    finalXML = "<sdo:datagraph xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:sdo=\"commonj.sdo\" xmlns:sdo_1=\"http://www.eclipse.org/emf/2003/SDO\" xmlns:tp=\"testPackage\"><changeSummary xmlns=\"\"><objectChanges key=\"#//@eRootObject\"><value xsi:type=\"sdo_1:EChangeSummarySetting\" featureName=\"name\" dataValue=\"Root\"/></objectChanges><objectChanges key=\"#//@eRootObject/@child.0\"><value xsi:type=\"sdo_1:EChangeSummarySetting\" featureName=\"child\"><listChanges index=\"0\" referenceValues=\"#//@eChangeSummary/@objectsToAttach.0\"/></value></objectChanges><objectChanges key=\"#//@eChangeSummary/@objectsToAttach.0\"><value xsi:type=\"sdo_1:EChangeSummarySetting\" featureName=\"name\" dataValue=\"Child\"/>" + "</objectChanges><objectsToAttach xsi:type=\"tp:testClass\"/></changeSummary><tp:testClass name=\"Root2\"><child name=\"Parent\"><reference>//@eRootObject</reference></child></tp:testClass></sdo:datagraph>"
      + System.getProperties().getProperty("line.separator");

    // Create a dynamic package
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("testPackage");
    ePackage.setNsURI("testPackage");
    ePackage.setNsPrefix("tp");
    EPackage.Registry.INSTANCE.put("testPackage", ePackage);

    // Set the factory impl to one that creates data objects.
    ePackage.setEFactoryInstance(new DynamicEDataObjectImpl.FactoryImpl());

    // Create a test class that has a name and contains children
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("testClass");
    EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
    eAttribute.setName("name");
    eAttribute.setEType(EcorePackage.eINSTANCE.getEString());
    eClass.getEStructuralFeatures().add(eAttribute);
    EReference eReference = EcoreFactory.eINSTANCE.createEReference();
    eReference.setName("child");
    eReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    eReference.setEType(eClass);
    eReference.setContainment(true);
    eClass.getEStructuralFeatures().add(eReference);
    
    EReference crossReference = EcoreFactory.eINSTANCE.createEReference();
    crossReference.setName("reference");
    crossReference.setEType(eClass);
    crossReference.setResolveProxies(false);
    eClass.getEStructuralFeatures().add(crossReference);
    ExtendedMetaData.INSTANCE.setFeatureKind(crossReference, ExtendedMetaData.ELEMENT_FEATURE);

    // Register the class with the package
    ePackage.getEClassifiers().add(eClass);

    // Create a new data graph with the resource set
    eDataGraph = SDOFactory.eINSTANCE.createEDataGraph();
    eDataGraph.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put(
      Resource.Factory.Registry.DEFAULT_EXTENSION,
      new DataGraphResourceFactoryImpl());

    // Create some objects in the graph
    DataObject rootObject = eDataGraph.createRootObject("testPackage", "testClass");
    rootObject.set("name", "Root");
    DataObject parentObject = rootObject.createDataObject("child", "testPackage", "testClass");
    parentObject.set("name", "Parent");
    DataObject childObject = parentObject.createDataObject("child", "testPackage", "testClass");
    childObject.set("name", "Child");
    parentObject.set("reference", rootObject);
  }

  public void testSave() throws Exception
  {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DataObject rootObject = eDataGraph.getRootObject();
    DataObject childObject = rootObject.getDataObject("child.0").getDataObject("child.0");
    ChangeSummary changeSummary = eDataGraph.getChangeSummary();

    changeSummary.beginLogging();

    // first change
    rootObject.setString("name", "Root2");

    // call save between beginLogging() and endLogging()
    eDataGraph.getDataGraphResource().save(outputStream, xmlOptions);
    assertEquals(modifiedXML, new String(outputStream.toByteArray()));

    // second change
    childObject.delete();

    changeSummary.endLogging();

    // make sure previous call to save didn't damage data graph
    outputStream = new ByteArrayOutputStream();
    eDataGraph.getDataGraphResource().save(outputStream, xmlOptions);
    assertEquals(finalXML, new String(outputStream.toByteArray()));
  }

  public void testLoad() throws Exception
  {
    EDataGraph loadedDataGraph = SDOUtil.loadDataGraph(new ByteArrayInputStream(expectedXML.getBytes()), xmlOptions);

    assertNotNull(loadedDataGraph.getERootObject());
    assertTrue(loadedDataGraph.getERootObject() instanceof DataObject);

    DataObject expectedObject = (DataObject)eDataGraph.getERootObject();
    DataObject loadedObject = (DataObject)loadedDataGraph.getERootObject();
    assertEquals(expectedObject.get("name"), loadedObject.get("name"));
    assertTrue(loadedObject.get("child") instanceof List);
    assertEquals(1, ((List<?>)loadedObject.get("child")).size());
    assertTrue(((List<?>)loadedObject.get("child")).get(0) instanceof DataObject);

    expectedObject = expectedObject.getDataObject("child.0");
    loadedObject = loadedObject.getDataObject("child.0");
    assertEquals(expectedObject.get("name"), loadedObject.get("name"));
    assertTrue(loadedObject.get("child") instanceof List);
    assertEquals(1, ((List<?>)loadedObject.get("child")).size());
    assertTrue(((List<?>)loadedObject.get("child")).get(0) instanceof DataObject);

    expectedObject = expectedObject.getDataObject("child.0");
    loadedObject = loadedObject.getDataObject("child.0");
    assertEquals(expectedObject.get("name"), loadedObject.get("name"));
    assertTrue(loadedObject.get("child") instanceof List);
    assertEquals(0, ((List<?>)loadedObject.get("child")).size());
  }

  // bugzilla 70560,70561,70562 
  public void testChangeSummary()
  {
    DataObject rootObject = eDataGraph.getRootObject();
    assertEquals("Root", rootObject.getString("name"));
    DataObject parentObject = rootObject.getDataObject("child.0");
    DataObject childObject = parentObject.getDataObject("child.0");
    assertEquals("Child", childObject.getString("name"));

    ChangeSummary changeSummary = eDataGraph.getChangeSummary();
    changeSummary.beginLogging();

    // modification
    rootObject.setString("name", "Root2");

    // deletion
    childObject.delete();
    assertNull(childObject.getContainer());

    // addition
    DataObject newChildObject = parentObject.createDataObject("child", "testPackage", "testClass");
    newChildObject.set("name", "NewChild");

    changeSummary.endLogging();

    assertEquals("Root2", rootObject.getString("name"));
    assertNull(((EChangeSummary)changeSummary).getOldContainer(rootObject));
    assertEquals(changeSummary, childObject.getContainer());
    assertEquals(rootObject.getDataObject("child[1]"), ((EChangeSummary)changeSummary).getOldContainer(childObject));

    assertTrue(changeSummary.isCreated(newChildObject));
    assertTrue(changeSummary.isDeleted(childObject));

    assertTrue(changeSummary.getChangedDataObjects().contains(rootObject));
    assertFalse(changeSummary.isCreated(rootObject));
    assertFalse(changeSummary.isDeleted(rootObject));
  }

  // bugzilla 70563
  public void testMultivalueXPathAccess()
  {
    DataObject rootObject = eDataGraph.getRootObject();
    assertNotNull(rootObject);

    DataObject parentObject = rootObject.getDataObject("child[name=Parent]");
    assertNotNull(parentObject);
    assertEquals("Parent", parentObject.getString("name"));

    assertNull(rootObject.getDataObject("child[name=Unknown]"));
  }
}