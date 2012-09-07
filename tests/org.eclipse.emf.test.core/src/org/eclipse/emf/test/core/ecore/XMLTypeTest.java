/**
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

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
    ts.addTest(new XMLTypeTest("testListSimpleTypeConversion"));
    ts.addTest(new XMLTypeTest("testFloatingPoint"));
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

  /**
   * @see https://bugs.eclipse.org/bugs/show_bug.cgi?id=376967
   */
  public void testListSimpleTypeConversion()
  {
    List<String> nmTokens = XMLTypeFactory.eINSTANCE.createNMTOKENS("a b c");
    assertEquals(3, nmTokens.size());
    nmTokens = XMLTypeFactory.eINSTANCE.createNMTOKENS("a  b  c");
    assertEquals(3, nmTokens.size());
    nmTokens = XMLTypeFactory.eINSTANCE.createNMTOKENS("a\tb\tc");
    assertEquals(3, nmTokens.size());
    nmTokens = XMLTypeFactory.eINSTANCE.createNMTOKENS("a\n\tb\n\tc");
    assertEquals(3, nmTokens.size());
    nmTokens = XMLTypeFactory.eINSTANCE.createNMTOKENS("a\r\tb\r\tc");
    assertEquals(3, nmTokens.size());
    nmTokens = XMLTypeFactory.eINSTANCE.createNMTOKENS("a\r\t\fb\r\t\fc");
    assertEquals(3, nmTokens.size());
    
  }
  
  private Object createValue(String literal, boolean isDouble, boolean useReflection, boolean usePrimitive)
  {
    if (isDouble)
    {
      if (usePrimitive)
      {
        if (useReflection)
        {
          return XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.DOUBLE, literal);
        }
        else
        {
          return XMLTypeFactory.eINSTANCE.createDouble(literal);
        }
      }
      else 
      {
        if (useReflection)
        {
          return XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.DOUBLE_OBJECT, literal);
        }
        else
        {
          return XMLTypeFactory.eINSTANCE.createDoubleObject(literal);
        }
      }
    }
    else
    {
      if (usePrimitive)
      {
        if (useReflection)
        {
          return XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.FLOAT, literal);
        }
        else
        {
          return XMLTypeFactory.eINSTANCE.createFloat(literal);
        }
      }
      else 
      {
        if (useReflection)
        {
          return XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.FLOAT_OBJECT, literal);
        }
        else
        {
          return XMLTypeFactory.eINSTANCE.createFloatObject(literal);
        }
      }
    } 
  }

  private String convertValue(Object value, boolean isDouble, boolean useReflection, boolean usePrimitive)
  {
    if (isDouble)
    {
      if (usePrimitive)
      {
        if (useReflection)
        {
          return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.DOUBLE, value);
        }
        else
        {
          return XMLTypeFactory.eINSTANCE.convertDouble((Double)value);
        }
      }
      else 
      {
        if (useReflection)
        {
          return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.DOUBLE_OBJECT, value);
        }
        else
        {
          return XMLTypeFactory.eINSTANCE.convertDoubleObject((Double)value);
        }
      }
    }
    else
    {
      if (usePrimitive)
      {
        if (useReflection)
        {
          return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.FLOAT, value);
        }
        else
        {
          return XMLTypeFactory.eINSTANCE.convertFloat((Float)value);
        }
      }
      else 
      {
        if (useReflection)
        {
          return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.FLOAT_OBJECT, value);
        }
        else
        {
          return XMLTypeFactory.eINSTANCE.convertFloatObject((Float)value);
        }
      }
    } 
  }
  
  public void testFloatingPoint()
  {
    String[] literals = 
      new String[] 
      { 
        "NaN",
        "+Infinity",
        "Infinity",
        "-Infinity",
        "+INF",
        "INF",
        "-INF",
      };
    String[] expectedLiterals = 
      new String[] 
      { 
        "NaN",
        "INF",
        "INF",
        "-INF",
        "INF",
        "INF",
        "-INF",
      };
    Double[] doubleValues = 
      new Double[] 
      { 
        Double.NaN,
        Double.POSITIVE_INFINITY,
        Double.POSITIVE_INFINITY,
        Double.NEGATIVE_INFINITY,
        Double.POSITIVE_INFINITY,
        Double.POSITIVE_INFINITY,
        Double.NEGATIVE_INFINITY,
      };
    Float[] floatValues = 
      new Float[] 
      { 
        Float.NaN,
        Float.POSITIVE_INFINITY,
        Float.POSITIVE_INFINITY,
        Float.NEGATIVE_INFINITY,
        Float.POSITIVE_INFINITY,
        Float.POSITIVE_INFINITY,
        Float.NEGATIVE_INFINITY,
      };
    
    for (int i = 0; i < literals.length; ++i)
    {
      for (int isDouble = 0; isDouble < 2; ++isDouble)
      {
        Object[] values = isDouble == 0 ? floatValues : doubleValues;
        for (int useReflection = 0; useReflection < 2; ++useReflection)
        {
          for (int usePrimitive = 0; usePrimitive < 2; ++usePrimitive)
          {
            Object value = createValue(literals[i], isDouble == 1, useReflection == 1, usePrimitive == 1);
            assertEquals
              ("Creating '" + literals[i] + "' as " + (isDouble == 0 ? usePrimitive == 0 ? "Float" : "float" : usePrimitive == 0 ? "Double" : "double") + " " + (useReflection == 0 ? "directly" : "reflectively"), 
               values[i], 
               value);
            String literal = convertValue(values[i], isDouble == 1, useReflection == 1, usePrimitive == 1);
            assertEquals
              ("Converting " + values[i] + " as " + (isDouble == 0 ? usePrimitive == 0 ? "Float" : "float" : usePrimitive == 0 ? "Double" : "double") + " " + (useReflection == 0 ? "directly" : "reflectively"), 
               expectedLiterals[i], 
               literal);
          }
        }
      }
    }
  }
}
