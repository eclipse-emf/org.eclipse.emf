/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: SimpleModelTest.java,v 1.3 2004/03/25 04:16:08 marcelop Exp $
 */
package org.eclipse.emf.test.core.dynamic;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;


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
    TestSuite ts = new TestSuite();
    ts.addTest(new SimpleModelTest("testPackageAndFactory"));
    ts.addTest(new SimpleModelTest("testAttributes"));
    ts.addTest(new SimpleModelTest("testReference"));
    ts.addTest(new SimpleModelTest("testMetaData"));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
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
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
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
    department.eSet(departmentNumber, new Integer(123));
    assertEquals(new Integer(123), department.eGet(departmentNumber));
  }

  public void testReference()
  {
    EFactory companyFactory = companyPackage.getEFactoryInstance();

    EObject employee1 = companyFactory.create(employeeClass);
    EObject employee2 = companyFactory.create(employeeClass);

    EObject department1 = companyFactory.create(departmentClass);

    List department1Employees = (List)department1.eGet(departmentEmployees);
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

    List department2Employees = (List)department2.eGet(departmentEmployees);
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
    List department1Employees = (List)department1.eGet(departmentEmployees);
    EObject department2 = companyFactory.create(departmentClass);
    List department2Employees = (List)department2.eGet(departmentEmployees);

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
}