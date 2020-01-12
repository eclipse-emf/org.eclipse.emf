/**
 * Copyright (c) 2020 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.core.ecore;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.junit.Test;


public class BadURIFragmentTest
{
  @Test
  public void testMissingAt()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEObject();
    try
    {
      eObject.eObjectForURIFragmentSegment("");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting @ at index 0 of ''", exception.getMessage());
    }
  }

  @Test
  public void testMissingOpeningBracket()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEObject();
    try
    {
      eObject.eObjectForURIFragmentSegment("@foo]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting [ in '@foo]'", exception.getMessage());
    }
  }

  @Test
  public void testMissingEquals()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[ ]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting = at or after index 0 of ' '", exception.getMessage());
    }
  }

  @Test
  public void testMissingClosingSingleQuote()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name='bar]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting ' at or after index 6 of 'name='bar'", exception.getMessage());
    }
  }

  @Test
  public void testMissingClosingDoubleQuote()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name=\"bar]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting \" at or after index 6 of 'name=\"bar'", exception.getMessage());
    }
  }

  @Test
  public void testBadValue()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name=bar]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting ', \", [, or null at index 5 of 'name=bar'", exception.getMessage());
    }
  }

  @Test
  public void testBadNullValue()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name=nul]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting null at index 5 of 'name=nul'", exception.getMessage());
    }
  }

  @Test
  public void testBadValueJunk()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name='' ]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting , at index 7 of 'name='' '", exception.getMessage());
    }
  }

  @Test
  public void testBadListValueUnterminatedStart()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name=[]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting ', \", ], or null at index 6 of 'name=['", exception.getMessage());
    }
  }
  
  @Test
  public void testBadListValueUnterminatedStart2()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name=['foo']");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting , or ] at index 11 of 'name=['foo''", exception.getMessage());
    }
  }

  @Test
  public void testBadListValueUnterminatedSingleQuote()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name=[']]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting ' at or after index 7 of 'name=[']'", exception.getMessage());
    }
  }
  

  @Test
  public void testBadListValueUnterminatedDoubleQuote()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name=[\"]]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting \" at or after index 7 of 'name=[\"]'", exception.getMessage());
    }
  }

  @Test
  public void testBadListValue()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name=[bar]]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting ', \", ], or null at index 6 of 'name=[bar]'", exception.getMessage());
    }
  }

  @Test
  public void testBadListNullValue()
  {
    InternalEObject eObject = (InternalEObject)EcoreFactory.eINSTANCE.createEClass();
    try
    {
      eObject.eObjectForURIFragmentSegment("@eSuperTypes[name=[nul]]");
      fail("Exception expected");
    }
    catch (RuntimeException exception)
    {
      assertEquals("Expecting null at index 6 of 'name=[nul]'", exception.getMessage());
    }
  }
}
