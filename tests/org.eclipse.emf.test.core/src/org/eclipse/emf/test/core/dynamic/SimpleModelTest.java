/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: SimpleModelTest.java,v 1.18 2008/12/22 14:26:10 emerks Exp $
 */
package org.eclipse.emf.test.core.dynamic;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;


public class SimpleModelTest extends TestCase
{
  private EPackage companyPackage;

  private EClass employeeClass;

  private EAttribute employeeName;

  private EAttribute employeeManager;

  private EClass departmentClass;

  private EAttribute departmentName;

  private EAttribute departmentNumber;

  private EReference departmentEmployees;

  public SimpleModelTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("SimpleModelTest");
    ts.addTest(new SimpleModelTest("testPackageAndFactory"));
    ts.addTest(new SimpleModelTest("testAttributes"));
    ts.addTest(new SimpleModelTest("testReference"));
    ts.addTest(new SimpleModelTest("testMetaData"));
    ts.addTest(new SimpleModelTest("testSaveAndLoad"));
    ts.addTest(new SimpleModelTest("testSaveAndLoadZip"));
    ts.addTest(new SimpleModelTest("testProxy"));
    ts.addTest(new SimpleModelTest("testTrackingModificaiton"));
    ts.addTest(new SimpleModelTest("testAddingDuplicates"));
    ts.addTest(new SimpleModelTest("testRenamingStructuralFeature"));
    ts.addTest(new SimpleModelTest("testRenamingClassifier"));
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

    employeeClass = ecoreFactory.createEClass();
    employeeClass.setName("Employee");

    employeeName = ecoreFactory.createEAttribute();
    employeeName.setName("name");
    employeeName.setEType(ecorePackage.getEString());
    employeeClass.getEStructuralFeatures().add(employeeName);

    employeeManager = ecoreFactory.createEAttribute();
    employeeManager.setName("manager");
    employeeManager.setEType(ecorePackage.getEBoolean());
    employeeClass.getEStructuralFeatures().add(employeeManager);

    departmentClass = ecoreFactory.createEClass();
    departmentClass.setName("Department");

    departmentName = ecoreFactory.createEAttribute();
    departmentName.setName("name");
    departmentName.setEType(ecorePackage.getEString());
    departmentClass.getEStructuralFeatures().add(departmentName);

    departmentNumber = ecoreFactory.createEAttribute();
    departmentNumber.setName("number");
    departmentNumber.setEType(ecorePackage.getEInt());
    departmentClass.getEStructuralFeatures().add(departmentNumber);

    departmentEmployees = ecoreFactory.createEReference();
    departmentEmployees.setName("employees");
    departmentEmployees.setEType(employeeClass);
    departmentEmployees.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    departmentEmployees.setContainment(true);
    departmentClass.getEStructuralFeatures().add(departmentEmployees);

    companyPackage = ecoreFactory.createEPackage();
    companyPackage.setName("company");
    companyPackage.setNsPrefix("company");
    companyPackage.setNsURI("http:///com.example.company.ecore");
    companyPackage.getEClassifiers().add(employeeClass);
    companyPackage.getEClassifiers().add(departmentClass);
    EPackage.Registry.INSTANCE.put(companyPackage.getNsURI(), companyPackage);
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception
  {
    employeeName = null;
    employeeManager = null;
    employeeClass.getEStructuralFeatures().clear();
    employeeClass = null;

    departmentEmployees = null;

    departmentName = null;
    departmentNumber = null;
    departmentClass.getEStructuralFeatures().clear();
    departmentClass = null;

    companyPackage = null;
  }

  public void testPackageAndFactory()
  {
    assertNotNull(companyPackage);
    assertEquals("company", companyPackage.getName());
    assertEquals("company", companyPackage.getNsPrefix());
    assertEquals("http:///com.example.company.ecore", companyPackage.getNsURI());

    EFactory companyFactory = companyPackage.getEFactoryInstance();
    assertNotNull(companyFactory);
    assertEquals(companyPackage, companyFactory.getEPackage());

    EObject employee = companyFactory.create(employeeClass);
    assertNotNull(employee);
    assertEquals(companyPackage, employee.eClass().getEPackage());
  }

  public void testAttributes()
  {
    EFactory companyFactory = companyPackage.getEFactoryInstance();

    EObject employee1 = companyFactory.create(employeeClass);
    employee1.eSet(employeeName, "John");
    assertEquals("John", employee1.eGet(employeeName));
    assertEquals(Boolean.FALSE, employee1.eGet(employeeManager));

    EObject employee2 = companyFactory.create(employeeClass);
    employee2.eSet(employeeName, "Katherine");
    assertEquals("Katherine", employee2.eGet(employeeName));
    employee2.eSet(employeeManager, Boolean.TRUE);
    assertEquals(Boolean.TRUE, employee2.eGet(employeeManager));

    EObject department = companyFactory.create(departmentClass);
    department.eSet(departmentName, "ABC");
    assertEquals("ABC", department.eGet(departmentName));
    department.eSet(departmentNumber, 123);
    assertEquals(123, department.eGet(departmentNumber));
  }

  public void testReference()
  {
    EFactory companyFactory = companyPackage.getEFactoryInstance();

    EObject employee1 = companyFactory.create(employeeClass);
    EObject employee2 = companyFactory.create(employeeClass);

    EObject department1 = companyFactory.create(departmentClass);

    @SuppressWarnings("unchecked")
    List<EObject> department1Employees = (List<EObject>)department1.eGet(departmentEmployees);
    assertNotNull(department1Employees);

    department1Employees.add(employee1);
    assertEquals(1, department1Employees.size());
    assertEquals(employee1, department1Employees.get(0));
    assertEquals(department1Employees, department1.eGet(departmentEmployees));

    department1Employees.add(employee2);
    assertEquals(2, department1Employees.size());
    assertEquals(employee2, department1Employees.get(1));
    assertEquals(department1Employees, department1.eGet(departmentEmployees));

    //Should not affect the list
    department1Employees.add(employee2);
    assertEquals(2, department1Employees.size());
    assertEquals(employee2, department1Employees.get(1));
    assertEquals(department1Employees, department1.eGet(departmentEmployees));

    EObject department2 = companyFactory.create(departmentClass);

    @SuppressWarnings("unchecked")
    List<EObject> department2Employees = (List<EObject>)department2.eGet(departmentEmployees);
    assertNotNull(department2Employees);

    department2Employees.add(employee1);
    assertEquals(1, department2Employees.size());
    assertEquals(employee1, department2Employees.get(0));
    assertEquals(department2Employees, department2.eGet(departmentEmployees));

    //since departmentEmployees is an aggregation, employee1 should be removed
    //from department1Employees
    assertEquals(1, department1Employees.size());
    assertEquals(employee2, department1Employees.get(0));
    assertEquals(department1Employees, department1.eGet(departmentEmployees));
  }

  public void testMetaData()
  {
    EFactory companyFactory = companyPackage.getEFactoryInstance();

    EObject employee1 = companyFactory.create(employeeClass);
    EObject employee2 = companyFactory.create(employeeClass);

    EObject department1 = companyFactory.create(departmentClass);
    @SuppressWarnings("unchecked")
    List<EObject> department1Employees = (List<EObject>)department1.eGet(departmentEmployees);
    EObject department2 = companyFactory.create(departmentClass);
    @SuppressWarnings("unchecked")
    List<EObject> department2Employees = (List<EObject>)department2.eGet(departmentEmployees);

    department1Employees.add(employee1);
    assertEquals(department1, employee1.eContainer());
    assertEquals(department1Employees.size(), department1.eContents().size());

    department1Employees.add(employee2);
    assertEquals(department1, employee1.eContainer());
    assertEquals(department1Employees.size(), department1.eContents().size());

    department2Employees.add(employee1);
    assertEquals(department2, employee1.eContainer());
    assertEquals(department1Employees.size(), department1.eContents().size());
    assertEquals(department2Employees.size(), department2.eContents().size());
  }
  
  public void testSaveAndLoadZip() throws Exception
  {
    //Instanciating the model
    EFactory companyFactory = companyPackage.getEFactoryInstance();

    EObject employee1 = companyFactory.create(employeeClass);
    employee1.eSet(employeeName, "John");
    EObject employee2 = companyFactory.create(employeeClass);
    employee2.eSet(employeeName, "Jane");

    EObject department = companyFactory.create(departmentClass);
    department.eSet(departmentName, "ACME1");
    @SuppressWarnings("unchecked")
    List<EObject> employeesList = (List<EObject>)department.eGet(departmentEmployees);
    employeesList.add(employee1);
    employeesList.add(employee2);

    URI departmentsURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/departments.dept");
    XMLResource departmentsResource = (XMLResource)new XMIResourceFactoryImpl().createResource(departmentsURI);
    departmentsResource.setUseZip(true);
    departmentsResource.getContents().add(department);

    //Saving
    departmentsResource.save(Collections.EMPTY_MAP);
    assertTrue(new File(departmentsURI.toFileString()).exists());

    //Loading department in ResourceSet
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("dept", new XMIResourceFactoryImpl()
        {
          /* (non-Javadoc)
           * @see org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl#createResource(org.eclipse.emf.common.util.URI)
           */
          @Override
          public Resource createResource(URI uri)
          {
            XMLResource result = (XMLResource)super.createResource(uri);
            result.setUseZip(true);
            return result;
          }
        });

    Resource loadedResource = resourceSet.getResource(departmentsURI, true);
    assertEquals(1, resourceSet.getResources().size());
    assertEquals(1, loadedResource.getContents().size());

    EObject loadedDepartment = loadedResource.getContents().get(0);
    assertEquals(department.eClass(), loadedDepartment.eClass());
    assertEquals(department.eGet(departmentName), loadedDepartment.eGet(departmentName));

    //Get Employess
    @SuppressWarnings("unchecked")
    List<EObject> loadedEmployees = (List<EObject>)department.eGet(departmentEmployees);
    assertEquals(2, loadedEmployees.size());
    EObject loadedEmployee = loadedEmployees.get(0);
    assertEquals(employee1.eClass(), loadedEmployee.eClass());
    assertEquals(employee1.eGet(employeeName), loadedEmployee.eGet(employeeName));
    loadedEmployee = loadedEmployees.get(1);
    assertEquals(employee2.eClass(), loadedEmployee.eClass());
    assertEquals(employee2.eGet(employeeName), loadedEmployee.eGet(employeeName));

    //Deleting created files
    new File(departmentsURI.toFileString()).delete();
    assertFalse(new File(departmentsURI.toFileString()).exists());
  }

  public void testSaveAndLoad() throws Exception
  {
    //Instanciating the model
    EFactory companyFactory = companyPackage.getEFactoryInstance();

    EObject employee1 = companyFactory.create(employeeClass);
    employee1.eSet(employeeName, "John");
    EObject employee2 = companyFactory.create(employeeClass);
    employee2.eSet(employeeName, "Jane");

    EObject department = companyFactory.create(departmentClass);
    department.eSet(departmentName, "ACME1");
    @SuppressWarnings("unchecked")
    List<EObject> employeesList = (List<EObject>)department.eGet(departmentEmployees);
    employeesList.add(employee1);
    employeesList.add(employee2);

    URI departmentsURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/departments.xmi");
    Resource departmentsResource = new XMIResourceFactoryImpl().createResource(departmentsURI);
    departmentsResource.getContents().add(department);

    //Saving
    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(XMLResource.OPTION_USE_FILE_BUFFER, Boolean.TRUE);
    departmentsResource.save(options);
    assertTrue(new File(departmentsURI.toFileString()).exists());

    //Loading department in ResourceSet
    ResourceSet resourceSet = new ResourceSetImpl();
    if (!EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    }

    Resource loadedResource = resourceSet.getResource(departmentsURI, true);
    assertEquals(1, resourceSet.getResources().size());
    assertEquals(1, loadedResource.getContents().size());

    EObject loadedDepartment = loadedResource.getContents().get(0);
    assertEquals(department.eClass(), loadedDepartment.eClass());
    assertEquals(department.eGet(departmentName), loadedDepartment.eGet(departmentName));

    //Get Employess
    @SuppressWarnings("unchecked")
    List<EObject> loadedEmployees = (List<EObject>)department.eGet(departmentEmployees);
    assertEquals(2, loadedEmployees.size());
    EObject loadedEmployee = loadedEmployees.get(0);
    assertEquals(employee1.eClass(), loadedEmployee.eClass());
    assertEquals(employee1.eGet(employeeName), loadedEmployee.eGet(employeeName));
    loadedEmployee = loadedEmployees.get(1);
    assertEquals(employee2.eClass(), loadedEmployee.eClass());
    assertEquals(employee2.eGet(employeeName), loadedEmployee.eGet(employeeName));

    //Deleting created files
    new File(departmentsURI.toFileString()).delete();
    assertFalse(new File(departmentsURI.toFileString()).exists());
  }
  
  public void testProxy() throws Exception
  {
    EFactory companyFactory = companyPackage.getEFactoryInstance();
    
    //Adding a not-contained association
    EReference associateDepartments = EcoreFactory.eINSTANCE.createEReference();
    associateDepartments.setName("associateDepartments");
    associateDepartments.setContainment(false);
    associateDepartments.setEType(departmentClass);
    associateDepartments.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    departmentClass.getEStructuralFeatures().add(associateDepartments);
    
    //Instanciating the model
    EObject department = companyFactory.create(departmentClass);
    department.eSet(departmentName, "ACME1");
    @SuppressWarnings("unchecked")
    List<EObject> associateDepartmentsList = (List<EObject>)department.eGet(associateDepartments);
    URI departmentURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/department.xmi");
    Resource departmentResource = new XMIResourceFactoryImpl().createResource(departmentURI);
    departmentResource.getContents().add(department);
    
    EObject department1 = companyFactory.create(departmentClass);
    department1.eSet(departmentName, "ACME1");
    associateDepartmentsList.add(department1);
    URI department1URI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/department1.xmi");
    Resource department1Resource = new XMIResourceFactoryImpl().createResource(department1URI);
    department1Resource.getContents().add(department1);
    
    EObject department2 = companyFactory.create(departmentClass);
    department2.eSet(departmentName, "ACME2");
    associateDepartmentsList.add(department2);
    URI department2URI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/department2.xmi");
    Resource department2Resource = new XMIResourceFactoryImpl().createResource(department2URI);
    department2Resource.getContents().add(department2);
    
    //Saving
    departmentResource.save(Collections.EMPTY_MAP);
    assertTrue(new File(departmentURI.toFileString()).exists());    
    department1Resource.save(Collections.EMPTY_MAP);
    assertTrue(new File(department1URI.toFileString()).exists());    
    department2Resource.save(Collections.EMPTY_MAP);
    assertTrue(new File(department2URI.toFileString()).exists());
    
    //Loading department into a resource set
    ResourceSet resourceSet = new ResourceSetImpl();
    if (!EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    }

    Resource loadedResource = resourceSet.getResource(departmentURI, true);
    assertEquals(1, loadedResource.getContents().size());
    
    EObject loadedDepartment = loadedResource.getContents().get(0);
    @SuppressWarnings("unchecked")
    BasicEList<EObject> loadedAssociateDepartmentsList = (BasicEList<EObject>)loadedDepartment.eGet(associateDepartments, false);
    assertEquals(2, loadedAssociateDepartmentsList.size());
    
    EObject loadedDepartment1 = loadedAssociateDepartmentsList.basicGet(0);
    EObject loadedDepartment2 = loadedAssociateDepartmentsList.basicGet(1);
    
    assertTrue(loadedDepartment1.eIsProxy());
    assertEquals(department1URI.toFileString(), ((InternalEObject)loadedDepartment1).eProxyURI().toFileString());
    assertNull(loadedDepartment1.eGet(departmentName));
    assertTrue(loadedDepartment2.eIsProxy());
    assertEquals(department2URI.toFileString(), ((InternalEObject)loadedDepartment2).eProxyURI().toFileString());
    assertNull(loadedDepartment2.eGet(departmentName));
    
    //Resolving Proxy
    loadedDepartment1 = EcoreUtil.resolve(loadedDepartment1, resourceSet);
    assertFalse(loadedDepartment1.eIsProxy());
    assertEquals(department1.eGet(departmentName), loadedDepartment1.eGet(departmentName));
    loadedDepartment2 = EcoreUtil.resolve(loadedDepartment2, resourceSet);
    assertFalse(loadedDepartment2.eIsProxy());
    assertEquals(department2.eGet(departmentName), loadedDepartment2.eGet(departmentName));
    
    //Deleting created files
    new File(departmentURI.toFileString()).delete();
    assertFalse(new File(departmentURI.toFileString()).exists());
    new File(department1URI.toFileString()).delete();
    assertFalse(new File(department1URI.toFileString()).exists());
    new File(department2URI.toFileString()).delete();
    assertFalse(new File(department2URI.toFileString()).exists());
  }
  
  /*
   * Bugzilla 80110
   */
  public void testTrackingModificaiton() throws Exception
  {
    Resource resource = new ResourceImpl()
    {
      @Override
      protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException
      {
        // Ignore
      }
    };
    assertFalse(resource.isTrackingModification());
    assertFalse(resource.isModified());
    
    EFactory companyFactory = companyPackage.getEFactoryInstance();
    EObject employee = companyFactory.create(employeeClass);
    EObject department = companyFactory.create(departmentClass);
    @SuppressWarnings("unchecked")
    List<EObject> employess = ((List<EObject>)department.eGet(departmentEmployees));
    employess.add(employee);
    
    resource.getContents().add(department);
    assertFalse(resource.isTrackingModification());
    assertFalse(resource.isModified());

    resource.setTrackingModification(true);
    assertTrue(resource.isTrackingModification());
    assertFalse(resource.isModified());
    
    employee.eSet(employeeName, "John");
    assertTrue(resource.isTrackingModification());
    assertTrue(resource.isModified());
    
    resource.save(new ByteArrayOutputStream(), null);
    assertTrue(resource.isTrackingModification());
    assertFalse(resource.isModified());
    
    resource.setTrackingModification(false);
    assertFalse(resource.isTrackingModification());
    assertFalse(resource.isModified());    

    employee.eSet(employeeName, "Joe");
    assertEquals(resource, employee.eResource());
    assertFalse(resource.isTrackingModification());
    assertFalse(resource.isModified());
    
    resource.setTrackingModification(true);
    assertTrue(resource.isTrackingModification());
    assertFalse(resource.isModified());
   
    EObject employee1 = companyFactory.create(employeeClass);
    EObject department1 = companyFactory.create(departmentClass);
    @SuppressWarnings("unchecked")
    List<EObject> department1Employees = ((List<EObject>)department1.eGet(departmentEmployees));
    department1Employees.add(employee1);
    resource.getContents().add(department1);
    assertTrue(resource.isTrackingModification());
    assertTrue(resource.isModified());

    resource.save(new ByteArrayOutputStream(), null);
    assertTrue(resource.isTrackingModification());
    assertFalse(resource.isModified());
    
    employee1.eSet(employeeName, "Mike");
    assertTrue(resource.isTrackingModification());
    assertTrue(resource.isModified());

    resource.save(new ByteArrayOutputStream(), null);
    assertTrue(resource.isTrackingModification());
    assertFalse(resource.isModified());

    resource.setTrackingModification(false);
    assertFalse(resource.isTrackingModification());
    assertFalse(resource.isModified());    

    employee1.eSet(employeeName, "Mark");
    assertEquals(resource, employee1.eResource());
    assertFalse(resource.isTrackingModification());
    assertFalse(resource.isModified());    
  }
  
  /*
   * Bugzilla 106702
   */
  public void testAddingDuplicates() throws Exception
  {
    EFactory companyFactory = companyPackage.getEFactoryInstance();

    EObject department1 = companyFactory.create(departmentClass);
    @SuppressWarnings("unchecked")
    List<EObject> department1Employees = (List<EObject>)department1.eGet(departmentEmployees);
    assertEquals(0, department1Employees.size());

    EObject employee1 = companyFactory.create(employeeClass);
    department1Employees.add(employee1);
    
    Exception exception = null;
    try
    {    
      department1Employees.add(0, null);
    }
    catch(IllegalArgumentException iae)
    {
      exception = iae;
    }
    assertNotNull(exception);
      
    assertEquals(1, department1Employees.size());
    assertEquals(0, department1Employees.indexOf(employee1));
    
    Object[] data = ((BasicEList<?>)department1Employees).data();
    assertEquals(employee1, data[0]);
    assertNull(data[1]);
  }
  
  public void testRenamingStructuralFeature() 
  {
    String COST_CENTER_NAME = "costCenter";
    String CHANGED_COST_CENTER_NAME = "changedCostCenter";
     
    EAttribute costCenterAttribute = EcoreFactory.eINSTANCE.createEAttribute();
    costCenterAttribute.setName( COST_CENTER_NAME );
     
    departmentClass.getEStructuralFeatures().add( costCenterAttribute );
     
    assertNotNull( departmentClass.getEStructuralFeature( COST_CENTER_NAME ) );
     
    costCenterAttribute.setName( CHANGED_COST_CENTER_NAME );
   
    assertEquals( CHANGED_COST_CENTER_NAME, costCenterAttribute.getName() );
    assertNotNull( departmentClass.getEStructuralFeature( CHANGED_COST_CENTER_NAME ) );
  }

  public void testRenamingClassifier() 
  {
    assertNotNull(companyPackage.getEClassifier("Employee"));
    employeeClass.setName("Employee1");
    assertNotNull(companyPackage.getEClassifier("Employee1"));
  }
}