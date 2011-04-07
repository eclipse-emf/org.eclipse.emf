/**
 * <copyright>
 *
 * Copyright (c) 2011 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Ed Merks - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ListDifferenceAnalyzerTest.java,v 1.1 2011/04/07 23:41:08 emerks Exp $
 */
package org.eclipse.emf.test.core.change;

import java.util.Random;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeKind;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.change.impl.ListChangeImpl;
import org.eclipse.emf.ecore.change.util.ListDifferenceAnalyzer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListDifferenceAnalyzerTest extends TestCase
{
  public ListDifferenceAnalyzerTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("ListDifferenceAnalyzer Test");
    ts.addTest(new ListDifferenceAnalyzerTest("testRandom"));
    return ts;
  }
  
  public void testRandom()
  {
    ListDifferenceAnalyzer listDifferenceAnalyzer = 
      new ListDifferenceAnalyzer()
    {
      @Override
      protected ListChange createListChange(EList<ListChange> listChanges, ChangeKind kind, int index)
      {
        ListChange listChange =  
          new ListChangeImpl() 
          {
            @Override
            public EStructuralFeature getFeature()
            {
              return EcorePackage.Literals.ETYPED_ELEMENT__LOWER_BOUND;
            } 
          };
        listChange.setKind(kind);
        listChange.setIndex(index);
        listChanges.add(listChange);
        return listChange;
      } 
    };
    
    Random random = 
      new Random(0)
      {
        private static final long serialVersionUID = 1L;

        @Override
        public int nextInt() 
        {
          int result = super.nextInt();
          return result < 0 ? -result : result;
        }
      };
    
    int repetitions = 50000;
    for (int repeat = 0; repeat < repetitions; ++repeat)
    {
      EList<Object> oldList = new BasicEList<Object>();
      int size = random.nextInt() % 100;
      for (int i = 0; i < size; ++i)
      {
        oldList.add(random.nextInt() % size);
      }
      EList<Object> newList = new BasicEList<Object>(oldList);
      
      int removeCount = 0;
      int addCount = 0;
      for (int i = 0; i < size; i += 2)
      {
        switch (random.nextInt() % 3)
        {
          case 0:
          {
            newList.remove(random.nextInt() % newList.size());
            ++removeCount;
            break;
          }
          case 1:
          {
            newList.move(random.nextInt() % newList.size(), random.nextInt() % newList.size());
            break;
          }
          case 2:
          {
            newList.add(random.nextInt() % (newList.size() + 1), random.nextInt() % size);
            ++addCount;
            break;
          }
        }
      }
  
      int deltaRemoveCount = 0;
      int deltaAddCount = 0;
      EList<ListChange> changes = listDifferenceAnalyzer.analyzeLists(oldList, newList);
      for (ListChange listChange : changes)
      {
        switch (listChange.getKind())
        {
          case REMOVE_LITERAL:
          {
            ++deltaRemoveCount;
            break;
          }
          case MOVE_LITERAL:
          {
            break;
          }
          case ADD_LITERAL:
          {
            ++deltaAddCount;
            break;
          }
          
        }
        listChange.apply(oldList);
      }
      
      assertTrue(deltaRemoveCount <= removeCount);
      assertTrue(deltaAddCount <= addCount);
      
      /*
      try
      {
        // Create a resource set to hold the resources.
        //
        ResourceSet resourceSet = new ResourceSetImpl();
        
        // Register the appropriate resource factory to handle all file extensions.
        //
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
          (Resource.Factory.Registry.DEFAULT_EXTENSION, 
           new XMIResourceFactoryImpl());
  
        Resource changeResource = resourceSet.createResource(URI.createURI("http:///My.test"));
        changeResource.getContents().addAll(changes);
        changeResource.save(System.out, null);
      }
      catch (Exception exception)
      {
      }
      */
      
      assertEquals(newList, oldList);
    }
  }
  
}
