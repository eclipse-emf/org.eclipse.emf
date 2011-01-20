/**
 * <copyright>
 *
 * Copyright (c) 2009 TIBCO Software Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Adrian Price
 *
 * </copyright>
 *
 * $Id: SwitchTest.java,v 1.1 2011/01/20 01:09:56 emerks Exp $
 */
package org.eclipse.emf.test.core.ecore;


import static org.eclipse.emf.test.models.switch1.Switch1Package.Literals.*;
import static org.eclipse.emf.test.models.switch2.Switch2Package.Literals.*;
import static org.eclipse.emf.test.models.switch3.Switch3Package.Literals.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.emf.test.models.switch1.EClass0;
import org.eclipse.emf.test.models.switch1.EClass1;
import org.eclipse.emf.test.models.switch1.util.Switch1Switch;
import org.eclipse.emf.test.models.switch2.EClass2;
import org.eclipse.emf.test.models.switch2.EClass3;
import org.eclipse.emf.test.models.switch2.util.Switch2Switch;
import org.eclipse.emf.test.models.switch3.EClass4;
import org.eclipse.emf.test.models.switch3.EClass5;
import org.eclipse.emf.test.models.switch3.util.Switch3Switch;


public class SwitchTest extends TestCase
{
  private static final class TestSwitch1 extends Switch1Switch<String>
  {
    @Override
    public String caseEClass0(EClass0 object)
    {
      return retval("TestSwitch1.caseEClass0", object);
    }

    @Override
    public String caseEClass1(EClass1 object)
    {
      return retval("TestSwitch1.caseEClass1", object);
    }

    @Override
    public String defaultCase(EObject object)
    {
      return retval("TestSwitch1.defaultCase", object);
    }
  }

  private static final class TestSwitch2 extends Switch2Switch<String>
  {
    @Override
    public String caseEClass0(EClass0 object)
    {
      return retval("TestSwitch2.caseEClass0", object);
    }

    @Override
    public String caseEClass1(EClass1 object)
    {
      return retval("TestSwitch2.caseEClass1", object);
    }

    @Override
    public String caseEClass2(EClass2 object)
    {
      return retval("TestSwitch2.caseEClass2", object);
    }

    @Override
    public String caseEClass3(EClass3 object)
    {
      return retval("TestSwitch2.caseEClass3", object);
    }

    @Override
    public String defaultCase(EObject object)
    {
      return retval("TestSwitch2.defaultCase", object);
    }
  }

  private static final class TestSwitch3 extends Switch3Switch<String>
  {
    @Override
    public String caseEClass0(EClass0 object)
    {
      return retval("TestSwitch3.caseEClass0", object);
    }

    @Override
    public String caseEClass1(EClass1 object)
    {
      return retval("TestSwitch3.caseEClass1", object);
    }

    @Override
    public String caseEClass2(EClass2 object)
    {
      return retval("TestSwitch3.caseEClass2", object);
    }

    @Override
    public String caseEClass3(EClass3 object)
    {
      return retval("TestSwitch3.caseEClass3", object);
    }

    @Override
    public String caseEClass4(EClass4 object)
    {
      return retval("TestSwitch3.caseEClass4", object);
    }

    @Override
    public String caseEClass5(EClass5 object)
    {
      return retval("TestSwitch3.caseEClass5", object);
    }

    @Override
    public String defaultCase(EObject object)
    {
      return retval("TestSwitch3.defaultCase", object);
    }
  }

  private static EObject[] eObjects;
  private static Switch<String> switch1;
  private static Switch<String> switch2;
  private static Switch<String> switch3;

  private static String retval(String prefix, EObject eObject)
  {
    return prefix + ": " + eObject.eClass().getName() + '@' + Integer.toHexString(System.identityHashCode(eObject));
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("SwitchTest");
    ts.addTest(new SwitchTest("testSwitch1"));
    ts.addTest(new SwitchTest("testSwitch2"));
    ts.addTest(new SwitchTest("testSwitch3"));
    ts.addTest(new SwitchTest("testSwitch12"));
    ts.addTest(new SwitchTest("testSwitch13"));
    ts.addTest(new SwitchTest("testSwitch23"));
    ts.addTest(new SwitchTest("testSwitch123"));
    return ts;
  }

  public SwitchTest(String name)
  {
    super(name);
  }
  
  private void doSwitches(Switch<String> switchObject, String switchIdentifier, String[] expectedPrefixes)
  {
    for (int i = 0; i < eObjects.length; i++)
    {
      String actual = switchObject.doSwitch(eObjects[i]);
      assertEquals(switchIdentifier + ".doSwitch(EClass" + i + "); ", retval(expectedPrefixes[i], eObjects[i]), actual);
    }
  }

  @Override
  public void setUp()
  {
    if (switch1 == null)
    {
      switch1 = new TestSwitch1();
      switch2 = new TestSwitch2();
      switch3 = new TestSwitch3();
      EClass[] eClasses = { ECLASS0, ECLASS1, ECLASS2, ECLASS3, ECLASS4, ECLASS5, };
      eObjects = new EObject [eClasses.length];
      for (int i = 0; i < eClasses.length; i++)
      {
        EClass eClass = eClasses[i];
        eObjects[i] = eClass.getEPackage().getEFactoryInstance().create(eClass);
      }
    }
  }

  @Override
  public void tearDown() throws Exception
  {
    // Do nothing.
  }

  public void testSwitch1()
  {
    String[] expectedPrefixes = {
      "TestSwitch1.caseEClass0",
      "TestSwitch1.caseEClass1",
      "TestSwitch1.caseEClass1",
      "TestSwitch1.caseEClass1",
      "TestSwitch1.caseEClass1",
      "TestSwitch1.caseEClass1", };
    doSwitches(switch1, "switch1", expectedPrefixes);
  }

  public void testSwitch12()
  {
    ComposedSwitch<String> switch12 = new ComposedSwitch<String>()
      {
        @Override
        public String defaultCase(EObject object)
        {
          return retval("ComposedSwitch12.defaultCase", object);
        }
      };
    switch12.addSwitch(switch1);
    switch12.addSwitch(switch2);
    String[] expectedPrefixes = {
      "TestSwitch1.caseEClass0",
      "TestSwitch1.caseEClass1",
      "TestSwitch2.caseEClass2",
      "TestSwitch2.caseEClass3",
      "TestSwitch2.caseEClass3",
      "TestSwitch2.caseEClass3", };
    doSwitches(switch12, "switch12", expectedPrefixes);
  }

  public void testSwitch123()
  {
    ComposedSwitch<String> switch123 = new ComposedSwitch<String>()
      {
        @Override
        public String defaultCase(EObject object)
        {
          return retval("ComposedSwitch123.defaultCase", object);
        }
      };
    switch123.addSwitch(switch1);
    switch123.addSwitch(switch2);
    switch123.addSwitch(switch3);
    String[] expectedPrefixes = {
      "TestSwitch1.caseEClass0",
      "TestSwitch1.caseEClass1",
      "TestSwitch2.caseEClass2",
      "TestSwitch2.caseEClass3",
      "TestSwitch3.caseEClass4",
      "TestSwitch3.caseEClass5", };
    doSwitches(switch123, "switch123", expectedPrefixes);
  }

  public void testSwitch13()
  {
    ComposedSwitch<String> switch13 = new ComposedSwitch<String>()
      {
        @Override
        public String defaultCase(EObject object)
        {
          return retval("ComposedSwitch13.defaultCase", object);
        }
      };
    switch13.addSwitch(switch1);
    switch13.addSwitch(switch3);
    String[] expectedPrefixes = {
      "TestSwitch1.caseEClass0",
      "TestSwitch1.caseEClass1",
      "TestSwitch1.caseEClass1",
      "TestSwitch1.caseEClass1",
      "TestSwitch3.caseEClass4",
      "TestSwitch3.caseEClass5", };
    doSwitches(switch13, "switch13", expectedPrefixes);
  }

  public void testSwitch2()
  {
    String[] expectedPrefixes = {
      "TestSwitch2.defaultCase",
      "TestSwitch2.defaultCase",
      "TestSwitch2.caseEClass2",
      "TestSwitch2.caseEClass3",
      "TestSwitch2.caseEClass3",
      "TestSwitch2.caseEClass3", };
    doSwitches(switch2, "switch2", expectedPrefixes);
  }

  public void testSwitch23()
  {
    ComposedSwitch<String> switch23 = new ComposedSwitch<String>()
      {
        @Override
        public String defaultCase(EObject object)
        {
          return retval("ComposedSwitch23.defaultCase", object);
        }
      };
    switch23.addSwitch(switch2);
    switch23.addSwitch(switch3);
    String[] expectedPrefixes = {
      "ComposedSwitch23.defaultCase",
      "ComposedSwitch23.defaultCase",
      "TestSwitch2.caseEClass2",
      "TestSwitch2.caseEClass3",
      "TestSwitch3.caseEClass4",
      "TestSwitch3.caseEClass5", };
    doSwitches(switch23, "switch23", expectedPrefixes);
  }

  public void testSwitch3()
  {
    String[] expectedPrefixes = {
      "TestSwitch3.defaultCase",
      "TestSwitch3.defaultCase",
      "TestSwitch3.defaultCase",
      "TestSwitch3.defaultCase",
      "TestSwitch3.caseEClass4",
      "TestSwitch3.caseEClass5", };
    doSwitches(switch3, "switch3", expectedPrefixes);
  }
}
