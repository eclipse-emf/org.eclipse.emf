/**
 * <copyright>
 *
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Florian Thienel <florian@thienel.org>
 *
 * </copyright>
 *
 * $Id: DynamicCrossResourceContainmentProxy.java,v 1.1 2008/06/02 14:44:01 emerks Exp $
 */
package org.eclipse.emf.test.core.dynamic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

public class DynamicCrossResourceContainmentProxy extends TestCase 
{
  public DynamicCrossResourceContainmentProxy(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("DynamicCrossResourceContainment");
    ts.addTest(new DynamicCrossResourceContainmentProxy("testDynamicGet"));
    return ts;
  }

  public void testDynamicGet() throws Exception 
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setNsURI("nsuri");
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("TestClass");
    ePackage.getEClassifiers().add(eClass);
    EReference eReference = EcoreFactory.eINSTANCE.createEReference();
    eClass.getEStructuralFeatures().add(eReference);
    eReference.setName("child");
    eReference.setEType(eClass);
    eReference.setContainment(true);
    eReference.setResolveProxies(true);
    
    EObject parent = ePackage.getEFactoryInstance().create(eClass);
    EObject child = ePackage.getEFactoryInstance().create(eClass);
    parent.eSet(eReference, child);

    assertEquals(child, parent.eGet(eClass.getEStructuralFeature("child")));

    URI parentURI = URI.createURI("_parent.xml", true);
    URI childURI = URI.createURI("_child.xml", true);

    ResourceSet outResourceSet = new ResourceSetImpl();
    outResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
      (Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl());
    Resource outParentResource = outResourceSet.createResource(parentURI);
    outParentResource.getContents().add(parent);
    Resource outChildResource = outResourceSet .createResource(childURI);
    outChildResource.getContents().add(child);
    ByteArrayOutputStream outParent = new ByteArrayOutputStream();
    outParentResource.save(outParent, null);
    ByteArrayOutputStream outChild = new ByteArrayOutputStream();
    outChildResource.save(outChild, null);

    ResourceSet inResourceSet = new ResourceSetImpl();
    inResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
      (Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl());
    inResourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);

    Resource inParentResource = inResourceSet.createResource(parentURI);
    inParentResource.load(new ByteArrayInputStream(outParent.toByteArray()), null);
    Resource inChildResource = inResourceSet.createResource(childURI);
    inChildResource.load(new ByteArrayInputStream(outChild.toByteArray()), null);

    EObject inParent = inParentResource.getContents().get(0);
    assertNotNull(inParent.eGet(eReference));
    assertNotNull(inParent.eGet(eReference));
  }
}
