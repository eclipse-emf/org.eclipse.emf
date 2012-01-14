/**
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class ResourceCacheMechanismTest extends TestCase
{
  private EAttribute id;
  private EAttribute name;
  
  private EObject john;
  private EObject mary;
  private EObject johnDoe;
  
  public ResourceCacheMechanismTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("ResourceCacheMechanismTest");
    testSuite.addTest(new ResourceCacheMechanismTest("testResourceImplIntrinsicIDCache"));
    testSuite.addTest(new ResourceCacheMechanismTest("testXMLResourceIDCache"));
    testSuite.addTest(new ResourceCacheMechanismTest("testXMLResourceImplIDCache"));
    testSuite.addTest(new ResourceCacheMechanismTest("testAllIDCaches"));
    testSuite.addTest(new ResourceCacheMechanismTest("testResourceSetURICache"));
    testSuite.addTest(new ResourceCacheMechanismTest("testXMLResourceIDCacheWithContainment"));
    return testSuite;
  }  
  
  @Override
  protected void setUp() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage(); 
    
    EClass employee = EcoreFactory.eINSTANCE.createEClass();
    employee.setName("Employee");
    pack.getEClassifiers().add(employee);
    
    id = EcoreFactory.eINSTANCE.createEAttribute();
    id.setName("id");
    id.setID(true);
    id.setEType(EcorePackage.Literals.ESTRING);
    employee.getEStructuralFeatures().add(id);

    name = EcoreFactory.eINSTANCE.createEAttribute();
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    employee.getEStructuralFeatures().add(name);
    
    john = pack.getEFactoryInstance().create(employee);
    john.eSet(id, "1");
    john.eSet(name, "John");

    mary = pack.getEFactoryInstance().create(employee);
    mary.eSet(id, "2");
    mary.eSet(name, "mary");
    
    johnDoe = pack.getEFactoryInstance().create(employee);
    johnDoe.eSet(name, "John");
  }
  
  /*
   * Bugzilla 62885
   */
  public void testResourceImplIntrinsicIDCache() throws Exception
  {
    Resource resource = new ResourceImpl();
    
    //Setting the map before adding the objects
    Map<String, EObject> map = new HashMap<String, EObject>();
    ((ResourceImpl)resource).setIntrinsicIDToEObjectMap(map);
    
    resource.getContents().add(john);
    resource.getContents().add(mary);
    resource.getContents().add(johnDoe);
        
    //The map should have all the objects with ID (john and mary)
    assertEquals(2, map.size());
    assertEquals(john, map.get(john.eGet(id)));
    assertEquals(mary, map.get("2"));
    
    resource.getContents().remove(mary);

    //Mary should have been removed from the map.
    assertEquals(1, map.size());
    assertEquals(john, map.get(john.eGet(id)));
    
    //Setting johnDoe's ID
    johnDoe.eSet(id, "newId");
    
    //The map should be the same as before
    assertEquals(1, map.size());
    assertEquals(john, map.get(john.eGet(id)));
    
    //Since john was added before johnDoe, retrieving john should not
    //add johnDoe to the map
    assertEquals(john, resource.getEObject("1"));
    assertEquals(1, map.size());
    assertEquals(john, map.get(john.eGet(id)));

    //Adding mary again but removing her ID first so she is not added to the map
    mary.eSet(id, null);
    resource.getContents().add(mary);
    assertEquals(1, map.size());
    assertEquals(john, map.get(john.eGet(id)));
    
    //Setting mary's ID
    mary.eSet(id, "2");
    
    //Now mary was added after johnDoe so retrieving Mary should add
    //johnDoe to the map
    assertEquals(mary, resource.getEObject("2"));
    assertEquals(3, map.size());
    assertEquals(john, map.get(john.eGet(id)));
    assertEquals(mary, map.get("2"));
    assertEquals(johnDoe, map.get(johnDoe.eGet(id)));
  }
  
  public void testXMLResourceIDCache() throws Exception
  { 
    XMLResource xmlResource = new XMLResourceImpl();
    
    //Setting an external ID for john
    xmlResource.setID(john, "externalIDForJohn");
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    assertEquals("externalIDForJohn", xmlResource.getID(john));
    
    //Setting the id doesn't add the object to the resource
    assertTrue(xmlResource.getContents().isEmpty());
    
    xmlResource.getContents().add(john);
    xmlResource.getContents().add(mary);
    
    //Only john can be retrieved by an external ID
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    
    //Everybody can be retrieved by their IDs (XMLResource inherits this from Resource)
    assertEquals(john, xmlResource.getEObject((String)john.eGet(id)));
    assertEquals(mary, xmlResource.getEObject((String)mary.eGet(id)));
    
    //Setting mary's external ID
    xmlResource.setID(mary, "MARY");
    
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    assertEquals(mary, xmlResource.getEObject("MARY"));
    assertEquals("externalIDForJohn", xmlResource.getID(john));
    assertEquals("MARY", xmlResource.getID(mary));
    
    //john and mary can be retrieved by their external IDs
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    assertEquals(mary, xmlResource.getEObject("MARY"));
    
    //Removing mary's external id
    xmlResource.setID(mary, null);

    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    assertNull(xmlResource.getEObject("MARY"));
    assertEquals("externalIDForJohn", xmlResource.getID(john));
    assertNull(xmlResource.getID(mary));
 
    //Setting mary's external ID again
    xmlResource.setID(mary, "MARY");
    //john and mary can be retrieved by their external IDs
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    assertEquals(mary, xmlResource.getEObject("MARY"));
    
    //Removing mary from the resource
    xmlResource.getContents().remove(mary);
    assertEquals(1, xmlResource.getContents().size());
    
    //Mary should not be in the maps.
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    assertEquals("externalIDForJohn", xmlResource.getID(john));
    assertNull(xmlResource.getEObject("MARY"));
    assertNull(xmlResource.getID(mary));
  }

  /*
   * IMPORTANT:  This is a test!!  The maps IDToEObject and EObjectToID should never
   * be used directly.  The get methods may be removed in future versions of EMF.
   */
  public void testXMLResourceImplIDCache() throws Exception
  { 
    XMLResource xmlResource = new XMLResourceImpl();
    
    //Setting an external ID for john
    xmlResource.setID(john, "externalIDForJohn");
    assertEquals(1, ((XMLResourceImpl)xmlResource).getIDToEObjectMap().size());
    assertEquals(john, ((XMLResourceImpl)xmlResource).getIDToEObjectMap().get("externalIDForJohn"));
    assertEquals(1, ((XMLResourceImpl)xmlResource).getEObjectToIDMap().size());
    assertEquals("externalIDForJohn", ((XMLResourceImpl)xmlResource).getEObjectToIDMap().get(john));
    
    //Setting the id doesn't add the object to the resource
    assertTrue(xmlResource.getContents().isEmpty());
    
    xmlResource.getContents().add(john);
    xmlResource.getContents().add(mary);
    xmlResource.getContents().add(johnDoe);
    
    //Only john can be retrieved by an external ID
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    
    //Everybody can be retrieved by their IDs (XMLResource inherits this from Resource)
    assertEquals(john, xmlResource.getEObject((String)john.eGet(id)));
    assertEquals(mary, xmlResource.getEObject((String)mary.eGet(id)));
    
    //Setting mary's external ID
    xmlResource.setID(mary, "MARY");
    
    assertEquals(2, ((XMLResourceImpl)xmlResource).getIDToEObjectMap().size());
    assertEquals(john, ((XMLResourceImpl)xmlResource).getIDToEObjectMap().get("externalIDForJohn"));
    assertEquals(mary, ((XMLResourceImpl)xmlResource).getIDToEObjectMap().get("MARY"));
    assertEquals(2, ((XMLResourceImpl)xmlResource).getEObjectToIDMap().size());
    assertEquals("externalIDForJohn", ((XMLResourceImpl)xmlResource).getEObjectToIDMap().get(john));
    assertEquals("MARY", ((XMLResourceImpl)xmlResource).getEObjectToIDMap().get(mary));
    
    //john and mary can be retrieved by their external IDs
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    assertEquals(mary, xmlResource.getEObject("MARY"));
    
    //Removing mary's external id
    xmlResource.setID(mary, null);

    assertEquals(1, ((XMLResourceImpl)xmlResource).getIDToEObjectMap().size());
    assertEquals(john, ((XMLResourceImpl)xmlResource).getIDToEObjectMap().get("externalIDForJohn"));
    assertNull(((XMLResourceImpl)xmlResource).getIDToEObjectMap().get("MARY"));
    assertEquals(1, ((XMLResourceImpl)xmlResource).getEObjectToIDMap().size());
    assertEquals("externalIDForJohn", ((XMLResourceImpl)xmlResource).getEObjectToIDMap().get(john));
    assertNull(((XMLResourceImpl)xmlResource).getEObjectToIDMap().get(mary));
    
    //Setting mary's external ID again
    xmlResource.setID(mary, "MARY");
    //john and mary can be retrieved by their external IDs
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    assertEquals(mary, xmlResource.getEObject("MARY"));    
    
    //Removing mary
    xmlResource.getContents().remove(mary);
    assertEquals(2, xmlResource.getContents().size());
    
    //Mary should not be in the maps.
    assertEquals(1, ((XMLResourceImpl)xmlResource).getIDToEObjectMap().size());
    assertEquals(john, ((XMLResourceImpl)xmlResource).getIDToEObjectMap().get("externalIDForJohn"));
    assertEquals(1, ((XMLResourceImpl)xmlResource).getEObjectToIDMap().size());
    assertEquals("externalIDForJohn", ((XMLResourceImpl)xmlResource).getEObjectToIDMap().get(john));
  }

  public void testAllIDCaches() throws Exception
  {
    XMLResource xmlResource = new XMLResourceImpl();
    
    //Setting an external ID for john
    xmlResource.setID(john, "externalIDForJohn");
    
    //Setting the map before adding the objects
    Map<String, EObject> map = new HashMap<String, EObject>();
    ((ResourceImpl)xmlResource).setIntrinsicIDToEObjectMap(map);
        
    xmlResource.getContents().add(john);
    xmlResource.getContents().add(mary);
    
    //Unsetting johnDoe's id
    johnDoe.eSet(id, null);
    xmlResource.getContents().add(johnDoe);
    
    //john with external ID, mary with instrinsic ID, johnDoe without any ID
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    assertEquals("externalIDForJohn", xmlResource.getID(john));
    assertEquals(mary, xmlResource.getEObject((String)mary.eGet(id)));
    assertNull(xmlResource.getID(mary));
    assertNull(xmlResource.getID(johnDoe));
    assertEquals(2, map.size());
    assertEquals(john, map.get(john.eGet(id)));
    assertEquals(mary, map.get(mary.eGet(id)));
    
    // Unsetting john's ID
    xmlResource.setID(john, null);
    
    // john and mary with instrinsic IDs, johnDoe without any ID
    assertNull(xmlResource.getEObject("externalIDForJohn"));
    assertNull(xmlResource.getID(john));
    assertEquals(john, xmlResource.getEObject((String)john.eGet(id)));
    assertEquals(2, map.size());
    assertEquals(john, map.get(john.eGet(id)));
    assertEquals(mary, map.get(mary.eGet(id)));

    xmlResource.setID(john, "externalIDForJohn");
    
    //john with external ID, mary with instrinsic ID, johnDoe without any ID
    assertEquals(john, xmlResource.getEObject("externalIDForJohn"));
    assertEquals("externalIDForJohn", xmlResource.getID(john));
    assertEquals(mary, xmlResource.getEObject((String)mary.eGet(id)));
    assertNull(xmlResource.getID(mary));
    assertNull(xmlResource.getID(johnDoe));
    assertEquals(2, map.size());
    assertEquals(john, map.get(john.eGet(id)));
    assertEquals(mary, map.get(mary.eGet(id)));
    
    xmlResource.getContents().remove(john);
    
    assertNull(xmlResource.getEObject("externalIDForJohn"));
    assertNull(xmlResource.getID(john));
    assertNull(xmlResource.getEObject((String)john.eGet(id)));
    assertEquals(mary, xmlResource.getEObject((String)mary.eGet(id)));
    assertEquals(1, map.size());
    assertEquals(mary, map.get(mary.eGet(id)));
  }
  
  public void testResourceSetURICache() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    
    Resource resource1 = new ResourceImpl();
    resource1.setURI(URI.createFileURI("f1"));
    resource1.getContents().add(john);
    resource1.getContents().add(mary);
    
    Resource resource2 = new ResourceImpl();
    resource2.setURI(URI.createURI("http://www.eclipse.org/emf"));
    resource2.getContents().add(johnDoe);
        
    Map<URI, Resource> map = new HashMap<URI, Resource>();
    ((ResourceSetImpl)resourceSet).setURIResourceMap(map);
    
    resourceSet.getResources().add(resource1);
    resourceSet.getResources().add(resource2);
    
    assertTrue(map.isEmpty());
    
    assertEquals(resource1, resourceSet.getResource(URI.createFileURI("f1"), false));
    assertEquals(1, map.size());
    assertEquals(resource1, map.get(URI.createFileURI("f1")));
    assertEquals(john, resourceSet.getEObject(URI.createFileURI("f1").appendFragment(resource1.getURIFragment(john)), false));
    assertEquals(1, map.size());
    assertEquals(resource1, map.get(URI.createFileURI("f1")));

    assertEquals(johnDoe, resourceSet.getEObject(resource2.getURI().appendFragment(resource2.getURIFragment(johnDoe)), false));
    assertEquals(2, map.size());
    assertEquals(resource1, map.get(URI.createFileURI("f1")));
    assertEquals(resource2, map.get(resource2.getURI()));
    
    resourceSet.getResources().remove(resource1);
    
    assertEquals(1, map.size());
    assertEquals(resource2, map.get(resource2.getURI()));
    
    resourceSet.getResources().clear();
    assertTrue(map.isEmpty());
  }
  
  public void testXMLResourceIDCacheWithContainment() throws Exception
  {
    EClass person = john.eClass();
    EPackage pack = person.getEPackage();
    
    EClass company = EcoreFactory.eINSTANCE.createEClass();
    company.setName("company");
    pack.getEClassifiers().add(company);
    
    EReference employees = EcoreFactory.eINSTANCE.createEReference();
    company.getEStructuralFeatures().add(employees);
    employees.setName("employees");
    employees.setContainment(true);
    employees.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    employees.setEType(person);
    
    EObject company1 = pack.getEFactoryInstance().create(company);
    @SuppressWarnings("unchecked")
    List<EObject> company1Employees = ((List<EObject>)company1.eGet(employees));
    company1Employees.add(john);
    assertEquals(company1, john.eContainer());
    
    XMLResource xmlResource = new XMLResourceImpl();
    xmlResource.getContents().add(company1);
    assertEquals(xmlResource, company1.eResource());
    assertEquals(john, xmlResource.getEObject((String)john.eGet(id)));
    
    xmlResource.setID(john, "JOHN");
    assertEquals(john, xmlResource.getEObject("JOHN"));
    
    EObject company2 = pack.getEFactoryInstance().create(company);
    @SuppressWarnings("unchecked")
    List<EObject> company2Employees = ((List<EObject>)company2.eGet(employees));
    company2Employees.add(john);
    assertEquals(company2, john.eContainer());
    assertTrue(((List<?>)company1.eGet(employees)).isEmpty());
    
    assertTrue(((XMLResourceImpl)xmlResource).getEObjectToIDMap().isEmpty());
    assertTrue(((XMLResourceImpl)xmlResource).getIDToEObjectMap().isEmpty());
    assertNull(xmlResource.getEObject("JOHN"));
  }
}