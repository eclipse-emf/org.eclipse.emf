/**
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.test.common.TestUtil;

public class ECollectionsTest extends TestCase
{
  public ECollectionsTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ECollectionsTest");
    ts.addTest(new ECollectionsTest("testSortEList"));
    ts.addTest(new ECollectionsTest("testIndexOf1"));
    ts.addTest(new ECollectionsTest("testIndexOf2"));
    ts.addTest(new ECollectionsTest("testSetEList"));
    ts.addTest(new ECollectionsTest("testMoveIntInt"));
    ts.addTest(new ECollectionsTest("testMoveIntObject"));
    return ts;
  }

  public void testSortEList()
  {
    Comparator<String> comparator = new Comparator<String>()
    {
      public int compare(String c1, String c2)
      {
        return -1 * c1.compareTo(c2);
      }
      
      @Override
      public boolean equals(Object obj)
      {
        return obj == this;
      }
    };
    
    List<String> initialList = new ArrayList<String>();
    initialList.add("k");
    initialList.add("a");
    initialList.add("f");
    initialList.add("b");
    initialList.add("z");
    initialList.add("a");
    initialList.add("b");
    List<String> sortedList = new ArrayList<String>(initialList);
    Collections.sort(sortedList);
    
    EList<String> eList = new BasicEList<String>(initialList);
    assertTrue(TestUtil.areEqual(initialList, eList));
    ECollections.sort(eList);
    assertTrue(TestUtil.areEqual(sortedList, eList));
        
    sortedList = new ArrayList<String>(initialList);
    Collections.sort(sortedList, comparator);
    
    eList = new BasicEList<String>(initialList);
    assertTrue(TestUtil.areEqual(initialList, eList));
    ECollections.sort(eList, comparator);
    assertTrue(TestUtil.areEqual(sortedList, eList));    

    initialList = new ArrayList<String>();
    initialList.add("k");
    initialList.add("f");
    initialList.add("b");
    initialList.add("z");
    initialList.add("a");
    sortedList = new ArrayList<String>(initialList);
    Collections.sort(sortedList);
    
    eList = new UniqueEList<String>(initialList);
    assertTrue(TestUtil.areEqual(initialList, eList));
    ECollections.sort(eList);
    assertTrue(TestUtil.areEqual(sortedList, eList));

    sortedList = new ArrayList<String>(initialList);
    Collections.sort(sortedList, comparator);
    
    eList = new BasicEList<String>(initialList);
    assertTrue(TestUtil.areEqual(initialList, eList));
    ECollections.sort(eList, comparator);
    assertTrue(TestUtil.areEqual(sortedList, eList));    
  }
  
  public void testIndexOf1()
  {
    EList<String> eList = new BasicEList<String>();
    eList.add("k");
    eList.add("a");
    eList.add("f");
    eList.add("k");
    eList.add("b");
    eList.add("z");
    eList.add("a");
    eList.add("b");
    
    assertEquals(0, ECollections.indexOf(eList, "k", -1));
    assertEquals(-1, ECollections.indexOf(eList, "k", 10));
    assertEquals(0, ECollections.indexOf(eList, "k", 0));
    assertEquals(1, ECollections.indexOf(eList, "a", 0));
    assertEquals(2, ECollections.indexOf(eList, "f", 0));
    assertEquals(3, ECollections.indexOf(eList, "k", 1));
    assertEquals(3, ECollections.indexOf(eList, "k", 3));
    assertEquals(4, ECollections.indexOf(eList, "b", 0));
    assertEquals(5, ECollections.indexOf(eList, "z", 0));
    assertEquals(6, ECollections.indexOf(eList, "a", 3));
    assertEquals(7, ECollections.indexOf(eList, "b", 5));
    
    eList = new UniqueEList<String>();
    eList.add("k");
    eList.add("a");
    eList.add("f");
    eList.add("k");
    eList.add("b");
    eList.add("z");
    eList.add("a");
    eList.add("b");    

    assertEquals(0, ECollections.indexOf(eList, "k", -1));
    assertEquals(-1, ECollections.indexOf(eList, "k", 10));
    assertEquals(0, ECollections.indexOf(eList, "k", 0));
    assertEquals(1, ECollections.indexOf(eList, "a", 0));
    assertEquals(2, ECollections.indexOf(eList, "f", 0));
    assertEquals(-1, ECollections.indexOf(eList, "k", 1));
    assertEquals(-1, ECollections.indexOf(eList, "k", 3));
    assertEquals(3, ECollections.indexOf(eList, "b", 0));
    assertEquals(4, ECollections.indexOf(eList, "z", 0));
    assertEquals(-1, ECollections.indexOf(eList, "a", 3));
    assertEquals(-1, ECollections.indexOf(eList, "b", 5));
  }
  
  public void testIndexOf2()
  {
    assertIndexOf(populateList(new ArrayList<Object>()));
    assertIndexOf(populateList(new LinkedList<Object>()));
    assertIndexOf(populateList(new BasicEList<Object>()));
  }
  
  public void testSetEList()
  {
    assertSetEList(populateList(new ArrayList<Object>()));
    assertSetEList(populateList(new LinkedList<Object>()));
    assertSetEList(populateList(new UniqueEList<Object>()));
  }  
  
  protected List<Object> populateList(List<Object> list)
  {
    list.add(null);            //0
    list.add(Boolean.FALSE);   //1
    list.add(1);  //2
    list.add(2);  //3
    list.add(null);            //4
    list.add("String");        //5
    list.add(1);  //6
    list.add("String");        //7
    list.add(Boolean.FALSE);   //8

    return list;
  }
  
  protected void assertIndexOf(List<?> list)
  {
    assertEquals(0, ECollections.indexOf(list, null, 0));
    assertEquals(4, ECollections.indexOf(list, null, 1));
    assertEquals(4, ECollections.indexOf(list, null, 4));
    
    assertEquals(1, ECollections.indexOf(list, Boolean.FALSE, 1));
    assertEquals(8, ECollections.indexOf(list, Boolean.FALSE, 2));
    assertEquals(-1, ECollections.indexOf(list, Boolean.FALSE, 9));
    assertEquals(1, ECollections.indexOf(list, Boolean.FALSE, -2));
    
    assertEquals(2, ECollections.indexOf(list, 1, 0));
    assertEquals(2, ECollections.indexOf(list, 1, 2));
    assertEquals(6, ECollections.indexOf(list, 1, 3));
    
    assertEquals(5, ECollections.indexOf(list, "String", 3));
    
    assertEquals(-1, ECollections.indexOf(list, null, 1000));
    assertEquals(-1, ECollections.indexOf(list, "String", 1000));
  }
    
  protected void assertSetEList(List<?> prototypeList)
  {
    EList<Object> eList = new BasicEList<Object>();
    ECollections.setEList(eList, prototypeList);
    assertTrue("Empty list test", TestUtil.areEqual(prototypeList, eList));
    
    eList = new BasicEList<Object>();
    eList.add(0, "String");
    eList.add(Boolean.FALSE);
    ECollections.setEList(eList, prototypeList);
    assertTrue("Smaller list test", TestUtil.areEqual(prototypeList, eList));
    
    eList = (EList<Object>)populateList(new BasicEList<Object>());
    ECollections.setEList(eList, prototypeList);
    assertTrue("Same list test", TestUtil.areEqual(prototypeList, eList));

    eList.remove(2);
    eList.add(3, this);
    ECollections.setEList(eList, prototypeList);
    assertTrue("Equal size list test", TestUtil.areEqual(prototypeList, eList));

    eList.add(0, "String");
    eList.add(2, Boolean.FALSE);
    eList.add(Boolean.FALSE);
    eList.add(this);
    ECollections.setEList(eList, prototypeList);
    assertTrue("Bigger list test", TestUtil.areEqual(prototypeList, eList));
  }
  
  /*
   * Bugzilla: 133907
   */
  public void testMoveIntInt() throws Exception
  {
    EList<Object> originalList = new BasicEList<Object>();
    originalList.add("pos0");
    originalList.add("pos1");
    originalList.add(2);
    originalList.add("pos3");
    
    EList<Object> eList = new BasicEList<Object>(originalList);
    List<Object> list = new ArrayList<Object>(originalList);
    
    int target = 2, source = 3;
    originalList.move(target, source);
    ECollections.move(eList, target, source);
    assertTrue(TestUtil.areEqual(originalList, eList));
    ECollections.move(list, target, source);
    assertTrue(TestUtil.areEqual(originalList, list));
    
    target = 2; source = 0;
    originalList.move(target, source);
    ECollections.move(eList, target, source);
    assertTrue(TestUtil.areEqual(originalList, eList));
    ECollections.move(list, target, source);
    assertTrue(TestUtil.areEqual(originalList, list));    

    target = 1; source = 1;
    originalList.move(target, source);
    ECollections.move(eList, target, source);
    assertTrue(TestUtil.areEqual(originalList, eList));
    ECollections.move(list, target, source);
    assertTrue(TestUtil.areEqual(originalList, list));    

    target = 0; source = 3;
    originalList.move(target, source);
    ECollections.move(eList, target, source);
    assertTrue(TestUtil.areEqual(originalList, eList));
    ECollections.move(list, target, source);
    assertTrue(TestUtil.areEqual(originalList, list));    
  }

  /*
   * Bugzilla: 133907
   */
  public void testMoveIntObject() throws Exception
  {
    EList<Object> originalList = new BasicEList<Object>();
    originalList.add("pos0");
    originalList.add("pos1");
    originalList.add(2);
    originalList.add("pos3");
    
    EList<Object> eList = new BasicEList<Object>(originalList);
    List<Object> list = new ArrayList<Object>(originalList);
    
    int target = 2; Object object = originalList.get(3);
    originalList.move(target, object);
    ECollections.move(eList, target, object);
    assertTrue(TestUtil.areEqual(originalList, eList));
    ECollections.move(list, target, object);
    assertTrue(TestUtil.areEqual(originalList, list));
    
    target = 2; object = originalList.get(0);
    originalList.move(target, object);
    ECollections.move(eList, target, object);
    assertTrue(TestUtil.areEqual(originalList, eList));
    ECollections.move(list, target, object);
    assertTrue(TestUtil.areEqual(originalList, list));    

    target = 1; object = originalList.get(1);
    originalList.move(target, object);
    ECollections.move(eList, target, object);
    assertTrue(TestUtil.areEqual(originalList, eList));
    ECollections.move(list, target, object);
    assertTrue(TestUtil.areEqual(originalList, list));    

    target = 0; object = originalList.get(3);
    originalList.move(target, object);
    ECollections.move(eList, target, object);
    assertTrue(TestUtil.areEqual(originalList, eList));
    ECollections.move(list, target, object);
    assertTrue(TestUtil.areEqual(originalList, list));    
  }
}
