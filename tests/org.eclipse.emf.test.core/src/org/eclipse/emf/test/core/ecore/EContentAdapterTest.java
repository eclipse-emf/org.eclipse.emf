/**
 * Copyright (c) 2015 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.ecore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Test;

public class EContentAdapterTest
{
  private ResourceSet resourceSet;
  private Resource rootResource;
  private Resource childResource;
  private EPackage ePackage;
  private EClass eClass;

  @Before
  public void setup()
  {
    resourceSet = new ResourceSetImpl();

    rootResource = resourceSet.createResource(URI.createFileURI("Root.ecore"));
    childResource = resourceSet.createResource(URI.createFileURI("Child.ecore"));

    ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("MyPackage");
    ePackage.setNsPrefix("my_package");
    eClass = EcoreFactory.eINSTANCE.createEClass();

    rootResource.getContents().add(ePackage);
    childResource.getContents().add(eClass);

    resourceSet.eAdapters().add(new EContentAdapter());
  }

  @Test
  public void testAddingAlreadyAdaptedObjectToCrossContainmentReference()
  {
    EList<Adapter> eAdapters = eClass.eAdapters();
    int size = eAdapters.size();
    ePackage.getEClassifiers().add(eClass);
    int expectedSize = eAdapters.size();
    assertEquals("No additional EContentAdapter is expected", expectedSize, size);
  }

  @Test
  public void testRemovingStillAdaptedObjectFromCrossContainmentReference() {
    ePackage.getEClassifiers().add(eClass);

    EList<Adapter> eAdapters = eClass.eAdapters();
    int expectedSize = eAdapters.size();
    ePackage.getEClassifiers().add(eClass);
    int size = eAdapters.size();
    assertEquals("No removal of EContentAdapter is expected", expectedSize, size);
  }

  @Test
  public void testAddingAlreadyAdaptedObjectToResourceContents()
  {
    EList<Adapter> eAdapters = eClass.eAdapters();
    int expectedSize = eAdapters.size();
    childResource.getContents().remove(eClass);
    int size = eAdapters.size();
    assertEquals("Removal of EContentAdapter is expected", expectedSize - 1, size);
    ePackage.getEClassifiers().add(eClass);
    size = eAdapters.size();
    assertEquals("Addition of EContentAdapter is expected", expectedSize, size);
    childResource.getContents().add(eClass);
    size = eAdapters.size();
    assertEquals("No additional EContentAdapter is expected", expectedSize, size);
  }
  
  @Test
  public void testRemovingStillAdaptedObjectFromResourceContents() {
    ePackage.getEClassifiers().add(eClass);

    EList<Adapter> eAdapters = eClass.eAdapters();
    int expectedSize = eAdapters.size();
    childResource.getContents().remove(eClass);
    int size = eAdapters.size();
    assertEquals("No removal of EContentAdapter is expected", expectedSize, size);
  }
  
  @Test
  public void testRemovingStillAdaptedObjectFromCrossContainmentReferenceAndResourceContents() {
    ePackage.getEClassifiers().add(eClass);

    EList<Adapter> eAdapters = eClass.eAdapters();
    int expectedSize = eAdapters.size();
    ePackage.getEClassifiers().remove(eClass);
    childResource.getContents().remove(eClass);
    int size = eAdapters.size();
    assertEquals("No removal of EContentAdapter is expected", expectedSize - 1, size);
  }
  
  @Test
  public void testProxyResolve() {
    EList<Adapter> eAdapters = eClass.eAdapters();
    int expectedSize = eAdapters.size();
    
    EClass proxyEClass = EcoreFactory.eINSTANCE.createEClass();
    proxyEClass.getESuperTypes();
    ((InternalEObject)proxyEClass).eSetProxyURI(EcoreUtil.getURI(eClass));
    EList<Adapter> proxyEAdapters = proxyEClass.eAdapters();
    int expectedProxySize = proxyEAdapters.size();
    ePackage.getEClassifiers().add(proxyEClass);
    assertTrue("Added proxy is still a proxy", proxyEClass.eIsProxy());
    int proxySize = proxyEAdapters.size();
    assertEquals("One EContentAdapter is expected", expectedProxySize + 1, proxySize);
    
    // Resolve the proxy.
    ePackage.getEClassifiers().get(0);
    proxySize = proxyEAdapters.size();
    assertEquals("No EContentAdapter is expected", expectedProxySize, proxySize);
    
    int size = eAdapters.size();
    assertEquals("One EContentAdapter is expected", expectedSize, size);
  }
  
  @Test
  public void testRemoveContentAdapter() {
    EList<Adapter> childResourceEAdapters = childResource.eAdapters();
    ePackage.getEClassifiers().add(eClass);
    EList<Adapter> eAdapters = eClass.eAdapters();
    int expectedSize = eAdapters.size();
    
    resourceSet.eAdapters().clear();
    assertEquals("No EContentAdapter is expected", expectedSize-1, eAdapters.size());
    assertEquals("No EContentAdapter is expected", 0, childResourceEAdapters.size());
  }
  
  @Test
  public void testRemoveResource() {
    EList<Adapter> childResourceEAdapters = childResource.eAdapters();
    ePackage.getEClassifiers().add(eClass);
    EList<Adapter> eAdapters = eClass.eAdapters();
    int expectedSize = eAdapters.size();
    
    resourceSet.getResources().remove(childResource);
    assertEquals("One EContentAdapter is expected", expectedSize, eAdapters.size());
    assertEquals("No EContentAdapter is expected", 0, childResourceEAdapters.size());
  }
}
