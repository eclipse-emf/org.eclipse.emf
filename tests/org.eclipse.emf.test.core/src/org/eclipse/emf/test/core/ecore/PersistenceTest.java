/**
 * <copyright>
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
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
 * $Id: PersistenceTest.java,v 1.17 2008/05/04 10:59:07 emerks Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.change.ChangePackage;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.AESCipherImpl;
import org.eclipse.emf.ecore.resource.impl.DESCipherImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;
import org.eclipse.emf.test.models.key.Item;
import org.eclipse.emf.test.models.key.KeyFactory;
import org.eclipse.emf.test.models.key.KeyPackage;
import org.eclipse.emf.test.models.key.Root;
import org.eclipse.emf.test.models.lib.Address;
import org.eclipse.emf.test.models.lib.Book;
import org.eclipse.emf.test.models.lib.Cafeteria;
import org.eclipse.emf.test.models.lib.LibFactory;
import org.eclipse.emf.test.models.lib.Library;
import org.eclipse.emf.test.models.lib.Person;

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
    ts.addTest(new PersistenceTest("testEDataType"));
    ts.addTest(new PersistenceTest("testCrossResourceContainment_XMLResourceUUID"));
    ts.addTest(new PersistenceTest("testCrossResourceContainment_Dynamic_ResourceSet"));
    ts.addTest(new PersistenceTest("testCrossResourceContainment_Static_ResourceSet"));
    ts.addTest(new PersistenceTest("testCrossResourceContainment_RemoveChild"));
    ts.addTest(new PersistenceTest("testPluginURINotRelative"));
    ts.addTest(new PersistenceTest("testReferenceEcoreUsingNSURI"));
    ts.addTest(new PersistenceTest("testCipher0"));
    ts.addTest(new PersistenceTest("testCipher1"));
    return ts;
  }
  
  @Override
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
    name.setEType(EcorePackage.Literals.ESTRING);
    
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
    brand.setEType(EcorePackage.Literals.ESTRING);

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
    
    @SuppressWarnings("unchecked")
    List<EObject> johnChildren = ((List<EObject>)john.eGet(children));
    johnChildren.add(mary);
    assertEquals(john, mary.eGet(father));
    
    @SuppressWarnings("unchecked")
    List<EObject> johnCars = ((List<EObject>)john.eGet(cars));
    johnCars.add(herbie);
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
      @Override
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
      @Override
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
      @Override
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
    URI uri = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/people.pep");
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
    
    checkIsJohn(loadedResource.getContents().get(0));
    checkIsMary(loadedResource.getContents().get(1));
    
    new File(uri.toFileString()).delete();
    assertFalse(new File(uri.toFileString()).exists());
  }

  public void twoFileTest(Resource.Factory johnResourceFactory, Resource.Factory maryResourceFactory) throws Exception
  {
    URI johnURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/f1/people.john");
    new File(johnURI.toFileString()).delete();
    URI maryURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/f1/f2/people.mary");
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
    
    EObject eObject = loadedResource.getContents().get(0); 
    checkIsJohn(eObject);
    checkIsMary((EObject)((List<?>)eObject.eGet(children)).get(0));
    
    resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("john", johnResourceFactory);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("mary", maryResourceFactory);
    
    loadedResource = resourceSet.getResource(maryURI, true);
    assertNotNull(loadedResource);
    assertEquals(1, loadedResource.getContents().size());
    
    eObject = loadedResource.getContents().get(0); 
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
    assertEquals(1, ((List<?>)person.eGet(children)).size());
    assertEquals(mary.eGet(name), ((EObject)((List<?>)person.eGet(children)).get(0)).eGet(name));
    assertEquals(1, ((List<?>)person.eGet(cars)).size());
    assertEquals(herbie.eGet(brand), ((EObject)((List<?>)person.eGet(cars)).get(0)).eGet(brand));
  }
  
  private void checkIsMary(EObject person)
  {
    assertEquals(mary.eGet(name), person.eGet(name));
    assertEquals(john.eGet(name), ((EObject)person.eGet(father)).eGet(name));
    assertTrue(((List<?>)person.eGet(cars)).isEmpty());
  }
  
  public void testEDataType() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("localpack");
    pack.setNsPrefix("localpack");
    pack.setNsURI("http://mylocalpack");
    EPackage.Registry.INSTANCE.put(pack.getNsURI(), pack);
        
    EDataType date = EcoreFactory.eINSTANCE.createEDataType();
    pack.getEClassifiers().add(date);
    date.setName("Date");
    date.setInstanceClass(Date.class);
    date.setSerializable(true);

    EDataType foo = EcoreFactory.eINSTANCE.createEDataType();
    pack.getEClassifiers().add(foo);
    foo.setName("Foo");
    foo.setInstanceClassName("org.Foo");
    foo.setSerializable(true);
    
    EClass person  = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(person);
    person.setName("Person");
    
    EAttribute birthday = EcoreFactory.eINSTANCE.createEAttribute();
    person.getEStructuralFeatures().add(birthday);
    birthday.setName("birthday");
    birthday.setEType(date);

    long dateValue = System.currentTimeMillis();
    EObject john = pack.getEFactoryInstance().create(person);
    john.eSet(birthday, new Date(dateValue));
    
    XMIResource xmiResource = new XMIResourceImpl();
    xmiResource.setURI(URI.createFileURI("foo.xmi"));
    xmiResource.getContents().add(john);
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    xmiResource.save(baos, null);
    
    XMIResource loadedXMIResource = new XMIResourceImpl();
    loadedXMIResource.load(new ByteArrayInputStream(baos.toByteArray()), null);
    assertEquals(1, loadedXMIResource.getContents().size());
    
    EObject loadedJohn = loadedXMIResource.getContents().get(0);
    assertTrue(loadedJohn.eGet(birthday) instanceof Date);
    assertEquals(dateValue, ((Date)loadedJohn.eGet(birthday)).getTime());
  }
  
  /*
   * Bugzilla 126647
   */
  public void testCrossResourceContainment_XMLResourceUUID() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack/person");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    
    EReference children = EcoreFactory.eINSTANCE.createEReference();
    children.setName("children");
    children.setEType(person);
    children.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    children.setContainment(true);
    children.setResolveProxies(true);
    person.getEStructuralFeatures().add(children);
    
    EObject john = EcoreUtil.create(person);
    john.eSet(name, "john");
    EObject mary = EcoreUtil.create(person);
    mary.eSet(name, "mary");
    @SuppressWarnings("unchecked")
    List<EObject> johnChildren = ((List<EObject>)john.eGet(children));
    johnChildren.add(mary);
    
    assertNull(john.eResource());
    assertNull(mary.eResource());
    assertEquals(john, mary.eContainer());
    
    XMLResource johnResource = new XMLResourceImpl(URI.createFileURI("john"))
    {
      @Override
      protected boolean useUUIDs()
      {
        return true;
      }
    };
    johnResource.setID(john, (String)john.eGet(name));
    johnResource.setID(mary, (String)mary.eGet(name));
    johnResource.getContents().add(john);
    
    assertEquals(john, johnResource.getEObject((String)john.eGet(name)));
    assertEquals(mary, johnResource.getEObject((String)mary.eGet(name)));
    //
    assertEquals(johnResource, john.eResource());
    assertEquals(johnResource, mary.eResource());
    assertEquals(john, mary.eContainer());
    
    XMLResource maryResource = new XMLResourceImpl(URI.createFileURI("mary"))
    {
      @Override
      protected boolean useUUIDs()
      {
        return true;
      }
    };
    maryResource.getContents().add(mary);
    
    assertEquals(john, johnResource.getEObject((String)john.eGet(name)));
    assertNull(johnResource.getEObject((String)mary.eGet(name)));
    assertNull(maryResource.getEObject((String)john.eGet(name)));
    assertEquals(mary, maryResource.getEObject((String)mary.eGet(name)));
    //
    assertEquals(johnResource, john.eResource());
    assertEquals(maryResource, mary.eResource());
    assertEquals(john, mary.eContainer());
    
    maryResource.getContents().remove(mary);
    
    assertEquals(john, johnResource.getEObject((String)john.eGet(name)));
    assertEquals(mary, johnResource.getEObject((String)mary.eGet(name)));
    assertNull(maryResource.getEObject((String)john.eGet(name)));
    assertNull(maryResource.getEObject((String)mary.eGet(name)));
    //
    assertEquals(johnResource, john.eResource());
    assertEquals(johnResource, mary.eResource());
    assertEquals(john, mary.eContainer());
  }
  
  /*
   * Bugzilla 126650
   */
  public void testCrossResourceContainment_Dynamic_ResourceSet() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("http://www.eclipse.org/emf/pack/person");
    
    EClass person = EcoreFactory.eINSTANCE.createEClass();
    person.setName("Person");
    pack.getEClassifiers().add(person);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    person.getEStructuralFeatures().add(name);
    
    EReference children = EcoreFactory.eINSTANCE.createEReference();
    children.setName("children");
    children.setEType(person);
    children.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    children.setContainment(true);
    children.setResolveProxies(true);
    person.getEStructuralFeatures().add(children);
    
    EReference spouse = EcoreFactory.eINSTANCE.createEReference();
    spouse.setName("spouse");
    spouse.setEType(person);
    spouse.setContainment(true);
    spouse.setResolveProxies(true);
    person.getEStructuralFeatures().add(spouse);
    
    EClass house = EcoreFactory.eINSTANCE.createEClass();
    house.setName("House");
    pack.getEClassifiers().add(house);
    
    EAttribute postalCode = EcoreFactory.eINSTANCE.createEAttribute();
    postalCode.setName("postalCode");
    postalCode.setEType(EcorePackage.Literals.ESTRING);
    house.getEStructuralFeatures().add(postalCode);

    EReference home = EcoreFactory.eINSTANCE.createEReference();
    home.setName("home");
    home.setEType(house);
    home.setContainment(true);
    home.setResolveProxies(true);
    person.getEStructuralFeatures().add(home);

    EReference owner = EcoreFactory.eINSTANCE.createEReference();
    owner.setName("owner");
    owner.setEType(person);
    house.getEStructuralFeatures().add(owner);
    
    owner.setEOpposite(home);
    home.setEOpposite(owner);
    
    EReference houses = EcoreFactory.eINSTANCE.createEReference();
    houses.setName("houses");
    houses.setEType(house);
    houses.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    houses.setContainment(true);
    houses.setResolveProxies(true);
    person.getEStructuralFeatures().add(houses);
    
    EReference landlord  = EcoreFactory.eINSTANCE.createEReference();
    landlord.setName("landlord");
    landlord.setEType(person);
    house.getEStructuralFeatures().add(landlord);

    landlord.setEOpposite(houses);
    houses.setEOpposite(landlord);
        
       
    EObject john = EcoreUtil.create(person);
    john.eSet(name, "john");
    EObject jane = EcoreUtil.create(person);
    jane.eSet(name, "jane");
    john.eSet(spouse, jane);
    EObject mary = EcoreUtil.create(person);
    mary.eSet(name, "mary");
    @SuppressWarnings("unchecked")
    List<EObject> johnChildren = ((List<EObject>)john.eGet(children));
    johnChildren.add(mary);
    EObject johnsHome = EcoreUtil.create(house);
    johnsHome.eSet(postalCode, "abcdefg");
    john.eSet(home, johnsHome);
    EObject house1 = EcoreUtil.create(house);
    house1.eSet(postalCode, "house 1");
    @SuppressWarnings("unchecked")
    List<EObject> johnHouses = ((List<EObject>)john.eGet(houses));
    johnHouses.add(house1);
    
    assertNull(john.eResource());
    assertNull(jane.eResource());
    assertNull(mary.eResource());
    assertNull(johnsHome.eResource());
    assertNull(house1.eResource());
    assertEquals(john, jane.eContainer());
    assertEquals(john, mary.eContainer());
    assertEquals(john, johnsHome.eContainer());
    assertEquals(john, johnsHome.eGet(owner));
    assertEquals(john, house1.eContainer());
    assertEquals(john, house1.eGet(landlord));

    URI johnURI = URI.createFileURI("john.xmi");
    URI uri2 = URI.createFileURI("uri2.xmi");
    
    Resource johnResource = new XMIResourceImpl(johnURI);
    johnResource.getContents().add(john);
    
    assertEquals(johnResource, john.eResource());
    assertEquals(johnResource, jane.eResource());
    assertEquals(johnResource, mary.eResource());
    assertEquals(johnResource, johnsHome.eResource());
    assertEquals(johnResource, house1.eResource());
    assertEquals(john, jane.eContainer());
    assertEquals(john, mary.eContainer());
    assertEquals(john, johnsHome.eContainer());
    assertEquals(john, johnsHome.eGet(owner));
    assertEquals(john, house1.eContainer());
    assertEquals(john, house1.eGet(landlord));
    
    Resource resource2 = new XMIResourceImpl(uri2);
    resource2.getContents().add(jane);
    resource2.getContents().add(mary);
    resource2.getContents().add(johnsHome);
    resource2.getContents().add(house1);

    assertEquals(johnResource, john.eResource());
    assertEquals(resource2, jane.eResource());
    assertEquals(resource2, mary.eResource());
    assertEquals(resource2, johnsHome.eResource());
    assertEquals(resource2, house1.eResource());
    assertEquals(john, jane.eContainer());
    assertEquals(john, mary.eContainer());
    assertEquals(john, johnsHome.eContainer());
    assertEquals(john, johnsHome.eGet(owner));
    assertEquals(john, house1.eContainer());
    assertEquals(john, house1.eGet(landlord));
    
    ByteArrayOutputStream johnBAOS = new ByteArrayOutputStream();
    johnResource.save(johnBAOS, null);
    ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
    resource2.save(baos2, null);
    
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getPackageRegistry().put(pack.getNsURI(), pack);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    
    johnResource = resourceSet.createResource(johnURI);
    johnResource.load(new ByteArrayInputStream(johnBAOS.toByteArray()), null);
    resource2 = resourceSet.createResource(uri2);
    resource2.load(new ByteArrayInputStream(baos2.toByteArray()), null);
    
    john = johnResource.getContents().get(0);
    jane = resource2.getContents().get(0);
    mary = resource2.getContents().get(1);
    johnsHome = resource2.getContents().get(2);
    house1 = resource2.getContents().get(3);

    assertEquals("john", john.eGet(name));
    assertEquals("jane", jane.eGet(name));
    assertEquals("mary", mary.eGet(name));
    assertEquals("abcdefg", johnsHome.eGet(postalCode));
    assertEquals("house 1", house1.eGet(postalCode));
    EcoreUtil.resolveAll(johnResource);
    
    assertEquals(johnResource, john.eResource());
    assertEquals(resource2, jane.eResource());
    assertEquals(resource2, mary.eResource());
    assertEquals(resource2, johnsHome.eResource());
    assertEquals(resource2, house1.eResource());
    assertEquals(john, jane.eContainer());
    assertEquals(john, mary.eContainer());
    assertEquals(john, johnsHome.eContainer());
    assertEquals(john, johnsHome.eGet(owner));
    assertEquals(john, house1.eContainer());
    assertEquals(john, house1.eGet(landlord));    
  }
  
  /*
   * Bugzilla 126650
   */
  public void testCrossResourceContainment_Static_ResourceSet() throws Exception
  {
    Library library = LibFactory.eINSTANCE.createLibrary();
    library.setName("Public Library");
    //
    Book book = LibFactory.eINSTANCE.createBook();
    book.setTitle("EMF");
    library.getBooks().add(book);
    //
    Address libraryAddress = LibFactory.eINSTANCE.createAddress();
    libraryAddress.setPostalCode("abcdefg");
    library.setAddress(libraryAddress);
    //
    Person john = LibFactory.eINSTANCE.createPerson();
    john.setName("john");
    library.getWriters().add(john);
    //
    Cafeteria cafeteria = LibFactory.eINSTANCE.createCafeteria();
    cafeteria.setName("cafe");
    library.setCafeteria(cafeteria);
    
    assertNull(library.eResource());
    assertNull(book.eResource());
    assertNull(libraryAddress.eResource());
    assertNull(john.eResource());
    assertNull(cafeteria.eResource());
    assertEquals(library, book.eContainer());
    assertEquals(library, libraryAddress.eContainer());
    assertEquals(library, john.eContainer());
    assertEquals(library, john.getLibrary());
    assertEquals(library, cafeteria.eContainer());
    assertEquals(library, cafeteria.getLibrary());
    
    URI libraryURI = URI.createFileURI("library.xmi");
    URI childrenURI = URI.createFileURI("children.xmi");
    
    Resource libraryResource = new XMIResourceImpl(libraryURI);
    libraryResource.getContents().add(library);
    
    assertEquals(libraryResource, library.eResource());
    assertEquals(libraryResource, book.eResource());
    assertEquals(libraryResource, libraryAddress.eResource());
    assertEquals(libraryResource, john.eResource());
    assertEquals(libraryResource, cafeteria.eResource());
    assertEquals(library, book.eContainer());
    assertEquals(library, libraryAddress.eContainer());
    assertEquals(library, john.eContainer());
    assertEquals(library, john.getLibrary());
    assertEquals(library, cafeteria.eContainer());
    assertEquals(library, cafeteria.getLibrary());
    
    Resource childrenResource = new XMIResourceImpl(childrenURI);
    childrenResource.getContents().add(book);
    childrenResource.getContents().add(libraryAddress);
    childrenResource.getContents().add(john);
    childrenResource.getContents().add(cafeteria);

    assertEquals(libraryResource, library.eResource());
    assertEquals(childrenResource, book.eResource());
    assertEquals(childrenResource, libraryAddress.eResource());
    assertEquals(childrenResource, john.eResource());
    assertEquals(childrenResource, cafeteria.eResource());
    assertEquals(library, book.eContainer());
    assertEquals(library, libraryAddress.eContainer());
    assertEquals(library, john.eContainer());
    assertEquals(library, john.getLibrary());
    assertEquals(library, cafeteria.eContainer());
    assertEquals(library, cafeteria.getLibrary());
    
    ByteArrayOutputStream libraryBAOS = new ByteArrayOutputStream();
    libraryResource.save(libraryBAOS, null);
    ByteArrayOutputStream bookBAOS = new ByteArrayOutputStream();
    childrenResource.save(bookBAOS, null);
    
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    
    libraryResource = resourceSet.createResource(libraryURI);
    libraryResource.load(new ByteArrayInputStream(libraryBAOS.toByteArray()), null);
    childrenResource = resourceSet.createResource(childrenURI);
    childrenResource.load(new ByteArrayInputStream(bookBAOS.toByteArray()), null);
    
    library = (Library)libraryResource.getContents().get(0);
    book = (Book)childrenResource.getContents().get(0);
    libraryAddress = (Address)childrenResource.getContents().get(1);
    john = (Person)childrenResource.getContents().get(2);
    cafeteria = (Cafeteria)childrenResource.getContents().get(3);

    assertEquals("Public Library", library.getName());
    assertEquals("EMF", book.getTitle());
    assertEquals("abcdefg", libraryAddress.getPostalCode());
    assertEquals("john", john.getName());
    assertEquals("cafe", cafeteria.getName());
    EcoreUtil.resolveAll(libraryResource);
    
    assertEquals(libraryResource, library.eResource());
    assertEquals(childrenResource, book.eResource());
    assertEquals(childrenResource, libraryAddress.eResource());
    assertEquals(childrenResource, john.eResource());
    assertEquals(childrenResource, cafeteria.eResource());
    assertEquals(library, book.eContainer());   
    assertEquals(library, libraryAddress.eContainer());
    assertEquals(library, john.eContainer());
    assertEquals(library, john.getLibrary());
    assertEquals(library, cafeteria.eContainer());
    assertEquals(library, cafeteria.getLibrary());
  }
  
  /*
   * Bugzilla 132904
   */
  public void testCrossResourceContainment_RemoveChild() throws Exception
  {
    Library library = LibFactory.eINSTANCE.createLibrary();
    library.setName("Public Library");
    //
    Book book = LibFactory.eINSTANCE.createBook();
    book.setTitle("EMF");
    library.getBooks().add(book);
    
    Resource libResource = new ResourceImpl(URI.createURI("lib"));
    libResource.getContents().add(library);
    
    assertEquals(library, book.eContainer());
    assertEquals(libResource, library.eResource());
    assertEquals(libResource, book.eResource());

    Resource bookResource = new ResourceImpl(URI.createURI("book"));
    bookResource.getContents().add(book);

    assertEquals(library, book.eContainer());
    assertEquals(libResource, library.eResource());
    assertEquals(bookResource, book.eResource());
    
    library.getBooks().remove(book);

    assertNull(book.eContainer());
    assertEquals(libResource, library.eResource());
    assertEquals(bookResource, book.eResource());
    
    library.getBooks().add(book);

    assertEquals(library, book.eContainer());
    assertEquals(libResource, library.eResource());
    assertEquals(bookResource, book.eResource());
  }
  
  /*
   * Bugzilla 169308
   */
  public void testPluginURINotRelative() throws Exception
  {
    Resource.Factory ecoreResourceFactory = new EcoreResourceFactoryImpl();
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", ecoreResourceFactory);
    
    URI localPluginURI = URI.createFileURI(new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID)).getAbsolutePath() + "/");
    URI pluginURI = URI.createURI("platform:/plugin/org.eclipse.emf.test.core/", false);    
    resourceSet.getURIConverter().getURIMap().put(pluginURI, localPluginURI);
    
    URI changeURI = URI.createPlatformPluginURI("/org.eclipse.emf.test.core/data/Change.ecore", false);
    Resource changeResource = resourceSet.getResource(changeURI, true);
    EPackage changePackage = (EPackage)changeResource.getContents().get(0); 
    
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("example");
    pack.setNsPrefix("example");
    pack.setNsURI("http://org.eclipse.emf.example");
    
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(eClass);
    eClass.setName("Monitor");
    
    EReference eReference = EcoreFactory.eINSTANCE.createEReference();
    eClass.getEStructuralFeatures().add(eReference);
    eReference.setName("change");
    eReference.setEType(changePackage.getEClassifier("ChangeDescription"));
    
    URI modelURI = URI.createPlatformResourceURI("/myProject/model/model.ecore", false);
    Resource modelResource = resourceSet.createResource(modelURI);
    modelResource.getContents().add(pack);
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    modelResource.save(baos, null);
    String contents = new String(baos.toByteArray());
    assertFalse(contents, contents.contains("../plugin"));
    //System.out.println(contents);
    
    modelResource = ecoreResourceFactory.createResource(URI.createPlatformResourceURI("/myNewProject/model.ecore", false));
    modelResource.load(new ByteArrayInputStream(baos.toByteArray()), null);
    EPackage loadedEPackage = (EPackage)modelResource.getContents().get(0);
    EClass loadedEClass = (EClass)loadedEPackage.eContents().get(0);
    EReference loadedEReference = (EReference)loadedEClass.eContents().get(0);
    EObject loadedEReferenceType = ((EReferenceImpl)loadedEReference).basicGetEType();
    
    assertTrue(loadedEReferenceType.eIsProxy());
    URI proxyURI = ((InternalEObject)loadedEReferenceType).eProxyURI();
    assertTrue(proxyURI.toString(), proxyURI.isPlatformPlugin());
  }

  public void testReferenceEcoreUsingNSURI() throws Exception
  {
    Resource.Factory ecoreResourceFactory = new EcoreResourceFactoryImpl();
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", ecoreResourceFactory);
    
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("example");
    pack.setNsPrefix("example");
    pack.setNsURI("http://org.eclipse.emf.example");
    
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(eClass);
    eClass.setName("Monitor");
    
    EReference eReference = EcoreFactory.eINSTANCE.createEReference();
    eClass.getEStructuralFeatures().add(eReference);
    eReference.setName("change");
    eReference.setEType(ChangePackage.Literals.CHANGE_DESCRIPTION);
    
    URI modelURI = URI.createPlatformResourceURI("/myProject/model/model.ecore", false);
    Resource modelResource = resourceSet.createResource(modelURI);
    modelResource.getContents().add(pack);
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    modelResource.save(baos, null);
    String contents = new String(baos.toByteArray());
    assertFalse(contents, contents.contains("../plugin"));
    
    modelResource = ecoreResourceFactory.createResource(URI.createPlatformResourceURI("/myNewProject/model.ecore", false));
    modelResource.load(new ByteArrayInputStream(baos.toByteArray()), null);
    EPackage loadedEPackage = (EPackage)modelResource.getContents().get(0);
    EClass loadedEClass = (EClass)loadedEPackage.eContents().get(0);
    EReference loadedEReference = (EReference)loadedEClass.eContents().get(0);
    EObject loadedEReferenceType = ((EReferenceImpl)loadedEReference).basicGetEType();
    
    assertTrue(loadedEReferenceType.eIsProxy());
    URI proxyURI = ((InternalEObject)loadedEReferenceType).eProxyURI();
    assertFalse(proxyURI.toString(), proxyURI.isPlatformPlugin());
    assertFalse(proxyURI.toString(), proxyURI.isRelative());
  }
  
  public void testCipher0() throws Exception
  {
    cipherTest(new DESCipherImpl("a very long key indeed"));
    cipherTest(new AESCipherImpl("a very long passowrd indeed"));
  }
  
  protected void cipherTest(URIConverter.Cipher cipher) throws Exception
  {
    StringBuilder originalMessage = new StringBuilder("<>\n");
    for (int i=0; i<100; i++)
    {
      originalMessage
      .append(i)
      .append(" - ")
      .append("the quick brown fox jumped over the sleeping lazy dog")
      .append('\n');
    }
    originalMessage.append("</>");
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    OutputStream os = cipher.encrypt(baos);
    os.write(originalMessage.toString().getBytes());
    cipher.finish(os);
    os.close();
    
    assertEquals(originalMessage.toString(), readEncriptedBytes(cipher, baos.toByteArray()));
  }
  
  protected String readEncriptedBytes(URIConverter.Cipher cipher, byte[] bytes) throws Exception
  {
    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    InputStream is = cipher.decrypt(bais);
    StringBuilder message = new StringBuilder();
    byte[] buffer = new byte[7];
    int i = buffer.length;
    while ((i = is.read(buffer)) >= 0)
    {
      message.append(new String(buffer, 0, i));
    }  
    return message.toString().trim();
  }

  public void testCipher1() throws Exception
  {    
    class Tester
    {
      public byte[] test(Map<String, Object> options) throws Exception
      {
        URI uri = URI.createFileURI("/home/foo/f1.xmi");
        byte[] bytes = testSave(uri, options);
        
        if (false) System.out.println(getContents(options, bytes));
        
        testSaveOnlyIfChanged(options);

        testLoad(uri, options, bytes);
        return bytes;
      }
      
      protected String getContents(Map<String, Object> options, byte[] bytes) throws Exception
      {
        URIConverter.Cipher cipher = options == null ? 
          null : (URIConverter.Cipher)options.get(Resource.OPTION_CIPHER);
        return cipher != null ?
          readEncriptedBytes(cipher, bytes) : new String(bytes);
      }
      
      protected EObject instantiateModel()
      {
        Root root = KeyFactory.eINSTANCE.createRoot();
        Item item = KeyFactory.eINSTANCE.createItem();
        item.setName("Name-Item1");
        root.getItems().add(item);
        return root;
      }
      
      public byte[] testSave(URI uri, Map<String, Object> options) throws Exception
      {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Resource resource = new XMIResourceImpl(uri); 
        resource.getContents().add(instantiateModel());
        resource.save(baos, options);
        
        byte[] bytes = baos.toByteArray();
        
        String contents = new String(bytes);
        boolean notEncripted = options == null || !options.containsKey(Resource.OPTION_CIPHER);
        assertEquals(contents, notEncripted, contents.contains("Root"));
        assertEquals(contents, notEncripted, contents.contains("items"));
        assertEquals(contents, notEncripted, contents.contains("Name-Item1"));
        
        return bytes;
      }

      public void testSaveOnlyIfChanged(Map<String, Object> options) throws Exception
      {
        File file = File.createTempFile("TestSaveOnlyIfChanged.xml", null);
        URI uri = URI.createFileURI(file.getPath());
        Resource resource = new XMIResourceImpl(uri); 
        resource.getContents().add(instantiateModel());
        resource.save(options);

        long time = file.lastModified();
        Map<Object, Object> localOptions = options == null ? new HashMap<Object, Object>() : new HashMap<Object, Object>(options);

        try
        {
          Thread.sleep(1000);
          localOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
          resource.save(localOptions);
          assertEquals(time, file.lastModified());
  
          Thread.sleep(1000);
          localOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_FILE_BUFFER);
          resource.save(localOptions);
          assertEquals(time, file.lastModified());
  
          Thread.sleep(1000);
          resource.save(options);
          assertTrue(time < file.lastModified());
        }
        finally
        {
          file.delete();
        }
      }
      
      public void testLoad(URI uri, Map<String, Object> options, byte[] contents) throws Exception
      {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry().put(KeyPackage.eNS_URI, KeyPackage.eINSTANCE);
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        Resource loadedResource = resourceSet.createResource(uri);
        ByteArrayInputStream bais = new ByteArrayInputStream(contents);
        loadedResource.load(bais, options);
        
        assertEquals(1, loadedResource.getContents().size());
        Root root = (Root)loadedResource.getContents().get(0);
        assertEquals(1, root.getItems().size());
        assertEquals("Name-Item1", root.getItems().get(0).getName());
      }      
    }    
    
    Tester tester = new Tester();
        
    byte[] noCypherBytes = tester.test(null);

    Map<String, Object> desCyperOptions = new HashMap<String, Object>(1);
    desCyperOptions.put(Resource.OPTION_CIPHER, new DESCipherImpl("a very long key indeed"));
    byte[] desCypherBytes = tester.test(desCyperOptions);

    Map<String, Object> aesCyperOptions = new HashMap<String, Object>(1);
    aesCyperOptions.put(Resource.OPTION_CIPHER, new AESCipherImpl("a very long password indeed"));
    byte[] aesCypherBytes = tester.test(aesCyperOptions);
    
    assertFalse(Arrays.equals(noCypherBytes, desCypherBytes));
    assertFalse(Arrays.equals(noCypherBytes, aesCypherBytes));
    assertFalse(Arrays.equals(aesCypherBytes, desCypherBytes));
  }
}
