/**
 * <copyright>
 *
 * Copyright (c) 2008 IBM Corporation and others.
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
 * $Id: XMLTypeTest.java,v 1.1 2008/10/16 17:46:53 davidms Exp $
 */
package org.eclipse.emf.test.core.ecore;

import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;

public class XMLTypeTest extends TestCase
{
  public XMLTypeTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("XMLTypeTest");
    ts.addTest(new XMLTypeTest("testValidGMonth"));
    
    // Sun and IBM 5.0 JREs both accept the incorrect gMonth format.
    // Sun 6.0 does not. I'm not sure about IBM 6.0, so this may have to change.
    //
    if (System.getProperty("java.version").startsWith("1.5"))
    {
      ts.addTest(new XMLTypeTest("testInvalidGMonthLax"));
    }
    else
    {
      ts.addTest(new XMLTypeTest("testInvalidGMonthStrict"));
    }
    return ts;
  }

  /**
   * Bug 251090: tests valid gMonth forms (--MM), which should work on any JRE.
   * See http://www.w3.org/2001/05/xmlschema-errata#e2-12
   */
  public void testValidGMonth()
  {
    XMLGregorianCalendar gMonth = XMLTypeFactory.eINSTANCE.createGMonth("--12");
    assertEquals("--12", gMonth.toString());

    gMonth = XMLTypeFactory.eINSTANCE.createGMonth("--12Z");
    assertEquals("--12Z", gMonth.toString());

    gMonth = XMLTypeFactory.eINSTANCE.createGMonth("--12+05:00");
    assertEquals("--12+05:00", gMonth.toString());

    gMonth = XMLTypeFactory.eINSTANCE.createGMonth("--12-05:00");
    assertEquals("--12-05:00", gMonth.toString());
  }

  /**
   * Bug 251090: tests invalid gMonth forms (--MM--), expecting that they will be made valid.
   * See http://www.w3.org/2001/05/xmlschema-errata#e2-12
   */
  public void testInvalidGMonthLax()
  {
    XMLGregorianCalendar gMonth = XMLTypeFactory.eINSTANCE.createGMonth("--12--");
    assertEquals("--12", gMonth.toString());

    gMonth = XMLTypeFactory.eINSTANCE.createGMonth("--12--Z");
    assertEquals("--12Z", gMonth.toString());

    gMonth = XMLTypeFactory.eINSTANCE.createGMonth("--12--+05:00");
    assertEquals("--12+05:00", gMonth.toString());

    gMonth = XMLTypeFactory.eINSTANCE.createGMonth("--12---05:00");
    assertEquals("--12-05:00", gMonth.toString());
  }

  /**
   * Bug 251090: tests invalid gMonth forms (--MM--), expecting that they will fail.
   * See http://www.w3.org/2001/05/xmlschema-errata#e2-12
   */
  public void testInvalidGMonthStrict()
  {
    try
    {
      XMLTypeFactory.eINSTANCE.createGMonth("--12--");
      fail("gMonth value '--12--' is invalid");
    }
    catch (IllegalArgumentException e)
    {
      // Expected.
    }

    try
    {
      XMLTypeFactory.eINSTANCE.createGMonth("--12--Z");
      fail("gMonth value '--12--Z' is invalid");
    }
    catch (IllegalArgumentException e)
    {
      // Expected.
    }

    try
    {
      XMLTypeFactory.eINSTANCE.createGMonth("--12--+05:00");
      fail("gMonth value '--12--+05:00' is invalid");
    }
    catch (IllegalArgumentException e)
    {
      // Expected.
    }

    try
    {
      XMLTypeFactory.eINSTANCE.createGMonth("--12---05:00");
      fail("gMonth value '--12---05:00' is invalid");
    }
    catch (IllegalArgumentException e)
    {
      // Expected.      
    }
  }
}
