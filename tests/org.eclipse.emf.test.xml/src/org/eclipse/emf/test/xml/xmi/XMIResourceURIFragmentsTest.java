/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.xml.xmi;


import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class XMIResourceURIFragmentsTest
{
  private static final EClass NODE_CLASS;

  private static final EReference NODES_REFERENCE;

  static
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("nodes");
    ePackage.setNsPrefix("nodes");
    ePackage.setNsURI("nodes");

    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Node");
    ePackage.getEClassifiers().add(eClass);
    NODE_CLASS = eClass;

    EReference eReference = EcoreFactory.eINSTANCE.createEReference();
    eReference.setName("nodes");
    eReference.setEType(eClass);
    eReference.setContainment(true);
    eReference.setUpperBound(-1);
    eClass.getEStructuralFeatures().add(eReference);
    NODES_REFERENCE = eReference;
  }

  private Resource resource;

  private Resource resourceWithRelativeIDFragments;

  @Before
  public void setUp()
  {
    resource = new XMIResourceImpl();
    resource.setURI(URI.createURI("resource.xmi"));

    resourceWithRelativeIDFragments = new XMIResourceImpl()
      {
        @Override
        protected boolean supportIDRelativeURIFragmentPaths()
        {
          return true;
        }
      };
    resourceWithRelativeIDFragments.setURI(URI.createURI("resourceWithRelativeIDFragments.xmi"));
  }

  protected EObject createNode()
  {
    return EcoreUtil.create(NODE_CLASS);
  }

  protected void addChild(EObject node, EObject child)
  {
    @SuppressWarnings("unchecked")
    List<EObject> nodes = (List<EObject>)node.eGet(NODES_REFERENCE);
    nodes.add(child);
  }

  protected void setID(EObject node, String id)
  {
    ((XMLResource)node.eResource()).setID(node, id);
  }

  @Test
  public void testSingleRoot()
  {
    EObject node = createNode();
    resource.getContents().add(node);
    Assert.assertEquals("/", resource.getURIFragment(node));
    Assert.assertSame(node, resource.getEObject("/"));
  }

  @Test
  public void testSingleRootWithID()
  {
    EObject node = createNode();
    resource.getContents().add(node);
    setID(node, "root");
    Assert.assertEquals("root", resource.getURIFragment(node));
    Assert.assertSame(node, resource.getEObject("root"));
  }

  @Test
  public void testMultipleRoot()
  {
    EObject node0 = createNode();
    resource.getContents().add(node0);
    EObject node1 = createNode();
    resource.getContents().add(node1);
    Assert.assertEquals("/1", resource.getURIFragment(node1));
    Assert.assertSame(node1, resource.getEObject("/1"));
  }

  @Test
  public void testMultipleRootWithID()
  {
    EObject node0 = createNode();
    resource.getContents().add(node0);
    EObject node1 = createNode();
    resource.getContents().add(node1);
    setID(node1, "id");
    Assert.assertEquals("id", resource.getURIFragment(node1));
    Assert.assertSame(node1, resource.getEObject("id"));
  }

  @Test
  public void testNestedNode()
  {
    EObject node0 = createNode();
    resourceWithRelativeIDFragments.getContents().add(node0);
    EObject node1 = createNode();
    addChild(node0, node1);
    Assert.assertEquals("//@nodes.0", resourceWithRelativeIDFragments.getURIFragment(node1));
    Assert.assertSame(node1, resourceWithRelativeIDFragments.getEObject("//@nodes.0"));
  }

  @Test
  public void testNestedNodeWithID()
  {
    EObject node0 = createNode();
    resourceWithRelativeIDFragments.getContents().add(node0);
    EObject node1 = createNode();
    addChild(node0, node1);
    setID(node1, "id");
    Assert.assertEquals("id", resourceWithRelativeIDFragments.getURIFragment(node1));
    Assert.assertSame(node1, resourceWithRelativeIDFragments.getEObject("id"));
  }

  @Test
  public void testNestedNodeWithIDAndRootID()
  {
    EObject node0 = createNode();
    resourceWithRelativeIDFragments.getContents().add(node0);
    setID(node0, "id0");
    EObject node1 = createNode();
    addChild(node0, node1);
    setID(node1, "id1");
    Assert.assertEquals("id1", resourceWithRelativeIDFragments.getURIFragment(node1));
    Assert.assertSame(node1, resourceWithRelativeIDFragments.getEObject("id1"));
  }

  @Test
  public void testNestedNodeWithRootID()
  {
    EObject node0 = createNode();
    resourceWithRelativeIDFragments.getContents().add(node0);
    setID(node0, "id");
    EObject node1 = createNode();
    addChild(node0, node1);
    Assert.assertEquals("/?id/@nodes.0", resourceWithRelativeIDFragments.getURIFragment(node1));
    Assert.assertSame(node1, resourceWithRelativeIDFragments.getEObject("/?id/@nodes.0"));
  }

  @Test
  public void testNestedDeepNodeWithIntermediateID()
  {
    EObject node0 = createNode();
    resourceWithRelativeIDFragments.getContents().add(node0);
    EObject node1 = createNode();
    addChild(node0, node1);
    EObject node2 = createNode();
    addChild(node1, node2);
    setID(node2, "id");
    EObject node3 = createNode();
    addChild(node2, node3);
    Assert.assertEquals("/?id/@nodes.0", resourceWithRelativeIDFragments.getURIFragment(node3));
    Assert.assertSame(node3, resourceWithRelativeIDFragments.getEObject("/?id/@nodes.0"));
  }
}
