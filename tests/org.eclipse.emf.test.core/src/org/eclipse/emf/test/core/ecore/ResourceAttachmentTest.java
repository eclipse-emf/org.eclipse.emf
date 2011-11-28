/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ResourceAttachmentTest.java,v 1.2 2011/11/28 11:34:54 emerks Exp $
 */
package org.eclipse.emf.test.core.ecore;


import java.util.List;
import java.util.Set;
import java.util.HashSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;


public class ResourceAttachmentTest extends TestCase
{
  private EPackage compositePackage;
  private EClass compositeClass;
  private EAttribute compositeName;
  private EReference compositeChildren;

  static class AttachmentTrackingResourceImpl extends ResourceImpl
  {
    public Set<EObject> attachedEObjects = new HashSet<EObject>();
    
    @Override
    protected boolean isAttachedDetachedHelperRequired()
    {
      return true;
    }

    @Override
    public void attachedHelper(EObject eObject)
    {
      super.attachedHelper(eObject);

      // Ensure that an object is only attached once.
      //
      boolean result = attachedEObjects.add(eObject);
      assertTrue(result);
    }

    @Override
    public void detachedHelper(EObject eObject)
    {
      super.detachedHelper(eObject);
      
      // Ensure that an object is only detached once.
      //
      boolean result = attachedEObjects.remove(eObject);
      assertTrue(result);
    }
  }

  public ResourceAttachmentTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite(ResourceAttachmentTest.class.getSimpleName());
    ts.addTest(new ResourceAttachmentTest("testPackage"));
    ts.addTest(new ResourceAttachmentTest("testSimpleAttachAndDetach"));
    ts.addTest(new ResourceAttachmentTest("testSimpleCrossResourceAttachAndDetach"));
    ts.addTest(new ResourceAttachmentTest("testIndirectAttachAndDetach"));
    ts.addTest(new ResourceAttachmentTest("testIndirectCrossResourceAttachAndDetach"));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
    EcorePackage ecorePackage = EcorePackage.eINSTANCE;

    compositeClass = ecoreFactory.createEClass();
    compositeClass.setName("Composite");

    compositeName = ecoreFactory.createEAttribute();
    compositeName.setName("name");
    compositeName.setEType(ecorePackage.getEString());
    compositeClass.getEStructuralFeatures().add(compositeName);

    compositeChildren = ecoreFactory.createEReference();
    compositeChildren.setName("children");
    compositeChildren.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    compositeChildren.setEType(compositeClass);
    compositeChildren.setContainment(true);
    compositeClass.getEStructuralFeatures().add(compositeChildren);

    compositePackage = ecoreFactory.createEPackage();
    compositePackage.setName("composite");
    compositePackage.setNsPrefix("composite");
    compositePackage.setNsURI("http:///com.example.composite.ecore");
    compositePackage.getEClassifiers().add(compositeClass);
    EPackage.Registry.INSTANCE.put(compositePackage.getNsURI(), compositePackage);
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception
  {
    EPackage.Registry.INSTANCE.remove(compositePackage.getNsURI());
    compositeClass = null;
    compositeName = null;
    compositeChildren = null;
    compositePackage = null;
  }

  public void testPackage()
  {
    assertEquals(Diagnostician.INSTANCE.validate(compositePackage).getSeverity(), Diagnostic.OK);
  }
  
  public void testSimpleAttachAndDetach()
  {
    AttachmentTrackingResourceImpl resource1 = new AttachmentTrackingResourceImpl();
    EObject composite1 = EcoreUtil.create(compositeClass);

    // Directly adding the object to the resource will attach it.
    //
    resource1.getContents().add(composite1);
    assertTrue(resource1.attachedEObjects.contains(composite1));

    // Directly adding the object from the resource will detach it.
    //
    resource1.getContents().remove(composite1);
    assertFalse(resource1.attachedEObjects.contains(composite1));
  }

  public void testIndirectAttachAndDetach()
  {
    AttachmentTrackingResourceImpl resource1 = new AttachmentTrackingResourceImpl();
    EObject composite1 = EcoreUtil.create(compositeClass);
    @SuppressWarnings("unchecked")
    List<EObject> children = (List<EObject>)composite1.eGet(compositeChildren);
    EObject composite2 = EcoreUtil.create(compositeClass);
    
    // Adding an object to the resource and a contained child to that object will attach them both.
    //
    resource1.getContents().add(composite1);
    children.add(composite2);
    assertTrue(resource1.attachedEObjects.contains(composite1));
    assertTrue(resource1.attachedEObjects.contains(composite2));
    
    // Removing a child from a parent and removing the parent from the resource will detach them both.
    // 
    children.remove(composite2);
    assertFalse(resource1.attachedEObjects.contains(composite2));
    resource1.getContents().remove(composite1);
    assertFalse(resource1.attachedEObjects.contains(composite1));
    
    // Adding an object with an existing contained child will attach them both.
    // 
    children.add(composite2);
    resource1.getContents().add(composite1);
    assertTrue(resource1.attachedEObjects.contains(composite1));
    assertTrue(resource1.attachedEObjects.contains(composite2));

    // Removing an object with an existing contained child will detach them both.
    // 
    resource1.getContents().remove(composite1);
    assertFalse(resource1.attachedEObjects.contains(composite1));
    assertFalse(resource1.attachedEObjects.contains(composite2));
  }

  public void testSimpleCrossResourceAttachAndDetach()
  {
    AttachmentTrackingResourceImpl resource1 = new AttachmentTrackingResourceImpl();
    AttachmentTrackingResourceImpl resource2 = new AttachmentTrackingResourceImpl();
    EObject composite1 = EcoreUtil.create(compositeClass);

    // Directly adding the object to the resource will attach it.
    //
    resource1.getContents().add(composite1);
    assertTrue(resource1.attachedEObjects.contains(composite1));

    // Directly adding the object a different resource will detach it from the old one and attach it to the new one.
    //
    resource2.getContents().add(composite1);
    assertFalse(resource1.attachedEObjects.contains(composite1));
    assertTrue(resource2.attachedEObjects.contains(composite1));
  }

  public void testIndirectCrossResourceAttachAndDetach()
  {
    AttachmentTrackingResourceImpl resource1 = new AttachmentTrackingResourceImpl();
    resource1.setURI(URI.createURI("resource1"));
    AttachmentTrackingResourceImpl resource2 = new AttachmentTrackingResourceImpl();
    resource2.setURI(URI.createURI("resource2"));
    EObject composite1 = EcoreUtil.create(compositeClass);
    @SuppressWarnings("unchecked")
    List<EObject> children = (List<EObject>)composite1.eGet(compositeChildren);
    EObject composite2 = EcoreUtil.create(compositeClass);
    
    // Adding an object to the resource and a contained child to that object will attach them both.
    //
    resource1.getContents().add(composite1);
    children.add(composite2);
    assertTrue(resource1.attachedEObjects.contains(composite1));
    assertTrue(resource1.attachedEObjects.contains(composite2));
    
    // Directly adding an object with a contained child to a different resource will detach both from the old one and attach both to the new one.
    // 
    resource2.getContents().add(composite1);
    assertFalse(resource1.attachedEObjects.contains(composite1));
    assertFalse(resource1.attachedEObjects.contains(composite2));
    assertTrue(resource2.attachedEObjects.contains(composite1));
    assertTrue(resource2.attachedEObjects.contains(composite2));
    
    // Moving it back does the expected thing too.
    //
    resource1.getContents().add(composite1);
    assertTrue(resource1.attachedEObjects.contains(composite1));
    assertTrue(resource1.attachedEObjects.contains(composite2));
    assertFalse(resource2.attachedEObjects.contains(composite1));
    assertFalse(resource2.attachedEObjects.contains(composite2));

    // Adding a proxy resolving contained child to a different resource will detach it from the old one and attach it to the new one.
    // 
    resource2.getContents().add(composite2);
    assertFalse(resource1.attachedEObjects.contains(composite2));
    assertTrue(resource2.attachedEObjects.contains(composite2));

    // Removing a proxy resolving contained child from a different resource will detach it from the old one and attach it to the one for the container.
    // 
    resource2.getContents().remove(composite2);
    assertFalse(resource2.attachedEObjects.contains(composite2));
    assertTrue(resource1.attachedEObjects.contains(composite2));
    
    // Add it back in again.
    //
    resource2.getContents().add(composite2);
    assertFalse(resource1.attachedEObjects.contains(composite2));
    assertTrue(resource2.attachedEObjects.contains(composite2));
    
    // Remove it by unloading.
    //
    resource2.unload();
    assertFalse(resource2.attachedEObjects.contains(composite2));
    assertTrue(resource1.attachedEObjects.contains(composite2));
  }
}