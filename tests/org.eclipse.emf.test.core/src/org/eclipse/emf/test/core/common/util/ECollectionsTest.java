/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id$
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
import org.eclipse.emf.test.core.TestUtil;

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
    return ts;
  }

  public void testSortEList()
  {
    Comparator comparator = new Comparator()
    {
      public int compare(Object o1,Object o2)
      {
        Comparable c1 = (Comparable)o1;
        Comparable c2 = (Comparable)o2;
        return -1 * c1.compareTo(c2);
      }
      
      public boolean equals(Object obj)
      {
        return obj == this;
      }
    };
    
    List initialList = new ArrayList();
    initialList.add("k");
    initialList.add("a");
    initialList.add("f");
    initialList.add("b");
    initialList.add("z");
    initialList.add("a");
    initialList.add("b");
    List sortedList = new ArrayList(initialList);
    Collections.sort(sortedList);
    
    EList eList = new BasicEList(initialList);
    assertTrue(TestUtil.areEqual(initialList, eList));
    ECollections.sort(eList);
    assertTrue(TestUtil.areEqual(sortedList, eList));
        
    sortedList = new ArrayList(initialList);
    Collections.sort(sortedList, comparator);
    
    eList = new BasicEList(initialList);
    assertTrue(TestUtil.areEqual(initialList, eList));
    ECollections.sort(eList, comparator);
    assertTrue(TestUtil.areEqual(sortedList, eList));    

    initialList = new ArrayList();
    initialList.add("k");
    initialList.add("f");
    initialList.add("b");
    initialList.add("z");
    initialList.add("a");
    sortedList = new ArrayList(initialList);
    Collections.sort(sortedList);
    
    eList = new UniqueEList(initialList);
    assertTrue(TestUtil.areEqual(initialList, eList));
    ECollections.sort(eList);
    assertTrue(TestUtil.areEqual(sortedList, eList));

    sortedList = new ArrayList(initialList);
    Collections.sort(sortedList, comparator);
    
    eList = new BasicEList(initialList);
    assertTrue(TestUtil.areEqual(initialList, eList));
    ECollections.sort(eList, comparator);
    assertTrue(TestUtil.areEqual(sortedList, eList));    
  }
  
  public void testIndexOf1()
  {
    EList eList = new BasicEList();
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
    
    eList = new UniqueEList();
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
    assertIndexOf(populateList(new ArrayList()));
    assertIndexOf(populateList(new LinkedList()));
    assertIndexOf(populateList(new BasicEList()));
  }
  
  public void testSetEList()
  {
    assertSetEList(populateList(new ArrayList()));
    assertSetEList(populateList(new LinkedList()));
    assertSetEList(populateList(new UniqueEList()));
  }  
  
  protected List populateList(List list)
  {
    list.add(null);            //0
    list.add(Boolean.FALSE);   //1
    list.add(new Integer(1));  //2
    list.add(new Integer(2));  //3
    list.add(null);            //4
    list.add("String");        //5
    list.add(new Integer(1));  //6
    list.add("String");        //7
    list.add(Boolean.FALSE);   //8

    return list;
  }
  
  protected void assertIndexOf(List list)
  {
    assertEquals(0, ECollections.indexOf(list, null, 0));
    assertEquals(4, ECollections.indexOf(list, null, 1));
    assertEquals(4, ECollections.indexOf(list, null, 4));
    
    assertEquals(1, ECollections.indexOf(list, Boolean.FALSE, 1));
    assertEquals(8, ECollections.indexOf(list, Boolean.FALSE, 2));
    assertEquals(-1, ECollections.indexOf(list, Boolean.FALSE, 9));
    assertEquals(1, ECollections.indexOf(list, Boolean.FALSE, -2));
    
    assertEquals(2, ECollections.indexOf(list, new Integer(1), 0));
    assertEquals(2, ECollections.indexOf(list, new Integer(1), 2));
    assertEquals(6, ECollections.indexOf(list, new Integer(1), 3));
    
    assertEquals(5, ECollections.indexOf(list, "String", 3));
    
    assertEquals(-1, ECollections.indexOf(list, null, 1000));
    assertEquals(-1, ECollections.indexOf(list, "String", 1000));
  }
    
  protected void assertSetEList(List prototypeList)
  {
    EList eList = new BasicEList();
    ECollections.setEList(eList, prototypeList);
    assertTrue("Empty list test", TestUtil.areEqual(prototypeList, eList));
    
    eList = new BasicEList();
    eList.add(0, "String");
    eList.add(Boolean.FALSE);
    ECollections.setEList(eList, prototypeList);
    assertTrue("Smaller list test", TestUtil.areEqual(prototypeList, eList));
    
    eList = (EList)populateList(new BasicEList());
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
}
