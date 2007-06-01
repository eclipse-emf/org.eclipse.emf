package org.eclipse.emf.test.sdo;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.ecore.sdo.impl.DynamicEDataObjectImpl;
import org.eclipse.emf.ecore.sdo.impl.EDataGraphImpl;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;


public class ChangeSummaryTest extends TestCase
{
  public ChangeSummaryTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("ChangeSummaryTest");
    //testSuite.addTest(new ChangeSummaryTest("testEndLoggingAfterSerialization")); // not supported in 2.0.2 (yet)
    testSuite.addTest(new ChangeSummaryTest("testIsDeleted"));
    return testSuite;
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
    List changedDOList = eDataGraph2.getChangeSummary().getChangedDataObjects();
    
    assertEquals(2, changedDOList.size());
    DataObject[] dataObjects = (DataObject[])changedDOList.toArray(new DataObject [changedDOList.size()]);
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

  /**
   * A derived data graph that records whether logging is on or off during serialization 
   * and restores the sate during deserialization.
   */
  public static class MyDataGraphImpl extends EDataGraphImpl
  {
    /**
     * A derived data graph externalizable that records the logging state and restores it.
     */
    public static class MyDataGraphExternalizable extends EDataGraphExternalizable
    {
      public MyDataGraphExternalizable()
      {
      }

      public MyDataGraphExternalizable(EDataGraph eDataGraph)
      {
        super(eDataGraph);
      }

      public void writeExternal(ObjectOutput objectOutput) throws IOException
      {
        super.writeExternal(objectOutput);
        objectOutput.writeBoolean(eDataGraph.getChangeSummary().isLogging());
      }

      public void readExternal(ObjectInput objectInput) throws IOException
      {
        super.readExternal(objectInput);
        boolean isLogging = objectInput.readBoolean();

        // If logging should be on...
        //
        if (isLogging)
        {
          // eDataGraph.getEChangeSummary().resumeLogging();
        }
      }
    }

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
    attr.setEType(EcorePackage.eINSTANCE.getEString());
    attr.setLowerBound(1);
    attr.setUpperBound(1);
    phoneClass.getEStructuralFeatures().add(attr);

    EClass detailClass = fac.createEClass();
    detailClass.setName("PhoneNumber");

    attr = fac.createEAttribute();
    attr.setName("number");
    attr.setEType(EcorePackage.eINSTANCE.getEString());
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
        public EObject basicCreate(EClass eClass)
        {
          return new DynamicEDataObjectImpl(eClass);
        }
      });
    pkg.setEFactoryInstance(dofac);
    
    assertNotNull(dataGraph.getChangeSummary());
    return dataGraph.createRootObject(pkg.getNsURI(), phoneClass.getName());
  }
}
