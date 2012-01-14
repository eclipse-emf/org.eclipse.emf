/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.edit.provider;


import java.util.Collection;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;


/**
 * A test case that demonstrates how getReachableObjectsOfType works.
 * This test case looks for different objects that are reachable
 * in the graph given their type.  The test case uses a large
 * dataset and demonstrates BUG #161744;
 * @author Ian Bull
 */
public class TypeSearchTest extends TestCase
{
  public TypeSearchTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("TypeSearchTest");
    suite.addTest(new TypeSearchTest("testFindFirstObject"));
    suite.addTest(new TypeSearchTest("testFindLastObject"));
    suite.addTest(new TypeSearchTest("testFindMultiType"));
    suite.addTest(new TypeSearchTest("testFindMultiType2"));
    suite.addTest(new TypeSearchTest("testFindNoType"));
    suite.addTest(new TypeSearchTest("testFindSingleType"));
    suite.addTest(new TypeSearchTest("testFindWithSuperTypes"));
    return suite;
  }

  public static final int DATASETSIZE = 10000;

  EClass poClass = null;

  EClass itemClass = null;

  EClass otherClass = null;

  EClass firstClass = null;

  EClass lastClass = null;

  EClass superClass = null;

  EReference next = null;

  EReference item = null;

  EReference otherReference = null;

  EReference lastReference = null;

  EReference first2second = null;

  EObject firstObject = null;

  EObject otherItemObject = null;

  EObject lastObject = null;

  /**
   * Creates the EClasses for the test case.
   */
  private void createEClasses()
  {
    EPackage poPackage = EcoreFactory.eINSTANCE.createEPackage();
    superClass = EcoreFactory.eINSTANCE.createEClass();
    poClass = EcoreFactory.eINSTANCE.createEClass();
    itemClass = EcoreFactory.eINSTANCE.createEClass();
    otherClass = EcoreFactory.eINSTANCE.createEClass();
    firstClass = EcoreFactory.eINSTANCE.createEClass();
    lastClass = EcoreFactory.eINSTANCE.createEClass();

    poClass.getESuperTypes().add(superClass);
    itemClass.getESuperTypes().add(superClass);

    poPackage.getEClassifiers().add(superClass);
    poPackage.getEClassifiers().add(poClass);
    poPackage.getEClassifiers().add(itemClass);
    poPackage.getEClassifiers().add(otherClass);
    poPackage.getEClassifiers().add(firstClass);
    poPackage.getEClassifiers().add(lastClass);

  }

  /**
   * Creates the EReferences for the test case.
   */
  private void createEReferences()
  {
    first2second = EcoreFactory.eINSTANCE.createEReference();
    first2second.setName("first2second");
    first2second.setEType(poClass);
    firstClass.getEStructuralFeatures().add(first2second);

    next = EcoreFactory.eINSTANCE.createEReference();
    next.setName("next");
    next.setEType(poClass);
    poClass.getEStructuralFeatures().add(next);

    item = EcoreFactory.eINSTANCE.createEReference();
    item.setName("item");
    item.setEType(itemClass);
    poClass.getEStructuralFeatures().add(item);

    otherReference = EcoreFactory.eINSTANCE.createEReference();
    otherReference.setName("other");
    otherReference.setEType(otherClass);
    itemClass.getEStructuralFeatures().add(otherReference);

    lastReference = EcoreFactory.eINSTANCE.createEReference();
    lastReference.setName("lastReference");
    lastReference.setEType(lastClass);
    poClass.getEStructuralFeatures().add(lastReference);

  }

  /**
   * Create the data for the test case.
   * This creates 3501 poClasses, 350 item classes and a single otherClass
   *
   */
  private void createInstanceData()
  {
    firstObject = EcoreUtil.create(firstClass);
    EObject currentObject = EcoreUtil.create(poClass);

    firstObject.eSet(first2second, currentObject);

    for (int i = 0; i < DATASETSIZE - 1; i++)
    {
      EObject nextObject = EcoreUtil.create(poClass);
      currentObject.eSet(next, nextObject);

      if (i % 10 == 0)
      {
        EObject itemObject = EcoreUtil.create(itemClass);
        currentObject.eSet(item, itemObject);
        if (i == 2000)
        {
          otherItemObject = EcoreUtil.create(otherClass);
          itemObject.eSet(otherReference, otherItemObject);
        }
      }
      currentObject = nextObject;
    }
    lastObject = EcoreUtil.create(lastClass);
    currentObject.eSet(lastReference, lastObject);
  }

  /**
   * Setup the test case
   */
  @Override
  protected void setUp() throws Exception
  {
    createEClasses();
    createEReferences();
    createInstanceData();
  }

  /**
   * See if we can find the 3501 po Objects
   * @throws Exception
   */
  public void testFindMultiType() throws Exception
  {
    Collection<EObject> orders = ItemPropertyDescriptor.getReachableObjectsOfType(firstObject, poClass);
    assertEquals(orders.size(), DATASETSIZE);
  }

  /**
   * See if we can find the 350 item Objects
   * @throws Exception
   */
  public void testFindMultiType2() throws Exception
  {
    Collection<EObject> items = ItemPropertyDescriptor.getReachableObjectsOfType(firstObject, itemClass);
    assertEquals(items.size(), DATASETSIZE / 10);
  }

  /**
   * This test cases tests the reachable objects using their supertype.  Both the po objects and the
   * items were of type "superClass".
   * @throws Exception
   */
  public void testFindWithSuperTypes() throws Exception
  {
    Collection<EObject> c = ItemPropertyDescriptor.getReachableObjectsOfType(firstObject, superClass);
    assertEquals(c.size(), DATASETSIZE + (DATASETSIZE / 10));
  }

  /**
   * See if we can find the 1 otherObject
   * @throws Exception
   */
  public void testFindSingleType() throws Exception
  {
    List<EObject> others = (List<EObject>)ItemPropertyDescriptor.getReachableObjectsOfType(firstObject, otherClass);
    assertEquals(others.size(), 1);
    assertEquals(others.get(0), otherItemObject);
  }

  /**
   * Starting from the otherObject we can't get anywhere.  See if this works.
   * i.e. we should get no po objects from here!
   * @throws Exception
   */
  public void testFindNoType() throws Exception
  {
    Collection<EObject> others = ItemPropertyDescriptor.getReachableObjectsOfType(otherItemObject, poClass);
    assertEquals(others.size(), 0);
  }

  /**
   * Looks to see if it can find the first node in the list.  The first node
   * was a different type, so it should be found if we search by type.
   * @throws Exception
   */
  public void testFindFirstObject() throws Exception
  {
    List<EObject> c = (List<EObject>)ItemPropertyDescriptor.getReachableObjectsOfType(firstObject, firstClass);
    assertEquals(c.size(), 1);
    assertEquals(c.get(0), firstObject);
  }

  /**
   * Looks to see if it can find the last node in the list.  The last node
   * was a different type, so it should be found if we search by type.
   * @throws Exception
   */
  public void testFindLastObject() throws Exception
  {
    List<EObject> c = (List<EObject>)ItemPropertyDescriptor.getReachableObjectsOfType(firstObject, lastClass);
    assertEquals(c.size(), 1);
    assertEquals(c.get(0), lastObject);
  }
}
