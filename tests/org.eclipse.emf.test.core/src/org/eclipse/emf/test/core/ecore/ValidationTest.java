/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: ValidationTest.java,v 1.2 2005/03/16 04:30:13 marcelop Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.util.Date;
import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;

import com.example.ppo.Item;
import com.example.ppo.PPOFactory;
import com.example.ppo.PPOPackage;
import com.example.ppo.PurchaseOrder;
import com.example.ppo.util.PPOValidator;


public class ValidationTest extends TestCase
{
  private PurchaseOrder purchaseOrder;

  public ValidationTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("ValidationTest");
    ts.addTest(new ValidationTest("testValidation1"));
    ts.addTest(new ValidationTest("testValidation2"));
    ts.addTest(new ValidationTest("testValidation3"));
    ts.addTest(new ValidationTest("testValidation4"));
    ts.addTest(new ValidationTest("testValidation5"));
    return ts;
  }
  
  protected void setUp()
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
    }
    purchaseOrder.setOrderDate(new Date(System.currentTimeMillis()));
  }

  public void testValidation1()
  {
    assertFalse(validateObject1(purchaseOrder));    
  }

  public void testValidation2()
  {
    assertFalse(validateObject2(purchaseOrder));    
  }
  
  public void testValidation3()
  {
    assertFalse(validateObject3(purchaseOrder));    
  }

  public void testValidation4()
  {
    assertFalse(validateObject4(purchaseOrder));    
  }

  public void testValidation5()
  {
    Diagnostic diagnostic = validateObject5(purchaseOrder);
    assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    assertEquals("org.eclipse.emf.ecore", diagnostic.getSource());
    assertEquals(0, diagnostic.getCode());
    assertEquals(1, diagnostic.getData().size());
    assertEquals(purchaseOrder, diagnostic.getData().get(0));
    
    assertEquals(5, diagnostic.getChildren().size());
    for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext();)
    {
      Diagnostic childDiagnostic = (Diagnostic)i.next();
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
    for (Iterator i = purchaseOrder.getItems().iterator(); i.hasNext();)
    {
      Item item = (Item)i.next();
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

    for (Iterator i = purchaseOrder.getItems().iterator(); i.hasNext();)
    {
      if (!validator.validateItem((Item)i.next(), null, null))
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

      for (Iterator i = eObject.eAllContents(); i.hasNext();)
      {
        if (!validator.validate((EObject)i.next(), null, null))
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
}
