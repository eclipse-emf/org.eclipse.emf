/**
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.Iterator;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.test.models.ppo.Item;
import org.eclipse.emf.test.models.ppo.PPOFactory;
import org.eclipse.emf.test.models.ppo.PPOPackage;
import org.eclipse.emf.test.models.ppo.PurchaseOrder;
import org.eclipse.emf.test.models.ppo.util.PPOValidator;
import org.junit.Before;
import org.junit.Test;


public class ValidationTest
{
  private PurchaseOrder purchaseOrder;

  @Before
  public void setUp()
  {
    Item item = PPOFactory.eINSTANCE.createItem();
    item.setProductName("Tires");
    item.setUSPrice(50);
    item.setPartNum("ABC-1234");
    item.setShipDate(new Date(System.currentTimeMillis()));
    //Negative quantity
    item.setQuantity(-4);

    purchaseOrder = PPOFactory.eINSTANCE.createPurchaseOrder();
    //USAddress without state
    purchaseOrder.setBillTo(PPOFactory.eINSTANCE.createUSAddress());
    //USAddress without state
    purchaseOrder.setShipTo(PPOFactory.eINSTANCE.createUSAddress());

    //The model specifies 2 or more items
    purchaseOrder.getItems().add(item);

    //Order earlier than ship
    try
    {
      Thread.sleep(50);
    }
    catch (InterruptedException e)
    {
      // Ignore
    }
    purchaseOrder.setOrderDate(new Date(System.currentTimeMillis()));
  }

  @Test
  public void testValidation1()
  {
    assertFalse(validateObject1(purchaseOrder));
  }

  @Test
  public void testValidation2()
  {
    assertFalse(validateObject2(purchaseOrder));
  }

  @Test
  public void testValidation3()
  {
    assertFalse(validateObject3(purchaseOrder));
  }

  @Test
  public void testValidation4()
  {
    assertFalse(validateObject4(purchaseOrder));
  }

  @Test
  public void testValidation5()
  {
    Diagnostic diagnostic = validateObject5(purchaseOrder);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals("org.eclipse.emf.ecore", diagnostic.getSource());
    assertEquals(0, diagnostic.getCode());
    assertEquals(1, diagnostic.getData().size());
    assertEquals(purchaseOrder, diagnostic.getData().get(0));

    assertEquals(5, diagnostic.getChildren().size());
    for (Diagnostic childDiagnostic : diagnostic.getChildren())
    {
      assertEquals(Diagnostic.ERROR, childDiagnostic.getSeverity());
      if (childDiagnostic.getData().get(0) == purchaseOrder.getBillTo())
      {
        assertEquals(1, childDiagnostic.getData().size());
        assertEquals(PPOValidator.DIAGNOSTIC_SOURCE, childDiagnostic.getSource());
        assertEquals(PPOValidator.US_ADDRESS__HAS_US_STATE, childDiagnostic.getCode());
        assertTrue(childDiagnostic.getMessage().indexOf("hasUSState") >= 0);
      }
      else if (childDiagnostic.getData().get(0) == purchaseOrder.getShipTo())
      {
        assertEquals(1, childDiagnostic.getData().size());
        assertEquals(PPOValidator.DIAGNOSTIC_SOURCE, childDiagnostic.getSource());
        assertEquals(PPOValidator.US_ADDRESS__HAS_US_STATE, childDiagnostic.getCode());
        assertTrue(childDiagnostic.getMessage().indexOf("hasUSState") >= 0);
      }
      else if (childDiagnostic.getData().get(0) == purchaseOrder.getItems().get(0))
      {
        assertEquals(1, childDiagnostic.getData().size());
        assertEquals(PPOValidator.DIAGNOSTIC_SOURCE, childDiagnostic.getSource());
        assertEquals(0, childDiagnostic.getCode());
        assertTrue(childDiagnostic.getMessage().indexOf("ValidShipDate") >= 0  || childDiagnostic.getMessage().indexOf("NonNegativeQuantity") >= 0 );
      }
      else if (childDiagnostic.getSource() == EObjectValidator.DIAGNOSTIC_SOURCE)
      {
        assertEquals(EObjectValidator.EOBJECT__EVERY_MULTIPCITY_CONFORMS, childDiagnostic.getCode());
        assertEquals(2, childDiagnostic.getData().size());
        assertTrue(childDiagnostic.getData().contains(purchaseOrder));
        assertTrue(childDiagnostic.getData().contains(PPOPackage.eINSTANCE.getPurchaseOrder_Items()));

      }
      else
      {
        fail("Unexpected childDiagnostic.getData().get(0): " + childDiagnostic.getData().get(0));
      }
    }
  }

  //validateObject5 is the recommended implementation to
  //invoke the contraints and invariants associated with an object
  public static boolean validateObject1(PurchaseOrder purchaseOrder)
  {
    if (!purchaseOrder.getBillTo().hasUSState(null, null) && !purchaseOrder.getShipTo().hasUSState(null, null))
    {
      return false;
    }

    PPOValidator validator = PPOValidator.INSTANCE;
    for (Item item : purchaseOrder.getItems())
    {
      if (!validator.validateItem_NonNegativeQuantity(item, null, null) && !validator.validateItem_ValidShipDate(item, null, null))
      {
        return false;
      }
    }

    return true;
  }

  //validateObject5 is the recommended implementation to
  //invoke the contraints and invariants associated with an object
  public static boolean validateObject2(PurchaseOrder purchaseOrder)
  {
    PPOValidator validator = PPOValidator.INSTANCE;

    if (!validator.validatePurchaseOrder(purchaseOrder, null, null))
    {
      return false;
    }
    if (!validator.validateUSAddress(purchaseOrder.getBillTo(), null, null))
    {
      return false;
    }
    if (!validator.validateUSAddress(purchaseOrder.getShipTo(), null, null))
    {
      return false;
    }

    for (Item item : purchaseOrder.getItems())
    {
      if (!validator.validateItem(item, null, null))
      {
        return false;
      }
    }

    return true;
  }

  //validateObject5 is the recommended implementation to
  //invoke the contraints and invariants associated with an object
  public static boolean validateObject3(EObject eObject)
  {
    EValidator validator = EValidator.Registry.INSTANCE.getEValidator(eObject.eClass().getEPackage());
    if (validator != null)
    {
      if (!validator.validate(eObject, null, null))
      {
        return false;
      }

      for (Iterator<EObject> i = eObject.eAllContents(); i.hasNext();)
      {
        if (!validator.validate(i.next(), null, null))
        {
          return false;
        }
      }
    }

    return true;
  }

  //validateObject5 is the recommended implementation to
  //invoke the contraints and invariants associated with an object
  public static boolean validateObject4(EObject eObject)
  {
    Diagnostician diagnostician = new Diagnostician();
    Diagnostic diagnostic = diagnostician.validate(eObject);
    return diagnostic.getSeverity() == Diagnostic.OK;
  }

  //This is the recommended implementation to
  //invoke the contraints and invariants associated with an object
  public static Diagnostic validateObject5(EObject eObject)
  {
    Diagnostician diagnostician = new Diagnostician();
    return diagnostician.validate(eObject);
  }

  /*
   * Bugzilla 124670
   */
  @Test
  public void testRequiredField() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");

    EClass person = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(person);
    person.setName("Person");

    EAttribute age = EcoreFactory.eINSTANCE.createEAttribute();
    person.getEStructuralFeatures().add(age);
    age.setName("age");
    age.setEType(EcorePackage.Literals.EINT);
    age.setLowerBound(1);

    EAttribute name = EcoreFactory.eINSTANCE.createEAttribute();
    person.getEStructuralFeatures().add(name);
    name.setName("name");
    name.setEType(EcorePackage.Literals.ESTRING);
    name.setLowerBound(1);
    name.setDefaultValue("Joe Doe");

    EAttribute numberOfChildren = EcoreFactory.eINSTANCE.createEAttribute();
    person.getEStructuralFeatures().add(numberOfChildren);
    numberOfChildren.setName("numberOfChildren");
    numberOfChildren.setEType(EcorePackage.Literals.EINT);
    numberOfChildren.setLowerBound(1);
    numberOfChildren.setDefaultValue(0);
    numberOfChildren.setUnsettable(true);

    EAttribute leftHanded = EcoreFactory.eINSTANCE.createEAttribute();
    person.getEStructuralFeatures().add(leftHanded);
    leftHanded.setName("leftHanded");
    leftHanded.setEType(EcorePackage.Literals.EBOOLEAN);
    leftHanded.setLowerBound(1);

    EAttribute smart = EcoreFactory.eINSTANCE.createEAttribute();
    person.getEStructuralFeatures().add(smart);
    smart.setName("smart");
    smart.setEType(EcorePackage.Literals.EBOOLEAN);
    smart.setLowerBound(1);
    smart.setDefaultValue(Boolean.TRUE);

    EObject john = EcoreUtil.create(person);
    assertEquals(Diagnostic.ERROR, Diagnostician.INSTANCE.validate(john).getSeverity());
    john.eSet(numberOfChildren, 0); //<== default value
    assertEquals(Diagnostic.OK, Diagnostician.INSTANCE.validate(john).getSeverity());

    john.eUnset(age); //<== uses the int intrinsic default
    assertEquals(Diagnostic.OK, Diagnostician.INSTANCE.validate(john).getSeverity());
    john.eSet(age, 30);
    assertEquals(Diagnostic.OK, Diagnostician.INSTANCE.validate(john).getSeverity());

    john.eSet(name, null);
    assertEquals(Diagnostic.ERROR, Diagnostician.INSTANCE.validate(john).getSeverity());
    john.eSet(name, "john");
    assertEquals(Diagnostic.OK, Diagnostician.INSTANCE.validate(john).getSeverity());
    john.eUnset(name);
    assertEquals(Diagnostic.OK, Diagnostician.INSTANCE.validate(john).getSeverity());
    john.eSet(name, "Joe Doe"); //<== default value
    assertEquals(Diagnostic.OK, Diagnostician.INSTANCE.validate(john).getSeverity());

    john.eUnset(numberOfChildren);
    assertEquals(Diagnostic.ERROR, Diagnostician.INSTANCE.validate(john).getSeverity());
    john.eSet(numberOfChildren, 4);
    assertEquals(Diagnostic.OK, Diagnostician.INSTANCE.validate(john).getSeverity());
    john.eSet(numberOfChildren, null);
    assertEquals(Diagnostic.ERROR, Diagnostician.INSTANCE.validate(john).getSeverity());
  }

  @Test
  public void defaultValueLiteralValidationTest() throws RuntimeException
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    ePackage.getEClassifiers().add(eClass);
    EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
    eClass.getEStructuralFeatures().add(eAttribute);
    eAttribute.setDefaultValueLiteral("");
    EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
    eAttribute.setEType(eDataType);

    try
    {
      EcoreValidator.INSTANCE.validateEStructuralFeature_ValidDefaultValueLiteral(eAttribute, new BasicDiagnostic(), null);
    }
    catch (RuntimeException ex)
    {
      throw ex;
    }

    eDataType.setInstanceClass(String.class);
    ePackage.getEClassifiers().add(eDataType);

    try
    {
      eAttribute.getDefaultValue();
      ePackage.setEFactoryInstance(null);
      EcoreValidator.INSTANCE.validateEStructuralFeature_ValidDefaultValueLiteral(eAttribute, new BasicDiagnostic(), null);
    }
    catch (RuntimeException ex)
    {
      throw ex;
    }
  }
}
