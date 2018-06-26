/**
 * Copyright (c) 2017 Eclipse Contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 */
package org.eclipse.emf.test.core.ecore;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class ECrossReferenceAdapterStressTest
{
  @Parameters(name = "{0}")
  public static Collection<TestableECrossReferenceAdapter> eCrossReferenceAdapters()
  {
    return Arrays.asList(new TestableECrossReferenceAdapter(), new TestableECrossReferenceAdapter()
      {
        @Override
        protected boolean useRecursion()
        {
          return false;
        }

        @Override
        public String toString()
        {
          return "iterative";
        }
      });
  }

  private List<EObject> instances;

  private int referenceCount;

  private ResourceSet resourceSet;

  private List<EReference> eReferences;

  @Parameter
  public TestableECrossReferenceAdapter eCrossReferenceAdapter;

  @Test
  public void testConsistentCrossReferences()
  {
    resourceSet.eAdapters().add(eCrossReferenceAdapter);

    eCrossReferenceAdapter.testProperlyFull();

    for (EObject eObject : instances)
    {
      for (int i = eReferences.size(); --i >= 0;)
      {
        @SuppressWarnings("unchecked")
        List<EObject> list = (List<EObject>)eObject.eGet(eReferences.get(i));
        List<EObject> removedEObjects = new ArrayList<EObject>();
        removedEObjects.add(list.remove(3));
        removedEObjects.add(list.remove(0));
        removedEObjects.add(list.remove(list.size() - 1));
        list.addAll(removedEObjects);
        eCrossReferenceAdapter.testProperlyFull();
      }
    }

    for (EObject eObject : instances)
    {
      for (EReference eReference : eReferences)
      {
        @SuppressWarnings("unchecked")
        List<EObject> list = (List<EObject>)eObject.eGet(eReference);
        list.remove(3);
        list.remove(0);
        list.remove(list.size() - 1);
        list.clear();
      }
    }

    eCrossReferenceAdapter.testEmpty();
  }

  @Before
  public void setUp()
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("_");
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("_");
    ePackage.getEClassifiers().add(eClass);
    EList<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
    eReferences = new ArrayList<EReference>();
    referenceCount = 50;
    for (int i = 0; i < referenceCount; ++i)
    {
      EReference eReference = EcoreFactory.eINSTANCE.createEReference();
      eReference.setName("_" + i);
      eReference.setEType(eClass);
      eReference.setUpperBound(-1);
      eStructuralFeatures.add(eReference);
      eReferences.add(eReference);
    }

    EReference containmentReference = EcoreFactory.eINSTANCE.createEReference();
    containmentReference.setContainment(true);
    containmentReference.setName("contents");
    containmentReference.setEType(eClass);
    eStructuralFeatures.add(containmentReference);

    instances = new ArrayList<EObject>();
    List<EObject> proxies = new ArrayList<EObject>();
    resourceSet = new ResourceSetImpl();
    EFactory eFactory = ePackage.getEFactoryInstance();
    for (int i = 0; i < 2; ++i)
    {
      Resource resource = resourceSet.createResource(URI.createURI("resource" + i + ".xmi"));
      EList<EObject> contents = resource.getContents();
      for (int j = 0; j < 100; ++j)
      {
        EObject eObject = eFactory.create(eClass);

        if (j == 0 && "iterative".equals(eCrossReferenceAdapter.toString()))
        {
          EObject container = eObject;
          for (int k = 0; k < 5000; ++k)
          {
            EObject child = eFactory.create(eClass);
            container.eSet(containmentReference, child);
            container = child;
          }
        }

        contents.add(eObject);
        instances.add(eObject);

        EObject proxy = eFactory.create(eClass);
        ((InternalEObject)proxy).eSetProxyURI(EcoreUtil.getURI(eObject));
        proxies.add(proxy);
      }
    }

    for (EObject eObject : instances)
    {
      for (EReference eReference : eReferences)
      {
        @SuppressWarnings("unchecked")
        List<EObject> list = (List<EObject>)eObject.eGet(eReference);
        list.addAll(proxies);
      }
    }

    eCrossReferenceAdapter.setECrossReferenceAdapterStressTest(this);
  }

  @After
  public void tearDown()
  {
  }

  private static class TestableECrossReferenceAdapter extends ECrossReferenceAdapter
  {
    private ECrossReferenceAdapterStressTest eCrossReferenceAdapterStressTest;

    public void setECrossReferenceAdapterStressTest(ECrossReferenceAdapterStressTest eCrossReferenceAdapterStressTest)
    {
      this.eCrossReferenceAdapterStressTest = eCrossReferenceAdapterStressTest;

    }

    public void testProperlyFull()
    {
      assertEquals("The inverse cross referencer isn't of the expected size", eCrossReferenceAdapterStressTest.instances.size(), inverseCrossReferencer.size());
      for (Map.Entry<EObject, Collection<Setting>> entry : inverseCrossReferencer.entrySet())
      {
        assertEquals("", eCrossReferenceAdapterStressTest.instances.size() * eCrossReferenceAdapterStressTest.referenceCount, entry.getValue().size());
      }
    }

    public void testEmpty()
    {
      assertEquals("The inverse cross referencer isn't empty", 0, inverseCrossReferencer.size());
    }

    @Override
    public String toString()
    {
      return "recursive";
    }
  }
}
