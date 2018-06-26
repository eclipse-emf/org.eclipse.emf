/**
 * Copyright (c) 2015 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.core.ecore;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
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


public class IterativeEContentAdapterTest
{
  public EContentAdapter eContentAdapter;

  private ResourceSet resourceSet;

  private Resource rootResource;

  private Resource childResource;

  private EPackage ePackage;

  private EPackage nestedEPackage;

  private Set<EPackage> deeplyNestedEPackages;

  @Before
  public void setup()
  {
    resourceSet = new ResourceSetImpl();

    rootResource = resourceSet.createResource(URI.createFileURI("Root.ecore"));
    childResource = resourceSet.createResource(URI.createFileURI("Child.ecore"));

    ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("MyPackage");
    ePackage.setNsPrefix("my_package");

    nestedEPackage = EcoreFactory.eINSTANCE.createEPackage();
    nestedEPackage.setName("MyPackage");
    nestedEPackage.setNsPrefix("my_package");

    rootResource.getContents().add(ePackage);
    childResource.getContents().add(nestedEPackage);

    deeplyNestedEPackages = new LinkedHashSet<EPackage>();
    EPackage parentEPackage = nestedEPackage;
    for (int i = 0; i < 5000; ++i)
    {
      EPackage deeplyNestedEPackage = EcoreFactory.eINSTANCE.createEPackage();
      deeplyNestedEPackage.setName("MyPackage" + i);
      deeplyNestedEPackage.setNsPrefix("my_package_" + i);
      parentEPackage.getESubpackages().add(deeplyNestedEPackage);
      deeplyNestedEPackages.add(deeplyNestedEPackage);
      parentEPackage = deeplyNestedEPackage;
    }

    eContentAdapter = new EContentAdapter()
      {
        @Override
        protected boolean useRecursion()
        {
          return false;
        }
      };

    resourceSet.eAdapters().add(eContentAdapter);
  }

  private void assertDeeplyNestedEPackagesAdapted(boolean adapted)
  {
    for (EPackage deeplyNestedEPackage : deeplyNestedEPackages)
    {
      if (adapted)
      {
        assertTrue("EContentAdapter expected", deeplyNestedEPackage.eAdapters().contains(eContentAdapter));
      }
      else
      {
        assertFalse("No EContentAdapter expected", deeplyNestedEPackage.eAdapters().contains(eContentAdapter));
      }
    }
  }

  @Test
  public void testAddingAlreadyAdaptedObjectToCrossContainmentReference()
  {
    EList<Adapter> eAdapters = nestedEPackage.eAdapters();
    int size = eAdapters.size();
    ePackage.getESubpackages().add(nestedEPackage);
    int expectedSize = eAdapters.size();
    assertEquals("No additional EContentAdapter is expected", expectedSize, size);
    assertDeeplyNestedEPackagesAdapted(true);
  }

  @Test
  public void testRemovingStillAdaptedObjectFromCrossContainmentReference()
  {
    ePackage.getESubpackages().add(nestedEPackage);

    EList<Adapter> eAdapters = nestedEPackage.eAdapters();
    int expectedSize = eAdapters.size();
    ePackage.getESubpackages().add(nestedEPackage);
    int size = eAdapters.size();
    assertEquals("No removal of EContentAdapter is expected", expectedSize, size);
    assertDeeplyNestedEPackagesAdapted(true);
  }

  @Test
  public void testAddingAlreadyAdaptedObjectToResourceContents()
  {
    EList<Adapter> eAdapters = nestedEPackage.eAdapters();
    int expectedSize = eAdapters.size();
    childResource.getContents().remove(nestedEPackage);
    int size = eAdapters.size();
    assertEquals("Removal of EContentAdapter is expected", expectedSize - 1, size);
    ePackage.getESubpackages().add(nestedEPackage);
    size = eAdapters.size();
    assertEquals("Addition of EContentAdapter is expected", expectedSize, size);
    childResource.getContents().add(nestedEPackage);
    size = eAdapters.size();
    assertEquals("No additional EContentAdapter is expected", expectedSize, size);
    assertDeeplyNestedEPackagesAdapted(true);
  }

  @Test
  public void testRemovingStillAdaptedObjectFromResourceContents()
  {
    ePackage.getESubpackages().add(nestedEPackage);

    EList<Adapter> eAdapters = nestedEPackage.eAdapters();
    int expectedSize = eAdapters.size();
    childResource.getContents().remove(nestedEPackage);
    int size = eAdapters.size();
    assertEquals("No removal of EContentAdapter is expected", expectedSize, size);
    assertDeeplyNestedEPackagesAdapted(true);
  }

  @Test
  public void testRemovingStillAdaptedObjectFromCrossContainmentReferenceAndResourceContents()
  {
    ePackage.getESubpackages().add(nestedEPackage);

    EList<Adapter> eAdapters = nestedEPackage.eAdapters();
    int expectedSize = eAdapters.size();
    ePackage.getESubpackages().remove(nestedEPackage);
    childResource.getContents().remove(nestedEPackage);
    int size = eAdapters.size();
    assertEquals("No removal of EContentAdapter is expected", expectedSize - 1, size);
    assertDeeplyNestedEPackagesAdapted(false);
  }

  @Test
  public void testProxyResolve()
  {
    EList<Adapter> eAdapters = nestedEPackage.eAdapters();
    int expectedSize = eAdapters.size();

    EPackage proxyEPackage = EcoreFactory.eINSTANCE.createEPackage();
    proxyEPackage.getESuperPackage();
    ((InternalEObject)proxyEPackage).eSetProxyURI(EcoreUtil.getURI(nestedEPackage));
    EList<Adapter> proxyEAdapters = proxyEPackage.eAdapters();
    int expectedProxySize = proxyEAdapters.size();
    ePackage.getESubpackages().add(proxyEPackage);
    assertTrue("Added proxy is still a proxy", proxyEPackage.eIsProxy());
    int proxySize = proxyEAdapters.size();
    assertEquals("One EContentAdapter is expected", expectedProxySize + 1, proxySize);

    // Resolve the proxy.
    ePackage.getESubpackages().get(0);
    proxySize = proxyEAdapters.size();
    assertEquals("No EContentAdapter is expected", expectedProxySize, proxySize);

    int size = eAdapters.size();
    assertEquals("One EContentAdapter is expected", expectedSize, size);
    assertDeeplyNestedEPackagesAdapted(true);
  }

  @Test
  public void testRemoveContentAdapter()
  {
    EList<Adapter> childResourceEAdapters = childResource.eAdapters();
    ePackage.getESubpackages().add(nestedEPackage);
    EList<Adapter> eAdapters = nestedEPackage.eAdapters();
    int expectedSize = eAdapters.size();

    resourceSet.eAdapters().clear();
    assertEquals("No EContentAdapter is expected", expectedSize - 1, eAdapters.size());
    assertEquals("No EContentAdapter is expected", 0, childResourceEAdapters.size());
    assertDeeplyNestedEPackagesAdapted(false);
  }

  @Test
  public void testRemoveResource()
  {
    EList<Adapter> childResourceEAdapters = childResource.eAdapters();
    ePackage.getESubpackages().add(nestedEPackage);
    EList<Adapter> eAdapters = nestedEPackage.eAdapters();
    int expectedSize = eAdapters.size();

    resourceSet.getResources().remove(childResource);
    assertEquals("One EContentAdapter is expected", expectedSize, eAdapters.size());
    assertEquals("No EContentAdapter is expected", 0, childResourceEAdapters.size());
    assertDeeplyNestedEPackagesAdapted(true);
  }

  @Test
  public void testRemoveOfDeeplyNestedEPackage()
  {
    int limit = deeplyNestedEPackages.size() / 2;
    for (EPackage deeplyNestedEPackage : deeplyNestedEPackages)
    {
      if (--limit == 0)
      {
        assertTrue("EContentAdapter expected", deeplyNestedEPackage.eAdapters().contains(eContentAdapter));
        deeplyNestedEPackage.getESuperPackage().getESubpackages().remove(deeplyNestedEPackage);
        assertFalse("No EContentAdapter expected", deeplyNestedEPackage.eAdapters().contains(eContentAdapter));
      }
      else if (limit < 0)
      {
        if (limit == -1000)
        {
          assertFalse("No EContentAdapter expected", deeplyNestedEPackage.eAdapters().contains(eContentAdapter));
          Resource resource = resourceSet.createResource(URI.createURI("newResource.ecore"));
          assertTrue("EContentAdapter expected", resource.eAdapters().contains(eContentAdapter));
          resource.getContents().add(deeplyNestedEPackage);
          assertTrue("EContentAdapter expected", deeplyNestedEPackage.eAdapters().contains(eContentAdapter));
        }
        else if (limit < -1000)
        {
          assertTrue("EContentAdapter expected", deeplyNestedEPackage.eAdapters().contains(eContentAdapter));
        }
        else
        {
          assertFalse("No EContentAdapter expected", deeplyNestedEPackage.eAdapters().contains(eContentAdapter));
        }
      }
      else
      {
        assertTrue("EContentAdapter expected", deeplyNestedEPackage.eAdapters().contains(eContentAdapter));
      }
    }
  }
}
