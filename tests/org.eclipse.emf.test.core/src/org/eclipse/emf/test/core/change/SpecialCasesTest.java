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
 * $Id: SpecialCasesTest.java,v 1.28 2010/02/04 20:56:00 emerks Exp $
 */
package org.eclipse.emf.test.core.change;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;
import org.eclipse.emf.test.core.featuremap.supplier.PurchaseOrder;
import org.eclipse.emf.test.core.featuremap.supplier.Supplier;
import org.eclipse.emf.test.core.featuremap.supplier.SupplierFactory;
import org.eclipse.emf.test.core.featuremap.supplier.SupplierPackage;
import org.eclipse.emf.test.models.customer.CustomerPackage;

public class SpecialCasesTest  extends TestCase
{
  static final boolean DEBUG = false;
  
  public SpecialCasesTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("Change Moldel - Special Cases");
    ts.addTest(new SpecialCasesTest("testAddAllToAnotherContainment"));
    ts.addTest(new SpecialCasesTest("testFeatureMapChange"));
    ts.addTest(new SpecialCasesTest("testOneToManyContainment"));
    ts.addTest(new SpecialCasesTest("testOneToOneContainment"));
    ts.addTest(new SpecialCasesTest("testBiDirectional"));
    ts.addTest(new SpecialCasesTest("testMultipleResources"));
    ts.addTest(new SpecialCasesTest("testCopyChangeDescription"));
    ts.addTest(new SpecialCasesTest("testCopyChangeDescriptionAndObject"));
    ts.addTest(new SpecialCasesTest("testChangeDescriptionWhenResumming1"));
    ts.addTest(new SpecialCasesTest("testChangeDescriptionWhenResumming2"));
    ts.addTest(new SpecialCasesTest("testLoadChangeDescritpions"));
    ts.addTest(new SpecialCasesTest("testEnumeration"));
    ts.addTest(new SpecialCasesTest("testNoChange"));
    ts.addTest(new SpecialCasesTest("testFeatureMapWithSingleValuedFeature"));
    return ts;
  }

  public void testFeatureMapChange() throws Exception
  {
    SupplierFactory supplierFactory = SupplierPackage.eINSTANCE.getSupplierFactory();
    
    Supplier supplier = supplierFactory.createSupplier();
    supplier.setName("ACME");
    Resource resource = new ResourceImpl(URI.createFileURI("dummy.xml"));
    resource.getContents().add(supplier);
    
    PurchaseOrder po1 = supplierFactory.createPurchaseOrder();
    po1.setId("po1");
    supplier.getPreferredOrders().add(po1);
    
    PurchaseOrder po2 = supplierFactory.createPurchaseOrder();
    po2.setId("po2");
    supplier.getStandardOrders().add(po2);

    PurchaseOrder po3 = SupplierFactory.eINSTANCE.createPurchaseOrder();
    PurchaseOrder po4 = SupplierFactory.eINSTANCE.createPurchaseOrder();
    
    List<FeatureMap.Entry> ordersBeforeChange = new ArrayList<FeatureMap.Entry>(supplier.getOrders());
    List<PurchaseOrder> preferredOrdersBeforeChange = new ArrayList<PurchaseOrder>(supplier.getPreferredOrders());
    List<PurchaseOrder> standardOrdersBeforeChange = new ArrayList<PurchaseOrder>(supplier.getStandardOrders());
    if (DEBUG) resource.save(System.out, null);
    
    ChangeRecorder changeRecorder = new ChangeRecorder(supplier);
    
    supplier.getPreferredOrders().add(po3);
    po3.setId("po3");
    supplier.getStandardOrders().add(po4);
    supplier.getStandardOrders().add(po1);
    supplier.getStandardOrders().remove(po2);
    
    ChangeDescription changeDescription = changeRecorder.endRecording();
  
    resource.getContents().add(changeDescription);
    if (DEBUG) resource.save(System.out, null);
    
    assertFalse(TestUtil.areEqual(ordersBeforeChange, supplier.getOrders()));
    assertFalse(TestUtil.areEqual(preferredOrdersBeforeChange, supplier.getPreferredOrders()));
    assertFalse(TestUtil.areEqual(standardOrdersBeforeChange, supplier.getStandardOrders()));
    
    assertEquals(2, changeDescription.getObjectsToDetach().size());
    assertEquals(po3, changeDescription.getObjectsToDetach().get(0));
    assertEquals(po4, changeDescription.getObjectsToDetach().get(1));
    assertEquals(1, changeDescription.getObjectsToAttach().size());
    assertEquals(po2, changeDescription.getObjectsToAttach().get(0));
    
    assertEquals(2, changeDescription.getObjectChanges().size());
    List<FeatureChange> featureChanges = changeDescription.getObjectChanges().get(po3);
    assertEquals(1, featureChanges.size());
    
    featureChanges = changeDescription.getObjectChanges().get(supplier);
    assertEquals(1, featureChanges.size());
    
    changeDescription.apply();

    if (DEBUG) resource.save(System.out, null);
    
    assertTrue(TestUtil.areEqual(ordersBeforeChange, supplier.getOrders()));
    assertTrue(TestUtil.areEqual(preferredOrdersBeforeChange, supplier.getPreferredOrders()));
    assertTrue(TestUtil.areEqual(standardOrdersBeforeChange, supplier.getStandardOrders()));
  }
  
  public void testOneToManyContainment()
  {
    // Creating the model
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);
    
    EReference friendsReference = EcoreFactory.eINSTANCE.createEReference();
    friendsReference.setName("Friends");
    friendsReference.setEType(person);
    friendsReference.setContainment(true);
    friendsReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(friendsReference);

    EReference enemiesReference = EcoreFactory.eINSTANCE.createEReference();
    enemiesReference.setName("Enemies");
    enemiesReference.setEType(person);
    enemiesReference.setContainment(true);
    enemiesReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(enemiesReference);
    
    // Instantiating the model
    EObject john = pack.getEFactoryInstance().create(person);
    @SuppressWarnings("unchecked")
    List<EObject> johnFriends = (List<EObject>)john.eGet(friendsReference);
    @SuppressWarnings("unchecked")
    List<EObject> johnEnemies = (List<EObject>)john.eGet(enemiesReference);
    new ResourceImpl().getContents().add(john);
    
    EObject mary = pack.getEFactoryInstance().create(person);
    johnFriends.add(mary);
    
    // Checking if Mary is a friend
    assertEquals(1, johnFriends.size());
    assertEquals(mary, johnFriends.get(0));
    assertEquals(0, johnEnemies.size());
    
    // Adding an unknow enemy
    johnEnemies.add(pack.getEFactoryInstance().create(person));

    // Mary is not a friend anymore
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    johnEnemies.add(mary);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    // Checking if Mary is an enemy
    assertEquals(2, johnEnemies.size());
    assertEquals(mary, johnEnemies.get(1));
    assertEquals(0, johnFriends.size());

    // Forgiven Mary
    changeDescription.applyAndReverse();

    // Checking if Mary is a friend again
    assertEquals(1, johnFriends.size());
    assertEquals(mary, johnFriends.get(0));
    assertEquals(1, johnEnemies.size());
    
    // Mary is really not a friend
    changeDescription.apply();

    // Checking if Mary is an enemy
    assertEquals(2, johnEnemies.size());
    assertEquals(mary, johnEnemies.get(1));
    assertEquals(0, johnFriends.size());
  }
  
  public void testOneToOneContainment()
  {
    // Creating the model
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();

    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);
    
    EClass mail = EcoreFactory.eINSTANCE.createEClass();
    mail.setName("Mail");
    pack.getEClassifiers().add(mail);

    EReference inBoxReference = EcoreFactory.eINSTANCE.createEReference();
    inBoxReference.setName("InBox");
    inBoxReference.setEType(mail);
    inBoxReference.setContainment(true);
    inBoxReference.setUpperBound(1);
    person.getEStructuralFeatures().add(inBoxReference);

    EReference outBoxReference = EcoreFactory.eINSTANCE.createEReference();
    outBoxReference.setName("InBox");
    outBoxReference.setEType(mail);
    outBoxReference.setContainment(true);
    inBoxReference.setUpperBound(1);
    person.getEStructuralFeatures().add(outBoxReference);
    
    // Instantiating the model
    EObject john = pack.getEFactoryInstance().create(person);
    new ResourceImpl().getContents().add(john);
    EObject rsvp = pack.getEFactoryInstance().create(mail);

    // John received a mail
    john.eSet(inBoxReference, rsvp);
    
    // Mail is in the InBox
    assertEquals(rsvp, john.eGet(inBoxReference));
    assertNull(john.eGet(outBoxReference));
    assertEquals(john, rsvp.eContainer());
    
    // Answering the mail
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eSet(outBoxReference, rsvp);
    ChangeDescription changeDescription = changeRecorder.endRecording();

    // Mail is in the OutBox
    assertEquals(rsvp, john.eGet(outBoxReference));
    assertNull(john.eGet(inBoxReference));
    assertEquals(john, rsvp.eContainer());
    
    // John forgot to sign
    changeDescription.applyAndReverse();

    // Mail is in the InBox
    assertEquals(rsvp, john.eGet(inBoxReference));
    assertNull(john.eGet(outBoxReference));
    assertEquals(john, rsvp.eContainer());
    
    // Trying to send again
    changeDescription.apply();

    // Mail is in the OutBox
    assertEquals(rsvp, john.eGet(outBoxReference));
    assertNull(john.eGet(inBoxReference));
    assertEquals(john, rsvp.eContainer());
  }
  
  public void testBiDirectional()
  {
    // Creating the model
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);
    
    EClass company = EcoreFactory.eINSTANCE.createEClass();
    company.setName("Company");
    pack.getEClassifiers().add(company);
    
    EReference worksForReference = EcoreFactory.eINSTANCE.createEReference();
    worksForReference.setName("WorksFor");
    worksForReference.setEType(company);
    person.getEStructuralFeatures().add(worksForReference);

    EReference employeesReference = EcoreFactory.eINSTANCE.createEReference();
    employeesReference.setName("Employess");
    employeesReference.setEType(person);
    employeesReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    company.getEStructuralFeatures().add(employeesReference);

    worksForReference.setEOpposite(employeesReference);
    employeesReference.setEOpposite(worksForReference);
    
    // Instantiating the model
    EObject john = pack.getEFactoryInstance().create(person);
    new ResourceImpl().getContents().add(john);

    EObject mary = pack.getEFactoryInstance().create(person);
    new ResourceImpl().getContents().add(john);
    
    EObject acme = pack.getEFactoryInstance().create(company);
    new ResourceImpl().getContents().add(acme);
    @SuppressWarnings("unchecked")
    List<EObject> acmeEmployess = (List<EObject>)acme.eGet(employeesReference);
    acmeEmployess.add(mary);
    
    // Instantiated Model: Initial state
    assertEquals(1, acmeEmployess.size());
    assertEquals(mary, acmeEmployess.get(0));
    assertEquals(acme, mary.eGet(worksForReference));
    assertNull(john.eGet(worksForReference));
    
    // Adding john to the ACME family
    ChangeRecorder changeRecorder = new ChangeRecorder(Arrays.asList(new Object[]{john, mary, acme}));
    john.eSet(worksForReference, acme);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    // Instantiated Model: After recorded changes
    assertEquals(2, acmeEmployess.size());
    assertEquals(mary, acmeEmployess.get(0));
    assertEquals(acme, mary.eGet(worksForReference));
    assertEquals(john, acmeEmployess.get(1));
    assertEquals(acme, john.eGet(worksForReference));
    
    // Firing John
    changeDescription.applyAndReverse();
    
    // Instantiated Model: Initial state
    assertEquals(1, acmeEmployess.size());
    assertEquals(mary, acmeEmployess.get(0));
    assertEquals(acme, mary.eGet(worksForReference));
    assertNull(john.eGet(worksForReference));

    // Hiring John again
    changeDescription.apply();
    
    // Instantiated Model: After recorded changes
    assertEquals(2, acmeEmployess.size());
    assertEquals(mary, acmeEmployess.get(0));
    assertEquals(acme, mary.eGet(worksForReference));
    assertEquals(john, acmeEmployess.get(1));
    assertEquals(acme, john.eGet(worksForReference));    
  }
  
  /*
   * Bugzilla 80502
   */
  public void testMultipleResources() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    
    EObject john = pack.getEFactoryInstance().create(person);
    assertNull(john.eGet(name));
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eSet(name, "John");
    ChangeDescription changeDescription = changeRecorder.endRecording();
    assertEquals("John", john.eGet(name));
    
    
    // save
    
    URI johnURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/john.xmi");
    Resource johnResource = new XMIResourceImpl(johnURI);
    johnResource.getContents().add(john);
    
    URI changeDescriptionURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/cd.xmi");
    Resource changeDescriptionResource = new XMIResourceImpl(changeDescriptionURI);
    changeDescriptionResource.getContents().add(changeDescription);
    
    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    changeDescriptionResource.save(options);
    johnResource.save(null);
    
    // load
    
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getPackageRegistry().put(pack.getNsURI(), pack);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    
    Resource loadedChangeDescriptionResource = resourceSet.getResource(changeDescriptionURI, true);
    assertNotNull(loadedChangeDescriptionResource);
    assertEquals(1, loadedChangeDescriptionResource.getContents().size());    
    ChangeDescription loadedChangeDescription = (ChangeDescription)loadedChangeDescriptionResource.getContents().get(0);
    loadedChangeDescription.applyAndReverse();
    
    Resource loadedJohnResource = resourceSet.getResource(johnURI, true);
    assertNotNull(loadedJohnResource);
    assertEquals(1, loadedJohnResource.getContents().size());
    EObject loadedJohn = loadedJohnResource.getContents().get(0);
    assertNull(loadedJohn.eGet(name));
    
    loadedChangeDescription.apply();
    assertEquals("John", loadedJohn.eGet(name));
    
    File johnFile = new File(johnURI.toFileString());
    johnFile.delete();
    assertFalse(johnFile.exists());
    File changeDescriptionFile = new File(changeDescriptionURI.toFileString());
    changeDescriptionFile.delete();
    assertFalse(changeDescriptionFile.exists());
  }
  
  /*
   * Bugzilla 80547
   */
  public void testCopyChangeDescription() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    EAttribute id = EcoreFactory.eINSTANCE.createEAttribute();
    id.setName("id");
    id.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(id);
    
    EReference friendsReference = EcoreFactory.eINSTANCE.createEReference();
    friendsReference.setName("Friends");
    friendsReference.setEType(person);
    friendsReference.setContainment(true);
    friendsReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(friendsReference);    
    
    EObject john = pack.getEFactoryInstance().create(person);
    john.eSet(id, "123");
    @SuppressWarnings("unchecked")
    List<Object> friendsOfJohn = ((List<Object>)john.eGet(friendsReference));
    EObject mary = pack.getEFactoryInstance().create(person);
    mary.eSet(name, "Mary");
    
    Resource resource = new ResourceImpl();
    resource.getContents().add(john);
    
    // State 0
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertTrue(friendsOfJohn.isEmpty());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eSet(name, "John");
    john.eSet(id, "456");
    friendsOfJohn.add(mary);
    ChangeDescription changeDescription = changeRecorder.endRecording();

    // State 1
    assertEquals("John", john.eGet(name));
    assertEquals("456", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(mary, friendsOfJohn.get(0));
        
    ChangeDescription copiedChangeDescription = EcoreUtil.copy(changeDescription);

    copiedChangeDescription.applyAndReverse();

    // State 0
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertTrue(friendsOfJohn.isEmpty());
    
    copiedChangeDescription.apply();

    // State 1
    assertEquals("John", john.eGet(name));
    assertEquals("456", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(mary, friendsOfJohn.get(0));
  }
  
  /*
   * Bugzilla 80547
   */
  public void testCopyChangeDescriptionAndObject() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    EAttribute id = EcoreFactory.eINSTANCE.createEAttribute();
    id.setName("id");
    id.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(id);
    
    EReference friendsReference = EcoreFactory.eINSTANCE.createEReference();
    friendsReference.setName("Friends");
    friendsReference.setEType(person);
    friendsReference.setContainment(true);
    friendsReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(friendsReference);    
    
    EObject john = pack.getEFactoryInstance().create(person);
    john.eSet(id, "123");
    @SuppressWarnings("unchecked")
    List<EObject> friendsOfJohn = ((List<EObject>)john.eGet(friendsReference));
    EObject mary = pack.getEFactoryInstance().create(person);
    mary.eSet(name, "Mary");
    
    Resource resource = new ResourceImpl();
    resource.getContents().add(john);
    
    // State 0
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertTrue(friendsOfJohn.isEmpty());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eSet(name, "John");
    john.eSet(id, "456");
    friendsOfJohn.add(mary);
    ChangeDescription changeDescription = changeRecorder.endRecording();

    // State 1
    assertEquals("John", john.eGet(name));
    assertEquals("456", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(mary, friendsOfJohn.get(0));
        
    List<EObject> objects = new ArrayList<EObject>();
    objects.add(john);
    objects.add(changeDescription);
    Collection<EObject> copiedObjects = EcoreUtil.copyAll(objects);
    assertEquals(objects.size(), copiedObjects.size());
    
    EObject copiedJohn = null;
    ChangeDescription copiedChangeDescription = null;
    for (EObject eObject : copiedObjects)
    {
      if (eObject instanceof ChangeDescription)
      {
        copiedChangeDescription = (ChangeDescription)eObject;
      }
      else if (john.eGet(id).equals(eObject.eGet(id)))
      {
        copiedJohn = eObject;
      }
    }
    assertFalse(john.equals(copiedJohn));
    assertFalse(changeDescription.equals(copiedChangeDescription));
    assertNotNull(copiedJohn);
    @SuppressWarnings({"unchecked", "null"})
    List<EObject> friendsOfCopiedJohn = ((List<EObject>)copiedJohn.eGet(friendsReference));

    // State 1
    assertEquals("John", copiedJohn.eGet(name));
    assertEquals("456", copiedJohn.eGet(id));
    assertEquals(1, friendsOfCopiedJohn.size());
    EObject copiedMary = friendsOfCopiedJohn.get(0);
    assertFalse(mary.equals(copiedMary));
    assertEquals("Mary", copiedMary.eGet(name));
    
    assertNotNull(copiedChangeDescription);
    assert copiedChangeDescription != null;
    copiedChangeDescription.applyAndReverse();

    // State 0
    assertNull(copiedJohn.eGet(name));
    assertEquals("123", copiedJohn.eGet(id));
    assertEquals("Mary", copiedMary.eGet(name));
    assertTrue(friendsOfCopiedJohn.isEmpty());
    
    copiedChangeDescription.apply();

    // State 1
    assertEquals("John", copiedJohn.eGet(name));
    assertEquals("456", copiedJohn.eGet(id));
    assertEquals("Mary", copiedMary.eGet(name));
    assertEquals(1, friendsOfCopiedJohn.size());
    assertEquals(copiedMary, friendsOfCopiedJohn.get(0));
  }

  /*
   * Bugzilla 81013
   */
  public void testChangeDescriptionWhenResumming1() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    EAttribute id = EcoreFactory.eINSTANCE.createEAttribute();
    id.setName("id");
    id.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(id);
    
    EReference friendsReference = EcoreFactory.eINSTANCE.createEReference();
    friendsReference.setName("Friends");
    friendsReference.setEType(person);
    friendsReference.setContainment(true);
    friendsReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(friendsReference);    
    
    EObject john = pack.getEFactoryInstance().create(person);
    john.eSet(id, "123");
    EObject mary = pack.getEFactoryInstance().create(person);
    mary.eSet(name, "Mary");
    
    Resource resource = new ResourceImpl();
    resource.getContents().add(john);

    @SuppressWarnings("unchecked")
    List<EObject> friendsOfJohn = ((List<EObject>)john.eGet(friendsReference));
    friendsOfJohn.add(mary);
    
    // State 0
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(mary, friendsOfJohn.get(0));
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eSet(name, "John");
    john.eSet(id, "456");
    friendsOfJohn.remove(mary);
    mary.eSet(id, "1");
    mary.eSet(name, "Mary P");
    friendsOfJohn.add(mary);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    assertTrue(changeDescription.getObjectsToAttach().isEmpty());
    assertTrue(changeDescription.getObjectsToDetach().isEmpty());
    assertTrue(changeDescription.getObjectChanges().keySet().contains(mary));
    
    // State 1
    assertEquals("John", john.eGet(name));
    assertEquals("456", john.eGet(id));
    assertEquals(1, friendsOfJohn.size());
    assertEquals(mary, friendsOfJohn.get(0));
    assertEquals("Mary P", mary.eGet(name));
    assertEquals("1", mary.eGet(id));
  }
  
  /*
   * Bugzilla 81013
   */  
  public void testChangeDescriptionWhenResumming2() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    EAttribute id = EcoreFactory.eINSTANCE.createEAttribute();
    id.setName("id");
    id.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(id);
    
    EReference friendsReference = EcoreFactory.eINSTANCE.createEReference();
    friendsReference.setName("Friends");
    friendsReference.setEType(person);
    friendsReference.setContainment(true);
    friendsReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(friendsReference);    
    
    EObject john = pack.getEFactoryInstance().create(person);
    john.eSet(id, "123");
    @SuppressWarnings("unchecked")
    List<EObject> friendsOfJohn = ((List<EObject>)john.eGet(friendsReference));
    EObject mary = pack.getEFactoryInstance().create(person);
    mary.eSet(name, "Mary");
    
    Resource resource = new ResourceImpl();
    resource.getContents().add(john);

    mary.eSet(id, "0");
    
    // State 0
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertEquals("Mary", mary.eGet(name));
    assertEquals("0", mary.eGet(id));
    assertTrue(friendsOfJohn.isEmpty());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    john.eSet(name, "John");
    john.eSet(id, "456");
    friendsOfJohn.add(mary);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    changeRecorder = new ChangeRecorder();
    changeRecorder.beginRecording(changeDescription, Collections.singleton(john));
    mary.eSet(id, "1");
    mary.eSet(name, "Mary P");
    changeRecorder.endRecording();
    changeRecorder = new ChangeRecorder();
    changeRecorder.beginRecording(changeDescription, Collections.singleton(john));
    friendsOfJohn.remove(mary);
    changeRecorder.endRecording();
    
    assertTrue(changeDescription.getObjectsToAttach().isEmpty());
    assertTrue(changeDescription.getObjectsToDetach().isEmpty());
    assertFalse(changeDescription.getObjectChanges().keySet().contains(mary));      
    
    // State 1
    assertEquals("John", john.eGet(name));
    assertEquals("456", john.eGet(id));
    assertTrue(friendsOfJohn.isEmpty());
    assertEquals("Mary P", mary.eGet(name));
    assertEquals("1", mary.eGet(id));
    
    changeRecorder = new ChangeRecorder();
    changeRecorder.beginRecording(changeDescription, Collections.singleton(john));
    mary.eSet(id, "2");
    changeRecorder.endRecording();
    
    //mary was removed from the objects and change description so nothing was recorded
    assertTrue(changeDescription.getObjectsToAttach().isEmpty());
    assertTrue(changeDescription.getObjectsToDetach().isEmpty());
    assertFalse(changeDescription.getObjectChanges().keySet().contains(mary));      

    // State 2
    assertEquals("John", john.eGet(name));
    assertEquals("456", john.eGet(id));
    assertTrue(friendsOfJohn.isEmpty());
    
    changeDescription.applyAndReverse();

    // State 0
    assertNull(john.eGet(name));
    assertEquals("123", john.eGet(id));
    assertTrue(friendsOfJohn.isEmpty());
    // Mary was not changed
    assertEquals("Mary P", mary.eGet(name));
    assertEquals("2", mary.eGet(id));    
  }
  
  public void testLoadChangeDescritpions() throws Exception
  {
    URI[] changeDescriptionURIs = new URI[]
    {
       URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/objectsAndChangeDescriptionWithObjectsToDetach.xmi")
      ,URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/objectsAndChangeDescriptionWithoutObjectsToDetach.xmi")
      ,URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/changeDescriptionWithObjectsToDetach.xmi")
      ,URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/changeDescriptionWithoutObjectsToDetach.xmi")
    };
    URI familyURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/family.xmi");
    
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/test.core.change/testLoadChangeDescritpion");
    
    EClass family = EcoreFactory.eINSTANCE.createEClass();
    family.setName("Family");
    pack.getEClassifiers().add(family);

    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    
    EReference spouse1 = EcoreFactory.eINSTANCE.createEReference();
    spouse1.setName("spouse1");
    spouse1.setContainment(true);
    spouse1.setEType(person);
    family.getEStructuralFeatures().add(spouse1);

    EReference spouse2 = EcoreFactory.eINSTANCE.createEReference();
    spouse2.setName("spouse2");
    spouse2.setContainment(true);
    spouse2.setEType(person);
    family.getEStructuralFeatures().add(spouse2);
    
    EReference children = EcoreFactory.eINSTANCE.createEReference();
    children.setName("children");
    children.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    children.setContainment(true);
    children.setEType(person);
    family.getEStructuralFeatures().add(children);
    
    boolean createFile = false;
    if (createFile)
    {
      EObject john = pack.getEFactoryInstance().create(person);
      john.eSet(name, "John");
      
      EObject mary = pack.getEFactoryInstance().create(person);
      mary.eSet(name, "Mary");

      EObject paul = pack.getEFactoryInstance().create(person);
      paul.eSet(name, "Paul");

      EObject lisa = pack.getEFactoryInstance().create(person);
      lisa.eSet(name, "Lisa");

      EObject anna = pack.getEFactoryInstance().create(person);
      anna.eSet(name, "Anna");
      
      EObject johnsFamily = pack.getEFactoryInstance().create(family);
      johnsFamily.eSet(spouse1, john);
      johnsFamily.eSet(spouse2, mary);
      @SuppressWarnings("unchecked")
      List<EObject> johnsFamilyChildren = ((List<EObject>)johnsFamily.eGet(children));
      johnsFamilyChildren.add(paul);
            
      int changeDescriptionURIIndex = 3;

      Resource familyResource = new XMIResourceImpl(changeDescriptionURIIndex <= 1? changeDescriptionURIs[changeDescriptionURIIndex] :  familyURI);
      familyResource.getContents().add(johnsFamily);
      
      ChangeRecorder changeRecorder = new ChangeRecorder(johnsFamily);
      johnsFamily.eSet(spouse2, lisa);
      johnsFamilyChildren.add(anna);
      ChangeDescription changeDescription = changeRecorder.endRecording();
      Resource changeDescriptionResource = changeDescriptionURIIndex <= 1? familyResource :  new XMIResourceImpl(changeDescriptionURIs[changeDescriptionURIIndex]);
      changeDescriptionResource.getContents().add(changeDescription);
      
      List<EObject> objectsToDetach = changeDescription.getObjectsToDetach();
      assertEquals(2, objectsToDetach.size());
      assertTrue(objectsToDetach.contains(lisa));
      assertTrue(objectsToDetach.contains(anna));
      List<EObject> objectsToAttach = changeDescription.getObjectsToAttach();
      assertEquals(1, objectsToAttach.size());
      assertTrue(objectsToAttach.contains(mary));
      
      familyResource.save(Collections.EMPTY_MAP);
      changeDescriptionResource.save(Collections.EMPTY_MAP);
    }
    
    for (int i = 0; i < changeDescriptionURIs.length; i++)
    {
      String file = changeDescriptionURIs[i].toFileString();
      assertTrue(file + " doesn't exist", new File(file).exists());
      
      ResourceSet resourceSet = new ResourceSetImpl();
      resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
      resourceSet.getPackageRegistry().put(pack.getNsURI(), pack);
      
      Resource changeDescriptionResource = resourceSet.getResource(changeDescriptionURIs[i], true);
      
      ChangeDescription changeDescription = (ChangeDescription)changeDescriptionResource.getContents().get(changeDescriptionResource.getContents().size()-1);
      assertEquals(file, 2, changeDescription.getObjectsToDetach().size());
      assertEquals(file, 1, changeDescription.getObjectsToAttach().size());
      
      Resource familyResource = i <= 1 ? changeDescriptionResource : resourceSet.getResource(familyURI, true);      
      EObject johnsFamily = familyResource.getContents().get(0);
      EObject john = (EObject)johnsFamily.eGet(spouse1);
      assertEquals("John", john.eGet(name));
      EObject lisa = (EObject)johnsFamily.eGet(spouse2);
      assertEquals("Lisa", lisa.eGet(name));
      @SuppressWarnings("unchecked")
      List<EObject> johnsFamilyChildren = ((List<EObject>)johnsFamily.eGet(children));
      EObject paul = johnsFamilyChildren.get(0);
      assertEquals("Paul", paul.eGet(name));
      EObject anna = johnsFamilyChildren.get(1);
      assertEquals("Anna", anna.eGet(name));

      List<EObject> resolvedObjectsToDetach = new ArrayList<EObject>(2);
      for (EObject eObject : changeDescription.getObjectsToDetach())
      {
        resolvedObjectsToDetach.add(eObject.eIsProxy()?EcoreUtil.resolve(eObject, resourceSet):eObject);
      }
      
      assertTrue(file, resolvedObjectsToDetach.contains(lisa));
      assertTrue(file, resolvedObjectsToDetach.contains(anna));
      EObject mary = changeDescription.getObjectsToAttach().get(0);
      assertEquals(file, "Mary", mary.eGet(name));      
    }    
  }
  
  /*
   * Bugzilla 92271
   */
  public void testEnumeration() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack");
    EPackage.Registry.INSTANCE.put(pack.getNsURI(), pack);
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);

    //Attribute enumeration
    EEnum maritalStatusEnum = EcoreFactory.eINSTANCE.createEEnum();
    pack.getEClassifiers().add(maritalStatusEnum);
    EEnumLiteral single = EcoreFactory.eINSTANCE.createEEnumLiteral();
    single.setName("single");
    single.setValue(0);
    maritalStatusEnum.getELiterals().add(single);
    EEnumLiteral married = EcoreFactory.eINSTANCE.createEEnumLiteral();
    married.setName("married");
    single.setValue(1);
    maritalStatusEnum.getELiterals().add(married);
    
    EAttribute maritalStatus = EcoreFactory.eINSTANCE.createEAttribute();
    maritalStatus.setName("maritalStatus");
    maritalStatus.setEType(maritalStatusEnum);
    person.getEStructuralFeatures().add(maritalStatus);

    //Reference enumeration
    EEnum rankEnum = EcoreFactory.eINSTANCE.createEEnum();
    pack.getEClassifiers().add(rankEnum);
    EEnumLiteral beginner = EcoreFactory.eINSTANCE.createEEnumLiteral();
    beginner.setName("beginner");
    beginner.setValue(0);
    rankEnum.getELiterals().add(beginner);
    EEnumLiteral intermediate = EcoreFactory.eINSTANCE.createEEnumLiteral();
    intermediate.setName("intermediate");
    intermediate.setValue(1);
    rankEnum.getELiterals().add(intermediate);
    EEnumLiteral advanced = EcoreFactory.eINSTANCE.createEEnumLiteral();
    advanced.setName("advanced");
    advanced.setValue(2);
    rankEnum.getELiterals().add(advanced);
    
    EReference emfKnowledge = EcoreFactory.eINSTANCE.createEReference();
    emfKnowledge.setName("emfKnowledge");
    emfKnowledge.setEType(EcorePackage.eINSTANCE.getEEnumLiteral());
    person.getEStructuralFeatures().add(emfKnowledge);
    
    EObject john = pack.getEFactoryInstance().create(person);
    john.eSet(name, "John");
    john.eSet(maritalStatus, married);
    john.eSet(emfKnowledge, intermediate);
    EObject mary = pack.getEFactoryInstance().create(person);
    mary.eSet(name, "Mary");
    mary.eSet(emfKnowledge, beginner);
    
    //State 0
    assertEquals(married, john.eGet(maritalStatus));
    assertEquals(intermediate, john.eGet(emfKnowledge));
    assertEquals(single, mary.eGet(maritalStatus));
    assertEquals(beginner, mary.eGet(emfKnowledge));

    ChangeRecorder changeRecorder = new ChangeRecorder(Arrays.asList(new Object[]{john, mary}));
    john.eSet(maritalStatus, single);
    john.eSet(emfKnowledge, beginner);
    mary.eSet(maritalStatus, married);
    mary.eSet(emfKnowledge, advanced);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    //State 1
    assertEquals(single, john.eGet(maritalStatus));
    assertEquals(beginner, john.eGet(emfKnowledge));
    assertEquals(married, mary.eGet(maritalStatus));
    assertEquals(advanced, mary.eGet(emfKnowledge));
    
    changeDescription.applyAndReverse();

    //State 0
    assertEquals(married, john.eGet(maritalStatus));
    assertEquals(intermediate, john.eGet(emfKnowledge));
    assertEquals(single, mary.eGet(maritalStatus));
    assertEquals(beginner, mary.eGet(emfKnowledge));

    changeDescription.applyAndReverse();

    //State 1
    assertEquals(single, john.eGet(maritalStatus));
    assertEquals(beginner, john.eGet(emfKnowledge));
    assertEquals(married, mary.eGet(maritalStatus));
    assertEquals(advanced, mary.eGet(emfKnowledge));
    
    // Prepare for serialization
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getPackageRegistry().put(pack.getNsURI(), pack);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    
    Resource ecore = resourceSet.createResource(URI.createFileURI("my.ecore"));
    ecore.getContents().add(pack);
    
    Resource resource = resourceSet.createResource(URI.createFileURI("foo.xmi"));
    resource.getContents().add(john);
    resource.getContents().add(mary);
    resource.getContents().add(changeDescription);
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    resource.save(baos, null);
    
    // Load resource
    resource.unload();
    resource.load(new ByteArrayInputStream(baos.toByteArray()), null);
    
    EObject loadedJohn =  resource.getContents().get(0);
    assertEquals("John", loadedJohn.eGet(name));
    EObject loadedMary =  resource.getContents().get(1);
    assertEquals("Mary", loadedMary.eGet(name));

    //State 1
    assertEquals(single, loadedJohn.eGet(maritalStatus));
    assertEquals(beginner, loadedJohn.eGet(emfKnowledge));
    assertEquals(married, loadedMary.eGet(maritalStatus));
    assertEquals(advanced, loadedMary.eGet(emfKnowledge));
    
    ChangeDescription loadedChangeDescription = (ChangeDescription)resource.getContents().get(2);
    
    loadedChangeDescription.applyAndReverse();
    
    //State 0
    assertEquals(married, loadedJohn.eGet(maritalStatus));
    assertEquals(intermediate, loadedJohn.eGet(emfKnowledge));
    assertEquals(single, loadedMary.eGet(maritalStatus));
    assertEquals(beginner, loadedMary.eGet(emfKnowledge));
  }
  
  /*
   * Bugzilla 126639
   */
  public void testNoChange() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack/person");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    
    EReference father = EcoreFactory.eINSTANCE.createEReference();
    father.setName("father");
    father.setEType(person);
    person.getEStructuralFeatures().add(father);
    
    URI maryURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/mary.xmi");

    /*
    {
      URI johnURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/john.xmi");

      EObject john = EcoreUtil.create(person);
      john.eSet(name, "John");
      Resource johnResource = new XMIResourceImpl(johnURI);
      johnResource.getContents().add(john);

      EObject mary = EcoreUtil.create(person);
      mary.eSet(name, "Mary");
      mary.eSet(father, john);
      Resource maryResource = new XMIResourceImpl(maryURI);
      maryResource.getContents().add(mary);
      
      maryResource.save(null);
      johnResource.save(null);
    }
    */
        
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getPackageRegistry().put(pack.getNsURI(), pack);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        
    Resource maryResource = resourceSet.getResource(maryURI, true);
    
    EObject mary = maryResource.getContents().get(0);
    assertEquals("Mary", mary.eGet(name));
    
    EObject proxyToJohn = (EObject)mary.eGet(father, false);
    assertNotNull(proxyToJohn);
    assertTrue(proxyToJohn.eIsProxy());
    
    ChangeRecorder changeRecorder = new ChangeRecorder(resourceSet);
    EObject john = (EObject)mary.eGet(father);
    assertEquals("John", john.eGet(name));
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    assertFalse(changeDescription.getObjectChanges().containsKey(mary));
  }
  
  /*
   * Bugzilla 150866
   */
  public void testAddAllToAnotherContainment()
  {
    // Creating the model
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);
    
    EReference friendsReference = EcoreFactory.eINSTANCE.createEReference();
    friendsReference.setName("Friends");
    friendsReference.setEType(person);
    friendsReference.setContainment(true);
    friendsReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(friendsReference);

    EReference enemiesReference = EcoreFactory.eINSTANCE.createEReference();
    enemiesReference.setName("Enemies");
    enemiesReference.setEType(person);
    enemiesReference.setContainment(true);
    enemiesReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    person.getEStructuralFeatures().add(enemiesReference);
    
    // Instantiating the model
    EObject john = pack.getEFactoryInstance().create(person);
    @SuppressWarnings("unchecked")
    List<EObject> johnFriends = (List<EObject>)john.eGet(friendsReference);
    new ResourceImpl().getContents().add(john);
    
    EObject mary = pack.getEFactoryInstance().create(person);
    johnFriends.add(mary);
    EObject joe = pack.getEFactoryInstance().create(person);
    johnFriends.add(joe);
    EObject jane = pack.getEFactoryInstance().create(person);
    johnFriends.add(jane);
    EObject mark = pack.getEFactoryInstance().create(person);
    johnFriends.add(mark);
    EObject beth = pack.getEFactoryInstance().create(person);
    johnFriends.add(beth);

    // State 0
    assertEquals(5, johnFriends.size());
    assertEquals(mary, johnFriends.get(0));
    assertEquals(joe, johnFriends.get(1));
    assertEquals(jane, johnFriends.get(2));
    assertEquals(mark, johnFriends.get(3));
    assertEquals(beth, johnFriends.get(4));
    
    ChangeRecorder changeRecorder = new ChangeRecorder(john);
    johnFriends.clear();
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    // State 1.0
    assertTrue(johnFriends.isEmpty());

    changeDescription.applyAndReverse();

    // State 0
    assertEquals(5, johnFriends.size());
    assertEquals(mary, johnFriends.get(0));
    assertEquals(joe, johnFriends.get(1));
    assertEquals(jane, johnFriends.get(2));
    assertEquals(mark, johnFriends.get(3));
    assertEquals(beth, johnFriends.get(4));
    
    List<EObject> list = new ArrayList<EObject>();
    list.add(joe);
    list.add(mark);
    list.add(mary);
    list.add(beth);
    
    changeRecorder.beginRecording(Collections.singleton(john));
    johnFriends.removeAll(list);
    changeDescription = changeRecorder.endRecording();
    
    // State 1.1
    assertEquals(1, johnFriends.size());    
    assertEquals(jane, johnFriends.get(0));

    changeDescription.applyAndReverse();

    // State 0
    assertEquals(5, johnFriends.size());
    assertEquals(mary, johnFriends.get(0));
    assertEquals(joe, johnFriends.get(1));
    assertEquals(jane, johnFriends.get(2));
    assertEquals(mark, johnFriends.get(3));
    assertEquals(beth, johnFriends.get(4));
    
    
    //======
    
    
    @SuppressWarnings("unchecked")
    List<EObject> johnEnemies = (List<EObject>)john.eGet(enemiesReference);
    
    // Part of State 0
    assertTrue(johnEnemies.isEmpty());
    
    changeRecorder.beginRecording(Collections.singleton(john));
    johnEnemies.addAll(list);
    changeDescription = changeRecorder.endRecording();    
    
    // State 1.2
    assertEquals(1, johnFriends.size());    
    assertEquals(jane, johnFriends.get(0));
    assertEquals(4, johnEnemies.size());    
    assertEquals(joe, johnEnemies.get(0));
    assertEquals(mark, johnEnemies.get(1));
    assertEquals(mary, johnEnemies.get(2));
    assertEquals(beth, johnEnemies.get(3));

    changeDescription.applyAndReverse();

    // State 0
    assertEquals(5, johnFriends.size());
    assertEquals(mary, johnFriends.get(0));
    assertEquals(joe, johnFriends.get(1));
    assertEquals(jane, johnFriends.get(2));
    assertEquals(mark, johnFriends.get(3));
    assertEquals(beth, johnFriends.get(4));
    assertTrue(johnEnemies.isEmpty());
  }  
  
  public void testFeatureMapWithSingleValuedFeature()
  {
    EPackage ePackage = EcoreUtil.copy(CustomerPackage.eINSTANCE);
    EClass customersType = (EClass)ePackage.getEClassifier("CustomersType");
    EReference customerFeature = (EReference)customersType.getEStructuralFeature("customer");
    customerFeature.setUpperBound(1);
    
    EObject eObject = EcoreUtil.create(customersType);
    EObject child1 = EcoreUtil.create(customerFeature.getEReferenceType());
    eObject.eSet(customerFeature, child1);
    ChangeRecorder changeRecorder = new ChangeRecorder(eObject);
    EObject child2 = EcoreUtil.create(customerFeature.getEReferenceType());
    eObject.eSet(customerFeature, child2);
    ChangeDescription changeDescription = changeRecorder.endRecording();
    changeDescription.applyAndReverse();
    assertEquals(child1, eObject.eGet(customerFeature));
    changeDescription.apply();
    assertEquals(child2, eObject.eGet(customerFeature));
  }
}