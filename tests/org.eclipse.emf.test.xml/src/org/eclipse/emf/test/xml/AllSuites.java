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
 * $Id: AllSuites.java,v 1.21 2011/10/26 08:05:07 emerks Exp $
 */
package org.eclipse.emf.test.xml;


import junit.framework.Test;
import junit.framework.TestSuite;


public class AllSuites extends TestSuite
{
  public static final String PLUGIN_ID = "org.eclipse.emf.test.xml";
  
  private static Test[] suites = new Test []{ 
    org.eclipse.emf.test.xml.xmi.LineWidthTest.suite()
    ,org.eclipse.emf.test.xml.xmi.ElementHandlerTest.suite()
    ,org.eclipse.emf.test.xml.xmi.NullNamespaceTest.suite()
    ,org.eclipse.emf.test.xml.xmi.NamespaceTest.suite()
    ,org.eclipse.emf.test.xml.xmi.OrderTest.suite()
    ,org.eclipse.emf.test.xml.xmi.QNameTest.suite()
    ,org.eclipse.emf.test.xml.xmi.CrossResourceReferencesTest.suite()
    ,org.eclipse.emf.test.xml.xmi.MultipleDocumentRootTest.suite()
    ,org.eclipse.emf.test.xml.encoding.UnicodeEncodingTest.suite() 
    ,org.eclipse.emf.test.xml.encoding.XMLHeaderTest.suite() 
    ,org.eclipse.emf.test.xml.xsdecore.Ecore2XSDTest.suite() 
    ,org.eclipse.emf.test.xml.xsdecore.XSDEcoreBuilderTests.suite()
    ,org.eclipse.emf.test.xml.xsdecore.XSD2EcoreTest.suite()
    ,org.eclipse.emf.test.xml.xsdecore.XSDValidateTest.suite()
    ,org.eclipse.emf.test.xml.xsdecore.XSDTests.suite()
    ,org.eclipse.emf.test.xml.rss.RSSTests.suite()
    ,ProcessingInstructionTest.suite()
    ,DTDTest.suite()
    ,org.eclipse.emf.test.xml.xmi.URIHandlerTest.suite()
    ,org.eclipse.emf.test.xml.xmi.LaxFeatureNamespaceMatchingTest.suite()
    ,org.eclipse.emf.test.xml.xsd.XSDFeatureTypeTest.suite()
  };

  public static Test suite()
  {
    return new AllSuites("EMF XML and XMI JUnit Test Suite");
  }

  public AllSuites()
  {
    super();
    populateSuite();
  }

  public AllSuites(Class<?> theClass)
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