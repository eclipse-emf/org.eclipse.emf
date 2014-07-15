/**
 * Copyright (c) 2014 CEA and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;
import org.eclipse.emf.test.core.xrefsmodel.A;
import org.eclipse.emf.test.core.xrefsmodel.XRefsModelPackage;
import org.eclipse.emf.test.core.xrefsmodel.util.XRefsModelUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ECrossReferenceAdapterTest
{
  private EPackage testPackage;

  private EAttribute mapAttribute;

  private ECrossReferenceAdapterFixture fixture;

  /**
   * Tests the filtered intrinsic cross-reference iterator for a resolving cross-referencer.
   */
  @Test
  public void testCrossReferenceIterator_resolving()
  {
    A a0 = loadXRefsInstance();

    List<EStructuralFeature> expectedEntries = new ArrayList<EStructuralFeature>(Arrays.asList(
      XRefsModelPackage.Literals.A__OTHERS,
      XRefsModelPackage.Literals.A__OTHERS,
      XRefsModelPackage.Literals.A__NON_OTHERS));
    List<String> expectedNames = new ArrayList<String>(Arrays.asList("a1", "a2", "a3"));
    for (EContentsEList.FeatureIterator<EObject> iter = fixture.getCrossReferences(a0); iter.hasNext();)
    {
      A next = (A)iter.next();
      EStructuralFeature feature = iter.feature();

      // Assert that we don't get an unexpected entry
      assertTrue(expectedEntries.remove(feature));

      // Assert that we get the expected object
      assertFalse(next.eIsProxy());
      assertEquals(expectedNames.remove(0), next.getName());
    }

    // Assert that all of the expected entries were found
    assertTrue(expectedEntries.isEmpty());
  }

  /**
   * Tests the filtered intrinsic cross-reference iterator for a non-resolving cross-referencer.
   */
  @Test
  public void testCrossReferenceIterator_nonResolving()
  {
    // Don't resolve cross-references
    fixture.setResolve(false);

    A a0 = loadXRefsInstance();

    List<EStructuralFeature> expectedEntries = new ArrayList<EStructuralFeature>(Arrays.asList(
      XRefsModelPackage.Literals.A__OTHERS,
      XRefsModelPackage.Literals.A__OTHERS,
      XRefsModelPackage.Literals.A__NON_OTHERS));
    List<String> expectedURIs = new ArrayList<String>(Arrays.asList("xrefs1.xmi#/0", "xrefs1.xmi#/1", "xrefs1.xmi#/2"));
    for (EContentsEList.FeatureIterator<EObject> iter = fixture.getCrossReferences(a0); iter.hasNext();)
    {
      A next = (A)iter.next();
      EStructuralFeature feature = iter.feature();

      // Assert that we don't get an unexpected entry
      assertTrue(expectedEntries.remove(feature));

      // Assert that we get the expected object
      assertTrue(next.eIsProxy());
      assertEquals(expectedURIs.remove(0), EcoreUtil.getURI(next).deresolve(a0.eResource().getURI()).toString());
    }

    // Assert that all of the expected entries were found
    assertTrue(expectedEntries.isEmpty());
  }

  /**
   * Tests the wrapped intrinsic cross-reference iterator for a resolving cross-referencer. This also tests that
   * its implementation of {@link EContentsEList.FeatureIterator#feature()} is consistent.
   */
  @Test
  public void testCrossReferenceIterator_resolving_wrapper()
  {
    XRefsModelUtil.setWrapCrossReferenceIterators(true);

    A a0 = loadXRefsInstance();

    List<EStructuralFeature> expectedEntries = new ArrayList<EStructuralFeature>(Arrays.asList(
      XRefsModelPackage.Literals.A__OTHERS,
      XRefsModelPackage.Literals.A__OTHERS,
      XRefsModelPackage.Literals.A__NON_OTHERS));
    List<String> expectedNames = new ArrayList<String>(Arrays.asList("a1", "a2", "a3"));
    for (EContentsEList.FeatureIterator<EObject> iter = fixture.getCrossReferences(a0); iter.hasNext();)
    {
      A next = (A)iter.next();
      EStructuralFeature feature = iter.feature();

      // Assert that we don't get an unexpected entry
      assertTrue(expectedEntries.remove(feature));

      // Assert that we get the expected object
      assertFalse(next.eIsProxy());
      assertEquals(expectedNames.remove(0), next.getName());
    }

    // Assert that all of the expected entries were found
    assertTrue(expectedEntries.isEmpty());

    // And that we couldn't avoid calling the derived A::getAllOthers() exactly twice:
    // once for eIsSet() and once for eGet()
    assertEquals(2, XRefsModelUtil.getAllOthersCallCount());
  }

  /**
   * Tests the wrapped intrinsic cross-reference iterator for a non-resolving cross-referencer. This also tests that
   * its implementation of {@link EContentsEList.FeatureIterator#feature()} is consistent.
   */
  @Test
  public void testCrossReferenceIterator_nonResolving_wrapper()
  {
    XRefsModelUtil.setWrapCrossReferenceIterators(true);

    // Don't resolve cross-references
    fixture.setResolve(false);

    A a0 = loadXRefsInstance();

    List<EStructuralFeature> expectedEntries = new ArrayList<EStructuralFeature>(Arrays.asList(
      XRefsModelPackage.Literals.A__OTHERS,
      XRefsModelPackage.Literals.A__OTHERS,
      XRefsModelPackage.Literals.A__NON_OTHERS));
    List<String> expectedURIs = new ArrayList<String>(Arrays.asList("xrefs1.xmi#/0", "xrefs1.xmi#/1", "xrefs1.xmi#/2"));
    for (EContentsEList.FeatureIterator<EObject> iter = fixture.getCrossReferences(a0); iter.hasNext();)
    {
      A next = (A)iter.next();
      EStructuralFeature feature = iter.feature();

      // Assert that we don't get an unexpected entry
      assertTrue(expectedEntries.remove(feature));

      // Assert that we get the expected object
      assertTrue(next.eIsProxy());
      assertEquals(expectedURIs.remove(0), EcoreUtil.getURI(next).deresolve(a0.eResource().getURI()).toString());
    }

    // Assert that all of the expected entries were found
    assertTrue(expectedEntries.isEmpty());

    // And that we couldn't avoid calling the derived A::getAllOthers() exactly twice:
    // once for eIsSet() and once for eGet()
    assertEquals(2, XRefsModelUtil.getAllOthersCallCount());
  }

  /**
   * Control test case: ETypedElements referencing types simply by eType.
   */
  @Test
  public void testSimpleTypeTypeReferencesDoNotLeak()
  {
    Resource resource = new ResourceImpl(URI.createURI("http:///bogus/testpackage.ecore"));
    resource.getContents().add(testPackage);
    resource.eAdapters().add(fixture);
    EcoreUtil.resolveAll(resource);

    // sanity check
    assertSame(EcorePackage.Literals.ESTRING, mapAttribute.getEType());

    fixture.assertCrossReferenceMapNotEmpty();
    fixture.assertCrossReferenced(mapAttribute, EcorePackage.Literals.ESTRING);

    resource.unload();

    fixture.assertCrossReferenceMapEmpty();
  }

  /**
   * Memory leak scenario:  ETypedElements referencing types via eGenericType
   *
   * @see https://bugs.eclipse.org/bugs/show_bug.cgi?id=433027
   */
  @Test
  public void testGenericTypeTypeReferencesDoNotLeak()
  {
    // change the map attribute to a generic type
    mapAttribute.setEType(null);
    EGenericType genType = EcoreFactory.eINSTANCE.createEGenericType();
    mapAttribute.setEGenericType(genType);
    genType.setEClassifier(EcorePackage.Literals.EMAP);
    EGenericType k = EcoreFactory.eINSTANCE.createEGenericType();
    genType.getETypeArguments().add(k);
    k.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
    EGenericType t = EcoreFactory.eINSTANCE.createEGenericType();
    genType.getETypeArguments().add(t);
    t.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);

    Resource resource = new ResourceImpl(URI.createURI("http:///bogus/testpackage.ecore"));
    resource.getContents().add(testPackage);
    resource.eAdapters().add(fixture);
    EcoreUtil.resolveAll(resource);

    // sanity check
    assertSame(EcorePackage.Literals.EMAP, mapAttribute.getEType());

    fixture.assertCrossReferenceMapNotEmpty();
    fixture.assertCrossReferenced(mapAttribute, EcorePackage.Literals.EMAP);

    resource.unload();

    fixture.assertCrossReferenceMapEmpty();
  }

  //
  // Test framework
  //

  @Before
  public void setUp()
  {
    // We must never call the A::getAllOthers() derived reference accessor when cross-referencing
    XRefsModelUtil.assertNoAllOthersCalls(true);

    fixture = new ECrossReferenceAdapterFixture();

    testPackage = EcoreFactory.eINSTANCE.createEPackage();
    testPackage.setName("test");
    testPackage.setNsPrefix("test");
    testPackage.setNsURI("http://testpackage");

    EClass foo = EcoreFactory.eINSTANCE.createEClass();
    testPackage.getEClassifiers().add(foo);
    foo.setName("Foo");

    mapAttribute = EcoreFactory.eINSTANCE.createEAttribute();
    foo.getEStructuralFeatures().add(mapAttribute);
    mapAttribute.setName("map");
    mapAttribute.setEType(EcorePackage.Literals.ESTRING);
  }

  @After
  public void tearDown()
  {
    XRefsModelUtil.assertNoAllOthersCalls(false);

    // In case it was set by a test case
    XRefsModelUtil.setWrapCrossReferenceIterators(false);

    testPackage = null;
    mapAttribute = null;
    fixture = null;
  }

  A loadXRefsInstance()
  {
    ResourceSet rset = new ResourceSetImpl();
    rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    rset.getPackageRegistry().put(XRefsModelPackage.eNS_URI, XRefsModelPackage.eINSTANCE);
    Resource resource = rset.getResource(URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xrefs0.xmi"), true);
    return (A)resource.getContents().get(0);
  }

  static class ECrossReferenceAdapterFixture extends ECrossReferenceAdapter
  {
    private boolean resolve = true;

    class InverseCrossReferencerFixture extends InverseCrossReferencer
    {
      private static final long serialVersionUID = 1L;

      @Override
      protected EContentsEList.FeatureIterator<EObject> getCrossReferences(EObject eObject)
      {
        return super.getCrossReferences(eObject);
      }
    }

    @Override
    protected InverseCrossReferencer createInverseCrossReferencer()
    {
      return new InverseCrossReferencerFixture();
    }

    EContentsEList.FeatureIterator<EObject> getCrossReferences(EObject eObject)
    {
      return ((InverseCrossReferencerFixture)inverseCrossReferencer).getCrossReferences(eObject);
    }

    void setResolve(boolean resolve)
    {
      this.resolve = resolve;
    }

    @Override
    protected boolean resolve()
    {
      return resolve;
    }

    void assertCrossReferenced(ETypedElement typedElement, EClassifier type)
    {
      Collection<EStructuralFeature.Setting> settings = getInverseReferences(type);
      for (EStructuralFeature.Setting next : settings)
      {
        if ((next.getEObject() == typedElement) && (next.getEStructuralFeature() == EcorePackage.Literals.ETYPED_ELEMENT__ETYPE))
        {
          return;
        }
      }

      fail("Cross-reference not found for ETypedElement::eType");
    }

    void assertCrossReferenceMapNotEmpty()
    {
      assertFalse("Cross-reference map is empty", inverseCrossReferencer.isEmpty());
    }

    void assertCrossReferenceMapEmpty()
    {
      assertTrue("Cross-reference map is not empty", inverseCrossReferencer.isEmpty());
    }
  }
}
