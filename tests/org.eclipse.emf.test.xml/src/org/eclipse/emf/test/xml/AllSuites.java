/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: AllSuites.java,v 1.2.2.5 2007/06/01 20:30:34 emerks Exp $
 */
package org.eclipse.emf.test.xml;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.test.xml.perf.TestXMLSave;


public class AllSuites extends TestSuite
{
  private static Test[] suites = new Test []{ 
    org.eclipse.emf.test.xml.xmi.NamespaceTest.suite()
    ,org.eclipse.emf.test.xml.xmi.OrderTest.suite()
    ,org.eclipse.emf.test.xml.xmi.QNameTest.suite()
    ,TestXMLSave.suite()
    //,CrossResourceReferencesTest.suite() // not supported in 2.0.2 (yet)
    //,org.eclipse.emf.test.xml.xsdecore.XSD2EcoreTest.suite() // not supported in 2.0.2 (yet)
    //,org.eclipse.emf.test.xml.xsdecore.XSDValidateTest.suite() // not supported in 2.0.2 (yet)
  };

  public static Test suite()
  {
    return new AllSuites("XML JUnit Test Suite");
  }

  public AllSuites()
  {
    super();
    populateSuite();
  }

  public AllSuites(Class theClass)
  {
    super(theClass);
    populateSuite();
  }

  public AllSuites(String name)
  {
    super(name);
    populateSuite();
  }

  protected void populateSuite()
  {
    for (int i = 0; i < suites.length; i++)
    {
      addTest(suites[i]);
    }
  }
}