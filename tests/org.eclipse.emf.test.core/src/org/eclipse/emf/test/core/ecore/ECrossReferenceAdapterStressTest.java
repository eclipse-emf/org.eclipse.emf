/**
 * Copyright (c) 2017 Eclipse Contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */
package org.eclipse.emf.test.core.ecore;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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


public class ECrossReferenceAdapterStressTest
{
  @Test
  public void testConsistentCrossReferences()
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("_");
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("_");
    ePackage.getEClassifiers().add(eClass);
    EList<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
    List<EReference> eReferences = new ArrayList<EReference>();
    final int referenceCount = 50;
    for (int i = 0; i < referenceCount; ++i)
    {
      EReference eReference = EcoreFactory.eINSTANCE.createEReference();
      eReference.setName("_" + i);
      eReference.setEType(eClass);
      eReference.setUpperBound(-1);
      eStructuralFeatures.add(eReference);
      eReferences.add(eReference);
    }

    final List<EObject> instances = new ArrayList<EObject>();
    List<EObject> proxies = new ArrayList<EObject>();
    ResourceSet resourceSet = new ResourceSetImpl();
    EFactory eFactory = ePackage.getEFactoryInstance();
    for (int i = 0; i < 2; ++i)
    {
      Resource resource = resourceSet.createResource(URI.createURI("resource" + i + ".xmi"));
      EList<EObject> contents = resource.getContents();
      for (int j = 0; j < 100; ++j)
      {
        EObject eObject = eFactory.create(eClass);
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

    class TestableECrossReferenceAdapter extends ECrossReferenceAdapter
    {
      public void testProperlyFull()
      {
        assertEquals("The inverse cross referencer isn't of the expected size", instances.size(), inverseCrossReferencer.size());
        for (Map.Entry<EObject, Collection<Setting>> entry : inverseCrossReferencer.entrySet())
        {
          assertEquals("", instances.size() * referenceCount, entry.getValue().size());
        }
      }
      public void testEmpty()
      {
        assertEquals("The inverse cross referencer isn't empty", 0, inverseCrossReferencer.size());
      }
    }

    TestableECrossReferenceAdapter eCrossReferenceAdapter = new TestableECrossReferenceAdapter();
    resourceSet.eAdapters().add(eCrossReferenceAdapter);

    eCrossReferenceAdapter.testProperlyFull();

    for (EObject eObject : instances)
    {
      for (int i = eReferences.size(); --i  >= 0 ; )
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
  }

  @After
  public void tearDown()
  {
  }
}
