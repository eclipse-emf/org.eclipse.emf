/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.ecore;



import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.test.common.reification.Bidirectional;
import org.eclipse.emf.test.common.reification.BoundedGenericContainer;
import org.eclipse.emf.test.common.reification.Container;
import org.eclipse.emf.test.common.reification.HighBoundedContainer;
import org.eclipse.emf.test.common.reification.HighListKeyValuePair;
import org.eclipse.emf.test.common.reification.IntegerListKeyValuePair;
import org.eclipse.emf.test.common.reification.KeyValuePairContainer;
import org.eclipse.emf.test.common.reification.Link;
import org.eclipse.emf.test.common.reification.LinkItem;
import org.eclipse.emf.test.common.reification.Low;
import org.eclipse.emf.test.common.reification.Medium;
import org.eclipse.emf.test.common.reification.MediumBidirectional;
import org.eclipse.emf.test.common.reification.MediumLink;
import org.eclipse.emf.test.common.reification.MediumUnboundedContainer;
import org.eclipse.emf.test.common.reification.RawKeyValuePair;
import org.eclipse.emf.test.common.reification.RawLink;
import org.eclipse.emf.test.common.reification.RawUnboundedContainer;
import org.eclipse.emf.test.common.reification.ReificationFactory;
import org.eclipse.emf.test.common.reification.ReificationPackage;
import org.eclipse.emf.test.common.reification.Root;
import org.eclipse.emf.test.common.reification.StringListKeyValuePair;
import org.eclipse.emf.test.common.reification.StringListKeyValuePairContainer;
import org.eclipse.emf.test.common.reification.UnboundedGenericContainer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ReificationTest extends TestCase
{
  public ReificationTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ReificationTest");

    ts.addTest(new ReificationTest("testAttribute"));
    ts.addTest(new ReificationTest("testReference"));

    ts.addTest(new ReificationTest("testUnboundedGenericContainerWithRawType"));
    ts.addTest(new ReificationTest("testUnboundedGenericContainerWithWildcard"));
    ts.addTest(new ReificationTest("testUnboundedGenericContainerWithSuper"));
    ts.addTest(new ReificationTest("testUnboundedGenericContainerWithExtends"));

    ts.addTest(new ReificationTest("testBoundedGenericContainerWithRawType"));
    ts.addTest(new ReificationTest("testBoundedGenericContainerWithWildcard"));
    ts.addTest(new ReificationTest("testBoundedGenericContainerWithSuper"));
    ts.addTest(new ReificationTest("testBoundedGenericContainerWithExtends"));

    ts.addTest(new ReificationTest("testRawKeyValuePair"));
    ts.addTest(new ReificationTest("testStringListKeyValuePair"));
    ts.addTest(new ReificationTest("testIntegerListKeyValuePair"));

    ts.addTest(new ReificationTest("testBidirectional"));
    ts.addTest(new ReificationTest("testLink"));
    ts.addTest(new ReificationTest("testMediumBidirectional"));

    ts.addTest(new ReificationTest("testStatic"));
    ts.addTest(new ReificationTest("testDynamic"));

    return ts;
  }

  public void testAttribute()
  {
    Root root = ReificationFactory.eINSTANCE.createRoot();
    root.setName("root");
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__NAME).isInstance("root"));
    // root.setName(Boolean.TRUE);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__NAME).isInstance(Boolean.TRUE));
  }

  public void testReference()
  {
    Root root = ReificationFactory.eINSTANCE.createRoot();
    Container container = ReificationFactory.eINSTANCE.createContainer();
    root.setContainer(container);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__CONTAINER).isInstance(container));
    // root.setContainer(root);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__CONTAINER).isInstance(root));
  }

  public void testUnboundedGenericContainerWithRawType()
  {
    Root root = ReificationFactory.eINSTANCE.createRoot();

    UnboundedGenericContainer<Object> container = ReificationFactory.eINSTANCE.createUnboundedGenericContainer();
    root.setUnboundedGenericContainerWithRawType(container);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE).isInstance(container));
    // root.setUnboundedGenericContainerWithRawType(root);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE).isInstance(root));

    container.setContent(ReificationFactory.eINSTANCE.createRoot());
    assertTrue(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER.getFeatureType(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(ReificationFactory.eINSTANCE.createRoot()));
    assertTrue(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER.getFeatureType(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER__CONTENT).isInstance("name"));
  }

  public void testUnboundedGenericContainerWithWildcard()
  {
    Root root = ReificationFactory.eINSTANCE.createRoot();

    UnboundedGenericContainer<Object> container = ReificationFactory.eINSTANCE.createUnboundedGenericContainer();
    root.setUnboundedGenericContainerWithWildcard(container);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD).isInstance(container));
    // root.setUnboundedGenericContainerWithWildcard(root);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD).isInstance(root));

    container.setContent(ReificationFactory.eINSTANCE.createRoot());
    assertTrue(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER.getFeatureType(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(ReificationFactory.eINSTANCE.createRoot()));
    assertTrue(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER.getFeatureType(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER__CONTENT).isInstance("name"));
  }

  @SuppressWarnings("unchecked")
  public void testUnboundedGenericContainerWithSuper()
  {
    Root root = ReificationFactory.eINSTANCE.createRoot();
    UnboundedGenericContainer<Object> container = ReificationFactory.eINSTANCE.createUnboundedGenericContainer();

    root.setUnboundedGenericContainerWithSuper(container);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER).isInstance(container));
    // root.setUnboundedGenericContainerWithSuper(root);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER).isInstance(root));

    MediumUnboundedContainer container2 = ReificationFactory.eINSTANCE.createMediumUnboundedContainer();
    root.setUnboundedGenericContainerWithSuper(container2);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER).isInstance(container2));

    RawUnboundedContainer container3 = ReificationFactory.eINSTANCE.createRawUnboundedContainer();
    root.setUnboundedGenericContainerWithSuper(container3);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER).isInstance(container3));

    // container2.setContent(ReificationFactory.eINSTANCE.createRoot());
    assertFalse(ReificationPackage.Literals.MEDIUM_UNBOUNDED_CONTAINER.getFeatureType(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(ReificationFactory.eINSTANCE.createRoot()));
    // container2.setContent("name");
    assertFalse(ReificationPackage.Literals.MEDIUM_UNBOUNDED_CONTAINER.getFeatureType(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER__CONTENT).isInstance("name"));
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void testUnboundedGenericContainerWithExtends()
  {
    Root root = ReificationFactory.eINSTANCE.createRoot();
    UnboundedGenericContainer container = ReificationFactory.eINSTANCE.createUnboundedGenericContainer();

    root.setUnboundedGenericContainerWithExtends(container);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS).isInstance(container));
    // root.setUnboundedGenericContainerWithExtends(root);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS).isInstance(root));

    MediumUnboundedContainer container2 = ReificationFactory.eINSTANCE.createMediumUnboundedContainer();
    root.setUnboundedGenericContainerWithExtends(container2);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS).isInstance(container2));

    RawUnboundedContainer container3 = ReificationFactory.eINSTANCE.createRawUnboundedContainer();
    root.setUnboundedGenericContainerWithExtends(container3);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS).isInstance(container3));

    //container2.setContent(ReificationFactory.eINSTANCE.createRoot());
    assertFalse(ReificationPackage.Literals.MEDIUM_UNBOUNDED_CONTAINER.getFeatureType(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(ReificationFactory.eINSTANCE.createRoot()));
    // container2.setContent("name");
    assertFalse(ReificationPackage.Literals.MEDIUM_UNBOUNDED_CONTAINER.getFeatureType(ReificationPackage.Literals.UNBOUNDED_GENERIC_CONTAINER__CONTENT).isInstance("name"));
  }

  //
  public void testBoundedGenericContainerWithRawType()
  {
    Root root = ReificationFactory.eINSTANCE.createRoot();

    BoundedGenericContainer<Medium> container = ReificationFactory.eINSTANCE.createBoundedGenericContainer();
    root.setBoundedGenericContainerWithRawType(container);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE).isInstance(container));
    // root.setBoundedGenericContainerWithRawType(root);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE).isInstance(root));

    Medium medium = ReificationFactory.eINSTANCE.createMedium();
    container.setContent(medium);
    assertTrue(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(medium));

    Low low = ReificationFactory.eINSTANCE.createLow();
    // container.setContent(low);
    assertFalse(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(low));

    // container.setContent(ReificationFactory.eINSTANCE.createRoot());
    assertFalse(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(ReificationFactory.eINSTANCE.createRoot()));
    // container.setContent("name");
    assertFalse(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance("name"));
  }

  public void testBoundedGenericContainerWithWildcard()
  {
    Root root = ReificationFactory.eINSTANCE.createRoot();

    BoundedGenericContainer<Medium> container = ReificationFactory.eINSTANCE.createBoundedGenericContainer();
    root.setBoundedGenericContainerWithWildcard(container);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD).isInstance(container));
    // root.setBoundedGenericContainerWithWildcard(root);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD).isInstance(root));

    // container.setContent(ReificationFactory.eINSTANCE.createRoot());
    assertFalse(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(ReificationFactory.eINSTANCE.createRoot()));
    // container.setContent("name");
    assertFalse(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance("name"));
  }

  public void testBoundedGenericContainerWithSuper()
  {
    Root root = ReificationFactory.eINSTANCE.createRoot();
    BoundedGenericContainer<Medium> container = ReificationFactory.eINSTANCE.createBoundedGenericContainer();

    root.setBoundedGenericContainerWithSuper(container);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER).isInstance(container));
    // root.setBoundedGenericContainerWithSuper(root);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER).isInstance(root));

    HighBoundedContainer container2 = ReificationFactory.eINSTANCE.createHighBoundedContainer();
    // root.setBoundedGenericContainerWithSuper(container2);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER).isInstance(container2));

    container2.setContent(ReificationFactory.eINSTANCE.createHigh());
    assertTrue(ReificationPackage.Literals.HIGH_BOUNDED_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(ReificationFactory.eINSTANCE.createHigh()));
    // container2.setContent(ReificationFactory.eINSTANCE.createRoot());
    assertFalse(ReificationPackage.Literals.HIGH_BOUNDED_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(ReificationFactory.eINSTANCE.createRoot()));
    // container2.setContent("name");
    assertFalse(ReificationPackage.Literals.HIGH_BOUNDED_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance("name"));
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void testBoundedGenericContainerWithExtends()
  {
    Root root = ReificationFactory.eINSTANCE.createRoot();
    BoundedGenericContainer container = ReificationFactory.eINSTANCE.createBoundedGenericContainer();

    root.setBoundedGenericContainerWithExtends(container);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS).isInstance(container));
    // root.setBoundedGenericContainerWithExtends(root);
    assertFalse(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS).isInstance(root));

    HighBoundedContainer container2 = ReificationFactory.eINSTANCE.createHighBoundedContainer();
    root.setBoundedGenericContainerWithExtends(container2);
    assertTrue(ReificationPackage.Literals.ROOT.getFeatureType(ReificationPackage.Literals.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS).isInstance(container2));

    container2.setContent(ReificationFactory.eINSTANCE.createHigh());
    assertTrue(ReificationPackage.Literals.HIGH_BOUNDED_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(ReificationFactory.eINSTANCE.createHigh()));
    //container2.setContent(ReificationFactory.eINSTANCE.createRoot());
    assertFalse(ReificationPackage.Literals.HIGH_BOUNDED_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance(ReificationFactory.eINSTANCE.createRoot()));
    // container2.setContent("name");
    assertFalse(ReificationPackage.Literals.HIGH_BOUNDED_CONTAINER.getFeatureType(ReificationPackage.Literals.BOUNDED_GENERIC_CONTAINER__CONTENT).isInstance("name"));
  }

  @SuppressWarnings("unchecked")
  public void testRawKeyValuePair()
  {
    RawKeyValuePair keyValuePair1 = ReificationFactory.eINSTANCE.createRawKeyValuePair();
    keyValuePair1.setKey(ECollections.asEList(new String[] { "name" }));
    assertTrue(ReificationPackage.Literals.RAW_KEY_VALUE_PAIR.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR__KEY).isInstance(ECollections.asEList(new String[] { "name" })));

    RawKeyValuePair keyValuePair2 = ReificationFactory.eINSTANCE.createRawKeyValuePair();
    keyValuePair2.setKey(ECollections.asEList(new Integer[] { 0 }));
    assertTrue(ReificationPackage.Literals.RAW_KEY_VALUE_PAIR.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR__KEY).isInstance(ECollections.asEList(new Integer[] { 0 })));

    keyValuePair1.setValue(keyValuePair2);
    assertTrue(ReificationPackage.Literals.RAW_KEY_VALUE_PAIR.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR__VALUE).isInstance(keyValuePair2));

    @SuppressWarnings("rawtypes")
    KeyValuePairContainer container = ReificationFactory.eINSTANCE.createKeyValuePairContainer();
    container.getKeyValuePairsList().add(keyValuePair1);
    assertTrue(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS).isInstance(keyValuePair1));
  }

  @SuppressWarnings("unchecked")
  public void testStringListKeyValuePair()
  {
    StringListKeyValuePair keyValuePair1 = ReificationFactory.eINSTANCE.createStringListKeyValuePair();
    keyValuePair1.setKey(ECollections.asEList(new String[] { "name" }));
    assertTrue(ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR__KEY).isInstance(ECollections.asEList(new String[] { "name" })));

    StringListKeyValuePair keyValuePair2 = ReificationFactory.eINSTANCE.createStringListKeyValuePair();
    // keyValuePair2.setKey(ECollections.asEList(new Integer[] { 0 }));
    // No reified information.
    assertTrue(ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR__KEY).isInstance(ECollections.asEList(new Integer[] { 0 })));

    keyValuePair1.setValue(keyValuePair2);
    assertTrue(ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR__VALUE).isInstance(keyValuePair2));

    StringListKeyValuePairContainer container = ReificationFactory.eINSTANCE.createStringListKeyValuePairContainer();
    container.getKeyValuePairsList().add(keyValuePair1);
    assertTrue(ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR_CONTAINER.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS).isInstance(keyValuePair1));

    // container.getKeyValuePairs().add(ReificationFactory.eINSTANCE.createKeyValuePair());
    // No reified information.
    assertTrue(ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR_CONTAINER.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS).isInstance(ReificationFactory.eINSTANCE.createKeyValuePair()));

    container.getKeyValuePairsList().add(ReificationFactory.eINSTANCE.createRawKeyValuePair());
    assertTrue(ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR_CONTAINER.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS).isInstance(ReificationFactory.eINSTANCE.createRawKeyValuePair()));
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void testIntegerListKeyValuePair()
  {
    IntegerListKeyValuePair keyValuePair1 = ReificationFactory.eINSTANCE.createIntegerListKeyValuePair();
    keyValuePair1.setKey(ECollections.asEList(new Integer[] { 0 }));
    assertTrue(ReificationPackage.Literals.INTEGER_LIST_KEY_VALUE_PAIR.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR__KEY).isInstance(ECollections.asEList(new Integer[] { 0 })));

    StringListKeyValuePair keyValuePair2 = ReificationFactory.eINSTANCE.createStringListKeyValuePair();

    // keyValuePair1.setValue(keyValuePair2);
    assertFalse(ReificationPackage.Literals.INTEGER_LIST_KEY_VALUE_PAIR.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR__VALUE).isInstance(keyValuePair2));

    // StringListKeyValuePairContainer container = ReificationFactory.eINSTANCE.createStringListKeyValuePairContainer();
    // container.getKeyValuePairs().add(keyValuePair1);
    assertFalse(ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR_CONTAINER.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS).isInstance(keyValuePair1));

    // StringListKeyValuePairContainer container = ReificationFactory.eINSTANCE.createStringListKeyValuePairContainer();
    // container.getKeyValuePairs().add(ReificationFactory.eINSTANCE.createTListKeyValuePair());
    assertFalse(ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR_CONTAINER.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS).isInstance(ReificationFactory.eINSTANCE.createTListKeyValuePair()));

    // StringListKeyValuePairContainer container = ReificationFactory.eINSTANCE.createStringListKeyValuePairContainer();
    // container.getKeyValuePairs().add(ReificationFactory.eINSTANCE.createBoundedTListKeyValuePair());
    assertFalse(ReificationPackage.Literals.STRING_LIST_KEY_VALUE_PAIR_CONTAINER.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS).isInstance(ReificationFactory.eINSTANCE.createBoundedTListKeyValuePair()));

    KeyValuePairContainer container = ReificationFactory.eINSTANCE.createKeyValuePairContainer();
    HighListKeyValuePair keyValuePair3 = ReificationFactory.eINSTANCE.createHighListKeyValuePair();
    container.getKeyValuePairsList().add(keyValuePair3);
    assertTrue(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS).isInstance(keyValuePair3));

    HighListKeyValuePair keyValuePair4 = ReificationFactory.eINSTANCE.createHighListKeyValuePair();
    keyValuePair4.setValue(keyValuePair3);
    assertTrue(ReificationPackage.Literals.HIGH_LIST_KEY_VALUE_PAIR.getFeatureType(ReificationPackage.Literals.KEY_VALUE_PAIR_CONTAINER__KEY_VALUE_PAIRS).isInstance(keyValuePair3));
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void testBidirectional()
  {
    Bidirectional bidirectional = ReificationFactory.eINSTANCE.createBidirectional();
    Bidirectional bidirectional2 = ReificationFactory.eINSTANCE.createBidirectional();
    bidirectional.setIn(bidirectional2);
    assertTrue(ReificationPackage.Literals.BIDIRECTIONAL.getFeatureType(ReificationPackage.Literals.BIDIRECTIONAL__IN).isInstance(bidirectional2));

    RawLink rawLink = ReificationFactory.eINSTANCE.createRawLink();
    bidirectional.setOut(rawLink);
    assertTrue(ReificationPackage.Literals.BIDIRECTIONAL.getFeatureType(ReificationPackage.Literals.BIDIRECTIONAL__OUT).isInstance(rawLink));

    LinkItem linkItem = ReificationFactory.eINSTANCE.createLinkItem();
    try
    {
      bidirectional.setOut(linkItem);
      fail("ClassClassCastException expected");
    }
    catch (ClassCastException exception)
    {
      // Expected.
    }
    assertTrue(ReificationPackage.Literals.BIDIRECTIONAL.getFeatureType(ReificationPackage.Literals.BIDIRECTIONAL__OUT).isInstance(linkItem));
  }

  @SuppressWarnings("rawtypes")
  public void testLink()
  {
    Link link = ReificationFactory.eINSTANCE.createLink();
    Link link2 = ReificationFactory.eINSTANCE.createLink();
    link.setIn(link2);
    assertTrue(ReificationPackage.Literals.LINK.getFeatureType(ReificationPackage.Literals.BIDIRECTIONAL__IN).isInstance(link2));

    Bidirectional bidirectional = ReificationFactory.eINSTANCE.createBidirectional();
    // link.setIn(bidirectional);
    assertFalse(ReificationPackage.Literals.LINK.getFeatureType(ReificationPackage.Literals.BIDIRECTIONAL__IN).isInstance(bidirectional));

    LinkItem linkItem = ReificationFactory.eINSTANCE.createLinkItem();
    // link.setOut(linkItem);
    assertFalse(ReificationPackage.Literals.LINK.getFeatureType(ReificationPackage.Literals.BIDIRECTIONAL__OUT).isInstance(linkItem));
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void testMediumBidirectional()
  {
    MediumBidirectional link = ReificationFactory.eINSTANCE.createMediumBidirectional();
    MediumBidirectional link2 = ReificationFactory.eINSTANCE.createMediumBidirectional();
    // link.setIn(link2);
    assertFalse(ReificationPackage.Literals.MEDIUM_BIDIRECTIONAL.getFeatureType(ReificationPackage.Literals.BIDIRECTIONAL__IN).isInstance(link2));

    link.getContentsList().add(link2);
    assertTrue(ReificationPackage.Literals.MEDIUM_BIDIRECTIONAL.getFeatureType(ReificationPackage.Literals.MEDIUM_BIDIRECTIONAL__CONTENTS).isInstance(link2));

    MediumLink link3 = ReificationFactory.eINSTANCE.createMediumLink();
    link.getContentsList().add(link3);
    assertTrue(ReificationPackage.Literals.MEDIUM_BIDIRECTIONAL.getFeatureType(ReificationPackage.Literals.MEDIUM_BIDIRECTIONAL__CONTENTS).isInstance(link3));

    link3.getContentsList().add(link2);
    assertTrue(ReificationPackage.Literals.MEDIUM_LINK.getFeatureType(ReificationPackage.Literals.MEDIUM_BIDIRECTIONAL__CONTENTS).isInstance(link2));
  }

  public void testDynamic()
  {
    testPackage(EcoreUtil.copy(ReificationPackage.eINSTANCE));
  }

  public void testStatic()
  {
    testPackage(ReificationPackage.eINSTANCE);
  }
  
  public void testPackage(EPackage ePackage)
  {
    List<EObject> eObjects = new BasicEList<EObject>();
    for (EClassifier eClassifer : ePackage.getEClassifiers())
    {
      if (eClassifer instanceof EClass)
      {
        EClass eClass = (EClass)eClassifer;
        if (!eClass.isAbstract())
        {
          eObjects.add(EcoreUtil.create(eClass));
        }
      }
    }

    for (EObject eObject : eObjects)
    {
      EClass eClass = eObject.eClass();
      for (EReference eReference : eClass.getEAllReferences())
      {
        EGenericType eGenericType = eClass.getFeatureType(eReference);
        for (EObject otherEObject : eObjects)
        {
          // The instance of test for the raw type should never be false when the test on the generic type returns true.
          // I.e., generic type instance of testing should always be more restricted.
          //
          boolean isRawInstance = eReference.getEReferenceType().isInstance(otherEObject);
          boolean isGenericInstance = eGenericType.isInstance(otherEObject);
          if (!isRawInstance && isGenericInstance)
          {
            fail();
          }
        }
      }
    }
  }
}