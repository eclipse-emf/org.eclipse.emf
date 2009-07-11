/**
 * <copyright>
 *
 * Copyright (c) 2009 BestSolution and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EMFEditPropertiesTest.java,v 1.2 2009/07/11 11:59:24 tschindl Exp $
 */
package org.eclipse.emf.test.databinding.edit;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.databinding.emfdb.A;
import org.eclipse.emf.test.databinding.emfdb.B;
import org.eclipse.emf.test.databinding.emfdb.EmfdbFactory;
import org.eclipse.emf.test.databinding.emfdb.EmfdbPackage;

import junit.framework.TestCase;

public class EMFEditPropertiesTest extends TestCase
{
  private Resource resource;
  private EditingDomain editingDomain;
  private Realm testRealm;
  private boolean flag;
  private ListDiffEntry[] listEntries;
  
  @Override
  protected void setUp() throws Exception
  {
    super.setUp();
    
    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
    ResourceSet resourceSet = new ResourceSetImpl();
    BasicCommandStack commandStack = new BasicCommandStack();
    
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
      Resource.Factory.Registry.DEFAULT_EXTENSION,
      new XMIResourceFactoryImpl());
    editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, resourceSet);
    URI uri = URI.createFileURI(TestUtil.getPluginDirectory("org.eclipse.emf.test.databinding")+"/model/A.xmi");
    resource = resourceSet.getResource(uri, true);
    testRealm = new Realm()
    {
      
      @Override
      public boolean isCurrent()
      {
        return true;
      }
    };
    flag = false;
    listEntries = null;
  }
  
  @Override
  protected void tearDown() throws Exception
  {
    super.tearDown();
  }
  
//  public void testValueEditingDomainEStructuralFeature()
//  {
//    fail("Not yet implemented");
//  }
//
//  public void testValueEditingDomainFeaturePath()
//  {
//    fail("Not yet implemented");
//  }
//
//  public void testValuesEditingDomainEStructuralFeatureArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  public void testValuesEditingDomainFeaturePathArray()
//  {
//    fail("Not yet implemented");
//  }

  public void testListEditingDomainEStructuralFeature() {
    Realm.runWithDefault(testRealm, new Runnable()
      {
        
        public void run()
        {
          _testListEditingDomainEStructuralFeature();
        }
      });
  }
  
  public void _testListEditingDomainEStructuralFeature()
  {
    A a = (A)resource.getContents().get(0);
    IEMFEditListProperty prop = EMFEditProperties.list(editingDomain, EmfdbPackage.Literals.A__BLIST);
    IObservableList list = prop.observe(a);
    list.addListChangeListener(new IListChangeListener()
      {
        
        public void handleListChange(ListChangeEvent event)
        {
          flag = true;
          listEntries = event.diff.getDifferences();
        }
      });
    assertEquals(a.getBlist().size(),list.size());
    B b = EmfdbFactory.eINSTANCE.createB();
    a.getBlist().add(b);
    assertEquals(a.getBlist().size(),list.size());
    assertTrue(flag);
    assertNotNull(listEntries);
    assertEquals(1, listEntries.length);
    assertTrue(listEntries[0].isAddition());
    assertSame(b,listEntries[0].getElement());
    assertEquals(a.getBlist().size()-1,listEntries[0].getPosition());
    assertEquals(list.get(0), a.getBlist().get(0));
  }

//  public void testListEditingDomainFeaturePath()
//  {
//    fail("Not yet implemented");
//  }
//
//  public void testMultiListEditingDomainEStructuralFeatureArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  public void testMultiListEditingDomainFeaturePathEStructuralFeatureArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  public void testMultiListEditingDomainFeaturePathArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  public void testMultiListEditingDomainIEMFEditListPropertyArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  public void testMap()
//  {
//    fail("Not yet implemented");
//  }

}
