/**
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Thales - initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;


import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;
import org.eclipse.emf.test.core.xrefsopposite.A;
import org.eclipse.emf.test.core.xrefsopposite.APkg;
import org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage;
import org.eclipse.emf.test.core.xrefsopposite.impl.AImpl;
import org.eclipse.emf.test.core.xrefsopposite.impl.APkgImpl;
import org.eclipse.emf.test.core.xrefsopposite.impl.XRefsOppositeFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class ensures that ECrossReferenceAdapter.getInverseReferences(object, feature) is properly working
 */
public class ECrossReferenceAdapterByFeatureTest
{
  private ECrossReferenceAdapter fixture;

  private HashMap<EStructuralFeature, Integer> countByFeature = new HashMap<EStructuralFeature, Integer>();

  public int count(EStructuralFeature feature) {
    if (countByFeature.containsKey(feature)) {
      return countByFeature.get(feature);
    }
    return 0;
  }

  public void increment(EStructuralFeature feature) {
    if (!countByFeature.containsKey(feature)) {
      countByFeature.put(feature, Integer.valueOf(0));
    }
    countByFeature.put(feature, countByFeature.get(feature) + 1);
  }

  public void resetCounter() {
    countByFeature.clear();
  }

  @Test
  public void testCrossReferenceByFeature()
  {
    Resource resource = loadXRefsInstance();
    APkg apkg = (APkg)resource.getEObject("apkg");
    A a0 = (A)resource.getEObject("a0");
    A a1 = (A)resource.getEObject("a1");
    A a2 = (A)resource.getEObject("a2");

    resetCounter();

    //check that getInverseReferences(obj) will access to all navigable references opposites
    assertInverse(a1, XRefsOppositePackage.Literals.A__REF1, a0);
    assertTrue(count(XRefsOppositePackage.Literals.A__OREF1) == 1);
    assertTrue(count(XRefsOppositePackage.Literals.A__OREF2) == 1);
    assertTrue(count(XRefsOppositePackage.Literals.A__REF3) == 0);

    resetCounter();

    //check that getInverseReferences(obj, feature) access only to the expected feature
    assertInverseFeature(a1, XRefsOppositePackage.Literals.A__REF1, a0);
    assertTrue(count(XRefsOppositePackage.Literals.A__OREF1) == 1);
    assertTrue(count(XRefsOppositePackage.Literals.A__OREF2) == 0);
    assertTrue(count(XRefsOppositePackage.Literals.A__REF3) == 0);
    assertTrue(count(XRefsOppositePackage.Literals.A__OWNING_APKG) == 0);

    resetCounter();

    //check non navigable references access only to the crossReferencer inner map
    assertInverseFeature(a1, XRefsOppositePackage.Literals.A__REF3, a2);
    assertTrue(count(XRefsOppositePackage.Literals.A__OREF1) == 0);
    assertTrue(count(XRefsOppositePackage.Literals.A__OREF2) == 0);
    assertTrue(count(XRefsOppositePackage.Literals.A__REF3) == 0);
    assertTrue(count(XRefsOppositePackage.Literals.A__OWNING_APKG) == 0);

    resetCounter();

    //check containment feature doens't access to others references
    assertInverseFeature(apkg, XRefsOppositePackage.Literals.A__OWNING_APKG, a0, a1, a2);
    assertTrue(count(XRefsOppositePackage.Literals.A__OREF1) == 0);
    assertTrue(count(XRefsOppositePackage.Literals.A__OREF2) == 0);
    assertTrue(count(XRefsOppositePackage.Literals.A__REF3) == 0);
    assertTrue(count(XRefsOppositePackage.Literals.A__OWNING_APKG) == 3);

  }

  /**
   * Returns whether the getInverseReferences(object, feature) returns the expected results
   */
  private void assertInverseFeature(EObject object, EReference feature, EObject ... expectedResults)
  {
    Collection<Setting> settings = fixture.getInverseReferences(object, feature, true);
    assertTrue(!settings.isEmpty());
    for (EStructuralFeature.Setting setting : settings)
    {
      assertTrue(setting.getEStructuralFeature() == feature);
      assertTrue(Arrays.asList(expectedResults).contains(setting.getEObject()));
    }
  }

  /**
   * Returns whether the getInverseReferences(object) returns the expected results
   */
  private void assertInverse(A object, EReference feature, EObject ... expectedResults)
  {
    Collection<Setting> settings = fixture.getInverseReferences(object, true);
    assertTrue(!settings.isEmpty());
    for (EStructuralFeature.Setting setting : settings)
    {
      if (feature == setting.getEStructuralFeature())
      {
        assertTrue(setting.getEStructuralFeature() == feature);
        assertTrue(Arrays.asList(expectedResults).contains(setting.getEObject()));
      }
    }
  }

  //
  // Test framework
  //

  @Before
  public void setUp()
  {
    // Create a fresh new instance the same as the parameter.
    fixture = new ECrossReferenceAdapter();
  }

  @After
  public void tearDown()
  {
    fixture = null;
  }

  private Resource loadXRefsInstance()
  {
    ResourceSet rset = new ResourceSetImpl();
    rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    rset.getPackageRegistry().put(XRefsOppositePackage.eNS_URI, XRefsOppositePackage.eINSTANCE);
    XRefsOppositePackage.eINSTANCE.setEFactoryInstance(new XRefsOppositeFactoryImpl() {

      @Override
      public APkg createAPkg()
      {
        return new APkgImpl() {
          
        };
      }
      
      @Override
      public A createA()
      {
        return new AImpl() {
          @Override
          public Setting eSetting(EStructuralFeature eFeature)
          {
            increment(eFeature);
            return super.eSetting(eFeature);
          }
          
          @Override
          public Object eGet(int featureID, boolean resolve, boolean coreType)
          {
            EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
            increment(eFeature);
            return super.eGet(featureID, resolve, coreType);
          }
        };
      }
    });

    Resource resource = rset.getResource(URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xrefsopposite.xmi"), true);
    resource.eAdapters().add(fixture);
    EcoreUtil.resolveAll(resource);

    return resource;
  }
}
