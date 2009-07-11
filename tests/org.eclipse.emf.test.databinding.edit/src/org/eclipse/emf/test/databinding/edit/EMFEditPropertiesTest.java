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
 * $Id: EMFEditPropertiesTest.java,v 1.1 2009/07/11 11:14:50 tschindl Exp $
 */
package org.eclipse.emf.test.databinding.edit;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;

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
import org.eclipse.emf.test.databinding.emfdb.EmfdbPackage;

import junit.framework.TestCase;

public class EMFEditPropertiesTest extends TestCase
{
  private Resource resource;
  private EditingDomain editingDomain;
  private Realm testRealm;
  
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
    
    assertEquals(a.getBlist().size(),list.size());
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
