/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: PersistenceTest.java,v 1.2 2005/01/05 20:42:49 marcelop Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.test.core.TestUtil;

public class PersistenceTest extends TestCase
{  
  private EObject john;
  private EObject mary;
  private EObject herbie;
  
  private EAttribute name;
  private EAttribute brand;
  private EReference children;
  private EReference father;
  private EReference cars;
  
  public PersistenceTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("PersistenceTest");
    ts.addTest(new PersistenceTest("testOneZipFile"));
    ts.addTest(new PersistenceTest("testOneTextFile"));
    ts.addTest(new PersistenceTest("testOneTextAndOneZipFiles"));
    ts.addTest(new PersistenceTest("testTwoZipFiles"));
    ts.addTest(new PersistenceTest("testTwoTextFiles"));
    return ts;
  }
  
  protected void setUp()
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsPrefix("pack");
    pack.setNsURI("http://mypack");
    EPackage.Registry.INSTANCE.put(pack.getNsURI(), pack);
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(person);
    person.setName("Person");
    
    name = EcoreFactory.eINSTANCE.createEAttribute();
    person.getEStructuralFeatures().add(name);
    name.setName("name");
    name.setEType(EcorePackage.eINSTANCE.getEString());
    
    children = EcoreFactory.eINSTANCE.createEReference();
    person.getEStructuralFeatures().add(children);
    children.setName("children");
    children.setEType(person);
    children.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    
    father = EcoreFactory.eINSTANCE.createEReference();
    person.getEStructuralFeatures().add(father);
    father.setName("father");
    father.setEType(person);

    children.setEOpposite(father);
    father.setEOpposite(children);
    
    EClass car = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(car);
    car.setName("Car");
    
    brand = EcoreFactory.eINSTANCE.createEAttribute();
    car.getEStructuralFeatures().add(brand);
    brand.setName("brand");
    brand.setEType(EcorePackage.eINSTANCE.getEString());

    cars = EcoreFactory.eINSTANCE.createEReference();
    person.getEStructuralFeatures().add(cars);
    cars.setName("cars");
    cars.setEType(car);
    cars.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    cars.setContainment(true);
    
    john = pack.getEFactoryInstance().create(person);
    john.eSet(name, "John");
    
    mary = pack.getEFactoryInstance().create(person);
    mary.eSet(name, "Mary");
    
    herbie = pack.getEFactoryInstance().create(car);
    herbie.eSet(brand, "vw");
    
    ((List)john.eGet(children)).add(mary);
    assertEquals(john, mary.eGet(father));
    
    ((List)john.eGet(cars)).add(herbie);
    assertEquals(john, herbie.eContainer());
  }

  public void testOneTextFile() throws Exception
  {
    oneFileTest(new XMIResourceFactoryImpl());
  }

  public void testOneZipFile() throws Exception
  {
    oneFileTest(new XMIResourceFactoryImpl()
    {
      public Resource createResource(URI uri)
      {
        XMIResource xmiResource = (XMIResource)super.createResource(uri);
        xmiResource.setUseZip(true);
        return xmiResource;
      }
    });
  }

  public void testTwoTextFiles() throws Exception
  {
    twoFileTest(new XMIResourceFactoryImpl(), new XMIResourceFactoryImpl());
  }

  public void testOneTextAndOneZipFiles() throws Exception
  {
    twoFileTest(new XMIResourceFactoryImpl(), new XMIResourceFactoryImpl()
    {
      public Resource createResource(URI uri)
      {
        XMIResource xmiResource = (XMIResource)super.createResource(uri);
        xmiResource.setUseZip(true);
        return xmiResource;
      }
    });
  }
  
  public void testTwoZipFiles() throws Exception
  {
    Resource.Factory zipResourceFactory = new XMIResourceFactoryImpl()
    {
      public Resource createResource(URI uri)
      {
        XMIResource xmiResource = (XMIResource)super.createResource(uri);
        xmiResource.setUseZip(true);
        return xmiResource;
      }
    };
    
    twoFileTest(zipResourceFactory, zipResourceFactory);
  }  
  
  public void oneFileTest(Resource.Factory resourceFactory) throws Exception
  {
    URI uri = uri = URI.createFileURI(TestUtil.getPluginDirectory() + "/people.pep");
    new File(uri.toFileString()).delete();

    Resource resource = resourceFactory.createResource(uri);
    
    resource.getContents().add(john);
    resource.getContents().add(mary);
    
    assertEquals(resource, john.eResource());
    assertEquals(resource, mary.eResource());
    
    resource.save(null);
    
    ResourceSet resourceSet = new ResourceSetImpl();
     resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("pep", resourceFactory);
    
    Resource loadedResource = resourceSet.getResource(uri, true);
    assertNotNull(loadedResource);
    assertEquals(2, loadedResource.getContents().size());
    
    checkIsJohn((EObject)loadedResource.getContents().get(0));
    checkIsMary((EObject)loadedResource.getContents().get(1));
    
    new File(uri.toFileString()).delete();
    assertFalse(new File(uri.toFileString()).exists());
  }

  public void twoFileTest(Resource.Factory johnResourceFactory, Resource.Factory maryResourceFactory) throws Exception
  {
    URI johnURI = URI.createFileURI(TestUtil.getPluginDirectory() + "/f1/people.john");
    new File(johnURI.toFileString()).delete();
    URI maryURI = URI.createFileURI(TestUtil.getPluginDirectory() + "/f1/f2/people.mary");
    new File(maryURI.toFileString()).delete();

    Resource johnResource = johnResourceFactory.createResource(johnURI);
    johnResource.getContents().add(john);
    
    Resource maryResource = maryResourceFactory.createResource(maryURI);
    maryResource.getContents().add(mary);
    
    assertEquals(johnResource, john.eResource());
    assertEquals(maryResource, mary.eResource());
    
    johnResource.save(null);
    maryResource.save(null);
    
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("john", johnResourceFactory);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("mary", maryResourceFactory);
    
    Resource loadedResource = resourceSet.getResource(johnURI, true);
    assertNotNull(loadedResource);
    assertEquals(1, loadedResource.getContents().size());
    
    EObject eObject = (EObject)loadedResource.getContents().get(0); 
    checkIsJohn(eObject);
    checkIsMary((EObject)((List)eObject.eGet(children)).get(0));
    
    resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("john", johnResourceFactory);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("mary", maryResourceFactory);
    
    loadedResource = resourceSet.getResource(maryURI, true);
    assertNotNull(loadedResource);
    assertEquals(1, loadedResource.getContents().size());
    
    eObject = (EObject)loadedResource.getContents().get(0); 
    checkIsMary(eObject);
    checkIsJohn((EObject)eObject.eGet(father));    
    
    new File(johnURI.toFileString()).delete();
    assertFalse(new File(johnURI.toFileString()).exists());
    new File(maryURI.toFileString()).delete();
    assertFalse(new File(maryURI.toFileString()).exists());
  }
  
  private void checkIsJohn(EObject person)
  {
    assertEquals(john.eGet(name), person.eGet(name));
    assertEquals(1, ((List)person.eGet(children)).size());
    assertEquals(mary.eGet(name), ((EObject)((List)person.eGet(children)).get(0)).eGet(name));
    assertEquals(1, ((List)person.eGet(cars)).size());
    assertEquals(herbie.eGet(brand), ((EObject)((List)person.eGet(cars)).get(0)).eGet(brand));
  }
  
  private void checkIsMary(EObject person)
  {
    assertEquals(mary.eGet(name), person.eGet(name));
    assertEquals(john.eGet(name), ((EObject)person.eGet(father)).eGet(name));
    assertTrue(((List)person.eGet(cars)).isEmpty());
  }  
}
