/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: SpecialCasesTest.java,v 1.3 2004/10/28 21:19:01 marcelop Exp $
 */
package org.eclipse.emf.test.core.change;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.test.core.EMFTestCorePlugin;
import org.eclipse.emf.test.core.featuremap.supplier.PurchaseOrder;
import org.eclipse.emf.test.core.featuremap.supplier.Supplier;
import org.eclipse.emf.test.core.featuremap.supplier.SupplierFactory;
import org.eclipse.emf.test.core.featuremap.supplier.SupplierPackage;

public class SpecialCasesTest  extends TestCase
{
  public SpecialCasesTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("FeatureMapTest - ChangeRecorder");
    ts.addTest(new SpecialCasesTest("testFeatureMapChange"));
    ts.addTest(new SpecialCasesTest("testOneToManyContainment"));
    ts.addTest(new SpecialCasesTest("testOneToOneContainment"));
    ts.addTest(new SpecialCasesTest("testBiDirectional"));
    return ts;
  }

  public void testFeatureMapChange() throws Exception
  {
    SupplierFactory supplierFactory = SupplierPackage.eINSTANCE.getSupplierFactory();
    
    Supplier supplier = supplierFactory.createSupplier();
    supplier.setName("ACME");
    new ResourceImpl().getContents().add(supplier);
    
    PurchaseOrder po1 = supplierFactory.createPurchaseOrder();
    po1.setId("po1");
    supplier.getPreferredOrders().add(po1);
    
    PurchaseOrder po2 = supplierFactory.createPurchaseOrder();
    po2.setId("po2");
    supplier.getStandardOrders().add(po2);

    PurchaseOrder po3 = SupplierFactory.eINSTANCE.createPurchaseOrder();
    PurchaseOrder po4 = SupplierFactory.eINSTANCE.createPurchaseOrder();
    
    List ordersBeforeChange = new ArrayList(supplier.getOrders());
    List preferredOrdersBeforeChange = new ArrayList(supplier.getPreferredOrders());
    List standardOrdersBeforeChange = new ArrayList(supplier.getStandardOrders());
        
    ChangeRecorder changeRecorder = new ChangeRecorder(supplier);
    
    supplier.getPreferredOrders().add(po3);
    po3.setId("po3");
    supplier.getStandardOrders().add(po4);
    supplier.getStandardOrders().add(po1);
    supplier.getStandardOrders().remove(po2);
    
    ChangeDescription changeDescription = changeRecorder.endRecording();
    
    assertFalse(EMFTestCorePlugin.areEqual(ordersBeforeChange, supplier.getOrders()));
    assertFalse(EMFTestCorePlugin.areEqual(preferredOrdersBeforeChange, supplier.getPreferredOrders()));
    assertFalse(EMFTestCorePlugin.areEqual(standardOrdersBeforeChange, supplier.getStandardOrders()));
    
    assertEquals(2, changeDescription.getObjectsToDetach().size());
    assertEquals(po3, changeDescription.getObjectsToDetach().get(0));
    assertEquals(po4, changeDescription.getObjectsToDetach().get(1));
    assertEquals(1, changeDescription.getObjectsToAttach().size());
    assertEquals(po2, changeDescription.getObjectsToAttach().get(0));
    
    assertEquals(2, changeDescription.getObjectChanges().size());
    List featureChanges = (List)changeDescription.getObjectChanges().get(po3);
    assertEquals(1, featureChanges.size());
    
    featureChanges = (List)changeDescription.getObjectChanges().get(supplier);
    assertEquals(2, featureChanges.size());
    
    changeDescription.apply();

    assertTrue(EMFTestCorePlugin.areEqual(ordersBeforeChange, supplier.getOrders()));
    assertTrue(EMFTestCorePlugin.areEqual(preferredOrdersBeforeChange, supplier.getPreferredOrders()));
    assertTrue(EMFTestCorePlugin.areEqual(standardOrdersBeforeChange, supplier.getStandardOrders()));
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
    List johnFriends = (List)john.eGet(friendsReference);
    List johnEnemies = (List)john.eGet(enemiesReference);
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
    List acmeEmployess = (List)acme.eGet(employeesReference);
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
}
