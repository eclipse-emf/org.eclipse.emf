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
 * $Id: ChangeSummaryTest.java,v 1.12 2008/05/04 10:58:55 emerks Exp $
 */
package org.eclipse.emf.test.sdo;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.models.ipo.IPOFactory;
import org.eclipse.emf.test.models.ipo.PurchaseOrderType;
import org.eclipse.emf.test.models.ipo.USAddress;
import org.eclipse.emf.test.models.personal.impl.PersonalPackageImpl;
import org.eclipse.emf.test.models.personal.mixed.impl.MixedPackageImpl;
import org.eclipse.emf.test.models.personal.mixed.util.MixedResourceFactoryImpl;
import org.eclipse.emf.test.models.personal.util.PersonalResourceFactoryImpl;

import org.eclipse.emf.ecore.sdo.EChangeSummary;
import org.eclipse.emf.ecore.sdo.EChangeSummarySetting;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.EDataObject;
import org.eclipse.emf.ecore.sdo.EDataObjectAnyType;
import org.eclipse.emf.ecore.sdo.EProperty;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.ecore.sdo.impl.DynamicEDataObjectImpl;
import org.eclipse.emf.ecore.sdo.impl.EDataGraphImpl;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Sequence;


public class ChangeSummaryTest extends TestCase
{

  private static final String DATA = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/";

  // private static final String DATA_URI = "file:///" + DATA;

  static final boolean DEBUG = false;

  ResourceSet resourceSet;

  // Temporary variables to test change summary 
  ChangeSummary changeSummary;

  public ChangeSummaryTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("ChangeSummaryTest");
    testSuite.addTest(new ChangeSummaryTest("testEndLoggingAfterSerialization"));
    testSuite.addTest(new ChangeSummaryTest("testIsDeleted"));
    testSuite.addTest(new ChangeSummaryTest("testPersonalMixed"));
    testSuite.addTest(new ChangeSummaryTest("testPersonal"));
    testSuite.addTest(new ChangeSummaryTest("testOldContainer"));
    testSuite.addTest(new ChangeSummaryTest("testOpenContentOldContainer"));
    testSuite.addTest(new ChangeSummaryTest("testGetOldValues"));
    return testSuite;
  }

  protected ExtendedMetaData registerPersonal()
  {
    PersonalPackageImpl.eINSTANCE.getName();
    resourceSet = SDOUtil.createResourceSet();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new PersonalResourceFactoryImpl());
    return new BasicExtendedMetaData(resourceSet.getPackageRegistry());
  }

  protected ExtendedMetaData registerPersonalMixed()
  {
    MixedPackageImpl.eINSTANCE.getName();
    resourceSet = SDOUtil.createResourceSet();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new MixedResourceFactoryImpl());
    return new BasicExtendedMetaData(resourceSet.getPackageRegistry());
  }

  public void testPersonal() throws Exception
  {
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, registerPersonal());
    FileInputStream fileInputStream = new FileInputStream(DATA + "personal.xml");
    byte[] dataGraphBytes = new byte [fileInputStream.available()];
    fileInputStream.read(dataGraphBytes);
    ByteArrayInputStream dgByteInputStream = new ByteArrayInputStream(dataGraphBytes);

    DataGraph dataGraph = SDOUtil.loadDataGraph(dgByteInputStream, options);
    ChangeSummary changeSummary = dataGraph.getChangeSummary();

    // Record original output stream
    ByteArrayOutputStream originalstream = new ByteArrayOutputStream(2064);
    ((EDataGraph)dataGraph).getRootResource().save(originalstream, options);

    // get some data
    DataObject personnel = (DataObject)dataGraph.getRootObject().get("personnel");
    @SuppressWarnings("unchecked") List<DataObject> personList = (List<DataObject>)personnel.get("person");
    DataObject bigBoss = personList.get(0);
    DataObject oneWorker = personList.get(1);
    DataObject twoWorker = personList.get(2);
    DataObject fiveWorker = personList.get(5);
    DataObject bigBossLink = (DataObject)bigBoss.get("link");
    DataObject twoWorkerLink = (DataObject)twoWorker.get("link");
    @SuppressWarnings("unchecked") List<String> emailList = fiveWorker.getList("email");
    List<?> subordinates = bigBossLink.getList("subordinates");

    // copy data that will be changed for later comparison
    DataObject oneWorkerCopy = (DataObject)EcoreUtil.copy((EObject)oneWorker);
    DataObject twoWorkerLinkCopy = (DataObject)EcoreUtil.copy((EObject)twoWorkerLink);

    // record changes
    changeSummary.beginLogging();
    twoWorkerLink.delete();
    oneWorker.delete();
    List<Object> newList = new ArrayList<Object>();
    newList.addAll(subordinates);
    newList.remove(0);
    bigBossLink.setList("subordinates", newList);
    emailList.add("five_extra@foo.com");
    changeSummary.endLogging();

    List<EObject> objectsToAttach = ((EChangeSummary)changeSummary).getObjectsToAttach();
    // make a copy so we can modify it
    List<EObject> objectsToAttachCopy = new ArrayList<EObject>(((EChangeSummary)changeSummary).getObjectsToAttach());
    
    @SuppressWarnings("unchecked")
    List<Object> changedDOList = new ArrayList<Object>(changeSummary.getChangedDataObjects());

    // verify that we have correct values for objects to attach
    DataObject personType = getDO(objectsToAttachCopy, "PersonType");
    verifyType(personType, oneWorkerCopy, objectsToAttachCopy, changeSummary);
    DataObject linkType = getDO(objectsToAttachCopy, "LinkType");
    verifyType(linkType, twoWorkerLinkCopy, objectsToAttachCopy, changeSummary);

    // remove all objects to attach since we've verified that those contain correct changes
    changedDOList.removeAll(objectsToAttach);

    // verify the values for the remaining changed objects
    for (int i = 0; i < changedDOList.size(); i++)
    {
      DataObject o = (DataObject)changedDOList.get(i);
      List<?> settings = changeSummary.getOldValues(o);
      String featureName = o.getType().getName();
      if (featureName.equals("PersonType"))
      {
        String id = o.getString("id");
        if (id.equals("two.worker"))
        {
          for (int k = 0; k < settings.size(); k++)
          {
            FeatureChange change = (FeatureChange)settings.get(k);
            assertEquals(true, objectsToAttach.contains(change.getReferenceValue()));
          }
        }
        else if (id.equals("five.worker"))
        {
          for (int k = 0; k < settings.size(); k++)
          {
            FeatureChange change = (FeatureChange)settings.get(k);
            ListChange lchange = change.getListChanges().get(0);
            assertEquals(ChangeKind.REMOVE, lchange.getKind().getValue());
            assertEquals(1, lchange.getIndex());
            assertEquals("email", lchange.getFeature().getName());
          }
        }
        else
        {
          assertNotSame("two.worker or five.worker", id);
        }
      }
      else if (featureName.equals("Personneltype"))
      {
        for (int k = 0; k < settings.size(); k++)
        {
          FeatureChange change = (FeatureChange)settings.get(k);
          ListChange lchange = change.getListChanges().get(0);
          assertEquals(ChangeKind.ADD, lchange.getKind().getValue());
          assertEquals(1, lchange.getIndex());
          assertEquals(0, lchange.getMoveToIndex());
          assertEquals(true, EcoreUtil.equals((EObject)personType, lchange.getReferenceValues().get(0)));
        }
      }
      else if (featureName.equals("LinkType"))
      {
        settings = changeSummary.getOldValues(o);
        for (int k = 0; k < settings.size(); k++)
        {
          FeatureChange change = (FeatureChange)settings.get(k);
          assertEquals("subordinates", change.getFeatureName());
          assertEquals("one.worker two.worker three.worker four.worker five.worker", change.getDataValue());
        }
      }
    }

    //  compare serialization format for root resource
    ByteArrayOutputStream changedstream = new ByteArrayOutputStream(2064);
    ByteArrayOutputStream changedstream2 = new ByteArrayOutputStream(2064);
    //((EDataGraph)dataGraph).getEChangeSummary().eResource().save(System.out, options);

    // record root resource following the changes
    ((EDataGraph)dataGraph).getRootResource().save(changedstream, options);

    // revert to original state and record root resource. This should be equal to the 
    // original file
    ((EChangeSummary)changeSummary).applyAndReverse();
    ByteArrayOutputStream originalstream2 = new ByteArrayOutputStream(2064);
    ((EDataGraph)dataGraph).getRootResource().save(originalstream2, options);
    CompareXML.compareFiles(new ByteArrayInputStream(originalstream.toByteArray()), new ByteArrayInputStream(originalstream2.toByteArray()));

    // re-apply changes and compare if root resource is the same as after we made first changes
    ((EChangeSummary)changeSummary).apply();

    ((EDataGraph)dataGraph).getRootResource().save(changedstream2, options);
    CompareXML.compareFiles(new ByteArrayInputStream(changedstream.toByteArray()), new ByteArrayInputStream(changedstream2.toByteArray()));

  }

  private void verifyType(DataObject o, DataObject copyDO, List<?> objectsToAttach, ChangeSummary changeSummary)
  {
    List<?> settings = changeSummary.getOldValues(o);
    for (int k = 0; k < settings.size(); k++)
    {
      ChangeSummary.Setting s = (ChangeSummary.Setting)settings.get(k);
      // FeatureChange change = (FeatureChange)s;
      Object svalue = s.getValue();
      Object value = copyDO.get(s.getProperty());
      if (svalue instanceof DataObject)
      {
        assertEquals(true, objectsToAttach.contains(svalue));
        verifyType((DataObject)svalue, (DataObject)value, objectsToAttach, changeSummary);
        // remove value so we don't process it again
        objectsToAttach.remove(svalue);
        continue;
      }
      assertEquals(svalue, value);
    }
  }

  public void testPersonalMixed() throws Exception
  {
    Map<String, Object> options = new HashMap<String, Object>();
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, registerPersonalMixed());
    FileInputStream fileInputStream = new FileInputStream(DATA + "personalMixed.xml");
    byte[] dataGraphBytes = new byte [fileInputStream.available()];
    fileInputStream.read(dataGraphBytes);

    ByteArrayInputStream dgByteInputStream = new ByteArrayInputStream(dataGraphBytes);
    DataGraph dataGraph = SDOUtil.loadDataGraph(dgByteInputStream, options);
    changeSummary = dataGraph.getChangeSummary();

    // RECORD ORIGINAL STREAM
    ByteArrayOutputStream originalstream = new ByteArrayOutputStream(2064);
    ((EDataGraph)dataGraph).getRootResource().save(originalstream, options);

    // Get some values
    DataObject personnel = (DataObject)dataGraph.getRootObject().get("personnel");
    List<?> personList = (List<?>)personnel.get("person");
    DataObject bigBoss = ((DataObject)personList.get(0));
    DataObject oneWorker = ((DataObject)personList.get(1));
    DataObject twoWorker = ((DataObject)personList.get(2));
    DataObject fiveWorker = ((DataObject)personList.get(5));
    DataObject bigBossLink = ((DataObject)bigBoss.get("link"));
    DataObject twoWorkerLink = ((DataObject)twoWorker.get("link"));
    @SuppressWarnings("unchecked") List<String> emailList = fiveWorker.getList("email");
    List<?> subordinates = bigBossLink.getList("subordinates");

    // make copy of original to compare with recorded values in change summary
    DataObject oneWorkerCopy = (DataObject)EcoreUtil.copy((EObject)oneWorker);
    // DataObject twoWorkerLinkCopy = (DataObject)EcoreUtil.copy((EObject)twoWorkerLink);

    // make changes
    changeSummary.beginLogging();
    twoWorkerLink.delete();
    oneWorker.delete();
    List<Object> newList = new ArrayList<Object>();
    newList.addAll(subordinates);
    newList.remove(0);
    bigBossLink.setList("subordinates", newList);
    emailList.add("five_extra@foo.com");
    fiveWorker.getDataObject("name").delete();
    changeSummary.endLogging();

    List<EObject> objectsToAttach = ((EChangeSummary)changeSummary).getObjectsToAttach();

    DataObject personType = getDO(objectsToAttach, "PersonType");

    // verify that personType is correctly recorded
    testGetOldValue(personType, oneWorkerCopy);
    testGetOldValues(personType, oneWorkerCopy);

    // compare serialization format for root resource
    ByteArrayOutputStream changedstream = new ByteArrayOutputStream(2064);
    ByteArrayOutputStream changedstream2 = new ByteArrayOutputStream(2064);

    // record root resource following the changes
    ((EDataGraph)dataGraph).getRootResource().save(changedstream, options);

    // revert to original state and record root resource. This should be equal to the 
    // original file
    ((EChangeSummary)changeSummary).applyAndReverse();
    ByteArrayOutputStream originalstream2 = new ByteArrayOutputStream(2064);
    ((EDataGraph)dataGraph).getRootResource().save(originalstream2, options);
    CompareXML.compareFiles(new ByteArrayInputStream(originalstream.toByteArray()), new ByteArrayInputStream(originalstream2.toByteArray()));

    // re-apply changes and compare if root resource is the same as after we made first changes
    ((EChangeSummary)changeSummary).apply();

    ((EDataGraph)dataGraph).getRootResource().save(changedstream2, options);
    CompareXML.compareFiles(new ByteArrayInputStream(changedstream.toByteArray()), new ByteArrayInputStream(changedstream2.toByteArray()));
  }

  private DataObject getDO(List<?> list, String name)
  {
    for (int i = 0; i < list.size(); i++)
    {
      DataObject o = (DataObject)list.get(i);
      if (o.getType().getName().equals(name))
      {
        return o;
      }
    }
    return null;
  }

  private void testGetOldValue(DataObject o, DataObject copyDO)
  {
    if (DEBUG)
    {
      System.out.println("processing..." + o);
    }
    List<?> properties = copyDO.getType().getProperties();
    for (int i = 0; i < properties.size(); i++)
    {
      EProperty p = (EProperty)properties.get(i);
      if (!FeatureMapUtil.isFeatureMap(p.getEStructuralFeature()))
      {
        if (DEBUG)
        {
          System.out.println(p.getName());
        }
        ChangeSummary.Setting s = ((EChangeSummary)changeSummary).getOldValue(o, p);
        if (s == null)
        {
          assertEquals(false, copyDO.isSet(p));
        }
        else
        {
          compareValues(s.getValue(), copyDO.get(p), true);
        }
      }
    }
  }

  private void testGetOldValues(DataObject o, DataObject copyDO)
  {
    List<?> settings = changeSummary.getOldValues(o);
    if (DEBUG)
    {
      System.out.println("**testGetOldValues");
    }
    for (int k = 0; k < settings.size(); k++)
    {
      ChangeSummary.Setting s = (ChangeSummary.Setting)settings.get(k);
      if (DEBUG)
      {
        System.out.println(settings);
        System.out.println("processing... " + o);
        System.out.println(s.getProperty().getName());
      }
      Object svalue = s.getValue();
      Object value = copyDO.get(s.getProperty());

      compareValues(svalue, value, false);
    }
  }

  private void compareValues(Object changedValue, Object originalValue, boolean isTestGetOldValue)
  {
    if (DEBUG)
    {
      System.out.println("  original:" + originalValue);
      System.out.println("  changed: " + changedValue);
    }

    if (originalValue instanceof DataObject)
    {
      if (isTestGetOldValue)
      {
        testGetOldValue((DataObject)changedValue, (DataObject)originalValue);
      }
      else
      {
        testGetOldValues((DataObject)changedValue, (DataObject)originalValue);
      }
    }
    else if (originalValue instanceof Sequence)
    {
      Sequence changedSequence = (Sequence)changedValue;
      Sequence sequence = (Sequence)originalValue;
      assertEquals(sequence.size(), changedSequence.size());
      for (int i = 0; i < sequence.size(); i++)
      {
        Property p = sequence.getProperty(i);
        originalValue = sequence.getValue(i);
        changedValue = changedSequence.getValue(i);
        if (DEBUG)
        {
          System.out.println(p.getName());
        }
        compareValues(changedValue, originalValue, isTestGetOldValue);
      }
    }
    else if (originalValue instanceof List<?>)
    {
      List<?> list = (List<?>)originalValue;
      List<?> changedList = (List<?>)changedValue;
      for (int i = 0; i < list.size(); i++)
      {
        compareValues(changedList.get(i), list.get(i), isTestGetOldValue);
      }
    }
    else
    {
      assertEquals(originalValue, changedValue);
    }
  }

  /*
   * Bugzilla 83314
   */
  public void testIsDeleted() throws Exception
  {
    DataGraph dataGraph = SDOFactory.eINSTANCE.createEDataGraph();
    DataObject phoneBook = createPhoneBookDataObject(dataGraph);
    DataObject homeNumber = phoneBook.createDataObject("home");
    homeNumber.setString("number", "111-111-1111");
    DataObject workNumber = phoneBook.createDataObject("work");
    workNumber.setString("number", "111-111-1112");

    ChangeSummary log = dataGraph.getChangeSummary();
    log.beginLogging();
    workNumber.delete();
    log.endLogging();

    assertTrue(log.isDeleted(workNumber));
    assertFalse(log.isDeleted(homeNumber));

    log.beginLogging();
    homeNumber.delete();
    log.endLogging();

    assertTrue(log.isDeleted(homeNumber));
  }

  public void testEndLoggingAfterSerialization() throws Exception
  {
    DataGraph dataGraph = new MyDataGraphImpl();

    DataObject phoneBook = createPhoneBookDataObject(dataGraph);
    assertEquals(dataGraph, phoneBook.getDataGraph());
    phoneBook.setString("MyName", "Srikanth");

    DataObject phoneNumber = phoneBook.createDataObject("work");
    assertEquals(dataGraph, phoneNumber.getDataGraph());
    phoneNumber.setString("number", "111-111-1112");

    ChangeSummary log = dataGraph.getChangeSummary();
    log.beginLogging();

    phoneNumber.delete();

    // Do Serialization and Deserialization
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
    objectOutputStream.writeObject(phoneBook.getDataGraph());
    objectOutputStream.close();
    InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
    EDataGraph eDataGraph2 = (EDataGraph)objectInputStream.readObject();

    assertTrue(eDataGraph2.getChangeSummary().isLogging());

    eDataGraph2.getChangeSummary().endLogging();
    log = eDataGraph2.getChangeSummary();
    List<?> changedDOList = eDataGraph2.getChangeSummary().getChangedDataObjects();

    assertEquals(2, changedDOList.size());
    DataObject[] dataObjects = changedDOList.toArray(new DataObject [changedDOList.size()]);
    String deletedTypeName = dataObjects[0].getType().getName();
    String updatedTypeName = dataObjects[1].getType().getName();
    if (log.isDeleted(dataObjects[1]))
    {
      deletedTypeName = dataObjects[1].getType().getName();
      updatedTypeName = dataObjects[0].getType().getName();
    }

    assertEquals("PhoneNumber", deletedTypeName);
    assertEquals("PhoneBook", updatedTypeName);
  }
  
  public void testOldContainer()
  {
    EDataGraph dataGraph = new MyDataGraphImpl();
    
    //Instantiating an object
    PurchaseOrderType purchaseOrder = IPOFactory.INSTANCE.createPurchaseOrderType();
    USAddress shipToAddress = IPOFactory.INSTANCE.createUSAddress();
    USAddress billToAddress = IPOFactory.INSTANCE.createUSAddress();
    
    dataGraph.setERootObject((EObject)purchaseOrder);
    purchaseOrder.setShipTo(shipToAddress);
    
    //Removing the object from the resource
    EChangeSummary log = (EChangeSummary)dataGraph.getChangeSummary();
    log.beginLogging();
    purchaseOrder.setBillTo(billToAddress);
    purchaseOrder.setShipTo(null);
    log.endLogging();
    
    DataObject oldContainerBillToContainer = log.getOldContainer((DataObject)billToAddress);
    assertNull(oldContainerBillToContainer);
    
    DataObject oldContainerShipToContainer = log.getOldContainer((DataObject)shipToAddress);
    assertEquals(oldContainerShipToContainer, purchaseOrder);
  }

  public void testOpenContentOldContainer()
  {
    EDataGraph dataGraph = new MyDataGraphImpl();
    ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(dataGraph.getResourceSet().getPackageRegistry());
    
    EDataObjectAnyType parent = SDOFactory.eINSTANCE.createEDataObjectAnyType();
    EDataObjectAnyType child1 = SDOFactory.eINSTANCE.createEDataObjectAnyType();
    EDataObjectAnyType child2 = SDOFactory.eINSTANCE.createEDataObjectAnyType();
    
    EStructuralFeature childFeature1 = extendedMetaData.demandFeature("namespace", "child1", true);
    EStructuralFeature childFeature2 = extendedMetaData.demandFeature("namespace", "child2", true);
    

    dataGraph.setERootObject(parent);
    parent.getAny().add(childFeature1, child1);
    
    //Removing the object from the resource
    EChangeSummary log = (EChangeSummary)dataGraph.getChangeSummary();
    log.beginLogging();
    parent.getAny().add(childFeature2, child2);
    parent.getAny().remove(0);
    log.endLogging();
    
    DataObject oldContainerBillToContainer = log.getOldContainer(child2);
    assertNull(oldContainerBillToContainer);
    
    DataObject oldContainerShipToContainer = log.getOldContainer(child1);
    assertEquals(oldContainerShipToContainer, parent);
  }

  /**
   * A derived data graph that records whether logging is on or off during serialization 
   * and restores the sate during deserialization.
   */
  public static class MyDataGraphImpl extends EDataGraphImpl
  {
    /**
     * 
     */
    private static final long serialVersionUID = 2L;

    /**
     * A derived data graph externalizable that records the logging state and restores it.
     */
    public static class MyDataGraphExternalizable extends EDataGraphExternalizable
    {
      public MyDataGraphExternalizable()
      {
        super();
      }

      public MyDataGraphExternalizable(EDataGraph eDataGraph)
      {
        super(eDataGraph);
      }

      @Override
      public void writeExternal(ObjectOutput objectOutput) throws IOException
      {
        super.writeExternal(objectOutput);
        objectOutput.writeBoolean(eDataGraph.getChangeSummary().isLogging());
      }

      @Override
      public void readExternal(ObjectInput objectInput) throws IOException
      {
        super.readExternal(objectInput);
        boolean isLogging = objectInput.readBoolean();

        // If logging should be on...
        //
        if (isLogging)
        {
          eDataGraph.getEChangeSummary().resumeLogging();
        }
      }
    }

    @Override
    protected EDataGraphExternalizable createEDataGraphExternalizable()
    {
      // Create the derived externalizable.
      //
      return new MyDataGraphExternalizable(this);
    }
  }

  public static DataObject createPhoneBookDataObject(DataGraph dataGraph)
  {
    EcoreFactory fac = EcoreFactory.eINSTANCE;

    EClass phoneClass = fac.createEClass();
    phoneClass.setName("PhoneBook");

    EAttribute attr = fac.createEAttribute();
    attr.setName("MyName");
    attr.setEType(EcorePackage.Literals.ESTRING);
    attr.setLowerBound(1);
    attr.setUpperBound(1);
    phoneClass.getEStructuralFeatures().add(attr);

    EClass detailClass = fac.createEClass();
    detailClass.setName("PhoneNumber");

    attr = fac.createEAttribute();
    attr.setName("number");
    attr.setEType(EcorePackage.Literals.ESTRING);
    attr.setLowerBound(1);
    attr.setUpperBound(1);
    detailClass.getEStructuralFeatures().add(attr);

    EReference work = fac.createEReference();
    work.setName("work");
    work.setContainment(true);
    work.setLowerBound(1);
    work.setUpperBound(1);
    work.setEType(detailClass);
    phoneClass.getEStructuralFeatures().add(work);

    EReference home = fac.createEReference();
    home.setName("home");
    home.setContainment(true);
    home.setLowerBound(1);
    home.setUpperBound(1);
    home.setEType(detailClass);
    phoneClass.getEStructuralFeatures().add(home);

    EPackage pkg = fac.createEPackage();
    pkg.setName("Phone");
    pkg.setNsURI("Phone.ecore");
    pkg.setNsPrefix("Phone");
    pkg.getEClassifiers().add(phoneClass);
    pkg.getEClassifiers().add(detailClass);

    EPackage.Registry.INSTANCE.put(pkg.getNsURI(), pkg);
    EFactoryImpl dofac = (new EFactoryImpl()
      {
        @Override
        public EObject basicCreate(EClass eClass)
        {
          return new DynamicEDataObjectImpl(eClass);
        }
      });
    pkg.setEFactoryInstance(dofac);

    assertNotNull(dataGraph.getChangeSummary());
    return dataGraph.createRootObject(pkg.getNsURI(), phoneClass.getName());
  }
  
  /*
   * Bugzillas: 106020, 106047
   */
  public void testGetOldValues() throws Exception
  {
    EClass employeeClass = EcoreFactory.eINSTANCE.createEClass();
    employeeClass.setName("Employee");

    // name
    EAttribute employeeNameFeature = EcoreFactory.eINSTANCE.createEAttribute();
    employeeNameFeature.setName("name");
    employeeNameFeature.setEType(EcorePackage.Literals.ESTRING);
    employeeClass.getEStructuralFeatures().add(employeeNameFeature);

    // employees (that the employee manages)
    EReference employeeEmployeesFeature = EcoreFactory.eINSTANCE.createEReference();
    employeeEmployeesFeature.setName("employees");
    employeeEmployeesFeature.setEType(employeeClass);
    employeeEmployeesFeature.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    employeeEmployeesFeature.setContainment(true); // must be true so that
    // nested employees will
    // be in the graph
    employeeClass.getEStructuralFeatures().add(employeeEmployeesFeature);

    EPackage employeePackage = EcoreFactory.eINSTANCE.createEPackage();
    employeePackage.setName("company");
    employeePackage.setNsPrefix("company");
    employeePackage.setNsURI("http://com.example.company.ecore");
    employeePackage.getEClassifiers().add(employeeClass);

    // Have the factory for this package build SDO Objects
    employeePackage.setEFactoryInstance(new DynamicEDataObjectImpl.FactoryImpl());
    

    EDataObject boss = (EDataObject)EcoreUtil.create(employeeClass);
    boss.eSet(employeeNameFeature, "Boss");
    EDataObject employeeA = (EDataObject)EcoreUtil.create(employeeClass);
    employeeA.eSet(employeeNameFeature, "employeeA");
    
    @SuppressWarnings("unchecked")
    List<Object> employees = (List<Object>)boss.eGet(employeeEmployeesFeature);
    
    employees.add(employeeA);
    EDataObject employeeB = (EDataObject)EcoreUtil.create(employeeClass);
    employeeB.eSet(employeeNameFeature, "employeeB");
    employees.add(employeeB);
    EDataObject employeeC = (EDataObject)EcoreUtil.create(employeeClass);
    employeeC.eSet(employeeNameFeature, "employeeC");
    
    
    EDataGraph employeeGraph = SDOFactory.eINSTANCE.createEDataGraph();
    employeeGraph.setERootObject(boss);
    employeeGraph.getChangeSummary().beginLogging();
    
    employees.add(employeeC);
    employees.remove(0); //remove employeeA
    
    EChangeSummary eChangeSummary = employeeGraph.getEChangeSummary();
    eChangeSummary.endLogging();
    
    assertTrue(eChangeSummary.isDeleted(employeeA));
    assertTrue(eChangeSummary.isCreated(employeeC));
    
    assertTrue(eChangeSummary.getOldValues(employeeA).isEmpty());
    assertTrue(eChangeSummary.getOldValues(employeeB).isEmpty());
    assertTrue(eChangeSummary.getOldValues(employeeC).isEmpty());
    
    List<?> changeSummarySettings = eChangeSummary.getOldValues(boss);
    assertEquals(1, changeSummarySettings.size());
    ChangeSummary.Setting setting = (ChangeSummary.Setting)changeSummarySettings.get(0);
    assertEquals(employeeEmployeesFeature, ((EChangeSummarySetting)setting).getFeature());
    List<?> value = (List<?>)((EChangeSummarySetting)setting).getValue();
    assertEquals(2, value.size());
    assertTrue(value.contains(employeeA));
    assertTrue(value.contains(employeeB));

    //
    
    employeeGraph.getChangeSummary().beginLogging();
    ((List<?>)boss.eGet(employeeEmployeesFeature)).remove(1); //remove employeeC
    
    eChangeSummary = employeeGraph.getEChangeSummary();
    eChangeSummary.endLogging();

    assertTrue(eChangeSummary.isDeleted(employeeC));

    assertTrue(eChangeSummary.getOldValues(employeeA).isEmpty());
    assertTrue(eChangeSummary.getOldValues(employeeB).isEmpty());
    assertTrue(eChangeSummary.getOldValues(employeeC).isEmpty());
    
    changeSummarySettings = eChangeSummary.getOldValues(boss);
    assertEquals(1, changeSummarySettings.size());
    setting = (ChangeSummary.Setting)changeSummarySettings.get(0);
    assertEquals(employeeEmployeesFeature, ((EChangeSummarySetting)setting).getFeature());
    value = (List<?>)((EChangeSummarySetting)setting).getValue();
    assertEquals(2, value.size());
    assertTrue(value.contains(employeeB));
    assertTrue(value.contains(employeeC));
  }
}